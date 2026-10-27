package android.view;

import android.app.WindowConfiguration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.proto.ProtoOutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
public class RemoteAnimationTarget implements Parcelable {
    public static final Parcelable.Creator<RemoteAnimationTarget> CREATOR = new Parcelable.Creator<RemoteAnimationTarget>() { // from class: android.view.RemoteAnimationTarget.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemoteAnimationTarget createFromParcel(Parcel parcel) {
            return new RemoteAnimationTarget(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RemoteAnimationTarget[] newArray(int i) {
            return new RemoteAnimationTarget[i];
        }
    };
    public static final int MODE_CHANGING = 2;
    public static final int MODE_CLOSING = 1;
    public static final int MODE_OPENING = 0;
    public final Rect clipRect;
    public final Rect contentInsets;
    public boolean isNotInRecents;
    public final boolean isTranslucent;
    public final SurfaceControl leash;
    public final Rect localBounds;
    public final int mode;

    @Deprecated
    public final Point position;
    public final int prefixOrderIndex;
    public final Rect screenSpaceBounds;

    @Deprecated
    public final Rect sourceContainerBounds;
    public final Rect startBounds;
    public final SurfaceControl startLeash;
    public final int taskId;
    public final WindowConfiguration windowConfiguration;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RemoteAnimationTarget(int i, int i2, SurfaceControl surfaceControl, boolean z, Rect rect, Rect rect2, int i3, Point point, Rect rect3, Rect rect4, WindowConfiguration windowConfiguration, boolean z2, SurfaceControl surfaceControl2, Rect rect5) {
        this.mode = i2;
        this.taskId = i;
        this.leash = surfaceControl;
        this.isTranslucent = z;
        this.clipRect = new Rect(rect);
        this.contentInsets = new Rect(rect2);
        this.prefixOrderIndex = i3;
        this.position = new Point(point);
        this.localBounds = new Rect(rect3);
        this.sourceContainerBounds = new Rect(rect4);
        this.screenSpaceBounds = new Rect(rect4);
        this.windowConfiguration = windowConfiguration;
        this.isNotInRecents = z2;
        this.startLeash = surfaceControl2;
        this.startBounds = rect5 == null ? null : new Rect(rect5);
    }

    public RemoteAnimationTarget(Parcel parcel) {
        this.taskId = parcel.readInt();
        this.mode = parcel.readInt();
        this.leash = (SurfaceControl) parcel.readParcelable(null);
        this.isTranslucent = parcel.readBoolean();
        this.clipRect = (Rect) parcel.readParcelable(null);
        this.contentInsets = (Rect) parcel.readParcelable(null);
        this.prefixOrderIndex = parcel.readInt();
        this.position = (Point) parcel.readParcelable(null);
        this.localBounds = (Rect) parcel.readParcelable(null);
        this.sourceContainerBounds = (Rect) parcel.readParcelable(null);
        this.screenSpaceBounds = (Rect) parcel.readParcelable(null);
        this.windowConfiguration = (WindowConfiguration) parcel.readParcelable(null);
        this.isNotInRecents = parcel.readBoolean();
        this.startLeash = (SurfaceControl) parcel.readParcelable(null);
        this.startBounds = (Rect) parcel.readParcelable(null);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.taskId);
        parcel.writeInt(this.mode);
        parcel.writeParcelable(this.leash, 0);
        parcel.writeBoolean(this.isTranslucent);
        parcel.writeParcelable(this.clipRect, 0);
        parcel.writeParcelable(this.contentInsets, 0);
        parcel.writeInt(this.prefixOrderIndex);
        parcel.writeParcelable(this.position, 0);
        parcel.writeParcelable(this.localBounds, 0);
        parcel.writeParcelable(this.sourceContainerBounds, 0);
        parcel.writeParcelable(this.screenSpaceBounds, 0);
        parcel.writeParcelable(this.windowConfiguration, 0);
        parcel.writeBoolean(this.isNotInRecents);
        parcel.writeParcelable(this.startLeash, 0);
        parcel.writeParcelable(this.startBounds, 0);
    }

    public void dump(PrintWriter printWriter, String str) {
        printWriter.print(str);
        printWriter.print("mode=");
        printWriter.print(this.mode);
        printWriter.print(" taskId=");
        printWriter.print(this.taskId);
        printWriter.print(" isTranslucent=");
        printWriter.print(this.isTranslucent);
        printWriter.print(" clipRect=");
        this.clipRect.printShortString(printWriter);
        printWriter.print(" contentInsets=");
        this.contentInsets.printShortString(printWriter);
        printWriter.print(" prefixOrderIndex=");
        printWriter.print(this.prefixOrderIndex);
        printWriter.print(" position=");
        this.position.printShortString(printWriter);
        printWriter.print(" sourceContainerBounds=");
        this.sourceContainerBounds.printShortString(printWriter);
        printWriter.print(" screenSpaceBounds=");
        this.screenSpaceBounds.printShortString(printWriter);
        printWriter.print(" localBounds=");
        this.localBounds.printShortString(printWriter);
        printWriter.println();
        printWriter.print(str);
        printWriter.print("windowConfiguration=");
        printWriter.println(this.windowConfiguration);
        printWriter.print(str);
        printWriter.print("leash=");
        printWriter.println(this.leash);
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        protoOutputStream.write(1120986464257L, this.taskId);
        protoOutputStream.write(1120986464258L, this.mode);
        this.leash.dumpDebug(protoOutputStream, 1146756268035L);
        protoOutputStream.write(1133871366148L, this.isTranslucent);
        this.clipRect.dumpDebug(protoOutputStream, 1146756268037L);
        this.contentInsets.dumpDebug(protoOutputStream, 1146756268038L);
        protoOutputStream.write(1120986464263L, this.prefixOrderIndex);
        this.position.dumpDebug(protoOutputStream, 1146756268040L);
        this.sourceContainerBounds.dumpDebug(protoOutputStream, 1146756268041L);
        this.screenSpaceBounds.dumpDebug(protoOutputStream, 1146756268046L);
        this.localBounds.dumpDebug(protoOutputStream, 1146756268045L);
        this.windowConfiguration.dumpDebug(protoOutputStream, 1146756268042L);
        SurfaceControl surfaceControl = this.startLeash;
        if (surfaceControl != null) {
            surfaceControl.dumpDebug(protoOutputStream, 1146756268043L);
        }
        Rect rect = this.startBounds;
        if (rect != null) {
            rect.dumpDebug(protoOutputStream, 1146756268044L);
        }
        protoOutputStream.end(jStart);
    }
}
