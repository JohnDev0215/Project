package com.neusoft.na.ehp.dbu;

import android.app.UiModeManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.neusoft.na.ehp.R;

/* JADX INFO: loaded from: classes.dex */
public class ProgressFragment extends Fragment {
    private static final String ARG_PARAM = "value";
    private static final String TAG = "NeuEhpProgress";
    private int mValue;
    private ProgressBar progressBar;
    private TextView tv_content;
    private TextView tv_progress;
    private TextView tv_title;

    public static ProgressFragment newInstance(int i) {
        ProgressFragment progressFragment = new ProgressFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PARAM, i);
        progressFragment.setArguments(bundle);
        return progressFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mValue = getArguments().getInt(ARG_PARAM);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_progress, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress);
        this.tv_progress = (TextView) view.findViewById(R.id.tv_progress);
        this.tv_title = (TextView) view.findViewById(R.id.tv_title);
        this.tv_content = (TextView) view.findViewById(R.id.tv_content);
        updateProgress(this.mValue);
        changeTheme(2 == ((UiModeManager) getActivity().getSystemService("uimode")).getNightMode());
    }

    public void updateProgress(int i) {
        Log.i(TAG, "updateProgress: " + i);
        if (i >= 100) {
            i = 100;
        } else if (i < 0) {
            i = 0;
        }
        ProgressBar progressBar = this.progressBar;
        if (progressBar != null) {
            progressBar.setProgress(i);
        }
        TextView textView = this.tv_progress;
        if (textView != null) {
            textView.setText(i + "%");
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.i(TAG, "ProgressFragment onConfigurationChanged: " + configuration.uiMode);
        changeTheme((configuration.uiMode & 48) == 32);
    }

    private void changeTheme(boolean z) {
        TextView textView = this.tv_title;
        FragmentActivity activity = getActivity();
        textView.setTextColor(z ? activity.getColor(R.color.pop_text_color_black_night) : activity.getColor(R.color.pop_text_color_black));
        TextView textView2 = this.tv_content;
        FragmentActivity activity2 = getActivity();
        textView2.setTextColor(z ? activity2.getColor(R.color.pop_text_color_black_night) : activity2.getColor(R.color.pop_text_color_black));
        this.tv_progress.setTextColor(z ? getActivity().getColor(R.color.pop_text_color_black_night) : getActivity().getColor(R.color.pop_text_color_black));
    }
}
