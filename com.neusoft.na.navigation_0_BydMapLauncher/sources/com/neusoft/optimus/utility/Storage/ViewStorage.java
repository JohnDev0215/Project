package com.neusoft.optimus.utility.Storage;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ViewStorage<T> {
    private List<T> _viewList = new ArrayList();

    public void attachView(T t) {
        if (t == null || this._viewList.contains(t)) {
            return;
        }
        this._viewList.add(t);
    }

    public void detachView(T t) {
        if (t == null) {
            return;
        }
        this._viewList.remove(t);
    }

    public int getViewCount() {
        return this._viewList.size();
    }

    public T getView(int i) {
        return this._viewList.get(i);
    }
}
