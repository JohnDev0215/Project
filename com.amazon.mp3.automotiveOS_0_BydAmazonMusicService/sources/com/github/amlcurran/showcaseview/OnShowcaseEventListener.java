package com.github.amlcurran.showcaseview;

import android.view.MotionEvent;

/* JADX INFO: loaded from: classes6.dex */
public interface OnShowcaseEventListener {
    public static final OnShowcaseEventListener NONE = new OnShowcaseEventListener() { // from class: com.github.amlcurran.showcaseview.OnShowcaseEventListener.1
        @Override // com.github.amlcurran.showcaseview.OnShowcaseEventListener
        public void onShowcaseViewDidHide(ShowcaseView showcaseView) {
        }

        @Override // com.github.amlcurran.showcaseview.OnShowcaseEventListener
        public void onShowcaseViewHide(ShowcaseView showcaseView) {
        }

        @Override // com.github.amlcurran.showcaseview.OnShowcaseEventListener
        public void onShowcaseViewShow(ShowcaseView showcaseView) {
        }

        @Override // com.github.amlcurran.showcaseview.OnShowcaseEventListener
        public void onShowcaseViewTouchBlocked(MotionEvent motionEvent) {
        }
    };

    void onShowcaseViewDidHide(ShowcaseView showcaseView);

    void onShowcaseViewHide(ShowcaseView showcaseView);

    void onShowcaseViewShow(ShowcaseView showcaseView);

    void onShowcaseViewTouchBlocked(MotionEvent motionEvent);
}
