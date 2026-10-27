package defpackage;

import J.N;
import android.widget.ImageView;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.customtabs.features.toolbar.CustomTabToolbar;
import org.chromium.chrome.browser.omnibox.h;
import org.chromium.chrome.browser.omnibox.suggestions.AutocompleteController;
import org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown;
import org.chromium.chrome.browser.omnibox.suggestions.d;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b50 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ int f;
    public final /* synthetic */ Object g;

    public /* synthetic */ b50(int i, int i2, Object obj) {
        this.e = i2;
        this.g = obj;
        this.f = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList;
        Callback<Integer> callback;
        switch (this.e) {
            case 0:
                CustomTabsConnection customTabsConnection = (CustomTabsConnection) this.g;
                int i = this.f;
                HashSet hashSet = CustomTabsConnection.o;
                customTabsConnection.getClass();
                Object obj = ThreadUtils.a;
                a aVar = customTabsConnection.c;
                synchronized (aVar) {
                    arrayList = new ArrayList();
                    for (Map.Entry entry : aVar.c.entrySet()) {
                        if (((a.e) entry.getValue()).a == i) {
                            arrayList.add((CustomTabsSessionToken) entry.getKey());
                        }
                        break;
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    customTabsConnection.r((CustomTabsSessionToken) it.next(), "onWarmupCompleted", null);
                }
                return;
            case 1:
                cj cjVar = (cj) this.g;
                cjVar.a = false;
                ImageView imageView = cjVar.c;
                imageView.setRotation(0.0f);
                int i2 = cjVar.b;
                int i3 = this.f;
                if (i2 == i3) {
                    imageView.setImageResource(i3);
                    return;
                }
                return;
            case 2:
                CustomTabToolbar.this.I.u(this.f);
                return;
            case 3:
                h hVar = (h) this.g;
                hVar.k.a.setSoftInputMode(this.f);
                hVar.l = h.p;
                return;
            case 4:
                d dVar = (d) this.g;
                AutocompleteController autocompleteController = dVar.w;
                String strN = dVar.k.n();
                long j = autocompleteController.f;
                if (j == 0) {
                    return;
                }
                N.MVpyxSWx(j, strN, this.f);
                return;
            case 5:
                OmniboxSuggestionsDropdown omniboxSuggestionsDropdown = (OmniboxSuggestionsDropdown) this.g;
                int i4 = omniboxSuggestionsDropdown.a1;
                int i5 = this.f;
                if (i4 != i5 || omniboxSuggestionsDropdown.b1 == i5 || (callback = omniboxSuggestionsDropdown.W0) == null) {
                    return;
                }
                callback.onResult(Integer.valueOf(i5));
                omniboxSuggestionsDropdown.b1 = i5;
                return;
            case 6:
            default:
                ((org.chromium.components.download.a) this.g).b.e(this.f);
                return;
            case 7:
                mx1 mx1Var = (mx1) this.g;
                mx1Var.j.l(nx1.c, mx1Var.a(this.f));
                return;
        }
    }
}
