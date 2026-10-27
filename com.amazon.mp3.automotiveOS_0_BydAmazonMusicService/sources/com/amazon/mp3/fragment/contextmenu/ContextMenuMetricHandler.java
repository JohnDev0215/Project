package com.amazon.mp3.fragment.contextmenu;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;

/* JADX INFO: compiled from: ContextMenuMetricHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/fragment/contextmenu/ContextMenuMetricHandler;", "", "handleSendMetricEvent", "", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/mp3/fragment/contextmenu/ContextMenuItemClickEvent;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface ContextMenuMetricHandler {
    boolean handleSendMetricEvent(ContextMenuItemClickEvent event);
}
