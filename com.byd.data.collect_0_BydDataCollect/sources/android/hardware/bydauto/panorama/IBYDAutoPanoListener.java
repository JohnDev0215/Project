package android.hardware.bydauto.panorama;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
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
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) {
            throw new RuntimeException("Stub!");
        }
    }

    String getPackageName();

    String getProperty(String str);

    boolean onBufferValueChanged(int i2, byte[] bArr);

    boolean onIntValueChanged(int i2, int i3);

    boolean onMapValueChanged(int i2, Map map);
}
