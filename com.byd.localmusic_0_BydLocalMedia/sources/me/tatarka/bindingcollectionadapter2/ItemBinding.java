package me.tatarka.bindingcollectionadapter2;

import android.util.SparseArray;
import androidx.databinding.ViewDataBinding;

/* JADX INFO: loaded from: classes5.dex */
public final class ItemBinding<T> {
    private static final int LAYOUT_NONE = 0;
    private static final int VAR_INVALID = -1;
    public static final int VAR_NONE = 0;
    private SparseArray<Object> extraBindings;
    private int layoutRes;
    private final OnItemBind<T> onItemBind;
    private int variableId;

    public static <T> ItemBinding<T> of(int i, int i2) {
        return new ItemBinding(null).set(i, i2);
    }

    public static <T> ItemBinding<T> of(OnItemBind<T> onItemBind) {
        if (onItemBind == null) {
            throw new NullPointerException("onItemBind == null");
        }
        return new ItemBinding<>(onItemBind);
    }

    private ItemBinding(OnItemBind<T> onItemBind) {
        this.onItemBind = onItemBind;
    }

    public final ItemBinding<T> set(int i, int i2) {
        this.variableId = i;
        this.layoutRes = i2;
        return this;
    }

    public final ItemBinding<T> variableId(int i) {
        this.variableId = i;
        return this;
    }

    public final ItemBinding<T> layoutRes(int i) {
        this.layoutRes = i;
        return this;
    }

    public final ItemBinding<T> bindExtra(int i, Object obj) {
        if (this.extraBindings == null) {
            this.extraBindings = new SparseArray<>(1);
        }
        this.extraBindings.put(i, obj);
        return this;
    }

    public final ItemBinding<T> clearExtras() {
        SparseArray<Object> sparseArray = this.extraBindings;
        if (sparseArray != null) {
            sparseArray.clear();
        }
        return this;
    }

    public ItemBinding<T> removeExtra(int i) {
        SparseArray<Object> sparseArray = this.extraBindings;
        if (sparseArray != null) {
            sparseArray.remove(i);
        }
        return this;
    }

    public final int variableId() {
        return this.variableId;
    }

    public final int layoutRes() {
        return this.layoutRes;
    }

    public final Object extraBinding(int i) {
        SparseArray<Object> sparseArray = this.extraBindings;
        if (sparseArray == null) {
            return null;
        }
        return sparseArray.get(i);
    }

    public void onItemBind(int i, T t) {
        OnItemBind<T> onItemBind = this.onItemBind;
        if (onItemBind != null) {
            this.variableId = -1;
            this.layoutRes = 0;
            onItemBind.onItemBind(this, i, t);
            if (this.variableId == -1) {
                throw new IllegalStateException("variableId not set in onItemBind()");
            }
            if (this.layoutRes == 0) {
                throw new IllegalStateException("layoutRes not set in onItemBind()");
            }
        }
    }

    public boolean bind(ViewDataBinding viewDataBinding, T t) {
        int i = this.variableId;
        if (i == 0) {
            return false;
        }
        if (!viewDataBinding.setVariable(i, t)) {
            Utils.throwMissingVariable(viewDataBinding, this.variableId, this.layoutRes);
        }
        SparseArray<Object> sparseArray = this.extraBindings;
        if (sparseArray == null) {
            return true;
        }
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            int iKeyAt = this.extraBindings.keyAt(i2);
            Object objValueAt = this.extraBindings.valueAt(i2);
            if (iKeyAt != 0) {
                viewDataBinding.setVariable(iKeyAt, objValueAt);
            }
        }
        return true;
    }
}
