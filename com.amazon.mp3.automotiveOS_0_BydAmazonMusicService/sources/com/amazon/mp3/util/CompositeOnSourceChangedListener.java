package com.amazon.mp3.util;

import com.amazon.mp3.actionbar.view.ActionBarSourceToggleView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class CompositeOnSourceChangedListener implements ActionBarSourceToggleView.SourceChangedListener {
    private Collection<ActionBarSourceToggleView.SourceChangedListener> mListeners = new ArrayList();

    public void addSourceChangedListener(ActionBarSourceToggleView.SourceChangedListener listener) {
        this.mListeners.add(listener);
    }

    public boolean removeOnSourceChangedListener(ActionBarSourceToggleView.SourceChangedListener listener) {
        return this.mListeners.remove(listener);
    }

    public void removeAllSourceChangedListeners() {
        this.mListeners.clear();
    }

    @Override // com.amazon.mp3.actionbar.view.ActionBarSourceToggleView.SourceChangedListener
    public void onSourceToggled(ActionBarSourceToggleView.Source source) {
        Iterator<ActionBarSourceToggleView.SourceChangedListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onSourceToggled(source);
        }
    }
}
