package t9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import n3.z;
import w9.u;

/* JADX INFO: loaded from: classes.dex */
public final class j {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Pattern f22544i = Pattern.compile("^[!\"#\\$%&'\\(\\)\\*\\+,\\-\\./:;<=>\\?@\\[\\\\\\]\\^_`\\{\\|\\}~\\p{Pc}\\p{Pd}\\p{Pe}\\p{Pf}\\p{Pi}\\p{Po}\\p{Ps}]");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Pattern f22545j = Pattern.compile("^(?:<[A-Za-z][A-Za-z0-9-]*(?:\\s+[a-zA-Z_:][a-zA-Z0-9:._-]*(?:\\s*=\\s*(?:[^\"'=<>`\\x00-\\x20]+|'[^']*'|\"[^\"]*\"))?)*\\s*/?>|</[A-Za-z][A-Za-z0-9-]*\\s*[>]|<!---->|<!--(?:-?[^>-])(?:-?[^-])*-->|[<][?].*?[?][>]|<![A-Z]+\\s+[^>]*>|<!\\[CDATA\\[[\\s\\S]*?\\]\\]>)", 2);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Pattern f22546k = Pattern.compile("^[!\"#$%&'()*+,./:;<=>?@\\[\\\\\\]^_`{|}~-]");
    public static final Pattern l = Pattern.compile("^&(?:#x[a-f0-9]{1,6}|#[0-9]{1,7}|[a-z][a-z0-9]{1,31});", 2);

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f22547m = Pattern.compile("`+");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Pattern f22548n = Pattern.compile("^`+");
    public static final Pattern o = Pattern.compile("^<([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*)>");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Pattern f22549p = Pattern.compile("^<[a-zA-Z][a-zA-Z0-9.+-]{1,31}:[^<>\u0000- ]*>");

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final Pattern f22550q = Pattern.compile("^ *(?:\n *)?");

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Pattern f22551r = Pattern.compile("^[\\p{Zs}\t\r\n\f]");

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Pattern f22552s = Pattern.compile("\\s+");

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final Pattern f22553t = Pattern.compile(" *$");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final BitSet f22554a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final BitSet f22555b;
    public final HashMap c;
    public final m2.b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f22556e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f22557f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public d f22558g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c f22559h;

    public j(m2.b bVar) {
        ArrayList arrayList = (ArrayList) bVar.l;
        HashMap map = new HashMap();
        b(Arrays.asList(new u9.a('*'), new u9.a('_')), map);
        b(arrayList, map);
        this.c = map;
        Set setKeySet = map.keySet();
        BitSet bitSet = new BitSet();
        Iterator it = setKeySet.iterator();
        while (it.hasNext()) {
            bitSet.set(((Character) it.next()).charValue());
        }
        this.f22555b = bitSet;
        BitSet bitSet2 = new BitSet();
        bitSet2.or(bitSet);
        bitSet2.set(10);
        bitSet2.set(96);
        bitSet2.set(91);
        bitSet2.set(93);
        bitSet2.set(92);
        bitSet2.set(33);
        bitSet2.set(60);
        bitSet2.set(38);
        this.f22554a = bitSet2;
        this.d = bVar;
    }

    public static void a(char c, z9.a aVar, HashMap map) {
        if (((z9.a) map.put(Character.valueOf(c), aVar)) == null) {
            return;
        }
        throw new IllegalArgumentException("Delimiter processor conflict with delimiter char '" + c + "'");
    }

    public static void b(List list, HashMap map) {
        p pVar;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            z9.a aVar = (z9.a) it.next();
            char cE = aVar.e();
            char cA = aVar.a();
            if (cE == cA) {
                z9.a aVar2 = (z9.a) map.get(Character.valueOf(cE));
                if (aVar2 == null || aVar2.e() != aVar2.a()) {
                    a(cE, aVar, map);
                } else {
                    if (aVar2 instanceof p) {
                        pVar = (p) aVar2;
                    } else {
                        p pVar2 = new p(cE);
                        pVar2.f(aVar2);
                        pVar = pVar2;
                    }
                    pVar.f(aVar);
                    map.put(Character.valueOf(cE), pVar);
                }
            } else {
                a(cE, aVar, map);
                a(cA, aVar, map);
            }
        }
    }

    public static void d(u uVar, u uVar2, int i7) {
        if (uVar == null || uVar2 == null || uVar == uVar2) {
            return;
        }
        StringBuilder sb = new StringBuilder(i7);
        sb.append(uVar.f23384g);
        z zVar = (z) uVar.f19923f;
        z zVar2 = (z) uVar2.f19923f;
        while (zVar != zVar2) {
            sb.append(((u) zVar).f23384g);
            z zVar3 = (z) zVar.f19923f;
            zVar.h();
            zVar = zVar3;
        }
        uVar.f23384g = sb.toString();
    }

    public static void e(z zVar, z zVar2) {
        u uVar = null;
        u uVar2 = null;
        int length = 0;
        while (zVar != null) {
            if (zVar instanceof u) {
                uVar2 = (u) zVar;
                if (uVar == null) {
                    uVar = uVar2;
                }
                length = uVar2.f23384g.length() + length;
            } else {
                d(uVar, uVar2, length);
                uVar = null;
                uVar2 = null;
                length = 0;
            }
            if (zVar == zVar2) {
                break;
            } else {
                zVar = (z) zVar.f19923f;
            }
        }
        d(uVar, uVar2, length);
    }

    public final String c(Pattern pattern) {
        if (this.f22557f >= this.f22556e.length()) {
            return null;
        }
        Matcher matcher = pattern.matcher(this.f22556e);
        matcher.region(this.f22557f, this.f22556e.length());
        if (!matcher.find()) {
            return null;
        }
        this.f22557f = matcher.end();
        return matcher.group();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01de  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01f1 A[PHI: r5
  0x01f1: PHI (r5v36 char) = (r5v35 char), (r5v37 char), (r5v38 char) binds: [B:112:0x01e6, B:114:0x01ea, B:117:0x01ef] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x051b A[LOOP:0: B:3:0x0014->B:266:0x051b, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0522 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f6  */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v10, types: [w9.u] */
    /* JADX WARN: Type inference failed for: r14v11 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v21 */
    /* JADX WARN: Type inference failed for: r14v22 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v6 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r18v0, types: [n3.z] */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v51 */
    /* JADX WARN: Type inference failed for: r5v53, types: [boolean] */
    /* JADX WARN: Type inference failed for: r5v60, types: [n3.z] */
    /* JADX WARN: Type inference failed for: r5v61 */
    /* JADX WARN: Type inference failed for: r5v62 */
    /* JADX WARN: Type inference failed for: r5v63 */
    /* JADX WARN: Type inference failed for: r5v64 */
    /* JADX WARN: Type inference failed for: r6v25, types: [e2.i0] */
    /* JADX WARN: Type inference failed for: r6v27 */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r8v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v27 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void f(java.lang.String r17, n3.z r18) {
        /*
            Method dump skipped, instruction units count: 1344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: t9.j.f(java.lang.String, n3.z):void");
    }

    public final char g() {
        if (this.f22557f < this.f22556e.length()) {
            return this.f22556e.charAt(this.f22557f);
        }
        return (char) 0;
    }

    public final void h(d dVar) {
        boolean z6;
        z zVar;
        HashMap map = new HashMap();
        d dVar2 = this.f22558g;
        while (dVar2 != null) {
            d dVar3 = dVar2.f22518e;
            if (dVar3 == dVar) {
                break;
            } else {
                dVar2 = dVar3;
            }
        }
        while (dVar2 != null) {
            HashMap map2 = this.c;
            char c = dVar2.f22517b;
            z9.a aVar = (z9.a) map2.get(Character.valueOf(c));
            if (!dVar2.d || aVar == null) {
                dVar2 = dVar2.f22519f;
            } else {
                char cE = aVar.e();
                d dVar4 = dVar2.f22518e;
                int iD = 0;
                boolean z10 = false;
                while (dVar4 != null && dVar4 != dVar && dVar4 != map.get(Character.valueOf(c))) {
                    if (dVar4.c && dVar4.f22517b == cE) {
                        iD = aVar.d(dVar4, dVar2);
                        z10 = true;
                        if (iD > 0) {
                            z6 = true;
                            break;
                        }
                    }
                    dVar4 = dVar4.f22518e;
                }
                z6 = z10;
                z10 = false;
                if (z10) {
                    u uVar = dVar4.f22516a;
                    dVar4.f22520g -= iD;
                    dVar2.f22520g -= iD;
                    String str = uVar.f23384g;
                    uVar.f23384g = str.substring(0, str.length() - iD);
                    u uVar2 = dVar2.f22516a;
                    String str2 = uVar2.f23384g;
                    uVar2.f23384g = str2.substring(0, str2.length() - iD);
                    d dVar5 = dVar2.f22518e;
                    while (dVar5 != null && dVar5 != dVar4) {
                        d dVar6 = dVar5.f22518e;
                        i(dVar5);
                        dVar5 = dVar6;
                    }
                    if (uVar != uVar2 && (zVar = (z) uVar.f19923f) != uVar2) {
                        e(zVar, (z) uVar2.f19922e);
                    }
                    aVar.b(uVar, uVar2, iD);
                    if (dVar4.f22520g == 0) {
                        dVar4.f22516a.h();
                        i(dVar4);
                    }
                    if (dVar2.f22520g == 0) {
                        d dVar7 = dVar2.f22519f;
                        uVar2.h();
                        i(dVar2);
                        dVar2 = dVar7;
                    }
                } else {
                    if (!z6) {
                        map.put(Character.valueOf(c), dVar2.f22518e);
                        if (!dVar2.c) {
                            i(dVar2);
                        }
                    }
                    dVar2 = dVar2.f22519f;
                }
            }
        }
        while (true) {
            d dVar8 = this.f22558g;
            if (dVar8 == null || dVar8 == dVar) {
                return;
            } else {
                i(dVar8);
            }
        }
    }

    public final void i(d dVar) {
        d dVar2 = dVar.f22518e;
        if (dVar2 != null) {
            dVar2.f22519f = dVar.f22519f;
        }
        d dVar3 = dVar.f22519f;
        if (dVar3 == null) {
            this.f22558g = dVar2;
        } else {
            dVar3.f22518e = dVar2;
        }
    }
}
