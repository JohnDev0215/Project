package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.b0;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: compiled from: AbstractSavedStateViewModelFactory.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends b0.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final SavedStateRegistry f2528a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final i f2529b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Bundle f2530c;

    public a(androidx.savedstate.c cVar, Bundle bundle) {
        this.f2528a = cVar.d();
        this.f2529b = cVar.a();
        this.f2530c = bundle;
    }

    @Override // androidx.lifecycle.b0.c, androidx.lifecycle.b0.b
    public final <T extends a0> T a(Class<T> cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return (T) c(canonicalName, cls);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.b0.e
    void b(a0 a0Var) {
        SavedStateHandleController.h(a0Var, this.f2528a, this.f2529b);
    }

    @Override // androidx.lifecycle.b0.c
    public final <T extends a0> T c(String str, Class<T> cls) {
        SavedStateHandleController savedStateHandleControllerJ = SavedStateHandleController.j(this.f2528a, this.f2529b, str, this.f2530c);
        T t6 = (T) d(str, cls, savedStateHandleControllerJ.k());
        t6.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", savedStateHandleControllerJ);
        return t6;
    }

    protected abstract <T extends a0> T d(String str, Class<T> cls, y yVar);
}
