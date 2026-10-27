package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.i;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: compiled from: Navigator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class q<D extends i> {

    /* JADX INFO: compiled from: Navigator.java */
    public interface a {
    }

    /* JADX INFO: compiled from: Navigator.java */
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface b {
        String value();
    }

    public abstract D a();

    public abstract i b(D d2, Bundle bundle, n nVar, a aVar);

    public void c(Bundle bundle) {
    }

    public Bundle d() {
        return null;
    }

    public abstract boolean e();
}
