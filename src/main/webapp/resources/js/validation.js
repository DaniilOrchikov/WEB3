function validate(a1, a2, input) {
    // удаляем все посторонние символы кроме минуса (если отрицательные числа входят в диапазон) и запятой
    input.value = input.value.replace(a1 < 0 ? /[^\d,-]/g : /[^\d,]/g, '')

    // удаляем все минусы которые находяться не в самом начале строки (перед ним есть цифра, запятая или другой минус)
    input.value = input.value.replace(/(?<=\d|-|,)-/g, "")

    // удаляем все запятые, кроме первой
    let parts = input.value.split(",");
    if (parts.length > 2)
        input.value = parts.shift() + "," + parts.join("")

    let value = input.value.replace(",", ".")
    if (value < a1 || value > a2 || isNaN(value)) {
        input.style.borderColor = "red"
        input.style.boxShadow = "0px 0px 8px red"
    } else {
        input.style.borderColor = "cornflowerblue"
        input.style.boxShadow = "0px 0px 8px cornflowerblue"
    }
    return value
}

function secondValidation(a1, a2, input) {
    console.log(input)
    if (input.val().replace(",", ".") < a1 || input.val().replace(",", ".") > a2 || isNaN(input.val().replace(",", "."))) {
        Swal.fire({
            icon: 'error',
            color: '#716add',
            title: "Введите число в диапазоне \n(" + a1 + "; " + a2 + ") для поля " + input.attr("name").split(":")[1],
            backdrop: `
                rgba(123,0,0,0.4)
                url("data/error.gif")
                left top
              `
        })
        return false
    } else {
        return true
    }
}

function validateSubmit () {
    return secondValidation(-2, 2, $( "input[id*='X']" )) &&
        secondValidation(-3, 5, $( "input[id*='Y']" ));
}