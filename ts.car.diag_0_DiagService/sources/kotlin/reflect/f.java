package kotlin.reflect;

import kotlin.Metadata;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u00022\u00020\u00032\u00020\u0004R\u0016\u0010\u0006\u001a\u00020\u000586@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u00020\u000586@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0007R\u0016\u0010\t\u001a\u00020\u000586@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0016\u0010\n\u001a\u00020\u000586@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0007R\u0016\u0010\u000b\u001a\u00020\u000586@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007¨\u0006\f"}, d2 = {"Lkotlin/reflect/f;", "R", "Lkotlin/reflect/b;", "Lkotlin/a;", "Lkotlin/Any;", "Lkotlin/Boolean;", "isExternal", "()Z", "isInfix", "isInline", "isSuspend", "isOperator", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public interface f<R> extends b<R>, kotlin.a<R> {
    boolean isExternal();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    @Override // kotlin.reflect.b
    boolean isSuspend();
}
