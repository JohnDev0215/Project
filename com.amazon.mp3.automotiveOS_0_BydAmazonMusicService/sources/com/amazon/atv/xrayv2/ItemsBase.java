package com.amazon.atv.xrayv2;

import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class ItemsBase {
    public final ImmutableList<Item> itemList;
    public final int version;

    @NotThreadSafe
    public static abstract class Builder {
        public ImmutableList<Item> itemList;
        public int version;
    }

    protected ItemsBase(Builder builder) {
        this.version = builder.version;
        this.itemList = (ImmutableList) Preconditions.checkNotNull(builder.itemList, "Unexpected null field: itemList");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ItemsBase)) {
            return false;
        }
        ItemsBase itemsBase = (ItemsBase) obj;
        return Objects.equal(Integer.valueOf(this.version), Integer.valueOf(itemsBase.version)) && Objects.equal(this.itemList, itemsBase.itemList);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.version), this.itemList);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("version", this.version).add("itemList", this.itemList).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<ItemsBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.ITEMSBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
