<!DOCTYPE html>

<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://bootstrap-ru.com/203/assets/css/bootstrap.css"">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="jumbotron">
        <div class="row">
            <div class="col-lg-6">
                    <div class="input-group">
                        <input id="text" name="text" type="text" class="form-control" placeholder="Search user">
                        <span class="input-group-btn">
                            <button id="search" class="btn btn-default" type="submit" onclick="search()">Search</button>
                        </span>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="select1">Параметры поиска</label>
                        <div class="controls">
                            <select id="select1">
                                <option id="1" value="first_name">Имя</option>
                                <option id="2" value="last_name">Фамилия</option>
                                <option id="3" value="number">Номер телефона</option>
                                <option id="4" value="coordinate">Координаты</option>
                            </select>
                        </div>
                </div>
            </div>
            <div class="col-lg-2">
                <input id="x1" name="text" type="text" class="form-control" placeholder="X min">
                <input id="x2" name="text" type="text" class="form-control" placeholder="X max">
                <input id="y1" name="text" type="text" class="form-control" placeholder="Y min">
                <input id="y2" name="text" type="text" class="form-control" placeholder="Y max">
            </div>
        </div>
    </div>
    <div id="result"></div>
</div>
<script type="text/javascript">
    function search() {
        var text = document.getElementById("text");
        var x1 = document.getElementById("x1");
        var x2 = document.getElementById("x2");
        var y1 = document.getElementById("y1");
        var y2 = document.getElementById("y2");

        var select = document.getElementById("select1");
        var xhr = new XMLHttpRequest();

        for(var i=0;select.options.length;i++){
            if(select.options[i].selected){
                if(i===3)
                    xhr.open('GET', 'http://localhost:8081/user?x1='+x1.value+"&x2="+x2.value+"&y1="+y1.value+"&y2="+y2.value, true);
                else
                xhr.open('GET', 'http://localhost:8081/user?'+select.options[i].value+"="+text.value, true);
                xhr.send();
                xhr.onreadystatechange = function () {
                    if (this.readyState !==4) return;
                    if (this.status !== 200) {
                        return;
                    }
                    var listUsers = JSON.parse(xhr.responseText);
                    var div = document.createElement("div");
                    div.className = "table-responsive";
                    var table = document.createElement("table");
                    table.className="table table-striped";
                    table.innerHTML="<thead><tr><th>#</th><th>Имя</th><th>Фамилия</th><th>Номер</th><th>Координты</th></tr></thead>";
                    var tbody = document.createElement("tbody");
                    listUsers.forEach(function (item) {
                        var tr = document.createElement("tr");
                        var id= document.createElement("td");
                        id.innerHTML=item.id;
                        var firstname= document.createElement("td");
                        firstname.innerHTML=item.firstName;
                        var lastname= document.createElement("td");
                        lastname.innerHTML=item.lastName;
                        var number= document.createElement("td");
                        number.innerHTML=item.number;
                        var cord= document.createElement("td");
                        cord.innerHTML="x="+item.coordinateX+" y="+item.coordinateY;
                        tr.appendChild(id);
                        tr.appendChild(firstname);
                        tr.appendChild(lastname);
                        tr.appendChild(number);
                        tr.appendChild(cord);
                        tbody.appendChild(tr);
                    });
                    table.appendChild(tbody);
                    div.appendChild(table);
                    var container = document.getElementById("result");
                    removeChildren(container);
                    container.appendChild(div);
                }
            }
        }
    }
    function removeChildren(node) {
        var children = node.childNodes;
        for(var i=0;i<children.length; i++) {
            var child = children[i];
            node.removeChild(child);
        }
    }
</script>
</body>
</html>