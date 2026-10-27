package K6;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.stingray.drivemodedetector.R;
import e2.W;
import e2.l0;
import h2.AbstractC1343A;
import m3.C1807g;
import m3.C1813m;
import m3.s;
import m3.x;
import s3.AbstractC2405y;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements View.OnClickListener {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3696m;

    public /* synthetic */ e(Object obj, int i7) {
        this.l = i7;
        this.f3696m = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        RecyclerView recyclerView;
        AbstractC2405y adapter;
        int iD;
        Object obj = this.f3696m;
        switch (this.l) {
            case 0:
                ((InterfaceC2772a) obj).a();
                break;
            case 1:
                ((s) obj).k(!r2.f19141w0);
                break;
            case 2:
                s sVar = ((C1807g) obj).f19059g;
                W w10 = sVar.f19137u0;
                if (w10 != null && w10.R0(29)) {
                    l0 l0VarY0 = sVar.f19137u0.y0();
                    W w11 = sVar.f19137u0;
                    int i7 = AbstractC1343A.f15195a;
                    w11.f0(l0VarY0.a().d(1).l(1).b());
                    sVar.f19128q.f19068e[1] = sVar.getResources().getString(R.string.exo_track_selection_auto);
                    sVar.f19138v.dismiss();
                    break;
                }
                break;
            case 3:
                C1813m c1813m = (C1813m) obj;
                int i10 = -1;
                if (c1813m.f22233s != null && (recyclerView = c1813m.f22232r) != null && (adapter = recyclerView.getAdapter()) != null && (iD = c1813m.f22232r.D(c1813m)) != -1 && c1813m.f22233s == adapter) {
                    i10 = iD;
                }
                s sVar2 = c1813m.f19067x;
                View view2 = sVar2.f19095K;
                if (i10 == 0) {
                    view2.getClass();
                    sVar2.d(sVar2.f19130r, view2);
                } else if (i10 != 1) {
                    sVar2.f19138v.dismiss();
                } else {
                    view2.getClass();
                    sVar2.d(sVar2.f19134t, view2);
                }
                break;
            case 4:
                s sVar3 = ((C1807g) obj).f19059g;
                W w12 = sVar3.f19137u0;
                if (w12 != null && w12.R0(29)) {
                    sVar3.f19137u0.f0(sVar3.f19137u0.y0().a().d(3).g().i().k().b());
                    sVar3.f19138v.dismiss();
                    break;
                }
                break;
            default:
                x xVar = (x) obj;
                xVar.g();
                if (view.getId() == R.id.exo_overflow_show) {
                    xVar.f19169q.start();
                } else if (view.getId() == R.id.exo_overflow_hide) {
                    xVar.f19170r.start();
                }
                break;
        }
    }
}
