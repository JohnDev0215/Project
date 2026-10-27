package org.chromium.chrome.browser.feedback;

import J.N;
import defpackage.em0;
import defpackage.w8;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes.dex */
public class FamilyInfoFeedbackSource implements w8 {
    public final Profile a;
    public final HashMap b = new HashMap();
    public boolean c;
    public Runnable d;

    public FamilyInfoFeedbackSource(Profile profile) {
        this.a = profile;
    }

    @Override // defpackage.w8
    public final boolean a() {
        return this.c;
    }

    @Override // defpackage.w8
    public final void b(em0 em0Var) {
        this.d = em0Var;
        N.MYGJsJCo(this, this.a);
    }

    @Override // defpackage.gm0
    public final Map<String, String> c() {
        return this.b;
    }

    @CalledByNative
    public final void processPrimaryAccountFamilyInfo(String str, String str2) {
        boolean zIsEmpty = str.isEmpty();
        HashMap map = this.b;
        if (!zIsEmpty) {
            map.put("Family_Member_Role", str);
        }
        if (str2 != null) {
            map.put("Parental_Control_Sites_Child", str2);
        }
        this.c = true;
        Runnable runnable = this.d;
        if (runnable != null) {
            runnable.run();
        }
    }
}
