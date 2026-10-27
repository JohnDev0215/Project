package t3;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.mvcadapter.ComplexData;
import com.byd.mvcadapter.Reply;
import com.byd.mvcadapter.rpc.GCMsg;
import com.byd.mvcadapter.rpc.GetMsg;
import com.byd.mvcadapter.rpc.InvokeMsg;
import java.util.Map;
import t3.d;

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
        public boolean onTransact(int i8, Parcel parcel, Parcel parcel2, int i9) throws RemoteException {
            if (i8 == 1598968902) {
                parcel2.writeString("com.byd.mvcadapter.IRemoteModelService");
                return true;
            }
            switch (i8) {
                case 1:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    int iR0 = r0();
                    parcel2.writeNoException();
                    parcel2.writeInt(iR0);
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    c0(d.a.A0(parcel.readStrongBinder()), parcel.readString(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    f0(d.a.A0(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    ComplexData complexDataE0 = e0(parcel.readHashMap(getClass().getClassLoader()), parcel.readInt() != 0 ? ComplexData.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (complexDataE0 != null) {
                        parcel2.writeInt(1);
                        complexDataE0.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    Reply replyB0 = b0(parcel.readInt() != 0 ? GetMsg.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (replyB0 != null) {
                        parcel2.writeInt(1);
                        replyB0.writeToParcel(parcel2, 1);
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
                    x(parcel.readInt() != 0 ? InvokeMsg.CREATOR.createFromParcel(parcel) : null);
                    return true;
                case 8:
                    parcel.enforceInterface("com.byd.mvcadapter.IRemoteModelService");
                    J(parcel.readInt() != 0 ? GCMsg.CREATOR.createFromParcel(parcel) : null);
                    return true;
                default:
                    return super.onTransact(i8, parcel, parcel2, i9);
            }
        }
    }

    Reply G(InvokeMsg invokeMsg) throws RemoteException;

    void J(GCMsg gCMsg) throws RemoteException;

    Reply b0(GetMsg getMsg) throws RemoteException;

    void c0(d dVar, String str, int i8, long j8) throws RemoteException;

    ComplexData e0(Map map, ComplexData complexData) throws RemoteException;

    void f0(d dVar) throws RemoteException;

    int r0() throws RemoteException;

    void x(InvokeMsg invokeMsg) throws RemoteException;
}
