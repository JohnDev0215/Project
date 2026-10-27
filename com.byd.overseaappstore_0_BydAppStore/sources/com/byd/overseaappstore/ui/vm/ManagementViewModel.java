package com.byd.overseaappstore.ui.vm;

import androidx.lifecycle.ViewModel;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.interceptor.SoftwareDataInterceptorFactory;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.notify.alone.sequence.ExecutorSequenceDispatcherMgr;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B1\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/ManagementViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "softwareRepository", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;", "softwareUiStateGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataInterceptorFactory;", "softwareDataInterceptorFactory", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorSequenceDispatcherMgr;", "executorSequenceDispatcherMgr", "<init>", "(Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;Lcom/byd/overseaappstore/module/ability/data/generator/interceptor/SoftwareDataInterceptorFactory;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorSequenceDispatcherMgr;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ManagementViewModel extends ViewModel {

    @NotNull
    public final SoftwareRepository a;

    @NotNull
    public final SoftwareUiStateGenerator b;

    @NotNull
    public final SoftwareDataInterceptorFactory c;

    @NotNull
    public final DataGenerator d;

    @NotNull
    public final ExecutorSequenceDispatcherMgr e;

    @NotNull
    public final MutableStateFlow<Boolean> f;

    @NotNull
    public final StateFlow<Boolean> g;

    public ManagementViewModel(@NotNull SoftwareRepository softwareRepository, @NotNull SoftwareUiStateGenerator softwareUiStateGenerator, @NotNull SoftwareDataInterceptorFactory softwareDataInterceptorFactory, @NotNull DataGenerator dataGenerator, @NotNull ExecutorSequenceDispatcherMgr executorSequenceDispatcherMgr) {
        Intrinsics.checkNotNullParameter(softwareRepository, "softwareRepository");
        Intrinsics.checkNotNullParameter(softwareUiStateGenerator, "softwareUiStateGenerator");
        Intrinsics.checkNotNullParameter(softwareDataInterceptorFactory, "softwareDataInterceptorFactory");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorSequenceDispatcherMgr, "executorSequenceDispatcherMgr");
        this.a = softwareRepository;
        this.b = softwareUiStateGenerator;
        this.c = softwareDataInterceptorFactory;
        this.d = dataGenerator;
        this.e = executorSequenceDispatcherMgr;
        MutableStateFlow<Boolean> MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.f = MutableStateFlow;
        this.g = MutableStateFlow;
    }
}
