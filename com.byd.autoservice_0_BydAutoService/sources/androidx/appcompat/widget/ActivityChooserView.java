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
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;
import n.p;
import o.e0;
import o.t;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f f413b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final g f414c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final View f415d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Drawable f416e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final FrameLayout f417f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ImageView f418g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final FrameLayout f419h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ImageView f420i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f421j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public l0.b f422k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final DataSetObserver f423l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ViewTreeObserver.OnGlobalLayoutListener f424m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ListPopupWindow f425n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public PopupWindow.OnDismissListener f426o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f427p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f428q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f429r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f430s;

    public static class InnerLayout extends LinearLayout {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final int[] f431b = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            e0 e0VarU = e0.u(context, attributeSet, f431b);
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
            ActivityChooserView.this.f413b.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.f413b.notifyDataSetInvalidated();
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
                l0.b bVar = ActivityChooserView.this.f422k;
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
            m0.c.D0(accessibilityNodeInfo).Y(true);
        }
    }

    public class d extends t {
        public d(View view) {
            super(view);
        }

        @Override // o.t
        public p b() {
            return ActivityChooserView.this.getListPopupWindow();
        }

        @Override // o.t
        public boolean c() {
            ActivityChooserView.this.c();
            return true;
        }

        @Override // o.t
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
        public o.c f437b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f438c = 4;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f439d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public boolean f440e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f441f;

        public f() {
        }

        public int a() {
            throw null;
        }

        public o.c b() {
            return this.f437b;
        }

        public ResolveInfo c() {
            throw null;
        }

        public int d() {
            throw null;
        }

        public boolean e() {
            return this.f439d;
        }

        public int f() {
            int i3 = this.f438c;
            this.f438c = Integer.MAX_VALUE;
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            int iMax = 0;
            View view = null;
            for (int i4 = 0; i4 < count; i4++) {
                view = getView(i4, view, null);
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                iMax = Math.max(iMax, view.getMeasuredWidth());
            }
            this.f438c = i3;
            return iMax;
        }

        public void g(o.c cVar) {
            if (ActivityChooserView.this.f413b.b() != null && ActivityChooserView.this.isShown()) {
                DataSetObserver dataSetObserver = ActivityChooserView.this.f423l;
                throw null;
            }
            if (cVar == null || !ActivityChooserView.this.isShown()) {
                notifyDataSetChanged();
            } else {
                DataSetObserver dataSetObserver2 = ActivityChooserView.this.f423l;
                throw null;
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            throw null;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i3) {
            int itemViewType = getItemViewType(i3);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
            if (this.f439d) {
                throw null;
            }
            throw null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i3) {
            return i3;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i3) {
            return (this.f441f && i3 == getCount() - 1) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i3, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i3);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                if (view != null && view.getId() == 1) {
                    return view;
                }
                View viewInflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R$layout.abc_activity_chooser_view_list_item, viewGroup, false);
                viewInflate.setId(1);
                ((TextView) viewInflate.findViewById(R$id.title)).setText(ActivityChooserView.this.getContext().getString(R$string.abc_activity_chooser_view_see_all));
                return viewInflate;
            }
            if (view == null || view.getId() != R$id.list_item) {
                view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(R$layout.abc_activity_chooser_view_list_item, viewGroup, false);
            }
            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ImageView imageView = (ImageView) view.findViewById(R$id.icon);
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i3);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(R$id.title)).setText(resolveInfo.loadLabel(packageManager));
            if (this.f439d && i3 == 0 && this.f440e) {
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

        public void h(int i3) {
            if (this.f438c != i3) {
                this.f438c = i3;
                notifyDataSetChanged();
            }
        }

        public void i(boolean z3, boolean z4) {
            if (this.f439d == z3 && this.f440e == z4) {
                return;
            }
            this.f439d = z3;
            this.f440e = z4;
            notifyDataSetChanged();
        }

        public void j(boolean z3) {
            if (this.f441f != z3) {
                this.f441f = z3;
                notifyDataSetChanged();
            }
        }
    }

    public class g implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        public g() {
        }

        public final void a() {
            PopupWindow.OnDismissListener onDismissListener = ActivityChooserView.this.f426o;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view == activityChooserView.f419h) {
                activityChooserView.a();
                ActivityChooserView.this.f413b.c();
                ActivityChooserView.this.f413b.b();
                throw null;
            }
            if (view != activityChooserView.f417f) {
                throw new IllegalArgumentException();
            }
            activityChooserView.f427p = false;
            activityChooserView.d(activityChooserView.f428q);
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            a();
            l0.b bVar = ActivityChooserView.this.f422k;
            if (bVar != null) {
                bVar.k(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i3, long j3) {
            int itemViewType = ((f) adapterView.getAdapter()).getItemViewType(i3);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                ActivityChooserView.this.d(Integer.MAX_VALUE);
                return;
            }
            ActivityChooserView.this.a();
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (!activityChooserView.f427p) {
                activityChooserView.f413b.e();
                ActivityChooserView.this.f413b.b();
                throw null;
            }
            if (i3 <= 0) {
                return;
            }
            activityChooserView.f413b.b();
            throw null;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.f419h) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.f413b.getCount() > 0) {
                ActivityChooserView activityChooserView2 = ActivityChooserView.this;
                activityChooserView2.f427p = true;
                activityChooserView2.d(activityChooserView2.f428q);
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
        viewTreeObserver.removeGlobalOnLayoutListener(this.f424m);
        return true;
    }

    public boolean b() {
        return getListPopupWindow().b();
    }

    public boolean c() {
        if (b() || !this.f429r) {
            return false;
        }
        this.f427p = false;
        d(this.f428q);
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
    public void d(int i3) {
        if (this.f413b.b() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.f424m);
        ?? r02 = this.f419h.getVisibility() == 0 ? 1 : 0;
        int iA = this.f413b.a();
        if (i3 == Integer.MAX_VALUE || iA <= i3 + r02) {
            this.f413b.j(false);
            this.f413b.h(i3);
        } else {
            this.f413b.j(true);
            this.f413b.h(i3 - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (listPopupWindow.b()) {
            return;
        }
        if (this.f427p || r02 == 0) {
            this.f413b.i(true, r02);
        } else {
            this.f413b.i(false, false);
        }
        listPopupWindow.F(Math.min(this.f413b.f(), this.f421j));
        listPopupWindow.g();
        l0.b bVar = this.f422k;
        if (bVar != null) {
            bVar.k(true);
        }
        listPopupWindow.l().setContentDescription(getContext().getString(R$string.abc_activitychooserview_choose_application));
        listPopupWindow.l().setSelector(new ColorDrawable(0));
    }

    public void e() {
        if (this.f413b.getCount() > 0) {
            this.f417f.setEnabled(true);
        } else {
            this.f417f.setEnabled(false);
        }
        int iA = this.f413b.a();
        int iD = this.f413b.d();
        if (iA == 1 || (iA > 1 && iD > 0)) {
            this.f419h.setVisibility(0);
            ResolveInfo resolveInfoC = this.f413b.c();
            PackageManager packageManager = getContext().getPackageManager();
            this.f420i.setImageDrawable(resolveInfoC.loadIcon(packageManager));
            if (this.f430s != 0) {
                this.f419h.setContentDescription(getContext().getString(this.f430s, resolveInfoC.loadLabel(packageManager)));
            }
        } else {
            this.f419h.setVisibility(8);
        }
        if (this.f419h.getVisibility() == 0) {
            this.f415d.setBackgroundDrawable(this.f416e);
        } else {
            this.f415d.setBackgroundDrawable(null);
        }
    }

    public o.c getDataModel() {
        return this.f413b.b();
    }

    public ListPopupWindow getListPopupWindow() {
        if (this.f425n == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.f425n = listPopupWindow;
            listPopupWindow.o(this.f413b);
            this.f425n.D(this);
            this.f425n.J(true);
            this.f425n.L(this.f414c);
            this.f425n.K(this.f414c);
        }
        return this.f425n;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f413b.b() != null) {
            throw null;
        }
        this.f429r = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f413b.b() != null) {
            throw null;
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.f424m);
        }
        if (b()) {
            a();
        }
        this.f429r = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z3, int i3, int i4, int i5, int i6) {
        this.f415d.layout(0, 0, i5 - i3, i6 - i4);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public void onMeasure(int i3, int i4) {
        View view = this.f415d;
        if (this.f419h.getVisibility() != 0) {
            i4 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i4), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        measureChild(view, i3, i4);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(o.c cVar) {
        this.f413b.g(cVar);
        if (b()) {
            a();
            c();
        }
    }

    public void setDefaultActionButtonContentDescription(int i3) {
        this.f430s = i3;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i3) {
        this.f418g.setContentDescription(getContext().getString(i3));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.f418g.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i3) {
        this.f428q = i3;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.f426o = onDismissListener;
    }

    public void setProvider(l0.b bVar) {
        this.f422k = bVar;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet, i3);
        this.f423l = new a();
        this.f424m = new b();
        this.f428q = 4;
        int[] iArr = R$styleable.ActivityChooserView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i3, 0);
        l0.t.h0(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i3, 0);
        this.f428q = typedArrayObtainStyledAttributes.getInt(R$styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(R$layout.abc_activity_chooser_view, (ViewGroup) this, true);
        g gVar = new g();
        this.f414c = gVar;
        View viewFindViewById = findViewById(R$id.activity_chooser_view_content);
        this.f415d = viewFindViewById;
        this.f416e = viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(R$id.default_activity_button);
        this.f419h = frameLayout;
        frameLayout.setOnClickListener(gVar);
        frameLayout.setOnLongClickListener(gVar);
        int i4 = R$id.image;
        this.f420i = (ImageView) frameLayout.findViewById(i4);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R$id.expand_activities_button);
        frameLayout2.setOnClickListener(gVar);
        frameLayout2.setAccessibilityDelegate(new c());
        frameLayout2.setOnTouchListener(new d(frameLayout2));
        this.f417f = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(i4);
        this.f418g = imageView;
        imageView.setImageDrawable(drawable);
        f fVar = new f();
        this.f413b = fVar;
        fVar.registerDataSetObserver(new e());
        Resources resources = context.getResources();
        this.f421j = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
    }
}
