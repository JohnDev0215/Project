package com.amazon.music.skyfire.ui.providers.impl;

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.view.View;
import com.amazon.music.skyfire.ui.Constants;
import com.amazon.music.skyfire.ui.ktx.AnyKt;
import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.ElementBindingProvider;
import com.amazon.music.skyfire.ui.providers.ExternalActionProvider;
import com.amazon.music.skyfire.ui.providers.LibraryProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.NotificationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.SharingProvider;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.amazon.music.views.library.views.HorizontalTileView;
import com.amazon.music.views.library.views.LibraryAddableView;
import com.google.android.gms.cast.MediaTrack;
import java.util.Date;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: DefaultExternalActionProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 '2\u00020\u0001:\u0001'B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ$\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0 H\u0014J6\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020!2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0 2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/impl/DefaultExternalActionProvider;", "Lcom/amazon/music/skyfire/ui/providers/ExternalActionProvider;", "libraryProvider", "Lcom/amazon/music/skyfire/ui/providers/LibraryProvider;", "navigationProvider", "Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "notificationProvider", "Lcom/amazon/music/skyfire/ui/providers/NotificationProvider;", "playbackProvider", "Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "sharingProvider", "Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "downloadProvider", "Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "(Lcom/amazon/music/skyfire/ui/providers/LibraryProvider;Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;Lcom/amazon/music/skyfire/ui/providers/NotificationProvider;Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;Lcom/amazon/music/skyfire/ui/providers/SharingProvider;Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;)V", "getDownloadProvider", "()Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "getLibraryProvider", "()Lcom/amazon/music/skyfire/ui/providers/LibraryProvider;", "getNavigationProvider", "()Lcom/amazon/music/skyfire/ui/providers/NavigationProvider;", "getNotificationProvider", "()Lcom/amazon/music/skyfire/ui/providers/NotificationProvider;", "getPlaybackProvider", "()Lcom/amazon/music/skyfire/ui/providers/PlaybackProvider;", "getSharingProvider", "()Lcom/amazon/music/skyfire/ui/providers/SharingProvider;", "createCalendarEvent", "", "context", "Landroid/content/Context;", "args", "", "", "handleAction", "", "name", "view", "Landroid/view/View;", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DefaultExternalActionProvider implements ExternalActionProvider {
    private static final Logger logger = LoggerFactory.getLogger(ElementBindingProvider.class.getSimpleName());
    private final DownloadProvider downloadProvider;
    private final LibraryProvider libraryProvider;
    private final NavigationProvider navigationProvider;
    private final NotificationProvider notificationProvider;
    private final PlaybackProvider playbackProvider;
    private final SharingProvider sharingProvider;

    public DefaultExternalActionProvider(LibraryProvider libraryProvider, NavigationProvider navigationProvider, NotificationProvider notificationProvider, PlaybackProvider playbackProvider, SharingProvider sharingProvider, DownloadProvider downloadProvider) {
        Intrinsics.checkNotNullParameter(libraryProvider, "libraryProvider");
        Intrinsics.checkNotNullParameter(navigationProvider, "navigationProvider");
        Intrinsics.checkNotNullParameter(notificationProvider, "notificationProvider");
        Intrinsics.checkNotNullParameter(playbackProvider, "playbackProvider");
        Intrinsics.checkNotNullParameter(sharingProvider, "sharingProvider");
        Intrinsics.checkNotNullParameter(downloadProvider, "downloadProvider");
        this.libraryProvider = libraryProvider;
        this.navigationProvider = navigationProvider;
        this.notificationProvider = notificationProvider;
        this.playbackProvider = playbackProvider;
        this.sharingProvider = sharingProvider;
        this.downloadProvider = downloadProvider;
    }

    public final LibraryProvider getLibraryProvider() {
        return this.libraryProvider;
    }

    public final NavigationProvider getNavigationProvider() {
        return this.navigationProvider;
    }

    public final NotificationProvider getNotificationProvider() {
        return this.notificationProvider;
    }

    public final PlaybackProvider getPlaybackProvider() {
        return this.playbackProvider;
    }

    public final SharingProvider getSharingProvider() {
        return this.sharingProvider;
    }

    public final DownloadProvider getDownloadProvider() {
        return this.downloadProvider;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.amazon.music.skyfire.ui.providers.ExternalActionProvider
    public boolean handleAction(Context context, String name, Map<String, String> args, View view) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(args, "args");
        switch (name.hashCode()) {
            case -2127894679:
                if (!name.equals(Methods.openPlaylistDetail)) {
                    return false;
                }
                NavigationProvider navigationProvider = this.navigationProvider;
                String str = args.get("ASIN");
                if (str == null) {
                    throw new IllegalStateException("Missing Playlist ASIN".toString());
                }
                navigationProvider.openPlaylistDetail(str, args.get("TRACK_ASIN"));
                return true;
            case -1948402466:
                if (!name.equals(Methods.openPopularContent)) {
                    return false;
                }
                this.navigationProvider.openChartsPage(args.get(Contexts.OpenPopularContent.node), args.get(Contexts.OpenPopularContent.widgetId));
                return true;
            case -1747592519:
                if (!name.equals(Methods.openExternalLink)) {
                    return false;
                }
                String str2 = args.get("URL");
                if (str2 == null) {
                    return true;
                }
                try {
                    context.startActivity(new Intent("android.intent.action.VIEW", AnyKt.toUri(str2)));
                    return true;
                } catch (Exception unused) {
                    logger.error("Unable to open external link {}", str2);
                    return true;
                }
            case -1540952042:
                if (!name.equals(Methods.openAlbumDetail)) {
                    return false;
                }
                NavigationProvider navigationProvider2 = this.navigationProvider;
                String str3 = args.get("ASIN");
                if (str3 == null) {
                    throw new IllegalStateException("Missing Album ASIN".toString());
                }
                navigationProvider2.openAlbumDetail(str3, args.get("TRACK_ASIN"));
                return true;
            case -1472565484:
                if (!name.equals(Methods.openArtistDetail)) {
                    return false;
                }
                NavigationProvider navigationProvider3 = this.navigationProvider;
                String str4 = args.get("ASIN");
                if (str4 == null) {
                    throw new IllegalStateException("Missing Artist ASIN".toString());
                }
                navigationProvider3.openArtistDetail(str4);
                return true;
            case -1382453013:
                if (!name.equals(Methods.notification)) {
                    return false;
                }
                this.notificationProvider.showNotification(context, args.get("MESSAGE"), args.get(Contexts.Notification.style));
                return true;
            case -1314960528:
                if (!name.equals(Methods.openLibraryArtistDetail)) {
                    return false;
                }
                NavigationProvider navigationProvider4 = this.navigationProvider;
                String str5 = args.get("FILTER");
                if (str5 == null) {
                    throw new IllegalStateException("Missing Refinement filter".toString());
                }
                String str6 = args.get("ASIN");
                if (str6 == null) {
                    throw new IllegalStateException("Missing Artist ASIN".toString());
                }
                String str7 = args.get(Contexts.LibraryArtistDetail.name);
                if (str7 == null) {
                    throw new IllegalStateException("Missing Artist name".toString());
                }
                navigationProvider4.openLibraryArtistDetail(context, str5, str6, str7, args.get(Contexts.LibraryArtistDetail.followerCount), args.get(Contexts.LibraryArtistDetail.artistId));
                return true;
            case -1151241837:
                if (!name.equals(Methods.openCalendar)) {
                    return false;
                }
                createCalendarEvent(context, args);
                return true;
            case -790994090:
                if (!name.equals(Methods.setNeutralTrack)) {
                    return false;
                }
                if (!(view instanceof HorizontalTileView)) {
                    return true;
                }
                LibraryProvider libraryProvider = this.libraryProvider;
                HorizontalTileView horizontalTileView = (HorizontalTileView) view;
                String str8 = args.get("ASIN");
                if (str8 == null) {
                    throw new IllegalStateException("Missing track ASIN to unlike track".toString());
                }
                libraryProvider.setNeutralTrack(context, horizontalTileView, str8);
                return true;
            case -782532295:
                if (!name.equals(Methods.openMusicExperience)) {
                    return false;
                }
                NavigationProvider navigationProvider5 = this.navigationProvider;
                String str9 = args.get("ASIN");
                if (str9 == null) {
                    throw new IllegalStateException("Missing Artist ASIN for Music Experience".toString());
                }
                String str10 = args.get(Contexts.OpenMusicExperience.experienceId);
                if (str10 == null) {
                    throw new IllegalStateException("Missing experience Id for Music Experience".toString());
                }
                navigationProvider5.openMusicExperience(str9, str10);
                return true;
            case -429175409:
                if (!name.equals(Methods.openShareLink)) {
                    return false;
                }
                this.sharingProvider.share(context, args);
                return true;
            case -276263244:
                if (!name.equals(Methods.downloadTrack)) {
                    return false;
                }
                if (!(view instanceof HorizontalTileView)) {
                    return true;
                }
                DownloadProvider downloadProvider = this.downloadProvider;
                LibraryAddableView libraryAddableView = (LibraryAddableView) view;
                String str11 = args.get("ASIN");
                if (str11 == null) {
                    throw new IllegalStateException("Missing track ASIN for download".toString());
                }
                downloadProvider.downloadTrack(context, libraryAddableView, str11);
                return true;
            case -141470786:
                if (!name.equals(Methods.openLiveStream)) {
                    return false;
                }
                NavigationProvider navigationProvider6 = this.navigationProvider;
                String str12 = args.get("LIVE_STREAM_ID");
                if (str12 == null) {
                    throw new IllegalStateException("Missing LiveStream id".toString());
                }
                navigationProvider6.openLiveStream(str12);
                return true;
            case -90626987:
                if (!name.equals(Methods.openLiveStreams)) {
                    return false;
                }
                this.navigationProvider.openLiveStreams();
                return true;
            case 74726:
                if (!name.equals(Methods.openDetail)) {
                    return false;
                }
                NavigationProvider navigationProvider7 = this.navigationProvider;
                String str13 = args.get("ASIN");
                if (str13 == null) {
                    throw new IllegalStateException("Missing Merch ASIN".toString());
                }
                navigationProvider7.openAllArtistMerchPage(str13, args.get(Contexts.OpenDetail.category));
                return true;
            case 42555958:
                if (!name.equals(Methods.openSeeMore)) {
                    return false;
                }
                NavigationProvider navigationProvider8 = this.navigationProvider;
                String str14 = args.get(Contexts.OpenSeeMore.uri);
                if (str14 == null) {
                    throw new IllegalStateException("Missing See More uri".toString());
                }
                navigationProvider8.openSeeMore(context, str14);
                return true;
            case 76309356:
                if (!name.equals(Methods.openArtistReleaseDetail)) {
                    return false;
                }
                NavigationProvider navigationProvider9 = this.navigationProvider;
                String str15 = args.get("artistAsin");
                if (str15 == null) {
                    throw new IllegalStateException("Missing Artist ASIN".toString());
                }
                navigationProvider9.openArtistReleasesDetailPage(str15);
                return true;
            case 358634570:
                if (!name.equals(Methods.openPodcastDetail)) {
                    return false;
                }
                NavigationProvider navigationProvider10 = this.navigationProvider;
                String str16 = args.get("URL");
                if (str16 == null) {
                    throw new IllegalStateException("Missing url".toString());
                }
                navigationProvider10.openPodcastDetail(str16);
                return true;
            case 454393126:
                if (!name.equals("OPEN_TARGET")) {
                    return false;
                }
                String str17 = args.get("TARGET");
                if (str17 == null) {
                    return true;
                }
                try {
                    context.startActivity(new Intent("android.intent.action.VIEW", AnyKt.toUri(str17)));
                    return true;
                } catch (Exception unused2) {
                    logger.error("Unable to open target {}", str17);
                    return true;
                }
            case 650813329:
                if (!name.equals(Methods.openArtistSpotlight)) {
                    return false;
                }
                NavigationProvider navigationProvider11 = this.navigationProvider;
                String str18 = args.get("ASIN");
                if (str18 == null) {
                    throw new IllegalStateException("Missing Artist Spotlight ASIN".toString());
                }
                navigationProvider11.openArtistSpotlight(str18);
                return true;
            case 1081888751:
                if (!name.equals(Methods.showUpsell)) {
                    return false;
                }
                LibraryProvider libraryProvider2 = this.libraryProvider;
                String str19 = args.get("ASIN");
                if (str19 == null) {
                    throw new IllegalStateException("Missing track ASIN to show upsell".toString());
                }
                libraryProvider2.showUpsell(context, str19);
                return true;
            case 1430721129:
                if (!name.equals(Methods.addTrackToLibrary)) {
                    return false;
                }
                if (!(view instanceof HorizontalTileView)) {
                    return true;
                }
                LibraryProvider libraryProvider3 = this.libraryProvider;
                HorizontalTileView horizontalTileView2 = (HorizontalTileView) view;
                String str20 = args.get("ASIN");
                if (str20 == null) {
                    throw new IllegalStateException("Missing track ASIN to add to library".toString());
                }
                libraryProvider3.addTrackToLibrary(context, horizontalTileView2, str20);
                return true;
            case 1889234592:
                if (!name.equals(Methods.setLikeTrack)) {
                    return false;
                }
                if (!(view instanceof HorizontalTileView)) {
                    return true;
                }
                LibraryProvider libraryProvider4 = this.libraryProvider;
                HorizontalTileView horizontalTileView3 = (HorizontalTileView) view;
                String str21 = args.get("ASIN");
                if (str21 == null) {
                    throw new IllegalStateException("Missing track ASIN to like track".toString());
                }
                libraryProvider4.setLikeTrack(context, horizontalTileView3, str21);
                return true;
            default:
                return false;
        }
    }

    protected void createCalendarEvent(Context context, Map<String, String> args) {
        Date date;
        Date date2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(args, "args");
        String str = args.get(Contexts.OpenCalendar.startDate);
        Long lValueOf = null;
        Long lValueOf2 = (str == null || (date = Constants.INSTANCE.getOPEN_CALENDAR_DATE_FORMAT().parse(str)) == null) ? null : Long.valueOf(date.getTime());
        String str2 = args.get(Contexts.OpenCalendar.endDate);
        if (str2 != null && (date2 = Constants.INSTANCE.getOPEN_CALENDAR_DATE_FORMAT().parse(str2)) != null) {
            lValueOf = Long.valueOf(date2.getTime());
        }
        if (lValueOf2 == null || lValueOf == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.INSERT");
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra("beginTime", lValueOf2.longValue());
        intent.putExtra("endTime", lValueOf.longValue());
        intent.putExtra("title", args.get("TITLE"));
        intent.putExtra(MediaTrack.ROLE_DESCRIPTION, args.get(Contexts.OpenCalendar.note));
        intent.putExtra("eventLocation", args.get("URL"));
        context.startActivity(intent);
    }
}
