package com.amazon.music.explore.views.videoPreview;

import Touch.WidgetsInterface.v1_0.VideoCarouselElement;
import android.os.Bundle;
import android.view.TextureView;
import android.view.ViewGroup;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.amazon.music.explore.managers.ExploreVideoStoryManager;
import com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback;

/* JADX INFO: loaded from: classes4.dex */
public class VideoPreviewItemAdapter extends FragmentStatePagerAdapter {
    private ExploreVideoStoryManager exploreVideoStoryManager;
    private boolean playInitialWhenReady;
    private VideoCarouselElement videoCarouselElement;
    private VideoQueuePlayback videoQueuePlayback;
    private WindowInsetsCompat windowInsets;

    public VideoPreviewItemAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.playInitialWhenReady = true;
    }

    public void setVideoQueuePlayback(ExploreVideoStoryManager exploreVideoStoryManager) {
        this.videoQueuePlayback = exploreVideoStoryManager.getVideoQueuePlayback();
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        VideoPreviewItemFragment videoPreviewItemFragment = new VideoPreviewItemFragment(this.exploreVideoStoryManager);
        Bundle bundle = new Bundle();
        bundle.putInt(VideoPreviewItemFragment.VIDEO_QUEUE_ITEM_POSITION, i);
        videoPreviewItemFragment.setArguments(bundle);
        if (this.playInitialWhenReady && i == 0) {
            videoPreviewItemFragment.setPlayWhenReady(true);
            this.playInitialWhenReady = false;
        }
        return videoPreviewItemFragment;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        VideoCarouselElement videoCarouselElement = this.videoCarouselElement;
        if (videoCarouselElement == null) {
            return 0;
        }
        return videoCarouselElement.items().size() + 2;
    }

    public void setVideoPreviewQueue(ExploreVideoStoryManager exploreVideoStoryManager) {
        this.exploreVideoStoryManager = exploreVideoStoryManager;
        this.videoCarouselElement = exploreVideoStoryManager.getVideoCarouselElement();
        notifyDataSetChanged();
    }

    public void setWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        this.windowInsets = windowInsetsCompat;
    }

    public TextureView getTextureView(int i) {
        VideoPreviewItemFragment videoPreviewItemFragment = (VideoPreviewItemFragment) instantiateItem((ViewGroup) this.exploreVideoStoryManager.getViewPager(), i);
        if (videoPreviewItemFragment != null) {
            return videoPreviewItemFragment.getTextureView();
        }
        return null;
    }
}
