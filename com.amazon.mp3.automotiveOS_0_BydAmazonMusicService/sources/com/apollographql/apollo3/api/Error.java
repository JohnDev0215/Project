package com.apollographql.apollo3.api;

import com.BV.LinearGradient.LinearGradientManager;
import com.amazonaws.http.HttpHeader;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Error.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0001\u0019B_\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b\u0012\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0010\u0012\u0016\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u0010¢\u0006\u0004\b\u0017\u0010\u0018J\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u001f\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001f\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\rR'\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R'\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\u0014¨\u0006\u001a"}, d2 = {"Lcom/apollographql/apollo3/api/Error;", "", "", "toString", "message", "Ljava/lang/String;", "getMessage", "()Ljava/lang/String;", "", "Lcom/apollographql/apollo3/api/Error$Location;", LinearGradientManager.PROP_LOCATIONS, "Ljava/util/List;", "getLocations", "()Ljava/util/List;", "path", "getPath", "", "extensions", "Ljava/util/Map;", "getExtensions", "()Ljava/util/Map;", "nonStandardFields", "getNonStandardFields", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/Map;Ljava/util/Map;)V", HttpHeader.LOCATION, "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class Error {
    private final Map<String, Object> extensions;
    private final List<Location> locations;
    private final String message;
    private final Map<String, Object> nonStandardFields;
    private final List<Object> path;

    public Error(String message, List<Location> list, List<? extends Object> list2, Map<String, ? extends Object> map, Map<String, ? extends Object> map2) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.message = message;
        this.locations = list;
        this.path = list2;
        this.extensions = map;
        this.nonStandardFields = map2;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Map<String, Object> getExtensions() {
        return this.extensions;
    }

    public String toString() {
        return "Error(message = " + this.message + ", locations = " + this.locations + ", path=" + this.path + ", extensions = " + this.extensions + ", nonStandardFields = " + this.nonStandardFields + ')';
    }

    /* JADX INFO: compiled from: Error.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo3/api/Error$Location;", "", "line", "", "column", "(II)V", "getColumn", "()I", "getLine", "toString", "", "apollo-api"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Location {
        private final int column;
        private final int line;

        public Location(int i, int i2) {
            this.line = i;
            this.column = i2;
        }

        public final int getLine() {
            return this.line;
        }

        public final int getColumn() {
            return this.column;
        }

        public String toString() {
            return "Location(line = " + this.line + ", column = " + this.column + ')';
        }
    }
}
