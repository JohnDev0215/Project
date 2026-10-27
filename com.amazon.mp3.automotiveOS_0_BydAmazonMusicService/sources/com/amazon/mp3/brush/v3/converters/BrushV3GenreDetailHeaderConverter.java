package com.amazon.mp3.brush.v3.converters;

import android.net.Uri;
import com.amazon.mp3.brush.converters.BrushConverterUtils;
import com.amazon.mp3.playback.state.ContentPlaybackUtils;
import com.amazon.music.ContentAccessType;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.Genre;
import com.amazon.music.page.api.model.Header;
import com.amazon.music.page.api.model.HeaderContent;
import com.amazon.music.page.api.model.Image;
import com.amazon.music.page.api.model.ImageType;
import com.amazon.music.page.api.model.Page;
import com.amazon.music.page.api.model.Station;
import com.amazon.music.page.api.model.Widget;
import com.amazon.music.page.api.model.WidgetContent;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.GenreMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.models.GenreDetailHeaderModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.musicensembleservice.brush.ParentalControls;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3GenreDetailHeaderConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\u001e\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u001e\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3GenreDetailHeaderConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/page/api/model/Page;", "data", "", "contentType", "Lcom/amazon/music/views/library/models/GenreDetailHeaderModel;", "convertGenreHeader", "Lcom/amazon/music/page/api/model/Header;", "header", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "getStationMetadata", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "convertPage", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3GenreDetailHeaderConverter implements SingleBaseModelConverter<Page> {
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(Page data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convertPage$default(this, data, null, 2, null);
    }

    public static /* synthetic */ BaseViewModel convertPage$default(BrushV3GenreDetailHeaderConverter brushV3GenreDetailHeaderConverter, Page page, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return brushV3GenreDetailHeaderConverter.convertPage(page, str);
    }

    public BaseViewModel convertPage(Page data, String contentType) {
        Intrinsics.checkNotNullParameter(data, "data");
        return convertGenreHeader(data, contentType);
    }

    private final GenreDetailHeaderModel convertGenreHeader(Page data, String contentType) {
        Object next;
        Genre genre = data.getEntity().getGenre();
        List<Image> images = data.getImages();
        Intrinsics.checkNotNullExpressionValue(images, "data.images");
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
        Image image = (Image) next;
        BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
        List<String> contentTiers = genre.getContentTiers();
        Intrinsics.checkNotNullExpressionValue(contentTiers, "item.contentTiers");
        Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
        String blockRef = genre.getBlockRef();
        String primaryTitle = genre.getPrimaryTitle();
        String secondaryTitle = genre.getSecondaryTitle();
        String src = image == null ? null : image.getSrc();
        String id = genre.getId();
        String id2 = genre.getId();
        String stationKey = genre.getStationKey();
        String src2 = image != null ? image.getSrc() : null;
        String title = genre.getTitle();
        Uri genreUri = ContentPlaybackUtils.INSTANCE.getGenreUri();
        String blockRef2 = genre.getBlockRef();
        Intrinsics.checkNotNullExpressionValue(id2, "id");
        return new GenreDetailHeaderModel(blockRef, primaryTitle, secondaryTitle, src, id, new GenreMetadata(blockRef2, genreUri, null, stationKey, id2, title, src2, setConvertTiers, 4, null), getStationMetadata(data.getHeader(), contentType), 3, 0);
    }

    private final StationMetadata getStationMetadata(Header header, String contentType) {
        Object next;
        if (header == null) {
            return null;
        }
        HeaderContent content = header.getContent();
        List<Widget> widgets = content == null ? null : content.getWidgets();
        if (widgets != null) {
            Iterator<Widget> it = widgets.iterator();
            while (it.hasNext()) {
                WidgetContent content2 = it.next().getContent();
                List<Entity> entities = content2 == null ? null : content2.getEntities();
                if (entities != null) {
                    Iterator<Entity> it2 = entities.iterator();
                    while (it2.hasNext()) {
                        Station station = it2.next().getStation();
                        if (station != null) {
                            List<Image> images = station.getImages();
                            Intrinsics.checkNotNullExpressionValue(images, "station.images");
                            Iterator<T> it3 = images.iterator();
                            while (true) {
                                if (!it3.hasNext()) {
                                    next = null;
                                    break;
                                }
                                next = it3.next();
                                if (((Image) next).getType() == ImageType.ORIGINAL_ART) {
                                    break;
                                }
                            }
                            Image image = (Image) next;
                            BrushConverterUtils brushConverterUtils = BrushConverterUtils.INSTANCE;
                            List<String> contentTiers = station.getContentTiers();
                            Intrinsics.checkNotNullExpressionValue(contentTiers, "station.getContentTiers()");
                            Set<ContentAccessType> setConvertTiers = brushConverterUtils.convertTiers(contentTiers);
                            LinkedHashMap linkedHashMap = new LinkedHashMap();
                            if (contentType != null) {
                                Boolean hasExplicitLanguage = station.getParentalControls().getHasExplicitLanguage();
                                ParentalControls parentalControls = new ParentalControls();
                                parentalControls.setHasExplicitLanguage(hasExplicitLanguage);
                                linkedHashMap.put(contentType, parentalControls);
                            }
                            String blockRef = station.getBlockRef();
                            String stationKey = station.getStationKey();
                            String title = station.getTitle();
                            String callToActionTitle = station.getCallToActionTitle();
                            String playingStateTitle = station.getPlayingStateTitle();
                            String pausedStateTitle = station.getPausedStateTitle();
                            String src = image != null ? image.getSrc() : null;
                            Map<ContentAccessType, Boolean> mapConvertExplicitMap = brushConverterUtils.convertExplicitMap(linkedHashMap);
                            ContentPlaybackUtils contentPlaybackUtils = ContentPlaybackUtils.INSTANCE;
                            String stationKey2 = station.getStationKey();
                            Intrinsics.checkNotNullExpressionValue(stationKey2, "station.stationKey");
                            Uri stationUri = contentPlaybackUtils.getStationUri(stationKey2);
                            Intrinsics.checkNotNullExpressionValue(stationKey, "stationKey");
                            return new StationMetadata(blockRef, stationUri, null, stationKey, title, callToActionTitle, playingStateTitle, pausedStateTitle, src, mapConvertExplicitMap, setConvertTiers, null, null, null, 14340, null);
                        }
                    }
                }
            }
        }
        return null;
    }
}
