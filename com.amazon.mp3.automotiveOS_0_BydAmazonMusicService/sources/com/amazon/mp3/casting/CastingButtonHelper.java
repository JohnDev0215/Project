package com.amazon.mp3.casting;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.view.ContextThemeWrapper;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.CastingException;
import com.amazon.music.casting.session.CastingConnectionCallback;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CastingButtonHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0015\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004¢\u0006\u0002\u0010\u0006J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014R4\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0006R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/casting/CastingButtonHelper;", "", "()V", "castingButton", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/mp3/casting/CastingButton;", "(Ljava/lang/ref/WeakReference;)V", "value", "getCastingButton", "()Ljava/lang/ref/WeakReference;", "setCastingButton", "castingManager", "Lcom/amazon/music/casting/session/CastingSessionManager;", "mCallback", "Lcom/amazon/music/casting/session/CastingConnectionCallback;", "getContentName", "Lcom/amazon/music/castingviews/CastingUIMetricsLogger$ContentName;", "originalContext", "Landroid/content/Context;", "refreshVisibility", "", "unregisterListeners", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CastingButtonHelper {
    private WeakReference<CastingButton> castingButton;
    private final CastingSessionManager castingManager;
    private final CastingConnectionCallback mCallback;

    public CastingButtonHelper(WeakReference<CastingButton> weakReference) {
        CastingSessionManager castingSessionManager = CastingSessionManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(castingSessionManager, "getInstance()");
        this.castingManager = castingSessionManager;
        this.mCallback = new CastingConnectionCallback() { // from class: com.amazon.mp3.casting.CastingButtonHelper$mCallback$1
            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onError(CastingException e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onConnected(CastingDevice device) {
                CastingButton castingButton;
                Intrinsics.checkNotNullParameter(device, "device");
                WeakReference<CastingButton> castingButton2 = this.this$0.getCastingButton();
                if (castingButton2 == null || (castingButton = castingButton2.get()) == null) {
                    return;
                }
                castingButton.updateButton(true);
            }

            @Override // com.amazon.music.casting.session.CastingConnectionCallback
            public void onDisconnected(TerminationReason terminationReason) {
                CastingButton castingButton;
                WeakReference<CastingButton> castingButton2 = this.this$0.getCastingButton();
                if (castingButton2 == null || (castingButton = castingButton2.get()) == null) {
                    return;
                }
                castingButton.updateButton(false);
            }
        };
        setCastingButton(weakReference);
    }

    public CastingButtonHelper() {
        this(null);
    }

    public final WeakReference<CastingButton> getCastingButton() {
        return this.castingButton;
    }

    public final void setCastingButton(WeakReference<CastingButton> weakReference) {
        CastingButton castingButton;
        this.castingButton = weakReference;
        if (weakReference != null && (castingButton = weakReference.get()) != null) {
            castingButton.setOnCastingButtonClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.casting.CastingButtonHelper$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CastingButtonHelper.m447_set_castingButton_$lambda0(this.f$0, view);
                }
            });
        }
        refreshVisibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _set_castingButton_$lambda-0, reason: not valid java name */
    public static final void m447_set_castingButton_$lambda0(CastingButtonHelper this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context = view.getContext();
        Intent startIntent = CastingFragment.getStartIntent(context);
        Intrinsics.checkNotNullExpressionValue(context, "context");
        CastingUIMetricsLogger.ContentName contentName = this$0.getContentName(context);
        if (contentName != null) {
            CastingUIMetricsLogger.recordCastingButtonClickMetric(contentName);
        }
        context.startActivity(startIntent);
    }

    public final void refreshVisibility() {
        CastingButton castingButton;
        CastingButton castingButton2;
        CastingButton castingButton3;
        MediaItem currentMediaItem = Playback.getInstance().getPlaybackController(ControlSource.APP_UI).getCurrentMediaItem();
        if (CastingUtil.isCastingAvailable() && currentMediaItem != null && !NowPlayingUtil.isVideoPlaying()) {
            ChildUserUtil childUserUtil = ChildUserUtil.INSTANCE;
            Context applicationContext = AmazonApplication.getApplication().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplication().applicationContext");
            if (!childUserUtil.isChildUser(applicationContext)) {
                WeakReference<CastingButton> weakReference = this.castingButton;
                if (weakReference != null && (castingButton3 = weakReference.get()) != null) {
                    castingButton3.updateButton(CastingUtil.isCasting());
                }
                this.castingManager.registerListener(this.mCallback);
                WeakReference<CastingButton> weakReference2 = this.castingButton;
                if (weakReference2 == null || (castingButton2 = weakReference2.get()) == null) {
                    return;
                }
                castingButton2.show(true);
                return;
            }
        }
        WeakReference<CastingButton> weakReference3 = this.castingButton;
        if (weakReference3 == null || (castingButton = weakReference3.get()) == null) {
            return;
        }
        castingButton.show(false);
    }

    public final void unregisterListeners() {
        this.castingManager.unregisterListener(this.mCallback);
    }

    private final CastingUIMetricsLogger.ContentName getContentName(Context originalContext) {
        if (originalContext instanceof ContextThemeWrapper) {
            originalContext = ((ContextThemeWrapper) originalContext).getBaseContext();
            Intrinsics.checkNotNullExpressionValue(originalContext, "originalContext.baseContext");
        }
        if (originalContext instanceof NowPlayingFragmentActivity) {
            return CastingUIMetricsLogger.ContentName.NOW_PLAYING;
        }
        if (originalContext instanceof MusicHomeActivity) {
            return CastingUIMetricsLogger.ContentName.ACTION_BAR;
        }
        return null;
    }
}
