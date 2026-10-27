package b7;

import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public final class n extends LinkedList {
    public final /* synthetic */ r l;

    public n(r rVar, i iVar) {
        this.l = rVar;
        m mVar = new m(this, 0);
        iVar.T0("open", mVar);
        add(new j(iVar, "open", mVar));
        m mVar2 = new m(this, 1);
        iVar.T0("packet", mVar2);
        add(new j(iVar, "packet", mVar2));
        m mVar3 = new m(this, 2);
        iVar.T0("error", mVar3);
        add(new j(iVar, "error", mVar3));
        m mVar4 = new m(this, 3);
        iVar.T0("close", mVar4);
        add(new j(iVar, "close", mVar4));
    }
}
