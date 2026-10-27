package com.amazon.mp3.prime.cta;

import UpsellInterface.v1_0.UpsellType;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.account.details.AccountDetailUtil;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.capability.FeatureFlagsProvider;
import com.amazon.mp3.download.generator.LocalLuidGenerator;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabaseUtil;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.net.cirrus.CirrusV3Request;
import com.amazon.mp3.net.service.ServiceException;
import com.amazon.mp3.net.service.ServiceRequest;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistUtil;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.subscription.BrowseMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class AddRemovePrimeManager {
    private static final String TAG = "AddRemovePrimeManager";
    private static AddRemovePrimeManager sInstance;
    private Context mContext;
    private final Object mSyncAddObj = new Object();
    private final Object mSyncRemoveObj = new Object();
    private Set<String> mTracksToAdd = readTracks("AddRemovePrimeManagerKey_AddAsins");
    private Set<String> mTracksToRemove = readTracks("AddRemovePrimeManagerKey_RemoveAsins");
    private ServiceRequest mInsertTracksByAsinServiceRequest = CirrusV3Request.InsertTracksByAsin;
    private ServiceRequest mDeleteTracksServiceRequest = CirrusV3Request.DeleteTracks;

    public static synchronized AddRemovePrimeManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AddRemovePrimeManager(context);
        }
        return sInstance;
    }

    private AddRemovePrimeManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public synchronized void queueTrackToAdd(String trackAsin) {
        HashSet hashSet = new HashSet();
        hashSet.add(trackAsin);
        queueTracksToAdd(hashSet);
    }

    public synchronized void queueTracksToAdd(final Collection<String> tracks) {
        if (tracks.isEmpty()) {
            return;
        }
        removePendingTracksToRemove(tracks);
        this.mTracksToAdd.addAll(tracks);
        writeTracks("AddRemovePrimeManagerKey_AddAsins", this.mTracksToAdd);
    }

    public synchronized Collection<String> getTracksToAdd() {
        return this.mTracksToAdd;
    }

    public synchronized boolean isQueuedToAdd(String asin) {
        return this.mTracksToAdd.contains(asin);
    }

    public synchronized Collection<String> getTracksToRemove() {
        return this.mTracksToRemove;
    }

    public synchronized boolean isAddQueueEmpty() {
        return this.mTracksToAdd.isEmpty();
    }

    public synchronized boolean isRemoveQueueEmpty() {
        return this.mTracksToRemove.isEmpty();
    }

    public synchronized void queueTrackToRemove(String trackAsin) {
        if (this.mTracksToAdd.remove(trackAsin)) {
            writeTracks("AddRemovePrimeManagerKey_AddAsins", this.mTracksToAdd);
        } else {
            this.mTracksToRemove.add(trackAsin);
            writeTracks("AddRemovePrimeManagerKey_RemoveAsins", this.mTracksToRemove);
        }
    }

    public Set<String> syncAdds() throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        HashSet<String> hashSet;
        synchronized (this.mSyncAddObj) {
            synchronized (this) {
                hashSet = new HashSet(this.mTracksToAdd);
            }
            Set<String> hashSet2 = new HashSet<>();
            if (hashSet.isEmpty()) {
                return hashSet2;
            }
            try {
                hashSet2 = syncAdds(hashSet);
            } catch (ServiceException e) {
                Log.error(TAG, "Adding all prime tracks failed due to Service Exception.  Adding individually.", e);
                HashSet hashSet3 = new HashSet();
                for (String str : hashSet) {
                    hashSet3.clear();
                    hashSet3.add(str);
                    try {
                        hashSet2.addAll(syncAdds(hashSet3));
                    } catch (ServiceException unused) {
                        Log.error(TAG, "Adding prime track " + str + " failed. Removing the track from the pending tracks to add", e);
                        removePendingTracksToAdd(hashSet3);
                        hashSet2.add(str);
                    }
                }
            }
            return hashSet2;
        }
    }

    private Set<String> syncAdds(final Collection<String> tracksToAdd) throws ServiceException, AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException {
        String str;
        JSONArray jSONArray = new JSONArray((Collection<?>) tracksToAdd);
        JSONObject jSONObject = new JSONObject();
        HashSet hashSet = new HashSet();
        try {
            BrowseMode.Mode browseMode = new BrowseMode(AccountManagerSingleton.get(), FeatureFlagsProvider.getFeatureFlagProvider()).getBrowseMode();
            if (browseMode.equals(BrowseMode.Mode.PRIME)) {
                str = UpsellType.PRIME;
            } else {
                str = browseMode.equals(BrowseMode.Mode.SONIC_RUSH) ? "SONIC_RUSH" : "HAWKFIRE";
            }
            jSONObject.put("mode", str);
            jSONObject.put(Splash.PARAMS_MUSIC_TERRITORY, AccountDetailUtil.getMusicTerritoryOfResidence());
            jSONObject.put("asinList", jSONArray);
            for (String str2 : InsertTracksByAsinResponse.fromJson(this.mInsertTracksByAsinServiceRequest.execute(jSONObject)).getIneligibleAsins()) {
                if (!tracksToAdd.contains(str2)) {
                    Log.warning(TAG, "API unexpectedly returned an ASIN that we didn't try to add: " + str2);
                } else {
                    hashSet.add(str2);
                }
            }
        } catch (JSONException e) {
            Log.error(TAG, "JSONException while trying to sync prime adds", e);
        }
        removePendingTracksToAdd(tracksToAdd);
        return hashSet;
    }

    public Set<String> syncRemoves(SQLiteDatabase db) throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, CirrusExceptions.CirrusException {
        HashSet hashSet;
        Set<String> setOnMetadataNotFoundException;
        Set<String> set;
        synchronized (this.mSyncRemoveObj) {
            synchronized (this) {
                hashSet = new HashSet(this.mTracksToRemove);
            }
            Set<String> hashSet2 = new HashSet<>();
            if (hashSet.isEmpty()) {
                return hashSet2;
            }
            Cursor cursorQueryLuids = queryLuids(db, hashSet);
            int columnIndex = cursorQueryLuids.getColumnIndex("luid");
            int columnIndex2 = cursorQueryLuids.getColumnIndex("asin");
            int columnIndexOrThrow = cursorQueryLuids.getColumnIndexOrThrow("ownership_status");
            int columnIndexOrThrow2 = cursorQueryLuids.getColumnIndexOrThrow("prime_status");
            JSONArray jSONArray = new JSONArray();
            List<String> arrayList = new ArrayList<>();
            while (cursorQueryLuids.moveToNext()) {
                String string = cursorQueryLuids.getString(columnIndex);
                String string2 = cursorQueryLuids.getString(columnIndex2);
                int i = cursorQueryLuids.getInt(columnIndexOrThrow);
                int i2 = cursorQueryLuids.getInt(columnIndexOrThrow2);
                if (TextUtils.isEmpty(string) || !LocalLuidGenerator.isLocalLuid(string)) {
                    if (i < 200) {
                        removePendingTracksToRemove(string2);
                        emitFlexMethodForAttemptedOwnedTrackDeletion(string2);
                        Log.warning(TAG, "Attempting to delete owned track in AddRemovePrimeManager.  This is almost certainly a bug.  asin: " + string2 + ", luid: " + string);
                    } else {
                        if (TextUtils.isEmpty(string) || IdGenerator.isPrimeLuid(string)) {
                            set = hashSet2;
                            Log.debug(TAG, "Track '%s' doesn't have a luid yet. Keep it in the remove queue.", string2);
                            hashSet.remove(string2);
                        } else {
                            if (!PrimePlaylistUtil.isAsin(string)) {
                                jSONArray.put(string);
                            }
                            arrayList.add(string2);
                            String str = TAG;
                            StringBuilder sb = new StringBuilder();
                            set = hashSet2;
                            sb.append("deleteTracks: ");
                            sb.append(string2);
                            sb.append(" ownership status: ");
                            sb.append(ContentOwnershipStatus.fromValue(i));
                            sb.append(" catalog status: ");
                            sb.append(ContentCatalogStatus.fromValue(i2));
                            Log.warning(str, sb.toString());
                        }
                        hashSet2 = set;
                    }
                }
            }
            Set<String> set2 = hashSet2;
            cursorQueryLuids.close();
            if (jSONArray.length() > 0) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("trackIdList", jSONArray);
                    Log.warning(TAG, "deleteTracks called - syncRemoves");
                    this.mDeleteTracksServiceRequest.execute(jSONObject);
                    removePendingTracksToRemove(hashSet);
                } catch (AbstractHttpClient.CanceledException e) {
                    e = e;
                    Log.error(TAG, "Exception when attempting to delete tracks: ", e);
                } catch (AbstractHttpClient.FailedException e2) {
                    e = e2;
                    Log.error(TAG, "Exception when attempting to delete tracks: ", e);
                } catch (AbstractHttpClient.IncompleteResultException e3) {
                    e = e3;
                    Log.error(TAG, "Exception when attempting to delete tracks: ", e);
                } catch (AbstractHttpClient.UnexpectedHttpStatusException e4) {
                    e = e4;
                    Log.error(TAG, "Exception when attempting to delete tracks: ", e);
                } catch (CirrusExceptions.MetadataNotFoundException e5) {
                    Log.warning(TAG, "Removing all prime tracks failed due to ServiceException", e5);
                    setOnMetadataNotFoundException = onMetadataNotFoundException(jSONArray, arrayList);
                } catch (ServiceException e6) {
                    e = e6;
                    Log.error(TAG, "Exception when attempting to delete tracks: ", e);
                } catch (JSONException e7) {
                    Log.warning(TAG, "JsonException while trying to sync prime removes", e7);
                }
                setOnMetadataNotFoundException = set2;
            } else {
                setOnMetadataNotFoundException = set2;
            }
            return setOnMetadataNotFoundException;
        }
    }

    private Cursor queryLuids(SQLiteDatabase db, final Collection<String> trackAsins) {
        DbUtil.WhereClause whereClause = new DbUtil.WhereClause("asin", new HashSet(trackAsins));
        return db.query("Track", new String[]{"luid", "asin", "prime_status", "ownership_status"}, whereClause.getClause(), whereClause.getArgs(), null, null, null);
    }

    private Set<String> onMetadataNotFoundException(JSONArray luids, List<String> asins) throws AbstractHttpClient.FailedException, AbstractHttpClient.CanceledException, AbstractHttpClient.IncompleteResultException, AbstractHttpClient.UnexpectedHttpStatusException, CirrusExceptions.CirrusException {
        HashSet hashSet = new HashSet();
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < luids.length(); i++) {
            String strOptString = luids.optString(i);
            String str = asins.get(i);
            if (strOptString.isEmpty()) {
                Log.info(TAG, "Removing prime track " + str + " failed because luid is empty.");
                hashSet.add(str);
                removePendingTracksToRemove(str);
            } else {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(strOptString);
                try {
                    jSONObject.put("trackIdList", jSONArray);
                    Log.warning(TAG, "deleteTracks called - metadataNotFound");
                    this.mDeleteTracksServiceRequest.execute(jSONObject);
                    removePendingTracksToRemove(str);
                } catch (AbstractHttpClient.CanceledException e) {
                    e = e;
                    Log.error(TAG, "Exception when attempting to delete tracks: ", e);
                } catch (AbstractHttpClient.FailedException e2) {
                    e = e2;
                    Log.error(TAG, "Exception when attempting to delete tracks: ", e);
                } catch (AbstractHttpClient.IncompleteResultException e3) {
                    e = e3;
                    Log.error(TAG, "Exception when attempting to delete tracks: ", e);
                } catch (AbstractHttpClient.UnexpectedHttpStatusException e4) {
                    e = e4;
                    Log.error(TAG, "Exception when attempting to delete tracks: ", e);
                } catch (CirrusExceptions.MetadataNotFoundException e5) {
                    Log.info(TAG, "Removing prime track " + str + " failed: ", e5);
                    hashSet.add(str);
                    removePendingTracksToRemove(str);
                } catch (ServiceException e6) {
                    e = e6;
                    Log.error(TAG, "Exception when attempting to delete tracks: ", e);
                } catch (JSONException unused) {
                    Log.warning(TAG, "JsonException while trying to sync prime removes");
                }
            }
        }
        return hashSet;
    }

    private void emitFlexMethodForAttemptedOwnedTrackDeletion(final String asin) {
        MetricsLogger.sendEvent(FlexEvent.builder("ATTEMPT_TO_DELETE_OWNED_TRACK_3P").withFlexStr1(asin).build());
    }

    @VisibleForTesting
    protected synchronized void removePendingTracksToAdd(Collection<String> tracks) {
        if (this.mTracksToAdd.removeAll(tracks)) {
            writeTracks("AddRemovePrimeManagerKey_AddAsins", this.mTracksToAdd);
        }
    }

    @VisibleForTesting
    protected synchronized void removePendingTracksToRemove(Collection<String> tracks) {
        if (this.mTracksToRemove.removeAll(tracks)) {
            writeTracks("AddRemovePrimeManagerKey_RemoveAsins", this.mTracksToRemove);
        }
    }

    @VisibleForTesting
    protected synchronized void removePendingTracksToRemove(String trackAsin) {
        if (this.mTracksToRemove.remove(trackAsin)) {
            writeTracks("AddRemovePrimeManagerKey_RemoveAsins", this.mTracksToRemove);
        }
    }

    @VisibleForTesting
    protected void writeTracks(String sharedPrefKey, Set<String> asins) {
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(this.mContext).edit();
        editorEdit.putStringSet(sharedPrefKey, asins);
        editorEdit.apply();
    }

    @VisibleForTesting
    protected Set<String> readTracks(String sharedPrefKey) {
        return new HashSet(SettingsUtil.getPrefs(this.mContext).getStringSet(sharedPrefKey, new HashSet()));
    }

    public void removeAllPrimeTracks(Context context, boolean fromCirrus) {
        String str;
        String str2 = TAG;
        Log.info(str2, "delete catalog tracks");
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(context);
        try {
            writableDatabase.beginTransaction();
            if (fromCirrus) {
                str = "prime_status > 175 AND ownership_status > 200";
                Cursor cursorQuery = writableDatabase.query(true, "Track", new String[]{"asin", "ownership_status", "prime_status"}, "prime_status > 175 AND ownership_status > 200", null, null, null, null, null);
                try {
                    if (cursorQuery != null) {
                        int columnIndex = cursorQuery.getColumnIndex("asin");
                        int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("ownership_status");
                        int columnIndexOrThrow2 = cursorQuery.getColumnIndexOrThrow("prime_status");
                        int i = cursorQuery.getInt(columnIndexOrThrow);
                        int i2 = cursorQuery.getInt(columnIndexOrThrow2);
                        while (cursorQuery.moveToNext()) {
                            Log.warning(TAG, "deleteTracks - queueTrackToRemove: " + cursorQuery.getString(columnIndex) + " ownership status: " + ContentOwnershipStatus.fromValue(i) + " catalog status: " + ContentCatalogStatus.fromValue(i2));
                            queueTrackToRemove(cursorQuery.getString(columnIndex));
                        }
                    } else {
                        Log.error(str2, "Cursor not useable");
                    }
                    try {
                        syncRemoves(writableDatabase);
                    } catch (Exception unused) {
                        Log.error(TAG, "Error occurred while syncing removal of prime tracks");
                    }
                } finally {
                    try {
                        cursorQuery.close();
                    } catch (Throwable unused2) {
                    }
                }
            } else {
                str = "prime_status > 175 AND ownership_status > 200";
            }
            writableDatabase.execSQL("DELETE FROM Track WHERE " + str);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            context.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
            CirrusDatabaseUtil.updateAllCachedTrackCounts(context);
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    @VisibleForTesting
    protected static class InsertTracksByAsinResponse {
        private final List<String> mScheduledAsins = new ArrayList();
        private final List<String> mIneligibleAsins = new ArrayList();

        protected InsertTracksByAsinResponse() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static InsertTracksByAsinResponse fromJson(JSONObject json) throws JSONException {
            InsertTracksByAsinResponse insertTracksByAsinResponse = new InsertTracksByAsinResponse();
            JSONArray jSONArrayOptJSONArray = json.optJSONArray("scheduledAsins");
            JSONArray jSONArrayOptJSONArray2 = json.optJSONArray("ineligibleAsins");
            copyJsonArrayToList(jSONArrayOptJSONArray, insertTracksByAsinResponse.getScheduledAsins());
            copyJsonArrayToList(jSONArrayOptJSONArray2, insertTracksByAsinResponse.getIneligibleAsins());
            return insertTracksByAsinResponse;
        }

        private static void copyJsonArrayToList(JSONArray array, List<String> list) throws JSONException {
            if (array == null) {
                return;
            }
            for (int i = 0; i < array.length(); i++) {
                list.add(array.getString(i));
            }
        }

        public List<String> getScheduledAsins() {
            return this.mScheduledAsins;
        }

        public List<String> getIneligibleAsins() {
            return this.mIneligibleAsins;
        }
    }
}
