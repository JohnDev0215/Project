package com.android.phone;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class CallerInfoCache {
    private static final boolean DBG = false;
    private static final String LOG_TAG = "CallerInfoCache";
    private static final String[] PROJECTION = {"data1", "data4", "custom_ringtone", "send_to_voicemail"};
    private CacheAsyncTask mCacheAsyncTask;
    private final Context mContext;

    public static class CacheEntry {
        public final String customRingtone;
        public final boolean sendToVoicemail;

        public CacheEntry(String str, boolean z) {
            this.customRingtone = str;
            this.sendToVoicemail = z;
        }

        public String toString() {
            return "ringtone: " + this.customRingtone + ", " + this.sendToVoicemail;
        }
    }

    private class CacheAsyncTask extends AsyncTask<Void, Void, Void> {
        private PowerManager.WakeLock mWakeLock;

        private CacheAsyncTask() {
        }

        public void acquireWakeLockAndExecute() {
            PowerManager.WakeLock wakeLockNewWakeLock = ((PowerManager) CallerInfoCache.this.mContext.getSystemService("power")).newWakeLock(1, CallerInfoCache.LOG_TAG);
            this.mWakeLock = wakeLockNewWakeLock;
            wakeLockNewWakeLock.acquire();
            execute(new Void[0]);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            if (CallerInfoCache.DBG) {
                CallerInfoCache.log("Start refreshing cache.");
            }
            CallerInfoCache.this.refreshCacheEntry();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            super.onPostExecute(r1);
            releaseWakeLock();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onCancelled(Void r1) {
            super.onCancelled(r1);
            releaseWakeLock();
        }

        private void releaseWakeLock() {
            PowerManager.WakeLock wakeLock = this.mWakeLock;
            if (wakeLock == null || !wakeLock.isHeld()) {
                return;
            }
            this.mWakeLock.release();
        }
    }

    public static CallerInfoCache init(Context context) {
        if (DBG) {
            log("init()");
        }
        CallerInfoCache callerInfoCache = new CallerInfoCache(context);
        callerInfoCache.startAsyncCache();
        return callerInfoCache;
    }

    private CallerInfoCache(Context context) {
        this.mContext = context;
        new HashMap();
    }

    void startAsyncCache() {
        if (DBG) {
            log("startAsyncCache");
        }
        if (this.mCacheAsyncTask != null) {
            Log.w(LOG_TAG, "Previous cache task is remaining.");
            this.mCacheAsyncTask.cancel(true);
        }
        CacheAsyncTask cacheAsyncTask = new CacheAsyncTask();
        this.mCacheAsyncTask = cacheAsyncTask;
        cacheAsyncTask.acquireWakeLockAndExecute();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshCacheEntry() {
        Cursor cursor = null;
        try {
            Cursor cursorQuery = this.mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Callable.CONTENT_URI, PROJECTION, "((custom_ringtone IS NOT NULL OR send_to_voicemail=1) AND data1 IS NOT NULL)", null, null);
            if (cursorQuery != null) {
                HashMap<String, CacheEntry> map = new HashMap<>(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    String string = cursorQuery.getString(0);
                    String string2 = cursorQuery.getString(1);
                    if (string2 == null) {
                        string2 = PhoneNumberUtils.normalizeNumber(string);
                    }
                    String string3 = cursorQuery.getString(2);
                    boolean z = cursorQuery.getInt(3) == 1;
                    if (PhoneNumberUtils.isUriNumber(string)) {
                        putNewEntryWhenAppropriate(map, string, string3, z);
                    } else {
                        int length = string2.length();
                        if (length > 7) {
                            string2 = string2.substring(length - 7, length);
                        }
                        putNewEntryWhenAppropriate(map, string2, string3, z);
                    }
                }
                if (DBG) {
                    log("Caching entries are done. Total: " + map.size());
                }
            } else {
                Log.w(LOG_TAG, "cursor is null");
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private void putNewEntryWhenAppropriate(HashMap<String, CacheEntry> map, String str, String str2, boolean z) {
        if (map.containsKey(str)) {
            if (map.get(str).sendToVoicemail || !z) {
                return;
            }
            map.put(str, new CacheEntry(str2, z));
            return;
        }
        map.put(str, new CacheEntry(str2, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void log(String str) {
        Log.d(LOG_TAG, str);
    }
}
