package com.amazon.podcast.bootstrap;

import androidx.fragment.app.FragmentManager;

/* JADX INFO: loaded from: classes5.dex */
public abstract class FragmentInfoProvider {
    public int fragmentContainerId() {
        throw new UnsupportedOperationException();
    }

    public FragmentManager fragmentManager() {
        throw new UnsupportedOperationException();
    }

    public Integer childFragmentContainerId() {
        throw new UnsupportedOperationException();
    }

    public FragmentManager childFragmentManager() {
        throw new UnsupportedOperationException();
    }

    public void addFragmentManagerListener(final FragmentManagerListener fragmentManagerListener) {
        throw new UnsupportedOperationException();
    }

    public void removeFragmentListener(final FragmentManagerListener fragmentManagerListener) {
        throw new UnsupportedOperationException();
    }
}
