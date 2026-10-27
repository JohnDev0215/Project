package n6;

import V8.AbstractC0828q;
import V8.f0;
import com.stingray.karaoke.scoring.NoteResult;
import h8.AbstractC1387d;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import n7.AbstractC1998m;

/* JADX INFO: renamed from: n6.H, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1967H {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final f0 f20002A;
    public final V8.N B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final z f20003a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final E6.b f20004b;
    public final B6.b c;
    public final LinkedHashMap d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashMap f20005e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LinkedHashMap f20006f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final LinkedHashMap f20007g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f20008h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final f0 f20009i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final V8.N f20010j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final f0 f20011k;
    public final V8.N l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final f0 f20012m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final V8.N f20013n;
    public final f0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final V8.N f20014p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final f0 f20015q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final V8.N f20016r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final f0 f20017s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final V8.N f20018t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final f0 f20019u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final V8.N f20020v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final f0 f20021w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final V8.N f20022x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final f0 f20023y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final V8.N f20024z;

    public C1967H(z zVar, E6.b bVar, B6.b bVar2) {
        A7.m.f(zVar, "midiManager");
        A7.m.f(bVar, "scoringLogger");
        A7.m.f(bVar2, "featureLogger");
        this.f20003a = zVar;
        this.f20004b = bVar;
        this.c = bVar2;
        this.d = new LinkedHashMap();
        this.f20005e = new LinkedHashMap();
        this.f20006f = new LinkedHashMap();
        this.f20007g = new LinkedHashMap();
        this.f20008h = new ArrayList();
        f0 f0VarC = AbstractC0828q.c(0);
        this.f20009i = f0VarC;
        this.f20010j = new V8.N(f0VarC);
        f0 f0VarC2 = AbstractC0828q.c(0);
        this.f20011k = f0VarC2;
        this.l = new V8.N(f0VarC2);
        f0 f0VarC3 = AbstractC0828q.c(Float.valueOf(0.0f));
        this.f20012m = f0VarC3;
        this.f20013n = new V8.N(f0VarC3);
        f0 f0VarC4 = AbstractC0828q.c(0);
        this.o = f0VarC4;
        this.f20014p = new V8.N(f0VarC4);
        f0 f0VarC5 = AbstractC0828q.c(0);
        this.f20015q = f0VarC5;
        this.f20016r = new V8.N(f0VarC5);
        f0 f0VarC6 = AbstractC0828q.c(1);
        this.f20017s = f0VarC6;
        this.f20018t = new V8.N(f0VarC6);
        f0 f0VarC7 = AbstractC0828q.c(1);
        this.f20019u = f0VarC7;
        this.f20020v = new V8.N(f0VarC7);
        f0 f0VarC8 = AbstractC0828q.c("");
        this.f20021w = f0VarC8;
        this.f20022x = new V8.N(f0VarC8);
        f0 f0VarC9 = AbstractC0828q.c(Boolean.FALSE);
        this.f20023y = f0VarC9;
        this.f20024z = new V8.N(f0VarC9);
        f0 f0VarC10 = AbstractC0828q.c("");
        this.f20002A = f0VarC10;
        this.B = new V8.N(f0VarC10);
    }

    public static String a(LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2, int i7) {
        List listT = (List) linkedHashMap.get(Integer.valueOf(i7));
        if (listT == null) {
            return "";
        }
        Integer numValueOf = Integer.valueOf(i7);
        Object obj = linkedHashMap2.get(numValueOf);
        if (obj == null) {
            obj = -1;
            linkedHashMap2.put(numValueOf, obj);
        }
        int iIntValue = ((Number) obj).intValue() + 1;
        if (iIntValue < listT.size()) {
            linkedHashMap2.put(Integer.valueOf(i7), Integer.valueOf(iIntValue));
            return (String) listT.get(iIntValue);
        }
        String str = (String) AbstractC1998m.h0(listT);
        linkedHashMap2.put(Integer.valueOf(i7), 0);
        do {
            listT = AbstractC1387d.t(listT);
        } while (A7.m.a(AbstractC1998m.h0(listT), str));
        linkedHashMap.put(Integer.valueOf(i7), listT);
        return (String) ((ArrayList) listT).get(0);
    }

    public final void b(C1960A c1960a, long j3, boolean z6) {
        Object objPrevious;
        A7.m.f(c1960a, "currentNote");
        ArrayList arrayList = c1960a.f19984k;
        ListIterator listIterator = arrayList.listIterator(arrayList.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                objPrevious = listIterator.previous();
                if (((Number) ((m7.i) objPrevious).l).longValue() <= j3) {
                    break;
                }
            } else {
                objPrevious = null;
                break;
            }
        }
        m7.i iVar = (m7.i) objPrevious;
        int i7 = 0;
        while (iVar != null) {
            NoteResult noteResult = NoteResult.UNKNOWN;
            Object obj = iVar.l;
            Object obj2 = iVar.f19259m;
            if (obj2 != noteResult && (obj2 != NoteResult.MISS || ((Number) obj).longValue() - c1960a.f19976a >= c1960a.f19980g)) {
                break;
            }
            int iIndexOf = c1960a.f19984k.indexOf(iVar);
            c1960a.f19984k.set(iIndexOf, new m7.i(obj, z6 ? NoteResult.HIT : NoteResult.MISS));
            iVar = (m7.i) AbstractC1998m.b0(iIndexOf - 1, c1960a.f19984k);
            i7++;
        }
        if (z6) {
            for (int i10 = 0; i10 < i7; i10++) {
                f0 f0Var = this.f20009i;
                f0Var.m(null, Integer.valueOf((((Number) this.f20017s.getValue()).intValue() * 10) + ((Number) f0Var.getValue()).intValue()));
            }
        }
    }
}
