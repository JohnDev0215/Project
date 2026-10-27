package com.amazon.music.explore.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import com.amazon.music.explore.R;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesStyleSheet;
import com.amazon.music.explore.views.swipeablePages.SwipeablePagesView;
import com.amazon.music.explore.widgets.views.ErrorView;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import com.amazon.music.views.library.styles.StyleSheet;

/* JADX INFO: loaded from: classes4.dex */
public class SwipeablePagesFragment extends BaseExploreFragment {
    private RelativeLayout fragmentView;
    private LinearLayout miniPlayer;
    private int originalStatusBarColor;
    private ProgressBar spinner;
    private boolean wasMiniPlayerAdded;
    private Window window;

    @Override // com.amazon.music.explore.fragment.BaseExploreFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        if (getActivity().getRequestedOrientation() != 5) {
            getActivity().setRequestedOrientation(5);
        }
        Window window = getActivity().getWindow();
        this.window = window;
        this.originalStatusBarColor = window.getStatusBarColor();
        this.window.getDecorView().setSystemUiVisibility(1280);
        this.window.setStatusBarColor(0);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        super.onActivityCreated(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.swipeable_pages_fragment, viewGroup, false);
        this.fragmentView = relativeLayout;
        this.spinner = (ProgressBar) relativeLayout.findViewById(R.id.swipeable_pages_fragment_loading_spinner);
        return this.fragmentView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        removeSwipeablePagesView();
    }

    @Override // com.amazon.music.explore.fragment.BaseExploreFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.window.getDecorView().setSystemUiVisibility(0);
        this.window.setStatusBarColor(this.originalStatusBarColor);
        getActivity().setRequestedOrientation(-1);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        super.onDestroy();
    }

    public LinearLayout getMiniPlayer() {
        return this.miniPlayer;
    }

    public void setMiniPlayer(LinearLayout linearLayout) {
        this.miniPlayer = linearLayout;
    }

    public void addSwipeablePagesView(View view) {
        removeSwipeablePagesView();
        if ((view instanceof SwipeablePagesView) || (view instanceof ErrorView)) {
            applyStyleSheet(view);
            addViewToFragment(view);
        }
    }

    public void removeSwipeablePagesView() {
        RelativeLayout relativeLayout = this.fragmentView;
        if (relativeLayout != null) {
            View childAt = relativeLayout.getChildAt(1);
            boolean z = childAt instanceof SwipeablePagesView;
            if (z || (childAt instanceof ErrorView)) {
                if (z) {
                    ((SwipeablePagesView) childAt).clear();
                }
                this.fragmentView.removeView(childAt);
                this.spinner.setVisibility(0);
            }
        }
    }

    private void addViewToFragment(View view) {
        LinearLayout linearLayout;
        this.fragmentView.addView(view, 1);
        if (this.wasMiniPlayerAdded || (linearLayout = this.miniPlayer) == null) {
            return;
        }
        this.fragmentView.addView(linearLayout);
        ((RelativeLayout.LayoutParams) ((LinearLayout) this.fragmentView.findViewById(R.id.swipeable_pages_mini_player_view)).getLayoutParams()).topMargin = ScreenUtils.getStatusBarHeight(view.getContext());
        this.wasMiniPlayerAdded = true;
    }

    private void applyStyleSheet(final View view) {
        if (view instanceof SwipeablePagesView) {
            this.styleSheetProvider.getStyleSheet().observe((LifecycleOwner) view.getContext(), new Observer() { // from class: com.amazon.music.explore.fragment.SwipeablePagesFragment$$ExternalSyntheticLambda0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.lambda$applyStyleSheet$0(view, (StyleSheet) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$applyStyleSheet$0(View view, StyleSheet styleSheet) {
        ((SwipeablePagesView) view).setStyleSheet(new SwipeablePagesStyleSheet(view.getContext(), styleSheet));
        ProgressBar progressBar = this.spinner;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
    }
}
