package com.byd.uereport.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;
import com.byd.uereport.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import i5.a;

/* JADX INFO: loaded from: classes2.dex */
public class DelayDismissTextView extends AppCompatTextView implements View.OnClickListener {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public View.OnClickListener f3421h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Handler f3422i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3423j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f3424k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final byte f3425l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final byte f3426m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f3427n;

    public DelayDismissTextView(Context context) {
        super(context);
        this.f3422i = new Handler(Looper.getMainLooper(), new a(this));
        this.f3425l = (byte) 7;
        this.f3426m = (byte) 0;
        this.f3427n = 0;
    }

    public void f(int i8) {
        this.f3424k = i8;
    }

    public final boolean g(Message message) {
        if (message.what != 1028) {
            return true;
        }
        if (message.arg1 == 0) {
            View.OnClickListener onClickListener = this.f3421h;
            if (onClickListener != null) {
                onClickListener.onClick(null);
            }
        } else {
            if (this.f3427n == 0) {
                setText(getContext().getString(R.string.display_dialog_confirm, Integer.valueOf(message.arg1)));
            } else {
                setText(getContext().getString(this.f3427n, Integer.valueOf(message.arg1)));
            }
            int i8 = message.arg1 - 1;
            this.f3424k = i8;
            Message messageObtainMessage = this.f3422i.obtainMessage(1028, "DelayDismissTextView");
            messageObtainMessage.what = 1028;
            messageObtainMessage.arg1 = i8;
            this.f3422i.sendMessageDelayed(messageObtainMessage, 1000L);
        }
        return true;
    }

    public int getValaue() {
        return this.f3424k;
    }

    public final void h(Context context) {
        BydLogHelper.logI("DelayDismissTextView", "init is begin");
    }

    public void j() {
        Handler handler;
        if (this.f3423j && (handler = this.f3422i) != null && handler.hasMessages(1028)) {
            this.f3422i.removeMessages(1028);
            this.f3422i = null;
        }
    }

    public void k() {
        Handler handler;
        if (!this.f3423j || (handler = this.f3422i) == null) {
            return;
        }
        if (handler.hasMessages(1028)) {
            this.f3422i.removeMessages(1028);
        }
        Handler handler2 = new Handler(Looper.getMainLooper(), new a(this));
        this.f3422i = handler2;
        Message messageObtainMessage = handler2.obtainMessage(1028, "DelayDismissTextView");
        messageObtainMessage.what = 1028;
        messageObtainMessage.arg1 = 7;
        this.f3422i.sendMessageDelayed(messageObtainMessage, 1000L);
    }

    public void l(View.OnClickListener onClickListener, boolean z7) {
        BydLogHelper.logI("DelayDismissTextView", "setConfirmBtnClick is begin isSendDelayMsg =" + z7);
        this.f3421h = onClickListener;
        this.f3423j = z7;
        setOnClickListener(onClickListener);
    }

    public void m(int i8, boolean z7) {
        this.f3423j = z7;
        this.f3427n = i8;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        BydLogHelper.logI("DelayDismissTextView", "onAttachedToWindow is begin isDelay =" + this.f3423j);
        if (this.f3423j) {
            if (this.f3422i.hasMessages(1028)) {
                this.f3422i.removeMessages(1028);
            }
            Message messageObtainMessage = this.f3422i.obtainMessage(1028, "DelayDismissTextView");
            messageObtainMessage.what = 1028;
            int i8 = this.f3424k;
            if (i8 <= 0) {
                i8 = 7;
            }
            messageObtainMessage.arg1 = i8;
            this.f3422i.sendMessage(messageObtainMessage);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        BydLogHelper.logI("DelayDismissTextView", "onClick: mListener.onCancelClick()");
        View.OnClickListener onClickListener = this.f3421h;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        j();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        j();
    }

    public DelayDismissTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3422i = new Handler(Looper.getMainLooper(), new a(this));
        this.f3425l = (byte) 7;
        this.f3426m = (byte) 0;
        this.f3427n = 0;
        h(context);
    }
}
