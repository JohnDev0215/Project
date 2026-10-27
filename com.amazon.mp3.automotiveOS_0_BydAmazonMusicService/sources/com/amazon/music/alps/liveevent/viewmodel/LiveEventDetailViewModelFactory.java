package com.amazon.music.alps.liveevent.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.amazon.music.alps.liveevent.LiveEventModelUseCase;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: LiveEventDetailViewModelFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ%\u0010\n\u001a\u0002H\u000b\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0016¢\u0006\u0002\u0010\u000fR\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/alps/liveevent/viewmodel/LiveEventDetailViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "useCase", "Lcom/amazon/music/alps/liveevent/LiveEventModelUseCase;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "refMarker", "", "associateTag", "(Lcom/amazon/music/alps/liveevent/LiveEventModelUseCase;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Ljava/lang/String;Ljava/lang/String;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LiveEventDetailViewModelFactory implements ViewModelProvider.Factory {
    private final String associateTag;
    private final PageType pageType;
    private final String refMarker;
    private final LiveEventModelUseCase useCase;

    public LiveEventDetailViewModelFactory(LiveEventModelUseCase useCase, PageType pageType, String refMarker, String associateTag) {
        Intrinsics.checkNotNullParameter(useCase, "useCase");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(refMarker, "refMarker");
        Intrinsics.checkNotNullParameter(associateTag, "associateTag");
        this.useCase = useCase;
        this.pageType = pageType;
        this.refMarker = refMarker;
        this.associateTag = associateTag;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        LiveEventModelUseCase liveEventModelUseCase = this.useCase;
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof MetricsProvider)) {
            interfaceProvider = null;
        }
        return new LiveEventDetailViewModel(liveEventModelUseCase, (MetricsProvider) interfaceProvider, this.pageType, this.refMarker, this.associateTag);
    }
}
