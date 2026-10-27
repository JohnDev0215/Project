package b.b.h.f.h;

import b.b.h.f.i.d;
import com.byd.db.ex.DbException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: SqlInfoBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap<d<?>, String> f1888a = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap<d<?>, String> f1889b = new ConcurrentHashMap<>();

    public static a a(d<?> dVar) throws DbException {
        b.b.h.f.i.a aVarG = dVar.g();
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS ");
        sb.append("\"");
        sb.append(dVar.h());
        sb.append("\"");
        sb.append(" ( ");
        if (aVarG.f()) {
            sb.append("\"");
            sb.append(aVarG.d());
            sb.append("\"");
            sb.append(" INTEGER PRIMARY KEY AUTOINCREMENT, ");
        } else {
            sb.append("\"");
            sb.append(aVarG.d());
            sb.append("\"");
            sb.append(aVarG.a());
            sb.append(" PRIMARY KEY, ");
        }
        for (b.b.h.f.i.a aVar : dVar.d().values()) {
            if (!aVar.g()) {
                sb.append("\"");
                sb.append(aVar.d());
                sb.append("\"");
                sb.append(' ');
                sb.append(aVar.a());
                sb.append(' ');
                sb.append(aVar.e());
                sb.append(',');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" )");
        return new a(sb.toString());
    }

    public static a b(d<?> dVar, c cVar) throws DbException {
        StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append("\"");
        sb.append(dVar.h());
        sb.append("\"");
        if (cVar != null && cVar.e() > 0) {
            sb.append(" WHERE ");
            sb.append(cVar.toString());
        }
        return new a(sb.toString());
    }

    public static a c(d<?> dVar, Object obj) throws DbException {
        List<b.b.h.e.a> listG = g(dVar, obj);
        if (listG.size() == 0) {
            return null;
        }
        a aVar = new a();
        String str = f1888a.get(dVar);
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ");
            sb.append("\"");
            sb.append(dVar.h());
            sb.append("\"");
            sb.append(" (");
            for (b.b.h.e.a aVar2 : listG) {
                sb.append("\"");
                sb.append(aVar2.f1858a);
                sb.append("\"");
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES (");
            int size = listG.size();
            for (int i = 0; i < size; i++) {
                sb.append("?,");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
            String string = sb.toString();
            aVar.d(string);
            aVar.b(listG);
            f1888a.put(dVar, string);
        } else {
            aVar.d(str);
            aVar.b(listG);
        }
        return aVar;
    }

    public static a d(d<?> dVar, Object obj) throws DbException {
        List<b.b.h.e.a> listG = g(dVar, obj);
        if (listG.size() == 0) {
            return null;
        }
        a aVar = new a();
        String str = f1889b.get(dVar);
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("REPLACE INTO ");
            sb.append("\"");
            sb.append(dVar.h());
            sb.append("\"");
            sb.append(" (");
            for (b.b.h.e.a aVar2 : listG) {
                sb.append("\"");
                sb.append(aVar2.f1858a);
                sb.append("\"");
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(") VALUES (");
            int size = listG.size();
            for (int i = 0; i < size; i++) {
                sb.append("?,");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
            String string = sb.toString();
            aVar.d(string);
            aVar.b(listG);
            f1889b.put(dVar, string);
        } else {
            aVar.d(str);
            aVar.b(listG);
        }
        return aVar;
    }

    public static a e(d<?> dVar, Object obj, String... strArr) throws DbException {
        List<b.b.h.e.a> listG = g(dVar, obj);
        HashSet hashSet = null;
        if (listG.size() == 0) {
            return null;
        }
        if (strArr != null && strArr.length > 0) {
            hashSet = new HashSet(strArr.length);
            Collections.addAll(hashSet, strArr);
        }
        b.b.h.f.i.a aVarG = dVar.g();
        Object objB = aVarG.b(obj);
        if (objB == null) {
            throw new DbException("this entity[" + dVar.f() + "]'s id value is null");
        }
        a aVar = new a();
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append("\"");
        sb.append(dVar.h());
        sb.append("\"");
        sb.append(" SET ");
        for (b.b.h.e.a aVar2 : listG) {
            if (hashSet == null || hashSet.contains(aVar2.f1858a)) {
                sb.append("\"");
                sb.append(aVar2.f1858a);
                sb.append("\"");
                sb.append("=?,");
                aVar.a(aVar2);
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE ");
        sb.append(c.c(aVarG.d(), "=", objB));
        aVar.d(sb.toString());
        return aVar;
    }

    public static b.b.h.e.a f(Object obj, b.b.h.f.i.a aVar) {
        if (aVar.f()) {
            return null;
        }
        return new b.b.h.e.a(aVar.d(), aVar.c(obj));
    }

    public static List<b.b.h.e.a> g(d<?> dVar, Object obj) {
        ArrayList arrayList = new ArrayList();
        Iterator<b.b.h.f.i.a> it = dVar.d().values().iterator();
        while (it.hasNext()) {
            b.b.h.e.a aVarF = f(obj, it.next());
            if (aVarF != null) {
                arrayList.add(aVarF);
            }
        }
        return arrayList;
    }
}
