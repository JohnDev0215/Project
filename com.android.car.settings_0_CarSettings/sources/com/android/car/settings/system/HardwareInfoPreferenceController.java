package com.android.car.settings.system;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Build;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.FragmentController;
import com.android.car.settings.common.Logger;
import com.android.car.settings.common.PreferenceController;
import com.android.settingslib.DeviceInfoUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX INFO: loaded from: classes5.dex */
public class HardwareInfoPreferenceController extends PreferenceController<Preference> {
    private static final Logger LOG = new Logger((Class<?>) HardwareInfoPreferenceController.class);

    public HardwareInfoPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected Class<Preference> getPreferenceType() {
        return Preference.class;
    }

    @Override // com.android.car.settings.common.PreferenceController
    protected void updateState(Preference preference) {
        preference.setSummary(getContext().getString(R.string.hardware_info_summary, getDeviceModel()));
    }

    private static String getDeviceModel() {
        FutureTask<String> msvSuffixTask = new FutureTask<>(new Callable() { // from class: com.android.car.settings.system.-$$Lambda$HardwareInfoPreferenceController$3gct6Seh_0Ciqy5HXwVBafn2SJ8
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return DeviceInfoUtils.getMsvSuffix();
            }
        });
        msvSuffixTask.run();
        try {
            String msvSuffix = msvSuffixTask.get();
            return Build.MODEL + msvSuffix;
        } catch (InterruptedException e) {
            LOG.e("Interruption error, so we only show model name");
            return Build.MODEL;
        } catch (ExecutionException e2) {
            LOG.e("Execution error, so we only show model name");
            return Build.MODEL;
        }
    }
}
