package org.chromium.base.process_launcher;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import org.chromium.build.annotations.UsedByReflection;

/* JADX INFO: loaded from: classes.dex */
@UsedByReflection
public final class FileDescriptorInfo implements Parcelable {
    public static final Parcelable.Creator<FileDescriptorInfo> CREATOR = new a();
    public final int e;
    public final ParcelFileDescriptor f;
    public final long g;
    public final long h;

    public class a implements Parcelable.Creator<FileDescriptorInfo> {
        @Override // android.os.Parcelable.Creator
        public final FileDescriptorInfo createFromParcel(Parcel parcel) {
            return new FileDescriptorInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final FileDescriptorInfo[] newArray(int i) {
            return new FileDescriptorInfo[i];
        }
    }

    public FileDescriptorInfo(int i, ParcelFileDescriptor parcelFileDescriptor, long j, long j2) {
        this.e = i;
        this.f = parcelFileDescriptor;
        this.g = j;
        this.h = j2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 1;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.e);
        parcel.writeParcelable(this.f, 1);
        parcel.writeLong(this.g);
        parcel.writeLong(this.h);
    }

    public FileDescriptorInfo(Parcel parcel) {
        this.e = parcel.readInt();
        this.f = (ParcelFileDescriptor) parcel.readParcelable(ParcelFileDescriptor.class.getClassLoader());
        this.g = parcel.readLong();
        this.h = parcel.readLong();
    }
}
