package ts.car.service.carplay.data.config;

/* JADX INFO: loaded from: classes5.dex */
public class ButtonInfo {
    private int mButtonLocation;
    private int mButtonPressDuration;
    private int mButtonType;

    public ButtonInfo(int i, int i2, int i3) {
        this.mButtonType = i;
        this.mButtonLocation = i2;
        this.mButtonPressDuration = i3;
    }

    public String toString() {
        return "ButtonInfo{mButtonType=" + this.mButtonType + ", mButtonLocation=" + this.mButtonLocation + ", mButtonPressDuration=" + this.mButtonPressDuration + '}';
    }

    public int getButtonType() {
        return this.mButtonType;
    }

    public void setButtonType(int i) {
        this.mButtonType = i;
    }

    public int getButtonLocation() {
        return this.mButtonLocation;
    }

    public void setButtonLocation(int i) {
        this.mButtonLocation = i;
    }

    public int getButtonPressDuration() {
        return this.mButtonPressDuration;
    }

    public void setButtonPressDuration(int i) {
        this.mButtonPressDuration = i;
    }
}
