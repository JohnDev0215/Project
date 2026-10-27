package com.android.server.telecom;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.telecom.Log;
import android.widget.Toast;
import com.android.server.telecom.ui.DisconnectedCallNotifier;

/* JADX INFO: loaded from: classes.dex */
public final class TelecomBroadcastIntentProcessor {
    private final CallsManager mCallsManager;
    private final Context mContext;

    public TelecomBroadcastIntentProcessor(Context context, CallsManager callsManager) {
        this.mContext = context;
        this.mCallsManager = callsManager;
    }

    public void processIntent(Intent intent) {
        String action = intent.getAction();
        if ("com.android.server.telecom.ACTION_SEND_SMS_FROM_NOTIFICATION".equals(action) || "com.android.server.telecom.ACTION_CALL_BACK_FROM_NOTIFICATION".equals(action) || "com.android.server.telecom.ACTION_CLEAR_MISSED_CALLS".equals(action)) {
            Log.v(this, "Action received: %s.", new Object[]{action});
            UserHandle userHandle = (UserHandle) intent.getParcelableExtra("userhandle");
            if (userHandle == null) {
                Log.d(this, "user handle can't be null, not processing the broadcast", new Object[0]);
                return;
            }
            MissedCallNotifier missedCallNotifier = this.mCallsManager.getMissedCallNotifier();
            if ("com.android.server.telecom.ACTION_SEND_SMS_FROM_NOTIFICATION".equals(action)) {
                closeSystemDialogs(this.mContext);
                missedCallNotifier.clearMissedCalls(userHandle);
                sendSmsIntent(intent, userHandle);
                return;
            } else if ("com.android.server.telecom.ACTION_CALL_BACK_FROM_NOTIFICATION".equals(action)) {
                closeSystemDialogs(this.mContext);
                missedCallNotifier.clearMissedCalls(userHandle);
                sendCallBackIntent(intent, userHandle);
                return;
            } else {
                if ("com.android.server.telecom.ACTION_CLEAR_MISSED_CALLS".equals(action)) {
                    missedCallNotifier.clearMissedCalls(userHandle);
                    return;
                }
                return;
            }
        }
        if ("com.android.server.telecom.ACTION_DISCONNECTED_SEND_SMS_FROM_NOTIFICATION".equals(action) || "com.android.server.telecom.ACTION_DISCONNECTED_CALL_BACK_FROM_NOTIFICATION".equals(action)) {
            Log.v(this, "Action received: %s.", new Object[]{action});
            UserHandle userHandle2 = (UserHandle) intent.getParcelableExtra("userhandle");
            if (userHandle2 == null) {
                Log.d(this, "disconnect user handle can't be null, not processing the broadcast", new Object[0]);
                return;
            }
            DisconnectedCallNotifier disconnectedCallNotifier = this.mCallsManager.getDisconnectedCallNotifier();
            if ("com.android.server.telecom.ACTION_DISCONNECTED_SEND_SMS_FROM_NOTIFICATION".equals(action)) {
                closeSystemDialogs(this.mContext);
                disconnectedCallNotifier.clearNotification(userHandle2);
                sendSmsIntent(intent, userHandle2);
                return;
            } else {
                if ("com.android.server.telecom.ACTION_DISCONNECTED_CALL_BACK_FROM_NOTIFICATION".equals(action)) {
                    closeSystemDialogs(this.mContext);
                    disconnectedCallNotifier.clearNotification(userHandle2);
                    sendCallBackIntent(intent, userHandle2);
                    return;
                }
                return;
            }
        }
        if ("com.android.server.telecom.ACTION_ANSWER_FROM_NOTIFICATION".equals(action)) {
            Log.startSession("TBIP.aAFM");
            try {
                Call incomingCall = this.mCallsManager.getIncomingCallNotifier().getIncomingCall();
                if (incomingCall != null) {
                    this.mCallsManager.answerCall(incomingCall, incomingCall.getVideoState());
                }
                return;
            } finally {
            }
        }
        if ("com.android.server.telecom.ACTION_REJECT_FROM_NOTIFICATION".equals(action)) {
            Log.startSession("TBIP.aRFM");
            try {
                Call incomingCall2 = this.mCallsManager.getIncomingCallNotifier().getIncomingCall();
                if (incomingCall2 != null) {
                    this.mCallsManager.rejectCall(incomingCall2, false, null);
                }
                return;
            } finally {
            }
        }
        if ("com.android.server.telecom.PROCEED_WITH_CALL".equals(action)) {
            Log.startSession("TBIP.aPWC");
            try {
                this.mCallsManager.confirmPendingCall(intent.getStringExtra("android.telecom.extra.OUTGOING_CALL_ID"));
                return;
            } finally {
            }
        }
        if ("com.android.server.telecom.CANCEL_CALL".equals(action)) {
            Log.startSession("TBIP.aCC");
            try {
                this.mCallsManager.cancelPendingCall(intent.getStringExtra("android.telecom.extra.OUTGOING_CALL_ID"));
                return;
            } finally {
            }
        }
        if ("com.android.server.telecom.PROCEED_WITH_REDIRECTED_CALL".equals(action)) {
            Log.startSession("TBIP.aPRC");
            try {
                this.mCallsManager.processRedirectedOutgoingCallAfterUserInteraction(intent.getStringExtra("android.telecom.extra.REDIRECTION_OUTGOING_CALL_ID"), "com.android.server.telecom.PROCEED_WITH_REDIRECTED_CALL");
            } finally {
            }
        } else if ("com.android.server.telecom.PROCEED_WITH_UNREDIRECTED_CALL".equals(action)) {
            Log.startSession("TBIP.aPUC");
            try {
                this.mCallsManager.processRedirectedOutgoingCallAfterUserInteraction(intent.getStringExtra("android.telecom.extra.REDIRECTION_OUTGOING_CALL_ID"), "com.android.server.telecom.PROCEED_WITH_UNREDIRECTED_CALL");
            } finally {
            }
        } else if ("com.android.server.telecom.CANCEL_REDIRECTED_CALL".equals(action)) {
            Log.startSession("TBIP.aCRC");
            try {
                this.mCallsManager.processRedirectedOutgoingCallAfterUserInteraction(intent.getStringExtra("android.telecom.extra.REDIRECTION_OUTGOING_CALL_ID"), "com.android.server.telecom.CANCEL_REDIRECTED_CALL");
            } finally {
            }
        }
    }

    private void closeSystemDialogs(Context context) {
        context.sendBroadcastAsUser(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"), UserHandle.ALL);
    }

    private void sendSmsIntent(Intent intent, UserHandle userHandle) {
        Intent intent2 = new Intent("android.intent.action.SENDTO", intent.getData());
        intent2.setFlags(268435456);
        if (this.mContext.getPackageManager().queryIntentActivitiesAsUser(intent2, 65536, userHandle.getIdentifier()).size() > 0) {
            this.mContext.startActivityAsUser(intent2, userHandle);
        } else {
            Toast.makeText(this.mContext, android.R.string.install_carrier_app_notification_text, 0).show();
        }
    }

    private void sendCallBackIntent(Intent intent, UserHandle userHandle) {
        Intent intent2 = new Intent("android.intent.action.CALL", intent.getData());
        intent2.setFlags(276824064);
        this.mContext.startActivityAsUser(intent2, userHandle);
    }
}
