package com.amazon.music.explore.fragment;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.PlayableContent;
import ExternalActionInterface.v1_0.ShowUpsellMethod;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod;
import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.amazon.music.explore.R;
import com.amazon.music.explore.picasso.BlurTransformation;
import com.amazon.music.explore.views.playback.IntroAnimations;
import com.amazon.music.explore.views.playback.PlaybackView;
import com.amazon.music.explore.widgets.views.ErrorView;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import com.squareup.picasso.Picasso;

/* JADX INFO: loaded from: classes4.dex */
public class PlaybackFragment extends BaseExploreFragment<PlaybackTemplate> {
    private static int IMMERSIVE_MODE_FLAGS = 5894;
    private static String loadingImage;
    private ErrorView errorView;
    private FrameLayout fragmentView;
    private boolean isStopped;
    private ImageView loadingBackground;
    private PlaybackView playbackView;
    private ProgressBar spinner;
    private boolean willPlayAdditionalContent;

    @Override // com.amazon.music.explore.fragment.BaseExploreFragment, com.amazon.music.explore.fragment.StandaloneContainerFragment
    public void onBindTemplate(String str, PlaybackTemplate playbackTemplate) {
        PlayableContent playableContent = playbackTemplate.playableContent();
        PlaybackView playbackView = new PlaybackView(getContext(), str, getMethodsDispatcher(), playbackTemplate, this, this.playbackProvider, this.animationProvider, this.metricsProvider);
        this.playbackView = playbackView;
        this.fragmentView.addView(playbackView);
        hideLoadingViews();
        this.playbackProvider.startPlaybackTemplate(getActivity(), this.playbackView, playableContent);
        super.onBindTemplate(str, playbackTemplate);
    }

    @Override // com.amazon.music.explore.fragment.BaseExploreFragment, com.amazon.music.explore.fragment.StandaloneContainerFragment
    public void onHandleTemplateMethod(String str, Method method) {
        if (method instanceof TriggerExternalActionMethod) {
            onHandleExternalAction((TriggerExternalActionMethod) method);
            return;
        }
        if (method instanceof TriggerExternalGenericPlaybackMethod) {
            PlaybackView playbackView = this.playbackView;
            if (playbackView == null) {
                this.willPlayAdditionalContent = true;
                removePlaybackFragment();
                this.playbackProvider.play(((TriggerExternalGenericPlaybackMethod) method).content().get(0), null);
                return;
            } else {
                if (playbackView.hasPlaybackStarted()) {
                    this.willPlayAdditionalContent = true;
                    this.playbackView.dismissAndPlay((TriggerExternalGenericPlaybackMethod) method);
                    return;
                }
                return;
            }
        }
        if (method instanceof ShowUpsellMethod) {
            this.willPlayAdditionalContent = true;
            removePlaybackFragment();
            ShowUpsellMethod showUpsellMethod = (ShowUpsellMethod) method;
            this.upsellProvider.showUpsell(getActivity(), showUpsellMethod.playableContent(), showUpsellMethod.name());
        }
    }

    private void removePlaybackFragment() {
        FrameLayout frameLayout = this.fragmentView;
        if (frameLayout == null || frameLayout.getParent() == null || !(this.fragmentView.getParent() instanceof ViewGroup)) {
            return;
        }
        ((ViewGroup) this.fragmentView.getParent()).removeView(this.fragmentView);
    }

    @Override // com.amazon.music.explore.fragment.BaseExploreFragment
    protected void onHandleExternalAction(TriggerExternalActionMethod triggerExternalActionMethod) {
        String strName = triggerExternalActionMethod.name();
        strName.hashCode();
        switch (strName) {
            case "OPEN_ARTIST_DETAIL":
                this.playbackView.setWillBeRemoved(true);
                this.playbackProvider.stopPlaybackAndClearQueue();
                break;
            case "OPEN_SHARE_LINK":
                if (this.playbackView.hasPlaybackStarted()) {
                    this.playbackView.pause();
                    break;
                } else {
                    return;
                }
                break;
            case "DISPLAY_ERROR_SCREEN":
                ErrorView errorView = new ErrorView(getContext());
                this.errorView = errorView;
                errorView.setPrimaryText(triggerExternalActionMethod.context().get(Contexts.DisplayErrorScreen.primaryText));
                this.errorView.setButtonText(triggerExternalActionMethod.context().get(Contexts.DisplayErrorScreen.buttonText));
                this.errorView.setButtonClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.fragment.PlaybackFragment$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        this.f$0.lambda$onHandleExternalAction$0(view);
                    }
                });
                this.fragmentView.addView(this.errorView);
                hideLoadingViews();
                break;
        }
        super.onHandleExternalAction(triggerExternalActionMethod);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onHandleExternalAction$0(View view) {
        getActivity().finish();
    }

    @Override // com.amazon.music.explore.fragment.BaseExploreFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        updateSystemUIVisibility();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        FrameLayout frameLayout = (FrameLayout) layoutInflater.inflate(R.layout.playback_fragment, viewGroup, false);
        this.fragmentView = frameLayout;
        this.spinner = (ProgressBar) frameLayout.findViewById(R.id.playback_fragment_loading_spinner);
        ImageView imageView = (ImageView) this.fragmentView.findViewById(R.id.playback_fragment_loading_background);
        this.loadingBackground = imageView;
        if (loadingImage != null) {
            applyLoadingImage(imageView);
        }
        return this.fragmentView;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.playbackView != null) {
            updateSystemUIVisibility();
            this.playbackView.onRotate();
        }
    }

    @Override // com.amazon.music.explore.fragment.BaseExploreFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        PlaybackView playbackView = this.playbackView;
        if (playbackView != null) {
            playbackView.clear();
        }
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.isStopped = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.isStopped = false;
        PlaybackView playbackView = this.playbackView;
        if (playbackView == null || !playbackView.hasPlaybackFinished()) {
            return;
        }
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.amazon.music.explore.fragment.PlaybackFragment$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onStart$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onStart$1() {
        getActivity().onBackPressed();
    }

    public static void setLoadingImage(String str) {
        loadingImage = str;
    }

    public void applyLoadingImage(ImageView imageView) {
        IntroAnimations.resizeBackgroundImage(imageView);
        Picasso.get().load(loadingImage).transform(new BlurTransformation(imageView.getContext(), 25.0f)).into(imageView);
    }

    public boolean isStopped() {
        return this.isStopped;
    }

    public void handleBackButtonClicked() {
        PlaybackView playbackView = this.playbackView;
        if (playbackView != null) {
            playbackView.close();
        } else if (this.errorView != null) {
            getActivity().finish();
        }
    }

    public boolean willPlayAdditionalContent() {
        return this.willPlayAdditionalContent;
    }

    private void hideLoadingViews() {
        this.spinner.setVisibility(8);
        this.loadingBackground.setVisibility(8);
    }

    private void updateSystemUIVisibility() {
        if (ScreenUtils.isLandscape(getContext())) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(IMMERSIVE_MODE_FLAGS);
        } else {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(0);
        }
    }
}
