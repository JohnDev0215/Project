package com.android.car.developeroptions.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.developeroptions.R;

/* JADX INFO: loaded from: classes.dex */
public class VideoPreference extends Preference {
    boolean mAnimationAvailable;
    private int mAnimationId;
    private float mAspectRadio;
    private final Context mContext;
    MediaPlayer mMediaPlayer;
    private int mPreviewResource;
    private Surface mSurface;
    private Uri mVideoPath;
    private boolean mVideoPaused;
    boolean mVideoReady;
    private boolean mViewVisible;

    public VideoPreference(Context context) {
        super(context);
        this.mAspectRadio = 1.0f;
        this.mContext = context;
        initialize(context, null);
    }

    public VideoPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAspectRadio = 1.0f;
        this.mContext = context;
        initialize(context, attributeSet);
    }

    private void initialize(Context context, AttributeSet attributeSet) {
        int resourceId;
        int resourceId2;
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.VideoPreference, 0, 0);
        try {
            try {
                if (this.mAnimationId == 0) {
                    resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
                } else {
                    resourceId = this.mAnimationId;
                }
                this.mAnimationId = resourceId;
                this.mVideoPath = new Uri.Builder().scheme("android.resource").authority(context.getPackageName()).appendPath(String.valueOf(this.mAnimationId)).build();
                if (this.mPreviewResource == 0) {
                    resourceId2 = typedArrayObtainStyledAttributes.getResourceId(1, 0);
                } else {
                    resourceId2 = this.mPreviewResource;
                }
                this.mPreviewResource = resourceId2;
            } catch (Exception unused) {
                Log.w("VideoPreference", "Animation resource not found. Will not show animation.");
            }
            if (resourceId2 == 0 && this.mAnimationId == 0) {
                return;
            }
            initMediaPlayer();
            if (this.mMediaPlayer != null && this.mMediaPlayer.getDuration() > 0) {
                setVisible(true);
                setLayoutResource(R.layout.video_preference);
                this.mAnimationAvailable = true;
                updateAspectRatio();
            } else {
                setVisible(false);
            }
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        if (this.mAnimationAvailable) {
            TextureView textureView = (TextureView) preferenceViewHolder.findViewById(R.id.video_texture_view);
            final ImageView imageView = (ImageView) preferenceViewHolder.findViewById(R.id.video_preview_image);
            final ImageView imageView2 = (ImageView) preferenceViewHolder.findViewById(R.id.video_play_button);
            AspectRatioFrameLayout aspectRatioFrameLayout = (AspectRatioFrameLayout) preferenceViewHolder.findViewById(R.id.video_container);
            imageView.setImageResource(this.mPreviewResource);
            aspectRatioFrameLayout.setAspectRatio(this.mAspectRadio);
            updateViewStates(imageView, imageView2);
            textureView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.widget.-$$Lambda$VideoPreference$CUS_9N24mEIIVfz3jOioMbMVgOE
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onBindViewHolder$0$VideoPreference(imageView, imageView2, view);
                }
            });
            textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.android.car.developeroptions.widget.VideoPreference.1
                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                    VideoPreference videoPreference = VideoPreference.this;
                    if (videoPreference.mMediaPlayer != null) {
                        videoPreference.mSurface = new Surface(surfaceTexture);
                        VideoPreference videoPreference2 = VideoPreference.this;
                        videoPreference2.mMediaPlayer.setSurface(videoPreference2.mSurface);
                    }
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    imageView.setVisibility(0);
                    return false;
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                    MediaPlayer mediaPlayer;
                    if (VideoPreference.this.mViewVisible) {
                        if (VideoPreference.this.mVideoReady) {
                            if (imageView.getVisibility() == 0) {
                                imageView.setVisibility(8);
                            }
                            if (!VideoPreference.this.mVideoPaused && (mediaPlayer = VideoPreference.this.mMediaPlayer) != null && !mediaPlayer.isPlaying()) {
                                VideoPreference.this.mMediaPlayer.start();
                                imageView2.setVisibility(8);
                            }
                        }
                        MediaPlayer mediaPlayer2 = VideoPreference.this.mMediaPlayer;
                        if (mediaPlayer2 == null || mediaPlayer2.isPlaying() || imageView2.getVisibility() == 0) {
                            return;
                        }
                        imageView2.setVisibility(0);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onBindViewHolder$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onBindViewHolder$0$VideoPreference(ImageView imageView, ImageView imageView2, View view) {
        updateViewStates(imageView, imageView2);
    }

    void updateViewStates(ImageView imageView, ImageView imageView2) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
                imageView2.setVisibility(0);
                imageView.setVisibility(0);
                this.mVideoPaused = true;
                return;
            }
            imageView.setVisibility(8);
            imageView2.setVisibility(8);
            this.mMediaPlayer.start();
            this.mVideoPaused = false;
        }
    }

    @Override // androidx.preference.Preference
    public void onDetached() {
        releaseMediaPlayer();
        super.onDetached();
    }

    public void onViewVisible(boolean z) {
        this.mViewVisible = true;
        this.mVideoPaused = z;
        initMediaPlayer();
    }

    public void onViewInvisible() {
        this.mViewVisible = false;
        releaseMediaPlayer();
    }

    public void setVideo(int i, int i2) {
        this.mAnimationId = i;
        this.mPreviewResource = i2;
        releaseMediaPlayer();
        initialize(this.mContext, null);
    }

    private void initMediaPlayer() {
        if (this.mMediaPlayer == null) {
            MediaPlayer mediaPlayerCreate = MediaPlayer.create(this.mContext, this.mVideoPath);
            this.mMediaPlayer = mediaPlayerCreate;
            if (mediaPlayerCreate != null) {
                mediaPlayerCreate.seekTo(0);
                this.mMediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() { // from class: com.android.car.developeroptions.widget.-$$Lambda$VideoPreference$xHmOe4qLfaxLA5RObhGm8LVElU8
                    @Override // android.media.MediaPlayer.OnSeekCompleteListener
                    public final void onSeekComplete(MediaPlayer mediaPlayer) {
                        this.f$0.lambda$initMediaPlayer$1$VideoPreference(mediaPlayer);
                    }
                });
                this.mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.android.car.developeroptions.widget.-$$Lambda$VideoPreference$kUZboUTN7NAnau7Uvmz5rVBZPpQ
                    @Override // android.media.MediaPlayer.OnPreparedListener
                    public final void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.setLooping(true);
                    }
                });
                Surface surface = this.mSurface;
                if (surface != null) {
                    this.mMediaPlayer.setSurface(surface);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$initMediaPlayer$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$initMediaPlayer$1$VideoPreference(MediaPlayer mediaPlayer) {
        this.mVideoReady = true;
    }

    private void releaseMediaPlayer() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mVideoReady = false;
        }
    }

    public boolean isVideoPaused() {
        return this.mVideoPaused;
    }

    void updateAspectRatio() {
        this.mAspectRadio = this.mMediaPlayer.getVideoWidth() / this.mMediaPlayer.getVideoHeight();
    }
}
