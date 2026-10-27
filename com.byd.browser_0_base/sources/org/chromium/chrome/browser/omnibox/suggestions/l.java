package org.chromium.chrome.browser.omnibox.suggestions;

import android.view.ViewGroup;

/* JADX INFO: compiled from: SuggestionListViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class l {

    /* JADX INFO: compiled from: SuggestionListViewBinder.java */
    public static class a {
        public final ViewGroup a;
        public final OmniboxSuggestionsDropdown b;
        public final SuggestionLayout c;
        public Runnable d;

        public a(ViewGroup viewGroup, OmniboxSuggestionsDropdown omniboxSuggestionsDropdown, SuggestionLayout suggestionLayout) {
            this.a = viewGroup;
            this.b = omniboxSuggestionsDropdown;
            this.c = suggestionLayout;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(org.chromium.chrome.browser.omnibox.suggestions.l.a r4, org.chromium.ui.modelutil.PropertyModel r5) {
        /*
            org.chromium.ui.modelutil.PropertyModel$r<ya1> r0 = org.chromium.chrome.browser.omnibox.suggestions.i.c
            java.lang.Object r5 = r5.j(r0)
            ya1 r5 = (defpackage.ya1) r5
            android.view.ViewGroup r0 = r4.a
            r1 = 0
            r0.setVisibility(r1)
            int r0 = r5.size()
            r2 = 8
            if (r0 != 0) goto L2e
            java.util.ArrayList<org.chromium.components.omnibox.AutocompleteMatch> r0 = defpackage.kg2.a
            android.content.SharedPreferences r0 = xz.a.a
            java.lang.String r3 = "isNtp"
            boolean r0 = r0.getBoolean(r3, r1)
            if (r0 == 0) goto L2e
            java.util.ArrayList r0 = defpackage.kg2.a()
            int r0 = r0.size()
            if (r0 <= 0) goto L2e
            r0 = r1
            goto L2f
        L2e:
            r0 = r2
        L2f:
            org.chromium.chrome.browser.omnibox.suggestions.SuggestionLayout r3 = r4.c
            r3.setVisibility(r0)
            int r5 = r5.size()
            if (r5 != 0) goto L3b
            r1 = r2
        L3b:
            org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown r4 = r4.b
            r4.setVisibility(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.suggestions.l.a(org.chromium.chrome.browser.omnibox.suggestions.l$a, org.chromium.ui.modelutil.PropertyModel):void");
    }
}
