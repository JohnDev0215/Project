package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.music.views.library.R$layout;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.IndicatorSizeKey;
import com.amazon.music.views.library.styles.keys.IndicatorStyleKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.utils.ColorUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadIndicatorView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u00100\u001a\u00020/\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b1\u00102J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\fH\u0016R\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0016R\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0016R\u001b\u0010\u001e\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001b\u0010#\u001a\u00020\u001f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u001b\u001a\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R$\u0010&\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020\u000f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R$\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00028F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u00063"}, d2 = {"Lcom/amazon/music/views/library/views/DownloadIndicatorView;", "Landroid/widget/FrameLayout;", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "downloadState", "", "updateViewsForDownloadState", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initStyleSheet", "Lcom/amazon/ui/foundations/styles/ButtonStyle;", "getErrorIndicatorStyle", "getQueueIndicatorStyle", "", "enabled", "setEnabled", "", "mProgress", "I", "mDownloadState", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "downloadErrorIconBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "downloadSuccessIconBuilder", "queueIconBuilder", "Lcom/amazon/ui/foundations/views/BaseButton;", "mBadgeImageView$delegate", "Lkotlin/Lazy;", "getMBadgeImageView", "()Lcom/amazon/ui/foundations/views/BaseButton;", "mBadgeImageView", "Landroid/widget/ProgressBar;", "mProgressBar$delegate", "getMProgressBar", "()Landroid/widget/ProgressBar;", "mProgressBar", "isDownloading", "()Z", NotificationCompat.CATEGORY_PROGRESS, "getProgress", "()I", "setProgress", "(I)V", "getDownloadState", "()Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "setDownloadState", "(Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;)V", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class DownloadIndicatorView extends FrameLayout {
    private BaseButton.StyleBuilder downloadErrorIconBuilder;
    private BaseButton.StyleBuilder downloadSuccessIconBuilder;

    /* JADX INFO: renamed from: mBadgeImageView$delegate, reason: from kotlin metadata */
    private final Lazy mBadgeImageView;
    private DownloadStateModel.DownloadState mDownloadState;
    private int mProgress;

    /* JADX INFO: renamed from: mProgressBar$delegate, reason: from kotlin metadata */
    private final Lazy mProgressBar;
    private BaseButton.StyleBuilder queueIconBuilder;

    /* JADX INFO: compiled from: DownloadIndicatorView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DownloadStateModel.DownloadState.values().length];
            iArr[DownloadStateModel.DownloadState.DOWNLOADED.ordinal()] = 1;
            iArr[DownloadStateModel.DownloadState.DOWNLOADING.ordinal()] = 2;
            iArr[DownloadStateModel.DownloadState.ERROR_DOWNLOADING.ordinal()] = 3;
            iArr[DownloadStateModel.DownloadState.QUEUING.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadIndicatorView(Context context, StyleSheet styleSheet) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.mDownloadState = DownloadStateModel.DownloadState.NO_STATE;
        this.mBadgeImageView = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.music.views.library.views.DownloadIndicatorView$mBadgeImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R$id.download_indicator_image);
            }
        });
        this.mProgressBar = LazyKt__LazyJVMKt.lazy(new Function0<ProgressBar>() { // from class: com.amazon.music.views.library.views.DownloadIndicatorView$mProgressBar$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ProgressBar invoke() {
                return (ProgressBar) this.this$0.findViewById(R$id.download_progress_bar);
            }
        });
        LayoutInflater.from(context).inflate(R$layout.dmmviewlibrary_download_button_layout, this);
        setWillNotDraw(false);
        getMProgressBar().setMax(100);
        getMProgressBar().setProgressDrawable(AppCompatResources.getDrawable(context, R$drawable.download_progress_circular));
        getMProgressBar().setVisibility(8);
        initStyleSheet(styleSheet);
        updateViewsForDownloadState(getMDownloadState());
    }

    private final BaseButton getMBadgeImageView() {
        Object value = this.mBadgeImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mBadgeImageView>(...)");
        return (BaseButton) value;
    }

    private final ProgressBar getMProgressBar() {
        Object value = this.mProgressBar.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mProgressBar>(...)");
        return (ProgressBar) value;
    }

    /* JADX INFO: renamed from: getProgress, reason: from getter */
    public final int getMProgress() {
        return this.mProgress;
    }

    public final void setProgress(int i) {
        if (this.mProgress == i) {
            return;
        }
        this.mProgress = i;
        if (i >= 100 || !isDownloading()) {
            return;
        }
        getMProgressBar().setProgress(i);
    }

    private final boolean isDownloading() {
        return this.mDownloadState == DownloadStateModel.DownloadState.DOWNLOADING;
    }

    /* JADX INFO: renamed from: getDownloadState, reason: from getter */
    public final DownloadStateModel.DownloadState getMDownloadState() {
        return this.mDownloadState;
    }

    public final void setDownloadState(DownloadStateModel.DownloadState downloadState) {
        Intrinsics.checkNotNullParameter(downloadState, "downloadState");
        if (this.mDownloadState == downloadState) {
            return;
        }
        this.mDownloadState = downloadState;
        updateViewsForDownloadState(downloadState);
    }

    private final void updateViewsForDownloadState(DownloadStateModel.DownloadState downloadState) {
        setVisibility(0);
        getMBadgeImageView().setVisibility(0);
        getMProgressBar().setVisibility(8);
        int i = WhenMappings.$EnumSwitchMapping$0[downloadState.ordinal()];
        if (i == 1) {
            BaseButton.StyleBuilder styleBuilder = this.downloadSuccessIconBuilder;
            if (styleBuilder == null) {
                return;
            }
            styleBuilder.applyStyle(getMBadgeImageView());
            return;
        }
        if (i == 2) {
            getMProgressBar().setVisibility(0);
            getMBadgeImageView().setVisibility(8);
            setVisibility(0);
            return;
        }
        if (i == 3) {
            BaseButton.StyleBuilder styleBuilder2 = this.downloadErrorIconBuilder;
            if (styleBuilder2 == null) {
                return;
            }
            styleBuilder2.applyStyle(getMBadgeImageView());
            return;
        }
        if (i == 4) {
            BaseButton.StyleBuilder styleBuilder3 = this.queueIconBuilder;
            if (styleBuilder3 == null) {
                return;
            }
            styleBuilder3.applyStyle(getMBadgeImageView());
            return;
        }
        setVisibility(8);
        getMBadgeImageView().setVisibility(8);
        getMProgressBar().setVisibility(8);
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        getMProgressBar().setEnabled(enabled);
        getMBadgeImageView().setEnabled(enabled);
    }

    private final void initStyleSheet(StyleSheet styleSheet) {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilder2;
        Drawable drawable = AppCompatResources.getDrawable(getContext(), R$drawable.ic_other_exclamation);
        if (drawable != null) {
            BaseButton.StyleBuilder indicatorBuilder = styleSheet.getIndicatorBuilder(IndicatorSizeKey.TINY, IndicatorStyleKey.DOWNLOAD);
            this.downloadErrorIconBuilder = indicatorBuilder == null ? null : indicatorBuilder.withIcon(drawable);
            ButtonStyle errorIndicatorStyle = getErrorIndicatorStyle(styleSheet);
            if (errorIndicatorStyle != null && (styleBuilder2 = this.downloadErrorIconBuilder) != null) {
                styleBuilder2.withStyle(errorIndicatorStyle);
            }
        }
        Context context = getContext();
        int i = R$drawable.ic_other_arrowdown;
        Drawable drawable2 = AppCompatResources.getDrawable(context, i);
        if (drawable2 != null) {
            BaseButton.StyleBuilder indicatorBuilder2 = styleSheet.getIndicatorBuilder(IndicatorSizeKey.TINY, IndicatorStyleKey.DOWNLOAD);
            this.downloadSuccessIconBuilder = indicatorBuilder2 == null ? null : indicatorBuilder2.withIcon(drawable2);
        }
        Drawable drawable3 = AppCompatResources.getDrawable(getContext(), i);
        if (drawable3 != null) {
            BaseButton.StyleBuilder indicatorBuilder3 = styleSheet.getIndicatorBuilder(IndicatorSizeKey.TINY, IndicatorStyleKey.DOWNLOAD);
            this.queueIconBuilder = indicatorBuilder3 != null ? indicatorBuilder3.withIcon(drawable3) : null;
            ButtonStyle queueIndicatorStyle = getQueueIndicatorStyle(styleSheet);
            if (queueIndicatorStyle != null && (styleBuilder = this.queueIconBuilder) != null) {
                styleBuilder.withStyle(queueIndicatorStyle);
            }
        }
        ButtonSize indicatorSize = styleSheet.getIndicatorSize(IndicatorSizeKey.TINY);
        if (indicatorSize == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getMProgressBar().getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
        }
        Integer width = indicatorSize.getWidth();
        if (width != null) {
            layoutParams.width = width.intValue();
        }
        Integer height = indicatorSize.getHeight();
        if (height != null) {
            layoutParams.height = height.intValue();
        }
        getMProgressBar().requestLayout();
    }

    private final ButtonStyle getErrorIndicatorStyle(StyleSheet styleSheet) {
        StatefulStyle statefulStyle;
        StatefulStyle statefulStyleCopy;
        ButtonStyle indicatorStyle;
        ColorKey colorKey = ColorKey.COLOR_ERROR;
        Integer color = styleSheet.getColor(colorKey);
        int iIntValue = color == null ? SupportMenu.CATEGORY_MASK : color.intValue();
        Integer color2 = styleSheet.getColor(colorKey, AlphaKey.GLASS_3);
        IndicatorStyleKey indicatorStyleKey = IndicatorStyleKey.DOWNLOAD;
        ButtonStyle indicatorStyle2 = styleSheet.getIndicatorStyle(indicatorStyleKey);
        if (indicatorStyle2 == null || (statefulStyle = indicatorStyle2.getStatefulStyle()) == null || (statefulStyleCopy = statefulStyle.copy((253 & 1) != 0 ? statefulStyle.color : null, (253 & 2) != 0 ? statefulStyle.pressedColor : null, (253 & 4) != 0 ? statefulStyle.focusedColor : null, (253 & 8) != 0 ? statefulStyle.disabledColor : null, (253 & 16) != 0 ? statefulStyle.backgroundColor : Integer.valueOf(iIntValue), (253 & 32) != 0 ? statefulStyle.pressedBackgroundColor : null, (253 & 64) != 0 ? statefulStyle.focusedBackgroundColor : null, (253 & 128) != 0 ? statefulStyle.disabledBackgroundColor : color2)) == null || (indicatorStyle = styleSheet.getIndicatorStyle(indicatorStyleKey)) == null) {
            return null;
        }
        return ButtonStyle.copy$default(indicatorStyle, statefulStyleCopy, null, null, null, 14, null);
    }

    private final ButtonStyle getQueueIndicatorStyle(StyleSheet styleSheet) {
        StatefulStyle statefulStyle;
        StatefulStyle statefulStyleCopy;
        ButtonStyle indicatorStyle;
        ColorKey colorKey = ColorKey.COLOR_PRIMARY;
        Integer color = styleSheet.getColor(colorKey, AlphaKey.GLASS_3);
        int iIntValue = color == null ? -1 : color.intValue();
        Integer color2 = styleSheet.getColor(colorKey, AlphaKey.GLASS_2);
        IndicatorStyleKey indicatorStyleKey = IndicatorStyleKey.PLAYBACK;
        ButtonStyle indicatorStyle2 = styleSheet.getIndicatorStyle(indicatorStyleKey);
        if (indicatorStyle2 == null || (statefulStyle = indicatorStyle2.getStatefulStyle()) == null || (statefulStyleCopy = statefulStyle.copy((253 & 1) != 0 ? statefulStyle.color : Integer.valueOf(iIntValue), (253 & 2) != 0 ? statefulStyle.pressedColor : null, (253 & 4) != 0 ? statefulStyle.focusedColor : null, (253 & 8) != 0 ? statefulStyle.disabledColor : color2, (253 & 16) != 0 ? statefulStyle.backgroundColor : 0, (253 & 32) != 0 ? statefulStyle.pressedBackgroundColor : null, (253 & 64) != 0 ? statefulStyle.focusedBackgroundColor : null, (253 & 128) != 0 ? statefulStyle.disabledBackgroundColor : 0)) == null || (indicatorStyle = styleSheet.getIndicatorStyle(indicatorStyleKey)) == null) {
            return null;
        }
        return ButtonStyle.copy$default(indicatorStyle, statefulStyleCopy, null, ColorUtil.getColorStateList$default(ColorUtil.INSTANCE, iIntValue, null, null, color2, 6, null), Integer.valueOf(getResources().getDimensionPixelSize(R$dimen.spacer_1)), 2, null);
    }
}
