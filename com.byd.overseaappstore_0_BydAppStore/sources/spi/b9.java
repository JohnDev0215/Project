package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface b9 {
    @Nullable
    Object a(@NotNull List<SoftwareOptParam> list, int i, @NotNull Continuation<? super List<Pair<SoftwareStateData, SoftwareStateData>>> continuation);

    @Nullable
    Object b(@NotNull SoftwareOptParam softwareOptParam, @NotNull Pair<SoftwareStateData, SoftwareStateData> pair, int i, @NotNull Continuation<? super Unit> continuation);

    @Nullable
    Object c(@NotNull SoftwareOptParam softwareOptParam, int i, @NotNull Continuation<? super Pair<SoftwareStateData, SoftwareStateData>> continuation);

    @Nullable
    Object d(@NotNull List<SoftwareOptParam> list, @NotNull List<Pair<SoftwareStateData, SoftwareStateData>> list2, int i, @NotNull Continuation<? super Unit> continuation);
}
