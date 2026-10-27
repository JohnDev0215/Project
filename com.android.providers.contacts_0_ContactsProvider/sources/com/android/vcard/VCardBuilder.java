package com.android.vcard;

import android.content.ContentValues;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.vcard.VCardUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class VCardBuilder {
    private static final Set<String> sAllowedAndroidPropertySet = Collections.unmodifiableSet(new HashSet(Arrays.asList("vnd.android.cursor.item/nickname", "vnd.android.cursor.item/contact_event", "vnd.android.cursor.item/relation")));
    private static final Map<Integer, Integer> sPostalTypePriorityMap;
    private final boolean mAppendTypeParamName;
    private StringBuilder mBuilder;
    private final String mCharset;
    private boolean mEndAppended;
    private final boolean mIsDoCoMo;
    private final boolean mIsJapaneseMobilePhone;
    private final boolean mIsV30OrV40;
    private final boolean mNeedsToConvertPhoneticString;
    private final boolean mOnlyOneNoteFieldIsAvailable;
    private final boolean mRefrainsQPToNameProperties;
    private final boolean mShouldAppendCharsetParam;
    private final boolean mShouldUseQuotedPrintable;
    private final boolean mUsesAndroidProperty;
    private final boolean mUsesDefactProperty;
    private final String mVCardCharsetParameter;
    private final int mVCardType;

    static {
        HashMap map = new HashMap();
        sPostalTypePriorityMap = map;
        map.put(1, 0);
        sPostalTypePriorityMap.put(2, 1);
        sPostalTypePriorityMap.put(3, 2);
        sPostalTypePriorityMap.put(0, 3);
    }

    public VCardBuilder(int i, String str) {
        this.mVCardType = i;
        if (VCardConfig.isVersion40(i)) {
            Log.w("vCard", "Should not use vCard 4.0 when building vCard. It is not officially published yet.");
        }
        this.mIsV30OrV40 = VCardConfig.isVersion30(i) || VCardConfig.isVersion40(i);
        this.mShouldUseQuotedPrintable = VCardConfig.shouldUseQuotedPrintable(i);
        this.mIsDoCoMo = VCardConfig.isDoCoMo(i);
        this.mIsJapaneseMobilePhone = VCardConfig.needsToConvertPhoneticString(i);
        this.mOnlyOneNoteFieldIsAvailable = VCardConfig.onlyOneNoteFieldIsAvailable(i);
        this.mUsesAndroidProperty = VCardConfig.usesAndroidSpecificProperty(i);
        this.mUsesDefactProperty = VCardConfig.usesDefactProperty(i);
        this.mRefrainsQPToNameProperties = VCardConfig.shouldRefrainQPToNameProperties(i);
        this.mAppendTypeParamName = VCardConfig.appendTypeParamName(i);
        this.mNeedsToConvertPhoneticString = VCardConfig.needsToConvertPhoneticString(i);
        this.mShouldAppendCharsetParam = (VCardConfig.isVersion30(i) && "UTF-8".equalsIgnoreCase(str)) ? false : true;
        if (VCardConfig.isDoCoMo(i)) {
            if (!"SHIFT_JIS".equalsIgnoreCase(str) && TextUtils.isEmpty(str)) {
                this.mCharset = "SHIFT_JIS";
            } else {
                this.mCharset = str;
            }
            this.mVCardCharsetParameter = "CHARSET=SHIFT_JIS";
        } else if (TextUtils.isEmpty(str)) {
            Log.i("vCard", "Use the charset \"UTF-8\" for export.");
            this.mCharset = "UTF-8";
            this.mVCardCharsetParameter = "CHARSET=UTF-8";
        } else {
            this.mCharset = str;
            this.mVCardCharsetParameter = "CHARSET=" + str;
        }
        clear();
    }

    public void clear() {
        this.mBuilder = new StringBuilder();
        this.mEndAppended = false;
        appendLine("BEGIN", "VCARD");
        if (VCardConfig.isVersion40(this.mVCardType)) {
            appendLine("VERSION", "4.0");
        } else {
            if (VCardConfig.isVersion30(this.mVCardType)) {
                appendLine("VERSION", "3.0");
                return;
            }
            if (!VCardConfig.isVersion21(this.mVCardType)) {
                Log.w("vCard", "Unknown vCard version detected.");
            }
            appendLine("VERSION", "2.1");
        }
    }

    private boolean containsNonEmptyName(ContentValues contentValues) {
        return (TextUtils.isEmpty(contentValues.getAsString("data3")) && TextUtils.isEmpty(contentValues.getAsString("data5")) && TextUtils.isEmpty(contentValues.getAsString("data2")) && TextUtils.isEmpty(contentValues.getAsString("data4")) && TextUtils.isEmpty(contentValues.getAsString("data6")) && TextUtils.isEmpty(contentValues.getAsString("data9")) && TextUtils.isEmpty(contentValues.getAsString("data8")) && TextUtils.isEmpty(contentValues.getAsString("data7")) && TextUtils.isEmpty(contentValues.getAsString("data1"))) ? false : true;
    }

    private ContentValues getPrimaryContentValueWithStructuredName(List<ContentValues> list) {
        Iterator<ContentValues> it = list.iterator();
        ContentValues contentValues = null;
        ContentValues contentValues2 = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ContentValues next = it.next();
            if (next != null) {
                Integer asInteger = next.getAsInteger("is_super_primary");
                if (asInteger != null && asInteger.intValue() > 0) {
                    contentValues = next;
                    break;
                }
                if (contentValues == null) {
                    Integer asInteger2 = next.getAsInteger("is_primary");
                    if (asInteger2 != null && asInteger2.intValue() > 0 && containsNonEmptyName(next)) {
                        contentValues = next;
                    } else if (contentValues2 == null && containsNonEmptyName(next)) {
                        contentValues2 = next;
                    }
                }
            }
        }
        return contentValues == null ? contentValues2 != null ? contentValues2 : new ContentValues() : contentValues;
    }

    private VCardBuilder appendNamePropertiesV40(List<ContentValues> list) {
        String str;
        if (this.mIsDoCoMo || this.mNeedsToConvertPhoneticString) {
            Log.w("vCard", "Invalid flag is used in vCard 4.0 construction. Ignored.");
        }
        if (list == null || list.isEmpty()) {
            appendLine("FN", "");
            return this;
        }
        ContentValues primaryContentValueWithStructuredName = getPrimaryContentValueWithStructuredName(list);
        String asString = primaryContentValueWithStructuredName.getAsString("data3");
        String asString2 = primaryContentValueWithStructuredName.getAsString("data5");
        String asString3 = primaryContentValueWithStructuredName.getAsString("data2");
        String asString4 = primaryContentValueWithStructuredName.getAsString("data4");
        String asString5 = primaryContentValueWithStructuredName.getAsString("data6");
        String asString6 = primaryContentValueWithStructuredName.getAsString("data1");
        if (!TextUtils.isEmpty(asString) || !TextUtils.isEmpty(asString3) || !TextUtils.isEmpty(asString2) || !TextUtils.isEmpty(asString4) || !TextUtils.isEmpty(asString5)) {
            str = asString;
        } else {
            if (TextUtils.isEmpty(asString6)) {
                appendLine("FN", "");
                return this;
            }
            str = asString6;
        }
        String asString7 = primaryContentValueWithStructuredName.getAsString("data9");
        String asString8 = primaryContentValueWithStructuredName.getAsString("data8");
        String asString9 = primaryContentValueWithStructuredName.getAsString("data7");
        String strEscapeCharacters = escapeCharacters(str);
        String strEscapeCharacters2 = escapeCharacters(asString3);
        String strEscapeCharacters3 = escapeCharacters(asString2);
        String strEscapeCharacters4 = escapeCharacters(asString4);
        String strEscapeCharacters5 = escapeCharacters(asString5);
        this.mBuilder.append("N");
        if (!TextUtils.isEmpty(asString7) || !TextUtils.isEmpty(asString8) || !TextUtils.isEmpty(asString9)) {
            this.mBuilder.append(";");
            String str2 = escapeCharacters(asString7) + ';' + escapeCharacters(asString9) + ';' + escapeCharacters(asString8);
            StringBuilder sb = this.mBuilder;
            sb.append("SORT-AS=");
            sb.append(VCardUtils.toStringAsV40ParamValue(str2));
        }
        this.mBuilder.append(":");
        this.mBuilder.append(strEscapeCharacters);
        this.mBuilder.append(";");
        this.mBuilder.append(strEscapeCharacters2);
        this.mBuilder.append(";");
        this.mBuilder.append(strEscapeCharacters3);
        this.mBuilder.append(";");
        this.mBuilder.append(strEscapeCharacters4);
        this.mBuilder.append(";");
        this.mBuilder.append(strEscapeCharacters5);
        this.mBuilder.append("\r\n");
        if (TextUtils.isEmpty(asString6)) {
            Log.w("vCard", "DISPLAY_NAME is empty.");
            appendLine("FN", escapeCharacters(VCardUtils.constructNameFromElements(VCardConfig.getNameOrderType(this.mVCardType), str, asString2, asString3, asString4, asString5)));
        } else {
            String strEscapeCharacters6 = escapeCharacters(asString6);
            this.mBuilder.append("FN");
            this.mBuilder.append(":");
            this.mBuilder.append(strEscapeCharacters6);
            this.mBuilder.append("\r\n");
        }
        appendPhoneticNameFields(primaryContentValueWithStructuredName);
        return this;
    }

    public VCardBuilder appendNameProperties(List<ContentValues> list) {
        ContentValues contentValues;
        int i;
        String str;
        String str2;
        String strEscapeCharacters;
        String strEscapeCharacters2;
        String strEscapeCharacters3;
        String strEscapeCharacters4;
        String strEscapeCharacters5;
        String str3;
        ContentValues contentValues2;
        if (VCardConfig.isVersion40(this.mVCardType)) {
            appendNamePropertiesV40(list);
            return this;
        }
        if (list == null || list.isEmpty()) {
            if (VCardConfig.isVersion30(this.mVCardType)) {
                appendLine("N", "");
                appendLine("FN", "");
            } else if (this.mIsDoCoMo) {
                appendLine("N", "");
            }
            return this;
        }
        ContentValues primaryContentValueWithStructuredName = getPrimaryContentValueWithStructuredName(list);
        String asString = primaryContentValueWithStructuredName.getAsString("data3");
        String asString2 = primaryContentValueWithStructuredName.getAsString("data5");
        String asString3 = primaryContentValueWithStructuredName.getAsString("data2");
        String asString4 = primaryContentValueWithStructuredName.getAsString("data4");
        String asString5 = primaryContentValueWithStructuredName.getAsString("data6");
        String asString6 = primaryContentValueWithStructuredName.getAsString("data1");
        if (!TextUtils.isEmpty(asString) || !TextUtils.isEmpty(asString3)) {
            int i2 = 0;
            boolean zShouldAppendCharsetParam = shouldAppendCharsetParam(asString, asString3, asString2, asString4, asString5);
            boolean z = (this.mRefrainsQPToNameProperties || (VCardUtils.containsOnlyNonCrLfPrintableAscii(asString) && VCardUtils.containsOnlyNonCrLfPrintableAscii(asString3) && VCardUtils.containsOnlyNonCrLfPrintableAscii(asString2) && VCardUtils.containsOnlyNonCrLfPrintableAscii(asString4) && VCardUtils.containsOnlyNonCrLfPrintableAscii(asString5))) ? false : true;
            if (TextUtils.isEmpty(asString6)) {
                contentValues = primaryContentValueWithStructuredName;
                i = 1;
                str = ";";
                str2 = "\r\n";
                asString6 = VCardUtils.constructNameFromElements(VCardConfig.getNameOrderType(this.mVCardType), asString, asString2, asString3, asString4, asString5);
            } else {
                contentValues = primaryContentValueWithStructuredName;
                i = 1;
                str = ";";
                str2 = "\r\n";
            }
            String[] strArr = new String[i];
            strArr[0] = asString6;
            boolean zShouldAppendCharsetParam2 = shouldAppendCharsetParam(strArr);
            if (!this.mRefrainsQPToNameProperties) {
                String[] strArr2 = new String[i];
                strArr2[0] = asString6;
                if (!VCardUtils.containsOnlyNonCrLfPrintableAscii(strArr2)) {
                    i2 = i;
                }
            }
            if (z) {
                strEscapeCharacters = encodeQuotedPrintable(asString);
                strEscapeCharacters2 = encodeQuotedPrintable(asString3);
                strEscapeCharacters3 = encodeQuotedPrintable(asString2);
                strEscapeCharacters4 = encodeQuotedPrintable(asString4);
                strEscapeCharacters5 = encodeQuotedPrintable(asString5);
            } else {
                strEscapeCharacters = escapeCharacters(asString);
                strEscapeCharacters2 = escapeCharacters(asString3);
                strEscapeCharacters3 = escapeCharacters(asString2);
                strEscapeCharacters4 = escapeCharacters(asString4);
                strEscapeCharacters5 = escapeCharacters(asString5);
            }
            String strEncodeQuotedPrintable = i2 != 0 ? encodeQuotedPrintable(asString6) : escapeCharacters(asString6);
            this.mBuilder.append("N");
            if (this.mIsDoCoMo) {
                if (zShouldAppendCharsetParam) {
                    str3 = str;
                    this.mBuilder.append(str3);
                    this.mBuilder.append(this.mVCardCharsetParameter);
                } else {
                    str3 = str;
                }
                if (z) {
                    this.mBuilder.append(str3);
                    this.mBuilder.append("ENCODING=QUOTED-PRINTABLE");
                }
                this.mBuilder.append(":");
                this.mBuilder.append(asString6);
                this.mBuilder.append(str3);
                this.mBuilder.append(str3);
                this.mBuilder.append(str3);
                this.mBuilder.append(str3);
            } else {
                str3 = str;
                if (zShouldAppendCharsetParam) {
                    this.mBuilder.append(str3);
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (z) {
                    this.mBuilder.append(str3);
                    this.mBuilder.append("ENCODING=QUOTED-PRINTABLE");
                }
                this.mBuilder.append(":");
                this.mBuilder.append(strEscapeCharacters);
                this.mBuilder.append(str3);
                this.mBuilder.append(strEscapeCharacters2);
                this.mBuilder.append(str3);
                this.mBuilder.append(strEscapeCharacters3);
                this.mBuilder.append(str3);
                this.mBuilder.append(strEscapeCharacters4);
                this.mBuilder.append(str3);
                this.mBuilder.append(strEscapeCharacters5);
            }
            String str4 = str2;
            this.mBuilder.append(str4);
            this.mBuilder.append("FN");
            if (zShouldAppendCharsetParam2) {
                this.mBuilder.append(str3);
                this.mBuilder.append(this.mVCardCharsetParameter);
            }
            if (i2 != 0) {
                this.mBuilder.append(str3);
                this.mBuilder.append("ENCODING=QUOTED-PRINTABLE");
            }
            this.mBuilder.append(":");
            this.mBuilder.append(strEncodeQuotedPrintable);
            this.mBuilder.append(str4);
            contentValues2 = contentValues;
        } else {
            if (!TextUtils.isEmpty(asString6)) {
                buildSinglePartNameField("N", asString6);
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append("\r\n");
                buildSinglePartNameField("FN", asString6);
                this.mBuilder.append("\r\n");
            } else if (VCardConfig.isVersion30(this.mVCardType)) {
                appendLine("N", "");
                appendLine("FN", "");
            } else if (this.mIsDoCoMo) {
                appendLine("N", "");
            }
            contentValues2 = primaryContentValueWithStructuredName;
        }
        appendPhoneticNameFields(contentValues2);
        return this;
    }

    private void buildSinglePartNameField(String str, String str2) {
        String strEscapeCharacters;
        boolean z = (this.mRefrainsQPToNameProperties || VCardUtils.containsOnlyNonCrLfPrintableAscii(str2)) ? false : true;
        if (z) {
            strEscapeCharacters = encodeQuotedPrintable(str2);
        } else {
            strEscapeCharacters = escapeCharacters(str2);
        }
        this.mBuilder.append(str);
        if (shouldAppendCharsetParam(str2)) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (z) {
            this.mBuilder.append(";");
            this.mBuilder.append("ENCODING=QUOTED-PRINTABLE");
        }
        this.mBuilder.append(":");
        this.mBuilder.append(strEscapeCharacters);
    }

    private void appendPhoneticNameFields(ContentValues contentValues) {
        String strEscapeCharacters;
        String strEscapeCharacters2;
        String strEscapeCharacters3;
        String strEscapeCharacters4;
        String strEscapeCharacters5;
        String strEscapeCharacters6;
        boolean z;
        String asString = contentValues.getAsString("data9");
        String asString2 = contentValues.getAsString("data8");
        String asString3 = contentValues.getAsString("data7");
        if (this.mNeedsToConvertPhoneticString) {
            asString = VCardUtils.toHalfWidthString(asString);
            asString2 = VCardUtils.toHalfWidthString(asString2);
            asString3 = VCardUtils.toHalfWidthString(asString3);
        }
        if (TextUtils.isEmpty(asString) && TextUtils.isEmpty(asString2) && TextUtils.isEmpty(asString3)) {
            if (this.mIsDoCoMo) {
                this.mBuilder.append("SOUND");
                this.mBuilder.append(";");
                this.mBuilder.append("X-IRMC-N");
                this.mBuilder.append(":");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append("\r\n");
                return;
            }
            return;
        }
        if (!VCardConfig.isVersion40(this.mVCardType)) {
            if (VCardConfig.isVersion30(this.mVCardType)) {
                String strConstructNameFromElements = VCardUtils.constructNameFromElements(this.mVCardType, asString, asString2, asString3);
                this.mBuilder.append("SORT-STRING");
                if (VCardConfig.isVersion30(this.mVCardType) && shouldAppendCharsetParam(strConstructNameFromElements)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                this.mBuilder.append(":");
                this.mBuilder.append(escapeCharacters(strConstructNameFromElements));
                this.mBuilder.append("\r\n");
            } else if (this.mIsJapaneseMobilePhone) {
                this.mBuilder.append("SOUND");
                this.mBuilder.append(";");
                this.mBuilder.append("X-IRMC-N");
                if ((this.mRefrainsQPToNameProperties || (VCardUtils.containsOnlyNonCrLfPrintableAscii(asString) && VCardUtils.containsOnlyNonCrLfPrintableAscii(asString2) && VCardUtils.containsOnlyNonCrLfPrintableAscii(asString3))) ? false : true) {
                    strEscapeCharacters4 = encodeQuotedPrintable(asString);
                    strEscapeCharacters5 = encodeQuotedPrintable(asString2);
                    strEscapeCharacters6 = encodeQuotedPrintable(asString3);
                } else {
                    strEscapeCharacters4 = escapeCharacters(asString);
                    strEscapeCharacters5 = escapeCharacters(asString2);
                    strEscapeCharacters6 = escapeCharacters(asString3);
                }
                if (shouldAppendCharsetParam(strEscapeCharacters4, strEscapeCharacters5, strEscapeCharacters6)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                this.mBuilder.append(":");
                if (TextUtils.isEmpty(strEscapeCharacters4)) {
                    z = true;
                } else {
                    this.mBuilder.append(strEscapeCharacters4);
                    z = false;
                }
                if (!TextUtils.isEmpty(strEscapeCharacters5)) {
                    if (z) {
                        z = false;
                    } else {
                        this.mBuilder.append(' ');
                    }
                    this.mBuilder.append(strEscapeCharacters5);
                }
                if (!TextUtils.isEmpty(strEscapeCharacters6)) {
                    if (!z) {
                        this.mBuilder.append(' ');
                    }
                    this.mBuilder.append(strEscapeCharacters6);
                }
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append("\r\n");
            }
        }
        if (this.mUsesDefactProperty) {
            if (!TextUtils.isEmpty(asString3)) {
                boolean z2 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(asString3);
                if (z2) {
                    strEscapeCharacters3 = encodeQuotedPrintable(asString3);
                } else {
                    strEscapeCharacters3 = escapeCharacters(asString3);
                }
                this.mBuilder.append("X-PHONETIC-FIRST-NAME");
                if (shouldAppendCharsetParam(asString3)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (z2) {
                    this.mBuilder.append(";");
                    this.mBuilder.append("ENCODING=QUOTED-PRINTABLE");
                }
                this.mBuilder.append(":");
                this.mBuilder.append(strEscapeCharacters3);
                this.mBuilder.append("\r\n");
            }
            if (!TextUtils.isEmpty(asString2)) {
                boolean z3 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(asString2);
                if (z3) {
                    strEscapeCharacters2 = encodeQuotedPrintable(asString2);
                } else {
                    strEscapeCharacters2 = escapeCharacters(asString2);
                }
                this.mBuilder.append("X-PHONETIC-MIDDLE-NAME");
                if (shouldAppendCharsetParam(asString2)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (z3) {
                    this.mBuilder.append(";");
                    this.mBuilder.append("ENCODING=QUOTED-PRINTABLE");
                }
                this.mBuilder.append(":");
                this.mBuilder.append(strEscapeCharacters2);
                this.mBuilder.append("\r\n");
            }
            if (TextUtils.isEmpty(asString)) {
                return;
            }
            boolean z4 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(asString);
            if (z4) {
                strEscapeCharacters = encodeQuotedPrintable(asString);
            } else {
                strEscapeCharacters = escapeCharacters(asString);
            }
            this.mBuilder.append("X-PHONETIC-LAST-NAME");
            if (shouldAppendCharsetParam(asString)) {
                this.mBuilder.append(";");
                this.mBuilder.append(this.mVCardCharsetParameter);
            }
            if (z4) {
                this.mBuilder.append(";");
                this.mBuilder.append("ENCODING=QUOTED-PRINTABLE");
            }
            this.mBuilder.append(":");
            this.mBuilder.append(strEscapeCharacters);
            this.mBuilder.append("\r\n");
        }
    }

    public VCardBuilder appendNickNames(List<ContentValues> list) {
        boolean z;
        if (!this.mIsV30OrV40) {
            z = this.mUsesAndroidProperty;
            return this;
        }
        if (list != null) {
            for (ContentValues contentValues : list) {
                String asString = contentValues.getAsString("data1");
                if (!TextUtils.isEmpty(asString)) {
                    if (z) {
                        appendAndroidSpecificProperty("vnd.android.cursor.item/nickname", contentValues);
                    } else {
                        appendLineWithCharsetAndQPDetection("NICKNAME", asString);
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendPhones(List<ContentValues> list, VCardPhoneNumberTranslationCallback vCardPhoneNumberTranslationCallback) {
        boolean z;
        if (list != null) {
            HashSet hashSet = new HashSet();
            z = false;
            for (ContentValues contentValues : list) {
                Integer asInteger = contentValues.getAsInteger("data2");
                String asString = contentValues.getAsString("data3");
                Integer asInteger2 = contentValues.getAsInteger("is_primary");
                boolean z2 = asInteger2 != null && asInteger2.intValue() > 0;
                String asString2 = contentValues.getAsString("data1");
                if (asString2 != null) {
                    asString2 = asString2.trim();
                }
                if (!TextUtils.isEmpty(asString2)) {
                    int iIntValue = asInteger != null ? asInteger.intValue() : 1;
                    if (vCardPhoneNumberTranslationCallback != null) {
                        String strOnValueReceived = vCardPhoneNumberTranslationCallback.onValueReceived(asString2, iIntValue, asString, z2);
                        if (!hashSet.contains(strOnValueReceived)) {
                            hashSet.add(strOnValueReceived);
                            appendTelLine(Integer.valueOf(iIntValue), asString, strOnValueReceived, z2);
                        }
                    } else if (iIntValue == 6 || VCardConfig.refrainPhoneNumberFormatting(this.mVCardType)) {
                        if (!hashSet.contains(asString2)) {
                            hashSet.add(asString2);
                            appendTelLine(Integer.valueOf(iIntValue), asString, asString2, z2);
                        }
                        z = true;
                    } else {
                        List<String> listSplitPhoneNumbers = splitPhoneNumbers(asString2);
                        if (!listSplitPhoneNumbers.isEmpty()) {
                            for (String str : listSplitPhoneNumbers) {
                                if (!hashSet.contains(str)) {
                                    String strReplace = str.replace(',', 'p').replace(';', 'w');
                                    if (TextUtils.equals(strReplace, str)) {
                                        StringBuilder sb = new StringBuilder();
                                        int length = str.length();
                                        for (int i = 0; i < length; i++) {
                                            char cCharAt = str.charAt(i);
                                            if (Character.isDigit(cCharAt) || cCharAt == '+') {
                                                sb.append(cCharAt);
                                            }
                                        }
                                        strReplace = VCardUtils.PhoneNumberUtilsPort.formatNumber(sb.toString(), VCardUtils.getPhoneNumberFormat(this.mVCardType));
                                    }
                                    if (VCardConfig.isVersion40(this.mVCardType) && !TextUtils.isEmpty(strReplace) && !strReplace.startsWith("tel:")) {
                                        strReplace = "tel:" + strReplace;
                                    }
                                    hashSet.add(str);
                                    appendTelLine(Integer.valueOf(iIntValue), asString, strReplace, z2);
                                }
                            }
                            z = true;
                        }
                    }
                }
            }
        } else {
            z = false;
        }
        if (!z && this.mIsDoCoMo) {
            appendTelLine(1, "", "", false);
        }
        return this;
    }

    private List<String> splitPhoneNumbers(String str) {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\n' && sb.length() > 0) {
                arrayList.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(cCharAt);
            }
        }
        if (sb.length() > 0) {
            arrayList.add(sb.toString());
        }
        return arrayList;
    }

    public VCardBuilder appendEmails(List<ContentValues> list) {
        boolean z;
        if (list != null) {
            HashSet hashSet = new HashSet();
            z = false;
            for (ContentValues contentValues : list) {
                String asString = contentValues.getAsString("data1");
                if (asString != null) {
                    asString = asString.trim();
                }
                if (!TextUtils.isEmpty(asString)) {
                    Integer asInteger = contentValues.getAsInteger("data2");
                    int iIntValue = asInteger != null ? asInteger.intValue() : 3;
                    String asString2 = contentValues.getAsString("data3");
                    Integer asInteger2 = contentValues.getAsInteger("is_primary");
                    boolean z2 = asInteger2 != null && asInteger2.intValue() > 0;
                    if (!hashSet.contains(asString)) {
                        hashSet.add(asString);
                        appendEmailLine(iIntValue, asString2, asString, z2);
                    }
                    z = true;
                }
            }
        } else {
            z = false;
        }
        if (!z && this.mIsDoCoMo) {
            appendEmailLine(1, "", "", false);
        }
        return this;
    }

    public VCardBuilder appendPostals(List<ContentValues> list) {
        if (list == null || list.isEmpty()) {
            if (this.mIsDoCoMo) {
                this.mBuilder.append("ADR");
                this.mBuilder.append(";");
                this.mBuilder.append("HOME");
                this.mBuilder.append(":");
                this.mBuilder.append("\r\n");
            }
        } else if (this.mIsDoCoMo) {
            appendPostalsForDoCoMo(list);
        } else {
            appendPostalsForGeneric(list);
        }
        return this;
    }

    private void appendPostalsForDoCoMo(List<ContentValues> list) {
        ContentValues contentValues;
        int i;
        Iterator<ContentValues> it = list.iterator();
        ContentValues contentValues2 = null;
        int i2 = Integer.MAX_VALUE;
        int iIntValue = Integer.MAX_VALUE;
        while (true) {
            if (!it.hasNext()) {
                contentValues = contentValues2;
                i = iIntValue;
                break;
            }
            ContentValues next = it.next();
            if (next != null) {
                Integer asInteger = next.getAsInteger("data2");
                Integer num = sPostalTypePriorityMap.get(asInteger);
                int iIntValue2 = num != null ? num.intValue() : Integer.MAX_VALUE;
                if (iIntValue2 < i2) {
                    iIntValue = asInteger.intValue();
                    if (iIntValue2 == 0) {
                        i = iIntValue;
                        contentValues = next;
                        break;
                    } else {
                        contentValues2 = next;
                        i2 = iIntValue2;
                    }
                } else {
                    continue;
                }
            }
        }
        if (contentValues == null) {
            Log.w("vCard", "Should not come here. Must have at least one postal data.");
        } else {
            appendPostalLine(i, contentValues.getAsString("data3"), contentValues, false, true);
        }
    }

    private void appendPostalsForGeneric(List<ContentValues> list) {
        for (ContentValues contentValues : list) {
            if (contentValues != null) {
                Integer asInteger = contentValues.getAsInteger("data2");
                int iIntValue = asInteger != null ? asInteger.intValue() : 1;
                String asString = contentValues.getAsString("data3");
                Integer asInteger2 = contentValues.getAsInteger("is_primary");
                boolean z = false;
                if (asInteger2 != null && asInteger2.intValue() > 0) {
                    z = true;
                }
                appendPostalLine(iIntValue, asString, contentValues, z, false);
            }
        }
    }

    private static class PostalStruct {
        final String addressData;
        final boolean appendCharset;
        final boolean reallyUseQuotedPrintable;

        public PostalStruct(boolean z, boolean z2, String str) {
            this.reallyUseQuotedPrintable = z;
            this.appendCharset = z2;
            this.addressData = str;
        }
    }

    private PostalStruct tryConstructPostalStruct(ContentValues contentValues) {
        String strEscapeCharacters;
        String strEscapeCharacters2;
        String strEncodeQuotedPrintable;
        String strEncodeQuotedPrintable2;
        String strEncodeQuotedPrintable3;
        String strEncodeQuotedPrintable4;
        String strEncodeQuotedPrintable5;
        String asString = contentValues.getAsString("data5");
        String asString2 = contentValues.getAsString("data6");
        String asString3 = contentValues.getAsString("data4");
        String asString4 = contentValues.getAsString("data7");
        String asString5 = contentValues.getAsString("data8");
        String asString6 = contentValues.getAsString("data9");
        String asString7 = contentValues.getAsString("data10");
        boolean z = false;
        String[] strArr = {asString, asString2, asString3, asString4, asString5, asString6, asString7};
        if (!VCardUtils.areAllEmpty(strArr)) {
            if (this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(strArr)) {
                z = true;
            }
            boolean z2 = !VCardUtils.containsOnlyPrintableAscii(strArr);
            if (TextUtils.isEmpty(asString4)) {
                asString4 = TextUtils.isEmpty(asString2) ? "" : asString2;
            } else if (!TextUtils.isEmpty(asString2)) {
                asString4 = asString4 + " " + asString2;
            }
            if (z) {
                strEscapeCharacters2 = encodeQuotedPrintable(asString);
                strEncodeQuotedPrintable = encodeQuotedPrintable(asString3);
                strEncodeQuotedPrintable2 = encodeQuotedPrintable(asString4);
                strEncodeQuotedPrintable3 = encodeQuotedPrintable(asString5);
                strEncodeQuotedPrintable4 = encodeQuotedPrintable(asString6);
                strEncodeQuotedPrintable5 = encodeQuotedPrintable(asString7);
            } else {
                strEscapeCharacters2 = escapeCharacters(asString);
                String strEscapeCharacters3 = escapeCharacters(asString3);
                String strEscapeCharacters4 = escapeCharacters(asString4);
                String strEscapeCharacters5 = escapeCharacters(asString5);
                String strEscapeCharacters6 = escapeCharacters(asString6);
                String strEscapeCharacters7 = escapeCharacters(asString7);
                escapeCharacters(asString2);
                strEncodeQuotedPrintable = strEscapeCharacters3;
                strEncodeQuotedPrintable2 = strEscapeCharacters4;
                strEncodeQuotedPrintable3 = strEscapeCharacters5;
                strEncodeQuotedPrintable4 = strEscapeCharacters6;
                strEncodeQuotedPrintable5 = strEscapeCharacters7;
            }
            return new PostalStruct(z, z2, strEscapeCharacters2 + ";;" + strEncodeQuotedPrintable + ";" + strEncodeQuotedPrintable2 + ";" + strEncodeQuotedPrintable3 + ";" + strEncodeQuotedPrintable4 + ";" + strEncodeQuotedPrintable5);
        }
        String asString8 = contentValues.getAsString("data1");
        if (TextUtils.isEmpty(asString8)) {
            return null;
        }
        boolean z3 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(asString8);
        boolean z4 = !VCardUtils.containsOnlyPrintableAscii(asString8);
        if (z3) {
            strEscapeCharacters = encodeQuotedPrintable(asString8);
        } else {
            strEscapeCharacters = escapeCharacters(asString8);
        }
        return new PostalStruct(z3, z4, ";" + strEscapeCharacters + ";;;;;");
    }

    public VCardBuilder appendIms(List<ContentValues> list) {
        String propertyNameForIm;
        if (list != null) {
            for (ContentValues contentValues : list) {
                Integer asInteger = contentValues.getAsInteger("data5");
                if (asInteger != null && (propertyNameForIm = VCardUtils.getPropertyNameForIm(asInteger.intValue())) != null) {
                    String asString = contentValues.getAsString("data1");
                    if (asString != null) {
                        asString = asString.trim();
                    }
                    if (!TextUtils.isEmpty(asString)) {
                        Integer asInteger2 = contentValues.getAsInteger("data2");
                        int iIntValue = asInteger2 != null ? asInteger2.intValue() : 3;
                        String str = null;
                        if (iIntValue == 0) {
                            String asString2 = contentValues.getAsString("data3");
                            if (asString2 != null) {
                                str = "X-" + asString2;
                            }
                        } else if (iIntValue == 1) {
                            str = "HOME";
                        } else if (iIntValue == 2) {
                            str = "WORK";
                        }
                        ArrayList arrayList = new ArrayList();
                        if (!TextUtils.isEmpty(str)) {
                            arrayList.add(str);
                        }
                        Integer asInteger3 = contentValues.getAsInteger("is_primary");
                        if (asInteger3 != null && asInteger3.intValue() > 0) {
                            arrayList.add("PREF");
                        }
                        appendLineWithCharsetAndQPDetection(propertyNameForIm, arrayList, asString);
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendWebsites(List<ContentValues> list) {
        if (list != null) {
            Iterator<ContentValues> it = list.iterator();
            while (it.hasNext()) {
                String asString = it.next().getAsString("data1");
                if (asString != null) {
                    asString = asString.trim();
                }
                if (!TextUtils.isEmpty(asString)) {
                    appendLineWithCharsetAndQPDetection("URL", asString);
                }
            }
        }
        return this;
    }

    public VCardBuilder appendOrganizations(List<ContentValues> list) {
        if (list != null) {
            for (ContentValues contentValues : list) {
                String asString = contentValues.getAsString("data1");
                if (asString != null) {
                    asString = asString.trim();
                }
                String asString2 = contentValues.getAsString("data5");
                if (asString2 != null) {
                    asString2 = asString2.trim();
                }
                String asString3 = contentValues.getAsString("data4");
                if (asString3 != null) {
                    asString3 = asString3.trim();
                }
                StringBuilder sb = new StringBuilder();
                if (!TextUtils.isEmpty(asString)) {
                    sb.append(asString);
                }
                if (!TextUtils.isEmpty(asString2)) {
                    if (sb.length() > 0) {
                        sb.append(';');
                    }
                    sb.append(asString2);
                }
                String string = sb.toString();
                appendLine("ORG", string, !VCardUtils.containsOnlyPrintableAscii(string), this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(string));
                if (!TextUtils.isEmpty(asString3)) {
                    appendLine("TITLE", asString3, !VCardUtils.containsOnlyPrintableAscii(asString3), this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(asString3));
                }
            }
        }
        return this;
    }

    public VCardBuilder appendPhotos(List<ContentValues> list) {
        byte[] asByteArray;
        if (list != null) {
            for (ContentValues contentValues : list) {
                if (contentValues != null && (asByteArray = contentValues.getAsByteArray("data15")) != null) {
                    String strGuessImageType = VCardUtils.guessImageType(asByteArray);
                    if (strGuessImageType == null) {
                        Log.d("vCard", "Unknown photo type. Ignored.");
                    } else {
                        String str = new String(Base64.encode(asByteArray, 2));
                        if (!TextUtils.isEmpty(str)) {
                            appendPhotoLine(str, strGuessImageType);
                        }
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendNotes(List<ContentValues> list) {
        if (list != null) {
            boolean z = false;
            if (this.mOnlyOneNoteFieldIsAvailable) {
                StringBuilder sb = new StringBuilder();
                Iterator<ContentValues> it = list.iterator();
                boolean z2 = true;
                while (it.hasNext()) {
                    String asString = it.next().getAsString("data1");
                    if (asString == null) {
                        asString = "";
                    }
                    if (asString.length() > 0) {
                        if (z2) {
                            z2 = false;
                        } else {
                            sb.append('\n');
                        }
                        sb.append(asString);
                    }
                }
                String string = sb.toString();
                boolean z3 = !VCardUtils.containsOnlyPrintableAscii(string);
                if (this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(string)) {
                    z = true;
                }
                appendLine("NOTE", string, z3, z);
            } else {
                Iterator<ContentValues> it2 = list.iterator();
                while (it2.hasNext()) {
                    String asString2 = it2.next().getAsString("data1");
                    if (!TextUtils.isEmpty(asString2)) {
                        appendLine("NOTE", asString2, !VCardUtils.containsOnlyPrintableAscii(asString2), this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(asString2));
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendEvents(List<ContentValues> list) {
        if (list != null) {
            Iterator<ContentValues> it = list.iterator();
            String str = null;
            String str2 = null;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ContentValues next = it.next();
                if (next != null) {
                    Integer asInteger = next.getAsInteger("data2");
                    if ((asInteger != null ? asInteger.intValue() : 2) == 3) {
                        String asString = next.getAsString("data1");
                        if (asString == null) {
                            continue;
                        } else {
                            Integer asInteger2 = next.getAsInteger("is_super_primary");
                            if (asInteger2 != null && asInteger2.intValue() > 0) {
                                str = asString;
                                break;
                            }
                            Integer asInteger3 = next.getAsInteger("is_primary");
                            if (asInteger3 != null && asInteger3.intValue() > 0) {
                                str = asString;
                            } else if (str2 == null) {
                                str2 = asString;
                            }
                        }
                    } else if (this.mUsesAndroidProperty) {
                        appendAndroidSpecificProperty("vnd.android.cursor.item/contact_event", next);
                    }
                }
            }
            if (str != null) {
                appendLineWithCharsetAndQPDetection("BDAY", str.trim());
            } else if (str2 != null) {
                appendLineWithCharsetAndQPDetection("BDAY", str2.trim());
            }
        }
        return this;
    }

    public VCardBuilder appendRelation(List<ContentValues> list) {
        if (this.mUsesAndroidProperty && list != null) {
            for (ContentValues contentValues : list) {
                if (contentValues != null) {
                    appendAndroidSpecificProperty("vnd.android.cursor.item/relation", contentValues);
                }
            }
        }
        return this;
    }

    public void appendPostalLine(int i, String str, ContentValues contentValues, boolean z, boolean z2) {
        boolean z3;
        boolean z4;
        String str2;
        PostalStruct postalStructTryConstructPostalStruct = tryConstructPostalStruct(contentValues);
        if (postalStructTryConstructPostalStruct != null) {
            z3 = postalStructTryConstructPostalStruct.reallyUseQuotedPrintable;
            z4 = postalStructTryConstructPostalStruct.appendCharset;
            str2 = postalStructTryConstructPostalStruct.addressData;
        } else {
            if (!z2) {
                return;
            }
            str2 = "";
            z3 = false;
            z4 = false;
        }
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add("PREF");
        }
        if (i != 0) {
            if (i == 1) {
                arrayList.add("HOME");
            } else if (i == 2) {
                arrayList.add("WORK");
            } else if (i != 3) {
                Log.e("vCard", "Unknown StructuredPostal type: " + i);
            }
        } else if (!TextUtils.isEmpty(str) && VCardUtils.containsOnlyAlphaDigitHyphen(str)) {
            arrayList.add("X-" + str);
        }
        this.mBuilder.append("ADR");
        if (!arrayList.isEmpty()) {
            this.mBuilder.append(";");
            appendTypeParameters(arrayList);
        }
        if (z4) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (z3) {
            this.mBuilder.append(";");
            this.mBuilder.append("ENCODING=QUOTED-PRINTABLE");
        }
        this.mBuilder.append(":");
        this.mBuilder.append(str2);
        this.mBuilder.append("\r\n");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void appendEmailLine(int r4, java.lang.String r5, java.lang.String r6, boolean r7) {
        /*
            r3 = this;
            java.lang.String r0 = "CELL"
            r1 = 1
            r2 = 0
            if (r4 == 0) goto L2f
            if (r4 == r1) goto L2c
            r5 = 2
            if (r4 == r5) goto L29
            r5 = 3
            if (r4 == r5) goto L27
            r5 = 4
            if (r4 == r5) goto L58
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r0 = "Unknown Email type: "
            r5.append(r0)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.lang.String r5 = "vCard"
            android.util.Log.e(r5, r4)
        L27:
            r0 = r2
            goto L58
        L29:
            java.lang.String r0 = "WORK"
            goto L58
        L2c:
            java.lang.String r0 = "HOME"
            goto L58
        L2f:
            boolean r4 = com.android.vcard.VCardUtils.isMobilePhoneLabel(r5)
            if (r4 == 0) goto L36
            goto L58
        L36:
            boolean r4 = android.text.TextUtils.isEmpty(r5)
            if (r4 != 0) goto L27
            java.lang.String[] r4 = new java.lang.String[r1]
            r0 = 0
            r4[r0] = r5
            boolean r4 = com.android.vcard.VCardUtils.containsOnlyAlphaDigitHyphen(r4)
            if (r4 == 0) goto L27
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "X-"
            r4.append(r0)
            r4.append(r5)
            java.lang.String r0 = r4.toString()
        L58:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            if (r7 == 0) goto L64
            java.lang.String r5 = "PREF"
            r4.add(r5)
        L64:
            boolean r5 = android.text.TextUtils.isEmpty(r0)
            if (r5 != 0) goto L6d
            r4.add(r0)
        L6d:
            java.lang.String r5 = "EMAIL"
            r3.appendLineWithCharsetAndQPDetection(r5, r4, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardBuilder.appendEmailLine(int, java.lang.String, java.lang.String, boolean):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void appendTelLine(Integer num, String str, String str2, boolean z) {
        this.mBuilder.append("TEL");
        this.mBuilder.append(";");
        int iIntValue = num == null ? 7 : num.intValue();
        ArrayList arrayList = new ArrayList();
        switch (iIntValue) {
            case 0:
                if (TextUtils.isEmpty(str)) {
                    arrayList.add("VOICE");
                } else if (VCardUtils.isMobilePhoneLabel(str)) {
                    arrayList.add("CELL");
                } else if (this.mIsV30OrV40) {
                    arrayList.add(str);
                } else {
                    String upperCase = str.toUpperCase();
                    if (VCardUtils.isValidInV21ButUnknownToContactsPhoteType(upperCase)) {
                        arrayList.add(upperCase);
                    } else if (VCardUtils.containsOnlyAlphaDigitHyphen(str)) {
                        arrayList.add("X-" + str);
                    }
                }
                break;
            case 1:
                arrayList.addAll(Arrays.asList("HOME"));
                break;
            case 2:
                arrayList.add("CELL");
                break;
            case 3:
                arrayList.addAll(Arrays.asList("WORK"));
                break;
            case 4:
                arrayList.addAll(Arrays.asList("WORK", "FAX"));
                break;
            case 5:
                arrayList.addAll(Arrays.asList("HOME", "FAX"));
                break;
            case 6:
                if (this.mIsDoCoMo) {
                    arrayList.add("VOICE");
                } else {
                    arrayList.add("PAGER");
                }
                break;
            case 7:
                arrayList.add("VOICE");
                break;
            case 9:
                arrayList.add("CAR");
                break;
            case 10:
                arrayList.add("WORK");
                z = true;
                break;
            case 11:
                arrayList.add("ISDN");
                break;
            case 12:
                z = true;
                break;
            case 13:
                arrayList.add("FAX");
                break;
            case 15:
                arrayList.add("TLX");
                break;
            case 17:
                arrayList.addAll(Arrays.asList("WORK", "CELL"));
                break;
            case 18:
                arrayList.add("WORK");
                if (this.mIsDoCoMo) {
                    arrayList.add("VOICE");
                } else {
                    arrayList.add("PAGER");
                }
                break;
            case 20:
                arrayList.add("MSG");
                break;
        }
        if (z) {
            arrayList.add("PREF");
        }
        if (arrayList.isEmpty()) {
            appendUncommonPhoneType(this.mBuilder, Integer.valueOf(iIntValue));
        } else {
            appendTypeParameters(arrayList);
        }
        this.mBuilder.append(":");
        this.mBuilder.append(str2);
        this.mBuilder.append("\r\n");
    }

    private void appendUncommonPhoneType(StringBuilder sb, Integer num) {
        if (this.mIsDoCoMo) {
            sb.append("VOICE");
            return;
        }
        String phoneTypeString = VCardUtils.getPhoneTypeString(num);
        if (phoneTypeString != null) {
            appendTypeParameter(phoneTypeString);
            return;
        }
        Log.e("vCard", "Unknown or unsupported (by vCard) Phone type: " + num);
    }

    public void appendPhotoLine(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("PHOTO");
        sb.append(";");
        if (this.mIsV30OrV40) {
            sb.append("ENCODING=B");
        } else {
            sb.append("ENCODING=BASE64");
        }
        sb.append(";");
        appendTypeParameter(sb, str2);
        sb.append(":");
        sb.append(str);
        String string = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        int length = string.length();
        int i = 73;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            sb2.append(string.charAt(i3));
            i2++;
            if (i2 > i) {
                sb2.append("\r\n");
                sb2.append(" ");
                i = 72;
                i2 = 0;
            }
        }
        this.mBuilder.append(sb2.toString());
        this.mBuilder.append("\r\n");
        this.mBuilder.append("\r\n");
    }

    public VCardBuilder appendSipAddresses(List<ContentValues> list) {
        boolean z;
        if (!this.mIsV30OrV40) {
            z = this.mUsesDefactProperty;
            return this;
        }
        if (list != null) {
            Iterator<ContentValues> it = list.iterator();
            while (it.hasNext()) {
                String asString = it.next().getAsString("data1");
                if (!TextUtils.isEmpty(asString)) {
                    if (z) {
                        if (asString.startsWith("sip:")) {
                            if (asString.length() != 4) {
                                asString = asString.substring(4);
                            }
                        }
                        appendLineWithCharsetAndQPDetection("X-SIP", asString);
                    } else {
                        if (!asString.startsWith("sip:")) {
                            asString = "sip:" + asString;
                        }
                        appendLineWithCharsetAndQPDetection(VCardConfig.isVersion40(this.mVCardType) ? "TEL" : "IMPP", asString);
                    }
                }
            }
        }
        return this;
    }

    public void appendAndroidSpecificProperty(String str, ContentValues contentValues) {
        String strEscapeCharacters;
        if (sAllowedAndroidPropertySet.contains(str)) {
            ArrayList<String> arrayList = new ArrayList();
            for (int i = 1; i <= 15; i++) {
                String asString = contentValues.getAsString("data" + i);
                if (asString == null) {
                    asString = "";
                }
                arrayList.add(asString);
            }
            boolean z = this.mShouldAppendCharsetParam && !VCardUtils.containsOnlyNonCrLfPrintableAscii(arrayList);
            boolean z2 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(arrayList);
            this.mBuilder.append("X-ANDROID-CUSTOM");
            if (z) {
                this.mBuilder.append(";");
                this.mBuilder.append(this.mVCardCharsetParameter);
            }
            if (z2) {
                this.mBuilder.append(";");
                this.mBuilder.append("ENCODING=QUOTED-PRINTABLE");
            }
            this.mBuilder.append(":");
            this.mBuilder.append(str);
            for (String str2 : arrayList) {
                if (z2) {
                    strEscapeCharacters = encodeQuotedPrintable(str2);
                } else {
                    strEscapeCharacters = escapeCharacters(str2);
                }
                this.mBuilder.append(";");
                this.mBuilder.append(strEscapeCharacters);
            }
            this.mBuilder.append("\r\n");
        }
    }

    public void appendLineWithCharsetAndQPDetection(String str, String str2) {
        appendLineWithCharsetAndQPDetection(str, null, str2);
    }

    public void appendLineWithCharsetAndQPDetection(String str, List<String> list, String str2) {
        appendLine(str, list, str2, !VCardUtils.containsOnlyPrintableAscii(str2), this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(str2));
    }

    public void appendLine(String str, String str2) {
        appendLine(str, str2, false, false);
    }

    public void appendLine(String str, String str2, boolean z, boolean z2) {
        appendLine(str, null, str2, z, z2);
    }

    public void appendLine(String str, List<String> list, String str2, boolean z, boolean z2) {
        String strEscapeCharacters;
        this.mBuilder.append(str);
        if (list != null && list.size() > 0) {
            this.mBuilder.append(";");
            appendTypeParameters(list);
        }
        if (z) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (z2) {
            this.mBuilder.append(";");
            this.mBuilder.append("ENCODING=QUOTED-PRINTABLE");
            strEscapeCharacters = encodeQuotedPrintable(str2);
        } else {
            strEscapeCharacters = escapeCharacters(str2);
        }
        this.mBuilder.append(":");
        this.mBuilder.append(strEscapeCharacters);
        this.mBuilder.append("\r\n");
    }

    private void appendTypeParameters(List<String> list) {
        String stringAsV30ParamValue;
        boolean z = true;
        for (String str : list) {
            if (VCardConfig.isVersion30(this.mVCardType) || VCardConfig.isVersion40(this.mVCardType)) {
                if (VCardConfig.isVersion40(this.mVCardType)) {
                    stringAsV30ParamValue = VCardUtils.toStringAsV40ParamValue(str);
                } else {
                    stringAsV30ParamValue = VCardUtils.toStringAsV30ParamValue(str);
                }
                if (!TextUtils.isEmpty(stringAsV30ParamValue)) {
                    if (z) {
                        z = false;
                    } else {
                        this.mBuilder.append(";");
                    }
                    appendTypeParameter(stringAsV30ParamValue);
                }
            } else if (VCardUtils.isV21Word(str)) {
                if (z) {
                    z = false;
                } else {
                    this.mBuilder.append(";");
                }
                appendTypeParameter(str);
            }
        }
    }

    private void appendTypeParameter(String str) {
        appendTypeParameter(this.mBuilder, str);
    }

    private void appendTypeParameter(StringBuilder sb, String str) {
        if (VCardConfig.isVersion40(this.mVCardType) || ((VCardConfig.isVersion30(this.mVCardType) || this.mAppendTypeParamName) && !this.mIsDoCoMo)) {
            sb.append("TYPE");
            sb.append("=");
        }
        sb.append(str);
    }

    private boolean shouldAppendCharsetParam(String... strArr) {
        if (!this.mShouldAppendCharsetParam) {
            return false;
        }
        for (String str : strArr) {
            if (!VCardUtils.containsOnlyPrintableAscii(str)) {
                return true;
            }
        }
        return false;
    }

    private String encodeQuotedPrintable(String str) {
        byte[] bytes;
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        try {
            bytes = str.getBytes(this.mCharset);
        } catch (UnsupportedEncodingException unused) {
            Log.e("vCard", "Charset " + this.mCharset + " cannot be used. Try default charset");
            bytes = str.getBytes();
        }
        int i = 0;
        int i2 = 0;
        while (i < bytes.length) {
            sb.append(String.format("=%02X", Byte.valueOf(bytes[i])));
            i++;
            i2 += 3;
            if (i2 >= 67) {
                sb.append("=\r\n");
                i2 = 0;
            }
        }
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String escapeCharacters(java.lang.String r7) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 == 0) goto L9
            java.lang.String r6 = ""
            return r6
        L9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r1 = r7.length()
            r2 = 0
        L13:
            if (r2 >= r1) goto L7a
            char r3 = r7.charAt(r2)
            r4 = 10
            if (r3 == r4) goto L72
            r5 = 13
            if (r3 == r5) goto L67
            r4 = 44
            if (r3 == r4) goto L59
            r4 = 62
            r5 = 92
            if (r3 == r4) goto L4a
            if (r3 == r5) goto L40
            r4 = 59
            if (r3 == r4) goto L39
            r4 = 60
            if (r3 == r4) goto L4a
            r0.append(r3)
            goto L77
        L39:
            r0.append(r5)
            r0.append(r4)
            goto L77
        L40:
            boolean r4 = r6.mIsV30OrV40
            if (r4 == 0) goto L4a
            java.lang.String r3 = "\\\\"
            r0.append(r3)
            goto L77
        L4a:
            boolean r4 = r6.mIsDoCoMo
            if (r4 == 0) goto L55
            r0.append(r5)
            r0.append(r3)
            goto L77
        L55:
            r0.append(r3)
            goto L77
        L59:
            boolean r4 = r6.mIsV30OrV40
            if (r4 == 0) goto L63
            java.lang.String r3 = "\\,"
            r0.append(r3)
            goto L77
        L63:
            r0.append(r3)
            goto L77
        L67:
            int r3 = r2 + 1
            if (r3 >= r1) goto L72
            char r3 = r7.charAt(r2)
            if (r3 != r4) goto L72
            goto L77
        L72:
            java.lang.String r3 = "\\n"
            r0.append(r3)
        L77:
            int r2 = r2 + 1
            goto L13
        L7a:
            java.lang.String r6 = r0.toString()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardBuilder.escapeCharacters(java.lang.String):java.lang.String");
    }

    public String toString() {
        if (!this.mEndAppended) {
            if (this.mIsDoCoMo) {
                appendLine("X-CLASS", "PUBLIC");
                appendLine("X-REDUCTION", "");
                appendLine("X-NO", "");
                appendLine("X-DCM-HMN-MODE", "");
            }
            appendLine("END", "VCARD");
            this.mEndAppended = true;
        }
        return this.mBuilder.toString();
    }
}
