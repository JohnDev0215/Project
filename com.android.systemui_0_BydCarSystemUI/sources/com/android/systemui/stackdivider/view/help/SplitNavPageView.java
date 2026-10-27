package com.android.systemui.stackdivider.view.help;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.android.systemui.R;
import com.byd.card.utils.AppUtils;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class SplitNavPageView extends RelativeLayout implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private static final int COMPLETION_VIDEO = 200;
    private static final int HIDE_FIRST_FRAME = 202;
    private static final int START_VIDEO = 201;
    private RoundImageView ivFirstFrame;
    private int mExplainTextRes;
    private int mFirstBitmapResId;
    private SplitNavPageViewHandler mHandler;
    private int mHeadTextRes;
    private String mVideoUri;
    private VideoView mVideoView;
    private RelativeLayout mVideoViewContainer;

    public SplitNavPageView(Context context) {
        super(context);
    }

    public SplitNavPageView(Context context, int headTextRes, int explainTextRes, String str, int firstBitmapResId) {
        super(context);
        this.mHeadTextRes = headTextRes;
        this.mExplainTextRes = explainTextRes;
        this.mVideoUri = str;
        this.mFirstBitmapResId = firstBitmapResId;
        this.mHandler = new SplitNavPageViewHandler(this);
        initView();
    }

    private void initView() {
        removeAllViews();
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.split_screen_help, (ViewGroup) null);
        addView(viewInflate);
        TextView textView = (TextView) viewInflate.findViewById(R.id.tvTitle);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.tvSubTitle);
        this.mVideoViewContainer = (RelativeLayout) viewInflate.findViewById(R.id.videoViewContainer);
        VideoView videoView = (VideoView) viewInflate.findViewById(R.id.video_view);
        this.mVideoView = videoView;
        videoView.setAudioFocusRequest(0);
        this.mVideoView.setOnPreparedListener(this);
        this.mVideoView.setOnCompletionListener(this);
        this.mVideoView.setOnErrorListener(this);
        this.mVideoView.setClipToOutline(true);
        textView.setText(this.mHeadTextRes);
        textView2.setText(this.mExplainTextRes);
        RoundImageView roundImageView = (RoundImageView) viewInflate.findViewById(R.id.ivFirstFrame);
        this.ivFirstFrame = roundImageView;
        roundImageView.setImageResource(this.mFirstBitmapResId);
        if (AppUtils.isRtl(getContext())) {
            this.mVideoView.setRotationY(180.0f);
            this.ivFirstFrame.setRotationY(180.0f);
            textView.setRotationY(180.0f);
            textView2.setRotationY(180.0f);
        }
    }

    public void startVideo() {
        BydLogHelper.logD("startVideo");
        this.mHandler.removeMessages(200);
        this.mHandler.removeMessages(201);
        this.mHandler.sendEmptyMessage(201);
        this.mHandler.removeMessages(202);
    }

    public void stopVideo() {
        BydLogHelper.logD("stopVideo");
        this.mVideoView.suspend();
        this.mHandler.removeMessages(200);
        this.mHandler.removeMessages(201);
        this.mHandler.removeMessages(202);
        this.ivFirstFrame.setVisibility(0);
    }

    public void release() {
        BydLogHelper.logD("release");
        stopVideo();
        this.mVideoView.stopPlayback();
        this.mVideoViewContainer.removeAllViews();
        this.mVideoView.setOnPreparedListener(null);
        this.mVideoView.setOnCompletionListener(null);
        this.mVideoView.setOnErrorListener(null);
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        BydLogHelper.logD("onPrepared");
        this.mHandler.removeMessages(202);
        this.mHandler.sendEmptyMessageDelayed(202, 220L);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        BydLogHelper.logD("onError what = " + what + ", extra = " + extra);
        return true;
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        BydLogHelper.logD("onCompletion");
        this.mHandler.sendEmptyMessage(200);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCompletionVideo() {
        this.mVideoView.requestFocus();
        BydLogHelper.logD("doCompletionVideo");
        this.mVideoView.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doStartVideo() {
        try {
            this.ivFirstFrame.setVisibility(0);
            this.mVideoView.setVideoURI(Uri.parse(this.mVideoUri));
            this.mVideoView.requestFocus();
            BydLogHelper.logD("doStartVideo");
            this.mVideoView.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHideFirstFrame() {
        BydLogHelper.logD("doHideFirstFrame");
        this.ivFirstFrame.setVisibility(8);
    }

    private static class SplitNavPageViewHandler extends Handler {
        WeakReference<SplitNavPageView> mSplitNavPageView;

        public SplitNavPageViewHandler(SplitNavPageView splitNavPageView) {
            this.mSplitNavPageView = new WeakReference<>(splitNavPageView);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.mSplitNavPageView.get() == null) {
                return;
            }
            SplitNavPageView splitNavPageView = this.mSplitNavPageView.get();
            if (msg.what == 200) {
                splitNavPageView.doCompletionVideo();
            } else if (msg.what == 201) {
                splitNavPageView.doStartVideo();
            } else if (msg.what == 202) {
                splitNavPageView.doHideFirstFrame();
            }
        }
    }
}
