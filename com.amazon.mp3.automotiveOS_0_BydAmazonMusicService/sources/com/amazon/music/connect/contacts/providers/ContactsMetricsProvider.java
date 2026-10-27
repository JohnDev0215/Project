package com.amazon.music.connect.contacts.providers;

import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.api.metrics.CounterMetric;
import com.amazon.commscore.api.metrics.TimerMetric;
import com.amazon.music.connect.contacts.metrics.ContactsCounterMetric;
import com.amazon.music.connect.contacts.metrics.ContactsTimerMetric;
import com.amazon.music.platform.metrics.event.FlexWrapper;
import com.amazon.music.platform.providers.MetricsProvider;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContactsMetricsProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b*\u0010+J0\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J8\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\u00022\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J@\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016JH\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J8\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0013\u001a\u00020\u000e2\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J@\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0012\u001a\u00020\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u000e2\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\bH\u0016J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0010H\u0016R\u0014\u0010\u001a\u001a\u00020\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0014\u0010\u001f\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001f\u0010\u001dR\u0014\u0010 \u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b \u0010\u001dR\u0014\u0010!\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b!\u0010\u001dR\u0014\u0010\"\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b\"\u0010\u001dR\u0014\u0010#\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b#\u0010\u001dR\u0014\u0010$\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b$\u0010\u001dR\u0014\u0010%\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b%\u0010\u001dR\u0014\u0010&\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b&\u0010\u001dR \u0010(\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00100'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)¨\u0006,"}, d2 = {"Lcom/amazon/music/connect/contacts/providers/ContactsMetricsProvider;", "Lcom/amazon/commscore/api/metrics/AlexaCommsCoreMetricsService;", "", "counterName", "subComponent", "", "", "customEntries", "Lcom/amazon/commscore/api/metrics/CounterMetric;", "createCounter", "ownerIdentifier", "timerName", "", "initialElapsedTime", "", "startTimer", "Lcom/amazon/commscore/api/metrics/TimerMetric;", "createTimer", "occurrenceName", "eventOccurred", "", "recordOccurrence", "metric", "recordCounter", "recordTimer", "Lcom/amazon/music/platform/providers/MetricsProvider;", "metricsProvider", "Lcom/amazon/music/platform/providers/MetricsProvider;", "MUSIC_APP_COMPONENT", "Ljava/lang/String;", "MUSIC_OWNER_IDENTIFIER", "CONTACT_IMPORT_FLEX", "OPERATIONAL", "INTERACTION", "CRITICAL", "INFO", "WARNING", "COUNTER", "TIMER", "Ljava/util/concurrent/ConcurrentMap;", "ongoingTimers", "Ljava/util/concurrent/ConcurrentMap;", "<init>", "(Lcom/amazon/music/platform/providers/MetricsProvider;)V", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
public final class ContactsMetricsProvider implements AlexaCommsCoreMetricsService {
    private final String CONTACT_IMPORT_FLEX;
    private final String COUNTER;
    private final String CRITICAL;
    private final String INFO;
    private final String INTERACTION;
    private final String MUSIC_APP_COMPONENT;
    private final String MUSIC_OWNER_IDENTIFIER;
    private final String OPERATIONAL;
    private final String TIMER;
    private final String WARNING;
    private final MetricsProvider metricsProvider;
    private final ConcurrentMap<String, TimerMetric> ongoingTimers;

    public ContactsMetricsProvider(MetricsProvider metricsProvider) {
        Intrinsics.checkNotNullParameter(metricsProvider, "metricsProvider");
        this.metricsProvider = metricsProvider;
        this.MUSIC_APP_COMPONENT = "Music";
        this.MUSIC_OWNER_IDENTIFIER = "ContactImport";
        this.CONTACT_IMPORT_FLEX = "ContactImportFlow";
        this.OPERATIONAL = "Operational";
        this.INTERACTION = "Interaction";
        this.CRITICAL = "Critical";
        this.INFO = "Info";
        this.WARNING = "Warning";
        this.COUNTER = "Counter";
        this.TIMER = "Timer";
        this.ongoingTimers = new ConcurrentHashMap();
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public CounterMetric createCounter(String counterName, String subComponent, Map<String, Object> customEntries) {
        Intrinsics.checkNotNullParameter(counterName, "counterName");
        return createCounter(counterName, subComponent, this.MUSIC_OWNER_IDENTIFIER, customEntries);
    }

    public CounterMetric createCounter(String counterName, String subComponent, String ownerIdentifier, Map<String, Object> customEntries) {
        Intrinsics.checkNotNullParameter(counterName, "counterName");
        Intrinsics.checkNotNullParameter(ownerIdentifier, "ownerIdentifier");
        ContactsCounterMetric contactsCounterMetric = new ContactsCounterMetric(counterName, this.MUSIC_APP_COMPONENT, subComponent, ownerIdentifier);
        contactsCounterMetric.setCustomEntries(customEntries);
        return contactsCounterMetric;
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public TimerMetric createTimer(String timerName, String subComponent, long initialElapsedTime, boolean startTimer, Map<String, Object> customEntries) {
        Intrinsics.checkNotNullParameter(timerName, "timerName");
        return createTimer(timerName, subComponent, this.MUSIC_OWNER_IDENTIFIER, initialElapsedTime, startTimer, customEntries);
    }

    public TimerMetric createTimer(String timerName, String subComponent, String ownerIdentifier, long initialElapsedTime, boolean startTimer, Map<String, Object> customEntries) {
        Intrinsics.checkNotNullParameter(timerName, "timerName");
        Intrinsics.checkNotNullParameter(ownerIdentifier, "ownerIdentifier");
        ContactsTimerMetric contactsTimerMetric = new ContactsTimerMetric(timerName, this.MUSIC_APP_COMPONENT, subComponent, initialElapsedTime, startTimer, ownerIdentifier);
        contactsTimerMetric.setCustomEntries(customEntries);
        this.ongoingTimers.put(timerName, contactsTimerMetric);
        return contactsTimerMetric;
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordOccurrence(String occurrenceName, String subComponent, boolean eventOccurred, Map<String, Object> customEntries) {
        Intrinsics.checkNotNullParameter(occurrenceName, "occurrenceName");
        recordOccurrence(occurrenceName, subComponent, this.MUSIC_OWNER_IDENTIFIER, eventOccurred, customEntries);
    }

    public void recordOccurrence(String occurrenceName, String subComponent, String ownerIdentifier, boolean eventOccurred, Map<String, Object> customEntries) {
        Intrinsics.checkNotNullParameter(occurrenceName, "occurrenceName");
        Intrinsics.checkNotNullParameter(ownerIdentifier, "ownerIdentifier");
        CounterMetric counterMetricCreateCounter = createCounter(occurrenceName, subComponent, ownerIdentifier, customEntries);
        counterMetricCreateCounter.incrementCounterBy(eventOccurred ? 1L : 0L);
        recordCounter(counterMetricCreateCounter);
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordCounter(CounterMetric metric) {
        Intrinsics.checkNotNullParameter(metric, "metric");
        FlexWrapper.Builder builderWithStr3 = new FlexWrapper.Builder().withName(this.CONTACT_IMPORT_FLEX).withStr1(this.COUNTER).withStr2(metric.getName()).withStr3(metric.getType().name());
        StringBuilder sb = new StringBuilder();
        sb.append((Object) metric.getComponent());
        sb.append('.');
        sb.append((Object) metric.getSubComponent());
        sb.append('.');
        sb.append((Object) metric.getOwnerIdentifier());
        this.metricsProvider.sendMetricsEvent(builderWithStr3.withStr4(sb.toString()).withNum1(Float.valueOf(metric.getCount())).build());
    }

    @Override // com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService
    public void recordTimer(TimerMetric metric) {
        Intrinsics.checkNotNullParameter(metric, "metric");
        metric.finishTimer();
        this.ongoingTimers.remove(metric.getName());
        FlexWrapper.Builder builderWithStr3 = new FlexWrapper.Builder().withName(this.CONTACT_IMPORT_FLEX).withStr1(this.TIMER).withStr2(metric.getName()).withStr3(metric.getType().name());
        StringBuilder sb = new StringBuilder();
        sb.append((Object) metric.getComponent());
        sb.append('.');
        sb.append((Object) metric.getSubComponent());
        sb.append('.');
        sb.append((Object) metric.getOwnerIdentifier());
        this.metricsProvider.sendMetricsEvent(builderWithStr3.withStr4(sb.toString()).withNum1(Float.valueOf(metric.getElapsedTime())).build());
    }
}
