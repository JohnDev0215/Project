package com.android.internal.telephony.gsm;

import android.content.res.Resources;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import com.android.internal.R;
import com.android.internal.telephony.EncodeException;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.Sms7BitEncodingTranslator;
import com.android.internal.telephony.SmsAddress;
import com.android.internal.telephony.SmsConstants;
import com.android.internal.telephony.SmsHeader;
import com.android.internal.telephony.SmsMessageBase;
import com.android.internal.telephony.uicc.IccUtils;
import com.android.internal.util.FrameworkStatsLog;
import com.android.telephony.Rlog;
import com.google.android.mms.pdu.CharacterSets;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes4.dex */
public class SmsMessage extends SmsMessageBase {
    private static final int INVALID_VALIDITY_PERIOD = -1;
    static final String LOG_TAG = "SmsMessage";
    private static final int VALIDITY_PERIOD_FORMAT_ABSOLUTE = 3;
    private static final int VALIDITY_PERIOD_FORMAT_ENHANCED = 1;
    private static final int VALIDITY_PERIOD_FORMAT_NONE = 0;
    private static final int VALIDITY_PERIOD_FORMAT_RELATIVE = 2;
    private static final int VALIDITY_PERIOD_MAX = 635040;
    private static final int VALIDITY_PERIOD_MIN = 5;
    private static final boolean VDBG = false;
    private int mDataCodingScheme;
    private int mMti;
    private int mProtocolIdentifier;
    private int mStatus;
    private SmsConstants.MessageClass messageClass;
    private boolean mReplyPathPresent = false;
    private boolean mIsStatusReportMessage = false;
    private int mVoiceMailCount = 0;

    public static class SubmitPdu extends SmsMessageBase.SubmitPduBase {
    }

    public static SmsMessage createFromPdu(byte[] bArr) {
        try {
            SmsMessage smsMessage = new SmsMessage();
            smsMessage.parsePdu(bArr);
            return smsMessage;
        } catch (OutOfMemoryError e) {
            Rlog.e(LOG_TAG, "SMS PDU parsing failed with out of memory: ", e);
            return null;
        } catch (RuntimeException e2) {
            Rlog.e(LOG_TAG, "SMS PDU parsing failed: ", e2);
            return null;
        }
    }

    public boolean isTypeZero() {
        return this.mProtocolIdentifier == 64;
    }

    public static SmsMessage newFromCMT(byte[] bArr) {
        try {
            SmsMessage smsMessage = new SmsMessage();
            smsMessage.parsePdu(bArr);
            return smsMessage;
        } catch (RuntimeException e) {
            Rlog.e(LOG_TAG, "SMS PDU parsing failed: ", e);
            return null;
        }
    }

    public static SmsMessage newFromCDS(byte[] bArr) {
        try {
            SmsMessage smsMessage = new SmsMessage();
            smsMessage.parsePdu(bArr);
            return smsMessage;
        } catch (RuntimeException e) {
            Rlog.e(LOG_TAG, "CDS SMS PDU parsing failed: ", e);
            return null;
        }
    }

    public static SmsMessage createFromEfRecord(int i, byte[] bArr) {
        try {
            SmsMessage smsMessage = new SmsMessage();
            smsMessage.mIndexOnIcc = i;
            if ((bArr[0] & 1) == 0) {
                Rlog.w(LOG_TAG, "SMS parsing failed: Trying to parse a free record");
                return null;
            }
            smsMessage.mStatusOnIcc = bArr[0] & 7;
            int length = bArr.length - 1;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 1, bArr2, 0, length);
            smsMessage.parsePdu(bArr2);
            return smsMessage;
        } catch (RuntimeException e) {
            Rlog.e(LOG_TAG, "SMS PDU parsing failed: ", e);
            return null;
        }
    }

    public static int getTPLayerLengthForPDU(String str) {
        return ((str.length() / 2) - Integer.parseInt(str.substring(0, 2), 16)) - 1;
    }

    public static int getRelativeValidityPeriod(int i) {
        if (i < 5 || i > VALIDITY_PERIOD_MAX) {
            Rlog.e(LOG_TAG, "Invalid Validity Period" + i);
            return -1;
        }
        if (i <= 720) {
            return (i / 5) - 1;
        }
        if (i <= 1440) {
            return ((i - 720) / 30) + 143;
        }
        if (i <= 43200) {
            return (i / 1440) + 166;
        }
        if (i <= VALIDITY_PERIOD_MAX) {
            return (i / FrameworkStatsLog.SETTING_SNAPSHOT) + 192;
        }
        return -1;
    }

    public static SubmitPdu getSubmitPdu(String str, String str2, String str3, boolean z, byte[] bArr) {
        return getSubmitPdu(str, str2, str3, z, bArr, 0, 0, 0);
    }

    public static SubmitPdu getSubmitPdu(String str, String str2, String str3, boolean z, byte[] bArr, int i, int i2, int i3) {
        return getSubmitPdu(str, str2, str3, z, bArr, i, i2, i3, -1);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0074  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.android.internal.telephony.gsm.SmsMessage.SubmitPdu getSubmitPdu(java.lang.String r16, java.lang.String r17, java.lang.String r18, boolean r19, byte[] r20, int r21, int r22, int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.telephony.gsm.SmsMessage.getSubmitPdu(java.lang.String, java.lang.String, java.lang.String, boolean, byte[], int, int, int, int):com.android.internal.telephony.gsm.SmsMessage$SubmitPdu");
    }

    private static byte[] encodeUCS2(String str, byte[] bArr) throws EncodeException, UnsupportedEncodingException {
        byte[] bytes = str.getBytes("utf-16be");
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length + bytes.length + 1];
            bArr2[0] = (byte) bArr.length;
            System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
            System.arraycopy(bytes, 0, bArr2, bArr.length + 1, bytes.length);
            bytes = bArr2;
        }
        if (bytes.length > 255) {
            throw new EncodeException("Payload cannot exceed 255 bytes", 1);
        }
        byte[] bArr3 = new byte[bytes.length + 1];
        bArr3[0] = (byte) (255 & bytes.length);
        System.arraycopy(bytes, 0, bArr3, 1, bytes.length);
        return bArr3;
    }

    public static SubmitPdu getSubmitPdu(String str, String str2, String str3, boolean z) {
        return getSubmitPdu(str, str2, str3, z, (byte[]) null);
    }

    public static SubmitPdu getSubmitPdu(String str, String str2, String str3, boolean z, int i) {
        return getSubmitPdu(str, str2, str3, z, null, 0, 0, 0, i);
    }

    public static SubmitPdu getSubmitPdu(String str, String str2, int i, byte[] bArr, boolean z) {
        SmsHeader.PortAddrs portAddrs = new SmsHeader.PortAddrs();
        portAddrs.destPort = i;
        portAddrs.origPort = 0;
        portAddrs.areEightBits = false;
        SmsHeader smsHeader = new SmsHeader();
        smsHeader.portAddrs = portAddrs;
        byte[] byteArray = SmsHeader.toByteArray(smsHeader);
        if (bArr.length + byteArray.length + 1 > 140) {
            StringBuilder sb = new StringBuilder();
            sb.append("SMS data message may only contain ");
            sb.append((140 - byteArray.length) - 1);
            sb.append(" bytes");
            Rlog.e(LOG_TAG, sb.toString());
            return null;
        }
        SubmitPdu submitPdu = new SubmitPdu();
        ByteArrayOutputStream submitPduHead = getSubmitPduHead(str, str2, (byte) 65, z, submitPdu);
        if (submitPduHead == null) {
            return submitPdu;
        }
        submitPduHead.write(4);
        submitPduHead.write(bArr.length + byteArray.length + 1);
        submitPduHead.write(byteArray.length);
        submitPduHead.write(byteArray, 0, byteArray.length);
        submitPduHead.write(bArr, 0, bArr.length);
        submitPdu.encodedMessage = submitPduHead.toByteArray();
        return submitPdu;
    }

    private static ByteArrayOutputStream getSubmitPduHead(String str, String str2, byte b, boolean z, SubmitPdu submitPdu) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(180);
        if (str == null) {
            submitPdu.encodedScAddress = null;
        } else {
            submitPdu.encodedScAddress = PhoneNumberUtils.networkPortionToCalledPartyBCDWithLength(str);
        }
        if (z) {
            b = (byte) (b | 32);
        }
        byteArrayOutputStream.write(b);
        byteArrayOutputStream.write(0);
        byte[] bArrNetworkPortionToCalledPartyBCD = PhoneNumberUtils.networkPortionToCalledPartyBCD(str2);
        if (bArrNetworkPortionToCalledPartyBCD == null) {
            return null;
        }
        byteArrayOutputStream.write(((bArrNetworkPortionToCalledPartyBCD.length - 1) * 2) - ((bArrNetworkPortionToCalledPartyBCD[bArrNetworkPortionToCalledPartyBCD.length - 1] & 240) != 240 ? 0 : 1));
        byteArrayOutputStream.write(bArrNetworkPortionToCalledPartyBCD, 0, bArrNetworkPortionToCalledPartyBCD.length);
        byteArrayOutputStream.write(0);
        return byteArrayOutputStream;
    }

    /* JADX WARN: Type inference failed for: r7v4, types: [java.time.LocalDateTime] */
    public static SubmitPdu getDeliverPdu(String str, String str2, String str3, long j) {
        byte[] byteArray;
        byte[] bArrEncodeUCS2;
        if (str2 != null && str3 != null) {
            GsmAlphabet.TextEncodingDetails textEncodingDetailsCalculateLength = calculateLength(str3, false);
            int i = textEncodingDetailsCalculateLength.codeUnitSize;
            int i2 = textEncodingDetailsCalculateLength.languageTable;
            int i3 = textEncodingDetailsCalculateLength.languageShiftTable;
            if (i != 1 || (i2 == 0 && i3 == 0)) {
                byteArray = null;
            } else {
                SmsHeader smsHeader = new SmsHeader();
                smsHeader.languageTable = i2;
                smsHeader.languageShiftTable = i3;
                byteArray = SmsHeader.toByteArray(smsHeader);
            }
            SubmitPdu submitPdu = new SubmitPdu();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(180);
            if (str == null) {
                submitPdu.encodedScAddress = null;
            } else {
                submitPdu.encodedScAddress = PhoneNumberUtils.networkPortionToCalledPartyBCDWithLength(str);
            }
            byteArrayOutputStream.write(0);
            byte[] bArrNetworkPortionToCalledPartyBCD = PhoneNumberUtils.networkPortionToCalledPartyBCD(str2);
            if (bArrNetworkPortionToCalledPartyBCD == null) {
                return null;
            }
            byteArrayOutputStream.write(((bArrNetworkPortionToCalledPartyBCD.length - 1) * 2) - ((bArrNetworkPortionToCalledPartyBCD[bArrNetworkPortionToCalledPartyBCD.length - 1] & 240) == 240 ? 1 : 0));
            byteArrayOutputStream.write(bArrNetworkPortionToCalledPartyBCD, 0, bArrNetworkPortionToCalledPartyBCD.length);
            byteArrayOutputStream.write(0);
            try {
                if (i == 1) {
                    bArrEncodeUCS2 = GsmAlphabet.stringToGsm7BitPackedWithHeader(str3, byteArray, i2, i3);
                } else {
                    try {
                        bArrEncodeUCS2 = encodeUCS2(str3, byteArray);
                    } catch (UnsupportedEncodingException e) {
                        Rlog.e(LOG_TAG, "Implausible UnsupportedEncodingException ", e);
                        return null;
                    }
                }
            } catch (EncodeException e2) {
                if (e2.getError() == 1) {
                    Rlog.e(LOG_TAG, "Exceed size limitation EncodeException", e2);
                    return null;
                }
                try {
                    bArrEncodeUCS2 = encodeUCS2(str3, byteArray);
                    i = 3;
                } catch (EncodeException e3) {
                    Rlog.e(LOG_TAG, "Exceed size limitation EncodeException", e3);
                } catch (UnsupportedEncodingException e4) {
                    Rlog.e(LOG_TAG, "Implausible UnsupportedEncodingException ", e4);
                    return null;
                }
            }
            if (i == 1) {
                if ((bArrEncodeUCS2[0] & 255) > 160) {
                    Rlog.e(LOG_TAG, "Message too long (" + (bArrEncodeUCS2[0] & 255) + " septets)");
                    return null;
                }
                byteArrayOutputStream.write(0);
            } else {
                if ((bArrEncodeUCS2[0] & 255) > 140) {
                    Rlog.e(LOG_TAG, "Message too long (" + (bArrEncodeUCS2[0] & 255) + " bytes)");
                    return null;
                }
                byteArrayOutputStream.write(8);
            }
            byte[] bArr = new byte[7];
            ZonedDateTime zonedDateTimeAtZone = Instant.ofEpochMilli(j).atZone(ZoneId.systemDefault());
            ?? localDateTime = zonedDateTimeAtZone.toLocalDateTime();
            int totalSeconds = (zonedDateTimeAtZone.getOffset().getTotalSeconds() / 60) / 15;
            boolean z = totalSeconds < 0;
            if (z) {
                totalSeconds = -totalSeconds;
            }
            int year = localDateTime.getYear();
            int monthValue = localDateTime.getMonthValue();
            int dayOfMonth = localDateTime.getDayOfMonth();
            int hour = localDateTime.getHour();
            int minute = localDateTime.getMinute();
            int second = localDateTime.getSecond();
            int i4 = year > 2000 ? year - 2000 : year - 1900;
            bArr[0] = (byte) ((((i4 % 10) & 15) << 4) | ((i4 / 10) & 15));
            bArr[1] = (byte) ((((monthValue % 10) & 15) << 4) | ((monthValue / 10) & 15));
            bArr[2] = (byte) ((((dayOfMonth % 10) & 15) << 4) | ((dayOfMonth / 10) & 15));
            bArr[3] = (byte) ((((hour % 10) & 15) << 4) | ((hour / 10) & 15));
            bArr[4] = (byte) ((((minute % 10) & 15) << 4) | ((minute / 10) & 15));
            bArr[5] = (byte) ((((second % 10) & 15) << 4) | ((second / 10) & 15));
            bArr[6] = (byte) (((totalSeconds / 10) & 15) | (((totalSeconds % 10) & 15) << 4));
            if (z) {
                bArr[0] = (byte) (bArr[0] | 8);
            }
            byteArrayOutputStream.write(bArr, 0, 7);
            byteArrayOutputStream.write(bArrEncodeUCS2, 0, bArrEncodeUCS2.length);
            submitPdu.encodedMessage = byteArrayOutputStream.toByteArray();
            return submitPdu;
        }
        return null;
    }

    private static class PduParser {
        byte[] mPdu;
        byte[] mUserData;
        SmsHeader mUserDataHeader;
        int mCur = 0;
        int mUserDataSeptetPadding = 0;

        PduParser(byte[] bArr) {
            this.mPdu = bArr;
        }

        String getSCAddress() {
            int i = getByte();
            String strCalledPartyBCDToString = null;
            if (i != 0) {
                try {
                    strCalledPartyBCDToString = PhoneNumberUtils.calledPartyBCDToString(this.mPdu, this.mCur, i, 2);
                } catch (RuntimeException e) {
                    Rlog.d(SmsMessage.LOG_TAG, "invalid SC address: ", e);
                }
            }
            this.mCur += i;
            return strCalledPartyBCDToString;
        }

        int getByte() {
            byte[] bArr = this.mPdu;
            int i = this.mCur;
            this.mCur = i + 1;
            return bArr[i] & 255;
        }

        GsmSmsAddress getAddress() {
            int i = (((this.mPdu[this.mCur] & 255) + 1) / 2) + 2;
            try {
                GsmSmsAddress gsmSmsAddress = new GsmSmsAddress(this.mPdu, this.mCur, i);
                this.mCur += i;
                return gsmSmsAddress;
            } catch (ParseException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        long getSCTimestampMillis() {
            byte[] bArr = this.mPdu;
            int i = this.mCur;
            this.mCur = i + 1;
            int iGsmBcdByteToInt = IccUtils.gsmBcdByteToInt(bArr[i]);
            byte[] bArr2 = this.mPdu;
            int i2 = this.mCur;
            this.mCur = i2 + 1;
            int iGsmBcdByteToInt2 = IccUtils.gsmBcdByteToInt(bArr2[i2]);
            byte[] bArr3 = this.mPdu;
            int i3 = this.mCur;
            this.mCur = i3 + 1;
            int iGsmBcdByteToInt3 = IccUtils.gsmBcdByteToInt(bArr3[i3]);
            byte[] bArr4 = this.mPdu;
            int i4 = this.mCur;
            this.mCur = i4 + 1;
            int iGsmBcdByteToInt4 = IccUtils.gsmBcdByteToInt(bArr4[i4]);
            byte[] bArr5 = this.mPdu;
            int i5 = this.mCur;
            this.mCur = i5 + 1;
            int iGsmBcdByteToInt5 = IccUtils.gsmBcdByteToInt(bArr5[i5]);
            byte[] bArr6 = this.mPdu;
            int i6 = this.mCur;
            this.mCur = i6 + 1;
            int iGsmBcdByteToInt6 = IccUtils.gsmBcdByteToInt(bArr6[i6]);
            byte[] bArr7 = this.mPdu;
            int i7 = this.mCur;
            this.mCur = i7 + 1;
            byte b = bArr7[i7];
            int iGsmBcdByteToInt7 = IccUtils.gsmBcdByteToInt((byte) (b & (-9)));
            if ((b & 8) != 0) {
                iGsmBcdByteToInt7 = -iGsmBcdByteToInt7;
            }
            return (LocalDateTime.of(iGsmBcdByteToInt >= 90 ? iGsmBcdByteToInt + 1900 : iGsmBcdByteToInt + 2000, iGsmBcdByteToInt2, iGsmBcdByteToInt3, iGsmBcdByteToInt4, iGsmBcdByteToInt5, iGsmBcdByteToInt6).toEpochSecond(ZoneOffset.UTC) - ((long) ((iGsmBcdByteToInt7 * 15) * 60))) * 1000;
        }

        int constructUserData(boolean z, boolean z2) {
            int i;
            int i2;
            int length;
            int i3 = this.mCur;
            byte[] bArr = this.mPdu;
            int i4 = i3 + 1;
            int i5 = bArr[i3] & 255;
            if (z) {
                int i6 = i4 + 1;
                int i7 = bArr[i4] & 255;
                byte[] bArr2 = new byte[i7];
                System.arraycopy(bArr, i6, bArr2, 0, i7);
                this.mUserDataHeader = SmsHeader.fromByteArray(bArr2);
                int i8 = i6 + i7;
                int i9 = (i7 + 1) * 8;
                i2 = (i9 / 7) + (i9 % 7 > 0 ? 1 : 0);
                this.mUserDataSeptetPadding = (i2 * 7) - i9;
                i4 = i8;
                i = i7;
            } else {
                i = 0;
                i2 = 0;
            }
            if (z2) {
                length = this.mPdu.length - i4;
            } else {
                length = i5 - (z ? i + 1 : 0);
                if (length < 0) {
                    length = 0;
                }
            }
            byte[] bArr3 = new byte[length];
            this.mUserData = bArr3;
            System.arraycopy(this.mPdu, i4, bArr3, 0, bArr3.length);
            this.mCur = i4;
            if (!z2) {
                return this.mUserData.length;
            }
            int i10 = i5 - i2;
            if (i10 < 0) {
                return 0;
            }
            return i10;
        }

        byte[] getUserData() {
            return this.mUserData;
        }

        SmsHeader getUserDataHeader() {
            return this.mUserDataHeader;
        }

        String getUserDataGSM7Bit(int i, int i2, int i3) {
            String strGsm7BitPackedToString = GsmAlphabet.gsm7BitPackedToString(this.mPdu, this.mCur, i, this.mUserDataSeptetPadding, i2, i3);
            this.mCur += (i * 7) / 8;
            return strGsm7BitPackedToString;
        }

        String getUserDataGSM8bit(int i) {
            String strGsm8BitUnpackedToString = GsmAlphabet.gsm8BitUnpackedToString(this.mPdu, this.mCur, i);
            this.mCur += i;
            return strGsm8BitUnpackedToString;
        }

        String getUserDataUCS2(int i) {
            String str;
            try {
                str = new String(this.mPdu, this.mCur, i, CharacterSets.MIMENAME_UTF_16);
            } catch (UnsupportedEncodingException e) {
                Rlog.e(SmsMessage.LOG_TAG, "implausible UnsupportedEncodingException", e);
                str = "";
            }
            this.mCur += i;
            return str;
        }

        String getUserDataKSC5601(int i) {
            String str;
            try {
                str = new String(this.mPdu, this.mCur, i, "KSC5601");
            } catch (UnsupportedEncodingException e) {
                Rlog.e(SmsMessage.LOG_TAG, "implausible UnsupportedEncodingException", e);
                str = "";
            }
            this.mCur += i;
            return str;
        }

        boolean moreDataPresent() {
            return this.mPdu.length > this.mCur;
        }
    }

    public static GsmAlphabet.TextEncodingDetails calculateLength(CharSequence charSequence, boolean z) {
        String strTranslate = Resources.getSystem().getBoolean(R.bool.config_sms_force_7bit_encoding) ? Sms7BitEncodingTranslator.translate(charSequence, false) : null;
        if (!TextUtils.isEmpty(strTranslate)) {
            charSequence = strTranslate;
        }
        GsmAlphabet.TextEncodingDetails textEncodingDetailsCountGsmSeptets = GsmAlphabet.countGsmSeptets(charSequence, z);
        return textEncodingDetailsCountGsmSeptets == null ? SmsMessageBase.calcUnicodeEncodingDetails(charSequence) : textEncodingDetailsCountGsmSeptets;
    }

    @Override // com.android.internal.telephony.SmsMessageBase
    public int getProtocolIdentifier() {
        return this.mProtocolIdentifier;
    }

    int getDataCodingScheme() {
        return this.mDataCodingScheme;
    }

    @Override // com.android.internal.telephony.SmsMessageBase
    public boolean isReplace() {
        int i = this.mProtocolIdentifier;
        return (i & 192) == 64 && (i & 63) > 0 && (i & 63) < 8;
    }

    @Override // com.android.internal.telephony.SmsMessageBase
    public boolean isCphsMwiMessage() {
        return ((GsmSmsAddress) this.mOriginatingAddress).isCphsVoiceMessageClear() || ((GsmSmsAddress) this.mOriginatingAddress).isCphsVoiceMessageSet();
    }

    @Override // com.android.internal.telephony.SmsMessageBase
    public boolean isMWIClearMessage() {
        if (!this.mIsMwi || this.mMwiSense) {
            return this.mOriginatingAddress != null && ((GsmSmsAddress) this.mOriginatingAddress).isCphsVoiceMessageClear();
        }
        return true;
    }

    @Override // com.android.internal.telephony.SmsMessageBase
    public boolean isMWISetMessage() {
        if (this.mIsMwi && this.mMwiSense) {
            return true;
        }
        return this.mOriginatingAddress != null && ((GsmSmsAddress) this.mOriginatingAddress).isCphsVoiceMessageSet();
    }

    @Override // com.android.internal.telephony.SmsMessageBase
    public boolean isMwiDontStore() {
        if (this.mIsMwi && this.mMwiDontStore) {
            return true;
        }
        return isCphsMwiMessage() && " ".equals(getMessageBody());
    }

    @Override // com.android.internal.telephony.SmsMessageBase
    public int getStatus() {
        return this.mStatus;
    }

    @Override // com.android.internal.telephony.SmsMessageBase
    public boolean isStatusReportMessage() {
        return this.mIsStatusReportMessage;
    }

    @Override // com.android.internal.telephony.SmsMessageBase
    public boolean isReplyPathPresent() {
        return this.mReplyPathPresent;
    }

    private void parsePdu(byte[] bArr) {
        this.mPdu = bArr;
        PduParser pduParser = new PduParser(bArr);
        this.mScAddress = pduParser.getSCAddress();
        String str = this.mScAddress;
        int i = pduParser.getByte();
        int i2 = i & 3;
        this.mMti = i2;
        if (i2 != 0) {
            if (i2 == 1) {
                parseSmsSubmit(pduParser, i);
                return;
            } else if (i2 == 2) {
                parseSmsStatusReport(pduParser, i);
                return;
            } else if (i2 != 3) {
                throw new RuntimeException("Unsupported message type");
            }
        }
        parseSmsDeliver(pduParser, i);
    }

    private void parseSmsStatusReport(PduParser pduParser, int i) {
        this.mIsStatusReportMessage = true;
        this.mMessageRef = pduParser.getByte();
        this.mRecipientAddress = pduParser.getAddress();
        this.mScTimeMillis = pduParser.getSCTimestampMillis();
        pduParser.getSCTimestampMillis();
        this.mStatus = pduParser.getByte();
        if (pduParser.moreDataPresent()) {
            int i2 = pduParser.getByte();
            int i3 = i2;
            while ((i3 & 128) != 0) {
                i3 = pduParser.getByte();
            }
            if ((i2 & 120) == 0) {
                if ((i2 & 1) != 0) {
                    this.mProtocolIdentifier = pduParser.getByte();
                }
                if ((i2 & 2) != 0) {
                    this.mDataCodingScheme = pduParser.getByte();
                }
                if ((i2 & 4) != 0) {
                    parseUserData(pduParser, (i & 64) == 64);
                }
            }
        }
    }

    private void parseSmsDeliver(PduParser pduParser, int i) {
        this.mReplyPathPresent = (i & 128) == 128;
        this.mOriginatingAddress = pduParser.getAddress();
        SmsAddress smsAddress = this.mOriginatingAddress;
        this.mProtocolIdentifier = pduParser.getByte();
        this.mDataCodingScheme = pduParser.getByte();
        this.mScTimeMillis = pduParser.getSCTimestampMillis();
        parseUserData(pduParser, (i & 64) == 64);
    }

    private void parseSmsSubmit(PduParser pduParser, int i) {
        this.mReplyPathPresent = (i & 128) == 128;
        this.mMessageRef = pduParser.getByte();
        this.mRecipientAddress = pduParser.getAddress();
        SmsAddress smsAddress = this.mRecipientAddress;
        this.mProtocolIdentifier = pduParser.getByte();
        this.mDataCodingScheme = pduParser.getByte();
        int i2 = (i >> 3) & 3;
        int i3 = i2 == 0 ? 0 : 2 == i2 ? 1 : 7;
        while (true) {
            int i4 = i3 - 1;
            if (i3 <= 0) {
                break;
            }
            pduParser.getByte();
            i3 = i4;
        }
        parseUserData(pduParser, (i & 64) == 64);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01e2  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void parseUserData(com.android.internal.telephony.gsm.SmsMessage.PduParser r17, boolean r18) {
        /*
            Method dump skipped, instruction units count: 656
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.telephony.gsm.SmsMessage.parseUserData(com.android.internal.telephony.gsm.SmsMessage$PduParser, boolean):void");
    }

    @Override // com.android.internal.telephony.SmsMessageBase
    public SmsConstants.MessageClass getMessageClass() {
        return this.messageClass;
    }

    boolean isUsimDataDownload() {
        int i;
        return this.messageClass == SmsConstants.MessageClass.CLASS_2 && ((i = this.mProtocolIdentifier) == 127 || i == 124);
    }

    public int getNumOfVoicemails() {
        if (!this.mIsMwi && isCphsMwiMessage()) {
            if (this.mOriginatingAddress != null && ((GsmSmsAddress) this.mOriginatingAddress).isCphsVoiceMessageSet()) {
                this.mVoiceMailCount = 255;
            } else {
                this.mVoiceMailCount = 0;
            }
            Rlog.v(LOG_TAG, "CPHS voice mail message");
        }
        return this.mVoiceMailCount;
    }
}
