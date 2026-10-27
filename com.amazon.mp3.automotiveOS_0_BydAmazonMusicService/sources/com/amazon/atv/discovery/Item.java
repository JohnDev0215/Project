package com.amazon.atv.discovery;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Item {
    public final Optional<Analytics> analytics;
    public final Optional<String> debugAttributes;
    public final Optional<ImmutableMap<String, String>> metadata;
    public final ItemType type;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public Analytics analytics;
        public String debugAttributes;
        public ImmutableMap<String, String> metadata;
        public ItemType type;
        public int version;
    }

    protected Item(Builder builder) {
        this.type = (ItemType) Preconditions.checkNotNull(builder.type, "Unexpected null field: type");
        this.version = builder.version;
        this.metadata = Optional.fromNullable(builder.metadata);
        this.analytics = Optional.fromNullable(builder.analytics);
        this.debugAttributes = Optional.fromNullable(builder.debugAttributes);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Item)) {
            return false;
        }
        Item item = (Item) obj;
        return Objects.equal(this.type, item.type) && Objects.equal(Integer.valueOf(this.version), Integer.valueOf(item.version)) && Objects.equal(this.metadata, item.metadata) && Objects.equal(this.analytics, item.analytics) && Objects.equal(this.debugAttributes, item.debugAttributes);
    }

    public int hashCode() {
        return Objects.hashCode(this.type, Integer.valueOf(this.version), this.metadata, this.analytics, this.debugAttributes);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add(AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, this.type).add("version", this.version).add("metadata", this.metadata).add("analytics", this.analytics).add("debugAttributes", this.debugAttributes).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<Item> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.ITEM_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
