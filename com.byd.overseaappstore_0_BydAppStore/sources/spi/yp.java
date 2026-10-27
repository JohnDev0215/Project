package spi;

import com.byd.overseaappstore.module.ability.data.ApiDataBody;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface yp {
    @Nullable
    Object a(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object b(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object c(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object d(int i, @NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object e(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object f(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object g(@NotNull String str, @NotNull Continuation<? super ApiDataBody<oq>> continuation);

    @Nullable
    Object h(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object i(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object j(@NotNull String str, @NotNull Continuation<? super ApiDataBody<qq>> continuation);

    @Nullable
    Object k(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object l(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object m(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object n(@NotNull Continuation<? super ApiDataBody<List<qq>>> continuation);

    @Nullable
    Object o(@NotNull pq pqVar, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object p(@NotNull pq pqVar, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object q(@NotNull oq oqVar, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object r(@NotNull String str, int i, int i2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object s(@NotNull List<oq> list, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object t(@NotNull String str, int i, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object u(@NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object v(@NotNull String str, int i, @NotNull String str2, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object w(@NotNull String str, @NotNull Continuation<? super Unit> continuation);
}
