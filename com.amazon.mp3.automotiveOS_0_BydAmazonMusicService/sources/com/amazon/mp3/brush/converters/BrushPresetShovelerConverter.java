package com.amazon.mp3.brush.converters;

import android.content.Context;
import com.amazon.music.views.library.converter.BaseContainerModelConverter;
import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Track;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushPresetShovelerConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0015B\u0011\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u0013\u0010\u0014J\u0016\u0010\u0007\u001a\u00020\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002J*\u0010\f\u001a\f\u0012\u0006\b\u0001\u0012\u00020\t\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\u00022\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u0003H\u0016J\u0018\u0010\r\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0016R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushPresetShovelerConverter;", "Lcom/amazon/music/views/library/converter/BaseContainerModelConverter;", "Lcom/amazon/musicensembleservice/brush/Block;", "", "Lcom/amazon/musicensembleservice/brush/Entity;", "entities", "", "areAllEntitiesTrackEntities", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "children", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "convert", "getChildrenModels", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushPresetShovelerConverter implements BaseContainerModelConverter<Block> {
    private static final String TAG = BrushPresetShovelerConverter.class.getSimpleName();
    private final Context context;

    /* JADX INFO: renamed from: convert, reason: avoid collision after fix types in other method */
    public BaseViewContainerModel<? extends BaseViewModel> convert2(Block data, List<? extends BaseViewModel> children) {
        Intrinsics.checkNotNullParameter(data, "data");
        return null;
    }

    public BrushPresetShovelerConverter(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    public /* bridge */ /* synthetic */ BaseViewContainerModel convert(Block block, List list) {
        return convert2(block, (List<? extends BaseViewModel>) list);
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x0142  */
    @Override // com.amazon.music.views.library.converter.BaseContainerModelConverter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.amazon.music.views.library.models.base.BaseViewModel> getChildrenModels(com.amazon.musicensembleservice.brush.Block r11) {
        /*
            Method dump skipped, instruction units count: 347
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.brush.converters.BrushPresetShovelerConverter.getChildrenModels(com.amazon.musicensembleservice.brush.Block):java.util.List");
    }

    private final boolean areAllEntitiesTrackEntities(List<? extends Entity> entities) {
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entities, 10));
        Iterator<T> it = entities.iterator();
        while (it.hasNext()) {
            arrayList.add(Boolean.valueOf(((Entity) it.next()) instanceof Track));
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            if (!((Boolean) it2.next()).booleanValue()) {
                return false;
            }
        }
        return true;
    }
}
