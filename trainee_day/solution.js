const data = [  
    ["Mawson", "South Pole", "New Hebrides"],  
    ["Mallory", "Everest", "Mont Blanc", "Pillar Rock"],  
    ["Hillary", "Everest", "South Pole"]  
];

const researchers = explorers => {
    const toponymsObj = explorers
        .reduce((acc, [researcher, ...toponyms]) => {
            toponyms.forEach(toponym => {
                if (!acc[toponym]) {
                    acc[toponym] = [];
                }
                acc[toponym].push(researcher);
            });
            return acc;
        }, {});
    
    return Object.entries(toponymsObj)
        .reduce((acc, [researcher, toponyms]) => {
            acc.push([researcher, ...toponyms]);
            return acc;
        }, []);
};

console.log(researchers(data));
