package com.android.providers.media;

import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.app.RecoverableSecurityException;
import android.app.RemoteAction;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ContentProvider;
import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.OperationApplicationException;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.ProviderInfo;
import android.content.res.AssetFileDescriptor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.icu.util.ULocale;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Environment;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.Trace;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.preference.PreferenceManager;
import android.provider.Column;
import android.provider.MediaStore;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructStat;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.Size;
import android.util.SparseArray;
import androidx.annotation.Keep;
import com.android.providers.media.DatabaseHelper;
import com.android.providers.media.fuse.ExternalStorageServiceImpl;
import com.android.providers.media.fuse.FuseDaemon;
import com.android.providers.media.playlist.Playlist;
import com.android.providers.media.scan.MediaScanner;
import com.android.providers.media.scan.ModernMediaScanner;
import com.android.providers.media.util.BackgroundThread;
import com.android.providers.media.util.CachedSupplier;
import com.android.providers.media.util.DatabaseUtils;
import com.android.providers.media.util.FileUtils;
import com.android.providers.media.util.ForegroundThread;
import com.android.providers.media.util.IsoInterface;
import com.android.providers.media.util.Logging;
import com.android.providers.media.util.LongArray;
import com.android.providers.media.util.Metrics;
import com.android.providers.media.util.MimeUtils;
import com.android.providers.media.util.PermissionUtils;
import com.android.providers.media.util.RedactingFileDescriptor;
import com.android.providers.media.util.SQLiteQueryBuilder;
import com.android.providers.media.util.XmpInterface;
import com.google.common.hash.Hashing;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class MediaProvider extends ContentProvider {
    static final int AUDIO_ALBUMART = 119;
    static final int AUDIO_ALBUMART_FILE_ID = 121;
    static final int AUDIO_ALBUMART_ID = 120;
    static final int AUDIO_ALBUMS = 116;
    static final int AUDIO_ALBUMS_ID = 117;
    static final int AUDIO_ARTISTS = 114;
    static final int AUDIO_ARTISTS_ID = 115;
    static final int AUDIO_ARTISTS_ID_ALBUMS = 118;
    static final int AUDIO_GENRES = 106;
    static final int AUDIO_GENRES_ALL_MEMBERS = 109;
    static final int AUDIO_GENRES_ID = 107;
    static final int AUDIO_GENRES_ID_MEMBERS = 108;
    static final int AUDIO_MEDIA = 100;
    static final int AUDIO_MEDIA_ID = 101;
    static final int AUDIO_MEDIA_ID_GENRES = 102;
    static final int AUDIO_MEDIA_ID_GENRES_ID = 103;
    static final int AUDIO_PLAYLISTS = 110;
    static final int AUDIO_PLAYLISTS_ID = 111;
    static final int AUDIO_PLAYLISTS_ID_MEMBERS = 112;
    static final int AUDIO_PLAYLISTS_ID_MEMBERS_ID = 113;
    private static final String CANONICAL = "canonical";
    private static final String DIRECTORY_ALARMS = "Alarms";
    private static final String DIRECTORY_ANDROID = "Android";
    private static final String DIRECTORY_AUDIOBOOKS = "Audiobooks";
    private static final String DIRECTORY_DCIM = "DCIM";
    private static final String DIRECTORY_DOCUMENTS = "Documents";
    private static final String DIRECTORY_DOWNLOADS = "Download";
    private static final String DIRECTORY_MEDIA = "media";
    private static final String DIRECTORY_MOVIES = "Movies";
    private static final String DIRECTORY_MUSIC = "Music";
    private static final String DIRECTORY_NOTIFICATIONS = "Notifications";
    private static final String DIRECTORY_PICTURES = "Pictures";
    private static final String DIRECTORY_PODCASTS = "Podcasts";
    private static final String DIRECTORY_RINGTONES = "Ringtones";
    private static final String DIRECTORY_THUMBNAILS = ".thumbnails";
    static final int DOWNLOADS = 800;
    static final int DOWNLOADS_ID = 801;
    static final int FILES = 700;
    static final int FILES_ID = 701;
    private static final String FILE_DATABASE_UUID = ".database_uuid";
    static final int FS_ID = 600;
    private static final String ID_NOT_PARENT_CLAUSE = "_id NOT IN (SELECT parent FROM files WHERE parent IS NOT NULL)";
    static final int IMAGES_MEDIA = 1;
    static final int IMAGES_MEDIA_ID = 2;
    static final int IMAGES_MEDIA_ID_THUMBNAIL = 3;
    static final int IMAGES_THUMBNAILS = 4;
    static final int IMAGES_THUMBNAILS_ID = 5;
    private static final String INCLUDED_DEFAULT_DIRECTORIES = "android:included-default-directories";
    private static final int MATCH_VISIBLE_FOR_FILEPATH = 32;
    static final int MEDIA_SCANNER = 500;
    private static final String PROP_FUSE = "persist.sys.fuse";
    private static final int TYPE_DELETE = 3;
    private static final int TYPE_INSERT = 1;
    private static final int TYPE_QUERY = 0;
    private static final int TYPE_UPDATE = 2;
    static final int VERSION = 601;
    static final int VIDEO_MEDIA = 200;
    static final int VIDEO_MEDIA_ID = 201;
    static final int VIDEO_MEDIA_ID_THUMBNAIL = 202;
    static final int VIDEO_THUMBNAILS = 203;
    static final int VIDEO_THUMBNAILS_ID = 204;
    static final int VOLUMES = 300;
    static final int VOLUMES_ID = 301;
    private final AppOpsManager.OnOpActiveChangedListener mActiveListener;
    private AppOpsManager mAppOpsManager;
    private final ArraySet<String> mAttachedVolumeNames;
    private Thumbnailer mAudioThumbnailer;
    private final SparseArray<LocalCallingIdentity> mCachedCallingIdentity;
    private final SparseArray<LocalCallingIdentity> mCachedCallingIdentityForFuse;
    private final ThreadLocal<LocalCallingIdentity> mCallingIdentity;
    private final ArraySet<String> mCustomCollators;
    private final ArrayMap<String, Long> mDirectoryCache;
    private DatabaseHelper mExternalDatabase;
    private final DatabaseHelper.OnFilesChangeListener mFilesListener;
    private final UnaryOperator<String> mIdGenerator;
    private Thumbnailer mImageThumbnailer;
    private DatabaseHelper mInternalDatabase;
    private MediaScanner mMediaScanner;
    private String mMediaScannerVolume;
    private AppOpsManager.OnOpChangedListener mModeListener;
    private PackageManager mPackageManager;
    private BroadcastReceiver mPackageReceiver;
    private final LongArray mShouldRedactThreadIds;
    private StorageManager mStorageManager;
    private Size mThumbSize;
    private final Binder.ProxyTransactListener mTransactListener;
    private LocalUriMatcher mUriMatcher;
    private Thumbnailer mVideoThumbnailer;
    private int mVolumeId;
    static final Pattern PATTERN_SELECTION_ID = Pattern.compile("(?:image_id|video_id)\\s*=\\s*(\\d+)");
    private static final String MATCH_PENDING_FROM_FUSE = String.format("lower(%s) NOT REGEXP '%s'", "_data", FileUtils.PATTERN_PENDING_FILEPATH_FOR_SQL);
    private static final ArrayMap<String, Object> sDataColumns = new ArrayMap<>();
    private static final Object sCacheLock = new Object();
    private static final Set<String> sCachedExternalVolumeNames = new ArraySet();
    private static final Map<String, File> sCachedVolumePaths = new ArrayMap();
    private static final Map<String, Collection<File>> sCachedVolumeScanPaths = new ArrayMap();
    private static final ArrayMap<File, String> sCachedVolumePathToId = new ArrayMap<>();
    private static final String[] sDataOnlyColumn = {"_data"};
    public static final DatabaseHelper.OnLegacyMigrationListener MIGRATION_LISTENER = new DatabaseHelper.OnLegacyMigrationListener() { // from class: com.android.providers.media.MediaProvider.4
        @Override // com.android.providers.media.DatabaseHelper.OnLegacyMigrationListener
        public void onProgress(ContentProviderClient contentProviderClient, String str, long j, long j2) {
        }

        @Override // com.android.providers.media.DatabaseHelper.OnLegacyMigrationListener
        public void onStarted(ContentProviderClient contentProviderClient, String str) {
            MediaStore.startLegacyMigration(ContentResolver.wrap(contentProviderClient), str);
        }

        @Override // com.android.providers.media.DatabaseHelper.OnLegacyMigrationListener
        public void onFinished(ContentProviderClient contentProviderClient, String str) {
            MediaStore.finishLegacyMigration(ContentResolver.wrap(contentProviderClient), str);
        }
    };
    private static final String[] REDACTED_EXIF_TAGS = {"GPSAltitude", "GPSAltitudeRef", "GPSAreaInformation", "GPSDOP", "GPSDateStamp", "GPSDestBearing", "GPSDestBearingRef", "GPSDestDistance", "GPSDestDistanceRef", "GPSDestLatitude", "GPSDestLatitudeRef", "GPSDestLongitude", "GPSDestLongitudeRef", "GPSDifferential", "GPSImgDirection", "GPSImgDirectionRef", "GPSLatitude", "GPSLatitudeRef", "GPSLongitude", "GPSLongitudeRef", "GPSMapDatum", "GPSMeasureMode", "GPSProcessingMethod", "GPSSatellites", "GPSSpeed", "GPSSpeedRef", "GPSStatus", "GPSTimeStamp", "GPSTrack", "GPSTrackRef", "GPSVersionID"};
    private static final int[] REDACTED_ISO_BOXES = {IsoInterface.BOX_LOCI, IsoInterface.BOX_XYZ, IsoInterface.BOX_GPS, IsoInterface.BOX_GPS0};
    public static final Set<String> sRedactedExifTags = new ArraySet(Arrays.asList(REDACTED_EXIF_TAGS));
    private static final String[] PATH_PROJECTION = {"_id", "_data"};
    private static final ArraySet<String> sMutableColumns = new ArraySet<>();
    private static final ArraySet<String> sPlacementColumns = new ArraySet<>();
    static final ArrayList<Pattern> sGreylist = new ArrayList<>();

    public MediaProvider() {
        sDataColumns.put("_data", null);
        sDataColumns.put("_data", null);
        sDataColumns.put("_data", null);
        sDataColumns.put("_data", null);
        sDataColumns.put("album_art", null);
        this.mShouldRedactThreadIds = new LongArray();
        this.mCachedCallingIdentity = new SparseArray<>();
        this.mActiveListener = new AppOpsManager.OnOpActiveChangedListener() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$zzbBPses8qCuVDntfxAMM7hPnyI
            @Override // android.app.AppOpsManager.OnOpActiveChangedListener
            public final void onOpActiveChanged(String str, int i, String str2, boolean z) {
                this.f$0.lambda$new$1$MediaProvider(str, i, str2, z);
            }
        };
        this.mCachedCallingIdentityForFuse = new SparseArray<>();
        this.mModeListener = new AppOpsManager.OnOpChangedListener() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$vH68ZyRa5ZtWWiD0JYR19W_k2Pk
            @Override // android.app.AppOpsManager.OnOpChangedListener
            public final void onOpChanged(String str, String str2) {
                this.f$0.lambda$new$2$MediaProvider(str, str2);
            }
        };
        this.mCallingIdentity = ThreadLocal.withInitial(new Supplier() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$5P4DLDQvZ2kc1HR2A_5XtxnnQ74
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.f$0.lambda$new$3$MediaProvider();
            }
        });
        this.mTransactListener = new Binder.ProxyTransactListener() { // from class: com.android.providers.media.MediaProvider.1
            public Object onTransactStarted(IBinder iBinder, int i) {
                if (Logging.LOGV) {
                    Trace.beginSection(Thread.currentThread().getStackTrace()[5].getMethodName());
                }
                return Long.valueOf(Binder.setCallingWorkSourceUid(((LocalCallingIdentity) MediaProvider.this.mCallingIdentity.get()).uid));
            }

            public void onTransactEnded(Object obj) {
                Binder.restoreCallingWorkSource(((Long) obj).longValue());
                if (Logging.LOGV) {
                    Trace.endSection();
                }
            }
        };
        this.mDirectoryCache = new ArrayMap<>();
        this.mPackageReceiver = new BroadcastReceiver() { // from class: com.android.providers.media.MediaProvider.2
            /* JADX WARN: Removed duplicated region for block: B:13:0x0028  */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onReceive(android.content.Context r4, android.content.Intent r5) {
                /*
                    r3 = this;
                    java.lang.String r4 = r5.getAction()
                    int r0 = r4.hashCode()
                    r1 = 525384130(0x1f50b9c2, float:4.419937E-20)
                    r2 = 1
                    if (r0 == r1) goto L1e
                    r1 = 1544582882(0x5c1076e2, float:1.6265244E17)
                    if (r0 == r1) goto L14
                    goto L28
                L14:
                    java.lang.String r0 = "android.intent.action.PACKAGE_ADDED"
                    boolean r4 = r4.equals(r0)
                    if (r4 == 0) goto L28
                    r4 = r2
                    goto L29
                L1e:
                    java.lang.String r0 = "android.intent.action.PACKAGE_REMOVED"
                    boolean r4 = r4.equals(r0)
                    if (r4 == 0) goto L28
                    r4 = 0
                    goto L29
                L28:
                    r4 = -1
                L29:
                    if (r4 == 0) goto L2e
                    if (r4 == r2) goto L2e
                    goto L71
                L2e:
                    android.net.Uri r4 = r5.getData()
                    if (r4 == 0) goto L39
                    java.lang.String r4 = r4.getSchemeSpecificPart()
                    goto L3a
                L39:
                    r4 = 0
                L3a:
                    if (r4 == 0) goto L57
                    com.android.providers.media.MediaProvider r3 = com.android.providers.media.MediaProvider.this
                    java.lang.StringBuilder r0 = new java.lang.StringBuilder
                    r0.<init>()
                    java.lang.String r1 = "package "
                    r0.append(r1)
                    java.lang.String r5 = r5.getAction()
                    r0.append(r5)
                    java.lang.String r5 = r0.toString()
                    com.android.providers.media.MediaProvider.access$100(r3, r4, r5)
                    goto L71
                L57:
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r4 = "Failed to retrieve package from intent: "
                    r3.append(r4)
                    java.lang.String r4 = r5.getAction()
                    r3.append(r4)
                    java.lang.String r3 = r3.toString()
                    java.lang.String r4 = "MediaProvider"
                    android.util.Log.w(r4, r3)
                L71:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.MediaProvider.AnonymousClass2.onReceive(android.content.Context, android.content.Intent):void");
            }
        };
        this.mFilesListener = new AnonymousClass3();
        this.mIdGenerator = new UnaryOperator() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$ovdhzjNF-N478oRw_XRNF5Z65tg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$new$4$MediaProvider((String) obj);
            }
        };
        this.mAudioThumbnailer = new Thumbnailer(Environment.DIRECTORY_MUSIC) { // from class: com.android.providers.media.MediaProvider.6
            @Override // com.android.providers.media.MediaProvider.Thumbnailer
            public Bitmap getThumbnailBitmap(Uri uri, CancellationSignal cancellationSignal) throws IOException {
                return ThumbnailUtils.createAudioThumbnail(MediaProvider.this.queryForDataFile(uri, cancellationSignal), MediaProvider.this.mThumbSize, cancellationSignal);
            }
        };
        this.mVideoThumbnailer = new Thumbnailer(Environment.DIRECTORY_MOVIES) { // from class: com.android.providers.media.MediaProvider.7
            @Override // com.android.providers.media.MediaProvider.Thumbnailer
            public Bitmap getThumbnailBitmap(Uri uri, CancellationSignal cancellationSignal) throws IOException {
                return ThumbnailUtils.createVideoThumbnail(MediaProvider.this.queryForDataFile(uri, cancellationSignal), MediaProvider.this.mThumbSize, cancellationSignal);
            }
        };
        this.mImageThumbnailer = new Thumbnailer(Environment.DIRECTORY_PICTURES) { // from class: com.android.providers.media.MediaProvider.8
            @Override // com.android.providers.media.MediaProvider.Thumbnailer
            public Bitmap getThumbnailBitmap(Uri uri, CancellationSignal cancellationSignal) throws IOException {
                return ThumbnailUtils.createImageThumbnail(MediaProvider.this.queryForDataFile(uri, cancellationSignal), MediaProvider.this.mThumbSize, cancellationSignal);
            }
        };
        this.mAttachedVolumeNames = new ArraySet<>();
        this.mCustomCollators = new ArraySet<>();
        this.mVolumeId = -1;
        sMutableColumns.add("_data");
        sMutableColumns.add("relative_path");
        sMutableColumns.add("_display_name");
        sMutableColumns.add("is_pending");
        sMutableColumns.add("is_trashed");
        sMutableColumns.add("is_favorite");
        sMutableColumns.add("owner_package_name");
        sMutableColumns.add("bookmark");
        sMutableColumns.add("tags");
        sMutableColumns.add("category");
        sMutableColumns.add("bookmark");
        sMutableColumns.add("name");
        sMutableColumns.add("audio_id");
        sMutableColumns.add("play_order");
        sMutableColumns.add("download_uri");
        sMutableColumns.add("referer_uri");
        sMutableColumns.add("mime_type");
        sMutableColumns.add("media_type");
        sPlacementColumns.add("_data");
        sPlacementColumns.add("relative_path");
        sPlacementColumns.add("_display_name");
        sPlacementColumns.add("mime_type");
        sPlacementColumns.add("is_pending");
        sPlacementColumns.add("is_trashed");
        sPlacementColumns.add("date_expires");
    }

    static {
        addGreylistPattern("(?i)[_a-z0-9]+( (as )?[_a-z0-9]+)?");
        addGreylistPattern("audio\\._id AS _id");
        addGreylistPattern("(?i)(min|max|sum|avg|total|count|cast)\\(([_a-z0-9]+( (as )?[_a-z0-9]+)?|\\*)\\)( (as )?[_a-z0-9]+)?");
        addGreylistPattern("case when case when \\(date_added >= \\d+ and date_added < \\d+\\) then date_added \\* \\d+ when \\(date_added >= \\d+ and date_added < \\d+\\) then date_added when \\(date_added >= \\d+ and date_added < \\d+\\) then date_added / \\d+ else \\d+ end > case when \\(date_modified >= \\d+ and date_modified < \\d+\\) then date_modified \\* \\d+ when \\(date_modified >= \\d+ and date_modified < \\d+\\) then date_modified when \\(date_modified >= \\d+ and date_modified < \\d+\\) then date_modified / \\d+ else \\d+ end then case when \\(date_added >= \\d+ and date_added < \\d+\\) then date_added \\* \\d+ when \\(date_added >= \\d+ and date_added < \\d+\\) then date_added when \\(date_added >= \\d+ and date_added < \\d+\\) then date_added / \\d+ else \\d+ end else case when \\(date_modified >= \\d+ and date_modified < \\d+\\) then date_modified \\* \\d+ when \\(date_modified >= \\d+ and date_modified < \\d+\\) then date_modified when \\(date_modified >= \\d+ and date_modified < \\d+\\) then date_modified / \\d+ else \\d+ end end as corrected_added_modified");
        addGreylistPattern("MAX\\(case when \\(datetaken >= \\d+ and datetaken < \\d+\\) then datetaken \\* \\d+ when \\(datetaken >= \\d+ and datetaken < \\d+\\) then datetaken when \\(datetaken >= \\d+ and datetaken < \\d+\\) then datetaken / \\d+ else \\d+ end\\)");
        addGreylistPattern("MAX\\(case when \\(date_added >= \\d+ and date_added < \\d+\\) then date_added \\* \\d+ when \\(date_added >= \\d+ and date_added < \\d+\\) then date_added when \\(date_added >= \\d+ and date_added < \\d+\\) then date_added / \\d+ else \\d+ end\\)");
        addGreylistPattern("MAX\\(case when \\(date_modified >= \\d+ and date_modified < \\d+\\) then date_modified \\* \\d+ when \\(date_modified >= \\d+ and date_modified < \\d+\\) then date_modified when \\(date_modified >= \\d+ and date_modified < \\d+\\) then date_modified / \\d+ else \\d+ end\\)");
        addGreylistPattern("\"content://media/[a-z]+/audio/media\"");
        addGreylistPattern("substr\\(_data, length\\(_data\\)-length\\(_display_name\\), 1\\) as filename_prevchar");
        addGreylistPattern("\\*( (as )?[_a-z0-9]+)?");
        addGreylistPattern("case when \\(datetaken >= \\d+ and datetaken < \\d+\\) then datetaken \\* \\d+ when \\(datetaken >= \\d+ and datetaken < \\d+\\) then datetaken when \\(datetaken >= \\d+ and datetaken < \\d+\\) then datetaken / \\d+ else \\d+ end");
    }

    public void updateVolumes() {
        synchronized (sCacheLock) {
            sCachedExternalVolumeNames.clear();
            sCachedExternalVolumeNames.addAll(MediaStore.getExternalVolumeNames(getContext()));
            Log.v(Logging.TAG, "Updated external volumes to: " + sCachedExternalVolumeNames.toString());
            sCachedVolumePaths.clear();
            sCachedVolumeScanPaths.clear();
            sCachedVolumePathToId.clear();
            try {
                sCachedVolumeScanPaths.put("internal", FileUtils.getVolumeScanPaths(getContext(), "internal"));
            } catch (FileNotFoundException e) {
                Log.wtf(Logging.TAG, "Failed to update volume internal", e);
            }
            for (String str : sCachedExternalVolumeNames) {
                try {
                    StorageVolume storageVolume = this.mStorageManager.getStorageVolume(MediaStore.Files.getContentUri(str));
                    sCachedVolumePaths.put(str, storageVolume.getDirectory());
                    sCachedVolumeScanPaths.put(str, FileUtils.getVolumeScanPaths(getContext(), str));
                    sCachedVolumePathToId.put(storageVolume.getDirectory(), storageVolume.getId());
                } catch (FileNotFoundException | IllegalStateException e2) {
                    Log.wtf(Logging.TAG, "Failed to update volume " + str, e2);
                }
            }
        }
        ForegroundThread.getExecutor().execute(new Runnable() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$xkbvwfxDRx2gMdY_Cje8WjPqJTM
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$updateVolumes$0$MediaProvider();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateVolumes$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateVolumes$0$MediaProvider() {
        this.mExternalDatabase.setFilterVolumeNames(getExternalVolumeNames());
    }

    public File getVolumePath(String str) throws FileNotFoundException {
        if (getContext() == null) {
            return Environment.getExternalStorageDirectory();
        }
        synchronized (sCacheLock) {
            if (sCachedVolumePaths.containsKey(str)) {
                return sCachedVolumePaths.get(str);
            }
            File volumePath = FileUtils.getVolumePath(getContext(), str);
            if (volumePath == null) {
                return Environment.getExternalStorageDirectory();
            }
            sCachedVolumePaths.put(str, volumePath);
            return volumePath;
        }
    }

    public String getVolumeId(File file) throws FileNotFoundException {
        synchronized (sCacheLock) {
            for (int i = 0; i < sCachedVolumePathToId.size(); i++) {
                if (FileUtils.contains(sCachedVolumePathToId.keyAt(i), file)) {
                    return sCachedVolumePathToId.valueAt(i);
                }
            }
            StorageVolume storageVolume = this.mStorageManager.getStorageVolume(file);
            if (storageVolume == null) {
                throw new FileNotFoundException("Missing volume for " + file);
            }
            sCachedVolumePathToId.put(storageVolume.getDirectory(), storageVolume.getId());
            return storageVolume.getId();
        }
    }

    public Set<String> getExternalVolumeNames() {
        ArraySet arraySet;
        synchronized (sCacheLock) {
            arraySet = new ArraySet(sCachedExternalVolumeNames);
        }
        return arraySet;
    }

    public Collection<File> getVolumeScanPaths(String str) throws FileNotFoundException {
        synchronized (sCacheLock) {
            if (sCachedVolumeScanPaths.containsKey(str)) {
                return new ArrayList(sCachedVolumeScanPaths.get(str));
            }
            Collection<File> volumeScanPaths = FileUtils.getVolumeScanPaths(getContext(), str);
            sCachedVolumeScanPaths.put(str, volumeScanPaths);
            return volumeScanPaths;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$1$MediaProvider(String str, int i, String str2, boolean z) {
        synchronized (this.mCachedCallingIdentity) {
            if (z) {
                this.mCachedCallingIdentity.put(i, LocalCallingIdentity.fromExternal(getContext(), i, str2, null));
            } else {
                this.mCachedCallingIdentity.remove(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$2, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$2$MediaProvider(String str, String str2) {
        invalidateLocalCallingIdentityCache(str2, "op " + str);
    }

    private LocalCallingIdentity getCachedCallingIdentityForFuse(int i) {
        LocalCallingIdentity localCallingIdentityFromExternal;
        synchronized (this.mCachedCallingIdentityForFuse) {
            PermissionUtils.setOpDescription("via FUSE");
            localCallingIdentityFromExternal = this.mCachedCallingIdentityForFuse.get(i);
            if (localCallingIdentityFromExternal == null) {
                localCallingIdentityFromExternal = LocalCallingIdentity.fromExternal(getContext(), i);
                this.mCachedCallingIdentityForFuse.put(i, localCallingIdentityFromExternal);
            }
        }
        return localCallingIdentityFromExternal;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$3, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ LocalCallingIdentity lambda$new$3$MediaProvider() {
        LocalCallingIdentity localCallingIdentityFromBinder;
        PermissionUtils.setOpDescription("via MediaProvider");
        synchronized (this.mCachedCallingIdentity) {
            localCallingIdentityFromBinder = this.mCachedCallingIdentity.get(Binder.getCallingUid());
            if (localCallingIdentityFromBinder == null) {
                localCallingIdentityFromBinder = LocalCallingIdentity.fromBinder(getContext(), this);
            }
        }
        return localCallingIdentityFromBinder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateLocalCallingIdentityCache(String str, String str2) {
        synchronized (this.mCachedCallingIdentityForFuse) {
            try {
                Log.i(Logging.TAG, "Invalidating LocalCallingIdentity cache for package " + str + ". Reason: " + str2);
                this.mCachedCallingIdentityForFuse.remove(getContext().getPackageManager().getPackageUid(str, 0));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateQuotaTypeForUri(Uri uri, int i) {
        Trace.beginSection("updateQuotaTypeForUri");
        try {
            File fileQueryForDataFile = queryForDataFile(uri, null);
            if (fileQueryForDataFile.exists()) {
                if (i == 0) {
                    i = MimeUtils.resolveMediaType(MimeUtils.resolveMimeType(fileQueryForDataFile));
                }
                updateQuotaTypeForFileInternal(fileQueryForDataFile, i);
            }
        } catch (FileNotFoundException unused) {
        } finally {
            Trace.endSection();
        }
    }

    private final void updateQuotaTypeForFileInternal(File file, int i) {
        try {
            if (i == 1) {
                this.mStorageManager.updateExternalStorageFileQuotaType(file, 1);
            } else if (i == 2) {
                this.mStorageManager.updateExternalStorageFileQuotaType(file, 2);
            } else if (i == 3) {
                this.mStorageManager.updateExternalStorageFileQuotaType(file, 3);
            } else {
                this.mStorageManager.updateExternalStorageFileQuotaType(file, 0);
            }
        } catch (IOException unused) {
            Log.w(Logging.TAG, "Failed to update quota type for " + file.getPath());
        }
    }

    /* JADX INFO: renamed from: com.android.providers.media.MediaProvider$3, reason: invalid class name */
    class AnonymousClass3 implements DatabaseHelper.OnFilesChangeListener {
        AnonymousClass3() {
        }

        @Override // com.android.providers.media.DatabaseHelper.OnFilesChangeListener
        public void onInsert(final DatabaseHelper databaseHelper, final String str, final long j, final int i, boolean z) {
            MediaProvider.this.handleInsertedRowForFuse(j);
            MediaProvider mediaProvider = MediaProvider.this;
            Objects.requireNonNull(databaseHelper);
            mediaProvider.acceptWithExpansion(new Consumer() { // from class: com.android.providers.media.-$$Lambda$F0-4cTH6KEr8uvNh4CuCOJj4agU
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    databaseHelper.notifyInsert((Uri) obj);
                }
            }, str, j, i, z);
            databaseHelper.postBackground(new Runnable() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$3$OA4Hj-5lam3D4oprnbu7ZpL-f_E
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onInsert$0$MediaProvider$3(databaseHelper, str, j, i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onInsert$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onInsert$0$MediaProvider$3(DatabaseHelper databaseHelper, String str, long j, int i) {
            if (databaseHelper.isExternal()) {
                MediaProvider.this.updateQuotaTypeForUri(MediaStore.Files.getContentUri(str, j), i);
            }
            MediaDocumentsProvider.onMediaStoreInsert(MediaProvider.this.getContext(), str, i, j);
        }

        @Override // com.android.providers.media.DatabaseHelper.OnFilesChangeListener
        public void onUpdate(final DatabaseHelper databaseHelper, String str, long j, int i, boolean z, long j2, final int i2, boolean z2, String str2, String str3, String str4) {
            boolean z3 = z || z2;
            final Uri contentUri = MediaStore.Files.getContentUri(str, j);
            MediaProvider.this.handleUpdatedRowForFuse(str4, str2, j, j2);
            MediaProvider.this.handleOwnerPackageNameChange(str4, str2, str3);
            MediaProvider mediaProvider = MediaProvider.this;
            Objects.requireNonNull(databaseHelper);
            mediaProvider.acceptWithExpansion(new Consumer() { // from class: com.android.providers.media.-$$Lambda$qsoRb5WGkLW5F4Wlsd55cRkfmO4
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    databaseHelper.notifyUpdate((Uri) obj);
                }
            }, str, j, i, z3);
            databaseHelper.postBackground(new Runnable() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$3$WJkiC6ajEYmAPvc5_niXuh2KmrI
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onUpdate$1$MediaProvider$3(databaseHelper, contentUri, i2);
                }
            });
            if (i2 != i) {
                MediaProvider mediaProvider2 = MediaProvider.this;
                Objects.requireNonNull(databaseHelper);
                mediaProvider2.acceptWithExpansion(new Consumer() { // from class: com.android.providers.media.-$$Lambda$qsoRb5WGkLW5F4Wlsd55cRkfmO4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        databaseHelper.notifyUpdate((Uri) obj);
                    }
                }, str, j, i2, z3);
                databaseHelper.postBackground(new Runnable() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$3$aKSZcS1TpPykmyPmVVjBG8KdkZo
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onUpdate$2$MediaProvider$3(contentUri);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onUpdate$1, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onUpdate$1$MediaProvider$3(DatabaseHelper databaseHelper, Uri uri, int i) {
            if (databaseHelper.isExternal()) {
                MediaProvider.this.updateQuotaTypeForUri(uri, i);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onUpdate$2, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onUpdate$2$MediaProvider$3(Uri uri) {
            MediaProvider.this.lambda$updateInternal$23(uri);
        }

        @Override // com.android.providers.media.DatabaseHelper.OnFilesChangeListener
        public void onDelete(final DatabaseHelper databaseHelper, final String str, final long j, final int i, final boolean z, String str2, String str3) {
            MediaProvider.this.handleDeletedRowForFuse(str3, str2, j);
            MediaProvider mediaProvider = MediaProvider.this;
            Objects.requireNonNull(databaseHelper);
            mediaProvider.acceptWithExpansion(new Consumer() { // from class: com.android.providers.media.-$$Lambda$yOd7kOHkqRBIqa9AoQMCmvjogFQ
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    databaseHelper.notifyDelete((Uri) obj);
                }
            }, str, j, i, z);
            databaseHelper.postBackground(new Runnable() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$3$bsf2OYCIEbGT_pcKUtJ2kb9x6wY
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onDelete$4$MediaProvider$3(str, j, i, z);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onDelete$4, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onDelete$4$MediaProvider$3(String str, long j, int i, boolean z) {
            Trace.beginSection("revokeUriPermission");
            try {
                MediaProvider.this.acceptWithExpansion(new Consumer() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$3$R0_z-wAIwi4wpGb5YSjjUoMK5J8
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        this.f$0.lambda$onDelete$3$MediaProvider$3((Uri) obj);
                    }
                }, str, j, i, z);
                Trace.endSection();
                MediaProvider.this.lambda$updateInternal$23(MediaStore.Files.getContentUri(str, j));
                MediaDocumentsProvider.onMediaStoreDelete(MediaProvider.this.getContext(), str, i, j);
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onDelete$3, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onDelete$3$MediaProvider$3(Uri uri) {
            MediaProvider.this.getContext().revokeUriPermission(uri, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$4, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ String lambda$new$4$MediaProvider(String str) {
        long deletedRowId = this.mCallingIdentity.get().getDeletedRowId(str);
        if (deletedRowId == -1 || !isFuseThread()) {
            return null;
        }
        return String.valueOf(deletedRowId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acceptWithExpansion(Consumer<Uri> consumer, String str, long j, int i, boolean z) {
        if (i == 1) {
            consumer.accept(MediaStore.Images.Media.getContentUri(str, j));
        } else if (i == 2) {
            consumer.accept(MediaStore.Audio.Media.getContentUri(str, j));
            consumer.accept(MediaStore.Audio.Genres.getContentUri(str));
            consumer.accept(MediaStore.Audio.Playlists.getContentUri(str));
            consumer.accept(MediaStore.Audio.Artists.getContentUri(str));
            consumer.accept(MediaStore.Audio.Albums.getContentUri(str));
        } else if (i == 3) {
            consumer.accept(MediaStore.Video.Media.getContentUri(str, j));
        }
        consumer.accept(MediaStore.Files.getContentUri(str, j));
        if (z) {
            consumer.accept(MediaStore.Downloads.getContentUri(str, j));
        }
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != -1820761141) {
            if (iHashCode == 570410685 && str.equals("internal")) {
                b = 0;
            }
        } else if (str.equals("external")) {
            b = 1;
        }
        if (b == 0 || b == 1) {
            return;
        }
        acceptWithExpansion(consumer, "external", j, i, z);
    }

    private void ensureDefaultFolders(String str, SQLiteDatabase sQLiteDatabase) {
        try {
            StorageVolume storageVolume = this.mStorageManager.getStorageVolume(getVolumePath(str));
            if (storageVolume == null) {
                Log.w(Logging.TAG, "Failed to ensure default folders for " + str);
                return;
            }
            Log.e(Logging.TAG, "ensureDefaultFolders vol " + storageVolume.toString() + " isPrimary " + storageVolume.isPrimary());
            if (storageVolume.isPrimary()) {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                if (defaultSharedPreferences.getInt("created_default_folders", 0) == 0) {
                    for (String str2 : FileUtils.DEFAULT_FOLDER_NAMES) {
                        File file = new File(storageVolume.getDirectory(), str2);
                        if (!file.exists()) {
                            file.mkdirs();
                            insertDirectory(sQLiteDatabase, file.getAbsolutePath());
                        }
                    }
                    SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
                    editorEdit.putInt("created_default_folders", 1);
                    editorEdit.commit();
                }
            }
        } catch (IOException e) {
            Log.w(Logging.TAG, "Failed to ensure default folders for " + str, e);
        }
    }

    private void ensureThumbnailsValid(String str, SQLiteDatabase sQLiteDatabase) {
        String orCreateUuid = DatabaseHelper.getOrCreateUuid(sQLiteDatabase);
        try {
            StorageVolume storageVolume = this.mStorageManager.getStorageVolume(getVolumePath(str));
            if (storageVolume == null || storageVolume.isPrimary()) {
                for (File file : getThumbnailDirectories(str)) {
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(file, FILE_DATABASE_UUID);
                    Optional<String> string = FileUtils.readString(file2);
                    boolean z = true;
                    if (string.isPresent()) {
                        if (Objects.equals(orCreateUuid, string.get())) {
                            z = false;
                        } else {
                            Log.d(Logging.TAG, "Invalidating all thumbnails under " + file);
                            FileUtils.walkFileTreeContents(file.toPath(), new Consumer() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$zMMVrPrCX2pE4GkQZA2PeJA85XI
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj) {
                                    this.f$0.deleteAndInvalidate((Path) obj);
                                }
                            });
                        }
                    }
                    if (z) {
                        FileUtils.writeString(file2, Optional.of(orCreateUuid));
                    }
                }
            }
        } catch (IOException e) {
            Log.w(Logging.TAG, "Failed to ensure thumbnails valid for " + str, e);
        }
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        Log.v(Logging.TAG, "Attached " + providerInfo.authority + " from " + providerInfo.applicationInfo.packageName);
        this.mUriMatcher = new LocalUriMatcher(providerInfo.authority);
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        Context context = getContext();
        Binder.setProxyTransactListener(this.mTransactListener);
        this.mStorageManager = (StorageManager) context.getSystemService(StorageManager.class);
        this.mAppOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        this.mPackageManager = context.getPackageManager();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int iMin = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) / 2;
        this.mThumbSize = new Size(iMin, iMin);
        this.mMediaScanner = new ModernMediaScanner(context);
        this.mInternalDatabase = new DatabaseHelper(context, "internal.db", true, false, false, Column.class, $$Lambda$v2UzoST2IXxtfDHBJ7sMtJ9dvk.INSTANCE, this.mFilesListener, MIGRATION_LISTENER, this.mIdGenerator);
        this.mExternalDatabase = new DatabaseHelper(context, "external.db", false, false, false, Column.class, $$Lambda$v2UzoST2IXxtfDHBJ7sMtJ9dvk.INSTANCE, this.mFilesListener, MIGRATION_LISTENER, this.mIdGenerator);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(10);
        intentFilter.addDataScheme("package");
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        context.registerReceiver(this.mPackageReceiver, intentFilter);
        this.mStorageManager.registerStorageVolumeCallback(context.getMainExecutor(), new StorageManager.StorageVolumeCallback() { // from class: com.android.providers.media.MediaProvider.5
            @Override // android.os.storage.StorageManager.StorageVolumeCallback
            public void onStateChanged(StorageVolume storageVolume) {
                MediaProvider.this.updateVolumes();
            }
        });
        updateVolumes();
        attachVolume("internal", false);
        Iterator<String> it = getExternalVolumeNames().iterator();
        while (it.hasNext()) {
            attachVolume(it.next(), false);
        }
        this.mAppOpsManager.startWatchingActive(new String[]{"android:camera"}, context.getMainExecutor(), this.mActiveListener);
        this.mAppOpsManager.startWatchingMode("android:read_external_storage", null, this.mModeListener);
        this.mAppOpsManager.startWatchingMode("android:write_external_storage", null, this.mModeListener);
        this.mAppOpsManager.startWatchingMode(AppOpsManager.permissionToOp("android.permission.ACCESS_MEDIA_LOCATION"), null, this.mModeListener);
        this.mAppOpsManager.startWatchingMode("android:legacy_storage", null, this.mModeListener);
        this.mAppOpsManager.startWatchingMode("android:manage_external_storage", null, this.mModeListener);
        this.mAppOpsManager.startWatchingMode("android:write_media_images", null, this.mModeListener);
        this.mAppOpsManager.startWatchingMode("android:write_media_video", null, this.mModeListener);
        try {
            this.mAppOpsManager.startWatchingMode(PermissionUtils.OPSTR_NO_ISOLATED_STORAGE, null, this.mModeListener);
            return true;
        } catch (IllegalArgumentException e) {
            Log.w(Logging.TAG, "Failed to start watching android:no_isolated_storage", e);
            return true;
        }
    }

    @Override // android.content.ContentProvider
    public void onCallingPackageChanged() {
        this.mCallingIdentity.remove();
    }

    public LocalCallingIdentity clearLocalCallingIdentity() {
        return clearLocalCallingIdentity(LocalCallingIdentity.fromSelf(getContext()));
    }

    public LocalCallingIdentity clearLocalCallingIdentity(LocalCallingIdentity localCallingIdentity) {
        LocalCallingIdentity localCallingIdentity2 = this.mCallingIdentity.get();
        this.mCallingIdentity.set(localCallingIdentity);
        return localCallingIdentity2;
    }

    public void restoreLocalCallingIdentity(LocalCallingIdentity localCallingIdentity) {
        this.mCallingIdentity.set(localCallingIdentity);
    }

    private boolean isPackageKnown(String str) {
        PackageManager packageManager = getContext().getPackageManager();
        try {
            packageManager.getPackageInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            Iterator<PackageInstaller.SessionInfo> it = packageManager.getPackageInstaller().getAllSessions().iterator();
            while (it.hasNext()) {
                if (Objects.equals(str, it.next().getAppPackageName())) {
                    return true;
                }
            }
            return false;
        }
    }

    public void onIdleMaintenance(final CancellationSignal cancellationSignal) {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        Logging.trimPersistent();
        for (final String str : getExternalVolumeNames()) {
            cancellationSignal.throwIfCanceled();
            try {
                MediaService.onScanVolume(getContext(), str, 3);
            } catch (IOException e) {
                Log.w(Logging.TAG, e);
            }
            this.mExternalDatabase.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$Jx28mbfi_eH1OKR4pLD_ichqWa0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f$0.lambda$onIdleMaintenance$5$MediaProvider(str, (SQLiteDatabase) obj);
                }
            });
        }
        int iIntValue = ((Integer) this.mExternalDatabase.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$Nz3J6g1CL6Xd3L5e_nSKs1djKrc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onIdleMaintenance$6$MediaProvider(cancellationSignal, (SQLiteDatabase) obj);
            }
        })).intValue();
        Log.d(Logging.TAG, "Pruned " + iIntValue + " unknown thumbnails");
        Log.d(Logging.TAG, "Pruned " + ((Integer) this.mExternalDatabase.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$at5x4whKTNE4ogtCVTF89cfj3hc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onIdleMaintenance$7$MediaProvider(cancellationSignal, (SQLiteDatabase) obj);
            }
        })).intValue() + " unknown packages");
        final long jCurrentTimeMillis = (System.currentTimeMillis() - FileUtils.DEFAULT_DURATION_PENDING) / 1000;
        final long jCurrentTimeMillis2 = System.currentTimeMillis() / 1000;
        int iIntValue2 = ((Integer) this.mExternalDatabase.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$HZsiV3X84WD-kzBmDj-poCC9u8E
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onIdleMaintenance$8$MediaProvider(jCurrentTimeMillis, jCurrentTimeMillis2, cancellationSignal, (SQLiteDatabase) obj);
            }
        })).intValue();
        Log.d(Logging.TAG, "Deleted " + iIntValue2 + " expired items");
        this.mExternalDatabase.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$JfLRJrnA9QqIXESSLbPoXcN3BA4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onIdleMaintenance$9$MediaProvider(cancellationSignal, (SQLiteDatabase) obj);
            }
        });
        synchronized (this.mDirectoryCache) {
            this.mDirectoryCache.clear();
        }
        Metrics.logIdleMaintenance("external", ((Long) this.mExternalDatabase.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$Dn0etbVSPJIurX6nILZ0gjlkwaw
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Long.valueOf(DatabaseHelper.getItemCount((SQLiteDatabase) obj));
            }
        })).longValue(), SystemClock.elapsedRealtime() - jElapsedRealtime, iIntValue, iIntValue2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onIdleMaintenance$5, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Object lambda$onIdleMaintenance$5$MediaProvider(String str, SQLiteDatabase sQLiteDatabase) {
        ensureThumbnailsValid(str, sQLiteDatabase);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onIdleMaintenance$6, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Integer lambda$onIdleMaintenance$6$MediaProvider(CancellationSignal cancellationSignal, SQLiteDatabase sQLiteDatabase) {
        return Integer.valueOf(pruneThumbnails(sQLiteDatabase, cancellationSignal));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onIdleMaintenance$7, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Integer lambda$onIdleMaintenance$7$MediaProvider(CancellationSignal cancellationSignal, SQLiteDatabase sQLiteDatabase) {
        ArraySet arraySet = new ArraySet();
        Cursor cursorQuery = sQLiteDatabase.query(true, "files", new String[]{"owner_package_name"}, null, null, null, null, null, null, cancellationSignal);
        while (cursorQuery.moveToNext()) {
            try {
                String string = cursorQuery.getString(0);
                if (!TextUtils.isEmpty(string) && !isPackageKnown(string)) {
                    arraySet.add(string);
                }
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
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            onPackageOrphaned(sQLiteDatabase, (String) it.next());
        }
        return Integer.valueOf(arraySet.size());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onIdleMaintenance$8, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Integer lambda$onIdleMaintenance$8$MediaProvider(long j, long j2, CancellationSignal cancellationSignal, SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query(true, "files", new String[]{"volume_name", "_id"}, "date_expires BETWEEN " + j + " AND " + j2, null, null, null, null, null, cancellationSignal);
        while (cursorQuery.moveToNext()) {
            try {
                delete(MediaStore.Files.getContentUri(cursorQuery.getString(0), cursorQuery.getLong(1)), null, null);
            } catch (Throwable th) {
                if (cursorQuery == null) {
                    throw th;
                }
                try {
                    cursorQuery.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        }
        Integer numValueOf = Integer.valueOf(cursorQuery.getCount());
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        return numValueOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onIdleMaintenance$9, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Object lambda$onIdleMaintenance$9$MediaProvider(CancellationSignal cancellationSignal, SQLiteDatabase sQLiteDatabase) {
        Set<String> recentExternalVolumeNames = MediaStore.getRecentExternalVolumeNames(getContext());
        ArraySet arraySet = new ArraySet();
        Cursor cursorQuery = sQLiteDatabase.query(true, "files", new String[]{"volume_name"}, null, null, null, null, null, null, cancellationSignal);
        while (cursorQuery.moveToNext()) {
            try {
                arraySet.add(cursorQuery.getString(0));
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
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        ArraySet<String> arraySet2 = new ArraySet();
        arraySet2.addAll((Collection) arraySet);
        arraySet2.removeAll(recentExternalVolumeNames);
        for (String str : arraySet2) {
            Log.d(Logging.TAG, "Forgot " + sQLiteDatabase.delete("files", "volume_name=?", new String[]{str}) + " stale items from " + str);
        }
        return null;
    }

    public void onPackageOrphaned(final String str) {
        this.mExternalDatabase.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$JW0vKHMl44155sMq31K1OzcRnlo
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onPackageOrphaned$11$MediaProvider(str, (SQLiteDatabase) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onPackageOrphaned$11, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Object lambda$onPackageOrphaned$11$MediaProvider(String str, SQLiteDatabase sQLiteDatabase) {
        onPackageOrphaned(sQLiteDatabase, str);
        return null;
    }

    public void onPackageOrphaned(SQLiteDatabase sQLiteDatabase, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.putNull("owner_package_name");
        int iUpdate = sQLiteDatabase.update("files", contentValues, "owner_package_name=?", new String[]{str});
        if (iUpdate > 0) {
            Log.d(Logging.TAG, "Orphaned " + iUpdate + " items belonging to " + str + " on " + sQLiteDatabase.getPath());
        }
    }

    public void scanDirectory(File file, int i) {
        this.mMediaScanner.scanDirectory(file, i);
    }

    public Uri scanFile(File file, int i) {
        return this.mMediaScanner.scanFile(file, i);
    }

    public Uri scanFile(File file, int i, String str) {
        return this.mMediaScanner.scanFile(file, i, str);
    }

    @Keep
    public void scanFileForFuse(String str) {
        scanFile(new File(str), 2);
    }

    @Keep
    public void onFileCreatedForFuse(final String str) {
        BackgroundThread.getExecutor().execute(new Runnable() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$VhB3wkpGkT4Cm_4TN5qOQg3m3rU
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onFileCreatedForFuse$12$MediaProvider(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onFileCreatedForFuse$12, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onFileCreatedForFuse$12$MediaProvider(String str) {
        File file = new File(str);
        updateQuotaTypeForFileInternal(file, MimeUtils.resolveMediaType(MimeUtils.resolveMimeType(file)));
    }

    static boolean hasPermissionToClearCaches(Context context, ApplicationInfo applicationInfo) {
        PermissionUtils.setOpDescription("clear app cache");
        try {
            return PermissionUtils.checkPermissionManager(context, -1, applicationInfo.uid, applicationInfo.packageName, null);
        } finally {
            PermissionUtils.clearOpDescription();
        }
    }

    void computeAudioLocalizedValues(ContentValues contentValues) {
        try {
            String asString = contentValues.getAsString("title");
            String asString2 = contentValues.getAsString("title_resource_uri");
            if (!TextUtils.isEmpty(asString2)) {
                String localizedTitle = getLocalizedTitle(asString2);
                if (!TextUtils.isEmpty(localizedTitle)) {
                    contentValues.put("title", localizedTitle);
                }
            } else {
                String localizedTitle2 = getLocalizedTitle(asString);
                if (!TextUtils.isEmpty(localizedTitle2)) {
                    contentValues.put("title", localizedTitle2);
                    contentValues.put("title_resource_uri", asString);
                }
            }
        } catch (Exception e) {
            Log.w(Logging.TAG, "Failed to localize title", e);
        }
    }

    static void computeAudioKeyValues(ContentValues contentValues) {
        computeAudioKeyValue(contentValues, "title", "title_key", null, 0);
        computeAudioKeyValue(contentValues, "artist", "artist_key", "artist_id", 0);
        computeAudioKeyValue(contentValues, "genre", "genre_key", "genre_id", 0);
        computeAudioAlbumKeyValue(contentValues);
    }

    private static void computeAudioAlbumKeyValue(ContentValues contentValues) {
        String asString = contentValues.getAsString("album_artist");
        int iHashCode = 0;
        if (!TextUtils.isEmpty(asString)) {
            iHashCode = asString.hashCode();
        } else {
            String asString2 = contentValues.getAsString("_data");
            if (!TextUtils.isEmpty(asString2)) {
                iHashCode = asString2.substring(0, asString2.lastIndexOf(47)).hashCode();
            }
        }
        computeAudioKeyValue(contentValues, "album", "album_key", "album_id", iHashCode);
    }

    private static void computeAudioKeyValue(ContentValues contentValues, String str, String str2, String str3, int i) {
        String strKeyFor;
        if (str2 != null) {
            contentValues.remove(str2);
        }
        if (str3 != null) {
            contentValues.remove(str3);
        }
        String asString = contentValues.getAsString(str);
        if (TextUtils.isEmpty(asString) || (strKeyFor = MediaStore.Audio.keyFor(asString)) == null) {
            return;
        }
        if (str2 != null) {
            contentValues.put(str2, strKeyFor);
        }
        if (str3 != null) {
            contentValues.put(str3, Long.valueOf(Hashing.farmHashFingerprint64().hashString(strKeyFor + i, StandardCharsets.UTF_8).asLong() & Long.MAX_VALUE));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0051 A[Catch: all -> 0x004f, TRY_ENTER, TryCatch #2 {all -> 0x004f, blocks: (B:14:0x002f, B:16:0x0039, B:22:0x0051, B:24:0x005f), top: B:44:0x0024, outer: #0 }] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.net.Uri canonicalize(android.net.Uri r12) {
        /*
            r11 = this;
            java.lang.String r0 = "document_id"
            boolean r1 = r11.isCallingPackageAllowedHidden()
            int r1 = r11.matchUri(r12, r1)
            java.lang.String r2 = "canonical"
            java.lang.String r3 = r12.getQueryParameter(r2)
            java.lang.String r4 = "1"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L19
            return r12
        L19:
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r5 = r11
            r6 = r12
            android.database.Cursor r3 = r5.queryForSingleItem(r6, r7, r8, r9, r10)     // Catch: java.io.FileNotFoundException -> L84
            r5 = 2
            if (r1 == r5) goto L51
            r5 = 101(0x65, float:1.42E-43)
            if (r1 == r5) goto L2f
            r11 = 201(0xc9, float:2.82E-43)
            if (r1 == r11) goto L51
            goto L73
        L2f:
            java.lang.String r11 = r11.getDefaultTitleFromCursor(r3)     // Catch: java.lang.Throwable -> L4f
            boolean r0 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> L4f
            if (r0 != 0) goto L73
            android.net.Uri$Builder r12 = r12.buildUpon()     // Catch: java.lang.Throwable -> L4f
            java.lang.String r0 = "title"
            r12.appendQueryParameter(r0, r11)     // Catch: java.lang.Throwable -> L4f
            r12.appendQueryParameter(r2, r4)     // Catch: java.lang.Throwable -> L4f
            android.net.Uri r11 = r12.build()     // Catch: java.lang.Throwable -> L4f
            if (r3 == 0) goto L4e
            r3.close()     // Catch: java.io.FileNotFoundException -> L84
        L4e:
            return r11
        L4f:
            r11 = move-exception
            goto L79
        L51:
            int r11 = r3.getColumnIndexOrThrow(r0)     // Catch: java.lang.Throwable -> L4f
            java.lang.String r11 = r3.getString(r11)     // Catch: java.lang.Throwable -> L4f
            boolean r1 = android.text.TextUtils.isEmpty(r11)     // Catch: java.lang.Throwable -> L4f
            if (r1 != 0) goto L73
            android.net.Uri$Builder r12 = r12.buildUpon()     // Catch: java.lang.Throwable -> L4f
            r12.appendQueryParameter(r0, r11)     // Catch: java.lang.Throwable -> L4f
            r12.appendQueryParameter(r2, r4)     // Catch: java.lang.Throwable -> L4f
            android.net.Uri r11 = r12.build()     // Catch: java.lang.Throwable -> L4f
            if (r3 == 0) goto L72
            r3.close()     // Catch: java.io.FileNotFoundException -> L84
        L72:
            return r11
        L73:
            if (r3 == 0) goto L8e
            r3.close()     // Catch: java.io.FileNotFoundException -> L84
            goto L8e
        L79:
            if (r3 == 0) goto L83
            r3.close()     // Catch: java.lang.Throwable -> L7f
            goto L83
        L7f:
            r12 = move-exception
            r11.addSuppressed(r12)     // Catch: java.io.FileNotFoundException -> L84
        L83:
            throw r11     // Catch: java.io.FileNotFoundException -> L84
        L84:
            r11 = move-exception
            java.lang.String r11 = r11.getMessage()
            java.lang.String r12 = "MediaProvider"
            android.util.Log.w(r12, r11)
        L8e:
            r11 = 0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.MediaProvider.canonicalize(android.net.Uri):android.net.Uri");
    }

    @Override // android.content.ContentProvider
    public Uri uncanonicalize(Uri uri) {
        Cursor cursorQueryForSingleItem;
        int iMatchUri = matchUri(uri, isCallingPackageAllowedHidden());
        if (!"1".equals(uri.getQueryParameter(CANONICAL))) {
            return uri;
        }
        String queryParameter = uri.getQueryParameter("title");
        String queryParameter2 = uri.getQueryParameter("document_id");
        Uri uriBuild = uri.buildUpon().clearQuery().build();
        if (iMatchUri != 2) {
            if (iMatchUri == 101) {
                try {
                    cursorQueryForSingleItem = queryForSingleItem(uriBuild, null, null, null, null);
                    try {
                    } finally {
                    }
                } catch (FileNotFoundException e) {
                    Log.w(Logging.TAG, "Trouble resolving " + uriBuild + "; falling back to search: " + e);
                }
                if (Objects.equals(queryParameter, getDefaultTitleFromCursor(cursorQueryForSingleItem))) {
                    if (cursorQueryForSingleItem != null) {
                        cursorQueryForSingleItem.close();
                    }
                    return uriBuild;
                }
                if (cursorQueryForSingleItem != null) {
                    cursorQueryForSingleItem.close();
                }
                Uri uriRemoveId = ContentUris.removeId(uriBuild);
                try {
                    Cursor cursorQueryForSingleItem2 = queryForSingleItem(uriRemoveId, new String[]{"_id"}, "title=?", new String[]{queryParameter}, null);
                    try {
                        Uri uriWithAppendedId = ContentUris.withAppendedId(uriRemoveId, cursorQueryForSingleItem2.getLong(0));
                        if (cursorQueryForSingleItem2 != null) {
                            cursorQueryForSingleItem2.close();
                        }
                        return uriWithAppendedId;
                    } finally {
                    }
                } catch (FileNotFoundException e2) {
                    Log.w(Logging.TAG, "Failed to resolve " + uriBuild + ": " + e2);
                    return null;
                }
            } else if (iMatchUri != VIDEO_MEDIA_ID) {
                return uriBuild;
            }
        }
        try {
            cursorQueryForSingleItem = queryForSingleItem(uriBuild, null, null, null, null);
        } catch (FileNotFoundException e3) {
            Log.w(Logging.TAG, "Trouble resolving " + uriBuild + "; falling back to search: " + e3);
        }
        try {
            if (Objects.equals(queryParameter, getDefaultTitleFromCursor(cursorQueryForSingleItem))) {
                if (cursorQueryForSingleItem != null) {
                    cursorQueryForSingleItem.close();
                }
                return uriBuild;
            }
            if (cursorQueryForSingleItem != null) {
                cursorQueryForSingleItem.close();
            }
            Uri uriRemoveId2 = ContentUris.removeId(uriBuild);
            try {
                Cursor cursorQueryForSingleItem3 = queryForSingleItem(uriRemoveId2, new String[]{"_id"}, "document_id=?", new String[]{queryParameter2}, null);
                try {
                    Uri uriWithAppendedId2 = ContentUris.withAppendedId(uriRemoveId2, cursorQueryForSingleItem3.getLong(0));
                    if (cursorQueryForSingleItem3 != null) {
                        cursorQueryForSingleItem3.close();
                    }
                    return uriWithAppendedId2;
                } finally {
                    if (cursorQueryForSingleItem3 == null) {
                        throw th;
                    }
                    try {
                        cursorQueryForSingleItem3.close();
                        throw th;
                    } catch (Throwable th) {
                        th.addSuppressed(th);
                    }
                }
            } catch (FileNotFoundException e4) {
                Log.w(Logging.TAG, "Failed to resolve " + uriBuild + ": " + e4);
                return null;
            }
        } finally {
            if (cursorQueryForSingleItem == null) {
                throw th;
            }
            try {
                cursorQueryForSingleItem.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
    }

    private Uri safeUncanonicalize(Uri uri) {
        Uri uriUncanonicalize = uncanonicalize(uri);
        return uriUncanonicalize != null ? uriUncanonicalize : uri;
    }

    private String getWhereClauseForMatchExclude(String str) {
        if (str.equalsIgnoreCase("is_pending")) {
            getCallingPackageOrSelf();
            return String.format("%s=0 OR (%s=1 AND %s AND %s)", str, str, MATCH_PENDING_FROM_FUSE, "owner_package_name IN " + getSharedPackages());
        }
        return str + "=0";
    }

    private String getWhereClauseForMatchableVisibleFromFilePath(Uri uri, String str) {
        if (isCallingPackageLegacyWrite() || checkCallingPermissionGlobal(uri, true)) {
            return null;
        }
        String callingPackageOrSelf = getCallingPackageOrSelf();
        ArrayList arrayList = new ArrayList();
        int iMatchUri = matchUri(uri, isCallingPackageAllowedHidden());
        if (iMatchUri == 1 || iMatchUri == 2 || iMatchUri == 4 || iMatchUri == 5) {
            if (checkCallingPermissionImages(true, callingPackageOrSelf)) {
                return null;
            }
        } else if (iMatchUri == 100 || iMatchUri == 101 || iMatchUri == 110 || iMatchUri == 111) {
            if (checkCallingPermissionAudio(true, callingPackageOrSelf)) {
                return null;
            }
        } else if (iMatchUri == VIDEO_MEDIA || iMatchUri == VIDEO_MEDIA_ID || iMatchUri == VIDEO_THUMBNAILS || iMatchUri == VIDEO_THUMBNAILS_ID) {
            if (checkCallingPermissionVideo(true, callingPackageOrSelf)) {
                return null;
            }
        } else if (iMatchUri == FILES || iMatchUri == FILES_ID) {
            if (checkCallingPermissionAudio(true, callingPackageOrSelf)) {
                arrayList.add(DatabaseUtils.bindSelection("media_type=?", 2));
                arrayList.add(DatabaseUtils.bindSelection("media_type=?", 4));
                arrayList.add(DatabaseUtils.bindSelection("media_type=?", 5));
            }
            if (checkCallingPermissionVideo(true, callingPackageOrSelf)) {
                arrayList.add(DatabaseUtils.bindSelection("media_type=?", 3));
                arrayList.add(DatabaseUtils.bindSelection("media_type=?", 5));
            }
            if (checkCallingPermissionImages(true, callingPackageOrSelf)) {
                arrayList.add(DatabaseUtils.bindSelection("media_type=?", 1));
            }
        } else if (iMatchUri != DOWNLOADS && iMatchUri != DOWNLOADS_ID) {
            return null;
        }
        arrayList.add(DatabaseUtils.bindSelection("owner_package_name IN " + getSharedPackages(), new Object[0]));
        if (str.equalsIgnoreCase("is_pending")) {
            arrayList.add(MATCH_PENDING_FROM_FUSE);
        }
        return String.format("%s=0 OR (%s=1 AND %s)", str, str, TextUtils.join(" OR ", arrayList));
    }

    @Keep
    public String[] getFilesInDirectoryForFuse(String str, int i) {
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(getCachedCallingIdentityForFuse(i));
        try {
            if (isPrivatePackagePathNotOwnedByCaller(str)) {
                return new String[]{""};
            }
            if (FileUtils.isDataOrObbPath(str)) {
                return new String[]{""};
            }
            if (shouldBypassFuseRestrictions(false, str)) {
                return new String[]{"/"};
            }
            if (isCallingPackageRequestingLegacy()) {
                return new String[]{""};
            }
            String strExtractRelativePathForDirectory = FileUtils.extractRelativePathForDirectory(str);
            if (strExtractRelativePathForDirectory == null) {
                return new String[]{"/"};
            }
            ArrayList arrayList = new ArrayList();
            Bundle bundle = new Bundle();
            bundle.putString("android:query-arg-sql-selection", "relative_path =? and mime_type not like 'null'");
            bundle.putStringArray("android:query-arg-sql-selection-args", new String[]{strExtractRelativePathForDirectory});
            Cursor cursorQuery = query(FileUtils.getContentUriForPath(str), new String[]{"_data"}, bundle, null);
            while (cursorQuery.moveToNext()) {
                try {
                    arrayList.add(FileUtils.extractDisplayName(cursorQuery.getString(0)));
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return (String[]) arrayList.toArray(new String[arrayList.size()]);
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    private void scanRenamedDirectoryForFuse(String str, String str2) {
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
        try {
            scanFile(new File(str), 2);
            scanFile(new File(str2), 2);
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    private boolean isMimeTypeSupportedInPath(String str, String str2) {
        int iMatchUri = matchUri(getContentUriForFile(str, str2), true);
        String str3 = iMatchUri != 1 ? iMatchUri != 100 ? iMatchUri != VIDEO_MEDIA ? "application/octet-stream" : "video" : "audio" : "image";
        return str3.equalsIgnoreCase("application/octet-stream") || MimeUtils.startsWithIgnoreCase(str2, str3);
    }

    private boolean maybeRemoveOwnerPackageForFuseRename(DatabaseHelper databaseHelper, String str) {
        Uri contentUriForPath = FileUtils.getContentUriForPath(str);
        int iMatchUri = matchUri(contentUriForPath, isCallingPackageAllowedHidden());
        String[] strArr = {str};
        Cursor cursorQuery = getQueryBuilder(0, iMatchUri, contentUriForPath, Bundle.EMPTY, null).query(databaseHelper, new String[]{"owner_package_name"}, "_data =? AND owner_package_name != 'null'", strArr, (String) null, (String) null, (String) null, (String) null, (CancellationSignal) null);
        try {
            if (!cursorQuery.moveToFirst()) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return true;
            }
            if (isCallingIdentitySharedPackageName(cursorQuery.getString(0))) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return true;
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            SQLiteQueryBuilder queryBuilder = getQueryBuilder(2, iMatchUri, contentUriForPath, Bundle.EMPTY, null);
            ContentValues contentValues = new ContentValues();
            contentValues.put("owner_package_name", "null");
            return queryBuilder.update(databaseHelper, contentValues, "_data =? AND owner_package_name != 'null'", strArr) == 1;
        } catch (Throwable th) {
            if (cursorQuery == null) {
                throw th;
            }
            try {
                cursorQuery.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    private boolean updateDatabaseForFuseRename(DatabaseHelper databaseHelper, String str, String str2, ContentValues contentValues) {
        return updateDatabaseForFuseRename(databaseHelper, str, str2, contentValues, Bundle.EMPTY);
    }

    private boolean updateDatabaseForFuseRename(DatabaseHelper databaseHelper, String str, String str2, ContentValues contentValues, Bundle bundle) {
        boolean z;
        int iUpdate;
        Uri contentUriForPath = FileUtils.getContentUriForPath(str);
        boolean zIsCallingPackageAllowedHidden = isCallingPackageAllowedHidden();
        SQLiteQueryBuilder queryBuilder = getQueryBuilder(2, matchUri(contentUriForPath, zIsCallingPackageAllowedHidden), contentUriForPath, bundle, null);
        try {
            iUpdate = queryBuilder.update(databaseHelper, contentValues, "_data =? ", new String[]{str});
            z = false;
        } catch (SQLiteConstraintException e) {
            Log.w(Logging.TAG, "Database update failed while renaming " + str, e);
            z = true;
            iUpdate = 0;
        }
        if (z) {
            Uri contentUriForPath2 = FileUtils.getContentUriForPath(str);
            if (getQueryBuilder(3, matchUri(contentUriForPath2, zIsCallingPackageAllowedHidden), contentUriForPath2, bundle, null).delete(databaseHelper, "_data =? ", new String[]{str2}) != 1) {
                return false;
            }
            Log.i(Logging.TAG, "Retrying database update after deleting conflicting entry");
            iUpdate = queryBuilder.update(databaseHelper, contentValues, "_data =? ", new String[]{str});
        }
        return iUpdate == 1;
    }

    private ContentValues getContentValuesForFuseRename(String str, String str2, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("mime_type", str2);
        contentValues.put("_data", str);
        if (z && shouldFileBeHidden(new File(str))) {
            contentValues.put("media_type", (Integer) 0);
        } else {
            contentValues.put("media_type", Integer.valueOf(MimeUtils.resolveMediaType(str2)));
        }
        boolean zIsCallingPackageAllowedHidden = isCallingPackageAllowedHidden();
        if (!str2.equalsIgnoreCase("null") && matchUri(getContentUriForFile(str, str2), zIsCallingPackageAllowedHidden) == 100) {
            computeAudioLocalizedValues(contentValues);
            computeAudioKeyValues(contentValues);
        }
        FileUtils.computeValuesFromData(contentValues, isFuseThread());
        return contentValues;
    }

    private ArrayList<String> getIncludedDefaultDirectories() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (checkCallingPermissionVideo(true, null)) {
            arrayList.add(DIRECTORY_DCIM);
            arrayList.add(DIRECTORY_PICTURES);
            arrayList.add(DIRECTORY_MOVIES);
        } else if (checkCallingPermissionImages(true, null)) {
            arrayList.add(DIRECTORY_DCIM);
            arrayList.add(DIRECTORY_PICTURES);
        }
        return arrayList;
    }

    private ArrayList<String> getAllFilesForRenameDirectory(String str) {
        String[] strArr = {DatabaseUtils.escapeForLike(str) + "/%"};
        ArrayList<String> arrayList = new ArrayList<>();
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
        try {
            Cursor cursorQuery = query(FileUtils.getContentUriForPath(str), new String[]{"_data"}, "_data LIKE ? ESCAPE '\\' and mime_type not like 'null'", strArr, null);
            while (cursorQuery.moveToNext()) {
                try {
                    arrayList.add(cursorQuery.getString(0).replaceFirst(Pattern.quote(str + "/"), ""));
                } finally {
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    private ArrayList<String> getWritableFilesForRenameDirectory(String str, String str2) throws IllegalArgumentException {
        String strExtractRelativePathForDirectory = FileUtils.extractRelativePathForDirectory(str);
        String strExtractRelativePathForDirectory2 = FileUtils.extractRelativePathForDirectory(str2);
        boolean z = false;
        boolean z2 = false;
        for (String str3 : getIncludedDefaultDirectories()) {
            if (strExtractRelativePathForDirectory.startsWith(str3)) {
                z2 = true;
            }
            if (strExtractRelativePathForDirectory2.startsWith(str3)) {
                z = true;
            }
        }
        if (z && z2) {
            return getAllFilesForRenameDirectory(str);
        }
        String[] strArr = {DatabaseUtils.escapeForLike(str) + "/%"};
        Uri contentUriForPath = FileUtils.getContentUriForPath(str);
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
        try {
            Cursor cursorQuery = query(contentUriForPath, new String[]{"_id"}, "_data LIKE ? ESCAPE '\\' and mime_type not like 'null'", strArr, null);
            try {
                int count = cursorQuery.getCount();
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
                SQLiteQueryBuilder queryBuilder = getQueryBuilder(2, matchUri(contentUriForPath, isCallingPackageAllowedHidden()), contentUriForPath, Bundle.EMPTY, null);
                try {
                    DatabaseHelper databaseForUri = getDatabaseForUri(contentUriForPath);
                    ArrayList<String> arrayList = new ArrayList<>();
                    Cursor cursorQuery2 = queryBuilder.query(databaseForUri, new String[]{"_data", "mime_type"}, "_data LIKE ? ESCAPE '\\' and mime_type not like 'null'", strArr, (String) null, (String) null, (String) null, (String) null, (CancellationSignal) null);
                    try {
                        if (cursorQuery2.getCount() != count) {
                            throw new IllegalArgumentException("Calling package doesn't have write permission  to rename one or more files in " + str);
                        }
                        while (cursorQuery2.moveToNext()) {
                            String strReplaceFirst = cursorQuery2.getString(0).replaceFirst(Pattern.quote(str + "/"), "");
                            String string = cursorQuery2.getString(1);
                            if (!isMimeTypeSupportedInPath(str2 + "/" + strReplaceFirst, string)) {
                                throw new IllegalArgumentException("Can't rename " + str + "/" + strReplaceFirst + ". Mime type " + string + " not supported in " + str2);
                            }
                            arrayList.add(strReplaceFirst);
                        }
                        if (cursorQuery2 != null) {
                            cursorQuery2.close();
                        }
                        return arrayList;
                    } finally {
                    }
                } catch (VolumeNotFoundException unused) {
                    throw new IllegalStateException("Volume not found while querying files for renaming " + str);
                }
            } finally {
            }
        } catch (Throwable th) {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
            throw th;
        }
    }

    private int renameInLowerFs(String str, String str2) {
        try {
            Os.rename(str, str2);
            return 0;
        } catch (ErrnoException e) {
            Log.e(Logging.TAG, "Rename " + str + " to " + str2 + " failed.", e);
            return e.errno;
        }
    }

    private int renameDirectoryCheckedForFuse(String str, String str2) {
        try {
            return renameDirectoryUncheckedForFuse(str, str2, getWritableFilesForRenameDirectory(str, str2));
        } catch (IllegalArgumentException e) {
            Log.e(Logging.TAG, "Rename " + str + " to " + str2 + " failed. ", e);
            return OsConstants.EPERM;
        }
    }

    private int renameDirectoryUncheckedForFuse(String str, String str2, ArrayList<String> arrayList) {
        try {
            DatabaseHelper databaseForUri = getDatabaseForUri(FileUtils.getContentUriForPath(str));
            databaseForUri.beginTransaction();
            try {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(INCLUDED_DEFAULT_DIRECTORIES, getIncludedDefaultDirectories());
                for (String str3 : arrayList) {
                    String str4 = str2 + "/" + str3;
                    if (!updateDatabaseForFuseRename(databaseForUri, str + "/" + str3, str4, getContentValuesForFuseRename(str4, MimeUtils.resolveMimeType(new File(str4)), false), bundle)) {
                        Log.e(Logging.TAG, "Calling package doesn't have write permission to rename file.");
                        return OsConstants.EPERM;
                    }
                }
                int iRenameInLowerFs = renameInLowerFs(str, str2);
                if (iRenameInLowerFs != 0) {
                    return iRenameInLowerFs;
                }
                databaseForUri.setTransactionSuccessful();
                databaseForUri.endTransaction();
                scanRenamedDirectoryForFuse(str, str2);
                return 0;
            } finally {
                databaseForUri.endTransaction();
            }
        } catch (VolumeNotFoundException e) {
            throw new IllegalStateException("Volume not found while trying to update database for " + str, e);
        }
    }

    private int renameFileCheckedForFuse(String str, String str2) {
        if (!isMimeTypeSupportedInPath(str2, MimeUtils.resolveMimeType(new File(str2)))) {
            return OsConstants.EPERM;
        }
        return renameFileForFuse(str, str2, false);
    }

    private int renameFileUncheckedForFuse(String str, String str2) {
        return renameFileForFuse(str, str2, true);
    }

    private static boolean shouldFileBeHidden(File file) {
        if (FileUtils.isFileHidden(file)) {
            return true;
        }
        for (File parentFile = file.getParentFile(); parentFile != null; parentFile = parentFile.getParentFile()) {
            if (FileUtils.isDirectoryHidden(parentFile)) {
                return true;
            }
        }
        return false;
    }

    private int renameFileForFuse(String str, String str2, boolean z) {
        try {
            DatabaseHelper databaseForUri = getDatabaseForUri(FileUtils.getContentUriForPath(str));
            databaseForUri.beginTransaction();
            try {
                if (!updateDatabaseForFuseRename(databaseForUri, str, str2, getContentValuesForFuseRename(str2, MimeUtils.resolveMimeType(new File(str2)), true))) {
                    if (!z) {
                        Log.e(Logging.TAG, "Calling package doesn't have write permission to rename file.");
                        return OsConstants.EPERM;
                    }
                    if (!maybeRemoveOwnerPackageForFuseRename(databaseForUri, str2)) {
                        Log.wtf(Logging.TAG, "Couldn't clear owner package name for " + str2);
                        return OsConstants.EPERM;
                    }
                }
                int iRenameInLowerFs = renameInLowerFs(str, str2);
                if (iRenameInLowerFs != 0) {
                    return iRenameInLowerFs;
                }
                databaseForUri.setTransactionSuccessful();
                databaseForUri.endTransaction();
                if (FileUtils.extractDisplayName(str).equals(".nomedia")) {
                    scanFile(new File(str).getParentFile(), 2);
                }
                if (!FileUtils.extractDisplayName(str2).equals(".nomedia")) {
                    return 0;
                }
                scanFile(new File(str2).getParentFile(), 2);
                return 0;
            } finally {
                databaseForUri.endTransaction();
            }
        } catch (VolumeNotFoundException e) {
            throw new IllegalStateException("Failed to update database row with " + str, e);
        }
    }

    private int renameUncheckedForFuse(String str, String str2) {
        if (new File(str).isFile()) {
            return renameFileUncheckedForFuse(str, str2);
        }
        return renameDirectoryUncheckedForFuse(str, str2, getAllFilesForRenameDirectory(str));
    }

    @Keep
    public int renameForFuse(String str, String str2, int i) {
        String str3 = "Rename " + str + " to " + str2 + " failed. ";
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(getCachedCallingIdentityForFuse(i));
        try {
            if (!isPrivatePackagePathNotOwnedByCaller(str) && !isPrivatePackagePathNotOwnedByCaller(str2)) {
                if (!str2.equals(FileUtils.getAbsoluteSanitizedPath(str2))) {
                    Log.e(Logging.TAG, "New path name contains invalid characters.");
                    return OsConstants.EPERM;
                }
                if (shouldBypassDatabaseForFuse(i)) {
                    return renameInLowerFs(str, str2);
                }
                if (shouldBypassFuseRestrictions(true, str) && shouldBypassFuseRestrictions(true, str2)) {
                    return renameUncheckedForFuse(str, str2);
                }
                if (isCallingPackageRequestingLegacy()) {
                    return OsConstants.EACCES;
                }
                String[] strArrSanitizePath = FileUtils.sanitizePath(FileUtils.extractRelativePath(str));
                String[] strArrSanitizePath2 = FileUtils.sanitizePath(FileUtils.extractRelativePath(str2));
                if (strArrSanitizePath.length != 0 && strArrSanitizePath2.length != 0) {
                    if (strArrSanitizePath.length == 1 && TextUtils.isEmpty(strArrSanitizePath[0])) {
                        String strExtractDisplayName = FileUtils.extractDisplayName(str);
                        for (String str4 : FileUtils.DEFAULT_FOLDER_NAMES) {
                            if (strExtractDisplayName.equals(str4)) {
                                Log.e(Logging.TAG, str3 + str + " is a default folder. Renaming a default folder is not allowed.");
                                return OsConstants.EPERM;
                            }
                        }
                    }
                    if (strArrSanitizePath2.length == 1 && TextUtils.isEmpty(strArrSanitizePath2[0])) {
                        Log.e(Logging.TAG, str3 + str2 + " is in root folder. Renaming a file/directory to root folder is not allowed");
                        return OsConstants.EPERM;
                    }
                    File file = new File(Environment.getExternalStorageDirectory(), DIRECTORY_ANDROID);
                    File file2 = new File(file, DIRECTORY_MEDIA);
                    if (file2.getAbsolutePath().equals(str)) {
                        Log.e(Logging.TAG, str3 + str + " is a default folder in app external directory. Renaming a default folder is not allowed.");
                        return OsConstants.EPERM;
                    }
                    if (FileUtils.contains(file, new File(str2))) {
                        if (strArrSanitizePath2.length == 1) {
                            Log.e(Logging.TAG, str3 + str2 + " is in app external directory. Renaming a file/directory to app external directory is not allowed.");
                            return OsConstants.EPERM;
                        }
                        if (!FileUtils.contains(file2, new File(str2))) {
                            Log.e(Logging.TAG, str3 + str2 + " is not in external media directory.File/directory can only be renamed to a path in external media directory. Renaming file/directory to path in other external directories is not allowed");
                            return OsConstants.EPERM;
                        }
                    }
                    return new File(str).isFile() ? renameFileCheckedForFuse(str, str2) : renameDirectoryCheckedForFuse(str, str2);
                }
                Log.e(Logging.TAG, str3 + "Invalid path.");
                return OsConstants.EPERM;
            }
            return OsConstants.EACCES;
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    public int checkUriPermission(Uri uri, int i, int i2) {
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(LocalCallingIdentity.fromExternal(getContext(), i));
        try {
            try {
                Cursor cursorQuery = getQueryBuilder((i2 & 2) != 0 ? 2 : 0, matchUri(uri, isCallingPackageAllowedHidden()), uri, Bundle.EMPTY, null).query(getDatabaseForUri(uri), new String[]{"_id"}, (String) null, (String[]) null, (String) null, (String) null, (String) null, (String) null, (CancellationSignal) null);
                try {
                    if (cursorQuery.getCount() == 1) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return 0;
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    try {
                        if (ContentUris.parseId(uri) != -1) {
                            return -1;
                        }
                    } catch (NumberFormatException unused) {
                    }
                    return (i2 & 128) == 0 ? 0 : -1;
                } finally {
                }
            } catch (VolumeNotFoundException unused2) {
                return -1;
            }
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return query(uri, strArr, DatabaseUtils.createSqlQueryBundle(str, strArr2, str2), null);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal) {
        Trace.beginSection("query");
        try {
            return queryInternal(uri, strArr, bundle, cancellationSignal);
        } catch (FallbackException e) {
            return e.translateForQuery(this.getCallingPackageTargetSdkVersion());
        } finally {
            Trace.endSection();
        }
    }

    private Cursor queryInternal(Uri uri, String[] strArr, Bundle bundle, CancellationSignal cancellationSignal) throws FallbackException {
        Uri uriWithAppendedId;
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        bundle2.remove(INCLUDED_DEFAULT_DIRECTORIES);
        final ArraySet arraySet = new ArraySet();
        DatabaseUtils.resolveQueryArgs(bundle2, new Consumer() { // from class: com.android.providers.media.-$$Lambda$orgjUUDtxyV5E5SwJQpugh0R_WM
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arraySet.add((String) obj);
            }
        }, new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$lUtX5aeqUa1u5UThtIo6zaB1VZk
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.ensureCustomCollator((String) obj);
            }
        });
        Uri uriSafeUncanonicalize = safeUncanonicalize(uri);
        String volumeName = MediaStore.getVolumeName(uriSafeUncanonicalize);
        int callingPackageTargetSdkVersion = getCallingPackageTargetSdkVersion();
        int iMatchUri = matchUri(uriSafeUncanonicalize, isCallingPackageAllowedHidden());
        if (iMatchUri == MEDIA_SCANNER) {
            MatrixCursor matrixCursor = new MatrixCursor(new String[]{"volume"});
            matrixCursor.addRow(new String[]{this.mMediaScannerVolume});
            return matrixCursor;
        }
        if (iMatchUri == FS_ID) {
            MatrixCursor matrixCursor2 = new MatrixCursor(new String[]{"fsid"});
            matrixCursor2.addRow(new Integer[]{Integer.valueOf(this.mVolumeId)});
            return matrixCursor2;
        }
        if (iMatchUri == VERSION) {
            MatrixCursor matrixCursor3 = new MatrixCursor(new String[]{"version"});
            matrixCursor3.addRow(new Integer[]{Integer.valueOf(DatabaseHelper.getDatabaseVersion(getContext()))});
            return matrixCursor3;
        }
        DatabaseHelper databaseForUri = getDatabaseForUri(uriSafeUncanonicalize);
        SQLiteQueryBuilder queryBuilder = getQueryBuilder(0, iMatchUri, uriSafeUncanonicalize, bundle2, new Consumer() { // from class: com.android.providers.media.-$$Lambda$orgjUUDtxyV5E5SwJQpugh0R_WM
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arraySet.add((String) obj);
            }
        });
        if (callingPackageTargetSdkVersion < 30) {
            DatabaseUtils.recoverAbusiveSortOrder(bundle2);
            DatabaseUtils.recoverAbusiveLimit(uriSafeUncanonicalize, bundle2);
        }
        if (callingPackageTargetSdkVersion < 29) {
            DatabaseUtils.recoverAbusiveSelection(bundle2);
            if (strArr != null && strArr.length > 0 && strArr[0].startsWith("DISTINCT ")) {
                strArr[0] = strArr[0].substring(9);
                queryBuilder.setDistinct(true);
            }
            String string = bundle2.getString("android:query-arg-sql-selection");
            if ((iMatchUri == 4 || iMatchUri == VIDEO_THUMBNAILS) && !TextUtils.isEmpty(string)) {
                Matcher matcher = PATTERN_SELECTION_ID.matcher(string);
                if (matcher.matches()) {
                    long j = Long.parseLong(matcher.group(1));
                    if (iMatchUri == 4) {
                        uriWithAppendedId = ContentUris.withAppendedId(MediaStore.Images.Media.getContentUri(volumeName), j);
                    } else if (iMatchUri == VIDEO_THUMBNAILS) {
                        uriWithAppendedId = ContentUris.withAppendedId(MediaStore.Video.Media.getContentUri(volumeName), j);
                    } else {
                        throw new IllegalArgumentException();
                    }
                    MatrixCursor matrixCursor4 = new MatrixCursor(strArr);
                    matrixCursor4.newRow().add("_id", null).add("image_id", Long.valueOf(j)).add("video_id", Long.valueOf(j)).add("_data", ContentResolver.encodeToFile(uriWithAppendedId.buildUpon().appendPath("thumbnail").build()).getAbsolutePath());
                    return matrixCursor4;
                }
            }
        }
        Cursor cursorQuery = queryBuilder.query(databaseForUri, strArr, bundle2, cancellationSignal);
        if (cursorQuery != null) {
            if ((this.mCallingIdentity.get().pid != Process.myPid()) && !isFuseThread()) {
                cursorQuery.setNotificationUri(getContext().getContentResolver(), uriSafeUncanonicalize);
            }
            Bundle bundle3 = new Bundle();
            bundle3.putStringArray("android.content.extra.HONORED_ARGS", (String[]) arraySet.toArray(new String[arraySet.size()]));
            cursorQuery.setExtras(bundle3);
        }
        return cursorQuery;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        int iMatchUri = matchUri(uri, true);
        if (iMatchUri == 1) {
            return "vnd.android.cursor.dir/image";
        }
        if (iMatchUri != 2) {
            if (iMatchUri == 4) {
                return "vnd.android.cursor.dir/image";
            }
            if (iMatchUri == 5 || iMatchUri == 120 || iMatchUri == 121) {
                return "image/jpeg";
            }
            if (iMatchUri == VIDEO_MEDIA) {
                return "vnd.android.cursor.dir/video";
            }
            if (iMatchUri != VIDEO_MEDIA_ID) {
                if (iMatchUri == VIDEO_THUMBNAILS_ID) {
                    return "image/jpeg";
                }
                if (iMatchUri != FILES_ID) {
                    if (iMatchUri == DOWNLOADS) {
                        return "vnd.android.cursor.dir/download";
                    }
                    if (iMatchUri != DOWNLOADS_ID) {
                        switch (iMatchUri) {
                            case 100:
                                return "vnd.android.cursor.dir/audio";
                            case 101:
                                break;
                            case 102:
                                return "vnd.android.cursor.dir/genre";
                            case 103:
                                return "vnd.android.cursor.item/genre";
                            default:
                                switch (iMatchUri) {
                                    case 106:
                                        return "vnd.android.cursor.dir/genre";
                                    case 107:
                                        return "vnd.android.cursor.item/genre";
                                    case 108:
                                        return "vnd.android.cursor.dir/audio";
                                    default:
                                        switch (iMatchUri) {
                                            case 110:
                                                return "vnd.android.cursor.dir/playlist";
                                            case 111:
                                            case 113:
                                                break;
                                            case 112:
                                                return "vnd.android.cursor.dir/audio";
                                            default:
                                                throw new IllegalStateException("Unknown URL : " + uri);
                                        }
                                        break;
                                }
                                break;
                        }
                    }
                }
            }
        }
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
        try {
            try {
                Cursor cursorQueryForSingleItem = queryForSingleItem(uri, new String[]{"mime_type"}, null, null, null);
                try {
                    String string = cursorQueryForSingleItem.getString(0);
                    if (cursorQueryForSingleItem != null) {
                        cursorQueryForSingleItem.close();
                    }
                    return string;
                } catch (Throwable th) {
                    if (cursorQueryForSingleItem != null) {
                        try {
                            cursorQueryForSingleItem.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    void ensureFileColumns(Uri uri, ContentValues contentValues) throws VolumeNotFoundException, VolumeArgumentException {
        ensureNonUniqueFileColumns(new LocalUriMatcher(DIRECTORY_MEDIA).matchUri(uri, true), uri, Bundle.EMPTY, contentValues, null);
    }

    private void ensureUniqueFileColumns(int i, Uri uri, Bundle bundle, ContentValues contentValues, String str) throws VolumeNotFoundException, VolumeArgumentException {
        ensureFileColumns(i, uri, bundle, contentValues, true, str);
    }

    private void ensureNonUniqueFileColumns(int i, Uri uri, Bundle bundle, ContentValues contentValues, String str) throws VolumeNotFoundException, VolumeArgumentException {
        ensureFileColumns(i, uri, bundle, contentValues, false, str);
    }

    /* JADX WARN: Removed duplicated region for block: B:115:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0431  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0497  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x04ed  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0519  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x02ce A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01ae A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01e5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0219  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void ensureFileColumns(int r28, android.net.Uri r29, android.os.Bundle r30, android.content.ContentValues r31, boolean r32, java.lang.String r33) throws com.android.providers.media.MediaProvider.VolumeNotFoundException, com.android.providers.media.MediaProvider.VolumeArgumentException {
        /*
            Method dump skipped, instruction units count: 1325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.MediaProvider.ensureFileColumns(int, android.net.Uri, android.os.Bundle, android.content.ContentValues, boolean, java.lang.String):void");
    }

    private void assertFileColumnsSane(int i, Uri uri, ContentValues contentValues) throws VolumeNotFoundException, VolumeArgumentException {
        if (contentValues.containsKey("_data")) {
            String strResolveVolumeName = resolveVolumeName(uri);
            try {
                Collection<File> volumeScanPaths = getVolumeScanPaths(strResolveVolumeName);
                File canonicalFile = new File(contentValues.getAsString("_data")).getCanonicalFile();
                if (FileUtils.contains(volumeScanPaths, canonicalFile)) {
                } else {
                    throw new VolumeArgumentException(canonicalFile, volumeScanPaths);
                }
            } catch (IOException unused) {
                throw new VolumeNotFoundException(strResolveVolumeName);
            }
        }
    }

    @Override // android.content.ContentProvider
    public int bulkInsert(Uri uri, ContentValues[] contentValuesArr) {
        int callingPackageTargetSdkVersion = getCallingPackageTargetSdkVersion();
        if (matchUri(uri, isCallingPackageAllowedHidden()) == VOLUMES) {
            return super.bulkInsert(uri, contentValuesArr);
        }
        try {
            DatabaseHelper databaseForUri = getDatabaseForUri(uri);
            databaseForUri.beginTransaction();
            try {
                int iBulkInsert = super.bulkInsert(uri, contentValuesArr);
                databaseForUri.setTransactionSuccessful();
                return iBulkInsert;
            } finally {
                databaseForUri.endTransaction();
            }
        } catch (VolumeNotFoundException e) {
            return e.translateForUpdateDelete(callingPackageTargetSdkVersion);
        }
    }

    private long insertDirectory(SQLiteDatabase sQLiteDatabase, String str) {
        if (Logging.LOGV) {
            Log.v(Logging.TAG, "inserting directory " + str);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("format", (Integer) 12289);
        contentValues.put("_data", str);
        contentValues.put("parent", Long.valueOf(getParent(sQLiteDatabase, str)));
        contentValues.put("owner_package_name", FileUtils.extractPathOwnerPackageName(str));
        contentValues.put("volume_name", FileUtils.extractVolumeName(str));
        contentValues.put("relative_path", FileUtils.extractRelativePath(str));
        contentValues.put("_display_name", FileUtils.extractDisplayName(str));
        contentValues.put("is_download", Integer.valueOf(FileUtils.isDownload(str) ? 1 : 0));
        File file = new File(str);
        if (file.exists()) {
            contentValues.put("date_modified", Long.valueOf(file.lastModified() / 1000));
        }
        return sQLiteDatabase.insert("files", "date_modified", contentValues);
    }

    private long getParent(SQLiteDatabase sQLiteDatabase, String str) {
        long jInsertDirectory;
        String parent = new File(str).getParent();
        if (Objects.equals("/", parent)) {
            return -1L;
        }
        synchronized (this.mDirectoryCache) {
            Long l = this.mDirectoryCache.get(parent);
            if (l != null) {
                return l.longValue();
            }
            Cursor cursorQuery = sQLiteDatabase.query("files", new String[]{"_id"}, "_data=?", new String[]{parent}, null, null, null);
            try {
                if (cursorQuery.moveToFirst()) {
                    jInsertDirectory = cursorQuery.getLong(0);
                } else {
                    jInsertDirectory = insertDirectory(sQLiteDatabase, parent);
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                synchronized (this.mDirectoryCache) {
                    this.mDirectoryCache.put(parent, Long.valueOf(jInsertDirectory));
                }
                return jInsertDirectory;
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

    private String getDefaultTitleFromCursor(Cursor cursor) {
        String defaultTitle;
        String string;
        int columnIndex = cursor.getColumnIndex("title_resource_uri");
        if (columnIndex <= -1 || (string = cursor.getString(columnIndex)) == null) {
            defaultTitle = null;
        } else {
            try {
                defaultTitle = getDefaultTitle(string);
            } catch (Exception unused) {
                defaultTitle = null;
            }
        }
        return defaultTitle == null ? cursor.getString(cursor.getColumnIndex("title")) : defaultTitle;
    }

    private String getDefaultTitle(String str) throws Exception {
        try {
            return getTitleFromResourceUri(str, false);
        } catch (Exception e) {
            Log.e(Logging.TAG, "Error getting default title for " + str, e);
            throw e;
        }
    }

    private String getLocalizedTitle(String str) throws Exception {
        try {
            return getTitleFromResourceUri(str, true);
        } catch (Exception e) {
            Log.e(Logging.TAG, "Error getting localized title for " + str, e);
            throw e;
        }
    }

    private String getTitleFromResourceUri(String str, boolean z) throws Exception {
        Resources resources;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri uri = Uri.parse(str);
        if (!"android.resource".equals(uri.getScheme())) {
            return null;
        }
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() != 2) {
            Log.e(Logging.TAG, "Error getting localized title for " + str + ", must have 2 path segments");
            return null;
        }
        String str2 = pathSegments.get(0);
        if (!"string".equals(str2)) {
            Log.e(Logging.TAG, "Error getting localized title for " + str + ", first path segment must be \"string\"");
            return null;
        }
        String authority = uri.getAuthority();
        if (z) {
            resources = this.mPackageManager.getResourcesForApplication(authority);
        } else {
            Context contextCreatePackageContext = getContext().createPackageContext(authority, 0);
            Configuration configuration = contextCreatePackageContext.getResources().getConfiguration();
            configuration.setLocale(Locale.US);
            resources = contextCreatePackageContext.createConfigurationContext(configuration).getResources();
        }
        return resources.getString(resources.getIdentifier(pathSegments.get(1), str2, authority));
    }

    public void onLocaleChanged() {
        this.mInternalDatabase.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$S0JqhTgPLezEOPzEF8bYKWLHABE
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$onLocaleChanged$13$MediaProvider((SQLiteDatabase) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onLocaleChanged$13, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Object lambda$onLocaleChanged$13$MediaProvider(SQLiteDatabase sQLiteDatabase) {
        localizeTitles(sQLiteDatabase);
        return null;
    }

    private void localizeTitles(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("files", new String[]{"_id", "title_resource_uri"}, "title_resource_uri IS NOT NULL", null, null, null, null);
        while (cursorQuery.moveToNext()) {
            try {
                String string = cursorQuery.getString(0);
                String string2 = cursorQuery.getString(1);
                ContentValues contentValues = new ContentValues();
                try {
                    contentValues.put("title_resource_uri", string2);
                    computeAudioLocalizedValues(contentValues);
                    computeAudioKeyValues(contentValues);
                    sQLiteDatabase.update("files", contentValues, "_id=?", new String[]{string});
                } catch (Exception unused) {
                    Log.e(Logging.TAG, "Error updating localized title for " + string2 + ", keeping old localization");
                }
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
        if (cursorQuery != null) {
            cursorQuery.close();
        }
    }

    private Uri insertFile(SQLiteQueryBuilder sQLiteQueryBuilder, DatabaseHelper databaseHelper, int i, Uri uri, Bundle bundle, ContentValues contentValues, int i2) throws VolumeNotFoundException, VolumeArgumentException {
        String asString;
        int iIntValue;
        if (contentValues.containsKey("_data")) {
            TextUtils.isEmpty(contentValues.getAsString("_data"));
        }
        ensureUniqueFileColumns(i, uri, bundle, contentValues, null);
        if (i2 == 2) {
            computeAudioLocalizedValues(contentValues);
            computeAudioKeyValues(contentValues);
        }
        String asString2 = contentValues.getAsString("_data");
        FileUtils.computeValuesFromData(contentValues, isFuseThread());
        contentValues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000));
        String asString3 = contentValues.getAsString("title");
        if (asString3 == null && asString2 != null) {
            asString3 = FileUtils.extractFileName(asString2);
        }
        contentValues.put("title", asString3);
        if (asString2 != null && new File(asString2).isDirectory()) {
            contentValues.put("format", (Integer) 12289);
            contentValues.putNull("mime_type");
            asString = null;
            iIntValue = 12289;
        } else {
            asString = contentValues.getAsString("mime_type");
            Integer asInteger = contentValues.getAsInteger("format");
            iIntValue = asInteger == null ? 0 : asInteger.intValue();
        }
        if (iIntValue == 0) {
            iIntValue = MimeUtils.resolveFormatCode(asString);
        }
        if (asString2 != null && asString2.endsWith("/")) {
            Log.e(Logging.TAG, "directory has trailing slash: " + asString2);
            return null;
        }
        if (iIntValue != 0) {
            contentValues.put("format", Integer.valueOf(iIntValue));
        }
        if (asString == null && asString2 != null && iIntValue != 12289) {
            asString = MimeUtils.resolveMimeType(new File(asString2));
        }
        if (asString != null) {
            contentValues.put("mime_type", asString);
            if (!isCallingPackageSelf() || !contentValues.containsKey("media_type")) {
                if (isFuseThread() && asString2 != null && shouldFileBeHidden(new File(asString2))) {
                    contentValues.put("media_type", (Integer) 0);
                } else {
                    contentValues.put("media_type", Integer.valueOf(MimeUtils.resolveMediaType(asString)));
                }
            }
        } else {
            contentValues.put("media_type", Integer.valueOf(i2));
        }
        if (i2 == 4) {
            if (contentValues.getAsString("name") == null && asString2 == null) {
                throw new IllegalArgumentException("no name was provided when inserting abstract playlist");
            }
        } else if (asString2 == null) {
            throw new IllegalArgumentException("no path was provided when inserting new file");
        }
        if (asString2 != null) {
            File file = new File(asString2);
            if (file.exists()) {
                contentValues.put("date_modified", Long.valueOf(file.lastModified() / 1000));
                if (!contentValues.containsKey("_size")) {
                    contentValues.put("_size", Long.valueOf(file.length()));
                }
            }
        }
        long jInsertAllowingUpsert = insertAllowingUpsert(sQLiteQueryBuilder, databaseHelper, contentValues, asString2);
        if (iIntValue == 12289) {
            synchronized (this.mDirectoryCache) {
                this.mDirectoryCache.put(asString2, Long.valueOf(jInsertAllowingUpsert));
            }
        }
        return ContentUris.withAppendedId(uri, jInsertAllowingUpsert);
    }

    private long insertAllowingUpsert(final SQLiteQueryBuilder sQLiteQueryBuilder, final DatabaseHelper databaseHelper, final ContentValues contentValues, final String str) throws SQLiteConstraintException {
        return ((Long) databaseHelper.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$FjHWs2-fjgG761OzVFzdTX4EWZg
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$insertAllowingUpsert$14$MediaProvider(contentValues, str, sQLiteQueryBuilder, databaseHelper, (SQLiteDatabase) obj);
            }
        })).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$insertAllowingUpsert$14, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Long lambda$insertAllowingUpsert$14$MediaProvider(ContentValues contentValues, String str, SQLiteQueryBuilder sQLiteQueryBuilder, DatabaseHelper databaseHelper, SQLiteDatabase sQLiteDatabase) {
        if (contentValues.getAsLong("parent") == null && str != null) {
            contentValues.put("parent", Long.valueOf(getParent(sQLiteDatabase, str)));
        }
        try {
            return Long.valueOf(sQLiteQueryBuilder.insert(databaseHelper, contentValues));
        } catch (SQLiteConstraintException e) {
            String allowedPackagesForUpsert = this.getAllowedPackagesForUpsert(contentValues.getAsString("owner_package_name"));
            SQLiteQueryBuilder queryBuilderForUpsert = this.getQueryBuilderForUpsert(str);
            long idIfPathOwnedByPackages = this.getIdIfPathOwnedByPackages(queryBuilderForUpsert, databaseHelper, str, allowedPackagesForUpsert);
            if (idIfPathOwnedByPackages != -1 && queryBuilderForUpsert.update(databaseHelper, contentValues, "_id=?", new String[]{Long.toString(idIfPathOwnedByPackages)}) == 1) {
                return Long.valueOf(idIfPathOwnedByPackages);
            }
            throw e;
        }
    }

    private long getIdIfPathOwnedByPackages(SQLiteQueryBuilder sQLiteQueryBuilder, DatabaseHelper databaseHelper, String str, String str2) {
        Cursor cursorQuery = sQLiteQueryBuilder.query(databaseHelper, new String[]{"_id"}, "_data =? AND " + DatabaseUtils.bindSelection("owner_package_name IN " + str2, new Object[0]), new String[]{str}, (String) null, (String) null, (String) null, (String) null, (CancellationSignal) null);
        try {
            if (cursorQuery.moveToFirst()) {
                long j = cursorQuery.getLong(0);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return j;
            }
            if (cursorQuery == null) {
                return -1L;
            }
            cursorQuery.close();
            return -1L;
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

    private String getAllowedPackagesForUpsert(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(this.mCallingIdentity.get().getSharedPackageNames()));
        if (str != null && isCallingPackageDelegator() && !isCallingIdentitySharedPackageName(str)) {
            arrayList.addAll(Arrays.asList(getSharedPackagesForPackage(str)));
        }
        return DatabaseUtils.bindList(arrayList.toArray());
    }

    private SQLiteQueryBuilder getQueryBuilderForUpsert(String str) {
        boolean zIsCallingPackageAllowedHidden = isCallingPackageAllowedHidden();
        Bundle bundle = new Bundle();
        bundle.putInt("android:query-arg-match-pending", 1);
        bundle.putInt("android:query-arg-match-trashed", 1);
        Uri contentUriForPath = FileUtils.getContentUriForPath(str);
        SQLiteQueryBuilder queryBuilder = getQueryBuilder(2, matchUri(contentUriForPath, zIsCallingPackageAllowedHidden), contentUriForPath, bundle, null);
        queryBuilder.setStrictColumns(false);
        return queryBuilder;
    }

    private void maybePut(ContentValues contentValues, String str, String str2) {
        if (str2 != null) {
            contentValues.put(str, str2);
        }
    }

    private boolean maybeMarkAsDownload(ContentValues contentValues) {
        String asString = contentValues.getAsString("_data");
        if (asString == null || !FileUtils.isDownload(asString)) {
            return false;
        }
        contentValues.put("is_download", (Integer) 1);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String resolveVolumeName(Uri uri) {
        String volumeName = MediaStore.getVolumeName(uri);
        return "external".equals(volumeName) ? "external_primary" : volumeName;
    }

    @Override // android.content.ContentProvider
    @Deprecated
    public Uri insert(Uri uri, ContentValues contentValues) {
        return insert(uri, contentValues, null);
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues, Bundle bundle) {
        Trace.beginSection("insert");
        try {
            try {
                return insertInternal(uri, contentValues, bundle);
            } catch (FallbackException e) {
                return e.translateForInsert(this.getCallingPackageTargetSdkVersion());
            } finally {
                Trace.endSection();
            }
        } catch (SQLiteConstraintException e2) {
            if (this.getCallingPackageTargetSdkVersion() >= 30) {
                throw e2;
            }
            return null;
            Trace.endSection();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:127:0x036a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.net.Uri insertInternal(android.net.Uri r25, android.content.ContentValues r26, android.os.Bundle r27) throws com.android.providers.media.MediaProvider.FallbackException {
        /*
            Method dump skipped, instruction units count: 1020
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.MediaProvider.insertInternal(android.net.Uri, android.content.ContentValues, android.os.Bundle):android.net.Uri");
    }

    @Override // android.content.ContentProvider
    public ContentProviderResult[] applyBatch(ArrayList<ContentProviderOperation> arrayList) throws OperationApplicationException {
        ArraySet arraySet = new ArraySet();
        try {
            try {
                for (ContentProviderOperation contentProviderOperation : arrayList) {
                    DatabaseHelper databaseForUri = getDatabaseForUri(contentProviderOperation.getUri());
                    if (!arraySet.contains(databaseForUri)) {
                        if (!databaseForUri.isTransactionActive()) {
                            databaseForUri.beginTransaction();
                            arraySet.add(databaseForUri);
                        } else if (!contentProviderOperation.isExceptionAllowed()) {
                            throw new IllegalStateException("Nested transactions not supported");
                        }
                    }
                }
                ContentProviderResult[] contentProviderResultArrApplyBatch = super.applyBatch(arrayList);
                Iterator it = arraySet.iterator();
                while (it.hasNext()) {
                    ((DatabaseHelper) it.next()).setTransactionSuccessful();
                }
                return contentProviderResultArrApplyBatch;
            } catch (VolumeNotFoundException e) {
                throw e.rethrowAsIllegalArgumentException();
            }
        } finally {
            Iterator it2 = arraySet.iterator();
            while (it2.hasNext()) {
                ((DatabaseHelper) it2.next()).endTransaction();
            }
        }
    }

    private void appendWhereStandaloneMatch(SQLiteQueryBuilder sQLiteQueryBuilder, String str, int i, Uri uri) {
        if (i != 1) {
            if (i == 2) {
                appendWhereStandalone(sQLiteQueryBuilder, getWhereClauseForMatchExclude(str), new Object[0]);
                return;
            }
            if (i == 3) {
                appendWhereStandalone(sQLiteQueryBuilder, str + "=?", 1);
                return;
            }
            if (i == 32) {
                String whereClauseForMatchableVisibleFromFilePath = getWhereClauseForMatchableVisibleFromFilePath(uri, str);
                if (whereClauseForMatchableVisibleFromFilePath != null) {
                    appendWhereStandalone(sQLiteQueryBuilder, whereClauseForMatchableVisibleFromFilePath, new Object[0]);
                    return;
                }
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    private static void appendWhereStandalone(SQLiteQueryBuilder sQLiteQueryBuilder, String str, Object... objArr) {
        sQLiteQueryBuilder.appendWhereStandalone(DatabaseUtils.bindSelection(str, objArr));
    }

    private static void appendWhereStandaloneFilter(SQLiteQueryBuilder sQLiteQueryBuilder, String[] strArr, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        for (String str2 : str.split("\\s+")) {
            appendWhereStandalone(sQLiteQueryBuilder, String.join("||", strArr) + " LIKE ? ESCAPE '\\'", "%" + DatabaseUtils.escapeForLike(MediaStore.Audio.keyFor(str2)) + "%");
        }
    }

    @Deprecated
    private String getSharedPackages() {
        return DatabaseUtils.bindList(this.mCallingIdentity.get().getSharedPackageNames());
    }

    private String[] getSharedPackagesForPackage(String str) {
        try {
            return getContext().getPackageManager().getPackagesForUid(getContext().getPackageManager().getPackageUid(str, 0));
        } catch (PackageManager.NameNotFoundException unused) {
            return new String[]{str};
        }
    }

    private SQLiteQueryBuilder getQueryBuilder(int i, int i2, Uri uri, Bundle bundle, Consumer<String> consumer) {
        Trace.beginSection("getQueryBuilder");
        try {
            return getQueryBuilderInternal(i, i2, uri, bundle, consumer);
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x04b6  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x04ca  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0530  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x09ee  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x09fc  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01ff  */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r2v75 */
    /* JADX WARN: Type inference failed for: r2v77 */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r4v38 */
    /* JADX WARN: Type inference failed for: r4v39 */
    /* JADX WARN: Type inference failed for: r4v42 */
    /* JADX WARN: Type inference failed for: r5v36 */
    /* JADX WARN: Type inference failed for: r5v38 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r9v41 */
    /* JADX WARN: Type inference failed for: r9v44 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.android.providers.media.util.SQLiteQueryBuilder getQueryBuilderInternal(int r54, int r55, android.net.Uri r56, android.os.Bundle r57, java.util.function.Consumer<java.lang.String> r58) {
        /*
            Method dump skipped, instruction units count: 2610
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.MediaProvider.getQueryBuilderInternal(int, int, android.net.Uri, android.os.Bundle, java.util.function.Consumer):com.android.providers.media.util.SQLiteQueryBuilder");
    }

    private boolean hasOwnerPackageName(Uri uri) {
        int iMatchUri = matchUri(uri, true);
        if (iMatchUri == 4 || iMatchUri == 5 || iMatchUri == VIDEO_THUMBNAILS || iMatchUri == VIDEO_THUMBNAILS_ID) {
            return false;
        }
        switch (iMatchUri) {
            case 119:
            case 120:
            case 121:
                return false;
            default:
                return true;
        }
    }

    @Override // android.content.ContentProvider
    @Deprecated
    public int delete(Uri uri, String str, String[] strArr) {
        return delete(uri, DatabaseUtils.createSqlQueryBundle(str, strArr, null));
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, Bundle bundle) {
        Trace.beginSection("delete");
        try {
            return deleteInternal(uri, bundle);
        } catch (FallbackException e) {
            return e.translateForUpdateDelete(this.getCallingPackageTargetSdkVersion());
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int deleteInternal(android.net.Uri r31, android.os.Bundle r32) throws com.android.providers.media.MediaProvider.FallbackException {
        /*
            Method dump skipped, instruction units count: 617
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.MediaProvider.deleteInternal(android.net.Uri, android.os.Bundle):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$deleteInternal$15, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Object lambda$deleteInternal$15$MediaProvider(long j, String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query("audio_playlists_map", new String[]{"playlist_id"}, "audio_id=" + j, null, "playlist_id", null, null);
        while (cursorQuery.moveToNext()) {
            try {
                resolvePlaylistMembers(ContentUris.withAppendedId(MediaStore.Audio.Playlists.getContentUri(str), cursorQuery.getLong(0)));
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
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.close();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$deleteInternal$16, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$deleteInternal$16$MediaProvider(LongSparseArray longSparseArray) {
        ((DownloadManager) getContext().getSystemService(DownloadManager.class)).onMediaStoreDownloadsDeleted(longSparseArray);
    }

    private int deleteRecursive(final SQLiteQueryBuilder sQLiteQueryBuilder, final DatabaseHelper databaseHelper, final String str, final String[] strArr) {
        return ((Integer) databaseHelper.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$66Co9d2SYurrd5nwUcTDCw0XWYU
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$deleteRecursive$17$MediaProvider(sQLiteQueryBuilder, databaseHelper, str, strArr, (SQLiteDatabase) obj);
            }
        })).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$deleteRecursive$17, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Integer lambda$deleteRecursive$17$MediaProvider(SQLiteQueryBuilder sQLiteQueryBuilder, DatabaseHelper databaseHelper, String str, String[] strArr, SQLiteDatabase sQLiteDatabase) {
        int iDelete;
        synchronized (this.mDirectoryCache) {
            this.mDirectoryCache.clear();
        }
        int i = 0;
        do {
            iDelete = sQLiteQueryBuilder.delete(databaseHelper, str, strArr);
            i += iDelete;
        } while (iDelete > 0);
        return Integer.valueOf(i);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        Trace.beginSection("call");
        try {
            return callInternal(str, str2, bundle);
        } finally {
            Trace.endSection();
        }
    }

    private Bundle callInternal(String str, String str2, Bundle bundle) {
        byte b;
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity;
        ContentProvider.CallingIdentity callingIdentityClearCallingIdentity;
        ContentProviderClient contentProviderClientAcquireUnstableContentProviderClient;
        b = -1;
        switch (str) {
            case "resolve_playlist_members":
                localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
                callingIdentityClearCallingIdentity = clearCallingIdentity();
                try {
                    resolvePlaylistMembers((Uri) bundle.getParcelable("uri"));
                    return null;
                } finally {
                }
            case "run_idle_maintenance":
                getContext().enforceCallingOrSelfPermission("android.permission.DUMP", Logging.TAG);
                localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
                callingIdentityClearCallingIdentity = clearCallingIdentity();
                try {
                    onIdleMaintenance(new CancellationSignal());
                    return null;
                } finally {
                }
            case "wait_for_idle":
                ForegroundThread.waitForIdle();
                BackgroundThread.waitForIdle();
                return null;
            case "scan_file":
            case "scan_volume":
                localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
                callingIdentityClearCallingIdentity = clearCallingIdentity();
                try {
                    try {
                        Bundle bundle2 = new Bundle();
                        int iHashCode = str.hashCode();
                        if (iHashCode != -1825673730) {
                            if (iHashCode == -1662171364 && str.equals("scan_volume")) {
                                b = 1;
                            }
                        } else if (str.equals("scan_file")) {
                            b = 0;
                        }
                        if (b == 0) {
                            bundle2.putParcelable("android.intent.extra.STREAM", scanFile(new File(str2), 2));
                        } else if (b == 1) {
                            MediaService.onScanVolume(getContext(), str2, 2);
                        }
                        return bundle2;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } finally {
                }
            case "get_version":
                try {
                    String str3 = (String) getDatabaseForUri(MediaStore.Files.getContentUri(bundle.getString("android.intent.extra.TEXT"))).runWithoutTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$D3Fx4JT7Kj7iKg8s84cJqX_n9gk
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return MediaProvider.lambda$callInternal$18((SQLiteDatabase) obj);
                        }
                    });
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("android.intent.extra.TEXT", str3);
                    return bundle3;
                } catch (VolumeNotFoundException e2) {
                    throw e2.rethrowAsIllegalArgumentException();
                }
            case "get_generation":
                try {
                    long jLongValue = ((Long) getDatabaseForUri(MediaStore.Files.getContentUri(bundle.getString("android.intent.extra.TEXT"))).runWithoutTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$mAPV7EE-uYguqHTxV8SBkmql5Yw
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return Long.valueOf(DatabaseHelper.getGeneration((SQLiteDatabase) obj));
                        }
                    })).longValue();
                    Bundle bundle4 = new Bundle();
                    bundle4.putLong("android.intent.extra.INDEX", jLongValue);
                    return bundle4;
                } catch (VolumeNotFoundException e3) {
                    throw e3.rethrowAsIllegalArgumentException();
                }
            case "get_document_uri":
                Uri uri = (Uri) bundle.getParcelable("uri");
                enforceCallingPermission(uri, bundle, false);
                localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
                try {
                    try {
                        Uri uriFromFile = Uri.fromFile(queryForDataFile(uri, null));
                        try {
                            contentProviderClientAcquireUnstableContentProviderClient = getContext().getContentResolver().acquireUnstableContentProviderClient("com.android.externalstorage.documents");
                            try {
                                bundle.putParcelable("uri", uriFromFile);
                                Bundle bundleCall = contentProviderClientAcquireUnstableContentProviderClient.call(str, null, bundle);
                                if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                                    contentProviderClientAcquireUnstableContentProviderClient.close();
                                }
                                return bundleCall;
                            } finally {
                                if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                                    try {
                                        contentProviderClientAcquireUnstableContentProviderClient.close();
                                    } catch (Throwable th) {
                                        th.addSuppressed(th);
                                    }
                                    break;
                                }
                            }
                        } catch (RemoteException e4) {
                            throw new IllegalStateException(e4);
                        }
                    } catch (FileNotFoundException e5) {
                        throw new IllegalArgumentException(e5);
                    }
                } finally {
                }
            case "get_media_uri":
                getContext().enforceCallingUriPermission((Uri) bundle.getParcelable("uri"), 1, Logging.TAG);
                try {
                    contentProviderClientAcquireUnstableContentProviderClient = getContext().getContentResolver().acquireUnstableContentProviderClient("com.android.externalstorage.documents");
                    try {
                        Uri uri2 = (Uri) contentProviderClientAcquireUnstableContentProviderClient.call(str, null, bundle).getParcelable("uri");
                        if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                            contentProviderClientAcquireUnstableContentProviderClient.close();
                        }
                        localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
                        try {
                            try {
                                Bundle bundle5 = new Bundle();
                                bundle5.putParcelable("uri", queryForMediaUri(new File(uri2.getPath()), null));
                                return bundle5;
                            } catch (FileNotFoundException e6) {
                                throw new IllegalArgumentException(e6);
                            }
                        } finally {
                        }
                    } finally {
                        if (contentProviderClientAcquireUnstableContentProviderClient != null) {
                            try {
                                break;
                            } catch (Throwable th2) {
                            }
                        }
                    }
                } catch (RemoteException e7) {
                    throw new IllegalStateException(e7);
                }
            case "create_write_request":
            case "create_favorite_request":
            case "create_trash_request":
            case "create_delete_request":
                PendingIntent pendingIntentCreateRequest = createRequest(str, bundle);
                Bundle bundle6 = new Bundle();
                bundle6.putParcelable("result", pendingIntentCreateRequest);
                return bundle6;
            default:
                throw new UnsupportedOperationException("Unsupported call: " + str);
        }
    }

    static /* synthetic */ String lambda$callInternal$18(SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.getVersion() + ":" + DatabaseHelper.getOrCreateUuid(sQLiteDatabase);
    }

    static List<Uri> collectUris(ClipData clipData) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < clipData.getItemCount(); i++) {
            arrayList.add(clipData.getItemAt(i).getUri());
        }
        return arrayList;
    }

    private PendingIntent createRequest(String str, Bundle bundle) {
        List listAsList;
        Iterator<Uri> it = collectUris((ClipData) bundle.getParcelable("clip_data")).iterator();
        while (it.hasNext()) {
            int iMatchUri = matchUri(it.next(), false);
            if (iMatchUri != 2 && iMatchUri != 101 && iMatchUri != VIDEO_MEDIA_ID) {
                throw new IllegalArgumentException("All requested items must be referenced by specific ID");
            }
        }
        ContentValues contentValues = (ContentValues) bundle.getParcelable("content_values");
        byte b = -1;
        int iHashCode = str.hashCode();
        if (iHashCode != -208838705) {
            if (iHashCode == 1948507781 && str.equals("create_trash_request")) {
                b = 1;
            }
        } else if (str.equals("create_favorite_request")) {
            b = 0;
        }
        if (b == 0) {
            listAsList = Arrays.asList("is_favorite");
        } else if (b == 1) {
            listAsList = Arrays.asList("is_trashed");
        } else {
            listAsList = Arrays.asList(new String[0]);
        }
        if (contentValues != null) {
            for (String str2 : contentValues.keySet()) {
                if (!listAsList.contains(str2)) {
                    throw new IllegalArgumentException("Invalid column " + str2);
                }
            }
        }
        Context context = getContext();
        Intent intent = new Intent(str, null, context, PermissionActivity.class);
        intent.putExtras(bundle);
        return PendingIntent.getActivity(context, 42, intent, 1409286144);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String ensureCustomCollator(final String str) {
        new ULocale(str);
        final String str2 = "custom_" + str.replaceAll("[^a-zA-Z]", "");
        synchronized (this.mCustomCollators) {
            if (!this.mCustomCollators.contains(str2)) {
                DatabaseHelper[] databaseHelperArr = {this.mInternalDatabase, this.mExternalDatabase};
                for (int i = 0; i < 2; i++) {
                    databaseHelperArr[i].runWithoutTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$11KdIZSoJnS8rdR3UAaAk8NhLrQ
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return MediaProvider.lambda$ensureCustomCollator$20(str, str2, (SQLiteDatabase) obj);
                        }
                    });
                }
                this.mCustomCollators.add(str2);
            }
        }
        return str2;
    }

    static /* synthetic */ Object lambda$ensureCustomCollator$20(String str, String str2, SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execPerConnectionSQL("SELECT icu_load_collation(?, ?);", new String[]{str, str2});
        return null;
    }

    private int pruneThumbnails(SQLiteDatabase sQLiteDatabase, CancellationSignal cancellationSignal) {
        LongArray longArray = new LongArray();
        Cursor cursorQuery = sQLiteDatabase.query(true, "files", new String[]{"_id"}, null, null, null, null, null, null, cancellationSignal);
        while (cursorQuery.moveToNext()) {
            try {
                longArray.add(cursorQuery.getLong(0));
            } catch (Throwable th) {
                if (cursorQuery == null) {
                    throw th;
                }
                try {
                    cursorQuery.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        }
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        long[] array = longArray.toArray();
        Arrays.sort(array);
        int i = 0;
        for (String str : getExternalVolumeNames()) {
            try {
                for (File file : getThumbnailDirectories(str)) {
                    cancellationSignal.throwIfCanceled();
                    File[] fileArrListFiles = file.listFiles();
                    if (fileArrListFiles == null) {
                        fileArrListFiles = new File[0];
                    }
                    for (File file2 : fileArrListFiles) {
                        if (!Objects.equals(file2.getName(), FILE_DATABASE_UUID)) {
                            if (Arrays.binarySearch(array, Long.parseLong(FileUtils.extractFileName(file2.getName()))) < 0) {
                                Log.v(Logging.TAG, "Deleting stale thumbnail " + file2);
                                deleteAndInvalidate(file2);
                                i++;
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                Log.w(Logging.TAG, "Failed to resolve volume " + str, e);
            }
        }
        sQLiteDatabase.execSQL("delete from thumbnails where image_id not in (select _id from images)");
        sQLiteDatabase.execSQL("delete from videothumbnails where video_id not in (select _id from video)");
        return i;
    }

    abstract class Thumbnailer {
        final String directoryName;

        public abstract Bitmap getThumbnailBitmap(Uri uri, CancellationSignal cancellationSignal) throws IOException;

        public Thumbnailer(String str) {
            this.directoryName = str;
        }

        private File getThumbnailFile(Uri uri) throws IOException {
            return FileUtils.buildPath(MediaProvider.this.getVolumePath(MediaProvider.resolveVolumeName(uri)), this.directoryName, MediaProvider.DIRECTORY_THUMBNAILS, ContentUris.parseId(uri) + ".jpg");
        }

        public ParcelFileDescriptor ensureThumbnail(Uri uri, CancellationSignal cancellationSignal) throws Throwable {
            ParcelFileDescriptor parcelFileDescriptor;
            ParcelFileDescriptor parcelFileDescriptorOpenSafely;
            File thumbnailFile = getThumbnailFile(uri);
            try {
                return FileUtils.openSafely(thumbnailFile, 268435456);
            } catch (FileNotFoundException unused) {
                File parentFile = thumbnailFile.getParentFile();
                parentFile.mkdirs();
                ParcelFileDescriptor parcelFileDescriptorOpenSafely2 = null;
                File fileCreateTempFile = File.createTempFile("thumb", null, parentFile);
                try {
                    parcelFileDescriptorOpenSafely = FileUtils.openSafely(fileCreateTempFile, 671088640);
                } catch (Throwable th) {
                    th = th;
                    parcelFileDescriptor = null;
                }
                try {
                    parcelFileDescriptorOpenSafely2 = FileUtils.openSafely(fileCreateTempFile, 268435456);
                    this.getThumbnailBitmap(uri, cancellationSignal).compress(Bitmap.CompressFormat.JPEG, 90, new FileOutputStream(parcelFileDescriptorOpenSafely.getFileDescriptor()));
                    try {
                        Os.rename(fileCreateTempFile.getAbsolutePath(), thumbnailFile.getAbsolutePath());
                    } catch (ErrnoException e) {
                        e.rethrowAsIOException();
                    }
                    ParcelFileDescriptor parcelFileDescriptorDup = parcelFileDescriptorOpenSafely2.dup();
                    FileUtils.closeQuietly(parcelFileDescriptorOpenSafely);
                    FileUtils.closeQuietly(parcelFileDescriptorOpenSafely2);
                    MediaProvider.this.deleteAndInvalidate(fileCreateTempFile);
                    return parcelFileDescriptorDup;
                } catch (Throwable th2) {
                    th = th2;
                    parcelFileDescriptor = parcelFileDescriptorOpenSafely2;
                    parcelFileDescriptorOpenSafely2 = parcelFileDescriptorOpenSafely;
                    FileUtils.closeQuietly(parcelFileDescriptorOpenSafely2);
                    FileUtils.closeQuietly(parcelFileDescriptor);
                    MediaProvider.this.deleteAndInvalidate(fileCreateTempFile);
                    throw th;
                }
            }
        }

        public void invalidateThumbnail(Uri uri) throws IOException {
            MediaProvider.this.deleteAndInvalidate(getThumbnailFile(uri));
        }
    }

    private List<File> getThumbnailDirectories(String str) throws FileNotFoundException {
        File volumePath = getVolumePath(str);
        return Arrays.asList(FileUtils.buildPath(volumePath, DIRECTORY_MUSIC, DIRECTORY_THUMBNAILS), FileUtils.buildPath(volumePath, DIRECTORY_MOVIES, DIRECTORY_THUMBNAILS), FileUtils.buildPath(volumePath, DIRECTORY_PICTURES, DIRECTORY_THUMBNAILS));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: invalidateThumbnails, reason: merged with bridge method [inline-methods] */
    public void lambda$updateInternal$23$MediaProvider(Uri uri) {
        Trace.beginSection("invalidateThumbnails");
        try {
            invalidateThumbnailsInternal(uri);
        } finally {
            Trace.endSection();
        }
    }

    private void invalidateThumbnailsInternal(final Uri uri) {
        final long id = ContentUris.parseId(uri);
        try {
            this.mAudioThumbnailer.invalidateThumbnail(uri);
            this.mVideoThumbnailer.invalidateThumbnail(uri);
            this.mImageThumbnailer.invalidateThumbnail(uri);
        } catch (IOException unused) {
        }
        try {
            getDatabaseForUri(uri).runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$lr2HxYx46jOBFdQwGoCMK6xozUY
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f$0.lambda$invalidateThumbnailsInternal$21$MediaProvider(id, uri, (SQLiteDatabase) obj);
                }
            });
        } catch (VolumeNotFoundException e) {
            Log.w(Logging.TAG, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$invalidateThumbnailsInternal$21, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Object lambda$invalidateThumbnailsInternal$21$MediaProvider(long j, Uri uri, SQLiteDatabase sQLiteDatabase) {
        String string = Long.toString(j);
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select _data from thumbnails where image_id=? union all select _data from videothumbnails where video_id=?", new String[]{string, string});
        while (cursorRawQuery.moveToNext()) {
            try {
                deleteIfAllowed(uri, Bundle.EMPTY, cursorRawQuery.getString(0));
            } catch (Throwable th) {
                if (cursorRawQuery != null) {
                    try {
                        cursorRawQuery.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        sQLiteDatabase.execSQL("delete from thumbnails where image_id=?", new String[]{string});
        sQLiteDatabase.execSQL("delete from videothumbnails where video_id=?", new String[]{string});
        return null;
    }

    @Override // android.content.ContentProvider
    @Deprecated
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return update(uri, contentValues, DatabaseUtils.createSqlQueryBundle(str, strArr, null));
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, Bundle bundle) {
        Trace.beginSection("update");
        try {
            return updateInternal(uri, contentValues, bundle);
        } catch (FallbackException e) {
            return e.translateForUpdateDelete(this.getCallingPackageTargetSdkVersion());
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v48, types: [android.net.Uri] */
    /* JADX WARN: Type inference failed for: r2v49 */
    /* JADX WARN: Type inference failed for: r2v52 */
    private int updateInternal(Uri uri, ContentValues contentValues, Bundle bundle) throws Throwable {
        String str;
        boolean z;
        int iResolvePlaylistIndex;
        String str2;
        String str3;
        String str4;
        int i;
        String str5;
        int i2;
        int i3;
        int i4;
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity;
        int i5;
        int i6;
        String str6;
        String str7;
        String str8;
        DatabaseHelper databaseHelper;
        String str9;
        final File file;
        Throwable th;
        String str10;
        int i7;
        Uri contentUri = uri;
        Bundle bundle2 = bundle != null ? bundle : new Bundle();
        bundle2.remove("android:query-arg-related-uri");
        bundle2.remove(INCLUDED_DEFAULT_DIRECTORIES);
        String string = bundle2.getString("android:query-arg-sql-selection");
        String[] stringArray = bundle2.getStringArray("android:query-arg-sql-selection-args");
        if ("com.google.android.GoogleCamera".equals(getCallingPackageOrSelf()) && getCallingPackageTargetSdkVersion() <= 29) {
            if (matchUri(contentUri, false) == 2) {
                Log.w(Logging.TAG, "Working around app bug in b/111966296");
                contentUri = MediaStore.Files.getContentUri("external", ContentUris.parseId(uri));
            } else if (matchUri(contentUri, false) == VIDEO_MEDIA_ID) {
                Log.w(Logging.TAG, "Working around app bug in b/112246630");
                contentUri = MediaStore.Files.getContentUri("external", ContentUris.parseId(uri));
            }
        }
        final Uri uriSafeUncanonicalize = safeUncanonicalize(contentUri);
        String volumeName = MediaStore.getVolumeName(uriSafeUncanonicalize);
        getCallingPackageTargetSdkVersion();
        int iMatchUri = matchUri(uriSafeUncanonicalize, isCallingPackageAllowedHidden());
        if (iMatchUri == 112) {
            str = volumeName;
            z = false;
        } else if (iMatchUri == 113) {
            str = volumeName;
            z = false;
            bundle2.putString("android:query-arg-sql-selection", "_id=" + uriSafeUncanonicalize.getPathSegments().get(5));
        } else {
            DatabaseHelper databaseForUri = getDatabaseForUri(uriSafeUncanonicalize);
            SQLiteQueryBuilder queryBuilder = getQueryBuilder(2, iMatchUri, uriSafeUncanonicalize, bundle2, null);
            if (iMatchUri == 2 || iMatchUri == 101 || iMatchUri == VIDEO_MEDIA_ID) {
                enforceCallingPermission(uriSafeUncanonicalize, bundle2, true);
            }
            if (contentValues != null) {
                contentValues.remove("_id");
                FileUtils.computeDateExpires(contentValues);
                for (String str11 : sDataColumns.keySet()) {
                    if (contentValues.containsKey(str11)) {
                        if (!isCallingPackageSelf() && !isCallingPackageLegacyWrite()) {
                            Log.w(Logging.TAG, "Ignoring mutation of  " + str11 + " from " + getCallingPackageOrSelf());
                            contentValues.remove(str11);
                        }
                    }
                }
                if (!contentValues.containsKey("owner_package_name") || isCallingPackageSelf() || isCallingPackageShell()) {
                    str10 = " from ";
                    str2 = " to ";
                    str3 = "_id";
                    str4 = volumeName;
                } else if (isCallingPackageDelegator()) {
                    String asString = contentValues.getAsString("owner_package_name");
                    try {
                        str10 = " from ";
                        str2 = " to ";
                        str3 = "_id";
                        str4 = volumeName;
                        Cursor cursorQueryForSingleItem = queryForSingleItem(MediaStore.Files.getContentUri(volumeName, ContentUris.parseId(uriSafeUncanonicalize)), new String[]{"owner_package_name"}, null, null, null);
                        try {
                            String string2 = cursorQueryForSingleItem.getString(0);
                            if (cursorQueryForSingleItem != null) {
                                cursorQueryForSingleItem.close();
                            }
                            if (string2 == null || Arrays.asList(getSharedPackagesForPackage(getCallingPackageOrSelf())).contains(string2)) {
                                Log.v(Logging.TAG, "Ownership transfer from " + string2 + str2 + asString + " allowed");
                            } else {
                                Log.w(Logging.TAG, "Ownership transfer from " + string2 + str2 + asString + " blocked");
                                contentValues.remove("owner_package_name");
                            }
                        } finally {
                        }
                    } catch (FileNotFoundException e) {
                        throw new IllegalStateException(e);
                    }
                } else {
                    str10 = " from ";
                    str2 = " to ";
                    str3 = "_id";
                    str4 = volumeName;
                    contentValues.remove("owner_package_name");
                }
                if (isCallingPackageSelf()) {
                    i7 = 0;
                } else {
                    Trace.beginSection("filter");
                    CachedSupplier cachedSupplier = new CachedSupplier(new Supplier() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$4HaDMe1Km4r-Uq0a9QPp2nTM9gE
                        @Override // java.util.function.Supplier
                        public final Object get() {
                            return this.f$0.lambda$updateInternal$22$MediaProvider(uriSafeUncanonicalize);
                        }
                    });
                    i7 = 0;
                    for (String str12 : new ArraySet(contentValues.keySet())) {
                        if (!sMutableColumns.contains(str12) && !((Boolean) cachedSupplier.get()).booleanValue()) {
                            Log.w(Logging.TAG, "Ignoring mutation of " + str12 + str10 + getCallingPackageOrSelf());
                            contentValues.remove(str12);
                            i7 = 1;
                        }
                        if ("is_pending".equals(str12)) {
                            contentValues.putNull("date_modified");
                            contentValues.putNull("_size");
                            i7 = 1;
                        }
                    }
                    Trace.endSection();
                }
                if ("files".equals(queryBuilder.getTables())) {
                    maybeMarkAsDownload(contentValues);
                }
                if (contentValues.containsKey("latitude")) {
                    contentValues.putNull("latitude");
                }
                if (contentValues.containsKey("longitude")) {
                    contentValues.putNull("longitude");
                }
                if (getCallingPackageTargetSdkVersion() <= 29) {
                    if (contentValues.containsKey("primary_directory")) {
                        contentValues.remove("primary_directory");
                    }
                    if (contentValues.containsKey("secondary_directory")) {
                        contentValues.remove("secondary_directory");
                    }
                }
                i = i7;
            } else {
                str2 = " to ";
                str3 = "_id";
                str4 = volumeName;
                i = 0;
            }
            if (contentValues.isEmpty()) {
                return 0;
            }
            boolean z2 = iMatchUri == 4 || iMatchUri == 5 || iMatchUri == 119 || iMatchUri == 120 || iMatchUri == VIDEO_THUMBNAILS || iMatchUri == VIDEO_THUMBNAILS_ID;
            if ((iMatchUri == 110 || iMatchUri == 111) && !isCallingPackageSelf()) {
                if (contentValues.containsKey("name")) {
                    contentValues.put("_display_name", contentValues.getAsString("name"));
                }
                if (!contentValues.containsKey("mime_type")) {
                    contentValues.put("mime_type", "audio/mpegurl");
                }
            }
            boolean z3 = bundle2.getBoolean("android:query-arg-allow-movement", !isCallingPackageSelf());
            if (!containsAny(contentValues.keySet(), sPlacementColumns) || contentValues.containsKey("_data") || z2 || !z3) {
                str5 = str4;
                i2 = 0;
                i3 = i;
            } else {
                Trace.beginSection("movement");
                if (iMatchUri != 2 && iMatchUri != 101 && iMatchUri != 111) {
                    if (iMatchUri != VIDEO_MEDIA_ID && iMatchUri != FILES_ID && iMatchUri != DOWNLOADS_ID) {
                        throw new IllegalArgumentException("Movement of " + uriSafeUncanonicalize + " which isn't part of well-defined collection not allowed");
                    }
                }
                LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity2 = clearLocalCallingIdentity();
                String str13 = str4;
                LocalCallingIdentity contentUri2 = MediaStore.Files.getContentUri(str13, ContentUris.parseId(uriSafeUncanonicalize));
                try {
                    try {
                        i2 = 0;
                        try {
                            Cursor cursorQueryForSingleItem2 = queryForSingleItem(contentUri2, (String[]) sPlacementColumns.toArray(new String[0]), string, stringArray, null);
                            for (int i8 = 0; i8 < cursorQueryForSingleItem2.getColumnCount(); i8++) {
                                try {
                                    try {
                                        String columnName = cursorQueryForSingleItem2.getColumnName(i8);
                                        if (!contentValues.containsKey(columnName)) {
                                            contentValues.put(columnName, cursorQueryForSingleItem2.getString(i8));
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (cursorQueryForSingleItem2 == null) {
                                            throw th;
                                        }
                                        try {
                                            cursorQueryForSingleItem2.close();
                                            throw th;
                                        } catch (Throwable th3) {
                                            try {
                                                th.addSuppressed(th3);
                                                throw th;
                                            } catch (FileNotFoundException e2) {
                                                e = e2;
                                                throw new IllegalStateException(e);
                                            }
                                        }
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                }
                            }
                            if (cursorQueryForSingleItem2 != null) {
                                cursorQueryForSingleItem2.close();
                            }
                            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity2);
                            String asString2 = contentValues.getAsString("_data");
                            String strExtractVolumeName = FileUtils.extractVolumeName(asString2);
                            String strExtractPathOwnerPackageName = FileUtils.extractPathOwnerPackageName(asString2);
                            contentValues.remove("_data");
                            int i9 = i;
                            str5 = str13;
                            ensureNonUniqueFileColumns(iMatchUri, uriSafeUncanonicalize, bundle2, contentValues, asString2);
                            String asString3 = contentValues.getAsString("_data");
                            String strExtractVolumeName2 = FileUtils.extractVolumeName(asString3);
                            String strExtractPathOwnerPackageName2 = FileUtils.extractPathOwnerPackageName(asString3);
                            if (Objects.equals(asString2, asString3)) {
                                Log.d(Logging.TAG, "Identical paths " + asString2 + "; not moving");
                                i3 = i9;
                            } else {
                                if (!Objects.equals(strExtractVolumeName, strExtractVolumeName2)) {
                                    throw new IllegalArgumentException("Changing volume from " + asString2 + str2 + asString3 + " not allowed");
                                }
                                if (!Objects.equals(strExtractPathOwnerPackageName, strExtractPathOwnerPackageName2)) {
                                    throw new IllegalArgumentException("Changing ownership from " + asString2 + str2 + asString3 + " not allowed");
                                }
                                contentValues.remove("_data");
                                ensureUniqueFileColumns(iMatchUri, uriSafeUncanonicalize, bundle2, contentValues, asString2);
                                String asString4 = contentValues.getAsString("_data");
                                Log.d(Logging.TAG, "Moving " + asString2 + str2 + asString4);
                                try {
                                    Os.rename(asString2, asString4);
                                    invalidateFuseDentry(asString2);
                                    invalidateFuseDentry(asString4);
                                } catch (ErrnoException e3) {
                                    if (e3.errno != OsConstants.ENOENT) {
                                        throw new IllegalStateException(e3);
                                    }
                                    Log.d(Logging.TAG, "Missing file at " + asString2 + "; continuing anyway");
                                }
                                contentValues.put("_data", asString4);
                                i3 = 1;
                            }
                            Trace.endSection();
                        } catch (FileNotFoundException e4) {
                            e = e4;
                        } catch (Throwable th5) {
                            th = th5;
                            contentUri2 = localCallingIdentityClearLocalCallingIdentity2;
                            restoreLocalCallingIdentity(contentUri2);
                            throw th;
                        }
                    } catch (FileNotFoundException e5) {
                        e = e5;
                    } catch (Throwable th6) {
                        th = th6;
                        contentUri2 = localCallingIdentityClearLocalCallingIdentity2;
                    }
                } catch (Throwable th7) {
                    th = th7;
                }
            }
            assertFileColumnsSane(iMatchUri, uriSafeUncanonicalize, contentValues);
            if (contentValues.containsKey("_data")) {
                String asString5 = contentValues.getAsString("_data");
                if (!TextUtils.isEmpty(asString5) && new File(asString5).exists()) {
                    i3 = 1;
                }
                i4 = 1;
            } else {
                i4 = i2;
            }
            int i10 = isCallingPackageSelf() ? i2 : i3;
            LongArray longArray = new LongArray();
            if (i4 == 0 && i10 == 0) {
                i5 = iMatchUri;
                str6 = Logging.TAG;
                i6 = i2;
                str7 = str5;
                str8 = "_data";
            } else {
                Trace.beginSection("snapshot");
                localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
                try {
                    i5 = iMatchUri;
                    i6 = i2;
                    str6 = Logging.TAG;
                    str7 = str5;
                    str8 = "_data";
                    Cursor cursorQuery = queryBuilder.query(databaseForUri, new String[]{str3}, string, stringArray, (String) null, (String) null, (String) null, (String) null, (CancellationSignal) null);
                    while (cursorQuery.moveToNext()) {
                        try {
                            longArray.add(cursorQuery.getLong(i6));
                        } finally {
                        }
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } finally {
                }
            }
            ContentValues contentValues2 = new ContentValues(contentValues);
            int i11 = i5;
            if (i11 == 2 || i11 == 101 || i11 == 111 || i11 == VIDEO_MEDIA_ID || i11 == FILES_ID || i11 == DOWNLOADS_ID) {
                FileUtils.computeValuesFromData(contentValues2, isFuseThread());
            }
            if (contentValues.containsKey("media_type") && contentValues.getAsInteger("media_type").intValue() == 2) {
                computeAudioLocalizedValues(contentValues2);
                computeAudioKeyValues(contentValues2);
            }
            int i12 = i6;
            int iUpdateAllowingReplace = updateAllowingReplace(queryBuilder, databaseForUri, contentValues2, string, stringArray);
            if (i4 != 0 || i10 != 0) {
                Trace.beginSection("invalidate");
                localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
                int i13 = i12;
                while (i13 < longArray.size()) {
                    try {
                        String str14 = str7;
                        final Uri contentUri3 = MediaStore.Files.getContentUri(str14, longArray.get(i13));
                        DatabaseHelper databaseHelper2 = databaseForUri;
                        databaseHelper2.postBackground(new Runnable() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$cFNZTL6fMmr8RQY2U1j1sRRAr-c
                            @Override // java.lang.Runnable
                            public final void run() {
                                this.f$0.lambda$updateInternal$23$MediaProvider(contentUri3);
                            }
                        });
                        if (i10 != 0) {
                            try {
                                try {
                                    Cursor cursorQueryForSingleItem3 = queryForSingleItem(contentUri3, new String[]{str8}, null, null, null);
                                    try {
                                        file = new File(cursorQueryForSingleItem3.getString(i12));
                                        databaseHelper = databaseHelper2;
                                    } catch (Throwable th8) {
                                        th = th8;
                                        databaseHelper = databaseHelper2;
                                    }
                                    try {
                                        databaseHelper.postBlocking(new Runnable() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$ngOkkVNZFnx7XlI6cnkQUvqi2Fc
                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                this.f$0.lambda$updateInternal$24$MediaProvider(file);
                                            }
                                        });
                                        if (cursorQueryForSingleItem3 != null) {
                                            try {
                                                cursorQueryForSingleItem3.close();
                                            } catch (Exception e6) {
                                                e = e6;
                                                str9 = str6;
                                                Log.w(str9, "Failed to update metadata for " + contentUri3, e);
                                            }
                                        }
                                    } catch (Throwable th9) {
                                        th = th9;
                                        Throwable th10 = th;
                                        if (cursorQueryForSingleItem3 != null) {
                                            try {
                                                cursorQueryForSingleItem3.close();
                                            } catch (Throwable th11) {
                                                th10.addSuppressed(th11);
                                            }
                                        }
                                        throw th10;
                                    }
                                } catch (Exception e7) {
                                    e = e7;
                                    databaseHelper = databaseHelper2;
                                }
                            } catch (Exception e8) {
                                e = e8;
                                databaseHelper = databaseHelper2;
                            }
                        } else {
                            databaseHelper = databaseHelper2;
                        }
                        str9 = str6;
                        i13++;
                        str6 = str9;
                        databaseForUri = databaseHelper;
                        str7 = str14;
                    } finally {
                    }
                }
            }
            return iUpdateAllowingReplace;
        }
        Uri uriWithAppendedId = ContentUris.withAppendedId(MediaStore.Audio.Playlists.getContentUri(str), Long.parseLong(uriSafeUncanonicalize.getPathSegments().get(3)));
        if (uriSafeUncanonicalize.getBooleanQueryParameter("move", z)) {
            int i14 = Integer.parseInt(uriSafeUncanonicalize.getPathSegments().get(5)) + 1;
            int iIntValue = contentValues.getAsInteger("play_order").intValue() + 1;
            bundle2.putString("android:query-arg-sql-selection", "play_order=" + i14);
            contentValues.put("play_order", Integer.valueOf(iIntValue));
        }
        if (contentValues.containsKey("play_order")) {
            iResolvePlaylistIndex = movePlaylistMembers(uriWithAppendedId, contentValues, bundle2);
        } else {
            iResolvePlaylistIndex = resolvePlaylistIndex(uriWithAppendedId, bundle2);
        }
        if (!contentValues.containsKey("audio_id")) {
            return 1;
        }
        Bundle bundle3 = new Bundle();
        StringBuilder sb = new StringBuilder();
        sb.append("play_order=");
        int i15 = iResolvePlaylistIndex + 1;
        sb.append(i15);
        bundle3.putString("android:query-arg-sql-selection", sb.toString());
        removePlaylistMembers(uriWithAppendedId, bundle3);
        ContentValues contentValues3 = new ContentValues();
        contentValues3.put("audio_id", contentValues.getAsString("audio_id"));
        contentValues3.put("play_order", Integer.valueOf(i15));
        addPlaylistMembers(uriWithAppendedId, contentValues3);
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateInternal$22, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Boolean lambda$updateInternal$22$MediaProvider(Uri uri) {
        return Boolean.valueOf(isPending(uri));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateInternal$24, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$updateInternal$24$MediaProvider(File file) {
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
        try {
            this.mMediaScanner.scanFile(file, 2);
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    private int updateAllowingReplace(final SQLiteQueryBuilder sQLiteQueryBuilder, final DatabaseHelper databaseHelper, final ContentValues contentValues, final String str, final String[] strArr) throws SQLiteConstraintException {
        return ((Integer) databaseHelper.runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$MKqBLjeqJzRznq_qwlNQl_bhmj4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$updateAllowingReplace$25$MediaProvider(sQLiteQueryBuilder, databaseHelper, contentValues, str, strArr, (SQLiteDatabase) obj);
            }
        })).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$updateAllowingReplace$25, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Integer lambda$updateAllowingReplace$25$MediaProvider(SQLiteQueryBuilder sQLiteQueryBuilder, DatabaseHelper databaseHelper, ContentValues contentValues, String str, String[] strArr, SQLiteDatabase sQLiteDatabase) {
        String asString;
        try {
            return Integer.valueOf(sQLiteQueryBuilder.update(databaseHelper, contentValues, str, strArr));
        } catch (SQLiteConstraintException e) {
            if (this.getCallingPackageTargetSdkVersion() >= 30 || (asString = contentValues.getAsString("_data")) == null || !new File(asString).exists()) {
                throw e;
            }
            Uri contentUriForPath = FileUtils.getContentUriForPath(asString);
            boolean zIsCallingPackageAllowedHidden = this.isCallingPackageAllowedHidden();
            Bundle bundle = new Bundle();
            bundle.putInt("android:query-arg-match-pending", 1);
            bundle.putInt("android:query-arg-match-trashed", 1);
            SQLiteQueryBuilder queryBuilder = this.getQueryBuilder(3, this.matchUri(contentUriForPath, zIsCallingPackageAllowedHidden), contentUriForPath, bundle, null);
            long idIfPathOwnedByPackages = this.getIdIfPathOwnedByPackages(queryBuilder, databaseHelper, asString, this.getSharedPackages());
            if (idIfPathOwnedByPackages != -1 && queryBuilder.delete(databaseHelper, "_id=?", new String[]{Long.toString(idIfPathOwnedByPackages)}) == 1) {
                Log.i(Logging.TAG, "Retrying database update after deleting conflicting entry");
                return Integer.valueOf(sQLiteQueryBuilder.update(databaseHelper, contentValues, str, strArr));
            }
            throw e;
        }
    }

    private void resolvePlaylistMembers(final Uri uri) {
        Trace.beginSection("resolvePlaylistMembers");
        try {
            try {
                getDatabaseForUri(uri).runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$QPdozQYRlYbFN5tCtGS17MQLZ_I
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return this.f$0.lambda$resolvePlaylistMembers$26$MediaProvider(uri, (SQLiteDatabase) obj);
                    }
                });
            } catch (VolumeNotFoundException e) {
                throw e.rethrowAsIllegalArgumentException();
            }
        } finally {
            Trace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$resolvePlaylistMembers$26, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Object lambda$resolvePlaylistMembers$26$MediaProvider(Uri uri, SQLiteDatabase sQLiteDatabase) {
        resolvePlaylistMembersInternal(uri, sQLiteDatabase);
        return null;
    }

    private void resolvePlaylistMembersInternal(Uri uri, SQLiteDatabase sQLiteDatabase) {
        try {
            String volumeName = MediaStore.getVolumeName(uri);
            long id = ContentUris.parseId(uri);
            sQLiteDatabase.delete("audio_playlists_map", "playlist_id=" + id, null);
            Path path = queryForDataFile(uri, null).toPath();
            Playlist playlist = new Playlist();
            playlist.read(path.toFile());
            List<Path> listAsList = playlist.asList();
            for (int i = 0; i < listAsList.size(); i++) {
                try {
                    long jQueryForPlaylistMember = queryForPlaylistMember(volumeName, path.getParent().resolve(listAsList.get(i)));
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("play_order", Integer.valueOf(i + 1));
                    contentValues.put("playlist_id", Long.valueOf(id));
                    contentValues.put("audio_id", Long.valueOf(jQueryForPlaylistMember));
                    sQLiteDatabase.insert("audio_playlists_map", null, contentValues);
                } catch (IOException e) {
                    Log.w(Logging.TAG, "Failed to resolve playlist member", e);
                }
            }
        } catch (IOException e2) {
            Log.w(Logging.TAG, "Failed to refresh playlist", e2);
        }
    }

    private long queryForPlaylistMember(String str, Path path) throws IOException {
        Uri contentUri = MediaStore.Audio.Media.getContentUri(str);
        try {
            Cursor cursorQueryForSingleItem = queryForSingleItem(contentUri, new String[]{"_id"}, "_data=?", new String[]{path.toFile().getCanonicalPath()}, null);
            try {
                long j = cursorQueryForSingleItem.getLong(0);
                if (cursorQueryForSingleItem != null) {
                    cursorQueryForSingleItem.close();
                }
                return j;
            } finally {
                if (cursorQueryForSingleItem != null) {
                    try {
                        try {
                        } catch (FileNotFoundException unused) {
                            throw new FileNotFoundException();
                        }
                    } catch (Throwable th) {
                    }
                }
            }
        } catch (FileNotFoundException unused2) {
            Cursor cursorQueryForSingleItem2 = queryForSingleItem(contentUri, new String[]{"_id"}, "_display_name=?", new String[]{path.toFile().getName()}, null);
            try {
                long j2 = cursorQueryForSingleItem2.getLong(0);
                if (cursorQueryForSingleItem2 != null) {
                    cursorQueryForSingleItem2.close();
                }
                return j2;
            } finally {
            }
        }
    }

    private long addPlaylistMembers(Uri uri, ContentValues contentValues) throws FallbackException {
        Uri contentUri = MediaStore.Audio.Media.getContentUri(MediaStore.getVolumeName(uri), contentValues.getAsLong("audio_id").longValue());
        Integer numValueOf = Integer.valueOf(contentValues.getAsInteger("play_order") != null ? r11.intValue() - 1 : Integer.MAX_VALUE);
        try {
            File fileQueryForDataFile = queryForDataFile(uri, null);
            File fileQueryForDataFile2 = queryForDataFile(contentUri, null);
            Playlist playlist = new Playlist();
            playlist.read(fileQueryForDataFile);
            Integer numValueOf2 = Integer.valueOf(playlist.add(numValueOf.intValue(), fileQueryForDataFile.toPath().getParent().relativize(fileQueryForDataFile2.toPath())));
            playlist.write(fileQueryForDataFile);
            resolvePlaylistMembers(uri);
            Cursor cursorQuery = query(MediaStore.Audio.Playlists.Members.getContentUri(MediaStore.getVolumeName(uri), ContentUris.parseId(uri)), new String[]{"_id"}, "play_order=" + (numValueOf2.intValue() + 1), null, null);
            try {
                cursorQuery.moveToFirst();
                long j = cursorQuery.getLong(0);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return j;
            } finally {
            }
        } catch (IOException e) {
            throw new FallbackException("Failed to update playlist", e, 30);
        }
    }

    private int movePlaylistMembers(Uri uri, ContentValues contentValues, Bundle bundle) throws FallbackException {
        int iResolvePlaylistIndex = resolvePlaylistIndex(uri, bundle);
        int iIntValue = contentValues.getAsInteger("play_order").intValue() - 1;
        if (iResolvePlaylistIndex == -1) {
            throw new FallbackException("Failed to resolve playlist member " + bundle, 30);
        }
        try {
            File fileQueryForDataFile = queryForDataFile(uri, null);
            Playlist playlist = new Playlist();
            playlist.read(fileQueryForDataFile);
            int iMove = playlist.move(iResolvePlaylistIndex, iIntValue);
            playlist.write(fileQueryForDataFile);
            resolvePlaylistMembers(uri);
            return iMove;
        } catch (IOException e) {
            throw new FallbackException("Failed to update playlist", e, 30);
        }
    }

    private int removePlaylistMembers(Uri uri, Bundle bundle) throws FallbackException {
        int size;
        int iResolvePlaylistIndex = resolvePlaylistIndex(uri, bundle);
        try {
            File fileQueryForDataFile = queryForDataFile(uri, null);
            Playlist playlist = new Playlist();
            playlist.read(fileQueryForDataFile);
            if (iResolvePlaylistIndex == -1) {
                size = playlist.asList().size();
                playlist.clear();
            } else {
                playlist.remove(iResolvePlaylistIndex);
                size = 1;
            }
            playlist.write(fileQueryForDataFile);
            resolvePlaylistMembers(uri);
            return size;
        } catch (IOException e) {
            throw new FallbackException("Failed to update playlist", e, 30);
        }
    }

    private int resolvePlaylistIndex(Uri uri, Bundle bundle) {
        Uri contentUri = MediaStore.Audio.Playlists.Members.getContentUri(MediaStore.getVolumeName(uri), ContentUris.parseId(uri));
        try {
            Cursor cursorQuery = getQueryBuilder(3, 112, contentUri, bundle, null).query(getDatabaseForUri(contentUri), new String[]{"play_order"}, bundle, null);
            try {
                if (cursorQuery.getCount() != 1 || !cursorQuery.moveToFirst()) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return -1;
                }
                int i = cursorQuery.getInt(0) - 1;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return i;
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
        } catch (VolumeNotFoundException unused) {
            return -1;
        }
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        return openFileCommon(uri, str, null);
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str, CancellationSignal cancellationSignal) throws FileNotFoundException {
        return openFileCommon(uri, str, cancellationSignal);
    }

    private ParcelFileDescriptor openFileCommon(Uri uri, String str, CancellationSignal cancellationSignal) throws FileNotFoundException {
        Uri uriSafeUncanonicalize = safeUncanonicalize(uri);
        int iMatchUri = matchUri(uriSafeUncanonicalize, isCallingPackageAllowedHidden());
        String volumeName = MediaStore.getVolumeName(uriSafeUncanonicalize);
        if (iMatchUri == 3) {
            return ensureThumbnail(ContentUris.withAppendedId(MediaStore.Images.Media.getContentUri(volumeName), Long.parseLong(uriSafeUncanonicalize.getPathSegments().get(3))), cancellationSignal);
        }
        if (iMatchUri == VIDEO_MEDIA_ID_THUMBNAIL) {
            return ensureThumbnail(ContentUris.withAppendedId(MediaStore.Video.Media.getContentUri(volumeName), Long.parseLong(uriSafeUncanonicalize.getPathSegments().get(3))), cancellationSignal);
        }
        if (iMatchUri == 120) {
            return ensureThumbnail(ContentUris.withAppendedId(MediaStore.Audio.Albums.getContentUri(volumeName), Long.parseLong(uriSafeUncanonicalize.getPathSegments().get(3))), cancellationSignal);
        }
        if (iMatchUri == 121) {
            return ensureThumbnail(ContentUris.withAppendedId(MediaStore.Audio.Media.getContentUri(volumeName), Long.parseLong(uriSafeUncanonicalize.getPathSegments().get(3))), cancellationSignal);
        }
        return openFileAndEnforcePathPermissionsHelper(uriSafeUncanonicalize, iMatchUri, str, cancellationSignal);
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openTypedAssetFile(Uri uri, String str, Bundle bundle) throws FileNotFoundException {
        return openTypedAssetFileCommon(uri, str, bundle, null);
    }

    @Override // android.content.ContentProvider
    public AssetFileDescriptor openTypedAssetFile(Uri uri, String str, Bundle bundle, CancellationSignal cancellationSignal) throws FileNotFoundException {
        return openTypedAssetFileCommon(uri, str, bundle, cancellationSignal);
    }

    private AssetFileDescriptor openTypedAssetFileCommon(Uri uri, String str, Bundle bundle, CancellationSignal cancellationSignal) throws FileNotFoundException {
        Uri uriSafeUncanonicalize = safeUncanonicalize(uri);
        if (bundle != null && bundle.containsKey("android.content.extra.SIZE") && MimeUtils.startsWithIgnoreCase(str, "image/")) {
            return new AssetFileDescriptor(ensureThumbnail(uriSafeUncanonicalize, cancellationSignal), 0L, -1L);
        }
        return new AssetFileDescriptor(openFileCommon(uriSafeUncanonicalize, "r", cancellationSignal), 0L, -1L);
    }

    private ParcelFileDescriptor ensureThumbnail(Uri uri, CancellationSignal cancellationSignal) throws FileNotFoundException {
        ParcelFileDescriptor parcelFileDescriptorEnsureThumbnail;
        int iMatchUri = matchUri(uri, isCallingPackageAllowedHidden());
        Trace.beginSection("ensureThumbnail");
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
        try {
            try {
                if (iMatchUri == 2) {
                    parcelFileDescriptorEnsureThumbnail = this.mImageThumbnailer.ensureThumbnail(uri, cancellationSignal);
                } else if (iMatchUri == 101) {
                    parcelFileDescriptorEnsureThumbnail = this.mAudioThumbnailer.ensureThumbnail(uri, cancellationSignal);
                } else if (iMatchUri == 117) {
                    Uri contentUri = MediaStore.Audio.Media.getContentUri(MediaStore.getVolumeName(uri));
                    Cursor cursorQuery = query(contentUri, new String[]{"_id"}, "album_id=" + ContentUris.parseId(uri), null, null, cancellationSignal);
                    try {
                        if (cursorQuery.moveToFirst()) {
                            parcelFileDescriptorEnsureThumbnail = this.mAudioThumbnailer.ensureThumbnail(ContentUris.withAppendedId(contentUri, cursorQuery.getLong(0)), cancellationSignal);
                            if (cursorQuery != null) {
                                cursorQuery.close();
                            }
                        } else {
                            throw new FileNotFoundException("No media for album " + uri);
                        }
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
                } else if (iMatchUri == VIDEO_MEDIA_ID) {
                    parcelFileDescriptorEnsureThumbnail = this.mVideoThumbnailer.ensureThumbnail(uri, cancellationSignal);
                } else if (iMatchUri == FILES_ID || iMatchUri == DOWNLOADS_ID) {
                    int iResolveMediaType = MimeUtils.resolveMediaType(getType(uri));
                    if (iResolveMediaType == 1) {
                        parcelFileDescriptorEnsureThumbnail = this.mImageThumbnailer.ensureThumbnail(uri, cancellationSignal);
                    } else if (iResolveMediaType == 2) {
                        parcelFileDescriptorEnsureThumbnail = this.mAudioThumbnailer.ensureThumbnail(uri, cancellationSignal);
                    } else if (iResolveMediaType == 3) {
                        parcelFileDescriptorEnsureThumbnail = this.mVideoThumbnailer.ensureThumbnail(uri, cancellationSignal);
                    } else {
                        throw new FileNotFoundException();
                    }
                } else {
                    throw new FileNotFoundException();
                }
                return parcelFileDescriptorEnsureThumbnail;
            } catch (IOException e) {
                Log.w(Logging.TAG, e);
                throw new FileNotFoundException(e.getMessage());
            }
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
            Trace.endSection();
        }
    }

    private void updateImageMetadata(ContentValues contentValues, File file) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), options);
        contentValues.put("width", Integer.valueOf(options.outWidth));
        contentValues.put("height", Integer.valueOf(options.outHeight));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleInsertedRowForFuse(long j) {
        if (isFuseThread()) {
            this.mCallingIdentity.get().removeDeletedRowId(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUpdatedRowForFuse(String str, String str2, long j, long j2) {
        if (j == j2) {
            return;
        }
        handleDeletedRowForFuse(str, str2, j);
        handleInsertedRowForFuse(j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDeletedRowForFuse(String str, String str2, long j) {
        if (isFuseThread()) {
            if (!str2.equals("null") && !str2.equals(getCallingPackageOrSelf())) {
                invalidateLocalCallingIdentityCache(str2, "owned_database_row_deleted:" + str);
            }
            this.mCallingIdentity.get().addDeletedRowId(str, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOwnerPackageNameChange(String str, String str2, String str3) {
        if (Objects.equals(str2, str3)) {
            return;
        }
        invalidateLocalCallingIdentityCache(str2, "owner_package_changed:" + str);
    }

    File queryForDataFile(Uri uri, CancellationSignal cancellationSignal) throws FileNotFoundException {
        return queryForDataFile(uri, null, null, cancellationSignal);
    }

    File queryForDataFile(Uri uri, String str, String[] strArr, CancellationSignal cancellationSignal) throws FileNotFoundException {
        Cursor cursorQueryForSingleItem = queryForSingleItem(uri, new String[]{"_data"}, str, strArr, cancellationSignal);
        try {
            String string = cursorQueryForSingleItem.getString(0);
            if (TextUtils.isEmpty(string)) {
                throw new FileNotFoundException("Missing path for " + uri);
            }
            File file = new File(string);
            if (cursorQueryForSingleItem != null) {
                cursorQueryForSingleItem.close();
            }
            return file;
        } catch (Throwable th) {
            if (cursorQueryForSingleItem != null) {
                try {
                    cursorQueryForSingleItem.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    Uri queryForMediaUri(File file, CancellationSignal cancellationSignal) throws FileNotFoundException {
        Uri contentUri = MediaStore.Files.getContentUri(FileUtils.getVolumeName(getContext(), file));
        Cursor cursorQueryForSingleItem = queryForSingleItem(contentUri, new String[]{"_id"}, "_data=?", new String[]{file.getAbsolutePath()}, cancellationSignal);
        try {
            Uri uriWithAppendedId = ContentUris.withAppendedId(contentUri, cursorQueryForSingleItem.getLong(0));
            if (cursorQueryForSingleItem != null) {
                cursorQueryForSingleItem.close();
            }
            return uriWithAppendedId;
        } catch (Throwable th) {
            if (cursorQueryForSingleItem != null) {
                try {
                    cursorQueryForSingleItem.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    Cursor queryForSingleItem(Uri uri, String[] strArr, String str, String[] strArr2, CancellationSignal cancellationSignal) throws FileNotFoundException {
        Cursor cursorQuery = query(uri, strArr, DatabaseUtils.createSqlQueryBundle(str, strArr2, null), cancellationSignal);
        if (cursorQuery == null) {
            throw new FileNotFoundException("Missing cursor for " + uri);
        }
        if (cursorQuery.getCount() < 1) {
            FileUtils.closeQuietly(cursorQuery);
            throw new FileNotFoundException("No item at " + uri);
        }
        if (cursorQuery.getCount() > 1) {
            FileUtils.closeQuietly(cursorQuery);
            throw new FileNotFoundException("Multiple items at " + uri);
        }
        if (cursorQuery.moveToFirst()) {
            return cursorQuery;
        }
        FileUtils.closeQuietly(cursorQuery);
        throw new FileNotFoundException("Failed to read row from " + uri);
    }

    private void requireOwnershipForItem(String str, Uri uri) {
        boolean z = str != null;
        boolean zEquals = Objects.equals(getCallingPackageOrSelf(), str);
        if (!z || zEquals) {
            return;
        }
        throw new IllegalStateException("Only owner is able to interact with pending item " + uri);
    }

    private File getFuseFile(File file) {
        return new File(file.getPath().replaceFirst("/storage/", "/mnt/user/" + UserHandle.myUserId() + "/"));
    }

    private FuseDaemon getFuseDaemonForFile(File file) throws FileNotFoundException {
        FuseDaemon fuseDaemon = ExternalStorageServiceImpl.getFuseDaemon(getVolumeId(file));
        if (fuseDaemon != null) {
            return fuseDaemon;
        }
        throw new FileNotFoundException("Missing FUSE daemon for " + file);
    }

    private void invalidateFuseDentry(File file) {
        invalidateFuseDentry(file.getAbsolutePath());
    }

    private void invalidateFuseDentry(String str) {
        try {
            FuseDaemon fuseDaemonForFile = getFuseDaemonForFile(new File(str));
            if (isFuseThread()) {
                return;
            }
            fuseDaemonForFile.invalidateFuseDentryCache(str);
        } catch (FileNotFoundException e) {
            Log.w(Logging.TAG, "Failed to invalidate FUSE dentry", e);
        }
    }

    private ParcelFileDescriptor openFileAndEnforcePathPermissionsHelper(final Uri uri, final int i, String str, CancellationSignal cancellationSignal) throws FileNotFoundException {
        RedactionInfo redactionInfo;
        ParcelFileDescriptor parcelFileDescriptorOpenSafely;
        int mode = ParcelFileDescriptor.parseMode(str);
        boolean z = true;
        boolean z2 = (536870912 & mode) != 0;
        if (z2) {
            mode |= 805306368;
        }
        boolean zHasOwnerPackageName = hasOwnerPackageName(uri);
        String[] strArr = new String[3];
        strArr[0] = "_data";
        strArr[1] = zHasOwnerPackageName ? "owner_package_name" : "NULL";
        strArr[2] = zHasOwnerPackageName ? "is_pending" : "0";
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
        try {
            try {
                Cursor cursorQueryForSingleItem = queryForSingleItem(uri, strArr, null, null, cancellationSignal);
                try {
                    String string = cursorQueryForSingleItem.getString(0);
                    if (TextUtils.isEmpty(string)) {
                        throw new FileNotFoundException("Missing path for " + uri);
                    }
                    final File canonicalFile = new File(string).getCanonicalFile();
                    String string2 = cursorQueryForSingleItem.getString(1);
                    boolean z3 = cursorQueryForSingleItem.getInt(2) != 0;
                    if (cursorQueryForSingleItem != null) {
                        cursorQueryForSingleItem.close();
                    }
                    restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
                    checkAccess(uri, Bundle.EMPTY, canonicalFile, z2);
                    if (z3 && !isPendingFromFuse(canonicalFile)) {
                        requireOwnershipForItem(string2, uri);
                    }
                    boolean zIsRedactionNeeded = Objects.equals(getCallingPackageOrSelf(), string2) ? false : isRedactionNeeded(uri);
                    try {
                        if (zIsRedactionNeeded) {
                            redactionInfo = getRedactionRanges(canonicalFile);
                        } else {
                            redactionInfo = new RedactionInfo(new long[0], new long[0]);
                        }
                        if (zIsRedactionNeeded && MediaStore.getRequireOriginal(uri)) {
                            throw new UnsupportedOperationException("Caller must hold ACCESS_MEDIA_LOCATION permission to access original");
                        }
                        ParcelFileDescriptor.OnCloseListener onCloseListener = new ParcelFileDescriptor.OnCloseListener() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$bWH2tOJWh67AvqWqCtlHKh4pMdA
                            @Override // android.os.ParcelFileDescriptor.OnCloseListener
                            public final void onClose(IOException iOException) {
                                this.f$0.lambda$openFileAndEnforcePathPermissionsHelper$27$MediaProvider(uri, canonicalFile, i, iOException);
                            }
                        };
                        try {
                            String path = canonicalFile.getPath();
                            if (redactionInfo.redactionRanges.length > 0) {
                                if (SystemProperties.getBoolean(PROP_FUSE, false)) {
                                    Log.i(Logging.TAG, "Redacting with new FUSE for " + path);
                                    long jMyTid = (long) Process.myTid();
                                    synchronized (this.mShouldRedactThreadIds) {
                                        this.mShouldRedactThreadIds.add(jMyTid);
                                    }
                                    try {
                                        parcelFileDescriptorOpenSafely = FileUtils.openSafely(getFuseFile(canonicalFile), mode);
                                        synchronized (this.mShouldRedactThreadIds) {
                                            this.mShouldRedactThreadIds.remove(this.mShouldRedactThreadIds.indexOf(jMyTid));
                                        }
                                    } catch (Throwable th) {
                                        synchronized (this.mShouldRedactThreadIds) {
                                            this.mShouldRedactThreadIds.remove(this.mShouldRedactThreadIds.indexOf(jMyTid));
                                            throw th;
                                        }
                                    }
                                } else {
                                    Log.i(Logging.TAG, "Redacting with old FUSE for " + path);
                                    parcelFileDescriptorOpenSafely = RedactingFileDescriptor.open(getContext(), canonicalFile, mode, redactionInfo.redactionRanges, redactionInfo.freeOffsets);
                                }
                            } else {
                                FuseDaemon fuseDaemonForFile = null;
                                try {
                                    fuseDaemonForFile = getFuseDaemonForFile(canonicalFile);
                                } catch (FileNotFoundException unused) {
                                }
                                ParcelFileDescriptor parcelFileDescriptorOpenSafely2 = FileUtils.openSafely(canonicalFile, mode);
                                if (fuseDaemonForFile == null || !fuseDaemonForFile.shouldOpenWithFuse(path, true, parcelFileDescriptorOpenSafely2.getFd())) {
                                    z = false;
                                }
                                if (SystemProperties.getBoolean(PROP_FUSE, false) && z) {
                                    Log.w(Logging.TAG, "Using FUSE for " + path);
                                    parcelFileDescriptorOpenSafely = FileUtils.openSafely(getFuseFile(canonicalFile), mode);
                                    try {
                                        parcelFileDescriptorOpenSafely2.close();
                                    } catch (IOException e) {
                                        Log.w(Logging.TAG, "Failed to close lower filesystem fd " + canonicalFile.getPath(), e);
                                    }
                                } else {
                                    Log.i(Logging.TAG, "Using lower FS for " + path);
                                    if (z2) {
                                        invalidateFuseDentry(canonicalFile);
                                    }
                                    parcelFileDescriptorOpenSafely = parcelFileDescriptorOpenSafely2;
                                }
                            }
                            return (z3 || !z2) ? parcelFileDescriptorOpenSafely : ParcelFileDescriptor.wrap(parcelFileDescriptorOpenSafely, BackgroundThread.getHandler(), onCloseListener);
                        } catch (IOException e2) {
                            if (e2 instanceof FileNotFoundException) {
                                throw ((FileNotFoundException) e2);
                            }
                            throw new IllegalStateException(e2);
                        }
                    } catch (IOException e3) {
                        throw new IllegalStateException(e3);
                    }
                } catch (Throwable th2) {
                    if (cursorQueryForSingleItem != null) {
                        try {
                            cursorQueryForSingleItem.close();
                        } catch (Throwable th3) {
                            th2.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            } catch (IOException e4) {
                throw new FileNotFoundException(e4.toString());
            }
        } catch (Throwable th4) {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
            throw th4;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$openFileAndEnforcePathPermissionsHelper$27, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$openFileAndEnforcePathPermissionsHelper$27$MediaProvider(Uri uri, File file, int i, IOException iOException) {
        lambda$updateInternal$23(uri);
        invalidateFuseDentry(file);
        try {
            if (i == 5 || i == VIDEO_THUMBNAILS_ID) {
                ContentValues contentValues = new ContentValues();
                updateImageMetadata(contentValues, file);
                update(uri, contentValues, null, null);
            } else {
                this.mMediaScanner.scanFile(file, 2);
            }
        } catch (Exception e) {
            Log.w(Logging.TAG, "Failed to update metadata for " + uri, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteAndInvalidate(Path path) {
        deleteAndInvalidate(path.toFile());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteAndInvalidate(File file) {
        file.delete();
        invalidateFuseDentry(file);
    }

    private void deleteIfAllowed(Uri uri, Bundle bundle, String str) {
        try {
            File file = new File(str);
            checkAccess(uri, bundle, file, true);
            deleteAndInvalidate(file);
        } catch (Exception e) {
            Log.e(Logging.TAG, "Couldn't delete " + str, e);
        }
    }

    @Deprecated
    private boolean isPending(Uri uri) {
        int iMatchUri = matchUri(uri, true);
        if (iMatchUri != 2 && iMatchUri != 101 && iMatchUri != VIDEO_MEDIA_ID) {
            return false;
        }
        try {
            Cursor cursorQueryForSingleItem = queryForSingleItem(uri, new String[]{"is_pending"}, null, null, null);
            try {
                boolean z = cursorQueryForSingleItem.getInt(0) != 0;
                if (cursorQueryForSingleItem != null) {
                    cursorQueryForSingleItem.close();
                }
                return z;
            } finally {
            }
        } catch (FileNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }

    @Deprecated
    private boolean isRedactionNeeded(Uri uri) {
        return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_IS_REDACTION_NEEDED);
    }

    private boolean isRedactionNeeded() {
        return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_IS_REDACTION_NEEDED);
    }

    private boolean isCallingPackageRequestingLegacy() {
        return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_IS_LEGACY_GRANTED);
    }

    private static int getFileMediaType(String str) {
        return MimeUtils.resolveMediaType(MimeUtils.resolveMimeType(new File(str)));
    }

    private boolean canAccessMediaFile(String str, boolean z) {
        if (!z && isCallingPackageRequestingLegacy()) {
            return false;
        }
        int fileMediaType = getFileMediaType(str);
        if (fileMediaType == 1) {
            return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_WRITE_IMAGES);
        }
        if (fileMediaType != 3) {
            return false;
        }
        return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_WRITE_VIDEO);
    }

    private boolean shouldBypassFuseRestrictions(boolean z, String str) {
        boolean zIsCallingPackageLegacyRead;
        if (z) {
            zIsCallingPackageLegacyRead = isCallingPackageLegacyWrite();
        } else {
            zIsCallingPackageLegacyRead = isCallingPackageLegacyRead();
        }
        if (zIsCallingPackageLegacyRead || isCallingPackageManager()) {
            return true;
        }
        String strExtractPathOwnerPackageName = FileUtils.extractPathOwnerPackageName(str);
        return (strExtractPathOwnerPackageName != null && isCallingIdentitySharedPackageName(strExtractPathOwnerPackageName)) || canAccessMediaFile(str, true);
    }

    private boolean isPrivatePackagePathNotOwnedByCaller(String str) {
        if (FileUtils.extractPathOwnerPackageName(str) == null || FileUtils.extractRelativePath(str).startsWith("Android/media")) {
            return false;
        }
        return !isCallingIdentitySharedPackageName(r0);
    }

    private boolean shouldBypassDatabaseForFuse(int i) {
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(getCachedCallingIdentityForFuse(i));
        if (i != 2000) {
            try {
                if (isCallingPackageManager()) {
                    return true;
                }
            } finally {
                restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
            }
        }
        if (isCallingPackageLegacyWrite()) {
            if (isCallingPackageSystemGallery()) {
                return true;
            }
        }
        return false;
    }

    private static final class RedactionInfo {
        public final long[] freeOffsets;
        public final long[] redactionRanges;

        public RedactionInfo(long[] jArr, long[] jArr2) {
            this.redactionRanges = jArr;
            this.freeOffsets = jArr2;
        }
    }

    @Keep
    public long[] getRedactionRangesForFuse(String str, int i, int i2) throws IOException {
        File file = new File(str);
        boolean z = true;
        if (i == Process.myUid()) {
            synchronized (this.mShouldRedactThreadIds) {
                if (this.mShouldRedactThreadIds.indexOf(i2) == -1) {
                    z = false;
                }
            }
            return z ? getRedactionRanges(file).redactionRanges : new long[0];
        }
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(getCachedCallingIdentityForFuse(i));
        long[] jArr = new long[0];
        try {
            if (isRedactionNeeded() && !shouldBypassFuseRestrictions(false, str)) {
                Uri contentUriForPath = FileUtils.getContentUriForPath(str);
                try {
                    Cursor cursorQueryForSingleItem = queryForSingleItem(contentUriForPath, new String[]{"owner_package_name", "_id"}, "_data=?", new String[]{str}, null);
                    try {
                        cursorQueryForSingleItem.moveToFirst();
                        String string = cursorQueryForSingleItem.getString(0);
                        Uri uriWithAppendedId = ContentUris.withAppendedId(contentUriForPath, cursorQueryForSingleItem.getInt(1));
                        if (cursorQueryForSingleItem != null) {
                            cursorQueryForSingleItem.close();
                        }
                        boolean zEquals = Objects.equals(getCallingPackageOrSelf(), string);
                        if (getContext().checkUriPermission(uriWithAppendedId, this.mCallingIdentity.get().pid, this.mCallingIdentity.get().uid, 2) != 0) {
                            z = false;
                        }
                        if (!zEquals && !z) {
                            jArr = getRedactionRanges(file).redactionRanges;
                        }
                        return jArr;
                    } catch (Throwable th) {
                        if (cursorQueryForSingleItem != null) {
                            try {
                                cursorQueryForSingleItem.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e) {
                    throw new FileNotFoundException(str + " not found while calculating redaction ranges: " + e.getMessage());
                }
            }
            return jArr;
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    public static RedactionInfo getRedactionRanges(File file) throws IOException {
        Trace.beginSection("getRedactionRanges");
        LongArray longArray = new LongArray();
        LongArray longArray2 = new LongArray();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                String strResolveMimeType = MimeUtils.resolveMimeType(file);
                if (ExifInterface.isSupportedMimeType(strResolveMimeType)) {
                    ExifInterface exifInterface = new ExifInterface(fileInputStream.getFD());
                    for (String str : REDACTED_EXIF_TAGS) {
                        long[] attributeRange = exifInterface.getAttributeRange(str);
                        if (attributeRange != null) {
                            longArray.add(attributeRange[0]);
                            longArray.add(attributeRange[0] + attributeRange[1]);
                        }
                    }
                    longArray.addAll(XmpInterface.fromContainer(exifInterface).getRedactionRanges());
                }
                if (IsoInterface.isSupportedMimeType(strResolveMimeType)) {
                    IsoInterface isoInterfaceFromFileDescriptor = IsoInterface.fromFileDescriptor(fileInputStream.getFD());
                    for (int i : REDACTED_ISO_BOXES) {
                        long[] boxRanges = isoInterfaceFromFileDescriptor.getBoxRanges(i);
                        for (int i2 = 0; i2 < boxRanges.length; i2 += 2) {
                            long j = boxRanges[i2] - 4;
                            longArray2.add(j);
                            longArray.add(j);
                            longArray.add(boxRanges[i2 + 1]);
                        }
                    }
                    longArray.addAll(XmpInterface.fromContainer(isoInterfaceFromFileDescriptor).getRedactionRanges());
                }
                fileInputStream.close();
            } finally {
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException e) {
            throw new IOException("Failed to redact " + file, e);
        }
        Trace.endSection();
        return new RedactionInfo(longArray.toArray(), longArray2.toArray());
    }

    private static boolean isPendingFromFuse(File file) {
        return !FileUtils.PATTERN_EXPIRES_FILE.matcher(FileUtils.extractDisplayName(file.getName())).matches();
    }

    @Keep
    public int isOpenAllowedForFuse(String str, int i, boolean z) {
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(getCachedCallingIdentityForFuse(i));
        try {
            if (isPrivatePackagePathNotOwnedByCaller(str)) {
                Log.e(Logging.TAG, "Can't open a file in another app's external directory!");
                return OsConstants.ENOENT;
            }
            if (shouldBypassFuseRestrictions(z, str)) {
                return 0;
            }
            if (isCallingPackageRequestingLegacy()) {
                return OsConstants.EACCES;
            }
            Uri contentUriForPath = FileUtils.getContentUriForPath(str);
            Cursor cursorQueryForSingleItem = queryForSingleItem(contentUriForPath, new String[]{"_id", "owner_package_name", "is_pending"}, "_data=?", new String[]{str}, null);
            try {
                Uri uriWithAppendedId = ContentUris.withAppendedId(contentUriForPath, cursorQueryForSingleItem.getInt(0));
                String string = cursorQueryForSingleItem.getString(1);
                boolean z2 = cursorQueryForSingleItem.getInt(2) != 0;
                if (cursorQueryForSingleItem != null) {
                    cursorQueryForSingleItem.close();
                }
                checkAccess(uriWithAppendedId, Bundle.EMPTY, new File(str), z);
                if (z2 && !isPendingFromFuse(new File(str))) {
                    requireOwnershipForItem(string, uriWithAppendedId);
                }
                return 0;
            } catch (Throwable th) {
                if (cursorQueryForSingleItem != null) {
                    try {
                        cursorQueryForSingleItem.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (FileNotFoundException unused) {
            Log.e(Logging.TAG, "Couldn't find file: " + str);
            return OsConstants.EACCES;
        } catch (IllegalStateException | SecurityException unused2) {
            Log.e(Logging.TAG, "Permission to access file: " + str + " is denied");
            return OsConstants.EACCES;
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    private boolean isCallingIdentitySharedPackageName(String str) {
        for (String str2 : this.mCallingIdentity.get().getSharedPackageNames()) {
            if (str.toLowerCase(Locale.ROOT).equals(str2.toLowerCase(Locale.ROOT))) {
                return true;
            }
        }
        return false;
    }

    private Uri getContentUriForFile(String str, String str2) {
        String volumeName = FileUtils.getVolumeName(getContext(), new File(str));
        Uri contentUri = MediaStore.Files.getContentUri(volumeName);
        String strExtractTopLevelDir = FileUtils.extractTopLevelDir(str);
        if (strExtractTopLevelDir == null) {
            return contentUri;
        }
        switch (strExtractTopLevelDir) {
            case "Podcasts":
            case "Ringtones":
            case "Alarms":
            case "Notifications":
            case "Audiobooks":
                return MediaStore.Audio.Media.getContentUri(volumeName);
            case "Music":
                if (MimeUtils.isPlaylistMimeType(str2)) {
                    return MediaStore.Audio.Playlists.getContentUri(volumeName);
                }
                return !MimeUtils.isSubtitleMimeType(str2) ? MediaStore.Audio.Media.getContentUri(volumeName) : contentUri;
            case "Movies":
                if (MimeUtils.isPlaylistMimeType(str2)) {
                    return MediaStore.Audio.Playlists.getContentUri(volumeName);
                }
                return !MimeUtils.isSubtitleMimeType(str2) ? MediaStore.Video.Media.getContentUri(volumeName) : contentUri;
            case "DCIM":
            case "Pictures":
                if (MimeUtils.isImageMimeType(str2)) {
                    return MediaStore.Images.Media.getContentUri(volumeName);
                }
                return MediaStore.Video.Media.getContentUri(volumeName);
            case "Download":
            case "Documents":
                return contentUri;
            default:
                Log.w(Logging.TAG, "Forgot to handle a top level directory in getContentUriForFile?");
                return contentUri;
        }
    }

    private boolean fileExists(String str) {
        String[] strArr = new String[0];
        String[] strArr2 = {str};
        Uri contentUriForPath = FileUtils.getContentUriForPath(str);
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity();
        try {
            Cursor cursorQuery = query(contentUriForPath, strArr, "_data = ?", strArr2, null);
            try {
                boolean z = cursorQuery.getCount() > 0;
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return z;
            } finally {
            }
        } finally {
            clearLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    private boolean isExternalMediaDirectory(String str) {
        String strExtractRelativePath = FileUtils.extractRelativePath(str);
        if (strExtractRelativePath != null) {
            return strExtractRelativePath.startsWith("Android/media");
        }
        return false;
    }

    private Uri insertFileForFuse(String str, Uri uri, String str2, boolean z) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("owner_package_name", getCallingPackageOrSelf());
        contentValues.put("mime_type", str2);
        contentValues.put("is_pending", (Integer) 1);
        if (z) {
            contentValues.put("_data", str);
        } else {
            contentValues.put("volume_name", FileUtils.extractVolumeName(str));
            contentValues.put("relative_path", FileUtils.extractRelativePath(str));
            contentValues.put("_display_name", FileUtils.extractDisplayName(str));
        }
        return insert(uri, contentValues, Bundle.EMPTY);
    }

    @Keep
    public int insertFileIfNecessaryForFuse(String str, int i) {
        String callingPackageOrSelf;
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(getCachedCallingIdentityForFuse(i));
        try {
            if (isPrivatePackagePathNotOwnedByCaller(str)) {
                Log.e(Logging.TAG, "Can't create a file in another app's external directory");
                return OsConstants.ENOENT;
            }
            if (!str.equals(FileUtils.getAbsoluteSanitizedPath(str))) {
                Log.e(Logging.TAG, "File name contains invalid characters");
                return OsConstants.EPERM;
            }
            if (shouldBypassDatabaseForFuse(i)) {
                return 0;
            }
            String strResolveMimeType = MimeUtils.resolveMimeType(new File(str));
            if (!shouldBypassFuseRestrictions(true, str)) {
                if (isCallingPackageRequestingLegacy()) {
                    return OsConstants.EPERM;
                }
                if (fileExists(str)) {
                    return OsConstants.EEXIST;
                }
                if (insertFileForFuse(str, getContentUriForFile(str, strResolveMimeType), strResolveMimeType, false) == null) {
                    return OsConstants.EPERM;
                }
                return 0;
            }
            boolean zIsCallingPackageRequestingLegacy = isCallingPackageRequestingLegacy();
            if (!fileExists(str)) {
                try {
                    insertFileForFuse(str, FileUtils.getContentUriForPath(str), strResolveMimeType, zIsCallingPackageRequestingLegacy);
                } catch (Exception unused) {
                }
            } else if (zIsCallingPackageRequestingLegacy && (callingPackageOrSelf = getCallingPackageOrSelf()) != null && !updateOwnerForPath(str, callingPackageOrSelf)) {
                return OsConstants.EPERM;
            }
            return 0;
        } catch (IllegalArgumentException e) {
            Log.e(Logging.TAG, "insertFileIfNecessary failed", e);
            return OsConstants.EPERM;
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    private boolean updateOwnerForPath(final String str, String str2) {
        try {
            DatabaseHelper databaseForUri = getDatabaseForUri(FileUtils.getContentUriForPath(str));
            final ContentValues contentValues = new ContentValues(1);
            contentValues.put("owner_package_name", str2);
            return ((Integer) databaseForUri.runWithoutTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$wJJJOAUGRxIzUlIUidEILJuhAWk
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Integer.valueOf(((SQLiteDatabase) obj).update("files", contentValues, "_data=?", new String[]{str}));
                }
            })).intValue() == 1;
        } catch (VolumeNotFoundException e) {
            throw new AssertionError("Path must already be resolved", e);
        }
    }

    private static int deleteFileUnchecked(String str) {
        if (new File(str).delete()) {
            return 0;
        }
        return OsConstants.ENOENT;
    }

    @Keep
    public int deleteFileForFuse(String str, int i) throws IOException {
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(getCachedCallingIdentityForFuse(i));
        try {
            if (isPrivatePackagePathNotOwnedByCaller(str)) {
                Log.e(Logging.TAG, "Can't delete a file in another app's external directory!");
                return OsConstants.ENOENT;
            }
            if (shouldBypassDatabaseForFuse(i)) {
                return deleteFileUnchecked(str);
            }
            boolean zShouldBypassFuseRestrictions = shouldBypassFuseRestrictions(true, str);
            if (!zShouldBypassFuseRestrictions && isCallingPackageRequestingLegacy()) {
                return OsConstants.EPERM;
            }
            if (delete(FileUtils.getContentUriForPath(str), "_data = ?", new String[]{str}) == 0) {
                return zShouldBypassFuseRestrictions ? deleteFileUnchecked(str) : OsConstants.ENOENT;
            }
            return 0;
        } catch (SecurityException e) {
            Log.e(Logging.TAG, "File deletion not allowed", e);
            return OsConstants.EPERM;
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    @Keep
    public int isDirectoryCreationOrDeletionAllowedForFuse(String str, int i, boolean z) {
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(getCachedCallingIdentityForFuse(i));
        try {
            if (isPrivatePackagePathNotOwnedByCaller(str)) {
                Log.e(Logging.TAG, "Can't modify another app's external directory!");
                return OsConstants.EACCES;
            }
            boolean z2 = true;
            if (shouldBypassFuseRestrictions(true, str)) {
                return 0;
            }
            if (isCallingPackageRequestingLegacy()) {
                return OsConstants.EACCES;
            }
            String[] strArrSanitizePath = FileUtils.sanitizePath(FileUtils.extractRelativePath(str));
            if (strArrSanitizePath.length != 1 || !TextUtils.isEmpty(strArrSanitizePath[0])) {
                z2 = false;
            }
            if (!z2) {
                return 0;
            }
            if (z && FileUtils.isDefaultDirectoryName(FileUtils.extractDisplayName(str))) {
                return 0;
            }
            Log.e(Logging.TAG, "Creating a non-default top level directory or deleting an existing one is not allowed!");
            return OsConstants.EPERM;
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    @Keep
    public int isOpendirAllowedForFuse(String str, int i, boolean z) {
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(getCachedCallingIdentityForFuse(i));
        try {
            if ("/storage/emulated".equals(str)) {
                return OsConstants.EPERM;
            }
            if (isPrivatePackagePathNotOwnedByCaller(str)) {
                Log.e(Logging.TAG, "Can't access another app's external directory!");
                return OsConstants.ENOENT;
            }
            if (FileUtils.isDataOrObbPath(str)) {
                return OsConstants.EACCES;
            }
            if (shouldBypassFuseRestrictions(z, str)) {
                return 0;
            }
            if (isCallingPackageRequestingLegacy()) {
                return OsConstants.EACCES;
            }
            if (z) {
                String[] strArrSanitizePath = FileUtils.sanitizePath(FileUtils.extractRelativePath(str));
                if (strArrSanitizePath.length == 0) {
                    Log.e(Logging.TAG, "Directoy write not allowed on invalid relative path for " + str);
                    return OsConstants.EPERM;
                }
                boolean z2 = true;
                if (strArrSanitizePath.length != 1 || !TextUtils.isEmpty(strArrSanitizePath[0])) {
                    z2 = false;
                }
                if (z2) {
                    if (FileUtils.isDefaultDirectoryName(FileUtils.extractDisplayName(str))) {
                        return 0;
                    }
                    Log.e(Logging.TAG, "Writing to a non-default top level directory is not allowed!");
                    return OsConstants.EACCES;
                }
            }
            return 0;
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    @Keep
    public boolean isUidForPackageForFuse(String str, int i) {
        LocalCallingIdentity localCallingIdentityClearLocalCallingIdentity = clearLocalCallingIdentity(getCachedCallingIdentityForFuse(i));
        try {
            return isCallingIdentitySharedPackageName(str);
        } finally {
            restoreLocalCallingIdentity(localCallingIdentityClearLocalCallingIdentity);
        }
    }

    private boolean checkCallingPermissionGlobal(Uri uri, boolean z) {
        if (isCallingPackageSelf() || isCallingPackageShell() || isCallingPackageManager()) {
            return true;
        }
        int iMatchUri = matchUri(uri, true);
        if (iMatchUri == 2 || iMatchUri == 101 || iMatchUri == VIDEO_MEDIA_ID || iMatchUri == FILES_ID || iMatchUri == DOWNLOADS_ID) {
            if (this.mCallingIdentity.get().isOwned(ContentUris.parseId(uri))) {
                return true;
            }
        }
        if (iMatchUri != 1 && iMatchUri != 100 && iMatchUri != 106 && iMatchUri != 110 && iMatchUri != 114 && iMatchUri != 116 && iMatchUri != VIDEO_MEDIA && iMatchUri != FILES && iMatchUri != DOWNLOADS) {
            if (getContext().checkUriPermission(uri, this.mCallingIdentity.get().pid, this.mCallingIdentity.get().uid, z ? 2 : 1) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean isFuseThread() {
        return FuseDaemon.native_is_fuse_thread();
    }

    @Deprecated
    private boolean checkCallingPermissionAudio(boolean z, String str) {
        if (z) {
            return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_WRITE_AUDIO);
        }
        return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_READ_AUDIO) || this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_WRITE_AUDIO);
    }

    @Deprecated
    private boolean checkCallingPermissionVideo(boolean z, String str) {
        if (z) {
            return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_WRITE_VIDEO);
        }
        return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_READ_VIDEO) || this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_WRITE_VIDEO);
    }

    @Deprecated
    private boolean checkCallingPermissionImages(boolean z, String str) {
        if (z) {
            return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_WRITE_IMAGES);
        }
        return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_READ_IMAGES) || this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_WRITE_IMAGES);
    }

    private void enforceCallingPermission(Uri uri, Bundle bundle, boolean z) {
        Trace.beginSection("enforceCallingPermission");
        try {
            enforceCallingPermissionInternal(uri, bundle, z);
        } finally {
            Trace.endSection();
        }
    }

    private void enforceCallingPermissionInternal(Uri uri, Bundle bundle, boolean z) {
        int i;
        Cursor cursorQuery;
        Objects.requireNonNull(uri);
        Objects.requireNonNull(bundle);
        if (checkCallingPermissionGlobal(uri, z)) {
            return;
        }
        try {
            DatabaseHelper databaseForUri = getDatabaseForUri(uri);
            int iMatchUri = matchUri(uri, isCallingPackageAllowedHidden());
            if (z) {
                i = 0;
                cursorQuery = getQueryBuilder(2, iMatchUri, uri, bundle, null).query(databaseForUri, new String[0], (String) null, (String[]) null, (String) null, (String) null, (String) null, (String) null, (CancellationSignal) null);
                try {
                    if (cursorQuery.moveToFirst()) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                            return;
                        }
                        return;
                    } else if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                } finally {
                }
            } else {
                i = 0;
            }
            int iMatchUri2 = matchUri(uri, true);
            int i2 = (iMatchUri2 == 2 || iMatchUri2 == 101 || iMatchUri2 == VIDEO_MEDIA_ID) ? 1 : i;
            int i3 = i;
            cursorQuery = getQueryBuilder(0, iMatchUri, uri, bundle, null).query(databaseForUri, new String[i3], (String) null, (String[]) null, (String) null, (String) null, (String) null, (String) null, (CancellationSignal) null);
            try {
                if (cursorQuery.moveToFirst()) {
                    if (!z) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                            return;
                        }
                        return;
                    } else if (i2 != 0) {
                        Context context = getContext();
                        Uri[] uriArr = new Uri[1];
                        uriArr[i3] = uri;
                        throw new RecoverableSecurityException(new SecurityException(getCallingPackageOrSelf() + " has no access to " + uri), context.getText(R.string.permission_required), new RemoteAction(getCollectionIcon(uri), context.getText(R.string.permission_required_action), context.getText(R.string.permission_required_action), MediaStore.createWriteRequest(ContentResolver.wrap(this), Arrays.asList(uriArr))));
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                throw new SecurityException(getCallingPackageOrSelf() + " has no access to " + uri);
            } finally {
            }
        } catch (VolumeNotFoundException e) {
            throw e.rethrowAsIllegalArgumentException();
        }
    }

    private Icon getCollectionIcon(Uri uri) {
        PackageManager packageManager = getContext().getPackageManager();
        uri.getPathSegments().get(1).hashCode();
        try {
            PermissionGroupInfo permissionGroupInfo = packageManager.getPermissionGroupInfo("android.permission-group.STORAGE", 0);
            return Icon.createWithResource(permissionGroupInfo.packageName, permissionGroupInfo.icon);
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkAccess(Uri uri, Bundle bundle, File file, boolean z) throws FileNotFoundException {
        enforceCallingPermission(uri, bundle, z);
        if (FileUtils.contains(Environment.getStorageDirectory(), file)) {
            return;
        }
        checkWorldReadAccess(file.getAbsolutePath());
    }

    public static void checkWorldReadAccess(String str) throws FileNotFoundException {
        int i = str.startsWith("/storage/") ? OsConstants.S_IRGRP : OsConstants.S_IROTH;
        try {
            StructStat structStatStat = Os.stat(str);
            if (OsConstants.S_ISREG(structStatStat.st_mode) && (structStatStat.st_mode & i) == i) {
                checkLeadingPathComponentsWorldExecutable(str);
                return;
            }
        } catch (ErrnoException unused) {
        }
        throw new FileNotFoundException("Can't access " + str);
    }

    private static void checkLeadingPathComponentsWorldExecutable(String str) throws FileNotFoundException {
        int i = str.startsWith("/storage/") ? OsConstants.S_IXGRP : OsConstants.S_IXOTH;
        for (File parentFile = new File(str).getParentFile(); parentFile != null; parentFile = parentFile.getParentFile()) {
            if (!parentFile.exists()) {
                throw new FileNotFoundException("access denied");
            }
            try {
                if ((Os.stat(parentFile.getPath()).st_mode & i) != i) {
                    throw new FileNotFoundException("Can't access " + str);
                }
            } catch (ErrnoException unused) {
                throw new FileNotFoundException("Can't access " + str);
            }
        }
    }

    static class FallbackException extends Exception {
        private final int mThrowSdkVersion;

        public FallbackException(String str, int i) {
            super(str);
            this.mThrowSdkVersion = i;
        }

        public FallbackException(String str, Throwable th, int i) {
            super(str, th);
            this.mThrowSdkVersion = i;
        }

        @Override // java.lang.Throwable
        public String getMessage() {
            if (getCause() != null) {
                return super.getMessage() + ": " + getCause().getMessage();
            }
            return super.getMessage();
        }

        public IllegalArgumentException rethrowAsIllegalArgumentException() {
            throw new IllegalArgumentException(getMessage());
        }

        public Cursor translateForQuery(int i) {
            Log.w(Logging.TAG, getMessage());
            return null;
        }

        public Uri translateForInsert(int i) {
            Log.w(Logging.TAG, getMessage());
            return null;
        }

        public int translateForUpdateDelete(int i) {
            Log.w(Logging.TAG, getMessage());
            return 0;
        }
    }

    static class VolumeNotFoundException extends FallbackException {
        public VolumeNotFoundException(String str) {
            super("Volume " + str + " not found", 29);
        }
    }

    static class VolumeArgumentException extends FallbackException {
        public VolumeArgumentException(File file, Collection<File> collection) {
            super("Requested path " + file + " doesn't appear under " + collection, 29);
        }
    }

    private DatabaseHelper getDatabaseForUri(Uri uri) throws VolumeNotFoundException {
        String strResolveVolumeName = resolveVolumeName(uri);
        synchronized (this.mAttachedVolumeNames) {
            if (!this.mAttachedVolumeNames.contains(strResolveVolumeName)) {
                throw new VolumeNotFoundException(strResolveVolumeName);
            }
        }
        if ("internal".equals(strResolveVolumeName)) {
            return this.mInternalDatabase;
        }
        return this.mExternalDatabase;
    }

    static boolean isMediaDatabaseName(String str) {
        if ("internal.db".equals(str) || "external.db".equals(str)) {
            return true;
        }
        return str.startsWith("external-") && str.endsWith(".db");
    }

    static boolean isInternalMediaDatabaseName(String str) {
        return "internal.db".equals(str);
    }

    private Uri getBaseContentUri(String str) {
        return MediaStore.AUTHORITY_URI.buildUpon().appendPath(str).build();
    }

    public Uri attachVolume(final String str, boolean z) {
        if (this.mCallingIdentity.get().pid != Process.myPid()) {
            throw new SecurityException("Opening and closing databases not allowed.");
        }
        MediaStore.checkArgumentVolumeName(str);
        if (!"internal".equals(str) && z) {
            try {
                getVolumePath(str);
            } catch (IOException e) {
                throw new IllegalArgumentException("Volume " + str + " currently unavailable", e);
            }
        }
        synchronized (this.mAttachedVolumeNames) {
            this.mAttachedVolumeNames.add(str);
        }
        ContentResolver contentResolver = getContext().getContentResolver();
        Uri baseContentUri = getBaseContentUri(str);
        contentResolver.notifyChange(getBaseContentUri(str), null);
        if (Logging.LOGV) {
            Log.v(Logging.TAG, "Attached volume: " + str);
        }
        if (!"internal".equals(str)) {
            contentResolver.notifyChange(getBaseContentUri("external"), null);
            ForegroundThread.getExecutor().execute(new Runnable() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$NXkptc-aYhN0k4DNjbUMX-LQA5Q
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$attachVolume$30$MediaProvider(str);
                }
            });
        }
        return baseContentUri;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$attachVolume$30, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$attachVolume$30$MediaProvider(final String str) {
        ("internal".equals(str) ? this.mInternalDatabase : this.mExternalDatabase).runWithTransaction(new Function() { // from class: com.android.providers.media.-$$Lambda$MediaProvider$1CDU5fJ12LLyxeohyL0g0CUV6r0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$attachVolume$29$MediaProvider(str, (SQLiteDatabase) obj);
            }
        });
        MediaDocumentsProvider.onMediaStoreReady(getContext(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$attachVolume$29, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ Object lambda$attachVolume$29$MediaProvider(String str, SQLiteDatabase sQLiteDatabase) {
        ensureDefaultFolders(str, sQLiteDatabase);
        ensureThumbnailsValid(str, sQLiteDatabase);
        return null;
    }

    private void detachVolume(Uri uri) {
        detachVolume(MediaStore.getVolumeName(uri));
    }

    public void detachVolume(String str) {
        if (this.mCallingIdentity.get().pid != Process.myPid()) {
            throw new SecurityException("Opening and closing databases not allowed.");
        }
        MediaStore.checkArgumentVolumeName(str);
        if ("internal".equals(str)) {
            throw new UnsupportedOperationException("Deleting the internal volume is not allowed");
        }
        this.mMediaScanner.onDetachVolume(str);
        synchronized (this.mAttachedVolumeNames) {
            this.mAttachedVolumeNames.remove(str);
        }
        ContentResolver contentResolver = getContext().getContentResolver();
        getBaseContentUri(str);
        contentResolver.notifyChange(getBaseContentUri(str), null);
        if (!"internal".equals(str)) {
            contentResolver.notifyChange(getBaseContentUri("external"), null);
        }
        if (Logging.LOGV) {
            Log.v(Logging.TAG, "Detached volume: " + str);
        }
    }

    private int matchUri(Uri uri, boolean z) {
        return this.mUriMatcher.matchUri(uri, z);
    }

    static class LocalUriMatcher {
        private final UriMatcher mPublic = new UriMatcher(-1);
        private final UriMatcher mHidden = new UriMatcher(-1);

        public int matchUri(Uri uri, boolean z) {
            int iMatch = this.mPublic.match(uri);
            if (iMatch != -1) {
                return iMatch;
            }
            int iMatch2 = this.mHidden.match(uri);
            if (iMatch2 == -1) {
                return -1;
            }
            if (z) {
                return iMatch2;
            }
            throw new IllegalStateException("Unknown URL: " + uri + " is hidden API");
        }

        public LocalUriMatcher(String str) {
            this.mPublic.addURI(str, "*/images/media", 1);
            this.mPublic.addURI(str, "*/images/media/#", 2);
            this.mPublic.addURI(str, "*/images/media/#/thumbnail", 3);
            this.mPublic.addURI(str, "*/images/thumbnails", 4);
            this.mPublic.addURI(str, "*/images/thumbnails/#", 5);
            this.mPublic.addURI(str, "*/audio/media", 100);
            this.mPublic.addURI(str, "*/audio/media/#", 101);
            this.mPublic.addURI(str, "*/audio/media/#/genres", 102);
            this.mPublic.addURI(str, "*/audio/media/#/genres/#", 103);
            this.mPublic.addURI(str, "*/audio/genres", 106);
            this.mPublic.addURI(str, "*/audio/genres/#", 107);
            this.mPublic.addURI(str, "*/audio/genres/#/members", 108);
            this.mPublic.addURI(str, "*/audio/genres/all/members", 109);
            this.mPublic.addURI(str, "*/audio/playlists", 110);
            this.mPublic.addURI(str, "*/audio/playlists/#", 111);
            this.mPublic.addURI(str, "*/audio/playlists/#/members", 112);
            this.mPublic.addURI(str, "*/audio/playlists/#/members/#", 113);
            this.mPublic.addURI(str, "*/audio/artists", 114);
            this.mPublic.addURI(str, "*/audio/artists/#", 115);
            this.mPublic.addURI(str, "*/audio/artists/#/albums", 118);
            this.mPublic.addURI(str, "*/audio/albums", 116);
            this.mPublic.addURI(str, "*/audio/albums/#", 117);
            this.mPublic.addURI(str, "*/audio/albumart", 119);
            this.mPublic.addURI(str, "*/audio/albumart/#", 120);
            this.mPublic.addURI(str, "*/audio/media/#/albumart", 121);
            this.mPublic.addURI(str, "*/video/media", MediaProvider.VIDEO_MEDIA);
            this.mPublic.addURI(str, "*/video/media/#", MediaProvider.VIDEO_MEDIA_ID);
            this.mPublic.addURI(str, "*/video/media/#/thumbnail", MediaProvider.VIDEO_MEDIA_ID_THUMBNAIL);
            this.mPublic.addURI(str, "*/video/thumbnails", MediaProvider.VIDEO_THUMBNAILS);
            this.mPublic.addURI(str, "*/video/thumbnails/#", MediaProvider.VIDEO_THUMBNAILS_ID);
            this.mPublic.addURI(str, "*/media_scanner", MediaProvider.MEDIA_SCANNER);
            this.mPublic.addURI(str, "*/fs_id", MediaProvider.FS_ID);
            this.mPublic.addURI(str, "*/version", MediaProvider.VERSION);
            this.mHidden.addURI(str, "*", MediaProvider.VOLUMES_ID);
            this.mHidden.addURI(str, null, MediaProvider.VOLUMES);
            this.mPublic.addURI(str, "*/file", MediaProvider.FILES);
            this.mPublic.addURI(str, "*/file/#", MediaProvider.FILES_ID);
            this.mPublic.addURI(str, "*/downloads", MediaProvider.DOWNLOADS);
            this.mPublic.addURI(str, "*/downloads/#", MediaProvider.DOWNLOADS_ID);
        }
    }

    private static void addGreylistPattern(String str) {
        sGreylist.add(Pattern.compile(" *" + str + " *"));
    }

    public ArrayMap<String, String> getProjectionMap(Class<?>... clsArr) {
        return this.mExternalDatabase.getProjectionMap(clsArr);
    }

    static <T> boolean containsAny(Set<T> set, Set<T> set2) {
        Iterator<T> it = set2.iterator();
        while (it.hasNext()) {
            if (set.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    static Uri computeCommonPrefix(List<Uri> list) {
        if (list.isEmpty()) {
            return null;
        }
        Uri uri = list.get(0);
        ArrayList arrayList = new ArrayList(uri.getPathSegments());
        for (int i = 1; i < list.size(); i++) {
            List<String> pathSegments = list.get(i).getPathSegments();
            for (int i2 = 0; i2 < arrayList.size() && i2 < pathSegments.size(); i2++) {
                if (!Objects.equals(arrayList.get(i2), pathSegments.get(i2))) {
                    while (arrayList.size() > i2) {
                        arrayList.remove(i2);
                    }
                }
            }
            int size = pathSegments.size();
            while (arrayList.size() > size) {
                arrayList.remove(size);
            }
        }
        Uri.Builder builderPath = uri.buildUpon().path(null);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            builderPath.appendPath((String) arrayList.get(i3));
        }
        return builderPath.build();
    }

    private boolean isCallingPackageSystemGallery() {
        return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_IS_SYSTEM_GALLERY);
    }

    @Deprecated
    private String getCallingPackageOrSelf() {
        return this.mCallingIdentity.get().getPackageName();
    }

    @Deprecated
    public int getCallingPackageTargetSdkVersion() {
        return this.mCallingIdentity.get().getTargetSdkVersion();
    }

    @Deprecated
    private boolean isCallingPackageAllowedHidden() {
        return isCallingPackageSelf();
    }

    @Deprecated
    private boolean isCallingPackageSelf() {
        return this.mCallingIdentity.get().hasPermission(1);
    }

    @Deprecated
    private boolean isCallingPackageShell() {
        return this.mCallingIdentity.get().hasPermission(2);
    }

    @Deprecated
    private boolean isCallingPackageManager() {
        return this.mCallingIdentity.get().hasPermission(4);
    }

    @Deprecated
    private boolean isCallingPackageDelegator() {
        return this.mCallingIdentity.get().hasPermission(8);
    }

    @Deprecated
    private boolean isCallingPackageLegacyRead() {
        return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_IS_LEGACY_READ);
    }

    @Deprecated
    private boolean isCallingPackageLegacyWrite() {
        return this.mCallingIdentity.get().hasPermission(LocalCallingIdentity.PERMISSION_IS_LEGACY_WRITE);
    }

    @Override // android.content.ContentProvider
    public void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("mThumbSize=" + this.mThumbSize);
        synchronized (this.mAttachedVolumeNames) {
            printWriter.println("mAttachedVolumeNames=" + this.mAttachedVolumeNames);
        }
        printWriter.println();
        Logging.dumpPersistent(printWriter);
    }
}
