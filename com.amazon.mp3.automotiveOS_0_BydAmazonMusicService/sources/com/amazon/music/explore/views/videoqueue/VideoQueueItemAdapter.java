package com.amazon.music.explore.views.videoqueue;

import Touch.VideoQueueTemplateInterface.v1_0.VideoQueueElement;
import android.os.Bundle;
import android.view.TextureView;
import android.view.ViewGroup;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.amazon.music.explore.Mappers;
import com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback;
import com.fasterxml.jackson.core.JsonProcessingException;

/* JADX INFO: loaded from: classes4.dex */
public class VideoQueueItemAdapter extends FragmentStatePagerAdapter {
    private boolean playInitialWhenReady;
    private VideoQueueElement videoQueue;
    private VideoQueuePlayback videoQueuePlayback;
    private WindowInsetsCompat windowInsets;

    public VideoQueueItemAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.playInitialWhenReady = true;
    }

    public void setVideoQueuePlayback(VideoQueuePlayback videoQueuePlayback) {
        this.videoQueuePlayback = videoQueuePlayback;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        VideoQueueItemFragment videoQueueItemFragment = new VideoQueueItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(VideoQueueItemFragment.VIDEO_QUEUE_ITEM_POSITION, i);
        WindowInsetsCompat windowInsetsCompat = this.windowInsets;
        if (windowInsetsCompat != null) {
            bundle.putInt(VideoQueueItemFragment.INSET_LEFT, windowInsetsCompat.getSystemWindowInsetLeft());
            bundle.putInt(VideoQueueItemFragment.INSET_TOP, this.windowInsets.getSystemWindowInsetTop());
            bundle.putInt(VideoQueueItemFragment.INSET_RIGHT, this.windowInsets.getSystemWindowInsetRight());
            bundle.putInt(VideoQueueItemFragment.INSET_BOTTOM, this.windowInsets.getSystemWindowInsetBottom());
        }
        try {
            bundle.putString(VideoQueueItemFragment.VIDEO_QUEUE_ITEM_ELEMENT, Mappers.mapper().writeValueAsString(this.videoQueue.videoItems().get(i)));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        videoQueueItemFragment.setArguments(bundle);
        if (this.playInitialWhenReady && i == 0) {
            videoQueueItemFragment.setPlayWhenReady(true);
            this.playInitialWhenReady = false;
        }
        return videoQueueItemFragment;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        VideoQueueElement videoQueueElement = this.videoQueue;
        if (videoQueueElement == null) {
            return 0;
        }
        return videoQueueElement.videoItems().size();
    }

    public void setVideoQueue(VideoQueueElement videoQueueElement) {
        this.videoQueue = videoQueueElement;
        notifyDataSetChanged();
    }

    public void setWindowInsets(WindowInsetsCompat windowInsetsCompat) {
        this.windowInsets = windowInsetsCompat;
    }

    public TextureView getTextureView(int i) {
        VideoQueueItemFragment videoQueueItemFragment = (VideoQueueItemFragment) instantiateItem((ViewGroup) null, i);
        if (videoQueueItemFragment != null) {
            return videoQueueItemFragment.getTextureView();
        }
        return null;
    }
}
