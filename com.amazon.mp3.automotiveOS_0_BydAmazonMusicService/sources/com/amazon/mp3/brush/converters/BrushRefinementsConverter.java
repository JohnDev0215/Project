package com.amazon.mp3.brush.converters;

import com.amazon.mp3.baseviews.model.configuration.AlbumModelConfiguration;
import com.amazon.mp3.baseviews.model.configuration.ModelConfiguration;
import com.amazon.mp3.catalog.fragment.viewmodel.AudioQualityRefinementEvaluator;
import com.amazon.mp3.catalog.fragment.viewmodel.RefinementsModelBuilder;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.Album;
import com.amazon.musicensembleservice.brush.Entity;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushRefinementsConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0002H\u0016R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushRefinementsConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/musicensembleservice/brush/Entity;", "Lcom/amazon/musicensembleservice/brush/Album;", "album", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "convertAlbum", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "modelConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "<init>", "(Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushRefinementsConverter implements SingleBaseModelConverter<Entity> {
    private final ModelConfiguration modelConfiguration;

    public BrushRefinementsConverter() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public BrushRefinementsConverter(ModelConfiguration modelConfiguration) {
        this.modelConfiguration = modelConfiguration;
    }

    public /* synthetic */ BrushRefinementsConverter(ModelConfiguration modelConfiguration, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : modelConfiguration);
    }

    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Entity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (data instanceof Album) {
            return convertAlbum((Album) data);
        }
        return null;
    }

    private final ExposedRefinementListModel convertAlbum(Album album) {
        Set set = null;
        byte b = 0;
        if (!new AlbumModelConfiguration(null, null, 3, null).shouldShowRefinementsOption(BrushConverterUtils.getAlbumMetadata$default(BrushConverterUtils.INSTANCE, album, this.modelConfiguration, null, 4, null))) {
            return null;
        }
        List<String> contentEncoding = album.getContentEncoding();
        return new RefinementsModelBuilder(new AudioQualityRefinementEvaluator(contentEncoding == null ? null : CollectionsKt___CollectionsKt.toSet(contentEncoding)), set, 2, b == true ? 1 : 0).build(RefinementsModelBuilder.RefinementType.ALBUM_DP);
    }
}
