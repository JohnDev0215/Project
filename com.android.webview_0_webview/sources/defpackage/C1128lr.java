package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: renamed from: lr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1128lr extends Su {
    public final /* synthetic */ C1183mr h;

    public C1128lr(C1183mr c1183mr) {
        this.h = c1183mr;
    }

    @Override // defpackage.Su
    public Object c() {
        EnumC0231Mq enumC0231Mq;
        C0197Kr c0197Kr = new C0197Kr();
        ArrayList<C0249Nq> arrayList = new ArrayList();
        for (AbstractC0125Gr abstractC0125Gr : c0197Kr.a) {
            arrayList.addAll(abstractC0125Gr.a());
        }
        HashMap map = new HashMap();
        for (C0249Nq c0249Nq : arrayList) {
            C0249Nq c0249Nq2 = (C0249Nq) map.get(c0249Nq.b);
            if (c0249Nq2 != null) {
                EnumC0231Mq enumC0231Mq2 = EnumC0231Mq.UPLOADED;
                long j = c0249Nq2.c;
                if (j == -1) {
                    j = c0249Nq.c;
                }
                c0249Nq2.c = j;
                c0249Nq2.f = (String) C0197Kr.a(c0249Nq2.f, c0249Nq.f);
                long j2 = c0249Nq2.g;
                if (j2 == -1) {
                    j2 = c0249Nq.g;
                }
                c0249Nq2.g = j2;
                c0249Nq2.d = (String) C0197Kr.a(c0249Nq2.d, c0249Nq.d);
                c0249Nq2.e = (List) C0197Kr.a(c0249Nq2.e, c0249Nq.e);
                EnumC0231Mq enumC0231Mq3 = c0249Nq2.a;
                if (enumC0231Mq3 == null || (enumC0231Mq = c0249Nq.a) == null) {
                    c0249Nq2.a = (EnumC0231Mq) C0197Kr.a(c0249Nq2.a, c0249Nq.a);
                } else if (enumC0231Mq3 == enumC0231Mq2 || enumC0231Mq == enumC0231Mq2) {
                    c0249Nq2.a = enumC0231Mq2;
                }
                long j3 = c0249Nq2.c;
                if (j3 != -1) {
                    long j4 = c0249Nq.c;
                    if (j4 != -1) {
                        c0249Nq2.c = Math.min(j3, j4);
                    }
                }
                long j5 = c0249Nq2.c;
                if (j5 == -1) {
                    j5 = c0249Nq.c;
                }
                c0249Nq2.c = j5;
            } else {
                map.put(c0249Nq.b, c0249Nq);
            }
        }
        ArrayList arrayList2 = new ArrayList(map.values());
        Collections.sort(arrayList2, new Comparator() { // from class: Jr
            @Override // java.util.Comparator
            public int compare(Object obj, Object obj2) {
                C0249Nq c0249Nq3 = (C0249Nq) obj;
                C0249Nq c0249Nq4 = (C0249Nq) obj2;
                long j6 = c0249Nq3.c;
                long j7 = c0249Nq4.c;
                if (j6 == j7) {
                    long j8 = c0249Nq3.g;
                    long j9 = c0249Nq4.g;
                    if (j8 == j9) {
                        return 0;
                    }
                    if (j8 < j9) {
                        return 1;
                    }
                } else if (j6 < j7) {
                    return 1;
                }
                return -1;
            }
        });
        return 20 >= arrayList2.size() ? arrayList2 : arrayList2.subList(0, 20);
    }

    @Override // defpackage.Su
    public void n(Object obj) {
        C1183mr c1183mr = this.h;
        c1183mr.a = (List) obj;
        c1183mr.notifyDataSetChanged();
    }
}
