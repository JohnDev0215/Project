package b.b.g.g;

import android.os.Handler;
import b.b.h.f.b;
import com.byd.carsettings.db.model.FunctionItem;

/* JADX INFO: compiled from: FunctionItemDbHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends b.b.g.g.a<FunctionItem> {

    /* JADX INFO: compiled from: FunctionItemDbHelper.java */
    public class a implements b.InterfaceC0077b {
        public a() {
        }

        @Override // b.b.h.f.b.InterfaceC0077b
        public void a(b.b.h.f.b bVar, int i, int i2) {
            b.b.m.c.a.a("FunctionItemDbHelper", "---createDb---onUpgrade---db = " + bVar + ", oldVersion = " + i + ", newVersion = " + i2);
        }
    }

    /* JADX INFO: compiled from: FunctionItemDbHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static c f794a = new c(null);

        public static c a() {
            return f794a;
        }
    }

    public /* synthetic */ c(a aVar) {
        this();
    }

    public void m() {
        if (this.f762d) {
            return;
        }
        b.b.h.d dVar = new b.b.h.d();
        dVar.h(FunctionItem.class);
        dVar.i("FunctionItem");
        dVar.j(3);
        dVar.l(new a());
        super.b(dVar);
    }

    public void n(Handler handler, int i, String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("%");
        stringBuffer.append(str2);
        stringBuffer.append("%");
        super.i(handler, i, "langTag", str, new String[]{"name", "keywords"}, new String[]{stringBuffer.toString(), stringBuffer.toString()});
    }

    public int o(String str) {
        return super.h("langTag", str);
    }

    public c() {
    }
}
