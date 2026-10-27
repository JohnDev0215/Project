package ts.car.service.carplay.data.config;

/* JADX INFO: loaded from: classes2.dex */
public class ButtonInfo {
    private int mButtonLocation;
    private int mButtonPressDuration;
    private int mButtonType;

    public ButtonInfo(int buttonType, int buttonLocation, int buttonPressDuration) {
        this.mButtonType = buttonType;
        this.mButtonLocation = buttonLocation;
        this.mButtonPressDuration = buttonPressDuration;
    }

    public String toString() {
        return "ButtonInfo{mButtonType=" + this.mButtonType + ", mButtonLocation=" + this.mButtonLocation + ", mButtonPressDuration=" + this.mButtonPressDuration + '}';
    }

    public int getButtonType() {
        return this.mButtonType;
    }

    public void setButtonType(int buttonType) {
        this.mButtonType = buttonType;
    }

    public int getButtonLocation() {
        return this.mButtonLocation;
    }

    public void setButtonLocation(int buttonLocation) {
        this.mButtonLocation = buttonLocation;
    }

    public int getButtonPressDuration() {
        return this.mButtonPressDuration;
    }

    public void setButtonPressDuration(int buttonPressDuration) {
        this.mButtonPressDuration = buttonPressDuration;
    }
}
