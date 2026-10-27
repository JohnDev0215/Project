package defpackage;

import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.View;
import defpackage.pi1;
import defpackage.tz;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.offlinepages.OfflinePageItem;
import org.chromium.chrome.browser.suggestions.tile.SuggestionsTileView;
import org.chromium.url.GURL;
import tz.b;

/* JADX INFO: compiled from: TileGroup.java */
/* JADX INFO: loaded from: classes2.dex */
public final class aa3 implements ni1 {
    public final kw2 a;
    public final tz b;
    public final b c;
    public final c d;
    public final ka3 e;
    public final ArrayList f = new ArrayList();
    public final d g;
    public SparseArray<List<w93>> h;
    public ArrayList i;
    public GURL j;
    public GURL k;
    public boolean l;
    public final a m;

    /* JADX INFO: compiled from: TileGroup.java */
    public class a {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0011  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final defpackage.z93 a(defpackage.w93 r5) {
            /*
                r4 = this;
                aa3 r0 = defpackage.aa3.this
                boolean r1 = r0.b()
                r2 = 0
                if (r1 == 0) goto L11
                ao2 r1 = r5.a
                int r1 = r1.e
                r3 = 1
                if (r1 != r3) goto L11
                goto L12
            L11:
                r3 = r2
            L12:
                if (r3 == 0) goto L1e
                java.util.ArrayList r0 = r0.f
                r1 = 3
                java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
                r0.add(r1)
            L1e:
                z93 r0 = new z93
                r0.<init>(r4, r2, r5, r3)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: aa3.a.a(w93):z93");
        }
    }

    /* JADX INFO: compiled from: TileGroup.java */
    public interface b {
        void a(int i, w93 w93Var);

        void b(List<w93> list);
    }

    /* JADX INFO: compiled from: TileGroup.java */
    public interface c {
    }

    /* JADX INFO: compiled from: TileGroup.java */
    public class d extends iw2<w93> {
        public d(OfflinePageBridge offlinePageBridge) {
            super(offlinePageBridge);
        }

        @Override // defpackage.iw2
        public final ArrayList d() {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                aa3 aa3Var = aa3.this;
                if (i >= aa3Var.h.size()) {
                    return arrayList;
                }
                arrayList.addAll(aa3Var.h.valueAt(i));
                i++;
            }
        }

        @Override // defpackage.iw2
        public final void e(dq1 dq1Var, OfflinePageItem offlinePageItem) {
            w93 w93Var = (w93) dq1Var;
            boolean z = w93Var.g != null;
            Long lValueOf = offlinePageItem == null ? null : Long.valueOf(offlinePageItem.b);
            w93Var.g = lValueOf;
            if (z == (lValueOf != null)) {
                return;
            }
            ti1 ti1Var = (ti1) aa3.this.d;
            ti1Var.getClass();
            SuggestionsTileView suggestionsTileViewA = ti1Var.a(w93Var.a);
            if (suggestionsTileViewA != null) {
                suggestionsTileViewA.setOfflineBadgeVisibility(w93Var.g != null);
            }
            Runnable runnable = ti1Var.n;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    /* JADX INFO: compiled from: TileGroup.java */
    public class e implements View.OnClickListener, View.OnCreateContextMenuListener, tz.a {
        public final ao2 e;
        public Runnable f;
        public Runnable g;

        public e(ao2 ao2Var) {
            this.e = ao2Var;
        }

        public final void a(int i) {
            aa3 aa3Var = aa3.this;
            w93 w93VarA = aa3Var.a(this.e);
            if (w93VarA == null) {
                return;
            }
            aa3Var.c.a(i, w93VarA);
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            aa3 aa3Var = aa3.this;
            w93 w93VarA = aa3Var.a(this.e);
            if (w93VarA == null) {
                return;
            }
            n3.X0("Suggestions.Tile.Tapped");
            Runnable runnable = this.f;
            if (runnable != null) {
                runnable.run();
            }
            aa3Var.c.a(1, w93VarA);
        }

        @Override // android.view.View.OnCreateContextMenuListener
        public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            tz tzVar = aa3.this.b;
            tzVar.getClass();
            tz.b bVar = tzVar.new b(this);
            boolean z = false;
            for (int i = 0; i < 8; i++) {
                if (tzVar.c(i, this) && i != 2) {
                    z = true;
                    if (i == 1 && tzVar.c(2, this)) {
                        if (CachedFeatureFlags.b(ar.d.b("TabGridLayoutAndroid:show_open_in_tab_group_menu_item_first"), false)) {
                            contextMenu.add(0, 2, 0, tzVar.b(view.getContext(), 2)).setOnMenuItemClickListener(bVar);
                            contextMenu.add(0, i, 0, tzVar.b(view.getContext(), i)).setOnMenuItemClickListener(bVar);
                        } else {
                            contextMenu.add(0, i, 0, tzVar.b(view.getContext(), i)).setOnMenuItemClickListener(bVar);
                            contextMenu.add(0, 2, 0, tzVar.b(view.getContext(), 2)).setOnMenuItemClickListener(bVar);
                        }
                    } else {
                        contextMenu.add(0, i, 0, tzVar.b(view.getContext(), i)).setOnMenuItemClickListener(bVar);
                    }
                }
            }
            if (z) {
                tzVar.b.c(false);
                tzVar.e = view;
                view.addOnAttachStateChangeListener(new sz(tzVar));
                n3.X0(tzVar.d + ".ContextMenu.Shown");
            }
        }
    }

    public aa3(ka3 ka3Var, qi1 qi1Var, tz tzVar, ca3 ca3Var, ti1 ti1Var, OfflinePageBridge offlinePageBridge) {
        SparseArray<List<w93>> sparseArray = new SparseArray<>();
        sparseArray.put(1, new ArrayList());
        this.h = sparseArray;
        this.m = new a();
        this.a = qi1Var;
        this.b = tzVar;
        this.c = ca3Var;
        this.d = ti1Var;
        this.e = ka3Var;
        d dVar = new d(offlinePageBridge);
        this.g = dVar;
        qi1Var.a.add(dVar);
    }

    public final w93 a(ao2 ao2Var) {
        if (this.h.get(ao2Var.e) == null) {
            return null;
        }
        for (w93 w93Var : this.h.get(ao2Var.e)) {
            if (w93Var.a.equals(ao2Var)) {
                return w93Var;
            }
        }
        return null;
    }

    public final boolean b() {
        ArrayList arrayList = this.f;
        return arrayList.contains(1) || arrayList.contains(2);
    }

    public final void c() {
        boolean z = !this.l;
        this.l = true;
        List<w93> list = this.h.get(1);
        int size = list == null ? 0 : list.size();
        SparseArray<List<w93>> sparseArray = new SparseArray<>();
        sparseArray.put(1, new ArrayList());
        boolean z2 = z;
        int i = 0;
        while (true) {
            w93 w93Var = null;
            if (i >= this.i.size()) {
                break;
            }
            ao2 ao2Var = (ao2) this.i.get(i);
            w93 w93VarA = a(ao2Var);
            if (w93VarA == null) {
                w93VarA = new w93(ao2Var, i);
                z2 = true;
            }
            int i2 = ao2Var.e;
            List<w93> arrayList = sparseArray.get(i2);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                sparseArray.append(i2, arrayList);
            }
            Iterator<w93> it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                w93 next = it.next();
                if (next.a.b.equals(ao2Var.b)) {
                    w93Var = next;
                    break;
                }
            }
            if (w93Var == null) {
                arrayList.add(w93VarA);
            }
            i++;
        }
        this.h = sparseArray;
        this.i = null;
        List<w93> list2 = sparseArray.get(1);
        boolean z3 = z || (list2 == null ? 0 : list2.size()) != size;
        if (z2 || z3) {
            this.g.f();
            c cVar = this.d;
            if (z3) {
                ti1 ti1Var = (ti1) cVar;
                Runnable runnable = ti1Var.o;
                if (runnable != null) {
                    runnable.run();
                }
                ti1Var.d();
            }
            if (b()) {
                this.f.add(2);
            }
            ti1 ti1Var2 = (ti1) cVar;
            if (ti1Var2.r.h.size() >= 1) {
                ti1Var2.q.c(ti1Var2.r.h.get(1), ti1Var2.g, ti1Var2.r.m);
                aa3 aa3Var = ti1Var2.r;
                if (aa3Var.b()) {
                    aa3Var.d(2);
                }
                ti1Var2.e();
                Runnable runnable2 = ti1Var2.n;
                if (runnable2 != null) {
                    runnable2.run();
                }
                Object obj = pi1.c;
                pi1 pi1Var = pi1.a.a;
                List<w93> list3 = ti1Var2.r.h.get(1);
                pi1Var.getClass();
                l91 l91Var = new l91(pi1Var, 28, list3);
                if (pi1Var.a != null) {
                    pi1Var.b = l91Var;
                    list3.size();
                } else {
                    pi1Var.a = l91Var;
                    pi1Var.b = null;
                    Log.i("cr_TopSites", "Start a new task.");
                    pi1Var.a.run();
                }
            }
            if (z) {
                d(1);
            }
        }
    }

    public final void d(int i) {
        ArrayList arrayList = this.f;
        arrayList.remove(Integer.valueOf(i));
        if (arrayList.isEmpty()) {
            this.c.b(this.h.get(1));
        }
    }
}
