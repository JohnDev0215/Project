package com.android.providers.media.scan;

import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.drm.DrmManagerClient;
import android.drm.DrmSupportInfo;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.OperationCanceledException;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.Trace;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.util.Pair;
import com.android.providers.media.util.DatabaseUtils;
import com.android.providers.media.util.ExifUtils;
import com.android.providers.media.util.FileUtils;
import com.android.providers.media.util.IsoInterface;
import com.android.providers.media.util.LongArray;
import com.android.providers.media.util.Metrics;
import com.android.providers.media.util.MimeUtils;
import com.android.providers.media.util.XmpInterface;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class ModernMediaScanner implements MediaScanner {
    private static final int BATCH_SIZE = 32;
    private static final Pattern PATTERN_ALBUM_ART;
    private static final Pattern PATTERN_INVISIBLE;
    private static final Pattern PATTERN_VISIBLE;
    private static final Pattern PATTERN_YEAR;
    private static ArrayMap<String, String> sAudioTypes;
    private static final SimpleDateFormat sDateFormat;
    private final Context mContext;
    private final DrmManagerClient mDrmClient;
    private static final String TAG = "ModernMediaScanner";
    private static final boolean LOGW = Log.isLoggable(TAG, 5);
    private static final boolean LOGD = Log.isLoggable(TAG, 3);
    private static final boolean LOGV = Log.isLoggable(TAG, 2);
    private final ArrayMap<String, CancellationSignal> mSignals = new ArrayMap<>();
    private final Map<Path, DirectoryLock> mDirectoryLocks = new ArrayMap();
    private final Set<String> mDrmMimeTypes = new ArraySet();

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
        sDateFormat = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        PATTERN_VISIBLE = Pattern.compile("(?i)^/storage/[^/]+(?:/[0-9]+)?(?:/Android/sandbox/([^/]+))?$");
        PATTERN_INVISIBLE = Pattern.compile("(?i)^/storage/[^/]+(?:/[0-9]+)?(?:/Android/sandbox/([^/]+))?/(?:(?:Android/(?:data|obb)$)|(?:(?:Movies|Music|Pictures)/.thumbnails$))");
        PATTERN_YEAR = Pattern.compile("([1-9][0-9][0-9][0-9])");
        PATTERN_ALBUM_ART = Pattern.compile("(?i)(?:(?:^folder|(?:^AlbumArt(?:(?:_\\{.*\\}_)?(?:small|large))?))(?:\\.jpg$)|(?:\\._.*))");
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        sAudioTypes = arrayMap;
        arrayMap.put(Environment.DIRECTORY_RINGTONES, "is_ringtone");
        sAudioTypes.put(Environment.DIRECTORY_NOTIFICATIONS, "is_notification");
        sAudioTypes.put(Environment.DIRECTORY_ALARMS, "is_alarm");
        sAudioTypes.put(Environment.DIRECTORY_PODCASTS, "is_podcast");
        sAudioTypes.put(Environment.DIRECTORY_AUDIOBOOKS, "is_audiobook");
        sAudioTypes.put(Environment.DIRECTORY_MUSIC, "is_music");
    }

    private static class DirectoryLock {
        public int count;
        public final Lock lock;

        private DirectoryLock() {
            this.lock = new ReentrantLock();
        }
    }

    public ModernMediaScanner(Context context) {
        this.mContext = context;
        DrmManagerClient drmManagerClient = new DrmManagerClient(context);
        this.mDrmClient = drmManagerClient;
        Iterator<DrmSupportInfo> it = drmManagerClient.getAvailableDrmSupportInfo().iterator();
        while (it.hasNext()) {
            Iterator<String> mimeTypeIterator = it.next().getMimeTypeIterator();
            while (mimeTypeIterator.hasNext()) {
                this.mDrmMimeTypes.add(mimeTypeIterator.next());
            }
        }
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public Context getContext() {
        return this.mContext;
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public void scanDirectory(File file, int i) {
        try {
            Scan scan = new Scan(file, i, null);
            try {
                scan.run();
                scan.close();
            } finally {
            }
        } catch (OperationCanceledException unused) {
        }
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public Uri scanFile(File file, int i) {
        return scanFile(file, i, null);
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public Uri scanFile(File file, int i, String str) {
        try {
            Scan scan = new Scan(file, i, str);
            try {
                scan.run();
                Uri firstResult = scan.getFirstResult();
                scan.close();
                return firstResult;
            } finally {
            }
        } catch (OperationCanceledException unused) {
            return null;
        }
    }

    @Override // com.android.providers.media.scan.MediaScanner
    public void onDetachVolume(String str) {
        synchronized (this.mSignals) {
            CancellationSignal cancellationSignalRemove = this.mSignals.remove(str);
            if (cancellationSignalRemove != null) {
                cancellationSignalRemove.cancel();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CancellationSignal getOrCreateSignal(String str) {
        CancellationSignal cancellationSignal;
        synchronized (this.mSignals) {
            cancellationSignal = this.mSignals.get(str);
            if (cancellationSignal == null) {
                cancellationSignal = new CancellationSignal();
                this.mSignals.put(str, cancellationSignal);
            }
        }
        return cancellationSignal;
    }

    private class Scan implements Runnable, FileVisitor<Path>, AutoCloseable {
        private static final int LENGTHOFTRUE = 4;
        private final ContentProviderClient mClient;
        private int mDeleteCount;
        private int mFileCount;
        private final Uri mFilesUri;
        private int mHiddenDirCount;
        private int mInsertCount;
        private final String mOwnerPackage;
        private final int mReason;
        private final ContentResolver mResolver;
        private final File mRoot;
        private final CancellationSignal mSignal;
        private final boolean mSingleFile;
        private final long mStartGeneration;
        private int mUpdateCount;
        private final String mVolumeName;
        private final Set<Path> mAcquiredDirectoryLocks = new ArraySet();
        private final ArrayList<ContentProviderOperation> mPending = new ArrayList<>();
        private LongArray mScannedIds = new LongArray();
        private LongArray mUnknownIds = new LongArray();
        private long mFirstId = -1;
        private final String[] mValidExtensions = {".mp3", ".mp4", ".m4a", ".3gp", ".3gpp", ".3g2", ".3gpp2", ".ogg", ".mid", ".smf", ".imy", ".wma", ".aac", ".wav", ".amr", ".midi", ".xmf", ".rtttl", ".rtx", ".ota", ".mkv", ".mka", ".webm", ".ts", ".fl", ".flac", ".mxmf", ".avi", ".mpeg", ".mpg", ".awb", ".mpga", ".mov", ".wmv", ".asf", ".m4v", ".oga", ".aiff", ".divx", ".xvid", ".flv", ".ape", ".jpg", ".jpeg", ".gif", ".png", ".bmp", ".webp", ".arw", ".cr2", ".dng", ".nef", ".nrw", ".orf", ".pef", ".raf", ".rw2", ".srw", ".heif", ".heic"};
        private final ArrayList<String> mSupportedExtensions = new ArrayList<>();
        private boolean mInitConfig = false;
        private boolean mIsSupportConfig = false;

        public Scan(File file, int i, String str) {
            Trace.beginSection("ctor");
            ContentProviderClient contentProviderClientAcquireContentProviderClient = ModernMediaScanner.this.mContext.getContentResolver().acquireContentProviderClient("media");
            this.mClient = contentProviderClientAcquireContentProviderClient;
            this.mResolver = ContentResolver.wrap(contentProviderClientAcquireContentProviderClient.getLocalContentProvider());
            this.mRoot = file;
            this.mReason = i;
            String volumeName = FileUtils.getVolumeName(ModernMediaScanner.this.mContext, file);
            this.mVolumeName = volumeName;
            this.mFilesUri = MediaStore.Files.getContentUri(volumeName);
            this.mSignal = ModernMediaScanner.this.getOrCreateSignal(this.mVolumeName);
            this.mStartGeneration = MediaStore.getGeneration(this.mResolver, this.mVolumeName);
            this.mSingleFile = this.mRoot.isFile();
            this.mOwnerPackage = str;
            Trace.endSection();
        }

        @Override // java.lang.Runnable
        public void run() {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            walkFileTree();
            if (!this.mSingleFile || this.mScannedIds.size() != 1) {
                reconcileAndClean();
            }
            resolvePlaylists();
            if (this.mSingleFile) {
                return;
            }
            Metrics.logScan(this.mVolumeName, this.mReason, this.mFileCount, SystemClock.elapsedRealtime() - jElapsedRealtime, this.mInsertCount, this.mUpdateCount, this.mDeleteCount);
        }

        private void walkFileTree() {
            this.mSignal.throwIfCanceled();
            Pair<Boolean, Boolean> pairShouldScanPathAndIsPathHidden = ModernMediaScanner.shouldScanPathAndIsPathHidden(this.mSingleFile ? this.mRoot.getParentFile() : this.mRoot);
            if (((Boolean) pairShouldScanPathAndIsPathHidden.first).booleanValue()) {
                Trace.beginSection("walkFileTree");
                if (((Boolean) pairShouldScanPathAndIsPathHidden.second).booleanValue()) {
                    this.mHiddenDirCount++;
                }
                if (this.mSingleFile) {
                    acquireDirectoryLock(this.mRoot.getParentFile().toPath());
                }
                try {
                    try {
                        Files.walkFileTree(this.mRoot.toPath(), this);
                        applyPending();
                    } catch (IOException e) {
                        throw new IllegalStateException(e);
                    }
                } finally {
                    if (this.mSingleFile) {
                        releaseDirectoryLock(this.mRoot.getParentFile().toPath());
                    }
                    Trace.endSection();
                }
            }
        }

        private void reconcileAndClean() {
            long[] array = this.mScannedIds.toArray();
            Arrays.sort(array);
            this.mSignal.throwIfCanceled();
            Trace.beginSection("reconcile");
            String str = "generation_added <= " + this.mStartGeneration;
            Bundle bundle = new Bundle();
            bundle.putString("android:query-arg-sql-selection", "ifnull(format,12288) != 47621 AND (_data LIKE ? ESCAPE '\\' OR _data LIKE ? ESCAPE '\\') AND " + str);
            String strEscapeForLike = DatabaseUtils.escapeForLike(this.mRoot.getAbsolutePath());
            bundle.putStringArray("android:query-arg-sql-selection-args", new String[]{strEscapeForLike + "/%", strEscapeForLike});
            bundle.putString("android:query-arg-sql-sort-order", "_id DESC");
            bundle.putInt("android:query-arg-match-pending", 2);
            bundle.putInt("android:query-arg-match-trashed", 1);
            bundle.putInt("android:query-arg-match-favorite", 1);
            try {
                Cursor cursorQuery = this.mResolver.query(this.mFilesUri, new String[]{"_id"}, bundle, this.mSignal);
                while (cursorQuery.moveToNext()) {
                    try {
                        long j = cursorQuery.getLong(0);
                        if (Arrays.binarySearch(array, j) < 0) {
                            this.mUnknownIds.add(j);
                        }
                    } finally {
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                Trace.endSection();
                this.mSignal.throwIfCanceled();
                Trace.beginSection("clean");
                for (int i = 0; i < this.mUnknownIds.size(); i++) {
                    try {
                        long j2 = this.mUnknownIds.get(i);
                        if (ModernMediaScanner.LOGV) {
                            Log.v(ModernMediaScanner.TAG, "Cleaning " + j2);
                        }
                        addPending(ContentProviderOperation.newDelete(MediaStore.Files.getContentUri(this.mVolumeName, j2).buildUpon().appendQueryParameter("deletedata", "false").build()).build());
                        maybeApplyPending();
                    } finally {
                    }
                }
                applyPending();
            } finally {
            }
        }

        private void resolvePlaylists() {
            this.mSignal.throwIfCanceled();
            if ("internal".equals(this.mVolumeName)) {
                return;
            }
            Uri contentUri = MediaStore.Audio.Playlists.getContentUri(this.mVolumeName);
            Bundle bundle = new Bundle();
            bundle.putString("android:query-arg-sql-selection", "generation_modified > " + this.mStartGeneration);
            try {
                Cursor cursorQuery = this.mResolver.query(contentUri, new String[]{"_id"}, bundle, this.mSignal);
                while (cursorQuery.moveToNext()) {
                    try {
                        MediaStore.resolvePlaylistMembers(this.mResolver, ContentUris.withAppendedId(contentUri, cursorQuery.getLong(0)));
                    } finally {
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } finally {
                Trace.endSection();
            }
        }

        private void acquireDirectoryLock(Path path) {
            DirectoryLock directoryLock;
            Trace.beginSection("acquireDirectoryLock");
            synchronized (ModernMediaScanner.this.mDirectoryLocks) {
                directoryLock = (DirectoryLock) ModernMediaScanner.this.mDirectoryLocks.get(path);
                if (directoryLock == null) {
                    directoryLock = new DirectoryLock();
                    ModernMediaScanner.this.mDirectoryLocks.put(path, directoryLock);
                }
                directoryLock.count++;
            }
            directoryLock.lock.lock();
            this.mAcquiredDirectoryLocks.add(path);
            Trace.endSection();
        }

        private void releaseDirectoryLock(Path path) {
            DirectoryLock directoryLock;
            Trace.beginSection("releaseDirectoryLock");
            synchronized (ModernMediaScanner.this.mDirectoryLocks) {
                directoryLock = (DirectoryLock) ModernMediaScanner.this.mDirectoryLocks.get(path);
                if (directoryLock == null) {
                    throw new IllegalStateException();
                }
                int i = directoryLock.count - 1;
                directoryLock.count = i;
                if (i == 0) {
                    ModernMediaScanner.this.mDirectoryLocks.remove(path);
                }
            }
            directoryLock.lock.unlock();
            this.mAcquiredDirectoryLocks.remove(path);
            Trace.endSection();
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            if (!this.mPending.isEmpty()) {
                throw new IllegalStateException();
            }
            Iterator it = new ArraySet(this.mAcquiredDirectoryLocks).iterator();
            while (it.hasNext()) {
                releaseDirectoryLock((Path) it.next());
            }
            this.mClient.close();
        }

        @Override // java.nio.file.FileVisitor
        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            this.mSignal.throwIfCanceled();
            if (!ModernMediaScanner.shouldScanDirectory(path.toFile())) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            acquireDirectoryLock(path);
            if (FileUtils.isDirectoryHidden(path.toFile())) {
                this.mHiddenDirCount++;
            }
            return visitFile(path, basicFileAttributes);
        }

        public String readAllLine(BufferedReader bufferedReader) throws IOException {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    return sb.toString();
                }
            }
        }

        private void initMediaExtensionConfig() {
            Log.v(ModernMediaScanner.TAG, "initMediaExtensionConfig");
            this.mInitConfig = true;
            try {
                String allLine = readAllLine(new BufferedReader(new FileReader("/system/etc/support_media_extension.conf")));
                Log.v(ModernMediaScanner.TAG, "extConf = " + allLine);
                String[] strArrSplit = allLine.split("\\|");
                int length = strArrSplit.length;
                for (int i = 0; i < length; i++) {
                    String str = strArrSplit[i];
                    String strSubstring = str.substring(str.length() - 4);
                    String strSubstring2 = str.substring(0, str.indexOf("="));
                    if (strSubstring.equals("true")) {
                        this.mSupportedExtensions.add(strSubstring2);
                    }
                }
            } catch (FileNotFoundException e) {
                Log.v(ModernMediaScanner.TAG, "ConfigManager: file open error" + e);
                return;
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            this.mIsSupportConfig = true;
        }

        private boolean fileHasAcceptableExtension(File file) {
            String absolutePath = file.getAbsolutePath();
            String strSubstring = absolutePath.substring(absolutePath.lastIndexOf("."), absolutePath.length());
            if (ModernMediaScanner.LOGV) {
                Log.v(ModernMediaScanner.TAG, "extension = " + strSubstring);
            }
            if (this.mIsSupportConfig) {
                int i = 0;
                while (i < this.mSupportedExtensions.size() && !strSubstring.equalsIgnoreCase(this.mSupportedExtensions.get(i))) {
                    i++;
                }
                if (i != this.mSupportedExtensions.size()) {
                    return true;
                }
                Log.v(ModernMediaScanner.TAG, "unsupported extension, indexA = " + i);
                return false;
            }
            int i2 = 0;
            while (true) {
                String[] strArr = this.mValidExtensions;
                if (i2 >= strArr.length || strSubstring.equalsIgnoreCase(strArr[i2])) {
                    break;
                }
                i2++;
            }
            if (i2 != this.mValidExtensions.length) {
                return true;
            }
            Log.v(ModernMediaScanner.TAG, "unsupported extension, indexB = " + i2);
            return false;
        }

        @Override // java.nio.file.FileVisitor
        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            int iResolveMediaTypeFromFilePath;
            Cursor cursor;
            long j;
            String str;
            boolean zEqualsIgnoreCase;
            if (ModernMediaScanner.LOGV) {
                Log.v(ModernMediaScanner.TAG, "Visiting " + path);
            }
            this.mFileCount++;
            File file = path.toFile();
            String strResolveMimeType = basicFileAttributes.isDirectory() ? null : MimeUtils.resolveMimeType(file);
            boolean zContains = ModernMediaScanner.this.mDrmMimeTypes.contains(strResolveMimeType);
            if (zContains) {
                strResolveMimeType = ModernMediaScanner.this.mDrmClient.getOriginalMimeType(file.getPath());
            }
            String str2 = strResolveMimeType;
            if (str2 != null) {
                iResolveMediaTypeFromFilePath = ModernMediaScanner.resolveMediaTypeFromFilePath(file, str2, this.mHiddenDirCount > 0);
            } else {
                iResolveMediaTypeFromFilePath = 0;
            }
            Trace.beginSection("checkChanged");
            Bundle bundle = new Bundle();
            bundle.putString("android:query-arg-sql-selection", "_data=?");
            bundle.putStringArray("android:query-arg-sql-selection-args", new String[]{file.getAbsolutePath()});
            bundle.putInt("android:query-arg-match-pending", 1);
            bundle.putInt("android:query-arg-match-trashed", 1);
            bundle.putInt("android:query-arg-match-favorite", 1);
            String[] strArr = {"_id", "date_modified", "_size", "mime_type", "media_type", "is_pending"};
            boolean z = !FileUtils.PATTERN_EXPIRES_FILE.matcher(file.getName()).matches();
            try {
                Cursor cursorQuery = this.mResolver.query(this.mFilesUri, strArr, bundle, this.mSignal);
                try {
                    if (cursorQuery.moveToFirst()) {
                        long j2 = cursorQuery.getLong(0);
                        long j3 = cursorQuery.getLong(1);
                        long j4 = cursorQuery.getLong(2);
                        String string = cursorQuery.getString(3);
                        int i = cursorQuery.getInt(4);
                        boolean z2 = (cursorQuery.getInt(5) != 0) & z;
                        this.mScannedIds.add(j2);
                        cursor = cursorQuery;
                        try {
                            if (this.mFirstId == -1) {
                                this.mFirstId = j2;
                            }
                            boolean z3 = ModernMediaScanner.lastModifiedTime(file, basicFileAttributes) == j3;
                            boolean z4 = basicFileAttributes.size() == j4;
                            if (string == null) {
                                zEqualsIgnoreCase = str2 == null;
                            } else {
                                zEqualsIgnoreCase = string.equalsIgnoreCase(str2);
                            }
                            boolean z5 = z3 && z4 && (iResolveMediaTypeFromFilePath == i) && zEqualsIgnoreCase && !z2;
                            if (!basicFileAttributes.isDirectory() && !z5) {
                                j = j2;
                            }
                            if (ModernMediaScanner.LOGV) {
                                Log.v(ModernMediaScanner.TAG, "Skipping unchanged " + path);
                            }
                            FileVisitResult fileVisitResult = FileVisitResult.CONTINUE;
                            if (cursor != null) {
                                cursor.close();
                            }
                            return fileVisitResult;
                        } catch (Throwable th) {
                            th = th;
                            Throwable th2 = th;
                            if (cursor == null) {
                                throw th2;
                            }
                            try {
                                cursor.close();
                                throw th2;
                            } catch (Throwable th3) {
                                th2.addSuppressed(th3);
                                throw th2;
                            }
                        }
                    }
                    cursor = cursorQuery;
                    j = -1;
                    if (cursor != null) {
                        cursor.close();
                    }
                    Trace.endSection();
                    Trace.beginSection("scanItem");
                    try {
                        if (!this.mInitConfig) {
                            initMediaExtensionConfig();
                        }
                        if (!file.toString().contains(".") || fileHasAcceptableExtension(file)) {
                            ContentProviderOperation.Builder builderScanItem = ModernMediaScanner.scanItem(j, file, basicFileAttributes, str2, iResolveMediaTypeFromFilePath, this.mVolumeName);
                            if (builderScanItem != null) {
                                if (builderScanItem.build().isInsert() && !basicFileAttributes.isDirectory() && (str = this.mOwnerPackage) != null) {
                                    builderScanItem.withValue("owner_package_name", str);
                                }
                                if (zContains) {
                                    builderScanItem.withValue("is_drm", 1);
                                }
                                addPending(builderScanItem.build());
                                maybeApplyPending();
                            }
                            return FileVisitResult.CONTINUE;
                        }
                        return FileVisitResult.CONTINUE;
                    } finally {
                    }
                } catch (Throwable th4) {
                    th = th4;
                    cursor = cursorQuery;
                }
            } finally {
            }
        }

        @Override // java.nio.file.FileVisitor
        public FileVisitResult visitFileFailed(Path path, IOException iOException) throws IOException {
            Log.w(ModernMediaScanner.TAG, "Failed to visit " + path + ": " + iOException);
            return FileVisitResult.CONTINUE;
        }

        @Override // java.nio.file.FileVisitor
        public FileVisitResult postVisitDirectory(Path path, IOException iOException) throws IOException {
            applyPending();
            if (FileUtils.isDirectoryHidden(path.toFile())) {
                this.mHiddenDirCount--;
            }
            releaseDirectoryLock(path);
            return FileVisitResult.CONTINUE;
        }

        private void addPending(ContentProviderOperation contentProviderOperation) {
            this.mPending.add(contentProviderOperation);
            if (contentProviderOperation.isInsert()) {
                this.mInsertCount++;
            }
            if (contentProviderOperation.isUpdate()) {
                this.mUpdateCount++;
            }
            if (contentProviderOperation.isDelete()) {
                this.mDeleteCount++;
            }
        }

        private void maybeApplyPending() {
            if (this.mPending.size() > 32) {
                applyPending();
            }
        }

        private void applyPending() {
            if (this.mPending.isEmpty()) {
                return;
            }
            Trace.beginSection("applyPending");
            try {
                ContentProviderResult[] contentProviderResultArrApplyBatch = this.mResolver.applyBatch("media", this.mPending);
                for (int i = 0; i < contentProviderResultArrApplyBatch.length; i++) {
                    ContentProviderResult contentProviderResult = contentProviderResultArrApplyBatch[i];
                    ContentProviderOperation contentProviderOperation = this.mPending.get(i);
                    if (contentProviderResult.exception != null) {
                        Log.w(ModernMediaScanner.TAG, "Failed to apply " + contentProviderOperation, contentProviderResult.exception);
                    }
                    Uri uri = contentProviderResult.uri;
                    if (uri != null) {
                        long id = ContentUris.parseId(uri);
                        if (this.mFirstId == -1) {
                            this.mFirstId = id;
                        }
                        this.mScannedIds.add(id);
                    }
                }
            } catch (OperationApplicationException | RemoteException unused) {
            } catch (Throwable th) {
                this.mPending.clear();
                Trace.endSection();
                throw th;
            }
            this.mPending.clear();
            Trace.endSection();
        }

        public Uri getFirstResult() {
            long j = this.mFirstId;
            if (j == -1) {
                return null;
            }
            Uri contentUri = MediaStore.Files.getContentUri(this.mVolumeName, j);
            Cursor cursorQuery = this.mResolver.query(contentUri, new String[]{"media_type"}, null, null);
            try {
                if (cursorQuery.moveToFirst()) {
                    int i = cursorQuery.getInt(0);
                    if (i == 1) {
                        Uri contentUri2 = MediaStore.Images.Media.getContentUri(this.mVolumeName, this.mFirstId);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return contentUri2;
                    }
                    if (i == 2) {
                        Uri contentUri3 = MediaStore.Audio.Media.getContentUri(this.mVolumeName, this.mFirstId);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return contentUri3;
                    }
                    if (i == 3) {
                        Uri contentUri4 = MediaStore.Video.Media.getContentUri(this.mVolumeName, this.mFirstId);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return contentUri4;
                    }
                    if (i == 4) {
                        Uri uriWithAppendedId = ContentUris.withAppendedId(MediaStore.Audio.Playlists.getContentUri(this.mVolumeName), this.mFirstId);
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return uriWithAppendedId;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return contentUri;
            } catch (Throwable th) {
                if (cursorQuery != null) {
                    try {
                        cursorQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ContentProviderOperation.Builder scanItem(long j, File file, BasicFileAttributes basicFileAttributes, String str, int i, String str2) {
        if (Objects.equals(file.getName(), ".nomedia")) {
            if (!LOGD) {
                return null;
            }
            Log.d(TAG, "Ignoring .nomedia file: " + file);
            return null;
        }
        if (basicFileAttributes.isDirectory()) {
            return scanItemDirectory(j, file, basicFileAttributes, str, str2);
        }
        switch (i) {
            case 1:
                return scanItemImage(j, file, basicFileAttributes, str, i, str2);
            case 2:
                return scanItemAudio(j, file, basicFileAttributes, str, i, str2);
            case 3:
                return scanItemVideo(j, file, basicFileAttributes, str, i, str2);
            case 4:
                return scanItemPlaylist(j, file, basicFileAttributes, str, i, str2);
            case 5:
                return scanItemSubtitle(j, file, basicFileAttributes, str, i, str2);
            case 6:
                return scanItemDocument(j, file, basicFileAttributes, str, i, str2);
            default:
                return scanItemFile(j, file, basicFileAttributes, str, i, str2);
        }
    }

    private static void withGenericValues(ContentProviderOperation.Builder builder, File file, BasicFileAttributes basicFileAttributes, String str, Integer num) {
        withOptionalMimeTypeAndMediaType(builder, Optional.ofNullable(str), Optional.ofNullable(num));
        builder.withValue("_data", file.getAbsolutePath());
        builder.withValue("_size", Long.valueOf(basicFileAttributes.size()));
        builder.withValue("date_modified", Long.valueOf(lastModifiedTime(file, basicFileAttributes)));
        builder.withValue("datetaken", null);
        builder.withValue("is_drm", 0);
        builder.withValue("width", null);
        builder.withValue("height", null);
        builder.withValue("resolution", null);
        builder.withValue("document_id", null);
        builder.withValue("instance_id", null);
        builder.withValue("original_document_id", null);
        builder.withValue("orientation", null);
        builder.withValue("cd_track_number", null);
        builder.withValue("album", null);
        builder.withValue("artist", null);
        builder.withValue("author", null);
        builder.withValue("composer", null);
        builder.withValue("genre", null);
        builder.withValue("title", FileUtils.extractFileName(file.getName()));
        builder.withValue("year", null);
        builder.withValue("duration", null);
        builder.withValue("num_tracks", null);
        builder.withValue("writer", null);
        builder.withValue("album_artist", null);
        builder.withValue("disc_number", null);
        builder.withValue("compilation", null);
        builder.withValue("bitrate", null);
        builder.withValue("capture_framerate", null);
    }

    private static void withRetrieverValues(ContentProviderOperation.Builder builder, MediaMetadataRetriever mediaMetadataRetriever, String str) {
        withOptionalMimeTypeAndMediaType(builder, parseOptionalMimeType(str, mediaMetadataRetriever.extractMetadata(12)), Optional.empty());
        withOptionalValue(builder, "datetaken", parseOptionalDate(mediaMetadataRetriever.extractMetadata(5)));
        withOptionalValue(builder, "cd_track_number", parseOptional(mediaMetadataRetriever.extractMetadata(0)));
        withOptionalValue(builder, "album", parseOptional(mediaMetadataRetriever.extractMetadata(1)));
        withOptionalValue(builder, "artist", firstPresent(parseOptional(mediaMetadataRetriever.extractMetadata(2)), parseOptional(mediaMetadataRetriever.extractMetadata(13))));
        withOptionalValue(builder, "author", parseOptional(mediaMetadataRetriever.extractMetadata(3)));
        withOptionalValue(builder, "composer", parseOptional(mediaMetadataRetriever.extractMetadata(4)));
        withOptionalValue(builder, "genre", parseOptional(mediaMetadataRetriever.extractMetadata(6)));
        withOptionalValue(builder, "title", parseOptional(mediaMetadataRetriever.extractMetadata(7)));
        withOptionalValue(builder, "year", parseOptionalYear(mediaMetadataRetriever.extractMetadata(8)));
        withOptionalValue(builder, "duration", parseOptional(mediaMetadataRetriever.extractMetadata(9)));
        withOptionalValue(builder, "num_tracks", parseOptional(mediaMetadataRetriever.extractMetadata(10)));
        withOptionalValue(builder, "writer", parseOptional(mediaMetadataRetriever.extractMetadata(11)));
        withOptionalValue(builder, "album_artist", parseOptional(mediaMetadataRetriever.extractMetadata(13)));
        withOptionalValue(builder, "disc_number", parseOptional(mediaMetadataRetriever.extractMetadata(14)));
        withOptionalValue(builder, "compilation", parseOptional(mediaMetadataRetriever.extractMetadata(15)));
        withOptionalValue(builder, "bitrate", parseOptional(mediaMetadataRetriever.extractMetadata(20)));
        withOptionalValue(builder, "capture_framerate", parseOptional(mediaMetadataRetriever.extractMetadata(25)));
    }

    private static void withXmpValues(ContentProviderOperation.Builder builder, XmpInterface xmpInterface, String str) {
        withOptionalMimeTypeAndMediaType(builder, parseOptionalMimeType(str, xmpInterface.getFormat()), Optional.empty());
        builder.withValue("document_id", xmpInterface.getDocumentId());
        builder.withValue("instance_id", xmpInterface.getInstanceId());
        builder.withValue("original_document_id", xmpInterface.getOriginalDocumentId());
        builder.withValue("xmp", xmpInterface.getRedactedXmp());
    }

    private static void withOptionalValue(ContentProviderOperation.Builder builder, String str, Optional<?> optional) {
        if (optional.isPresent()) {
            builder.withValue(str, optional.get());
        }
    }

    private static void withOptionalMimeTypeAndMediaType(ContentProviderOperation.Builder builder, Optional<String> optional, Optional<Integer> optional2) {
        if (optional.isPresent()) {
            String str = optional.get();
            builder.withValue("mime_type", str);
            if (optional2.isPresent()) {
                builder.withValue("media_type", optional2.get());
            } else {
                builder.withValue("media_type", Integer.valueOf(MimeUtils.resolveMediaType(str)));
            }
        }
    }

    private static ContentProviderOperation.Builder scanItemDirectory(long j, File file, BasicFileAttributes basicFileAttributes, String str, String str2) {
        ContentProviderOperation.Builder builderNewUpsert = newUpsert(str2, j);
        withGenericValues(builderNewUpsert, file, basicFileAttributes, str, null);
        try {
            builderNewUpsert.withValue("format", 12289);
        } catch (Exception e) {
            logTroubleScanning(file, e);
        }
        return builderNewUpsert;
    }

    private static ContentProviderOperation.Builder scanItemAudio(long j, File file, BasicFileAttributes basicFileAttributes, String str, int i, String str2) {
        ContentProviderOperation.Builder builderNewUpsert = newUpsert(str2, j);
        withGenericValues(builderNewUpsert, file, basicFileAttributes, str, Integer.valueOf(i));
        builderNewUpsert.withValue("artist", "<unknown>");
        builderNewUpsert.withValue("album", file.getParentFile().getName());
        String lowerCase = file.getAbsolutePath().toLowerCase(Locale.ROOT);
        int i2 = 0;
        int i3 = 0;
        while (i2 < sAudioTypes.size()) {
            boolean zContains = lowerCase.contains('/' + sAudioTypes.keyAt(i2).toLowerCase(Locale.ROOT) + '/');
            builderNewUpsert.withValue(sAudioTypes.valueAt(i2), Integer.valueOf(zContains ? 1 : 0));
            i2++;
            i3 |= zContains ? 1 : 0;
        }
        if (i3 == 0) {
            builderNewUpsert.withValue("is_music", 1);
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                try {
                    mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
                    withRetrieverValues(builderNewUpsert, mediaMetadataRetriever, str);
                    withOptionalValue(builderNewUpsert, "track", parseOptionalTrack(mediaMetadataRetriever));
                    mediaMetadataRetriever.close();
                    withXmpValues(builderNewUpsert, XmpInterface.fromContainer(IsoInterface.fromFileDescriptor(fileInputStream.getFD())), str);
                    fileInputStream.close();
                } finally {
                }
            } finally {
            }
        } catch (Exception e) {
            logTroubleScanning(file, e);
        }
        return builderNewUpsert;
    }

    private static ContentProviderOperation.Builder scanItemPlaylist(long j, File file, BasicFileAttributes basicFileAttributes, String str, int i, String str2) {
        ContentProviderOperation.Builder builderNewUpsert = newUpsert(str2, j);
        withGenericValues(builderNewUpsert, file, basicFileAttributes, str, Integer.valueOf(i));
        try {
            builderNewUpsert.withValue("name", FileUtils.extractFileName(file.getName()));
        } catch (Exception e) {
            logTroubleScanning(file, e);
        }
        return builderNewUpsert;
    }

    private static ContentProviderOperation.Builder scanItemSubtitle(long j, File file, BasicFileAttributes basicFileAttributes, String str, int i, String str2) {
        ContentProviderOperation.Builder builderNewUpsert = newUpsert(str2, j);
        withGenericValues(builderNewUpsert, file, basicFileAttributes, str, Integer.valueOf(i));
        return builderNewUpsert;
    }

    private static ContentProviderOperation.Builder scanItemDocument(long j, File file, BasicFileAttributes basicFileAttributes, String str, int i, String str2) {
        ContentProviderOperation.Builder builderNewUpsert = newUpsert(str2, j);
        withGenericValues(builderNewUpsert, file, basicFileAttributes, str, Integer.valueOf(i));
        return builderNewUpsert;
    }

    private static ContentProviderOperation.Builder scanItemVideo(long j, File file, BasicFileAttributes basicFileAttributes, String str, int i, String str2) {
        FileInputStream fileInputStream;
        ContentProviderOperation.Builder builderNewUpsert = newUpsert(str2, j);
        withGenericValues(builderNewUpsert, file, basicFileAttributes, str, Integer.valueOf(i));
        builderNewUpsert.withValue("artist", "<unknown>");
        builderNewUpsert.withValue("album", file.getParentFile().getName());
        builderNewUpsert.withValue("color_standard", null);
        builderNewUpsert.withValue("color_transfer", null);
        builderNewUpsert.withValue("color_range", null);
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception e) {
            logTroubleScanning(file, e);
        }
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
                withRetrieverValues(builderNewUpsert, mediaMetadataRetriever, str);
                withOptionalValue(builderNewUpsert, "width", parseOptional(mediaMetadataRetriever.extractMetadata(18)));
                withOptionalValue(builderNewUpsert, "height", parseOptional(mediaMetadataRetriever.extractMetadata(19)));
                withOptionalValue(builderNewUpsert, "resolution", parseOptionalVideoResolution(mediaMetadataRetriever));
                withOptionalValue(builderNewUpsert, "orientation", parseOptional(mediaMetadataRetriever.extractMetadata(24)));
                withOptionalValue(builderNewUpsert, "color_standard", parseOptional(mediaMetadataRetriever.extractMetadata(35)));
                withOptionalValue(builderNewUpsert, "color_transfer", parseOptional(mediaMetadataRetriever.extractMetadata(36)));
                withOptionalValue(builderNewUpsert, "color_range", parseOptional(mediaMetadataRetriever.extractMetadata(37)));
                mediaMetadataRetriever.close();
                withXmpValues(builderNewUpsert, XmpInterface.fromContainer(IsoInterface.fromFileDescriptor(fileInputStream.getFD())), str);
                fileInputStream.close();
                return builderNewUpsert;
            } finally {
            }
        } finally {
        }
    }

    private static ContentProviderOperation.Builder scanItemImage(long j, File file, BasicFileAttributes basicFileAttributes, String str, int i, String str2) {
        ContentProviderOperation.Builder builderNewUpsert = newUpsert(str2, j);
        withGenericValues(builderNewUpsert, file, basicFileAttributes, str, Integer.valueOf(i));
        builderNewUpsert.withValue("description", null);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                ExifInterface exifInterface = new ExifInterface(fileInputStream);
                withOptionalValue(builderNewUpsert, "width", parseOptionalOrZero(exifInterface.getAttribute("ImageWidth")));
                withOptionalValue(builderNewUpsert, "height", parseOptionalOrZero(exifInterface.getAttribute("ImageLength")));
                withOptionalValue(builderNewUpsert, "resolution", parseOptionalResolution(exifInterface));
                withOptionalValue(builderNewUpsert, "datetaken", parseOptionalDateTaken(exifInterface, lastModifiedTime(file, basicFileAttributes) * 1000));
                withOptionalValue(builderNewUpsert, "orientation", parseOptionalOrientation(exifInterface.getAttributeInt("Orientation", 0)));
                withOptionalValue(builderNewUpsert, "description", parseOptional(exifInterface.getAttribute("ImageDescription")));
                withOptionalValue(builderNewUpsert, "exposure_time", parseOptional(exifInterface.getAttribute("ExposureTime")));
                withOptionalValue(builderNewUpsert, "f_number", parseOptional(exifInterface.getAttribute("FNumber")));
                withOptionalValue(builderNewUpsert, "iso", parseOptional(exifInterface.getAttribute("ISOSpeedRatings")));
                withOptionalValue(builderNewUpsert, "scene_capture_type", parseOptional(exifInterface.getAttribute("SceneCaptureType")));
                withXmpValues(builderNewUpsert, XmpInterface.fromContainer(exifInterface), str);
                fileInputStream.close();
            } finally {
            }
        } catch (Exception e) {
            logTroubleScanning(file, e);
        }
        return builderNewUpsert;
    }

    private static ContentProviderOperation.Builder scanItemFile(long j, File file, BasicFileAttributes basicFileAttributes, String str, int i, String str2) {
        ContentProviderOperation.Builder builderNewUpsert = newUpsert(str2, j);
        withGenericValues(builderNewUpsert, file, basicFileAttributes, str, Integer.valueOf(i));
        return builderNewUpsert;
    }

    private static ContentProviderOperation.Builder newUpsert(String str, long j) {
        Uri contentUri = MediaStore.Files.getContentUri(str);
        if (j == -1) {
            return ContentProviderOperation.newInsert(contentUri).withExceptionAllowed(true);
        }
        return ContentProviderOperation.newUpdate(ContentUris.withAppendedId(contentUri, j)).withExpectedCount(1).withExceptionAllowed(true);
    }

    @SafeVarargs
    private static <T> Optional<T> firstPresent(Optional<T>... optionalArr) {
        for (Optional<T> optional : optionalArr) {
            if (optional.isPresent()) {
                return optional;
            }
        }
        return Optional.empty();
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T> Optional<T> parseOptional(T t) {
        if (t == 0) {
            return Optional.empty();
        }
        boolean z = t instanceof String;
        if (z && ((String) t).length() == 0) {
            return Optional.empty();
        }
        if (z && ((String) t).equals("-1")) {
            return Optional.empty();
        }
        if (z && ((String) t).trim().length() == 0) {
            return Optional.empty();
        }
        if ((t instanceof Number) && ((Number) t).intValue() == -1) {
            return Optional.empty();
        }
        return Optional.of(t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static <T> Optional<T> parseOptionalOrZero(T t) {
        if ((t instanceof String) && isZero((String) t)) {
            return Optional.empty();
        }
        if ((t instanceof Number) && ((Number) t).intValue() == 0) {
            return Optional.empty();
        }
        return parseOptional(t);
    }

    static Optional<Integer> parseOptionalNumerator(String str) {
        Optional optional = parseOptional(str);
        if (optional.isPresent()) {
            String strSubstring = (String) optional.get();
            int iIndexOf = strSubstring.indexOf(47);
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(0, iIndexOf);
            }
            try {
                return Optional.of(Integer.valueOf(Integer.parseInt(strSubstring)));
            } catch (NumberFormatException unused) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    static Optional<Long> parseOptionalDateTaken(ExifInterface exifInterface, long j) {
        long dateTimeOriginal = ExifUtils.getDateTimeOriginal(exifInterface);
        if (exifInterface.hasAttribute("OffsetTimeOriginal")) {
            return Optional.of(Long.valueOf(dateTimeOriginal));
        }
        long gpsDateTime = ExifUtils.getGpsDateTime(exifInterface);
        if (gpsDateTime > 0) {
            long j2 = gpsDateTime - dateTimeOriginal;
            if (Math.abs(j2) < 86400000) {
                return Optional.of(Long.valueOf(dateTimeOriginal + (((long) Math.round(j2 / 900000.0f)) * 900000)));
            }
        }
        if (j > 0) {
            long j3 = j - dateTimeOriginal;
            if (Math.abs(j3) < 86400000) {
                return Optional.of(Long.valueOf(dateTimeOriginal + (((long) Math.round(j3 / 900000.0f)) * 900000)));
            }
        }
        return Optional.empty();
    }

    static Optional<Integer> parseOptionalOrientation(int i) {
        if (i == 1) {
            return Optional.of(0);
        }
        if (i == 3) {
            return Optional.of(180);
        }
        if (i == 6) {
            return Optional.of(90);
        }
        if (i == 8) {
            return Optional.of(270);
        }
        return Optional.empty();
    }

    static Optional<String> parseOptionalVideoResolution(MediaMetadataRetriever mediaMetadataRetriever) {
        Optional optional = parseOptional(mediaMetadataRetriever.extractMetadata(18));
        Optional optional2 = parseOptional(mediaMetadataRetriever.extractMetadata(19));
        if (optional.isPresent() && optional2.isPresent()) {
            return Optional.of(optional.get() + "×" + optional2.get());
        }
        return Optional.empty();
    }

    static Optional<String> parseOptionalImageResolution(MediaMetadataRetriever mediaMetadataRetriever) {
        Optional optional = parseOptional(mediaMetadataRetriever.extractMetadata(29));
        Optional optional2 = parseOptional(mediaMetadataRetriever.extractMetadata(30));
        if (optional.isPresent() && optional2.isPresent()) {
            return Optional.of(optional.get() + "×" + optional2.get());
        }
        return Optional.empty();
    }

    static Optional<String> parseOptionalResolution(ExifInterface exifInterface) {
        Optional optionalOrZero = parseOptionalOrZero(exifInterface.getAttribute("ImageWidth"));
        Optional optionalOrZero2 = parseOptionalOrZero(exifInterface.getAttribute("ImageLength"));
        if (optionalOrZero.isPresent() && optionalOrZero2.isPresent()) {
            return Optional.of(optionalOrZero.get() + "×" + optionalOrZero2.get());
        }
        return Optional.empty();
    }

    static Optional<Long> parseOptionalDate(String str) {
        Optional<Long> optionalOf;
        if (TextUtils.isEmpty(str)) {
            return Optional.empty();
        }
        try {
            synchronized (sDateFormat) {
                long time = sDateFormat.parse(str).getTime();
                optionalOf = time > 0 ? Optional.of(Long.valueOf(time)) : Optional.empty();
            }
            return optionalOf;
        } catch (ParseException unused) {
            return Optional.empty();
        }
    }

    static Optional<Integer> parseOptionalYear(String str) {
        Optional optional = parseOptional(str);
        if (optional.isPresent()) {
            Matcher matcher = PATTERN_YEAR.matcher((CharSequence) optional.get());
            if (matcher.find()) {
                return Optional.of(Integer.valueOf(Integer.parseInt(matcher.group(1))));
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    static Optional<Integer> parseOptionalTrack(MediaMetadataRetriever mediaMetadataRetriever) {
        Optional<Integer> optionalNumerator = parseOptionalNumerator(mediaMetadataRetriever.extractMetadata(14));
        Optional<Integer> optionalNumerator2 = parseOptionalNumerator(mediaMetadataRetriever.extractMetadata(0));
        return (optionalNumerator.isPresent() && optionalNumerator2.isPresent()) ? Optional.of(Integer.valueOf((optionalNumerator.get().intValue() * 1000) + optionalNumerator2.get().intValue())) : optionalNumerator2;
    }

    static Optional<String> parseOptionalMimeType(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return Optional.empty();
        }
        int iIndexOf = str2.indexOf(47);
        if (iIndexOf == -1) {
            return Optional.empty();
        }
        if (str.regionMatches(true, 0, str2, 0, iIndexOf + 1)) {
            return Optional.of(str2);
        }
        if ("video/mp4".equalsIgnoreCase(str) && "audio/mp4".equalsIgnoreCase(str2)) {
            return Optional.of(str2);
        }
        return Optional.empty();
    }

    public static long lastModifiedTime(File file, BasicFileAttributes basicFileAttributes) {
        if (FileUtils.contains(Environment.getStorageDirectory(), file)) {
            return basicFileAttributes.lastModifiedTime().toMillis() / 1000;
        }
        return Build.TIME / 1000;
    }

    static Pair<Boolean, Boolean> shouldScanPathAndIsPathHidden(File file) {
        Boolean bool = Boolean.FALSE;
        Trace.beginSection("shouldScanPathAndIsPathHiodden");
        boolean z = false;
        while (file != null) {
            try {
                if (!shouldScanDirectory(file)) {
                    return Pair.create(bool, bool);
                }
                z = z || FileUtils.isDirectoryHidden(file);
                file = file.getParentFile();
            } finally {
                Trace.endSection();
            }
        }
        return Pair.create(Boolean.TRUE, Boolean.valueOf(z));
    }

    static boolean shouldScanDirectory(File file) {
        File file2 = new File(file, ".nomedia");
        if (PATTERN_VISIBLE.matcher(file.getAbsolutePath()).matches()) {
            file2.delete();
            return true;
        }
        if (!PATTERN_INVISIBLE.matcher(file.getAbsolutePath()).matches()) {
            return true;
        }
        try {
            file2.createNewFile();
            return false;
        } catch (IOException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int resolveMediaTypeFromFilePath(File file, String str, boolean z) {
        int iResolveMediaType = MimeUtils.resolveMediaType(str);
        if (z || FileUtils.isFileHidden(file)) {
            iResolveMediaType = 0;
        }
        if (iResolveMediaType == 1 && isFileAlbumArt(file)) {
            return 0;
        }
        return iResolveMediaType;
    }

    static boolean isFileAlbumArt(File file) {
        return PATTERN_ALBUM_ART.matcher(file.getName()).matches();
    }

    static boolean isZero(String str) {
        if (str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

    static void logTroubleScanning(File file, Exception exc) {
        if (LOGW) {
            Log.w(TAG, "Trouble scanning " + file + ": " + exc);
        }
    }
}
