package com.android.settingslib.deviceinfo;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.format.DateUtils;
import androidx.annotation.VisibleForTesting;
import androidx.preference.Preference;
import com.android.settingslib.core.lifecycle.b.i;
import com.android.settingslib.core.lifecycle.b.j;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public abstract class AbstractUptimePreferenceController extends com.android.settingslib.d.a implements com.android.settingslib.core.lifecycle.a, i, j {

    @VisibleForTesting
    static final String KEY_UPTIME = "up_time";
    private Preference d;
    private Handler e;

    /* JADX INFO: compiled from: caradapter-bug */
    private static class a extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<AbstractUptimePreferenceController> f1349a;

        public a(AbstractUptimePreferenceController abstractUptimePreferenceController) {
            this.f1349a = new WeakReference<>(abstractUptimePreferenceController);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AbstractUptimePreferenceController abstractUptimePreferenceController = this.f1349a.get();
            if (abstractUptimePreferenceController == null) {
                return;
            }
            if (message.what == 500) {
                abstractUptimePreferenceController.p();
                sendEmptyMessageDelayed(500, 1000L);
            } else {
                throw new IllegalStateException("Unknown message " + message.what);
            }
        }
    }

    private Handler o() {
        if (this.e == null) {
            this.e = new a(this);
        }
        return this.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        this.d.u0(DateUtils.formatElapsedTime(SystemClock.elapsedRealtime() / 1000));
    }

    @Override // com.android.settingslib.core.lifecycle.b.i
    public void c() {
        o().sendEmptyMessage(500);
    }

    @Override // com.android.settingslib.core.lifecycle.b.j
    public void g() {
        o().removeMessages(500);
    }
}
