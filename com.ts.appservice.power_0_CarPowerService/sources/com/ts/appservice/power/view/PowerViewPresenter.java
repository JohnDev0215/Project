package com.ts.appservice.power.view;

import android.content.Context;
import android.os.CountDownTimer;
import com.ts.appservice.power.view.PowerViewContract;

/* JADX INFO: loaded from: classes.dex */
public class PowerViewPresenter extends BasePresenter implements PowerViewContract.Presenter {
    private static final long COUNT_DOWN_INTERVAL = 1000;
    private static final long MILLIS_IN_FUTURE = 5000;
    private CountDownTimer mTimer;
    private PowerViewContract.IView mView;

    public PowerViewPresenter(Context context, PowerViewContract.IView view) {
        super(context);
        this.mView = view;
    }

    @Override // com.ts.appservice.power.view.PowerViewContract.Presenter
    public void startTimer() {
        startCountDownTimer();
    }

    @Override // com.ts.appservice.power.view.PowerViewContract.Presenter
    public void exitTimer() {
        CountDownTimer countDownTimer = this.mTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.mTimer = null;
        }
    }

    private void startCountDownTimer() {
        if (this.mTimer == null) {
            this.mTimer = new CountDownTimer(MILLIS_IN_FUTURE, COUNT_DOWN_INTERVAL) { // from class: com.ts.appservice.power.view.PowerViewPresenter.1
                @Override // android.os.CountDownTimer
                public void onTick(long millisUntilFinished) {
                    PowerViewPresenter.this.mView.updateRemainTime(false, (500 + millisUntilFinished) / PowerViewPresenter.COUNT_DOWN_INTERVAL);
                }

                @Override // android.os.CountDownTimer
                public void onFinish() {
                    PowerViewPresenter.this.mView.updateRemainTime(true, 0L);
                    PowerViewPresenter.this.exitTimer();
                }
            };
        }
        this.mTimer.start();
    }
}
