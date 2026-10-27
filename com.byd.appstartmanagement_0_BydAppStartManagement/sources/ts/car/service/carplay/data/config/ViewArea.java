package ts.car.service.carplay.data.config;

/* JADX INFO: loaded from: classes2.dex */
public class ViewArea {
    private int mHeightPixel;
    private int mOriginX;
    private int mOriginY;
    private int mWidthPixel;

    public ViewArea(int i, int i2, int i3, int i4) {
        this.mWidthPixel = i;
        this.mHeightPixel = i2;
        this.mOriginX = i3;
        this.mOriginY = i4;
    }

    public int getHeightPixel() {
        return this.mHeightPixel;
    }

    public int getOriginX() {
        return this.mOriginX;
    }

    public int getOriginY() {
        return this.mOriginY;
    }

    public int getWidthPixel() {
        return this.mWidthPixel;
    }

    public void setHeightPixel(int i) {
        this.mHeightPixel = i;
    }

    public void setOriginX(int i) {
        this.mOriginX = i;
    }

    public void setOriginY(int i) {
        this.mOriginY = i;
    }

    public void setWidthPixel(int i) {
        this.mWidthPixel = i;
    }

    public String toString() {
        return "ViewArea{mWidthPixel=" + this.mWidthPixel + ", mHeightPixel=" + this.mHeightPixel + ", mOriginX=" + this.mOriginX + ", mOriginY=" + this.mOriginY + '}';
    }
}
