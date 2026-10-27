package com.amazon.mp3.recents.click;

import android.view.View;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoOpClickListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/recents/click/NoOpClickListener;", "Landroid/view/View$OnClickListener;", "()V", "onClick", "", "p0", "Landroid/view/View;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NoOpClickListener implements View.OnClickListener {
    @Override // android.view.View.OnClickListener
    public void onClick(View p0) {
        throw new NotImplementedError(Intrinsics.stringPlus("An operation is not implemented: ", "not implemented"));
    }
}
