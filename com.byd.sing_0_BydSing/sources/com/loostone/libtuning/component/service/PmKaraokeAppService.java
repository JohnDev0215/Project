package com.loostone.libtuning.component.service;

import OooO00o.OooO00o.OooO0O0.b;
import a.a.a.a.a;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.loostone.libtuning.channel.skyworth.old.ai.AiControlFactory;
import com.loostone.libtuning.channel.skyworth.old.ai.IAiControl;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class PmKaraokeAppService extends Service {

    /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
    public IAiControl f3886OooO00o;
    public final IBinder OooO0O0 = new OooO0O0();

    public static final class OooO00o {
        public OooO00o() {
        }

        public /* synthetic */ OooO00o(f fVar) {
            this();
        }
    }

    static {
        new OooO00o(null);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        b.f41a.b("PmKaraokeAppService", "onBind");
        return this.OooO0O0;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        b.f41a.b("PmKaraokeAppService", "onCreate");
        this.f3886OooO00o = AiControlFactory.getAiControl();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        b.f41a.b("PmKaraokeAppService", "onUnbind");
        IAiControl iAiControl = this.f3886OooO00o;
        if (iAiControl != null) {
            iAiControl.exitKaraokeApp();
        }
        return super.onUnbind(intent);
    }

    public static final class OooO0O0 extends a {
        public OooO0O0() {
        }

        public void OooO00o(String str) {
            IAiControl iAiControl;
            h.d(str, "packageName");
            b.f41a.b("PmKaraokeAppService", h.i("enterApp,", str));
            if (PmKaraokeAppService.this.f3886OooO00o == null || (iAiControl = PmKaraokeAppService.this.f3886OooO00o) == null) {
                return;
            }
            iAiControl.enterKaraokeApp(str);
        }

        public void OooO0O0() {
            IAiControl iAiControl;
            b.f41a.b("PmKaraokeAppService", "exitApp");
            if (PmKaraokeAppService.this.f3886OooO00o == null || (iAiControl = PmKaraokeAppService.this.f3886OooO00o) == null) {
                return;
            }
            iAiControl.exitKaraokeApp();
        }

        public void OooO0OO() {
            IAiControl iAiControl;
            b.f41a.b("PmKaraokeAppService", "exitPlayer");
            if (PmKaraokeAppService.this.f3886OooO00o == null || (iAiControl = PmKaraokeAppService.this.f3886OooO00o) == null) {
                return;
            }
            iAiControl.exitKaraokePlayer();
        }

        public void OooO00o() {
            IAiControl iAiControl;
            b.f41a.b("PmKaraokeAppService", "enterPlayer");
            if (PmKaraokeAppService.this.f3886OooO00o == null || (iAiControl = PmKaraokeAppService.this.f3886OooO00o) == null) {
                return;
            }
            iAiControl.enterKaraokePlayer();
        }
    }
}
