package com.android.inputmethod.keyboard;

import android.util.SparseArray;
import com.android.inputmethod.keyboard.internal.KeyVisualAttributes;
import com.android.inputmethod.keyboard.internal.KeyboardIconsSet;
import com.android.inputmethod.keyboard.internal.KeyboardParams;
import com.android.inputmethod.latin.common.CoordinateUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class Keyboard {

    @Nonnull
    public final List<Key> mAltCodeKeysWhileTyping;
    public final int mBaseHeight;
    public final int mBaseWidth;

    @Nonnull
    public final KeyboardIconsSet mIconsSet;

    @Nonnull
    public final KeyboardId mId;
    private final SparseArray<Key> mKeyCache = new SparseArray<>();
    public final KeyVisualAttributes mKeyVisualAttributes;

    @Nonnull
    private final KeyboardLayout mKeyboardLayout;
    public final int mMaxMoreKeysKeyboardColumn;
    public final int mMoreKeysTemplate;
    public final int mMostCommonKeyHeight;
    public final int mMostCommonKeyWidth;
    public final int mOccupiedHeight;
    public final int mOccupiedWidth;
    private final boolean mProximityCharsCorrectionEnabled;

    @Nonnull
    private final ProximityInfo mProximityInfo;

    @Nonnull
    public final List<Key> mShiftKeys;

    @Nonnull
    private final List<Key> mSortedKeys;
    public final int mThemeId;
    public final int mTopPadding;
    public final int mVerticalGap;

    public Keyboard(@Nonnull final KeyboardParams params) {
        this.mId = params.mId;
        this.mThemeId = params.mThemeId;
        int i = params.mOccupiedHeight;
        this.mOccupiedHeight = i;
        int i2 = params.mOccupiedWidth;
        this.mOccupiedWidth = i2;
        this.mBaseHeight = params.mBaseHeight;
        this.mBaseWidth = params.mBaseWidth;
        int i3 = params.mMostCommonKeyHeight;
        this.mMostCommonKeyHeight = i3;
        int i4 = params.mMostCommonKeyWidth;
        this.mMostCommonKeyWidth = i4;
        this.mMoreKeysTemplate = params.mMoreKeysTemplate;
        this.mMaxMoreKeysKeyboardColumn = params.mMaxMoreKeysKeyboardColumn;
        this.mKeyVisualAttributes = params.mKeyVisualAttributes;
        this.mTopPadding = params.mTopPadding;
        this.mVerticalGap = params.mVerticalGap;
        List<Key> listUnmodifiableList = Collections.unmodifiableList(new ArrayList(params.mSortedKeys));
        this.mSortedKeys = listUnmodifiableList;
        this.mShiftKeys = Collections.unmodifiableList(params.mShiftKeys);
        this.mAltCodeKeysWhileTyping = Collections.unmodifiableList(params.mAltCodeKeysWhileTyping);
        this.mIconsSet = params.mIconsSet;
        this.mProximityInfo = new ProximityInfo(params.GRID_WIDTH, params.GRID_HEIGHT, i2, i, i4, i3, listUnmodifiableList, params.mTouchPositionCorrection);
        this.mProximityCharsCorrectionEnabled = params.mProximityCharsCorrectionEnabled;
        this.mKeyboardLayout = KeyboardLayout.newKeyboardLayout(listUnmodifiableList, i4, i3, i2, i);
    }

    protected Keyboard(@Nonnull final Keyboard keyboard) {
        this.mId = keyboard.mId;
        this.mThemeId = keyboard.mThemeId;
        this.mOccupiedHeight = keyboard.mOccupiedHeight;
        this.mOccupiedWidth = keyboard.mOccupiedWidth;
        this.mBaseHeight = keyboard.mBaseHeight;
        this.mBaseWidth = keyboard.mBaseWidth;
        this.mMostCommonKeyHeight = keyboard.mMostCommonKeyHeight;
        this.mMostCommonKeyWidth = keyboard.mMostCommonKeyWidth;
        this.mMoreKeysTemplate = keyboard.mMoreKeysTemplate;
        this.mMaxMoreKeysKeyboardColumn = keyboard.mMaxMoreKeysKeyboardColumn;
        this.mKeyVisualAttributes = keyboard.mKeyVisualAttributes;
        this.mTopPadding = keyboard.mTopPadding;
        this.mVerticalGap = keyboard.mVerticalGap;
        this.mSortedKeys = keyboard.mSortedKeys;
        this.mShiftKeys = keyboard.mShiftKeys;
        this.mAltCodeKeysWhileTyping = keyboard.mAltCodeKeysWhileTyping;
        this.mIconsSet = keyboard.mIconsSet;
        this.mProximityInfo = keyboard.mProximityInfo;
        this.mProximityCharsCorrectionEnabled = keyboard.mProximityCharsCorrectionEnabled;
        this.mKeyboardLayout = keyboard.mKeyboardLayout;
    }

    public boolean hasProximityCharsCorrection(final int code) {
        if (this.mProximityCharsCorrectionEnabled) {
            return (this.mId.mElementId == 0 || this.mId.mElementId == 2) || Character.isLetter(code);
        }
        return false;
    }

    @Nonnull
    public ProximityInfo getProximityInfo() {
        return this.mProximityInfo;
    }

    @Nonnull
    public KeyboardLayout getKeyboardLayout() {
        return this.mKeyboardLayout;
    }

    @Nonnull
    public List<Key> getSortedKeys() {
        return this.mSortedKeys;
    }

    @Nullable
    public Key getKey(final int code) {
        if (code == -15) {
            return null;
        }
        synchronized (this.mKeyCache) {
            int iIndexOfKey = this.mKeyCache.indexOfKey(code);
            if (iIndexOfKey >= 0) {
                return this.mKeyCache.valueAt(iIndexOfKey);
            }
            for (Key key : getSortedKeys()) {
                if (key.getCode() == code) {
                    this.mKeyCache.put(code, key);
                    return key;
                }
            }
            this.mKeyCache.put(code, null);
            return null;
        }
    }

    public boolean hasKey(@Nonnull final Key aKey) {
        if (this.mKeyCache.indexOfValue(aKey) >= 0) {
            return true;
        }
        for (Key key : getSortedKeys()) {
            if (key == aKey) {
                this.mKeyCache.put(key.getCode(), key);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return this.mId.toString();
    }

    @Nonnull
    public List<Key> getNearestKeys(final int x, final int y) {
        return this.mProximityInfo.getNearestKeys(Math.max(0, Math.min(x, this.mOccupiedWidth - 1)), Math.max(0, Math.min(y, this.mOccupiedHeight - 1)));
    }

    @Nonnull
    public int[] getCoordinates(@Nonnull final int[] codePoints) {
        int length = codePoints.length;
        int[] iArrNewCoordinateArray = CoordinateUtils.newCoordinateArray(length);
        for (int i = 0; i < length; i++) {
            Key key = getKey(codePoints[i]);
            if (key != null) {
                CoordinateUtils.setXYInArray(iArrNewCoordinateArray, i, key.getX() + (key.getWidth() / 2), key.getY() + (key.getHeight() / 2));
            } else {
                CoordinateUtils.setXYInArray(iArrNewCoordinateArray, i, -1, -1);
            }
        }
        return iArrNewCoordinateArray;
    }
}
