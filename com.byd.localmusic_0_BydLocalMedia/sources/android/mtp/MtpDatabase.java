package android.mtp;

import android.content.BroadcastReceiver;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.mtp.MtpStorageManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.SystemProperties;
import android.os.storage.StorageVolume;
import android.provider.MediaStore;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.util.Log;
import android.util.SparseArray;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.collect.Sets;
import dalvik.system.CloseGuard;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: classes2.dex */
public class MtpDatabase implements AutoCloseable {
    private static final int[] AUDIO_PROPERTIES;
    private static final int[] DEVICE_PROPERTIES;
    private static final int[] FILE_PROPERTIES;
    private static final int[] IMAGE_PROPERTIES;
    private static final int MAX_THUMB_SIZE = 204800;
    private static final String NO_MEDIA = ".nomedia";
    private static final String PATH_WHERE = "_data=?";
    private static final int[] PLAYBACK_FORMATS;
    private static final String TAG = MtpDatabase.class.getSimpleName();
    private static final int[] VIDEO_PROPERTIES;
    private int mBatteryLevel;
    private int mBatteryScale;
    private final Context mContext;
    private SharedPreferences mDeviceProperties;
    private int mDeviceType;
    private MtpStorageManager mManager;
    private final ContentProviderClient mMediaProvider;
    private long mNativeContext;
    private MtpServer mServer;
    private final AtomicBoolean mClosed = new AtomicBoolean();
    private final CloseGuard mCloseGuard = CloseGuard.get();
    private final HashMap<String, MtpStorage> mStorageMap = new HashMap<>();
    private final SparseArray<MtpPropertyGroup> mPropertyGroupsByProperty = new SparseArray<>();
    private final SparseArray<MtpPropertyGroup> mPropertyGroupsByFormat = new SparseArray<>();
    private BroadcastReceiver mBatteryReceiver = new BroadcastReceiver() { // from class: android.mtp.MtpDatabase.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                MtpDatabase.this.mBatteryScale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                int intExtra = intent.getIntExtra("level", 0);
                if (intExtra != MtpDatabase.this.mBatteryLevel) {
                    MtpDatabase.this.mBatteryLevel = intExtra;
                    if (MtpDatabase.this.mServer != null) {
                        MtpDatabase.this.mServer.sendDevicePropertyChanged(MtpConstants.DEVICE_PROPERTY_BATTERY_LEVEL);
                    }
                }
            }
        }
    };

    private int[] getObjectReferences(int i) {
        return null;
    }

    private int[] getSupportedCaptureFormats() {
        return null;
    }

    private final native void native_finalize();

    private final native void native_setup();

    private int setObjectReferences(int i, int[] iArr) {
        return 8197;
    }

    static {
        System.loadLibrary("media_jni");
        PLAYBACK_FORMATS = new int[]{12288, 12289, 12292, 12293, 12296, 12297, 12299, MtpConstants.FORMAT_EXIF_JPEG, MtpConstants.FORMAT_TIFF_EP, MtpConstants.FORMAT_BMP, MtpConstants.FORMAT_GIF, MtpConstants.FORMAT_JFIF, MtpConstants.FORMAT_PNG, MtpConstants.FORMAT_TIFF, MtpConstants.FORMAT_WMA, MtpConstants.FORMAT_OGG, MtpConstants.FORMAT_AAC, MtpConstants.FORMAT_MP4_CONTAINER, MtpConstants.FORMAT_MP2, MtpConstants.FORMAT_3GP_CONTAINER, MtpConstants.FORMAT_ABSTRACT_AV_PLAYLIST, MtpConstants.FORMAT_WPL_PLAYLIST, MtpConstants.FORMAT_M3U_PLAYLIST, MtpConstants.FORMAT_PLS_PLAYLIST, MtpConstants.FORMAT_XML_DOCUMENT, MtpConstants.FORMAT_FLAC, MtpConstants.FORMAT_DNG, MtpConstants.FORMAT_HEIF};
        FILE_PROPERTIES = new int[]{MtpConstants.PROPERTY_STORAGE_ID, MtpConstants.PROPERTY_OBJECT_FORMAT, MtpConstants.PROPERTY_PROTECTION_STATUS, MtpConstants.PROPERTY_OBJECT_SIZE, MtpConstants.PROPERTY_OBJECT_FILE_NAME, MtpConstants.PROPERTY_DATE_MODIFIED, MtpConstants.PROPERTY_PERSISTENT_UID, MtpConstants.PROPERTY_PARENT_OBJECT, MtpConstants.PROPERTY_NAME, MtpConstants.PROPERTY_DISPLAY_NAME, MtpConstants.PROPERTY_DATE_ADDED};
        AUDIO_PROPERTIES = new int[]{MtpConstants.PROPERTY_ARTIST, MtpConstants.PROPERTY_ALBUM_NAME, MtpConstants.PROPERTY_ALBUM_ARTIST, MtpConstants.PROPERTY_TRACK, MtpConstants.PROPERTY_ORIGINAL_RELEASE_DATE, MtpConstants.PROPERTY_DURATION, MtpConstants.PROPERTY_GENRE, MtpConstants.PROPERTY_COMPOSER, MtpConstants.PROPERTY_AUDIO_WAVE_CODEC, MtpConstants.PROPERTY_BITRATE_TYPE, MtpConstants.PROPERTY_AUDIO_BITRATE, MtpConstants.PROPERTY_NUMBER_OF_CHANNELS, MtpConstants.PROPERTY_SAMPLE_RATE};
        VIDEO_PROPERTIES = new int[]{MtpConstants.PROPERTY_ARTIST, MtpConstants.PROPERTY_ALBUM_NAME, MtpConstants.PROPERTY_DURATION, MtpConstants.PROPERTY_DESCRIPTION};
        IMAGE_PROPERTIES = new int[]{MtpConstants.PROPERTY_DESCRIPTION};
        DEVICE_PROPERTIES = new int[]{MtpConstants.DEVICE_PROPERTY_SYNCHRONIZATION_PARTNER, MtpConstants.DEVICE_PROPERTY_DEVICE_FRIENDLY_NAME, MtpConstants.DEVICE_PROPERTY_IMAGE_SIZE, MtpConstants.DEVICE_PROPERTY_BATTERY_LEVEL, MtpConstants.DEVICE_PROPERTY_PERCEIVED_DEVICE_TYPE};
    }

    private int[] getSupportedObjectProperties(int i) {
        if (i != 12296 && i != 12297) {
            if (i != 12299) {
                if (i != 14337 && i != 14340 && i != 14343 && i != 14347) {
                    if (i != 47489 && i != 47492) {
                        if (i != 14353 && i != 14354) {
                            switch (i) {
                                case MtpConstants.FORMAT_WMA /* 47361 */:
                                case MtpConstants.FORMAT_OGG /* 47362 */:
                                case MtpConstants.FORMAT_AAC /* 47363 */:
                                    break;
                                default:
                                    return FILE_PROPERTIES;
                            }
                        }
                    }
                }
                return IntStream.concat(Arrays.stream(FILE_PROPERTIES), Arrays.stream(IMAGE_PROPERTIES)).toArray();
            }
            return IntStream.concat(Arrays.stream(FILE_PROPERTIES), Arrays.stream(VIDEO_PROPERTIES)).toArray();
        }
        return IntStream.concat(Arrays.stream(FILE_PROPERTIES), Arrays.stream(AUDIO_PROPERTIES)).toArray();
    }

    public static Uri getObjectPropertiesUri(int i, String str) {
        if (i != 12296 && i != 12297) {
            if (i != 12299) {
                if (i != 14337 && i != 14340 && i != 14343 && i != 14347) {
                    if (i != 47489 && i != 47492) {
                        if (i != 14353 && i != 14354) {
                            switch (i) {
                                case MtpConstants.FORMAT_WMA /* 47361 */:
                                case MtpConstants.FORMAT_OGG /* 47362 */:
                                case MtpConstants.FORMAT_AAC /* 47363 */:
                                    break;
                                default:
                                    return MediaStore.Files.getContentUri(str);
                            }
                        }
                    }
                }
                return MediaStore.Images.Media.getContentUri(str);
            }
            return MediaStore.Video.Media.getContentUri(str);
        }
        return MediaStore.Audio.Media.getContentUri(str);
    }

    private int[] getSupportedDeviceProperties() {
        return DEVICE_PROPERTIES;
    }

    private int[] getSupportedPlaybackFormats() {
        return PLAYBACK_FORMATS;
    }

    public MtpDatabase(Context context, String[] strArr) throws Throwable {
        native_setup();
        this.mContext = (Context) Objects.requireNonNull(context);
        this.mMediaProvider = context.getContentResolver().acquireContentProviderClient("media");
        this.mManager = new MtpStorageManager(new MtpStorageManager.MtpNotifier() { // from class: android.mtp.MtpDatabase.2
            @Override // android.mtp.MtpStorageManager.MtpNotifier
            public void sendObjectAdded(int i) {
                if (MtpDatabase.this.mServer != null) {
                    MtpDatabase.this.mServer.sendObjectAdded(i);
                }
            }

            @Override // android.mtp.MtpStorageManager.MtpNotifier
            public void sendObjectRemoved(int i) {
                if (MtpDatabase.this.mServer != null) {
                    MtpDatabase.this.mServer.sendObjectRemoved(i);
                }
            }

            @Override // android.mtp.MtpStorageManager.MtpNotifier
            public void sendObjectInfoChanged(int i) {
                if (MtpDatabase.this.mServer != null) {
                    MtpDatabase.this.mServer.sendObjectInfoChanged(i);
                }
            }
        }, strArr == null ? null : Sets.newHashSet(strArr));
        initDeviceProperties(context);
        this.mDeviceType = SystemProperties.getInt("sys.usb.mtp.device_type", 0);
        this.mCloseGuard.open("close");
    }

    public void setServer(MtpServer mtpServer) {
        this.mServer = mtpServer;
        try {
            this.mContext.unregisterReceiver(this.mBatteryReceiver);
        } catch (IllegalArgumentException unused) {
        }
        if (mtpServer != null) {
            this.mContext.registerReceiver(this.mBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        this.mManager.close();
        this.mCloseGuard.close();
        if (this.mClosed.compareAndSet(false, true)) {
            ContentProviderClient contentProviderClient = this.mMediaProvider;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
            native_finalize();
        }
    }

    protected void finalize() throws Throwable {
        try {
            if (this.mCloseGuard != null) {
                this.mCloseGuard.warnIfOpen();
            }
            close();
        } finally {
            super.finalize();
        }
    }

    public void addStorage(StorageVolume storageVolume) {
        MtpStorage mtpStorageAddMtpStorage = this.mManager.addMtpStorage(storageVolume);
        this.mStorageMap.put(storageVolume.getPath(), mtpStorageAddMtpStorage);
        MtpServer mtpServer = this.mServer;
        if (mtpServer != null) {
            mtpServer.addStorage(mtpStorageAddMtpStorage);
        }
    }

    public void removeStorage(StorageVolume storageVolume) {
        MtpStorage mtpStorage = this.mStorageMap.get(storageVolume.getPath());
        if (mtpStorage == null) {
            return;
        }
        MtpServer mtpServer = this.mServer;
        if (mtpServer != null) {
            mtpServer.removeStorage(mtpStorage);
        }
        this.mManager.removeMtpStorage(mtpStorage);
        this.mStorageMap.remove(storageVolume.getPath());
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void initDeviceProperties(android.content.Context r12) throws java.lang.Throwable {
        /*
            r11 = this;
            java.lang.String r0 = "device-properties"
            r1 = 0
            android.content.SharedPreferences r2 = r12.getSharedPreferences(r0, r1)
            r11.mDeviceProperties = r2
            java.io.File r2 = r12.getDatabasePath(r0)
            boolean r2 = r2.exists()
            if (r2 == 0) goto L83
            r2 = 0
            android.database.sqlite.SQLiteDatabase r1 = r12.openOrCreateDatabase(r0, r1, r2)     // Catch: java.lang.Throwable -> L5f java.lang.Exception -> L62
            if (r1 == 0) goto L54
            java.lang.String r4 = "properties"
            java.lang.String r3 = "_id"
            java.lang.String r5 = "code"
            java.lang.String r6 = "value"
            java.lang.String[] r5 = new java.lang.String[]{r3, r5, r6}     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L77
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r3 = r1
            android.database.Cursor r2 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L77
            if (r2 == 0) goto L54
            android.content.SharedPreferences r3 = r11.mDeviceProperties     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L77
            android.content.SharedPreferences$Editor r3 = r3.edit()     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L77
        L3a:
            boolean r4 = r2.moveToNext()     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L77
            if (r4 == 0) goto L4e
            r4 = 1
            java.lang.String r4 = r2.getString(r4)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L77
            r5 = 2
            java.lang.String r5 = r2.getString(r5)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L77
            r3.putString(r4, r5)     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L77
            goto L3a
        L4e:
            r3.commit()     // Catch: java.lang.Exception -> L52 java.lang.Throwable -> L77
            goto L54
        L52:
            r3 = move-exception
            goto L64
        L54:
            if (r2 == 0) goto L59
            r2.close()
        L59:
            if (r1 == 0) goto L73
        L5b:
            r1.close()
            goto L73
        L5f:
            r12 = move-exception
            r1 = r2
            goto L78
        L62:
            r3 = move-exception
            r1 = r2
        L64:
            java.lang.String r4 = android.mtp.MtpDatabase.TAG     // Catch: java.lang.Throwable -> L77
            java.lang.String r5 = "failed to migrate device properties"
            android.util.Log.e(r4, r5, r3)     // Catch: java.lang.Throwable -> L77
            if (r2 == 0) goto L70
            r2.close()
        L70:
            if (r1 == 0) goto L73
            goto L5b
        L73:
            r12.deleteDatabase(r0)
            goto L83
        L77:
            r12 = move-exception
        L78:
            if (r2 == 0) goto L7d
            r2.close()
        L7d:
            if (r1 == 0) goto L82
            r1.close()
        L82:
            throw r12
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.mtp.MtpDatabase.initDeviceProperties(android.content.Context):void");
    }

    public int beginSendObject(String str, int i, int i2, int i3) {
        MtpStorageManager.MtpObject storageRoot = i2 == 0 ? this.mManager.getStorageRoot(i3) : this.mManager.getObject(i2);
        if (storageRoot == null) {
            return -1;
        }
        return this.mManager.beginSendObject(storageRoot, Paths.get(str, new String[0]).getFileName().toString(), i);
    }

    private void endSendObject(int i, boolean z) {
        MtpStorageManager.MtpObject object = this.mManager.getObject(i);
        if (object == null || !this.mManager.endSendObject(object, z)) {
            Log.e(TAG, "Failed to successfully end send object");
        } else if (z) {
            updateMediaStore(this.mContext, object.getPath().toFile());
        }
    }

    private void rescanFile(String str, int i, int i2) {
        MediaStore.scanFile(this.mContext.getContentResolver(), new File(str));
    }

    private int[] getObjectList(int i, int i2, int i3) {
        List<MtpStorageManager.MtpObject> objects = this.mManager.getObjects(i3, i2, i);
        if (objects == null) {
            return null;
        }
        int[] iArr = new int[objects.size()];
        for (int i4 = 0; i4 < objects.size(); i4++) {
            iArr[i4] = objects.get(i4).getId();
        }
        return iArr;
    }

    public int getNumObjects(int i, int i2, int i3) {
        List<MtpStorageManager.MtpObject> objects = this.mManager.getObjects(i3, i2, i);
        if (objects == null) {
            return -1;
        }
        return objects.size();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private android.mtp.MtpPropertyList getObjectPropertyList(int r10, int r11, int r12, int r13, int r14) {
        /*
            Method dump skipped, instruction units count: 231
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.mtp.MtpDatabase.getObjectPropertyList(int, int, int, int, int):android.mtp.MtpPropertyList");
    }

    private int renameFile(int i, String str) {
        MtpStorageManager.MtpObject object = this.mManager.getObject(i);
        if (object == null) {
            return 8201;
        }
        Path path = object.getPath();
        if (!this.mManager.beginRenameObject(object, str)) {
            return 8194;
        }
        Path path2 = object.getPath();
        boolean zRenameTo = path.toFile().renameTo(path2.toFile());
        try {
            Os.access(path.toString(), OsConstants.F_OK);
            Os.access(path2.toString(), OsConstants.F_OK);
        } catch (ErrnoException unused) {
        }
        if (!this.mManager.endRenameObject(object, path.getFileName().toString(), zRenameTo)) {
            Log.e(TAG, "Failed to end rename object");
        }
        if (!zRenameTo) {
            return 8194;
        }
        updateMediaStore(this.mContext, path.toFile());
        updateMediaStore(this.mContext, path2.toFile());
        return 8193;
    }

    private int beginMoveObject(int i, int i2, int i3) {
        MtpStorageManager.MtpObject object = this.mManager.getObject(i);
        MtpStorageManager.MtpObject storageRoot = i2 == 0 ? this.mManager.getStorageRoot(i3) : this.mManager.getObject(i2);
        if (object == null || storageRoot == null) {
            return 8201;
        }
        return this.mManager.beginMoveObject(object, storageRoot) ? 8193 : 8194;
    }

    private void endMoveObject(int i, int i2, int i3, int i4, int i5, boolean z) {
        MtpStorageManager.MtpObject storageRoot = i == 0 ? this.mManager.getStorageRoot(i3) : this.mManager.getObject(i);
        MtpStorageManager.MtpObject storageRoot2 = i2 == 0 ? this.mManager.getStorageRoot(i4) : this.mManager.getObject(i2);
        String name = this.mManager.getObject(i5).getName();
        if (storageRoot2 == null || storageRoot == null || !this.mManager.endMoveObject(storageRoot, storageRoot2, name, z)) {
            Log.e(TAG, "Failed to end move object");
            return;
        }
        MtpStorageManager.MtpObject object = this.mManager.getObject(i5);
        if (!z || object == null) {
            return;
        }
        Path pathResolve = storageRoot2.getPath().resolve(name);
        updateMediaStore(this.mContext, storageRoot.getPath().resolve(name).toFile());
        updateMediaStore(this.mContext, pathResolve.toFile());
    }

    private int beginCopyObject(int i, int i2, int i3) {
        MtpStorageManager.MtpObject object = this.mManager.getObject(i);
        MtpStorageManager.MtpObject storageRoot = i2 == 0 ? this.mManager.getStorageRoot(i3) : this.mManager.getObject(i2);
        if (object == null || storageRoot == null) {
            return 8201;
        }
        return this.mManager.beginCopyObject(object, storageRoot);
    }

    private void endCopyObject(int i, boolean z) {
        MtpStorageManager.MtpObject object = this.mManager.getObject(i);
        if (object == null || !this.mManager.endCopyObject(object, z)) {
            Log.e(TAG, "Failed to end copy object");
        } else if (z) {
            updateMediaStore(this.mContext, object.getPath().toFile());
        }
    }

    private static void updateMediaStore(Context context, File file) {
        ContentResolver contentResolver = context.getContentResolver();
        if (!file.isDirectory() && file.getName().toLowerCase(Locale.ROOT).endsWith(NO_MEDIA)) {
            MediaStore.scanFile(contentResolver, file.getParentFile());
        } else {
            MediaStore.scanFile(contentResolver, file);
        }
    }

    private int setObjectProperty(int i, int i2, long j, String str) {
        return i2 != 56327 ? MtpConstants.RESPONSE_OBJECT_PROP_NOT_SUPPORTED : renameFile(i, str);
    }

    private int getDeviceProperty(int i, long[] jArr, char[] cArr) {
        switch (i) {
            case MtpConstants.DEVICE_PROPERTY_BATTERY_LEVEL /* 20481 */:
                jArr[0] = this.mBatteryLevel;
                jArr[1] = this.mBatteryScale;
                break;
            case MtpConstants.DEVICE_PROPERTY_IMAGE_SIZE /* 20483 */:
                Display defaultDisplay = ((WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                String str = Integer.toString(defaultDisplay.getMaximumSizeDimension()) + "x" + Integer.toString(defaultDisplay.getMaximumSizeDimension());
                str.getChars(0, str.length(), cArr, 0);
                cArr[str.length()] = 0;
                break;
            case MtpConstants.DEVICE_PROPERTY_SYNCHRONIZATION_PARTNER /* 54273 */:
            case MtpConstants.DEVICE_PROPERTY_DEVICE_FRIENDLY_NAME /* 54274 */:
                String string = this.mDeviceProperties.getString(Integer.toString(i), "");
                int length = string.length();
                if (length > 255) {
                    length = 255;
                }
                string.getChars(0, length, cArr, 0);
                cArr[length] = 0;
                break;
            case MtpConstants.DEVICE_PROPERTY_PERCEIVED_DEVICE_TYPE /* 54279 */:
                jArr[0] = this.mDeviceType;
                break;
        }
        return 8193;
    }

    private int setDeviceProperty(int i, long j, String str) {
        switch (i) {
            case MtpConstants.DEVICE_PROPERTY_SYNCHRONIZATION_PARTNER /* 54273 */:
            case MtpConstants.DEVICE_PROPERTY_DEVICE_FRIENDLY_NAME /* 54274 */:
                SharedPreferences.Editor editorEdit = this.mDeviceProperties.edit();
                editorEdit.putString(Integer.toString(i), str);
                return editorEdit.commit() ? 8193 : 8194;
            default:
                return 8202;
        }
    }

    private boolean getObjectInfo(int i, int[] iArr, char[] cArr, long[] jArr) {
        MtpStorageManager.MtpObject object = this.mManager.getObject(i);
        if (object == null) {
            return false;
        }
        iArr[0] = object.getStorageId();
        iArr[1] = object.getFormat();
        iArr[2] = object.getParent().isRoot() ? 0 : object.getParent().getId();
        int iMin = Integer.min(object.getName().length(), 255);
        object.getName().getChars(0, iMin, cArr, 0);
        cArr[iMin] = 0;
        jArr[0] = object.getModifiedTime();
        jArr[1] = object.getModifiedTime();
        return true;
    }

    private int getObjectFilePath(int i, char[] cArr, long[] jArr) {
        MtpStorageManager.MtpObject object = this.mManager.getObject(i);
        if (object == null) {
            return 8201;
        }
        String string = object.getPath().toString();
        int iMin = Integer.min(string.length(), 4096);
        string.getChars(0, iMin, cArr, 0);
        cArr[iMin] = 0;
        jArr[0] = object.getSize();
        jArr[1] = object.getFormat();
        return 8193;
    }

    private int getObjectFormat(int i) {
        MtpStorageManager.MtpObject object = this.mManager.getObject(i);
        if (object == null) {
            return -1;
        }
        return object.getFormat();
    }

    private byte[] getThumbnailProcess(String str, Bitmap bitmap) {
        try {
            if (bitmap == null) {
                Log.d(TAG, "getThumbnailProcess: Fail to generate thumbnail. Probably unsupported or corrupted image");
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            if (byteArrayOutputStream.size() > MAX_THUMB_SIZE) {
                return null;
            }
            return byteArrayOutputStream.toByteArray();
        } catch (OutOfMemoryError e) {
            Log.w(TAG, "OutOfMemoryError:" + e);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003c A[Catch: IOException -> 0x0056, TryCatch #0 {IOException -> 0x0056, blocks: (B:18:0x0031, B:20:0x003c, B:22:0x0041), top: B:27:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0031 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean getThumbnailInfo(int r6, long[] r7) {
        /*
            r5 = this;
            android.mtp.MtpStorageManager r0 = r5.mManager
            android.mtp.MtpStorageManager$MtpObject r6 = r0.getObject(r6)
            r0 = 0
            if (r6 != 0) goto La
            return r0
        La:
            java.nio.file.Path r1 = r6.getPath()
            java.lang.String r1 = r1.toString()
            int r6 = r6.getFormat()
            r2 = 14337(0x3801, float:2.009E-41)
            r3 = 2
            r4 = 1
            if (r6 == r2) goto L31
            r2 = 14340(0x3804, float:2.0095E-41)
            if (r6 == r2) goto L56
            r2 = 14347(0x380b, float:2.0104E-41)
            if (r6 == r2) goto L56
            r2 = 14354(0x3812, float:2.0114E-41)
            if (r6 == r2) goto L31
            r2 = 14343(0x3807, float:2.0099E-41)
            if (r6 == r2) goto L56
            r2 = 14344(0x3808, float:2.01E-41)
            if (r6 == r2) goto L31
            return r0
        L31:
            android.media.ExifInterface r6 = new android.media.ExifInterface     // Catch: java.io.IOException -> L56
            r6.<init>(r1)     // Catch: java.io.IOException -> L56
            long[] r1 = r6.getThumbnailRange()     // Catch: java.io.IOException -> L56
            if (r1 == 0) goto L3f
            r1 = r1[r4]     // Catch: java.io.IOException -> L56
            goto L41
        L3f:
            r1 = 0
        L41:
            r7[r0] = r1     // Catch: java.io.IOException -> L56
            java.lang.String r1 = "PixelXDimension"
            int r1 = r6.getAttributeInt(r1, r0)     // Catch: java.io.IOException -> L56
            long r1 = (long) r1     // Catch: java.io.IOException -> L56
            r7[r4] = r1     // Catch: java.io.IOException -> L56
            java.lang.String r1 = "PixelYDimension"
            int r6 = r6.getAttributeInt(r1, r0)     // Catch: java.io.IOException -> L56
            long r1 = (long) r6     // Catch: java.io.IOException -> L56
            r7[r3] = r1     // Catch: java.io.IOException -> L56
            return r4
        L56:
            r1 = 204800(0x32000, double:1.011846E-318)
            r7[r0] = r1
            r0 = 320(0x140, double:1.58E-321)
            r7[r4] = r0
            r0 = 240(0xf0, double:1.186E-321)
            r7[r3] = r0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.mtp.MtpDatabase.getThumbnailInfo(int, long[]):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x002f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public byte[] getThumbnailData(int r4) {
        /*
            r3 = this;
            android.mtp.MtpStorageManager r0 = r3.mManager
            android.mtp.MtpStorageManager$MtpObject r4 = r0.getObject(r4)
            r0 = 0
            if (r4 != 0) goto La
            return r0
        La:
            java.nio.file.Path r1 = r4.getPath()
            java.lang.String r1 = r1.toString()
            int r4 = r4.getFormat()
            r2 = 14337(0x3801, float:2.009E-41)
            if (r4 == r2) goto L2f
            r2 = 14340(0x3804, float:2.0095E-41)
            if (r4 == r2) goto L39
            r2 = 14347(0x380b, float:2.0104E-41)
            if (r4 == r2) goto L39
            r2 = 14354(0x3812, float:2.0114E-41)
            if (r4 == r2) goto L2f
            r2 = 14343(0x3807, float:2.0099E-41)
            if (r4 == r2) goto L39
            r2 = 14344(0x3808, float:2.01E-41)
            if (r4 == r2) goto L2f
            return r0
        L2f:
            android.media.ExifInterface r4 = new android.media.ExifInterface     // Catch: java.io.IOException -> L39
            r4.<init>(r1)     // Catch: java.io.IOException -> L39
            byte[] r4 = r4.getThumbnail()     // Catch: java.io.IOException -> L39
            return r4
        L39:
            r4 = 1
            android.graphics.Bitmap r4 = android.media.ThumbnailUtils.createImageThumbnail(r1, r4)
            byte[] r4 = r3.getThumbnailProcess(r1, r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.mtp.MtpDatabase.getThumbnailData(int):byte[]");
    }

    private int beginDeleteObject(int i) {
        MtpStorageManager.MtpObject object = this.mManager.getObject(i);
        if (object == null) {
            return 8201;
        }
        return !this.mManager.beginRemoveObject(object) ? 8194 : 8193;
    }

    private void endDeleteObject(int i, boolean z) {
        MtpStorageManager.MtpObject object = this.mManager.getObject(i);
        if (object == null) {
            return;
        }
        if (!this.mManager.endRemoveObject(object, z)) {
            Log.e(TAG, "Failed to end remove object");
        }
        if (z) {
            deleteFromMedia(object, object.getPath(), object.isDir());
        }
    }

    private void deleteFromMedia(MtpStorageManager.MtpObject mtpObject, Path path, boolean z) {
        Uri contentUri = MediaStore.Files.getContentUri(mtpObject.getVolumeName());
        if (z) {
            try {
                this.mMediaProvider.delete(contentUri, "_data LIKE ?1 AND lower(substr(_data,1,?2))=lower(?3)", new String[]{path + "/%", Integer.toString(path.toString().length() + 1), path.toString() + "/"});
            } catch (Exception unused) {
                Log.d(TAG, "Failed to delete " + path + " from MediaProvider");
                return;
            }
        }
        if (this.mMediaProvider.delete(contentUri, PATH_WHERE, new String[]{path.toString()}) == 0) {
            Log.i(TAG, "MediaProvider didn't delete " + path);
        }
        updateMediaStore(this.mContext, path.toFile());
    }
}
