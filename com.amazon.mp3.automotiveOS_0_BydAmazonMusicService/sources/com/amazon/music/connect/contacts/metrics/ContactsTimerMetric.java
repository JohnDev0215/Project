package com.amazon.music.connect.contacts.metrics;

import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.commscore.api.metrics.TimerMetric;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContactsTimerMetric.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B;\b\u0016\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0010\u001a\u00020\t¢\u0006\u0004\b\u0011\u0010\u0012J\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/connect/contacts/metrics/ContactsTimerMetric;", "Lcom/amazon/commscore/api/metrics/TimerMetric;", "", "finishTimer", "", "getElapsedTime", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsTimer;", "mTimer", "Lcom/amazon/alexa/mobilytics/event/operational/MobilyticsMetricsTimer;", "", "name", "component", "subComponent", "initialElapsedTime", "", "startTimer", "ownerIdentifier", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JZLjava/lang/String;)V", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
public final class ContactsTimerMetric extends TimerMetric {
    private final MobilyticsMetricsTimer mTimer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContactsTimerMetric(String name, String component, String str, long j, boolean z, String ownerIdentifier) {
        super(name, component, str);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(component, "component");
        Intrinsics.checkNotNullParameter(ownerIdentifier, "ownerIdentifier");
        this.mTimer = new DefaultMobilyticsMetricsTimer(name, component, str, j, z, ownerIdentifier);
    }

    @Override // com.amazon.commscore.api.metrics.TimerMetric
    public void finishTimer() {
        this.mTimer.finishTimer();
    }

    @Override // com.amazon.commscore.api.metrics.TimerMetric
    public long getElapsedTime() {
        return this.mTimer.getElapsedTime();
    }
}
