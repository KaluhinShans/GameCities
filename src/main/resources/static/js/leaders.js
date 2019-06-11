function getLeaders() {
    $.ajax({
        type: "GET",
        url: "/getLeaders",
        dataType: "json",
        success: function (data) {
            setLeadersInHTML(data);
        }
    });
}

function setLeadersInHTML(data) {
    var arr = [document.getElementById("first"),
        document.getElementById("second"),
        document.getElementById("third"),
        document.getElementById("fourth"),
        document.getElementById("fifth"),
        document.getElementById("sixth"),
        document.getElementById("seventh"),
        document.getElementById("eighth"),
        document.getElementById("ninth"),
        document.getElementById("tenth")
    ];

    for(var i = 0; typeof data[i + "leader"] !== "undefined"; i++){
        arr[i].innerText =  i + 1 + ". " + data[i + "leader"] + " score:" + data[i + "score"];
    }
}

function saveLeader(){


    var name = document.getElementById("leaderName").value;
    var score = document.getElementById("saveScore").textContent;

    if(name == ''){alert("Введите имя")}else {
        $.ajax({
            type: "POST",
            url: "/createNewLeader",
            dataType: "json",
            contentType: "application/json;",
            data: JSON.stringify(
                {
                    "name": name,
                    "score": score
                })
        });

        var sdiv = document.getElementById("startForm");
        var savediv = document.getElementById("saveForm");
        savediv.className = "blockHidden";
        sdiv.className = "block";
    }
}