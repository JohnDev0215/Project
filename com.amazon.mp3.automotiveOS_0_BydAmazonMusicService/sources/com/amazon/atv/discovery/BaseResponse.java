package com.amazon.atv.discovery;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseResponse {
    public final Optional<ImmutableMap<DebugType, ImmutableList<DebugAttribute>>> debugAttributes;

    @NotThreadSafe
    public static abstract class Builder {
        public ImmutableMap<DebugType, ImmutableList<DebugAttribute>> debugAttributes;
    }

    protected BaseResponse(Builder builder) {
        this.debugAttributes = Optional.fromNullable(builder.debugAttributes);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BaseResponse) {
            return Objects.equal(this.debugAttributes, ((BaseResponse) obj).debugAttributes);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.debugAttributes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("debugAttributes", this.debugAttributes).toString();
    }
}
