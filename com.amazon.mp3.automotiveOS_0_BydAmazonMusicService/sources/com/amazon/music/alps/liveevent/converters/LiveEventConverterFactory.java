package com.amazon.music.alps.liveevent.converters;

import android.content.Context;
import com.amazon.music.alps.R$string;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LiveEventConverterFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000203X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/amazon/music/alps/liveevent/converters/LiveEventConverterFactory;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "albumShovelerConverter", "Lcom/amazon/music/alps/liveevent/converters/AlbumShovelerConverter;", "albumVerticalTitleConverter", "Lcom/amazon/music/alps/liveevent/converters/AlbumVerticalTitleConverter;", "artistMerchShovelerConverter", "Lcom/amazon/music/alps/liveevent/converters/ArtistMerchShovelerConverter;", "artistMerchVerticalTileConverter", "Lcom/amazon/music/alps/liveevent/converters/ArtistMerchVerticalTileConverter;", "eventDescriptionConverter", "Lcom/amazon/music/alps/liveevent/converters/EventDescriptionConverter;", "eventDescriptionSectionConverter", "Lcom/amazon/music/alps/liveevent/converters/EventDescriptionSectionConverter;", "eventHeaderConverter", "Lcom/amazon/music/alps/liveevent/converters/EventHeaderConverter;", "lineupConverter", "Lcom/amazon/music/alps/liveevent/converters/LineupConverter;", "lineupGroupsFullScreenConverter", "Lcom/amazon/music/alps/liveevent/converters/LineupGroupsSectionConverter;", "lineupGroupsSectionConverter", "lineupsPageGridConverter", "Lcom/amazon/music/alps/liveevent/converters/LineupGroupsPageGridConverter;", "getLineupsPageGridConverter", "()Lcom/amazon/music/alps/liveevent/converters/LineupGroupsPageGridConverter;", "liveEventDetailPageGridConverter", "Lcom/amazon/music/alps/liveevent/converters/LiveEventDetailPageGridConverter;", "getLiveEventDetailPageGridConverter", "()Lcom/amazon/music/alps/liveevent/converters/LiveEventDetailPageGridConverter;", "merchListShovelerConverter", "Lcom/amazon/music/alps/liveevent/converters/MerchListShovelerConverter;", "merchVerticalTileConverter", "Lcom/amazon/music/alps/liveevent/converters/MerchVerticalTileConverter;", "newEventHeaderConverter", "Lcom/amazon/music/alps/liveevent/converters/NewEventHeaderConverter;", "newWaysToWatchSectionConverter", "Lcom/amazon/music/alps/liveevent/converters/NewWaysToWatchSectionConverter;", "playlistShovelerConverter", "Lcom/amazon/music/alps/liveevent/converters/PlaylistShovelerConverter;", "playlistVerticalTileConverter", "Lcom/amazon/music/alps/liveevent/converters/PlaylistVerticalTileConverter;", "sponsoredImageConverter", "Lcom/amazon/music/alps/liveevent/converters/SponsoredImageConverter;", "verticalIconLinkItemConverter", "Lcom/amazon/music/alps/liveevent/converters/VerticalIconLinkItemConverter;", "wayToWatchConverter", "Lcom/amazon/music/alps/liveevent/converters/WayToWatchConverter;", "waysToWatchSectionConverter", "Lcom/amazon/music/alps/liveevent/converters/WaysToWatchSectionConverter;", "DMMALPS_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LiveEventConverterFactory {
    private final AlbumShovelerConverter albumShovelerConverter;
    private final AlbumVerticalTitleConverter albumVerticalTitleConverter;
    private final ArtistMerchShovelerConverter artistMerchShovelerConverter;
    private final ArtistMerchVerticalTileConverter artistMerchVerticalTileConverter;
    private final EventDescriptionConverter eventDescriptionConverter;
    private final EventDescriptionSectionConverter eventDescriptionSectionConverter;
    private final EventHeaderConverter eventHeaderConverter;
    private final LineupConverter lineupConverter;
    private final LineupGroupsSectionConverter lineupGroupsFullScreenConverter;
    private final LineupGroupsSectionConverter lineupGroupsSectionConverter;
    private final LineupGroupsPageGridConverter lineupsPageGridConverter;
    private final LiveEventDetailPageGridConverter liveEventDetailPageGridConverter;
    private final MerchListShovelerConverter merchListShovelerConverter;
    private final MerchVerticalTileConverter merchVerticalTileConverter;
    private final NewEventHeaderConverter newEventHeaderConverter;
    private final NewWaysToWatchSectionConverter newWaysToWatchSectionConverter;
    private final PlaylistShovelerConverter playlistShovelerConverter;
    private final PlaylistVerticalTileConverter playlistVerticalTileConverter;
    private final SponsoredImageConverter sponsoredImageConverter;
    private final VerticalIconLinkItemConverter verticalIconLinkItemConverter;
    private final WayToWatchConverter wayToWatchConverter;
    private final WaysToWatchSectionConverter waysToWatchSectionConverter;

    public LiveEventConverterFactory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        EventHeaderConverter eventHeaderConverter = new EventHeaderConverter(context);
        this.eventHeaderConverter = eventHeaderConverter;
        NewEventHeaderConverter newEventHeaderConverter = new NewEventHeaderConverter(context);
        this.newEventHeaderConverter = newEventHeaderConverter;
        WayToWatchConverter wayToWatchConverter = new WayToWatchConverter(context);
        this.wayToWatchConverter = wayToWatchConverter;
        VerticalIconLinkItemConverter verticalIconLinkItemConverter = new VerticalIconLinkItemConverter(context);
        this.verticalIconLinkItemConverter = verticalIconLinkItemConverter;
        EventDescriptionConverter eventDescriptionConverter = new EventDescriptionConverter(context);
        this.eventDescriptionConverter = eventDescriptionConverter;
        MerchVerticalTileConverter merchVerticalTileConverter = new MerchVerticalTileConverter(context);
        this.merchVerticalTileConverter = merchVerticalTileConverter;
        ArtistMerchVerticalTileConverter artistMerchVerticalTileConverter = new ArtistMerchVerticalTileConverter(context);
        this.artistMerchVerticalTileConverter = artistMerchVerticalTileConverter;
        PlaylistVerticalTileConverter playlistVerticalTileConverter = new PlaylistVerticalTileConverter(context);
        this.playlistVerticalTileConverter = playlistVerticalTileConverter;
        AlbumVerticalTitleConverter albumVerticalTitleConverter = new AlbumVerticalTitleConverter(context);
        this.albumVerticalTitleConverter = albumVerticalTitleConverter;
        LineupConverter lineupConverter = new LineupConverter(context);
        this.lineupConverter = lineupConverter;
        WaysToWatchSectionConverter waysToWatchSectionConverter = new WaysToWatchSectionConverter(context, wayToWatchConverter);
        this.waysToWatchSectionConverter = waysToWatchSectionConverter;
        NewWaysToWatchSectionConverter newWaysToWatchSectionConverter = new NewWaysToWatchSectionConverter(context, verticalIconLinkItemConverter);
        this.newWaysToWatchSectionConverter = newWaysToWatchSectionConverter;
        EventDescriptionSectionConverter eventDescriptionSectionConverter = new EventDescriptionSectionConverter(context, eventDescriptionConverter);
        this.eventDescriptionSectionConverter = eventDescriptionSectionConverter;
        int i = R$string.lineup;
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.lineup)");
        int i2 = R$string.alps_see_more;
        String string2 = context.getString(i2);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.alps_see_more)");
        LineupGroupsSectionConverter lineupGroupsSectionConverter = new LineupGroupsSectionConverter(lineupConverter, string, string2, true);
        this.lineupGroupsSectionConverter = lineupGroupsSectionConverter;
        String string3 = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.lineup)");
        LineupGroupsSectionConverter lineupGroupsSectionConverter2 = new LineupGroupsSectionConverter(lineupConverter, string3, "", false);
        this.lineupGroupsFullScreenConverter = lineupGroupsSectionConverter2;
        String string4 = context.getString(R$string.event_merch);
        Intrinsics.checkNotNullExpressionValue(string4, "context.getString(R.string.event_merch)");
        String string5 = context.getString(i2);
        Intrinsics.checkNotNullExpressionValue(string5, "context.getString(R.string.alps_see_more)");
        MerchListShovelerConverter merchListShovelerConverter = new MerchListShovelerConverter(merchVerticalTileConverter, string4, string5);
        this.merchListShovelerConverter = merchListShovelerConverter;
        String string6 = context.getString(R$string.artist_merch);
        Intrinsics.checkNotNullExpressionValue(string6, "context.getString(R.string.artist_merch)");
        String string7 = context.getString(i2);
        Intrinsics.checkNotNullExpressionValue(string7, "context.getString(R.string.alps_see_more)");
        ArtistMerchShovelerConverter artistMerchShovelerConverter = new ArtistMerchShovelerConverter(artistMerchVerticalTileConverter, string6, string7);
        this.artistMerchShovelerConverter = artistMerchShovelerConverter;
        String string8 = context.getString(R$string.festival_playlist);
        Intrinsics.checkNotNullExpressionValue(string8, "context.getString(R.string.festival_playlist)");
        PlaylistShovelerConverter playlistShovelerConverter = new PlaylistShovelerConverter(playlistVerticalTileConverter, string8);
        this.playlistShovelerConverter = playlistShovelerConverter;
        String string9 = context.getString(R$string.festival_album);
        Intrinsics.checkNotNullExpressionValue(string9, "context.getString(R.string.festival_album)");
        AlbumShovelerConverter albumShovelerConverter = new AlbumShovelerConverter(albumVerticalTitleConverter, string9);
        this.albumShovelerConverter = albumShovelerConverter;
        String string10 = context.getString(R$string.sponsored_by);
        Intrinsics.checkNotNullExpressionValue(string10, "context.getString(R.string.sponsored_by)");
        SponsoredImageConverter sponsoredImageConverter = new SponsoredImageConverter(string10);
        this.sponsoredImageConverter = sponsoredImageConverter;
        this.liveEventDetailPageGridConverter = new LiveEventDetailPageGridConverter(eventHeaderConverter, newEventHeaderConverter, waysToWatchSectionConverter, newWaysToWatchSectionConverter, eventDescriptionSectionConverter, lineupGroupsSectionConverter, albumShovelerConverter, merchListShovelerConverter, artistMerchShovelerConverter, playlistShovelerConverter, sponsoredImageConverter);
        this.lineupsPageGridConverter = new LineupGroupsPageGridConverter(lineupGroupsSectionConverter2);
    }

    public final LiveEventDetailPageGridConverter getLiveEventDetailPageGridConverter() {
        return this.liveEventDetailPageGridConverter;
    }

    public final LineupGroupsPageGridConverter getLineupsPageGridConverter() {
        return this.lineupsPageGridConverter;
    }
}
