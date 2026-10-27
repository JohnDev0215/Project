package spi;

import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkPreTreat;
import com.byd.overseaappstore.module.ability.util.operate.factory.linea.ExecutorHciWorkProcess;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class y9 {

    @NotNull
    public final ExecutorHciWorkPreTreat a;

    @NotNull
    public final DataGenerator b;

    public y9(@NotNull ExecutorHciWorkPreTreat preTreat, @NotNull ExecutorHciWorkProcess process, @NotNull u9 execute, @NotNull DataGenerator dataGenerator) {
        Intrinsics.checkNotNullParameter(preTreat, "preTreat");
        Intrinsics.checkNotNullParameter(process, "process");
        Intrinsics.checkNotNullParameter(execute, "execute");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        this.a = preTreat;
        this.b = dataGenerator;
        process.a = execute;
        preTreat.a = process;
    }
}
