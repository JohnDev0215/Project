package com.amazon.music.alps.liveevent.converters;

import com.amazon.music.alps.config.URLConfig;
import com.amazon.music.alps.liveevent.model.ArtistMerch;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.graphql.generated.ArtistMerchQuery;
import com.amazon.music.platform.graphql.generated.fragment.Merch;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.metrics.event.UIContentViewWrapper;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ShovelerModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: ArtistMerchShovelerConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u000e¢\u0006\u0004\b\u0012\u0010\u0013J*\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016J\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0010¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/ArtistMerchShovelerConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/platform/graphql/generated/ArtistMerchQuery$Artist;", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/alps/liveevent/model/ArtistMerch;", "childConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "", "title", "Ljava/lang/String;", "moreText", "<init>", "(Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;Ljava/lang/String;Ljava/lang/String;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class ArtistMerchShovelerConverter implements BaseContainerModelConverter<ArtistMerchQuery.Artist> {
    private final SingleBaseModelConverter<ArtistMerch> childConverter;
    private final String moreText;
    private final String title;

    public ArtistMerchShovelerConverter(SingleBaseModelConverter<ArtistMerch> childConverter, String title, String moreText) {
        Intrinsics.checkNotNullParameter(childConverter, "childConverter");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(moreText, "moreText");
        this.childConverter = childConverter;
        this.title = title;
        this.moreText = moreText;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(ArtistMerchQuery.Artist artist, List list) {
        return convert2(artist, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(ArtistMerchQuery.Artist data, List<? extends BaseViewModel> children) {
        ArtistMerchQuery.PageInfo pageInfo;
        ShovelerModel shovelerModel;
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
            metricsProvider.sendMetricsEvent(new UIContentViewWrapper.Builder().withContainerType(ContainerType.SECTION.name()).withContentName(ContentName.ARTIST_MERCH_SHOVELER.name()).withNumItemsAccessible(Long.valueOf(children.size())).withEventTime(Long.valueOf(System.currentTimeMillis())).build());
        }
        ArtistMerchQuery.RelatedMerch relatedMerch = data.getRelatedMerch();
        if ((relatedMerch == null || (pageInfo = relatedMerch.getPageInfo()) == null || !pageInfo.getHasNextPage()) ? false : true) {
            String value = URLConfig.SHARE_BASE_URL.getValue();
            shovelerModel = new ShovelerModel(ContentName.ARTIST_MERCH_SHOVELER.name(), children, 17, null, this.title, null, this.moreText, null, value + "merch/artists/" + ((Object) data.getArtistEmbedded().getId()), false, null, null, null, false, null, 32424, null);
        } else {
            shovelerModel = new ShovelerModel(ContentName.ARTIST_MERCH_SHOVELER.name(), children, 17, null, this.title, null, null, null, null, false, null, null, null, false, null, 32744, null);
        }
        return shovelerModel;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(ArtistMerchQuery.Artist data) {
        List<ArtistMerchQuery.Edge> edges;
        ArtistMerchQuery.Node node;
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        ArtistMerchQuery.RelatedMerch relatedMerch = data.getRelatedMerch();
        ArrayList arrayList2 = null;
        if (relatedMerch != null && (edges = relatedMerch.getEdges()) != null) {
            ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(edges, 10));
            for (ArtistMerchQuery.Edge edge : edges) {
                arrayList3.add((edge == null || (node = edge.getNode()) == null) ? null : node.getMerch());
            }
            arrayList2 = arrayList3;
        }
        if (!(arrayList2 == null || arrayList2.isEmpty())) {
            Iterator it = CollectionsKt___CollectionsKt.filterNotNull(arrayList2).iterator();
            while (it.hasNext()) {
                BaseViewModel baseViewModelConvert = this.childConverter.convert(new ArtistMerch(data.getArtistEmbedded(), (Merch) it.next()));
                if (baseViewModelConvert != null) {
                    arrayList.add(baseViewModelConvert);
                }
            }
        }
        return arrayList;
    }
}
