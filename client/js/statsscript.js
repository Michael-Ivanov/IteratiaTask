function statsButtonFunction() {
    let charCode1 = document.getElementById("select1").value;
    let charCode2 = document.getElementById("select2").value;

    // introduce graphql query
    let query = `query getStats($charCode1: String, $charCode2: String) {
                      stats(charCode1: $charCode1, charCode2: $charCode2) {
                            avgRate,
                            sumValue
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
            variables: {charCode1, charCode2}
        })
    })
        .then((res) => res.json())
        .then((result) => {
            // set resultSum value to 'sum_output' input
            if (charCode1 === "NoValue" || charCode2 === "NoValue") {
                alert("Выберите пару валют для просмотра статистики")
                return;
            }

            document.getElementById("stats_panel").hidden = false;

            let avgRate = result.data.stats.avgRate;
            let sumValue = result.data.stats.sumValue;

            let holder = document.getElementById("stats_holder");
            if (avgRate !== 0 && sumValue !== 0) {
                holder.innerText = "Средний курс конвертации: " + avgRate +
                    "\nСуммарный объем конвертаций: " + sumValue + " " + charCode1;
            } else {
                holder.innerText = "Нет операций"
            }

        })
        .catch(error => {
            console.log(error);
        });
}