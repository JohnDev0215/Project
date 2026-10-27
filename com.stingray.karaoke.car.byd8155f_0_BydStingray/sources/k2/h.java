package k2;

import android.net.Uri;
import e2.InterfaceC1201i;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface h extends InterfaceC1201i {
    void b(E e10);

    void close();

    default Map f() {
        return Collections.emptyMap();
    }

    long g(m mVar);

    Uri j();
}
