package spi;

import com.byd.overseaappstore.module.ability.util.download.data.SoftwareOptParam;
import com.byd.overseaappstore.module.ability.util.download.data.SoftwareStateData;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
public final class g9 implements b9 {

    @NotNull
    public final b9 a;

    @NotNull
    public final b9 b;

    @NotNull
    public final b9 c;

    @NotNull
    public final b9 d;

    @NotNull
    public final b9 e;

    public g9(@NotNull b9 executorConnectWorkClickSingle, @NotNull b9 executorConnectWorkClickMulti, @NotNull b9 executorConnectWorkClickMultiSpecial, @NotNull b9 executorConnectWorkWaitingResume, @NotNull b9 executorConnectWorkAutoResume) {
        Intrinsics.checkNotNullParameter(executorConnectWorkClickSingle, "executorConnectWorkClickSingle");
        Intrinsics.checkNotNullParameter(executorConnectWorkClickMulti, "executorConnectWorkClickMulti");
        Intrinsics.checkNotNullParameter(executorConnectWorkClickMultiSpecial, "executorConnectWorkClickMultiSpecial");
        Intrinsics.checkNotNullParameter(executorConnectWorkWaitingResume, "executorConnectWorkWaitingResume");
        Intrinsics.checkNotNullParameter(executorConnectWorkAutoResume, "executorConnectWorkAutoResume");
        this.a = executorConnectWorkClickSingle;
        this.b = executorConnectWorkClickMulti;
        this.c = executorConnectWorkClickMultiSpecial;
        this.d = executorConnectWorkWaitingResume;
        this.e = executorConnectWorkAutoResume;
    }

    @Override // spi.b9
    @Nullable
    public final Object a(@NotNull List<SoftwareOptParam> list, int i, @NotNull Continuation<? super List<Pair<SoftwareStateData, SoftwareStateData>>> continuation) {
        return e(i).a(list, i, continuation);
    }

    @Override // spi.b9
    @Nullable
    public final Object b(@NotNull SoftwareOptParam softwareOptParam, @NotNull Pair<SoftwareStateData, SoftwareStateData> pair, int i, @NotNull Continuation<? super Unit> continuation) {
        Object objB = e(i).b(softwareOptParam, pair, i, continuation);
        return objB == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objB : Unit.INSTANCE;
    }

    @Override // spi.b9
    @Nullable
    public final Object c(@NotNull SoftwareOptParam softwareOptParam, int i, @NotNull Continuation<? super Pair<SoftwareStateData, SoftwareStateData>> continuation) {
        return e(i).c(softwareOptParam, i, continuation);
    }

    @Override // spi.b9
    @Nullable
    public final Object d(@NotNull List<SoftwareOptParam> list, @NotNull List<Pair<SoftwareStateData, SoftwareStateData>> list2, int i, @NotNull Continuation<? super Unit> continuation) {
        Object objD = e(i).d(list, list2, i, continuation);
        return objD == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objD : Unit.INSTANCE;
    }

    public final b9 e(int i) {
        if (i == 0) {
            ct.a(e.c("executor getSoftwareSoftwareConnectWork CLICK_FOR_SINGLE_TASK policy:", i), new Object[0]);
            return this.a;
        }
        if (i == 1) {
            ct.a(e.c("executor getSoftwareSoftwareConnectWork CLICK_FOR_MULTI_TASKS policy:", i), new Object[0]);
            return this.b;
        }
        if (i == 2) {
            ct.a(e.c("executor getSoftwareSoftwareConnectWork WAITING_RESUME policy:", i), new Object[0]);
            return this.d;
        }
        if (i != 5) {
            ct.a(e.c("executor getSoftwareSoftwareConnectWork AUTO_RESUME policy:", i), new Object[0]);
            return this.e;
        }
        ct.a(e.c("executor getSoftwareSoftwareConnectWork CLICK_FOR_MULTI_SPACIAL_TASKS policy:", i), new Object[0]);
        return this.c;
    }
}
