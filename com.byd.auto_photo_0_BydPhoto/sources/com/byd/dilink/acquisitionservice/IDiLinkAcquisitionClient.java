package com.byd.dilink.acquisitionservice;

import android.util.ArrayMap;
import androidx.annotation.NonNull;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkAcquisitionClient {
    int acquisitionControl(int i);

    void onAcqSucceed(ArrayList<String> arrayList);

    void registerOnBYDAcqListener(IDiLinkOnBYDAcqListener iDiLinkOnBYDAcqListener);

    void sendAcquisitionInfos(String str, String str2);

    int sendBuffer(int i, @NonNull byte[] bArr);

    int sendEventBuffer(int i, String str, String str2);

    int sendFile(ArrayMap<String, String> arrayMap, ArrayMap<String, String> arrayMap2);

    void unregisterOnBYDAcqListener(IDiLinkOnBYDAcqListener iDiLinkOnBYDAcqListener);
}
