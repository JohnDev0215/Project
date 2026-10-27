package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentSessionContext;
import kotlin.Metadata;

/* JADX INFO: compiled from: FpsReporter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/amazon/avod/content/smoothstream/FpsReporterInterface;", "", "processFragment", "", "context", "Lcom/amazon/avod/content/ContentSessionContext;", "fragmentUri", "Lcom/amazon/avod/content/smoothstream/SmoothStreamingURI;", "fragment", "Lcom/amazon/avod/content/smoothstream/Fragment;", "ATVPlaybackContent_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface FpsReporterInterface {
    void processFragment(ContentSessionContext context, SmoothStreamingURI fragmentUri, Fragment fragment);
}
