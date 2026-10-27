package dagger.android;

/* JADX INFO: loaded from: classes7.dex */
public interface AndroidInjector<T> {

    public interface Factory<T> {
        AndroidInjector<T> create(T t);
    }

    void inject(T t);
}
