package com.byd.dilink40.acquisitionservice;

import android.acquisitionservice.BYDAcquisitionClient;
import android.util.ArrayMap;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClient;
import com.byd.dilink.acquisitionservice.IDiLinkOnBYDAcqListener;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class DiLinkAcquisitionClient implements IDiLinkAcquisitionClient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private BYDAcquisitionClient f1263a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink3_0_BYDAcquisitionListeners f1264b = new DiLink3_0_BYDAcquisitionListeners();

    public static class DiLink3_0_BYDAcquisitionListeners extends DiLinkCallbackHolders<IDiLinkOnBYDAcqListener, BYDAcquisitionClient.OnBYDAcqListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BYDAcquisitionClient.OnBYDAcqListener createInnerCallback(final IDiLinkOnBYDAcqListener iDiLinkOnBYDAcqListener) {
            return new BYDAcquisitionClient.OnBYDAcqListener() { // from class: com.byd.dilink40.acquisitionservice.DiLinkAcquisitionClient.DiLink3_0_BYDAcquisitionListeners.1
                @Keep
                public void onAcqSucceed(ArrayList<String> arrayList) {
                    iDiLinkOnBYDAcqListener.onAcqSucceed(arrayList);
                }
            };
        }
    }

    public DiLinkAcquisitionClient(BYDAcquisitionClient bYDAcquisitionClient) {
        if (bYDAcquisitionClient == null) {
            throw new IllegalArgumentException("BYDAcquisitionClient is null");
        }
        this.f1263a = bYDAcquisitionClient;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(BYDAcquisitionClient.OnBYDAcqListener onBYDAcqListener) {
        this.f1263a.unregisterListener(onBYDAcqListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(BYDAcquisitionClient.OnBYDAcqListener onBYDAcqListener) {
        this.f1263a.registerListener(onBYDAcqListener);
    }

    @Override // com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClient
    public int acquisitionControl(int i) {
        return this.f1263a.acquisitionControl(i);
    }

    @Override // com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClient
    public void onAcqSucceed(ArrayList<String> arrayList) {
        BYDAcquisitionClient.onAcqSucceed(arrayList);
    }

    @Override // com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClient
    public void registerOnBYDAcqListener(IDiLinkOnBYDAcqListener iDiLinkOnBYDAcqListener) {
        this.f1264b.addCallbackIgnoreRemoteException(iDiLinkOnBYDAcqListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink40.acquisitionservice.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1268a.d((BYDAcquisitionClient.OnBYDAcqListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClient
    public void sendAcquisitionInfos(String str, String str2) {
        this.f1263a.sendAcquisitionInfos(str, str2);
    }

    @Override // com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClient
    public int sendBuffer(int i, @NonNull byte[] bArr) {
        return this.f1263a.sendBuffer(i, bArr);
    }

    @Override // com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClient
    public int sendEventBuffer(int i, String str, String str2) {
        return this.f1263a.sendEventBuffer(i, str, str2);
    }

    @Override // com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClient
    public int sendFile(ArrayMap<String, String> arrayMap, ArrayMap<String, String> arrayMap2) {
        return this.f1263a.sendFile(arrayMap, arrayMap2);
    }

    @Override // com.byd.dilink.acquisitionservice.IDiLinkAcquisitionClient
    public void unregisterOnBYDAcqListener(IDiLinkOnBYDAcqListener iDiLinkOnBYDAcqListener) {
        this.f1264b.removeCallbackIgnoreRemoteException(iDiLinkOnBYDAcqListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink40.acquisitionservice.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1267a.a((BYDAcquisitionClient.OnBYDAcqListener) obj);
            }
        });
    }
}
