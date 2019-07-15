var templateList = [];

window.onload = function () {
    let title, author, publisher, ISBN, cover;
    getBooks();

    for (let i = 0; i < 4; i++) {
        setVars(`title : ${i}`, "Billy", "penguin", "askjdfhiy", 'https://about.canva.com/wp-content/uploads/sites/3/2015/01/Design-an-Original-Book-Cover-tb-1324x0.png');
        console.log(`book${i}`);
        let temp = document.getElementById(`book${i}`);
        //console.log(templateList[i]);
        temp.innerHTML = templateList[i];
        temp.addEventListener("click", function () {
            displayBox(templateList[i]);
        })
    }
}

function setVars(title, auth, publishing, ISBN, imgLink) {
    let bookTemplate = `
    <div class="bookInner">
    <h3>${title}</h3>
    <div class="bookInnerDisplay">
        <h5>${auth}</h5>
        <h6>${publishing}</h6>
        <h6>${ISBN}</h6>
        <img src="${imgLink}" alt="cover for book">
        <p>This is a blurb for a book</p>
    </div>
    </div>`;
    templateList.push(bookTemplate)
}

function displayBox(templateToDisplay) {
    let temp = document.getElementById("bookDisplay");
    temp.innerHTML = templateToDisplay;
    let modal = document.getElementById("modalBox");
    modal.style.display = "block";
    let close = document.getElementById("close");
    close.onclick = function () {
        modal.style.display = "none";
    }
}

function getBooks() {
    url = "http://openlibrary.org/search.json?q=gene+wolfe+sun";
    let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    //I'm not sure what was going on here, but I read a few places
    //that firefox sometimes defaults to xml instead of json
    //as a response type, otherwise XML malformed error
    xhr.overrideMimeType("text/json");
    console.log("After step2");
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            console.log("ready...");
            parseVars(this); 
        }
    }
    xhr.open("GET", url, true);
    xhr.send();
}

function parseVars(xhr) {
    //returned as a JSON obj, no need to parse
    let bookObj = xhr;
    for(let i = 0; i < 4; i++){
        console.log(bookObj.docs[i].title_suggest);
    }
}