package org.chromium.chrome.browser.privacy_sandbox;

import J.N;
import android.R;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.bz;
import defpackage.iq2;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.ui.drawable.StateListDrawableBuilder;
import org.chromium.ui.widget.ButtonCompat;
import org.chromium.ui.widget.CheckableImageView;
import org.chromium.ui.widget.ChromeBulletSpan;

/* JADX INFO: loaded from: classes2.dex */
public class PrivacySandboxDialogConsent extends Dialog implements View.OnClickListener {
    public final View e;
    public final LayoutInflater f;
    public final CheckableImageView g;
    public boolean h;

    public PrivacySandboxDialogConsent(Context context) {
        super(context, R$style.ThemeOverlay_BrowserUI_Fullscreen);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        this.f = layoutInflaterFrom;
        this.h = false;
        View viewInflate = layoutInflaterFrom.inflate(R$layout.privacy_sandbox_consent, (ViewGroup) null);
        this.e = viewInflate;
        setContentView(viewInflate);
        ((ButtonCompat) viewInflate.findViewById(R$id.yes_button)).setOnClickListener(this);
        ((ButtonCompat) viewInflate.findViewById(R$id.no_button)).setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.dropdown_element);
        linearLayout.setOnClickListener(this);
        b(linearLayout);
        CheckableImageView checkableImageView = (CheckableImageView) viewInflate.findViewById(R$id.expand_arrow);
        this.g = checkableImageView;
        StateListDrawableBuilder stateListDrawableBuilder = new StateListDrawableBuilder(context);
        StateListDrawableBuilder.a aVarA = stateListDrawableBuilder.a(R$drawable.ic_expand_less_black_24dp, R.attr.state_checked);
        StateListDrawableBuilder.a aVarA2 = stateListDrawableBuilder.a(R$drawable.ic_expand_more_black_24dp, new int[0]);
        stateListDrawableBuilder.b(aVarA, aVarA2, R$drawable.transition_expand_less_expand_more_black_24dp);
        stateListDrawableBuilder.b(aVarA2, aVarA, R$drawable.transition_expand_more_expand_less_black_24dp);
        AnimatedStateListDrawable animatedStateListDrawableC = stateListDrawableBuilder.c();
        animatedStateListDrawableC.setTintList(bz.b(context, R$color.default_icon_color_tint_list));
        checkableImageView.setImageDrawable(animatedStateListDrawableC);
        checkableImageView.setChecked(this.h);
    }

    public final void a(LinearLayout linearLayout, int i, int i2) {
        TextView textView = (TextView) linearLayout.findViewById(i);
        SpannableString spannableStringA = iq2.a(getContext().getResources().getString(i2), new iq2.a(new StyleSpan(1), "<b>", "</b>"));
        spannableStringA.setSpan(new ChromeBulletSpan(getContext()), 0, spannableStringA.length(), 0);
        textView.setText(spannableStringA);
    }

    public final void b(View view) {
        view.setContentDescription(getContext().getResources().getString(R$string.concat_two_strings_with_periods, getContext().getResources().getString(R$string.privacy_sandbox_consent_dropdown_button), getContext().getResources().getString(this.h ? R$string.accessibility_expanded_group : R$string.accessibility_collapsed_group)));
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        if (id == R$id.yes_button) {
            N.Mq9orIwX(6);
            dismiss();
            return;
        }
        if (id == R$id.no_button) {
            N.Mq9orIwX(7);
            dismiss();
            return;
        }
        if (id == R$id.dropdown_element) {
            LinearLayout linearLayout = (LinearLayout) this.e.findViewById(R$id.dropdown_container);
            if (this.h) {
                N.Mq9orIwX(9);
                linearLayout.removeAllViews();
                linearLayout.setVisibility(8);
            } else {
                N.Mq9orIwX(8);
                linearLayout.setVisibility(0);
                this.f.inflate(R$layout.privacy_sandbox_consent_dropdown, linearLayout);
                a(linearLayout, R$id.privacy_sandbox_consent_dropdown_topics_one, R$string.privacy_sandbox_learn_more_description_topics_1);
                a(linearLayout, R$id.privacy_sandbox_consent_dropdown_topics_two, R$string.privacy_sandbox_learn_more_description_topics_2);
                a(linearLayout, R$id.privacy_sandbox_consent_dropdown_topics_three, R$string.privacy_sandbox_learn_more_description_topics_3);
                a(linearLayout, R$id.privacy_sandbox_consent_dropdown_fledge_one, R$string.privacy_sandbox_learn_more_description_fledge_1);
                a(linearLayout, R$id.privacy_sandbox_consent_dropdown_fledge_two, R$string.privacy_sandbox_learn_more_description_fledge_2);
                a(linearLayout, R$id.privacy_sandbox_consent_dropdown_fledge_three, R$string.privacy_sandbox_learn_more_description_fledge_3);
            }
            boolean z = !this.h;
            this.h = z;
            this.g.setChecked(z);
            b(view);
            view.announceForAccessibility(getContext().getResources().getString(this.h ? R$string.accessibility_expanded_group : R$string.accessibility_collapsed_group));
        }
    }

    @Override // android.app.Dialog
    public final void show() {
        N.Mq9orIwX(5);
        super.show();
    }
}
