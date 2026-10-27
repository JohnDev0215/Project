package b.a.e;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: ISystemSettings.java */
/* JADX INFO: loaded from: classes2.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: ISystemSettings.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: b.a.e.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ISystemSettings.java */
        public static class C0026a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public IBinder f369a;

            public C0026a(IBinder iBinder) {
                this.f369a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f369a;
            }
        }

        public static b B(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.systemsettings.ISystemSettings");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0026a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }
    }
}
