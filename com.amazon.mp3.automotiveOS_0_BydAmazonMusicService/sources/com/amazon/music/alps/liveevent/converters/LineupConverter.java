package com.amazon.music.alps.liveevent.converters;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.VisibleForTesting;
import com.amazon.music.alps.R$string;
import com.amazon.music.alps.config.URLConfig;
import com.amazon.music.alps.liveevent.LineupGroupNameType;
import com.amazon.music.alps.liveevent.enums.ImageType;
import com.amazon.music.alps.liveevent.ktx.StringKt;
import com.amazon.music.alps.liveevent.viewmodel.LineupViewModel;
import com.amazon.music.alps.liveevent.viewmodel.SelectItemViewModel;
import com.amazon.music.platform.graphql.generated.fragment.ArtistEmbedded;
import com.amazon.music.platform.graphql.generated.fragment.Image;
import com.amazon.music.platform.graphql.generated.fragment.Lineup;
import com.amazon.music.platform.graphql.generated.fragment.LiveEvent;
import com.amazon.music.views.library.converter.SingleBaseModelConverter;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.models.ArtworkFrameModel;
import com.amazon.music.views.library.models.HorizontalTileModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.joda.time.DateTime;

/* JADX INFO: compiled from: LineupConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J(\u0010\f\u001a\u0004\u0018\u00010\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\nJ\b\u0010\r\u001a\u00020\bH\u0007J\u0018\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0007R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/LineupConverter;", "Lcom/amazon/music/views/library/converter/SingleBaseModelConverter;", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent;", "data", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "convert", "", "Lcom/amazon/music/platform/graphql/generated/fragment/LiveEvent$LineupGroup;", "", "lineupGroupNameType", "", "index", "getLineupGroupDisplay", "getBaseUrl", "baseUrl", "asin", "Landroid/net/Uri;", "getUri", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final class LineupConverter implements SingleBaseModelConverter<LiveEvent> {
    private final Context context;

    public LineupConverter(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v6, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r15v12, types: [com.amazon.music.platform.graphql.generated.fragment.ArtistEmbedded$Image] */
    /* JADX WARN: Type inference failed for: r15v13 */
    /* JADX WARN: Type inference failed for: r15v16 */
    @Override // com.amazon.music.views.library.converter.SingleBaseModelConverter
    public BaseViewModel convert(LiveEvent data) {
        Lineup lineup;
        Lineup.Artist1 artist;
        ArtistEmbedded artistEmbedded;
        Lineup.Artist1 artist2;
        ArtistEmbedded artistEmbedded2;
        List<ArtistEmbedded.Image> images;
        ArtistEmbedded.Image imagePrevious;
        Image image;
        ?? r15;
        Lineup.Artist1 artist3;
        ArtistEmbedded artistEmbedded3;
        String startDate;
        DateTime dateTimeIso8601DateTime;
        Image image2;
        LineupConverter lineupConverter = this;
        Intrinsics.checkNotNullParameter(data, "data");
        ArrayList arrayList = new ArrayList();
        List<LiveEvent.LineupGroup> lineupGroups = data.getLineupGroups();
        String str = null;
        if (lineupGroups == null || lineupGroups.isEmpty()) {
            return null;
        }
        List<LiveEvent.LineupGroup> lineupGroups2 = data.getLineupGroups();
        List<LiveEvent.LineupGroup> listFilterNotNull = lineupGroups2 == null ? null : CollectionsKt___CollectionsKt.filterNotNull(lineupGroups2);
        if (listFilterNotNull == null) {
            return null;
        }
        int size = listFilterNotNull.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            arrayList.add(new SelectItemViewModel(null, i, lineupConverter.getLineupGroupDisplay(listFilterNotNull, data.getLineupGroupNameType(), i), Boolean.valueOf(i == 0), 1, null));
            i = i2;
        }
        String baseUrl = getBaseUrl();
        ArrayList arrayList2 = new ArrayList();
        int size2 = listFilterNotNull.size();
        int i3 = 0;
        while (i3 < size2) {
            int i4 = i3 + 1;
            ArrayList arrayList3 = new ArrayList();
            List<LiveEvent.Lineup> lineups = listFilterNotNull.get(i3).getLineups();
            if (!(lineups == null || lineups.isEmpty())) {
                int size3 = lineups.size();
                int i5 = 0;
                while (i5 < size3) {
                    int i6 = i5 + 1;
                    LiveEvent.Lineup lineup2 = lineups.get(i5);
                    ?? artists = (lineup2 == null || (lineup = lineup2.getLineup()) == null) ? str : lineup.getArtists();
                    if (artists != 0) {
                        for (Lineup.Artist artist4 : artists) {
                            String id = (artist4 == null || (artist = artist4.getArtist()) == null || (artistEmbedded = artist.getArtistEmbedded()) == null) ? str : artistEmbedded.getId();
                            Intrinsics.checkNotNull(id);
                            ArtistMetadata artistMetadata = new ArtistMetadata(null, lineupConverter.getUri(baseUrl, id), null, null, id, null, null, null, null, null, null, false, null, null, false, null, null, false, false, null, 1048557, null);
                            if (artist4 == null || (artist2 = artist4.getArtist()) == null || (artistEmbedded2 = artist2.getArtistEmbedded()) == null || (images = artistEmbedded2.getImages()) == null) {
                                r15 = str;
                            } else {
                                ListIterator<ArtistEmbedded.Image> listIterator = images.listIterator(images.size());
                                while (true) {
                                    if (!listIterator.hasPrevious()) {
                                        imagePrevious = null;
                                        break;
                                    }
                                    imagePrevious = listIterator.previous();
                                    ArtistEmbedded.Image image3 = imagePrevious;
                                    if (ImageType.INSTANCE.from((image3 != null && (image = image3.getImage()) != null) ? image.getImageType() : null) == ImageType.PROFILE) {
                                        break;
                                    }
                                }
                                r15 = imagePrevious;
                            }
                            arrayList3.add(new HorizontalTileModel(null, null, (artist4 == null || (artist3 = artist4.getArtist()) == null || (artistEmbedded3 = artist3.getArtistEmbedded()) == null) ? null : artistEmbedded3.getName(), (artist4 == null || (startDate = artist4.getStartDate()) == null || (dateTimeIso8601DateTime = StringKt.iso8601DateTime(startDate)) == null) ? null : dateTimeIso8601DateTime.toString("h:mma zzz"), null, new ArtworkFrameModel(null, (r15 == 0 || (image2 = r15.getImage()) == null) ? null : image2.getUrl(), 4, null, null, null, null, null, null, null, null, null, 4089, null), null, false, null, null, false, false, null, true, false, null, 4, artistMetadata, null, false, false, null, false, null, null, null, false, false, false, null, 1073536979, null));
                            lineupConverter = this;
                            str = null;
                        }
                    }
                    lineupConverter = this;
                    i5 = i6;
                    str = null;
                }
            }
            arrayList2.add(arrayList3);
            lineupConverter = this;
            i3 = i4;
            str = null;
        }
        return new LineupViewModel(null, 0, arrayList, arrayList2, 1, null);
    }

    public final String getLineupGroupDisplay(List<LiveEvent.LineupGroup> data, String lineupGroupNameType, int index) {
        List<LiveEvent.Lineup> lineups;
        LiveEvent.Lineup lineup;
        Lineup lineup2;
        String scheduledStartDate;
        DateTime dateTimeIso8601DateTime;
        Intrinsics.checkNotNullParameter(data, "data");
        if (!(lineupGroupNameType == null || lineupGroupNameType.length() == 0) && LineupGroupNameType.INSTANCE.from(lineupGroupNameType) == LineupGroupNameType.DAY_NUMBER) {
            return this.context.getString(R$string.lineup_day_number, String.valueOf(index + 1));
        }
        LiveEvent.LineupGroup lineupGroup = data.get(index);
        if (lineupGroup == null || (lineups = lineupGroup.getLineups()) == null || (lineup = (LiveEvent.Lineup) CollectionsKt___CollectionsKt.getOrNull(lineups, 0)) == null || (lineup2 = lineup.getLineup()) == null || (scheduledStartDate = lineup2.getScheduledStartDate()) == null || (dateTimeIso8601DateTime = StringKt.iso8601DateTime(scheduledStartDate)) == null) {
            return null;
        }
        return dateTimeIso8601DateTime.toString("MMM d");
    }

    @VisibleForTesting
    public final String getBaseUrl() {
        return URLConfig.SHARE_BASE_URL.getValue();
    }

    @VisibleForTesting
    public final Uri getUri(String baseUrl, String asin) {
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        Intrinsics.checkNotNullParameter(asin, "asin");
        Uri uri = Uri.parse(baseUrl + "artists/" + asin);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(\"${baseUrl}artists/$asin\")");
        return uri;
    }
}
