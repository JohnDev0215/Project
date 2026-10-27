package androidx.appcompat.widget;

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
import androidx.appcompat.R;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.widget.ActivityChooserModel;
import androidx.core.view.ActionProvider;
import defpackage.in3;
import defpackage.om3;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ActivityChooserView extends ViewGroup implements ActivityChooserModel.ActivityChooserModelClient {
    public final ActivityChooserViewAdapter e;
    public final Callbacks f;
    public final View g;
    public final Drawable h;
    public final FrameLayout i;
    public final ImageView j;
    public final FrameLayout k;
    public final ImageView l;
    public final int m;
    public ActionProvider n;
    public final DataSetObserver o;
    public final ViewTreeObserver.OnGlobalLayoutListener p;
    public ListPopupWindow q;
    public PopupWindow.OnDismissListener r;
    public boolean s;
    public int t;
    public boolean u;
    public int v;

    public class ActivityChooserViewAdapter extends BaseAdapter {
        public ActivityChooserModel e;
        public int f = 4;
        public boolean g;
        public boolean h;
        public boolean i;

        public ActivityChooserViewAdapter() {
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            int iD = this.e.d();
            if (!this.g && this.e.e() != null) {
                iD--;
            }
            int iMin = Math.min(iD, this.f);
            return this.i ? iMin + 1 : iMin;
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i) {
            int itemViewType = getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType == 1) {
                    return null;
                }
                throw new IllegalArgumentException();
            }
            if (!this.g && this.e.e() != null) {
                i++;
            }
            return this.e.c(i);
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return i;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public final int getItemViewType(int i) {
            return (this.i && i == getCount() - 1) ? 1 : 0;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i);
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                if (view != null && view.getId() == 1) {
                    return view;
                }
                View viewInflate = LayoutInflater.from(activityChooserView.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
                viewInflate.setId(1);
                ((TextView) viewInflate.findViewById(R.id.title)).setText(activityChooserView.getContext().getString(R.string.abc_activity_chooser_view_see_all));
                return viewInflate;
            }
            if (view == null || view.getId() != R.id.list_item) {
                view = LayoutInflater.from(activityChooserView.getContext()).inflate(R.layout.abc_activity_chooser_view_list_item, viewGroup, false);
            }
            PackageManager packageManager = activityChooserView.getContext().getPackageManager();
            ImageView imageView = (ImageView) view.findViewById(R.id.icon);
            ResolveInfo resolveInfo = (ResolveInfo) getItem(i);
            imageView.setImageDrawable(resolveInfo.loadIcon(packageManager));
            ((TextView) view.findViewById(R.id.title)).setText(resolveInfo.loadLabel(packageManager));
            if (this.g && i == 0 && this.h) {
                view.setActivated(true);
            } else {
                view.setActivated(false);
            }
            return view;
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public final int getViewTypeCount() {
            return 3;
        }
    }

    public class Callbacks implements AdapterView.OnItemClickListener, View.OnClickListener, View.OnLongClickListener, PopupWindow.OnDismissListener {
        public Callbacks() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.k) {
                if (view != activityChooserView.i) {
                    throw new IllegalArgumentException();
                }
                activityChooserView.s = false;
                activityChooserView.c(activityChooserView.t);
                return;
            }
            activityChooserView.a();
            ActivityChooserView.this.e.e.e();
            ActivityChooserModel activityChooserModel = ActivityChooserView.this.e.e;
            synchronized (activityChooserModel.a) {
                try {
                    activityChooserModel.a();
                    ArrayList arrayList = activityChooserModel.b;
                    if (arrayList.size() > 0) {
                        ((ActivityChooserModel.ActivityResolveInfo) arrayList.get(0)).getClass();
                    }
                } finally {
                }
            }
            synchronized (ActivityChooserView.this.e.e.a) {
            }
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public final void onDismiss() {
            ActionProvider.a aVar;
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            PopupWindow.OnDismissListener onDismissListener = activityChooserView.r;
            if (onDismissListener != null) {
                onDismissListener.onDismiss();
            }
            ActionProvider actionProvider = activityChooserView.n;
            if (actionProvider == null || (aVar = actionProvider.b) == null) {
                return;
            }
            ((ActionMenuPresenter) aVar).q(false);
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            int itemViewType = ((ActivityChooserViewAdapter) adapterView.getAdapter()).getItemViewType(i);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    throw new IllegalArgumentException();
                }
                ActivityChooserView.this.c(Integer.MAX_VALUE);
                return;
            }
            ActivityChooserView.this.a();
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (!activityChooserView.s) {
                ActivityChooserViewAdapter activityChooserViewAdapter = activityChooserView.e;
                boolean z = activityChooserViewAdapter.g;
                synchronized (activityChooserViewAdapter.e.a) {
                }
                return;
            }
            if (i > 0) {
                ActivityChooserModel activityChooserModel = activityChooserView.e.e;
                synchronized (activityChooserModel.a) {
                    activityChooserModel.a();
                    ActivityChooserModel.ActivityResolveInfo activityResolveInfo = (ActivityChooserModel.ActivityResolveInfo) activityChooserModel.b.get(i);
                    if (((ActivityChooserModel.ActivityResolveInfo) activityChooserModel.b.get(0)) != null) {
                        activityResolveInfo.getClass();
                    }
                    activityResolveInfo.getClass();
                    throw null;
                }
            }
        }

        @Override // android.view.View.OnLongClickListener
        public final boolean onLongClick(View view) {
            ActivityChooserView activityChooserView = ActivityChooserView.this;
            if (view != activityChooserView.k) {
                throw new IllegalArgumentException();
            }
            if (activityChooserView.e.getCount() > 0) {
                activityChooserView.s = true;
                activityChooserView.c(activityChooserView.t);
            }
            return true;
        }
    }

    public static class InnerLayout extends LinearLayout {
        public static final int[] e = {android.R.attr.background};

        public InnerLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TintTypedArray tintTypedArray = new TintTypedArray(context, context.obtainStyledAttributes(attributeSet, e));
            setBackgroundDrawable(tintTypedArray.e(0));
            tintTypedArray.n();
        }
    }

    public ActivityChooserView(Context context) {
        this(context, null);
    }

    public final void a() {
        if (b()) {
            getListPopupWindow().dismiss();
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeGlobalOnLayoutListener(this.p);
            }
        }
    }

    public final boolean b() {
        return getListPopupWindow().b();
    }

    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [boolean, int] */
    public final void c(int i) {
        ActionProvider.a aVar;
        ActivityChooserViewAdapter activityChooserViewAdapter = this.e;
        if (activityChooserViewAdapter.e == null) {
            throw new IllegalStateException("No data model. Did you call #setDataModel?");
        }
        getViewTreeObserver().addOnGlobalLayoutListener(this.p);
        ?? r1 = this.k.getVisibility() == 0 ? 1 : 0;
        int iD = activityChooserViewAdapter.e.d();
        if (i == Integer.MAX_VALUE || iD <= i + r1) {
            if (activityChooserViewAdapter.i) {
                activityChooserViewAdapter.i = false;
                activityChooserViewAdapter.notifyDataSetChanged();
            }
            if (activityChooserViewAdapter.f != i) {
                activityChooserViewAdapter.f = i;
                activityChooserViewAdapter.notifyDataSetChanged();
            }
        } else {
            if (!activityChooserViewAdapter.i) {
                activityChooserViewAdapter.i = true;
                activityChooserViewAdapter.notifyDataSetChanged();
            }
            int i2 = i - 1;
            if (activityChooserViewAdapter.f != i2) {
                activityChooserViewAdapter.f = i2;
                activityChooserViewAdapter.notifyDataSetChanged();
            }
        }
        ListPopupWindow listPopupWindow = getListPopupWindow();
        if (listPopupWindow.b()) {
            return;
        }
        if (this.s || r1 == 0) {
            if (!activityChooserViewAdapter.g || activityChooserViewAdapter.h != r1) {
                activityChooserViewAdapter.g = true;
                activityChooserViewAdapter.h = r1;
                activityChooserViewAdapter.notifyDataSetChanged();
            }
        } else if (activityChooserViewAdapter.g || activityChooserViewAdapter.h) {
            activityChooserViewAdapter.g = false;
            activityChooserViewAdapter.h = false;
            activityChooserViewAdapter.notifyDataSetChanged();
        }
        int i3 = activityChooserViewAdapter.f;
        activityChooserViewAdapter.f = Integer.MAX_VALUE;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int count = activityChooserViewAdapter.getCount();
        int iMax = 0;
        View view = null;
        for (int i4 = 0; i4 < count; i4++) {
            view = activityChooserViewAdapter.getView(i4, view, null);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax = Math.max(iMax, view.getMeasuredWidth());
        }
        activityChooserViewAdapter.f = i3;
        listPopupWindow.f(Math.min(iMax, this.m));
        listPopupWindow.a();
        ActionProvider actionProvider = this.n;
        if (actionProvider != null && (aVar = actionProvider.b) != null) {
            ((ActionMenuPresenter) aVar).q(true);
        }
        listPopupWindow.g.setContentDescription(getContext().getString(R.string.abc_activitychooserview_choose_application));
        listPopupWindow.g.setSelector(new ColorDrawable(0));
    }

    public ActivityChooserModel getDataModel() {
        return this.e.e;
    }

    public ListPopupWindow getListPopupWindow() {
        if (this.q == null) {
            ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
            this.q = listPopupWindow;
            listPopupWindow.v(this.e);
            ListPopupWindow listPopupWindow2 = this.q;
            listPopupWindow2.s = this;
            listPopupWindow2.C = true;
            listPopupWindow2.D.setFocusable(true);
            ListPopupWindow listPopupWindow3 = this.q;
            Callbacks callbacks = this.f;
            listPopupWindow3.setOnItemClickListener(callbacks);
            this.q.setOnDismissListener(callbacks);
        }
        return this.q;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ActivityChooserModel activityChooserModel = this.e.e;
        if (activityChooserModel != null) {
            activityChooserModel.registerObserver(this.o);
        }
        this.u = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActivityChooserModel activityChooserModel = this.e.e;
        if (activityChooserModel != null) {
            activityChooserModel.unregisterObserver(this.o);
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.p);
        }
        if (b()) {
            a();
        }
        this.u = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.g.layout(0, 0, i3 - i, i4 - i2);
        if (b()) {
            return;
        }
        a();
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.k.getVisibility() != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED);
        }
        View view = this.g;
        measureChild(view, i, i2);
        setMeasuredDimension(view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    public void setActivityChooserModel(ActivityChooserModel activityChooserModel) {
        ActivityChooserViewAdapter activityChooserViewAdapter = this.e;
        ActivityChooserView activityChooserView = ActivityChooserView.this;
        ActivityChooserModel activityChooserModel2 = activityChooserView.e.e;
        DataSetObserver dataSetObserver = activityChooserView.o;
        if (activityChooserModel2 != null && activityChooserView.isShown()) {
            activityChooserModel2.unregisterObserver(dataSetObserver);
        }
        activityChooserViewAdapter.e = activityChooserModel;
        if (activityChooserModel != null && activityChooserView.isShown()) {
            activityChooserModel.registerObserver(dataSetObserver);
        }
        activityChooserViewAdapter.notifyDataSetChanged();
        if (b()) {
            a();
            if (b() || !this.u) {
                return;
            }
            this.s = false;
            c(this.t);
        }
    }

    public void setDefaultActionButtonContentDescription(int i) {
        this.v = i;
    }

    public void setExpandActivityOverflowButtonContentDescription(int i) {
        this.j.setContentDescription(getContext().getString(i));
    }

    public void setExpandActivityOverflowButtonDrawable(Drawable drawable) {
        this.j.setImageDrawable(drawable);
    }

    public void setInitialActivityCount(int i) {
        this.t = i;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener onDismissListener) {
        this.r = onDismissListener;
    }

    public void setProvider(ActionProvider actionProvider) {
        this.n = actionProvider;
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ActivityChooserView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.o = new DataSetObserver() { // from class: androidx.appcompat.widget.ActivityChooserView.1
            @Override // android.database.DataSetObserver
            public final void onChanged() {
                super.onChanged();
                ActivityChooserView.this.e.notifyDataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public final void onInvalidated() {
                super.onInvalidated();
                ActivityChooserView.this.e.notifyDataSetInvalidated();
            }
        };
        this.p = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.appcompat.widget.ActivityChooserView.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                ActionProvider.a aVar;
                ActivityChooserView activityChooserView = ActivityChooserView.this;
                if (activityChooserView.b()) {
                    if (!activityChooserView.isShown()) {
                        activityChooserView.getListPopupWindow().dismiss();
                        return;
                    }
                    activityChooserView.getListPopupWindow().a();
                    ActionProvider actionProvider = activityChooserView.n;
                    if (actionProvider == null || (aVar = actionProvider.b) == null) {
                        return;
                    }
                    ((ActionMenuPresenter) aVar).q(true);
                }
            }
        };
        this.t = 4;
        int[] iArr = R.styleable.ActivityChooserView;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, 0);
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i, 0);
        this.t = typedArrayObtainStyledAttributes.getInt(R.styleable.ActivityChooserView_initialActivityCount, 4);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(R.styleable.ActivityChooserView_expandActivityOverflowButtonDrawable);
        typedArrayObtainStyledAttributes.recycle();
        LayoutInflater.from(getContext()).inflate(R.layout.abc_activity_chooser_view, (ViewGroup) this, true);
        Callbacks callbacks = new Callbacks();
        this.f = callbacks;
        View viewFindViewById = findViewById(R.id.activity_chooser_view_content);
        this.g = viewFindViewById;
        this.h = viewFindViewById.getBackground();
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.default_activity_button);
        this.k = frameLayout;
        frameLayout.setOnClickListener(callbacks);
        frameLayout.setOnLongClickListener(callbacks);
        int i2 = R.id.image;
        this.l = (ImageView) frameLayout.findViewById(i2);
        FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.expand_activities_button);
        frameLayout2.setOnClickListener(callbacks);
        frameLayout2.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: androidx.appcompat.widget.ActivityChooserView.3
            @Override // android.view.View.AccessibilityDelegate
            public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                accessibilityNodeInfo.setCanOpenPopup(true);
            }
        });
        frameLayout2.setOnTouchListener(new ForwardingListener(frameLayout2) { // from class: androidx.appcompat.widget.ActivityChooserView.4
            @Override // androidx.appcompat.widget.ForwardingListener
            public final ShowableListMenu b() {
                return ActivityChooserView.this.getListPopupWindow();
            }

            @Override // androidx.appcompat.widget.ForwardingListener
            public final boolean c() {
                ActivityChooserView activityChooserView = ActivityChooserView.this;
                if (activityChooserView.b() || !activityChooserView.u) {
                    return true;
                }
                activityChooserView.s = false;
                activityChooserView.c(activityChooserView.t);
                return true;
            }

            @Override // androidx.appcompat.widget.ForwardingListener
            public final boolean d() {
                ActivityChooserView.this.a();
                return true;
            }
        });
        this.i = frameLayout2;
        ImageView imageView = (ImageView) frameLayout2.findViewById(i2);
        this.j = imageView;
        imageView.setImageDrawable(drawable);
        ActivityChooserViewAdapter activityChooserViewAdapter = new ActivityChooserViewAdapter();
        this.e = activityChooserViewAdapter;
        activityChooserViewAdapter.registerDataSetObserver(new DataSetObserver() { // from class: androidx.appcompat.widget.ActivityChooserView.5
            @Override // android.database.DataSetObserver
            public final void onChanged() {
                int size;
                super.onChanged();
                ActivityChooserView activityChooserView = ActivityChooserView.this;
                if (activityChooserView.e.getCount() > 0) {
                    activityChooserView.i.setEnabled(true);
                } else {
                    activityChooserView.i.setEnabled(false);
                }
                int iD = activityChooserView.e.e.d();
                ActivityChooserModel activityChooserModel = activityChooserView.e.e;
                synchronized (activityChooserModel.a) {
                    activityChooserModel.a();
                    size = activityChooserModel.c.size();
                }
                if (iD == 1 || (iD > 1 && size > 0)) {
                    activityChooserView.k.setVisibility(0);
                    ResolveInfo resolveInfoE = activityChooserView.e.e.e();
                    PackageManager packageManager = activityChooserView.getContext().getPackageManager();
                    activityChooserView.l.setImageDrawable(resolveInfoE.loadIcon(packageManager));
                    if (activityChooserView.v != 0) {
                        activityChooserView.k.setContentDescription(activityChooserView.getContext().getString(activityChooserView.v, resolveInfoE.loadLabel(packageManager)));
                    }
                } else {
                    activityChooserView.k.setVisibility(8);
                }
                if (activityChooserView.k.getVisibility() == 0) {
                    activityChooserView.g.setBackgroundDrawable(activityChooserView.h);
                } else {
                    activityChooserView.g.setBackgroundDrawable(null);
                }
            }
        });
        Resources resources = context.getResources();
        this.m = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
    }
}
