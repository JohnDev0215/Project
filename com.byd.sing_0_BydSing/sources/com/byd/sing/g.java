package com.byd.sing;

import com.loostone.libtuning.channel.byd.old.IDongleListener;
import com.loostone.libtuning.channel.byd.old.PuremicTuning;

/* JADX INFO: compiled from: PuremicTuningProxy.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: compiled from: PuremicTuningProxy.java */
    class a implements IDongleListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f3245a;

        a(d dVar) {
            this.f3245a = dVar;
        }

        @Override // com.loostone.libtuning.channel.byd.old.IDongleListener
        public void attach() {
            d dVar = this.f3245a;
            if (dVar != null) {
                dVar.attach();
            }
        }

        @Override // com.loostone.libtuning.channel.byd.old.IDongleListener
        public void detach() {
            d dVar = this.f3245a;
            if (dVar != null) {
                dVar.detach();
            }
        }

        @Override // com.loostone.libtuning.channel.byd.old.IDongleListener
        public void onMicPowerChanged(int i, boolean z) {
            d dVar = this.f3245a;
            if (dVar != null) {
                dVar.onMicPowerChanged(i, z);
            }
        }

        @Override // com.loostone.libtuning.channel.byd.old.IDongleListener
        public void onMicPowerOnInit() {
            d dVar = this.f3245a;
            if (dVar != null) {
                dVar.onMicPowerOnInit();
            }
        }

        @Override // com.loostone.libtuning.channel.byd.old.IDongleListener
        public void onOutsideDongleRejected() {
        }

        @Override // com.loostone.libtuning.channel.byd.old.IDongleListener
        public void onPairStateChanged(int i, int i2) {
        }
    }

    public static void a() {
        com.byd.sing.a.a("init");
        try {
            PuremicTuning.getInstance().init(SingApplication.a(), j.f().h());
        } catch (Exception e) {
            com.byd.sing.a.g("init error", e);
        }
    }

    public static boolean b() {
        try {
            return PuremicTuning.isLoostoneMicPhone(SingApplication.a());
        } catch (Exception e) {
            com.byd.sing.a.g("isMicConnect error", e);
            return false;
        }
    }

    public static void c(int i) {
        com.byd.sing.a.a("setKaraokeEffect value = " + i);
        try {
            PuremicTuning.getInstance().setKaraokeEffect(i);
        } catch (Exception e) {
            com.byd.sing.a.g("setKaraokeEffect error", e);
        }
    }

    public static void d(d dVar) {
        PuremicTuning.getInstance().setDongleListener(new a(dVar));
    }

    public static void e(int i) {
        int i2 = i * 10;
        com.byd.sing.a.a("setMicPercentage value = " + i2);
        try {
            PuremicTuning.getInstance().setMicPercentage(i2);
        } catch (Exception e) {
            com.byd.sing.a.g("setMicPercentage error", e);
        }
    }

    public static void f(int i) {
        int i2 = i * 10;
        com.byd.sing.a.a("setReverbPercentage value = " + i2);
        try {
            PuremicTuning.getInstance().setReverbPercentage(i2);
        } catch (Exception e) {
            com.byd.sing.a.g("setReverbPercentage error", e);
        }
    }

    public static void g(int i) {
        com.byd.sing.a.a("setStreamVolumeAndMax value:" + i);
        PuremicTuning.getInstance().setStreamVolumeAndMax(i, 39);
    }
}
