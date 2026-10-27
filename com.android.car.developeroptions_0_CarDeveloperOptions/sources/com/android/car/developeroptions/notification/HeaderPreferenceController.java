package com.android.car.developeroptions.notification;

import android.app.NotificationChannelGroup;
import android.content.Context;
import android.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.PreferenceControllerMixin;
import com.android.car.developeroptions.widget.EntityHeaderController;
import com.android.settingslib.widget.LayoutPreference;

/* JADX INFO: loaded from: classes.dex */
public class HeaderPreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, LifecycleObserver {
    private final PreferenceFragmentCompat mFragment;
    private EntityHeaderController mHeaderController;
    private boolean mStarted;

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public String getPreferenceKey() {
        return "pref_app_header";
    }

    public HeaderPreferenceController(Context context, PreferenceFragmentCompat preferenceFragmentCompat) {
        super(context, null);
        this.mStarted = false;
        this.mFragment = preferenceFragmentCompat;
    }

    @Override // com.android.car.developeroptions.notification.NotificationPreferenceController, com.android.settingslib.core.AbstractPreferenceController
    public boolean isAvailable() {
        return this.mAppRow != null;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public void updateState(Preference preference) {
        PreferenceFragmentCompat preferenceFragmentCompat;
        if (this.mAppRow == null || (preferenceFragmentCompat = this.mFragment) == null) {
            return;
        }
        FragmentActivity activity = this.mStarted ? preferenceFragmentCompat.getActivity() : null;
        if (activity == null) {
            return;
        }
        EntityHeaderController entityHeaderControllerNewInstance = EntityHeaderController.newInstance(activity, this.mFragment, ((LayoutPreference) preference).findViewById(R.id.entity_header));
        this.mHeaderController = entityHeaderControllerNewInstance;
        entityHeaderControllerNewInstance.setIcon(this.mAppRow.icon);
        entityHeaderControllerNewInstance.setLabel(getLabel());
        entityHeaderControllerNewInstance.setSummary(getSummary());
        entityHeaderControllerNewInstance.setPackageName(this.mAppRow.pkg);
        entityHeaderControllerNewInstance.setUid(this.mAppRow.uid);
        entityHeaderControllerNewInstance.setButtonActions(1, 0);
        entityHeaderControllerNewInstance.setHasAppInfoLink(true);
        entityHeaderControllerNewInstance.done(activity, ((NotificationPreferenceController) this).mContext).findViewById(R.id.entity_header).setVisibility(0);
    }

    CharSequence getLabel() {
        if (this.mChannel != null && !isDefaultChannel()) {
            return this.mChannel.getName();
        }
        NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
        if (notificationChannelGroup != null) {
            return notificationChannelGroup.getName();
        }
        return this.mAppRow.label;
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        if (this.mChannel == null || isDefaultChannel()) {
            return this.mChannelGroup != null ? this.mAppRow.label.toString() : "";
        }
        NotificationChannelGroup notificationChannelGroup = this.mChannelGroup;
        if (notificationChannelGroup != null && !TextUtils.isEmpty(notificationChannelGroup.getName())) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            BidiFormatter bidiFormatter = BidiFormatter.getInstance();
            spannableStringBuilder.append((CharSequence) bidiFormatter.unicodeWrap(this.mAppRow.label.toString()));
            spannableStringBuilder.append(bidiFormatter.unicodeWrap(((NotificationPreferenceController) this).mContext.getText(R.string.notification_header_divider_symbol_with_spaces)));
            spannableStringBuilder.append((CharSequence) bidiFormatter.unicodeWrap(this.mChannelGroup.getName().toString()));
            return spannableStringBuilder.toString();
        }
        return this.mAppRow.label.toString();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {
        this.mStarted = true;
        EntityHeaderController entityHeaderController = this.mHeaderController;
        if (entityHeaderController != null) {
            entityHeaderController.styleActionBar(this.mFragment.getActivity());
        }
    }
}
