package androidx.leanback.preference;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.leanback.widget.VerticalGridView;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceRecyclerViewAccessibilityDelegate;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseLeanbackPreferenceFragmentCompat extends PreferenceFragmentCompat {
    private Context mThemedContext;

    @Override // androidx.fragment.app.Fragment
    public Context getContext() {
        if (this.mThemedContext == null && getActivity() != null) {
            TypedValue tv = new TypedValue();
            getActivity().getTheme().resolveAttribute(androidx.preference.R.attr.preferenceTheme, tv, true);
            int theme = tv.resourceId;
            if (theme == 0) {
                theme = R.style.PreferenceThemeOverlayLeanback;
            }
            this.mThemedContext = new ContextThemeWrapper(super.getContext(), theme);
        }
        return this.mThemedContext;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public RecyclerView onCreateRecyclerView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        VerticalGridView verticalGridView = (VerticalGridView) inflater.inflate(R.layout.leanback_preferences_list, parent, false);
        verticalGridView.setWindowAlignment(3);
        verticalGridView.setFocusScrollStrategy(0);
        verticalGridView.setAccessibilityDelegateCompat(new PreferenceRecyclerViewAccessibilityDelegate(verticalGridView));
        return verticalGridView;
    }

    @Override // androidx.preference.PreferenceFragmentCompat
    public Fragment getCallbackFragment() {
        return getParentFragment();
    }
}
