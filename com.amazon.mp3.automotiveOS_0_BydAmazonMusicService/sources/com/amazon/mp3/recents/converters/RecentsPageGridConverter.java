package com.amazon.mp3.recents.converters;

import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.recents.data.RecentsPageData;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsPageGridConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J\"\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004H\u0016J\u0018\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/recents/converters/RecentsPageGridConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/mp3/recents/data/RecentsPageData;", "data", "", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "convert", "getChildrenModels", "Lcom/amazon/mp3/recents/converters/RecentsShovelerConverter;", "shovelerConverter", "Lcom/amazon/mp3/recents/converters/RecentsShovelerConverter;", "Lcom/amazon/mp3/recents/converters/RecentsSectionGridConverter;", "sectionGridConverter", "Lcom/amazon/mp3/recents/converters/RecentsSectionGridConverter;", "<init>", "(Lcom/amazon/mp3/recents/converters/RecentsShovelerConverter;Lcom/amazon/mp3/recents/converters/RecentsSectionGridConverter;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RecentsPageGridConverter implements BaseContainerModelConverter<RecentsPageData> {
    private final RecentsSectionGridConverter sectionGridConverter;
    private final RecentsShovelerConverter shovelerConverter;

    public RecentsPageGridConverter(RecentsShovelerConverter shovelerConverter, RecentsSectionGridConverter sectionGridConverter) {
        Intrinsics.checkNotNullParameter(shovelerConverter, "shovelerConverter");
        Intrinsics.checkNotNullParameter(sectionGridConverter, "sectionGridConverter");
        this.shovelerConverter = shovelerConverter;
        this.sectionGridConverter = sectionGridConverter;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(RecentsPageData recentsPageData, List list) {
        return convert2(recentsPageData, (List<? extends BaseViewModel>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public PageGridViewModel convert2(RecentsPageData data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (children == null || !(!children.isEmpty())) {
            return null;
        }
        return new PageGridViewModel(null, null, null, null, children, 2, 0, null, null, null, null, 1807, null);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(RecentsPageData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        if (!data.getRecentlyPlayed().isEmpty()) {
            BaseViewContainerModel<? extends BaseViewModel> baseViewContainerModelConvert2 = this.shovelerConverter.convert2((List<? extends RecentlyPlayedItem>) data.getRecentlyPlayed(), (List<? extends BaseViewModel>) this.shovelerConverter.getChildrenModels((List<? extends RecentlyPlayedItem>) data.getRecentlyPlayed()));
            if (baseViewContainerModelConvert2 != null) {
                arrayList.add(baseViewContainerModelConvert2);
            }
        }
        if (data.getRecentsPlaylistsData() != null && !data.getRecentsPlaylistsData().isEmpty()) {
            SectionGridViewModel sectionGridViewModelConvert2 = this.sectionGridConverter.convert2(data.getRecentsPlaylistsData(), (List<? extends BaseViewModel>) this.sectionGridConverter.getChildrenModels2(data.getRecentsPlaylistsData()));
            if (sectionGridViewModelConvert2 != null) {
                arrayList.add(sectionGridViewModelConvert2);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return arrayList;
    }
}
