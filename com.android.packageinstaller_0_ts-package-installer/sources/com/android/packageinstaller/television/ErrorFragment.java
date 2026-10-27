package com.android.packageinstaller.television;

import android.os.Bundle;
import androidx.leanback.app.GuidedStepFragment;
import androidx.leanback.widget.GuidanceStylist;
import androidx.leanback.widget.GuidedAction;
import com.android.packageinstaller.UninstallerActivity;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ErrorFragment extends GuidedStepFragment {
    public static final String TEXT = "com.android.packageinstaller.arg.text";
    public static final String TITLE = "com.android.packageinstaller.arg.title";

    @Override // androidx.leanback.app.GuidedStepFragment
    public int onProvideTheme() {
        return 2131689787;
    }

    @Override // androidx.leanback.app.GuidedStepFragment
    public GuidanceStylist.Guidance onCreateGuidance(Bundle savedInstanceState) {
        return new GuidanceStylist.Guidance(getString(getArguments().getInt("com.android.packageinstaller.arg.title")), getString(getArguments().getInt("com.android.packageinstaller.arg.text")), null, null);
    }

    @Override // androidx.leanback.app.GuidedStepFragment
    public void onCreateActions(List<GuidedAction> actions, Bundle savedInstanceState) {
        actions.add(new GuidedAction.Builder(getContext()).clickAction(-4L).build());
    }

    @Override // androidx.leanback.app.GuidedStepFragment
    public void onGuidedActionClicked(GuidedAction action) {
        if (isAdded()) {
            if (getActivity() instanceof UninstallerActivity) {
                ((UninstallerActivity) getActivity()).dispatchAborted();
            }
            getActivity().setResult(1);
            getActivity().finish();
        }
    }
}
