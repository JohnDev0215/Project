package android.os;

import android.os.IServiceManager;

/* JADX INFO: compiled from: ServiceManagerNative.java */
/* JADX INFO: loaded from: classes2.dex */
class ServiceManagerProxy implements IServiceManager {
    private IBinder mRemote;
    private IServiceManager mServiceManager;

    public ServiceManagerProxy(IBinder iBinder) {
        this.mRemote = iBinder;
        this.mServiceManager = IServiceManager.Stub.asInterface(iBinder);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this.mRemote;
    }

    @Override // android.os.IServiceManager
    public IBinder getService(String str) throws RemoteException {
        return this.mServiceManager.checkService(str);
    }

    @Override // android.os.IServiceManager
    public IBinder checkService(String str) throws RemoteException {
        return this.mServiceManager.checkService(str);
    }

    @Override // android.os.IServiceManager
    public void addService(String str, IBinder iBinder, boolean z, int i) throws RemoteException {
        this.mServiceManager.addService(str, iBinder, z, i);
    }

    @Override // android.os.IServiceManager
    public String[] listServices(int i) throws RemoteException {
        return this.mServiceManager.listServices(i);
    }

    @Override // android.os.IServiceManager
    public void registerForNotifications(String str, IServiceCallback iServiceCallback) throws RemoteException {
        throw new RemoteException();
    }

    @Override // android.os.IServiceManager
    public void unregisterForNotifications(String str, IServiceCallback iServiceCallback) throws RemoteException {
        throw new RemoteException();
    }

    @Override // android.os.IServiceManager
    public boolean isDeclared(String str) throws RemoteException {
        return this.mServiceManager.isDeclared(str);
    }

    @Override // android.os.IServiceManager
    public void registerClientCallback(String str, IBinder iBinder, IClientCallback iClientCallback) throws RemoteException {
        throw new RemoteException();
    }

    @Override // android.os.IServiceManager
    public void tryUnregisterService(String str, IBinder iBinder) throws RemoteException {
        throw new RemoteException();
    }
}
