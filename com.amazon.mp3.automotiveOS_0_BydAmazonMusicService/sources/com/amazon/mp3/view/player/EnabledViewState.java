package com.amazon.mp3.view.player;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: EnabledViewState.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/view/player/EnabledViewState;", "", "(Ljava/lang/String;I)V", "updateViewState", "", "view", "Landroid/view/View;", "invisibleWhenGone", "", "(Landroid/view/View;Ljava/lang/Boolean;)V", "ENABLED", "DISABLED", "GONE", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum EnabledViewState {
    ENABLED,
    DISABLED,
    GONE;

    public static /* synthetic */ void updateViewState$default(EnabledViewState enabledViewState, View view, Boolean bool, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateViewState");
        }
        if ((i & 2) != 0) {
            bool = Boolean.FALSE;
        }
        enabledViewState.updateViewState(view, bool);
    }

    public final void updateViewState(View view, Boolean invisibleWhenGone) {
        Intrinsics.checkNotNullParameter(view, "view");
        if (this == ENABLED) {
            view.setEnabled(true);
            view.setVisibility(0);
        } else if (this == DISABLED) {
            view.setEnabled(false);
            view.setVisibility(0);
        } else if (this == GONE) {
            view.setVisibility(Intrinsics.areEqual(invisibleWhenGone, Boolean.TRUE) ? 4 : 8);
        }
    }
}
