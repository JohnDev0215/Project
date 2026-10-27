package com.android.inputmethod.latin.suggestions;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import com.android.inputmethod.keyboard.Key;
import com.android.inputmethod.keyboard.Keyboard;
import com.android.inputmethod.keyboard.KeyboardActionListener;
import com.android.inputmethod.keyboard.MoreKeysKeyboardView;
import com.android.inputmethod.latin.R;
import com.android.inputmethod.latin.SuggestedWords;
import com.android.inputmethod.latin.suggestions.MoreSuggestions;

/* JADX INFO: loaded from: classes2.dex */
public final class MoreSuggestionsView extends MoreKeysKeyboardView {
    private static final String TAG = "MoreSuggestionsView";
    private boolean mIsInModalMode;

    public static abstract class MoreSuggestionsListener extends KeyboardActionListener.Adapter {
        public abstract void onSuggestionSelected(final SuggestedWords.SuggestedWordInfo info);
    }

    public MoreSuggestionsView(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.moreKeysKeyboardViewStyle);
    }

    public MoreSuggestionsView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysKeyboardView, com.android.inputmethod.keyboard.KeyboardView
    public void setKeyboard(final Keyboard keyboard) {
        super.setKeyboard(keyboard);
        this.mIsInModalMode = false;
        if (this.mAccessibilityDelegate != null) {
            this.mAccessibilityDelegate.setOpenAnnounce(R.string.spoken_open_more_suggestions);
            this.mAccessibilityDelegate.setCloseAnnounce(R.string.spoken_close_more_suggestions);
        }
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysKeyboardView
    protected int getDefaultCoordX() {
        return ((MoreSuggestions) getKeyboard()).mOccupiedWidth / 2;
    }

    public void updateKeyboardGeometry(final int keyHeight) {
        updateKeyDrawParams(keyHeight);
    }

    public void setModalMode() {
        this.mIsInModalMode = true;
        this.mKeyDetector.setKeyboard(getKeyboard(), -getPaddingLeft(), -getPaddingTop());
    }

    public boolean isInModalMode() {
        return this.mIsInModalMode;
    }

    @Override // com.android.inputmethod.keyboard.MoreKeysKeyboardView
    protected void onKeyInput(final Key key, final int x, final int y) {
        if (!(key instanceof MoreSuggestions.MoreSuggestionKey)) {
            Log.e(TAG, "Expected key is MoreSuggestionKey, but found " + key.getClass().getName());
            return;
        }
        Keyboard keyboard = getKeyboard();
        if (!(keyboard instanceof MoreSuggestions)) {
            Log.e(TAG, "Expected keyboard is MoreSuggestions, but found " + keyboard.getClass().getName());
            return;
        }
        SuggestedWords suggestedWords = ((MoreSuggestions) keyboard).mSuggestedWords;
        int i = ((MoreSuggestions.MoreSuggestionKey) key).mSuggestedWordIndex;
        if (i < 0 || i >= suggestedWords.size()) {
            Log.e(TAG, "Selected suggestion has an illegal index: " + i);
        } else if (!(this.mListener instanceof MoreSuggestionsListener)) {
            Log.e(TAG, "Expected mListener is MoreSuggestionsListener, but found " + this.mListener.getClass().getName());
        } else {
            ((MoreSuggestionsListener) this.mListener).onSuggestionSelected(suggestedWords.getInfo(i));
        }
    }
}
