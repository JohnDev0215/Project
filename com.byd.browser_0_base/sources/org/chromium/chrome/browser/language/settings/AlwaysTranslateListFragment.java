package org.chromium.chrome.browser.language.settings;

import J.N;
import android.content.Context;
import defpackage.f51;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.TreeSet;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.language.settings.LanguageItemListFragment;

/* JADX INFO: loaded from: classes.dex */
public class AlwaysTranslateListFragment extends LanguageItemListFragment {

    public static class a implements LanguageItemListFragment.b {
        @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment.b
        public final String a() {
            return AlwaysTranslateListFragment.class.getName();
        }

        @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment.b
        public final TreeSet b() {
            c cVarB = c.b();
            cVarB.getClass();
            ArrayList<String> arrayList = new ArrayList(Arrays.asList(N.MAJqSbXG()));
            TreeSet treeSet = new TreeSet(f51.f);
            for (String str : arrayList) {
                LinkedHashMap linkedHashMap = cVarB.a;
                if (linkedHashMap.containsKey(str)) {
                    treeSet.add((f51) linkedHashMap.get(str));
                }
            }
            return treeSet;
        }
    }

    @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment
    public final String H0(Context context) {
        return context.getResources().getString(R$string.languages_settings_automatic_title);
    }

    @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment
    public final int I0() {
        return 4;
    }

    @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment
    public final LanguageItemListFragment.b J0() {
        return new a();
    }

    @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment
    public final void K0(String str) {
        N.M2Oi3mFV(str, true);
    }

    @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment
    public final void L0(String str) {
        N.M2Oi3mFV(str, false);
    }

    @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment
    public final void M0() {
        c.g(14);
    }

    @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment
    public final void N0() {
        c.h(10);
    }

    @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment
    public final void O0() {
        c.h(9);
    }

    @Override // org.chromium.chrome.browser.language.settings.LanguageItemListFragment
    public final void P0() {
        c.g(13);
    }
}
