package com.amazon.device.ads;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.device.ads.JSONUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class Settings {
    private static final String LOGTAG = "Settings";
    private static Settings instance = new Settings();
    private final ConcurrentHashMap<String, Value> cache;
    private DebugProperties debugProperties;
    private JSONUtils.JSONUtilities jsonUtilities;
    private LinkedBlockingQueue<SettingsListener> listeners;
    private final MobileAdsLogger logger;
    private final CountDownLatch settingsLoadedLatch;
    private SharedPreferences sharedPreferences;
    private final ReentrantLock writeToSharedPreferencesLock;

    public interface SettingsListener {
        void settingsLoaded();
    }

    public Settings() {
        this(new JSONUtils.JSONUtilities(), DebugProperties.getInstance());
    }

    Settings(JSONUtils.JSONUtilities jSONUtilities, DebugProperties debugProperties) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.listeners = new LinkedBlockingQueue<>();
        this.writeToSharedPreferencesLock = new ReentrantLock();
        this.settingsLoadedLatch = new CountDownLatch(1);
        this.cache = new ConcurrentHashMap<>();
        this.jsonUtilities = jSONUtilities;
        this.debugProperties = debugProperties;
    }

    public static Settings getInstance() {
        return instance;
    }

    void contextReceived(Context context) {
        if (context == null) {
            return;
        }
        beginFetch(context);
    }

    void beginFetch(final Context context) {
        ThreadUtils.scheduleRunnable(new Runnable() { // from class: com.amazon.device.ads.Settings.1
            @Override // java.lang.Runnable
            public void run() {
                Settings.this.fetchSharedPreferences(context);
            }
        });
    }

    public boolean isSettingsLoaded() {
        return this.sharedPreferences != null;
    }

    public void listenForSettings(SettingsListener settingsListener) {
        if (isSettingsLoaded()) {
            settingsListener.settingsLoaded();
            return;
        }
        try {
            this.listeners.put(settingsListener);
        } catch (InterruptedException e) {
            this.logger.e("Interrupted exception while adding listener: %s", e.getMessage());
        }
    }

    SharedPreferences getSharedPreferencesFromContext(Context context) {
        return context.getSharedPreferences("AmazonMobileAds", 0);
    }

    private void putSetting(String str, Value value) {
        if (value.value == null) {
            this.logger.w("Could not set null value for setting: %s", str);
            return;
        }
        putSettingWithNoFlush(str, value);
        if (value.isTransientData || !isSettingsLoaded()) {
            return;
        }
        flush();
    }

    private void putSettingWithNoFlush(String str, Value value) {
        if (value.value == null) {
            this.logger.w("Could not set null value for setting: %s", str);
        } else {
            this.cache.put(str, value);
        }
    }

    void readSharedPreferencesIntoCache(SharedPreferences sharedPreferences) {
        cacheAdditionalEntries(sharedPreferences.getAll());
    }

    void cacheAdditionalEntries(Map<String, ?> map) {
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key != null && !this.cache.containsKey(key)) {
                Object value = entry.getValue();
                if (value != null) {
                    this.cache.put(key, new Value(value.getClass(), value));
                } else {
                    this.logger.w("Could not cache null value for SharedPreferences setting: %s", key);
                }
            }
        }
    }

    private void writeCacheToSharedPreferences() {
        writeCacheToSharedPreferences(this.sharedPreferences);
    }

    void writeCacheToSharedPreferences(final SharedPreferences sharedPreferences) {
        ThreadUtils.scheduleRunnable(new Runnable() { // from class: com.amazon.device.ads.Settings.2
            @Override // java.lang.Runnable
            public void run() {
                Settings.this.writeToSharedPreferencesLock.lock();
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.clear();
                for (Map.Entry entry : Settings.this.cache.entrySet()) {
                    Value value = (Value) entry.getValue();
                    if (!value.isTransientData) {
                        Class<?> cls = value.clazz;
                        if (cls == String.class) {
                            editorEdit.putString((String) entry.getKey(), (String) value.value);
                        } else if (cls == Long.class) {
                            editorEdit.putLong((String) entry.getKey(), ((Long) value.value).longValue());
                        } else if (cls == Integer.class) {
                            editorEdit.putInt((String) entry.getKey(), ((Integer) value.value).intValue());
                        } else if (cls == Boolean.class) {
                            editorEdit.putBoolean((String) entry.getKey(), ((Boolean) value.value).booleanValue());
                        }
                    }
                }
                Settings.this.commit(editorEdit);
                Settings.this.writeToSharedPreferencesLock.unlock();
            }
        });
    }

    void flush() {
        writeCacheToSharedPreferences();
    }

    public boolean containsKey(String str) {
        return this.cache.containsKey(str);
    }

    public JSONObject getJSONObject(String str, JSONObject jSONObject) {
        JSONObject jSONObjectFromString;
        Value value = this.cache.get(str);
        return (value == null || (jSONObjectFromString = this.jsonUtilities.getJSONObjectFromString((String) value.value)) == null) ? jSONObject : jSONObjectFromString;
    }

    public void putJSONObject(String str, JSONObject jSONObject) {
        putSetting(str, new Value(String.class, jSONObject.toString()));
    }

    public void putJSONObjectWithNoFlush(String str, JSONObject jSONObject) {
        putSettingWithNoFlush(str, new Value(String.class, jSONObject.toString()));
    }

    public String getString(String str, String str2) {
        Value value = this.cache.get(str);
        return value == null ? str2 : (String) value.value;
    }

    void putString(String str, String str2) {
        putSetting(str, new Value(String.class, str2));
    }

    void putStringWithNoFlush(String str, String str2) {
        putSettingWithNoFlush(str, new Value(String.class, str2));
    }

    public String getWrittenString(String str, String str2) {
        return isSettingsLoaded() ? this.sharedPreferences.getString(str, str2) : str2;
    }

    public int getInt(String str, int i) {
        Value value = this.cache.get(str);
        return value == null ? i : ((Integer) value.value).intValue();
    }

    void putIntWithNoFlush(String str, int i) {
        putSettingWithNoFlush(str, new Value(Integer.class, Integer.valueOf(i)));
    }

    public long getLong(String str, long j) {
        Value value = this.cache.get(str);
        return value == null ? j : ((Long) value.value).longValue();
    }

    void putLong(String str, long j) {
        putSetting(str, new Value(Long.class, Long.valueOf(j)));
    }

    void putLongWithNoFlush(String str, long j) {
        putSettingWithNoFlush(str, new Value(Long.class, Long.valueOf(j)));
    }

    public long getWrittenLong(String str, long j) {
        return isSettingsLoaded() ? this.sharedPreferences.getLong(str, j) : j;
    }

    public boolean getBoolean(String str, boolean z) {
        Boolean bool = getBoolean(str, (Boolean) null);
        return bool == null ? z : bool.booleanValue();
    }

    public Boolean getBoolean(String str, Boolean bool) {
        Value value = this.cache.get(str);
        return value == null ? bool : (Boolean) value.value;
    }

    void putBoolean(String str, boolean z) {
        putSetting(str, new Value(Boolean.class, Boolean.valueOf(z)));
    }

    void putBooleanWithNoFlush(String str, boolean z) {
        putSettingWithNoFlush(str, new Value(Boolean.class, Boolean.valueOf(z)));
    }

    void putTransientBoolean(String str, boolean z) {
        putSettingWithNoFlush(str, new TransientValue(Boolean.class, Boolean.valueOf(z)));
    }

    public void putTransientObject(String str, Object obj) {
        putSettingWithNoFlush(str, new TransientValue(obj.getClass(), obj));
    }

    void remove(String str) {
        Value valueRemove = this.cache.remove(str);
        if (valueRemove == null || valueRemove.isTransientData || !isSettingsLoaded()) {
            return;
        }
        flush();
    }

    void removeWithNoFlush(String str) {
        this.cache.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commit(SharedPreferences.Editor editor) {
        editor.apply();
    }

    void notifySettingsListeners() {
        while (true) {
            SettingsListener settingsListenerPoll = this.listeners.poll();
            if (settingsListenerPoll == null) {
                return;
            } else {
                settingsListenerPoll.settingsLoaded();
            }
        }
    }

    class Value {
        public Class<?> clazz;
        public boolean isTransientData;
        public Object value;

        public Value(Class<?> cls, Object obj) {
            this.clazz = cls;
            this.value = obj;
        }
    }

    class TransientValue extends Value {
        public TransientValue(Class<?> cls, Object obj) {
            super(cls, obj);
            this.isTransientData = true;
        }
    }

    void fetchSharedPreferences(Context context) {
        if (!isSettingsLoaded()) {
            SharedPreferences sharedPreferencesFromContext = getSharedPreferencesFromContext(context);
            readSharedPreferencesIntoCache(sharedPreferencesFromContext);
            this.sharedPreferences = sharedPreferencesFromContext;
            writeCacheToSharedPreferences(sharedPreferencesFromContext);
        }
        this.settingsLoadedLatch.countDown();
        notifySettingsListeners();
    }
}
