package com.android.bluetooth.map;

import android.database.Cursor;
import android.net.INetd;
import android.util.Base64;
import android.util.Log;
import com.android.vcard.VCardConstants;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapUtils {
    public static final long CONVO_ID_TYPE_EMAIL_IM = 2;
    public static final long CONVO_ID_TYPE_SMS_MMS = 1;
    private static final boolean D = false;
    private static final byte ESCAPE_CHAR = 61;
    private static final long HANDLE_TYPE_EMAIL_MASK = 144115188075855872L;
    private static final long HANDLE_TYPE_IM_MASK = 1152921504606846976L;
    private static final long HANDLE_TYPE_MASK = -72057594037927936L;
    private static final long HANDLE_TYPE_MMS_MASK = 72057594037927936L;
    private static final long HANDLE_TYPE_SMS_CDMA_MASK = 576460752303423488L;
    private static final long HANDLE_TYPE_SMS_GSM_MASK = 288230376151711744L;
    private static final int LONG_LONG_LENGTH = 32;
    static final int MAP_EVENT_REPORT_V10 = 10;
    static final int MAP_EVENT_REPORT_V11 = 11;
    static final int MAP_EVENT_REPORT_V12 = 12;
    static final int MAP_FEATURE_BROWSING_BIT = 4;
    static final int MAP_FEATURE_CONVERSATION_VERSION_COUNTER_BIT = 8192;
    static final int MAP_FEATURE_DATABASE_INDENTIFIER_BIT = 2048;
    static final int MAP_FEATURE_DEFAULT_BITMASK = 31;
    static final int MAP_FEATURE_DEFINED_TIMESTAMP_FORMAT_BIT = 262144;
    static final int MAP_FEATURE_DELETE_BIT = 16;
    static final int MAP_FEATURE_EVENT_REPORT_V12_BIT = 128;
    static final int MAP_FEATURE_EXTENDED_EVENT_REPORT_11_BIT = 64;
    static final int MAP_FEATURE_FOLDER_VERSION_COUNTER_BIT = 4096;
    static final int MAP_FEATURE_INSTANCE_INFORMATION_BIT = 32;
    static final int MAP_FEATURE_MESSAGE_FORMAT_V11_BIT = 256;
    static final int MAP_FEATURE_MESSAGE_LISTING_FORMAT_V11_BIT = 512;
    static final int MAP_FEATURE_NOTIFICATION_BIT = 2;
    static final int MAP_FEATURE_NOTIFICATION_FILTERING_BIT = 131072;
    static final int MAP_FEATURE_NOTIFICATION_REGISTRATION_BIT = 1;
    static final int MAP_FEATURE_PARTICIPANT_CHAT_STATE_CHANGE_BIT = 32768;
    static final int MAP_FEATURE_PARTICIPANT_PRESENCE_CHANGE_BIT = 16384;
    static final int MAP_FEATURE_PBAP_CONTACT_CROSS_REFERENCE_BIT = 65536;
    static final int MAP_FEATURE_PERSISTENT_MESSAGE_HANDLE_BIT = 1024;
    static final int MAP_FEATURE_UPLOADING_BIT = 8;
    static final int MAP_MESSAGE_FORMAT_V10 = 10;
    static final int MAP_MESSAGE_FORMAT_V11 = 11;
    static final int MAP_MESSAGE_LISTING_FORMAT_V10 = 10;
    static final int MAP_MESSAGE_LISTING_FORMAT_V11 = 11;
    static final String MAP_V10_STR = "1.0";
    static final String MAP_V11_STR = "1.1";
    static final String MAP_V12_STR = "1.2";
    private static final byte SPACE = 32;
    private static final byte TAB = 9;
    private static final String TAG = "BluetoothMapUtils";
    private static final boolean V = false;
    private static boolean mPeerSupportUtcTimeStamp = false;
    private static final Pattern PATTERN = Pattern.compile("=\\?(.+?)\\?(.)\\?(.+?(?=\\?=))\\?=");

    public enum TYPE {
        NONE,
        EMAIL,
        SMS_GSM,
        SMS_CDMA,
        MMS,
        IM;

        private static TYPE[] sAllValues = values();

        public static TYPE fromOrdinal(int n) {
            TYPE[] typeArr = sAllValues;
            if (n < typeArr.length) {
                return typeArr[n];
            }
            return NONE;
        }
    }

    public static void printCursor(Cursor c) {
    }

    public static String getLongAsString(long v) {
        char[] result = new char[16];
        int v1 = (int) (v & (-1));
        int v2 = (int) ((-1) & (v >> 32));
        for (int i = 0; i < 8; i++) {
            int c = v2 & 15;
            int i2 = 48;
            result[7 - i] = (char) (c + (c < 10 ? 48 : 55));
            v2 >>= 4;
            int c2 = v1 & 15;
            if (c2 >= 10) {
                i2 = 55;
            }
            result[15 - i] = (char) (c2 + i2);
            v1 >>= 4;
        }
        return new String(result);
    }

    public static long getLongFromString(String valueStr) throws UnsupportedEncodingException {
        byte c;
        if (valueStr == null) {
            throw null;
        }
        byte[] nibbles = valueStr.getBytes("US-ASCII");
        int count = 0;
        int length = nibbles.length;
        long value = 0;
        for (int i = 0; i != length; i++) {
            byte c2 = nibbles[i];
            if (c2 >= 48 && c2 <= 57) {
                c = (byte) (c2 - 48);
            } else if (c2 >= 65 && c2 <= 70) {
                c = (byte) (c2 - 55);
            } else if (c2 >= 97 && c2 <= 102) {
                c = (byte) (c2 - 87);
            } else {
                if (c2 > 32 && c2 != 45) {
                    throw new NumberFormatException("Invalid character:" + ((int) c2));
                }
            }
            value = (value << 4) + ((long) c);
            count++;
            if (count > 16) {
                throw new NullPointerException("String to large - count: " + count);
            }
        }
        return value;
    }

    public static String getLongLongAsString(long vLow, long vHigh) {
        char[] result = new char[32];
        int v1 = (int) (vLow & (-1));
        int v2 = (int) ((vLow >> 32) & (-1));
        int v3 = (int) (vHigh & (-1));
        int v4 = (int) ((-1) & (vHigh >> 32));
        int i = 0;
        while (true) {
            int i2 = 48;
            if (i >= 8) {
                break;
            }
            int c = v2 & 15;
            int c2 = c + (c < 10 ? 48 : 55);
            int d = v4 & 15;
            int d2 = d + (d < 10 ? 48 : 55);
            result[23 - i] = (char) c2;
            result[7 - i] = (char) d2;
            v2 >>= 4;
            v4 >>= 4;
            int c3 = v1 & 15;
            int c4 = c3 + (c3 < 10 ? 48 : 55);
            int d3 = v3 & 15;
            if (d3 >= 10) {
                i2 = 55;
            }
            result[31 - i] = (char) c4;
            result[15 - i] = (char) (d3 + i2);
            v1 >>= 4;
            v3 >>= 4;
            i++;
        }
        int i3 = 0;
        while (i3 < 32 && result[i3] == '0') {
            i3++;
        }
        return new String(result, i3, 32 - i3);
    }

    public static String getMapHandle(long cpHandle, TYPE messageType) {
        if (messageType == null) {
            return "-1";
        }
        switch (AnonymousClass1.$SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[messageType.ordinal()]) {
            case 1:
                String mapHandle = getLongAsString(HANDLE_TYPE_MMS_MASK | cpHandle);
                return mapHandle;
            case 2:
                String mapHandle2 = getLongAsString(HANDLE_TYPE_SMS_GSM_MASK | cpHandle);
                return mapHandle2;
            case 3:
                String mapHandle3 = getLongAsString(HANDLE_TYPE_SMS_CDMA_MASK | cpHandle);
                return mapHandle3;
            case 4:
                String mapHandle4 = getLongAsString(HANDLE_TYPE_EMAIL_MASK | cpHandle);
                return mapHandle4;
            case 5:
                String mapHandle5 = getLongAsString(HANDLE_TYPE_IM_MASK | cpHandle);
                return mapHandle5;
            case 6:
                return "-1";
            default:
                throw new IllegalArgumentException("Message type not supported");
        }
    }

    /* JADX INFO: renamed from: com.android.bluetooth.map.BluetoothMapUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE;

        static {
            int[] iArr = new int[TYPE.values().length];
            $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE = iArr;
            try {
                iArr[TYPE.MMS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[TYPE.SMS_GSM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[TYPE.SMS_CDMA.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[TYPE.EMAIL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[TYPE.IM.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[TYPE.NONE.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public static String getMapConvoHandle(long cpHandle, TYPE messageType) {
        int i = AnonymousClass1.$SwitchMap$com$android$bluetooth$map$BluetoothMapUtils$TYPE[messageType.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            String mapHandle = getLongLongAsString(cpHandle, 1L);
            return mapHandle;
        }
        if (i == 4 || i == 5) {
            String mapHandle2 = getLongLongAsString(cpHandle, 2L);
            return mapHandle2;
        }
        throw new IllegalArgumentException("Message type not supported");
    }

    public static long getMsgHandleAsLong(String mapHandle) {
        return Long.parseLong(mapHandle, 16);
    }

    public static long getCpHandle(String mapHandle) {
        long cpHandle = getMsgHandleAsLong(mapHandle);
        return cpHandle & 72057594037927935L;
    }

    public static TYPE getMsgTypeFromHandle(String mapHandle) {
        long cpHandle = getMsgHandleAsLong(mapHandle);
        if ((HANDLE_TYPE_MMS_MASK & cpHandle) != 0) {
            return TYPE.MMS;
        }
        if ((HANDLE_TYPE_EMAIL_MASK & cpHandle) != 0) {
            return TYPE.EMAIL;
        }
        if ((HANDLE_TYPE_SMS_GSM_MASK & cpHandle) != 0) {
            return TYPE.SMS_GSM;
        }
        if ((HANDLE_TYPE_SMS_CDMA_MASK & cpHandle) != 0) {
            return TYPE.SMS_CDMA;
        }
        if ((HANDLE_TYPE_IM_MASK & cpHandle) != 0) {
            return TYPE.IM;
        }
        throw new IllegalArgumentException("Message type not found in handle string.");
    }

    public static String stripInvalidChars(String text) {
        if (text == null) {
            return INetd.NEXTHOP_NONE;
        }
        char[] out = new char[text.length()];
        int i = 0;
        int o = 0;
        int l = text.length();
        while (i < l) {
            char c = text.charAt(i);
            if ((c >= ' ' && c <= 55295) || (c >= 57344 && c <= 65533)) {
                out[o] = c;
                o++;
            }
            i++;
        }
        if (i == o) {
            return text;
        }
        return new String(out, 0, o);
    }

    public static byte[] truncateUtf8StringToBytearray(String utf8String, int maxLength) throws UnsupportedEncodingException {
        byte[] utf8Bytes = new byte[utf8String.length() + 1];
        try {
            System.arraycopy(utf8String.getBytes("UTF-8"), 0, utf8Bytes, 0, utf8String.length());
            if (utf8Bytes.length > maxLength) {
                if ((utf8Bytes[maxLength - 1] & 192) == 128) {
                    for (int i = maxLength - 2; i >= 0; i--) {
                        if ((utf8Bytes[i] & 192) == 192) {
                            byte[] utf8Bytes2 = Arrays.copyOf(utf8Bytes, i + 1);
                            utf8Bytes2[i] = 0;
                            return utf8Bytes2;
                        }
                    }
                    return utf8Bytes;
                }
                byte[] utf8Bytes3 = Arrays.copyOf(utf8Bytes, maxLength);
                utf8Bytes3[maxLength - 1] = 0;
                return utf8Bytes3;
            }
            return utf8Bytes;
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "truncateUtf8StringToBytearray: getBytes exception ", e);
            throw e;
        }
    }

    public static String stripEncoding(String in) {
        if (in.contains("=?") && in.contains("?=")) {
            Matcher m = PATTERN.matcher(in);
            while (m.find()) {
                String match = m.group(0);
                String charset = m.group(1);
                String encoding = m.group(2);
                String encodedText = m.group(3);
                Log.v(TAG, "Matching:" + match + "\nCharset: " + charset + "\nEncoding : " + encoding + "\nText: " + encodedText);
                if (encoding.equalsIgnoreCase("Q")) {
                    Log.d(TAG, "StripEncoding: Quoted Printable string : " + encodedText);
                    in = in.replace(match, new String(quotedPrintableToUtf8(encodedText, charset)));
                } else if (encoding.equalsIgnoreCase(VCardConstants.PARAM_ENCODING_B)) {
                    try {
                        Log.d(TAG, "StripEncoding: base64 string : " + encodedText);
                        String str = new String(Base64.decode(encodedText.getBytes(charset), 0), charset);
                        Log.d(TAG, "StripEncoding: decoded string : " + str);
                        in = in.replace(match, str);
                    } catch (UnsupportedEncodingException e) {
                        Log.e(TAG, "stripEncoding: Unsupported charset: " + charset);
                    } catch (IllegalArgumentException e2) {
                        Log.e(TAG, "stripEncoding: string not encoded as base64: " + encodedText);
                    }
                } else {
                    Log.e(TAG, "stripEncoding: Hit unknown encoding: " + encoding);
                }
            }
        }
        return in;
    }

    /* JADX WARN: Incorrect condition in loop: B:56:0x00b5 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] quotedPrintableToUtf8(java.lang.String r16, java.lang.String r17) throws java.io.UnsupportedEncodingException {
        /*
            Method dump skipped, instruction units count: 291
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.bluetooth.map.BluetoothMapUtils.quotedPrintableToUtf8(java.lang.String, java.lang.String):byte[]");
    }

    public static final String encodeQuotedPrintable(byte[] bArr) {
        if (bArr == 0) {
            return null;
        }
        BitSet bitSet = new BitSet(256);
        for (int i = 33; i <= 60; i++) {
            bitSet.set(i);
        }
        for (int i2 = 62; i2 <= 126; i2++) {
            bitSet.set(i2);
        }
        bitSet.set(9);
        bitSet.set(32);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i3 = 0; i3 < bArr.length; i3++) {
            int i4 = bArr[i3];
            if (i4 < 0) {
                i4 += 256;
            }
            if (bitSet.get(i4)) {
                byteArrayOutputStream.write(i4);
            } else {
                byteArrayOutputStream.write(61);
                char hex1 = Character.toUpperCase(Character.forDigit((i4 >> 4) & 15, 16));
                char hex2 = Character.toUpperCase(Character.forDigit(i4 & 15, 16));
                byteArrayOutputStream.write(hex1);
                byteArrayOutputStream.write(hex2);
            }
        }
        try {
            return byteArrayOutputStream.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return INetd.NEXTHOP_NONE;
        }
    }

    static String getDateTimeString(long timestamp) {
        SimpleDateFormat format = mPeerSupportUtcTimeStamp ? new SimpleDateFormat("yyyyMMdd'T'HHmmssZ") : new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        return format.format(cal.getTime());
    }

    static void savePeerSupportUtcTimeStamp(int remoteFeatureMask) {
        if ((remoteFeatureMask & 262144) == 262144) {
            mPeerSupportUtcTimeStamp = true;
        } else {
            mPeerSupportUtcTimeStamp = false;
        }
    }
}
