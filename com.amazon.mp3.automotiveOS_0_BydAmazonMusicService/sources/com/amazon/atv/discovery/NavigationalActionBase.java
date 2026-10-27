package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Action;
import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NavigationalActionBase extends Action {

    @NotThreadSafe
    public static abstract class Builder extends Action.Builder {
    }

    protected NavigationalActionBase(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.discovery.Action
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NavigationalActionBase)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.discovery.Action
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.atv.discovery.Action
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<NavigationalActionBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.NAVIGATIONALACTIONBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
