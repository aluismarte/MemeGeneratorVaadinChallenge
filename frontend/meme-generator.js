import {html, LitElement} from 'lit-element';

class MemeGenerator extends LitElement {

    static get properties() {
        return {
            topText: {type: String},
            bottomText: {type: String},
            src: {type: String},
            topTextY: {type: Number},
            topTextX: {type: Number},
            bottomTextY: {type: Number},
            bottomTextX: {type: Number},
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
        this.bottomTextY = 270;
        this.bottomTextX = 200;
        this.srcBase64 = "";
    }

    set src(val) {
        const oldValue = this.srcBase64;
        let generator = this;
        let baseImage = new Image();
        baseImage.src = val;
        baseImage.onload = function () {
            generator.srcBase64 = generator.getBase64Image(baseImage);
            generator.requestUpdate('src', oldValue);
        };
    }

    render() {
        return html
            `
                <div>
                    <svg id="memeGenerated" xmlns="http://www.w3.org/2000/svg" width=${this.memeWidth} height=${this.memeHeight}>
                        <style>
                            text {
                                text-align: center;
                                user-select: none;
                                cursor: pointer;
                                z-index: 4;
                            }
                        </style>
                        <image style="z-index: 2;" href="${this.srcBase64}" height="${this.memeHeight}" width="${this.memeWidth}" x=0 y=0 />
                        <text id="top-text" class="meme" dominant-baseline="middle" text-anchor="middle" fill="var(--meme-generator-text-color, white)" font-family="var(--meme-generator-font-family, 'Impact')" font-size="var(--meme-generator-font-size, '50px')" x=${this.topTextX} y=${this.topTextY} @mousedown=${this.handleMouseDown} @mouseup=${this.handleMouseUp}>
                          ${this.topText}
                        </text>
                        <text id="bottom-text" class="meme" dominant-baseline="middle" text-anchor="middle" fill="var(--meme-generator-text-color, white)" font-family="var(--meme-generator-font-family, 'Impact')" font-size="var(--meme-generator-font-size, '50px')" x=${this.bottomTextX} y=${this.bottomTextY} @mousedown=${this.handleMouseDown} @mouseup=${this.handleMouseUp}>
                          ${this.bottomText}
                         </text>
                    </svg>
                </div>
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

    _generateMeme() {
        let svg = this.shadowRoot.getElementById("memeGenerated");
        var svgData = new XMLSerializer().serializeToString(svg);
        svgData = svgData.split('var(--meme-generator-text-color, white)').join(this.style.getPropertyValue("--meme-generator-text-color"));
        svgData = svgData.split('var(--meme-generator-font-family, \'Impact\')').join(this.style.getPropertyValue("--meme-generator-font-family"));
        svgData = svgData.split('var(--meme-generator-font-size, \'50px\')').join(this.style.getPropertyValue("--meme-generator-font-size"));
        const canvas = document.createElement("canvas");
        canvas.width = this.memeWidth;
        canvas.height = this.memeHeight;
        const context = canvas.getContext("2d");
        const img = document.createElement("img");
        img.src = "data:image/svg+xml;base64," + window.btoa(svgData);
        img.onload = function () {
            context.drawImage(img, 0, 0);
            const a = document.createElement("a");
            a.download = "meme.png";
            a.href = canvas.toDataURL();
            a.click();
        };
    }

    getBase64Image(image) {
        const canvas = document.createElement("canvas");
        canvas.width = image.width;
        canvas.height = image.height;
        const context2d = canvas.getContext("2d");
        context2d.drawImage(image, 0, 0);
        return canvas.toDataURL();
    }

    handleMouseMove(e, type) {
        let svg = this.shadowRoot.getElementById("memeGenerated");
        let rect = svg.getBoundingClientRect();
        const xOffset = e.clientX - rect.left;
        const yOffset = e.clientY - rect.top;
        if (type === "top-text") {
            this.topTextX = xOffset;
            this.topTextY = yOffset;
        } else {
            this.bottomTextX = xOffset;
            this.bottomTextY = yOffset;
        }
    }

    handleMouseDown(e) {
        const target = e.target.id;
        document.myMoveEvent = (event) => { // Save for remove later
            this.handleMouseMove(event, target);
        };
        document.addEventListener('mousemove', document.myMoveEvent);
    }

    handleMouseUp(e) {
        document.removeEventListener('mousemove', document.myMoveEvent);
    }
}

customElements.define('meme-generator', MemeGenerator);