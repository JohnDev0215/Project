package androidx.leanback.preference;

import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.preference.DialogPreference;

/* JADX INFO: loaded from: classes3.dex */
public class LeanbackPreferenceDialogFragmentCompat extends Fragment {
    public static final String ARG_KEY = "key";
    private DialogPreference mPreference;

    public LeanbackPreferenceDialogFragmentCompat() {
        if (Build.VERSION.SDK_INT >= 21) {
            LeanbackPreferenceFragmentTransitionHelperApi21.addTransitions(this);
        }
    }

    @Override // androidx.fragment.app.Fragment
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
