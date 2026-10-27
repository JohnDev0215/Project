package m6;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: StandardAndroidSocketAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
public final class l extends f {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final a f8432j = new a(null);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final Class<? super SSLSocketFactory> f8433h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Class<?> f8434i;

    /* JADX INFO: compiled from: StandardAndroidSocketAdapter.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public static /* synthetic */ k b(a aVar, String str, int i7, Object obj) {
            if ((i7 & 1) != 0) {
                str = "com.android.org.conscrypt";
            }
            return aVar.a(str);
        }

        public final k a(String packageName) {
            kotlin.jvm.internal.l.e(packageName, "packageName");
            try {
                Class<?> cls = Class.forName(packageName + ".OpenSSLSocketImpl");
                Class<?> cls2 = Class.forName(packageName + ".OpenSSLSocketFactoryImpl");
                Class<?> paramsClass = Class.forName(packageName + ".SSLParametersImpl");
                kotlin.jvm.internal.l.d(paramsClass, "paramsClass");
                return new l(cls, cls2, paramsClass);
            } catch (Exception e7) {
                l6.h.f8004c.g().j("unable to load android socket classes", 5, e7);
                return null;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(Class<? super SSLSocket> sslSocketClass, Class<? super SSLSocketFactory> sslSocketFactoryClass, Class<?> paramClass) {
        super(sslSocketClass);
        kotlin.jvm.internal.l.e(sslSocketClass, "sslSocketClass");
        kotlin.jvm.internal.l.e(sslSocketFactoryClass, "sslSocketFactoryClass");
        kotlin.jvm.internal.l.e(paramClass, "paramClass");
        this.f8433h = sslSocketFactoryClass;
        this.f8434i = paramClass;
    }
}
