package com.android.server.telecom;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.Trace;
import android.os.UserHandle;
import android.os.UserManager;
import android.telecom.DefaultDialerManager;
import android.telecom.Log;
import android.telecom.Logging.Session;
import android.telecom.PhoneAccountHandle;
import android.telephony.PhoneNumberUtils;
import android.widget.Toast;
import com.android.server.telecom.NewOutgoingCallIntentBroadcaster;
import com.android.server.telecom.components.ErrorDialogActivity;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes.dex */
public class CallIntentProcessor {
    private final CallsManager mCallsManager;
    private final Context mContext;
    private final DefaultDialerCache mDefaultDialerCache;

    public interface Adapter {
        void processIncomingCallIntent(CallsManager callsManager, Intent intent);

        void processOutgoingCallIntent(Context context, CallsManager callsManager, Intent intent, String str);

        void processUnknownCallIntent(CallsManager callsManager, Intent intent);
    }

    public static class AdapterImpl implements Adapter {
        private final DefaultDialerCache mDefaultDialerCache;

        public AdapterImpl(DefaultDialerCache defaultDialerCache) {
            this.mDefaultDialerCache = defaultDialerCache;
        }

        @Override // com.android.server.telecom.CallIntentProcessor.Adapter
        public void processOutgoingCallIntent(Context context, CallsManager callsManager, Intent intent, String str) {
            CallIntentProcessor.processOutgoingCallIntent(context, callsManager, intent, str, this.mDefaultDialerCache);
        }

        @Override // com.android.server.telecom.CallIntentProcessor.Adapter
        public void processIncomingCallIntent(CallsManager callsManager, Intent intent) {
            CallIntentProcessor.processIncomingCallIntent(callsManager, intent);
        }

        @Override // com.android.server.telecom.CallIntentProcessor.Adapter
        public void processUnknownCallIntent(CallsManager callsManager, Intent intent) {
            CallIntentProcessor.processUnknownCallIntent(callsManager, intent);
        }
    }

    public CallIntentProcessor(Context context, CallsManager callsManager, DefaultDialerCache defaultDialerCache) {
        this.mContext = context;
        this.mCallsManager = callsManager;
        this.mDefaultDialerCache = defaultDialerCache;
    }

    public void processIntent(Intent intent, String str) {
        boolean booleanExtra = intent.getBooleanExtra("is_unknown_call", false);
        Log.i(this, "onReceive - isUnknownCall: %s", new Object[]{Boolean.valueOf(booleanExtra)});
        Trace.beginSection("processNewCallCallIntent");
        if (booleanExtra) {
            processUnknownCallIntent(this.mCallsManager, intent);
        } else {
            processOutgoingCallIntent(this.mContext, this.mCallsManager, intent, str, this.mDefaultDialerCache);
        }
        Trace.endSection();
    }

    static void processOutgoingCallIntent(Context context, CallsManager callsManager, Intent intent, String str, DefaultDialerCache defaultDialerCache) {
        Uri data = intent.getData();
        String scheme = data.getScheme();
        String schemeSpecificPart = data.getSchemeSpecificPart();
        if ("tel".equals(scheme) && PhoneNumberUtils.isUriNumber(schemeSpecificPart)) {
            data = Uri.fromParts("sip", schemeSpecificPart, null);
        }
        Uri uri = data;
        PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) intent.getParcelableExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE");
        Bundle bundleExtra = intent.hasExtra("android.telecom.extra.OUTGOING_CALL_EXTRAS") ? intent.getBundleExtra("android.telecom.extra.OUTGOING_CALL_EXTRAS") : null;
        Bundle bundle = bundleExtra == null ? new Bundle() : bundleExtra;
        if (intent.hasExtra("android.telecom.extra.IS_USER_INTENT_EMERGENCY_CALL")) {
            bundle.putBoolean("android.telecom.extra.IS_USER_INTENT_EMERGENCY_CALL", intent.getBooleanExtra("android.telecom.extra.IS_USER_INTENT_EMERGENCY_CALL", false));
        }
        if (intent.hasExtra("android.telecom.extra.CALL_SUBJECT")) {
            bundle.putString("android.telecom.extra.CALL_SUBJECT", intent.getStringExtra("android.telecom.extra.CALL_SUBJECT"));
        }
        bundle.putInt("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", intent.getIntExtra("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", 0));
        if (!callsManager.isSelfManaged(phoneAccountHandle, (UserHandle) intent.getParcelableExtra("initiating_user"))) {
            if (fixInitiatingUserIfNecessary(context, intent)) {
                Toast.makeText(context, Looper.getMainLooper(), context.getString(R.string.toast_personal_call_msg), 1).show();
            }
        } else {
            Log.i(CallIntentProcessor.class, "processOutgoingCallIntent: skip initiating user check", new Object[0]);
        }
        UserHandle userHandle = (UserHandle) intent.getParcelableExtra("initiating_user");
        final NewOutgoingCallIntentBroadcaster newOutgoingCallIntentBroadcaster = new NewOutgoingCallIntentBroadcaster(context, callsManager, intent, callsManager.getPhoneNumberUtilsAdapter(), defaultDialerCache.isDefaultOrSystemDialer(str, userHandle.getIdentifier()), defaultDialerCache);
        final NewOutgoingCallIntentBroadcaster.CallDisposition callDispositionEvaluateCall = newOutgoingCallIntentBroadcaster.evaluateCall();
        int i = callDispositionEvaluateCall.disconnectCause;
        if (i != 0) {
            showErrorDialog(context, i);
            return;
        }
        CompletableFuture<Call> completableFutureStartOutgoingCall = callsManager.startOutgoingCall(uri, phoneAccountHandle, bundle, userHandle, intent, str);
        final Session sessionCreateSubsession = Log.createSubsession();
        completableFutureStartOutgoingCall.thenAccept(new Consumer() { // from class: com.android.server.telecom.-$$Lambda$CallIntentProcessor$sD3Y3IInzRkH4n23s3aNI3q-DuE
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                CallIntentProcessor.lambda$processOutgoingCallIntent$0(sessionCreateSubsession, newOutgoingCallIntentBroadcaster, callDispositionEvaluateCall, (Call) obj);
            }
        });
    }

    static /* synthetic */ void lambda$processOutgoingCallIntent$0(Session session, NewOutgoingCallIntentBroadcaster newOutgoingCallIntentBroadcaster, NewOutgoingCallIntentBroadcaster.CallDisposition callDisposition, Call call) {
        if (call != null) {
            Log.continueSession(session, "CIP.sNOCI");
            try {
                newOutgoingCallIntentBroadcaster.processCall(call, callDisposition);
            } finally {
                Log.endSession();
            }
        }
    }

    static boolean fixInitiatingUserIfNecessary(Context context, Intent intent) {
        UserHandle userHandle = (UserHandle) intent.getParcelableExtra("initiating_user");
        if (UserUtil.isManagedProfile(context, userHandle)) {
            if (DefaultDialerManager.getInstalledDialerApplications(context, userHandle.getIdentifier()).size() == 0) {
                UserHandle userHandle2 = UserManager.get(context).getProfileParent(userHandle.getIdentifier()).getUserHandle();
                intent.putExtra("initiating_user", userHandle2);
                Log.i(CallIntentProcessor.class, "fixInitiatingUserIfNecessary: no dialer installed for current user; setting initiator to parent %s" + userHandle2, new Object[0]);
                return true;
            }
        }
        return false;
    }

    static void processIncomingCallIntent(CallsManager callsManager, Intent intent) {
        PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) intent.getParcelableExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE");
        if (phoneAccountHandle == null) {
            Log.w(CallIntentProcessor.class, "Rejecting incoming call due to null phone account", new Object[0]);
            return;
        }
        if (phoneAccountHandle.getComponentName() == null) {
            Log.w(CallIntentProcessor.class, "Rejecting incoming call due to null component name", new Object[0]);
            return;
        }
        Bundle bundleExtra = intent.hasExtra("android.telecom.extra.INCOMING_CALL_EXTRAS") ? intent.getBundleExtra("android.telecom.extra.INCOMING_CALL_EXTRAS") : null;
        if (bundleExtra == null) {
            bundleExtra = new Bundle();
        }
        Log.d(CallIntentProcessor.class, "Processing incoming call from connection service [%s]", new Object[]{phoneAccountHandle.getComponentName()});
        callsManager.processIncomingCallIntent(phoneAccountHandle, bundleExtra);
    }

    static void processUnknownCallIntent(CallsManager callsManager, Intent intent) {
        PhoneAccountHandle phoneAccountHandle = (PhoneAccountHandle) intent.getParcelableExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE");
        if (phoneAccountHandle == null) {
            Log.w(CallIntentProcessor.class, "Rejecting unknown call due to null phone account", new Object[0]);
        } else if (phoneAccountHandle.getComponentName() == null) {
            Log.w(CallIntentProcessor.class, "Rejecting unknown call due to null component name", new Object[0]);
        } else {
            callsManager.addNewUnknownCall(phoneAccountHandle, intent.getExtras());
        }
    }

    private static void showErrorDialog(Context context, int i) {
        Intent intent = new Intent(context, (Class<?>) ErrorDialogActivity.class);
        int i2 = (i == 7 || i == 38) ? R.string.outgoing_call_error_no_phone_number_supplied : -1;
        if (i2 != -1) {
            intent.putExtra("error_message_id", i2);
            intent.setFlags(268435456);
            context.startActivityAsUser(intent, UserHandle.CURRENT);
        }
    }
}
