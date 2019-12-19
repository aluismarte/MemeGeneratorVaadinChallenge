import {css, html, LitElement} from 'lit-element';

class MemeGenerator extends LitElement {

    static get properties() {
        return {
            topText: {type: String},
            bottomText: {type: String},
            src: {type: String},
            srcBase64: {type: String},
            bottomTextY: {type: Number},
            bottomTextX: {type: Number},
            topTextY: {type: Number},
            topTextX: {type: Number},
            memeWidth: {type: Number},
            memeHeight: {type: Number}
        }
    }

    constructor() {
        super();
        this.memeWidth = 400;
        this.memeHeight = 300;
        this.topTextY = 20;
        this.topTextX = 200;
        this.bottomTextY = 280;
        this.bottomTextX = 200;
        this.srcBase64 = "";
    }

    set src(val) {
        const oldValue = this.srcBase64;
        var generator = this;
        var baseImage = new Image();
        baseImage.src = val;
        baseImage.onload = function () {
            generator.srcBase64 = generator.getBase64Image(baseImage);
            generator.requestUpdate('src', val);
        };
    }

    static get styles() {
        return css
            `
                .meme {
                    text-align: center;
                    userSelect: "none";
                    cursor: "pointer";
                    color: var(--meme-generator-text-color, white);
                    fontFamily: var(--meme-generator-font-family, 'Impact');
                    fontSize: var(--meme-generator-font-size, '50px');
                    textTransform: var(--meme-generator-text-transform, 'uppercase');
                    z-index: 4;
                }
            `;
    }

    render() {
        return html
            `
                <svg xmlns="http://www.w3.org/2000/svg" id="memeGenerated" width=${this.memeWidth} height=${this.memeHeight}>
                  <image style="z-index: 2;" href="${this.srcBase64}" height="${this.memeHeight}" width="${this.memeWidth}" x=0 y=0 />
                  <text class="meme" dominant-baseline="middle" text-anchor="middle" x=${this.topTextX} y=${this.topTextY} >
                      ${this.topText}
                  </text>
                  <text class="meme" dominant-baseline="middle" text-anchor="middle" x=${this.bottomTextX} y=${this.bottomTextY}>
                      ${this.bottomText}
                  </text>
             <div>
            `;
    }

    _textColor(color) {
        this.style.setProperty('--meme-generator-text-color', color);
    }

    _fontFamily(family) {
        this.style.setProperty('--meme-generator-font-family', family);
    }

    _fontSize(size) {
        this.style.setProperty('--meme-generator-font-size', size);
    }

    _textTransform(transform) {
        this.style.setProperty('--meme-generator-text-transform', transform);
    }

    _generateMeme() {
        // Need to convert svg and later call save
        // window.saveAs(blob, 'my-node.png');
    }

    getBase64Image(image) {
        var canvas = document.createElement("canvas");
        canvas.width = image.width;
        canvas.height = image.height;
        var context2d = canvas.getContext("2d");
        context2d.drawImage(image, 0, 0);
        var data = canvas.toDataURL();
        return data;
    }

    convertSvgToImage() {
        // Create canvas?
        // Configure canvas
        // set Images size (maybe configure)
        // Parse to canvas
    }
}

customElements.define('meme-generator', MemeGenerator);