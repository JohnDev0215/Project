package com.byd.automultipletheme.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventBusBean.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/byd/automultipletheme/bean/EventBusBean;", "", "eventType", "", "eventResult", "", "(Ljava/lang/String;I)V", "getEventResult", "()I", "setEventResult", "(I)V", "getEventType", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class EventBusBean {
    private int eventResult;
    private final String eventType;

    public static /* synthetic */ EventBusBean copy$default(EventBusBean eventBusBean, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = eventBusBean.eventType;
        }
        if ((i2 & 2) != 0) {
            i = eventBusBean.eventResult;
        }
        return eventBusBean.copy(str, i);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getEventType() {
        return this.eventType;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getEventResult() {
        return this.eventResult;
    }

    public final EventBusBean copy(String eventType, int eventResult) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        return new EventBusBean(eventType, eventResult);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EventBusBean)) {
            return false;
        }
        EventBusBean eventBusBean = (EventBusBean) other;
        return Intrinsics.areEqual(this.eventType, eventBusBean.eventType) && this.eventResult == eventBusBean.eventResult;
    }

    public int hashCode() {
        return (this.eventType.hashCode() * 31) + this.eventResult;
    }

    public String toString() {
        return "EventBusBean(eventType=" + this.eventType + ", eventResult=" + this.eventResult + ')';
    }

    public EventBusBean(String eventType, int i) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        this.eventType = eventType;
        this.eventResult = i;
    }

    public final String getEventType() {
        return this.eventType;
    }

    public final int getEventResult() {
        return this.eventResult;
    }

    public final void setEventResult(int i) {
        this.eventResult = i;
    }
}
