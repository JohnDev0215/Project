package b.b.g.q.d.u;

import android.app.Activity;
import b.b.m.f.d.d;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: SystemPageDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends b {
    public static void q(Activity activity, d.a aVar) {
        if (activity == null) {
            return;
        }
        b.b.g.q.f.b.b bVar = new b.b.g.q.f.b.b(activity);
        bVar.setOnOKClickedListener(aVar);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) activity.getResources().getDimension(R.dimen.dp_312);
        b.b.m.c.a.a("SystemPageDialog", "---showSoundCharact---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(activity, bVar, dimension, dimension2);
    }
}
