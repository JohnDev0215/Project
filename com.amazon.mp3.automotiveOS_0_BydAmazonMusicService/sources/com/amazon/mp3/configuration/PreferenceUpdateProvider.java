package com.amazon.mp3.configuration;

import android.os.Handler;
import android.os.Looper;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class PreferenceUpdateProvider {
    private static Set<PreferenceUpdateReceiver> receivers = new HashSet();

    public interface PreferenceUpdateReceiver {
        void onReceive();
    }

    public static synchronized void registerReceiver(final PreferenceUpdateReceiver preferenceUpdateReceiver) {
        receivers.add(preferenceUpdateReceiver);
    }

    public static synchronized void deregisterReceiver(final PreferenceUpdateReceiver preferenceUpdateReceiver) {
        if (receivers.contains(preferenceUpdateReceiver)) {
            receivers.remove(preferenceUpdateReceiver);
        }
    }

    public static synchronized void dispatchPreferenceUpdateEvent() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.mp3.configuration.PreferenceUpdateProvider.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (PreferenceUpdateProvider.class) {
                    Iterator it = PreferenceUpdateProvider.receivers.iterator();
                    while (it.hasNext()) {
                        ((PreferenceUpdateReceiver) it.next()).onReceive();
                    }
                }
            }
        });
    }
}
