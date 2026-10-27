package com.amplifyframework.api;

import com.amplifyframework.core.async.AmplifyOperation;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.category.CategoryType;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ApiOperation<R> extends AmplifyOperation<R> implements Cancelable {
    protected ApiOperation(R r) {
        super(CategoryType.API, r);
    }
}
