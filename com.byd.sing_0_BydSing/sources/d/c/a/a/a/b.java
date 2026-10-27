package d.c.a.a.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: ISpeechFrontendRecordService.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: ISpeechFrontendRecordService.java */
    public static abstract class a extends Binder implements b {

        /* JADX INFO: renamed from: d.c.a.a.a.b$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ISpeechFrontendRecordService.java */
        private static class C0128a implements b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static b f4052a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f4053b;

            C0128a(IBinder iBinder) {
                this.f4053b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f4053b;
            }

            @Override // d.c.a.a.a.b
            public int f0() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.iflytek.autofly.speechfrontend.aidl.ISpeechFrontendRecordService");
                    if (!this.f4053b.transact(3, parcelObtain, parcelObtain2, 0) && a.r0() != null) {
                        return a.r0().f0();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // d.c.a.a.a.b
            public int j(String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.iflytek.autofly.speechfrontend.aidl.ISpeechFrontendRecordService");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    if (!this.f4053b.transact(4, parcelObtain, parcelObtain2, 0) && a.r0() != null) {
                        return a.r0().j(str, str2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // d.c.a.a.a.b
            public int j0(int i, d.c.a.a.a.a aVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.iflytek.autofly.speechfrontend.aidl.ISpeechFrontendRecordService");
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(aVar != null ? aVar.asBinder() : null);
                    if (!this.f4053b.transact(2, parcelObtain, parcelObtain2, 0) && a.r0() != null) {
                        return a.r0().j0(i, aVar);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // d.c.a.a.a.b
            public int k(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.iflytek.autofly.speechfrontend.aidl.ISpeechFrontendRecordService");
                    parcelObtain.writeString(str);
                    if (!this.f4053b.transact(1, parcelObtain, parcelObtain2, 0) && a.r0() != null) {
                        return a.r0().k(str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static b q0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.iflytek.autofly.speechfrontend.aidl.ISpeechFrontendRecordService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new C0128a(iBinder) : (b) iInterfaceQueryLocalInterface;
        }

        public static b r0() {
            return C0128a.f4052a;
        }
    }

    int f0() throws RemoteException;

    int j(String str, String str2) throws RemoteException;

    int j0(int i, d.c.a.a.a.a aVar) throws RemoteException;

    int k(String str) throws RemoteException;
}
