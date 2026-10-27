package com.amplifyframework.api.graphql;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.api.graphql.GraphQLRequest;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class SimpleGraphQLRequest<R> extends GraphQLRequest<R> {
    private final String document;
    private final Map<String, Object> variables;

    public SimpleGraphQLRequest(String str, Type type, GraphQLRequest.VariablesSerializer variablesSerializer) {
        this(str, Collections.emptyMap(), type, variablesSerializer);
    }

    public SimpleGraphQLRequest(String str, Map<String, Object> map, Type type, GraphQLRequest.VariablesSerializer variablesSerializer) {
        super(type, variablesSerializer);
        this.variables = map;
        this.document = str;
    }

    @Override // com.amplifyframework.api.graphql.GraphQLRequest
    public String getQuery() {
        return this.document;
    }

    @Override // com.amplifyframework.api.graphql.GraphQLRequest
    public Map<String, Object> getVariables() {
        return this.variables;
    }

    @Override // com.amplifyframework.api.graphql.GraphQLRequest
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SimpleGraphQLRequest.class != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        SimpleGraphQLRequest simpleGraphQLRequest = (SimpleGraphQLRequest) obj;
        return ObjectsCompat.equals(this.document, simpleGraphQLRequest.document) && ObjectsCompat.equals(this.variables, simpleGraphQLRequest.variables);
    }

    @Override // com.amplifyframework.api.graphql.GraphQLRequest
    public int hashCode() {
        return ObjectsCompat.hash(Integer.valueOf(super.hashCode()), this.document, this.variables);
    }
}
