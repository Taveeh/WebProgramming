ascedingArray = [true, true, true, true];

$(function() {
    $('tbody tr').each(function () {
        let f1 = $(this).find('td').eq(1).text() || 0;
        f1 = Number(f1);
        let f2 = $(this).find('td').eq(2).text() || 0;
        f2 = Number(f2);
        let resultTd = $(this).find('td').last();
        resultTd.text(f2 / f1);
    });
});

function comparerFunction(columnIndex) {
    return function(row1, row2) {
        let v1 = $(row1).children('td').eq(columnIndex).text();
        let v2 = $(row2).children('td').eq(columnIndex).text();
        if ($.isNumeric(v1) && $.isNumeric(v2)) {
            return Number(v1) - Number(v2);
        } else {
            return v1.toString().localeCompare(v2);
        }
    }
}

$('thead th').on('click', function () {
    let tableBodyRows = $('#sortingTable tbody').eq(0);
    let rows = tableBodyRows.find('tr').toArray().sort(comparerFunction($(this).index()));
    if (!ascedingArray[$(this).index()]) {
        rows = rows.reverse();
    }
    ascedingArray[$(this).index()] = !ascedingArray[$(this).index()];
    for (let i = 0; i < rows.length; i++) {
        tableBodyRows.append(rows[i]);
    }
});

$('tfoot td').on('click', function() {
    const col = $(this).index();
    swap(col);
});


function swap(value) {
    const rows = $('#sortingTable tr').toArray();
    if (value === 3) {
        // for (const row of rows) {
        //     row.insertBefore(row.children[3], row.children[0]);
        //     row.insertBefore(row.children[1], row.children[4]);
            rows.forEach((row) => row.insertBefore(row.children[3], row.children[0]))
            rows.forEach((row) => row.insertBefore(row.children[1], row.children[4]))

        // }
    } else {
        // for (const row of rows) {
            // row.insertBefore(row.children[value + 1], row.children[value]);
            rows.forEach((row) => row.insertBefore(row.children[value + 1], row.children[value]))

        // }
    }
}

