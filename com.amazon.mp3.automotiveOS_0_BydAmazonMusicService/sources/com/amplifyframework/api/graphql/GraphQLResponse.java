package com.amplifyframework.api.graphql;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.util.Immutable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class GraphQLResponse<R> {
    private final R data;
    private final List<Error> errors;

    public interface Factory {
        <R> GraphQLResponse<R> buildResponse(GraphQLRequest<R> graphQLRequest, String str) throws ApiException;
    }

    public GraphQLResponse(@Nullable R r, @Nullable List<Error> list) {
        this.data = r;
        ArrayList arrayList = new ArrayList();
        this.errors = arrayList;
        if (list != null) {
            arrayList.addAll(list);
        }
    }

    @NonNull
    public List<Error> getErrors() {
        return this.errors;
    }

    public boolean hasErrors() {
        return this.errors.size() > 0;
    }

    public R getData() {
        return this.data;
    }

    public boolean hasData() {
        return this.data != null;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GraphQLResponse.class != obj.getClass()) {
            return false;
        }
        GraphQLResponse graphQLResponse = (GraphQLResponse) obj;
        if (ObjectsCompat.equals(this.data, graphQLResponse.data)) {
            return ObjectsCompat.equals(this.errors, graphQLResponse.errors);
        }
        return false;
    }

    public int hashCode() {
        R r = this.data;
        int iHashCode = (r != null ? r.hashCode() : 0) * 31;
        List<Error> list = this.errors;
        return iHashCode + (list != null ? list.hashCode() : 0);
    }

    @NonNull
    public String toString() {
        return "GraphQLResponse{data='" + this.data + "', errors='" + this.errors + "'}";
    }

    public static final class Error {
        private final Map<String, Object> extensions;
        private final List<GraphQLLocation> locations;
        private final String message;
        private final List<GraphQLPathSegment> path;

        public Error(@NonNull String str, @Nullable List<GraphQLLocation> list, @Nullable List<GraphQLPathSegment> list2, @Nullable Map<String, Object> map) {
            Objects.requireNonNull(str);
            this.message = str;
            this.locations = list;
            this.path = list2;
            this.extensions = map;
        }

        @NonNull
        public String getMessage() {
            return this.message;
        }

        @Nullable
        public List<GraphQLLocation> getLocations() {
            return Immutable.of(this.locations);
        }

        @Nullable
        public List<GraphQLPathSegment> getPath() {
            return Immutable.of(this.path);
        }

        @Nullable
        public Map<String, Object> getExtensions() {
            return Immutable.of(this.extensions);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Error.class != obj.getClass()) {
                return false;
            }
            Error error = (Error) obj;
            return ObjectsCompat.equals(this.message, error.message) && ObjectsCompat.equals(this.locations, error.locations) && ObjectsCompat.equals(this.path, error.path) && ObjectsCompat.equals(this.extensions, error.extensions);
        }

        public int hashCode() {
            int iHashCode = this.message.hashCode() * 31;
            List<GraphQLLocation> list = this.locations;
            int iHashCode2 = (iHashCode + (list != null ? list.hashCode() : 0)) * 31;
            List<GraphQLPathSegment> list2 = this.path;
            int iHashCode3 = (iHashCode2 + (list2 != null ? list2.hashCode() : 0)) * 31;
            Map<String, Object> map = this.extensions;
            return iHashCode3 + (map != null ? map.hashCode() : 0);
        }

        public String toString() {
            return "GraphQLResponse.Error{message='" + this.message + "', locations='" + this.locations + "', path='" + this.path + "', extensions='" + this.extensions + "'}";
        }
    }
}
