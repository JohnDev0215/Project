package b.b.h.f;

import android.text.TextUtils;
import com.byd.db.ex.DbException;
import java.io.Closeable;
import java.io.File;
import java.util.Objects;
import net.sqlcipher.Cursor;

/* JADX INFO: compiled from: DbManager.java */
/* JADX INFO: loaded from: classes2.dex */
public interface b extends Closeable {

    /* JADX INFO: compiled from: DbManager.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f1860a = "pan.db";

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1861b = 1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public boolean f1862c = true;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public InterfaceC0077b f1863d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public File f1864e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f1865f;
        public Class<?> g;

        public File a() {
            return this.f1864e;
        }

        public String b() {
            return this.f1860a;
        }

        public InterfaceC0077b c() {
            return this.f1863d;
        }

        public int d() {
            return this.f1861b;
        }

        public String e() {
            return this.f1865f;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || a.class != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            if (this.f1860a.equals(aVar.f1860a)) {
                return Objects.equals(this.f1864e, aVar.f1864e);
            }
            return false;
        }

        public Class<?> f() {
            return this.g;
        }

        public boolean g() {
            return this.f1862c;
        }

        public a h(boolean z) {
            this.f1862c = z;
            return this;
        }

        public int hashCode() {
            int iHashCode = this.f1860a.hashCode() * 31;
            File file = this.f1864e;
            int iHashCode2 = iHashCode + (file != null ? file.hashCode() : 0);
            Class<?> cls = this.g;
            return iHashCode2 + (cls != null ? cls.hashCode() : 0);
        }

        public a i(File file) {
            this.f1864e = file;
            return this;
        }

        public a j(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.f1860a = str;
            }
            return this;
        }

        public a k(InterfaceC0077b interfaceC0077b) {
            this.f1863d = interfaceC0077b;
            return this;
        }

        public a l(int i) {
            this.f1861b = i;
            return this;
        }

        public void m(String str) {
            this.f1865f = str;
        }

        public void n(Class<?> cls) {
            this.g = cls;
        }

        public String toString() {
            return String.valueOf(this.f1864e) + "/" + this.f1860a;
        }
    }

    /* JADX INFO: renamed from: b.b.h.f.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DbManager.java */
    public interface InterfaceC0077b {
        void a(b bVar, int i, int i2);
    }

    <T> e<T> D(Class<T> cls) throws DbException;

    void I(Class<?> cls) throws DbException;

    a K();

    void P(Object obj) throws DbException;

    void b(Class<?> cls) throws DbException;

    Cursor d(String str) throws DbException;

    void m(Object obj) throws DbException;

    void p(Class<?> cls, b.b.h.f.h.c cVar) throws DbException;

    void r(Class<?> cls, String str) throws DbException;
}
