
getCurrencies().then(result => {
        populateSelectElement("select1", result);
        populateSelectElement("select2", result);
    }
);
setHeaderDate();

function populateSelectElement(id, result) {
    let select = document.getElementById(id);

    // create and set default option for 'select'
    select.length = 0;
    let defaultOption = document.createElement('option');
    defaultOption.text = 'Выберите валюту';
    defaultOption.value = 'NoValue';
    select.add(defaultOption);
    select.selectedIndex = 0;

    // create listener to clear 'sum_output' field when currency changed
    select.addEventListener('change', () => {
        document.getElementById('sum_output').value = '';
        document.getElementById("stats_panel").hidden = true;
    })

    // for each currency element create option and append to select
    result.data.currencies.forEach(element => {
    let option = document.createElement('option');
    option.value = element.charCode;
    option.innerText = element.name;
    select.append(option);
    });
}

function getCurrencies() {
    // fetch currencies list and return promise result
    return fetch('http://localhost:8888/graphql', {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            query: "{ currencies { charCode, name } }"
        }),
    })
        .then((res) => {
            return res.json()
        })
        .catch(error => {
            console.log(error);
        });
}

// get current date. format and set date in header
function setHeaderDate() {
    let dateField = document.getElementById("date");
    let day = new Date(Date.now()).getDate().toString();
    if (day < 10) day = "0" + day;
    let month = new Date(Date.now()).getMonth().toString();
    if (month < 10) month = "0" + month;
    let year = new Date(Date.now()).getFullYear().toString();
    dateField.innerText = day + "." + month + "." + year
}
