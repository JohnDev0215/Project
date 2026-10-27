package org.chromium.chrome.browser.password_check;

import android.content.res.Resources;
import android.text.method.LinkMovementMethod;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.dm0;
import defpackage.iq2;
import defpackage.un1;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$plurals;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_check.h;

/* JADX INFO: compiled from: PasswordCheckViewBinder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i {
    public static String a(View view, int i) {
        return view.getContext().getResources().getString(i);
    }

    public static void b(View view, int i, Integer num) {
        int i2;
        if (i == 0 && num == null) {
            return;
        }
        ImageView imageView = (ImageView) view.findViewById(R$id.check_status_icon);
        switch (i) {
            case 0:
                i2 = num.intValue() != 0 ? R$drawable.ic_warning_red_24dp : R$drawable.ic_check_circle_filled_green_24dp;
                break;
            case 1:
            case 2:
            default:
                i2 = 0;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                i2 = org.chromium.ui.R$drawable.ic_error_grey800_24dp_filled;
                break;
        }
        imageView.setImageResource(i2);
        imageView.setVisibility(i == 1 ? 8 : 0);
        view.findViewById(R$id.check_status_progress).setVisibility(i != 1 ? 8 : 0);
    }

    public static void c(View view, int i, Integer num) {
        int i2;
        if (i == 0 && num == null) {
            return;
        }
        ImageView imageView = (ImageView) view.findViewById(R$id.check_status_illustration);
        switch (i) {
            case 0:
                i2 = num.intValue() != 0 ? R$drawable.password_checkup_warning : R$drawable.password_check_positive;
                break;
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                i2 = R$drawable.password_check_neutral;
                break;
            case 2:
            default:
                i2 = 0;
                break;
        }
        imageView.setImageResource(i2);
    }

    public static void d(View view, int i, boolean z, Integer num) {
        String strA;
        if (i == 0 && num == null) {
            return;
        }
        TextView textView = (TextView) view.findViewById(R$id.check_status_subtitle);
        switch (i) {
            case 0:
                strA = num.intValue() != 0 ? a(view, R$string.password_check_status_subtitle_found_compromised_credentials) : a(view, R$string.password_check_status_subtitle_no_findings);
                break;
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                strA = a(view, R$string.password_check_status_subtitle_found_compromised_credentials);
                break;
            case 2:
            default:
                strA = null;
                break;
        }
        textView.setText(strA);
        textView.setVisibility(z ? 0 : 8);
    }

    public static void e(View view, int i, Integer num, Long l, Pair<Integer, Integer> pair, Runnable runnable) {
        CharSequence quantityString;
        int i2;
        if (i == 0 && (num == null || l == null)) {
            return;
        }
        if (i == 1 && pair == null) {
            return;
        }
        TextView textView = (TextView) view.findViewById(R$id.check_status_message);
        String string = null;
        switch (i) {
            case 0:
                quantityString = num.intValue() != 0 ? view.getContext().getResources().getQuantityString(R$plurals.password_check_status_message_idle_with_leaks, num.intValue(), num) : a(view, R$string.password_check_status_message_idle_no_leaks);
                break;
            case 1:
                quantityString = !pair.equals(h.b.i) ? String.format(a(view, R$string.password_check_status_message_running), pair.first, pair.second) : a(view, R$string.password_check_status_message_initial_running);
                break;
            case 2:
            default:
                quantityString = null;
                break;
            case 3:
                quantityString = a(view, R$string.password_check_status_message_error_offline);
                break;
            case 4:
                quantityString = a(view, R$string.password_check_status_message_error_no_passwords);
                break;
            case 5:
                quantityString = a(view, R$string.password_check_status_message_error_signed_out);
                break;
            case 6:
                quantityString = a(view, R$string.password_check_status_message_error_quota_limit);
                break;
            case 7:
                quantityString = iq2.a(a(view, R$string.password_check_status_message_error_quota_limit_account_check), new iq2.a(new un1(view.getContext(), new dm0(20, runnable)), "<link>", "</link>"));
                break;
            case 8:
                quantityString = a(view, R$string.password_check_status_message_error_unknown);
                break;
        }
        textView.setText(quantityString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.check_status_text_layout);
        switch (i) {
            case 0:
                i2 = R$dimen.check_status_message_idle_margin_vertical;
                break;
            case 1:
                i2 = R$dimen.check_status_message_running_margin_vertical;
                break;
            case 2:
            default:
                i2 = 0;
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                i2 = R$dimen.check_status_message_error_margin_vertical;
                break;
        }
        int dimensionPixelOffset = view.getContext().getResources().getDimensionPixelOffset(i2);
        linearLayout.setPadding(0, dimensionPixelOffset, 0, dimensionPixelOffset);
        TextView textView2 = (TextView) view.findViewById(R$id.check_status_description);
        if (l != null) {
            Resources resources = view.getContext().getResources();
            int i3 = R$string.password_check_status_description_idle;
            Object[] objArr = new Object[1];
            long jCurrentTimeMillis = System.currentTimeMillis() - l.longValue();
            if (jCurrentTimeMillis < 0) {
                jCurrentTimeMillis = 0;
            }
            int i4 = (int) (jCurrentTimeMillis / 86400000);
            int i5 = (int) (jCurrentTimeMillis / 3600000);
            int i6 = (int) (jCurrentTimeMillis / 60000);
            objArr[0] = ((long) i4) > 0 ? resources.getQuantityString(R$plurals.n_days_ago, i4, Integer.valueOf(i4)) : ((long) i5) > 0 ? resources.getQuantityString(R$plurals.n_hours_ago, i5, Integer.valueOf(i5)) : ((long) i6) > 0 ? resources.getQuantityString(R$plurals.n_minutes_ago, i6, Integer.valueOf(i6)) : resources.getString(R$string.password_check_just_now);
            string = resources.getString(i3, objArr);
        }
        textView2.setText(string);
        textView2.setVisibility(i != 0 ? 8 : 0);
    }
}
