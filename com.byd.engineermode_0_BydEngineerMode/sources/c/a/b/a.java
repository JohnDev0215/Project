package c.a.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* JADX INFO: compiled from: IAuthBinder.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: c.a.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IAuthBinder.java */
    public static abstract class AbstractBinderC0057a extends Binder implements a {

        /* JADX INFO: renamed from: c.a.b.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IAuthBinder.java */
        private static class C0058a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f2080a;

            C0058a(IBinder iBinder) {
                this.f2080a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f2080a;
            }
        }

        public static a j(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.logtools.IAuthBinder");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0058a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }
    }
}
