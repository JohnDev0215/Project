package org.chromium.chrome.browser.autofill.settings;

import J.N;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import defpackage.eb;
import defpackage.n3;
import defpackage.q11;
import defpackage.xf0;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.settings.SettingsActivity;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: loaded from: classes.dex */
public class AutofillProfileBridge {
    public String a;

    public static class a {
        public final int a;
        public final String b;
        public final boolean c;
        public final boolean d;

        public a(String str, int i, boolean z, boolean z2) {
            this.a = i;
            this.b = str;
            this.c = z;
            this.d = z2;
        }
    }

    public static ArrayList b() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        N.M6KwIT3h(arrayList, arrayList2);
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList3.add(new xf0.a((String) arrayList.get(i), (CharSequence) arrayList2.get(i)));
        }
        Collator collator = Collator.getInstance(Locale.getDefault());
        collator.setStrength(0);
        Collections.sort(arrayList3, new eb(collator));
        return arrayList3;
    }

    public static void c(WebContents webContents, Class<? extends Fragment> cls) {
        Context context = (Context) webContents.V().k().get();
        String name = cls.getName();
        Intent intent = new Intent();
        intent.setClass(context, SettingsActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
            intent.addFlags(67108864);
        }
        intent.putExtra("show_fragment", name);
        ComponentName componentName = q11.a;
        try {
            context.startActivity(intent, null);
        } catch (ActivityNotFoundException unused) {
        }
    }

    @CalledByNative
    public static void intArrayToList(int[] iArr, List<Integer> list) {
        for (int i : iArr) {
            list.add(Integer.valueOf(i));
        }
    }

    @CalledByNative
    public static void showAutofillCreditCardSettings(WebContents webContents) {
        n3.X0("AutofillCreditCardsViewed");
        c(webContents, AutofillPaymentMethodsFragment.class);
    }

    @CalledByNative
    public static void showAutofillProfileSettings(WebContents webContents) {
        n3.X0("AutofillAddressesViewed");
        c(webContents, AutofillProfilesFragment.class);
    }

    @CalledByNative
    public static void stringArrayToList(String[] strArr, List<String> list) {
        for (String str : strArr) {
            list.add(str);
        }
    }

    public final ArrayList a(int i, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        this.a = N.MRFNh4wI(str, str2, i, arrayList, arrayList2, arrayList3, arrayList4);
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            int iIntValue = ((Integer) arrayList.get(i2)).intValue();
            String str3 = (String) arrayList2.get(i2);
            boolean z = true;
            boolean z2 = ((Integer) arrayList3.get(i2)).intValue() == 1;
            if (((Integer) arrayList4.get(i2)).intValue() != 1) {
                z = false;
            }
            arrayList5.add(new a(str3, iIntValue, z2, z));
        }
        return arrayList5;
    }
}
