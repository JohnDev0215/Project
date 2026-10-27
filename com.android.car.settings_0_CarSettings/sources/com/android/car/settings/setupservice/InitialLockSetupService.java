package com.android.car.settings.setupservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.UserHandle;
import com.android.car.settings.common.Logger;
import com.android.car.settings.security.PasswordHelper;
import com.android.car.setupwizardlib.IInitialLockSetupService;
import com.android.car.setupwizardlib.InitialLockSetupHelper;
import com.android.car.setupwizardlib.LockConfig;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;
import com.android.internal.widget.LockscreenCredential;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class InitialLockSetupService extends Service {
    private static final Logger LOG = new Logger((Class<?>) InitialLockSetupService.class);
    private static final String SET_LOCK_PERMISSION = "com.android.car.settings.SET_INITIAL_LOCK";
    private final InitialLockSetupServiceImpl mIInitialLockSetupService = new InitialLockSetupServiceImpl();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LOG.v("onBind");
        if (checkCallingOrSelfPermission("com.android.car.settings.SET_INITIAL_LOCK") != 0) {
            return null;
        }
        int userId = UserHandle.myUserId();
        LockPatternUtils lockPatternUtils = new LockPatternUtils(getApplicationContext());
        if (lockPatternUtils.getKeyguardStoredPasswordQuality(userId) != 0) {
            LOG.v("Rejecting binding, lock exists");
            return null;
        }
        return this.mIInitialLockSetupService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<LockPatternView.Cell> toSettingsPattern(byte[] pattern) {
        List<LockPatternView.Cell> outputList = new ArrayList<>();
        for (int i = 0; i < pattern.length; i++) {
            outputList.add(LockPatternView.Cell.of(InitialLockSetupHelper.getPatternCellRowFromByte(pattern[i]), InitialLockSetupHelper.getPatternCellColumnFromByte(pattern[i])));
        }
        return outputList;
    }

    private class InitialLockSetupServiceImpl extends IInitialLockSetupService.Stub {
        private InitialLockSetupServiceImpl() {
        }

        @Override // com.android.car.setupwizardlib.IInitialLockSetupService
        public int getServiceVersion() {
            return 1;
        }

        @Override // com.android.car.setupwizardlib.IInitialLockSetupService
        public LockConfig getLockConfig(int lockType) {
            if (lockType == 0 || lockType == 1 || lockType == 2) {
                return new LockConfig(true, 4);
            }
            return null;
        }

        @Override // com.android.car.setupwizardlib.IInitialLockSetupService
        public int checkValidLock(int lockType, byte[] password) {
            if (lockType == 0) {
                PasswordHelper passwordHelper = new PasswordHelper(false);
                return passwordHelper.validateSetupWizard(password);
            }
            if (lockType == 1) {
                PasswordHelper passwordHelper2 = new PasswordHelper(true);
                return passwordHelper2.validateSetupWizard(password);
            }
            if (lockType == 2) {
                return password.length >= 4 ? 0 : 1;
            }
            InitialLockSetupService.LOG.e("other lock type, returning generic error");
            return 8;
        }

        @Override // com.android.car.setupwizardlib.IInitialLockSetupService
        public int setLock(int lockType, byte[] password) {
            int userId = UserHandle.myUserId();
            LockPatternUtils lockPatternUtils = new LockPatternUtils(InitialLockSetupService.this.getApplicationContext());
            int currentPassword = lockPatternUtils.getKeyguardStoredPasswordQuality(userId);
            if (currentPassword != 0) {
                InitialLockSetupService.LOG.v("Password already set, rejecting call to setLock");
                return -1;
            }
            if (!InitialLockSetupHelper.isValidLockResultCode(checkValidLock(lockType, password))) {
                InitialLockSetupService.LOG.v("Password is not valid, rejecting call to setLock");
                return -2;
            }
            boolean success = false;
            try {
                if (lockType != 0) {
                    if (lockType == 1) {
                        CharSequence pinStr = InitialLockSetupHelper.byteArrayToCharSequence(password);
                        lockPatternUtils.setLockCredential(LockscreenCredential.createPin(pinStr), LockscreenCredential.createNone(), userId);
                        success = true;
                    } else if (lockType != 2) {
                        InitialLockSetupService.LOG.e("Unknown lock type, returning a failure");
                    } else {
                        List<LockPatternView.Cell> pattern = InitialLockSetupService.this.toSettingsPattern(password);
                        lockPatternUtils.setLockCredential(LockscreenCredential.createPattern(pattern), LockscreenCredential.createNone(), userId);
                        pattern.clear();
                        success = true;
                    }
                } else {
                    CharSequence passwordStr = InitialLockSetupHelper.byteArrayToCharSequence(password);
                    lockPatternUtils.setLockCredential(LockscreenCredential.createPassword(passwordStr), LockscreenCredential.createNone(), userId);
                    success = true;
                }
            } catch (Exception e) {
                InitialLockSetupService.LOG.e("Save lock exception", e);
                success = false;
            }
            Arrays.fill(password, (byte) 0);
            return success ? 1 : -3;
        }
    }
}
