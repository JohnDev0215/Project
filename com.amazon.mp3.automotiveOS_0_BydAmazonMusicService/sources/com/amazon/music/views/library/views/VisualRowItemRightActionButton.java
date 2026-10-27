package com.amazon.music.views.library.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import com.amazon.music.views.library.R$drawable;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: VisualRowItemRightActionButton.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nJ\u0006\u0010\u000e\u001a\u00020\fJ\u0016\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/views/library/views/VisualRowItemRightActionButton;", "Lcom/amazon/ui/foundations/views/BaseButton;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "currentActionType", "Lcom/amazon/music/views/library/views/VisualRowItemRightActionButton$ActionType;", "hide", "", "actionType", "reset", "show", "styleBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "ActionType", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class VisualRowItemRightActionButton extends BaseButton {
    private ActionType currentActionType;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VisualRowItemRightActionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ VisualRowItemRightActionButton(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VisualRowItemRightActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: compiled from: VisualRowItemRightActionButton.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/music/views/library/views/VisualRowItemRightActionButton$ActionType;", "", "drawableId", "", "(Ljava/lang/String;II)V", "getDrawableId", "()I", "SHUFFLE", "STATION", "MISS_SD_CARD", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ActionType {
        SHUFFLE(R$drawable.ic_shuffle_ring),
        STATION(R$drawable.ic_station_ring),
        MISS_SD_CARD(R$drawable.sd_card_missing);

        private final int drawableId;

        ActionType(int i) {
            this.drawableId = i;
        }

        public final int getDrawableId() {
            return this.drawableId;
        }
    }

    public final void show(ActionType actionType, BaseButton.StyleBuilder styleBuilder) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(styleBuilder, "styleBuilder");
        if (this.currentActionType != actionType) {
            Drawable drawable = AppCompatResources.getDrawable(getContext(), actionType.getDrawableId());
            if (drawable != null) {
                styleBuilder.withIcon(drawable).applyStyle(this);
            }
            this.currentActionType = actionType;
        }
        setVisibility(0);
    }

    public final void hide(ActionType actionType) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        if (this.currentActionType == actionType) {
            setVisibility(8);
            this.currentActionType = null;
        }
    }

    public final void reset() {
        this.currentActionType = null;
        setVisibility(8);
    }
}
