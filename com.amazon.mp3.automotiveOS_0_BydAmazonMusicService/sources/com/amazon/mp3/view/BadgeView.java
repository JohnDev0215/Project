package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.amazon.mp3.view.model.BadgeModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BadgeView.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/view/BadgeView;", "Landroid/widget/TextView;", "", "iconId", "", "setBadgeIcon", "", "isVisible", "setBadgeVisiblility", "Lcom/amazon/mp3/view/model/BadgeModel;", "badgeModel", "populate", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BadgeView extends TextView {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BadgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attrs, "attrs");
    }

    public final void setBadgeVisiblility(boolean isVisible) {
        setVisibility(isVisible ? 0 : 8);
    }

    public final void populate(BadgeModel badgeModel) {
        Unit unit;
        Intrinsics.checkNotNullParameter(badgeModel, "badgeModel");
        setText(badgeModel.getBadgeText());
        setContentDescription(badgeModel.getBadgeText());
        setVisibility(badgeModel.getBadgeVisibility());
        Integer badgeIcon = badgeModel.getBadgeIcon();
        if (badgeIcon == null) {
            unit = null;
        } else {
            setBadgeIcon(badgeIcon.intValue());
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            setCompoundDrawablesRelativeWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }
    }

    private final void setBadgeIcon(int iconId) {
        int currentTextColor = getCurrentTextColor();
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), iconId, null);
        if (drawable != null) {
            drawable.setTint(currentTextColor);
        }
        CharSequence text = getText();
        Intrinsics.checkNotNullExpressionValue(text, "text");
        if (text.length() > 0) {
            setCompoundDrawablePadding((int) ((2 * getResources().getDisplayMetrics().density) + 0.5f));
        }
        setCompoundDrawablesRelativeWithIntrinsicBounds(iconId, 0, 0, 0);
    }
}
