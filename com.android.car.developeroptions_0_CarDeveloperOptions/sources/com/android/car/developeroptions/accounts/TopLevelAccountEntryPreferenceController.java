package com.android.car.developeroptions.accounts;

import android.content.Context;
import android.content.IntentFilter;
import android.icu.text.ListFormatter;
import android.os.UserHandle;
import android.text.BidiFormatter;
import android.text.TextUtils;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.BasePreferenceController;
import com.android.car.developeroptions.slices.SliceBackgroundWorker;
import com.android.settingslib.accounts.AuthenticatorHelper;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TopLevelAccountEntryPreferenceController extends BasePreferenceController {
    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ void copy() {
        super.copy();
    }

    @Override // com.android.car.developeroptions.core.BasePreferenceController
    public int getAvailabilityStatus() {
        return 1;
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ Class<? extends SliceBackgroundWorker> getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isCopyableSlice() {
        return super.isCopyableSlice();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    @Override // com.android.car.developeroptions.slices.Sliceable
    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public TopLevelAccountEntryPreferenceController(Context context, String str) {
        super(context, str);
    }

    @Override // com.android.settingslib.core.AbstractPreferenceController
    public CharSequence getSummary() {
        AuthenticatorHelper authenticatorHelper = new AuthenticatorHelper(this.mContext, UserHandle.of(UserHandle.myUserId()), null);
        String[] enabledAccountTypes = authenticatorHelper.getEnabledAccountTypes();
        BidiFormatter bidiFormatter = BidiFormatter.getInstance();
        ArrayList arrayList = new ArrayList();
        if (enabledAccountTypes == null || enabledAccountTypes.length == 0) {
            arrayList.add(this.mContext.getString(R.string.account_dashboard_default_summary));
        } else {
            int iMin = Math.min(3, enabledAccountTypes.length);
            for (int i = 0; i < enabledAccountTypes.length && iMin > 0; i++) {
                CharSequence labelForType = authenticatorHelper.getLabelForType(this.mContext, enabledAccountTypes[i]);
                if (!TextUtils.isEmpty(labelForType)) {
                    arrayList.add(bidiFormatter.unicodeWrap(labelForType));
                    iMin--;
                }
            }
        }
        return ListFormatter.getInstance().format(arrayList);
    }
}
