package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.page.api.model.Image;
import com.amazon.music.page.api.model.ImageType;
import com.amazon.music.page.api.model.Page;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.BaseDetailHeaderModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3BaseDetailHeaderConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3BaseDetailHeaderConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Page;", "data", "Lcom/amazon/music/views/library/models/BaseDetailHeaderModel;", "convertBaseDetailHeader", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3BaseDetailHeaderConverter implements SingleBaseModelConverter<Page> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Page data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convertBaseDetailHeader(data);
    }

    private final BaseDetailHeaderModel convertBaseDetailHeader(Page data) {
        Object next;
        Image image;
        List<Image> images = data.getImages();
        if (images == null) {
            image = null;
        } else {
            Iterator<T> it = images.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (((Image) next).getType() == ImageType.DARK_BACKGROUND) {
                    break;
                }
            }
            image = (Image) next;
        }
        return new BaseDetailHeaderModel(data.getTitle(), null, null, image != null ? image.getSrc() : null, 6, null);
    }
}
