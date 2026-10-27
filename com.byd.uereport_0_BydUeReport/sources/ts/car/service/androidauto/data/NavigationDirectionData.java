package ts.car.service.androidauto.data;

/* JADX INFO: loaded from: classes2.dex */
public final class NavigationDirectionData {
    private int mIsHighlighted;
    private int mShape;

    public int getHighlighted() {
        return this.mIsHighlighted;
    }

    public int getShape() {
        return this.mShape;
    }

    public void setHighlighted(int i8) {
        this.mIsHighlighted = i8;
    }

    public void setShape(int i8) {
        this.mShape = i8;
    }

    public String toString() {
        return "NavigationDirectionData{mShape=" + this.mShape + ", mIsHighlighted=" + this.mIsHighlighted + '}';
    }
}
