package defpackage;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public abstract class H0 {
    private static final I0 a;

    static {
        I0 i0 = null;
        try {
            i0 = (I0) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (i0 == null) {
            i0 = new I0();
        }
        a = i0;
    }

    public static String a(ViewOnClickListenerC0202o0 viewOnClickListenerC0202o0) {
        a.getClass();
        String string = viewOnClickListenerC0202o0.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }
}
