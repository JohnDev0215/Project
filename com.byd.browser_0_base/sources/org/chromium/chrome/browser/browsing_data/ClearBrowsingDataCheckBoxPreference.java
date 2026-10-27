package org.chromium.chrome.browser.browsing_data;

import android.R;
import android.content.Context;
import android.graphics.PorterDuff;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.iq2;
import defpackage.n32;
import defpackage.qo;
import defpackage.un1;
import defpackage.wb;
import org.chromium.chrome.R$drawable;
import org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference;

/* JADX INFO: loaded from: classes.dex */
public class ClearBrowsingDataCheckBoxPreference extends ChromeBaseCheckBoxPreference {
    public View Y;
    public Runnable Z;
    public boolean a0;

    public ClearBrowsingDataCheckBoxPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.preference.Preference
    public final void R(CharSequence charSequence) {
        String string = charSequence.toString();
        if (!string.contains("<link>") || !string.contains("</link>")) {
            super.R(charSequence);
        } else {
            if (qo.f().c()) {
                super.R(string.replaceAll("</?link>", ""));
                return;
            }
            SpannableString spannableStringA = iq2.a(string, new iq2.a(new un1(this.e, new wb(9, this)), "<link>", "</link>"));
            this.a0 = true;
            super.R(spannableStringA);
        }
    }

    @Override // org.chromium.components.browser_ui.settings.ChromeBaseCheckBoxPreference, androidx.preference.CheckBoxPreference, androidx.preference.Preference
    public final void y(n32 n32Var) {
        super.y(n32Var);
        View view = n32Var.e;
        this.Y = view;
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);
        if (checkBox != null) {
            checkBox.setButtonTintMode(PorterDuff.Mode.DST);
            boolean z = this.S;
            Context context = this.e;
            if (z) {
                checkBox.setButtonDrawable(AppCompatResources.a(context, R$drawable.check_box_select));
            } else {
                checkBox.setButtonDrawable(AppCompatResources.a(context, R$drawable.check_box_no_select));
            }
        }
        final TextView textView = (TextView) this.Y.findViewById(R.id.summary);
        textView.setOnTouchListener(new View.OnTouchListener() { // from class: ot
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                if (!this.e.a0) {
                    return false;
                }
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                TextView textView2 = textView;
                int offsetForPosition = textView2.getOffsetForPosition(x, y);
                CharSequence text = textView2.getText();
                if (!(text instanceof Spanned)) {
                    return false;
                }
                ClickableSpan[] clickableSpanArr = (ClickableSpan[]) ((Spanned) text).getSpans(offsetForPosition, offsetForPosition, ClickableSpan.class);
                if (clickableSpanArr.length <= 0) {
                    return false;
                }
                if (motionEvent.getAction() == 1) {
                    for (ClickableSpan clickableSpan : clickableSpanArr) {
                        clickableSpan.onClick(textView2);
                    }
                }
                return true;
            }
        });
    }
}
