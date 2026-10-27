package com.amazon.atv.discovery;

import com.amazon.atv.discovery.Action;
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
        this.substitutionParameters = Optional.fromNullable(builder.substitutionParameters);
        this.api = (String) Preconditions.checkNotNull(builder.api, "Unexpected null field: api");
    }

    @Override // com.amazon.atv.discovery.Action
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PaginationActionBase)) {
            return false;
        }
        PaginationActionBase paginationActionBase = (PaginationActionBase) obj;
        return super.equals(obj) && Objects.equal(this.parameters, paginationActionBase.parameters) && Objects.equal(this.substitutionParameters, paginationActionBase.substitutionParameters) && Objects.equal(this.api, paginationActionBase.api);
    }

    @Override // com.amazon.atv.discovery.Action
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.parameters, this.substitutionParameters, this.api);
    }

    @Override // com.amazon.atv.discovery.Action
    public String toString() {
        return MoreObjects.toStringHelper(this).add("parameters", this.parameters).add("substitutionParameters", this.substitutionParameters).add("api", this.api).toString();
    }

    @ThreadSafe
    public static class Parser extends PolymorphicJsonParser<PaginationActionBase> {
        public Parser(@Nonnull ObjectMapper objectMapper) {
            super(objectMapper, ParserConstants.PAGINATIONACTIONBASE_TYPE_PARSER_MAP_FUNCTION);
        }
    }
}
