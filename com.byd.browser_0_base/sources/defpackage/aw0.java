package defpackage;

import android.content.pm.ApplicationInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import defpackage.iw0;
import defpackage.zn;
import java.util.List;

/* JADX INFO: compiled from: IChildProcessService.java */
/* JADX INFO: loaded from: classes.dex */
public interface aw0 extends IInterface {
    void b(Bundle bundle);

    ApplicationInfo i();

    void o();

    void p(Bundle bundle, iw0 iw0Var, List<IBinder> list);

    boolean r(String str);

    void x(int i);

    void y();

    /* JADX INFO: compiled from: IChildProcessService.java */
    public static abstract class a extends Binder implements aw0 {
        public static final /* synthetic */ int e = 0;

        /* JADX INFO: renamed from: aw0$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IChildProcessService.java */
        public static class C0031a implements aw0 {
            public final IBinder e;

            public C0031a(IBinder iBinder) {
                this.e = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.e;
            }

            @Override // defpackage.aw0
            public final void b(Bundle bundle) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.e.transact(7, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.aw0
            public final ApplicationInfo i() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    this.e.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return (ApplicationInfo) (parcelObtain2.readInt() != 0 ? ApplicationInfo.CREATOR.createFromParcel(parcelObtain2) : null);
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.aw0
            public final void o() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    this.e.transact(6, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.aw0
            public final void p(Bundle bundle, iw0 iw0Var, List<IBinder> list) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongInterface(iw0Var);
                    parcelObtain.writeBinderList(list);
                    this.e.transact(3, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.aw0
            public final boolean r(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    parcelObtain.writeString(str);
                    this.e.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.aw0
            public final void x(int i) {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    parcelObtain.writeInt(i);
                    this.e.transact(5, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // defpackage.aw0
            public final void y() {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
                    this.e.transact(4, parcelObtain, null, 1);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public a() {
            attachInterface(this, "org.chromium.base.process_launcher.IChildProcessService");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i >= 1 && i <= 16777215) {
                parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            }
            if (i == 1598968902) {
                parcel2.writeString("org.chromium.base.process_launcher.IChildProcessService");
                return true;
            }
            iw0 c0059a = null;
            switch (i) {
                case 1:
                    boolean zR = ((zn.a) this).r(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zR ? 1 : 0);
                    return true;
                case 2:
                    ApplicationInfo applicationInfoI = ((zn.a) this).i();
                    parcel2.writeNoException();
                    if (applicationInfoI != null) {
                        parcel2.writeInt(1);
                        applicationInfoI.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 3:
                    Bundle bundle = (Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    IBinder strongBinder = parcel.readStrongBinder();
                    if (strongBinder != null) {
                        IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("org.chromium.base.process_launcher.IParentProcess");
                        c0059a = (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof iw0)) ? new iw0.a.C0059a(strongBinder) : (iw0) iInterfaceQueryLocalInterface;
                    }
                    ((zn.a) this).p(bundle, c0059a, parcel.createBinderArrayList());
                    return true;
                case 4:
                    ((zn.a) this).y();
                    return true;
                case 5:
                    ((zn.a) this).x(parcel.readInt());
                    return true;
                case 6:
                    ((zn.a) this).o();
                    return true;
                case 7:
                    ((zn.a) this).b((Bundle) (parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null));
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this;
        }
    }
}
