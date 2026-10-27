package b.b.g.n;

import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import b.b.g.g.c;
import b.b.g.g.f;
import b.b.g.p.d;
import b.b.m.e.g;
import com.byd.carsettings.db.model.FunctionItem;
import com.byd.carsettings.db.model.SearchKeyword;
import com.byd.carsettings.ui.activity.SearchActivity;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: compiled from: SearchPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public class e extends g<SearchActivity> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.b.g.g.c f983a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.b.g.g.f f984b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f985c;

    /* JADX INFO: compiled from: SearchPresenter.java */
    public class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SearchKeyword f986a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f987b;

        public a(SearchKeyword searchKeyword, String str) {
            this.f986a = searchKeyword;
            this.f987b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.f984b.r(this.f986a, this.f987b, null);
        }
    }

    /* JADX INFO: compiled from: SearchPresenter.java */
    public class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ SearchKeyword f989a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f990b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ String f991c;

        /* JADX INFO: compiled from: SearchPresenter.java */
        public class a implements b.b.h.c {
            public a() {
            }

            @Override // b.b.h.c
            public void a(int i) {
            }

            @Override // b.b.h.c
            public void b() {
                e.this.f984b.r(new SearchKeyword(b.this.f990b), b.this.f991c, null);
            }
        }

        public b(SearchKeyword searchKeyword, String str, String str2) {
            this.f989a = searchKeyword;
            this.f990b = str;
            this.f991c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            e.this.f984b.n(this.f989a.getId() + "", new a());
        }
    }

    /* JADX INFO: compiled from: SearchPresenter.java */
    public static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public WeakReference<e> f994a;

        public c(e eVar) {
            this.f994a = new WeakReference<>(eVar);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            super.handleMessage(message);
            WeakReference<e> weakReference = this.f994a;
            if (weakReference == null || weakReference.get() == null || this.f994a.get().getV() == null) {
                return;
            }
            int i = message.what;
            if (i == 33) {
                List<SearchKeyword> list = (List) message.obj;
                b.b.m.c.a.a("SearchPresenter", "---handleMessage--WHAT_DB_KEYWORD--keywords = " + list);
                ((SearchActivity) this.f994a.get().getV()).r0(list);
                return;
            }
            if (i != 34) {
                return;
            }
            List<FunctionItem> list2 = (List) message.obj;
            b.b.m.c.a.a("SearchPresenter", "---handleMessage--WHAT_DB_FUNCTION--items = " + list2);
            ((SearchActivity) this.f994a.get().getV()).q0(list2);
        }
    }

    public void g(b.b.h.c cVar) {
        b.b.m.c.a.a("SearchPresenter", "-----deleteKeywordAll-----");
        this.f984b.c(cVar);
    }

    public void h(SearchKeyword searchKeyword, String str) {
        b.b.m.c.a.a("SearchPresenter", "-----deleteKeywordAll-----");
        b.b.m.g.b.a(new b(searchKeyword, str, d.g.a().v(getV().getApplicationContext()).toString()));
    }

    public void i() {
        b.b.g.j.a.f(getV().getApplicationContext());
        this.f983a = c.b.a();
        b.b.g.g.f fVarA = f.b.a();
        this.f984b = fVarA;
        fVarA.m();
        if (this.f985c == null) {
            this.f985c = new c(this);
        }
    }

    public void j(String str) {
        b.b.m.c.a.a("SearchPresenter", "-----likeFunctionByName-----keyword = " + str);
        String string = d.g.a().v(getV()).toString();
        b.b.m.c.a.a("SearchPresenter", "-----likeFunctionByName-----langTag： " + string);
        this.f983a.n(this.f985c, 34, string, str);
    }

    public void l() {
        b.b.m.c.a.a("SearchPresenter", "-----queryKeywordAll-----");
        this.f984b.o(this.f985c, 33);
    }

    public void m(String str) {
        b.b.m.g.b.a(new a(new SearchKeyword(str), d.g.a().v(getV().getApplicationContext()).toString()));
    }

    @Override // b.b.m.e.g
    public void unbindV() {
        super.unbindV();
        c cVar = this.f985c;
        if (cVar != null) {
            cVar.removeCallbacksAndMessages(null);
        }
    }
}
