package org.chromium.content.browser;

import J.N;
import defpackage.i9;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.chromium.base.TraceEvent;
import org.chromium.content.browser.TtsPlatformImpl;

/* JADX INFO: compiled from: TtsPlatformImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class a extends i9<List<TtsPlatformImpl.c>> {
    public final /* synthetic */ TtsPlatformImpl.b h;

    public a(TtsPlatformImpl.b bVar) {
        this.h = bVar;
    }

    @Override // defpackage.i9
    public final List<TtsPlatformImpl.c> b() {
        TraceEvent traceEventH0 = TraceEvent.H0("TtsEngine:initialize_default.async_task", null);
        try {
            Locale[] availableLocales = Locale.getAvailableLocales();
            ArrayList arrayList = new ArrayList();
            for (Locale locale : availableLocales) {
                if (locale.getVariant().isEmpty()) {
                    try {
                        if (this.h.a.isLanguageAvailable(locale) > 0) {
                            String displayLanguage = locale.getDisplayLanguage();
                            if (!locale.getCountry().isEmpty()) {
                                displayLanguage = displayLanguage + " " + locale.getDisplayCountry();
                            }
                            arrayList.add(new TtsPlatformImpl.c(displayLanguage, locale.toString()));
                        }
                    } catch (Exception unused) {
                    }
                }
            }
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused2) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.i9
    public final void l(List<TtsPlatformImpl.c> list) {
        TtsPlatformImpl.b bVar = this.h;
        bVar.b = list;
        bVar.c = true;
        N.MpJkwIUo(bVar.f);
        TtsPlatformImpl.a aVar = bVar.e;
        if (aVar != null) {
            aVar.a.speak(aVar.b, aVar.c, aVar.d, aVar.e, aVar.f, aVar.g, aVar.h);
        }
        TraceEvent.F(bVar.hashCode(), "TtsEngine:initialize_default");
    }
}
