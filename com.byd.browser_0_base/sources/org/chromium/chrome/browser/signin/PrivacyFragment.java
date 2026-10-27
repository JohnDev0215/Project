package org.chromium.chrome.browser.signin;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.fragment.app.Fragment;
import defpackage.by1;
import defpackage.f52;
import defpackage.hn0;
import defpackage.l91;
import defpackage.n3;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.signin.PrivacyFragment;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacyFragment extends Fragment implements hn0 {
    public static final /* synthetic */ int d0 = 0;
    public ScrollView Z;
    public View a0;
    public f52 b0;
    public l91 c0;

    @Override // androidx.fragment.app.Fragment
    public final void c0(Bundle bundle) {
        super.c0(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public final View e0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(R$layout.privacy_fragment, viewGroup, false);
        viewInflate.setLayoutDirection(3);
        by1 by1Var = new by1(20, this);
        final View viewFindViewById = viewInflate.findViewById(R$id.bt_privacy_agree);
        this.a0 = viewInflate.findViewById(R$id.bt_privacy_disagree);
        viewFindViewById.setOnClickListener(by1Var);
        this.a0.setOnClickListener(by1Var);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.ll_agree_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        this.Z = (ScrollView) viewInflate.findViewById(R$id.scrollView);
        N().getConfiguration();
        ScrollView scrollView = this.Z;
        if (this.b0 == null) {
            this.b0 = new f52(7, this);
        }
        scrollView.post(this.b0);
        this.Z.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: o42
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i, int i2, int i3, int i4) {
                PrivacyFragment privacyFragment = this.a;
                if (Math.abs((privacyFragment.Z.getHeight() + i2) - privacyFragment.Z.getChildAt(0).getHeight()) < 10) {
                    View view2 = viewFindViewById;
                    view2.setEnabled(true);
                    privacyFragment.a0.setEnabled(true);
                    view2.setVisibility(0);
                    privacyFragment.a0.setVisibility(0);
                }
            }
        });
        l91 l91Var = new l91(this, 26, viewFindViewById);
        this.c0 = l91Var;
        this.a0.post(l91Var);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void f0() {
        f52 f52Var = this.b0;
        if (f52Var != null) {
            this.Z.removeCallbacks(f52Var);
        }
        l91 l91Var = this.c0;
        if (l91Var != null) {
            this.a0.removeCallbacks(l91Var);
        }
        this.b0 = null;
        this.c0 = null;
        this.H = true;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        this.H = true;
        f52 f52Var = this.b0;
        if (f52Var != null) {
            this.Z.removeCallbacks(f52Var);
        }
        ScrollView scrollView = this.Z;
        if (this.b0 == null) {
            this.b0 = new f52(7, this);
        }
        scrollView.post(this.b0);
    }

    @Override // defpackage.hn0
    public final void h() {
    }

    @Override // androidx.fragment.app.Fragment
    public final void q0(View view, Bundle bundle) {
    }
}
