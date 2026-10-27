package com.amazon.atv.parentalcontrols;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AgeBandResponse {
    public final Optional<String> activeAgeBand;

    @NotThreadSafe
    public static abstract class Builder {
        public String activeAgeBand;
    }

    protected AgeBandResponse(Builder builder) {
        this.activeAgeBand = Optional.fromNullable(builder.activeAgeBand);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AgeBandResponse) {
            return Objects.equal(this.activeAgeBand, ((AgeBandResponse) obj).activeAgeBand);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.activeAgeBand);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("activeAgeBand", this.activeAgeBand).toString();
    }
}
