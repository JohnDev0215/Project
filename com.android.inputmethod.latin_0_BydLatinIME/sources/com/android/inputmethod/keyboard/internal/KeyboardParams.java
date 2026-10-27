package com.android.inputmethod.keyboard.internal;

import android.util.SparseIntArray;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.KeyboardId;
import com.android.inputmethod.keyboard.internal.MoreKeySpec;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class KeyboardParams {
    private static final Comparator<Key> ROW_COLUMN_COMPARATOR = new Comparator<Key>() { // from class: com.android.inputmethod.keyboard.internal.KeyboardParams.1
        @Override // java.util.Comparator
        public int compare(final Key lhs, final Key rhs) {
            if (lhs.getY() < rhs.getY()) {
                return -1;
            }
            if (lhs.getY() > rhs.getY()) {
                return 1;
            }
            if (lhs.getX() < rhs.getX()) {
                return -1;
            }
            return lhs.getX() > rhs.getX() ? 1 : 0;
        }
    };
    public int GRID_HEIGHT;
    public int GRID_WIDTH;
    public boolean mAllowRedundantMoreKeys;

    @Nonnull
    public final ArrayList<Key> mAltCodeKeysWhileTyping;
    public int mBaseHeight;
    public int mBaseWidth;
    public int mBottomPadding;
    public int mDefaultKeyWidth;
    public int mDefaultRowHeight;
    private final SparseIntArray mHeightHistogram;
    public int mHorizontalGap;

    @Nonnull
    public final KeyboardIconsSet mIconsSet;
    public KeyboardId mId;

    @Nonnull
    public final KeyStylesSet mKeyStyles;

    @Nullable
    public KeyVisualAttributes mKeyVisualAttributes;
    public int mLeftPadding;
    private int mMaxHeightCount;
    public int mMaxMoreKeysKeyboardColumn;
    private int mMaxWidthCount;
    public int mMoreKeysTemplate;
    public int mMostCommonKeyHeight;
    public int mMostCommonKeyWidth;
    public int mOccupiedHeight;
    public int mOccupiedWidth;
    public boolean mProximityCharsCorrectionEnabled;
    public int mRightPadding;

    @Nonnull
    public final ArrayList<Key> mShiftKeys;

    @Nonnull
    public final SortedSet<Key> mSortedKeys;

    @Nonnull
    public final KeyboardTextsSet mTextsSet;
    public int mThemeId;
    public int mTopPadding;

    @Nonnull
    public final TouchPositionCorrection mTouchPositionCorrection;

    @Nonnull
    private final UniqueKeysCache mUniqueKeysCache;
    public int mVerticalGap;
    private final SparseIntArray mWidthHistogram;

    public KeyboardParams() {
        this(UniqueKeysCache.NO_CACHE);
    }

    public KeyboardParams(@Nonnull final UniqueKeysCache keysCache) {
        this.mSortedKeys = new TreeSet(ROW_COLUMN_COMPARATOR);
        this.mShiftKeys = new ArrayList<>();
        this.mAltCodeKeysWhileTyping = new ArrayList<>();
        this.mIconsSet = new KeyboardIconsSet();
        KeyboardTextsSet keyboardTextsSet = new KeyboardTextsSet();
        this.mTextsSet = keyboardTextsSet;
        this.mKeyStyles = new KeyStylesSet(keyboardTextsSet);
        this.mMostCommonKeyHeight = 0;
        this.mMostCommonKeyWidth = 0;
        this.mTouchPositionCorrection = new TouchPositionCorrection();
        this.mMaxHeightCount = 0;
        this.mMaxWidthCount = 0;
        this.mHeightHistogram = new SparseIntArray();
        this.mWidthHistogram = new SparseIntArray();
        this.mUniqueKeysCache = keysCache;
    }

    protected void clearKeys() {
        this.mSortedKeys.clear();
        this.mShiftKeys.clear();
        clearHistogram();
    }

    public void onAddKey(@Nonnull final Key newKey) {
        Key uniqueKey = this.mUniqueKeysCache.getUniqueKey(newKey);
        boolean zIsSpacer = uniqueKey.isSpacer();
        if (zIsSpacer && uniqueKey.getWidth() == 0) {
            return;
        }
        this.mSortedKeys.add(uniqueKey);
        if (zIsSpacer) {
            return;
        }
        updateHistogram(uniqueKey);
        if (uniqueKey.getCode() == -1) {
            this.mShiftKeys.add(uniqueKey);
        }
        if (uniqueKey.altCodeWhileTyping()) {
            this.mAltCodeKeysWhileTyping.add(uniqueKey);
        }
    }

    public void removeRedundantMoreKeys() {
        if (this.mAllowRedundantMoreKeys) {
            return;
        }
        MoreKeySpec.LettersOnBaseLayout lettersOnBaseLayout = new MoreKeySpec.LettersOnBaseLayout();
        Iterator<Key> it = this.mSortedKeys.iterator();
        while (it.hasNext()) {
            lettersOnBaseLayout.addLetter(it.next());
        }
        ArrayList arrayList = new ArrayList(this.mSortedKeys);
        this.mSortedKeys.clear();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.mSortedKeys.add(this.mUniqueKeysCache.getUniqueKey(Key.removeRedundantMoreKeys((Key) it2.next(), lettersOnBaseLayout)));
        }
    }

    private void clearHistogram() {
        this.mMostCommonKeyHeight = 0;
        this.mMaxHeightCount = 0;
        this.mHeightHistogram.clear();
        this.mMaxWidthCount = 0;
        this.mMostCommonKeyWidth = 0;
        this.mWidthHistogram.clear();
    }

    private static int updateHistogramCounter(final SparseIntArray histogram, final int key) {
        int i = (histogram.indexOfKey(key) >= 0 ? histogram.get(key) : 0) + 1;
        histogram.put(key, i);
        return i;
    }

    private void updateHistogram(final Key key) {
        int height = key.getHeight() + this.mVerticalGap;
        int iUpdateHistogramCounter = updateHistogramCounter(this.mHeightHistogram, height);
        if (iUpdateHistogramCounter > this.mMaxHeightCount) {
            this.mMaxHeightCount = iUpdateHistogramCounter;
            this.mMostCommonKeyHeight = height;
        }
        int width = key.getWidth() + this.mHorizontalGap;
        int iUpdateHistogramCounter2 = updateHistogramCounter(this.mWidthHistogram, width);
        if (iUpdateHistogramCounter2 > this.mMaxWidthCount) {
            this.mMaxWidthCount = iUpdateHistogramCounter2;
            this.mMostCommonKeyWidth = width;
        }
    }
}
