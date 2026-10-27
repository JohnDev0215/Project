package d.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: ISystemSettings.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: ISystemSettings.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: d.a.a.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ISystemSettings.java */
        private static class C0083a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f4673a;

            C0083a(IBinder iBinder) {
                this.f4673a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4673a;
            }
        }

        public static b h(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.systemsettings.ISystemSettings");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0083a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }
    }
}
