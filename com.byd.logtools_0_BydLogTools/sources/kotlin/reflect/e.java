package kotlin.reflect;

/* JADX INFO: compiled from: KFunction.kt */
/* JADX INFO: loaded from: classes.dex */
public interface e<R> extends b<R> {
    boolean isExternal();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    @Override // kotlin.reflect.b
    boolean isSuspend();
}
