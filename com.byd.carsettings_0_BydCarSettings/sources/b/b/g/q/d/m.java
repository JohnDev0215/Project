package b.b.g.q.d;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.byd.carsettings.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: DialogUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static List<Object> f1479a = new ArrayList(4);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static List<String> f1480b = new ArrayList(4);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static HashMap<String, Object> f1481c = new HashMap<>();

    public static boolean a(Object obj) {
        if (obj instanceof l) {
            ((l) obj).f();
            return true;
        }
        if (!(obj instanceof o)) {
            return false;
        }
        ((o) obj).b();
        return true;
    }

    public static void b() {
        int i = 0;
        while (i < f1479a.size()) {
            Object obj = f1479a.get(i);
            if (a(obj)) {
                f1479a.remove(obj);
                i--;
            }
            i++;
        }
    }

    public static l c(Context context, String str, String str2, String str3, int i, int i2, String str4, String str5, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, boolean z, boolean z2, View view) {
        l lVar = new l(context);
        lVar.x(str);
        lVar.z(str2);
        lVar.w(str3, i, i2);
        lVar.t(str4);
        lVar.r(str5);
        lVar.setBtnCancelListener(onClickListener2);
        lVar.setBtnOKListener(onClickListener);
        lVar.n(z);
        lVar.o(z2);
        lVar.A(view);
        if (!f1479a.contains(lVar)) {
            f1479a.add(lVar);
        }
        if (f1480b.contains(str)) {
            f1480b.remove(str);
        }
        n();
        return lVar;
    }

    public static l d(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.dialog_carhealthy_tpms, (ViewGroup) null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        viewInflate.setLayoutParams(layoutParams);
        return j(context, str, str2, str3, str4, onClickListener, onClickListener2, true, false, viewInflate);
    }

    public static l e(Context context, String str, String str2, View.OnClickListener onClickListener) {
        return c(context, str, context.getResources().getString(R.string.vehiclehealth_tips), str2, 17, context.getResources().getDimensionPixelSize(R.dimen.sp_20), context.getResources().getString(R.string.dialog_btn_ok), null, onClickListener, null, true, true, null);
    }

    public static l f(Context context, String str, String str2, String str3, int i, int i2, String str4, String str5, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        return c(context, str, str2, str3, i, i2, str4, str5, onClickListener, onClickListener2, true, true, null);
    }

    public static l g(Context context, String str, String str2, String str3, int i, String str4, String str5, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        return c(context, str, str2, str3, i, context.getResources().getDimensionPixelSize(R.dimen.sp_20), str4, str5, onClickListener, onClickListener2, true, true, null);
    }

    public static l h(Context context, String str, String str2, String str3, String str4, String str5, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        return c(context, str, str2, str3, 17, context.getResources().getDimensionPixelSize(R.dimen.sp_20), str4, str5, onClickListener, onClickListener2, true, true, null);
    }

    public static l i(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, boolean z, View view) {
        return c(context, str, str2, null, 17, context.getResources().getDimensionPixelSize(R.dimen.sp_20), str3, str4, onClickListener, onClickListener2, z, true, view);
    }

    public static l j(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, boolean z, boolean z2, View view) {
        return c(context, str, str2, null, 17, context.getResources().getDimensionPixelSize(R.dimen.sp_20), str3, str4, onClickListener, onClickListener2, z, z2, view);
    }

    public static o k(Context context, String str) {
        o oVar = new o(context);
        oVar.h(str);
        if (!f1479a.contains(oVar)) {
            f1479a.add(oVar);
        }
        if (f1480b.contains(str)) {
            f1480b.remove(str);
        }
        n();
        return oVar;
    }

    public static String l(Object obj) {
        return obj instanceof l ? ((l) obj).g() : obj instanceof o ? ((o) obj).c() : "";
    }

    public static boolean m(String str) {
        return f1480b.contains(str);
    }

    public static void n() {
        if (f1479a.size() > 4) {
            int i = 0;
            while (i < f1479a.size() - 4) {
                Object obj = f1479a.get(i);
                if (a(obj)) {
                    f1479a.remove(obj);
                    i--;
                }
                i++;
            }
        }
    }

    public static void o() {
        f1480b.clear();
        Iterator<Object> it = f1479a.iterator();
        while (it.hasNext()) {
            String strL = l(it.next());
            if (!TextUtils.isEmpty(strL)) {
                f1480b.add(strL);
            }
        }
    }
}
