package com.amazon.urlvending;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes5.dex */
public abstract class UrlVendingBaseException {
    public final Optional<String> message;

    @NotThreadSafe
    public static abstract class Builder {
        public String message;
    }

    protected UrlVendingBaseException(Builder builder) {
        this.message = Optional.fromNullable(builder.message);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof UrlVendingBaseException) {
            return Objects.equal(this.message, ((UrlVendingBaseException) obj).message);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.message);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("message", this.message).toString();
    }
}
