package com.amazon.avod.media.playback.reporting.aloysius;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Insets;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Size;
import android.view.Display;
import android.view.View;
import android.view.WindowInsets$Type;
import android.view.WindowManager;
import android.view.WindowMetrics;
import androidx.annotation.VisibleForTesting;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.events.AloysiusConfig;
import com.amazon.avod.media.events.MediaEventProducer;
import com.amazon.avod.media.events.MediaEventQueue;
import com.amazon.avod.media.events.MediaEventReporter;
import com.amazon.avod.media.events.model.MediaEvent;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.util.BroadcastReceiverUtils;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class AloysiusDisplayReporter extends BroadcastReceiver implements MediaEventProducer, MediaEventReporter {
    private static final String HDMI_AUDIO_PLUG_MEDIA = "android.media.action.HDMI_AUDIO_PLUG";
    private static final String PRIVATE_METHOD_GETMETRICS = "getRealMetrics";
    private final Context mContext;
    private ExecutorService mDisplayEventExecutor;
    private final DisplayManager mDisplayManager;
    private boolean mIsConnected;
    private boolean mIsRegistered;
    private final MediaEventQueue mMediaEventQueue;
    private final Object mMutex;
    private final PlaybackDisplayContext mPlaybackDisplayContext;
    private final boolean mShouldUseRealDisplayMetricsForReporting;
    private final WindowManager mWindowManager;

    public AloysiusDisplayReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull Context context) {
        this.mMutex = new Object();
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mDisplayManager = (DisplayManager) context.getSystemService("display");
        this.mPlaybackDisplayContext = PlaybackDisplayContext.getInstance();
        this.mDisplayEventExecutor = ExecutorBuilder.newBuilderFor(this, new String[0]).withFixedThreadPoolSize(1).allowCoreThreadExpiry().build();
        this.mShouldUseRealDisplayMetricsForReporting = AloysiusConfig.getInstance().shouldUseRealDisplayMetricsForReporting();
        this.mWindowManager = (WindowManager) context.getSystemService("window");
    }

    @VisibleForTesting
    public AloysiusDisplayReporter(@Nonnull MediaEventQueue mediaEventQueue, @Nonnull Context context, @Nonnull ExecutorService executorService, boolean z) {
        this.mMutex = new Object();
        this.mMediaEventQueue = (MediaEventQueue) Preconditions.checkNotNull(mediaEventQueue, "mediaEventQueue");
        this.mContext = (Context) Preconditions.checkNotNull(context, "context");
        this.mDisplayEventExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "displayEventExecutor");
        this.mDisplayManager = (DisplayManager) context.getSystemService("display");
        this.mPlaybackDisplayContext = PlaybackDisplayContext.getInstance();
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        this.mShouldUseRealDisplayMetricsForReporting = z;
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void initialize() {
        synchronized (this.mMutex) {
            this.mContext.registerReceiver(this, new IntentFilter(HDMI_AUDIO_PLUG_MEDIA));
            this.mIsRegistered = true;
        }
    }

    @Override // com.amazon.avod.media.events.MediaEventReporter
    public void terminate() {
        synchronized (this.mMutex) {
            if (this.mIsRegistered) {
                BroadcastReceiverUtils.unregisterReceiver(this.mContext, this);
                this.mIsRegistered = false;
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.mIsConnected = intent.getIntExtra("state", -1) == 1;
        addEventToQueue();
    }

    @Override // com.amazon.avod.media.events.MediaEventProducer
    public void produce() {
        addEventToQueue();
    }

    class EventRunnable implements Runnable {
        EventRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            DisplayMetricsUtils displayMetricsUtils;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            boolean z = false;
            AloysiusDisplayReporter.this.mDisplayManager.getDisplay(0).getMetrics(displayMetrics);
            if (AloysiusDisplayReporter.this.mShouldUseRealDisplayMetricsForReporting) {
                displayMetricsUtils = getRealDisplayMetrics();
            } else {
                displayMetricsUtils = new DisplayMetricsUtils(displayMetrics.widthPixels, displayMetrics.heightPixels);
            }
            int width = displayMetricsUtils.getWidth() * displayMetricsUtils.getHeight();
            DisplayMediaEvent.Builder builderVisible = new DisplayMediaEvent.Builder().connected(AloysiusDisplayReporter.this.mIsConnected).display(displayMetricsUtils.getWidth(), displayMetricsUtils.getHeight()).visible(width, width);
            View view = AloysiusDisplayReporter.this.mPlaybackDisplayContext.getPlaybackView() != null ? AloysiusDisplayReporter.this.mPlaybackDisplayContext.getPlaybackView().get() : null;
            if (view == null) {
                VideoResolution videoResolution = AloysiusDisplayReporter.this.mPlaybackDisplayContext.getVideoResolution();
                if (videoResolution != null) {
                    builderVisible.player(videoResolution.getWidth(), videoResolution.getHeight());
                } else {
                    builderVisible.player(displayMetricsUtils.getWidth(), displayMetricsUtils.getHeight());
                }
            } else {
                builderVisible.player(view.getWidth(), view.getHeight());
            }
            DisplayMediaEvent.DisplayInfo displayInfo = builderVisible.mPlayer;
            if (displayInfo.mWidth == displayMetricsUtils.getWidth() && displayInfo.mHeight == displayMetricsUtils.getHeight()) {
                z = true;
            }
            Activity activity = AloysiusDisplayReporter.this.mPlaybackDisplayContext.getActivity() != null ? AloysiusDisplayReporter.this.mPlaybackDisplayContext.getActivity().get() : null;
            if (activity != null && Build.VERSION.SDK_INT >= 26 && activity.isInPictureInPictureMode()) {
                builderVisible.mode(DisplayMediaEvent.Mode.PictureInPicture);
            } else if ((activity != null && activity.isInMultiWindowMode()) || !z) {
                builderVisible.mode(DisplayMediaEvent.Mode.Embedded);
            } else {
                builderVisible.mode(DisplayMediaEvent.Mode.Fullscreen);
            }
            AloysiusDisplayReporter.this.mMediaEventQueue.add(builderVisible.build());
        }

        private DisplayMetricsUtils getRealDisplayMetrics() {
            if (Build.VERSION.SDK_INT >= 30) {
                AloysiusDisplayReporter.this.mWindowManager.getCurrentWindowMetrics().getBounds();
                WindowMetrics currentWindowMetrics = AloysiusDisplayReporter.this.mWindowManager.getCurrentWindowMetrics();
                Insets insetsIgnoringVisibility = currentWindowMetrics.getWindowInsets().getInsetsIgnoringVisibility(WindowInsets$Type.navigationBars() | WindowInsets$Type.displayCutout());
                int i = insetsIgnoringVisibility.right + insetsIgnoringVisibility.left;
                int i2 = insetsIgnoringVisibility.top + insetsIgnoringVisibility.bottom;
                Rect bounds = currentWindowMetrics.getBounds();
                Size size = new Size(bounds.width() - i, bounds.height() - i2);
                return new DisplayMetricsUtils(size.getWidth(), size.getHeight());
            }
            Display display = AloysiusDisplayReporter.this.mDisplayManager.getDisplay(0);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Method method = null;
            try {
                method = display.getClass().getMethod(AloysiusDisplayReporter.PRIVATE_METHOD_GETMETRICS, DisplayMetrics.class);
            } catch (NoSuchMethodException | SecurityException e) {
                DLog.exceptionf(e, "Could not get %s method using reflection", AloysiusDisplayReporter.PRIVATE_METHOD_GETMETRICS);
            }
            if (method != null) {
                try {
                    method.invoke(display, displayMetrics);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
                    DLog.exceptionf(e2, "Could not invoke %s method using reflection", AloysiusDisplayReporter.PRIVATE_METHOD_GETMETRICS);
                }
            }
            return new DisplayMetricsUtils(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }
    }

    private void addEventToQueue() {
        this.mDisplayEventExecutor.execute(new EventRunnable());
    }

    static class DisplayMetricsUtils {
        private final int mHeight;
        private final int mWidth;

        public DisplayMetricsUtils(int i, int i2) {
            this.mWidth = i;
            this.mHeight = i2;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int getHeight() {
            return this.mHeight;
        }
    }

    @VisibleForTesting
    static class DisplayMediaEvent implements MediaEvent {
        private final boolean mConnected;
        private final DisplayInfo mDisplay;
        private final Mode mMode;
        private final DisplayInfo mPlayer;
        private final Visible mVisible;

        @VisibleForTesting
        enum Mode {
            Fullscreen,
            Embedded,
            PictureInPicture
        }

        private DisplayMediaEvent(@Nonnull Builder builder) {
            this.mConnected = ((Builder) Preconditions.checkNotNull(builder, "builder")).mConnected;
            this.mDisplay = builder.mDisplay;
            this.mPlayer = builder.mPlayer;
            this.mVisible = builder.mVisible;
            this.mMode = builder.mMode;
        }

        public boolean isConnected() {
            return this.mConnected;
        }

        @Nonnull
        public DisplayInfo getDisplay() {
            return this.mDisplay;
        }

        @Nonnull
        public DisplayInfo getPlayer() {
            return this.mPlayer;
        }

        @Nullable
        public Visible getVisible() {
            return this.mVisible;
        }

        @Override // com.amazon.avod.media.events.model.MediaEvent
        public MediaEvent.MediaEventType getMediaEventType() {
            return MediaEvent.MediaEventType.Display;
        }

        @Nonnull
        public Mode getMode() {
            return this.mMode;
        }

        @VisibleForTesting
        static class DisplayInfo {
            private final int mHeight;
            private final int mWidth;

            private DisplayInfo(@Nonnegative int i, @Nonnegative int i2) {
                Preconditions.checkArgument(i >= 0, "width must be non negative");
                Preconditions.checkArgument(i2 >= 0, "height must be non negative");
                this.mWidth = i;
                this.mHeight = i2;
            }

            @Nonnegative
            public int getWidth() {
                return this.mWidth;
            }

            @Nonnegative
            public int getHeight() {
                return this.mHeight;
            }
        }

        @VisibleForTesting
        static class Visible {
            private final int mPixelsTotal;
            private final int mPixelsVisible;

            private Visible(@Nonnegative int i, @Nonnegative int i2) {
                Preconditions.checkArgument(i >= 0, "pixelsTotal must be non negative");
                Preconditions.checkArgument(i2 >= 0, "pixelsVisible must be non negative");
                this.mPixelsTotal = i;
                this.mPixelsVisible = i2;
            }

            @Nonnegative
            public int getPixelsTotal() {
                return this.mPixelsTotal;
            }

            @Nonnegative
            public int getPixelsVisible() {
                return this.mPixelsVisible;
            }
        }

        private static class Builder {
            private boolean mConnected;
            private DisplayInfo mDisplay;
            private Mode mMode;
            private DisplayInfo mPlayer;
            private Visible mVisible;

            private Builder() {
            }

            public Builder connected(boolean z) {
                this.mConnected = z;
                return this;
            }

            public Builder display(@Nonnegative int i, @Nonnegative int i2) {
                this.mDisplay = new DisplayInfo(i, i2);
                return this;
            }

            public Builder player(@Nonnegative int i, @Nonnegative int i2) {
                this.mPlayer = new DisplayInfo(i, i2);
                return this;
            }

            public Builder visible(@Nonnegative int i, @Nonnegative int i2) {
                this.mVisible = new Visible(i, i2);
                return this;
            }

            public Builder mode(@Nonnull Mode mode) {
                this.mMode = (Mode) Preconditions.checkNotNull(mode, "mode");
                return this;
            }

            public MediaEvent build() {
                return new DisplayMediaEvent(this);
            }
        }
    }
}
