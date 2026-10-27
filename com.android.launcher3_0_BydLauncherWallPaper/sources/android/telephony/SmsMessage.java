package android.telephony;

import android.annotation.SystemApi;
import android.content.res.Resources;
import android.os.Binder;
import android.text.TextUtils;
import com.android.internal.R;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.Sms7BitEncodingTranslator;
import com.android.internal.telephony.SmsConstants;
import com.android.internal.telephony.SmsHeader;
import com.android.internal.telephony.SmsMessageBase;
import com.android.internal.telephony.cdma.sms.UserData;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class SmsMessage {
    public static final int ENCODING_16BIT = 3;
    public static final int ENCODING_7BIT = 1;
    public static final int ENCODING_8BIT = 2;
    public static final int ENCODING_KSC5601 = 4;
    public static final int ENCODING_UNKNOWN = 0;
    public static final String FORMAT_3GPP = "3gpp";
    public static final String FORMAT_3GPP2 = "3gpp2";
    private static final String LOG_TAG = "SmsMessage";
    public static final int MAX_USER_DATA_BYTES = 140;
    public static final int MAX_USER_DATA_BYTES_WITH_HEADER = 134;
    public static final int MAX_USER_DATA_SEPTETS = 160;
    public static final int MAX_USER_DATA_SEPTETS_WITH_HEADER = 153;
    private static boolean mIsNoEmsSupportConfigListLoaded = false;
    private static NoEmsSupportConfig[] mNoEmsSupportConfigList;
    private int mSubId = 0;
    public SmsMessageBase mWrappedSmsMessage;

    @Retention(RetentionPolicy.SOURCE)
    public @interface EncodingSize {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Format {
    }

    public enum MessageClass {
        UNKNOWN,
        CLASS_0,
        CLASS_1,
        CLASS_2,
        CLASS_3
    }

    public void setSubId(int i) {
        this.mSubId = i;
    }

    public int getSubId() {
        return this.mSubId;
    }

    public static class SubmitPdu {
        public byte[] encodedMessage;
        public byte[] encodedScAddress;

        public String toString() {
            return "SubmitPdu: encodedScAddress = " + Arrays.toString(this.encodedScAddress) + ", encodedMessage = " + Arrays.toString(this.encodedMessage);
        }

        protected SubmitPdu(SmsMessageBase.SubmitPduBase submitPduBase) {
            this.encodedMessage = submitPduBase.encodedMessage;
            this.encodedScAddress = submitPduBase.encodedScAddress;
        }
    }

    public SmsMessage(SmsMessageBase smsMessageBase) {
        this.mWrappedSmsMessage = smsMessageBase;
    }

    @Deprecated
    public static SmsMessage createFromPdu(byte[] bArr) {
        return createFromPdu(bArr, 2 == TelephonyManager.getDefault().getCurrentPhoneType() ? "3gpp2" : "3gpp");
    }

    public static SmsMessage createFromPdu(byte[] bArr, String str) {
        return createFromPdu(bArr, str, true);
    }

    private static SmsMessage createFromPdu(byte[] bArr, String str, boolean z) {
        SmsMessageBase smsMessageBaseCreateFromPdu;
        if (bArr == null) {
            com.android.telephony.Rlog.i(LOG_TAG, "createFromPdu(): pdu is null");
            return null;
        }
        String str2 = "3gpp2".equals(str) ? "3gpp" : "3gpp2";
        if ("3gpp2".equals(str)) {
            smsMessageBaseCreateFromPdu = com.android.internal.telephony.cdma.SmsMessage.createFromPdu(bArr);
        } else if ("3gpp".equals(str)) {
            smsMessageBaseCreateFromPdu = com.android.internal.telephony.gsm.SmsMessage.createFromPdu(bArr);
        } else {
            com.android.telephony.Rlog.e(LOG_TAG, "createFromPdu(): unsupported message format " + str);
            return null;
        }
        if (smsMessageBaseCreateFromPdu != null) {
            return new SmsMessage(smsMessageBaseCreateFromPdu);
        }
        if (z) {
            return createFromPdu(bArr, str2, false);
        }
        com.android.telephony.Rlog.e(LOG_TAG, "createFromPdu(): wrappedMessage is null");
        return null;
    }

    public static SmsMessage newFromCMT(byte[] bArr) {
        com.android.internal.telephony.gsm.SmsMessage smsMessageNewFromCMT = com.android.internal.telephony.gsm.SmsMessage.newFromCMT(bArr);
        if (smsMessageNewFromCMT != null) {
            return new SmsMessage(smsMessageNewFromCMT);
        }
        com.android.telephony.Rlog.e(LOG_TAG, "newFromCMT(): wrappedMessage is null");
        return null;
    }

    public static SmsMessage createFromEfRecord(int i, byte[] bArr) {
        return createFromEfRecord(i, bArr, SmsManager.getDefaultSmsSubscriptionId());
    }

    public static SmsMessage createFromEfRecord(int i, byte[] bArr, int i2) {
        SmsMessageBase smsMessageBaseCreateFromEfRecord;
        if (isCdmaVoice(i2)) {
            smsMessageBaseCreateFromEfRecord = com.android.internal.telephony.cdma.SmsMessage.createFromEfRecord(i, bArr);
        } else {
            smsMessageBaseCreateFromEfRecord = com.android.internal.telephony.gsm.SmsMessage.createFromEfRecord(i, bArr);
        }
        if (smsMessageBaseCreateFromEfRecord != null) {
            return new SmsMessage(smsMessageBaseCreateFromEfRecord);
        }
        return null;
    }

    @SystemApi
    public static SmsMessage createFromNativeSmsSubmitPdu(byte[] bArr, boolean z) {
        SmsMessageBase smsMessageBaseCreateFromEfRecord;
        if (z) {
            smsMessageBaseCreateFromEfRecord = com.android.internal.telephony.cdma.SmsMessage.createFromEfRecord(0, bArr);
        } else {
            smsMessageBaseCreateFromEfRecord = com.android.internal.telephony.gsm.SmsMessage.createFromEfRecord(0, bArr);
        }
        if (smsMessageBaseCreateFromEfRecord != null) {
            return new SmsMessage(smsMessageBaseCreateFromEfRecord);
        }
        return null;
    }

    public static int getTPLayerLengthForPDU(String str) {
        if (isCdmaVoice()) {
            return com.android.internal.telephony.cdma.SmsMessage.getTPLayerLengthForPDU(str);
        }
        return com.android.internal.telephony.gsm.SmsMessage.getTPLayerLengthForPDU(str);
    }

    public static int[] calculateLength(CharSequence charSequence, boolean z) {
        return calculateLength(charSequence, z, SmsManager.getDefaultSmsSubscriptionId());
    }

    public static int[] calculateLength(CharSequence charSequence, boolean z, int i) {
        GsmAlphabet.TextEncodingDetails textEncodingDetailsCalculateLength;
        if (useCdmaFormatForMoSms(i)) {
            textEncodingDetailsCalculateLength = com.android.internal.telephony.cdma.SmsMessage.calculateLength(charSequence, z, true);
        } else {
            textEncodingDetailsCalculateLength = com.android.internal.telephony.gsm.SmsMessage.calculateLength(charSequence, z);
        }
        return new int[]{textEncodingDetailsCalculateLength.msgCount, textEncodingDetailsCalculateLength.codeUnitCount, textEncodingDetailsCalculateLength.codeUnitsRemaining, textEncodingDetailsCalculateLength.codeUnitSize, textEncodingDetailsCalculateLength.languageTable, textEncodingDetailsCalculateLength.languageShiftTable};
    }

    public static ArrayList<String> fragmentText(String str) {
        return fragmentText(str, SmsManager.getDefaultSmsSubscriptionId());
    }

    public static ArrayList<String> fragmentText(String str, int i) {
        GsmAlphabet.TextEncodingDetails textEncodingDetailsCalculateLength;
        int i2;
        int iFindNextUnicodePosition;
        int i3;
        boolean zUseCdmaFormatForMoSms = useCdmaFormatForMoSms(i);
        int i4 = 0;
        if (zUseCdmaFormatForMoSms) {
            textEncodingDetailsCalculateLength = com.android.internal.telephony.cdma.SmsMessage.calculateLength(str, false, true);
        } else {
            textEncodingDetailsCalculateLength = com.android.internal.telephony.gsm.SmsMessage.calculateLength(str, false);
        }
        if (textEncodingDetailsCalculateLength.codeUnitSize == 1) {
            if (textEncodingDetailsCalculateLength.languageTable == 0 || textEncodingDetailsCalculateLength.languageShiftTable == 0) {
                i3 = (textEncodingDetailsCalculateLength.languageTable == 0 && textEncodingDetailsCalculateLength.languageShiftTable == 0) ? 0 : 4;
            } else {
                i3 = 7;
            }
            if (textEncodingDetailsCalculateLength.msgCount > 1) {
                i3 += 6;
            }
            if (i3 != 0) {
                i3++;
            }
            i2 = 160 - i3;
        } else if (textEncodingDetailsCalculateLength.msgCount > 1) {
            i2 = 134;
            if (!hasEmsSupport() && textEncodingDetailsCalculateLength.msgCount < 10) {
                i2 = 132;
            }
        } else {
            i2 = 140;
        }
        String strTranslate = Resources.getSystem().getBoolean(R.bool.config_sms_force_7bit_encoding) ? Sms7BitEncodingTranslator.translate(str, zUseCdmaFormatForMoSms) : null;
        if (!TextUtils.isEmpty(strTranslate)) {
            str = strTranslate;
        }
        int length = str.length();
        ArrayList<String> arrayList = new ArrayList<>(textEncodingDetailsCalculateLength.msgCount);
        while (i4 < length) {
            if (textEncodingDetailsCalculateLength.codeUnitSize == 1) {
                if (zUseCdmaFormatForMoSms && textEncodingDetailsCalculateLength.msgCount == 1) {
                    iFindNextUnicodePosition = Math.min(i2, length - i4) + i4;
                } else {
                    iFindNextUnicodePosition = GsmAlphabet.findGsmSeptetLimitIndex(str, i4, i2, textEncodingDetailsCalculateLength.languageTable, textEncodingDetailsCalculateLength.languageShiftTable);
                }
            } else {
                iFindNextUnicodePosition = SmsMessageBase.findNextUnicodePosition(i4, i2, str);
            }
            if (iFindNextUnicodePosition <= i4 || iFindNextUnicodePosition > length) {
                com.android.telephony.Rlog.e(LOG_TAG, "fragmentText failed (" + i4 + " >= " + iFindNextUnicodePosition + " or " + iFindNextUnicodePosition + " >= " + length + ")");
                break;
            }
            arrayList.add(str.substring(i4, iFindNextUnicodePosition));
            i4 = iFindNextUnicodePosition;
        }
        return arrayList;
    }

    public static int[] calculateLength(String str, boolean z) {
        return calculateLength((CharSequence) str, z);
    }

    public static int[] calculateLength(String str, boolean z, int i) {
        return calculateLength((CharSequence) str, z, i);
    }

    public static SubmitPdu getSubmitPdu(String str, String str2, String str3, boolean z) {
        return getSubmitPdu(str, str2, str3, z, SmsManager.getDefaultSmsSubscriptionId());
    }

    public static SubmitPdu getSubmitPdu(String str, String str2, String str3, boolean z, int i) {
        SmsMessageBase.SubmitPduBase submitPdu;
        if (useCdmaFormatForMoSms(i)) {
            submitPdu = com.android.internal.telephony.cdma.SmsMessage.getSubmitPdu(str, str2, str3, z, (SmsHeader) null);
        } else {
            submitPdu = com.android.internal.telephony.gsm.SmsMessage.getSubmitPdu(str, str2, str3, z);
        }
        if (submitPdu != null) {
            return new SubmitPdu(submitPdu);
        }
        return null;
    }

    public static SubmitPdu getSubmitPdu(String str, String str2, short s, byte[] bArr, boolean z) {
        SmsMessageBase.SubmitPduBase submitPdu;
        if (useCdmaFormatForMoSms()) {
            submitPdu = com.android.internal.telephony.cdma.SmsMessage.getSubmitPdu(str, str2, s, bArr, z);
        } else {
            submitPdu = com.android.internal.telephony.gsm.SmsMessage.getSubmitPdu(str, str2, s, bArr, z);
        }
        if (submitPdu != null) {
            return new SubmitPdu(submitPdu);
        }
        return null;
    }

    @SystemApi
    public static SubmitPdu getSmsPdu(int i, int i2, String str, String str2, String str3, long j) {
        SmsMessageBase.SubmitPduBase deliverPdu;
        if (isCdmaVoice(i)) {
            if (i2 == 1 || i2 == 3) {
                deliverPdu = com.android.internal.telephony.cdma.SmsMessage.getDeliverPdu(str2, str3, j);
            } else {
                deliverPdu = com.android.internal.telephony.cdma.SmsMessage.getSubmitPdu(str, str2, str3, false, (SmsHeader) null);
            }
        } else if (i2 == 1 || i2 == 3) {
            deliverPdu = com.android.internal.telephony.gsm.SmsMessage.getDeliverPdu(str, str2, str3, j);
        } else {
            deliverPdu = com.android.internal.telephony.gsm.SmsMessage.getSubmitPdu(str, str2, str3, false, (byte[]) null);
        }
        if (deliverPdu != null) {
            return new SubmitPdu(deliverPdu);
        }
        return null;
    }

    @SystemApi
    public static byte[] getSubmitPduEncodedMessage(boolean z, String str, String str2, int i, int i2, int i3, int i4, int i5, int i6) {
        byte[] bArr;
        SmsHeader.ConcatRef concatRef = new SmsHeader.ConcatRef();
        concatRef.refNumber = i4;
        concatRef.seqNumber = i5;
        concatRef.msgCount = i6;
        concatRef.isEightBits = true;
        SmsHeader smsHeader = new SmsHeader();
        smsHeader.concatRef = concatRef;
        if (i == 1) {
            smsHeader.languageTable = i2;
            smsHeader.languageShiftTable = i3;
        }
        if (z) {
            bArr = com.android.internal.telephony.gsm.SmsMessage.getSubmitPdu(null, str, str2, false, SmsHeader.toByteArray(smsHeader), i, i2, i3).encodedMessage;
        } else {
            UserData userData = new UserData();
            userData.payloadStr = str2;
            userData.userDataHeader = smsHeader;
            if (i == 1) {
                userData.msgEncoding = 9;
            } else {
                userData.msgEncoding = 4;
            }
            userData.msgEncodingSet = true;
            bArr = com.android.internal.telephony.cdma.SmsMessage.getSubmitPdu(str, userData, false).encodedMessage;
        }
        return bArr == null ? new byte[0] : bArr;
    }

    public String getServiceCenterAddress() {
        return this.mWrappedSmsMessage.getServiceCenterAddress();
    }

    public String getOriginatingAddress() {
        return this.mWrappedSmsMessage.getOriginatingAddress();
    }

    public String getDisplayOriginatingAddress() {
        return this.mWrappedSmsMessage.getDisplayOriginatingAddress();
    }

    public String getMessageBody() {
        return this.mWrappedSmsMessage.getMessageBody();
    }

    /* JADX INFO: renamed from: android.telephony.SmsMessage$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$internal$telephony$SmsConstants$MessageClass;

        static {
            int[] iArr = new int[SmsConstants.MessageClass.values().length];
            $SwitchMap$com$android$internal$telephony$SmsConstants$MessageClass = iArr;
            try {
                iArr[SmsConstants.MessageClass.CLASS_0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$SmsConstants$MessageClass[SmsConstants.MessageClass.CLASS_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$SmsConstants$MessageClass[SmsConstants.MessageClass.CLASS_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$internal$telephony$SmsConstants$MessageClass[SmsConstants.MessageClass.CLASS_3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public MessageClass getMessageClass() {
        int i = AnonymousClass1.$SwitchMap$com$android$internal$telephony$SmsConstants$MessageClass[this.mWrappedSmsMessage.getMessageClass().ordinal()];
        if (i == 1) {
            return MessageClass.CLASS_0;
        }
        if (i == 2) {
            return MessageClass.CLASS_1;
        }
        if (i == 3) {
            return MessageClass.CLASS_2;
        }
        if (i == 4) {
            return MessageClass.CLASS_3;
        }
        return MessageClass.UNKNOWN;
    }

    public String getDisplayMessageBody() {
        return this.mWrappedSmsMessage.getDisplayMessageBody();
    }

    public String getPseudoSubject() {
        return this.mWrappedSmsMessage.getPseudoSubject();
    }

    public long getTimestampMillis() {
        return this.mWrappedSmsMessage.getTimestampMillis();
    }

    public boolean isEmail() {
        return this.mWrappedSmsMessage.isEmail();
    }

    public String getEmailBody() {
        return this.mWrappedSmsMessage.getEmailBody();
    }

    public String getEmailFrom() {
        return this.mWrappedSmsMessage.getEmailFrom();
    }

    public int getProtocolIdentifier() {
        return this.mWrappedSmsMessage.getProtocolIdentifier();
    }

    public boolean isReplace() {
        return this.mWrappedSmsMessage.isReplace();
    }

    public boolean isCphsMwiMessage() {
        return this.mWrappedSmsMessage.isCphsMwiMessage();
    }

    public boolean isMWIClearMessage() {
        return this.mWrappedSmsMessage.isMWIClearMessage();
    }

    public boolean isMWISetMessage() {
        return this.mWrappedSmsMessage.isMWISetMessage();
    }

    public boolean isMwiDontStore() {
        return this.mWrappedSmsMessage.isMwiDontStore();
    }

    public byte[] getUserData() {
        return this.mWrappedSmsMessage.getUserData();
    }

    public byte[] getPdu() {
        return this.mWrappedSmsMessage.getPdu();
    }

    @Deprecated
    public int getStatusOnSim() {
        return this.mWrappedSmsMessage.getStatusOnIcc();
    }

    public int getStatusOnIcc() {
        return this.mWrappedSmsMessage.getStatusOnIcc();
    }

    @Deprecated
    public int getIndexOnSim() {
        return this.mWrappedSmsMessage.getIndexOnIcc();
    }

    public int getIndexOnIcc() {
        return this.mWrappedSmsMessage.getIndexOnIcc();
    }

    public int getStatus() {
        return this.mWrappedSmsMessage.getStatus();
    }

    public boolean isStatusReportMessage() {
        return this.mWrappedSmsMessage.isStatusReportMessage();
    }

    public boolean isReplyPathPresent() {
        return this.mWrappedSmsMessage.isReplyPathPresent();
    }

    private static boolean useCdmaFormatForMoSms() {
        return useCdmaFormatForMoSms(SmsManager.getDefaultSmsSubscriptionId());
    }

    private static boolean useCdmaFormatForMoSms(int i) {
        SmsManager smsManagerForSubscriptionId = SmsManager.getSmsManagerForSubscriptionId(i);
        if (!smsManagerForSubscriptionId.isImsSmsSupported()) {
            return isCdmaVoice(i);
        }
        return "3gpp2".equals(smsManagerForSubscriptionId.getImsSmsFormat());
    }

    private static boolean isCdmaVoice() {
        return isCdmaVoice(SmsManager.getDefaultSmsSubscriptionId());
    }

    private static boolean isCdmaVoice(int i) {
        return 2 == TelephonyManager.getDefault().getCurrentPhoneType(i);
    }

    public static boolean hasEmsSupport() {
        if (!isNoEmsSupportConfigListExisted()) {
            return true;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            String simOperatorNumeric = TelephonyManager.getDefault().getSimOperatorNumeric();
            String groupIdLevel1 = TelephonyManager.getDefault().getGroupIdLevel1();
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            if (!TextUtils.isEmpty(simOperatorNumeric)) {
                for (NoEmsSupportConfig noEmsSupportConfig : mNoEmsSupportConfigList) {
                    if (simOperatorNumeric.startsWith(noEmsSupportConfig.mOperatorNumber) && (TextUtils.isEmpty(noEmsSupportConfig.mGid1) || (!TextUtils.isEmpty(noEmsSupportConfig.mGid1) && noEmsSupportConfig.mGid1.equalsIgnoreCase(groupIdLevel1)))) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th;
        }
    }

    public static boolean shouldAppendPageNumberAsPrefix() {
        if (!isNoEmsSupportConfigListExisted()) {
            return false;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            String simOperatorNumeric = TelephonyManager.getDefault().getSimOperatorNumeric();
            String groupIdLevel1 = TelephonyManager.getDefault().getGroupIdLevel1();
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            for (NoEmsSupportConfig noEmsSupportConfig : mNoEmsSupportConfigList) {
                if (simOperatorNumeric.startsWith(noEmsSupportConfig.mOperatorNumber) && (TextUtils.isEmpty(noEmsSupportConfig.mGid1) || (!TextUtils.isEmpty(noEmsSupportConfig.mGid1) && noEmsSupportConfig.mGid1.equalsIgnoreCase(groupIdLevel1)))) {
                    return noEmsSupportConfig.mIsPrefix;
                }
            }
            return false;
        } catch (Throwable th) {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            throw th;
        }
    }

    private static class NoEmsSupportConfig {
        String mGid1;
        boolean mIsPrefix;
        String mOperatorNumber;

        public NoEmsSupportConfig(String[] strArr) {
            this.mOperatorNumber = strArr[0];
            this.mIsPrefix = "prefix".equals(strArr[1]);
            this.mGid1 = strArr.length > 2 ? strArr[2] : null;
        }

        public String toString() {
            return "NoEmsSupportConfig { mOperatorNumber = " + this.mOperatorNumber + ", mIsPrefix = " + this.mIsPrefix + ", mGid1 = " + this.mGid1 + " }";
        }
    }

    private static boolean isNoEmsSupportConfigListExisted() {
        Resources system;
        if (!mIsNoEmsSupportConfigListLoaded && (system = Resources.getSystem()) != null) {
            String[] stringArray = system.getStringArray(R.array.no_ems_support_sim_operators);
            if (stringArray != null && stringArray.length > 0) {
                mNoEmsSupportConfigList = new NoEmsSupportConfig[stringArray.length];
                for (int i = 0; i < stringArray.length; i++) {
                    mNoEmsSupportConfigList[i] = new NoEmsSupportConfig(stringArray[i].split(";"));
                }
            }
            mIsNoEmsSupportConfigListLoaded = true;
        }
        NoEmsSupportConfig[] noEmsSupportConfigArr = mNoEmsSupportConfigList;
        return (noEmsSupportConfigArr == null || noEmsSupportConfigArr.length == 0) ? false : true;
    }

    public String getRecipientAddress() {
        return this.mWrappedSmsMessage.getRecipientAddress();
    }
}
