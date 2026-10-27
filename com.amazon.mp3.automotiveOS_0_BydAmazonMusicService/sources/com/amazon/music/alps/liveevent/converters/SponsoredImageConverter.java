package com.amazon.music.alps.liveevent.converters;

import com.amazon.music.alps.liveevent.enums.ImageType;
import com.amazon.music.alps.liveevent.ktx.LiveEventKt;
import com.amazon.music.alps.liveevent.viewmodel.SponsoredImageItemViewModel;
import com.amazon.music.alps.liveevent.viewmodel.SponsoredImageViewModel;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.metrics.event.UIContentViewWrapper;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: SponsoredImageConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u0006\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/SponsoredImageConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "", "title", "Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class SponsoredImageConverter implements SingleBaseModelConverter<LiveEvent> {
    private final String title;

    public SponsoredImageConverter(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewContainerModel<? extends BaseViewModel> convert(LiveEvent data) {
        List listFilterNotNull;
        ArrayList<LiveEvent.Image> arrayList;
        Intrinsics.checkNotNullParameter(data, "data");
        List<LiveEvent.Image> images = data.getImages();
        if (images == null || (listFilterNotNull = CollectionsKt___CollectionsKt.filterNotNull(images)) == null) {
            arrayList = null;
        } else {
            arrayList = new ArrayList();
            for (Object obj : listFilterNotNull) {
                if (ImageType.INSTANCE.from(((LiveEvent.Image) obj).getImage().getImageType()) == ImageType.SPONSOR) {
                    arrayList.add(obj);
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Intrinsics.checkNotNull(arrayList);
        for (LiveEvent.Image image : arrayList) {
            arrayList2.add(new SponsoredImageItemViewModel(null, LiveEventKt.pageSubType(data), data.getId(), image.getImage().getUrl(), image.getImage().getExternalUrl(), 1, null));
        }
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(MetricsProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        if (!(interfaceProvider instanceof MetricsProvider)) {
            interfaceProvider = null;
        }
        MetricsProvider metricsProvider = (MetricsProvider) interfaceProvider;
        if (metricsProvider != null) {
            metricsProvider.sendMetricsEvent(new UIContentViewWrapper.Builder().withContainerType(ContainerType.SECTION.name()).withContentName(ContentName.SPONSOR_IMAGE.name()).withNumItemsAccessible(Long.valueOf(arrayList2.size())).withEventTime(Long.valueOf(System.currentTimeMillis())).build());
        }
        return new SectionGridViewModel(null, CollectionsKt__CollectionsJVMKt.listOf(new SponsoredImageViewModel(null, arrayList2, 1, null)), 2, null, this.title, null, null, null, null, null, false, null, false, null, null, null, null, 131049, null);
    }
}
