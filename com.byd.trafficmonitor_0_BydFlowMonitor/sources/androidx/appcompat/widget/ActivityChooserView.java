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
import b.b.h;
import b.b.j;
import b.b.p.j.p;
import b.b.q.e0;
import b.b.q.t;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f378b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g f379c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f380d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Drawable f381e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final FrameLayout f382f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ImageView f383g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final FrameLayout f384h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ImageView f385i;
    public final int j;
    public b.h.m.b k;
    public final DataSetObserver l;
    public final ViewTreeObserver.OnGlobalLayoutListener m;
    public ListPopupWindow n;
    public PopupWindow.OnDismissListener o;
    public boolean p;
    public int q;
    public boolean r;
    public int s;

    public static class InnerLayout extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final int[] f386b = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            e0 e0VarU = e0.u(context, attributeSet, f386b);
            setBackgroundDrawable(e0VarU.g(0));
            e0VarU.w();
        }
    }

    public class a extends DataSetObserver {
        public a() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.f378b.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.f378b.notifyDataSetInvalidated();
        }
    }

    public class b implements ViewTreeObserver.OnGlobalLayoutListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (ActivityChooserView.this.b()) {
                if (!ActivityChooserView.this.isShown()) {
                    ActivityChooserView.this.getListPopupWindow().dismiss();
                    return;
                }
                ActivityChooserView.this.getListPopupWindow().g();
                b.h.m.b bVar = ActivityChooserView.this.k;
                if (bVar != null) {
                    bVar.k(true);
                }
            }
        }
    }

    public class c extends View.AccessibilityDelegate {
        public c() {
        }

        @Override // android.view.View.AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            b.h.m.c0.c.F0(accessibilityNodeInfo).Z(true);
        }
    }

    public class d extends t {
        public d(View view) {
            super(view);
        }

        @Override // b.b.q.t
        public p b() {
            return ActivityChooserView.this.getListPopupWindow();
        }

        @Override // b.b.q.t
        public boolean c() {
            ActivityChooserView.this.c();
            return true;
        }

        @Override // b.b.q.t
        public boolean d() {
            ActivityChooserView.this.a();
            return true;
        }
    }

    public class e extends DataSetObserver {
        public e() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.e();
        }
    }

    public class f extends BaseAdapter {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public b.b.q.c f391b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f392c = 4;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f393d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f394e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f395f;

        public f() {
        }

        public int a() {
            throw null;
        }

        public b.b.q.c b() {
            return this.f391b;
        }

        public ResolveInfo c() {
            throw null;
        }

        public int d() {
            throw null;
        }

        public boolean e() {
            return this.f393d;
        }

        public int f() {
            int i2 = this.f392c;
            this.f392c = Integer.MAX_VALUE;
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            int iMax = 0;
            View view = null;
            for (int i3 = 0; i3 < count; i3++) {
                view = getView(i3, view, null);
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                iMax = Math.max(iMax, view.getMeasuredWidth());
            }
            this.f392c = i2;
            return iMax;
        }

        public void g(b.b.q.c cVar) {
            if (ActivityChooserView.this.f378b.b() != null && ActivityChooserView.this.isShown()) {
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
        public Object getItem(int i2) {
            int itemViewType = getItemViewType(i2);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
            if (this.f393d) {
                throw null;
            }
            throw null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i2) {
            return i2;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i2) {
            return (this.f395f && i2 == getCount() - 1) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i2, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i2);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                if (view != null && view.getId() == 1) {
                    return view;
                }
                View viewInflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(b.b.g.abc_activity_chooser_view_list_item, viewGroup, false);
                viewInflate.setId(1);
                ((TextView) viewInflate.findViewById(b.b.f.title)).setText(ActivityChooserView.this.getContext().getString(h.abc_activity_chooser_view_see_all));
                return viewInflate;
            }
            if (view == null || view.getId() != b.b.f.list_item) {
                view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(b.b.g.abc_activity_chooser_view_list_item, viewGroup, false);
            }
            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ImageView imageView = (ImageView) view.findViewById(b.b.f.icon);
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i2);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(b.b.f.title)).setText(resolveInfo.loadLabel(packageManager));
            if (this.f393d && i2 == 0 && this.f394e) {
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

        public void h(int i2) {
            if (this.f392c != i2) {
                this.f392c = i2;
                notifyDataSetChanged();
            }
        }

        public void i(boolean z, boolean z2) {
            if (this.f393d == z && this.f394e == z2) {
                return;
            }
            this.f393d = z;
            this.f394e = z2;
            notifyDataSetChanged();
        }

        public void j(boolean z) {
            if (this.f395f != z) {
                this.f395f = z;
                notifyDataSetChanged();
            }
        }
    }

    public class g implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        public g() {
        }

        public final void a() {
            PopupWindow.OnDismissListener onDismissListener = ActivityChooserView.this.o;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view == activityChooserView.f384h) {
                activityChooserView.a();
                ActivityChooserView.this.f378b.c();
                ActivityChooserView.this.f378b.b();
                throw null;
            }
            if (view != activityChooserView.f382f) {
                throw new IllegalArgumentException();
            }
            activityChooserView.p = false;
            activityChooserView.d(activityChooserView.q);
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            a();
            b.h.m.b bVar = ActivityChooserView.this.k;
            if (bVar != null) {
                bVar.k(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i2, long j) {
            int itemViewType = ((f) adapterView.getAdapter()).getItemViewType(i2);
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
                activityChooserView.f378b.e();
                ActivityChooserView.this.f378b.b();
                throw null;
            }
            if (i2 <= 0) {
                return;
            }
            activityChooserView.f378b.b();
            throw null;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.f384h) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.f378b.getCount() > 0) {
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
    public void d(int i2) {
        if (this.f378b.b() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.m);
        ?? r0 = this.f384h.getVisibility() == 0 ? 1 : 0;
        int iA = this.f378b.a();
        if (i2 == Integer.MAX_VALUE || iA <= i2 + r0) {
            this.f378b.j(false);
            this.f378b.h(i2);
        } else {
            this.f378b.j(true);
            this.f378b.h(i2 - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (listPopupWindow.b()) {
            return;
        }
        if (this.p || r0 == 0) {
            this.f378b.i(true, r0);
        } else {
            this.f378b.i(false, false);
        }
        listPopupWindow.F(Math.min(this.f378b.f(), this.j));
        listPopupWindow.g();
        b.h.m.b bVar = this.k;
        if (bVar != null) {
            bVar.k(true);
        }
        listPopupWindow.l().setContentDescription(getContext().getString(h.abc_activitychooserview_choose_application));
        listPopupWindow.l().setSelector(new ColorDrawable(0));
    }

    public void e() {
        if (this.f378b.getCount() > 0) {
            this.f382f.setEnabled(true);
        } else {
            this.f382f.setEnabled(false);
        }
        int iA = this.f378b.a();
        int iD = this.f378b.d();
        if (iA == 1 || (iA > 1 && iD > 0)) {
            this.f384h.setVisibility(0);
            ResolveInfo resolveInfoC = this.f378b.c();
            PackageManager packageManager = getContext().getPackageManager();
            this.f385i.setImageDrawable(resolveInfoC.loadIcon(packageManager));
            if (this.s != 0) {
                this.f384h.setContentDescription(getContext().getString(this.s, resolveInfoC.loadLabel(packageManager)));
            }
        } else {
            this.f384h.setVisibility(8);
        }
        if (this.f384h.getVisibility() == 0) {
            this.f380d.setBackgroundDrawable(this.f381e);
        } else {
            this.f380d.setBackgroundDrawable(null);
        }
    }

    public b.b.q.c getDataModel() {
        return this.f378b.b();
    }

    public ListPopupWindow getListPopupWindow() {
        if (this.n == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.n = listPopupWindow;
            listPopupWindow.o(this.f378b);
            this.n.D(this);
            this.n.J(true);
            this.n.L(this.f379c);
            this.n.K(this.f379c);
        }
        return this.n;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f378b.b() != null) {
            throw null;
        }
        this.r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f378b.b() != null) {
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
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.f380d.layout(0, 0, i4 - i2, i5 - i3);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        View view = this.f380d;
        if (this.f384h.getVisibility() != 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        measureChild(view, i2, i3);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(b.b.q.c cVar) {
        this.f378b.g(cVar);
        if (b()) {
            a();
            c();
        }
    }

    public void setDefaultActionButtonContentDescription(int i2) {
        this.s = i2;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i2) {
        this.f383g.setContentDescription(getContext().getString(i2));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.f383g.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i2) {
        this.q = i2;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.o = onDismissListener;
    }

    public void setProvider(b.h.m.b bVar) {
        this.k = bVar;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.l = new a();
        this.m = new b();
        this.q = 4;
        int[] iArr = j.ActivityChooserView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        b.h.m.t.i0(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        this.q = typedArrayObtainStyledAttributes.getInt(j.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(j.ActivityChooserView_expandActivityOverflowButtonDrawable);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(b.b.g.abc_activity_chooser_view, (ViewGroup) this, true);
        g gVar = new g();
        this.f379c = gVar;
        View viewFindViewById = findViewById(b.b.f.activity_chooser_view_content);
        this.f380d = viewFindViewById;
        this.f381e = viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(b.b.f.default_activity_button);
        this.f384h = frameLayout;
        frameLayout.setOnClickListener(gVar);
        frameLayout.setOnLongClickListener(gVar);
        int i3 = b.b.f.image;
        this.f385i = (ImageView) frameLayout.findViewById(i3);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(b.b.f.expand_activities_button);
        frameLayout2.setOnClickListener(gVar);
        frameLayout2.setAccessibilityDelegate(new c());
        frameLayout2.setOnTouchListener(new d(frameLayout2));
        this.f382f = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(i3);
        this.f383g = imageView;
        imageView.setImageDrawable(drawable);
        f fVar = new f();
        this.f378b = fVar;
        fVar.registerDataSetObserver(new e());
        Resources resources = context.getResources();
        this.j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(b.b.d.abc_config_prefDialogWidth));
    }
}
