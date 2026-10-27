package b.b.h.f.h;

import android.text.TextUtils;
import b.b.h.f.g.f;
import com.byd.db.db.sqlite.ColumnDbType;
import com.bydux.api.common.tools.util.ListUtils;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: WhereBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<String> f1890a = new ArrayList();

    public static c c(String str, String str2, Object obj) {
        c cVar = new c();
        cVar.b(null, str, str2, obj);
        return cVar;
    }

    public c a(c cVar) {
        return d((this.f1890a.size() == 0 ? " " : "AND ") + "(" + cVar.toString() + ")");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.util.ArrayList, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.util.ArrayList, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.Iterable] */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.lang.Iterable] */
    public final void b(String str, String str2, String str3, Object obj) {
        StringBuilder sb = new StringBuilder();
        if (this.f1890a.size() > 0) {
            sb.append(" ");
        }
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            sb.append(" ");
        }
        sb.append("\"");
        sb.append(str2);
        sb.append("\"");
        if ("!=".equals(str3)) {
            str3 = "<>";
        } else if ("==".equals(str3)) {
            str3 = "=";
        }
        if (obj != null) {
            sb.append(" ");
            sb.append(str3);
            sb.append(" ");
            int i = 0;
            ?? arrayList = 0;
            ?? arrayList2 = 0;
            if ("IN".equalsIgnoreCase(str3)) {
                if (obj instanceof Iterable) {
                    arrayList2 = (Iterable) obj;
                } else if (obj.getClass().isArray()) {
                    arrayList2 = new ArrayList();
                    int length = Array.getLength(obj);
                    while (i < length) {
                        arrayList2.add(Array.get(obj, i));
                        i++;
                    }
                }
                if (arrayList2 == 0) {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
                StringBuilder sb2 = new StringBuilder("(");
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    Object objA = b.b.h.f.i.b.a(it.next());
                    if (ColumnDbType.TEXT.equals(f.b(objA.getClass()))) {
                        String string = objA.toString();
                        if (string.indexOf(39) != -1) {
                            string = string.replace("'", "''");
                        }
                        sb2.append("'");
                        sb2.append(string);
                        sb2.append("'");
                    } else {
                        sb2.append(objA);
                    }
                    sb2.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
                }
                sb2.deleteCharAt(sb2.length() - 1);
                sb2.append(")");
                sb.append(sb2.toString());
            } else if ("BETWEEN".equalsIgnoreCase(str3)) {
                if (obj instanceof Iterable) {
                    arrayList = (Iterable) obj;
                } else if (obj.getClass().isArray()) {
                    arrayList = new ArrayList();
                    int length2 = Array.getLength(obj);
                    while (i < length2) {
                        arrayList.add(Array.get(obj, i));
                        i++;
                    }
                }
                if (arrayList == 0) {
                    throw new IllegalArgumentException("value must be an Array or an Iterable.");
                }
                Iterator it2 = arrayList.iterator();
                if (!it2.hasNext()) {
                    throw new IllegalArgumentException("value must have tow items.");
                }
                Object next = it2.next();
                if (!it2.hasNext()) {
                    throw new IllegalArgumentException("value must have tow items.");
                }
                Object next2 = it2.next();
                Object objA2 = b.b.h.f.i.b.a(next);
                Object objA3 = b.b.h.f.i.b.a(next2);
                if (ColumnDbType.TEXT.equals(f.b(objA2.getClass()))) {
                    String string2 = objA2.toString();
                    if (string2.indexOf(39) != -1) {
                        string2 = string2.replace("'", "''");
                    }
                    String string3 = objA3.toString();
                    if (string3.indexOf(39) != -1) {
                        string3 = string3.replace("'", "''");
                    }
                    sb.append("'");
                    sb.append(string2);
                    sb.append("'");
                    sb.append(" AND ");
                    sb.append("'");
                    sb.append(string3);
                    sb.append("'");
                } else {
                    sb.append(objA2);
                    sb.append(" AND ");
                    sb.append(objA3);
                }
            } else {
                Object objA4 = b.b.h.f.i.b.a(obj);
                if (ColumnDbType.TEXT.equals(f.b(objA4.getClass()))) {
                    String string4 = objA4.toString();
                    if (string4.indexOf(39) != -1) {
                        string4 = string4.replace("'", "''");
                    }
                    sb.append("'");
                    sb.append(string4);
                    sb.append("'");
                } else {
                    sb.append(objA4);
                }
            }
        } else if ("=".equals(str3)) {
            sb.append(" IS NULL");
        } else if ("<>".equals(str3)) {
            sb.append(" IS NOT NULL");
        } else {
            sb.append(" ");
            sb.append(str3);
            sb.append(" NULL");
        }
        this.f1890a.add(sb.toString());
    }

    public c d(String str) {
        this.f1890a.add(" " + str);
        return this;
    }

    public int e() {
        return this.f1890a.size();
    }

    public c f(String str, String str2, Object obj) {
        b(this.f1890a.size() == 0 ? null : "OR", str, str2, obj);
        return this;
    }

    public String toString() {
        if (this.f1890a.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = this.f1890a.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }
}
