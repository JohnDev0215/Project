package dagger.android;

import android.content.ContentProvider;

/* JADX INFO: loaded from: classes2.dex */
public abstract class DaggerContentProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public boolean onCreate() {
        AndroidInjection.inject(this);
        return true;
    }
}
