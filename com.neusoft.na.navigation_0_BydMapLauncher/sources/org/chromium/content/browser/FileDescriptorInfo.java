package org.chromium.content.browser;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public final class FileDescriptorInfo implements Parcelable {
    public static final Parcelable.Creator<FileDescriptorInfo> CREATOR = new Parcelable.Creator<FileDescriptorInfo>() { // from class: org.chromium.content.browser.FileDescriptorInfo.1
        @Override // android.os.Parcelable.Creator
        public FileDescriptorInfo createFromParcel(Parcel parcel) {
            return new FileDescriptorInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public FileDescriptorInfo[] newArray(int i) {
            return new FileDescriptorInfo[i];
        }
    };
    public final ParcelFileDescriptor mFd;
    public final int mId;
    public final long mOffset;
    public final long mSize;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 1;
    }

    FileDescriptorInfo(int i, ParcelFileDescriptor parcelFileDescriptor, long j, long j2) {
        this.mId = i;
        this.mFd = parcelFileDescriptor;
        this.mOffset = j;
        this.mSize = j2;
    }

    FileDescriptorInfo(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mFd = (ParcelFileDescriptor) parcel.readParcelable(null);
        this.mOffset = parcel.readLong();
        this.mSize = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeParcelable(this.mFd, 1);
        parcel.writeLong(this.mOffset);
        parcel.writeLong(this.mSize);
    }
}
