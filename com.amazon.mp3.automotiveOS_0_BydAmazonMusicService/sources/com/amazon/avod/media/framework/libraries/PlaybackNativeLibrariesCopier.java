package com.amazon.avod.media.framework.libraries;

import android.content.Context;
import android.os.Build;
import androidx.core.os.EnvironmentCompat;
import com.amazon.avod.playback.capability.DeviceIdentity;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.google.common.io.Files;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class PlaybackNativeLibrariesCopier {
    private static final Charset UTF_8_CHARSET = Charset.forName("UTF8");
    private final Context mContext;
    private final List<String> mInitializedLibraryPaths;
    private boolean mIsInitialized = false;
    private final LibraryPathResolver mLibraryPathResolver;

    public PlaybackNativeLibrariesCopier(@Nonnull Context context) {
        Context context2 = (Context) Preconditions.checkNotNull(context, "context");
        this.mContext = context2;
        this.mLibraryPathResolver = LibraryPathResolver.createResolver(context2);
        this.mInitializedLibraryPaths = new ArrayList();
    }

    public boolean initialize(@Nonnull DeviceIdentity deviceIdentity) {
        Preconditions.checkNotNull(deviceIdentity, "deviceIdentity");
        List<String> orderedLibraryPaths = this.mLibraryPathResolver.getOrderedLibraryPaths();
        if (deviceIdentity.isAmazonDevice()) {
            this.mInitializedLibraryPaths.addAll(orderedLibraryPaths);
            this.mIsInitialized = true;
            return true;
        }
        String versionId = getVersionId(this.mContext);
        for (String str : orderedLibraryPaths) {
            if (str != null && !needsInitialization(str, versionId)) {
                DLog.logf("PlaybackNativeLibrariesCopier: No initialization needed for path %s", str);
                this.mInitializedLibraryPaths.add(str);
                this.mIsInitialized = true;
                return true;
            }
        }
        for (String str2 : orderedLibraryPaths) {
            if (initializePath(this.mContext, str2, versionId)) {
                DLog.logf("PlaybackNativeLibrariesCopier: Successfully initialized path %s", str2);
                this.mInitializedLibraryPaths.add(str2);
                this.mIsInitialized = true;
                return true;
            }
        }
        DLog.warnf("PlaybackNativeLibrariesCopier: No path could be initialized.");
        this.mIsInitialized = true;
        return false;
    }

    @Nonnull
    public List<String> getInitializedLibraryPaths() {
        Preconditions.checkState(this.mIsInitialized, "PlaybackNativeLibrariesCopier.getInitializedLibraryPath() called before initialization!");
        return this.mInitializedLibraryPaths;
    }

    private boolean initializePath(@Nonnull Context context, @Nullable String str, @Nonnull String str2) {
        if (str == null || !createNewDirectory(str)) {
            return false;
        }
        if (updateLibraries(context, str, "lib" + File.separator + getPreferredAbi())) {
            return createSentinelFile(str, str2);
        }
        return false;
    }

    @Nonnull
    private String getPreferredAbi() {
        String[] strArr = Build.SUPPORTED_ABIS;
        return strArr.length > 0 ? strArr[0] : "armeabi-v7a";
    }

    private boolean createNewDirectory(@Nullable String str) {
        if (str == null) {
            return false;
        }
        File file = new File(str);
        if (!removeFile(file, false)) {
            DLog.warnf("PlaybackNativeLibrariesCopier: Failed to remove the directory %s", str);
        }
        if (file.exists() || file.mkdirs()) {
            return true;
        }
        DLog.warnf("PlaybackNativeLibrariesCopier: Failed to create a directory path %s", str);
        return false;
    }

    @Nonnull
    private String getVersionId(@Nonnull Context context) throws Throwable {
        InputStreamReader inputStreamReader = null;
        try {
            try {
                InputStreamReader inputStreamReader2 = new InputStreamReader(context.getAssets().open("release_number"), UTF_8_CHARSET);
                try {
                    String string = CharStreams.toString(inputStreamReader2);
                    Closeables.closeQuietly(inputStreamReader2);
                    return string;
                } catch (IOException e) {
                    e = e;
                    inputStreamReader = inputStreamReader2;
                    DLog.errorf("PlaybackNativeLibrariesCopier: Failed to read the version id from assets: %s", e);
                    Closeables.closeQuietly(inputStreamReader);
                    return EnvironmentCompat.MEDIA_UNKNOWN;
                } catch (Throwable th) {
                    th = th;
                    inputStreamReader = inputStreamReader2;
                    Closeables.closeQuietly(inputStreamReader);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private boolean createSentinelFile(@Nonnull String str, @Nonnull String str2) throws Throwable {
        try {
            Files.write(str2.getBytes(UTF_8_CHARSET), new File(str, "libInit.so"));
            return true;
        } catch (IOException e) {
            DLog.errorf("PlaybackNativeLibrariesCopier: Failed to create the sentinel file with: %s", e);
            return false;
        }
    }

    private boolean updateLibraries(@Nonnull Context context, @Nonnull String str, @Nonnull String str2) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        ZipInputStream zipInputStream = null;
        BufferedOutputStream bufferedOutputStream2 = null;
        zipInputStream = null;
        try {
            try {
                ZipInputStream zipInputStream2 = new ZipInputStream(new BufferedInputStream(new FileInputStream(context.getApplicationInfo().sourceDir)));
                boolean z = true;
                while (true) {
                    try {
                        ZipEntry nextEntry = zipInputStream2.getNextEntry();
                        if (nextEntry != null) {
                            String name = nextEntry.getName();
                            if (name.startsWith(str2) && !nextEntry.isDirectory()) {
                                File file = new File(str + File.separator + getFileName(name));
                                File parentFile = file.getParentFile();
                                if (!parentFile.exists() && !parentFile.mkdirs()) {
                                    throw new IOException(String.format(Locale.US, "PlaybackNativeLibrariesCopier: Failed creating directory at %s", parentFile.getAbsolutePath()));
                                }
                                DLog.logf("PlaybackNativeLibrariesCopier: Updating file %s", name);
                                try {
                                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                                } catch (IOException e) {
                                    e = e;
                                    bufferedOutputStream = null;
                                } catch (Throwable th) {
                                    th = th;
                                }
                                try {
                                    try {
                                        ByteStreams.copy(zipInputStream2, bufferedOutputStream);
                                        Closeables.close(bufferedOutputStream, true);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        bufferedOutputStream2 = bufferedOutputStream;
                                        Closeables.close(bufferedOutputStream2, true);
                                        throw th;
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    DLog.warnf("PlaybackNativeLibrariesCopier: Failed copying library %s with %s", file, e);
                                    Closeables.close(bufferedOutputStream, true);
                                    z = false;
                                }
                            }
                        } else {
                            Closeables.closeQuietly(zipInputStream2);
                            return z;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        zipInputStream = zipInputStream2;
                        DLog.errorf("PlaybackNativeLibrariesCopier: Failed extracting libraries with %s", e);
                        Closeables.closeQuietly(zipInputStream);
                        return false;
                    } catch (Throwable th3) {
                        th = th3;
                        zipInputStream = zipInputStream2;
                        Closeables.closeQuietly(zipInputStream);
                        throw th;
                    }
                }
            } catch (IOException e4) {
                e = e4;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    private String getFileName(@Nonnull String str) {
        return new File(str).getName();
    }

    private boolean removeFile(@Nonnull File file, boolean z) {
        boolean z2 = true;
        if (file.exists()) {
            if (!file.isFile()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null) {
                    boolean z3 = true;
                    for (File file2 : fileArrListFiles) {
                        if (!removeFile(file2, true)) {
                            z3 = false;
                        }
                    }
                    z2 = z3;
                }
                if (z && !file.delete()) {
                    return false;
                }
            } else if (!file.delete()) {
                return false;
            }
        }
        return z2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean needsInitialization(@javax.annotation.Nonnull java.lang.String r7, @javax.annotation.Nonnull java.lang.String r8) {
        /*
            r6 = this;
            r0 = 0
            r1 = 1
            java.io.File r2 = new java.io.File     // Catch: java.io.IOException -> L40
            java.lang.String r3 = "libInit.so"
            r2.<init>(r7, r3)     // Catch: java.io.IOException -> L40
            boolean r7 = r2.exists()     // Catch: java.io.IOException -> L40
            if (r7 == 0) goto L4a
            java.nio.charset.Charset r7 = com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesCopier.UTF_8_CHARSET     // Catch: java.io.IOException -> L40
            java.lang.String r7 = com.google.common.io.Files.toString(r2, r7)     // Catch: java.io.IOException -> L40
            if (r7 == 0) goto L29
            java.util.Locale r3 = java.util.Locale.US     // Catch: java.io.IOException -> L40
            java.lang.String r4 = r7.toLowerCase(r3)     // Catch: java.io.IOException -> L40
            java.lang.String r3 = r8.toLowerCase(r3)     // Catch: java.io.IOException -> L40
            boolean r3 = r4.startsWith(r3)     // Catch: java.io.IOException -> L40
            if (r3 != 0) goto L29
            r3 = r1
            goto L2a
        L29:
            r3 = r0
        L2a:
            if (r3 == 0) goto L3f
            java.lang.String r4 = "PlaybackNativeLibrariesCopier: Found sentinel file: %s which contains version %s that's different from the expected version %s"
            r5 = 3
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.io.IOException -> L40
            java.io.File r2 = r2.getAbsoluteFile()     // Catch: java.io.IOException -> L40
            r5[r0] = r2     // Catch: java.io.IOException -> L40
            r5[r1] = r7     // Catch: java.io.IOException -> L40
            r7 = 2
            r5[r7] = r8     // Catch: java.io.IOException -> L40
            com.amazon.avod.util.DLog.warnf(r4, r5)     // Catch: java.io.IOException -> L40
        L3f:
            return r3
        L40:
            r7 = move-exception
            java.lang.Object[] r8 = new java.lang.Object[r1]
            r8[r0] = r7
            java.lang.String r7 = "PlaybackNativeLibrariesCopier: Failed to read the sentinel file: %s"
            com.amazon.avod.util.DLog.errorf(r7, r8)
        L4a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesCopier.needsInitialization(java.lang.String, java.lang.String):boolean");
    }
}
