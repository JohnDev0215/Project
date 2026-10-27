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
import q.p;
import r.e0;
import r.t;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f514d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f515e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final View f516f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Drawable f517g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final FrameLayout f518h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ImageView f519i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final FrameLayout f520j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ImageView f521k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f522l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public o0.b f523m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final DataSetObserver f524n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final ViewTreeObserver.OnGlobalLayoutListener f525o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ListPopupWindow f526p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public PopupWindow.OnDismissListener f527q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f528r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f529s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f530t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f531u;

    public static class InnerLayout extends LinearLayout {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final int[] f532d = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            e0 e0VarU = e0.u(context, attributeSet, f532d);
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
            ActivityChooserView.this.f514d.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.f514d.notifyDataSetInvalidated();
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
                ActivityChooserView.this.getListPopupWindow().e();
                o0.b bVar = ActivityChooserView.this.f523m;
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
            p0.c.F0(accessibilityNodeInfo).Z(true);
        }
    }

    public class d extends t {
        public d(View view) {
            super(view);
        }

        @Override // r.t
        public p b() {
            return ActivityChooserView.this.getListPopupWindow();
        }

        @Override // r.t
        public boolean c() {
            ActivityChooserView.this.c();
            return true;
        }

        @Override // r.t
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

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public r.c f538d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f539e = 4;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public boolean f540f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public boolean f541g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public boolean f542h;

        public f() {
        }

        public int a() {
            throw null;
        }

        public r.c b() {
            return this.f538d;
        }

        public ResolveInfo c() {
            throw null;
        }

        public int d() {
            throw null;
        }

        public boolean e() {
            return this.f540f;
        }

        public int f() {
            int i8 = this.f539e;
            this.f539e = Integer.MAX_VALUE;
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int count = getCount();
            int iMax = 0;
            View view = null;
            for (int i9 = 0; i9 < count; i9++) {
                view = getView(i9, view, null);
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                iMax = Math.max(iMax, view.getMeasuredWidth());
            }
            this.f539e = i8;
            return iMax;
        }

        public void g(r.c cVar) {
            if (ActivityChooserView.this.f514d.b() != null && ActivityChooserView.this.isShown()) {
                DataSetObserver dataSetObserver = ActivityChooserView.this.f524n;
                throw null;
            }
            if (cVar == null || !ActivityChooserView.this.isShown()) {
                notifyDataSetChanged();
            } else {
                DataSetObserver dataSetObserver2 = ActivityChooserView.this.f524n;
                throw null;
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            throw null;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i8) {
            int itemViewType = getItemViewType(i8);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
            if (this.f540f) {
                throw null;
            }
            throw null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i8) {
            return i8;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i8) {
            return (this.f542h && i8 == getCount() - 1) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public View getView(int i8, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i8);
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
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i8);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(R$id.title)).setText(resolveInfo.loadLabel(packageManager));
            if (this.f540f && i8 == 0 && this.f541g) {
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

        public void h(int i8) {
            if (this.f539e != i8) {
                this.f539e = i8;
                notifyDataSetChanged();
            }
        }

        public void i(boolean z7, boolean z8) {
            if (this.f540f == z7 && this.f541g == z8) {
                return;
            }
            this.f540f = z7;
            this.f541g = z8;
            notifyDataSetChanged();
        }

        public void j(boolean z7) {
            if (this.f542h != z7) {
                this.f542h = z7;
                notifyDataSetChanged();
            }
        }
    }

    public class g implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        public g() {
        }

        public final void a() {
            PopupWindow.OnDismissListener onDismissListener = ActivityChooserView.this.f527q;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view == activityChooserView.f520j) {
                activityChooserView.a();
                ActivityChooserView.this.f514d.c();
                ActivityChooserView.this.f514d.b();
                throw null;
            }
            if (view != activityChooserView.f518h) {
                throw new IllegalArgumentException();
            }
            activityChooserView.f528r = false;
            activityChooserView.d(activityChooserView.f529s);
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            a();
            o0.b bVar = ActivityChooserView.this.f523m;
            if (bVar != null) {
                bVar.k(false);
            }
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i8, long j8) {
            int itemViewType = ((f) adapterView.getAdapter()).getItemViewType(i8);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                ActivityChooserView.this.d(Integer.MAX_VALUE);
                return;
            }
            ActivityChooserView.this.a();
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (!activityChooserView.f528r) {
                activityChooserView.f514d.e();
                ActivityChooserView.this.f514d.b();
                throw null;
            }
            if (i8 <= 0) {
                return;
            }
            activityChooserView.f514d.b();
            throw null;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.f520j) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.f514d.getCount() > 0) {
                ActivityChooserView activityChooserView2 = ActivityChooserView.this;
                activityChooserView2.f528r = true;
                activityChooserView2.d(activityChooserView2.f529s);
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
        viewTreeObserver.removeGlobalOnLayoutListener(this.f525o);
        return true;
    }

    public boolean b() {
        return getListPopupWindow().c();
    }

    public boolean c() {
        if (b() || !this.f530t) {
            return false;
        }
        this.f528r = false;
        d(this.f529s);
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
    public void d(int i8) {
        if (this.f514d.b() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.f525o);
        ?? r02 = this.f520j.getVisibility() == 0 ? 1 : 0;
        int iA = this.f514d.a();
        if (i8 == Integer.MAX_VALUE || iA <= i8 + r02) {
            this.f514d.j(false);
            this.f514d.h(i8);
        } else {
            this.f514d.j(true);
            this.f514d.h(i8 - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (listPopupWindow.c()) {
            return;
        }
        if (this.f528r || r02 == 0) {
            this.f514d.i(true, r02);
        } else {
            this.f514d.i(false, false);
        }
        listPopupWindow.F(Math.min(this.f514d.f(), this.f522l));
        listPopupWindow.e();
        o0.b bVar = this.f523m;
        if (bVar != null) {
            bVar.k(true);
        }
        listPopupWindow.h().setContentDescription(getContext().getString(R$string.abc_activitychooserview_choose_application));
        listPopupWindow.h().setSelector(new ColorDrawable(0));
    }

    public void e() {
        if (this.f514d.getCount() > 0) {
            this.f518h.setEnabled(true);
        } else {
            this.f518h.setEnabled(false);
        }
        int iA = this.f514d.a();
        int iD = this.f514d.d();
        if (iA == 1 || (iA > 1 && iD > 0)) {
            this.f520j.setVisibility(0);
            ResolveInfo resolveInfoC = this.f514d.c();
            PackageManager packageManager = getContext().getPackageManager();
            this.f521k.setImageDrawable(resolveInfoC.loadIcon(packageManager));
            if (this.f531u != 0) {
                this.f520j.setContentDescription(getContext().getString(this.f531u, resolveInfoC.loadLabel(packageManager)));
            }
        } else {
            this.f520j.setVisibility(8);
        }
        if (this.f520j.getVisibility() == 0) {
            this.f516f.setBackgroundDrawable(this.f517g);
        } else {
            this.f516f.setBackgroundDrawable(null);
        }
    }

    public r.c getDataModel() {
        return this.f514d.b();
    }

    public ListPopupWindow getListPopupWindow() {
        if (this.f526p == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.f526p = listPopupWindow;
            listPopupWindow.p(this.f514d);
            this.f526p.D(this);
            this.f526p.J(true);
            this.f526p.L(this.f515e);
            this.f526p.K(this.f515e);
        }
        return this.f526p;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f514d.b() != null) {
            throw null;
        }
        this.f530t = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f514d.b() != null) {
            throw null;
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.f525o);
        }
        if (b()) {
            a();
        }
        this.f530t = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z7, int i8, int i9, int i10, int i11) {
        this.f516f.layout(0, 0, i10 - i8, i11 - i9);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public void onMeasure(int i8, int i9) {
        View view = this.f516f;
        if (this.f520j.getVisibility() != 0) {
            i9 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i9), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        measureChild(view, i8, i9);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(r.c cVar) {
        this.f514d.g(cVar);
        if (b()) {
            a();
            c();
        }
    }

    public void setDefaultActionButtonContentDescription(int i8) {
        this.f531u = i8;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i8) {
        this.f519i.setContentDescription(getContext().getString(i8));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.f519i.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i8) {
        this.f529s = i8;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.f527q = onDismissListener;
    }

    public void setProvider(o0.b bVar) {
        this.f523m = bVar;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
        this.f524n = new a();
        this.f525o = new b();
        this.f529s = 4;
        int[] iArr = R$styleable.ActivityChooserView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i8, 0);
        o0.t.j0(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i8, 0);
        this.f529s = typedArrayObtainStyledAttributes.getInt(R$styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R$styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(R$layout.abc_activity_chooser_view, (ViewGroup) this, true);
        g gVar = new g();
        this.f515e = gVar;
        View viewFindViewById = findViewById(R$id.activity_chooser_view_content);
        this.f516f = viewFindViewById;
        this.f517g = viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(R$id.default_activity_button);
        this.f520j = frameLayout;
        frameLayout.setOnClickListener(gVar);
        frameLayout.setOnLongClickListener(gVar);
        int i9 = R$id.image;
        this.f521k = (ImageView) frameLayout.findViewById(i9);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R$id.expand_activities_button);
        frameLayout2.setOnClickListener(gVar);
        frameLayout2.setAccessibilityDelegate(new c());
        frameLayout2.setOnTouchListener(new d(frameLayout2));
        this.f518h = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(i9);
        this.f519i = imageView;
        imageView.setImageDrawable(drawable);
        f fVar = new f();
        this.f514d = fVar;
        fVar.registerDataSetObserver(new e());
        Resources resources = context.getResources();
        this.f522l = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
    }
}
