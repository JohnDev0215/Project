package org.chromium.content.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;
import android.view.SurfaceControl;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class SurfaceWrapper implements Parcelable {
    public static final Parcelable.Creator<SurfaceWrapper> CREATOR = new a();
    public final boolean e;
    public Surface f;
    public final boolean g;
    public SurfaceControl h;

    public class a implements Parcelable.Creator<SurfaceWrapper> {
        @Override // android.os.Parcelable.Creator
        public final SurfaceWrapper createFromParcel(Parcel parcel) {
            if (parcel.readInt() == 1) {
                return new SurfaceWrapper((Surface) Surface.CREATOR.createFromParcel(parcel), parcel.readInt() == 1);
            }
            return new SurfaceWrapper((SurfaceControl) SurfaceControl.CREATOR.createFromParcel(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public final SurfaceWrapper[] newArray(int i) {
            return new SurfaceWrapper[i];
        }
    }

    public SurfaceWrapper(Surface surface, boolean z) {
        this.e = true;
        this.f = surface;
        this.g = z;
        this.h = null;
    }

    @CalledByNative
    public static SurfaceWrapper create(Surface surface, boolean z) {
        return new SurfaceWrapper(surface, z);
    }

    @CalledByNative
    public static SurfaceWrapper createFromSurfaceControl(SurfaceControl surfaceControl) {
        return new SurfaceWrapper(surfaceControl);
    }

    @CalledByNative
    public final boolean canBeUsedWithSurfaceControl() {
        return this.g;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @CalledByNative
    public final boolean getWrapsSurface() {
        return this.e;
    }

    @CalledByNative
    public final Surface takeSurface() {
        Surface surface = this.f;
        this.f = null;
        return surface;
    }

    @CalledByNative
    public final SurfaceControl takeSurfaceControl() {
        SurfaceControl surfaceControl = this.h;
        this.h = null;
        return surfaceControl;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        boolean z = this.e;
        parcel.writeInt(z ? 1 : 0);
        if (!z) {
            this.h.writeToParcel(parcel, 0);
        } else {
            this.f.writeToParcel(parcel, 0);
            parcel.writeInt(this.g ? 1 : 0);
        }
    }

    public SurfaceWrapper(SurfaceControl surfaceControl) {
        this.e = false;
        this.f = null;
        this.g = true;
        this.h = surfaceControl;
    }
}
