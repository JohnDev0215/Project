package org.chromium.base;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Callback<T> {
    public abstract void onResult(T t);

    /* JADX WARN: Multi-variable type inference failed */
    private void onResultFromNative(Object obj) {
        onResult(obj);
    }

    private void onResultFromNative(boolean z) {
        onResult(Boolean.valueOf(z));
    }

    private void onResultFromNative(int i) {
        onResult(Integer.valueOf(i));
    }
}
