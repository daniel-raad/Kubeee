// It will attempt to perform he aggregate function on which ever db you are currectly connected to in the shell

db = connect( "mongodb://localhost/products")
db.catalogue.aggregate(
    [
        {$project: {"Items Sold":1}},
        {$out: {db: "demo", coll:"sampleData"}}
    ])