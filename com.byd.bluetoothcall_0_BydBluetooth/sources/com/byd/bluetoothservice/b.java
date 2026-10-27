package com.byd.bluetoothservice;

import android.bluetooth.BluetoothDevice;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.byd.bluetoothservice.a;

/* JADX INFO: compiled from: IBluetoothInterface.java */
/* JADX INFO: loaded from: classes.dex */
public interface b extends IInterface {

    /* JADX INFO: compiled from: IBluetoothInterface.java */
    public static abstract class a extends Binder implements b {
        public a() {
            attachInterface(this, "com.byd.bluetoothservice.IBluetoothInterface");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("com.byd.bluetoothservice.IBluetoothInterface");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    boolean zG = G();
                    parcel2.writeNoException();
                    parcel2.writeInt(zG ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    boolean zN = n();
                    parcel2.writeNoException();
                    parcel2.writeInt(zN ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    boolean zL = L();
                    parcel2.writeNoException();
                    parcel2.writeInt(zL ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    Z(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 5:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    String strW = w();
                    parcel2.writeNoException();
                    parcel2.writeString(strW);
                    return true;
                case 6:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    String strR = R();
                    parcel2.writeNoException();
                    parcel2.writeString(strR);
                    return true;
                case 7:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    BluetoothDevice bluetoothDeviceK = k();
                    parcel2.writeNoException();
                    if (bluetoothDeviceK != null) {
                        parcel2.writeInt(1);
                        bluetoothDeviceK.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 8:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    String strA = A();
                    parcel2.writeNoException();
                    parcel2.writeString(strA);
                    return true;
                case 9:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    BluetoothDevice bluetoothDeviceK2 = K();
                    parcel2.writeNoException();
                    if (bluetoothDeviceK2 != null) {
                        parcel2.writeInt(1);
                        bluetoothDeviceK2.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 10:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    int iW = W();
                    parcel2.writeNoException();
                    parcel2.writeInt(iW);
                    return true;
                case 11:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    int iP = P();
                    parcel2.writeNoException();
                    parcel2.writeInt(iP);
                    return true;
                case 12:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    String strE = E();
                    parcel2.writeNoException();
                    parcel2.writeString(strE);
                    return true;
                case 13:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    String strQ = q();
                    parcel2.writeNoException();
                    parcel2.writeString(strQ);
                    return true;
                case 14:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    String strO = O();
                    parcel2.writeNoException();
                    parcel2.writeString(strO);
                    return true;
                case 15:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    int iJ = J();
                    parcel2.writeNoException();
                    parcel2.writeInt(iJ);
                    return true;
                case 16:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    long jS = s();
                    parcel2.writeNoException();
                    parcel2.writeLong(jS);
                    return true;
                case 17:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    long j = j();
                    parcel2.writeNoException();
                    parcel2.writeLong(j);
                    return true;
                case 18:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    X();
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    D();
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    u();
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    Q();
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    m();
                    parcel2.writeNoException();
                    return true;
                case 23:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    WidgetCardShow(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    BT_Card_Slides_up();
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    int I = I();
                    parcel2.writeNoException();
                    parcel2.writeInt(I);
                    return true;
                case 26:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    o();
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    C(a.AbstractBinderC0092a.c0(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface("com.byd.bluetoothservice.IBluetoothInterface");
                    y(a.AbstractBinderC0092a.c0(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    String A() throws RemoteException;

    void BT_Card_Slides_up() throws RemoteException;

    void C(com.byd.bluetoothservice.a aVar) throws RemoteException;

    void D() throws RemoteException;

    String E() throws RemoteException;

    boolean G() throws RemoteException;

    int I() throws RemoteException;

    int J() throws RemoteException;

    BluetoothDevice K() throws RemoteException;

    boolean L() throws RemoteException;

    String O() throws RemoteException;

    int P() throws RemoteException;

    void Q() throws RemoteException;

    String R() throws RemoteException;

    int W() throws RemoteException;

    void WidgetCardShow(boolean z) throws RemoteException;

    void X() throws RemoteException;

    void Z(boolean z) throws RemoteException;

    long j() throws RemoteException;

    BluetoothDevice k() throws RemoteException;

    void m() throws RemoteException;

    boolean n() throws RemoteException;

    void o() throws RemoteException;

    String q() throws RemoteException;

    long s() throws RemoteException;

    void u() throws RemoteException;

    String w() throws RemoteException;

    void y(com.byd.bluetoothservice.a aVar) throws RemoteException;
}
