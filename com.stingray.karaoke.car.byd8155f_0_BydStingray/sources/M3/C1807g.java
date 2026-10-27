package m3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.stingray.drivemodedetector.R;
import e2.W;
import e2.g0;
import e2.h0;
import e2.k0;
import e2.l0;
import java.util.ArrayList;
import java.util.List;
import s3.AbstractC2405y;
import s3.U;

/* JADX INFO: renamed from: m3.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1807g extends AbstractC2405y {
    public List d = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ s f19057e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f19058f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ s f19059g;

    public C1807g(s sVar, int i7) {
        this.f19058f = i7;
        this.f19059g = sVar;
        this.f19057e = sVar;
    }

    private final void i(String str) {
    }

    @Override // s3.AbstractC2405y
    public final int a() {
        if (this.d.isEmpty()) {
            return 0;
        }
        return this.d.size() + 1;
    }

    @Override // s3.AbstractC2405y
    public /* bridge */ /* synthetic */ void c(U u4, int i7) {
        switch (this.f19058f) {
            case 1:
                g((o) u4, i7);
                break;
            default:
                g((o) u4, i7);
                break;
        }
    }

    @Override // s3.AbstractC2405y
    public final U d(ViewGroup viewGroup) {
        return new o(LayoutInflater.from(this.f19057e.getContext()).inflate(R.layout.exo_styled_sub_settings_list_item, viewGroup, false));
    }

    public boolean e(l0 l0Var) {
        for (int i7 = 0; i7 < this.d.size(); i7++) {
            if (l0Var.f14194A.containsKey(((p) this.d.get(i7)).f19073a.f14222b)) {
                return true;
            }
        }
        return false;
    }

    public void f(List list) {
        boolean z6 = false;
        int i7 = 0;
        while (true) {
            if (i7 >= list.size()) {
                break;
            }
            p pVar = (p) list.get(i7);
            if (pVar.f19073a.f14223e[pVar.f19074b]) {
                z6 = true;
                break;
            }
            i7++;
        }
        s sVar = this.f19059g;
        ImageView imageView = sVar.f19089H;
        if (imageView != null) {
            imageView.setImageDrawable(z6 ? sVar.f19122m0 : sVar.f19124n0);
            sVar.f19089H.setContentDescription(z6 ? sVar.f19125o0 : sVar.f19127p0);
        }
        this.d = list;
    }

    public void g(o oVar, int i7) {
        switch (this.f19058f) {
            case 1:
                h(oVar, i7);
                if (i7 > 0) {
                    p pVar = (p) this.d.get(i7 - 1);
                    oVar.f19072v.setVisibility(pVar.f19073a.f14223e[pVar.f19074b] ? 0 : 4);
                }
                break;
            default:
                h(oVar, i7);
                break;
        }
    }

    public final void h(o oVar, int i7) {
        final W w10 = this.f19057e.f19137u0;
        if (w10 == null) {
        }
        if (i7 != 0) {
            final p pVar = (p) this.d.get(i7 - 1);
            final g0 g0Var = pVar.f19073a.f14222b;
            boolean z6 = w10.y0().f14194A.get(g0Var) != null && pVar.f19073a.f14223e[pVar.f19074b];
            oVar.f19071u.setText(pVar.c);
            oVar.f19072v.setVisibility(z6 ? 0 : 4);
            oVar.f22219a.setOnClickListener(new View.OnClickListener() { // from class: m3.q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    C1807g c1807g = this.l;
                    c1807g.getClass();
                    W w11 = w10;
                    if (w11.R0(29)) {
                        k0 k0VarA = w11.y0().a();
                        p pVar2 = pVar;
                        w11.f0(k0VarA.h(new h0(g0Var, d5.I.B(Integer.valueOf(pVar2.f19074b)))).l(pVar2.f19073a.f14222b.c).b());
                        String str = pVar2.c;
                        switch (c1807g.f19058f) {
                            case 0:
                                c1807g.f19059g.f19128q.f19068e[1] = str;
                                break;
                        }
                        c1807g.f19057e.f19138v.dismiss();
                    }
                }
            });
            return;
        }
        switch (this.f19058f) {
            case 0:
                oVar.f19071u.setText(R.string.exo_track_selection_auto);
                W w11 = this.f19059g.f19137u0;
                w11.getClass();
                oVar.f19072v.setVisibility(e(w11.y0()) ? 4 : 0);
                oVar.f22219a.setOnClickListener(new K6.e(this, 2));
                break;
            default:
                oVar.f19071u.setText(R.string.exo_track_selection_none);
                int i10 = 0;
                int i11 = 0;
                while (true) {
                    if (i11 < this.d.size()) {
                        p pVar2 = (p) this.d.get(i11);
                        if (pVar2.f19073a.f14223e[pVar2.f19074b]) {
                            i10 = 4;
                        } else {
                            i11++;
                        }
                    }
                }
                oVar.f19072v.setVisibility(i10);
                oVar.f22219a.setOnClickListener(new K6.e(this, 4));
                break;
        }
    }
}
