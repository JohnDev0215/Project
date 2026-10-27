package com.android.launcher3;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Process;
import android.os.UserHandle;
import android.util.Log;
import android.util.LongSparseArray;
import com.android.launcher3.compat.ShortcutConfigActivityInfo;
import com.android.launcher3.compat.UserManagerCompat;
import com.android.launcher3.icons.GraphicsUtils;
import com.android.launcher3.icons.IconCache;
import com.android.launcher3.icons.LauncherIcons;
import com.android.launcher3.icons.ShadowGenerator;
import com.android.launcher3.icons.cache.BaseIconCache;
import com.android.launcher3.model.WidgetItem;
import com.android.launcher3.util.ComponentKey;
import com.android.launcher3.util.PackageUserKey;
import com.android.launcher3.util.Preconditions;
import com.android.launcher3.util.SQLiteCacheHelper;
import com.android.launcher3.widget.WidgetCell;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes4.dex */
public class WidgetPreviewLoader {
    private static final boolean DEBUG = false;
    private static final String TAG = "WidgetPreviewLoader";
    private final Context mContext;
    private final CacheDb mDb;
    private final IconCache mIconCache;
    private final UserManagerCompat mUserManager;
    private final HashMap<String, long[]> mPackageVersions = new HashMap<>();
    final Set<Bitmap> mUnusedBitmaps = Collections.newSetFromMap(new WeakHashMap());
    private final MainThreadExecutor mMainThreadExecutor = new MainThreadExecutor();
    final Handler mWorkerHandler = new Handler(LauncherModel.getWorkerLooper());

    public WidgetPreviewLoader(Context context, IconCache iconCache) {
        this.mContext = context;
        this.mIconCache = iconCache;
        this.mUserManager = UserManagerCompat.getInstance(context);
        this.mDb = new CacheDb(context);
    }

    public CancellationSignal getPreview(WidgetItem item, int previewWidth, int previewHeight, WidgetCell caller) {
        PreviewLoadTask previewLoadTask = new PreviewLoadTask(new WidgetCacheKey(item.componentName, item.user, previewWidth + "x" + previewHeight), item, previewWidth, previewHeight, caller);
        previewLoadTask.executeOnExecutor(Utilities.THREAD_POOL_EXECUTOR, new Void[0]);
        CancellationSignal cancellationSignal = new CancellationSignal();
        cancellationSignal.setOnCancelListener(previewLoadTask);
        return cancellationSignal;
    }

    public void refresh() {
        this.mDb.clear();
    }

    private static class CacheDb extends SQLiteCacheHelper {
        private static final String COLUMN_COMPONENT = "componentName";
        private static final String COLUMN_LAST_UPDATED = "lastUpdated";
        private static final String COLUMN_PACKAGE = "packageName";
        private static final String COLUMN_PREVIEW_BITMAP = "preview_bitmap";
        private static final String COLUMN_SIZE = "size";
        private static final String COLUMN_USER = "profileId";
        private static final String COLUMN_VERSION = "version";
        private static final int DB_VERSION = 9;
        private static final String TABLE_NAME = "shortcut_and_widget_previews";

        public CacheDb(Context context) {
            super(context, LauncherFiles.WIDGET_PREVIEWS_DB, 9, TABLE_NAME);
        }

        @Override // com.android.launcher3.util.SQLiteCacheHelper
        public void onCreateTable(SQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS shortcut_and_widget_previews (componentName TEXT NOT NULL, profileId INTEGER NOT NULL, size TEXT NOT NULL, packageName TEXT NOT NULL, lastUpdated INTEGER NOT NULL DEFAULT 0, version INTEGER NOT NULL DEFAULT 0, preview_bitmap BLOB, PRIMARY KEY (componentName, profileId, size) );");
        }
    }

    void writeToDb(WidgetCacheKey key, long[] versions, Bitmap preview) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseIconCache.IconDB.COLUMN_COMPONENT, key.componentName.flattenToShortString());
        contentValues.put("profileId", Long.valueOf(this.mUserManager.getSerialNumberForUser(key.user)));
        contentValues.put("size", key.size);
        contentValues.put("packageName", key.componentName.getPackageName());
        contentValues.put("version", Long.valueOf(versions[0]));
        contentValues.put(BaseIconCache.IconDB.COLUMN_LAST_UPDATED, Long.valueOf(versions[1]));
        contentValues.put("preview_bitmap", GraphicsUtils.flattenBitmap(preview));
        this.mDb.insertOrReplace(contentValues);
    }

    public void removePackage(String packageName, UserHandle user) {
        removePackage(packageName, user, this.mUserManager.getSerialNumberForUser(user));
    }

    private void removePackage(String packageName, UserHandle user, long userSerial) {
        synchronized (this.mPackageVersions) {
            this.mPackageVersions.remove(packageName);
        }
        this.mDb.delete("packageName = ? AND profileId = ?", new String[]{packageName, Long.toString(userSerial)});
    }

    public void removeObsoletePreviews(ArrayList<? extends ComponentKey> list, PackageUserKey packageUser) {
        int i;
        Preconditions.assertWorkerThread();
        LongSparseArray longSparseArray = new LongSparseArray();
        for (ComponentKey componentKey : list) {
            long serialNumberForUser = this.mUserManager.getSerialNumberForUser(componentKey.user);
            HashSet hashSet = (HashSet) longSparseArray.get(serialNumberForUser);
            if (hashSet == null) {
                hashSet = new HashSet();
                longSparseArray.put(serialNumberForUser, hashSet);
            }
            hashSet.add(componentKey.componentName.getPackageName());
        }
        LongSparseArray longSparseArray2 = new LongSparseArray();
        long serialNumberForUser2 = packageUser == null ? 0L : this.mUserManager.getSerialNumberForUser(packageUser.mUser);
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = this.mDb.query(new String[]{"profileId", "packageName", BaseIconCache.IconDB.COLUMN_LAST_UPDATED, "version"}, null, null);
            } catch (SQLException e) {
                Log.e(TAG, "Error updating widget previews", e);
                if (cursorQuery == null) {
                    return;
                }
            }
            while (true) {
                if (!cursorQuery.moveToNext()) {
                    break;
                }
                long j = cursorQuery.getLong(0);
                String string = cursorQuery.getString(1);
                long j2 = cursorQuery.getLong(2);
                long j3 = cursorQuery.getLong(3);
                if (packageUser == null || (string.equals(packageUser.mPackageName) && j == serialNumberForUser2)) {
                    HashSet hashSet2 = (HashSet) longSparseArray.get(j);
                    if (hashSet2 != null && hashSet2.contains(string)) {
                        long[] packageVersion = getPackageVersion(string);
                        if (packageVersion[0] != j3 || packageVersion[1] != j2) {
                        }
                    }
                    HashSet hashSet3 = (HashSet) longSparseArray2.get(j);
                    if (hashSet3 == null) {
                        hashSet3 = new HashSet();
                        longSparseArray2.put(j, hashSet3);
                    }
                    hashSet3.add(string);
                }
                cursorQuery.close();
            }
            for (i = 0; i < longSparseArray2.size(); i++) {
                long jKeyAt = longSparseArray2.keyAt(i);
                UserHandle userForSerialNumber = this.mUserManager.getUserForSerialNumber(jKeyAt);
                Iterator it = ((HashSet) longSparseArray2.valueAt(i)).iterator();
                while (it.hasNext()) {
                    removePackage((String) it.next(), userForSerialNumber, jKeyAt);
                }
            }
            if (cursorQuery == null) {
                return;
            }
            cursorQuery.close();
        } catch (Throwable th) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0076 A[PHI: r9
  0x0076: PHI (r9v4 android.database.Cursor) = (r9v3 android.database.Cursor), (r9v8 android.database.Cursor) binds: [B:30:0x0074, B:21:0x0064] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.graphics.Bitmap readFromDb(com.android.launcher3.WidgetPreviewLoader.WidgetCacheKey r10, android.graphics.Bitmap r11, com.android.launcher3.WidgetPreviewLoader.PreviewLoadTask r12) throws java.lang.Throwable {
        /*
            r9 = this;
            r0 = 0
            com.android.launcher3.WidgetPreviewLoader$CacheDb r1 = r9.mDb     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            java.lang.String r2 = "preview_bitmap"
            java.lang.String[] r2 = new java.lang.String[]{r2}     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            java.lang.String r3 = "componentName = ? AND profileId = ? AND size = ?"
            r4 = 3
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            android.content.ComponentName r5 = r10.componentName     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            java.lang.String r5 = r5.flattenToShortString()     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            r6 = 0
            r4[r6] = r5     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            r5 = 1
            com.android.launcher3.compat.UserManagerCompat r9 = r9.mUserManager     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            android.os.UserHandle r7 = r10.user     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            long r7 = r9.getSerialNumberForUser(r7)     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            java.lang.String r9 = java.lang.Long.toString(r7)     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            r4[r5] = r9     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            r9 = 2
            java.lang.String r10 = r10.size     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            r4[r9] = r10     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            android.database.Cursor r9 = r1.query(r2, r3, r4)     // Catch: java.lang.Throwable -> L69 android.database.SQLException -> L6b
            boolean r10 = r12.isCancelled()     // Catch: android.database.SQLException -> L67 java.lang.Throwable -> L7a
            if (r10 == 0) goto L3c
            if (r9 == 0) goto L3b
            r9.close()
        L3b:
            return r0
        L3c:
            boolean r10 = r9.moveToNext()     // Catch: android.database.SQLException -> L67 java.lang.Throwable -> L7a
            if (r10 == 0) goto L64
            byte[] r10 = r9.getBlob(r6)     // Catch: android.database.SQLException -> L67 java.lang.Throwable -> L7a
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options     // Catch: android.database.SQLException -> L67 java.lang.Throwable -> L7a
            r1.<init>()     // Catch: android.database.SQLException -> L67 java.lang.Throwable -> L7a
            r1.inBitmap = r11     // Catch: android.database.SQLException -> L67 java.lang.Throwable -> L7a
            boolean r11 = r12.isCancelled()     // Catch: java.lang.Exception -> L5e android.database.SQLException -> L67 java.lang.Throwable -> L7a
            if (r11 != 0) goto L64
            int r11 = r10.length     // Catch: java.lang.Exception -> L5e android.database.SQLException -> L67 java.lang.Throwable -> L7a
            android.graphics.Bitmap r10 = android.graphics.BitmapFactory.decodeByteArray(r10, r6, r11, r1)     // Catch: java.lang.Exception -> L5e android.database.SQLException -> L67 java.lang.Throwable -> L7a
            if (r9 == 0) goto L5d
            r9.close()
        L5d:
            return r10
        L5e:
            if (r9 == 0) goto L63
            r9.close()
        L63:
            return r0
        L64:
            if (r9 == 0) goto L79
            goto L76
        L67:
            r10 = move-exception
            goto L6d
        L69:
            r10 = move-exception
            goto L7c
        L6b:
            r10 = move-exception
            r9 = r0
        L6d:
            java.lang.String r11 = "WidgetPreviewLoader"
            java.lang.String r12 = "Error loading preview from DB"
            android.util.Log.w(r11, r12, r10)     // Catch: java.lang.Throwable -> L7a
            if (r9 == 0) goto L79
        L76:
            r9.close()
        L79:
            return r0
        L7a:
            r10 = move-exception
            r0 = r9
        L7c:
            if (r0 == 0) goto L81
            r0.close()
        L81:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.WidgetPreviewLoader.readFromDb(com.android.launcher3.WidgetPreviewLoader$WidgetCacheKey, android.graphics.Bitmap, com.android.launcher3.WidgetPreviewLoader$PreviewLoadTask):android.graphics.Bitmap");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap generatePreview(BaseActivity launcher, WidgetItem item, Bitmap recycle, int previewWidth, int previewHeight) {
        if (item.widgetInfo != null) {
            return generateWidgetPreview(launcher, item.widgetInfo, previewWidth, recycle, null);
        }
        return generateShortcutPreview(launcher, item.activityInfo, previewWidth, previewHeight, recycle);
    }

    public Bitmap generateWidgetPreview(BaseActivity launcher, LauncherAppWidgetProviderInfo info, int maxPreviewWidth, Bitmap preview, int[] preScaledWidthOut) {
        Drawable drawableMutateOnMainThread;
        int iMax;
        int iMax2;
        Canvas canvas;
        Drawable fullResIcon;
        Bitmap bitmapCreateBitmap = preview;
        int i = maxPreviewWidth < 0 ? Integer.MAX_VALUE : maxPreviewWidth;
        if (info.previewImage != 0) {
            try {
                drawableMutateOnMainThread = info.loadPreviewImage(this.mContext, 0);
            } catch (OutOfMemoryError e) {
                Log.w(TAG, "Error loading widget preview for: " + info.provider, e);
                drawableMutateOnMainThread = null;
            }
            if (drawableMutateOnMainThread != null) {
                drawableMutateOnMainThread = mutateOnMainThread(drawableMutateOnMainThread);
            } else {
                Log.w(TAG, "Can't load widget preview drawable 0x" + Integer.toHexString(info.previewImage) + " for provider: " + info.provider);
            }
        } else {
            drawableMutateOnMainThread = null;
        }
        boolean z = drawableMutateOnMainThread != null;
        int i2 = info.spanX;
        int i3 = info.spanY;
        if (z && drawableMutateOnMainThread.getIntrinsicWidth() > 0 && drawableMutateOnMainThread.getIntrinsicHeight() > 0) {
            iMax2 = drawableMutateOnMainThread.getIntrinsicWidth();
            iMax = drawableMutateOnMainThread.getIntrinsicHeight();
        } else {
            DeviceProfile deviceProfile = launcher.getDeviceProfile();
            int iMin = Math.min(deviceProfile.cellWidthPx, deviceProfile.cellHeightPx);
            iMax = iMin * i3;
            iMax2 = iMin * i2;
        }
        if (preScaledWidthOut != null) {
            preScaledWidthOut[0] = iMax2;
        }
        float f = iMax2 > i ? i / iMax2 : 1.0f;
        if (f != 1.0f) {
            iMax2 = Math.max((int) (iMax2 * f), 1);
            iMax = Math.max((int) (iMax * f), 1);
        }
        Canvas canvas2 = new Canvas();
        if (bitmapCreateBitmap == null) {
            bitmapCreateBitmap = Bitmap.createBitmap(iMax2, iMax, Bitmap.Config.ARGB_8888);
            canvas2.setBitmap(bitmapCreateBitmap);
        } else {
            if (preview.getHeight() > iMax) {
                bitmapCreateBitmap.reconfigure(preview.getWidth(), iMax, preview.getConfig());
            }
            canvas2.setBitmap(bitmapCreateBitmap);
            canvas2.drawColor(0, PorterDuff.Mode.CLEAR);
        }
        int width = (bitmapCreateBitmap.getWidth() - iMax2) / 2;
        if (z) {
            drawableMutateOnMainThread.setBounds(width, 0, iMax2 + width, iMax);
            drawableMutateOnMainThread.draw(canvas2);
        } else {
            RectF rectFDrawBoxWithShadow = drawBoxWithShadow(canvas2, iMax2, iMax);
            Paint paint = new Paint(1);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(this.mContext.getResources().getDimension(R.dimen.widget_preview_cell_divider_width));
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            float f2 = rectFDrawBoxWithShadow.left;
            float fWidth = rectFDrawBoxWithShadow.width() / i2;
            int i4 = 1;
            while (i4 < i2) {
                f2 += fWidth;
                canvas2.drawLine(f2, 0.0f, f2, iMax, paint);
                i4++;
                canvas2 = canvas2;
            }
            Canvas canvas3 = canvas2;
            float f3 = rectFDrawBoxWithShadow.top;
            float fHeight = rectFDrawBoxWithShadow.height() / i3;
            for (int i5 = 1; i5 < i3; i5++) {
                f3 += fHeight;
                canvas3.drawLine(0.0f, f3, iMax2, f3, paint);
            }
            try {
                fullResIcon = this.mIconCache.getFullResIcon(info.provider.getPackageName(), info.icon);
            } catch (Resources.NotFoundException unused) {
            }
            if (fullResIcon != null) {
                int iMin2 = (int) Math.min(launcher.getDeviceProfile().iconSizePx * f, Math.min(rectFDrawBoxWithShadow.width(), rectFDrawBoxWithShadow.height()));
                Drawable drawableMutateOnMainThread2 = mutateOnMainThread(fullResIcon);
                int i6 = (iMax2 - iMin2) / 2;
                int i7 = (iMax - iMin2) / 2;
                drawableMutateOnMainThread2.setBounds(i6, i7, i6 + iMin2, iMin2 + i7);
                canvas = canvas3;
                try {
                    drawableMutateOnMainThread2.draw(canvas);
                } catch (Resources.NotFoundException unused2) {
                }
            } else {
                canvas = canvas3;
            }
            canvas.setBitmap(null);
        }
        return bitmapCreateBitmap;
    }

    private RectF drawBoxWithShadow(Canvas c, int width, int height) {
        Resources resources = this.mContext.getResources();
        ShadowGenerator.Builder builder = new ShadowGenerator.Builder(-1);
        builder.shadowBlur = resources.getDimension(R.dimen.widget_preview_shadow_blur);
        builder.radius = resources.getDimension(R.dimen.widget_preview_corner_radius);
        builder.keyShadowDistance = resources.getDimension(R.dimen.widget_preview_key_shadow_distance);
        builder.bounds.set(builder.shadowBlur, builder.shadowBlur, width - builder.shadowBlur, (height - builder.shadowBlur) - builder.keyShadowDistance);
        builder.drawShadow(c);
        return builder.bounds;
    }

    private Bitmap generateShortcutPreview(BaseActivity launcher, ShortcutConfigActivityInfo info, int maxWidth, int maxHeight, Bitmap preview) {
        int i = launcher.getDeviceProfile().iconSizePx;
        int dimensionPixelSize = launcher.getResources().getDimensionPixelSize(R.dimen.widget_preview_shortcut_padding);
        int i2 = (dimensionPixelSize * 2) + i;
        if (maxHeight < i2 || maxWidth < i2) {
            throw new RuntimeException("Max size is too small for preview");
        }
        Canvas canvas = new Canvas();
        if (preview == null || preview.getWidth() < i2 || preview.getHeight() < i2) {
            preview = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
            canvas.setBitmap(preview);
        } else {
            if (preview.getWidth() > i2 || preview.getHeight() > i2) {
                preview.reconfigure(i2, i2, preview.getConfig());
            }
            canvas.setBitmap(preview);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        }
        RectF rectFDrawBoxWithShadow = drawBoxWithShadow(canvas, i2, i2);
        LauncherIcons launcherIconsObtain = LauncherIcons.obtain(this.mContext);
        Bitmap bitmap = launcherIconsObtain.createBadgedIconBitmap(mutateOnMainThread(info.getFullResIcon(this.mIconCache)), Process.myUserHandle(), 0).icon;
        launcherIconsObtain.recycle();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        float f = i;
        rectFDrawBoxWithShadow.set(0.0f, 0.0f, f, f);
        float f2 = dimensionPixelSize;
        rectFDrawBoxWithShadow.offset(f2, f2);
        canvas.drawBitmap(bitmap, rect, rectFDrawBoxWithShadow, new Paint(3));
        canvas.setBitmap(null);
        return preview;
    }

    private Drawable mutateOnMainThread(final Drawable drawable) {
        try {
            return (Drawable) this.mMainThreadExecutor.submit(new Callable<Drawable>() { // from class: com.android.launcher3.WidgetPreviewLoader.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Drawable call() throws Exception {
                    return drawable.mutate();
                }
            }).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            throw new RuntimeException(e2);
        }
    }

    long[] getPackageVersion(String packageName) {
        long[] jArr;
        synchronized (this.mPackageVersions) {
            jArr = this.mPackageVersions.get(packageName);
            if (jArr == null) {
                jArr = new long[2];
                try {
                    PackageInfo packageInfo = this.mContext.getPackageManager().getPackageInfo(packageName, 8192);
                    jArr[0] = packageInfo.versionCode;
                    jArr[1] = packageInfo.lastUpdateTime;
                } catch (PackageManager.NameNotFoundException e) {
                    Log.e(TAG, "PackageInfo not found", e);
                }
                this.mPackageVersions.put(packageName, jArr);
            }
        }
        return jArr;
    }

    public class PreviewLoadTask extends AsyncTask<Void, Void, Bitmap> implements CancellationSignal.OnCancelListener {
        private final BaseActivity mActivity;
        Bitmap mBitmapToRecycle;
        private final WidgetCell mCaller;
        private final WidgetItem mInfo;
        final WidgetCacheKey mKey;
        private final int mPreviewHeight;
        private final int mPreviewWidth;
        long[] mVersions;

        PreviewLoadTask(WidgetCacheKey key, WidgetItem info, int previewWidth, int previewHeight, WidgetCell caller) {
            this.mKey = key;
            this.mInfo = info;
            this.mPreviewHeight = previewHeight;
            this.mPreviewWidth = previewWidth;
            this.mCaller = caller;
            this.mActivity = BaseActivity.fromContext(caller.getContext());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Void... params) throws Throwable {
            Bitmap bitmapCreateBitmap;
            if (isCancelled()) {
                return null;
            }
            synchronized (WidgetPreviewLoader.this.mUnusedBitmaps) {
                Iterator<Bitmap> it = WidgetPreviewLoader.this.mUnusedBitmaps.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        bitmapCreateBitmap = null;
                        break;
                    }
                    bitmapCreateBitmap = it.next();
                    if (bitmapCreateBitmap != null && bitmapCreateBitmap.isMutable() && bitmapCreateBitmap.getWidth() == this.mPreviewWidth && bitmapCreateBitmap.getHeight() == this.mPreviewHeight) {
                        WidgetPreviewLoader.this.mUnusedBitmaps.remove(bitmapCreateBitmap);
                        break;
                    }
                }
            }
            if (bitmapCreateBitmap == null) {
                bitmapCreateBitmap = Bitmap.createBitmap(this.mPreviewWidth, this.mPreviewHeight, Bitmap.Config.ARGB_8888);
            }
            Bitmap bitmap = bitmapCreateBitmap;
            if (isCancelled()) {
                return bitmap;
            }
            Bitmap fromDb = WidgetPreviewLoader.this.readFromDb(this.mKey, bitmap, this);
            if (isCancelled() || fromDb != null) {
                return fromDb;
            }
            this.mVersions = this.mInfo.activityInfo == null || this.mInfo.activityInfo.isPersistable() ? WidgetPreviewLoader.this.getPackageVersion(this.mKey.componentName.getPackageName()) : null;
            return WidgetPreviewLoader.this.generatePreview(this.mActivity, this.mInfo, bitmap, this.mPreviewWidth, this.mPreviewHeight);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(final Bitmap preview) {
            this.mCaller.applyPreview(preview);
            if (this.mVersions != null) {
                WidgetPreviewLoader.this.mWorkerHandler.post(new Runnable() { // from class: com.android.launcher3.WidgetPreviewLoader.PreviewLoadTask.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!PreviewLoadTask.this.isCancelled()) {
                            WidgetPreviewLoader.this.writeToDb(PreviewLoadTask.this.mKey, PreviewLoadTask.this.mVersions, preview);
                            PreviewLoadTask.this.mBitmapToRecycle = preview;
                        } else {
                            synchronized (WidgetPreviewLoader.this.mUnusedBitmaps) {
                                WidgetPreviewLoader.this.mUnusedBitmaps.add(preview);
                            }
                        }
                    }
                });
            } else {
                this.mBitmapToRecycle = preview;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onCancelled(final Bitmap preview) {
            if (preview != null) {
                WidgetPreviewLoader.this.mWorkerHandler.post(new Runnable() { // from class: com.android.launcher3.WidgetPreviewLoader.PreviewLoadTask.2
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (WidgetPreviewLoader.this.mUnusedBitmaps) {
                            WidgetPreviewLoader.this.mUnusedBitmaps.add(preview);
                        }
                    }
                });
            }
        }

        @Override // android.os.CancellationSignal.OnCancelListener
        public void onCancel() {
            cancel(true);
            if (this.mBitmapToRecycle != null) {
                WidgetPreviewLoader.this.mWorkerHandler.post(new Runnable() { // from class: com.android.launcher3.WidgetPreviewLoader.PreviewLoadTask.3
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (WidgetPreviewLoader.this.mUnusedBitmaps) {
                            WidgetPreviewLoader.this.mUnusedBitmaps.add(PreviewLoadTask.this.mBitmapToRecycle);
                        }
                        PreviewLoadTask.this.mBitmapToRecycle = null;
                    }
                });
            }
        }
    }

    private static final class WidgetCacheKey extends ComponentKey {
        final String size;

        public WidgetCacheKey(ComponentName componentName, UserHandle user, String size) {
            super(componentName, user);
            this.size = size;
        }

        @Override // com.android.launcher3.util.ComponentKey
        public int hashCode() {
            return this.size.hashCode() ^ super.hashCode();
        }

        @Override // com.android.launcher3.util.ComponentKey
        public boolean equals(Object o) {
            return super.equals(o) && ((WidgetCacheKey) o).size.equals(this.size);
        }
    }
}
