package x3;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView;
import com.vivid.spotify.v;
import h3.i;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.l;
import n2.d;

/* JADX INFO: compiled from: ShimmerAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a extends RecyclerView.g<C0171a> implements BaseRecyclerView.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f9827c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f9828d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f9829e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f9830f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f9831g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private float f9832h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f9833i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private Drawable f9834j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private boolean f9835k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private BaseRecyclerView f9836l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final CopyOnWriteArrayList<BaseRecyclerView.a> f9837m = new CopyOnWriteArrayList<>();

    /* JADX INFO: renamed from: x3.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ShimmerAdapter.kt */
    public final class C0171a extends RecyclerView.c0 implements BaseRecyclerView.a {

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        private final d f9838t;

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        final /* synthetic */ a f9839u;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0171a(a this$0, LayoutInflater inflater, ViewGroup parent, int i7) {
            super(inflater.inflate(v.viewholder_shimmer, parent, false));
            l.e(this$0, "this$0");
            l.e(inflater, "inflater");
            l.e(parent, "parent");
            this.f9839u = this$0;
            d dVar = (d) this.f2857a;
            this.f9838t = dVar;
            inflater.inflate(i7, (ViewGroup) dVar, true);
        }

        private final void O(Drawable drawable) {
            this.f9838t.setBackground(drawable);
        }

        public final void M() {
            this.f9838t.c();
        }

        public final d N() {
            return this.f9838t;
        }

        public final void P(Drawable drawable) {
            if (drawable != null) {
                O(drawable);
            }
        }

        @Override // com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView.a
        public void onConfigurationChanged(Configuration configuration) {
            try {
                ViewGroup.LayoutParams layoutParams = this.f9838t.getLayoutParams();
                BaseRecyclerView baseRecyclerViewB = this.f9839u.B();
                l.c(baseRecyclerViewB);
                layoutParams.width = baseRecyclerViewB.getItemWidth();
            } catch (Exception e7) {
                i.b("ShimmerViewHolder").e(e7, "setWidth error", new Object[0]);
            }
        }
    }

    public final BaseRecyclerView B() {
        return this.f9836l;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public void q(C0171a holder, int i7) {
        l.e(holder, "holder");
        holder.M();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    /* JADX INFO: renamed from: D, reason: merged with bridge method [inline-methods] */
    public C0171a s(ViewGroup parent, int i7) {
        l.e(parent, "parent");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        l.d(inflater, "inflater");
        C0171a c0171a = new C0171a(this, inflater, parent, this.f9828d);
        c0171a.P(this.f9834j);
        if (this.f9835k && this.f9836l != null) {
            ViewGroup.LayoutParams layoutParams = c0171a.N().getLayoutParams();
            BaseRecyclerView baseRecyclerView = this.f9836l;
            l.c(baseRecyclerView);
            layoutParams.width = baseRecyclerView.getItemWidth();
            this.f9837m.addIfAbsent(c0171a);
        }
        return c0171a;
    }

    public final void E(boolean z6) {
        this.f9833i = z6;
    }

    public final void F(int i7) {
        this.f9828d = i7;
    }

    public final void G(int i7) {
        this.f9827c = i7;
    }

    public final void H(int i7) {
        this.f9829e = i7;
    }

    public final void I(int i7) {
        this.f9830f = i7;
    }

    public final void J(int i7) {
        this.f9831g = i7;
    }

    public final void K(Drawable shimmerItemBackground) {
        l.e(shimmerItemBackground, "shimmerItemBackground");
        this.f9834j = shimmerItemBackground;
    }

    public final void L(float f7) {
        this.f9832h = f7;
    }

    public final void M(boolean z6) {
        this.f9835k = z6;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public int e() {
        return this.f9827c;
    }

    @Override // com.vivid.spotify.common.ui.recyclerview.BaseRecyclerView.a
    public void onConfigurationChanged(Configuration configuration) {
        Iterator<BaseRecyclerView.a> it = this.f9837m.iterator();
        while (it.hasNext()) {
            it.next().onConfigurationChanged(configuration);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void p(RecyclerView recyclerView) {
        l.e(recyclerView, "recyclerView");
        super.p(recyclerView);
        BaseRecyclerView baseRecyclerView = (BaseRecyclerView) recyclerView;
        this.f9836l = baseRecyclerView;
        l.c(baseRecyclerView);
        baseRecyclerView.z1(this);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.g
    public void t(RecyclerView recyclerView) {
        l.e(recyclerView, "recyclerView");
        super.t(recyclerView);
        BaseRecyclerView baseRecyclerView = this.f9836l;
        l.c(baseRecyclerView);
        baseRecyclerView.E1(this);
        this.f9836l = null;
    }
}
