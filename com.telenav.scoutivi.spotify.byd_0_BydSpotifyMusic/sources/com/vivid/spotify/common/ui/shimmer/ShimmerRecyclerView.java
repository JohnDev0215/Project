package com.vivid.spotify.common.ui.shimmer;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.media.MediaBrowserCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.vivid.spotify.q;
import com.vivid.spotify.y;
import java.util.List;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.l;

/* JADX INFO: compiled from: ShimmerRecyclerView.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ShimmerRecyclerView extends w3.b {

    /* JADX INFO: renamed from: k1, reason: collision with root package name */
    public static final a f5240k1 = new a(null);

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    private RecyclerView.g<?> f5241c1;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    private x3.a f5242d1;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    private RecyclerView.o f5243e1;

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    private RecyclerView.o f5244f1;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    private b f5245g1;

    /* JADX INFO: renamed from: h1, reason: collision with root package name */
    private boolean f5246h1;

    /* JADX INFO: renamed from: i1, reason: collision with root package name */
    private int f5247i1;

    /* JADX INFO: renamed from: j1, reason: collision with root package name */
    private int f5248j1;

    /* JADX INFO: compiled from: ShimmerRecyclerView.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(g gVar) {
            this();
        }

        public final void a(ShimmerRecyclerView recyclerView, List<? extends MediaBrowserCompat.MediaItem> list) {
            l.e(recyclerView, "recyclerView");
            if (list == null || list.isEmpty()) {
                recyclerView.N1();
            } else {
                recyclerView.J1();
            }
        }
    }

    /* JADX INFO: compiled from: ShimmerRecyclerView.kt */
    public enum b {
        LINEAR_VERTICAL,
        LINEAR_HORIZONTAL,
        GRID
    }

    /* JADX INFO: compiled from: ShimmerRecyclerView.kt */
    public /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f5250a;

        static {
            int[] iArr = new int[b.values().length];
            iArr[b.LINEAR_VERTICAL.ordinal()] = 1;
            iArr[b.LINEAR_HORIZONTAL.ordinal()] = 2;
            iArr[b.GRID.ordinal()] = 3;
            f5250a = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ShimmerRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        l.e(context, "context");
        K1(context, attributeSet);
    }

    private final int I1(int i7) {
        return getContext().getColor(i7);
    }

    private final void K1(Context context, AttributeSet attributeSet) {
        int iP2;
        this.f5242d1 = new x3.a();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, y.ShimmerRecyclerView, 0, 0);
        l.d(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…himmerRecyclerView, 0, 0)");
        try {
            setDemoLayoutReference(typedArrayObtainStyledAttributes.getResourceId(y.ShimmerRecyclerView_shimmer_layout, -1));
            setGridChildCount(typedArrayObtainStyledAttributes.getInteger(y.ShimmerRecyclerView_shimmer_grid_child_count, 2));
            if (getLayoutManager() instanceof LinearLayoutManager) {
                RecyclerView.o layoutManager = getLayoutManager();
                if (layoutManager == null) {
                    throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                }
                iP2 = ((LinearLayoutManager) layoutManager).p2();
            } else {
                iP2 = 1;
            }
            int integer = typedArrayObtainStyledAttributes.getInteger(y.ShimmerRecyclerView_shimmer_layout_manager_type, iP2);
            if (integer == 0) {
                setDemoLayoutManager(b.LINEAR_HORIZONTAL);
            } else if (integer == 1) {
                setDemoLayoutManager(b.LINEAR_VERTICAL);
            } else {
                if (integer != 2) {
                    throw new IllegalArgumentException("This value for layout manager is not valid!");
                }
                setDemoLayoutManager(b.GRID);
            }
            int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(y.ShimmerRecyclerView_shimmer_item_decoration_size, 0);
            if (dimensionPixelSize != 0) {
                h(new com.vivid.spotify.common.ui.c(integer == 0 ? 0 : 1, dimensionPixelSize));
            }
            x3.a aVar = this.f5242d1;
            l.c(aVar);
            aVar.M(this.S0);
            setDemoChildCount(typedArrayObtainStyledAttributes.getInteger(y.ShimmerRecyclerView_shimmer_child_count, integer == 2 ? 16 : 10));
            int integer2 = typedArrayObtainStyledAttributes.getInteger(y.ShimmerRecyclerView_shimmer_angle, 0);
            int color = typedArrayObtainStyledAttributes.getColor(y.ShimmerRecyclerView_shimmer_shimmer_color, I1(q.default_shimmer_color));
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(y.ShimmerRecyclerView_shimmer_view_holder_item_background);
            int integer3 = typedArrayObtainStyledAttributes.getInteger(y.ShimmerRecyclerView_shimmer_duration, 1500);
            float f7 = typedArrayObtainStyledAttributes.getFloat(y.ShimmerRecyclerView_shimmer_mask_width, 0.5f);
            boolean z6 = typedArrayObtainStyledAttributes.getBoolean(y.ShimmerRecyclerView_shimmer_reverse_animation, false);
            typedArrayObtainStyledAttributes.recycle();
            x3.a aVar2 = this.f5242d1;
            l.c(aVar2);
            aVar2.H(integer2);
            x3.a aVar3 = this.f5242d1;
            l.c(aVar3);
            aVar3.I(color);
            x3.a aVar4 = this.f5242d1;
            l.c(aVar4);
            aVar4.L(f7);
            if (drawable != null) {
                x3.a aVar5 = this.f5242d1;
                l.c(aVar5);
                aVar5.K(drawable);
            }
            x3.a aVar6 = this.f5242d1;
            l.c(aVar6);
            aVar6.J(integer3);
            x3.a aVar7 = this.f5242d1;
            l.c(aVar7);
            aVar7.E(z6);
            N1();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    private final void L1() {
        b bVar = this.f5245g1;
        int i7 = bVar == null ? -1 : c.f5250a[bVar.ordinal()];
        if (i7 == 1) {
            final Context context = getContext();
            this.f5243e1 = new LinearLayoutManager(context) { // from class: com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView$initShimmerManager$1
                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
                public boolean l() {
                    return this.I.f5246h1;
                }
            };
        } else if (i7 == 2) {
            final Context context2 = getContext();
            this.f5243e1 = new LinearLayoutManager(context2) { // from class: com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView$initShimmerManager$2
                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
                public boolean k() {
                    return this.I.f5246h1;
                }
            };
        } else {
            if (i7 != 3) {
                return;
            }
            final Context context3 = getContext();
            final int i8 = this.f5248j1;
            this.f5243e1 = new GridLayoutManager(context3, i8) { // from class: com.vivid.spotify.common.ui.shimmer.ShimmerRecyclerView$initShimmerManager$3
                @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
                public boolean l() {
                    return this.R.f5246h1;
                }
            };
        }
    }

    public static final void M1(ShimmerRecyclerView shimmerRecyclerView, List<? extends MediaBrowserCompat.MediaItem> list) {
        f5240k1.a(shimmerRecyclerView, list);
    }

    public static /* synthetic */ void getShimmerAdapter$annotations() {
    }

    public final void J1() {
        if (getLayoutManager() == this.f5244f1 && getAdapter() == this.f5241c1) {
            return;
        }
        this.f5246h1 = true;
        setLayoutManager(this.f5244f1);
        setAdapter(this.f5241c1);
    }

    public final void N1() {
        if (l.a(getLayoutManager(), this.f5243e1) && l.a(getAdapter(), this.f5242d1)) {
            return;
        }
        this.f5246h1 = false;
        if (this.f5243e1 == null) {
            L1();
        }
        setLayoutManager(this.f5243e1);
        setAdapter(this.f5242d1);
    }

    public final RecyclerView.g<?> getActualAdapter() {
        return this.f5241c1;
    }

    public final int getLayoutReference() {
        return this.f5247i1;
    }

    public final RecyclerView.g<?> getShimmerAdapter() {
        return this.f5242d1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView, androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.g<?> gVar) {
        if (gVar == null) {
            this.f5241c1 = null;
        } else if (gVar != this.f5242d1) {
            this.f5241c1 = gVar;
        }
        super.setAdapter(gVar);
    }

    public final void setDemoChildCount(int i7) {
        x3.a aVar = this.f5242d1;
        l.c(aVar);
        aVar.G(i7);
    }

    public final void setDemoLayoutManager(b type) {
        l.e(type, "type");
        if (type != this.f5245g1) {
            this.f5245g1 = type;
            if (type == b.GRID) {
                setDemoChildCount(16);
            } else {
                setDemoChildCount(10);
            }
            L1();
        }
    }

    public final void setDemoLayoutReference(int i7) {
        this.f5247i1 = i7;
        x3.a aVar = this.f5242d1;
        l.c(aVar);
        aVar.F(this.f5247i1);
    }

    public final void setDemoShimmerDuration(int i7) {
        x3.a aVar = this.f5242d1;
        l.c(aVar);
        aVar.J(i7);
    }

    public final void setDemoShimmerMaskWidth(float f7) {
        x3.a aVar = this.f5242d1;
        l.c(aVar);
        aVar.L(f7);
    }

    public final void setGridChildCount(int i7) {
        this.f5248j1 = i7;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(RecyclerView.o oVar) {
        if (oVar == null) {
            this.f5244f1 = null;
        } else if (oVar != this.f5243e1) {
            this.f5244f1 = oVar;
        }
        super.setLayoutManager(oVar);
    }
}
