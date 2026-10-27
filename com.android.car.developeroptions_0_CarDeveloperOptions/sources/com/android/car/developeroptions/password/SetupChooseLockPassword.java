package com.android.car.developeroptions.password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SetupRedactionInterstitial;
import com.android.car.developeroptions.password.ChooseLockPassword;
import com.android.car.developeroptions.password.ChooseLockTypeDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class SetupChooseLockPassword extends ChooseLockPassword {
    public static Intent modifyIntentForSetup(Context context, Intent intent) {
        intent.setClass(context, SetupChooseLockPassword.class);
        intent.putExtra("extra_prefs_show_button_bar", false);
        return intent;
    }

    @Override // com.android.car.developeroptions.password.ChooseLockPassword, com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        return SetupChooseLockPasswordFragment.class.getName().equals(str);
    }

    @Override // com.android.car.developeroptions.password.ChooseLockPassword
    Class<? extends Fragment> getFragmentClass() {
        return SetupChooseLockPasswordFragment.class;
    }

    @Override // com.android.car.developeroptions.password.ChooseLockPassword, com.android.car.developeroptions.SettingsActivity, com.android.car.developeroptions.core.SettingsBaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((LinearLayout) findViewById(R.id.content_parent)).setFitsSystemWindows(false);
    }

    public static class SetupChooseLockPasswordFragment extends ChooseLockPassword.ChooseLockPasswordFragment implements ChooseLockTypeDialogFragment.OnLockTypeSelectedListener {
        private boolean mLeftButtonIsSkip;
        private Button mOptionsButton;

        @Override // com.android.car.developeroptions.password.ChooseLockPassword.ChooseLockPasswordFragment, androidx.fragment.app.Fragment
        public void onViewCreated(View view, Bundle bundle) {
            super.onViewCreated(view, bundle);
            FragmentActivity activity = getActivity();
            boolean z = new ChooseLockGenericController(activity, this.mUserId).getVisibleScreenLockTypes(65536, false).size() > 0;
            boolean booleanExtra = activity.getIntent().getBooleanExtra("show_options_button", false);
            if (!z) {
                Log.w("SetupChooseLockPassword", "Visible screen lock types is empty!");
            }
            if (booleanExtra && z) {
                Button button = (Button) view.findViewById(R.id.screen_lock_options);
                this.mOptionsButton = button;
                button.setVisibility(0);
                this.mOptionsButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.password.-$$Lambda$SetupChooseLockPassword$SetupChooseLockPasswordFragment$zN_EpJ5J8VB9dw6O320QFUICPvM
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.lambda$onViewCreated$0$SetupChooseLockPassword$SetupChooseLockPasswordFragment(view2);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onViewCreated$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onViewCreated$0$SetupChooseLockPassword$SetupChooseLockPasswordFragment(View view) {
            ChooseLockTypeDialogFragment.newInstance(this.mUserId).show(getChildFragmentManager(), "skip_screen_lock_dialog");
        }

        @Override // com.android.car.developeroptions.password.ChooseLockPassword.ChooseLockPasswordFragment
        protected void onSkipOrClearButtonClick(View view) {
            if (this.mLeftButtonIsSkip) {
                SetupSkipDialog.newInstance(getActivity().getIntent().getBooleanExtra(":settings:frp_supported", false)).show(getFragmentManager());
            } else {
                super.onSkipOrClearButtonClick(view);
            }
        }

        @Override // com.android.car.developeroptions.password.ChooseLockPassword.ChooseLockPasswordFragment
        protected Intent getRedactionInterstitialIntent(Context context) {
            SetupRedactionInterstitial.setEnabled(context, true);
            return null;
        }

        @Override // com.android.car.developeroptions.password.ChooseLockTypeDialogFragment.OnLockTypeSelectedListener
        public void onLockTypeSelected(ScreenLockType screenLockType) {
            if (screenLockType == (this.mIsAlphaMode ? ScreenLockType.PASSWORD : ScreenLockType.PIN)) {
                return;
            }
            startChooseLockActivity(screenLockType, getActivity());
        }

        @Override // com.android.car.developeroptions.password.ChooseLockPassword.ChooseLockPasswordFragment
        protected void updateUi() {
            super.updateUi();
            if (this.mUiStage == ChooseLockPassword.ChooseLockPasswordFragment.Stage.Introduction) {
                this.mSkipOrClearButton.setText(getActivity(), R.string.skip_label);
                this.mLeftButtonIsSkip = true;
            } else {
                this.mSkipOrClearButton.setText(getActivity(), R.string.lockpassword_clear_label);
                this.mLeftButtonIsSkip = false;
            }
            Button button = this.mOptionsButton;
            if (button != null) {
                button.setVisibility(this.mUiStage != ChooseLockPassword.ChooseLockPasswordFragment.Stage.Introduction ? 8 : 0);
            }
        }
    }
}
