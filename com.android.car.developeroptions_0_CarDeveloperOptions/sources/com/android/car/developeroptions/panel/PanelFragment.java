package com.android.car.developeroptions.panel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.overlay.FeatureFactory;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.google.android.setupdesign.DividerItemDecoration;

/* JADX INFO: loaded from: classes.dex */
public class PanelFragment extends Fragment {

    @VisibleForTesting
    PanelSlicesAdapter mAdapter;
    private Button mDoneButton;
    private MetricsFeatureProvider mMetricsProvider;
    private PanelContent mPanel;
    private RecyclerView mPanelSlices;
    private Button mSeeMoreButton;
    private TextView mTitleView;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FragmentActivity activity = getActivity();
        View viewInflate = layoutInflater.inflate(R.layout.panel_layout, viewGroup, false);
        this.mPanelSlices = (RecyclerView) viewInflate.findViewById(R.id.panel_parent_layout);
        this.mSeeMoreButton = (Button) viewInflate.findViewById(R.id.see_more);
        this.mDoneButton = (Button) viewInflate.findViewById(R.id.done);
        this.mTitleView = (TextView) viewInflate.findViewById(R.id.panel_title);
        Bundle arguments = getArguments();
        String string = arguments.getString("PANEL_TYPE_ARGUMENT");
        String string2 = arguments.getString("PANEL_CALLING_PACKAGE_NAME");
        this.mPanel = FeatureFactory.getFactory(activity).getPanelFeatureProvider().getPanel(activity, string, arguments.getString("PANEL_MEDIA_PACKAGE_NAME"));
        MetricsFeatureProvider metricsFeatureProvider = FeatureFactory.getFactory(activity).getMetricsFeatureProvider();
        this.mMetricsProvider = metricsFeatureProvider;
        metricsFeatureProvider.action(0, 1, this.mPanel.getMetricsCategory(), string2, 0);
        this.mAdapter = new PanelSlicesAdapter(this, this.mPanel);
        this.mPanelSlices.setHasFixedSize(true);
        this.mPanelSlices.setLayoutManager(new LinearLayoutManager(activity));
        this.mPanelSlices.setAdapter(this.mAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity());
        dividerItemDecoration.setDividerCondition(1);
        this.mPanelSlices.addItemDecoration(dividerItemDecoration);
        this.mTitleView.setText(this.mPanel.getTitle());
        this.mSeeMoreButton.setOnClickListener(getSeeMoreListener());
        this.mDoneButton.setOnClickListener(getCloseListener());
        if (this.mPanel.getSeeMoreIntent() == null) {
            this.mSeeMoreButton.setVisibility(8);
        }
        return viewInflate;
    }

    @VisibleForTesting
    View.OnClickListener getSeeMoreListener() {
        return new View.OnClickListener() { // from class: com.android.car.developeroptions.panel.-$$Lambda$PanelFragment$ZJ4NMe_l7ALu6aljuhj-xDFLphY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getSeeMoreListener$0$PanelFragment(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getSeeMoreListener$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$getSeeMoreListener$0$PanelFragment(View view) {
        this.mMetricsProvider.action(0, 2, this.mPanel.getMetricsCategory(), "see_more", 0);
        FragmentActivity activity = getActivity();
        activity.startActivityForResult(this.mPanel.getSeeMoreIntent(), 0);
        activity.finish();
    }

    @VisibleForTesting
    View.OnClickListener getCloseListener() {
        return new View.OnClickListener() { // from class: com.android.car.developeroptions.panel.-$$Lambda$PanelFragment$zdTdVlUm6h78dSYJRgvH6vvonNg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$getCloseListener$1$PanelFragment(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getCloseListener$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$getCloseListener$1$PanelFragment(View view) {
        this.mMetricsProvider.action(0, 2, this.mPanel.getMetricsCategory(), "done", 0);
        getActivity().finish();
    }
}
