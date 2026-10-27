package com.amazon.atv.title.v1;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Fragment {
    public final Optional<String> validUntil;
    public final String version;

    @NotThreadSafe
    public static abstract class Builder {
        public String validUntil;
        public String version;
    }

    protected Fragment(Builder builder) {
        this.version = (String) Preconditions.checkNotNull(builder.version, "Unexpected null field: version");
        this.validUntil = Optional.fromNullable(builder.validUntil);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fragment)) {
            return false;
        }
        Fragment fragment = (Fragment) obj;
        return Objects.equal(this.version, fragment.version) && Objects.equal(this.validUntil, fragment.validUntil);
    }

    public int hashCode() {
        return Objects.hashCode(this.version, this.validUntil);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("version", this.version).add("validUntil", this.validUntil).toString();
    }
}
