package com.amazon.music.explore.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.amazon.music.explore.R;
import com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView;
import com.amazon.music.explore.widgets.views.ErrorView;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;

/* JADX INFO: loaded from: classes4.dex */
public class StageSwipeablePagesFragment extends BaseExploreFragment {
    public BaseButton backButton;
    private final ContextItemSelectionListener contextItemSelectionListener;
    private RelativeLayout fragmentView;
    private final boolean isLyricsSharingEnabled;
    private LinearLayout miniPlayer;
    private int originalStatusBarColor;
    private ProgressBar spinner;
    private StageSwipeablePagesView stageSwipeablePagesView;
    private boolean wasMiniPlayerAdded;
    private Window window;

    public interface ContextItemSelectionListener {
        boolean onContextItemSelected(MenuItem menuItem);
    }

    public StageSwipeablePagesFragment(ContextItemSelectionListener contextItemSelectionListener, boolean z) {
        this.contextItemSelectionListener = contextItemSelectionListener;
        this.isLyricsSharingEnabled = z;
    }

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
        addStageSwipeablePagesView(this.stageSwipeablePagesView);
        return this.fragmentView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        StageSwipeablePagesView stageSwipeablePagesView = this.stageSwipeablePagesView;
        if (stageSwipeablePagesView != null) {
            this.stageSwipeablePagesView.impressionEnded(stageSwipeablePagesView.evaluateEndReason(), false);
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.isLyricsSharingEnabled) {
            this.backButton = (BaseButton) this.fragmentView.findViewById(R.id.ExitXrayFullScreenButton);
            initBackButton(this.styleSheetProvider.getStyleSheet().getValue());
        }
    }

    private void initBackButton(StyleSheet styleSheet) {
        this.backButton.setVisibility(0);
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_action_cancel);
        if (iconBuilder != null && drawable != null) {
            iconBuilder.withIcon(drawable).applyStyle(this.backButton);
        }
        this.backButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.fragment.StageSwipeablePagesFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initBackButton$0(view);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(this.backButton, new OnApplyWindowInsetsListener() { // from class: com.amazon.music.explore.fragment.StageSwipeablePagesFragment$$ExternalSyntheticLambda1
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return this.f$0.lambda$initBackButton$1(view, windowInsetsCompat);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initBackButton$0(View view) {
        if (getActivity() != null) {
            getActivity().onBackPressed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ WindowInsetsCompat lambda$initBackButton$1(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars());
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.backButton.getLayoutParams();
        layoutParams.setMargins(getContext().getResources().getDimensionPixelSize(R.dimen.spacer_20), insets.top + getContext().getResources().getDimensionPixelSize(R.dimen.spacer_8), 0, 0);
        this.backButton.setLayoutParams(layoutParams);
        return WindowInsetsCompat.CONSUMED;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.isLyricsSharingEnabled) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.backButton.getLayoutParams();
            layoutParams.addRule(10);
            this.backButton.setLayoutParams(layoutParams);
        }
    }

    @Override // com.amazon.music.explore.fragment.BaseExploreFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        this.window.getDecorView().setSystemUiVisibility(0);
        this.window.setStatusBarColor(this.originalStatusBarColor);
        getActivity().setRequestedOrientation(-1);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem menuItem) {
        return this.contextItemSelectionListener.onContextItemSelected(menuItem);
    }

    public LinearLayout getMiniPlayer() {
        return this.miniPlayer;
    }

    public void setMiniPlayer(LinearLayout linearLayout) {
        this.miniPlayer = linearLayout;
    }

    public void setSwipeablesPagesView(StageSwipeablePagesView stageSwipeablePagesView) {
        this.stageSwipeablePagesView = stageSwipeablePagesView;
    }

    public void addStageSwipeablePagesView(View view) {
        removeStageSwipeablePagesView();
        boolean z = view instanceof StageSwipeablePagesView;
        if (z || (view instanceof ErrorView)) {
            if (z) {
                setSwipeablesPagesView((StageSwipeablePagesView) view);
            }
            ProgressBar progressBar = this.spinner;
            if (progressBar != null) {
                progressBar.setVisibility(8);
            }
            addViewToFragment(view);
        }
    }

    public void removeStageSwipeablePagesView() {
        RelativeLayout relativeLayout = this.fragmentView;
        if (relativeLayout != null) {
            View childAt = relativeLayout.getChildAt(1);
            if ((childAt instanceof StageSwipeablePagesView) || (childAt instanceof ErrorView)) {
                this.fragmentView.removeView(childAt);
                if (this.wasMiniPlayerAdded) {
                    alignMiniPlayer();
                }
                ProgressBar progressBar = this.spinner;
                if (progressBar != null) {
                    progressBar.setVisibility(0);
                }
            }
        }
    }

    private void alignMiniPlayer() {
        LinearLayout linearLayout = (LinearLayout) this.fragmentView.findViewById(R.id.swipeable_pages_mini_player_view);
        if (linearLayout != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) linearLayout.getLayoutParams();
            if (this.isLyricsSharingEnabled) {
                layoutParams.addRule(12);
            } else {
                layoutParams.addRule(10);
            }
            linearLayout.setLayoutParams(layoutParams);
        }
    }

    private void alignBottomButtonsView() {
        View viewFindViewById = this.fragmentView.findViewById(R.id.raiser_mask_elevator);
        if (viewFindViewById != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) viewFindViewById.getLayoutParams();
            layoutParams.height = getContext().getResources().getDimensionPixelSize(R.dimen.spacer_72);
            viewFindViewById.setLayoutParams(layoutParams);
        }
    }

    private void addViewToFragment(View view) {
        LinearLayout linearLayout;
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        RelativeLayout relativeLayout = this.fragmentView;
        if (relativeLayout != null) {
            relativeLayout.addView(view, 1);
            if (!this.wasMiniPlayerAdded && (linearLayout = this.miniPlayer) != null) {
                this.fragmentView.addView(linearLayout);
                alignMiniPlayer();
                this.wasMiniPlayerAdded = true;
            }
            alignBottomButtonsView();
        }
    }
}
