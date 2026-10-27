package com.android.car.developeroptions.password;

import android.app.admin.DevicePolicyManager;
import android.app.admin.PasswordMetrics;
import android.content.Context;
import android.os.UserHandle;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.intelligence.LogProto$SettingsLog;
import com.android.internal.widget.LockPatternUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChooseLockGenericController {
    private final Context mContext;
    private DevicePolicyManager mDpm;
    private final LockPatternUtils mLockPatternUtils;
    private ManagedLockPasswordProvider mManagedPasswordProvider;
    private final int mRequestedMinComplexity;
    private final int mUserId;

    public ChooseLockGenericController(Context context, int i) {
        this(context, i, 0, new LockPatternUtils(context));
    }

    public ChooseLockGenericController(Context context, int i, int i2, LockPatternUtils lockPatternUtils) {
        this(context, i, i2, (DevicePolicyManager) context.getSystemService(DevicePolicyManager.class), ManagedLockPasswordProvider.get(context, i), lockPatternUtils);
    }

    ChooseLockGenericController(Context context, int i, int i2, DevicePolicyManager devicePolicyManager, ManagedLockPasswordProvider managedLockPasswordProvider, LockPatternUtils lockPatternUtils) {
        this.mContext = context;
        this.mUserId = i;
        this.mRequestedMinComplexity = i2;
        this.mManagedPasswordProvider = managedLockPasswordProvider;
        this.mDpm = devicePolicyManager;
        this.mLockPatternUtils = lockPatternUtils;
    }

    public int upgradeQuality(int i) {
        return Math.max(Math.max(i, this.mDpm.getPasswordQuality(null, this.mUserId)), PasswordMetrics.complexityLevelToMinQuality(this.mRequestedMinComplexity));
    }

    /* JADX INFO: renamed from: com.android.car.developeroptions.password.ChooseLockGenericController$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$developeroptions$password$ScreenLockType;

        static {
            int[] iArr = new int[ScreenLockType.values().length];
            $SwitchMap$com$android$car$developeroptions$password$ScreenLockType = iArr;
            try {
                iArr[ScreenLockType.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.SWIPE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.MANAGED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.PIN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.PATTERN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$car$developeroptions$password$ScreenLockType[ScreenLockType.PASSWORD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public boolean isScreenLockVisible(ScreenLockType screenLockType) {
        boolean z = this.mUserId != UserHandle.myUserId();
        switch (AnonymousClass1.$SwitchMap$com$android$car$developeroptions$password$ScreenLockType[screenLockType.ordinal()]) {
            case 1:
                return (this.mContext.getResources().getBoolean(R.bool.config_hide_none_security_option) || z) ? false : true;
            case 2:
                return (this.mContext.getResources().getBoolean(R.bool.config_hide_swipe_security_option) || z) ? false : true;
            case 3:
                return this.mManagedPasswordProvider.isManagedPasswordChoosable();
            case 4:
            case 5:
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                return this.mLockPatternUtils.hasSecureLockScreen();
            default:
                return true;
        }
    }

    public boolean isScreenLockEnabled(ScreenLockType screenLockType, int i) {
        return screenLockType.maxQuality >= i;
    }

    public boolean isScreenLockDisabledByAdmin(ScreenLockType screenLockType, int i) {
        boolean z = true;
        boolean z2 = screenLockType.maxQuality < i;
        if (screenLockType != ScreenLockType.MANAGED) {
            return z2;
        }
        if (!z2 && this.mManagedPasswordProvider.isManagedPasswordChoosable()) {
            z = false;
        }
        return z;
    }

    public CharSequence getTitle(ScreenLockType screenLockType) {
        switch (AnonymousClass1.$SwitchMap$com$android$car$developeroptions$password$ScreenLockType[screenLockType.ordinal()]) {
            case 1:
                return this.mContext.getText(R.string.unlock_set_unlock_off_title);
            case 2:
                return this.mContext.getText(R.string.unlock_set_unlock_none_title);
            case 3:
                return this.mManagedPasswordProvider.getPickerOptionTitle(false);
            case 4:
                return this.mContext.getText(R.string.unlock_set_unlock_pin_title);
            case 5:
                return this.mContext.getText(R.string.unlock_set_unlock_pattern_title);
            case LogProto$SettingsLog.TIMESTAMP_FIELD_NUMBER /* 6 */:
                return this.mContext.getText(R.string.unlock_set_unlock_password_title);
            default:
                return null;
        }
    }

    public List<ScreenLockType> getVisibleScreenLockTypes(int i, boolean z) {
        int iUpgradeQuality = upgradeQuality(i);
        ArrayList arrayList = new ArrayList();
        for (ScreenLockType screenLockType : ScreenLockType.values()) {
            if (isScreenLockVisible(screenLockType) && (z || isScreenLockEnabled(screenLockType, iUpgradeQuality))) {
                arrayList.add(screenLockType);
            }
        }
        return arrayList;
    }
}
