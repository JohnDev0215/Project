package com.android.server.telecom;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.provider.ContactsContract;
import android.telecom.CallerInfo;
import android.telecom.Connection;
import android.telecom.DisconnectCause;
import android.telecom.GatewayInfo;
import android.telecom.Log;
import android.telecom.Logging.EventManager;
import android.telecom.ParcelableConference;
import android.telecom.ParcelableConnection;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.Response;
import android.telecom.StatusHints;
import android.telecom.VideoProfile;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.telephony.emergency.EmergencyNumber;
import android.text.TextUtils;
import com.android.internal.annotations.VisibleForTesting;
import com.android.internal.telecom.IVideoProvider;
import com.android.internal.util.Preconditions;
import com.android.server.telecom.Analytics;
import com.android.server.telecom.CallerInfoLookupHelper;
import com.android.server.telecom.ConnectionServiceFocusManager;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.ui.ToastFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class Call implements CreateConnectionResponse, EventManager.Loggable, ConnectionServiceFocusManager.CallFocus {
    private Analytics.CallInfo mAnalytics;
    private CharSequence mAudioProcessingRequestingApp;
    private long mCallDataUsage;
    private int mCallDirection;
    private String mCallerDisplayName;
    private int mCallerDisplayNamePresentation;
    private CallerInfo mCallerInfo;
    private final CallerInfoLookupHelper.OnQueryCompleteListener mCallerInfoQueryListener;
    private int mCallerNumberVerificationStatus;
    private final CallsManager mCallsManager;
    private List<String> mCannedSmsResponses;
    private boolean mCannedSmsResponsesLoadingStarted;
    private List<Call> mChildCalls;
    private final ClockProxy mClockProxy;
    private Call mConferenceLevelActiveCall;
    private final List<Call> mConferenceableCalls;
    private long mConnectElapsedTimeMillis;
    private long mConnectTimeMillis;
    private int mConnectionCapabilities;
    private String mConnectionId;
    private PhoneAccountHandle mConnectionManagerPhoneAccountHandle;
    private int mConnectionProperties;
    private ConnectionServiceWrapper mConnectionService;
    private ParcelFileDescriptor[] mConnectionServiceToInCallStreams;
    private final Context mContext;
    private CreateConnectionProcessor mCreateConnectionProcessor;
    private long mCreationTimeMillis;
    private boolean mDidRequestToStartWithRtt;
    private DisconnectCause mDisconnectCause;
    private long mDisconnectElapsedTimeMillis;
    private long mDisconnectTimeMillis;
    private Bundle mExtras;
    private GatewayInfo mGatewayInfo;
    private boolean mHadChildren;
    private Uri mHandle;
    private int mHandlePresentation;
    private final Handler mHandler;
    private Call mHandoverDestinationCall;
    private Call mHandoverSourceCall;
    private int mHandoverState;
    private boolean mHasGoneActiveBefore;
    private final String mId;
    private ParcelFileDescriptor[] mInCallToConnectionServiceStreams;
    private UserHandle mInitiatingUser;
    private Bundle mIntentExtras;
    private boolean mIsConference;
    private boolean mIsDisconnectingChildCall;
    private boolean mIsEmergencyCall;
    private boolean mIsLocallyDisconnecting;
    private boolean mIsPullExternalCallSupported;
    private boolean mIsRemotelyHeld;
    private boolean mIsSelfManaged;
    private boolean mIsTestEmergencyCall;
    private boolean mIsVideoCallingSupportedByPhoneAccount;
    private boolean mIsVoipAudioMode;
    private boolean mIsWorkCall;
    private final Set<Listener> mListeners;
    private final TelecomSystem.SyncRoot mLock;
    private String mOriginalConnectionId;
    private DisconnectCause mOverrideDisconnectCause;
    private Call mParentCall;
    private List<Uri> mParticipants;
    private int mPendingRttRequestId;
    private PhoneNumberUtilsAdapter mPhoneNumberUtilsAdapter;
    private char mPlayingDtmfTone;
    private String mPostCallPackageName;
    private String mPostDialDigits;
    private PhoneAccountHandle mRemotePhoneAccountHandle;
    private final ConnectionServiceRepository mRepository;
    private boolean mRingbackRequested;
    private int mRttMode;
    private final boolean mShouldAttachToExistingConnection;
    private boolean mSilentRingingRequested;
    private boolean mSpeakerphoneOn;
    private int mState;
    private StatusHints mStatusHints;
    private int mSupportedAudioRoutes;
    private PhoneAccountHandle mTargetPhoneAccountHandle;
    private final ToastFactory mToastFactory;
    private boolean mUseCallRecordingTone;
    private String mViaNumber;
    private VideoProviderProxy mVideoProviderProxy;
    private int mVideoState;
    private int mVideoStateHistory;
    private boolean mWasConferencePreviouslyMerged;
    private boolean mWasEverRtt;
    private boolean mWasHighDefAudio;
    private boolean mWasVolte;
    private boolean mWasWifi;

    @VisibleForTesting
    public interface Listener {
        void onCallDirectionChanged(Call call);

        void onCallHoldFailed(Call call);

        void onCallSwitchFailed(Call call);

        void onCallerDisplayNameChanged(Call call);

        void onCallerInfoChanged(Call call);

        boolean onCanceledViaNewOutgoingCallBroadcast(Call call, long j);

        void onCannedSmsResponsesLoaded(Call call);

        void onCdmaConferenceSwap(Call call);

        void onChildrenChanged(Call call);

        void onConferenceStateChanged(Call call, boolean z);

        void onConferenceableCallsChanged(Call call);

        void onConnectionCapabilitiesChanged(Call call);

        void onConnectionEvent(Call call, String str, Bundle bundle);

        void onConnectionManagerPhoneAccountChanged(Call call);

        void onConnectionPropertiesChanged(Call call, boolean z);

        void onExternalCallChanged(Call call, boolean z);

        void onExtrasChanged(Call call, int i, Bundle bundle);

        void onExtrasRemoved(Call call, int i, List<String> list);

        void onFailedIncomingCall(Call call);

        void onFailedOutgoingCall(Call call, DisconnectCause disconnectCause);

        void onFailedUnknownCall(Call call);

        void onHandleChanged(Call call);

        void onHandoverComplete(Call call);

        void onHandoverFailed(Call call, int i);

        void onHandoverRequested(Call call, PhoneAccountHandle phoneAccountHandle, int i, Bundle bundle, boolean z);

        void onHoldToneRequested(Call call);

        void onIsVoipAudioModeChanged(Call call);

        void onParentChanged(Call call);

        void onPostDialChar(Call call, char c);

        void onPostDialWait(Call call, String str);

        void onRemoteRttRequest(Call call, int i);

        void onRingbackRequested(Call call, boolean z);

        void onRttInitiationFailure(Call call, int i);

        void onStatusHintsChanged(Call call);

        void onSuccessfulIncomingCall(Call call);

        void onSuccessfulOutgoingCall(Call call, int i);

        void onSuccessfulUnknownCall(Call call, int i);

        void onTargetPhoneAccountChanged(Call call);

        void onVideoCallProviderChanged(Call call);

        void onVideoStateChanged(Call call, int i, int i2);
    }

    public static abstract class ListenerBase implements Listener {
        @Override // com.android.server.telecom.Call.Listener
        public void onCallDirectionChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onCallHoldFailed(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onCallSwitchFailed(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onCallerDisplayNameChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onCallerInfoChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public boolean onCanceledViaNewOutgoingCallBroadcast(Call call, long j) {
            return false;
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onCannedSmsResponsesLoaded(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onCdmaConferenceSwap(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onChildrenChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onConferenceStateChanged(Call call, boolean z) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onConferenceableCallsChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onConnectionCapabilitiesChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onConnectionEvent(Call call, String str, Bundle bundle) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onConnectionManagerPhoneAccountChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onConnectionPropertiesChanged(Call call, boolean z) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onExternalCallChanged(Call call, boolean z) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onExtrasChanged(Call call, int i, Bundle bundle) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onExtrasRemoved(Call call, int i, List<String> list) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onFailedIncomingCall(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onFailedOutgoingCall(Call call, DisconnectCause disconnectCause) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onFailedUnknownCall(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onHandleChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onHandoverComplete(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onHandoverFailed(Call call, int i) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onHandoverRequested(Call call, PhoneAccountHandle phoneAccountHandle, int i, Bundle bundle, boolean z) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onHoldToneRequested(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onIsVoipAudioModeChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onParentChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onPostDialChar(Call call, char c) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onPostDialWait(Call call, String str) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onRemoteRttRequest(Call call, int i) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onRingbackRequested(Call call, boolean z) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onRttInitiationFailure(Call call, int i) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onStatusHintsChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onSuccessfulIncomingCall(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onSuccessfulOutgoingCall(Call call, int i) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onSuccessfulUnknownCall(Call call, int i) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onTargetPhoneAccountChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onVideoCallProviderChanged(Call call) {
        }

        @Override // com.android.server.telecom.Call.Listener
        public void onVideoStateChanged(Call call, int i, int i2) {
        }
    }

    public static int getRemappedCallDirection(int i) {
        if (i != 0) {
            return i != 1 ? 0 : 1;
        }
        return 2;
    }

    static int getStateFromConnectionState(int i) {
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 4;
        }
        int i2 = 3;
        if (i != 3) {
            i2 = 5;
            if (i != 4) {
                if (i != 5) {
                    return i != 7 ? 7 : 10;
                }
                return 6;
            }
        }
        return i2;
    }

    public void setIsUsingCallFiltering(boolean z) {
    }

    public void setNewOutgoingCallIntentBroadcastIsDone() {
    }

    public void setOriginalCallIntent(Intent intent) {
    }

    public Call(String str, Context context, CallsManager callsManager, TelecomSystem.SyncRoot syncRoot, ConnectionServiceRepository connectionServiceRepository, PhoneNumberUtilsAdapter phoneNumberUtilsAdapter, Uri uri, GatewayInfo gatewayInfo, PhoneAccountHandle phoneAccountHandle, PhoneAccountHandle phoneAccountHandle2, int i, boolean z, boolean z2, ClockProxy clockProxy, ToastFactory toastFactory) {
        this(str, context, callsManager, syncRoot, connectionServiceRepository, phoneNumberUtilsAdapter, uri, null, gatewayInfo, phoneAccountHandle, phoneAccountHandle2, i, z, z2, clockProxy, toastFactory);
    }

    public Call(String str, Context context, CallsManager callsManager, TelecomSystem.SyncRoot syncRoot, ConnectionServiceRepository connectionServiceRepository, PhoneNumberUtilsAdapter phoneNumberUtilsAdapter, Uri uri, List<Uri> list, GatewayInfo gatewayInfo, PhoneAccountHandle phoneAccountHandle, PhoneAccountHandle phoneAccountHandle2, int i, boolean z, boolean z2, ClockProxy clockProxy, ToastFactory toastFactory) {
        this.mCallerInfoQueryListener = new CallerInfoLookupHelper.OnQueryCompleteListener() { // from class: com.android.server.telecom.Call.1
            @Override // com.android.server.telecom.CallerInfoLookupHelper.OnQueryCompleteListener
            public void onCallerInfoQueryComplete(Uri uri2, CallerInfo callerInfo) {
                synchronized (Call.this.mLock) {
                    Call.this.setCallerInfo(uri2, callerInfo);
                }
            }

            @Override // com.android.server.telecom.CallerInfoLookupHelper.OnQueryCompleteListener
            public void onContactPhotoQueryComplete(Uri uri2, CallerInfo callerInfo) {
                synchronized (Call.this.mLock) {
                    Call.this.setCallerInfo(uri2, callerInfo);
                }
            }
        };
        this.mViaNumber = "";
        this.mConnectTimeMillis = 0L;
        this.mConnectElapsedTimeMillis = 0L;
        this.mDisconnectTimeMillis = 0L;
        this.mDisconnectElapsedTimeMillis = 0L;
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mConferenceableCalls = new ArrayList();
        this.mIsDisconnectingChildCall = false;
        this.mDisconnectCause = new DisconnectCause(0);
        this.mOverrideDisconnectCause = new DisconnectCause(0);
        this.mIntentExtras = new Bundle();
        this.mListeners = Collections.newSetFromMap(new ConcurrentHashMap(8, 0.9f, 1));
        this.mRingbackRequested = false;
        this.mSilentRingingRequested = false;
        this.mSupportedAudioRoutes = 15;
        this.mIsConference = false;
        this.mHadChildren = false;
        this.mParentCall = null;
        this.mChildCalls = new LinkedList();
        this.mCannedSmsResponses = Collections.EMPTY_LIST;
        this.mCannedSmsResponsesLoadingStarted = false;
        this.mAnalytics = new Analytics.CallInfo();
        this.mWasConferencePreviouslyMerged = false;
        this.mWasHighDefAudio = false;
        this.mWasWifi = false;
        this.mWasVolte = false;
        this.mConferenceLevelActiveCall = null;
        this.mIsLocallyDisconnecting = false;
        this.mCallDataUsage = -1L;
        this.mIsRemotelyHeld = false;
        this.mIsSelfManaged = false;
        this.mIsVideoCallingSupportedByPhoneAccount = false;
        this.mIsPullExternalCallSupported = true;
        this.mDidRequestToStartWithRtt = false;
        this.mWasEverRtt = false;
        this.mPendingRttRequestId = -1;
        this.mHandoverDestinationCall = null;
        this.mHandoverSourceCall = null;
        this.mAudioProcessingRequestingApp = null;
        this.mHandoverState = 1;
        this.mHasGoneActiveBefore = false;
        this.mId = str;
        this.mConnectionId = str;
        this.mState = (!z2 || i == 2 || i == 1) ? 0 : 5;
        this.mContext = context;
        this.mCallsManager = callsManager;
        this.mLock = syncRoot;
        this.mRepository = connectionServiceRepository;
        this.mPhoneNumberUtilsAdapter = phoneNumberUtilsAdapter;
        setHandle(uri);
        this.mParticipants = list;
        this.mPostDialDigits = uri != null ? PhoneNumberUtils.extractPostDialPortion(uri.getSchemeSpecificPart()) : "";
        this.mGatewayInfo = gatewayInfo;
        setConnectionManagerPhoneAccount(phoneAccountHandle);
        setTargetPhoneAccount(phoneAccountHandle2);
        this.mCallDirection = i;
        this.mIsConference = z2;
        this.mShouldAttachToExistingConnection = z || i == 2;
        maybeLoadCannedSmsResponses();
        this.mClockProxy = clockProxy;
        this.mToastFactory = toastFactory;
        this.mCreationTimeMillis = clockProxy.currentTimeMillis();
    }

    Call(String str, Context context, CallsManager callsManager, TelecomSystem.SyncRoot syncRoot, ConnectionServiceRepository connectionServiceRepository, PhoneNumberUtilsAdapter phoneNumberUtilsAdapter, Uri uri, GatewayInfo gatewayInfo, PhoneAccountHandle phoneAccountHandle, PhoneAccountHandle phoneAccountHandle2, int i, boolean z, boolean z2, long j, long j2, ClockProxy clockProxy, ToastFactory toastFactory) {
        this(str, context, callsManager, syncRoot, connectionServiceRepository, phoneNumberUtilsAdapter, uri, gatewayInfo, phoneAccountHandle, phoneAccountHandle2, i, z, z2, clockProxy, toastFactory);
        this.mConnectTimeMillis = j;
        this.mConnectElapsedTimeMillis = j2;
        this.mAnalytics.setCallStartTime(j);
    }

    public void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        if (listener != null) {
            this.mListeners.remove(listener);
        }
    }

    public void initAnalytics() {
        initAnalytics(null);
    }

    public void initAnalytics(String str) {
        int i = this.mCallDirection;
        Analytics.CallInfo callInfoInitiateCallAnalytics = Analytics.initiateCallAnalytics(this.mId, i != 1 ? i != 2 ? 0 : 1 : 2);
        this.mAnalytics = callInfoInitiateCallAnalytics;
        callInfoInitiateCallAnalytics.setCallIsEmergency(this.mIsEmergencyCall);
        Log.addEvent(this, "CREATED", str);
    }

    public Analytics.CallInfo getAnalytics() {
        return this.mAnalytics;
    }

    public void destroy() {
        CallerInfo callerInfo = this.mCallerInfo;
        if (callerInfo != null) {
            callerInfo.cachedPhotoIcon = null;
            callerInfo.cachedPhoto = null;
        }
        closeRttStreams();
        Log.addEvent(this, "DESTROYED");
    }

    private void closeRttStreams() {
        ParcelFileDescriptor[] parcelFileDescriptorArr = this.mConnectionServiceToInCallStreams;
        if (parcelFileDescriptorArr != null) {
            for (ParcelFileDescriptor parcelFileDescriptor : parcelFileDescriptorArr) {
                if (parcelFileDescriptor != null) {
                    try {
                        parcelFileDescriptor.close();
                    } catch (IOException unused) {
                    }
                }
            }
        }
        ParcelFileDescriptor[] parcelFileDescriptorArr2 = this.mInCallToConnectionServiceStreams;
        if (parcelFileDescriptorArr2 != null) {
            for (ParcelFileDescriptor parcelFileDescriptor2 : parcelFileDescriptorArr2) {
                if (parcelFileDescriptor2 != null) {
                    try {
                        parcelFileDescriptor2.close();
                    } catch (IOException unused2) {
                    }
                }
            }
        }
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[10];
        objArr[0] = this.mId;
        objArr[1] = CallState.toString(getParcelableCallState());
        objArr[2] = getTargetPhoneAccount();
        objArr[3] = getConnectionManagerPhoneAccount();
        objArr[4] = Log.piiHandle(this.mHandle);
        objArr[5] = getVideoStateDescription(getVideoState());
        objArr[6] = Integer.valueOf(getChildCalls().size());
        objArr[7] = Boolean.valueOf(getParentCall() != null);
        objArr[8] = Connection.capabilitiesToStringShort(getConnectionCapabilities());
        objArr[9] = Connection.propertiesToStringShort(getConnectionProperties());
        return String.format(locale, "[Call id=%s, state=%s, tpac=%s, cmgr=%s, handle=%s, vidst=%s, childs(%d), has_parent(%b), cap=%s, prop=%s]", objArr);
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        if (isSelfManaged()) {
            sb.append("SelfMgd Call");
        } else if (isExternalCall()) {
            sb.append("External Call");
        } else {
            sb.append("Call");
        }
        sb.append(getId());
        sb.append(" [");
        sb.append(SimpleDateFormat.getDateTimeInstance().format(new Date(getCreationTimeMillis())));
        sb.append("]");
        sb.append(isIncoming() ? "(MT - incoming)" : "(MO - outgoing)");
        sb.append("\n\t");
        PhoneAccountHandle targetPhoneAccount = getTargetPhoneAccount();
        PhoneAccountHandle remotePhoneAccountHandle = getRemotePhoneAccountHandle();
        PhoneAccountHandle connectionManagerPhoneAccount = getConnectionManagerPhoneAccount();
        PhoneAccountHandle delegatePhoneAccountHandle = getDelegatePhoneAccountHandle();
        boolean z = targetPhoneAccount != null && targetPhoneAccount.equals(remotePhoneAccountHandle);
        if (Objects.equals(delegatePhoneAccountHandle, targetPhoneAccount)) {
            sb.append(">>>");
        }
        sb.append("Target");
        sb.append(" PhoneAccount: ");
        if (targetPhoneAccount != null) {
            sb.append(targetPhoneAccount);
            sb.append(" (");
            sb.append(getTargetPhoneAccountLabel());
            sb.append(")");
            if (z) {
                sb.append("(remote)");
            }
        } else {
            sb.append("not set");
        }
        if (!z && remotePhoneAccountHandle != null) {
            if (delegatePhoneAccountHandle.equals(remotePhoneAccountHandle)) {
                sb.append("\n\t>>>Remote PhoneAccount: ");
            } else {
                sb.append("\n\tRemote PhoneAccount: ");
            }
            sb.append(remotePhoneAccountHandle);
        }
        if (connectionManagerPhoneAccount != null) {
            if (delegatePhoneAccountHandle.equals(connectionManagerPhoneAccount)) {
                sb.append("\n\t>>>Conn mgr: ");
            } else {
                sb.append("\n\tConn mgr: ");
            }
            sb.append(connectionManagerPhoneAccount);
        }
        sb.append("\n\tTo address: ");
        sb.append(Log.piiHandle(getHandle()));
        if (isIncoming()) {
            int i = this.mCallerNumberVerificationStatus;
            if (i == 0) {
                sb.append(" Verstat: not");
            } else if (i == 1) {
                sb.append(" Verstat: pass");
            } else if (i == 2) {
                sb.append(" Verstat: fail");
            }
        }
        sb.append(" Presentation: ");
        int handlePresentation = getHandlePresentation();
        if (handlePresentation == 1) {
            sb.append("Allowed");
        } else if (handlePresentation == 2) {
            sb.append("Restricted");
        } else if (handlePresentation == 3) {
            sb.append("Unknown");
        } else if (handlePresentation == 4) {
            sb.append("Payphone");
        } else {
            sb.append("<undefined>");
        }
        sb.append("\n");
        return sb.toString();
    }

    private String getVideoStateDescription(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("A");
        if (VideoProfile.isTransmissionEnabled(i)) {
            sb.append("T");
        }
        if (VideoProfile.isReceptionEnabled(i)) {
            sb.append("R");
        }
        if (VideoProfile.isPaused(i)) {
            sb.append("P");
        }
        return sb.toString();
    }

    @Override // com.android.server.telecom.ConnectionServiceFocusManager.CallFocus
    public ConnectionServiceFocusManager.ConnectionServiceFocus getConnectionServiceWrapper() {
        return this.mConnectionService;
    }

    @Override // com.android.server.telecom.ConnectionServiceFocusManager.CallFocus
    @VisibleForTesting
    public int getState() {
        return this.mState;
    }

    public int getParcelableCallState() {
        if (!isLocallyDisconnecting() || this.mState == 7) {
            return this.mState;
        }
        return 9;
    }

    @Override // com.android.server.telecom.ConnectionServiceFocusManager.CallFocus
    public boolean isFocusable() {
        return ((getParentCall() != null) || isExternalCall()) ? false : true;
    }

    private boolean shouldContinueProcessingAfterDisconnect() {
        CreateConnectionProcessor createConnectionProcessor;
        DisconnectCause disconnectCause;
        if (CreateConnectionTimeout.isCallBeingPlaced(this) && isEmergencyCall() && (createConnectionProcessor = this.mCreateConnectionProcessor) != null && createConnectionProcessor.isProcessingComplete() && this.mCreateConnectionProcessor.hasMorePhoneAccounts() && (disconnectCause = this.mDisconnectCause) != null) {
            return disconnectCause.getCode() == 1 || this.mCreateConnectionProcessor.isCallTimedOut();
        }
        return false;
    }

    public String getId() {
        return this.mId;
    }

    public String getConnectionId() {
        if (this.mCreateConnectionProcessor != null) {
            String str = this.mId + "_" + String.valueOf(this.mCreateConnectionProcessor.getConnectionAttempt());
            this.mConnectionId = str;
            return str;
        }
        return this.mConnectionId;
    }

    public boolean setState(int i, String str) {
        String str2;
        DisconnectCause disconnectCause;
        int i2;
        int i3 = this.mState;
        if (i3 != i) {
            Log.v(this, "setState %s -> %s", new Object[]{CallState.toString(i3), CallState.toString(i)});
            if (i == 7 && shouldContinueProcessingAfterDisconnect()) {
                Log.w(this, "continuing processing disconnected call with another service", new Object[0]);
                this.mCreateConnectionProcessor.continueProcessingIfPossible(this, this.mDisconnectCause);
                return false;
            }
            if (i == 11 && (i2 = this.mState) == 5) {
                Log.w(this, "setState %s -> %s; call already active.", new Object[]{CallState.toString(i2), CallState.toString(i)});
                return false;
            }
            updateVideoHistoryViaState(this.mState, i);
            this.mState = i;
            maybeLoadCannedSmsResponses();
            int i4 = this.mState;
            if (i4 == 5 || i4 == 6) {
                if (this.mConnectTimeMillis == 0) {
                    this.mConnectTimeMillis = this.mClockProxy.currentTimeMillis();
                    this.mConnectElapsedTimeMillis = this.mClockProxy.elapsedRealtime();
                    this.mAnalytics.setCallStartTime(this.mConnectTimeMillis);
                }
                this.mDisconnectTimeMillis = 0L;
                this.mDisconnectElapsedTimeMillis = 0L;
                this.mHasGoneActiveBefore = true;
            } else if (i4 == 7) {
                this.mDisconnectTimeMillis = this.mClockProxy.currentTimeMillis();
                this.mDisconnectElapsedTimeMillis = this.mClockProxy.elapsedRealtime();
                this.mAnalytics.setCallEndTime(this.mDisconnectTimeMillis);
                setLocallyDisconnecting(false);
                fixParentAfterDisconnect();
            }
            DisconnectCause disconnectCause2 = null;
            String str3 = null;
            disconnectCause2 = null;
            disconnectCause2 = null;
            disconnectCause2 = null;
            disconnectCause2 = null;
            disconnectCause2 = null;
            disconnectCause2 = null;
            disconnectCause2 = null;
            disconnectCause2 = null;
            switch (i) {
                case 1:
                    str2 = "SET_CONNECTING";
                    String str4 = str2;
                    disconnectCause = disconnectCause2;
                    str3 = str4;
                    break;
                case HeadsetMediaButton.LONG_PRESS /* 2 */:
                    str2 = "SET_SELECT_PHONE_ACCOUNT";
                    String str42 = str2;
                    disconnectCause = disconnectCause2;
                    str3 = str42;
                    break;
                case 3:
                    str2 = "SET_DIALING";
                    String str422 = str2;
                    disconnectCause = disconnectCause2;
                    str3 = str422;
                    break;
                case 4:
                    str2 = "SET_RINGING";
                    String str4222 = str2;
                    disconnectCause = disconnectCause2;
                    str3 = str4222;
                    break;
                case 5:
                    str2 = "SET_ACTIVE";
                    String str42222 = str2;
                    disconnectCause = disconnectCause2;
                    str3 = str42222;
                    break;
                case 6:
                    str2 = "SET_HOLD";
                    String str422222 = str2;
                    disconnectCause = disconnectCause2;
                    str3 = str422222;
                    break;
                case 7:
                    disconnectCause2 = getDisconnectCause();
                    str2 = "SET_DISCONNECTED";
                    String str4222222 = str2;
                    disconnectCause = disconnectCause2;
                    str3 = str4222222;
                    break;
                case 8:
                default:
                    disconnectCause = null;
                    break;
                case PhoneAccountRegistrar.EXPECTED_STATE_VERSION /* 9 */:
                    str2 = "SET_DISCONNECTING";
                    String str42222222 = str2;
                    disconnectCause = disconnectCause2;
                    str3 = str42222222;
                    break;
                case 10:
                    str2 = "SET_PULLING";
                    String str422222222 = str2;
                    disconnectCause = disconnectCause2;
                    str3 = str422222222;
                    break;
                case 11:
                    str2 = "SET_ANSWERED";
                    String str4222222222 = str2;
                    disconnectCause = disconnectCause2;
                    str3 = str4222222222;
                    break;
            }
            if (str3 != null) {
                if (disconnectCause != null) {
                    if (str == null) {
                        str = disconnectCause.toString();
                    } else {
                        str = str + "> " + disconnectCause;
                    }
                }
                Log.addEvent(this, str3, str);
            }
            TelecomStatsLog.write(61, i, i == 7 ? getDisconnectCause().getCode() : 0, isSelfManaged(), isExternalCall());
        }
        return true;
    }

    void setRingbackRequested(boolean z) {
        this.mRingbackRequested = z;
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onRingbackRequested(this, this.mRingbackRequested);
        }
    }

    boolean isRingbackRequested() {
        return this.mRingbackRequested;
    }

    public void setSilentRingingRequested(boolean z) {
        this.mSilentRingingRequested = z;
        Bundle bundle = new Bundle();
        bundle.putBoolean("android.telecom.extra.SILENT_RINGING_REQUESTED", z);
        putExtras(1, bundle);
    }

    public boolean isSilentRingingRequested() {
        return this.mSilentRingingRequested;
    }

    @VisibleForTesting
    public boolean isConference() {
        return this.mIsConference;
    }

    public boolean hadChildren() {
        return this.mHadChildren;
    }

    public Uri getHandle() {
        return this.mHandle;
    }

    public List<Uri> getParticipants() {
        return this.mParticipants;
    }

    public boolean isAdhocConferenceCall() {
        int i;
        return this.mIsConference && ((i = this.mCallDirection) == 1 || i == 2);
    }

    public String getPostDialDigits() {
        return this.mPostDialDigits;
    }

    public void clearPostDialDigits() {
        this.mPostDialDigits = null;
    }

    public String getViaNumber() {
        return this.mViaNumber;
    }

    public void setViaNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.mViaNumber = str;
    }

    public int getHandlePresentation() {
        return this.mHandlePresentation;
    }

    public void setCallerNumberVerificationStatus(int i) {
        this.mCallerNumberVerificationStatus = i;
    }

    public int getCallerNumberVerificationStatus() {
        return this.mCallerNumberVerificationStatus;
    }

    void setHandle(Uri uri) {
        setHandle(uri, 1);
    }

    public void setHandle(Uri uri, int i) {
        if (Objects.equals(uri, this.mHandle) && i == this.mHandlePresentation) {
            return;
        }
        this.mHandlePresentation = i;
        if (i == 2 || i == 3) {
            this.mHandle = null;
        } else {
            this.mHandle = uri;
            if (uri != null && !"voicemail".equals(uri.getScheme()) && TextUtils.isEmpty(this.mHandle.getSchemeSpecificPart())) {
                this.mHandle = null;
            }
        }
        if (!this.mIsEmergencyCall) {
            try {
                this.mIsEmergencyCall = this.mHandle != null && getTelephonyManager().isEmergencyNumber(this.mHandle.getSchemeSpecificPart());
            } catch (IllegalStateException e) {
                Log.e(this, e, "setHandle: can't determine if number is emergency", new Object[0]);
                this.mIsEmergencyCall = false;
            }
            this.mAnalytics.setCallIsEmergency(this.mIsEmergencyCall);
        }
        if (!this.mIsTestEmergencyCall) {
            Uri uri2 = this.mHandle;
            this.mIsTestEmergencyCall = uri2 != null && isTestEmergencyCall(uri2.getSchemeSpecificPart());
        }
        startCallerInfoLookup();
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onHandleChanged(this);
        }
    }

    private boolean isTestEmergencyCall(final String str) {
        try {
            return getTelephonyManager().getEmergencyNumberList().values().stream().flatMap(new Function() { // from class: com.android.server.telecom.-$$Lambda$seyL25CSW2NInOydsTbSDrNW6pM
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((List) obj).stream();
                }
            }).anyMatch(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$Call$Lpfw2JrNAlpPOmfZu4pq1SHKHRc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Call.lambda$isTestEmergencyCall$0(str, (EmergencyNumber) obj);
                }
            });
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    static /* synthetic */ boolean lambda$isTestEmergencyCall$0(String str, EmergencyNumber emergencyNumber) {
        return emergencyNumber.isFromSources(32) && str.equals(emergencyNumber.getNumber());
    }

    public String getCallerDisplayName() {
        return this.mCallerDisplayName;
    }

    public int getCallerDisplayNamePresentation() {
        return this.mCallerDisplayNamePresentation;
    }

    void setCallerDisplayName(String str, int i) {
        if (TextUtils.equals(str, this.mCallerDisplayName) && i == this.mCallerDisplayNamePresentation) {
            return;
        }
        this.mCallerDisplayName = str;
        this.mCallerDisplayNamePresentation = i;
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onCallerDisplayNameChanged(this);
        }
    }

    public String getName() {
        CallerInfo callerInfo = this.mCallerInfo;
        if (callerInfo == null) {
            return null;
        }
        return callerInfo.getName();
    }

    public String getPhoneNumber() {
        CallerInfo callerInfo = this.mCallerInfo;
        if (callerInfo == null) {
            return null;
        }
        return callerInfo.getPhoneNumber();
    }

    public Bitmap getPhotoIcon() {
        CallerInfo callerInfo = this.mCallerInfo;
        if (callerInfo == null) {
            return null;
        }
        return callerInfo.cachedPhotoIcon;
    }

    public Drawable getPhoto() {
        CallerInfo callerInfo = this.mCallerInfo;
        if (callerInfo == null) {
            return null;
        }
        return callerInfo.cachedPhoto;
    }

    public void setDisconnectCause(DisconnectCause disconnectCause) {
        if (this.mOverrideDisconnectCause.getCode() != 0) {
            int code = this.mOverrideDisconnectCause.getCode();
            CharSequence label = TextUtils.isEmpty(this.mOverrideDisconnectCause.getLabel()) ? disconnectCause.getLabel() : this.mOverrideDisconnectCause.getLabel();
            CharSequence description = this.mOverrideDisconnectCause.getDescription() == null ? disconnectCause.getDescription() : this.mOverrideDisconnectCause.getDescription();
            String reason = TextUtils.isEmpty(this.mOverrideDisconnectCause.getReason()) ? disconnectCause.getReason() : this.mOverrideDisconnectCause.getReason();
            if (this.mOverrideDisconnectCause.getTone() != 0) {
                disconnectCause = this.mOverrideDisconnectCause;
            }
            disconnectCause = new DisconnectCause(code, label, description, reason, disconnectCause.getTone());
        }
        this.mAnalytics.setCallDisconnectCause(disconnectCause);
        this.mDisconnectCause = disconnectCause;
    }

    public void setOverrideDisconnectCauseCode(DisconnectCause disconnectCause) {
        this.mOverrideDisconnectCause = disconnectCause;
    }

    public DisconnectCause getDisconnectCause() {
        return this.mDisconnectCause;
    }

    @VisibleForTesting
    public boolean isEmergencyCall() {
        return this.mIsEmergencyCall;
    }

    public boolean isTestEmergencyCall() {
        return this.mIsTestEmergencyCall;
    }

    public boolean isNetworkIdentifiedEmergencyCall() {
        return hasProperty(1024);
    }

    public Uri getOriginalHandle() {
        GatewayInfo gatewayInfo = this.mGatewayInfo;
        if (gatewayInfo != null && !gatewayInfo.isEmpty()) {
            return this.mGatewayInfo.getOriginalAddress();
        }
        return getHandle();
    }

    @VisibleForTesting
    public GatewayInfo getGatewayInfo() {
        return this.mGatewayInfo;
    }

    void setGatewayInfo(GatewayInfo gatewayInfo) {
        this.mGatewayInfo = gatewayInfo;
    }

    @VisibleForTesting
    public PhoneAccountHandle getConnectionManagerPhoneAccount() {
        return this.mConnectionManagerPhoneAccountHandle;
    }

    @VisibleForTesting
    public void setConnectionManagerPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
        if (!Objects.equals(this.mConnectionManagerPhoneAccountHandle, phoneAccountHandle)) {
            this.mConnectionManagerPhoneAccountHandle = phoneAccountHandle;
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onConnectionManagerPhoneAccountChanged(this);
            }
        }
        checkIfRttCapable();
    }

    public PhoneAccountHandle getRemotePhoneAccountHandle() {
        return this.mRemotePhoneAccountHandle;
    }

    public void setRemotePhoneAccountHandle(PhoneAccountHandle phoneAccountHandle) {
        this.mRemotePhoneAccountHandle = phoneAccountHandle;
    }

    public PhoneAccountHandle getDelegatePhoneAccountHandle() {
        PhoneAccountHandle phoneAccountHandle = this.mRemotePhoneAccountHandle;
        if (phoneAccountHandle != null) {
            return phoneAccountHandle;
        }
        PhoneAccountHandle phoneAccountHandle2 = this.mConnectionManagerPhoneAccountHandle;
        return phoneAccountHandle2 != null ? phoneAccountHandle2 : this.mTargetPhoneAccountHandle;
    }

    @VisibleForTesting
    public PhoneAccountHandle getTargetPhoneAccount() {
        return this.mTargetPhoneAccountHandle;
    }

    @VisibleForTesting
    public void setTargetPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
        if (!Objects.equals(this.mTargetPhoneAccountHandle, phoneAccountHandle)) {
            this.mTargetPhoneAccountHandle = phoneAccountHandle;
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onTargetPhoneAccountChanged(this);
            }
            configureCallAttributes();
        }
        checkIfVideoCapable();
        checkIfRttCapable();
    }

    public CharSequence getTargetPhoneAccountLabel() {
        PhoneAccount phoneAccountUnchecked;
        if (getTargetPhoneAccount() == null || (phoneAccountUnchecked = this.mCallsManager.getPhoneAccountRegistrar().getPhoneAccountUnchecked(getTargetPhoneAccount())) == null) {
            return null;
        }
        return phoneAccountUnchecked.getLabel();
    }

    public boolean isLoggedSelfManaged() {
        PhoneAccount phoneAccountUnchecked;
        if (!isSelfManaged()) {
            return true;
        }
        if (getTargetPhoneAccount() == null || (phoneAccountUnchecked = this.mCallsManager.getPhoneAccountRegistrar().getPhoneAccountUnchecked(getTargetPhoneAccount())) == null || getHandle() == null) {
            return false;
        }
        if ("sip".equals(getHandle().getScheme()) || "tel".equals(getHandle().getScheme())) {
            return phoneAccountUnchecked.getExtras() != null && phoneAccountUnchecked.getExtras().getBoolean("android.telecom.extra.LOG_SELF_MANAGED_CALLS", false);
        }
        return false;
    }

    @VisibleForTesting
    public boolean isIncoming() {
        return this.mCallDirection == 2;
    }

    public boolean isExternalCall() {
        return (getConnectionProperties() & 16) == 16;
    }

    public boolean isWorkCall() {
        return this.mIsWorkCall;
    }

    public boolean isUsingCallRecordingTone() {
        return this.mUseCallRecordingTone;
    }

    public boolean isVideoCallingSupportedByPhoneAccount() {
        return this.mIsVideoCallingSupportedByPhoneAccount;
    }

    public void setVideoCallingSupportedByPhoneAccount(boolean z) {
        if (this.mIsVideoCallingSupportedByPhoneAccount == z) {
            return;
        }
        Log.i(this, "setVideoCallingSupportedByPhoneAccount: isSupp=%b", new Object[]{Boolean.valueOf(z)});
        this.mIsVideoCallingSupportedByPhoneAccount = z;
        setConnectionCapabilities(getConnectionCapabilities(), true);
    }

    public void setIsPullExternalCallSupported(boolean z) {
        if (isExternalCall() && z != this.mIsPullExternalCallSupported) {
            Log.i(this, "setCanPullExternalCall: canPull=%b", new Object[]{Boolean.valueOf(z)});
            this.mIsPullExternalCallSupported = z;
            setConnectionCapabilities(this.mConnectionCapabilities, true);
        }
    }

    public boolean isLocallyVideoCapable() {
        return (getConnectionCapabilities() & 768) == 768;
    }

    public boolean isSelfManaged() {
        return this.mIsSelfManaged;
    }

    public void setIsSelfManaged(boolean z) {
        this.mIsSelfManaged = z;
        setConnectionProperties(getConnectionProperties());
    }

    public void markFinishedHandoverStateAndCleanup(int i) {
        Call call = this.mHandoverSourceCall;
        if (call != null) {
            call.setHandoverState(i);
        } else {
            Call call2 = this.mHandoverDestinationCall;
            if (call2 != null) {
                call2.setHandoverState(i);
            }
        }
        setHandoverState(i);
        maybeCleanupHandover();
    }

    public void maybeCleanupHandover() {
        Call call = this.mHandoverSourceCall;
        if (call != null) {
            call.setHandoverSourceCall(null);
            this.mHandoverSourceCall.setHandoverDestinationCall(null);
            this.mHandoverSourceCall = null;
        } else {
            Call call2 = this.mHandoverDestinationCall;
            if (call2 != null) {
                call2.setHandoverSourceCall(null);
                this.mHandoverDestinationCall.setHandoverDestinationCall(null);
                this.mHandoverDestinationCall = null;
            }
        }
    }

    public boolean isHandoverInProgress() {
        return (this.mHandoverSourceCall == null && this.mHandoverDestinationCall == null) ? false : true;
    }

    public Call getHandoverDestinationCall() {
        return this.mHandoverDestinationCall;
    }

    public void setHandoverDestinationCall(Call call) {
        this.mHandoverDestinationCall = call;
    }

    public Call getHandoverSourceCall() {
        return this.mHandoverSourceCall;
    }

    public void setHandoverSourceCall(Call call) {
        this.mHandoverSourceCall = call;
    }

    public void setHandoverState(int i) {
        Log.d(this, "setHandoverState: callId=%s, handoverState=%s", new Object[]{getId(), HandoverState.stateToString(i)});
        this.mHandoverState = i;
    }

    public int getHandoverState() {
        return this.mHandoverState;
    }

    private void configureCallAttributes() {
        boolean z;
        UserHandle userHandle;
        PhoneAccount phoneAccountUnchecked = this.mCallsManager.getPhoneAccountRegistrar().getPhoneAccountUnchecked(this.mTargetPhoneAccountHandle);
        boolean z2 = false;
        if (phoneAccountUnchecked != null) {
            if (phoneAccountUnchecked.hasCapabilities(32)) {
                userHandle = this.mInitiatingUser;
            } else {
                userHandle = this.mTargetPhoneAccountHandle.getUserHandle();
            }
            boolean zIsManagedProfile = userHandle != null ? UserUtil.isManagedProfile(this.mContext, userHandle) : false;
            if (phoneAccountUnchecked.hasCapabilities(4) && phoneAccountUnchecked.getExtras() != null && phoneAccountUnchecked.getExtras().getBoolean("android.telecom.extra.PLAY_CALL_RECORDING_TONE", false)) {
                z2 = true;
            }
            z = z2;
            z2 = zIsManagedProfile;
        } else {
            z = false;
        }
        this.mIsWorkCall = z2;
        this.mUseCallRecordingTone = z;
    }

    private void checkIfVideoCapable() {
        PhoneAccountRegistrar phoneAccountRegistrar = this.mCallsManager.getPhoneAccountRegistrar();
        PhoneAccountHandle phoneAccountHandle = this.mTargetPhoneAccountHandle;
        if (phoneAccountHandle == null) {
            this.mIsVideoCallingSupportedByPhoneAccount = true;
            Log.d(this, "checkIfVideoCapable: no phone account selected; assume video capable.", new Object[0]);
            return;
        }
        PhoneAccount phoneAccountUnchecked = phoneAccountRegistrar.getPhoneAccountUnchecked(phoneAccountHandle);
        boolean z = phoneAccountUnchecked != null && phoneAccountUnchecked.hasCapabilities(8);
        this.mIsVideoCallingSupportedByPhoneAccount = z;
        if (z || !VideoProfile.isVideo(getVideoState())) {
            return;
        }
        setVideoState(0);
        Log.d(this, "checkIfVideoCapable: selected phone account doesn't support video.", new Object[0]);
    }

    private void checkIfRttCapable() {
        PhoneAccountRegistrar phoneAccountRegistrar = this.mCallsManager.getPhoneAccountRegistrar();
        PhoneAccountHandle phoneAccountHandle = this.mTargetPhoneAccountHandle;
        if (phoneAccountHandle == null) {
            return;
        }
        PhoneAccount phoneAccountUnchecked = phoneAccountRegistrar.getPhoneAccountUnchecked(phoneAccountHandle);
        PhoneAccount phoneAccountUnchecked2 = phoneAccountRegistrar.getPhoneAccountUnchecked(this.mConnectionManagerPhoneAccountHandle);
        boolean z = phoneAccountUnchecked != null && phoneAccountUnchecked.hasCapabilities(4096);
        if (((phoneAccountUnchecked2 != null && phoneAccountUnchecked2.hasCapabilities(4096)) || z) && this.mDidRequestToStartWithRtt && !areRttStreamsInitialized()) {
            createRttStreams();
            Log.i(this, "Setting RTT streams after target phone account selected", new Object[0]);
        }
    }

    boolean shouldAttachToExistingConnection() {
        return this.mShouldAttachToExistingConnection;
    }

    @VisibleForTesting
    public long getAgeMillis() {
        if (this.mState == 7 && (this.mDisconnectCause.getCode() == 6 || this.mDisconnectCause.getCode() == 5)) {
            return 0L;
        }
        long j = this.mConnectElapsedTimeMillis;
        if (j == 0) {
            return 0L;
        }
        long j2 = this.mDisconnectElapsedTimeMillis;
        return j2 == 0 ? this.mClockProxy.elapsedRealtime() - this.mConnectElapsedTimeMillis : j2 - j;
    }

    public long getCreationTimeMillis() {
        return this.mCreationTimeMillis;
    }

    public long getConnectTimeMillis() {
        return this.mConnectTimeMillis;
    }

    public void setConnectTimeMillis(long j) {
        this.mConnectTimeMillis = j;
    }

    public void setConnectElapsedTimeMillis(long j) {
        this.mConnectElapsedTimeMillis = j;
    }

    public int getConnectionCapabilities() {
        return stripUnsupportedCapabilities(this.mConnectionCapabilities);
    }

    int getConnectionProperties() {
        return this.mConnectionProperties;
    }

    public void setConnectionCapabilities(int i) {
        setConnectionCapabilities(i, false);
    }

    void setConnectionCapabilities(int i, boolean z) {
        Log.v(this, "setConnectionCapabilities: %s", new Object[]{Connection.capabilitiesToString(i)});
        if (z || this.mConnectionCapabilities != i) {
            int i2 = this.mConnectionCapabilities;
            this.mConnectionCapabilities = i;
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onConnectionCapabilitiesChanged(this);
            }
            int connectionCapabilities = getConnectionCapabilities();
            int i3 = i2 ^ connectionCapabilities;
            Log.addEvent(this, "CAPABILITY_CHANGE", "Current: [%s], Removed [%s], Added [%s]", new Object[]{Connection.capabilitiesToStringShort(connectionCapabilities), Connection.capabilitiesToStringShort(i2 & i3), Connection.capabilitiesToStringShort(connectionCapabilities & i3)});
        }
    }

    private int stripUnsupportedCapabilities(int i) {
        if (this.mIsPullExternalCallSupported) {
            return i;
        }
        int i2 = i | 16777216;
        if (i2 <= 0) {
            return i2;
        }
        int i3 = i2 & (-16777217);
        Log.i(this, "stripCapabilitiesBasedOnState: CAPABILITY_CAN_PULL_CALL removed.", new Object[0]);
        return i3;
    }

    public void setConnectionProperties(int i) {
        Log.v(this, "setConnectionProperties: %s", new Object[]{Connection.propertiesToString(i)});
        int i2 = isSelfManaged() ? i | 128 : i & (-129);
        int i3 = this.mConnectionProperties;
        int i4 = i3 ^ i2;
        if (i4 != 0) {
            this.mConnectionProperties = i2;
            boolean z = (i4 & 256) == 256;
            if (z) {
                if ((this.mConnectionProperties & 256) == 256) {
                    createRttStreams();
                    this.mConnectionService.startRtt(this, getInCallToCsRttPipeForCs(), getCsToInCallRttPipeForCs());
                    this.mWasEverRtt = true;
                    if (isEmergencyCall()) {
                        this.mCallsManager.mute(false);
                    }
                } else {
                    closeRttStreams();
                    this.mInCallToConnectionServiceStreams = null;
                    this.mConnectionServiceToInCallStreams = null;
                }
            }
            this.mWasHighDefAudio = (i2 & 4) == 4;
            this.mWasWifi = (i2 & 8) > 0;
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onConnectionPropertiesChanged(this, z);
            }
            boolean z2 = (i3 & 16) == 16;
            boolean z3 = (i2 & 16) == 16;
            if (z2 != z3) {
                Log.v(this, "setConnectionProperties: external call changed isExternal = %b", new Object[]{Boolean.valueOf(z3)});
                Log.addEvent(this, "IS_EXTERNAL", Boolean.valueOf(z3));
                if (z3) {
                    setIsPullExternalCallSupported(!this.mCallsManager.isInEmergencyCall());
                }
                Iterator<Listener> it2 = this.mListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onExternalCallChanged(this, z3);
                }
            }
            this.mAnalytics.addCallProperties(this.mConnectionProperties);
            int i5 = this.mConnectionProperties;
            int i6 = i3 ^ i5;
            Log.addEvent(this, "PROPERTY_CHANGE", "Current: [%s], Removed [%s], Added [%s]", new Object[]{Connection.propertiesToStringShort(i5), Connection.propertiesToStringShort(i3 & i6), Connection.propertiesToStringShort(this.mConnectionProperties & i6)});
        }
    }

    public int getSupportedAudioRoutes() {
        return this.mSupportedAudioRoutes;
    }

    void setSupportedAudioRoutes(int i) {
        if (this.mSupportedAudioRoutes != i) {
            this.mSupportedAudioRoutes = i;
        }
    }

    @VisibleForTesting
    public Call getParentCall() {
        return this.mParentCall;
    }

    @VisibleForTesting
    public List<Call> getChildCalls() {
        return this.mChildCalls;
    }

    @VisibleForTesting
    public boolean wasConferencePreviouslyMerged() {
        return this.mWasConferencePreviouslyMerged;
    }

    public boolean isDisconnectingChildCall() {
        return this.mIsDisconnectingChildCall;
    }

    private void maybeSetCallAsDisconnectingChild() {
        if (this.mParentCall != null) {
            this.mIsDisconnectingChildCall = true;
        }
    }

    @VisibleForTesting
    public Call getConferenceLevelActiveCall() {
        return this.mConferenceLevelActiveCall;
    }

    @VisibleForTesting
    public ConnectionServiceWrapper getConnectionService() {
        return this.mConnectionService;
    }

    public Context getContext() {
        return this.mContext;
    }

    @VisibleForTesting
    public void setConnectionService(ConnectionServiceWrapper connectionServiceWrapper) {
        Preconditions.checkNotNull(connectionServiceWrapper);
        clearConnectionService();
        connectionServiceWrapper.incrementAssociatedCallCount();
        this.mConnectionService = connectionServiceWrapper;
        this.mAnalytics.setCallConnectionService(connectionServiceWrapper.getComponentName().flattenToShortString());
        this.mConnectionService.addCall(this);
    }

    public void replaceConnectionService(ConnectionServiceWrapper connectionServiceWrapper) {
        Preconditions.checkNotNull(connectionServiceWrapper);
        ConnectionServiceWrapper connectionServiceWrapper2 = this.mConnectionService;
        if (connectionServiceWrapper2 != null) {
            this.mConnectionService = null;
            connectionServiceWrapper2.removeCall(this);
            connectionServiceWrapper2.decrementAssociatedCallCount(true);
        }
        connectionServiceWrapper.incrementAssociatedCallCount();
        this.mConnectionService = connectionServiceWrapper;
        this.mAnalytics.setCallConnectionService(connectionServiceWrapper.getComponentName().flattenToShortString());
    }

    void clearConnectionService() {
        ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
        if (connectionServiceWrapper != null) {
            this.mConnectionService = null;
            connectionServiceWrapper.removeCall(this);
            decrementAssociatedCallCount(connectionServiceWrapper);
        }
    }

    void startCreateConnection(PhoneAccountRegistrar phoneAccountRegistrar) {
        if (this.mCreateConnectionProcessor != null) {
            Log.w(this, "mCreateConnectionProcessor in startCreateConnection is not null. This is due to a race between NewOutgoingCallIntentBroadcaster and phoneAccountSelected, but is harmlessly resolved by ignoring the second invocation.", new Object[0]);
            return;
        }
        CreateConnectionProcessor createConnectionProcessor = new CreateConnectionProcessor(this, this.mRepository, this, phoneAccountRegistrar, this.mContext);
        this.mCreateConnectionProcessor = createConnectionProcessor;
        createConnectionProcessor.process();
    }

    @Override // com.android.server.telecom.CreateConnectionResponse
    public void handleCreateConferenceSuccess(CallIdMapper callIdMapper, ParcelableConference parcelableConference) {
        Log.v(this, "handleCreateConferenceSuccessful %s", new Object[]{parcelableConference});
        setTargetPhoneAccount(parcelableConference.getPhoneAccount());
        setHandle(parcelableConference.getHandle(), parcelableConference.getHandlePresentation());
        setConnectionCapabilities(parcelableConference.getConnectionCapabilities());
        setConnectionProperties(parcelableConference.getConnectionProperties());
        setVideoProvider(parcelableConference.getVideoProvider());
        setVideoState(parcelableConference.getVideoState());
        setRingbackRequested(parcelableConference.isRingbackRequested());
        setStatusHints(parcelableConference.getStatusHints());
        putExtras(1, parcelableConference.getExtras());
        int i = this.mCallDirection;
        if (i == 1) {
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onSuccessfulOutgoingCall(this, getStateFromConnectionState(parcelableConference.getState()));
            }
        } else {
            if (i != 2) {
                return;
            }
            Iterator<Listener> it2 = this.mListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onSuccessfulIncomingCall(this);
            }
        }
    }

    @Override // com.android.server.telecom.CreateConnectionResponse
    public void handleCreateConnectionSuccess(CallIdMapper callIdMapper, ParcelableConnection parcelableConnection) {
        Log.v(this, "handleCreateConnectionSuccessful %s", new Object[]{parcelableConnection});
        setTargetPhoneAccount(parcelableConnection.getPhoneAccount());
        setHandle(parcelableConnection.getHandle(), parcelableConnection.getHandlePresentation());
        setCallerDisplayName(parcelableConnection.getCallerDisplayName(), parcelableConnection.getCallerDisplayNamePresentation());
        setConnectionCapabilities(parcelableConnection.getConnectionCapabilities());
        setConnectionProperties(parcelableConnection.getConnectionProperties());
        setIsVoipAudioMode(parcelableConnection.getIsVoipAudioMode());
        setSupportedAudioRoutes(parcelableConnection.getSupportedAudioRoutes());
        setVideoProvider(parcelableConnection.getVideoProvider());
        setVideoState(parcelableConnection.getVideoState());
        setRingbackRequested(parcelableConnection.isRingbackRequested());
        setStatusHints(parcelableConnection.getStatusHints());
        putExtras(1, parcelableConnection.getExtras());
        this.mConferenceableCalls.clear();
        Iterator it = parcelableConnection.getConferenceableConnectionIds().iterator();
        while (it.hasNext()) {
            this.mConferenceableCalls.add(callIdMapper.getCall((String) it.next()));
        }
        int i = this.mCallDirection;
        if (i == 1) {
            Iterator<Listener> it2 = this.mListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onSuccessfulOutgoingCall(this, getStateFromConnectionState(parcelableConnection.getState()));
            }
        } else {
            if (i == 2) {
                setCallerNumberVerificationStatus(parcelableConnection.getCallerNumberVerificationStatus());
                Iterator<Listener> it3 = this.mListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onSuccessfulIncomingCall(this);
                }
                return;
            }
            if (i != 3) {
                return;
            }
            Iterator<Listener> it4 = this.mListeners.iterator();
            while (it4.hasNext()) {
                it4.next().onSuccessfulUnknownCall(this, getStateFromConnectionState(parcelableConnection.getState()));
            }
        }
    }

    @Override // com.android.server.telecom.CreateConnectionResponse
    public void handleCreateConferenceFailure(DisconnectCause disconnectCause) {
        clearConnectionService();
        setDisconnectCause(disconnectCause);
        this.mCallsManager.markCallAsDisconnected(this, disconnectCause);
        int i = this.mCallDirection;
        if (i == 1) {
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onFailedOutgoingCall(this, disconnectCause);
            }
        } else {
            if (i != 2) {
                return;
            }
            Iterator<Listener> it2 = this.mListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onFailedIncomingCall(this);
            }
        }
    }

    @Override // com.android.server.telecom.CreateConnectionResponse
    public void handleCreateConnectionFailure(DisconnectCause disconnectCause) {
        clearConnectionService();
        setDisconnectCause(disconnectCause);
        this.mCallsManager.markCallAsDisconnected(this, disconnectCause);
        int i = this.mCallDirection;
        if (i == 1) {
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onFailedOutgoingCall(this, disconnectCause);
            }
        } else if (i == 2) {
            Iterator<Listener> it2 = this.mListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onFailedIncomingCall(this);
            }
        } else {
            if (i != 3) {
                return;
            }
            Iterator<Listener> it3 = this.mListeners.iterator();
            while (it3.hasNext()) {
                it3.next().onFailedUnknownCall(this);
            }
        }
    }

    @VisibleForTesting
    public void playDtmfTone(char c) {
        if (this.mConnectionService == null) {
            Log.w(this, "playDtmfTone() request on a call without a connection service.", new Object[0]);
        } else {
            Log.i(this, "Send playDtmfTone to connection service for call %s", new Object[]{this});
            this.mConnectionService.playDtmfTone(this, c);
            Log.addEvent(this, "START_DTMF", Log.pii(Character.valueOf(c)));
        }
        this.mPlayingDtmfTone = c;
    }

    @VisibleForTesting
    public void stopDtmfTone() {
        if (this.mConnectionService == null) {
            Log.w(this, "stopDtmfTone() request on a call without a connection service.", new Object[0]);
        } else {
            Log.i(this, "Send stopDtmfTone to connection service for call %s", new Object[]{this});
            Log.addEvent(this, "STOP_DTMF");
            this.mConnectionService.stopDtmfTone(this);
        }
        this.mPlayingDtmfTone = (char) 0;
    }

    boolean isDtmfTonePlaying() {
        return this.mPlayingDtmfTone != 0;
    }

    void silence() {
        if (this.mConnectionService == null) {
            Log.w(this, "silence() request on a call without a connection service.", new Object[0]);
            return;
        }
        Log.i(this, "Send silence to connection service for call %s", new Object[]{this});
        Log.addEvent(this, "SILENCE");
        this.mConnectionService.silence(this);
    }

    @VisibleForTesting
    public void disconnect() {
        disconnect(0L);
    }

    @VisibleForTesting
    public void disconnect(String str) {
        disconnect(0L, str);
    }

    @VisibleForTesting
    public void disconnect(long j) {
        disconnect(j, "internal");
    }

    @VisibleForTesting
    public void disconnect(long j, String str) {
        Log.addEvent(this, "REQUEST_DISCONNECT", str);
        setLocallyDisconnecting(true);
        maybeSetCallAsDisconnectingChild();
        int i = this.mState;
        if (i == 0 || i == 2 || i == 1) {
            Log.v(this, "Aborting call %s", new Object[]{this});
            abort(j);
            return;
        }
        if (i == 8 || i == 7) {
            return;
        }
        if (i == 12 && !hasGoneActiveBefore()) {
            setOverrideDisconnectCauseCode(new DisconnectCause(6));
        } else if (this.mState == 13) {
            setOverrideDisconnectCauseCode(new DisconnectCause(5));
        }
        if (this.mConnectionService == null) {
            Log.e(this, new Exception(), "disconnect() request on a call without a connection service.", new Object[0]);
        } else {
            Log.i(this, "Send disconnect to connection service for call: %s", new Object[]{this});
            this.mConnectionService.disconnect(this);
        }
    }

    void abort(long j) {
        CreateConnectionProcessor createConnectionProcessor = this.mCreateConnectionProcessor;
        if (createConnectionProcessor != null && !createConnectionProcessor.isProcessingComplete()) {
            this.mCreateConnectionProcessor.abort();
            return;
        }
        int i = this.mState;
        if (i == 0 || i == 2 || i == 1) {
            if (j > 0) {
                Iterator<Listener> it = this.mListeners.iterator();
                while (it.hasNext()) {
                    if (it.next().onCanceledViaNewOutgoingCallBroadcast(this, j)) {
                        setLocallyDisconnecting(false);
                        return;
                    }
                }
            }
            handleCreateConnectionFailure(new DisconnectCause(4));
            return;
        }
        Log.v(this, "Cannot abort a call which is neither SELECT_PHONE_ACCOUNT or CONNECTING", new Object[0]);
    }

    @VisibleForTesting
    public void answer(int i) {
        if (isRinging("answer")) {
            if (!isVideoCallingSupportedByPhoneAccount() && VideoProfile.isVideo(i)) {
                i = 0;
            }
            ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
            if (connectionServiceWrapper != null) {
                connectionServiceWrapper.answer(this, i);
            } else {
                Log.e(this, new NullPointerException(), "answer call failed due to null CS callId=%s", new Object[]{getId()});
            }
            Log.addEvent(this, "REQUEST_ACCEPT");
        }
    }

    public void answerForAudioProcessing() {
        if (this.mState != 4) {
            Log.w(this, "Trying to audio-process a non-ringing call: id=%s", new Object[]{this.mId});
            return;
        }
        ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
        if (connectionServiceWrapper != null) {
            connectionServiceWrapper.answer(this, 0);
        } else {
            Log.e(this, new NullPointerException(), "answer call (audio processing) failed due to null CS callId=%s", new Object[]{getId()});
        }
        Log.addEvent(this, "REQUEST_PICKUP_FOR_AUDIO_PROCESSING");
    }

    public void setAudioProcessingRequestingApp(CharSequence charSequence) {
        this.mAudioProcessingRequestingApp = charSequence;
    }

    public CharSequence getAudioProcessingRequestingApp() {
        return this.mAudioProcessingRequestingApp;
    }

    @VisibleForTesting
    public void deflect(Uri uri) {
        if (isRinging("deflect")) {
            this.mVideoStateHistory |= this.mVideoState;
            ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
            if (connectionServiceWrapper != null) {
                connectionServiceWrapper.deflect(this, uri);
            } else {
                Log.e(this, new NullPointerException(), "deflect call failed due to null CS callId=%s", new Object[]{getId()});
            }
            Log.addEvent(this, "REQUEST_DEFLECT", Log.pii(uri));
        }
    }

    @VisibleForTesting
    public void reject(boolean z, String str) {
        reject(z, str, "internal");
    }

    @VisibleForTesting
    public void reject(boolean z, String str, String str2) {
        if (this.mState == 13) {
            setOverrideDisconnectCauseCode(new DisconnectCause(6));
            ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
            if (connectionServiceWrapper != null) {
                connectionServiceWrapper.disconnect(this);
            } else {
                Log.e(this, new NullPointerException(), "reject call failed due to null CS callId=%s", new Object[]{getId()});
            }
            Log.addEvent(this, "REQUEST_REJECT", str2);
            return;
        }
        if (isRinging("reject")) {
            this.mVideoStateHistory |= this.mVideoState;
            ConnectionServiceWrapper connectionServiceWrapper2 = this.mConnectionService;
            if (connectionServiceWrapper2 != null) {
                connectionServiceWrapper2.reject(this, z, str);
            } else {
                Log.e(this, new NullPointerException(), "reject call failed due to null CS callId=%s", new Object[]{getId()});
            }
            Log.addEvent(this, "REQUEST_REJECT", str2);
        }
    }

    public void reject(int i) {
        if (this.mState == 13) {
            setOverrideDisconnectCauseCode(new DisconnectCause(6));
            ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
            if (connectionServiceWrapper != null) {
                connectionServiceWrapper.disconnect(this);
            } else {
                Log.e(this, new NullPointerException(), "reject call failed due to null CS callId=%s", new Object[]{getId()});
            }
            Log.addEvent(this, "REQUEST_REJECT");
            return;
        }
        if (isRinging("reject")) {
            this.mVideoStateHistory |= this.mVideoState;
            ConnectionServiceWrapper connectionServiceWrapper2 = this.mConnectionService;
            if (connectionServiceWrapper2 != null) {
                connectionServiceWrapper2.rejectWithReason(this, i);
            } else {
                Log.e(this, new NullPointerException(), "reject call failed due to null CS callId=%s", new Object[]{getId()});
            }
            Log.addEvent(this, "REQUEST_REJECT", Integer.valueOf(i));
        }
    }

    @VisibleForTesting
    public void transfer(Uri uri, boolean z) {
        int i = this.mState;
        if (i == 5 || i == 6) {
            ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
            if (connectionServiceWrapper != null) {
                connectionServiceWrapper.transfer(this, uri, z);
            } else {
                Log.e(this, new NullPointerException(), "transfer call failed due to null CS callId=%s", new Object[]{getId()});
            }
            Log.addEvent(this, "REQUEST_TRANSFER", Log.pii(uri));
        }
    }

    @VisibleForTesting
    public void transfer(Call call) {
        if (this.mState == 5 && call != null && call.getState() == 6) {
            ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
            if (connectionServiceWrapper != null) {
                connectionServiceWrapper.transfer(this, call);
            } else {
                Log.e(this, new NullPointerException(), "transfer call failed due to null CS callId=%s", new Object[]{getId()});
            }
            Log.addEvent(this, "REQUEST_CONSULTATIVE_TRANSFER", call);
        }
    }

    @VisibleForTesting
    public void hold() {
        hold(null);
    }

    public void hold(String str) {
        if (this.mState == 5) {
            ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
            if (connectionServiceWrapper != null) {
                connectionServiceWrapper.hold(this);
            } else {
                Log.e(this, new NullPointerException(), "hold call failed due to null CS callId=%s", new Object[]{getId()});
            }
            Log.addEvent(this, "REQUEST_HOLD", str);
        }
    }

    @VisibleForTesting
    public void unhold() {
        unhold(null);
    }

    public void unhold(String str) {
        if (this.mState == 6) {
            ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
            if (connectionServiceWrapper != null) {
                connectionServiceWrapper.unhold(this);
            } else {
                Log.e(this, new NullPointerException(), "unhold call failed due to null CS callId=%s", new Object[]{getId()});
            }
            Log.addEvent(this, "REQUEST_UNHOLD", str);
        }
    }

    @VisibleForTesting
    public boolean isAlive() {
        int i = this.mState;
        return (i == 0 || i == 4 || i == 11 || i == 7 || i == 8) ? false : true;
    }

    @VisibleForTesting
    public boolean isActive() {
        return this.mState == 5;
    }

    Bundle getExtras() {
        return this.mExtras;
    }

    public void putExtras(int i, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (this.mExtras == null) {
            this.mExtras = new Bundle();
        }
        this.mExtras.putAll(bundle);
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onExtrasChanged(this, i, bundle);
        }
        if (this.mExtras.containsKey("android.telecom.extra.CALL_NETWORK_TYPE") && this.mExtras.get("android.telecom.extra.CALL_NETWORK_TYPE").equals(13)) {
            this.mWasVolte = true;
        }
        if (bundle.containsKey("android.telecom.extra.ORIGINAL_CONNECTION_ID")) {
            setOriginalConnectionId(bundle.getString("android.telecom.extra.ORIGINAL_CONNECTION_ID"));
        }
        if (this.mExtras.containsKey("android.telecom.extra.REMOTE_PHONE_ACCOUNT_HANDLE")) {
            setRemotePhoneAccountHandle((PhoneAccountHandle) bundle.getParcelable("android.telecom.extra.REMOTE_PHONE_ACCOUNT_HANDLE"));
        }
        if (i == 2) {
            ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
            if (connectionServiceWrapper != null) {
                connectionServiceWrapper.onExtrasChanged(this, this.mExtras);
            } else {
                Log.e(this, new NullPointerException(), "putExtras failed due to null CS callId=%s", new Object[]{getId()});
            }
        }
    }

    void removeExtras(int i, List<String> list) {
        if (this.mExtras == null) {
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.mExtras.remove(it.next());
        }
        Iterator<Listener> it2 = this.mListeners.iterator();
        while (it2.hasNext()) {
            it2.next().onExtrasRemoved(this, i, list);
        }
        if (i == 2) {
            ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
            if (connectionServiceWrapper != null) {
                connectionServiceWrapper.onExtrasChanged(this, this.mExtras);
            } else {
                Log.e(this, new NullPointerException(), "removeExtras failed due to null CS callId=%s", new Object[]{getId()});
            }
        }
    }

    @VisibleForTesting
    public Bundle getIntentExtras() {
        return this.mIntentExtras;
    }

    void setIntentExtras(Bundle bundle) {
        this.mIntentExtras = bundle;
    }

    @VisibleForTesting
    public Uri getContactUri() {
        CallerInfo callerInfo = this.mCallerInfo;
        if (callerInfo == null || !callerInfo.contactExists) {
            return getHandle();
        }
        return ContactsContract.Contacts.getLookupUri(callerInfo.getContactId(), this.mCallerInfo.lookupKey);
    }

    Uri getRingtone() {
        CallerInfo callerInfo = this.mCallerInfo;
        if (callerInfo == null) {
            return null;
        }
        return callerInfo.contactRingtoneUri;
    }

    void onPostDialWait(String str) {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onPostDialWait(this, str);
        }
    }

    void onPostDialChar(char c) {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onPostDialChar(this, c);
        }
    }

    void postDialContinue(boolean z) {
        ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
        if (connectionServiceWrapper != null) {
            connectionServiceWrapper.onPostDialContinue(this, z);
        } else {
            Log.e(this, new NullPointerException(), "postDialContinue failed due to null CS callId=%s", new Object[]{getId()});
        }
    }

    void conferenceWith(Call call) {
        if (this.mConnectionService == null) {
            Log.w(this, "conference requested on a call without a connection service.", new Object[0]);
        } else {
            Log.addEvent(this, "CONF_WITH", call);
            this.mConnectionService.conference(this, call);
        }
    }

    void splitFromConference() {
        if (this.mConnectionService == null) {
            Log.w(this, "splitting from conference call without a connection service", new Object[0]);
        } else {
            Log.addEvent(this, "CONF_SPLIT");
            this.mConnectionService.splitFromConference(this);
        }
    }

    @VisibleForTesting
    public void mergeConference() {
        if (this.mConnectionService == null) {
            Log.w(this, "merging conference calls without a connection service.", new Object[0]);
        } else if (can(4)) {
            Log.addEvent(this, "CONF_WITH");
            this.mConnectionService.mergeConference(this);
            this.mWasConferencePreviouslyMerged = true;
        }
    }

    @VisibleForTesting
    public void swapConference() {
        if (this.mConnectionService == null) {
            Log.w(this, "swapping conference calls without a connection service.", new Object[0]);
            return;
        }
        if (can(8)) {
            Log.addEvent(this, "SWAP");
            this.mConnectionService.swapConference(this);
            int size = this.mChildCalls.size();
            if (size == 1) {
                this.mConferenceLevelActiveCall = this.mChildCalls.get(0);
            } else if (size == 2) {
                this.mConferenceLevelActiveCall = this.mChildCalls.get(0) == this.mConferenceLevelActiveCall ? this.mChildCalls.get(1) : this.mChildCalls.get(0);
            } else {
                this.mConferenceLevelActiveCall = null;
            }
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onCdmaConferenceSwap(this);
            }
        }
    }

    public void addConferenceParticipants(List<Uri> list) {
        if (this.mConnectionService == null) {
            Log.w(this, "adding conference participants without a connection service.", new Object[0]);
        } else if (can(67108864)) {
            Log.addEvent(this, "ADD_PARTICIPANT");
            this.mConnectionService.addConferenceParticipants(this, list);
        }
    }

    public void pullExternalCall() {
        if (this.mConnectionService == null) {
            Log.w(this, "pulling a call without a connection service.", new Object[0]);
        }
        if (!hasProperty(16)) {
            Log.w(this, "pullExternalCall - call %s is not an external call.", new Object[]{this.mId});
            return;
        }
        if (!can(16777216)) {
            Log.w(this, "pullExternalCall - call %s is external but cannot be pulled.", new Object[]{this.mId});
        } else if (this.mCallsManager.isInEmergencyCall()) {
            Log.w(this, "pullExternalCall = pullExternalCall - call %s is external but can not be pulled while an emergency call is in progress.", new Object[]{this.mId});
            this.mToastFactory.makeText(this.mContext, R.string.toast_emergency_can_not_pull_call, 1).show();
        } else {
            Log.addEvent(this, "PULL");
            this.mConnectionService.pullExternalCall(this);
        }
    }

    public void sendCallEvent(String str, Bundle bundle) {
        sendCallEvent(str, 0, bundle);
    }

    public void sendCallEvent(String str, int i, Bundle bundle) {
        if (this.mConnectionService != null) {
            if (!"android.telecom.event.REQUEST_HANDOVER".equals(str)) {
                Log.addEvent(this, "CALL_EVENT", str);
                this.mConnectionService.sendCallEvent(this, str, bundle);
                return;
            }
            if (i > 28) {
                Log.e(this, new Exception(), "sendCallEvent failed. Use public api handoverTo for API > 28(P)", new Object[0]);
                this.mHandler.post(new Runnable() { // from class: com.android.server.telecom.Call.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Call.this.mToastFactory.makeText(Call.this.mContext, "WARNING: Event-based handover APIs are deprecated and will no longer function in Android Q.", 1).show();
                    }
                });
            }
            if (bundle == null) {
                Log.w(this, "sendCallEvent: %s event received with null extras.", new Object[]{"android.telecom.event.REQUEST_HANDOVER"});
                this.mConnectionService.sendCallEvent(this, "android.telecom.event.HANDOVER_FAILED", null);
                return;
            }
            Parcelable parcelable = bundle.getParcelable("android.telecom.extra.HANDOVER_PHONE_ACCOUNT_HANDLE");
            if (!(parcelable instanceof PhoneAccountHandle) || parcelable == null) {
                Log.w(this, "sendCallEvent: %s event received with invalid handover acct.", new Object[]{"android.telecom.event.REQUEST_HANDOVER"});
                this.mConnectionService.sendCallEvent(this, "android.telecom.event.HANDOVER_FAILED", null);
                return;
            } else {
                PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) parcelable;
                int i2 = bundle.getInt("android.telecom.extra.HANDOVER_VIDEO_STATE", 0);
                Parcelable parcelable2 = bundle.getParcelable("android.telecom.extra.HANDOVER_EXTRAS");
                requestHandover(phoneAccountHandle, i2, parcelable2 instanceof Bundle ? (Bundle) parcelable2 : null, true);
                return;
            }
        }
        Log.e(this, new NullPointerException(), "sendCallEvent failed due to null CS callId=%s", new Object[]{getId()});
    }

    public void handoverTo(PhoneAccountHandle phoneAccountHandle, int i, Bundle bundle) {
        requestHandover(phoneAccountHandle, i, bundle, false);
    }

    void setParentAndChildCall(Call call) {
        boolean z = this.mParentCall != call;
        setParentCall(call);
        setChildOf(call);
        if (z) {
            notifyParentChanged(call);
        }
    }

    void notifyParentChanged(Call call) {
        Log.addEvent(this, "SET_PARENT", call);
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onParentChanged(this);
        }
    }

    void setParentCall(Call call) {
        if (call == this) {
            Log.e(this, new Exception(), "setting the parent to self", new Object[0]);
            return;
        }
        Call call2 = this.mParentCall;
        if (call == call2) {
            return;
        }
        if (call2 != null) {
            call2.removeChildCall(this);
        }
        this.mParentCall = call;
    }

    public void setChildOf(Call call) {
        if (call == null || call.getChildCalls().contains(this)) {
            return;
        }
        call.addChildCall(this);
    }

    void setConferenceableCalls(List<Call> list) {
        this.mConferenceableCalls.clear();
        this.mConferenceableCalls.addAll(list);
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onConferenceableCallsChanged(this);
        }
    }

    @VisibleForTesting
    public List<Call> getConferenceableCalls() {
        return this.mConferenceableCalls;
    }

    @VisibleForTesting
    public boolean can(int i) {
        return (getConnectionCapabilities() & i) == i;
    }

    @VisibleForTesting
    public boolean hasProperty(int i) {
        return (this.mConnectionProperties & i) == i;
    }

    private void addChildCall(Call call) {
        if (this.mChildCalls.contains(call)) {
            return;
        }
        this.mHadChildren = true;
        this.mConferenceLevelActiveCall = call;
        this.mChildCalls.add(call);
        maybeAdjustConnectTime(call);
        Log.addEvent(this, "ADD_CHILD", call);
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onChildrenChanged(this);
        }
    }

    private void maybeAdjustConnectTime(Call call) {
        long connectTimeMillis = call.getConnectTimeMillis();
        long connectTimeMillis2 = getConnectTimeMillis();
        if (connectTimeMillis != 0) {
            if (connectTimeMillis2 == 0 || connectTimeMillis < getConnectTimeMillis()) {
                setConnectTimeMillis(connectTimeMillis);
            }
        }
    }

    private void removeChildCall(Call call) {
        if (this.mChildCalls.remove(call)) {
            Log.addEvent(this, "REMOVE_CHILD", call);
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onChildrenChanged(this);
            }
        }
    }

    public boolean isRespondViaSmsCapable() {
        return (this.mState != 4 || getHandle() == null || this.mPhoneNumberUtilsAdapter.isUriNumber(getHandle().toString()) || ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).getAndUpdateDefaultRespondViaMessageApplication() == null) ? false : true;
    }

    List<String> getCannedSmsResponses() {
        return this.mCannedSmsResponses;
    }

    private void fixParentAfterDisconnect() {
        setParentAndChildCall(null);
    }

    private boolean isRinging(String str) {
        int i = this.mState;
        if (i == 4 || i == 11) {
            return true;
        }
        Log.i(this, "Request to %s a non-ringing call %s", new Object[]{str, this});
        return false;
    }

    private void decrementAssociatedCallCount(ServiceBinder serviceBinder) {
        if (serviceBinder != null) {
            serviceBinder.decrementAssociatedCallCount();
        }
    }

    private void startCallerInfoLookup() {
        this.mCallerInfo = null;
        this.mCallsManager.getCallerInfoLookupHelper().startLookup(this.mHandle, this.mCallerInfoQueryListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setCallerInfo(android.net.Uri r3, android.telecom.CallerInfo r4) {
        /*
            r2 = this;
            java.lang.String r0 = "setCallerInfo"
            android.os.Trace.beginSection(r0)
            r0 = 0
            if (r4 != 0) goto L10
            java.lang.Object[] r3 = new java.lang.Object[r0]
            java.lang.String r4 = "CallerInfo lookup returned null, skipping update"
            android.telecom.Log.i(r2, r4, r3)
            return
        L10:
            if (r3 == 0) goto L22
            android.net.Uri r1 = r2.mHandle
            boolean r3 = r3.equals(r1)
            if (r3 != 0) goto L22
            java.lang.Object[] r3 = new java.lang.Object[r0]
            java.lang.String r4 = "setCallerInfo received stale caller info for an old handle. Ignoring."
            android.telecom.Log.i(r2, r4, r3)
            return
        L22:
            r2.mCallerInfo = r4
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]
            android.net.Uri r1 = r2.mHandle
            java.lang.String r1 = android.telecom.Log.piiHandle(r1)
            r3[r0] = r1
            r0 = 1
            r3[r0] = r4
            java.lang.String r4 = "CallerInfo received for %s: %s"
            android.telecom.Log.i(r2, r4, r3)
            android.telecom.CallerInfo r3 = r2.mCallerInfo
            android.net.Uri r3 = r3.getContactDisplayPhotoUri()
            if (r3 == 0) goto L49
            android.telecom.CallerInfo r3 = r2.mCallerInfo
            android.graphics.Bitmap r4 = r3.cachedPhotoIcon
            if (r4 != 0) goto L49
            android.graphics.drawable.Drawable r3 = r3.cachedPhoto
            if (r3 == 0) goto L5f
        L49:
            java.util.Set<com.android.server.telecom.Call$Listener> r3 = r2.mListeners
            java.util.Iterator r3 = r3.iterator()
        L4f:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L5f
            java.lang.Object r4 = r3.next()
            com.android.server.telecom.Call$Listener r4 = (com.android.server.telecom.Call.Listener) r4
            r4.onCallerInfoChanged(r2)
            goto L4f
        L5f:
            android.os.Trace.endSection()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.Call.setCallerInfo(android.net.Uri, android.telecom.CallerInfo):void");
    }

    public CallerInfo getCallerInfo() {
        return this.mCallerInfo;
    }

    private void maybeLoadCannedSmsResponses() {
        if (this.mCallDirection == 2 && isRespondViaSmsCapable() && !this.mCannedSmsResponsesLoadingStarted) {
            Log.d(this, "maybeLoadCannedSmsResponses: starting task to load messages", new Object[0]);
            this.mCannedSmsResponsesLoadingStarted = true;
            this.mCallsManager.getRespondViaSmsManager().loadCannedTextMessages(new Response<Void, List<String>>() { // from class: com.android.server.telecom.Call.3
                public void onResult(Void r3, List<String>... listArr) {
                    if (listArr.length > 0) {
                        Log.d(this, "maybeLoadCannedSmsResponses: got %s", new Object[]{listArr[0]});
                        Call.this.mCannedSmsResponses = listArr[0];
                        Iterator it = Call.this.mListeners.iterator();
                        while (it.hasNext()) {
                            ((Listener) it.next()).onCannedSmsResponsesLoaded(Call.this);
                        }
                    }
                }

                public void onError(Void r2, int i, String str) {
                    Log.w(Call.this, "Error obtaining canned SMS responses: %d %s", new Object[]{Integer.valueOf(i), str});
                }
            }, this.mContext);
            return;
        }
        Log.d(this, "maybeLoadCannedSmsResponses: doing nothing", new Object[0]);
    }

    public void setStartWithSpeakerphoneOn(boolean z) {
        this.mSpeakerphoneOn = z;
    }

    public boolean getStartWithSpeakerphoneOn() {
        return this.mSpeakerphoneOn;
    }

    public void setRequestedToStartWithRtt() {
        this.mDidRequestToStartWithRtt = true;
    }

    public void stopRtt() {
        ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
        if (connectionServiceWrapper != null) {
            connectionServiceWrapper.stopRtt(this);
        } else {
            Log.w(this, "stopRtt() called before connection service is set.", new Object[0]);
        }
    }

    public void sendRttRequest() {
        createRttStreams();
        this.mConnectionService.startRtt(this, getInCallToCsRttPipeForCs(), getCsToInCallRttPipeForCs());
    }

    private boolean areRttStreamsInitialized() {
        return (this.mInCallToConnectionServiceStreams == null || this.mConnectionServiceToInCallStreams == null) ? false : true;
    }

    public void createRttStreams() {
        if (areRttStreamsInitialized()) {
            return;
        }
        Log.i(this, "Initializing RTT streams", new Object[0]);
        try {
            this.mInCallToConnectionServiceStreams = ParcelFileDescriptor.createReliablePipe();
            this.mConnectionServiceToInCallStreams = ParcelFileDescriptor.createReliablePipe();
        } catch (IOException e) {
            Log.e(this, e, "Failed to create pipes for RTT call.", new Object[0]);
        }
    }

    public void onRttConnectionFailure(int i) {
        Log.i(this, "Got RTT initiation failure with reason %d", new Object[]{Integer.valueOf(i)});
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onRttInitiationFailure(this, i);
        }
    }

    public void onRemoteRttRequest() {
        if (isRttCall()) {
            Log.w(this, "Remote RTT request on a call that's already RTT", new Object[0]);
            return;
        }
        this.mPendingRttRequestId = this.mCallsManager.getNextRttRequestId();
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onRemoteRttRequest(this, this.mPendingRttRequestId);
        }
    }

    public void handleRttRequestResponse(int i, boolean z) {
        int i2 = this.mPendingRttRequestId;
        if (i2 == -1) {
            Log.w(this, "Response received to a nonexistent RTT request: %d", new Object[]{Integer.valueOf(i)});
            return;
        }
        if (i != i2) {
            Log.w(this, "Response ID %d does not match expected %d", new Object[]{Integer.valueOf(i), Integer.valueOf(this.mPendingRttRequestId)});
            return;
        }
        if (z) {
            createRttStreams();
            Log.i(this, "RTT request %d accepted.", new Object[]{Integer.valueOf(i)});
            this.mConnectionService.respondToRttRequest(this, getInCallToCsRttPipeForCs(), getCsToInCallRttPipeForCs());
        } else {
            Log.i(this, "RTT request %d rejected.", new Object[]{Integer.valueOf(i)});
            this.mConnectionService.respondToRttRequest(this, null, null);
        }
    }

    public boolean isRttCall() {
        return (this.mConnectionProperties & 256) == 256;
    }

    public boolean wasEverRttCall() {
        return this.mWasEverRtt;
    }

    public ParcelFileDescriptor getCsToInCallRttPipeForCs() {
        ParcelFileDescriptor[] parcelFileDescriptorArr = this.mConnectionServiceToInCallStreams;
        if (parcelFileDescriptorArr == null) {
            return null;
        }
        return parcelFileDescriptorArr[1];
    }

    public ParcelFileDescriptor getInCallToCsRttPipeForCs() {
        ParcelFileDescriptor[] parcelFileDescriptorArr = this.mInCallToConnectionServiceStreams;
        if (parcelFileDescriptorArr == null) {
            return null;
        }
        return parcelFileDescriptorArr[0];
    }

    public ParcelFileDescriptor getCsToInCallRttPipeForInCall() {
        ParcelFileDescriptor[] parcelFileDescriptorArr = this.mConnectionServiceToInCallStreams;
        if (parcelFileDescriptorArr == null) {
            return null;
        }
        return parcelFileDescriptorArr[0];
    }

    public ParcelFileDescriptor getInCallToCsRttPipeForInCall() {
        ParcelFileDescriptor[] parcelFileDescriptorArr = this.mInCallToConnectionServiceStreams;
        if (parcelFileDescriptorArr == null) {
            return null;
        }
        return parcelFileDescriptorArr[1];
    }

    public int getRttMode() {
        return this.mRttMode;
    }

    public void setVideoProvider(IVideoProvider iVideoProvider) {
        Log.v(this, "setVideoProvider", new Object[0]);
        VideoProviderProxy videoProviderProxy = this.mVideoProviderProxy;
        if (videoProviderProxy != null) {
            videoProviderProxy.clearVideoCallback();
            this.mVideoProviderProxy = null;
        }
        if (iVideoProvider != null) {
            try {
                this.mVideoProviderProxy = new VideoProviderProxy(this.mLock, iVideoProvider, this, this.mCallsManager);
            } catch (RemoteException unused) {
            }
        }
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onVideoCallProviderChanged(this);
        }
    }

    public IVideoProvider getVideoProvider() {
        VideoProviderProxy videoProviderProxy = this.mVideoProviderProxy;
        if (videoProviderProxy == null) {
            return null;
        }
        return videoProviderProxy.getInterface();
    }

    public VideoProviderProxy getVideoProviderProxy() {
        return this.mVideoProviderProxy;
    }

    public int getVideoState() {
        return this.mVideoState;
    }

    public int getVideoStateHistory() {
        return this.mVideoStateHistory;
    }

    public void setVideoState(int i) {
        if (!isVideoCallingSupportedByPhoneAccount()) {
            Log.d(this, "setVideoState: videoState=%s defaulted to audio (video not supported)", new Object[]{VideoProfile.videoStateToString(i)});
            i = 0;
        }
        if (isActive() || getState() == 7) {
            this.mVideoStateHistory |= i;
        }
        int i2 = this.mVideoState;
        this.mVideoState = i;
        if (i != i2) {
            Log.addEvent(this, "VIDEO_STATE_CHANGED", VideoProfile.videoStateToString(i));
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onVideoStateChanged(this, i2, this.mVideoState);
            }
        }
        if (VideoProfile.isVideo(i)) {
            this.mAnalytics.setCallIsVideo(true);
        }
    }

    public boolean getIsVoipAudioMode() {
        return this.mIsVoipAudioMode;
    }

    public void setIsVoipAudioMode(boolean z) {
        this.mIsVoipAudioMode = z;
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onIsVoipAudioModeChanged(this);
        }
    }

    public StatusHints getStatusHints() {
        return this.mStatusHints;
    }

    public void setStatusHints(StatusHints statusHints) {
        this.mStatusHints = statusHints;
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onStatusHintsChanged(this);
        }
    }

    public boolean isUnknown() {
        return this.mCallDirection == 3;
    }

    public boolean isLocallyDisconnecting() {
        return this.mIsLocallyDisconnecting;
    }

    private void setLocallyDisconnecting(boolean z) {
        this.mIsLocallyDisconnecting = z;
    }

    public UserHandle getInitiatingUser() {
        return this.mInitiatingUser;
    }

    public void setInitiatingUser(UserHandle userHandle) {
        Preconditions.checkNotNull(userHandle);
        this.mInitiatingUser = userHandle;
    }

    public boolean isDisconnected() {
        return getState() == 7 || getState() == 8;
    }

    public boolean isNew() {
        return getState() == 0;
    }

    public void setCallDataUsage(long j) {
        this.mCallDataUsage = j;
    }

    public long getCallDataUsage() {
        return this.mCallDataUsage;
    }

    public boolean isRemotelyHeld() {
        return this.mIsRemotelyHeld;
    }

    public void onConnectionEvent(String str, Bundle bundle) {
        Log.addEvent(this, "CONNECTION_EVENT", str);
        if ("android.telecom.event.ON_HOLD_TONE_START".equals(str)) {
            this.mIsRemotelyHeld = true;
            Log.addEvent(this, "REMOTELY_HELD");
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onHoldToneRequested(this);
            }
            return;
        }
        if ("android.telecom.event.ON_HOLD_TONE_END".equals(str)) {
            this.mIsRemotelyHeld = false;
            Log.addEvent(this, "REMOTELY_UNHELD");
            Iterator<Listener> it2 = this.mListeners.iterator();
            while (it2.hasNext()) {
                it2.next().onHoldToneRequested(this);
            }
            return;
        }
        if ("android.telecom.event.CALL_HOLD_FAILED".equals(str)) {
            Iterator<Listener> it3 = this.mListeners.iterator();
            while (it3.hasNext()) {
                it3.next().onCallHoldFailed(this);
            }
        } else if ("android.telecom.event.CALL_SWITCH_FAILED".equals(str)) {
            Iterator<Listener> it4 = this.mListeners.iterator();
            while (it4.hasNext()) {
                it4.next().onCallSwitchFailed(this);
            }
        } else {
            Iterator<Listener> it5 = this.mListeners.iterator();
            while (it5.hasNext()) {
                it5.next().onConnectionEvent(this, str, bundle);
            }
        }
    }

    public void onHandoverComplete() {
        Log.i(this, "onHandoverComplete; callId=%s", new Object[]{getId()});
        ConnectionServiceWrapper connectionServiceWrapper = this.mConnectionService;
        if (connectionServiceWrapper != null) {
            connectionServiceWrapper.handoverComplete(this);
        }
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onHandoverComplete(this);
        }
    }

    public void onHandoverFailed(int i) {
        Log.i(this, "onHandoverFailed; callId=%s, handoverError=%d", new Object[]{getId(), Integer.valueOf(i)});
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onHandoverFailed(this, i);
        }
    }

    public void setOriginalConnectionId(String str) {
        this.mOriginalConnectionId = str;
    }

    public String getOriginalConnectionId() {
        return this.mOriginalConnectionId;
    }

    public ConnectionServiceFocusManager getConnectionServiceFocusManager() {
        return this.mCallsManager.getConnectionServiceFocusManager();
    }

    private void requestHandover(PhoneAccountHandle phoneAccountHandle, int i, Bundle bundle, boolean z) {
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onHandoverRequested(this, phoneAccountHandle, i, bundle, z);
        }
    }

    private TelephonyManager getTelephonyManager() {
        return (TelephonyManager) this.mContext.getSystemService(TelephonyManager.class);
    }

    public void setConferenceState(boolean z) {
        this.mIsConference = z;
        Log.addEvent(this, "CONF_STATE_CHANGED", "isConference=" + z);
        Iterator<Listener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onConferenceStateChanged(this, z);
        }
    }

    public void setCallDirection(int i) {
        if (this.mCallDirection != i) {
            Log.addEvent(this, "CALL_DIRECTION_CHANGED", "callDirection=" + i);
            this.mCallDirection = i;
            Iterator<Listener> it = this.mListeners.iterator();
            while (it.hasNext()) {
                it.next().onCallDirectionChanged(this);
            }
        }
    }

    private void updateVideoHistoryViaState(int i, int i2) {
        if ((i == 3 && i2 == 5) || (i == 4 && i2 == 11)) {
            this.mVideoStateHistory = this.mVideoState;
        }
        this.mVideoStateHistory |= this.mVideoState;
    }

    boolean wasHighDefAudio() {
        return this.mWasHighDefAudio;
    }

    boolean wasWifi() {
        return this.mWasWifi;
    }

    public boolean wasVolte() {
        return this.mWasVolte;
    }

    public boolean hasGoneActiveBefore() {
        return this.mHasGoneActiveBefore;
    }

    public void maybeEnableSpeakerForVideoUpgrade(int i) {
        if (this.mCallsManager.isSpeakerphoneAutoEnabledForVideoCalls(i)) {
            Log.i(this, "maybeEnableSpeakerForVideoCall; callId=%s, auto-enable speaker for call upgraded to video.", new Object[0]);
            this.mCallsManager.setAudioRoute(8, null);
        }
    }

    public void setPostCallPackageName(String str) {
        this.mPostCallPackageName = str;
    }

    public String getPostCallPackageName() {
        return this.mPostCallPackageName;
    }
}
