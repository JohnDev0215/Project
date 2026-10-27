package com.ts.dm.service.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.ts.dm.service.R;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class TopDialog extends CommonDialog implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener, View.OnTouchListener {
    private static final int TIME_AUTOHIDE = 10000;
    private TextView mClose;
    private TextView mContent;
    private float mMoveY;
    private View mRootView;
    private float mStartY;
    private Timer mTimer;

    public TopDialog(Context context) {
        super(context);
        this.mMoveY = 0.0f;
        setContentView(R.layout.toast_common);
        getWindow().setLayout(-1, -1);
        getWindow().setGravity(48);
        getWindow().setBackgroundDrawableResource(R.color.transparent);
        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() { // from class: com.ts.dm.service.view.-$$Lambda$TopDialog$5RZ-q_rY4WbWLZkoLMEKZGmFmJ4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$0$TopDialog(view);
            }
        });
        this.mRootView = findViewById(R.id.toast_view);
        this.mContent = (TextView) findViewById(R.id.toast_content);
        TextView textView = (TextView) findViewById(R.id.toast_close);
        this.mClose = textView;
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.ts.dm.service.view.-$$Lambda$TopDialog$o-OEWDHsU4R7ARiwKzMgX3GRfZA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$new$1$TopDialog(view);
            }
        });
        setOnDismissListener(this);
        setOnShowListener(this);
        this.mRootView.setOnTouchListener(this);
    }

    public /* synthetic */ void lambda$new$0$TopDialog(View view) {
        dismiss();
    }

    public /* synthetic */ void lambda$new$1$TopDialog(View view) {
        dismiss();
    }

    @Override // com.ts.dm.service.view.CommonDialog, android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialog) {
        if (this.mListener != null) {
            this.mListener.onShowChanged(true);
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(new TimerTask() { // from class: com.ts.dm.service.view.TopDialog.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                TopDialog.this.dismiss();
            }
        }, 10000L);
    }

    @Override // com.ts.dm.service.view.CommonDialog, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        if (this.mListener != null) {
            this.mListener.onShowChanged(false);
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent ev) {
        int action = ev.getAction();
        if (action == 0) {
            this.mStartY = ev.getY();
            return true;
        }
        if (action == 2) {
            float y = ev.getY() - this.mStartY;
            this.mMoveY = y;
            if (y <= 0.0f) {
                dismiss();
                return true;
            }
            return true;
        }
        return true;
    }

    @Override // com.ts.dm.service.view.CommonDialog
    public void setDialogRecommend(String content, final View.OnClickListener listener) {
        if (listener == null) {
            return;
        }
        this.mContent.setOnClickListener(new View.OnClickListener() { // from class: com.ts.dm.service.view.-$$Lambda$TopDialog$bC1fvLnGseZFDra5sxFKWLuDv6I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setDialogRecommend$2$TopDialog(listener, view);
            }
        });
    }

    public /* synthetic */ void lambda$setDialogRecommend$2$TopDialog(View.OnClickListener listener, View v) {
        dismiss();
        listener.onClick(v);
    }

    @Override // com.ts.dm.service.view.CommonDialog
    public void setDialogContent(String content) {
        this.mContent.setText(content);
    }
}
