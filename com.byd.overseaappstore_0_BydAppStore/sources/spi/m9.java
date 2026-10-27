package spi;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class m9<T> {

    @Nullable
    public m9<T> a;

    @Nullable
    public abstract Object a(@NotNull a2 a2Var, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public abstract Object b(@NotNull a2 a2Var, T t, @NotNull Continuation<? super Unit> continuation);
}
