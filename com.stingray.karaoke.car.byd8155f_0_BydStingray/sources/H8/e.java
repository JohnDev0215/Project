package H8;

import A7.k;
import A7.m;
import Q7.AbstractC0662o;
import Q7.InterfaceC0654g;
import Q7.N;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import n7.AbstractC1985B;
import n7.u;
import n7.w;
import o8.C2061f;
import y8.n;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public class e implements n {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2857b;

    public e(int i7, String... strArr) {
        String str;
        k.p(i7, "kind");
        m.f(strArr, "formatParams");
        Object[] objArrCopyOf = Arrays.copyOf(strArr, strArr.length);
        Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, objArrCopyOf.length);
        switch (i7) {
            case 1:
                str = "No member resolution should be done on captured type, it used only during constraint system resolution";
                break;
            case 2:
                str = "Scope for integer literal type (%s)";
                break;
            case 3:
                str = "Error scope for erased receiver type";
                break;
            case 4:
                str = "Scope for abbreviation %s";
                break;
            case 5:
                str = "Scope for stub type %s";
                break;
            case 6:
                str = "A scope for common supertype which is not a normal classifier";
                break;
            case 7:
                str = "Scope for error type %s";
                break;
            case 8:
                str = "Scope for unsupported type %s";
                break;
            case 9:
                str = "Error scope for class %s with arguments: %s";
                break;
            case 10:
                str = "Error resolution candidate for call %s";
                break;
            default:
                throw null;
        }
        this.f2857b = String.format(str, objArrCopyOf2);
    }

    @Override // y8.p
    public InterfaceC0654g c(C2061f c2061f, Y7.b bVar) {
        m.f(c2061f, "name");
        m.f(bVar, "location");
        return new a(C2061f.g(String.format("<Error class: %s>", Arrays.copyOf(new Object[]{c2061f}, 1))));
    }

    @Override // y8.p
    public Collection d(y8.f fVar, InterfaceC2782k interfaceC2782k) {
        m.f(fVar, "kindFilter");
        m.f(interfaceC2782k, "nameFilter");
        return u.l;
    }

    @Override // y8.n
    public Set e() {
        return w.l;
    }

    @Override // y8.n
    public Set f() {
        return w.l;
    }

    @Override // y8.n
    public Set g() {
        return w.l;
    }

    @Override // y8.n
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public Set a(C2061f c2061f, Y7.b bVar) {
        m.f(c2061f, "name");
        a aVar = i.c;
        m.f(aVar, "containingDeclaration");
        b bVar2 = new b(aVar, null, R7.g.f7535a, C2061f.g("<Error function>"), 1, N.c);
        u uVar = u.l;
        bVar2.h1(null, null, uVar, uVar, uVar, i.c(h.f2883p, new String[0]), 3, AbstractC0662o.f7191e);
        return AbstractC1985B.t(bVar2);
    }

    @Override // y8.n
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public Set b(C2061f c2061f, Y7.b bVar) {
        m.f(c2061f, "name");
        return i.f2898f;
    }

    public String toString() {
        return android.hardware.bydauto.ac.tsimpl.a.k(new StringBuilder("ErrorScope{"), this.f2857b, '}');
    }
}
