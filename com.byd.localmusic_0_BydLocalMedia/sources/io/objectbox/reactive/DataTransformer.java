package io.objectbox.reactive;

/* JADX INFO: loaded from: classes5.dex */
public interface DataTransformer<FROM, TO> {
    TO transform(FROM from) throws Exception;
}
