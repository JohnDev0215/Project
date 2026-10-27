package f;

import java.util.Collections;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: CookieJar.java */
/* JADX INFO: loaded from: classes.dex */
public interface m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f5360a = new a();

    /* JADX INFO: compiled from: CookieJar.java */
    public static class a implements m {
        @Override // f.m
        public List<l> a(HttpUrl httpUrl) {
            return Collections.emptyList();
        }

        @Override // f.m
        public void b(HttpUrl httpUrl, List<l> list) {
        }
    }

    List<l> a(HttpUrl httpUrl);

    void b(HttpUrl httpUrl, List<l> list);
}
