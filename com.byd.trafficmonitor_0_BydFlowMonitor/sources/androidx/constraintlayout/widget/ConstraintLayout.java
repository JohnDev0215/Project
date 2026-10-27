package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import b.f.b.j.d;
import b.f.b.j.f;
import b.f.b.j.l.b;
import b.f.c.e;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public SparseArray<View> f708b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ArrayList<ConstraintHelper> f709c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public d f710d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f711e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f712f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f713g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f714h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f715i;
    public int j;
    public b.f.c.b k;
    public b.f.c.a l;
    public int m;
    public HashMap<String, Integer> n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public SparseArray<ConstraintWidget> u;
    public c v;
    public int w;
    public int x;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f716a;

        static {
            int[] iArr = new int[ConstraintWidget.DimensionBehaviour.values().length];
            f716a = iArr;
            try {
                iArr[ConstraintWidget.DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f716a[ConstraintWidget.DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f716a[ConstraintWidget.DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f716a[ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public class c implements b.InterfaceC0027b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ConstraintLayout f727a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f728b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f729c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f730d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f731e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f732f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f733g;

        public c(ConstraintLayout constraintLayout) {
            this.f727a = constraintLayout;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:101:0x0163  */
        /* JADX WARN: Removed duplicated region for block: B:104:0x016e A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:112:0x0183  */
        /* JADX WARN: Removed duplicated region for block: B:117:0x0195  */
        /* JADX WARN: Removed duplicated region for block: B:120:0x01a6  */
        /* JADX WARN: Removed duplicated region for block: B:121:0x01b1  */
        /* JADX WARN: Removed duplicated region for block: B:123:0x01bd  */
        /* JADX WARN: Removed duplicated region for block: B:124:0x01c7  */
        /* JADX WARN: Removed duplicated region for block: B:127:0x01d4  */
        /* JADX WARN: Removed duplicated region for block: B:128:0x01d9  */
        /* JADX WARN: Removed duplicated region for block: B:131:0x01de  */
        /* JADX WARN: Removed duplicated region for block: B:134:0x01e6  */
        /* JADX WARN: Removed duplicated region for block: B:135:0x01eb  */
        /* JADX WARN: Removed duplicated region for block: B:138:0x01f0  */
        /* JADX WARN: Removed duplicated region for block: B:143:0x0202  */
        /* JADX WARN: Removed duplicated region for block: B:151:0x0217  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x021b  */
        /* JADX WARN: Removed duplicated region for block: B:155:0x0221  */
        /* JADX WARN: Removed duplicated region for block: B:158:0x0237  */
        /* JADX WARN: Removed duplicated region for block: B:159:0x0239  */
        /* JADX WARN: Removed duplicated region for block: B:162:0x023f  */
        /* JADX WARN: Removed duplicated region for block: B:166:0x0247  */
        /* JADX WARN: Removed duplicated region for block: B:169:0x024e  */
        /* JADX WARN: Removed duplicated region for block: B:170:0x0250  */
        /* JADX WARN: Removed duplicated region for block: B:172:0x0253  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00ba  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x0120  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x012f  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0131  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0134  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x0136  */
        /* JADX WARN: Removed duplicated region for block: B:80:0x013b  */
        /* JADX WARN: Removed duplicated region for block: B:84:0x0142  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0145  */
        /* JADX WARN: Removed duplicated region for block: B:90:0x014c  */
        /* JADX WARN: Removed duplicated region for block: B:93:0x0150  */
        /* JADX WARN: Removed duplicated region for block: B:96:0x0158  */
        /* JADX WARN: Removed duplicated region for block: B:98:0x015b  */
        @Override // b.f.b.j.l.b.InterfaceC0027b
        @android.annotation.SuppressLint({"WrongCall"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void a(androidx.constraintlayout.solver.widgets.ConstraintWidget r20, b.f.b.j.l.b.a r21) {
            /*
                Method dump skipped, instruction units count: 616
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.c.a(androidx.constraintlayout.solver.widgets.ConstraintWidget, b.f.b.j.l.b$a):void");
        }

        @Override // b.f.b.j.l.b.InterfaceC0027b
        public final void b() {
            int childCount = this.f727a.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = this.f727a.getChildAt(i2);
                if (childAt instanceof Placeholder) {
                    ((Placeholder) childAt).b(this.f727a);
                }
            }
            int size = this.f727a.f709c.size();
            if (size > 0) {
                for (int i3 = 0; i3 < size; i3++) {
                    ((ConstraintHelper) this.f727a.f709c.get(i3)).q(this.f727a);
                }
            }
        }

        public void c(int i2, int i3, int i4, int i5, int i6, int i7) {
            this.f728b = i4;
            this.f729c = i5;
            this.f730d = i6;
            this.f731e = i7;
            this.f732f = i2;
            this.f733g = i3;
        }
    }

    public ConstraintLayout(Context context) {
        super(context);
        this.f708b = new SparseArray<>();
        this.f709c = new ArrayList<>(4);
        this.f710d = new d();
        this.f711e = 0;
        this.f712f = 0;
        this.f713g = Integer.MAX_VALUE;
        this.f714h = Integer.MAX_VALUE;
        this.f715i = true;
        this.j = 263;
        this.k = null;
        this.l = null;
        this.m = -1;
        this.n = new HashMap<>();
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = 0;
        this.t = 0;
        this.u = new SparseArray<>();
        this.v = new c(this);
        this.w = 0;
        this.x = 0;
        l(null, 0, 0);
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingLeft()) + Math.max(0, getPaddingRight());
        int iMax2 = Build.VERSION.SDK_INT >= 17 ? Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart()) : 0;
        return iMax2 > 0 ? iMax2 : iMax;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i2, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i2, layoutParams);
        if (Build.VERSION.SDK_INT < 14) {
            onViewAdded(view);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(boolean r20, android.view.View r21, androidx.constraintlayout.solver.widgets.ConstraintWidget r22, androidx.constraintlayout.widget.ConstraintLayout.b r23, android.util.SparseArray<androidx.constraintlayout.solver.widgets.ConstraintWidget> r24) {
        /*
            Method dump skipped, instruction units count: 718
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.b(boolean, android.view.View, androidx.constraintlayout.solver.widgets.ConstraintWidget, androidx.constraintlayout.widget.ConstraintLayout$b, android.util.SparseArray):void");
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public b generateDefaultLayoutParams() {
        return new b(-2, -2);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof b;
    }

    @Override // android.view.ViewGroup
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public b generateLayoutParams(AttributeSet attributeSet) {
        return new b(getContext(), attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList<ConstraintHelper> arrayList = this.f709c;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                this.f709c.get(i2).r(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = getWidth();
            float height = getHeight();
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                    if (strArrSplit.length == 4) {
                        int i4 = Integer.parseInt(strArrSplit[0]);
                        int i5 = Integer.parseInt(strArrSplit[1]);
                        int i6 = Integer.parseInt(strArrSplit[2]);
                        int i7 = (int) ((i4 / 1080.0f) * width);
                        int i8 = (int) ((i5 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f2 = i7;
                        float f3 = i8;
                        float f4 = i7 + ((int) ((i6 / 1080.0f) * width));
                        canvas.drawLine(f2, f3, f4, f3, paint);
                        float f5 = i8 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f4, f3, f4, f5, paint);
                        canvas.drawLine(f4, f5, f2, f5, paint);
                        canvas.drawLine(f2, f5, f2, f3, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f2, f3, f4, f5, paint);
                        canvas.drawLine(f2, f5, f4, f3, paint);
                    }
                }
            }
        }
    }

    public Object e(int i2, Object obj) {
        if (i2 != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> map = this.n;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.n.get(str);
    }

    public final ConstraintWidget f(int i2) {
        if (i2 == 0) {
            return this.f710d;
        }
        View viewFindViewById = this.f708b.get(i2);
        if (viewFindViewById == null && (viewFindViewById = findViewById(i2)) != null && viewFindViewById != this && viewFindViewById.getParent() == this) {
            onViewAdded(viewFindViewById);
        }
        if (viewFindViewById == this) {
            return this.f710d;
        }
        if (viewFindViewById == null) {
            return null;
        }
        return ((b) viewFindViewById.getLayoutParams()).n0;
    }

    @Override // android.view.View
    public void forceLayout() {
        q();
        super.forceLayout();
    }

    public View g(int i2) {
        return this.f708b.get(i2);
    }

    public int getMaxHeight() {
        return this.f714h;
    }

    public int getMaxWidth() {
        return this.f713g;
    }

    public int getMinHeight() {
        return this.f712f;
    }

    public int getMinWidth() {
        return this.f711e;
    }

    public int getOptimizationLevel() {
        return this.f710d.X0();
    }

    public final ConstraintWidget k(View view) {
        if (view == this) {
            return this.f710d;
        }
        if (view == null) {
            return null;
        }
        return ((b) view.getLayoutParams()).n0;
    }

    public final void l(AttributeSet attributeSet, int i2, int i3) {
        this.f710d.d0(this);
        this.f710d.h1(this.v);
        this.f708b.put(getId(), this);
        this.k = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, e.ConstraintLayout_Layout, i2, i3);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i4 = 0; i4 < indexCount; i4++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i4);
                if (index == e.ConstraintLayout_Layout_android_minWidth) {
                    this.f711e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f711e);
                } else if (index == e.ConstraintLayout_Layout_android_minHeight) {
                    this.f712f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f712f);
                } else if (index == e.ConstraintLayout_Layout_android_maxWidth) {
                    this.f713g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f713g);
                } else if (index == e.ConstraintLayout_Layout_android_maxHeight) {
                    this.f714h = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f714h);
                } else if (index == e.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.j = typedArrayObtainStyledAttributes.getInt(index, this.j);
                } else if (index == e.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            r(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.l = null;
                        }
                    }
                } else if (index == e.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        b.f.c.b bVar = new b.f.c.b();
                        this.k = bVar;
                        bVar.u(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.k = null;
                    }
                    this.m = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f710d.i1(this.j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        View content;
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            b bVar = (b) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = bVar.n0;
            if ((childAt.getVisibility() != 8 || bVar.Z || bVar.a0 || bVar.c0 || zIsInEditMode) && !bVar.b0) {
                int iR = constraintWidget.R();
                int iS = constraintWidget.S();
                int iQ = constraintWidget.Q() + iR;
                int iW = constraintWidget.w() + iS;
                childAt.layout(iR, iS, iQ, iW);
                if ((childAt instanceof Placeholder) && (content = ((Placeholder) childAt).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(iR, iS, iQ, iW);
                }
            }
        }
        int size = this.f709c.size();
        if (size > 0) {
            for (int i7 = 0; i7 < size; i7++) {
                this.f709c.get(i7).p(this);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        this.w = i2;
        this.x = i3;
        this.f710d.j1(p());
        if (this.f715i) {
            this.f715i = false;
            if (x()) {
                this.f710d.l1();
            }
        }
        t(this.f710d, this.j, i2, i3);
        s(i2, i3, this.f710d.Q(), this.f710d.w(), this.f710d.d1(), this.f710d.b1());
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget constraintWidgetK = k(view);
        if ((view instanceof Guideline) && !(constraintWidgetK instanceof f)) {
            b bVar = (b) view.getLayoutParams();
            f fVar = new f();
            bVar.n0 = fVar;
            bVar.Z = true;
            fVar.S0(bVar.S);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.u();
            ((b) view.getLayoutParams()).a0 = true;
            if (!this.f709c.contains(constraintHelper)) {
                this.f709c.add(constraintHelper);
            }
        }
        this.f708b.put(view.getId(), view);
        this.f715i = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.f708b.remove(view.getId());
        this.f710d.N0(k(view));
        this.f709c.remove(view);
        this.f715i = true;
    }

    public boolean p() {
        if (Build.VERSION.SDK_INT >= 17) {
            return ((getContext().getApplicationInfo().flags & 4194304) != 0) && 1 == getLayoutDirection();
        }
        return false;
    }

    public final void q() {
        this.f715i = true;
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = 0;
        this.t = 0;
    }

    public void r(int i2) {
        this.l = new b.f.c.a(getContext(), this, i2);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        super.removeView(view);
        if (Build.VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        q();
        super.requestLayout();
    }

    public void s(int i2, int i3, int i4, int i5, boolean z, boolean z2) {
        c cVar = this.v;
        int i6 = cVar.f731e;
        int i7 = i4 + cVar.f730d;
        int i8 = i5 + i6;
        if (Build.VERSION.SDK_INT < 11) {
            setMeasuredDimension(i7, i8);
            this.o = i7;
            this.p = i8;
            return;
        }
        int iResolveSizeAndState = ViewGroup.resolveSizeAndState(i7, i2, 0);
        int iResolveSizeAndState2 = ViewGroup.resolveSizeAndState(i8, i3, 0) & 16777215;
        int iMin = Math.min(this.f713g, iResolveSizeAndState & 16777215);
        int iMin2 = Math.min(this.f714h, iResolveSizeAndState2);
        if (z) {
            iMin |= 16777216;
        }
        if (z2) {
            iMin2 |= 16777216;
        }
        setMeasuredDimension(iMin, iMin2);
        this.o = iMin;
        this.p = iMin2;
    }

    public void setConstraintSet(b.f.c.b bVar) {
        this.k = bVar;
    }

    @Override // android.view.View
    public void setId(int i2) {
        this.f708b.remove(getId());
        super.setId(i2);
        this.f708b.put(getId(), this);
    }

    public void setMaxHeight(int i2) {
        if (i2 == this.f714h) {
            return;
        }
        this.f714h = i2;
        requestLayout();
    }

    public void setMaxWidth(int i2) {
        if (i2 == this.f713g) {
            return;
        }
        this.f713g = i2;
        requestLayout();
    }

    public void setMinHeight(int i2) {
        if (i2 == this.f712f) {
            return;
        }
        this.f712f = i2;
        requestLayout();
    }

    public void setMinWidth(int i2) {
        if (i2 == this.f711e) {
            return;
        }
        this.f711e = i2;
        requestLayout();
    }

    public void setOnConstraintsChanged(b.f.c.c cVar) {
        b.f.c.a aVar = this.l;
        if (aVar != null) {
            aVar.c(cVar);
        }
    }

    public void setOptimizationLevel(int i2) {
        this.j = i2;
        this.f710d.i1(i2);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void t(d dVar, int i2, int i3, int i4) {
        int iMax;
        int mode = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i3);
        int mode2 = View.MeasureSpec.getMode(i4);
        int size2 = View.MeasureSpec.getSize(i4);
        int iMax2 = Math.max(0, getPaddingTop());
        int iMax3 = Math.max(0, getPaddingBottom());
        int i5 = iMax2 + iMax3;
        int paddingWidth = getPaddingWidth();
        this.v.c(i3, i4, iMax2, iMax3, paddingWidth, i5);
        if (Build.VERSION.SDK_INT >= 17) {
            int iMax4 = Math.max(0, getPaddingStart());
            int iMax5 = Math.max(0, getPaddingEnd());
            if (iMax4 <= 0 && iMax5 <= 0) {
                iMax4 = Math.max(0, getPaddingLeft());
            } else if (p()) {
                iMax4 = iMax5;
            }
            iMax = iMax4;
        } else {
            iMax = Math.max(0, getPaddingLeft());
        }
        int i6 = size - paddingWidth;
        int i7 = size2 - i5;
        w(dVar, mode, i6, mode2, i7);
        dVar.e1(i2, mode, i6, mode2, i7, this.o, this.p, iMax, iMax2);
    }

    public final void u() {
        boolean zIsInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ConstraintWidget constraintWidgetK = k(getChildAt(i2));
            if (constraintWidgetK != null) {
                constraintWidgetK.Z();
            }
        }
        if (zIsInEditMode) {
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                try {
                    String resourceName = getResources().getResourceName(childAt.getId());
                    v(0, resourceName, Integer.valueOf(childAt.getId()));
                    int iIndexOf = resourceName.indexOf(47);
                    if (iIndexOf != -1) {
                        resourceName = resourceName.substring(iIndexOf + 1);
                    }
                    f(childAt.getId()).e0(resourceName);
                } catch (Resources.NotFoundException unused) {
                }
            }
        }
        if (this.m != -1) {
            for (int i4 = 0; i4 < childCount; i4++) {
                View childAt2 = getChildAt(i4);
                if (childAt2.getId() == this.m && (childAt2 instanceof Constraints)) {
                    this.k = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        b.f.c.b bVar = this.k;
        if (bVar != null) {
            bVar.f(this, true);
        }
        this.f710d.O0();
        int size = this.f709c.size();
        if (size > 0) {
            for (int i5 = 0; i5 < size; i5++) {
                this.f709c.get(i5).t(this);
            }
        }
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt3 = getChildAt(i6);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).c(this);
            }
        }
        this.u.clear();
        this.u.put(0, this.f710d);
        this.u.put(getId(), this.f710d);
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt4 = getChildAt(i7);
            this.u.put(childAt4.getId(), k(childAt4));
        }
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt5 = getChildAt(i8);
            ConstraintWidget constraintWidgetK2 = k(childAt5);
            if (constraintWidgetK2 != null) {
                b bVar2 = (b) childAt5.getLayoutParams();
                this.f710d.a(constraintWidgetK2);
                b(zIsInEditMode, childAt5, constraintWidgetK2, bVar2, this.u);
            }
        }
    }

    public void v(int i2, Object obj, Object obj2) {
        if (i2 == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.n == null) {
                this.n = new HashMap<>();
            }
            String strSubstring = (String) obj;
            int iIndexOf = strSubstring.indexOf("/");
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(iIndexOf + 1);
            }
            this.n.put(strSubstring, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003e A[PHI: r2
  0x003e: PHI (r2v4 androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour) = 
  (r2v3 androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour)
  (r2v0 androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour)
 binds: [B:21:0x004a, B:17:0x003c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void w(b.f.b.j.d r8, int r9, int r10, int r11, int r12) {
        /*
            r7 = this;
            androidx.constraintlayout.widget.ConstraintLayout$c r0 = r7.v
            int r1 = r0.f731e
            int r0 = r0.f730d
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            int r3 = r7.getChildCount()
            r4 = 1073741824(0x40000000, float:2.0)
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = 0
            if (r9 == r5) goto L2e
            if (r9 == 0) goto L23
            if (r9 == r4) goto L1a
            r9 = r2
        L18:
            r10 = r6
            goto L38
        L1a:
            int r9 = r7.f713g
            int r9 = r9 - r0
            int r10 = java.lang.Math.min(r9, r10)
            r9 = r2
            goto L38
        L23:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L18
            int r10 = r7.f711e
            int r10 = java.lang.Math.max(r6, r10)
            goto L38
        L2e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L38
            int r10 = r7.f711e
            int r10 = java.lang.Math.max(r6, r10)
        L38:
            if (r11 == r5) goto L53
            if (r11 == 0) goto L48
            if (r11 == r4) goto L40
        L3e:
            r12 = r6
            goto L5d
        L40:
            int r11 = r7.f714h
            int r11 = r11 - r1
            int r12 = java.lang.Math.min(r11, r12)
            goto L5d
        L48:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L3e
            int r11 = r7.f712f
            int r12 = java.lang.Math.max(r6, r11)
            goto L5d
        L53:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L5d
            int r11 = r7.f712f
            int r12 = java.lang.Math.max(r6, r11)
        L5d:
            int r11 = r8.Q()
            if (r10 != r11) goto L69
            int r11 = r8.w()
            if (r12 == r11) goto L6c
        L69:
            r8.a1()
        L6c:
            r8.G0(r6)
            r8.H0(r6)
            int r11 = r7.f713g
            int r11 = r11 - r0
            r8.t0(r11)
            int r11 = r7.f714h
            int r11 = r11 - r1
            r8.s0(r11)
            r8.v0(r6)
            r8.u0(r6)
            r8.m0(r9)
            r8.F0(r10)
            r8.B0(r2)
            r8.i0(r12)
            int r9 = r7.f711e
            int r9 = r9 - r0
            r8.v0(r9)
            int r9 = r7.f712f
            int r9 = r9 - r1
            r8.u0(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.w(b.f.b.j.d, int, int, int, int):void");
    }

    public final boolean x() {
        int childCount = getChildCount();
        boolean z = false;
        int i2 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            if (getChildAt(i2).isLayoutRequested()) {
                z = true;
                break;
            }
            i2++;
        }
        if (z) {
            u();
        }
        return z;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new b(layoutParams);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f708b = new SparseArray<>();
        this.f709c = new ArrayList<>(4);
        this.f710d = new d();
        this.f711e = 0;
        this.f712f = 0;
        this.f713g = Integer.MAX_VALUE;
        this.f714h = Integer.MAX_VALUE;
        this.f715i = true;
        this.j = 263;
        this.k = null;
        this.l = null;
        this.m = -1;
        this.n = new HashMap<>();
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = 0;
        this.t = 0;
        this.u = new SparseArray<>();
        this.v = new c(this);
        this.w = 0;
        this.x = 0;
        l(attributeSet, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f708b = new SparseArray<>();
        this.f709c = new ArrayList<>(4);
        this.f710d = new d();
        this.f711e = 0;
        this.f712f = 0;
        this.f713g = Integer.MAX_VALUE;
        this.f714h = Integer.MAX_VALUE;
        this.f715i = true;
        this.j = 263;
        this.k = null;
        this.l = null;
        this.m = -1;
        this.n = new HashMap<>();
        this.o = -1;
        this.p = -1;
        this.q = -1;
        this.r = -1;
        this.s = 0;
        this.t = 0;
        this.u = new SparseArray<>();
        this.v = new c(this);
        this.w = 0;
        this.x = 0;
        l(attributeSet, i2, 0);
    }

    public static class b extends ViewGroup.MarginLayoutParams {
        public float A;
        public String B;
        public float C;
        public int D;
        public float E;
        public float F;
        public int G;
        public int H;
        public int I;
        public int J;
        public int K;
        public int L;
        public int M;
        public int N;
        public float O;
        public float P;
        public int Q;
        public int R;
        public int S;
        public boolean T;
        public boolean U;
        public String V;
        public boolean W;
        public boolean X;
        public boolean Y;
        public boolean Z;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f717a;
        public boolean a0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f718b;
        public boolean b0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f719c;
        public boolean c0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f720d;
        public int d0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f721e;
        public int e0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f722f;
        public int f0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f723g;
        public int g0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f724h;
        public int h0;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f725i;
        public int i0;
        public int j;
        public float j0;
        public int k;
        public int k0;
        public int l;
        public int l0;
        public int m;
        public float m0;
        public int n;
        public ConstraintWidget n0;
        public float o;
        public boolean o0;
        public int p;
        public int q;
        public int r;
        public int s;
        public int t;
        public int u;
        public int v;
        public int w;
        public int x;
        public int y;
        public float z;

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final SparseIntArray f726a;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                f726a = sparseIntArray;
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintCircle, 2);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                sparseIntArray.append(e.ConstraintLayout_Layout_android_orientation, 1);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                sparseIntArray.append(e.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
                f726a.append(e.ConstraintLayout_Layout_layout_constraintTag, 51);
            }
        }

        public b(Context context, AttributeSet attributeSet) {
            int i2;
            super(context, attributeSet);
            this.f717a = -1;
            this.f718b = -1;
            this.f719c = -1.0f;
            this.f720d = -1;
            this.f721e = -1;
            this.f722f = -1;
            this.f723g = -1;
            this.f724h = -1;
            this.f725i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 0.0f;
            this.D = 1;
            this.E = -1.0f;
            this.F = -1.0f;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 1.0f;
            this.P = 1.0f;
            this.Q = -1;
            this.R = -1;
            this.S = -1;
            this.T = false;
            this.U = false;
            this.V = null;
            this.W = true;
            this.X = true;
            this.Y = false;
            this.Z = false;
            this.a0 = false;
            this.b0 = false;
            this.c0 = false;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = -1;
            this.h0 = -1;
            this.i0 = -1;
            this.j0 = 0.5f;
            this.n0 = new ConstraintWidget();
            this.o0 = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, e.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i3 = 0; i3 < indexCount; i3++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i3);
                int i4 = a.f726a.get(index);
                switch (i4) {
                    case 1:
                        this.S = typedArrayObtainStyledAttributes.getInt(index, this.S);
                        break;
                    case 2:
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.m);
                        this.m = resourceId;
                        if (resourceId == -1) {
                            this.m = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 3:
                        this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.n);
                        break;
                    case 4:
                        float f2 = typedArrayObtainStyledAttributes.getFloat(index, this.o) % 360.0f;
                        this.o = f2;
                        if (f2 < 0.0f) {
                            this.o = (360.0f - f2) % 360.0f;
                        }
                        break;
                    case 5:
                        this.f717a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f717a);
                        break;
                    case 6:
                        this.f718b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f718b);
                        break;
                    case 7:
                        this.f719c = typedArrayObtainStyledAttributes.getFloat(index, this.f719c);
                        break;
                    case 8:
                        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, this.f720d);
                        this.f720d = resourceId2;
                        if (resourceId2 == -1) {
                            this.f720d = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 9:
                        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, this.f721e);
                        this.f721e = resourceId3;
                        if (resourceId3 == -1) {
                            this.f721e = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 10:
                        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, this.f722f);
                        this.f722f = resourceId4;
                        if (resourceId4 == -1) {
                            this.f722f = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 11:
                        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, this.f723g);
                        this.f723g = resourceId5;
                        if (resourceId5 == -1) {
                            this.f723g = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 12:
                        int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, this.f724h);
                        this.f724h = resourceId6;
                        if (resourceId6 == -1) {
                            this.f724h = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 13:
                        int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, this.f725i);
                        this.f725i = resourceId7;
                        if (resourceId7 == -1) {
                            this.f725i = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 14:
                        int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, this.j);
                        this.j = resourceId8;
                        if (resourceId8 == -1) {
                            this.j = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 15:
                        int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, this.k);
                        this.k = resourceId9;
                        if (resourceId9 == -1) {
                            this.k = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 16:
                        int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, this.l);
                        this.l = resourceId10;
                        if (resourceId10 == -1) {
                            this.l = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 17:
                        int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, this.p);
                        this.p = resourceId11;
                        if (resourceId11 == -1) {
                            this.p = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 18:
                        int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, this.q);
                        this.q = resourceId12;
                        if (resourceId12 == -1) {
                            this.q = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 19:
                        int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, this.r);
                        this.r = resourceId13;
                        if (resourceId13 == -1) {
                            this.r = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 20:
                        int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, this.s);
                        this.s = resourceId14;
                        if (resourceId14 == -1) {
                            this.s = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 21:
                        this.t = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.t);
                        break;
                    case 22:
                        this.u = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.u);
                        break;
                    case 23:
                        this.v = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.v);
                        break;
                    case 24:
                        this.w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.w);
                        break;
                    case 25:
                        this.x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.x);
                        break;
                    case 26:
                        this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.y);
                        break;
                    case 27:
                        this.T = typedArrayObtainStyledAttributes.getBoolean(index, this.T);
                        break;
                    case 28:
                        this.U = typedArrayObtainStyledAttributes.getBoolean(index, this.U);
                        break;
                    case 29:
                        this.z = typedArrayObtainStyledAttributes.getFloat(index, this.z);
                        break;
                    case 30:
                        this.A = typedArrayObtainStyledAttributes.getFloat(index, this.A);
                        break;
                    case 31:
                        int i5 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.I = i5;
                        if (i5 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        }
                        break;
                    case 32:
                        int i6 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.J = i6;
                        if (i6 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                        }
                        break;
                    case 33:
                        try {
                            this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                        } catch (Exception unused) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.K) == -2) {
                                this.K = -2;
                            }
                        }
                        break;
                    case 34:
                        try {
                            this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                        } catch (Exception unused2) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.M) == -2) {
                                this.M = -2;
                            }
                        }
                        break;
                    case 35:
                        this.O = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.O));
                        this.I = 2;
                        break;
                    case 36:
                        try {
                            this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        } catch (Exception unused3) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.L) == -2) {
                                this.L = -2;
                            }
                        }
                        break;
                    case 37:
                        try {
                            this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.N);
                        } catch (Exception unused4) {
                            if (typedArrayObtainStyledAttributes.getInt(index, this.N) == -2) {
                                this.N = -2;
                            }
                        }
                        break;
                    case 38:
                        this.P = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, this.P));
                        this.J = 2;
                        break;
                    default:
                        switch (i4) {
                            case 44:
                                String string = typedArrayObtainStyledAttributes.getString(index);
                                this.B = string;
                                this.C = Float.NaN;
                                this.D = -1;
                                if (string != null) {
                                    int length = string.length();
                                    int iIndexOf = this.B.indexOf(44);
                                    if (iIndexOf <= 0 || iIndexOf >= length - 1) {
                                        i2 = 0;
                                    } else {
                                        String strSubstring = this.B.substring(0, iIndexOf);
                                        if (strSubstring.equalsIgnoreCase("W")) {
                                            this.D = 0;
                                        } else if (strSubstring.equalsIgnoreCase("H")) {
                                            this.D = 1;
                                        }
                                        i2 = iIndexOf + 1;
                                    }
                                    int iIndexOf2 = this.B.indexOf(58);
                                    if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                                        String strSubstring2 = this.B.substring(i2);
                                        if (strSubstring2.length() > 0) {
                                            this.C = Float.parseFloat(strSubstring2);
                                        }
                                    } else {
                                        String strSubstring3 = this.B.substring(i2, iIndexOf2);
                                        String strSubstring4 = this.B.substring(iIndexOf2 + 1);
                                        if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                                            try {
                                                float f3 = Float.parseFloat(strSubstring3);
                                                float f4 = Float.parseFloat(strSubstring4);
                                                if (f3 > 0.0f && f4 > 0.0f) {
                                                    if (this.D == 1) {
                                                        this.C = Math.abs(f4 / f3);
                                                    } else {
                                                        this.C = Math.abs(f3 / f4);
                                                    }
                                                }
                                            } catch (NumberFormatException unused5) {
                                            }
                                        }
                                    }
                                }
                                break;
                            case 45:
                                this.E = typedArrayObtainStyledAttributes.getFloat(index, this.E);
                                break;
                            case 46:
                                this.F = typedArrayObtainStyledAttributes.getFloat(index, this.F);
                                break;
                            case 47:
                                this.G = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 48:
                                this.H = typedArrayObtainStyledAttributes.getInt(index, 0);
                                break;
                            case 49:
                                this.Q = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.Q);
                                break;
                            case 50:
                                this.R = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.R);
                                break;
                            case 51:
                                this.V = typedArrayObtainStyledAttributes.getString(index);
                                break;
                        }
                        break;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
            c();
        }

        public String a() {
            return this.V;
        }

        public ConstraintWidget b() {
            return this.n0;
        }

        public void c() {
            this.Z = false;
            this.W = true;
            this.X = true;
            int i2 = ((ViewGroup.MarginLayoutParams) this).width;
            if (i2 == -2 && this.T) {
                this.W = false;
                if (this.I == 0) {
                    this.I = 1;
                }
            }
            int i3 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i3 == -2 && this.U) {
                this.X = false;
                if (this.J == 0) {
                    this.J = 1;
                }
            }
            if (i2 == 0 || i2 == -1) {
                this.W = false;
                if (i2 == 0 && this.I == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.T = true;
                }
            }
            if (i3 == 0 || i3 == -1) {
                this.X = false;
                if (i3 == 0 && this.J == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.U = true;
                }
            }
            if (this.f719c == -1.0f && this.f717a == -1 && this.f718b == -1) {
                return;
            }
            this.Z = true;
            this.W = true;
            this.X = true;
            if (!(this.n0 instanceof f)) {
                this.n0 = new f();
            }
            ((f) this.n0).S0(this.S);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0052  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0059  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x0066  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x006c  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x007e  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x0086  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x0017  */
        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        @android.annotation.TargetApi(17)
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void resolveLayoutDirection(int r10) {
            /*
                Method dump skipped, instruction units count: 263
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.b.resolveLayoutDirection(int):void");
        }

        public b(int i2, int i3) {
            super(i2, i3);
            this.f717a = -1;
            this.f718b = -1;
            this.f719c = -1.0f;
            this.f720d = -1;
            this.f721e = -1;
            this.f722f = -1;
            this.f723g = -1;
            this.f724h = -1;
            this.f725i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 0.0f;
            this.D = 1;
            this.E = -1.0f;
            this.F = -1.0f;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 1.0f;
            this.P = 1.0f;
            this.Q = -1;
            this.R = -1;
            this.S = -1;
            this.T = false;
            this.U = false;
            this.V = null;
            this.W = true;
            this.X = true;
            this.Y = false;
            this.Z = false;
            this.a0 = false;
            this.b0 = false;
            this.c0 = false;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = -1;
            this.h0 = -1;
            this.i0 = -1;
            this.j0 = 0.5f;
            this.n0 = new ConstraintWidget();
            this.o0 = false;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f717a = -1;
            this.f718b = -1;
            this.f719c = -1.0f;
            this.f720d = -1;
            this.f721e = -1;
            this.f722f = -1;
            this.f723g = -1;
            this.f724h = -1;
            this.f725i = -1;
            this.j = -1;
            this.k = -1;
            this.l = -1;
            this.m = -1;
            this.n = 0;
            this.o = 0.0f;
            this.p = -1;
            this.q = -1;
            this.r = -1;
            this.s = -1;
            this.t = -1;
            this.u = -1;
            this.v = -1;
            this.w = -1;
            this.x = -1;
            this.y = -1;
            this.z = 0.5f;
            this.A = 0.5f;
            this.B = null;
            this.C = 0.0f;
            this.D = 1;
            this.E = -1.0f;
            this.F = -1.0f;
            this.G = 0;
            this.H = 0;
            this.I = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            this.M = 0;
            this.N = 0;
            this.O = 1.0f;
            this.P = 1.0f;
            this.Q = -1;
            this.R = -1;
            this.S = -1;
            this.T = false;
            this.U = false;
            this.V = null;
            this.W = true;
            this.X = true;
            this.Y = false;
            this.Z = false;
            this.a0 = false;
            this.b0 = false;
            this.c0 = false;
            this.d0 = -1;
            this.e0 = -1;
            this.f0 = -1;
            this.g0 = -1;
            this.h0 = -1;
            this.i0 = -1;
            this.j0 = 0.5f;
            this.n0 = new ConstraintWidget();
            this.o0 = false;
        }
    }
}
