package com.byd.overseaappstore.ui.vm;

import androidx.lifecycle.ViewModel;
import com.byd.overseaappstore.module.ability.data.generator.DataGenerator;
import com.byd.overseaappstore.module.ability.data.generator.ErrorUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.generator.SoftwareUiStateGenerator;
import com.byd.overseaappstore.module.ability.data.uistate.BannerUiState;
import com.byd.overseaappstore.module.ability.data.uistate.ErrorEmptyUiState;
import com.byd.overseaappstore.module.ability.data.uistate.FeatureThemGroupUiState;
import com.byd.overseaappstore.module.ability.data.uistate.FooterHeaderUiState;
import com.byd.overseaappstore.module.ability.data.uistate.LoadingUiState;
import com.byd.overseaappstore.module.ability.data.uistate.SoftwareThemeUiState;
import com.byd.overseaappstore.module.ability.data.uistate.ToastUiState;
import com.byd.overseaappstore.module.ability.repo.SoftwareRepository;
import com.byd.overseaappstore.module.ability.util.notify.alone.language.LanguageChangeStateMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.sequence.ExecutorSequenceDispatcherMgr;
import com.byd.overseaappstore.module.ability.util.notify.alone.trigger.ExecutorSoftwareNewTaskInTrigger;
import java.util.HashMap;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import spi.e1;
import spi.oa;
import spi.pb;
import spi.s9;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002BY\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/byd/overseaappstore/ui/vm/FeatureViewModel;", "Landroidx/lifecycle/ViewModel;", "", "Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;", "softwareRepository", "Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;", "softwareUiStateGenerator", "Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;", "errorUiStateGenerator", "Lspi/e1;", "bannerRepository", "Lspi/s9;", "executorHciMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;", "executorSoftwareNewTaskInTrigger", "Lspi/oa;", "executorSoftwareStateMgr", "Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;", "dataGenerator", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorSequenceDispatcherMgr;", "executorSequenceDispatcherMgr", "Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;", "languageChangeStateMgr", "<init>", "(Lcom/byd/overseaappstore/module/ability/repo/SoftwareRepository;Lcom/byd/overseaappstore/module/ability/data/generator/SoftwareUiStateGenerator;Lcom/byd/overseaappstore/module/ability/data/generator/ErrorUiStateGenerator;Lspi/e1;Lspi/s9;Lcom/byd/overseaappstore/module/ability/util/notify/alone/trigger/ExecutorSoftwareNewTaskInTrigger;Lspi/oa;Lcom/byd/overseaappstore/module/ability/data/generator/DataGenerator;Lcom/byd/overseaappstore/module/ability/util/notify/alone/sequence/ExecutorSequenceDispatcherMgr;Lcom/byd/overseaappstore/module/ability/util/notify/alone/language/LanguageChangeStateMgr;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class FeatureViewModel extends ViewModel {

    @NotNull
    public final MutableStateFlow<HashMap<String, Integer>> A;

    @NotNull
    public final StateFlow<HashMap<String, Integer>> B;

    @NotNull
    public final SoftwareRepository a;

    @NotNull
    public final SoftwareUiStateGenerator b;

    @NotNull
    public final ErrorUiStateGenerator c;

    @NotNull
    public final e1 d;

    @NotNull
    public final s9 e;

    @NotNull
    public final ExecutorSoftwareNewTaskInTrigger f;

    @NotNull
    public final oa g;

    @NotNull
    public final DataGenerator h;

    @NotNull
    public final ExecutorSequenceDispatcherMgr i;

    @NotNull
    public final LanguageChangeStateMgr j;

    @NotNull
    public final MutableStateFlow<BannerUiState> k;

    @NotNull
    public final StateFlow<BannerUiState> l;

    @NotNull
    public final MutableStateFlow<SoftwareThemeUiState> m;

    @NotNull
    public final StateFlow<SoftwareThemeUiState> n;

    @NotNull
    public final MutableStateFlow<FeatureThemGroupUiState> o;

    @NotNull
    public final StateFlow<FeatureThemGroupUiState> p;

    @NotNull
    public final MutableStateFlow<LoadingUiState> q;

    @NotNull
    public final StateFlow<LoadingUiState> r;

    @NotNull
    public final MutableStateFlow<ToastUiState> s;

    @NotNull
    public final StateFlow<ToastUiState> t;

    @NotNull
    public final MutableStateFlow<ErrorEmptyUiState> u;

    @NotNull
    public final StateFlow<ErrorEmptyUiState> v;

    @NotNull
    public final MutableStateFlow<Boolean> w;

    @NotNull
    public final StateFlow<Boolean> x;

    @NotNull
    public final MutableStateFlow<FooterHeaderUiState> y;

    @NotNull
    public final StateFlow<FooterHeaderUiState> z;

    public FeatureViewModel(@NotNull SoftwareRepository softwareRepository, @NotNull SoftwareUiStateGenerator softwareUiStateGenerator, @NotNull ErrorUiStateGenerator errorUiStateGenerator, @NotNull e1 bannerRepository, @NotNull s9 executorHciMgr, @NotNull ExecutorSoftwareNewTaskInTrigger executorSoftwareNewTaskInTrigger, @NotNull oa executorSoftwareStateMgr, @NotNull DataGenerator dataGenerator, @NotNull ExecutorSequenceDispatcherMgr executorSequenceDispatcherMgr, @NotNull LanguageChangeStateMgr languageChangeStateMgr) {
        Intrinsics.checkNotNullParameter(softwareRepository, "softwareRepository");
        Intrinsics.checkNotNullParameter(softwareUiStateGenerator, "softwareUiStateGenerator");
        Intrinsics.checkNotNullParameter(errorUiStateGenerator, "errorUiStateGenerator");
        Intrinsics.checkNotNullParameter(bannerRepository, "bannerRepository");
        Intrinsics.checkNotNullParameter(executorHciMgr, "executorHciMgr");
        Intrinsics.checkNotNullParameter(executorSoftwareNewTaskInTrigger, "executorSoftwareNewTaskInTrigger");
        Intrinsics.checkNotNullParameter(executorSoftwareStateMgr, "executorSoftwareStateMgr");
        Intrinsics.checkNotNullParameter(dataGenerator, "dataGenerator");
        Intrinsics.checkNotNullParameter(executorSequenceDispatcherMgr, "executorSequenceDispatcherMgr");
        Intrinsics.checkNotNullParameter(languageChangeStateMgr, "languageChangeStateMgr");
        this.a = softwareRepository;
        this.b = softwareUiStateGenerator;
        this.c = errorUiStateGenerator;
        this.d = bannerRepository;
        this.e = executorHciMgr;
        this.f = executorSoftwareNewTaskInTrigger;
        this.g = executorSoftwareStateMgr;
        this.h = dataGenerator;
        this.i = executorSequenceDispatcherMgr;
        this.j = languageChangeStateMgr;
        MutableStateFlow<BannerUiState> MutableStateFlow = StateFlowKt.MutableStateFlow(new BannerUiState(false, null, false, 7, null));
        this.k = MutableStateFlow;
        this.l = MutableStateFlow;
        MutableStateFlow<SoftwareThemeUiState> MutableStateFlow2 = StateFlowKt.MutableStateFlow(new SoftwareThemeUiState(false, null, false, 7, null));
        this.m = MutableStateFlow2;
        this.n = MutableStateFlow2;
        MutableStateFlow<FeatureThemGroupUiState> MutableStateFlow3 = StateFlowKt.MutableStateFlow(new FeatureThemGroupUiState(false, null, false, 7, null));
        this.o = MutableStateFlow3;
        this.p = MutableStateFlow3;
        MutableStateFlow<LoadingUiState> MutableStateFlow4 = StateFlowKt.MutableStateFlow(new LoadingUiState(null, false, 3, null));
        this.q = MutableStateFlow4;
        this.r = MutableStateFlow4;
        MutableStateFlow<ToastUiState> MutableStateFlow5 = StateFlowKt.MutableStateFlow(new ToastUiState(false, 0, 3, null));
        this.s = MutableStateFlow5;
        this.t = MutableStateFlow5;
        MutableStateFlow<ErrorEmptyUiState> MutableStateFlow6 = StateFlowKt.MutableStateFlow(new ErrorEmptyUiState(false, 0, 0, 7, null));
        this.u = MutableStateFlow6;
        this.v = MutableStateFlow6;
        MutableStateFlow<Boolean> MutableStateFlow7 = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.w = MutableStateFlow7;
        this.x = MutableStateFlow7;
        MutableStateFlow<FooterHeaderUiState> MutableStateFlow8 = StateFlowKt.MutableStateFlow(new FooterHeaderUiState(false, 0, 3, null));
        this.y = MutableStateFlow8;
        this.z = MutableStateFlow8;
        MutableStateFlow<HashMap<String, Integer>> MutableStateFlow9 = StateFlowKt.MutableStateFlow(new HashMap());
        this.A = MutableStateFlow9;
        this.B = MutableStateFlow9;
    }

    public static final void a(FeatureViewModel featureViewModel, String str, int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                Integer num = featureViewModel.B.getValue().get(str);
                if (num == null || num.intValue() != i) {
                    HashMap<String, Integer> map = new HashMap<>();
                    map.putAll(featureViewModel.B.getValue());
                    map.put(str, Integer.valueOf(i));
                    featureViewModel.A.setValue(map);
                    break;
                }
                break;
            case 6:
            case 7:
            default:
                if (featureViewModel.B.getValue().containsKey(str)) {
                    HashMap<String, Integer> map2 = new HashMap<>();
                    map2.putAll(featureViewModel.B.getValue());
                    map2.remove(str);
                    featureViewModel.A.setValue(map2);
                }
                break;
        }
    }

    public static void b(FeatureViewModel featureViewModel) {
        CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        Objects.requireNonNull(featureViewModel);
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new pb(featureViewModel, null), 3, null);
    }
}
