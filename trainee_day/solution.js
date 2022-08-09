const data = [  
    ["Mawson", "South Pole", "New Hebrides"],  
    ["Mallory", "Everest", "Mont Blanc", "Pillar Rock"],  
    ["Hillary", "Everest", "South Pole"]  
];

const researchers = explorers => Object.values(explorers
    .reduce((acc, [researcher, ...toponyms]) => {
        toponyms.forEach(toponym => {
            if (!acc[toponym]) {
                acc[toponym] = [toponym];
            }
            acc[toponym].push(researcher);
        });
        return acc;
    }, {}));
    
console.log(researchers(data));
