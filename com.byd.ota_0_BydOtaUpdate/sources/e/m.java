package e;

import java.util.Collections;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: CookieJar.java */
/* JADX INFO: loaded from: classes.dex */
public interface m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f3614a = new a();

    /* JADX INFO: compiled from: CookieJar.java */
    public class a implements m {
        @Override // e.m
        public List<l> a(HttpUrl httpUrl) {
            return Collections.emptyList();
        }

        @Override // e.m
        public void b(HttpUrl httpUrl, List<l> list) {
        }
    }

    List<l> a(HttpUrl httpUrl);

    void b(HttpUrl httpUrl, List<l> list);
}
