package org.chromium.chrome.browser.omnibox.suggestions.base;

import android.content.Context;
import defpackage.bw2;
import defpackage.i2;
import defpackage.v62;
import java.util.List;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: BaseSuggestionViewProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d {
    public static final PropertyModel.r<e> a;
    public static final PropertyModel.r<List<a>> b;
    public static final PropertyModel.r<Runnable> c;
    public static final PropertyModel.r<Runnable> d;
    public static final PropertyModel.r<Runnable> e;
    public static final v62[] f;
    public static final v62[] g;

    static {
        PropertyModel.r<e> rVar = new PropertyModel.r<>();
        a = rVar;
        PropertyModel.r<List<a>> rVar2 = new PropertyModel.r<>();
        b = rVar2;
        PropertyModel.r<Runnable> rVar3 = new PropertyModel.r<>();
        c = rVar3;
        PropertyModel.p pVar = new PropertyModel.p();
        PropertyModel.r<Runnable> rVar4 = new PropertyModel.r<>();
        d = rVar4;
        PropertyModel.r<Runnable> rVar5 = new PropertyModel.r<>();
        e = rVar5;
        v62[] v62VarArr = {rVar, rVar2, rVar3, pVar, rVar4, rVar5};
        f = v62VarArr;
        g = PropertyModel.f(PropertyModel.f(v62VarArr, i2.b), bw2.d);
    }

    /* JADX INFO: compiled from: BaseSuggestionViewProperties.java */
    public static final class a {
        public final e a;
        public final Runnable b;
        public final String c;
        public final String d;

        public a() {
            throw null;
        }

        public a(e eVar, String str, String str2, Runnable runnable) {
            this.a = eVar;
            this.c = str;
            this.d = str2;
            this.b = runnable;
        }

        public a(Context context, e eVar, int i, Runnable runnable) {
            this(eVar, context.getResources().getString(i), (String) null, runnable);
        }
    }
}
