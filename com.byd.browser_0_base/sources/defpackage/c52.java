package defpackage;

import J.N;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.CheckableImageView;

/* JADX INFO: compiled from: PrivacySandboxDialogConsentEEAV4.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c52 extends Dialog implements View.OnClickListener, DialogInterface.OnShowListener {
    public final CheckableImageView e;
    public final LinearLayout f;
    public final LinearLayout g;
    public final LinearLayout h;
    public final LinearLayout i;
    public final ButtonCompat j;
    public final LinearLayout k;
    public final ScrollView l;
    public final boolean m;
    public final SettingsLauncher n;

    public c52(AppCompatActivity appCompatActivity, n3 n3Var) {
        super(appCompatActivity, R$style.ThemeOverlay_BrowserUI_Fullscreen);
        this.n = n3Var;
        this.m = false;
        View viewInflate = LayoutInflater.from(appCompatActivity).inflate(R$layout.privacy_sandbox_consent_eea_v4, (ViewGroup) null);
        setContentView(viewInflate);
        ((ButtonCompat) viewInflate.findViewById(R$id.ack_button)).setOnClickListener(this);
        ((ButtonCompat) viewInflate.findViewById(R$id.no_button)).setOnClickListener(this);
        ButtonCompat buttonCompat = (ButtonCompat) viewInflate.findViewById(R$id.more_button);
        this.j = buttonCompat;
        this.k = (LinearLayout) viewInflate.findViewById(R$id.action_buttons);
        ScrollView scrollView = (ScrollView) viewInflate.findViewById(R$id.privacy_sandbox_dialog_scroll_view);
        this.l = scrollView;
        this.h = (LinearLayout) viewInflate.findViewById(R$id.progress_bar_container);
        this.i = (LinearLayout) viewInflate.findViewById(R$id.privacy_sandbox_consent_eea_view);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.dropdown_element);
        this.g = linearLayout;
        linearLayout.setOnClickListener(this);
        this.f = (LinearLayout) viewInflate.findViewById(R$id.dropdown_container);
        CheckableImageView checkableImageView = (CheckableImageView) viewInflate.findViewById(R$id.expand_arrow);
        this.e = checkableImageView;
        checkableImageView.setImageDrawable(h52.a(appCompatActivity));
        checkableImageView.setChecked(b());
        buttonCompat.setOnClickListener(this);
        setOnShowListener(this);
        setCancelable(false);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new b52(this, 0));
    }

    public final void a() {
        this.h.setVisibility(0);
        this.i.setVisibility(8);
        Handler handler = new Handler();
        y42 y42Var = new y42(this, 1);
        boolean z = this.m;
        handler.postDelayed(y42Var, z ? 0L : 1800L);
        handler.postDelayed(new z42(this, 1), z ? 0L : 1500L);
    }

    public final boolean b() {
        LinearLayout linearLayout = this.f;
        return linearLayout != null && linearLayout.getVisibility() == 0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        if (id == R$id.ack_button) {
            N.Mq9orIwX(6);
            a();
            return;
        }
        if (id == R$id.no_button) {
            N.Mq9orIwX(7);
            a();
            return;
        }
        int i = R$id.more_button;
        ScrollView scrollView = this.l;
        if (id == i) {
            N.Mq9orIwX(14);
            if (scrollView.canScrollVertically(130)) {
                scrollView.post(new qm0(23, this));
                return;
            }
            this.j.setVisibility(8);
            this.k.setVisibility(0);
            scrollView.post(new y42(this, 0));
            return;
        }
        if (id == R$id.dropdown_element) {
            boolean zB = b();
            LinearLayout linearLayout = this.f;
            if (zB) {
                N.Mq9orIwX(9);
                linearLayout.setVisibility(8);
                linearLayout.removeAllViews();
                scrollView.post(new z42(this, 0));
            } else {
                linearLayout.setVisibility(0);
                N.Mq9orIwX(8);
                LayoutInflater.from(getContext()).inflate(R$layout.privacy_sandbox_consent_eea_dropdown_v4, linearLayout);
                h52.c(getContext(), linearLayout, R$id.privacy_sandbox_m1_consent_learn_more_bullet_one, R$string.privacy_sandbox_m1_consent_learn_more_bullet_1);
                h52.c(getContext(), linearLayout, R$id.privacy_sandbox_m1_consent_learn_more_bullet_two, R$string.privacy_sandbox_m1_consent_learn_more_bullet_2);
                h52.c(getContext(), linearLayout, R$id.privacy_sandbox_m1_consent_learn_more_bullet_three, R$string.privacy_sandbox_m1_consent_learn_more_bullet_3);
                scrollView.post(new a52(this, 0));
            }
            this.e.setChecked(b());
            h52.d(R$string.privacy_sandbox_m1_consent_learn_more_expand_label, getContext(), view, b());
            view.announceForAccessibility(getContext().getResources().getString(b() ? R$string.accessibility_expanded_group : R$string.accessibility_collapsed_group));
        }
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        boolean zCanScrollVertically = this.l.canScrollVertically(130);
        LinearLayout linearLayout = this.k;
        ButtonCompat buttonCompat = this.j;
        if (zCanScrollVertically) {
            buttonCompat.setVisibility(0);
            linearLayout.setVisibility(8);
        } else {
            buttonCompat.setVisibility(8);
            linearLayout.setVisibility(0);
        }
    }

    @Override // android.app.Dialog
    public final void show() {
        N.Mq9orIwX(5);
        super.show();
    }
}
