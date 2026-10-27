package com.amazon.mp3.brush.converters;

import android.content.Context;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* JADX INFO: compiled from: BrushRecentlyPlayedConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00112\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0001\u0011B\u001d\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\u000f\u0010\u0010J0\u0010\b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0002H\u0016J\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010\u000e¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushRecentlyPlayedConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "", "Lcom/amazon/mp3/recentlyplayed/RecentlyPlayedItem;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "list", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "Landroid/content/Context;", "context", "Landroid/content/Context;", "containerModel", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushRecentlyPlayedConverter implements BaseContainerModelConverter<List<? extends RecentlyPlayedItem>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final BaseViewContainerModel<BaseViewModel> containerModel;
    private final Context context;

    public BrushRecentlyPlayedConverter(Context context, BaseViewContainerModel<BaseViewModel> containerModel) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(containerModel, "containerModel");
        this.context = context;
        this.containerModel = containerModel;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(List<? extends RecentlyPlayedItem> list, List list2) {
        return convert2(list, (List<? extends BaseViewModel>) list2);
    }

    /* JADX INFO: compiled from: BrushRecentlyPlayedConverter.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushRecentlyPlayedConverter$Companion;", "", "()V", "containsRecentlyPlayedTag", "", "tags", "", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean containsRecentlyPlayedTag(List<String> tags) {
            if (tags != null) {
                Iterator<String> it = tags.iterator();
                while (it.hasNext()) {
                    if (StringsKt__StringsKt.contains$default((CharSequence) it.next(), (CharSequence) "recent", false, 2, (Object) null)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(List<? extends RecentlyPlayedItem> data, List<? extends BaseViewModel> list) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (list != null) {
            this.containerModel.setModels(list);
        }
        return this.containerModel;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public List<BaseViewModel> getChildrenModels(List<? extends RecentlyPlayedItem> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        BaseModelConverter recentlyPlayedConverter = BrushConverterFactory.INSTANCE.getRecentlyPlayedConverter(this.containerModel.getTileType(), this.context);
        SingleBaseModelConverter singleBaseModelConverter = recentlyPlayedConverter instanceof SingleBaseModelConverter ? (SingleBaseModelConverter) recentlyPlayedConverter : null;
        Iterator<? extends RecentlyPlayedItem> it = data.iterator();
        while (it.hasNext()) {
            BaseViewModel baseViewModelConvert = singleBaseModelConverter == null ? null : singleBaseModelConverter.convert(it.next());
            if (baseViewModelConvert != null) {
                arrayList.add(baseViewModelConvert);
            }
        }
        return arrayList;
    }
}
