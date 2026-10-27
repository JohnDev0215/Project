package com.android.networkstack.tethering;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.IIntResultListener;
import android.net.INetworkStackConnector;
import android.net.ITetheringConnector;
import android.net.ITetheringEventCallback;
import android.net.NetworkRequest;
import android.net.NetworkStack;
import android.net.TetheringRequestParcel;
import android.net.dhcp.DhcpServerCallbacks;
import android.net.dhcp.DhcpServingParamsParcel;
import android.net.ip.IpServer;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class TetheringService extends Service {
    private static final String TAG = TetheringService.class.getSimpleName();
    private TetheringConnector mConnector;

    @Override // android.app.Service
    public void onCreate() {
        this.mConnector = new TetheringConnector(makeTethering(makeTetheringDependencies()), this);
    }

    public Tethering makeTethering(TetheringDependencies tetheringDependencies) {
        System.loadLibrary("tetherutilsjni");
        return new Tethering(tetheringDependencies);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mConnector;
    }

    private static class TetheringConnector extends ITetheringConnector.Stub {
        private final TetheringService mService;
        private final Tethering mTethering;

        TetheringConnector(Tethering tethering, TetheringService tetheringService) {
            this.mTethering = tethering;
            this.mService = tetheringService;
        }

        public void tether(String str, String str2, IIntResultListener iIntResultListener) {
            if (checkAndNotifyCommonError(str2, iIntResultListener)) {
                return;
            }
            try {
                iIntResultListener.onResult(this.mTethering.tether(str));
            } catch (RemoteException unused) {
            }
        }

        public void untether(String str, String str2, IIntResultListener iIntResultListener) {
            if (checkAndNotifyCommonError(str2, iIntResultListener)) {
                return;
            }
            try {
                iIntResultListener.onResult(this.mTethering.untether(str));
            } catch (RemoteException unused) {
            }
        }

        public void setUsbTethering(boolean z, String str, IIntResultListener iIntResultListener) {
            if (checkAndNotifyCommonError(str, iIntResultListener)) {
                return;
            }
            try {
                iIntResultListener.onResult(this.mTethering.setUsbTethering(z));
            } catch (RemoteException unused) {
            }
        }

        public void startTethering(TetheringRequestParcel tetheringRequestParcel, String str, IIntResultListener iIntResultListener) {
            if (checkAndNotifyCommonError(str, tetheringRequestParcel.exemptFromEntitlementCheck, iIntResultListener)) {
                return;
            }
            this.mTethering.startTethering(tetheringRequestParcel, iIntResultListener);
        }

        public void stopTethering(int i, String str, IIntResultListener iIntResultListener) {
            if (checkAndNotifyCommonError(str, iIntResultListener)) {
                return;
            }
            try {
                this.mTethering.stopTethering(i);
                iIntResultListener.onResult(0);
            } catch (RemoteException unused) {
            }
        }

        public void requestLatestTetheringEntitlementResult(int i, ResultReceiver resultReceiver, boolean z, String str) {
            if (checkAndNotifyCommonError(str, resultReceiver)) {
                return;
            }
            this.mTethering.requestLatestTetheringEntitlementResult(i, resultReceiver, z);
        }

        public void registerTetheringEventCallback(ITetheringEventCallback iTetheringEventCallback, String str) {
            try {
                if (!hasTetherAccessPermission()) {
                    iTetheringEventCallback.onCallbackStopped(15);
                } else {
                    this.mTethering.registerTetheringEventCallback(iTetheringEventCallback);
                }
            } catch (RemoteException unused) {
            }
        }

        public void unregisterTetheringEventCallback(ITetheringEventCallback iTetheringEventCallback, String str) {
            try {
                if (!hasTetherAccessPermission()) {
                    iTetheringEventCallback.onCallbackStopped(15);
                } else {
                    this.mTethering.unregisterTetheringEventCallback(iTetheringEventCallback);
                }
            } catch (RemoteException unused) {
            }
        }

        public void stopAllTethering(String str, IIntResultListener iIntResultListener) {
            if (checkAndNotifyCommonError(str, iIntResultListener)) {
                return;
            }
            try {
                this.mTethering.untetherAll();
                iIntResultListener.onResult(0);
            } catch (RemoteException unused) {
            }
        }

        public void isTetheringSupported(String str, IIntResultListener iIntResultListener) {
            if (checkAndNotifyCommonError(str, iIntResultListener)) {
                return;
            }
            try {
                iIntResultListener.onResult(0);
            } catch (RemoteException unused) {
            }
        }

        protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            this.mTethering.dump(fileDescriptor, printWriter, strArr);
        }

        private boolean checkAndNotifyCommonError(String str, IIntResultListener iIntResultListener) {
            return checkAndNotifyCommonError(str, false, iIntResultListener);
        }

        private boolean checkAndNotifyCommonError(String str, boolean z, IIntResultListener iIntResultListener) {
            try {
                if (!hasTetherChangePermission(str, z)) {
                    iIntResultListener.onResult(14);
                    return true;
                }
                if (this.mTethering.isTetheringSupported()) {
                    return false;
                }
                iIntResultListener.onResult(3);
                return true;
            } catch (RemoteException unused) {
                return true;
            }
        }

        private boolean checkAndNotifyCommonError(String str, ResultReceiver resultReceiver) {
            if (!hasTetherChangePermission(str, false)) {
                resultReceiver.send(14, null);
                return true;
            }
            if (this.mTethering.isTetheringSupported()) {
                return false;
            }
            resultReceiver.send(3, null);
            return true;
        }

        private boolean hasNetworkStackPermission() {
            return checkCallingOrSelfPermission("android.permission.NETWORK_STACK") || checkCallingOrSelfPermission("android.permission.MAINLINE_NETWORK_STACK");
        }

        private boolean hasTetherPrivilegedPermission() {
            return checkCallingOrSelfPermission("android.permission.TETHER_PRIVILEGED");
        }

        private boolean checkCallingOrSelfPermission(String str) {
            return this.mService.checkCallingOrSelfPermission(str) == 0;
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        private boolean hasTetherChangePermission(String str, boolean z) {
            if (z && !hasNetworkStackPermission()) {
                return false;
            }
            if (hasTetherPrivilegedPermission()) {
                return true;
            }
            if (this.mTethering.isTetherProvisioningRequired()) {
                return false;
            }
            int callingUid = Binder.getCallingUid();
            TetheringService tetheringService = this.mService;
            return tetheringService.checkAndNoteWriteSettingsOperation(tetheringService, callingUid, str, false);
        }

        private boolean hasTetherAccessPermission() {
            return hasTetherPrivilegedPermission() || this.mService.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0;
        }
    }

    boolean checkAndNoteWriteSettingsOperation(Context context, int i, String str, boolean z) {
        return Settings.checkAndNoteWriteSettingsOperation(context, i, str, z);
    }

    public TetheringDependencies makeTetheringDependencies() {
        return new TetheringDependencies() { // from class: com.android.networkstack.tethering.TetheringService.1
            @Override // com.android.networkstack.tethering.TetheringDependencies
            public NetworkRequest getDefaultNetworkRequest() {
                return new NetworkRequest.Builder().clearCapabilities().addCapability(13).addCapability(14).addCapability(15).addCapability(12).build();
            }

            @Override // com.android.networkstack.tethering.TetheringDependencies
            public Looper getTetheringLooper() {
                HandlerThread handlerThread = new HandlerThread("android.tethering");
                handlerThread.start();
                return handlerThread.getLooper();
            }

            @Override // com.android.networkstack.tethering.TetheringDependencies
            public Context getContext() {
                return TetheringService.this;
            }

            @Override // com.android.networkstack.tethering.TetheringDependencies
            public IpServer.Dependencies getIpServerDependencies() {
                return new IpServer.Dependencies() { // from class: com.android.networkstack.tethering.TetheringService.1.1
                    @Override // android.net.ip.IpServer.Dependencies
                    public void makeDhcpServer(String str, DhcpServingParamsParcel dhcpServingParamsParcel, DhcpServerCallbacks dhcpServerCallbacks) {
                        try {
                            INetworkStackConnector networkStackConnector = getNetworkStackConnector();
                            if (networkStackConnector == null) {
                                return;
                            }
                            networkStackConnector.makeDhcpServer(str, dhcpServingParamsParcel, dhcpServerCallbacks);
                        } catch (RemoteException unused) {
                            Log.e(TetheringService.TAG, "Fail to make dhcp server");
                            try {
                                dhcpServerCallbacks.onDhcpServerCreated(3, null);
                            } catch (RemoteException unused2) {
                            }
                        }
                    }
                };
            }

            /* JADX INFO: Access modifiers changed from: private */
            public INetworkStackConnector getNetworkStackConnector() {
                try {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    while (true) {
                        IBinder service = NetworkStack.getService();
                        if (service != null) {
                            return INetworkStackConnector.Stub.asInterface(service);
                        }
                        if (System.currentTimeMillis() - jCurrentTimeMillis > 60000) {
                            Log.wtf(TetheringService.TAG, "Timeout, fail to get INetworkStackConnector");
                            return null;
                        }
                        Thread.sleep(200L);
                    }
                } catch (InterruptedException unused) {
                    Log.wtf(TetheringService.TAG, "Interrupted, fail to get INetworkStackConnector");
                    return null;
                }
            }

            @Override // com.android.networkstack.tethering.TetheringDependencies
            public BluetoothAdapter getBluetoothAdapter() {
                return BluetoothAdapter.getDefaultAdapter();
            }
        };
    }
}
