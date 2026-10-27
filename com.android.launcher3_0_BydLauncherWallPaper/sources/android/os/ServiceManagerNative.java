package android.os;

/* JADX INFO: loaded from: classes2.dex */
public final class ServiceManagerNative {
    private ServiceManagerNative() {
    }

    public static IServiceManager asInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        return new ServiceManagerProxy(iBinder);
    }
}
