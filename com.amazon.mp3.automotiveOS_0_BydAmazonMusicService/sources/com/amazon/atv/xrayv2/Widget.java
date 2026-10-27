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
public abstract class Widget {
    public final Optional<Analytics> analytics;
    public final Optional<Blueprint> blueprint;
    public final Optional<String> debugAttributes;
    public final Optional<String> id;
    public final String type;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public Analytics analytics;
        public Blueprint blueprint;
        public String debugAttributes;
        public String id;
        public String type;
        public int version;
    }

    protected Widget(Builder builder) {
        this.analytics = Optional.fromNullable(builder.analytics);
        this.blueprint = Optional.fromNullable(builder.blueprint);
        this.debugAttributes = Optional.fromNullable(builder.debugAttributes);
        this.version = builder.version;
        this.type = (String) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.id = Optional.fromNullable(builder.id);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Widget)) {
            return false;
        }
        Widget widget = (Widget) obj;
        return Objects.equal(this.analytics, widget.analytics) && Objects.equal(this.blueprint, widget.blueprint) && Objects.equal(this.debugAttributes, widget.debugAttributes) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(widget.version)) && Objects.equal(this.type, widget.type) && Objects.equal(this.id, widget.id);
    }

    public int hashCode() {
        return Objects.hashCode(this.analytics, this.blueprint, this.debugAttributes, Integer.valueOf(this.version), this.type, this.id);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("analytics", this.analytics).add("blueprint", this.blueprint).add("debugAttributes", this.debugAttributes).add("version", this.version).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("id", this.id).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<Widget> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.WIDGET_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
