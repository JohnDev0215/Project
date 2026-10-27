package t1;

/* JADX INFO: compiled from: LottieCompositionCache.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f f8010a = new f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final v.e<String, o1.e> f8011b = new v.e<>(20);

    public static f b() {
        return f8010a;
    }

    public o1.e a(String str) {
        if (str == null) {
            return null;
        }
        return this.f8011b.c(str);
    }

    public void c(String str, o1.e eVar) {
        if (str == null) {
            return;
        }
        this.f8011b.d(str, eVar);
    }
}
