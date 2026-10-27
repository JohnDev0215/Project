package com.amplifyframework.api.graphql;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public final class GraphQLLocation {
    private int column;
    private int line;

    public GraphQLLocation(int i, int i2) {
        this.line = i;
        this.column = i2;
    }

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GraphQLLocation.class != obj.getClass()) {
            return false;
        }
        GraphQLLocation graphQLLocation = (GraphQLLocation) obj;
        return this.line == graphQLLocation.line && this.column == graphQLLocation.column;
    }

    public int hashCode() {
        return (this.line * 31) + this.column;
    }

    @NonNull
    public String toString() {
        return "GraphQLLocation{line='" + this.line + "', column='" + this.column + "'}";
    }
}
