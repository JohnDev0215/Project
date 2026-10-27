package com.android.inputmethod.dictionarypack;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.inputmethod.latin.BinaryDictionaryFileDumper;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.LocaleUtils;
import com.android.inputmethod.latin.utils.ApplicationUtils;
import com.android.inputmethod.latin.utils.DebugLogUtils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/* JADX INFO: loaded from: classes2.dex */
public final class ActionBatch {
    private final Queue<Action> mActions = new LinkedList();

    public interface Action {
        void execute(final Context context);
    }

    public static final class StartDownloadAction implements Action {
        static final String TAG = "DictionaryProvider:" + StartDownloadAction.class.getSimpleName();
        private final String mClientId;
        final WordListMetadata mWordList;

        public StartDownloadAction(final String clientId, final WordListMetadata wordList) {
            DebugLogUtils.l("New download action for client ", clientId, " : ", wordList);
            this.mClientId = clientId;
            this.mWordList = wordList;
        }

        @Override // com.android.inputmethod.dictionarypack.ActionBatch.Action
        public void execute(final Context context) {
            if (this.mWordList == null) {
                Log.e(TAG, "UpdateAction with a null parameter!");
                return;
            }
            DebugLogUtils.l("Downloading word list");
            SQLiteDatabase db = MetadataDbHelper.getDb(context, this.mClientId);
            ContentValues contentValuesByWordListId = MetadataDbHelper.getContentValuesByWordListId(db, this.mWordList.mId, this.mWordList.mVersion);
            int iIntValue = contentValuesByWordListId.getAsInteger("status").intValue();
            DownloadManagerWrapper downloadManagerWrapper = new DownloadManagerWrapper(context);
            if (2 == iIntValue) {
                downloadManagerWrapper.remove(contentValuesByWordListId.getAsLong(MetadataDbHelper.PENDINGID_COLUMN).longValue());
                MetadataDbHelper.markEntryAsAvailable(db, this.mWordList.mId, this.mWordList.mVersion);
            } else if (1 != iIntValue && 6 != iIntValue) {
                Log.e(TAG, "Unexpected state of the word list '" + this.mWordList.mId + "' : " + iIntValue + " for an upgrade action. Fall back to download.");
            }
            DebugLogUtils.l("Upgrade word list, downloading", this.mWordList.mRemoteFilename);
            Uri uri = Uri.parse(this.mWordList.mRemoteFilename + ("#" + System.currentTimeMillis() + ApplicationUtils.getVersionName(context) + ".dict"));
            DownloadManager.Request request = new DownloadManager.Request(uri);
            Resources resources = context.getResources();
            request.setAllowedNetworkTypes(3);
            request.setTitle(this.mWordList.mDescription);
            request.setNotificationVisibility(2);
            request.setVisibleInDownloadsUi(resources.getBoolean(R.bool.dict_downloads_visible_in_download_UI));
            long jRegisterDownloadRequest = UpdateHandler.registerDownloadRequest(downloadManagerWrapper, request, db, this.mWordList.mId, this.mWordList.mVersion);
            Log.i(TAG, String.format("Starting the dictionary download with version: %d and Url: %s", Integer.valueOf(this.mWordList.mVersion), uri));
            DebugLogUtils.l("Starting download of", uri, "with id", Long.valueOf(jRegisterDownloadRequest));
            PrivateLog.log("Starting download of " + uri + ", id : " + jRegisterDownloadRequest);
        }
    }

    public static final class InstallAfterDownloadAction implements Action {
        static final String TAG = "DictionaryProvider:" + InstallAfterDownloadAction.class.getSimpleName();
        private final String mClientId;
        final ContentValues mWordListValues;

        public InstallAfterDownloadAction(final String clientId, final ContentValues wordListValues) {
            DebugLogUtils.l("New InstallAfterDownloadAction for client ", clientId, " : ", wordListValues);
            this.mClientId = clientId;
            this.mWordListValues = wordListValues;
        }

        @Override // com.android.inputmethod.dictionarypack.ActionBatch.Action
        public void execute(final Context context) {
            ContentValues contentValues = this.mWordListValues;
            if (contentValues == null) {
                Log.e(TAG, "InstallAfterDownloadAction with a null parameter!");
                return;
            }
            int iIntValue = contentValues.getAsInteger("status").intValue();
            if (2 != iIntValue) {
                Log.e(TAG, "Unexpected state of the word list '" + this.mWordListValues.getAsString(MetadataDbHelper.WORDLISTID_COLUMN) + "' : " + iIntValue + " for an InstallAfterDownload action. Bailing out.");
                return;
            }
            DebugLogUtils.l("Setting word list as installed");
            MetadataDbHelper.markEntryAsFinishedDownloadingAndInstalled(MetadataDbHelper.getDb(context, this.mClientId), this.mWordListValues);
            BinaryDictionaryFileDumper.installDictToStagingFromContentProvider(LocaleUtils.constructLocaleFromString(this.mWordListValues.getAsString("locale")), context, false);
        }
    }

    public static final class EnableAction implements Action {
        static final String TAG = "DictionaryProvider:" + EnableAction.class.getSimpleName();
        private final String mClientId;
        final WordListMetadata mWordList;

        public EnableAction(final String clientId, final WordListMetadata wordList) {
            DebugLogUtils.l("New EnableAction for client ", clientId, " : ", wordList);
            this.mClientId = clientId;
            this.mWordList = wordList;
        }

        @Override // com.android.inputmethod.dictionarypack.ActionBatch.Action
        public void execute(final Context context) {
            if (this.mWordList == null) {
                Log.e(TAG, "EnableAction with a null parameter!");
                return;
            }
            DebugLogUtils.l("Enabling word list");
            SQLiteDatabase db = MetadataDbHelper.getDb(context, this.mClientId);
            int iIntValue = MetadataDbHelper.getContentValuesByWordListId(db, this.mWordList.mId, this.mWordList.mVersion).getAsInteger("status").intValue();
            if (4 != iIntValue && 5 != iIntValue) {
                Log.e(TAG, "Unexpected state of the word list '" + this.mWordList.mId + " : " + iIntValue + " for an enable action. Cancelling");
            } else {
                MetadataDbHelper.markEntryAsEnabled(db, this.mWordList.mId, this.mWordList.mVersion);
            }
        }
    }

    public static final class DisableAction implements Action {
        static final String TAG = "DictionaryProvider:" + DisableAction.class.getSimpleName();
        private final String mClientId;
        final WordListMetadata mWordList;

        public DisableAction(final String clientId, final WordListMetadata wordlist) {
            DebugLogUtils.l("New Disable action for client ", clientId, " : ", wordlist);
            this.mClientId = clientId;
            this.mWordList = wordlist;
        }

        @Override // com.android.inputmethod.dictionarypack.ActionBatch.Action
        public void execute(final Context context) {
            if (this.mWordList == null) {
                Log.e(TAG, "DisableAction with a null word list!");
                return;
            }
            DebugLogUtils.l("Disabling word list : " + this.mWordList);
            SQLiteDatabase db = MetadataDbHelper.getDb(context, this.mClientId);
            ContentValues contentValuesByWordListId = MetadataDbHelper.getContentValuesByWordListId(db, this.mWordList.mId, this.mWordList.mVersion);
            int iIntValue = contentValuesByWordListId.getAsInteger("status").intValue();
            if (3 == iIntValue) {
                MetadataDbHelper.markEntryAsDisabled(db, this.mWordList.mId, this.mWordList.mVersion);
                return;
            }
            if (2 != iIntValue) {
                Log.e(TAG, "Unexpected state of the word list '" + this.mWordList.mId + "' : " + iIntValue + " for a disable action. Fall back to marking as available.");
            }
            new DownloadManagerWrapper(context).remove(contentValuesByWordListId.getAsLong(MetadataDbHelper.PENDINGID_COLUMN).longValue());
            MetadataDbHelper.markEntryAsAvailable(db, this.mWordList.mId, this.mWordList.mVersion);
        }
    }

    public static final class MakeAvailableAction implements Action {
        static final String TAG = "DictionaryProvider:" + MakeAvailableAction.class.getSimpleName();
        private final String mClientId;
        final WordListMetadata mWordList;

        public MakeAvailableAction(final String clientId, final WordListMetadata wordlist) {
            DebugLogUtils.l("New MakeAvailable action", clientId, " : ", wordlist);
            this.mClientId = clientId;
            this.mWordList = wordlist;
        }

        @Override // com.android.inputmethod.dictionarypack.ActionBatch.Action
        public void execute(final Context context) {
            if (this.mWordList == null) {
                Log.e(TAG, "MakeAvailableAction with a null word list!");
                return;
            }
            SQLiteDatabase db = MetadataDbHelper.getDb(context, this.mClientId);
            if (MetadataDbHelper.getContentValuesByWordListId(db, this.mWordList.mId, this.mWordList.mVersion) != null) {
                Log.e(TAG, "Unexpected state of the word list '" + this.mWordList.mId + "'  for a makeavailable action. Marking as available anyway.");
            }
            DebugLogUtils.l("Making word list available : " + this.mWordList);
            ContentValues contentValuesMakeContentValues = MetadataDbHelper.makeContentValues(0, 2, 1, this.mWordList.mId, this.mWordList.mLocale, this.mWordList.mDescription, this.mWordList.mLocalFilename == null ? "" : this.mWordList.mLocalFilename, this.mWordList.mRemoteFilename, this.mWordList.mLastUpdate, this.mWordList.mRawChecksum, this.mWordList.mChecksum, this.mWordList.mRetryCount, this.mWordList.mFileSize, this.mWordList.mVersion, this.mWordList.mFormatVersion);
            PrivateLog.log("Insert 'available' record for " + this.mWordList.mDescription + " and locale " + this.mWordList.mLocale);
            db.insert("pendingUpdates", null, contentValuesMakeContentValues);
        }
    }

    public static final class MarkPreInstalledAction implements Action {
        static final String TAG = "DictionaryProvider:" + MarkPreInstalledAction.class.getSimpleName();
        private final String mClientId;
        final WordListMetadata mWordList;

        public MarkPreInstalledAction(final String clientId, final WordListMetadata wordlist) {
            DebugLogUtils.l("New MarkPreInstalled action", clientId, " : ", wordlist);
            this.mClientId = clientId;
            this.mWordList = wordlist;
        }

        @Override // com.android.inputmethod.dictionarypack.ActionBatch.Action
        public void execute(final Context context) {
            if (this.mWordList == null) {
                Log.e(TAG, "MarkPreInstalledAction with a null word list!");
                return;
            }
            SQLiteDatabase db = MetadataDbHelper.getDb(context, this.mClientId);
            if (MetadataDbHelper.getContentValuesByWordListId(db, this.mWordList.mId, this.mWordList.mVersion) != null) {
                Log.e(TAG, "Unexpected state of the word list '" + this.mWordList.mId + "'  for a markpreinstalled action. Marking as preinstalled anyway.");
            }
            DebugLogUtils.l("Marking word list preinstalled : " + this.mWordList);
            ContentValues contentValuesMakeContentValues = MetadataDbHelper.makeContentValues(0, 2, 3, this.mWordList.mId, this.mWordList.mLocale, this.mWordList.mDescription, TextUtils.isEmpty(this.mWordList.mLocalFilename) ? "" : this.mWordList.mLocalFilename, this.mWordList.mRemoteFilename, this.mWordList.mLastUpdate, this.mWordList.mRawChecksum, this.mWordList.mChecksum, this.mWordList.mRetryCount, this.mWordList.mFileSize, this.mWordList.mVersion, this.mWordList.mFormatVersion);
            PrivateLog.log("Insert 'preinstalled' record for " + this.mWordList.mDescription + " and locale " + this.mWordList.mLocale);
            db.insert("pendingUpdates", null, contentValuesMakeContentValues);
        }
    }

    public static final class UpdateDataAction implements Action {
        static final String TAG = "DictionaryProvider:" + UpdateDataAction.class.getSimpleName();
        private final String mClientId;
        final WordListMetadata mWordList;

        public UpdateDataAction(final String clientId, final WordListMetadata wordlist) {
            DebugLogUtils.l("New UpdateData action for client ", clientId, " : ", wordlist);
            this.mClientId = clientId;
            this.mWordList = wordlist;
        }

        @Override // com.android.inputmethod.dictionarypack.ActionBatch.Action
        public void execute(final Context context) {
            if (this.mWordList == null) {
                Log.e(TAG, "UpdateDataAction with a null word list!");
                return;
            }
            SQLiteDatabase db = MetadataDbHelper.getDb(context, this.mClientId);
            ContentValues contentValuesByWordListId = MetadataDbHelper.getContentValuesByWordListId(db, this.mWordList.mId, this.mWordList.mVersion);
            if (contentValuesByWordListId == null) {
                Log.e(TAG, "Trying to update data about a non-existing word list. Bailing out.");
                return;
            }
            DebugLogUtils.l("Updating data about a word list : " + this.mWordList);
            ContentValues contentValuesMakeContentValues = MetadataDbHelper.makeContentValues(contentValuesByWordListId.getAsInteger(MetadataDbHelper.PENDINGID_COLUMN).intValue(), contentValuesByWordListId.getAsInteger(MetadataDbHelper.TYPE_COLUMN).intValue(), contentValuesByWordListId.getAsInteger("status").intValue(), this.mWordList.mId, this.mWordList.mLocale, this.mWordList.mDescription, contentValuesByWordListId.getAsString(MetadataDbHelper.LOCAL_FILENAME_COLUMN), this.mWordList.mRemoteFilename, this.mWordList.mLastUpdate, this.mWordList.mRawChecksum, this.mWordList.mChecksum, this.mWordList.mRetryCount, this.mWordList.mFileSize, this.mWordList.mVersion, this.mWordList.mFormatVersion);
            PrivateLog.log("Updating record for " + this.mWordList.mDescription + " and locale " + this.mWordList.mLocale);
            db.update("pendingUpdates", contentValuesMakeContentValues, "id = ? AND version = ?", new String[]{this.mWordList.mId, Integer.toString(this.mWordList.mVersion)});
        }
    }

    public static final class ForgetAction implements Action {
        static final String TAG = "DictionaryProvider:" + ForgetAction.class.getSimpleName();
        private final String mClientId;
        final boolean mHasNewerVersion;
        final WordListMetadata mWordList;

        public ForgetAction(final String clientId, final WordListMetadata wordlist, final boolean hasNewerVersion) {
            DebugLogUtils.l("New TryRemove action for client ", clientId, " : ", wordlist);
            this.mClientId = clientId;
            this.mWordList = wordlist;
            this.mHasNewerVersion = hasNewerVersion;
        }

        @Override // com.android.inputmethod.dictionarypack.ActionBatch.Action
        public void execute(final Context context) {
            if (this.mWordList == null) {
                Log.e(TAG, "TryRemoveAction with a null word list!");
                return;
            }
            DebugLogUtils.l("Trying to remove word list : " + this.mWordList);
            SQLiteDatabase db = MetadataDbHelper.getDb(context, this.mClientId);
            ContentValues contentValuesByWordListId = MetadataDbHelper.getContentValuesByWordListId(db, this.mWordList.mId, this.mWordList.mVersion);
            if (contentValuesByWordListId == null) {
                Log.e(TAG, "Trying to update the metadata of a non-existing wordlist. Cancelling.");
                return;
            }
            int iIntValue = contentValuesByWordListId.getAsInteger("status").intValue();
            if (this.mHasNewerVersion && 1 != iIntValue) {
                Log.e(TAG, "Unexpected status for forgetting a word list info : " + iIntValue + ", removing URL to prevent re-download");
            }
            if (3 == iIntValue || 4 == iIntValue || 5 == iIntValue) {
                contentValuesByWordListId.put(MetadataDbHelper.REMOTE_FILENAME_COLUMN, "");
                contentValuesByWordListId.put("status", (Integer) 5);
                db.update("pendingUpdates", contentValuesByWordListId, "id = ? AND version = ?", new String[]{this.mWordList.mId, Integer.toString(this.mWordList.mVersion)});
                return;
            }
            db.delete("pendingUpdates", "id = ? AND version = ?", new String[]{this.mWordList.mId, Integer.toString(this.mWordList.mVersion)});
        }
    }

    public static final class StartDeleteAction implements Action {
        static final String TAG = "DictionaryProvider:" + StartDeleteAction.class.getSimpleName();
        private final String mClientId;
        final WordListMetadata mWordList;

        public StartDeleteAction(final String clientId, final WordListMetadata wordlist) {
            DebugLogUtils.l("New StartDelete action for client ", clientId, " : ", wordlist);
            this.mClientId = clientId;
            this.mWordList = wordlist;
        }

        @Override // com.android.inputmethod.dictionarypack.ActionBatch.Action
        public void execute(final Context context) {
            if (this.mWordList == null) {
                Log.e(TAG, "StartDeleteAction with a null word list!");
                return;
            }
            DebugLogUtils.l("Trying to delete word list : " + this.mWordList);
            SQLiteDatabase db = MetadataDbHelper.getDb(context, this.mClientId);
            ContentValues contentValuesByWordListId = MetadataDbHelper.getContentValuesByWordListId(db, this.mWordList.mId, this.mWordList.mVersion);
            if (contentValuesByWordListId == null) {
                Log.e(TAG, "Trying to set a non-existing wordlist for removal. Cancelling.");
                return;
            }
            int iIntValue = contentValuesByWordListId.getAsInteger("status").intValue();
            if (4 != iIntValue) {
                Log.e(TAG, "Unexpected status for deleting a word list info : " + iIntValue);
            }
            MetadataDbHelper.markEntryAsDeleting(db, this.mWordList.mId, this.mWordList.mVersion);
        }
    }

    public static final class FinishDeleteAction implements Action {
        static final String TAG = "DictionaryProvider:" + FinishDeleteAction.class.getSimpleName();
        private final String mClientId;
        final WordListMetadata mWordList;

        public FinishDeleteAction(final String clientId, final WordListMetadata wordlist) {
            DebugLogUtils.l("New FinishDelete action for client", clientId, " : ", wordlist);
            this.mClientId = clientId;
            this.mWordList = wordlist;
        }

        @Override // com.android.inputmethod.dictionarypack.ActionBatch.Action
        public void execute(final Context context) {
            if (this.mWordList == null) {
                Log.e(TAG, "FinishDeleteAction with a null word list!");
                return;
            }
            DebugLogUtils.l("Trying to delete word list : " + this.mWordList);
            SQLiteDatabase db = MetadataDbHelper.getDb(context, this.mClientId);
            ContentValues contentValuesByWordListId = MetadataDbHelper.getContentValuesByWordListId(db, this.mWordList.mId, this.mWordList.mVersion);
            if (contentValuesByWordListId == null) {
                Log.e(TAG, "Trying to set a non-existing wordlist for removal. Cancelling.");
                return;
            }
            int iIntValue = contentValuesByWordListId.getAsInteger("status").intValue();
            if (5 != iIntValue) {
                Log.e(TAG, "Unexpected status for finish-deleting a word list info : " + iIntValue);
            }
            if (TextUtils.isEmpty(contentValuesByWordListId.getAsString(MetadataDbHelper.REMOTE_FILENAME_COLUMN))) {
                db.delete("pendingUpdates", "id = ? AND version = ?", new String[]{this.mWordList.mId, Integer.toString(this.mWordList.mVersion)});
            } else {
                MetadataDbHelper.markEntryAsAvailable(db, this.mWordList.mId, this.mWordList.mVersion);
            }
        }
    }

    public void add(final Action a) {
        this.mActions.add(a);
    }

    public void append(final ActionBatch that) {
        Iterator<Action> it = that.mActions.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    public void execute(final Context context, final ProblemReporter reporter) {
        DebugLogUtils.l("Executing a batch of actions");
        Queue<Action> queue = this.mActions;
        while (!queue.isEmpty()) {
            try {
                queue.poll().execute(context);
            } catch (Exception e) {
                if (reporter != null) {
                    reporter.report(e);
                }
            }
        }
    }
}
