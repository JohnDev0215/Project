package android.view;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class InsetsSource implements Parcelable {
    public static final Parcelable.Creator<InsetsSource> CREATOR = new Parcelable.Creator<InsetsSource>() { // from class: android.view.InsetsSource.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InsetsSource createFromParcel(Parcel parcel) {
            return new InsetsSource(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InsetsSource[] newArray(int i) {
            return new InsetsSource[i];
        }
    };
    private final Rect mFrame;
    private final Rect mTmpFrame;
    private final int mType;
    private boolean mVisible;
    private Rect mVisibleFrame;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InsetsSource(int i) {
        this.mTmpFrame = new Rect();
        this.mType = i;
        this.mFrame = new Rect();
        this.mVisible = InsetsState.getDefaultVisibility(i);
    }

    public InsetsSource(InsetsSource insetsSource) {
        this.mTmpFrame = new Rect();
        this.mType = insetsSource.mType;
        this.mFrame = new Rect(insetsSource.mFrame);
        this.mVisible = insetsSource.mVisible;
        this.mVisibleFrame = insetsSource.mVisibleFrame != null ? new Rect(insetsSource.mVisibleFrame) : null;
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        this.mFrame.set(i, i2, i3, i4);
    }

    public void setFrame(Rect rect) {
        this.mFrame.set(rect);
    }

    public void setVisibleFrame(Rect rect) {
        if (rect != null) {
            rect = new Rect(rect);
        }
        this.mVisibleFrame = rect;
    }

    public void setVisible(boolean z) {
        this.mVisible = z;
    }

    public int getType() {
        return this.mType;
    }

    public Rect getFrame() {
        return this.mFrame;
    }

    public Rect getVisibleFrame() {
        return this.mVisibleFrame;
    }

    public boolean isVisible() {
        return this.mVisible;
    }

    boolean isUserControllable() {
        Rect rect = this.mVisibleFrame;
        return rect == null || !rect.isEmpty();
    }

    public Insets calculateInsets(Rect rect, boolean z) {
        return calculateInsets(rect, this.mFrame, z);
    }

    public Insets calculateVisibleInsets(Rect rect) {
        Rect rect2 = this.mVisibleFrame;
        if (rect2 == null) {
            rect2 = this.mFrame;
        }
        return calculateInsets(rect, rect2, false);
    }

    private Insets calculateInsets(Rect rect, Rect rect2, boolean z) {
        if (!z && !this.mVisible) {
            return Insets.NONE;
        }
        if (getType() == 2) {
            return Insets.of(0, rect2.height(), 0, 0);
        }
        if (!getIntersection(rect2, rect, this.mTmpFrame)) {
            return Insets.NONE;
        }
        if (getType() == 17) {
            return Insets.of(0, 0, 0, this.mTmpFrame.height());
        }
        if (this.mTmpFrame.width() == rect.width()) {
            if (this.mTmpFrame.top == rect.top) {
                return Insets.of(0, this.mTmpFrame.height(), 0, 0);
            }
            if (this.mTmpFrame.bottom == rect.bottom) {
                return Insets.of(0, 0, 0, this.mTmpFrame.height());
            }
            if (this.mTmpFrame.top == 0) {
                return Insets.of(0, this.mTmpFrame.height(), 0, 0);
            }
        } else if (this.mTmpFrame.height() == rect.height()) {
            if (this.mTmpFrame.left == rect.left) {
                return Insets.of(this.mTmpFrame.width(), 0, 0, 0);
            }
            if (this.mTmpFrame.right == rect.right) {
                return Insets.of(0, 0, this.mTmpFrame.width(), 0);
            }
        }
        return Insets.NONE;
    }

    private static boolean getIntersection(Rect rect, Rect rect2, Rect rect3) {
        if (rect.left <= rect2.right && rect2.left <= rect.right && rect.top <= rect2.bottom && rect2.top <= rect.bottom) {
            rect3.left = Math.max(rect.left, rect2.left);
            rect3.top = Math.max(rect.top, rect2.top);
            rect3.right = Math.min(rect.right, rect2.right);
            rect3.bottom = Math.min(rect.bottom, rect2.bottom);
            return true;
        }
        rect3.setEmpty();
        return false;
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.print(str);
        printWriter.print("InsetsSource type=");
        printWriter.print(InsetsState.typeToString(this.mType));
        printWriter.print(" frame=");
        printWriter.print(this.mFrame.toShortString());
        if (this.mVisibleFrame != null) {
            printWriter.print(" visibleFrame=");
            printWriter.print(this.mVisibleFrame.toShortString());
        }
        printWriter.print(" visible=");
        printWriter.print(this.mVisible);
        printWriter.println();
    }

    public boolean equals(Object obj) {
        return equals(obj, false);
    }

    public boolean equals(Object obj, boolean z) {
        boolean z2;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InsetsSource insetsSource = (InsetsSource) obj;
        int i = this.mType;
        if (i != insetsSource.mType || (z2 = this.mVisible) != insetsSource.mVisible) {
            return false;
        }
        if (z && !z2 && i == 17) {
            return true;
        }
        if (Objects.equals(this.mVisibleFrame, insetsSource.mVisibleFrame)) {
            return this.mFrame.equals(insetsSource.mFrame);
        }
        return false;
    }

    public int hashCode() {
        int iHashCode = ((this.mType * 31) + this.mFrame.hashCode()) * 31;
        Rect rect = this.mVisibleFrame;
        return ((iHashCode + (rect != null ? rect.hashCode() : 0)) * 31) + (this.mVisible ? 1 : 0);
    }

    public InsetsSource(Parcel parcel) {
        this.mTmpFrame = new Rect();
        this.mType = parcel.readInt();
        if (parcel.readInt() != 0) {
            this.mFrame = Rect.CREATOR.createFromParcel(parcel);
        } else {
            this.mFrame = null;
        }
        if (parcel.readInt() != 0) {
            this.mVisibleFrame = Rect.CREATOR.createFromParcel(parcel);
        } else {
            this.mVisibleFrame = null;
        }
        this.mVisible = parcel.readBoolean();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        if (this.mFrame != null) {
            parcel.writeInt(1);
            this.mFrame.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        if (this.mVisibleFrame != null) {
            parcel.writeInt(1);
            this.mVisibleFrame.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeBoolean(this.mVisible);
    }

    public String toString() {
        return "InsetsSource: {mType=" + InsetsState.typeToString(this.mType) + ", mFrame=" + this.mFrame.toShortString() + ", mVisible=" + this.mVisible + "}";
    }
}
