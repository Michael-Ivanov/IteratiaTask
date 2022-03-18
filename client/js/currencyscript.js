fetchCurrencies("select1");
fetchCurrencies("select2");

function fetchCurrencies(id) {
    var select = document.getElementById(id);
    select.length = 0;

    var defaultOption = document.createElement('option');
    defaultOption.text = 'Выберите валюту';


    select.add(defaultOption);
    select.selectedIndex = 0;

    fetch('http://localhost:8888/graphql', {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            query: "{ currencies { charCode, name } }"
        }),
    })
        .then((res) => res.json())
        .then((result) => {
            result.data.currencies.forEach(element => {
                let option = document.createElement('option');
                option.value = element.charCode;
                option.innerText = element.name;
                select.append(option);
            });
        });
}

var dateField = document.getElementById("date");
let day = new Date(Date.now()).getDate().toString();
if (day < 10) day = "0" + day;
let month = new Date(Date.now()).getMonth().toString();
if (month < 10) month = "0" + month;
let year = new Date(Date.now()).getFullYear().toString();
dateField.innerText = day + "." + month + "." + year