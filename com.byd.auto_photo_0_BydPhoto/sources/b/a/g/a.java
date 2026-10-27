package b.a.g;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: b.a.g.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0016a extends Binder implements a {

        /* JADX INFO: renamed from: b.a.g.a$a$a, reason: collision with other inner class name */
        private static class C0017a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f472a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f473b;

            C0017a(IBinder iBinder) {
                this.f473b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f473b;
            }

            @Override // b.a.g.a
            public int disableElfFix() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    if (!this.f473b.transact(4, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().disableElfFix();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.g.a
            public int dpkInstall(String str, ParcelFileDescriptor parcelFileDescriptor) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    parcelObtain.writeString(str);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f473b.transact(5, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().dpkInstall(str, parcelFileDescriptor);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.g.a
            public List<String> dpkPayload(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    parcelObtain.writeString(str);
                    if (!this.f473b.transact(9, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().dpkPayload(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createStringArrayList();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.g.a
            public int dpkStatus(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    parcelObtain.writeString(str);
                    if (!this.f473b.transact(8, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().dpkStatus(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.g.a
            public int dpkUninstall(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    parcelObtain.writeString(str);
                    if (!this.f473b.transact(6, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().dpkUninstall(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.g.a
            public int dpkUninstallWithReason(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    parcelObtain.writeString(str);
                    if (!this.f473b.transact(7, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().dpkUninstallWithReason(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.g.a
            public int enableElfFix() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    if (!this.f473b.transact(3, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().enableElfFix();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.g.a
            public int hotfixInstall(String str, String str2, ParcelFileDescriptor parcelFileDescriptor) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (parcelFileDescriptor != null) {
                        parcelObtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f473b.transact(1, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().hotfixInstall(str, str2, parcelFileDescriptor);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.g.a
            public int hotfixUninstall(String str) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    parcelObtain.writeString(str);
                    if (!this.f473b.transact(2, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().hotfixUninstall(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.g.a
            public int isElfFeatureEnabled() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    if (!this.f473b.transact(11, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().isElfFeatureEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // b.a.g.a
            public int syncSettingsFromFileSystem() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.hotfix.IUpdateServiceInterface");
                    if (!this.f473b.transact(10, parcelObtain, parcelObtain2, 0) && AbstractBinderC0016a.b() != null) {
                        return AbstractBinderC0016a.b().syncSettingsFromFileSystem();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.hotfix.IUpdateServiceInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0017a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a b() {
            return C0017a.f472a;
        }
    }

    int disableElfFix();

    int dpkInstall(String str, ParcelFileDescriptor parcelFileDescriptor);

    List<String> dpkPayload(String str);

    int dpkStatus(String str);

    int dpkUninstall(String str);

    int dpkUninstallWithReason(String str);

    int enableElfFix();

    int hotfixInstall(String str, String str2, ParcelFileDescriptor parcelFileDescriptor);

    int hotfixUninstall(String str);

    int isElfFeatureEnabled();

    int syncSettingsFromFileSystem();
}
