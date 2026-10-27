package com.ts.appservice.power.view;

/* JADX INFO: loaded from: classes.dex */
public interface PowerViewContract {

    public interface IView {
        void updateRemainTime(boolean z, long j);
    }

    public interface Presenter {
        void exitTimer();

        void startTimer();
    }
}
