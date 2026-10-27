package org.apache.shiro.util;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractFactory<T> implements Factory<T> {
    private boolean singleton = true;
    private T singletonInstance;

    protected abstract T createInstance();

    public boolean isSingleton() {
        return this.singleton;
    }

    public void setSingleton(boolean z) {
        this.singleton = z;
    }

    @Override // org.apache.shiro.util.Factory
    public T getInstance() {
        T tCreateInstance;
        if (isSingleton()) {
            if (this.singletonInstance == null) {
                this.singletonInstance = createInstance();
            }
            tCreateInstance = this.singletonInstance;
        } else {
            tCreateInstance = createInstance();
        }
        if (tCreateInstance != null) {
            return tCreateInstance;
        }
        throw new IllegalStateException("Factory 'createInstance' implementation returned a null object.");
    }
}
