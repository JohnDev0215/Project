package kotlin.reflect;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u00022\u00020\u0003R\u0016\u0010\u0005\u001a\u00020\u000486@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\n\u001a\u00020\u000786@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\u000b\u001a\u00020\u000486@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0006R\u0016\u0010\f\u001a\u00020\u000486@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u0016\u0010\u0010\u001a\u00020\r86@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0012\u001a\u00020\r86@\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0016\u0010\u0016\u001a\u00020\u001386@\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\u0017\u001a\u00020\u000486@\u0000X§\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0006¨\u0006\u0018"}, d2 = {"Lkotlin/reflect/b;", "R", "Lkotlin/reflect/a;", "Lkotlin/Any;", "Lkotlin/Boolean;", "isOpen", "()Z", "Lkotlin/reflect/KVisibility;", "getVisibility", "()Lkotlin/reflect/KVisibility;", "visibility", "isFinal", "isAbstract", "Ljava/util/List;", "getTypeParameters", "()Ljava/util/List;", "typeParameters", "getParameters", "parameters", "Lkotlin/reflect/o;", "getReturnType", "()Lkotlin/reflect/o;", "returnType", "isSuspend", "kotlin-stdlib"}, k = 1, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
public interface b<R> extends a {
    R call(@NotNull Object... objArr);

    R callBy(@NotNull Map<Object, ? extends Object> map);

    @NotNull
    List<Object> getParameters();

    @NotNull
    o getReturnType();

    @NotNull
    List<Object> getTypeParameters();

    @Nullable
    KVisibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isOpen();

    boolean isSuspend();
}
