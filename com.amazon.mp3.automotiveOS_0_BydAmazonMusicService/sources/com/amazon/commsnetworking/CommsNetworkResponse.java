package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkResponse;
import java.io.IOException;
import okhttp3.Response;

/* JADX INFO: loaded from: classes2.dex */
public class CommsNetworkResponse implements INetworkResponse {
    private INetworkRequest request;
    private Response response;

    public CommsNetworkResponse(@NonNull INetworkRequest iNetworkRequest, @NonNull Response response) {
        this.request = iNetworkRequest;
        this.response = response;
    }

    @Override // java.lang.AutoCloseable
    public void close() throws IOException {
        this.response.close();
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    @Nullable
    public String getBody() {
        if (this.response.body() == null) {
            return null;
        }
        try {
            return this.response.body().string();
        } catch (IOException unused) {
            return null;
        } finally {
            this.response.close();
        }
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    public boolean isSuccessful() {
        return this.response.isSuccessful();
    }

    @Override // com.amazon.commsnetworking.api.INetworkResponse
    public int getStatusCode() {
        return this.response.code();
    }
}
