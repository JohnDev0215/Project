package com.android.bluetooth.avrcp;

import android.util.Log;
import com.android.bluetooth.Utils;
import com.google.common.collect.EvictingQueue;

/* JADX INFO: loaded from: classes.dex */
public class AvrcpEventLogger {
    private final EvictingQueue<Event> mEvents;
    private final String mTitle;

    private class Event {
        private final String mMsg;
        private final String mTimeStamp = Utils.getLocalTimeString();

        Event(String msg) {
            this.mMsg = msg;
        }

        public String toString() {
            return this.mTimeStamp + " " + this.mMsg;
        }
    }

    AvrcpEventLogger(int size, String title) {
        this.mEvents = EvictingQueue.create(size);
        this.mTitle = title;
    }

    synchronized void add(String msg) {
        Event event = new Event(msg);
        this.mEvents.add(event);
    }

    synchronized void logv(String tag, String msg) {
        add(msg);
        Log.v(tag, msg);
    }

    synchronized void logd(String tag, String msg) {
        logd(true, tag, msg);
    }

    synchronized void logd(boolean debug, String tag, String msg) {
        add(msg);
        if (debug) {
            Log.d(tag, msg);
        }
    }

    synchronized void dump(StringBuilder sb) {
        sb.append("Avrcp ");
        sb.append(this.mTitle);
        sb.append(":\n");
        for (Event event : this.mEvents) {
            sb.append("  ");
            sb.append(event.toString());
            sb.append("\n");
        }
    }
}
