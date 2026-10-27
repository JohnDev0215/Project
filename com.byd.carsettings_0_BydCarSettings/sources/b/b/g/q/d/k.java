package b.b.g.q.d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: compiled from: CarSettingDialogUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static List<String> f1463a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static HashMap<String, Integer> f1464b = new HashMap<>();

    public static void a(String str) {
        if (f1463a.contains(str)) {
            return;
        }
        f1463a.add(str);
    }

    public static List<String> b() {
        return f1463a;
    }

    public static int c() {
        return f1463a.size();
    }

    public static void d() {
        List<String> list = f1463a;
        if (list != null) {
            list.clear();
        }
    }

    public static void e(String str) {
        if (f1463a.contains(str)) {
            f1463a.remove(str);
        }
    }
}
