package com.amazon.music.connect.contacts.metrics;

import com.amazon.commscore.api.metrics.CounterMetric;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContactsCounterMetric.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\r\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0002H\u0016R\u0016\u0010\u0007\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/connect/contacts/metrics/ContactsCounterMetric;", "Lcom/amazon/commscore/api/metrics/CounterMetric;", "", "increment", "", "incrementCounterBy", "getCount", "count", "J", "", "name", "component", "subComponent", "ownerIdentifier", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
public final class ContactsCounterMetric extends CounterMetric {
    private long count;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContactsCounterMetric(String name, String component, String str, String ownerIdentifier) {
        super(name, component, str, ownerIdentifier);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(component, "component");
        Intrinsics.checkNotNullParameter(ownerIdentifier, "ownerIdentifier");
    }

    @Override // com.amazon.commscore.api.metrics.CounterMetric
    public void incrementCounterBy(long increment) {
        this.count += increment;
    }

    @Override // com.amazon.commscore.api.metrics.CounterMetric
    public long getCount() {
        return this.count;
    }
}
