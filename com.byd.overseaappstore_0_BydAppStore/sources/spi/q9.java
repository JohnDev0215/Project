package spi;

import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class q9 {

    @NotNull
    public final y9 a;

    @NotNull
    public final DataGenerator b;

    public q9(@NotNull y9 workLine, @NotNull DataGenerator dataGenerator, @NotNull o9 cacheLine) {
        Intrinsics.checkNotNullParameter(workLine, "workLine");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(cacheLine, "cacheLine");
        this.a = workLine;
        this.b = dataGenerator;
    }
}
