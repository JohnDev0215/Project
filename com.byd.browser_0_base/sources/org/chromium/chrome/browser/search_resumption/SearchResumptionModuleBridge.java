package org.chromium.chrome.browser.search_resumption;

import J.N;
import android.text.TextUtils;
import defpackage.c01;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class SearchResumptionModuleBridge {
    public long a;
    public a b;

    public interface a {
    }

    public SearchResumptionModuleBridge(Profile profile) {
        this.a = N.MSl$dHUT(this, profile);
    }

    public final void a() {
        long j = this.a;
        if (j != 0) {
            N.MJ0smVoV(j, this);
            this.a = 0L;
        }
    }

    @CalledByNative
    public void onSuggestionsReceived(String[] strArr, GURL[] gurlArr) {
        boolean z;
        b bVar = (b) ((c01) this.b).f;
        if (bVar.m != null) {
            return;
        }
        if (gurlArr.length != strArr.length || gurlArr.length < 2) {
            z = false;
        } else {
            int i = 0;
            for (int i2 = 0; i2 < gurlArr.length; i2++) {
                z = true;
                if (!TextUtils.isEmpty(strArr[i2])) {
                    i++;
                }
                if (i >= 2) {
                    break;
                }
            }
            z = false;
        }
        if (z) {
            bVar.f(strArr, gurlArr, false);
        } else {
            c.a(0);
        }
    }
}
