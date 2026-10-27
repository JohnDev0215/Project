package org.chromium.base.process_launcher;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import defpackage.Du;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class FileDescriptorInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Du();
    public final int s;
    public final ParcelFileDescriptor t;
    public final long u;
    public final long v;

    public FileDescriptorInfo(int i, ParcelFileDescriptor parcelFileDescriptor, long j, long j2) {
        this.s = i;
        this.t = parcelFileDescriptor;
        this.u = j;
        this.v = j2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 1;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.s);
        parcel.writeParcelable(this.t, 1);
        parcel.writeLong(this.u);
        parcel.writeLong(this.v);
    }

    public FileDescriptorInfo(Parcel parcel) {
        this.s = parcel.readInt();
        this.t = (ParcelFileDescriptor) parcel.readParcelable(ParcelFileDescriptor.class.getClassLoader());
        this.u = parcel.readLong();
        this.v = parcel.readLong();
    }
}
