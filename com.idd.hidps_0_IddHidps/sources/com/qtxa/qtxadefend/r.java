package com.qtxa.qtxadefend;

import com.qtxa.qtxadefend.s;
import java.util.ArrayList;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
final class r extends TimerTask {

    class a implements s.a {
        a(r rVar) {
        }

        @Override // com.qtxa.qtxadefend.s.a
        public void a(String str) {
        }
    }

    r() {
    }

    void a() {
        new ArrayList();
        new s().a(new a(this));
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        a();
    }
}
