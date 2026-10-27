package com.byd.filemanager.k;

import android.content.Context;
import android.text.TextUtils;
import com.byd.filemanager.FileApplication;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: HistoryUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ArrayList<String> f3614a = d(FileApplication.a());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f3615b = 20;

    public static void a(String str) {
        b.b("addHistory value = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList<String> arrayList = f3614a;
        if (!arrayList.contains(str)) {
            arrayList.add(0, str);
            while (true) {
                ArrayList<String> arrayList2 = f3614a;
                if (arrayList2.size() <= f3615b) {
                    break;
                } else {
                    arrayList2.remove(arrayList2.size() - 1);
                }
            }
        } else {
            arrayList.remove(str);
            arrayList.add(0, str);
        }
        g();
    }

    public static void b() {
        f3614a.clear();
        g();
    }

    public static List<String> c() {
        return f3614a;
    }

    public static ArrayList<String> d(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] strArrSplit = null;
        String strB = j.b(context, "history_list", null);
        b.b("value = " + strB);
        if (!TextUtils.isEmpty(strB)) {
            try {
                strArrSplit = strB.split(",");
            } catch (Throwable th) {
                b.h("split error", th);
            }
            if (strArrSplit != null && strArrSplit.length > 0) {
                for (String str : strArrSplit) {
                    if (!TextUtils.isEmpty(str)) {
                        arrayList.add(str);
                    }
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
        j.d(context, str, sb.toString());
    }

    public static void f(String str) {
        b.b("addHistory value = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        f3614a.remove(str);
        g();
    }

    public static void g() {
        ArrayList<String> arrayList = f3614a;
        if (arrayList.size() > 0) {
            e(FileApplication.a(), "history_list", arrayList);
        } else {
            j.d(FileApplication.a(), "history_list", "");
        }
    }

    public static void h(int i) {
        if (i <= 0) {
            i = 20;
        }
        f3615b = i;
    }
}
