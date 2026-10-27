package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.views.library.R$drawable;
import com.amazon.music.views.library.R$id;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: IconPresenter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0014B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000b\u001a\u0004\u0018\u00010\nJ\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0005J\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/views/library/views/IconPresenter;", "", "buttonView", "Lcom/amazon/ui/foundations/views/BaseButton;", "buttonBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "context", "Landroid/content/Context;", "(Lcom/amazon/ui/foundations/views/BaseButton;Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;Landroid/content/Context;)V", "currentIconType", "Lcom/amazon/music/views/library/views/IconPresenter$IconType;", "getCurrentIconType", "reset", "", "setIcon", "iconType", "styleBuilder", "setOnClickListener", "onClickListener", "Landroid/view/View$OnClickListener;", "IconType", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class IconPresenter {
    private BaseButton.StyleBuilder buttonBuilder;
    private BaseButton buttonView;
    private Context context;
    private IconType currentIconType;

    public IconPresenter(BaseButton buttonView, BaseButton.StyleBuilder styleBuilder, Context context) {
        Intrinsics.checkNotNullParameter(buttonView, "buttonView");
        this.buttonView = buttonView;
        this.buttonBuilder = styleBuilder;
        this.context = context;
    }

    /* JADX INFO: compiled from: IconPresenter.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B#\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/views/library/views/IconPresenter$IconType;", "", "drawableId", "", "resourceId", "descriptionId", "(Ljava/lang/String;IIILjava/lang/Integer;)V", "getDescriptionId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getDrawableId", "()I", "getResourceId", "DOWNLOAD_ICON", "PLAY_ICON", "OVERFLOW_ICON", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum IconType {
        DOWNLOAD_ICON(R$drawable.ic_action_download, R$id.download_button_icon, null, 4, null),
        PLAY_ICON(R$drawable.ic_play_bauhaus, R$id.play_icon, null, 4, null),
        OVERFLOW_ICON(R$drawable.ic_action_more, R$id.overflow_icon, null, 4, null);

        private final Integer descriptionId;
        private final int drawableId;
        private final int resourceId;

        IconType(int i, int i2, Integer num) {
            this.drawableId = i;
            this.resourceId = i2;
            this.descriptionId = num;
        }

        /* synthetic */ IconType(int i, int i2, Integer num, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, i2, (i3 & 4) != 0 ? null : num);
        }

        public final int getDrawableId() {
            return this.drawableId;
        }

        public final int getResourceId() {
            return this.resourceId;
        }

        public final Integer getDescriptionId() {
            return this.descriptionId;
        }
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        this.buttonView.setOnClickListener(onClickListener);
    }

    public final void setIcon(IconType iconType, BaseButton.StyleBuilder styleBuilder) {
        Drawable drawable;
        Intrinsics.checkNotNullParameter(iconType, "iconType");
        Intrinsics.checkNotNullParameter(styleBuilder, "styleBuilder");
        if (this.currentIconType != iconType) {
            Context context = this.context;
            if (context != null && (drawable = AppCompatResources.getDrawable(context, iconType.getDrawableId())) != null) {
                styleBuilder.withIcon(drawable).applyStyle(this.buttonView);
            }
            this.buttonView.setId(iconType.getResourceId());
            BaseButton baseButton = this.buttonView;
            baseButton.setContentDescription(baseButton.getContext().getString(iconType.getDrawableId()));
            this.currentIconType = iconType;
            this.buttonView.setVisibility(0);
        }
    }

    public final void reset() {
        this.currentIconType = null;
        this.buttonView.setVisibility(8);
    }

    public final IconType getCurrentIconType() {
        return this.currentIconType;
    }
}
