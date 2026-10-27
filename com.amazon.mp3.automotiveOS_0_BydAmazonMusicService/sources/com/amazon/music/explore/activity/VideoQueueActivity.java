package com.amazon.music.explore.activity;

import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import ExternalActionInterface.v1_0.TriggerExternalGenericPlaybackMethod;
import ExternalActionInterface.v1_0.TriggerExternalPlaybackMethod;
import Touch.VideoQueueTemplateInterface.v1_0.VideoQueueElement;
import Touch.VideoQueueTemplateInterface.v1_0.VideoQueueTemplate;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;
import com.amazon.music.explore.R;
import com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback;
import com.amazon.music.explore.views.videoqueue.StackingPageTransformer;
import com.amazon.music.explore.views.videoqueue.VideoQueueItemAdapter;
import com.amazon.music.explore.views.videoqueue.VideoQueueItemFragment;
import com.amazon.music.skyfire.ui.widgets.views.IconButtonView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class VideoQueueActivity extends BaseExploreActivity<VideoQueueTemplate> implements VideoQueuePlayback.PositionCallback, VideoQueuePlayback.VideoScreenCallback, VideoQueuePlayback.ContentStateCallback, VideoQueueItemFragment.CloseCallback {
    private static final int SPINNER_ANIM_DELAY_MILLIS = 130;
    private static final int SPINNER_ANIM_DURATION_MILLIS = 100;
    private static final Logger logger = LoggerFactory.getLogger(VideoQueueActivity.class.getSimpleName());
    private IconButtonView closeButton;
    private LinearLayout progressBarContainer;
    private ProgressBar[] progressBarList;
    private ProgressBar spinner;
    private ViewPager videoPager;
    private VideoQueueElement videoQueue;
    private VideoQueueItemAdapter videoQueueItemAdapter;
    public VideoQueuePlayback videoQueuePlayback;

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void createAndBindTemplate(String str, VideoQueueTemplate videoQueueTemplate) {
        bindTemplate(str, videoQueueTemplate);
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, com.amazon.music.explore.skyfire.containers.StandaloneContainerActivity
    public void bindTemplate(String str, VideoQueueTemplate videoQueueTemplate) {
        super.bindTemplate(str, videoQueueTemplate);
        VideoQueueElement videoQueueElementVideoQueue = videoQueueTemplate.videoQueue();
        this.videoQueue = videoQueueElementVideoQueue;
        this.videoQueuePlayback.setVideoQueue(videoQueueElementVideoQueue);
        this.videoQueueItemAdapter.setVideoQueue(this.videoQueue);
        initProgressBar(this.videoQueue.videoItems().size());
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.skyFireApplication.registerOwner(this.ownerId);
        setContentView(R.layout.video_queue_template_view);
        this.playbackProvider.pause();
        this.spinner = (ProgressBar) findViewById(R.id.video_playback_loading_spinner);
        this.videoQueuePlayback = new VideoQueuePlayback(this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.video_queue_pager);
        this.videoPager = viewPager;
        viewPager.setPageTransformer(false, new StackingPageTransformer());
        this.videoPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.amazon.music.explore.activity.VideoQueueActivity.1
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                VideoQueueActivity.logger.info("onPageSelected {}", Integer.valueOf(i));
                VideoQueueActivity.this.videoQueuePlayback.playQueuePosition(i);
                if (i > 0) {
                    int i2 = i - 1;
                    ((ProgressBar) VideoQueueActivity.this.progressBarContainer.getChildAt(i2)).setProgress(((ProgressBar) VideoQueueActivity.this.progressBarContainer.getChildAt(i2)).getMax());
                }
                if (i < VideoQueueActivity.this.videoQueue.videoItems().size() - 1) {
                    ((ProgressBar) VideoQueueActivity.this.progressBarContainer.getChildAt(i + 1)).setProgress(0);
                }
            }
        });
        VideoQueueItemAdapter videoQueueItemAdapter = new VideoQueueItemAdapter(getSupportFragmentManager());
        this.videoQueueItemAdapter = videoQueueItemAdapter;
        videoQueueItemAdapter.setVideoQueuePlayback(this.videoQueuePlayback);
        ViewCompat.setOnApplyWindowInsetsListener(this.videoPager, new OnApplyWindowInsetsListener() { // from class: com.amazon.music.explore.activity.VideoQueueActivity$$ExternalSyntheticLambda1
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return VideoQueueActivity.lambda$onCreate$0(view, windowInsetsCompat);
            }
        });
        this.videoPager.setAdapter(this.videoQueueItemAdapter);
        this.closeButton = (IconButtonView) findViewById(R.id.video_queue_close_button);
        this.progressBarContainer = (LinearLayout) findViewById(R.id.video_queue_progress_bar_container);
        bindCloseButton();
        handleNotch();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ WindowInsetsCompat lambda$onCreate$0(View view, WindowInsetsCompat windowInsetsCompat) {
        ((VideoQueueItemAdapter) ((ViewPager) view).getAdapter()).setWindowInsets(windowInsetsCompat);
        return windowInsetsCompat.consumeSystemWindowInsets();
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.videoQueuePlayback.addPositionCallback(this);
        this.videoQueuePlayback.addContentStateCallback(this);
        this.videoQueuePlayback.setVideoScreenCallback(this);
        this.videoQueuePlayback.play();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.videoQueuePlayback.removePositionCallback(this);
        this.videoQueuePlayback.removeContentStateCallback(this);
        this.videoQueuePlayback.removeVideoScreenCallback();
        this.videoQueuePlayback.pause();
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.skyFireApplication.unregisterOwner(this.ownerId);
        this.videoQueuePlayback.shutdown();
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.VideoScreenCallback
    public TextureView getTextureView(int i) {
        return this.videoQueueItemAdapter.getTextureView(i);
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.PositionCallback
    public void onPlaybackPositionChanged(int i, long j) {
        if (this.videoPager.getCurrentItem() == i) {
            ((ProgressBar) this.progressBarContainer.getChildAt(i)).setProgress((int) j);
        }
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.PositionCallback
    public void onPlaybackFinished() {
        if (this.videoPager.getCurrentItem() < this.videoQueue.videoItems().size() - 1) {
            this.videoPager.arrowScroll(66);
        } else {
            close();
        }
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.ContentStateCallback
    public void onContentLoading() {
        scheduleShowSpinner();
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.ContentStateCallback
    public void onContentReady() {
        hideSpinner();
        bindProgressBar(this.videoQueuePlayback.getDuration());
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        close();
        return false;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        close();
    }

    @Override // com.amazon.music.explore.views.videoqueue.VideoQueueItemFragment.CloseCallback
    public void close() {
        finish();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity
    protected void handleExternalAction(TriggerExternalActionMethod triggerExternalActionMethod) {
        String strName = triggerExternalActionMethod.name();
        strName.hashCode();
        switch (strName) {
            case "OPEN_PLAYLIST_DETAIL":
            case "OPEN_ALBUM_DETAIL":
            case "OPEN_ARTIST_DETAIL":
            case "OPEN_PODCAST_LINK":
                close();
                break;
        }
        super.handleExternalAction(triggerExternalActionMethod);
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity
    protected void handleExternalPlayback(TriggerExternalPlaybackMethod triggerExternalPlaybackMethod) {
        super.handleExternalPlayback(triggerExternalPlaybackMethod);
        close();
    }

    @Override // com.amazon.music.explore.activity.BaseExploreActivity
    protected void handleGenericPlaybackMethod(TriggerExternalGenericPlaybackMethod triggerExternalGenericPlaybackMethod) {
        super.handleGenericPlaybackMethod(triggerExternalGenericPlaybackMethod);
        close();
    }

    private void bindCloseButton() {
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.explore.activity.VideoQueueActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$bindCloseButton$1(view);
            }
        });
        this.closeButton.setImage("https://m.media-amazon.com/images/G/01/music-explore/ic_action_cancel.png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindCloseButton$1(View view) {
        close();
    }

    private void initProgressBar(int i) {
        this.progressBarList = new ProgressBar[i];
        for (int i2 = 0; i2 < i; i2++) {
            this.progressBarList[i2] = new ProgressBar(this, null, 0, R.style.VideoQueueProgressBar);
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.video_queue_progress_bar_margin_in_between);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((this.progressBarContainer.getWidth() - ((i - 1) * dimensionPixelSize)) / i, -1);
            if (i2 != 0) {
                layoutParams.leftMargin = dimensionPixelSize;
            }
            this.progressBarContainer.addView(this.progressBarList[i2], layoutParams);
        }
    }

    private void bindProgressBar(int i) {
        ProgressBar progressBar = (ProgressBar) this.progressBarContainer.getChildAt(this.videoPager.getCurrentItem());
        progressBar.setMax(i);
        progressBar.setProgress(0);
    }

    private void handleNotch() {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        getWindow().getAttributes().layoutInDisplayCutoutMode = 1;
    }

    private void scheduleShowSpinner() {
        this.spinner.animate().alpha(1.0f).setDuration(100L).start();
    }

    private void hideSpinner() {
        this.spinner.animate().alpha(0.0f).setStartDelay(130L).setDuration(100L).start();
    }
}
