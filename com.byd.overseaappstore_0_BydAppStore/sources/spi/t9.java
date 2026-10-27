package spi;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public abstract class t9<T> {

    @Nullable
    public t9<T> a;

    @Nullable
    public abstract Object a(@NotNull a2 a2Var, @NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public abstract Object b(@NotNull a2 a2Var, @NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public abstract Object c(@NotNull a2 a2Var, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public abstract Object d(@NotNull a2 a2Var, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public abstract Object e(@NotNull a2 a2Var, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public abstract Object f(@NotNull a2 a2Var, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public abstract Object g(@NotNull a2 a2Var, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    public abstract Object h(@NotNull a2 a2Var, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation);

    public abstract void i(@NotNull a2 a2Var, T t);

    @Nullable
    public abstract Object j(@NotNull a2 a2Var, @NotNull Flow<? extends T> flow, @NotNull Continuation<? super Unit> continuation);
}
