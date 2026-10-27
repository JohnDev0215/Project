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
    final f f796b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f797c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final View f798d;
    private final Drawable e;
    final FrameLayout f;
    private final ImageView g;
    final FrameLayout h;
    private final ImageView i;
    private final int j;
    c.g.k.b k;
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
        private static final int[] f799b = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            e0 e0VarU = e0.u(context, attributeSet, f799b);
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
            ActivityChooserView.this.f796b.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.f796b.notifyDataSetInvalidated();
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
                ActivityChooserView.this.getListPopupWindow().e();
                c.g.k.b bVar = ActivityChooserView.this.k;
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
            c.g.k.c0.c.D0(accessibilityNodeInfo).Y(true);
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
        private androidx.appcompat.widget.c f804b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f805c = 4;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f806d;
        private boolean e;
        private boolean f;

        f() {
        }

        public int a() {
            throw null;
        }

        public androidx.appcompat.widget.c b() {
            return this.f804b;
        }

        public ResolveInfo c() {
            throw null;
        }

        public int d() {
            throw null;
        }

        public boolean e() {
            return this.f806d;
        }

        public int f() {
            int i = this.f805c;
            this.f805c = Integer.MAX_VALUE;
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
            this.f805c = i;
            return iMax;
        }

        public void g(androidx.appcompat.widget.c cVar) {
            if (ActivityChooserView.this.f796b.b() != null && ActivityChooserView.this.isShown()) {
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
            if (this.f806d) {
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
                View viewInflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(c.a.g.f, viewGroup, false);
                viewInflate.setId(1);
                ((TextView) viewInflate.findViewById(c.a.f.S)).setText(ActivityChooserView.this.getContext().getString(c.a.h.f1991b));
                return viewInflate;
            }
            if (view == null || view.getId() != c.a.f.x) {
                view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(c.a.g.f, viewGroup, false);
            }
            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ImageView imageView = (ImageView) view.findViewById(c.a.f.v);
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(c.a.f.S)).setText(resolveInfo.loadLabel(packageManager));
            if (this.f806d && i == 0 && this.e) {
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
            if (this.f805c != i) {
                this.f805c = i;
                notifyDataSetChanged();
            }
        }

        public void i(boolean z, boolean z2) {
            if (this.f806d == z && this.e == z2) {
                return;
            }
            this.f806d = z;
            this.e = z2;
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
                ActivityChooserView.this.f796b.c();
                ActivityChooserView.this.f796b.b();
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
            c.g.k.b bVar = ActivityChooserView.this.k;
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
                activityChooserView.f796b.e();
                ActivityChooserView.this.f796b.b();
                throw null;
            }
            if (i <= 0) {
                return;
            }
            activityChooserView.f796b.b();
            throw null;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.h) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.f796b.getCount() > 0) {
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
        return getListPopupWindow().c();
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
        if (this.f796b.b() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.m);
        ?? r0 = this.h.getVisibility() == 0 ? 1 : 0;
        int iA = this.f796b.a();
        if (i == Integer.MAX_VALUE || iA <= i + r0) {
            this.f796b.j(false);
            this.f796b.h(i);
        } else {
            this.f796b.j(true);
            this.f796b.h(i - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (listPopupWindow.c()) {
            return;
        }
        if (this.p || r0 == 0) {
            this.f796b.i(true, r0);
        } else {
            this.f796b.i(false, false);
        }
        listPopupWindow.F(Math.min(this.f796b.f(), this.j));
        listPopupWindow.e();
        c.g.k.b bVar = this.k;
        if (bVar != null) {
            bVar.k(true);
        }
        listPopupWindow.h().setContentDescription(getContext().getString(c.a.h.f1992c));
        listPopupWindow.h().setSelector(new ColorDrawable(0));
    }

    void e() {
        if (this.f796b.getCount() > 0) {
            this.f.setEnabled(true);
        } else {
            this.f.setEnabled(false);
        }
        int iA = this.f796b.a();
        int iD = this.f796b.d();
        if (iA == 1 || (iA > 1 && iD > 0)) {
            this.h.setVisibility(0);
            ResolveInfo resolveInfoC = this.f796b.c();
            PackageManager packageManager = getContext().getPackageManager();
            this.i.setImageDrawable(resolveInfoC.loadIcon(packageManager));
            if (this.s != 0) {
                this.h.setContentDescription(getContext().getString(this.s, resolveInfoC.loadLabel(packageManager)));
            }
        } else {
            this.h.setVisibility(8);
        }
        if (this.h.getVisibility() == 0) {
            this.f798d.setBackgroundDrawable(this.e);
        } else {
            this.f798d.setBackgroundDrawable(null);
        }
    }

    public androidx.appcompat.widget.c getDataModel() {
        return this.f796b.b();
    }

    ListPopupWindow getListPopupWindow() {
        if (this.n == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.n = listPopupWindow;
            listPopupWindow.p(this.f796b);
            this.n.D(this);
            this.n.J(true);
            this.n.L(this.f797c);
            this.n.K(this.f797c);
        }
        return this.n;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f796b.b() != null) {
            throw null;
        }
        this.r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f796b.b() != null) {
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
        this.f798d.layout(0, 0, i3 - i, i4 - i2);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        View view = this.f798d;
        if (this.h.getVisibility() != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(androidx.appcompat.widget.c cVar) {
        this.f796b.g(cVar);
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

    public void setProvider(c.g.k.b bVar) {
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
        int[] iArr = c.a.j.E;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        c.g.k.t.h0(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        this.q = typedArrayObtainStyledAttributes.getInt(c.a.j.G, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(c.a.j.F);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(c.a.g.e, (ViewGroup) this, true);
        g gVar = new g();
        this.f797c = gVar;
        View viewFindViewById = findViewById(c.a.f.j);
        this.f798d = viewFindViewById;
        this.e = viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(c.a.f.r);
        this.h = frameLayout;
        frameLayout.setOnClickListener(gVar);
        frameLayout.setOnLongClickListener(gVar);
        int i2 = c.a.f.w;
        this.i = (ImageView) frameLayout.findViewById(i2);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(c.a.f.t);
        frameLayout2.setOnClickListener(gVar);
        frameLayout2.setAccessibilityDelegate(new c());
        frameLayout2.setOnTouchListener(new d(frameLayout2));
        this.f = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(i2);
        this.g = imageView;
        imageView.setImageDrawable(drawable);
        f fVar = new f();
        this.f796b = fVar;
        fVar.registerDataSetObserver(new e());
        Resources resources = context.getResources();
        this.j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(c.a.d.f1977d));
    }
}
