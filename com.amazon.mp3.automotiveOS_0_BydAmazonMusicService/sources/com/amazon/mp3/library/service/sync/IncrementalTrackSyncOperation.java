package com.amazon.mp3.library.service.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.core.app.NotificationCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.item.TrackStatus;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.TrackDataUtil;
import com.amazon.mp3.library.sync.FullSyncResponseProcessor;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.net.AbstractHttpClient;
import com.amazon.mp3.net.cirrus.CirrusV3Request;
import com.amazon.mp3.net.task.RetrieveContributorImagesTask;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
class IncrementalTrackSyncOperation extends TrackSyncOperation {
    private static final String TAG = "IncrementalTrackSyncOperation";
    private int mEstimated;
    private boolean mForceUpdate;
    private int mProcessed;
    private TrackDataUtil mTrackDataUtil;
    private int mUpdated;

    IncrementalTrackSyncOperation(Context context, SQLiteDatabase db) {
        super(context, db);
        this.mEstimated = Integer.MIN_VALUE;
        this.mProcessed = 0;
        this.mUpdated = 0;
        this.mForceUpdate = SettingsUtil.shouldForceIncrementalUpdate(context);
        this.mTrackDataUtil = new TrackDataUtil(db);
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int sync() {
        if (!AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            return 0;
        }
        Profiler.begin("Incremental track sync operation");
        int iSyncUpdates = syncUpdates();
        if (iSyncUpdates == 0) {
            SettingsUtil.setShouldForceIncrementalUpdate(getContext(), false);
        }
        Profiler.end();
        return iSyncUpdates;
    }

    private JSONObject createSyncRequestArguments(String checkpoint) throws JSONException {
        JSONObject jSONObjectPut = new JSONObject().put("format", "CSV_1_0");
        if (checkpoint != null) {
            jSONObjectPut.put("checkpoint", checkpoint);
        }
        return jSONObjectPut;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int getProgress() {
        int i = this.mEstimated;
        if (i == Integer.MIN_VALUE || i == 0) {
            return 0;
        }
        return (this.mProcessed * 100) / i;
    }

    @Override // com.amazon.mp3.library.service.sync.SyncOperation
    int getUpdatedResourceCount() {
        return this.mUpdated;
    }

    private int syncUpdates() {
        HashSet hashSet;
        HashSet hashSet2;
        String syncLastCheckpoint = SyncService.getSyncLastCheckpoint(getContext());
        char c = 0;
        if (syncLastCheckpoint == null) {
            return 0;
        }
        try {
            JSONObject jSONObjectExecute = CirrusV3Request.Sync.execute(createSyncRequestArguments(syncLastCheckpoint));
            IncrementalSyncResponse incrementalSyncResponseInflateFromJSON = IncrementalSyncResponse.inflateFromJSON(jSONObjectExecute);
            if (syncLastCheckpoint.equals(incrementalSyncResponseInflateFromJSON.mCheckpoint)) {
                if (this.mUpdated > 0) {
                    updateCirrusSmartPlaylist();
                    PlaylistUtil.refreshPlaylistArt(MediaProvider.SmartPlaylists.getContentUri("cirrus", 0L));
                    PlaylistUtil.refreshPlaylistArt(MediaProvider.SmartPlaylists.getContentUri("cirrus", 1L));
                }
                return 0;
            }
            int i = 1;
            if (jSONObjectExecute.has("snapshotURL")) {
                Log.warning(TAG, "Starting full sync because of snapshotURL present in the response");
                this.mUpdated = new FullSyncResponseProcessor(getContext(), getDatabase()).process(jSONObjectExecute);
                SyncService.setLastCloudSyncTime(getContext(), new Date().getTime());
                SyncService.setSyncLastCheckpoint(getContext(), incrementalSyncResponseInflateFromJSON.mCheckpoint);
                return 1;
            }
            ArrayList<SyncUpdate> arrayList = incrementalSyncResponseInflateFromJSON.mUpdateList;
            HashSet hashSet3 = new HashSet();
            HashSet hashSet4 = new HashSet();
            HashSet hashSet5 = new HashSet();
            beginTransaction(TAG);
            try {
                ContributorAccessObject.ContributorData contributorData = new ContributorAccessObject.ContributorData();
                for (SyncUpdate syncUpdate : arrayList) {
                    if (syncUpdate.mUpdateType == UpdateType.DELETE) {
                        String str = TAG;
                        Object[] objArr = new Object[i];
                        objArr[c] = syncUpdate.mTrackId;
                        Log.debug(str, "receiving delete updateType, removing track %s...", objArr);
                        this.mUpdated += this.mTrackDataUtil.removeTrack(getContext(), syncUpdate.mTrackId);
                    } else {
                        JSONObject jSONObject = syncUpdate.mTrackMetadata;
                        if (jSONObject != null) {
                            ContentValues contentValues = getContentValues(jSONObject);
                            if (TrackStatus.isAvailable(contentValues.getAsString(NotificationCompat.CATEGORY_STATUS))) {
                                String asString = contentValues.getAsString("album_id");
                                String asString2 = contentValues.getAsString("artist");
                                String asString3 = contentValues.getAsString("album_artist_id");
                                hashSet = hashSet5;
                                hashSet2 = hashSet4;
                                updateContributorData(contributorData, asString, asString3, contentValues.getAsString("album_artist"), asString3, asString2, syncUpdate.mTrackMetadata);
                                if (this.mTrackDataUtil.updateOrInsertTrack(getContext(), contentValues, this.mForceUpdate) == TrackDataUtil.Action.UPDATE) {
                                    hashSet3.add(contentValues.getAsString("album_id"));
                                    hashSet2.add(contentValues.getAsString("asin"));
                                } else {
                                    hashSet.add(contentValues.getAsString("album_id"));
                                }
                                this.mUpdated++;
                            } else {
                                hashSet = hashSet5;
                                hashSet2 = hashSet4;
                                Log.debug(TAG, "track has become unavailable, removing track %s...", syncUpdate.mTrackId);
                                this.mUpdated += this.mTrackDataUtil.removeTrack(getContext(), syncUpdate.mTrackId);
                            }
                        }
                        hashSet5 = hashSet;
                        hashSet4 = hashSet2;
                        c = 0;
                        i = 1;
                    }
                    hashSet = hashSet5;
                    hashSet2 = hashSet4;
                    hashSet5 = hashSet;
                    hashSet4 = hashSet2;
                    c = 0;
                    i = 1;
                }
                Collection<String> collection = hashSet5;
                HashSet hashSet6 = hashSet4;
                AmazonApplication.getNetExecutionController().submitTask(new RetrieveContributorImagesTask(Collections.singletonList(contributorData)));
                SyncService.setLastCloudSyncTime(getContext(), new Date().getTime());
                SyncService.setSyncLastCheckpoint(getContext(), incrementalSyncResponseInflateFromJSON.mCheckpoint);
                this.mProcessed += incrementalSyncResponseInflateFromJSON.mUpdateList.size();
                TrackSyncUtil.updateDownloadedContentMetadata(getContext(), getDatabase(), hashSet6);
                endTransaction(TAG, true);
                prefetch((Collection<String>) hashSet3, false);
                prefetch(collection, false);
                return 1;
            } catch (Throwable th) {
                endTransaction(TAG, false);
                throw th;
            }
        } catch (AbstractHttpClient.IncompleteResultException e) {
            Log.error(TAG, "Incomplete Result.", e);
            return 3;
        } catch (AbstractHttpClient.HttpClientException e2) {
            Log.error(TAG, "Network Error.", e2);
            return 2;
        } catch (ParseException e3) {
            e = e3;
            Log.error(TAG, "Invalid Response.", e);
            return 3;
        } catch (JSONException e4) {
            e = e4;
            Log.error(TAG, "Invalid Response.", e);
            return 3;
        } catch (Exception e5) {
            Log.error(TAG, "Exception during incremental sync.", e5);
            return 3;
        }
    }
}
