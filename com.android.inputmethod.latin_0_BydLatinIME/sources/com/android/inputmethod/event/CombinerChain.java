package com.android.inputmethod.event;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class CombinerChain {
    private StringBuilder mCombinedText;
    private final ArrayList<Combiner> mCombiners;
    private SpannableStringBuilder mStateFeedback;

    public CombinerChain(final String initialText) {
        ArrayList<Combiner> arrayList = new ArrayList<>();
        this.mCombiners = arrayList;
        arrayList.add(new DeadKeyCombiner());
        this.mCombinedText = new StringBuilder(initialText);
        this.mStateFeedback = new SpannableStringBuilder();
    }

    public void reset() {
        this.mCombinedText.setLength(0);
        this.mStateFeedback.clear();
        Iterator<Combiner> it = this.mCombiners.iterator();
        while (it.hasNext()) {
            it.next().reset();
        }
    }

    private void updateStateFeedback() {
        this.mStateFeedback.clear();
        for (int size = this.mCombiners.size() - 1; size >= 0; size--) {
            this.mStateFeedback.append(this.mCombiners.get(size).getCombiningStateFeedback());
        }
    }

    @Nonnull
    public Event processEvent(final ArrayList<Event> previousEvents, @Nonnull final Event newEvent) {
        ArrayList<Event> arrayList = new ArrayList<>(previousEvents);
        Iterator<Combiner> it = this.mCombiners.iterator();
        while (it.hasNext()) {
            newEvent = it.next().processEvent(arrayList, newEvent);
            if (newEvent.isConsumed()) {
                break;
            }
        }
        updateStateFeedback();
        return newEvent;
    }

    public void applyProcessedEvent(final Event event) {
        if (event != null) {
            if (-5 == event.mKeyCode) {
                int length = this.mCombinedText.length();
                if (length > 0) {
                    this.mCombinedText.delete(length - Character.charCount(this.mCombinedText.codePointBefore(length)), length);
                }
            } else {
                CharSequence textToCommit = event.getTextToCommit();
                if (!TextUtils.isEmpty(textToCommit)) {
                    this.mCombinedText.append(textToCommit);
                }
            }
        }
        updateStateFeedback();
    }

    public CharSequence getComposingWordWithCombiningFeedback() {
        return new SpannableStringBuilder(this.mCombinedText).append((CharSequence) this.mStateFeedback);
    }
}
