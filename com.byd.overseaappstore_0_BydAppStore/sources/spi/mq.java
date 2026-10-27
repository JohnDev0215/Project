package spi;

import androidx.room.Dao;
import androidx.room.Query;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Dao
public interface mq {
    @Query("DELETE from SoftwareExtraLocal Where packageName =:packageName")
    @Nullable
    Object a(@NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Query("DELETE from SoftwareExtraLocal Where primePackageName =:primePackageName")
    @Nullable
    Object b(@NotNull String str, @NotNull Continuation<? super Unit> continuation);

    @Query("SELECT * FROM SoftwareExtraLocal WHERE packageName = :packageName")
    @Nullable
    Object g(@NotNull String str, @NotNull Continuation<? super oq> continuation);
}
