package com.android.server.telecom;

import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.IBluetoothHeadsetPhone;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.telecom.Log;
import android.telecom.PhoneAccount;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.CallsManager;
import com.android.server.telecom.TelecomSystem;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPhoneServiceImpl {
    private BluetoothAdapterProxy mBluetoothAdapter;
    private BluetoothHeadsetProxy mBluetoothHeadset;
    private final CallsManager mCallsManager;
    private final Context mContext;
    private final TelecomSystem.SyncRoot mLock;
    private final PhoneAccountRegistrar mPhoneAccountRegistrar;
    private int mNumActiveCalls = 0;
    private int mNumHeldCalls = 0;
    private int mNumChildrenOfActiveCall = 0;
    private int mBluetoothCallState = 6;
    private String mRingingAddress = "";
    private int mRingingAddressType = 128;
    private Call mOldHeldCall = null;
    private boolean mIsDisconnectedTonePlaying = false;

    @VisibleForTesting
    public final IBluetoothHeadsetPhone.Stub mBinder = new IBluetoothHeadsetPhone.Stub() { // from class: com.android.server.telecom.BluetoothPhoneServiceImpl.1
        public boolean answerCall() throws RemoteException {
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                BluetoothPhoneServiceImpl.this.enforceModifyPermission();
                Log.startSession("BPSI.aC");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    Log.i("BluetoothPhoneService", "BT - answering call", new Object[0]);
                    Call ringingOrSimulatedRingingCall = BluetoothPhoneServiceImpl.this.mCallsManager.getRingingOrSimulatedRingingCall();
                    if (ringingOrSimulatedRingingCall == null) {
                        return false;
                    }
                    BluetoothPhoneServiceImpl.this.mCallsManager.answerCall(ringingOrSimulatedRingingCall, 0);
                    return true;
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }
        }

        public boolean hangupCall() throws RemoteException {
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                BluetoothPhoneServiceImpl.this.enforceModifyPermission();
                Log.startSession("BPSI.hC");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    Log.i("BluetoothPhoneService", "BT - hanging up call", new Object[0]);
                    Call foregroundCall = BluetoothPhoneServiceImpl.this.mCallsManager.getForegroundCall();
                    if (foregroundCall == null) {
                        return false;
                    }
                    BluetoothPhoneServiceImpl.this.mCallsManager.lambda$disconnectOtherCalls$17(foregroundCall);
                    return true;
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }
        }

        public boolean sendDtmf(int i) throws RemoteException {
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                BluetoothPhoneServiceImpl.this.enforceModifyPermission();
                Log.startSession("BPSI.sD");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    Object[] objArr = new Object[1];
                    objArr[0] = Integer.valueOf(Log.DEBUG ? i : 46);
                    Log.i("BluetoothPhoneService", "BT - sendDtmf %c", objArr);
                    Call foregroundCall = BluetoothPhoneServiceImpl.this.mCallsManager.getForegroundCall();
                    if (foregroundCall == null) {
                        return false;
                    }
                    BluetoothPhoneServiceImpl.this.mCallsManager.playDtmfTone(foregroundCall, (char) i);
                    BluetoothPhoneServiceImpl.this.mCallsManager.stopDtmfTone(foregroundCall);
                    return true;
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }
        }

        public String getNetworkOperator() throws RemoteException {
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                BluetoothPhoneServiceImpl.this.enforceModifyPermission();
                Log.startSession("BPSI.gNO");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    Log.i("BluetoothPhoneService", "getNetworkOperator", new Object[0]);
                    PhoneAccount bestPhoneAccount = BluetoothPhoneServiceImpl.this.getBestPhoneAccount();
                    if (bestPhoneAccount != null && bestPhoneAccount.getLabel() != null) {
                        return bestPhoneAccount.getLabel().toString();
                    }
                    return ((TelephonyManager) BluetoothPhoneServiceImpl.this.mContext.getSystemService(TelephonyManager.class)).getNetworkOperatorName();
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }
        }

        public String getSubscriberNumber() throws RemoteException {
            String line1Number;
            Uri address;
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                BluetoothPhoneServiceImpl.this.enforceModifyPermission();
                Log.startSession("BPSI.gSN");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    Log.i("BluetoothPhoneService", "getSubscriberNumber", new Object[0]);
                    line1Number = null;
                    PhoneAccount bestPhoneAccount = BluetoothPhoneServiceImpl.this.getBestPhoneAccount();
                    if (bestPhoneAccount != null && (address = bestPhoneAccount.getAddress()) != null) {
                        line1Number = address.getSchemeSpecificPart();
                    }
                    if (TextUtils.isEmpty(line1Number)) {
                        line1Number = ((TelephonyManager) BluetoothPhoneServiceImpl.this.mContext.getSystemService(TelephonyManager.class)).getLine1Number();
                        if (line1Number == null) {
                            line1Number = "";
                        }
                    }
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }
            return line1Number;
        }

        public boolean listCurrentCalls() throws RemoteException {
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                BluetoothPhoneServiceImpl.this.enforceModifyPermission();
                Log.startSession("BPSI.lCC");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    boolean z = BluetoothPhoneServiceImpl.this.mHeadsetUpdatedRecently;
                    BluetoothPhoneServiceImpl.this.mHeadsetUpdatedRecently = false;
                    if (z) {
                        Log.i("BluetoothPhoneService", "listcurrentCalls", new Object[0]);
                    }
                    BluetoothPhoneServiceImpl.this.sendListOfCalls(z);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }
            return true;
        }

        public boolean queryPhoneState() throws RemoteException {
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                BluetoothPhoneServiceImpl.this.enforceModifyPermission();
                Log.startSession("BPSI.qPS");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    Log.i("BluetoothPhoneService", "queryPhoneState", new Object[0]);
                    BluetoothPhoneServiceImpl.this.updateHeadsetWithCallState(true);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }
            return true;
        }

        public boolean processChld(int i) throws RemoteException {
            boolean zProcessChld;
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                BluetoothPhoneServiceImpl.this.enforceModifyPermission();
                Log.startSession("BPSI.pC");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    Log.i("BluetoothPhoneService", "processChld %d", new Object[]{Integer.valueOf(i)});
                    zProcessChld = BluetoothPhoneServiceImpl.this.processChld(i);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }
            return zProcessChld;
        }

        public void updateBtHandsfreeAfterRadioTechnologyChange() throws RemoteException {
            Log.d("BluetoothPhoneService", "RAT change - deprecated", new Object[0]);
        }

        public void cdmaSetSecondCallState(boolean z) throws RemoteException {
            Log.d("BluetoothPhoneService", "cdma 1 - deprecated", new Object[0]);
        }

        public void cdmaSwapSecondCallState() throws RemoteException {
            Log.d("BluetoothPhoneService", "cdma 2 - deprecated", new Object[0]);
        }
    };

    @VisibleForTesting
    public CallsManager.CallsManagerListener mCallsManagerListener = new CallsManagerListenerBase() { // from class: com.android.server.telecom.BluetoothPhoneServiceImpl.2
        @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
        public void onCallAdded(Call call) {
            if (call.isExternalCall()) {
                return;
            }
            BluetoothPhoneServiceImpl.this.updateHeadsetWithCallState(false);
        }

        @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
        public void onCallRemoved(Call call) {
            if (call.isExternalCall()) {
                return;
            }
            BluetoothPhoneServiceImpl.this.mClccIndexMap.remove(call);
            BluetoothPhoneServiceImpl.this.updateHeadsetWithCallState(false);
        }

        @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
        public void onExternalCallChanged(Call call, boolean z) {
            if (z) {
                onCallRemoved(call);
            } else {
                onCallAdded(call);
            }
        }

        @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
        public void onCallStateChanged(Call call, int i, int i2) {
            if (call.isExternalCall()) {
                return;
            }
            if (i == 5 && i2 == 6) {
                Iterator<Call> it = BluetoothPhoneServiceImpl.this.mCallsManager.getCalls().iterator();
                while (it.hasNext()) {
                    if (it.next().getState() == 1) {
                        return;
                    }
                }
            }
            if (BluetoothPhoneServiceImpl.this.mCallsManager.getActiveCall() != null && i == 1 && (i2 == 3 || i2 == 10)) {
                return;
            }
            BluetoothPhoneServiceImpl.this.updateHeadsetWithCallState(false);
        }

        @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
        public void onIsConferencedChanged(Call call) {
            if (call.isExternalCall()) {
                return;
            }
            if (call.getParentCall() != null) {
                Log.d(this, "Ignoring onIsConferenceChanged from child call with new parent", new Object[0]);
            } else if (call.getChildCalls().size() != 1) {
                BluetoothPhoneServiceImpl.this.updateHeadsetWithCallState(false);
            } else {
                Log.d(this, "Ignoring onIsConferenceChanged from parent with only one child call", new Object[0]);
            }
        }

        @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
        public void onDisconnectedTonePlaying(boolean z) {
            BluetoothPhoneServiceImpl.this.mIsDisconnectedTonePlaying = z;
            BluetoothPhoneServiceImpl.this.updateHeadsetWithCallState(false);
        }
    };

    @VisibleForTesting
    public BluetoothProfile.ServiceListener mProfileListener = new BluetoothProfile.ServiceListener() { // from class: com.android.server.telecom.BluetoothPhoneServiceImpl.3
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                BluetoothPhoneServiceImpl.this.setBluetoothHeadset(new BluetoothHeadsetProxy((BluetoothHeadset) bluetoothProfile));
                BluetoothPhoneServiceImpl.this.updateHeadsetWithCallState(true);
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                BluetoothPhoneServiceImpl.this.mBluetoothHeadset = null;
            }
        }
    };

    @VisibleForTesting
    public final BroadcastReceiver mBluetoothAdapterReceiver = new BroadcastReceiver() { // from class: com.android.server.telecom.BluetoothPhoneServiceImpl.4
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            synchronized (BluetoothPhoneServiceImpl.this.mLock) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                Log.d("BluetoothPhoneService", "Bluetooth Adapter state: %d", new Object[]{Integer.valueOf(intExtra)});
                if (intExtra == 12) {
                    try {
                        BluetoothPhoneServiceImpl.this.mBinder.queryPhoneState();
                    } catch (RemoteException unused) {
                    }
                }
            }
        }
    };
    private Map<Call, Integer> mClccIndexMap = new HashMap();
    private boolean mHeadsetUpdatedRecently = false;

    public interface BluetoothPhoneServiceImplFactory {
        BluetoothPhoneServiceImpl makeBluetoothPhoneServiceImpl(Context context, TelecomSystem.SyncRoot syncRoot, CallsManager callsManager, PhoneAccountRegistrar phoneAccountRegistrar);
    }

    public IBinder getBinder() {
        return this.mBinder;
    }

    public BluetoothPhoneServiceImpl(Context context, TelecomSystem.SyncRoot syncRoot, CallsManager callsManager, BluetoothAdapterProxy bluetoothAdapterProxy, PhoneAccountRegistrar phoneAccountRegistrar) {
        Log.d(this, "onCreate", new Object[0]);
        this.mContext = context;
        this.mLock = syncRoot;
        this.mCallsManager = callsManager;
        this.mPhoneAccountRegistrar = phoneAccountRegistrar;
        this.mBluetoothAdapter = bluetoothAdapterProxy;
        if (bluetoothAdapterProxy == null) {
            Log.d(this, "BluetoothPhoneService shutting down, no BT Adapter found.", new Object[0]);
            return;
        }
        bluetoothAdapterProxy.getProfileProxy(context, this.mProfileListener, 1);
        context.registerReceiver(this.mBluetoothAdapterReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        this.mCallsManager.addListener(this.mCallsManagerListener);
        updateHeadsetWithCallState(false);
    }

    @VisibleForTesting
    public void setBluetoothHeadset(BluetoothHeadsetProxy bluetoothHeadsetProxy) {
        this.mBluetoothHeadset = bluetoothHeadsetProxy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean processChld(int i) {
        Call activeCall = this.mCallsManager.getActiveCall();
        Call ringingOrSimulatedRingingCall = this.mCallsManager.getRingingOrSimulatedRingingCall();
        Call heldCall = this.mCallsManager.getHeldCall();
        Log.i("BluetoothPhoneService", "Active: %s\nRinging: %s\nHeld: %s", new Object[]{activeCall, ringingOrSimulatedRingingCall, heldCall});
        if (i == 0) {
            if (ringingOrSimulatedRingingCall != null) {
                this.mCallsManager.rejectCall(ringingOrSimulatedRingingCall, false, null);
                return true;
            }
            if (heldCall != null) {
                this.mCallsManager.lambda$disconnectOtherCalls$17(heldCall);
                return true;
            }
        } else {
            if (i == 1) {
                if (activeCall == null && ringingOrSimulatedRingingCall == null && heldCall == null) {
                    return false;
                }
                if (activeCall != null) {
                    this.mCallsManager.lambda$disconnectOtherCalls$17(activeCall);
                    if (ringingOrSimulatedRingingCall != null) {
                        this.mCallsManager.answerCall(ringingOrSimulatedRingingCall, 0);
                    }
                    return true;
                }
                if (ringingOrSimulatedRingingCall != null) {
                    this.mCallsManager.answerCall(ringingOrSimulatedRingingCall, ringingOrSimulatedRingingCall.getVideoState());
                } else if (heldCall != null) {
                    this.mCallsManager.unholdCall(heldCall);
                }
                return true;
            }
            if (i == 2) {
                if (activeCall != null && activeCall.can(8)) {
                    activeCall.swapConference();
                    Log.i("BluetoothPhoneService", "CDMA calls in conference swapped, updating headset", new Object[0]);
                    updateHeadsetWithCallState(true);
                    return true;
                }
                if (ringingOrSimulatedRingingCall != null) {
                    this.mCallsManager.answerCall(ringingOrSimulatedRingingCall, 0);
                    return true;
                }
                if (heldCall != null) {
                    this.mCallsManager.unholdCall(heldCall);
                    return true;
                }
                if (activeCall != null && activeCall.can(1)) {
                    this.mCallsManager.holdCall(activeCall);
                    return true;
                }
            } else if (i == 3 && activeCall != null) {
                if (activeCall.can(4)) {
                    activeCall.mergeConference();
                    return true;
                }
                List<Call> conferenceableCalls = activeCall.getConferenceableCalls();
                if (!conferenceableCalls.isEmpty()) {
                    this.mCallsManager.conference(activeCall, conferenceableCalls.get(0));
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceModifyPermission() {
        this.mContext.enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendListOfCalls(boolean z) {
        for (Call call : this.mCallsManager.getCalls()) {
            if (!call.isConference() || (call.isConference() && call.can(2097152))) {
                sendClccForCall(call, z);
            }
        }
        sendClccEndMarker();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x005b  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void sendClccForCall(com.android.server.telecom.Call r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.BluetoothPhoneServiceImpl.sendClccForCall(com.android.server.telecom.Call, boolean):void");
    }

    private void sendClccEndMarker() {
        BluetoothHeadsetProxy bluetoothHeadsetProxy = this.mBluetoothHeadset;
        if (bluetoothHeadsetProxy != null) {
            bluetoothHeadsetProxy.clccResponse(0, 0, 0, 0, false, null, 0);
        }
    }

    private int getIndexForCall(Call call) {
        if (this.mClccIndexMap.containsKey(call)) {
            return this.mClccIndexMap.get(call).intValue();
        }
        int i = 1;
        while (this.mClccIndexMap.containsValue(Integer.valueOf(i))) {
            i++;
        }
        this.mClccIndexMap.put(call, Integer.valueOf(i));
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void updateHeadsetWithCallState(boolean z) {
        String name;
        byte b;
        String str;
        byte b2;
        char c;
        Call activeCall = this.mCallsManager.getActiveCall();
        Call ringingOrSimulatedRingingCall = this.mCallsManager.getRingingOrSimulatedRingingCall();
        Call heldCall = this.mCallsManager.getHeldCall();
        int bluetoothCallStateForUpdate = getBluetoothCallStateForUpdate();
        String schemeSpecificPart = null;
        if (ringingOrSimulatedRingingCall == null || ringingOrSimulatedRingingCall.getHandle() == null || ringingOrSimulatedRingingCall.isSilentRingingRequested()) {
            name = null;
        } else {
            schemeSpecificPart = ringingOrSimulatedRingingCall.getHandle().getSchemeSpecificPart();
            i = schemeSpecificPart != null ? PhoneNumberUtils.toaFromString(schemeSpecificPart) : 128;
            String callerDisplayName = ringingOrSimulatedRingingCall.getCallerDisplayName();
            name = TextUtils.isEmpty(callerDisplayName) ? ringingOrSimulatedRingingCall.getName() : callerDisplayName;
        }
        if (schemeSpecificPart == null) {
            schemeSpecificPart = "";
        }
        int i = activeCall == null ? 0 : 1;
        int numHeldCalls = this.mCallsManager.getNumHeldCalls();
        int size = activeCall == null ? 0 : activeCall.getChildCalls().size();
        byte b3 = numHeldCalls == 2;
        if (activeCall == null || !activeCall.isConference() || activeCall.can(2097152)) {
            b = false;
        } else {
            if (activeCall.can(8)) {
                numHeldCalls = !activeCall.wasConferencePreviouslyMerged() ? 1 : 0;
            } else if (activeCall.can(4)) {
                numHeldCalls = 1;
            }
            Iterator<Call> it = activeCall.getChildCalls().iterator();
            while (it.hasNext()) {
                if (this.mOldHeldCall == it.next()) {
                    b = true;
                    break;
                }
            }
            b = false;
        }
        if (this.mBluetoothHeadset != null) {
            if (!z) {
                if (b3 == true) {
                    return;
                }
                if (i == this.mNumActiveCalls && size == this.mNumChildrenOfActiveCall && numHeldCalls == this.mNumHeldCalls && bluetoothCallStateForUpdate == this.mBluetoothCallState && TextUtils.equals(schemeSpecificPart, this.mRingingAddress) && i == this.mRingingAddressType && (heldCall == this.mOldHeldCall || b != false)) {
                    return;
                }
            }
            byte b4 = this.mBluetoothCallState != bluetoothCallStateForUpdate && bluetoothCallStateForUpdate == 3;
            this.mOldHeldCall = heldCall;
            this.mNumActiveCalls = i;
            this.mNumChildrenOfActiveCall = size;
            this.mNumHeldCalls = numHeldCalls;
            this.mBluetoothCallState = bluetoothCallStateForUpdate;
            this.mRingingAddress = schemeSpecificPart;
            this.mRingingAddressType = i;
            if (b4 == true) {
                Log.i("BluetoothPhoneService", "updateHeadsetWithCallState numActive %s, numHeld %s, callState %s, ringing number %s, ringing type %s, ringing name %s", new Object[]{Integer.valueOf(i), Integer.valueOf(this.mNumHeldCalls), 2, Log.pii(this.mRingingAddress), Integer.valueOf(this.mRingingAddressType), Log.pii(name)});
                str = "BluetoothPhoneService";
                c = 4;
                b2 = 3;
                this.mBluetoothHeadset.phoneStateChanged(this.mNumActiveCalls, this.mNumHeldCalls, 2, this.mRingingAddress, this.mRingingAddressType, name);
            } else {
                str = "BluetoothPhoneService";
                b2 = 3;
                c = 4;
            }
            Object[] objArr = new Object[6];
            objArr[0] = Integer.valueOf(this.mNumActiveCalls);
            objArr[1] = Integer.valueOf(this.mNumHeldCalls);
            objArr[2] = Integer.valueOf(this.mBluetoothCallState);
            objArr[b2] = Log.pii(this.mRingingAddress);
            objArr[c] = Integer.valueOf(this.mRingingAddressType);
            objArr[5] = Log.pii(name);
            Log.i(str, "updateHeadsetWithCallState numActive %s, numHeld %s, callState %s, ringing number %s, ringing type %s, ringing name %s", objArr);
            this.mBluetoothHeadset.phoneStateChanged(this.mNumActiveCalls, this.mNumHeldCalls, this.mBluetoothCallState, this.mRingingAddress, this.mRingingAddressType, name);
            this.mHeadsetUpdatedRecently = true;
        }
    }

    private int getBluetoothCallStateForUpdate() {
        Call ringingOrSimulatedRingingCall = this.mCallsManager.getRingingOrSimulatedRingingCall();
        Call outgoingCall = this.mCallsManager.getOutgoingCall();
        boolean zHasOnlyDisconnectedCalls = this.mCallsManager.hasOnlyDisconnectedCalls();
        if (ringingOrSimulatedRingingCall != null && !ringingOrSimulatedRingingCall.isSilentRingingRequested()) {
            return 4;
        }
        if (outgoingCall != null) {
            return 3;
        }
        return (zHasOnlyDisconnectedCalls || this.mIsDisconnectedTonePlaying) ? 7 : 6;
    }

    private int getBtCallState(Call call, boolean z) {
        int state = call.getState();
        if (state == 10) {
            return 3;
        }
        if (state != 11 && state != 13) {
            switch (state) {
                case 1:
                case HeadsetMediaButton.LONG_PRESS /* 2 */:
                case 3:
                    return 3;
                case 4:
                    break;
                case 5:
                    return 0;
                case 6:
                    return 1;
                default:
                    return 6;
            }
        }
        if (call.isSilentRingingRequested()) {
            return 6;
        }
        return z ? 4 : 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PhoneAccount getBestPhoneAccount() {
        if (this.mPhoneAccountRegistrar == null) {
            return null;
        }
        Call foregroundCall = this.mCallsManager.getForegroundCall();
        PhoneAccount phoneAccountOfCurrentUser = foregroundCall != null ? this.mPhoneAccountRegistrar.getPhoneAccountOfCurrentUser(foregroundCall.getTargetPhoneAccount()) : null;
        if (phoneAccountOfCurrentUser != null) {
            return phoneAccountOfCurrentUser;
        }
        PhoneAccountRegistrar phoneAccountRegistrar = this.mPhoneAccountRegistrar;
        return phoneAccountRegistrar.getPhoneAccountUnchecked(phoneAccountRegistrar.getOutgoingPhoneAccountForSchemeOfCurrentUser("tel"));
    }
}
