package com.android.car.settings.security;

import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.os.Bundle;
import android.os.UserHandle;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import com.android.car.settings.R;
import com.android.car.settings.common.ConfirmationDialogFragment;
import com.android.car.settings.common.FragmentController;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockscreenCredential;

/* JADX INFO: loaded from: classes5.dex */
public class NoLockPreferenceController extends LockTypeBasePreferenceController {
    private static final int[] ALLOWED_PASSWORD_QUALITIES = {0};
    final ConfirmationDialogFragment.ConfirmListener mConfirmListener;

    public /* synthetic */ void lambda$new$0$NoLockPreferenceController(Bundle arguments) {
        int userId = UserHandle.myUserId();
        new LockPatternUtils(getContext()).setLockCredential(LockscreenCredential.createNone(), getCurrentPassword(), userId);
        getFragmentController().goBack();
    }

    public NoLockPreferenceController(Context context, String preferenceKey, FragmentController fragmentController, CarUxRestrictions uxRestrictions) {
        super(context, preferenceKey, fragmentController, uxRestrictions);
        this.mConfirmListener = new ConfirmationDialogFragment.ConfirmListener() { // from class: com.android.car.settings.security.-$$Lambda$NoLockPreferenceController$inBY8YHhaE0dxSiKq_DgaPrMy7Y
            @Override // com.android.car.settings.common.ConfirmationDialogFragment.ConfirmListener
            public final void onConfirm(Bundle bundle) {
                this.f$0.lambda$new$0$NoLockPreferenceController(bundle);
            }
        };
    }

    @Override // com.android.car.settings.security.LockTypeBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected void onCreateInternal() {
        super.onCreateInternal();
        ConfirmationDialogFragment dialog = (ConfirmationDialogFragment) getFragmentController().findDialogByTag(ConfirmationDialogFragment.TAG);
        ConfirmationDialogFragment.resetListeners(dialog, this.mConfirmListener, null, null);
    }

    @Override // com.android.car.settings.security.LockTypeBasePreferenceController, com.android.car.settings.common.PreferenceController
    protected boolean handlePreferenceClicked(Preference preference) {
        if (isCurrentLock()) {
            getFragmentController().goBack();
            return true;
        }
        getFragmentController().showDialog(getConfirmRemoveScreenLockDialogFragment(), ConfirmationDialogFragment.TAG);
        return true;
    }

    @Override // com.android.car.settings.security.LockTypeBasePreferenceController
    protected Fragment fragmentToOpen() {
        return null;
    }

    @Override // com.android.car.settings.security.LockTypeBasePreferenceController
    protected int[] allowedPasswordQualities() {
        return ALLOWED_PASSWORD_QUALITIES;
    }

    private ConfirmationDialogFragment getConfirmRemoveScreenLockDialogFragment() {
        return new ConfirmationDialogFragment.Builder(getContext()).setTitle(R.string.remove_screen_lock_title).setMessage(R.string.remove_screen_lock_message).setPositiveButton(R.string.remove_button, this.mConfirmListener).setNegativeButton(android.R.string.cancel, (ConfirmationDialogFragment.RejectListener) null).build();
    }
}
