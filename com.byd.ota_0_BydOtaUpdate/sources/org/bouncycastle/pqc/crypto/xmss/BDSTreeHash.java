package org.bouncycastle.pqc.crypto.xmss;

import g.a.e.b.e.c;
import g.a.e.b.e.e;
import g.a.e.b.e.f;
import g.a.e.b.e.g;
import g.a.e.b.e.o;
import java.io.Serializable;
import java.util.Objects;
import java.util.Stack;

/* JADX INFO: loaded from: classes.dex */
public class BDSTreeHash implements Serializable {
    private static final long serialVersionUID = 1;
    private int height;
    private final int initialHeight;
    private int nextIndex;
    private XMSSNode tailNode;
    private boolean initialized = false;
    private boolean finished = false;

    public BDSTreeHash(int i2) {
        this.initialHeight = i2;
    }

    public int getHeight() {
        if (!this.initialized || this.finished) {
            return Integer.MAX_VALUE;
        }
        return this.height;
    }

    public int getIndexLeaf() {
        return this.nextIndex;
    }

    public XMSSNode getTailNode() {
        return this.tailNode.clone();
    }

    public void initialize(int i2) {
        this.tailNode = null;
        this.height = this.initialHeight;
        this.nextIndex = i2;
        this.initialized = true;
        this.finished = false;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public void setNode(XMSSNode xMSSNode) {
        this.tailNode = xMSSNode;
        int height = xMSSNode.getHeight();
        this.height = height;
        if (height == this.initialHeight) {
            this.finished = true;
        }
    }

    public void update(Stack<XMSSNode> stack, g gVar, byte[] bArr, byte[] bArr2, f fVar) {
        Objects.requireNonNull(fVar, "otsHashAddress == null");
        if (this.finished || !this.initialized) {
            throw new IllegalStateException("finished or not initialized");
        }
        f fVar2 = (f) new f.b().g(fVar.b()).h(fVar.c()).p(this.nextIndex).n(fVar.e()).o(fVar.f()).f(fVar.a()).l();
        e eVar = (e) new e.b().g(fVar2.b()).h(fVar2.c()).n(this.nextIndex).l();
        c cVar = (c) new c.b().g(fVar2.b()).h(fVar2.c()).n(this.nextIndex).k();
        gVar.h(gVar.g(bArr2, fVar2), bArr);
        XMSSNode xMSSNodeA = o.a(gVar, gVar.e(fVar2), eVar);
        while (!stack.isEmpty() && stack.peek().getHeight() == xMSSNodeA.getHeight() && stack.peek().getHeight() != this.initialHeight) {
            c cVar2 = (c) new c.b().g(cVar.b()).h(cVar.c()).m(cVar.e()).n((cVar.f() - 1) / 2).f(cVar.a()).k();
            XMSSNode xMSSNodeB = o.b(gVar, stack.pop(), xMSSNodeA, cVar2);
            XMSSNode xMSSNode = new XMSSNode(xMSSNodeB.getHeight() + 1, xMSSNodeB.getValue());
            cVar = (c) new c.b().g(cVar2.b()).h(cVar2.c()).m(cVar2.e() + 1).n(cVar2.f()).f(cVar2.a()).k();
            xMSSNodeA = xMSSNode;
        }
        XMSSNode xMSSNode2 = this.tailNode;
        if (xMSSNode2 == null) {
            this.tailNode = xMSSNodeA;
        } else if (xMSSNode2.getHeight() == xMSSNodeA.getHeight()) {
            c cVar3 = (c) new c.b().g(cVar.b()).h(cVar.c()).m(cVar.e()).n((cVar.f() - 1) / 2).f(cVar.a()).k();
            xMSSNodeA = new XMSSNode(this.tailNode.getHeight() + 1, o.b(gVar, this.tailNode, xMSSNodeA, cVar3).getValue());
            this.tailNode = xMSSNodeA;
        } else {
            stack.push(xMSSNodeA);
        }
        if (this.tailNode.getHeight() == this.initialHeight) {
            this.finished = true;
        } else {
            this.height = xMSSNodeA.getHeight();
            this.nextIndex++;
        }
    }
}
