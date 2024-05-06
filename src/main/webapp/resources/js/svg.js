function validateAndSetR(a1, a2, input) {
    let value = validate(a1, a2, input)
    if (!(value < a1 || value > a2 || isNaN(value))) {
        changePositionOfCircles(value)
        setR(value)
    }
}

function setR(value) {
    $(".R").html(value * 1)
    $(".-R").html(value * -1)
    $(".R5").html(value * 0.5)
    $(".-R5").html(value * -0.5)
}

function changePositionOfCircles(R) {
    let r0 = document.getElementsByClassName("R")[0].innerHTML
    $(".point").each(function () {
        let x = $(this).attr("cx")
        let y = $(this).attr("cy")
        x = (x - 144 - 36) / (144 / r0)
        y = (y - 144 - 36) / (-144 / r0)
        $(this).attr("cx", x * (144 / R) + 144 + 36)
        $(this).attr("cy", y * (-144 / R) + 144 + 36)
    })
}

function removeAllPoint() {
    $("svg").find("circle").remove()
}

var svg = document.getElementById("chart");
var button = document.getElementsByClassName("link")[0];

// Добавляем обработчик события click на SVG-элемент
svg.addEventListener("click", function(e) {
    let x = e.offsetX;
    let y = e.offsetY;
    console.log(x, y);
    x = (x - 180) / 144 * $(".R").html()
    y = (180 - y) / 144 * $(".R").html()
    $(".X0").attr("value", x)
    $(".Y0").attr("value", y)
    $(".R0").attr("value", $(".R").html())
    const event = new MouseEvent("click", {
        bubbles: true,
        cancelable: true,
        view: window
    });
    button.dispatchEvent(event);
});