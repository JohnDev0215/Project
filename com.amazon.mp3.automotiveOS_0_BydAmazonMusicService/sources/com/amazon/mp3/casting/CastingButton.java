package com.amazon.mp3.casting;

import android.view.View;
import kotlin.Metadata;

/* JADX INFO: compiled from: CastingButton.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH&¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/casting/CastingButton;", "", "setOnCastingButtonClickListener", "", "onClickListener", "Landroid/view/View$OnClickListener;", "show", "isShown", "", "updateButton", "connected", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface CastingButton {
    void setOnCastingButtonClickListener(View.OnClickListener onClickListener);

    void show(boolean isShown);

    void updateButton(boolean connected);
}
