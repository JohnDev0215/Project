package u1;

import java.util.Collections;
import java.util.Map;
import u1.j;

/* JADX INFO: compiled from: Headers.java */
/* JADX INFO: loaded from: classes.dex */
public interface h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Deprecated
    public static final h f9592a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final h f9593b = new j.a().a();

    /* JADX INFO: compiled from: Headers.java */
    class a implements h {
        a() {
        }

        @Override // u1.h
        public Map<String, String> a() {
            return Collections.emptyMap();
        }
    }

    Map<String, String> a();
}
