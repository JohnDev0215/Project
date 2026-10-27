package android.view;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.PrintWriter;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public class InsetsSourceControl implements Parcelable {
    public static final Parcelable.Creator<InsetsSourceControl> CREATOR = new Parcelable.Creator<InsetsSourceControl>() { // from class: android.view.InsetsSourceControl.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InsetsSourceControl createFromParcel(Parcel parcel) {
            return new InsetsSourceControl(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InsetsSourceControl[] newArray(int i) {
            return new InsetsSourceControl[i];
        }
    };
    private final SurfaceControl mLeash;
    private final Point mSurfacePosition;
    private final int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InsetsSourceControl(int i, SurfaceControl surfaceControl, Point point) {
        this.mType = i;
        this.mLeash = surfaceControl;
        this.mSurfacePosition = point;
    }

    public InsetsSourceControl(InsetsSourceControl insetsSourceControl) {
        this.mType = insetsSourceControl.mType;
        if (insetsSourceControl.mLeash != null) {
            this.mLeash = new SurfaceControl(insetsSourceControl.mLeash, "InsetsSourceControl");
        } else {
            this.mLeash = null;
        }
        this.mSurfacePosition = new Point(insetsSourceControl.mSurfacePosition);
    }

    public int getType() {
        return this.mType;
    }

    public SurfaceControl getLeash() {
        return this.mLeash;
    }

    public InsetsSourceControl(Parcel parcel) {
        this.mType = parcel.readInt();
        this.mLeash = (SurfaceControl) parcel.readParcelable(null);
        this.mSurfacePosition = (Point) parcel.readParcelable(null);
    }

    public boolean setSurfacePosition(int i, int i2) {
        if (this.mSurfacePosition.equals(i, i2)) {
            return false;
        }
        this.mSurfacePosition.set(i, i2);
        return true;
    }

    public Point getSurfacePosition() {
        return this.mSurfacePosition;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mType);
        parcel.writeParcelable(this.mLeash, 0);
        parcel.writeParcelable(this.mSurfacePosition, 0);
    }

    public void release(Consumer<SurfaceControl> consumer) {
        SurfaceControl surfaceControl = this.mLeash;
        if (surfaceControl != null) {
            consumer.accept(surfaceControl);
        }
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.print(str);
        printWriter.print("InsetsSourceControl type=");
        printWriter.print(InsetsState.typeToString(this.mType));
        printWriter.print(" mLeash=");
        printWriter.print(this.mLeash);
        printWriter.print(" mSurfacePosition=");
        printWriter.print(this.mSurfacePosition);
        printWriter.println();
    }
}
