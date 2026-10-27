package com.byd.overseaappstore.ui.vm;

import androidx.lifecycle.ViewModel;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.generator.ErrorUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.InstalledItemUiState;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.notify.alone.language.LanguageChangeStateMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.sequence.ExecutorSequenceDispatcherMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.ExecutorDialogRouterMgr;
import com.byd.overseaappstore.module.ability.util.notify.router.iuu.IUUState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import spi.s9;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002BI\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/InstalledViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "softwareRepository", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;", "softwareUiStateGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;", "errorUiStateGenerator", "Lspi/s9;", "executorHciMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;", "executorDialogRouterMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorSequenceDispatcherMgr;", "executorSequenceDispatcherMgr", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;", "languageChangeStateMgr", "<init>", "(Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;Lspi/s9;Lcom/byd/overseaappstore/module/ability/util/notify/router/ExecutorDialogRouterMgr;Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorSequenceDispatcherMgr;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class InstalledViewModel extends ViewModel {

    @NotNull
    public final SoftwareRepository a;

    @NotNull
    public final SoftwareUiStateGenerator b;

    @NotNull
    public final ErrorUiStateGenerator c;

    @NotNull
    public final s9 d;

    @NotNull
    public final ExecutorSequenceDispatcherMgr e;

    @NotNull
    public final DataGenerator f;

    @NotNull
    public final LanguageChangeStateMgr g;

    @NotNull
    public final SharedFlow<IUUState> h;

    @NotNull
    public final MutableStateFlow<InstalledItemUiState> i;

    @NotNull
    public final StateFlow<InstalledItemUiState> j;

    @NotNull
    public final MutableStateFlow<ErrorEmptyUiState> k;

    @NotNull
    public final StateFlow<ErrorEmptyUiState> l;

    public InstalledViewModel(@NotNull SoftwareRepository softwareRepository, @NotNull SoftwareUiStateGenerator softwareUiStateGenerator, @NotNull ErrorUiStateGenerator errorUiStateGenerator, @NotNull s9 executorHciMgr, @NotNull ExecutorDialogRouterMgr executorDialogRouterMgr, @NotNull ExecutorSequenceDispatcherMgr executorSequenceDispatcherMgr, @NotNull DataGenerator dataGenerator, @NotNull LanguageChangeStateMgr languageChangeStateMgr) {
        Intrinsics.checkNotNullParameter(softwareRepository, "softwareRepository");
        Intrinsics.checkNotNullParameter(softwareUiStateGenerator, "softwareUiStateGenerator");
        Intrinsics.checkNotNullParameter(errorUiStateGenerator, "errorUiStateGenerator");
        Intrinsics.checkNotNullParameter(executorHciMgr, "executorHciMgr");
        Intrinsics.checkNotNullParameter(executorDialogRouterMgr, "executorDialogRouterMgr");
        Intrinsics.checkNotNullParameter(executorSequenceDispatcherMgr, "executorSequenceDispatcherMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(languageChangeStateMgr, "languageChangeStateMgr");
        this.a = softwareRepository;
        this.b = softwareUiStateGenerator;
        this.c = errorUiStateGenerator;
        this.d = executorHciMgr;
        this.e = executorSequenceDispatcherMgr;
        this.f = dataGenerator;
        this.g = languageChangeStateMgr;
        this.h = executorDialogRouterMgr.getExecutorIuuState().getIuuStateDataFlow();
        MutableStateFlow<InstalledItemUiState> MutableStateFlow = StateFlowKt.MutableStateFlow(new InstalledItemUiState(null, false, 3, null));
        this.i = MutableStateFlow;
        this.j = MutableStateFlow;
        MutableStateFlow<ErrorEmptyUiState> MutableStateFlow2 = StateFlowKt.MutableStateFlow(new ErrorEmptyUiState(false, 0, 0, 7, null));
        this.k = MutableStateFlow2;
        this.l = MutableStateFlow2;
    }
}
