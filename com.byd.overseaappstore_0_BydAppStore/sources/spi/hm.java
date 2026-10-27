package spi;

import android.content.Context;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class hm {

    @NotNull
    public final DataGenerator a;

    @NotNull
    public final Context b;

    public hm(@NotNull DataGenerator dataGenerator, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(context, "context");
        this.a = dataGenerator;
        this.b = context;
    }
}
