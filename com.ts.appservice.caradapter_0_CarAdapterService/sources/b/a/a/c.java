package b.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.ts.avm.bean.CmdBean;

/* JADX INFO: compiled from: IAvmServiceInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface c extends IInterface {

    /* JADX INFO: compiled from: IAvmServiceInterface.java */
    public static abstract class a extends Binder implements c {

        /* JADX INFO: renamed from: b.a.a.c$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IAvmServiceInterface.java */
        private static class C0002a implements c {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public static c f6b;

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private IBinder f7a;

            C0002a(IBinder iBinder) {
                this.f7a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f7a;
            }

            @Override // b.a.a.c
            public int c(d dVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(dVar != null ? dVar.asBinder() : null);
                    if (!this.f7a.transact(2, parcelObtain, parcelObtain2, 0) && a.b() != null) {
                        return a.b().c(dVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.a.c
            public void e(CmdBean cmdBean) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    if (cmdBean != null) {
                        parcelObtain.writeInt(1);
                        cmdBean.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f7a.transact(6, parcelObtain, parcelObtain2, 0) || a.b() == null) {
                        parcelObtain2.readException();
                    } else {
                        a.b().e(cmdBean);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.a.c
            public int h(b.a.a.a aVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!this.f7a.transact(9, parcelObtain, parcelObtain2, 0) && a.b() != null) {
                        return a.b().h(aVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.a.c
            public int j(b bVar) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.ts.avm.IAvmServiceInterface");
                    parcelObtain.writeStrongBinder(bVar != null ? bVar.asBinder() : null);
                    if (!this.f7a.transact(7, parcelObtain, parcelObtain2, 0) && a.b() != null) {
                        return a.b().j(bVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static c a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.ts.avm.IAvmServiceInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof c)) ? new C0002a(iBinder) : (c) iInterfaceQueryLocalInterface;
        }

        public static c b() {
            return C0002a.f6b;
        }
    }

    int c(d dVar);

    void e(CmdBean cmdBean);

    int h(b.a.a.a aVar);

    int j(b bVar);
}
