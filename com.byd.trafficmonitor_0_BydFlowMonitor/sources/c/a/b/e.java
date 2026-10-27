package c.a.b;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import c.a.b.d;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.GCMsg;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.util.Map;

/* JADX INFO: compiled from: IRemoteModelService.java */
/* JADX INFO: loaded from: classes.dex */
public interface e extends IInterface {

    /* JADX INFO: compiled from: IRemoteModelService.java */
    public static abstract class a extends Binder implements e {
        public a() {
            attachInterface(this, "com.byd.mvcadapter.IRemoteModelService");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i2, Parcel parcel, Parcel parcel2, int i3) throws RemoteException {
            if (i2 == 1598968902) {
                parcel2.writeString("com.byd.mvcadapter.IRemoteModelService");
                return true;
            }
            switch (i2) {
                case 1:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    int iD = D();
                    parcel2.writeNoException();
                    parcel2.writeInt(iD);
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    r(d.a.K(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    u(d.a.K(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    ComplexData complexDataT = t(parcel.readHashMap(getClass().getClassLoader()), parcel.readInt() != 0 ? ComplexData.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (complexDataT != null) {
                        parcel2.writeInt(1);
                        complexDataT.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    Reply replyQ = q(parcel.readInt() != 0 ? GetMsg.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (replyQ != null) {
                        parcel2.writeInt(1);
                        replyQ.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    Reply replyG = G(parcel.readInt() != 0 ? InvokeMsg.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (replyG != null) {
                        parcel2.writeInt(1);
                        replyG.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 7:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    p(parcel.readInt() != 0 ? InvokeMsg.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    f(parcel.readInt() != 0 ? GCMsg.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i2, parcel, parcel2, i3);
            }
        }
    }

    int D() throws RemoteException;

    Reply G(InvokeMsg invokeMsg) throws RemoteException;

    void f(GCMsg gCMsg) throws RemoteException;

    void p(InvokeMsg invokeMsg) throws RemoteException;

    Reply q(GetMsg getMsg) throws RemoteException;

    void r(d dVar, String str, int i2, long j) throws RemoteException;

    ComplexData t(Map map, ComplexData complexData) throws RemoteException;

    void u(d dVar) throws RemoteException;
}
