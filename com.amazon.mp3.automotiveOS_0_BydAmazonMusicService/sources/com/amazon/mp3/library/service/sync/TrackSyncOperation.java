package com.amazon.mp3.library.service.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.sync.CSVProcessor;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.JsonUtil;
import com.amazon.mp3.util.MatchHashUtil;
import com.amazon.mp3.util.Profiler;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class TrackSyncOperation extends SyncOperation {
    private final CacheManager mCacheManager;
    private final int mPrefetchSize;

    TrackSyncOperation(Context context, SQLiteDatabase db) {
        super(context, db);
        this.mCacheManager = CacheManager.getInstance();
        this.mPrefetchSize = AmazonApplication.getCapabilities().defaultPrefetchImageSize();
    }

    protected ContributorAccessObject.ContributorData updateContributorData(ContributorAccessObject.ContributorData contributorData, String albumId, String albumArtistId, String albumArtistName, String artistId, String artistName, JSONObject metadata) {
        try {
            Profiler.begin("Updating contributor data in sync");
            if (contributorData == null) {
                contributorData = new ContributorAccessObject.ContributorData();
            }
            try {
                String string = metadata.getString("artistAsin");
                contributorData.addContributorEntry(new JSONObject(metadata.getString("albumContributors")), true, albumId, albumArtistId, albumArtistName, string);
                contributorData.addContributorEntry(new JSONObject(metadata.getString("contributors")), false, albumId, artistId, artistName, string);
            } catch (JSONException unused) {
            }
            return contributorData;
        } finally {
            Profiler.end();
        }
    }

    protected ContentValues getContentValues(JSONObject jSONObject) {
        String string = getString(jSONObject, "title");
        String string2 = getString(jSONObject, "sortTitle");
        int i = getInt(jSONObject, "discNum", 0);
        int i2 = getInt(jSONObject, "trackNum", 0);
        String string3 = getString(jSONObject, NotificationCompat.CATEGORY_STATUS);
        int i3 = i == 0 ? 1 : i;
        ContentValues contentValues = new ContentValues();
        String string4 = getString(jSONObject, "asin");
        contentValues.put("asin", string4);
        contentValues.put("album_asin", getString(jSONObject, "albumAsin"));
        contentValues.put("artist_asin", getString(jSONObject, "artistAsin"));
        contentValues.put("title", string);
        contentValues.put("track_num", Integer.valueOf(i2));
        contentValues.put("disc_num", Integer.valueOf(i3));
        contentValues.put("size", Integer.valueOf(getInt(jSONObject, "fileSize", 0)));
        contentValues.put("duration", Integer.valueOf(getInt(jSONObject, "duration", -1)));
        contentValues.put("date_created", Long.valueOf(getDate(jSONObject, "creationDate")));
        contentValues.put("date_updated", Long.valueOf(getDate(jSONObject, "lastUpdatedDate")));
        contentValues.put("date_purchased", Long.valueOf(getDate(jSONObject, "purchaseDate")));
        String string5 = getString(jSONObject, "fileExtension");
        Locale locale = Locale.US;
        contentValues.put("extension", string5.toLowerCase(locale));
        contentValues.put("version", getString(jSONObject, "version"));
        contentValues.put(IntlConfiguration.MARKETPLACE, getString(jSONObject, IntlConfiguration.MARKETPLACE));
        contentValues.put("sort_album", getString(jSONObject, "sortAlbumName"));
        contentValues.put("sort_artist", getString(jSONObject, "sortArtistName"));
        contentValues.put("sort_album_artist", getString(jSONObject, "sortAlbumArtistName"));
        contentValues.put("sort_title", string2);
        contentValues.put(NotificationCompat.CATEGORY_STATUS, string3);
        String string6 = getString(jSONObject, "primeStatus");
        String string7 = getString(jSONObject, "isMusicSubscription");
        CatalogStatusTiers catalogStatusTiersConvertToCatalogStatusTiers = TrackSyncUtil.convertToCatalogStatusTiers(string6, string7, JsonUtil.stringToJson(getString(jSONObject, "eligibility")));
        contentValues.put("catalog_status_tiers", Integer.valueOf(catalogStatusTiersConvertToCatalogStatusTiers.serialize()));
        contentValues.put("prime_status", Integer.valueOf(TrackSyncUtil.mapCatalogStatusForLibrarySync(string6, string7, catalogStatusTiersConvertToCatalogStatusTiers.getIsSonicRush()).getValue()));
        contentValues.put("ownership_status", Integer.valueOf(TrackSyncUtil.mapOwnershipStatusForLibrarySync(string3, string6).getValue()));
        String string8 = getString(jSONObject, "primaryGenre");
        long jGenerateGenreId = IdGenerator.generateGenreId(string8);
        contentValues.put(ParserUtil.GENRE_SEGMENT_NAME, string8);
        contentValues.put("genre_id", Long.valueOf(jGenerateGenreId));
        String lowerCase = getString(jSONObject, "objectId").toLowerCase(locale);
        contentValues.put("luid", lowerCase);
        contentValues.put("luid_hash", Integer.valueOf(lowerCase.hashCode()));
        contentValues.put(PageUriUtils.SOURCE_QUERY_PARAM, (Integer) 0);
        boolean zEquals = "true".equals(getString(jSONObject, "uploaded"));
        boolean zEquals2 = "true".equals(getString(jSONObject, "purchased"));
        if (zEquals || zEquals2) {
            contentValues.put("uploaded", Integer.valueOf(zEquals ? 1 : 0));
            contentValues.put("purchased", Integer.valueOf(zEquals2 ? 1 : 0));
        }
        String string9 = getString(jSONObject, "orderId");
        if (string9 != null && string9.length() > 0) {
            contentValues.put("order_id", string9);
        }
        String string10 = getString(jSONObject, "albumName");
        String string11 = getString(jSONObject, "albumAsin");
        String string12 = getString(jSONObject, "artistName");
        String string13 = getString(jSONObject, "albumArtistName");
        String str = string12.length() == 0 ? string13 : string12;
        String str2 = string13.length() == 0 ? str : string13;
        contentValues.put("artist", str);
        contentValues.put("album_artist", str2);
        contentValues.put("album", string10);
        contentValues.put("match_hash", Integer.valueOf(MatchHashUtil.generateMatchHashForNotNormalizedMetadata(getContext(), string, string10, str, str2, i2, i3)));
        long jGenerateArtistId = IdGenerator.generateArtistId(str);
        long jGenerateArtistId2 = IdGenerator.generateArtistId(str2);
        long jGenerateAlbumId = IdGenerator.generateAlbumId(string11, string10);
        contentValues.put("album_id", Long.valueOf(jGenerateAlbumId));
        contentValues.put("album_art_id", Long.valueOf(jGenerateAlbumId));
        contentValues.put("album_artist_id", Long.valueOf(jGenerateArtistId2));
        contentValues.put("artist_id", Long.valueOf(jGenerateArtistId));
        contentValues.put("is_explicit", Integer.valueOf(CSVProcessor.getExplicitValue(getString(jSONObject, "parentalControls"), string4, true)));
        contentValues.put("content_encoding", CSVProcessor.getContentEncoding(getString(jSONObject, "assetEligibility"), string4, true));
        contentValues.put("is_exclusion_tagged", Integer.valueOf(CSVProcessor.getExclusionTaggedValue(getString(jSONObject, "internalTags"), string4, true)));
        return contentValues;
    }

    protected void prefetch(String albumid, boolean expelCached) {
        this.mCacheManager.prefetch(ImageLoaderFactory.ItemType.ALBUM, this.mPrefetchSize, albumid, expelCached);
    }

    protected void prefetch(Collection<String> albumids, boolean expelCached) {
        Iterator<String> it = albumids.iterator();
        while (it.hasNext()) {
            prefetch(it.next(), expelCached);
        }
    }

    protected void updateCirrusSmartPlaylist() {
        ArrayList arrayList = new ArrayList();
        Iterator<Long> it = CirrusMediaSource.SMART_PLAYLIST_IDS.iterator();
        while (it.hasNext()) {
            arrayList.add(Long.toString(it.next().longValue()));
        }
    }
}
