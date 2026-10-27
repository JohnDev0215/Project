package com.android.inputmethod.keyboard;

import android.graphics.Rect;
import com.android.inputmethod.keyboard.internal.TouchPositionCorrection;
import com.android.inputmethod.latin.utils.JniUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ProximityInfo {
    private static final boolean DEBUG = false;
    private static final float DEFAULT_TOUCH_POSITION_CORRECTION_RADIUS = 0.15f;

    @Nonnull
    private static final List<Key> EMPTY_KEY_LIST = Collections.emptyList();
    public static final int MAX_PROXIMITY_CHARS_SIZE = 16;
    private static final float SEARCH_DISTANCE = 1.2f;
    private static final String TAG = "ProximityInfo";
    private final int mCellHeight;
    private final int mCellWidth;
    private final int mGridHeight;

    @Nonnull
    private final List<Key>[] mGridNeighbors;
    private final int mGridSize;
    private final int mGridWidth;
    private final int mKeyboardHeight;
    private final int mKeyboardMinWidth;
    private final int mMostCommonKeyHeight;
    private final int mMostCommonKeyWidth;
    private long mNativeProximityInfo;

    @Nonnull
    private final List<Key> mSortedKeys;

    private static native void releaseProximityInfoNative(long nativeProximityInfo);

    private static native long setProximityInfoNative(int displayWidth, int displayHeight, int gridWidth, int gridHeight, int mostCommonKeyWidth, int mostCommonKeyHeight, int[] proximityCharsArray, int keyCount, int[] keyXCoordinates, int[] keyYCoordinates, int[] keyWidths, int[] keyHeights, int[] keyCharCodes, float[] sweetSpotCenterXs, float[] sweetSpotCenterYs, float[] sweetSpotRadii);

    static {
        JniUtils.loadNativeLibrary();
    }

    ProximityInfo(final int gridWidth, final int gridHeight, final int minWidth, final int height, final int mostCommonKeyWidth, final int mostCommonKeyHeight, @Nonnull final List<Key> sortedKeys, @Nonnull final TouchPositionCorrection touchPositionCorrection) {
        this.mGridWidth = gridWidth;
        this.mGridHeight = gridHeight;
        int i = gridWidth * gridHeight;
        this.mGridSize = i;
        this.mCellWidth = ((minWidth + gridWidth) - 1) / gridWidth;
        this.mCellHeight = ((height + gridHeight) - 1) / gridHeight;
        this.mKeyboardMinWidth = minWidth;
        this.mKeyboardHeight = height;
        this.mMostCommonKeyHeight = mostCommonKeyHeight;
        this.mMostCommonKeyWidth = mostCommonKeyWidth;
        this.mSortedKeys = sortedKeys;
        this.mGridNeighbors = new List[i];
        if (minWidth == 0 || height == 0) {
            return;
        }
        computeNearestNeighbors();
        this.mNativeProximityInfo = createNativeProximityInfo(touchPositionCorrection);
    }

    static boolean needsProximityInfo(final Key key) {
        return key.getCode() >= 32;
    }

    private static int getProximityInfoKeysCount(final List<Key> keys) {
        Iterator<Key> it = keys.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (needsProximityInfo(it.next())) {
                i++;
            }
        }
        return i;
    }

    private long createNativeProximityInfo(@Nonnull final TouchPositionCorrection touchPositionCorrection) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] iArr5;
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        List<Key> list;
        float f;
        int[] iArr6;
        int[] iArr7;
        List<Key>[] listArr = this.mGridNeighbors;
        int[] iArr8 = new int[this.mGridSize * 16];
        Arrays.fill(iArr8, -1);
        for (int i = 0; i < this.mGridSize; i++) {
            List<Key> list2 = listArr[i];
            int size = list2.size();
            int i2 = i * 16;
            for (int i3 = 0; i3 < size; i3++) {
                Key key = list2.get(i3);
                if (needsProximityInfo(key)) {
                    iArr8[i2] = key.getCode();
                    i2++;
                }
            }
        }
        List<Key> list3 = this.mSortedKeys;
        int proximityInfoKeysCount = getProximityInfoKeysCount(list3);
        int[] iArr9 = new int[proximityInfoKeysCount];
        int[] iArr10 = new int[proximityInfoKeysCount];
        int[] iArr11 = new int[proximityInfoKeysCount];
        int[] iArr12 = new int[proximityInfoKeysCount];
        int[] iArr13 = new int[proximityInfoKeysCount];
        int i4 = 0;
        for (int i5 = 0; i5 < list3.size(); i5++) {
            Key key2 = list3.get(i5);
            if (needsProximityInfo(key2)) {
                iArr9[i4] = key2.getX();
                iArr10[i4] = key2.getY();
                iArr11[i4] = key2.getWidth();
                iArr12[i4] = key2.getHeight();
                iArr13[i4] = key2.getCode();
                i4++;
            }
        }
        if (touchPositionCorrection.isValid()) {
            float[] fArr4 = new float[proximityInfoKeysCount];
            float[] fArr5 = new float[proximityInfoKeysCount];
            float[] fArr6 = new float[proximityInfoKeysCount];
            int rows = touchPositionCorrection.getRows();
            iArr = iArr13;
            iArr4 = iArr11;
            iArr5 = iArr12;
            float fHypot = ((float) Math.hypot(this.mMostCommonKeyWidth, this.mMostCommonKeyHeight)) * DEFAULT_TOUCH_POSITION_CORRECTION_RADIUS;
            int i6 = 0;
            int i7 = 0;
            while (i6 < list3.size()) {
                Key key3 = list3.get(i6);
                if (needsProximityInfo(key3)) {
                    Rect hitBox = key3.getHitBox();
                    fArr4[i7] = hitBox.exactCenterX();
                    fArr5[i7] = hitBox.exactCenterY();
                    fArr6[i7] = fHypot;
                    int i8 = hitBox.top / this.mMostCommonKeyHeight;
                    if (i8 < rows) {
                        int iWidth = hitBox.width();
                        int iHeight = hitBox.height();
                        list = list3;
                        f = fHypot;
                        iArr6 = iArr9;
                        iArr7 = iArr10;
                        float fHypot2 = (float) Math.hypot(iWidth, iHeight);
                        fArr4[i7] = fArr4[i7] + (touchPositionCorrection.getX(i8) * iWidth);
                        fArr5[i7] = fArr5[i7] + (touchPositionCorrection.getY(i8) * iHeight);
                        fArr6[i7] = touchPositionCorrection.getRadius(i8) * fHypot2;
                    } else {
                        list = list3;
                        f = fHypot;
                        iArr6 = iArr9;
                        iArr7 = iArr10;
                    }
                    i7++;
                } else {
                    list = list3;
                    f = fHypot;
                    iArr6 = iArr9;
                    iArr7 = iArr10;
                }
                i6++;
                list3 = list;
                fHypot = f;
                iArr9 = iArr6;
                iArr10 = iArr7;
            }
            iArr2 = iArr9;
            iArr3 = iArr10;
            fArr = fArr4;
            fArr3 = fArr5;
            fArr2 = fArr6;
        } else {
            iArr = iArr13;
            iArr2 = iArr9;
            iArr3 = iArr10;
            iArr4 = iArr11;
            iArr5 = iArr12;
            fArr = null;
            fArr2 = null;
            fArr3 = null;
        }
        return setProximityInfoNative(this.mKeyboardMinWidth, this.mKeyboardHeight, this.mGridWidth, this.mGridHeight, this.mMostCommonKeyWidth, this.mMostCommonKeyHeight, iArr8, proximityInfoKeysCount, iArr2, iArr3, iArr4, iArr5, iArr, fArr, fArr3, fArr2);
    }

    public long getNativeProximityInfo() {
        return this.mNativeProximityInfo;
    }

    protected void finalize() throws Throwable {
        try {
            long j = this.mNativeProximityInfo;
            if (j != 0) {
                releaseProximityInfoNative(j);
                this.mNativeProximityInfo = 0L;
            }
        } finally {
            super.finalize();
        }
    }

    private void computeNearestNeighbors() {
        int i = this.mMostCommonKeyWidth;
        int size = this.mSortedKeys.size();
        int length = this.mGridNeighbors.length;
        int i2 = (int) (i * SEARCH_DISTANCE);
        int i3 = i2 * i2;
        int i4 = this.mGridWidth;
        int i5 = this.mCellWidth;
        int i6 = (i4 * i5) - 1;
        int i7 = this.mGridHeight;
        int i8 = this.mCellHeight;
        int i9 = (i7 * i8) - 1;
        Key[] keyArr = new Key[length * size];
        int[] iArr = new int[length];
        int i10 = i5 / 2;
        int i11 = i8 / 2;
        Iterator<Key> it = this.mSortedKeys.iterator();
        while (it.hasNext()) {
            Key next = it.next();
            if (!next.isSpacer()) {
                int x = next.getX();
                int y = next.getY();
                int i12 = y - i2;
                int i13 = this.mCellHeight;
                Iterator<Key> it2 = it;
                int i14 = i12 % i13;
                int i15 = (i12 - i14) + i11;
                if (i14 <= i11) {
                    i13 = 0;
                }
                int iMax = Math.max(i11, i15 + i13);
                int iMin = Math.min(i9, y + next.getHeight() + i2);
                int i16 = x - i2;
                int i17 = i9;
                int i18 = this.mCellWidth;
                int i19 = i11;
                int i20 = i16 % i18;
                int iMax2 = Math.max(i10, (i16 - i20) + i10 + (i20 <= i10 ? 0 : i18));
                int iMin2 = Math.min(i6, x + next.getWidth() + i2);
                int i21 = ((iMax / this.mCellHeight) * this.mGridWidth) + (iMax2 / this.mCellWidth);
                while (iMax <= iMin) {
                    int i22 = iMax2;
                    int i23 = i21;
                    while (i22 <= iMin2) {
                        int i24 = i2;
                        if (next.squaredDistanceToEdge(i22, iMax) < i3) {
                            keyArr[(i23 * size) + iArr[i23]] = next;
                            iArr[i23] = iArr[i23] + 1;
                        }
                        i23++;
                        i22 += this.mCellWidth;
                        i2 = i24;
                    }
                    i21 += this.mGridWidth;
                    iMax += this.mCellHeight;
                    i2 = i2;
                }
                i9 = i17;
                it = it2;
                i11 = i19;
            }
        }
        for (int i25 = 0; i25 < length; i25++) {
            int i26 = i25 * size;
            int i27 = iArr[i25] + i26;
            ArrayList arrayList = new ArrayList(i27 - i26);
            while (i26 < i27) {
                arrayList.add(keyArr[i26]);
                i26++;
            }
            this.mGridNeighbors[i25] = Collections.unmodifiableList(arrayList);
        }
    }

    public void fillArrayWithNearestKeyCodes(final int x, final int y, final int primaryKeyCode, final int[] dest) {
        int code;
        int length = dest.length;
        int i = 1;
        if (length < 1) {
            return;
        }
        if (primaryKeyCode > 32) {
            dest[0] = primaryKeyCode;
        } else {
            i = 0;
        }
        for (Key key : getNearestKeys(x, y)) {
            if (i >= length || (code = key.getCode()) <= 32) {
                break;
            }
            dest[i] = code;
            i++;
        }
        if (i < length) {
            dest[i] = -1;
        }
    }

    @Nonnull
    public List<Key> getNearestKeys(final int x, final int y) {
        int i;
        if (x >= 0 && x < this.mKeyboardMinWidth && y >= 0 && y < this.mKeyboardHeight && (i = ((y / this.mCellHeight) * this.mGridWidth) + (x / this.mCellWidth)) < this.mGridSize) {
            return this.mGridNeighbors[i];
        }
        return EMPTY_KEY_LIST;
    }
}
