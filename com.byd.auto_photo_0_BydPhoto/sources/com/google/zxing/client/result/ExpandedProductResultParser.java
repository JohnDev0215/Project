package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class ExpandedProductResultParser extends ResultParser {
    private static String findAIvalue(int i, String str) {
        char cCharAt;
        if (str.charAt(i) != '(') {
            return null;
        }
        String strSubstring = str.substring(i + 1);
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < strSubstring.length() && (cCharAt = strSubstring.charAt(i2)) != ')'; i2++) {
            if (cCharAt < '0' || cCharAt > '9') {
                return null;
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    private static String findValue(int i, String str) {
        StringBuilder sb = new StringBuilder();
        String strSubstring = str.substring(i);
        for (int i2 = 0; i2 < strSubstring.length(); i2++) {
            char cCharAt = strSubstring.charAt(i2);
            if (cCharAt != '(') {
                sb.append(cCharAt);
            } else {
                if (findAIvalue(i2, strSubstring) != null) {
                    break;
                }
                sb.append('(');
            }
        }
        return sb.toString();
    }

    @Override // com.google.zxing.client.result.ResultParser
    public ExpandedProductParsedResult parse(Result result) {
        String strFindValue;
        int length;
        ExpandedProductParsedResult expandedProductParsedResult = null;
        if (result.getBarcodeFormat() != BarcodeFormat.RSS_EXPANDED) {
            return null;
        }
        String massagedText = ResultParser.getMassagedText(result);
        HashMap map = new HashMap();
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String strSubstring = null;
        String str10 = null;
        String strSubstring2 = null;
        String strSubstring3 = null;
        int i = 0;
        while (i < massagedText.length()) {
            String strFindAIvalue = findAIvalue(i, massagedText);
            if (strFindAIvalue != null) {
                int length2 = i + strFindAIvalue.length() + 2;
                strFindValue = findValue(length2, massagedText);
                length = length2 + strFindValue.length();
                switch (strFindAIvalue) {
                    case "00":
                        str2 = strFindValue;
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                    case "01":
                        str = strFindValue;
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                    case "10":
                        str3 = strFindValue;
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                    case "11":
                        str4 = strFindValue;
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                    case "13":
                        str5 = strFindValue;
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                    case "15":
                        str6 = strFindValue;
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                    case "17":
                        str7 = strFindValue;
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                    case "3100":
                    case "3101":
                    case "3102":
                    case "3103":
                    case "3104":
                    case "3105":
                    case "3106":
                    case "3107":
                    case "3108":
                    case "3109":
                        strSubstring = strFindAIvalue.substring(3);
                        str9 = ExpandedProductParsedResult.KILOGRAM;
                        str8 = strFindValue;
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                    case "3200":
                    case "3201":
                    case "3202":
                    case "3203":
                    case "3204":
                    case "3205":
                    case "3206":
                    case "3207":
                    case "3208":
                    case "3209":
                        strSubstring = strFindAIvalue.substring(3);
                        str9 = ExpandedProductParsedResult.POUND;
                        str8 = strFindValue;
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                    case "3920":
                    case "3921":
                    case "3922":
                    case "3923":
                        strSubstring2 = strFindAIvalue.substring(3);
                        str10 = strFindValue;
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                    case "3930":
                    case "3931":
                    case "3932":
                    case "3933":
                        if (strFindValue.length() >= 4) {
                            String strSubstring4 = strFindValue.substring(3);
                            strSubstring3 = strFindValue.substring(0, 3);
                            str10 = strSubstring4;
                            i = length;
                            expandedProductParsedResult = null;
                            strSubstring2 = strFindAIvalue.substring(3);
                            break;
                        } else {
                            return null;
                        }
                        break;
                    default:
                        map.put(strFindAIvalue, strFindValue);
                        i = length;
                        expandedProductParsedResult = null;
                        break;
                }
            } else {
                return expandedProductParsedResult;
            }
        }
        return new ExpandedProductParsedResult(massagedText, str, str2, str3, str4, str5, str6, str7, str8, str9, strSubstring, str10, strSubstring2, strSubstring3, map);
    }
}
