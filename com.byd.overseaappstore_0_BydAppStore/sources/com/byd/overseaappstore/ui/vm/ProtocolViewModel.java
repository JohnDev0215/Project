package com.byd.overseaappstore.ui.vm;

import androidx.lifecycle.ViewModel;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/ProtocolViewModel;", "Landroidx/lifecycle/ViewModel;", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "<init>", "(Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ProtocolViewModel extends ViewModel {

    @NotNull
    public final DataGenerator a;

    public ProtocolViewModel(@NotNull DataGenerator dataGenerator) {
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        this.a = dataGenerator;
    }
}
