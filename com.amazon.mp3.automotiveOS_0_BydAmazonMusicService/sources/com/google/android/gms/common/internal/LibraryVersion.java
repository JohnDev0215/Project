package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-basement@@18.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@KeepForSdk
@Deprecated
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static LibraryVersion zzb = new LibraryVersion();
    private ConcurrentHashMap zzc = new ConcurrentHashMap();

    @VisibleForTesting
    protected LibraryVersion() {
    }

    @NonNull
    @KeepForSdk
    public static LibraryVersion getInstance() {
        return zzb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v7, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r7v0 */
    @NonNull
    @KeepForSdk
    @Deprecated
    public String getVersion(@NonNull String str) throws Throwable {
        ?? r3;
        Preconditions.checkNotEmpty(str, "Please provide a valid libraryName");
        if (this.zzc.containsKey(str)) {
            return (String) this.zzc.get(str);
        }
        Properties properties = new Properties();
        ?? r4 = 0;
        r4 = 0;
        r4 = 0;
        InputStream inputStream = null;
        try {
            try {
                InputStream resourceAsStream = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", str));
                try {
                    if (resourceAsStream != null) {
                        properties.load(resourceAsStream);
                        String property = properties.getProperty("version", null);
                        zza.v("LibraryVersion", str + " version is " + property);
                        r4 = property;
                    } else {
                        zza.w("LibraryVersion", "Failed to get app version for libraryName: " + str);
                    }
                    if (resourceAsStream != null) {
                        IOUtils.closeQuietly(resourceAsStream);
                    }
                } catch (IOException e) {
                    e = e;
                    ?? r7 = r4;
                    inputStream = resourceAsStream;
                    r3 = r7;
                    zza.e("LibraryVersion", "Failed to get app version for libraryName: " + str, e);
                    if (inputStream != null) {
                        IOUtils.closeQuietly(inputStream);
                    }
                    r4 = r3;
                } catch (Throwable th) {
                    th = th;
                    r4 = resourceAsStream;
                    if (r4 != 0) {
                        IOUtils.closeQuietly((Closeable) r4);
                    }
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                r3 = 0;
            }
            if (r4 == 0) {
                zza.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used");
                r4 = "UNKNOWN";
            }
            this.zzc.put(str, r4);
            return r4;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
