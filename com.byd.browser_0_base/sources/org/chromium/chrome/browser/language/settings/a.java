package org.chromium.chrome.browser.language.settings;

import androidx.fragment.app.FragmentActivity;
import defpackage.gx;
import defpackage.tp2;
import defpackage.wp2;

/* JADX INFO: compiled from: AppLanguagePreferenceDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public wp2 a;
    public tp2 b;
    public b c;
    public LanguageItemPickerPreference d;
    public FragmentActivity e;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.language.settings.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AppLanguagePreferenceDelegate.java */
    public interface InterfaceC0091a {
        void h();
    }

    /* JADX INFO: compiled from: AppLanguagePreferenceDelegate.java */
    public static class b implements wp2.b {
        public final InterfaceC0091a e;

        public b(gx gxVar) {
            this.e = gxVar;
        }

        @Override // wp2.b
        public final void j(Object obj) {
            InterfaceC0091a interfaceC0091a = this.e;
            if (interfaceC0091a != null) {
                interfaceC0091a.h();
            }
        }
    }
}
