package org.chromium.chrome.browser.omnibox.suggestions;

import J.N;
import android.text.TextUtils;
import defpackage.w80;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.omnibox.AutocompleteMatch;
import org.chromium.components.omnibox.AutocompleteResult;

/* JADX INFO: loaded from: classes2.dex */
public class AutocompleteController implements w80 {
    public long f;
    public final HashSet e = new HashSet();
    public AutocompleteResult g = AutocompleteResult.e;

    public interface a {
        void a(AutocompleteResult autocompleteResult, String str, boolean z);
    }

    public AutocompleteController(Profile profile) {
        this.f = N.MqTA7Skq(this, profile);
    }

    public final AutocompleteMatch a(String str, boolean z) {
        long j = this.f;
        if (j == 0) {
            return null;
        }
        return (AutocompleteMatch) N.MDxZMia5(j, str, z);
    }

    public void addOnSuggestionsReceivedListener(a aVar) {
        this.e.add(aVar);
    }

    public final void b(int i, int i2, String str, String str2, boolean z) {
        if (this.f == 0 || TextUtils.isEmpty(str)) {
            return;
        }
        N.Mc4QrncX(this.f, str2, i2, null, str, i, z, false, false, true);
    }

    public final void c(String str, int i, String str2, String str3) {
        if (this.f == 0 || TextUtils.isEmpty(str2)) {
            return;
        }
        N.MmFptZoy(this.f, str, str2, i, str3);
    }

    @Override // defpackage.w80
    public final void destroy() {
        this.e.clear();
        long j = this.f;
        if (j == 0) {
            return;
        }
        N.Ma0ICZoG(j);
        this.f = 0L;
    }

    @CalledByNative
    public final void onSuggestionsReceived(AutocompleteResult autocompleteResult, String str, boolean z) {
        this.g = autocompleteResult;
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((a) it.next()).a(autocompleteResult, str, z);
        }
    }

    public void removeOnSuggestionsReceivedListener(a aVar) {
        this.e.remove(aVar);
    }
}
