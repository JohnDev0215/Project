package kotlin.sequences;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p.bv0;
import p.jb2;
import p.nb2;
import p.rn2;
import p.ry0;
import p.sb;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends nb2 {
    public static bv0 I0(jb2 jb2Var, jb2 jb2Var2) {
        v03.j(jb2Var2, "elements");
        jb2 sbVar = new sb(0, new jb2[]{jb2Var, jb2Var2});
        boolean z = sbVar instanceof rn2;
        SequencesKt__SequencesKt$flatten$1 sequencesKt__SequencesKt$flatten$1 = new ry0() { // from class: kotlin.sequences.SequencesKt__SequencesKt$flatten$1
            @Override // p.ry0
            public final Object invoke(Object obj) {
                jb2 jb2Var3 = (jb2) obj;
                v03.j(jb2Var3, "it");
                return jb2Var3.iterator();
            }
        };
        if (!z) {
            return new bv0(sbVar, new ry0() { // from class: kotlin.sequences.SequencesKt__SequencesKt$flatten$3
                @Override // p.ry0
                public final Object invoke(Object obj) {
                    return obj;
                }
            }, sequencesKt__SequencesKt$flatten$1);
        }
        rn2 rn2Var = (rn2) sbVar;
        v03.j(sequencesKt__SequencesKt$flatten$1, "iterator");
        return new bv0(rn2Var.a, rn2Var.b, sequencesKt__SequencesKt$flatten$1);
    }

    public static List J0(jb2 jb2Var) {
        ArrayList arrayList = new ArrayList();
        Iterator it = jb2Var.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return v03.F(arrayList);
    }
}
