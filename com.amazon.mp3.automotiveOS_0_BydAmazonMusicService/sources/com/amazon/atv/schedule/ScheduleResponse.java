package com.amazon.atv.schedule;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import java.util.Date;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class ScheduleResponse {
    public final Optional<Date> expiry;
    public final Optional<Date> now;

    @NotThreadSafe
    public static class Builder {
        public Date expiry;
        public Date now;
    }

    protected ScheduleResponse(Builder builder) {
        this.expiry = Optional.fromNullable(builder.expiry);
        this.now = Optional.fromNullable(builder.now);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScheduleResponse)) {
            return false;
        }
        ScheduleResponse scheduleResponse = (ScheduleResponse) obj;
        return Objects.equal(this.expiry, scheduleResponse.expiry) && Objects.equal(this.now, scheduleResponse.now);
    }

    public int hashCode() {
        return Objects.hashCode(this.expiry, this.now);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("expiry", this.expiry).add("now", this.now).toString();
    }
}
