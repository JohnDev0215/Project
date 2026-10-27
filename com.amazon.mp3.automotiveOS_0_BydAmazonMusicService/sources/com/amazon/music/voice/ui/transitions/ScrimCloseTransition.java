package com.amazon.music.voice.ui.transitions;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public final class ScrimCloseTransition extends ScrimTransition {
    public static final Parcelable.Creator<ScrimCloseTransition> CREATOR = new Parcelable.Creator<ScrimCloseTransition>() { // from class: com.amazon.music.voice.ui.transitions.ScrimCloseTransition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScrimCloseTransition createFromParcel(Parcel parcel) {
            return new ScrimCloseTransition(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScrimCloseTransition[] newArray(int i) {
            return new ScrimCloseTransition[i];
        }
    };

    @Override // com.amazon.music.voice.ui.transitions.ScrimTransition
    protected int getBodyAnimationDuration(int i) {
        return i >= 100 ? 500 : 150;
    }

    @Override // com.amazon.music.voice.ui.transitions.ScrimTransition
    @Nullable
    protected View selectView(@Nullable View view, @Nullable View view2) {
        return view;
    }

    public ScrimCloseTransition() {
    }

    protected ScrimCloseTransition(Parcel parcel) {
        super(parcel);
    }

    @Override // com.amazon.music.voice.ui.transitions.ScrimTransition
    @NonNull
    protected Animator createAlphaAnimator(@NonNull View view) {
        return ObjectAnimator.ofFloat(view, (Property<View, Float>) View.ALPHA, 0.0f);
    }

    @Override // com.amazon.music.voice.ui.transitions.ScrimTransition
    @NonNull
    protected Animator createTranslationAnimator(@NonNull View view, int i) {
        return ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_Y, i);
    }
}
