package ts.car.service.carplay.data.config;

/* JADX INFO: loaded from: classes2.dex */
public class ViewArea {
    private int mHeightPixel;
    private int mOriginX;
    private int mOriginY;
    private int mWidthPixel;

    public ViewArea(int i8, int i9, int i10, int i11) {
        this.mWidthPixel = i8;
        this.mHeightPixel = i9;
        this.mOriginX = i10;
        this.mOriginY = i11;
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

    public void setHeightPixel(int i8) {
        this.mHeightPixel = i8;
    }

    public void setOriginX(int i8) {
        this.mOriginX = i8;
    }

    public void setOriginY(int i8) {
        this.mOriginY = i8;
    }

    public void setWidthPixel(int i8) {
        this.mWidthPixel = i8;
    }

    public String toString() {
        return "ViewArea{mWidthPixel=" + this.mWidthPixel + ", mHeightPixel=" + this.mHeightPixel + ", mOriginX=" + this.mOriginX + ", mOriginY=" + this.mOriginY + '}';
    }
}
