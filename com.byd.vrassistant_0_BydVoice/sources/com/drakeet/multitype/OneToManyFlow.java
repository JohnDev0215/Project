package com.drakeet.multitype;

import kotlin.Metadata;

/* JADX INFO: compiled from: OneToManyFlow.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J;\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042&\u0010\u0005\u001a\u0014\u0012\u0010\b\u0001\u0012\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u00070\u0006\"\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0007H'¢\u0006\u0002\u0010\bJ;\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042&\u0010\u0005\u001a\u0014\u0012\u0010\b\u0001\u0012\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\t0\u0006\"\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\tH'¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/drakeet/multitype/OneToManyFlow;", "T", "", "to", "Lcom/drakeet/multitype/OneToManyEndpoint;", "delegates", "", "Lcom/drakeet/multitype/ItemViewBinder;", "([Lcom/drakeet/multitype/ItemViewBinder;)Lcom/drakeet/multitype/OneToManyEndpoint;", "Lcom/drakeet/multitype/ItemViewDelegate;", "([Lcom/drakeet/multitype/ItemViewDelegate;)Lcom/drakeet/multitype/OneToManyEndpoint;", "multitype"}, k = 1, mv = {1, 4, 2})
public interface OneToManyFlow<T> {
    OneToManyEndpoint<T> to(ItemViewBinder<T, ?>... delegates);

    OneToManyEndpoint<T> to(ItemViewDelegate<T, ?>... delegates);
}
