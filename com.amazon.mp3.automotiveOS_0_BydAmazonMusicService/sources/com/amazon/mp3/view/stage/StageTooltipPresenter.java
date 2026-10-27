package com.amazon.mp3.view.stage;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.ToolTipDialog;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StageTooltipPresenter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 #2\u00020\u0001:\u0002#$B\u0019\u0012\u0006\u0010 \u001a\u00020\u001f\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b!\u0010\"J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0006\u0010\b\u001a\u00020\u0007J\u0006\u0010\t\u001a\u00020\u0007R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0014\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0018\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR(\u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00020\u001c0\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e¨\u0006%"}, d2 = {"Lcom/amazon/mp3/view/stage/StageTooltipPresenter;", "", "Lcom/amazon/mp3/view/stage/StageTooltipPresenter$StageTooltipType;", "tooltipType", "", "canPresentTooltip", "canPresentExperience", "", "dismissAllToolTips", "onDestroy", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "Landroid/content/SharedPreferences;", "tooltipShownPrefs$delegate", "Lkotlin/Lazy;", "getTooltipShownPrefs", "()Landroid/content/SharedPreferences;", "tooltipShownPrefs", "Lcom/amazon/music/views/library/views/ToolTipDialog;", "bauhausTooltip", "Lcom/amazon/music/views/library/views/ToolTipDialog;", "Lio/reactivex/disposables/Disposable;", "tooltipDisposable", "Lio/reactivex/disposables/Disposable;", "", "Lkotlin/Pair;", "toolTips", "Ljava/util/List;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "Companion", "StageTooltipType", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageTooltipPresenter {
    private static final String TAG;
    private ToolTipDialog bauhausTooltip;
    private final StyleSheet styleSheet;
    private List<Pair<ToolTipDialog, StageTooltipType>> toolTips;
    private Disposable tooltipDisposable;

    /* JADX INFO: renamed from: tooltipShownPrefs$delegate, reason: from kotlin metadata */
    private final Lazy tooltipShownPrefs;

    /* JADX INFO: compiled from: StageTooltipPresenter.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/view/stage/StageTooltipPresenter$StageTooltipType;", "", "(Ljava/lang/String;I)V", "CASTING_TOOLTIP", "AUDIO_QUALITY_TOOLTIP", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum StageTooltipType {
        CASTING_TOOLTIP,
        AUDIO_QUALITY_TOOLTIP
    }

    public StageTooltipPresenter(final Context context, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.styleSheet = styleSheet;
        this.tooltipShownPrefs = LazyKt__LazyJVMKt.lazy(new Function0<SharedPreferences>() { // from class: com.amazon.mp3.view.stage.StageTooltipPresenter$tooltipShownPrefs$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SharedPreferences invoke() {
                return SettingsUtil.getPrefs(context);
            }
        });
        this.toolTips = new ArrayList();
    }

    static {
        String simpleName = StageTooltipPresenter.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "StageTooltipPresenter::class.java.simpleName");
        TAG = simpleName;
    }

    private final SharedPreferences getTooltipShownPrefs() {
        Object value = this.tooltipShownPrefs.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-tooltipShownPrefs>(...)");
        return (SharedPreferences) value;
    }

    private final boolean canPresentTooltip(StageTooltipType tooltipType) {
        return canPresentExperience(tooltipType);
    }

    private final boolean canPresentExperience(StageTooltipType tooltipType) {
        return !getTooltipShownPrefs().getBoolean(tooltipType.name(), false);
    }

    public final void dismissAllToolTips() {
        for (Pair<ToolTipDialog, StageTooltipType> pair : this.toolTips) {
            if (canPresentTooltip(pair.getSecond())) {
                pair.getFirst().dismiss();
            }
        }
    }

    public final void onDestroy() {
        ToolTipDialog toolTipDialog = this.bauhausTooltip;
        if (toolTipDialog != null) {
            toolTipDialog.dismiss();
        }
        this.bauhausTooltip = null;
        Disposable disposable = this.tooltipDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.tooltipDisposable = null;
    }
}
