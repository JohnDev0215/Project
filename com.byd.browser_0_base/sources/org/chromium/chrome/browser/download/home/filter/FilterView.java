package org.chromium.chrome.browser.download.home.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;

/* JADX INFO: loaded from: classes.dex */
class FilterView {
    public final ViewGroup a;
    public final TabLayout b;
    public final ViewGroup c;
    public Callback<Integer> d;

    public FilterView(Context context) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R$layout.download_home_tabs, (ViewGroup) null);
        this.a = viewGroup;
        TabLayout tabLayout = (TabLayout) viewGroup.findViewById(R$id.tabs);
        this.b = tabLayout;
        this.c = (ViewGroup) viewGroup.findViewById(R$id.content_container);
        tabLayout.setOnTabSelectedListener((TabLayout.d) new a());
    }

    public class a implements TabLayout.d {
        public a() {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void h(TabLayout.g gVar) {
            Callback<Integer> callback = FilterView.this.d;
            if (callback == null) {
                return;
            }
            callback.onResult(Integer.valueOf(gVar.e == 0 ? 0 : 1));
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void a(TabLayout.g gVar) {
        }

        @Override // com.google.android.material.tabs.TabLayout.c
        public final void l(TabLayout.g gVar) {
        }
    }
}
