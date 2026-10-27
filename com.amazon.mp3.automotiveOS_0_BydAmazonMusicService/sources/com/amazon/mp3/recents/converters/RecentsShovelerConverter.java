package com.amazon.mp3.recents.converters;

import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
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

/* JADX INFO: compiled from: RecentsShovelerConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B\u001d\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u0010\u0010\u0011J0\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0002H\u0016J\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\u000e\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/recents/converters/RecentsShovelerConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItem;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "childConverter", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "", "title", "Ljava/lang/String;", "<init>", "(Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class RecentsShovelerConverter implements BaseContainerModelConverter<List<? extends RecentlyPlayedItem>> {
    private SingleBaseModelConverter<RecentlyPlayedItem> childConverter;
    private String title;

    public RecentsShovelerConverter(SingleBaseModelConverter<RecentlyPlayedItem> childConverter, String title) {
        Intrinsics.checkNotNullParameter(childConverter, "childConverter");
        Intrinsics.checkNotNullParameter(title, "title");
        this.childConverter = childConverter;
        this.title = title;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(List<? extends RecentlyPlayedItem> list, List list2) {
        return convert2(list, (List<? extends BaseViewModel>) list2);
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(List<? extends RecentlyPlayedItem> data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (children == null || children.isEmpty()) {
            return null;
        }
        return new ShovelerModel(null, children, 5, null, this.title, null, null, null, null, false, null, null, null, false, null, 32233, null);
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(List<? extends RecentlyPlayedItem> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList(data.size());
        Iterator<? extends RecentlyPlayedItem> it = data.iterator();
        while (it.hasNext()) {
            BaseViewModel baseViewModelConvert = this.childConverter.convert(it.next());
            if (baseViewModelConvert != null) {
                arrayList.add(baseViewModelConvert);
            }
        }
        return arrayList;
    }
}
