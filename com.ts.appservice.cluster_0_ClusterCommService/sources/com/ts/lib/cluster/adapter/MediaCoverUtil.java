package com.ts.lib.cluster.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import com.ts.lib.cluster.utils.LogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public class MediaCoverUtil {
    private static final int ALBUMNAME_LENGTH = 6;
    private static final int ALBUM_MAX_NUM = 10;
    private static final String ALPHABET_STRING = "abcdefghijklmnopqrstuvwxyz";
    private static final int CYCLE_FIRST_TIME = 1;
    private static final int CYCLE_MAX_NUM = 3;
    private static final int EARLIER_FLAG = -1;
    private static final int EXIST_COVER = 1;
    private static final int FILE_NUM = 4;
    private static final String GET = "GET";
    private static final int INEXIST_COVER = 0;
    private static final String JPG = ".jpg";
    private static final int LATER_FLAG = 1;
    private static final int NUM_HUNDRED = 100;
    private static final int NUM_NINETY = 90;
    private static final int NUM_TWENTYFIVE = 25;
    private static final int SAME_TIME = 0;
    private static final int SAMPLE_SIZE = 4;
    private static final String STORE_ALBUM_PATH = "/sdcard/album";
    private static final String TAG = "MediaCoverUtil";
    private static final int TIME_OUT = 3000;
    private static volatile MediaCoverUtil sInstance = null;
    private OnDataFinishedListener mOnDataFinishedListener;
    private LoadAlbum mLoadAlbum = new LoadAlbum();
    private int mCycle = 0;

    public static synchronized MediaCoverUtil getInstance() {
        if (sInstance == null) {
            sInstance = new MediaCoverUtil();
        }
        return sInstance;
    }

    public void setOnDataFinishedListener(OnDataFinishedListener onDataFinishedListener) {
        this.mOnDataFinishedListener = onDataFinishedListener;
    }

    public void executeLoadAlbum(String url) {
        if (url == null || "".equals(url)) {
            this.mOnDataFinishedListener.onSaveFailReceived(0);
        } else {
            LoadAlbum loadAlbum = new LoadAlbum();
            loadAlbum.execute(url);
        }
    }

    public void storeByteAlbum(byte[] mediaCover) {
        if (mediaCover != null && mediaCover.length > 0) {
            Bitmap album = bytesToBimap(mediaCover);
            LogUtil.logD(TAG, "bitmap album：" + album);
            saveBitmapToSdCard(album, STORE_ALBUM_PATH);
            return;
        }
        this.mOnDataFinishedListener.onSaveFailReceived(0);
    }

    private class LoadAlbum extends AsyncTask<String, Integer, Bitmap> {
        private LoadAlbum() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(String... strings) {
            if (strings == null || strings.length <= 0) {
                MediaCoverUtil.this.mOnDataFinishedListener.onSaveFailReceived(0);
                return null;
            }
            LogUtil.logD(MediaCoverUtil.TAG, "doInBackground string[0]: " + strings[0]);
            String doInBackground = strings[0];
            if (doInBackground != null) {
                return (doInBackground.length() <= 4 || !doInBackground.substring(0, 4).equals("http")) ? MediaCoverUtil.this.loadLocalAlbum(doInBackground) : MediaCoverUtil.this.downloadPicture(doInBackground);
            }
            MediaCoverUtil.this.mOnDataFinishedListener.onSaveFailReceived(0);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bmp) {
            if (bmp != null) {
                MediaCoverUtil.this.saveBitmapToSdCard(bmp, MediaCoverUtil.STORE_ALBUM_PATH);
                super.onPostExecute(bmp);
            } else {
                MediaCoverUtil.this.mOnDataFinishedListener.onSaveFailReceived(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap downloadPicture(String surl) {
        Bitmap bmp;
        InputStream in = null;
        HttpURLConnection connection = null;
        try {
            try {
                URL url = new URL(surl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(GET);
                connection.setConnectTimeout(TIME_OUT);
                connection.setReadTimeout(TIME_OUT);
                in = connection.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                bmp = BitmapFactory.decodeStream(in, null, options);
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        exception = e;
                        exception.printStackTrace();
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                int i = this.mCycle + 1;
                this.mCycle = i;
                if (i == 1) {
                    this.mOnDataFinishedListener.onSaveFailReceived(0);
                }
                if (this.mCycle > 3) {
                    LogUtil.logD(TAG, "connect or read timeout, so the defult img will be displayed.");
                    bmp = null;
                } else {
                    LogUtil.logD(TAG, "mCycle:" + this.mCycle);
                    bmp = downloadPicture(surl);
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e2) {
                        exception = e2;
                        exception.printStackTrace();
                    }
                }
            }
            connection.disconnect();
            this.mCycle = 0;
            if (bmp != null) {
                LogUtil.logD(TAG, "dowmload success");
            } else {
                LogUtil.logD(TAG, "download failed");
            }
            return bmp;
        } catch (Throwable th) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException exception2) {
                    exception2.printStackTrace();
                }
            }
            connection.disconnect();
            this.mCycle = 0;
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap loadLocalAlbum(String url) throws IOException {
        Bitmap bitmap;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
        } catch (RuntimeException ex) {
            LogUtil.logD(TAG, "retriever.release() failed");
            ex.printStackTrace();
        }
        try {
            try {
                try {
                    retriever.setDataSource(url);
                    byte[] picture = retriever.getEmbeddedPicture();
                    if (picture == null || picture.length <= 0) {
                        LogUtil.logD(TAG, "local picture is null");
                        bitmap = null;
                    } else {
                        bitmap = bytesToBimap(picture);
                        LogUtil.logD(TAG, "get byte[] picture success");
                    }
                    retriever.release();
                } catch (RuntimeException ex2) {
                    LogUtil.logD(TAG, "RuntimeException");
                    bitmap = null;
                    ex2.printStackTrace();
                    retriever.release();
                }
            } catch (IllegalArgumentException e) {
                bitmap = null;
                LogUtil.logD(TAG, "this is illegal media file !");
                retriever.release();
            }
            return bitmap;
        } catch (Throwable th) {
            try {
                retriever.release();
            } catch (RuntimeException ex3) {
                LogUtil.logD(TAG, "retriever.release() failed");
                ex3.printStackTrace();
            }
            throw th;
        }
    }

    private Bitmap bytesToBimap(byte[] b) {
        if (b != null && b.length != 0) {
            LogUtil.logD(TAG, "bytesToBimap: excuted");
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveBitmapToSdCard(Bitmap bitmap, String path) {
        int quality = bitmap.getByteCount();
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        LogUtil.logD(TAG, "Bimap's quality:" + quality + "Bimap's height:" + height + "Bimap's width:" + width);
        File fileDirectory = new File(path);
        if (fileDirectory.exists()) {
            File[] files = fileDirectory.listFiles();
            if (files != null) {
                int albumNum = files.length;
                if (albumNum >= 10) {
                    Arrays.sort(files, new Comparator<File>() { // from class: com.ts.lib.cluster.adapter.MediaCoverUtil.1
                        @Override // java.util.Comparator
                        public int compare(File f1, File f2) {
                            long diff = f1.lastModified() - f2.lastModified();
                            if (diff > 0) {
                                return 1;
                            }
                            if (diff == 0) {
                                return 0;
                            }
                            return -1;
                        }

                        @Override // java.util.Comparator
                        public boolean equals(Object obj) {
                            return this == obj;
                        }
                    });
                    files[0].delete();
                    LogUtil.logD(TAG, "saveBitmapToSDCard: delete earliest files");
                }
            }
        } else {
            try {
                fileDirectory.mkdirs();
            } catch (Exception exception) {
                this.mOnDataFinishedListener.onSaveFailReceived(0);
                exception.printStackTrace();
            }
        }
        if (bitmap == null) {
            LogUtil.logD(TAG, "saveBitmapToSDCard: bitmap null --- save failed");
            this.mOnDataFinishedListener.onSaveFailReceived(0);
            return;
        }
        String albumName = getRandomString(6);
        String storePath = fileDirectory.getAbsolutePath() + "/" + albumName + JPG;
        File file = new File(storePath);
        if (file.exists()) {
            file.delete();
        }
        try {
            boolean flag = file.createNewFile();
            LogUtil.logD(TAG, "saveBitmapToSDCard: create new file :" + flag);
        } catch (IOException exception2) {
            this.mOnDataFinishedListener.onSaveFailReceived(0);
            exception2.printStackTrace();
        }
        LogUtil.logD(TAG, "saveBitmapToSDCard:  album local path  :" + file.getAbsolutePath());
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file);
                    if (height < NUM_NINETY || width < NUM_NINETY) {
                        if (bitmap.compress(Bitmap.CompressFormat.JPEG, NUM_HUNDRED, fileOutputStream)) {
                            LogUtil.logD(TAG, "saveCustomaryBitmapToSDCard: ----------save success-------------------");
                            this.mOnDataFinishedListener.onDataReceived(file.getAbsolutePath());
                            this.mOnDataFinishedListener.onSaveFailReceived(1);
                        }
                    } else if (bitmap.compress(Bitmap.CompressFormat.JPEG, NUM_TWENTYFIVE, fileOutputStream)) {
                        LogUtil.logD(TAG, "saveCompressBitmapToSDCard: ----------save success-------------------");
                        this.mOnDataFinishedListener.onDataReceived(file.getAbsolutePath());
                        this.mOnDataFinishedListener.onSaveFailReceived(1);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } finally {
                }
            } catch (Exception exception3) {
                exception3.printStackTrace();
                LogUtil.logD(TAG, "saveBitmapToSDCard:----------save failed------------------- ");
                this.mOnDataFinishedListener.onSaveFailReceived(0);
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
        } catch (IOException exception4) {
            exception4.printStackTrace();
        }
    }

    public static String getRandomString(int stringLength) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < stringLength; i++) {
            int index = (int) Math.floor(Math.random() * ((double) ALPHABET_STRING.length()));
            sb.append(ALPHABET_STRING.charAt(index));
        }
        return sb.toString();
    }

    public static Bitmap getBitmapFromSdCard(String getpath) {
        FileInputStream fileInputStream = null;
        try {
            try {
                fileInputStream = new FileInputStream(getpath);
                BitmapFactory.Options options = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream, null, options);
                try {
                    fileInputStream.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return bitmap;
            } catch (Exception e) {
                LogUtil.logD(TAG, "getBitmapFromSDCard: failed");
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception exception2) {
                        exception2.printStackTrace();
                    }
                }
                return null;
            }
        } catch (Throwable th) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception exception3) {
                    exception3.printStackTrace();
                }
            }
            throw th;
        }
    }
}
