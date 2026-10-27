package org.chromium.chrome.browser.omnibox;

import android.text.TextWatcher;
import android.view.ActionMode;
import defpackage.it3;
import defpackage.v62;
import java.util.Locale;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: UrlBarProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j {
    public static final PropertyModel.r<ActionMode.Callback> a;
    public static final PropertyModel.n b;
    public static final PropertyModel.r<a> c;
    public static final PropertyModel.r<UrlBar.c> d;
    public static final PropertyModel.r<Callback<Boolean>> e;
    public static final PropertyModel.n f;
    public static final PropertyModel.r<UrlBar.d> g;
    public static final PropertyModel.r<b> h;
    public static final PropertyModel.r<Callback<Integer>> i;
    public static final PropertyModel.r<UrlBar.e> j;
    public static final PropertyModel.r<TextWatcher> k;
    public static final PropertyModel.p l;
    public static final PropertyModel.n m;
    public static final PropertyModel.r<it3> n;
    public static final v62[] o;

    /* JADX INFO: compiled from: UrlBarProperties.java */
    public static class a {
        public final String a;
        public final String b;

        public a(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public final String toString() {
            return String.format(Locale.US, "%s: user text: %s; autocomplete text: %s", a.class.getSimpleName(), this.a, this.b);
        }
    }

    /* JADX INFO: compiled from: UrlBarProperties.java */
    public static class b {
        public final CharSequence a;
        public final CharSequence b;
        public final int c;
        public final int d;
        public final int e;

        public b(int i, int i2, int i3, CharSequence charSequence, CharSequence charSequence2) {
            this.a = charSequence;
            this.b = charSequence2;
            this.c = i;
            this.d = i2;
            this.e = i3;
        }

        public final String toString() {
            return String.format(Locale.US, "%s: text: %s; scrollType: %d; selectionState: %d", b.class.getSimpleName(), this.a, Integer.valueOf(this.c), Integer.valueOf(this.e));
        }
    }

    static {
        PropertyModel.r<ActionMode.Callback> rVar = new PropertyModel.r<>();
        a = rVar;
        PropertyModel.n nVar = new PropertyModel.n();
        b = nVar;
        PropertyModel.r<a> rVar2 = new PropertyModel.r<>();
        c = rVar2;
        PropertyModel.r<UrlBar.c> rVar3 = new PropertyModel.r<>();
        d = rVar3;
        PropertyModel.r<Callback<Boolean>> rVar4 = new PropertyModel.r<>();
        e = rVar4;
        PropertyModel.n nVar2 = new PropertyModel.n();
        f = nVar2;
        PropertyModel.r<UrlBar.d> rVar5 = new PropertyModel.r<>();
        g = rVar5;
        PropertyModel.r<b> rVar6 = new PropertyModel.r<>();
        h = rVar6;
        PropertyModel.r<Callback<Integer>> rVar7 = new PropertyModel.r<>();
        i = rVar7;
        PropertyModel.r<UrlBar.e> rVar8 = new PropertyModel.r<>();
        j = rVar8;
        PropertyModel.r<TextWatcher> rVar9 = new PropertyModel.r<>();
        k = rVar9;
        PropertyModel.p pVar = new PropertyModel.p();
        l = pVar;
        PropertyModel.n nVar3 = new PropertyModel.n();
        m = nVar3;
        PropertyModel.r<it3> rVar10 = new PropertyModel.r<>();
        n = rVar10;
        o = new v62[]{rVar, nVar, rVar2, rVar3, rVar4, nVar2, rVar5, rVar6, rVar7, rVar8, rVar9, pVar, nVar3, rVar10};
    }
}
