package b.d.a.a.v;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.Scroller;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class f<S> extends t<S> {
    public static final Object g0 = "MONTHS_VIEW_GROUP_TAG";
    public static final Object h0 = "NAVIGATION_PREV_TAG";
    public static final Object i0 = "NAVIGATION_NEXT_TAG";
    public static final Object j0 = "SELECTOR_TOGGLE_TAG";
    public int W;
    public b.d.a.a.v.d<S> X;
    public b.d.a.a.v.a Y;
    public o Z;
    public e a0;
    public b.d.a.a.v.c b0;
    public RecyclerView c0;
    public RecyclerView d0;
    public View e0;
    public View f0;

    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2086a;

        public a(int i) {
            this.f2086a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            f.this.d0.n0(this.f2086a);
        }
    }

    public class b extends a.h.k.a {
        public b(f fVar) {
        }

        @Override // a.h.k.a
        public void d(View view, a.h.k.x.b bVar) {
            this.f807a.onInitializeAccessibilityNodeInfo(view, bVar.f856a);
            bVar.h(null);
        }
    }

    public class c extends u {
        public final /* synthetic */ int H;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Context context, int i, boolean z, int i2) {
            super(context, i, z);
            this.H = i2;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void Z0(RecyclerView.y yVar, int[] iArr) {
            if (this.H == 0) {
                iArr[0] = f.this.d0.getWidth();
                iArr[1] = f.this.d0.getWidth();
            } else {
                iArr[0] = f.this.d0.getHeight();
                iArr[1] = f.this.d0.getHeight();
            }
        }
    }

    public class d implements InterfaceC0044f {
        public d() {
        }
    }

    public enum e {
        DAY,
        YEAR
    }

    /* JADX INFO: renamed from: b.d.a.a.v.f$f, reason: collision with other inner class name */
    public interface InterfaceC0044f {
    }

    public static int P(Context context) {
        return context.getResources().getDimensionPixelSize(b.d.a.a.d.mtrl_calendar_day_height);
    }

    @Override // androidx.fragment.app.Fragment
    public void B(Bundle bundle) {
        bundle.putInt("THEME_RES_ID_KEY", this.W);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.X);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.Y);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.Z);
    }

    public LinearLayoutManager Q() {
        return (LinearLayoutManager) this.d0.getLayoutManager();
    }

    public final void R(int i) {
        this.d0.post(new a(i));
    }

    public void S(o oVar) {
        RecyclerView recyclerView;
        int i;
        r rVar = (r) this.d0.getAdapter();
        int iJ = rVar.f2119d.f2065a.j(oVar);
        int iK = iJ - rVar.k(this.Z);
        boolean z = Math.abs(iK) > 3;
        boolean z2 = iK > 0;
        this.Z = oVar;
        if (!z || !z2) {
            if (z) {
                recyclerView = this.d0;
                i = iJ + 3;
            }
            R(iJ);
        }
        recyclerView = this.d0;
        i = iJ - 3;
        recyclerView.k0(i);
        R(iJ);
    }

    public void T(e eVar) {
        this.a0 = eVar;
        if (eVar == e.YEAR) {
            this.c0.getLayoutManager().M0(((y) this.c0.getAdapter()).j(this.Z.f2106c));
            this.e0.setVisibility(0);
            this.f0.setVisibility(8);
        } else if (eVar == e.DAY) {
            this.e0.setVisibility(8);
            this.f0.setVisibility(0);
            S(this.Z);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void y(Bundle bundle) {
        super.y(bundle);
        if (bundle == null) {
            bundle = this.f1558f;
        }
        this.W = bundle.getInt("THEME_RES_ID_KEY");
        this.X = (b.d.a.a.v.d) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.Y = (b.d.a.a.v.a) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.Z = (o) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    public View z(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2;
        a.p.e.y yVar;
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(l(), this.W);
        this.b0 = new b.d.a.a.v.c(contextThemeWrapper);
        LayoutInflater layoutInflaterCloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        o oVar = this.Y.f2065a;
        if (n.P(contextThemeWrapper)) {
            i = b.d.a.a.h.mtrl_calendar_vertical;
            i2 = 1;
        } else {
            i = b.d.a.a.h.mtrl_calendar_horizontal;
            i2 = 0;
        }
        View viewInflate = layoutInflaterCloneInContext.inflate(i, viewGroup, false);
        GridView gridView = (GridView) viewInflate.findViewById(b.d.a.a.f.mtrl_calendar_days_of_week);
        a.h.k.q.U(gridView, new b(this));
        gridView.setAdapter((ListAdapter) new b.d.a.a.v.e());
        gridView.setNumColumns(oVar.f2107d);
        gridView.setEnabled(false);
        this.d0 = (RecyclerView) viewInflate.findViewById(b.d.a.a.f.mtrl_calendar_months);
        this.d0.setLayoutManager(new c(l(), i2, false, i2));
        this.d0.setTag("MONTHS_VIEW_GROUP_TAG");
        r rVar = new r(contextThemeWrapper, this.X, this.Y, new d());
        this.d0.setAdapter(rVar);
        int integer = contextThemeWrapper.getResources().getInteger(b.d.a.a.g.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView3 = (RecyclerView) viewInflate.findViewById(b.d.a.a.f.mtrl_calendar_year_selector_frame);
        this.c0 = recyclerView3;
        if (recyclerView3 != null) {
            recyclerView3.setHasFixedSize(true);
            this.c0.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.c0.setAdapter(new y(this));
            this.c0.g(new g(this));
        }
        if (viewInflate.findViewById(b.d.a.a.f.month_navigation_fragment_toggle) != null) {
            MaterialButton materialButton = (MaterialButton) viewInflate.findViewById(b.d.a.a.f.month_navigation_fragment_toggle);
            materialButton.setTag("SELECTOR_TOGGLE_TAG");
            a.h.k.q.U(materialButton, new h(this));
            MaterialButton materialButton2 = (MaterialButton) viewInflate.findViewById(b.d.a.a.f.month_navigation_previous);
            materialButton2.setTag("NAVIGATION_PREV_TAG");
            MaterialButton materialButton3 = (MaterialButton) viewInflate.findViewById(b.d.a.a.f.month_navigation_next);
            materialButton3.setTag("NAVIGATION_NEXT_TAG");
            this.e0 = viewInflate.findViewById(b.d.a.a.f.mtrl_calendar_year_selector_frame);
            this.f0 = viewInflate.findViewById(b.d.a.a.f.mtrl_calendar_day_selector_frame);
            T(e.DAY);
            materialButton.setText(this.Z.h(viewInflate.getContext()));
            this.d0.h(new i(this, rVar, materialButton));
            materialButton.setOnClickListener(new j(this));
            materialButton3.setOnClickListener(new k(this, rVar));
            materialButton2.setOnClickListener(new l(this, rVar));
        }
        if (!n.P(contextThemeWrapper) && (recyclerView2 = (yVar = new a.p.e.y()).f1151a) != (recyclerView = this.d0)) {
            if (recyclerView2 != null) {
                RecyclerView.r rVar2 = yVar.f1152b;
                List<RecyclerView.r> list = recyclerView2.g0;
                if (list != null) {
                    list.remove(rVar2);
                }
                yVar.f1151a.setOnFlingListener(null);
            }
            yVar.f1151a = recyclerView;
            if (recyclerView != null) {
                if (recyclerView.getOnFlingListener() != null) {
                    throw new IllegalStateException("An instance of OnFlingListener already set.");
                }
                yVar.f1151a.h(yVar.f1152b);
                yVar.f1151a.setOnFlingListener(yVar);
                new Scroller(yVar.f1151a.getContext(), new DecelerateInterpolator());
                yVar.b();
            }
        }
        this.d0.k0(rVar.k(this.Z));
        return viewInflate;
    }
}
