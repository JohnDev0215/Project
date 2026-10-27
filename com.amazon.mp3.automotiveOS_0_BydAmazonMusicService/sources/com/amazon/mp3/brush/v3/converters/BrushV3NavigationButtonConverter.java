package com.amazon.mp3.brush.v3.converters;

import com.amazon.mp3.brush.v3.extensions.ImageKt;
import com.amazon.music.page.api.model.Button;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.Image;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.LinkNavigatorModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3NavigationButtonConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3NavigationButtonConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Entity;", "Lcom/amazon/music/page/api/model/Button;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "getModelFromButton", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3NavigationButtonConverter implements SingleBaseModelConverter<Entity> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data.getButton() == null) {
            return null;
        }
        Button button = data.getButton();
        Intrinsics.checkNotNullExpressionValue(button, "data.button");
        return getModelFromButton(button);
    }

    private final BaseViewModel getModelFromButton(Button data) {
        Image originalArtOrDefault;
        String blockRef = data.getBlockRef();
        String title = data.getTitle();
        Intrinsics.checkNotNullExpressionValue(title, "data.title");
        String target = data.getTarget();
        Intrinsics.checkNotNullExpressionValue(target, "data.target");
        List<Image> images = data.getImages();
        String src = null;
        if (images != null && (originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images)) != null) {
            src = originalArtOrDefault.getSrc();
        }
        return new LinkNavigatorModel(blockRef, title, target, src, false, null, null, null, false, false, null, 2032, null);
    }
}
