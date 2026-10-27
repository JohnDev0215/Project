package org.chromium.content.browser.input;

/* JADX INFO: loaded from: classes3.dex */
public interface PastePopupMenu {

    public interface PastePopupMenuDelegate {
        void onDismiss();

        void paste();
    }

    void hide();

    boolean isShowing();

    void show(int i, int i2);
}
