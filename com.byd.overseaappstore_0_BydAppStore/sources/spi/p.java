package spi;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import spi.q;

/* JADX INFO: loaded from: classes2.dex */
public final class p implements ViewModelProvider.Factory {
    public final /* synthetic */ Context a;

    public p(q qVar, Context context) {
        this.a = context;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NonNull
    public final <T extends ViewModel> T create(@NonNull Class<T> cls) {
        return new q.b(new a5(((z4) ((q.a) s8.a(this.a, q.a.class)).g()).a, null));
    }
}
