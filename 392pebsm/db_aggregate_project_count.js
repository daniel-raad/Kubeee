// Connect to a database directly
db = connect( "mongodb://localhost/cooker")


db.sampleData.aggregate(
    [
        {$project: {"Items Sold":1}},
        {$count: "No of items sold:"},
        {$out: {db: "demo", coll:"results1"}}
    ])


