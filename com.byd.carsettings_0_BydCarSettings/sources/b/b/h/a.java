package b.b.h;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import b.b.h.f.b;
import b.b.h.f.e;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: BydDB.java */
/* JADX INFO: loaded from: classes2.dex */
public class a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile boolean f1845a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f1846b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Class f1847c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.b.h.f.b f1848d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ConcurrentHashMap<String, WeakReference<InterfaceC0076a>> f1849e = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b.b.h.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BydDB.java */
    public interface InterfaceC0076a {
        void a(String str);
    }

    public static void g(Application application) {
        if (application == null) {
            return;
        }
        b.b.m.c.a.a("BydDB", "---initDb---");
        b.b(application);
        f1845a = true;
        SharedPreferences sharedPreferences = application.getApplicationContext().getSharedPreferences("first_start", 0);
        f1846b = sharedPreferences.getBoolean("is_first_start", true);
        if (f1846b) {
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putBoolean("is_first_start", false);
            editorEdit.commit();
        }
    }

    public void a() {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return;
        }
        b.b.h.f.i.d dVarC = null;
        try {
            dVarC = b.b.h.f.i.d.c(bVar, this.f1847c);
        } catch (Exception e2) {
            b.b.m.c.a.c("BydDB", "---autoAddColumnIfNot---TableEntity.get---e = " + e2.getMessage());
        }
        if (dVarC == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        String strF = f();
        for (b.b.h.f.i.a aVar : dVarC.d().values()) {
            if (!h(strF, aVar.d())) {
                arrayList.add(aVar);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                this.f1848d.r(this.f1847c, ((b.b.h.f.i.a) it.next()).d());
            } catch (Exception e3) {
                b.b.m.c.a.c("BydDB", "---autoAddColumnIfNot---addColumn---e = " + e3.getMessage());
            }
        }
    }

    public synchronized void b(Application application, d dVar) {
        if (dVar != null) {
            if (!TextUtils.isEmpty(dVar.c())) {
                b.b.m.c.a.a("BydDB", "---createTable---isInited: " + f1845a);
                if (!f1845a) {
                    g(application);
                }
                this.f1847c = dVar.b();
                b.a aVar = new b.a();
                aVar.h(true);
                aVar.k(dVar.f());
                if (dVar.g()) {
                    File file = new File(dVar.a());
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    aVar.i(file);
                }
                aVar.j(dVar.c());
                aVar.n(this.f1847c);
                aVar.l(dVar.d());
                aVar.m(dVar.e());
                this.f1848d = b.a(aVar);
                if (f1846b) {
                    c(this.f1847c);
                } else {
                    a();
                }
            }
        }
    }

    public synchronized void c(Class<?> cls) {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return;
        }
        try {
            bVar.I(cls);
        } catch (Exception e2) {
            b.b.m.c.a.c("BydDB", "-----createTableIfNotExist----DbException e = " + e2.getMessage());
        }
    }

    public synchronized void d(c cVar) {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return;
        }
        try {
            String strB = bVar.K().b();
            File fileA = this.f1848d.K().a();
            this.f1848d.b(this.f1847c);
            if (fileA == null) {
                i(strB);
            }
            if (cVar != null) {
                cVar.b();
            }
        } catch (Exception e2) {
            b.b.m.c.a.c("BydDB", "-----deleteAll----DbException e = " + e2.getMessage());
            if (cVar != null) {
                cVar.a(-1);
            }
        }
    }

    public synchronized void e(String str, String str2, c cVar) {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return;
        }
        try {
            String strB = bVar.K().b();
            File fileA = this.f1848d.K().a();
            this.f1848d.p(this.f1847c, b.b.h.f.h.c.c(str, "=", str2));
            if (fileA == null) {
                i(strB);
            }
            if (cVar != null) {
                cVar.b();
            }
        } catch (Exception e2) {
            b.b.m.c.a.c("BydDB", "-----deleteByField----DbException e = " + e2.getMessage());
            if (cVar != null) {
                cVar.a(-1);
            }
        }
    }

    public final String f() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("SELECT * FROM ");
        stringBuffer.append(this.f1847c.getSimpleName());
        stringBuffer.append(" LIMIT 0");
        return stringBuffer.toString();
    }

    public final boolean h(String str, String str2) {
        b.b.h.f.b bVar = this.f1848d;
        boolean z = false;
        if (bVar == null) {
            return false;
        }
        Cursor cursorD = null;
        try {
            try {
                cursorD = bVar.d(str);
                if (cursorD != null) {
                    if (cursorD.getColumnIndex(str2) >= 0) {
                        z = true;
                    }
                }
                if (cursorD != null) {
                    cursorD.close();
                }
                return z;
            } catch (Exception e2) {
                b.b.m.c.a.c("BydDB", "-----isColumnExists----DbException e = " + e2.getMessage());
                if (cursorD != null) {
                    cursorD.close();
                }
                return false;
            }
        } catch (Throwable th) {
            if (cursorD != null) {
                cursorD.close();
            }
            throw th;
        }
    }

    public final void i(String str) {
        if (this.f1849e.size() <= 0) {
            return;
        }
        for (Map.Entry<String, WeakReference<InterfaceC0076a>> entry : this.f1849e.entrySet()) {
            if (entry.getValue() != null && entry.getValue().get() != null) {
                entry.getValue().get().a(str);
            }
        }
    }

    public List<T> j(String str, boolean z) {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return null;
        }
        try {
            return bVar.D(this.f1847c).j(str, z).b();
        } catch (Exception e2) {
            b.b.m.c.a.c("BydDB", "-----queryAllAndOrder----DbException e = " + e2.getMessage());
            return null;
        }
    }

    public void k(Handler handler, int i, String str, boolean z) {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return;
        }
        try {
            try {
                q(handler, i, bVar.D(this.f1847c).j(str, z).b());
            } catch (Exception e2) {
                b.b.m.c.a.c("BydDB", "-----queryAllAndOrder----DbException e = " + e2.getMessage());
                q(handler, i, null);
            }
        } catch (Throwable th) {
            q(handler, i, null);
            throw th;
        }
    }

    public List<T> l(String str, String str2) {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return null;
        }
        try {
            return bVar.D(this.f1847c).l(str, "=", str2).b();
        } catch (Exception e2) {
            b.b.m.c.a.c("BydDB", "-----queryByField----DbException e = " + e2.getMessage());
            return null;
        }
    }

    public int m() {
        int iB;
        List<b.b.h.f.i.c> listA;
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return -1;
        }
        try {
            listA = bVar.D(this.f1847c).k("count(*)as size").a();
        } catch (Exception e2) {
            e = e2;
            iB = 0;
        }
        if (listA == null || listA.isEmpty()) {
            return 0;
        }
        Iterator<b.b.h.f.i.c> it = listA.iterator();
        iB = 0;
        while (it.hasNext()) {
            try {
                iB = it.next().b("size");
            } catch (Exception e3) {
                e = e3;
                b.b.m.c.a.c("BydDB", "-----queryDataSizeByField----DbException e = " + e.getMessage());
            }
        }
        return iB;
        b.b.m.c.a.c("BydDB", "-----queryDataSizeByField----DbException e = " + e.getMessage());
        return iB;
    }

    public int n(String str, String str2) {
        b.b.h.f.b bVar = this.f1848d;
        int iB = -1;
        if (bVar == null) {
            return -1;
        }
        try {
            List<b.b.h.f.i.c> listA = bVar.D(this.f1847c).l(str, "=", str2).k("count(*)as size").a();
            if (listA != null && !listA.isEmpty()) {
                Iterator<b.b.h.f.i.c> it = listA.iterator();
                while (it.hasNext()) {
                    iB = it.next().b("size");
                }
            }
        } catch (Exception e2) {
            b.b.m.c.a.c("BydDB", "-----queryDataSizeByField----DbException e = " + e2.getMessage());
        }
        return iB;
    }

    public void o(Handler handler, int i, String str, String str2, String[] strArr, String[] strArr2) {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return;
        }
        try {
            try {
                e<T> eVarL = bVar.D(this.f1847c).l(str, "=", str2);
                if (strArr != null && strArr2 != null && strArr.length > 0 && strArr.length == strArr2.length) {
                    b.b.h.f.h.c cVarC = b.b.h.f.h.c.c(strArr[0], "LIKE", strArr2[0]);
                    int length = strArr.length;
                    if (length > 1) {
                        for (int i2 = 1; i2 < length; i2++) {
                            cVarC.f(strArr[i2], "LIKE", strArr2[i2]);
                        }
                    }
                    eVarL.a(cVarC);
                }
                q(handler, i, eVarL.b());
            } catch (Exception e2) {
                b.b.m.c.a.c("BydDB", "--queryFieldAndLikeFields--DbException e = " + e2.getMessage());
                q(handler, i, null);
            }
        } catch (Throwable th) {
            q(handler, i, null);
            throw th;
        }
    }

    public List<T> p(String str, String str2, String str3, boolean z, int i) {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return null;
        }
        try {
            return bVar.D(this.f1847c).l(str, "=", str2).j(str3, z).i(i).b();
        } catch (Exception e2) {
            b.b.m.c.a.c("BydDB", "-----queryByRecently----DbException e = " + e2.getMessage());
            return null;
        }
    }

    public final void q(Handler handler, int i, Object obj) {
        if (handler != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.obj = obj;
            handler.sendMessage(messageObtain);
        }
    }

    public synchronized void r(List<T> list) {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return;
        }
        try {
            String strB = bVar.K().b();
            File fileA = this.f1848d.K().a();
            this.f1848d.m(list);
            if (fileA == null) {
                i(strB);
            }
        } catch (Exception e2) {
            b.b.m.c.a.c("BydDB", "-----saveAll----DbException e = " + e2.getMessage());
        }
    }

    public synchronized void s(T t, c cVar) {
        b.b.h.f.b bVar = this.f1848d;
        if (bVar == null) {
            return;
        }
        try {
            String strB = bVar.K().b();
            File fileA = this.f1848d.K().a();
            this.f1848d.P(t);
            if (fileA == null) {
                i(strB);
            }
            if (cVar != null) {
                cVar.b();
            }
        } catch (Exception e2) {
            b.b.m.c.a.c("BydDB", "-----saveOrUpdate----DbException e = " + e2.getMessage());
            if (cVar != null) {
                cVar.a(-1);
            }
        }
    }
}
