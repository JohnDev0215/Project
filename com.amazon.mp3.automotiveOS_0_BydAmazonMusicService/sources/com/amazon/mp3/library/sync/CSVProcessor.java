package com.amazon.mp3.library.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.configuration.IntlConfiguration;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil;
import com.amazon.mp3.library.service.sync.TrackSyncUtil;
import com.amazon.mp3.net.cirrus.AlbumArtworkUrlCache;
import com.amazon.mp3.net.cirrus.AlbumArtworkUrlRequest;
import com.amazon.mp3.prime.browse.metadata.CatalogStatusTiers;
import com.amazon.mp3.util.ContentEncodingDecoder;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.JsonUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MatchHashUtil;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazonaws.util.DateUtils;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class CSVProcessor extends Thread {
    private static final String TAG = CSVProcessor.class.getSimpleName();
    private Collection<String> mAccessedAlbumIds;
    private Collection<String> mAccessedArtistIds;
    private Collection<String> mAccessedGenreIds;
    private Collection<String> mAccessedTrackIds;
    private Map<String, String> mAlbumIdsToTrackIds;
    private final CacheManager mCacheManager;
    private Context mContext;
    private final PipedReader mCsvReader;
    private final SimpleDateFormat mDateFormat;
    private final SQLiteDatabase mDb;
    private Set<String> mInflightContributors;
    private final int mPrefetchSize;
    private boolean mSuccess;
    private int mUpdatedResourceCount;

    public CSVProcessor(Context context, PipedWriter writer, SQLiteDatabase db) throws IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN);
        this.mDateFormat = simpleDateFormat;
        this.mSuccess = true;
        this.mAccessedAlbumIds = new HashSet();
        this.mAccessedArtistIds = new HashSet();
        this.mAccessedTrackIds = new HashSet();
        this.mAccessedGenreIds = new HashSet();
        this.mAlbumIdsToTrackIds = new HashMap();
        this.mInflightContributors = new HashSet();
        this.mUpdatedResourceCount = 0;
        this.mContext = context;
        this.mDb = db;
        this.mCsvReader = new PipedReader(writer, Data.MAX_DATA_BYTES);
        this.mCacheManager = CacheManager.getInstance();
        this.mPrefetchSize = AmazonApplication.getCapabilities().defaultPrefetchImageSize();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private void resolveArtworkUrls(Map<String, String> trackIdToAlbumId) {
        try {
            new AlbumArtworkUrlRequest().requestAlbumArtworkUrls(trackIdToAlbumId, AlbumArtworkUrlCache.getCache(this.mContext), AlbumArtworkUrlCache.AlbumArtworkUrlCacheType.HEAVY);
        } catch (Exception e) {
            Log.warning(TAG, "Unable to prefetch artwork urls for track IDs " + StringUtils.join(trackIdToAlbumId.keySet(), " "), e);
        }
    }

    protected void batchResolveArtworkUrls(Map<String, String> albumIdsToTrackIds) {
        HashMap map = new HashMap(200);
        for (Map.Entry<String, String> entry : albumIdsToTrackIds.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
            if (map.size() > 200) {
                resolveArtworkUrls(map);
                map.clear();
            }
        }
        if (map.size() > 0) {
            resolveArtworkUrls(map);
        }
    }

    protected void fetchArtwork(Map<String, String> albumIdsToTrackIds, Collection<String> albumids, boolean expelCached) {
        batchResolveArtworkUrls(albumIdsToTrackIds);
        Iterator<String> it = albumids.iterator();
        while (it.hasNext()) {
            this.mCacheManager.prefetch(ImageLoaderFactory.ItemType.ALBUM, this.mPrefetchSize, it.next(), expelCached);
        }
    }

    static void writeToDb(final Context context, SQLiteDatabase db, ContentValues[] contentValuesBatch, int numberToWrite) {
        try {
            db.beginTransaction();
            TrackDataUtil trackDataUtil = new TrackDataUtil(db);
            for (int i = 0; i < numberToWrite; i++) {
                trackDataUtil.updateOrInsertTrack(context, contentValuesBatch[i], true);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 229
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.library.sync.CSVProcessor.run():void");
    }

    static String getStringValue(String[] line, CSV_1_0 columnDef) {
        Integer columnIndex = columnDef.getColumnIndex();
        if (columnIndex != null) {
            return line[columnIndex.intValue()];
        }
        return null;
    }

    static long getDateValue(String[] line, CSV_1_0 columnDef, SimpleDateFormat simpleDateFormat) {
        Integer columnIndex = columnDef.getColumnIndex();
        if (columnIndex != null) {
            return strToDate(line[columnIndex.intValue()], simpleDateFormat);
        }
        return 0L;
    }

    static int getIntValue(String[] line, CSV_1_0 columnDef, int defValue) {
        Integer columnIndex = columnDef.getColumnIndex();
        if (columnIndex != null) {
            return strToInt(line[columnIndex.intValue()], defValue);
        }
        return 0;
    }

    private static long strToDate(String dateString, SimpleDateFormat simpleDateFormat) {
        if (TextUtils.isEmpty(dateString)) {
            return 0L;
        }
        try {
            return simpleDateFormat.parse(dateString).getTime();
        } catch (ParseException unused) {
            Log.error(TAG, "Sync. Cannot parse date string %s.", dateString);
            return 0L;
        }
    }

    private static int strToInt(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException unused) {
            return defaultValue;
        }
    }

    @VisibleForTesting
    void populateContentValues(ContentValues contentValues, String[] strArr) {
        contentValues.clear();
        String stringValue = getStringValue(strArr, CSV_1_0.TITLE);
        String stringValue2 = getStringValue(strArr, CSV_1_0.SORT_TITLE);
        int intValue = getIntValue(strArr, CSV_1_0.TRACK_DISC_NUM, 0);
        int intValue2 = getIntValue(strArr, CSV_1_0.TRACK_NUM, 0);
        String stringValue3 = getStringValue(strArr, CSV_1_0.STATUS);
        if (intValue == 0) {
            intValue = 1;
        }
        int i = intValue;
        String stringValue4 = getStringValue(strArr, CSV_1_0.ASIN);
        contentValues.put("asin", stringValue4);
        CSV_1_0 csv_1_0 = CSV_1_0.ALBUM_ASIN;
        contentValues.put("album_asin", getStringValue(strArr, csv_1_0));
        contentValues.put("artist_asin", getStringValue(strArr, CSV_1_0.TRACK_ARTIST_ASIN));
        contentValues.put("title", stringValue);
        contentValues.put("disc_num", Integer.valueOf(i));
        contentValues.put("size", Integer.valueOf(getIntValue(strArr, CSV_1_0.FILE_SIZE, 0)));
        contentValues.put("duration", Integer.valueOf(getIntValue(strArr, CSV_1_0.TRACK_DURATION, -1)));
        contentValues.put("date_created", Long.valueOf(getDateValue(strArr, CSV_1_0.CREATION_DATE, this.mDateFormat)));
        contentValues.put("date_updated", Long.valueOf(getDateValue(strArr, CSV_1_0.LAST_UPDATE_DATE, this.mDateFormat)));
        contentValues.put("date_purchased", Long.valueOf(getDateValue(strArr, CSV_1_0.PURCHASE_DATE, this.mDateFormat)));
        String stringValue5 = getStringValue(strArr, CSV_1_0.FILE_EXTENSION);
        Locale locale = Locale.US;
        contentValues.put("extension", stringValue5.toLowerCase(locale));
        contentValues.put(IntlConfiguration.MARKETPLACE, getStringValue(strArr, CSV_1_0.MARKET));
        contentValues.put("sort_album", getStringValue(strArr, CSV_1_0.SORT_ALBUM_NAME));
        contentValues.put("sort_artist", getStringValue(strArr, CSV_1_0.SORT_TRACK_ARTIST_NAME));
        contentValues.put("sort_album_artist", getStringValue(strArr, CSV_1_0.SORT_ALBUM_ARTIST_NAME));
        contentValues.put("sort_title", stringValue2);
        contentValues.put(NotificationCompat.CATEGORY_STATUS, stringValue3);
        contentValues.put("track_num", Integer.valueOf(intValue2));
        String stringValue6 = getStringValue(strArr, CSV_1_0.PRIME_STATUS);
        String stringValue7 = getStringValue(strArr, CSV_1_0.HAWKFIRE_STATUS);
        String stringValue8 = getStringValue(strArr, CSV_1_0.ELIGIBILITY);
        if (stringValue8 != null) {
            CatalogStatusTiers catalogStatusTiersConvertToCatalogStatusTiers = TrackSyncUtil.convertToCatalogStatusTiers(stringValue6, stringValue7, JsonUtil.stringToJson(stringValue8));
            contentValues.put("catalog_status_tiers", Integer.valueOf(catalogStatusTiersConvertToCatalogStatusTiers.serialize()));
            contentValues.put("prime_status", Integer.valueOf(TrackSyncUtil.mapCatalogStatusForLibrarySync(stringValue6, stringValue7, catalogStatusTiersConvertToCatalogStatusTiers.getIsSonicRush()).getValue()));
        } else {
            contentValues.put("prime_status", Integer.valueOf(TrackSyncUtil.mapCatalogStatusForLibrarySync(stringValue6, stringValue7, false).getValue()));
        }
        contentValues.put("ownership_status", Integer.valueOf(TrackSyncUtil.mapOwnershipStatusForLibrarySync(stringValue3, stringValue6).getValue()));
        String stringValue9 = getStringValue(strArr, CSV_1_0.TRACK_PRIMARY_GENRE);
        long jGenerateGenreId = IdGenerator.generateGenreId(stringValue9);
        contentValues.put(ParserUtil.GENRE_SEGMENT_NAME, stringValue9);
        contentValues.put("genre_id", Long.valueOf(jGenerateGenreId));
        String lowerCase = getStringValue(strArr, CSV_1_0.OBJECT_ID).toLowerCase(locale);
        contentValues.put("luid", lowerCase);
        contentValues.put("luid_hash", Integer.valueOf(lowerCase.hashCode()));
        contentValues.put(PageUriUtils.SOURCE_QUERY_PARAM, (Integer) 0);
        boolean zEquals = "true".equals(getStringValue(strArr, CSV_1_0.UPLOADED));
        boolean zEquals2 = "true".equals(getStringValue(strArr, CSV_1_0.PURCHASED));
        if (zEquals || zEquals2) {
            contentValues.put("uploaded", Integer.valueOf(zEquals ? 1 : 0));
            contentValues.put("purchased", Integer.valueOf(zEquals2 ? 1 : 0));
        }
        String stringValue10 = getStringValue(strArr, CSV_1_0.ORDER_ID);
        if (stringValue10 != null && stringValue10.length() > 0) {
            contentValues.put("order_id", stringValue10);
        }
        String stringValue11 = getStringValue(strArr, CSV_1_0.ALBUM_NAME);
        String stringValue12 = getStringValue(strArr, csv_1_0);
        String stringValue13 = getStringValue(strArr, CSV_1_0.TRACK_ARTIST_NAME);
        String stringValue14 = getStringValue(strArr, CSV_1_0.ALBUM_ARTIST_NAME);
        String str = stringValue13.length() == 0 ? stringValue14 : stringValue13;
        String str2 = stringValue14.length() == 0 ? str : stringValue14;
        contentValues.put("artist", str);
        contentValues.put("album_artist", str2);
        contentValues.put("album", stringValue11);
        String str3 = str2;
        contentValues.put("match_hash", Integer.valueOf(MatchHashUtil.generateMatchHashForNotNormalizedMetadata(this.mContext, stringValue, stringValue11, str, str3, intValue2, i)));
        long jGenerateArtistId = IdGenerator.generateArtistId(str);
        long jGenerateArtistId2 = IdGenerator.generateArtistId(str3);
        long jGenerateAlbumId = IdGenerator.generateAlbumId(stringValue12, stringValue11);
        contentValues.put("album_id", Long.valueOf(jGenerateAlbumId));
        contentValues.put("album_art_id", Long.valueOf(jGenerateAlbumId));
        contentValues.put("album_artist_id", Long.valueOf(jGenerateArtistId2));
        contentValues.put("artist_id", Long.valueOf(jGenerateArtistId));
        contentValues.put("is_explicit", Integer.valueOf(getExplicitValue(getStringValue(strArr, CSV_1_0.PARENTAL_CONTROLS), stringValue4, false)));
        contentValues.put("content_encoding", getContentEncoding(getStringValue(strArr, CSV_1_0.ASSET_ELIGIBILITY), stringValue4, false));
        contentValues.put("is_exclusion_tagged", Integer.valueOf(getExclusionTaggedValue(getStringValue(strArr, CSV_1_0.INTERNAL_TAGS), stringValue4, false)));
        this.mAccessedTrackIds.add(lowerCase);
        String strValueOf = String.valueOf(jGenerateAlbumId);
        if (!this.mAccessedAlbumIds.contains(strValueOf)) {
            this.mAlbumIdsToTrackIds.put(strValueOf, lowerCase);
            this.mAccessedAlbumIds.add(strValueOf);
        }
        this.mAccessedArtistIds.add(String.valueOf(jGenerateArtistId));
        this.mAccessedArtistIds.add(String.valueOf(jGenerateArtistId2));
        this.mAccessedGenreIds.add(String.valueOf(jGenerateGenreId));
    }

    public static int getExplicitValue(String str, String str2, boolean z) {
        try {
            return new JSONObject(str).optBoolean("hasExplicitLanguage", false) ? 1 : 0;
        } catch (Exception unused) {
            if (!z) {
                return 0;
            }
            Log.error(TAG, "Unable to parse parentalControls column. ASIN: " + str2 + " ParentalControls: " + str);
            return 0;
        }
    }

    public static int getExclusionTaggedValue(String str, String str2, boolean z) {
        try {
            return new JSONArray(str).getString(0).equals("YRdPi52K") ? 1 : 0;
        } catch (Exception unused) {
            if (!z) {
                return 0;
            }
            Log.error(TAG, "Unable to parse internalTags column. ASIN: " + str2 + " InternalTags: " + str);
            return 0;
        }
    }

    public static String getContentEncoding(final String assetEligibility, final String asin, final boolean shouldLogError) {
        try {
            JSONObject jSONObject = new JSONObject(assetEligibility);
            ArrayList arrayList = new ArrayList();
            if (jSONObject.optBoolean("isHdAvailable", false)) {
                arrayList.add(ContentEncoding.HD);
            }
            if (jSONObject.optBoolean("isHiResAvailable", false)) {
                arrayList.add(ContentEncoding.UHD);
            }
            if (jSONObject.optBoolean("atmosAvailable", false)) {
                arrayList.add(ContentEncoding.DOLBY_ATMOS);
            }
            if (jSONObject.optBoolean("ra360Available", false)) {
                arrayList.add(ContentEncoding.SONY_360);
            }
            return ContentEncodingDecoder.contentEncodingToString(arrayList);
        } catch (Exception unused) {
            if (!shouldLogError) {
                return "";
            }
            Log.error(TAG, "Unable to parse contentEncoding column. ASIN: " + asin + " AssetEligibility: " + assetEligibility);
            return "";
        }
    }

    public boolean isSuccess() {
        return this.mSuccess;
    }

    public int getUpdatedResourceCount() {
        return this.mUpdatedResourceCount;
    }

    protected ContributorAccessObject.ContributorData createContributorData(String contributorsString, String albumContributorsString, ContentValues values) {
        try {
            String asString = values.getAsString("album_id");
            String asString2 = values.getAsString("album_artist_id");
            String asString3 = values.getAsString("album_artist");
            String asString4 = values.getAsString("artist_asin");
            String asString5 = values.getAsString("artist_id");
            String asString6 = values.getAsString("artist");
            StringBuilder sb = new StringBuilder();
            sb.append(asString);
            sb.append((char) 31);
            sb.append(asString2);
            sb.append((char) 31);
            sb.append(asString3);
            sb.append((char) 31);
            sb.append(asString4);
            sb.append((char) 31);
            sb.append(asString5);
            sb.append((char) 31);
            sb.append(asString6);
            sb.append((char) 31);
            sb.append(contributorsString != null ? contributorsString.length() : 0);
            sb.append((char) 31);
            sb.append(albumContributorsString != null ? albumContributorsString.length() : 0);
            if (!this.mInflightContributors.add(sb.toString())) {
                return null;
            }
            ContributorAccessObject.ContributorData contributorData = new ContributorAccessObject.ContributorData();
            if (!TextUtils.isEmpty(albumContributorsString)) {
                contributorData.addContributorEntry(new JSONObject(albumContributorsString), true, asString, asString2, asString3, asString4);
            }
            if (!TextUtils.isEmpty(contributorsString)) {
                contributorData.addContributorEntry(new JSONObject(contributorsString), false, asString, asString5, asString6, asString4);
            }
            return contributorData;
        } catch (JSONException e) {
            Log.warning(TAG, "Unable to parse JSON", e);
            return null;
        }
    }
}
