package q8;

import Q7.D;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0657j;
import Q7.Q;
import T7.B;
import h8.AbstractC1386c;
import java.util.ArrayList;
import n7.C1984A;
import o8.C2060e;
import o8.C2061f;

/* JADX INFO: renamed from: q8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2198b implements InterfaceC2199c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2198b f21261b = new C2198b(0);
    public static final C2198b c = new C2198b(1);
    public static final C2198b d = new C2198b(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f21262a;

    public /* synthetic */ C2198b(int i7) {
        this.f21262a = i7;
    }

    public static String b(InterfaceC0654g interfaceC0654g) {
        String strP;
        C2061f name = interfaceC0654g.getName();
        A7.m.e(name, "descriptor.name");
        String strO = AbstractC1386c.o(name);
        if (interfaceC0654g instanceof Q) {
            return strO;
        }
        InterfaceC0657j interfaceC0657jO = interfaceC0654g.o();
        A7.m.e(interfaceC0657jO, "descriptor.containingDeclaration");
        if (interfaceC0657jO instanceof InterfaceC0652e) {
            strP = b((InterfaceC0654g) interfaceC0657jO);
        } else if (interfaceC0657jO instanceof D) {
            C2060e c2060eI = ((B) ((D) interfaceC0657jO)).f8767q.i();
            A7.m.e(c2060eI, "descriptor.fqName.toUnsafe()");
            strP = AbstractC1386c.p(c2060eI.e());
        } else {
            strP = null;
        }
        if (strP == null || strP.equals("")) {
            return strO;
        }
        return strP + '.' + strO;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [Q7.g, Q7.j] */
    /* JADX WARN: Type inference failed for: r1v5, types: [Q7.j] */
    /* JADX WARN: Type inference failed for: r1v6, types: [Q7.j] */
    @Override // q8.InterfaceC2199c
    public final String a(InterfaceC0654g interfaceC0654g, C2203g c2203g) {
        switch (this.f21262a) {
            case 0:
                A7.m.f(c2203g, "renderer");
                if (interfaceC0654g instanceof Q) {
                    C2061f name = ((Q) interfaceC0654g).getName();
                    A7.m.e(name, "classifier.name");
                    return c2203g.O(name, false);
                }
                C2060e c2060eG = r8.d.g(interfaceC0654g);
                A7.m.e(c2060eG, "getFqName(classifier)");
                return c2203g.o(AbstractC1386c.p(c2060eG.e()));
            case 1:
                A7.m.f(c2203g, "renderer");
                if (interfaceC0654g instanceof Q) {
                    C2061f name2 = ((Q) interfaceC0654g).getName();
                    A7.m.e(name2, "classifier.name");
                    return c2203g.O(name2, false);
                }
                ArrayList arrayList = new ArrayList();
                do {
                    arrayList.add(interfaceC0654g.getName());
                    interfaceC0654g = interfaceC0654g.o();
                } while (interfaceC0654g instanceof InterfaceC0652e);
                return AbstractC1386c.p(new C1984A(arrayList));
            default:
                A7.m.f(c2203g, "renderer");
                return b(interfaceC0654g);
        }
    }
}
