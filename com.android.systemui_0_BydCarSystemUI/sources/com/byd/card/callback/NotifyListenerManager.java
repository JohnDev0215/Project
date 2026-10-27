package com.byd.card.callback;

import com.byd.card.bean.AppOrShortcutItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class NotifyListenerManager {
    public static volatile NotifyListenerManager sInstance;
    private List<INotifyListener> listeners = new ArrayList();
    private Map<String, INotifyListener> maps = new HashMap();

    private NotifyListenerManager() {
    }

    public static NotifyListenerManager getInstance() {
        if (sInstance == null) {
            synchronized (NotifyListenerManager.class) {
                if (sInstance == null) {
                    sInstance = new NotifyListenerManager();
                }
            }
        }
        return sInstance;
    }

    public void registerListener(INotifyListener listener, String tag) {
        if (this.listeners.contains(listener)) {
            return;
        }
        this.listeners.add(listener);
        this.maps.put(tag, listener);
    }

    public void unRegisterListener(INotifyListener listener) {
        if (this.listeners.contains(listener)) {
            this.listeners.remove(listener);
        }
        if (this.maps.get(listener) != null) {
            this.maps.remove(listener);
        }
    }

    public void notifyContext(List<AppOrShortcutItem> list, String tag) {
        INotifyListener iNotifyListener = this.maps.get(tag);
        if (iNotifyListener != null) {
            iNotifyListener.notifyContext(list);
        }
    }

    public void removeAllListener() {
        this.listeners.clear();
        this.maps.clear();
    }
}
