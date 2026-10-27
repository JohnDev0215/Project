package com.byd.auto_photo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.byd.util.GearboxUtil;
import com.byd.util.w;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseAutoFinishActivity extends BaseDialogActivity implements View.OnClickListener {
    private static boolean g = false;
    private static BaseAutoFinishActivity h;
    private b i;
    protected TextView j;
    protected TextView k;
    private int l = 10;

    private static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<BaseAutoFinishActivity> f986a;

        private b(BaseAutoFinishActivity baseAutoFinishActivity) {
            this.f986a = new WeakReference<>(baseAutoFinishActivity);
        }

        public void a() {
            WeakReference<BaseAutoFinishActivity> weakReference = this.f986a;
            if (weakReference != null) {
                weakReference.clear();
                this.f986a = null;
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            WeakReference<BaseAutoFinishActivity> weakReference = this.f986a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.f986a.get().p();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        int i = this.l - 1;
        this.l = i;
        if (i == 0) {
            r();
        } else {
            this.k.setText(String.format(Locale.US, getResources().getString(R.string.sure_counter), Integer.valueOf(this.l)));
            this.i.sendEmptyMessageDelayed(1, 1000L);
        }
    }

    public static void q() {
        BaseAutoFinishActivity baseAutoFinishActivity = h;
        if (baseAutoFinishActivity != null) {
            baseAutoFinishActivity.s();
            g = false;
            h = null;
        }
    }

    private void r() {
        s();
        Set<Activity> setB = PhotoBaseApp.b();
        if (setB.size() > 0) {
            int size = setB.size();
            Activity[] activityArr = new Activity[size];
            setB.toArray(activityArr);
            for (int i = size - 1; i >= 0; i--) {
                activityArr[i].finish();
            }
        }
    }

    private void s() {
        this.i.removeCallbacksAndMessages(null);
        finish();
    }

    public static boolean u() {
        return g;
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void h() {
        setContentView(R.layout.activity_auto_finish);
        TextView textView = (TextView) findViewById(R.id.tv_msg);
        this.j = textView;
        textView.setText(R.string.app_disable_gearbox);
        TextView textView2 = (TextView) findViewById(R.id.tv_sure);
        this.k = textView2;
        textView2.setText(String.format(Locale.US, getResources().getString(R.string.sure_counter), Integer.valueOf(this.l)));
        this.k.setOnClickListener(this);
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity
    protected void k(Configuration configuration) {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        r();
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        g = true;
        h = this;
        super.onCreate(bundle);
        b bVar = new b();
        this.i = bVar;
        bVar.sendEmptyMessageDelayed(1, 1000L);
        w.c("AutoFinish onCreate");
    }

    @Override // com.byd.auto_photo.BaseDialogSupActivity, android.app.Activity
    protected void onDestroy() {
        b bVar = this.i;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
            this.i.a();
            this.i = null;
        }
        super.onDestroy();
        g = false;
        h = null;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.l = 10;
        TextView textView = this.k;
        if (textView != null) {
            textView.setText(String.format(Locale.US, getResources().getString(R.string.sure_counter), Integer.valueOf(this.l)));
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        w.c("AutoFinish onPause");
        if (GearboxUtil.i((PhotoApp) getApplication()).g() != 1) {
            r();
        }
    }
}
