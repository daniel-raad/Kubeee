db.contexts.insertOne({
    "firstName" : "Tadas", 
    "lastName" : "Vaidotas", 
    "age" : 33, 
    "occupation" : "Trainer",
    "specialisation": "DevOps",
    "subjects": [
        "Docker",
        "AWS",
        "Scala"
    ]
})

return db.sampleData.aggregate(
    [
        {$project: {"Items Sold":1}},
        {$out: {db: "demo", coll:"sampleData"}}
    ]);
