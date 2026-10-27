package com.amazon.music.voice.ui.transitions;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.music.voice.ui.ControllerTransition;

/* JADX INFO: loaded from: classes4.dex */
public final class InstantTransition implements ControllerTransition {
    public static final Parcelable.Creator<InstantTransition> CREATOR = new Parcelable.Creator<InstantTransition>() { // from class: com.amazon.music.voice.ui.transitions.InstantTransition.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstantTransition createFromParcel(Parcel parcel) {
            return new InstantTransition();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public InstantTransition[] newArray(int i) {
            return new InstantTransition[i];
        }
    };

    @Override // com.amazon.music.voice.ui.ControllerTransition
    public void completeTransition() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    @Override // com.amazon.music.voice.ui.ControllerTransition
    public void performTransition(@NonNull ViewGroup viewGroup, @Nullable View view, @Nullable View view2, @NonNull ControllerTransition.CompletionListener completionListener) {
        completionListener.completeTransition();
    }
}
