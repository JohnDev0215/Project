package com.byd.sing.service;

import android.accessibilityservice.AccessibilityService;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import com.byd.sing.R;
import com.byd.sing.SingApplication;
import com.byd.sing.ThreadManager;
import com.byd.sing.f;
import com.byd.sing.j;
import com.byd.sing.k;
import com.byd.sing.l;
import com.byd.sing.view.a.c;
import com.byd.sing.view.a.d;
import com.byd.sing.view.a.g;
import com.byd.sing.view.a.i;

/* JADX INFO: loaded from: classes.dex */
public class MicKeyService extends AccessibilityService {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private BYDAutoAudioDevice f3280b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BYDAutoBodyworkDevice f3281c;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Instrumentation instrumentation = new Instrumentation();
            instrumentation.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0f, 0.0f, 0));
            instrumentation.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0f, 0.0f, 0));
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Instrumentation instrumentation = new Instrumentation();
            instrumentation.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 0, 0.0f, 0.0f, 0));
            instrumentation.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), 1, 0.0f, 0.0f, 0));
        }
    }

    private boolean a() {
        return (f.a(this) && f.b(this)) ? false : true;
    }

    private boolean b() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.f3281c;
        if (bYDAutoBodyworkDevice == null) {
            return false;
        }
        int powerLevel = bYDAutoBodyworkDevice.getPowerLevel();
        com.byd.sing.a.n("level = " + powerLevel);
        if (powerLevel != 0) {
            return false;
        }
        com.byd.sing.a.n("acc off not handle");
        return true;
    }

    private boolean c(int i) {
        return i == 131 || i == 132 || i == 133 || i == 134 || i == 135 || i == 140 || i == 141 || i == 142;
    }

    private void d() {
        if (j.f().x(i.class.getSimpleName())) {
            return;
        }
        j.f().S(new i(1));
    }

    public static void f() {
        if (j.f().u()) {
            SingApplication.a().sendBroadcast(new Intent("com.byd.action.quit.power"));
            return;
        }
        int i = j.f().i();
        com.byd.sing.a.a("screenState = " + i);
        if (i != 1) {
            ThreadManager.h().i(new b());
        } else {
            if (j.f().x(com.byd.sing.view.a.j.class.getSimpleName())) {
                return;
            }
            j.f().b();
            j.f().S(new com.byd.sing.view.a.j());
        }
    }

    public static void g(Context context) {
        Settings.Secure.putString(context.getContentResolver(), "enabled_accessibility_services", "com.byd.sing/.service.MicKeyService");
        Settings.Secure.putInt(context.getContentResolver(), "accessibility_enabled", 1);
    }

    public void e() {
        if (!j.f().x(com.byd.sing.view.a.j.class.getSimpleName())) {
            f();
            return;
        }
        g gVarE = j.f().e(com.byd.sing.view.a.j.class.getSimpleName());
        if (gVarE != null) {
            ((c) gVarE).F();
        }
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        com.byd.sing.a.a("onCreate");
        this.f3281c = BYDAutoBodyworkDevice.getInstance(this);
        this.f3280b = BYDAutoAudioDevice.getInstance(this);
    }

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected boolean onKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        com.byd.sing.a.a("onKeyEvent keyCode = " + keyCode);
        if (!c(keyCode)) {
            return super.onKeyEvent(keyEvent);
        }
        if (!k.g()) {
            return super.onKeyEvent(keyEvent);
        }
        if (1 == keyEvent.getAction()) {
            if (j.f().p()) {
                l.a(R.string.call_active);
                return true;
            }
            switch (keyCode) {
                case 131:
                    boolean zB = com.byd.sing.i.b("key_start", false);
                    com.byd.sing.a.a("onKeyEvent power on isStartThirdApp = " + zB);
                    if (zB && !j.f().v()) {
                        if (j.f().i() != 1) {
                            ThreadManager.h().i(new a());
                        }
                        k.j();
                    }
                    this.f3280b.setKtvResource(1);
                    Intent intent = new Intent("byd.intent.minikaraoke_micevent");
                    intent.putExtra("android.intent.extra.KEY_EVENT", 131);
                    sendBroadcast(intent);
                    break;
                case 132:
                    com.byd.sing.a.a("onKeyEvent power off");
                    this.f3280b.setKtvResource(2);
                    Intent intent2 = new Intent("byd.intent.minikaraoke_micevent");
                    intent2.putExtra("android.intent.extra.KEY_EVENT", 132);
                    sendBroadcast(intent2);
                    break;
                case 133:
                    com.byd.sing.a.a("onKeyEvent function");
                    if (!a()) {
                        com.byd.sing.a.a("karaokeAppsState = " + k.b());
                        if (!b() && !j.f().v()) {
                            if (!j.f().o()) {
                                d();
                            } else {
                                e();
                            }
                        }
                    }
                    break;
                case 134:
                    com.byd.sing.a.a("onKeyEvent volume add");
                    if (!a() && !b() && !j.f().v()) {
                        if (!j.f().o()) {
                            d();
                        } else {
                            j.f().P(j.f().k() + 1);
                            org.greenrobot.eventbus.c.c().k(new com.byd.sing.service.a(0));
                            f();
                        }
                    }
                    break;
                case 135:
                    com.byd.sing.a.a("onKeyEvent volume minus");
                    if (!a() && !b() && !j.f().v()) {
                        if (!j.f().o()) {
                            d();
                        } else {
                            j.f().P(j.f().k() - 1);
                            org.greenrobot.eventbus.c.c().k(new com.byd.sing.service.a(1));
                            f();
                        }
                    }
                    break;
                case 140:
                    com.byd.sing.a.a("onKeyEvent low power");
                    if (!a() && !b() && !j.f().v()) {
                        j.f().S(new d(-1));
                    }
                    break;
                case 141:
                    com.byd.sing.a.a("onKeyEvent charging");
                    break;
                case 142:
                    com.byd.sing.a.a("onKeyEvent charged");
                    break;
            }
        }
        return super.onKeyEvent(keyEvent);
    }
}
