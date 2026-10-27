package com.android.inputmethod.keyboard.internal;

import android.content.res.TypedArray;
import android.util.SparseArray;
import com.android.inputmethod.latin.utils.XmlParseUtils;
import java.util.Arrays;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyStylesSet {
    private static final boolean DEBUG = false;

    @Nonnull
    private static final String EMPTY_STYLE_NAME = "<empty>";
    private static final String TAG = "KeyStylesSet";

    @Nonnull
    private final KeyStyle mEmptyKeyStyle;

    @Nonnull
    private final HashMap<String, KeyStyle> mStyles;

    @Nonnull
    private final KeyboardTextsSet mTextsSet;

    public KeyStylesSet(@Nonnull final KeyboardTextsSet textsSet) {
        HashMap<String, KeyStyle> map = new HashMap<>();
        this.mStyles = map;
        this.mTextsSet = textsSet;
        EmptyKeyStyle emptyKeyStyle = new EmptyKeyStyle(textsSet);
        this.mEmptyKeyStyle = emptyKeyStyle;
        map.put(EMPTY_STYLE_NAME, emptyKeyStyle);
    }

    private static final class EmptyKeyStyle extends KeyStyle {
        EmptyKeyStyle(@Nonnull final KeyboardTextsSet textsSet) {
            super(textsSet);
        }

        @Override // com.android.inputmethod.keyboard.internal.KeyStyle
        @Nullable
        public String[] getStringArray(final TypedArray a, final int index) {
            return parseStringArray(a, index);
        }

        @Override // com.android.inputmethod.keyboard.internal.KeyStyle
        @Nullable
        public String getString(final TypedArray a, final int index) {
            return parseString(a, index);
        }

        @Override // com.android.inputmethod.keyboard.internal.KeyStyle
        public int getInt(final TypedArray a, final int index, final int defaultValue) {
            return a.getInt(index, defaultValue);
        }

        @Override // com.android.inputmethod.keyboard.internal.KeyStyle
        public int getFlags(final TypedArray a, final int index) {
            return a.getInt(index, 0);
        }
    }

    private static final class DeclaredKeyStyle extends KeyStyle {
        private final String mParentStyleName;
        private final SparseArray<Object> mStyleAttributes;
        private final HashMap<String, KeyStyle> mStyles;

        public DeclaredKeyStyle(@Nonnull final String parentStyleName, @Nonnull final KeyboardTextsSet textsSet, @Nonnull final HashMap<String, KeyStyle> styles) {
            super(textsSet);
            this.mStyleAttributes = new SparseArray<>();
            this.mParentStyleName = parentStyleName;
            this.mStyles = styles;
        }

        @Override // com.android.inputmethod.keyboard.internal.KeyStyle
        @Nullable
        public String[] getStringArray(final TypedArray a, final int index) {
            if (a.hasValue(index)) {
                return parseStringArray(a, index);
            }
            Object obj = this.mStyleAttributes.get(index);
            if (obj != null) {
                String[] strArr = (String[]) obj;
                return (String[]) Arrays.copyOf(strArr, strArr.length);
            }
            return this.mStyles.get(this.mParentStyleName).getStringArray(a, index);
        }

        @Override // com.android.inputmethod.keyboard.internal.KeyStyle
        @Nullable
        public String getString(final TypedArray a, final int index) {
            if (a.hasValue(index)) {
                return parseString(a, index);
            }
            Object obj = this.mStyleAttributes.get(index);
            if (obj != null) {
                return (String) obj;
            }
            return this.mStyles.get(this.mParentStyleName).getString(a, index);
        }

        @Override // com.android.inputmethod.keyboard.internal.KeyStyle
        public int getInt(final TypedArray a, final int index, final int defaultValue) {
            if (a.hasValue(index)) {
                return a.getInt(index, defaultValue);
            }
            Object obj = this.mStyleAttributes.get(index);
            if (obj != null) {
                return ((Integer) obj).intValue();
            }
            return this.mStyles.get(this.mParentStyleName).getInt(a, index, defaultValue);
        }

        @Override // com.android.inputmethod.keyboard.internal.KeyStyle
        public int getFlags(final TypedArray a, final int index) {
            int flags = this.mStyles.get(this.mParentStyleName).getFlags(a, index);
            Integer num = (Integer) this.mStyleAttributes.get(index);
            return a.getInt(index, 0) | (num != null ? num.intValue() : 0) | flags;
        }

        public void readKeyAttributes(final TypedArray keyAttr) {
            readString(keyAttr, 1);
            readString(keyAttr, 23);
            readString(keyAttr, 5);
            readStringArray(keyAttr, 32);
            readStringArray(keyAttr, 0);
            readFlags(keyAttr, 13);
            readString(keyAttr, 12);
            readInt(keyAttr, 31);
            readInt(keyAttr, 2);
            readFlags(keyAttr, 4);
        }

        private void readString(final TypedArray a, final int index) {
            if (a.hasValue(index)) {
                this.mStyleAttributes.put(index, parseString(a, index));
            }
        }

        private void readInt(final TypedArray a, final int index) {
            if (a.hasValue(index)) {
                this.mStyleAttributes.put(index, Integer.valueOf(a.getInt(index, 0)));
            }
        }

        private void readFlags(final TypedArray a, final int index) {
            if (a.hasValue(index)) {
                Integer num = (Integer) this.mStyleAttributes.get(index);
                this.mStyleAttributes.put(index, Integer.valueOf(a.getInt(index, 0) | (num != null ? num.intValue() : 0)));
            }
        }

        private void readStringArray(final TypedArray a, final int index) {
            if (a.hasValue(index)) {
                this.mStyleAttributes.put(index, parseStringArray(a, index));
            }
        }
    }

    public void parseKeyStyleAttributes(final TypedArray keyStyleAttr, final TypedArray keyAttrs, final XmlPullParser parser) throws XmlPullParserException {
        String string = keyStyleAttr.getString(1);
        if (string == null) {
            throw new XmlParseUtils.ParseException("key-style has no styleName attribute", parser);
        }
        String string2 = keyStyleAttr.getString(0);
        if (string2 != null && !this.mStyles.containsKey(string2)) {
            throw new XmlParseUtils.ParseException("Unknown parentStyle " + string2, parser);
        }
        if (string2 == null) {
            string2 = EMPTY_STYLE_NAME;
        }
        DeclaredKeyStyle declaredKeyStyle = new DeclaredKeyStyle(string2, this.mTextsSet, this.mStyles);
        declaredKeyStyle.readKeyAttributes(keyAttrs);
        this.mStyles.put(string, declaredKeyStyle);
    }

    @Nonnull
    public KeyStyle getKeyStyle(final TypedArray keyAttr, final XmlPullParser parser) throws XmlParseUtils.ParseException {
        String string = keyAttr.getString(24);
        if (string == null) {
            return this.mEmptyKeyStyle;
        }
        KeyStyle keyStyle = this.mStyles.get(string);
        if (keyStyle != null) {
            return keyStyle;
        }
        throw new XmlParseUtils.ParseException("Unknown key style: " + string, parser);
    }
}
