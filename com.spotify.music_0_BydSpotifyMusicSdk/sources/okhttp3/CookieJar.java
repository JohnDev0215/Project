package okhttp3;

import java.util.List;
import kotlin.collections.EmptyList;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public interface CookieJar {
    public static final CookieJar a;

    public static final class Companion {
        public static final /* synthetic */ int a = 0;

        public static final class NoCookies implements CookieJar {
            @Override // okhttp3.CookieJar
            public final EmptyList a(HttpUrl httpUrl) {
                v03.j(httpUrl, "url");
                return EmptyList.c;
            }

            @Override // okhttp3.CookieJar
            public final void b(HttpUrl httpUrl, List list) {
                v03.j(httpUrl, "url");
            }
        }

        static {
            new Companion();
        }

        private Companion() {
        }
    }

    static {
        int i = Companion.a;
        a = new Companion.NoCookies();
    }

    EmptyList a(HttpUrl httpUrl);

    void b(HttpUrl httpUrl, List list);
}
