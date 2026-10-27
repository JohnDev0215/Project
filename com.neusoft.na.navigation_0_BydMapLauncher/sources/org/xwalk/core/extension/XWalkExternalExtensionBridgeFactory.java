package org.xwalk.core.extension;

/* JADX INFO: loaded from: classes4.dex */
final class XWalkExternalExtensionBridgeFactory {
    XWalkExternalExtensionBridgeFactory() {
    }

    public static XWalkExternalExtensionBridge createInstance(XWalkExternalExtension xWalkExternalExtension) {
        return new XWalkCoreExtensionBridge(xWalkExternalExtension);
    }
}
