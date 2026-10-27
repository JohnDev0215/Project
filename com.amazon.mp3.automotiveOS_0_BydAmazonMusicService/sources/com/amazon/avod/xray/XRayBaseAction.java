package com.amazon.avod.xray;

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
public abstract class XRayBaseAction {
    public final String parentTargetId;
    public final String targetId;
    public final String type;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public String parentTargetId;
        public String targetId;
        public String type;
        public int version;
    }

    protected XRayBaseAction(Builder builder) {
        this.parentTargetId = (String) Preconditions.checkNotNull(builder.parentTargetId, "Unexpected null field: parentTargetId");
        this.type = (String) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.targetId = (String) Preconditions.checkNotNull(builder.targetId, "Unexpected null field: targetId");
        this.version = builder.version;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof XRayBaseAction)) {
            return false;
        }
        XRayBaseAction xRayBaseAction = (XRayBaseAction) obj;
        return Objects.equal(this.parentTargetId, xRayBaseAction.parentTargetId) && Objects.equal(this.type, xRayBaseAction.type) && Objects.equal(this.targetId, xRayBaseAction.targetId) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(xRayBaseAction.version));
    }

    public int hashCode() {
        return Objects.hashCode(this.parentTargetId, this.type, this.targetId, Integer.valueOf(this.version));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("parentTargetId", this.parentTargetId).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("targetId", this.targetId).add("version", this.version).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<XRayBaseAction> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.XRAYBASEACTION_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
