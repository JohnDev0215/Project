package com.amazon.music.curate.provider;

import androidx.fragment.app.FragmentManager;

/* JADX INFO: loaded from: classes4.dex */
public abstract class FragmentsStackProvider {

    public interface FragmentManagerListener {
        void onFragmentManagerUpdated(final FragmentManager fragmentManager);
    }

    public int fragmentContainerId() {
        throw new UnsupportedOperationException();
    }

    public FragmentManager fragmentManager() {
        throw new UnsupportedOperationException();
    }

    public void addFragmentManagerListener(final FragmentManagerListener fragmentManagerListener) {
        throw new UnsupportedOperationException();
    }

    public void removeFragmentListener(final FragmentManagerListener fragmentManagerListener) {
        throw new UnsupportedOperationException();
    }
}
