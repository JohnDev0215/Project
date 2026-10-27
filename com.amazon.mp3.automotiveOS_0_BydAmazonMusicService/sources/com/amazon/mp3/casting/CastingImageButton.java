package com.amazon.mp3.casting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CastingImageButton.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0016\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H\u0016J\u0012\u0010\u000b\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/casting/CastingImageButton;", "Landroid/widget/ImageButton;", "Lcom/amazon/mp3/casting/CastingButton;", "", "connected", "", "updateButton", "isShown", "show", "Landroid/view/View$OnClickListener;", "onClickListener", "setOnCastingButtonClickListener", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CastingImageButton extends ImageButton implements CastingButton {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CastingImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
    }

    @Override // com.amazon.mp3.casting.CastingButton
    public void updateButton(boolean connected) {
        Context context = getContext();
        Integer numValueOf = Integer.valueOf(R.drawable.ic_casting_inactive);
        Integer numValueOf2 = Integer.valueOf(R.drawable.ic_casting_active);
        if (connected) {
            setImageResource(numValueOf2.intValue());
            if (context != null) {
                setContentDescription(context.getResources().getString(R.string.casting_icon_connected_text, CastingUtil.getCastingDeviceName(context)));
                return;
            }
            return;
        }
        setImageResource(numValueOf.intValue());
        if (context != null) {
            setContentDescription(context.getResources().getString(R.string.casting_icon_disconnected_text));
        }
    }

    @Override // com.amazon.mp3.casting.CastingButton
    public void show(boolean isShown) {
        setVisibility(isShown ? 0 : 4);
    }

    @Override // com.amazon.mp3.casting.CastingButton
    public void setOnCastingButtonClickListener(View.OnClickListener onClickListener) {
        setOnClickListener(onClickListener);
    }
}
