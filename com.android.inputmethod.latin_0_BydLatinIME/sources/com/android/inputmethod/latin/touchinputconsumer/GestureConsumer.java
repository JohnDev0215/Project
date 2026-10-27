package com.android.inputmethod.latin.touchinputconsumer;

import android.view.inputmethod.EditorInfo;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.latin.DictionaryFacilitator;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.common.InputPointers;
import com.android.inputmethod.latin.inputlogic.PrivateCommandPerformer;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class GestureConsumer {
    public static final GestureConsumer NULL_GESTURE_CONSUMER = new GestureConsumer();

    public void onGestureCanceled() {
    }

    public void onGestureCompleted(final InputPointers inputPointers) {
    }

    public void onGestureStarted(final Locale locale, final Keyboard keyboard) {
    }

    public void onImeSuggestionsProcessed(final SuggestedWords suggestedWords, final int composingStart, final int composingLength, final DictionaryFacilitator dictionaryFacilitator) {
    }

    public void onInit(final Locale locale, final Keyboard keyboard) {
    }

    public boolean willConsume() {
        return false;
    }

    public static GestureConsumer newInstance(final EditorInfo editorInfo, final PrivateCommandPerformer commandPerformer, final Locale locale, final Keyboard keyboard) {
        return NULL_GESTURE_CONSUMER;
    }

    private GestureConsumer() {
    }
}
