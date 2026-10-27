package com.google.common.collect;

import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public interface ListMultimap<K, V> extends Multimap<K, V> {
    @Override // 
    List<V> get(K k);

    @Override // 
    List<V> removeAll(@CheckForNull Object obj);
}
