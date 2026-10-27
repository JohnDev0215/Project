package com.android.inputmethod.dictionarypack;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class DictionaryListInterfaceState {
    private HashMap<String, State> mWordlistToState = new HashMap<>();
    private ArrayList<View> mViewCache = new ArrayList<>();

    static class State {
        public boolean mOpen = false;
        public int mStatus = 0;

        State() {
        }
    }

    public boolean isOpen(final String wordlistId) {
        State state = this.mWordlistToState.get(wordlistId);
        if (state == null) {
            return false;
        }
        return state.mOpen;
    }

    public int getStatus(final String wordlistId) {
        State state = this.mWordlistToState.get(wordlistId);
        if (state == null) {
            return 0;
        }
        return state.mStatus;
    }

    public void setOpen(final String wordlistId, final int status) {
        State state = this.mWordlistToState.get(wordlistId);
        if (state == null) {
            state = new State();
        }
        state.mOpen = true;
        state.mStatus = status;
        this.mWordlistToState.put(wordlistId, state);
    }

    public void closeAll() {
        Iterator<State> it = this.mWordlistToState.values().iterator();
        while (it.hasNext()) {
            it.next().mOpen = false;
        }
    }

    public View findFirstOrphanedView() {
        for (View view : this.mViewCache) {
            if (view.getParent() == null) {
                return view;
            }
        }
        return null;
    }

    public View addToCacheAndReturnView(final View view) {
        this.mViewCache.add(view);
        return view;
    }

    public void removeFromCache(final View view) {
        this.mViewCache.remove(view);
    }
}
