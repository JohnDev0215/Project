package android.net;

import android.net.ipmemorystore.Blob;
import android.net.ipmemorystore.IOnBlobRetrievedListener;
import android.net.ipmemorystore.IOnL2KeyResponseListener;
import android.net.ipmemorystore.IOnNetworkAttributesRetrievedListener;
import android.net.ipmemorystore.IOnSameL3NetworkResponseListener;
import android.net.ipmemorystore.IOnStatusAndCountListener;
import android.net.ipmemorystore.IOnStatusListener;
import android.net.ipmemorystore.NetworkAttributesParcelable;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.android.networkstack.metrics.NetworkIpProvisioningReported;

/* JADX INFO: loaded from: classes.dex */
public interface IIpMemoryStore extends IInterface {
    void delete(String str, boolean z, IOnStatusAndCountListener iOnStatusAndCountListener) throws RemoteException;

    void deleteCluster(String str, boolean z, IOnStatusAndCountListener iOnStatusAndCountListener) throws RemoteException;

    void factoryReset() throws RemoteException;

    void findL2Key(NetworkAttributesParcelable networkAttributesParcelable, IOnL2KeyResponseListener iOnL2KeyResponseListener) throws RemoteException;

    String getInterfaceHash() throws RemoteException;

    int getInterfaceVersion() throws RemoteException;

    void isSameNetwork(String str, String str2, IOnSameL3NetworkResponseListener iOnSameL3NetworkResponseListener) throws RemoteException;

    void retrieveBlob(String str, String str2, String str3, IOnBlobRetrievedListener iOnBlobRetrievedListener) throws RemoteException;

    void retrieveNetworkAttributes(String str, IOnNetworkAttributesRetrievedListener iOnNetworkAttributesRetrievedListener) throws RemoteException;

    void storeBlob(String str, String str2, String str3, Blob blob, IOnStatusListener iOnStatusListener) throws RemoteException;

    void storeNetworkAttributes(String str, NetworkAttributesParcelable networkAttributesParcelable, IOnStatusListener iOnStatusListener) throws RemoteException;

    public static abstract class Stub extends Binder implements IIpMemoryStore {
        private static final String DESCRIPTOR = "android$net$IIpMemoryStore".replace('$', '.');

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            String str = DESCRIPTOR;
            if (i == 1598968902) {
                parcel2.writeString(str);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(str);
                    storeNetworkAttributes(parcel.readString(), parcel.readInt() != 0 ? NetworkAttributesParcelable.CREATOR.createFromParcel(parcel) : null, IOnStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 2:
                    parcel.enforceInterface(str);
                    storeBlob(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0 ? Blob.CREATOR.createFromParcel(parcel) : null, IOnStatusListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 3:
                    parcel.enforceInterface(str);
                    findL2Key(parcel.readInt() != 0 ? NetworkAttributesParcelable.CREATOR.createFromParcel(parcel) : null, IOnL2KeyResponseListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 4:
                    parcel.enforceInterface(str);
                    isSameNetwork(parcel.readString(), parcel.readString(), IOnSameL3NetworkResponseListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 5:
                    parcel.enforceInterface(str);
                    retrieveNetworkAttributes(parcel.readString(), IOnNetworkAttributesRetrievedListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 6:
                    parcel.enforceInterface(str);
                    retrieveBlob(parcel.readString(), parcel.readString(), parcel.readString(), IOnBlobRetrievedListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case NetworkIpProvisioningReported.RANDOM_NUMBER_FIELD_NUMBER /* 7 */:
                    parcel.enforceInterface(str);
                    factoryReset();
                    return true;
                case 8:
                    parcel.enforceInterface(str);
                    delete(parcel.readString(), parcel.readInt() != 0, IOnStatusAndCountListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                case 9:
                    parcel.enforceInterface(str);
                    deleteCluster(parcel.readString(), parcel.readInt() != 0, IOnStatusAndCountListener.Stub.asInterface(parcel.readStrongBinder()));
                    return true;
                default:
                    switch (i) {
                        case 16777214:
                            parcel.enforceInterface(str);
                            parcel2.writeNoException();
                            parcel2.writeString(getInterfaceHash());
                            return true;
                        case 16777215:
                            parcel.enforceInterface(str);
                            parcel2.writeNoException();
                            parcel2.writeInt(getInterfaceVersion());
                            return true;
                        default:
                            return super.onTransact(i, parcel, parcel2, i2);
                    }
            }
        }
    }
}
