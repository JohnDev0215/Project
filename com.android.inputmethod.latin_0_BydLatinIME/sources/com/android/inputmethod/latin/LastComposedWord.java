package com.android.inputmethod.latin;

import android.text.TextUtils;
import com.android.inputmethod.event.Event;
import com.android.inputmethod.latin.common.InputPointers;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public final class LastComposedWord {
    public static final int COMMIT_TYPE_CANCEL_AUTO_CORRECT = 3;
    public static final int COMMIT_TYPE_DECIDED_WORD = 2;
    public static final int COMMIT_TYPE_MANUAL_PICK = 1;
    public static final int COMMIT_TYPE_USER_TYPED_WORD = 0;
    public static final LastComposedWord NOT_A_COMPOSED_WORD = new LastComposedWord(new ArrayList(), null, "", "", "", null, 0);
    public static final String NOT_A_SEPARATOR = "";
    private boolean mActive;
    public final int mCapitalizedMode;
    public final CharSequence mCommittedWord;
    public final ArrayList<Event> mEvents;
    public final InputPointers mInputPointers;
    public final NgramContext mNgramContext;
    public final String mSeparatorString;
    public final String mTypedWord;

    public LastComposedWord(final ArrayList<Event> events, final InputPointers inputPointers, final String typedWord, final CharSequence committedWord, final String separatorString, final NgramContext ngramContext, final int capitalizedMode) {
        InputPointers inputPointers2 = new InputPointers(48);
        this.mInputPointers = inputPointers2;
        if (inputPointers != null) {
            inputPointers2.copy(inputPointers);
        }
        this.mTypedWord = typedWord;
        this.mEvents = new ArrayList<>(events);
        this.mCommittedWord = committedWord;
        this.mSeparatorString = separatorString;
        this.mActive = true;
        this.mNgramContext = ngramContext;
        this.mCapitalizedMode = capitalizedMode;
    }

    public void deactivate() {
        this.mActive = false;
    }

    public boolean canRevertCommit() {
        return (!this.mActive || TextUtils.isEmpty(this.mCommittedWord) || didCommitTypedWord()) ? false : true;
    }

    private boolean didCommitTypedWord() {
        return TextUtils.equals(this.mTypedWord, this.mCommittedWord);
    }
}
