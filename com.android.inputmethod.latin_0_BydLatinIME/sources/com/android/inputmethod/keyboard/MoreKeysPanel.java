package com.android.inputmethod.keyboard;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes2.dex */
public interface MoreKeysPanel {
    public static final Controller EMPTY_CONTROLLER = new Controller() { // from class: com.android.inputmethod.keyboard.MoreKeysPanel.1
        @Override // com.android.inputmethod.keyboard.MoreKeysPanel.Controller
        public void onCancelMoreKeysPanel() {
        }

        @Override // com.android.inputmethod.keyboard.MoreKeysPanel.Controller
        public void onDismissMoreKeysPanel() {
        }

        @Override // com.android.inputmethod.keyboard.MoreKeysPanel.Controller
        public void onShowMoreKeysPanel(final MoreKeysPanel panel) {
        }
    };

    public interface Controller {
        void onCancelMoreKeysPanel();

        void onDismissMoreKeysPanel();

        void onShowMoreKeysPanel(final MoreKeysPanel panel);
    }

    void dismissMoreKeysPanel();

    boolean isShowingInParent();

    void onDownEvent(final int x, final int y, final int pointerId, final long eventTime);

    void onDownEvent(final Key key, final int x, final int y, final int pointerId, final long eventTime);

    void onMoveEvent(final int x, final int y, final int pointerId, final long eventTime);

    void onUpEvent(final int x, final int y, final int pointerId, final long eventTime);

    void removeFromParent();

    void showInParent(ViewGroup parentView);

    void showMoreKeysPanel(View parentView, Controller controller, int pointX, int pointY, KeyboardActionListener listener);

    int translateX(int x);

    int translateY(int y);
}
