package com.android.inputmethod.keyboard.internal;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Xml;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.utils.ResourceUtils;
import java.util.ArrayDeque;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyboardRow {
    private static final int KEYWIDTH_FILL_RIGHT = -1;
    private static final int KEYWIDTH_NOT_ENUM = 0;
    private float mCurrentX;
    private final int mCurrentY;
    private final KeyboardParams mParams;
    private final ArrayDeque<RowAttributes> mRowAttributesStack;
    private final int mRowHeight;

    private static class RowAttributes {
        public final int mDefaultBackgroundType;
        public final int mDefaultKeyLabelFlags;
        public final float mDefaultKeyWidth;

        public RowAttributes(final TypedArray keyAttr, final float defaultKeyWidth, final int keyboardWidth) {
            this.mDefaultKeyWidth = keyAttr.getFraction(29, keyboardWidth, keyboardWidth, defaultKeyWidth);
            this.mDefaultKeyLabelFlags = keyAttr.getInt(13, 0);
            this.mDefaultBackgroundType = keyAttr.getInt(2, 1);
        }

        public RowAttributes(final TypedArray keyAttr, final RowAttributes defaultRowAttr, final int keyboardWidth) {
            this.mDefaultKeyWidth = keyAttr.getFraction(29, keyboardWidth, keyboardWidth, defaultRowAttr.mDefaultKeyWidth);
            this.mDefaultKeyLabelFlags = keyAttr.getInt(13, 0) | defaultRowAttr.mDefaultKeyLabelFlags;
            this.mDefaultBackgroundType = keyAttr.getInt(2, defaultRowAttr.mDefaultBackgroundType);
        }
    }

    public KeyboardRow(final Resources res, final KeyboardParams params, final XmlPullParser parser, final int y) {
        ArrayDeque<RowAttributes> arrayDeque = new ArrayDeque<>();
        this.mRowAttributesStack = arrayDeque;
        this.mParams = params;
        TypedArray typedArrayObtainAttributes = res.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard);
        this.mRowHeight = (int) ResourceUtils.getDimensionOrFraction(typedArrayObtainAttributes, 28, params.mBaseHeight, params.mDefaultRowHeight);
        typedArrayObtainAttributes.recycle();
        TypedArray typedArrayObtainAttributes2 = res.obtainAttributes(Xml.asAttributeSet(parser), R.styleable.Keyboard_Key);
        arrayDeque.push(new RowAttributes(typedArrayObtainAttributes2, params.mDefaultKeyWidth, params.mBaseWidth));
        typedArrayObtainAttributes2.recycle();
        this.mCurrentY = y;
        this.mCurrentX = 0.0f;
    }

    public int getRowHeight() {
        return this.mRowHeight;
    }

    public void pushRowAttributes(final TypedArray keyAttr) {
        this.mRowAttributesStack.push(new RowAttributes(keyAttr, this.mRowAttributesStack.peek(), this.mParams.mBaseWidth));
    }

    public void popRowAttributes() {
        this.mRowAttributesStack.pop();
    }

    public float getDefaultKeyWidth() {
        return this.mRowAttributesStack.peek().mDefaultKeyWidth;
    }

    public int getDefaultKeyLabelFlags() {
        return this.mRowAttributesStack.peek().mDefaultKeyLabelFlags;
    }

    public int getDefaultBackgroundType() {
        return this.mRowAttributesStack.peek().mDefaultBackgroundType;
    }

    public void setXPos(final float keyXPos) {
        this.mCurrentX = keyXPos;
    }

    public void advanceXPos(final float width) {
        this.mCurrentX += width;
    }

    public int getKeyY() {
        return this.mCurrentY;
    }

    public float getKeyX(final TypedArray keyAttr) {
        if (keyAttr == null || !keyAttr.hasValue(30)) {
            return this.mCurrentX;
        }
        float fraction = keyAttr.getFraction(30, this.mParams.mBaseWidth, this.mParams.mBaseWidth, 0.0f);
        if (fraction >= 0.0f) {
            return fraction + this.mParams.mLeftPadding;
        }
        return Math.max(fraction + (this.mParams.mOccupiedWidth - this.mParams.mRightPadding), this.mCurrentX);
    }

    public float getKeyWidth(final TypedArray keyAttr, final float keyXPos) {
        if (keyAttr == null) {
            return getDefaultKeyWidth();
        }
        if (ResourceUtils.getEnumValue(keyAttr, 29, 0) == -1) {
            return (this.mParams.mOccupiedWidth - this.mParams.mRightPadding) - keyXPos;
        }
        return keyAttr.getFraction(29, this.mParams.mBaseWidth, this.mParams.mBaseWidth, getDefaultKeyWidth());
    }
}
