package com.android.car.ui.recyclerview;

import android.annotation.SuppressLint;
import android.car.drivingstate.XCarUxRestrictions;
import android.car.utils.Logs;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.android.arouter.utils.Consts;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$dimen;
import com.android.car.ui.R$drawable;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$string;
import com.android.car.ui.R$styleable;
import com.android.car.ui.preference.PreferenceFragment;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.CarUiRecyclerViewImpl;
import com.android.car.ui.recyclerview.decorations.grid.GridDividerItemDecoration;
import com.android.car.ui.recyclerview.decorations.linear.LinearDividerItemDecoration;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import spi.b2;
import spi.e1;
import spi.e2;
import spi.j1;
import spi.k1;
import spi.u9;
import spi.yc;

/* JADX INFO: loaded from: classes2.dex */
@RequiresApi(28)
@SuppressLint({"CustomViewStyleable"})
public final class CarUiRecyclerViewImpl extends FrameLayout implements CarUiRecyclerView, yc.a, PreferenceFragment.b {
    public static final Class<?>[] t;

    @Nullable
    public RecyclerView.Adapter<?> a;

    @NonNull
    public RecyclerView b;
    public final d c;

    @NonNull
    public final e2 d;
    public boolean e;

    @Nullable
    public String f;
    public int g;
    public int h;

    @Nullable
    public u9 i;

    @Nullable
    public GridDividerItemDecoration j;

    @Nullable
    public LinearDividerItemDecoration k;
    public int l;
    public int m;
    public boolean n;
    public boolean o;

    @NonNull
    public final Set<Runnable> p;

    @Nullable
    public j1 q;

    @NonNull
    public final List<CarUiRecyclerView.c> r;

    @NonNull
    public final a s;

    public class a extends RecyclerView.OnScrollListener {
        public a() {
        }

        /* JADX WARN: Type inference failed for: r1v2, types: [java.util.ArrayList, java.util.List<com.android.car.ui.recyclerview.CarUiRecyclerView$c>] */
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public final void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
            Iterator it = CarUiRecyclerViewImpl.this.r.iterator();
            while (it.hasNext()) {
                ((CarUiRecyclerView.c) it.next()).a();
            }
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.util.ArrayList, java.util.List<com.android.car.ui.recyclerview.CarUiRecyclerView$c>] */
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public final void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
            Iterator it = CarUiRecyclerViewImpl.this.r.iterator();
            while (it.hasNext()) {
                ((CarUiRecyclerView.c) it.next()).b();
            }
        }
    }

    public class b extends LinearLayoutManager {
        public b(Context context, int i, boolean z) {
            super(context, i, z);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public final void onLayoutCompleted(RecyclerView.State state) {
            super.onLayoutCompleted(state);
            Iterator it = new HashSet(CarUiRecyclerViewImpl.this.p).iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
    }

    public class c extends GridLayoutManager {
        public c(Context context, int i, int i2, boolean z) {
            super(context, i, i2, z);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public final void onLayoutCompleted(RecyclerView.State state) {
            super.onLayoutCompleted(state);
            Iterator it = new HashSet(CarUiRecyclerViewImpl.this.p).iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }
    }

    public class d implements e2.a {
        private d() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // spi.e2.a
        public final void a(@NonNull XCarUxRestrictions xCarUxRestrictions) {
            RecyclerView.Adapter adapter = CarUiRecyclerViewImpl.this.b.getAdapter();
            if (adapter instanceof CarUiRecyclerView.a) {
                int maxCumulativeContentItems = (xCarUxRestrictions.getActiveRestrictions() & 32) != 0 ? xCarUxRestrictions.getMaxCumulativeContentItems() : -1;
                int itemCount = adapter.getItemCount();
                ((CarUiRecyclerView.a) adapter).a(maxCumulativeContentItems);
                int itemCount2 = adapter.getItemCount();
                if (itemCount2 == itemCount) {
                    return;
                }
                if (itemCount2 < itemCount) {
                    adapter.notifyItemRangeRemoved(itemCount2, itemCount - itemCount2);
                } else {
                    adapter.notifyItemRangeInserted(itemCount, itemCount2 - itemCount);
                }
            }
        }

        public /* synthetic */ d(CarUiRecyclerViewImpl carUiRecyclerViewImpl, a aVar) {
            this();
        }
    }

    static {
        Class<?> cls = Integer.TYPE;
        t = new Class[]{Context.class, AttributeSet.class, cls, cls};
    }

    public CarUiRecyclerViewImpl(@NonNull Context context) {
        this(context, null);
    }

    private void setNumOfColumns(int i) {
        this.l = i;
        GridDividerItemDecoration gridDividerItemDecoration = this.j;
        if (gridDividerItemDecoration != null) {
            gridDividerItemDecoration.c = i;
        }
    }

    @Override // spi.yc.a
    public final boolean a() {
        RecyclerView.Adapter<?> adapter = getAdapter();
        return (adapter == null || adapter.getItemCount() <= 0 || this.b.isComputingLayout()) ? false : true;
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public final void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {
        this.b.addItemDecoration(itemDecoration);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void addOnChildAttachStateChangeListener(RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        this.b.addOnChildAttachStateChangeListener(onChildAttachStateChangeListener);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<com.android.car.ui.recyclerview.CarUiRecyclerView$c>] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.ArrayList, java.util.List<com.android.car.ui.recyclerview.CarUiRecyclerView$c>] */
    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void addOnScrollListener(CarUiRecyclerView.c cVar) {
        if (this.r.isEmpty()) {
            this.b.addOnScrollListener(this.s);
        }
        this.r.add(cVar);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<java.lang.Runnable>] */
    @Override // spi.yc.a
    public final void b(@Nullable Runnable runnable) {
        if (runnable != null) {
            this.p.remove(runnable);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.HashSet, java.util.Set<java.lang.Runnable>] */
    @Override // spi.yc.a
    public final void c(@Nullable Runnable runnable) {
        if (runnable != null) {
            this.p.add(runnable);
        }
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i) {
        return this.b.canScrollHorizontally(i);
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i) {
        return this.b.canScrollVertically(i);
    }

    public final OrientationHelper d() {
        j1 j1Var = this.q;
        return (j1Var == null || j1Var.getOrientation() != 1) ? OrientationHelper.createHorizontalHelper(this.b.getLayoutManager()) : OrientationHelper.createVerticalHelper(this.b.getLayoutManager());
    }

    public final void e(@NonNull final ViewGroup viewGroup, boolean z, int i) {
        if (z) {
            viewGroup.setContentDescription(i == 1 ? "com.android.car.ui.utils.VERTICALLY_SCROLLABLE" : "com.android.car.ui.utils.HORIZONTALLY_SCROLLABLE");
        }
        viewGroup.setOnGenericMotionListener(z ? new View.OnGenericMotionListener() { // from class: spi.s1
            @Override // android.view.View.OnGenericMotionListener
            public final boolean onGenericMotion(View view, MotionEvent motionEvent) {
                ViewGroup viewGroup2 = viewGroup;
                Class<?>[] clsArr = CarUiRecyclerViewImpl.t;
                if (motionEvent.getAction() != 8 || motionEvent.getSource() != 4194304) {
                    return false;
                }
                MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
                motionEventObtain.setSource(8194);
                viewGroup2.onGenericMotionEvent(motionEventObtain);
                return true;
            }
        } : null);
        viewGroup.setFocusable(z);
        viewGroup.setDescendantFocusability(131072);
        viewGroup.setDefaultFocusHighlightEnabled(false);
        viewGroup.setOnFocusChangeListener(z ? new View.OnFocusChangeListener() { // from class: spi.r1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z2) {
                u9 u9Var = this.a.i;
                if (u9Var != null) {
                    u9Var.setHighlightThumb(z2);
                }
            }
        } : null);
        if (z) {
            return;
        }
        viewGroup.setContentDescription("com.android.car.ui.utils.ROTARY_CONTAINER");
    }

    public final void f(int i, int i2) {
        u9 u9Var;
        if (!this.e || (u9Var = this.i) == null) {
            return;
        }
        u9Var.setPadding(i, i2);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public final int findFirstCompletelyVisibleItemPosition() {
        RecyclerView.LayoutManager layoutManager = this.b.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public final int findFirstVisibleItemPosition() {
        RecyclerView.LayoutManager layoutManager = this.b.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public final int findLastCompletelyVisibleItemPosition() {
        RecyclerView.LayoutManager layoutManager = this.b.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        return ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public final int findLastVisibleItemPosition() {
        RecyclerView.LayoutManager layoutManager = this.b.getLayoutManager();
        Objects.requireNonNull(layoutManager);
        return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public final RecyclerView.ViewHolder findViewHolderForAdapterPosition(int i) {
        return this.b.findViewHolderForAdapterPosition(i);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public RecyclerView.Adapter<?> getAdapter() {
        return this.b.getAdapter();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public final int getChildLayoutPosition(View view) {
        return this.b.getChildLayoutPosition(view);
    }

    public int getEndAfterPadding() {
        if (this.q == null) {
            return 0;
        }
        return d().getEndAfterPadding();
    }

    public int getItemDecorationCount() {
        return this.b.getItemDecorationCount();
    }

    @Nullable
    public RecyclerView.LayoutManager getLayoutManager() {
        return this.b.getLayoutManager();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public j1 getLayoutStyle() {
        return this.q;
    }

    @Override // android.view.View, com.android.car.ui.recyclerview.CarUiRecyclerView
    public int getPaddingBottom() {
        return this.b.getPaddingBottom();
    }

    @Override // android.view.View, com.android.car.ui.recyclerview.CarUiRecyclerView
    public int getPaddingTop() {
        return this.b.getPaddingTop();
    }

    @Override // com.android.car.ui.preference.PreferenceFragment.b
    public RecyclerView getRecyclerView() {
        return this.b;
    }

    public int getRecyclerViewChildCount() {
        if (this.b.getLayoutManager() != null) {
            return this.b.getLayoutManager().getChildCount();
        }
        return 0;
    }

    public u9 getScrollBar() {
        return this.i;
    }

    public int getScrollState() {
        int scrollState = this.b.getScrollState();
        if (scrollState != 1) {
            return scrollState != 2 ? 0 : 2;
        }
        return 1;
    }

    public int getStartAfterPadding() {
        if (this.q == null) {
            return 0;
        }
        return d().getStartAfterPadding();
    }

    public int getTotalSpace() {
        if (this.q == null) {
            return 0;
        }
        return d().getTotalSpace();
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    @NonNull
    public View getView() {
        return this;
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public final void invalidateItemDecorations() {
        this.b.invalidateItemDecorations();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.d.register(this.c);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.d.unregister(this.c);
    }

    @Override // android.view.View, com.android.car.ui.recyclerview.CarUiRecyclerView
    public final boolean post(Runnable runnable) {
        return this.b.post(runnable);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void removeOnChildAttachStateChangeListener(RecyclerView.OnChildAttachStateChangeListener onChildAttachStateChangeListener) {
        this.b.removeOnChildAttachStateChangeListener(onChildAttachStateChangeListener);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.ArrayList, java.util.List<com.android.car.ui.recyclerview.CarUiRecyclerView$c>] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.ArrayList, java.util.List<com.android.car.ui.recyclerview.CarUiRecyclerView$c>] */
    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void removeOnScrollListener(CarUiRecyclerView.c cVar) {
        this.r.remove(cVar);
        if (this.r.isEmpty()) {
            this.b.removeOnScrollListener(this.s);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        super.requestLayout();
        if (this.n) {
            this.b.requestLayout();
        }
        u9 u9Var = this.i;
        if (u9Var != null) {
            u9Var.requestLayout();
        }
    }

    @Override // android.view.View
    public final void scrollBy(int i, int i2) {
        this.b.scrollBy(i, i2);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public final void scrollToPosition(int i) {
        this.b.scrollToPosition(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void setAdapter(@Nullable RecyclerView.Adapter<?> adapter) {
        u9 u9Var = this.i;
        if (u9Var != null) {
            u9Var.adapterChanged(adapter);
        }
        Object obj = this.a;
        if (obj instanceof CarUiRecyclerView.b) {
            ((CarUiRecyclerView.b) obj).b();
        }
        this.a = adapter;
        this.b.setAdapter(adapter);
        if (adapter instanceof CarUiRecyclerView.b) {
            ((CarUiRecyclerView.b) adapter).a();
        }
    }

    @Override // android.view.View
    public void setContentDescription(CharSequence charSequence) {
        boolean z = charSequence != null && ("com.android.car.ui.utils.HORIZONTALLY_SCROLLABLE".contentEquals(charSequence) || "com.android.car.ui.utils.VERTICALLY_SCROLLABLE".contentEquals(charSequence));
        e(this.b, z, getLayoutStyle() != null ? getLayoutStyle().getOrientation() : 1);
        if (z) {
            return;
        }
        super.setContentDescription(charSequence);
    }

    public void setHasFixedSize(boolean z) {
        this.b.setHasFixedSize(z);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        this.b.setItemAnimator(itemAnimator);
    }

    public void setLayoutManager(@Nullable RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            setLayoutStyle(e1.a(layoutManager));
        } else {
            setLayoutStyle(k1.a(layoutManager));
        }
    }

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
    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    @SuppressLint({"WrongConstant"})
    public void setLayoutStyle(j1 j1Var) {
        RecyclerView.LayoutManager bVar;
        this.q = j1Var;
        if (j1Var == null) {
            this.b.setLayoutManager(null);
            return;
        }
        if (j1Var.getLayoutType() == 0) {
            bVar = new b(getContext(), j1Var.getOrientation(), j1Var.getReverseLayout());
        } else {
            c cVar = new c(getContext(), j1Var.getSpanCount(), j1Var.getOrientation(), j1Var.getReverseLayout());
            bVar = cVar;
            if (j1Var instanceof e1) {
                cVar.setSpanSizeLookup(((e1) j1Var).d);
                bVar = cVar;
            }
        }
        if (this.n) {
            RecyclerView recyclerView = this.b;
            GridDividerItemDecoration gridDividerItemDecoration = this.j;
            Objects.requireNonNull(gridDividerItemDecoration);
            recyclerView.removeItemDecoration(gridDividerItemDecoration);
            RecyclerView recyclerView2 = this.b;
            LinearDividerItemDecoration linearDividerItemDecoration = this.k;
            Objects.requireNonNull(linearDividerItemDecoration);
            recyclerView2.removeItemDecoration(linearDividerItemDecoration);
            if (bVar instanceof GridLayoutManager) {
                if (this.o) {
                    RecyclerView recyclerView3 = this.b;
                    GridDividerItemDecoration gridDividerItemDecoration2 = this.j;
                    Objects.requireNonNull(gridDividerItemDecoration2);
                    recyclerView3.addItemDecoration(gridDividerItemDecoration2);
                }
                setNumOfColumns(((GridLayoutManager) bVar).getSpanCount());
            } else if (this.o) {
                RecyclerView recyclerView4 = this.b;
                LinearDividerItemDecoration linearDividerItemDecoration2 = this.k;
                Objects.requireNonNull(linearDividerItemDecoration2);
                recyclerView4.addItemDecoration(linearDividerItemDecoration2);
            }
        }
        this.b.setLayoutManager(bVar);
    }

    @Override // com.android.car.ui.recyclerview.CarUiRecyclerView
    public void setOnFlingListener(RecyclerView.OnFlingListener onFlingListener) {
        this.b.setOnFlingListener(onFlingListener);
    }

    @Override // android.view.View, com.android.car.ui.recyclerview.CarUiRecyclerView
    public final void setPadding(int i, int i2, int i3, int i4) {
        if (this.e) {
            f(this.g + i2, i4 == 0 ? this.h : i4);
        }
        this.b.setPadding(0, i2, 0, i4);
        Logs.d("CarUiRecyclerView", "CarUiRecyclerViewImpl setPadding mRecyclerView setPadding top:" + i2);
        super.setPadding(i, 0, i3, 0);
    }

    @Override // android.view.View
    public final void setPaddingRelative(int i, int i2, int i3, int i4) {
        if (this.e) {
            f(this.g + i2, i4 == 0 ? this.h : i4);
        }
        this.b.setPaddingRelative(0, i2, 0, i4);
        Logs.d("CarUiRecyclerView", "CarUiRecyclerViewImpl setPaddingRelative mRecyclerView setPaddingRelative top:" + i2);
        super.setPaddingRelative(i, 0, i3, 0);
    }

    public void setSpanSizeLookup(@NonNull GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        if (this.b.getLayoutManager() instanceof GridLayoutManager) {
            ((GridLayoutManager) this.b.getLayoutManager()).setSpanSizeLookup(spanSizeLookup);
        }
    }

    public CarUiRecyclerViewImpl(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarUiRecyclerViewImpl(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        Class<?> clsLoadClass;
        ClassLoader classLoader;
        Constructor constructor;
        super(context, attributeSet, i);
        Object[] objArr = null;
        this.c = new d(this, 0 == true ? 1 : 0);
        this.p = new HashSet();
        this.r = new ArrayList();
        this.s = new a();
        this.d = e2.a(context);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CarUiRecyclerView, i, 0);
        this.e = context.getResources().getBoolean(R$bool.car_ui_scrollbar_enable);
        int i2 = R$layout.car_ui_recycler_view_no_scrollbar;
        int i3 = typedArrayObtainStyledAttributes.getInt(R$styleable.CarUiRecyclerView_carUiSize, 2);
        this.m = i3;
        if (this.e) {
            if (i3 == 0) {
                i2 = R$layout.car_ui_recycler_view_small;
            } else if (i3 == 1) {
                i2 = R$layout.car_ui_recycler_view_medium;
            } else if (i3 == 2) {
                i2 = R$layout.car_ui_recycler_view;
            }
        }
        StringBuilder sbK = spi.c.k("CarUiRecyclerViewImpl init mSize:");
        sbK.append(this.m);
        Logs.d("CarUiRecyclerView", sbK.toString());
        View viewInflate = LayoutInflater.from(context).inflate(i2, (ViewGroup) this, true);
        ViewGroup viewGroup = (ViewGroup) b2.n(viewInflate, R$id.car_ui_recycler_view);
        if (viewGroup instanceof CarUiRecyclerViewContainer) {
            this.b = (RecyclerView) viewGroup.getChildAt(0);
            Logs.d("CarUiRecyclerView", "CarUiRecyclerViewImpl init 1:");
        } else {
            this.b = (RecyclerView) viewGroup;
            Logs.d("CarUiRecyclerView", "CarUiRecyclerViewImpl init 2:");
        }
        try {
            if (viewGroup.getParent() != null && ((ViewGroup) viewGroup.getParent()).getChildAt(1) != null && ((ViewGroup) ((ViewGroup) viewGroup.getParent()).getChildAt(1)).getChildAt(0) != null && ((ViewGroup) ((ViewGroup) viewGroup.getParent()).getChildAt(1)).getChildAt(0).getId() == R$id.car_ui_scroll_bar) {
                ((FrameLayout.LayoutParams) ((ViewGroup) viewGroup.getParent()).getChildAt(1).getLayoutParams()).gravity = GravityCompat.END;
            }
        } catch (Exception unused) {
            Logs.e("CarUiRecyclerView", "error: setScrollBarLoc");
        }
        e(this.b, typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiRecyclerView_rotaryScrollEnabled, false), typedArrayObtainStyledAttributes.getInt(R$styleable.CarUiRecyclerView_android_orientation, 1));
        this.g = context.getResources().getDimensionPixelSize(R$dimen.car_ui_scrollbar_padding_top);
        this.h = context.getResources().getDimensionPixelSize(R$dimen.car_ui_scrollbar_padding_bottom);
        int i4 = typedArrayObtainStyledAttributes.getInt(R$styleable.CarUiRecyclerView_layoutStyle, 0);
        this.l = typedArrayObtainStyledAttributes.getInt(R$styleable.CarUiRecyclerView_numOfColumns, 2);
        this.o = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CarUiRecyclerView_enableDivider, false);
        this.k = new LinearDividerItemDecoration(ContextCompat.getDrawable(context, R$drawable.car_ui_recyclerview_divider));
        int i5 = R$drawable.car_ui_divider;
        this.j = new GridDividerItemDecoration(ContextCompat.getDrawable(context, i5), ContextCompat.getDrawable(context, i5), this.l);
        this.n = true;
        this.b.setClipToPadding(true);
        String string = typedArrayObtainStyledAttributes.getString(R$styleable.CarUiRecyclerView_layoutManager);
        if (TextUtils.isEmpty(string)) {
            if (i4 == 1) {
                setLayoutManager(new GridLayoutManager(getContext(), this.l));
            } else {
                setLayoutManager(new LinearLayoutManager(getContext()));
            }
        } else if (string != null) {
            String strTrim = string.trim();
            if (!strTrim.isEmpty()) {
                if (strTrim.charAt(0) == '.') {
                    strTrim = context.getPackageName() + strTrim;
                } else if (!strTrim.contains(Consts.DOT)) {
                    strTrim = RecyclerView.class.getPackage().getName() + '.' + strTrim;
                }
                try {
                    if (isInEditMode()) {
                        classLoader = CarUiRecyclerViewImpl.class.getClassLoader();
                    } else {
                        classLoader = context.getClassLoader();
                    }
                    Class<? extends U> clsAsSubclass = Class.forName(strTrim, false, classLoader).asSubclass(RecyclerView.LayoutManager.class);
                    try {
                        constructor = clsAsSubclass.getConstructor(t);
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i), 0};
                    } catch (NoSuchMethodException e) {
                        try {
                            constructor = clsAsSubclass.getConstructor(new Class[0]);
                        } catch (NoSuchMethodException e2) {
                            e2.initCause(e);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strTrim, e2);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((RecyclerView.LayoutManager) constructor.newInstance(objArr));
                } catch (ClassCastException e3) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strTrim, e3);
                } catch (ClassNotFoundException e4) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strTrim, e4);
                } catch (IllegalAccessException e5) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strTrim, e5);
                } catch (InstantiationException e6) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strTrim, e6);
                } catch (InvocationTargetException e7) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strTrim, e7);
                }
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        if (this.e) {
            this.b.setVerticalScrollBarEnabled(false);
            this.b.setHorizontalScrollBarEnabled(false);
            this.f = context.getResources().getString(R$string.car_ui_scrollbar_component);
            View viewN = b2.n(viewInflate, R$id.car_ui_scroll_bar);
            try {
                if (!TextUtils.isEmpty(this.f)) {
                    clsLoadClass = getContext().getClassLoader().loadClass(this.f);
                } else {
                    clsLoadClass = DefaultScrollBar.class;
                }
                try {
                    Constructor<?> declaredConstructor = clsLoadClass.getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    u9 u9Var = (u9) declaredConstructor.newInstance(new Object[0]);
                    this.i = u9Var;
                    u9Var.initialize(context, this.b, viewN);
                    f(getPaddingTop() + this.g, getPaddingBottom() + this.h);
                } catch (ReflectiveOperationException e8) {
                    StringBuilder sbK2 = spi.c.k("Error creating scroll bar component: ");
                    sbK2.append(this.f);
                    throw new IllegalArgumentException(sbK2.toString(), e8);
                }
            } catch (ReflectiveOperationException e9) {
                StringBuilder sbK3 = spi.c.k("Error loading scroll bar component: ");
                sbK3.append(this.f);
                throw new IllegalArgumentException(sbK3.toString(), e9);
            }
        }
    }
}
