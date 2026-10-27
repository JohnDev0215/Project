package android.view;

import android.content.res.Resources;
import android.graphics.Insets;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.proto.ProtoOutputStream;
import android.view.CutoutSpecification;
import com.android.internal.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class DisplayCutout {
    public static final int BOUNDS_POSITION_BOTTOM = 3;
    public static final int BOUNDS_POSITION_LEFT = 0;
    public static final int BOUNDS_POSITION_LENGTH = 4;
    public static final int BOUNDS_POSITION_RIGHT = 2;
    public static final int BOUNDS_POSITION_TOP = 1;
    private static final Object CACHE_LOCK;
    public static final String EMULATION_OVERLAY_CATEGORY = "com.android.internal.display_cutout_emulation";
    public static final DisplayCutout NO_CUTOUT;
    private static final Pair<Path, DisplayCutout> NULL_PAIR;
    private static final String TAG = "DisplayCutout";
    private static final Rect ZERO_RECT;
    private static Pair<Path, DisplayCutout> sCachedCutout;
    private static float sCachedDensity;
    private static int sCachedDisplayHeight;
    private static int sCachedDisplayWidth;
    private static String sCachedSpec;
    private static Insets sCachedWaterfallInsets;
    private final Bounds mBounds;
    private final Rect mSafeInsets;
    private final Insets mWaterfallInsets;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BoundsPosition {
    }

    private static int atLeastZero(int i) {
        if (i < 0) {
            return 0;
        }
        return i;
    }

    static {
        Rect rect = new Rect();
        ZERO_RECT = rect;
        NO_CUTOUT = new DisplayCutout(rect, Insets.NONE, rect, rect, rect, rect, false);
        Pair<Path, DisplayCutout> pair = new Pair<>(null, null);
        NULL_PAIR = pair;
        CACHE_LOCK = new Object();
        sCachedCutout = pair;
    }

    private static class Bounds {
        private final Rect[] mRects;

        private Bounds(Rect rect, Rect rect2, Rect rect3, Rect rect4, boolean z) {
            this.mRects = new Rect[]{DisplayCutout.getCopyOrRef(rect, z), DisplayCutout.getCopyOrRef(rect2, z), DisplayCutout.getCopyOrRef(rect3, z), DisplayCutout.getCopyOrRef(rect4, z)};
        }

        private Bounds(Rect[] rectArr, boolean z) {
            if (rectArr.length != 4) {
                throw new IllegalArgumentException("rects must have exactly 4 elements: rects=" + Arrays.toString(rectArr));
            }
            int i = 0;
            if (z) {
                this.mRects = new Rect[4];
                while (i < 4) {
                    this.mRects[i] = new Rect(rectArr[i]);
                    i++;
                }
                return;
            }
            int length = rectArr.length;
            while (i < length) {
                if (rectArr[i] == null) {
                    throw new IllegalArgumentException("rects must have non-null elements: rects=" + Arrays.toString(rectArr));
                }
                i++;
            }
            this.mRects = rectArr;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isEmpty() {
            for (Rect rect : this.mRects) {
                if (!rect.isEmpty()) {
                    return false;
                }
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Rect getRect(int i) {
            return new Rect(this.mRects[i]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Rect[] getRects() {
            Rect[] rectArr = new Rect[4];
            for (int i = 0; i < 4; i++) {
                rectArr[i] = new Rect(this.mRects[i]);
            }
            return rectArr;
        }

        public int hashCode() {
            int iHashCode = 0;
            for (Rect rect : this.mRects) {
                iHashCode = (iHashCode * 48271) + rect.hashCode();
            }
            return iHashCode;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof Bounds) {
                return Arrays.deepEquals(this.mRects, ((Bounds) obj).mRects);
            }
            return false;
        }

        public String toString() {
            return "Bounds=" + Arrays.toString(this.mRects);
        }
    }

    public DisplayCutout(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4) {
        this(insets.toRect(), Insets.NONE, rect, rect2, rect3, rect4, true);
    }

    public DisplayCutout(Insets insets, Rect rect, Rect rect2, Rect rect3, Rect rect4, Insets insets2) {
        this(insets.toRect(), insets2, rect, rect2, rect3, rect4, true);
    }

    @Deprecated
    public DisplayCutout(Rect rect, List<Rect> list) {
        this(rect, Insets.NONE, extractBoundsFromList(rect, list), true);
    }

    private DisplayCutout(Rect rect, Insets insets, Rect rect2, Rect rect3, Rect rect4, Rect rect5, boolean z) {
        this.mSafeInsets = getCopyOrRef(rect, z);
        this.mWaterfallInsets = insets == null ? Insets.NONE : insets;
        this.mBounds = new Bounds(rect2, rect3, rect4, rect5, z);
    }

    private DisplayCutout(Rect rect, Insets insets, Rect[] rectArr, boolean z) {
        this.mSafeInsets = getCopyOrRef(rect, z);
        this.mWaterfallInsets = insets == null ? Insets.NONE : insets;
        this.mBounds = new Bounds(rectArr, z);
    }

    private DisplayCutout(Rect rect, Insets insets, Bounds bounds) {
        this.mSafeInsets = rect;
        this.mWaterfallInsets = insets == null ? Insets.NONE : insets;
        this.mBounds = bounds;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Rect getCopyOrRef(Rect rect, boolean z) {
        if (rect == null) {
            return ZERO_RECT;
        }
        return z ? new Rect(rect) : rect;
    }

    public Insets getWaterfallInsets() {
        return this.mWaterfallInsets;
    }

    public static Rect[] extractBoundsFromList(Rect rect, List<Rect> list) {
        Rect[] rectArr = new Rect[4];
        for (int i = 0; i < 4; i++) {
            rectArr[i] = ZERO_RECT;
        }
        if (rect != null && list != null) {
            boolean z = rect.top > 0 || rect.bottom > 0;
            for (Rect rect2 : list) {
                if (z) {
                    if (rect2.top == 0) {
                        rectArr[1] = rect2;
                    } else {
                        rectArr[3] = rect2;
                    }
                } else if (rect2.left == 0) {
                    rectArr[0] = rect2;
                } else {
                    rectArr[2] = rect2;
                }
            }
        }
        return rectArr;
    }

    public boolean isBoundsEmpty() {
        return this.mBounds.isEmpty();
    }

    public boolean isEmpty() {
        return this.mSafeInsets.equals(ZERO_RECT);
    }

    public int getSafeInsetTop() {
        return this.mSafeInsets.top;
    }

    public int getSafeInsetBottom() {
        return this.mSafeInsets.bottom;
    }

    public int getSafeInsetLeft() {
        return this.mSafeInsets.left;
    }

    public int getSafeInsetRight() {
        return this.mSafeInsets.right;
    }

    public Rect getSafeInsets() {
        return new Rect(this.mSafeInsets);
    }

    public List<Rect> getBoundingRects() {
        ArrayList arrayList = new ArrayList();
        for (Rect rect : getBoundingRectsAll()) {
            if (!rect.isEmpty()) {
                arrayList.add(new Rect(rect));
            }
        }
        return arrayList;
    }

    public Rect[] getBoundingRectsAll() {
        return this.mBounds.getRects();
    }

    public Rect getBoundingRectLeft() {
        return this.mBounds.getRect(0);
    }

    public Rect getBoundingRectTop() {
        return this.mBounds.getRect(1);
    }

    public Rect getBoundingRectRight() {
        return this.mBounds.getRect(2);
    }

    public Rect getBoundingRectBottom() {
        return this.mBounds.getRect(3);
    }

    public int hashCode() {
        return (((this.mSafeInsets.hashCode() * 48271) + this.mBounds.hashCode()) * 48271) + this.mWaterfallInsets.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DisplayCutout)) {
            return false;
        }
        DisplayCutout displayCutout = (DisplayCutout) obj;
        return this.mSafeInsets.equals(displayCutout.mSafeInsets) && this.mBounds.equals(displayCutout.mBounds) && this.mWaterfallInsets.equals(displayCutout.mWaterfallInsets);
    }

    public String toString() {
        return "DisplayCutout{insets=" + this.mSafeInsets + " waterfall=" + this.mWaterfallInsets + " boundingRect={" + this.mBounds + "}}";
    }

    public void dumpDebug(ProtoOutputStream protoOutputStream, long j) {
        long jStart = protoOutputStream.start(j);
        this.mSafeInsets.dumpDebug(protoOutputStream, 1146756268033L);
        this.mBounds.getRect(0).dumpDebug(protoOutputStream, 1146756268035L);
        this.mBounds.getRect(1).dumpDebug(protoOutputStream, 1146756268036L);
        this.mBounds.getRect(2).dumpDebug(protoOutputStream, 1146756268037L);
        this.mBounds.getRect(3).dumpDebug(protoOutputStream, 1146756268038L);
        this.mWaterfallInsets.toRect().dumpDebug(protoOutputStream, 1146756268033L);
        protoOutputStream.end(jStart);
    }

    public DisplayCutout inset(int i, int i2, int i3, int i4) {
        if ((i == 0 && i2 == 0 && i3 == 0 && i4 == 0) || (isBoundsEmpty() && this.mWaterfallInsets.equals(Insets.NONE))) {
            return this;
        }
        Rect rectInsetInsets = insetInsets(i, i2, i3, i4, new Rect(this.mSafeInsets));
        if (i == 0 && i2 == 0 && this.mSafeInsets.equals(rectInsetInsets)) {
            return this;
        }
        Rect rectInsetInsets2 = insetInsets(i, i2, i3, i4, this.mWaterfallInsets.toRect());
        Rect[] rects = this.mBounds.getRects();
        for (int i5 = 0; i5 < rects.length; i5++) {
            if (!rects[i5].equals(ZERO_RECT)) {
                rects[i5].offset(-i, -i2);
            }
        }
        return new DisplayCutout(rectInsetInsets, Insets.of(rectInsetInsets2), rects, false);
    }

    private Rect insetInsets(int i, int i2, int i3, int i4, Rect rect) {
        if (i2 > 0 || rect.top > 0) {
            rect.top = atLeastZero(rect.top - i2);
        }
        if (i4 > 0 || rect.bottom > 0) {
            rect.bottom = atLeastZero(rect.bottom - i4);
        }
        if (i > 0 || rect.left > 0) {
            rect.left = atLeastZero(rect.left - i);
        }
        if (i3 > 0 || rect.right > 0) {
            rect.right = atLeastZero(rect.right - i3);
        }
        return rect;
    }

    public DisplayCutout replaceSafeInsets(Rect rect) {
        return new DisplayCutout(new Rect(rect), this.mWaterfallInsets, this.mBounds);
    }

    public static DisplayCutout fromBoundingRect(int i, int i2, int i3, int i4, int i5) {
        Rect[] rectArr = new Rect[4];
        int i6 = 0;
        while (i6 < 4) {
            rectArr[i6] = i5 == i6 ? new Rect(i, i2, i3, i4) : new Rect();
            i6++;
        }
        return new DisplayCutout(ZERO_RECT, Insets.NONE, rectArr, false);
    }

    public static DisplayCutout fromBoundsAndWaterfall(Rect[] rectArr, Insets insets) {
        return new DisplayCutout(ZERO_RECT, insets, rectArr, false);
    }

    public static DisplayCutout fromBounds(Rect[] rectArr) {
        return new DisplayCutout(ZERO_RECT, Insets.NONE, rectArr, false);
    }

    public static DisplayCutout fromResourcesRectApproximation(Resources resources, int i, int i2) {
        return fromSpec(resources.getString(R.string.config_mainBuiltInDisplayCutoutRectApproximation), i, i2, DisplayMetrics.DENSITY_DEVICE_STABLE / 160.0f, loadWaterfallInset(resources));
    }

    public static Path pathFromResources(Resources resources, int i, int i2) {
        return pathAndDisplayCutoutFromSpec(resources.getString(R.string.config_mainBuiltInDisplayCutout), i, i2, DisplayMetrics.DENSITY_DEVICE_STABLE / 160.0f, loadWaterfallInset(resources)).first;
    }

    public static DisplayCutout fromSpec(String str, int i, int i2, float f, Insets insets) {
        return pathAndDisplayCutoutFromSpec(str, i, i2, f, insets).second;
    }

    private static Pair<Path, DisplayCutout> pathAndDisplayCutoutFromSpec(String str, int i, int i2, float f, Insets insets) {
        if (TextUtils.isEmpty(str) && insets.equals(Insets.NONE)) {
            return NULL_PAIR;
        }
        Object obj = CACHE_LOCK;
        synchronized (obj) {
            if (str.equals(sCachedSpec) && sCachedDisplayWidth == i && sCachedDisplayHeight == i2 && sCachedDensity == f && insets.equals(sCachedWaterfallInsets)) {
                return sCachedCutout;
            }
            String strTrim = str.trim();
            CutoutSpecification cutoutSpecification = new CutoutSpecification.Parser(f, i, i2).parse(strTrim);
            Rect safeInset = cutoutSpecification.getSafeInset();
            Rect leftBound = cutoutSpecification.getLeftBound();
            Rect topBound = cutoutSpecification.getTopBound();
            Rect rightBound = cutoutSpecification.getRightBound();
            Rect bottomBound = cutoutSpecification.getBottomBound();
            if (!insets.equals(Insets.NONE)) {
                safeInset.set(Math.max(insets.left, safeInset.left), Math.max(insets.top, safeInset.top), Math.max(insets.right, safeInset.right), Math.max(insets.bottom, safeInset.bottom));
            }
            Pair<Path, DisplayCutout> pair = new Pair<>(cutoutSpecification.getPath(), new DisplayCutout(safeInset, insets, leftBound, topBound, rightBound, bottomBound, false));
            synchronized (obj) {
                sCachedSpec = strTrim;
                sCachedDisplayWidth = i;
                sCachedDisplayHeight = i2;
                sCachedDensity = f;
                sCachedCutout = pair;
                sCachedWaterfallInsets = insets;
            }
            return pair;
        }
    }

    private static Insets loadWaterfallInset(Resources resources) {
        return Insets.of(resources.getDimensionPixelSize(R.dimen.waterfall_display_left_edge_size), resources.getDimensionPixelSize(R.dimen.waterfall_display_top_edge_size), resources.getDimensionPixelSize(R.dimen.waterfall_display_right_edge_size), resources.getDimensionPixelSize(R.dimen.waterfall_display_bottom_edge_size));
    }

    public static final class ParcelableWrapper implements Parcelable {
        public static final Parcelable.Creator<ParcelableWrapper> CREATOR = new Parcelable.Creator<ParcelableWrapper>() { // from class: android.view.DisplayCutout.ParcelableWrapper.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ParcelableWrapper createFromParcel(Parcel parcel) {
                return new ParcelableWrapper(ParcelableWrapper.readCutoutFromParcel(parcel));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ParcelableWrapper[] newArray(int i) {
                return new ParcelableWrapper[i];
            }
        };
        private DisplayCutout mInner;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ParcelableWrapper() {
            this(DisplayCutout.NO_CUTOUT);
        }

        public ParcelableWrapper(DisplayCutout displayCutout) {
            this.mInner = displayCutout;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            writeCutoutToParcel(this.mInner, parcel, i);
        }

        public static void writeCutoutToParcel(DisplayCutout displayCutout, Parcel parcel, int i) {
            if (displayCutout == null) {
                parcel.writeInt(-1);
                return;
            }
            if (displayCutout == DisplayCutout.NO_CUTOUT) {
                parcel.writeInt(0);
                return;
            }
            parcel.writeInt(1);
            parcel.writeTypedObject(displayCutout.mSafeInsets, i);
            parcel.writeTypedArray(displayCutout.mBounds.getRects(), i);
            parcel.writeTypedObject(displayCutout.mWaterfallInsets, i);
        }

        public void readFromParcel(Parcel parcel) {
            this.mInner = readCutoutFromParcel(parcel);
        }

        public static DisplayCutout readCutoutFromParcel(Parcel parcel) {
            int i = parcel.readInt();
            if (i == -1) {
                return null;
            }
            if (i == 0) {
                return DisplayCutout.NO_CUTOUT;
            }
            Rect rect = (Rect) parcel.readTypedObject(Rect.CREATOR);
            Rect[] rectArr = new Rect[4];
            parcel.readTypedArray(rectArr, Rect.CREATOR);
            return new DisplayCutout(rect, (Insets) parcel.readTypedObject(Insets.CREATOR), rectArr, false);
        }

        public DisplayCutout get() {
            return this.mInner;
        }

        public void set(ParcelableWrapper parcelableWrapper) {
            this.mInner = parcelableWrapper.get();
        }

        public void set(DisplayCutout displayCutout) {
            this.mInner = displayCutout;
        }

        public int hashCode() {
            return this.mInner.hashCode();
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParcelableWrapper) && this.mInner.equals(((ParcelableWrapper) obj).mInner);
        }

        public String toString() {
            return String.valueOf(this.mInner);
        }
    }
}
