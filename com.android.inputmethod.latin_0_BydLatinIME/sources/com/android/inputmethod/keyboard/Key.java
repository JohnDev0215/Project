package com.android.inputmethod.keyboard;

import android.R;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.android.inputmethod.keyboard.internal.KeyDrawParams;
import com.android.inputmethod.keyboard.internal.KeySpecParser;
import com.android.inputmethod.keyboard.internal.KeyStyle;
import com.android.inputmethod.keyboard.internal.KeyVisualAttributes;
import com.android.inputmethod.keyboard.internal.KeyboardIconsSet;
import com.android.inputmethod.keyboard.internal.KeyboardParams;
import com.android.inputmethod.keyboard.internal.KeyboardRow;
import com.android.inputmethod.keyboard.internal.MoreKeySpec;
import com.android.inputmethod.latin.common.Constants;
import com.android.inputmethod.latin.common.StringUtils;
import java.util.Arrays;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class Key implements Comparable<Key> {
    private static final int ACTION_FLAGS_ALT_CODE_WHILE_TYPING = 4;
    private static final int ACTION_FLAGS_ENABLE_LONG_PRESS = 8;
    private static final int ACTION_FLAGS_IS_REPEATABLE = 1;
    private static final int ACTION_FLAGS_NO_KEY_PREVIEW = 2;
    public static final int BACKGROUND_TYPE_ACTION = 5;
    public static final int BACKGROUND_TYPE_EMPTY = 0;
    public static final int BACKGROUND_TYPE_FUNCTIONAL = 2;
    public static final int BACKGROUND_TYPE_NORMAL = 1;
    public static final int BACKGROUND_TYPE_SPACEBAR = 6;
    public static final int BACKGROUND_TYPE_STICKY_OFF = 3;
    public static final int BACKGROUND_TYPE_STICKY_ON = 4;
    private static final int LABEL_FLAGS_ALIGN_HINT_LABEL_TO_BOTTOM = 2;
    private static final int LABEL_FLAGS_ALIGN_ICON_TO_BOTTOM = 4;
    private static final int LABEL_FLAGS_ALIGN_LABEL_OFF_CENTER = 8;
    private static final int LABEL_FLAGS_AUTO_SCALE = 49152;
    private static final int LABEL_FLAGS_AUTO_X_SCALE = 16384;
    private static final int LABEL_FLAGS_AUTO_Y_SCALE = 32768;
    private static final int LABEL_FLAGS_DISABLE_ADDITIONAL_MORE_KEYS = Integer.MIN_VALUE;
    private static final int LABEL_FLAGS_DISABLE_HINT_LABEL = 1073741824;
    private static final int LABEL_FLAGS_FOLLOW_FUNCTIONAL_TEXT_COLOR = 524288;
    private static final int LABEL_FLAGS_FOLLOW_KEY_HINT_LABEL_RATIO = 320;
    private static final int LABEL_FLAGS_FOLLOW_KEY_LABEL_RATIO = 192;
    private static final int LABEL_FLAGS_FOLLOW_KEY_LARGE_LETTER_RATIO = 64;
    private static final int LABEL_FLAGS_FOLLOW_KEY_LETTER_RATIO = 128;
    private static final int LABEL_FLAGS_FOLLOW_KEY_TEXT_RATIO_MASK = 448;
    private static final int LABEL_FLAGS_FONT_DEFAULT = 48;
    private static final int LABEL_FLAGS_FONT_MASK = 48;
    private static final int LABEL_FLAGS_FONT_MONO_SPACE = 32;
    private static final int LABEL_FLAGS_FONT_NORMAL = 16;
    private static final int LABEL_FLAGS_FROM_CUSTOM_ACTION_LABEL = 262144;
    private static final int LABEL_FLAGS_HAS_HINT_LABEL = 2048;
    private static final int LABEL_FLAGS_HAS_POPUP_HINT = 512;
    private static final int LABEL_FLAGS_HAS_SHIFTED_LETTER_HINT = 1024;
    private static final int LABEL_FLAGS_KEEP_BACKGROUND_ASPECT_RATIO = 1048576;
    private static final int LABEL_FLAGS_PRESERVE_CASE = 65536;
    private static final int LABEL_FLAGS_SHIFTED_LETTER_ACTIVATED = 131072;
    private static final String MORE_KEYS_AUTO_COLUMN_ORDER = "!autoColumnOrder!";
    private static final int MORE_KEYS_COLUMN_NUMBER_MASK = 255;
    private static final String MORE_KEYS_FIXED_COLUMN_ORDER = "!fixedColumnOrder!";
    private static final int MORE_KEYS_FLAGS_FIXED_COLUMN = 256;
    private static final int MORE_KEYS_FLAGS_FIXED_ORDER = 512;
    private static final int MORE_KEYS_FLAGS_HAS_LABELS = 1073741824;
    private static final int MORE_KEYS_FLAGS_NEEDS_DIVIDERS = 536870912;
    private static final int MORE_KEYS_FLAGS_NO_PANEL_AUTO_MORE_KEY = 268435456;
    private static final String MORE_KEYS_HAS_LABELS = "!hasLabels!";
    private static final int MORE_KEYS_MODE_FIXED_COLUMN_WITH_AUTO_ORDER = 256;
    private static final int MORE_KEYS_MODE_FIXED_COLUMN_WITH_FIXED_ORDER = 768;
    private static final int MORE_KEYS_MODE_MAX_COLUMN_WITH_AUTO_ORDER = 0;
    private static final String MORE_KEYS_NEEDS_DIVIDERS = "!needsDividers!";
    private static final String MORE_KEYS_NO_PANEL_AUTO_MORE_KEY = "!noPanelAutoMoreKey!";
    private final int mActionFlags;
    private final int mBackgroundType;
    private final int mCode;
    private boolean mEnabled;
    private final int mHashCode;
    private final int mHeight;
    private final String mHintLabel;

    @Nonnull
    private final Rect mHitBox;
    private final int mHorizontalGap;
    private final int mIconId;

    @Nullable
    private final KeyVisualAttributes mKeyVisualAttributes;
    private final String mLabel;
    private final int mLabelFlags;

    @Nullable
    private final MoreKeySpec[] mMoreKeys;
    private final int mMoreKeysColumnAndFlags;

    @Nullable
    private final OptionalAttributes mOptionalAttributes;
    private boolean mPressed;
    private final int mVerticalGap;
    private final int mWidth;
    private final int mX;
    private final int mY;

    private static String backgroundName(final int backgroundType) {
        switch (backgroundType) {
            case 0:
                return "empty";
            case 1:
                return "normal";
            case 2:
                return "functional";
            case 3:
                return "stickyOff";
            case 4:
                return "stickyOn";
            case 5:
                return "action";
            case 6:
                return "spacebar";
            default:
                return null;
        }
    }

    private static boolean needsToUpcase(final int labelFlags, final int keyboardElementId) {
        if ((labelFlags & 65536) != 0) {
            return false;
        }
        return keyboardElementId == 1 || keyboardElementId == 2 || keyboardElementId == 3 || keyboardElementId == 4;
    }

    private static final class OptionalAttributes {
        public final int mAltCode;
        public final int mDisabledIconId;
        public final String mOutputText;
        public final int mVisualInsetsLeft;
        public final int mVisualInsetsRight;

        private OptionalAttributes(final String outputText, final int altCode, final int disabledIconId, final int visualInsetsLeft, final int visualInsetsRight) {
            this.mOutputText = outputText;
            this.mAltCode = altCode;
            this.mDisabledIconId = disabledIconId;
            this.mVisualInsetsLeft = visualInsetsLeft;
            this.mVisualInsetsRight = visualInsetsRight;
        }

        @Nullable
        public static OptionalAttributes newInstance(final String outputText, final int altCode, final int disabledIconId, final int visualInsetsLeft, final int visualInsetsRight) {
            if (outputText == null && altCode == -15 && disabledIconId == 0 && visualInsetsLeft == 0 && visualInsetsRight == 0) {
                return null;
            }
            return new OptionalAttributes(outputText, altCode, disabledIconId, visualInsetsLeft, visualInsetsRight);
        }
    }

    public Key(@Nullable final String label, final int iconId, final int code, @Nullable final String outputText, @Nullable final String hintLabel, final int labelFlags, final int backgroundType, final int x, final int y, final int width, final int height, final int horizontalGap, final int verticalGap) {
        Rect rect = new Rect();
        this.mHitBox = rect;
        this.mEnabled = true;
        this.mWidth = width - horizontalGap;
        this.mHeight = height - verticalGap;
        this.mHorizontalGap = horizontalGap;
        this.mVerticalGap = verticalGap;
        this.mHintLabel = hintLabel;
        this.mLabelFlags = labelFlags;
        this.mBackgroundType = backgroundType;
        this.mActionFlags = 2;
        this.mMoreKeys = null;
        this.mMoreKeysColumnAndFlags = 0;
        this.mLabel = label;
        this.mOptionalAttributes = OptionalAttributes.newInstance(outputText, -15, 0, 0, 0);
        this.mCode = code;
        this.mEnabled = code != -15;
        this.mIconId = iconId;
        this.mX = (horizontalGap / 2) + x;
        this.mY = y;
        rect.set(x, y, x + width + 1, y + height);
        this.mKeyVisualAttributes = null;
        this.mHashCode = computeHashCode(this);
    }

    public Key(@Nullable final String keySpec, @Nonnull final TypedArray keyAttr, @Nonnull final KeyStyle style, @Nonnull final KeyboardParams params, @Nonnull final KeyboardRow row) {
        Rect rect = new Rect();
        this.mHitBox = rect;
        this.mEnabled = true;
        int i = isSpacer() ? 0 : params.mHorizontalGap;
        this.mHorizontalGap = i;
        int i2 = params.mVerticalGap;
        this.mVerticalGap = i2;
        float f = i;
        int rowHeight = row.getRowHeight();
        this.mHeight = rowHeight - i2;
        float keyX = row.getKeyX(keyAttr);
        float keyWidth = row.getKeyWidth(keyAttr, keyX);
        int keyY = row.getKeyY();
        this.mX = Math.round((f / 2.0f) + keyX);
        this.mY = keyY;
        this.mWidth = Math.round(keyWidth - f);
        int iRound = Math.round(keyX);
        float f2 = keyX + keyWidth;
        rect.set(iRound, keyY, Math.round(f2) + 1, rowHeight + keyY);
        row.setXPos(f2);
        this.mBackgroundType = style.getInt(keyAttr, 2, row.getDefaultBackgroundType());
        int i3 = params.mBaseWidth;
        int iRound2 = Math.round(keyAttr.getFraction(33, i3, i3, 0.0f));
        int iRound3 = Math.round(keyAttr.getFraction(34, i3, i3, 0.0f));
        int defaultKeyLabelFlags = row.getDefaultKeyLabelFlags() | style.getFlags(keyAttr, 13);
        this.mLabelFlags = defaultKeyLabelFlags;
        boolean zNeedsToUpcase = needsToUpcase(defaultKeyLabelFlags, params.mId.mElementId);
        Locale locale = params.mId.getLocale();
        int flags = style.getFlags(keyAttr, 4);
        String[] stringArray = style.getStringArray(keyAttr, 32);
        int i4 = style.getInt(keyAttr, 31, params.mMaxMoreKeysKeyboardColumn) | 0;
        int intValue = MoreKeySpec.getIntValue(stringArray, MORE_KEYS_AUTO_COLUMN_ORDER, -1);
        i4 = intValue > 0 ? (intValue & 255) | 256 : i4;
        int intValue2 = MoreKeySpec.getIntValue(stringArray, MORE_KEYS_FIXED_COLUMN_ORDER, -1);
        i4 = intValue2 > 0 ? (intValue2 & 255) | 768 : i4;
        i4 = MoreKeySpec.getBooleanValue(stringArray, MORE_KEYS_HAS_LABELS) ? i4 | 1073741824 : i4;
        i4 = MoreKeySpec.getBooleanValue(stringArray, MORE_KEYS_NEEDS_DIVIDERS) ? i4 | 536870912 : i4;
        this.mMoreKeysColumnAndFlags = MoreKeySpec.getBooleanValue(stringArray, MORE_KEYS_NO_PANEL_AUTO_MORE_KEY) ? i4 | 268435456 : i4;
        String str = null;
        String[] strArrInsertAdditionalMoreKeys = MoreKeySpec.insertAdditionalMoreKeys(stringArray, (KeySpecParser.getCode(keySpec) == 44 || KeySpecParser.getCode(keySpec) == 64) ? null : (defaultKeyLabelFlags & Integer.MIN_VALUE) != 0 ? null : style.getStringArray(keyAttr, 0));
        if (strArrInsertAdditionalMoreKeys != null) {
            flags |= 8;
            this.mMoreKeys = new MoreKeySpec[strArrInsertAdditionalMoreKeys.length];
            for (int i5 = 0; i5 < strArrInsertAdditionalMoreKeys.length; i5++) {
                this.mMoreKeys[i5] = new MoreKeySpec(strArrInsertAdditionalMoreKeys[i5], zNeedsToUpcase, locale);
            }
        } else {
            this.mMoreKeys = null;
        }
        this.mActionFlags = flags;
        this.mIconId = KeySpecParser.getIconId(keySpec);
        int iconId = KeySpecParser.getIconId(style.getString(keyAttr, 12));
        int code = KeySpecParser.getCode(keySpec);
        if ((this.mLabelFlags & 262144) != 0) {
            this.mLabel = params.mId.mCustomActionLabel;
        } else if (code >= 65536) {
            this.mLabel = new StringBuilder().appendCodePoint(code).toString();
        } else {
            String label = KeySpecParser.getLabel(keySpec);
            this.mLabel = zNeedsToUpcase ? StringUtils.toTitleCaseOfKeyLabel(label, locale) : label;
        }
        if ((this.mLabelFlags & 1073741824) != 0) {
            this.mHintLabel = null;
        } else {
            String string = style.getString(keyAttr, 5);
            this.mHintLabel = zNeedsToUpcase ? StringUtils.toTitleCaseOfKeyLabel(string, locale) : string;
        }
        String outputText = KeySpecParser.getOutputText(keySpec);
        outputText = zNeedsToUpcase ? StringUtils.toTitleCaseOfKeyLabel(outputText, locale) : outputText;
        if (code != -15 || !TextUtils.isEmpty(outputText) || TextUtils.isEmpty(this.mLabel)) {
            if (code == -15 && outputText != null) {
                if (StringUtils.codePointCount(outputText) == 1) {
                    this.mCode = outputText.codePointAt(0);
                } else {
                    this.mCode = -4;
                }
            } else {
                this.mCode = zNeedsToUpcase ? StringUtils.toTitleCaseOfKeyCode(code, locale) : code;
            }
            str = outputText;
        } else if (StringUtils.codePointCount(this.mLabel) == 1) {
            if (hasShiftedLetterHint() && isShiftedLetterActivated()) {
                this.mCode = this.mHintLabel.codePointAt(0);
            } else {
                this.mCode = this.mLabel.codePointAt(0);
            }
            str = outputText;
        } else {
            str = this.mLabel;
            this.mCode = -4;
        }
        int code2 = KeySpecParser.parseCode(style.getString(keyAttr, 1), -15);
        this.mOptionalAttributes = OptionalAttributes.newInstance(str, zNeedsToUpcase ? StringUtils.toTitleCaseOfKeyCode(code2, locale) : code2, iconId, iRound2, iRound3);
        this.mKeyVisualAttributes = KeyVisualAttributes.newInstance(keyAttr);
        this.mHashCode = computeHashCode(this);
    }

    protected Key(@Nonnull final Key key) {
        this(key, key.mMoreKeys);
    }

    private Key(@Nonnull final Key key, @Nullable final MoreKeySpec[] moreKeys) {
        Rect rect = new Rect();
        this.mHitBox = rect;
        this.mEnabled = true;
        this.mCode = key.mCode;
        this.mLabel = key.mLabel;
        this.mHintLabel = key.mHintLabel;
        this.mLabelFlags = key.mLabelFlags;
        this.mIconId = key.mIconId;
        this.mWidth = key.mWidth;
        this.mHeight = key.mHeight;
        this.mHorizontalGap = key.mHorizontalGap;
        this.mVerticalGap = key.mVerticalGap;
        this.mX = key.mX;
        this.mY = key.mY;
        rect.set(key.mHitBox);
        this.mMoreKeys = moreKeys;
        this.mMoreKeysColumnAndFlags = key.mMoreKeysColumnAndFlags;
        this.mBackgroundType = key.mBackgroundType;
        this.mActionFlags = key.mActionFlags;
        this.mKeyVisualAttributes = key.mKeyVisualAttributes;
        this.mOptionalAttributes = key.mOptionalAttributes;
        this.mHashCode = key.mHashCode;
        this.mPressed = key.mPressed;
        this.mEnabled = key.mEnabled;
    }

    @Nonnull
    public static Key removeRedundantMoreKeys(@Nonnull final Key key, @Nonnull final MoreKeySpec.LettersOnBaseLayout lettersOnBaseLayout) {
        MoreKeySpec[] moreKeys = key.getMoreKeys();
        MoreKeySpec[] moreKeySpecArrRemoveRedundantMoreKeys = MoreKeySpec.removeRedundantMoreKeys(moreKeys, lettersOnBaseLayout);
        return moreKeySpecArrRemoveRedundantMoreKeys == moreKeys ? key : new Key(key, moreKeySpecArrRemoveRedundantMoreKeys);
    }

    private static int computeHashCode(final Key key) {
        return Arrays.hashCode(new Object[]{Integer.valueOf(key.mX), Integer.valueOf(key.mY), Integer.valueOf(key.mWidth), Integer.valueOf(key.mHeight), Integer.valueOf(key.mCode), key.mLabel, key.mHintLabel, Integer.valueOf(key.mIconId), Integer.valueOf(key.mBackgroundType), Integer.valueOf(Arrays.hashCode(key.mMoreKeys)), key.getOutputText(), Integer.valueOf(key.mActionFlags), Integer.valueOf(key.mLabelFlags)});
    }

    private boolean equalsInternal(final Key o) {
        if (this == o) {
            return true;
        }
        return o.mX == this.mX && o.mY == this.mY && o.mWidth == this.mWidth && o.mHeight == this.mHeight && o.mCode == this.mCode && TextUtils.equals(o.mLabel, this.mLabel) && TextUtils.equals(o.mHintLabel, this.mHintLabel) && o.mIconId == this.mIconId && o.mBackgroundType == this.mBackgroundType && Arrays.equals(o.mMoreKeys, this.mMoreKeys) && TextUtils.equals(o.getOutputText(), getOutputText()) && o.mActionFlags == this.mActionFlags && o.mLabelFlags == this.mLabelFlags;
    }

    @Override // java.lang.Comparable
    public int compareTo(Key o) {
        if (equalsInternal(o)) {
            return 0;
        }
        return this.mHashCode > o.mHashCode ? 1 : -1;
    }

    public int hashCode() {
        return this.mHashCode;
    }

    public boolean equals(final Object o) {
        return (o instanceof Key) && equalsInternal((Key) o);
    }

    public String toString() {
        return toShortString() + " " + getX() + "," + getY() + " " + getWidth() + "x" + getHeight();
    }

    public String toShortString() {
        int code = getCode();
        if (code == -4) {
            return getOutputText();
        }
        return Constants.printableCode(code);
    }

    public String toLongString() {
        int iconId = getIconId();
        String label = iconId == 0 ? KeyboardIconsSet.PREFIX_ICON + KeyboardIconsSet.getIconName(iconId) : getLabel();
        String hintLabel = getHintLabel();
        if (hintLabel != null) {
            label = label + "^" + hintLabel;
        }
        return toString() + " " + label + "/" + backgroundName(this.mBackgroundType);
    }

    public int getCode() {
        return this.mCode;
    }

    @Nullable
    public String getLabel() {
        return this.mLabel;
    }

    @Nullable
    public String getHintLabel() {
        return this.mHintLabel;
    }

    @Nullable
    public MoreKeySpec[] getMoreKeys() {
        return this.mMoreKeys;
    }

    public void markAsLeftEdge(final KeyboardParams params) {
        this.mHitBox.left = params.mLeftPadding;
    }

    public void markAsRightEdge(final KeyboardParams params) {
        this.mHitBox.right = params.mOccupiedWidth - params.mRightPadding;
    }

    public void markAsTopEdge(final KeyboardParams params) {
        this.mHitBox.top = params.mTopPadding;
    }

    public void markAsBottomEdge(final KeyboardParams params) {
        this.mHitBox.bottom = params.mOccupiedHeight + params.mBottomPadding;
    }

    public final boolean isSpacer() {
        return this instanceof Spacer;
    }

    public final boolean isActionKey() {
        return this.mBackgroundType == 5;
    }

    public final boolean isShift() {
        return this.mCode == -1;
    }

    public final boolean isModifier() {
        int i = this.mCode;
        return i == -1 || i == -3;
    }

    public final boolean isRepeatable() {
        return (this.mActionFlags & 1) != 0;
    }

    public final boolean noKeyPreview() {
        return (this.mActionFlags & 2) != 0;
    }

    public final boolean altCodeWhileTyping() {
        return (this.mActionFlags & 4) != 0;
    }

    public final boolean isLongPressEnabled() {
        return (this.mActionFlags & 8) != 0 && (this.mLabelFlags & 131072) == 0;
    }

    public KeyVisualAttributes getVisualAttributes() {
        return this.mKeyVisualAttributes;
    }

    @Nonnull
    public final Typeface selectTypeface(final KeyDrawParams params) {
        int i = this.mLabelFlags & 48;
        if (i == 16) {
            return Typeface.DEFAULT;
        }
        if (i == 32) {
            return Typeface.MONOSPACE;
        }
        return params.mTypeface;
    }

    public final int selectTextSize(final KeyDrawParams params) {
        int i = this.mLabelFlags & 448;
        if (i == 64) {
            return params.mLargeLetterSize;
        }
        if (i == 128) {
            return params.mLetterSize;
        }
        if (i == 192) {
            return params.mLabelSize;
        }
        if (i != 320) {
            return StringUtils.codePointCount(this.mLabel) == 1 ? params.mLetterSize : params.mLabelSize;
        }
        return params.mHintLabelSize;
    }

    public final int selectTextColor(final KeyDrawParams params) {
        if ((this.mLabelFlags & 524288) != 0) {
            return params.mFunctionalTextColor;
        }
        return isShiftedLetterActivated() ? params.mTextInactivatedColor : params.mTextColor;
    }

    public final int selectHintTextSize(final KeyDrawParams params) {
        if (hasHintLabel()) {
            return params.mHintLabelSize;
        }
        if (hasShiftedLetterHint()) {
            return params.mShiftedLetterHintSize;
        }
        return params.mHintLetterSize;
    }

    public final int selectHintTextColor(final KeyDrawParams params) {
        if (hasHintLabel()) {
            return params.mHintLabelColor;
        }
        if (hasShiftedLetterHint()) {
            return isShiftedLetterActivated() ? params.mShiftedLetterHintActivatedColor : params.mShiftedLetterHintInactivatedColor;
        }
        return params.mHintLetterColor;
    }

    public final int selectMoreKeyTextSize(final KeyDrawParams params) {
        return hasLabelsInMoreKeys() ? params.mLabelSize : params.mLetterSize;
    }

    public final String getPreviewLabel() {
        return isShiftedLetterActivated() ? this.mHintLabel : this.mLabel;
    }

    private boolean previewHasLetterSize() {
        return (this.mLabelFlags & 128) != 0 || StringUtils.codePointCount(getPreviewLabel()) == 1;
    }

    public final int selectPreviewTextSize(final KeyDrawParams params) {
        if (previewHasLetterSize()) {
            return params.mPreviewTextSize;
        }
        return params.mLetterSize;
    }

    @Nonnull
    public Typeface selectPreviewTypeface(final KeyDrawParams params) {
        if (previewHasLetterSize()) {
            return selectTypeface(params);
        }
        return Typeface.DEFAULT_BOLD;
    }

    public final boolean isAlignHintLabelToBottom(final int defaultFlags) {
        return ((defaultFlags | this.mLabelFlags) & 2) != 0;
    }

    public final boolean isAlignIconToBottom() {
        return (this.mLabelFlags & 4) != 0;
    }

    public final boolean isAlignLabelOffCenter() {
        return (this.mLabelFlags & 8) != 0;
    }

    public final boolean hasPopupHint() {
        return (this.mLabelFlags & 512) != 0;
    }

    public final boolean hasShiftedLetterHint() {
        return ((this.mLabelFlags & 1024) == 0 || TextUtils.isEmpty(this.mHintLabel)) ? false : true;
    }

    public final boolean hasHintLabel() {
        return (this.mLabelFlags & 2048) != 0;
    }

    public final boolean needsAutoXScale() {
        return (this.mLabelFlags & 16384) != 0;
    }

    public final boolean needsAutoScale() {
        return (this.mLabelFlags & LABEL_FLAGS_AUTO_SCALE) == LABEL_FLAGS_AUTO_SCALE;
    }

    public final boolean needsToKeepBackgroundAspectRatio(final int defaultFlags) {
        return ((defaultFlags | this.mLabelFlags) & 1048576) != 0;
    }

    public final boolean hasCustomActionLabel() {
        return (this.mLabelFlags & 262144) != 0;
    }

    private final boolean isShiftedLetterActivated() {
        return ((this.mLabelFlags & 131072) == 0 || TextUtils.isEmpty(this.mHintLabel)) ? false : true;
    }

    public final int getMoreKeysColumnNumber() {
        return this.mMoreKeysColumnAndFlags & 255;
    }

    public final boolean isMoreKeysFixedColumn() {
        return (this.mMoreKeysColumnAndFlags & 256) != 0;
    }

    public final boolean isMoreKeysFixedOrder() {
        return (this.mMoreKeysColumnAndFlags & 512) != 0;
    }

    public final boolean hasLabelsInMoreKeys() {
        return (this.mMoreKeysColumnAndFlags & 1073741824) != 0;
    }

    public final int getMoreKeyLabelFlags() {
        return (hasLabelsInMoreKeys() ? 192 : 128) | 16384;
    }

    public final boolean needsDividersInMoreKeys() {
        return (this.mMoreKeysColumnAndFlags & 536870912) != 0;
    }

    public final boolean hasNoPanelAutoMoreKey() {
        return (this.mMoreKeysColumnAndFlags & 268435456) != 0;
    }

    @Nullable
    public final String getOutputText() {
        OptionalAttributes optionalAttributes = this.mOptionalAttributes;
        if (optionalAttributes != null) {
            return optionalAttributes.mOutputText;
        }
        return null;
    }

    public final int getAltCode() {
        OptionalAttributes optionalAttributes = this.mOptionalAttributes;
        if (optionalAttributes != null) {
            return optionalAttributes.mAltCode;
        }
        return -15;
    }

    public int getIconId() {
        return this.mIconId;
    }

    @Nullable
    public Drawable getIcon(final KeyboardIconsSet iconSet, final int alpha) {
        OptionalAttributes optionalAttributes = this.mOptionalAttributes;
        int iconId = optionalAttributes != null ? optionalAttributes.mDisabledIconId : 0;
        if (this.mEnabled) {
            iconId = getIconId();
        }
        Drawable iconDrawable = iconSet.getIconDrawable(iconId);
        if (iconDrawable != null) {
            iconDrawable.setAlpha(alpha);
        }
        return iconDrawable;
    }

    @Nullable
    public Drawable getPreviewIcon(final KeyboardIconsSet iconSet) {
        return iconSet.getIconDrawable(getIconId());
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getHorizontalGap() {
        return this.mHorizontalGap;
    }

    public int getVerticalGap() {
        return this.mVerticalGap;
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public final int getDrawX() {
        int x = getX();
        OptionalAttributes optionalAttributes = this.mOptionalAttributes;
        return optionalAttributes == null ? x : x + optionalAttributes.mVisualInsetsLeft;
    }

    public final int getDrawWidth() {
        OptionalAttributes optionalAttributes = this.mOptionalAttributes;
        if (optionalAttributes == null) {
            return this.mWidth;
        }
        return (this.mWidth - optionalAttributes.mVisualInsetsLeft) - optionalAttributes.mVisualInsetsRight;
    }

    public void onPressed() {
        this.mPressed = true;
    }

    public void onReleased() {
        this.mPressed = false;
    }

    public final boolean isEnabled() {
        return this.mEnabled;
    }

    public void setEnabled(final boolean enabled) {
        this.mEnabled = enabled;
    }

    @Nonnull
    public Rect getHitBox() {
        return this.mHitBox;
    }

    public boolean isOnKey(final int x, final int y) {
        return this.mHitBox.contains(x, y);
    }

    public int squaredDistanceToEdge(final int x, final int y) {
        int x2 = getX();
        int i = this.mWidth + x2;
        int y2 = getY();
        int i2 = this.mHeight + y2;
        if (x >= x2) {
            x2 = x > i ? i : x;
        }
        if (y >= y2) {
            y2 = y > i2 ? i2 : y;
        }
        int i3 = x - x2;
        int i4 = y - y2;
        return (i3 * i3) + (i4 * i4);
    }

    static class KeyBackgroundState {
        public static final KeyBackgroundState[] STATES = {new KeyBackgroundState(R.attr.state_empty), new KeyBackgroundState(new int[0]), new KeyBackgroundState(new int[0]), new KeyBackgroundState(R.attr.state_checkable), new KeyBackgroundState(R.attr.state_checkable, R.attr.state_checked), new KeyBackgroundState(R.attr.state_active), new KeyBackgroundState(new int[0])};
        private final int[] mPressedState;
        private final int[] mReleasedState;

        private KeyBackgroundState(final int... attrs) {
            this.mReleasedState = attrs;
            int[] iArrCopyOf = Arrays.copyOf(attrs, attrs.length + 1);
            this.mPressedState = iArrCopyOf;
            iArrCopyOf[attrs.length] = 16842919;
        }

        public int[] getState(final boolean pressed) {
            return pressed ? this.mPressedState : this.mReleasedState;
        }
    }

    @Nonnull
    public final Drawable selectBackgroundDrawable(@Nonnull final Drawable keyBackground, @Nonnull final Drawable functionalKeyBackground, @Nonnull final Drawable spacebarBackground) {
        int i = this.mBackgroundType;
        if (i == 2) {
            keyBackground = functionalKeyBackground;
        } else if (i == 6) {
            keyBackground = spacebarBackground;
        }
        keyBackground.setState(KeyBackgroundState.STATES[this.mBackgroundType].getState(this.mPressed));
        return keyBackground;
    }

    public static class Spacer extends Key {
        @Override // com.android.inputmethod.keyboard.Key, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Key o) {
            return super.compareTo(o);
        }

        public Spacer(final TypedArray keyAttr, final KeyStyle keyStyle, final KeyboardParams params, final KeyboardRow row) {
            super(null, keyAttr, keyStyle, params, row);
        }

        protected Spacer(final KeyboardParams params, final int x, final int y, final int width, final int height) {
            super(null, 0, -15, null, null, 0, 0, x, y, width, height, params.mHorizontalGap, params.mVerticalGap);
        }
    }
}
