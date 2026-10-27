package org.bouncycastle.pqc.crypto.xmss;

import g.a.e.b.e.c;
import g.a.e.b.e.e;
import g.a.e.b.e.f;
import g.a.e.b.e.g;
import g.a.e.b.e.j;
import g.a.e.b.e.o;
import g.a.e.b.e.q;
import g.a.e.b.e.t;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class BDS implements Serializable {
    private static final long serialVersionUID = 1;
    private List<XMSSNode> authenticationPath;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public transient g f5957c;
    private int index;
    private int k;
    private Map<Integer, XMSSNode> keep;
    private Map<Integer, LinkedList<XMSSNode>> retain;
    private XMSSNode root;
    private Stack<XMSSNode> stack;
    private final List<BDSTreeHash> treeHashInstances;
    private final int treeHeight;
    private boolean used;

    public BDS(g gVar, int i2, int i3) {
        this.f5957c = gVar;
        this.treeHeight = i2;
        this.k = i3;
        if (i3 <= i2 && i3 >= 2) {
            int i4 = i2 - i3;
            if (i4 % 2 == 0) {
                this.authenticationPath = new ArrayList();
                this.retain = new TreeMap();
                this.stack = new Stack<>();
                this.treeHashInstances = new ArrayList();
                for (int i5 = 0; i5 < i4; i5++) {
                    this.treeHashInstances.add(new BDSTreeHash(i5));
                }
                this.keep = new TreeMap();
                this.index = 0;
                this.used = false;
                return;
            }
        }
        throw new IllegalArgumentException("illegal value for BDS parameter k");
    }

    public BDS(q qVar, int i2) {
        this(qVar.f(), qVar.d(), qVar.e());
        this.index = i2;
        this.used = true;
    }

    public BDS(q qVar, byte[] bArr, byte[] bArr2, f fVar) {
        this(qVar.f(), qVar.d(), qVar.e());
        b(bArr, bArr2, fVar);
    }

    public BDS(q qVar, byte[] bArr, byte[] bArr2, f fVar, int i2) {
        this(qVar.f(), qVar.d(), qVar.e());
        b(bArr, bArr2, fVar);
        while (this.index < i2) {
            c(bArr, bArr2, fVar);
            this.used = false;
        }
    }

    public BDS(BDS bds, byte[] bArr, byte[] bArr2, f fVar) {
        this.f5957c = bds.f5957c;
        this.treeHeight = bds.treeHeight;
        this.k = bds.k;
        this.root = bds.root;
        this.authenticationPath = new ArrayList(bds.authenticationPath);
        this.retain = bds.retain;
        this.stack = (Stack) bds.stack.clone();
        this.treeHashInstances = bds.treeHashInstances;
        this.keep = new TreeMap(bds.keep);
        this.index = bds.index;
        c(bArr, bArr2, fVar);
        bds.used = true;
    }

    public final BDSTreeHash a() {
        BDSTreeHash bDSTreeHash = null;
        for (BDSTreeHash bDSTreeHash2 : this.treeHashInstances) {
            if (!bDSTreeHash2.isFinished() && bDSTreeHash2.isInitialized() && (bDSTreeHash == null || bDSTreeHash2.getHeight() < bDSTreeHash.getHeight() || (bDSTreeHash2.getHeight() == bDSTreeHash.getHeight() && bDSTreeHash2.getIndexLeaf() < bDSTreeHash.getIndexLeaf()))) {
                bDSTreeHash = bDSTreeHash2;
            }
        }
        return bDSTreeHash;
    }

    public final void b(byte[] bArr, byte[] bArr2, f fVar) {
        Objects.requireNonNull(fVar, "otsHashAddress == null");
        e eVar = (e) new e.b().g(fVar.b()).h(fVar.c()).l();
        c cVar = (c) new c.b().g(fVar.b()).h(fVar.c()).k();
        for (int i2 = 0; i2 < (1 << this.treeHeight); i2++) {
            fVar = (f) new f.b().g(fVar.b()).h(fVar.c()).p(i2).n(fVar.e()).o(fVar.f()).f(fVar.a()).l();
            g gVar = this.f5957c;
            gVar.h(gVar.g(bArr2, fVar), bArr);
            j jVarE = this.f5957c.e(fVar);
            eVar = (e) new e.b().g(eVar.b()).h(eVar.c()).n(i2).o(eVar.f()).p(eVar.g()).f(eVar.a()).l();
            XMSSNode xMSSNodeA = o.a(this.f5957c, jVarE, eVar);
            cVar = (c) new c.b().g(cVar.b()).h(cVar.c()).n(i2).f(cVar.a()).k();
            while (!this.stack.isEmpty() && this.stack.peek().getHeight() == xMSSNodeA.getHeight()) {
                int iFloor = (int) Math.floor(i2 / (1 << xMSSNodeA.getHeight()));
                if (iFloor == 1) {
                    this.authenticationPath.add(xMSSNodeA.clone());
                }
                if (iFloor == 3 && xMSSNodeA.getHeight() < this.treeHeight - this.k) {
                    this.treeHashInstances.get(xMSSNodeA.getHeight()).setNode(xMSSNodeA.clone());
                }
                if (iFloor >= 3 && (iFloor & 1) == 1 && xMSSNodeA.getHeight() >= this.treeHeight - this.k && xMSSNodeA.getHeight() <= this.treeHeight - 2) {
                    if (this.retain.get(Integer.valueOf(xMSSNodeA.getHeight())) == null) {
                        LinkedList<XMSSNode> linkedList = new LinkedList<>();
                        linkedList.add(xMSSNodeA.clone());
                        this.retain.put(Integer.valueOf(xMSSNodeA.getHeight()), linkedList);
                    } else {
                        this.retain.get(Integer.valueOf(xMSSNodeA.getHeight())).add(xMSSNodeA.clone());
                    }
                }
                c cVar2 = (c) new c.b().g(cVar.b()).h(cVar.c()).m(cVar.e()).n((cVar.f() - 1) / 2).f(cVar.a()).k();
                XMSSNode xMSSNodeB = o.b(this.f5957c, this.stack.pop(), xMSSNodeA, cVar2);
                XMSSNode xMSSNode = new XMSSNode(xMSSNodeB.getHeight() + 1, xMSSNodeB.getValue());
                cVar = (c) new c.b().g(cVar2.b()).h(cVar2.c()).m(cVar2.e() + 1).n(cVar2.f()).f(cVar2.a()).k();
                xMSSNodeA = xMSSNode;
            }
            this.stack.push(xMSSNodeA);
        }
        this.root = this.stack.pop();
    }

    public final void c(byte[] bArr, byte[] bArr2, f fVar) {
        List<XMSSNode> list;
        XMSSNode xMSSNodeRemoveFirst;
        Objects.requireNonNull(fVar, "otsHashAddress == null");
        if (this.used) {
            throw new IllegalStateException("index already used");
        }
        if (this.index > (1 << this.treeHeight) - 2) {
            throw new IllegalStateException("index out of bounds");
        }
        e eVar = (e) new e.b().g(fVar.b()).h(fVar.c()).l();
        c cVar = (c) new c.b().g(fVar.b()).h(fVar.c()).k();
        int iB = t.b(this.index, this.treeHeight);
        if (((this.index >> (iB + 1)) & 1) == 0 && iB < this.treeHeight - 1) {
            this.keep.put(Integer.valueOf(iB), this.authenticationPath.get(iB).clone());
        }
        if (iB == 0) {
            fVar = (f) new f.b().g(fVar.b()).h(fVar.c()).p(this.index).n(fVar.e()).o(fVar.f()).f(fVar.a()).l();
            g gVar = this.f5957c;
            gVar.h(gVar.g(bArr2, fVar), bArr);
            this.authenticationPath.set(0, o.a(this.f5957c, this.f5957c.e(fVar), (e) new e.b().g(eVar.b()).h(eVar.c()).n(this.index).o(eVar.f()).p(eVar.g()).f(eVar.a()).l()));
        } else {
            int i2 = iB - 1;
            XMSSNode xMSSNodeB = o.b(this.f5957c, this.authenticationPath.get(i2), this.keep.get(Integer.valueOf(i2)), (c) new c.b().g(cVar.b()).h(cVar.c()).m(i2).n(this.index >> iB).f(cVar.a()).k());
            this.authenticationPath.set(iB, new XMSSNode(xMSSNodeB.getHeight() + 1, xMSSNodeB.getValue()));
            this.keep.remove(Integer.valueOf(i2));
            for (int i3 = 0; i3 < iB; i3++) {
                if (i3 < this.treeHeight - this.k) {
                    list = this.authenticationPath;
                    xMSSNodeRemoveFirst = this.treeHashInstances.get(i3).getTailNode();
                } else {
                    list = this.authenticationPath;
                    xMSSNodeRemoveFirst = this.retain.get(Integer.valueOf(i3)).removeFirst();
                }
                list.set(i3, xMSSNodeRemoveFirst);
            }
            int iMin = Math.min(iB, this.treeHeight - this.k);
            for (int i4 = 0; i4 < iMin; i4++) {
                int i5 = this.index + 1 + ((1 << i4) * 3);
                if (i5 < (1 << this.treeHeight)) {
                    this.treeHashInstances.get(i4).initialize(i5);
                }
            }
        }
        for (int i6 = 0; i6 < ((this.treeHeight - this.k) >> 1); i6++) {
            BDSTreeHash bDSTreeHashA = a();
            if (bDSTreeHashA != null) {
                bDSTreeHashA.update(this.stack, this.f5957c, bArr, bArr2, fVar);
            }
        }
        this.index++;
    }

    public List<XMSSNode> getAuthenticationPath() {
        ArrayList arrayList = new ArrayList();
        Iterator<XMSSNode> it = this.authenticationPath.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().clone());
        }
        return arrayList;
    }

    public int getIndex() {
        return this.index;
    }

    public BDS getNextState(byte[] bArr, byte[] bArr2, f fVar) {
        return new BDS(this, bArr, bArr2, fVar);
    }

    public XMSSNode getRoot() {
        return this.root.clone();
    }

    public int getTreeHeight() {
        return this.treeHeight;
    }

    public boolean isUsed() {
        return this.used;
    }

    public void setXMSS(q qVar) {
        if (this.treeHeight != qVar.d()) {
            throw new IllegalStateException("wrong height");
        }
        this.f5957c = qVar.f();
    }

    public void validate() {
        if (this.authenticationPath == null) {
            throw new IllegalStateException("authenticationPath == null");
        }
        if (this.retain == null) {
            throw new IllegalStateException("retain == null");
        }
        if (this.stack == null) {
            throw new IllegalStateException("stack == null");
        }
        if (this.treeHashInstances == null) {
            throw new IllegalStateException("treeHashInstances == null");
        }
        if (this.keep == null) {
            throw new IllegalStateException("keep == null");
        }
        if (!t.l(this.treeHeight, this.index)) {
            throw new IllegalStateException("index in BDS state out of bounds");
        }
    }
}
