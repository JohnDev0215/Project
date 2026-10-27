package com.amazon.mp3.fragment.utils;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: loaded from: classes3.dex */
public class FragmentLifecycleObserver implements DefaultLifecycleObserver {
    private FragmentStartedListener mFragmentStartedListener;

    public interface FragmentStartedListener {
        void onFragmentStarted();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStart(final LifecycleOwner owner) {
        FragmentStartedListener fragmentStartedListener = this.mFragmentStartedListener;
        if (fragmentStartedListener != null) {
            fragmentStartedListener.onFragmentStarted();
        }
    }
}
