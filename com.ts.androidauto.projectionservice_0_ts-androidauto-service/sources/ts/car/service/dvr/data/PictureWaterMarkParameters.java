package ts.car.service.dvr.data;

/* JADX INFO: loaded from: classes2.dex */
public final class PictureWaterMarkParameters {
    private int mBottom;
    private boolean mEnable;
    private int mLeft;
    private String mResourcePath;
    private int mRight;
    private int mTop;
    private int mType;

    public int getBottom() {
        return this.mBottom;
    }

    public void setBottom(int bottom) {
        this.mBottom = bottom;
    }

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

    public String getResourcePath() {
        return this.mResourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.mResourcePath = resourcePath;
    }

    public PictureWaterMarkParameters(int type, boolean enable, int left, int top, int right, int bottom, String resourcePath) {
        this.mType = type;
        this.mEnable = enable;
        this.mLeft = left;
        this.mTop = top;
        this.mRight = right;
        this.mBottom = bottom;
        this.mResourcePath = resourcePath;
    }
}
