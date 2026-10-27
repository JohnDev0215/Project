package org.chromium.content.browser;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.chromium.base.Log;
import org.chromium.base.PathUtils;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class MediaResourceGetter {
    private static final MediaMetadata EMPTY_METADATA = new MediaMetadata(0, 0, 0, false);
    private static final String TAG = "cr_MediaResource";
    private final MediaMetadataRetriever mRetriever = new MediaMetadataRetriever();

    MediaResourceGetter() {
    }

    static class MediaMetadata {
        private final int mDurationInMilliseconds;
        private final int mHeight;
        private final boolean mSuccess;
        private final int mWidth;

        MediaMetadata(int i, int i2, int i3, boolean z) {
            this.mDurationInMilliseconds = i;
            this.mWidth = i2;
            this.mHeight = i3;
            this.mSuccess = z;
        }

        int getDurationInMilliseconds() {
            return this.mDurationInMilliseconds;
        }

        int getWidth() {
            return this.mWidth;
        }

        int getHeight() {
            return this.mHeight;
        }

        boolean isSuccess() {
            return this.mSuccess;
        }

        public String toString() {
            return "MediaMetadata[durationInMilliseconds=" + this.mDurationInMilliseconds + ", width=" + this.mWidth + ", height=" + this.mHeight + ", success=" + this.mSuccess + "]";
        }

        public int hashCode() {
            return ((((((this.mDurationInMilliseconds + 31) * 31) + this.mHeight) * 31) + (this.mSuccess ? 1231 : 1237)) * 31) + this.mWidth;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            MediaMetadata mediaMetadata = (MediaMetadata) obj;
            return this.mDurationInMilliseconds == mediaMetadata.mDurationInMilliseconds && this.mHeight == mediaMetadata.mHeight && this.mSuccess == mediaMetadata.mSuccess && this.mWidth == mediaMetadata.mWidth;
        }
    }

    private static MediaMetadata extractMediaMetadata(Context context, String str, String str2, String str3) {
        return new MediaResourceGetter().extract(context, str, str2, str3);
    }

    private static MediaMetadata extractMediaMetadataFromFd(int i, long j, long j2) {
        return new MediaResourceGetter().extract(i, j, j2);
    }

    MediaMetadata extract(int i, long j, long j2) {
        configure(i, j, j2);
        return doExtractMetadata();
    }

    MediaMetadata extract(Context context, String str, String str2, String str3) {
        if (!configure(context, str, str2, str3)) {
            Log.e(TAG, "Unable to configure metadata extractor", new Object[0]);
            return EMPTY_METADATA;
        }
        return doExtractMetadata();
    }

    private MediaMetadata doExtractMetadata() {
        int i;
        int i2;
        try {
            String strExtractMetadata = extractMetadata(9);
            if (strExtractMetadata == null) {
                Log.w(TAG, "missing duration metadata", new Object[0]);
                return EMPTY_METADATA;
            }
            try {
                int i3 = Integer.parseInt(strExtractMetadata);
                boolean zEquals = "yes".equals(extractMetadata(17));
                Log.d(TAG, zEquals ? "resource has video" : "resource doesn't have video");
                if (zEquals) {
                    String strExtractMetadata2 = extractMetadata(18);
                    if (strExtractMetadata2 == null) {
                        Log.w(TAG, "missing video width metadata", new Object[0]);
                        return EMPTY_METADATA;
                    }
                    try {
                        i = Integer.parseInt(strExtractMetadata2);
                        String strExtractMetadata3 = extractMetadata(19);
                        if (strExtractMetadata3 == null) {
                            Log.w(TAG, "missing video height metadata", new Object[0]);
                            return EMPTY_METADATA;
                        }
                        try {
                            i2 = Integer.parseInt(strExtractMetadata3);
                        } catch (NumberFormatException unused) {
                            Log.w(TAG, "non-numeric height: %s", strExtractMetadata3);
                            return EMPTY_METADATA;
                        }
                    } catch (NumberFormatException unused2) {
                        Log.w(TAG, "non-numeric width: %s", strExtractMetadata2);
                        return EMPTY_METADATA;
                    }
                } else {
                    i = 0;
                    i2 = 0;
                }
                MediaMetadata mediaMetadata = new MediaMetadata(i3, i, i2, true);
                Log.d(TAG, "extracted valid metadata: %s", mediaMetadata);
                return mediaMetadata;
            } catch (NumberFormatException unused3) {
                Log.w(TAG, "non-numeric duration: %s", strExtractMetadata);
                return EMPTY_METADATA;
            }
        } catch (RuntimeException e) {
            Log.e(TAG, "Unable to extract metadata: %s", e);
            return EMPTY_METADATA;
        }
    }

    boolean configure(Context context, String str, String str2, String str3) {
        try {
            URI uriCreate = URI.create(str);
            String scheme = uriCreate.getScheme();
            if (scheme == null || scheme.equals("file") || scheme.equals("app")) {
                File fileUriToFile = uriToFile(uriCreate.getPath());
                if (!fileUriToFile.exists()) {
                    Log.e(TAG, "File does not exist.", new Object[0]);
                    return false;
                }
                if (!filePathAcceptable(fileUriToFile, context)) {
                    Log.e(TAG, "Refusing to read from unsafe file location.", new Object[0]);
                    return false;
                }
                try {
                    configure(fileUriToFile.getAbsolutePath());
                    return true;
                } catch (RuntimeException e) {
                    Log.e(TAG, "Error configuring data source: %s", e);
                    return false;
                }
            }
            if (scheme.equals("content")) {
                try {
                    configure(context, Uri.parse(uriCreate.toString()));
                    return true;
                } catch (RuntimeException e2) {
                    Log.e(TAG, "Error configuring data source: %s", e2);
                    return false;
                }
            }
            if (uriCreate.getPath() != null && uriCreate.getPath().endsWith(".m3u8")) {
                return false;
            }
            if (!isLoopbackAddress(uriCreate.getHost()) && !isNetworkReliable(context)) {
                Log.w(TAG, "non-file URI can't be read due to unsuitable network conditions", new Object[0]);
                return false;
            }
            HashMap map = new HashMap();
            if (!TextUtils.isEmpty(str2)) {
                map.put("Cookie", str2);
            }
            if (!TextUtils.isEmpty(str3)) {
                map.put("User-Agent", str3);
            }
            try {
                configure(str, map);
                return true;
            } catch (RuntimeException e3) {
                Log.e(TAG, "Error configuring data source: %s", e3);
                return false;
            }
        } catch (IllegalArgumentException e4) {
            Log.e(TAG, "Cannot parse uri: %s", e4);
            return false;
        }
    }

    boolean isNetworkReliable(Context context) {
        if (context.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) {
            Log.w(TAG, "permission denied to access network state", new Object[0]);
            return false;
        }
        Integer networkType = getNetworkType(context);
        if (networkType == null) {
            return false;
        }
        int iIntValue = networkType.intValue();
        if (iIntValue == 1 || iIntValue == 9) {
            Log.d(TAG, "ethernet/wifi connection detected");
            return true;
        }
        Log.d(TAG, "no ethernet/wifi connection detected");
        return false;
    }

    private boolean isLoopbackAddress(String str) {
        return str != null && (str.equalsIgnoreCase("localhost") || str.equalsIgnoreCase("localhost.localdomain") || str.equalsIgnoreCase("localhost6") || str.equalsIgnoreCase("localhost6.localdomain6") || str.toLowerCase(Locale.US).endsWith(".localhost") || str.equals("127.0.0.1") || str.equals("[::1]"));
    }

    boolean filePathAcceptable(File file, Context context) {
        try {
            String canonicalPath = file.getCanonicalPath();
            List<String> listCanonicalize = canonicalize(getRawAcceptableDirectories(context));
            listCanonicalize.add(getExternalStorageDirectory());
            Log.d(TAG, "canonicalized file path: %s", canonicalPath);
            Iterator<String> it = listCanonicalize.iterator();
            while (it.hasNext()) {
                if (canonicalPath.startsWith(it.next())) {
                    return true;
                }
            }
            return false;
        } catch (IOException unused) {
            Log.w(TAG, "canonicalization of file path failed", new Object[0]);
            return false;
        }
    }

    File uriToFile(String str) {
        return new File(str);
    }

    Integer getNetworkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            Log.w(TAG, "no connectivity manager available", new Object[0]);
            return null;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            Log.d(TAG, "no active network");
            return null;
        }
        return Integer.valueOf(activeNetworkInfo.getType());
    }

    private List<String> getRawAcceptableDirectories(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("/mnt/sdcard/");
        arrayList.add("/sdcard/");
        arrayList.add("/data/data/" + context.getPackageName() + "/cache/");
        return arrayList;
    }

    private List<String> canonicalize(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        try {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(new File(it.next()).getCanonicalPath());
            }
            return arrayList;
        } catch (IOException unused) {
            Log.w(TAG, "canonicalization of file path failed", new Object[0]);
            return arrayList;
        }
    }

    String getExternalStorageDirectory() {
        return PathUtils.getExternalStorageDirectory();
    }

    void configure(int i, long j, long j2) {
        ParcelFileDescriptor parcelFileDescriptorAdoptFd = ParcelFileDescriptor.adoptFd(i);
        try {
            this.mRetriever.setDataSource(parcelFileDescriptorAdoptFd.getFileDescriptor(), j, j2);
            try {
                parcelFileDescriptorAdoptFd.close();
            } catch (IOException e) {
                Log.e(TAG, "Failed to close file descriptor: %s", e);
            }
        } catch (Throwable th) {
            try {
                parcelFileDescriptorAdoptFd.close();
            } catch (IOException e2) {
                Log.e(TAG, "Failed to close file descriptor: %s", e2);
            }
            throw th;
        }
    }

    void configure(String str, Map<String, String> map) {
        this.mRetriever.setDataSource(str, map);
    }

    void configure(String str) {
        this.mRetriever.setDataSource(str);
    }

    void configure(Context context, Uri uri) {
        this.mRetriever.setDataSource(context, uri);
    }

    String extractMetadata(int i) {
        return this.mRetriever.extractMetadata(i);
    }
}
