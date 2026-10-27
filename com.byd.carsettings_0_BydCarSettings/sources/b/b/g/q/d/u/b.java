package b.b.g.q.d.u;

import android.app.Activity;
import android.content.Context;
import b.b.g.q.f.b.g;
import b.b.g.q.f.b.r;
import b.b.g.q.f.b.s;
import b.b.g.q.f.b.t;
import b.b.m.f.d.d;
import com.byd.carsettings.R;

/* JADX INFO: compiled from: DialogHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends b.b.m.f.d.d {
    public static b.b.m.f.d.b k(Context context, String str, String str2, String str3, d.a aVar) {
        if (context == null) {
            return null;
        }
        g gVar = new g(context);
        gVar.setTitleText(str);
        gVar.e(str2);
        gVar.c(str3);
        gVar.setOnOKClickedListener(aVar);
        int dimension = (int) context.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) context.getResources().getDimension(R.dimen.dp_312);
        b.b.m.c.a.a("DialogHelper", "---showBtnLeftHighlight---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(context, gVar, dimension, dimension2);
        return b.b.m.f.d.d.f(g.class);
    }

    public static void l(Activity activity, String str, String str2, d.a aVar) {
        if (activity == null) {
            return;
        }
        g gVar = new g(activity);
        gVar.setOnOKClickedListener(aVar);
        gVar.setTitleText(str);
        gVar.e(str2);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) activity.getResources().getDimension(R.dimen.dp_312);
        b.b.m.c.a.a("DialogHelper", "---showBtnRightHighlight---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(activity, gVar, dimension, dimension2);
    }

    public static void m(Activity activity, String str, int i, int i2, String... strArr) {
        if (activity == null) {
            return;
        }
        s sVar = new s(activity);
        sVar.setTitleText(str);
        sVar.b(i, i2, strArr);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) activity.getResources().getDimension(R.dimen.dp_384);
        b.b.m.c.a.a("DialogHelper", "---showHint---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(activity, sVar, dimension, dimension2);
    }

    public static void n(Activity activity, String str, String str2) {
        if (activity == null) {
            return;
        }
        r rVar = new r(activity);
        rVar.setTitleText(str);
        rVar.c(str2);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_560);
        b.b.m.c.a.a("DialogHelper", "---showHintBtnCenter---width = " + dimension + ", height = -2");
        b.b.m.f.d.d.i(activity, rVar, dimension, -2);
    }

    public static void o(Activity activity, String str) {
        if (activity == null) {
            return;
        }
        t tVar = new t(activity);
        tVar.b(str);
        int dimension = (int) activity.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) activity.getResources().getDimension(R.dimen.dp_312);
        b.b.m.c.a.a("DialogHelper", "---showLoading---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.i(activity, tVar, dimension, dimension2);
    }

    public static void p(Context context, String str) {
        if (context == null) {
            return;
        }
        t tVar = new t(context);
        tVar.b(str);
        int dimension = (int) context.getResources().getDimension(R.dimen.dp_560);
        int dimension2 = (int) context.getResources().getDimension(R.dimen.dp_312);
        b.b.m.c.a.a("DialogHelper", "---showLoadingNoClickRespond---width = " + dimension + ", height = " + dimension2);
        b.b.m.f.d.d.j(context, tVar, dimension, dimension2);
    }
}
