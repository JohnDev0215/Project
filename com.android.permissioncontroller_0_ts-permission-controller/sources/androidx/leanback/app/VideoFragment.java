package androidx.leanback.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.R;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class VideoFragment extends PlaybackFragment {
    static final int SURFACE_CREATED = 1;
    static final int SURFACE_NOT_CREATED = 0;
    SurfaceHolder.Callback mMediaPlaybackCallback;
    int mState = 0;
    SurfaceView mVideoSurface;

    @Override // androidx.leanback.app.PlaybackFragment, android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) super.onCreateView(inflater, container, savedInstanceState);
        SurfaceView surfaceView = (SurfaceView) LayoutInflater.from(FragmentUtil.getContext(this)).inflate(R.layout.lb_video_surface, root, false);
        this.mVideoSurface = surfaceView;
        root.addView(surfaceView, 0);
        this.mVideoSurface.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: androidx.leanback.app.VideoFragment.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder holder) {
                if (VideoFragment.this.mMediaPlaybackCallback != null) {
                    VideoFragment.this.mMediaPlaybackCallback.surfaceCreated(holder);
                }
                VideoFragment.this.mState = 1;
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                if (VideoFragment.this.mMediaPlaybackCallback != null) {
                    VideoFragment.this.mMediaPlaybackCallback.surfaceChanged(holder, format, width, height);
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (VideoFragment.this.mMediaPlaybackCallback != null) {
                    VideoFragment.this.mMediaPlaybackCallback.surfaceDestroyed(holder);
                }
                VideoFragment.this.mState = 0;
            }
        });
        setBackgroundType(2);
        return root;
    }

    public void setSurfaceHolderCallback(SurfaceHolder.Callback callback) {
        this.mMediaPlaybackCallback = callback;
        if (callback != null && this.mState == 1) {
            callback.surfaceCreated(this.mVideoSurface.getHolder());
        }
    }

    @Override // androidx.leanback.app.PlaybackFragment
    protected void onVideoSizeChanged(int width, int height) {
        int screenWidth = getView().getWidth();
        int screenHeight = getView().getHeight();
        ViewGroup.LayoutParams p = this.mVideoSurface.getLayoutParams();
        if (screenWidth * height > width * screenHeight) {
            p.height = screenHeight;
            p.width = (screenHeight * width) / height;
        } else {
            p.width = screenWidth;
            p.height = (screenWidth * height) / width;
        }
        this.mVideoSurface.setLayoutParams(p);
    }

    public SurfaceView getSurfaceView() {
        return this.mVideoSurface;
    }

    @Override // androidx.leanback.app.PlaybackFragment, android.app.Fragment
    public void onDestroyView() {
        this.mVideoSurface = null;
        this.mState = 0;
        super.onDestroyView();
    }
}
