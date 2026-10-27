package com.android.car.settings.applications.assist;

import android.car.drivingstate.CarUxRestrictions;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.Settings;
import androidx.preference.TwoStatePreference;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.PreferenceController;
import com.android.internal.app.AssistUtils;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AssistConfigBasePreferenceController extends PreferenceController<TwoStatePreference> {
    private final AssistUtils mAssistUtils;
    private final SettingObserver mSettingObserver;

    protected abstract List<Uri> getSettingUris();

    public AssistConfigBasePreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mAssistUtils = new AssistUtils(context);
        this.mSettingObserver = new SettingObserver(getSettingUris(), new Runnable() { // from class: com.android.car.settings.applications.assist.-$$Lambda$xRokt-kLvpB2ZuwN8jUTEjocIMs
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.refreshUi();
            }
        });
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<TwoStatePreference> getPreferenceType() {
        return TwoStatePreference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected int getAvailabilityStatus() {
        return this.mAssistUtils.getAssistComponentForUser(UserHandle.myUserId()) != null ? 0 : 1;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStartInternal() {
        this.mSettingObserver.register(getContext().getContentResolver(), true);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void onStopInternal() {
        this.mSettingObserver.register(getContext().getContentResolver(), false);
    }

    private static class SettingObserver extends ContentObserver {
        private static final Uri ASSIST_URI = Settings.Secure.getUriFor("assistant");
        private final Runnable mSettingChangeListener;
        private final List<Uri> mUriList;

        SettingObserver(List<Uri> uriList, Runnable settingChangeListener) {
            super(new Handler(Looper.getMainLooper()));
            this.mUriList = uriList;
            this.mSettingChangeListener = settingChangeListener;
        }

        void register(ContentResolver cr, boolean register) {
            if (register) {
                cr.registerContentObserver(ASSIST_URI, false, this);
                List<Uri> list = this.mUriList;
                if (list != null) {
                    for (Uri uri : list) {
                        cr.registerContentObserver(uri, false, this);
                    }
                    return;
                }
                return;
            }
            cr.unregisterContentObserver(this);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
            if (shouldUpdatePreference(uri)) {
                this.mSettingChangeListener.run();
            }
        }

        private boolean shouldUpdatePreference(Uri uri) {
            List<Uri> list;
            return ASSIST_URI.equals(uri) || ((list = this.mUriList) != null && list.contains(uri));
        }
    }
}
