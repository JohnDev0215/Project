package a.q;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.savedstate.Recreator;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public final class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Bundle f1271b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1272c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Recreator.a f1273d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a.c.a.b.b<String, b> f1270a = new a.c.a.b.b<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1274e = true;

    /* JADX INFO: renamed from: a.q.a$a, reason: collision with other inner class name */
    public interface InterfaceC0026a {
        void a(c cVar);
    }

    public interface b {
        Bundle a();
    }

    public Bundle a(String str) {
        if (!this.f1272c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f1271b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f1271b.remove(str);
        if (this.f1271b.isEmpty()) {
            this.f1271b = null;
        }
        return bundle2;
    }

    public void b(Class<? extends InterfaceC0026a> cls) {
        if (!this.f1274e) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.f1273d == null) {
            this.f1273d = new Recreator.a(this);
        }
        try {
            cls.getDeclaredConstructor(new Class[0]);
            Recreator.a aVar = this.f1273d;
            aVar.f1723a.add(cls.getName());
        } catch (NoSuchMethodException e2) {
            StringBuilder sbD = b.a.a.a.a.d("Class");
            sbD.append(cls.getSimpleName());
            sbD.append(" must have default constructor in order to be automatically recreated");
            throw new IllegalArgumentException(sbD.toString(), e2);
        }
    }
}
