package com.amazon.music.play.queue;

import com.amazon.musicplayqueueservice.client.common.GetTrackAndQueueMetadataResponse;
import com.amazon.musicplayqueueservice.client.common.RateEntityResponse;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes4.dex */
public interface MusicPlayQueueService {
    GetTrackAndQueueMetadataResponse getTrackAndQueueMetadata(String str) throws VolleyError;

    RateEntityResponse rateEntity(String str, String str2, int i, long j) throws VolleyError;
}
