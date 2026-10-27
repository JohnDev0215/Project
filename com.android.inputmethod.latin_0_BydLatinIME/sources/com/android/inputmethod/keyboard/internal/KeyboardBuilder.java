package com.android.inputmethod.keyboard.internal;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.KeyboardId;
import com.android.inputmethod.keyboard.KeyboardTheme;
import com.android.inputmethod.keyboard.internal.KeyboardParams;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.StringUtils;
import com.android.inputmethod.latin.utils.ResourceUtils;
import com.android.inputmethod.latin.utils.XmlParseUtils;
import java.io.IOException;
import java.util.Locale;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class KeyboardBuilder<KP extends KeyboardParams> {
    private static final String BUILDER_TAG = "Keyboard.Builder";
    private static final boolean DEBUG = false;
    private static final int DEFAULT_KEYBOARD_COLUMNS = 10;
    private static final int DEFAULT_KEYBOARD_ROWS = 4;
    private static final String SPACES = "                                             ";
    private static final String TAG_CASE = "case";
    private static final String TAG_DEFAULT = "default";
    private static final String TAG_GRID_ROWS = "GridRows";
    private static final String TAG_INCLUDE = "include";
    private static final String TAG_KEY = "Key";
    private static final String TAG_KEYBOARD = "Keyboard";
    public static final String TAG_KEY_STYLE = "key-style";
    private static final String TAG_MERGE = "merge";
    private static final String TAG_ROW = "Row";
    private static final String TAG_SPACER = "Spacer";
    private static final String TAG_SWITCH = "switch";
    protected final Context mContext;
    private int mIndent;
    private boolean mLeftEdge;

    @Nonnull
    protected final KP mParams;
    protected final Resources mResources;
    private boolean mTopEdge;
    private int mCurrentY = 0;
    private KeyboardRow mCurrentRow = null;
    private Key mRightEdgeKey = null;

    public KeyboardBuilder(final Context context, @Nonnull final KP params) {
        this.mContext = context;
        Resources resources = context.getResources();
        this.mResources = resources;
        this.mParams = params;
        params.GRID_WIDTH = resources.getInteger(R.integer.config_keyboard_grid_width);
        params.GRID_HEIGHT = resources.getInteger(R.integer.config_keyboard_grid_height);
    }

    public void setAllowRedundantMoreKes(final boolean enabled) {
        this.mParams.mAllowRedundantMoreKeys = enabled;
    }

    public KeyboardBuilder<KP> load(final int xmlId, final KeyboardId id) {
        this.mParams.mId = id;
        XmlResourceParser xml = this.mResources.getXml(xmlId);
        try {
            try {
                parseKeyboard(xml);
                return this;
            } catch (IOException e) {
                Log.w(BUILDER_TAG, "keyboard XML parse error", e);
                throw new RuntimeException(e.getMessage(), e);
            } catch (XmlPullParserException e2) {
                Log.w(BUILDER_TAG, "keyboard XML parse error", e2);
                throw new IllegalArgumentException(e2.getMessage(), e2);
            }
        } finally {
            xml.close();
        }
    }

    public void disableTouchPositionCorrectionDataForTest() {
        this.mParams.mTouchPositionCorrection.setEnabled(false);
    }

    public void setProximityCharsCorrectionEnabled(final boolean enabled) {
        this.mParams.mProximityCharsCorrectionEnabled = enabled;
    }

    @Nonnull
    public Keyboard build() {
        return new Keyboard(this.mParams);
    }

    private static String spaces(final int count) {
        return count < 45 ? SPACES.substring(0, count) : SPACES;
    }

    private void startTag(final String format, final Object... args) {
        StringBuilder sb = new StringBuilder();
        int i = this.mIndent + 1;
        this.mIndent = i;
        Log.d(BUILDER_TAG, String.format(sb.append(spaces(i * 2)).append(format).toString(), args));
    }

    private void endTag(final String format, final Object... args) {
        StringBuilder sb = new StringBuilder();
        int i = this.mIndent;
        this.mIndent = i - 1;
        Log.d(BUILDER_TAG, String.format(sb.append(spaces(i * 2)).append(format).toString(), args));
    }

    private void startEndTag(final String format, final Object... args) {
        StringBuilder sb = new StringBuilder();
        int i = this.mIndent + 1;
        this.mIndent = i;
        Log.d(BUILDER_TAG, String.format(sb.append(spaces(i * 2)).append(format).toString(), args));
        this.mIndent--;
    }

    private void parseKeyboard(final XmlPullParser parser) throws XmlPullParserException, IOException {
        while (parser.getEventType() != 1) {
            if (parser.next() == 2) {
                String name = parser.getName();
                if ("Keyboard".equals(name)) {
                    parseKeyboardAttributes(parser);
                    startKeyboard();
                    parseKeyboardContent(parser, false);
                    return;
                }
                throw new XmlParseUtils.IllegalStartTag(parser, name, "Keyboard");
            }
        }
    }

    private void parseKeyboardAttributes(final XmlPullParser parser) {
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(parser);
        TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSetAsAttributeSet, R.styleable.Keyboard, R.attr.keyboardStyle, R.style.Keyboard);
        TypedArray typedArrayObtainAttributes = this.mResources.obtainAttributes(attributeSetAsAttributeSet, R.styleable.Keyboard_Key);
        try {
            KP kp = this.mParams;
            int i = kp.mId.mHeight;
            int i2 = kp.mId.mWidth;
            kp.mOccupiedHeight = i;
            kp.mOccupiedWidth = i2;
            kp.mTopPadding = (int) typedArrayObtainStyledAttributes.getFraction(26, i, i, 0.0f);
            kp.mBottomPadding = (int) typedArrayObtainStyledAttributes.getFraction(23, i, i, 0.0f);
            kp.mLeftPadding = (int) typedArrayObtainStyledAttributes.getFraction(24, i2, i2, 0.0f);
            kp.mRightPadding = (int) typedArrayObtainStyledAttributes.getFraction(25, i2, i2, 0.0f);
            int i3 = (kp.mOccupiedWidth - kp.mLeftPadding) - kp.mRightPadding;
            kp.mBaseWidth = i3;
            kp.mDefaultKeyWidth = (int) typedArrayObtainAttributes.getFraction(29, i3, i3, i3 / 10);
            kp.mHorizontalGap = (int) typedArrayObtainStyledAttributes.getFraction(0, i3, i3, 0.0f);
            kp.mVerticalGap = (int) typedArrayObtainStyledAttributes.getFraction(31, i, i, 0.0f);
            int i4 = ((kp.mOccupiedHeight - kp.mTopPadding) - kp.mBottomPadding) + kp.mVerticalGap;
            kp.mBaseHeight = i4;
            kp.mDefaultRowHeight = (int) ResourceUtils.getDimensionOrFraction(typedArrayObtainStyledAttributes, 28, i4, i4 / 4);
            kp.mKeyVisualAttributes = KeyVisualAttributes.newInstance(typedArrayObtainAttributes);
            kp.mMoreKeysTemplate = typedArrayObtainStyledAttributes.getResourceId(27, 0);
            kp.mMaxMoreKeysKeyboardColumn = typedArrayObtainAttributes.getInt(31, 5);
            kp.mThemeId = typedArrayObtainStyledAttributes.getInt(29, 0);
            kp.mIconsSet.loadIcons(typedArrayObtainStyledAttributes);
            kp.mTextsSet.setLocale(kp.mId.getLocale(), this.mContext);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(30, 0);
            if (resourceId != 0) {
                kp.mTouchPositionCorrection.load(this.mResources.getStringArray(resourceId));
            }
        } finally {
            typedArrayObtainAttributes.recycle();
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void parseKeyboardContent(final XmlPullParser parser, final boolean skip) throws XmlPullParserException, IOException {
        while (parser.getEventType() != 1) {
            int next = parser.next();
            if (next == 2) {
                String name = parser.getName();
                if (TAG_ROW.equals(name)) {
                    KeyboardRow rowAttributes = parseRowAttributes(parser);
                    if (!skip) {
                        startRow(rowAttributes);
                    }
                    parseRowContent(parser, rowAttributes, skip);
                } else if (TAG_GRID_ROWS.equals(name)) {
                    parseGridRows(parser, skip);
                } else if (TAG_INCLUDE.equals(name)) {
                    parseIncludeKeyboardContent(parser, skip);
                } else if (TAG_SWITCH.equals(name)) {
                    parseSwitchKeyboardContent(parser, skip);
                } else if (TAG_KEY_STYLE.equals(name)) {
                    parseKeyStyle(parser, skip);
                } else {
                    throw new XmlParseUtils.IllegalStartTag(parser, name, TAG_ROW);
                }
            } else if (next == 3) {
                String name2 = parser.getName();
                if ("Keyboard".equals(name2)) {
                    endKeyboard();
                    return;
                } else {
                    if (!TAG_CASE.equals(name2) && !TAG_DEFAULT.equals(name2) && !TAG_MERGE.equals(name2)) {
                        throw new XmlParseUtils.IllegalEndTag(parser, name2, TAG_ROW);
                    }
                    return;
                }
            }
        }
    }

    private KeyboardRow parseRowAttributes(final XmlPullParser parser) throws XmlPullParserException {
        TypedArray typedArrayObtainAttributes = this.mResources.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard);
        try {
            if (typedArrayObtainAttributes.hasValue(0)) {
                throw new XmlParseUtils.IllegalAttribute(parser, TAG_ROW, "horizontalGap");
            }
            if (typedArrayObtainAttributes.hasValue(31)) {
                throw new XmlParseUtils.IllegalAttribute(parser, TAG_ROW, "verticalGap");
            }
            return new KeyboardRow(this.mResources, this.mParams, parser, this.mCurrentY);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private void parseRowContent(final XmlPullParser parser, final KeyboardRow row, final boolean skip) throws XmlPullParserException, IOException {
        while (parser.getEventType() != 1) {
            int next = parser.next();
            if (next == 2) {
                String name = parser.getName();
                if (TAG_KEY.equals(name)) {
                    parseKey(parser, row, skip);
                } else if (TAG_SPACER.equals(name)) {
                    parseSpacer(parser, row, skip);
                } else if (TAG_INCLUDE.equals(name)) {
                    parseIncludeRowContent(parser, row, skip);
                } else if (TAG_SWITCH.equals(name)) {
                    parseSwitchRowContent(parser, row, skip);
                } else if (TAG_KEY_STYLE.equals(name)) {
                    parseKeyStyle(parser, skip);
                } else {
                    throw new XmlParseUtils.IllegalStartTag(parser, name, TAG_ROW);
                }
            } else if (next == 3) {
                String name2 = parser.getName();
                if (TAG_ROW.equals(name2)) {
                    if (skip) {
                        return;
                    }
                    endRow(row);
                    return;
                } else {
                    if (!TAG_CASE.equals(name2) && !TAG_DEFAULT.equals(name2) && !TAG_MERGE.equals(name2)) {
                        throw new XmlParseUtils.IllegalEndTag(parser, name2, TAG_ROW);
                    }
                    return;
                }
            }
        }
    }

    private void parseGridRows(final XmlPullParser parser, final boolean skip) throws XmlPullParserException, IOException {
        String label;
        int i;
        String str;
        int minSupportSdkVersion;
        String[] strArr;
        if (skip) {
            XmlParseUtils.checkEndTag(TAG_GRID_ROWS, parser);
            return;
        }
        KeyboardRow keyboardRow = new KeyboardRow(this.mResources, this.mParams, parser, this.mCurrentY);
        TypedArray typedArrayObtainAttributes = this.mResources.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard_GridRows);
        int i2 = 0;
        int resourceId = typedArrayObtainAttributes.getResourceId(0, 0);
        int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
        typedArrayObtainAttributes.recycle();
        if (resourceId == 0 && resourceId2 == 0) {
            throw new XmlParseUtils.ParseException("Missing codesArray or textsArray attributes", parser);
        }
        if (resourceId != 0 && resourceId2 != 0) {
            throw new XmlParseUtils.ParseException("Both codesArray and textsArray attributes specifed", parser);
        }
        Resources resources = this.mResources;
        if (resourceId != 0) {
            resourceId2 = resourceId;
        }
        String[] stringArray = resources.getStringArray(resourceId2);
        int length = stringArray.length;
        TypedArray typedArray = null;
        float keyWidth = keyboardRow.getKeyWidth(null, 0.0f);
        int i3 = (int) (this.mParams.mOccupiedWidth / keyWidth);
        int i4 = 0;
        while (i4 < length) {
            KeyboardRow keyboardRow2 = new KeyboardRow(this.mResources, this.mParams, parser, this.mCurrentY);
            startRow(keyboardRow2);
            int i5 = i2;
            while (i5 < i3) {
                int i6 = i4 + i5;
                if (i6 >= length) {
                    break;
                }
                if (resourceId != 0) {
                    String str2 = stringArray[i6];
                    label = CodesArrayParser.parseLabel(str2);
                    int code = CodesArrayParser.parseCode(str2);
                    String outputText = CodesArrayParser.parseOutputText(str2);
                    minSupportSdkVersion = CodesArrayParser.getMinSupportSdkVersion(str2);
                    i = code;
                    str = outputText;
                } else {
                    label = stringArray[i6];
                    i = -4;
                    str = label + ' ';
                    minSupportSdkVersion = 0;
                }
                String str3 = label;
                if (Build.VERSION.SDK_INT < minSupportSdkVersion) {
                    strArr = stringArray;
                } else {
                    strArr = stringArray;
                    endKey(new Key(str3, 0, i, str, null, keyboardRow2.getDefaultKeyLabelFlags(), keyboardRow2.getDefaultBackgroundType(), (int) keyboardRow2.getKeyX(typedArray), keyboardRow2.getKeyY(), (int) keyWidth, keyboardRow2.getRowHeight(), this.mParams.mHorizontalGap, this.mParams.mVerticalGap));
                    keyboardRow2.advanceXPos(keyWidth);
                }
                i5++;
                stringArray = strArr;
                typedArray = null;
            }
            endRow(keyboardRow2);
            i4 += i3;
            stringArray = stringArray;
            i2 = 0;
            typedArray = null;
        }
        XmlParseUtils.checkEndTag(TAG_GRID_ROWS, parser);
    }

    private void parseKey(final XmlPullParser parser, final KeyboardRow row, final boolean skip) throws XmlPullParserException, IOException {
        if (skip) {
            XmlParseUtils.checkEndTag(TAG_KEY, parser);
            return;
        }
        TypedArray typedArrayObtainAttributes = this.mResources.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard_Key);
        KeyStyle keyStyle = this.mParams.mKeyStyles.getKeyStyle(typedArrayObtainAttributes, parser);
        String string = keyStyle.getString(typedArrayObtainAttributes, 23);
        if (TextUtils.isEmpty(string)) {
            throw new XmlParseUtils.ParseException("Empty keySpec", parser);
        }
        Key key = new Key(string, typedArrayObtainAttributes, keyStyle, this.mParams, row);
        typedArrayObtainAttributes.recycle();
        XmlParseUtils.checkEndTag(TAG_KEY, parser);
        endKey(key);
    }

    private void parseSpacer(final XmlPullParser parser, final KeyboardRow row, final boolean skip) throws XmlPullParserException, IOException {
        if (skip) {
            XmlParseUtils.checkEndTag(TAG_SPACER, parser);
            return;
        }
        TypedArray typedArrayObtainAttributes = this.mResources.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard_Key);
        Key.Spacer spacer = new Key.Spacer(typedArrayObtainAttributes, this.mParams.mKeyStyles.getKeyStyle(typedArrayObtainAttributes, parser), this.mParams, row);
        typedArrayObtainAttributes.recycle();
        XmlParseUtils.checkEndTag(TAG_SPACER, parser);
        endKey(spacer);
    }

    private void parseIncludeKeyboardContent(final XmlPullParser parser, final boolean skip) throws XmlPullParserException, IOException {
        parseIncludeInternal(parser, null, skip);
    }

    private void parseIncludeRowContent(final XmlPullParser parser, final KeyboardRow row, final boolean skip) throws XmlPullParserException, IOException {
        parseIncludeInternal(parser, row, skip);
    }

    private void parseIncludeInternal(final XmlPullParser parser, final KeyboardRow row, final boolean skip) throws XmlPullParserException, IOException {
        if (skip) {
            XmlParseUtils.checkEndTag(TAG_INCLUDE, parser);
            return;
        }
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(parser);
        TypedArray typedArrayObtainAttributes = this.mResources.obtainAttributes(attributeSetAsAttributeSet, R.styleable.Keyboard_Include);
        TypedArray typedArrayObtainAttributes2 = this.mResources.obtainAttributes(attributeSetAsAttributeSet, R.styleable.Keyboard_Key);
        try {
            XmlParseUtils.checkAttributeExists(typedArrayObtainAttributes, 0, "keyboardLayout", TAG_INCLUDE, parser);
            int resourceId = typedArrayObtainAttributes.getResourceId(0, 0);
            if (row != null) {
                row.setXPos(row.getKeyX(typedArrayObtainAttributes2));
                row.pushRowAttributes(typedArrayObtainAttributes2);
            }
            typedArrayObtainAttributes.recycle();
            typedArrayObtainAttributes2.recycle();
            XmlParseUtils.checkEndTag(TAG_INCLUDE, parser);
            XmlResourceParser xml = this.mResources.getXml(resourceId);
            try {
                parseMerge(xml, row, skip);
            } finally {
                if (row != null) {
                    row.popRowAttributes();
                }
                xml.close();
            }
        } catch (Throwable th) {
            typedArrayObtainAttributes.recycle();
            typedArrayObtainAttributes2.recycle();
            throw th;
        }
    }

    private void parseMerge(final XmlPullParser parser, final KeyboardRow row, final boolean skip) throws XmlPullParserException, IOException {
        while (parser.getEventType() != 1) {
            if (parser.next() == 2) {
                if (!TAG_MERGE.equals(parser.getName())) {
                    throw new XmlParseUtils.ParseException("Included keyboard layout must have <merge> root element", parser);
                }
                if (row == null) {
                    parseKeyboardContent(parser, skip);
                    return;
                } else {
                    parseRowContent(parser, row, skip);
                    return;
                }
            }
        }
    }

    private void parseSwitchKeyboardContent(final XmlPullParser parser, final boolean skip) throws XmlPullParserException, IOException {
        parseSwitchInternal(parser, null, skip);
    }

    private void parseSwitchRowContent(final XmlPullParser parser, final KeyboardRow row, final boolean skip) throws XmlPullParserException, IOException {
        parseSwitchInternal(parser, row, skip);
    }

    private void parseSwitchInternal(final XmlPullParser parser, final KeyboardRow row, final boolean skip) throws XmlPullParserException, IOException {
        boolean z;
        boolean z2 = false;
        while (true) {
            if (parser.getEventType() == 1) {
                return;
            }
            int next = parser.next();
            if (next == 2) {
                String name = parser.getName();
                if (TAG_CASE.equals(name)) {
                    z = parseCase(parser, row, z2 ? true : skip);
                } else if (TAG_DEFAULT.equals(name)) {
                    z = parseDefault(parser, row, z2 ? true : skip);
                } else {
                    throw new XmlParseUtils.IllegalStartTag(parser, name, TAG_SWITCH);
                }
                z2 |= z;
            } else if (next == 3) {
                String name2 = parser.getName();
                if (!TAG_SWITCH.equals(name2)) {
                    throw new XmlParseUtils.IllegalEndTag(parser, name2, TAG_SWITCH);
                }
                return;
            }
        }
    }

    private boolean parseCase(final XmlPullParser parser, final KeyboardRow row, final boolean skip) throws XmlPullParserException, IOException {
        boolean caseCondition = parseCaseCondition(parser);
        if (row == null) {
            if (!caseCondition) {
                skip = true;
            }
            parseKeyboardContent(parser, skip);
        } else {
            if (!caseCondition) {
                skip = true;
            }
            parseRowContent(parser, row, skip);
        }
        return caseCondition;
    }

    private boolean parseCaseCondition(final XmlPullParser parser) {
        KeyboardId keyboardId = this.mParams.mId;
        if (keyboardId == null) {
            return true;
        }
        TypedArray typedArrayObtainAttributes = this.mResources.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard_Case);
        try {
            boolean zMatchString = matchString(typedArrayObtainAttributes, 7, keyboardId.mSubtype.getKeyboardLayoutSetName());
            boolean zMatchTypedValue = matchTypedValue(typedArrayObtainAttributes, 8, keyboardId.mElementId, KeyboardId.elementIdToName(keyboardId.mElementId));
            boolean zMatchTypedValue2 = matchTypedValue(typedArrayObtainAttributes, 9, this.mParams.mThemeId, KeyboardTheme.getKeyboardThemeName(this.mParams.mThemeId));
            boolean zMatchTypedValue3 = matchTypedValue(typedArrayObtainAttributes, 13, keyboardId.mMode, KeyboardId.modeName(keyboardId.mMode));
            boolean zMatchBoolean = matchBoolean(typedArrayObtainAttributes, 14, keyboardId.navigateNext());
            boolean zMatchBoolean2 = matchBoolean(typedArrayObtainAttributes, 15, keyboardId.navigatePrevious());
            boolean zMatchBoolean3 = matchBoolean(typedArrayObtainAttributes, 16, keyboardId.passwordInput());
            boolean zMatchBoolean4 = matchBoolean(typedArrayObtainAttributes, 0, keyboardId.mClobberSettingsKey);
            boolean zMatchBoolean5 = matchBoolean(typedArrayObtainAttributes, 2, keyboardId.mHasShortcutKey);
            boolean zMatchBoolean6 = matchBoolean(typedArrayObtainAttributes, 11, keyboardId.mLanguageSwitchKeyEnabled);
            boolean zMatchBoolean7 = matchBoolean(typedArrayObtainAttributes, 5, keyboardId.isMultiLine());
            boolean zMatchInteger = matchInteger(typedArrayObtainAttributes, 3, keyboardId.imeAction());
            boolean zIsIconDefined = isIconDefined(typedArrayObtainAttributes, 4, this.mParams.mIconsSet);
            Locale locale = keyboardId.getLocale();
            return zMatchString && zMatchTypedValue && zMatchTypedValue2 && zMatchTypedValue3 && zMatchBoolean && zMatchBoolean2 && zMatchBoolean3 && zMatchBoolean4 && zMatchBoolean5 && zMatchBoolean6 && zMatchBoolean7 && zMatchInteger && zIsIconDefined && matchLocaleCodes(typedArrayObtainAttributes, locale) && matchLanguageCodes(typedArrayObtainAttributes, locale) && matchCountryCodes(typedArrayObtainAttributes, locale) && matchBoolean(typedArrayObtainAttributes, 6, keyboardId.mIsSplitLayout);
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    private static boolean matchLocaleCodes(TypedArray caseAttr, final Locale locale) {
        return matchString(caseAttr, 12, locale.toString());
    }

    private static boolean matchLanguageCodes(TypedArray caseAttr, Locale locale) {
        return matchString(caseAttr, 10, locale.getLanguage());
    }

    private static boolean matchCountryCodes(TypedArray caseAttr, Locale locale) {
        return matchString(caseAttr, 1, locale.getCountry());
    }

    private static boolean matchInteger(final TypedArray a, final int index, final int value) {
        return !a.hasValue(index) || a.getInt(index, 0) == value;
    }

    private static boolean matchBoolean(final TypedArray a, final int index, final boolean value) {
        return !a.hasValue(index) || a.getBoolean(index, false) == value;
    }

    private static boolean matchString(final TypedArray a, final int index, final String value) {
        return !a.hasValue(index) || StringUtils.containsInArray(value, a.getString(index).split("\\|"));
    }

    private static boolean matchTypedValue(final TypedArray a, final int index, final int intValue, final String strValue) {
        TypedValue typedValuePeekValue = a.peekValue(index);
        if (typedValuePeekValue == null) {
            return true;
        }
        if (ResourceUtils.isIntegerValue(typedValuePeekValue)) {
            return intValue == a.getInt(index, 0);
        }
        if (ResourceUtils.isStringValue(typedValuePeekValue)) {
            return StringUtils.containsInArray(strValue, a.getString(index).split("\\|"));
        }
        return false;
    }

    private static boolean isIconDefined(final TypedArray a, final int index, final KeyboardIconsSet iconsSet) {
        return (a.hasValue(index) && iconsSet.getIconDrawable(KeyboardIconsSet.getIconId(a.getString(index))) == null) ? false : true;
    }

    private boolean parseDefault(final XmlPullParser parser, final KeyboardRow row, final boolean skip) throws XmlPullParserException, IOException {
        if (row == null) {
            parseKeyboardContent(parser, skip);
            return true;
        }
        parseRowContent(parser, row, skip);
        return true;
    }

    private void parseKeyStyle(final XmlPullParser parser, final boolean skip) throws XmlPullParserException, IOException {
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(parser);
        TypedArray typedArrayObtainAttributes = this.mResources.obtainAttributes(attributeSetAsAttributeSet, R.styleable.Keyboard_KeyStyle);
        TypedArray typedArrayObtainAttributes2 = this.mResources.obtainAttributes(attributeSetAsAttributeSet, R.styleable.Keyboard_Key);
        try {
            if (!typedArrayObtainAttributes.hasValue(1)) {
                throw new XmlParseUtils.ParseException("<key-style/> needs styleName attribute", parser);
            }
            if (!skip) {
                this.mParams.mKeyStyles.parseKeyStyleAttributes(typedArrayObtainAttributes, typedArrayObtainAttributes2, parser);
            }
            typedArrayObtainAttributes.recycle();
            typedArrayObtainAttributes2.recycle();
            XmlParseUtils.checkEndTag(TAG_KEY_STYLE, parser);
        } catch (Throwable th) {
            typedArrayObtainAttributes.recycle();
            typedArrayObtainAttributes2.recycle();
            throw th;
        }
    }

    private void startKeyboard() {
        this.mCurrentY += this.mParams.mTopPadding;
        this.mTopEdge = true;
    }

    private void startRow(final KeyboardRow row) {
        addEdgeSpace(this.mParams.mLeftPadding, row);
        this.mCurrentRow = row;
        this.mLeftEdge = true;
        this.mRightEdgeKey = null;
    }

    private void endRow(final KeyboardRow row) {
        if (this.mCurrentRow == null) {
            throw new RuntimeException("orphan end row tag");
        }
        Key key = this.mRightEdgeKey;
        if (key != null) {
            key.markAsRightEdge(this.mParams);
            this.mRightEdgeKey = null;
        }
        addEdgeSpace(this.mParams.mRightPadding, row);
        this.mCurrentY += row.getRowHeight();
        this.mCurrentRow = null;
        this.mTopEdge = false;
    }

    private void endKey(@Nonnull final Key key) {
        this.mParams.onAddKey(key);
        if (this.mLeftEdge) {
            key.markAsLeftEdge(this.mParams);
            this.mLeftEdge = false;
        }
        if (this.mTopEdge) {
            key.markAsTopEdge(this.mParams);
        }
        this.mRightEdgeKey = key;
    }

    private void endKeyboard() {
        this.mParams.removeRedundantMoreKeys();
        int i = (this.mCurrentY - this.mParams.mVerticalGap) + this.mParams.mBottomPadding;
        KP kp = this.mParams;
        kp.mOccupiedHeight = Math.max(kp.mOccupiedHeight, i);
    }

    private void addEdgeSpace(final float width, final KeyboardRow row) {
        row.advanceXPos(width);
        this.mLeftEdge = false;
        this.mRightEdgeKey = null;
    }

    private static String textAttr(final String value, final String name) {
        return value != null ? String.format(" %s=%s", name, value) : "";
    }

    private static String booleanAttr(final TypedArray a, final int index, final String name) {
        return a.hasValue(index) ? String.format(" %s=%s", name, Boolean.valueOf(a.getBoolean(index, false))) : "";
    }
}
