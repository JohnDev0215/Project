package android.hardware.bydauto.panorama;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
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
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            throw new RuntimeException("Stub!");
        }
    }

    byte[] getBuffer(int i2);

    int getValue(int i2);

    int registerUser(IBYDAutoPanoListener iBYDAutoPanoListener);

    int setBuffer(int i2, byte[] bArr);

    int setMap(String[] strArr, Map map);

    int setValue(int i2, int i3);

    int unregisterUser(IBYDAutoPanoListener iBYDAutoPanoListener);
}
