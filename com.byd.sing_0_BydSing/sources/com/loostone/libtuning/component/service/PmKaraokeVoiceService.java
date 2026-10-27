package com.loostone.libtuning.component.service;

import a.a.a.a.b;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.loostone.libtuning.PuremicTuning;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.channel.skyworth.old.ai.AiControlMgr;
import com.loostone.libtuning.channel.skyworth.old.record.RecordMgr;
import kotlin.a;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;

/* JADX INFO: loaded from: classes.dex */
public final class PmKaraokeVoiceService extends Service {

    /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
    public final a f3888OooO00o = d.b(OooO0OO.f3890OooO00o);
    public final OooO0O0 OooO0O0 = new OooO0O0();

    public static final class OooO00o {
        public OooO00o() {
        }

        public /* synthetic */ OooO00o(f fVar) {
            this();
        }
    }

    public static final class OooO0O0 extends b {
        public OooO0O0() {
        }

        public int OooO00o(byte[] bArr, int i, int i2) {
            return RecordMgr.getInstance().read(bArr, i, i2);
        }

        public int OooO00o(String str) {
            return AiControlMgr.getInstance().sendCmd(str);
        }

        public int OooO00o() {
            return PmKaraokeVoiceService.this.OooO00o().getVerifyStatus() == 2 ? 1 : 0;
        }
    }

    public static final class OooO0OO extends Lambda implements kotlin.jvm.b.a<BaseChannelMgr> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0OO f3890OooO00o = new OooO0OO();

        public OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final BaseChannelMgr invoke() {
            return PuremicTuning.Companion.getInstance().getChannelMgr();
        }
    }

    static {
        new OooO00o(null);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        OooO00o.OooO00o.OooO0O0.b.f41a.e("PmKaraokeVoiceService", "  onBind");
        return this.OooO0O0;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        AiControlMgr.getInstance().create(getApplicationContext());
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        AiControlMgr.getInstance().destroy();
    }

    public final BaseChannelMgr OooO00o() {
        return (BaseChannelMgr) this.f3888OooO00o.getValue();
    }
}
