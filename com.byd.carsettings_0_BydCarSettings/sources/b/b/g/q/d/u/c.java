package b.b.g.q.d.u;

import android.app.Activity;
import b.b.g.q.f.b.i;
import b.b.g.q.f.b.j;
import b.b.g.q.f.b.o;
import b.b.g.q.f.b.p;
import b.b.g.q.f.b.q;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: SoundPageDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends b {
    public static void q(Activity activity, b.b.m.f.f.b.a aVar) {
        if (activity == null) {
            return;
        }
        i iVar = new i(activity);
        iVar.setOnRadioBtnChekedCallback(aVar);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_640);
        int dimension2 = (int) activity.getResources().getDimension(R.dimen.dp_544);
        b.b.m.c.a.a("SoundPageDialog", "---showDiracEqualizer---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(activity, iVar, dimension, dimension2);
    }

    public static void r(Activity activity) {
        if (activity == null) {
            return;
        }
        j jVar = new j(activity);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) activity.getResources().getDimension(R.dimen.dp_544);
        b.b.m.c.a.a("SoundPageDialog", "---showDiracSoundField---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(activity, jVar, dimension, dimension2);
    }

    public static void s(Activity activity, String str) {
        if (activity == null) {
            return;
        }
        p pVar = new p(activity);
        pVar.setTitleText(str);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) activity.getResources().getDimension(R.dimen.dp_544);
        b.b.m.c.a.a("SoundPageDialog", "---showHamanEqualizer---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(activity, pVar, dimension, dimension2);
    }

    public static void t(Activity activity, int i, b.b.g.o.g.a aVar) {
        if (activity == null) {
            return;
        }
        o oVar = new o(activity);
        oVar.f(i, aVar);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) activity.getResources().getDimension(R.dimen.dp_544);
        b.b.m.c.a.a("SoundPageDialog", "---showHamanSoundBalance---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(activity, oVar, dimension, dimension2);
    }

    public static void u(Activity activity, String str) {
        if (activity == null) {
            return;
        }
        q qVar = new q(activity);
        qVar.setTitle(str);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) activity.getResources().getDimension(R.dimen.dp_544);
        b.b.m.c.a.a("SoundPageDialog", "---showHamanSoundFade---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(activity, qVar, dimension, dimension2);
    }
}
