import {customElement, html, LitElement, property} from 'lit-element';

@customElement('new-meme-generator')
export class NewMemeGenerator extends LitElement {

    @property({type: String}) topText = "";
    @property({type: String}) bottomText = "";
    @property({type: String}) src = null;
    @property({type: Number}) topTextY = 20;
    @property({type: Number}) topTextX = 200;
    @property({type: Number}) bottomTextY = 270;
    @property({type: Number}) bottomTextX = 200;
    @property({type: Number}) memeWidth = 400;
    @property({type: Number}) memeHeight = 300;

    render() {
        return html
            `
                <div>
                <div>
            `;
    }

    _textColor(color) {
        this.style.setProperty('--new-meme-generator-text-color', color);
    }

    _fontFamily(family) {
        this.style.setProperty('--new-meme-generator-font-family', family);
    }

    _fontSize(size) {
        this.style.setProperty('--new-meme-generator-font-size', size);
    }
}