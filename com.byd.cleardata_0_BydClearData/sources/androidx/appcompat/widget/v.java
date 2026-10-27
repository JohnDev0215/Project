package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.app.b;

/* JADX INFO: loaded from: classes.dex */
public class v extends Spinner implements a.g.k.s {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int[] f1028b = {R.attr.spinnerMode};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final androidx.appcompat.widget.e f1029c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Context f1030d;
    private g0 e;
    private SpinnerAdapter f;
    private final boolean g;
    private g h;
    int i;
    final Rect j;

    class a extends g0 {
        final /* synthetic */ e k;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(View view, e eVar) {
            super(view);
            this.k = eVar;
        }

        @Override // androidx.appcompat.widget.g0
        public androidx.appcompat.view.menu.p b() {
            return this.k;
        }

        @Override // androidx.appcompat.widget.g0
        @SuppressLint({"SyntheticAccessor"})
        public boolean c() {
            if (v.this.getInternalPopup().b()) {
                return true;
            }
            v.this.b();
            return true;
        }
    }

    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!v.this.getInternalPopup().b()) {
                v.this.b();
            }
            ViewTreeObserver viewTreeObserver = v.this.getViewTreeObserver();
            if (viewTreeObserver != null) {
                if (Build.VERSION.SDK_INT >= 16) {
                    viewTreeObserver.removeOnGlobalLayoutListener(this);
                } else {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
            }
        }
    }

    class c implements g, DialogInterface.OnClickListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        androidx.appcompat.app.b f1032b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private ListAdapter f1033c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private CharSequence f1034d;

        c() {
        }

        @Override // androidx.appcompat.widget.v.g
        public boolean b() {
            androidx.appcompat.app.b bVar = this.f1032b;
            if (bVar != null) {
                return bVar.isShowing();
            }
            return false;
        }

        @Override // androidx.appcompat.widget.v.g
        public void c(Drawable drawable) {
            Log.e("AppCompatSpinner", "Cannot set popup background for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.v.g
        public void d(int i) {
            Log.e("AppCompatSpinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.v.g
        public void dismiss() {
            androidx.appcompat.app.b bVar = this.f1032b;
            if (bVar != null) {
                bVar.dismiss();
                this.f1032b = null;
            }
        }

        @Override // androidx.appcompat.widget.v.g
        public int e() {
            return 0;
        }

        @Override // androidx.appcompat.widget.v.g
        public void f(int i, int i2) {
            if (this.f1033c == null) {
                return;
            }
            b.a aVar = new b.a(v.this.getPopupContext());
            CharSequence charSequence = this.f1034d;
            if (charSequence != null) {
                aVar.h(charSequence);
            }
            androidx.appcompat.app.b bVarA = aVar.g(this.f1033c, v.this.getSelectedItemPosition(), this).a();
            this.f1032b = bVarA;
            ListView listViewE = bVarA.e();
            if (Build.VERSION.SDK_INT >= 17) {
                listViewE.setTextDirection(i);
                listViewE.setTextAlignment(i2);
            }
            this.f1032b.show();
        }

        @Override // androidx.appcompat.widget.v.g
        public int h() {
            return 0;
        }

        @Override // androidx.appcompat.widget.v.g
        public Drawable j() {
            return null;
        }

        @Override // androidx.appcompat.widget.v.g
        public CharSequence k() {
            return this.f1034d;
        }

        @Override // androidx.appcompat.widget.v.g
        public void m(CharSequence charSequence) {
            this.f1034d = charSequence;
        }

        @Override // androidx.appcompat.widget.v.g
        public void n(int i) {
            Log.e("AppCompatSpinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
        }

        @Override // androidx.appcompat.widget.v.g
        public void o(ListAdapter listAdapter) {
            this.f1033c = listAdapter;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            v.this.setSelection(i);
            if (v.this.getOnItemClickListener() != null) {
                v.this.performItemClick(null, i, this.f1033c.getItemId(i));
            }
            dismiss();
        }

        @Override // androidx.appcompat.widget.v.g
        public void p(int i) {
            Log.e("AppCompatSpinner", "Cannot set horizontal (original) offset for MODE_DIALOG, ignoring");
        }
    }

    private static class d implements ListAdapter, SpinnerAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private SpinnerAdapter f1035b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private ListAdapter f1036c;

        public d(SpinnerAdapter spinnerAdapter, Resources.Theme theme) {
            this.f1035b = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.f1036c = (ListAdapter) spinnerAdapter;
            }
            if (theme != null) {
                if (Build.VERSION.SDK_INT >= 23 && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                    ThemedSpinnerAdapter themedSpinnerAdapter = (ThemedSpinnerAdapter) spinnerAdapter;
                    if (themedSpinnerAdapter.getDropDownViewTheme() != theme) {
                        themedSpinnerAdapter.setDropDownViewTheme(theme);
                        return;
                    }
                    return;
                }
                if (spinnerAdapter instanceof r0) {
                    r0 r0Var = (r0) spinnerAdapter;
                    if (r0Var.getDropDownViewTheme() == null) {
                        r0Var.setDropDownViewTheme(theme);
                    }
                }
            }
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.f1036c;
            if (listAdapter != null) {
                return listAdapter.areAllItemsEnabled();
            }
            return true;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.f1035b;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.f1035b;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.f1035b;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.f1035b;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i) {
            return 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            return getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.f1035b;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.f1036c;
            if (listAdapter != null) {
                return listAdapter.isEnabled(i);
            }
            return true;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f1035b;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.f1035b;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    class e extends i0 implements g {
        private CharSequence K;
        ListAdapter L;
        private final Rect M;
        private int N;

        class a implements AdapterView.OnItemClickListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ v f1037b;

            a(v vVar) {
                this.f1037b = vVar;
            }

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                v.this.setSelection(i);
                if (v.this.getOnItemClickListener() != null) {
                    e eVar = e.this;
                    v.this.performItemClick(view, i, eVar.L.getItemId(i));
                }
                e.this.dismiss();
            }
        }

        class b implements ViewTreeObserver.OnGlobalLayoutListener {
            b() {
            }

            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                e eVar = e.this;
                if (!eVar.U(v.this)) {
                    e.this.dismiss();
                } else {
                    e.this.S();
                    e.super.g();
                }
            }
        }

        class c implements PopupWindow.OnDismissListener {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ ViewTreeObserver.OnGlobalLayoutListener f1040b;

            c(ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
                this.f1040b = onGlobalLayoutListener;
            }

            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                ViewTreeObserver viewTreeObserver = v.this.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this.f1040b);
                }
            }
        }

        public e(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.M = new Rect();
            D(v.this);
            J(true);
            O(0);
            L(new a(v.this));
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x008d  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x009a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void S() {
            /*
                r8 = this;
                android.graphics.drawable.Drawable r0 = r8.j()
                r1 = 0
                if (r0 == 0) goto L26
                androidx.appcompat.widget.v r1 = androidx.appcompat.widget.v.this
                android.graphics.Rect r1 = r1.j
                r0.getPadding(r1)
                androidx.appcompat.widget.v r0 = androidx.appcompat.widget.v.this
                boolean r0 = androidx.appcompat.widget.b1.b(r0)
                if (r0 == 0) goto L1d
                androidx.appcompat.widget.v r0 = androidx.appcompat.widget.v.this
                android.graphics.Rect r0 = r0.j
                int r0 = r0.right
                goto L24
            L1d:
                androidx.appcompat.widget.v r0 = androidx.appcompat.widget.v.this
                android.graphics.Rect r0 = r0.j
                int r0 = r0.left
                int r0 = -r0
            L24:
                r1 = r0
                goto L2e
            L26:
                androidx.appcompat.widget.v r0 = androidx.appcompat.widget.v.this
                android.graphics.Rect r0 = r0.j
                r0.right = r1
                r0.left = r1
            L2e:
                androidx.appcompat.widget.v r0 = androidx.appcompat.widget.v.this
                int r0 = r0.getPaddingLeft()
                androidx.appcompat.widget.v r2 = androidx.appcompat.widget.v.this
                int r2 = r2.getPaddingRight()
                androidx.appcompat.widget.v r3 = androidx.appcompat.widget.v.this
                int r3 = r3.getWidth()
                androidx.appcompat.widget.v r4 = androidx.appcompat.widget.v.this
                int r5 = r4.i
                r6 = -2
                if (r5 != r6) goto L78
                android.widget.ListAdapter r5 = r8.L
                android.widget.SpinnerAdapter r5 = (android.widget.SpinnerAdapter) r5
                android.graphics.drawable.Drawable r6 = r8.j()
                int r4 = r4.a(r5, r6)
                androidx.appcompat.widget.v r5 = androidx.appcompat.widget.v.this
                android.content.Context r5 = r5.getContext()
                android.content.res.Resources r5 = r5.getResources()
                android.util.DisplayMetrics r5 = r5.getDisplayMetrics()
                int r5 = r5.widthPixels
                androidx.appcompat.widget.v r6 = androidx.appcompat.widget.v.this
                android.graphics.Rect r6 = r6.j
                int r7 = r6.left
                int r5 = r5 - r7
                int r6 = r6.right
                int r5 = r5 - r6
                if (r4 <= r5) goto L70
                r4 = r5
            L70:
                int r5 = r3 - r0
                int r5 = r5 - r2
                int r4 = java.lang.Math.max(r4, r5)
                goto L7e
            L78:
                r4 = -1
                if (r5 != r4) goto L82
                int r4 = r3 - r0
                int r4 = r4 - r2
            L7e:
                r8.F(r4)
                goto L85
            L82:
                r8.F(r5)
            L85:
                androidx.appcompat.widget.v r4 = androidx.appcompat.widget.v.this
                boolean r4 = androidx.appcompat.widget.b1.b(r4)
                if (r4 == 0) goto L9a
                int r3 = r3 - r2
                int r0 = r8.z()
                int r3 = r3 - r0
                int r0 = r8.T()
                int r3 = r3 - r0
                int r1 = r1 + r3
                goto La0
            L9a:
                int r2 = r8.T()
                int r0 = r0 + r2
                int r1 = r1 + r0
            La0:
                r8.d(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.v.e.S():void");
        }

        public int T() {
            return this.N;
        }

        boolean U(View view) {
            return a.g.k.t.M(view) && view.getGlobalVisibleRect(this.M);
        }

        @Override // androidx.appcompat.widget.v.g
        public void f(int i, int i2) {
            ViewTreeObserver viewTreeObserver;
            boolean zB = b();
            S();
            I(2);
            super.g();
            ListView listViewL = l();
            listViewL.setChoiceMode(1);
            if (Build.VERSION.SDK_INT >= 17) {
                listViewL.setTextDirection(i);
                listViewL.setTextAlignment(i2);
            }
            P(v.this.getSelectedItemPosition());
            if (zB || (viewTreeObserver = v.this.getViewTreeObserver()) == null) {
                return;
            }
            b bVar = new b();
            viewTreeObserver.addOnGlobalLayoutListener(bVar);
            K(new c(bVar));
        }

        @Override // androidx.appcompat.widget.v.g
        public CharSequence k() {
            return this.K;
        }

        @Override // androidx.appcompat.widget.v.g
        public void m(CharSequence charSequence) {
            this.K = charSequence;
        }

        @Override // androidx.appcompat.widget.i0, androidx.appcompat.widget.v.g
        public void o(ListAdapter listAdapter) {
            super.o(listAdapter);
            this.L = listAdapter;
        }

        @Override // androidx.appcompat.widget.v.g
        public void p(int i) {
            this.N = i;
        }
    }

    static class f extends View.BaseSavedState {
        public static final Parcelable.Creator<f> CREATOR = new a();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1042b;

        class a implements Parcelable.Creator<f> {
            a() {
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public f createFromParcel(Parcel parcel) {
                return new f(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public f[] newArray(int i) {
                return new f[i];
            }
        }

        f(Parcel parcel) {
            super(parcel);
            this.f1042b = parcel.readByte() != 0;
        }

        f(Parcelable parcelable) {
            super(parcelable);
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.f1042b ? (byte) 1 : (byte) 0);
        }
    }

    interface g {
        boolean b();

        void c(Drawable drawable);

        void d(int i);

        void dismiss();

        int e();

        void f(int i, int i2);

        int h();

        Drawable j();

        CharSequence k();

        void m(CharSequence charSequence);

        void n(int i);

        void o(ListAdapter listAdapter);

        void p(int i);
    }

    public v(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.a.a.J);
    }

    public v(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, -1);
    }

    public v(Context context, AttributeSet attributeSet, int i, int i2) {
        this(context, attributeSet, i, i2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v7, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [android.content.res.TypedArray] */
    /* JADX WARN: Type inference failed for: r6v0, types: [android.view.View, android.widget.Spinner, androidx.appcompat.widget.v] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public v(android.content.Context r7, android.util.AttributeSet r8, int r9, int r10, android.content.res.Resources.Theme r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.v.<init>(android.content.Context, android.util.AttributeSet, int, int, android.content.res.Resources$Theme):void");
    }

    int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i) {
                view = null;
                i = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        drawable.getPadding(this.j);
        Rect rect = this.j;
        return iMax2 + rect.left + rect.right;
    }

    void b() {
        if (Build.VERSION.SDK_INT >= 17) {
            this.h.f(getTextDirection(), getTextAlignment());
        } else {
            this.h.f(-1, -1);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        androidx.appcompat.widget.e eVar = this.f1029c;
        if (eVar != null) {
            eVar.b();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        g gVar = this.h;
        if (gVar != null) {
            return gVar.e();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownHorizontalOffset();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        g gVar = this.h;
        if (gVar != null) {
            return gVar.h();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownVerticalOffset();
        }
        return 0;
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        if (this.h != null) {
            return this.i;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getDropDownWidth();
        }
        return 0;
    }

    final g getInternalPopup() {
        return this.h;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        g gVar = this.h;
        if (gVar != null) {
            return gVar.j();
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return super.getPopupBackground();
        }
        return null;
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f1030d;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        g gVar = this.h;
        return gVar != null ? gVar.k() : super.getPrompt();
    }

    @Override // a.g.k.s
    public ColorStateList getSupportBackgroundTintList() {
        androidx.appcompat.widget.e eVar = this.f1029c;
        if (eVar != null) {
            return eVar.c();
        }
        return null;
    }

    @Override // a.g.k.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        androidx.appcompat.widget.e eVar = this.f1029c;
        if (eVar != null) {
            return eVar.d();
        }
        return null;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        g gVar = this.h;
        if (gVar == null || !gVar.b()) {
            return;
        }
        this.h.dismiss();
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.h == null || View.MeasureSpec.getMode(i) != Integer.MIN_VALUE) {
            return;
        }
        setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        f fVar = (f) parcelable;
        super.onRestoreInstanceState(fVar.getSuperState());
        if (!fVar.f1042b || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new b());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public Parcelable onSaveInstanceState() {
        f fVar = new f(super.onSaveInstanceState());
        g gVar = this.h;
        fVar.f1042b = gVar != null && gVar.b();
        return fVar;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        g0 g0Var = this.e;
        if (g0Var == null || !g0Var.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public boolean performClick() {
        g gVar = this.h;
        if (gVar == null) {
            return super.performClick();
        }
        if (gVar.b()) {
            return true;
        }
        b();
        return true;
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.g) {
            this.f = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.h != null) {
            Context context = this.f1030d;
            if (context == null) {
                context = getContext();
            }
            this.h.o(new d(spinnerAdapter, context.getTheme()));
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        androidx.appcompat.widget.e eVar = this.f1029c;
        if (eVar != null) {
            eVar.f(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        androidx.appcompat.widget.e eVar = this.f1029c;
        if (eVar != null) {
            eVar.g(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i) {
        g gVar = this.h;
        if (gVar != null) {
            gVar.p(i);
            this.h.d(i);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownHorizontalOffset(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i) {
        g gVar = this.h;
        if (gVar != null) {
            gVar.n(i);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownVerticalOffset(i);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i) {
        if (this.h != null) {
            this.i = i;
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setDropDownWidth(i);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        g gVar = this.h;
        if (gVar != null) {
            gVar.c(drawable);
        } else if (Build.VERSION.SDK_INT >= 16) {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(a.a.k.a.a.d(getPopupContext(), i));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        g gVar = this.h;
        if (gVar != null) {
            gVar.m(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @Override // a.g.k.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        androidx.appcompat.widget.e eVar = this.f1029c;
        if (eVar != null) {
            eVar.i(colorStateList);
        }
    }

    @Override // a.g.k.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        androidx.appcompat.widget.e eVar = this.f1029c;
        if (eVar != null) {
            eVar.j(mode);
        }
    }
}
