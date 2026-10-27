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
import b.f.m.s;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup {
    public final ImageView E3;
    public final FrameLayout F3;
    public final ImageView G3;
    public final int H3;
    public b.f.m.b I3;
    public final DataSetObserver J3;
    public final ViewTreeObserver.OnGlobalLayoutListener K3;
    public ListPopupWindow L3;
    public PopupWindow.OnDismissListener M3;
    public boolean N3;
    public int O3;
    public boolean P3;
    public int Q3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final f f176c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final g f177d;
    public final View s;
    public final Drawable x;
    public final FrameLayout y;

    public static class InnerLayout extends LinearLayout {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final int[] f178c = {R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            e0 e0VarT = e0.t(context, attributeSet, f178c);
            setBackgroundDrawable(e0VarT.f(0));
            e0VarT.v();
        }
    }

    public class a extends DataSetObserver {
        public a() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            super.onChanged();
            ActivityChooserView.this.f176c.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            super.onInvalidated();
            ActivityChooserView.this.f176c.notifyDataSetInvalidated();
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
                ActivityChooserView.this.getListPopupWindow().f();
                b.f.m.b bVar = ActivityChooserView.this.I3;
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
            b.f.m.b0.c.m0(accessibilityNodeInfo).S(true);
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

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public b.b.q.c f183c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f184d = 4;
        public boolean s;
        public boolean x;
        public boolean y;

        public f() {
        }

        public int a() {
            throw null;
        }

        public b.b.q.c b() {
            return this.f183c;
        }

        public ResolveInfo c() {
            throw null;
        }

        public int d() {
            throw null;
        }

        public boolean e() {
            return this.s;
        }

        public int f() {
            int i2 = this.f184d;
            this.f184d = Integer.MAX_VALUE;
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
            this.f184d = i2;
            return iMax;
        }

        public void g(b.b.q.c cVar) {
            if (ActivityChooserView.this.f176c.b() != null && ActivityChooserView.this.isShown()) {
                DataSetObserver dataSetObserver = ActivityChooserView.this.J3;
                throw null;
            }
            if (cVar == null || !ActivityChooserView.this.isShown()) {
                notifyDataSetChanged();
            } else {
                DataSetObserver dataSetObserver2 = ActivityChooserView.this.J3;
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
            if (this.s) {
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
            return (this.y && i2 == getCount() - 1) ? 1 : 0;
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
                View viewInflate = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(b.b.g.f754f, viewGroup, false);
                viewInflate.setId(1);
                ((TextView) viewInflate.findViewById(b.b.f.S)).setText(ActivityChooserView.this.getContext().getString(h.f759b));
                return viewInflate;
            }
            if (view == null || view.getId() != b.b.f.x) {
                view = LayoutInflater.from(ActivityChooserView.this.getContext()).inflate(b.b.g.f754f, viewGroup, false);
            }
            PackageManager packageManager = ActivityChooserView.this.getContext().getPackageManager();
            ImageView imageView = (ImageView) view.findViewById(b.b.f.v);
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i2);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(b.b.f.S)).setText(resolveInfo.loadLabel(packageManager));
            if (this.s && i2 == 0 && this.x) {
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
            if (this.f184d != i2) {
                this.f184d = i2;
                notifyDataSetChanged();
            }
        }

        public void i(boolean z, boolean z2) {
            if (this.s == z && this.x == z2) {
                return;
            }
            this.s = z;
            this.x = z2;
            notifyDataSetChanged();
        }

        public void j(boolean z) {
            if (this.y != z) {
                this.y = z;
                notifyDataSetChanged();
            }
        }
    }

    public class g implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        public g() {
        }

        public final void a() {
            PopupWindow.OnDismissListener onDismissListener = ActivityChooserView.this.M3;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view == activityChooserView.F3) {
                activityChooserView.a();
                ActivityChooserView.this.f176c.c();
                ActivityChooserView.this.f176c.b();
                throw null;
            }
            if (view != activityChooserView.y) {
                throw new IllegalArgumentException();
            }
            activityChooserView.N3 = false;
            activityChooserView.d(activityChooserView.O3);
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            a();
            b.f.m.b bVar = ActivityChooserView.this.I3;
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
            if (!activityChooserView.N3) {
                activityChooserView.f176c.e();
                ActivityChooserView.this.f176c.b();
                throw null;
            }
            if (i2 <= 0) {
                return;
            }
            activityChooserView.f176c.b();
            throw null;
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.F3) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.f176c.getCount() > 0) {
                ActivityChooserView activityChooserView2 = ActivityChooserView.this;
                activityChooserView2.N3 = true;
                activityChooserView2.d(activityChooserView2.O3);
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
        viewTreeObserver.removeGlobalOnLayoutListener(this.K3);
        return true;
    }

    public boolean b() {
        return getListPopupWindow().b();
    }

    public boolean c() {
        if (b() || !this.P3) {
            return false;
        }
        this.N3 = false;
        d(this.O3);
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
        if (this.f176c.b() == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.K3);
        ?? r0 = this.F3.getVisibility() == 0 ? 1 : 0;
        int iA = this.f176c.a();
        if (i2 == Integer.MAX_VALUE || iA <= i2 + r0) {
            this.f176c.j(false);
            this.f176c.h(i2);
        } else {
            this.f176c.j(true);
            this.f176c.h(i2 - 1);
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (listPopupWindow.b()) {
            return;
        }
        if (this.N3 || r0 == 0) {
            this.f176c.i(true, r0);
        } else {
            this.f176c.i(false, false);
        }
        listPopupWindow.B(Math.min(this.f176c.f(), this.H3));
        listPopupWindow.f();
        b.f.m.b bVar = this.I3;
        if (bVar != null) {
            bVar.k(true);
        }
        listPopupWindow.k().setContentDescription(getContext().getString(h.f760c));
        listPopupWindow.k().setSelector(new ColorDrawable(0));
    }

    public void e() {
        if (this.f176c.getCount() > 0) {
            this.y.setEnabled(true);
        } else {
            this.y.setEnabled(false);
        }
        int iA = this.f176c.a();
        int iD = this.f176c.d();
        if (iA == 1 || (iA > 1 && iD > 0)) {
            this.F3.setVisibility(0);
            ResolveInfo resolveInfoC = this.f176c.c();
            PackageManager packageManager = getContext().getPackageManager();
            this.G3.setImageDrawable(resolveInfoC.loadIcon(packageManager));
            if (this.Q3 != 0) {
                this.F3.setContentDescription(getContext().getString(this.Q3, resolveInfoC.loadLabel(packageManager)));
            }
        } else {
            this.F3.setVisibility(8);
        }
        if (this.F3.getVisibility() == 0) {
            this.s.setBackgroundDrawable(this.x);
        } else {
            this.s.setBackgroundDrawable(null);
        }
    }

    public b.b.q.c getDataModel() {
        return this.f176c.b();
    }

    public ListPopupWindow getListPopupWindow() {
        if (this.L3 == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.L3 = listPopupWindow;
            listPopupWindow.o(this.f176c);
            this.L3.z(this);
            this.L3.F(true);
            this.L3.H(this.f177d);
            this.L3.G(this.f177d);
        }
        return this.L3;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f176c.b() != null) {
            throw null;
        }
        this.P3 = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f176c.b() != null) {
            throw null;
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.K3);
        }
        if (b()) {
            a();
        }
        this.P3 = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.s.layout(0, 0, i4 - i2, i5 - i3);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public void onMeasure(int i2, int i3) {
        View view = this.s;
        if (this.F3.getVisibility() != 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i3), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        measureChild(view, i2, i3);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(b.b.q.c cVar) {
        this.f176c.g(cVar);
        if (b()) {
            a();
            c();
        }
    }

    public void setDefaultActionButtonContentDescription(int i2) {
        this.Q3 = i2;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i2) {
        this.E3.setContentDescription(getContext().getString(i2));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.E3.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i2) {
        this.O3 = i2;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.M3 = onDismissListener;
    }

    public void setProvider(b.f.m.b bVar) {
        this.I3 = bVar;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.J3 = new a();
        this.K3 = new b();
        this.O3 = 4;
        int[] iArr = j.E;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i2, 0);
        s.R(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i2, 0);
        this.O3 = typedArrayObtainStyledAttributes.getInt(j.G, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(j.F);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(b.b.g.f753e, (ViewGroup) this, true);
        g gVar = new g();
        this.f177d = gVar;
        View viewFindViewById = findViewById(b.b.f.j);
        this.s = viewFindViewById;
        this.x = viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(b.b.f.r);
        this.F3 = frameLayout;
        frameLayout.setOnClickListener(gVar);
        frameLayout.setOnLongClickListener(gVar);
        int i3 = b.b.f.w;
        this.G3 = (ImageView) frameLayout.findViewById(i3);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(b.b.f.t);
        frameLayout2.setOnClickListener(gVar);
        frameLayout2.setAccessibilityDelegate(new c());
        frameLayout2.setOnTouchListener(new d(frameLayout2));
        this.y = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(i3);
        this.E3 = imageView;
        imageView.setImageDrawable(drawable);
        f fVar = new f();
        this.f176c = fVar;
        fVar.registerDataSetObserver(new e());
        Resources resources = context.getResources();
        this.H3 = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(b.b.d.f725d));
    }
}
