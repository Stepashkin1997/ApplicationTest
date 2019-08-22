var testTime = 30;

new Vue({
    el: '#main',
    data() {
        return {
            percent: 0,
            showprogress: false,
            showstatus: false,
            random: 1002,
            namebutton: "Test",
            buttonaction: "'progress'",
            sec: 0,
            isButtonDisabled: false
        }
    },
    methods: {
        randomValue: function () {
            do {
                this.random = Math.floor(Math.random() * (1010 - 1002)) + 1002;
            } while (this.random == 1007 || this.random == 1009);
            this.showstatus = true;
            this.showprogress = false;
            this.isButtonDisabled = false;
            this.namebutton = "Restart Test";
        },
        myLoop: function () {           //  create a loop function
            setTimeout(() => {    //  call a 3s setTimeout when the loop is called
                this.percent++;
                this.sec = parseInt(testTime - (this.percent * 0.3));
                if (this.percent == 100) {            //  if the counter < 10, call the loop function
                    this.randomValue();             //  ..  again which will trigger another
                } else {
                    this.myLoop();
                }//  ..  setTimeout()
            }, 100)
        }
        ,
        progress: function () {
            this.showprogress = true;
            this.isButtonDisabled = true;
            this.myLoop()
        }
        ,
        reset: function () {
            this.showprogress = false;
            this.showstatus = false;
            this.namebutton = "Test";
            this.percent = 0;
        }
    },
    mounted() {
        if (localStorage.showprogress == "true") {
            if (localStorage.percent < 100 && localStorage.percent > 0) {
                this.percent = localStorage.percent;
                this.sec = localStorage.sec;
                this.showprogress = localStorage.showprogress;
                this.progress();
            }
        }
    },
    watch: {
        percent(newPercent) {
            localStorage.percent = newPercent;
        },
        sec(newSec) {
            localStorage.sec = newSec;
        },
        showprogress(newProgress) {
            localStorage.showprogress = newProgress;
        }
    }
})
;

