package org.chromium.chrome.browser.toolbar.top;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.bz;
import defpackage.gq2;
import defpackage.n3;
import defpackage.nw2;
import defpackage.oj2;
import defpackage.p11;
import defpackage.pd;
import defpackage.pm1;
import defpackage.xk1;
import defpackage.z7;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: NavigationPopup.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b implements AdapterView.OnItemClickListener {
    public final Profile e;
    public final Context f;
    public final ListPopupWindow g;
    public final NavigationController h;
    public final xk1 i;
    public final c j;
    public final int k;
    public final int l;
    public final org.chromium.chrome.browser.toolbar.top.a m;
    public final nw2<Tab> n;
    public final InterfaceC0116b o;
    public FaviconHelper.a p;
    public FaviconHelper q;
    public boolean r;

    /* JADX INFO: compiled from: NavigationPopup.java */
    public static class a {
        public View a;
        public ImageView b;
        public TextView c;
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.toolbar.top.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NavigationPopup.java */
    public interface InterfaceC0116b {
    }

    /* JADX INFO: compiled from: NavigationPopup.java */
    public class c extends BaseAdapter {
        public Integer e;

        public c() {
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            return b.this.i.b();
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i) {
            return b.this.i.a(i);
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return ((NavigationEntry) getItem(i)).a;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R$layout.navigation_popup_item, viewGroup, false);
                aVar = new a();
                aVar.a = view;
                aVar.b = (ImageView) view.findViewById(R$id.favicon_img);
                aVar.c = (TextView) view.findViewById(R$id.entry_title);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            NavigationEntry navigationEntry = (NavigationEntry) getItem(i);
            TextView textView = aVar.c;
            String strJ = navigationEntry.e;
            if (TextUtils.isEmpty(strJ)) {
                strJ = navigationEntry.d.j();
            }
            if (TextUtils.isEmpty(strJ)) {
                strJ = navigationEntry.b.j();
            }
            textView.setText(strJ);
            aVar.b.setImageBitmap(navigationEntry.f);
            b bVar = b.this;
            if (navigationEntry.a == -1) {
                aVar.b.setImageTintList(bz.b(bVar.f, R$color.default_icon_color_accent1_tint_list));
            } else {
                aVar.b.setImageTintList(null);
            }
            if (bVar.k == 0) {
                View view2 = aVar.a;
                if (this.e == null) {
                    this.e = Integer.valueOf(view2.getResources().getDimensionPixelSize(R$dimen.navigation_popup_top_padding));
                }
                aVar.a.setPadding(view2.getPaddingLeft(), i == 0 ? this.e.intValue() : 0, view2.getPaddingRight(), view2.getPaddingBottom());
            }
            return view;
        }
    }

    public b(Profile profile, Context context, NavigationController navigationController, int i, p11 p11Var, InterfaceC0116b interfaceC0116b) {
        this.e = profile;
        this.f = context;
        Resources resources = context.getResources();
        this.h = navigationController;
        this.k = i;
        this.n = p11Var;
        this.o = interfaceC0116b;
        int i2 = 1;
        boolean z = i == 2;
        boolean z2 = i == 0;
        xk1 xk1VarM = navigationController.m(z);
        this.i = xk1VarM;
        if (!profile.h()) {
            xk1VarM.a.add(new NavigationEntry(-1, new GURL("chrome://history/"), GURL.emptyGURL(), GURL.emptyGURL(), resources.getString(R$string.show_full_history), null));
        }
        c cVar = new c();
        this.j = cVar;
        ListPopupWindow listPopupWindow = new ListPopupWindow(context, null, 0, R$style.NavigationPopupDialog);
        this.g = listPopupWindow;
        listPopupWindow.setOnDismissListener(new pm1(i2, this));
        listPopupWindow.setBackgroundDrawable(AppCompatResources.a(context, z2 ? R$drawable.menu_bg_bottom_tinted : R$drawable.menu_bg_tinted));
        listPopupWindow.setModal(true);
        listPopupWindow.setInputMethodMode(2);
        listPopupWindow.setHeight(-2);
        listPopupWindow.setOnItemClickListener(this);
        listPopupWindow.setAdapter(cVar);
        listPopupWindow.setWidth(resources.getDimensionPixelSize(z2 ? R$dimen.navigation_popup_width : R$dimen.menu_width));
        if (z2) {
            listPopupWindow.setVerticalOffset(0);
            this.m = new org.chromium.chrome.browser.toolbar.top.a(this);
        } else {
            this.m = null;
        }
        this.l = resources.getDimensionPixelSize(R$dimen.default_favicon_size);
    }

    public final String a(String str) {
        return gq2.i(new StringBuilder(), this.k == 2 ? "ForwardMenu_" : "BackMenu_", str);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        NavigationEntry navigationEntry = (NavigationEntry) adapterView.getItemAtPosition(i);
        if (navigationEntry.a == -1) {
            n3.X0(a("ShowFullHistory"));
            Tab tab = this.n.get();
            Activity activity = (Activity) tab.D().k().get();
            boolean zIsIncognito = tab.isIncognito();
            ((pd) this.o).getClass();
            z7.j0(activity, tab, zIsIncognito);
        } else {
            n3.X0(a("HistoryClick" + (i + 1)));
            NavigationController navigationController = this.h;
            int i2 = navigationEntry.a;
            oj2.O("Navigation.BackForward.NavigatingToEntryMarkedToBeSkipped", navigationController.q(i2));
            navigationController.w(i2);
        }
        this.g.dismiss();
    }
}
