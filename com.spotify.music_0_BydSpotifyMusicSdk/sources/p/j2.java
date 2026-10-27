package p;

import android.content.Context;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealInterceptorChain;

/* JADX INFO: loaded from: classes.dex */
public final class j2 implements Interceptor {
    public final Context b;

    public j2(Context context) {
        v03.j(context, "context");
        this.b = context;
    }

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain chain) {
        String strReplaceAll;
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Request requestB = realInterceptorChain.e;
        if (requestB.c.a("Accept-Language") == null) {
            Context context = this.b;
            v03.j(context, "context");
            ArrayList arrayList = new ArrayList(2);
            int i = 0;
            Locale localeB = new ac1(new cc1(yw.a(context.getResources().getConfiguration()))).b(0);
            if (localeB != null) {
                arrayList.add(localeB);
            }
            Locale locale = Locale.US;
            if (!v03.b(locale, localeB)) {
                v03.i(locale, "US");
                arrayList.add(locale);
            }
            int i2 = 1;
            List listSubList = arrayList.subList(0, Math.min(arrayList.size(), 1));
            ArrayList<String> arrayList2 = new ArrayList(3);
            StringBuilder sb = new StringBuilder(32);
            NumberFormat numberInstance = NumberFormat.getNumberInstance(locale);
            v03.g(numberInstance, "null cannot be cast to non-null type java.text.DecimalFormat");
            DecimalFormat decimalFormat = (DecimalFormat) numberInstance;
            decimalFormat.applyPattern("#.##");
            Iterator it = listSubList.iterator();
            double size = 1.0d;
            while (it.hasNext()) {
                String strA = d7.a("-", (Locale) it.next());
                sb.delete(i, sb.length());
                sb.append(strA);
                if (listSubList.size() > i2) {
                    sb.append(";q=");
                    sb.append(decimalFormat.format(size));
                }
                String string = sb.toString();
                v03.i(string, "acceptLanguageValueBuilder.toString()");
                arrayList2.add(string);
                size -= size / ((double) listSubList.size());
                i = 0;
                i2 = 1;
            }
            StringBuilder sb2 = new StringBuilder(32);
            for (String str : arrayList2) {
                if (sb2.length() != 0) {
                    sb2.append(", ");
                }
                sb2.append(str);
            }
            String string2 = sb2.toString();
            try {
                strReplaceAll = Normalizer.normalize(string2, Normalizer.Form.NFKD).replaceAll("[^\\x20-\\x7E]", "");
            } catch (AssertionError e) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException unused) {
                }
                try {
                    try {
                        String strNormalize = Normalizer.normalize(string2, Normalizer.Form.NFKD);
                        lc.c("normalize worked after sleeping");
                        strReplaceAll = strNormalize.replaceAll("[^\\x20-\\x7E]", "");
                    } catch (ClassNotFoundException e2) {
                        throw new AssertionError(e2);
                    } catch (IllegalAccessException e3) {
                        throw new AssertionError(e3);
                    } catch (NoSuchFieldException e4) {
                        throw new AssertionError(e4);
                    }
                } catch (AssertionError unused2) {
                    Class<?> cls = Class.forName("libcore.icu.NativeNormalizer$1");
                    Field declaredField = cls.getDeclaredField("$SwitchMap$java$text$Normalizer$Form");
                    declaredField.setAccessible(true);
                    int[] iArr = (int[]) declaredField.get(cls);
                    throw new RuntimeException(String.format("switchmap overwritten? 0x%08x,0x%08x,0x%08x,0x%08x", Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]), Integer.valueOf(iArr[2]), Integer.valueOf(iArr[3])), e);
                }
            }
            v03.i(strReplaceAll, "normalizeAndStripNonAsci…geHeaderValue.toString())");
            Request.Builder builderB = requestB.b();
            builderB.d("Accept-Language", strReplaceAll);
            requestB = builderB.b();
        }
        return realInterceptorChain.a(requestB);
    }
}
