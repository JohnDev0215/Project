package com.byd.auto_emanual.util;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.byd.auto_emanual.util.Downloader;
import com.byd.auto_emanual.video.db.MediaCache;
import com.byd.auto_emanual.video.db.VideoDbUtil;
import java.io.File;
import org.apache.shiro.util.AntPathMatcher;

/* JADX INFO: loaded from: classes.dex */
public class LocalPathUtil {
    private static final String TAG = "LocalPathUtil";

    public static String getLocalPath(Context context, String path, Downloader.IDownloadListener listener) {
        if (!URLUtil.isNetworkUrl(path)) {
            if (listener != null) {
                listener.onDownloadComplete(path, path);
            }
            return path;
        }
        MediaCache mediaCache = getMediaCache(context, path);
        String localPathImpl = getLocalPathImpl(mediaCache);
        if (!TextUtils.isEmpty(localPathImpl)) {
            MLog.d(TAG, "localPath = " + localPathImpl);
            if (listener != null) {
                listener.onDownloadComplete(path, localPathImpl);
            }
            return localPathImpl;
        }
        return downLoad(context, path, mediaCache, listener);
    }

    private static MediaCache getMediaCache(Context context, String path) {
        return VideoDbUtil.getInstance(context).getMediaCacheByUrl(path);
    }

    private static String getLocalPathImpl(MediaCache cache) {
        return (cache != null && cache.getState() == 1 && getLocalFile(cache).exists()) ? cache.getLocalPath() : "";
    }

    private static File getLocalFile(MediaCache cache) {
        return new File(cache.getLocalPath());
    }

    private static String downLoad(Context context, String path, MediaCache cache, Downloader.IDownloadListener listener) {
        String localPath;
        int current;
        if (!NetUtil.isNetAvailable(context)) {
            MLog.i(TAG, "network is unavailable!!!");
            if (listener != null) {
                listener.onNetworkUnavailable();
            }
            return path;
        }
        if (cache != null && getLocalFile(cache).exists()) {
            current = cache.getCurrent();
            localPath = cache.getLocalPath();
        } else {
            localPath = FileUtil.getCountryAndLanguagePath() + File.separator + getNameFromPath(path);
            current = 0;
        }
        startDownLoad(path, current, 0, localPath, listener);
        return localPath;
    }

    public static String getNameFromPath(String path) {
        return path.substring(path.lastIndexOf(AntPathMatcher.DEFAULT_PATH_SEPARATOR)).replaceAll("[^\\w]", "");
    }

    private static void startDownLoad(String path, int from, int to, String localPath, Downloader.IDownloadListener listener) {
        MLog.i(TAG, "startDownLoad \npath = " + path + "\nfrom = " + from + "\nto = " + to + "\nlocalPath = " + localPath);
        Downloader.getInstance().startDownload(path, from, to, localPath, listener);
    }
}
