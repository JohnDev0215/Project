package org.chromium.chrome.browser.language.settings;

import J.N;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i;
import defpackage.bq;
import defpackage.f51;
import defpackage.gx;
import defpackage.ke0;
import defpackage.pj2;
import defpackage.pq2;
import defpackage.sk;
import defpackage.u6;
import defpackage.wd2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$menu;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.language.settings.b;
import org.chromium.chrome.browser.translate.TranslateBridge;

/* JADX INFO: loaded from: classes.dex */
public class SelectLanguageFragment extends Fragment {
    public static final /* synthetic */ int f0 = 0;
    public SearchView Z;
    public String a0;
    public RecyclerView b0;
    public b c0;
    public ArrayList d0;
    public pq2 e0;

    public class b extends org.chromium.chrome.browser.language.settings.b {
        public b(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override // org.chromium.chrome.browser.language.settings.b, androidx.recyclerview.widget.RecyclerView.e
        public final void t(RecyclerView.a0 a0Var, int i) {
            super.t(a0Var, i);
            ((b.c) a0Var).e.setOnClickListener(new wd2(SelectLanguageFragment.this.e0, 4, (f51) this.j.get(i)));
        }
    }

    public interface c {
    }

    @Override // androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        sk skVar = bq.a;
        if (N.M09VlOh_("DetailedLanguageSettings")) {
            D().setTitle(R$string.languages_select);
        } else {
            D().setTitle(R$string.add_language);
        }
        D0();
    }

    @Override // androidx.fragment.app.Fragment
    public final void d0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R$menu.languages_action_bar_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R$id.search).getActionView();
        this.Z = searchView;
        searchView.setImeOptions(33554432);
        this.Z.setOnCloseListener(new ke0(15, this));
        this.Z.setOnQueryTextListener(new a());
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ArrayList arrayList;
        View viewInflate = layoutInflater.inflate(R$layout.add_languages_main, viewGroup, false);
        this.a0 = "";
        FragmentActivity fragmentActivityD = D();
        this.b0 = (RecyclerView) viewInflate.findViewById(R$id.language_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivityD);
        this.b0.setLayoutManager(linearLayoutManager);
        this.b0.g(new i(fragmentActivityD, linearLayoutManager.p));
        int intExtra = D().getIntent().getIntExtra("SelectLanguageFragment.PotentialLanguages", 0);
        org.chromium.chrome.browser.language.settings.c cVarB = org.chromium.chrome.browser.language.settings.c.b();
        LinkedHashMap linkedHashMap = cVarB.a;
        if (intExtra != 0) {
            arrayList = null;
            if (intExtra == 1) {
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                f51 f51VarC = cVarB.c(u6.a());
                if (!TextUtils.equals(f51VarC.a, null)) {
                    linkedHashSet.add(f51.a());
                }
                gx gxVar = new gx(15, f51VarC);
                org.chromium.chrome.browser.language.settings.c.a(linkedHashSet, cVarB.e(), gxVar);
                org.chromium.chrome.browser.language.settings.c.a(linkedHashSet, linkedHashMap.values(), gxVar);
                arrayList = new ArrayList(linkedHashSet);
            } else if (intExtra == 2) {
                arrayList = cVarB.d(Arrays.asList(LocaleUtils.a(N.MMKf4EpW())));
            } else if (intExtra == 3) {
                arrayList = cVarB.d(new ArrayList(Arrays.asList(N.MGg_6_1K())));
            } else if (intExtra == 4) {
                arrayList = cVarB.d(new ArrayList(Arrays.asList(N.MAJqSbXG())));
            }
        } else {
            HashSet hashSet = new HashSet(TranslateBridge.a());
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            for (f51 f51Var : linkedHashMap.values()) {
                if (!hashSet.contains(f51Var.a)) {
                    linkedHashSet2.add(f51Var);
                }
            }
            arrayList = new ArrayList(linkedHashSet2);
        }
        this.d0 = arrayList;
        this.e0 = new pq2(19, fragmentActivityD);
        b bVar = new b(fragmentActivityD);
        this.c0 = bVar;
        this.b0.setAdapter(bVar);
        this.c0.H(this.d0);
        this.b0.getViewTreeObserver().addOnScrollChangedListener(new pj2(this.b0, viewInflate.findViewById(R$id.shadow)));
        return viewInflate;
    }

    public class a implements SearchView.OnQueryTextListener {
        public a() {
        }

        @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
        public final void onQueryTextChange(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            SelectLanguageFragment selectLanguageFragment = SelectLanguageFragment.this;
            if (TextUtils.equals(str, selectLanguageFragment.a0)) {
                return;
            }
            selectLanguageFragment.a0 = str;
            b bVar = selectLanguageFragment.c0;
            bVar.getClass();
            boolean zIsEmpty = TextUtils.isEmpty(str);
            SelectLanguageFragment selectLanguageFragment2 = SelectLanguageFragment.this;
            if (zIsEmpty) {
                bVar.H(selectLanguageFragment2.d0);
                return;
            }
            Locale locale = Locale.getDefault();
            String lowerCase = str.trim().toLowerCase(locale);
            ArrayList arrayList = new ArrayList();
            for (f51 f51Var : selectLanguageFragment2.d0) {
                if (f51Var.b.toLowerCase(locale).contains(lowerCase)) {
                    arrayList.add(f51Var);
                }
            }
            bVar.H(arrayList);
        }

        @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
        public final void a() {
        }
    }
}
