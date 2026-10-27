package com.spotify.voiceassistants.playermodels;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface SearchResponse {
    List<MetadataItem> getResponseAlternativeResults();

    MetadataItem getResponseFeedbackDetails();

    String getSearchRequestId();
}
