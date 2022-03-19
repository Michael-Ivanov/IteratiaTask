function buttonFunction() {

    let val1 = document.getElementById("select1").value;
    let val2 = document.getElementById("select2").value;
    let sum = document.getElementById("sum_input").value;
    operateByCharCodes(val1, val2, sum);

    // get operation object and its result sum
    function operateByCharCodes(charCode1, charCode2, sum) {

        // introduce graphql query
        let query = `query getResultOps($charCode1: String, $charCode2: String, $sum: String) {
                                operation(charCode1: $charCode1, charCode2: $charCode2, sum: $sum) {
                                         id,
                                         date,
                                         charCode1,
                                         charCode2,
                                         exchangeRate,
                                         sumToExchange,
                                         resultSum,
                                }
                           }`;
        // fetch json response
        fetch('http://localhost:8888/graphql', {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json",
            },
            body: JSON.stringify({
                query,
                variables: {charCode1, charCode2, sum}
            })
        })
            .then((res) => res.json())
            .then((result) => {
                // set resultSum value to 'sum_output' input
                document.getElementById("sum_output").value = result.data.operation.resultSum;
            });
        // todo: handle error
    }
}