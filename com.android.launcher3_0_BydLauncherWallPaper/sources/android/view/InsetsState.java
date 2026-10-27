package android.view;

import android.graphics.Insets;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.ArraySet;
import android.util.SparseIntArray;
import android.view.WindowInsets;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

/* JADX INFO: loaded from: classes3.dex */
public class InsetsState implements Parcelable {
    static final int FIRST_TYPE = 0;
    static final int ISIDE_BOTTOM = 3;
    static final int ISIDE_FLOATING = 4;
    static final int ISIDE_LEFT = 0;
    static final int ISIDE_RIGHT = 2;
    static final int ISIDE_TOP = 1;
    static final int ISIDE_UNKNOWN = 5;
    public static final int ITYPE_BOTTOM_DISPLAY_CUTOUT = 16;
    public static final int ITYPE_BOTTOM_GESTURES = 4;
    public static final int ITYPE_BOTTOM_MANDATORY_GESTURES = 8;
    public static final int ITYPE_BOTTOM_TAPPABLE_ELEMENT = 12;
    public static final int ITYPE_CAPTION_BAR = 2;
    public static final int ITYPE_CLIMATE_BAR = 18;
    public static final int ITYPE_EXTRA_NAVIGATION_BAR = 19;
    public static final int ITYPE_IME = 17;
    public static final int ITYPE_INVALID = -1;
    public static final int ITYPE_LEFT_DISPLAY_CUTOUT = 13;
    public static final int ITYPE_LEFT_GESTURES = 5;
    public static final int ITYPE_LEFT_MANDATORY_GESTURES = 9;
    public static final int ITYPE_NAVIGATION_BAR = 1;
    public static final int ITYPE_RIGHT_DISPLAY_CUTOUT = 15;
    public static final int ITYPE_RIGHT_GESTURES = 6;
    public static final int ITYPE_RIGHT_MANDATORY_GESTURES = 10;
    public static final int ITYPE_SHELF = 1;
    public static final int ITYPE_STATUS_BAR = 0;
    public static final int ITYPE_TOP_DISPLAY_CUTOUT = 14;
    public static final int ITYPE_TOP_GESTURES = 3;
    public static final int ITYPE_TOP_MANDATORY_GESTURES = 7;
    public static final int ITYPE_TOP_TAPPABLE_ELEMENT = 11;
    static final int LAST_TYPE = 19;
    public static final int SIZE = 20;
    private final Rect mDisplayFrame;
    private InsetsSource[] mSources;
    public static final InsetsState EMPTY = new InsetsState();
    public static final Parcelable.Creator<InsetsState> CREATOR = new Parcelable.Creator<InsetsState>() { // from class: android.view.InsetsState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InsetsState createFromParcel(Parcel parcel) {
            return new InsetsState(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InsetsState[] newArray(int i) {
            return new InsetsState[i];
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    public @interface InternalInsetsSide {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface InternalInsetsType {
    }

    public static boolean getDefaultVisibility(int i) {
        return i != 17;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public InsetsState() {
        this.mSources = new InsetsSource[20];
        this.mDisplayFrame = new Rect();
    }

    public InsetsState(InsetsState insetsState) {
        this.mSources = new InsetsSource[20];
        this.mDisplayFrame = new Rect();
        set(insetsState);
    }

    public InsetsState(InsetsState insetsState, boolean z) {
        this.mSources = new InsetsSource[20];
        this.mDisplayFrame = new Rect();
        set(insetsState, z);
    }

    public WindowInsets calculateInsets(Rect rect, InsetsState insetsState, boolean z, boolean z2, DisplayCutout displayCutout, int i, int i2, int i3, SparseIntArray sparseIntArray) {
        Insets[] insetsArr = new Insets[9];
        Insets[] insetsArr2 = new Insets[9];
        boolean[] zArr = new boolean[20];
        Rect rect2 = new Rect(rect);
        Rect rect3 = new Rect(rect);
        int i4 = 0;
        while (true) {
            boolean z3 = true;
            if (i4 > 19) {
                break;
            }
            InsetsSource insetsSource = this.mSources[i4];
            if (insetsSource == null) {
                int iIndexOf = WindowInsets.Type.indexOf(toPublicType(i4));
                if (insetsArr[iIndexOf] == null) {
                    insetsArr[iIndexOf] = Insets.NONE;
                }
            } else {
                boolean z4 = ViewRootImpl.sNewInsetsMode == 1 && insetsSource.getType() != 17;
                boolean z5 = ViewRootImpl.sNewInsetsMode != 2 && (i4 == 0 || i4 == 1);
                if (ViewRootImpl.sNewInsetsMode != 0 || (i4 != 0 && i4 != 1 && i4 != 17)) {
                    z3 = false;
                }
                if (z5 || z3 || z4) {
                    zArr[WindowInsets.Type.indexOf(toPublicType(i4))] = insetsSource.isVisible();
                } else {
                    processSource(insetsSource, rect2, false, insetsArr, sparseIntArray, zArr);
                    if (insetsSource.getType() != 17) {
                        InsetsSource source = insetsState != null ? insetsState.getSource(i4) : insetsSource;
                        if (source != null) {
                            processSource(source, rect3, true, insetsArr2, null, null);
                        }
                    }
                }
            }
            i4++;
        }
        int i5 = i & 240;
        int iSystemBars = WindowInsets.Type.systemBars() | WindowInsets.Type.displayCutout();
        if (i5 == 16) {
            iSystemBars |= WindowInsets.Type.ime();
        }
        return new WindowInsets(insetsArr, insetsArr2, zArr, z, z2, displayCutout, (i2 & 1024) != 0 ? (~WindowInsets.Type.statusBars()) & iSystemBars : iSystemBars, ViewRootImpl.sNewInsetsMode == 2 && (i3 & 256) != 0);
    }

    public Rect calculateVisibleInsets(Rect rect, int i) {
        Insets insetsMax = Insets.NONE;
        for (int i2 = 0; i2 <= 19; i2++) {
            InsetsSource insetsSource = this.mSources[i2];
            if (insetsSource != null && ((ViewRootImpl.sNewInsetsMode == 2 || i2 == 17) && WindowInsets.Type.isVisibleInsetsType(toPublicType(i2), i))) {
                insetsMax = Insets.max(insetsSource.calculateVisibleInsets(rect), insetsMax);
            }
        }
        return insetsMax.toRect();
    }

    public int calculateUncontrollableInsetsFromFrame(Rect rect) {
        int publicType = 0;
        for (int i = 0; i <= 19; i++) {
            InsetsSource insetsSource = this.mSources[i];
            if (insetsSource != null && !canControlSide(rect, getInsetSide(insetsSource.calculateInsets(rect, true)))) {
                publicType |= toPublicType(i);
            }
        }
        return publicType;
    }

    private boolean canControlSide(Rect rect, int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return i == 4;
                    }
                }
            }
            return rect.top == this.mDisplayFrame.top && rect.bottom == this.mDisplayFrame.bottom;
        }
        return rect.left == this.mDisplayFrame.left && rect.right == this.mDisplayFrame.right;
    }

    private void processSource(InsetsSource insetsSource, Rect rect, boolean z, Insets[] insetsArr, SparseIntArray sparseIntArray, boolean[] zArr) {
        Insets insetsCalculateInsets = insetsSource.calculateInsets(rect, z);
        int publicType = toPublicType(insetsSource.getType());
        processSourceAsPublicType(insetsSource, insetsArr, sparseIntArray, zArr, insetsCalculateInsets, publicType);
        if (publicType == 32) {
            processSourceAsPublicType(insetsSource, insetsArr, sparseIntArray, zArr, insetsCalculateInsets, 16);
        }
    }

    private void processSourceAsPublicType(InsetsSource insetsSource, Insets[] insetsArr, SparseIntArray sparseIntArray, boolean[] zArr, Insets insets, int i) {
        int insetSide;
        int iIndexOf = WindowInsets.Type.indexOf(i);
        Insets insets2 = insetsArr[iIndexOf];
        if (insets2 == null) {
            insetsArr[iIndexOf] = insets;
        } else {
            insetsArr[iIndexOf] = Insets.max(insets2, insets);
        }
        if (zArr != null) {
            zArr[iIndexOf] = insetsSource.isVisible();
        }
        if (sparseIntArray == null || (insetSide = getInsetSide(insets)) == 5) {
            return;
        }
        sparseIntArray.put(insetsSource.getType(), insetSide);
    }

    private int getInsetSide(Insets insets) {
        if (Insets.NONE.equals(insets)) {
            return 4;
        }
        if (insets.left != 0) {
            return 0;
        }
        if (insets.top != 0) {
            return 1;
        }
        if (insets.right != 0) {
            return 2;
        }
        return insets.bottom != 0 ? 3 : 5;
    }

    public InsetsSource getSource(int i) {
        InsetsSource insetsSource = this.mSources[i];
        if (insetsSource != null) {
            return insetsSource;
        }
        InsetsSource insetsSource2 = new InsetsSource(i);
        this.mSources[i] = insetsSource2;
        return insetsSource2;
    }

    public InsetsSource peekSource(int i) {
        return this.mSources[i];
    }

    public boolean hasSources() {
        for (int i = 0; i < 20; i++) {
            if (this.mSources[i] != null) {
                return true;
            }
        }
        return false;
    }

    public boolean getSourceOrDefaultVisibility(int i) {
        InsetsSource insetsSource = this.mSources[i];
        return insetsSource != null ? insetsSource.isVisible() : getDefaultVisibility(i);
    }

    public void setDisplayFrame(Rect rect) {
        this.mDisplayFrame.set(rect);
    }

    public Rect getDisplayFrame() {
        return this.mDisplayFrame;
    }

    public void removeSource(int i) {
        this.mSources[i] = null;
    }

    public void setSourceVisible(int i, boolean z) {
        InsetsSource insetsSource = this.mSources[i];
        if (insetsSource != null) {
            insetsSource.setVisible(z);
        }
    }

    public void set(InsetsState insetsState) {
        set(insetsState, false);
    }

    public void set(InsetsState insetsState, boolean z) {
        this.mDisplayFrame.set(insetsState.mDisplayFrame);
        int i = 0;
        if (!z) {
            while (i < 20) {
                this.mSources[i] = insetsState.mSources[i];
                i++;
            }
        } else {
            while (i < 20) {
                InsetsSource insetsSource = insetsState.mSources[i];
                this.mSources[i] = insetsSource != null ? new InsetsSource(insetsSource) : null;
                i++;
            }
        }
    }

    public void addSource(InsetsSource insetsSource) {
        this.mSources[insetsSource.getType()] = insetsSource;
    }

    public static ArraySet<Integer> toInternalType(int i) {
        ArraySet<Integer> arraySet = new ArraySet<>();
        if ((i & 1) != 0) {
            arraySet.add(0);
            arraySet.add(18);
        }
        if ((i & 2) != 0) {
            arraySet.add(1);
            arraySet.add(19);
        }
        if ((i & 4) != 0) {
            arraySet.add(2);
        }
        if ((i & 128) != 0) {
            arraySet.add(13);
            arraySet.add(14);
            arraySet.add(15);
            arraySet.add(16);
        }
        if ((i & 8) != 0) {
            arraySet.add(17);
        }
        return arraySet;
    }

    public static int toPublicType(int i) {
        switch (i) {
            case 0:
            case 18:
                return 1;
            case 1:
            case 19:
                return 2;
            case 2:
                return 4;
            case 3:
            case 4:
            case 7:
            case 8:
            case 9:
            case 10:
                return 32;
            case 5:
            case 6:
                return 16;
            case 11:
            case 12:
                return 64;
            case 13:
            case 14:
            case 15:
            case 16:
                return 128;
            case 17:
                return 8;
            default:
                throw new IllegalArgumentException("Unknown type: " + i);
        }
    }

    public static boolean containsType(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.println(str + "InsetsState");
        for (int i = 0; i < 20; i++) {
            InsetsSource insetsSource = this.mSources[i];
            if (insetsSource != null) {
                insetsSource.dump(str + "  ", printWriter);
            }
        }
    }

    public static String typeToString(int i) {
        switch (i) {
            case 0:
                return "ITYPE_STATUS_BAR";
            case 1:
                return "ITYPE_NAVIGATION_BAR";
            case 2:
                return "ITYPE_CAPTION_BAR";
            case 3:
                return "ITYPE_TOP_GESTURES";
            case 4:
                return "ITYPE_BOTTOM_GESTURES";
            case 5:
                return "ITYPE_LEFT_GESTURES";
            case 6:
                return "ITYPE_RIGHT_GESTURES";
            case 7:
                return "ITYPE_TOP_MANDATORY_GESTURES";
            case 8:
                return "ITYPE_BOTTOM_MANDATORY_GESTURES";
            case 9:
                return "ITYPE_LEFT_MANDATORY_GESTURES";
            case 10:
                return "ITYPE_RIGHT_MANDATORY_GESTURES";
            case 11:
                return "ITYPE_TOP_TAPPABLE_ELEMENT";
            case 12:
                return "ITYPE_BOTTOM_TAPPABLE_ELEMENT";
            case 13:
                return "ITYPE_LEFT_DISPLAY_CUTOUT";
            case 14:
                return "ITYPE_TOP_DISPLAY_CUTOUT";
            case 15:
                return "ITYPE_RIGHT_DISPLAY_CUTOUT";
            case 16:
                return "ITYPE_BOTTOM_DISPLAY_CUTOUT";
            case 17:
                return "ITYPE_IME";
            case 18:
                return "ITYPE_CLIMATE_BAR";
            case 19:
                return "ITYPE_EXTRA_NAVIGATION_BAR";
            default:
                return "ITYPE_UNKNOWN_" + i;
        }
    }

    public boolean equals(Object obj) {
        return equals(obj, false, false);
    }

    public boolean equals(Object obj, boolean z, boolean z2) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InsetsState insetsState = (InsetsState) obj;
        if (!this.mDisplayFrame.equals(insetsState.mDisplayFrame)) {
            return false;
        }
        for (int i = 0; i < 20; i++) {
            if (!z || i != 2) {
                InsetsSource insetsSource = this.mSources[i];
                InsetsSource insetsSource2 = insetsState.mSources[i];
                if ((insetsSource != null || insetsSource2 != null) && ((insetsSource != null && insetsSource2 == null) || ((insetsSource == null && insetsSource2 != null) || !insetsSource2.equals(insetsSource, z2)))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        return Objects.hash(this.mDisplayFrame, Integer.valueOf(Arrays.hashCode(this.mSources)));
    }

    public InsetsState(Parcel parcel) {
        this.mSources = new InsetsSource[20];
        this.mDisplayFrame = new Rect();
        readFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mDisplayFrame, i);
        parcel.writeParcelableArray(this.mSources, 0);
    }

    public void readFromParcel(Parcel parcel) {
        this.mDisplayFrame.set((Rect) parcel.readParcelable(null));
        this.mSources = (InsetsSource[]) parcel.readParcelableArray(null, InsetsSource.class);
    }

    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (int i = 0; i < 20; i++) {
            InsetsSource insetsSource = this.mSources[i];
            if (insetsSource != null) {
                stringJoiner.add(insetsSource.toString());
            }
        }
        return "InsetsState: {mDisplayFrame=" + this.mDisplayFrame + ", mSources= { " + stringJoiner + " }";
    }
}
