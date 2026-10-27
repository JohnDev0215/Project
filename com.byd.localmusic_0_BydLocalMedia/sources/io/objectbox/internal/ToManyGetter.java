package io.objectbox.internal;

import java.io.Serializable;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface ToManyGetter<SOURCE, TARGET> extends Serializable {
    List<TARGET> getToMany(SOURCE source);
}
