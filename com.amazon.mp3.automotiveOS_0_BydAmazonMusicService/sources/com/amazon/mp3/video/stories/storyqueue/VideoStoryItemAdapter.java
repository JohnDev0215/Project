package com.amazon.mp3.video.stories.storyqueue;

import Touch.PlaybackTemplateInterface.v1_0.PlaybackTemplate;
import android.os.Bundle;
import android.view.TextureView;
import android.view.ViewGroup;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.amazon.mp3.video.stories.Mappers;
import com.amazon.mp3.video.stories.VideoStoryFragment;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VideoStoryItemAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010$\u001a\u00020#\u0012\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b%\u0010&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0002H\u0016J\u0014\u0010\u000b\u001a\u00020\n2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0010\u0010\u000e\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u000fJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0003\u001a\u00020\u0002R\u0017\u0010\u0017\u001a\u00020\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\r\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u001eR\u0016\u0010 \u001a\u00020\u001f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b \u0010!R\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\"¨\u0006'"}, d2 = {"Lcom/amazon/mp3/video/stories/storyqueue/VideoStoryItemAdapter;", "Landroidx/fragment/app/FragmentStatePagerAdapter;", "", "position", "Landroidx/fragment/app/Fragment;", "getItem", "getCount", "", "LTouch/PlaybackTemplateInterface/v1_0/PlaybackTemplate;", "playbackTemplates", "", "setVideoQueue", "Landroidx/core/view/WindowInsetsCompat;", "windowInsets", "setWindowInsets", "Landroid/view/ViewGroup;", "container", "setContainer", "Lcom/amazon/mp3/video/stories/storyqueue/VideoStoryItemFragment;", "getFragment", "Landroid/view/TextureView;", "getTextureView", "Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "parentFragment", "Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "getParentFragment", "()Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "", "videoQueue", "Ljava/util/List;", "Landroidx/core/view/WindowInsetsCompat;", "", "playInitialWhenReady", "Z", "Landroid/view/ViewGroup;", "Landroidx/fragment/app/FragmentManager;", "fragmentManager", "<init>", "(Landroidx/fragment/app/FragmentManager;Lcom/amazon/mp3/video/stories/VideoStoryFragment;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryItemAdapter extends FragmentStatePagerAdapter {
    private ViewGroup container;
    private final VideoStoryFragment parentFragment;
    private boolean playInitialWhenReady;
    private List<PlaybackTemplate> videoQueue;
    private WindowInsetsCompat windowInsets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VideoStoryItemAdapter(FragmentManager fragmentManager, VideoStoryFragment parentFragment) {
        super(fragmentManager);
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(parentFragment, "parentFragment");
        this.parentFragment = parentFragment;
        this.videoQueue = new ArrayList();
        this.playInitialWhenReady = true;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int position) {
        VideoStoryItemFragment videoStoryItemFragment = new VideoStoryItemFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("VIDEO_STORY_ITEM_POSITION", position);
        WindowInsetsCompat windowInsetsCompat = this.windowInsets;
        if (windowInsetsCompat != null) {
            bundle.putInt("INSET_LEFT", windowInsetsCompat.getSystemWindowInsetLeft());
            bundle.putInt("INSET_TOP", windowInsetsCompat.getSystemWindowInsetTop());
            bundle.putInt("INSET_RIGHT", windowInsetsCompat.getSystemWindowInsetRight());
            bundle.putInt("INSET_BOTTOM", windowInsetsCompat.getSystemWindowInsetBottom());
        }
        try {
            if (this.videoQueue.size() == 0) {
                bundle.putString("VIDEO_STORY_ITEM_ELEMENT", null);
            } else {
                bundle.putString("VIDEO_STORY_ITEM_ELEMENT", Mappers.INSTANCE.mapper().writeValueAsString(this.videoQueue.get(position)));
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        videoStoryItemFragment.setArguments(bundle);
        if (this.playInitialWhenReady) {
            videoStoryItemFragment.setPlayWhenReady(true);
            this.playInitialWhenReady = false;
        }
        return videoStoryItemFragment;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.videoQueue.size();
    }

    public final void setVideoQueue(List<? extends PlaybackTemplate> playbackTemplates) {
        Intrinsics.checkNotNullParameter(playbackTemplates, "playbackTemplates");
        this.videoQueue.addAll(playbackTemplates);
        notifyDataSetChanged();
    }

    public final void setWindowInsets(WindowInsetsCompat windowInsets) {
        this.windowInsets = windowInsets;
    }

    public final void setContainer(ViewGroup container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.container = container;
    }

    public final VideoStoryItemFragment getFragment(int position) {
        ViewGroup viewGroup = this.container;
        if (viewGroup == null) {
            return null;
        }
        return (VideoStoryItemFragment) instantiateItem(viewGroup, position);
    }

    public final TextureView getTextureView(int position) {
        ViewGroup viewGroup = this.container;
        if (viewGroup == null) {
            return null;
        }
        return ((VideoStoryItemFragment) instantiateItem(viewGroup, position)).getTextureView();
    }
}
