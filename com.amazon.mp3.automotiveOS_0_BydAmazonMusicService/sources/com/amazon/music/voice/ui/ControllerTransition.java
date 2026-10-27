package com.amazon.music.voice.ui;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes4.dex */
public interface ControllerTransition extends Parcelable {

    public interface CompletionListener {
        public static final CompletionListener NONE = new CompletionListener() { // from class: com.amazon.music.voice.ui.ControllerTransition.CompletionListener.1
            @Override // com.amazon.music.voice.ui.ControllerTransition.CompletionListener
            public void completeTransition() {
            }
        };

        void completeTransition();
    }

    void completeTransition();

    void performTransition(@NonNull ViewGroup viewGroup, @Nullable View view, @Nullable View view2, @NonNull CompletionListener completionListener);
}
