package ts.car.service.carplay.data.config;

/* JADX INFO: loaded from: classes2.dex */
public class SafeArea {
    private int mHeightPixel;
    private int mOriginX;
    private int mOriginY;
    private int mWidthPixel;

    public SafeArea(int widthPixel, int heightPixel, int originX, int originY) {
        this.mWidthPixel = widthPixel;
        this.mHeightPixel = heightPixel;
        this.mOriginX = originX;
        this.mOriginY = originY;
    }

    public String toString() {
        return "SafeArea{mWidthPixel=" + this.mWidthPixel + ", mHeightPixel=" + this.mHeightPixel + ", mOriginX=" + this.mOriginX + ", mOriginY=" + this.mOriginY + '}';
    }

    public int getWidthPixel() {
        return this.mWidthPixel;
    }

    public void setWidthPixel(int widthPixel) {
        this.mWidthPixel = widthPixel;
    }

    public int getHeightPixel() {
        return this.mHeightPixel;
    }

    public void setHeightPixel(int heightPixel) {
        this.mHeightPixel = heightPixel;
    }

    public int getOriginX() {
        return this.mOriginX;
    }

    public void setOriginX(int originX) {
        this.mOriginX = originX;
    }

    public int getOriginY() {
        return this.mOriginY;
    }

    public void setOriginY(int originY) {
        this.mOriginY = originY;
    }
}
