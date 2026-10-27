package com.amazon.avod.qos.listeners.fragmentreporter;

import com.amazon.avod.content.event.ContentEventFragmentConsumed;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public interface FragmentReporter {
    void onFragmentConsumed(@Nonnull ContentEventFragmentConsumed contentEventFragmentConsumed);

    void onFragmentDownloaded(@Nonnull ContentEventFragmentDownloaded contentEventFragmentDownloaded);
}
