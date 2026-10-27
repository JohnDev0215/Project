package com.amazon.mp3.brush.v3.converters;

import com.amazon.mp3.brush.v3.extensions.ImageKt;
import com.amazon.music.page.api.model.Button;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.Genre;
import com.amazon.music.page.api.model.Image;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ThumbnailCellModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3ThumbnailConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0007H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3ThumbnailConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Entity;", "Lcom/amazon/music/page/api/model/Genre;", "item", "Lcom/amazon/music/views/library/models/ThumbnailCellModel;", "convertGenre", "Lcom/amazon/music/page/api/model/Button;", "convertButton", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3ThumbnailConverter implements SingleBaseModelConverter<Entity> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data.getGenre() != null) {
            Genre genre = data.getGenre();
            Intrinsics.checkNotNullExpressionValue(genre, "data.genre");
            return convertGenre(genre);
        }
        if (data.getButton() == null) {
            return null;
        }
        Button button = data.getButton();
        Intrinsics.checkNotNullExpressionValue(button, "data.button");
        return convertButton(button);
    }

    private final ThumbnailCellModel convertGenre(Genre item) {
        Image originalArtOrDefault;
        String blockRef = item.getBlockRef();
        String target = item.getTarget();
        String title = item.getTitle();
        List<Image> images = item.getImages();
        String src = null;
        if (images != null && (originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images)) != null) {
            src = originalArtOrDefault.getSrc();
        }
        return new ThumbnailCellModel(blockRef, title, target, src);
    }

    private final ThumbnailCellModel convertButton(Button item) {
        Image originalArtOrDefault;
        String blockRef = item.getBlockRef();
        String target = item.getTarget();
        String title = item.getTitle();
        List<Image> images = item.getImages();
        String src = null;
        if (images != null && (originalArtOrDefault = ImageKt.getOriginalArtOrDefault(images)) != null) {
            src = originalArtOrDefault.getSrc();
        }
        return new ThumbnailCellModel(blockRef, title, target, src);
    }
}
