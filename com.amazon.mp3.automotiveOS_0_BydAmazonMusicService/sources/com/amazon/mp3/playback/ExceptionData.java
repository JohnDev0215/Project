package com.amazon.mp3.playback;

import androidx.core.app.NotificationCompat;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueFlexMetricsManager;
import com.google.firebase.perf.util.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackExceptions.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\r\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u000e\u0010\u0010R\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/playback/ExceptionData;", "", "", "toString", "", "hashCode", "other", "", "equals", "exception", "I", "getException", "()I", "Lkotlin/Function0;", Constants.ENABLE_DISABLE, "Lkotlin/jvm/functions/Function0;", "()Lkotlin/jvm/functions/Function0;", "Lcom/amazon/mp3/cloudqueue/metrics/CloudQueueFlexMetricsManager$Event;", NotificationCompat.CATEGORY_EVENT, "Lcom/amazon/mp3/cloudqueue/metrics/CloudQueueFlexMetricsManager$Event;", "getEvent", "()Lcom/amazon/mp3/cloudqueue/metrics/CloudQueueFlexMetricsManager$Event;", "<init>", "(ILkotlin/jvm/functions/Function0;Lcom/amazon/mp3/cloudqueue/metrics/CloudQueueFlexMetricsManager$Event;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class ExceptionData {
    private final CloudQueueFlexMetricsManager.Event event;
    private final int exception;
    private final Function0<Boolean> isEnabled;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ExceptionData)) {
            return false;
        }
        ExceptionData exceptionData = (ExceptionData) other;
        return this.exception == exceptionData.exception && Intrinsics.areEqual(this.isEnabled, exceptionData.isEnabled) && this.event == exceptionData.event;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.exception) * 31) + this.isEnabled.hashCode()) * 31) + this.event.hashCode();
    }

    public String toString() {
        return "ExceptionData(exception=" + this.exception + ", isEnabled=" + this.isEnabled + ", event=" + this.event + ')';
    }

    public ExceptionData(int i, Function0<Boolean> isEnabled, CloudQueueFlexMetricsManager.Event event) {
        Intrinsics.checkNotNullParameter(isEnabled, "isEnabled");
        Intrinsics.checkNotNullParameter(event, "event");
        this.exception = i;
        this.isEnabled = isEnabled;
        this.event = event;
    }

    public final int getException() {
        return this.exception;
    }

    public final Function0<Boolean> isEnabled() {
        return this.isEnabled;
    }

    public final CloudQueueFlexMetricsManager.Event getEvent() {
        return this.event;
    }
}
