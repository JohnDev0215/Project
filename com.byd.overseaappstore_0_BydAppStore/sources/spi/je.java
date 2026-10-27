package spi;

import java.util.Collections;
import java.util.Map;
import spi.ng;

/* JADX INFO: loaded from: classes.dex */
public interface je {
    public static final ng a;

    public class a implements je {
        @Override // spi.je
        public final Map<String, String> a() {
            return Collections.emptyMap();
        }
    }

    static {
        new a();
        a = new ng(new ng.a().a);
    }

    Map<String, String> a();
}
