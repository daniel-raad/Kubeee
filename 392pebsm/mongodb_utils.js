db = connect( "mongodb://localhost/utils");
db.dropDatabase();


var countItemsSold_inCookerDB = ()=>
{
    db = connect( "mongodb://localhost/cooker")
    return db.sampleData.aggregate(
        [
            {$project: {"Items Sold":1}},
            {$count: "No of items sold:"}
        ])
}

var dropDemoDB = ()=>
{
    db = connect( "mongodb://localhost/demo")

    db.dropDatabase()
}

var functions = ()=>
{
    return db.utils.aggregate([{$project:{_id:0, "function":1}},
                               {$sort: {"function":1}} ])
}

db.utils.insertMany([
    {"function": "functions()"},
    {"function": "countItemsSold_inCookerDB()"},
    {"function": "dropDemoDB()"}
])

// db.utils.insertOne({
//     functions:[
//         {"function": "countItemsSold_inCookerDB()"},
//         {"function": "dropDemoDB()"}
// ]})

