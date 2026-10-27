package com.android.server.telecom.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Binder;
import android.os.UserHandle;
import android.telecom.DisconnectCause;
import android.telecom.Log;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import com.android.server.telecom.Call;
import com.android.server.telecom.CallsManager;
import com.android.server.telecom.CallsManagerListenerBase;
import com.android.server.telecom.R;
import com.android.server.telecom.components.TelecomBroadcastReceiver;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class DisconnectedCallNotifier extends CallsManagerListenerBase {
    private static final String NOTIFICATION_TAG = "DisconnectedCallNotifier";
    private final CallsManager mCallsManager;
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    private CallInfo mPendingCallNotification;

    public interface Factory {
        DisconnectedCallNotifier create(Context context, CallsManager callsManager);
    }

    public static class Default implements Factory {
        @Override // com.android.server.telecom.ui.DisconnectedCallNotifier.Factory
        public DisconnectedCallNotifier create(Context context, CallsManager callsManager) {
            return new DisconnectedCallNotifier(context, callsManager);
        }
    }

    private static class CallInfo {
        public final Bitmap callerInfoIcon;
        public final String callerInfoName;
        public final Drawable callerInfoPhoto;
        public final long endTimeMs;
        public final Uri handle;
        public final boolean isEmergency;
        public final UserHandle userHandle;

        public CallInfo(UserHandle userHandle, Uri uri, long j, Bitmap bitmap, Drawable drawable, String str, boolean z) {
            this.userHandle = userHandle;
            this.handle = uri;
            this.endTimeMs = j;
            this.callerInfoIcon = bitmap;
            this.callerInfoPhoto = drawable;
            this.callerInfoName = str;
            this.isEmergency = z;
        }

        public String toString() {
            return "CallInfo{userHandle=" + this.userHandle + ", handle=" + this.handle + ", isEmergency=" + this.isEmergency + ", endTimeMs=" + this.endTimeMs + ", callerInfoIcon=" + this.callerInfoIcon + ", callerInfoPhoto=" + this.callerInfoPhoto + ", callerInfoName='" + this.callerInfoName + "'}";
        }
    }

    public DisconnectedCallNotifier(Context context, CallsManager callsManager) {
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
        this.mCallsManager = callsManager;
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallRemoved(Call call) {
        CallInfo callInfo;
        if (!this.mCallsManager.getCalls().isEmpty() || (callInfo = this.mPendingCallNotification) == null) {
            return;
        }
        showDisconnectedNotification(callInfo);
        this.mPendingCallNotification = null;
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallStateChanged(Call call, int i, int i2) {
        DisconnectCause disconnectCause = call.getDisconnectCause();
        if (disconnectCause == null) {
            Log.w(this, "onCallStateChanged: unexpected null disconnect cause.", new Object[0]);
            return;
        }
        if (i2 == 7 && disconnectCause.getCode() == 2 && "REASON_EMERGENCY_CALL_PLACED".equals(disconnectCause.getReason())) {
            clearNotification(this.mCallsManager.getCurrentUserHandle());
            UserHandle userHandle = call.getTargetPhoneAccount() != null ? call.getTargetPhoneAccount().getUserHandle() : call.getInitiatingUser();
            if (userHandle == null) {
                userHandle = this.mCallsManager.getCurrentUserHandle();
            }
            this.mPendingCallNotification = new CallInfo(userHandle, call.getHandle(), call.getCreationTimeMillis() + call.getAgeMillis(), call.getPhotoIcon(), call.getPhoto(), call.getName(), call.isEmergencyCall());
        }
    }

    private void showDisconnectedNotification(CallInfo callInfo) {
        CharSequence string;
        Log.i(this, "showDisconnectedNotification: userHandle=%d", new Object[]{Integer.valueOf(callInfo.userHandle.getIdentifier())});
        if (callInfo.isEmergency) {
            string = this.mContext.getText(R.string.notification_disconnectedCall_generic_body);
        } else {
            string = this.mContext.getString(R.string.notification_disconnectedCall_body, getNameForCallNotification(callInfo));
        }
        Context contextForUser = getContextForUser(callInfo.userHandle);
        Notification.Builder builder = new Notification.Builder(contextForUser, "TelecomDisconnectedCalls");
        builder.setSmallIcon(android.R.drawable.stat_notify_error).setColor(this.mContext.getResources().getColor(R.color.theme_color, null)).setWhen(callInfo.endTimeMs).setShowWhen(true).setContentTitle(this.mContext.getText(R.string.userCallActivityLabel)).setContentText(this.mContext.getText(R.string.notification_disconnectedCall_title)).setAutoCancel(true);
        if (!callInfo.isEmergency) {
            builder.setContentIntent(createCallLogPendingIntent(callInfo.userHandle));
        }
        Notification.Builder builder2 = new Notification.Builder(contextForUser, "TelecomDisconnectedCalls");
        builder2.setSmallIcon(android.R.drawable.stat_notify_error).setColor(this.mContext.getResources().getColor(R.color.theme_color, null)).setWhen(callInfo.endTimeMs).setShowWhen(true).setContentTitle(this.mContext.getText(R.string.notification_disconnectedCall_title)).setStyle(new Notification.BigTextStyle().bigText(string)).setAutoCancel(true).setPublicVersion(builder.build()).setChannelId("TelecomDisconnectedCalls");
        if (!callInfo.isEmergency) {
            builder2.setContentIntent(createCallLogPendingIntent(callInfo.userHandle));
        }
        Uri uri = callInfo.handle;
        String schemeSpecificPart = uri != null ? uri.getSchemeSpecificPart() : null;
        if (!TextUtils.isEmpty(schemeSpecificPart) && !TextUtils.equals(schemeSpecificPart, this.mContext.getString(R.string.handle_restricted)) && !callInfo.isEmergency) {
            builder2.addAction(new Notification.Action.Builder(Icon.createWithResource(contextForUser, R.drawable.ic_phone_24dp), this.mContext.getString(R.string.notification_missedCall_call_back), createCallBackPendingIntent(callInfo.handle, callInfo.userHandle)).build());
            if (canRespondViaSms(callInfo)) {
                builder2.addAction(new Notification.Action.Builder(Icon.createWithResource(contextForUser, R.drawable.ic_message_24dp), this.mContext.getString(R.string.notification_missedCall_message), createSendSmsFromNotificationPendingIntent(callInfo.handle, callInfo.userHandle)).build());
            }
        }
        Bitmap bitmap = callInfo.callerInfoIcon;
        if (bitmap != null) {
            builder2.setLargeIcon(bitmap);
        } else {
            Drawable drawable = callInfo.callerInfoPhoto;
            if (drawable instanceof BitmapDrawable) {
                builder2.setLargeIcon(((BitmapDrawable) drawable).getBitmap());
            }
        }
        builder2.build();
        Log.i(this, "Adding missed call notification for %s.", new Object[]{Log.pii(callInfo.handle)});
        Binder.clearCallingIdentity();
    }

    private String getNameForCallNotification(CallInfo callInfo) {
        Uri uri = callInfo.handle;
        String schemeSpecificPart = uri != null ? uri.getSchemeSpecificPart() : null;
        if (!TextUtils.isEmpty(schemeSpecificPart)) {
            String number = PhoneNumberUtils.formatNumber(schemeSpecificPart, getCurrentCountryIso(this.mContext));
            if (!TextUtils.isEmpty(number)) {
                schemeSpecificPart = number;
            }
        }
        if (!TextUtils.isEmpty(callInfo.callerInfoName) && TextUtils.isGraphic(callInfo.callerInfoName)) {
            return callInfo.callerInfoName;
        }
        if (!TextUtils.isEmpty(schemeSpecificPart)) {
            return BidiFormatter.getInstance().unicodeWrap(schemeSpecificPart, TextDirectionHeuristics.LTR);
        }
        return this.mContext.getString(R.string.unknown);
    }

    private String getCurrentCountryIso(Context context) {
        String upperCase = ((TelephonyManager) context.getSystemService("phone")).getNetworkCountryIso().toUpperCase();
        if (upperCase != null) {
            return upperCase;
        }
        String country = Locale.getDefault().getCountry();
        Log.w(this, "No CountryDetector; falling back to countryIso based on locale: " + country, new Object[0]);
        return country;
    }

    private Context getContextForUser(UserHandle userHandle) {
        try {
            return this.mContext.createPackageContextAsUser(this.mContext.getPackageName(), 0, userHandle);
        } catch (PackageManager.NameNotFoundException unused) {
            return this.mContext;
        }
    }

    private PendingIntent createCallBackPendingIntent(Uri uri, UserHandle userHandle) {
        return createTelecomPendingIntent("com.android.server.telecom.ACTION_DISCONNECTED_CALL_BACK_FROM_NOTIFICATION", uri, userHandle);
    }

    private PendingIntent createTelecomPendingIntent(String str, Uri uri, UserHandle userHandle) {
        Intent intent = new Intent(str, uri, this.mContext, TelecomBroadcastReceiver.class);
        intent.putExtra("userhandle", userHandle);
        return PendingIntent.getBroadcast(this.mContext, 0, intent, 134217728);
    }

    private boolean canRespondViaSms(CallInfo callInfo) {
        Uri uri = callInfo.handle;
        return uri != null && "tel".equals(uri.getScheme());
    }

    private PendingIntent createCallLogPendingIntent(UserHandle userHandle) {
        Intent intent = new Intent("android.intent.action.VIEW", (Uri) null);
        intent.setType("vnd.android.cursor.dir/calls");
        TaskStackBuilder taskStackBuilderCreate = TaskStackBuilder.create(this.mContext);
        taskStackBuilderCreate.addNextIntent(intent);
        return taskStackBuilderCreate.getPendingIntent(0, 0, null, userHandle);
    }

    private PendingIntent createSendSmsFromNotificationPendingIntent(Uri uri, UserHandle userHandle) {
        return createTelecomPendingIntent("com.android.server.telecom.ACTION_DISCONNECTED_SEND_SMS_FROM_NOTIFICATION", Uri.fromParts("smsto", uri.getSchemeSpecificPart(), null), userHandle);
    }

    public void clearNotification(UserHandle userHandle) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            this.mNotificationManager.cancelAsUser(NOTIFICATION_TAG, 1, userHandle);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }
}
