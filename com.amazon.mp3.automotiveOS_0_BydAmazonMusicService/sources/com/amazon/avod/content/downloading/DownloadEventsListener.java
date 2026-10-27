package com.amazon.avod.content.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.downloadservice.DownloadStatistics;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadEventsListener {
    void onBufferFilled();

    void onBufferSizeChange(long j, StreamIndex streamIndex, boolean z);

    void onDownloadError(ContentUrl contentUrl, SmoothStreamingURI smoothStreamingURI, ContentException contentException, int i, @Nullable Map<String, List<String>> map);

    void onFragmentDownloadFinished(ContentUrl contentUrl, SmoothStreamingURI smoothStreamingURI, DownloadStatistics downloadStatistics, int i);

    void onFragmentDownloadRequested(SmoothStreamingURI smoothStreamingURI);
}
