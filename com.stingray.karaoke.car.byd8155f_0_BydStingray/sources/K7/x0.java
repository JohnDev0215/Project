package K7;

import F8.AbstractC0287w;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0666t;
import T7.AbstractC0794m;
import java.util.List;
import n7.AbstractC1998m;
import o8.C2061f;
import q8.C2203g;

/* JADX INFO: loaded from: classes.dex */
public abstract class x0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2203g f3852a = C2203g.c;

    public static void a(InterfaceC0650c interfaceC0650c, StringBuilder sb) {
        T7.u uVarG = A0.g(interfaceC0650c);
        T7.u uVarK = interfaceC0650c.K();
        if (uVarG != null) {
            sb.append(d(uVarG.b()));
            sb.append(".");
        }
        boolean z6 = (uVarG == null || uVarK == null) ? false : true;
        if (z6) {
            sb.append("(");
        }
        if (uVarK != null) {
            sb.append(d(uVarK.b()));
            sb.append(".");
        }
        if (z6) {
            sb.append(")");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String b(InterfaceC0666t interfaceC0666t) {
        A7.m.f(interfaceC0666t, "descriptor");
        StringBuilder sb = new StringBuilder();
        sb.append("fun ");
        a(interfaceC0666t, sb);
        C2061f name = ((AbstractC0794m) interfaceC0666t).getName();
        A7.m.e(name, "descriptor.name");
        sb.append(f3852a.O(name, true));
        List listD0 = interfaceC0666t.D0();
        A7.m.e(listD0, "descriptor.valueParameters");
        AbstractC1998m.d0(listD0, sb, ", ", (124 & 4) != 0 ? "" : "(", (124 & 8) != 0 ? "" : ")", -1, "...", (124 & 64) != 0 ? null : C0377b.f3771w);
        sb.append(": ");
        AbstractC0287w abstractC0287wP = interfaceC0666t.p();
        A7.m.c(abstractC0287wP);
        sb.append(d(abstractC0287wP));
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static String c(Q7.L l) {
        A7.m.f(l, "descriptor");
        StringBuilder sb = new StringBuilder();
        sb.append(l.I() ? "var " : "val ");
        a(l, sb);
        C2061f name = l.getName();
        A7.m.e(name, "descriptor.name");
        sb.append(f3852a.O(name, true));
        sb.append(": ");
        AbstractC0287w abstractC0287wB = l.b();
        A7.m.e(abstractC0287wB, "descriptor.type");
        sb.append(d(abstractC0287wB));
        String string = sb.toString();
        A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }

    public static String d(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "type");
        return f3852a.Y(abstractC0287w);
    }
}
