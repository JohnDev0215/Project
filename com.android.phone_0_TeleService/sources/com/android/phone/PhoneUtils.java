package com.android.phone;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telephony.SubscriptionManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.android.internal.telephony.Call;
import com.android.internal.telephony.CallStateException;
import com.android.internal.telephony.Connection;
import com.android.internal.telephony.IccCard;
import com.android.internal.telephony.MmiCode;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.phone.CdmaPhoneCallState;
import com.android.telephony.Rlog;
import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PhoneUtils {
    private static final long[] DEFAULT_VIBRATE_PATTERN = {0, 250, 250, 250};
    private static StringBuilder sUssdMsg = new StringBuilder();
    private static final ComponentName PSTN_CONNECTION_SERVICE_COMPONENT = new ComponentName("com.android.phone", "com.android.services.telephony.TelephonyConnectionService");

    private static void updateCdmaCallStateOnNewOutgoingCall(PhoneGlobals phoneGlobals, Connection connection) {
        if (phoneGlobals.cdmaPhoneCallState.getCurrentCallState() == CdmaPhoneCallState.PhoneCallState.IDLE) {
            phoneGlobals.cdmaPhoneCallState.setCurrentCallState(CdmaPhoneCallState.PhoneCallState.SINGLE_ACTIVE);
        } else {
            phoneGlobals.cdmaPhoneCallState.setCurrentCallState(CdmaPhoneCallState.PhoneCallState.THRWAY_ACTIVE);
        }
    }

    public static int placeOtaspCall(Context context, Phone phone, String str) {
        log("placeCall()... number: " + toLogSafePhoneNumber(str) + ", GW: null");
        PhoneGlobals phoneGlobals = PhoneGlobals.getInstance();
        try {
            Connection connectionDial = phoneGlobals.mCM.dial(phone, str, 0);
            int phoneType = phone.getPhoneType();
            if (connectionDial == null) {
                return 2;
            }
            if (phoneType == 2) {
                updateCdmaCallStateOnNewOutgoingCall(phoneGlobals, connectionDial);
            }
            return 0;
        } catch (CallStateException e) {
            Log.w("PhoneUtils", "Exception from app.mCM.dial()", e);
            return 2;
        }
    }

    static String toLogSafePhoneNumber(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '-' || cCharAt == '@' || cCharAt == '.') {
                sb.append(cCharAt);
            } else {
                sb.append('x');
            }
        }
        return sb.toString();
    }

    static Dialog displayMMIInitiate(Context context, MmiCode mmiCode, Message message, Dialog dialog) {
        log("displayMMIInitiate: " + Rlog.pii("PhoneUtils", mmiCode.toString()));
        if (dialog != null) {
            dialog.dismiss();
        }
        if (!(mmiCode != null && mmiCode.isCancelable())) {
            log("displayMMIInitiate: not a USSD code, displaying status toast.");
            Toast.makeText(context, context.getText(R.string.mmiStarted), 0).show();
            return null;
        }
        log("displayMMIInitiate: running USSD code, displaying intermediate progress.");
        ProgressDialog progressDialog = new ProgressDialog(context, android.R.style.Theme.DeviceDefault.Dialog.Alert);
        progressDialog.setMessage(context.getText(R.string.ussdRunning));
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.getWindow().addFlags(2);
        progressDialog.show();
        return progressDialog;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static void displayMMIComplete(final com.android.internal.telephony.Phone r9, android.content.Context r10, final com.android.internal.telephony.MmiCode r11, android.os.Message r12, android.app.AlertDialog r13) {
        /*
            Method dump skipped, instruction units count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.PhoneUtils.displayMMIComplete(com.android.internal.telephony.Phone, android.content.Context, com.android.internal.telephony.MmiCode, android.os.Message, android.app.AlertDialog):void");
    }

    /* JADX INFO: renamed from: com.android.phone.PhoneUtils$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$telephony$MmiCode$State;

        static {
            int[] iArr = new int[MmiCode.State.values().length];
            $SwitchMap$com$android$internal$telephony$MmiCode$State = iArr;
            try {
                iArr[MmiCode.State.PENDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$MmiCode$State[MmiCode.State.CANCELLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$MmiCode$State[MmiCode.State.COMPLETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$MmiCode$State[MmiCode.State.FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static void playSound(Context context) {
        int ringerMode = ((AudioManager) context.getSystemService("audio")).getRingerMode();
        if (ringerMode != 2) {
            if (ringerMode == 1) {
                log("playSound : RINGER_MODE_VIBRATE");
                ((Vibrator) context.getSystemService("vibrator")).vibrate(VibrationEffect.createWaveform(getLongArray(context.getResources(), R.array.config_defaultNotificationVibePattern, DEFAULT_VIBRATE_PATTERN), -1));
                return;
            }
            return;
        }
        log("playSound : RINGER_MODE_NORMAL");
        try {
            Uri defaultUri = RingtoneManager.getDefaultUri(2);
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(context, defaultUri);
            mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(5).setUsage(5).build());
            mediaPlayer.setLooping(false);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            log("playSound exception : " + e);
        }
    }

    private static long[] getLongArray(Resources resources, int i, long[] jArr) {
        int[] intArray = resources.getIntArray(i);
        if (intArray == null) {
            return jArr;
        }
        int length = intArray.length;
        long[] jArr2 = new long[length];
        for (int i2 = 0; i2 < length; i2++) {
            jArr2[i2] = intArray[i2];
        }
        return jArr2;
    }

    public static void createUssdDialog(PhoneGlobals phoneGlobals, Context context, CharSequence charSequence, Phone phone, int i) {
        StringBuilder sb = sUssdMsg;
        log("displayMMIComplete: MMI code has finished running.");
        log("displayMMIComplete: Extended NW displayMMIInitiate (" + ((Object) charSequence) + ")");
        if (charSequence == null || charSequence.length() == 0) {
            return;
        }
        AlertDialog alertDialogCreate = new AlertDialog.Builder(context, android.R.style.Theme.DeviceDefault.Dialog.Alert).setPositiveButton(R.string.ok, (DialogInterface.OnClickListener) null).setCancelable(true).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.phone.PhoneUtils.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                PhoneUtils.sUssdMsg.setLength(0);
            }
        }).create();
        alertDialogCreate.getWindow().setType(i);
        alertDialogCreate.getWindow().addFlags(2);
        if (sb.length() != 0) {
            sb.insert(0, "\n").insert(0, phoneGlobals.getResources().getString(R.string.ussd_dialog_sep)).insert(0, "\n");
        }
        if (phone != null && phone.getCarrierName() != null) {
            alertDialogCreate.setTitle(phoneGlobals.getResources().getString(R.string.carrier_mmi_msg_title, phone.getCarrierName()));
        } else {
            alertDialogCreate.setTitle(phoneGlobals.getResources().getString(R.string.default_carrier_mmi_msg_title));
        }
        sb.insert(0, charSequence);
        alertDialogCreate.setMessage(sb.toString());
        alertDialogCreate.show();
    }

    static boolean cancelMmiCode(Phone phone) {
        List pendingMmiCodes = phone.getPendingMmiCodes();
        if (pendingMmiCodes.size() <= 0) {
            return false;
        }
        MmiCode mmiCode = (MmiCode) pendingMmiCodes.get(0);
        if (!mmiCode.isCancelable()) {
            return false;
        }
        mmiCode.cancel();
        return true;
    }

    public static boolean isRealIncomingCall(Call.State state) {
        return state == Call.State.INCOMING && !PhoneGlobals.getInstance().mCM.hasActiveFgCall();
    }

    private static void log(String str) {
        Log.d("PhoneUtils", str);
    }

    public static PhoneAccountHandle makePstnPhoneAccountHandle(Phone phone) {
        return makePstnPhoneAccountHandleWithPrefix(phone, "", false);
    }

    public static PhoneAccountHandle makePstnPhoneAccountHandleWithPrefix(Phone phone, String str, boolean z) {
        String str2;
        if (z) {
            str2 = "E";
        } else {
            str2 = str + String.valueOf(phone.getFullIccSerialNumber());
        }
        return makePstnPhoneAccountHandleWithPrefix(str2, str, z);
    }

    public static PhoneAccountHandle makePstnPhoneAccountHandleWithPrefix(String str, String str2, boolean z) {
        return new PhoneAccountHandle(getPstnConnectionServiceName(), str);
    }

    public static int getSubIdForPhoneAccount(PhoneAccount phoneAccount) {
        if (phoneAccount == null || !phoneAccount.hasCapabilities(4)) {
            return -1;
        }
        return getSubIdForPhoneAccountHandle(phoneAccount.getAccountHandle());
    }

    public static int getSubIdForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle) {
        Phone phoneForPhoneAccountHandle = getPhoneForPhoneAccountHandle(phoneAccountHandle);
        if (phoneForPhoneAccountHandle != null) {
            return phoneForPhoneAccountHandle.getSubId();
        }
        return -1;
    }

    public static Phone getPhoneForPhoneAccountHandle(PhoneAccountHandle phoneAccountHandle) {
        if (phoneAccountHandle == null || !phoneAccountHandle.getComponentName().equals(getPstnConnectionServiceName())) {
            return null;
        }
        return getPhoneFromIccId(phoneAccountHandle.getId());
    }

    public static boolean isPhoneAccountActive(SubscriptionManager subscriptionManager, PhoneAccountHandle phoneAccountHandle) {
        return subscriptionManager.getActiveSubscriptionInfoForIcc(phoneAccountHandle.getId()) != null;
    }

    private static ComponentName getPstnConnectionServiceName() {
        return PSTN_CONNECTION_SERVICE_COMPONENT;
    }

    private static Phone getPhoneFromIccId(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (Phone phone : PhoneFactory.getPhones()) {
            if (str.equals(phone.getFullIccSerialNumber())) {
                return phone;
            }
        }
        return null;
    }

    static final void registerIccStatus(Handler handler, int i) {
        for (Phone phone : PhoneFactory.getPhones()) {
            IccCard iccCard = phone.getIccCard();
            if (iccCard != null) {
                iccCard.registerForNetworkLocked(handler, i, phone);
            }
        }
    }

    static final void registerIccStatus(Handler handler, int i, int i2) {
        Phone[] phones = PhoneFactory.getPhones();
        IccCard iccCard = phones[i2].getIccCard();
        if (iccCard != null) {
            iccCard.registerForNetworkLocked(handler, i, phones[i2]);
        }
    }

    static final void unregisterIccStatus(Handler handler, int i) {
        IccCard iccCard = PhoneFactory.getPhones()[i].getIccCard();
        if (iccCard != null) {
            iccCard.unregisterForNetworkLocked(handler);
        }
    }

    static final void setRadioPower(boolean z) {
        for (Phone phone : PhoneFactory.getPhones()) {
            phone.setRadioPower(z);
        }
    }
}
