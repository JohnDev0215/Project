package com.amazon.music.metrics.mts;

import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.metrics.event.Event;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class MTSEvent extends Event<MTSEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(MTSEvent.class.getSimpleName());
    private List<Map<String, String>> mContentInfoAttributes;
    private Map<String, String> mEventAttributes;
    private String mEventName;
    private Map<String, String> mMetricsContext;
    private long mTimestamp;

    @Deprecated
    public MTSEvent(String str) {
        this(str, 0L);
    }

    public MTSEvent(String str, long j) {
        this(str, j, new ConcurrentHashMap());
    }

    private MTSEvent(String str, long j, Map<String, String> map) {
        super(MTSEvent.class);
        this.mEventAttributes = new ConcurrentHashMap();
        this.mMetricsContext = new HashMap();
        this.mContentInfoAttributes = new ArrayList();
        if (isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("Cannot create event with null or empty name");
        }
        this.mEventName = str;
        this.mEventAttributes = map;
        this.mTimestamp = getEpochTimeMilliseconds();
        setSpecificEventVersion(j);
    }

    public String getEventName() {
        return this.mEventName;
    }

    public Map<String, String> getEventAttributes() {
        return this.mEventAttributes;
    }

    public List<Map<String, String>> getContentInfoAttributes() {
        return this.mContentInfoAttributes;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setTimestamp(long j) {
        if (j < 0) {
            LOG.warn("Invalid timestamp passed for metric, resetting to 0");
            this.mTimestamp = 0L;
        } else {
            this.mTimestamp = j;
        }
    }

    public void addAttribute(String str, String str2) {
        validateKey(str);
        if (str2 == null || str2.length() == 0) {
            return;
        }
        this.mEventAttributes.put(str, str2);
    }

    public void addAttributes(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            addAttribute(entry.getKey(), entry.getValue());
        }
    }

    public void addContentInfoAttributes(List<Map<String, String>> list) {
        for (Map<String, String> map : list) {
            HashMap map2 = new HashMap();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                validateKey(key);
                String value = entry.getValue();
                if (value != null && value.length() != 0) {
                    map2.put(key, value);
                }
            }
            this.mContentInfoAttributes.add(map2);
        }
    }

    public void addAttribute(String str, long j) {
        addAttribute(str, String.valueOf(j));
    }

    public void addAttribute(String str, int i) {
        addAttribute(str, String.valueOf(i));
    }

    public void addAttribute(String str, boolean z) {
        addAttribute(str, String.valueOf(z));
    }

    public void addAttribute(String str, Object obj) {
        if (obj != null) {
            addAttribute(str, String.valueOf(obj));
        }
    }

    public void setSpecificEventVersion(long j) {
        this.mEventAttributes.put("specificEventVersion", Long.toString(j));
    }

    public Map<String, String> getMetricsContext() {
        return this.mMetricsContext;
    }

    public void setMetricsContext(Map<String, String> map) {
        this.mMetricsContext = map;
    }

    public void addData(MTSEventDataProvider mTSEventDataProvider) {
        this.mEventAttributes.put("baseEventVersion", Long.toString(4L));
        this.mEventAttributes.put(Splash.PARAMS_APP_VERSION, mTSEventDataProvider.getAppVersion());
        this.mEventAttributes.put("platformName", mTSEventDataProvider.getPlatformName());
        this.mEventAttributes.put(Splash.PARAMS_LOCALE, mTSEventDataProvider.getLocale());
        this.mEventAttributes.put(Splash.PARAMS_MUSIC_TERRITORY, mTSEventDataProvider.getMusicTerritory());
        String subDeviceId = mTSEventDataProvider.getSubDeviceId();
        String str = MTSEventDataProvider.NO_SUB_DEVICE;
        if (subDeviceId != str) {
            this.mEventAttributes.put("subDeviceId", subDeviceId);
        }
        String subDeviceStreamMedium = mTSEventDataProvider.getSubDeviceStreamMedium();
        if (subDeviceStreamMedium != str) {
            this.mEventAttributes.put("subDeviceStreamMedium", subDeviceStreamMedium);
        }
        this.mEventAttributes.put("deviceTimeZone", mTSEventDataProvider.getDeviceTimeZone());
    }

    private void validateKey(String str) {
        if (isNullOrWhiteSpace(str)) {
            throw new IllegalArgumentException("Cannot add an attribute with a null or empty Key");
        }
    }

    private static long getEpochTimeMilliseconds() {
        return Calendar.getInstance().getTimeInMillis();
    }

    private static boolean isNullOrWhiteSpace(String str) {
        return str == null || str.trim().length() == 0;
    }
}
