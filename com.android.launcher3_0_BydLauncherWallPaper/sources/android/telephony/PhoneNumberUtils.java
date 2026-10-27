package android.telephony;

import android.annotation.SystemApi;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.PersistableBundle;
import android.provider.SettingsStringUtil;
import android.sysprop.TelephonyProperties;
import android.telecom.PhoneAccount;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.util.SparseIntArray;
import com.android.i18n.phonenumbers.NumberParseException;
import com.android.i18n.phonenumbers.PhoneNumberUtil;
import com.android.i18n.phonenumbers.Phonenumber;
import com.android.internal.R;
import com.android.internal.content.NativeLibraryHelper;
import com.android.internal.midi.MidiConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public class PhoneNumberUtils {
    private static final String BCD_CALLED_PARTY_EXTENDED = "*#abc";
    private static final String BCD_EF_ADN_EXTENDED = "*#,N;";
    public static final int BCD_EXTENDED_TYPE_CALLED_PARTY = 2;
    public static final int BCD_EXTENDED_TYPE_EF_ADN = 1;
    private static final int CCC_LENGTH;
    private static final String CLIR_OFF = "#31#";
    private static final String CLIR_ON = "*31#";
    private static final boolean[] COUNTRY_CALLING_CALL;
    private static final boolean DBG = false;
    public static final int FORMAT_JAPAN = 2;
    public static final int FORMAT_NANP = 1;
    public static final int FORMAT_UNKNOWN = 0;
    private static final String JAPAN_ISO_COUNTRY_CODE = "JP";
    private static final SparseIntArray KEYPAD_MAP;
    private static final String KOREA_ISO_COUNTRY_CODE = "KR";
    static final String LOG_TAG = "PhoneNumberUtils";
    private static final String NANP_IDP_STRING = "011";
    private static final int NANP_LENGTH = 10;
    private static final int NANP_STATE_DASH = 4;
    private static final int NANP_STATE_DIGIT = 1;
    private static final int NANP_STATE_ONE = 3;
    private static final int NANP_STATE_PLUS = 2;
    public static final char PAUSE = ',';
    private static final char PLUS_SIGN_CHAR = '+';
    private static final String PLUS_SIGN_STRING = "+";
    public static final int TOA_International = 145;
    public static final int TOA_Unknown = 129;
    public static final char WAIT = ';';
    public static final char WILD = 'N';
    private static String[] sConvertToEmergencyMap;
    private static final Pattern GLOBAL_PHONE_NUMBER_PATTERN = Pattern.compile("[\\+]?[0-9.-]+");
    private static int sMinMatch = 0;
    private static final String[] NANP_COUNTRIES = {"US", "CA", "AS", "AI", "AG", "BS", "BB", "BM", "VG", "KY", "DM", "DO", "GD", "GU", "JM", "PR", "MS", "MP", "KN", "LC", "VC", "TT", "TC", "VI"};

    @Retention(RetentionPolicy.SOURCE)
    public @interface BcdExtendType {
    }

    public static final boolean is12Key(char c) {
        return (c >= '0' && c <= '9') || c == '*' || c == '#';
    }

    public static final boolean isDialable(char c) {
        return (c >= '0' && c <= '9') || c == '*' || c == '#' || c == '+' || c == 'N';
    }

    public static boolean isISODigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static final boolean isNonSeparator(char c) {
        return (c >= '0' && c <= '9') || c == '*' || c == '#' || c == '+' || c == 'N' || c == ';' || c == ',';
    }

    private static boolean isPause(char c) {
        return c == 'p' || c == 'P';
    }

    public static final boolean isReallyDialable(char c) {
        return (c >= '0' && c <= '9') || c == '*' || c == '#' || c == '+';
    }

    public static final boolean isStartsPostDial(char c) {
        return c == ',' || c == ';';
    }

    private static boolean isToneWait(char c) {
        return c == 'w' || c == 'W';
    }

    private static boolean isTwoToNine(char c) {
        return c >= '2' && c <= '9';
    }

    private static int minPositive(int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            return i < i2 ? i : i2;
        }
        if (i >= 0) {
            return i;
        }
        if (i2 >= 0) {
            return i2;
        }
        return -1;
    }

    private static int tryGetISODigit(char c) {
        if ('0' > c || c > '9') {
            return -1;
        }
        return c - '0';
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        KEYPAD_MAP = sparseIntArray;
        sparseIntArray.put(97, 50);
        sparseIntArray.put(98, 50);
        sparseIntArray.put(99, 50);
        sparseIntArray.put(65, 50);
        sparseIntArray.put(66, 50);
        sparseIntArray.put(67, 50);
        sparseIntArray.put(100, 51);
        sparseIntArray.put(101, 51);
        sparseIntArray.put(102, 51);
        sparseIntArray.put(68, 51);
        sparseIntArray.put(69, 51);
        sparseIntArray.put(70, 51);
        sparseIntArray.put(103, 52);
        sparseIntArray.put(104, 52);
        sparseIntArray.put(105, 52);
        sparseIntArray.put(71, 52);
        sparseIntArray.put(72, 52);
        sparseIntArray.put(73, 52);
        sparseIntArray.put(106, 53);
        sparseIntArray.put(107, 53);
        sparseIntArray.put(108, 53);
        sparseIntArray.put(74, 53);
        sparseIntArray.put(75, 53);
        sparseIntArray.put(76, 53);
        sparseIntArray.put(109, 54);
        sparseIntArray.put(110, 54);
        sparseIntArray.put(111, 54);
        sparseIntArray.put(77, 54);
        sparseIntArray.put(78, 54);
        sparseIntArray.put(79, 54);
        sparseIntArray.put(112, 55);
        sparseIntArray.put(113, 55);
        sparseIntArray.put(114, 55);
        sparseIntArray.put(115, 55);
        sparseIntArray.put(80, 55);
        sparseIntArray.put(81, 55);
        sparseIntArray.put(82, 55);
        sparseIntArray.put(83, 55);
        sparseIntArray.put(116, 56);
        sparseIntArray.put(117, 56);
        sparseIntArray.put(118, 56);
        sparseIntArray.put(84, 56);
        sparseIntArray.put(85, 56);
        sparseIntArray.put(86, 56);
        sparseIntArray.put(119, 57);
        sparseIntArray.put(120, 57);
        sparseIntArray.put(121, 57);
        sparseIntArray.put(122, 57);
        sparseIntArray.put(87, 57);
        sparseIntArray.put(88, 57);
        sparseIntArray.put(89, 57);
        sparseIntArray.put(90, 57);
        boolean[] zArr = {true, true, false, false, false, false, false, true, false, false, false, false, false, false, false, false, false, false, false, false, true, false, false, false, false, false, false, true, true, false, true, true, true, true, true, false, true, false, false, true, true, false, false, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, true, false, true, true, true, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, true, false, false, true, true, true, true, true, true, true, false, false, true, false};
        COUNTRY_CALLING_CALL = zArr;
        CCC_LENGTH = zArr.length;
        sConvertToEmergencyMap = null;
    }

    private static int getMinMatch() {
        if (sMinMatch == 0) {
            sMinMatch = Resources.getSystem().getInteger(R.integer.config_phonenumber_compare_min_match);
        }
        return sMinMatch;
    }

    public static int getMinMatchForTest() {
        return getMinMatch();
    }

    public static void setMinMatchForTest(int i) {
        sMinMatch = i;
    }

    private static boolean isSeparator(char c) {
        return !isDialable(c) && ('a' > c || c > 'z') && ('A' > c || c > 'Z');
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getNumberFromIntent(android.content.Intent r7, android.content.Context r8) throws java.lang.Throwable {
        /*
            android.net.Uri r1 = r7.getData()
            r6 = 0
            if (r1 != 0) goto L8
            return r6
        L8:
            java.lang.String r0 = r1.getScheme()
            if (r0 != 0) goto Lf
            return r6
        Lf:
            java.lang.String r2 = "tel"
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L87
            java.lang.String r2 = "sip"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L23
            goto L87
        L23:
            if (r8 != 0) goto L26
            return r6
        L26:
            r7.resolveType(r8)
            java.lang.String r7 = r1.getAuthority()
            java.lang.String r0 = "contacts"
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L39
            java.lang.String r7 = "number"
            goto L45
        L39:
            java.lang.String r0 = "com.android.contacts"
            boolean r7 = r0.equals(r7)
            if (r7 == 0) goto L44
            java.lang.String r7 = "data1"
            goto L45
        L44:
            r7 = r6
        L45:
            android.content.ContentResolver r0 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L70 java.lang.RuntimeException -> L72
            r8 = 1
            java.lang.String[] r2 = new java.lang.String[r8]     // Catch: java.lang.Throwable -> L70 java.lang.RuntimeException -> L72
            r8 = 0
            r2[r8] = r7     // Catch: java.lang.Throwable -> L70 java.lang.RuntimeException -> L72
            r3 = 0
            r4 = 0
            r5 = 0
            android.database.Cursor r8 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L70 java.lang.RuntimeException -> L72
            if (r8 == 0) goto L6a
            boolean r0 = r8.moveToFirst()     // Catch: java.lang.RuntimeException -> L68 java.lang.Throwable -> L7f
            if (r0 == 0) goto L6a
            int r7 = r8.getColumnIndex(r7)     // Catch: java.lang.RuntimeException -> L68 java.lang.Throwable -> L7f
            java.lang.String r7 = r8.getString(r7)     // Catch: java.lang.RuntimeException -> L68 java.lang.Throwable -> L7f
            r6 = r7
            goto L6a
        L68:
            r7 = move-exception
            goto L74
        L6a:
            if (r8 == 0) goto L7e
        L6c:
            r8.close()
            goto L7e
        L70:
            r7 = move-exception
            goto L81
        L72:
            r7 = move-exception
            r8 = r6
        L74:
            java.lang.String r0 = "PhoneNumberUtils"
            java.lang.String r1 = "Error getting phone number."
            com.android.telephony.Rlog.e(r0, r1, r7)     // Catch: java.lang.Throwable -> L7f
            if (r8 == 0) goto L7e
            goto L6c
        L7e:
            return r6
        L7f:
            r7 = move-exception
            r6 = r8
        L81:
            if (r6 == 0) goto L86
            r6.close()
        L86:
            throw r7
        L87:
            java.lang.String r7 = r1.getSchemeSpecificPart()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.PhoneNumberUtils.getNumberFromIntent(android.content.Intent, android.content.Context):java.lang.String");
    }

    public static String extractNetworkPortion(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            int iDigit = Character.digit(cCharAt, 10);
            if (iDigit == -1) {
                if (cCharAt == '+') {
                    String string = sb.toString();
                    if (string.length() == 0 || string.equals(CLIR_ON) || string.equals(CLIR_OFF)) {
                        sb.append(cCharAt);
                    }
                } else if (isDialable(cCharAt)) {
                    sb.append(cCharAt);
                } else if (isStartsPostDial(cCharAt)) {
                    break;
                }
            } else {
                sb.append(iDigit);
            }
        }
        return sb.toString();
    }

    public static String extractNetworkPortionAlt(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '+') {
                if (z) {
                    continue;
                } else {
                    z = true;
                }
            }
            if (isDialable(cCharAt)) {
                sb.append(cCharAt);
            } else if (isStartsPostDial(cCharAt)) {
                break;
            }
        }
        return sb.toString();
    }

    public static String stripSeparators(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            int iDigit = Character.digit(cCharAt, 10);
            if (iDigit != -1) {
                sb.append(iDigit);
            } else if (isNonSeparator(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static String convertAndStrip(String str) {
        return stripSeparators(convertKeypadLettersToDigits(str));
    }

    public static String convertPreDial(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (isPause(cCharAt)) {
                cCharAt = ',';
            } else if (isToneWait(cCharAt)) {
                cCharAt = ';';
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    private static void log(String str) {
        com.android.telephony.Rlog.d(LOG_TAG, str);
    }

    private static int indexOfLastNetworkChar(String str) {
        int length = str.length();
        int iMinPositive = minPositive(str.indexOf(44), str.indexOf(59));
        return iMinPositive < 0 ? length - 1 : iMinPositive - 1;
    }

    public static String extractPostDialPortion(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int iIndexOfLastNetworkChar = indexOfLastNetworkChar(str) + 1; iIndexOfLastNetworkChar < length; iIndexOfLastNetworkChar++) {
            char cCharAt = str.charAt(iIndexOfLastNetworkChar);
            if (isNonSeparator(cCharAt)) {
                sb.append(cCharAt);
            }
        }
        return sb.toString();
    }

    public static boolean compare(String str, String str2) {
        return compare(str, str2, false);
    }

    public static boolean compare(Context context, String str, String str2) {
        return compare(str, str2, context.getResources().getBoolean(R.bool.config_use_strict_phone_number_comparation));
    }

    public static boolean compare(String str, String str2, boolean z) {
        return z ? compareStrictly(str, str2) : compareLoosely(str, str2);
    }

    public static boolean compareLoosely(String str, String str2) {
        boolean z;
        int minMatch = getMinMatch();
        if (str == null || str2 == null) {
            return str == str2;
        }
        if (str.length() != 0 && str2.length() != 0) {
            int iIndexOfLastNetworkChar = indexOfLastNetworkChar(str);
            int iIndexOfLastNetworkChar2 = indexOfLastNetworkChar(str2);
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (iIndexOfLastNetworkChar >= 0 && iIndexOfLastNetworkChar2 >= 0) {
                char cCharAt = str.charAt(iIndexOfLastNetworkChar);
                if (isDialable(cCharAt)) {
                    z = false;
                } else {
                    iIndexOfLastNetworkChar--;
                    i++;
                    z = true;
                }
                char cCharAt2 = str2.charAt(iIndexOfLastNetworkChar2);
                if (!isDialable(cCharAt2)) {
                    iIndexOfLastNetworkChar2--;
                    i2++;
                    z = true;
                }
                if (!z) {
                    if (cCharAt2 != cCharAt && cCharAt != 'N' && cCharAt2 != 'N') {
                        break;
                    }
                    iIndexOfLastNetworkChar--;
                    iIndexOfLastNetworkChar2--;
                    i3++;
                }
            }
            if (i3 < minMatch) {
                int length = str.length() - i;
                return length == str2.length() - i2 && length == i3;
            }
            if (i3 >= minMatch && (iIndexOfLastNetworkChar < 0 || iIndexOfLastNetworkChar2 < 0)) {
                return true;
            }
            int i4 = iIndexOfLastNetworkChar + 1;
            if (matchIntlPrefix(str, i4) && matchIntlPrefix(str2, iIndexOfLastNetworkChar2 + 1)) {
                return true;
            }
            if (matchTrunkPrefix(str, i4) && matchIntlPrefixAndCC(str2, iIndexOfLastNetworkChar2 + 1)) {
                return true;
            }
            if (matchTrunkPrefix(str2, iIndexOfLastNetworkChar2 + 1) && matchIntlPrefixAndCC(str, i4)) {
                return true;
            }
        }
        return false;
    }

    public static boolean compareStrictly(String str, String str2) {
        return compareStrictly(str, str2, true);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:36:0x006f
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:226)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:196)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:63)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    public static boolean compareStrictly(java.lang.String r17, java.lang.String r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.telephony.PhoneNumberUtils.compareStrictly(java.lang.String, java.lang.String, boolean):boolean");
    }

    public static String toCallerIDMinMatch(String str) {
        return internalGetStrippedReversed(extractNetworkPortionAlt(str), getMinMatch());
    }

    public static String getStrippedReversed(String str) {
        String strExtractNetworkPortionAlt = extractNetworkPortionAlt(str);
        if (strExtractNetworkPortionAlt == null) {
            return null;
        }
        return internalGetStrippedReversed(strExtractNetworkPortionAlt, strExtractNetworkPortionAlt.length());
    }

    private static String internalGetStrippedReversed(String str, int i) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(i);
        int length = str.length();
        for (int i2 = length - 1; i2 >= 0 && length - i2 <= i; i2--) {
            sb.append(str.charAt(i2));
        }
        return sb.toString();
    }

    public static String stringFromStringAndTOA(String str, int i) {
        if (str == null) {
            return null;
        }
        return (i != 145 || str.length() <= 0 || str.charAt(0) == '+') ? str : PLUS_SIGN_STRING + str;
    }

    public static int toaFromString(String str) {
        return (str == null || str.length() <= 0 || str.charAt(0) != '+') ? 129 : 145;
    }

    @Deprecated
    public static String calledPartyBCDToString(byte[] bArr, int i, int i2) {
        return calledPartyBCDToString(bArr, i, i2, 1);
    }

    public static String calledPartyBCDToString(byte[] bArr, int i, int i2, int i3) {
        StringBuilder sb;
        StringBuilder sb2 = new StringBuilder((i2 * 2) + 1);
        if (i2 < 2) {
            return "";
        }
        boolean z = (bArr[i] & 240) == 144;
        internalCalledPartyBCDFragmentToString(sb2, bArr, i + 1, i2 - 1, i3);
        if (z && sb2.length() == 0) {
            return "";
        }
        if (z) {
            String string = sb2.toString();
            Matcher matcher = Pattern.compile("(^[#*])(.*)([#*])(.*)(#)$").matcher(string);
            if (matcher.matches()) {
                if ("".equals(matcher.group(2))) {
                    sb = new StringBuilder();
                    sb.append(matcher.group(1));
                    sb.append(matcher.group(3));
                    sb.append(matcher.group(4));
                    sb.append(matcher.group(5));
                    sb.append(PLUS_SIGN_STRING);
                } else {
                    sb = new StringBuilder();
                    sb.append(matcher.group(1));
                    sb.append(matcher.group(2));
                    sb.append(matcher.group(3));
                    sb.append(PLUS_SIGN_STRING);
                    sb.append(matcher.group(4));
                    sb.append(matcher.group(5));
                }
            } else {
                Matcher matcher2 = Pattern.compile("(^[#*])(.*)([#*])(.*)").matcher(string);
                if (matcher2.matches()) {
                    sb = new StringBuilder();
                    sb.append(matcher2.group(1));
                    sb.append(matcher2.group(2));
                    sb.append(matcher2.group(3));
                    sb.append(PLUS_SIGN_STRING);
                    sb.append(matcher2.group(4));
                } else {
                    sb2 = new StringBuilder();
                    sb2.append(PLUS_SIGN_CHAR);
                    sb2.append(string);
                }
            }
            sb2 = sb;
        }
        return sb2.toString();
    }

    private static void internalCalledPartyBCDFragmentToString(StringBuilder sb, byte[] bArr, int i, int i2, int i3) {
        char cBcdToChar;
        char cBcdToChar2;
        int i4 = i;
        while (true) {
            int i5 = i2 + i;
            if (i4 >= i5 || (cBcdToChar = bcdToChar((byte) (bArr[i4] & MidiConstants.STATUS_CHANNEL_MASK), i3)) == 0) {
                return;
            }
            sb.append(cBcdToChar);
            byte b = (byte) ((bArr[i4] >> 4) & 15);
            if ((b == 15 && i4 + 1 == i5) || (cBcdToChar2 = bcdToChar(b, i3)) == 0) {
                return;
            }
            sb.append(cBcdToChar2);
            i4++;
        }
    }

    @Deprecated
    public static String calledPartyBCDFragmentToString(byte[] bArr, int i, int i2) {
        return calledPartyBCDFragmentToString(bArr, i, i2, 1);
    }

    public static String calledPartyBCDFragmentToString(byte[] bArr, int i, int i2, int i3) {
        StringBuilder sb = new StringBuilder(i2 * 2);
        internalCalledPartyBCDFragmentToString(sb, bArr, i, i2, i3);
        return sb.toString();
    }

    private static char bcdToChar(byte b, int i) {
        int i2;
        if (b < 10) {
            return (char) (b + 48);
        }
        String str = null;
        if (1 == i) {
            str = BCD_EF_ADN_EXTENDED;
        } else if (2 == i) {
            str = BCD_CALLED_PARTY_EXTENDED;
        }
        if (str == null || (i2 = b - 10) >= str.length()) {
            return (char) 0;
        }
        return str.charAt(i2);
    }

    private static int charToBCD(char c, int i) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        String str = null;
        if (1 == i) {
            str = BCD_EF_ADN_EXTENDED;
        } else if (2 == i) {
            str = BCD_CALLED_PARTY_EXTENDED;
        }
        if (str == null || str.indexOf(c) == -1) {
            throw new RuntimeException("invalid char for BCD " + c);
        }
        return str.indexOf(c) + 10;
    }

    public static boolean isWellFormedSmsAddress(String str) {
        String strExtractNetworkPortion = extractNetworkPortion(str);
        return (strExtractNetworkPortion.equals(PLUS_SIGN_STRING) || TextUtils.isEmpty(strExtractNetworkPortion) || !isDialable(strExtractNetworkPortion)) ? false : true;
    }

    public static boolean isGlobalPhoneNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return GLOBAL_PHONE_NUMBER_PATTERN.matcher(str).matches();
    }

    private static boolean isDialable(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isDialable(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNonSeparator(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isNonSeparator(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static byte[] networkPortionToCalledPartyBCD(String str) {
        return numberToCalledPartyBCDHelper(extractNetworkPortion(str), false, 1);
    }

    public static byte[] networkPortionToCalledPartyBCDWithLength(String str) {
        return numberToCalledPartyBCDHelper(extractNetworkPortion(str), true, 1);
    }

    @Deprecated
    public static byte[] numberToCalledPartyBCD(String str) {
        return numberToCalledPartyBCD(str, 1);
    }

    public static byte[] numberToCalledPartyBCD(String str, int i) {
        return numberToCalledPartyBCDHelper(str, false, i);
    }

    private static byte[] numberToCalledPartyBCDHelper(String str, boolean z, int i) {
        int length = str.length();
        char c = 0;
        boolean z2 = str.indexOf(43) != -1;
        int i2 = z2 ? length - 1 : length;
        if (i2 == 0) {
            return null;
        }
        int i3 = (i2 + 1) / 2;
        int i4 = z ? 2 : 1;
        int i5 = i3 + i4;
        byte[] bArr = new byte[i5];
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if (cCharAt != '+') {
                int i8 = (i6 >> 1) + i4;
                bArr[i8] = (byte) (((byte) ((charToBCD(cCharAt, i) & 15) << ((i6 & 1) == 1 ? 4 : 0))) | bArr[i8]);
                i6++;
            }
        }
        if ((i6 & 1) == 1) {
            int i9 = i4 + (i6 >> 1);
            bArr[i9] = (byte) (bArr[i9] | 240);
        }
        if (z) {
            bArr[0] = (byte) (i5 - 1);
            c = 1;
        }
        bArr[c] = (byte) (z2 ? 145 : 129);
        return bArr;
    }

    @Deprecated
    public static String formatNumber(String str) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        formatNumber(spannableStringBuilder, getFormatTypeForLocale(Locale.getDefault()));
        return spannableStringBuilder.toString();
    }

    @Deprecated
    public static String formatNumber(String str, int i) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        formatNumber(spannableStringBuilder, i);
        return spannableStringBuilder.toString();
    }

    @Deprecated
    public static int getFormatTypeForLocale(Locale locale) {
        return getFormatTypeFromCountryCode(locale.getCountry());
    }

    @Deprecated
    public static void formatNumber(Editable editable, int i) {
        if (editable.length() > 2 && editable.charAt(0) == '+') {
            if (editable.charAt(1) == '1') {
                i = 1;
            } else {
                i = (editable.length() >= 3 && editable.charAt(1) == '8' && editable.charAt(2) == '1') ? 2 : 0;
            }
        }
        if (i == 0) {
            removeDashes(editable);
        } else if (i == 1) {
            formatNanpNumber(editable);
        } else {
            if (i != 2) {
                return;
            }
            formatJapaneseNumber(editable);
        }
    }

    @Deprecated
    public static void formatNanpNumber(Editable editable) {
        int i;
        int length = editable.length();
        if (length <= 15 && length > 5) {
            CharSequence charSequenceSubSequence = editable.subSequence(0, length);
            removeDashes(editable);
            int length2 = editable.length();
            int[] iArr = new int[3];
            int i2 = 0;
            int i3 = 0;
            char c = 1;
            for (int i4 = 0; i4 < length2; i4++) {
                char cCharAt = editable.charAt(i4);
                if (cCharAt != '+') {
                    if (cCharAt != '-') {
                        switch (cCharAt) {
                            case '1':
                                if (i2 == 0 || c == 2) {
                                    c = 3;
                                    break;
                                }
                            case '0':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                if (c == 2) {
                                    editable.replace(0, length2, charSequenceSubSequence);
                                } else {
                                    if (c != 3) {
                                        if (c != 4 && (i2 == 3 || i2 == 6)) {
                                            i = i3 + 1;
                                            iArr[i3] = i4;
                                        }
                                        i2++;
                                        c = 1;
                                    } else {
                                        i = i3 + 1;
                                        iArr[i3] = i4;
                                    }
                                    i3 = i;
                                    i2++;
                                    c = 1;
                                }
                                break;
                            default:
                                editable.replace(0, length2, charSequenceSubSequence);
                                break;
                        }
                        return;
                    }
                    c = 4;
                } else {
                    if (i4 != 0) {
                        editable.replace(0, length2, charSequenceSubSequence);
                        return;
                    }
                    c = 2;
                }
            }
            if (i2 == 7) {
                i3--;
            }
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = iArr[i5] + i5;
                editable.replace(i6, i6, NativeLibraryHelper.CLEAR_ABI_OVERRIDE);
            }
            for (int length3 = editable.length(); length3 > 0; length3--) {
                int i7 = length3 - 1;
                if (editable.charAt(i7) != '-') {
                    return;
                }
                editable.delete(i7, length3);
            }
        }
    }

    @Deprecated
    public static void formatJapaneseNumber(Editable editable) {
        JapanesePhoneNumberFormatter.format(editable);
    }

    private static void removeDashes(Editable editable) {
        int i = 0;
        while (i < editable.length()) {
            if (editable.charAt(i) == '-') {
                editable.delete(i, i + 1);
            } else {
                i++;
            }
        }
    }

    public static String formatNumberToE164(String str, String str2) {
        return formatNumberInternal(str, str2, PhoneNumberUtil.PhoneNumberFormat.E164);
    }

    public static String formatNumberToRFC3966(String str, String str2) {
        return formatNumberInternal(str, str2, PhoneNumberUtil.PhoneNumberFormat.RFC3966);
    }

    private static String formatNumberInternal(String str, String str2, PhoneNumberUtil.PhoneNumberFormat phoneNumberFormat) {
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(str, str2);
            if (phoneNumberUtil.isValidNumber(phoneNumber)) {
                return phoneNumberUtil.format(phoneNumber, phoneNumberFormat);
            }
            return null;
        } catch (NumberParseException unused) {
            return null;
        }
    }

    public static boolean isInternationalNumber(String str, String str2) {
        if (TextUtils.isEmpty(str) || str.startsWith("#") || str.startsWith("*")) {
            return false;
        }
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            return phoneNumberUtil.parseAndKeepRawInput(str, str2).getCountryCode() != phoneNumberUtil.getCountryCodeForRegion(str2);
        } catch (NumberParseException unused) {
            return false;
        }
    }

    public static String formatNumber(String str, String str2) {
        String inOriginalFormat;
        if (str.startsWith("#") || str.startsWith("*")) {
            return str;
        }
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        try {
            Phonenumber.PhoneNumber andKeepRawInput = phoneNumberUtil.parseAndKeepRawInput(str, str2);
            if (KOREA_ISO_COUNTRY_CODE.equalsIgnoreCase(str2) && andKeepRawInput.getCountryCode() == phoneNumberUtil.getCountryCodeForRegion(KOREA_ISO_COUNTRY_CODE) && andKeepRawInput.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN) {
                inOriginalFormat = phoneNumberUtil.format(andKeepRawInput, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
            } else if (JAPAN_ISO_COUNTRY_CODE.equalsIgnoreCase(str2) && andKeepRawInput.getCountryCode() == phoneNumberUtil.getCountryCodeForRegion(JAPAN_ISO_COUNTRY_CODE) && andKeepRawInput.getCountryCodeSource() == Phonenumber.PhoneNumber.CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN) {
                inOriginalFormat = phoneNumberUtil.format(andKeepRawInput, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
            } else {
                inOriginalFormat = phoneNumberUtil.formatInOriginalFormat(andKeepRawInput, str2);
            }
            return inOriginalFormat;
        } catch (NumberParseException unused) {
            return null;
        }
    }

    public static String formatNumber(String str, String str2, String str3) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!isDialable(str.charAt(i))) {
                return str;
            }
        }
        PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        if (str2 != null && str2.length() >= 2 && str2.charAt(0) == '+') {
            try {
                String regionCodeForNumber = phoneNumberUtil.getRegionCodeForNumber(phoneNumberUtil.parse(str2, "ZZ"));
                if (!TextUtils.isEmpty(regionCodeForNumber)) {
                    if (normalizeNumber(str).indexOf(str2.substring(1)) <= 0) {
                        str3 = regionCodeForNumber;
                    }
                }
            } catch (NumberParseException unused) {
            }
        }
        String number = formatNumber(str, str3);
        return number != null ? number : str;
    }

    public static String normalizeNumber(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            int iDigit = Character.digit(cCharAt, 10);
            if (iDigit != -1) {
                sb.append(iDigit);
            } else if (sb.length() == 0 && cCharAt == '+') {
                sb.append(cCharAt);
            } else if ((cCharAt >= 'a' && cCharAt <= 'z') || (cCharAt >= 'A' && cCharAt <= 'Z')) {
                return normalizeNumber(convertKeypadLettersToDigits(str));
            }
        }
        return sb.toString();
    }

    public static String replaceUnicodeDigits(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (char c : str.toCharArray()) {
            int iDigit = Character.digit(c, 10);
            if (iDigit != -1) {
                sb.append(iDigit);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @Deprecated
    public static boolean isEmergencyNumber(String str) {
        return isEmergencyNumber(getDefaultVoiceSubId(), str);
    }

    @Deprecated
    public static boolean isEmergencyNumber(int i, String str) {
        return isEmergencyNumberInternal(i, str, true);
    }

    @Deprecated
    public static boolean isPotentialEmergencyNumber(String str) {
        return isPotentialEmergencyNumber(getDefaultVoiceSubId(), str);
    }

    @Deprecated
    public static boolean isPotentialEmergencyNumber(int i, String str) {
        return isEmergencyNumberInternal(i, str, false);
    }

    private static boolean isEmergencyNumberInternal(String str, boolean z) {
        return isEmergencyNumberInternal(getDefaultVoiceSubId(), str, z);
    }

    private static boolean isEmergencyNumberInternal(int i, String str, boolean z) {
        return isEmergencyNumberInternal(i, str, null, z);
    }

    @Deprecated
    public static boolean isEmergencyNumber(String str, String str2) {
        return isEmergencyNumber(getDefaultVoiceSubId(), str, str2);
    }

    @Deprecated
    public static boolean isEmergencyNumber(int i, String str, String str2) {
        return isEmergencyNumberInternal(i, str, str2, true);
    }

    @Deprecated
    public static boolean isPotentialEmergencyNumber(String str, String str2) {
        return isPotentialEmergencyNumber(getDefaultVoiceSubId(), str, str2);
    }

    @Deprecated
    public static boolean isPotentialEmergencyNumber(int i, String str, String str2) {
        return isEmergencyNumberInternal(i, str, str2, false);
    }

    private static boolean isEmergencyNumberInternal(String str, String str2, boolean z) {
        return isEmergencyNumberInternal(getDefaultVoiceSubId(), str, str2, z);
    }

    private static boolean isEmergencyNumberInternal(int i, String str, String str2, boolean z) {
        try {
            if (z) {
                return TelephonyManager.getDefault().isEmergencyNumber(str);
            }
            return TelephonyManager.getDefault().isPotentialEmergencyNumber(str);
        } catch (RuntimeException e) {
            com.android.telephony.Rlog.e(LOG_TAG, "isEmergencyNumberInternal: RuntimeException: " + e);
            return false;
        }
    }

    @Deprecated
    public static boolean isLocalEmergencyNumber(Context context, String str) {
        return isLocalEmergencyNumber(context, getDefaultVoiceSubId(), str);
    }

    @Deprecated
    public static boolean isLocalEmergencyNumber(Context context, int i, String str) {
        return isLocalEmergencyNumberInternal(i, str, context, true);
    }

    @Deprecated
    public static boolean isPotentialLocalEmergencyNumber(Context context, String str) {
        return isPotentialLocalEmergencyNumber(context, getDefaultVoiceSubId(), str);
    }

    @Deprecated
    public static boolean isPotentialLocalEmergencyNumber(Context context, int i, String str) {
        return isLocalEmergencyNumberInternal(i, str, context, false);
    }

    private static boolean isLocalEmergencyNumberInternal(String str, Context context, boolean z) {
        return isLocalEmergencyNumberInternal(getDefaultVoiceSubId(), str, context, z);
    }

    private static boolean isLocalEmergencyNumberInternal(int i, String str, Context context, boolean z) {
        return isEmergencyNumberInternal(i, str, null, z);
    }

    public static boolean isVoiceMailNumber(String str) {
        return isVoiceMailNumber(SubscriptionManager.getDefaultSubscriptionId(), str);
    }

    public static boolean isVoiceMailNumber(int i, String str) {
        return isVoiceMailNumber(null, i, str);
    }

    @SystemApi
    public static boolean isVoiceMailNumber(Context context, int i, String str) {
        TelephonyManager telephonyManagerFrom;
        CarrierConfigManager carrierConfigManager;
        PersistableBundle configForSubId;
        try {
            if (context == null) {
                telephonyManagerFrom = TelephonyManager.getDefault();
            } else {
                telephonyManagerFrom = TelephonyManager.from(context);
            }
            String voiceMailNumber = telephonyManagerFrom.getVoiceMailNumber(i);
            String line1Number = telephonyManagerFrom.getLine1Number(i);
            String strExtractNetworkPortionAlt = extractNetworkPortionAlt(str);
            if (TextUtils.isEmpty(strExtractNetworkPortionAlt)) {
                return false;
            }
            if ((context == null || (carrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config")) == null || (configForSubId = carrierConfigManager.getConfigForSubId(i)) == null) ? false : configForSubId.getBoolean(CarrierConfigManager.KEY_MDN_IS_ADDITIONAL_VOICEMAIL_NUMBER_BOOL)) {
                return compare(strExtractNetworkPortionAlt, voiceMailNumber) || compare(strExtractNetworkPortionAlt, line1Number);
            }
            return compare(strExtractNetworkPortionAlt, voiceMailNumber);
        } catch (SecurityException unused) {
            return false;
        }
    }

    public static String convertKeypadLettersToDigits(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return str;
        }
        char[] charArray = str.toCharArray();
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            charArray[i] = (char) KEYPAD_MAP.get(c, c);
        }
        return new String(charArray);
    }

    public static String cdmaCheckAndProcessPlusCode(String str) {
        if (TextUtils.isEmpty(str) || !isReallyDialable(str.charAt(0)) || !isNonSeparator(str)) {
            return str;
        }
        String networkCountryIso = TelephonyManager.getDefault().getNetworkCountryIso();
        String simCountryIso = TelephonyManager.getDefault().getSimCountryIso();
        return (TextUtils.isEmpty(networkCountryIso) || TextUtils.isEmpty(simCountryIso)) ? str : cdmaCheckAndProcessPlusCodeByNumberFormat(str, getFormatTypeFromCountryCode(networkCountryIso), getFormatTypeFromCountryCode(simCountryIso));
    }

    public static String cdmaCheckAndProcessPlusCodeForSms(String str) {
        if (TextUtils.isEmpty(str) || !isReallyDialable(str.charAt(0)) || !isNonSeparator(str)) {
            return str;
        }
        String simCountryIso = TelephonyManager.getDefault().getSimCountryIso();
        if (TextUtils.isEmpty(simCountryIso)) {
            return str;
        }
        int formatTypeFromCountryCode = getFormatTypeFromCountryCode(simCountryIso);
        return cdmaCheckAndProcessPlusCodeByNumberFormat(str, formatTypeFromCountryCode, formatTypeFromCountryCode);
    }

    public static String cdmaCheckAndProcessPlusCodeByNumberFormat(String str, int i, int i2) {
        String strExtractNetworkPortionAlt;
        String strProcessPlusCode;
        boolean z = i == i2 && i == 1;
        if (str == null || str.lastIndexOf(PLUS_SIGN_STRING) == -1) {
            return str;
        }
        String str2 = null;
        String strSubstring = str;
        while (true) {
            if (z) {
                strExtractNetworkPortionAlt = extractNetworkPortion(strSubstring);
            } else {
                strExtractNetworkPortionAlt = extractNetworkPortionAlt(strSubstring);
            }
            strProcessPlusCode = processPlusCode(strExtractNetworkPortionAlt, z);
            if (TextUtils.isEmpty(strProcessPlusCode)) {
                com.android.telephony.Rlog.e("checkAndProcessPlusCode: null newDialStr", strProcessPlusCode);
                return str;
            }
            if (str2 != null) {
                strProcessPlusCode = str2.concat(strProcessPlusCode);
            }
            String strExtractPostDialPortion = extractPostDialPortion(strSubstring);
            if (!TextUtils.isEmpty(strExtractPostDialPortion)) {
                int iFindDialableIndexFromPostDialStr = findDialableIndexFromPostDialStr(strExtractPostDialPortion);
                if (iFindDialableIndexFromPostDialStr >= 1) {
                    strProcessPlusCode = appendPwCharBackToOrigDialStr(iFindDialableIndexFromPostDialStr, strProcessPlusCode, strExtractPostDialPortion);
                    strSubstring = strExtractPostDialPortion.substring(iFindDialableIndexFromPostDialStr);
                } else {
                    if (iFindDialableIndexFromPostDialStr < 0) {
                        strExtractPostDialPortion = "";
                    }
                    com.android.telephony.Rlog.e("wrong postDialStr=", strExtractPostDialPortion);
                }
            }
            if (TextUtils.isEmpty(strExtractPostDialPortion) || TextUtils.isEmpty(strSubstring)) {
                break;
            }
            str2 = strProcessPlusCode;
        }
        return strProcessPlusCode;
    }

    public static CharSequence createTtsSpannable(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        Spannable spannableNewSpannable = Spannable.Factory.getInstance().newSpannable(charSequence);
        addTtsSpan(spannableNewSpannable, 0, spannableNewSpannable.length());
        return spannableNewSpannable;
    }

    public static void addTtsSpan(Spannable spannable, int i, int i2) {
        spannable.setSpan(createTtsSpan(spannable.subSequence(i, i2).toString()), i, i2, 33);
    }

    @Deprecated
    public static CharSequence ttsSpanAsPhoneNumber(CharSequence charSequence) {
        return createTtsSpannable(charSequence);
    }

    @Deprecated
    public static void ttsSpanAsPhoneNumber(Spannable spannable, int i, int i2) {
        addTtsSpan(spannable, i, i2);
    }

    public static TtsSpan createTtsSpan(String str) {
        Phonenumber.PhoneNumber phoneNumber = null;
        if (str == null) {
            return null;
        }
        try {
            phoneNumber = PhoneNumberUtil.getInstance().parse(str, (String) null);
        } catch (NumberParseException unused) {
        }
        TtsSpan.TelephoneBuilder telephoneBuilder = new TtsSpan.TelephoneBuilder();
        if (phoneNumber == null) {
            telephoneBuilder.setNumberParts(splitAtNonNumerics(str));
        } else {
            if (phoneNumber.hasCountryCode()) {
                telephoneBuilder.setCountryCode(Integer.toString(phoneNumber.getCountryCode()));
            }
            telephoneBuilder.setNumberParts(Long.toString(phoneNumber.getNationalNumber()));
        }
        return telephoneBuilder.build();
    }

    private static String splitAtNonNumerics(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(charSequence.length());
        int i = 0;
        while (true) {
            Object objValueOf = " ";
            if (i < charSequence.length()) {
                if (is12Key(charSequence.charAt(i))) {
                    objValueOf = Character.valueOf(charSequence.charAt(i));
                }
                sb.append(objValueOf);
                i++;
            } else {
                return sb.toString().replaceAll(" +", " ").trim();
            }
        }
    }

    private static String getCurrentIdp(boolean z) {
        return z ? NANP_IDP_STRING : TelephonyProperties.operator_idp_string().orElse(PLUS_SIGN_STRING);
    }

    private static int getFormatTypeFromCountryCode(String str) {
        int length = NANP_COUNTRIES.length;
        for (int i = 0; i < length; i++) {
            if (NANP_COUNTRIES[i].compareToIgnoreCase(str) == 0) {
                return 1;
            }
        }
        return "jp".compareToIgnoreCase(str) == 0 ? 2 : 0;
    }

    public static boolean isNanp(String str) {
        if (str != null) {
            if (str.length() != 10 || !isTwoToNine(str.charAt(0)) || !isTwoToNine(str.charAt(3))) {
                return false;
            }
            for (int i = 1; i < 10; i++) {
                if (!isISODigit(str.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
        com.android.telephony.Rlog.e("isNanp: null dialStr passed in", str);
        return false;
    }

    private static boolean isOneNanp(String str) {
        if (str != null) {
            return str.charAt(0) == '1' && isNanp(str.substring(1));
        }
        com.android.telephony.Rlog.e("isOneNanp: null dialStr passed in", str);
        return false;
    }

    @SystemApi
    public static boolean isUriNumber(String str) {
        return str != null && (str.contains("@") || str.contains("%40"));
    }

    @SystemApi
    public static String getUsernameFromUriNumber(String str) {
        int iIndexOf = str.indexOf(64);
        if (iIndexOf < 0) {
            iIndexOf = str.indexOf("%40");
        }
        if (iIndexOf < 0) {
            com.android.telephony.Rlog.w(LOG_TAG, "getUsernameFromUriNumber: no delimiter found in SIP addr '" + str + "'");
            iIndexOf = str.length();
        }
        return str.substring(0, iIndexOf);
    }

    public static Uri convertSipUriToTelUri(Uri uri) {
        if (!"sip".equals(uri.getScheme())) {
            return uri;
        }
        String[] strArrSplit = uri.getSchemeSpecificPart().split("[@;:]");
        return strArrSplit.length == 0 ? uri : Uri.fromParts(PhoneAccount.SCHEME_TEL, strArrSplit[0], null);
    }

    private static String processPlusCode(String str, boolean z) {
        if (str == null || str.charAt(0) != '+' || str.length() <= 1) {
            return str;
        }
        String strSubstring = str.substring(1);
        return (z && isOneNanp(strSubstring)) ? strSubstring : str.replaceFirst("[+]", getCurrentIdp(z));
    }

    private static int findDialableIndexFromPostDialStr(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (isReallyDialable(str.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    private static String appendPwCharBackToOrigDialStr(int i, String str, String str2) {
        if (i == 1) {
            return str + str2.charAt(0);
        }
        return str.concat(str2.substring(0, i));
    }

    private static boolean matchIntlPrefix(String str, int i) {
        char c = 0;
        for (int i2 = 0; i2 < i; i2++) {
            char cCharAt = str.charAt(i2);
            if (c != 0) {
                if (c != 2) {
                    if (c != 4) {
                        if (isNonSeparator(cCharAt)) {
                            return false;
                        }
                    } else if (cCharAt == '1') {
                        c = 5;
                    } else if (isNonSeparator(cCharAt)) {
                        return false;
                    }
                } else if (cCharAt == '0') {
                    c = 3;
                } else if (cCharAt == '1') {
                    c = 4;
                } else if (isNonSeparator(cCharAt)) {
                    return false;
                }
            } else if (cCharAt == '+') {
                c = 1;
            } else if (cCharAt == '0') {
                c = 2;
            } else if (isNonSeparator(cCharAt)) {
                return false;
            }
        }
        return c == 1 || c == 3 || c == 5;
    }

    private static boolean matchIntlPrefixAndCC(String str, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            char cCharAt = str.charAt(i3);
            switch (i2) {
                case 0:
                    if (cCharAt == '+') {
                        i2 = 1;
                    } else if (cCharAt == '0') {
                        i2 = 2;
                    } else {
                        if (isNonSeparator(cCharAt)) {
                            return false;
                        }
                    }
                    break;
                case 1:
                case 3:
                case 5:
                    if (isISODigit(cCharAt)) {
                        i2 = 6;
                    } else {
                        if (isNonSeparator(cCharAt)) {
                            return false;
                        }
                    }
                    break;
                case 2:
                    if (cCharAt == '0') {
                        i2 = 3;
                    } else if (cCharAt == '1') {
                        i2 = 4;
                    } else {
                        if (isNonSeparator(cCharAt)) {
                            return false;
                        }
                    }
                    break;
                case 4:
                    if (cCharAt == '1') {
                        i2 = 5;
                    } else {
                        if (isNonSeparator(cCharAt)) {
                            return false;
                        }
                    }
                    break;
                case 6:
                case 7:
                    if (isISODigit(cCharAt)) {
                        i2++;
                    } else {
                        if (isNonSeparator(cCharAt)) {
                            return false;
                        }
                    }
                    break;
                default:
                    if (isNonSeparator(cCharAt)) {
                        return false;
                    }
                    break;
                    break;
            }
        }
        return i2 == 6 || i2 == 7 || i2 == 8;
    }

    private static boolean matchTrunkPrefix(String str, int i) {
        boolean z = false;
        for (int i2 = 0; i2 < i; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '0' && !z) {
                z = true;
            } else if (isNonSeparator(cCharAt)) {
                return false;
            }
        }
        return z;
    }

    private static boolean isCountryCallingCode(int i) {
        return i > 0 && i < CCC_LENGTH && COUNTRY_CALLING_CALL[i];
    }

    private static class CountryCallingCodeAndNewIndex {
        public final int countryCallingCode;
        public final int newIndex;

        public CountryCallingCodeAndNewIndex(int i, int i2) {
            this.countryCallingCode = i;
            this.newIndex = i2;
        }
    }

    private static CountryCallingCodeAndNewIndex tryGetCountryCallingCodeAndNewIndex(String str, boolean z) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            char cCharAt = str.charAt(i3);
            switch (i) {
                case 0:
                    if (cCharAt == '+') {
                        i = 1;
                    } else if (cCharAt == '0') {
                        i = 2;
                    } else if (cCharAt == '1') {
                        if (!z) {
                            return null;
                        }
                        i = 8;
                    } else {
                        if (isDialable(cCharAt)) {
                            return null;
                        }
                    }
                    break;
                case 1:
                case 3:
                case 5:
                case 6:
                case 7:
                    int iTryGetISODigit = tryGetISODigit(cCharAt);
                    if (iTryGetISODigit > 0) {
                        i2 = (i2 * 10) + iTryGetISODigit;
                        if (i2 >= 100 || isCountryCallingCode(i2)) {
                            return new CountryCallingCodeAndNewIndex(i2, i3 + 1);
                        }
                        i = (i != 1 && i != 3 && i != 5) ? i + 1 : 6;
                    } else {
                        if (isDialable(cCharAt)) {
                            return null;
                        }
                    }
                    break;
                case 2:
                    if (cCharAt == '0') {
                        i = 3;
                    } else if (cCharAt == '1') {
                        i = 4;
                    } else {
                        if (isDialable(cCharAt)) {
                            return null;
                        }
                    }
                    break;
                case 4:
                    if (cCharAt == '1') {
                        i = 5;
                    } else {
                        if (isDialable(cCharAt)) {
                            return null;
                        }
                    }
                    break;
                case 8:
                    if (cCharAt == '6') {
                        i = 9;
                    } else {
                        if (isDialable(cCharAt)) {
                            return null;
                        }
                    }
                    break;
                case 9:
                    if (cCharAt == '6') {
                        return new CountryCallingCodeAndNewIndex(66, i3 + 1);
                    }
                    return null;
                default:
                    return null;
            }
        }
        return null;
    }

    private static int tryGetTrunkPrefixOmittedIndex(String str, int i) {
        int length = str.length();
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (tryGetISODigit(cCharAt) >= 0) {
                return i + 1;
            }
            if (isDialable(cCharAt)) {
                return -1;
            }
            i++;
        }
        return -1;
    }

    private static boolean checkPrefixIsIgnorable(String str, int i, int i2) {
        boolean z = false;
        while (i2 >= i) {
            if (tryGetISODigit(str.charAt(i2)) >= 0) {
                if (z) {
                    return false;
                }
                z = true;
            } else if (isDialable(str.charAt(i2))) {
                return false;
            }
            i2--;
        }
        return true;
    }

    private static int getDefaultVoiceSubId() {
        return SubscriptionManager.getDefaultVoiceSubscriptionId();
    }

    public static String convertToEmergencyNumber(Context context, String str) {
        String[] strArrSplit;
        if (context != null && !TextUtils.isEmpty(str)) {
            String strNormalizeNumber = normalizeNumber(str);
            if (isEmergencyNumber(strNormalizeNumber)) {
                return str;
            }
            if (sConvertToEmergencyMap == null) {
                sConvertToEmergencyMap = context.getResources().getStringArray(R.array.config_convert_to_emergency_number_map);
            }
            String[] strArr = sConvertToEmergencyMap;
            if (strArr != null && strArr.length != 0) {
                for (String str2 : strArr) {
                    String str3 = null;
                    String[] strArrSplit2 = !TextUtils.isEmpty(str2) ? str2.split(SettingsStringUtil.DELIMITER) : null;
                    if (strArrSplit2 == null || strArrSplit2.length != 2) {
                        strArrSplit = null;
                    } else {
                        String str4 = strArrSplit2[1];
                        strArrSplit = TextUtils.isEmpty(strArrSplit2[0]) ? null : strArrSplit2[0].split(SmsManager.REGEX_PREFIX_DELIMITER);
                        str3 = str4;
                    }
                    if (!TextUtils.isEmpty(str3) && strArrSplit != null && strArrSplit.length != 0) {
                        for (String str5 : strArrSplit) {
                            if (!TextUtils.isEmpty(str5) && str5.equals(strNormalizeNumber)) {
                                return str3;
                            }
                        }
                    }
                }
            }
        }
        return str;
    }
}
