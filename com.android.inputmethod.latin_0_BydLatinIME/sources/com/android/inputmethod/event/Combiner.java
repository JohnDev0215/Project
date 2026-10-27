package com.android.inputmethod.event;

import java.util.ArrayList;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface Combiner {
    CharSequence getCombiningStateFeedback();

    @Nonnull
    Event processEvent(ArrayList<Event> previousEvents, Event event);

    void reset();
}
