package com.amazon.atvplaybackresource;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public class PRSException {
    public final Optional<String> downstreamReason;
    public final Optional<String> errorCode;
    public final Optional<String> message;
    public final Optional<String> type;

    protected PRSException(Builder builder) {
        this.downstreamReason = Optional.fromNullable(builder.downstreamReason);
        this.errorCode = Optional.fromNullable(builder.errorCode);
        this.type = Optional.fromNullable(builder.type);
        this.message = Optional.fromNullable(builder.message);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PRSException)) {
            return false;
        }
        PRSException pRSException = (PRSException) obj;
        return Objects.equal(this.downstreamReason, pRSException.downstreamReason) && Objects.equal(this.errorCode, pRSException.errorCode) && Objects.equal(this.type, pRSException.type) && Objects.equal(this.message, pRSException.message);
    }

    public int hashCode() {
        return Objects.hashCode(this.downstreamReason, this.errorCode, this.type, this.message);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("downstreamReason", this.downstreamReason).add("errorCode", this.errorCode).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("message", this.message).toString();
    }

    @NotThreadSafe
    public static class Builder {
        public String downstreamReason;
        public String errorCode;
        public String message;
        public String type;

        public PRSException build() {
            return new PRSException(this);
        }
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<PRSException> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.PRSEXCEPTION_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
