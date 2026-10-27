package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface z9 {
    @Nullable
    Object a(@NotNull String str, @NotNull qq qqVar, @NotNull Context context, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object b(@NotNull String str, @NotNull qq qqVar, @NotNull Context context, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object c(@NotNull SoftwareOptParam softwareOptParam, @NotNull Context context, @NotNull Continuation<? super Unit> continuation);

    void d(@NotNull SoftwareOptParam softwareOptParam, @NotNull Context context);

    @Nullable
    Object e(@NotNull String str, @NotNull qq qqVar, @NotNull Context context, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object f(@NotNull SoftwareOptParam softwareOptParam, @NotNull Context context, @NotNull Continuation<? super Unit> continuation);
}
