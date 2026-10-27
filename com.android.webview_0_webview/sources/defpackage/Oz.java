package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.TtsPlatformImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Oz extends Su {
    public final /* synthetic */ TtsPlatformImpl h;

    public Oz(TtsPlatformImpl ttsPlatformImpl) {
        this.h = ttsPlatformImpl;
    }

    @Override // defpackage.Su
    public Object c() {
        TraceEvent traceEventM = TraceEvent.M("TtsPlatformImpl:initialize.async_task");
        try {
            Locale[] availableLocales = Locale.getAvailableLocales();
            ArrayList arrayList = new ArrayList();
            for (Locale locale : availableLocales) {
                if (locale.getVariant().isEmpty()) {
                    try {
                        if (this.h.b.isLanguageAvailable(locale) > 0) {
                            String displayLanguage = locale.getDisplayLanguage();
                            if (!locale.getCountry().isEmpty()) {
                                displayLanguage = displayLanguage + " " + locale.getDisplayCountry();
                            }
                            arrayList.add(new Qz(displayLanguage, locale.toString(), null));
                        }
                    } catch (Exception unused) {
                    }
                }
            }
            if (traceEventM != null) {
                traceEventM.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (traceEventM != null) {
                try {
                    traceEventM.close();
                } catch (Throwable th2) {
                    AbstractC0279Pk.a.a(th, th2);
                }
            }
            throw th;
        }
    }

    @Override // defpackage.Su
    public void n(Object obj) {
        TtsPlatformImpl ttsPlatformImpl = this.h;
        ttsPlatformImpl.d = (List) obj;
        ttsPlatformImpl.c = true;
        N.MpJkwIUo(ttsPlatformImpl.a, ttsPlatformImpl);
        Pz pz = this.h.f;
        if (pz != null) {
            pz.a.speak(pz.b, pz.c, pz.d, pz.e, pz.f, pz.g);
        }
        TraceEvent.A("TtsPlatformImpl:initialize");
    }
}
