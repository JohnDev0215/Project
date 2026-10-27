package com.amazon.mp3.library.data.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.item.ItemWrapper$IdType;
import com.amazon.mp3.library.item.ItemWrapper$ItemType;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import com.amazon.mp3.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public final class ContributorAccessObject {
    private static final String LOGTAG = "ContributorAccessObject";
    private static final String[] JOINED_ITEM_PROJECTION = {"ItemContributors.item_uri AS item_uri", "ItemContributors.contributor_asin AS asin", "Contributors.artist_asin AS artist_asin", "Contributors.xray_ready AS xray_ready", "Contributors.hero_image_url AS hero_image_url", "Contributors.hero_image_max_width AS hero_image_max_width", "Contributors.hero_image_max_height AS hero_image_max_height", "Contributors.image_url AS image_url", "Contributors.image_max_width AS image_max_width", "Contributors.image_max_height AS image_max_height", "Contributors.name AS name", "Contributors.date_last_updated AS date_last_updated", "Contributors.is_temp AS is_temp"};
    private static final String[] CONTRIBUTOR_PROJECTION = {"asin", "artist_asin", "name", "xray_ready", "hero_image_url", "hero_image_max_width", "hero_image_max_height", "image_url", "image_max_width", "image_max_height", "is_temp", "date_last_updated"};
    private static final String[] ITEM_CONTRIBUTOR_PROJECTION = {"contributor_asin", "item_uri", "default_contrib_flag", "date_last_updated"};

    public static class ContributorData {
        private HashSet<Contributor> mAllContributors = new HashSet<>();
        private HashMap<String, Contributor> mArtistContributorMap = new HashMap<>();
        private HashMap<String, HashSet<Contributor>> mAlbumContributorsMap = new HashMap<>();

        public HashSet<Contributor> getAllContributors() {
            return this.mAllContributors;
        }

        public HashMap<String, Contributor> getArtistContributorMap() {
            return this.mArtistContributorMap;
        }

        public void addContributorEntry(JSONObject contributors, boolean findAlbumContributor, String albumId, String artistId, String artistName, String artistAsin) throws JSONException {
            HashSet<Contributor> hashSet;
            Profiler.begin("Adding single contributor entry");
            JSONArray jSONArray = contributors.getJSONArray("primaryContributors");
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Contributor contributor = new Contributor();
                String string = jSONObject.getString("name");
                contributor.setArtistAsin(artistAsin);
                contributor.setAsin(jSONObject.getString("asin"));
                contributor.setName(string);
                if (length > 1) {
                    contributor.setPrimary(false);
                    if (artistId != null && artistName != null && !this.mArtistContributorMap.containsKey(artistId) && StringUtil.normalizeArtistName(artistName).contains(StringUtil.normalizeArtistName(string))) {
                        this.mArtistContributorMap.put(artistId, contributor);
                    }
                } else {
                    this.mArtistContributorMap.put(artistId, contributor);
                }
                if (findAlbumContributor) {
                    if (this.mAlbumContributorsMap.containsKey(albumId)) {
                        hashSet = this.mAlbumContributorsMap.get(albumId);
                    } else {
                        hashSet = new HashSet<>();
                        this.mAlbumContributorsMap.put(albumId, hashSet);
                    }
                    hashSet.add(contributor);
                }
                this.mAllContributors.add(contributor);
            }
            Profiler.end();
        }
    }

    public static class ItemContributor {
        private String mContributorAsin;
        private boolean mDefaultFlag;
        private String mItemUri;
        private Date mLastUpdated;

        public String getItemUri() {
            return this.mItemUri;
        }

        public void setItemUri(String itemUri) {
            this.mItemUri = itemUri;
        }

        public void setContributorAsin(String contributorAsin) {
            this.mContributorAsin = contributorAsin;
        }

        public void setDefaultFlag(boolean defaultFlag) {
            this.mDefaultFlag = defaultFlag;
        }

        public void setLastUpdated(Date lastUpdated) {
            this.mLastUpdated = (Date) lastUpdated.clone();
        }

        public ItemContributor copyFrom(Cursor cursor) {
            setContributorAsin(cursor.getString(cursor.getColumnIndex("contributor_asin")));
            setItemUri(cursor.getString(cursor.getColumnIndex("item_uri")));
            setDefaultFlag(cursor.getInt(cursor.getColumnIndex("default_contrib_flag")) > 0);
            setLastUpdated(new Date(cursor.getLong(cursor.getColumnIndex("date_last_updated"))));
            return this;
        }
    }

    public static class Contributor {
        private String m16x9HeroImageURL;
        private String mArtistAsin;
        private String mAsin;
        private String mHeroImageURL;
        private int mHeroImage_MaxHeight;
        private int mHeroImage_MaxWidth;
        private boolean mIsTemp;
        private Date mLastUpdated;
        private String mName;
        private boolean mPrimary = true;
        private String mThumbnailImageURL;
        private int mThumbnailImage_MaxHeight;
        private int mThumbnailImage_MaxWidth;
        private boolean mXrayReady;

        public boolean getPrimary() {
            return this.mPrimary;
        }

        public void setPrimary(boolean primary) {
            this.mPrimary = primary;
        }

        public String getAsin() {
            return this.mAsin;
        }

        public void setAsin(String asin) {
            this.mAsin = asin;
        }

        public String getArtistAsin() {
            return this.mArtistAsin;
        }

        public void setArtistAsin(String asin) {
            this.mArtistAsin = asin;
        }

        public String getName() {
            return this.mName;
        }

        public void setName(String name) {
            this.mName = name;
        }

        public boolean isXrayReady() {
            return this.mXrayReady;
        }

        public void setXrayReady(boolean xrayReady) {
            this.mXrayReady = xrayReady;
        }

        public String getHeroImageURL() {
            return this.mHeroImageURL;
        }

        public int getHeroImageMaxWidth() {
            return this.mHeroImage_MaxWidth;
        }

        public int getHeroImageMaxHeight() {
            return this.mHeroImage_MaxHeight;
        }

        public void setHeroImageMaxWidth(int width) {
            this.mHeroImage_MaxWidth = width;
        }

        public void setHeroImageMaxHeight(int height) {
            this.mHeroImage_MaxHeight = height;
        }

        public String get16x9HeroImageURL() {
            if (this.m16x9HeroImageURL == null) {
                String str = this.mHeroImageURL;
                if (str != null && this.mHeroImage_MaxHeight > 300 && this.mHeroImage_MaxWidth > 533) {
                    this.m16x9HeroImageURL = str.replaceAll("\\.\\_.*\\.", "._CR0,0," + this.mHeroImage_MaxWidth + "," + Math.round(this.mHeroImage_MaxWidth * 0.5625f) + "_.");
                } else {
                    String str2 = this.mThumbnailImageURL;
                    if (str2 != null && this.mThumbnailImage_MaxHeight > 300 && this.mThumbnailImage_MaxWidth > 533) {
                        this.m16x9HeroImageURL = str2.replaceAll("\\.\\_.*\\.", "._CR0,0," + this.mThumbnailImage_MaxWidth + "," + Math.round(this.mThumbnailImage_MaxWidth * 0.5625f) + "_.");
                    }
                }
            }
            return this.m16x9HeroImageURL;
        }

        public void setHeroImageURL(String heroImageURL) {
            this.mHeroImageURL = heroImageURL;
        }

        public String getThumbnailImageURL() {
            return this.mThumbnailImageURL;
        }

        public void setThumbnailImageURL(String imageURL) {
            this.mThumbnailImageURL = imageURL;
        }

        public int getThumbnailImageMaxWidth() {
            return this.mThumbnailImage_MaxWidth;
        }

        public int getThumbnailImageMaxHeight() {
            return this.mThumbnailImage_MaxHeight;
        }

        public void setThumbnailImageMaxWidth(int width) {
            this.mThumbnailImage_MaxWidth = width;
        }

        public void setThumbnailImageMaxHeight(int height) {
            this.mThumbnailImage_MaxHeight = height;
        }

        public String getImageURL() {
            String str = this.mThumbnailImageURL;
            return str != null ? str : this.mHeroImageURL;
        }

        public int getImageMaxWidth() {
            if (this.mThumbnailImageURL != null) {
                return this.mThumbnailImage_MaxWidth;
            }
            return this.mHeroImage_MaxWidth;
        }

        public int getImageMaxHeight() {
            if (this.mThumbnailImageURL != null) {
                return this.mThumbnailImage_MaxHeight;
            }
            return this.mHeroImage_MaxHeight;
        }

        public Date getLastUpdated() {
            Date date = this.mLastUpdated;
            if (date != null) {
                return (Date) date.clone();
            }
            return null;
        }

        public void setLastUpdated(Date lastUpdated) {
            this.mLastUpdated = (Date) lastUpdated.clone();
        }

        public int hashCode() {
            String str = this.mAsin;
            if (str != null) {
                return str.hashCode();
            }
            return super.hashCode();
        }

        public boolean equals(Object o) {
            String str;
            if (!(o instanceof Contributor) || (str = this.mAsin) == null) {
                return false;
            }
            return str.equals(((Contributor) o).mAsin);
        }

        public boolean isTemp() {
            return this.mIsTemp;
        }

        public void setTemp(boolean temp) {
            this.mIsTemp = temp;
        }

        public String toString() {
            return String.format(Locale.US, "Contributor [asin: %s, name: %s, xrayReady: %s,  thumbnailImage: %s (%d x %d), heroImage: %s (%d x %d)]", this.mAsin, this.mName, Boolean.valueOf(this.mXrayReady), this.mThumbnailImageURL, Integer.valueOf(this.mThumbnailImage_MaxWidth), Integer.valueOf(this.mThumbnailImage_MaxHeight), this.mHeroImageURL, Integer.valueOf(this.mHeroImage_MaxWidth), Integer.valueOf(this.mHeroImage_MaxHeight));
        }

        public Contributor copyFrom(Cursor cursor) {
            setAsin(cursor.getString(cursor.getColumnIndex("asin")));
            setArtistAsin(cursor.getString(cursor.getColumnIndex("artist_asin")));
            setName(cursor.getString(cursor.getColumnIndex("name")));
            setXrayReady(cursor.getInt(cursor.getColumnIndex("xray_ready")) > 0);
            setHeroImageURL(cursor.getString(cursor.getColumnIndex("hero_image_url")));
            setHeroImageMaxWidth(cursor.getInt(cursor.getColumnIndex("hero_image_max_width")));
            setHeroImageMaxHeight(cursor.getInt(cursor.getColumnIndex("hero_image_max_height")));
            setThumbnailImageURL(cursor.getString(cursor.getColumnIndex("image_url")));
            setThumbnailImageMaxWidth(cursor.getInt(cursor.getColumnIndex("image_max_width")));
            setThumbnailImageMaxHeight(cursor.getInt(cursor.getColumnIndex("image_max_height")));
            setTemp(cursor.getInt(cursor.getColumnIndex("is_temp")) > 0);
            setLastUpdated(new Date(cursor.getLong(cursor.getColumnIndex("date_last_updated"))));
            return this;
        }

        public ContentValues toContentValues() {
            ContentValues contentValues = new ContentValues();
            contentValues.put("asin", getAsin());
            contentValues.put("artist_asin", getArtistAsin());
            contentValues.put("image_url", getThumbnailImageURL());
            contentValues.put("image_max_width", Integer.valueOf(getImageMaxWidth()));
            contentValues.put("image_max_height", Integer.valueOf(getImageMaxHeight()));
            contentValues.put("hero_image_url", getHeroImageURL());
            contentValues.put("hero_image_max_width", Integer.valueOf(getHeroImageMaxWidth()));
            contentValues.put("hero_image_max_height", Integer.valueOf(getHeroImageMaxHeight()));
            contentValues.put("xray_ready", Boolean.valueOf(isXrayReady()));
            contentValues.put("name", getName());
            contentValues.put("is_temp", Boolean.valueOf(isTemp()));
            if (getLastUpdated() == null) {
                contentValues.put("date_last_updated", Long.valueOf(System.currentTimeMillis()));
            } else {
                contentValues.put("date_last_updated", Long.valueOf(getLastUpdated().getTime()));
            }
            return contentValues;
        }
    }

    public static void insertOrUpdate(ContributorData contributorData) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(AmazonApplication.getContext());
        writableDatabase.beginTransaction();
        try {
            Profiler.begin("insert/updating contributor data");
            if (contributorData != null) {
                insertOrUpdate(contributorData.mAllContributors);
                for (String str : contributorData.mArtistContributorMap.keySet()) {
                    Uri uri = ItemWrapper$IdType.MERGED_ID.getUri(ItemWrapper$ItemType.ARTIST, str);
                    Contributor contributor = (Contributor) contributorData.mArtistContributorMap.get(str);
                    insertOrUpdate(uri.toString(), contributor.getAsin(), true, contributor.getArtistAsin());
                }
                for (String str2 : contributorData.mAlbumContributorsMap.keySet()) {
                    Uri uri2 = ItemWrapper$IdType.MERGED_ID.getUri(ItemWrapper$ItemType.ALBUM, str2);
                    for (Contributor contributor2 : (Set) contributorData.mAlbumContributorsMap.get(str2)) {
                        insertOrUpdate(uri2.toString(), contributor2.getAsin(), false, contributor2.getArtistAsin());
                    }
                }
                writableDatabase.setTransactionSuccessful();
                return;
            }
            Log.warning(LOGTAG, "Got insert/update contributor data null");
        } finally {
            writableDatabase.endTransaction();
            Profiler.end();
        }
    }

    public static void insertOrUpdate(String itemUri, String contributorAsin, boolean defaultContribFlag, String artistAsin) {
        if (contributorAsin == null) {
            Log.warning(LOGTAG, "Got insert/update contributor request with null ASIN");
            return;
        }
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(AmazonApplication.getContext());
        writableDatabase.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("item_uri", itemUri);
            contentValues.put("contributor_asin", contributorAsin);
            contentValues.put("date_last_updated", Long.valueOf(new Date().getTime()));
            contentValues.put("default_contrib_flag", Boolean.valueOf(defaultContribFlag));
            String[] strArr = {itemUri, contributorAsin};
            Cursor cursorQuery = writableDatabase.query("ItemContributors", new String[]{"contributor_asin"}, "item_uri=? AND contributor_asin=?", strArr, null, null, null);
            if (cursorQuery != null && cursorQuery.getCount() > 1) {
                Log.error(LOGTAG, "Got more than one result back querying Contributors table for %s!!", contributorAsin);
            } else if (cursorQuery != null && cursorQuery.getCount() == 1) {
                writableDatabase.update("ItemContributors", contentValues, "item_uri=? AND contributor_asin=?", strArr);
            } else {
                writableDatabase.insert("ItemContributors", null, contentValues);
            }
            DbUtil.closeCursor(cursorQuery);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public static void insertOrUpdate(SQLiteDatabase db, Contributor contributor) throws Throwable {
        Cursor cursor = null;
        try {
            if (contributor == null) {
                Log.warning(LOGTAG, "Got insert/update contributor request with null");
                DbUtil.closeCursor(null);
                return;
            }
            String asin = contributor.getAsin();
            if (asin == null) {
                Log.warning(LOGTAG, "Got insert/update contributor request with null ASIN");
                DbUtil.closeCursor(null);
                return;
            }
            String[] strArr = {asin};
            Profiler.begin("CAO - querying contributors");
            Cursor cursorQuery = db.query("Contributors", new String[]{"asin"}, "asin=?", strArr, null, null, null);
            try {
                Profiler.end();
                if (cursorQuery != null && cursorQuery.getCount() > 1) {
                    Log.error(LOGTAG, "Got more than one result back querying Contributors table for %s!!", asin);
                } else if (cursorQuery != null && cursorQuery.getCount() == 1) {
                    Profiler.begin("CAO - updating");
                    db.update("Contributors", contributor.toContentValues(), "asin=?", strArr);
                    Profiler.end();
                } else {
                    Profiler.begin("CAO = inserting");
                    db.insert("Contributors", null, contributor.toContentValues());
                    Profiler.end();
                }
                DbUtil.closeCursor(cursorQuery);
            } catch (Throwable th) {
                th = th;
                cursor = cursorQuery;
                DbUtil.closeCursor(cursor);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void insertOrUpdate(Collection<Contributor> contributors) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(AmazonApplication.getContext());
        writableDatabase.beginTransaction();
        try {
            Date date = new Date();
            for (Contributor contributor : contributors) {
                contributor.setLastUpdated(date);
                insertOrUpdate(writableDatabase, contributor);
            }
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public static Contributor fetchContributor(String asin) throws Throwable {
        Throwable th;
        Cursor cursorQuery;
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getContext());
        Contributor contributorCopyFrom = null;
        if (asin == null) {
            Log.warning(LOGTAG, "Got fetch contributor request with null ASIN");
            return null;
        }
        try {
            cursorQuery = readOnlyDatabase.query("Contributors", CONTRIBUTOR_PROJECTION, "asin=?", new String[]{asin}, null, null, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        contributorCopyFrom = new Contributor().copyFrom(cursorQuery);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    DbUtil.closeCursor(cursorQuery);
                    throw th;
                }
            }
            DbUtil.closeCursor(cursorQuery);
            return contributorCopyFrom;
        } catch (Throwable th3) {
            th = th3;
            cursorQuery = null;
        }
    }

    public static List<Contributor> fetchContributorsForItem(String itemUri) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getContext());
        Cursor cursorQuery = null;
        if (itemUri == null || itemUri.length() == 0) {
            Log.warning(LOGTAG, "Got fetch contributor for item request with null or empty itemUri");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            cursorQuery = readOnlyDatabase.query("ItemContributors LEFT OUTER JOIN Contributors ON ItemContributors.contributor_asin=Contributors.asin", JOINED_ITEM_PROJECTION, "ItemContributors.item_uri=?", new String[]{itemUri}, null, null, null);
            while (cursorQuery != null) {
                if (!cursorQuery.moveToNext()) {
                    break;
                }
                arrayList.add(new Contributor().copyFrom(cursorQuery));
            }
            return arrayList;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }

    public static Contributor fetchDefaultContributorForItem(String itemUri) throws Throwable {
        Throwable th;
        Cursor cursorQuery;
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getContext());
        Contributor contributorCopyFrom = null;
        if (itemUri == null || itemUri.length() == 0) {
            Log.warning(LOGTAG, "Got fetch contributor for item request with null or empty itemUri");
            return null;
        }
        try {
            cursorQuery = readOnlyDatabase.query("ItemContributors LEFT OUTER JOIN Contributors ON ItemContributors.contributor_asin=Contributors.asin", JOINED_ITEM_PROJECTION, "ItemContributors.item_uri=? AND ItemContributors.default_contrib_flag=?", new String[]{itemUri, "1"}, null, null, null);
            while (cursorQuery != null) {
                try {
                    if (!cursorQuery.moveToNext()) {
                        break;
                    }
                    contributorCopyFrom = new Contributor().copyFrom(cursorQuery);
                } catch (Throwable th2) {
                    th = th2;
                    DbUtil.closeCursor(cursorQuery);
                    throw th;
                }
            }
            DbUtil.closeCursor(cursorQuery);
            return contributorCopyFrom;
        } catch (Throwable th3) {
            th = th3;
            cursorQuery = null;
        }
    }

    public static List<ItemContributor> fetchItemsForContributor(String contributorAsin) {
        SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(AmazonApplication.getContext());
        Cursor cursorQuery = null;
        if (contributorAsin == null || contributorAsin.length() == 0) {
            Log.warning(LOGTAG, "Got fetch items for contributor request with null or empty asin");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            cursorQuery = readOnlyDatabase.query("ItemContributors", ITEM_CONTRIBUTOR_PROJECTION, "contributor_asin=?", new String[]{contributorAsin}, null, null, null);
            while (cursorQuery != null) {
                if (!cursorQuery.moveToNext()) {
                    break;
                }
                ItemContributor itemContributor = new ItemContributor();
                itemContributor.copyFrom(cursorQuery);
                arrayList.add(itemContributor);
            }
            return arrayList;
        } finally {
            DbUtil.closeCursor(cursorQuery);
        }
    }
}
