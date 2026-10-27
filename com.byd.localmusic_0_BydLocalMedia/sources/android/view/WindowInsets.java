package android.view;

import android.graphics.Insets;
import android.graphics.Rect;
import com.android.internal.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class WindowInsets {
    public static final WindowInsets CONSUMED = new WindowInsets((Rect) null, null, false, false, null);
    private final boolean mAlwaysConsumeSystemBars;
    private final boolean mCompatIgnoreVisibility;
    private final int mCompatInsetsTypes;
    private final DisplayCutout mDisplayCutout;
    private final boolean mDisplayCutoutConsumed;
    private final boolean mIsRound;
    private final boolean mStableInsetsConsumed;
    private final boolean mSystemWindowInsetsConsumed;
    private Rect mTempRect;
    private final Insets[] mTypeInsetsMap;
    private final Insets[] mTypeMaxInsetsMap;
    private final boolean[] mTypeVisibilityMap;

    @Deprecated
    public WindowInsets consumeStableInsets() {
        return this;
    }

    public WindowInsets(Rect rect, Rect rect2, boolean z, boolean z2, DisplayCutout displayCutout) {
        this(createCompatTypeMap(rect), createCompatTypeMap(rect2), createCompatVisibilityMap(createCompatTypeMap(rect)), z, z2, displayCutout, Type.systemBars(), false);
    }

    public WindowInsets(Insets[] insetsArr, Insets[] insetsArr2, boolean[] zArr, boolean z, boolean z2, DisplayCutout displayCutout, int i, boolean z3) {
        boolean z4 = insetsArr == null;
        this.mSystemWindowInsetsConsumed = z4;
        this.mTypeInsetsMap = z4 ? new Insets[9] : (Insets[]) insetsArr.clone();
        boolean z5 = insetsArr2 == null;
        this.mStableInsetsConsumed = z5;
        this.mTypeMaxInsetsMap = z5 ? new Insets[9] : (Insets[]) insetsArr2.clone();
        this.mTypeVisibilityMap = zArr;
        this.mIsRound = z;
        this.mAlwaysConsumeSystemBars = z2;
        this.mCompatInsetsTypes = i;
        this.mCompatIgnoreVisibility = z3;
        boolean z6 = displayCutout == null;
        this.mDisplayCutoutConsumed = z6;
        this.mDisplayCutout = (z6 || displayCutout.isEmpty()) ? null : displayCutout;
    }

    public WindowInsets(WindowInsets windowInsets) {
        this(windowInsets.mSystemWindowInsetsConsumed ? null : windowInsets.mTypeInsetsMap, windowInsets.mStableInsetsConsumed ? null : windowInsets.mTypeMaxInsetsMap, windowInsets.mTypeVisibilityMap, windowInsets.mIsRound, windowInsets.mAlwaysConsumeSystemBars, displayCutoutCopyConstructorArgument(windowInsets), windowInsets.mCompatInsetsTypes, windowInsets.mCompatIgnoreVisibility);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DisplayCutout displayCutoutCopyConstructorArgument(WindowInsets windowInsets) {
        if (windowInsets.mDisplayCutoutConsumed) {
            return null;
        }
        DisplayCutout displayCutout = windowInsets.mDisplayCutout;
        return displayCutout == null ? DisplayCutout.NO_CUTOUT : displayCutout;
    }

    static Insets getInsets(Insets[] insetsArr, int i) {
        Insets insets;
        Insets insetsMax = null;
        for (int i2 = 1; i2 <= 256; i2 <<= 1) {
            if ((i & i2) != 0 && (insets = insetsArr[Type.indexOf(i2)]) != null) {
                insetsMax = insetsMax == null ? insets : Insets.max(insetsMax, insets);
            }
        }
        return insetsMax == null ? Insets.NONE : insetsMax;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setInsets(Insets[] insetsArr, int i, Insets insets) {
        for (int i2 = 1; i2 <= 256; i2 <<= 1) {
            if ((i & i2) != 0) {
                insetsArr[Type.indexOf(i2)] = insets;
            }
        }
    }

    public WindowInsets(Rect rect) {
        this(createCompatTypeMap(rect), null, new boolean[9], false, false, null, Type.systemBars(), false);
    }

    private static Insets[] createCompatTypeMap(Rect rect) {
        if (rect == null) {
            return null;
        }
        Insets[] insetsArr = new Insets[9];
        assignCompatInsets(insetsArr, rect);
        return insetsArr;
    }

    public static void assignCompatInsets(Insets[] insetsArr, Rect rect) {
        insetsArr[Type.indexOf(1)] = Insets.of(0, rect.top, 0, 0);
        insetsArr[Type.indexOf(2)] = Insets.of(rect.left, 0, rect.right, rect.bottom);
    }

    private static boolean[] createCompatVisibilityMap(Insets[] insetsArr) {
        boolean[] zArr = new boolean[9];
        if (insetsArr == null) {
            return zArr;
        }
        for (int i = 1; i <= 256; i <<= 1) {
            int iIndexOf = Type.indexOf(i);
            if (!Insets.NONE.equals(insetsArr[iIndexOf])) {
                zArr[iIndexOf] = true;
            }
        }
        return zArr;
    }

    @Deprecated
    public Rect getSystemWindowInsetsAsRect() {
        if (this.mTempRect == null) {
            this.mTempRect = new Rect();
        }
        Insets systemWindowInsets = getSystemWindowInsets();
        this.mTempRect.set(systemWindowInsets.left, systemWindowInsets.top, systemWindowInsets.right, systemWindowInsets.bottom);
        return this.mTempRect;
    }

    @Deprecated
    public Insets getSystemWindowInsets() {
        Insets insets;
        if (this.mCompatIgnoreVisibility) {
            insets = getInsetsIgnoringVisibility(this.mCompatInsetsTypes & (~Type.ime()));
        } else {
            insets = getInsets(this.mCompatInsetsTypes);
        }
        return ((this.mCompatInsetsTypes & Type.ime()) == 0 || !this.mCompatIgnoreVisibility) ? insets : Insets.max(insets, getInsets(Type.ime()));
    }

    public Insets getInsets(int i) {
        return getInsets(this.mTypeInsetsMap, i);
    }

    public Insets getInsetsIgnoringVisibility(int i) {
        if ((i & 8) != 0) {
            throw new IllegalArgumentException("Unable to query the maximum insets for IME");
        }
        return getInsets(this.mTypeMaxInsetsMap, i);
    }

    public boolean isVisible(int i) {
        for (int i2 = 1; i2 <= 256; i2 <<= 1) {
            if ((i & i2) != 0 && !this.mTypeVisibilityMap[Type.indexOf(i2)]) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public int getSystemWindowInsetLeft() {
        return getSystemWindowInsets().left;
    }

    @Deprecated
    public int getSystemWindowInsetTop() {
        return getSystemWindowInsets().top;
    }

    @Deprecated
    public int getSystemWindowInsetRight() {
        return getSystemWindowInsets().right;
    }

    @Deprecated
    public int getSystemWindowInsetBottom() {
        return getSystemWindowInsets().bottom;
    }

    @Deprecated
    public boolean hasSystemWindowInsets() {
        return !getSystemWindowInsets().equals(Insets.NONE);
    }

    public boolean hasInsets() {
        return (getInsets(this.mTypeInsetsMap, Type.all()).equals(Insets.NONE) && getInsets(this.mTypeMaxInsetsMap, Type.all()).equals(Insets.NONE) && this.mDisplayCutout == null) ? false : true;
    }

    public DisplayCutout getDisplayCutout() {
        return this.mDisplayCutout;
    }

    @Deprecated
    public WindowInsets consumeDisplayCutout() {
        return new WindowInsets(this.mSystemWindowInsetsConsumed ? null : this.mTypeInsetsMap, this.mStableInsetsConsumed ? null : this.mTypeMaxInsetsMap, this.mTypeVisibilityMap, this.mIsRound, this.mAlwaysConsumeSystemBars, null, this.mCompatInsetsTypes, this.mCompatIgnoreVisibility);
    }

    public boolean isConsumed() {
        return this.mSystemWindowInsetsConsumed && this.mStableInsetsConsumed && this.mDisplayCutoutConsumed;
    }

    public boolean isRound() {
        return this.mIsRound;
    }

    @Deprecated
    public WindowInsets consumeSystemWindowInsets() {
        return new WindowInsets(null, null, this.mTypeVisibilityMap, this.mIsRound, this.mAlwaysConsumeSystemBars, displayCutoutCopyConstructorArgument(this), this.mCompatInsetsTypes, this.mCompatIgnoreVisibility);
    }

    @Deprecated
    public WindowInsets replaceSystemWindowInsets(int i, int i2, int i3, int i4) {
        return this.mSystemWindowInsetsConsumed ? this : new Builder(this).setSystemWindowInsets(Insets.of(i, i2, i3, i4)).build();
    }

    @Deprecated
    public WindowInsets replaceSystemWindowInsets(Rect rect) {
        return replaceSystemWindowInsets(rect.left, rect.top, rect.right, rect.bottom);
    }

    @Deprecated
    public Insets getStableInsets() {
        return getInsets(this.mTypeMaxInsetsMap, this.mCompatInsetsTypes);
    }

    @Deprecated
    public int getStableInsetTop() {
        return getStableInsets().top;
    }

    @Deprecated
    public int getStableInsetLeft() {
        return getStableInsets().left;
    }

    @Deprecated
    public int getStableInsetRight() {
        return getStableInsets().right;
    }

    @Deprecated
    public int getStableInsetBottom() {
        return getStableInsets().bottom;
    }

    @Deprecated
    public boolean hasStableInsets() {
        return !getStableInsets().equals(Insets.NONE);
    }

    @Deprecated
    public Insets getSystemGestureInsets() {
        return getInsets(this.mTypeInsetsMap, 16);
    }

    @Deprecated
    public Insets getMandatorySystemGestureInsets() {
        return getInsets(this.mTypeInsetsMap, 32);
    }

    @Deprecated
    public Insets getTappableElementInsets() {
        return getInsets(this.mTypeInsetsMap, 64);
    }

    public boolean shouldAlwaysConsumeSystemBars() {
        return this.mAlwaysConsumeSystemBars;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("WindowInsets{\n    ");
        for (int i = 0; i < 9; i++) {
            Insets insets = this.mTypeInsetsMap[i];
            Insets insets2 = this.mTypeMaxInsetsMap[i];
            boolean z = this.mTypeVisibilityMap[i];
            if (!Insets.NONE.equals(insets) || !Insets.NONE.equals(insets2) || z) {
                sb.append(Type.toString(1 << i));
                sb.append("=");
                sb.append(insets);
                sb.append(" max=");
                sb.append(insets2);
                sb.append(" vis=");
                sb.append(z);
                sb.append("\n    ");
            }
        }
        sb.append(this.mDisplayCutout != null ? "cutout=" + this.mDisplayCutout : "");
        sb.append("\n    ");
        sb.append(isRound() ? "round" : "");
        sb.append("}");
        return sb.toString();
    }

    @Deprecated
    public WindowInsets inset(Rect rect) {
        return inset(rect.left, rect.top, rect.right, rect.bottom);
    }

    public WindowInsets inset(Insets insets) {
        Objects.requireNonNull(insets);
        return inset(insets.left, insets.top, insets.right, insets.bottom);
    }

    public WindowInsets inset(int i, int i2, int i3, int i4) {
        DisplayCutout displayCutoutInset;
        Preconditions.checkArgumentNonnegative(i);
        Preconditions.checkArgumentNonnegative(i2);
        Preconditions.checkArgumentNonnegative(i3);
        Preconditions.checkArgumentNonnegative(i4);
        Insets[] insetsArrInsetInsets = this.mSystemWindowInsetsConsumed ? null : insetInsets(this.mTypeInsetsMap, i, i2, i3, i4);
        Insets[] insetsArrInsetInsets2 = this.mStableInsetsConsumed ? null : insetInsets(this.mTypeMaxInsetsMap, i, i2, i3, i4);
        boolean[] zArr = this.mTypeVisibilityMap;
        boolean z = this.mIsRound;
        boolean z2 = this.mAlwaysConsumeSystemBars;
        if (this.mDisplayCutoutConsumed) {
            displayCutoutInset = null;
        } else {
            DisplayCutout displayCutout = this.mDisplayCutout;
            if (displayCutout == null) {
                displayCutoutInset = DisplayCutout.NO_CUTOUT;
            } else {
                displayCutoutInset = displayCutout.inset(i, i2, i3, i4);
            }
        }
        return new WindowInsets(insetsArrInsetInsets, insetsArrInsetInsets2, zArr, z, z2, displayCutoutInset, this.mCompatInsetsTypes, this.mCompatIgnoreVisibility);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WindowInsets)) {
            return false;
        }
        WindowInsets windowInsets = (WindowInsets) obj;
        return this.mIsRound == windowInsets.mIsRound && this.mAlwaysConsumeSystemBars == windowInsets.mAlwaysConsumeSystemBars && this.mSystemWindowInsetsConsumed == windowInsets.mSystemWindowInsetsConsumed && this.mStableInsetsConsumed == windowInsets.mStableInsetsConsumed && this.mDisplayCutoutConsumed == windowInsets.mDisplayCutoutConsumed && Arrays.equals(this.mTypeInsetsMap, windowInsets.mTypeInsetsMap) && Arrays.equals(this.mTypeMaxInsetsMap, windowInsets.mTypeMaxInsetsMap) && Arrays.equals(this.mTypeVisibilityMap, windowInsets.mTypeVisibilityMap) && Objects.equals(this.mDisplayCutout, windowInsets.mDisplayCutout);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(Arrays.hashCode(this.mTypeInsetsMap)), Integer.valueOf(Arrays.hashCode(this.mTypeMaxInsetsMap)), Integer.valueOf(Arrays.hashCode(this.mTypeVisibilityMap)), Boolean.valueOf(this.mIsRound), this.mDisplayCutout, Boolean.valueOf(this.mAlwaysConsumeSystemBars), Boolean.valueOf(this.mSystemWindowInsetsConsumed), Boolean.valueOf(this.mStableInsetsConsumed), Boolean.valueOf(this.mDisplayCutoutConsumed));
    }

    private static Insets[] insetInsets(Insets[] insetsArr, int i, int i2, int i3, int i4) {
        Insets insetsInsetInsets;
        boolean z = false;
        for (int i5 = 0; i5 < 9; i5++) {
            Insets insets = insetsArr[i5];
            if (insets != null && (insetsInsetInsets = insetInsets(insets, i, i2, i3, i4)) != insets) {
                if (!z) {
                    insetsArr = (Insets[]) insetsArr.clone();
                    z = true;
                }
                insetsArr[i5] = insetsInsetInsets;
            }
        }
        return insetsArr;
    }

    static Insets insetInsets(Insets insets, int i, int i2, int i3, int i4) {
        int iMax = Math.max(0, insets.left - i);
        int iMax2 = Math.max(0, insets.top - i2);
        int iMax3 = Math.max(0, insets.right - i3);
        int iMax4 = Math.max(0, insets.bottom - i4);
        return (iMax == i && iMax2 == i2 && iMax3 == i3 && iMax4 == i4) ? insets : Insets.of(iMax, iMax2, iMax3, iMax4);
    }

    boolean isSystemWindowInsetsConsumed() {
        return this.mSystemWindowInsetsConsumed;
    }

    public static final class Builder {
        private boolean mAlwaysConsumeSystemBars;
        private DisplayCutout mDisplayCutout;
        private boolean mIsRound;
        private boolean mStableInsetsConsumed;
        private boolean mSystemInsetsConsumed;
        private final Insets[] mTypeInsetsMap;
        private final Insets[] mTypeMaxInsetsMap;
        private final boolean[] mTypeVisibilityMap;

        public Builder() {
            this.mSystemInsetsConsumed = true;
            this.mStableInsetsConsumed = true;
            this.mTypeInsetsMap = new Insets[9];
            this.mTypeMaxInsetsMap = new Insets[9];
            this.mTypeVisibilityMap = new boolean[9];
        }

        public Builder(WindowInsets windowInsets) {
            this.mSystemInsetsConsumed = true;
            this.mStableInsetsConsumed = true;
            this.mTypeInsetsMap = (Insets[]) windowInsets.mTypeInsetsMap.clone();
            this.mTypeMaxInsetsMap = (Insets[]) windowInsets.mTypeMaxInsetsMap.clone();
            this.mTypeVisibilityMap = (boolean[]) windowInsets.mTypeVisibilityMap.clone();
            this.mSystemInsetsConsumed = windowInsets.mSystemWindowInsetsConsumed;
            this.mStableInsetsConsumed = windowInsets.mStableInsetsConsumed;
            this.mDisplayCutout = WindowInsets.displayCutoutCopyConstructorArgument(windowInsets);
            this.mIsRound = windowInsets.mIsRound;
            this.mAlwaysConsumeSystemBars = windowInsets.mAlwaysConsumeSystemBars;
        }

        @Deprecated
        public Builder setSystemWindowInsets(Insets insets) {
            Preconditions.checkNotNull(insets);
            WindowInsets.assignCompatInsets(this.mTypeInsetsMap, insets.toRect());
            this.mSystemInsetsConsumed = false;
            return this;
        }

        @Deprecated
        public Builder setSystemGestureInsets(Insets insets) {
            WindowInsets.setInsets(this.mTypeInsetsMap, 16, insets);
            return this;
        }

        @Deprecated
        public Builder setMandatorySystemGestureInsets(Insets insets) {
            WindowInsets.setInsets(this.mTypeInsetsMap, 32, insets);
            return this;
        }

        @Deprecated
        public Builder setTappableElementInsets(Insets insets) {
            WindowInsets.setInsets(this.mTypeInsetsMap, 64, insets);
            return this;
        }

        public Builder setInsets(int i, Insets insets) {
            Preconditions.checkNotNull(insets);
            WindowInsets.setInsets(this.mTypeInsetsMap, i, insets);
            this.mSystemInsetsConsumed = false;
            return this;
        }

        public Builder setInsetsIgnoringVisibility(int i, Insets insets) throws IllegalArgumentException {
            if (i == 8) {
                throw new IllegalArgumentException("Maximum inset not available for IME");
            }
            Preconditions.checkNotNull(insets);
            WindowInsets.setInsets(this.mTypeMaxInsetsMap, i, insets);
            this.mStableInsetsConsumed = false;
            return this;
        }

        public Builder setVisible(int i, boolean z) {
            for (int i2 = 1; i2 <= 256; i2 <<= 1) {
                if ((i & i2) != 0) {
                    this.mTypeVisibilityMap[Type.indexOf(i2)] = z;
                }
            }
            return this;
        }

        @Deprecated
        public Builder setStableInsets(Insets insets) {
            Preconditions.checkNotNull(insets);
            WindowInsets.assignCompatInsets(this.mTypeMaxInsetsMap, insets.toRect());
            this.mStableInsetsConsumed = false;
            return this;
        }

        public Builder setDisplayCutout(DisplayCutout displayCutout) {
            if (displayCutout == null) {
                displayCutout = DisplayCutout.NO_CUTOUT;
            }
            this.mDisplayCutout = displayCutout;
            if (!displayCutout.isEmpty()) {
                Insets insetsOf = Insets.of(this.mDisplayCutout.getSafeInsets());
                int iIndexOf = Type.indexOf(128);
                this.mTypeInsetsMap[iIndexOf] = insetsOf;
                this.mTypeMaxInsetsMap[iIndexOf] = insetsOf;
                this.mTypeVisibilityMap[iIndexOf] = true;
            }
            return this;
        }

        public Builder setRound(boolean z) {
            this.mIsRound = z;
            return this;
        }

        public Builder setAlwaysConsumeSystemBars(boolean z) {
            this.mAlwaysConsumeSystemBars = z;
            return this;
        }

        public WindowInsets build() {
            return new WindowInsets(this.mSystemInsetsConsumed ? null : this.mTypeInsetsMap, this.mStableInsetsConsumed ? null : this.mTypeMaxInsetsMap, this.mTypeVisibilityMap, this.mIsRound, this.mAlwaysConsumeSystemBars, this.mDisplayCutout, Type.systemBars(), false);
        }
    }

    public static final class Type {
        static final int CAPTION_BAR = 4;
        static final int DISPLAY_CUTOUT = 128;
        static final int FIRST = 1;
        static final int IME = 8;
        static final int LAST = 256;
        static final int MANDATORY_SYSTEM_GESTURES = 32;
        static final int NAVIGATION_BARS = 2;
        static final int SIZE = 9;
        static final int STATUS_BARS = 1;
        static final int SYSTEM_GESTURES = 16;
        static final int TAPPABLE_ELEMENT = 64;
        static final int WINDOW_DECOR = 256;

        @Retention(RetentionPolicy.SOURCE)
        public @interface InsetsType {
        }

        public static int all() {
            return -1;
        }

        public static int captionBar() {
            return 4;
        }

        public static int displayCutout() {
            return 128;
        }

        public static int ime() {
            return 8;
        }

        public static int mandatorySystemGestures() {
            return 32;
        }

        public static int navigationBars() {
            return 2;
        }

        public static int statusBars() {
            return 1;
        }

        public static int systemBars() {
            return 7;
        }

        public static int systemGestures() {
            return 16;
        }

        public static int tappableElement() {
            return 64;
        }

        static int indexOf(int i) {
            if (i == 1) {
                return 0;
            }
            if (i == 2) {
                return 1;
            }
            if (i == 4) {
                return 2;
            }
            if (i == 8) {
                return 3;
            }
            if (i == 16) {
                return 4;
            }
            if (i == 32) {
                return 5;
            }
            if (i == 64) {
                return 6;
            }
            if (i == 128) {
                return 7;
            }
            if (i == 256) {
                return 8;
            }
            throw new IllegalArgumentException("type needs to be >= FIRST and <= LAST, type=" + i);
        }

        static String toString(int i) {
            StringBuilder sb = new StringBuilder();
            if ((i & 1) != 0) {
                sb.append("statusBars |");
            }
            if ((i & 2) != 0) {
                sb.append("navigationBars |");
            }
            if ((i & 8) != 0) {
                sb.append("ime |");
            }
            if ((i & 16) != 0) {
                sb.append("systemGestures |");
            }
            if ((i & 32) != 0) {
                sb.append("mandatorySystemGestures |");
            }
            if ((i & 64) != 0) {
                sb.append("tappableElement |");
            }
            if ((i & 128) != 0) {
                sb.append("displayCutout |");
            }
            if ((i & 256) != 0) {
                sb.append("windowDecor |");
            }
            if (sb.length() > 0) {
                sb.delete(sb.length() - 2, sb.length());
            }
            return sb.toString();
        }

        private Type() {
        }

        public static boolean isVisibleInsetsType(int i, int i2) {
            return ((systemBars() & i) == 0 && ((i2 & 240) == 48 || (i & ime()) == 0)) ? false : true;
        }
    }

    public static final class Side {
        public static final int BOTTOM = 8;
        public static final int LEFT = 1;
        public static final int RIGHT = 4;
        public static final int TOP = 2;

        @Retention(RetentionPolicy.SOURCE)
        public @interface InsetsSide {
        }

        public static int all() {
            return 15;
        }

        private Side() {
        }
    }
}
