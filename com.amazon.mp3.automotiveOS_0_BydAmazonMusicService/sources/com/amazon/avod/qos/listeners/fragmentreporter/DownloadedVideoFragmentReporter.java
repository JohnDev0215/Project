package com.amazon.avod.qos.listeners.fragmentreporter;

import com.amazon.avod.content.event.ContentEventFragmentConsumed;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.qos.reporter.SmoothStreamingReporter;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadedVideoFragmentReporter extends FragmentReporterBase {
    @Override // com.amazon.avod.qos.listeners.fragmentreporter.FragmentReporter
    public void onFragmentConsumed(@Nonnull ContentEventFragmentConsumed contentEventFragmentConsumed) {
    }

    public DownloadedVideoFragmentReporter(SmoothStreamingReporter smoothStreamingReporter) {
        super(smoothStreamingReporter);
    }

    @Override // com.amazon.avod.qos.listeners.fragmentreporter.FragmentReporter
    public void onFragmentDownloaded(@Nonnull ContentEventFragmentDownloaded contentEventFragmentDownloaded) {
        Preconditions.checkNotNull(contentEventFragmentDownloaded);
        super.reportFragment(contentEventFragmentDownloaded);
    }
}
