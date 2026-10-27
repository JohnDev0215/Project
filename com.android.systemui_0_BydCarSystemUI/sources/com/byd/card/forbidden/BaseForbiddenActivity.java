package com.byd.card.forbidden;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.TextView;
import androidx.core.view.GravityCompat;
import com.android.systemui.R;
import com.ts.avm.sdk.impl.AvmManagerImpl;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseForbiddenActivity extends Activity {
    private static final String TAG = "BaseForbiddenActivity";

    protected abstract void handleQuit();

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addWindowFlag();
        setContentView(R.layout.forbidden_activity);
        final TextView textView = (TextView) findViewById(R.id.forbidden_text);
        textView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.byd.card.forbidden.BaseForbiddenActivity.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (textView.getLineCount() > 2) {
                    textView.setGravity(GravityCompat.START);
                    textView.setTextAlignment(2);
                } else {
                    textView.setGravity(17);
                    textView.setTextAlignment(4);
                }
                textView.getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });
        EventBus.getDefault().register(this);
        findViewById(R.id.forbidden_quit).setOnClickListener(new View.OnClickListener() { // from class: com.byd.card.forbidden.BaseForbiddenActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BaseForbiddenActivity.this.onQuitButtonClicked();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ForbiddenEvent event) {
        Log.d(TAG, "onEvent " + event.mEventId);
        if (event.mEventId != 0) {
            return;
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onQuitButtonClicked() {
        Log.d(TAG, "onQuitButtonClick");
        finish();
        handleQuit();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "onBackPressed");
        handleQuit();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    protected void sendHomeKey() {
        Log.d(TAG, "sendHomeKey");
        long jUptimeMillis = SystemClock.uptimeMillis();
        sendEvent(3, 0, 0, jUptimeMillis, jUptimeMillis);
        sendEvent(3, 1, 0, jUptimeMillis, jUptimeMillis);
    }

    private void addWindowFlag() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(AvmManagerImpl.AVM_CALIBRATION_CODE);
        window.setStatusBarColor(0);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.getDecorView().setSystemUiVisibility(1792);
        getWindow().setBackgroundDrawable(new ColorDrawable(getColor(R.color.forbidden_app_window_background)));
    }

    private void sendEvent(int keyCode, int action, int flags, long down, long when) {
        KeyEvent keyEvent = new KeyEvent(down, when, action, keyCode, (flags & 128) != 0 ? 1 : 0, 0, -1, 0, flags | 8 | 64, 257);
        keyEvent.setDisplayId(0);
        InputManager.getInstance().injectInputEvent(keyEvent, 0);
    }

    public static class ForbiddenEvent {
        public static final int EVENT_BUS_FINISH = 0;
        public static final int EVENT_BUS_INVALID = -1;
        public int mEventId;

        public ForbiddenEvent(int eventId) {
            this.mEventId = -1;
            this.mEventId = eventId;
        }
    }
}
