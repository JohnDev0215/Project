package com.android.server.telecom.components;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.UserHandle;
import android.os.UserManager;
import android.telecom.Log;
import android.telecom.TelecomManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import com.android.server.telecom.R;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.TelephonyUtil;
import com.android.server.telecom.UserUtil;

/* JADX INFO: loaded from: classes.dex */
public class UserCallIntentProcessor {
    private final Context mContext;
    private final UserHandle mUserHandle;

    public UserCallIntentProcessor(Context context, UserHandle userHandle) {
        this.mContext = context;
        this.mUserHandle = userHandle;
    }

    public void processIntent(Intent intent, String str, boolean z, boolean z2) {
        if (isVoiceCapable()) {
            String action = intent.getAction();
            if ("android.intent.action.CALL".equals(action) || "android.intent.action.CALL_PRIVILEGED".equals(action) || "android.intent.action.CALL_EMERGENCY".equals(action)) {
                processOutgoingCallIntent(intent, str, z, z2);
            }
        }
    }

    private void processOutgoingCallIntent(Intent intent, String str, boolean z, boolean z2) {
        Intent intentCreateAdminSupportIntent;
        Uri data = intent.getData();
        String scheme = data.getScheme();
        String schemeSpecificPart = data.getSchemeSpecificPart();
        if ("tel".equals(scheme) && PhoneNumberUtils.isUriNumber(schemeSpecificPart)) {
            data = Uri.fromParts("sip", schemeSpecificPart, null);
        }
        if (!UserUtil.isManagedProfile(this.mContext, this.mUserHandle) && !TelephonyUtil.shouldProcessAsEmergency(this.mContext, data)) {
            UserManager userManager = (UserManager) this.mContext.getSystemService("user");
            if (userManager.hasBaseUserRestriction("no_outgoing_calls", this.mUserHandle)) {
                showErrorDialogForRestrictedOutgoingCall(this.mContext, R.string.outgoing_call_not_allowed_user_restriction);
                Log.w(this, "Rejecting non-emergency phone call due to DISALLOW_OUTGOING_CALLS restriction", new Object[0]);
                return;
            } else if (userManager.hasUserRestriction("no_outgoing_calls", this.mUserHandle)) {
                DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService(DevicePolicyManager.class);
                if (devicePolicyManager == null || (intentCreateAdminSupportIntent = devicePolicyManager.createAdminSupportIntent("no_outgoing_calls")) == null) {
                    return;
                }
                this.mContext.startActivity(intentCreateAdminSupportIntent);
                return;
            }
        }
        if (!z && !TelephonyUtil.shouldProcessAsEmergency(this.mContext, data)) {
            showErrorDialogForRestrictedOutgoingCall(this.mContext, R.string.outgoing_call_not_allowed_no_permission);
            Log.w(this, "Rejecting non-emergency phone call because android.permission.CALL_PHONE permission is not granted.", new Object[0]);
            return;
        }
        Log.d(this, "processOutgoingCallIntent videoState = " + intent.getIntExtra("android.telecom.extra.START_CALL_WITH_VIDEO_STATE", 0), new Object[0]);
        intent.putExtra("initiating_user", this.mUserHandle);
        sendIntentToDestination(intent, z2, str);
    }

    private boolean isVoiceCapable() {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).isVoiceCapable();
    }

    private boolean sendIntentToDestination(Intent intent, boolean z, String str) {
        intent.putExtra("is_incoming_call", false);
        intent.setFlags(268435456);
        if (z) {
            Log.i(this, "sendIntentToDestination: send intent to Telecom directly.", new Object[0]);
            synchronized (TelecomSystem.getInstance().getLock()) {
                TelecomSystem.getInstance().getCallIntentProcessor().processIntent(intent, str);
            }
            return true;
        }
        Log.i(this, "sendIntentToDestination: trampoline to Telecom.", new Object[0]);
        ((TelecomManager) this.mContext.getSystemService("telecom")).handleCallIntent(intent, str);
        return true;
    }

    private static void showErrorDialogForRestrictedOutgoingCall(Context context, int i) {
        Intent intent = new Intent(context, (Class<?>) ErrorDialogActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("error_message_id", i);
        context.startActivityAsUser(intent, UserHandle.CURRENT);
    }
}
