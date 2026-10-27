package org.chromium.content.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Surface;

/* JADX INFO: loaded from: classes3.dex */
public class SurfaceWrapper implements Parcelable {
    public static final Parcelable.Creator<SurfaceWrapper> CREATOR = new Parcelable.Creator<SurfaceWrapper>() { // from class: org.chromium.content.common.SurfaceWrapper.1
        @Override // android.os.Parcelable.Creator
        public SurfaceWrapper createFromParcel(Parcel parcel) {
            return new SurfaceWrapper((Surface) Surface.CREATOR.createFromParcel(parcel));
        }

        @Override // android.os.Parcelable.Creator
        public SurfaceWrapper[] newArray(int i) {
            return new SurfaceWrapper[i];
        }
    };
    private final Surface mSurface;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SurfaceWrapper(Surface surface) {
        this.mSurface = surface;
    }

    public Surface getSurface() {
        return this.mSurface;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.mSurface.writeToParcel(parcel, 0);
    }
}
