package com.android.settingslib.drawer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f1355a;

    /* JADX INFO: compiled from: caradapter-bug */
    protected static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1356a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1357b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @DrawableRes
        private int f1358c;
        private int d;
        private int e;
        private Boolean f;

        @StringRes
        private int g;
        private String h;

        @StringRes
        private int i;
        private String j;

        /* JADX INFO: Access modifiers changed from: private */
        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putString("com.android.settings.category", this.f1356a);
            int i = this.f1357b;
            if (i != 0) {
                bundle.putInt("com.android.settings.order", i);
            }
            int i2 = this.f1358c;
            if (i2 != 0) {
                bundle.putInt("com.android.settings.icon", i2);
            }
            int i3 = this.d;
            if (i3 != 0) {
                bundle.putInt("com.android.settings.bg.hint", i3);
            }
            int i4 = this.e;
            if (i4 != 0) {
                bundle.putInt("com.android.settings.bg.argb", i4);
            }
            Boolean bool = this.f;
            if (bool != null) {
                bundle.putBoolean("com.android.settings.icon_tintable", bool.booleanValue());
            }
            int i5 = this.g;
            if (i5 != 0) {
                bundle.putInt("com.android.settings.title", i5);
            } else {
                String str = this.h;
                if (str != null) {
                    bundle.putString("com.android.settings.title", str);
                }
            }
            int i6 = this.i;
            if (i6 != 0) {
                bundle.putInt("com.android.settings.summary", i6);
            } else {
                String str2 = this.j;
                if (str2 != null) {
                    bundle.putString("com.android.settings.summary", str2);
                }
            }
            return bundle;
        }
    }

    private void f(Context context, String str) {
        context.getContentResolver().notifyChange(g.b(this.f1355a, str, d()), null);
    }

    Bundle a() {
        a aVarC = c();
        if (aVarC == null) {
            throw new NullPointerException("Should not return null in getMetaData()");
        }
        Bundle bundleB = aVarC.b();
        String string = new Uri.Builder().scheme("content").authority(this.f1355a).build().toString();
        bundleB.putString("com.android.settings.keyhint", d());
        bundleB.putString("com.android.settings.switch_uri", string);
        if (this instanceof e) {
            bundleB.putString("com.android.settings.icon_uri", string);
        }
        if (this instanceof d) {
            bundleB.putString("com.android.settings.title_uri", string);
        }
        if (this instanceof c) {
            bundleB.putString("com.android.settings.summary_uri", string);
        }
        return bundleB;
    }

    protected abstract String b(boolean z);

    protected abstract a c();

    public abstract String d();

    protected abstract boolean e();

    public void g(Context context) {
        if (this instanceof c) {
            f(context, "getDynamicSummary");
        }
    }

    protected abstract boolean h(boolean z);

    void i(String str) {
        this.f1355a = str;
    }
}
