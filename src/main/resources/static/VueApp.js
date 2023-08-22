const { createApp } = Vue;

const mainContainer = {
    data () {
        return {
            mensagem: "Vue funcionando!"
        }
    }
}

createApp(mainContainer).mount('#app');