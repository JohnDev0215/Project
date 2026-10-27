package com.amazon.mp3.brush;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.brush.BauhausTooltipExperience;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.OneTimeExperience;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.ToolTipDialog;
import com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BauhausTooltipExperience.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 B2\u00020\u00012\u00020\u0002:\u0002BCBq\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017J\u0016\u0010'\u001a\u00020\"2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0002J\u0006\u0010+\u001a\u00020\u0016J\b\u0010,\u001a\u00020\u0016H\u0002J\u0010\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020\u0016H\u0016J8\u0010/\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040100j\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000401`22\u0006\u00103\u001a\u00020\u0004H\u0002J\b\u00104\u001a\u00020\u0014H\u0002J\u0010\u00105\u001a\u00020\u00142\u0006\u00106\u001a\u000207H\u0002J\u0010\u00108\u001a\u00020\u00142\u0006\u00106\u001a\u000207H\u0002J\b\u00109\u001a\u00020*H\u0002J\b\u0010:\u001a\u00020*H\u0016J\u000e\u0010;\u001a\u00020*2\u0006\u0010<\u001a\u00020\u0011J \u0010=\u001a\u00020*2\u0006\u0010>\u001a\u00020\u00162\u0006\u00106\u001a\u0002072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007J\u0010\u0010>\u001a\u00020*2\u0006\u00106\u001a\u000207H\u0002J\u001a\u0010?\u001a\u00020*2\u0006\u00106\u001a\u0002072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010@\u001a\u00020*2\u0006\u0010A\u001a\u00020\u0004H\u0002R\u001b\u0010\u0018\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001d\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/amazon/mp3/brush/BauhausTooltipExperience;", "Lcom/amazon/music/views/library/views/presentation/TooltipExperienceDecorator;", "Lcom/amazon/mp3/util/OneTimeExperience;", "tooltipStateKey", "", "tooltipText", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "context", "Landroid/content/Context;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "metricsContentName", "pageContainer", "Landroidx/recyclerview/widget/RecyclerView;", "headerContainer", "containerCallbacks", "Lcom/amazon/mp3/brush/BauhausTooltipExperience$ContainerCallbacks;", "containerIndex", "maxNumPageView", "", "updatePageViewOnShowAndDisableToolTip", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/amazon/music/views/library/styles/StyleSheet;Landroid/content/Context;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Ljava/lang/String;Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView;Lcom/amazon/mp3/brush/BauhausTooltipExperience$ContainerCallbacks;Ljava/lang/String;IZ)V", "TOOL_TIP_METRICS_CONTENT_NAME_PREFIX", "getTOOL_TIP_METRICS_CONTENT_NAME_PREFIX", "()Ljava/lang/String;", "TOOL_TIP_METRICS_CONTENT_NAME_PREFIX$delegate", "Lkotlin/Lazy;", "TOOL_TIP_PREFIX", "bauhausTooltip", "Lcom/amazon/music/views/library/views/ToolTipDialog;", "bottomMargin", "headerContainerTouchListener", "Landroidx/recyclerview/widget/RecyclerView$OnItemTouchListener;", "leftMargin", "pageContainerTouchListener", "tooltipDisposable", "Lio/reactivex/disposables/Disposable;", "attachOnTouchListener", "callback", "Lkotlin/Function0;", "", "canPresentTooltip", "dismissExperience", "dismissTooltip", "dismissPermanently", "getContentInfo", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "tooltipContentName", "getNumTooltipViews", "getOffsetX", "view", "Landroid/view/View;", "getOffsetY", "incrementTooltipViewCount", "onDestroy", "setContainerCallbacks", "callbacks", "setVisibility", "show", "showTooltip", "trackTooltipContentView", "contentName", "Companion", "ContainerCallbacks", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BauhausTooltipExperience extends OneTimeExperience implements TooltipExperienceDecorator {
    private static final String TAG;

    /* JADX INFO: renamed from: TOOL_TIP_METRICS_CONTENT_NAME_PREFIX$delegate, reason: from kotlin metadata */
    private final Lazy TOOL_TIP_METRICS_CONTENT_NAME_PREFIX;
    private final String TOOL_TIP_PREFIX;
    private ToolTipDialog bauhausTooltip;
    private int bottomMargin;
    private ContainerCallbacks containerCallbacks;
    private final String containerIndex;
    private final RecyclerView headerContainer;
    private RecyclerView.OnItemTouchListener headerContainerTouchListener;
    private int leftMargin;
    private final int maxNumPageView;
    private final String metricsContentName;
    private final RecyclerView pageContainer;
    private RecyclerView.OnItemTouchListener pageContainerTouchListener;
    private final PageType pageType;
    private final StyleSheet styleSheet;
    private Disposable tooltipDisposable;
    private final String tooltipStateKey;
    private final String tooltipText;
    private final boolean updatePageViewOnShowAndDisableToolTip;

    /* JADX INFO: compiled from: BauhausTooltipExperience.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/brush/BauhausTooltipExperience$ContainerCallbacks;", "", "headerContainerCallback", "", "pageContainerCallback", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface ContainerCallbacks {
        void headerContainerCallback();

        void pageContainerCallback();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BauhausTooltipExperience(String tooltipStateKey, String tooltipText, StyleSheet styleSheet, final Context context, PageType pageType, String metricsContentName, RecyclerView recyclerView, RecyclerView recyclerView2, ContainerCallbacks containerCallbacks, String containerIndex, int i, boolean z) {
        super(Intrinsics.stringPlus(tooltipStateKey, "-name"), context);
        Intrinsics.checkNotNullParameter(tooltipStateKey, "tooltipStateKey");
        Intrinsics.checkNotNullParameter(tooltipText, "tooltipText");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(metricsContentName, "metricsContentName");
        Intrinsics.checkNotNullParameter(containerIndex, "containerIndex");
        this.tooltipStateKey = tooltipStateKey;
        this.tooltipText = tooltipText;
        this.styleSheet = styleSheet;
        this.pageType = pageType;
        this.metricsContentName = metricsContentName;
        this.pageContainer = recyclerView;
        this.headerContainer = recyclerView2;
        this.containerCallbacks = containerCallbacks;
        this.containerIndex = containerIndex;
        this.maxNumPageView = i;
        this.updatePageViewOnShowAndDisableToolTip = z;
        this.TOOL_TIP_PREFIX = "ToolTip";
        this.TOOL_TIP_METRICS_CONTENT_NAME_PREFIX = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.brush.BauhausTooltipExperience$TOOL_TIP_METRICS_CONTENT_NAME_PREFIX$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Resources resources;
                Context context2 = context;
                String string = null;
                if (context2 != null && (resources = context2.getResources()) != null) {
                    string = resources.getString(R.string.dmusic_tooltip);
                }
                if (string == null) {
                    string = this.TOOL_TIP_PREFIX;
                }
                Intrinsics.checkNotNullExpressionValue(string, "context?.resources?.getS…oltip) ?: TOOL_TIP_PREFIX");
                return string;
            }
        });
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.HUGE);
        this.leftMargin = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        this.bottomMargin = spacerInPixels2 != null ? spacerInPixels2.intValue() : 0;
        this.pageContainerTouchListener = recyclerView == null ? null : attachOnTouchListener(new Function0<Unit>() { // from class: com.amazon.mp3.brush.BauhausTooltipExperience$1$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                BauhausTooltipExperience.ContainerCallbacks containerCallbacks2 = this.this$0.containerCallbacks;
                if (containerCallbacks2 == null) {
                    return;
                }
                containerCallbacks2.pageContainerCallback();
            }
        });
        this.headerContainerTouchListener = recyclerView2 != null ? attachOnTouchListener(new Function0<Unit>() { // from class: com.amazon.mp3.brush.BauhausTooltipExperience$2$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                BauhausTooltipExperience.ContainerCallbacks containerCallbacks2 = this.this$0.containerCallbacks;
                if (containerCallbacks2 == null) {
                    return;
                }
                containerCallbacks2.headerContainerCallback();
            }
        }) : null;
        RecyclerView.OnItemTouchListener onItemTouchListener = this.pageContainerTouchListener;
        if (onItemTouchListener != null && recyclerView != null) {
            recyclerView.addOnItemTouchListener(onItemTouchListener);
        }
        RecyclerView.OnItemTouchListener onItemTouchListener2 = this.headerContainerTouchListener;
        if (onItemTouchListener2 == null || recyclerView2 == null) {
            return;
        }
        recyclerView2.addOnItemTouchListener(onItemTouchListener2);
    }

    static {
        String simpleName = BauhausTooltipExperience.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "BauhausTooltipExperience::class.java.simpleName");
        TAG = simpleName;
    }

    private final String getTOOL_TIP_METRICS_CONTENT_NAME_PREFIX() {
        return (String) this.TOOL_TIP_METRICS_CONTENT_NAME_PREFIX.getValue();
    }

    public final void setContainerCallbacks(ContainerCallbacks callbacks) {
        Intrinsics.checkNotNullParameter(callbacks, "callbacks");
        this.containerCallbacks = callbacks;
    }

    private final RecyclerView.OnItemTouchListener attachOnTouchListener(final Function0<Unit> callback) {
        return new RecyclerView.OnItemTouchListener() { // from class: com.amazon.mp3.brush.BauhausTooltipExperience.attachOnTouchListener.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                Intrinsics.checkNotNullParameter(rv, "rv");
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                RecyclerView recyclerView;
                RecyclerView recyclerView2;
                Intrinsics.checkNotNullParameter(rv, "rv");
                Intrinsics.checkNotNullParameter(e, "e");
                if (e.getAction() != 1 && e.getAction() != 2) {
                    return false;
                }
                if (e.getAction() == 1) {
                    callback.invoke();
                } else {
                    callback.invoke();
                }
                RecyclerView.OnItemTouchListener onItemTouchListener = this.pageContainerTouchListener;
                if (onItemTouchListener != null && (recyclerView2 = this.pageContainer) != null) {
                    recyclerView2.removeOnItemTouchListener(onItemTouchListener);
                }
                RecyclerView.OnItemTouchListener onItemTouchListener2 = this.headerContainerTouchListener;
                if (onItemTouchListener2 == null || (recyclerView = this.headerContainer) == null) {
                    return false;
                }
                recyclerView.removeOnItemTouchListener(onItemTouchListener2);
                return false;
            }
        };
    }

    @Override // com.amazon.mp3.util.OneTimeExperience, com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator
    public void onDestroy() {
        this.bauhausTooltip = null;
        Disposable disposable = this.tooltipDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.tooltipDisposable = null;
        super.onDestroy();
    }

    @Override // com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator
    public void showTooltip(final View view, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.tooltipDisposable = Observable.create(new ObservableOnSubscribe() { // from class: com.amazon.mp3.brush.BauhausTooltipExperience$$ExternalSyntheticLambda0
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                BauhausTooltipExperience.m432showTooltip$lambda4(this.f$0, observableEmitter);
            }
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.brush.BauhausTooltipExperience$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                BauhausTooltipExperience.m433showTooltip$lambda6(this.f$0, view, (Boolean) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.brush.BauhausTooltipExperience$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                BauhausTooltipExperience.m434showTooltip$lambda7((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-4, reason: not valid java name */
    public static final void m432showTooltip$lambda4(BauhausTooltipExperience this$0, ObservableEmitter emitter) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        emitter.onNext(Boolean.valueOf(this$0.canPresentTooltip()));
        emitter.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-6, reason: not valid java name */
    public static final void m433showTooltip$lambda6(BauhausTooltipExperience this$0, View view, Boolean success) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(view, "$view");
        if (success == null) {
            return;
        }
        success.booleanValue();
        Intrinsics.checkNotNullExpressionValue(success, "success");
        if (success.booleanValue()) {
            this$0.show(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showTooltip$lambda-7, reason: not valid java name */
    public static final void m434showTooltip$lambda7(Throwable th) {
        Log.error(TAG, th.toString());
    }

    public final void setVisibility(boolean show, View view, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (show) {
            showTooltip(view, styleSheet);
        } else {
            dismissExperience();
        }
    }

    private final void show(View view) {
        StyleSheet styleSheet = this.styleSheet;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "view.context");
        this.bauhausTooltip = new ToolTipDialog(context, styleSheet).setPosition(ToolTipDialog.Position.BOTTOM).setAnchorPoint(getOffsetX(view), getOffsetY(view)).setText(this.tooltipText);
        if (this.updatePageViewOnShowAndDisableToolTip) {
            dismissTooltip(false);
        }
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog != null) {
            toolTipDialog.show();
        }
        trackTooltipContentView(getTOOL_TIP_METRICS_CONTENT_NAME_PREFIX() + '-' + this.metricsContentName);
        ToolTipDialog toolTipDialog2 = this.bauhausTooltip;
        Window window = toolTipDialog2 == null ? null : toolTipDialog2.getWindow();
        WindowManager.LayoutParams attributes = window != null ? window.getAttributes() : null;
        if (attributes != null) {
            attributes.flags = 262168;
        }
        if (window == null) {
            return;
        }
        window.setAttributes(attributes);
    }

    private final int getOffsetX(View view) {
        return view.getLeft() + (this.leftMargin * 2);
    }

    private final int getOffsetY(View view) {
        return view.getBottom() - this.bottomMargin;
    }

    public final boolean canPresentTooltip() {
        return canPresentExperience();
    }

    private final int getNumTooltipViews() {
        return getPrefs().getInt(this.tooltipStateKey, 0);
    }

    @Override // com.amazon.music.views.library.views.presentation.TooltipExperienceDecorator
    public void dismissTooltip(boolean dismissPermanently) {
        if (dismissExperience()) {
            incrementTooltipViewCount();
            int numTooltipViews = getNumTooltipViews();
            if (dismissPermanently || numTooltipViews >= this.maxNumPageView) {
                disableExperiencePresentation();
            }
        }
    }

    private final boolean dismissExperience() {
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog == null) {
            return false;
        }
        toolTipDialog.dismiss();
        return true;
    }

    private final void incrementTooltipViewCount() {
        int numTooltipViews = getNumTooltipViews() + 1;
        SharedPreferences.Editor editorEdit = getPrefs().edit();
        editorEdit.putInt(this.tooltipStateKey, numTooltipViews);
        editorEdit.apply();
    }

    private final void trackTooltipContentView(String contentName) {
        Log.info(TAG, Intrinsics.stringPlus("Sending Content View Event for ", contentName));
        MetricsLogger.sendEvent(UiContentViewEvent.builder().withPageType(this.pageType.getMetricsValue()).withContentInfo(getContentInfo(contentName)).build());
    }

    private final ArrayList<Map<String, String>> getContentInfo(String tooltipContentName) {
        ArrayList<Map<String, String>> arrayList = new ArrayList<>();
        HashMap map = new HashMap();
        map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        map.put("contentName", tooltipContentName);
        map.put("containerIndex", this.containerIndex);
        map.put("containerType", ContainerType.DIALOG.toString());
        map.put("numItemsAccessible", "1");
        map.put("impressionTimestamp", String.valueOf(System.currentTimeMillis()));
        arrayList.add(map);
        return arrayList;
    }
}
