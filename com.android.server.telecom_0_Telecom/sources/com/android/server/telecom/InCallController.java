package com.android.server.telecom;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.os.Trace;
import android.os.UserHandle;
import android.telecom.CallAudioState;
import android.telecom.Log;
import android.telecom.Logging.EventManager;
import android.telecom.Logging.Runnable;
import android.telecom.ParcelableCall;
import android.text.TextUtils;
import android.util.ArrayMap;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telecom.IInCallService;
import com.android.internal.util.IndentingPrintWriter;
import com.android.server.telecom.Call;
import com.android.server.telecom.CallIdMapper;
import com.android.server.telecom.InCallController;
import com.android.server.telecom.SystemStateHelper;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.Timeouts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class InCallController extends CallsManagerListenerBase {
    public static final String NOTIFICATION_TAG = "InCallController";
    private final AppOpsManager mAppOpsManager;
    private final CallsManager mCallsManager;
    private final CarModeTracker mCarModeTracker;
    private final ClockProxy mClockProxy;
    private final Context mContext;
    private final DefaultDialerCache mDefaultDialerCache;
    private final EmergencyCallHelper mEmergencyCallHelper;
    private CarSwappingInCallServiceConnection mInCallServiceConnection;
    private final TelecomSystem.SyncRoot mLock;
    private NonUIInCallServiceConnectionCollection mNonUIInCallServiceConnections;
    private final SystemStateHelper mSystemStateHelper;
    private final Timeouts.Adapter mTimeoutsAdapter;
    private final Call.Listener mCallListener = new Call.ListenerBase() { // from class: com.android.server.telecom.InCallController.1
        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onConnectionCapabilitiesChanged(Call call) {
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onConnectionPropertiesChanged(Call call, boolean z) {
            InCallController.this.updateCall(call, false, z);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onCannedSmsResponsesLoaded(Call call) {
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onVideoCallProviderChanged(Call call) {
            InCallController.this.updateCall(call, true, false);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onStatusHintsChanged(Call call) {
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onExtrasChanged(Call call, int i, Bundle bundle) {
            if (i == 2) {
                return;
            }
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onExtrasRemoved(Call call, int i, List<String> list) {
            if (i == 2) {
                return;
            }
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onHandleChanged(Call call) {
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onCallerDisplayNameChanged(Call call) {
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onCallDirectionChanged(Call call) {
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onVideoStateChanged(Call call, int i, int i2) {
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onTargetPhoneAccountChanged(Call call) {
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onConferenceableCallsChanged(Call call) {
            InCallController.this.updateCall(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onConnectionEvent(Call call, String str, Bundle bundle) {
            InCallController.this.notifyConnectionEvent(call, str, bundle);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onHandoverFailed(Call call, int i) {
            InCallController.this.notifyHandoverFailed(call, i);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onHandoverComplete(Call call) {
            InCallController.this.notifyHandoverComplete(call);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onRttInitiationFailure(Call call, int i) {
            InCallController.this.notifyRttInitiationFailure(call, i);
            InCallController.this.updateCall(call, false, true);
        }

        @Override // com.android.server.telecom.Call.ListenerBase, com.android.server.telecom.Call.Listener
        public void onRemoteRttRequest(Call call, int i) {
            InCallController.this.notifyRemoteRttRequest(call, i);
        }
    };
    private final SystemStateHelper.SystemStateListener mSystemStateListener = new SystemStateHelper.SystemStateListener() { // from class: com.android.server.telecom.-$$Lambda$InCallController$7MIWK1QfVtbLqD7L7AoDsDyq2WQ
        @Override // com.android.server.telecom.SystemStateHelper.SystemStateListener
        public final void onCarModeChanged(int i, String str, boolean z) {
            this.f$0.lambda$new$0$InCallController(i, str, z);
        }
    };
    private final Map<InCallServiceInfo, IInCallService> mInCallServices = new ArrayMap();
    private final CallIdMapper mCallIdMapper = new CallIdMapper(new CallIdMapper.ICallInfo() { // from class: com.android.server.telecom.-$$Lambda$mWsx8FxBq7LKakxUJd_28wdgfas
        @Override // com.android.server.telecom.CallIdMapper.ICallInfo
        public final String getCallId(Call call) {
            return call.getId();
        }
    });
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private CompletableFuture<Boolean> mBindingFuture = CompletableFuture.completedFuture(Boolean.TRUE);
    private String mCurrentUserInterfacePackageName = null;
    private boolean mIsCallUsingMicrophone = false;

    public class InCallServiceConnection {
        public Call mCall;
        protected Listener mListener;

        public abstract int connect(Call call);

        public abstract void disconnect();

        public abstract void dump(IndentingPrintWriter indentingPrintWriter);

        public InCallServiceInfo getInfo() {
            return null;
        }

        public void setHasEmergency(boolean z) {
        }

        public InCallServiceConnection(InCallController inCallController) {
        }

        public class Listener {
            public abstract void onDisconnect(InCallServiceConnection inCallServiceConnection, Call call);

            public Listener(InCallServiceConnection inCallServiceConnection) {
            }
        }

        public void setListener(Listener listener) {
            this.mListener = listener;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class InCallServiceInfo {
        private long mBindingStartTime;
        private final ComponentName mComponentName;
        private long mDisconnectTime;
        private boolean mIsExternalCallsSupported;
        private boolean mIsSelfManagedCallsSupported;
        private final int mType;

        public InCallServiceInfo(InCallController inCallController, ComponentName componentName, boolean z, boolean z2, int i) {
            this.mComponentName = componentName;
            this.mIsExternalCallsSupported = z;
            this.mIsSelfManagedCallsSupported = z2;
            this.mType = i;
        }

        public ComponentName getComponentName() {
            return this.mComponentName;
        }

        public boolean isExternalCallsSupported() {
            return this.mIsExternalCallsSupported;
        }

        public boolean isSelfManagedCallsSupported() {
            return this.mIsSelfManagedCallsSupported;
        }

        public int getType() {
            return this.mType;
        }

        public long getBindingStartTime() {
            return this.mBindingStartTime;
        }

        public long getDisconnectTime() {
            return this.mDisconnectTime;
        }

        public void setBindingStartTime(long j) {
            this.mBindingStartTime = j;
        }

        public void setDisconnectTime(long j) {
            this.mDisconnectTime = j;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || InCallServiceInfo.class != obj.getClass()) {
                return false;
            }
            InCallServiceInfo inCallServiceInfo = (InCallServiceInfo) obj;
            if (this.mIsExternalCallsSupported == inCallServiceInfo.mIsExternalCallsSupported && this.mIsSelfManagedCallsSupported == inCallServiceInfo.mIsSelfManagedCallsSupported) {
                return this.mComponentName.equals(inCallServiceInfo.mComponentName);
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(this.mComponentName, Boolean.valueOf(this.mIsExternalCallsSupported), Boolean.valueOf(this.mIsSelfManagedCallsSupported));
        }

        public String toString() {
            return "[" + this.mComponentName + " supportsExternal? " + this.mIsExternalCallsSupported + " supportsSelfMg?" + this.mIsSelfManagedCallsSupported + "]";
        }
    }

    private class InCallServiceBindingConnection extends InCallServiceConnection {
        private final InCallServiceInfo mInCallServiceInfo;
        private boolean mIsBound;
        private boolean mIsConnected;
        private boolean mIsNullBinding;
        private final ServiceConnection mServiceConnection;

        public InCallServiceBindingConnection(InCallServiceInfo inCallServiceInfo) {
            super(InCallController.this);
            this.mServiceConnection = new ServiceConnection() { // from class: com.android.server.telecom.InCallController.InCallServiceBindingConnection.1
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    Log.startSession("ICSBC.oSC", Log.getPackageAbbreviation(componentName));
                    synchronized (InCallController.this.mLock) {
                        try {
                            Log.d(this, "onServiceConnected: %s %b %b", new Object[]{componentName, Boolean.valueOf(InCallServiceBindingConnection.this.mIsBound), Boolean.valueOf(InCallServiceBindingConnection.this.mIsConnected)});
                            InCallServiceBindingConnection.this.mIsBound = true;
                            if (InCallServiceBindingConnection.this.mIsConnected) {
                                InCallServiceBindingConnection.this.onConnected(iBinder);
                            }
                        } finally {
                            Log.endSession();
                        }
                    }
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    Log.startSession("ICSBC.oSD", Log.getPackageAbbreviation(componentName));
                    synchronized (InCallController.this.mLock) {
                        try {
                            Log.d(this, "onServiceDisconnected: %s", new Object[]{componentName});
                            InCallServiceBindingConnection.this.mIsBound = false;
                            InCallServiceBindingConnection.this.onDisconnected();
                        } finally {
                            Log.endSession();
                        }
                    }
                }

                @Override // android.content.ServiceConnection
                public void onNullBinding(ComponentName componentName) {
                    Log.startSession("ICSBC.oNB", Log.getPackageAbbreviation(componentName));
                    synchronized (InCallController.this.mLock) {
                        try {
                            Log.d(this, "onNullBinding: %s", new Object[]{componentName});
                            InCallServiceBindingConnection.this.mIsNullBinding = true;
                            InCallServiceBindingConnection.this.mIsBound = false;
                            InCallServiceBindingConnection.this.onDisconnected();
                        } finally {
                            Log.endSession();
                        }
                    }
                }

                @Override // android.content.ServiceConnection
                public void onBindingDied(ComponentName componentName) {
                    Log.startSession("ICSBC.oBD", Log.getPackageAbbreviation(componentName));
                    synchronized (InCallController.this.mLock) {
                        try {
                            Log.d(this, "onBindingDied: %s", new Object[]{componentName});
                            InCallServiceBindingConnection.this.mIsBound = false;
                            InCallServiceBindingConnection.this.onDisconnected();
                        } finally {
                            Log.endSession();
                        }
                    }
                }
            };
            this.mIsConnected = false;
            this.mIsBound = false;
            this.mIsNullBinding = false;
            this.mInCallServiceInfo = inCallServiceInfo;
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public int connect(Call call) {
            if (this.mIsConnected) {
                Log.addEvent(call, "INFO", "Already connected, ignoring request.");
                return 1;
            }
            if (call != null && call.isSelfManaged() && !this.mInCallServiceInfo.isSelfManagedCallsSupported()) {
                Log.i(this, "Skipping binding to %s - doesn't support self-mgd calls", new Object[]{this.mInCallServiceInfo});
                this.mIsConnected = false;
                return 3;
            }
            Intent intent = new Intent("android.telecom.InCallService");
            intent.setComponent(this.mInCallServiceInfo.getComponentName());
            if (call != null && !call.isIncoming() && !call.isExternalCall()) {
                intent.putExtra("android.telecom.extra.OUTGOING_CALL_EXTRAS", call.getIntentExtras());
                intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", call.getTargetPhoneAccount());
            }
            Log.i(this, "Attempting to bind to InCall %s, with %s", new Object[]{this.mInCallServiceInfo, intent});
            this.mIsConnected = true;
            this.mInCallServiceInfo.setBindingStartTime(InCallController.this.mClockProxy.elapsedRealtime());
            if (!InCallController.this.mContext.bindServiceAsUser(intent, this.mServiceConnection, 68157441, UserHandle.CURRENT)) {
                Log.w(this, "Failed to connect.", new Object[0]);
                this.mIsConnected = false;
            }
            if (this.mIsConnected && call != null) {
                this.mCall = call;
            }
            Log.i(this, "mCall: %s, mIsConnected: %s", new Object[]{this.mCall, Boolean.valueOf(this.mIsConnected)});
            return this.mIsConnected ? 1 : 2;
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public InCallServiceInfo getInfo() {
            return this.mInCallServiceInfo;
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public void disconnect() {
            if (this.mIsConnected) {
                this.mInCallServiceInfo.setDisconnectTime(InCallController.this.mClockProxy.elapsedRealtime());
                Log.i(InCallController.this, "ICSBC#disconnect: unbinding after %s ms;%s. isCrashed: %s", new Object[]{Long.valueOf(this.mInCallServiceInfo.mDisconnectTime - this.mInCallServiceInfo.mBindingStartTime), this.mInCallServiceInfo, Boolean.valueOf(this.mIsNullBinding)});
                String packageName = this.mInCallServiceInfo.getComponentName().getPackageName();
                InCallController.this.mContext.unbindService(this.mServiceConnection);
                this.mIsConnected = false;
                if (this.mIsNullBinding && this.mInCallServiceInfo.getType() != 4) {
                    InCallController.this.sendCrashedInCallServiceNotification(packageName);
                }
                Call call = this.mCall;
                if (call != null) {
                    call.getAnalytics().addInCallService(this.mInCallServiceInfo.getComponentName().flattenToShortString(), this.mInCallServiceInfo.getType(), this.mInCallServiceInfo.getDisconnectTime() - this.mInCallServiceInfo.getBindingStartTime(), this.mIsNullBinding);
                }
                InCallController.this.onDisconnected(this.mInCallServiceInfo);
                return;
            }
            Log.i(InCallController.this, "ICSBC#disconnect: already disconnected; %s", new Object[]{this.mInCallServiceInfo});
            Log.addEvent((EventManager.Loggable) null, "INFO", "Already disconnected, ignoring request.");
        }

        public boolean isConnected() {
            return this.mIsConnected;
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public void dump(IndentingPrintWriter indentingPrintWriter) {
            indentingPrintWriter.print("BindingConnection [");
            indentingPrintWriter.print(this.mIsConnected ? "" : "not ");
            indentingPrintWriter.print("connected, ");
            indentingPrintWriter.print(this.mIsBound ? "" : "not ");
            indentingPrintWriter.print("bound, ");
            indentingPrintWriter.print(this.mInCallServiceInfo);
            indentingPrintWriter.println("\n");
        }

        protected void onConnected(IBinder iBinder) {
            if (InCallController.this.onConnected(this.mInCallServiceInfo, iBinder)) {
                return;
            }
            disconnect();
        }

        protected void onDisconnected() {
            InCallController.this.onDisconnected(this.mInCallServiceInfo);
            disconnect();
            InCallServiceConnection.Listener listener = this.mListener;
            if (listener != null) {
                listener.onDisconnect(this, this.mCall);
            }
        }
    }

    private class EmergencyInCallServiceConnection extends InCallServiceBindingConnection {
        private boolean mIsConnected;
        private boolean mIsProxying;
        private final InCallServiceConnection mSubConnection;
        private InCallServiceConnection.Listener mSubListener;

        public EmergencyInCallServiceConnection(InCallServiceInfo inCallServiceInfo, InCallServiceConnection inCallServiceConnection) {
            super(inCallServiceInfo);
            this.mIsProxying = true;
            this.mIsConnected = false;
            InCallServiceConnection.Listener listener = new InCallServiceConnection.Listener() { // from class: com.android.server.telecom.InCallController.EmergencyInCallServiceConnection.1
                @Override // com.android.server.telecom.InCallController.InCallServiceConnection.Listener
                public void onDisconnect(InCallServiceConnection inCallServiceConnection2, Call call) {
                    if (inCallServiceConnection2 == EmergencyInCallServiceConnection.this.mSubConnection && EmergencyInCallServiceConnection.this.mIsConnected && EmergencyInCallServiceConnection.this.mIsProxying) {
                        EmergencyInCallServiceConnection.this.mIsProxying = false;
                        EmergencyInCallServiceConnection.this.connect(call);
                    }
                }
            };
            this.mSubListener = listener;
            this.mSubConnection = inCallServiceConnection;
            if (inCallServiceConnection != null) {
                inCallServiceConnection.setListener(listener);
            }
            this.mIsProxying = this.mSubConnection != null;
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceBindingConnection, com.android.server.telecom.InCallController.InCallServiceConnection
        public int connect(Call call) {
            this.mIsConnected = true;
            if (this.mIsProxying) {
                int iConnect = this.mSubConnection.connect(call);
                this.mIsConnected = iConnect == 1;
                if (iConnect != 2) {
                    return iConnect;
                }
                this.mIsProxying = false;
            }
            InCallController.this.mEmergencyCallHelper.maybeGrantTemporaryLocationPermission(call, InCallController.this.mCallsManager.getCurrentUserHandle());
            if (call != null && call.isIncoming() && InCallController.this.mEmergencyCallHelper.getLastEmergencyCallTimeMillis() > 0) {
                Bundle bundle = new Bundle();
                bundle.putLong("android.telecom.extra.LAST_EMERGENCY_CALLBACK_TIME_MILLIS", InCallController.this.mEmergencyCallHelper.getLastEmergencyCallTimeMillis());
                call.putExtras(1, bundle);
            }
            return super.connect(call);
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceBindingConnection, com.android.server.telecom.InCallController.InCallServiceConnection
        public void disconnect() {
            Log.i(this, "Disconnect forced!", new Object[0]);
            if (this.mIsProxying) {
                this.mSubConnection.disconnect();
            } else {
                super.disconnect();
                InCallController.this.mEmergencyCallHelper.maybeRevokeTemporaryLocationPermission();
            }
            this.mIsConnected = false;
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public void setHasEmergency(boolean z) {
            if (z) {
                takeControl();
            }
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceBindingConnection, com.android.server.telecom.InCallController.InCallServiceConnection
        public InCallServiceInfo getInfo() {
            if (this.mIsProxying) {
                return this.mSubConnection.getInfo();
            }
            return super.getInfo();
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceBindingConnection
        protected void onDisconnected() {
            boolean z = this.mIsConnected;
            super.onDisconnected();
            if (!z || this.mIsProxying) {
                return;
            }
            connect(this.mCall);
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceBindingConnection, com.android.server.telecom.InCallController.InCallServiceConnection
        public void dump(IndentingPrintWriter indentingPrintWriter) {
            indentingPrintWriter.print("Emergency ICS Connection [");
            indentingPrintWriter.append(this.mIsProxying ? "" : "not ").append((CharSequence) "proxying, ");
            indentingPrintWriter.append(this.mIsConnected ? "" : "not ").append((CharSequence) "connected]\n");
            indentingPrintWriter.increaseIndent();
            indentingPrintWriter.print("Emergency: ");
            super.dump(indentingPrintWriter);
            if (this.mSubConnection != null) {
                indentingPrintWriter.print("Default-Dialer: ");
                this.mSubConnection.dump(indentingPrintWriter);
            }
            indentingPrintWriter.decreaseIndent();
        }

        private void takeControl() {
            if (this.mIsProxying) {
                this.mIsProxying = false;
                if (this.mIsConnected) {
                    this.mSubConnection.disconnect();
                    super.connect(null);
                }
            }
        }
    }

    private class CarSwappingInCallServiceConnection extends InCallServiceConnection {
        private InCallServiceConnection mCarModeConnection;
        private InCallServiceConnection mCurrentConnection;
        private final InCallServiceConnection mDialerConnection;
        private boolean mIsCarMode;
        private boolean mIsConnected;

        public CarSwappingInCallServiceConnection(InCallServiceConnection inCallServiceConnection, InCallServiceConnection inCallServiceConnection2) {
            super(InCallController.this);
            this.mIsCarMode = false;
            this.mIsConnected = false;
            this.mDialerConnection = inCallServiceConnection;
            this.mCarModeConnection = inCallServiceConnection2;
            this.mCurrentConnection = getCurrentConnection();
        }

        public synchronized void chooseInitialInCallService(boolean z) {
            Log.i(this, "chooseInitialInCallService: " + this.mIsCarMode + " => " + z, new Object[0]);
            if (z != this.mIsCarMode) {
                this.mIsCarMode = z;
                InCallServiceConnection currentConnection = getCurrentConnection();
                if (currentConnection != this.mCurrentConnection) {
                    if (this.mIsConnected) {
                        this.mCurrentConnection.disconnect();
                    }
                    this.mIsConnected = currentConnection.connect(null) == 1;
                    this.mCurrentConnection = currentConnection;
                }
            }
        }

        public synchronized void disableCarMode() {
            this.mIsCarMode = false;
            if (this.mIsConnected) {
                this.mCurrentConnection.disconnect();
            }
            this.mCurrentConnection = this.mDialerConnection;
            this.mIsConnected = this.mDialerConnection.connect(null) == 1;
        }

        public synchronized void changeCarModeApp(String str) {
            Log.i(this, "changeCarModeApp: isCarModeNow=" + this.mIsCarMode, new Object[0]);
            InCallServiceInfo info = this.mCurrentConnection == null ? null : this.mCurrentConnection.getInfo();
            InCallServiceInfo inCallServiceComponent = InCallController.this.getInCallServiceComponent(str, 3);
            if (!Objects.equals(info, inCallServiceComponent)) {
                Log.i(this, "changeCarModeApp: " + info + " => " + inCallServiceComponent, new Object[0]);
                if (this.mIsConnected) {
                    this.mCurrentConnection.disconnect();
                }
                if (inCallServiceComponent != null) {
                    InCallServiceBindingConnection inCallServiceBindingConnection = InCallController.this.new InCallServiceBindingConnection(inCallServiceComponent);
                    this.mCurrentConnection = inCallServiceBindingConnection;
                    this.mCarModeConnection = inCallServiceBindingConnection;
                    this.mIsCarMode = true;
                } else {
                    this.mCarModeConnection = null;
                    this.mIsCarMode = false;
                    this.mCurrentConnection = this.mDialerConnection;
                }
                this.mIsConnected = this.mCurrentConnection.connect(null) == 1;
            } else {
                Log.i(this, "changeCarModeApp: unchanged; " + info + " => " + inCallServiceComponent, new Object[0]);
            }
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public int connect(Call call) {
            if (this.mIsConnected) {
                Log.i(this, "already connected", new Object[0]);
                return 1;
            }
            int iConnect = this.mCurrentConnection.connect(call);
            if (iConnect == 2) {
                return 2;
            }
            this.mIsConnected = iConnect == 1;
            return iConnect;
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public void disconnect() {
            if (this.mIsConnected) {
                Log.i(InCallController.this, "CSICSC: disconnect %s", new Object[]{this.mCurrentConnection});
                this.mCurrentConnection.disconnect();
                this.mIsConnected = false;
                return;
            }
            Log.i(this, "already disconnected", new Object[0]);
        }

        public boolean isConnected() {
            return this.mIsConnected;
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public void setHasEmergency(boolean z) {
            InCallServiceConnection inCallServiceConnection = this.mDialerConnection;
            if (inCallServiceConnection != null) {
                inCallServiceConnection.setHasEmergency(z);
            }
            InCallServiceConnection inCallServiceConnection2 = this.mCarModeConnection;
            if (inCallServiceConnection2 != null) {
                inCallServiceConnection2.setHasEmergency(z);
            }
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public InCallServiceInfo getInfo() {
            return this.mCurrentConnection.getInfo();
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public void dump(IndentingPrintWriter indentingPrintWriter) {
            indentingPrintWriter.print("Car Swapping ICS [");
            indentingPrintWriter.append(this.mIsConnected ? "" : "not ").append((CharSequence) "connected]\n");
            indentingPrintWriter.increaseIndent();
            if (this.mDialerConnection != null) {
                indentingPrintWriter.print("Dialer: ");
                this.mDialerConnection.dump(indentingPrintWriter);
            }
            if (this.mCarModeConnection != null) {
                indentingPrintWriter.print("Car Mode: ");
                this.mCarModeConnection.dump(indentingPrintWriter);
            }
        }

        private InCallServiceConnection getCurrentConnection() {
            InCallServiceConnection inCallServiceConnection;
            return (!this.mIsCarMode || (inCallServiceConnection = this.mCarModeConnection) == null) ? this.mDialerConnection : inCallServiceConnection;
        }
    }

    private class NonUIInCallServiceConnectionCollection extends InCallServiceConnection {
        private final List<InCallServiceBindingConnection> mSubConnections;

        public NonUIInCallServiceConnectionCollection(InCallController inCallController, List<InCallServiceBindingConnection> list) {
            super(inCallController);
            this.mSubConnections = list;
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public int connect(Call call) {
            Iterator<InCallServiceBindingConnection> it = this.mSubConnections.iterator();
            while (it.hasNext()) {
                it.next().connect(call);
            }
            return 1;
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public void disconnect() {
            for (InCallServiceBindingConnection inCallServiceBindingConnection : this.mSubConnections) {
                if (inCallServiceBindingConnection.isConnected()) {
                    inCallServiceBindingConnection.disconnect();
                }
            }
        }

        @Override // com.android.server.telecom.InCallController.InCallServiceConnection
        public void dump(IndentingPrintWriter indentingPrintWriter) {
            indentingPrintWriter.println("Non-UI Connections:");
            indentingPrintWriter.increaseIndent();
            Iterator<InCallServiceBindingConnection> it = this.mSubConnections.iterator();
            while (it.hasNext()) {
                it.next().dump(indentingPrintWriter);
            }
            indentingPrintWriter.decreaseIndent();
        }
    }

    public InCallController(Context context, TelecomSystem.SyncRoot syncRoot, CallsManager callsManager, SystemStateHelper systemStateHelper, DefaultDialerCache defaultDialerCache, Timeouts.Adapter adapter, EmergencyCallHelper emergencyCallHelper, CarModeTracker carModeTracker, ClockProxy clockProxy) {
        this.mContext = context;
        this.mAppOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        this.mLock = syncRoot;
        this.mCallsManager = callsManager;
        this.mSystemStateHelper = systemStateHelper;
        this.mTimeoutsAdapter = adapter;
        this.mDefaultDialerCache = defaultDialerCache;
        this.mEmergencyCallHelper = emergencyCallHelper;
        this.mCarModeTracker = carModeTracker;
        systemStateHelper.addListener(this.mSystemStateListener);
        this.mClockProxy = clockProxy;
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallAdded(Call call) {
        if (!isBoundAndConnectedToServices()) {
            Log.i(this, "onCallAdded: %s; not bound or connected.", new Object[]{call});
            bindToServices(call);
            return;
        }
        adjustServiceBindingsForEmergency();
        this.mEmergencyCallHelper.maybeGrantTemporaryLocationPermission(call, this.mCallsManager.getCurrentUserHandle());
        Log.i(this, "onCallAdded: %s", new Object[]{call});
        addCall(call);
        Log.i(this, "mInCallServiceConnection isConnected=%b", new Object[]{Boolean.valueOf(this.mInCallServiceConnection.isConnected())});
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<InCallServiceInfo, IInCallService> entry : this.mInCallServices.entrySet()) {
            InCallServiceInfo key = entry.getKey();
            if (!call.isExternalCall() || key.isExternalCallsSupported()) {
                if (!call.isSelfManaged() || key.isSelfManagedCallsSupported()) {
                    boolean zEquals = key.equals(this.mInCallServiceConnection.getInfo());
                    arrayList.add(key.getComponentName());
                    try {
                        entry.getValue().addCall(sanitizeParcelableCallForService(key, ParcelableCallUtils.toParcelableCall(call, true, this.mCallsManager.getPhoneAccountRegistrar(), key.isExternalCallsSupported(), zEquals, key.getType() == 2)));
                    } catch (RemoteException unused) {
                    }
                }
            }
        }
        Log.i(this, "Call added to components: %s", new Object[]{arrayList});
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallRemoved(Call call) {
        Log.i(this, "onCallRemoved: %s", new Object[]{call});
        if (this.mCallsManager.getCalls().isEmpty()) {
            this.mHandler.postDelayed(new Runnable("ICC.oCR", this.mLock) { // from class: com.android.server.telecom.InCallController.2
                public void loggedRun() {
                    if (InCallController.this.mCallsManager.getCalls().isEmpty()) {
                        InCallController.this.unbindFromServices();
                        InCallController.this.mEmergencyCallHelper.maybeRevokeTemporaryLocationPermission();
                    }
                }
            }.prepare(), this.mTimeoutsAdapter.getCallRemoveUnbindInCallServicesDelay(this.mContext.getContentResolver()));
        }
        call.removeListener(this.mCallListener);
        this.mCallIdMapper.removeCall(call);
        maybeTrackMicrophoneUse(isMuted());
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onExternalCallChanged(Call call, boolean z) {
        Log.i(this, "onExternalCallChanged: %s -> %b", new Object[]{call, Boolean.valueOf(z)});
        ArrayList arrayList = new ArrayList();
        if (!z) {
            for (Map.Entry<InCallServiceInfo, IInCallService> entry : this.mInCallServices.entrySet()) {
                InCallServiceInfo key = entry.getKey();
                if (!key.isExternalCallsSupported() && (!call.isSelfManaged() || key.isSelfManagedCallsSupported())) {
                    arrayList.add(key.getComponentName());
                    try {
                        entry.getValue().addCall(sanitizeParcelableCallForService(key, ParcelableCallUtils.toParcelableCall(call, true, this.mCallsManager.getPhoneAccountRegistrar(), key.isExternalCallsSupported(), key.equals(this.mInCallServiceConnection.getInfo()), key.getType() == 2)));
                    } catch (RemoteException unused) {
                    }
                }
            }
            Log.i(this, "Previously external call added to components: %s", new Object[]{arrayList});
        } else {
            Log.i(this, "Removing external call %s", new Object[]{call});
            for (Map.Entry<InCallServiceInfo, IInCallService> entry2 : this.mInCallServices.entrySet()) {
                InCallServiceInfo key2 = entry2.getKey();
                if (!key2.isExternalCallsSupported()) {
                    arrayList.add(key2.getComponentName());
                    try {
                        entry2.getValue().updateCall(sanitizeParcelableCallForService(key2, ParcelableCallUtils.toParcelableCall(call, false, this.mCallsManager.getPhoneAccountRegistrar(), false, 7, false, key2.getType() == 2)));
                    } catch (RemoteException unused2) {
                    }
                }
            }
            Log.i(this, "External call removed from components: %s", new Object[]{arrayList});
        }
        maybeTrackMicrophoneUse(isMuted());
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallStateChanged(Call call, int i, int i2) {
        maybeTrackMicrophoneUse(isMuted());
        updateCall(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onConnectionServiceChanged(Call call, ConnectionServiceWrapper connectionServiceWrapper, ConnectionServiceWrapper connectionServiceWrapper2) {
        updateCall(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallAudioStateChanged(CallAudioState callAudioState, CallAudioState callAudioState2) {
        if (this.mInCallServices.isEmpty()) {
            return;
        }
        Log.i(this, "Calling onAudioStateChanged, audioState: %s -> %s", new Object[]{callAudioState, callAudioState2});
        maybeTrackMicrophoneUse(callAudioState2.isMuted());
        Iterator<IInCallService> it = this.mInCallServices.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().onCallAudioStateChanged(callAudioState2);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCanAddCallChanged(boolean z) {
        if (this.mInCallServices.isEmpty()) {
            return;
        }
        Log.i(this, "onCanAddCallChanged : %b", new Object[]{Boolean.valueOf(z)});
        Iterator<IInCallService> it = this.mInCallServices.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().onCanAddCallChanged(z);
            } catch (RemoteException unused) {
            }
        }
    }

    void onPostDialWait(Call call, String str) {
        if (this.mInCallServices.isEmpty()) {
            return;
        }
        Log.i(this, "Calling onPostDialWait, remaining = %s", new Object[]{str});
        Iterator<IInCallService> it = this.mInCallServices.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().setPostDialWait(this.mCallIdMapper.getCallId(call), str);
            } catch (RemoteException unused) {
            }
        }
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIsConferencedChanged(Call call) {
        Log.d(this, "onIsConferencedChanged %s", new Object[]{call});
        updateCall(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onConnectionTimeChanged(Call call) {
        Log.d(this, "onConnectionTimeChanged %s", new Object[]{call});
        updateCall(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIsVoipAudioModeChanged(Call call) {
        Log.d(this, "onIsVoipAudioModeChanged %s", new Object[]{call});
        updateCall(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onConferenceStateChanged(Call call, boolean z) {
        Log.d(this, "onConferenceStateChanged %s ,isConf=%b", new Object[]{call, Boolean.valueOf(z)});
        updateCall(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCdmaConferenceSwap(Call call) {
        Log.d(this, "onCdmaConferenceSwap %s", new Object[]{call});
        updateCall(call);
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onSetCamera(Call call, String str) {
        Log.i(this, "onSetCamera callId=%s, cameraId=%s", new Object[]{call.getId(), str});
        if (str != null) {
            this.mAppOpsManager.startOp(101, Process.myUid(), this.mContext.getOpPackageName(), false, null, null);
        } else {
            this.mAppOpsManager.finishOp(101, Process.myUid(), this.mContext.getOpPackageName(), (String) null);
        }
    }

    void bringToForeground(boolean z) {
        if (!this.mInCallServices.isEmpty()) {
            Iterator<IInCallService> it = this.mInCallServices.values().iterator();
            while (it.hasNext()) {
                try {
                    it.next().bringToForeground(z);
                } catch (RemoteException unused) {
                }
            }
            return;
        }
        Log.w(this, "Asking to bring unbound in-call UI to foreground.", new Object[0]);
    }

    void silenceRinger() {
        if (this.mInCallServices.isEmpty()) {
            return;
        }
        Iterator<IInCallService> it = this.mInCallServices.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().silenceRinger();
            } catch (RemoteException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyConnectionEvent(Call call, String str, Bundle bundle) {
        if (this.mInCallServices.isEmpty()) {
            return;
        }
        for (IInCallService iInCallService : this.mInCallServices.values()) {
            try {
                Object[] objArr = new Object[3];
                objArr[0] = call != null ? call.toString() : "null";
                objArr[1] = str != null ? str : "null";
                objArr[2] = bundle != null ? bundle.toString() : "null";
                Log.i(this, "notifyConnectionEvent {Call: %s, Event: %s, Extras:[%s]}", objArr);
                iInCallService.onConnectionEvent(this.mCallIdMapper.getCallId(call), str, bundle);
            } catch (RemoteException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRttInitiationFailure(final Call call, final int i) {
        if (this.mInCallServices.isEmpty()) {
            return;
        }
        this.mInCallServices.entrySet().stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$InCallController$Bj0FwA5VzgLQ-Es_PRrbkWyyJ3c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$notifyRttInitiationFailure$1$InCallController((Map.Entry) obj);
            }
        }).forEach(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$InCallController$NF3HcUvpcnKPmNDSRkoouIHGh9s
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$notifyRttInitiationFailure$2$InCallController(call, i, (Map.Entry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$notifyRttInitiationFailure$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$notifyRttInitiationFailure$1$InCallController(Map.Entry entry) {
        return ((InCallServiceInfo) entry.getKey()).equals(this.mInCallServiceConnection.getInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$notifyRttInitiationFailure$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$notifyRttInitiationFailure$2$InCallController(Call call, int i, Map.Entry entry) {
        try {
            Log.i(this, "notifyRttFailure, call %s, incall %s", new Object[]{call, entry.getKey()});
            ((IInCallService) entry.getValue()).onRttInitiationFailure(this.mCallIdMapper.getCallId(call), i);
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRemoteRttRequest(final Call call, final int i) {
        if (this.mInCallServices.isEmpty()) {
            return;
        }
        this.mInCallServices.entrySet().stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$InCallController$dC2iAhRSfYFtAhI_wKPNiMZxLo8
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$notifyRemoteRttRequest$3$InCallController((Map.Entry) obj);
            }
        }).forEach(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$InCallController$9cgkVQuM6OTruheyC4S1QqHOizo
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$notifyRemoteRttRequest$4$InCallController(call, i, (Map.Entry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$notifyRemoteRttRequest$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ boolean lambda$notifyRemoteRttRequest$3$InCallController(Map.Entry entry) {
        return ((InCallServiceInfo) entry.getKey()).equals(this.mInCallServiceConnection.getInfo());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$notifyRemoteRttRequest$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$notifyRemoteRttRequest$4$InCallController(Call call, int i, Map.Entry entry) {
        try {
            Log.i(this, "notifyRemoteRttRequest, call %s, incall %s", new Object[]{call, entry.getKey()});
            ((IInCallService) entry.getValue()).onRttUpgradeRequest(this.mCallIdMapper.getCallId(call), i);
        } catch (RemoteException unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyHandoverFailed(Call call, int i) {
        if (this.mInCallServices.isEmpty()) {
            return;
        }
        Iterator<IInCallService> it = this.mInCallServices.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().onHandoverFailed(this.mCallIdMapper.getCallId(call), i);
            } catch (RemoteException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyHandoverComplete(Call call) {
        if (this.mInCallServices.isEmpty()) {
            return;
        }
        Iterator<IInCallService> it = this.mInCallServices.values().iterator();
        while (it.hasNext()) {
            try {
                it.next().onHandoverComplete(this.mCallIdMapper.getCallId(call));
            } catch (RemoteException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unbindFromServices() {
        CarSwappingInCallServiceConnection carSwappingInCallServiceConnection = this.mInCallServiceConnection;
        if (carSwappingInCallServiceConnection != null) {
            carSwappingInCallServiceConnection.disconnect();
            this.mInCallServiceConnection = null;
        }
        NonUIInCallServiceConnectionCollection nonUIInCallServiceConnectionCollection = this.mNonUIInCallServiceConnections;
        if (nonUIInCallServiceConnectionCollection != null) {
            nonUIInCallServiceConnectionCollection.disconnect();
            this.mNonUIInCallServiceConnections = null;
        }
        this.mInCallServices.clear();
    }

    @VisibleForTesting
    public void bindToServices(Call call) {
        if (this.mInCallServiceConnection == null) {
            InCallServiceInfo defaultDialerComponent = getDefaultDialerComponent();
            Log.i(this, "defaultDialer: " + defaultDialerComponent, new Object[0]);
            InCallServiceBindingConnection inCallServiceBindingConnection = null;
            InCallServiceBindingConnection inCallServiceBindingConnection2 = (defaultDialerComponent == null || defaultDialerComponent.getComponentName().equals(this.mDefaultDialerCache.getSystemDialerComponent())) ? null : new InCallServiceBindingConnection(defaultDialerComponent);
            Log.i(this, "defaultDialer: " + inCallServiceBindingConnection2, new Object[0]);
            EmergencyInCallServiceConnection emergencyInCallServiceConnection = new EmergencyInCallServiceConnection(getInCallServiceComponent(this.mDefaultDialerCache.getSystemDialerComponent(), 2), inCallServiceBindingConnection2);
            emergencyInCallServiceConnection.setHasEmergency(this.mCallsManager.isInEmergencyCall());
            InCallServiceInfo currentCarModeComponent = getCurrentCarModeComponent();
            if (currentCarModeComponent != null && !currentCarModeComponent.getComponentName().equals(this.mDefaultDialerCache.getSystemDialerComponent())) {
                inCallServiceBindingConnection = new InCallServiceBindingConnection(currentCarModeComponent);
            }
            this.mInCallServiceConnection = new CarSwappingInCallServiceConnection(emergencyInCallServiceConnection, inCallServiceBindingConnection);
        }
        this.mInCallServiceConnection.chooseInitialInCallService(shouldUseCarModeUI());
        if (this.mInCallServiceConnection.connect(call) == 1) {
            connectToNonUiInCallServices(call);
            this.mBindingFuture = new CompletableFuture().completeOnTimeout(Boolean.FALSE, this.mTimeoutsAdapter.getCallRemoveUnbindInCallServicesDelay(this.mContext.getContentResolver()), TimeUnit.MILLISECONDS);
        } else {
            Log.i(this, "bindToServices: current UI doesn't support call; not binding.", new Object[0]);
        }
    }

    private void connectToNonUiInCallServices(Call call) {
        List<InCallServiceInfo> inCallServiceComponents = getInCallServiceComponents(4);
        LinkedList linkedList = new LinkedList();
        Iterator<InCallServiceInfo> it = inCallServiceComponents.iterator();
        while (it.hasNext()) {
            linkedList.add(new InCallServiceBindingConnection(it.next()));
        }
        List<String> callCompanionApps = this.mCallsManager.getRoleManagerAdapter().getCallCompanionApps();
        if (callCompanionApps != null && !callCompanionApps.isEmpty()) {
            Iterator<String> it2 = callCompanionApps.iterator();
            while (it2.hasNext()) {
                InCallServiceInfo inCallServiceComponent = getInCallServiceComponent(it2.next(), 5);
                if (inCallServiceComponent != null) {
                    linkedList.add(new InCallServiceBindingConnection(inCallServiceComponent));
                }
            }
        }
        NonUIInCallServiceConnectionCollection nonUIInCallServiceConnectionCollection = new NonUIInCallServiceConnectionCollection(this, linkedList);
        this.mNonUIInCallServiceConnections = nonUIInCallServiceConnectionCollection;
        nonUIInCallServiceConnectionCollection.connect(call);
    }

    private InCallServiceInfo getDefaultDialerComponent() {
        String defaultDialerApplication = this.mDefaultDialerCache.getDefaultDialerApplication(this.mCallsManager.getCurrentUserHandle().getIdentifier());
        String systemDialerApplication = this.mDefaultDialerCache.getSystemDialerApplication();
        Log.d(this, "Default Dialer package: " + defaultDialerApplication, new Object[0]);
        if (systemDialerApplication != null && systemDialerApplication.equals(defaultDialerApplication)) {
            return getInCallServiceComponent(defaultDialerApplication, 2);
        }
        return getInCallServiceComponent(defaultDialerApplication, 1);
    }

    private InCallServiceInfo getCurrentCarModeComponent() {
        return getInCallServiceComponent(this.mCarModeTracker.getCurrentCarModePackage(), 3);
    }

    private InCallServiceInfo getInCallServiceComponent(ComponentName componentName, int i) {
        List<InCallServiceInfo> inCallServiceComponents = getInCallServiceComponents(componentName, i);
        if (inCallServiceComponents != null && !inCallServiceComponents.isEmpty()) {
            return inCallServiceComponents.get(0);
        }
        Log.e(this, new Exception(), "Package Manager could not find ComponentName: " + componentName + ". Trying to bind anyway.", new Object[0]);
        return new InCallServiceInfo(this, componentName, false, false, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public InCallServiceInfo getInCallServiceComponent(String str, int i) {
        List<InCallServiceInfo> inCallServiceComponents = getInCallServiceComponents(str, i);
        if (inCallServiceComponents == null || inCallServiceComponents.isEmpty()) {
            return null;
        }
        return inCallServiceComponents.get(0);
    }

    private List<InCallServiceInfo> getInCallServiceComponents(int i) {
        return getInCallServiceComponents(null, null, i);
    }

    private List<InCallServiceInfo> getInCallServiceComponents(String str, int i) {
        return getInCallServiceComponents(str, null, i);
    }

    private List<InCallServiceInfo> getInCallServiceComponents(ComponentName componentName, int i) {
        return getInCallServiceComponents(null, componentName, i);
    }

    private List<InCallServiceInfo> getInCallServiceComponents(String str, ComponentName componentName, int i) {
        LinkedList linkedList = new LinkedList();
        Intent intent = new Intent("android.telecom.InCallService");
        if (str != null) {
            intent.setPackage(str);
        }
        if (componentName != null) {
            intent.setComponent(componentName);
        }
        PackageManager packageManager = this.mContext.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryIntentServicesAsUser(intent, 128, this.mCallsManager.getCurrentUserHandle().getIdentifier())) {
            ServiceInfo serviceInfo = resolveInfo.serviceInfo;
            if (serviceInfo != null) {
                Bundle bundle = serviceInfo.metaData;
                boolean z = bundle != null && bundle.getBoolean("android.telecom.INCLUDE_EXTERNAL_CALLS", false);
                Bundle bundle2 = serviceInfo.metaData;
                boolean z2 = bundle2 != null && bundle2.getBoolean("android.telecom.INCLUDE_SELF_MANAGED_CALLS", false);
                int inCallServiceType = getInCallServiceType(resolveInfo.serviceInfo, packageManager, str);
                if (i == 0 || i == inCallServiceType) {
                    linkedList.add(new InCallServiceInfo(this, new ComponentName(serviceInfo.packageName, serviceInfo.name), z, i != 4 ? z2 : false, i));
                }
            }
        }
        return linkedList;
    }

    private boolean shouldUseCarModeUI() {
        return this.mCarModeTracker.isInCarMode();
    }

    private int getInCallServiceType(ServiceInfo serviceInfo, final PackageManager packageManager, String str) {
        String str2 = serviceInfo.permission;
        if (!(str2 != null && str2.equals("android.permission.BIND_INCALL_SERVICE"))) {
            Log.w(this, "InCallService does not require BIND_INCALL_SERVICE permission: " + serviceInfo.packageName, new Object[0]);
            return 0;
        }
        if (this.mDefaultDialerCache.getSystemDialerApplication().equals(serviceInfo.packageName) && this.mDefaultDialerCache.getSystemDialerComponent().getClassName().equals(serviceInfo.name)) {
            return 2;
        }
        Bundle bundle = serviceInfo.metaData;
        boolean z = bundle != null && bundle.getBoolean("android.telecom.IN_CALL_SERVICE_UI");
        boolean zAnyMatch = Arrays.stream(packageManager.getPackagesForUid(serviceInfo.applicationInfo.uid)).anyMatch(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$InCallController$tmJGgoBSJAKA-__Tacu08t4g-As
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InCallController.lambda$getInCallServiceType$5(packageManager, (String) obj);
            }
        });
        Bundle bundle2 = serviceInfo.metaData;
        boolean z2 = bundle2 != null && bundle2.getBoolean("android.telecom.IN_CALL_SERVICE_CAR_MODE_UI", false);
        if (z2 && zAnyMatch) {
            return 3;
        }
        if (Objects.equals(serviceInfo.packageName, this.mDefaultDialerCache.getDefaultDialerApplication(this.mCallsManager.getCurrentUserHandle().getIdentifier())) && z) {
            return 1;
        }
        if (!z && !z2 && zAnyMatch) {
            return 4;
        }
        Log.i(this, "Skipping binding to %s:%s, control: %b, car-mode: %b, ui: %b", new Object[]{serviceInfo.packageName, serviceInfo.name, Boolean.valueOf(zAnyMatch), Boolean.valueOf(z2), Boolean.valueOf(z)});
        return 0;
    }

    static /* synthetic */ boolean lambda$getInCallServiceType$5(PackageManager packageManager, String str) {
        return packageManager.checkPermission("android.permission.CONTROL_INCALL_EXPERIENCE", str) == 0;
    }

    private void adjustServiceBindingsForEmergency() {
        if (this.mCallsManager.isInEmergencyCall()) {
            this.mInCallServiceConnection.setHasEmergency(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onConnected(InCallServiceInfo inCallServiceInfo, IBinder iBinder) {
        Log.i(this, "onConnected to %s", new Object[]{inCallServiceInfo.getComponentName()});
        if (inCallServiceInfo.getType() == 3 || inCallServiceInfo.getType() == 2 || inCallServiceInfo.getType() == 1) {
            trackCallingUserInterfaceStarted(inCallServiceInfo);
        }
        IInCallService iInCallServiceAsInterface = IInCallService.Stub.asInterface(iBinder);
        this.mInCallServices.put(inCallServiceInfo, iInCallServiceAsInterface);
        try {
            iInCallServiceAsInterface.setInCallAdapter(new InCallAdapter(this.mCallsManager, this.mCallIdMapper, this.mLock, inCallServiceInfo.getComponentName().getPackageName()));
            List<Call> listOrderCallsWithChildrenFirst = orderCallsWithChildrenFirst(this.mCallsManager.getCalls());
            Log.i(this, "Adding %s calls to InCallService after onConnected: %s, including external calls", new Object[]{Integer.valueOf(listOrderCallsWithChildrenFirst.size()), inCallServiceInfo.getComponentName()});
            int i = 0;
            for (Call call : listOrderCallsWithChildrenFirst) {
                if (!call.isSelfManaged() || inCallServiceInfo.isSelfManagedCallsSupported()) {
                    if (!call.isExternalCall() || inCallServiceInfo.isExternalCallsSupported()) {
                        boolean zEquals = inCallServiceInfo.equals(this.mInCallServiceConnection.getInfo());
                        addCall(call);
                        i++;
                        iInCallServiceAsInterface.addCall(sanitizeParcelableCallForService(inCallServiceInfo, ParcelableCallUtils.toParcelableCall(call, true, this.mCallsManager.getPhoneAccountRegistrar(), inCallServiceInfo.isExternalCallsSupported(), zEquals, inCallServiceInfo.getType() == 2)));
                    }
                }
            }
            try {
                iInCallServiceAsInterface.onCallAudioStateChanged(this.mCallsManager.getAudioState());
                iInCallServiceAsInterface.onCanAddCallChanged(this.mCallsManager.canAddCall());
            } catch (RemoteException unused) {
            }
            if (inCallServiceInfo.getType() != 4) {
                this.mBindingFuture.complete(Boolean.TRUE);
            }
            Log.i(this, "%s calls sent to InCallService.", new Object[]{Integer.valueOf(i)});
            return true;
        } catch (RemoteException e) {
            Log.e(this, e, "Failed to set the in-call adapter.", new Object[0]);
            Trace.endSection();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDisconnected(InCallServiceInfo inCallServiceInfo) {
        Log.i(this, "onDisconnected from %s", new Object[]{inCallServiceInfo.getComponentName()});
        if (inCallServiceInfo.getType() == 3 || inCallServiceInfo.getType() == 2 || inCallServiceInfo.getType() == 1) {
            trackCallingUserInterfaceStopped(inCallServiceInfo);
        }
        this.mInCallServices.remove(inCallServiceInfo);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCall(Call call) {
        updateCall(call, false, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCall(Call call, boolean z, boolean z2) {
        if (this.mInCallServices.isEmpty()) {
            return;
        }
        Log.i(this, "Sending updateCall %s", new Object[]{call});
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<InCallServiceInfo, IInCallService> entry : this.mInCallServices.entrySet()) {
            InCallServiceInfo key = entry.getKey();
            if (!call.isExternalCall() || key.isExternalCallsSupported()) {
                if (!call.isSelfManaged() || key.isSelfManagedCallsSupported()) {
                    ParcelableCall parcelableCall = ParcelableCallUtils.toParcelableCall(call, z, this.mCallsManager.getPhoneAccountRegistrar(), key.isExternalCallsSupported(), z2 && key.equals(this.mInCallServiceConnection.getInfo()), key.getType() == 2);
                    ComponentName componentName = key.getComponentName();
                    IInCallService value = entry.getValue();
                    arrayList.add(componentName);
                    try {
                        value.updateCall(sanitizeParcelableCallForService(key, parcelableCall));
                    } catch (RemoteException unused) {
                    }
                }
            }
        }
        Log.i(this, "Components updated: %s", new Object[]{arrayList});
    }

    private void addCall(Call call) {
        if (this.mCallIdMapper.getCallId(call) == null) {
            this.mCallIdMapper.addCall(call);
            call.addListener(this.mCallListener);
        }
        maybeTrackMicrophoneUse(isMuted());
    }

    private boolean isBoundAndConnectedToServices() {
        CarSwappingInCallServiceConnection carSwappingInCallServiceConnection = this.mInCallServiceConnection;
        return carSwappingInCallServiceConnection != null && carSwappingInCallServiceConnection.isConnected();
    }

    public CompletableFuture<Boolean> getBindingFuture() {
        return this.mBindingFuture;
    }

    public void dump(IndentingPrintWriter indentingPrintWriter) {
        indentingPrintWriter.println("mInCallServices (InCalls registered):");
        indentingPrintWriter.increaseIndent();
        Iterator<InCallServiceInfo> it = this.mInCallServices.keySet().iterator();
        while (it.hasNext()) {
            indentingPrintWriter.println(it.next());
        }
        indentingPrintWriter.decreaseIndent();
        indentingPrintWriter.println("ServiceConnections (InCalls bound):");
        indentingPrintWriter.increaseIndent();
        CarSwappingInCallServiceConnection carSwappingInCallServiceConnection = this.mInCallServiceConnection;
        if (carSwappingInCallServiceConnection != null) {
            carSwappingInCallServiceConnection.dump(indentingPrintWriter);
        }
        indentingPrintWriter.decreaseIndent();
        this.mCarModeTracker.dump(indentingPrintWriter);
    }

    private ComponentName getConnectedUi() {
        InCallServiceInfo inCallServiceInfoOrElse = this.mInCallServices.keySet().stream().filter(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$InCallController$yIIX_8NnO5Mq2hDvl9K33_0W5ns
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InCallController.lambda$getConnectedUi$6((InCallController.InCallServiceInfo) obj);
            }
        }).findAny().orElse(null);
        if (inCallServiceInfoOrElse != null) {
            return inCallServiceInfoOrElse.mComponentName;
        }
        return null;
    }

    static /* synthetic */ boolean lambda$getConnectedUi$6(InCallServiceInfo inCallServiceInfo) {
        return inCallServiceInfo.getType() == 1 || inCallServiceInfo.getType() == 2;
    }

    public boolean doesConnectedDialerSupportRinging() {
        String defaultDialerApplication;
        Bundle bundle;
        ComponentName connectedUi = getConnectedUi();
        if (connectedUi != null) {
            defaultDialerApplication = connectedUi.getPackageName().trim();
            Log.d(this, "doesConnectedDialerSupportRinging: alreadyConnectedPackage=%s", new Object[]{defaultDialerApplication});
        } else {
            defaultDialerApplication = null;
        }
        if (TextUtils.isEmpty(defaultDialerApplication) && (defaultDialerApplication = this.mDefaultDialerCache.getDefaultDialerApplication(this.mCallsManager.getCurrentUserHandle().getIdentifier())) != null) {
            Log.d(this, "doesConnectedDialerSupportRinging: notCurentlyConnectedPackage=%s", new Object[]{defaultDialerApplication});
        }
        if (TextUtils.isEmpty(defaultDialerApplication)) {
            Log.w(this, "doesConnectedDialerSupportRinging: no default dialer found; oh no!", new Object[0]);
            return false;
        }
        List listQueryIntentServicesAsUser = this.mContext.getPackageManager().queryIntentServicesAsUser(new Intent("android.telecom.InCallService").setPackage(defaultDialerApplication), 128, this.mCallsManager.getCurrentUserHandle().getIdentifier());
        if (listQueryIntentServicesAsUser.isEmpty()) {
            Log.w(this, "doesConnectedDialerSupportRinging: couldn't find dialer's package info <sad trombone>", new Object[0]);
            return false;
        }
        ServiceInfo serviceInfo = ((ResolveInfo) listQueryIntentServicesAsUser.get(0)).serviceInfo;
        if (serviceInfo == null || (bundle = serviceInfo.metaData) == null) {
            Log.w(this, "doesConnectedDialerSupportRinging: couldn't find dialer's metadata <even sadder trombone>", new Object[0]);
            return false;
        }
        return bundle.getBoolean("android.telecom.IN_CALL_SERVICE_RINGING", false);
    }

    private List<Call> orderCallsWithChildrenFirst(Collection<Call> collection) {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        for (Call call : collection) {
            if (call.getChildCalls().size() > 0) {
                linkedList.add(call);
            } else {
                linkedList2.add(call);
            }
        }
        linkedList2.addAll(linkedList);
        return linkedList2;
    }

    private ParcelableCall sanitizeParcelableCallForService(InCallServiceInfo inCallServiceInfo, ParcelableCall parcelableCall) {
        ParcelableCall.ParcelableCallBuilder parcelableCallBuilderFromParcelableCall = ParcelableCall.ParcelableCallBuilder.fromParcelableCall(parcelableCall);
        if (this.mContext.getPackageManager().checkPermission("android.permission.READ_CONTACTS", inCallServiceInfo.getComponentName().getPackageName()) != 0) {
            parcelableCallBuilderFromParcelableCall.setContactDisplayName((String) null);
        }
        return parcelableCallBuilderFromParcelableCall.createParcelableCall();
    }

    @VisibleForTesting
    public Handler getHandler() {
        return this.mHandler;
    }

    private boolean isCarModeInCallService(String str) {
        InCallServiceInfo inCallServiceComponent = getInCallServiceComponent(str, 3);
        return inCallServiceComponent != null && inCallServiceComponent.getType() == 3;
    }

    /* JADX INFO: renamed from: handleCarModeChange, reason: merged with bridge method [inline-methods] */
    public void lambda$new$0$InCallController(int i, String str, boolean z) {
        Log.i(this, "handleCarModeChange: packageName=%s, priority=%d, isCarMode=%b", new Object[]{str, Integer.valueOf(i), Boolean.valueOf(z)});
        if (!isCarModeInCallService(str)) {
            Log.i(this, "handleCarModeChange: not a valid InCallService; packageName=%s", new Object[]{str});
            return;
        }
        if (z) {
            this.mCarModeTracker.handleEnterCarMode(i, str);
        } else {
            this.mCarModeTracker.handleExitCarMode(i, str);
        }
        if (this.mInCallServiceConnection != null) {
            Log.i(this, "handleCarModeChange: car mode apps: %s", new Object[]{this.mCarModeTracker.getCarModeApps().stream().collect(Collectors.joining(", "))});
            if (shouldUseCarModeUI()) {
                this.mInCallServiceConnection.changeCarModeApp(this.mCarModeTracker.getCurrentCarModePackage());
            } else {
                this.mInCallServiceConnection.disableCarMode();
            }
        }
    }

    private void trackCallingUserInterfaceStarted(InCallServiceInfo inCallServiceInfo) {
        String packageName = inCallServiceInfo.getComponentName().getPackageName();
        if (!Objects.equals(this.mCurrentUserInterfacePackageName, packageName)) {
            Log.i(this, "trackCallingUserInterfaceStarted: %s is now calling UX.", new Object[]{packageName});
            this.mCurrentUserInterfacePackageName = packageName;
        }
        maybeTrackMicrophoneUse(isMuted());
    }

    private void trackCallingUserInterfaceStopped(InCallServiceInfo inCallServiceInfo) {
        maybeTrackMicrophoneUse(isMuted());
        this.mCurrentUserInterfacePackageName = null;
        Log.i(this, "trackCallingUserInterfaceStopped: %s is no longer calling UX", new Object[]{inCallServiceInfo.getComponentName().getPackageName()});
    }

    private void maybeTrackMicrophoneUse(boolean z) {
        boolean z2 = this.mIsCallUsingMicrophone;
        boolean z3 = isTrackingManagedAliveCall() && !z;
        this.mIsCallUsingMicrophone = z3;
        if (z2 != z3) {
            if (z3) {
                this.mAppOpsManager.startOp(100, Process.myUid(), this.mContext.getOpPackageName(), false, null, null);
            } else {
                this.mAppOpsManager.finishOp(100, Process.myUid(), this.mContext.getOpPackageName(), (String) null);
            }
        }
    }

    private boolean isTrackingManagedAliveCall() {
        return this.mCallIdMapper.getCalls().stream().anyMatch(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$InCallController$MFSghgG6acsAXF0jwhqLtdLNsRU
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InCallController.lambda$isTrackingManagedAliveCall$7((Call) obj);
            }
        });
    }

    static /* synthetic */ boolean lambda$isTrackingManagedAliveCall$7(Call call) {
        return (call.isExternalCall() || call.isSelfManaged() || !call.isAlive() || call.getState() == 6 || call.getState() == 12) ? false : true;
    }

    private boolean isMuted() {
        if (this.mCallsManager.getAudioState() == null) {
            return false;
        }
        return this.mCallsManager.getAudioState().isMuted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v5 */
    public void sendCrashedInCallServiceNotification(String str) {
        PackageManager packageManager = this.mContext.getPackageManager();
        String systemDialerApplication = this.mDefaultDialerCache.getSystemDialerApplication();
        if (systemDialerApplication == null || !systemDialerApplication.equals(str)) {
            try {
                CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo((String) str, 0));
                if (!TextUtils.isEmpty(applicationLabel)) {
                    str = applicationLabel;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            NotificationManager notificationManager = (NotificationManager) this.mContext.getSystemService("notification");
            Notification.Builder builder = new Notification.Builder(this.mContext, "TelecomInCallServiceCrash");
            builder.setSmallIcon(R.drawable.ic_phone).setColor(this.mContext.getResources().getColor(R.color.theme_color)).setContentTitle(this.mContext.getString(R.string.notification_incallservice_not_responding_title, new Object[]{str})).setStyle(new Notification.BigTextStyle().bigText(this.mContext.getText(R.string.notification_incallservice_not_responding_body)));
            notificationManager.notify(NOTIFICATION_TAG, 3, builder.build());
        }
    }
}
