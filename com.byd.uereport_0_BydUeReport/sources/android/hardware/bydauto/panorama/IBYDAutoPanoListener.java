package android.hardware.bydauto.panorama;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface IBYDAutoPanoListener extends IInterface {

    public static abstract class Stub extends Binder implements IBYDAutoPanoListener {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IBYDAutoPanoListener asInterface(IBinder iBinder) {
            throw new RuntimeException("Stub!");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            throw new RuntimeException("Stub!");
        }

        @Override // android.os.Binder
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) throws RemoteException {
            throw new RuntimeException("Stub!");
        }
    }

    String getPackageName() throws RemoteException;

    String getProperty(String str) throws RemoteException;

    boolean onBufferValueChanged(int i8, byte[] bArr) throws RemoteException;

    boolean onIntValueChanged(int i8, int i9) throws RemoteException;

    boolean onMapValueChanged(int i8, Map map) throws RemoteException;
}
