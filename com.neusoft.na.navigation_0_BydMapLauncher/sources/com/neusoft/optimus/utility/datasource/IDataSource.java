package com.neusoft.optimus.utility.datasource;

/* JADX INFO: loaded from: classes2.dex */
public interface IDataSource<DataType> {

    public interface IDataSourceListener<DataType> {
        void onDataSourceChanged();

        void onItemInserted(int i, int i2);

        void onItemInserted(int i, DataType datatype);

        void onItemRemoved(int i, int i2);

        void onItemRemoved(int i, DataType datatype);

        void onItemUpdate(int i, DataType datatype);
    }

    DataType item(int i);

    void registerListener(IDataSourceListener<DataType> iDataSourceListener);

    int size();

    void unregisterListener(IDataSourceListener<DataType> iDataSourceListener);
}
