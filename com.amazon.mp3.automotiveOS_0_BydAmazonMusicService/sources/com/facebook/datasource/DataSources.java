package com.facebook.datasource;

import com.facebook.common.internal.Supplier;

/* JADX INFO: loaded from: classes5.dex */
public class DataSources {
    private DataSources() {
    }

    public static <T> DataSource<T> immediateFailedDataSource(Throwable failure) {
        SimpleDataSource simpleDataSourceCreate = SimpleDataSource.create();
        simpleDataSourceCreate.setFailure(failure);
        return simpleDataSourceCreate;
    }

    public static <T> Supplier<DataSource<T>> getFailedDataSourceSupplier(final Throwable failure) {
        return new Supplier<DataSource<T>>() { // from class: com.facebook.datasource.DataSources.1
            @Override // com.facebook.common.internal.Supplier
            public DataSource<T> get() {
                return DataSources.immediateFailedDataSource(failure);
            }
        };
    }
}
