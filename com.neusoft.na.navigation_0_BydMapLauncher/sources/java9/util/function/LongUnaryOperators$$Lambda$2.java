package java9.util.function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class LongUnaryOperators$$Lambda$2 implements LongUnaryOperator {
    private final LongUnaryOperator arg$1;
    private final LongUnaryOperator arg$2;

    private LongUnaryOperators$$Lambda$2(LongUnaryOperator longUnaryOperator, LongUnaryOperator longUnaryOperator2) {
        this.arg$1 = longUnaryOperator;
        this.arg$2 = longUnaryOperator2;
    }

    /* JADX WARN: Method inline analysis failed
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList.removeIf(ArrayList.java:1768)
    	at java.base/java.util.ArrayList.removeIf(ArrayList.java:1743)
    	at jadx.core.dex.instructions.args.SSAVar.removeUse(SSAVar.java:139)
    	at jadx.core.dex.visitors.MarkMethodsForInline.lambda$unbindSsaVars$1(MarkMethodsForInline.java:150)
    	at jadx.core.dex.nodes.InsnNode.visitArgs(InsnNode.java:339)
    	at jadx.core.dex.visitors.MarkMethodsForInline.unbindSsaVars(MarkMethodsForInline.java:145)
    	at jadx.core.dex.visitors.MarkMethodsForInline.addInlineAttr(MarkMethodsForInline.java:135)
    	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:82)
    	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:55)
    	at jadx.core.dex.visitors.MarkMethodsForInline.visit(MarkMethodsForInline.java:38)
     */
    public static LongUnaryOperator lambdaFactory$(LongUnaryOperator longUnaryOperator, LongUnaryOperator longUnaryOperator2) {
        return new LongUnaryOperators$$Lambda$2(longUnaryOperator, longUnaryOperator2);
    }

    @Override // java9.util.function.LongUnaryOperator
    public long applyAsLong(long j) {
        return LongUnaryOperators.lambda$andThen$1(this.arg$1, this.arg$2, j);
    }
}
