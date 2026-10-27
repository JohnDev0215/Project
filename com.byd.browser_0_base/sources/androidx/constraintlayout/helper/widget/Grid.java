package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.VirtualLayout;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class Grid extends VirtualLayout {
    public float A;
    public int B;
    public int C;
    public boolean[][] D;
    public final HashSet E;
    public int[] F;
    public View[] p;
    public ConstraintLayout q;
    public int r;
    public int s;
    public int t;
    public int u;
    public String v;
    public String w;
    public String x;
    public String y;
    public float z;

    public Grid(Context context) {
        super(context);
        this.C = 0;
        this.E = new HashSet();
    }

    public static ConstraintLayout.LayoutParams E(View view) {
        return (ConstraintLayout.LayoutParams) view.getLayoutParams();
    }

    public static int[][] F(String str) {
        String[] strArrSplit = str.split(",");
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, strArrSplit.length, 3);
        for (int i = 0; i < strArrSplit.length; i++) {
            String[] strArrSplit2 = strArrSplit[i].trim().split(":");
            String[] strArrSplit3 = strArrSplit2[1].split("x");
            iArr[i][0] = Integer.parseInt(strArrSplit2[0]);
            iArr[i][1] = Integer.parseInt(strArrSplit3[0]);
            iArr[i][2] = Integer.parseInt(strArrSplit3[1]);
        }
        return iArr;
    }

    public static float[] G(int i, String str) {
        float[] fArr = null;
        if (str != null && !str.trim().isEmpty()) {
            String[] strArrSplit = str.split(",");
            if (strArrSplit.length != i) {
                return null;
            }
            fArr = new float[i];
            for (int i2 = 0; i2 < i; i2++) {
                fArr[i2] = Float.parseFloat(strArrSplit[i2].trim());
            }
        }
        return fArr;
    }

    private int getNextPosition() {
        boolean z = false;
        int i = 0;
        while (!z) {
            i = this.C;
            if (i >= this.r * this.t) {
                return -1;
            }
            int iA = A(i);
            int iZ = z(this.C);
            boolean[] zArr = this.D[iA];
            if (zArr[iZ]) {
                zArr[iZ] = false;
                z = true;
            }
            this.C++;
        }
        return i;
    }

    public static void v(View view) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        layoutParams.H = -1.0f;
        layoutParams.f = -1;
        layoutParams.e = -1;
        layoutParams.g = -1;
        layoutParams.h = -1;
        ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = -1;
        view.setLayoutParams(layoutParams);
    }

    public static void w(View view) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        layoutParams.I = -1.0f;
        layoutParams.j = -1;
        layoutParams.i = -1;
        layoutParams.k = -1;
        layoutParams.l = -1;
        ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = -1;
        view.setLayoutParams(layoutParams);
    }

    public final int A(int i) {
        return this.B == 1 ? i % this.r : i / this.t;
    }

    public final void B() {
        boolean[][] zArr = (boolean[][]) Array.newInstance((Class<?>) Boolean.TYPE, this.r, this.t);
        this.D = zArr;
        for (boolean[] zArr2 : zArr) {
            Arrays.fill(zArr2, true);
        }
    }

    public final boolean C(int i, int i2, int i3, int i4) {
        for (int i5 = i; i5 < i + i3; i5++) {
            for (int i6 = i2; i6 < i2 + i4; i6++) {
                boolean[][] zArr = this.D;
                if (i5 < zArr.length && i6 < zArr[0].length) {
                    boolean[] zArr2 = zArr[i5];
                    if (zArr2[i6]) {
                        zArr2[i6] = false;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public final View D() {
        View view = new View(getContext());
        view.setId(View.generateViewId());
        view.setVisibility(4);
        this.q.addView(view, new ConstraintLayout.LayoutParams(0, 0));
        return view;
    }

    public final void H() {
        int i;
        int i2 = this.s;
        if (i2 != 0 && (i = this.u) != 0) {
            this.r = i2;
            this.t = i;
            return;
        }
        int i3 = this.u;
        if (i3 > 0) {
            this.t = i3;
            this.r = ((this.f + i3) - 1) / i3;
        } else if (i2 > 0) {
            this.r = i2;
            this.t = ((this.f + i2) - 1) / i2;
        } else {
            int iSqrt = (int) (Math.sqrt(this.f) + 1.5d);
            this.r = iSqrt;
            this.t = ((this.f + iSqrt) - 1) / iSqrt;
        }
    }

    public String getColumnWeights() {
        return this.y;
    }

    public int getColumns() {
        return this.u;
    }

    public float getHorizontalGaps() {
        return this.z;
    }

    public int getOrientation() {
        return this.B;
    }

    public String getRowWeights() {
        return this.x;
    }

    public int getRows() {
        return this.s;
    }

    public String getSkips() {
        return this.w;
    }

    public String getSpans() {
        return this.v;
    }

    public float getVerticalGaps() {
        return this.A;
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper
    public final void n(AttributeSet attributeSet) {
        super.n(attributeSet);
        this.i = true;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.Grid);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == R$styleable.Grid_grid_rows) {
                    this.s = typedArrayObtainStyledAttributes.getInteger(index, 0);
                } else if (index == R$styleable.Grid_grid_columns) {
                    this.u = typedArrayObtainStyledAttributes.getInteger(index, 0);
                } else if (index == R$styleable.Grid_grid_spans) {
                    this.v = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == R$styleable.Grid_grid_skips) {
                    this.w = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == R$styleable.Grid_grid_rowWeights) {
                    this.x = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == R$styleable.Grid_grid_columnWeights) {
                    this.y = typedArrayObtainStyledAttributes.getString(index);
                } else if (index == R$styleable.Grid_grid_orientation) {
                    this.B = typedArrayObtainStyledAttributes.getInt(index, 0);
                } else if (index == R$styleable.Grid_grid_horizontalGaps) {
                    this.z = typedArrayObtainStyledAttributes.getDimension(index, 0.0f);
                } else if (index == R$styleable.Grid_grid_verticalGaps) {
                    this.A = typedArrayObtainStyledAttributes.getDimension(index, 0.0f);
                } else if (index == R$styleable.Grid_grid_validateInputs) {
                    typedArrayObtainStyledAttributes.getBoolean(index, false);
                } else if (index == R$styleable.Grid_grid_useRtl) {
                    typedArrayObtainStyledAttributes.getBoolean(index, false);
                }
            }
            H();
            B();
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.constraintlayout.widget.VirtualLayout, androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.q = (ConstraintLayout) getParent();
        y(false);
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    public final void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            Paint paint = new Paint();
            paint.setColor(-65536);
            paint.setStyle(Paint.Style.STROKE);
            int top = getTop();
            int left = getLeft();
            int bottom = getBottom();
            int right = getRight();
            for (View view : this.p) {
                int left2 = view.getLeft() - left;
                int top2 = view.getTop() - top;
                int right2 = view.getRight() - left;
                int bottom2 = view.getBottom() - top;
                canvas.drawRect(left2, 0.0f, right2, bottom - top, paint);
                canvas.drawRect(0.0f, top2, right - left, bottom2, paint);
            }
        }
    }

    public void setColumnWeights(String str) {
        String str2 = this.y;
        if (str2 == null || !str2.equals(str)) {
            this.y = str;
            y(true);
            invalidate();
        }
    }

    public void setColumns(int i) {
        if (i <= 50 && this.u != i) {
            this.u = i;
            H();
            B();
            y(false);
            invalidate();
        }
    }

    public void setHorizontalGaps(float f) {
        if (f >= 0.0f && this.z != f) {
            this.z = f;
            y(true);
            invalidate();
        }
    }

    public void setOrientation(int i) {
        if ((i == 0 || i == 1) && this.B != i) {
            this.B = i;
            y(true);
            invalidate();
        }
    }

    public void setRowWeights(String str) {
        String str2 = this.x;
        if (str2 == null || !str2.equals(str)) {
            this.x = str;
            y(true);
            invalidate();
        }
    }

    public void setRows(int i) {
        if (i <= 50 && this.s != i) {
            this.s = i;
            H();
            B();
            y(false);
            invalidate();
        }
    }

    public void setSkips(String str) {
        String str2 = this.w;
        if (str2 == null || !str2.equals(str)) {
            this.w = str;
            y(true);
            invalidate();
        }
    }

    public void setSpans(CharSequence charSequence) {
        String str = this.v;
        if (str == null || !str.contentEquals(charSequence)) {
            this.v = charSequence.toString();
            y(true);
            invalidate();
        }
    }

    public void setVerticalGaps(float f) {
        if (f >= 0.0f && this.A != f) {
            this.A = f;
            y(true);
            invalidate();
        }
    }

    public final void x(View view, int i, int i2, int i3, int i4) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
        int[] iArr = this.F;
        layoutParams.e = iArr[i2];
        layoutParams.i = iArr[i];
        layoutParams.h = iArr[(i2 + i4) - 1];
        layoutParams.l = iArr[(i + i3) - 1];
        view.setLayoutParams(layoutParams);
    }

    public final void y(boolean z) {
        int i;
        int i2;
        int[][] iArrF;
        int[][] iArrF2;
        if (this.q == null || this.r < 1 || this.t < 1) {
            return;
        }
        HashSet hashSet = this.E;
        if (z) {
            for (int i3 = 0; i3 < this.D.length; i3++) {
                int i4 = 0;
                while (true) {
                    boolean[][] zArr = this.D;
                    if (i4 < zArr[0].length) {
                        zArr[i3][i4] = true;
                        i4++;
                    }
                }
            }
            hashSet.clear();
        }
        this.C = 0;
        int iMax = Math.max(this.r, this.t);
        View[] viewArr = this.p;
        if (viewArr == null) {
            this.p = new View[iMax];
            int i5 = 0;
            while (true) {
                View[] viewArr2 = this.p;
                if (i5 >= viewArr2.length) {
                    break;
                }
                viewArr2[i5] = D();
                i5++;
            }
        } else if (iMax != viewArr.length) {
            View[] viewArr3 = new View[iMax];
            for (int i6 = 0; i6 < iMax; i6++) {
                View[] viewArr4 = this.p;
                if (i6 < viewArr4.length) {
                    viewArr3[i6] = viewArr4[i6];
                } else {
                    viewArr3[i6] = D();
                }
            }
            int i7 = iMax;
            while (true) {
                View[] viewArr5 = this.p;
                if (i7 >= viewArr5.length) {
                    break;
                }
                this.q.removeView(viewArr5[i7]);
                i7++;
            }
            this.p = viewArr3;
        }
        this.F = new int[iMax];
        int i8 = 0;
        while (true) {
            View[] viewArr6 = this.p;
            if (i8 >= viewArr6.length) {
                break;
            }
            this.F[i8] = viewArr6[i8].getId();
            i8++;
        }
        int id = getId();
        int iMax2 = Math.max(this.r, this.t);
        float[] fArrG = G(this.r, this.x);
        if (this.r == 1) {
            ConstraintLayout.LayoutParams layoutParamsE = E(this.p[0]);
            w(this.p[0]);
            layoutParamsE.i = id;
            layoutParamsE.l = id;
            this.p[0].setLayoutParams(layoutParamsE);
        } else {
            int i9 = 0;
            while (true) {
                i = this.r;
                if (i9 >= i) {
                    break;
                }
                ConstraintLayout.LayoutParams layoutParamsE2 = E(this.p[i9]);
                w(this.p[i9]);
                if (fArrG != null) {
                    layoutParamsE2.I = fArrG[i9];
                }
                if (i9 > 0) {
                    layoutParamsE2.j = this.F[i9 - 1];
                } else {
                    layoutParamsE2.i = id;
                }
                if (i9 < this.r - 1) {
                    layoutParamsE2.k = this.F[i9 + 1];
                } else {
                    layoutParamsE2.l = id;
                }
                if (i9 > 0) {
                    ((ViewGroup.MarginLayoutParams) layoutParamsE2).topMargin = (int) this.z;
                }
                this.p[i9].setLayoutParams(layoutParamsE2);
                i9++;
            }
            while (i < iMax2) {
                ConstraintLayout.LayoutParams layoutParamsE3 = E(this.p[i]);
                w(this.p[i]);
                layoutParamsE3.i = id;
                layoutParamsE3.l = id;
                this.p[i].setLayoutParams(layoutParamsE3);
                i++;
            }
        }
        int id2 = getId();
        int iMax3 = Math.max(this.r, this.t);
        float[] fArrG2 = G(this.t, this.y);
        ConstraintLayout.LayoutParams layoutParamsE4 = E(this.p[0]);
        if (this.t == 1) {
            v(this.p[0]);
            layoutParamsE4.e = id2;
            layoutParamsE4.h = id2;
            this.p[0].setLayoutParams(layoutParamsE4);
        } else {
            int i10 = 0;
            while (true) {
                i2 = this.t;
                if (i10 >= i2) {
                    break;
                }
                ConstraintLayout.LayoutParams layoutParamsE5 = E(this.p[i10]);
                v(this.p[i10]);
                if (fArrG2 != null) {
                    layoutParamsE5.H = fArrG2[i10];
                }
                if (i10 > 0) {
                    layoutParamsE5.f = this.F[i10 - 1];
                } else {
                    layoutParamsE5.e = id2;
                }
                if (i10 < this.t - 1) {
                    layoutParamsE5.g = this.F[i10 + 1];
                } else {
                    layoutParamsE5.h = id2;
                }
                if (i10 > 0) {
                    ((ViewGroup.MarginLayoutParams) layoutParamsE5).leftMargin = (int) this.z;
                }
                this.p[i10].setLayoutParams(layoutParamsE5);
                i10++;
            }
            while (i2 < iMax3) {
                ConstraintLayout.LayoutParams layoutParamsE6 = E(this.p[i2]);
                v(this.p[i2]);
                layoutParamsE6.e = id2;
                layoutParamsE6.h = id2;
                this.p[i2].setLayoutParams(layoutParamsE6);
                i2++;
            }
        }
        String str = this.w;
        if (str != null && !str.trim().isEmpty() && (iArrF2 = F(this.w)) != null) {
            for (int i11 = 0; i11 < iArrF2.length; i11++) {
                int iA = A(iArrF2[i11][0]);
                int iZ = z(iArrF2[i11][0]);
                int[] iArr = iArrF2[i11];
                if (!C(iA, iZ, iArr[1], iArr[2])) {
                    break;
                }
            }
        }
        String str2 = this.v;
        if (str2 != null && !str2.trim().isEmpty() && (iArrF = F(this.v)) != null) {
            int[] iArr2 = this.e;
            View[] viewArrM = m(this.q);
            for (int i12 = 0; i12 < iArrF.length; i12++) {
                int iA2 = A(iArrF[i12][0]);
                int iZ2 = z(iArrF[i12][0]);
                int[] iArr3 = iArrF[i12];
                if (!C(iA2, iZ2, iArr3[1], iArr3[2])) {
                    break;
                }
                View view = viewArrM[i12];
                int[] iArr4 = iArrF[i12];
                x(view, iA2, iZ2, iArr4[1], iArr4[2]);
                hashSet.add(Integer.valueOf(iArr2[i12]));
            }
        }
        View[] viewArrM2 = m(this.q);
        for (int i13 = 0; i13 < this.f; i13++) {
            if (!hashSet.contains(Integer.valueOf(this.e[i13]))) {
                int nextPosition = getNextPosition();
                int iA3 = A(nextPosition);
                int iZ3 = z(nextPosition);
                if (nextPosition == -1) {
                    return;
                } else {
                    x(viewArrM2[i13], iA3, iZ3, 1, 1);
                }
            }
        }
    }

    public final int z(int i) {
        return this.B == 1 ? i / this.r : i % this.t;
    }

    public Grid(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.C = 0;
        this.E = new HashSet();
    }

    public Grid(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.C = 0;
        this.E = new HashSet();
    }
}
