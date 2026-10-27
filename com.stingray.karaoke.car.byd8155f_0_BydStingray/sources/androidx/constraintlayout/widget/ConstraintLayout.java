package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import h1.c;
import h6.n;
import java.util.ArrayList;
import java.util.HashMap;
import l5.C1778f;
import o1.d;
import o1.e;
import o1.h;
import r1.AbstractC2270b;
import r1.AbstractC2271c;
import r1.C2272d;
import r1.C2273e;
import r1.m;
import r1.p;
import r1.q;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static q f11701A;
    public final SparseArray l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f11702m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final e f11703n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f11704p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f11705q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f11706r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f11707s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f11708t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public m f11709u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public C1778f f11710v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f11711w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public HashMap f11712x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final SparseArray f11713y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C2273e f11714z;

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = new SparseArray();
        this.f11702m = new ArrayList(4);
        this.f11703n = new e();
        this.o = 0;
        this.f11704p = 0;
        this.f11705q = Integer.MAX_VALUE;
        this.f11706r = Integer.MAX_VALUE;
        this.f11707s = true;
        this.f11708t = 257;
        this.f11709u = null;
        this.f11710v = null;
        this.f11711w = -1;
        this.f11712x = new HashMap();
        this.f11713y = new SparseArray();
        this.f11714z = new C2273e(this, this);
        e(attributeSet, 0);
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingRight()) + Math.max(0, getPaddingLeft());
        int iMax2 = Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart());
        return iMax2 > 0 ? iMax2 : iMax;
    }

    public static q getSharedValues() {
        if (f11701A == null) {
            q qVar = new q();
            new SparseIntArray();
            new HashMap();
            f11701A = qVar;
        }
        return f11701A;
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C2272d;
    }

    public final d d(View view) {
        if (view == this) {
            return this.f11703n;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof C2272d) {
            return ((C2272d) view.getLayoutParams()).f21641p0;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof C2272d) {
            return ((C2272d) view.getLayoutParams()).f21641p0;
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList arrayList = this.f11702m;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i7 = 0; i7 < size; i7++) {
                ((AbstractC2270b) arrayList.get(i7)).getClass();
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt = getChildAt(i10);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i11 = Integer.parseInt(strArrSplit[0]);
                        int i12 = Integer.parseInt(strArrSplit[1]);
                        int i13 = Integer.parseInt(strArrSplit[2]);
                        int i14 = (int) ((i11 / 1080.0f) * width);
                        int i15 = (int) ((i12 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f10 = i14;
                        float f11 = i15;
                        float f12 = i14 + ((int) ((i13 / 1080.0f) * width));
                        canvas.drawLine(f10, f11, f12, f11, paint);
                        float f13 = i15 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f12, f11, f12, f13, paint);
                        canvas.drawLine(f12, f13, f10, f13, paint);
                        canvas.drawLine(f10, f13, f10, f11, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f10, f11, f12, f13, paint);
                        canvas.drawLine(f10, f13, f12, f11, paint);
                    }
                }
            }
        }
    }

    public final void e(AttributeSet attributeSet, int i7) {
        e eVar = this.f11703n;
        eVar.f20293g0 = this;
        C2273e c2273e = this.f11714z;
        eVar.f20336v0 = c2273e;
        eVar.f20334t0.f20658h = c2273e;
        this.l.put(getId(), this);
        this.f11709u = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, p.f21764b, i7, 0);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i10 = 0; i10 < indexCount; i10++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i10);
                if (index == 16) {
                    this.o = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.o);
                } else if (index == 17) {
                    this.f11704p = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f11704p);
                } else if (index == 14) {
                    this.f11705q = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f11705q);
                } else if (index == 15) {
                    this.f11706r = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f11706r);
                } else if (index == 113) {
                    this.f11708t = typedArrayObtainStyledAttributes.getInt(index, this.f11708t);
                } else if (index == 56) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            f(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.f11710v = null;
                        }
                    }
                } else if (index == 34) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        m mVar = new m();
                        this.f11709u = mVar;
                        mVar.e(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.f11709u = null;
                    }
                    this.f11711w = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        eVar.f20324E0 = this.f11708t;
        c.f15168q = eVar.c0(512);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0076  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(int r13) {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.f(int):void");
    }

    @Override // android.view.View
    public final void forceLayout() {
        this.f11707s = true;
        super.forceLayout();
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C2272d(-2, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        C2272d c2272d = new C2272d(context, attributeSet);
        c2272d.f21614a = -1;
        c2272d.f21616b = -1;
        c2272d.c = -1.0f;
        c2272d.d = true;
        c2272d.f21620e = -1;
        c2272d.f21622f = -1;
        c2272d.f21624g = -1;
        c2272d.f21626h = -1;
        c2272d.f21628i = -1;
        c2272d.f21630j = -1;
        c2272d.f21632k = -1;
        c2272d.l = -1;
        c2272d.f21635m = -1;
        c2272d.f21637n = -1;
        c2272d.o = -1;
        c2272d.f21640p = -1;
        c2272d.f21642q = 0;
        c2272d.f21643r = 0.0f;
        c2272d.f21644s = -1;
        c2272d.f21645t = -1;
        c2272d.f21646u = -1;
        c2272d.f21647v = -1;
        c2272d.f21648w = Integer.MIN_VALUE;
        c2272d.f21649x = Integer.MIN_VALUE;
        c2272d.f21650y = Integer.MIN_VALUE;
        c2272d.f21651z = Integer.MIN_VALUE;
        c2272d.f21589A = Integer.MIN_VALUE;
        c2272d.B = Integer.MIN_VALUE;
        c2272d.f21590C = Integer.MIN_VALUE;
        c2272d.f21591D = 0;
        c2272d.f21592E = 0.5f;
        c2272d.f21593F = 0.5f;
        c2272d.f21594G = null;
        c2272d.f21595H = -1.0f;
        c2272d.f21596I = -1.0f;
        c2272d.f21597J = 0;
        c2272d.f21598K = 0;
        c2272d.f21599L = 0;
        c2272d.f21600M = 0;
        c2272d.f21601N = 0;
        c2272d.f21602O = 0;
        c2272d.f21603P = 0;
        c2272d.f21604Q = 0;
        c2272d.f21605R = 1.0f;
        c2272d.f21606S = 1.0f;
        c2272d.f21607T = -1;
        c2272d.f21608U = -1;
        c2272d.f21609V = -1;
        c2272d.f21610W = false;
        c2272d.f21611X = false;
        c2272d.f21612Y = null;
        c2272d.f21613Z = 0;
        c2272d.f21615a0 = true;
        c2272d.f21617b0 = true;
        c2272d.f21618c0 = false;
        c2272d.f21619d0 = false;
        c2272d.f21621e0 = false;
        c2272d.f21623f0 = -1;
        c2272d.f21625g0 = -1;
        c2272d.f21627h0 = -1;
        c2272d.f21629i0 = -1;
        c2272d.f21631j0 = Integer.MIN_VALUE;
        c2272d.f21633k0 = Integer.MIN_VALUE;
        c2272d.f21634l0 = 0.5f;
        c2272d.f21641p0 = new d();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, p.f21764b);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i7);
            int i10 = AbstractC2271c.f21588a.get(index);
            switch (i10) {
                case 1:
                    c2272d.f21609V = typedArrayObtainStyledAttributes.getInt(index, c2272d.f21609V);
                    break;
                case 2:
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21640p);
                    c2272d.f21640p = resourceId;
                    if (resourceId == -1) {
                        c2272d.f21640p = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 3:
                    c2272d.f21642q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21642q);
                    break;
                case 4:
                    float f10 = typedArrayObtainStyledAttributes.getFloat(index, c2272d.f21643r) % 360.0f;
                    c2272d.f21643r = f10;
                    if (f10 < 0.0f) {
                        c2272d.f21643r = (360.0f - f10) % 360.0f;
                    }
                    break;
                case 5:
                    c2272d.f21614a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, c2272d.f21614a);
                    break;
                case 6:
                    c2272d.f21616b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, c2272d.f21616b);
                    break;
                case 7:
                    c2272d.c = typedArrayObtainStyledAttributes.getFloat(index, c2272d.c);
                    break;
                case 8:
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21620e);
                    c2272d.f21620e = resourceId2;
                    if (resourceId2 == -1) {
                        c2272d.f21620e = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 9:
                    int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21622f);
                    c2272d.f21622f = resourceId3;
                    if (resourceId3 == -1) {
                        c2272d.f21622f = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 10:
                    int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21624g);
                    c2272d.f21624g = resourceId4;
                    if (resourceId4 == -1) {
                        c2272d.f21624g = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 11:
                    int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21626h);
                    c2272d.f21626h = resourceId5;
                    if (resourceId5 == -1) {
                        c2272d.f21626h = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 12:
                    int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21628i);
                    c2272d.f21628i = resourceId6;
                    if (resourceId6 == -1) {
                        c2272d.f21628i = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 13:
                    int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21630j);
                    c2272d.f21630j = resourceId7;
                    if (resourceId7 == -1) {
                        c2272d.f21630j = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 14:
                    int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21632k);
                    c2272d.f21632k = resourceId8;
                    if (resourceId8 == -1) {
                        c2272d.f21632k = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 15:
                    int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.l);
                    c2272d.l = resourceId9;
                    if (resourceId9 == -1) {
                        c2272d.l = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 16:
                    int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21635m);
                    c2272d.f21635m = resourceId10;
                    if (resourceId10 == -1) {
                        c2272d.f21635m = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 17:
                    int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21644s);
                    c2272d.f21644s = resourceId11;
                    if (resourceId11 == -1) {
                        c2272d.f21644s = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 18:
                    int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21645t);
                    c2272d.f21645t = resourceId12;
                    if (resourceId12 == -1) {
                        c2272d.f21645t = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 19:
                    int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21646u);
                    c2272d.f21646u = resourceId13;
                    if (resourceId13 == -1) {
                        c2272d.f21646u = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 20:
                    int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21647v);
                    c2272d.f21647v = resourceId14;
                    if (resourceId14 == -1) {
                        c2272d.f21647v = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 21:
                    c2272d.f21648w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21648w);
                    break;
                case 22:
                    c2272d.f21649x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21649x);
                    break;
                case 23:
                    c2272d.f21650y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21650y);
                    break;
                case 24:
                    c2272d.f21651z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21651z);
                    break;
                case 25:
                    c2272d.f21589A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21589A);
                    break;
                case 26:
                    c2272d.B = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.B);
                    break;
                case 27:
                    c2272d.f21610W = typedArrayObtainStyledAttributes.getBoolean(index, c2272d.f21610W);
                    break;
                case 28:
                    c2272d.f21611X = typedArrayObtainStyledAttributes.getBoolean(index, c2272d.f21611X);
                    break;
                case 29:
                    c2272d.f21592E = typedArrayObtainStyledAttributes.getFloat(index, c2272d.f21592E);
                    break;
                case 30:
                    c2272d.f21593F = typedArrayObtainStyledAttributes.getFloat(index, c2272d.f21593F);
                    break;
                case 31:
                    int i11 = typedArrayObtainStyledAttributes.getInt(index, 0);
                    c2272d.f21599L = i11;
                    if (i11 == 1) {
                        n.j("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                    }
                    break;
                case 32:
                    int i12 = typedArrayObtainStyledAttributes.getInt(index, 0);
                    c2272d.f21600M = i12;
                    if (i12 == 1) {
                        n.j("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                    }
                    break;
                case 33:
                    try {
                        c2272d.f21601N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21601N);
                    } catch (Exception unused) {
                        if (typedArrayObtainStyledAttributes.getInt(index, c2272d.f21601N) == -2) {
                            c2272d.f21601N = -2;
                        }
                    }
                    break;
                case 34:
                    try {
                        c2272d.f21603P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21603P);
                    } catch (Exception unused2) {
                        if (typedArrayObtainStyledAttributes.getInt(index, c2272d.f21603P) == -2) {
                            c2272d.f21603P = -2;
                        }
                    }
                    break;
                case 35:
                    c2272d.f21605R = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, c2272d.f21605R));
                    c2272d.f21599L = 2;
                    break;
                case 36:
                    try {
                        c2272d.f21602O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21602O);
                    } catch (Exception unused3) {
                        if (typedArrayObtainStyledAttributes.getInt(index, c2272d.f21602O) == -2) {
                            c2272d.f21602O = -2;
                        }
                    }
                    break;
                case 37:
                    try {
                        c2272d.f21604Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21604Q);
                    } catch (Exception unused4) {
                        if (typedArrayObtainStyledAttributes.getInt(index, c2272d.f21604Q) == -2) {
                            c2272d.f21604Q = -2;
                        }
                    }
                    break;
                case 38:
                    c2272d.f21606S = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, c2272d.f21606S));
                    c2272d.f21600M = 2;
                    break;
                default:
                    switch (i10) {
                        case 44:
                            m.h(c2272d, typedArrayObtainStyledAttributes.getString(index));
                            break;
                        case 45:
                            c2272d.f21595H = typedArrayObtainStyledAttributes.getFloat(index, c2272d.f21595H);
                            break;
                        case 46:
                            c2272d.f21596I = typedArrayObtainStyledAttributes.getFloat(index, c2272d.f21596I);
                            break;
                        case 47:
                            c2272d.f21597J = typedArrayObtainStyledAttributes.getInt(index, 0);
                            break;
                        case 48:
                            c2272d.f21598K = typedArrayObtainStyledAttributes.getInt(index, 0);
                            break;
                        case 49:
                            c2272d.f21607T = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, c2272d.f21607T);
                            break;
                        case 50:
                            c2272d.f21608U = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, c2272d.f21608U);
                            break;
                        case 51:
                            c2272d.f21612Y = typedArrayObtainStyledAttributes.getString(index);
                            break;
                        case 52:
                            int resourceId15 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.f21637n);
                            c2272d.f21637n = resourceId15;
                            if (resourceId15 == -1) {
                                c2272d.f21637n = typedArrayObtainStyledAttributes.getInt(index, -1);
                            }
                            break;
                        case 53:
                            int resourceId16 = typedArrayObtainStyledAttributes.getResourceId(index, c2272d.o);
                            c2272d.o = resourceId16;
                            if (resourceId16 == -1) {
                                c2272d.o = typedArrayObtainStyledAttributes.getInt(index, -1);
                            }
                            break;
                        case 54:
                            c2272d.f21591D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21591D);
                            break;
                        case 55:
                            c2272d.f21590C = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, c2272d.f21590C);
                            break;
                        default:
                            switch (i10) {
                                case 64:
                                    m.g(c2272d, typedArrayObtainStyledAttributes, index, 0);
                                    break;
                                case 65:
                                    m.g(c2272d, typedArrayObtainStyledAttributes, index, 1);
                                    break;
                                case 66:
                                    c2272d.f21613Z = typedArrayObtainStyledAttributes.getInt(index, c2272d.f21613Z);
                                    break;
                                case 67:
                                    c2272d.d = typedArrayObtainStyledAttributes.getBoolean(index, c2272d.d);
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        c2272d.a();
        return c2272d;
    }

    public int getMaxHeight() {
        return this.f11706r;
    }

    public int getMaxWidth() {
        return this.f11705q;
    }

    public int getMinHeight() {
        return this.f11704p;
    }

    public int getMinWidth() {
        return this.o;
    }

    public int getOptimizationLevel() {
        return this.f11703n.f20324E0;
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        e eVar = this.f11703n;
        if (eVar.f20300k == null) {
            int id2 = getId();
            if (id2 != -1) {
                eVar.f20300k = getContext().getResources().getResourceEntryName(id2);
            } else {
                eVar.f20300k = "parent";
            }
        }
        if (eVar.f20297i0 == null) {
            eVar.f20297i0 = eVar.f20300k;
            Log.v("ConstraintLayout", " setDebugName " + eVar.f20297i0);
        }
        for (d dVar : eVar.f20332r0) {
            View view = (View) dVar.f20293g0;
            if (view != null) {
                if (dVar.f20300k == null && (id = view.getId()) != -1) {
                    dVar.f20300k = getContext().getResources().getResourceEntryName(id);
                }
                if (dVar.f20297i0 == null) {
                    dVar.f20297i0 = dVar.f20300k;
                    Log.v("ConstraintLayout", " setDebugName " + dVar.f20297i0);
                }
            }
        }
        eVar.o(sb);
        return sb.toString();
    }

    public final void j(d dVar, C2272d c2272d, SparseArray sparseArray, int i7, int i10) {
        View view = (View) this.l.get(i7);
        d dVar2 = (d) sparseArray.get(i7);
        if (dVar2 == null || view == null || !(view.getLayoutParams() instanceof C2272d)) {
            return;
        }
        c2272d.f21618c0 = true;
        if (i10 == 6) {
            C2272d c2272d2 = (C2272d) view.getLayoutParams();
            c2272d2.f21618c0 = true;
            c2272d2.f21641p0.f20261F = true;
        }
        dVar.j(6).b(dVar2.j(i10), c2272d.f21591D, c2272d.f21590C, true);
        dVar.f20261F = true;
        dVar.j(3).j();
        dVar.j(5).j();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            C2272d c2272d = (C2272d) childAt.getLayoutParams();
            d dVar = c2272d.f21641p0;
            if (childAt.getVisibility() != 8 || c2272d.f21619d0 || c2272d.f21621e0 || zIsInEditMode) {
                int iS = dVar.s();
                int iT = dVar.t();
                childAt.layout(iS, iT, dVar.r() + iS, dVar.l() + iT);
            }
        }
        ArrayList arrayList = this.f11702m;
        int size = arrayList.size();
        if (size > 0) {
            for (int i14 = 0; i14 < size; i14++) {
                ((AbstractC2270b) arrayList.get(i14)).getClass();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:159:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0311  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0355  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x03a5  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0574  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0596  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x05a9  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x05b4  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x05e5  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x05e8  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x05ef  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x05f2  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0636  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x0639  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onMeasure(int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 1598
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.onMeasure(int, int):void");
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        super.onViewAdded(view);
        d dVarD = d(view);
        if ((view instanceof Guideline) && !(dVarD instanceof h)) {
            C2272d c2272d = (C2272d) view.getLayoutParams();
            h hVar = new h();
            c2272d.f21641p0 = hVar;
            c2272d.f21619d0 = true;
            hVar.W(c2272d.f21609V);
        }
        if (view instanceof AbstractC2270b) {
            AbstractC2270b abstractC2270b = (AbstractC2270b) view;
            abstractC2270b.e();
            ((C2272d) view.getLayoutParams()).f21621e0 = true;
            ArrayList arrayList = this.f11702m;
            if (!arrayList.contains(abstractC2270b)) {
                arrayList.add(abstractC2270b);
            }
        }
        this.l.put(view.getId(), view);
        this.f11707s = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.l.remove(view.getId());
        d dVarD = d(view);
        this.f11703n.f20332r0.remove(dVarD);
        dVarD.D();
        this.f11702m.remove(view);
        this.f11707s = true;
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.f11707s = true;
        super.requestLayout();
    }

    public void setConstraintSet(m mVar) {
        this.f11709u = mVar;
    }

    @Override // android.view.View
    public void setId(int i7) {
        int id = getId();
        SparseArray sparseArray = this.l;
        sparseArray.remove(id);
        super.setId(i7);
        sparseArray.put(getId(), this);
    }

    public void setMaxHeight(int i7) {
        if (i7 == this.f11706r) {
            return;
        }
        this.f11706r = i7;
        requestLayout();
    }

    public void setMaxWidth(int i7) {
        if (i7 == this.f11705q) {
            return;
        }
        this.f11705q = i7;
        requestLayout();
    }

    public void setMinHeight(int i7) {
        if (i7 == this.f11704p) {
            return;
        }
        this.f11704p = i7;
        requestLayout();
    }

    public void setMinWidth(int i7) {
        if (i7 == this.o) {
            return;
        }
        this.o = i7;
        requestLayout();
    }

    public void setOnConstraintsChanged(r1.n nVar) {
        C1778f c1778f = this.f11710v;
        if (c1778f != null) {
            c1778f.getClass();
        }
    }

    public void setOptimizationLevel(int i7) {
        this.f11708t = i7;
        e eVar = this.f11703n;
        eVar.f20324E0 = i7;
        c.f15168q = eVar.c0(512);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.l = new SparseArray();
        this.f11702m = new ArrayList(4);
        this.f11703n = new e();
        this.o = 0;
        this.f11704p = 0;
        this.f11705q = Integer.MAX_VALUE;
        this.f11706r = Integer.MAX_VALUE;
        this.f11707s = true;
        this.f11708t = 257;
        this.f11709u = null;
        this.f11710v = null;
        this.f11711w = -1;
        this.f11712x = new HashMap();
        this.f11713y = new SparseArray();
        this.f11714z = new C2273e(this, this);
        e(attributeSet, i7);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        C2272d c2272d = new C2272d(layoutParams);
        c2272d.f21614a = -1;
        c2272d.f21616b = -1;
        c2272d.c = -1.0f;
        c2272d.d = true;
        c2272d.f21620e = -1;
        c2272d.f21622f = -1;
        c2272d.f21624g = -1;
        c2272d.f21626h = -1;
        c2272d.f21628i = -1;
        c2272d.f21630j = -1;
        c2272d.f21632k = -1;
        c2272d.l = -1;
        c2272d.f21635m = -1;
        c2272d.f21637n = -1;
        c2272d.o = -1;
        c2272d.f21640p = -1;
        c2272d.f21642q = 0;
        c2272d.f21643r = 0.0f;
        c2272d.f21644s = -1;
        c2272d.f21645t = -1;
        c2272d.f21646u = -1;
        c2272d.f21647v = -1;
        c2272d.f21648w = Integer.MIN_VALUE;
        c2272d.f21649x = Integer.MIN_VALUE;
        c2272d.f21650y = Integer.MIN_VALUE;
        c2272d.f21651z = Integer.MIN_VALUE;
        c2272d.f21589A = Integer.MIN_VALUE;
        c2272d.B = Integer.MIN_VALUE;
        c2272d.f21590C = Integer.MIN_VALUE;
        c2272d.f21591D = 0;
        c2272d.f21592E = 0.5f;
        c2272d.f21593F = 0.5f;
        c2272d.f21594G = null;
        c2272d.f21595H = -1.0f;
        c2272d.f21596I = -1.0f;
        c2272d.f21597J = 0;
        c2272d.f21598K = 0;
        c2272d.f21599L = 0;
        c2272d.f21600M = 0;
        c2272d.f21601N = 0;
        c2272d.f21602O = 0;
        c2272d.f21603P = 0;
        c2272d.f21604Q = 0;
        c2272d.f21605R = 1.0f;
        c2272d.f21606S = 1.0f;
        c2272d.f21607T = -1;
        c2272d.f21608U = -1;
        c2272d.f21609V = -1;
        c2272d.f21610W = false;
        c2272d.f21611X = false;
        c2272d.f21612Y = null;
        c2272d.f21613Z = 0;
        c2272d.f21615a0 = true;
        c2272d.f21617b0 = true;
        c2272d.f21618c0 = false;
        c2272d.f21619d0 = false;
        c2272d.f21621e0 = false;
        c2272d.f21623f0 = -1;
        c2272d.f21625g0 = -1;
        c2272d.f21627h0 = -1;
        c2272d.f21629i0 = -1;
        c2272d.f21631j0 = Integer.MIN_VALUE;
        c2272d.f21633k0 = Integer.MIN_VALUE;
        c2272d.f21634l0 = 0.5f;
        c2272d.f21641p0 = new d();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            ((ViewGroup.MarginLayoutParams) c2272d).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) c2272d).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) c2272d).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) c2272d).bottomMargin = marginLayoutParams.bottomMargin;
            c2272d.setMarginStart(marginLayoutParams.getMarginStart());
            c2272d.setMarginEnd(marginLayoutParams.getMarginEnd());
        }
        if (layoutParams instanceof C2272d) {
            C2272d c2272d2 = (C2272d) layoutParams;
            c2272d.f21614a = c2272d2.f21614a;
            c2272d.f21616b = c2272d2.f21616b;
            c2272d.c = c2272d2.c;
            c2272d.d = c2272d2.d;
            c2272d.f21620e = c2272d2.f21620e;
            c2272d.f21622f = c2272d2.f21622f;
            c2272d.f21624g = c2272d2.f21624g;
            c2272d.f21626h = c2272d2.f21626h;
            c2272d.f21628i = c2272d2.f21628i;
            c2272d.f21630j = c2272d2.f21630j;
            c2272d.f21632k = c2272d2.f21632k;
            c2272d.l = c2272d2.l;
            c2272d.f21635m = c2272d2.f21635m;
            c2272d.f21637n = c2272d2.f21637n;
            c2272d.o = c2272d2.o;
            c2272d.f21640p = c2272d2.f21640p;
            c2272d.f21642q = c2272d2.f21642q;
            c2272d.f21643r = c2272d2.f21643r;
            c2272d.f21644s = c2272d2.f21644s;
            c2272d.f21645t = c2272d2.f21645t;
            c2272d.f21646u = c2272d2.f21646u;
            c2272d.f21647v = c2272d2.f21647v;
            c2272d.f21648w = c2272d2.f21648w;
            c2272d.f21649x = c2272d2.f21649x;
            c2272d.f21650y = c2272d2.f21650y;
            c2272d.f21651z = c2272d2.f21651z;
            c2272d.f21589A = c2272d2.f21589A;
            c2272d.B = c2272d2.B;
            c2272d.f21590C = c2272d2.f21590C;
            c2272d.f21591D = c2272d2.f21591D;
            c2272d.f21592E = c2272d2.f21592E;
            c2272d.f21593F = c2272d2.f21593F;
            c2272d.f21594G = c2272d2.f21594G;
            c2272d.f21595H = c2272d2.f21595H;
            c2272d.f21596I = c2272d2.f21596I;
            c2272d.f21597J = c2272d2.f21597J;
            c2272d.f21598K = c2272d2.f21598K;
            c2272d.f21610W = c2272d2.f21610W;
            c2272d.f21611X = c2272d2.f21611X;
            c2272d.f21599L = c2272d2.f21599L;
            c2272d.f21600M = c2272d2.f21600M;
            c2272d.f21601N = c2272d2.f21601N;
            c2272d.f21603P = c2272d2.f21603P;
            c2272d.f21602O = c2272d2.f21602O;
            c2272d.f21604Q = c2272d2.f21604Q;
            c2272d.f21605R = c2272d2.f21605R;
            c2272d.f21606S = c2272d2.f21606S;
            c2272d.f21607T = c2272d2.f21607T;
            c2272d.f21608U = c2272d2.f21608U;
            c2272d.f21609V = c2272d2.f21609V;
            c2272d.f21615a0 = c2272d2.f21615a0;
            c2272d.f21617b0 = c2272d2.f21617b0;
            c2272d.f21618c0 = c2272d2.f21618c0;
            c2272d.f21619d0 = c2272d2.f21619d0;
            c2272d.f21623f0 = c2272d2.f21623f0;
            c2272d.f21625g0 = c2272d2.f21625g0;
            c2272d.f21627h0 = c2272d2.f21627h0;
            c2272d.f21629i0 = c2272d2.f21629i0;
            c2272d.f21631j0 = c2272d2.f21631j0;
            c2272d.f21633k0 = c2272d2.f21633k0;
            c2272d.f21634l0 = c2272d2.f21634l0;
            c2272d.f21612Y = c2272d2.f21612Y;
            c2272d.f21613Z = c2272d2.f21613Z;
            c2272d.f21641p0 = c2272d2.f21641p0;
        }
        return c2272d;
    }
}
