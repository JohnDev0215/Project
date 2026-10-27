package com.loostone.libtuning;

import OooO00o.OooO00o.OooO0O0.b;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o;
import OooO00o.OooO00o.OooO0o0.OooO0o0.OooO0OO.OooO0OO;
import android.app.Application;
import android.content.Context;
import android.util.ArrayMap;
import com.loostone.libtuning.channel.BaseChannelMgr;
import com.loostone.libtuning.channel.FullSceneSpeakMgr;
import com.loostone.libtuning.channel.adjust.AdjustAMgr;
import com.loostone.libtuning.channel.baidu.BaiDuAMgr;
import com.loostone.libtuning.channel.byd.BiYaDiAMgr;
import com.loostone.libtuning.channel.huawei.HuaWeiBMgr;
import com.loostone.libtuning.channel.oppo.OPPOAMgr;
import com.loostone.libtuning.channel.skyworth.SkyworthAMgr;
import com.loostone.libtuning.channel.skyworth.SkyworthBMgr;
import com.loostone.libtuning.channel.skyworth.SkyworthCMgr;
import com.loostone.libtuning.define.BaseDefine;
import com.loostone.libtuning.inf.extern.IVolumeAdjustment;
import com.loostone.libtuning.process.dongle.LocalDongleManager;
import kotlin.LazyThreadSafetyMode;
import kotlin.Result;
import kotlin.a;
import kotlin.d;
import kotlin.g;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;
import kotlin.l;
import kotlin.reflect.i;

/* JADX INFO: loaded from: classes.dex */
public final class PuremicTuning {
    private static final String TAG = "PuremicTuning";
    private final a fullSceneSpeakMgr$delegate;
    private boolean isInit;
    private final ArrayMap<String, String> propertiesMap;
    public static final Companion Companion = new Companion(null);
    private static final a<PuremicTuning> instance$delegate = d.a(LazyThreadSafetyMode.SYNCHRONIZED, OooO00o.f3811OooO00o);

    public static final class Companion {
        public static final /* synthetic */ i<Object>[] $$delegatedProperties = {j.d(new PropertyReference1Impl(j.a(Companion.class), "instance", "getInstance()Lcom/loostone/libtuning/PuremicTuning;"))};

        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }

        public final PuremicTuning getInstance() {
            return (PuremicTuning) PuremicTuning.instance$delegate.getValue();
        }
    }

    public static final class OooO00o extends Lambda implements kotlin.jvm.b.a<PuremicTuning> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO00o f3811OooO00o = new OooO00o();

        public OooO00o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final PuremicTuning invoke() {
            return new PuremicTuning(null);
        }
    }

    public static final class OooO0O0 extends Lambda implements kotlin.jvm.b.a<BaseChannelMgr> {
        public OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final BaseChannelMgr invoke() {
            return PuremicTuning.this.createChannelMgr();
        }
    }

    private PuremicTuning() {
        this.fullSceneSpeakMgr$delegate = d.b(new OooO0O0());
        this.propertiesMap = new ArrayMap<>();
    }

    public /* synthetic */ PuremicTuning(f fVar) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BaseChannelMgr createChannelMgr() {
        Long lA = OooO0o.f425a.j().a();
        int i = 1;
        Context context = null;
        byte b2 = 0;
        byte b3 = 0;
        byte b4 = 0;
        byte b5 = 0;
        byte b6 = 0;
        byte b7 = 0;
        byte b8 = 0;
        byte b9 = 0;
        byte b10 = 0;
        byte b11 = 0;
        byte b12 = 0;
        byte b13 = 0;
        byte b14 = 0;
        byte b15 = 0;
        byte b16 = 0;
        byte b17 = 0;
        byte b18 = 0;
        if (lA != null && lA.longValue() == 100001) {
            b.f41a.b(TAG, " ------> HuaWei B");
            return new HuaWeiBMgr(context, i, b18 == true ? 1 : 0);
        }
        if (lA != null && lA.longValue() == 200000) {
            b.f41a.b(TAG, " ------> SKYWORTH A");
            return new SkyworthAMgr(b17 == true ? 1 : 0, i, b16 == true ? 1 : 0);
        }
        if (lA != null && lA.longValue() == 200001) {
            b.f41a.b(TAG, " ------> SKYWORTH B");
            return new SkyworthBMgr(b15 == true ? 1 : 0, i, b14 == true ? 1 : 0);
        }
        if (lA != null && lA.longValue() == 200002) {
            b.f41a.b(TAG, " ------> SKYWORTH C");
            return new SkyworthCMgr(b13 == true ? 1 : 0, i, b12 == true ? 1 : 0);
        }
        if (lA != null && lA.longValue() == 1) {
            b.f41a.b(TAG, " ------> ADJUST A");
            return new AdjustAMgr(b11 == true ? 1 : 0, i, b10 == true ? 1 : 0);
        }
        if (lA != null && lA.longValue() == 300000) {
            b.f41a.b(TAG, " ------> BaiDu A");
            return new BaiDuAMgr(b9 == true ? 1 : 0, i, b8 == true ? 1 : 0);
        }
        if (lA != null && lA.longValue() == 400000) {
            b.f41a.b(TAG, " ------> BYD A");
            return new BiYaDiAMgr(b7 == true ? 1 : 0, i, b6 == true ? 1 : 0);
        }
        if (lA != null && lA.longValue() == 500000) {
            b.f41a.b(TAG, " ------> OPPO A");
            return new OPPOAMgr(b5 == true ? 1 : 0, i, b4 == true ? 1 : 0);
        }
        b.f41a.b(TAG, " ------> Common");
        return new FullSceneSpeakMgr(b3 == true ? 1 : 0, i, b2 == true ? 1 : 0);
    }

    private final BaseChannelMgr getFullSceneSpeakMgr() {
        return (BaseChannelMgr) this.fullSceneSpeakMgr$delegate.getValue();
    }

    public final BaseChannelMgr getChannelMgr() {
        return getFullSceneSpeakMgr();
    }

    public final LocalDongleManager getDongleManager() {
        return getFullSceneSpeakMgr().getLocalDongleManager();
    }

    public final IVolumeAdjustment getVolumeAdjustment() {
        return getFullSceneSpeakMgr().getVolumeAdjustment();
    }

    public final void init(Application application) {
        h.d(application, "context");
        b.f41a.e(TAG, h.i("------>", application));
        init(application, 400000L, 255);
    }

    public final void release() {
        this.isInit = false;
        getFullSceneSpeakMgr().release();
        a.a.d.a.b.a.f562a.d();
    }

    public final void init(Application application, long j, int i) {
        h.d(application, "context");
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put(BaseDefine.SystemPropertiesKey.CAR_TYPE_CODE, String.valueOf(i));
        init(application, j, arrayMap);
    }

    public final void init(Application application, long j, ArrayMap<String, String> arrayMap) {
        Object objM23constructorimpl;
        l lVar;
        h.d(application, "context");
        h.d(arrayMap, "dataMaps");
        this.propertiesMap.putAll((ArrayMap<? extends String, ? extends String>) arrayMap);
        if (this.isInit) {
            return;
        }
        b.f41a.e(TAG, " ------> SDK VERSION 4.0.32_2025-03-25 18:00:02_5fec3632");
        OooO00o.OooO00o.OooO0O0.a.f39a.b(application);
        OooO0o.f425a.j().e(Long.valueOf(j));
        int i = 255;
        l lVar2 = null;
        try {
            String str = this.propertiesMap.get(BaseDefine.SystemPropertiesKey.CAR_TYPE_CODE);
            if (str == null) {
                lVar = null;
            } else {
                i = Integer.parseInt(str);
                lVar = l.f4084a;
            }
            objM23constructorimpl = Result.m23constructorimpl(lVar);
        } catch (Throwable th) {
            objM23constructorimpl = Result.m23constructorimpl(g.a(th));
        }
        int i2 = i;
        Throwable thM26exceptionOrNullimpl = Result.m26exceptionOrNullimpl(objM23constructorimpl);
        if (thM26exceptionOrNullimpl != null) {
            b.f41a.d(TAG, h.i("init carType error :: ", thM26exceptionOrNullimpl));
            thM26exceptionOrNullimpl.printStackTrace();
        }
        OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0 oooO0O0 = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0.f354a;
        oooO0O0.k(this.propertiesMap);
        OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0.i(oooO0O0, j, i2, false, 4);
        try {
            String str2 = this.propertiesMap.get(BaseDefine.SystemPropertiesKey.SCREEN_LIFT_STATE);
            if ((!(str2 == null || str2.length() == 0) ? this : null) != null) {
                getDongleManager().setScreenLiftState(h.a("true", str2));
                lVar2 = l.f4084a;
            }
            Result.m23constructorimpl(lVar2);
        } catch (Throwable th2) {
            Result.m23constructorimpl(g.a(th2));
        }
        getFullSceneSpeakMgr().init();
        OooO0OO.f536a.p();
        OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0.f354a.t();
        this.isInit = true;
    }
}
