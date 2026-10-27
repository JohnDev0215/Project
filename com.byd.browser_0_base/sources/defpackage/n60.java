package defpackage;

import defpackage.f81;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.chromium.components.offline_items_collection.OfflineItem;

/* JADX INFO: compiled from: DateOrderedListMutator.java */
/* JADX INFO: loaded from: classes.dex */
public final class n60 implements qq1 {
    public final um0 e;
    public final g81 f;
    public b81 g;
    public final ArrayList<f81> h = new ArrayList<>();

    /* JADX INFO: compiled from: DateOrderedListMutator.java */
    public interface a extends b81 {
        void c();

        void reset();
    }

    public n60(eh3 eh3Var, g81 g81Var, um0 um0Var) {
        this.f = g81Var;
        this.e = um0Var;
        eh3Var.a(this);
        b(eh3Var.f);
    }

    public final void a() {
        b81 b81Var = this.g;
        if (b81Var == null) {
            return;
        }
        b81Var.b(this.h);
    }

    @Override // defpackage.qq1
    public final void b(Collection<OfflineItem> collection) {
        Iterator<OfflineItem> it = collection.iterator();
        while (it.hasNext()) {
            this.h.add(new f81.f(it.next()));
        }
        a();
    }

    @Override // defpackage.qq1
    public final void d(Collection<OfflineItem> collection) {
        for (OfflineItem offlineItem : collection) {
            int i = 0;
            while (true) {
                ArrayList<f81> arrayList = this.h;
                if (i < arrayList.size()) {
                    if (offlineItem.e.equals(((f81.f) arrayList.get(i)).e.e)) {
                        arrayList.remove(i);
                    }
                    i++;
                }
            }
        }
        a();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0073  */
    @Override // defpackage.qq1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e(org.chromium.components.offline_items_collection.OfflineItem r6, org.chromium.components.offline_items_collection.OfflineItem r7) {
        /*
            r5 = this;
            long r0 = r6.q
            long r2 = r7.q
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            g81 r1 = r5.f
            if (r0 != 0) goto L73
            int r0 = r6.h
            int r2 = r7.h
            if (r0 != r2) goto L73
            um0 r0 = r5.e
            boolean r2 = r0.a(r6)
            boolean r0 = r0.a(r7)
            if (r2 == r0) goto L1d
            goto L73
        L1d:
            r6 = 0
            r0 = r6
        L1f:
            java.util.ArrayList<f81> r2 = r5.h
            int r3 = r2.size()
            if (r0 >= r3) goto L44
            ny r3 = r7.e
            java.lang.Object r4 = r2.get(r0)
            f81$f r4 = (f81.f) r4
            org.chromium.components.offline_items_collection.OfflineItem r4 = r4.e
            ny r4 = r4.e
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L41
            f81$f r3 = new f81$f
            r3.<init>(r7)
            r2.set(r0, r3)
        L41:
            int r0 = r0 + 1
            goto L1f
        L44:
            int r5 = r1.size()
            if (r6 >= r5) goto L81
            java.lang.Object r5 = r1.get(r6)
            f81 r5 = (defpackage.f81) r5
            boolean r0 = r5 instanceof f81.f
            if (r0 != 0) goto L55
            goto L70
        L55:
            f81$f r5 = (f81.f) r5
            ny r0 = r7.e
            org.chromium.components.offline_items_collection.OfflineItem r2 = r5.e
            ny r2 = r2.e
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L70
            r5.e = r7
            java.util.ArrayList r7 = r1.f
            r7.set(r6, r5)
            r5 = 0
            r7 = 1
            r1.q(r6, r7, r5)
            goto L81
        L70:
            int r6 = r6 + 1
            goto L44
        L73:
            java.util.List r6 = java.util.Collections.singletonList(r6)
            r5.d(r6)
            java.util.List r6 = java.util.Collections.singletonList(r7)
            r5.b(r6)
        L81:
            ee r5 = r1.g
            r5.d()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.n60.e(org.chromium.components.offline_items_collection.OfflineItem, org.chromium.components.offline_items_collection.OfflineItem):void");
    }
}
