package defpackage;

import J.N;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.appcompat.app.AppCompatActivity;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.privacy_sandbox.PrivacySandboxSettingsBaseFragment;
import org.chromium.components.browser_ui.settings.SettingsLauncher;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.CheckableImageView;

/* JADX INFO: compiled from: PrivacySandboxDialogNoticeROWV4.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g52 extends Dialog implements View.OnClickListener, DialogInterface.OnShowListener {
    public final SettingsLauncher e;
    public final View f;
    public final CheckableImageView g;
    public final LinearLayout h;
    public final LinearLayout i;
    public final ButtonCompat j;
    public final LinearLayout k;
    public final ScrollView l;

    public g52(AppCompatActivity appCompatActivity, n3 n3Var) {
        super(appCompatActivity, R$style.ThemeOverlay_BrowserUI_Fullscreen);
        this.e = n3Var;
        View viewInflate = LayoutInflater.from(appCompatActivity).inflate(R$layout.privacy_sandbox_notice_row_v4, (ViewGroup) null);
        this.f = viewInflate;
        setContentView(viewInflate);
        ((ButtonCompat) viewInflate.findViewById(R$id.ack_button)).setOnClickListener(this);
        ((ButtonCompat) viewInflate.findViewById(R$id.settings_button)).setOnClickListener(this);
        ButtonCompat buttonCompat = (ButtonCompat) viewInflate.findViewById(R$id.more_button);
        this.j = buttonCompat;
        this.k = (LinearLayout) viewInflate.findViewById(R$id.action_buttons);
        ScrollView scrollView = (ScrollView) viewInflate.findViewById(R$id.privacy_sandbox_dialog_scroll_view);
        this.l = scrollView;
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.dropdown_element);
        this.i = linearLayout;
        linearLayout.setOnClickListener(this);
        this.h = (LinearLayout) viewInflate.findViewById(R$id.dropdown_container);
        CheckableImageView checkableImageView = (CheckableImageView) viewInflate.findViewById(R$id.expand_arrow);
        this.g = checkableImageView;
        checkableImageView.setImageDrawable(h52.a(appCompatActivity));
        checkableImageView.setChecked(a());
        buttonCompat.setOnClickListener(this);
        setOnShowListener(this);
        setCancelable(false);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() { // from class: e52
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                g52 g52Var = this.a;
                ScrollView scrollView2 = g52Var.l;
                if (scrollView2.canScrollVertically(130)) {
                    return;
                }
                g52Var.j.setVisibility(8);
                g52Var.k.setVisibility(0);
                scrollView2.post(new zx1(5, g52Var));
            }
        });
    }

    public final boolean a() {
        LinearLayout linearLayout = this.h;
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
        ScrollView scrollView = this.l;
        if (id == i) {
            N.Mq9orIwX(15);
            if (scrollView.canScrollVertically(130)) {
                scrollView.post(new eu1(8, this));
                return;
            }
            this.j.setVisibility(8);
            this.k.setVisibility(0);
            scrollView.post(new f52(0, this));
            return;
        }
        if (id == R$id.dropdown_element) {
            boolean zA = a();
            LinearLayout linearLayout = this.h;
            if (zA) {
                N.Mq9orIwX(13);
                linearLayout.setVisibility(8);
                linearLayout.removeAllViews();
            } else {
                linearLayout.setVisibility(0);
                N.Mq9orIwX(12);
                LayoutInflater.from(getContext()).inflate(R$layout.privacy_sandbox_notice_row_dropdown_v4, linearLayout);
                Context context = getContext();
                int i2 = R$id.privacy_sandbox_m1_notice_row_learn_more_bullet_one;
                int i3 = R$string.privacy_sandbox_m1_notice_row_learn_more_bullet_1;
                View view2 = this.f;
                h52.b(context, view2, i2, i3);
                h52.b(getContext(), view2, R$id.privacy_sandbox_m1_notice_row_learn_more_bullet_two, R$string.privacy_sandbox_m1_notice_row_learn_more_bullet_2);
                scrollView.post(new qm0(25, this));
            }
            this.g.setChecked(a());
            h52.d(R$string.privacy_sandbox_m1_notice_row_learn_more_expand_label, getContext(), view, a());
            view.announceForAccessibility(getContext().getResources().getString(a() ? R$string.accessibility_expanded_group : R$string.accessibility_collapsed_group));
        }
    }

    @Override // android.content.DialogInterface.OnShowListener
    public final void onShow(DialogInterface dialogInterface) {
        ScrollView scrollView = this.l;
        boolean zCanScrollVertically = scrollView.canScrollVertically(130);
        LinearLayout linearLayout = this.k;
        ButtonCompat buttonCompat = this.j;
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
