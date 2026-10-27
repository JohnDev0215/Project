package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.Widget;
import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NavigationsBase extends Widget {

    @NotThreadSafe
    public static abstract class Builder extends Widget.Builder {
    }

    protected NavigationsBase(Builder builder) {
        super(builder);
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NavigationsBase)) {
            return false;
        }
        return super.equals(obj);
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()));
    }

    @Override // com.amazon.atv.xrayv2.Widget
    public String toString() {
        return MoreObjects.toStringHelper(this).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<NavigationsBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.NAVIGATIONSBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
