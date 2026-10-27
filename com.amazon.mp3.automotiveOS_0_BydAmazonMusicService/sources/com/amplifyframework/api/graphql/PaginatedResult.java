package com.amplifyframework.api.graphql;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.util.ObjectsCompat;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes5.dex */
public final class PaginatedResult<T> implements Iterable<T> {
    private final Iterable<T> items;
    private final GraphQLRequest<PaginatedResult<T>> requestForNextResult;

    public PaginatedResult(@NonNull Iterable<T> iterable, @Nullable GraphQLRequest<PaginatedResult<T>> graphQLRequest) {
        this.requestForNextResult = graphQLRequest;
        this.items = iterable;
    }

    public Iterable<T> getItems() {
        return this.items;
    }

    public boolean hasNextResult() {
        return this.requestForNextResult != null;
    }

    public GraphQLRequest<PaginatedResult<T>> getRequestForNextResult() {
        return this.requestForNextResult;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PaginatedResult.class != obj.getClass()) {
            return false;
        }
        PaginatedResult paginatedResult = (PaginatedResult) obj;
        return ObjectsCompat.equals(this.requestForNextResult, paginatedResult.requestForNextResult) && ObjectsCompat.equals(this.items, paginatedResult.items);
    }

    public int hashCode() {
        return ObjectsCompat.hash(this.requestForNextResult, this.items);
    }

    public String toString() {
        return "PaginatedResult{requestForNextResult=" + this.requestForNextResult + ", items=" + this.items + '}';
    }

    @Override // java.lang.Iterable
    @NonNull
    public Iterator<T> iterator() {
        return this.items.iterator();
    }

    @Override // java.lang.Iterable
    @RequiresApi(api = 24)
    public void forEach(@NonNull Consumer<? super T> consumer) {
        this.items.forEach(consumer);
    }

    @Override // java.lang.Iterable
    @NonNull
    @RequiresApi(api = 24)
    public Spliterator<T> spliterator() {
        return this.items.spliterator();
    }
}
