package com.amazon.mp3.library.service.sync;

import android.app.ActivityManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.Profiler;
import com.amazonaws.util.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SyncOperation {
    static final SimpleDateFormat sDateFormat;
    protected final Context mContext;
    private final SQLiteDatabase mDb;
    private final HashSet<String> mScheduledKeys = new HashSet<>();
    private final HashMap<String, Object> mResultMap = new HashMap<>();
    private final ExecutorService mRequestExecutor = Executors.newFixedThreadPool(2);

    int getProgress() {
        return -1;
    }

    int getUpdatedResourceCount() {
        return 0;
    }

    void onFailed() {
    }

    abstract int sync() throws AccountStatusException;

    static {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN, Locale.ENGLISH);
        sDateFormat = simpleDateFormat;
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public static class AccountStatusException extends Exception {
        AccountStatusException(CirrusExceptions.CirrusException inner) {
            super(inner);
        }
    }

    SyncOperation(final Context context, final SQLiteDatabase db) {
        this.mContext = context.getApplicationContext();
        this.mDb = db;
    }

    void onFinished() {
        this.mRequestExecutor.shutdownNow();
    }

    Uri getContentUri() {
        return CirrusMediaSource.NOTIFICATION_URI;
    }

    protected Context getContext() {
        return this.mContext;
    }

    protected int getChunkSize() {
        int memoryClass = ((ActivityManager) this.mContext.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)).getMemoryClass();
        if (memoryClass > 64) {
            return 200;
        }
        return memoryClass > 32 ? 125 : 50;
    }

    protected SQLiteDatabase getDatabase() {
        return this.mDb;
    }

    protected void beginTransaction(String tag) {
        Log.verbose(tag, "beginTransaction");
        DbUtil.beginTransaction(getDatabase());
    }

    protected void endTransaction(String tag, boolean success) {
        Profiler.begin("Sync - ending db transaction");
        Log.verbose(tag, "endTransaction. success=" + success);
        if (success) {
            getDatabase().setTransactionSuccessful();
        }
        getDatabase().endTransaction();
        Profiler.end();
    }

    protected String getString(JSONObject o, String s) {
        try {
            return !o.isNull(s) ? o.getString(s) : "";
        } catch (JSONException unused) {
            return "";
        }
    }

    protected long getDate(JSONObject o, String s) {
        long time;
        String string = getString(o, s);
        if (string == null) {
            return 0L;
        }
        try {
            SimpleDateFormat simpleDateFormat = sDateFormat;
            synchronized (simpleDateFormat) {
                time = simpleDateFormat.parse(string).getTime();
            }
            return time;
        } catch (ParseException unused) {
            return 0L;
        }
    }

    protected int getInt(JSONObject o, String s, int def) {
        try {
            return o.getInt(s);
        } catch (JSONException unused) {
            return def;
        }
    }
}
