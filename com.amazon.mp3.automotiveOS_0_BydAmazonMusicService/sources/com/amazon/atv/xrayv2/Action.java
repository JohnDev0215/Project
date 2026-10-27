package com.amazon.atv.xrayv2;

import com.amazon.atv.discovery.Analytics;
import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Action {
    public final Optional<Analytics> analytics;
    public final Optional<String> text;
    public final ActionType type;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public Analytics analytics;
        public String text;
        public ActionType type;
        public int version;
    }

    protected Action(Builder builder) {
        this.analytics = Optional.fromNullable(builder.analytics);
        this.text = Optional.fromNullable(builder.text);
        this.type = (ActionType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.version = builder.version;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Action)) {
            return false;
        }
        Action action = (Action) obj;
        return Objects.equal(this.analytics, action.analytics) && Objects.equal(this.text, action.text) && Objects.equal(this.type, action.type) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(action.version));
    }

    public int hashCode() {
        return Objects.hashCode(this.analytics, this.text, this.type, Integer.valueOf(this.version));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("analytics", this.analytics).add("text", this.text).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("version", this.version).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<Action> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.ACTION_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
