package com.byd.auto_emanual.video.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.MediaExtractor;
import com.byd.auto_emanual.ClassifyDbUtil;
import com.byd.feature.carsettings.CarStatusSettingDiCare;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class VideoDbUtil {
    private static final String DB_NAME = "media_cache.db";
    private static final int DB_VERSION = 2;
    private static volatile VideoDbUtil instance;
    private Context context;
    private SQLiteDatabase db;
    private MediaExtractor extractor;
    private MHelper mHelper;

    private VideoDbUtil(Context c) {
        this.context = c.getApplicationContext();
        MHelper mHelper = new MHelper(this.context);
        this.mHelper = mHelper;
        this.db = mHelper.getWritableDatabase();
    }

    public static VideoDbUtil getInstance(Context context) {
        synchronized (VideoDbUtil.class) {
            if (instance == null) {
                instance = new VideoDbUtil(context);
            }
        }
        return instance;
    }

    public SQLiteDatabase getDb() {
        return this.db;
    }

    private class MHelper extends SQLiteOpenHelper {
        public MHelper(Context context) {
            super(context, VideoDbUtil.DB_NAME, (SQLiteDatabase.CursorFactory) null, 2);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table media_cache (_id integer primary key autoincrement, local_path text not null, url text not null, state integer, current integer, file_size integer, time text)");
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            while (oldVersion <= newVersion) {
                if (oldVersion == 2) {
                    VideoDbUtil.this.upgradeDatabaseFrom1To2(db);
                }
                oldVersion++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upgradeDatabaseFrom1To2(SQLiteDatabase db) {
        db.execSQL("ALTER TABLE media_cache ADD COLUMN file_size integer");
    }

    public MediaCache getMediaCacheByUrl(String url) {
        Cursor cursorQuery = this.db.query("media_cache", new String[]{ClassifyDbUtil.TableClassify.COLUMN_INDEX, "local_path", "state", "current", CarStatusSettingDiCare.DiCareRecord.DICARE_TIME}, "url=?", new String[]{url}, null, null, null);
        if (cursorQuery != null) {
            mediaCache = cursorQuery.moveToNext() ? new MediaCache(cursorQuery.getInt(0), cursorQuery.getString(1), url, cursorQuery.getInt(2), cursorQuery.getInt(3), Long.parseLong(cursorQuery.getString(4))) : null;
            cursorQuery.close();
        }
        return mediaCache;
    }

    private boolean checkMediaType(String path, boolean isVideo) {
        int i;
        if (this.extractor == null) {
            this.extractor = new MediaExtractor();
        }
        try {
            try {
                this.extractor.setDataSource(path);
                String str = isVideo ? "video/" : "audio/";
                int trackCount = this.extractor.getTrackCount();
                i = 0;
                while (true) {
                    if (i >= trackCount) {
                        i = -1;
                        break;
                    }
                    if (this.extractor.getTrackFormat(i).getString("mime").startsWith(str)) {
                        break;
                    }
                    i++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                this.extractor.release();
                i = -1;
            }
            return i != -1;
        } finally {
            this.extractor.release();
        }
    }

    public int addMediaCache(String localPath, String url, int state, int fileSize) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("local_path", localPath);
        contentValues.put("url", url);
        contentValues.put("state", Integer.valueOf(state));
        contentValues.put(CarStatusSettingDiCare.DiCareRecord.DICARE_TIME, String.valueOf(System.currentTimeMillis()));
        contentValues.put("file_size", Integer.valueOf(fileSize));
        return (int) this.db.insert("media_cache", null, contentValues);
    }

    public void deleteMediaCache(int id) {
        this.db.delete("media_cache", "_id=" + id, null);
    }

    public int getMediaCacheId(String url) {
        Cursor cursorQuery = this.db.query("media_cache", new String[]{ClassifyDbUtil.TableClassify.COLUMN_INDEX}, "url=?", new String[]{url}, null, null, null);
        if (cursorQuery != null) {
            i = cursorQuery.moveToNext() ? cursorQuery.getInt(0) : -1;
            cursorQuery.close();
        }
        return i;
    }

    public void updateCachePos(String url, int current) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("current", Integer.valueOf(current));
        this.db.update("media_cache", contentValues, "url=?", new String[]{url});
    }

    public void changeCacheState(String url, int state) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("state", Integer.valueOf(state));
        this.db.update("media_cache", contentValues, "url=?", new String[]{url});
    }
}
