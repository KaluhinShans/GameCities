function startGame() {
    var gdiv = document.getElementById("gameForm");
    var sdiv = document.getElementById("startForm");
    var score = document.getElementById("score");
    var saveButton = document.getElementById("save");
    score.parentElement.style.cssText = "color: yellow; display: block";
    saveButton.style.cssText = "display: block";

    sdiv.className = "blockHidden";
    gdiv.className = "block";

    randomCity();
}

function continueGame(){
    var gdiv = document.getElementById("gameForm");
    var savediv = document.getElementById("saveForm");
    savediv.className = "blockHidden";
    gdiv.className = "block";
    var score = document.getElementById("score");
    score.parentElement.style.cssText = "color: yellow; display: block";
}

function endGame() {
    var score = document.getElementById("score");
    if (score.innerText == 0) {
        alert("Твой счет равен нулю, набери баллы что бы конкурировать с лидерами")
    } else {
        var gdiv = document.getElementById("gameForm");
        var savediv = document.getElementById("saveForm");
        gdiv.className = "blockHidden";
        savediv.className = "block";

        document.getElementById("saveScore").innerText = score.innerText;
        score.parentElement.style.cssText = "display: none";
    }

}

function setBotAnswer(city, letter) {
    var botAnswer = document.getElementById("botAnswer");
    var lastLetter = document.getElementById("lastLetter");

    botAnswer.innerHTML = city;
    lastLetter.innerHTML = letter;

}

function randomCity() {
    $.ajax({
        type: "GET",
        url: "/randomCity",
        dataType: "json",
        success: function (data) {
            setBotAnswer(data['randomCity'], data['lastLetter']);
        }
    });
}

function sendPlayerAnswer() {
    var playerAnswer = document.getElementById("playerAnswer").value;
    var needLetter = document.getElementById("lastLetter").textContent;

    $.ajax({
        type: "POST",
        url: "/checkCity",
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(
            {
                "needLetter": needLetter,
                "playerAnswer": playerAnswer
            }),
        success: function (data) {
           if(typeof data["ERROR"] == "undefined"){
               setBotAnswer(data['newCity'],data['newLetter']);
               addScore();
           }else{
               alert(data["ERROR"])
           }
        }


    });
}

function addScore() {
 var score = document.getElementById("score");
 score.innerText = ++score.textContent;
}