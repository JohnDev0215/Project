package com.amazon.avod.xray;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XRayFragmentBase {
    public final FragmentVersion version;

    @NotThreadSafe
    public static abstract class Builder {
        public FragmentVersion version;
    }

    protected XRayFragmentBase(Builder builder) {
        this.version = (FragmentVersion) Preconditions.checkNotNull(builder.version, "Unexpected null field: version");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof XRayFragmentBase) {
            return Objects.equal(this.version, ((XRayFragmentBase) obj).version);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.version);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("version", this.version).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<XRayFragmentBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.XRAYFRAGMENTBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
