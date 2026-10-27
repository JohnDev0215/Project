package com.byd.auto_emanual.video.util;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.ViewUtil;
import com.byd.auto_emanual.video.PlayController;
import com.byd.auto_emanual.video.VideoPlayer;

/* JADX INFO: loaded from: classes.dex */
public class ZoomInOutHelper {
    private static String TAG = "ZoomInOutHelper";

    public static void zoom(VideoPlayer videoPlayer, int defOrientation, String zoomTag) {
        int dimensionPixelOffset;
        int dimensionPixelOffset2;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) videoPlayer.getLayoutParams();
        MLog.d(TAG, "zoomTag = " + zoomTag);
        if (PlayController.ZOOM_IN.equals(zoomTag)) {
            ((Activity) videoPlayer.getContext()).getWindow().getDecorView().setSystemUiVisibility(5894);
            setOtherViewsVisibility(videoPlayer, 8);
            layoutParams.width = -1;
            layoutParams.height = -1;
            int dimensionPixelOffset3 = videoPlayer.getResources().getDimensionPixelOffset(R.dimen.video_player_margin_zoom_in);
            layoutParams.bottomMargin = dimensionPixelOffset3;
            layoutParams.topMargin = dimensionPixelOffset3;
        } else {
            ((Activity) videoPlayer.getContext()).getWindow().getDecorView().setSystemUiVisibility(0);
            setOtherViewsVisibility(videoPlayer, 0);
            if (ViewUtil.isPort(videoPlayer.getResources().getConfiguration().orientation)) {
                layoutParams.width = videoPlayer.getResources().getDimensionPixelOffset(R.dimen.video_player_width_port);
            } else {
                layoutParams.width = videoPlayer.getResources().getDimensionPixelOffset(R.dimen.video_player_width);
            }
            layoutParams.height = -2;
            Resources resources = videoPlayer.getResources();
            if (ViewUtil.isPort(resources.getConfiguration().orientation)) {
                dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.video_player_margin_top_port);
                dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.video_player_margin_bottom_port);
            } else {
                dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.video_player_margin_top);
                dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.video_player_margin_bottom);
            }
            layoutParams.topMargin = dimensionPixelOffset;
            layoutParams.bottomMargin = dimensionPixelOffset2;
        }
        videoPlayer.setLayoutParams(layoutParams);
        updateVideoViewSize(videoPlayer, PlayController.ZOOM_OUT);
    }

    public static void updateVideoViewSize(VideoPlayer videoPlayer, String zoomTag) {
        int dimensionPixelOffset;
        int dimensionPixelOffset2;
        Resources resources = videoPlayer.getResources();
        if (zoomTag.equals(videoPlayer.getZoomTag())) {
            if (ViewUtil.isPort(resources.getConfiguration().orientation)) {
                dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.video_player_view_width_port);
                dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.video_player_view_height_port);
            } else {
                dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.video_player_view_width);
                dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.video_player_view_height);
            }
        } else if (ViewUtil.isPort(resources.getConfiguration().orientation)) {
            dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.video_player_view_width_full_port);
            dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.video_player_view_height_full_port);
        } else {
            dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.video_player_view_width_full);
            dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.video_player_view_height_full);
        }
        videoPlayer.updateVideoViewSize(dimensionPixelOffset, dimensionPixelOffset2);
    }

    private static void setOtherViewsVisibility(VideoPlayer view, int visibility) {
        VideoPlayer videoPlayer = (VideoPlayer) ((Activity) view.getContext()).findViewById(R.id.video_player);
        ViewGroup viewGroup = (ViewGroup) ((Activity) view.getContext()).findViewById(R.id.video_player_parent);
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getId() != videoPlayer.getId()) {
                if (childAt instanceof LinearLayout) {
                    childAt.setVisibility(8);
                } else {
                    childAt.setVisibility(visibility);
                }
            }
        }
    }
}
