package com.amazon.mp3.brush.converters;

import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.LinkNavigatorModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Button;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Image;
import com.amazon.musicensembleservice.brush.NavigationEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushNavigationButtonConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0007H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0002H\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushNavigationButtonConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/Button;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "getModelFromButton", "Lcom/amazon/musicensembleservice/brush/NavigationEntity;", "getModelFromNavigationEntity", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushNavigationButtonConverter implements SingleBaseModelConverter<Entity> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Button) {
            return getModelFromButton((Button) data);
        }
        if (data instanceof NavigationEntity) {
            return getModelFromNavigationEntity((NavigationEntity) data);
        }
        return null;
    }

    private final BaseViewModel getModelFromButton(Button data) {
        Image image;
        String blockRef = data.getBlockRef();
        String title = data.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "data.title");
        String target = data.getTarget();
        Intrinsics.checkNotNullExpressionValue(target, "data.target");
        List<Image> images = data.getImages();
        String url = null;
        if (images != null && (image = (Image) CollectionsKt___CollectionsKt.first((List) images)) != null) {
            url = image.getUrl();
        }
        return new LinkNavigatorModel(blockRef, title, target, url, false, null, null, null, false, false, null, 2032, null);
    }

    private final BaseViewModel getModelFromNavigationEntity(NavigationEntity data) {
        String blockRef = data.getBlockRef();
        String title = data.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "data.title");
        String target = data.getTarget();
        Intrinsics.checkNotNullExpressionValue(target, "data.target");
        return new LinkNavigatorModel(blockRef, title, target, null, false, null, null, null, false, false, null, 2032, null);
    }
}
