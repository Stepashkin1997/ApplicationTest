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
            sec: 0
        }
    },
    methods: {
        randomValue: function () {
            do {
                this.random = Math.floor(Math.random() * (1010 - 1002)) + 1002;
            } while (this.random == 1007 || this.random == 1009);
            this.showstatus = true;
            this.namebutton = "Restart Test";
        },
        progress: function () {
            this.showprogress = true;
            var timerId = setInterval(() => {
                console.log("aleee");
                this.incr()
            }, testTime * 10);

            setTimeout(() => {
                clearInterval(timerId);
                this.randomValue();
            }, testTime * 1000);
        },
        incr: function () {
            this.percent++;
            this.sec = parseInt(testTime - (this.percent * 0.3));
        }
    }
});

