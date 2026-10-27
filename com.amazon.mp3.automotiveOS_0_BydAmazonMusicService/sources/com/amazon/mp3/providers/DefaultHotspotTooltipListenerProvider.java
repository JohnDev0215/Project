package com.amazon.mp3.providers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.ui.interaction.SmoothScrollerUtils;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiContentViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.base.HotspotSupportedModel;
import com.amazon.music.views.library.models.base.HotspotSupportedView;
import com.amazon.music.views.library.providers.HotspotTooltipListenerProvider;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import com.amazon.music.views.library.views.HotSpotPulse;
import com.amazon.music.views.library.views.HotSpotToolTipDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultHotspotTooltipListenerProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010.\u001a\u0004\u0018\u00010-\u0012\b\u00103\u001a\u0004\u0018\u000102¢\u0006\u0004\bF\u0010GJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002J(\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\u0002J@\u0010\u0012\u001a*\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00100\u000fj\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0010`\u00112\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0002J!\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J)\u0010\u001a\u001a\u0004\u0018\u00010\u00152\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00182\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ)\u0010\u001c\u001a\u0004\u0018\u00010\u00152\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00182\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u001c\u0010\u001bJ\u0018\u0010\u001d\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J0\u0010\"\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J \u0010%\u001a\u00020\u00042\u0006\u0010$\u001a\u00020#2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010&\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0002H\u0016J\u0018\u0010*\u001a\u00020)2\u0006\u0010(\u001a\u00020'2\u0006\u0010!\u001a\u00020 H\u0016J\u0018\u0010,\u001a\u00020+2\u0006\u0010(\u001a\u00020'2\u0006\u0010!\u001a\u00020 H\u0016R\u0019\u0010.\u001a\u0004\u0018\u00010-8\u0006¢\u0006\f\n\u0004\b.\u0010/\u001a\u0004\b0\u00101R\u0019\u00103\u001a\u0004\u0018\u0001028\u0006¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106R\u0014\u00108\u001a\u0002078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b8\u00109R\u0014\u0010:\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b:\u0010;R\u0014\u0010<\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\b<\u0010;R\u001b\u0010A\u001a\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@R\u001b\u0010D\u001a\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bB\u0010>\u001a\u0004\bC\u0010@R\u0014\u0010E\u001a\u00020\u00028\u0002X\u0082D¢\u0006\u0006\n\u0004\bE\u0010;¨\u0006H"}, d2 = {"Lcom/amazon/mp3/providers/DefaultHotspotTooltipListenerProvider;", "Lcom/amazon/music/views/library/providers/HotspotTooltipListenerProvider;", "", "hotspotTag", "", "disableHotspot", "hotSpotContentName", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ContainerType;", "containerType", "trackHotSpotContentView", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "trackHotSpotUIClickEvent", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getContentInfo", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "targetViewModel", "", "getScrollTargetPosition", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/views/library/models/base/BaseViewModel;)Ljava/lang/Integer;", "", "models", "findSearchHotspotIndexes", "(Ljava/util/List;Lcom/amazon/music/views/library/models/base/BaseViewModel;)Ljava/lang/Integer;", "findBrowseHomeHotspotIndexes", "onShowHotspotView", "Lcom/amazon/music/views/library/views/HotSpotPulse;", "hotspot", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog;", "tooltip", "onClickHotspotView", "", "isToolTipButtonPressed", "onDismissToolTipView", "onShowToolTipView", "Lcom/amazon/music/views/library/models/base/HotspotSupportedView;", "view", "Landroid/view/View$OnClickListener;", "tooltipButtonOnClick", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog$ToolTipViewTouchListener;", "tooltipBackgroundOnClick", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroidx/recyclerview/widget/RecyclerView;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "Landroid/content/SharedPreferences;", "prefs", "Landroid/content/SharedPreferences;", "HOT_SPOT_TOOL_TIP_PREFIX", "Ljava/lang/String;", "HOT_SPOT_PULSE_PREFIX", "HOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX$delegate", "Lkotlin/Lazy;", "getHOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX", "()Ljava/lang/String;", "HOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX", "HOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX$delegate", "getHOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX", "HOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX", "CONTENT_INFO_NUM_ITEMS_ACCESSIBLE", "<init>", "(Landroid/content/Context;Landroidx/recyclerview/widget/RecyclerView;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DefaultHotspotTooltipListenerProvider implements HotspotTooltipListenerProvider {
    private final String CONTENT_INFO_NUM_ITEMS_ACCESSIBLE;

    /* JADX INFO: renamed from: HOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX$delegate, reason: from kotlin metadata */
    private final Lazy HOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX;
    private final String HOT_SPOT_PULSE_PREFIX;

    /* JADX INFO: renamed from: HOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX$delegate, reason: from kotlin metadata */
    private final Lazy HOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX;
    private final String HOT_SPOT_TOOL_TIP_PREFIX;
    private final Context context;
    private final SharedPreferences prefs;
    private final RecyclerView recyclerView;

    /* JADX INFO: compiled from: DefaultHotspotTooltipListenerProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PageType.values().length];
            iArr[PageType.BROWSE_HOME.ordinal()] = 1;
            iArr[PageType.SEARCH_RESULTS.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public DefaultHotspotTooltipListenerProvider(Context context, RecyclerView recyclerView) {
        SharedPreferences prefs;
        String str;
        this.context = context;
        this.recyclerView = recyclerView;
        if (context != null) {
            prefs = SettingsUtil.getPrefs(context);
            str = "getPrefs(context)";
        } else {
            prefs = SettingsUtil.getPrefs(AmazonApplication.getApplication().getApplicationContext());
            str = "getPrefs(AmazonApplicati…ion().applicationContext)";
        }
        Intrinsics.checkNotNullExpressionValue(prefs, str);
        this.prefs = prefs;
        this.HOT_SPOT_TOOL_TIP_PREFIX = "HotSpotToolTip";
        this.HOT_SPOT_PULSE_PREFIX = "HotSpotPulse";
        this.HOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.providers.DefaultHotspotTooltipListenerProvider$HOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Resources resources;
                Context context2 = this.this$0.getContext();
                String string = null;
                if (context2 != null && (resources = context2.getResources()) != null) {
                    string = resources.getString(R.string.dmusic_hotspot_tooltip);
                }
                if (string == null) {
                    string = this.this$0.HOT_SPOT_TOOL_TIP_PREFIX;
                }
                Intrinsics.checkNotNullExpressionValue(string, "context?.resources?.getS… HOT_SPOT_TOOL_TIP_PREFIX");
                return string;
            }
        });
        this.HOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.amazon.mp3.providers.DefaultHotspotTooltipListenerProvider$HOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Resources resources;
                Context context2 = this.this$0.getContext();
                String string = null;
                if (context2 != null && (resources = context2.getResources()) != null) {
                    string = resources.getString(R.string.dmusic_hotspot_pulse);
                }
                if (string == null) {
                    string = this.this$0.HOT_SPOT_PULSE_PREFIX;
                }
                Intrinsics.checkNotNullExpressionValue(string, "context?.resources?.getS… ?: HOT_SPOT_PULSE_PREFIX");
                return string;
            }
        });
        this.CONTENT_INFO_NUM_ITEMS_ACCESSIBLE = "1";
    }

    public final Context getContext() {
        return this.context;
    }

    private final String getHOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX() {
        return (String) this.HOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX.getValue();
    }

    private final String getHOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX() {
        return (String) this.HOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX.getValue();
    }

    @Override // com.amazon.music.views.library.providers.HotspotTooltipListenerProvider
    public void onShowHotspotView(PageType pageType, String hotspotTag) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(hotspotTag, "hotspotTag");
        trackHotSpotContentView(getHOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX() + '-' + hotspotTag, pageType, ContainerType.DIALOG);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.music.views.library.providers.HotspotTooltipListenerProvider
    public void onClickHotspotView(PageType pageType, String hotspotTag, HotSpotPulse hotspot, HotSpotToolTipDialog tooltip, BaseViewModel targetViewModel) {
        RecyclerView.LayoutManager layoutManager;
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(hotspotTag, "hotspotTag");
        Intrinsics.checkNotNullParameter(hotspot, "hotspot");
        Intrinsics.checkNotNullParameter(tooltip, "tooltip");
        Intrinsics.checkNotNullParameter(targetViewModel, "targetViewModel");
        trackHotSpotUIClickEvent(getHOT_SPOT_PULSE_METRICS_CONTENT_NAME_PREFIX() + '-' + hotspotTag, ActionType.DYNAMIC_MESSAGE_CTA_CLICK, pageType, ContainerType.DIALOG);
        Integer scrollTargetPosition = getScrollTargetPosition(pageType, targetViewModel);
        LinearSmoothScroller linearSmoothScrollerCreateHotspotSmoothScroller = SmoothScrollerUtils.INSTANCE.createHotspotSmoothScroller(this.context, this.recyclerView, hotspot, tooltip);
        if (scrollTargetPosition != null) {
            if (linearSmoothScrollerCreateHotspotSmoothScroller != null) {
                linearSmoothScrollerCreateHotspotSmoothScroller.setTargetPosition(scrollTargetPosition.intValue());
            }
            RecyclerView recyclerView = this.recyclerView;
            if (recyclerView != null && (layoutManager = recyclerView.getLayoutManager()) != null) {
                layoutManager.startSmoothScroll(linearSmoothScrollerCreateHotspotSmoothScroller);
            }
        }
        HotspotSupportedModel hotspotSupportedModel = targetViewModel instanceof HotspotSupportedModel ? (HotspotSupportedModel) targetViewModel : null;
        if (hotspotSupportedModel == null) {
            return;
        }
        hotspotSupportedModel.setHotspotTooltipModel(null);
    }

    @Override // com.amazon.music.views.library.providers.HotspotTooltipListenerProvider
    public void onDismissToolTipView(boolean isToolTipButtonPressed, PageType pageType, String hotspotTag) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(hotspotTag, "hotspotTag");
        disableHotspot(hotspotTag);
        if (isToolTipButtonPressed) {
            trackHotSpotUIClickEvent(getHOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX() + '-' + hotspotTag, ActionType.CTA_DISMISS, pageType, ContainerType.DIALOG);
        }
    }

    @Override // com.amazon.music.views.library.providers.HotspotTooltipListenerProvider
    public void onShowToolTipView(PageType pageType, String hotspotTag) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(hotspotTag, "hotspotTag");
        trackHotSpotContentView(getHOT_SPOT_TOOL_TIP_METRICS_CONTENT_NAME_PREFIX() + '-' + hotspotTag, pageType, ContainerType.DIALOG);
    }

    @Override // com.amazon.music.views.library.providers.HotspotTooltipListenerProvider
    public View.OnClickListener tooltipButtonOnClick(final HotspotSupportedView view, final HotSpotToolTipDialog tooltip) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(tooltip, "tooltip");
        return new View.OnClickListener() { // from class: com.amazon.mp3.providers.DefaultHotspotTooltipListenerProvider$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DefaultHotspotTooltipListenerProvider.m1604tooltipButtonOnClick$lambda0(tooltip, view, view2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: tooltipButtonOnClick$lambda-0, reason: not valid java name */
    public static final void m1604tooltipButtonOnClick$lambda0(HotSpotToolTipDialog tooltip, HotspotSupportedView view, View view2) {
        Intrinsics.checkNotNullParameter(tooltip, "$tooltip");
        Intrinsics.checkNotNullParameter(view, "$view");
        tooltip.toggleShowTextView(false, true);
        view.getHotSpotPulseView().setVisibility(8);
    }

    @Override // com.amazon.music.views.library.providers.HotspotTooltipListenerProvider
    public HotSpotToolTipDialog.ToolTipViewTouchListener tooltipBackgroundOnClick(final HotspotSupportedView view, final HotSpotToolTipDialog tooltip) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(tooltip, "tooltip");
        return new HotSpotToolTipDialog.ToolTipViewTouchListener() { // from class: com.amazon.mp3.providers.DefaultHotspotTooltipListenerProvider$tooltipBackgroundOnClick$toolTipViewTouchListener$1
            @Override // com.amazon.music.views.library.views.HotSpotToolTipDialog.ToolTipViewTouchListener
            public void onTouchToolTipView(MotionEvent event) {
                Intrinsics.checkNotNullParameter(event, "event");
                if (event.getAction() == 4) {
                    tooltip.toggleShowTextView(false, false);
                    view.getHotSpotPulseView().setVisibility(8);
                }
            }
        };
    }

    private final void disableHotspot(String hotspotTag) {
        SharedPreferences.Editor editorEdit = this.prefs.edit();
        editorEdit.putBoolean(hotspotTag, true);
        editorEdit.apply();
    }

    private final void trackHotSpotContentView(String hotSpotContentName, PageType pageType, ContainerType containerType) {
        MetricsLogger.sendEvent(UiContentViewEvent.builder().withPageType(pageType.getMetricsValue()).withContentInfo(getContentInfo(hotSpotContentName, containerType)).build());
    }

    private final void trackHotSpotUIClickEvent(String hotSpotContentName, ActionType actionType, PageType pageType, ContainerType containerType) {
        MetricsLogger.sendEvent(UiClickEvent.builder(actionType).withPageType(pageType).withInteractionType(InteractionType.TAP).withContentInfo(getContentInfo(hotSpotContentName, containerType)).withEventTime(System.currentTimeMillis()).build());
    }

    private final ArrayList<Map<String, String>> getContentInfo(String hotSpotContentName, ContainerType containerType) {
        ArrayList<Map<String, String>> arrayList = new ArrayList<>();
        HashMap map = new HashMap();
        map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        map.put("contentName", hotSpotContentName);
        map.put("containerType", containerType.toString());
        map.put("numItemsAccessible", this.CONTENT_INFO_NUM_ITEMS_ACCESSIBLE);
        map.put("impressionTimestamp", String.valueOf(System.currentTimeMillis()));
        arrayList.add(map);
        return arrayList;
    }

    private final Integer getScrollTargetPosition(PageType pageType, BaseViewModel targetViewModel) {
        RecyclerView recyclerView = this.recyclerView;
        if (!((recyclerView == null ? null : recyclerView.getAdapter()) instanceof UniversalAdapter)) {
            return null;
        }
        RecyclerView recyclerView2 = this.recyclerView;
        RecyclerView.Adapter adapter = recyclerView2 == null ? null : recyclerView2.getAdapter();
        if (adapter == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter");
        }
        List<Object> items = ((UniversalAdapter) adapter).getItems();
        int i = WhenMappings.$EnumSwitchMapping$0[pageType.ordinal()];
        if (i == 1) {
            return findBrowseHomeHotspotIndexes(items instanceof List ? items : null, targetViewModel);
        }
        if (i != 2) {
            return null;
        }
        return findSearchHotspotIndexes(items instanceof List ? items : null, targetViewModel);
    }

    private final Integer findBrowseHomeHotspotIndexes(List<? extends BaseViewModel> models, BaseViewModel targetViewModel) {
        Resources resources;
        Configuration configuration;
        if (models == null) {
            return null;
        }
        int size = models.size();
        boolean z = false;
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            if (models.get(i).equals(targetViewModel)) {
                if (i != 0) {
                    Context context = this.context;
                    if (context != null && (resources = context.getResources()) != null && (configuration = resources.getConfiguration()) != null && configuration.orientation == 2) {
                        z = true;
                    }
                    if (!z) {
                        return Integer.valueOf(i - 1);
                    }
                }
                return Integer.valueOf(i);
            }
            i = i2;
        }
        return null;
    }

    private final Integer findSearchHotspotIndexes(List<? extends BaseViewModel> models, BaseViewModel targetViewModel) {
        List<BaseViewModel> models2;
        Resources resources;
        Configuration configuration;
        if (models == null) {
            return null;
        }
        boolean z = false;
        int i = 0;
        for (Object obj : models) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            BaseViewModel baseViewModel = (BaseViewModel) obj;
            SectionGridViewModel sectionGridViewModel = baseViewModel instanceof SectionGridViewModel ? (SectionGridViewModel) baseViewModel : null;
            if (sectionGridViewModel != null && (models2 = sectionGridViewModel.getModels()) != null) {
                Iterator<T> it = models2.iterator();
                while (it.hasNext()) {
                    String viewReferenceId = ((BaseViewModel) it.next()).getViewReferenceId();
                    if (viewReferenceId != null && Intrinsics.areEqual(targetViewModel.getViewReferenceId(), viewReferenceId)) {
                        if (i != 0) {
                            Context context = getContext();
                            if (context != null && (resources = context.getResources()) != null && (configuration = resources.getConfiguration()) != null && configuration.orientation == 2) {
                                z = true;
                            }
                            if (!z) {
                                return Integer.valueOf(i - 1);
                            }
                        }
                        return Integer.valueOf(i);
                    }
                }
            }
            i = i2;
        }
        return null;
    }
}
