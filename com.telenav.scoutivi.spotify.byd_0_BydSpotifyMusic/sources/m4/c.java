package m4;

import android.os.Bundle;
import androidx.lifecycle.a0;
import androidx.lifecycle.b0;
import androidx.lifecycle.y;
import java.util.Map;
import java.util.Set;
import l4.e;

/* JADX INFO: compiled from: HiltViewModelFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class c implements b0.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Set<String> f8397a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b0.b f8398b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final androidx.lifecycle.a f8399c;

    /* JADX INFO: compiled from: HiltViewModelFactory.java */
    class a extends androidx.lifecycle.a {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ e f8400d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(androidx.savedstate.c cVar, Bundle bundle, e eVar) {
            super(cVar, bundle);
            this.f8400d = eVar;
        }

        @Override // androidx.lifecycle.a
        protected <T extends a0> T d(String str, Class<T> cls, y yVar) {
            f5.a<a0> aVar = ((b) h4.a.a(this.f8400d.b(yVar).a(), b.class)).a().get(cls.getName());
            if (aVar != null) {
                return (T) aVar.get();
            }
            throw new IllegalStateException("Expected the @HiltViewModel-annotated class '" + cls.getName() + "' to be available in the multi-binding of @HiltViewModelMap but none was found.");
        }
    }

    /* JADX INFO: compiled from: HiltViewModelFactory.java */
    public interface b {
        Map<String, f5.a<a0>> a();
    }

    public c(androidx.savedstate.c cVar, Bundle bundle, Set<String> set, b0.b bVar, e eVar) {
        this.f8397a = set;
        this.f8398b = bVar;
        this.f8399c = new a(cVar, bundle, eVar);
    }

    @Override // androidx.lifecycle.b0.b
    public <T extends a0> T a(Class<T> cls) {
        return this.f8397a.contains(cls.getName()) ? (T) this.f8399c.a(cls) : (T) this.f8398b.a(cls);
    }
}
