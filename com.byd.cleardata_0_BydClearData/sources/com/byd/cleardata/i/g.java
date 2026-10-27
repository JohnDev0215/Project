package com.byd.cleardata.i;

import android.content.Context;
import android.text.TextUtils;
import com.byd.cleardata.FileApplication;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final List<String> f2700a = d(FileApplication.a());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f2701b = 20;

    public static void a(String str) {
        c.b("addHistory value = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        List<String> list = f2700a;
        if (list.contains(str)) {
            return;
        }
        list.add(str);
        while (true) {
            List<String> list2 = f2700a;
            if (list2.size() <= f2701b) {
                return;
            } else {
                list2.remove(0);
            }
        }
    }

    public static void b() {
        f2700a.clear();
    }

    public static List<String> c() {
        return f2700a;
    }

    public static List<String> d(Context context) {
        String[] strArrSplit;
        ArrayList arrayList = new ArrayList();
        String strB = l.b(context, "history_list", null);
        if (!TextUtils.isEmpty(strB) && (strArrSplit = strB.split(",")) != null && strArrSplit.length > 0) {
            for (String str : strArrSplit) {
                if (!TextUtils.isEmpty(str)) {
                    arrayList.add(str);
                }
            }
        }
        return arrayList;
    }

    public static void e(Context context, String str, List<String> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(",");
        }
        l.d(context, str, sb.toString());
    }

    public static void f(String str) {
        c.b("addHistory value = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f2700a.remove(str);
        g();
    }

    public static void g() {
        List<String> list = f2700a;
        if (list.size() > 0) {
            e(FileApplication.a(), "history_list", list);
        } else {
            l.d(FileApplication.a(), "history_list", "");
        }
    }

    public static void h(int i) {
        if (i <= 0) {
            i = 20;
        }
        f2701b = i;
    }
}
