package com.amazon.mp3.brush.converters;

import com.amazon.mp3.detailpages.util.DiscHeaderIndexer;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.TextRowItemModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.views.TextRowItemView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushDiscHeaderRowItemConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushDiscHeaderRowItemConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/mp3/detailpages/util/DiscHeaderIndexer$Disc;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushDiscHeaderRowItemConverter implements SingleBaseModelConverter<DiscHeaderIndexer.Disc> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(DiscHeaderIndexer.Disc data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new TextRowItemModel(null, data.getTitle(), null, null, TextRowItemView.TextStyle.DISC_HEADER, false, 45, null);
    }
}
