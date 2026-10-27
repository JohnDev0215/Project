package b.b.g.g;

import android.os.Handler;
import android.text.TextUtils;
import b.b.h.f.b;
import com.byd.carsettings.db.model.SearchKeyword;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: SearchKeywordDbHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public class f extends b.b.g.g.a<SearchKeyword> {

    /* JADX INFO: compiled from: SearchKeywordDbHelper.java */
    public class a implements b.InterfaceC0077b {
        public a() {
        }

        @Override // b.b.h.f.b.InterfaceC0077b
        public void a(b.b.h.f.b bVar, int i, int i2) {
            b.b.m.c.a.a("SearchKeywordDbHelper", "---createDb---onUpgrade---db = " + bVar + ", oldVersion = " + i + ", newVersion = " + i2);
        }
    }

    /* JADX INFO: compiled from: SearchKeywordDbHelper.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static f f800a = new f(null);

        public static f a() {
            return f800a;
        }
    }

    public /* synthetic */ f(a aVar) {
        this();
    }

    public void m() {
        if (this.f762d) {
            return;
        }
        b.b.h.d dVar = new b.b.h.d();
        dVar.h(SearchKeyword.class);
        dVar.i("SearchKeyword");
        dVar.j(2);
        dVar.l(new a());
        super.b(dVar);
    }

    public void n(String str, b.b.h.c cVar) {
        super.d("id", str, cVar);
    }

    public void o(Handler handler, int i) {
        b.b.m.c.a.a("SearchKeywordDbHelper", "---queryAllAndOrder---");
        super.e(handler, i, "searchCount", true);
    }

    public List<SearchKeyword> p(String str) {
        b.b.m.c.a.a("SearchKeywordDbHelper", "---queryAllAndOrder---");
        return super.f("keyword", str);
    }

    public int q(String str) {
        return super.h("langTag", str);
    }

    public void r(SearchKeyword searchKeyword, String str, b.b.h.c cVar) {
        if (searchKeyword == null || TextUtils.isEmpty(searchKeyword.getKeyword())) {
            return;
        }
        List<SearchKeyword> listP = p(searchKeyword.getKeyword());
        if (listP == null || listP.isEmpty()) {
            int iG = g();
            b.b.m.c.a.a("SearchKeywordDbHelper", "---saveOrUpdate---size: " + iG);
            if (iG >= 20) {
                List listJ = this.f760b.j("searchCount", true);
                Collections.sort(listJ);
                b.b.m.c.a.a("SearchKeywordDbHelper", "---saveOrUpdate---skList.size: " + listJ.size());
                for (int i = 19; i < iG; i++) {
                    n(((SearchKeyword) listJ.get(i)).getId() + "", null);
                }
            }
        } else {
            searchKeyword = listP.get(0);
        }
        searchKeyword.setSearchCount(searchKeyword.getSearchCount() + 1);
        searchKeyword.setSearchTime(System.currentTimeMillis());
        searchKeyword.setLangTag(str);
        b.b.m.c.a.a("SearchKeywordDbHelper", "---saveOrUpdate---sk: " + searchKeyword);
        super.l(searchKeyword, cVar);
    }

    public f() {
    }
}
