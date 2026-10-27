package com.spotify.ubi.logger;

import com.spotify.ubi.model.ImpressionId;
import com.spotify.ubi.model.PageInstanceId;
import java.io.Serializable;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ImpressionLoggingResult implements Serializable {
    private static final long serialVersionUID = -7774588449227442920L;
    private final ImpressionId impressionId;
    private final PageInstanceId pageInstanceId;

    public ImpressionLoggingResult(ImpressionId impressionId, PageInstanceId pageInstanceId) {
        this.impressionId = impressionId;
        this.pageInstanceId = pageInstanceId;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImpressionLoggingResult)) {
            return false;
        }
        ImpressionLoggingResult impressionLoggingResult = (ImpressionLoggingResult) obj;
        return v03.b(this.impressionId, impressionLoggingResult.impressionId) && v03.b(this.pageInstanceId, impressionLoggingResult.pageInstanceId);
    }

    public final int hashCode() {
        int iHashCode = this.impressionId.hashCode() * 31;
        PageInstanceId pageInstanceId = this.pageInstanceId;
        return iHashCode + (pageInstanceId == null ? 0 : pageInstanceId.hashCode());
    }

    public final String toString() {
        return "ImpressionLoggingResult(impressionId=" + this.impressionId + ", pageInstanceId=" + this.pageInstanceId + ')';
    }
}
