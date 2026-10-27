package com.amazon.music.voice.ui.transitions;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.voice.ui.internal.util.DisplayUtils;

/* JADX INFO: loaded from: classes4.dex */
public final class ScrimOpenTransition extends ScrimTransition {
    public static final Parcelable.Creator<ScrimOpenTransition> CREATOR = new Parcelable.Creator<ScrimOpenTransition>() { // from class: com.amazon.music.voice.ui.transitions.ScrimOpenTransition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScrimOpenTransition createFromParcel(Parcel parcel) {
            return new ScrimOpenTransition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScrimOpenTransition[] newArray(int i) {
            return new ScrimOpenTransition[i];
        }
    };

    @Override // com.amazon.music.voice.ui.transitions.ScrimTransition
    protected int getBodyAnimationDuration(int i) {
        return 500;
    }

    @Override // com.amazon.music.voice.ui.transitions.ScrimTransition
    @Nullable
    protected View selectView(@Nullable View view, @Nullable View view2) {
        return view2;
    }

    public ScrimOpenTransition() {
    }

    protected ScrimOpenTransition(Parcel parcel) {
        super(parcel);
    }

    @Override // com.amazon.music.voice.ui.transitions.ScrimTransition
    @NonNull
    protected Animator createAlphaAnimator(@NonNull View view) {
        return ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f, 1.0f);
    }

    @Override // com.amazon.music.voice.ui.transitions.ScrimTransition
    @NonNull
    protected Animator createTranslationAnimator(@NonNull View view, int i) {
        return ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_Y, DisplayUtils.transformDpiToPx(view.getContext(), 80), 0.0f);
    }
}
