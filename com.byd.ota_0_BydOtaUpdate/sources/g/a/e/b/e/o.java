package g.a.e.b.e;

import g.a.e.b.e.c;
import g.a.e.b.e.e;
import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.XMSSNode;

/* JADX INFO: loaded from: classes.dex */
public class o {
    public static XMSSNode a(g gVar, j jVar, e eVar) {
        double d2;
        Objects.requireNonNull(jVar, "publicKey == null");
        Objects.requireNonNull(eVar, "address == null");
        int iC = gVar.d().c();
        byte[][] bArrA = jVar.a();
        XMSSNode[] xMSSNodeArr = new XMSSNode[bArrA.length];
        for (int i2 = 0; i2 < bArrA.length; i2++) {
            xMSSNodeArr[i2] = new XMSSNode(0, bArrA[i2]);
        }
        e.b bVarF = new e.b().g(eVar.b()).h(eVar.c()).n(eVar.e()).o(0).p(eVar.g()).f(eVar.a());
        while (true) {
            e eVar2 = (e) bVarF.l();
            if (iC <= 1) {
                return xMSSNodeArr[0];
            }
            int i3 = 0;
            while (true) {
                d2 = iC / 2;
                if (i3 >= ((int) Math.floor(d2))) {
                    break;
                }
                eVar2 = (e) new e.b().g(eVar2.b()).h(eVar2.c()).n(eVar2.e()).o(eVar2.f()).p(i3).f(eVar2.a()).l();
                int i4 = i3 * 2;
                xMSSNodeArr[i3] = b(gVar, xMSSNodeArr[i4], xMSSNodeArr[i4 + 1], eVar2);
                i3++;
            }
            if (iC % 2 == 1) {
                xMSSNodeArr[(int) Math.floor(d2)] = xMSSNodeArr[iC - 1];
            }
            iC = (int) Math.ceil(((double) iC) / 2.0d);
            bVarF = new e.b().g(eVar2.b()).h(eVar2.c()).n(eVar2.e()).o(eVar2.f() + 1).p(eVar2.g()).f(eVar2.a());
        }
    }

    public static XMSSNode b(g gVar, XMSSNode xMSSNode, XMSSNode xMSSNode2, k kVar) {
        Objects.requireNonNull(xMSSNode, "left == null");
        Objects.requireNonNull(xMSSNode2, "right == null");
        if (xMSSNode.getHeight() != xMSSNode2.getHeight()) {
            throw new IllegalStateException("height of both nodes must be equal");
        }
        Objects.requireNonNull(kVar, "address == null");
        byte[] bArrF = gVar.f();
        if (kVar instanceof e) {
            e eVar = (e) kVar;
            kVar = (e) new e.b().g(eVar.b()).h(eVar.c()).n(eVar.e()).o(eVar.f()).p(eVar.g()).f(0).l();
        } else if (kVar instanceof c) {
            c cVar = (c) kVar;
            kVar = (c) new c.b().g(cVar.b()).h(cVar.c()).m(cVar.e()).n(cVar.f()).f(0).k();
        }
        byte[] bArrC = gVar.c().c(bArrF, kVar.d());
        if (kVar instanceof e) {
            e eVar2 = (e) kVar;
            kVar = (e) new e.b().g(eVar2.b()).h(eVar2.c()).n(eVar2.e()).o(eVar2.f()).p(eVar2.g()).f(1).l();
        } else if (kVar instanceof c) {
            c cVar2 = (c) kVar;
            kVar = (c) new c.b().g(cVar2.b()).h(cVar2.c()).m(cVar2.e()).n(cVar2.f()).f(1).k();
        }
        byte[] bArrC2 = gVar.c().c(bArrF, kVar.d());
        if (kVar instanceof e) {
            e eVar3 = (e) kVar;
            kVar = (e) new e.b().g(eVar3.b()).h(eVar3.c()).n(eVar3.e()).o(eVar3.f()).p(eVar3.g()).f(2).l();
        } else if (kVar instanceof c) {
            c cVar3 = (c) kVar;
            kVar = (c) new c.b().g(cVar3.b()).h(cVar3.c()).m(cVar3.e()).n(cVar3.f()).f(2).k();
        }
        byte[] bArrC3 = gVar.c().c(bArrF, kVar.d());
        int iB = gVar.d().b();
        byte[] bArr = new byte[iB * 2];
        for (int i2 = 0; i2 < iB; i2++) {
            bArr[i2] = (byte) (xMSSNode.getValue()[i2] ^ bArrC2[i2]);
        }
        for (int i3 = 0; i3 < iB; i3++) {
            bArr[i3 + iB] = (byte) (xMSSNode2.getValue()[i3] ^ bArrC3[i3]);
        }
        return new XMSSNode(xMSSNode.getHeight(), gVar.c().b(bArrC, bArr));
    }
}
