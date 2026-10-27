package com.amazon.avod.events.perf;

import androidx.core.app.NotificationCompat;
import com.amazon.avod.events.Event;
import kotlin.Metadata;

/* JADX INFO: compiled from: EventManagerProfilerListener.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\b"}, d2 = {"Lcom/amazon/avod/events/perf/EventManagerProfilerListener;", "", "Lcom/amazon/avod/events/Event;", NotificationCompat.CATEGORY_EVENT, "", "successful", "", "onEventQueued", "ATVAndroidCore_release"}, k = 1, mv = {1, 6, 0})
public interface EventManagerProfilerListener {
    void onEventQueued(Event event, boolean successful);
}
