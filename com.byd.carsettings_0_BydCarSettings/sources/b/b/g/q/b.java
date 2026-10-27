package b.b.g.q;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import androidx.appcompat.widget.ActivityChooserModel;
import b.b.a.f.h;
import b.b.g.r.k;
import b.b.g.r.p;
import b.b.g.r.r;
import b.b.i.c;
import b.b.i.d;
import com.byd.carsettings.BydApp;
import com.byd.carsettings.MainActivity;
import com.byd.carsettings.db.model.FunctionItem;
import com.byd.carsettings.ui.activity.ContainerActivity;
import com.byd.carsettings.ui.fragment.DiLinkFragment;
import com.byd.mvp.mvp.NavigationBarClickReceiver;
import com.byd.systemsettings.BydSettingsApi;
import com.bydux.api.common.tools.util.ListUtils;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: PageSelector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f1427a = {"com.byd.secretcode"};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f1428b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile boolean f1429c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public volatile boolean f1430d;

    /* JADX INFO: compiled from: PageSelector.java */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.b.m.e.i.a.o(false);
            b.b.m.c.a.a("PageSelector", "backToMainActivity", "reset search state.");
        }
    }

    /* JADX INFO: renamed from: b.b.g.q.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: PageSelector.java */
    public static class C0065b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f1432a = new b(null);

        public static b a() {
            return f1432a;
        }
    }

    public /* synthetic */ b(a aVar) {
        this();
    }

    public void a(Activity activity, FunctionItem functionItem) {
        boolean z = false;
        b.b.m.c.a.a("PageSelector", "backToMainActivity", "item: " + functionItem);
        Intent intent = new Intent(activity, (Class<?>) MainActivity.class);
        if (functionItem != null) {
            intent.putExtra("search_intdex_0", functionItem.getIndex0());
            intent.putExtra("search_intdex_1", functionItem.getIndex1());
            intent.putExtra("search_intdex_2", functionItem.getIndex2());
            intent.putExtra("search_intdex_3", functionItem.getIndex3());
            z = true;
        }
        intent.putExtra("do_search", z);
        activity.setResult(86, intent);
        activity.finish();
        if (z) {
            b.b.m.e.i.a.o(true);
            b.b.m.c.a.a("PageSelector", "backToMainActivity", "set doing search");
            h.a().e(new a(), 1000L);
        }
    }

    public int b(Activity activity, Intent intent) {
        int iO;
        if (activity == null || intent == null) {
            return -1;
        }
        if (intent.getBooleanExtra("do_search", false)) {
            intent.putExtra("do_search", false);
            return -1;
        }
        String action = intent.getAction();
        b.b.m.c.a.d("PageSelector", "doFormMainActivity", "---action = " + action + ", in = " + intent);
        if (!"action_main".equals(action)) {
            p.a(intent);
            b.b.m.c.a.d("PageSelector", "doFormMainActivity", "---action: " + action + ", pageIndexesInvalid: " + this.f1430d);
            if (!this.f1430d) {
                return -1;
            }
            this.f1430d = false;
            return 0;
        }
        String stringExtra = intent.getStringExtra("main_from");
        stringExtra.hashCode();
        switch (stringExtra) {
            case "system_ui_app":
            case "ac":
                iO = 3;
                break;
            case "DialogService":
                iO = o(intent.getIntExtra("main_intdex_0", -1), intent.getIntExtra("main_intdex_1", -1), intent.getIntExtra("main_intdex_2", -1), intent.getIntExtra("main_intdex_3", -1));
                break;
            default:
                iO = n(activity, intent);
                break;
        }
        return iO;
    }

    public int c(int i, Intent intent, Intent intent2) {
        b.b.m.c.a.d("PageSelector", "doFormMainActivityResult", ", requestCode: " + i + ", in");
        if (i == 86 && intent != null) {
            int intExtra = intent.getIntExtra("search_intdex_0", -1);
            int intExtra2 = intent.getIntExtra("search_intdex_1", -1);
            int intExtra3 = intent.getIntExtra("search_intdex_2", -1);
            int intExtra4 = intent.getIntExtra("search_intdex_3", -1);
            b.b.m.c.a.g("PageSelector", "doFormMainActivityResult", ", indexArr: " + intExtra + ListUtils.DEFAULT_JOIN_SEPARATOR + intExtra2 + ListUtils.DEFAULT_JOIN_SEPARATOR + intExtra3 + ListUtils.DEFAULT_JOIN_SEPARATOR + intExtra4);
            boolean booleanExtra = intent.getBooleanExtra("do_search", false);
            StringBuilder sb = new StringBuilder();
            sb.append(", doSearch: ");
            sb.append(booleanExtra);
            b.b.m.c.a.g("PageSelector", "doFormMainActivityResult", sb.toString());
            if (intent2 != null) {
                intent2.putExtra("do_search", booleanExtra);
            }
            if (booleanExtra) {
                return p(intExtra, intExtra2, intExtra3, intExtra4, null, null);
            }
        }
        return -1;
    }

    public void d(Service service, Intent intent) {
        if (service == null || intent == null || intent.getAction() == null) {
            return;
        }
        k.c(service);
        if (!b.b.m.g.h.f()) {
            b.b.m.g.h.g(new b.b.g.r.h());
        }
        b.b.m.g.h.i(service);
        String action = intent.getAction();
        b.b.m.c.a.a("PageSelector", "doFormService", ", action: " + action);
        action.hashCode();
        if (action.equals("android.intent.action.VIEW")) {
            g(service, intent);
        } else if (action.equals("action_home_key_event")) {
            c.d();
        }
    }

    public final void e(Context context, Uri uri, String str) {
        b.b.m.c.a.g("PageSelector", "doHighLevelAction", ",  action: " + str);
        str.hashCode();
        switch (str) {
            case "screen_saver":
                d.F(context);
                break;
            case "restore_factory_settings":
                String queryParameter = uri.getQueryParameter("pkg");
                b.b.m.c.a.g("PageSelector", "doHighLevelAction", "---RESTORE_FACTORY_SETTINGS---pkgName: " + queryParameter);
                if (w(queryParameter)) {
                    d.t(context);
                    break;
                }
                break;
            case "finish_screen_saver":
                d.q();
                break;
            case "dismiss_all_dialog":
                c.d();
                break;
            case "dismiss_hotspot":
                d.p();
                break;
        }
    }

    public final void f(Service service, Uri uri, String str) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        b.b.m.c.a.g("PageSelector", "doNormalAction", ", scheme: " + uri.getScheme() + ", action: " + str);
        str.hashCode();
        byte b2 = 9;
        switch (str) {
            case "adas_lane_assist":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 2, 1, 11, 0);
                break;
            case "cellular_data":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                q(service, NavigationBarClickReceiver.f3701b);
                t(service, 1, 0, 0, 0);
                break;
            case "dilink_version_manage":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 1, 4, 0, 0);
                break;
            case "Hotspot":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                d.D(service);
                break;
            case "new_electricity":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 3, 1, 0, 0);
                break;
            case "notification_app_list":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                b.b.g.q.d.u.a.r(service);
                break;
            case "trailer":
            case "hud":
            case "seat":
            case "cockpit":
            case "AirConditioner":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                j(service, 3, uri, str);
                break;
            case "self_start":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                v(service);
                break;
            case "carhealthy_message":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 5, 2, 0, 0);
                break;
            case "Bluetooth":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                d.y(b.b.a.a.a.b().a());
                break;
            case "car_play":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                d.B(service);
                break;
            case "Main":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                u(service, uri);
                break;
            case "WiFi":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                d.H(service);
                break;
            case "sys_language_settings":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                s(service, str);
                break;
            case "new_energy":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 3, 0, 0, 0);
                break;
            case "sound_settings":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 1, 1, 0, 0);
                break;
            case "car_setting":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 4, 0, 0, 0);
                break;
            case "new_consumption":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 3, 2, 0, 0);
                break;
            case "maintenance_oil":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 5, 0, 0, 100);
                break;
            case "dilink_setting":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 1, 0, 0, 0);
                break;
            case "maintenance_overhaul":
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                t(service, 5, 1, 0, 0);
                break;
            default:
                obj = "trailer";
                obj2 = "hud";
                obj3 = "seat";
                obj4 = "new_energy";
                break;
        }
        switch (str.hashCode()) {
            case -1901256353:
                b2 = !str.equals(BydSettingsApi.CELLULAR_DATA) ? (byte) -1 : (byte) 0;
                break;
            case -1067215565:
                b2 = !str.equals(obj) ? (byte) -1 : (byte) 1;
                break;
            case 103671:
                b2 = !str.equals(obj2) ? (byte) -1 : (byte) 2;
                break;
            case 3526149:
                b2 = !str.equals(obj3) ? (byte) -1 : (byte) 3;
                break;
            case 339833351:
                b2 = !str.equals(obj4) ? (byte) -1 : (byte) 4;
                break;
            case 662627667:
                b2 = !str.equals(BydSettingsApi.SOUND_SETTINGS) ? (byte) -1 : (byte) 5;
                break;
            case 766320869:
                b2 = !str.equals(BydSettingsApi.CAR_SETTING) ? (byte) -1 : (byte) 6;
                break;
            case 941114183:
                b2 = !str.equals("cockpit") ? (byte) -1 : (byte) 7;
                break;
            case 1167443838:
                b2 = !str.equals("AirConditioner") ? (byte) -1 : (byte) 8;
                break;
            case 1379350544:
                if (!str.equals(BydSettingsApi.DILINK_SETTING)) {
                    b2 = -1;
                }
                break;
            default:
                b2 = -1;
                break;
        }
        switch (b2) {
            case 0:
            case 4:
            case 5:
            case 6:
            case 9:
                r(0);
                break;
            case 1:
                r(4);
                break;
            case 2:
                r(3);
                break;
            case 3:
                r(2);
                break;
            case 7:
                r(5);
                break;
            case 8:
                r(1);
                break;
        }
    }

    public final void g(Service service, Intent intent) {
        if (intent.getData() == null) {
            return;
        }
        Uri data = intent.getData();
        String strI = i(intent);
        b.b.m.c.a.g("PageSelector", "doSchemeFormService", ", scheme: " + data.getScheme() + ", action: " + strI);
        e(service, data, strI);
        f(service, data, strI);
    }

    public final int h(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e2) {
            b.b.m.c.a.c("PageSelector", "e:" + e2.toString());
            return 0;
        }
    }

    public String i(Intent intent) {
        if (intent.getData() == null) {
            return "";
        }
        Uri data = intent.getData();
        String queryParameter = data.getQueryParameter("action");
        if (TextUtils.isEmpty(queryParameter)) {
            queryParameter = data.getQueryParameter(ActivityChooserModel.ATTRIBUTE_ACTIVITY);
        }
        return TextUtils.isEmpty(queryParameter) ? data.getQueryParameter("system") : queryParameter;
    }

    public final void j(Service service, int i, Uri uri, String str) {
        Intent intent = new Intent(service, (Class<?>) MainActivity.class);
        intent.setAction("action_main");
        if ("AirConditioner".equals(str)) {
            intent.putExtra("main_from", "ac");
        }
        if ("seat".equals(str)) {
            intent.putExtra("main_from", "system_ui_app");
            b.b.m.e.i.a.m("moduleAction", "massage");
        }
        if ("hud".equals(str)) {
            intent.putExtra("main_from", "system_ui_app");
        }
        if ("trailer".equals(str)) {
            intent.putExtra("main_from", "system_ui_app");
        }
        if ("cockpit".equals(str)) {
            intent.putExtra("main_from", "system_ui_app");
        }
        b.b.m.e.i.a.m("moduleKey", str);
        intent.setFlags(337641472);
        service.startActivity(intent);
    }

    public void k(Context context, boolean z) {
        this.f1429c = z;
        b.b.m.c.a.a("PageSelector", "----onCpaaDisplay----display: " + z);
        if (z) {
            d.n();
            d.o();
            d.r();
            d.p();
        }
    }

    public void l() {
        c.d();
    }

    public void m() {
        this.f1430d = false;
    }

    public final int n(Context context, Intent intent) {
        return 0;
    }

    public int o(int i, int i2, int i3, int i4) {
        return p(i, i2, i3, i4, null, null);
    }

    public int p(int i, int i2, int i3, int i4, String str, String str2) {
        b.b.m.c.a.g("PageSelector", "selectPage", ", i0: " + i + ", i1: " + i2 + ", i2: " + i3);
        StringBuilder sb = new StringBuilder();
        sb.append(", mKey: ");
        sb.append(str);
        sb.append(", mAction: ");
        sb.append(str2);
        b.b.m.c.a.g("PageSelector", "selectPage", sb.toString());
        b.b.g.j.a.e(BydApp.getContext().getApplicationContext(), i, i2, i3, i4);
        int iG = b.b.g.j.a.g(i, i2, i3, i4);
        int iB = b.b.g.j.a.b(i, i2, i3);
        int iD = b.b.g.j.a.d(i, i2);
        int iC = b.b.g.j.a.c(i);
        b.b.m.c.a.g("PageSelector", "selectPage", ", ---after--i0: " + iC + ", i1: " + iD + ", i2: " + iB);
        String strE = b.b.m.e.i.a.e(i);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(", key1: ");
        sb2.append(strE);
        b.b.m.c.a.g("PageSelector", "selectPage", sb2.toString());
        if (iC == 0 && ((iD == 0 || iD == 1) && TextUtils.isEmpty(strE))) {
            strE = DiLinkFragment.class.getName();
            b.b.m.c.a.g("PageSelector", "selectPage", ", fix key1: " + strE);
        }
        if (TextUtils.isEmpty(str)) {
            b.b.m.e.i.a.b();
        } else {
            b.b.m.e.i.a.m("moduleKey", str);
            b.b.m.e.i.a.m("moduleAction", str2);
        }
        r.f1829a = strE;
        if (i == 4) {
            b.b.m.e.i.a.k(strE, i2);
        } else if (i == 3) {
            b.b.g.n.n.d dVar = new b.b.g.n.n.d();
            dVar.g();
            boolean zI = dVar.i();
            b.b.m.c.a.d("PageSelector", "NewEnergyFragment---getSupportiTAC ---" + zI);
            if (zI) {
                iD = iD == 3 ? 0 : iD + 1;
            }
            b.b.m.e.i.a.k(strE, iD);
        } else {
            b.b.m.e.i.a.k(strE, iD);
        }
        String strE2 = b.b.m.e.i.a.e(i, i2);
        b.b.m.c.a.g("PageSelector", "selectPage", ", key2: " + strE2);
        if (i == 4 || i == 3 || i == 5 || i == 2) {
            b.b.m.e.i.a.k(strE2, i3);
        } else {
            b.b.m.e.i.a.k(strE2, iB);
        }
        r.f1830b = strE2;
        String strE3 = b.b.m.e.i.a.e(i, i2, i3);
        b.b.m.c.a.g("PageSelector", "selectPage", ", key3: " + strE3);
        b.b.m.e.i.a.k(strE3, iG);
        r.f1831c = strE3;
        return iC;
    }

    public final void q(Service service, String str) {
        Intent intent = new Intent(NavigationBarClickReceiver.f3700a);
        intent.putExtra("btn_value", str);
        service.sendBroadcast(intent);
    }

    public final void r(int i) {
        EventBus.getDefault().post(new b.b.a.j.b.b(i));
        b.b.m.f.d.d.b();
        b.b.g.q.d.k.d();
    }

    public final void s(Context context, String str) {
        b.b.m.c.a.a("PageSelector", "---startContainerActivity---action: " + str);
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) ContainerActivity.class);
        intent.setAction("action_main");
        intent.putExtra("action", str);
        intent.setFlags(337641472);
        context.startActivity(intent);
    }

    public final void t(Context context, int i, int i2, int i3, int i4) {
        b.b.m.c.a.a("PageSelector", "startMainActivity", "indexArr: " + i + ListUtils.DEFAULT_JOIN_SEPARATOR + i2 + ListUtils.DEFAULT_JOIN_SEPARATOR + i3 + ListUtils.DEFAULT_JOIN_SEPARATOR + i4);
        int i5 = context.getResources().getConfiguration().orientation;
        if (MainActivity.r != i5) {
            b.b.m.c.a.d("PageSelector", "startMainActivity", "The orientation is changed, and do finish MainActivity.", "curOrientation: " + i5, "oldOrientation: " + MainActivity.r);
            b.b.m.a.a.a(MainActivity.class.getName());
        }
        Intent intent = new Intent(context, (Class<?>) MainActivity.class);
        intent.setAction("action_main");
        intent.putExtra("main_from", "DialogService");
        intent.putExtra("main_intdex_0", i);
        intent.putExtra("main_intdex_1", i2);
        intent.putExtra("main_intdex_2", i3);
        intent.putExtra("main_intdex_3", i4);
        intent.setFlags(337641472);
        context.startActivity(intent);
    }

    public final void u(Context context, Uri uri) {
        String queryParameter = uri.getQueryParameter("module_index");
        String queryParameter2 = uri.getQueryParameter("page_index");
        String queryParameter3 = uri.getQueryParameter("function_index");
        String queryParameter4 = uri.getQueryParameter("sub_function_index");
        b.b.m.c.a.a("PageSelector", "startMainActivity", ", module: " + queryParameter + ", page: " + queryParameter2 + ", func: " + queryParameter3 + ", subfunc: " + queryParameter4);
        String queryParameter5 = uri.getQueryParameter("page_key");
        String queryParameter6 = uri.getQueryParameter("page_action");
        b.b.m.e.i.a.m("moduleKey", queryParameter5);
        b.b.m.e.i.a.m("moduleAction", queryParameter6);
        b.b.m.c.a.a("PageSelector", "startMainActivity", ", mKey: " + queryParameter5 + ", mAction: " + queryParameter6);
        b.b.m.c.a.a("PageSelector", "startMainActivity", ", indexArr: " + h(queryParameter) + ListUtils.DEFAULT_JOIN_SEPARATOR + h(queryParameter2) + ListUtils.DEFAULT_JOIN_SEPARATOR + h(queryParameter3) + ListUtils.DEFAULT_JOIN_SEPARATOR + h(queryParameter4));
        t(context, h(queryParameter), h(queryParameter2), h(queryParameter3), h(queryParameter4));
    }

    public final void v(Context context) {
    }

    public final boolean w(String str) {
        for (String str2 : f1427a) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public b() {
        this.f1428b = "adas_lane_assist";
        this.f1429c = false;
        this.f1430d = false;
    }
}
