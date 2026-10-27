package c4;

import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;
import y0.i;
import y0.j;

/* JADX INFO: compiled from: SearchKeywordsDao_Impl.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements c4.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final y0.f f3607a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final y0.c f3608b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final y0.b f3609c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final y0.b f3610d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final j f3611e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final j f3612f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final j f3613g;

    /* JADX INFO: compiled from: SearchKeywordsDao_Impl.java */
    class a extends y0.c<c4.a> {
        a(y0.f fVar) {
            super(fVar);
        }

        @Override // y0.j
        public String d() {
            return "INSERT OR REPLACE INTO `search_keywords`(`id`,`keyword`,`remark`,`user`,`update_time`,`create_time`) VALUES (nullif(?, 0),?,?,?,?,?)";
        }

        @Override // y0.c
        /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
        public void g(b1.f fVar, c4.a aVar) {
            fVar.r(1, aVar.f3601a);
            String str = aVar.f3602b;
            if (str == null) {
                fVar.C(2);
            } else {
                fVar.q(2, str);
            }
            String str2 = aVar.f3603c;
            if (str2 == null) {
                fVar.C(3);
            } else {
                fVar.q(3, str2);
            }
            String str3 = aVar.f3604d;
            if (str3 == null) {
                fVar.C(4);
            } else {
                fVar.q(4, str3);
            }
            fVar.r(5, aVar.f3605e);
            fVar.r(6, aVar.f3606f);
        }
    }

    /* JADX INFO: compiled from: SearchKeywordsDao_Impl.java */
    class b extends y0.b<c4.a> {
        b(y0.f fVar) {
            super(fVar);
        }

        @Override // y0.j
        public String d() {
            return "DELETE FROM `search_keywords` WHERE `id` = ?";
        }
    }

    /* JADX INFO: renamed from: c4.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SearchKeywordsDao_Impl.java */
    class C0037c extends y0.b<c4.a> {
        C0037c(y0.f fVar) {
            super(fVar);
        }

        @Override // y0.j
        public String d() {
            return "UPDATE OR ABORT `search_keywords` SET `id` = ?,`keyword` = ?,`remark` = ?,`user` = ?,`update_time` = ?,`create_time` = ? WHERE `id` = ?";
        }
    }

    /* JADX INFO: compiled from: SearchKeywordsDao_Impl.java */
    class d extends j {
        d(y0.f fVar) {
            super(fVar);
        }

        @Override // y0.j
        public String d() {
            return "delete from search_keywords where keyword =?";
        }
    }

    /* JADX INFO: compiled from: SearchKeywordsDao_Impl.java */
    class e extends j {
        e(y0.f fVar) {
            super(fVar);
        }

        @Override // y0.j
        public String d() {
            return "delete from search_keywords";
        }
    }

    /* JADX INFO: compiled from: SearchKeywordsDao_Impl.java */
    class f extends j {
        f(y0.f fVar) {
            super(fVar);
        }

        @Override // y0.j
        public String d() {
            return "delete from search_keywords where id in (select id from search_keywords ORDER BY update_time DESC LIMIT (select count(id) from search_keywords) offset ?)";
        }
    }

    public c(y0.f fVar) {
        this.f3607a = fVar;
        this.f3608b = new a(fVar);
        this.f3609c = new b(fVar);
        this.f3610d = new C0037c(fVar);
        this.f3611e = new d(fVar);
        this.f3612f = new e(fVar);
        this.f3613g = new f(fVar);
    }

    @Override // c4.b
    public List<c4.a> a() {
        i iVarA = i.A("select * from search_keywords ORDER BY update_time DESC", 0);
        Cursor cursorO = this.f3607a.o(iVarA);
        try {
            int columnIndexOrThrow = cursorO.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = cursorO.getColumnIndexOrThrow("keyword");
            int columnIndexOrThrow3 = cursorO.getColumnIndexOrThrow("remark");
            int columnIndexOrThrow4 = cursorO.getColumnIndexOrThrow("user");
            int columnIndexOrThrow5 = cursorO.getColumnIndexOrThrow("update_time");
            int columnIndexOrThrow6 = cursorO.getColumnIndexOrThrow("create_time");
            ArrayList arrayList = new ArrayList(cursorO.getCount());
            while (cursorO.moveToNext()) {
                c4.a aVar = new c4.a();
                aVar.f3601a = cursorO.getLong(columnIndexOrThrow);
                aVar.f3602b = cursorO.getString(columnIndexOrThrow2);
                aVar.f3603c = cursorO.getString(columnIndexOrThrow3);
                aVar.f3604d = cursorO.getString(columnIndexOrThrow4);
                aVar.f3605e = cursorO.getLong(columnIndexOrThrow5);
                aVar.f3606f = cursorO.getLong(columnIndexOrThrow6);
                arrayList.add(aVar);
            }
            return arrayList;
        } finally {
            cursorO.close();
            iVarA.K();
        }
    }

    @Override // c4.b
    public void b(int i7) {
        b1.f fVarA = this.f3613g.a();
        this.f3607a.b();
        try {
            fVarA.r(1, i7);
            fVarA.v();
            this.f3607a.q();
        } finally {
            this.f3607a.f();
            this.f3613g.f(fVarA);
        }
    }

    @Override // c4.b
    public long c(c4.a aVar) {
        this.f3607a.b();
        try {
            long jH = this.f3608b.h(aVar);
            this.f3607a.q();
            return jH;
        } finally {
            this.f3607a.f();
        }
    }

    @Override // c4.b
    public c4.a d(String str) {
        c4.a aVar;
        i iVarA = i.A("select * from search_keywords where keyword =?", 1);
        if (str == null) {
            iVarA.C(1);
        } else {
            iVarA.q(1, str);
        }
        Cursor cursorO = this.f3607a.o(iVarA);
        try {
            int columnIndexOrThrow = cursorO.getColumnIndexOrThrow("id");
            int columnIndexOrThrow2 = cursorO.getColumnIndexOrThrow("keyword");
            int columnIndexOrThrow3 = cursorO.getColumnIndexOrThrow("remark");
            int columnIndexOrThrow4 = cursorO.getColumnIndexOrThrow("user");
            int columnIndexOrThrow5 = cursorO.getColumnIndexOrThrow("update_time");
            int columnIndexOrThrow6 = cursorO.getColumnIndexOrThrow("create_time");
            if (cursorO.moveToFirst()) {
                aVar = new c4.a();
                aVar.f3601a = cursorO.getLong(columnIndexOrThrow);
                aVar.f3602b = cursorO.getString(columnIndexOrThrow2);
                aVar.f3603c = cursorO.getString(columnIndexOrThrow3);
                aVar.f3604d = cursorO.getString(columnIndexOrThrow4);
                aVar.f3605e = cursorO.getLong(columnIndexOrThrow5);
                aVar.f3606f = cursorO.getLong(columnIndexOrThrow6);
            } else {
                aVar = null;
            }
            return aVar;
        } finally {
            cursorO.close();
            iVarA.K();
        }
    }

    @Override // c4.b
    public void e() {
        b1.f fVarA = this.f3612f.a();
        this.f3607a.b();
        try {
            fVarA.v();
            this.f3607a.q();
        } finally {
            this.f3607a.f();
            this.f3612f.f(fVarA);
        }
    }

    @Override // c4.b
    public int f() {
        i iVarA = i.A("select count(id) from search_keywords", 0);
        Cursor cursorO = this.f3607a.o(iVarA);
        try {
            return cursorO.moveToFirst() ? cursorO.getInt(0) : 0;
        } finally {
            cursorO.close();
            iVarA.K();
        }
    }
}
