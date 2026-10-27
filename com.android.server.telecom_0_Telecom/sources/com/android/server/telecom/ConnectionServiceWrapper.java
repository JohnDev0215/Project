package com.android.server.telecom;

import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.os.UserHandle;
import android.telecom.CallAudioState;
import android.telecom.ConnectionRequest;
import android.telecom.DisconnectCause;
import android.telecom.GatewayInfo;
import android.telecom.Log;
import android.telecom.Logging.Session;
import android.telecom.ParcelableConference;
import android.telecom.ParcelableConnection;
import android.telecom.PhoneAccountHandle;
import android.telecom.StatusHints;
import android.telecom.VideoProfile;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telecom.IConnectionService;
import com.android.internal.telecom.IConnectionServiceAdapter;
import com.android.internal.telecom.IVideoProvider;
import com.android.internal.telecom.RemoteServiceCallback;
import com.android.server.telecom.CallIdMapper;
import com.android.server.telecom.ConnectionServiceFocusManager;
import com.android.server.telecom.PhoneAccountRegistrar;
import com.android.server.telecom.ServiceBinder;
import com.android.server.telecom.TelecomSystem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class ConnectionServiceWrapper extends ServiceBinder implements ConnectionServiceFocusManager.ConnectionServiceFocus {
    private final Adapter mAdapter;
    private final AppOpsManager mAppOpsManager;
    private ServiceBinder.Binder2 mBinder;
    private final CallIdMapper mCallIdMapper;
    private final CallsManager mCallsManager;
    private ConnectionServiceFocusManager.ConnectionServiceFocusListener mConnSvrFocusListener;
    private final ConnectionServiceRepository mConnectionServiceRepository;
    private final Context mContext;
    private final Map<String, CreateConnectionResponse> mPendingResponses;
    private final PhoneAccountRegistrar mPhoneAccountRegistrar;
    private IConnectionService mServiceInterface;

    private final class Adapter extends IConnectionServiceAdapter.Stub {
        public void onRttInitiationSuccess(String str, Session.Info info) throws RemoteException {
        }

        public void onRttSessionRemotelyTerminated(String str, Session.Info info) throws RemoteException {
        }

        private Adapter() {
        }

        public void handleCreateConnectionComplete(String str, ConnectionRequest connectionRequest, ParcelableConnection parcelableConnection, Session.Info info) {
            Log.startSession(info, "CSW.hCCC", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("handleCreateConnectionComplete %s", str);
                    ConnectionServiceWrapper.this.handleCreateConnectionComplete(str, connectionRequest, parcelableConnection);
                    if (ConnectionServiceWrapper.this.mServiceInterface != null) {
                        ConnectionServiceWrapper.this.logOutgoing("createConnectionComplete %s", str);
                        try {
                            ConnectionServiceWrapper.this.mServiceInterface.createConnectionComplete(str, Log.getExternalSession());
                        } catch (RemoteException unused) {
                        }
                    }
                }
            } finally {
            }
        }

        public void handleCreateConferenceComplete(String str, ConnectionRequest connectionRequest, ParcelableConference parcelableConference, Session.Info info) {
            Log.startSession(info, "CSW.hCCC", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("handleCreateConferenceComplete %s", str);
                    ConnectionServiceWrapper.this.handleCreateConferenceComplete(str, connectionRequest, parcelableConference);
                    if (ConnectionServiceWrapper.this.mServiceInterface != null) {
                        ConnectionServiceWrapper.this.logOutgoing("createConferenceComplete %s", str);
                        try {
                            ConnectionServiceWrapper.this.mServiceInterface.createConferenceComplete(str, Log.getExternalSession());
                        } catch (RemoteException unused) {
                        }
                    }
                }
            } finally {
            }
        }

        public void setActive(String str, Session.Info info) {
            Log.startSession(info, "CSW.sA", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setActive %s", str);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        ConnectionServiceWrapper.this.mCallsManager.markCallAsActive(call);
                    }
                }
            } finally {
            }
        }

        public void setRinging(String str, Session.Info info) {
            Log.startSession(info, "CSW.sR", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setRinging %s", str);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        ConnectionServiceWrapper.this.mCallsManager.markCallAsRinging(call);
                    }
                }
            } finally {
            }
        }

        public void resetConnectionTime(String str, Session.Info info) {
            Log.startSession(info, "CSW.rCCT", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("resetConnectionTime %s", str);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        ConnectionServiceWrapper.this.mCallsManager.resetConnectionTime(call);
                    }
                }
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
                Log.endSession();
            }
        }

        public void setVideoProvider(String str, IVideoProvider iVideoProvider, Session.Info info) {
            Log.startSession(info, "CSW.sVP", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setVideoProvider %s", str);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setVideoProvider(iVideoProvider);
                    }
                }
            } finally {
            }
        }

        public void setDialing(String str, Session.Info info) {
            Log.startSession(info, "CSW.sD", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setDialing %s", str);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        ConnectionServiceWrapper.this.mCallsManager.markCallAsDialing(call);
                    }
                }
            } finally {
            }
        }

        public void setPulling(String str, Session.Info info) {
            Log.startSession(info, "CSW.sP", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setPulling %s", str);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        ConnectionServiceWrapper.this.mCallsManager.markCallAsPulling(call);
                    }
                }
            } finally {
            }
        }

        public void setDisconnected(String str, DisconnectCause disconnectCause, Session.Info info) {
            Log.startSession(info, "CSW.sDc", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setDisconnected %s %s", str, disconnectCause);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    Log.d(this, "disconnect call %s %s", new Object[]{disconnectCause, call});
                    if (call != null) {
                        ConnectionServiceWrapper.this.mCallsManager.markCallAsDisconnected(call, disconnectCause);
                    }
                }
            } finally {
            }
        }

        public void setOnHold(String str, Session.Info info) {
            Log.startSession(info, "CSW.sOH", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setOnHold %s", str);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        ConnectionServiceWrapper.this.mCallsManager.markCallAsOnHold(call);
                    }
                }
            } finally {
            }
        }

        public void setRingbackRequested(String str, boolean z, Session.Info info) {
            Log.startSession(info, "CSW.SRR", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setRingbackRequested %s %b", str, Boolean.valueOf(z));
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setRingbackRequested(z);
                    }
                }
            } finally {
            }
        }

        public void removeCall(String str, Session.Info info) {
            Log.startSession(info, "CSW.rC", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("removeCall %s", str);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        if (call.isAlive()) {
                            ConnectionServiceWrapper.this.mCallsManager.markCallAsDisconnected(call, new DisconnectCause(3));
                        } else {
                            ConnectionServiceWrapper.this.mCallsManager.markCallAsRemoved(call);
                        }
                    }
                }
            } finally {
            }
        }

        public void setConnectionCapabilities(String str, int i, Session.Info info) {
            Log.startSession(info, "CSW.sCC", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setConnectionCapabilities %s %d", str, Integer.valueOf(i));
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setConnectionCapabilities(i);
                    }
                }
            } finally {
            }
        }

        public void setConnectionProperties(String str, int i, Session.Info info) {
            Log.startSession("CSW.sCP", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setConnectionProperties %s %d", str, Integer.valueOf(i));
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setConnectionProperties(i);
                    }
                }
            } finally {
            }
        }

        public void setIsConferenced(String str, String str2, Session.Info info) {
            Log.startSession(info, "CSW.sIC", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setIsConferenced %s %s", str, str2);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        if (str2 == null) {
                            Log.d(this, "unsetting parent: %s", new Object[]{str2});
                            call.setParentAndChildCall(null);
                        } else {
                            call.setParentAndChildCall(ConnectionServiceWrapper.this.mCallIdMapper.getCall(str2));
                        }
                    }
                }
            } finally {
            }
        }

        public void setConferenceMergeFailed(String str, Session.Info info) {
            Log.startSession(info, "CSW.sCMF", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setConferenceMergeFailed %s", str);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.onConnectionEvent("android.telecom.event.CALL_MERGE_FAILED", null);
                    } else {
                        Log.w(this, "setConferenceMergeFailed, unknown call id: %s", new Object[]{str});
                    }
                }
            } finally {
            }
        }

        public void addConferenceCall(String str, ParcelableConference parcelableConference, Session.Info info) {
            Log.startSession(info, "CSW.aCC", ConnectionServiceWrapper.this.mPackageAbbreviation);
            if (parcelableConference.getConnectElapsedTimeMillis() != 0 && ConnectionServiceWrapper.this.mContext.checkCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE") != 0) {
                Log.w(this, "addConferenceCall from caller without permission!", new Object[0]);
                parcelableConference = new ParcelableConference.Builder(parcelableConference.getPhoneAccount(), parcelableConference.getState()).setConnectionCapabilities(parcelableConference.getConnectionCapabilities()).setConnectionProperties(parcelableConference.getConnectionProperties()).setConnectionIds(parcelableConference.getConnectionIds()).setVideoAttributes(parcelableConference.getVideoProvider(), parcelableConference.getVideoState()).setStatusHints(parcelableConference.getStatusHints()).setExtras(parcelableConference.getExtras()).setAddress(parcelableConference.getHandle(), parcelableConference.getHandlePresentation()).setDisconnectCause(parcelableConference.getDisconnectCause()).setRingbackRequested(parcelableConference.isRingbackRequested()).build();
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    if (ConnectionServiceWrapper.this.mCallIdMapper.getCall(str) == null) {
                        ConnectionServiceWrapper.this.logIncoming("addConferenceCall %s %s [%s]", str, parcelableConference, parcelableConference.getConnectionIds());
                        Iterator it = parcelableConference.getConnectionIds().iterator();
                        boolean z = false;
                        while (it.hasNext()) {
                            if (ConnectionServiceWrapper.this.mCallIdMapper.getCall((String) it.next()) != null) {
                                z = true;
                            }
                        }
                        if (!z && parcelableConference.getConnectionIds().size() > 0) {
                            Log.d(this, "Attempting to add a conference with no valid calls", new Object[0]);
                        } else {
                            PhoneAccountHandle phoneAccount = null;
                            if (parcelableConference != null && parcelableConference.getPhoneAccount() != null) {
                                phoneAccount = parcelableConference.getPhoneAccount();
                            }
                            Bundle extras = parcelableConference.getExtras();
                            Call alreadyAddedConnection = ConnectionServiceWrapper.this.mCallsManager.getAlreadyAddedConnection((extras == null || !extras.containsKey("android.telecom.extra.ORIGINAL_CONNECTION_ID")) ? str : extras.getString("android.telecom.extra.ORIGINAL_CONNECTION_ID"));
                            if (alreadyAddedConnection == null || ConnectionServiceWrapper.this.mCallIdMapper.getCall(str) != null) {
                                alreadyAddedConnection = ConnectionServiceWrapper.this.mCallsManager.createConferenceCall(str, phoneAccount, parcelableConference);
                                ConnectionServiceWrapper.this.mCallIdMapper.addCall(alreadyAddedConnection, str);
                                alreadyAddedConnection.setConnectionService(ConnectionServiceWrapper.this);
                            } else {
                                ConnectionServiceWrapper.this.mCallIdMapper.addCall(alreadyAddedConnection, str);
                                alreadyAddedConnection.replaceConnectionService(ConnectionServiceWrapper.this);
                            }
                            Log.d(this, "adding children to conference %s phAcc %s", new Object[]{parcelableConference.getConnectionIds(), phoneAccount});
                            for (String str2 : parcelableConference.getConnectionIds()) {
                                Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str2);
                                Log.d(this, "found child: %s", new Object[]{str2});
                                if (call != null) {
                                    call.setParentAndChildCall(alreadyAddedConnection);
                                }
                            }
                        }
                    } else {
                        Log.w(this, "Attempting to add a conference call using an existing call id %s", new Object[]{str});
                    }
                }
            } finally {
            }
        }

        public void onPostDialWait(String str, String str2, Session.Info info) throws RemoteException {
            Log.startSession(info, "CSW.oPDW", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("onPostDialWait %s %s", str, str2);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.onPostDialWait(str2);
                    }
                }
            } finally {
            }
        }

        public void onPostDialChar(String str, char c, Session.Info info) throws RemoteException {
            Log.startSession(info, "CSW.oPDC", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("onPostDialChar %s %s", str, Character.valueOf(c));
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.onPostDialChar(c);
                    }
                }
            } finally {
            }
        }

        public void queryRemoteConnectionServices(RemoteServiceCallback remoteServiceCallback, String str, Session.Info info) {
            UserHandle callingUserHandle = Binder.getCallingUserHandle();
            Log.startSession(info, "CSW.qRCS", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("queryRemoteConnectionServices callingPackage=" + str, new Object[0]);
                    ConnectionServiceWrapper.this.queryRemoteConnectionServices(callingUserHandle, str, remoteServiceCallback);
                }
            } finally {
            }
        }

        public void setVideoState(String str, int i, Session.Info info) {
            Log.startSession(info, "CSW.sVS", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setVideoState %s %d", str, Integer.valueOf(i));
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setVideoState(i);
                    }
                }
            } finally {
            }
        }

        public void setIsVoipAudioMode(String str, boolean z, Session.Info info) {
            Log.startSession(info, "CSW.sIVAM", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setIsVoipAudioMode %s %b", str, Boolean.valueOf(z));
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setIsVoipAudioMode(z);
                    }
                }
            } finally {
            }
        }

        public void setAudioRoute(String str, int i, String str2, Session.Info info) {
            Log.startSession(info, "CSW.sAR", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setAudioRoute %s %s", str, CallAudioState.audioRouteToString(i));
                    ConnectionServiceWrapper.this.mCallsManager.setAudioRoute(i, str2);
                }
            } finally {
            }
        }

        public void setStatusHints(String str, StatusHints statusHints, Session.Info info) {
            Log.startSession(info, "CSW.sSH", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setStatusHints %s %s", str, statusHints);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setStatusHints(statusHints);
                    }
                }
            } finally {
            }
        }

        public void putExtras(String str, Bundle bundle, Session.Info info) {
            Log.startSession(info, "CSW.pE", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    Bundle.setDefusable(bundle, true);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.putExtras(1, bundle);
                    }
                }
            } finally {
            }
        }

        public void removeExtras(String str, List<String> list, Session.Info info) {
            Log.startSession(info, "CSW.rE", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("removeExtra %s %s", str, list);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.removeExtras(1, list);
                    }
                }
            } finally {
            }
        }

        public void setAddress(String str, Uri uri, int i, Session.Info info) {
            Log.startSession(info, "CSW.sA", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setAddress %s %s %d", str, uri, Integer.valueOf(i));
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setHandle(uri, i);
                    }
                }
            } finally {
            }
        }

        public void setCallerDisplayName(String str, String str2, int i, Session.Info info) {
            Log.startSession(info, "CSW.sCDN", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setCallerDisplayName %s %s %d", str, str2, Integer.valueOf(i));
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setCallerDisplayName(str2, i);
                    }
                }
            } finally {
            }
        }

        public void setConferenceableConnections(String str, List<String> list, Session.Info info) {
            Log.startSession(info, "CSW.sCC", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        ConnectionServiceWrapper.this.logIncoming("setConferenceableConnections %s %s", str, list);
                        ArrayList arrayList = new ArrayList(list.size());
                        Iterator<String> it = list.iterator();
                        while (it.hasNext()) {
                            Call call2 = ConnectionServiceWrapper.this.mCallIdMapper.getCall(it.next());
                            if (call2 != null && call2 != call) {
                                arrayList.add(call2);
                            }
                        }
                        call.setConferenceableCalls(arrayList);
                    }
                }
            } finally {
            }
        }

        public void addExistingConnection(String str, ParcelableConnection parcelableConnection, Session.Info info) {
            Log.startSession(info, "CSW.aEC", ConnectionServiceWrapper.this.mPackageAbbreviation);
            UserHandle callingUserHandle = Binder.getCallingUserHandle();
            PhoneAccountHandle phoneAccount = parcelableConnection.getPhoneAccount();
            if (phoneAccount != null) {
                ConnectionServiceWrapper.this.mAppOpsManager.checkPackage(Binder.getCallingUid(), phoneAccount.getComponentName().getPackageName());
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    PhoneAccountHandle phoneAccountHandle = null;
                    for (PhoneAccountHandle phoneAccountHandle2 : ConnectionServiceWrapper.this.mPhoneAccountRegistrar.getCallCapablePhoneAccounts(null, false, callingUserHandle, 0, 0)) {
                        if (phoneAccountHandle2.equals(phoneAccount)) {
                            phoneAccountHandle = phoneAccountHandle2;
                        }
                    }
                    if (phoneAccountHandle == null && phoneAccount != null) {
                        Iterator<PhoneAccountHandle> it = ConnectionServiceWrapper.this.mPhoneAccountRegistrar.getSimPhoneAccounts(callingUserHandle).iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            PhoneAccountHandle simCallManager = ConnectionServiceWrapper.this.mPhoneAccountRegistrar.getSimCallManager(ConnectionServiceWrapper.this.mPhoneAccountRegistrar.getSubscriptionIdForPhoneAccount(it.next()), callingUserHandle);
                            if (phoneAccount.equals(simCallManager)) {
                                phoneAccountHandle = simCallManager;
                                break;
                            }
                        }
                    }
                    if (phoneAccountHandle != null) {
                        ConnectionServiceWrapper.this.logIncoming("addExistingConnection %s %s", str, parcelableConnection);
                        Bundle extras = parcelableConnection.getExtras();
                        Call alreadyAddedConnection = ConnectionServiceWrapper.this.mCallsManager.getAlreadyAddedConnection((extras == null || !extras.containsKey("android.telecom.extra.ORIGINAL_CONNECTION_ID")) ? str : extras.getString("android.telecom.extra.ORIGINAL_CONNECTION_ID"));
                        if (alreadyAddedConnection != null && ConnectionServiceWrapper.this.mCallIdMapper.getCall(str) == null) {
                            ConnectionServiceWrapper.this.mCallIdMapper.addCall(alreadyAddedConnection, str);
                            alreadyAddedConnection.replaceConnectionService(ConnectionServiceWrapper.this);
                        } else {
                            Call callCreateCallForExistingConnection = ConnectionServiceWrapper.this.mCallsManager.createCallForExistingConnection(str, parcelableConnection);
                            ConnectionServiceWrapper.this.mCallIdMapper.addCall(callCreateCallForExistingConnection, str);
                            callCreateCallForExistingConnection.setConnectionService(ConnectionServiceWrapper.this);
                        }
                    } else {
                        Log.e(this, new RemoteException("The PhoneAccount being used is not currently registered with Telecom."), "Unable to addExistingConnection.", new Object[0]);
                    }
                }
            } finally {
            }
        }

        public void onConnectionEvent(String str, String str2, Bundle bundle, Session.Info info) {
            Log.startSession(info, "CSW.oCE", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    Bundle.setDefusable(bundle, true);
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.onConnectionEvent(str2, bundle);
                    }
                }
            } finally {
            }
        }

        public void onRttInitiationFailure(String str, int i, Session.Info info) throws RemoteException {
            Log.startSession(info, "CSW.oRIF", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.onRttConnectionFailure(i);
                    }
                }
            } finally {
            }
        }

        public void onRemoteRttRequest(String str, Session.Info info) throws RemoteException {
            Log.startSession(info, "CSW.oRRR", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.onRemoteRttRequest();
                    }
                }
            } finally {
            }
        }

        public void onPhoneAccountChanged(String str, PhoneAccountHandle phoneAccountHandle, Session.Info info) throws RemoteException {
            if (phoneAccountHandle != null) {
                ConnectionServiceWrapper.this.mAppOpsManager.checkPackage(Binder.getCallingUid(), phoneAccountHandle.getComponentName().getPackageName());
            }
            Log.startSession(info, "CSW.oPAC", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setTargetPhoneAccount(phoneAccountHandle);
                    }
                }
            } finally {
            }
        }

        public void onConnectionServiceFocusReleased(Session.Info info) throws RemoteException {
            Log.startSession(info, "CSW.oCSFR", ConnectionServiceWrapper.this.mPackageAbbreviation);
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.mConnSvrFocusListener.onConnectionServiceReleased(ConnectionServiceWrapper.this);
                }
            } finally {
            }
        }

        public void setConferenceState(String str, boolean z, Session.Info info) throws RemoteException {
            Log.startSession(info, "CSW.sCS", ConnectionServiceWrapper.this.mPackageAbbreviation);
            if (ConnectionServiceWrapper.this.mContext.checkCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE") != 0) {
                Log.w(this, "setConferenceState from caller without permission.", new Object[0]);
                Log.endSession();
                return;
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setConferenceState(z);
                    }
                }
            } finally {
            }
        }

        public void setCallDirection(String str, int i, Session.Info info) {
            Log.startSession(info, "CSW.sCD", ConnectionServiceWrapper.this.mPackageAbbreviation);
            if (ConnectionServiceWrapper.this.mContext.checkCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE") != 0) {
                Log.w(this, "setCallDirection from caller without permission.", new Object[0]);
                Log.endSession();
                return;
            }
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                synchronized (ConnectionServiceWrapper.this.mLock) {
                    ConnectionServiceWrapper.this.logIncoming("setCallDirection %s %d", str, Integer.valueOf(i));
                    Call call = ConnectionServiceWrapper.this.mCallIdMapper.getCall(str);
                    if (call != null) {
                        call.setCallDirection(Call.getRemappedCallDirection(i));
                    }
                }
            } finally {
            }
        }
    }

    ConnectionServiceWrapper(ComponentName componentName, ConnectionServiceRepository connectionServiceRepository, PhoneAccountRegistrar phoneAccountRegistrar, CallsManager callsManager, Context context, TelecomSystem.SyncRoot syncRoot, UserHandle userHandle) {
        super("android.telecom.ConnectionService", componentName, context, syncRoot, userHandle);
        this.mAdapter = new Adapter();
        this.mCallIdMapper = new CallIdMapper(new CallIdMapper.ICallInfo() { // from class: com.android.server.telecom.-$$Lambda$9jc1HEH8OMWdBU_0br9tImb0e6k
            @Override // com.android.server.telecom.CallIdMapper.ICallInfo
            public final String getCallId(Call call) {
                return call.getConnectionId();
            }
        });
        this.mPendingResponses = new HashMap();
        this.mBinder = new ServiceBinder.Binder2();
        this.mConnectionServiceRepository = connectionServiceRepository;
        phoneAccountRegistrar.addListener(new PhoneAccountRegistrar.Listener(this) { // from class: com.android.server.telecom.ConnectionServiceWrapper.1
        });
        this.mPhoneAccountRegistrar = phoneAccountRegistrar;
        this.mCallsManager = callsManager;
        this.mAppOpsManager = (AppOpsManager) context.getSystemService("appops");
        this.mContext = context;
    }

    private void addConnectionServiceAdapter(IConnectionServiceAdapter iConnectionServiceAdapter) {
        if (isServiceValid("addConnectionServiceAdapter")) {
            try {
                logOutgoing("addConnectionServiceAdapter %s", iConnectionServiceAdapter);
                this.mServiceInterface.addConnectionServiceAdapter(iConnectionServiceAdapter, Log.getExternalSession());
            } catch (RemoteException unused) {
            }
        }
    }

    private void removeConnectionServiceAdapter(IConnectionServiceAdapter iConnectionServiceAdapter) {
        if (isServiceValid("removeConnectionServiceAdapter")) {
            try {
                logOutgoing("removeConnectionServiceAdapter %s", iConnectionServiceAdapter);
                this.mServiceInterface.removeConnectionServiceAdapter(iConnectionServiceAdapter, Log.getExternalSession());
            } catch (RemoteException unused) {
            }
        }
    }

    public void createConference(final Call call, final CreateConnectionResponse createConnectionResponse) {
        Log.d(this, "createConference(%s) via %s.", new Object[]{call, getComponentName()});
        this.mBinder.bind(new ServiceBinder.BindCallback() { // from class: com.android.server.telecom.ConnectionServiceWrapper.2
            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onSuccess() {
                String callId = ConnectionServiceWrapper.this.mCallIdMapper.getCallId(call);
                ConnectionServiceWrapper.this.mPendingResponses.put(callId, createConnectionResponse);
                Bundle intentExtras = call.getIntentExtras();
                Call call2 = call;
                Log.addEvent(call2, "START_CONFERENCE", Log.piiHandle(call2.getHandle()));
                try {
                    ConnectionServiceWrapper.this.mServiceInterface.createConference(call.getConnectionManagerPhoneAccount(), callId, new ConnectionRequest.Builder().setAccountHandle(call.getTargetPhoneAccount()).setAddress(call.getHandle()).setExtras(intentExtras).setVideoState(call.getVideoState()).setTelecomCallId(callId).setShouldShowIncomingCallUi(!ConnectionServiceWrapper.this.mCallsManager.shouldShowSystemIncomingCallUi(call)).setRttPipeFromInCall(call.getInCallToCsRttPipeForCs()).setRttPipeToInCall(call.getCsToInCallRttPipeForCs()).setParticipants(call.getParticipants()).setIsAdhocConferenceCall(call.isAdhocConferenceCall()).build(), call.shouldAttachToExistingConnection(), call.isUnknown(), Log.getExternalSession("cast"));
                } catch (RemoteException e) {
                    Log.e(this, e, "Failure to createConference -- %s", new Object[]{ConnectionServiceWrapper.this.getComponentName()});
                    ((CreateConnectionResponse) ConnectionServiceWrapper.this.mPendingResponses.remove(callId)).handleCreateConferenceFailure(new DisconnectCause(1, e.toString()));
                }
            }

            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onFailure() {
                Log.e(this, new Exception(), "Failure to conference %s", new Object[]{ConnectionServiceWrapper.this.getComponentName()});
                createConnectionResponse.handleCreateConferenceFailure(new DisconnectCause(1));
            }
        }, call);
    }

    @VisibleForTesting
    public void createConnection(final Call call, final CreateConnectionResponse createConnectionResponse) {
        Log.i(this, "createConnection(%s) via %s.", new Object[]{call, getComponentName()});
        this.mBinder.bind(new ServiceBinder.BindCallback() { // from class: com.android.server.telecom.ConnectionServiceWrapper.3
            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onSuccess() {
                String callId = ConnectionServiceWrapper.this.mCallIdMapper.getCallId(call);
                ConnectionServiceWrapper.this.mPendingResponses.put(callId, createConnectionResponse);
                GatewayInfo gatewayInfo = call.getGatewayInfo();
                Bundle intentExtras = call.getIntentExtras();
                if (gatewayInfo != null && gatewayInfo.getGatewayProviderPackageName() != null && gatewayInfo.getOriginalAddress() != null) {
                    intentExtras = (Bundle) intentExtras.clone();
                    intentExtras.putString("android.telecom.extra.GATEWAY_PROVIDER_PACKAGE", gatewayInfo.getGatewayProviderPackageName());
                    intentExtras.putParcelable("android.telecom.extra.GATEWAY_ORIGINAL_ADDRESS", gatewayInfo.getOriginalAddress());
                }
                if (call.isIncoming() && ConnectionServiceWrapper.this.mCallsManager.getEmergencyCallHelper().getLastEmergencyCallTimeMillis() > 0) {
                    if (intentExtras == call.getIntentExtras()) {
                        intentExtras = (Bundle) intentExtras.clone();
                    }
                    intentExtras.putLong("android.telecom.extra.LAST_EMERGENCY_CALLBACK_TIME_MILLIS", ConnectionServiceWrapper.this.mCallsManager.getEmergencyCallHelper().getLastEmergencyCallTimeMillis());
                }
                if (call.isIncoming() && call.getHandoverSourceCall() != null) {
                    intentExtras.putBoolean("android.telecom.extra.IS_HANDOVER", true);
                    intentExtras.putParcelable("android.telecom.extra.HANDOVER_FROM_PHONE_ACCOUNT", call.getHandoverSourceCall().getTargetPhoneAccount());
                }
                Log.addEvent(call, "START_CONNECTION", Log.piiHandle(call.getHandle()) + " via:" + ConnectionServiceWrapper.this.getComponentName().getPackageName());
                try {
                    ConnectionServiceWrapper.this.mServiceInterface.createConnection(call.getConnectionManagerPhoneAccount(), callId, new ConnectionRequest.Builder().setAccountHandle(call.getTargetPhoneAccount()).setAddress(call.getHandle()).setExtras(intentExtras).setVideoState(call.getVideoState()).setTelecomCallId(callId).setShouldShowIncomingCallUi(!ConnectionServiceWrapper.this.mCallsManager.shouldShowSystemIncomingCallUi(call)).setRttPipeFromInCall(call.getInCallToCsRttPipeForCs()).setRttPipeToInCall(call.getCsToInCallRttPipeForCs()).build(), call.shouldAttachToExistingConnection(), call.isUnknown(), Log.getExternalSession("cast"));
                } catch (RemoteException e) {
                    Log.e(this, e, "Failure to createConnection -- %s", new Object[]{ConnectionServiceWrapper.this.getComponentName()});
                    ((CreateConnectionResponse) ConnectionServiceWrapper.this.mPendingResponses.remove(callId)).handleCreateConnectionFailure(new DisconnectCause(1, e.toString()));
                }
            }

            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onFailure() {
                Log.e(this, new Exception(), "Failure to call %s", new Object[]{ConnectionServiceWrapper.this.getComponentName()});
                createConnectionResponse.handleCreateConnectionFailure(new DisconnectCause(1));
            }
        }, call);
    }

    void createConnectionFailed(final Call call) {
        Log.d(this, "createConnectionFailed(%s) via %s.", new Object[]{call, getComponentName()});
        this.mBinder.bind(new ServiceBinder.BindCallback() { // from class: com.android.server.telecom.ConnectionServiceWrapper.4
            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onSuccess() {
                String callId = ConnectionServiceWrapper.this.mCallIdMapper.getCallId(call);
                if (callId == null || !ConnectionServiceWrapper.this.isServiceValid("createConnectionFailed")) {
                    return;
                }
                Call call2 = call;
                Log.addEvent(call2, "CREATE_CONNECTION_FAILED", Log.piiHandle(call2.getHandle()));
                try {
                    ConnectionServiceWrapper.this.logOutgoing("createConnectionFailed %s", callId);
                    ConnectionServiceWrapper.this.mServiceInterface.createConnectionFailed(call.getConnectionManagerPhoneAccount(), callId, new ConnectionRequest(call.getTargetPhoneAccount(), call.getHandle(), call.getIntentExtras(), call.getVideoState(), callId, false), call.isIncoming(), Log.getExternalSession("cast"));
                    call.setDisconnectCause(new DisconnectCause(4));
                    call.disconnect();
                } catch (RemoteException unused) {
                }
            }

            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onFailure() {
                Log.w(this, "onFailure - could not bind to CS for call %s", new Object[]{call.getId()});
            }
        }, call);
    }

    void createConferenceFailed(final Call call) {
        Log.d(this, "createConferenceFailed(%s) via %s.", new Object[]{call, getComponentName()});
        this.mBinder.bind(new ServiceBinder.BindCallback() { // from class: com.android.server.telecom.ConnectionServiceWrapper.5
            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onSuccess() {
                String callId = ConnectionServiceWrapper.this.mCallIdMapper.getCallId(call);
                if (callId == null || !ConnectionServiceWrapper.this.isServiceValid("createConferenceFailed")) {
                    return;
                }
                Call call2 = call;
                Log.addEvent(call2, "CREATE_CONFERENCE_FAILED", Log.piiHandle(call2.getHandle()));
                try {
                    ConnectionServiceWrapper.this.logOutgoing("createConferenceFailed %s", callId);
                    ConnectionServiceWrapper.this.mServiceInterface.createConferenceFailed(call.getConnectionManagerPhoneAccount(), callId, new ConnectionRequest(call.getTargetPhoneAccount(), call.getHandle(), call.getIntentExtras(), call.getVideoState(), callId, false), call.isIncoming(), Log.getExternalSession("cast"));
                    call.setDisconnectCause(new DisconnectCause(4));
                    call.disconnect();
                } catch (RemoteException unused) {
                }
            }

            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onFailure() {
                Log.w(this, "onFailure - could not bind to CS for conf call %s", new Object[]{call.getId()});
            }
        }, call);
    }

    void handoverFailed(final Call call, final int i) {
        Log.d(this, "handoverFailed(%s) via %s.", new Object[]{call, getComponentName()});
        this.mBinder.bind(new ServiceBinder.BindCallback() { // from class: com.android.server.telecom.ConnectionServiceWrapper.6
            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onSuccess() {
                String callId = ConnectionServiceWrapper.this.mCallIdMapper.getCallId(call);
                if (callId == null || !ConnectionServiceWrapper.this.isServiceValid("handoverFailed")) {
                    return;
                }
                Call call2 = call;
                Log.addEvent(call2, "HANDOVER_FAILED", Log.piiHandle(call2.getHandle()));
                try {
                    ConnectionServiceWrapper.this.mServiceInterface.handoverFailed(callId, new ConnectionRequest(call.getTargetPhoneAccount(), call.getHandle(), call.getIntentExtras(), call.getVideoState(), callId, false), i, Log.getExternalSession("cast"));
                } catch (RemoteException unused) {
                }
            }

            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onFailure() {
                Log.w(this, "onFailure - could not bind to CS for call %s", new Object[]{call.getId()});
            }
        }, call);
    }

    void handoverComplete(final Call call) {
        Log.d(this, "handoverComplete(%s) via %s.", new Object[]{call, getComponentName()});
        this.mBinder.bind(new ServiceBinder.BindCallback() { // from class: com.android.server.telecom.ConnectionServiceWrapper.7
            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onSuccess() {
                String callId = ConnectionServiceWrapper.this.mCallIdMapper.getCallId(call);
                if (callId == null || !ConnectionServiceWrapper.this.isServiceValid("handoverComplete")) {
                    return;
                }
                try {
                    ConnectionServiceWrapper.this.mServiceInterface.handoverComplete(callId, Log.getExternalSession("cast"));
                } catch (RemoteException unused) {
                }
            }

            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onFailure() {
                Log.w(this, "onFailure - could not bind to CS for call %s", new Object[]{call.getId()});
            }
        }, call);
    }

    void abort(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId != null && isServiceValid("abort")) {
            try {
                logOutgoing("abort %s", callId);
                this.mServiceInterface.abort(callId, Log.getExternalSession("cast"));
            } catch (RemoteException unused) {
            }
        }
        removeCall(call, new DisconnectCause(2));
    }

    void silence(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("silence")) {
            return;
        }
        try {
            logOutgoing("silence %s", callId);
            this.mServiceInterface.silence(callId, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void hold(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("hold")) {
            return;
        }
        try {
            logOutgoing("hold %s", callId);
            this.mServiceInterface.hold(callId, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void unhold(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("unhold")) {
            return;
        }
        try {
            logOutgoing("unhold %s", callId);
            this.mServiceInterface.unhold(callId, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    @VisibleForTesting
    public void onCallAudioStateChanged(Call call, CallAudioState callAudioState) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("onCallAudioStateChanged")) {
            return;
        }
        try {
            logOutgoing("onCallAudioStateChanged %s %s", callId, callAudioState);
            this.mServiceInterface.onCallAudioStateChanged(callId, callAudioState, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void disconnect(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("disconnect")) {
            return;
        }
        try {
            logOutgoing("disconnect %s", callId);
            this.mServiceInterface.disconnect(callId, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void answer(Call call, int i) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("answer")) {
            return;
        }
        try {
            logOutgoing("answer %s %d", callId, Integer.valueOf(i));
            if (VideoProfile.isAudioOnly(i)) {
                this.mServiceInterface.answer(callId, Log.getExternalSession("cast"));
            } else {
                this.mServiceInterface.answerVideo(callId, i, Log.getExternalSession("cast"));
            }
        } catch (RemoteException unused) {
        }
    }

    void deflect(Call call, Uri uri) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("deflect")) {
            return;
        }
        try {
            logOutgoing("deflect %s", callId);
            this.mServiceInterface.deflect(callId, uri, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void reject(Call call, boolean z, String str) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("reject")) {
            return;
        }
        try {
            logOutgoing("reject %s", callId);
            if (z && call.can(4194304)) {
                this.mServiceInterface.rejectWithMessage(callId, str, Log.getExternalSession("cast"));
            } else {
                this.mServiceInterface.reject(callId, Log.getExternalSession("cast"));
            }
        } catch (RemoteException unused) {
        }
    }

    void rejectWithReason(Call call, int i) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("rejectReason")) {
            return;
        }
        try {
            logOutgoing("rejectReason %s, %d", callId, Integer.valueOf(i));
            this.mServiceInterface.rejectWithReason(callId, i, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void transfer(Call call, Uri uri, boolean z) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("transfer")) {
            return;
        }
        try {
            logOutgoing("transfer %s", callId);
            this.mServiceInterface.transfer(callId, uri, z, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void transfer(Call call, Call call2) {
        String callId = this.mCallIdMapper.getCallId(call);
        String callId2 = this.mCallIdMapper.getCallId(call2);
        if (callId == null || callId2 == null || !isServiceValid("consultativeTransfer")) {
            return;
        }
        try {
            logOutgoing("consultativeTransfer %s", callId);
            this.mServiceInterface.consultativeTransfer(callId, callId2, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void playDtmfTone(Call call, char c) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("playDtmfTone")) {
            return;
        }
        try {
            logOutgoing("playDtmfTone %s %c", callId, Character.valueOf(c));
            this.mServiceInterface.playDtmfTone(callId, c, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void stopDtmfTone(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("stopDtmfTone")) {
            return;
        }
        try {
            logOutgoing("stopDtmfTone %s", callId);
            this.mServiceInterface.stopDtmfTone(callId, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void addCall(Call call) {
        if (this.mCallIdMapper.getCallId(call) == null) {
            this.mCallIdMapper.addCall(call);
        }
    }

    void removeCall(Call call) {
        removeCall(call, new DisconnectCause(1));
    }

    void removeCall(String str, DisconnectCause disconnectCause) {
        CreateConnectionResponse createConnectionResponseRemove = this.mPendingResponses.remove(str);
        if (createConnectionResponseRemove != null) {
            createConnectionResponseRemove.handleCreateConnectionFailure(disconnectCause);
        }
        this.mCallIdMapper.removeCall(str);
    }

    void removeCall(Call call, DisconnectCause disconnectCause) {
        CreateConnectionResponse createConnectionResponseRemove = this.mPendingResponses.remove(this.mCallIdMapper.getCallId(call));
        if (createConnectionResponseRemove != null) {
            createConnectionResponseRemove.handleCreateConnectionFailure(disconnectCause);
        }
        this.mCallIdMapper.removeCall(call);
    }

    void onPostDialContinue(Call call, boolean z) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("onPostDialContinue")) {
            return;
        }
        try {
            logOutgoing("onPostDialContinue %s %b", callId, Boolean.valueOf(z));
            this.mServiceInterface.onPostDialContinue(callId, z, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void conference(Call call, Call call2) {
        String callId = this.mCallIdMapper.getCallId(call);
        String callId2 = this.mCallIdMapper.getCallId(call2);
        if (callId == null || callId2 == null || !isServiceValid("conference")) {
            return;
        }
        try {
            logOutgoing("conference %s %s", callId, callId2);
            this.mServiceInterface.conference(callId, callId2, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void splitFromConference(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("splitFromConference")) {
            return;
        }
        try {
            logOutgoing("splitFromConference %s", callId);
            this.mServiceInterface.splitFromConference(callId, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void mergeConference(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("mergeConference")) {
            return;
        }
        try {
            logOutgoing("mergeConference %s", callId);
            this.mServiceInterface.mergeConference(callId, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void swapConference(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("swapConference")) {
            return;
        }
        try {
            logOutgoing("swapConference %s", callId);
            this.mServiceInterface.swapConference(callId, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void addConferenceParticipants(Call call, List<Uri> list) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("addConferenceParticipants")) {
            return;
        }
        try {
            logOutgoing("addConferenceParticipants %s", callId);
            this.mServiceInterface.addConferenceParticipants(callId, list, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    @VisibleForTesting
    public void pullExternalCall(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("pullExternalCall")) {
            return;
        }
        try {
            logOutgoing("pullExternalCall %s", callId);
            this.mServiceInterface.pullExternalCall(callId, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void sendCallEvent(Call call, String str, Bundle bundle) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("sendCallEvent")) {
            return;
        }
        try {
            logOutgoing("sendCallEvent %s %s", callId, str);
            this.mServiceInterface.sendCallEvent(callId, str, bundle, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void onExtrasChanged(Call call, Bundle bundle) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("onExtrasChanged")) {
            return;
        }
        try {
            logOutgoing("onExtrasChanged %s %s", callId, bundle);
            this.mServiceInterface.onExtrasChanged(callId, bundle, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void startRtt(Call call, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("startRtt")) {
            return;
        }
        try {
            logOutgoing("startRtt: %s %s %s", callId, parcelFileDescriptor, parcelFileDescriptor2);
            this.mServiceInterface.startRtt(callId, parcelFileDescriptor, parcelFileDescriptor2, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void stopRtt(Call call) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("stopRtt")) {
            return;
        }
        try {
            logOutgoing("stopRtt: %s", callId);
            this.mServiceInterface.stopRtt(callId, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    void respondToRttRequest(Call call, ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2) {
        String callId = this.mCallIdMapper.getCallId(call);
        if (callId == null || !isServiceValid("respondToRttRequest")) {
            return;
        }
        try {
            logOutgoing("respondToRttRequest: %s %s %s", callId, parcelFileDescriptor, parcelFileDescriptor2);
            this.mServiceInterface.respondToRttUpgradeRequest(callId, parcelFileDescriptor, parcelFileDescriptor2, Log.getExternalSession("cast"));
        } catch (RemoteException unused) {
        }
    }

    @Override // com.android.server.telecom.ServiceBinder
    protected void setServiceInterface(IBinder iBinder) {
        this.mServiceInterface = IConnectionService.Stub.asInterface(iBinder);
        Log.v(this, "Adding Connection Service Adapter.", new Object[0]);
        addConnectionServiceAdapter(this.mAdapter);
    }

    @Override // com.android.server.telecom.ServiceBinder
    protected void removeServiceInterface() {
        Log.v(this, "Removing Connection Service Adapter.", new Object[0]);
        removeConnectionServiceAdapter(this.mAdapter);
        handleConnectionServiceDeath();
        this.mCallsManager.handleConnectionServiceDeath(this);
        this.mServiceInterface = null;
    }

    @Override // com.android.server.telecom.ConnectionServiceFocusManager.ConnectionServiceFocus
    public void connectionServiceFocusLost() {
        ConnectionServiceFocusManager.ConnectionServiceFocusListener connectionServiceFocusListener = this.mConnSvrFocusListener;
        if (connectionServiceFocusListener != null) {
            connectionServiceFocusListener.onConnectionServiceReleased(this);
        }
        this.mBinder.bind(new ServiceBinder.BindCallback() { // from class: com.android.server.telecom.ConnectionServiceWrapper.8
            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onFailure() {
            }

            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onSuccess() {
                try {
                    ConnectionServiceWrapper.this.mServiceInterface.connectionServiceFocusLost(Log.getExternalSession("cast"));
                } catch (RemoteException unused) {
                    Log.d(this, "failed to inform the focus lost event", new Object[0]);
                }
            }
        }, null);
    }

    @Override // com.android.server.telecom.ConnectionServiceFocusManager.ConnectionServiceFocus
    public void connectionServiceFocusGained() {
        this.mBinder.bind(new ServiceBinder.BindCallback() { // from class: com.android.server.telecom.ConnectionServiceWrapper.9
            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onFailure() {
            }

            @Override // com.android.server.telecom.ServiceBinder.BindCallback
            public void onSuccess() {
                try {
                    ConnectionServiceWrapper.this.mServiceInterface.connectionServiceFocusGained(Log.getExternalSession("cast"));
                } catch (RemoteException unused) {
                    Log.d(this, "failed to inform the focus gained event", new Object[0]);
                }
            }
        }, null);
    }

    @Override // com.android.server.telecom.ConnectionServiceFocusManager.ConnectionServiceFocus
    public void setConnectionServiceFocusListener(ConnectionServiceFocusManager.ConnectionServiceFocusListener connectionServiceFocusListener) {
        this.mConnSvrFocusListener = connectionServiceFocusListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCreateConnectionComplete(String str, ConnectionRequest connectionRequest, ParcelableConnection parcelableConnection) {
        if (parcelableConnection.getState() == 6) {
            Call call = this.mCallIdMapper.getCall(str);
            if (parcelableConnection.getConnectTimeMillis() != 0) {
                call.setConnectTimeMillis(parcelableConnection.getConnectTimeMillis());
            }
            if (call != null) {
                call.clearPostDialDigits();
            }
            removeCall(str, parcelableConnection.getDisconnectCause());
            return;
        }
        if (this.mPendingResponses.containsKey(str)) {
            this.mPendingResponses.remove(str).handleCreateConnectionSuccess(this.mCallIdMapper, parcelableConnection);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCreateConferenceComplete(String str, ConnectionRequest connectionRequest, ParcelableConference parcelableConference) {
        if (parcelableConference.getState() == 6) {
            removeCall(str, parcelableConference.getDisconnectCause());
        } else if (this.mPendingResponses.containsKey(str)) {
            this.mPendingResponses.remove(str).handleCreateConferenceSuccess(this.mCallIdMapper, parcelableConference);
        }
    }

    private void handleConnectionServiceDeath() {
        if (!this.mPendingResponses.isEmpty()) {
            CreateConnectionResponse[] createConnectionResponseArr = (CreateConnectionResponse[]) this.mPendingResponses.values().toArray(new CreateConnectionResponse[this.mPendingResponses.values().size()]);
            this.mPendingResponses.clear();
            for (CreateConnectionResponse createConnectionResponse : createConnectionResponseArr) {
                createConnectionResponse.handleCreateConnectionFailure(new DisconnectCause(1, "CS_DEATH"));
            }
        }
        this.mCallIdMapper.clear();
        ConnectionServiceFocusManager.ConnectionServiceFocusListener connectionServiceFocusListener = this.mConnSvrFocusListener;
        if (connectionServiceFocusListener != null) {
            connectionServiceFocusListener.onConnectionServiceDeath(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logIncoming(String str, Object... objArr) {
        Log.d(this, "CS -> TC[" + Log.getPackageAbbreviation(this.mComponentName) + "]: " + str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logOutgoing(String str, Object... objArr) {
        Log.d(this, "TC -> CS[" + Log.getPackageAbbreviation(this.mComponentName) + "]: " + str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryRemoteConnectionServices(UserHandle userHandle, String str, final RemoteServiceCallback remoteServiceCallback) {
        final Set<ConnectionServiceWrapper> setNewSetFromMap = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
        boolean z = false;
        for (PhoneAccountHandle phoneAccountHandle : this.mPhoneAccountRegistrar.getSimPhoneAccounts(userHandle)) {
            PhoneAccountHandle simCallManager = this.mPhoneAccountRegistrar.getSimCallManager(this.mPhoneAccountRegistrar.getSubscriptionIdForPhoneAccount(phoneAccountHandle), userHandle);
            if (simCallManager == null || !simCallManager.getComponentName().getPackageName().equals(str)) {
                Log.v(this, "queryRemoteConnectionServices: callingPackage=%s skipped; doesn't match mgr %s for tfa %s", new Object[]{str, simCallManager, phoneAccountHandle});
            } else {
                z = true;
            }
            ConnectionServiceWrapper service = this.mConnectionServiceRepository.getService(phoneAccountHandle.getComponentName(), phoneAccountHandle.getUserHandle());
            if (service == null || service == this) {
                Log.w(this, "call provider also detected as SIM call manager: " + service, new Object[0]);
            } else {
                setNewSetFromMap.add(service);
            }
        }
        if (!z) {
            Log.d(this, "queryRemoteConnectionServices: none; not sim call mgr.", new Object[0]);
            noRemoteServices(remoteServiceCallback);
            return;
        }
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        Log.i(this, "queryRemoteConnectionServices, simServices = %s", new Object[]{setNewSetFromMap});
        for (final ConnectionServiceWrapper connectionServiceWrapper : setNewSetFromMap) {
            connectionServiceWrapper.mBinder.bind(new ServiceBinder.BindCallback() { // from class: com.android.server.telecom.ConnectionServiceWrapper.10
                @Override // com.android.server.telecom.ServiceBinder.BindCallback
                public void onSuccess() {
                    Log.d(this, "queryRemoteConnectionServices: Adding simService %s", new Object[]{connectionServiceWrapper.getComponentName()});
                    if (connectionServiceWrapper.mServiceInterface == null) {
                        Log.w(this, "queryRemoteConnectionServices: simService %s died - Skipping.", new Object[]{connectionServiceWrapper.getComponentName()});
                    } else {
                        arrayList.add(connectionServiceWrapper.getComponentName());
                        arrayList2.add(connectionServiceWrapper.mServiceInterface.asBinder());
                    }
                    maybeComplete();
                }

                @Override // com.android.server.telecom.ServiceBinder.BindCallback
                public void onFailure() {
                    Log.d(this, "queryRemoteConnectionServices: Failed simService %s", new Object[]{connectionServiceWrapper.getComponentName()});
                    ConnectionServiceWrapper.this.noRemoteServices(remoteServiceCallback);
                }

                private void maybeComplete() {
                    if (arrayList.size() == setNewSetFromMap.size()) {
                        ConnectionServiceWrapper.this.setRemoteServices(remoteServiceCallback, arrayList, arrayList2);
                    }
                }
            }, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRemoteServices(RemoteServiceCallback remoteServiceCallback, List<ComponentName> list, List<IBinder> list2) {
        try {
            remoteServiceCallback.onResult(list, list2);
        } catch (RemoteException e) {
            Log.e(this, e, "setRemoteServices: Contacting ConnectionService %s", new Object[]{getComponentName()});
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void noRemoteServices(RemoteServiceCallback remoteServiceCallback) {
        List<ComponentName> list = Collections.EMPTY_LIST;
        setRemoteServices(remoteServiceCallback, list, list);
    }

    public String toString() {
        return "[ConnectionServiceWrapper componentName=" + this.mComponentName + "]";
    }
}
