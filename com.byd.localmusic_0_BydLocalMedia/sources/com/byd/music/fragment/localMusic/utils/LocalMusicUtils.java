package com.byd.music.fragment.localMusic.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.os.SystemProperties;
import android.os.storage.DiskInfo;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Size;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.beans.AlbumBean;
import com.byd.music.beans.ArtistBean;
import com.byd.music.beans.LocalMusicBean;
import com.byd.music.constant.SPConstants;
import com.byd.music.db.LocalMusicDB;
import com.byd.music.fragment.localMusic.MusicPlayer.MusicPlayHelper;
import com.byd.music.pinyin.PinyinComparator;
import com.byd.music.style.BydUiStyleConfig;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.ShowCarModeUtil;
import com.byd.music.utils.StringSubUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class LocalMusicUtils {
    public static final String BYD_SHOW_CAR_ARTIST = "<unknown>";
    public static final int BYD_SHOW_CAR_DURATION = 19500;
    public static final int BYD_SHOW_CAR_ID = 1187;
    public static final String BYD_SHOW_CAR_NAME = "星势礼赞.wav";
    public static final String BYD_SHOW_CAR_PATH = "/storage/emulated/0/byd_show/星势礼赞.wav";
    public static String BYD_SHOW_CAR_PATH_WITH_CAR_STYLE = null;
    public static final String BYD_SHOW_CAR_TITLE = "星势礼赞";
    public static final String PATH_SDCARD_INTERNAL = "/mnt/media_rw/sd_card";
    public static final String PATH_USB_INTERNAL = "/mnt/media_rw/u_disk";
    public static final String SD_PATH = "sdcard";
    public static final String USB_PATH = "usbotg";
    private static final String TAG = LocalMusicUtils.class.getSimpleName();
    private static final Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
    private static Bitmap mCachedBit = null;
    private static final BitmapFactory.Options sBitmapOptions = new BitmapFactory.Options();
    private static Uri mMediaAlbumUri = Uri.parse("content://media/external/audio/albumart");

    private static boolean isRepeat(String str, String str2) {
        return false;
    }

    public static String queryMusicById(Context context, int i) {
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_data"}, "_id=?", new String[]{String.valueOf(i)}, null);
        cursorQuery.moveToFirst();
        String string = !cursorQuery.isAfterLast() ? cursorQuery.getString(0) : null;
        cursorQuery.close();
        return string;
    }

    public static ArrayList<LocalMusicBean> queryMusic(Context context, String str) {
        ArrayList<LocalMusicBean> arrayList = new ArrayList<>();
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, "_data like ?", new String[]{str + "%"}, "title_key");
        if (cursorQuery == null) {
            return arrayList;
        }
        cursorQuery.moveToFirst();
        while (!cursorQuery.isAfterLast()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("is_music"));
            if (string == null || !string.equals("")) {
                String string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                if (checkMusicFileExist(string2)) {
                    LocalMusicBean localMusicBean = new LocalMusicBean();
                    localMusicBean.setMusicId(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("_id")));
                    localMusicBean.setTitle(getTitleFromData(string2));
                    localMusicBean.setArtist(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("artist")));
                    localMusicBean.setArtistId(cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("artist_id")));
                    String string3 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("album"));
                    if (str.contains(string3)) {
                        string3 = BYD_SHOW_CAR_ARTIST;
                    }
                    localMusicBean.setAlbum(string3);
                    localMusicBean.setAlbumId(cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("album_id")));
                    localMusicBean.setUri(string2);
                    localMusicBean.setDuration(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("duration")));
                    localMusicBean.setCreateTime(cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("date_added")));
                    cursorQuery.getLong(3);
                    cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("album_id"));
                    LogUtil.d(TAG, "queryMusic id:" + localMusicBean.getMusicId() + " artistId=" + localMusicBean.getArtistId() + " albumId=" + localMusicBean.getAlbumId() + " ablum=" + localMusicBean.getAlbum() + " getTitle:" + StringSubUtil.subString(localMusicBean.getTitle()) + " getArtist:" + localMusicBean.getArtist() + " getLength:" + localMusicBean.getDuration());
                    if (!arrayList.contains(localMusicBean) && localMusicBean.getDuration() != 0) {
                        arrayList.add(localMusicBean);
                    }
                }
            }
            cursorQuery.moveToNext();
        }
        cursorQuery.close();
        LogUtil.d(TAG, "queryMusic results size:" + str);
        return arrayList;
    }

    public static ArrayList<LocalMusicBean> queryMusicWithType(Context context, String str, int i) {
        Cursor cursorQuery;
        ArrayList<LocalMusicBean> arrayList = new ArrayList<>();
        try {
            cursorQuery = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, "_data like ?", new String[]{str + "%"}, "title_key");
        } catch (Throwable th) {
            LogUtil.e(TAG, "exception at getCursor: " + th);
            cursorQuery = null;
        }
        StringBuilder sb = new StringBuilder();
        if (cursorQuery == null) {
            sb.append("cursor is null");
            sb.append(str);
            LogUtil.e(TAG, sb.toString());
            return arrayList;
        }
        cursorQuery.moveToFirst();
        while (!cursorQuery.isAfterLast()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("is_music"));
            if (string == null || !string.equals("")) {
                String string2 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                if (checkMusicFileExist(string2)) {
                    LocalMusicBean localMusicBean = new LocalMusicBean();
                    localMusicBean.setMusicId(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("_id")));
                    localMusicBean.setArtist(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("artist")));
                    localMusicBean.setArtistId(cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("artist_id")));
                    String string3 = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("album"));
                    if (str.contains(string3)) {
                        string3 = BYD_SHOW_CAR_ARTIST;
                    }
                    localMusicBean.setAlbum(string3);
                    localMusicBean.setAlbumId(cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("album_id")));
                    localMusicBean.setUri(string2);
                    localMusicBean.setTitle(getTitleFromData(string2));
                    localMusicBean.setDuration(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("duration")));
                    localMusicBean.setCreateTime(cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("date_added")));
                    localMusicBean.setStorageType(i);
                    if (!arrayList.contains(localMusicBean)) {
                        arrayList.add(localMusicBean);
                    }
                }
            }
            cursorQuery.moveToNext();
        }
        if (ShowCarModeUtil.isShowCarModeStyle() && i == 0) {
            LocalMusicBean localMusicBeanAddShowCarMusic = addShowCarMusic(i);
            if (!TextUtils.isEmpty(localMusicBeanAddShowCarMusic.getTitle())) {
                arrayList.add(localMusicBeanAddShowCarMusic);
                LogUtil.e(TAG, "queryMusicWithType showCarMusic bean!");
                sortShowCarMusicList(arrayList);
            }
        }
        cursorQuery.close();
        sb.append(str);
        sb.append(" queryMusicWithType results---:");
        sb.append(arrayList.size());
        sb.append(" dirName=");
        sb.append(str);
        sb.append(" type=");
        sb.append(i);
        LogUtil.e(TAG, sb.toString());
        return arrayList;
    }

    public static LocalMusicBean addShowCarMusic(int i) {
        LocalMusicBean localMusicBean = new LocalMusicBean();
        if (BydUiStyleConfig.isDenza()) {
            localMusicBean.setMusicId(1187);
            localMusicBean.setTitle(BYD_SHOW_CAR_TITLE);
            localMusicBean.setArtist(BYD_SHOW_CAR_ARTIST);
            localMusicBean.setUri(BYD_SHOW_CAR_PATH);
            localMusicBean.setDuration(BYD_SHOW_CAR_DURATION);
            localMusicBean.setStorageType(i);
            BYD_SHOW_CAR_PATH_WITH_CAR_STYLE = BYD_SHOW_CAR_PATH;
        } else {
            LogUtil.e(TAG, "addShowCarMusic style error" + BydUiStyleConfig.getUiStyle());
        }
        return localMusicBean;
    }

    public static void sortShowCarMusicList(List<LocalMusicBean> list) {
        LocalMusicBean localMusicBeanAddShowCarMusic = addShowCarMusic(0);
        if (TextUtils.isEmpty(localMusicBeanAddShowCarMusic.getTitle())) {
            return;
        }
        if (list.indexOf(localMusicBeanAddShowCarMusic) != -1) {
            list.remove(localMusicBeanAddShowCarMusic);
            list.sort(new PinyinComparator());
            list.add(0, localMusicBeanAddShowCarMusic);
            return;
        }
        list.sort(new PinyinComparator());
    }

    public static ArrayList<LocalMusicBean> loadAllMusic(Context context) {
        ArrayList<LocalMusicBean> arrayList = new ArrayList<>();
        Cursor cursorQuery = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_id", "artist", "artist_id", "album", "album_id", "_display_name", "duration", "title", "date_added"}, null, null, null);
        LogUtil.e(TAG, "loadAllMusic: " + cursorQuery);
        if (cursorQuery == null) {
            return arrayList;
        }
        cursorQuery.moveToFirst();
        while (!cursorQuery.isAfterLast()) {
            LocalMusicBean localMusicBean = new LocalMusicBean();
            localMusicBean.setMusicId(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("_id")));
            localMusicBean.setTitle(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("title")));
            localMusicBean.setArtist(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("artist")));
            localMusicBean.setArtistId(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("artist_id")));
            localMusicBean.setAlbum(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("album")));
            localMusicBean.setAlbumId(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("album_id")));
            localMusicBean.setUri(cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data")));
            localMusicBean.setDuration(cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("duration")));
            localMusicBean.setCreateTime(cursorQuery.getLong(cursorQuery.getColumnIndexOrThrow("date_added")));
            cursorQuery.getLong(3);
            cursorQuery.getInt(cursorQuery.getColumnIndexOrThrow("album_id"));
            LogUtil.d(TAG, "loadAllMusic queryMusic id:" + localMusicBean.getMusicId() + " artistId=" + localMusicBean.getArtistId() + " albumId=" + localMusicBean.getAlbumId() + " ablum=" + localMusicBean.getAlbum() + " getTitle:" + localMusicBean.getTitle() + " getArtist:" + localMusicBean.getArtist() + " getLength:" + localMusicBean.getDuration());
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("loadAllMusic: ");
            sb.append(localMusicBean.getStorageType());
            LogUtil.e(str, sb.toString());
            if (!arrayList.contains(localMusicBean) && localMusicBean.getDuration() != 0) {
                arrayList.add(localMusicBean);
            }
            cursorQuery.moveToNext();
        }
        cursorQuery.close();
        LogUtil.d(TAG, "queryMusic results---:" + arrayList.size());
        return arrayList;
    }

    public static Map<Long, ArtistBean> loadAllArtist(List<LocalMusicBean> list, int i) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList<ArtistBean> arrayList = new ArrayList();
        Iterator<LocalMusicBean> it = list.iterator();
        while (it.hasNext()) {
            ArtistBean artistBeanFrom = ArtistBean.from(it.next(), i);
            if (artistBeanFrom != null) {
                arrayList.add(artistBeanFrom);
            }
        }
        for (ArtistBean artistBean : arrayList) {
            linkedHashMap.put(Long.valueOf(artistBean.getId()), artistBean);
        }
        return linkedHashMap;
    }

    public static List<ArtistBean> transformDesignatedArtistList(List<LocalMusicBean> list, int i) {
        ArrayList arrayList = new ArrayList();
        Iterator<LocalMusicBean> it = list.iterator();
        while (it.hasNext()) {
            ArtistBean artistBeanFrom = ArtistBean.from(it.next(), i);
            if (artistBeanFrom != null) {
                arrayList.add(artistBeanFrom);
            }
        }
        return arrayList;
    }

    public static Map<String, AlbumBean> loadAllAlbums(Context context) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList<AlbumBean> arrayList = new ArrayList();
        Cursor cursorQuery = null;
        try {
            cursorQuery = context.getContentResolver().query(AlbumBean.QUERY_URI, AlbumBean.QUERY_PROJECTION, null, null, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    AlbumBean albumBeanFrom = AlbumBean.from(cursorQuery);
                    if (albumBeanFrom != null) {
                        arrayList.add(albumBeanFrom);
                    }
                }
                cursorQuery.close();
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "exception at loadAllAlbums: " + e);
        } catch (Throwable unused) {
            cursorQuery.close();
        }
        for (AlbumBean albumBean : arrayList) {
            linkedHashMap.put(albumBean.getName(), albumBean);
        }
        return linkedHashMap;
    }

    public static Map<Long, AlbumBean> loadAllAlbumsByPath(List<LocalMusicBean> list, int i) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList<AlbumBean> arrayList = new ArrayList();
        Iterator<LocalMusicBean> it = list.iterator();
        while (it.hasNext()) {
            AlbumBean albumBeanFrom = AlbumBean.from(it.next(), i);
            if (albumBeanFrom != null) {
                arrayList.add(albumBeanFrom);
            }
        }
        for (AlbumBean albumBean : arrayList) {
            linkedHashMap.put(Long.valueOf(albumBean.getId()), albumBean);
        }
        return linkedHashMap;
    }

    public static ArrayList<LocalMusicBean> removeDuplicate(ArrayList<LocalMusicBean> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Before removeDuplicate size=");
        sb.append(arrayList.size());
        for (int i = 0; i < arrayList.size() - 1; i++) {
            for (int size = arrayList.size() - 1; size > i; size--) {
                if (arrayList.get(i).getTitle() != null && arrayList.get(i).getTitle().equals(arrayList.get(size).getTitle())) {
                    arrayList.remove(size);
                }
            }
        }
        sb.append(" After removeDuplicate=");
        sb.append(arrayList.size());
        LogUtil.e(TAG, sb.toString());
        return arrayList;
    }

    private static Bitmap getAlbumArt(Context context, int i) {
        LogUtil.d(TAG, "getAlbumArt album_id:" + i);
        if (i < 0) {
            return BitmapFactory.decodeResource(context.getResources(), R.drawable.album_default_248);
        }
        Cursor cursorQuery = context.getContentResolver().query(Uri.parse("content://media/external/audio/albums/" + Integer.toString(i)), new String[]{"album_art"}, null, null, null);
        String string = null;
        LogUtil.d(TAG, "getAlbumArt album_id  1  getCount:" + cursorQuery.getCount() + " getColumnCount:" + cursorQuery.getColumnCount());
        if (cursorQuery.getCount() > 0 && cursorQuery.getColumnCount() > 0) {
            cursorQuery.moveToNext();
            string = cursorQuery.getString(0);
        }
        cursorQuery.close();
        LogUtil.d(TAG, "getAlbumArt album_id  2:" + i);
        if (string != null) {
            LogUtil.d(TAG, "getAlbumArt album_id  3:" + i);
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(string);
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("getAlbumArt album_id  4:");
            sb.append(bitmapDecodeFile != null);
            LogUtil.d(str, sb.toString());
            return bitmapDecodeFile;
        }
        LogUtil.d(TAG, "getAlbumArt album_id  5:" + i);
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.album_default_248);
    }

    private static Bitmap getArtworkFromFile(Context context, long j, long j2) {
        if (j2 < 0 && j < 0) {
            throw new IllegalArgumentException("Must specify an album or a song id");
        }
        Bitmap bitmapDecodeFileDescriptor = null;
        try {
            if (j2 < 0) {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(Uri.parse("content://media/external/audio/media/" + j + "/albumart"), "r");
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
                }
            } else {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor2 = context.getContentResolver().openFileDescriptor(ContentUris.withAppendedId(sArtworkUri, j2), "r");
                if (parcelFileDescriptorOpenFileDescriptor2 != null) {
                    bitmapDecodeFileDescriptor = BitmapFactory.decodeFileDescriptor(parcelFileDescriptorOpenFileDescriptor2.getFileDescriptor());
                }
            }
        } catch (FileNotFoundException unused) {
        }
        if (bitmapDecodeFileDescriptor != null) {
            mCachedBit = bitmapDecodeFileDescriptor;
        }
        return bitmapDecodeFileDescriptor;
    }

    public static Bitmap getAlbumPictureFromPath(Context context, String str) {
        try {
            try {
                return ThumbnailUtils.createAudioThumbnail(new File(str), new Size(150, 150), null);
            } catch (IOException e) {
                e.printStackTrace();
                LogUtil.e("getImageBitmap error");
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    public static Bitmap getArtwork(Context context, long j, long j2, boolean z, String str) throws Throwable {
        Bitmap artworkFromFile;
        LogUtil.d(TAG, "getArtwork song_id:" + j + " album_id:" + j2);
        InputStream inputStream = null;
        if (j2 < 0) {
            if (j >= 0 && (artworkFromFile = getArtworkFromFile(context, j, -1L)) != null) {
                return artworkFromFile;
            }
            if (z) {
                return getDefaultArtwork(context);
            }
            return null;
        }
        ContentResolver contentResolver = context.getContentResolver();
        Uri uriWithAppendedId = ContentUris.withAppendedId(sArtworkUri, j2);
        try {
            if (uriWithAppendedId == null) {
                return null;
            }
            try {
                LogUtil.d(TAG, "getArtwork 1");
                InputStream inputStreamOpenInputStream = contentResolver.openInputStream(uriWithAppendedId);
                try {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream, null, sBitmapOptions);
                    if (inputStreamOpenInputStream != null) {
                        try {
                            inputStreamOpenInputStream.close();
                        } catch (IOException unused) {
                        }
                    }
                    return bitmapDecodeStream;
                } catch (FileNotFoundException e) {
                    e = e;
                    inputStream = inputStreamOpenInputStream;
                    LogUtil.d(TAG, "getArtwork FileNotFoundException:" + e.getMessage());
                    Bitmap artworkFromFile2 = getArtworkFromFile(context, j, j2);
                    if (artworkFromFile2 != null) {
                        if (artworkFromFile2.getConfig() == null && (artworkFromFile2 = artworkFromFile2.copy(Bitmap.Config.RGB_565, false)) == null && z) {
                            Bitmap defaultArtwork = getDefaultArtwork(context);
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException unused2) {
                                }
                            }
                            return defaultArtwork;
                        }
                    } else if (z) {
                        artworkFromFile2 = getDefaultArtwork(context);
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused3) {
                        }
                    }
                    return artworkFromFile2;
                } catch (Throwable th) {
                    th = th;
                    inputStream = inputStreamOpenInputStream;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException unused4) {
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static Bitmap getDefaultArtwork(Context context) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.album_default_248, options);
    }

    public static Uri getAlbumUri(long j) {
        return Uri.withAppendedPath(mMediaAlbumUri, String.valueOf(j));
    }

    private static boolean checkIsFirstStart() {
        if (TextUtils.isEmpty(SystemProperties.get("sys.mediacenter.byd"))) {
            LogUtil.e("initMusicData first start, yes.");
            SystemProperties.set("sys.mediacenter.byd", "byd_music");
            return true;
        }
        LogUtil.e("initMusicData first start, no no no.");
        return false;
    }

    public static void initMusicData(Boolean bool) {
        String[] storagePath = getStoragePath(Utils.getApp().getApplicationContext());
        boolean z = storagePath[1] != null;
        boolean z2 = storagePath[2] != null;
        SPUtils.getInstance().put(SPConstants.SP_EXISTED_SD, z);
        SPUtils.getInstance().put(SPConstants.SP_EXISTED_USB, z2);
        if (z) {
            MusicPlayHelper.getInstance().setIsSDPlug(true);
        }
        if (z2) {
            MusicPlayHelper.getInstance().setIsUsbPlug(true);
        }
        if (!checkIsFirstStart()) {
            for (int i = 0; i < 3; i++) {
                if (!TextUtils.isEmpty(storagePath[i])) {
                    ArrayList<LocalMusicBean> arrayListQueryMusicWithType = queryMusicWithType(Utils.getApp().getApplicationContext(), storagePath[i], i);
                    int size = arrayListQueryMusicWithType.size();
                    List<LocalMusicBean> localMusicByStorageType = LocalMusicDB.getLocalMusicByStorageType(i);
                    LogUtil.e(TAG, "initMusicData-> tempList size=" + size + " i=" + i + " path=" + storagePath[i] + " dbSize=" + localMusicByStorageType.size() + " isInternalStorage=" + bool);
                    if (size > 0) {
                        LocalMusicDB.updateAll(arrayListQueryMusicWithType, localMusicByStorageType);
                    } else if (i != 0 || localMusicByStorageType.size() <= 0 || !checkMusicFileExist(localMusicByStorageType.get(0).getUri())) {
                        LocalMusicDB.deleteByStorageType(i);
                    }
                }
            }
        } else if (ShowCarModeUtil.isShowCarModeStyle() && !TextUtils.isEmpty(storagePath[0])) {
            LocalMusicDB.updateAll(queryMusicWithType(Utils.getApp().getApplicationContext(), storagePath[0], 0), LocalMusicDB.getLocalMusicByStorageType(0));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" isInternalStorage=");
        sb.append(bool);
        LocalMusicBean currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp();
        if (currentMusicFromSp != null && checkMusicFileExist(currentMusicFromSp.getUri()) && currentMusicFromSp.getStorageType() != 0) {
            if (MusicPlayHelper.getInstance().currentLocalMusic.get() == null) {
                MusicPlayHelper.getInstance().updateCurrentMusicInfo();
            }
            int size2 = MusicPlayHelper.getInstance().getLocalMusicBeanList().size();
            sb.append(" playListSize=");
            sb.append(size2);
            if (size2 <= 0) {
                MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(currentMusicFromSp.getStorageType()));
            }
        }
        LogUtil.e(TAG, sb.toString());
    }

    public static void initMusicDataByPath(String str) {
        String[] storagePath = getStoragePath(Utils.getApp().getApplicationContext());
        setInternalStoragePlugConfig(storagePath);
        int i = 0;
        while (true) {
            if (i >= 3) {
                break;
            }
            if (str != null) {
                if (storagePath[i] != null && str.contains(storagePath[i])) {
                    ArrayList<LocalMusicBean> arrayListQueryMusicWithType = queryMusicWithType(Utils.getApp().getApplicationContext(), storagePath[i], i);
                    int size = arrayListQueryMusicWithType.size();
                    List<LocalMusicBean> localMusicByStorageType = LocalMusicDB.getLocalMusicByStorageType(i);
                    LogUtil.e(TAG, "initMusicDataByPath-> tempList size=" + size + " i=" + i + " path=" + storagePath[i] + " dbSize=" + localMusicByStorageType.size());
                    if (size > 0) {
                        LocalMusicDB.updateAll(arrayListQueryMusicWithType, localMusicByStorageType);
                    } else {
                        LocalMusicDB.deleteByStorageType(i);
                    }
                }
            } else if (storagePath[i] != null) {
                ArrayList<LocalMusicBean> arrayListQueryMusicWithType2 = queryMusicWithType(Utils.getApp().getApplicationContext(), storagePath[i], i);
                int size2 = arrayListQueryMusicWithType2.size();
                List<LocalMusicBean> localMusicByStorageType2 = LocalMusicDB.getLocalMusicByStorageType(i);
                if (size2 > 0) {
                    LocalMusicDB.updateAll(arrayListQueryMusicWithType2, localMusicByStorageType2);
                } else {
                    LocalMusicDB.deleteByStorageType(i);
                }
            }
            i++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(" initMusicDataByPath scanUriPath=");
        sb.append(str);
        int i2 = OnLineMusic.musicEngine;
        boolean z = MusicPlayHelper.getInstance().getIsSDPlug().get();
        boolean z2 = MusicPlayHelper.getInstance().getIsUsbPlug().get();
        int iConvertMusicEngineToStorageType = MusicPlayHelper.getInstance().convertMusicEngineToStorageType(OnLineMusic.musicEngine);
        sb.append(" musicEngine=");
        sb.append(i2);
        sb.append(" convertStorageType=");
        sb.append(iConvertMusicEngineToStorageType);
        sb.append(" isSDPlug=");
        sb.append(z);
        sb.append(" isUSBPlug=");
        sb.append(z2);
        List<LocalMusicBean> localMusicBeanList = MusicPlayHelper.getInstance().getLocalMusicBeanList();
        localMusicBeanList.size();
        sb.append(" tmpListSize=");
        sb.append(localMusicBeanList.size());
        if (iConvertMusicEngineToStorageType == -1) {
            LocalMusicBean currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp();
            if (currentMusicFromSp != null && checkMusicFileExist(currentMusicFromSp.getUri()) && localMusicBeanList.size() <= 0) {
                sb.append(" init and update!");
                MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(currentMusicFromSp.getStorageType()));
            }
            LogUtil.e(TAG, sb.toString());
            return;
        }
        if (i2 == 4) {
            if (z2) {
                if (!localMusicBeanList.isEmpty()) {
                    LocalMusicBean localMusicBean = localMusicBeanList.get(0);
                    if (localMusicBean.getStorageType() != iConvertMusicEngineToStorageType || !checkMusicFileExist(localMusicBean.getUri())) {
                        sb.append(" tmpLocalStorageType=");
                        sb.append(localMusicBean.getStorageType());
                        sb.append(" invalid url list and init music list");
                        MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(iConvertMusicEngineToStorageType));
                    } else {
                        sb.append(" already init playList!");
                    }
                } else {
                    sb.append(" reload playList");
                    MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(iConvertMusicEngineToStorageType));
                }
                sb.append(" and return usb!");
                if (MusicPlayHelper.getInstance().currentLocalMusicMaps.get(Integer.valueOf(iConvertMusicEngineToStorageType)) == null) {
                    MusicPlayHelper.getInstance().updateCurrentMusicInfo();
                }
            } else {
                MusicPlayHelper.getInstance().getLocalMusicBeanList().clear();
                sb.append(" clear playList and return usb!");
            }
            LogUtil.e(TAG, sb.toString());
            return;
        }
        if (i2 == 3) {
            if (z) {
                if (!localMusicBeanList.isEmpty()) {
                    LocalMusicBean localMusicBean2 = localMusicBeanList.get(0);
                    if (localMusicBean2.getStorageType() != iConvertMusicEngineToStorageType || !checkMusicFileExist(localMusicBean2.getUri())) {
                        sb.append(" tmpLocalStorageType=");
                        sb.append(localMusicBean2.getStorageType());
                        sb.append(" invalid url list and init music list");
                        MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(iConvertMusicEngineToStorageType));
                    } else {
                        sb.append(" already init playList!");
                    }
                } else {
                    sb.append(" reload playList");
                    MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(iConvertMusicEngineToStorageType));
                }
                sb.append(" and return sd!");
                if (MusicPlayHelper.getInstance().currentLocalMusicMaps.get(Integer.valueOf(iConvertMusicEngineToStorageType)) == null) {
                    MusicPlayHelper.getInstance().updateCurrentMusicInfo();
                }
            } else {
                MusicPlayHelper.getInstance().getLocalMusicBeanList().clear();
                sb.append(" clear playList and return sd!");
            }
            LogUtil.e(TAG, sb.toString());
            return;
        }
        if (i2 == 5) {
            if (!localMusicBeanList.isEmpty()) {
                LocalMusicBean localMusicBean3 = localMusicBeanList.get(0);
                if (localMusicBean3.getStorageType() != iConvertMusicEngineToStorageType || !checkMusicFileExist(localMusicBean3.getUri())) {
                    sb.append(" tmpLocalStorageType=");
                    sb.append(localMusicBean3.getStorageType());
                    sb.append(" invalid url list and init music list");
                    MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(iConvertMusicEngineToStorageType));
                } else {
                    sb.append(" already init playList");
                }
                sb.append(" and return local!");
            } else {
                sb.append(" reload playList and return local!");
                MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(iConvertMusicEngineToStorageType));
                if (MusicPlayHelper.getInstance().currentLocalMusicMaps.get(Integer.valueOf(iConvertMusicEngineToStorageType)) == null) {
                    MusicPlayHelper.getInstance().updateCurrentMusicInfo();
                }
            }
            LogUtil.e(TAG, sb.toString());
            return;
        }
        LocalMusicBean currentMusicFromSp2 = MusicPlayHelper.getInstance().getCurrentMusicFromSp();
        sb.append(" localMusicSp isExist=");
        sb.append(currentMusicFromSp2 != null);
        if (currentMusicFromSp2 != null) {
            sb.append(" storageType=");
            sb.append(currentMusicFromSp2.getStorageType());
        }
        if (currentMusicFromSp2 != null && checkMusicFileExist(currentMusicFromSp2.getUri())) {
            if (currentMusicFromSp2.getStorageType() != 0 && MusicPlayHelper.getInstance().currentLocalMusic.get() == null) {
                MusicPlayHelper.getInstance().updateCurrentMusicInfo();
            }
            int size3 = MusicPlayHelper.getInstance().getLocalMusicBeanList().size();
            sb.append(" playListSize=");
            sb.append(size3);
            if (size3 <= 0) {
                MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(currentMusicFromSp2.getStorageType()));
            }
        } else {
            List<LocalMusicBean> localMusicBeanList2 = MusicPlayHelper.getInstance().getLocalMusicBeanList();
            sb.append(" tmpList=");
            sb.append(localMusicBeanList2.size());
            if (localMusicBeanList2.size() > 0) {
                LocalMusicBean localMusicBean4 = localMusicBeanList2.get(0);
                if (!checkMusicFileExist(localMusicBean4.getUri())) {
                    sb.append(" storageType=");
                    sb.append(localMusicBean4.getStorageType());
                    sb.append(" invalid url list and init music list!!!");
                    MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(localMusicBean4.getStorageType()));
                }
            }
        }
        LogUtil.e(TAG, sb.toString());
    }

    private static void setInternalStoragePlugConfig(String[] strArr) {
        StringBuilder sb = new StringBuilder("setInternalStoragePlugConfig: ");
        if (strArr == null || strArr.length < 3) {
            sb.append(" path is invalid and return !");
            LogUtil.e(TAG, sb.toString());
            return;
        }
        boolean z = strArr[1] != null;
        boolean z2 = strArr[2] != null;
        SPUtils.getInstance().put(SPConstants.SP_EXISTED_SD, z);
        SPUtils.getInstance().put(SPConstants.SP_EXISTED_USB, z2);
        if (z) {
            MusicPlayHelper.getInstance().setIsSDPlug(true);
        }
        if (z2) {
            MusicPlayHelper.getInstance().setIsUsbPlug(true);
        }
        sb.append(" isSdPlug= ");
        sb.append(z);
        sb.append(" isUSBPlug= ");
        sb.append(z2);
        LogUtil.e(TAG, sb.toString());
    }

    public static void initMusicDataByPath(Boolean bool, String str, int i) {
        List<LocalMusicBean> localMusicByStorageType;
        if (bool.booleanValue()) {
            localMusicByStorageType = LocalMusicDB.getSongIdList();
        } else {
            localMusicByStorageType = LocalMusicDB.getLocalMusicByStorageType(i);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<LocalMusicBean> arrayListQueryMusicWithType = queryMusicWithType(Utils.getApp().getApplicationContext(), str, i);
        if (arrayListQueryMusicWithType.size() > 0) {
            arrayList.addAll(arrayListQueryMusicWithType);
        } else {
            LocalMusicDB.deleteByStorageType(i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("initMusicDataByPath:querySize=");
        sb.append(arrayList.size());
        sb.append(" dbsize=");
        sb.append(localMusicByStorageType.size());
        sb.append(" storageType：");
        sb.append(i);
        if (arrayList.size() > 0) {
            LocalMusicDB.updateAll(arrayList, localMusicByStorageType);
        }
        if (MusicPlayHelper.getInstance().getMusicPlayer() != null) {
            MusicPlayHelper.getInstance().getMusicPlayer().setPlayErrorTime(0);
        }
        LocalMusicBean currentMusicFromSp = MusicPlayHelper.getInstance().getCurrentMusicFromSp();
        if (currentMusicFromSp != null && checkMusicFileExist(currentMusicFromSp.getUri()) && currentMusicFromSp.getStorageType() == i) {
            int size = MusicPlayHelper.getInstance().getLocalMusicBeanList().size();
            sb.append(" playListSize=");
            sb.append(size);
            if (size <= 1) {
                MusicPlayHelper.getInstance().init(LocalMusicDB.getLocalMusicByStorageType(currentMusicFromSp.getStorageType()));
            }
        }
        LogUtil.e(TAG, sb.toString());
    }

    public static String[] getStoragePath(Context context) {
        String[] strArr = new String[3];
        try {
            List<VolumeInfo> volumes = ((StorageManager) context.getSystemService("storage")).getVolumes();
            if (volumes != null) {
                for (VolumeInfo volumeInfo : volumes) {
                    if (volumeInfo.getType() == 2) {
                        if (volumeInfo.getPath() != null) {
                            strArr[0] = volumeInfo.getPath().getAbsolutePath();
                        }
                    } else if (volumeInfo.getType() == 0) {
                        DiskInfo diskInfo = volumeInfo.disk;
                        String str = volumeInfo.internalPath;
                        if (volumeInfo.getPath() != null) {
                            if (volumeInfo.getDeviceType().equals(SD_PATH)) {
                                strArr[1] = volumeInfo.getPath().getAbsolutePath();
                            } else if (volumeInfo.getDeviceType().equals(USB_PATH)) {
                                strArr[2] = volumeInfo.getPath().getAbsolutePath();
                            }
                        }
                    }
                }
            }
            LogUtil.e(TAG, "path EMULATED=" + strArr[0] + " sd=" + strArr[1] + " usb=" + strArr[2]);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return strArr;
    }

    public static int addVolume(VolumeInfo volumeInfo) {
        if (volumeInfo.getType() == 0) {
            if (volumeInfo.getDeviceType().equals(SD_PATH)) {
                return 1;
            }
            if (volumeInfo.getDeviceType().equals(USB_PATH)) {
                return 2;
            }
        }
        return 0;
    }

    public static String[] getStoragePath(Context context, Boolean bool) {
        String[] strArr = {"test", "test"};
        try {
            List<VolumeInfo> volumes = ((StorageManager) context.getSystemService("storage")).getVolumes();
            if (volumes != null) {
                for (VolumeInfo volumeInfo : volumes) {
                    if (volumeInfo.getType() == 0) {
                        DiskInfo diskInfo = volumeInfo.disk;
                        String str = volumeInfo.internalPath;
                        Thread.sleep(100L);
                        if (volumeInfo.getDeviceType().equals(SD_PATH)) {
                            strArr[0] = volumeInfo.getPath().getAbsolutePath();
                        } else if (volumeInfo.getDeviceType().equals(USB_PATH)) {
                            strArr[1] = volumeInfo.getPath().getAbsolutePath();
                        }
                    }
                }
            }
            LogUtil.e(TAG, "path storage=" + strArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return strArr;
    }

    public static boolean checkMusicFileExist(String str) {
        boolean zExists = new File(str).exists();
        if (zExists) {
            return zExists;
        }
        LogUtil.d(TAG, "checkMusicFileExist file is not exist:" + StringSubUtil.subString(str));
        if (ShowCarModeUtil.isShowCarFileExist(str)) {
            return true;
        }
        return zExists;
    }

    public static boolean checkMusicFileLength(String str) {
        if (new File(str).length() > 0) {
            return true;
        }
        LogUtil.e(TAG, "checkMusicFileExist checkMusicFileLength < 0:" + StringSubUtil.subString(str));
        return false;
    }

    public static String getAlbumUriById(int i) {
        String string;
        Cursor cursorQuery = Utils.getApp().getApplicationContext().getContentResolver().query(Uri.parse("content://media/external/audio/albums/" + Integer.toString(i)), new String[]{"album_art"}, null, null, null);
        if (cursorQuery.getCount() <= 0 || cursorQuery.getColumnCount() <= 0) {
            string = null;
        } else {
            cursorQuery.moveToNext();
            string = cursorQuery.getString(0);
        }
        cursorQuery.close();
        LogUtil.e(TAG, "getAlbumUriById: " + string);
        return string;
    }

    public static String getTitleFromData(String str) {
        if (str == null) {
            return "";
        }
        String strSubstring = null;
        int iLastIndexOf = str.lastIndexOf(47) + 1;
        int iLastIndexOf2 = str.lastIndexOf(46);
        if (iLastIndexOf != 0 && iLastIndexOf2 != -1 && iLastIndexOf2 > iLastIndexOf) {
            strSubstring = str.substring(iLastIndexOf, iLastIndexOf2);
        }
        return strSubstring != null ? strSubstring : "";
    }
}
