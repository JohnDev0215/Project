package androidx.constraintlayout.widget;

import a0.b;
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
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import z.d;
import z.f;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public SparseArray<View> f1069d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList<ConstraintHelper> f1070e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public d f1071f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1072g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1073h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1074i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1075j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f1076k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1077l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public b0.b f1078m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public b0.a f1079n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f1080o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public HashMap<String, Integer> f1081p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f1082q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f1083r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f1084s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f1085t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f1086u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f1087v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public SparseArray<ConstraintWidget> f1088w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public c f1089x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f1090y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f1091z;

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1092a;

        static {
            int[] iArr = new int[ConstraintWidget.DimensionBehaviour.values().length];
            f1092a = iArr;
            try {
                iArr[ConstraintWidget.DimensionBehaviour.FIXED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1092a[ConstraintWidget.DimensionBehaviour.WRAP_CONTENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1092a[ConstraintWidget.DimensionBehaviour.MATCH_PARENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1092a[ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public class c implements b.InterfaceC0000b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ConstraintLayout f1135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1136b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f1137c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1138d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1139e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1140f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1141g;

        public c(ConstraintLayout constraintLayout) {
            this.f1135a = constraintLayout;
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
        @Override // a0.b.InterfaceC0000b
        @android.annotation.SuppressLint({"WrongCall"})
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void a(androidx.constraintlayout.solver.widgets.ConstraintWidget r20, a0.b.a r21) {
            /*
                Method dump skipped, instruction units count: 616
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.c.a(androidx.constraintlayout.solver.widgets.ConstraintWidget, a0.b$a):void");
        }

        @Override // a0.b.InterfaceC0000b
        public final void b() {
            int childCount = this.f1135a.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                View childAt = this.f1135a.getChildAt(i8);
                if (childAt instanceof Placeholder) {
                    ((Placeholder) childAt).b(this.f1135a);
                }
            }
            int size = this.f1135a.f1070e.size();
            if (size > 0) {
                for (int i9 = 0; i9 < size; i9++) {
                    ((ConstraintHelper) this.f1135a.f1070e.get(i9)).q(this.f1135a);
                }
            }
        }

        public void c(int i8, int i9, int i10, int i11, int i12, int i13) {
            this.f1136b = i10;
            this.f1137c = i11;
            this.f1138d = i12;
            this.f1139e = i13;
            this.f1140f = i8;
            this.f1141g = i9;
        }
    }

    public ConstraintLayout(Context context) {
        super(context);
        this.f1069d = new SparseArray<>();
        this.f1070e = new ArrayList<>(4);
        this.f1071f = new d();
        this.f1072g = 0;
        this.f1073h = 0;
        this.f1074i = Integer.MAX_VALUE;
        this.f1075j = Integer.MAX_VALUE;
        this.f1076k = true;
        this.f1077l = 263;
        this.f1078m = null;
        this.f1079n = null;
        this.f1080o = -1;
        this.f1081p = new HashMap<>();
        this.f1082q = -1;
        this.f1083r = -1;
        this.f1084s = -1;
        this.f1085t = -1;
        this.f1086u = 0;
        this.f1087v = 0;
        this.f1088w = new SparseArray<>();
        this.f1089x = new c(this);
        this.f1090y = 0;
        this.f1091z = 0;
        l(null, 0, 0);
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingLeft()) + Math.max(0, getPaddingRight());
        int iMax2 = Build.VERSION.SDK_INT >= 17 ? Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart()) : 0;
        return iMax2 > 0 ? iMax2 : iMax;
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i8, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i8, layoutParams);
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
        ArrayList<ConstraintHelper> arrayList = this.f1070e;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i8 = 0; i8 < size; i8++) {
                this.f1070e.get(i8).r(this);
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = getWidth();
            float height = getHeight();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = getChildAt(i9);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                    if (strArrSplit.length == 4) {
                        int i10 = Integer.parseInt(strArrSplit[0]);
                        int i11 = Integer.parseInt(strArrSplit[1]);
                        int i12 = Integer.parseInt(strArrSplit[2]);
                        int i13 = (int) ((i10 / 1080.0f) * width);
                        int i14 = (int) ((i11 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f8 = i13;
                        float f9 = i14;
                        float f10 = i13 + ((int) ((i12 / 1080.0f) * width));
                        canvas.drawLine(f8, f9, f10, f9, paint);
                        float f11 = i14 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f10, f9, f10, f11, paint);
                        canvas.drawLine(f10, f11, f8, f11, paint);
                        canvas.drawLine(f8, f11, f8, f9, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f8, f9, f10, f11, paint);
                        canvas.drawLine(f8, f11, f10, f9, paint);
                    }
                }
            }
        }
    }

    public Object e(int i8, Object obj) {
        if (i8 != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> map = this.f1081p;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.f1081p.get(str);
    }

    public final ConstraintWidget f(int i8) {
        if (i8 == 0) {
            return this.f1071f;
        }
        View viewFindViewById = this.f1069d.get(i8);
        if (viewFindViewById == null && (viewFindViewById = findViewById(i8)) != null && viewFindViewById != this && viewFindViewById.getParent() == this) {
            onViewAdded(viewFindViewById);
        }
        if (viewFindViewById == this) {
            return this.f1071f;
        }
        if (viewFindViewById == null) {
            return null;
        }
        return ((b) viewFindViewById.getLayoutParams()).f1120n0;
    }

    @Override // android.view.View
    public void forceLayout() {
        q();
        super.forceLayout();
    }

    public View g(int i8) {
        return this.f1069d.get(i8);
    }

    public int getMaxHeight() {
        return this.f1075j;
    }

    public int getMaxWidth() {
        return this.f1074i;
    }

    public int getMinHeight() {
        return this.f1073h;
    }

    public int getMinWidth() {
        return this.f1072g;
    }

    public int getOptimizationLevel() {
        return this.f1071f.X0();
    }

    public final ConstraintWidget k(View view) {
        if (view == this) {
            return this.f1071f;
        }
        if (view == null) {
            return null;
        }
        return ((b) view.getLayoutParams()).f1120n0;
    }

    public final void l(AttributeSet attributeSet, int i8, int i9) {
        this.f1071f.d0(this);
        this.f1071f.h1(this.f1089x);
        this.f1069d.put(getId(), this);
        this.f1078m = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout, i8, i9);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i10 = 0; i10 < indexCount; i10++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i10);
                if (index == R$styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.f1072g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1072g);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.f1073h = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1073h);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.f1074i = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1074i);
                } else if (index == R$styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.f1075j = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1075j);
                } else if (index == R$styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.f1077l = typedArrayObtainStyledAttributes.getInt(index, this.f1077l);
                } else if (index == R$styleable.ConstraintLayout_Layout_layoutDescription) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            r(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.f1079n = null;
                        }
                    }
                } else if (index == R$styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        b0.b bVar = new b0.b();
                        this.f1078m = bVar;
                        bVar.u(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.f1078m = null;
                    }
                    this.f1080o = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        this.f1071f.i1(this.f1077l);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        View content;
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt = getChildAt(i12);
            b bVar = (b) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = bVar.f1120n0;
            if ((childAt.getVisibility() != 8 || bVar.Z || bVar.f1094a0 || bVar.f1098c0 || zIsInEditMode) && !bVar.f1096b0) {
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
        int size = this.f1070e.size();
        if (size > 0) {
            for (int i13 = 0; i13 < size; i13++) {
                this.f1070e.get(i13).p(this);
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        this.f1090y = i8;
        this.f1091z = i9;
        this.f1071f.j1(p());
        if (this.f1076k) {
            this.f1076k = false;
            if (x()) {
                this.f1071f.l1();
            }
        }
        t(this.f1071f, this.f1077l, i8, i9);
        s(i8, i9, this.f1071f.Q(), this.f1071f.w(), this.f1071f.d1(), this.f1071f.b1());
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
            bVar.f1120n0 = fVar;
            bVar.Z = true;
            fVar.S0(bVar.S);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.u();
            ((b) view.getLayoutParams()).f1094a0 = true;
            if (!this.f1070e.contains(constraintHelper)) {
                this.f1070e.add(constraintHelper);
            }
        }
        this.f1069d.put(view.getId(), view);
        this.f1076k = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.f1069d.remove(view.getId());
        this.f1071f.N0(k(view));
        this.f1070e.remove(view);
        this.f1076k = true;
    }

    public boolean p() {
        if (Build.VERSION.SDK_INT >= 17) {
            return ((getContext().getApplicationInfo().flags & 4194304) != 0) && 1 == getLayoutDirection();
        }
        return false;
    }

    public final void q() {
        this.f1076k = true;
        this.f1082q = -1;
        this.f1083r = -1;
        this.f1084s = -1;
        this.f1085t = -1;
        this.f1086u = 0;
        this.f1087v = 0;
    }

    public void r(int i8) {
        this.f1079n = new b0.a(getContext(), this, i8);
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

    public void s(int i8, int i9, int i10, int i11, boolean z7, boolean z8) {
        c cVar = this.f1089x;
        int i12 = cVar.f1139e;
        int i13 = i10 + cVar.f1138d;
        int i14 = i11 + i12;
        if (Build.VERSION.SDK_INT < 11) {
            setMeasuredDimension(i13, i14);
            this.f1082q = i13;
            this.f1083r = i14;
            return;
        }
        int iResolveSizeAndState = ViewGroup.resolveSizeAndState(i13, i8, 0);
        int iResolveSizeAndState2 = ViewGroup.resolveSizeAndState(i14, i9, 0) & 16777215;
        int iMin = Math.min(this.f1074i, iResolveSizeAndState & 16777215);
        int iMin2 = Math.min(this.f1075j, iResolveSizeAndState2);
        if (z7) {
            iMin |= 16777216;
        }
        if (z8) {
            iMin2 |= 16777216;
        }
        setMeasuredDimension(iMin, iMin2);
        this.f1082q = iMin;
        this.f1083r = iMin2;
    }

    public void setConstraintSet(b0.b bVar) {
        this.f1078m = bVar;
    }

    @Override // android.view.View
    public void setId(int i8) {
        this.f1069d.remove(getId());
        super.setId(i8);
        this.f1069d.put(getId(), this);
    }

    public void setMaxHeight(int i8) {
        if (i8 == this.f1075j) {
            return;
        }
        this.f1075j = i8;
        requestLayout();
    }

    public void setMaxWidth(int i8) {
        if (i8 == this.f1074i) {
            return;
        }
        this.f1074i = i8;
        requestLayout();
    }

    public void setMinHeight(int i8) {
        if (i8 == this.f1073h) {
            return;
        }
        this.f1073h = i8;
        requestLayout();
    }

    public void setMinWidth(int i8) {
        if (i8 == this.f1072g) {
            return;
        }
        this.f1072g = i8;
        requestLayout();
    }

    public void setOnConstraintsChanged(b0.c cVar) {
        b0.a aVar = this.f1079n;
        if (aVar != null) {
            aVar.c(cVar);
        }
    }

    public void setOptimizationLevel(int i8) {
        this.f1077l = i8;
        this.f1071f.i1(i8);
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void t(d dVar, int i8, int i9, int i10) {
        int iMax;
        int mode = View.MeasureSpec.getMode(i9);
        int size = View.MeasureSpec.getSize(i9);
        int mode2 = View.MeasureSpec.getMode(i10);
        int size2 = View.MeasureSpec.getSize(i10);
        int iMax2 = Math.max(0, getPaddingTop());
        int iMax3 = Math.max(0, getPaddingBottom());
        int i11 = iMax2 + iMax3;
        int paddingWidth = getPaddingWidth();
        this.f1089x.c(i9, i10, iMax2, iMax3, paddingWidth, i11);
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
        int i12 = size - paddingWidth;
        int i13 = size2 - i11;
        w(dVar, mode, i12, mode2, i13);
        dVar.e1(i8, mode, i12, mode2, i13, this.f1082q, this.f1083r, iMax, iMax2);
    }

    public final void u() {
        boolean zIsInEditMode = isInEditMode();
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            ConstraintWidget constraintWidgetK = k(getChildAt(i8));
            if (constraintWidgetK != null) {
                constraintWidgetK.Z();
            }
        }
        if (zIsInEditMode) {
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = getChildAt(i9);
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
        if (this.f1080o != -1) {
            for (int i10 = 0; i10 < childCount; i10++) {
                View childAt2 = getChildAt(i10);
                if (childAt2.getId() == this.f1080o && (childAt2 instanceof Constraints)) {
                    this.f1078m = ((Constraints) childAt2).getConstraintSet();
                }
            }
        }
        b0.b bVar = this.f1078m;
        if (bVar != null) {
            bVar.f(this, true);
        }
        this.f1071f.O0();
        int size = this.f1070e.size();
        if (size > 0) {
            for (int i11 = 0; i11 < size; i11++) {
                this.f1070e.get(i11).t(this);
            }
        }
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt3 = getChildAt(i12);
            if (childAt3 instanceof Placeholder) {
                ((Placeholder) childAt3).c(this);
            }
        }
        this.f1088w.clear();
        this.f1088w.put(0, this.f1071f);
        this.f1088w.put(getId(), this.f1071f);
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt4 = getChildAt(i13);
            this.f1088w.put(childAt4.getId(), k(childAt4));
        }
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt5 = getChildAt(i14);
            ConstraintWidget constraintWidgetK2 = k(childAt5);
            if (constraintWidgetK2 != null) {
                b bVar2 = (b) childAt5.getLayoutParams();
                this.f1071f.a(constraintWidgetK2);
                b(zIsInEditMode, childAt5, constraintWidgetK2, bVar2, this.f1088w);
            }
        }
    }

    public void v(int i8, Object obj, Object obj2) {
        if (i8 == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.f1081p == null) {
                this.f1081p = new HashMap<>();
            }
            String strSubstring = (String) obj;
            int iIndexOf = strSubstring.indexOf("/");
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(iIndexOf + 1);
            }
            this.f1081p.put(strSubstring, Integer.valueOf(((Integer) obj2).intValue()));
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
    public void w(z.d r8, int r9, int r10, int r11, int r12) {
        /*
            r7 = this;
            androidx.constraintlayout.widget.ConstraintLayout$c r0 = r7.f1089x
            int r1 = r0.f1139e
            int r0 = r0.f1138d
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
            int r9 = r7.f1074i
            int r9 = r9 - r0
            int r10 = java.lang.Math.min(r9, r10)
            r9 = r2
            goto L38
        L23:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L18
            int r10 = r7.f1072g
            int r10 = java.lang.Math.max(r6, r10)
            goto L38
        L2e:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L38
            int r10 = r7.f1072g
            int r10 = java.lang.Math.max(r6, r10)
        L38:
            if (r11 == r5) goto L53
            if (r11 == 0) goto L48
            if (r11 == r4) goto L40
        L3e:
            r12 = r6
            goto L5d
        L40:
            int r11 = r7.f1075j
            int r11 = r11 - r1
            int r12 = java.lang.Math.min(r11, r12)
            goto L5d
        L48:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L3e
            int r11 = r7.f1073h
            int r12 = java.lang.Math.max(r6, r11)
            goto L5d
        L53:
            androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r3 != 0) goto L5d
            int r11 = r7.f1073h
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
            int r11 = r7.f1074i
            int r11 = r11 - r0
            r8.t0(r11)
            int r11 = r7.f1075j
            int r11 = r11 - r1
            r8.s0(r11)
            r8.v0(r6)
            r8.u0(r6)
            r8.m0(r9)
            r8.F0(r10)
            r8.B0(r2)
            r8.i0(r12)
            int r9 = r7.f1072g
            int r9 = r9 - r0
            r8.v0(r9)
            int r9 = r7.f1073h
            int r9 = r9 - r1
            r8.u0(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.w(z.d, int, int, int, int):void");
    }

    public final boolean x() {
        int childCount = getChildCount();
        boolean z7 = false;
        int i8 = 0;
        while (true) {
            if (i8 >= childCount) {
                break;
            }
            if (getChildAt(i8).isLayoutRequested()) {
                z7 = true;
                break;
            }
            i8++;
        }
        if (z7) {
            u();
        }
        return z7;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new b(layoutParams);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1069d = new SparseArray<>();
        this.f1070e = new ArrayList<>(4);
        this.f1071f = new d();
        this.f1072g = 0;
        this.f1073h = 0;
        this.f1074i = Integer.MAX_VALUE;
        this.f1075j = Integer.MAX_VALUE;
        this.f1076k = true;
        this.f1077l = 263;
        this.f1078m = null;
        this.f1079n = null;
        this.f1080o = -1;
        this.f1081p = new HashMap<>();
        this.f1082q = -1;
        this.f1083r = -1;
        this.f1084s = -1;
        this.f1085t = -1;
        this.f1086u = 0;
        this.f1087v = 0;
        this.f1088w = new SparseArray<>();
        this.f1089x = new c(this);
        this.f1090y = 0;
        this.f1091z = 0;
        l(attributeSet, 0, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f1069d = new SparseArray<>();
        this.f1070e = new ArrayList<>(4);
        this.f1071f = new d();
        this.f1072g = 0;
        this.f1073h = 0;
        this.f1074i = Integer.MAX_VALUE;
        this.f1075j = Integer.MAX_VALUE;
        this.f1076k = true;
        this.f1077l = 263;
        this.f1078m = null;
        this.f1079n = null;
        this.f1080o = -1;
        this.f1081p = new HashMap<>();
        this.f1082q = -1;
        this.f1083r = -1;
        this.f1084s = -1;
        this.f1085t = -1;
        this.f1086u = 0;
        this.f1087v = 0;
        this.f1088w = new SparseArray<>();
        this.f1089x = new c(this);
        this.f1090y = 0;
        this.f1091z = 0;
        l(attributeSet, i8, 0);
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
        public int f1093a;

        /* JADX INFO: renamed from: a0, reason: collision with root package name */
        public boolean f1094a0;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1095b;

        /* JADX INFO: renamed from: b0, reason: collision with root package name */
        public boolean f1096b0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1097c;

        /* JADX INFO: renamed from: c0, reason: collision with root package name */
        public boolean f1098c0;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f1099d;

        /* JADX INFO: renamed from: d0, reason: collision with root package name */
        public int f1100d0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f1101e;

        /* JADX INFO: renamed from: e0, reason: collision with root package name */
        public int f1102e0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f1103f;

        /* JADX INFO: renamed from: f0, reason: collision with root package name */
        public int f1104f0;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f1105g;

        /* JADX INFO: renamed from: g0, reason: collision with root package name */
        public int f1106g0;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f1107h;

        /* JADX INFO: renamed from: h0, reason: collision with root package name */
        public int f1108h0;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f1109i;

        /* JADX INFO: renamed from: i0, reason: collision with root package name */
        public int f1110i0;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public int f1111j;

        /* JADX INFO: renamed from: j0, reason: collision with root package name */
        public float f1112j0;

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public int f1113k;

        /* JADX INFO: renamed from: k0, reason: collision with root package name */
        public int f1114k0;

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public int f1115l;

        /* JADX INFO: renamed from: l0, reason: collision with root package name */
        public int f1116l0;

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public int f1117m;

        /* JADX INFO: renamed from: m0, reason: collision with root package name */
        public float f1118m0;

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public int f1119n;

        /* JADX INFO: renamed from: n0, reason: collision with root package name */
        public ConstraintWidget f1120n0;

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public float f1121o;

        /* JADX INFO: renamed from: o0, reason: collision with root package name */
        public boolean f1122o0;

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public int f1123p;

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public int f1124q;

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public int f1125r;

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public int f1126s;

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public int f1127t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public int f1128u;

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public int f1129v;

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public int f1130w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public int f1131x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public int f1132y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public float f1133z;

        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public static final SparseIntArray f1134a;

            static {
                SparseIntArray sparseIntArray = new SparseIntArray();
                f1134a = sparseIntArray;
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_android_orientation, 1);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                sparseIntArray.append(R$styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
                f1134a.append(R$styleable.ConstraintLayout_Layout_layout_constraintTag, 51);
            }
        }

        public b(Context context, AttributeSet attributeSet) {
            int i8;
            super(context, attributeSet);
            this.f1093a = -1;
            this.f1095b = -1;
            this.f1097c = -1.0f;
            this.f1099d = -1;
            this.f1101e = -1;
            this.f1103f = -1;
            this.f1105g = -1;
            this.f1107h = -1;
            this.f1109i = -1;
            this.f1111j = -1;
            this.f1113k = -1;
            this.f1115l = -1;
            this.f1117m = -1;
            this.f1119n = 0;
            this.f1121o = 0.0f;
            this.f1123p = -1;
            this.f1124q = -1;
            this.f1125r = -1;
            this.f1126s = -1;
            this.f1127t = -1;
            this.f1128u = -1;
            this.f1129v = -1;
            this.f1130w = -1;
            this.f1131x = -1;
            this.f1132y = -1;
            this.f1133z = 0.5f;
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
            this.f1094a0 = false;
            this.f1096b0 = false;
            this.f1098c0 = false;
            this.f1100d0 = -1;
            this.f1102e0 = -1;
            this.f1104f0 = -1;
            this.f1106g0 = -1;
            this.f1108h0 = -1;
            this.f1110i0 = -1;
            this.f1112j0 = 0.5f;
            this.f1120n0 = new ConstraintWidget();
            this.f1122o0 = false;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ConstraintLayout_Layout);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i9 = 0; i9 < indexCount; i9++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i9);
                int i10 = a.f1134a.get(index);
                switch (i10) {
                    case 1:
                        this.S = typedArrayObtainStyledAttributes.getInt(index, this.S);
                        break;
                    case 2:
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.f1117m);
                        this.f1117m = resourceId;
                        if (resourceId == -1) {
                            this.f1117m = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 3:
                        this.f1119n = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1119n);
                        break;
                    case 4:
                        float f8 = typedArrayObtainStyledAttributes.getFloat(index, this.f1121o) % 360.0f;
                        this.f1121o = f8;
                        if (f8 < 0.0f) {
                            this.f1121o = (360.0f - f8) % 360.0f;
                        }
                        break;
                    case 5:
                        this.f1093a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1093a);
                        break;
                    case 6:
                        this.f1095b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f1095b);
                        break;
                    case 7:
                        this.f1097c = typedArrayObtainStyledAttributes.getFloat(index, this.f1097c);
                        break;
                    case 8:
                        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1099d);
                        this.f1099d = resourceId2;
                        if (resourceId2 == -1) {
                            this.f1099d = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 9:
                        int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1101e);
                        this.f1101e = resourceId3;
                        if (resourceId3 == -1) {
                            this.f1101e = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 10:
                        int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1103f);
                        this.f1103f = resourceId4;
                        if (resourceId4 == -1) {
                            this.f1103f = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 11:
                        int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1105g);
                        this.f1105g = resourceId5;
                        if (resourceId5 == -1) {
                            this.f1105g = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 12:
                        int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1107h);
                        this.f1107h = resourceId6;
                        if (resourceId6 == -1) {
                            this.f1107h = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 13:
                        int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1109i);
                        this.f1109i = resourceId7;
                        if (resourceId7 == -1) {
                            this.f1109i = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 14:
                        int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1111j);
                        this.f1111j = resourceId8;
                        if (resourceId8 == -1) {
                            this.f1111j = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 15:
                        int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1113k);
                        this.f1113k = resourceId9;
                        if (resourceId9 == -1) {
                            this.f1113k = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 16:
                        int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1115l);
                        this.f1115l = resourceId10;
                        if (resourceId10 == -1) {
                            this.f1115l = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 17:
                        int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1123p);
                        this.f1123p = resourceId11;
                        if (resourceId11 == -1) {
                            this.f1123p = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 18:
                        int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1124q);
                        this.f1124q = resourceId12;
                        if (resourceId12 == -1) {
                            this.f1124q = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 19:
                        int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1125r);
                        this.f1125r = resourceId13;
                        if (resourceId13 == -1) {
                            this.f1125r = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 20:
                        int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, this.f1126s);
                        this.f1126s = resourceId14;
                        if (resourceId14 == -1) {
                            this.f1126s = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        break;
                    case 21:
                        this.f1127t = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1127t);
                        break;
                    case 22:
                        this.f1128u = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1128u);
                        break;
                    case 23:
                        this.f1129v = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1129v);
                        break;
                    case 24:
                        this.f1130w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1130w);
                        break;
                    case 25:
                        this.f1131x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1131x);
                        break;
                    case 26:
                        this.f1132y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.f1132y);
                        break;
                    case 27:
                        this.T = typedArrayObtainStyledAttributes.getBoolean(index, this.T);
                        break;
                    case 28:
                        this.U = typedArrayObtainStyledAttributes.getBoolean(index, this.U);
                        break;
                    case 29:
                        this.f1133z = typedArrayObtainStyledAttributes.getFloat(index, this.f1133z);
                        break;
                    case 30:
                        this.A = typedArrayObtainStyledAttributes.getFloat(index, this.A);
                        break;
                    case 31:
                        int i11 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.I = i11;
                        if (i11 == 1) {
                            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        }
                        break;
                    case 32:
                        int i12 = typedArrayObtainStyledAttributes.getInt(index, 0);
                        this.J = i12;
                        if (i12 == 1) {
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
                        switch (i10) {
                            case 44:
                                String string = typedArrayObtainStyledAttributes.getString(index);
                                this.B = string;
                                this.C = Float.NaN;
                                this.D = -1;
                                if (string != null) {
                                    int length = string.length();
                                    int iIndexOf = this.B.indexOf(44);
                                    if (iIndexOf <= 0 || iIndexOf >= length - 1) {
                                        i8 = 0;
                                    } else {
                                        String strSubstring = this.B.substring(0, iIndexOf);
                                        if (strSubstring.equalsIgnoreCase("W")) {
                                            this.D = 0;
                                        } else if (strSubstring.equalsIgnoreCase("H")) {
                                            this.D = 1;
                                        }
                                        i8 = iIndexOf + 1;
                                    }
                                    int iIndexOf2 = this.B.indexOf(58);
                                    if (iIndexOf2 < 0 || iIndexOf2 >= length - 1) {
                                        String strSubstring2 = this.B.substring(i8);
                                        if (strSubstring2.length() > 0) {
                                            this.C = Float.parseFloat(strSubstring2);
                                        }
                                    } else {
                                        String strSubstring3 = this.B.substring(i8, iIndexOf2);
                                        String strSubstring4 = this.B.substring(iIndexOf2 + 1);
                                        if (strSubstring3.length() > 0 && strSubstring4.length() > 0) {
                                            try {
                                                float f9 = Float.parseFloat(strSubstring3);
                                                float f10 = Float.parseFloat(strSubstring4);
                                                if (f9 > 0.0f && f10 > 0.0f) {
                                                    if (this.D == 1) {
                                                        this.C = Math.abs(f10 / f9);
                                                    } else {
                                                        this.C = Math.abs(f9 / f10);
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
            return this.f1120n0;
        }

        public void c() {
            this.Z = false;
            this.W = true;
            this.X = true;
            int i8 = ((ViewGroup.MarginLayoutParams) this).width;
            if (i8 == -2 && this.T) {
                this.W = false;
                if (this.I == 0) {
                    this.I = 1;
                }
            }
            int i9 = ((ViewGroup.MarginLayoutParams) this).height;
            if (i9 == -2 && this.U) {
                this.X = false;
                if (this.J == 0) {
                    this.J = 1;
                }
            }
            if (i8 == 0 || i8 == -1) {
                this.W = false;
                if (i8 == 0 && this.I == 1) {
                    ((ViewGroup.MarginLayoutParams) this).width = -2;
                    this.T = true;
                }
            }
            if (i9 == 0 || i9 == -1) {
                this.X = false;
                if (i9 == 0 && this.J == 1) {
                    ((ViewGroup.MarginLayoutParams) this).height = -2;
                    this.U = true;
                }
            }
            if (this.f1097c == -1.0f && this.f1093a == -1 && this.f1095b == -1) {
                return;
            }
            this.Z = true;
            this.W = true;
            this.X = true;
            if (!(this.f1120n0 instanceof f)) {
                this.f1120n0 = new f();
            }
            ((f) this.f1120n0).S0(this.S);
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

        public b(int i8, int i9) {
            super(i8, i9);
            this.f1093a = -1;
            this.f1095b = -1;
            this.f1097c = -1.0f;
            this.f1099d = -1;
            this.f1101e = -1;
            this.f1103f = -1;
            this.f1105g = -1;
            this.f1107h = -1;
            this.f1109i = -1;
            this.f1111j = -1;
            this.f1113k = -1;
            this.f1115l = -1;
            this.f1117m = -1;
            this.f1119n = 0;
            this.f1121o = 0.0f;
            this.f1123p = -1;
            this.f1124q = -1;
            this.f1125r = -1;
            this.f1126s = -1;
            this.f1127t = -1;
            this.f1128u = -1;
            this.f1129v = -1;
            this.f1130w = -1;
            this.f1131x = -1;
            this.f1132y = -1;
            this.f1133z = 0.5f;
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
            this.f1094a0 = false;
            this.f1096b0 = false;
            this.f1098c0 = false;
            this.f1100d0 = -1;
            this.f1102e0 = -1;
            this.f1104f0 = -1;
            this.f1106g0 = -1;
            this.f1108h0 = -1;
            this.f1110i0 = -1;
            this.f1112j0 = 0.5f;
            this.f1120n0 = new ConstraintWidget();
            this.f1122o0 = false;
        }

        public b(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f1093a = -1;
            this.f1095b = -1;
            this.f1097c = -1.0f;
            this.f1099d = -1;
            this.f1101e = -1;
            this.f1103f = -1;
            this.f1105g = -1;
            this.f1107h = -1;
            this.f1109i = -1;
            this.f1111j = -1;
            this.f1113k = -1;
            this.f1115l = -1;
            this.f1117m = -1;
            this.f1119n = 0;
            this.f1121o = 0.0f;
            this.f1123p = -1;
            this.f1124q = -1;
            this.f1125r = -1;
            this.f1126s = -1;
            this.f1127t = -1;
            this.f1128u = -1;
            this.f1129v = -1;
            this.f1130w = -1;
            this.f1131x = -1;
            this.f1132y = -1;
            this.f1133z = 0.5f;
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
            this.f1094a0 = false;
            this.f1096b0 = false;
            this.f1098c0 = false;
            this.f1100d0 = -1;
            this.f1102e0 = -1;
            this.f1104f0 = -1;
            this.f1106g0 = -1;
            this.f1108h0 = -1;
            this.f1110i0 = -1;
            this.f1112j0 = 0.5f;
            this.f1120n0 = new ConstraintWidget();
            this.f1122o0 = false;
        }
    }
}
