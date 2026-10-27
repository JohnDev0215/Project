package dagger.hilt.android.internal.managers;

import android.app.Activity;
import android.app.Application;
import androidx.activity.ComponentActivity;

/* JADX INFO: compiled from: ActivityComponentManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements o4.b<Object> {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile Object f5977e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Object f5978g = new Object();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected final Activity f5979h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final o4.b<j4.b> f5980i;

    /* JADX INFO: renamed from: dagger.hilt.android.internal.managers.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityComponentManager.java */
    public interface InterfaceC0063a {
        l4.a a();
    }

    public a(Activity activity) {
        this.f5979h = activity;
        this.f5980i = new b((ComponentActivity) activity);
    }

    protected Object a() {
        if (this.f5979h.getApplication() instanceof o4.b) {
            return ((InterfaceC0063a) h4.a.a(this.f5980i, InterfaceC0063a.class)).a().b(this.f5979h).a();
        }
        if (Application.class.equals(this.f5979h.getApplication().getClass())) {
            throw new IllegalStateException("Hilt Activity must be attached to an @HiltAndroidApp Application. Did you forget to specify your Application's class name in your manifest's <application />'s android:name attribute?");
        }
        throw new IllegalStateException("Hilt Activity must be attached to an @AndroidEntryPoint Application. Found: " + this.f5979h.getApplication().getClass());
    }

    @Override // o4.b
    public Object f() {
        if (this.f5977e == null) {
            synchronized (this.f5978g) {
                if (this.f5977e == null) {
                    this.f5977e = a();
                }
            }
        }
        return this.f5977e;
    }
}
