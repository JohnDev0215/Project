package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes2.dex */
public final class NavigationDirectionData {
    private int mIsHighlighted;
    private int mShape;

    public int getShape() {
        return this.mShape;
    }

    public void setShape(int i) {
        this.mShape = i;
    }

    public int getHighlighted() {
        return this.mIsHighlighted;
    }

    public void setHighlighted(int i) {
        this.mIsHighlighted = i;
    }

    public String toString() {
        return "NavigationDirectionData{mShape=" + this.mShape + ", mIsHighlighted=" + this.mIsHighlighted + '}';
    }
}
