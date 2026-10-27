package com.amazon.music.recents;

import com.amazon.nimblymusicservice.GetRecentActivityRequest;
import com.amazon.nimblymusicservice.GetRecentActivityResponse;
import com.amazon.nimblymusicservice.GetRecentTrackActivityRequest;
import com.amazon.nimblymusicservice.GetRecentTrackActivityResponse;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackRequest;
import com.amazon.nimblymusicservice.GetRecentUnfilteredTrackResponse;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes4.dex */
public interface RecentActivityService {
    GetRecentActivityResponse getRecentActivity(GetRecentActivityRequest getRecentActivityRequest) throws VolleyError;

    GetRecentTrackActivityResponse getRecentTrackActivity(GetRecentTrackActivityRequest getRecentTrackActivityRequest) throws VolleyError;

    GetRecentUnfilteredTrackResponse getRecentUnfilteredTrackActivity(GetRecentUnfilteredTrackRequest getRecentUnfilteredTrackRequest) throws VolleyError;
}
