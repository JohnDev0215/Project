package com.byd.profile.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class TimeView extends AppCompatTextView {
    private static final int WHAT_TIME = 1;
    private boolean is12Hour;
    private Handler mHandler;
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm", Locale.US);
    private static final SimpleDateFormat s12DateFormat = new SimpleDateFormat("hh:mm", Locale.US);

    public TimeView(Context context) {
        this(context, null);
    }

    public TimeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mHandler = new Handler() { // from class: com.byd.profile.view.TimeView.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    TimeView timeView = TimeView.this;
                    timeView.setText(timeView.getTimeText());
                    TimeView.this.mHandler.sendEmptyMessageDelayed(1, 1000L);
                }
            }
        };
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mHandler.sendEmptyMessage(1);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getTimeText() {
        return this.is12Hour ? s12DateFormat.format(new Date()) : sDateFormat.format(new Date());
    }

    public void set12Hour(boolean is12Hour) {
        this.is12Hour = is12Hour;
        setText(getTimeText());
    }
}
