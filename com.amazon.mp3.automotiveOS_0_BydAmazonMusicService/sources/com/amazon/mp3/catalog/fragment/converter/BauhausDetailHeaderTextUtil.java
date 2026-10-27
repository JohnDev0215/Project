package com.amazon.mp3.catalog.fragment.converter;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.baseviews.model.configuration.PlaylistModelConfiguration;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.playlist.CatalogPlaylist;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: BauhausDetailHeaderTextUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ\u001a\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ\u0017\u0010\u0010\u001a\u0004\u0018\u00010\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ \u0010\u0018\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00012\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006J.\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\tH\u0002J\"\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 2\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¨\u0006#"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/converter/BauhausDetailHeaderTextUtil;", "", "()V", "calculateDuration", "", "tracks", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "getCuratedByProfileId", "", "item", "Lcom/amazon/mp3/library/item/AbstractItem;", "getDescriptionText", "getFollowerCount", "playlistModelConfiguration", "Lcom/amazon/mp3/baseviews/model/configuration/PlaylistModelConfiguration;", "getFollowerCountString", "count", "(Ljava/lang/Integer;)Ljava/lang/String;", "getPlaylistCuratedByProfileId", "catalogPlaylist", "Lcom/amazon/mp3/playlist/CatalogPlaylist;", "getPlaylistCuratedByText", "getPrimaryText", "getSecondaryText", "trackCount", "durationSeconds", "releaseDate", "Ljava/util/Date;", "visibilityText", "getSecondaryTextForAlbum", "album", "Lcom/amazon/mp3/library/item/Album;", "isCuratedByProfile", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BauhausDetailHeaderTextUtil {
    public static final BauhausDetailHeaderTextUtil INSTANCE = new BauhausDetailHeaderTextUtil();

    private BauhausDetailHeaderTextUtil() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String getSecondaryText$default(BauhausDetailHeaderTextUtil bauhausDetailHeaderTextUtil, Object obj, Collection collection, int i, Object obj2) {
        if ((i & 2) != 0) {
            collection = null;
        }
        return bauhausDetailHeaderTextUtil.getSecondaryText(obj, collection);
    }

    public final String getSecondaryText(Object item, Collection<? extends MusicTrack> tracks) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof CatalogPlaylist) {
            CatalogPlaylist catalogPlaylist = (CatalogPlaylist) item;
            int trackCount = catalogPlaylist.getTrackCount();
            int durationSeconds = catalogPlaylist.getDurationSeconds();
            String visibilityText = catalogPlaylist.getVisibilityText();
            Intrinsics.checkNotNullExpressionValue(visibilityText, "item.visibilityText");
            String upperCase = visibilityText.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
            return getSecondaryText$default(this, trackCount, durationSeconds, null, upperCase, 4, null);
        }
        if (!(item instanceof Playlist)) {
            if (item instanceof Album) {
                return getSecondaryTextForAlbum((Album) item, tracks);
            }
            if (!(item instanceof com.amazon.musicensembleservice.brush.Album)) {
                return "";
            }
            com.amazon.musicensembleservice.brush.Album album = (com.amazon.musicensembleservice.brush.Album) item;
            return getSecondaryText$default(this, (int) album.getTracksCount().longValue(), (int) album.getDuration().longValue(), album.getOriginalReleaseDate(), null, 8, null);
        }
        Playlist playlist = (Playlist) item;
        int trackCount2 = (int) playlist.getTrackCount();
        int iCalculateDuration = calculateDuration(tracks);
        String visibilityText2 = playlist.getVisibilityText();
        Intrinsics.checkNotNullExpressionValue(visibilityText2, "item.visibilityText");
        String upperCase2 = visibilityText2.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase2, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        return getSecondaryText$default(this, trackCount2, iCalculateDuration, null, upperCase2, 4, null);
    }

    public final String getPrimaryText(AbstractItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof CatalogPlaylist) {
            return getPlaylistCuratedByText((CatalogPlaylist) item);
        }
        return null;
    }

    public final String getDescriptionText(AbstractItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (!(item instanceof CatalogPlaylist)) {
            return null;
        }
        CatalogPlaylist catalogPlaylist = (CatalogPlaylist) item;
        if (TextUtils.isEmpty(catalogPlaylist.getDescription())) {
            return null;
        }
        return catalogPlaylist.getDescription();
    }

    public final String getCuratedByProfileId(AbstractItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item instanceof CatalogPlaylist) {
            return getPlaylistCuratedByProfileId((CatalogPlaylist) item);
        }
        return null;
    }

    public final String getFollowerCount(AbstractItem item, PlaylistModelConfiguration playlistModelConfiguration) {
        int numberOfFollowers;
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(playlistModelConfiguration, "playlistModelConfiguration");
        if (!playlistModelConfiguration.shouldDisplayFollowerCount(item)) {
            return null;
        }
        if (item instanceof CatalogPlaylist) {
            numberOfFollowers = ((CatalogPlaylist) item).getNumberOfFollowers();
        } else {
            numberOfFollowers = item instanceof Playlist ? ((Playlist) item).getNumberOfFollowers() : 0;
        }
        return getFollowerCountString(Integer.valueOf(numberOfFollowers));
    }

    public final String getFollowerCountString(Integer count) {
        Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
        if (count == null) {
            return "";
        }
        if (count.intValue() == 0) {
            return count + ' ' + applicationContext.getString(R.string.dmusic_profile_followers);
        }
        if (count.intValue() == 1) {
            return count + ' ' + applicationContext.getString(R.string.dmusic_profile_follower);
        }
        if (count.intValue() <= 1) {
            return null;
        }
        return count + ' ' + applicationContext.getString(R.string.dmusic_profile_followers);
    }

    private final String getPlaylistCuratedByText(CatalogPlaylist catalogPlaylist) {
        if (TextUtils.isEmpty(catalogPlaylist.getCuratedBy())) {
            return null;
        }
        String string = AmazonApplication.getContext().getResources().getString(R.string.dmusic_library_prime_playlists_description);
        Intrinsics.checkNotNullExpressionValue(string, "getContext().resources.g…me_playlists_description)");
        String curatedBy = catalogPlaylist.getCuratedBy();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(string, Arrays.copyOf(new Object[]{curatedBy}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        return str;
    }

    private final String getPlaylistCuratedByProfileId(CatalogPlaylist catalogPlaylist) {
        if (isCuratedByProfile(catalogPlaylist)) {
            return catalogPlaylist.getProfileId();
        }
        return null;
    }

    static /* synthetic */ String getSecondaryText$default(BauhausDetailHeaderTextUtil bauhausDetailHeaderTextUtil, int i, int i2, Date date, String str, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            date = null;
        }
        if ((i3 & 8) != 0) {
            str = "";
        }
        return bauhausDetailHeaderTextUtil.getSecondaryText(i, i2, date, str);
    }

    private final String getSecondaryText(int trackCount, int durationSeconds, Date releaseDate, String visibilityText) {
        String strStringPlus;
        String str;
        String upperCase;
        int i = durationSeconds / 3600;
        int i2 = (durationSeconds % 3600) / 60;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Locale locale = Locale.ENGLISH;
        String str2 = String.format(locale, AmazonApplication.getContext().getResources().getQuantityText(R.plurals.dmusic_track_count, trackCount).toString(), Arrays.copyOf(new Object[]{Integer.valueOf(trackCount)}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(locale, format, *args)");
        if (i >= 1) {
            String str3 = String.format(locale, AmazonApplication.getContext().getResources().getQuantityText(R.plurals.dmusic_duration_hours, i).toString(), Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(locale, format, *args)");
            strStringPlus = Intrinsics.stringPlus(str3, " ");
        } else {
            strStringPlus = "";
        }
        if (i2 >= 1) {
            str = String.format(locale, AmazonApplication.getContext().getResources().getQuantityText(R.plurals.dmusic_duration_minutes, i2).toString(), Arrays.copyOf(new Object[]{Integer.valueOf(i2)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
        } else {
            str = "";
        }
        if (releaseDate == null) {
            upperCase = null;
        } else {
            String str4 = new SimpleDateFormat("MMM dd yyyy").format(releaseDate);
            Intrinsics.checkNotNullExpressionValue(str4, "dateFormat.format(date)");
            upperCase = str4.toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(visibilityText);
        sb.append(str2);
        sb.append(" • ");
        sb.append(strStringPlus);
        sb.append(str);
        sb.append(upperCase != null ? Intrinsics.stringPlus(" • ", upperCase) : "");
        return sb.toString();
    }

    private final int calculateDuration(Collection<? extends MusicTrack> tracks) {
        int duration = 0;
        if (tracks == null) {
            return 0;
        }
        Iterator<? extends MusicTrack> it = tracks.iterator();
        while (it.hasNext()) {
            duration += (int) it.next().getDuration();
        }
        return duration;
    }

    private final boolean isCuratedByProfile(CatalogPlaylist catalogPlaylist) {
        String profileId = catalogPlaylist.getProfileId();
        if (profileId == null || profileId.length() == 0) {
            return false;
        }
        String curatedBy = catalogPlaylist.getCuratedBy();
        return !(curatedBy == null || curatedBy.length() == 0);
    }

    private final String getSecondaryTextForAlbum(Album album, Collection<? extends MusicTrack> tracks) {
        Collection<? extends MusicTrack> tracks2 = tracks == null ? album.getTracks() : tracks;
        Integer numValueOf = tracks == null ? null : Integer.valueOf(tracks.size());
        return getSecondaryText$default(this, numValueOf == null ? (int) album.getTrackCount() : numValueOf.intValue(), calculateDuration(tracks2), null, null, 12, null);
    }
}
