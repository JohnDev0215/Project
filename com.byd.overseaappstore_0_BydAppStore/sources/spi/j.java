package spi;

import android.app.Activity;
import android.app.Application;
import androidx.activity.ComponentActivity;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class j implements xc<Object> {
    public volatile y4 a;
    public final Object b = new Object();
    public final Activity c;
    public final xc<n> d;

    public interface a {
        i b();
    }

    public j(Activity activity) {
        this.c = activity;
        this.d = new q((ComponentActivity) activity);
    }

    @Override // spi.xc
    public final Object a() {
        if (this.a == null) {
            synchronized (this.b) {
                if (this.a == null) {
                    this.a = (y4) b();
                }
            }
        }
        return this.a;
    }

    public final Object b() {
        if (!(this.c.getApplication() instanceof xc)) {
            if (Application.class.equals(this.c.getApplication().getClass())) {
                throw new IllegalStateException("Hilt Activity must be attached to an @HiltAndroidApp Application. Did you forget to specify your Application's class name in your manifest's <application />'s android:name attribute?");
            }
            StringBuilder sbK = e.k("Hilt Activity must be attached to an @AndroidEntryPoint Application. Found: ");
            sbK.append(this.c.getApplication().getClass());
            throw new IllegalStateException(sbK.toString());
        }
        i iVarB = ((a) t8.a(this.d, a.class)).b();
        Activity activity = this.c;
        x4 x4Var = (x4) iVarB;
        Objects.requireNonNull(x4Var);
        Objects.requireNonNull(activity);
        x4Var.c = activity;
        return new y4(x4Var.a, x4Var.b, activity, null);
    }
}
