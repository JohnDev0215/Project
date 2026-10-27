package com.amazon.music.alps.liveevent.converters;

import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LineupGroupsPageGridConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ*\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/LineupGroupsPageGridConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "Lcom/amazon/music/alps/liveevent/converters/LineupGroupsSectionConverter;", "lineupGroupsSectionConverter", "Lcom/amazon/music/alps/liveevent/converters/LineupGroupsSectionConverter;", "<init>", "(Lcom/amazon/music/alps/liveevent/converters/LineupGroupsSectionConverter;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class LineupGroupsPageGridConverter implements BaseContainerModelConverter<LiveEvent> {
    private final LineupGroupsSectionConverter lineupGroupsSectionConverter;

    public LineupGroupsPageGridConverter(LineupGroupsSectionConverter lineupGroupsSectionConverter) {
        Intrinsics.checkNotNullParameter(lineupGroupsSectionConverter, "lineupGroupsSectionConverter");
        this.lineupGroupsSectionConverter = lineupGroupsSectionConverter;
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
        return new PageGridViewModel(null, null, null, null, children, 2, 0, null, null, null, null, 1807, null);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(LiveEvent data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert2 = this.lineupGroupsSectionConverter.convert2(data, (List<? extends BaseViewModel>) this.lineupGroupsSectionConverter.getChildrenModels(data));
        if (baseViewContainerModelConvert2 != null) {
            arrayList.add(baseViewContainerModelConvert2);
        }
        return arrayList;
    }
}
