package com.android.inputmethod.keyboard;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodSubtype;
import com.android.inputmethod.compat.EditorInfoCompatUtils;
import com.android.inputmethod.compat.InputMethodSubtypeCompatUtils;
import com.android.inputmethod.compat.UserManagerCompatUtils;
import com.android.inputmethod.keyboard.internal.KeyboardBuilder;
import com.android.inputmethod.keyboard.internal.KeyboardParams;
import com.android.inputmethod.keyboard.internal.UniqueKeysCache;
import com.android.inputmethod.latin.InputAttributes;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.RichInputMethodSubtype;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.utils.InputTypeUtils;
import com.android.inputmethod.latin.utils.SubtypeLocaleUtils;
import com.android.inputmethod.latin.utils.XmlParseUtils;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyboardLayoutSet {
    private static final boolean DEBUG_CACHE = false;
    private static final int FORCIBLE_CACHE_SIZE = 4;
    private static final String KEYBOARD_LAYOUT_SET_RESOURCE_PREFIX = "keyboard_layout_set_";
    private static final String TAG = "KeyboardLayoutSet";
    private static final String TAG_ELEMENT = "Element";
    private static final String TAG_FEATURE = "Feature";
    private static final String TAG_KEYBOARD_SET = "KeyboardLayoutSet";
    private final Context mContext;

    @Nonnull
    private final Params mParams;
    private static final Keyboard[] sForcibleKeyboardCache = new Keyboard[4];
    private static final HashMap<KeyboardId, SoftReference<Keyboard>> sKeyboardCache = new HashMap<>();

    @Nonnull
    private static final UniqueKeysCache sUniqueKeysCache = UniqueKeysCache.newInstance();
    private static final HashMap<InputMethodSubtype, Integer> sScriptIdsForSubtypes = new HashMap<>();

    private static final class ElementParams {
        boolean mAllowRedundantMoreKeys;
        int mKeyboardXmlId;
        boolean mProximityCharsCorrectionEnabled;
        boolean mSupportsSplitLayout;
    }

    public static final class Params {
        boolean mDisableTouchPositionCorrectionDataForTest;
        EditorInfo mEditorInfo;
        boolean mIsPasswordField;
        boolean mIsSpellChecker;
        boolean mIsSplitLayoutEnabled;
        boolean mIsSplitLayoutEnabledByUser;
        int mKeyboardHeight;
        String mKeyboardLayoutSetName;
        int mKeyboardWidth;
        boolean mLanguageSwitchKeyEnabled;
        int mMode;
        boolean mNoSettingsKey;
        RichInputMethodSubtype mSubtype;
        boolean mVoiceInputKeyEnabled;
        int mScriptId = 11;
        final SparseArray<ElementParams> mKeyboardLayoutSetElementIdToParamsMap = new SparseArray<>();
    }

    public static final class KeyboardLayoutSetException extends RuntimeException {
        public final KeyboardId mKeyboardId;

        public KeyboardLayoutSetException(final Throwable cause, final KeyboardId keyboardId) {
            super(cause);
            this.mKeyboardId = keyboardId;
        }
    }

    public static void onSystemLocaleChanged() {
        clearKeyboardCache();
    }

    public static void onKeyboardThemeChanged() {
        clearKeyboardCache();
    }

    private static void clearKeyboardCache() {
        sKeyboardCache.clear();
        sUniqueKeysCache.clear();
    }

    public static int getScriptId(final Resources resources, @Nonnull final InputMethodSubtype subtype) {
        HashMap<InputMethodSubtype, Integer> map = sScriptIdsForSubtypes;
        Integer num = map.get(subtype);
        if (num == null) {
            int scriptId = Builder.readScriptId(resources, subtype);
            map.put(subtype, Integer.valueOf(scriptId));
            return scriptId;
        }
        return num.intValue();
    }

    KeyboardLayoutSet(final Context context, @Nonnull final Params params) {
        this.mContext = context;
        this.mParams = params;
    }

    @Nonnull
    public Keyboard getKeyboard(final int baseKeyboardLayoutSetElementId) {
        switch (this.mParams.mMode) {
            case 4:
                baseKeyboardLayoutSetElementId = baseKeyboardLayoutSetElementId != 5 ? 7 : 8;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                baseKeyboardLayoutSetElementId = 9;
                break;
        }
        ElementParams elementParams = this.mParams.mKeyboardLayoutSetElementIdToParamsMap.get(baseKeyboardLayoutSetElementId);
        boolean z = false;
        if (elementParams == null) {
            elementParams = this.mParams.mKeyboardLayoutSetElementIdToParamsMap.get(0);
        }
        Params params = this.mParams;
        if (params.mIsSplitLayoutEnabledByUser && elementParams.mSupportsSplitLayout) {
            z = true;
        }
        params.mIsSplitLayoutEnabled = z;
        KeyboardId keyboardId = new KeyboardId(baseKeyboardLayoutSetElementId, this.mParams);
        try {
            return getKeyboard(elementParams, keyboardId);
        } catch (RuntimeException e) {
            Log.e(TAG, "Can't create keyboard: " + keyboardId, e);
            throw new KeyboardLayoutSetException(e, keyboardId);
        }
    }

    @Nonnull
    private Keyboard getKeyboard(final ElementParams elementParams, final KeyboardId id) {
        HashMap<KeyboardId, SoftReference<Keyboard>> map = sKeyboardCache;
        SoftReference<Keyboard> softReference = map.get(id);
        Keyboard keyboard = softReference == null ? null : softReference.get();
        if (keyboard != null) {
            return keyboard;
        }
        Context context = this.mContext;
        UniqueKeysCache uniqueKeysCache = sUniqueKeysCache;
        KeyboardBuilder keyboardBuilder = new KeyboardBuilder(context, new KeyboardParams(uniqueKeysCache));
        uniqueKeysCache.setEnabled(id.isAlphabetKeyboard());
        keyboardBuilder.setAllowRedundantMoreKes(elementParams.mAllowRedundantMoreKeys);
        keyboardBuilder.load(elementParams.mKeyboardXmlId, id);
        if (this.mParams.mDisableTouchPositionCorrectionDataForTest) {
            keyboardBuilder.disableTouchPositionCorrectionDataForTest();
        }
        keyboardBuilder.setProximityCharsCorrectionEnabled(elementParams.mProximityCharsCorrectionEnabled);
        Keyboard keyboardBuild = keyboardBuilder.build();
        map.put(id, new SoftReference<>(keyboardBuild));
        if ((id.mElementId == 0 || id.mElementId == 2) && !this.mParams.mIsSpellChecker) {
            for (int length = sForcibleKeyboardCache.length - 1; length >= 1; length--) {
                Keyboard[] keyboardArr = sForcibleKeyboardCache;
                keyboardArr[length] = keyboardArr[length - 1];
            }
            sForcibleKeyboardCache[0] = keyboardBuild;
        }
        return keyboardBuild;
    }

    public int getScriptId() {
        return this.mParams.mScriptId;
    }

    public static final class Builder {
        private static final EditorInfo EMPTY_EDITOR_INFO = new EditorInfo();
        private final Context mContext;
        private final String mPackageName;
        private final Params mParams;
        private final Resources mResources;

        public Builder(final Context context, @Nullable final EditorInfo ei) {
            Params params = new Params();
            this.mParams = params;
            this.mContext = context;
            String packageName = context.getPackageName();
            this.mPackageName = packageName;
            this.mResources = context.getResources();
            ei = ei == null ? EMPTY_EDITOR_INFO : ei;
            params.mMode = getKeyboardMode(ei);
            params.mEditorInfo = ei;
            params.mIsPasswordField = InputTypeUtils.isPasswordInputType(ei.inputType);
            params.mNoSettingsKey = InputAttributes.inPrivateImeOptions(packageName, Constants.ImeOption.NO_SETTINGS_KEY, ei);
            if (UserManagerCompatUtils.getUserLockState(context) == 2) {
                params.mNoSettingsKey = true;
            }
        }

        public Builder setKeyboardGeometry(final int keyboardWidth, final int keyboardHeight) {
            this.mParams.mKeyboardWidth = keyboardWidth;
            this.mParams.mKeyboardHeight = keyboardHeight;
            return this;
        }

        public Builder setSubtype(@Nonnull final RichInputMethodSubtype subtype) {
            boolean zIsAsciiCapable = InputMethodSubtypeCompatUtils.isAsciiCapable(subtype);
            if ((EditorInfoCompatUtils.hasFlagForceAscii(this.mParams.mEditorInfo.imeOptions) || InputAttributes.inPrivateImeOptions(this.mPackageName, Constants.ImeOption.FORCE_ASCII, this.mParams.mEditorInfo)) && !zIsAsciiCapable) {
                subtype = RichInputMethodSubtype.getNoLanguageSubtype();
            }
            this.mParams.mSubtype = subtype;
            this.mParams.mKeyboardLayoutSetName = KeyboardLayoutSet.KEYBOARD_LAYOUT_SET_RESOURCE_PREFIX + subtype.getKeyboardLayoutSetName();
            return this;
        }

        public Builder setIsSpellChecker(final boolean isSpellChecker) {
            this.mParams.mIsSpellChecker = isSpellChecker;
            return this;
        }

        public Builder setVoiceInputKeyEnabled(final boolean enabled) {
            this.mParams.mVoiceInputKeyEnabled = enabled;
            return this;
        }

        public Builder setLanguageSwitchKeyEnabled(final boolean enabled) {
            this.mParams.mLanguageSwitchKeyEnabled = enabled;
            return this;
        }

        public Builder disableTouchPositionCorrectionData() {
            this.mParams.mDisableTouchPositionCorrectionDataForTest = true;
            return this;
        }

        public Builder setSplitLayoutEnabledByUser(final boolean enabled) {
            this.mParams.mIsSplitLayoutEnabledByUser = enabled;
            return this;
        }

        static int readScriptId(final Resources resources, final InputMethodSubtype subtype) {
            String str = KeyboardLayoutSet.KEYBOARD_LAYOUT_SET_RESOURCE_PREFIX + SubtypeLocaleUtils.getKeyboardLayoutSetName(subtype);
            XmlResourceParser xml = resources.getXml(getXmlId(resources, str));
            while (xml.getEventType() != 1) {
                try {
                    try {
                        xml.next();
                        if (KeyboardLayoutSet.TAG_FEATURE.equals(xml.getName())) {
                            return readScriptIdFromTagFeature(resources, xml);
                        }
                    } catch (IOException | XmlPullParserException e) {
                        throw new RuntimeException(e.getMessage() + " in " + str, e);
                    }
                } finally {
                    xml.close();
                }
            }
            xml.close();
            return 11;
        }

        private static int readScriptIdFromTagFeature(final Resources resources, final XmlPullParser parser) throws XmlPullParserException, IOException {
            TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.KeyboardLayoutSet_Feature);
            try {
                int i = typedArrayObtainAttributes.getInt(0, -1);
                XmlParseUtils.checkEndTag(KeyboardLayoutSet.TAG_FEATURE, parser);
                return i;
            } finally {
                typedArrayObtainAttributes.recycle();
            }
        }

        public KeyboardLayoutSet build() {
            if (this.mParams.mSubtype == null) {
                throw new RuntimeException("KeyboardLayoutSet subtype is not specified");
            }
            try {
                parseKeyboardLayoutSet(this.mResources, getXmlId(this.mResources, this.mParams.mKeyboardLayoutSetName));
                return new KeyboardLayoutSet(this.mContext, this.mParams);
            } catch (IOException | XmlPullParserException e) {
                throw new RuntimeException(e.getMessage() + " in " + this.mParams.mKeyboardLayoutSetName, e);
            }
        }

        private static int getXmlId(final Resources resources, final String keyboardLayoutSetName) {
            return resources.getIdentifier(keyboardLayoutSetName, "xml", resources.getResourcePackageName(R.xml.keyboard_layout_set_qwerty));
        }

        private void parseKeyboardLayoutSet(final Resources res, final int resId) throws XmlPullParserException, IOException {
            XmlResourceParser xml = res.getXml(resId);
            while (xml.getEventType() != 1) {
                try {
                    if (xml.next() == 2) {
                        String name = xml.getName();
                        if ("KeyboardLayoutSet".equals(name)) {
                            parseKeyboardLayoutSetContent(xml);
                        } else {
                            throw new XmlParseUtils.IllegalStartTag(xml, name, "KeyboardLayoutSet");
                        }
                    }
                } finally {
                    xml.close();
                }
            }
        }

        private void parseKeyboardLayoutSetContent(final XmlPullParser parser) throws XmlPullParserException, IOException {
            while (parser.getEventType() != 1) {
                int next = parser.next();
                if (next == 2) {
                    String name = parser.getName();
                    if (KeyboardLayoutSet.TAG_ELEMENT.equals(name)) {
                        parseKeyboardLayoutSetElement(parser);
                    } else if (KeyboardLayoutSet.TAG_FEATURE.equals(name)) {
                        this.mParams.mScriptId = readScriptIdFromTagFeature(this.mResources, parser);
                    } else {
                        throw new XmlParseUtils.IllegalStartTag(parser, name, "KeyboardLayoutSet");
                    }
                } else if (next == 3) {
                    String name2 = parser.getName();
                    if (!"KeyboardLayoutSet".equals(name2)) {
                        throw new XmlParseUtils.IllegalEndTag(parser, name2, "KeyboardLayoutSet");
                    }
                    return;
                }
            }
        }

        private void parseKeyboardLayoutSetElement(final XmlPullParser parser) throws XmlPullParserException, IOException {
            TypedArray typedArrayObtainAttributes = this.mResources.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.KeyboardLayoutSet_Element);
            try {
                XmlParseUtils.checkAttributeExists(typedArrayObtainAttributes, 2, "elementName", KeyboardLayoutSet.TAG_ELEMENT, parser);
                XmlParseUtils.checkAttributeExists(typedArrayObtainAttributes, 1, "elementKeyboard", KeyboardLayoutSet.TAG_ELEMENT, parser);
                XmlParseUtils.checkEndTag(KeyboardLayoutSet.TAG_ELEMENT, parser);
                ElementParams elementParams = new ElementParams();
                int i = typedArrayObtainAttributes.getInt(2, 0);
                elementParams.mKeyboardXmlId = typedArrayObtainAttributes.getResourceId(1, 0);
                elementParams.mProximityCharsCorrectionEnabled = typedArrayObtainAttributes.getBoolean(3, false);
                elementParams.mSupportsSplitLayout = typedArrayObtainAttributes.getBoolean(4, false);
                elementParams.mAllowRedundantMoreKeys = typedArrayObtainAttributes.getBoolean(0, true);
                this.mParams.mKeyboardLayoutSetElementIdToParamsMap.put(i, elementParams);
            } finally {
                typedArrayObtainAttributes.recycle();
            }
        }

        private static int getKeyboardMode(final EditorInfo editorInfo) {
            int i = editorInfo.inputType;
            int i2 = i & 4080;
            int i3 = i & 15;
            if (i3 == 1) {
                if (InputTypeUtils.isEmailVariation(i2)) {
                    return 2;
                }
                if (i2 == 16) {
                    return 1;
                }
                if (i2 == 64) {
                    return 3;
                }
                if (i2 == 176) {
                }
                return 0;
            }
            if (i3 == 2) {
                return 5;
            }
            if (i3 == 3) {
                return 4;
            }
            if (i3 != 4) {
                return 0;
            }
            if (i2 != 16) {
                return i2 != 32 ? 8 : 7;
            }
            return 6;
        }
    }
}
