// Connect to a database directly
db = connect( "mongodb://localhost/cooker")

db.sampleData.aggregate(
    [
        {$project: {"Items Sold":1}},
        {$out: {db: "demo", coll:"sampleData"}}
    ])