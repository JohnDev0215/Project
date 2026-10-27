package com.amazon.mp3.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.QueueEntityCapabilityTranslator;
import com.amazon.mp3.dialog.fragment.HawkfireAllDevicesUpsellDialogFragment;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.DynamicUpsellTranslator;
import com.amazon.mp3.util.RunnableDebouncer;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.BauhausSeekBarWidget;
import com.amazon.mp3.view.stage.StageTooltipPresenter;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: BauhausSeekBarWidget.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001{B5\u0012\u0006\u0010s\u001a\u00020r\u0012\u0006\u0010u\u001a\u00020t\u0012\b\u00105\u001a\u0004\u0018\u000104\u0012\b\b\u0002\u0010v\u001a\u00020\u0007\u0012\b\b\u0002\u0010w\u001a\u00020\u0007¢\u0006\u0004\bx\u0010yB\u0019\b\u0016\u0012\u0006\u0010s\u001a\u00020r\u0012\u0006\u0010u\u001a\u00020t¢\u0006\u0004\bx\u0010zJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0014J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007J\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0004J$\u0010\u0011\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0015\u001a\u00020\u0004J\u0006\u0010\u0016\u001a\u00020\u0004J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0017J\u0012\u0010\u001c\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0014J\u000e\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001dJ\u0006\u0010 \u001a\u00020\u0007J\u0006\u0010!\u001a\u00020\u0007J\u0006\u0010\"\u001a\u00020\u0007J\u0006\u0010#\u001a\u00020\u0007J\b\u0010$\u001a\u00020\u0004H\u0002J\b\u0010%\u001a\u00020\u0004H\u0002J\b\u0010&\u001a\u00020\u0004H\u0002J\b\u0010'\u001a\u00020\u0004H\u0002J\u0010\u0010)\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0017H\u0002J\u0014\u0010+\u001a\u00020\u00042\n\b\u0002\u0010*\u001a\u0004\u0018\u00010\u001aH\u0002J\u0018\u00100\u001a\u00020\u00042\u0006\u0010-\u001a\u00020,2\u0006\u0010/\u001a\u00020.H\u0002J\u0010\u00103\u001a\u00020\u00042\u0006\u00102\u001a\u000201H\u0002R$\u00105\u001a\u0004\u0018\u0001048\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001c\u0010<\u001a\n ;*\u0004\u0018\u00010\f0\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020\u00078\u0002X\u0082D¢\u0006\u0006\n\u0004\b>\u0010?R\u0014\u0010@\u001a\u00020\f8\u0002X\u0082D¢\u0006\u0006\n\u0004\b@\u0010=R\u0014\u0010A\u001a\u00020\f8\u0002X\u0082D¢\u0006\u0006\n\u0004\bA\u0010=R\u0016\u0010B\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bB\u0010?R\"\u0010D\u001a\u00020C8\u0006@\u0006X\u0086.¢\u0006\u0012\n\u0004\bD\u0010E\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u0018\u0010K\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bK\u0010LR\u0018\u0010M\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010LR\u0018\u0010N\u001a\u0004\u0018\u00010J8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010LR\u0016\u0010O\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bO\u0010?R\u0017\u0010Q\u001a\u00020P8\u0006¢\u0006\f\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010TR\u0017\u0010V\u001a\u00020U8\u0006¢\u0006\f\n\u0004\bV\u0010W\u001a\u0004\bX\u0010YR\u0017\u0010[\u001a\u00020Z8\u0006¢\u0006\f\n\u0004\b[\u0010\\\u001a\u0004\b]\u0010^R\u0018\u0010_\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b_\u0010`R\u0014\u0010b\u001a\u00020a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bb\u0010cR\u0014\u0010e\u001a\u00020d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\be\u0010fR\u0014\u0010h\u001a\u00020g8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bh\u0010iR\u0011\u0010l\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\bj\u0010kR$\u0010q\u001a\u00020\u00072\u0006\u0010m\u001a\u00020\u00078F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bn\u0010k\"\u0004\bo\u0010p¨\u0006|"}, d2 = {"Lcom/amazon/mp3/view/BauhausSeekBarWidget;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/mp3/view/BauhausSeekBarWidget$TrackTouchListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "setTrackTouchListener", "onDetachedFromWindow", "", "size", "setSeekbarThumbSize", "updateSeekBar", "updateProgressBar", "", "badgeText", "contentDescription", "Lcom/amazon/mp3/view/stage/StageTooltipPresenter;", "stageTooltipPresenter", "updateAudioQualityBadge", "Landroid/view/View$OnClickListener;", "onClickListener", "setAudioQualityBadgeClickListener", "hideAudioQualityBadge", "pauseSeekbarProgressUpdate", "", "isLoading", "setLoadingState", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "", "intArray", "getSeekBarLocation", "getSeekBarLeftPadding", "getSeekBarRightPadding", "getSeekBarWidth", "getSeekBarHeight", "setSeekbarTextStyling", "setSeekbarProgressStyle", "setSeekbarThumbStyle", "updateTimeInfo", "lastTouch", "onUserUpdatedSeekBar", "configuration", "updateSeekBarVerticalPadding", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/DialogFragment;", "dialog", "showDialog", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "sendUiClickMetric", "Lcom/amazon/music/views/library/styles/StyleSheet;", "stylesheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStylesheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "setStylesheet", "(Lcom/amazon/music/views/library/styles/StyleSheet;)V", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "SEEK_BAR_REFRESH_INTERVAL_MS", "I", "ZERO_TIME_STRING", "DEFAULT_FORMATTED_TIME_STRING", "seekBarMaxProgress", "Landroid/widget/SeekBar;", "seekBar", "Landroid/widget/SeekBar;", "getSeekBar", "()Landroid/widget/SeekBar;", "setSeekBar", "(Landroid/widget/SeekBar;)V", "Landroid/widget/TextView;", "progressTime", "Landroid/widget/TextView;", "trackTime", "audioQualityBadge", "oldProgress", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "getPlaybackController", "()Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Landroid/os/Handler;", "mainHandler", "Landroid/os/Handler;", "getMainHandler", "()Landroid/os/Handler;", "Lcom/amazon/mp3/util/RunnableDebouncer;", "runnableDebouncer", "Lcom/amazon/mp3/util/RunnableDebouncer;", "getRunnableDebouncer", "()Lcom/amazon/mp3/util/RunnableDebouncer;", "trackTouchListener", "Lcom/amazon/mp3/view/BauhausSeekBarWidget$TrackTouchListener;", "Lrx/Subscription;", "styleSheetSubscription", "Lrx/Subscription;", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "mOnUserDraggedListener", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "Ljava/lang/Runnable;", "mUpdateSeekBarProgressRunnable", "Ljava/lang/Runnable;", "getProgress", "()I", NotificationCompat.CATEGORY_PROGRESS, "value", "getMax", "setMax", "(I)V", "max", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "defStyleAttr", "defStyleRes", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;Lcom/amazon/music/views/library/styles/StyleSheet;II)V", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "TrackTouchListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BauhausSeekBarWidget extends ConstraintLayout {
    private final String DEFAULT_FORMATTED_TIME_STRING;
    private final int SEEK_BAR_REFRESH_INTERVAL_MS;
    private final String TAG;
    private final String ZERO_TIME_STRING;
    private TextView audioQualityBadge;
    private final SeekBar.OnSeekBarChangeListener mOnUserDraggedListener;
    private final Runnable mUpdateSeekBarProgressRunnable;
    private final Handler mainHandler;
    private int oldProgress;
    private final ActionValidatedPlaybackController playbackController;
    private TextView progressTime;
    private final RunnableDebouncer runnableDebouncer;
    public SeekBar seekBar;
    private int seekBarMaxProgress;
    private final Subscription styleSheetSubscription;
    private StyleSheet stylesheet;
    private TextView trackTime;
    private TrackTouchListener trackTouchListener;

    /* JADX INFO: compiled from: BauhausSeekBarWidget.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/view/BauhausSeekBarWidget$TrackTouchListener;", "", "onProgressChanged", "", "seekBar", "Landroid/widget/SeekBar;", NotificationCompat.CATEGORY_PROGRESS, "", "fromUser", "", "onStartTrackingTouch", "onStopTrackingTouch", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface TrackTouchListener {
        void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser);

        void onStartTrackingTouch();

        void onStopTrackingTouch();
    }

    public final StyleSheet getStylesheet() {
        return this.stylesheet;
    }

    public final void setStylesheet(StyleSheet styleSheet) {
        this.stylesheet = styleSheet;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BauhausSeekBarWidget(Context context, AttributeSet attributeSet, StyleSheet styleSheet, int i, int i2) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.stylesheet = styleSheet;
        this.TAG = BauhausSeekBarWidget.class.getSimpleName();
        this.SEEK_BAR_REFRESH_INTERVAL_MS = 16;
        this.ZERO_TIME_STRING = "0:00";
        this.DEFAULT_FORMATTED_TIME_STRING = "--:--";
        this.seekBarMaxProgress = 1;
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        Handler handler = new Handler(Looper.getMainLooper());
        this.mainHandler = handler;
        this.runnableDebouncer = new RunnableDebouncer(handler);
        SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.amazon.mp3.view.BauhausSeekBarWidget$mOnUserDraggedListener$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                long durationMillis = this.this$0.getPlaybackController().getDurationMillis();
                long j = progress;
                String cachedFormattedTime = StringUtil.getCachedFormattedTime(j);
                String cachedFormattedTime2 = StringUtil.getCachedFormattedTime(durationMillis - j);
                TextView textView = this.this$0.progressTime;
                if (textView != null) {
                    textView.setText(cachedFormattedTime);
                }
                TextView textView2 = this.this$0.trackTime;
                if (textView2 != null) {
                    textView2.setText(cachedFormattedTime2);
                }
                if (!fromUser || CastingUtil.isCasting()) {
                    return;
                }
                this.this$0.onUserUpdatedSeekBar(false);
                BauhausSeekBarWidget.TrackTouchListener trackTouchListener = this.this$0.trackTouchListener;
                if (trackTouchListener == null) {
                    return;
                }
                trackTouchListener.onProgressChanged(seekBar, progress, fromUser);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                if (!CastingUtil.isCasting()) {
                    this.this$0.onUserUpdatedSeekBar(false);
                }
                BauhausSeekBarWidget bauhausSeekBarWidget = this.this$0;
                bauhausSeekBarWidget.oldProgress = bauhausSeekBarWidget.getProgress();
                BauhausSeekBarWidget bauhausSeekBarWidget2 = this.this$0;
                bauhausSeekBarWidget2.setSeekbarThumbSize((int) bauhausSeekBarWidget2.getResources().getDimension(R.dimen.spacer_16));
                this.this$0.getRunnableDebouncer().remove(this.this$0.mUpdateSeekBarProgressRunnable);
                BauhausSeekBarWidget.TrackTouchListener trackTouchListener = this.this$0.trackTouchListener;
                if (trackTouchListener == null) {
                    return;
                }
                trackTouchListener.onStartTrackingTouch();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                this.this$0.onUserUpdatedSeekBar(true);
                BauhausSeekBarWidget bauhausSeekBarWidget = this.this$0;
                bauhausSeekBarWidget.setSeekbarThumbSize((int) bauhausSeekBarWidget.getResources().getDimension(R.dimen.spacer_12));
                this.this$0.getRunnableDebouncer().post(this.this$0.mUpdateSeekBarProgressRunnable);
                BauhausSeekBarWidget.TrackTouchListener trackTouchListener = this.this$0.trackTouchListener;
                if (trackTouchListener != null) {
                    trackTouchListener.onStopTrackingTouch();
                }
                if (this.this$0.getProgress() > this.this$0.oldProgress) {
                    this.this$0.sendUiClickMetric(ActionType.FAST_FORWARD);
                } else {
                    this.this$0.sendUiClickMetric(ActionType.REWIND);
                }
            }
        };
        this.mOnUserDraggedListener = onSeekBarChangeListener;
        this.mUpdateSeekBarProgressRunnable = new Runnable() { // from class: com.amazon.mp3.view.BauhausSeekBarWidget$mUpdateSeekBarProgressRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                if (this.this$0.getPlaybackController().getDurationMillis() <= 0) {
                    return;
                }
                long positionMillis = this.this$0.getPlaybackController().getPositionMillis();
                if (positionMillis >= 0) {
                    this.this$0.getSeekBar().setProgress((int) positionMillis);
                }
                this.this$0.updateTimeInfo();
                if (this.this$0.getPlaybackController().getPlayStatus().isPositionChanging()) {
                    this.this$0.getRunnableDebouncer().postDelayed(this, this.this$0.SEEK_BAR_REFRESH_INTERVAL_MS);
                }
            }
        };
        View viewInflate = View.inflate(context, R.layout.bauhaus_seekbar, this);
        View viewFindViewById = viewInflate.findViewById(R.id.seekbar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "seekbarLayout.findViewBy…sicSeekBar>(R.id.seekbar)");
        setSeekBar((SeekBar) viewFindViewById);
        this.progressTime = (TextView) viewInflate.findViewById(R.id.progress_time);
        this.trackTime = (TextView) viewInflate.findViewById(R.id.track_time);
        this.audioQualityBadge = (TextView) viewInflate.findViewById(R.id.KatanaNPSBadge);
        Subscription subscriptionSubscribe = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.view.BauhausSeekBarWidget$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                BauhausSeekBarWidget.m1705_init_$lambda0(this.f$0, (StyleSheet) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(subscriptionSubscribe, "getStyleSheetObservable(…arTextStyling()\n        }");
        this.styleSheetSubscription = subscriptionSubscribe;
        getSeekBar().setMax(this.seekBarMaxProgress);
        getSeekBar().setOnSeekBarChangeListener(onSeekBarChangeListener);
        updateSeekBarVerticalPadding$default(this, null, 1, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BauhausSeekBarWidget(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, null, 0, 0);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
    }

    public final int getProgress() {
        return getSeekBar().getProgress();
    }

    public final int getMax() {
        return getSeekBar().getMax();
    }

    public final void setMax(int i) {
        getSeekBar().setMax(i);
    }

    public final SeekBar getSeekBar() {
        SeekBar seekBar = this.seekBar;
        if (seekBar != null) {
            return seekBar;
        }
        Intrinsics.throwUninitializedPropertyAccessException("seekBar");
        return null;
    }

    public final void setSeekBar(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "<set-?>");
        this.seekBar = seekBar;
    }

    public final ActionValidatedPlaybackController getPlaybackController() {
        return this.playbackController;
    }

    public final Handler getMainHandler() {
        return this.mainHandler;
    }

    public final RunnableDebouncer getRunnableDebouncer() {
        return this.runnableDebouncer;
    }

    public final void setTrackTouchListener(TrackTouchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.trackTouchListener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m1705_init_$lambda0(BauhausSeekBarWidget this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.stylesheet = styleSheet;
        this$0.setSeekbarProgressStyle();
        this$0.setSeekbarThumbStyle();
        this$0.setSeekbarTextStyling();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.styleSheetSubscription.unsubscribe();
        this.runnableDebouncer.remove(this.mUpdateSeekBarProgressRunnable);
    }

    private final void setSeekbarTextStyling() {
        FontStyle fontStyle;
        FontStyle fontStyle2;
        FontStyle fontStyleCopy$default;
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            StyleSheet styleSheet = this.stylesheet;
            Integer color = styleSheet == null ? null : styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_3);
            if (color == null) {
                return;
            }
            int iIntValue = color.intValue();
            StyleSheet stylesheet = getStylesheet();
            if (stylesheet == null || (fontStyle2 = stylesheet.getFontStyle(FontStyleKey.INDEX)) == null || (fontStyleCopy$default = FontStyle.copy$default(fontStyle2, null, null, iIntValue, null, 11, null)) == null) {
                return;
            }
            TextView textView = this.progressTime;
            if (textView != null) {
                FontUtil.INSTANCE.applyFontStyle(textView, fontStyleCopy$default);
            }
            TextView textView2 = this.trackTime;
            if (textView2 != null) {
                FontUtil.INSTANCE.applyFontStyle(textView2, fontStyleCopy$default);
            }
            TextView textView3 = this.audioQualityBadge;
            if (textView3 == null) {
                return;
            }
            FontUtil.INSTANCE.applyFontStyle(textView3, fontStyleCopy$default);
            return;
        }
        StyleSheet styleSheet2 = this.stylesheet;
        if (styleSheet2 == null || (fontStyle = styleSheet2.getFontStyle(FontStyleKey.INDEX)) == null) {
            return;
        }
        TextView textView4 = this.progressTime;
        if (textView4 != null) {
            FontUtil.INSTANCE.applyFontStyle(textView4, fontStyle);
        }
        TextView textView5 = this.trackTime;
        if (textView5 != null) {
            FontUtil.INSTANCE.applyFontStyle(textView5, fontStyle);
        }
        TextView textView6 = this.audioQualityBadge;
        if (textView6 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(textView6, fontStyle);
    }

    private final void setSeekbarProgressStyle() {
        StyleSheet styleSheet = this.stylesheet;
        Integer color = styleSheet == null ? null : styleSheet.getColor(ColorKey.COLOR_PRIMARY);
        StyleSheet styleSheet2 = this.stylesheet;
        Integer color2 = styleSheet2 != null ? styleSheet2.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_3) : null;
        Drawable progressDrawable = getSeekBar().getProgressDrawable();
        if (progressDrawable == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.LayerDrawable");
        }
        LayerDrawable layerDrawable = (LayerDrawable) progressDrawable;
        Drawable drawable = layerDrawable.getDrawable(0);
        Drawable drawable2 = layerDrawable.getDrawable(2);
        if (color != null) {
            drawable2.setColorFilter(color.intValue(), PorterDuff.Mode.SRC_IN);
        }
        if (color2 == null) {
            return;
        }
        drawable.setColorFilter(color2.intValue(), PorterDuff.Mode.SRC_IN);
    }

    private final void setSeekbarThumbStyle() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        Intrinsics.checkNotNullExpressionValue(viewTreeObserver, "this.viewTreeObserver");
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.amazon.mp3.view.BauhausSeekBarWidget.setSeekbarThumbStyle.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                Integer color;
                Drawable drawable = BauhausSeekBarWidget.this.getContext().getDrawable(R.drawable.ic_other_circle);
                int dimension = (int) BauhausSeekBarWidget.this.getContext().getResources().getDimension(R.dimen.spacer_12);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(BauhausSeekBarWidget.this.getContext().getResources(), drawable == null ? null : DrawableKt.toBitmap$default(drawable, dimension, dimension, null, 4, null));
                StyleSheet stylesheet = BauhausSeekBarWidget.this.getStylesheet();
                if (stylesheet != null && (color = stylesheet.getColor(ColorKey.COLOR_PRIMARY)) != null) {
                    bitmapDrawable.setColorFilter(color.intValue(), PorterDuff.Mode.SRC_IN);
                }
                BauhausSeekBarWidget.this.getSeekBar().setThumb(bitmapDrawable);
                BauhausSeekBarWidget.this.getSeekBar().getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
        });
    }

    public final void setSeekbarThumbSize(int size) {
        Drawable thumb = getSeekBar().getThumb();
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getContext().getResources(), thumb == null ? null : DrawableKt.toBitmap$default(thumb, size, size, null, 4, null));
        bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
        getSeekBar().setThumb(bitmapDrawable);
    }

    public final void updateSeekBar() {
        int durationMillis = (int) this.playbackController.getDurationMillis();
        if (durationMillis > 0) {
            this.seekBarMaxProgress = durationMillis;
        }
        getSeekBar().setMax(this.seekBarMaxProgress);
        if (UserSubscriptionUtil.isNightwingOnly()) {
            setEnabled(true);
        } else if (isEnabled() != this.playbackController.canSeek()) {
            this.mainHandler.post(new Runnable() { // from class: com.amazon.mp3.view.BauhausSeekBarWidget$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    BauhausSeekBarWidget.m1706updateSeekBar$lambda12(this.f$0);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updateSeekBar$lambda-12, reason: not valid java name */
    public static final void m1706updateSeekBar$lambda12(BauhausSeekBarWidget this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setEnabled(this$0.playbackController.canSeek());
    }

    public final void updateProgressBar() {
        this.runnableDebouncer.post(this.mUpdateSeekBarProgressRunnable);
    }

    public final void updateAudioQualityBadge(String badgeText, String contentDescription, StageTooltipPresenter stageTooltipPresenter) {
        if (badgeText == null) {
            hideAudioQualityBadge();
            return;
        }
        TextView textView = this.audioQualityBadge;
        if (textView == null) {
            return;
        }
        textView.setVisibility(0);
        textView.setText(badgeText);
        textView.setContentDescription(contentDescription);
    }

    public final void setAudioQualityBadgeClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        TextView textView = this.audioQualityBadge;
        if (textView == null) {
            return;
        }
        textView.setOnClickListener(onClickListener);
    }

    public final void hideAudioQualityBadge() {
        TextView textView = this.audioQualityBadge;
        if (textView == null) {
            return;
        }
        textView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTimeInfo() {
        long positionMillis;
        long durationMillis;
        synchronized (this) {
            positionMillis = getPlaybackController().getPositionMillis();
            durationMillis = getPlaybackController().getDurationMillis();
            Unit unit = Unit.INSTANCE;
        }
        long jMin = Math.min(positionMillis, durationMillis);
        long j = durationMillis - positionMillis;
        if (this.progressTime == null || this.trackTime == null) {
            return;
        }
        String cachedFormattedTime = jMin != Long.MIN_VALUE ? StringUtil.getCachedFormattedTime(jMin) : this.ZERO_TIME_STRING;
        String cachedFormattedTime2 = durationMillis != Long.MIN_VALUE ? StringUtil.getCachedFormattedTime(j) : this.DEFAULT_FORMATTED_TIME_STRING;
        TextView textView = this.progressTime;
        if (textView != null) {
            textView.setText(cachedFormattedTime);
        }
        TextView textView2 = this.trackTime;
        if (textView2 == null) {
            return;
        }
        textView2.setText(cachedFormattedTime2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onUserUpdatedSeekBar(boolean lastTouch) {
        int progress = getSeekBar().getProgress();
        if (lastTouch) {
            if (UserSubscriptionUtil.getUserSubscription().isHomeOnly()) {
                Context context = getContext();
                if (context != null) {
                    showDialog((FragmentActivity) context, new HawkfireAllDevicesUpsellDialogFragment());
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            }
            if (!this.playbackController.canSeek()) {
                Context context2 = getContext();
                if (context2 != null) {
                    DynamicUpsellTranslator.showBlockingCapabilityUpsell((FragmentActivity) context2, this.playbackController.getCurrentMediaItem(), QueueEntityCapabilityTranslator.QueueEntityCapability.SCRUB_FORWARD);
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
            }
            this.playbackController.seek(progress, 0);
        }
    }

    static /* synthetic */ void updateSeekBarVerticalPadding$default(BauhausSeekBarWidget bauhausSeekBarWidget, Configuration configuration, int i, Object obj) {
        if ((i & 1) != 0) {
            configuration = null;
        }
        bauhausSeekBarWidget.updateSeekBarVerticalPadding(configuration);
    }

    private final void updateSeekBarVerticalPadding(Configuration configuration) {
        boolean zIsPortrait = true;
        if (configuration != null) {
            if (configuration.orientation != 1) {
                zIsPortrait = false;
            }
        } else {
            zIsPortrait = ScreenUtil.isPortrait();
        }
        int dimensionPixelSize = zIsPortrait ? getResources().getDimensionPixelSize(R.dimen.spacer_16) : 0;
        SeekBar seekBar = getSeekBar();
        seekBar.setPadding(seekBar.getPaddingLeft(), dimensionPixelSize, seekBar.getPaddingRight(), dimensionPixelSize);
    }

    public final void pauseSeekbarProgressUpdate() {
        this.runnableDebouncer.remove(this.mUpdateSeekBarProgressRunnable);
    }

    public final void setLoadingState(boolean isLoading) {
        if (isLoading) {
            getSeekBar().getThumb().mutate().setAlpha(0);
            TextView textView = this.progressTime;
            if (textView != null) {
                textView.setVisibility(8);
            }
            TextView textView2 = this.trackTime;
            if (textView2 == null) {
                return;
            }
            textView2.setVisibility(8);
            return;
        }
        getSeekBar().getThumb().mutate().setAlpha(255);
        TextView textView3 = this.progressTime;
        if (textView3 != null) {
            textView3.setVisibility(0);
        }
        TextView textView4 = this.trackTime;
        if (textView4 == null) {
            return;
        }
        textView4.setVisibility(0);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateSeekBarVerticalPadding(newConfig);
    }

    public final void getSeekBarLocation(int[] intArray) {
        Intrinsics.checkNotNullParameter(intArray, "intArray");
        getSeekBar().getLocationOnScreen(intArray);
    }

    public final int getSeekBarLeftPadding() {
        return getSeekBar().getPaddingLeft();
    }

    public final int getSeekBarRightPadding() {
        return getSeekBar().getPaddingRight();
    }

    public final int getSeekBarWidth() {
        return getSeekBar().getWidth();
    }

    public final int getSeekBarHeight() {
        return getSeekBar().getHeight();
    }

    private final void showDialog(FragmentActivity activity, DialogFragment dialog) {
        FragmentTransaction fragmentTransactionBeginTransaction = activity.getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "activity.supportFragmentManager.beginTransaction()");
        fragmentTransactionBeginTransaction.add(dialog, (String) null);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendUiClickMetric(ActionType actionType) {
        PageType pageType;
        if (AmazonApplication.getCapabilities().isYAImmerseEnabled()) {
            pageType = PageType.NOW_PLAYING_STAGE;
        } else {
            pageType = PageType.NOW_PLAYING;
        }
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.DRAG).withEventTime(System.currentTimeMillis()).withPageType(pageType).build());
    }
}
