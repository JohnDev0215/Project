package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.text.BidiFormatter;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextDirectionHeuristics;
import android.text.style.BulletSpan;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;

/* JADX INFO: loaded from: classes5.dex */
public class ResetNetworkItemsPreferenceController extends PreferenceController<Preference> {
    public ResetNetworkItemsPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        SpannableStringBuilder sb = new SpannableStringBuilder();
        sb.append((CharSequence) getContext().getString(R.string.reset_network_desc));
        sb.append((CharSequence) System.lineSeparator());
        if (hasFeature("android.hardware.wifi")) {
            addBulletedValue(sb, R.string.reset_network_item_wifi);
        }
        if (hasFeature("android.hardware.telephony")) {
            addBulletedValue(sb, R.string.reset_network_item_mobile);
        }
        if (hasFeature("android.hardware.bluetooth")) {
            addBulletedValue(sb, R.string.reset_network_item_bluetooth);
        }
        preference.setTitle(sb);
    }

    private boolean hasFeature(String feature) {
        return getContext().getPackageManager().hasSystemFeature(feature);
    }

    private void addBulletedValue(SpannableStringBuilder sb, int resId) {
        sb.append(System.lineSeparator());
        SpannableString value = new SpannableString(BidiFormatter.getInstance().unicodeWrap(getContext().getString(resId), TextDirectionHeuristics.LOCALE));
        value.setSpan(new BulletSpan(10), 0, value.length(), 0);
        sb.append((CharSequence) value);
    }
}
