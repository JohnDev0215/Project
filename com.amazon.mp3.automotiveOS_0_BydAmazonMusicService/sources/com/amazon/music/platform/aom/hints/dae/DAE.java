package com.amazon.music.platform.aom.hints.dae;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DAE.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\n\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0006\u0010\u0003\u001a\u00020\u0002J\t\u0010\u0004\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0006\u001a\u00020\u0005HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u0011\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/platform/aom/hints/dae/DAE;", "", "", "getKey", "toString", "", "hashCode", "other", "", "equals", "domain", "Ljava/lang/String;", "getDomain", "()Ljava/lang/String;", "application", "getApplication", "eventType", "getEventType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class DAE {
    private final String application;
    private final String domain;
    private final String eventType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DAE)) {
            return false;
        }
        DAE dae = (DAE) other;
        return Intrinsics.areEqual(this.domain, dae.domain) && Intrinsics.areEqual(this.application, dae.application) && Intrinsics.areEqual(this.eventType, dae.eventType);
    }

    public int hashCode() {
        return (((this.domain.hashCode() * 31) + this.application.hashCode()) * 31) + this.eventType.hashCode();
    }

    public String toString() {
        return "DAE(domain=" + this.domain + ", application=" + this.application + ", eventType=" + this.eventType + ')';
    }

    public DAE(String domain, String application, String eventType) {
        Intrinsics.checkNotNullParameter(domain, "domain");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        this.domain = domain;
        this.application = application;
        this.eventType = eventType;
    }

    public final String getApplication() {
        return this.application;
    }

    public final String getDomain() {
        return this.domain;
    }

    public final String getEventType() {
        return this.eventType;
    }

    public final String getKey() {
        return this.domain + this.application + this.eventType;
    }
}
