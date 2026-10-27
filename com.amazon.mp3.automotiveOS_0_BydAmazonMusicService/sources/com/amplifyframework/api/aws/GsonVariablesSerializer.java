package com.amplifyframework.api.aws;

import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.util.GsonFactory;
import com.google.gson.Gson;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class GsonVariablesSerializer implements GraphQLRequest.VariablesSerializer {
    private final Gson gson;

    public int hashCode() {
        return 0;
    }

    public GsonVariablesSerializer() {
        this(GsonFactory.instance());
    }

    public GsonVariablesSerializer(Gson gson) {
        this.gson = gson;
    }

    @Override // com.amplifyframework.api.graphql.GraphQLRequest.VariablesSerializer
    public String serialize(Map<String, Object> map) {
        return this.gson.toJson(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && GsonVariablesSerializer.class == obj.getClass();
    }
}
