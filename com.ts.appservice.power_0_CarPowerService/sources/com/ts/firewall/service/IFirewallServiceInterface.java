package com.ts.firewall.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ts.firewall.service.IFirewallServiceListener;
import com.ts.firewall.service.IFirewallServiceResponse;
import com.ts.firewall.service.data.ApplicationRule;
import com.ts.firewall.service.data.FirewallData;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IFirewallServiceInterface extends IInterface {
    boolean addApplicationRule(int i, boolean z, List<ApplicationRule> list) throws RemoteException;

    boolean addIpRule(int i, boolean z, List<String> list, List<String> list2) throws RemoteException;

    boolean addPortRule(int i, boolean z, List<FirewallData> list, List<String> list2) throws RemoteException;

    boolean addUrlRule(int i, boolean z, List<String> list) throws RemoteException;

    List<String> getInterfaces() throws RemoteException;

    boolean getLogs(int i, long j, long j2) throws RemoteException;

    boolean getRules(int i, int i2) throws RemoteException;

    boolean isFirewallEnabled() throws RemoteException;

    boolean isWhiteListEnabled() throws RemoteException;

    boolean registerCallback(IFirewallServiceListener iFirewallServiceListener, IFirewallServiceResponse iFirewallServiceResponse) throws RemoteException;

    boolean removeApplicationRule(int i, boolean z, List<FirewallData> list, List<String> list2) throws RemoteException;

    boolean removeIpRule(int i, boolean z, List<String> list, List<String> list2) throws RemoteException;

    boolean removePortRule(int i, boolean z, List<FirewallData> list, List<String> list2) throws RemoteException;

    boolean removeUrlRule(int i, boolean z, List<String> list) throws RemoteException;

    boolean setFirewallEnable(boolean z) throws RemoteException;

    boolean setWhiteListEnable(boolean z) throws RemoteException;

    public static class Default implements IFirewallServiceInterface {
        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean registerCallback(IFirewallServiceListener listener, IFirewallServiceResponse response) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean setFirewallEnable(boolean enable) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean isFirewallEnabled() throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean setWhiteListEnable(boolean enable) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean isWhiteListEnabled() throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public List<String> getInterfaces() throws RemoteException {
            return null;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean getRules(int serial, int type) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean getLogs(int serial, long startTime, long endTime) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean addApplicationRule(int serial, boolean isWhiteList, List<ApplicationRule> uidRuls) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean removeApplicationRule(int serial, boolean isWhiteList, List<FirewallData> uids, List<String> packageNames) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean addIpRule(int serial, boolean isWhiteList, List<String> ipaddress, List<String> ifaces) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean removeIpRule(int serial, boolean isWhiteList, List<String> ipaddress, List<String> ifaces) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean addPortRule(int serial, boolean isWhiteList, List<FirewallData> port, List<String> iface) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean removePortRule(int serial, boolean isWhiteList, List<FirewallData> port, List<String> iface) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean addUrlRule(int serial, boolean isWhiteList, List<String> url) throws RemoteException {
            return false;
        }

        @Override // com.ts.firewall.service.IFirewallServiceInterface
        public boolean removeUrlRule(int serial, boolean isWhiteList, List<String> url) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IFirewallServiceInterface {
        private static final String DESCRIPTOR = "com.ts.firewall.service.IFirewallServiceInterface";
        static final int TRANSACTION_addApplicationRule = 9;
        static final int TRANSACTION_addIpRule = 11;
        static final int TRANSACTION_addPortRule = 13;
        static final int TRANSACTION_addUrlRule = 15;
        static final int TRANSACTION_getInterfaces = 6;
        static final int TRANSACTION_getLogs = 8;
        static final int TRANSACTION_getRules = 7;
        static final int TRANSACTION_isFirewallEnabled = 3;
        static final int TRANSACTION_isWhiteListEnabled = 5;
        static final int TRANSACTION_registerCallback = 1;
        static final int TRANSACTION_removeApplicationRule = 10;
        static final int TRANSACTION_removeIpRule = 12;
        static final int TRANSACTION_removePortRule = 14;
        static final int TRANSACTION_removeUrlRule = 16;
        static final int TRANSACTION_setFirewallEnable = 2;
        static final int TRANSACTION_setWhiteListEnable = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IFirewallServiceInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin != null && (iin instanceof IFirewallServiceInterface)) {
                return (IFirewallServiceInterface) iin;
            }
            return new Proxy(obj);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterCallback = registerCallback(IFirewallServiceListener.Stub.asInterface(parcel.readStrongBinder()), IFirewallServiceResponse.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterCallback ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean firewallEnable = setFirewallEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(firewallEnable ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsFirewallEnabled = isFirewallEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsFirewallEnabled ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean whiteListEnable = setWhiteListEnable(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    parcel2.writeInt(whiteListEnable ? 1 : 0);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsWhiteListEnabled = isWhiteListEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsWhiteListEnabled ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<String> interfaces = getInterfaces();
                    parcel2.writeNoException();
                    parcel2.writeStringList(interfaces);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean rules = getRules(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(rules ? 1 : 0);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean logs = getLogs(parcel.readInt(), parcel.readLong(), parcel.readLong());
                    parcel2.writeNoException();
                    parcel2.writeInt(logs ? 1 : 0);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddApplicationRule = addApplicationRule(parcel.readInt(), parcel.readInt() != 0, parcel.createTypedArrayList(ApplicationRule.CREATOR));
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddApplicationRule ? 1 : 0);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveApplicationRule = removeApplicationRule(parcel.readInt(), parcel.readInt() != 0, parcel.createTypedArrayList(FirewallData.CREATOR), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveApplicationRule ? 1 : 0);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddIpRule = addIpRule(parcel.readInt(), parcel.readInt() != 0, parcel.createStringArrayList(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddIpRule ? 1 : 0);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveIpRule = removeIpRule(parcel.readInt(), parcel.readInt() != 0, parcel.createStringArrayList(), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveIpRule ? 1 : 0);
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddPortRule = addPortRule(parcel.readInt(), parcel.readInt() != 0, parcel.createTypedArrayList(FirewallData.CREATOR), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddPortRule ? 1 : 0);
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemovePortRule = removePortRule(parcel.readInt(), parcel.readInt() != 0, parcel.createTypedArrayList(FirewallData.CREATOR), parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemovePortRule ? 1 : 0);
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zAddUrlRule = addUrlRule(parcel.readInt(), parcel.readInt() != 0, parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zAddUrlRule ? 1 : 0);
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRemoveUrlRule = removeUrlRule(parcel.readInt(), parcel.readInt() != 0, parcel.createStringArrayList());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRemoveUrlRule ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IFirewallServiceInterface {
            public static IFirewallServiceInterface sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean registerCallback(IFirewallServiceListener listener, IFirewallServiceResponse response) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(listener != null ? listener.asBinder() : null);
                    _data.writeStrongBinder(response != null ? response.asBinder() : null);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(listener, response);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean setFirewallEnable(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setFirewallEnable(enable);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean isFirewallEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isFirewallEnabled();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean setWhiteListEnable(boolean enable) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enable ? 1 : 0);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setWhiteListEnable(enable);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean isWhiteListEnabled() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWhiteListEnabled();
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public List<String> getInterfaces() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInterfaces();
                    }
                    _reply.readException();
                    List<String> _result = _reply.createStringArrayList();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean getRules(int serial, int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(type);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRules(serial, type);
                    }
                    _reply.readException();
                    boolean _status2 = _reply.readInt() != 0;
                    return _status2;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean getLogs(int serial, long startTime, long endTime) throws Throwable {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeInt(serial);
                    } catch (Throwable th) {
                        th = th;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeLong(startTime);
                        try {
                            _data.writeLong(endTime);
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                        if (!_status && Stub.getDefaultImpl() != null) {
                            boolean logs = Stub.getDefaultImpl().getLogs(serial, startTime, endTime);
                            _reply.recycle();
                            _data.recycle();
                            return logs;
                        }
                        _reply.readException();
                        boolean _status2 = _reply.readInt() != 0;
                        _reply.recycle();
                        _data.recycle();
                        return _status2;
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean addApplicationRule(int serial, boolean isWhiteList, List<ApplicationRule> uidRuls) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(isWhiteList ? 1 : 0);
                    _data.writeTypedList(uidRuls);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addApplicationRule(serial, isWhiteList, uidRuls);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean removeApplicationRule(int serial, boolean isWhiteList, List<FirewallData> uids, List<String> packageNames) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(isWhiteList ? 1 : 0);
                    _data.writeTypedList(uids);
                    _data.writeStringList(packageNames);
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeApplicationRule(serial, isWhiteList, uids, packageNames);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean addIpRule(int serial, boolean isWhiteList, List<String> ipaddress, List<String> ifaces) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(isWhiteList ? 1 : 0);
                    _data.writeStringList(ipaddress);
                    _data.writeStringList(ifaces);
                    boolean _status = this.mRemote.transact(11, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addIpRule(serial, isWhiteList, ipaddress, ifaces);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean removeIpRule(int serial, boolean isWhiteList, List<String> ipaddress, List<String> ifaces) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(isWhiteList ? 1 : 0);
                    _data.writeStringList(ipaddress);
                    _data.writeStringList(ifaces);
                    boolean _status = this.mRemote.transact(12, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeIpRule(serial, isWhiteList, ipaddress, ifaces);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean addPortRule(int serial, boolean isWhiteList, List<FirewallData> port, List<String> iface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(isWhiteList ? 1 : 0);
                    _data.writeTypedList(port);
                    _data.writeStringList(iface);
                    boolean _status = this.mRemote.transact(13, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addPortRule(serial, isWhiteList, port, iface);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean removePortRule(int serial, boolean isWhiteList, List<FirewallData> port, List<String> iface) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(isWhiteList ? 1 : 0);
                    _data.writeTypedList(port);
                    _data.writeStringList(iface);
                    boolean _status = this.mRemote.transact(14, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removePortRule(serial, isWhiteList, port, iface);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean addUrlRule(int serial, boolean isWhiteList, List<String> url) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(isWhiteList ? 1 : 0);
                    _data.writeStringList(url);
                    boolean _status = this.mRemote.transact(15, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addUrlRule(serial, isWhiteList, url);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.ts.firewall.service.IFirewallServiceInterface
            public boolean removeUrlRule(int serial, boolean isWhiteList, List<String> url) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(serial);
                    _data.writeInt(isWhiteList ? 1 : 0);
                    _data.writeStringList(url);
                    boolean _status = this.mRemote.transact(16, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeUrlRule(serial, isWhiteList, url);
                    }
                    _reply.readException();
                    boolean _result = _reply.readInt() != 0;
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IFirewallServiceInterface impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (impl != null) {
                Proxy.sDefaultImpl = impl;
                return true;
            }
            return false;
        }

        public static IFirewallServiceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
