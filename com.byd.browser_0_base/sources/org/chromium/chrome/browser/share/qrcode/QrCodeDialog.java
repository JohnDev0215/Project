package org.chromium.chrome.browser.share.qrcode;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import defpackage.eu1;
import defpackage.g82;
import defpackage.v03;
import defpackage.vt;
import defpackage.w72;
import defpackage.x72;
import defpackage.z72;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$style;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.widget.ChromeImageButton;

/* JADX INFO: loaded from: classes2.dex */
public class QrCodeDialog extends DialogFragment {
    public static final /* synthetic */ int h = 0;
    public WindowAndroid e;
    public ArrayList<w72> f;
    public v03 g;

    public final void a(WindowAndroid windowAndroid) {
        this.e = windowAndroid;
        v03 v03Var = this.g;
        if (v03Var != null) {
            Iterator<w72> it = v03Var.h.iterator();
            while (it.hasNext()) {
                it.next().a(windowAndroid);
            }
        }
    }

    @Override // android.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.e == null || this.g == null) {
            onDestroyView();
        }
    }

    @Override // android.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        Activity activity = getActivity();
        g82 g82Var = new g82(activity, new eu1(16, this), getArguments().getString("url_key"), this.e);
        z72 z72Var = new z72(activity, this.e);
        ArrayList<w72> arrayList = new ArrayList<>();
        this.f = arrayList;
        arrayList.add(g82Var);
        this.f.add(z72Var);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R$style.ThemeOverlay_BrowserUI_Fullscreen);
        View viewInflate = getActivity().getLayoutInflater().inflate(R$layout.qrcode_dialog, (ViewGroup) null);
        ((ChromeImageButton) viewInflate.findViewById(R$id.close_button)).setOnClickListener(new vt(21, this));
        ArrayList arrayList2 = new ArrayList();
        Iterator<w72> it = this.f.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().getView());
        }
        x72 x72Var = new x72(arrayList2);
        TabLayout tabLayout = (TabLayout) viewInflate.findViewById(R$id.tab_layout);
        ViewPager viewPager = (ViewPager) viewInflate.findViewById(R$id.qrcode_view_pager);
        viewPager.setAdapter(x72Var);
        v03 v03Var = new v03(tabLayout, this.f);
        this.g = v03Var;
        viewPager.addOnPageChangeListener(v03Var);
        tabLayout.addOnTabSelectedListener((TabLayout.d) new TabLayout.i(viewPager));
        builder.a.r = viewInflate;
        return builder.a();
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        Iterator<w72> it = this.f.iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
        this.f.clear();
        this.e = null;
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
        Iterator<w72> it = this.g.h.iterator();
        while (it.hasNext()) {
            it.next().onPause();
        }
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        v03 v03Var = this.g;
        v03Var.h.get(v03Var.i).onResume();
    }
}
