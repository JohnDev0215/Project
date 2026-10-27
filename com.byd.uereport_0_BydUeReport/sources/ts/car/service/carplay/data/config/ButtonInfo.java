package ts.car.service.carplay.data.config;

/* JADX INFO: loaded from: classes2.dex */
public class ButtonInfo {
    private int mButtonLocation;
    private int mButtonPressDuration;
    private int mButtonType;

    public ButtonInfo(int i8, int i9, int i10) {
        this.mButtonType = i8;
        this.mButtonLocation = i9;
        this.mButtonPressDuration = i10;
    }

    public int getButtonLocation() {
        return this.mButtonLocation;
    }

    public int getButtonPressDuration() {
        return this.mButtonPressDuration;
    }

    public int getButtonType() {
        return this.mButtonType;
    }

    public void setButtonLocation(int i8) {
        this.mButtonLocation = i8;
    }

    public void setButtonPressDuration(int i8) {
        this.mButtonPressDuration = i8;
    }

    public void setButtonType(int i8) {
        this.mButtonType = i8;
    }

    public String toString() {
        return "ButtonInfo{mButtonType=" + this.mButtonType + ", mButtonLocation=" + this.mButtonLocation + ", mButtonPressDuration=" + this.mButtonPressDuration + '}';
    }
}
