package com.amazon.music.alps.liveevent.converters;

import androidx.annotation.VisibleForTesting;
import com.amazon.music.alps.config.URLConfig;
import com.amazon.music.alps.liveevent.viewmodel.LineupViewModel;
import com.amazon.music.alps.liveevent.viewmodel.PlaceholderViewModel;
import com.amazon.music.alps.liveevent.viewmodel.SelectItemViewModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.metrics.event.UIContentViewWrapper;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: LineupGroupsSectionConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\n\u0012\u0006\u0010\u0014\u001a\u00020\n\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0018\u0010\u0019J*\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016J\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u000b\u001a\u00020\nH\u0007J\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0007R\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0013R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/LineupGroupsSectionConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "", "getBaseUrl", "baseUrl", "dataId", "getMoreTarget", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "childConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "title", "Ljava/lang/String;", "moreText", "", "showMore", "Z", "<init>", "(Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;Ljava/lang/String;Ljava/lang/String;Z)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class LineupGroupsSectionConverter implements BaseContainerModelConverter<LiveEvent> {
    private final SingleBaseModelConverter<LiveEvent> childConverter;
    private final String moreText;
    private final boolean showMore;
    private final String title;

    public LineupGroupsSectionConverter(SingleBaseModelConverter<LiveEvent> childConverter, String title, String moreText, boolean z) {
        Intrinsics.checkNotNullParameter(childConverter, "childConverter");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(moreText, "moreText");
        this.childConverter = childConverter;
        this.title = title;
        this.moreText = moreText;
        this.showMore = z;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(LiveEvent liveEvent, List list) {
        return convert2(liveEvent, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(LiveEvent data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (children == null || children.isEmpty()) {
            return null;
        }
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        MetricsProvider metricsProvider = (MetricsProvider) (interfaceProvider instanceof MetricsProvider ? interfaceProvider : null);
        if (metricsProvider != null) {
            metricsProvider.sendMetricsEvent(new UIContentViewWrapper.Builder().withContainerType(ContainerType.SECTION.name()).withContentName(ContentName.LIVE_EVENT_LINEUP.name()).withNumItemsAccessible(Long.valueOf(children.size())).withEventTime(Long.valueOf(System.currentTimeMillis())).build());
        }
        if (this.showMore) {
            String baseUrl = getBaseUrl();
            String str = this.title;
            String str2 = this.moreText;
            String id = data.getId();
            Intrinsics.checkNotNull(id);
            return new SectionGridViewModel(ContentName.LIVE_EVENT_LINEUP.name(), children, 2, null, str, null, str2, getMoreTarget(baseUrl, id), null, null, false, 1, false, null, null, null, null, 128808, null);
        }
        return new SectionGridViewModel(null, children, 2, null, this.title, null, null, null, null, null, false, null, false, null, null, null, null, 131049, null);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(LiveEvent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        BaseViewModel baseViewModelConvert = this.childConverter.convert(data);
        if (baseViewModelConvert != null) {
            List<SelectItemViewModel> lineupItemList = ((LineupViewModel) baseViewModelConvert).getLineupItemList();
            if (!(lineupItemList == null || lineupItemList.isEmpty())) {
                arrayList.add(baseViewModelConvert);
                if (this.showMore) {
                    arrayList.add(new PlaceholderViewModel(null, 1, null));
                }
            }
        }
        return arrayList;
    }

    @VisibleForTesting
    public final String getBaseUrl() {
        return URLConfig.SHARE_BASE_URL.getValue();
    }

    @VisibleForTesting
    public final String getMoreTarget(String baseUrl, String dataId) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(dataId, "dataId");
        return baseUrl + "live/events/" + dataId + "/lineup";
    }
}
