package OooO00o.OooO00o.OooO0Oo.OooO0Oo.d;

/* JADX INFO: loaded from: classes.dex */
public interface h<T> {

    public static final class a {
        public static <T> void a(h<T> hVar, int i, String str) {
            kotlin.jvm.internal.h.d(hVar, "this");
            hVar.onError("code:" + i + ", " + ((Object) str));
        }
    }

    void a(int i, String str);

    void onError(String str);

    void onSuccess(T t);
}
