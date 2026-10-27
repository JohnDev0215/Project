package com.byd.uereport.dialog;

import android.content.Context;
import android.hardware.bydauto.setting.BYDAutoSettingDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.View;
import b5.g;
import com.byd.uereport.R;
import com.byd.uereport.widget.DelayDismissTextView;
import p3.f;
import widget.DialogTouchLinearLayout;

/* JADX INFO: loaded from: classes2.dex */
public class DisplayEngineDialog extends g {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte f3218d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final byte f3219e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Handler f3220f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public DelayDismissTextView f3221g;

    public DisplayEngineDialog(Context context) {
        super(context);
        this.f3218d = (byte) 7;
        this.f3219e = (byte) 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void f(View view) {
        onClick(this.f3221g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void h(boolean z7) {
        BYDAutoSettingDevice.getInstance(this.mContext).setExhibitionStartEnginePermit(z7 ? 1 : 2);
    }

    public final boolean c(Message message) {
        int i8 = message.what;
        if (i8 == 1028) {
            int i9 = message.arg1;
            if (i9 == 0) {
                if (i8 == 1028 && this.f3220f != null) {
                    BYDAutoSettingDevice.getInstance(this.mContext).setExhibitionStartEnginePermit(2);
                    f.getInstance().a(8, "DisplayEngineDialog", "dismissDialog", new f.a() { // from class: b5.f
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f2078d.dismiss();
                        }
                    });
                    this.f3220f.removeMessages(1028);
                }
            } else if (this.f3221g != null) {
                Message messageObtainMessage = this.f3220f.obtainMessage(1028, "DisplayEngineDialog");
                messageObtainMessage.what = 1028;
                messageObtainMessage.arg1 = i9 - 1;
                this.f3220f.sendMessageDelayed(messageObtainMessage, 1000L);
            }
        }
        return true;
    }

    public final void i(final boolean z7) {
        f.getInstance().a(2, "DisplayEngineDialog", "setEngineEnable", new f.a() { // from class: b5.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f2075d.h(z7);
            }
        });
    }

    @Override // b5.g
    public void initContent(View view) {
        view.findViewById(R.id.tv_engine_allow_yes).setOnClickListener(this);
        DelayDismissTextView delayDismissTextView = (DelayDismissTextView) view.findViewById(R.id.tv_engine_forbid_no);
        this.f3221g = delayDismissTextView;
        delayDismissTextView.f(8);
        this.f3221g.m(R.string.display_not_start_the_engine, true);
        this.f3221g.setOnClickListener(this);
        if (view instanceof DialogTouchLinearLayout) {
            ((DialogTouchLinearLayout) view).b(getLayoutParams(), new DialogTouchLinearLayout.a() { // from class: b5.c
                @Override // widget.DialogTouchLinearLayout.a
                public final void removeView(View view2) {
                    this.f2074a.f(view2);
                }
            });
        }
    }

    @Override // b5.g
    public void initData() {
        HandlerThread handlerThread = new HandlerThread("DisplayEngineDialog");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper(), new Handler.Callback() { // from class: b5.b
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.f2073d.c(message);
            }
        });
        this.f3220f = handler;
        Message messageObtainMessage = handler.obtainMessage(1028, "DisplayEngineDialog");
        messageObtainMessage.what = 1028;
        messageObtainMessage.arg1 = 7;
        this.f3220f.sendMessageDelayed(messageObtainMessage, 1000L);
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_display_allow_engine_tip;
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        i(view.getId() == R.id.tv_engine_allow_yes);
        Handler handler = this.f3220f;
        if (handler != null && handler.hasMessages(1028)) {
            this.f3220f.removeMessages(1028);
        }
        dismiss();
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
    }
}
