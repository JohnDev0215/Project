package defpackage;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import java.util.Locale;
import org.chromium.components.browser_ui.settings.R$id;
import org.chromium.components.browser_ui.settings.R$string;
import org.chromium.components.browser_ui.widget.text.TextViewWithCompoundDrawables;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: ManagedPreferencesUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class nb1 {
    public static String a(mb1 mb1Var, Preference preference) {
        if (mb1Var == null) {
            return null;
        }
        if (mb1Var.r(preference)) {
            return preference.e.getString(R$string.managed_by_your_organization);
        }
        if (mb1Var.b(preference)) {
            return preference.e.getString(mb1Var.e() ? R$string.managed_by_your_parents : R$string.managed_by_your_parent);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.drawable.Drawable b(defpackage.mb1 r1, androidx.preference.Preference r2) {
        /*
            if (r1 != 0) goto L3
            goto L15
        L3:
            boolean r0 = r1.r(r2)
            if (r0 == 0) goto Lc
            int r1 = org.chromium.components.browser_ui.settings.R$drawable.ic_business_small
            goto L16
        Lc:
            boolean r1 = r1.b(r2)
            if (r1 == 0) goto L15
            int r1 = org.chromium.components.browser_ui.settings.R$drawable.ic_account_child_grey600_36dp
            goto L16
        L15:
            r1 = 0
        L16:
            if (r1 != 0) goto L2b
            android.graphics.drawable.Drawable r1 = r2.o
            if (r1 != 0) goto L28
            int r1 = r2.n
            if (r1 == 0) goto L28
            android.content.Context r0 = r2.e
            android.graphics.drawable.Drawable r1 = androidx.appcompat.content.res.AppCompatResources.a(r0, r1)
            r2.o = r1
        L28:
            android.graphics.drawable.Drawable r1 = r2.o
            goto L31
        L2b:
            android.content.Context r2 = r2.e
            android.graphics.drawable.Drawable r1 = defpackage.qj2.b(r2, r1)
        L31:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nb1.b(mb1, androidx.preference.Preference):android.graphics.drawable.Drawable");
    }

    public static void c(mb1 mb1Var, Preference preference, boolean z, boolean z2) {
        int iD;
        if (mb1Var == null) {
            return;
        }
        if (!z2 && (iD = mb1Var.d()) != 0) {
            preference.f17J = iD;
        }
        if (z) {
            preference.L(b(mb1Var, preference));
        }
        if (mb1Var.g(preference)) {
            if (preference.I) {
                preference.I = false;
                preference.s();
            }
            preference.J(false);
            preference.r = null;
            preference.q = null;
            preference.setOnPreferenceClickListener(null);
        }
    }

    public static void d(mb1 mb1Var, Preference preference, View view) {
        if (mb1Var == null) {
            return;
        }
        if (!oj2.y()) {
            if (mb1Var.g(preference)) {
                ao3.g(view, false);
            }
            TextView textView = (TextView) view.findViewById(R.id.summary);
            f(textView.getVisibility() == 0 ? textView.getText() : null, a(mb1Var, preference), view);
            return;
        }
        if (mb1Var.g(preference)) {
            ao3.g(view, false);
        }
        TextView textView2 = (TextView) view.findViewById(R.id.summary);
        CharSequence text = textView2.getVisibility() == 0 ? textView2.getText() : null;
        int i = R$id.managed_disclaimer_text;
        if (view.findViewById(i) == null || !mb1Var.r(preference)) {
            f(text, a(mb1Var, preference), view);
            return;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        if (imageView != null) {
            imageView.setVisibility(8);
        }
        View viewFindViewById = view.findViewById(R$id.icon_frame);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(8);
        }
        Context context = preference.e;
        if (TextUtils.isEmpty(text)) {
            ((TextView) view.findViewById(R.id.summary)).setVisibility(8);
        } else {
            g(view, text);
        }
        TextViewWithCompoundDrawables textViewWithCompoundDrawables = (TextViewWithCompoundDrawables) view.findViewById(i);
        textViewWithCompoundDrawables.setVisibility(0);
        textViewWithCompoundDrawables.setEnabled(true);
    }

    public static boolean e(mb1 mb1Var, Preference preference) {
        if (mb1Var == null || !mb1Var.g(preference)) {
            return false;
        }
        if (mb1Var.r(preference)) {
            h(preference.e, R$string.managed_by_your_organization);
            return true;
        }
        if (!mb1Var.b(preference)) {
            return true;
        }
        h(preference.e, mb1Var.e() ? R$string.managed_by_your_parents : R$string.managed_by_your_parent);
        return true;
    }

    public static void f(CharSequence charSequence, String str, View view) {
        boolean zIsEmpty = TextUtils.isEmpty(charSequence);
        boolean zIsEmpty2 = TextUtils.isEmpty(str);
        if (zIsEmpty && zIsEmpty2) {
            ((TextView) view.findViewById(R.id.summary)).setVisibility(8);
        } else if (zIsEmpty) {
            g(view, str);
        } else if (zIsEmpty2) {
            g(view, charSequence);
        } else {
            g(view, String.format(Locale.getDefault(), "%s\n%s", charSequence, str));
        }
        View viewFindViewById = view.findViewById(R$id.managed_disclaimer_text);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(8);
        }
    }

    public static void g(View view, CharSequence charSequence) {
        TextView textView = (TextView) view.findViewById(R.id.summary);
        textView.setText(charSequence);
        textView.setVisibility(0);
    }

    public static Toast h(Context context, int i) {
        Toast toastB = Toast.b(1, context, context.getString(i));
        toastB.e();
        return toastB;
    }
}
