package com.amazon.music.views.library.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import androidx.core.app.NotificationCompat;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.R$anim;
import com.amazon.music.views.library.R$string;
import com.amazon.music.views.library.R$style;
import com.amazon.music.views.library.providers.HotspotTooltipListenerProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.utils.HotSpotLocation;
import com.amazon.music.views.library.views.HotSpotToolTipView;
import com.amazon.ui.foundations.utils.ScreenUtil;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: compiled from: HotSpotToolTipDialog.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u0000 b2\u00020\u0001:\u0002bcB\u001f\u0012\u0006\u0010_\u001a\u00020^\u0012\u0006\u00107\u001a\u000206\u0012\u0006\u0010'\u001a\u00020&¢\u0006\u0004\b`\u0010aJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J)\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\f\u001a\u00020\u0002H\u0002J\u0012\u0010\u000f\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0014J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u000e\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0014J\u0010\u0010\u0018\u001a\u00020\u00022\b\u0010\u0015\u001a\u0004\u0018\u00010\u0017J\u000e\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u001cJ\u000e\u0010 \u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u0004J\u0010\u0010\"\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0019H\u0016J\u0010\u0010%\u001a\u00020\u00002\u0006\u0010$\u001a\u00020#H\u0016J\u0010\u0010(\u001a\u00020\u00002\u0006\u0010'\u001a\u00020&H\u0016J\u001a\u0010+\u001a\u00020\u00002\u0006\u0010)\u001a\u00020\u00122\b\b\u0002\u0010*\u001a\u00020\u0012H\u0016J\u0016\u0010.\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00192\u0006\u0010-\u001a\u00020,J\u0010\u0010/\u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u0019H\u0016J\u0019\u00100\u001a\u00020\u00022\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b0\u00101J\u0006\u00102\u001a\u00020\u0004J\u0006\u00103\u001a\u00020\u0004J\u0006\u00104\u001a\u00020\u0004J\u0006\u00105\u001a\u00020\u0002R\u0014\u00107\u001a\u0002068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u00109R\u001b\u0010?\u001a\u00020:8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R\u0017\u0010@\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\u0014\u0010E\u001a\u00020D8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010FR\u0014\u0010H\u001a\u00020G8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010IR\u0014\u0010J\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010AR\u0014\u0010K\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010AR\u0016\u0010L\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bL\u0010AR\u0016\u0010M\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bM\u0010AR\u0016\u0010N\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bN\u0010AR\u0016\u0010O\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bO\u0010AR\u0018\u0010P\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u0010QR\u0016\u0010R\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bR\u0010AR\u0018\u0010S\u001a\u0004\u0018\u00010\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bS\u0010TR\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010UR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010VR$\u0010X\u001a\u0004\u0018\u00010W8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]¨\u0006d"}, d2 = {"Lcom/amazon/music/views/library/views/HotSpotToolTipDialog;", "Landroid/app/Dialog;", "", "setToolTipMargins", "", "anchorViewLeft", "determineHotspotXAnchor", "anchorViewTop", "dialogHeight", "overrideDialogSize", "determineHotspotYAnchor", "(IILjava/lang/Integer;)I", "setMaxWidth", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/MotionEvent;", NotificationCompat.CATEGORY_EVENT, "", "onTouchEvent", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog$ToolTipViewTouchListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnTouchToolTipViewListener", "Lcom/amazon/music/views/library/providers/HotspotTooltipListenerProvider;", "setTooltipListenerProvider", "", "hotspotTag", "setTag", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "setHotspotPageType", "tooltipOffset", "setToolTipOffset", "text", "setText", "Landroid/view/View;", "view", "setAnchorView", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog$Companion$Position;", "position", "setPosition", "show", "isToolTipButtonPressed", "toggleShowTextView", "Landroid/view/View$OnClickListener;", "onClickListener", "setButton", "setTitle", "updateDialogPosition", "(Ljava/lang/Integer;)V", "getStatusBarHeight", "getScreenWidth", "getScreenHeight", "setWindowLayoutFlags", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog$Companion$Position;", "Lcom/amazon/music/views/library/views/HotSpotToolTipView;", "hotSpotToolTipView$delegate", "Lkotlin/Lazy;", "getHotSpotToolTipView", "()Lcom/amazon/music/views/library/views/HotSpotToolTipView;", "hotSpotToolTipView", "largeSpacerDp", "I", "getLargeSpacerDp", "()I", "Landroid/graphics/Rect;", "anchorViewClickRect", "Landroid/graphics/Rect;", "", "anchorViewPosition", "[I", "screenWidth", "screenHeight", "toolTipMargin", "innerElementMargin", "toolTipArrowMargin", "centeredArrowMargin", "onTouchToolTipViewListener", "Lcom/amazon/music/views/library/views/HotSpotToolTipDialog$ToolTipViewTouchListener;", "toolTipOffset", "hotspotTooltipListenerProvider", "Lcom/amazon/music/views/library/providers/HotspotTooltipListenerProvider;", "Ljava/lang/String;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Lcom/amazon/music/views/library/utils/HotSpotLocation;", "hotSpotLocation", "Lcom/amazon/music/views/library/utils/HotSpotLocation;", "getHotSpotLocation", "()Lcom/amazon/music/views/library/utils/HotSpotLocation;", "setHotSpotLocation", "(Lcom/amazon/music/views/library/utils/HotSpotLocation;)V", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/views/HotSpotToolTipDialog$Companion$Position;)V", "Companion", "ToolTipViewTouchListener", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public class HotSpotToolTipDialog extends Dialog {
    private final Rect anchorViewClickRect;
    private final int[] anchorViewPosition;
    private int centeredArrowMargin;
    private HotSpotLocation hotSpotLocation;

    /* JADX INFO: renamed from: hotSpotToolTipView$delegate, reason: from kotlin metadata */
    private final Lazy hotSpotToolTipView;
    private String hotspotTag;
    private HotspotTooltipListenerProvider hotspotTooltipListenerProvider;
    private int innerElementMargin;
    private final int largeSpacerDp;
    private ToolTipViewTouchListener onTouchToolTipViewListener;
    private PageType pageType;
    private Companion.Position position;
    private final int screenHeight;
    private final int screenWidth;
    private final StyleSheet styleSheet;
    private int toolTipArrowMargin;
    private int toolTipMargin;
    private int toolTipOffset;

    /* JADX INFO: compiled from: HotSpotToolTipDialog.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/views/library/views/HotSpotToolTipDialog$ToolTipViewTouchListener;", "", "onTouchToolTipView", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface ToolTipViewTouchListener {
        void onTouchToolTipView(MotionEvent event);
    }

    /* JADX INFO: compiled from: HotSpotToolTipDialog.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Companion.Position.values().length];
            iArr[Companion.Position.TOP.ordinal()] = 1;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[HotSpotLocation.values().length];
            iArr2[HotSpotLocation.hotspot_on_demand_playlists.ordinal()] = 1;
            iArr2[HotSpotLocation.hotspot_search_page_top_results_shuffle_track.ordinal()] = 2;
            iArr2[HotSpotLocation.f3default.ordinal()] = 3;
            iArr2[HotSpotLocation.hotspot_on_demand_playlist_tracks.ordinal()] = 4;
            iArr2[HotSpotLocation.hotspot_search_page_top_results_ondemand_widget.ordinal()] = 5;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HotSpotToolTipDialog(final Context context, StyleSheet styleSheet, Companion.Position position) {
        super(context, R$style.dmmviewlibrary_tool_tip_dialog);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(position, "position");
        this.styleSheet = styleSheet;
        this.position = position;
        this.hotSpotToolTipView = LazyKt__LazyJVMKt.lazy(new Function0<HotSpotToolTipView>() { // from class: com.amazon.music.views.library.views.HotSpotToolTipDialog$hotSpotToolTipView$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final HotSpotToolTipView invoke() {
                return new HotSpotToolTipView(context);
            }
        });
        Integer spacerInDp = styleSheet.getSpacerInDp(SpacerKey.LARGE);
        this.largeSpacerDp = spacerInDp == null ? 0 : spacerInDp.intValue();
        this.anchorViewClickRect = new Rect();
        this.anchorViewPosition = new int[2];
        this.screenWidth = getScreenWidth();
        this.screenHeight = getScreenHeight();
        SpacerKey spacerKey = SpacerKey.SMALL;
        Integer spacerInPixels = styleSheet.getSpacerInPixels(spacerKey);
        this.toolTipMargin = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.MEDIUM);
        this.innerElementMargin = spacerInPixels2 == null ? 0 : spacerInPixels2.intValue();
        Integer spacerInPixels3 = styleSheet.getSpacerInPixels(spacerKey);
        this.toolTipArrowMargin = spacerInPixels3 == null ? 0 : spacerInPixels3.intValue();
        Integer spacerInPixels4 = styleSheet.getSpacerInPixels(SpacerKey.MINI);
        this.centeredArrowMargin = spacerInPixels4 != null ? spacerInPixels4.intValue() : 0;
    }

    public final HotSpotToolTipView getHotSpotToolTipView() {
        return (HotSpotToolTipView) this.hotSpotToolTipView.getValue();
    }

    public final int getLargeSpacerDp() {
        return this.largeSpacerDp;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getHotSpotToolTipView());
        getHotSpotToolTipView().initStyleSheet(this.styleSheet);
        setPosition(this.position);
        setToolTipMargins();
        setWindowLayoutFlags();
        setMaxWidth();
    }

    @Override // android.app.Dialog
    public boolean onTouchEvent(MotionEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        ToolTipViewTouchListener toolTipViewTouchListener = this.onTouchToolTipViewListener;
        if (toolTipViewTouchListener != null) {
            toolTipViewTouchListener.onTouchToolTipView(event);
        }
        return super.onTouchEvent(event);
    }

    public final void setOnTouchToolTipViewListener(ToolTipViewTouchListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onTouchToolTipViewListener = listener;
    }

    public final void setTooltipListenerProvider(HotspotTooltipListenerProvider listener) {
        this.hotspotTooltipListenerProvider = listener;
    }

    public final void setTag(String hotspotTag) {
        Intrinsics.checkNotNullParameter(hotspotTag, "hotspotTag");
        this.hotspotTag = hotspotTag;
    }

    public final void setHotspotPageType(PageType pageType) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        this.pageType = pageType;
    }

    public final void setToolTipOffset(int tooltipOffset) {
        this.toolTipOffset = tooltipOffset;
    }

    public HotSpotToolTipDialog setText(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getHotSpotToolTipView().setText(text);
        return this;
    }

    public HotSpotToolTipDialog setAnchorView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.anchorViewClickRect.set(0, 0, view.getWidth(), view.getHeight());
        view.getLocationOnScreen(this.anchorViewPosition);
        int[] iArr = this.anchorViewPosition;
        iArr[1] = iArr[1] - getStatusBarHeight();
        return this;
    }

    public HotSpotToolTipDialog setPosition(Companion.Position position) {
        Intrinsics.checkNotNullParameter(position, "position");
        this.position = position;
        if (WhenMappings.$EnumSwitchMapping$0[position.ordinal()] == 1) {
            getHotSpotToolTipView().setDirection(HotSpotToolTipView.Direction.TOP);
        }
        return this;
    }

    public static /* synthetic */ HotSpotToolTipDialog toggleShowTextView$default(HotSpotToolTipDialog hotSpotToolTipDialog, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toggleShowTextView");
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        return hotSpotToolTipDialog.toggleShowTextView(z, z2);
    }

    public HotSpotToolTipDialog toggleShowTextView(boolean show, boolean isToolTipButtonPressed) {
        if (show) {
            show();
            updateDialogPosition$default(this, null, 1, null);
            getHotSpotToolTipView().setVisibility(0);
            getHotSpotToolTipView().startAnimation(AnimationUtils.loadAnimation(getContext(), R$anim.dmmviewlibrary_fade_in_hotpsot));
            HotspotTooltipListenerProvider hotspotTooltipListenerProvider = this.hotspotTooltipListenerProvider;
            if (hotspotTooltipListenerProvider != null) {
                PageType pageType = this.pageType;
                if (pageType == null) {
                    pageType = PageType.UNKNOWN;
                }
                String str = this.hotspotTag;
                hotspotTooltipListenerProvider.onShowToolTipView(pageType, str != null ? str : "");
            }
        } else {
            HotspotTooltipListenerProvider hotspotTooltipListenerProvider2 = this.hotspotTooltipListenerProvider;
            if (hotspotTooltipListenerProvider2 != null) {
                PageType pageType2 = this.pageType;
                if (pageType2 == null) {
                    pageType2 = PageType.UNKNOWN;
                }
                String str2 = this.hotspotTag;
                hotspotTooltipListenerProvider2.onDismissToolTipView(isToolTipButtonPressed, pageType2, str2 != null ? str2 : "");
            }
            getHotSpotToolTipView().setVisibility(8);
            dismiss();
        }
        return this;
    }

    public final HotSpotToolTipDialog setButton(String text, View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(text, "text");
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        getHotSpotToolTipView().setButton(text, onClickListener);
        return this;
    }

    public HotSpotToolTipDialog setTitle(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        getHotSpotToolTipView().setTitle(text);
        return this;
    }

    private final void setToolTipMargins() {
        ViewGroup.LayoutParams layoutParams = getHotSpotToolTipView().getLayoutParams();
        FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
        int i = this.toolTipMargin;
        int i2 = this.toolTipArrowMargin;
        if (WhenMappings.$EnumSwitchMapping$0[this.position.ordinal()] == 1 && layoutParams2 != null) {
            layoutParams2.setMargins(i, i, i, i2);
        }
        getHotSpotToolTipView().requestLayout();
    }

    public static /* synthetic */ void updateDialogPosition$default(HotSpotToolTipDialog hotSpotToolTipDialog, Integer num, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateDialogPosition");
        }
        if ((i & 1) != 0) {
            num = null;
        }
        hotSpotToolTipDialog.updateDialogPosition(num);
    }

    public final void updateDialogPosition(Integer overrideDialogSize) {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(51);
        }
        Window window2 = getWindow();
        WindowManager.LayoutParams attributes = window2 == null ? null : window2.getAttributes();
        if (attributes == null || this.screenHeight == 0 || this.screenWidth == 0) {
            return;
        }
        if (overrideDialogSize == null) {
            getHotSpotToolTipView().measure(0, 0);
        }
        int[] iArr = this.anchorViewPosition;
        int i = iArr[0];
        int i2 = iArr[1] + this.centeredArrowMargin;
        int iDetermineHotspotXAnchor = determineHotspotXAnchor(i);
        int measuredWidth = overrideDialogSize == null ? (this.toolTipMargin * 2) + getHotSpotToolTipView().getMeasuredWidth() : overrideDialogSize.intValue();
        int measuredHeight = overrideDialogSize == null ? this.toolTipArrowMargin + getHotSpotToolTipView().getMeasuredHeight() + this.toolTipMargin : overrideDialogSize.intValue();
        int i3 = this.screenWidth - measuredWidth;
        int iCoerceIn = RangesKt___RangesKt.coerceIn(iDetermineHotspotXAnchor - (measuredWidth / 2), 0, i3 > 0 ? i3 : 1);
        if (overrideDialogSize == null) {
            getHotSpotToolTipView().setArrowPosition(((iDetermineHotspotXAnchor - iCoerceIn) - this.toolTipMargin) - this.centeredArrowMargin);
        }
        attributes.x = iCoerceIn;
        attributes.y = determineHotspotYAnchor(i2, measuredHeight, overrideDialogSize);
        if (overrideDialogSize == null) {
            getHotSpotToolTipView().invalidate();
        }
        Window window3 = getWindow();
        if (window3 == null) {
            return;
        }
        window3.setAttributes(attributes);
    }

    private final int determineHotspotXAnchor(int anchorViewLeft) {
        int iWidth;
        double dWidth;
        HotSpotLocation hotSpotLocation = this.hotSpotLocation;
        int i = hotSpotLocation == null ? -1 : WhenMappings.$EnumSwitchMapping$1[hotSpotLocation.ordinal()];
        double d = 0.75d;
        if (i == 1 || i == 2 || i == 3) {
            iWidth = this.anchorViewClickRect.width();
        } else {
            if (i == 4) {
                dWidth = this.anchorViewClickRect.width();
                d = 0.9d;
                return anchorViewLeft + ((int) (dWidth * d));
            }
            if (i == 5) {
                anchorViewLeft += this.toolTipMargin;
                iWidth = this.anchorViewClickRect.width();
            } else {
                iWidth = this.anchorViewClickRect.width();
            }
        }
        dWidth = iWidth;
        return anchorViewLeft + ((int) (dWidth * d));
    }

    private final int determineHotspotYAnchor(int anchorViewTop, int dialogHeight, Integer overrideDialogSize) {
        int i;
        int iHeight;
        if (overrideDialogSize == null) {
            HotSpotLocation hotSpotLocation = this.hotSpotLocation;
            i = hotSpotLocation != null ? WhenMappings.$EnumSwitchMapping$1[hotSpotLocation.ordinal()] : -1;
            if (i == 1) {
                iHeight = this.toolTipOffset;
            } else if (i == 2 || i == 3) {
                anchorViewTop += this.toolTipArrowMargin;
                iHeight = this.toolTipMargin;
            } else if (i == 4) {
                anchorViewTop += this.toolTipOffset;
                iHeight = this.toolTipMargin * 2;
            } else if (i == 5) {
                anchorViewTop = anchorViewTop + (this.toolTipOffset * 2) + this.toolTipArrowMargin;
                iHeight = this.anchorViewClickRect.height() / 2;
            } else {
                anchorViewTop += this.toolTipOffset;
                iHeight = this.toolTipArrowMargin;
            }
        } else {
            HotSpotLocation hotSpotLocation2 = this.hotSpotLocation;
            i = hotSpotLocation2 != null ? WhenMappings.$EnumSwitchMapping$1[hotSpotLocation2.ordinal()] : -1;
            if (i != 1 && i != 2 && i != 3 && i != 4 && i == 5) {
                anchorViewTop = (anchorViewTop - dialogHeight) - this.toolTipOffset;
                iHeight = this.anchorViewClickRect.height();
            } else {
                anchorViewTop = (anchorViewTop - dialogHeight) - this.toolTipOffset;
                iHeight = this.anchorViewClickRect.height() / 2;
            }
        }
        return anchorViewTop + iHeight;
    }

    public final int getStatusBarHeight() {
        Resources resources = getContext().getResources();
        String string = resources.getString(R$string.status_bar_height);
        Intrinsics.checkNotNullExpressionValue(string, "it.getString(R.string.status_bar_height)");
        String string2 = resources.getString(R$string.dimen);
        Intrinsics.checkNotNullExpressionValue(string2, "it.getString(R.string.dimen)");
        String string3 = resources.getString(R$string.f722android);
        Intrinsics.checkNotNullExpressionValue(string3, "it.getString(R.string.android)");
        return resources.getDimensionPixelSize(resources.getIdentifier(string, string2, string3));
    }

    public final int getScreenWidth() {
        Object systemService = getContext().getSystemService("window");
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager == null) {
            return 0;
        }
        return ScreenUtil.INSTANCE.getScreenWidthPixels(windowManager);
    }

    public final int getScreenHeight() {
        Object systemService = getContext().getSystemService("window");
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager == null) {
            return 0;
        }
        return ScreenUtil.INSTANCE.getScreenHeightPixels(windowManager);
    }

    public final void setWindowLayoutFlags() {
        Window window = getWindow();
        if (window != null) {
            window.setFlags(8, 8);
        }
        Window window2 = getWindow();
        if (window2 == null) {
            return;
        }
        window2.setFlags(262144, 262144);
    }

    private final void setMaxWidth() {
        int widthConstraints = getHotSpotToolTipView().getWidthConstraints(this.styleSheet);
        getHotSpotToolTipView().setMaxWidth(widthConstraints);
        getHotSpotToolTipView().getLayout().setMaxWidth(widthConstraints - (this.innerElementMargin * 2));
    }
}
