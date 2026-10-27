package android.hardware.bydauto.panorama;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface IBYDAutoPanoService extends IInterface {
    public static final String NAME = "android.hardware.bydauto.panorama.IBYDAutoPanoService";

    public static abstract class Stub extends Binder implements IBYDAutoPanoService {
        public Stub() {
            throw new RuntimeException("Stub!");
        }

        public static IBYDAutoPanoService asInterface() {
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

    byte[] getBuffer(int i8) throws RemoteException;

    int getValue(int i8) throws RemoteException;

    int registerUser(IBYDAutoPanoListener iBYDAutoPanoListener) throws RemoteException;

    int setBuffer(int i8, byte[] bArr) throws RemoteException;

    int setMap(String[] strArr, Map map) throws RemoteException;

    int setValue(int i8, int i9) throws RemoteException;

    int unregisterUser(IBYDAutoPanoListener iBYDAutoPanoListener) throws RemoteException;
}
