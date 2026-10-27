package com.amazon.atv.parentalcontrols;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AgeBandRequest {
    public final Optional<String> profileACI;

    @NotThreadSafe
    public static abstract class Builder {
        public String profileACI;
    }

    protected AgeBandRequest(Builder builder) {
        this.profileACI = Optional.fromNullable(builder.profileACI);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AgeBandRequest) {
            return Objects.equal(this.profileACI, ((AgeBandRequest) obj).profileACI);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.profileACI);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("profileACI", this.profileACI).toString();
    }
}
