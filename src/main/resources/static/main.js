let selectedPokemonId = null;

function closeModal(){
    const modalElement = document.getElementById('pokemodal');
    const modal = bootstrap.Modal.getInstance(modalElement);
    modal.hide();

    document.getElementById("name").value = " ";
    document.getElementById("type").value = " ";
    document.getElementById("moves").value = " ";
    document.getElementById("weakness").value = " ";

    document.getElementById('submitBtn').innerHTML = "Save";
    selectedPokemonId = null;

}

function submitForm(){
    if (!selectedPokemonId) savePokemon(); //update
    else updatePokemon();
}

function writePokemonRow(pokemon){
   const pokemonUpdate = encodeURIComponent(JSON.stringify(pokemon));
    return `
                <tr id = "row-${pokemon.id}">
                    
                    <td>${pokemon.id}</td>
                    <td>${pokemon.name}</td>
                    <td>${pokemon.type}</td>
                    <td>${pokemon.moves}</td>
                    <td>${pokemon.weakness}</td>
                    <td class = "w-25">
                        <button class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#pokemodal" onclick="fillFormForUpdate('${pokemonUpdate}')">Update
                        <button> |
                        <button class="btn btn-danger" onclick="deleteUser('${pokemon.id}')">Delete</button>
                    </td>
                </tr>
                `;
                
}

function fillFormForUpdate(pokemonPayLoad){
    const pokemon = JSON.parse(decodeURIComponent(pokemonPayLoad));

    document.getElementById('name').value = pokemon.name;
    document.getElementById('type').value = pokemon.type;
    document.getElementById('moves').value = pokemon.moves;
    document.getElementById('weakness').value = pokemon.weakness;
    document.getElementById('submitBtn').innerHTML = "Update";


    selectedPokemonId = pokemon.id;
    

    
}

async function savePokemon(){
    const name = document.getElementById("name").value;
    const type = document.getElementById("type").value;
    const moves = document.getElementById("moves").value;
    const weakness = document.getElementById("weakness").value;

   const {data} = await axios.post('http://localhost:8080/pokedex/create', {name,type,moves,weakness});
    
    const pokemon = { id: data.id, name, type, moves, weakness}
   
    const appendData = writePokemonRow(pokemon);

    const tableBody = document.getElementById('table-body');
    tableBody.innerHTML += appendData;  

    closeModal();
}

async function updatePokemon(){
    const name = document.getElementById("name").value;
    const type = document.getElementById("type").value;
    const moves = document.getElementById("moves").value;
    const weakness = document.getElementById("weakness").value;

    await axios({
        method: 'put',
        url: 'http://localhost:8080/pokedex/update/' + selectedPokemonId,
        data: {name,type,moves,weakness}
    });


    // const nameF = document.querySelector('#row-' + selectedPokemonId + "td:nth-child(2)");
    // const typeF = document.querySelector('#row-' + selectedPokemonId + "td:nth-child(3)");
    // const movesF = document.querySelector('#row-' + selectedPokemonId + "td:nth-child(4)");
    // const weaknessF = document.querySelector('#row-' + selectedPokemonId + "td:nth-child(5)");
    // const updateButtonField = document.querySelector('#row-' + selectedPokemonId + "td:nth-child(6) button:nth-child(1)");

    // const pokemonUpdate = encodeURIComponent(JSON.stringify({id: selectedPokemonId, name, type, moves, weakness}));

    // updateButtonField.setAttribute('onclick', 'fillFormForUpdate("' + pokemonUpdate + '")');

    // nameF.innerHTML = name;
    // typeF.innerHTML = type;
    // movesF.innerHTML = moves;
    // weaknessF.innerHTML = weakness;

 closeModal();

loadpokemon();
}


async function deleteUser(pokemonId){
    await axios.delete('http://localhost:8080/pokedex/delete/' + pokemonId);
    const pokeElement = document.getElementById("row-" + pokemonId);
    pokeElement.remove();
}



async function loadpokemon(){
    let tableBodyContent = " ";
    const {data:pokemons} = await axios.get('http://localhost:8080/pokedex/getAll');

    pokemons.forEach(pokemon => (tableBodyContent += writePokemonRow(pokemon)));
   
    const tableBody = document.getElementById('table-body');
    tableBody.innerHTML = tableBodyContent;   
}

loadpokemon(); 
