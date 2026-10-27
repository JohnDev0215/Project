package com.neusoft.optimus.utility.datasource;

import android.os.Handler;
import android.os.Looper;
import com.neusoft.optimus.utility.datasource.IDataSource;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DataSourceImp<ItemType> implements IDataSource<ItemType> {
    private final boolean _isRefObject;
    private List<IDataSource.IDataSourceListener<ItemType>> _listeners = new ArrayList();
    protected List<ItemType> _dataSource = new ArrayList();
    private final long _id = System.currentTimeMillis();

    protected abstract ItemType acquireItem(int i);

    protected DataSourceImp(boolean z) {
        this._isRefObject = z;
    }

    public void inject(DataSourceImp<ItemType> dataSourceImp, boolean z) {
        inject(dataSourceImp.getObject(), z);
    }

    protected void inject(final List<ItemType> list, final boolean z) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.optimus.utility.datasource.DataSourceImp.1
            @Override // java.lang.Runnable
            public void run() {
                DataSourceImp.this.injectInternal(list, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void injectInternal(List<ItemType> list, boolean z) {
        if (z) {
            this._dataSource = list;
            notifyDataSourceChanged();
        } else {
            int size = size();
            this._dataSource.addAll(list);
            notifyItemInserted(size, (list.size() + size) - 1);
        }
    }

    public void update(final int i, final ItemType itemtype) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.optimus.utility.datasource.DataSourceImp.2
            @Override // java.lang.Runnable
            public void run() {
                DataSourceImp.this.updateInternal(i, itemtype);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInternal(int i, ItemType itemtype) {
        if (isInvalidate(i)) {
            return;
        }
        if (!this._isRefObject) {
            this._dataSource.remove(i);
            this._dataSource.add(i, itemtype);
        }
        notifyItemUpdate(i, itemtype);
    }

    protected boolean isInvalidate(int i) {
        return this._dataSource == null || i == -1 || i >= size();
    }

    public void insert(ItemType itemtype) {
        insert(itemtype, -1);
    }

    public void insert(final ItemType itemtype, final int i) {
        if (itemtype == null || this._dataSource == null) {
            return;
        }
        if (i == -1 || i >= size() - 1) {
            i = size();
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.optimus.utility.datasource.DataSourceImp.3
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference fix 'apply assigned field type' failed
            java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
            	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
            	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
            	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
             */
            @Override // java.lang.Runnable
            public void run() {
                DataSourceImp.this.insertInternal(itemtype, i, true);
            }
        });
    }

    protected void insertInternal(ItemType itemtype, int i, boolean z) {
        this._dataSource.add(i, itemtype);
        if (z) {
            notifyItemInserted(i, itemtype);
        }
    }

    public void remove(final int i) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.optimus.utility.datasource.DataSourceImp.4
            @Override // java.lang.Runnable
            public void run() {
                DataSourceImp.this.removeInternal(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void removeInternal(int i) {
        if (isInvalidate(i)) {
            return;
        }
        notifyItemRemoved(i, this._dataSource.remove(i));
    }

    public void removeAll() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.neusoft.optimus.utility.datasource.DataSourceImp.5
            @Override // java.lang.Runnable
            public void run() {
                DataSourceImp.this.removeAllInternal();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void removeAllInternal() {
        this._dataSource.clear();
        notifyDataSourceChanged();
    }

    public void notifyDataSourceChanged() {
        synchronized (this) {
            for (int i = 0; i < this._listeners.size(); i++) {
                IDataSource.IDataSourceListener<ItemType> iDataSourceListener = this._listeners.get(i);
                if (iDataSourceListener != null) {
                    iDataSourceListener.onDataSourceChanged();
                }
            }
        }
    }

    protected void notifyItemUpdate(int i, ItemType itemtype) {
        synchronized (this) {
            for (int i2 = 0; i2 < this._listeners.size(); i2++) {
                IDataSource.IDataSourceListener<ItemType> iDataSourceListener = this._listeners.get(i2);
                if (iDataSourceListener != null) {
                    iDataSourceListener.onItemUpdate(i, itemtype);
                }
            }
        }
    }

    protected void notifyItemInserted(int i, ItemType itemtype) {
        synchronized (this) {
            for (int i2 = 0; i2 < this._listeners.size(); i2++) {
                IDataSource.IDataSourceListener<ItemType> iDataSourceListener = this._listeners.get(i2);
                if (iDataSourceListener != null) {
                    iDataSourceListener.onItemInserted(i, itemtype);
                }
            }
        }
    }

    protected void notifyItemInserted(int i, int i2) {
        synchronized (this) {
            for (int i3 = 0; i3 < this._listeners.size(); i3++) {
                IDataSource.IDataSourceListener<ItemType> iDataSourceListener = this._listeners.get(i3);
                if (iDataSourceListener != null) {
                    iDataSourceListener.onItemInserted(i, i2);
                }
            }
        }
    }

    protected void notifyItemRemoved(int i, ItemType itemtype) {
        synchronized (this) {
            for (int i2 = 0; i2 < this._listeners.size(); i2++) {
                IDataSource.IDataSourceListener<ItemType> iDataSourceListener = this._listeners.get(i2);
                if (iDataSourceListener != null) {
                    iDataSourceListener.onItemRemoved(i, itemtype);
                }
            }
        }
    }

    @Override // com.neusoft.optimus.utility.datasource.IDataSource
    public int size() {
        List<ItemType> list = this._dataSource;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // com.neusoft.optimus.utility.datasource.IDataSource
    public ItemType item(int i) {
        return acquireItem(i);
    }

    @Override // com.neusoft.optimus.utility.datasource.IDataSource
    public void registerListener(IDataSource.IDataSourceListener<ItemType> iDataSourceListener) {
        if (iDataSourceListener == null) {
            return;
        }
        synchronized (this) {
            this._listeners.add(iDataSourceListener);
        }
    }

    protected void clear() {
        synchronized (this) {
            this._listeners.clear();
        }
        this._dataSource.clear();
    }

    @Override // com.neusoft.optimus.utility.datasource.IDataSource
    public void unregisterListener(IDataSource.IDataSourceListener<ItemType> iDataSourceListener) {
        synchronized (this) {
            this._listeners.remove(iDataSourceListener);
        }
    }

    protected List<ItemType> getObject() {
        return this._dataSource;
    }

    public DataSourceImp<ItemType>.Transaction beginTransaction() {
        return new Transaction();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this._id == ((DataSourceImp) obj)._id;
    }

    public int hashCode() {
        long j = this._id;
        return (int) (j ^ (j >>> 32));
    }

    public class Transaction {
        protected final List<ItemType> _dataSource = new ArrayList();

        public Transaction() {
        }

        public void insert(ItemType itemtype) {
            this._dataSource.add(itemtype);
        }

        public void commit() {
            DataSourceImp.this.inject((List) this._dataSource, false);
        }
    }
}
