package com.amazon.mp3.playback;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.OneTimeExperience;
import com.amazon.mp3.util.RunnableDebouncer;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ToolTipDialog;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: SkipTooltipExperience.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b&\u0018\u0000 N2\u00020\u0001:\u0003ONPB!\b\u0016\u0012\u0006\u0010G\u001a\u00020F\u0012\u0006\u0010I\u001a\u00020H\u0012\u0006\u0010K\u001a\u00020J¢\u0006\u0004\bL\u0010MJ(\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0002J\b\u0010\u000b\u001a\u00020\u0006H\u0002J\b\u0010\f\u001a\u00020\tH\u0002J\b\u0010\r\u001a\u00020\tH\u0002JM\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J(\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0016J(\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006H\u0004J\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0006J\b\u0010\u0016\u001a\u00020\tH\u0016J\b\u0010\u0017\u001a\u00020\u0006H&J\u0018\u0010\u0018\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J\u0006\u0010\u0019\u001a\u00020\u0006J\b\u0010\u001a\u001a\u00020\tH&J\u0010\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001bH&J\b\u0010\u001e\u001a\u00020\tH&R$\u0010 \u001a\u0004\u0018\u00010\u001f8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010&\u001a\u00020\u00108\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R$\u0010,\u001a\u0004\u0018\u00010\u00108\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R$\u00102\u001a\u0004\u0018\u00010\u00108\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b2\u0010-\u001a\u0004\b3\u0010/\"\u0004\b4\u00101R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0003\u00105R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0005\u00106R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u00107R\u0016\u0010\b\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\b\u00107R\u0018\u00109\u001a\u0004\u0018\u0001088\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010;\u001a\u00020\u00108\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010'R\u001b\u0010A\u001a\u00020<8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u0014\u0010C\u001a\u00020B8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bC\u0010DR\u0014\u0010E\u001a\u00020B8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010D¨\u0006Q"}, d2 = {"Lcom/amazon/mp3/playback/SkipTooltipExperience;", "Lcom/amazon/mp3/util/OneTimeExperience;", "Landroid/view/View;", "rootView", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "", "isMiniPlayer", "inImmersiveMode", "", "showBauhausTooltip", "dismissTooltip", "removeSkipTooltipDismissTimer", "onTooltipDismissed", "hasRotatedOrientation", "fragmentIsInitializing", "", "skipsRemaining", "showTooltip", "(Landroid/view/View;ZZLcom/amazon/music/views/library/styles/StyleSheet;Ljava/lang/Integer;ZZ)V", "startShowBauhausTooltip", "dismissPermanently", "onDestroy", "canPresentTooltip", "incrementNumTooltipViews", "isTooltipShowing", "updateTooltipText", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ContainerType;", "containerType", "trackTooltipViewFlexEvent", "trackTooltipDismissFlexEvent", "Lcom/amazon/music/views/library/views/ToolTipDialog;", "bauhausTooltip", "Lcom/amazon/music/views/library/views/ToolTipDialog;", "getBauhausTooltip", "()Lcom/amazon/music/views/library/views/ToolTipDialog;", "setBauhausTooltip", "(Lcom/amazon/music/views/library/views/ToolTipDialog;)V", "maxNumTooltipViews", "I", "getMaxNumTooltipViews", "()I", "setMaxNumTooltipViews", "(I)V", "tooltipSkipsRemaining", "Ljava/lang/Integer;", "getTooltipSkipsRemaining", "()Ljava/lang/Integer;", "setTooltipSkipsRemaining", "(Ljava/lang/Integer;)V", "tooltipTextResId", "getTooltipTextResId", "setTooltipTextResId", "Landroid/view/View;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Z", "Lrx/Subscription;", "tooltipSubscription", "Lrx/Subscription;", "skipsRemainingTooltipTimeout", "Lcom/amazon/mp3/util/RunnableDebouncer;", "mRunnableDebouncer$delegate", "Lkotlin/Lazy;", "getMRunnableDebouncer", "()Lcom/amazon/mp3/util/RunnableDebouncer;", "mRunnableDebouncer", "Ljava/lang/Runnable;", "showTooltipRunnable", "Ljava/lang/Runnable;", "dismissTooltipRunnable", "Landroid/content/Context;", "context", "Lcom/amazon/mp3/playback/SkipTooltipExperience$Builder;", "builder", "", "experienceName", "<init>", "(Landroid/content/Context;Lcom/amazon/mp3/playback/SkipTooltipExperience$Builder;Ljava/lang/String;)V", "Companion", "Builder", "TooltipType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public abstract class SkipTooltipExperience extends OneTimeExperience {
    private static final String TAG;
    private ToolTipDialog bauhausTooltip;
    private final Runnable dismissTooltipRunnable;
    private boolean inImmersiveMode;
    private boolean isMiniPlayer;

    /* JADX INFO: renamed from: mRunnableDebouncer$delegate, reason: from kotlin metadata */
    private final Lazy mRunnableDebouncer;
    private int maxNumTooltipViews;
    private View rootView;
    private final Runnable showTooltipRunnable;
    private int skipsRemainingTooltipTimeout;
    private StyleSheet styleSheet;
    private Integer tooltipSkipsRemaining;
    private Subscription tooltipSubscription;
    private Integer tooltipTextResId;

    /* JADX INFO: compiled from: SkipTooltipExperience.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/playback/SkipTooltipExperience$TooltipType;", "", "(Ljava/lang/String;I)V", "SKIPS_REMAINING", "EDUCATIONAL", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum TooltipType {
        SKIPS_REMAINING,
        EDUCATIONAL
    }

    public abstract boolean canPresentTooltip();

    public void incrementNumTooltipViews(boolean hasRotatedOrientation, boolean fragmentIsInitializing) {
    }

    public abstract void trackTooltipDismissFlexEvent();

    public abstract void trackTooltipViewFlexEvent(ContainerType containerType);

    public abstract void updateTooltipText();

    protected final ToolTipDialog getBauhausTooltip() {
        return this.bauhausTooltip;
    }

    protected final void setBauhausTooltip(ToolTipDialog toolTipDialog) {
        this.bauhausTooltip = toolTipDialog;
    }

    public int getMaxNumTooltipViews() {
        return this.maxNumTooltipViews;
    }

    public void setMaxNumTooltipViews(int i) {
        this.maxNumTooltipViews = i;
    }

    protected final Integer getTooltipSkipsRemaining() {
        return this.tooltipSkipsRemaining;
    }

    public Integer getTooltipTextResId() {
        return this.tooltipTextResId;
    }

    public void setTooltipTextResId(Integer num) {
        this.tooltipTextResId = num;
    }

    private final RunnableDebouncer getMRunnableDebouncer() {
        return (RunnableDebouncer) this.mRunnableDebouncer.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkipTooltipExperience(Context context, Builder builder, String experienceName) {
        super(experienceName, context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(experienceName, "experienceName");
        this.maxNumTooltipViews = Builder.INSTANCE.getMAX_NUM_TOOLTIP_VIEWS();
        this.mRunnableDebouncer = LazyKt__LazyJVMKt.lazy(new Function0<RunnableDebouncer>() { // from class: com.amazon.mp3.playback.SkipTooltipExperience$mRunnableDebouncer$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RunnableDebouncer invoke() {
                return new RunnableDebouncer(new Handler());
            }
        });
        this.showTooltipRunnable = new Runnable() { // from class: com.amazon.mp3.playback.SkipTooltipExperience$showTooltipRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                if (this.this$0.rootView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootView");
                }
                if (this.this$0.styleSheet == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
                }
                SkipTooltipExperience skipTooltipExperience = this.this$0;
                View view = skipTooltipExperience.rootView;
                StyleSheet styleSheet = null;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootView");
                    view = null;
                }
                StyleSheet styleSheet2 = this.this$0.styleSheet;
                if (styleSheet2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
                } else {
                    styleSheet = styleSheet2;
                }
                skipTooltipExperience.showBauhausTooltip(view, styleSheet, this.this$0.isMiniPlayer, this.this$0.inImmersiveMode);
            }
        };
        this.dismissTooltipRunnable = new Runnable() { // from class: com.amazon.mp3.playback.SkipTooltipExperience$dismissTooltipRunnable$1
            @Override // java.lang.Runnable
            public void run() {
                if (this.this$0.isTooltipShowing()) {
                    this.this$0.dismissTooltip(true);
                }
            }
        };
        setMaxNumTooltipViews(builder.getMNumViews());
    }

    static {
        String simpleName = SkipTooltipExperience.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SkipTooltipExperience::class.java.simpleName");
        TAG = simpleName;
    }

    public void showTooltip(final View rootView, final boolean hasRotatedOrientation, final boolean fragmentIsInitializing, final StyleSheet styleSheet, Integer skipsRemaining, final boolean isMiniPlayer, final boolean inImmersiveMode) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        if (skipsRemaining != null) {
            this.tooltipSkipsRemaining = skipsRemaining;
        }
        this.tooltipSubscription = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.playback.SkipTooltipExperience$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SkipTooltipExperience.m1393showTooltip$lambda0(this.f$0, hasRotatedOrientation, fragmentIsInitializing, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.playback.SkipTooltipExperience$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SkipTooltipExperience.m1394showTooltip$lambda2(this.f$0, rootView, styleSheet, isMiniPlayer, inImmersiveMode, (Boolean) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.playback.SkipTooltipExperience$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SkipTooltipExperience.m1395showTooltip$lambda3((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-0, reason: not valid java name */
    public static final void m1393showTooltip$lambda0(SkipTooltipExperience this$0, boolean z, boolean z2, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.incrementNumTooltipViews(z, z2);
        subscriber.onNext(Boolean.valueOf(this$0.canPresentTooltip()));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-2, reason: not valid java name */
    public static final void m1394showTooltip$lambda2(SkipTooltipExperience this$0, View rootView, StyleSheet styleSheet, boolean z, boolean z2, Boolean success) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(rootView, "$rootView");
        Intrinsics.checkNotNullParameter(styleSheet, "$styleSheet");
        if (success == null) {
            return;
        }
        success.booleanValue();
        Intrinsics.checkNotNullExpressionValue(success, "success");
        if (success.booleanValue()) {
            this$0.showTooltip(rootView, styleSheet, z, z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-3, reason: not valid java name */
    public static final void m1395showTooltip$lambda3(Throwable th) {
        Log.error(TAG, th.toString());
    }

    public void showTooltip(View rootView, StyleSheet styleSheet, boolean isMiniPlayer, boolean inImmersiveMode) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        if (AmazonApplication.getCapabilities().isBauhausInAppMessagingEnabled() && styleSheet.getIsInitCompleted()) {
            startShowBauhausTooltip(rootView, styleSheet, isMiniPlayer, inImmersiveMode);
        }
    }

    protected final void startShowBauhausTooltip(View rootView, StyleSheet styleSheet, boolean isMiniPlayer, boolean inImmersiveMode) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        updateTooltipText();
        this.rootView = rootView;
        this.styleSheet = styleSheet;
        this.isMiniPlayer = isMiniPlayer;
        this.inImmersiveMode = inImmersiveMode;
        Integer tooltipTextResId = getTooltipTextResId();
        if (tooltipTextResId == null) {
            return;
        }
        tooltipTextResId.intValue();
        getMRunnableDebouncer().postDelayed(this.showTooltipRunnable, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showBauhausTooltip(View rootView, StyleSheet styleSheet, boolean isMiniPlayer, boolean inImmersiveMode) {
        View viewFindViewById;
        ToolTipDialog.Position position;
        ContainerType containerType;
        Window window;
        Window window2;
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog != null) {
            toolTipDialog.dismiss();
        }
        if (isMiniPlayer) {
            viewFindViewById = rootView.findViewById(R.id.MiniPlayerNextButton);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "{\n                rootVi…NextButton)\n            }");
        } else {
            viewFindViewById = rootView.findViewById(R.id.PersistentPlayerNextButton);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "{\n                rootVi…NextButton)\n            }");
        }
        if (isMiniPlayer) {
            position = ToolTipDialog.Position.TOP;
            containerType = ContainerType.MINI_TRANSPORT;
        } else {
            position = ScreenUtil.isLandscape() ? ToolTipDialog.Position.TOP : ToolTipDialog.Position.BOTTOM;
            containerType = ContainerType.NOW_PLAYING;
        }
        Integer tooltipTextResId = getTooltipTextResId();
        if (tooltipTextResId == null) {
            return;
        }
        int iIntValue = tooltipTextResId.intValue();
        Context context = viewFindViewById.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "nextButton.context");
        setBauhausTooltip(new ToolTipDialog(context, styleSheet).setPosition(position).setAnchorView(viewFindViewById).setText(iIntValue));
        ToolTipDialog bauhausTooltip = getBauhausTooltip();
        if (bauhausTooltip != null) {
            bauhausTooltip.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.mp3.playback.SkipTooltipExperience$$ExternalSyntheticLambda0
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    SkipTooltipExperience.m1392showBauhausTooltip$lambda6$lambda5(this.f$0, dialogInterface);
                }
            });
        }
        removeSkipTooltipDismissTimer();
        ToolTipDialog bauhausTooltip2 = getBauhausTooltip();
        if (bauhausTooltip2 != null) {
            bauhausTooltip2.show();
        }
        getMRunnableDebouncer().postDelayed(this.dismissTooltipRunnable, 10000L);
        if (inImmersiveMode) {
            ToolTipDialog bauhausTooltip3 = getBauhausTooltip();
            if (bauhausTooltip3 != null && (window2 = bauhausTooltip3.getWindow()) != null) {
                window2.addFlags(256);
            }
        } else {
            ToolTipDialog bauhausTooltip4 = getBauhausTooltip();
            if (bauhausTooltip4 != null && (window = bauhausTooltip4.getWindow()) != null) {
                window.clearFlags(256);
            }
        }
        trackTooltipViewFlexEvent(containerType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showBauhausTooltip$lambda-6$lambda-5, reason: not valid java name */
    public static final void m1392showBauhausTooltip$lambda6$lambda5(SkipTooltipExperience this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onTooltipDismissed();
    }

    public final void dismissTooltip(boolean dismissPermanently) {
        if (dismissTooltip() && dismissPermanently) {
            disableExperiencePresentation();
        }
    }

    private final boolean dismissTooltip() {
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog == null) {
            return false;
        }
        toolTipDialog.dismiss();
        removeSkipTooltipDismissTimer();
        Subscription subscription = this.tooltipSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.tooltipSubscription = null;
        return true;
    }

    private final void removeSkipTooltipDismissTimer() {
        getMRunnableDebouncer().remove(this.dismissTooltipRunnable);
        this.skipsRemainingTooltipTimeout = 0;
    }

    @Override // com.amazon.mp3.util.OneTimeExperience, com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator
    public void onDestroy() {
        super.onDestroy();
        getMRunnableDebouncer().remove(this.showTooltipRunnable);
        getMRunnableDebouncer().remove(this.dismissTooltipRunnable);
    }

    private final void onTooltipDismissed() {
        trackTooltipDismissFlexEvent();
        disableExperiencePresentation();
    }

    public final boolean isTooltipShowing() {
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog == null) {
            return false;
        }
        return toolTipDialog.isShowing();
    }

    /* JADX INFO: compiled from: SkipTooltipExperience.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\n\b\u0016\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\t\u0010\nR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/playback/SkipTooltipExperience$Builder;", "", "", "mNumViews", "I", "getMNumViews", "()I", "setMNumViews", "(I)V", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static class Builder {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final int MAX_NUM_TOOLTIP_VIEWS = 1;
        private int mNumViews = MAX_NUM_TOOLTIP_VIEWS;

        public final int getMNumViews() {
            return this.mNumViews;
        }

        public final void setMNumViews(int i) {
            this.mNumViews = i;
        }

        /* JADX INFO: compiled from: SkipTooltipExperience.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/playback/SkipTooltipExperience$Builder$Companion;", "", "()V", "MAX_NUM_TOOLTIP_VIEWS", "", "getMAX_NUM_TOOLTIP_VIEWS", "()I", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final int getMAX_NUM_TOOLTIP_VIEWS() {
                return Builder.MAX_NUM_TOOLTIP_VIEWS;
            }
        }
    }
}
