package com.amazon.music.alps.liveevent.converters;

import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.platform.AtomicMap;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: MerchListShovelerConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B%\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u0012J0\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0002H\u0016J\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u000f¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/MerchListShovelerConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "", "Lcom/amazon/music/platform/graphql/generated/fragment/Merch;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "childConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "", "title", "Ljava/lang/String;", "moreText", "<init>", "(Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;Ljava/lang/String;Ljava/lang/String;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class MerchListShovelerConverter implements BaseContainerModelConverter<List<? extends Merch>> {
    private final SingleBaseModelConverter<Merch> childConverter;
    private final String moreText;
    private final String title;

    public MerchListShovelerConverter(SingleBaseModelConverter<Merch> childConverter, String title, String moreText) {
        Intrinsics.checkNotNullParameter(childConverter, "childConverter");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(moreText, "moreText");
        this.childConverter = childConverter;
        this.title = title;
        this.moreText = moreText;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(List<? extends Merch> list, List list2) {
        return convert2((List<Merch>) list, (List<? extends BaseViewModel>) list2);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ List getChildrenModels(List<? extends Merch> list) {
        return getChildrenModels2((List<Merch>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(List<Merch> data, List<? extends BaseViewModel> children) {
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
            metricsProvider.sendMetricsEvent(new UIContentViewWrapper.Builder().withContainerType(ContainerType.SECTION.name()).withContentName(ContentName.EVENT_MERCH_SHOVELER.name()).withNumItemsAccessible(Long.valueOf(children.size())).withEventTime(Long.valueOf(System.currentTimeMillis())).build());
        }
        return new ShovelerModel(ContentName.EVENT_MERCH_SHOVELER.name(), children, 17, null, this.title, null, this.moreText, null, null, false, null, null, null, false, null, 32680, null);
    }

    /* JADX INFO: renamed from: getChildrenModels, reason: avoid collision after fix types in other method */
    public List<BaseViewModel> getChildrenModels2(List<Merch> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList(data.size());
        Iterator<Merch> it = data.iterator();
        while (it.hasNext()) {
            BaseViewModel baseViewModelConvert = this.childConverter.convert(it.next());
            if (baseViewModelConvert != null) {
                arrayList.add(baseViewModelConvert);
            }
        }
        return arrayList;
    }
}
