package com.amazon.music.platform.aom.launcher.events;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EventTarget.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/aom/launcher/events/EventTarget;", "", "", "toString", "", "hashCode", "other", "", "equals", "token", "Ljava/lang/String;", "getToken", "()Ljava/lang/String;", "<init>", "(Ljava/lang/String;)V", "MusicPlatformAOM_release"}, k = 1, mv = {1, 7, 1})
public final /* data */ class EventTarget {
    private final String token;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof EventTarget) && Intrinsics.areEqual(this.token, ((EventTarget) other).token);
    }

    public int hashCode() {
        return this.token.hashCode();
    }

    public String toString() {
        return "EventTarget(token=" + this.token + ')';
    }

    public EventTarget(String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        this.token = token;
    }

    public final String getToken() {
        return this.token;
    }
}
