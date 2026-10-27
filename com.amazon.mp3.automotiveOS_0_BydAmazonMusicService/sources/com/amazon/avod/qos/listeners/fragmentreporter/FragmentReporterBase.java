package com.amazon.avod.qos.listeners.fragmentreporter;

import com.amazon.avod.content.dash.quality.heuristic.HeuristicQosMetrics;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.content.event.FragmentInformationHolder;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.qos.reporter.SmoothStreamingReporter;
import com.amazon.avod.util.DataUnit;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public abstract class FragmentReporterBase implements FragmentReporter {
    private final SmoothStreamingReporter mSmoothStreamingReporter;

    public FragmentReporterBase(SmoothStreamingReporter smoothStreamingReporter) {
        this.mSmoothStreamingReporter = smoothStreamingReporter;
    }

    protected void reportFragment(ContentEventFragmentDownloaded contentEventFragmentDownloaded) {
        FragmentInformationHolder fragmentInformationHolder = contentEventFragmentDownloaded.getFragmentInformationHolder();
        DataUnit dataUnit = DataUnit.BITS;
        int kiloBits = (int) dataUnit.toKiloBits(contentEventFragmentDownloaded.getBandwidthBitsPerSecond());
        int kiloBits2 = (int) dataUnit.toKiloBits(fragmentInformationHolder.getBitrate());
        HeuristicQosMetrics heuristicQosMetrics = new HeuristicQosMetrics();
        heuristicQosMetrics.initialize(contentEventFragmentDownloaded.getHeuristics());
        this.mSmoothStreamingReporter.reportFragment(fragmentInformationHolder.getChunkIndex(), kiloBits, kiloBits2, contentEventFragmentDownloaded.getFragmentSizeBytes(), TimeUnit.NANOSECONDS.toMillis(contentEventFragmentDownloaded.getDownloadTimeInNanos()), new TimeSpan(contentEventFragmentDownloaded.getEpochTimeInNanos()), heuristicQosMetrics.toJsonString());
    }
}
