"use strict";

const fs = require('fs');
const entryFile = process.argv[2];
let data = JSON.parse(fs.readFileSync(entryFile, 'utf8'));

function findMinAndMaxProperty(athletesArray, property) {
    
    let max = 0;
    let min = 100000000;

    for(let i = 0; i < athletesArray.length; i++) {
        let athlete = athletesArray[i];
        
        if (athlete[property] > max) {    
            max = athlete[property];
        }

        if (athlete[property] < min) {
            min = athlete[property]
        }
    }

    return [min, max];
}

console.log(findMinAndMaxProperty(data['atletas'], 'preco_num'))
console.log(findMinAndMaxProperty(data['atletas'], 'media_num'))
console.log(findMinAndMaxProperty(data['atletas'], 'variacao_num'))

