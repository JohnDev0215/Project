package ts.car.service.dvr.data;

/* JADX INFO: loaded from: classes.dex */
public final class TimeWaterMarkParameters {
    private int mBottom;
    private String mColor;
    private boolean mEnable;
    private int mFontSize;
    private String mFormat;
    private int mLeft;
    private int mRight;
    private int mTop;
    private int mType;

    public int getType() {
        return this.mType;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public boolean isEnable() {
        return this.mEnable;
    }

    public void setEnable(boolean enable) {
        this.mEnable = enable;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public void setLeft(int left) {
        this.mLeft = left;
    }

    public int getTop() {
        return this.mTop;
    }

    public void setTop(int top) {
        this.mTop = top;
    }

    public int getRight() {
        return this.mRight;
    }

    public void setRight(int right) {
        this.mRight = right;
    }

    public int getBottom() {
        return this.mBottom;
    }

    public void setBottom(int bottom) {
        this.mBottom = bottom;
    }

    public String getColor() {
        return this.mColor;
    }

    public void setColor(String color) {
        this.mColor = color;
    }

    public int getFontSize() {
        return this.mFontSize;
    }

    public void setFontSize(int fontSize) {
        this.mFontSize = fontSize;
    }

    public String getFormat() {
        return this.mFormat;
    }

    public void setFormat(String format) {
        this.mFormat = format;
    }

    public TimeWaterMarkParameters(int type, boolean enable, int left, int top, int right, int bottom, String color, int fontSize, String format) {
        this.mType = type;
        this.mEnable = enable;
        this.mLeft = left;
        this.mTop = top;
        this.mRight = right;
        this.mBottom = bottom;
        this.mColor = color;
        this.mFontSize = fontSize;
        this.mFormat = format;
    }
}
