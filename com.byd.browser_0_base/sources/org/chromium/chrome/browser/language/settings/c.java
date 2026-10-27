package org.chromium.chrome.browser.language.settings;

import J.N;
import android.text.TextUtils;
import defpackage.f51;
import defpackage.k32;
import defpackage.ke0;
import defpackage.oj2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.browser.language.settings.ContentLanguagesPreference;
import org.chromium.chrome.browser.translate.TranslateBridge;

/* JADX INFO: compiled from: LanguagesManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static c c;
    public final LinkedHashMap a = new LinkedHashMap();
    public a b;

    /* JADX INFO: compiled from: LanguagesManager.java */
    public interface a {
    }

    public c() {
        ArrayList<f51> arrayList = new ArrayList();
        N.MlwSZWfW(arrayList);
        for (f51 f51Var : arrayList) {
            this.a.put(f51Var.a, f51Var);
        }
    }

    public static void a(LinkedHashSet linkedHashSet, Collection collection, k32 k32Var) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            f51 f51Var = (f51) it.next();
            if (k32Var.q(f51Var)) {
                linkedHashSet.add(f51Var);
            }
        }
    }

    public static c b() {
        if (c == null) {
            c = new c();
        }
        return c;
    }

    public static void g(int i) {
        oj2.U(i, 17, "LanguageSettings.Actions");
    }

    public static void h(int i) {
        oj2.U(i, 12, "LanguageSettings.PageImpression");
    }

    public final f51 c(String str) {
        if (TextUtils.equals(str, null)) {
            return f51.a();
        }
        LinkedHashMap linkedHashMap = this.a;
        f51 f51Var = (f51) linkedHashMap.get(str);
        return f51Var != null ? f51Var : (f51) linkedHashMap.get(LocaleUtils.c(str));
    }

    public final ArrayList d(List list) {
        HashSet hashSet = new HashSet(list);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ke0 ke0Var = new ke0(14, hashSet);
        a(linkedHashSet, e(), ke0Var);
        a(linkedHashSet, this.a.values(), ke0Var);
        return new ArrayList(linkedHashSet);
    }

    public final ArrayList e() {
        ArrayList<String> arrayListA = TranslateBridge.a();
        ArrayList arrayList = new ArrayList();
        for (String str : arrayListA) {
            LinkedHashMap linkedHashMap = this.a;
            if (linkedHashMap.containsKey(str)) {
                arrayList.add((f51) linkedHashMap.get(str));
            }
        }
        return arrayList;
    }

    public final void f(String str, int i) {
        if (i == 0) {
            return;
        }
        N.MLlzo6h2(str, i);
        g(8);
        a aVar = this.b;
        if (aVar != null) {
            ((ContentLanguagesPreference.a) aVar).I();
        }
    }
}
