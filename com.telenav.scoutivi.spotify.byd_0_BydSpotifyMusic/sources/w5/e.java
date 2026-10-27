package w5;

/* JADX INFO: compiled from: KFunction.kt */
/* JADX INFO: loaded from: classes.dex */
public interface e<R> extends b<R>, g5.c<R> {
    boolean isExternal();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    @Override // w5.b
    boolean isSuspend();
}
