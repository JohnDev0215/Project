package android.net;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public final class TestNetworkInterface implements Parcelable {
    public static final Parcelable.Creator<TestNetworkInterface> CREATOR = new Parcelable.Creator<TestNetworkInterface>() { // from class: android.net.TestNetworkInterface.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestNetworkInterface createFromParcel(Parcel parcel) {
            return new TestNetworkInterface(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestNetworkInterface[] newArray(int i) {
            return new TestNetworkInterface[i];
        }
    };
    private final ParcelFileDescriptor mFileDescriptor;
    private final String mInterfaceName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return this.mFileDescriptor != null ? 1 : 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mFileDescriptor, 1);
        parcel.writeString(this.mInterfaceName);
    }

    public TestNetworkInterface(ParcelFileDescriptor parcelFileDescriptor, String str) {
        this.mFileDescriptor = parcelFileDescriptor;
        this.mInterfaceName = str;
    }

    private TestNetworkInterface(Parcel parcel) {
        this.mFileDescriptor = (ParcelFileDescriptor) parcel.readParcelable(ParcelFileDescriptor.class.getClassLoader());
        this.mInterfaceName = parcel.readString();
    }

    public ParcelFileDescriptor getFileDescriptor() {
        return this.mFileDescriptor;
    }

    public String getInterfaceName() {
        return this.mInterfaceName;
    }
}
