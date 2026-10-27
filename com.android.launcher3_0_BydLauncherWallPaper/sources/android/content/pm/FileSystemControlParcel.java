package android.content.pm;

import android.content.pm.IPackageInstallerSessionFileSystemConnector;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.incremental.IIncrementalServiceConnector;
import android.os.incremental.IncrementalFileSystemControlParcel;

/* JADX INFO: loaded from: classes.dex */
public class FileSystemControlParcel implements Parcelable {
    public static final Parcelable.Creator<FileSystemControlParcel> CREATOR = new Parcelable.Creator<FileSystemControlParcel>() { // from class: android.content.pm.FileSystemControlParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileSystemControlParcel createFromParcel(Parcel parcel) {
            FileSystemControlParcel fileSystemControlParcel = new FileSystemControlParcel();
            fileSystemControlParcel.readFromParcel(parcel);
            return fileSystemControlParcel;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FileSystemControlParcel[] newArray(int i) {
            return new FileSystemControlParcel[i];
        }
    };
    public IPackageInstallerSessionFileSystemConnector callback;
    public IncrementalFileSystemControlParcel incremental;
    public IIncrementalServiceConnector service;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iDataPosition = parcel.dataPosition();
        parcel.writeInt(0);
        if (this.incremental != null) {
            parcel.writeInt(1);
            this.incremental.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        IIncrementalServiceConnector iIncrementalServiceConnector = this.service;
        parcel.writeStrongBinder(iIncrementalServiceConnector != null ? iIncrementalServiceConnector.asBinder() : null);
        IPackageInstallerSessionFileSystemConnector iPackageInstallerSessionFileSystemConnector = this.callback;
        parcel.writeStrongBinder(iPackageInstallerSessionFileSystemConnector != null ? iPackageInstallerSessionFileSystemConnector.asBinder() : null);
        int iDataPosition2 = parcel.dataPosition();
        parcel.setDataPosition(iDataPosition);
        parcel.writeInt(iDataPosition2 - iDataPosition);
        parcel.setDataPosition(iDataPosition2);
    }

    public final void readFromParcel(Parcel parcel) {
        int iDataPosition = parcel.dataPosition();
        int i = parcel.readInt();
        if (i < 0) {
            return;
        }
        try {
            if (parcel.readInt() != 0) {
                this.incremental = IncrementalFileSystemControlParcel.CREATOR.createFromParcel(parcel);
            } else {
                this.incremental = null;
            }
            if (parcel.dataPosition() - iDataPosition < i) {
                this.service = IIncrementalServiceConnector.Stub.asInterface(parcel.readStrongBinder());
                if (parcel.dataPosition() - iDataPosition < i) {
                    this.callback = IPackageInstallerSessionFileSystemConnector.Stub.asInterface(parcel.readStrongBinder());
                    if (parcel.dataPosition() - iDataPosition < i) {
                    }
                }
            }
        } finally {
            parcel.setDataPosition(iDataPosition + i);
        }
    }
}
