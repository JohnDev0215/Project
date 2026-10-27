package defpackage;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.l;
import defpackage.eg;
import defpackage.lk1;
import defpackage.xf;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.bookmarks.BookmarkSelectableListLayout;
import org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.bookmarks.BookmarkId;
import org.chromium.components.bookmarks.BookmarkItem;
import org.chromium.components.browser_ui.widget.dragreorder.DragReorderableListAdapter;
import org.chromium.components.browser_ui.widget.selectable_list.a;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: BookmarkManagerMediator.java */
/* JADX INFO: loaded from: classes.dex */
public final class gg implements qf, PartnerBookmarksReader.a, xf.b {
    public int A;
    public String B;
    public BookmarkId C;
    public BookmarkId D;
    public final a a;
    public final b b;
    public final c c;
    public final d d;
    public final e e;
    public final f f;
    public final org.chromium.base.b<fh> g;
    public final g h;
    public final Context i;
    public final BookmarkModel j;
    public final ng k;
    public final BookmarkSelectableListLayout<BookmarkId> l;
    public final org.chromium.components.browser_ui.widget.selectable_list.a<BookmarkId> m;
    public final RecyclerView n;
    public final xf o;
    public final LargeIconBridge p;
    public final boolean q;
    public final aq1<Boolean> r;
    public final ArrayList s;
    public final Profile t;
    public final pg u;
    public final org.chromium.chrome.browser.bookmarks.a v;
    public boolean w;
    public String x;
    public boolean y;
    public be z;

    /* JADX INFO: compiled from: BookmarkManagerMediator.java */
    public class b extends Stack<gh> {
        public b() {
        }

        @Override // java.util.Stack
        public final gh pop() {
            gh ghVar;
            synchronized (this) {
                ghVar = (gh) super.pop();
                gg.this.v();
            }
            return ghVar;
        }

        @Override // java.util.Stack
        public final gh push(gh ghVar) {
            gh ghVar2 = (gh) super.push(ghVar);
            gg.this.v();
            return ghVar2;
        }
    }

    /* JADX INFO: compiled from: BookmarkManagerMediator.java */
    public class c extends RecyclerView.g {
        public c() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void a() {
            gg.p(gg.this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public final void f(int i, int i2) {
            gg.p(gg.this);
        }
    }

    /* JADX INFO: compiled from: BookmarkManagerMediator.java */
    public class d extends mg {
        public d() {
        }

        @Override // defpackage.mg
        public final void f() {
            gg ggVar = gg.this;
            ggVar.D = null;
            ggVar.t(ggVar.e);
            if (ggVar.n() == 3) {
                if (TextUtils.equals(ggVar.B, null)) {
                    ggVar.A((gh) ggVar.b.pop());
                } else {
                    ggVar.x(ggVar.B);
                }
            }
        }

        @Override // defpackage.mg
        public final void i(BookmarkItem bookmarkItem) {
            gg ggVar = gg.this;
            ggVar.D = null;
            int iR = ggVar.r(bookmarkItem.c);
            if (iR >= 0) {
                ggVar.o.e.d(iR, 1, null);
            }
        }

        @Override // defpackage.mg
        public final void m(BookmarkItem bookmarkItem, BookmarkItem bookmarkItem2, boolean z) {
            gg ggVar = gg.this;
            ggVar.D = null;
            if (ggVar.n() == 3) {
                ggVar.x(ggVar.B);
                return;
            }
            if (bookmarkItem2.d) {
                ggVar.t(ggVar.e);
                return;
            }
            int iR = ggVar.r(bookmarkItem2.c);
            if (iR >= 0) {
                xf xfVar = ggVar.o;
                xfVar.j.remove(iR);
                xfVar.e.f(iR, 1);
            }
        }
    }

    /* JADX INFO: compiled from: BookmarkManagerMediator.java */
    public class e implements fh {
        public e() {
        }

        @Override // defpackage.fh
        public final void d(BookmarkId bookmarkId) {
            gg ggVar = gg.this;
            boolean zEquals = bookmarkId.equals(ggVar.j.k());
            BookmarkModel bookmarkModel = ggVar.j;
            if (zEquals) {
                bookmarkId = bookmarkModel.n();
            }
            ggVar.D = null;
            ggVar.B = null;
            ggVar.C = bookmarkId;
            xf xfVar = ggVar.o;
            if (xfVar.i == null) {
                DragReorderableListAdapter.b bVar = new DragReorderableListAdapter.b();
                xfVar.getClass();
                xfVar.i = new l(bVar);
            }
            xfVar.i.j(xfVar.k);
            BookmarkId bookmarkId2 = ggVar.C;
            if ((bookmarkId2 == null || bookmarkModel == null) ? true : bookmarkId2.equals(bookmarkModel.n())) {
                ggVar.w();
                ggVar.y(ggVar.s);
            } else {
                ggVar.y(bookmarkModel.j(bookmarkId));
            }
            boolean zEquals2 = BookmarkId.c.equals(bookmarkId);
            BookmarkSelectableListLayout<BookmarkId> bookmarkSelectableListLayout = ggVar.l;
            if (zEquals2) {
                bookmarkSelectableListLayout.setEmptyViewText(R$string.tracked_products_empty_list_title);
            } else if (bookmarkId.getType() != 2) {
                bookmarkSelectableListLayout.setEmptyViewText(R$string.bookmarks_folder_empty);
            } else {
                oj2.t(ggVar.t).notifyEvent("read_later_bookmark_folder_opened");
                bookmarkSelectableListLayout.setEmptyViewText(R$string.reading_list_empty_list_title);
            }
        }

        @Override // defpackage.fh
        public final void g() {
            BookmarkId bookmarkId;
            gg ggVar = gg.this;
            if (ggVar.n() == 3 && (bookmarkId = ggVar.C) != null && !bookmarkId.equals(ggVar.j.n())) {
                b bVar = ggVar.b;
                if (!bVar.isEmpty()) {
                    bVar.pop();
                }
                if (!bVar.isEmpty()) {
                    ggVar.A((gh) bVar.pop());
                }
                ggVar.l.k();
            }
            ggVar.D = null;
            xf xfVar = ggVar.o;
            xfVar.D();
            ggVar.B(false);
            int size = xfVar.j.size();
            while (true) {
                size--;
                if (size < 0) {
                    xfVar.o();
                    return;
                } else if (((yf) xfVar.j.get(size)).a == 6) {
                    xfVar.j.remove(size);
                }
            }
        }

        @Override // defpackage.fh
        public final void onDestroy() {
            gg ggVar = gg.this;
            ggVar.i(ggVar.e);
            ggVar.j.r(ggVar.d);
            ggVar.m.g(ggVar.f);
            pg pgVar = ggVar.u;
            if (pgVar.j != null) {
                pgVar.g.j(pgVar);
                pgVar.i.e(pgVar);
            }
            pgVar.f.n(pgVar);
        }
    }

    /* JADX INFO: compiled from: BookmarkManagerMediator.java */
    public class f implements a.InterfaceC0128a<BookmarkId> {
        public f() {
        }

        @Override // org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
        public final void f(ArrayList arrayList) {
            gg.this.D = null;
        }
    }

    /* JADX INFO: compiled from: BookmarkManagerMediator.java */
    public class g implements pd0 {
        public qf a;
        public org.chromium.components.browser_ui.widget.selectable_list.a<BookmarkId> b;
        public boolean c;

        public g() {
        }

        @Override // defpackage.pd0
        public final boolean a() {
            return b() && this.b.e();
        }

        @Override // defpackage.pd0
        public final boolean b() {
            return !this.c && this.a.n() == 2;
        }
    }

    public gg(Context context, BookmarkModel bookmarkModel, ng ngVar, BookmarkSelectableListLayout bookmarkSelectableListLayout, eg.a aVar, RecyclerView recyclerView, xf xfVar, LargeIconBridge largeIconBridge, boolean z, aq1 aq1Var, Profile profile, org.chromium.chrome.browser.bookmarks.a aVar2) {
        a aVar3 = new a();
        this.a = aVar3;
        this.b = new b();
        c cVar = new c();
        this.c = cVar;
        this.d = new d();
        this.e = new e();
        this.f = new f();
        this.g = new org.chromium.base.b<>();
        this.h = new g();
        this.s = new ArrayList();
        this.A = -1;
        this.i = context;
        this.j = bookmarkModel;
        bookmarkModel.c(aVar3);
        this.k = ngVar;
        this.l = bookmarkSelectableListLayout;
        bookmarkSelectableListLayout.getHandleBackPressChangedSupplier().p(new so(10, this));
        this.m = aVar;
        this.n = recyclerView;
        this.o = xfVar;
        xfVar.A(cVar);
        this.p = largeIconBridge;
        this.q = z;
        this.r = aq1Var;
        this.t = profile;
        this.u = new pg(context, profile, new androidx.appcompat.widget.a(13, this));
        this.v = aVar2;
        PartnerBookmarksReader.j.add(this);
        A(new gh(1, "", null));
        bookmarkModel.g(new fg(this, 1));
    }

    public static void p(gg ggVar) {
        org.chromium.components.browser_ui.widget.selectable_list.a<BookmarkId> aVar = ggVar.m;
        for (BookmarkId bookmarkId : aVar.c()) {
            if (aVar.d(bookmarkId) && ggVar.r(bookmarkId) == -1) {
                aVar.i(bookmarkId);
            }
        }
    }

    public final void A(gh ghVar) {
        BookmarkModel bookmarkModel = this.j;
        if (!ghVar.c(bookmarkModel)) {
            ghVar = gh.a(bookmarkModel.n(), bookmarkModel);
        }
        b bVar = this.b;
        if (bVar.isEmpty() || !bVar.peek().equals(ghVar)) {
            if (!bVar.isEmpty() && bVar.peek().a == 1) {
                bVar.pop();
            }
            bVar.push(ghVar);
            u(ghVar);
        }
    }

    public final void B(boolean z) {
        int i;
        boolean zS = s();
        int iN = n();
        if (iN == 1) {
            return;
        }
        if (iN == 3 || (i = this.u.k) == 0) {
            this.A = -1;
        } else if (i == 1) {
            this.A = 0;
        } else if (i == 2) {
            this.A = 1;
        } else if (i == 3) {
            this.A = 2;
        }
        boolean zS2 = s();
        xf xfVar = this.o;
        if (!zS && zS2) {
            xfVar.j.add(0, new yf(this.A, null, null));
            if (z) {
                xfVar.e.e(0, 1);
                return;
            }
            return;
        }
        if (zS && zS2) {
            if (z) {
                xfVar.e.d(0, 1, null);
            }
        } else {
            if (!zS || zS2) {
                return;
            }
            xfVar.j.remove(0);
            if (z) {
                xfVar.e.f(0, 1);
            }
        }
    }

    @Override // defpackage.qf
    public final g a() {
        return this.h;
    }

    @Override // defpackage.qf
    public final LargeIconBridge b() {
        return this.p;
    }

    @Override // defpackage.qf
    public final void c(BookmarkId bookmarkId) {
        n3.X0("MobileBookmarkManagerOpenFolder");
        A(gh.a(bookmarkId, this.j));
        this.n.g0(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
    @Override // defpackage.qf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d(org.chromium.components.bookmarks.BookmarkId r8) {
        /*
            r7 = this;
            r0 = 2
            ng r1 = r7.k
            if (r8 != 0) goto L9
            r1.getClass()
            goto L11
        L9:
            org.chromium.chrome.browser.bookmarks.BookmarkModel r2 = r1.a
            org.chromium.components.bookmarks.BookmarkItem r2 = r2.h(r8)
            if (r2 != 0) goto L13
        L11:
            r1 = 0
            goto L42
        L13:
            org.chromium.components.bookmarks.BookmarkId r3 = r2.c
            int r4 = r3.getType()
            r5 = 1
            if (r4 != r0) goto L23
            org.chromium.chrome.browser.bookmarks.BookmarkModel r4 = r1.a
            org.chromium.url.GURL r6 = r2.b
            r4.t(r6, r5)
        L23:
            java.lang.String r4 = "MobileBookmarkManagerEntryOpened"
            defpackage.n3.X0(r4)
            int r3 = r3.getType()
            r4 = 3
            java.lang.String r6 = "Bookmarks.OpenBookmarkType"
            defpackage.oj2.U(r3, r4, r6)
            java.lang.String r3 = "Bookmarks.OpenBookmarkTimeInterval2."
            defpackage.ng.c(r2, r3)
            boolean r3 = r7.q
            android.content.Intent r1 = r1.a(r2, r3)
            r2 = 0
            org.chromium.chrome.browser.b.A(r2, r1, r2)
            r1 = r5
        L42:
            if (r1 != 0) goto L45
            return
        L45:
            if (r8 == 0) goto L64
            int r8 = r8.getType()
            if (r8 == r0) goto L64
            int r8 = defpackage.lh.a
            android.content.Context r7 = r7.i
            boolean r8 = r7 instanceof org.chromium.chrome.browser.app.bookmarks.BookmarkActivity
            if (r8 == 0) goto L5b
            android.app.Activity r7 = (android.app.Activity) r7
            r7.finish()
            goto L64
        L5b:
            boolean r8 = r7 instanceof org.chromium.chrome.browser.bookmark_history.BookmarkHistoryActivity
            if (r8 == 0) goto L64
            android.app.Activity r7 = (android.app.Activity) r7
            r7.finish()
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gg.d(org.chromium.components.bookmarks.BookmarkId):void");
    }

    @Override // defpackage.qf
    public final void e(BookmarkId bookmarkId) {
        int iR = r(bookmarkId);
        xf xfVar = this.o;
        xfVar.j.remove(iR);
        BookmarkModel bookmarkModel = this.j;
        xfVar.j.add(iR - 1, yf.a(bookmarkModel.h(bookmarkId), bookmarkModel.l(bookmarkId)));
        z(xfVar.j);
    }

    @Override // org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader.a
    public final void f(String str) {
        this.p.c.remove(new GURL(str));
        this.y = true;
    }

    @Override // defpackage.qf
    public final void g(BookmarkId bookmarkId) {
        this.n.g0(r(bookmarkId));
        this.D = bookmarkId;
    }

    @Override // defpackage.qf
    public final BookmarkModel h() {
        return this.j;
    }

    @Override // defpackage.qf
    public final void i(fh fhVar) {
        this.g.c(fhVar);
    }

    @Override // defpackage.qf
    public final void j() {
        A(new gh(3, "", null));
        int i = R$string.bookmark_no_result;
        BookmarkSelectableListLayout<BookmarkId> bookmarkSelectableListLayout = this.l;
        bookmarkSelectableListLayout.getContext().getString(i);
        bookmarkSelectableListLayout.q();
    }

    @Override // defpackage.qf
    public final void k(fh fhVar) {
        this.g.a(fhVar);
        t(fhVar);
    }

    @Override // defpackage.qf
    public final org.chromium.components.browser_ui.widget.selectable_list.a<BookmarkId> l() {
        return this.m;
    }

    @Override // defpackage.qf
    public final void m(BookmarkId bookmarkId) {
        int iR = r(bookmarkId);
        xf xfVar = this.o;
        xfVar.j.remove(iR);
        BookmarkModel bookmarkModel = this.j;
        xfVar.j.add(iR + 1, yf.a(bookmarkModel.h(bookmarkId), bookmarkModel.l(bookmarkId)));
        z(xfVar.j);
    }

    @Override // defpackage.qf
    public final int n() {
        b bVar = this.b;
        if (bVar.isEmpty()) {
            return 1;
        }
        return bVar.peek().a;
    }

    @Override // org.chromium.chrome.browser.partnerbookmarks.PartnerBookmarksReader.a
    public final void o() {
        if (this.y) {
            xf xfVar = this.o;
            if (xfVar.j != null) {
                xfVar.o();
            }
            this.y = false;
        }
    }

    public final int q() {
        xf xfVar = this.o;
        int size = xfVar.j.size() - 1;
        BookmarkItem bookmarkItem = ((yf) xfVar.j.get(size)).b;
        return (bookmarkItem == null || !lh.c(bookmarkItem)) ? size - 1 : size;
    }

    public final int r(BookmarkId bookmarkId) {
        BookmarkItem bookmarkItem;
        int i = 0;
        while (true) {
            xf xfVar = this.o;
            if (i >= xfVar.j.size()) {
                return -1;
            }
            yf yfVar = (yf) xfVar.j.get(i);
            if (bookmarkId.equals((yfVar == null || (bookmarkItem = yfVar.b) == null) ? null : bookmarkItem.c)) {
                return i;
            }
            i++;
        }
    }

    public final boolean s() {
        return this.A != -1;
    }

    public final void t(fh fhVar) {
        int iN = n();
        fhVar.a(iN);
        if (iN != 1) {
            if (iN == 2) {
                fhVar.d(this.b.peek().c);
            } else {
                if (iN != 3) {
                    return;
                }
                fhVar.g();
            }
        }
    }

    public final void u(gh ghVar) {
        if (ghVar.a == 2) {
            int i = lh.a;
            SharedPreferencesManager sharedPreferencesManager = SharedPreferencesManager.getInstance();
            String str = ghVar.b;
            sharedPreferencesManager.o("enhanced_bookmark_last_used_url", str);
            be beVar = this.z;
            if (beVar != null && !str.equals(beVar.j)) {
                LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
                loadUrlParams.o = false;
                ((lk1.b) beVar.e).b(loadUrlParams, false);
            }
        }
        Iterator<fh> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                t((fh) aVar.next());
            }
        }
    }

    public final void v() {
        boolean z = this.w;
        aq1<Boolean> aq1Var = this.r;
        if (z) {
            aq1Var.r(Boolean.FALSE);
            return;
        }
        boolean z2 = true;
        if (!Boolean.TRUE.equals(this.l.getHandleBackPressChangedSupplier().get()) && this.b.size() <= 1) {
            z2 = false;
        }
        aq1Var.r(Boolean.valueOf(z2));
    }

    public final void w() {
        ArrayList arrayList = this.s;
        arrayList.clear();
        int i = lh.a;
        ArrayList arrayList2 = new ArrayList();
        BookmarkModel bookmarkModel = this.j;
        ArrayList<BookmarkId> arrayListO = bookmarkModel.o(false);
        BookmarkId bookmarkIdN = bookmarkModel.n();
        ArrayList arrayList3 = new ArrayList();
        for (BookmarkId bookmarkId : arrayListO) {
            if (bookmarkId.getType() == 2) {
                arrayList2.add(bookmarkId);
                oj2.t(Profile.d()).notifyEvent("read_later_bottom_sheet_folder_seen");
            } else if (bookmarkModel.h(bookmarkId).e.equals(bookmarkIdN)) {
                arrayList3.add(bookmarkId);
            }
        }
        arrayList2.addAll(arrayList3);
        arrayList.addAll(arrayList2);
        arrayList.addAll(bookmarkModel.j(bookmarkModel.k()));
    }

    public final void x(String str) {
        this.B = str == null ? "" : str.trim();
        BookmarkId bookmarkId = this.C;
        BookmarkModel bookmarkModel = this.j;
        if (bookmarkId.equals(bookmarkModel.n()) && TextUtils.isEmpty(this.B)) {
            y(this.s);
        } else {
            y(bookmarkModel.s(this.B, 0, 500));
        }
    }

    public final void y(ArrayList arrayList) {
        this.D = null;
        xf xfVar = this.o;
        xfVar.j.clear();
        if (s()) {
            xfVar.j.add(new yf(this.A, null, null));
        }
        B(false);
        boolean zEquals = BookmarkId.c.equals(this.C);
        BookmarkModel bookmarkModel = this.j;
        if (zEquals) {
            int size = arrayList.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                c32 c32VarL = bookmarkModel.l((BookmarkId) arrayList.get(size));
                if (c32VarL == null || !c32VarL.Q() || !c32VarL.P().S()) {
                    arrayList.remove(size);
                }
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BookmarkId bookmarkId = (BookmarkId) it.next();
            BookmarkItem bookmarkItemH = bookmarkModel.h(bookmarkId);
            if (bookmarkItemH != null) {
                xfVar.j.add(yf.a(bookmarkItemH, bookmarkModel.l(bookmarkId)));
            }
        }
        if (this.C.getType() == 2 && n() != 3) {
            ArrayList arrayList2 = xfVar.j;
            if (!arrayList2.isEmpty()) {
                Iterator it2 = arrayList2.iterator();
                int i = 0;
                while (it2.hasNext()) {
                    BookmarkItem bookmarkItem = ((yf) it2.next()).b;
                    if (bookmarkItem != null && bookmarkItem.c.getType() == 2) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i != arrayList2.size()) {
                    Collections.sort(arrayList2.subList(i, arrayList2.size()), new kb3(1));
                    int i2 = 0;
                    int i3 = 0;
                    for (int i4 = 0; i4 < arrayList2.size(); i4++) {
                        if (((yf) arrayList2.get(i4)).b != null) {
                            if (((yf) arrayList2.get(i4)).b.i) {
                                i2++;
                            } else {
                                i3++;
                            }
                        }
                    }
                    n3.X0("Android.BookmarkPage.ReadingList.OpenReadingList");
                    oj2.R(i2, "Bookmarks.ReadingList.NumberOfReadItems");
                    oj2.R(i3, "Bookmarks.ReadingList.NumberOfUnreadItems");
                    oj2.R(i2 + i3, "Bookmarks.ReadingList.NumberOfItems");
                    Context context = this.i;
                    arrayList2.add(i, l92.a(context, false));
                    int i5 = i + 1;
                    while (true) {
                        if (i5 >= arrayList2.size()) {
                            arrayList2.add(arrayList2.size(), l92.a(context, true));
                            break;
                        } else {
                            if (((yf) arrayList2.get(i5)).b.i) {
                                arrayList2.add(i5, l92.a(context, true));
                                break;
                            }
                            i5++;
                        }
                    }
                }
            }
        }
        xfVar.o();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2, types: [int] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r4v0, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v1, types: [int] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    public final void z(ArrayList arrayList) {
        BookmarkId bookmarkId = this.C;
        BookmarkModel bookmarkModel = this.j;
        boolean zEquals = (bookmarkId == null || bookmarkModel == null) ? true : bookmarkId.equals(bookmarkModel.n());
        if (zEquals) {
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            for (yf yfVar : arrayList) {
                if (yfVar.b.d) {
                    arrayList2.add(yfVar);
                } else {
                    arrayList3.add(yfVar);
                }
            }
            arrayList.clear();
            arrayList.addAll(arrayList2);
            arrayList.addAll(arrayList3);
            xf xfVar = this.o;
            if (xfVar.j != null) {
                xfVar.o();
            }
        }
        BookmarkId bookmarkIdK = zEquals ? bookmarkModel.k() : this.C;
        ?? S = s();
        int iQ = q();
        ?? r4 = S;
        if (zEquals) {
            r4 = S + 1;
        }
        long[] jArr = new long[(iQ - r4) + 1];
        for (?? r2 = r4; r2 <= iQ; r2++) {
            jArr[r2 - r4] = ((yf) arrayList.get(r2)).b.c.getId();
        }
        bookmarkModel.getClass();
        Object obj = ThreadUtils.a;
        long j = bookmarkModel.a;
        if (j != 0) {
            N.MgC7owSN(j, bookmarkModel, bookmarkIdK, jArr);
        }
        if (this.h.a()) {
            n3.X0("MobileBookmarkManagerDragReorder");
        }
    }

    /* JADX INFO: compiled from: BookmarkManagerMediator.java */
    public class a extends mg {
        public a() {
        }

        @Override // defpackage.mg
        public final void f() {
            gg ggVar = gg.this;
            if (ggVar.n() == 2) {
                ggVar.A(ggVar.b.peek());
            }
        }

        @Override // defpackage.mg
        public final void i(BookmarkItem bookmarkItem) {
            gg ggVar = gg.this;
            if (ggVar.n() == 2) {
                b bVar = ggVar.b;
                if (!bVar.isEmpty() && bookmarkItem.c.equals(bVar.peek().c)) {
                    ggVar.u(bVar.peek());
                    return;
                }
            }
            f();
        }

        @Override // defpackage.mg
        public final void k() {
            xf xfVar = gg.this.o;
            if (xfVar.j != null) {
                xfVar.o();
            }
        }

        @Override // defpackage.mg
        public final void l(BookmarkItem bookmarkItem, BookmarkItem bookmarkItem2, int i) {
            f();
            gg.this.m.b();
        }

        @Override // defpackage.mg
        public final void m(BookmarkItem bookmarkItem, BookmarkItem bookmarkItem2, boolean z) {
            gg ggVar = gg.this;
            if (ggVar.n() == 2 && bookmarkItem2.c.equals(ggVar.b.peek().c)) {
                BookmarkModel bookmarkModel = ggVar.j;
                if (bookmarkModel.o(true).contains(bookmarkItem2.c)) {
                    ggVar.c(bookmarkModel.n());
                } else {
                    ggVar.c(bookmarkItem.c);
                }
            }
            ggVar.o.o();
        }

        @Override // defpackage.mg
        public final void e() {
        }
    }
}
