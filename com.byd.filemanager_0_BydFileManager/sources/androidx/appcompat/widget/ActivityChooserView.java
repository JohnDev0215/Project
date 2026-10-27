package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final f f300b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f301c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final View f302d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Drawable f303e;
    final FrameLayout f;
    private final ImageView g;
    final FrameLayout h;
    private final ImageView i;
    private final int j;
    b.g.k.b k;
    final DataSetObserver l;
    private final ViewTreeObserver.OnGlobalLayoutListener m;
    private ListPopupWindow n;
    PopupWindow.OnDismissListener o;
    boolean p;
    int q;
    private boolean r;
    private int s;

    public static class InnerLayout extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private static final int[] f304b = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            e0 e0VarU = e0.u(context, attributeSet, f304b);
            setBackgroundDrawable(e0VarU.g(0));
            e0VarU.w();
        }
    }

    class a extends DataSetObserver {
        a() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.f300b.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.f300b.notifyDataSetInvalidated();
        }
    }

    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (ActivityChooserView.this.b()) {
                if (!ActivityChooserView.this.isShown()) {
                    ActivityChooserView.this.getListPopupWindow().dismiss();
                    return;
                }
                ActivityChooserView.this.getListPopupWindow().g();
                b.g.k.b bVar = ActivityChooserView.this.k;
                if (bVar != null) {
                    bVar.k(true);
                }
            }
        }
    }

    class c extends View.AccessibilityDelegate {
        c() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            b.g.k.c0.c.D0(accessibilityNodeInfo).Y(true);
        }
    }

    class d extends t {
        d(View view) {
            super(view);
        }

        @Override // androidx.appcompat.widget.t
        public androidx.appcompat.view.menu.p b() {
            return ActivityChooserView.this.getListPopupWindow();
        }

        @Override // androidx.appcompat.widget.t
        protected boolean c() {
            ActivityChooserView.this.c();
            return true;
        }

        @Override // androidx.appcompat.widget.t
        protected boolean d() {
            ActivityChooserView.this.a();
            return true;
        }
    }

    class e extends DataSetObserver {
        e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.e();
        }
    }

    private class f extends BaseAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private androidx.appcompat.widget.c f309b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f310c = 4;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f311d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f312e;
        private boolean f;

        f() {
        }

        public int a() {
            throw null;
        }

        public androidx.appcompat.widget.c b() {
            return this.f309b;
        }

        public ResolveInfo c() {
            throw null;
        }

        public int d() {
            throw null;
        }

        public boolean e() {
            return this.f311d;
        }

        public int f() {
            int i = this.f310c;
            this.f310c = Integer.MAX_VALUE;
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            int iMax = 0;
            View view = null;
            for (int i2 = 0; i2 < count; i2++) {
                view = getView(i2, view, null);
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                iMax = Math.max(iMax, view.getMeasuredWidth());
            }
            this.f310c = i;
            return iMax;
        }

        public void g(androidx.appcompat.widget.c cVar) {
            if (ActivityChooserView.this.f300b.b() != null && ActivityChooserView.this.isShown()) {
                DataSetObserver dataSetObserver = ActivityChooserView.this.l;
                throw null;
            }
            if (cVar == null || !ActivityChooserView.this.isShown()) {
                notifyDataSetChanged();
            } else {
                DataSetObserver dataSetObserver2 = ActivityChooserView.this.l;
                throw null;
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            throw null;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
            if (this.f311d) {
                throw null;
            }
            throw null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return (this.f && i == getCount() - 1) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                if (view != null && view.getId() == 1) {
                    return view;
                }
                View viewInflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(b.a.g.f, viewGroup, false);
                viewInflate.setId(1);
                ((TextView) viewInflate.findViewById(b.a.f.S)).setText(ActivityChooserView.this.getContext().getString(b.a.h.f1613b));
                return viewInflate;
            }
            if (view == null || view.getId() != b.a.f.x) {
                view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(b.a.g.f, viewGroup, false);
            }
            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ImageView imageView = (ImageView) view.findViewById(b.a.f.v);
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(b.a.f.S)).setText(resolveInfo.loadLabel(packageManager));
            if (this.f311d && i == 0 && this.f312e) {
                view.setActivated(true);
            } else {
                view.setActivated(false);
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getViewTypeCount() {
            return 3;
        }

        public void h(int i) {
            if (this.f310c != i) {
                this.f310c = i;
                notifyDataSetChanged();
            }
        }

        public void i(boolean z, boolean z2) {
            if (this.f311d == z && this.f312e == z2) {
                return;
            }
            this.f311d = z;
            this.f312e = z2;
            notifyDataSetChanged();
        }

        public void j(boolean z) {
            if (this.f != z) {
                this.f = z;
                notifyDataSetChanged();
            }
        }
    }

    private class g implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        g() {
        }

        private void a() {
            PopupWindow.OnDismissListener onDismissListener = ActivityChooserView.this.o;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view == activityChooserView.h) {
                activityChooserView.a();
                ActivityChooserView.this.f300b.c();
                ActivityChooserView.this.f300b.b();
                throw null;
            }
            if (view != activityChooserView.f) {
                throw new IllegalArgumentException();
            }
            activityChooserView.p = false;
            activityChooserView.d(activityChooserView.q);
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            a();
            b.g.k.b bVar = ActivityChooserView.this.k;
            if (bVar != null) {
                bVar.k(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int itemViewType = ((f) adapterView.getAdapter()).getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                ActivityChooserView.this.d(Integer.MAX_VALUE);
                return;
            }
            ActivityChooserView.this.a();
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (!activityChooserView.p) {
                activityChooserView.f300b.e();
                ActivityChooserView.this.f300b.b();
                throw null;
            }
            if (i <= 0) {
                return;
            }
            activityChooserView.f300b.b();
            throw null;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.h) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.f300b.getCount() > 0) {
                ActivityChooserView activityChooserView2 = ActivityChooserView.this;
                activityChooserView2.p = true;
                activityChooserView2.d(activityChooserView2.q);
            }
            return true;
        }
    }

    public ActivityChooserView(Context context) {
        this(context, null);
    }

    public boolean a() {
        if (!b()) {
            return true;
        }
        getListPopupWindow().dismiss();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (!viewTreeObserver.isAlive()) {
            return true;
        }
        viewTreeObserver.removeGlobalOnLayoutListener(this.m);
        return true;
    }

    public boolean b() {
        return getListPopupWindow().b();
    }

    public boolean c() {
        if (b() || !this.r) {
            return false;
        }
        this.p = false;
        d(this.q);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7, types: [boolean, int] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    void d(int i) {
        if (this.f300b.b() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.m);
        ?? r0 = this.h.getVisibility() == 0 ? 1 : 0;
        int iA = this.f300b.a();
        if (i == Integer.MAX_VALUE || iA <= i + r0) {
            this.f300b.j(false);
            this.f300b.h(i);
        } else {
            this.f300b.j(true);
            this.f300b.h(i - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (listPopupWindow.b()) {
            return;
        }
        if (this.p || r0 == 0) {
            this.f300b.i(true, r0);
        } else {
            this.f300b.i(false, false);
        }
        listPopupWindow.F(Math.min(this.f300b.f(), this.j));
        listPopupWindow.g();
        b.g.k.b bVar = this.k;
        if (bVar != null) {
            bVar.k(true);
        }
        listPopupWindow.l().setContentDescription(getContext().getString(b.a.h.f1614c));
        listPopupWindow.l().setSelector(new ColorDrawable(0));
    }

    void e() {
        if (this.f300b.getCount() > 0) {
            this.f.setEnabled(true);
        } else {
            this.f.setEnabled(false);
        }
        int iA = this.f300b.a();
        int iD = this.f300b.d();
        if (iA == 1 || (iA > 1 && iD > 0)) {
            this.h.setVisibility(0);
            ResolveInfo resolveInfoC = this.f300b.c();
            PackageManager packageManager = getContext().getPackageManager();
            this.i.setImageDrawable(resolveInfoC.loadIcon(packageManager));
            if (this.s != 0) {
                this.h.setContentDescription(getContext().getString(this.s, resolveInfoC.loadLabel(packageManager)));
            }
        } else {
            this.h.setVisibility(8);
        }
        if (this.h.getVisibility() == 0) {
            this.f302d.setBackgroundDrawable(this.f303e);
        } else {
            this.f302d.setBackgroundDrawable(null);
        }
    }

    public androidx.appcompat.widget.c getDataModel() {
        return this.f300b.b();
    }

    ListPopupWindow getListPopupWindow() {
        if (this.n == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.n = listPopupWindow;
            listPopupWindow.o(this.f300b);
            this.n.D(this);
            this.n.J(true);
            this.n.L(this.f301c);
            this.n.K(this.f301c);
        }
        return this.n;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f300b.b() != null) {
            throw null;
        }
        this.r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f300b.b() != null) {
            throw null;
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.m);
        }
        if (b()) {
            a();
        }
        this.r = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.f302d.layout(0, 0, i3 - i, i4 - i2);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        View view = this.f302d;
        if (this.h.getVisibility() != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(androidx.appcompat.widget.c cVar) {
        this.f300b.g(cVar);
        if (b()) {
            a();
            c();
        }
    }

    public void setDefaultActionButtonContentDescription(int i) {
        this.s = i;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i) {
        this.g.setContentDescription(getContext().getString(i));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.g.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i) {
        this.q = i;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.o = onDismissListener;
    }

    public void setProvider(b.g.k.b bVar) {
        this.k = bVar;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = new a();
        this.m = new b();
        this.q = 4;
        int[] iArr = b.a.j.E;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        b.g.k.t.h0(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        this.q = typedArrayObtainStyledAttributes.getInt(b.a.j.G, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(b.a.j.F);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(b.a.g.f1611e, (ViewGroup) this, true);
        g gVar = new g();
        this.f301c = gVar;
        View viewFindViewById = findViewById(b.a.f.j);
        this.f302d = viewFindViewById;
        this.f303e = viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(b.a.f.r);
        this.h = frameLayout;
        frameLayout.setOnClickListener(gVar);
        frameLayout.setOnLongClickListener(gVar);
        int i2 = b.a.f.w;
        this.i = (ImageView) frameLayout.findViewById(i2);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(b.a.f.t);
        frameLayout2.setOnClickListener(gVar);
        frameLayout2.setAccessibilityDelegate(new c());
        frameLayout2.setOnTouchListener(new d(frameLayout2));
        this.f = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(i2);
        this.g = imageView;
        imageView.setImageDrawable(drawable);
        f fVar = new f();
        this.f300b = fVar;
        fVar.registerDataSetObserver(new e());
        Resources resources = context.getResources();
        this.j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(b.a.d.f1595d));
    }
}
