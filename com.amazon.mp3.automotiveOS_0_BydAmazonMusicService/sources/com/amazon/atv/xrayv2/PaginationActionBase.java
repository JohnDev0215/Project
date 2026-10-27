package com.amazon.atv.xrayv2;

import com.amazon.atv.xrayv2.Action;
import com.amazon.avod.util.json.PolymorphicJsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PaginationActionBase extends Action {
    public final String api;
    public final Optional<ImmutableMap<String, String>> parameters;
    public final Optional<ImmutableList<SubstitutionParameter>> substitutionParameters;

    @NotThreadSafe
    public static abstract class Builder extends Action.Builder {
        public String api;
        public ImmutableMap<String, String> parameters;
        public ImmutableList<SubstitutionParameter> substitutionParameters;
    }

    protected PaginationActionBase(Builder builder) {
        super(builder);
        this.parameters = Optional.fromNullable(builder.parameters);
        this.api = (String) Preconditions.checkNotNull(builder.api, "Unexpected null field: api");
        this.substitutionParameters = Optional.fromNullable(builder.substitutionParameters);
    }

    @Override // com.amazon.atv.xrayv2.Action
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PaginationActionBase)) {
            return false;
        }
        PaginationActionBase paginationActionBase = (PaginationActionBase) obj;
        return super.equals(obj) && Objects.equal(this.parameters, paginationActionBase.parameters) && Objects.equal(this.api, paginationActionBase.api) && Objects.equal(this.substitutionParameters, paginationActionBase.substitutionParameters);
    }

    @Override // com.amazon.atv.xrayv2.Action
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.parameters, this.api, this.substitutionParameters);
    }

    @Override // com.amazon.atv.xrayv2.Action
    public String toString() {
        return MoreObjects.toStringHelper(this).add("parameters", this.parameters).add("api", this.api).add("substitutionParameters", this.substitutionParameters).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<PaginationActionBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.PAGINATIONACTIONBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
