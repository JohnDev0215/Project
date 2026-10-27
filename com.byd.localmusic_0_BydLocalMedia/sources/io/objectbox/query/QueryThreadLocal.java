package io.objectbox.query;

/* JADX INFO: loaded from: classes5.dex */
public class QueryThreadLocal<T> extends ThreadLocal<Query<T>> {
    private final Query<T> original;

    public QueryThreadLocal(Query<T> query) {
        this.original = query;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.lang.ThreadLocal
    public Query<T> initialValue() {
        return this.original.copy();
    }
}
