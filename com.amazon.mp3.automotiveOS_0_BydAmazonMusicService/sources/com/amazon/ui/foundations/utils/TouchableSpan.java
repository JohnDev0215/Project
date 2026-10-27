package com.amazon.ui.foundations.utils;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TouchableSpan.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0012\b\u0002\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\b¢\u0006\u0002\u0010\nJ\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u000e\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0006J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016R\u0018\u0010\u0007\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\t\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\fR\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\r¨\u0006\u0016"}, d2 = {"Lcom/amazon/ui/foundations/utils/TouchableSpan;", "Landroid/text/style/ClickableSpan;", "mNormalTextColor", "", "mPressedTextColor", "showUnderline", "", "linkClick", "Lkotlin/Function0;", "", "(ILjava/lang/Integer;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function0;)V", "mIsPressed", "Ljava/lang/Integer;", "Ljava/lang/Boolean;", "onClick", "view", "Landroid/view/View;", "setPressed", "isSelected", "updateDrawState", "ds", "Landroid/text/TextPaint;", "DMMUIFoundations_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TouchableSpan extends ClickableSpan {
    private final Function0<Unit> linkClick;
    private boolean mIsPressed;
    private final int mNormalTextColor;
    private final Integer mPressedTextColor;
    private final Boolean showUnderline;

    public /* synthetic */ TouchableSpan(int i, Integer num, Boolean bool, Function0 function0, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : num, (i2 & 4) != 0 ? null : bool, (i2 & 8) != 0 ? null : function0);
    }

    public TouchableSpan(int i, Integer num, Boolean bool, Function0<Unit> function0) {
        this.mNormalTextColor = i;
        this.mPressedTextColor = num;
        this.showUnderline = bool;
        this.linkClick = function0;
    }

    public final void setPressed(boolean isSelected) {
        this.mIsPressed = isSelected;
    }

    @Override // android.text.style.ClickableSpan
    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Function0<Unit> function0 = this.linkClick;
        if (function0 == null) {
            return;
        }
        function0.invoke();
    }

    @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
    public void updateDrawState(TextPaint ds) {
        Boolean bool;
        Integer num;
        Intrinsics.checkNotNullParameter(ds, "ds");
        super.updateDrawState(ds);
        ds.setColor((!this.mIsPressed || (num = this.mPressedTextColor) == null) ? this.mNormalTextColor : num.intValue());
        boolean zBooleanValue = false;
        if (this.mIsPressed && (bool = this.showUnderline) != null) {
            zBooleanValue = bool.booleanValue();
        }
        ds.setUnderlineText(zBooleanValue);
    }
}
