package com.amazon.music.alps.liveevent.converters;

import android.content.Context;
import com.amazon.music.alps.R$string;
import com.amazon.music.alps.liveevent.LiveEventStatus;
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

/* JADX INFO: compiled from: EventDescriptionSectionConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J*\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016J\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/EventDescriptionSectionConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "childConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class EventDescriptionSectionConverter implements BaseContainerModelConverter<LiveEvent> {
    private final SingleBaseModelConverter<LiveEvent> childConverter;
    private final Context context;

    /* JADX INFO: compiled from: EventDescriptionSectionConverter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LiveEventStatus.values().length];
            iArr[LiveEventStatus.AFTERGLOW.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public EventDescriptionSectionConverter(Context context, SingleBaseModelConverter<LiveEvent> childConverter) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(childConverter, "childConverter");
        this.context = context;
        this.childConverter = childConverter;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(LiveEvent liveEvent, List list) {
        return convert2(liveEvent, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(LiveEvent data, List<? extends BaseViewModel> children) {
        String string;
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
            metricsProvider.sendMetricsEvent(new UIContentViewWrapper.Builder().withContainerType(ContainerType.SECTION.name()).withContentName(ContentName.LIVE_EVENT_INFO.name()).withNumItemsAccessible(Long.valueOf(children.size())).withEventTime(Long.valueOf(System.currentTimeMillis())).build());
        }
        if (WhenMappings.$EnumSwitchMapping$0[LiveEventStatus.INSTANCE.from(data.getStatus()).ordinal()] == 1) {
            string = this.context.getString(R$string.thanks_for_watching_event, data.getName());
        } else {
            string = this.context.getString(R$string.event_information);
        }
        return new SectionGridViewModel(null, children, 2, null, string, null, null, null, null, null, false, null, false, null, null, null, null, 131049, null);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(LiveEvent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        BaseViewModel baseViewModelConvert = this.childConverter.convert(data);
        if (baseViewModelConvert != null) {
            arrayList.add(baseViewModelConvert);
        }
        return arrayList;
    }
}
