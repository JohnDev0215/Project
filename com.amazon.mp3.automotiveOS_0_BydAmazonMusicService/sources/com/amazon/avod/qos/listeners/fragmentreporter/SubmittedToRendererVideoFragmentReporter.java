package com.amazon.avod.qos.listeners.fragmentreporter;

import com.amazon.avod.content.event.ContentEventFragmentConsumed;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.qos.reporter.SmoothStreamingReporter;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SubmittedToRendererVideoFragmentReporter extends FragmentReporterBase {
    private final Map<Integer, ContentEventFragmentDownloaded> downloadedFragments;

    public SubmittedToRendererVideoFragmentReporter(SmoothStreamingReporter smoothStreamingReporter) {
        super(smoothStreamingReporter);
        this.downloadedFragments = Maps.newHashMap();
    }

    @Override // com.amazon.avod.qos.listeners.fragmentreporter.FragmentReporter
    public void onFragmentDownloaded(@Nonnull ContentEventFragmentDownloaded contentEventFragmentDownloaded) {
        Preconditions.checkNotNull(contentEventFragmentDownloaded, "fragment");
        this.downloadedFragments.put(Integer.valueOf(contentEventFragmentDownloaded.getFragmentInformationHolder().getChunkIndex()), contentEventFragmentDownloaded);
    }

    @Override // com.amazon.avod.qos.listeners.fragmentreporter.FragmentReporter
    public void onFragmentConsumed(@Nonnull ContentEventFragmentConsumed contentEventFragmentConsumed) {
        Preconditions.checkNotNull(contentEventFragmentConsumed, "consumedFragment");
        if (contentEventFragmentConsumed.getURI().isVideo()) {
            int chunkIndex = contentEventFragmentConsumed.getURI().getChunkIndex();
            ContentEventFragmentDownloaded contentEventFragmentDownloadedRemove = this.downloadedFragments.remove(Integer.valueOf(chunkIndex));
            if (contentEventFragmentDownloadedRemove != null) {
                reportFragment(contentEventFragmentDownloadedRemove);
            } else {
                DLog.warnf("Consumed fragment index %s which is not in downloaded indices", Integer.valueOf(chunkIndex));
            }
        }
    }
}
