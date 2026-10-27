package com.amazon.music.alps.liveevent.converters;

import android.content.Context;
import com.amazon.music.alps.R$string;
import com.amazon.music.alps.liveevent.LiveEventStatus;
import com.amazon.music.alps.liveevent.ktx.LiveEventKt;
import com.amazon.music.alps.liveevent.viewmodel.NewWaysToWatchViewModel;
import com.amazon.music.alps.liveevent.viewmodel.VerticalIconLinkItemViewModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.graphql.generated.fragment.Lineup;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.metrics.event.UIContentViewWrapper;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: NewWaysToWatchSectionConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u0001¢\u0006\u0004\b\u0010\u0010\u0011J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u001a\u0010\t\u001a\u0004\u0018\u00010\b2\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/NewWaysToWatchSectionConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "data", "", "Lcom/amazon/music/alps/liveevent/viewmodel/VerticalIconLinkItemViewModel;", "getChildrenModels", "children", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/platform/graphql/generated/fragment/Lineup$Endpoint;", "childConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class NewWaysToWatchSectionConverter implements SingleBaseModelConverter<LiveEvent> {
    private final SingleBaseModelConverter<Lineup.Endpoint> childConverter;
    private final Context context;

    /* JADX INFO: compiled from: NewWaysToWatchSectionConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LiveEventStatus.values().length];
            iArr[LiveEventStatus.UPCOMING.ordinal()] = 1;
            iArr[LiveEventStatus.BETWEEN.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public NewWaysToWatchSectionConverter(Context context, SingleBaseModelConverter<Lineup.Endpoint> childConverter) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(childConverter, "childConverter");
        this.context = context;
        this.childConverter = childConverter;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(LiveEvent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        List<VerticalIconLinkItemViewModel> childrenModels = getChildrenModels(data);
        int i = WhenMappings.$EnumSwitchMapping$0[LiveEventStatus.INSTANCE.from(data.getStatus()).ordinal()];
        if (i == 1) {
            return convert(childrenModels);
        }
        if (i != 2) {
            return null;
        }
        return convert(childrenModels);
    }

    private final List<VerticalIconLinkItemViewModel> getChildrenModels(LiveEvent data) {
        List<Lineup.Endpoint> endpoints;
        List listFilterNotNull;
        ArrayList arrayList = new ArrayList();
        Lineup nextLineup = LiveEventKt.getNextLineup(data);
        if (nextLineup != null && (endpoints = nextLineup.getEndpoints()) != null && (listFilterNotNull = CollectionsKt___CollectionsKt.filterNotNull(endpoints)) != null) {
            Iterator it = listFilterNotNull.iterator();
            while (it.hasNext()) {
                BaseViewModel baseViewModelConvert = this.childConverter.convert((Lineup.Endpoint) it.next());
                if (baseViewModelConvert != null) {
                    arrayList.add((VerticalIconLinkItemViewModel) baseViewModelConvert);
                }
            }
        }
        return arrayList;
    }

    private final BaseViewModel convert(List<VerticalIconLinkItemViewModel> children) {
        if (children == null || children.isEmpty()) {
            return null;
        }
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        MetricsProvider metricsProvider = (MetricsProvider) (interfaceProvider instanceof MetricsProvider ? interfaceProvider : null);
        if (metricsProvider != null) {
            metricsProvider.sendMetricsEvent(new UIContentViewWrapper.Builder().withContainerType(ContainerType.SECTION.name()).withContentName(ContentName.VERTICAL_ICON_LINK_BUTTON.name()).withNumItemsAccessible(Long.valueOf(children.size())).withEventTime(Long.valueOf(System.currentTimeMillis())).build());
        }
        return new NewWaysToWatchViewModel(null, this.context.getString(R$string.ways_to_watch), children, 1, null);
    }
}
