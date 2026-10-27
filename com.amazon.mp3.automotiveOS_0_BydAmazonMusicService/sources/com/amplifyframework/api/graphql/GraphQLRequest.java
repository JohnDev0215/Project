package com.amplifyframework.api.graphql;

import android.text.TextUtils;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.model.ModelIdentifier;
import com.amplifyframework.util.Wrap;
import com.google.android.gms.actions.SearchIntents;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public abstract class GraphQLRequest<R> {
    private final Type responseType;
    private final VariablesSerializer variablesSerializer;

    public interface VariablesSerializer {
        String serialize(Map<String, Object> map);
    }

    public abstract String getQuery();

    public abstract Map<String, Object> getVariables();

    public GraphQLRequest(Type type, VariablesSerializer variablesSerializer) {
        this.responseType = type;
        this.variablesSerializer = variablesSerializer;
    }

    public String getContent() {
        return Wrap.inBraces(TextUtils.join(", ", Arrays.asList(Wrap.inDoubleQuotes(SearchIntents.EXTRA_QUERY) + ": " + Wrap.inDoubleQuotes(getQuery().replace(ModelIdentifier.Helper.PRIMARY_KEY_ENCAPSULATE_CHAR, "\\\"").replace("\n", "\\n")), Wrap.inDoubleQuotes("variables") + ": " + (getVariables().isEmpty() ? null : this.variablesSerializer.serialize(getVariables())))));
    }

    public Type getResponseType() {
        return this.responseType;
    }

    protected VariablesSerializer getVariablesSerializer() {
        return this.variablesSerializer;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GraphQLRequest graphQLRequest = (GraphQLRequest) obj;
        return ObjectsCompat.equals(this.responseType, graphQLRequest.responseType) && ObjectsCompat.equals(this.variablesSerializer, graphQLRequest.variablesSerializer);
    }

    public int hashCode() {
        int iHashCode = this.responseType.hashCode() * 31;
        VariablesSerializer variablesSerializer = this.variablesSerializer;
        return iHashCode + (variablesSerializer != null ? variablesSerializer.hashCode() : 0);
    }

    public String toString() {
        return "GraphQLRequest{, responseType='" + this.responseType + "', variablesSerializer='" + this.variablesSerializer + "'}";
    }
}
