package org.chromium.content_public.browser;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class NavigationHistory {
    private int mCurrentEntryIndex;
    private final ArrayList<NavigationEntry> mEntries = new ArrayList<>();

    public void addEntry(NavigationEntry navigationEntry) {
        this.mEntries.add(navigationEntry);
    }

    public void setCurrentEntryIndex(int i) {
        this.mCurrentEntryIndex = i;
    }

    public int getEntryCount() {
        return this.mEntries.size();
    }

    public NavigationEntry getEntryAtIndex(int i) {
        return this.mEntries.get(i);
    }

    public int getCurrentEntryIndex() {
        return this.mCurrentEntryIndex;
    }
}
