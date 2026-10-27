package t9;

import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import n3.z;
import w9.r;
import w9.v;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final LinkedHashSet f22524p = new LinkedHashSet(Arrays.asList(w9.b.class, w9.h.class, w9.f.class, w9.i.class, v.class, w9.o.class, w9.l.class));

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Map f22525q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CharSequence f22526a;
    public boolean d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f22531h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f22532i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final l9.n f22533j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList f22534k;
    public final e l;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f22527b = 0;
    public int c = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f22528e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22529f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f22530g = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final LinkedHashMap f22535m = new LinkedHashMap();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final ArrayList f22536n = new ArrayList();
    public final LinkedHashSet o = new LinkedHashSet();

    static {
        HashMap map = new HashMap();
        map.put(w9.b.class, new a(0));
        map.put(w9.h.class, new a(2));
        map.put(w9.f.class, new a(1));
        map.put(w9.i.class, new a(3));
        map.put(v.class, new a(6));
        map.put(w9.o.class, new a(5));
        map.put(w9.l.class, new a(4));
        f22525q = Collections.unmodifiableMap(map);
    }

    public f(ArrayList arrayList, l9.n nVar, ArrayList arrayList2) {
        this.f22532i = arrayList;
        this.f22533j = nVar;
        this.f22534k = arrayList2;
        e eVar = new e(0);
        this.l = eVar;
        this.f22536n.add(eVar);
        this.o.add(eVar);
    }

    public final void a(y9.a aVar) {
        while (!h().b(aVar.d())) {
            e(h());
        }
        h().d().b(aVar.d());
        this.f22536n.add(aVar);
        this.o.add(aVar);
    }

    public final void b(o oVar) {
        k kVar = oVar.f22574b;
        kVar.a();
        for (w9.n nVar : kVar.c) {
            r rVar = oVar.f22573a;
            nVar.h();
            z zVar = (z) rVar.f19922e;
            nVar.f19922e = zVar;
            if (zVar != null) {
                zVar.f19923f = nVar;
            }
            nVar.f19923f = rVar;
            rVar.f19922e = nVar;
            z zVar2 = (z) rVar.f19921b;
            nVar.f19921b = zVar2;
            if (((z) nVar.f19922e) == null) {
                zVar2.c = nVar;
            }
            LinkedHashMap linkedHashMap = this.f22535m;
            String str = nVar.f23378g;
            if (!linkedHashMap.containsKey(str)) {
                linkedHashMap.put(str, nVar);
            }
        }
    }

    public final void c() {
        CharSequence charSequenceSubSequence;
        if (this.d) {
            int i7 = this.f22527b + 1;
            CharSequence charSequence = this.f22526a;
            CharSequence charSequenceSubSequence2 = charSequence.subSequence(i7, charSequence.length());
            int i10 = 4 - (this.c % 4);
            StringBuilder sb = new StringBuilder(charSequenceSubSequence2.length() + i10);
            for (int i11 = 0; i11 < i10; i11++) {
                sb.append(' ');
            }
            sb.append(charSequenceSubSequence2);
            charSequenceSubSequence = sb.toString();
        } else {
            CharSequence charSequence2 = this.f22526a;
            charSequenceSubSequence = charSequence2.subSequence(this.f22527b, charSequence2.length());
        }
        h().a(charSequenceSubSequence);
    }

    public final void d() {
        if (this.f22526a.charAt(this.f22527b) != '\t') {
            this.f22527b++;
            this.c++;
        } else {
            this.f22527b++;
            int i7 = this.c;
            this.c = (4 - (i7 % 4)) + i7;
        }
    }

    public final void e(y9.a aVar) {
        if (h() == aVar) {
            this.f22536n.remove(r0.size() - 1);
        }
        if (aVar instanceof o) {
            b((o) aVar);
        }
        aVar.c();
    }

    public final void f(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            e((y9.a) arrayList.get(size));
        }
    }

    public final void g() {
        int i7 = this.f22527b;
        int i10 = this.c;
        this.f22531h = true;
        int length = this.f22526a.length();
        while (true) {
            if (i7 >= length) {
                break;
            }
            char cCharAt = this.f22526a.charAt(i7);
            if (cCharAt == '\t') {
                i7++;
                i10 += 4 - (i10 % 4);
            } else if (cCharAt != ' ') {
                this.f22531h = false;
                break;
            } else {
                i7++;
                i10++;
            }
        }
        this.f22528e = i7;
        this.f22529f = i10;
        this.f22530g = i10 - this.c;
    }

    public final y9.a h() {
        return (y9.a) AbstractC1347d.e(1, this.f22536n);
    }

    /* JADX WARN: Code restructure failed: missing block: B:113:0x01e4, code lost:
    
        if (r13 < 1) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x01e6, code lost:
    
        r13 = r3 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01ec, code lost:
    
        if (r13 >= r10.length()) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x01ee, code lost:
    
        r14 = r10.charAt(r13);
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x01f6, code lost:
    
        if (r14 == '\t') goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:119:0x01fa, code lost:
    
        if (r14 == ' ') goto L122;
     */
    /* JADX WARN: Code restructure failed: missing block: B:121:0x01fd, code lost:
    
        r22 = r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x01ff, code lost:
    
        r3 = r10.subSequence(r7, r3).toString();
        r14 = new w9.q();
        r14.f23382h = java.lang.Integer.parseInt(r3);
        r14.f23383i = r12;
        r3 = new t9.l(r14, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:342:0x063b, code lost:
    
        k(r21.f22528e);
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:106:0x01d0. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0518  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0532  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x053d  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0189 A[PHI: r19 r22
  0x0189: PHI (r19v8 boolean) = 
  (r19v4 boolean)
  (r19v4 boolean)
  (r19v5 boolean)
  (r19v5 boolean)
  (r19v5 boolean)
  (r19v6 boolean)
  (r19v6 boolean)
  (r19v6 boolean)
  (r19v10 boolean)
  (r19v15 boolean)
 binds: [B:263:0x04ab, B:269:0x04c2, B:187:0x0350, B:189:0x0358, B:391:0x0189, B:180:0x031c, B:182:0x0320, B:184:0x032c, B:159:0x0294, B:84:0x0185] A[DONT_GENERATE, DONT_INLINE]
  0x0189: PHI (r22v7 java.util.Iterator) = 
  (r22v3 java.util.Iterator)
  (r22v3 java.util.Iterator)
  (r22v4 java.util.Iterator)
  (r22v4 java.util.Iterator)
  (r22v4 java.util.Iterator)
  (r22v5 java.util.Iterator)
  (r22v5 java.util.Iterator)
  (r22v5 java.util.Iterator)
  (r22v9 java.util.Iterator)
  (r22v16 java.util.Iterator)
 binds: [B:263:0x04ab, B:269:0x04c2, B:187:0x0350, B:189:0x0358, B:391:0x0189, B:180:0x031c, B:182:0x0320, B:184:0x032c, B:159:0x0294, B:84:0x0185] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(java.lang.String r22) {
        /*
            Method dump skipped, instruction units count: 1692
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: t9.f.i(java.lang.String):void");
    }

    public final void j(int i7) {
        int i10;
        int i11 = this.f22529f;
        if (i7 >= i11) {
            this.f22527b = this.f22528e;
            this.c = i11;
        }
        int length = this.f22526a.length();
        while (true) {
            i10 = this.c;
            if (i10 >= i7 || this.f22527b == length) {
                break;
            } else {
                d();
            }
        }
        if (i10 <= i7) {
            this.d = false;
            return;
        }
        this.f22527b--;
        this.c = i7;
        this.d = true;
    }

    public final void k(int i7) {
        int i10 = this.f22528e;
        if (i7 >= i10) {
            this.f22527b = i10;
            this.c = this.f22529f;
        }
        int length = this.f22526a.length();
        while (true) {
            int i11 = this.f22527b;
            if (i11 >= i7 || i11 == length) {
                break;
            } else {
                d();
            }
        }
        this.d = false;
    }
}
