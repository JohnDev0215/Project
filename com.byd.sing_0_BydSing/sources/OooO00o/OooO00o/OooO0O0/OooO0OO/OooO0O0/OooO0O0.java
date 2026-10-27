package OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0;

import OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o;
import OooO00o.OooO00o.OooO0O0.b;
import OooO00o.OooO00o.OooO0Oo.OooO0OO.d.c;
import android.text.TextUtils;
import com.android.volley.BuildConfig;
import com.blankj.utilcode.util.j;
import com.blankj.utilcode.util.q;
import com.blankj.utilcode.util.r;
import com.google.gson.Gson;
import com.loostone.libserver.version2.data.config.DefaultMicConfig;
import com.loostone.libtuning.data.bean.LS_FX_CMD;
import com.loostone.libtuning.data.config.ConfigMgr;
import com.loostone.libtuning.data.config.DefaultConfig;
import com.loostone.libtuning.data.config.EncodeConfig;
import com.loostone.libtuning.data.config.HardEffect;
import com.loostone.libtuning.data.config.HardEffectDglItem;
import com.loostone.libtuning.data.config.HardEffectMicItem;
import com.loostone.libtuning.data.config.HardEffectVolume;
import com.loostone.libtuning.data.config.manager.BiYaDiConfigMgr;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.d;
import kotlin.i;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.h;
import kotlin.k;
import kotlin.m;
import kotlin.text.n;

/* JADX INFO: loaded from: classes.dex */
public final class OooO0O0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final OooO0O0 f354a = new OooO0O0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final kotlin.a f355b = d.b(C0016OooO0O0.f357OooO00o);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final kotlin.a f356c = d.b(OooO0OO.f358OooO00o);

    /* JADX INFO: renamed from: OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0$OooO0O0, reason: collision with other inner class name */
    public static final class C0016OooO0O0 extends Lambda implements kotlin.jvm.b.a<ConfigMgr> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final C0016OooO0O0 f357OooO00o = new C0016OooO0O0();

        public C0016OooO0O0() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final ConfigMgr invoke() {
            return OooO0O0.f354a.c();
        }
    }

    public static final class OooO0OO extends Lambda implements kotlin.jvm.b.a<Map<String, String>> {

        /* JADX INFO: renamed from: OooO00o, reason: collision with root package name */
        public static final OooO0OO f358OooO00o = new OooO0OO();

        public OooO0OO() {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        /* JADX INFO: renamed from: OooO00o, reason: merged with bridge method [inline-methods] */
        public final Map<String, String> invoke() {
            return new LinkedHashMap();
        }
    }

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f359a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f360b;

        public a(int i, int i2) {
            this.f359a = i;
            this.f360b = i2;
        }

        public final int a() {
            return this.f359a;
        }

        public final int b() {
            return this.f360b;
        }
    }

    public static /* synthetic */ byte a(OooO0O0 oooO0O0, byte[] bArr, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = bArr.length;
        }
        return oooO0O0.b(bArr, i);
    }

    public static /* synthetic */ void i(OooO0O0 oooO0O0, long j, int i, boolean z, int i2) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        oooO0O0.h(j, i, z);
    }

    public final byte b(byte[] bArr, int i) {
        int i2 = i - 1;
        int i3 = 0;
        if (i2 > 0) {
            int i4 = 0;
            while (true) {
                int i5 = i3 + 1;
                i4 += bArr[i3];
                if (i5 >= i2) {
                    break;
                }
                i3 = i5;
            }
            i3 = i4;
        }
        return (byte) i3;
    }

    public final ConfigMgr c() {
        return new BiYaDiConfigMgr();
    }

    public final String e(long j) {
        String configPath = BuildConfig.FLAVOR;
        String strI = j == 200000 ? h.i(OooO00o.f18a.g("config"), File.separator) : j == 200001 ? "vendor/tvconfig/loostone/" : BuildConfig.FLAVOR;
        if (!TextUtils.isEmpty(strI)) {
            configPath = p().getConfigPath(strI);
        }
        return (TextUtils.isEmpty(configPath) || !new File(configPath).exists()) ? OooO00o.f18a.g("config.json") : configPath;
    }

    public final String f(long j, int i) throws Throwable {
        String strE = e(j);
        if (new File(strE).exists()) {
            b.f41a.e("InitConfig", h.i("外部路径或安装路径下配置-->path=", strE));
            c.c(OooO00o.OooO00o.OooO0O0.a.f39a.a(), "init_config_path", strE);
            String strC = OooO00o.OooO00o.OooO0O0.OooO0o0.b.c(strE);
            h.c(strC, "{\n            LogTuning.i(TAG, \"外部路径或安装路径下配置-->path=$path\")\n            SharedPreferencesCacheUtil.saveData(FakeApplication.instance,Constant.CONFIG_PATH, path)\n\n            OldCommonUtil.getStringFromFile(path)\n        }");
            return strC;
        }
        b bVar = b.f41a;
        bVar.e("InitConfig", "  默认配置 channel: " + j + ", model: " + i);
        p().setModel(i);
        String configPath$default = ConfigMgr.getConfigPath$default(p(), null, 1, null);
        bVar.e("InitConfig", h.i("  path: ", configPath$default));
        c.c(OooO00o.OooO00o.OooO0O0.a.f39a.a(), "init_config_path", configPath$default);
        String strB = q.b(configPath$default);
        h.c(strB, "{\n            LogTuning.i(TAG, \"  默认配置 channel: $channel, model: $model\")\n            configMgr.setModel(model)\n            path = configMgr.getConfigPath()\n            LogTuning.i(TAG, \"  path: $path\")\n            SharedPreferencesCacheUtil.saveData(FakeApplication.instance,Constant.CONFIG_PATH, path)\n\n            ResourceUtils.readAssets2String(path)\n        }");
        return strB;
    }

    public final String g(String str) {
        EncodeConfig encodeConfig = (EncodeConfig) j.d(str, EncodeConfig.class);
        if (encodeConfig == null) {
            b.f41a.e("InitConfig", "to EncodeConfig error");
            return null;
        }
        if (encodeConfig.getKey() == null || encodeConfig.getValue() == null) {
            b.f41a.e("InitConfig", "not need decrypt");
        } else {
            OooO00o.OooO00o.OooO0o0.OooO0o.b bVar = OooO00o.OooO00o.OooO0o0.OooO0o.b.f494a;
            String value = encodeConfig.getValue();
            h.b(value);
            String key = encodeConfig.getKey();
            h.b(key);
            str = bVar.c(value, key);
            b.f41a.e("InitConfig", h.i("decrypt result:", Boolean.valueOf(str != null)));
        }
        return str;
    }

    public final void h(long j, int i, boolean z) {
        String strE = e(j);
        if (!z) {
            OooO0o oooO0o = OooO0o.f425a;
            Integer numA = oooO0o.q().a();
            boolean zA = false;
            boolean z2 = true;
            boolean z3 = (numA == null ? 0 : numA.intValue()) >= 15;
            boolean zA2 = h.a(oooO0o.o().a(), Boolean.TRUE);
            String strA = oooO0o.u().a();
            String str = BuildConfig.FLAVOR;
            if (strA == null) {
                strA = BuildConfig.FLAVOR;
            }
            OooO00o oooO00o = OooO00o.f18a;
            boolean zA3 = h.a(strA, oooO00o.e(strE));
            String strA2 = oooO0o.J().a();
            if (strA2 != null) {
                str = strA2;
            }
            boolean zA4 = h.a(str, com.loostone.libtuning.BuildConfig.VERSION_NAME);
            OooO00o.OooO00o.OooO0O0.OooO0o0.c cVar = OooO00o.OooO00o.OooO0O0.OooO0o0.c.f35a;
            String strC = h.a(cVar.b("sys.has.dn.amp"), "true") ? "1" : cVar.c("sys.ext.amp.type", "0");
            String strA3 = oooO0o.A().a();
            if (strA3 != null && strA3.length() != 0) {
                z2 = false;
            }
            if (!z2) {
                String str2 = 'M' + i + "_S" + strC;
                Integer numA2 = n.a(cVar.b("sys.ext.amp.multi.channel"));
                if (numA2 != null) {
                    str2 = str2 + "_MC" + numA2.intValue();
                }
                b.f41a.e("InitConfig", h.i("init config, modelName = ", str2));
                zA = h.a(str2, oooO0o.A().a());
            }
            b bVar = b.f41a;
            bVar.e("InitConfig", "version = " + z3 + ", init = " + zA2 + ", md5 = " + zA3 + ", versionName = " + zA4 + ", sameModel = " + zA);
            if ((z3 & zA2 & zA3 & zA4) && zA) {
                String strA4 = c.a(OooO00o.OooO00o.OooO0O0.a.f39a.a(), "init_config_path");
                if (strA4 == null) {
                    return;
                }
                bVar.e("InitConfig", h.i("already init config, path = ", strA4));
                return;
            }
            oooO0o.o().e(Boolean.FALSE);
            oooO0o.J().e(com.loostone.libtuning.BuildConfig.VERSION_NAME);
            oooO0o.z().e(Integer.valueOf(i));
            oooO0o.u().e(oooO00o.e(strE));
        }
        OooO0o oooO0o2 = OooO0o.f425a;
        oooO0o2.q().e(15);
        q(f(j, i));
        s();
        oooO0o2.o().e(Boolean.TRUE);
    }

    public final void j(DefaultConfig defaultConfig) {
        HardEffect hardEffect = defaultConfig.getHardEffect();
        if (hardEffect != null) {
            a.a.d.b.a aVar = a.a.d.b.a.f564a;
            aVar.f(f354a.m(hardEffect));
            byte[] bArrJ = aVar.j();
            if (bArrJ != null) {
                r.b("hard_config").h("hardEffect", j.i(bArrJ));
            }
        }
        HardEffectVolume hardEffectVolume = defaultConfig.getHardEffectVolume();
        if (hardEffectVolume != null) {
            a.a.d.b.a aVar2 = a.a.d.b.a.f564a;
            aVar2.i(f354a.n(hardEffectVolume));
            byte[] bArrN = aVar2.n();
            if (bArrN != null) {
                r.b("hard_config").h("hardEffectVolume", j.i(bArrN));
            }
        }
        List<HardEffect> hardEffectMicList = defaultConfig.getHardEffectMicList();
        if (hardEffectMicList != null) {
            a.a.d.b.a.f564a.h(new ArrayList());
            for (HardEffect hardEffect2 : hardEffectMicList) {
                b.f41a.b("InitConfig", "hardEffectMicItem, id = " + hardEffect2.getId() + ", name = " + ((Object) hardEffect2.getName()));
                Integer id = hardEffect2.getId();
                if (id != null) {
                    int iIntValue = id.intValue();
                    byte[] bArrM = f354a.m(hardEffect2);
                    if (bArrM != null) {
                        HardEffectMicItem hardEffectMicItem = new HardEffectMicItem();
                        hardEffectMicItem.setId(Integer.valueOf(iIntValue));
                        hardEffectMicItem.setMicEffect(bArrM);
                        List<HardEffectMicItem> listM = a.a.d.b.a.f564a.m();
                        if (listM != null) {
                            listM.add(hardEffectMicItem);
                        }
                    }
                }
            }
            List<HardEffectMicItem> listM2 = a.a.d.b.a.f564a.m();
            if (listM2 != null) {
                if (!(!listM2.isEmpty())) {
                    listM2 = null;
                }
                if (listM2 != null) {
                    r.b("hard_config").h("hardEffectMicList", j.i(listM2));
                }
            }
        }
        List<HardEffectDglItem> hardEffectDglList = defaultConfig.getHardEffectDglList();
        if (hardEffectDglList != null) {
            a.a.d.b.a.f564a.d(new ArrayList());
            for (HardEffectDglItem hardEffectDglItem : hardEffectDglList) {
                b.f41a.b("InitConfig", h.i("hardEffectDglItem, name = ", hardEffectDglItem.getProductName()));
                OooO0O0 oooO0O0 = f354a;
                String productName = hardEffectDglItem.getProductName();
                if ((!(productName == null || productName.length() == 0) ? oooO0O0 : null) != null) {
                    HardEffectDglItem hardEffectDglItem2 = new HardEffectDglItem();
                    hardEffectDglItem2.setProductName(hardEffectDglItem.getProductName());
                    HardEffect hardEffect3 = hardEffectDglItem.getHardEffect();
                    if (hardEffect3 != null) {
                        hardEffectDglItem2.setHardEffectData(oooO0O0.m(hardEffect3));
                    }
                    HardEffectVolume hardEffectVolume2 = hardEffectDglItem.getHardEffectVolume();
                    if (hardEffectVolume2 != null) {
                        hardEffectDglItem2.setHardEffectVolumeData(oooO0O0.n(hardEffectVolume2));
                    }
                    List<HardEffectDglItem> listK = a.a.d.b.a.f564a.k();
                    if (listK != null) {
                        listK.add(hardEffectDglItem2);
                    }
                }
            }
            List<HardEffectDglItem> listK2 = a.a.d.b.a.f564a.k();
            if (listK2 != null) {
                if (!(!listK2.isEmpty())) {
                    listK2 = null;
                }
                if (listK2 != null) {
                    r.b("hard_config").h("hardEffectDglList", j.i(listK2));
                }
            }
        }
        List<HardEffectDglItem> hardEffectTVLiftList = defaultConfig.getHardEffectTVLiftList();
        if (hardEffectTVLiftList != null) {
            a.a.d.b.a.f564a.l(new ArrayList());
            for (HardEffectDglItem hardEffectDglItem3 : hardEffectTVLiftList) {
                b.f41a.b("InitConfig", h.i("hardEffectDglItem, name = ", hardEffectDglItem3.getProductName()));
                OooO0O0 oooO0O02 = f354a;
                String productName2 = hardEffectDglItem3.getProductName();
                if ((!(productName2 == null || productName2.length() == 0) ? oooO0O02 : null) != null) {
                    HardEffectDglItem hardEffectDglItem4 = new HardEffectDglItem();
                    hardEffectDglItem4.setProductName(hardEffectDglItem3.getProductName());
                    HardEffect hardEffect4 = hardEffectDglItem3.getHardEffect();
                    if (hardEffect4 != null) {
                        hardEffectDglItem4.setHardEffectData(oooO0O02.m(hardEffect4));
                    }
                    HardEffectVolume hardEffectVolume3 = hardEffectDglItem3.getHardEffectVolume();
                    if (hardEffectVolume3 != null) {
                        hardEffectDglItem4.setHardEffectVolumeData(oooO0O02.n(hardEffectVolume3));
                    }
                    List<HardEffectDglItem> listO = a.a.d.b.a.f564a.o();
                    if (listO != null) {
                        listO.add(hardEffectDglItem4);
                    }
                }
            }
            List<HardEffectDglItem> listO2 = a.a.d.b.a.f564a.o();
            if (listO2 != null) {
                List<HardEffectDglItem> list = listO2.isEmpty() ^ true ? listO2 : null;
                if (list != null) {
                    r.b("hard_config").h("hardEffectTVLiftList", j.i(list));
                }
            }
        }
        OooO0o.f425a.e(defaultConfig);
        OooO oooO = OooO.f199a;
        oooO.e(defaultConfig);
        OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0OO oooO0OO = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0OO.f361a;
        oooO0OO.c(defaultConfig);
        oooO.f(defaultConfig.getEqList());
        oooO0OO.d(defaultConfig.getEqList());
    }

    public final void k(Map<String, String> map) {
        h.d(map, "dataMap");
        if ((map.isEmpty() ^ true ? this : null) == null) {
            return;
        }
        OooO0O0 oooO0O0 = f354a;
        oooO0O0.r().putAll(map);
        oooO0O0.p().setDataForConfigParse(oooO0O0.r());
    }

    public final byte[] l(int i, int i2) {
        String string;
        ArrayList<a> arrayList = new ArrayList<>();
        arrayList.add(new a(i, i2));
        byte[] bArrO = o(arrayList);
        b bVar = b.f41a;
        StringBuilder sb = new StringBuilder();
        sb.append("effectMicListData index=");
        sb.append(i);
        sb.append(",value=");
        sb.append(i2);
        sb.append(", ");
        if (bArrO == null) {
            string = null;
        } else {
            string = Arrays.toString(bArrO);
            h.c(string, "java.util.Arrays.toString(this)");
        }
        sb.append((Object) string);
        bVar.b("InitConfig", sb.toString());
        return bArrO;
    }

    public final byte[] m(HardEffect hardEffect) {
        String string;
        ArrayList<a> arrayList = new ArrayList<>();
        Integer global_eq_on_off = hardEffect.getGLOBAL_EQ_ON_OFF();
        if (global_eq_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_ON_OFF.getIndex(), global_eq_on_off.intValue()));
        }
        Integer global_eq_type1 = hardEffect.getGLOBAL_EQ_TYPE1();
        if (global_eq_type1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_TYPE1.getIndex(), global_eq_type1.intValue()));
        }
        Integer global_eq_fc1 = hardEffect.getGLOBAL_EQ_FC1();
        if (global_eq_fc1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_FC1.getIndex(), global_eq_fc1.intValue()));
        }
        Integer global_eq_gain1 = hardEffect.getGLOBAL_EQ_GAIN1();
        if (global_eq_gain1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_GAIN1.getIndex(), global_eq_gain1.intValue()));
        }
        Integer global_eq_q1 = hardEffect.getGLOBAL_EQ_Q1();
        if (global_eq_q1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_Q1.getIndex(), global_eq_q1.intValue()));
        }
        Integer global_eq_type2 = hardEffect.getGLOBAL_EQ_TYPE2();
        if (global_eq_type2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_TYPE2.getIndex(), global_eq_type2.intValue()));
        }
        Integer global_eq_fc2 = hardEffect.getGLOBAL_EQ_FC2();
        if (global_eq_fc2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_FC2.getIndex(), global_eq_fc2.intValue()));
        }
        Integer global_eq_gain2 = hardEffect.getGLOBAL_EQ_GAIN2();
        if (global_eq_gain2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_GAIN2.getIndex(), global_eq_gain2.intValue()));
        }
        Integer global_eq_q2 = hardEffect.getGLOBAL_EQ_Q2();
        if (global_eq_q2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_Q2.getIndex(), global_eq_q2.intValue()));
        }
        Integer global_eq_type3 = hardEffect.getGLOBAL_EQ_TYPE3();
        if (global_eq_type3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_TYPE3.getIndex(), global_eq_type3.intValue()));
        }
        Integer global_eq_fc3 = hardEffect.getGLOBAL_EQ_FC3();
        if (global_eq_fc3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_FC3.getIndex(), global_eq_fc3.intValue()));
        }
        Integer global_eq_gain3 = hardEffect.getGLOBAL_EQ_GAIN3();
        if (global_eq_gain3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_GAIN3.getIndex(), global_eq_gain3.intValue()));
        }
        Integer global_eq_q3 = hardEffect.getGLOBAL_EQ_Q3();
        if (global_eq_q3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_Q3.getIndex(), global_eq_q3.intValue()));
        }
        Integer global_eq_type4 = hardEffect.getGLOBAL_EQ_TYPE4();
        if (global_eq_type4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_TYPE4.getIndex(), global_eq_type4.intValue()));
        }
        Integer global_eq_fc4 = hardEffect.getGLOBAL_EQ_FC4();
        if (global_eq_fc4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_FC4.getIndex(), global_eq_fc4.intValue()));
        }
        Integer global_eq_gain4 = hardEffect.getGLOBAL_EQ_GAIN4();
        if (global_eq_gain4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_GAIN4.getIndex(), global_eq_gain4.intValue()));
        }
        Integer global_eq_q4 = hardEffect.getGLOBAL_EQ_Q4();
        if (global_eq_q4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_Q4.getIndex(), global_eq_q4.intValue()));
        }
        Integer global_eq_type5 = hardEffect.getGLOBAL_EQ_TYPE5();
        if (global_eq_type5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_TYPE5.getIndex(), global_eq_type5.intValue()));
        }
        Integer global_eq_fc5 = hardEffect.getGLOBAL_EQ_FC5();
        if (global_eq_fc5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_FC5.getIndex(), global_eq_fc5.intValue()));
        }
        Integer global_eq_gain5 = hardEffect.getGLOBAL_EQ_GAIN5();
        if (global_eq_gain5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_GAIN5.getIndex(), global_eq_gain5.intValue()));
        }
        Integer global_eq_q5 = hardEffect.getGLOBAL_EQ_Q5();
        if (global_eq_q5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_Q5.getIndex(), global_eq_q5.intValue()));
        }
        Integer global_eq_type6 = hardEffect.getGLOBAL_EQ_TYPE6();
        if (global_eq_type6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_TYPE6.getIndex(), global_eq_type6.intValue()));
        }
        Integer global_eq_fc6 = hardEffect.getGLOBAL_EQ_FC6();
        if (global_eq_fc6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_FC6.getIndex(), global_eq_fc6.intValue()));
        }
        Integer global_eq_gain6 = hardEffect.getGLOBAL_EQ_GAIN6();
        if (global_eq_gain6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_GAIN6.getIndex(), global_eq_gain6.intValue()));
        }
        Integer global_eq_q6 = hardEffect.getGLOBAL_EQ_Q6();
        if (global_eq_q6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_Q6.getIndex(), global_eq_q6.intValue()));
        }
        Integer global_eq_type7 = hardEffect.getGLOBAL_EQ_TYPE7();
        if (global_eq_type7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_TYPE7.getIndex(), global_eq_type7.intValue()));
        }
        Integer global_eq_fc7 = hardEffect.getGLOBAL_EQ_FC7();
        if (global_eq_fc7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_FC7.getIndex(), global_eq_fc7.intValue()));
        }
        Integer global_eq_gain7 = hardEffect.getGLOBAL_EQ_GAIN7();
        if (global_eq_gain7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_GAIN7.getIndex(), global_eq_gain7.intValue()));
        }
        Integer global_eq_q7 = hardEffect.getGLOBAL_EQ_Q7();
        if (global_eq_q7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_Q7.getIndex(), global_eq_q7.intValue()));
        }
        Integer global_eq_type8 = hardEffect.getGLOBAL_EQ_TYPE8();
        if (global_eq_type8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_TYPE8.getIndex(), global_eq_type8.intValue()));
        }
        Integer global_eq_fc8 = hardEffect.getGLOBAL_EQ_FC8();
        if (global_eq_fc8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_FC8.getIndex(), global_eq_fc8.intValue()));
        }
        Integer global_eq_gain8 = hardEffect.getGLOBAL_EQ_GAIN8();
        if (global_eq_gain8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_GAIN8.getIndex(), global_eq_gain8.intValue()));
        }
        Integer global_eq_q8 = hardEffect.getGLOBAL_EQ_Q8();
        if (global_eq_q8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_Q8.getIndex(), global_eq_q8.intValue()));
        }
        Integer global_eq_type9 = hardEffect.getGLOBAL_EQ_TYPE9();
        if (global_eq_type9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_TYPE9.getIndex(), global_eq_type9.intValue()));
        }
        Integer global_eq_fc9 = hardEffect.getGLOBAL_EQ_FC9();
        if (global_eq_fc9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_FC9.getIndex(), global_eq_fc9.intValue()));
        }
        Integer global_eq_gain9 = hardEffect.getGLOBAL_EQ_GAIN9();
        if (global_eq_gain9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_GAIN9.getIndex(), global_eq_gain9.intValue()));
        }
        Integer global_eq_q9 = hardEffect.getGLOBAL_EQ_Q9();
        if (global_eq_q9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_GLOBAL_EQ_Q9.getIndex(), global_eq_q9.intValue()));
        }
        Integer scene_eq_on_off = hardEffect.getSCENE_EQ_ON_OFF();
        if (scene_eq_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_ON_OFF.getIndex(), scene_eq_on_off.intValue()));
        }
        Integer scene_eq_type1 = hardEffect.getSCENE_EQ_TYPE1();
        if (scene_eq_type1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_TYPE1.getIndex(), scene_eq_type1.intValue()));
        }
        Integer scene_eq_fc1 = hardEffect.getSCENE_EQ_FC1();
        if (scene_eq_fc1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_FC1.getIndex(), scene_eq_fc1.intValue()));
        }
        Integer scene_eq_gain1 = hardEffect.getSCENE_EQ_GAIN1();
        if (scene_eq_gain1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_GAIN1.getIndex(), scene_eq_gain1.intValue()));
        }
        Integer scene_eq_q1 = hardEffect.getSCENE_EQ_Q1();
        if (scene_eq_q1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_Q1.getIndex(), scene_eq_q1.intValue()));
        }
        Integer scene_eq_type2 = hardEffect.getSCENE_EQ_TYPE2();
        if (scene_eq_type2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_TYPE2.getIndex(), scene_eq_type2.intValue()));
        }
        Integer scene_eq_fc2 = hardEffect.getSCENE_EQ_FC2();
        if (scene_eq_fc2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_FC2.getIndex(), scene_eq_fc2.intValue()));
        }
        Integer scene_eq_gain2 = hardEffect.getSCENE_EQ_GAIN2();
        if (scene_eq_gain2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_GAIN2.getIndex(), scene_eq_gain2.intValue()));
        }
        Integer scene_eq_q2 = hardEffect.getSCENE_EQ_Q2();
        if (scene_eq_q2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_Q2.getIndex(), scene_eq_q2.intValue()));
        }
        Integer scene_eq_type3 = hardEffect.getSCENE_EQ_TYPE3();
        if (scene_eq_type3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_TYPE3.getIndex(), scene_eq_type3.intValue()));
        }
        Integer scene_eq_fc3 = hardEffect.getSCENE_EQ_FC3();
        if (scene_eq_fc3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_FC3.getIndex(), scene_eq_fc3.intValue()));
        }
        Integer scene_eq_gain3 = hardEffect.getSCENE_EQ_GAIN3();
        if (scene_eq_gain3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_GAIN3.getIndex(), scene_eq_gain3.intValue()));
        }
        Integer scene_eq_q3 = hardEffect.getSCENE_EQ_Q3();
        if (scene_eq_q3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_Q3.getIndex(), scene_eq_q3.intValue()));
        }
        Integer scene_eq_type4 = hardEffect.getSCENE_EQ_TYPE4();
        if (scene_eq_type4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_TYPE4.getIndex(), scene_eq_type4.intValue()));
        }
        Integer scene_eq_fc4 = hardEffect.getSCENE_EQ_FC4();
        if (scene_eq_fc4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_FC4.getIndex(), scene_eq_fc4.intValue()));
        }
        Integer scene_eq_gain4 = hardEffect.getSCENE_EQ_GAIN4();
        if (scene_eq_gain4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_GAIN4.getIndex(), scene_eq_gain4.intValue()));
        }
        Integer scene_eq_q4 = hardEffect.getSCENE_EQ_Q4();
        if (scene_eq_q4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_Q4.getIndex(), scene_eq_q4.intValue()));
        }
        Integer scene_eq_type5 = hardEffect.getSCENE_EQ_TYPE5();
        if (scene_eq_type5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_TYPE5.getIndex(), scene_eq_type5.intValue()));
        }
        Integer scene_eq_fc5 = hardEffect.getSCENE_EQ_FC5();
        if (scene_eq_fc5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_FC5.getIndex(), scene_eq_fc5.intValue()));
        }
        Integer scene_eq_gain5 = hardEffect.getSCENE_EQ_GAIN5();
        if (scene_eq_gain5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_GAIN5.getIndex(), scene_eq_gain5.intValue()));
        }
        Integer scene_eq_q5 = hardEffect.getSCENE_EQ_Q5();
        if (scene_eq_q5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_Q5.getIndex(), scene_eq_q5.intValue()));
        }
        Integer scene_eq_type6 = hardEffect.getSCENE_EQ_TYPE6();
        if (scene_eq_type6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_TYPE6.getIndex(), scene_eq_type6.intValue()));
        }
        Integer scene_eq_fc6 = hardEffect.getSCENE_EQ_FC6();
        if (scene_eq_fc6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_FC6.getIndex(), scene_eq_fc6.intValue()));
        }
        Integer scene_eq_gain6 = hardEffect.getSCENE_EQ_GAIN6();
        if (scene_eq_gain6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_GAIN6.getIndex(), scene_eq_gain6.intValue()));
        }
        Integer scene_eq_q6 = hardEffect.getSCENE_EQ_Q6();
        if (scene_eq_q6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_Q6.getIndex(), scene_eq_q6.intValue()));
        }
        Integer scene_eq_type7 = hardEffect.getSCENE_EQ_TYPE7();
        if (scene_eq_type7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_TYPE7.getIndex(), scene_eq_type7.intValue()));
        }
        Integer scene_eq_fc7 = hardEffect.getSCENE_EQ_FC7();
        if (scene_eq_fc7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_FC7.getIndex(), scene_eq_fc7.intValue()));
        }
        Integer scene_eq_gain7 = hardEffect.getSCENE_EQ_GAIN7();
        if (scene_eq_gain7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_GAIN7.getIndex(), scene_eq_gain7.intValue()));
        }
        Integer scene_eq_q7 = hardEffect.getSCENE_EQ_Q7();
        if (scene_eq_q7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_Q7.getIndex(), scene_eq_q7.intValue()));
        }
        Integer scene_eq_type8 = hardEffect.getSCENE_EQ_TYPE8();
        if (scene_eq_type8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_TYPE8.getIndex(), scene_eq_type8.intValue()));
        }
        Integer scene_eq_fc8 = hardEffect.getSCENE_EQ_FC8();
        if (scene_eq_fc8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_FC8.getIndex(), scene_eq_fc8.intValue()));
        }
        Integer scene_eq_gain8 = hardEffect.getSCENE_EQ_GAIN8();
        if (scene_eq_gain8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_GAIN8.getIndex(), scene_eq_gain8.intValue()));
        }
        Integer scene_eq_q8 = hardEffect.getSCENE_EQ_Q8();
        if (scene_eq_q8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_Q8.getIndex(), scene_eq_q8.intValue()));
        }
        Integer scene_eq_type9 = hardEffect.getSCENE_EQ_TYPE9();
        if (scene_eq_type9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_TYPE9.getIndex(), scene_eq_type9.intValue()));
        }
        Integer scene_eq_fc9 = hardEffect.getSCENE_EQ_FC9();
        if (scene_eq_fc9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_FC9.getIndex(), scene_eq_fc9.intValue()));
        }
        Integer scene_eq_gain9 = hardEffect.getSCENE_EQ_GAIN9();
        if (scene_eq_gain9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_GAIN9.getIndex(), scene_eq_gain9.intValue()));
        }
        Integer scene_eq_q9 = hardEffect.getSCENE_EQ_Q9();
        if (scene_eq_q9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SCENE_EQ_Q9.getIndex(), scene_eq_q9.intValue()));
        }
        Integer echo_on_off = hardEffect.getECHO_ON_OFF();
        if (echo_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_ECHO_ON_OFF.getIndex(), echo_on_off.intValue()));
        }
        Integer echo_type = hardEffect.getECHO_TYPE();
        if (echo_type != null) {
            arrayList.add(new a(LS_FX_CMD.LS_ECHO_TYPE.getIndex(), echo_type.intValue()));
        }
        Integer echo_time = hardEffect.getECHO_TIME();
        if (echo_time != null) {
            arrayList.add(new a(LS_FX_CMD.LS_ECHO_TIME.getIndex(), echo_time.intValue()));
        }
        Integer echo_vol = hardEffect.getECHO_VOL();
        if (echo_vol != null) {
            arrayList.add(new a(LS_FX_CMD.LS_ECHO_VOL.getIndex(), echo_vol.intValue()));
        }
        Integer echo_feedback = hardEffect.getECHO_FEEDBACK();
        if (echo_feedback != null) {
            arrayList.add(new a(LS_FX_CMD.LS_ECHO_FEEDBACK.getIndex(), echo_feedback.intValue()));
        }
        Integer echo_highcut = hardEffect.getECHO_HIGHCUT();
        if (echo_highcut != null) {
            arrayList.add(new a(LS_FX_CMD.LS_ECHO_HIGHCUT.getIndex(), echo_highcut.intValue()));
        }
        Integer echo_lowcut = hardEffect.getECHO_LOWCUT();
        if (echo_lowcut != null) {
            arrayList.add(new a(LS_FX_CMD.LS_ECHO_LOWCUT.getIndex(), echo_lowcut.intValue()));
        }
        Integer echo_stwide = hardEffect.getECHO_STWIDE();
        if (echo_stwide != null) {
            arrayList.add(new a(LS_FX_CMD.LS_ECHO_STWIDE.getIndex(), echo_stwide.intValue()));
        }
        Integer echo_killdry = hardEffect.getECHO_KILLDRY();
        if (echo_killdry != null) {
            arrayList.add(new a(LS_FX_CMD.LS_ECHO_KILLDRY.getIndex(), echo_killdry.intValue()));
        }
        Integer echo_ahead = hardEffect.getECHO_AHEAD();
        if (echo_ahead != null) {
            arrayList.add(new a(LS_FX_CMD.LS_ECHO_AHEAD.getIndex(), echo_ahead.intValue()));
        }
        Integer rev_on_off = hardEffect.getREV_ON_OFF();
        if (rev_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_REV_ON_OFF.getIndex(), rev_on_off.intValue()));
        }
        Integer rev_type = hardEffect.getREV_TYPE();
        if (rev_type != null) {
            arrayList.add(new a(LS_FX_CMD.LS_REV_TYPE.getIndex(), rev_type.intValue()));
        }
        Integer rev_time = hardEffect.getREV_TIME();
        if (rev_time != null) {
            arrayList.add(new a(LS_FX_CMD.LS_REV_TIME.getIndex(), rev_time.intValue()));
        }
        Integer rev_vol = hardEffect.getREV_VOL();
        if (rev_vol != null) {
            arrayList.add(new a(LS_FX_CMD.LS_REV_VOL.getIndex(), rev_vol.intValue()));
        }
        Integer rev_highdamp = hardEffect.getREV_HIGHDAMP();
        if (rev_highdamp != null) {
            arrayList.add(new a(LS_FX_CMD.LS_REV_HIGHDAMP.getIndex(), rev_highdamp.intValue()));
        }
        Integer rev_lowcut = hardEffect.getREV_LOWCUT();
        if (rev_lowcut != null) {
            arrayList.add(new a(LS_FX_CMD.LS_REV_LOWCUT.getIndex(), rev_lowcut.intValue()));
        }
        Integer rev_highcut = hardEffect.getREV_HIGHCUT();
        if (rev_highcut != null) {
            arrayList.add(new a(LS_FX_CMD.LS_REV_HIGHCUT.getIndex(), rev_highcut.intValue()));
        }
        Integer rev_stwide = hardEffect.getREV_STWIDE();
        if (rev_stwide != null) {
            arrayList.add(new a(LS_FX_CMD.LS_REV_STWIDE.getIndex(), rev_stwide.intValue()));
        }
        Integer rev_echo_gain = hardEffect.getREV_ECHO_GAIN();
        if (rev_echo_gain != null) {
            arrayList.add(new a(LS_FX_CMD.LS_REV_ECHO_GAIN.getIndex(), rev_echo_gain.intValue()));
        }
        Integer comp_thres = hardEffect.getCOMP_THRES();
        if (comp_thres != null) {
            arrayList.add(new a(LS_FX_CMD.LS_COMP_THRES.getIndex(), comp_thres.intValue()));
        }
        Integer comp_pregain = hardEffect.getCOMP_PREGAIN();
        if (comp_pregain != null) {
            arrayList.add(new a(LS_FX_CMD.LS_COMP_PREGAIN.getIndex(), comp_pregain.intValue()));
        }
        Integer comp_attack = hardEffect.getCOMP_ATTACK();
        if (comp_attack != null) {
            arrayList.add(new a(LS_FX_CMD.LS_COMP_ATTACK.getIndex(), comp_attack.intValue()));
        }
        Integer comp_release = hardEffect.getCOMP_RELEASE();
        if (comp_release != null) {
            arrayList.add(new a(LS_FX_CMD.LS_COMP_RELEASE.getIndex(), comp_release.intValue()));
        }
        Integer comp_ratio = hardEffect.getCOMP_RATIO();
        if (comp_ratio != null) {
            arrayList.add(new a(LS_FX_CMD.LS_COMP_RATIO.getIndex(), comp_ratio.intValue()));
        }
        Integer comp_volume = hardEffect.getCOMP_VOLUME();
        if (comp_volume != null) {
            arrayList.add(new a(LS_FX_CMD.LS_COMP_VOLUME.getIndex(), comp_volume.intValue()));
        }
        Integer comp_global_gain = hardEffect.getCOMP_GLOBAL_GAIN();
        if (comp_global_gain != null) {
            arrayList.add(new a(LS_FX_CMD.LS_COMP_GLOBAL_GAIN.getIndex(), comp_global_gain.intValue()));
        }
        Integer ps_on_off = hardEffect.getPS_ON_OFF();
        if (ps_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_PS_ON_OFF.getIndex(), ps_on_off.intValue()));
        }
        Integer ps_semi = hardEffect.getPS_SEMI();
        if (ps_semi != null) {
            arrayList.add(new a(LS_FX_CMD.LS_PS_SEMI.getIndex(), ps_semi.intValue()));
        }
        Integer ps_mode = hardEffect.getPS_MODE();
        if (ps_mode != null) {
            arrayList.add(new a(LS_FX_CMD.LS_PS_MODE.getIndex(), ps_mode.intValue()));
        }
        Integer fbc_level = hardEffect.getFBC_LEVEL();
        if (fbc_level != null) {
            arrayList.add(new a(LS_FX_CMD.LS_FBC_LEVEL.getIndex(), fbc_level.intValue()));
        }
        Integer fbc_threshold = hardEffect.getFBC_THRESHOLD();
        if (fbc_threshold != null) {
            arrayList.add(new a(LS_FX_CMD.LS_FBC_THRESHOLD.getIndex(), fbc_threshold.intValue()));
        }
        Integer dhs_on_off = hardEffect.getDHS_ON_OFF();
        if (dhs_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_DHS_ON_OFF.getIndex(), dhs_on_off.intValue()));
        }
        Integer dhs_threshold = hardEffect.getDHS_THRESHOLD();
        if (dhs_threshold != null) {
            arrayList.add(new a(LS_FX_CMD.LS_DHS_THRESHOLD.getIndex(), dhs_threshold.intValue()));
        }
        Integer nsr_on_off = hardEffect.getNSR_ON_OFF();
        if (nsr_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NSR_ON_OFF.getIndex(), nsr_on_off.intValue()));
        }
        Integer nsr_threshold = hardEffect.getNSR_THRESHOLD();
        if (nsr_threshold != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NSR_THRESHOLD.getIndex(), nsr_threshold.intValue()));
        }
        Integer lofi_on_off = hardEffect.getLOFI_ON_OFF();
        if (lofi_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_LOFI_ON_OFF.getIndex(), lofi_on_off.intValue()));
        }
        Integer lofi_down_sample = hardEffect.getLOFI_DOWN_SAMPLE();
        if (lofi_down_sample != null) {
            arrayList.add(new a(LS_FX_CMD.LS_LOFI_DOWN_SAMPLE.getIndex(), lofi_down_sample.intValue()));
        }
        Integer lofi_type = hardEffect.getLOFI_TYPE();
        if (lofi_type != null) {
            arrayList.add(new a(LS_FX_CMD.LS_LOFI_TYPE.getIndex(), lofi_type.intValue()));
        }
        Integer lofi_8bit_type = hardEffect.getLOFI_8BIT_TYPE();
        if (lofi_8bit_type != null) {
            arrayList.add(new a(LS_FX_CMD.LS_LOFI_8BIT_TYPE.getIndex(), lofi_8bit_type.intValue()));
        }
        Integer lofi_alaising = hardEffect.getLOFI_ALAISING();
        if (lofi_alaising != null) {
            arrayList.add(new a(LS_FX_CMD.LS_LOFI_ALAISING.getIndex(), lofi_alaising.intValue()));
        }
        Integer vc_on_off = hardEffect.getVC_ON_OFF();
        if (vc_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_VC_ON_OFF.getIndex(), vc_on_off.intValue()));
        }
        Integer vc_formant_semi = hardEffect.getVC_FORMANT_SEMI();
        if (vc_formant_semi != null) {
            arrayList.add(new a(LS_FX_CMD.LS_VC_FORMANT_SEMI.getIndex(), vc_formant_semi.intValue()));
        }
        Integer vc_pitch_semi = hardEffect.getVC_PITCH_SEMI();
        if (vc_pitch_semi != null) {
            arrayList.add(new a(LS_FX_CMD.LS_VC_PITCH_SEMI.getIndex(), vc_pitch_semi.intValue()));
        }
        Integer vc_delaylen = hardEffect.getVC_DELAYLEN();
        if (vc_delaylen != null) {
            arrayList.add(new a(LS_FX_CMD.LS_VC_DELAYLEN.getIndex(), vc_delaylen.intValue()));
        }
        Integer vc_robotpitch = hardEffect.getVC_ROBOTPITCH();
        if (vc_robotpitch != null) {
            arrayList.add(new a(LS_FX_CMD.LS_VC_ROBOTPITCH.getIndex(), vc_robotpitch.intValue()));
        }
        Integer vc_robotpitchonoff = hardEffect.getVC_ROBOTPITCHONOFF();
        if (vc_robotpitchonoff != null) {
            arrayList.add(new a(LS_FX_CMD.LS_VC_ROBOTPITCHONOFF.getIndex(), vc_robotpitchonoff.intValue()));
        }
        Integer vc_autotuneonoff = hardEffect.getVC_AUTOTUNEONOFF();
        if (vc_autotuneonoff != null) {
            arrayList.add(new a(LS_FX_CMD.LS_VC_AUTOTUNEONOFF.getIndex(), vc_autotuneonoff.intValue()));
        }
        Integer vc_drywet = hardEffect.getVC_DRYWET();
        if (vc_drywet != null) {
            arrayList.add(new a(LS_FX_CMD.LS_VC_DRYWET.getIndex(), vc_drywet.intValue()));
        }
        Integer nsgate_on_off = hardEffect.getNSGATE_ON_OFF();
        if (nsgate_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NSGATE_ON_OFF.getIndex(), nsgate_on_off.intValue()));
        }
        Integer nsgate_thres = hardEffect.getNSGATE_THRES();
        if (nsgate_thres != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NSGATE_THRES.getIndex(), nsgate_thres.intValue()));
        }
        Integer nsgate_release = hardEffect.getNSGATE_RELEASE();
        if (nsgate_release != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NSGATE_RELEASE.getIndex(), nsgate_release.intValue()));
        }
        Integer nsgate_ratio = hardEffect.getNSGATE_RATIO();
        if (nsgate_ratio != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NSGATE_RATIO.getIndex(), nsgate_ratio.intValue()));
        }
        Integer sweep_sin_start = hardEffect.getSWEEP_SIN_START();
        if (sweep_sin_start != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SWEEP_SIN_START.getIndex(), sweep_sin_start.intValue()));
        }
        Integer sweep_sin_left_vol = hardEffect.getSWEEP_SIN_LEFT_VOL();
        if (sweep_sin_left_vol != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SWEEP_SIN_LEFT_VOL.getIndex(), sweep_sin_left_vol.intValue()));
        }
        Integer sweep_sin_right_vol = hardEffect.getSWEEP_SIN_RIGHT_VOL();
        if (sweep_sin_right_vol != null) {
            arrayList.add(new a(LS_FX_CMD.LS_SWEEP_SIN_RIGHT_VOL.getIndex(), sweep_sin_right_vol.intValue()));
        }
        Integer bandcomp_on_off = hardEffect.getBANDCOMP_ON_OFF();
        if (bandcomp_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_ON_OFF.getIndex(), bandcomp_on_off.intValue()));
        }
        Integer bandcomp_low_fc = hardEffect.getBANDCOMP_LOW_FC();
        if (bandcomp_low_fc != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_LOW_FC.getIndex(), bandcomp_low_fc.intValue()));
        }
        Integer bandcomp_high_fc = hardEffect.getBANDCOMP_HIGH_FC();
        if (bandcomp_high_fc != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_HIGH_FC.getIndex(), bandcomp_high_fc.intValue()));
        }
        Integer bandcomp_low_pregain = hardEffect.getBANDCOMP_LOW_PREGAIN();
        if (bandcomp_low_pregain != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_LOW_PREGAIN.getIndex(), bandcomp_low_pregain.intValue()));
        }
        Integer bandcomp_mid_pregain = hardEffect.getBANDCOMP_MID_PREGAIN();
        if (bandcomp_mid_pregain != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_MID_PREGAIN.getIndex(), bandcomp_mid_pregain.intValue()));
        }
        Integer bandcomp_high_pregain = hardEffect.getBANDCOMP_HIGH_PREGAIN();
        if (bandcomp_high_pregain != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_HIGH_PREGAIN.getIndex(), bandcomp_high_pregain.intValue()));
        }
        Integer bandcomp_low_threshold = hardEffect.getBANDCOMP_LOW_THRESHOLD();
        if (bandcomp_low_threshold != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_LOW_THRESHOLD.getIndex(), bandcomp_low_threshold.intValue()));
        }
        Integer bandcomp_mid_threshold = hardEffect.getBANDCOMP_MID_THRESHOLD();
        if (bandcomp_mid_threshold != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_MID_THRESHOLD.getIndex(), bandcomp_mid_threshold.intValue()));
        }
        Integer bandcomp_high_threshold = hardEffect.getBANDCOMP_HIGH_THRESHOLD();
        if (bandcomp_high_threshold != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_HIGH_THRESHOLD.getIndex(), bandcomp_high_threshold.intValue()));
        }
        Integer bandcomp_low_ratio = hardEffect.getBANDCOMP_LOW_RATIO();
        if (bandcomp_low_ratio != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_LOW_RATIO.getIndex(), bandcomp_low_ratio.intValue()));
        }
        Integer bandcomp_mid_ratio = hardEffect.getBANDCOMP_MID_RATIO();
        if (bandcomp_mid_ratio != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_MID_RATIO.getIndex(), bandcomp_mid_ratio.intValue()));
        }
        Integer bandcomp_high_ratio = hardEffect.getBANDCOMP_HIGH_RATIO();
        if (bandcomp_high_ratio != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_HIGH_RATIO.getIndex(), bandcomp_high_ratio.intValue()));
        }
        Integer bandcomp_low_attack = hardEffect.getBANDCOMP_LOW_ATTACK();
        if (bandcomp_low_attack != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_LOW_ATTACK.getIndex(), bandcomp_low_attack.intValue()));
        }
        Integer bandcomp_mid_attack = hardEffect.getBANDCOMP_MID_ATTACK();
        if (bandcomp_mid_attack != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_MID_ATTACK.getIndex(), bandcomp_mid_attack.intValue()));
        }
        Integer bandcomp_high_attack = hardEffect.getBANDCOMP_HIGH_ATTACK();
        if (bandcomp_high_attack != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_HIGH_ATTACK.getIndex(), bandcomp_high_attack.intValue()));
        }
        Integer bandcomp_low_release = hardEffect.getBANDCOMP_LOW_RELEASE();
        if (bandcomp_low_release != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_LOW_RELEASE.getIndex(), bandcomp_low_release.intValue()));
        }
        Integer bandcomp_mid_release = hardEffect.getBANDCOMP_MID_RELEASE();
        if (bandcomp_mid_release != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_MID_RELEASE.getIndex(), bandcomp_mid_release.intValue()));
        }
        Integer bandcomp_high_release = hardEffect.getBANDCOMP_HIGH_RELEASE();
        if (bandcomp_high_release != null) {
            arrayList.add(new a(LS_FX_CMD.LS_BANDCOMP_HIGH_RELEASE.getIndex(), bandcomp_high_release.intValue()));
        }
        Integer popr_on_off = hardEffect.getPOPR_ON_OFF();
        if (popr_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_POPR_ON_OFF.getIndex(), popr_on_off.intValue()));
        }
        Integer popr_mute = hardEffect.getPOPR_MUTE();
        if (popr_mute != null) {
            arrayList.add(new a(LS_FX_CMD.LS_POPR_MUTE.getIndex(), popr_mute.intValue()));
        }
        Integer popr_unmute = hardEffect.getPOPR_UNMUTE();
        if (popr_unmute != null) {
            arrayList.add(new a(LS_FX_CMD.LS_POPR_UNMUTE.getIndex(), popr_unmute.intValue()));
        }
        Integer popr_mute_time = hardEffect.getPOPR_MUTE_TIME();
        if (popr_mute_time != null) {
            arrayList.add(new a(LS_FX_CMD.LS_POPR_MUTE_TIME.getIndex(), popr_mute_time.intValue()));
        }
        Integer popr_unmute_time = hardEffect.getPOPR_UNMUTE_TIME();
        if (popr_unmute_time != null) {
            arrayList.add(new a(LS_FX_CMD.LS_POPR_UNMUTE_TIME.getIndex(), popr_unmute_time.intValue()));
        }
        Integer deesser_on_off = hardEffect.getDEESSER_ON_OFF();
        if (deesser_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_DEESSER_ON_OFF.getIndex(), deesser_on_off.intValue()));
        }
        Integer deesser_freq = hardEffect.getDEESSER_FREQ();
        if (deesser_freq != null) {
            arrayList.add(new a(LS_FX_CMD.LS_DEESSER_FREQ.getIndex(), deesser_freq.intValue()));
        }
        Integer deesser_order = hardEffect.getDEESSER_ORDER();
        if (deesser_order != null) {
            arrayList.add(new a(LS_FX_CMD.LS_DEESSER_ORDER.getIndex(), deesser_order.intValue()));
        }
        Integer deesser_thres = hardEffect.getDEESSER_THRES();
        if (deesser_thres != null) {
            arrayList.add(new a(LS_FX_CMD.LS_DEESSER_THRES.getIndex(), deesser_thres.intValue()));
        }
        Integer deesser_ratio = hardEffect.getDEESSER_RATIO();
        if (deesser_ratio != null) {
            arrayList.add(new a(LS_FX_CMD.LS_DEESSER_RATIO.getIndex(), deesser_ratio.intValue()));
        }
        Integer neolimiter_on_off = hardEffect.getNEOLIMITER_ON_OFF();
        if (neolimiter_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NEOLIMITER_ON_OFF.getIndex(), neolimiter_on_off.intValue()));
        }
        Integer neolimiter_threshold_dbfs = hardEffect.getNEOLIMITER_THRESHOLD_DBFS();
        if (neolimiter_threshold_dbfs != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NEOLIMITER_THRESHOLD_DBFS.getIndex(), neolimiter_threshold_dbfs.intValue()));
        }
        Integer neolimiter_makeup_gain_db = hardEffect.getNEOLIMITER_MAKEUP_GAIN_DB();
        if (neolimiter_makeup_gain_db != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NEOLIMITER_MAKEUP_GAIN_DB.getIndex(), neolimiter_makeup_gain_db.intValue()));
        }
        Integer neolimiter_attack_time_ms = hardEffect.getNEOLIMITER_ATTACK_TIME_MS();
        if (neolimiter_attack_time_ms != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NEOLIMITER_ATTACK_TIME_MS.getIndex(), neolimiter_attack_time_ms.intValue()));
        }
        Integer neolimiter_release_time_ms = hardEffect.getNEOLIMITER_RELEASE_TIME_MS();
        if (neolimiter_release_time_ms != null) {
            arrayList.add(new a(LS_FX_CMD.LS_NEOLIMITER_RELEASE_TIME_MS.getIndex(), neolimiter_release_time_ms.intValue()));
        }
        Integer clear_all = hardEffect.getCLEAR_ALL();
        if (clear_all != null) {
            arrayList.add(new a(LS_FX_CMD.LS_CLEAR_ALL.getIndex(), clear_all.intValue()));
        }
        Integer autoadapt_manule_start = hardEffect.getAUTOADAPT_MANULE_START();
        if (autoadapt_manule_start != null) {
            arrayList.add(new a(LS_FX_CMD.LS_AUTOADAPT_MANULE_START.getIndex(), autoadapt_manule_start.intValue()));
        }
        Integer auto_pregain_on_off = hardEffect.getAUTO_PREGAIN_ON_OFF();
        if (auto_pregain_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_AUTO_PREGAIN_ON_OFF.getIndex(), auto_pregain_on_off.intValue()));
        }
        Integer aec_start_test = hardEffect.getAEC_START_TEST();
        if (aec_start_test != null) {
            arrayList.add(new a(LS_FX_CMD.LS_AEC_START_TEST.getIndex(), aec_start_test.intValue()));
        }
        Integer aec_on_off = hardEffect.getAEC_ON_OFF();
        if (aec_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_AEC_ON_OFF.getIndex(), aec_on_off.intValue()));
        }
        Integer mic_gain = hardEffect.getMIC_GAIN();
        if (mic_gain != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_GAIN.getIndex(), mic_gain.intValue()));
        }
        Integer mic_eq_on_off = hardEffect.getMIC_EQ_ON_OFF();
        if (mic_eq_on_off != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_ON_OFF.getIndex(), mic_eq_on_off.intValue()));
        }
        Integer mic_eq_type1 = hardEffect.getMIC_EQ_TYPE1();
        if (mic_eq_type1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_TYPE1.getIndex(), mic_eq_type1.intValue()));
        }
        Integer mic_eq_fc1 = hardEffect.getMIC_EQ_FC1();
        if (mic_eq_fc1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_FC1.getIndex(), mic_eq_fc1.intValue()));
        }
        Integer mic_eq_gain1 = hardEffect.getMIC_EQ_GAIN1();
        if (mic_eq_gain1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_GAIN1.getIndex(), mic_eq_gain1.intValue()));
        }
        Integer mic_eq_q1 = hardEffect.getMIC_EQ_Q1();
        if (mic_eq_q1 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_Q1.getIndex(), mic_eq_q1.intValue()));
        }
        Integer mic_eq_type2 = hardEffect.getMIC_EQ_TYPE2();
        if (mic_eq_type2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_TYPE2.getIndex(), mic_eq_type2.intValue()));
        }
        Integer mic_eq_fc2 = hardEffect.getMIC_EQ_FC2();
        if (mic_eq_fc2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_FC2.getIndex(), mic_eq_fc2.intValue()));
        }
        Integer mic_eq_gain2 = hardEffect.getMIC_EQ_GAIN2();
        if (mic_eq_gain2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_GAIN2.getIndex(), mic_eq_gain2.intValue()));
        }
        Integer mic_eq_q2 = hardEffect.getMIC_EQ_Q2();
        if (mic_eq_q2 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_Q2.getIndex(), mic_eq_q2.intValue()));
        }
        Integer mic_eq_type3 = hardEffect.getMIC_EQ_TYPE3();
        if (mic_eq_type3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_TYPE3.getIndex(), mic_eq_type3.intValue()));
        }
        Integer mic_eq_fc3 = hardEffect.getMIC_EQ_FC3();
        if (mic_eq_fc3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_FC3.getIndex(), mic_eq_fc3.intValue()));
        }
        Integer mic_eq_gain3 = hardEffect.getMIC_EQ_GAIN3();
        if (mic_eq_gain3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_GAIN3.getIndex(), mic_eq_gain3.intValue()));
        }
        Integer mic_eq_q3 = hardEffect.getMIC_EQ_Q3();
        if (mic_eq_q3 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_Q3.getIndex(), mic_eq_q3.intValue()));
        }
        Integer mic_eq_type4 = hardEffect.getMIC_EQ_TYPE4();
        if (mic_eq_type4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_TYPE4.getIndex(), mic_eq_type4.intValue()));
        }
        Integer mic_eq_fc4 = hardEffect.getMIC_EQ_FC4();
        if (mic_eq_fc4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_FC4.getIndex(), mic_eq_fc4.intValue()));
        }
        Integer mic_eq_gain4 = hardEffect.getMIC_EQ_GAIN4();
        if (mic_eq_gain4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_GAIN4.getIndex(), mic_eq_gain4.intValue()));
        }
        Integer mic_eq_q4 = hardEffect.getMIC_EQ_Q4();
        if (mic_eq_q4 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_Q4.getIndex(), mic_eq_q4.intValue()));
        }
        Integer mic_eq_type5 = hardEffect.getMIC_EQ_TYPE5();
        if (mic_eq_type5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_TYPE5.getIndex(), mic_eq_type5.intValue()));
        }
        Integer mic_eq_fc5 = hardEffect.getMIC_EQ_FC5();
        if (mic_eq_fc5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_FC5.getIndex(), mic_eq_fc5.intValue()));
        }
        Integer mic_eq_gain5 = hardEffect.getMIC_EQ_GAIN5();
        if (mic_eq_gain5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_GAIN5.getIndex(), mic_eq_gain5.intValue()));
        }
        Integer mic_eq_q5 = hardEffect.getMIC_EQ_Q5();
        if (mic_eq_q5 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_Q5.getIndex(), mic_eq_q5.intValue()));
        }
        Integer mic_eq_type6 = hardEffect.getMIC_EQ_TYPE6();
        if (mic_eq_type6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_TYPE6.getIndex(), mic_eq_type6.intValue()));
        }
        Integer mic_eq_fc6 = hardEffect.getMIC_EQ_FC6();
        if (mic_eq_fc6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_FC6.getIndex(), mic_eq_fc6.intValue()));
        }
        Integer mic_eq_gain6 = hardEffect.getMIC_EQ_GAIN6();
        if (mic_eq_gain6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_GAIN6.getIndex(), mic_eq_gain6.intValue()));
        }
        Integer mic_eq_q6 = hardEffect.getMIC_EQ_Q6();
        if (mic_eq_q6 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_Q6.getIndex(), mic_eq_q6.intValue()));
        }
        Integer mic_eq_type7 = hardEffect.getMIC_EQ_TYPE7();
        if (mic_eq_type7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_TYPE7.getIndex(), mic_eq_type7.intValue()));
        }
        Integer mic_eq_fc7 = hardEffect.getMIC_EQ_FC7();
        if (mic_eq_fc7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_FC7.getIndex(), mic_eq_fc7.intValue()));
        }
        Integer mic_eq_gain7 = hardEffect.getMIC_EQ_GAIN7();
        if (mic_eq_gain7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_GAIN7.getIndex(), mic_eq_gain7.intValue()));
        }
        Integer mic_eq_q7 = hardEffect.getMIC_EQ_Q7();
        if (mic_eq_q7 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_Q7.getIndex(), mic_eq_q7.intValue()));
        }
        Integer mic_eq_type8 = hardEffect.getMIC_EQ_TYPE8();
        if (mic_eq_type8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_TYPE8.getIndex(), mic_eq_type8.intValue()));
        }
        Integer mic_eq_fc8 = hardEffect.getMIC_EQ_FC8();
        if (mic_eq_fc8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_FC8.getIndex(), mic_eq_fc8.intValue()));
        }
        Integer mic_eq_gain8 = hardEffect.getMIC_EQ_GAIN8();
        if (mic_eq_gain8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_GAIN8.getIndex(), mic_eq_gain8.intValue()));
        }
        Integer mic_eq_q8 = hardEffect.getMIC_EQ_Q8();
        if (mic_eq_q8 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_Q8.getIndex(), mic_eq_q8.intValue()));
        }
        Integer mic_eq_type9 = hardEffect.getMIC_EQ_TYPE9();
        if (mic_eq_type9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_TYPE9.getIndex(), mic_eq_type9.intValue()));
        }
        Integer mic_eq_fc9 = hardEffect.getMIC_EQ_FC9();
        if (mic_eq_fc9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_FC9.getIndex(), mic_eq_fc9.intValue()));
        }
        Integer mic_eq_gain9 = hardEffect.getMIC_EQ_GAIN9();
        if (mic_eq_gain9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_GAIN9.getIndex(), mic_eq_gain9.intValue()));
        }
        Integer mic_eq_q9 = hardEffect.getMIC_EQ_Q9();
        if (mic_eq_q9 != null) {
            arrayList.add(new a(LS_FX_CMD.LS_MIC_EQ_Q9.getIndex(), mic_eq_q9.intValue()));
        }
        byte[] bArrO = o(arrayList);
        b bVar = b.f41a;
        if (bArrO == null) {
            string = null;
        } else {
            string = Arrays.toString(bArrO);
            h.c(string, "java.util.Arrays.toString(this)");
        }
        bVar.b("InitConfig", h.i("dealHardEffect, ", string));
        return bArrO;
    }

    public final byte[] n(HardEffectVolume hardEffectVolume) {
        String string;
        ArrayList<a> arrayList = new ArrayList<>();
        double[] micVolcurveX = hardEffectVolume.getMicVolcurveX();
        if (micVolcurveX != null) {
            arrayList.add(new a(LS_FX_CMD.MIC_VOLUME_X_SIZE.getIndex(), micVolcurveX.length));
            for (double d2 : micVolcurveX) {
                int iA = m.a(d2 * ((double) 10000));
                arrayList.add(new a(i.a(iA >>> 16), k.a((short) iA) & 65535));
            }
        }
        double[] micVolcurveY = hardEffectVolume.getMicVolcurveY();
        if (micVolcurveY != null) {
            arrayList.add(new a(LS_FX_CMD.MIC_VOLUME_Y_SIZE.getIndex(), micVolcurveY.length));
            for (double d3 : micVolcurveY) {
                int iA2 = m.a(d3 * ((double) 10000));
                arrayList.add(new a(i.a(iA2 >>> 16), k.a((short) iA2) & 65535));
            }
        }
        double[] sysVolcurveX = hardEffectVolume.getSysVolcurveX();
        if (sysVolcurveX != null) {
            arrayList.add(new a(LS_FX_CMD.SYS_VOLUME_X_SIZE.getIndex(), sysVolcurveX.length));
            for (double d4 : sysVolcurveX) {
                int iA3 = m.a(d4 * ((double) 10000));
                arrayList.add(new a(i.a(iA3 >>> 16), k.a((short) iA3) & 65535));
            }
        }
        double[] sysVolcurveY = hardEffectVolume.getSysVolcurveY();
        if (sysVolcurveY != null) {
            arrayList.add(new a(LS_FX_CMD.SYS_VOLUME_Y_SIZE.getIndex(), sysVolcurveY.length));
            for (double d5 : sysVolcurveY) {
                int iA4 = m.a(d5 * ((double) 10000));
                arrayList.add(new a(i.a(iA4 >>> 16), k.a((short) iA4) & 65535));
            }
        }
        byte[] bArrO = o(arrayList);
        b bVar = b.f41a;
        if (bArrO == null) {
            string = null;
        } else {
            string = Arrays.toString(bArrO);
            h.c(string, "java.util.Arrays.toString(this)");
        }
        bVar.b("InitConfig", h.i("dealHardEffectVolume, ", string));
        return bArrO;
    }

    public final byte[] o(ArrayList<a> arrayList) {
        if (arrayList.size() == 0) {
            return null;
        }
        int size = (arrayList.size() * 4) + 4;
        byte[] bArr = new byte[size];
        bArr[0] = 86;
        int i = 3;
        bArr[1] = 3;
        bArr[2] = (byte) (arrayList.size() * 4);
        for (a aVar : arrayList) {
            int i2 = i + 1;
            bArr[i] = (byte) i.a(i.a(aVar.a()) >>> 8);
            int i3 = i2 + 1;
            bArr[i2] = (byte) k.a((short) aVar.a());
            int i4 = i3 + 1;
            bArr[i3] = (byte) i.a(i.a(aVar.b()) >>> 8);
            i = i4 + 1;
            bArr[i4] = (byte) k.a((short) aVar.b());
        }
        bArr[i] = a(this, bArr, 0, 2);
        if (size > 256) {
            return null;
        }
        return bArr;
    }

    public final ConfigMgr p() {
        return (ConfigMgr) f355b.getValue();
    }

    public final void q(String str) {
        if (str == null || str.length() == 0) {
            b.f41a.d("InitConfig", "读取配置文件失败,加载默认配置");
            c.c(OooO00o.OooO00o.OooO0O0.a.f39a.a(), "init_config_path", "byd/default/config.json");
            str = q.b("byd/default/config.json");
        }
        String strG = g(str);
        if (strG == null) {
            return;
        }
        OooO0O0 oooO0O0 = f354a;
        Object objD = j.d(strG, DefaultConfig.class);
        h.c(objD, "fromJson(it, DefaultConfig::class.java)");
        oooO0O0.j((DefaultConfig) objD);
    }

    public final Map<String, String> r() {
        return (Map) f356c.getValue();
    }

    public final void s() {
        DefaultMicConfig defaultMicConfig = (DefaultMicConfig) new Gson().fromJson(q.b("mic_config.json"), DefaultMicConfig.class);
        OooO00o.OooO00o.OooO0O0.a aVar = OooO00o.OooO00o.OooO0O0.a.f39a;
        c.b(aVar.a(), "mic_period_ota", defaultMicConfig.getMicPeriod());
        c.b(aVar.a(), "mic_version_ota", defaultMicConfig.getMicListVersion());
        c.c(aVar.a(), "mic_list", new Gson().toJson(defaultMicConfig.getMicList()));
    }

    public final void t() {
        String strF;
        String strF2;
        String strF3;
        String strF4;
        String strF5;
        a.a.d.b.a aVar = a.a.d.b.a.f564a;
        if (aVar.j() == null && (strF5 = r.b("hard_config").f("hardEffect", null)) != null) {
            aVar.f((byte[]) j.d(strF5, byte[].class));
        }
        if (aVar.n() == null && (strF4 = r.b("hard_config").f("hardEffectVolume", null)) != null) {
            aVar.i((byte[]) j.d(strF4, byte[].class));
        }
        if (aVar.m() == null && (strF3 = r.b("hard_config").f("hardEffectMicList", null)) != null) {
            aVar.h((List) j.e(strF3, j.g(HardEffectMicItem.class)));
        }
        if (aVar.k() == null && (strF2 = r.b("hard_config").f("hardEffectDglList", null)) != null) {
            aVar.d((List) j.e(strF2, j.g(HardEffectDglItem.class)));
        }
        if (aVar.o() == null && (strF = r.b("hard_config").f("hardEffectTVLiftList", null)) != null) {
            aVar.l((List) j.e(strF, j.g(HardEffectDglItem.class)));
        }
        OooO0o oooO0o = OooO0o.f425a;
        if (h.a(oooO0o.o().a(), Boolean.TRUE)) {
            oooO0o.N();
            OooO.f199a.c2();
            OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0OO.f361a.j0();
        }
        OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.b.a aVar2 = OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.b.a.f469a;
        Long lA = oooO0o.j().a();
        h.b(lA);
        long jLongValue = lA.longValue();
        Integer numA = oooO0o.z().a();
        h.b(numA);
        aVar2.d(jLongValue, numA.intValue());
    }
}
