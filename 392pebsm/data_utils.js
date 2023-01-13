db = connect( "mongodb://localhost/utils");
db.dropDatabase();


function    simpleDataInsertion()
{
    db = connect( "mongodb://localhost/cooker")

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
}

function    insertProducts()
{
    db = connect("mongodb://localhost/cooker")

    db.sampleData.insertOne({
            "Description" : "Samsung WideScreen 125 Inch",
            "Quantity In Stock" : 44,
            "Unit Price" : 1895.95,
            "VAT Rated" : 20,
            "Product No" : "TVWSSS03",
            "Image" : Binary("0A0D0F")
        })
}

function exampleAggregateProjection()
{
    return db.sampleData.aggregate(
        [
            {$project: {"Items Sold":1}},
            {$out: {db: "demo", coll:"sampleData"}}
        ]);
}

var functions = ()=>
{
    return db.utils.aggregate([{$project:{_id:0, "function":1}},
                               {$sort: {"function":1}} ])
}

db.utils.insertMany([
    {"function": "simpleDataInsertion()"},
    {"function": "exampleAggregateProjection()"},
    {"function": "insertProducts()"}
])
