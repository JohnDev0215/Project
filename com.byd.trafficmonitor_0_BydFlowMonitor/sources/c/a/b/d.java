package c.a.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.CallbackMsg;

/* JADX INFO: compiled from: IRemoteModelCallback.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends IInterface {

    /* JADX INFO: compiled from: IRemoteModelCallback.java */
    public static abstract class a extends Binder implements d {

        /* JADX INFO: renamed from: c.a.b.d$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: IRemoteModelCallback.java */
        public static class C0072a implements d {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static d f3457a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public IBinder f3458b;

            public C0072a(IBinder iBinder) {
                this.f3458b = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3458b;
            }

            @Override // c.a.b.d
            public void i(CallbackMsg callbackMsg) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelCallback");
                    if (callbackMsg != null) {
                        parcelObtain.writeInt(1);
                        callbackMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f3458b.transact(3, parcelObtain, null, 1) || a.L() == null) {
                        return;
                    }
                    a.L().i(callbackMsg);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.b.d
            public Reply w(CallbackMsg callbackMsg) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelCallback");
                    if (callbackMsg != null) {
                        parcelObtain.writeInt(1);
                        callbackMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.f3458b.transact(2, parcelObtain, parcelObtain2, 0) && a.L() != null) {
                        return a.L().w(callbackMsg);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Reply.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // c.a.b.d
            public void x(Reply reply) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.byd.mvcadapter.IRemoteModelCallback");
                    if (reply != null) {
                        parcelObtain.writeInt(1);
                        reply.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (this.f3458b.transact(4, parcelObtain, null, 1) || a.L() == null) {
                        return;
                    }
                    a.L().x(reply);
                } finally {
                    parcelObtain.recycle();
                }
            }
        }

        public static d K(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.byd.mvcadapter.IRemoteModelCallback");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) ? new C0072a(iBinder) : (d) iInterfaceQueryLocalInterface;
        }

        public static d L() {
            return C0072a.f3457a;
        }
    }

    void i(CallbackMsg callbackMsg) throws RemoteException;

    Reply w(CallbackMsg callbackMsg) throws RemoteException;

    void x(Reply reply) throws RemoteException;
}
