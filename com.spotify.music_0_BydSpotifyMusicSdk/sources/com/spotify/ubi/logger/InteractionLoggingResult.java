package com.spotify.ubi.logger;

import com.spotify.ubi.model.InteractionId;
import com.spotify.ubi.model.PageInstanceId;
import java.io.Serializable;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class InteractionLoggingResult implements Serializable {
    private static final long serialVersionUID = -2771678779950046121L;
    private final InteractionId interactionId;
    private final PageInstanceId pageInstanceId;

    public InteractionLoggingResult(InteractionId interactionId, PageInstanceId pageInstanceId) {
        this.interactionId = interactionId;
        this.pageInstanceId = pageInstanceId;
    }

    public final InteractionId a() {
        return this.interactionId;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InteractionLoggingResult)) {
            return false;
        }
        InteractionLoggingResult interactionLoggingResult = (InteractionLoggingResult) obj;
        return v03.b(this.interactionId, interactionLoggingResult.interactionId) && v03.b(this.pageInstanceId, interactionLoggingResult.pageInstanceId);
    }

    public final int hashCode() {
        int iHashCode = this.interactionId.hashCode() * 31;
        PageInstanceId pageInstanceId = this.pageInstanceId;
        return iHashCode + (pageInstanceId == null ? 0 : pageInstanceId.hashCode());
    }

    public final String toString() {
        return "InteractionLoggingResult(interactionId=" + this.interactionId + ", pageInstanceId=" + this.pageInstanceId + ')';
    }
}
