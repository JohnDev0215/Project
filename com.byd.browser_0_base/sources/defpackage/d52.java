package defpackage;

import J.N;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.CheckableImageView;

/* JADX INFO: compiled from: PrivacySandboxDialogNoticeEEAV4.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d52 extends Dialog implements View.OnClickListener, DialogInterface.OnShowListener {
    public final SettingsLauncher e;
    public final ButtonCompat f;
    public final LinearLayout g;
    public final ScrollView h;
    public final LinearLayout i;
    public final CheckableImageView j;
    public final LinearLayout k;

    public d52(Context context, SettingsLauncher settingsLauncher) {
        super(context, R$style.ThemeOverlay_BrowserUI_Fullscreen);
        this.e = settingsLauncher;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.privacy_sandbox_notice_eea_v4, (ViewGroup) null);
        setContentView(viewInflate);
        ((ButtonCompat) viewInflate.findViewById(R$id.ack_button)).setOnClickListener(this);
        ((ButtonCompat) viewInflate.findViewById(R$id.settings_button)).setOnClickListener(this);
        ButtonCompat buttonCompat = (ButtonCompat) viewInflate.findViewById(R$id.more_button);
        this.f = buttonCompat;
        this.g = (LinearLayout) viewInflate.findViewById(R$id.action_buttons);
        ScrollView scrollView = (ScrollView) viewInflate.findViewById(R$id.privacy_sandbox_dialog_scroll_view);
        this.h = scrollView;
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.dropdown_element);
        this.i = linearLayout;
        linearLayout.setOnClickListener(this);
        this.k = (LinearLayout) viewInflate.findViewById(R$id.dropdown_container);
        CheckableImageView checkableImageView = (CheckableImageView) viewInflate.findViewById(R$id.expand_arrow);
        this.j = checkableImageView;
        checkableImageView.setImageDrawable(h52.a(context));
        checkableImageView.setChecked(a());
        h52.b(getContext(), viewInflate, R$id.privacy_sandbox_m1_notice_eea_bullet_one, R$string.privacy_sandbox_m1_notice_eea_bullet_1);
        h52.b(getContext(), viewInflate, R$id.privacy_sandbox_m1_notice_eea_bullet_two, R$string.privacy_sandbox_m1_notice_eea_bullet_2);
        buttonCompat.setOnClickListener(this);
        setOnShowListener(this);
        setCancelable(false);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new b52(this, 1));
    }

    public final boolean a() {
        LinearLayout linearLayout = this.k;
        return linearLayout != null && linearLayout.getVisibility() == 0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        if (id == R$id.ack_button) {
            N.Mq9orIwX(2);
            dismiss();
            return;
        }
        if (id == R$id.settings_button) {
            N.Mq9orIwX(1);
            dismiss();
            PrivacySandboxSettingsBaseFragment.M0(getContext(), this.e, 2);
            return;
        }
        int i = R$id.more_button;
        ScrollView scrollView = this.h;
        if (id == i) {
            N.Mq9orIwX(15);
            if (scrollView.canScrollVertically(130)) {
                scrollView.post(new zx1(4, this));
                return;
            }
            this.f.setVisibility(8);
            this.g.setVisibility(0);
            scrollView.post(new eu1(7, this));
            return;
        }
        if (id == R$id.dropdown_element) {
            boolean zA = a();
            LinearLayout linearLayout = this.k;
            if (zA) {
                N.Mq9orIwX(13);
                linearLayout.setVisibility(8);
                linearLayout.removeAllViews();
            } else {
                linearLayout.setVisibility(0);
                N.Mq9orIwX(12);
                LayoutInflater.from(getContext()).inflate(R$layout.privacy_sandbox_notice_eea_dropdown_v4, linearLayout);
                h52.c(getContext(), linearLayout, R$id.privacy_sandbox_m1_notice_eea_learn_more_bullet_one, R$string.privacy_sandbox_m1_notice_eea_learn_more_bullet_1);
                h52.c(getContext(), linearLayout, R$id.privacy_sandbox_m1_notice_eea_learn_more_bullet_two, R$string.privacy_sandbox_m1_notice_eea_learn_more_bullet_2);
                h52.c(getContext(), linearLayout, R$id.privacy_sandbox_m1_notice_eea_learn_more_bullet_three, R$string.privacy_sandbox_m1_notice_eea_learn_more_bullet_3);
                scrollView.post(new ql0(29, this));
            }
            this.j.setChecked(a());
            h52.d(R$string.privacy_sandbox_m1_notice_eea_learn_more_expand_label, getContext(), view, a());
            view.announceForAccessibility(getContext().getResources().getString(a() ? R$string.accessibility_expanded_group : R$string.accessibility_collapsed_group));
        }
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        ScrollView scrollView = this.h;
        boolean zCanScrollVertically = scrollView.canScrollVertically(130);
        LinearLayout linearLayout = this.g;
        ButtonCompat buttonCompat = this.f;
        if (zCanScrollVertically) {
            buttonCompat.setVisibility(0);
            linearLayout.setVisibility(8);
        } else {
            buttonCompat.setVisibility(8);
            linearLayout.setVisibility(0);
        }
        scrollView.setVisibility(0);
    }

    @Override // android.app.Dialog
    public final void show() {
        N.Mq9orIwX(0);
        super.show();
    }
}
