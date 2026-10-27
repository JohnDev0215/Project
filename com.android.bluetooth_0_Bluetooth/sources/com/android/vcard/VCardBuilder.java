package com.android.vcard;

import android.content.ContentValues;
import android.net.INetd;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.android.bluetooth.Utils;
import com.android.vcard.VCardUtils;
import com.google.protobuf.Reader;
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
    public static final int DEFAULT_EMAIL_TYPE = 3;
    public static final int DEFAULT_PHONE_TYPE = 1;
    public static final int DEFAULT_POSTAL_TYPE = 1;
    private static final String LOG_TAG = "vCard";
    private static final String SHIFT_JIS = "SHIFT_JIS";
    private static final String VCARD_DATA_PUBLIC = "PUBLIC";
    private static final String VCARD_DATA_SEPARATOR = ":";
    private static final String VCARD_DATA_VCARD = "VCARD";
    public static final String VCARD_END_OF_LINE = "\r\n";
    private static final String VCARD_ITEM_SEPARATOR = ";";
    private static final String VCARD_PARAM_ENCODING_BASE64_AS_B = "ENCODING=B";
    private static final String VCARD_PARAM_ENCODING_BASE64_V21 = "ENCODING=BASE64";
    private static final String VCARD_PARAM_ENCODING_QP = "ENCODING=QUOTED-PRINTABLE";
    private static final String VCARD_PARAM_EQUAL = "=";
    private static final String VCARD_PARAM_SEPARATOR = ";";
    private static final String VCARD_WS = " ";
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

    public VCardBuilder(int vcardType) {
        this(vcardType, null);
    }

    public VCardBuilder(int vcardType, String charset) throws UnsupportedEncodingException {
        this.mVCardType = vcardType;
        if (VCardConfig.isVersion40(vcardType)) {
            Log.w(LOG_TAG, "Should not use vCard 4.0 when building vCard. It is not officially published yet.");
        }
        this.mIsV30OrV40 = VCardConfig.isVersion30(vcardType) || VCardConfig.isVersion40(vcardType);
        this.mShouldUseQuotedPrintable = VCardConfig.shouldUseQuotedPrintable(vcardType);
        this.mIsDoCoMo = VCardConfig.isDoCoMo(vcardType);
        this.mIsJapaneseMobilePhone = VCardConfig.needsToConvertPhoneticString(vcardType);
        this.mOnlyOneNoteFieldIsAvailable = VCardConfig.onlyOneNoteFieldIsAvailable(vcardType);
        this.mUsesAndroidProperty = VCardConfig.usesAndroidSpecificProperty(vcardType);
        this.mUsesDefactProperty = VCardConfig.usesDefactProperty(vcardType);
        this.mRefrainsQPToNameProperties = VCardConfig.shouldRefrainQPToNameProperties(vcardType);
        this.mAppendTypeParamName = VCardConfig.appendTypeParamName(vcardType);
        this.mNeedsToConvertPhoneticString = VCardConfig.needsToConvertPhoneticString(vcardType);
        this.mShouldAppendCharsetParam = (VCardConfig.isVersion30(vcardType) && "UTF-8".equalsIgnoreCase(charset)) ? false : true;
        if (VCardConfig.isDoCoMo(vcardType)) {
            if (!SHIFT_JIS.equalsIgnoreCase(charset) && TextUtils.isEmpty(charset)) {
                this.mCharset = SHIFT_JIS;
            } else {
                this.mCharset = charset;
            }
            this.mVCardCharsetParameter = "CHARSET=SHIFT_JIS";
        } else if (TextUtils.isEmpty(charset)) {
            Log.i(LOG_TAG, "Use the charset \"UTF-8\" for export.");
            this.mCharset = "UTF-8";
            this.mVCardCharsetParameter = "CHARSET=UTF-8";
        } else {
            this.mCharset = charset;
            this.mVCardCharsetParameter = "CHARSET=" + charset;
        }
        clear();
    }

    public void clear() throws UnsupportedEncodingException {
        this.mBuilder = new StringBuilder();
        this.mEndAppended = false;
        appendLine(VCardConstants.PROPERTY_BEGIN, VCARD_DATA_VCARD);
        if (VCardConfig.isVersion40(this.mVCardType)) {
            appendLine(VCardConstants.PROPERTY_VERSION, VCardConstants.VERSION_V40);
        } else {
            if (VCardConfig.isVersion30(this.mVCardType)) {
                appendLine(VCardConstants.PROPERTY_VERSION, VCardConstants.VERSION_V30);
                return;
            }
            if (!VCardConfig.isVersion21(this.mVCardType)) {
                Log.w(LOG_TAG, "Unknown vCard version detected.");
            }
            appendLine(VCardConstants.PROPERTY_VERSION, VCardConstants.VERSION_V21);
        }
    }

    private boolean containsNonEmptyName(ContentValues contentValues) {
        String familyName = contentValues.getAsString("data3");
        String middleName = contentValues.getAsString("data5");
        String givenName = contentValues.getAsString("data2");
        String prefix = contentValues.getAsString("data4");
        String suffix = contentValues.getAsString("data6");
        String phoneticFamilyName = contentValues.getAsString("data9");
        String phoneticMiddleName = contentValues.getAsString("data8");
        String phoneticGivenName = contentValues.getAsString("data7");
        String displayName = contentValues.getAsString("data1");
        return (TextUtils.isEmpty(familyName) && TextUtils.isEmpty(middleName) && TextUtils.isEmpty(givenName) && TextUtils.isEmpty(prefix) && TextUtils.isEmpty(suffix) && TextUtils.isEmpty(phoneticFamilyName) && TextUtils.isEmpty(phoneticMiddleName) && TextUtils.isEmpty(phoneticGivenName) && TextUtils.isEmpty(displayName)) ? false : true;
    }

    private ContentValues getPrimaryContentValueWithStructuredName(List<ContentValues> contentValuesList) {
        ContentValues primaryContentValues = null;
        ContentValues subprimaryContentValues = null;
        Iterator<ContentValues> it = contentValuesList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ContentValues contentValues = it.next();
            if (contentValues != null) {
                Integer isSuperPrimary = contentValues.getAsInteger("is_super_primary");
                if (isSuperPrimary != null && isSuperPrimary.intValue() > 0) {
                    primaryContentValues = contentValues;
                    break;
                }
                if (primaryContentValues == null) {
                    Integer isPrimary = contentValues.getAsInteger("is_primary");
                    if (isPrimary != null && isPrimary.intValue() > 0 && containsNonEmptyName(contentValues)) {
                        primaryContentValues = contentValues;
                    } else if (subprimaryContentValues == null && containsNonEmptyName(contentValues)) {
                        subprimaryContentValues = contentValues;
                    }
                }
            }
        }
        if (primaryContentValues == null) {
            if (subprimaryContentValues != null) {
                ContentValues primaryContentValues2 = subprimaryContentValues;
                return primaryContentValues2;
            }
            ContentValues primaryContentValues3 = new ContentValues();
            return primaryContentValues3;
        }
        return primaryContentValues;
    }

    private VCardBuilder appendNamePropertiesV40(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        String givenName;
        if (this.mIsDoCoMo || this.mNeedsToConvertPhoneticString) {
            Log.w(LOG_TAG, "Invalid flag is used in vCard 4.0 construction. Ignored.");
        }
        if (contentValuesList == null || contentValuesList.isEmpty()) {
            String str = VCardConstants.PROPERTY_FN;
            appendLine(str, INetd.NEXTHOP_NONE);
            return this;
        }
        ContentValues contentValues = getPrimaryContentValueWithStructuredName(contentValuesList);
        String familyName = contentValues.getAsString("data3");
        String middleName = contentValues.getAsString("data5");
        String givenName2 = contentValues.getAsString("data2");
        String prefix = contentValues.getAsString("data4");
        String suffix = contentValues.getAsString("data6");
        String formattedName = contentValues.getAsString("data1");
        if (TextUtils.isEmpty(familyName) && TextUtils.isEmpty(givenName2) && TextUtils.isEmpty(middleName) && TextUtils.isEmpty(prefix) && TextUtils.isEmpty(suffix)) {
            if (TextUtils.isEmpty(formattedName)) {
                appendLine(VCardConstants.PROPERTY_FN, INetd.NEXTHOP_NONE);
                return this;
            }
            familyName = formattedName;
        }
        String phoneticFamilyName = contentValues.getAsString("data9");
        String phoneticMiddleName = contentValues.getAsString("data8");
        String phoneticGivenName = contentValues.getAsString("data7");
        String escapedFamily = escapeCharacters(familyName);
        String escapedGiven = escapeCharacters(givenName2);
        String escapedMiddle = escapeCharacters(middleName);
        String escapedPrefix = escapeCharacters(prefix);
        String escapedSuffix = escapeCharacters(suffix);
        this.mBuilder.append(VCardConstants.PROPERTY_N);
        if (TextUtils.isEmpty(phoneticFamilyName) && TextUtils.isEmpty(phoneticMiddleName) && TextUtils.isEmpty(phoneticGivenName)) {
            givenName = givenName2;
        } else {
            this.mBuilder.append(";");
            StringBuilder sb = new StringBuilder();
            givenName = givenName2;
            sb.append(escapeCharacters(phoneticFamilyName));
            sb.append(Utils.WAIT);
            sb.append(escapeCharacters(phoneticGivenName));
            sb.append(Utils.WAIT);
            sb.append(escapeCharacters(phoneticMiddleName));
            String sortAs = sb.toString();
            StringBuilder sb2 = this.mBuilder;
            sb2.append("SORT-AS=");
            sb2.append(VCardUtils.toStringAsV40ParamValue(sortAs));
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(escapedFamily);
        this.mBuilder.append(";");
        this.mBuilder.append(escapedGiven);
        this.mBuilder.append(";");
        this.mBuilder.append(escapedMiddle);
        this.mBuilder.append(";");
        this.mBuilder.append(escapedPrefix);
        this.mBuilder.append(";");
        this.mBuilder.append(escapedSuffix);
        this.mBuilder.append(VCARD_END_OF_LINE);
        if (!TextUtils.isEmpty(formattedName)) {
            String escapedFormatted = escapeCharacters(formattedName);
            this.mBuilder.append(VCardConstants.PROPERTY_FN);
            this.mBuilder.append(VCARD_DATA_SEPARATOR);
            this.mBuilder.append(escapedFormatted);
            this.mBuilder.append(VCARD_END_OF_LINE);
        } else {
            Log.w(LOG_TAG, "DISPLAY_NAME is empty.");
            String escapedGiven2 = familyName;
            String escaped = escapeCharacters(VCardUtils.constructNameFromElements(VCardConfig.getNameOrderType(this.mVCardType), escapedGiven2, middleName, givenName, prefix, suffix));
            appendLine(VCardConstants.PROPERTY_FN, escaped);
        }
        appendPhoneticNameFields(contentValues);
        return this;
    }

    public VCardBuilder appendNameProperties(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        ContentValues contentValues;
        int i;
        String str;
        String str2;
        String formattedName;
        String encodedFamily;
        String encodedGiven;
        String encodedMiddle;
        String encodedPrefix;
        String familyName;
        String strEscapeCharacters;
        String prefix;
        if (VCardConfig.isVersion40(this.mVCardType)) {
            return appendNamePropertiesV40(contentValuesList);
        }
        if (contentValuesList == null || contentValuesList.isEmpty()) {
            if (VCardConfig.isVersion30(this.mVCardType)) {
                appendLine(VCardConstants.PROPERTY_N, INetd.NEXTHOP_NONE);
                appendLine(VCardConstants.PROPERTY_FN, INetd.NEXTHOP_NONE);
            } else if (this.mIsDoCoMo) {
                appendLine(VCardConstants.PROPERTY_N, INetd.NEXTHOP_NONE);
            }
            return this;
        }
        ContentValues contentValues2 = getPrimaryContentValueWithStructuredName(contentValuesList);
        String familyName2 = contentValues2.getAsString("data3");
        String middleName = contentValues2.getAsString("data5");
        String givenName = contentValues2.getAsString("data2");
        String prefix2 = contentValues2.getAsString("data4");
        String suffix = contentValues2.getAsString("data6");
        String displayName = contentValues2.getAsString("data1");
        if (!TextUtils.isEmpty(familyName2) || !TextUtils.isEmpty(givenName)) {
            int i2 = 0;
            boolean reallyAppendCharsetParameterToName = shouldAppendCharsetParam(familyName2, givenName, middleName, prefix2, suffix);
            boolean reallyUseQuotedPrintableToName = (this.mRefrainsQPToNameProperties || (VCardUtils.containsOnlyNonCrLfPrintableAscii(familyName2) && VCardUtils.containsOnlyNonCrLfPrintableAscii(givenName) && VCardUtils.containsOnlyNonCrLfPrintableAscii(middleName) && VCardUtils.containsOnlyNonCrLfPrintableAscii(prefix2) && VCardUtils.containsOnlyNonCrLfPrintableAscii(suffix))) ? false : true;
            if (!TextUtils.isEmpty(displayName)) {
                formattedName = displayName;
                contentValues = contentValues2;
                i = 1;
                str = ";";
                str2 = VCARD_END_OF_LINE;
            } else {
                int nameOrderType = VCardConfig.getNameOrderType(this.mVCardType);
                contentValues = contentValues2;
                i = 1;
                str = ";";
                str2 = VCARD_END_OF_LINE;
                formattedName = VCardUtils.constructNameFromElements(nameOrderType, familyName2, middleName, givenName, prefix2, suffix);
            }
            String[] strArr = new String[i];
            strArr[0] = formattedName;
            boolean reallyAppendCharsetParameterToFN = shouldAppendCharsetParam(strArr);
            if (!this.mRefrainsQPToNameProperties) {
                String[] strArr2 = new String[i];
                strArr2[0] = formattedName;
                if (!VCardUtils.containsOnlyNonCrLfPrintableAscii(strArr2)) {
                    i2 = i;
                }
            }
            int i3 = i2;
            if (reallyUseQuotedPrintableToName) {
                encodedFamily = encodeQuotedPrintable(familyName2);
                encodedGiven = encodeQuotedPrintable(givenName);
                encodedMiddle = encodeQuotedPrintable(middleName);
                encodedPrefix = encodeQuotedPrintable(prefix2);
                familyName = encodeQuotedPrintable(suffix);
            } else {
                encodedFamily = escapeCharacters(familyName2);
                encodedGiven = escapeCharacters(givenName);
                encodedMiddle = escapeCharacters(middleName);
                encodedPrefix = escapeCharacters(prefix2);
                familyName = escapeCharacters(suffix);
            }
            if (i3 == 0) {
                strEscapeCharacters = escapeCharacters(formattedName);
            } else {
                strEscapeCharacters = encodeQuotedPrintable(formattedName);
            }
            String encodedFormattedname = strEscapeCharacters;
            this.mBuilder.append(VCardConstants.PROPERTY_N);
            if (this.mIsDoCoMo) {
                if (reallyAppendCharsetParameterToName) {
                    prefix = str;
                    this.mBuilder.append(prefix);
                    this.mBuilder.append(this.mVCardCharsetParameter);
                } else {
                    prefix = str;
                }
                if (reallyUseQuotedPrintableToName) {
                    this.mBuilder.append(prefix);
                    this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(formattedName);
                this.mBuilder.append(prefix);
                this.mBuilder.append(prefix);
                this.mBuilder.append(prefix);
                this.mBuilder.append(prefix);
            } else {
                prefix = str;
                if (reallyAppendCharsetParameterToName) {
                    this.mBuilder.append(prefix);
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (reallyUseQuotedPrintableToName) {
                    this.mBuilder.append(prefix);
                    this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(encodedFamily);
                this.mBuilder.append(prefix);
                this.mBuilder.append(encodedGiven);
                this.mBuilder.append(prefix);
                this.mBuilder.append(encodedMiddle);
                this.mBuilder.append(prefix);
                this.mBuilder.append(encodedPrefix);
                this.mBuilder.append(prefix);
                this.mBuilder.append(familyName);
            }
            String str3 = str2;
            this.mBuilder.append(str3);
            this.mBuilder.append(VCardConstants.PROPERTY_FN);
            if (reallyAppendCharsetParameterToFN) {
                this.mBuilder.append(prefix);
                this.mBuilder.append(this.mVCardCharsetParameter);
            }
            if (i3 != 0) {
                this.mBuilder.append(prefix);
                this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
            }
            this.mBuilder.append(VCARD_DATA_SEPARATOR);
            this.mBuilder.append(encodedFormattedname);
            this.mBuilder.append(str3);
        } else if (!TextUtils.isEmpty(displayName)) {
            buildSinglePartNameField(VCardConstants.PROPERTY_N, displayName);
            this.mBuilder.append(";");
            this.mBuilder.append(";");
            this.mBuilder.append(";");
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_END_OF_LINE);
            buildSinglePartNameField(VCardConstants.PROPERTY_FN, displayName);
            this.mBuilder.append(VCARD_END_OF_LINE);
            contentValues = contentValues2;
        } else if (VCardConfig.isVersion30(this.mVCardType)) {
            appendLine(VCardConstants.PROPERTY_N, INetd.NEXTHOP_NONE);
            appendLine(VCardConstants.PROPERTY_FN, INetd.NEXTHOP_NONE);
            contentValues = contentValues2;
        } else if (!this.mIsDoCoMo) {
            contentValues = contentValues2;
        } else {
            appendLine(VCardConstants.PROPERTY_N, INetd.NEXTHOP_NONE);
            contentValues = contentValues2;
        }
        appendPhoneticNameFields(contentValues);
        return this;
    }

    private void buildSinglePartNameField(String property, String part) throws UnsupportedEncodingException {
        String encodedPart;
        boolean reallyUseQuotedPrintable = (this.mRefrainsQPToNameProperties || VCardUtils.containsOnlyNonCrLfPrintableAscii(part)) ? false : true;
        if (reallyUseQuotedPrintable) {
            encodedPart = encodeQuotedPrintable(part);
        } else {
            encodedPart = escapeCharacters(part);
        }
        this.mBuilder.append(property);
        if (shouldAppendCharsetParam(part)) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (reallyUseQuotedPrintable) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(encodedPart);
    }

    private void appendPhoneticNameFields(ContentValues contentValues) throws UnsupportedEncodingException {
        String phoneticFamilyName;
        String phoneticMiddleName;
        String phoneticGivenName;
        String encodedPhoneticFamilyName;
        String encodedPhoneticMiddleName;
        String encodedPhoneticGivenName;
        String encodedPhoneticFamilyName2;
        String encodedPhoneticMiddleName2;
        String encodedPhoneticGivenName2;
        String tmpPhoneticFamilyName = contentValues.getAsString("data9");
        String tmpPhoneticMiddleName = contentValues.getAsString("data8");
        String tmpPhoneticGivenName = contentValues.getAsString("data7");
        if (this.mNeedsToConvertPhoneticString) {
            phoneticFamilyName = VCardUtils.toHalfWidthString(tmpPhoneticFamilyName);
            phoneticMiddleName = VCardUtils.toHalfWidthString(tmpPhoneticMiddleName);
            phoneticGivenName = VCardUtils.toHalfWidthString(tmpPhoneticGivenName);
        } else {
            phoneticFamilyName = tmpPhoneticFamilyName;
            phoneticMiddleName = tmpPhoneticMiddleName;
            phoneticGivenName = tmpPhoneticGivenName;
        }
        if (TextUtils.isEmpty(phoneticFamilyName) && TextUtils.isEmpty(phoneticMiddleName) && TextUtils.isEmpty(phoneticGivenName)) {
            if (this.mIsDoCoMo) {
                this.mBuilder.append(VCardConstants.PROPERTY_SOUND);
                this.mBuilder.append(";");
                this.mBuilder.append("X-IRMC-N");
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(VCARD_END_OF_LINE);
                return;
            }
            return;
        }
        if (!VCardConfig.isVersion40(this.mVCardType)) {
            if (VCardConfig.isVersion30(this.mVCardType)) {
                String sortString = VCardUtils.constructNameFromElements(this.mVCardType, phoneticFamilyName, phoneticMiddleName, phoneticGivenName);
                this.mBuilder.append(VCardConstants.PROPERTY_SORT_STRING);
                if (VCardConfig.isVersion30(this.mVCardType) && shouldAppendCharsetParam(sortString)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(escapeCharacters(sortString));
                this.mBuilder.append(VCARD_END_OF_LINE);
            } else if (this.mIsJapaneseMobilePhone) {
                this.mBuilder.append(VCardConstants.PROPERTY_SOUND);
                this.mBuilder.append(";");
                this.mBuilder.append("X-IRMC-N");
                boolean reallyUseQuotedPrintable = (this.mRefrainsQPToNameProperties || (VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticFamilyName) && VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticMiddleName) && VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticGivenName))) ? false : true;
                if (reallyUseQuotedPrintable) {
                    encodedPhoneticFamilyName2 = encodeQuotedPrintable(phoneticFamilyName);
                    encodedPhoneticMiddleName2 = encodeQuotedPrintable(phoneticMiddleName);
                    encodedPhoneticGivenName2 = encodeQuotedPrintable(phoneticGivenName);
                } else {
                    encodedPhoneticFamilyName2 = escapeCharacters(phoneticFamilyName);
                    encodedPhoneticMiddleName2 = escapeCharacters(phoneticMiddleName);
                    encodedPhoneticGivenName2 = escapeCharacters(phoneticGivenName);
                }
                if (shouldAppendCharsetParam(encodedPhoneticFamilyName2, encodedPhoneticMiddleName2, encodedPhoneticGivenName2)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                boolean first = true;
                if (!TextUtils.isEmpty(encodedPhoneticFamilyName2)) {
                    this.mBuilder.append(encodedPhoneticFamilyName2);
                    first = false;
                }
                if (!TextUtils.isEmpty(encodedPhoneticMiddleName2)) {
                    if (first) {
                        first = false;
                    } else {
                        this.mBuilder.append(' ');
                    }
                    this.mBuilder.append(encodedPhoneticMiddleName2);
                }
                if (!TextUtils.isEmpty(encodedPhoneticGivenName2)) {
                    if (!first) {
                        this.mBuilder.append(' ');
                    }
                    this.mBuilder.append(encodedPhoneticGivenName2);
                }
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(";");
                this.mBuilder.append(VCARD_END_OF_LINE);
            }
        }
        if (this.mUsesDefactProperty) {
            if (!TextUtils.isEmpty(phoneticGivenName)) {
                boolean reallyUseQuotedPrintable2 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticGivenName);
                if (reallyUseQuotedPrintable2) {
                    encodedPhoneticGivenName = encodeQuotedPrintable(phoneticGivenName);
                } else {
                    encodedPhoneticGivenName = escapeCharacters(phoneticGivenName);
                }
                this.mBuilder.append(VCardConstants.PROPERTY_X_PHONETIC_FIRST_NAME);
                if (shouldAppendCharsetParam(phoneticGivenName)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (reallyUseQuotedPrintable2) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(encodedPhoneticGivenName);
                this.mBuilder.append(VCARD_END_OF_LINE);
            }
            boolean reallyUseQuotedPrintable3 = TextUtils.isEmpty(phoneticMiddleName);
            if (!reallyUseQuotedPrintable3) {
                boolean reallyUseQuotedPrintable4 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticMiddleName);
                if (reallyUseQuotedPrintable4) {
                    encodedPhoneticMiddleName = encodeQuotedPrintable(phoneticMiddleName);
                } else {
                    encodedPhoneticMiddleName = escapeCharacters(phoneticMiddleName);
                }
                this.mBuilder.append(VCardConstants.PROPERTY_X_PHONETIC_MIDDLE_NAME);
                if (shouldAppendCharsetParam(phoneticMiddleName)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (reallyUseQuotedPrintable4) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(encodedPhoneticMiddleName);
                this.mBuilder.append(VCARD_END_OF_LINE);
            }
            boolean reallyUseQuotedPrintable5 = TextUtils.isEmpty(phoneticFamilyName);
            if (!reallyUseQuotedPrintable5) {
                boolean reallyUseQuotedPrintable6 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(phoneticFamilyName);
                if (reallyUseQuotedPrintable6) {
                    encodedPhoneticFamilyName = encodeQuotedPrintable(phoneticFamilyName);
                } else {
                    encodedPhoneticFamilyName = escapeCharacters(phoneticFamilyName);
                }
                this.mBuilder.append(VCardConstants.PROPERTY_X_PHONETIC_LAST_NAME);
                if (shouldAppendCharsetParam(phoneticFamilyName)) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(this.mVCardCharsetParameter);
                }
                if (reallyUseQuotedPrintable6) {
                    this.mBuilder.append(";");
                    this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
                }
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(encodedPhoneticFamilyName);
                this.mBuilder.append(VCARD_END_OF_LINE);
            }
        }
    }

    public VCardBuilder appendNickNames(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        boolean useAndroidProperty;
        if (this.mIsV30OrV40) {
            useAndroidProperty = false;
        } else {
            boolean useAndroidProperty2 = this.mUsesAndroidProperty;
            if (useAndroidProperty2) {
                useAndroidProperty = true;
            } else {
                return this;
            }
        }
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                String nickname = contentValues.getAsString("data1");
                if (!TextUtils.isEmpty(nickname)) {
                    if (useAndroidProperty) {
                        appendAndroidSpecificProperty("vnd.android.cursor.item/nickname", contentValues);
                    } else {
                        appendLineWithCharsetAndQPDetection(VCardConstants.PROPERTY_NICKNAME, nickname);
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendPhones(List<ContentValues> contentValuesList, VCardPhoneNumberTranslationCallback translationCallback) {
        Iterator<ContentValues> it;
        boolean phoneLineExists;
        Iterator<ContentValues> it2;
        ContentValues contentValues;
        String formatted;
        ContentValues contentValues2;
        VCardPhoneNumberTranslationCallback vCardPhoneNumberTranslationCallback = translationCallback;
        boolean phoneLineExists2 = false;
        if (contentValuesList != null) {
            Set<String> phoneSet = new HashSet<>();
            Iterator<ContentValues> it3 = contentValuesList.iterator();
            while (it3.hasNext()) {
                ContentValues contentValues3 = it3.next();
                Integer typeAsObject = contentValues3.getAsInteger("data2");
                String label = contentValues3.getAsString("data3");
                Integer isPrimaryAsInteger = contentValues3.getAsInteger("is_primary");
                boolean isPrimary = isPrimaryAsInteger != null && isPrimaryAsInteger.intValue() > 0;
                String phoneNumber = contentValues3.getAsString("data1");
                if (phoneNumber != null) {
                    phoneNumber = phoneNumber.trim();
                }
                if (!TextUtils.isEmpty(phoneNumber)) {
                    int type = typeAsObject != null ? typeAsObject.intValue() : 1;
                    if (vCardPhoneNumberTranslationCallback != null) {
                        String phoneNumber2 = vCardPhoneNumberTranslationCallback.onValueReceived(phoneNumber, type, label, isPrimary);
                        if (!phoneSet.contains(phoneNumber2)) {
                            phoneSet.add(phoneNumber2);
                            appendTelLine(Integer.valueOf(type), label, phoneNumber2, isPrimary);
                        }
                        it = it3;
                    } else {
                        if (type == 6) {
                            it = it3;
                        } else if (VCardConfig.refrainPhoneNumberFormatting(this.mVCardType)) {
                            it = it3;
                        } else {
                            List<String> phoneNumberList = splitPhoneNumbers(phoneNumber);
                            if (!phoneNumberList.isEmpty()) {
                                phoneLineExists2 = true;
                                for (String actualPhoneNumber : phoneNumberList) {
                                    if (phoneSet.contains(actualPhoneNumber)) {
                                        phoneLineExists = phoneLineExists2;
                                        it2 = it3;
                                        contentValues = contentValues3;
                                    } else {
                                        phoneLineExists = phoneLineExists2;
                                        String numberWithControlSequence = actualPhoneNumber.replace(Utils.PAUSE, 'p').replace(Utils.WAIT, 'w');
                                        if (TextUtils.equals(numberWithControlSequence, actualPhoneNumber)) {
                                            StringBuilder digitsOnlyBuilder = new StringBuilder();
                                            int length = actualPhoneNumber.length();
                                            it2 = it3;
                                            int i = 0;
                                            while (i < length) {
                                                int length2 = length;
                                                char ch = actualPhoneNumber.charAt(i);
                                                if (Character.isDigit(ch)) {
                                                    contentValues2 = contentValues3;
                                                } else {
                                                    contentValues2 = contentValues3;
                                                    if (ch == '+') {
                                                    }
                                                    i++;
                                                    length = length2;
                                                    contentValues3 = contentValues2;
                                                }
                                                digitsOnlyBuilder.append(ch);
                                                i++;
                                                length = length2;
                                                contentValues3 = contentValues2;
                                            }
                                            contentValues = contentValues3;
                                            int length3 = this.mVCardType;
                                            int phoneFormat = VCardUtils.getPhoneNumberFormat(length3);
                                            formatted = VCardUtils.PhoneNumberUtilsPort.formatNumber(digitsOnlyBuilder.toString(), phoneFormat);
                                        } else {
                                            it2 = it3;
                                            contentValues = contentValues3;
                                            formatted = numberWithControlSequence;
                                        }
                                        if (VCardConfig.isVersion40(this.mVCardType) && !TextUtils.isEmpty(formatted) && !formatted.startsWith("tel:")) {
                                            formatted = "tel:" + formatted;
                                        }
                                        phoneSet.add(actualPhoneNumber);
                                        appendTelLine(Integer.valueOf(type), label, formatted, isPrimary);
                                    }
                                    phoneLineExists2 = phoneLineExists;
                                    it3 = it2;
                                    contentValues3 = contentValues;
                                }
                                it = it3;
                            }
                        }
                        phoneLineExists2 = true;
                        if (!phoneSet.contains(phoneNumber)) {
                            phoneSet.add(phoneNumber);
                            appendTelLine(Integer.valueOf(type), label, phoneNumber, isPrimary);
                        }
                    }
                    vCardPhoneNumberTranslationCallback = translationCallback;
                    it3 = it;
                }
            }
        }
        if (!phoneLineExists2 && this.mIsDoCoMo) {
            appendTelLine(1, INetd.NEXTHOP_NONE, INetd.NEXTHOP_NONE, false);
        }
        return this;
    }

    private List<String> splitPhoneNumbers(String phoneNumber) {
        List<String> phoneList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        int length = phoneNumber.length();
        for (int i = 0; i < length; i++) {
            char ch = phoneNumber.charAt(i);
            if (ch == '\n' && builder.length() > 0) {
                phoneList.add(builder.toString());
                builder = new StringBuilder();
            } else {
                builder.append(ch);
            }
        }
        int i2 = builder.length();
        if (i2 > 0) {
            phoneList.add(builder.toString());
        }
        return phoneList;
    }

    public VCardBuilder appendEmails(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        boolean emailAddressExists = false;
        if (contentValuesList != null) {
            Set<String> addressSet = new HashSet<>();
            for (ContentValues contentValues : contentValuesList) {
                String emailAddress = contentValues.getAsString("data1");
                if (emailAddress != null) {
                    emailAddress = emailAddress.trim();
                }
                if (!TextUtils.isEmpty(emailAddress)) {
                    Integer typeAsObject = contentValues.getAsInteger("data2");
                    int type = typeAsObject != null ? typeAsObject.intValue() : 3;
                    String label = contentValues.getAsString("data3");
                    Integer isPrimaryAsInteger = contentValues.getAsInteger("is_primary");
                    boolean isPrimary = isPrimaryAsInteger != null && isPrimaryAsInteger.intValue() > 0;
                    emailAddressExists = true;
                    if (!addressSet.contains(emailAddress)) {
                        addressSet.add(emailAddress);
                        appendEmailLine(type, label, emailAddress, isPrimary);
                    }
                }
            }
        }
        if (!emailAddressExists && this.mIsDoCoMo) {
            appendEmailLine(1, INetd.NEXTHOP_NONE, INetd.NEXTHOP_NONE, false);
        }
        return this;
    }

    public VCardBuilder appendPostals(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        if (contentValuesList == null || contentValuesList.isEmpty()) {
            if (this.mIsDoCoMo) {
                this.mBuilder.append(VCardConstants.PROPERTY_ADR);
                this.mBuilder.append(";");
                this.mBuilder.append(VCardConstants.PARAM_TYPE_HOME);
                this.mBuilder.append(VCARD_DATA_SEPARATOR);
                this.mBuilder.append(VCARD_END_OF_LINE);
            }
        } else if (this.mIsDoCoMo) {
            appendPostalsForDoCoMo(contentValuesList);
        } else {
            appendPostalsForGeneric(contentValuesList);
        }
        return this;
    }

    private void appendPostalsForDoCoMo(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        int priority;
        ContentValues currentContentValues;
        int currentPriority = Reader.READ_DONE;
        int currentType = Reader.READ_DONE;
        ContentValues currentContentValues2 = null;
        Iterator<ContentValues> it = contentValuesList.iterator();
        while (true) {
            if (!it.hasNext()) {
                priority = currentType;
                currentContentValues = currentContentValues2;
                break;
            }
            ContentValues contentValues = it.next();
            if (contentValues != null) {
                Integer typeAsInteger = contentValues.getAsInteger("data2");
                Integer priorityAsInteger = sPostalTypePriorityMap.get(typeAsInteger);
                int priority2 = priorityAsInteger != null ? priorityAsInteger.intValue() : Reader.READ_DONE;
                if (priority2 < currentPriority) {
                    currentPriority = priority2;
                    currentType = typeAsInteger.intValue();
                    currentContentValues2 = contentValues;
                    if (priority2 == 0) {
                        priority = currentType;
                        currentContentValues = currentContentValues2;
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        if (currentContentValues == null) {
            Log.w(LOG_TAG, "Should not come here. Must have at least one postal data.");
        } else {
            String label = currentContentValues.getAsString("data3");
            appendPostalLine(priority, label, currentContentValues, false, true);
        }
    }

    private void appendPostalsForGeneric(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        int type;
        boolean isPrimary;
        for (ContentValues contentValues : contentValuesList) {
            if (contentValues != null) {
                Integer typeAsInteger = contentValues.getAsInteger("data2");
                if (typeAsInteger == null) {
                    type = 1;
                } else {
                    type = typeAsInteger.intValue();
                }
                String label = contentValues.getAsString("data3");
                Integer isPrimaryAsInteger = contentValues.getAsInteger("is_primary");
                if (isPrimaryAsInteger != null && isPrimaryAsInteger.intValue() > 0) {
                    isPrimary = true;
                } else {
                    isPrimary = false;
                }
                appendPostalLine(type, label, contentValues, isPrimary, false);
            }
        }
    }

    private static class PostalStruct {
        final String addressData;
        final boolean appendCharset;
        final boolean reallyUseQuotedPrintable;

        public PostalStruct(boolean reallyUseQuotedPrintable, boolean appendCharset, String addressData) {
            this.reallyUseQuotedPrintable = reallyUseQuotedPrintable;
            this.appendCharset = appendCharset;
            this.addressData = addressData;
        }
    }

    private PostalStruct tryConstructPostalStruct(ContentValues contentValues) throws UnsupportedEncodingException {
        String encodedFormattedAddress;
        String rawLocality2;
        String encodedPoBox;
        String encodedStreet;
        String rawPoBox;
        String rawNeighborhood;
        String rawStreet;
        String rawLocality;
        String rawPoBox2 = contentValues.getAsString("data5");
        String rawNeighborhood2 = contentValues.getAsString("data6");
        String rawStreet2 = contentValues.getAsString("data4");
        String rawLocality3 = contentValues.getAsString("data7");
        String rawRegion = contentValues.getAsString("data8");
        String rawPostalCode = contentValues.getAsString("data9");
        String rawCountry = contentValues.getAsString("data10");
        boolean reallyUseQuotedPrintable = false;
        String[] rawAddressArray = {rawPoBox2, rawNeighborhood2, rawStreet2, rawLocality3, rawRegion, rawPostalCode, rawCountry};
        if (!VCardUtils.areAllEmpty(rawAddressArray)) {
            if (this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawAddressArray)) {
                reallyUseQuotedPrintable = true;
            }
            boolean appendCharset = true ^ VCardUtils.containsOnlyPrintableAscii(rawAddressArray);
            if (TextUtils.isEmpty(rawLocality3)) {
                if (TextUtils.isEmpty(rawNeighborhood2)) {
                    rawLocality2 = INetd.NEXTHOP_NONE;
                } else {
                    rawLocality2 = rawNeighborhood2;
                }
            } else if (TextUtils.isEmpty(rawNeighborhood2)) {
                rawLocality2 = rawLocality3;
            } else {
                rawLocality2 = rawLocality3 + VCARD_WS + rawNeighborhood2;
            }
            if (reallyUseQuotedPrintable) {
                encodedPoBox = encodeQuotedPrintable(rawPoBox2);
                encodedStreet = encodeQuotedPrintable(rawStreet2);
                String encodedLocality = encodeQuotedPrintable(rawLocality2);
                String encodedRegion = encodeQuotedPrintable(rawRegion);
                String encodedPostalCode = encodeQuotedPrintable(rawPostalCode);
                rawPoBox = encodedLocality;
                rawNeighborhood = encodedRegion;
                rawStreet = encodedPostalCode;
                rawLocality = encodeQuotedPrintable(rawCountry);
            } else {
                encodedPoBox = escapeCharacters(rawPoBox2);
                encodedStreet = escapeCharacters(rawStreet2);
                String encodedLocality2 = escapeCharacters(rawLocality2);
                String encodedRegion2 = escapeCharacters(rawRegion);
                String encodedPostalCode2 = escapeCharacters(rawPostalCode);
                String encodedCountry = escapeCharacters(rawCountry);
                escapeCharacters(rawNeighborhood2);
                rawPoBox = encodedLocality2;
                rawNeighborhood = encodedRegion2;
                rawStreet = encodedPostalCode2;
                rawLocality = encodedCountry;
            }
            String encodedLocality3 = encodedPoBox + ";;" + encodedStreet + ";" + rawPoBox + ";" + rawNeighborhood + ";" + rawStreet + ";" + rawLocality;
            return new PostalStruct(reallyUseQuotedPrintable, appendCharset, encodedLocality3);
        }
        String rawFormattedAddress = contentValues.getAsString("data1");
        if (TextUtils.isEmpty(rawFormattedAddress)) {
            return null;
        }
        boolean reallyUseQuotedPrintable2 = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawFormattedAddress);
        boolean appendCharset2 = !VCardUtils.containsOnlyPrintableAscii(rawFormattedAddress);
        if (reallyUseQuotedPrintable2) {
            encodedFormattedAddress = encodeQuotedPrintable(rawFormattedAddress);
        } else {
            encodedFormattedAddress = escapeCharacters(rawFormattedAddress);
        }
        return new PostalStruct(reallyUseQuotedPrintable2, appendCharset2, ";" + encodedFormattedAddress + ";;;;;");
    }

    public VCardBuilder appendIms(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        String propertyName;
        String label;
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                Integer protocolAsObject = contentValues.getAsInteger("data5");
                if (protocolAsObject != null && (propertyName = VCardUtils.getPropertyNameForIm(protocolAsObject.intValue())) != null) {
                    String data = contentValues.getAsString("data1");
                    if (data != null) {
                        data = data.trim();
                    }
                    if (!TextUtils.isEmpty(data)) {
                        Integer typeAsInteger = contentValues.getAsInteger("data2");
                        int iIntValue = typeAsInteger != null ? typeAsInteger.intValue() : 3;
                        if (iIntValue == 0) {
                            String label2 = contentValues.getAsString("data3");
                            String typeAsString = label2 != null ? "X-" + label2 : null;
                            label = typeAsString;
                        } else if (iIntValue == 1) {
                            label = VCardConstants.PARAM_TYPE_HOME;
                        } else if (iIntValue == 2) {
                            label = VCardConstants.PARAM_TYPE_WORK;
                        } else {
                            label = null;
                        }
                        List<String> parameterList = new ArrayList<>();
                        if (!TextUtils.isEmpty(label)) {
                            parameterList.add(label);
                        }
                        Integer isPrimaryAsInteger = contentValues.getAsInteger("is_primary");
                        boolean isPrimary = isPrimaryAsInteger != null && isPrimaryAsInteger.intValue() > 0;
                        if (isPrimary) {
                            parameterList.add(VCardConstants.PARAM_TYPE_PREF);
                        }
                        appendLineWithCharsetAndQPDetection(propertyName, parameterList, data);
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendWebsites(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                String website = contentValues.getAsString("data1");
                if (website != null) {
                    website = website.trim();
                }
                if (!TextUtils.isEmpty(website)) {
                    appendLineWithCharsetAndQPDetection(VCardConstants.PROPERTY_URL, website);
                }
            }
        }
        return this;
    }

    public VCardBuilder appendOrganizations(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                String company = contentValues.getAsString("data1");
                if (company != null) {
                    company = company.trim();
                }
                String department = contentValues.getAsString("data5");
                if (department != null) {
                    department = department.trim();
                }
                String title = contentValues.getAsString("data4");
                if (title != null) {
                    title = title.trim();
                }
                StringBuilder orgBuilder = new StringBuilder();
                if (!TextUtils.isEmpty(company)) {
                    orgBuilder.append(company);
                }
                if (!TextUtils.isEmpty(department)) {
                    if (orgBuilder.length() > 0) {
                        orgBuilder.append(Utils.WAIT);
                    }
                    orgBuilder.append(department);
                }
                String orgline = orgBuilder.toString();
                appendLine(VCardConstants.PROPERTY_ORG, orgline, !VCardUtils.containsOnlyPrintableAscii(orgline), this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(orgline));
                if (!TextUtils.isEmpty(title)) {
                    appendLine(VCardConstants.PROPERTY_TITLE, title, !VCardUtils.containsOnlyPrintableAscii(title), this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(title));
                }
            }
        }
        return this;
    }

    public VCardBuilder appendPhotos(List<ContentValues> contentValuesList) {
        byte[] data;
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                if (contentValues != null && (data = contentValues.getAsByteArray("data15")) != null) {
                    String photoType = VCardUtils.guessImageType(data);
                    if (photoType == null) {
                        Log.d(LOG_TAG, "Unknown photo type. Ignored.");
                    } else {
                        String photoString = new String(Base64.encode(data, 2));
                        if (!TextUtils.isEmpty(photoString)) {
                            appendPhotoLine(photoString, photoType);
                        }
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendNotes(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        if (contentValuesList != null) {
            boolean reallyUseQuotedPrintable = false;
            if (this.mOnlyOneNoteFieldIsAvailable) {
                StringBuilder noteBuilder = new StringBuilder();
                boolean first = true;
                for (ContentValues contentValues : contentValuesList) {
                    String note = contentValues.getAsString("data1");
                    if (note == null) {
                        note = INetd.NEXTHOP_NONE;
                    }
                    if (note.length() > 0) {
                        if (first) {
                            first = false;
                        } else {
                            noteBuilder.append('\n');
                        }
                        noteBuilder.append(note);
                    }
                }
                String noteStr = noteBuilder.toString();
                boolean shouldAppendCharsetInfo = !VCardUtils.containsOnlyPrintableAscii(noteStr);
                if (this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(noteStr)) {
                    reallyUseQuotedPrintable = true;
                }
                appendLine(VCardConstants.PROPERTY_NOTE, noteStr, shouldAppendCharsetInfo, reallyUseQuotedPrintable);
            } else {
                for (ContentValues contentValues2 : contentValuesList) {
                    String noteStr2 = contentValues2.getAsString("data1");
                    if (!TextUtils.isEmpty(noteStr2)) {
                        boolean shouldAppendCharsetInfo2 = !VCardUtils.containsOnlyPrintableAscii(noteStr2);
                        appendLine(VCardConstants.PROPERTY_NOTE, noteStr2, shouldAppendCharsetInfo2, this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(noteStr2));
                    }
                }
            }
        }
        return this;
    }

    public VCardBuilder appendEvents(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        int eventType;
        if (contentValuesList != null) {
            String primaryBirthday = null;
            String secondaryBirthday = null;
            Iterator<ContentValues> it = contentValuesList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ContentValues contentValues = it.next();
                if (contentValues != null) {
                    Integer eventTypeAsInteger = contentValues.getAsInteger("data2");
                    if (eventTypeAsInteger != null) {
                        eventType = eventTypeAsInteger.intValue();
                    } else {
                        eventType = 2;
                    }
                    if (eventType == 3) {
                        String birthdayCandidate = contentValues.getAsString("data1");
                        if (birthdayCandidate == null) {
                            continue;
                        } else {
                            Integer isSuperPrimaryAsInteger = contentValues.getAsInteger("is_super_primary");
                            boolean isSuperPrimary = isSuperPrimaryAsInteger != null && isSuperPrimaryAsInteger.intValue() > 0;
                            if (isSuperPrimary) {
                                primaryBirthday = birthdayCandidate;
                                break;
                            }
                            Integer isPrimaryAsInteger = contentValues.getAsInteger("is_primary");
                            boolean isPrimary = isPrimaryAsInteger != null && isPrimaryAsInteger.intValue() > 0;
                            if (isPrimary) {
                                primaryBirthday = birthdayCandidate;
                            } else if (secondaryBirthday == null) {
                                secondaryBirthday = birthdayCandidate;
                            }
                        }
                    } else if (this.mUsesAndroidProperty) {
                        appendAndroidSpecificProperty("vnd.android.cursor.item/contact_event", contentValues);
                    }
                }
            }
            if (primaryBirthday != null) {
                appendLineWithCharsetAndQPDetection(VCardConstants.PROPERTY_BDAY, primaryBirthday.trim());
            } else if (secondaryBirthday != null) {
                appendLineWithCharsetAndQPDetection(VCardConstants.PROPERTY_BDAY, secondaryBirthday.trim());
            }
        }
        return this;
    }

    public VCardBuilder appendRelation(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        if (this.mUsesAndroidProperty && contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                if (contentValues != null) {
                    appendAndroidSpecificProperty("vnd.android.cursor.item/relation", contentValues);
                }
            }
        }
        return this;
    }

    public void appendPostalLine(int type, String label, ContentValues contentValues, boolean isPrimary, boolean emitEveryTime) throws UnsupportedEncodingException {
        boolean reallyUseQuotedPrintable;
        boolean appendCharset;
        String addressValue;
        PostalStruct postalStruct = tryConstructPostalStruct(contentValues);
        if (postalStruct == null) {
            if (emitEveryTime) {
                reallyUseQuotedPrintable = false;
                appendCharset = false;
                addressValue = INetd.NEXTHOP_NONE;
            } else {
                return;
            }
        } else {
            reallyUseQuotedPrintable = postalStruct.reallyUseQuotedPrintable;
            appendCharset = postalStruct.appendCharset;
            addressValue = postalStruct.addressData;
        }
        List<String> parameterList = new ArrayList<>();
        if (isPrimary) {
            parameterList.add(VCardConstants.PARAM_TYPE_PREF);
        }
        if (type != 0) {
            if (type == 1) {
                parameterList.add(VCardConstants.PARAM_TYPE_HOME);
            } else if (type == 2) {
                parameterList.add(VCardConstants.PARAM_TYPE_WORK);
            } else if (type != 3) {
                Log.e(LOG_TAG, "Unknown StructuredPostal type: " + type);
            }
        } else if (!TextUtils.isEmpty(label) && VCardUtils.containsOnlyAlphaDigitHyphen(label)) {
            parameterList.add("X-" + label);
        }
        this.mBuilder.append(VCardConstants.PROPERTY_ADR);
        if (!parameterList.isEmpty()) {
            this.mBuilder.append(";");
            appendTypeParameters(parameterList);
        }
        if (appendCharset) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (reallyUseQuotedPrintable) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(addressValue);
        this.mBuilder.append(VCARD_END_OF_LINE);
    }

    public void appendEmailLine(int type, String label, String rawValue, boolean isPrimary) throws UnsupportedEncodingException {
        String typeAsString;
        if (type != 0) {
            if (type == 1) {
                typeAsString = VCardConstants.PARAM_TYPE_HOME;
            } else if (type == 2) {
                typeAsString = VCardConstants.PARAM_TYPE_WORK;
            } else if (type == 3) {
                typeAsString = null;
            } else if (type == 4) {
                typeAsString = VCardConstants.PARAM_TYPE_CELL;
            } else {
                Log.e(LOG_TAG, "Unknown Email type: " + type);
                typeAsString = null;
            }
        } else if (VCardUtils.isMobilePhoneLabel(label)) {
            typeAsString = VCardConstants.PARAM_TYPE_CELL;
        } else if (!TextUtils.isEmpty(label) && VCardUtils.containsOnlyAlphaDigitHyphen(label)) {
            typeAsString = "X-" + label;
        } else {
            typeAsString = null;
        }
        List<String> parameterList = new ArrayList<>();
        if (isPrimary) {
            parameterList.add(VCardConstants.PARAM_TYPE_PREF);
        }
        if (!TextUtils.isEmpty(typeAsString)) {
            parameterList.add(typeAsString);
        }
        appendLineWithCharsetAndQPDetection("EMAIL", parameterList, rawValue);
    }

    public void appendTelLine(Integer typeAsInteger, String label, String encodedValue, boolean isPrimary) {
        int type;
        this.mBuilder.append(VCardConstants.PROPERTY_TEL);
        this.mBuilder.append(";");
        if (typeAsInteger == null) {
            type = 7;
        } else {
            type = typeAsInteger.intValue();
        }
        ArrayList<String> parameterList = new ArrayList<>();
        switch (type) {
            case 0:
                if (TextUtils.isEmpty(label)) {
                    parameterList.add(VCardConstants.PARAM_TYPE_VOICE);
                } else if (VCardUtils.isMobilePhoneLabel(label)) {
                    parameterList.add(VCardConstants.PARAM_TYPE_CELL);
                } else if (this.mIsV30OrV40) {
                    parameterList.add(label);
                } else {
                    String upperLabel = label.toUpperCase();
                    if (VCardUtils.isValidInV21ButUnknownToContactsPhoteType(upperLabel)) {
                        parameterList.add(upperLabel);
                    } else if (VCardUtils.containsOnlyAlphaDigitHyphen(label)) {
                        parameterList.add("X-" + label);
                    }
                }
                break;
            case 1:
                parameterList.addAll(Arrays.asList(VCardConstants.PARAM_TYPE_HOME));
                break;
            case 2:
                parameterList.add(VCardConstants.PARAM_TYPE_CELL);
                break;
            case 3:
                parameterList.addAll(Arrays.asList(VCardConstants.PARAM_TYPE_WORK));
                break;
            case 4:
                parameterList.addAll(Arrays.asList(VCardConstants.PARAM_TYPE_WORK, VCardConstants.PARAM_TYPE_FAX));
                break;
            case 5:
                parameterList.addAll(Arrays.asList(VCardConstants.PARAM_TYPE_HOME, VCardConstants.PARAM_TYPE_FAX));
                break;
            case 6:
                if (this.mIsDoCoMo) {
                    parameterList.add(VCardConstants.PARAM_TYPE_VOICE);
                } else {
                    parameterList.add(VCardConstants.PARAM_TYPE_PAGER);
                }
                break;
            case 7:
                parameterList.add(VCardConstants.PARAM_TYPE_VOICE);
                break;
            case 9:
                parameterList.add(VCardConstants.PARAM_TYPE_CAR);
                break;
            case 10:
                parameterList.add(VCardConstants.PARAM_TYPE_WORK);
                isPrimary = true;
                break;
            case 11:
                parameterList.add(VCardConstants.PARAM_TYPE_ISDN);
                break;
            case 12:
                isPrimary = true;
                break;
            case 13:
                parameterList.add(VCardConstants.PARAM_TYPE_FAX);
                break;
            case 15:
                parameterList.add(VCardConstants.PARAM_TYPE_TLX);
                break;
            case 17:
                parameterList.addAll(Arrays.asList(VCardConstants.PARAM_TYPE_WORK, VCardConstants.PARAM_TYPE_CELL));
                break;
            case 18:
                parameterList.add(VCardConstants.PARAM_TYPE_WORK);
                if (this.mIsDoCoMo) {
                    parameterList.add(VCardConstants.PARAM_TYPE_VOICE);
                } else {
                    parameterList.add(VCardConstants.PARAM_TYPE_PAGER);
                }
                break;
            case 20:
                parameterList.add(VCardConstants.PARAM_TYPE_MSG);
                break;
        }
        if (isPrimary) {
            parameterList.add(VCardConstants.PARAM_TYPE_PREF);
        }
        if (parameterList.isEmpty()) {
            appendUncommonPhoneType(this.mBuilder, Integer.valueOf(type));
        } else {
            appendTypeParameters(parameterList);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(encodedValue);
        this.mBuilder.append(VCARD_END_OF_LINE);
    }

    private void appendUncommonPhoneType(StringBuilder builder, Integer type) {
        if (this.mIsDoCoMo) {
            builder.append(VCardConstants.PARAM_TYPE_VOICE);
            return;
        }
        String phoneType = VCardUtils.getPhoneTypeString(type);
        if (phoneType != null) {
            appendTypeParameter(phoneType);
            return;
        }
        Log.e(LOG_TAG, "Unknown or unsupported (by vCard) Phone type: " + type);
    }

    public void appendPhotoLine(String encodedValue, String photoType) {
        StringBuilder tmpBuilder = new StringBuilder();
        tmpBuilder.append(VCardConstants.PROPERTY_PHOTO);
        tmpBuilder.append(";");
        if (this.mIsV30OrV40) {
            tmpBuilder.append(VCARD_PARAM_ENCODING_BASE64_AS_B);
        } else {
            tmpBuilder.append(VCARD_PARAM_ENCODING_BASE64_V21);
        }
        tmpBuilder.append(";");
        appendTypeParameter(tmpBuilder, photoType);
        tmpBuilder.append(VCARD_DATA_SEPARATOR);
        tmpBuilder.append(encodedValue);
        String tmpStr = tmpBuilder.toString();
        StringBuilder tmpBuilder2 = new StringBuilder();
        int lineCount = 0;
        int length = tmpStr.length();
        int maxNumForFirstLine = 75 - VCARD_END_OF_LINE.length();
        int maxNumInGeneral = maxNumForFirstLine - VCARD_WS.length();
        int maxNum = maxNumForFirstLine;
        for (int i = 0; i < length; i++) {
            tmpBuilder2.append(tmpStr.charAt(i));
            lineCount++;
            if (lineCount > maxNum) {
                tmpBuilder2.append(VCARD_END_OF_LINE);
                tmpBuilder2.append(VCARD_WS);
                maxNum = maxNumInGeneral;
                lineCount = 0;
            }
        }
        this.mBuilder.append(tmpBuilder2.toString());
        this.mBuilder.append(VCARD_END_OF_LINE);
        this.mBuilder.append(VCARD_END_OF_LINE);
    }

    public VCardBuilder appendSipAddresses(List<ContentValues> contentValuesList) throws UnsupportedEncodingException {
        boolean useXProperty;
        String propertyName;
        if (this.mIsV30OrV40) {
            useXProperty = false;
        } else {
            boolean useXProperty2 = this.mUsesDefactProperty;
            if (useXProperty2) {
                useXProperty = true;
            } else {
                return this;
            }
        }
        if (contentValuesList != null) {
            for (ContentValues contentValues : contentValuesList) {
                String sipAddress = contentValues.getAsString("data1");
                if (!TextUtils.isEmpty(sipAddress)) {
                    if (useXProperty) {
                        if (sipAddress.startsWith("sip:")) {
                            if (sipAddress.length() != 4) {
                                sipAddress = sipAddress.substring(4);
                            }
                        }
                        appendLineWithCharsetAndQPDetection(VCardConstants.PROPERTY_X_SIP, sipAddress);
                    } else {
                        if (!sipAddress.startsWith("sip:")) {
                            sipAddress = "sip:" + sipAddress;
                        }
                        if (VCardConfig.isVersion40(this.mVCardType)) {
                            propertyName = VCardConstants.PROPERTY_TEL;
                        } else {
                            propertyName = VCardConstants.PROPERTY_IMPP;
                        }
                        appendLineWithCharsetAndQPDetection(propertyName, sipAddress);
                    }
                }
            }
        }
        return this;
    }

    public void appendAndroidSpecificProperty(String mimeType, ContentValues contentValues) throws UnsupportedEncodingException {
        String encodedValue;
        if (!sAllowedAndroidPropertySet.contains(mimeType)) {
            return;
        }
        List<String> rawValueList = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            String value = contentValues.getAsString("data" + i);
            if (value == null) {
                value = INetd.NEXTHOP_NONE;
            }
            rawValueList.add(value);
        }
        boolean reallyUseQuotedPrintable = false;
        boolean needCharset = this.mShouldAppendCharsetParam && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawValueList);
        if (this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawValueList)) {
            reallyUseQuotedPrintable = true;
        }
        this.mBuilder.append(VCardConstants.PROPERTY_X_ANDROID_CUSTOM);
        if (needCharset) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (reallyUseQuotedPrintable) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(mimeType);
        for (String rawValue : rawValueList) {
            if (reallyUseQuotedPrintable) {
                encodedValue = encodeQuotedPrintable(rawValue);
            } else {
                encodedValue = escapeCharacters(rawValue);
            }
            this.mBuilder.append(";");
            this.mBuilder.append(encodedValue);
        }
        this.mBuilder.append(VCARD_END_OF_LINE);
    }

    public void appendLineWithCharsetAndQPDetection(String propertyName, String rawValue) throws UnsupportedEncodingException {
        appendLineWithCharsetAndQPDetection(propertyName, (List<String>) null, rawValue);
    }

    public void appendLineWithCharsetAndQPDetection(String propertyName, List<String> rawValueList) throws UnsupportedEncodingException {
        appendLineWithCharsetAndQPDetection(propertyName, (List<String>) null, rawValueList);
    }

    public void appendLineWithCharsetAndQPDetection(String propertyName, List<String> parameterList, String rawValue) throws UnsupportedEncodingException {
        boolean needCharset = !VCardUtils.containsOnlyPrintableAscii(rawValue);
        boolean reallyUseQuotedPrintable = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawValue);
        appendLine(propertyName, parameterList, rawValue, needCharset, reallyUseQuotedPrintable);
    }

    public void appendLineWithCharsetAndQPDetection(String propertyName, List<String> parameterList, List<String> rawValueList) throws UnsupportedEncodingException {
        boolean needCharset = this.mShouldAppendCharsetParam && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawValueList);
        boolean reallyUseQuotedPrintable = this.mShouldUseQuotedPrintable && !VCardUtils.containsOnlyNonCrLfPrintableAscii(rawValueList);
        appendLine(propertyName, parameterList, rawValueList, needCharset, reallyUseQuotedPrintable);
    }

    public void appendLine(String propertyName, String rawValue) throws UnsupportedEncodingException {
        appendLine(propertyName, rawValue, false, false);
    }

    public void appendLine(String propertyName, List<String> rawValueList) throws UnsupportedEncodingException {
        appendLine(propertyName, rawValueList, false, false);
    }

    public void appendLine(String propertyName, String rawValue, boolean needCharset, boolean reallyUseQuotedPrintable) throws UnsupportedEncodingException {
        appendLine(propertyName, (List<String>) null, rawValue, needCharset, reallyUseQuotedPrintable);
    }

    public void appendLine(String propertyName, List<String> parameterList, String rawValue) throws UnsupportedEncodingException {
        appendLine(propertyName, parameterList, rawValue, false, false);
    }

    public void appendLine(String propertyName, List<String> parameterList, String rawValue, boolean needCharset, boolean reallyUseQuotedPrintable) throws UnsupportedEncodingException {
        String encodedValue;
        this.mBuilder.append(propertyName);
        if (parameterList != null && parameterList.size() > 0) {
            this.mBuilder.append(";");
            appendTypeParameters(parameterList);
        }
        if (needCharset) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (reallyUseQuotedPrintable) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
            encodedValue = encodeQuotedPrintable(rawValue);
        } else {
            encodedValue = escapeCharacters(rawValue);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        this.mBuilder.append(encodedValue);
        this.mBuilder.append(VCARD_END_OF_LINE);
    }

    public void appendLine(String propertyName, List<String> rawValueList, boolean needCharset, boolean needQuotedPrintable) throws UnsupportedEncodingException {
        appendLine(propertyName, (List<String>) null, rawValueList, needCharset, needQuotedPrintable);
    }

    public void appendLine(String propertyName, List<String> parameterList, List<String> rawValueList, boolean needCharset, boolean needQuotedPrintable) throws UnsupportedEncodingException {
        String encodedValue;
        this.mBuilder.append(propertyName);
        if (parameterList != null && parameterList.size() > 0) {
            this.mBuilder.append(";");
            appendTypeParameters(parameterList);
        }
        if (needCharset) {
            this.mBuilder.append(";");
            this.mBuilder.append(this.mVCardCharsetParameter);
        }
        if (needQuotedPrintable) {
            this.mBuilder.append(";");
            this.mBuilder.append(VCARD_PARAM_ENCODING_QP);
        }
        this.mBuilder.append(VCARD_DATA_SEPARATOR);
        boolean first = true;
        for (String rawValue : rawValueList) {
            if (needQuotedPrintable) {
                encodedValue = encodeQuotedPrintable(rawValue);
            } else {
                encodedValue = escapeCharacters(rawValue);
            }
            if (!first) {
                this.mBuilder.append(";");
            } else {
                first = false;
            }
            this.mBuilder.append(encodedValue);
        }
        this.mBuilder.append(VCARD_END_OF_LINE);
    }

    private void appendTypeParameters(List<String> types) {
        String encoded;
        boolean first = true;
        for (String typeValue : types) {
            if (VCardConfig.isVersion30(this.mVCardType) || VCardConfig.isVersion40(this.mVCardType)) {
                if (VCardConfig.isVersion40(this.mVCardType)) {
                    encoded = VCardUtils.toStringAsV40ParamValue(typeValue);
                } else {
                    encoded = VCardUtils.toStringAsV30ParamValue(typeValue);
                }
                if (!TextUtils.isEmpty(encoded)) {
                    if (first) {
                        first = false;
                    } else {
                        this.mBuilder.append(";");
                    }
                    appendTypeParameter(encoded);
                }
            } else if (VCardUtils.isV21Word(typeValue)) {
                if (first) {
                    first = false;
                } else {
                    this.mBuilder.append(";");
                }
                appendTypeParameter(typeValue);
            }
        }
    }

    private void appendTypeParameter(String type) {
        appendTypeParameter(this.mBuilder, type);
    }

    private void appendTypeParameter(StringBuilder builder, String type) {
        if (VCardConfig.isVersion40(this.mVCardType) || ((VCardConfig.isVersion30(this.mVCardType) || this.mAppendTypeParamName) && !this.mIsDoCoMo)) {
            builder.append(VCardConstants.PARAM_TYPE);
            builder.append(VCARD_PARAM_EQUAL);
        }
        builder.append(type);
    }

    private boolean shouldAppendCharsetParam(String... propertyValueList) {
        if (!this.mShouldAppendCharsetParam) {
            return false;
        }
        for (String propertyValue : propertyValueList) {
            if (!VCardUtils.containsOnlyPrintableAscii(propertyValue)) {
                return true;
            }
        }
        return false;
    }

    private String encodeQuotedPrintable(String str) throws UnsupportedEncodingException {
        byte[] strArray;
        if (TextUtils.isEmpty(str)) {
            return INetd.NEXTHOP_NONE;
        }
        StringBuilder builder = new StringBuilder();
        int index = 0;
        int lineCount = 0;
        try {
            strArray = str.getBytes(this.mCharset);
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "Charset " + this.mCharset + " cannot be used. Try default charset");
            strArray = str.getBytes();
        }
        while (index < strArray.length) {
            builder.append(String.format("=%02X", Byte.valueOf(strArray[index])));
            index++;
            lineCount += 3;
            if (lineCount >= 67) {
                builder.append("=\r\n");
                lineCount = 0;
            }
        }
        return builder.toString();
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
            java.lang.String r0 = ""
            return r0
        L9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r1 = r7.length()
            r2 = 0
        L13:
            if (r2 >= r1) goto L7b
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
            goto L78
        L39:
            r0.append(r5)
            r0.append(r4)
            goto L78
        L40:
            boolean r4 = r6.mIsV30OrV40
            if (r4 == 0) goto L4a
            java.lang.String r4 = "\\\\"
            r0.append(r4)
            goto L78
        L4a:
            boolean r4 = r6.mIsDoCoMo
            if (r4 == 0) goto L55
            r0.append(r5)
            r0.append(r3)
            goto L78
        L55:
            r0.append(r3)
            goto L78
        L59:
            boolean r4 = r6.mIsV30OrV40
            if (r4 == 0) goto L63
            java.lang.String r4 = "\\,"
            r0.append(r4)
            goto L78
        L63:
            r0.append(r3)
            goto L78
        L67:
            int r5 = r2 + 1
            if (r5 >= r1) goto L72
            char r5 = r7.charAt(r2)
            if (r5 != r4) goto L72
            goto L78
        L72:
            java.lang.String r4 = "\\n"
            r0.append(r4)
        L78:
            int r2 = r2 + 1
            goto L13
        L7b:
            java.lang.String r2 = r0.toString()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardBuilder.escapeCharacters(java.lang.String):java.lang.String");
    }

    public String toString() throws UnsupportedEncodingException {
        if (!this.mEndAppended) {
            if (this.mIsDoCoMo) {
                appendLine(VCardConstants.PROPERTY_X_CLASS, VCARD_DATA_PUBLIC);
                appendLine(VCardConstants.PROPERTY_X_REDUCTION, INetd.NEXTHOP_NONE);
                appendLine(VCardConstants.PROPERTY_X_NO, INetd.NEXTHOP_NONE);
                appendLine(VCardConstants.PROPERTY_X_DCM_HMN_MODE, INetd.NEXTHOP_NONE);
            }
            appendLine(VCardConstants.PROPERTY_END, VCARD_DATA_VCARD);
            this.mEndAppended = true;
        }
        return this.mBuilder.toString();
    }
}
