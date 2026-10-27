package org.chromium.chrome.browser.privacy_sandbox;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import androidx.preference.d;
import defpackage.iq2;
import defpackage.qj2;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$xml;
import org.chromium.components.browser_ui.settings.LongSummaryTextMessagePreference;
import org.chromium.ui.widget.ChromeBulletSpan;

/* JADX INFO: loaded from: classes2.dex */
public class LearnMoreFragment extends d {
    @Override // androidx.preference.d
    public final void J0(Bundle bundle, String str) {
        D().setTitle(R$string.privacy_sandbox_learn_more_title);
        qj2.a(this, R$xml.learn_more_preference);
        LongSummaryTextMessagePreference longSummaryTextMessagePreference = (LongSummaryTextMessagePreference) f("topics_description");
        longSummaryTextMessagePreference.Z = null;
        TextView textView = longSummaryTextMessagePreference.Y;
        if (textView != null) {
            textView.setMovementMethod(null);
        }
        longSummaryTextMessagePreference.R(TextUtils.concat(M0(R$string.privacy_sandbox_learn_more_description_topics_1), "\n\n", M0(R$string.privacy_sandbox_learn_more_description_topics_2), "\n\n", M0(R$string.privacy_sandbox_learn_more_description_topics_3)));
        LongSummaryTextMessagePreference longSummaryTextMessagePreference2 = (LongSummaryTextMessagePreference) f("fledge_description");
        longSummaryTextMessagePreference2.Z = null;
        TextView textView2 = longSummaryTextMessagePreference2.Y;
        if (textView2 != null) {
            textView2.setMovementMethod(null);
        }
        longSummaryTextMessagePreference2.R(TextUtils.concat(M0(R$string.privacy_sandbox_learn_more_description_fledge_1), "\n\n", M0(R$string.privacy_sandbox_learn_more_description_fledge_2), "\n\n", M0(R$string.privacy_sandbox_learn_more_description_fledge_3)));
        D0();
    }

    public final SpannableString M0(int i) {
        SpannableString spannableStringA = iq2.a(K().getString(i), new iq2.a(new StyleSpan(1), "<b>", "</b>"));
        spannableStringA.setSpan(new ChromeBulletSpan(K()), 0, spannableStringA.length(), 0);
        return spannableStringA;
    }

    @Override // androidx.fragment.app.Fragment
    public final void d0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
    }
}
