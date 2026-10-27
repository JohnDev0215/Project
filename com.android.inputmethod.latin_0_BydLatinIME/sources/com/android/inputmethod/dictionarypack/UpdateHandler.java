package com.android.inputmethod.dictionarypack;

import android.app.DownloadManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.android.inputmethod.compat.NotificationCompatUtils;
import com.android.inputmethod.dictionarypack.ActionBatch;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.common.LocaleUtils;
import com.android.inputmethod.latin.utils.ApplicationUtils;
import com.android.inputmethod.latin.utils.DebugLogUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class UpdateHandler {
    private static final boolean DEBUG = false;
    static final int DICT_AVAILABLE_NOTIFICATION_ID = 1;
    private static final String DICT_FILE_SUFFIX = ".dict";
    public static final int DOWNLOAD_OVER_METERED_ALLOWED = 1;
    public static final int DOWNLOAD_OVER_METERED_DISALLOWED = 2;
    private static final String DOWNLOAD_OVER_METERED_SETTING_PREFS_KEY = "downloadOverMetered";
    public static final int DOWNLOAD_OVER_METERED_SETTING_UNKNOWN = 0;
    private static final int FILE_COPY_BUFFER_SIZE = 8192;
    public static final String MAIN_DICTIONARY_CATEGORY = "main";
    public static final int MAXIMUM_SUPPORTED_FORMAT_VERSION = 86736212;
    static final String METADATA_NAME = "metadata";
    static final int METADATA_TYPE = 0;
    public static final int NOT_AN_ID = -1;
    public static final String TEMP_DICT_FILE_SUB = "___";
    static final int WORDLIST_TYPE = 1;
    static final String TAG = "DictionaryProvider:" + UpdateHandler.class.getSimpleName();
    static final Object sSharedIdProtector = new Object();
    private static List<UpdateEventListener> sUpdateEventListeners = Collections.synchronizedList(new LinkedList());

    public interface UpdateEventListener {
        void downloadedMetadata(boolean succeeded);

        void updateCycleCompleted();

        void wordListDownloadFinished(String wordListId, boolean succeeded);
    }

    public static void registerUpdateEventListener(final UpdateEventListener listener) {
        sUpdateEventListeners.add(listener);
    }

    public static void unregisterUpdateEventListener(final UpdateEventListener listener) {
        sUpdateEventListeners.remove(listener);
    }

    private static void writeMetadataDownloadId(final Context context, final String uri, final long downloadId) {
        MetadataDbHelper.registerMetadataDownloadId(context, uri, downloadId);
    }

    public static void setDownloadOverMeteredSetting(final Context context, final boolean shouldDownloadOverMetered) {
        SharedPreferences.Editor editorEdit = CommonPreferences.getCommonPreferences(context).edit();
        editorEdit.putInt(DOWNLOAD_OVER_METERED_SETTING_PREFS_KEY, shouldDownloadOverMetered ? 1 : 2);
        editorEdit.apply();
    }

    public static int getDownloadOverMeteredSetting(final Context context) {
        return CommonPreferences.getCommonPreferences(context).getInt(DOWNLOAD_OVER_METERED_SETTING_PREFS_KEY, 0);
    }

    public static boolean tryUpdate(final Context context) {
        TreeSet<String> treeSet = new TreeSet();
        Cursor cursorQueryClientIds = MetadataDbHelper.queryClientIds(context);
        boolean z = false;
        if (cursorQueryClientIds == null) {
            return false;
        }
        try {
            if (!cursorQueryClientIds.moveToFirst()) {
                return false;
            }
            do {
                String string = cursorQueryClientIds.getString(0);
                String metadataUriAsString = MetadataDbHelper.getMetadataUriAsString(context, string);
                PrivateLog.log("Update for clientId " + DebugLogUtils.s(string));
                DebugLogUtils.l("Update for clientId", string, " which uses URI ", metadataUriAsString);
                treeSet.add(metadataUriAsString);
            } while (cursorQueryClientIds.moveToNext());
            cursorQueryClientIds.close();
            for (String str : treeSet) {
                if (!TextUtils.isEmpty(str)) {
                    updateClientsWithMetadataUri(context, str);
                    z = true;
                }
            }
            return z;
        } finally {
            cursorQueryClientIds.close();
        }
    }

    private static void updateClientsWithMetadataUri(final Context context, final String metadataUri) {
        long jEnqueue;
        String str = TAG;
        Log.i(str, "updateClientsWithMetadataUri() : MetadataUri = " + metadataUri);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(metadataUri + ("#" + System.currentTimeMillis() + ApplicationUtils.getVersionName(context) + ".json")));
        DebugLogUtils.l("Request =", request);
        Resources resources = context.getResources();
        request.setAllowedNetworkTypes(3);
        request.setTitle(resources.getString(R.string.download_description));
        request.setNotificationVisibility(2);
        request.setVisibleInDownloadsUi(resources.getBoolean(R.bool.metadata_downloads_visible_in_download_UI));
        DownloadManagerWrapper downloadManagerWrapper = new DownloadManagerWrapper(context);
        if (maybeCancelUpdateAndReturnIfStillRunning(context, metadataUri, downloadManagerWrapper, DictionaryService.NO_CANCEL_DOWNLOAD_PERIOD_MILLIS)) {
            return;
        }
        synchronized (sSharedIdProtector) {
            jEnqueue = downloadManagerWrapper.enqueue(request);
            DebugLogUtils.l("Metadata download requested with id", Long.valueOf(jEnqueue));
            writeMetadataDownloadId(context, metadataUri, jEnqueue);
        }
        Log.i(str, "updateClientsWithMetadataUri() : DownloadId = " + jEnqueue);
    }

    private static boolean maybeCancelUpdateAndReturnIfStillRunning(final Context context, final String metadataUri, final DownloadManagerWrapper manager, final long graceTime) {
        synchronized (sSharedIdProtector) {
            DownloadIdAndStartDate metadataDownloadIdAndStartDateForURI = MetadataDbHelper.getMetadataDownloadIdAndStartDateForURI(context, metadataUri);
            if (metadataDownloadIdAndStartDateForURI == null) {
                return false;
            }
            if (-1 == metadataDownloadIdAndStartDateForURI.mId) {
                return false;
            }
            if (metadataDownloadIdAndStartDateForURI.mStartDate + graceTime > System.currentTimeMillis()) {
                return true;
            }
            manager.remove(metadataDownloadIdAndStartDateForURI.mId);
            writeMetadataDownloadId(context, metadataUri, -1L);
            Iterator it = linkedCopyOfList(sUpdateEventListeners).iterator();
            while (it.hasNext()) {
                ((UpdateEventListener) it.next()).downloadedMetadata(false);
            }
            return false;
        }
    }

    public static void cancelUpdate(final Context context, final String clientId) {
        maybeCancelUpdateAndReturnIfStillRunning(context, MetadataDbHelper.getMetadataUriAsString(context, clientId), new DownloadManagerWrapper(context), 0L);
    }

    public static long registerDownloadRequest(final DownloadManagerWrapper manager, final DownloadManager.Request request, final SQLiteDatabase db, final String id, final int version) {
        long jEnqueue;
        String str = TAG;
        Log.i(str, "registerDownloadRequest() : Id = " + id + " : Version = " + version);
        synchronized (sSharedIdProtector) {
            jEnqueue = manager.enqueue(request);
            Log.i(str, "registerDownloadRequest() : DownloadId = " + jEnqueue);
            MetadataDbHelper.markEntryAsDownloading(db, id, version, jEnqueue);
        }
        return jEnqueue;
    }

    private static CompletedDownloadInfo getCompletedDownloadInfo(final DownloadManagerWrapper manager, final long downloadId) {
        Cursor cursorQuery = manager.query(new DownloadManager.Query().setFilterById(downloadId));
        int i = 16;
        String str = null;
        if (cursorQuery == null) {
            return new CompletedDownloadInfo(null, downloadId, 16);
        }
        try {
            if (cursorQuery.moveToNext()) {
                int columnIndex = cursorQuery.getColumnIndex("status");
                int columnIndex2 = cursorQuery.getColumnIndex("reason");
                int columnIndex3 = cursorQuery.getColumnIndex("uri");
                int i2 = cursorQuery.getInt(columnIndex2);
                i = cursorQuery.getInt(columnIndex);
                String string = cursorQuery.getString(columnIndex3);
                int iIndexOf = string.indexOf(35);
                String strSubstring = iIndexOf != -1 ? string.substring(0, iIndexOf) : string;
                if (8 != i) {
                    Log.e(TAG, "Permanent failure of download " + downloadId + " with error code: " + i2);
                }
                str = strSubstring;
            }
            return new CompletedDownloadInfo(str, downloadId, i);
        } finally {
            cursorQuery.close();
        }
    }

    private static ArrayList<DownloadRecord> getDownloadRecordsForCompletedDownloadInfo(final Context context, final CompletedDownloadInfo downloadInfo) {
        ArrayList<DownloadRecord> downloadRecordsForDownloadId;
        synchronized (sSharedIdProtector) {
            downloadRecordsForDownloadId = MetadataDbHelper.getDownloadRecordsForDownloadId(context, downloadInfo.mDownloadId);
            boolean z = false;
            Iterator<DownloadRecord> it = downloadRecordsForDownloadId.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (it.next().isMetadata()) {
                    z = true;
                    break;
                }
            }
            if (z) {
                writeMetadataDownloadId(context, downloadInfo.mUri, -1L);
                MetadataDbHelper.saveLastUpdateTimeOfUri(context, downloadInfo.mUri);
            }
        }
        return downloadRecordsForDownloadId;
    }

    static void downloadFinished(final Context context, final Intent intent) throws Throwable {
        DownloadManagerWrapper downloadManagerWrapper;
        CompletedDownloadInfo completedDownloadInfo;
        ArrayList<DownloadRecord> downloadRecordsForCompletedDownloadInfo;
        String str;
        boolean zHandleDownloadedFile;
        String str2;
        int i;
        String str3 = "Success";
        long longExtra = intent.getLongExtra("extra_download_id", -1L);
        Log.i(TAG, "downloadFinished() : DownloadId = " + longExtra);
        if (-1 == longExtra || (downloadRecordsForCompletedDownloadInfo = getDownloadRecordsForCompletedDownloadInfo(context, (completedDownloadInfo = getCompletedDownloadInfo((downloadManagerWrapper = new DownloadManagerWrapper(context)), longExtra)))) == null) {
            return;
        }
        int i2 = 1;
        DebugLogUtils.l("Received result for download ", Long.valueOf(longExtra));
        for (DownloadRecord downloadRecord : downloadRecordsForCompletedDownloadInfo) {
            try {
                if (completedDownloadInfo.wasSuccessful()) {
                    zHandleDownloadedFile = handleDownloadedFile(context, downloadRecord, downloadManagerWrapper, longExtra);
                    try {
                        Log.i(TAG, "downloadFinished() : Success = " + zHandleDownloadedFile);
                    } catch (Throwable th) {
                        th = th;
                        str = str3;
                        String str4 = zHandleDownloadedFile ? str : "Failure";
                        if (downloadRecord.isMetadata()) {
                            Log.i(TAG, "downloadFinished() : Metadata " + str4);
                            publishUpdateMetadataCompleted(context, zHandleDownloadedFile);
                        } else {
                            Log.i(TAG, "downloadFinished() : WordList " + str4);
                            publishUpdateWordListCompleted(context, zHandleDownloadedFile, longExtra, MetadataDbHelper.getDb(context, downloadRecord.mClientId), downloadRecord.mAttributes, downloadRecord.mClientId);
                        }
                        throw th;
                    }
                } else {
                    zHandleDownloadedFile = false;
                }
                String str5 = zHandleDownloadedFile ? str3 : "Failure";
                if (downloadRecord.isMetadata()) {
                    Log.i(TAG, "downloadFinished() : Metadata " + str5);
                    publishUpdateMetadataCompleted(context, zHandleDownloadedFile);
                    str2 = str3;
                    i = i2;
                } else {
                    Log.i(TAG, "downloadFinished() : WordList " + str5);
                    str2 = str3;
                    i = i2;
                    publishUpdateWordListCompleted(context, zHandleDownloadedFile, longExtra, MetadataDbHelper.getDb(context, downloadRecord.mClientId), downloadRecord.mAttributes, downloadRecord.mClientId);
                }
                i2 = i;
                str3 = str2;
            } catch (Throwable th2) {
                th = th2;
                str = str3;
                zHandleDownloadedFile = false;
            }
        }
        long[] jArr = new long[i2];
        jArr[0] = longExtra;
        downloadManagerWrapper.remove(jArr);
    }

    public static void publishUpdateMetadataCompleted(final Context context, final boolean downloadSuccessful) {
        Iterator it = linkedCopyOfList(sUpdateEventListeners).iterator();
        while (it.hasNext()) {
            ((UpdateEventListener) it.next()).downloadedMetadata(downloadSuccessful);
        }
        publishUpdateCycleCompletedEvent(context);
    }

    private static void publishUpdateWordListCompleted(final Context context, final boolean downloadSuccessful, final long fileId, final SQLiteDatabase db, final ContentValues downloadedFileRecord, final String clientId) {
        synchronized (sSharedIdProtector) {
            if (downloadSuccessful) {
                ActionBatch actionBatch = new ActionBatch();
                actionBatch.add(new ActionBatch.InstallAfterDownloadAction(clientId, downloadedFileRecord));
                actionBatch.execute(context, new LogProblemReporter(TAG));
            } else {
                MetadataDbHelper.deleteDownloadingEntry(db, fileId);
            }
        }
        Iterator it = linkedCopyOfList(sUpdateEventListeners).iterator();
        while (it.hasNext()) {
            ((UpdateEventListener) it.next()).wordListDownloadFinished(downloadedFileRecord.getAsString(MetadataDbHelper.WORDLISTID_COLUMN), downloadSuccessful);
        }
        publishUpdateCycleCompletedEvent(context);
    }

    private static void publishUpdateCycleCompletedEvent(final Context context) {
        PrivateLog.log("Publishing update cycle completed event");
        DebugLogUtils.l("Publishing update cycle completed event");
        Iterator it = linkedCopyOfList(sUpdateEventListeners).iterator();
        while (it.hasNext()) {
            ((UpdateEventListener) it.next()).updateCycleCompleted();
        }
        signalNewDictionaryState(context);
    }

    private static boolean handleDownloadedFile(final Context context, final DownloadRecord downloadRecord, final DownloadManagerWrapper manager, final long fileId) throws Throwable {
        try {
            if (downloadRecord.isMetadata()) {
                DebugLogUtils.l("Data D/L'd is metadata for", downloadRecord.mClientId);
                handleMetadata(context, new ParcelFileDescriptor.AutoCloseInputStream(manager.openDownloadedFile(fileId)), downloadRecord.mClientId);
            } else {
                DebugLogUtils.l("Data D/L'd is a word list");
                if (2 == downloadRecord.mAttributes.getAsInteger("status").intValue()) {
                    handleWordList(context, new ParcelFileDescriptor.AutoCloseInputStream(manager.openDownloadedFile(fileId)), downloadRecord);
                } else {
                    Log.e(TAG, "Spurious download ended. Maybe a cancelled download?");
                }
            }
            return true;
        } catch (BadFormatException e) {
            Log.e(TAG, "Incorrect data received", e);
            return false;
        } catch (FileNotFoundException e2) {
            Log.e(TAG, "A file was downloaded but it can't be opened", e2);
            return false;
        } catch (IOException e3) {
            Log.e(TAG, "Can't read a file", e3);
            return false;
        } catch (IllegalStateException e4) {
            Log.e(TAG, "Incorrect data received", e4);
            return false;
        }
    }

    private static <T> List<T> linkedCopyOfList(final List<T> src) {
        return new LinkedList(src);
    }

    private static void signalNewDictionaryState(final Context context) {
        context.sendBroadcast(new Intent(DictionaryPackConstants.NEW_DICTIONARY_INTENT_ACTION));
    }

    public static void handleMetadata(final Context context, final InputStream stream, final String clientId) throws IOException, BadFormatException {
        DebugLogUtils.l("Entering handleMetadata");
        InputStreamReader inputStreamReader = new InputStreamReader(stream);
        try {
            List<WordListMetadata> metadata = MetadataHandler.readMetadata(inputStreamReader);
            inputStreamReader.close();
            DebugLogUtils.l("Downloaded metadata :", metadata);
            PrivateLog.log("Downloaded metadata\n" + metadata);
            computeUpgradeTo(context, clientId, metadata).execute(context, new LogProblemReporter(TAG));
        } catch (Throwable th) {
            inputStreamReader.close();
            throw th;
        }
    }

    private static void handleWordList(Context context, InputStream inputStream, DownloadRecord downloadRecord) throws Throwable {
        FileOutputStream fileOutputStreamOpenFileOutput;
        DebugLogUtils.l("Downloaded a new word list :", downloadRecord.mAttributes.getAsString("description"), "for", downloadRecord.mClientId);
        PrivateLog.log("Downloaded a new word list with description : " + downloadRecord.mAttributes.getAsString("description") + " for " + downloadRecord.mClientId);
        String tempFileName = getTempFileName(context, downloadRecord.mAttributes.getAsString("locale"));
        downloadRecord.mAttributes.put(MetadataDbHelper.LOCAL_FILENAME_COLUMN, tempFileName);
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStreamOpenFileInput = null;
        try {
            fileOutputStreamOpenFileOutput = context.openFileOutput(tempFileName, 0);
        } catch (Throwable th) {
            th = th;
        }
        try {
            copyFile(inputStream, fileOutputStreamOpenFileOutput);
            inputStream.close();
            if (fileOutputStreamOpenFileOutput != null) {
                fileOutputStreamOpenFileOutput.close();
            }
            try {
                fileInputStreamOpenFileInput = context.openFileInput(tempFileName);
                String strChecksum = MD5Calculator.checksum(fileInputStreamOpenFileInput);
                if (TextUtils.isEmpty(strChecksum) || strChecksum.equals(downloadRecord.mAttributes.getAsString(MetadataDbHelper.CHECKSUM_COLUMN))) {
                    return;
                }
                context.deleteFile(tempFileName);
                throw new BadFormatException("MD5 checksum check failed : \"" + strChecksum + "\" <> \"" + downloadRecord.mAttributes.getAsString(MetadataDbHelper.CHECKSUM_COLUMN) + "\"");
            } finally {
                if (fileInputStreamOpenFileInput != null) {
                    fileInputStreamOpenFileInput.close();
                }
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = fileOutputStreamOpenFileOutput;
            inputStream.close();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    private static void copyFile(final InputStream in, final OutputStream out) throws IOException {
        DebugLogUtils.l("Copying files");
        if (!(in instanceof FileInputStream) || !(out instanceof FileOutputStream)) {
            DebugLogUtils.l("Not the right types");
            copyFileFallback(in, out);
        } else {
            try {
                ((FileInputStream) in).getChannel().transferTo(0L, 2147483647L, ((FileOutputStream) out).getChannel());
            } catch (IOException unused) {
                DebugLogUtils.l("Won't work");
                copyFileFallback(in, out);
            }
        }
    }

    private static void copyFileFallback(final InputStream in, final OutputStream out) throws IOException {
        DebugLogUtils.l("Falling back to slow copy");
        byte[] bArr = new byte[8192];
        while (true) {
            int i = in.read(bArr);
            if (i < 0) {
                return;
            } else {
                out.write(bArr, 0, i);
            }
        }
    }

    private static String getTempFileName(final Context context, final String locale) throws IOException {
        DebugLogUtils.l("Entering openTempFileOutput");
        File fileCreateTempFile = File.createTempFile(locale + TEMP_DICT_FILE_SUB, DICT_FILE_SUFFIX, context.getFilesDir());
        DebugLogUtils.l("File name is", fileCreateTempFile.getName());
        return fileCreateTempFile.getName();
    }

    private static ActionBatch compareMetadataForUpgrade(final Context context, final String clientId, @Nullable final List<WordListMetadata> from, @Nullable final List<WordListMetadata> to) {
        ActionBatch actionBatch = new ActionBatch();
        DebugLogUtils.l("Comparing dictionaries");
        TreeSet<String> treeSet = new TreeSet();
        List<WordListMetadata> arrayList = from == null ? new ArrayList<>() : from;
        List<WordListMetadata> arrayList2 = to == null ? new ArrayList<>() : to;
        Iterator<WordListMetadata> it = arrayList.iterator();
        while (it.hasNext()) {
            treeSet.add(it.next().mId);
        }
        Iterator<WordListMetadata> it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            treeSet.add(it2.next().mId);
        }
        for (String str : treeSet) {
            WordListMetadata wordListMetadataFindWordListById = MetadataHandler.findWordListById(arrayList, str);
            WordListMetadata wordListMetadataFindWordListById2 = MetadataHandler.findWordListById(arrayList2, str);
            WordListMetadata wordListMetadata = (wordListMetadataFindWordListById2 == null || wordListMetadataFindWordListById2.mFormatVersion > 86736212) ? null : wordListMetadataFindWordListById2;
            DebugLogUtils.l("Considering updating ", str, "currentInfo =", wordListMetadataFindWordListById);
            if (wordListMetadataFindWordListById == null && wordListMetadata == null) {
                if (wordListMetadataFindWordListById2 == null) {
                    Log.e(TAG, "Got an id for a wordlist that is neither in from nor in to");
                } else {
                    Log.i(TAG, "Can't handle word list with id '" + str + "' because it has format version " + wordListMetadataFindWordListById2.mFormatVersion + " and the maximum version we can handle is 86736212");
                }
            } else if (wordListMetadataFindWordListById == null) {
                actionBatch.add(new ActionBatch.MakeAvailableAction(clientId, wordListMetadata));
            } else if (wordListMetadata == null) {
                actionBatch.add(new ActionBatch.ForgetAction(clientId, wordListMetadataFindWordListById, false));
            } else {
                SQLiteDatabase db = MetadataDbHelper.getDb(context, clientId);
                if (wordListMetadata.mVersion == wordListMetadataFindWordListById.mVersion) {
                    if (TextUtils.equals(wordListMetadata.mRemoteFilename, wordListMetadataFindWordListById.mRemoteFilename)) {
                        wordListMetadata.mRetryCount = wordListMetadataFindWordListById.mRetryCount;
                    }
                    actionBatch.add(new ActionBatch.UpdateDataAction(clientId, wordListMetadata));
                } else if (wordListMetadata.mVersion > wordListMetadataFindWordListById.mVersion) {
                    int iIntValue = MetadataDbHelper.getContentValuesByWordListId(db, wordListMetadataFindWordListById.mId, wordListMetadataFindWordListById.mVersion).getAsInteger("status").intValue();
                    actionBatch.add(new ActionBatch.MakeAvailableAction(clientId, wordListMetadata));
                    if (iIntValue == 3 || iIntValue == 4) {
                        actionBatch.add(new ActionBatch.StartDownloadAction(clientId, wordListMetadata));
                    } else {
                        actionBatch.add(new ActionBatch.ForgetAction(clientId, wordListMetadataFindWordListById, true));
                    }
                }
            }
        }
        return actionBatch;
    }

    public static ActionBatch computeUpgradeTo(final Context context, final String clientId, final List<WordListMetadata> newMetadata) {
        return compareMetadataForUpgrade(context, clientId, MetadataHandler.getCurrentMetadata(context, clientId), newMetadata);
    }

    private static void showDictionaryAvailableNotification(final Context context, final String clientId, final ContentValues installCandidate) {
        String asString = installCandidate.getAsString("locale");
        Intent intent = new Intent();
        intent.setClass(context, DownloadOverMeteredDialog.class);
        intent.putExtra(DownloadOverMeteredDialog.CLIENT_ID_KEY, clientId);
        intent.putExtra(DownloadOverMeteredDialog.WORDLIST_TO_DOWNLOAD_KEY, installCandidate.getAsString(MetadataDbHelper.WORDLISTID_COLUMN));
        intent.putExtra(DownloadOverMeteredDialog.SIZE_KEY, installCandidate.getAsInteger(MetadataDbHelper.FILESIZE_COLUMN));
        intent.putExtra("locale", asString);
        intent.addFlags(276824064);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, 1207959552);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (activity == null || notificationManager == null) {
            return;
        }
        String str = String.format(context.getString(R.string.dict_available_notification_title), asString == null ? "" : LocaleUtils.constructLocaleFromString(asString).getDisplayLanguage());
        Notification.Builder smallIcon = new Notification.Builder(context).setAutoCancel(true).setContentIntent(activity).setContentTitle(str).setContentText(context.getString(R.string.dict_available_notification_description)).setTicker(str).setOngoing(false).setOnlyAlertOnce(true).setSmallIcon(R.drawable.ic_notify_dictionary);
        NotificationCompatUtils.setColor(smallIcon, context.getResources().getColor(R.color.notification_accent_color));
        NotificationCompatUtils.setPriorityToLow(smallIcon);
        NotificationCompatUtils.setVisibilityToSecret(smallIcon);
        NotificationCompatUtils.setCategoryToRecommendation(smallIcon);
        notificationManager.notify(1, NotificationCompatUtils.build(smallIcon));
    }

    public static void installIfNeverRequested(final Context context, final String clientId, final String wordlistId) {
        String str = TAG;
        Log.i(str, "installIfNeverRequested() : ClientId = " + clientId + " : WordListId = " + wordlistId);
        String[] strArrSplit = wordlistId.split(":");
        if ("main".equals(2 == strArrSplit.length ? strArrSplit[0] : "main") && !CommonPreferences.getCommonPreferences(context).contains(wordlistId)) {
            ContentValues contentValuesOfLatestAvailableWordlistById = MetadataDbHelper.getContentValuesOfLatestAvailableWordlistById(MetadataDbHelper.getDb(context, clientId), wordlistId);
            if (1 != contentValuesOfLatestAvailableWordlistById.getAsInteger("status").intValue()) {
                return;
            }
            ActionBatch actionBatch = new ActionBatch();
            WordListMetadata wordListMetadataCreateFromContentValues = WordListMetadata.createFromContentValues(contentValuesOfLatestAvailableWordlistById);
            actionBatch.add(new ActionBatch.StartDownloadAction(clientId, wordListMetadataCreateFromContentValues));
            String asString = contentValuesOfLatestAvailableWordlistById.getAsString("locale");
            if (Settings.Global.getInt(context.getContentResolver(), "device_provisioned", 0) != 0) {
                Intent intent = new Intent();
                intent.setClass(context, DictionaryService.class);
                intent.setAction("com.android.inputmethod.latin.SHOW_DOWNLOAD_TOAST_INTENT_ACTION");
                intent.putExtra("locale", asString);
                context.startService(intent);
            } else {
                Log.i(str, "installIfNeverRequested() : Don't show download toast");
            }
            Log.i(str, "installIfNeverRequested() : StartDownloadAction for " + wordListMetadataCreateFromContentValues);
            actionBatch.execute(context, new LogProblemReporter(str));
        }
    }

    public static void markAsUsed(final Context context, final String clientId, final String wordlistId, final int version, final int status, final boolean allowDownloadOnMeteredData) {
        WordListMetadata currentMetadataForWordList = MetadataHandler.getCurrentMetadataForWordList(context, clientId, wordlistId, version);
        if (currentMetadataForWordList == null) {
            return;
        }
        ActionBatch actionBatch = new ActionBatch();
        if (4 == status || 5 == status) {
            actionBatch.add(new ActionBatch.EnableAction(clientId, currentMetadataForWordList));
        } else if (1 == status) {
            actionBatch.add(new ActionBatch.StartDownloadAction(clientId, currentMetadataForWordList));
        } else {
            Log.e(TAG, "Unexpected state of the word list for markAsUsed : " + status);
        }
        actionBatch.execute(context, new LogProblemReporter(TAG));
        signalNewDictionaryState(context);
    }

    public static void markAsUnused(final Context context, final String clientId, final String wordlistId, final int version, final int status) {
        WordListMetadata currentMetadataForWordList = MetadataHandler.getCurrentMetadataForWordList(context, clientId, wordlistId, version);
        if (currentMetadataForWordList == null) {
            return;
        }
        ActionBatch actionBatch = new ActionBatch();
        actionBatch.add(new ActionBatch.DisableAction(clientId, currentMetadataForWordList));
        actionBatch.execute(context, new LogProblemReporter(TAG));
        signalNewDictionaryState(context);
    }

    public static void markAsDeleting(final Context context, final String clientId, final String wordlistId, final int version, final int status) {
        WordListMetadata currentMetadataForWordList = MetadataHandler.getCurrentMetadataForWordList(context, clientId, wordlistId, version);
        if (currentMetadataForWordList == null) {
            return;
        }
        ActionBatch actionBatch = new ActionBatch();
        actionBatch.add(new ActionBatch.DisableAction(clientId, currentMetadataForWordList));
        actionBatch.add(new ActionBatch.StartDeleteAction(clientId, currentMetadataForWordList));
        actionBatch.execute(context, new LogProblemReporter(TAG));
        signalNewDictionaryState(context);
    }

    public static void markAsDeleted(final Context context, final String clientId, final String wordlistId, final int version, final int status) {
        WordListMetadata currentMetadataForWordList = MetadataHandler.getCurrentMetadataForWordList(context, clientId, wordlistId, version);
        if (currentMetadataForWordList == null) {
            return;
        }
        ActionBatch actionBatch = new ActionBatch();
        actionBatch.add(new ActionBatch.FinishDeleteAction(clientId, currentMetadataForWordList));
        actionBatch.execute(context, new LogProblemReporter(TAG));
        signalNewDictionaryState(context);
    }

    public static void markAsBrokenOrRetrying(final Context context, final String clientId, final String wordlistId, final int version) {
        if (MetadataDbHelper.maybeMarkEntryAsRetrying(MetadataDbHelper.getDb(context, clientId), wordlistId, version)) {
            WordListMetadata currentMetadataForWordList = MetadataHandler.getCurrentMetadataForWordList(context, clientId, wordlistId, version);
            if (currentMetadataForWordList == null) {
                return;
            }
            ActionBatch actionBatch = new ActionBatch();
            actionBatch.add(new ActionBatch.StartDownloadAction(clientId, currentMetadataForWordList));
            actionBatch.execute(context, new LogProblemReporter(TAG));
            return;
        }
        MetadataDbHelper.deleteEntry(MetadataDbHelper.getDb(context, clientId), wordlistId, version);
    }
}
