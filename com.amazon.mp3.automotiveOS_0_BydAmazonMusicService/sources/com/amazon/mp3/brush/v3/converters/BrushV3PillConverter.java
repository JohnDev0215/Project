package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.page.api.model.Button;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.PillCellModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.TargetType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3PillConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3PillConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Entity;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3PillConverter implements SingleBaseModelConverter<Entity> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Button button = data.getButton();
        if (button == null) {
            return null;
        }
        String blockRef = button.getBlockRef();
        String target = button.getTarget();
        String title = button.getTitle();
        BrushTargetTypeUtils brushTargetTypeUtils = BrushTargetTypeUtils.INSTANCE;
        String target2 = button.getTarget();
        Intrinsics.checkNotNullExpressionValue(target2, "it.target");
        TargetType targetType = brushTargetTypeUtils.getTargetType(target2);
        Intrinsics.checkNotNullExpressionValue(title, "title");
        Intrinsics.checkNotNullExpressionValue(target, "target");
        return new PillCellModel(blockRef, title, target, targetType, null, 16, null);
    }
}
