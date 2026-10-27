package com.facebook.datasource;

import com.facebook.common.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
public class SimpleDataSource<T> extends AbstractDataSource<T> {
    private SimpleDataSource() {
    }

    public static <T> SimpleDataSource<T> create() {
        return new SimpleDataSource<>();
    }

    @Override // com.facebook.datasource.AbstractDataSource
    public boolean setFailure(Throwable throwable) {
        return super.setFailure((Throwable) Preconditions.checkNotNull(throwable));
    }
}
