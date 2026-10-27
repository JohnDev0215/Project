package org.chromium.chrome.browser.language.settings;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.i;
import defpackage.by1;
import defpackage.f51;
import defpackage.ii2;
import defpackage.k81;
import defpackage.pj2;
import defpackage.pp0;
import defpackage.qy;
import defpackage.ya1;
import defpackage.za3;
import defpackage.zd;
import java.util.TreeSet;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.language.settings.LanguageItemListFragment;
import org.chromium.chrome.browser.language.settings.b;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes.dex */
public abstract class LanguageItemListFragment extends Fragment implements pp0 {
    public static final /* synthetic */ int c0 = 0;
    public SettingsLauncher Z;
    public a a0;
    public b b0;

    public class a extends org.chromium.chrome.browser.language.settings.b {
        public a(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override // org.chromium.chrome.browser.language.settings.b, androidx.recyclerview.widget.RecyclerView.e
        public final void t(RecyclerView.a0 a0Var, int i) {
            super.t(a0Var, i);
            final f51 f51Var = (f51) this.j.get(i);
            ya1 ya1Var = new ya1();
            ya1Var.t(zd.c(R$string.remove, 0, 0));
            ((b.c) a0Var).u(new qy(this, ya1Var, new k81.a() { // from class: g51
                @Override // k81.a
                public final void a(PropertyModel propertyModel) {
                    LanguageItemListFragment.a aVar = this.e;
                    aVar.getClass();
                    if (propertyModel.h(o81.a) == R$string.remove) {
                        String str = f51Var.a;
                        LanguageItemListFragment languageItemListFragment = LanguageItemListFragment.this;
                        languageItemListFragment.L0(str);
                        aVar.H(languageItemListFragment.b0.b());
                        languageItemListFragment.P0();
                    }
                }
            }, 1));
        }
    }

    public interface b {
        String a();

        TreeSet b();
    }

    public abstract String H0(Context context);

    public abstract int I0();

    public abstract b J0();

    public abstract void K0(String str);

    public abstract void L0(String str);

    public abstract void M0();

    public abstract void N0();

    public abstract void O0();

    public abstract void P0();

    @Override // androidx.fragment.app.Fragment
    public final void Z(int i, int i2, Intent intent) {
        if (i == 1 && i2 == -1) {
            K0(intent.getStringExtra("SelectLanguageFragment.SelectedLanguage"));
            a aVar = this.a0;
            aVar.H(LanguageItemListFragment.this.b0.b());
            M0();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
        this.b0 = J0();
        D().setTitle(H0(K()));
        O0();
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R$layout.language_list_with_add_button, viewGroup, false);
        FragmentActivity fragmentActivityD = D();
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.language_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivityD);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.g(new i(fragmentActivityD, linearLayoutManager.p));
        a aVar = new a(fragmentActivityD);
        this.a0 = aVar;
        recyclerView.setAdapter(aVar);
        a aVar2 = this.a0;
        aVar2.H(LanguageItemListFragment.this.b0.b());
        ScrollView scrollView = (ScrollView) viewInflate.findViewById(R$id.scroll_view);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new pj2(scrollView, viewInflate.findViewById(R$id.shadow)));
        TextView textView = (TextView) viewInflate.findViewById(R$id.add_language);
        za3 za3VarA = za3.a(K(), R$drawable.plus);
        za3VarA.setTint(ii2.b(K()));
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(za3VarA, (Drawable) null, (Drawable) null, (Drawable) null);
        textView.setOnClickListener(new by1(12, this));
        return viewInflate;
    }

    @Override // defpackage.pp0
    public final void i(SettingsLauncher settingsLauncher) {
        this.Z = settingsLauncher;
    }
}
