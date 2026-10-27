package b.b.h.f;

import android.text.TextUtils;
import com.byd.db.ex.DbException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: Selector.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b.b.h.f.i.d<T> f1876a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.h.f.h.c f1877b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f1878c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1879d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List<a> f1880e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1881f = 0;
    public int g = 0;

    /* JADX INFO: compiled from: Selector.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1882a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public boolean f1883b;

        public a(String str, boolean z) {
            this.f1882a = str;
            this.f1883b = z;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("\"");
            sb.append(this.f1882a);
            sb.append("\"");
            sb.append(this.f1883b ? " DESC" : " ASC");
            return sb.toString();
        }
    }

    public e(b.b.h.f.i.d<T> dVar) {
        this.f1876a = dVar;
    }

    public static <T> e<T> c(b.b.h.f.i.d<T> dVar) {
        return new e<>(dVar);
    }

    public e<T> a(b.b.h.f.h.c cVar) {
        this.f1877b.a(cVar);
        return this;
    }

    public List<T> b() throws DbException {
        ArrayList arrayList = null;
        if (!this.f1876a.m()) {
            return null;
        }
        Cursor cursorD = this.f1876a.e().d(toString());
        if (cursorD != null) {
            try {
                arrayList = new ArrayList();
                while (cursorD.moveToNext()) {
                    arrayList.add(b.b.h.f.a.b(this.f1876a, cursorD));
                }
            } finally {
            }
        }
        return arrayList;
    }

    public int d() {
        return this.f1881f;
    }

    public int e() {
        return this.g;
    }

    public List<a> f() {
        return this.f1880e;
    }

    public b.b.h.f.i.d<T> g() {
        return this.f1876a;
    }

    public b.b.h.f.h.c h() {
        return this.f1877b;
    }

    public e<T> i(int i) {
        this.f1881f = i;
        return this;
    }

    public e<T> j(String str, boolean z) {
        if (this.f1880e == null) {
            this.f1880e = new ArrayList();
        }
        this.f1880e.add(new a(str, z));
        return this;
    }

    public d k(String... strArr) {
        return new d(this, strArr);
    }

    public e<T> l(String str, String str2, Object obj) {
        this.f1877b = b.b.h.f.h.c.c(str, str2, obj);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("*");
        sb.append(" FROM ");
        sb.append("\"");
        sb.append(this.f1876a.h());
        sb.append("\"");
        b.b.h.f.h.c cVar = this.f1877b;
        if (cVar != null && cVar.e() > 0) {
            sb.append(" WHERE ");
            sb.append(this.f1877b.toString());
        }
        if (this.f1879d && !TextUtils.isEmpty(this.f1878c)) {
            sb.append(" GROUP BY ");
            sb.append("\"");
            sb.append(this.f1878c);
            sb.append("\"");
            b.b.h.f.h.c cVar2 = this.f1877b;
            if (cVar2 != null && cVar2.e() > 0) {
                sb.append(" HAVING ");
                sb.append(this.f1877b.toString());
            }
        }
        List<a> list = this.f1880e;
        if (list != null && list.size() > 0) {
            sb.append(" ORDER BY ");
            Iterator<a> it = this.f1880e.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
                sb.append(',');
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        if (this.f1881f > 0) {
            sb.append(" LIMIT ");
            sb.append(this.f1881f);
            sb.append(" OFFSET ");
            sb.append(this.g);
        }
        return sb.toString();
    }
}
