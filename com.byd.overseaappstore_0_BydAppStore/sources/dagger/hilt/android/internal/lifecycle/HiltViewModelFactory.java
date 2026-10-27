package dagger.hilt.android.internal.lifecycle;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistryOwner;
import java.io.Closeable;
import java.util.Map;
import java.util.Set;
import spi.e;
import spi.gv;
import spi.ll;
import spi.on;
import spi.t8;

/* JADX INFO: loaded from: classes2.dex */
public final class HiltViewModelFactory implements ViewModelProvider.Factory {
    public final Set<String> a;
    public final ViewModelProvider.Factory b;
    public final a c;

    public class a extends AbstractSavedStateViewModelFactory {
        public final /* synthetic */ gv a;

        public a(HiltViewModelFactory hiltViewModelFactory, gv gvVar) {
            this.a = gvVar;
        }

        @Override // androidx.lifecycle.AbstractSavedStateViewModelFactory
        @NonNull
        public final <T extends ViewModel> T create(@NonNull String str, @NonNull Class<T> cls, @NonNull SavedStateHandle savedStateHandle) {
            final on onVar = new on();
            ll<ViewModel> llVar = ((b) t8.a(this.a.a(savedStateHandle).b(onVar).build(), b.class)).a().get(cls.getName());
            if (llVar != null) {
                T t = (T) llVar.get();
                t.addCloseable(new Closeable() { // from class: spi.me
                    @Override // java.io.Closeable, java.lang.AutoCloseable
                    public final void close() {
                        onVar.a();
                    }
                });
                return t;
            }
            StringBuilder sbK = e.k("Expected the @HiltViewModel-annotated class '");
            sbK.append(cls.getName());
            sbK.append("' to be available in the multi-binding of @HiltViewModelMap but none was found.");
            throw new IllegalStateException(sbK.toString());
        }
    }

    public interface b {
        Map<String, ll<ViewModel>> a();
    }

    public HiltViewModelFactory(@NonNull SavedStateRegistryOwner savedStateRegistryOwner, @Nullable Bundle bundle, @NonNull Set<String> set, @NonNull ViewModelProvider.Factory factory, @NonNull gv gvVar) {
        this.a = set;
        this.b = factory;
        this.c = new a(this, gvVar);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NonNull
    public final <T extends ViewModel> T create(@NonNull Class<T> cls) {
        return this.a.contains(cls.getName()) ? (T) this.c.create(cls) : (T) this.b.create(cls);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    @NonNull
    public final <T extends ViewModel> T create(@NonNull Class<T> cls, @NonNull CreationExtras creationExtras) {
        return this.a.contains(cls.getName()) ? (T) this.c.create(cls, creationExtras) : (T) this.b.create(cls, creationExtras);
    }
}
