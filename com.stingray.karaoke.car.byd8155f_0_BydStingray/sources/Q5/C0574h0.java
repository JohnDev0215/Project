package Q5;

import java.util.List;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q5.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0574h0 implements InterfaceC2786o {
    public final /* synthetic */ List l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f6838m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ E.D f6839n;
    public final /* synthetic */ InterfaceC2782k o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f6840p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ D.L f6841q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f6842r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ b6.g f6843s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Integer f6844t;

    public C0574h0(List list, InterfaceC2785n interfaceC2785n, E.D d, InterfaceC2782k interfaceC2782k, int i7, D.L l, InterfaceC2785n interfaceC2785n2, b6.g gVar, Integer num) {
        this.l = list;
        this.f6838m = interfaceC2785n;
        this.f6839n = d;
        this.o = interfaceC2782k;
        this.f6840p = i7;
        this.f6841q = l;
        this.f6842r = interfaceC2785n2;
        this.f6843s = gVar;
        this.f6844t = num;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v21 ??, still in use, count: 1, list:
          (r2v21 ?? I:java.lang.Object) from 0x00c0: INVOKE (r13v1 ?? I:T.q), (r2v21 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    @Override // z7.InterfaceC2786o
    public final java.lang.Object g(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v21 ??, still in use, count: 1, list:
          (r2v21 ?? I:java.lang.Object) from 0x00c0: INVOKE (r13v1 ?? I:T.q), (r2v21 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r23v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */
}
