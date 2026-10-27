package com.amazon.music.casting;

import com.amazon.musicplayqueueservice.client.common.CreateQueueRequest;
import com.amazon.musicplayqueueservice.client.common.CreateQueueResponse;
import com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponse;
import com.amazon.musicplayqueueservice.client.common.GetDeviceListResponse;
import com.amazon.musicplayqueueservice.client.common.LoginResponse;
import com.amazon.musicplayqueueservice.client.common.TransferQueueRequest;
import com.amazon.musicplayqueueservice.client.common.TransferQueueResponse;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes4.dex */
public interface CastingService {
    CreateQueueResponse createQueue(CreateQueueRequest createQueueRequest) throws VolleyError;

    GetConnectionInfoResponse getConnectionInfo() throws VolleyError;

    GetDeviceListResponse getDeviceList() throws VolleyError;

    LoginResponse login() throws VolleyError;

    TransferQueueResponse transferQueue(TransferQueueRequest transferQueueRequest) throws VolleyError;
}
