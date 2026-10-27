package OooO00o.OooO00o.OooO0o0.OooO00o.OooO00o;

import OooO00o.OooO00o.OooO0o0.OooO0o.OooOO0;
import a.a.d.c.c;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.Uri;
import android.text.TextUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.i;
import com.blankj.utilcode.util.j;
import com.loostone.libbottom.util.permission.PermissionActivity;
import com.loostone.libserver.version2.bean.PlatformParams;
import com.loostone.libtuning.BuildConfig;
import com.loostone.libtuning.PuremicTuning;
import com.loostone.libtuning.R;
import com.loostone.libtuning.channel.byd.upgrade.util.DongleUpdateUtil;
import com.loostone.libtuning.channel.byd.upgrade.util.MicUpdateUtil;
import com.loostone.libtuning.data.bean.TuningInfo;
import com.loostone.libtuning.inf.extern.IVolumeAdjustment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.d;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class OooO00o extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f165a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.a f166b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final kotlin.a f167c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f168d;

    public static final class OooO extends Lambda implements kotlin.jvm.b.a<IVolumeAdjustment> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO f169OooO00o = new OooO();

        public OooO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final IVolumeAdjustment invoke() {
            return PuremicTuning.Companion.getInstance().getVolumeAdjustment();
        }
    }

    public static final class OooO0O0 extends Lambda implements kotlin.jvm.b.a<AudioManager> {
        public OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final AudioManager invoke() {
            Object systemService = OooO00o.this.f165a.getSystemService("audio");
            Objects.requireNonNull(systemService, "null cannot be cast to non-null type android.media.AudioManager");
            return (AudioManager) systemService;
        }
    }

    public static final class OooO0o extends Lambda implements kotlin.jvm.b.a<ArrayList<c>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0o f171OooO00o = new OooO0o();

        public OooO0o() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final ArrayList<c> invoke() {
            return new ArrayList<>();
        }
    }

    public static final class a {
        public a() {
        }

        public /* synthetic */ a(f fVar) {
            this();
        }
    }

    public static final class b implements OooO00o.OooO00o.OooO0O0.OooO0o0.e.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f172a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ String f173b;

        public b(String str, String str2) {
            this.f172a = str;
            this.f173b = str2;
        }

        @Override // OooO00o.OooO00o.OooO0O0.OooO0o0.e.a
        public void a() {
            if (!i.b(this.f172a)) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("InfoReceiver", "create tuning_info error");
                return;
            }
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(this.f172a), "UTF-8");
                outputStreamWriter.write(this.f173b);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                ToastUtils.v("write tuning_info success", new Object[0]);
            } catch (Exception e) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("InfoReceiver", "write tuning_info error");
                e.printStackTrace();
            }
        }

        @Override // OooO00o.OooO00o.OooO0O0.OooO0o0.e.a
        public void b(String[] strArr) {
        }
    }

    static {
        new a(null);
    }

    public OooO00o(Context context) {
        h.d(context, "context");
        this.f165a = context;
        this.f166b = d.b(OooO0o.f171OooO00o);
        d.b(new OooO0O0());
        this.f167c = d.b(OooO.f169OooO00o);
    }

    public final List<c> b() {
        return (List) this.f166b.getValue();
    }

    public final void c(int i) {
        synchronized (b()) {
            Iterator<c> it = b().iterator();
            while (it.hasNext()) {
                it.next().onBydMicKeyEvent(i);
            }
        }
    }

    public final synchronized void d(c cVar) {
        h.d(cVar, "callback");
        b().add(cVar);
    }

    public final void e(String str) {
        String strI = j.i(new TuningInfo());
        OooO00o.OooO00o.OooO0O0.b.f41a.e("InfoReceiver", h.i("tuner, ", strI));
        PermissionActivity.f3800OooO00o.OooO00o(new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, new b(str, strI));
        PuremicTuning.Companion companion = PuremicTuning.Companion;
        OooO00o.OooO00o.OooO0Oo.OooO0Oo.OooO0O0 oooO0O0C = companion.getInstance().getChannelMgr().getNetMgr().c();
        String strA = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o.f425a.A().a();
        if (strA == null) {
            strA = "byd_default";
        }
        PlatformParams platformParams = new PlatformParams(strA, BuildConfig.VERSION_NAME);
        platformParams.setConfigListener(companion.getInstance().getChannelMgr().getMJsonCallback());
        oooO0O0C.f(platformParams);
    }

    public final IVolumeAdjustment f() {
        return (IVolumeAdjustment) this.f167c.getValue();
    }

    public final void g(int i) {
        if (i == 744) {
            f().setMicVolumeEnable(false);
        } else if (i == 758) {
            f().setMicVolumeEnable(true);
        }
        synchronized (b()) {
            Iterator<c> it = b().iterator();
            while (it.hasNext()) {
                it.next().onSkyworthKey(i);
            }
        }
    }

    public final synchronized void h(c cVar) {
        h.d(cVar, "callback");
        b().remove(cVar);
    }

    public final void i(String str) {
        OooO00o.OooO00o.OooO0O0.b bVar = OooO00o.OooO00o.OooO0O0.b.f41a;
        bVar.e("InfoReceiver", h.i(" ------> U盘接入 path: ", str));
        if (new File(h.i(str, "/tuning_config.json")).exists()) {
            bVar.e("InfoReceiver", " ------> 通过U盘文件更新配置");
            try {
                OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0.f354a.q(OooO00o.OooO00o.OooO0O0.OooO0o0.b.c(h.i(str, "/tuning_config.json")));
                ToastUtils.v("Load tuning_config success", new Object[0]);
                a.a.d.b.a.f564a.e(true);
                String strB = OooO00o.OooO00o.OooO0O0.OooO0o0.c.f35a.b("persist.sys.byd.default_name");
                if (TextUtils.isEmpty(strB)) {
                    strB = "获取失败";
                }
                StringBuilder sb = new StringBuilder("车名：" + strB + (char) 12290);
                OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o oooO0o = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o.f425a;
                Integer numA = oooO0o.m().a();
                if (numA != null && numA.intValue() == 0) {
                    sb.append("车型ID未录入。");
                } else {
                    sb.append("车型ID已录入。");
                }
                sb.append(h.i("车型ID、音箱和多声道通道数信息为", oooO0o.A().a()));
                ToastUtils.r(sb);
                bVar.e("InfoReceiver", h.i("car info :: ", sb));
            } catch (Exception e) {
                ToastUtils.v("Load tuning_config fail, data parsing error.", new Object[0]);
                OooO00o.OooO00o.OooO0O0.b.f41a.d("InfoReceiver", "Load tuning_config fail, error");
                e.printStackTrace();
            }
        }
        File file = new File(h.i(str, "/loostone_dongle_firmware.zip"));
        if (file.exists()) {
            if (DongleUpdateUtil.getInstance().isRealUpdating()) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("InfoReceiver", " ------> 通过U盘文件升级接收器固件被拦截，升级中...");
            } else {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("InfoReceiver", " ------> 通过U盘文件升级接收器固件");
                this.f168d = str;
                DongleUpdateUtil.getInstance().setUpgradeFromUDisk();
                DongleUpdateUtil.getInstance().startUpdate(file, null);
            }
        }
        File file2 = new File(h.i(str, "/loostone_mic_firmware.zip"));
        if (file2.exists()) {
            if (MicUpdateUtil.getInstance().isRealUpdating()) {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("InfoReceiver", " ------> 通过U盘文件升级麦克风固件被拦截，升级中...");
            } else {
                OooO00o.OooO00o.OooO0O0.b.f41a.e("InfoReceiver", " ------> 通过U盘文件升级麦克风固件");
                this.f168d = str;
                MicUpdateUtil.getInstance().setUpgradeFromUDisk();
                MicUpdateUtil.getInstance().startUpdate(file2, null);
            }
        }
        if (new File(h.i(str, "/loostone/puremic.tuner")).exists()) {
            e(h.i(str, "/loostone/tuning_info.txt"));
        }
        if (new File(h.i(str, "/loostone/puremic.tuning")).exists()) {
            OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.b.b bVar2 = OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.b.b.f136a;
            String string = this.f165a.getResources().getString(R.string.ota_address_test);
            h.c(string, "context.resources.getString(R.string.ota_address_test)");
            bVar2.b(string);
            OooO00o.OooO00o.OooO0O0.b.f41a.e("InfoReceiver", "OTA 测试环境");
        }
        if (new File(h.i(str, "/loostone/switch_off.ota")).exists()) {
            OooO00o.OooO00o.OooO0Oo.OooO0Oo.b.a.f128a.e(false);
            OooO00o.OooO00o.OooO0O0.b.f41a.e("InfoReceiver", "OTA 已关闭");
        }
    }

    public final void j() {
        synchronized (b()) {
            Iterator<c> it = b().iterator();
            while (it.hasNext()) {
                it.next().onScreenChange(false);
            }
        }
    }

    public final void k() {
        synchronized (b()) {
            Iterator<c> it = b().iterator();
            while (it.hasNext()) {
                it.next().onScreenChange(true);
            }
        }
    }

    public final void l() {
        OooOO0 oooOO0 = OooOO0.f490a;
        int i = oooOO0.i();
        int iJ = oooOO0.j();
        Number numberValueOf = (iJ <= 0 || i > iJ) ? 100 : Float.valueOf((i * 100.0f) / iJ);
        try {
            com.loostone.libtuning.channel.byd.old.PuremicTuning.getInstance().setSystemPercentage(numberValueOf.intValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer numA = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o.f425a.s().a();
        if (numA != null && numA.intValue() == 1) {
            f().setSystemVolume(numberValueOf.intValue());
            OooO00o.OooO00o.OooO0O0.b.f41a.e("InfoReceiver", " ------> 收到广播，设置系统音量");
        }
        f().setMicVolume(f().getMicVolume());
        synchronized (b()) {
            Iterator<c> it = b().iterator();
            while (it.hasNext()) {
                it.next().onVolumeChange(i);
            }
        }
    }

    public final void m() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.media.VOLUME_CHANGED_ACTION");
        intentFilter.addAction("android.media.STREAM_MUTE_CHANGED_ACTION");
        intentFilter.addAction("android.media.MASTER_MUTE_CHANGED_ACTION");
        intentFilter.addAction("android.media.MASTER_VOLUME_CHANGED_ACTION");
        intentFilter.addAction("android.media.EXTRA_MASTER_VOLUME_VALUE");
        intentFilter.addAction("android.media.EXTRA_MASTER_VOLUME_MUTED");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("com.android.sky.SendHotKey");
        intentFilter.addAction("byd.intent.minikaraoke_micevent");
        this.f165a.registerReceiver(this, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addDataScheme("file");
        intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter2.addAction("android.intent.action.MEDIA_UNMOUNTED");
        this.f165a.registerReceiver(this, intentFilter2);
        l();
    }

    public final void n() {
        this.f165a.unregisterReceiver(this);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        String path;
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        boolean z = false;
        switch (action.hashCode()) {
            case -2128145023:
                if (action.equals("android.intent.action.SCREEN_OFF")) {
                    j();
                    return;
                }
                return;
            case -1940635523:
                if (!action.equals("android.media.VOLUME_CHANGED_ACTION")) {
                    return;
                }
                break;
            case -1514214344:
                if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                    Uri data = intent.getData();
                    i(data == null ? null : data.getPath());
                    return;
                }
                return;
            case -1454123155:
                if (action.equals("android.intent.action.SCREEN_ON")) {
                    k();
                    return;
                }
                return;
            case -1132153869:
                if (action.equals("com.android.sky.SendHotKey")) {
                    g(intent.getIntExtra("specialKey", 0));
                    return;
                }
                return;
            case -963871873:
                if (action.equals("android.intent.action.MEDIA_UNMOUNTED")) {
                    Uri data2 = intent.getData();
                    if (data2 != null && (path = data2.getPath()) != null && path.equals(this.f168d)) {
                        z = true;
                    }
                    if (z) {
                        DongleUpdateUtil.getInstance().uDiskRemove();
                        MicUpdateUtil.getInstance().uDiskRemove();
                        return;
                    }
                    return;
                }
                return;
            case -659591274:
                if (action.equals("byd.intent.minikaraoke_micevent")) {
                    c(intent.getIntExtra("android.intent.extra.KEY_EVENT", -1));
                    return;
                }
                return;
            case -189218414:
                if (!action.equals("android.media.MASTER_VOLUME_CHANGED_ACTION")) {
                    return;
                }
                break;
            case 622839499:
                if (!action.equals("android.media.EXTRA_MASTER_VOLUME_MUTED")) {
                    return;
                }
                break;
            case 630548177:
                if (!action.equals("android.media.EXTRA_MASTER_VOLUME_VALUE")) {
                    return;
                }
                break;
            case 1170999219:
                if (!action.equals("android.media.MASTER_MUTE_CHANGED_ACTION")) {
                    return;
                }
                break;
            case 1920758225:
                if (!action.equals("android.media.STREAM_MUTE_CHANGED_ACTION")) {
                    return;
                }
                break;
            default:
                return;
        }
        OooOO0 oooOO0 = OooOO0.f490a;
        int i = oooOO0.i();
        OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o oooO0o = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0o.f425a;
        Integer numA = oooO0o.C().a();
        if (numA != null && i == numA.intValue()) {
            return;
        }
        oooO0o.C().e(Integer.valueOf(oooOO0.i()));
        l();
    }
}
