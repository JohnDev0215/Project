package com.android.car.developeroptions.password;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.SetupRedactionInterstitial;
import com.android.car.developeroptions.password.ChooseLockPattern;
import com.android.car.developeroptions.password.ChooseLockTypeDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class SetupChooseLockPattern extends ChooseLockPattern {
    public static Intent modifyIntentForSetup(Context context, Intent intent) {
        intent.setClass(context, SetupChooseLockPattern.class);
        return intent;
    }

    @Override // com.android.car.developeroptions.password.ChooseLockPattern, com.android.car.developeroptions.SettingsActivity
    protected boolean isValidFragment(String str) {
        return SetupChooseLockPatternFragment.class.getName().equals(str);
    }

    @Override // com.android.car.developeroptions.password.ChooseLockPattern
    Class<? extends Fragment> getFragmentClass() {
        return SetupChooseLockPatternFragment.class;
    }

    public static class SetupChooseLockPatternFragment extends ChooseLockPattern.ChooseLockPatternFragment implements ChooseLockTypeDialogFragment.OnLockTypeSelectedListener {
        private boolean mLeftButtonIsSkip;
        private Button mOptionsButton;

        @Override // com.android.car.developeroptions.password.ChooseLockPattern.ChooseLockPatternFragment, androidx.fragment.app.Fragment
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View viewOnCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
            if (!getResources().getBoolean(R.bool.config_lock_pattern_minimal_ui)) {
                Button button = (Button) viewOnCreateView.findViewById(R.id.screen_lock_options);
                this.mOptionsButton = button;
                button.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.password.-$$Lambda$SetupChooseLockPattern$SetupChooseLockPatternFragment$OhOWXzRoYFY2FX60VxlEHlX1jK0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$onCreateView$0$SetupChooseLockPattern$SetupChooseLockPatternFragment(view);
                    }
                });
            }
            this.mSkipOrClearButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.password.-$$Lambda$VIE2NYdg2tUtvBs0S7cMWhid6oo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.onSkipOrClearButtonClick(view);
                }
            });
            return viewOnCreateView;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onCreateView$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onCreateView$0$SetupChooseLockPattern$SetupChooseLockPatternFragment(View view) {
            ChooseLockTypeDialogFragment.newInstance(this.mUserId).show(getChildFragmentManager(), "skip_screen_lock_dialog");
        }

        @Override // com.android.car.developeroptions.password.ChooseLockPattern.ChooseLockPatternFragment
        protected void onSkipOrClearButtonClick(View view) {
            if (this.mLeftButtonIsSkip) {
                SetupSkipDialog.newInstance(getActivity().getIntent().getBooleanExtra(":settings:frp_supported", false)).show(getFragmentManager());
            } else {
                super.onSkipOrClearButtonClick(view);
            }
        }

        @Override // com.android.car.developeroptions.password.ChooseLockTypeDialogFragment.OnLockTypeSelectedListener
        public void onLockTypeSelected(ScreenLockType screenLockType) {
            if (ScreenLockType.PATTERN == screenLockType) {
                return;
            }
            startChooseLockActivity(screenLockType, getActivity());
        }

        @Override // com.android.car.developeroptions.password.ChooseLockPattern.ChooseLockPatternFragment
        protected void updateStage(ChooseLockPattern.ChooseLockPatternFragment.Stage stage) {
            Button button;
            super.updateStage(stage);
            if (!getResources().getBoolean(R.bool.config_lock_pattern_minimal_ui) && (button = this.mOptionsButton) != null) {
                button.setVisibility((stage == ChooseLockPattern.ChooseLockPatternFragment.Stage.Introduction || stage == ChooseLockPattern.ChooseLockPatternFragment.Stage.HelpScreen || stage == ChooseLockPattern.ChooseLockPatternFragment.Stage.ChoiceTooShort || stage == ChooseLockPattern.ChooseLockPatternFragment.Stage.FirstChoiceValid) ? 0 : 4);
            }
            if (stage.leftMode == ChooseLockPattern.ChooseLockPatternFragment.LeftButtonMode.Gone && stage == ChooseLockPattern.ChooseLockPatternFragment.Stage.Introduction) {
                this.mSkipOrClearButton.setVisibility(0);
                this.mSkipOrClearButton.setText(getActivity(), R.string.skip_label);
                this.mLeftButtonIsSkip = true;
                return;
            }
            this.mLeftButtonIsSkip = false;
        }

        @Override // com.android.car.developeroptions.password.ChooseLockPattern.ChooseLockPatternFragment
        protected Intent getRedactionInterstitialIntent(Context context) {
            SetupRedactionInterstitial.setEnabled(context, true);
            return null;
        }
    }
}
