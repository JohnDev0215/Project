package com.amplifyframework.datastore.model;

import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.datastore.DataStoreException;

/* JADX INFO: loaded from: classes5.dex */
public interface ModelFieldTypeConverter<S, T> {
    Object convertValueFromSource(S s, ModelField modelField) throws DataStoreException;

    Object convertValueFromTarget(T t, ModelField modelField) throws DataStoreException;
}
