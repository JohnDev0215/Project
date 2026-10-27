package com.android.server.telecom;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.telecom.Log;
import android.telecom.Response;
import android.telephony.PhoneNumberUtils;
import android.telephony.SmsManager;
import android.telephony.SubscriptionManager;
import android.text.SpannableString;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.server.telecom.TelecomSystem;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RespondViaSmsManager extends CallsManagerListenerBase {
    private final CallsManager mCallsManager;
    private final TelecomSystem.SyncRoot mLock;

    private static final class MessageSentReceiver extends BroadcastReceiver {
        private final String mContactName;
        private final int mNumMessageParts;
        private int mNumMessagesSent = 0;

        MessageSentReceiver(String str, int i) {
            this.mContactName = str;
            this.mNumMessageParts = i;
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == -1) {
                int i = this.mNumMessagesSent + 1;
                this.mNumMessagesSent = i;
                if (i == this.mNumMessageParts) {
                    RespondViaSmsManager.showMessageResultToast(this.mContactName, context, true);
                    context.unregisterReceiver(this);
                    return;
                }
                return;
            }
            context.unregisterReceiver(this);
            RespondViaSmsManager.showMessageResultToast(this.mContactName, context, false);
            Log.w(RespondViaSmsManager.class.getSimpleName(), "Message failed with error %s", new Object[]{Integer.valueOf(getResultCode())});
        }
    }

    public RespondViaSmsManager(CallsManager callsManager, TelecomSystem.SyncRoot syncRoot) {
        this.mCallsManager = callsManager;
        this.mLock = syncRoot;
    }

    public void loadCannedTextMessages(final Response<Void, List<String>> response, final Context context) {
        new Thread() { // from class: com.android.server.telecom.RespondViaSmsManager.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Log.d(RespondViaSmsManager.this, "loadCannedResponses() starting", new Object[0]);
                QuickResponseUtils.maybeMigrateLegacyQuickResponses(context);
                SharedPreferences sharedPreferences = context.getSharedPreferences("respond_via_sms_prefs", 4);
                Resources resources = context.getResources();
                ArrayList arrayList = new ArrayList(4);
                QuickResponseUtils.maybeResetQuickResponses(context, sharedPreferences);
                arrayList.add(0, sharedPreferences.getString("canned_response_pref_1", resources.getString(R.string.respond_via_sms_canned_response_1)));
                arrayList.add(1, sharedPreferences.getString("canned_response_pref_2", resources.getString(R.string.respond_via_sms_canned_response_2)));
                arrayList.add(2, sharedPreferences.getString("canned_response_pref_3", resources.getString(R.string.respond_via_sms_canned_response_3)));
                arrayList.add(3, sharedPreferences.getString("canned_response_pref_4", resources.getString(R.string.respond_via_sms_canned_response_4)));
                Log.d(RespondViaSmsManager.this, "loadCannedResponses() completed, found responses: %s", new Object[]{arrayList.toString()});
                synchronized (RespondViaSmsManager.this.mLock) {
                    response.onResult((Object) null, new List[]{arrayList});
                }
            }
        }.start();
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onIncomingCallRejected(Call call, boolean z, String str) {
        if (!z || call.getHandle() == null || call.can(4194304)) {
            return;
        }
        rejectCallWithMessage(call.getContext(), call.getHandle().getSchemeSpecificPart(), str, this.mCallsManager.getPhoneAccountRegistrar().getSubscriptionIdForPhoneAccount(call.getTargetPhoneAccount()), call.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showMessageResultToast(String str, Context context, boolean z) {
        String str2 = String.format(context.getResources().getString(z ? R.string.respond_via_sms_confirmation_format : R.string.respond_via_sms_failure_format), str);
        int iIndexOf = str2.indexOf(str);
        int length = str.length() + iIndexOf;
        SpannableString spannableString = new SpannableString(str2);
        PhoneNumberUtils.addTtsSpan(spannableString, iIndexOf, length);
        Toast.makeText(context, spannableString, 1).show();
    }

    private void rejectCallWithMessage(Context context, String str, String str2, int i, String str3) {
        if (TextUtils.isEmpty(str2)) {
            Log.w(this, "Couldn't send SMS message: empty text message. ", new Object[0]);
            return;
        }
        if (!SubscriptionManager.isValidSubscriptionId(i)) {
            Log.w(this, "Couldn't send SMS message: Invalid SubId: " + i, new Object[0]);
            return;
        }
        SmsManager smsManagerForSubscriptionId = SmsManager.getSmsManagerForSubscriptionId(i);
        try {
            ArrayList<String> arrayListDivideMessage = smsManagerForSubscriptionId.divideMessage(str2);
            ArrayList arrayList = new ArrayList(arrayListDivideMessage.size());
            for (int i2 = 0; i2 < arrayListDivideMessage.size(); i2++) {
                arrayList.add(PendingIntent.getBroadcast(context, i2, new Intent("com.android.server.telecom.MESSAGE_SENT"), 1073741824));
            }
            if (TextUtils.isEmpty(str3)) {
                str3 = str;
            }
            context.registerReceiver(new MessageSentReceiver(str3, arrayListDivideMessage.size()), new IntentFilter("com.android.server.telecom.MESSAGE_SENT"));
            smsManagerForSubscriptionId.sendMultipartTextMessage(str, null, arrayListDivideMessage, arrayList, null, context.getOpPackageName(), context.getAttributionTag());
        } catch (IllegalArgumentException e) {
            Log.w(this, "Couldn't send SMS message: " + e.getMessage(), new Object[0]);
        }
    }
}
