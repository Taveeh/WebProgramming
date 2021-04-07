ascedingArray = [true, true, true];
function sorting (col = 0) {
    let table, rows, switching, i, x, y, shouldSwitch;
    table = document.querySelector("#sortingTable");
    switching = true;
    let ascending = ascedingArray[col];
    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < (rows.length) - 1; i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("td")[col];
            y = rows[i + 1].getElementsByTagName("td")[col];
            if (col === 0) {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    if (ascending) {
                        shouldSwitch = true;
                        break;
                    }
                } else {
                    if (!ascending) {
                        shouldSwitch = true;
                        break;
                    }
                }
            } else {
                if (Number(x.innerHTML.toLowerCase()) > Number(y.innerHTML.toLowerCase())) {
                    if (ascending) {
                        shouldSwitch = true;
                        break;
                    }
                } else {
                    if (!ascending) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
        }
    }
    ascedingArray[col] = !ascedingArray[col];
}