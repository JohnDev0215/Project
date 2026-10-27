package OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.b;

import OooO00o.OooO00o.OooO0O0.OooO0o0.OooO00o;
import OooO00o.OooO00o.OooO0O0.b;
import OooO00o.OooO00o.OooO0o0.OooO0OO.OooO0O0.OooO0O0;
import com.blankj.utilcode.util.j;
import com.blankj.utilcode.util.q;
import com.loostone.libtuning.data.config.vendor.EnvironmentalSoundConfig;
import com.loostone.libtuning.data.config.vendor.EnvironmentalSoundItem;
import java.io.File;
import java.util.ArrayList;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f469a = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ArrayList<EnvironmentalSoundItem> f470b;

    public final String a(long j, int i) throws Throwable {
        String str = "environmental_sound.json";
        String strG = j == 200001 ? "vendor/tvconfig/loostone/environmental_sound.json" : OooO00o.f18a.g("environmental_sound.json");
        if (new File(strG).exists()) {
            b.f41a.e("EnvironmentalSound", " ------> 安装路径下配置环境音效");
            String strC = OooO00o.OooO00o.OooO0O0.OooO0o0.b.c(strG);
            h.c(strC, "{\n            LogTuning.i(TAG, \" ------> 安装路径下配置环境音效\")\n\n            OldCommonUtil.getStringFromFile(path)\n        }");
            return strC;
        }
        b.f41a.e("EnvironmentalSound", " ------> 默认配置环境音效 channel: " + j + ", model: " + i);
        if (j == 400000) {
            str = "byd/scene_effect/environmental_sound.json";
        } else if (j == 200000) {
            str = "skyworth_a/scene_effect/environmental_sound.json";
        } else if (j == 200001) {
            str = "skyworth_b/scene_effect/environmental_sound.json";
        } else if (j == 200002) {
            str = "skyworth_c/scene_effect/environmental_sound.json";
        }
        String strB = q.b(str);
        h.c(strB, "{\n            LogTuning.i(TAG, \" ------> 默认配置环境音效 channel: $channel, model: $model\")\n\n            path = when (channel) {\n                // 比亚迪渠道\n                Channel.BYD.BYD_A -> {\n                    \"byd/scene_effect/environmental_sound.json\"\n                }\n\n                // 创维KO方案-国内\n                Channel.SkyWorth.SKYWORTH_A -> {\n                    \"skyworth_a/scene_effect/environmental_sound.json\"\n                }\n\n                // 创维SO方案-海外\n                Channel.SkyWorth.SKYWORTH_B -> {\n                    \"skyworth_b/scene_effect/environmental_sound.json\"\n                }\n\n                // 创维酷沃智行 - SO方案\n                Channel.SkyWorth.SKYWORTH_C -> {\n                    \"skyworth_c/scene_effect/environmental_sound.json\"\n                }\n\n                else -> {\n                    \"environmental_sound.json\"\n                }\n            }\n\n            ResourceUtils.readAssets2String(path)\n        }");
        return strB;
    }

    public final ArrayList<EnvironmentalSoundItem> b() {
        return f470b;
    }

    public final void c(ArrayList<EnvironmentalSoundItem> arrayList) {
        f470b = arrayList;
    }

    public final void d(long j, int i) {
        EnvironmentalSoundConfig environmentalSoundConfig;
        String strG = OooO0O0.f354a.g(a(j, i));
        if (strG == null || (environmentalSoundConfig = (EnvironmentalSoundConfig) j.d(strG, EnvironmentalSoundConfig.class)) == null) {
            return;
        }
        f469a.c(environmentalSoundConfig.getEnvironmentalSoundList());
    }
}
