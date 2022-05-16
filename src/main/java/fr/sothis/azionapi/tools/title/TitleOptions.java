package fr.sothis.azionapi.tools.title;

public class TitleOptions {

    private int fadeIn;
    private int stay;
    private int fadeOut;

    public TitleOptions(int fadeIn, int stay, int fadeOut) {
        this.fadeIn = fadeIn;
        this.stay = stay;
        this.fadeOut = fadeOut;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public int getStay() {
        return stay;
    }

    public int getFadeOut() {
        return fadeOut;
    }
}
