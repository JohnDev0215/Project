package com.amazon.avod.content.smoothstream.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.quality.FragmentDownloadParameters;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.media.downloadservice.DownloadStatistics;

/* JADX INFO: loaded from: classes2.dex */
public interface FragmentDownloadController {
    FragmentDownloadParameters getDownloadParameters(SmoothStreamingURI smoothStreamingURI, int i, long j);

    ContentUrl getManifestUrl();

    void notifyDownloadSuccess(SmoothStreamingURI smoothStreamingURI, long j);

    void onDownloadProgress(SmoothStreamingURI smoothStreamingURI, long j, int i, DownloadStatistics downloadStatistics, long j2);

    void onFailure(SmoothStreamingURI smoothStreamingURI, long j, ContentUrl contentUrl, ContentException contentException, int i, DownloadStatistics downloadStatistics);

    void onSuccess(SmoothStreamingURI smoothStreamingURI, long j, ContentUrl contentUrl, int i, long j2, DownloadStatistics downloadStatistics);
}
