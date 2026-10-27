package m2;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import m2.g;
import m2.k;

/* JADX INFO: compiled from: CSSParser.java */
/* JADX INFO: loaded from: classes.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private f f8074a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private u f8075b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f8076c = false;

    /* JADX INFO: compiled from: CSSParser.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f8077a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f8078b;

        static {
            int[] iArr = new int[j.values().length];
            f8078b = iArr;
            try {
                iArr[j.first_child.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8078b[j.last_child.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8078b[j.only_child.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8078b[j.first_of_type.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8078b[j.last_of_type.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8078b[j.only_of_type.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8078b[j.root.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8078b[j.empty.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8078b[j.nth_child.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f8078b[j.nth_last_child.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f8078b[j.nth_of_type.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f8078b[j.nth_last_of_type.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f8078b[j.not.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f8078b[j.target.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f8078b[j.lang.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f8078b[j.link.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f8078b[j.visited.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f8078b[j.hover.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f8078b[j.active.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f8078b[j.focus.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f8078b[j.enabled.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f8078b[j.disabled.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                f8078b[j.checked.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                f8078b[j.indeterminate.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            int[] iArr2 = new int[c.values().length];
            f8077a = iArr2;
            try {
                iArr2[c.EQUALS.ordinal()] = 1;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                f8077a[c.INCLUDES.ordinal()] = 2;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                f8077a[c.DASHMATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused27) {
            }
        }
    }

    /* JADX INFO: renamed from: m2.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CSSParser.java */
    private static class C0123b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final String f8079a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c f8080b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final String f8081c;

        C0123b(String str, c cVar, String str2) {
            this.f8079a = str;
            this.f8080b = cVar;
            this.f8081c = str2;
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private enum c {
        EXISTS,
        EQUALS,
        INCLUDES,
        DASHMATCH
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private static class d extends k.i {

        /* JADX INFO: compiled from: CSSParser.java */
        private static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public int f8083a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public int f8084b;

            a(int i7, int i8) {
                this.f8083a = i7;
                this.f8084b = i8;
            }
        }

        d(String str) {
            super(str.replaceAll("(?s)/\\*.*?\\*/", ""));
        }

        private int C(int i7) {
            if (i7 >= 48 && i7 <= 57) {
                return i7 - 48;
            }
            int i8 = 65;
            if (i7 < 65 || i7 > 70) {
                i8 = 97;
                if (i7 < 97 || i7 > 102) {
                    return -1;
                }
            }
            return (i7 - i8) + 10;
        }

        private a D() {
            m2.c cVarC;
            a aVar;
            if (h()) {
                return null;
            }
            int i7 = this.f8389b;
            if (!f('(')) {
                return null;
            }
            A();
            int i8 = 1;
            if (g("odd")) {
                aVar = new a(2, 1);
            } else {
                if (g("even")) {
                    aVar = new a(2, 0);
                } else {
                    int i9 = (!f('+') && f('-')) ? -1 : 1;
                    m2.c cVarC2 = m2.c.c(this.f8388a, this.f8389b, this.f8390c, false);
                    if (cVarC2 != null) {
                        this.f8389b = cVarC2.a();
                    }
                    if (f('n') || f('N')) {
                        if (cVarC2 == null) {
                            cVarC2 = new m2.c(1L, this.f8389b);
                        }
                        A();
                        boolean zF = f('+');
                        if (!zF && (zF = f('-'))) {
                            i8 = -1;
                        }
                        if (zF) {
                            A();
                            cVarC = m2.c.c(this.f8388a, this.f8389b, this.f8390c, false);
                            if (cVarC == null) {
                                this.f8389b = i7;
                                return null;
                            }
                            this.f8389b = cVarC.a();
                        } else {
                            cVarC = null;
                        }
                        int i10 = i8;
                        i8 = i9;
                        i9 = i10;
                    } else {
                        cVarC = cVarC2;
                        cVarC2 = null;
                    }
                    aVar = new a(cVarC2 == null ? 0 : i8 * cVarC2.d(), cVarC != null ? i9 * cVarC.d() : 0);
                }
            }
            A();
            if (f(')')) {
                return aVar;
            }
            this.f8389b = i7;
            return null;
        }

        private String E() {
            if (h()) {
                return null;
            }
            String strQ = q();
            return strQ != null ? strQ : H();
        }

        private List<String> G() {
            if (h()) {
                return null;
            }
            int i7 = this.f8389b;
            if (!f('(')) {
                return null;
            }
            A();
            ArrayList arrayList = null;
            do {
                String strH = H();
                if (strH == null) {
                    this.f8389b = i7;
                    return null;
                }
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(strH);
                A();
            } while (z());
            if (f(')')) {
                return arrayList;
            }
            this.f8389b = i7;
            return null;
        }

        private List<s> K() {
            List<t> list;
            List<g> list2;
            if (h()) {
                return null;
            }
            int i7 = this.f8389b;
            if (!f('(')) {
                return null;
            }
            A();
            List<s> listL = L();
            if (listL == null) {
                this.f8389b = i7;
                return null;
            }
            if (!f(')')) {
                this.f8389b = i7;
                return null;
            }
            Iterator<s> it = listL.iterator();
            while (it.hasNext() && (list = it.next().f8103a) != null) {
                Iterator<t> it2 = list.iterator();
                while (it2.hasNext() && (list2 = it2.next().f8108d) != null) {
                    Iterator<g> it3 = list2.iterator();
                    while (it3.hasNext()) {
                        if (it3.next() instanceof k) {
                            return null;
                        }
                    }
                }
            }
            return listL;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public List<s> L() {
            a aVar = null;
            if (h()) {
                return null;
            }
            ArrayList arrayList = new ArrayList(1);
            s sVar = new s(aVar);
            while (!h() && M(sVar)) {
                if (z()) {
                    arrayList.add(sVar);
                    sVar = new s(aVar);
                }
            }
            if (!sVar.f()) {
                arrayList.add(sVar);
            }
            return arrayList;
        }

        private void O(s sVar, t tVar) throws m2.a {
            g gVar;
            g gVar2;
            String strH = H();
            if (strH == null) {
                throw new m2.a("Invalid pseudo class");
            }
            j jVarFromString = j.fromString(strH);
            a aVar = null;
            switch (a.f8078b[jVarFromString.ordinal()]) {
                case 1:
                    g hVar = new h(0, 1, true, false, null);
                    sVar.b();
                    gVar2 = hVar;
                    tVar.b(gVar2);
                    return;
                case 2:
                    g hVar2 = new h(0, 1, false, false, null);
                    sVar.b();
                    gVar2 = hVar2;
                    tVar.b(gVar2);
                    return;
                case 3:
                    g mVar = new m(false, null);
                    sVar.b();
                    gVar2 = mVar;
                    tVar.b(gVar2);
                    return;
                case 4:
                    g hVar3 = new h(0, 1, true, true, tVar.f8106b);
                    sVar.b();
                    gVar2 = hVar3;
                    tVar.b(gVar2);
                    return;
                case 5:
                    g hVar4 = new h(0, 1, false, true, tVar.f8106b);
                    sVar.b();
                    gVar2 = hVar4;
                    tVar.b(gVar2);
                    return;
                case 6:
                    g mVar2 = new m(true, tVar.f8106b);
                    sVar.b();
                    gVar2 = mVar2;
                    tVar.b(gVar2);
                    return;
                case 7:
                    g nVar = new n(aVar);
                    sVar.b();
                    gVar2 = nVar;
                    tVar.b(gVar2);
                    return;
                case 8:
                    g iVar = new i(aVar);
                    sVar.b();
                    gVar2 = iVar;
                    tVar.b(gVar2);
                    return;
                case 9:
                case 10:
                case 11:
                case 12:
                    boolean z6 = jVarFromString == j.nth_child || jVarFromString == j.nth_of_type;
                    boolean z7 = jVarFromString == j.nth_of_type || jVarFromString == j.nth_last_of_type;
                    a aVarD = D();
                    if (aVarD == null) {
                        throw new m2.a("Invalid or missing parameter section for pseudo class: " + strH);
                    }
                    g hVar5 = new h(aVarD.f8083a, aVarD.f8084b, z6, z7, tVar.f8106b);
                    sVar.b();
                    gVar = hVar5;
                    gVar2 = gVar;
                    tVar.b(gVar2);
                    return;
                case 13:
                    List<s> listK = K();
                    if (listK == null) {
                        throw new m2.a("Invalid or missing parameter section for pseudo class: " + strH);
                    }
                    k kVar = new k(listK);
                    sVar.f8104b = kVar.b();
                    gVar = kVar;
                    gVar2 = gVar;
                    tVar.b(gVar2);
                    return;
                case 14:
                    g oVar = new o(aVar);
                    sVar.b();
                    gVar2 = oVar;
                    tVar.b(gVar2);
                    return;
                case 15:
                    G();
                    g lVar = new l(strH);
                    sVar.b();
                    gVar2 = lVar;
                    tVar.b(gVar2);
                    return;
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                    g lVar2 = new l(strH);
                    sVar.b();
                    gVar2 = lVar2;
                    tVar.b(gVar2);
                    return;
                default:
                    throw new m2.a("Unsupported pseudo class: " + strH);
            }
        }

        private int P() {
            int i7;
            if (h()) {
                return this.f8389b;
            }
            int i8 = this.f8389b;
            int iCharAt = this.f8388a.charAt(i8);
            if (iCharAt == 45) {
                iCharAt = a();
            }
            if ((iCharAt < 65 || iCharAt > 90) && ((iCharAt < 97 || iCharAt > 122) && iCharAt != 95)) {
                i7 = i8;
            } else {
                int iA = a();
                while (true) {
                    if ((iA < 65 || iA > 90) && ((iA < 97 || iA > 122) && !((iA >= 48 && iA <= 57) || iA == 45 || iA == 95))) {
                        break;
                    }
                    iA = a();
                }
                i7 = this.f8389b;
            }
            this.f8389b = i8;
            return i7;
        }

        String F() {
            int iC;
            if (h()) {
                return null;
            }
            char cCharAt = this.f8388a.charAt(this.f8389b);
            if (cCharAt != '\'' && cCharAt != '\"') {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            this.f8389b++;
            int iIntValue = l().intValue();
            while (iIntValue != -1 && iIntValue != cCharAt) {
                if (iIntValue == 92) {
                    iIntValue = l().intValue();
                    if (iIntValue != -1) {
                        if (iIntValue == 10 || iIntValue == 13 || iIntValue == 12) {
                            iIntValue = l().intValue();
                        } else {
                            int iC2 = C(iIntValue);
                            if (iC2 != -1) {
                                for (int i7 = 1; i7 <= 5 && (iC = C((iIntValue = l().intValue()))) != -1; i7++) {
                                    iC2 = (iC2 * 16) + iC;
                                }
                                sb.append((char) iC2);
                            }
                        }
                    }
                }
                sb.append((char) iIntValue);
                iIntValue = l().intValue();
            }
            return sb.toString();
        }

        String H() {
            int iP = P();
            int i7 = this.f8389b;
            if (iP == i7) {
                return null;
            }
            String strSubstring = this.f8388a.substring(i7, iP);
            this.f8389b = iP;
            return strSubstring;
        }

        String I() {
            char cCharAt;
            int iC;
            StringBuilder sb = new StringBuilder();
            while (!h() && (cCharAt = this.f8388a.charAt(this.f8389b)) != '\'' && cCharAt != '\"' && cCharAt != '(' && cCharAt != ')' && !k(cCharAt) && !Character.isISOControl((int) cCharAt)) {
                this.f8389b++;
                if (cCharAt == '\\') {
                    if (!h()) {
                        String str = this.f8388a;
                        int i7 = this.f8389b;
                        this.f8389b = i7 + 1;
                        cCharAt = str.charAt(i7);
                        if (cCharAt != '\n' && cCharAt != '\r' && cCharAt != '\f') {
                            int iC2 = C(cCharAt);
                            if (iC2 != -1) {
                                for (int i8 = 1; i8 <= 5 && !h() && (iC = C(this.f8388a.charAt(this.f8389b))) != -1; i8++) {
                                    this.f8389b++;
                                    iC2 = (iC2 * 16) + iC;
                                }
                                sb.append((char) iC2);
                            }
                        }
                    }
                }
                sb.append(cCharAt);
            }
            if (sb.length() == 0) {
                return null;
            }
            return sb.toString();
        }

        String J() {
            if (h()) {
                return null;
            }
            int i7 = this.f8389b;
            int iCharAt = this.f8388a.charAt(i7);
            int i8 = i7;
            while (iCharAt != -1 && iCharAt != 59 && iCharAt != 125 && iCharAt != 33 && !j(iCharAt)) {
                if (!k(iCharAt)) {
                    i8 = this.f8389b + 1;
                }
                iCharAt = a();
            }
            if (this.f8389b > i7) {
                return this.f8388a.substring(i7, i8);
            }
            this.f8389b = i7;
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        boolean M(m2.b.s r11) throws m2.a {
            /*
                Method dump skipped, instruction units count: 310
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: m2.b.d.M(m2.b$s):boolean");
        }

        String N() {
            if (h()) {
                return null;
            }
            int i7 = this.f8389b;
            if (!g("url(")) {
                return null;
            }
            A();
            String strF = F();
            if (strF == null) {
                strF = I();
            }
            if (strF == null) {
                this.f8389b = i7;
                return null;
            }
            A();
            if (h() || g(")")) {
                return strF;
            }
            this.f8389b = i7;
            return null;
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private enum e {
        DESCENDANT,
        CHILD,
        FOLLOWS
    }

    /* JADX INFO: compiled from: CSSParser.java */
    enum f {
        all,
        aural,
        braille,
        embossed,
        handheld,
        print,
        projection,
        screen,
        speech,
        tty,
        tv
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private interface g {
        boolean a(q qVar, g.l0 l0Var);
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private static class h implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f8087a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f8088b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f8089c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f8090d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private String f8091e;

        h(int i7, int i8, boolean z6, boolean z7, String str) {
            this.f8087a = i7;
            this.f8088b = i8;
            this.f8089c = z6;
            this.f8090d = z7;
            this.f8091e = str;
        }

        @Override // m2.b.g
        public boolean a(q qVar, g.l0 l0Var) {
            int i7;
            int i8;
            String strO = (this.f8090d && this.f8091e == null) ? l0Var.o() : this.f8091e;
            g.j0 j0Var = l0Var.f8246b;
            if (j0Var != null) {
                Iterator<g.n0> it = j0Var.j().iterator();
                i7 = 0;
                i8 = 0;
                while (it.hasNext()) {
                    g.l0 l0Var2 = (g.l0) it.next();
                    if (l0Var2 == l0Var) {
                        i7 = i8;
                    }
                    if (strO == null || l0Var2.o().equals(strO)) {
                        i8++;
                    }
                }
            } else {
                i7 = 0;
                i8 = 1;
            }
            int i9 = this.f8089c ? i7 + 1 : i8 - i7;
            int i10 = this.f8087a;
            if (i10 == 0) {
                return i9 == this.f8088b;
            }
            int i11 = this.f8088b;
            if ((i9 - i11) % i10 == 0) {
                return Integer.signum(i9 - i11) == 0 || Integer.signum(i9 - this.f8088b) == Integer.signum(this.f8087a);
            }
            return false;
        }

        public String toString() {
            String str = this.f8089c ? "" : "last-";
            return this.f8090d ? String.format("nth-%schild(%dn%+d of type <%s>)", str, Integer.valueOf(this.f8087a), Integer.valueOf(this.f8088b), this.f8091e) : String.format("nth-%schild(%dn%+d)", str, Integer.valueOf(this.f8087a), Integer.valueOf(this.f8088b));
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private static class i implements g {
        private i() {
        }

        /* synthetic */ i(a aVar) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // m2.b.g
        public boolean a(q qVar, g.l0 l0Var) {
            return !(l0Var instanceof g.j0) || ((g.j0) l0Var).j().size() == 0;
        }

        public String toString() {
            return "empty";
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private enum j {
        target,
        root,
        nth_child,
        nth_last_child,
        nth_of_type,
        nth_last_of_type,
        first_child,
        last_child,
        first_of_type,
        last_of_type,
        only_child,
        only_of_type,
        empty,
        not,
        lang,
        link,
        visited,
        hover,
        active,
        focus,
        enabled,
        disabled,
        checked,
        indeterminate,
        UNSUPPORTED;


        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private static final Map<String, j> f8092e = new HashMap();

        static {
            for (j jVar : values()) {
                if (jVar != UNSUPPORTED) {
                    f8092e.put(jVar.name().replace('_', '-'), jVar);
                }
            }
        }

        public static j fromString(String str) {
            j jVar = f8092e.get(str);
            return jVar != null ? jVar : UNSUPPORTED;
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private static class k implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private List<s> f8094a;

        k(List<s> list) {
            this.f8094a = list;
        }

        @Override // m2.b.g
        public boolean a(q qVar, g.l0 l0Var) {
            Iterator<s> it = this.f8094a.iterator();
            while (it.hasNext()) {
                if (b.l(qVar, it.next(), l0Var)) {
                    return false;
                }
            }
            return true;
        }

        int b() {
            Iterator<s> it = this.f8094a.iterator();
            int i7 = Integer.MIN_VALUE;
            while (it.hasNext()) {
                int i8 = it.next().f8104b;
                if (i8 > i7) {
                    i7 = i8;
                }
            }
            return i7;
        }

        public String toString() {
            return "not(" + this.f8094a + ")";
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private static class l implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f8095a;

        l(String str) {
            this.f8095a = str;
        }

        @Override // m2.b.g
        public boolean a(q qVar, g.l0 l0Var) {
            return false;
        }

        public String toString() {
            return this.f8095a;
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private static class m implements g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f8096a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f8097b;

        public m(boolean z6, String str) {
            this.f8096a = z6;
            this.f8097b = str;
        }

        @Override // m2.b.g
        public boolean a(q qVar, g.l0 l0Var) {
            int i7;
            String strO = (this.f8096a && this.f8097b == null) ? l0Var.o() : this.f8097b;
            g.j0 j0Var = l0Var.f8246b;
            if (j0Var != null) {
                Iterator<g.n0> it = j0Var.j().iterator();
                i7 = 0;
                while (it.hasNext()) {
                    g.l0 l0Var2 = (g.l0) it.next();
                    if (strO == null || l0Var2.o().equals(strO)) {
                        i7++;
                    }
                }
            } else {
                i7 = 1;
            }
            return i7 == 1;
        }

        public String toString() {
            return this.f8096a ? String.format("only-of-type <%s>", this.f8097b) : String.format("only-child", new Object[0]);
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private static class n implements g {
        private n() {
        }

        /* synthetic */ n(a aVar) {
            this();
        }

        @Override // m2.b.g
        public boolean a(q qVar, g.l0 l0Var) {
            return l0Var.f8246b == null;
        }

        public String toString() {
            return "root";
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private static class o implements g {
        private o() {
        }

        /* synthetic */ o(a aVar) {
            this();
        }

        @Override // m2.b.g
        public boolean a(q qVar, g.l0 l0Var) {
            return qVar != null && l0Var == qVar.f8101a;
        }

        public String toString() {
            return "target";
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    static class p {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        s f8098a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        g.e0 f8099b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        u f8100c;

        p(s sVar, g.e0 e0Var, u uVar) {
            this.f8098a = sVar;
            this.f8099b = e0Var;
            this.f8100c = uVar;
        }

        public String toString() {
            return String.valueOf(this.f8098a) + " {...} (src=" + this.f8100c + ")";
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    static class q {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        g.l0 f8101a;

        q() {
        }

        public String toString() {
            g.l0 l0Var = this.f8101a;
            return l0Var != null ? String.format("<%s id=\"%s\">", l0Var.o(), this.f8101a.f8235c) : "";
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    static class r {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private List<p> f8102a = null;

        r() {
        }

        void a(p pVar) {
            if (this.f8102a == null) {
                this.f8102a = new ArrayList();
            }
            for (int i7 = 0; i7 < this.f8102a.size(); i7++) {
                if (this.f8102a.get(i7).f8098a.f8104b > pVar.f8098a.f8104b) {
                    this.f8102a.add(i7, pVar);
                    return;
                }
            }
            this.f8102a.add(pVar);
        }

        void b(r rVar) {
            if (rVar.f8102a == null) {
                return;
            }
            if (this.f8102a == null) {
                this.f8102a = new ArrayList(rVar.f8102a.size());
            }
            Iterator<p> it = rVar.f8102a.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
        }

        List<p> c() {
            return this.f8102a;
        }

        boolean d() {
            List<p> list = this.f8102a;
            return list == null || list.isEmpty();
        }

        void e(u uVar) {
            List<p> list = this.f8102a;
            if (list == null) {
                return;
            }
            Iterator<p> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().f8100c == uVar) {
                    it.remove();
                }
            }
        }

        int f() {
            List<p> list = this.f8102a;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        public String toString() {
            if (this.f8102a == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            Iterator<p> it = this.f8102a.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
                sb.append('\n');
            }
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    private static class t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        e f8105a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f8106b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        List<C0123b> f8107c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        List<g> f8108d = null;

        t(e eVar, String str) {
            this.f8105a = null;
            this.f8106b = null;
            this.f8105a = eVar == null ? e.DESCENDANT : eVar;
            this.f8106b = str;
        }

        void a(String str, c cVar, String str2) {
            if (this.f8107c == null) {
                this.f8107c = new ArrayList();
            }
            this.f8107c.add(new C0123b(str, cVar, str2));
        }

        void b(g gVar) {
            if (this.f8108d == null) {
                this.f8108d = new ArrayList();
            }
            this.f8108d.add(gVar);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            e eVar = this.f8105a;
            if (eVar == e.CHILD) {
                sb.append("> ");
            } else if (eVar == e.FOLLOWS) {
                sb.append("+ ");
            }
            String str = this.f8106b;
            if (str == null) {
                str = "*";
            }
            sb.append(str);
            List<C0123b> list = this.f8107c;
            if (list != null) {
                for (C0123b c0123b : list) {
                    sb.append('[');
                    sb.append(c0123b.f8079a);
                    int i7 = a.f8077a[c0123b.f8080b.ordinal()];
                    if (i7 == 1) {
                        sb.append('=');
                        sb.append(c0123b.f8081c);
                    } else if (i7 == 2) {
                        sb.append("~=");
                        sb.append(c0123b.f8081c);
                    } else if (i7 == 3) {
                        sb.append("|=");
                        sb.append(c0123b.f8081c);
                    }
                    sb.append(']');
                }
            }
            List<g> list2 = this.f8108d;
            if (list2 != null) {
                for (g gVar : list2) {
                    sb.append(':');
                    sb.append(gVar);
                }
            }
            return sb.toString();
        }
    }

    /* JADX INFO: compiled from: CSSParser.java */
    enum u {
        Document,
        RenderOptions
    }

    b(f fVar, u uVar) {
        this.f8074a = fVar;
        this.f8075b = uVar;
    }

    private static int a(List<g.j0> list, int i7, g.l0 l0Var) {
        int i8 = 0;
        if (i7 < 0) {
            return 0;
        }
        g.j0 j0Var = list.get(i7);
        g.j0 j0Var2 = l0Var.f8246b;
        if (j0Var != j0Var2) {
            return -1;
        }
        Iterator<g.n0> it = j0Var2.j().iterator();
        while (it.hasNext()) {
            if (it.next() == l0Var) {
                return i8;
            }
            i8++;
        }
        return -1;
    }

    static boolean b(String str, f fVar) {
        d dVar = new d(str);
        dVar.A();
        return c(h(dVar), fVar);
    }

    private static boolean c(List<f> list, f fVar) {
        for (f fVar2 : list) {
            if (fVar2 == f.all || fVar2 == fVar) {
                return true;
            }
        }
        return false;
    }

    private void e(r rVar, d dVar) throws m2.a {
        String strH = dVar.H();
        dVar.A();
        if (strH == null) {
            throw new m2.a("Invalid '@' rule");
        }
        if (!this.f8076c && strH.equals("media")) {
            List<f> listH = h(dVar);
            if (!dVar.f('{')) {
                throw new m2.a("Invalid @media rule: missing rule set");
            }
            dVar.A();
            if (c(listH, this.f8074a)) {
                this.f8076c = true;
                rVar.b(j(dVar));
                this.f8076c = false;
            } else {
                j(dVar);
            }
            if (!dVar.h() && !dVar.f('}')) {
                throw new m2.a("Invalid @media rule: expected '}' at end of rule set");
            }
        } else if (this.f8076c || !strH.equals("import")) {
            p("Ignoring @%s rule", strH);
            o(dVar);
        } else {
            String strN = dVar.N();
            if (strN == null) {
                strN = dVar.F();
            }
            if (strN == null) {
                throw new m2.a("Invalid @import rule: expected string or url()");
            }
            dVar.A();
            h(dVar);
            if (!dVar.h() && !dVar.f(';')) {
                throw new m2.a("Invalid @media rule: expected '}' at end of rule set");
            }
            m2.g.k();
        }
        dVar.A();
    }

    public static List<String> f(String str) {
        d dVar = new d(str);
        ArrayList arrayList = null;
        while (!dVar.h()) {
            String strR = dVar.r();
            if (strR != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(strR);
                dVar.A();
            }
        }
        return arrayList;
    }

    private g.e0 g(d dVar) throws m2.a {
        g.e0 e0Var = new g.e0();
        do {
            String strH = dVar.H();
            dVar.A();
            if (!dVar.f(':')) {
                throw new m2.a("Expected ':'");
            }
            dVar.A();
            String strJ = dVar.J();
            if (strJ == null) {
                throw new m2.a("Expected property value");
            }
            dVar.A();
            if (dVar.f('!')) {
                dVar.A();
                if (!dVar.g("important")) {
                    throw new m2.a("Malformed rule set: found unexpected '!'");
                }
                dVar.A();
            }
            dVar.f(';');
            m2.k.S0(e0Var, strH, strJ);
            dVar.A();
            if (dVar.h()) {
                break;
            }
        } while (!dVar.f('}'));
        return e0Var;
    }

    private static List<f> h(d dVar) {
        String strW;
        ArrayList arrayList = new ArrayList();
        while (!dVar.h() && (strW = dVar.w()) != null) {
            try {
                arrayList.add(f.valueOf(strW));
            } catch (IllegalArgumentException unused) {
            }
            if (!dVar.z()) {
                break;
            }
        }
        return arrayList;
    }

    private boolean i(r rVar, d dVar) throws m2.a {
        List listL = dVar.L();
        if (listL == null || listL.isEmpty()) {
            return false;
        }
        if (!dVar.f('{')) {
            throw new m2.a("Malformed rule block: expected '{'");
        }
        dVar.A();
        g.e0 e0VarG = g(dVar);
        dVar.A();
        Iterator it = listL.iterator();
        while (it.hasNext()) {
            rVar.a(new p((s) it.next(), e0VarG, this.f8075b));
        }
        return true;
    }

    private r j(d dVar) {
        r rVar = new r();
        while (!dVar.h()) {
            try {
                if (!dVar.g("<!--") && !dVar.g("-->")) {
                    if (!dVar.f('@')) {
                        if (!i(rVar, dVar)) {
                            break;
                        }
                    } else {
                        e(rVar, dVar);
                    }
                }
            } catch (m2.a e7) {
                Log.e("AndroidSVG CSSParser", "CSS parser terminated early due to error: " + e7.getMessage());
            }
        }
        return rVar;
    }

    private static boolean k(q qVar, s sVar, int i7, List<g.j0> list, int i8, g.l0 l0Var) {
        t tVarE = sVar.e(i7);
        if (!n(qVar, tVarE, list, i8, l0Var)) {
            return false;
        }
        e eVar = tVarE.f8105a;
        if (eVar == e.DESCENDANT) {
            if (i7 == 0) {
                return true;
            }
            while (i8 >= 0) {
                if (m(qVar, sVar, i7 - 1, list, i8)) {
                    return true;
                }
                i8--;
            }
            return false;
        }
        if (eVar == e.CHILD) {
            return m(qVar, sVar, i7 - 1, list, i8);
        }
        int iA = a(list, i8, l0Var);
        if (iA <= 0) {
            return false;
        }
        return k(qVar, sVar, i7 - 1, list, i8, (g.l0) l0Var.f8246b.j().get(iA - 1));
    }

    static boolean l(q qVar, s sVar, g.l0 l0Var) {
        ArrayList arrayList = new ArrayList();
        for (Object obj = l0Var.f8246b; obj != null; obj = ((g.n0) obj).f8246b) {
            arrayList.add(0, obj);
        }
        int size = arrayList.size() - 1;
        return sVar.g() == 1 ? n(qVar, sVar.e(0), arrayList, size, l0Var) : k(qVar, sVar, sVar.g() - 1, arrayList, size, l0Var);
    }

    private static boolean m(q qVar, s sVar, int i7, List<g.j0> list, int i8) {
        t tVarE = sVar.e(i7);
        g.l0 l0Var = (g.l0) list.get(i8);
        if (!n(qVar, tVarE, list, i8, l0Var)) {
            return false;
        }
        e eVar = tVarE.f8105a;
        if (eVar == e.DESCENDANT) {
            if (i7 == 0) {
                return true;
            }
            while (i8 > 0) {
                i8--;
                if (m(qVar, sVar, i7 - 1, list, i8)) {
                    return true;
                }
            }
            return false;
        }
        if (eVar == e.CHILD) {
            return m(qVar, sVar, i7 - 1, list, i8 - 1);
        }
        int iA = a(list, i8, l0Var);
        if (iA <= 0) {
            return false;
        }
        return k(qVar, sVar, i7 - 1, list, i8, (g.l0) l0Var.f8246b.j().get(iA - 1));
    }

    private static boolean n(q qVar, t tVar, List<g.j0> list, int i7, g.l0 l0Var) {
        List<String> list2;
        String str = tVar.f8106b;
        if (str != null && !str.equals(l0Var.o().toLowerCase(Locale.US))) {
            return false;
        }
        List<C0123b> list3 = tVar.f8107c;
        if (list3 != null) {
            for (C0123b c0123b : list3) {
                String str2 = c0123b.f8079a;
                str2.hashCode();
                if (str2.equals("id")) {
                    if (!c0123b.f8081c.equals(l0Var.f8235c)) {
                        return false;
                    }
                } else if (!str2.equals("class") || (list2 = l0Var.f8239g) == null || !list2.contains(c0123b.f8081c)) {
                    return false;
                }
            }
        }
        List<g> list4 = tVar.f8108d;
        if (list4 == null) {
            return true;
        }
        Iterator<g> it = list4.iterator();
        while (it.hasNext()) {
            if (!it.next().a(qVar, l0Var)) {
                return false;
            }
        }
        return true;
    }

    private void o(d dVar) {
        int i7 = 0;
        while (!dVar.h()) {
            int iIntValue = dVar.l().intValue();
            if (iIntValue == 59 && i7 == 0) {
                return;
            }
            if (iIntValue == 123) {
                i7++;
            } else if (iIntValue == 125 && i7 > 0 && i7 - 1 == 0) {
                return;
            }
        }
    }

    private static void p(String str, Object... objArr) {
        Log.w("AndroidSVG CSSParser", String.format(str, objArr));
    }

    r d(String str) {
        d dVar = new d(str);
        dVar.A();
        return j(dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CSSParser.java */
    static class s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        List<t> f8103a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f8104b;

        private s() {
            this.f8103a = null;
            this.f8104b = 0;
        }

        void a(t tVar) {
            if (this.f8103a == null) {
                this.f8103a = new ArrayList();
            }
            this.f8103a.add(tVar);
        }

        void b() {
            this.f8104b += 1000;
        }

        void c() {
            this.f8104b++;
        }

        void d() {
            this.f8104b += 1000000;
        }

        t e(int i7) {
            return this.f8103a.get(i7);
        }

        boolean f() {
            List<t> list = this.f8103a;
            return list == null || list.isEmpty();
        }

        int g() {
            List<t> list = this.f8103a;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Iterator<t> it = this.f8103a.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(' ');
            }
            sb.append('[');
            sb.append(this.f8104b);
            sb.append(']');
            return sb.toString();
        }

        /* synthetic */ s(a aVar) {
            this();
        }
    }
}
