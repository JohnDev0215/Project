package org.chromium.content.browser;

import android.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.provider.Settings;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.chromium.base.Log;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
public class ContentVideoView extends FrameLayout implements SurfaceHolder.Callback {
    public static final int MEDIA_ERROR_INVALID_CODE = 3;
    public static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 2;
    private static final int STATE_ERROR = -1;
    private static final int STATE_NO_ERROR = 0;
    private static final String TAG = "cr.ContentVideoView";
    private int mCurrentState;
    private final ContentVideoViewEmbedder mEmbedder;
    private String mErrorButton;
    private String mErrorTitle;
    private final Runnable mExitFullscreenRunnable;
    private boolean mInitialOrientation;
    private long mNativeContentVideoView;
    private long mOrientationChangedTime;
    private String mPlaybackErrorText;
    private long mPlaybackStartTime;
    private boolean mPossibleAccidentalChange;
    private View mProgressView;
    private SurfaceHolder mSurfaceHolder;
    private boolean mUmaRecorded;
    private String mUnknownErrorText;
    private int mVideoHeight;
    private String mVideoLoadingText;
    private VideoSurfaceView mVideoSurfaceView;
    private int mVideoWidth;

    private native void nativeDidExitFullscreen(long j, boolean z);

    private static native ContentVideoView nativeGetSingletonJavaContentVideoView();

    private native void nativeRecordExitFullscreenPlayback(long j, boolean z, long j2, long j3);

    private native void nativeRecordFullscreenPlayback(long j, boolean z, boolean z2);

    private native void nativeSetSurface(long j, Surface surface);

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    private class VideoSurfaceView extends SurfaceView {
        public VideoSurfaceView(Context context) {
            super(context);
        }

        @Override // android.view.SurfaceView, android.view.View
        protected void onMeasure(int i, int i2) {
            int defaultSize;
            int defaultSize2;
            if (ContentVideoView.this.mVideoWidth <= 0 || ContentVideoView.this.mVideoHeight <= 0) {
                defaultSize = 1;
                defaultSize2 = 1;
            } else {
                defaultSize = getDefaultSize(ContentVideoView.this.mVideoWidth, i);
                defaultSize2 = getDefaultSize(ContentVideoView.this.mVideoHeight, i2);
                if (ContentVideoView.this.mVideoWidth * defaultSize2 > ContentVideoView.this.mVideoHeight * defaultSize) {
                    defaultSize2 = (ContentVideoView.this.mVideoHeight * defaultSize) / ContentVideoView.this.mVideoWidth;
                } else if (ContentVideoView.this.mVideoWidth * defaultSize2 < ContentVideoView.this.mVideoHeight * defaultSize) {
                    defaultSize = (ContentVideoView.this.mVideoWidth * defaultSize2) / ContentVideoView.this.mVideoHeight;
                }
            }
            if (ContentVideoView.this.mUmaRecorded) {
                if (ContentVideoView.this.mPlaybackStartTime == ContentVideoView.this.mOrientationChangedTime) {
                    if (ContentVideoView.this.isOrientationPortrait() != ContentVideoView.this.mInitialOrientation) {
                        ContentVideoView.this.mOrientationChangedTime = System.currentTimeMillis();
                    }
                } else if (!ContentVideoView.this.mPossibleAccidentalChange && ContentVideoView.this.isOrientationPortrait() == ContentVideoView.this.mInitialOrientation && System.currentTimeMillis() - ContentVideoView.this.mOrientationChangedTime < 5000) {
                    ContentVideoView.this.mPossibleAccidentalChange = true;
                }
            }
            setMeasuredDimension(defaultSize, defaultSize2);
        }
    }

    private static class ProgressView extends LinearLayout {
        private final ProgressBar mProgressBar;
        private final TextView mTextView;

        public ProgressView(Context context, String str) {
            super(context);
            setOrientation(1);
            setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            ProgressBar progressBar = new ProgressBar(context, null, R.attr.progressBarStyleLarge);
            this.mProgressBar = progressBar;
            TextView textView = new TextView(context);
            this.mTextView = textView;
            textView.setText(str);
            addView(progressBar);
            addView(textView);
        }
    }

    private ContentVideoView(Context context, long j, ContentVideoViewEmbedder contentVideoViewEmbedder) {
        super(context);
        this.mCurrentState = 0;
        this.mExitFullscreenRunnable = new Runnable() { // from class: org.chromium.content.browser.ContentVideoView.1
            @Override // java.lang.Runnable
            public void run() {
                ContentVideoView.this.exitFullscreen(true);
            }
        };
        this.mNativeContentVideoView = j;
        this.mEmbedder = contentVideoViewEmbedder;
        this.mUmaRecorded = false;
        this.mPossibleAccidentalChange = false;
        initResources(context);
        this.mVideoSurfaceView = new VideoSurfaceView(context);
        showContentVideoView();
        setVisibility(0);
    }

    private ContentVideoViewEmbedder getContentVideoViewEmbedder() {
        return this.mEmbedder;
    }

    private void initResources(Context context) {
        if (this.mPlaybackErrorText != null) {
            return;
        }
        this.mPlaybackErrorText = context.getString(org.chromium.content.R.string.media_player_error_text_invalid_progressive_playback);
        this.mUnknownErrorText = context.getString(org.chromium.content.R.string.media_player_error_text_unknown);
        this.mErrorButton = context.getString(org.chromium.content.R.string.media_player_error_button);
        this.mErrorTitle = context.getString(org.chromium.content.R.string.media_player_error_title);
        this.mVideoLoadingText = context.getString(org.chromium.content.R.string.media_player_loading_video);
    }

    private void showContentVideoView() {
        this.mVideoSurfaceView.getHolder().addCallback(this);
        addView(this.mVideoSurfaceView, new FrameLayout.LayoutParams(-2, -2, 17));
        View videoLoadingProgressView = this.mEmbedder.getVideoLoadingProgressView();
        this.mProgressView = videoLoadingProgressView;
        if (videoLoadingProgressView == null) {
            this.mProgressView = new ProgressView(getContext(), this.mVideoLoadingText);
        }
        addView(this.mProgressView, new FrameLayout.LayoutParams(-2, -2, 17));
    }

    public void onMediaPlayerError(int i) {
        String str;
        Log.d(TAG, "OnMediaPlayerError: %d", Integer.valueOf(i));
        if (this.mCurrentState == -1 || i == 3) {
            return;
        }
        this.mCurrentState = -1;
        if (WindowAndroid.activityFromContext(getContext()) == null) {
            Log.w(TAG, "Unable to show alert dialog because it requires an activity context", new Object[0]);
            return;
        }
        if (getWindowToken() != null) {
            if (i == 2) {
                str = this.mPlaybackErrorText;
            } else {
                str = this.mUnknownErrorText;
            }
            try {
                new AlertDialog.Builder(getContext()).setTitle(this.mErrorTitle).setMessage(str).setPositiveButton(this.mErrorButton, new DialogInterface.OnClickListener() { // from class: org.chromium.content.browser.ContentVideoView.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                    }
                }).setCancelable(false).show();
            } catch (RuntimeException e) {
                Log.e(TAG, "Cannot show the alert dialog, error message: %s", str, e);
            }
        }
    }

    private void onVideoSizeChanged(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        this.mVideoSurfaceView.getHolder().setFixedSize(this.mVideoWidth, this.mVideoHeight);
        if (this.mUmaRecorded) {
            return;
        }
        this.mProgressView.setVisibility(8);
        try {
            if (Settings.System.getInt(getContext().getContentResolver(), "accelerometer_rotation") == 0) {
                return;
            }
            this.mInitialOrientation = isOrientationPortrait();
            this.mUmaRecorded = true;
            long jCurrentTimeMillis = System.currentTimeMillis();
            this.mPlaybackStartTime = jCurrentTimeMillis;
            this.mOrientationChangedTime = jCurrentTimeMillis;
            nativeRecordFullscreenPlayback(this.mNativeContentVideoView, this.mVideoHeight > this.mVideoWidth, this.mInitialOrientation);
        } catch (Settings.SettingNotFoundException unused) {
        }
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.mSurfaceHolder = surfaceHolder;
        openVideo();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        long j = this.mNativeContentVideoView;
        if (j != 0) {
            nativeSetSurface(j, null);
        }
        this.mSurfaceHolder = null;
        post(this.mExitFullscreenRunnable);
    }

    private void openVideo() {
        SurfaceHolder surfaceHolder = this.mSurfaceHolder;
        if (surfaceHolder != null) {
            this.mCurrentState = 0;
            long j = this.mNativeContentVideoView;
            if (j != 0) {
                nativeSetSurface(j, surfaceHolder.getSurface());
            }
        }
    }

    private static ContentVideoView createContentVideoView(ContentViewCore contentViewCore, long j) {
        ThreadUtils.assertOnUiThread();
        Context context = contentViewCore.getContext();
        ContentVideoViewEmbedder contentVideoViewEmbedder = contentViewCore.getContentVideoViewEmbedder();
        ContentVideoView contentVideoView = new ContentVideoView(context, j, contentVideoViewEmbedder);
        contentVideoViewEmbedder.enterFullscreenVideo(contentVideoView);
        return contentVideoView;
    }

    public void removeSurfaceView() {
        removeView(this.mVideoSurfaceView);
        removeView(this.mProgressView);
        this.mVideoSurfaceView = null;
        this.mProgressView = null;
    }

    public void exitFullscreen(boolean z) {
        long j;
        if (this.mNativeContentVideoView != 0) {
            destroyContentVideoView(false);
            if (this.mUmaRecorded && !this.mPossibleAccidentalChange) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                long j2 = this.mOrientationChangedTime;
                long j3 = j2 - this.mPlaybackStartTime;
                long j4 = jCurrentTimeMillis - j2;
                if (j3 == 0) {
                    j3 = j4;
                    j = 0;
                } else {
                    j = j4;
                }
                nativeRecordExitFullscreenPlayback(this.mNativeContentVideoView, this.mInitialOrientation, j3, j);
            }
            nativeDidExitFullscreen(this.mNativeContentVideoView, z);
            this.mNativeContentVideoView = 0L;
        }
    }

    public void onFullscreenWindowFocused() {
        this.mEmbedder.setSystemUiVisibility(true);
    }

    private void destroyContentVideoView(boolean z) {
        if (this.mVideoSurfaceView != null) {
            removeSurfaceView();
            setVisibility(8);
            this.mEmbedder.exitFullscreenVideo();
        }
        if (z) {
            this.mNativeContentVideoView = 0L;
        }
    }

    public static ContentVideoView getContentVideoView() {
        return nativeGetSingletonJavaContentVideoView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isOrientationPortrait() {
        Display defaultDisplay = ((WindowManager) getContext().getSystemService("window")).getDefaultDisplay();
        Point point = new Point(0, 0);
        defaultDisplay.getSize(point);
        return point.x <= point.y;
    }
}
