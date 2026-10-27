package com.byd.bluetoothservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.info.BtCallInfo;
import java.util.List;

/* JADX INFO: compiled from: IBTServiceCallBack.java */
/* JADX INFO: loaded from: classes.dex */
public interface a extends IInterface {

    /* JADX INFO: renamed from: com.byd.bluetoothservice.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: IBTServiceCallBack.java */
    public static abstract class AbstractBinderC0092a extends Binder implements a {

        /* JADX INFO: renamed from: com.byd.bluetoothservice.a$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IBTServiceCallBack.java */
        private static class C0093a implements a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static a f3531a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private IBinder f3532b;

            C0093a(IBinder iBinder) {
                this.f3532b = iBinder;
            }

            @Override // com.byd.bluetoothservice.a
            public void B(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.f3532b.transact(13, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().B(z);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.a
            public void CallState_Change(List<BtCallInfo> list) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeTypedList(list);
                    if (this.f3532b.transact(8, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().CallState_Change(list);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.a
            public void T(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeInt(i);
                    if (this.f3532b.transact(10, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().T(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.a
            public void Update_Call_Time(BtCallInfo btCallInfo) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    if (btCallInfo != null) {
                        parcelObtain.writeInt(1);
                        btCallInfo.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f3532b.transact(9, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().Update_Call_Time(btCallInfo);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.a
            public void V(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeInt(i);
                    if (this.f3532b.transact(2, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().V(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3532b;
            }

            @Override // com.byd.bluetoothservice.a
            public void h(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeInt(i);
                    if (this.f3532b.transact(14, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().h(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.a
            public void updateBattery(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeInt(i);
                    if (this.f3532b.transact(3, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().updateBattery(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.a
            public void updateCurrentTime(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeLong(j);
                    if (this.f3532b.transact(6, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().updateCurrentTime(j);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.a
            public void updatePlayState(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeInt(i);
                    if (this.f3532b.transact(7, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().updatePlayState(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.a
            public void updateTitle(String str, String str2, String str3, String str4) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    parcelObtain.writeString(str4);
                    if (this.f3532b.transact(4, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().updateTitle(str, str2, str3, str4);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.a
            public void updateTotalTime(long j) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeLong(j);
                    if (this.f3532b.transact(5, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().updateTotalTime(j);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.byd.bluetoothservice.a
            public void x(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.bluetoothservice.IBTServiceCallBack");
                    parcelObtain.writeInt(i);
                    if (this.f3532b.transact(1, parcelObtain, parcelObtain2, 0) || AbstractBinderC0092a.d0() == null) {
                        parcelObtain2.readException();
                    } else {
                        AbstractBinderC0092a.d0().x(i);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static a c0(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.bluetoothservice.IBTServiceCallBack");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0093a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }

        public static a d0() {
            return C0093a.f3531a;
        }
    }

    void B(boolean z) throws RemoteException;

    void CallState_Change(List<BtCallInfo> list) throws RemoteException;

    void T(int i) throws RemoteException;

    void Update_Call_Time(BtCallInfo btCallInfo) throws RemoteException;

    void V(int i) throws RemoteException;

    void h(int i) throws RemoteException;

    void updateBattery(int i) throws RemoteException;

    void updateCurrentTime(long j) throws RemoteException;

    void updatePlayState(int i) throws RemoteException;

    void updateTitle(String str, String str2, String str3, String str4) throws RemoteException;

    void updateTotalTime(long j) throws RemoteException;

    void x(int i) throws RemoteException;
}
