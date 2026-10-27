package defpackage;

import android.content.Intent;
import android.os.Bundle;
import android.os.LocaleList;
import android.text.TextUtils;
import android.util.SparseArray;
import defpackage.j30;
import java.util.ArrayList;

/* JADX INFO: compiled from: CustomTabsIntent.java */
/* JADX INFO: loaded from: classes.dex */
public final class g50 {
    public final Intent a;

    /* JADX INFO: compiled from: CustomTabsIntent.java */
    public static final class a {
        public ArrayList<Bundle> c;
        public final Intent a = new Intent("android.intent.action.VIEW");
        public final j30.a b = new j30.a();
        public int d = 0;
        public final boolean e = true;

        public final g50 a() {
            Intent intent = this.a;
            if (!intent.hasExtra("android.support.customtabs.extra.SESSION")) {
                Bundle bundle = new Bundle();
                bundle.putBinder("android.support.customtabs.extra.SESSION", null);
                intent.putExtras(bundle);
            }
            ArrayList<Bundle> arrayList = this.c;
            if (arrayList != null) {
                intent.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", arrayList);
            }
            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.e);
            Integer num = this.b.a;
            Bundle bundle2 = new Bundle();
            if (num != null) {
                bundle2.putInt("android.support.customtabs.extra.TOOLBAR_COLOR", num.intValue());
            }
            intent.putExtras(bundle2);
            intent.putExtra("androidx.browser.customtabs.extra.SHARE_STATE", this.d);
            LocaleList adjustedDefault = LocaleList.getAdjustedDefault();
            String languageTag = adjustedDefault.size() > 0 ? adjustedDefault.get(0).toLanguageTag() : null;
            if (!TextUtils.isEmpty(languageTag)) {
                Bundle bundleExtra = intent.hasExtra("com.android.browser.headers") ? intent.getBundleExtra("com.android.browser.headers") : new Bundle();
                if (!bundleExtra.containsKey("Accept-Language")) {
                    bundleExtra.putString("Accept-Language", languageTag);
                    intent.putExtra("com.android.browser.headers", bundleExtra);
                }
            }
            return new g50(intent);
        }

        public final void b(int i) {
            if (i < 0 || i > 2) {
                throw new IllegalArgumentException("Invalid value for the colorScheme argument");
            }
            this.a.putExtra("androidx.browser.customtabs.extra.COLOR_SCHEME", i);
        }

        public final void c() {
            this.d = 1;
            this.a.putExtra("android.support.customtabs.extra.SHARE_MENU_ITEM", true);
        }

        public final void d() {
            this.a.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", 1);
        }
    }

    public g50(Intent intent) {
        this.a = intent;
    }

    public static j30 a(Intent intent, int i) {
        Bundle bundle;
        if (i < 0 || i > 2 || i == 0) {
            throw new IllegalArgumentException(gq2.e("Invalid colorScheme: ", i));
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return j30.a(null);
        }
        j30 j30VarA = j30.a(extras);
        SparseArray sparseParcelableArray = extras.getSparseParcelableArray("androidx.browser.customtabs.extra.COLOR_SCHEME_PARAMS");
        if (sparseParcelableArray == null || (bundle = (Bundle) sparseParcelableArray.get(i)) == null) {
            return j30VarA;
        }
        j30 j30VarA2 = j30.a(bundle);
        Integer num = j30VarA2.a;
        if (num == null) {
            num = j30VarA.a;
        }
        Integer num2 = j30VarA2.b;
        if (num2 == null) {
            num2 = j30VarA.b;
        }
        Integer num3 = j30VarA2.c;
        if (num3 == null) {
            num3 = j30VarA.c;
        }
        Integer num4 = j30VarA2.d;
        if (num4 == null) {
            num4 = j30VarA.d;
        }
        return new j30(num, num2, num3, num4);
    }
}
