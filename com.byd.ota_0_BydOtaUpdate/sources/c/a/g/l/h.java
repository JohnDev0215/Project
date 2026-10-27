package c.a.g.l;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: compiled from: JsonUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2766a = "h";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2767b = System.getProperty("line.separator");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final c.b.a.e f2768c = new c.b.a.f().d().f().b();

    /* JADX INFO: compiled from: JsonUtil.java */
    public class a extends c.b.a.u.a<ArrayList<c.b.a.m>> {
    }

    public static <T> List<T> a(String str, Class<T> cls) {
        ArrayList arrayList = null;
        try {
            List list = (List) f2768c.l(str, new a().e());
            if (list != null) {
                ArrayList arrayList2 = new ArrayList();
                try {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(f2768c.g((c.b.a.m) it.next(), cls));
                    }
                    arrayList = arrayList2;
                } catch (Exception e2) {
                    e = e2;
                    arrayList = arrayList2;
                    e.printStackTrace();
                    c.a.b.d.e.b(f2766a, "parseArray->集合数据解析错误即返回数据不是集合格式的数据，默认返回null集合 - jsonString：" + str);
                }
            } else {
                c.a.b.d.e.b(f2766a, "parseArray->集合数据解析异常即返回数据不是集合格式的数据，默认返回null集合 - jsonString：" + str);
            }
        } catch (Exception e3) {
            e = e3;
        }
        return arrayList;
    }

    public static String b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has(str2)) {
                    return jSONObject.getString(str2);
                }
                return null;
            } catch (Exception e2) {
                c.a.b.d.e.b(f2766a, "parseNote JSONException = " + e2.getMessage() + ", jsonStr = " + str);
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static <T> T c(String str, c.b.a.u.a<T> aVar) {
        try {
            return (T) f2768c.l(str, aVar.e());
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static <T> T d(String str, Class<T> cls) {
        try {
            return (T) f2768c.k(str, cls);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void e(Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append(obj.getClass().getSimpleName());
        String str = f2767b;
        sb.append(str);
        sb.append(f2768c.t(obj));
        String[] strArrSplit = sb.toString().split(str);
        c.a.b.d.e.c(f2766a, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        for (String str2 : strArrSplit) {
            c.a.b.d.e.c(f2766a, "║ " + str2);
        }
        c.a.b.d.e.c(f2766a, "╚═══════════════════════════════════════════════════════════════════════════════════════");
    }

    public static String f(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return f2768c.t(obj);
        } catch (Exception e2) {
            c.a.b.d.e.b(f2766a, "toJson Exception = " + e2.getMessage());
            e2.printStackTrace();
            return null;
        }
    }
}
