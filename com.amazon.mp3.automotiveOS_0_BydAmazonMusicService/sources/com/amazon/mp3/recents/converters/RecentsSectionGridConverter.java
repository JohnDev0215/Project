package com.amazon.mp3.recents.converters;

import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.recents.data.RecentsPlaylistData;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecentsSectionGridConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u001d\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\r\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J(\u0010\u000b\u001a\u0004\u0018\u00010\n2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0002H\u0016J\u001e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/recents/converters/RecentsSectionGridConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "", "Lcom/amazon/mp3/recents/data/RecentsPlaylistData;", "item", "", "shouldHideSongsHistory", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/SectionGridViewModel;", "convert", "getChildrenModels", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "childConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "", "title", "Ljava/lang/String;", "<init>", "(Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RecentsSectionGridConverter implements BaseContainerModelConverter<List<? extends RecentsPlaylistData>> {
    private SingleBaseModelConverter<RecentsPlaylistData> childConverter;
    private String title;

    public RecentsSectionGridConverter(SingleBaseModelConverter<RecentsPlaylistData> childConverter, String title) {
        Intrinsics.checkNotNullParameter(childConverter, "childConverter");
        Intrinsics.checkNotNullParameter(title, "title");
        this.childConverter = childConverter;
        this.title = title;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(List<? extends RecentsPlaylistData> list, List list2) {
        return convert2((List<RecentsPlaylistData>) list, (List<? extends BaseViewModel>) list2);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ List getChildrenModels(List<? extends RecentsPlaylistData> list) {
        return getChildrenModels2((List<RecentsPlaylistData>) list);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public SectionGridViewModel convert2(List<RecentsPlaylistData> data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (children == null || !(!children.isEmpty())) {
            return null;
        }
        return new SectionGridViewModel(null, children, 2, null, this.title, null, null, null, null, null, false, null, false, null, null, null, null, 131049, null);
    }

    /* JADX INFO: renamed from: getChildrenModels, reason: avoid collision after fix types in other method */
    public List<BaseViewModel> getChildrenModels2(List<RecentsPlaylistData> data) {
        BaseViewModel baseViewModelConvert;
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        for (RecentsPlaylistData recentsPlaylistData : data) {
            if (!shouldHideSongsHistory(recentsPlaylistData) && (baseViewModelConvert = this.childConverter.convert(recentsPlaylistData)) != null) {
                arrayList.add(baseViewModelConvert);
            }
        }
        return arrayList;
    }

    private final boolean shouldHideSongsHistory(RecentsPlaylistData item) {
        return Feature.hide_song_history_library_page.isEnabled() && item.getPlaylistId() == 3;
    }
}
