package com.byd.music.fragment.localMusic.utils;

import android.content.Context;
import android.os.Environment;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.db.LocalMusicDB;
import com.byd.music.utils.LogUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class MusicUtils {
    private static final int HANDLER_POST_SEND = 10001;
    private static final String TAG = MusicUtils.class.getName();
    private static final String SEPARATOR = File.separator;
    private static int mRetryCount = 0;
    static String dir = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static void initMusicList(Context context) {
        int i;
        LogUtil.d(TAG, "initMusicList,dir:" + dir);
        ArrayList<LocalMusicBean> arrayListQueryMusic = LocalMusicUtils.queryMusic(context, dir);
        LocalMusicDB.insertAll(arrayListQueryMusic);
        int size = arrayListQueryMusic.size();
        LogUtil.d(TAG, "getPlayList mMusicList size:" + arrayListQueryMusic.size() + "\u3000mRetryCount:" + mRetryCount);
        if (size != 0 || (i = mRetryCount) >= 20) {
            return;
        }
        mRetryCount = i + 1;
    }

    public static void getPlayList(String str) {
        File file = new File(str);
        LogUtil.d(TAG, "getPlayList:" + str);
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                LogUtil.d(TAG, "getPlayList subFiles:" + fileArrListFiles.length + "getName:" + file2.getName());
                if (file2.isDirectory()) {
                    getPlayList(file2.getAbsolutePath());
                } else if (file2.isFile() && file2.getName().endsWith(".mp3")) {
                    LocalMusicBean localMusicBean = new LocalMusicBean();
                    localMusicBean.setTitle(file2.getName().substring(0, file2.getName().length() - 4));
                    localMusicBean.setUri(file2.getPath());
                    LogUtil.d(TAG, "getPlayList setTitle:" + localMusicBean.getTitle() + "setUri:" + localMusicBean.getUri());
                    LocalMusicDB.insert(localMusicBean);
                }
            }
        }
    }

    public static String getBaseDir(Context context) {
        String str;
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)) {
            str = Environment.getExternalStorageDirectory() + File.separator;
            LogUtil.d(TAG, "getBaseDir 1");
        } else {
            str = context.getFilesDir() + File.separator;
            LogUtil.d(TAG, "getBaseDir 2");
        }
        LogUtil.d(TAG, "getBaseDir:" + str);
        return str;
    }

    public static String getAppLocalDir(Context context) {
        String str;
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)) {
            str = Environment.getExternalStorageDirectory() + File.separator;
        } else {
            str = context.getFilesDir() + File.separator;
        }
        return mkdir(str);
    }

    public static String getMusicDir(Context context) {
        return mkdir(getAppLocalDir(context) + "music" + File.separator);
    }

    public static String getLrcDir(Context context) {
        return mkdir(getAppLocalDir(context) + "lrc" + File.separator);
    }

    public static String mkdir(String str) {
        File file = new File(str);
        if (file.exists()) {
            return str;
        }
        for (int i = 0; i < 5; i++) {
            if (file.mkdirs()) {
                return str;
            }
        }
        return null;
    }

    public static String transferLongToDate(String str, Long l) {
        return new SimpleDateFormat(str).format(new Date(l.longValue()));
    }
}
