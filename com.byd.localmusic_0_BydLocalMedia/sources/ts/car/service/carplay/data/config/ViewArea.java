package ts.car.service.carplay.data.config;

/* JADX INFO: loaded from: classes5.dex */
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

    public String toString() {
        return "ViewArea{mWidthPixel=" + this.mWidthPixel + ", mHeightPixel=" + this.mHeightPixel + ", mOriginX=" + this.mOriginX + ", mOriginY=" + this.mOriginY + '}';
    }

    public int getWidthPixel() {
        return this.mWidthPixel;
    }

    public void setWidthPixel(int i) {
        this.mWidthPixel = i;
    }

    public int getHeightPixel() {
        return this.mHeightPixel;
    }

    public void setHeightPixel(int i) {
        this.mHeightPixel = i;
    }

    public int getOriginX() {
        return this.mOriginX;
    }

    public void setOriginX(int i) {
        this.mOriginX = i;
    }

    public int getOriginY() {
        return this.mOriginY;
    }

    public void setOriginY(int i) {
        this.mOriginY = i;
    }
}
