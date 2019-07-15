window.onload = function(){
    //Select the element by id (fizzbuzzbutton)
    //Apply an event listener
    //Define a function to be invoked when triggered
    let submitButton = document.getElementById("submitButton");
    submitButton.addEventListener("click", function(){
        //alert("YOU FOOL");
        let array = [2,1,0,3,7,8,7,1,2,4,5,87,5,4,5,55,8,9,9,1,1,1,100,2,3,4,5];
        let num1 = document.getElementById("num1").value;
        let num2 = document.getElementById("num2").value;
        console.log('Array: '+array);
        console.log(fibonacci(num1));
        let sorted = sortArray(array);
        console.log(sorted);
        console.log(factorial(num1));
        console.log(rotateArray(array, num1));
        let braktest1 = "{[([()])]}";
        let braktest2 = ")({{})(";
        console.log(isBalanced(braktest1));
        console.log(isBalanced(braktest2));
    });
}

function fibonacci(driveTo){
    let start = 0;
    let returnMe = 1;
    let temp;
    //special case
    if(driveTo <= 0){
        return 0;
    }
    for(let i = 1; i < parseInt(driveTo); i++){
        temp = returnMe;
        returnMe = (start+returnMe);
        start = temp;
    }
    return returnMe;
}

//this does not work on negative numbers.
function sortArray(sortMe){
    //...x is somewhat like varargs in java
    let arrLength = Math.max(...sortMe);
    //We up count where index = sortMe[i]
    //so we need the max val to have a slot too.
    let count = new Array(arrLength+1);
    count.fill(0);
    let adder = 0;
    //sortMe value corresponds to the index of countMe
    for(let i = 0; i< sortMe.length; i++){
        adder = sortMe[i];
        count[adder] += 1;
    }
    let returnMe = [];
    //Index is separate index, it is i that we are placing
    //as the value into the sorted array depending
    //on how many values we saw in the unsorted array
    index = 0;
    for(let i = 0; i < count.length; i++){
        while(count[i] > 0){
            //console.log("val: "+count[i] +"i:"+i);
            returnMe[index] = i;
            //if count[i] there are no more numbers of that value (i)
            //to place
            count[i]--;
            index++;
        }
    }
    return returnMe;
}

function factorial(number){
    //user left num1 slot empty
    if(number == false){
        return "Type a number next time.";
    }
	if(number < 0){
		return "jerk."
	}
    if(number === 0){
        return 1;
    }
    if(number === 1){
        return 1;
    }
    return number*factorial(number-1);
}

function rotateArray(array, num){
    //these are dummy vars for cleanness
    //-num since it's rotating left
    let numInt = parseInt(-num);
    let len = parseInt(array.length);
    let returnMe = new Array(array.length);
    let temp = 0;
    //We are placing the i value into the corresponding
    //post rotation index of return me
    for(let i = 0; i < array.length; i++){
        temp = (len+numInt+i)%len;
        returnMe[temp] = array[i];
    }
    return returnMe;
}

function isBalanced(brackets){
    let brak = brackets[0];
    switch(brak) {
        case '(':
            if(brackets[brackets.length-1] === ')'){
                return true;
            }
        case '[':
            if(brackets[brackets.length-1] === ']'){
                return true;
            }
        case '{':
            if(brackets[brackets.length-1] === '}'){
                return true;
            }
        default:
            return false;
    }
    return  isBalanced(brackets.substring(1, brackets.length-1));
}