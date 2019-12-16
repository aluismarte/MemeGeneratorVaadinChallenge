import {css, html, LitElement} from 'lit-element';

class MemeGenerator extends LitElement {

    constructor() {
        super();
    }

    static get properties() {
        return {
            topText: {type: String},
            bottomText: {type: String},
            src: {type: String}
        }
    }

    static get styles() {
        return css
            `
                .meme {
                    position: relative;
                    text-align: center;
                    color: white;
                    width: 100%;
                    height: 100%;
                }
                .meme-img {
                    width: 100%;
                    height: 100%;
                }
                .top-text {
                    position: absolute;
                    margin: auto;
                    top: 0;
                    left:0;
                    right:0;
                    bottom:0;
                    top: 12px;
                }
                .bottom-text {
                    position: absolute;
                    margin: auto;
                    left:0;
                    right:0;
                    bottom:15px;
                }
            `;
    }

    render() {
        return html
            `
            <div class='meme'>
                <p class="top-text">${this.topText}</p>
                <p class="bottom-text">${this.bottomText}</p>
                <img class="meme-img" alt="" src="${this.src}"/>
             <div>
            `;
    }
}

customElements.define('meme-generator', MemeGenerator);