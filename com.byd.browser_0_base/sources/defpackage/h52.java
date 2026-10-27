package defpackage;

import android.R;
import android.content.Context;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.iq2;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.ui.drawable.StateListDrawableBuilder;
import org.chromium.ui.widget.ChromeBulletSpan;

/* JADX INFO: compiled from: PrivacySandboxDialogUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h52 {
    public static AnimatedStateListDrawable a(Context context) {
        StateListDrawableBuilder stateListDrawableBuilder = new StateListDrawableBuilder(context);
        StateListDrawableBuilder.a aVarA = stateListDrawableBuilder.a(R$drawable.ic_expand_less_black_24dp, R.attr.state_checked);
        StateListDrawableBuilder.a aVarA2 = stateListDrawableBuilder.a(R$drawable.ic_expand_more_black_24dp, new int[0]);
        stateListDrawableBuilder.b(aVarA, aVarA2, R$drawable.transition_expand_less_expand_more_black_24dp);
        stateListDrawableBuilder.b(aVarA2, aVarA, R$drawable.transition_expand_more_expand_less_black_24dp);
        AnimatedStateListDrawable animatedStateListDrawableC = stateListDrawableBuilder.c();
        animatedStateListDrawableC.setTintList(bz.b(context, R$color.default_icon_color_tint_list));
        return animatedStateListDrawableC;
    }

    public static void b(Context context, View view, int i, int i2) {
        TextView textView = (TextView) view.findViewById(i);
        SpannableString spannableString = new SpannableString(context.getResources().getString(i2));
        spannableString.setSpan(new ChromeBulletSpan(context), 0, spannableString.length(), 0);
        textView.setText(spannableString);
    }

    public static void c(Context context, LinearLayout linearLayout, int i, int i2) {
        TextView textView = (TextView) linearLayout.findViewById(i);
        SpannableString spannableStringA = iq2.a(context.getResources().getString(i2), new iq2.a(new TextAppearanceSpan(context, R$style.TextAppearance_TextMediumThick_Secondary), "<b>", "</b>"));
        spannableStringA.setSpan(new ChromeBulletSpan(context), 0, spannableStringA.length(), 0);
        textView.setText(spannableStringA);
    }

    public static void d(int i, Context context, View view, boolean z) {
        view.setContentDescription(context.getResources().getString(R$string.concat_two_strings_with_periods, context.getResources().getString(i), context.getResources().getString(z ? R$string.accessibility_expanded_group : R$string.accessibility_collapsed_group)));
    }
}
