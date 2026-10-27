package org.chromium.components.browser_ui.share;

import J.N;
import android.content.ComponentName;
import android.net.Uri;
import android.text.TextUtils;
import defpackage.C0175b;
import java.util.ArrayList;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ShareParams.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    public final WindowAndroid a;
    public final String b;
    public final String c;
    public final String d;
    public String e;
    public final String f;
    public final ArrayList<Uri> g;
    public final ArrayList<String> h;
    public final Uri i;
    public final Uri j;
    public final Boolean k;
    public final String l;
    public final String m;
    public b n;

    /* JADX INFO: renamed from: org.chromium.components.browser_ui.share.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ShareParams.java */
    public static class C0124a {
        public final WindowAndroid a;
        public final String b;
        public String c;
        public String d;
        public String e;
        public ArrayList<Uri> f;
        public b g;

        public C0124a(WindowAndroid windowAndroid, String str, String str2) {
            this.a = windowAndroid;
            this.d = str2;
            this.b = str;
        }

        public final a a() {
            if (!TextUtils.isEmpty(this.d)) {
                String strJ = this.d;
                if (!TextUtils.isEmpty(strJ)) {
                    strJ = ((GURL) N.M1WDPiaY(strJ)).j();
                }
                this.d = strJ;
            }
            return new a(this.a, this.b, this.c, null, this.d, this.e, this.f, null, null, null, this.g, null, null, null);
        }
    }

    /* JADX INFO: compiled from: ShareParams.java */
    public interface b {
        void a(ComponentName componentName);

        void onCancel();
    }

    public a() {
        throw null;
    }

    public a(WindowAndroid windowAndroid, String str, String str2, String str3, String str4, String str5, ArrayList arrayList, ArrayList arrayList2, Uri uri, Uri uri2, b bVar, Boolean bool, String str6, String str7) {
        this.a = windowAndroid;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = arrayList;
        this.h = arrayList2;
        this.i = uri;
        this.j = uri2;
        this.n = bVar;
        this.k = bool;
        this.l = str6;
        this.m = str7;
    }

    public final String a() {
        String str = this.c;
        String str2 = this.d;
        return str2 == null ? str : String.format(str2, str);
    }

    public final String b() {
        String strA = a();
        String str = this.e;
        return TextUtils.isEmpty(str) ? strA : TextUtils.isEmpty(strA) ? str : C0175b.k(strA, " ", str);
    }
}
