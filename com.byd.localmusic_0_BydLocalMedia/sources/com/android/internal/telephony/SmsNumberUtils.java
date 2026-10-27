package com.android.internal.telephony;

import android.app.blob.XmlTags;
import android.content.Context;
import android.media.AudioSystem;
import android.os.Binder;
import android.os.PersistableBundle;
import android.os.SystemProperties;
import android.security.keystore.KeyProperties;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.internal.telephony.util.TelephonyUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes4.dex */
public class SmsNumberUtils {
    private static int[] ALL_COUNTRY_CODES = null;
    private static final int CDMA_HOME_NETWORK = 1;
    private static final int CDMA_ROAMING_NETWORK = 2;
    private static final boolean DBG;
    private static final int GSM_UMTS_NETWORK = 0;
    private static HashMap<String, ArrayList<String>> IDDS_MAPS = null;
    private static int MAX_COUNTRY_CODES_LENGTH = 0;
    private static final int MIN_COUNTRY_AREA_LOCAL_LENGTH = 10;
    private static final int NANP_CC = 1;
    private static final String NANP_IDD = "011";
    private static final int NANP_LONG_LENGTH = 11;
    private static final int NANP_MEDIUM_LENGTH = 10;
    private static final String NANP_NDD = "1";
    private static final int NANP_SHORT_LENGTH = 7;
    private static final int NP_CC_AREA_LOCAL = 104;
    private static final int NP_HOMEIDD_CC_AREA_LOCAL = 101;
    private static final int NP_INTERNATIONAL_BEGIN = 100;
    private static final int NP_LOCALIDD_CC_AREA_LOCAL = 103;
    private static final int NP_NANP_AREA_LOCAL = 2;
    private static final int NP_NANP_BEGIN = 1;
    private static final int NP_NANP_LOCAL = 1;
    private static final int NP_NANP_LOCALIDD_CC_AREA_LOCAL = 5;
    private static final int NP_NANP_NBPCD_CC_AREA_LOCAL = 4;
    private static final int NP_NANP_NBPCD_HOMEIDD_CC_AREA_LOCAL = 6;
    private static final int NP_NANP_NDD_AREA_LOCAL = 3;
    private static final int NP_NBPCD_CC_AREA_LOCAL = 102;
    private static final int NP_NBPCD_HOMEIDD_CC_AREA_LOCAL = 100;
    private static final int NP_NONE = 0;
    private static final String PLUS_SIGN = "+";
    private static final String TAG = "SmsNumberUtils";

    private static String getNumberPlanType(int i) {
        return i == 1 ? "NP_NANP_LOCAL" : i == 2 ? "NP_NANP_AREA_LOCAL" : i == 3 ? "NP_NANP_NDD_AREA_LOCAL" : i == 4 ? "NP_NANP_NBPCD_CC_AREA_LOCAL" : i == 5 ? "NP_NANP_LOCALIDD_CC_AREA_LOCAL" : i == 6 ? "NP_NANP_NBPCD_HOMEIDD_CC_AREA_LOCAL" : i == 100 ? "NP_NBPCD_HOMEIDD_CC_AREA_LOCAL" : i == 101 ? "NP_HOMEIDD_CC_AREA_LOCAL" : i == 102 ? "NP_NBPCD_CC_AREA_LOCAL" : i == 103 ? "NP_LOCALIDD_CC_AREA_LOCAL" : i == 104 ? "NP_CC_AREA_LOCAL" : "Unknown type";
    }

    private static boolean isTwoToNine(char c) {
        return c >= '2' && c <= '9';
    }

    static {
        DBG = SystemProperties.getInt("ro.debuggable", 0) == 1;
        ALL_COUNTRY_CODES = null;
        IDDS_MAPS = new HashMap<>();
    }

    private static class NumberEntry {
        public String IDD;
        public int countryCode;
        public String number;

        public NumberEntry(String str) {
            this.number = str;
        }
    }

    private static String formatNumber(Context context, String str, String str2, int i) {
        if (str == null) {
            throw new IllegalArgumentException("number is null");
        }
        if (str2 == null || str2.trim().length() == 0) {
            throw new IllegalArgumentException("activeMcc is null or empty!");
        }
        String strExtractNetworkPortion = PhoneNumberUtils.extractNetworkPortion(str);
        if (strExtractNetworkPortion == null || strExtractNetworkPortion.length() == 0) {
            throw new IllegalArgumentException("Number is invalid!");
        }
        NumberEntry numberEntry = new NumberEntry(strExtractNetworkPortion);
        ArrayList<String> allIDDs = getAllIDDs(context, str2);
        int iCheckNANP = checkNANP(numberEntry, allIDDs);
        if (DBG) {
            Log.d(TAG, "NANP type: " + getNumberPlanType(iCheckNANP));
        }
        if (iCheckNANP == 1 || iCheckNANP == 2 || iCheckNANP == 3) {
            return strExtractNetworkPortion;
        }
        if (iCheckNANP == 4) {
            return (i == 1 || i == 2) ? strExtractNetworkPortion.substring(1) : strExtractNetworkPortion;
        }
        if (iCheckNANP == 5) {
            if (i == 1) {
                return strExtractNetworkPortion;
            }
            if (i == 0) {
                return PLUS_SIGN + strExtractNetworkPortion.substring(numberEntry.IDD != null ? numberEntry.IDD.length() : 0);
            }
            if (i == 2) {
                return strExtractNetworkPortion.substring(numberEntry.IDD != null ? numberEntry.IDD.length() : 0);
            }
        }
        int iCheckInternationalNumberPlan = checkInternationalNumberPlan(context, numberEntry, allIDDs, NANP_IDD);
        if (DBG) {
            Log.d(TAG, "International type: " + getNumberPlanType(iCheckInternationalNumberPlan));
        }
        String strSubstring = null;
        switch (iCheckInternationalNumberPlan) {
            case 100:
                if (i == 0) {
                    strSubstring = strExtractNetworkPortion.substring(1);
                }
                break;
            case 101:
                strSubstring = strExtractNetworkPortion;
                break;
            case 102:
                strSubstring = NANP_IDD + strExtractNetworkPortion.substring(1);
                break;
            case 103:
                if (i == 0 || i == 2) {
                    strSubstring = NANP_IDD + strExtractNetworkPortion.substring(numberEntry.IDD != null ? numberEntry.IDD.length() : 0);
                }
                break;
            case 104:
                int i2 = numberEntry.countryCode;
                if (!inExceptionListForNpCcAreaLocal(numberEntry) && strExtractNetworkPortion.length() >= 11 && i2 != 1) {
                    strSubstring = NANP_IDD + strExtractNetworkPortion;
                }
                break;
            default:
                if (strExtractNetworkPortion.startsWith(PLUS_SIGN) && (i == 1 || i == 2)) {
                    strSubstring = strExtractNetworkPortion.startsWith("+011") ? strExtractNetworkPortion.substring(1) : NANP_IDD + strExtractNetworkPortion.substring(1);
                }
                break;
        }
        return strSubstring == null ? strExtractNetworkPortion : strSubstring;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005c A[PHI: r3
  0x005c: PHI (r3v4 android.database.Cursor) = (r3v3 android.database.Cursor), (r3v5 android.database.Cursor) binds: [B:23:0x005a, B:17:0x004f] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.util.ArrayList<java.lang.String> getAllIDDs(android.content.Context r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "SmsNumberUtils"
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r1 = com.android.internal.telephony.SmsNumberUtils.IDDS_MAPS
            java.lang.Object r1 = r1.get(r11)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            if (r1 == 0) goto Ld
            return r1
        Ld:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r2 = "IDD"
            java.lang.String r3 = "MCC"
            java.lang.String[] r6 = new java.lang.String[]{r2, r3}
            r2 = 0
            r3 = 0
            if (r11 == 0) goto L28
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]
            r4[r2] = r11
            java.lang.String r5 = "MCC=?"
            r8 = r4
            r7 = r5
            goto L2a
        L28:
            r7 = r3
            r8 = r7
        L2a:
            android.content.ContentResolver r4 = r10.getContentResolver()     // Catch: java.lang.Throwable -> L52 android.database.SQLException -> L54
            android.net.Uri r5 = com.android.internal.telephony.HbpcdLookup.MccIdd.CONTENT_URI     // Catch: java.lang.Throwable -> L52 android.database.SQLException -> L54
            r9 = 0
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L52 android.database.SQLException -> L54
            int r10 = r3.getCount()     // Catch: java.lang.Throwable -> L52 android.database.SQLException -> L54
            if (r10 <= 0) goto L4f
        L3b:
            boolean r10 = r3.moveToNext()     // Catch: java.lang.Throwable -> L52 android.database.SQLException -> L54
            if (r10 == 0) goto L4f
            java.lang.String r10 = r3.getString(r2)     // Catch: java.lang.Throwable -> L52 android.database.SQLException -> L54
            boolean r4 = r1.contains(r10)     // Catch: java.lang.Throwable -> L52 android.database.SQLException -> L54
            if (r4 != 0) goto L3b
            r1.add(r10)     // Catch: java.lang.Throwable -> L52 android.database.SQLException -> L54
            goto L3b
        L4f:
            if (r3 == 0) goto L5f
            goto L5c
        L52:
            r10 = move-exception
            goto L85
        L54:
            r10 = move-exception
            java.lang.String r2 = "Can't access HbpcdLookup database"
            android.util.Log.e(r0, r2, r10)     // Catch: java.lang.Throwable -> L52
            if (r3 == 0) goto L5f
        L5c:
            r3.close()
        L5f:
            java.util.HashMap<java.lang.String, java.util.ArrayList<java.lang.String>> r10 = com.android.internal.telephony.SmsNumberUtils.IDDS_MAPS
            r10.put(r11, r1)
            boolean r10 = com.android.internal.telephony.SmsNumberUtils.DBG
            if (r10 == 0) goto L84
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r2 = "MCC = "
            r10.append(r2)
            r10.append(r11)
            java.lang.String r11 = ", all IDDs = "
            r10.append(r11)
            r10.append(r1)
            java.lang.String r10 = r10.toString()
            android.util.Log.d(r0, r10)
        L84:
            return r1
        L85:
            if (r3 == 0) goto L8a
            r3.close()
        L8a:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.telephony.SmsNumberUtils.getAllIDDs(android.content.Context, java.lang.String):java.util.ArrayList");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002d A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int checkNANP(com.android.internal.telephony.SmsNumberUtils.NumberEntry r6, java.util.ArrayList<java.lang.String> r7) {
        /*
            java.lang.String r0 = r6.number
            int r1 = r0.length()
            r2 = 7
            r3 = 0
            r4 = 1
            if (r1 != r2) goto L2e
            char r6 = r0.charAt(r3)
            r7 = 50
            if (r6 < r7) goto L2a
            r7 = 57
            if (r6 > r7) goto L2a
            r6 = r4
        L18:
            if (r6 >= r2) goto L28
            char r7 = r0.charAt(r6)
            boolean r7 = android.telephony.PhoneNumberUtils.isISODigit(r7)
            if (r7 != 0) goto L25
            goto L2a
        L25:
            int r6 = r6 + 1
            goto L18
        L28:
            r6 = r4
            goto L2b
        L2a:
            r6 = r3
        L2b:
            if (r6 == 0) goto Lb8
            return r4
        L2e:
            int r1 = r0.length()
            r2 = 10
            if (r1 != r2) goto L3e
            boolean r6 = isNANP(r0)
            if (r6 == 0) goto Lb8
            r6 = 2
            return r6
        L3e:
            int r1 = r0.length()
            r2 = 3
            r5 = 11
            if (r1 != r5) goto L4e
            boolean r6 = isNANP(r0)
            if (r6 == 0) goto Lb8
            return r2
        L4e:
            java.lang.String r1 = "+"
            boolean r1 = r0.startsWith(r1)
            if (r1 == 0) goto L84
            java.lang.String r6 = r0.substring(r4)
            int r7 = r6.length()
            if (r7 != r5) goto L68
            boolean r6 = isNANP(r6)
            if (r6 == 0) goto Lb8
            r6 = 4
            return r6
        L68:
            java.lang.String r7 = "011"
            boolean r7 = r6.startsWith(r7)
            if (r7 == 0) goto Lb8
            int r7 = r6.length()
            r0 = 14
            if (r7 != r0) goto Lb8
            java.lang.String r6 = r6.substring(r2)
            boolean r6 = isNANP(r6)
            if (r6 == 0) goto Lb8
            r6 = 6
            return r6
        L84:
            java.util.Iterator r7 = r7.iterator()
        L88:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto Lb8
            java.lang.Object r1 = r7.next()
            java.lang.String r1 = (java.lang.String) r1
            boolean r2 = r0.startsWith(r1)
            if (r2 == 0) goto L88
            int r2 = r1.length()
            java.lang.String r2 = r0.substring(r2)
            if (r2 == 0) goto L88
            java.lang.String r5 = java.lang.String.valueOf(r4)
            boolean r5 = r2.startsWith(r5)
            if (r5 == 0) goto L88
            boolean r2 = isNANP(r2)
            if (r2 == 0) goto L88
            r6.IDD = r1
            r6 = 5
            return r6
        Lb8:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.telephony.SmsNumberUtils.checkNANP(com.android.internal.telephony.SmsNumberUtils$NumberEntry, java.util.ArrayList):int");
    }

    private static boolean isNANP(String str) {
        if (str.length() != 10 && (str.length() != 11 || !str.startsWith(NANP_NDD))) {
            return false;
        }
        if (str.length() == 11) {
            str = str.substring(1);
        }
        if (!isTwoToNine(str.charAt(0)) || !isTwoToNine(str.charAt(3))) {
            return false;
        }
        for (int i = 1; i < 10; i++) {
            if (!PhoneNumberUtils.isISODigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int checkInternationalNumberPlan(Context context, NumberEntry numberEntry, ArrayList<String> arrayList, String str) {
        int countryCode;
        int countryCode2;
        String str2 = numberEntry.number;
        if (str2.startsWith(PLUS_SIGN)) {
            String strSubstring = str2.substring(1);
            if (strSubstring.startsWith(str)) {
                int countryCode3 = getCountryCode(context, strSubstring.substring(str.length()));
                if (countryCode3 <= 0) {
                    return 0;
                }
                numberEntry.countryCode = countryCode3;
                return 100;
            }
            int countryCode4 = getCountryCode(context, strSubstring);
            if (countryCode4 <= 0) {
                return 0;
            }
            numberEntry.countryCode = countryCode4;
            return 102;
        }
        if (str2.startsWith(str)) {
            int countryCode5 = getCountryCode(context, str2.substring(str.length()));
            if (countryCode5 <= 0) {
                return 0;
            }
            numberEntry.countryCode = countryCode5;
            return 101;
        }
        for (String str3 : arrayList) {
            if (str2.startsWith(str3) && (countryCode2 = getCountryCode(context, str2.substring(str3.length()))) > 0) {
                numberEntry.countryCode = countryCode2;
                numberEntry.IDD = str3;
                return 103;
            }
        }
        if (str2.startsWith(AudioSystem.LEGACY_REMOTE_SUBMIX_ADDRESS) || (countryCode = getCountryCode(context, str2)) <= 0) {
            return 0;
        }
        numberEntry.countryCode = countryCode;
        return 104;
    }

    private static int getCountryCode(Context context, String str) {
        int[] allCountryCodes;
        if (str.length() < 10 || (allCountryCodes = getAllCountryCodes(context)) == null) {
            return -1;
        }
        int[] iArr = new int[MAX_COUNTRY_CODES_LENGTH];
        int i = 0;
        while (i < MAX_COUNTRY_CODES_LENGTH) {
            int i2 = i + 1;
            iArr[i] = Integer.parseInt(str.substring(0, i2));
            i = i2;
        }
        for (int i3 : allCountryCodes) {
            for (int i4 = 0; i4 < MAX_COUNTRY_CODES_LENGTH; i4++) {
                if (i3 == iArr[i4]) {
                    if (DBG) {
                        Log.d(TAG, "Country code = " + i3);
                    }
                    return i3;
                }
            }
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005c A[PHI: r0
  0x005c: PHI (r0v4 android.database.Cursor) = (r0v2 android.database.Cursor), (r0v5 android.database.Cursor) binds: [B:21:0x005a, B:15:0x004d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int[] getAllCountryCodes(android.content.Context r8) {
        /*
            int[] r0 = com.android.internal.telephony.SmsNumberUtils.ALL_COUNTRY_CODES
            if (r0 == 0) goto L5
            return r0
        L5:
            r0 = 0
            java.lang.String r1 = "Country_Code"
            java.lang.String[] r4 = new java.lang.String[]{r1}     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            android.net.Uri r3 = com.android.internal.telephony.HbpcdLookup.MccLookup.CONTENT_URI     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            int r8 = r0.getCount()     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            if (r8 <= 0) goto L4d
            int r8 = r0.getCount()     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            int[] r8 = new int[r8]     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            com.android.internal.telephony.SmsNumberUtils.ALL_COUNTRY_CODES = r8     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            r8 = 0
            r1 = r8
        L29:
            boolean r2 = r0.moveToNext()     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            if (r2 == 0) goto L4d
            int r2 = r0.getInt(r8)     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            int[] r3 = com.android.internal.telephony.SmsNumberUtils.ALL_COUNTRY_CODES     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            int r4 = r1 + 1
            r3[r1] = r2     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            java.lang.String r1 = java.lang.String.valueOf(r2)     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            java.lang.String r1 = r1.trim()     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            int r1 = r1.length()     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            int r2 = com.android.internal.telephony.SmsNumberUtils.MAX_COUNTRY_CODES_LENGTH     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
            if (r1 <= r2) goto L4b
            com.android.internal.telephony.SmsNumberUtils.MAX_COUNTRY_CODES_LENGTH = r1     // Catch: java.lang.Throwable -> L50 android.database.SQLException -> L52
        L4b:
            r1 = r4
            goto L29
        L4d:
            if (r0 == 0) goto L5f
            goto L5c
        L50:
            r8 = move-exception
            goto L62
        L52:
            r8 = move-exception
            java.lang.String r1 = "SmsNumberUtils"
            java.lang.String r2 = "Can't access HbpcdLookup database"
            android.util.Log.e(r1, r2, r8)     // Catch: java.lang.Throwable -> L50
            if (r0 == 0) goto L5f
        L5c:
            r0.close()
        L5f:
            int[] r8 = com.android.internal.telephony.SmsNumberUtils.ALL_COUNTRY_CODES
            return r8
        L62:
            if (r0 == 0) goto L67
            r0.close()
        L67:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.telephony.SmsNumberUtils.getAllCountryCodes(android.content.Context):int[]");
    }

    private static boolean inExceptionListForNpCcAreaLocal(NumberEntry numberEntry) {
        int i = numberEntry.countryCode;
        return numberEntry.number.length() == 12 && (i == 7 || i == 20 || i == 65 || i == 90);
    }

    public static String filterDestAddr(Context context, int i, String str) {
        int networkType;
        String strSubstring;
        if (DBG) {
            Log.d(TAG, "enter filterDestAddr. destAddr=\"" + pii(TAG, str) + "\"");
        }
        if (str == null || !PhoneNumberUtils.isGlobalPhoneNumber(str)) {
            Log.w(TAG, "destAddr" + pii(TAG, str) + " is not a global phone number! Nothing changed.");
            return str;
        }
        TelephonyManager telephonyManagerCreateForSubscriptionId = ((TelephonyManager) context.getSystemService("phone")).createForSubscriptionId(i);
        String networkOperator = telephonyManagerCreateForSubscriptionId.getNetworkOperator();
        String number = null;
        if (needToConvert(context, i) && (networkType = getNetworkType(telephonyManagerCreateForSubscriptionId)) != -1 && !TextUtils.isEmpty(networkOperator) && (strSubstring = networkOperator.substring(0, 3)) != null && strSubstring.trim().length() > 0) {
            number = formatNumber(context, str, strSubstring, networkType);
        }
        if (DBG) {
            StringBuilder sb = new StringBuilder();
            sb.append("destAddr is ");
            sb.append(number != null ? "formatted." : "not formatted.");
            Log.d(TAG, sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append("leave filterDestAddr, new destAddr=\"");
            sb2.append(number != null ? pii(TAG, number) : pii(TAG, str));
            sb2.append("\"");
            Log.d(TAG, sb2.toString());
        }
        return number != null ? number : str;
    }

    private static int getNetworkType(TelephonyManager telephonyManager) {
        int phoneType = telephonyManager.getPhoneType();
        if (phoneType == 1) {
            return 0;
        }
        if (phoneType == 2) {
            return isInternationalRoaming(telephonyManager) ? 2 : 1;
        }
        if (DBG) {
            Log.w(TAG, "warning! unknown mPhoneType value=" + phoneType);
        }
        return -1;
    }

    private static boolean isInternationalRoaming(TelephonyManager telephonyManager) {
        boolean zEquals;
        String networkCountryIso = telephonyManager.getNetworkCountryIso();
        String simCountryIso = telephonyManager.getSimCountryIso();
        boolean z = (TextUtils.isEmpty(networkCountryIso) || TextUtils.isEmpty(simCountryIso) || simCountryIso.equals(networkCountryIso)) ? false : true;
        if (!z) {
            return z;
        }
        if (XmlTags.ATTR_USER_ID.equals(simCountryIso)) {
            zEquals = "vi".equals(networkCountryIso);
        } else {
            if (!"vi".equals(simCountryIso)) {
                return z;
            }
            zEquals = XmlTags.ATTR_USER_ID.equals(networkCountryIso);
        }
        return !zEquals;
    }

    private static boolean needToConvert(Context context, int i) {
        PersistableBundle configForSubId;
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            CarrierConfigManager carrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config");
            if (carrierConfigManager != null && (configForSubId = carrierConfigManager.getConfigForSubId(i)) != null) {
                return configForSubId.getBoolean(CarrierConfigManager.KEY_SMS_REQUIRES_DESTINATION_NUMBER_CONVERSION_BOOL);
            }
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            return false;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private static String pii(String str, Object obj) {
        String strValueOf = String.valueOf(obj);
        if (obj == null || TextUtils.isEmpty(strValueOf) || Log.isLoggable(str, 2)) {
            return strValueOf;
        }
        return "[" + secureHash(strValueOf.getBytes()) + "]";
    }

    private static String secureHash(byte[] bArr) {
        if (TelephonyUtils.IS_USER) {
            return "****";
        }
        try {
            return Base64.encodeToString(MessageDigest.getInstance(KeyProperties.DIGEST_SHA1).digest(bArr), 11);
        } catch (NoSuchAlgorithmException unused) {
            return "####";
        }
    }
}
