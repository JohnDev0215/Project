package com.amazon.music.explore.views.videoPreview;

import ExternalActionInterface.v1_0.VideoStoryContent;
import Touch.WidgetsInterface.v1_0.VideoCarouselElement;
import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import com.amazon.music.explore.R;
import com.amazon.music.explore.managers.ExploreVideoStoryManager;
import com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback;
import com.amazon.music.skyfire.ui.utils.ScreenUtils;
import com.squareup.picasso.Picasso;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public class VideoPreviewItemFragment extends Fragment implements VideoQueuePlayback.PositionCallback {
    public static String VIDEO_QUEUE_ITEM_POSITION = "VIDEO_QUEUE_ITEM_POSITION";
    private CoachMarkCircleView coachMarkCircleView;
    private final ExploreVideoStoryManager exploreVideoStoryManager;
    private int marginLeft;
    private int marginTop;
    private ImageView previewImage;
    private int queuePosition;
    private float screenWidth;
    private TextureView textureView;
    private VideoCarouselElement videoCarouselElement;
    private VideoQueuePlayback videoQueuePlayback;
    private final double EXPANDED_LARGE_CIRCLE_RATIO = 0.13526d;
    private boolean playWhenReady = false;

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.PositionCallback
    public void onPlaybackFinished() {
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.PositionCallback
    public void onPlaybackPositionChanged(int i, long j) {
    }

    public VideoPreviewItemFragment(ExploreVideoStoryManager exploreVideoStoryManager) {
        this.exploreVideoStoryManager = exploreVideoStoryManager;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.video_preview_layout, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    @SuppressLint({"ClickableViewAccessibility"})
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.textureView = (TextureView) view.findViewById(R.id.video_queue_texture_view);
        this.previewImage = (ImageView) view.findViewById(R.id.video_queue_preview_image);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.queuePosition = arguments.getInt(VIDEO_QUEUE_ITEM_POSITION);
        }
        this.videoQueuePlayback = this.exploreVideoStoryManager.getVideoQueuePlayback();
        this.videoCarouselElement = this.exploreVideoStoryManager.getVideoCarouselElement();
        if (this.playWhenReady) {
            this.playWhenReady = false;
            this.videoQueuePlayback.playPreviewQueuePosition(this.queuePosition);
        }
        this.textureView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.views.videoPreview.VideoPreviewItemFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$onViewCreated$0(view2);
            }
        });
        bindPreviewImage();
        if (this.queuePosition >= 2) {
            CoachMarkCircleView coachMarkCircleView = (CoachMarkCircleView) view.findViewById(R.id.coach_mark_circle_view);
            this.coachMarkCircleView = coachMarkCircleView;
            coachMarkCircleView.setVisibility(0);
            float screenWidth = ScreenUtils.getScreenWidth(getActivity());
            this.screenWidth = screenWidth;
            this.marginLeft = ((int) (((double) screenWidth) - (((double) screenWidth) * 0.13526d))) / 2;
            this.marginTop = (getResources().getDimensionPixelSize(R.dimen.spacer_epic) * 2) + getResources().getDimensionPixelSize(R.dimen.spacer_giant);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.coachMarkCircleView.getLayoutParams();
            layoutParams.setMargins(this.marginLeft, this.marginTop, 0, 0);
            this.coachMarkCircleView.setLayoutParams(layoutParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onViewCreated$0(View view) {
        this.exploreVideoStoryManager.openVideoStory(this.queuePosition);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.videoQueuePlayback.addPositionCallback(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.videoQueuePlayback.removePositionCallback(this);
    }

    public void setPlayWhenReady(boolean z) {
        this.playWhenReady = z;
    }

    public TextureView getTextureView() {
        return this.textureView;
    }

    private void bindPreviewImage() {
        String strIntroUri;
        int i = this.queuePosition;
        if (i == 0) {
            strIntroUri = this.videoCarouselElement.artworkIntro();
        } else if (i == 1) {
            strIntroUri = this.videoCarouselElement.artwork();
        } else {
            strIntroUri = ((VideoStoryContent) this.videoCarouselElement.items().get(this.queuePosition - 2).content()).introUri();
        }
        Picasso.get().load(strIntroUri).fit().centerCrop().placeholder(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK)).into(this.previewImage);
    }
}
