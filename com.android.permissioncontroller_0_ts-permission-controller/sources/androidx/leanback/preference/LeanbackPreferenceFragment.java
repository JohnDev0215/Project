package androidx.leanback.preference;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public abstract class LeanbackPreferenceFragment extends BaseLeanbackPreferenceFragment {
    public LeanbackPreferenceFragment() {
        if (Build.VERSION.SDK_INT >= 21) {
            LeanbackPreferenceFragmentTransitionHelperApi21.addTransitions(this);
        }
    }

    @Override // androidx.preference.PreferenceFragment, android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View innerView = super.onCreateView(inflater, container, savedInstanceState);
        LayoutInflater themedInflater = LayoutInflater.from(innerView.getContext());
        View view = themedInflater.inflate(R.layout.leanback_preference_fragment, container, false);
        ViewGroup innerContainer = (ViewGroup) view.findViewById(R.id.main_frame);
        if (innerView != null) {
            innerContainer.addView(innerView);
        }
        return view;
    }

    @Override // androidx.preference.PreferenceFragment, android.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(getPreferenceScreen().getTitle());
    }

    public void setTitle(CharSequence title) {
        View view = getView();
        TextView decorTitle = view == null ? null : (TextView) view.findViewById(R.id.decor_title);
        if (decorTitle != null) {
            decorTitle.setText(title);
        }
    }
}
