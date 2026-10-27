package b.b.g.q.d.u;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.activity.NotificationAppListActivity;
import com.byd.mvp.ui.adapter.BydAdapter;

/* JADX INFO: compiled from: AppManagePageDialog.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends b {
    public static void q(Activity activity, BydAdapter bydAdapter) {
        if (activity == null || bydAdapter == null) {
            return;
        }
        b.b.g.q.f.b.a aVar = new b.b.g.q.f.b.a(activity);
        aVar.b(bydAdapter);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) activity.getResources().getDimension(R.dimen.dp_544);
        b.b.m.c.a.a("AppManagePageDialog", "---showList---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(activity, aVar, dimension, dimension2);
    }

    public static void r(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) NotificationAppListActivity.class);
        if (context instanceof Activity) {
            intent.setFlags(536870912);
        } else {
            intent.setFlags(805306368);
        }
        context.startActivity(intent);
    }
}
