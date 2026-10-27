package defpackage;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: l5, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1087l5 extends AbstractC0921i6 {
    public ArrayList h = new ArrayList();
    public ArrayList i = new ArrayList();
    public ArrayList j = new ArrayList();
    public ArrayList k = new ArrayList();
    public ArrayList l = new ArrayList();
    public ArrayList m = new ArrayList();
    public ArrayList n = new ArrayList();
    public ArrayList o = new ArrayList();
    public ArrayList p = new ArrayList();
    public ArrayList q = new ArrayList();
    public ArrayList r = new ArrayList();

    @Override // defpackage.Q5
    public void c() {
        ArrayList arrayList;
        int size;
        ArrayList arrayList2;
        int size2;
        ArrayList arrayList3;
        int size3;
        int size4 = this.j.size() - 1;
        if (size4 >= 0) {
            throw null;
        }
        int size5 = this.h.size();
        while (true) {
            size5--;
            if (size5 < 0) {
                break;
            }
            a((AbstractC0697e6) this.h.get(size5));
            this.h.remove(size5);
        }
        int size6 = this.i.size() - 1;
        if (size6 >= 0) {
            throw null;
        }
        int size7 = this.k.size() - 1;
        if (size7 >= 0) {
            throw null;
        }
        this.k.clear();
        if (d()) {
            int size8 = this.m.size();
            do {
                size8--;
                if (size8 < 0) {
                    int size9 = this.l.size();
                    do {
                        size9--;
                        if (size9 < 0) {
                            int size10 = this.n.size();
                            do {
                                size10--;
                                if (size10 < 0) {
                                    e(this.q);
                                    e(this.p);
                                    e(this.o);
                                    e(this.r);
                                    b();
                                    return;
                                }
                                arrayList = (ArrayList) this.n.get(size10);
                                size = arrayList.size() - 1;
                            } while (size < 0);
                            throw null;
                        }
                        arrayList2 = (ArrayList) this.l.get(size9);
                        size2 = arrayList2.size() - 1;
                    } while (size2 < 0);
                    throw null;
                }
                arrayList3 = (ArrayList) this.m.get(size8);
                size3 = arrayList3.size() - 1;
            } while (size3 < 0);
            throw null;
        }
    }

    @Override // defpackage.Q5
    public boolean d() {
        return (this.i.isEmpty() && this.k.isEmpty() && this.j.isEmpty() && this.h.isEmpty() && this.p.isEmpty() && this.q.isEmpty() && this.o.isEmpty() && this.r.isEmpty() && this.m.isEmpty() && this.l.isEmpty() && this.n.isEmpty()) ? false : true;
    }

    public void e(List list) {
        int size = list.size() - 1;
        if (size < 0) {
            return;
        }
        throw null;
    }
}
