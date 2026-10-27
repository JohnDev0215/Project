package com.android.server.telecom;

import android.app.BroadcastOptions;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserHandle;
import android.telecom.GatewayInfo;
import android.telecom.Log;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.callredirection.CallRedirectionProcessor;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public class NewOutgoingCallIntentBroadcaster {
    private Call mCall;
    private final CallsManager mCallsManager;
    private final Context mContext;
    private final DefaultDialerCache mDefaultDialerCache;
    private final Intent mIntent;
    private final boolean mIsDefaultOrSystemPhoneApp;
    private final TelecomSystem.SyncRoot mLock;
    private final PhoneNumberUtilsAdapter mPhoneNumberUtilsAdapter;

    public static class CallDisposition {
        Uri callingAddress;
        String number;
        public boolean callImmediately = false;
        public boolean sendBroadcast = true;
        public boolean requestRedirection = true;
        public int disconnectCause = 0;
    }

    @VisibleForTesting
    public NewOutgoingCallIntentBroadcaster(Context context, CallsManager callsManager, Intent intent, PhoneNumberUtilsAdapter phoneNumberUtilsAdapter, boolean z, DefaultDialerCache defaultDialerCache) {
        this.mContext = context;
        this.mCallsManager = callsManager;
        this.mIntent = intent;
        this.mPhoneNumberUtilsAdapter = phoneNumberUtilsAdapter;
        this.mIsDefaultOrSystemPhoneApp = z;
        this.mLock = callsManager.getLock();
        this.mDefaultDialerCache = defaultDialerCache;
    }

    public class NewOutgoingCallBroadcastIntentReceiver extends BroadcastReceiver {
        public NewOutgoingCallBroadcastIntentReceiver() {
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0070 A[Catch: all -> 0x011a, Merged into TryCatch #1 {all -> 0x011d, blocks: (B:2:0x0000, B:3:0x0010, B:38:0x011c, B:4:0x0011, B:6:0x0046, B:13:0x0070, B:15:0x0078, B:16:0x0081, B:19:0x0089, B:21:0x0095, B:22:0x00a4, B:24:0x00a6, B:28:0x00b7, B:30:0x00d0, B:32:0x00eb, B:33:0x0112, B:31:0x00d8, B:8:0x0059, B:10:0x0061), top: B:43:0x0000 }] */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0089 A[Catch: all -> 0x011a, Merged into TryCatch #1 {all -> 0x011d, blocks: (B:2:0x0000, B:3:0x0010, B:38:0x011c, B:4:0x0011, B:6:0x0046, B:13:0x0070, B:15:0x0078, B:16:0x0081, B:19:0x0089, B:21:0x0095, B:22:0x00a4, B:24:0x00a6, B:28:0x00b7, B:30:0x00d0, B:32:0x00eb, B:33:0x0112, B:31:0x00d8, B:8:0x0059, B:10:0x0061), top: B:43:0x0000 }, TRY_ENTER] */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r13, android.content.Intent r14) {
            /*
                Method dump skipped, instruction units count: 293
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.NewOutgoingCallIntentBroadcaster.NewOutgoingCallBroadcastIntentReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    @VisibleForTesting
    public CallDisposition evaluateCall() {
        PhoneAccount phoneAccountUnchecked;
        CallDisposition callDisposition = new CallDisposition();
        Intent intent = this.mIntent;
        String action = intent.getAction();
        Uri data = intent.getData();
        if (data == null) {
            Log.w(this, "Empty handle obtained from the call intent.", new Object[0]);
            callDisposition.disconnectCause = 7;
            return callDisposition;
        }
        if ("voicemail".equals(data.getScheme())) {
            if ("android.intent.action.CALL".equals(action) || "android.intent.action.CALL_PRIVILEGED".equals(action)) {
                Log.i(this, "Voicemail number dialed. Skipping redirection and broadcast", new Object[]{intent});
                this.mIntent.putExtra("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", 0);
                callDisposition.callImmediately = true;
                callDisposition.requestRedirection = false;
                callDisposition.sendBroadcast = false;
                callDisposition.callingAddress = data;
                return callDisposition;
            }
            Log.i(this, "Unhandled intent %s. Ignoring and not placing call.", new Object[]{intent});
            callDisposition.disconnectCause = 44;
            return callDisposition;
        }
        PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) this.mIntent.getParcelableExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE");
        boolean zIsSelfManaged = (phoneAccountHandle == null || (phoneAccountUnchecked = this.mCallsManager.getPhoneAccountRegistrar().getPhoneAccountUnchecked(phoneAccountHandle)) == null) ? false : phoneAccountUnchecked.isSelfManaged();
        callDisposition.number = "";
        callDisposition.callingAddress = data;
        if (zIsSelfManaged) {
            callDisposition.callImmediately = true;
            callDisposition.sendBroadcast = false;
            callDisposition.requestRedirection = false;
            Log.i(this, "Skipping NewOutgoingCallBroadcast for self-managed call.", new Object[0]);
            return callDisposition;
        }
        String numberFromCallIntent = getNumberFromCallIntent(intent);
        callDisposition.number = numberFromCallIntent;
        if (numberFromCallIntent == null) {
            callDisposition.disconnectCause = 38;
            return callDisposition;
        }
        boolean zIsPotentialEmergencyNumber = isPotentialEmergencyNumber(numberFromCallIntent);
        Log.v(this, "isPotentialEmergencyNumber = %s", new Object[]{Boolean.valueOf(zIsPotentialEmergencyNumber)});
        String strCalculateCallIntentAction = calculateCallIntentAction(intent, zIsPotentialEmergencyNumber);
        intent.setAction(strCalculateCallIntentAction);
        if ("android.intent.action.CALL".equals(strCalculateCallIntentAction)) {
            if (zIsPotentialEmergencyNumber) {
                if (!this.mIsDefaultOrSystemPhoneApp) {
                    Log.w(this, "Cannot call potential emergency number %s with CALL Intent %s unless caller is system or default dialer.", new Object[]{numberFromCallIntent, intent});
                    launchSystemDialer(intent.getData());
                    callDisposition.disconnectCause = 44;
                    return callDisposition;
                }
                callDisposition.callImmediately = true;
                callDisposition.requestRedirection = false;
            }
        } else {
            if (!"android.intent.action.CALL_EMERGENCY".equals(strCalculateCallIntentAction)) {
                Log.w(this, "Unhandled Intent %s. Ignoring and not placing call.", new Object[]{intent});
                callDisposition.disconnectCause = 7;
                return callDisposition;
            }
            if (!zIsPotentialEmergencyNumber) {
                Log.w(this, "Cannot call non-potential-emergency number %s with EMERGENCY_CALL Intent %s.", new Object[]{numberFromCallIntent, intent});
                callDisposition.disconnectCause = 44;
                return callDisposition;
            }
            callDisposition.callImmediately = true;
            callDisposition.requestRedirection = false;
        }
        callDisposition.callingAddress = Uri.fromParts(this.mPhoneNumberUtilsAdapter.isUriNumber(numberFromCallIntent) ? "sip" : "tel", numberFromCallIntent, null);
        return callDisposition;
    }

    private String getNumberFromCallIntent(Intent intent) {
        String numberFromIntent = this.mPhoneNumberUtilsAdapter.getNumberFromIntent(intent, this.mContext);
        if (TextUtils.isEmpty(numberFromIntent)) {
            Log.w(this, "Empty number obtained from the call intent.", new Object[0]);
            return null;
        }
        if (this.mPhoneNumberUtilsAdapter.isUriNumber(numberFromIntent)) {
            return numberFromIntent;
        }
        return this.mPhoneNumberUtilsAdapter.stripSeparators(this.mPhoneNumberUtilsAdapter.convertKeypadLettersToDigits(numberFromIntent));
    }

    public void processCall(Call call, CallDisposition callDisposition) {
        boolean zCanMakeCallRedirectionWithService;
        this.mCall = call;
        boolean z = false;
        if (callDisposition.callImmediately) {
            placeOutgoingCallImmediately(this.mCall, callDisposition.callingAddress, null, this.mIntent.getBooleanExtra("android.telecom.extra.START_CALL_WITH_SPEAKERPHONE", false), this.mIntent.getIntExtra("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", 0));
        }
        if (callDisposition.requestRedirection) {
            Context context = this.mContext;
            CallsManager callsManager = this.mCallsManager;
            Call call2 = this.mCall;
            Uri uri = callDisposition.callingAddress;
            PhoneAccountRegistrar phoneAccountRegistrar = callsManager.getPhoneAccountRegistrar();
            Intent intent = this.mIntent;
            CallRedirectionProcessor callRedirectionProcessor = new CallRedirectionProcessor(context, callsManager, call2, uri, phoneAccountRegistrar, getGateWayInfoFromIntent(intent, intent.getData()), this.mIntent.getBooleanExtra("android.telecom.extra.START_CALL_WITH_SPEAKERPHONE", false), this.mIntent.getIntExtra("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", 0));
            zCanMakeCallRedirectionWithService = callRedirectionProcessor.canMakeCallRedirectionWithService();
            if (zCanMakeCallRedirectionWithService) {
                callRedirectionProcessor.performCallRedirection();
            }
        } else {
            zCanMakeCallRedirectionWithService = false;
        }
        if (callDisposition.sendBroadcast) {
            UserHandle initiatingUser = this.mCall.getInitiatingUser();
            Log.i(this, "Sending NewOutgoingCallBroadcast for %s to %s", new Object[]{this.mCall, initiatingUser});
            Intent intent2 = this.mIntent;
            String str = callDisposition.number;
            if (!callDisposition.callImmediately && !zCanMakeCallRedirectionWithService) {
                z = true;
            }
            broadcastIntent(intent2, str, z, initiatingUser);
        }
    }

    private void broadcastIntent(Intent intent, String str, boolean z, UserHandle userHandle) {
        Intent intent2 = new Intent("android.intent.action.NEW_OUTGOING_CALL");
        if (str != null) {
            intent2.putExtra("android.intent.extra.PHONE_NUMBER", str);
        }
        intent2.addFlags(285212672);
        Log.v(this, "Broadcasting intent: %s.", new Object[]{intent2});
        checkAndCopyProviderExtras(intent, intent2);
        BroadcastOptions broadcastOptionsMakeBasic = BroadcastOptions.makeBasic();
        broadcastOptionsMakeBasic.setBackgroundActivityStartsAllowed(true);
        this.mContext.sendOrderedBroadcastAsUser(intent2, userHandle, "android.permission.PROCESS_OUTGOING_CALLS", 54, broadcastOptionsMakeBasic.toBundle(), z ? new NewOutgoingCallBroadcastIntentReceiver() : null, null, -1, str, null);
    }

    public void checkAndCopyProviderExtras(Intent intent, Intent intent2) {
        if (intent == null) {
            return;
        }
        if (hasGatewayProviderExtras(intent)) {
            intent2.putExtra("com.android.phone.extra.GATEWAY_PROVIDER_PACKAGE", intent.getStringExtra("com.android.phone.extra.GATEWAY_PROVIDER_PACKAGE"));
            intent2.putExtra("com.android.phone.extra.GATEWAY_URI", intent.getStringExtra("com.android.phone.extra.GATEWAY_URI"));
            Log.d(this, "Found and copied gateway provider extras to broadcast intent.", new Object[0]);
            return;
        }
        Log.d(this, "No provider extras found in call intent.", new Object[0]);
    }

    private boolean hasGatewayProviderExtras(Intent intent) {
        return (TextUtils.isEmpty(intent.getStringExtra("com.android.phone.extra.GATEWAY_PROVIDER_PACKAGE")) || TextUtils.isEmpty(intent.getStringExtra("com.android.phone.extra.GATEWAY_URI"))) ? false : true;
    }

    private static Uri getGatewayUriFromString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return Uri.parse(str);
    }

    public static GatewayInfo getGateWayInfoFromIntent(Intent intent, Uri uri) {
        if (intent == null) {
            return null;
        }
        String stringExtra = intent.getStringExtra("com.android.phone.extra.GATEWAY_PROVIDER_PACKAGE");
        Uri gatewayUriFromString = getGatewayUriFromString(intent.getStringExtra("com.android.phone.extra.GATEWAY_URI"));
        if (TextUtils.isEmpty(stringExtra) || gatewayUriFromString == null) {
            return null;
        }
        return new GatewayInfo(stringExtra, gatewayUriFromString, uri);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void placeOutgoingCallImmediately(Call call, Uri uri, GatewayInfo gatewayInfo, boolean z, int i) {
        Log.i(this, "Placing call immediately instead of waiting for OutgoingCallBroadcastReceiver", new Object[0]);
        this.mCall.setNewOutgoingCallIntentBroadcastIsDone();
        this.mCallsManager.placeOutgoingCall(call, uri, gatewayInfo, z, i);
    }

    private void launchSystemDialer(Uri uri) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this.mDefaultDialerCache.getSystemDialerApplication(), this.mContext.getResources().getString(R.string.dialer_default_class)));
        intent.setAction("android.intent.action.DIAL");
        intent.setData(uri);
        intent.setFlags(268435456);
        Log.v(this, "calling startActivity for default dialer: %s", new Object[]{intent});
        this.mContext.startActivityAsUser(intent, UserHandle.CURRENT);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPotentialEmergencyNumber(String str) {
        Log.v(this, "Checking restrictions for number : %s", new Object[]{Log.pii(str)});
        if (str == null) {
            return false;
        }
        try {
            return ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).isPotentialEmergencyNumber(str);
        } catch (Exception e) {
            Log.e(this, e, "isPotentialEmergencyNumber: Telephony threw an exception.", new Object[0]);
            return false;
        }
    }

    private String calculateCallIntentAction(Intent intent, boolean z) {
        String str;
        String action = intent.getAction();
        if (!"android.intent.action.CALL_PRIVILEGED".equals(action)) {
            return action;
        }
        if (z) {
            Log.i(this, "ACTION_CALL_PRIVILEGED is used while the number is a potential emergency number. Using ACTION_CALL_EMERGENCY as an action instead.", new Object[0]);
            str = "android.intent.action.CALL_EMERGENCY";
        } else {
            str = "android.intent.action.CALL";
        }
        Log.v(this, " - updating action from CALL_PRIVILEGED to %s", new Object[]{str});
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getDisconnectTimeoutFromApp(Bundle bundle, long j) {
        if (bundle == null) {
            return j;
        }
        long j2 = bundle.getLong("android.telecom.extra.NEW_OUTGOING_CALL_CANCEL_TIMEOUT", j);
        if (j2 < 0) {
            j2 = 0;
        }
        return Math.min(j2, Timeouts.getMaxNewOutgoingCallCancelMillis(this.mContext.getContentResolver()));
    }
}
