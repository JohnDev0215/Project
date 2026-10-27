package org.chromium.content.browser;

/* JADX INFO: loaded from: classes3.dex */
public interface ContextualSearchClient {
    void onSelectionChanged(String str);

    void onSelectionEvent(int i, float f, float f2);

    void showUnhandledTapUIIfNeeded(int i, int i2);
}
