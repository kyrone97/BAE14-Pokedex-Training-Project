const pokemon = [
    {   
        _id: 1,
        name: "mike",
        type: "Dark",
        moves: "Bite",
        weakness: "Rock",

    },
    {
        _id: 2,
        name: "Blake",
        type: "Fire",
        moves: "Blaze", 
        weakness: "Water",

    },
    {
        _id: 3,
        name: "Dwight",
        type: "Fairy",
        moves: "Steel Tail",
        weakness: "Fire",

    },

];

function savePokemon(){
    alert("calling savePokemon");
}


function loadpokemon(){
    let tableBodyContent = " ";

    pokemon.forEach(pokemon => {
        tableBodyContent += `
                <tr>
                    <td>${pokemon._id}</td>
                    <td>${pokemon.name}</td>
                    <td>${pokemon.type}</td>
                    <td>${pokemon.moves}</td>
                    <td>${pokemon.weakness}</td>
                    <td class = "w-25">
                        <button class="btn btn-warning">Update</button> |
                        <button class="btn btn-danger">Delete</button>
                    </td>
                </tr>
    `;
    });
    const tableBody = document.getElementById('table-body');
    tableBody.innerHTML += tableBodyContent;   
}

loadpokemon();
