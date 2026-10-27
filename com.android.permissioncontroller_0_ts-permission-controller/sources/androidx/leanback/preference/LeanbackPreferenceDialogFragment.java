package androidx.leanback.preference;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import androidx.preference.DialogPreference;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class LeanbackPreferenceDialogFragment extends Fragment {
    public static final String ARG_KEY = "key";
    private DialogPreference mPreference;

    public LeanbackPreferenceDialogFragment() {
        if (Build.VERSION.SDK_INT >= 21) {
            LeanbackPreferenceFragmentTransitionHelperApi21.addTransitions(this);
        }
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fragment rawFragment = getTargetFragment();
        if (!(rawFragment instanceof DialogPreference.TargetFragment)) {
            throw new IllegalStateException("Target fragment " + rawFragment + " must implement TargetFragment interface");
        }
    }

    public DialogPreference getPreference() {
        if (this.mPreference == null) {
            String key = getArguments().getString("key");
            DialogPreference.TargetFragment fragment = (DialogPreference.TargetFragment) getTargetFragment();
            this.mPreference = (DialogPreference) fragment.findPreference(key);
        }
        return this.mPreference;
    }
}
