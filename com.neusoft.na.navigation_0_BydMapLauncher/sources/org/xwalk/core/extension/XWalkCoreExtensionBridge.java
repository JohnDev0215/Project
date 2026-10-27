package org.xwalk.core.extension;

import android.content.Intent;
import org.xwalk.core.XWalkExtension;

/* JADX INFO: loaded from: classes4.dex */
class XWalkCoreExtensionBridge extends XWalkExtension implements XWalkExternalExtensionBridge {
    private XWalkExternalExtension mExtension;

    public XWalkCoreExtensionBridge(XWalkExternalExtension xWalkExternalExtension) {
        super(xWalkExternalExtension.getExtensionName(), xWalkExternalExtension.getJsApi(), xWalkExternalExtension.getEntryPoints());
        this.mExtension = xWalkExternalExtension;
    }

    @Override // org.xwalk.core.XWalkExtension, org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void onMessage(int i, String str) {
        this.mExtension.onMessage(i, str);
    }

    @Override // org.xwalk.core.XWalkExtension, org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void onBinaryMessage(int i, byte[] bArr) {
        this.mExtension.onBinaryMessage(i, bArr);
    }

    @Override // org.xwalk.core.XWalkExtension, org.xwalk.core.extension.XWalkExternalExtensionBridge
    public String onSyncMessage(int i, String str) {
        return this.mExtension.onSyncMessage(i, str);
    }

    @Override // org.xwalk.core.XWalkExtension, org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void onInstanceCreated(int i) {
        this.mExtension.onInstanceCreated(i);
    }

    @Override // org.xwalk.core.XWalkExtension, org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void onInstanceDestroyed(int i) {
        this.mExtension.onInstanceDestroyed(i);
    }

    @Override // org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void onDestroy() {
        this.mExtension.onDestroy();
    }

    @Override // org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void onResume() {
        this.mExtension.onResume();
    }

    @Override // org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void onPause() {
        this.mExtension.onPause();
    }

    @Override // org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void onStart() {
        this.mExtension.onStart();
    }

    @Override // org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void onStop() {
        this.mExtension.onStop();
    }

    @Override // org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void onNewIntent(Intent intent) {
        this.mExtension.onNewIntent(intent);
    }

    @Override // org.xwalk.core.XWalkExtension, org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void postMessage(int i, String str) {
        super.postMessage(i, str);
    }

    @Override // org.xwalk.core.XWalkExtension, org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void postBinaryMessage(int i, byte[] bArr) {
        super.postBinaryMessage(i, bArr);
    }

    @Override // org.xwalk.core.XWalkExtension, org.xwalk.core.extension.XWalkExternalExtensionBridge
    public void broadcastMessage(String str) {
        super.broadcastMessage(str);
    }
}
