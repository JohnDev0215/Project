package org.chromium.content.browser;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import org.chromium.content.browser.PositionObserver;

/* JADX INFO: loaded from: classes3.dex */
public class ViewPositionObserver implements PositionObserver {
    private ViewTreeObserver.OnPreDrawListener mPreDrawListener;
    private View mView;
    private final int[] mPosition = new int[2];
    private final ArrayList<PositionObserver.Listener> mListeners = new ArrayList<>();

    public ViewPositionObserver(View view) {
        this.mView = view;
        updatePosition();
        this.mPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: org.chromium.content.browser.ViewPositionObserver.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                ViewPositionObserver.this.updatePosition();
                return true;
            }
        };
    }

    @Override // org.chromium.content.browser.PositionObserver
    public int getPositionX() {
        updatePosition();
        return this.mPosition[0];
    }

    @Override // org.chromium.content.browser.PositionObserver
    public int getPositionY() {
        updatePosition();
        return this.mPosition[1];
    }

    @Override // org.chromium.content.browser.PositionObserver
    public void addListener(PositionObserver.Listener listener) {
        if (this.mListeners.contains(listener)) {
            return;
        }
        if (this.mListeners.isEmpty()) {
            this.mView.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
            updatePosition();
        }
        this.mListeners.add(listener);
    }

    @Override // org.chromium.content.browser.PositionObserver
    public void removeListener(PositionObserver.Listener listener) {
        if (this.mListeners.contains(listener)) {
            this.mListeners.remove(listener);
            if (this.mListeners.isEmpty()) {
                this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
            }
        }
    }

    private void notifyListeners() {
        for (int i = 0; i < this.mListeners.size(); i++) {
            PositionObserver.Listener listener = this.mListeners.get(i);
            int[] iArr = this.mPosition;
            listener.onPositionChanged(iArr[0], iArr[1]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePosition() {
        int[] iArr = this.mPosition;
        int i = iArr[0];
        int i2 = iArr[1];
        this.mView.getLocationInWindow(iArr);
        int[] iArr2 = this.mPosition;
        if (iArr2[0] == i && iArr2[1] == i2) {
            return;
        }
        notifyListeners();
    }

    @Override // org.chromium.content.browser.PositionObserver
    public void clearListener() {
        this.mListeners.clear();
    }
}
