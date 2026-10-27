package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes.dex */
public final class NavigationDirectionData {
    private int mIsHighlighted;
    private int mShape;

    public int getShape() {
        return this.mShape;
    }

    public void setShape(int shape) {
        this.mShape = shape;
    }

    public int getHighlighted() {
        return this.mIsHighlighted;
    }

    public void setHighlighted(int highlighted) {
        this.mIsHighlighted = highlighted;
    }

    public String toString() {
        return "NavigationDirectionData{mShape=" + this.mShape + ", mIsHighlighted=" + this.mIsHighlighted + '}';
    }
}
