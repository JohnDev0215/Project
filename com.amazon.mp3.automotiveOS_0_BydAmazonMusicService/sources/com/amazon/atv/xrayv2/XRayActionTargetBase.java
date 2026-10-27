package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class XRayActionTargetBase {
    public final String type;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public String type;
        public int version;
    }

    protected XRayActionTargetBase(Builder builder) {
        this.type = (String) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.version = builder.version;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRayActionTargetBase)) {
            return false;
        }
        XRayActionTargetBase xRayActionTargetBase = (XRayActionTargetBase) obj;
        return Objects.equal(this.type, xRayActionTargetBase.type) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(xRayActionTargetBase.version));
    }

    public int hashCode() {
        return Objects.hashCode(this.type, Integer.valueOf(this.version));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("version", this.version).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<XRayActionTargetBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.XRAYACTIONTARGETBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
