package com.android.internal.telephony.cdma.sms;

import android.content.res.Resources;
import android.telephony.SmsCbCmasInfo;
import android.telephony.cdma.CdmaSmsCbProgramData;
import android.telephony.cdma.CdmaSmsCbProgramResults;
import com.android.internal.R;
import com.android.internal.telephony.EncodeException;
import com.android.internal.telephony.GsmAlphabet;
import com.android.internal.telephony.SmsHeader;
import com.android.internal.telephony.SmsMessageBase;
import com.android.internal.telephony.gsm.SmsMessage;
import com.android.internal.telephony.uicc.IccUtils;
import com.android.internal.util.BitwiseInputStream;
import com.android.internal.util.BitwiseOutputStream;
import com.android.telephony.Rlog;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class BearerData {
    public static final int ALERT_DEFAULT = 0;
    public static final int ALERT_HIGH_PRIO = 3;
    public static final int ALERT_LOW_PRIO = 1;
    public static final int ALERT_MEDIUM_PRIO = 2;
    public static final int DISPLAY_MODE_DEFAULT = 1;
    public static final int DISPLAY_MODE_IMMEDIATE = 0;
    public static final int DISPLAY_MODE_USER = 2;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_PERMANENT = 3;
    public static final int ERROR_TEMPORARY = 2;
    public static final int ERROR_UNDEFINED = 255;
    public static final int LANGUAGE_CHINESE = 6;
    public static final int LANGUAGE_ENGLISH = 1;
    public static final int LANGUAGE_FRENCH = 2;
    public static final int LANGUAGE_HEBREW = 7;
    public static final int LANGUAGE_JAPANESE = 4;
    public static final int LANGUAGE_KOREAN = 5;
    public static final int LANGUAGE_SPANISH = 3;
    public static final int LANGUAGE_UNKNOWN = 0;
    private static final String LOG_TAG = "BearerData";
    public static final int MESSAGE_TYPE_CANCELLATION = 3;
    public static final int MESSAGE_TYPE_DELIVER = 1;
    public static final int MESSAGE_TYPE_DELIVERY_ACK = 4;
    public static final int MESSAGE_TYPE_DELIVER_REPORT = 7;
    public static final int MESSAGE_TYPE_READ_ACK = 6;
    public static final int MESSAGE_TYPE_SUBMIT = 2;
    public static final int MESSAGE_TYPE_SUBMIT_REPORT = 8;
    public static final int MESSAGE_TYPE_USER_ACK = 5;
    public static final int PRIORITY_EMERGENCY = 3;
    public static final int PRIORITY_INTERACTIVE = 1;
    public static final int PRIORITY_NORMAL = 0;
    public static final int PRIORITY_URGENT = 2;
    public static final int PRIVACY_CONFIDENTIAL = 2;
    public static final int PRIVACY_NOT_RESTRICTED = 0;
    public static final int PRIVACY_RESTRICTED = 1;
    public static final int PRIVACY_SECRET = 3;
    public static final int RELATIVE_TIME_DAYS_LIMIT = 196;
    public static final int RELATIVE_TIME_HOURS_LIMIT = 167;
    public static final int RELATIVE_TIME_INDEFINITE = 245;
    public static final int RELATIVE_TIME_MINS_LIMIT = 143;
    public static final int RELATIVE_TIME_MOBILE_INACTIVE = 247;
    public static final int RELATIVE_TIME_NOW = 246;
    public static final int RELATIVE_TIME_RESERVED = 248;
    public static final int RELATIVE_TIME_WEEKS_LIMIT = 244;
    public static final int STATUS_ACCEPTED = 0;
    public static final int STATUS_BLOCKED_DESTINATION = 7;
    public static final int STATUS_CANCELLED = 3;
    public static final int STATUS_CANCEL_FAILED = 6;
    public static final int STATUS_DELIVERED = 2;
    public static final int STATUS_DEPOSITED_TO_INTERNET = 1;
    public static final int STATUS_DUPLICATE_MESSAGE = 9;
    public static final int STATUS_INVALID_DESTINATION = 10;
    public static final int STATUS_MESSAGE_EXPIRED = 13;
    public static final int STATUS_NETWORK_CONGESTION = 4;
    public static final int STATUS_NETWORK_ERROR = 5;
    public static final int STATUS_TEXT_TOO_LONG = 8;
    public static final int STATUS_UNDEFINED = 255;
    public static final int STATUS_UNKNOWN_ERROR = 31;
    private static final byte SUBPARAM_ALERT_ON_MESSAGE_DELIVERY = 12;
    private static final byte SUBPARAM_CALLBACK_NUMBER = 14;
    private static final byte SUBPARAM_DEFERRED_DELIVERY_TIME_ABSOLUTE = 6;
    private static final byte SUBPARAM_DEFERRED_DELIVERY_TIME_RELATIVE = 7;
    private static final byte SUBPARAM_ID_LAST_DEFINED = 23;
    private static final byte SUBPARAM_LANGUAGE_INDICATOR = 13;
    private static final byte SUBPARAM_MESSAGE_CENTER_TIME_STAMP = 3;
    private static final byte SUBPARAM_MESSAGE_DEPOSIT_INDEX = 17;
    private static final byte SUBPARAM_MESSAGE_DISPLAY_MODE = 15;
    private static final byte SUBPARAM_MESSAGE_IDENTIFIER = 0;
    private static final byte SUBPARAM_MESSAGE_STATUS = 20;
    private static final byte SUBPARAM_NUMBER_OF_MESSAGES = 11;
    private static final byte SUBPARAM_PRIORITY_INDICATOR = 8;
    private static final byte SUBPARAM_PRIVACY_INDICATOR = 9;
    private static final byte SUBPARAM_REPLY_OPTION = 10;
    private static final byte SUBPARAM_SERVICE_CATEGORY_PROGRAM_DATA = 18;
    private static final byte SUBPARAM_SERVICE_CATEGORY_PROGRAM_RESULTS = 19;
    private static final byte SUBPARAM_USER_DATA = 1;
    private static final byte SUBPARAM_USER_RESPONSE_CODE = 2;
    private static final byte SUBPARAM_VALIDITY_PERIOD_ABSOLUTE = 4;
    private static final byte SUBPARAM_VALIDITY_PERIOD_RELATIVE = 5;
    public CdmaSmsAddress callbackNumber;
    public SmsCbCmasInfo cmasWarningInfo;
    public TimeStamp deferredDeliveryTimeAbsolute;
    public int deferredDeliveryTimeRelative;
    public boolean deferredDeliveryTimeRelativeSet;
    public boolean deliveryAckReq;
    public int depositIndex;
    public boolean hasUserDataHeader;
    public int messageId;
    public int messageType;
    public TimeStamp msgCenterTimeStamp;
    public int numberOfMessages;
    public boolean readAckReq;
    public boolean reportReq;
    public ArrayList<CdmaSmsCbProgramData> serviceCategoryProgramData;
    public ArrayList<CdmaSmsCbProgramResults> serviceCategoryProgramResults;
    public boolean userAckReq;
    public UserData userData;
    public int userResponseCode;
    public TimeStamp validityPeriodAbsolute;
    public int validityPeriodRelative;
    public boolean validityPeriodRelativeSet;
    public boolean priorityIndicatorSet = false;
    public int priority = 0;
    public boolean privacyIndicatorSet = false;
    public int privacy = 0;
    public boolean alertIndicatorSet = false;
    public int alert = 0;
    public boolean displayModeSet = false;
    public int displayMode = 1;
    public boolean languageIndicatorSet = false;
    public int language = 0;
    public boolean messageStatusSet = false;
    public int errorClass = 255;
    public int messageStatus = 255;
    public boolean userResponseCodeSet = false;

    private static String getLanguageCodeForValue(int i) {
        switch (i) {
            case 1:
                return "en";
            case 2:
                return "fr";
            case 3:
                return "es";
            case 4:
                return "ja";
            case 5:
                return "ko";
            case 6:
                return "zh";
            case 7:
                return "he";
            default:
                return null;
        }
    }

    private static boolean isCmasAlertCategory(int i) {
        return i >= 4096 && i <= 4351;
    }

    private static int serviceCategoryToCmasMessageClass(int i) {
        switch (i) {
            case 4096:
                return 0;
            case 4097:
                return 1;
            case 4098:
                return 2;
            case 4099:
                return 3;
            case 4100:
                return 4;
            default:
                return -1;
        }
    }

    public static class TimeStamp {
        public int hour;
        private ZoneId mZoneId = ZoneId.systemDefault();
        public int minute;
        public int monthDay;
        public int monthOrdinal;
        public int second;
        public int year;

        public static TimeStamp fromByteArray(byte[] bArr) {
            TimeStamp timeStamp = new TimeStamp();
            int iCdmaBcdByteToInt = IccUtils.cdmaBcdByteToInt(bArr[0]);
            if (iCdmaBcdByteToInt <= 99 && iCdmaBcdByteToInt >= 0) {
                timeStamp.year = iCdmaBcdByteToInt >= 96 ? iCdmaBcdByteToInt + 1900 : iCdmaBcdByteToInt + 2000;
                int iCdmaBcdByteToInt2 = IccUtils.cdmaBcdByteToInt(bArr[1]);
                if (iCdmaBcdByteToInt2 >= 1 && iCdmaBcdByteToInt2 <= 12) {
                    timeStamp.monthOrdinal = iCdmaBcdByteToInt2;
                    int iCdmaBcdByteToInt3 = IccUtils.cdmaBcdByteToInt(bArr[2]);
                    if (iCdmaBcdByteToInt3 >= 1 && iCdmaBcdByteToInt3 <= 31) {
                        timeStamp.monthDay = iCdmaBcdByteToInt3;
                        int iCdmaBcdByteToInt4 = IccUtils.cdmaBcdByteToInt(bArr[3]);
                        if (iCdmaBcdByteToInt4 >= 0 && iCdmaBcdByteToInt4 <= 23) {
                            timeStamp.hour = iCdmaBcdByteToInt4;
                            int iCdmaBcdByteToInt5 = IccUtils.cdmaBcdByteToInt(bArr[4]);
                            if (iCdmaBcdByteToInt5 >= 0 && iCdmaBcdByteToInt5 <= 59) {
                                timeStamp.minute = iCdmaBcdByteToInt5;
                                int iCdmaBcdByteToInt6 = IccUtils.cdmaBcdByteToInt(bArr[5]);
                                if (iCdmaBcdByteToInt6 >= 0 && iCdmaBcdByteToInt6 <= 59) {
                                    timeStamp.second = iCdmaBcdByteToInt6;
                                    return timeStamp;
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        /* JADX WARN: Type inference failed for: r2v3, types: [java.time.LocalDateTime] */
        public static TimeStamp fromMillis(long j) {
            TimeStamp timeStamp = new TimeStamp();
            ?? localDateTime = Instant.ofEpochMilli(j).atZone(timeStamp.mZoneId).toLocalDateTime();
            int year = localDateTime.getYear();
            if (year < 1996 || year > 2095) {
                return null;
            }
            timeStamp.year = year;
            timeStamp.monthOrdinal = localDateTime.getMonthValue();
            timeStamp.monthDay = localDateTime.getDayOfMonth();
            timeStamp.hour = localDateTime.getHour();
            timeStamp.minute = localDateTime.getMinute();
            timeStamp.second = localDateTime.getSecond();
            return timeStamp;
        }

        public byte[] toByteArray() {
            int i = this.year % 100;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(6);
            byteArrayOutputStream.write(((i % 10) & 15) | (((i / 10) & 15) << 4));
            int i2 = this.monthOrdinal;
            byteArrayOutputStream.write(((i2 % 10) & 15) | (((i2 / 10) << 4) & 240));
            int i3 = this.monthDay;
            byteArrayOutputStream.write(((i3 % 10) & 15) | (((i3 / 10) << 4) & 240));
            int i4 = this.hour;
            byteArrayOutputStream.write(((i4 % 10) & 15) | (((i4 / 10) << 4) & 240));
            int i5 = this.minute;
            byteArrayOutputStream.write(((i5 % 10) & 15) | (((i5 / 10) << 4) & 240));
            int i6 = this.second;
            byteArrayOutputStream.write(((i6 % 10) & 15) | (((i6 / 10) << 4) & 240));
            return byteArrayOutputStream.toByteArray();
        }

        public long toMillis() {
            LocalDateTime localDateTimeOf = LocalDateTime.of(this.year, this.monthOrdinal, this.monthDay, this.hour, this.minute, this.second);
            return localDateTimeOf.toInstant(this.mZoneId.getRules().getOffset(localDateTimeOf)).toEpochMilli();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("TimeStamp ");
            sb.append("{ year=" + this.year);
            sb.append(", month=" + this.monthOrdinal);
            sb.append(", day=" + this.monthDay);
            sb.append(", hour=" + this.hour);
            sb.append(", minute=" + this.minute);
            sb.append(", second=" + this.second);
            sb.append(" }");
            return sb.toString();
        }
    }

    private static class CodingException extends Exception {
        public CodingException(String str) {
            super(str);
        }
    }

    public String getLanguage() {
        return getLanguageCodeForValue(this.language);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BearerData ");
        sb.append("{ messageType=" + this.messageType);
        sb.append(", messageId=" + this.messageId);
        sb.append(", priority=" + (this.priorityIndicatorSet ? Integer.valueOf(this.priority) : "unset"));
        sb.append(", privacy=" + (this.privacyIndicatorSet ? Integer.valueOf(this.privacy) : "unset"));
        sb.append(", alert=" + (this.alertIndicatorSet ? Integer.valueOf(this.alert) : "unset"));
        sb.append(", displayMode=" + (this.displayModeSet ? Integer.valueOf(this.displayMode) : "unset"));
        sb.append(", language=" + (this.languageIndicatorSet ? Integer.valueOf(this.language) : "unset"));
        sb.append(", errorClass=" + (this.messageStatusSet ? Integer.valueOf(this.errorClass) : "unset"));
        sb.append(", msgStatus=" + (this.messageStatusSet ? Integer.valueOf(this.messageStatus) : "unset"));
        StringBuilder sbAppend = new StringBuilder().append(", msgCenterTimeStamp=");
        Object obj = this.msgCenterTimeStamp;
        if (obj == null) {
            obj = "unset";
        }
        sb.append(sbAppend.append(obj).toString());
        StringBuilder sbAppend2 = new StringBuilder().append(", validityPeriodAbsolute=");
        Object obj2 = this.validityPeriodAbsolute;
        if (obj2 == null) {
            obj2 = "unset";
        }
        sb.append(sbAppend2.append(obj2).toString());
        sb.append(", validityPeriodRelative=" + (this.validityPeriodRelativeSet ? Integer.valueOf(this.validityPeriodRelative) : "unset"));
        StringBuilder sbAppend3 = new StringBuilder().append(", deferredDeliveryTimeAbsolute=");
        Object obj3 = this.deferredDeliveryTimeAbsolute;
        if (obj3 == null) {
            obj3 = "unset";
        }
        sb.append(sbAppend3.append(obj3).toString());
        sb.append(", deferredDeliveryTimeRelative=" + (this.deferredDeliveryTimeRelativeSet ? Integer.valueOf(this.deferredDeliveryTimeRelative) : "unset"));
        sb.append(", userAckReq=" + this.userAckReq);
        sb.append(", deliveryAckReq=" + this.deliveryAckReq);
        sb.append(", readAckReq=" + this.readAckReq);
        sb.append(", reportReq=" + this.reportReq);
        sb.append(", numberOfMessages=" + this.numberOfMessages);
        sb.append(", callbackNumber=" + Rlog.pii(LOG_TAG, this.callbackNumber));
        sb.append(", depositIndex=" + this.depositIndex);
        sb.append(", hasUserDataHeader=" + this.hasUserDataHeader);
        sb.append(", userData=" + this.userData);
        sb.append(" }");
        return sb.toString();
    }

    private static void encodeMessageId(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 3);
        bitwiseOutputStream.write(4, bearerData.messageType);
        bitwiseOutputStream.write(8, bearerData.messageId >> 8);
        bitwiseOutputStream.write(8, bearerData.messageId);
        bitwiseOutputStream.write(1, bearerData.hasUserDataHeader ? 1 : 0);
        bitwiseOutputStream.skip(3);
    }

    private static int countAsciiSeptets(CharSequence charSequence, boolean z) {
        int length = charSequence.length();
        if (z) {
            return length;
        }
        for (int i = 0; i < length; i++) {
            if (UserData.charToAscii.get(charSequence.charAt(i), -1) == -1) {
                return -1;
            }
        }
        return length;
    }

    public static GsmAlphabet.TextEncodingDetails calcTextEncodingDetails(CharSequence charSequence, boolean z, boolean z2) {
        int iCountAsciiSeptets = countAsciiSeptets(charSequence, z);
        if (iCountAsciiSeptets != -1 && iCountAsciiSeptets <= 160) {
            GsmAlphabet.TextEncodingDetails textEncodingDetails = new GsmAlphabet.TextEncodingDetails();
            textEncodingDetails.msgCount = 1;
            textEncodingDetails.codeUnitCount = iCountAsciiSeptets;
            textEncodingDetails.codeUnitsRemaining = 160 - iCountAsciiSeptets;
            textEncodingDetails.codeUnitSize = 1;
            return textEncodingDetails;
        }
        GsmAlphabet.TextEncodingDetails textEncodingDetailsCalculateLength = SmsMessage.calculateLength(charSequence, z);
        return (textEncodingDetailsCalculateLength.msgCount == 1 && textEncodingDetailsCalculateLength.codeUnitSize == 1 && z2) ? SmsMessageBase.calcUnicodeEncodingDetails(charSequence) : textEncodingDetailsCalculateLength;
    }

    private static byte[] encode7bitAscii(String str, boolean z) throws CodingException {
        try {
            BitwiseOutputStream bitwiseOutputStream = new BitwiseOutputStream(str.length());
            int length = str.length();
            for (int i = 0; i < length; i++) {
                int i2 = UserData.charToAscii.get(str.charAt(i), -1);
                if (i2 != -1) {
                    bitwiseOutputStream.write(7, i2);
                } else if (z) {
                    bitwiseOutputStream.write(7, 32);
                } else {
                    throw new CodingException("cannot ASCII encode (" + str.charAt(i) + ")");
                }
            }
            return bitwiseOutputStream.toByteArray();
        } catch (BitwiseOutputStream.AccessException e) {
            throw new CodingException("7bit ASCII encode failed: " + e);
        }
    }

    private static byte[] encodeUtf16(String str) throws CodingException {
        try {
            return str.getBytes("utf-16be");
        } catch (UnsupportedEncodingException e) {
            throw new CodingException("UTF-16 encode failed: " + e);
        }
    }

    private static class Gsm7bitCodingResult {
        byte[] data;
        int septets;

        private Gsm7bitCodingResult() {
        }
    }

    private static Gsm7bitCodingResult encode7bitGsm(String str, int i, boolean z) throws CodingException {
        try {
            byte[] bArrStringToGsm7BitPacked = GsmAlphabet.stringToGsm7BitPacked(str, i, !z, 0, 0);
            Gsm7bitCodingResult gsm7bitCodingResult = new Gsm7bitCodingResult();
            gsm7bitCodingResult.data = new byte[bArrStringToGsm7BitPacked.length - 1];
            System.arraycopy(bArrStringToGsm7BitPacked, 1, gsm7bitCodingResult.data, 0, bArrStringToGsm7BitPacked.length - 1);
            gsm7bitCodingResult.septets = bArrStringToGsm7BitPacked[0] & 255;
            return gsm7bitCodingResult;
        } catch (EncodeException e) {
            throw new CodingException("7bit GSM encode failed: " + e);
        }
    }

    private static void encode7bitEms(UserData userData, byte[] bArr, boolean z) throws CodingException {
        Gsm7bitCodingResult gsm7bitCodingResultEncode7bitGsm = encode7bitGsm(userData.payloadStr, (((bArr.length + 1) * 8) + 6) / 7, z);
        userData.msgEncoding = 9;
        userData.msgEncodingSet = true;
        userData.numFields = gsm7bitCodingResultEncode7bitGsm.septets;
        userData.payload = gsm7bitCodingResultEncode7bitGsm.data;
        userData.payload[0] = (byte) bArr.length;
        System.arraycopy(bArr, 0, userData.payload, 1, bArr.length);
    }

    private static void encode16bitEms(UserData userData, byte[] bArr) throws CodingException {
        byte[] bArrEncodeUtf16 = encodeUtf16(userData.payloadStr);
        int length = bArr.length + 1;
        int length2 = bArrEncodeUtf16.length / 2;
        userData.msgEncoding = 4;
        userData.msgEncodingSet = true;
        userData.numFields = ((length + 1) / 2) + length2;
        userData.payload = new byte[userData.numFields * 2];
        userData.payload[0] = (byte) bArr.length;
        System.arraycopy(bArr, 0, userData.payload, 1, bArr.length);
        System.arraycopy(bArrEncodeUtf16, 0, userData.payload, length, bArrEncodeUtf16.length);
    }

    private static void encode7bitAsciiEms(UserData userData, byte[] bArr, boolean z) throws CodingException {
        try {
            Rlog.d(LOG_TAG, "encode7bitAsciiEms");
            int length = bArr.length + 1;
            int i = length * 8;
            int i2 = (i + 6) / 7;
            int i3 = (i2 * 7) - i;
            String str = userData.payloadStr;
            int length2 = str.length();
            BitwiseOutputStream bitwiseOutputStream = new BitwiseOutputStream((i3 > 0 ? 1 : 0) + length2);
            bitwiseOutputStream.write(i3, 0);
            for (int i4 = 0; i4 < length2; i4++) {
                int i5 = UserData.charToAscii.get(str.charAt(i4), -1);
                if (i5 != -1) {
                    bitwiseOutputStream.write(7, i5);
                } else if (z) {
                    bitwiseOutputStream.write(7, 32);
                } else {
                    throw new CodingException("cannot ASCII encode (" + str.charAt(i4) + ")");
                }
            }
            byte[] byteArray = bitwiseOutputStream.toByteArray();
            userData.msgEncoding = 2;
            userData.msgEncodingSet = true;
            userData.numFields = i2 + userData.payloadStr.length();
            userData.payload = new byte[byteArray.length + length];
            userData.payload[0] = (byte) bArr.length;
            System.arraycopy(bArr, 0, userData.payload, 1, bArr.length);
            System.arraycopy(byteArray, 0, userData.payload, length, byteArray.length);
        } catch (BitwiseOutputStream.AccessException e) {
            throw new CodingException("7bit ASCII encode failed: " + e);
        }
    }

    private static void encodeEmsUserDataPayload(UserData userData) throws CodingException {
        byte[] byteArray = SmsHeader.toByteArray(userData.userDataHeader);
        if (userData.msgEncodingSet) {
            if (userData.msgEncoding == 9) {
                encode7bitEms(userData, byteArray, true);
                return;
            } else if (userData.msgEncoding == 4) {
                encode16bitEms(userData, byteArray);
                return;
            } else {
                if (userData.msgEncoding == 2) {
                    encode7bitAsciiEms(userData, byteArray, true);
                    return;
                }
                throw new CodingException("unsupported EMS user data encoding (" + userData.msgEncoding + ")");
            }
        }
        try {
            encode7bitEms(userData, byteArray, false);
        } catch (CodingException unused) {
            encode16bitEms(userData, byteArray);
        }
    }

    private static byte[] encodeShiftJis(String str) throws CodingException {
        try {
            return str.getBytes("Shift_JIS");
        } catch (UnsupportedEncodingException e) {
            throw new CodingException("Shift-JIS encode failed: " + e);
        }
    }

    private static void encodeUserDataPayload(UserData userData) throws CodingException {
        if (userData.payloadStr == null && userData.msgEncoding != 0) {
            Rlog.e(LOG_TAG, "user data with null payloadStr");
            userData.payloadStr = "";
        }
        if (userData.userDataHeader != null) {
            encodeEmsUserDataPayload(userData);
            return;
        }
        if (userData.msgEncodingSet) {
            if (userData.msgEncoding == 0) {
                if (userData.payload == null) {
                    Rlog.e(LOG_TAG, "user data with octet encoding but null payload");
                    userData.payload = new byte[0];
                    userData.numFields = 0;
                    return;
                }
                userData.numFields = userData.payload.length;
                return;
            }
            if (userData.payloadStr == null) {
                Rlog.e(LOG_TAG, "non-octet user data with null payloadStr");
                userData.payloadStr = "";
            }
            if (userData.msgEncoding == 9) {
                Gsm7bitCodingResult gsm7bitCodingResultEncode7bitGsm = encode7bitGsm(userData.payloadStr, 0, true);
                userData.payload = gsm7bitCodingResultEncode7bitGsm.data;
                userData.numFields = gsm7bitCodingResultEncode7bitGsm.septets;
                return;
            } else if (userData.msgEncoding == 2) {
                userData.payload = encode7bitAscii(userData.payloadStr, true);
                userData.numFields = userData.payloadStr.length();
                return;
            } else if (userData.msgEncoding == 4) {
                userData.payload = encodeUtf16(userData.payloadStr);
                userData.numFields = userData.payloadStr.length();
                return;
            } else {
                if (userData.msgEncoding == 5) {
                    userData.payload = encodeShiftJis(userData.payloadStr);
                    userData.numFields = userData.payload.length;
                    return;
                }
                throw new CodingException("unsupported user data encoding (" + userData.msgEncoding + ")");
            }
        }
        try {
            userData.payload = encode7bitAscii(userData.payloadStr, false);
            userData.msgEncoding = 2;
        } catch (CodingException unused) {
            userData.payload = encodeUtf16(userData.payloadStr);
            userData.msgEncoding = 4;
        }
        userData.numFields = userData.payloadStr.length();
        userData.msgEncodingSet = true;
    }

    private static void encodeUserData(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException, CodingException {
        encodeUserDataPayload(bearerData.userData);
        bearerData.hasUserDataHeader = bearerData.userData.userDataHeader != null;
        if (bearerData.userData.payload.length > 140) {
            throw new CodingException("encoded user data too large (" + bearerData.userData.payload.length + " > 140 bytes)");
        }
        if (bearerData.userData.msgEncoding == 2) {
            UserData userData = bearerData.userData;
            userData.paddingBits = (userData.payload.length * 8) - (bearerData.userData.numFields * 7);
        } else {
            bearerData.userData.paddingBits = 0;
        }
        int length = (bearerData.userData.payload.length * 8) - bearerData.userData.paddingBits;
        int i = length + 13;
        if (bearerData.userData.msgEncoding == 1 || bearerData.userData.msgEncoding == 10) {
            i += 8;
        }
        int i2 = (i / 8) + (i % 8 > 0 ? 1 : 0);
        int i3 = (i2 * 8) - i;
        bitwiseOutputStream.write(8, i2);
        bitwiseOutputStream.write(5, bearerData.userData.msgEncoding);
        if (bearerData.userData.msgEncoding == 1 || bearerData.userData.msgEncoding == 10) {
            bitwiseOutputStream.write(8, bearerData.userData.msgType);
        }
        bitwiseOutputStream.write(8, bearerData.userData.numFields);
        bitwiseOutputStream.writeByteArray(length, bearerData.userData.payload);
        if (i3 > 0) {
            bitwiseOutputStream.write(i3, 0);
        }
    }

    private static void encodeReplyOption(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 1);
        bitwiseOutputStream.write(1, bearerData.userAckReq ? 1 : 0);
        bitwiseOutputStream.write(1, bearerData.deliveryAckReq ? 1 : 0);
        bitwiseOutputStream.write(1, bearerData.readAckReq ? 1 : 0);
        bitwiseOutputStream.write(1, bearerData.reportReq ? 1 : 0);
        bitwiseOutputStream.write(4, 0);
    }

    private static byte[] encodeDtmfSmsAddress(String str) {
        int i;
        int length = str.length();
        int i2 = length * 4;
        byte[] bArr = new byte[(i2 / 8) + (i2 % 8 > 0 ? 1 : 0)];
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = str.charAt(i3);
            if (cCharAt >= '1' && cCharAt <= '9') {
                i = cCharAt - '0';
            } else if (cCharAt == '0') {
                i = 10;
            } else if (cCharAt == '*') {
                i = 11;
            } else {
                if (cCharAt != '#') {
                    return null;
                }
                i = 12;
            }
            int i4 = i3 / 2;
            bArr[i4] = (byte) ((i << (4 - ((i3 % 2) * 4))) | bArr[i4]);
        }
        return bArr;
    }

    private static void encodeCdmaSmsAddress(CdmaSmsAddress cdmaSmsAddress) throws CodingException {
        if (cdmaSmsAddress.digitMode == 1) {
            try {
                cdmaSmsAddress.origBytes = cdmaSmsAddress.address.getBytes("US-ASCII");
            } catch (UnsupportedEncodingException unused) {
                throw new CodingException("invalid SMS address, cannot convert to ASCII");
            }
        } else {
            cdmaSmsAddress.origBytes = encodeDtmfSmsAddress(cdmaSmsAddress.address);
        }
    }

    private static void encodeCallbackNumber(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException, CodingException {
        int i;
        int i2;
        CdmaSmsAddress cdmaSmsAddress = bearerData.callbackNumber;
        encodeCdmaSmsAddress(cdmaSmsAddress);
        if (cdmaSmsAddress.digitMode == 1) {
            i2 = 16;
            i = cdmaSmsAddress.numberOfDigits * 8;
        } else {
            i = cdmaSmsAddress.numberOfDigits * 4;
            i2 = 9;
        }
        int i3 = i2 + i;
        int i4 = (i3 / 8) + (i3 % 8 > 0 ? 1 : 0);
        int i5 = (i4 * 8) - i3;
        bitwiseOutputStream.write(8, i4);
        bitwiseOutputStream.write(1, cdmaSmsAddress.digitMode);
        if (cdmaSmsAddress.digitMode == 1) {
            bitwiseOutputStream.write(3, cdmaSmsAddress.ton);
            bitwiseOutputStream.write(4, cdmaSmsAddress.numberPlan);
        }
        bitwiseOutputStream.write(8, cdmaSmsAddress.numberOfDigits);
        bitwiseOutputStream.writeByteArray(i, cdmaSmsAddress.origBytes);
        if (i5 > 0) {
            bitwiseOutputStream.write(i5, 0);
        }
    }

    private static void encodeMsgStatus(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 1);
        bitwiseOutputStream.write(2, bearerData.errorClass);
        bitwiseOutputStream.write(6, bearerData.messageStatus);
    }

    private static void encodeMsgCount(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 1);
        bitwiseOutputStream.write(8, bearerData.numberOfMessages);
    }

    private static void encodeValidityPeriodRel(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 1);
        bitwiseOutputStream.write(8, bearerData.validityPeriodRelative);
    }

    private static void encodePrivacyIndicator(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 1);
        bitwiseOutputStream.write(2, bearerData.privacy);
        bitwiseOutputStream.skip(6);
    }

    private static void encodeLanguageIndicator(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 1);
        bitwiseOutputStream.write(8, bearerData.language);
    }

    private static void encodeDisplayMode(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 1);
        bitwiseOutputStream.write(2, bearerData.displayMode);
        bitwiseOutputStream.skip(6);
    }

    private static void encodePriorityIndicator(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 1);
        bitwiseOutputStream.write(2, bearerData.priority);
        bitwiseOutputStream.skip(6);
    }

    private static void encodeMsgDeliveryAlert(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 1);
        bitwiseOutputStream.write(2, bearerData.alert);
        bitwiseOutputStream.skip(6);
    }

    private static void encodeScpResults(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        ArrayList<CdmaSmsCbProgramResults> arrayList = bearerData.serviceCategoryProgramResults;
        bitwiseOutputStream.write(8, arrayList.size() * 4);
        for (CdmaSmsCbProgramResults cdmaSmsCbProgramResults : arrayList) {
            int category = cdmaSmsCbProgramResults.getCategory();
            bitwiseOutputStream.write(8, category >> 8);
            bitwiseOutputStream.write(8, category);
            bitwiseOutputStream.write(8, cdmaSmsCbProgramResults.getLanguage());
            bitwiseOutputStream.write(4, cdmaSmsCbProgramResults.getCategoryResult());
            bitwiseOutputStream.skip(4);
        }
    }

    private static void encodeMsgCenterTimeStamp(BearerData bearerData, BitwiseOutputStream bitwiseOutputStream) throws BitwiseOutputStream.AccessException {
        bitwiseOutputStream.write(8, 6);
        bitwiseOutputStream.writeByteArray(48, bearerData.msgCenterTimeStamp.toByteArray());
    }

    public static byte[] encode(BearerData bearerData) {
        UserData userData = bearerData.userData;
        bearerData.hasUserDataHeader = (userData == null || userData.userDataHeader == null) ? false : true;
        try {
            BitwiseOutputStream bitwiseOutputStream = new BitwiseOutputStream(200);
            bitwiseOutputStream.write(8, 0);
            encodeMessageId(bearerData, bitwiseOutputStream);
            if (bearerData.userData != null) {
                bitwiseOutputStream.write(8, 1);
                encodeUserData(bearerData, bitwiseOutputStream);
            }
            if (bearerData.callbackNumber != null) {
                bitwiseOutputStream.write(8, 14);
                encodeCallbackNumber(bearerData, bitwiseOutputStream);
            }
            if (bearerData.userAckReq || bearerData.deliveryAckReq || bearerData.readAckReq || bearerData.reportReq) {
                bitwiseOutputStream.write(8, 10);
                encodeReplyOption(bearerData, bitwiseOutputStream);
            }
            if (bearerData.numberOfMessages != 0) {
                bitwiseOutputStream.write(8, 11);
                encodeMsgCount(bearerData, bitwiseOutputStream);
            }
            if (bearerData.validityPeriodRelativeSet) {
                bitwiseOutputStream.write(8, 5);
                encodeValidityPeriodRel(bearerData, bitwiseOutputStream);
            }
            if (bearerData.privacyIndicatorSet) {
                bitwiseOutputStream.write(8, 9);
                encodePrivacyIndicator(bearerData, bitwiseOutputStream);
            }
            if (bearerData.languageIndicatorSet) {
                bitwiseOutputStream.write(8, 13);
                encodeLanguageIndicator(bearerData, bitwiseOutputStream);
            }
            if (bearerData.displayModeSet) {
                bitwiseOutputStream.write(8, 15);
                encodeDisplayMode(bearerData, bitwiseOutputStream);
            }
            if (bearerData.priorityIndicatorSet) {
                bitwiseOutputStream.write(8, 8);
                encodePriorityIndicator(bearerData, bitwiseOutputStream);
            }
            if (bearerData.alertIndicatorSet) {
                bitwiseOutputStream.write(8, 12);
                encodeMsgDeliveryAlert(bearerData, bitwiseOutputStream);
            }
            if (bearerData.messageStatusSet) {
                bitwiseOutputStream.write(8, 20);
                encodeMsgStatus(bearerData, bitwiseOutputStream);
            }
            if (bearerData.serviceCategoryProgramResults != null) {
                bitwiseOutputStream.write(8, 19);
                encodeScpResults(bearerData, bitwiseOutputStream);
            }
            if (bearerData.msgCenterTimeStamp != null) {
                bitwiseOutputStream.write(8, 3);
                encodeMsgCenterTimeStamp(bearerData, bitwiseOutputStream);
            }
            return bitwiseOutputStream.toByteArray();
        } catch (CodingException e) {
            Rlog.e(LOG_TAG, "BearerData encode failed: " + e);
            return null;
        } catch (BitwiseOutputStream.AccessException e2) {
            Rlog.e(LOG_TAG, "BearerData encode failed: " + e2);
            return null;
        }
    }

    private static boolean decodeMessageId(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 24) {
            i -= 24;
            bearerData.messageType = bitwiseInputStream.read(4);
            int i2 = bitwiseInputStream.read(8) << 8;
            bearerData.messageId = i2;
            bearerData.messageId = bitwiseInputStream.read(8) | i2;
            bearerData.hasUserDataHeader = bitwiseInputStream.read(1) == 1;
            bitwiseInputStream.skip(3);
            z = true;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "MESSAGE_IDENTIFIER decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        return z;
    }

    private static boolean decodeReserved(BearerData bearerData, BitwiseInputStream bitwiseInputStream, int i) throws BitwiseInputStream.AccessException, CodingException {
        boolean z;
        int i2 = bitwiseInputStream.read(8);
        int i3 = i2 * 8;
        if (i3 <= bitwiseInputStream.available()) {
            z = true;
            bitwiseInputStream.skip(i3);
        } else {
            z = false;
        }
        Rlog.d(LOG_TAG, "RESERVED bearer data subparameter " + i + " decode " + (z ? "succeeded" : "failed") + " (param bits = " + i3 + ")");
        if (z) {
            return z;
        }
        throw new CodingException("RESERVED bearer data subparameter " + i + " had invalid SUBPARAM_LEN " + i2);
    }

    private static boolean decodeUserData(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        int i = bitwiseInputStream.read(8) * 8;
        UserData userData = new UserData();
        bearerData.userData = userData;
        int i2 = 5;
        userData.msgEncoding = bitwiseInputStream.read(5);
        bearerData.userData.msgEncodingSet = true;
        bearerData.userData.msgType = 0;
        if (bearerData.userData.msgEncoding == 1 || bearerData.userData.msgEncoding == 10) {
            bearerData.userData.msgType = bitwiseInputStream.read(8);
            i2 = 13;
        }
        bearerData.userData.numFields = bitwiseInputStream.read(8);
        bearerData.userData.payload = bitwiseInputStream.readByteArray(i - (i2 + 8));
        return true;
    }

    private static String decodeUtf8(byte[] bArr, int i, int i2) throws CodingException {
        return decodeCharset(bArr, i, i2, 1, "UTF-8");
    }

    private static String decodeUtf16(byte[] bArr, int i, int i2) throws CodingException {
        return decodeCharset(bArr, i, i2 - (((i % 2) + i) / 2), 2, "utf-16be");
    }

    private static String decodeCharset(byte[] bArr, int i, int i2, int i3, String str) throws CodingException {
        if (i2 < 0 || (i2 * i3) + i > bArr.length) {
            int length = ((bArr.length - i) - (i % i3)) / i3;
            if (length < 0) {
                throw new CodingException(str + " decode failed: offset out of range");
            }
            Rlog.e(LOG_TAG, str + " decode error: offset = " + i + " numFields = " + i2 + " data.length = " + bArr.length + " maxNumFields = " + length);
            i2 = length;
        }
        try {
            return new String(bArr, i, i2 * i3, str);
        } catch (UnsupportedEncodingException e) {
            throw new CodingException(str + " decode failed: " + e);
        }
    }

    private static String decode7bitAscii(byte[] bArr, int i, int i2) throws CodingException {
        try {
            int i3 = ((i * 8) + 6) / 7;
            int i4 = i2 - i3;
            StringBuffer stringBuffer = new StringBuffer(i4);
            BitwiseInputStream bitwiseInputStream = new BitwiseInputStream(bArr);
            int i5 = i3 * 7;
            int i6 = (i4 * 7) + i5;
            if (bitwiseInputStream.available() < i6) {
                throw new CodingException("insufficient data (wanted " + i6 + " bits, but only have " + bitwiseInputStream.available() + ")");
            }
            bitwiseInputStream.skip(i5);
            for (int i7 = 0; i7 < i4; i7++) {
                int i8 = bitwiseInputStream.read(7);
                if (i8 >= 32 && i8 <= UserData.ASCII_MAP_MAX_INDEX) {
                    stringBuffer.append(UserData.ASCII_MAP[i8 - 32]);
                } else if (i8 == 10) {
                    stringBuffer.append('\n');
                } else if (i8 == 13) {
                    stringBuffer.append('\r');
                } else {
                    stringBuffer.append(' ');
                }
            }
            return stringBuffer.toString();
        } catch (BitwiseInputStream.AccessException e) {
            throw new CodingException("7bit ASCII decode failed: " + e);
        }
    }

    private static String decode7bitGsm(byte[] bArr, int i, int i2) throws CodingException {
        int i3 = i * 8;
        int i4 = (i3 + 6) / 7;
        String strGsm7BitPackedToString = GsmAlphabet.gsm7BitPackedToString(bArr, i, i2 - i4, (i4 * 7) - i3, 0, 0);
        if (strGsm7BitPackedToString != null) {
            return strGsm7BitPackedToString;
        }
        throw new CodingException("7bit GSM decoding failed");
    }

    private static String decodeLatin(byte[] bArr, int i, int i2) throws CodingException {
        return decodeCharset(bArr, i, i2, 1, "ISO-8859-1");
    }

    private static String decodeShiftJis(byte[] bArr, int i, int i2) throws CodingException {
        return decodeCharset(bArr, i, i2, 1, "Shift_JIS");
    }

    private static String decodeGsmDcs(byte[] bArr, int i, int i2, int i3) throws CodingException {
        if ((i3 & 192) != 0) {
            throw new CodingException("unsupported coding group (" + i3 + ")");
        }
        int i4 = (i3 >> 2) & 3;
        if (i4 == 0) {
            return decode7bitGsm(bArr, i, i2);
        }
        if (i4 == 1) {
            return decodeUtf8(bArr, i, i2);
        }
        if (i4 == 2) {
            return decodeUtf16(bArr, i, i2);
        }
        throw new CodingException("unsupported user msgType encoding (" + i3 + ")");
    }

    private static void decodeUserDataPayload(UserData userData, boolean z) throws CodingException {
        int i;
        if (z) {
            int i2 = userData.payload[0] & 255;
            i = i2 + 1 + 0;
            byte[] bArr = new byte[i2];
            System.arraycopy(userData.payload, 1, bArr, 0, i2);
            userData.userDataHeader = SmsHeader.fromByteArray(bArr);
        } else {
            i = 0;
        }
        int i3 = userData.msgEncoding;
        if (i3 == 0) {
            boolean z2 = Resources.getSystem().getBoolean(R.bool.config_sms_utf8_support);
            byte[] bArr2 = new byte[userData.numFields];
            System.arraycopy(userData.payload, 0, bArr2, 0, userData.numFields < userData.payload.length ? userData.numFields : userData.payload.length);
            userData.payload = bArr2;
            if (!z2) {
                userData.payloadStr = decodeLatin(userData.payload, i, userData.numFields);
                return;
            } else {
                userData.payloadStr = decodeUtf8(userData.payload, i, userData.numFields);
                return;
            }
        }
        if (i3 == 2 || i3 == 3) {
            userData.payloadStr = decode7bitAscii(userData.payload, i, userData.numFields);
            return;
        }
        if (i3 == 4) {
            userData.payloadStr = decodeUtf16(userData.payload, i, userData.numFields);
            return;
        }
        if (i3 != 5) {
            switch (i3) {
                case 8:
                    userData.payloadStr = decodeLatin(userData.payload, i, userData.numFields);
                    return;
                case 9:
                    userData.payloadStr = decode7bitGsm(userData.payload, i, userData.numFields);
                    return;
                case 10:
                    userData.payloadStr = decodeGsmDcs(userData.payload, i, userData.numFields, userData.msgType);
                    return;
                default:
                    throw new CodingException("unsupported user data encoding (" + userData.msgEncoding + ")");
            }
        }
        userData.payloadStr = decodeShiftJis(userData.payload, i, userData.numFields);
    }

    private static void decodeIs91VoicemailStatus(BearerData bearerData) throws BitwiseInputStream.AccessException, CodingException {
        BitwiseInputStream bitwiseInputStream = new BitwiseInputStream(bearerData.userData.payload);
        int iAvailable = bitwiseInputStream.available() / 6;
        int i = bearerData.userData.numFields;
        if (iAvailable > 14 || iAvailable < 3 || iAvailable < i) {
            throw new CodingException("IS-91 voicemail status decoding failed");
        }
        try {
            StringBuffer stringBuffer = new StringBuffer(iAvailable);
            while (bitwiseInputStream.available() >= 6) {
                stringBuffer.append(UserData.ASCII_MAP[bitwiseInputStream.read(6)]);
            }
            String string = stringBuffer.toString();
            bearerData.numberOfMessages = Integer.parseInt(string.substring(0, 2));
            char cCharAt = string.charAt(2);
            if (cCharAt == ' ') {
                bearerData.priority = 0;
            } else if (cCharAt == '!') {
                bearerData.priority = 2;
            } else {
                throw new CodingException("IS-91 voicemail status decoding failed: illegal priority setting (" + cCharAt + ")");
            }
            bearerData.priorityIndicatorSet = true;
            bearerData.userData.payloadStr = string.substring(3, i - 3);
        } catch (IndexOutOfBoundsException e) {
            throw new CodingException("IS-91 voicemail status decoding failed: " + e);
        } catch (NumberFormatException e2) {
            throw new CodingException("IS-91 voicemail status decoding failed: " + e2);
        }
    }

    private static void decodeIs91ShortMessage(BearerData bearerData) throws BitwiseInputStream.AccessException, CodingException {
        BitwiseInputStream bitwiseInputStream = new BitwiseInputStream(bearerData.userData.payload);
        int iAvailable = bitwiseInputStream.available() / 6;
        int i = bearerData.userData.numFields;
        if (i > 14 || iAvailable < i) {
            throw new CodingException("IS-91 short message decoding failed");
        }
        StringBuffer stringBuffer = new StringBuffer(iAvailable);
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(UserData.ASCII_MAP[bitwiseInputStream.read(6)]);
        }
        bearerData.userData.payloadStr = stringBuffer.toString();
    }

    private static void decodeIs91Cli(BearerData bearerData) throws CodingException {
        int iAvailable = new BitwiseInputStream(bearerData.userData.payload).available() / 4;
        int i = bearerData.userData.numFields;
        if (iAvailable > 14 || iAvailable < 3 || iAvailable < i) {
            throw new CodingException("IS-91 voicemail status decoding failed");
        }
        CdmaSmsAddress cdmaSmsAddress = new CdmaSmsAddress();
        cdmaSmsAddress.digitMode = 0;
        cdmaSmsAddress.origBytes = bearerData.userData.payload;
        cdmaSmsAddress.numberOfDigits = (byte) i;
        decodeSmsAddress(cdmaSmsAddress);
        bearerData.callbackNumber = cdmaSmsAddress;
    }

    private static void decodeIs91(BearerData bearerData) throws BitwiseInputStream.AccessException, CodingException {
        switch (bearerData.userData.msgType) {
            case 130:
                decodeIs91VoicemailStatus(bearerData);
                return;
            case 131:
            case 133:
                decodeIs91ShortMessage(bearerData);
                return;
            case 132:
                decodeIs91Cli(bearerData);
                return;
            default:
                throw new CodingException("unsupported IS-91 message type (" + bearerData.userData.msgType + ")");
        }
    }

    private static boolean decodeReplyOption(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            bearerData.userAckReq = bitwiseInputStream.read(1) == 1;
            bearerData.deliveryAckReq = bitwiseInputStream.read(1) == 1;
            bearerData.readAckReq = bitwiseInputStream.read(1) == 1;
            bearerData.reportReq = bitwiseInputStream.read(1) == 1;
            bitwiseInputStream.skip(4);
            z = true;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "REPLY_OPTION decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        return z;
    }

    private static boolean decodeMsgCount(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            z = true;
            bearerData.numberOfMessages = IccUtils.cdmaBcdByteToInt((byte) bitwiseInputStream.read(8));
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "NUMBER_OF_MESSAGES decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        return z;
    }

    private static boolean decodeDepositIndex(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 16) {
            i -= 16;
            z = true;
            bearerData.depositIndex = bitwiseInputStream.read(8) | (bitwiseInputStream.read(8) << 8);
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "MESSAGE_DEPOSIT_INDEX decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        return z;
    }

    private static String decodeDtmfSmsAddress(byte[] bArr, int i) throws CodingException {
        StringBuffer stringBuffer = new StringBuffer(i);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = (bArr[i2 / 2] >>> (4 - ((i2 % 2) * 4))) & 15;
            if (i3 >= 1 && i3 <= 9) {
                stringBuffer.append(Integer.toString(i3, 10));
            } else if (i3 == 10) {
                stringBuffer.append('0');
            } else if (i3 == 11) {
                stringBuffer.append('*');
            } else if (i3 == 12) {
                stringBuffer.append('#');
            } else {
                throw new CodingException("invalid SMS address DTMF code (" + i3 + ")");
            }
        }
        return stringBuffer.toString();
    }

    private static void decodeSmsAddress(CdmaSmsAddress cdmaSmsAddress) throws CodingException {
        if (cdmaSmsAddress.digitMode == 1) {
            try {
                cdmaSmsAddress.address = new String(cdmaSmsAddress.origBytes, 0, cdmaSmsAddress.origBytes.length, "US-ASCII");
            } catch (UnsupportedEncodingException unused) {
                throw new CodingException("invalid SMS address ASCII code");
            }
        } else {
            cdmaSmsAddress.address = decodeDtmfSmsAddress(cdmaSmsAddress.origBytes, cdmaSmsAddress.numberOfDigits);
        }
    }

    private static boolean decodeCallbackNumber(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException, CodingException {
        byte b;
        int i = bitwiseInputStream.read(8) * 8;
        if (i < 8) {
            bitwiseInputStream.skip(i);
            return false;
        }
        CdmaSmsAddress cdmaSmsAddress = new CdmaSmsAddress();
        cdmaSmsAddress.digitMode = bitwiseInputStream.read(1);
        int i2 = 4;
        if (cdmaSmsAddress.digitMode == 1) {
            cdmaSmsAddress.ton = bitwiseInputStream.read(3);
            cdmaSmsAddress.numberPlan = bitwiseInputStream.read(4);
            b = (byte) 8;
            i2 = 8;
        } else {
            b = 1;
        }
        cdmaSmsAddress.numberOfDigits = bitwiseInputStream.read(8);
        int i3 = i - ((byte) (b + 8));
        int i4 = cdmaSmsAddress.numberOfDigits * i2;
        int i5 = i3 - i4;
        if (i3 < i4) {
            throw new CodingException("CALLBACK_NUMBER subparam encoding size error (remainingBits + " + i3 + ", dataBits + " + i4 + ", paddingBits + " + i5 + ")");
        }
        cdmaSmsAddress.origBytes = bitwiseInputStream.readByteArray(i4);
        bitwiseInputStream.skip(i5);
        decodeSmsAddress(cdmaSmsAddress);
        bearerData.callbackNumber = cdmaSmsAddress;
        return true;
    }

    private static boolean decodeMsgStatus(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            z = true;
            bearerData.errorClass = bitwiseInputStream.read(2);
            bearerData.messageStatus = bitwiseInputStream.read(6);
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "MESSAGE_STATUS decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        bearerData.messageStatusSet = z;
        return z;
    }

    private static boolean decodeMsgCenterTimeStamp(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 48) {
            i -= 48;
            z = true;
            bearerData.msgCenterTimeStamp = TimeStamp.fromByteArray(bitwiseInputStream.readByteArray(48));
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "MESSAGE_CENTER_TIME_STAMP decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        return z;
    }

    private static boolean decodeValidityAbs(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 48) {
            i -= 48;
            z = true;
            bearerData.validityPeriodAbsolute = TimeStamp.fromByteArray(bitwiseInputStream.readByteArray(48));
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "VALIDITY_PERIOD_ABSOLUTE decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        return z;
    }

    private static boolean decodeDeferredDeliveryAbs(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 48) {
            i -= 48;
            z = true;
            bearerData.deferredDeliveryTimeAbsolute = TimeStamp.fromByteArray(bitwiseInputStream.readByteArray(48));
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "DEFERRED_DELIVERY_TIME_ABSOLUTE decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        return z;
    }

    private static boolean decodeValidityRel(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            z = true;
            bearerData.deferredDeliveryTimeRelative = bitwiseInputStream.read(8);
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "VALIDITY_PERIOD_RELATIVE decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        bearerData.deferredDeliveryTimeRelativeSet = z;
        return z;
    }

    private static boolean decodeDeferredDeliveryRel(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            z = true;
            bearerData.validityPeriodRelative = bitwiseInputStream.read(8);
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "DEFERRED_DELIVERY_TIME_RELATIVE decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        bearerData.validityPeriodRelativeSet = z;
        return z;
    }

    private static boolean decodePrivacyIndicator(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            z = true;
            bearerData.privacy = bitwiseInputStream.read(2);
            bitwiseInputStream.skip(6);
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "PRIVACY_INDICATOR decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        bearerData.privacyIndicatorSet = z;
        return z;
    }

    private static boolean decodeLanguageIndicator(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            z = true;
            bearerData.language = bitwiseInputStream.read(8);
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "LANGUAGE_INDICATOR decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        bearerData.languageIndicatorSet = z;
        return z;
    }

    private static boolean decodeDisplayMode(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            z = true;
            bearerData.displayMode = bitwiseInputStream.read(2);
            bitwiseInputStream.skip(6);
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "DISPLAY_MODE decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        bearerData.displayModeSet = z;
        return z;
    }

    private static boolean decodePriorityIndicator(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            z = true;
            bearerData.priority = bitwiseInputStream.read(2);
            bitwiseInputStream.skip(6);
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "PRIORITY_INDICATOR decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        bearerData.priorityIndicatorSet = z;
        return z;
    }

    private static boolean decodeMsgDeliveryAlert(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            z = true;
            bearerData.alert = bitwiseInputStream.read(2);
            bitwiseInputStream.skip(6);
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "ALERT_ON_MESSAGE_DELIVERY decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        bearerData.alertIndicatorSet = z;
        return z;
    }

    private static boolean decodeUserResponseCode(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException {
        boolean z;
        int i = bitwiseInputStream.read(8) * 8;
        if (i >= 8) {
            i -= 8;
            z = true;
            bearerData.userResponseCode = bitwiseInputStream.read(8);
        } else {
            z = false;
        }
        if (!z || i > 0) {
            Rlog.d(LOG_TAG, "USER_RESPONSE_CODE decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i + ")");
        }
        bitwiseInputStream.skip(i);
        bearerData.userResponseCodeSet = z;
        return z;
    }

    private static boolean decodeServiceCategoryProgramData(BearerData bearerData, BitwiseInputStream bitwiseInputStream) throws BitwiseInputStream.AccessException, CodingException {
        if (bitwiseInputStream.available() < 13) {
            throw new CodingException("SERVICE_CATEGORY_PROGRAM_DATA decode failed: only " + bitwiseInputStream.available() + " bits available");
        }
        int i = bitwiseInputStream.read(8) * 8;
        int i2 = bitwiseInputStream.read(5);
        int i3 = i - 5;
        if (bitwiseInputStream.available() < i3) {
            throw new CodingException("SERVICE_CATEGORY_PROGRAM_DATA decode failed: only " + bitwiseInputStream.available() + " bits available (" + i3 + " bits expected)");
        }
        ArrayList<CdmaSmsCbProgramData> arrayList = new ArrayList<>();
        boolean z = false;
        while (i3 >= 48) {
            int i4 = bitwiseInputStream.read(4);
            int i5 = bitwiseInputStream.read(8) | (bitwiseInputStream.read(8) << 8);
            int i6 = bitwiseInputStream.read(8);
            int i7 = bitwiseInputStream.read(8);
            int i8 = bitwiseInputStream.read(4);
            int i9 = bitwiseInputStream.read(8);
            int i10 = i3 - 48;
            int bitsForNumFields = getBitsForNumFields(i2, i9);
            if (i10 < bitsForNumFields) {
                throw new CodingException("category name is " + bitsForNumFields + " bits in length, but there are only " + i10 + " bits available");
            }
            UserData userData = new UserData();
            userData.msgEncoding = i2;
            userData.msgEncodingSet = true;
            userData.numFields = i9;
            userData.payload = bitwiseInputStream.readByteArray(bitsForNumFields);
            i3 = i10 - bitsForNumFields;
            decodeUserDataPayload(userData, false);
            arrayList.add(new CdmaSmsCbProgramData(i4, i5, i6, i7, i8, userData.payloadStr));
            z = true;
        }
        if (!z || i3 > 0) {
            Rlog.d(LOG_TAG, "SERVICE_CATEGORY_PROGRAM_DATA decode " + (z ? "succeeded" : "failed") + " (extra bits = " + i3 + ')');
        }
        bitwiseInputStream.skip(i3);
        bearerData.serviceCategoryProgramData = arrayList;
        return z;
    }

    private static int getBitsForNumFields(int i, int i2) throws CodingException {
        if (i != 0) {
            switch (i) {
                case 2:
                case 3:
                case 9:
                    return i2 * 7;
                case 4:
                    return i2 * 16;
                case 5:
                case 6:
                case 7:
                case 8:
                    break;
                default:
                    throw new CodingException("unsupported message encoding (" + i + ')');
            }
        }
        return i2 * 8;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void decodeCmasUserData(com.android.internal.telephony.cdma.sms.BearerData r16, int r17) throws com.android.internal.util.BitwiseInputStream.AccessException, com.android.internal.telephony.cdma.sms.BearerData.CodingException {
        /*
            Method dump skipped, instruction units count: 227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.telephony.cdma.sms.BearerData.decodeCmasUserData(com.android.internal.telephony.cdma.sms.BearerData, int):void");
    }

    public static BearerData decode(byte[] bArr) {
        return decode(bArr, 0);
    }

    public static BearerData decode(byte[] bArr, int i) {
        boolean zDecodeMessageId;
        try {
            BitwiseInputStream bitwiseInputStream = new BitwiseInputStream(bArr);
            BearerData bearerData = new BearerData();
            int i2 = 0;
            while (bitwiseInputStream.available() > 0) {
                int i3 = bitwiseInputStream.read(8);
                int i4 = 1 << i3;
                if ((i2 & i4) != 0 && i3 >= 0 && i3 <= 23) {
                    throw new CodingException("illegal duplicate subparameter (" + i3 + ")");
                }
                switch (i3) {
                    case 0:
                        zDecodeMessageId = decodeMessageId(bearerData, bitwiseInputStream);
                        break;
                    case 1:
                        zDecodeMessageId = decodeUserData(bearerData, bitwiseInputStream);
                        break;
                    case 2:
                        zDecodeMessageId = decodeUserResponseCode(bearerData, bitwiseInputStream);
                        break;
                    case 3:
                        zDecodeMessageId = decodeMsgCenterTimeStamp(bearerData, bitwiseInputStream);
                        break;
                    case 4:
                        zDecodeMessageId = decodeValidityAbs(bearerData, bitwiseInputStream);
                        break;
                    case 5:
                        zDecodeMessageId = decodeValidityRel(bearerData, bitwiseInputStream);
                        break;
                    case 6:
                        zDecodeMessageId = decodeDeferredDeliveryAbs(bearerData, bitwiseInputStream);
                        break;
                    case 7:
                        zDecodeMessageId = decodeDeferredDeliveryRel(bearerData, bitwiseInputStream);
                        break;
                    case 8:
                        zDecodeMessageId = decodePriorityIndicator(bearerData, bitwiseInputStream);
                        break;
                    case 9:
                        zDecodeMessageId = decodePrivacyIndicator(bearerData, bitwiseInputStream);
                        break;
                    case 10:
                        zDecodeMessageId = decodeReplyOption(bearerData, bitwiseInputStream);
                        break;
                    case 11:
                        zDecodeMessageId = decodeMsgCount(bearerData, bitwiseInputStream);
                        break;
                    case 12:
                        zDecodeMessageId = decodeMsgDeliveryAlert(bearerData, bitwiseInputStream);
                        break;
                    case 13:
                        zDecodeMessageId = decodeLanguageIndicator(bearerData, bitwiseInputStream);
                        break;
                    case 14:
                        zDecodeMessageId = decodeCallbackNumber(bearerData, bitwiseInputStream);
                        break;
                    case 15:
                        zDecodeMessageId = decodeDisplayMode(bearerData, bitwiseInputStream);
                        break;
                    case 16:
                    case 19:
                    default:
                        zDecodeMessageId = decodeReserved(bearerData, bitwiseInputStream, i3);
                        break;
                    case 17:
                        zDecodeMessageId = decodeDepositIndex(bearerData, bitwiseInputStream);
                        break;
                    case 18:
                        zDecodeMessageId = decodeServiceCategoryProgramData(bearerData, bitwiseInputStream);
                        break;
                    case 20:
                        zDecodeMessageId = decodeMsgStatus(bearerData, bitwiseInputStream);
                        break;
                }
                if (zDecodeMessageId && i3 >= 0 && i3 <= 23) {
                    i2 |= i4;
                }
            }
            if ((i2 & 1) == 0) {
                throw new CodingException("missing MESSAGE_IDENTIFIER subparam");
            }
            if (bearerData.userData != null) {
                if (isCmasAlertCategory(i)) {
                    decodeCmasUserData(bearerData, i);
                } else if (bearerData.userData.msgEncoding == 1) {
                    if (((i2 ^ 1) ^ 2) != 0) {
                        Rlog.e(LOG_TAG, "IS-91 must occur without extra subparams (" + i2 + ")");
                    }
                    decodeIs91(bearerData);
                } else {
                    decodeUserDataPayload(bearerData.userData, bearerData.hasUserDataHeader);
                }
            }
            return bearerData;
        } catch (CodingException e) {
            Rlog.e(LOG_TAG, "BearerData decode failed: " + e);
            return null;
        } catch (BitwiseInputStream.AccessException e2) {
            Rlog.e(LOG_TAG, "BearerData decode failed: " + e2);
            return null;
        }
    }
}
