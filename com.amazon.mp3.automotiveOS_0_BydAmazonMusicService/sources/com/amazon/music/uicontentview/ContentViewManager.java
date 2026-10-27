package com.amazon.music.uicontentview;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.util.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.ISODateTimeFormat;

/* JADX INFO: loaded from: classes4.dex */
public class ContentViewManager implements ContentViewedListener {
    private static final String TAG = "ContentViewManager";
    private LinkedHashMap<String, Map<String, String>> contentViewedMap = new LinkedHashMap<>();

    @Override // com.amazon.music.uicontentview.ContentViewedListener
    public void onItemRendered(String str, Map<String, String> map) {
        if (this.contentViewedMap.containsKey(str)) {
            return;
        }
        DateTime dateTime = new DateTime(DateTimeZone.UTC);
        if (!map.containsKey("impressionTimestamp")) {
            map.put("impressionTimestamp", ISODateTimeFormat.dateTime().print(dateTime));
        }
        this.contentViewedMap.put(str, map);
        Log.v(TAG, "onItemRendered: " + map);
    }

    @Override // com.amazon.music.uicontentview.ContentViewedListener
    public void onItemRenderedInSequence(String str, int i, Map<String, String> map) {
        if (this.contentViewedMap.containsKey(str)) {
            Map<String, String> map2 = this.contentViewedMap.get(str);
            if (map2.containsKey("firstViewableIndex") && map2.containsKey("lastViewableIndex")) {
                String str2 = map2.get("firstViewableIndex");
                String str3 = map2.get("lastViewableIndex");
                if (!StringUtil.isNullOrEmpty(str2) && !StringUtil.isNullOrEmpty(str3)) {
                    if (i >= 0 && Integer.parseInt(str2) > i) {
                        map2.put("firstViewableIndex", Integer.toString(i));
                    } else if (i >= 0 && Integer.parseInt(str3) < i) {
                        map2.put("lastViewableIndex", Integer.toString(i));
                    }
                }
            }
            if (map2.containsKey("numItemsAccessible") && map.containsKey("numItemsAccessible")) {
                int i2 = Integer.parseInt(map2.get("numItemsAccessible"));
                int i3 = Integer.parseInt(map.get("numItemsAccessible"));
                if (i2 < i3) {
                    map2.put("numItemsAccessible", Integer.toString(i3));
                    return;
                }
                return;
            }
            return;
        }
        String string = Integer.toString(i);
        map.put("impressionTimestamp", ISODateTimeFormat.dateTime().print(new DateTime(DateTimeZone.UTC)));
        map.put("firstViewableIndex", string);
        map.put("lastViewableIndex", string);
        this.contentViewedMap.put(str, map);
    }

    public void sendUiContentViewEvent(final String str) {
        Runnable runnable = new Runnable() { // from class: com.amazon.music.uicontentview.ContentViewManager.1
            @Override // java.lang.Runnable
            public void run() {
                if (ContentViewManager.this.getSingleUiContentViewEvent(str) == null) {
                    return;
                }
                MetricsHolder.getManager().handleEvent(ContentViewManager.this.getSingleUiContentViewEvent(str));
                ContentViewManager.this.clearContentViewed();
            }
        };
        HandlerThread handlerThread = new HandlerThread("ContentView Module handler thread", 10);
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(runnable);
    }

    public void sendUiContentViewEvent() {
        sendUiContentViewEvent(null);
    }

    UiContentViewEvent getSingleUiContentViewEvent(String str) {
        if (this.contentViewedMap.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<String, Map<String, String>>> it = this.contentViewedMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue());
        }
        return UiContentViewEvent.builder().withContentInfo(arrayList).withExperienceMode(str).withEventTime(System.currentTimeMillis()).build();
    }

    void clearContentViewed() {
        this.contentViewedMap = new LinkedHashMap<>();
    }
}
