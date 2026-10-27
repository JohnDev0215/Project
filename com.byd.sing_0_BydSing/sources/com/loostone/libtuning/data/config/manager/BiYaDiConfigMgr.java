package com.loostone.libtuning.data.config.manager;

import OooO00o.OooO00o.OooO0O0.OooO0o0.c;
import OooO00o.OooO00o.OooO0O0.b;
import android.text.TextUtils;
import com.android.volley.BuildConfig;
import com.loostone.libtuning.data.config.ConfigMgr;
import com.loostone.libtuning.define.BaseDefine;
import java.io.IOException;
import java.util.List;
import kotlin.a;
import kotlin.collections.k;
import kotlin.d;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.h;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.n;

/* JADX INFO: loaded from: classes.dex */
public final class BiYaDiConfigMgr extends ConfigMgr {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = BiYaDiConfigMgr.class.getSimpleName();
    private int hardModel;
    private final a priority$delegate = d.b(new kotlin.jvm.b.a<List<String>>() { // from class: com.loostone.libtuning.data.config.manager.BiYaDiConfigMgr$priority$2
        {
            super(0);
        }

        @Override // kotlin.jvm.b.a
        public final List<String> invoke() {
            String str;
            Integer numA;
            List<String> listG = k.g("byd/" + this.this$0.getSpeakerModel() + '/' + this.this$0.getModelSuffix() + ".json", "byd/" + this.this$0.getSpeakerModel() + "/config.json", "byd/config.json");
            BiYaDiConfigMgr biYaDiConfigMgr = this.this$0;
            Integer numA2 = n.a(biYaDiConfigMgr.getMultiChannel());
            String string = BuildConfig.FLAVOR;
            if (numA2 != null) {
                int iIntValue = numA2.intValue();
                listG.add(0, "byd/" + biYaDiConfigMgr.getSpeakerModel() + '/' + biYaDiConfigMgr.getModelSuffix() + '_' + iIntValue + ".json");
                StringBuilder sb = new StringBuilder();
                sb.append(BuildConfig.FLAVOR);
                sb.append("__MC");
                sb.append(iIntValue);
                string = sb.toString();
            }
            if ((biYaDiConfigMgr.getPropertiesMap$libTuning_BIYADI_OVERSEAS_8155Release().containsKey(BaseDefine.SystemPropertiesKey.AMPLIFIER_COUNT) ? biYaDiConfigMgr : null) != null && (str = biYaDiConfigMgr.getPropertiesMap$libTuning_BIYADI_OVERSEAS_8155Release().get(BaseDefine.SystemPropertiesKey.AMPLIFIER_COUNT)) != null && (numA = n.a(str)) != null) {
                int iIntValue2 = numA.intValue();
                listG.add(0, "byd/" + biYaDiConfigMgr.getSpeakerModel() + '/' + biYaDiConfigMgr.getModelSuffix() + "__AC" + iIntValue2 + ".json");
                StringBuilder sb2 = new StringBuilder();
                sb2.append(string);
                sb2.append("__AC");
                sb2.append(iIntValue2);
                string = sb2.toString();
            }
            if ((string.length() > 0) && StringsKt__StringsKt.A(string, "_", 0, false, 6, null) > 4) {
                listG.add(0, "byd/" + biYaDiConfigMgr.getSpeakerModel() + '/' + biYaDiConfigMgr.getModelSuffix() + string + ".json");
            }
            b bVar = b.f41a;
            String str2 = BiYaDiConfigMgr.TAG;
            h.c(str2, "TAG");
            bVar.b(str2, "extraParams = " + string + ", list size = " + listG.size());
            return listG;
        }
    });
    private final a speakerModel$delegate = d.b(new kotlin.jvm.b.a<String>() { // from class: com.loostone.libtuning.data.config.manager.BiYaDiConfigMgr$speakerModel$2
        {
            super(0);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:20:0x005d  */
        @Override // kotlin.jvm.b.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String invoke() {
            /*
                Method dump skipped, instruction units count: 260
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loostone.libtuning.data.config.manager.BiYaDiConfigMgr$speakerModel$2.invoke():java.lang.String");
        }
    });
    private final a multiChannel$delegate = d.b(new kotlin.jvm.b.a<String>() { // from class: com.loostone.libtuning.data.config.manager.BiYaDiConfigMgr$multiChannel$2
        @Override // kotlin.jvm.b.a
        public final String invoke() {
            String strB = c.f35a.b("sys.ext.amp.multi.channel");
            b bVar = b.f41a;
            String str = BiYaDiConfigMgr.TAG;
            h.c(str, "TAG");
            bVar.b(str, h.i("multi Channel = ", strB));
            return strB;
        }
    });
    private final a modelSuffix$delegate = d.b(new kotlin.jvm.b.a<String>() { // from class: com.loostone.libtuning.data.config.manager.BiYaDiConfigMgr$modelSuffix$2
        {
            super(0);
        }

        /* JADX WARN: Removed duplicated region for block: B:42:0x00d9  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00e8  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x00f7  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0199  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x01a7  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x01b5  */
        /* JADX WARN: Removed duplicated region for block: B:58:0x01c3  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x01d1  */
        @Override // kotlin.jvm.b.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.String invoke() {
            /*
                Method dump skipped, instruction units count: 718
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loostone.libtuning.data.config.manager.BiYaDiConfigMgr$modelSuffix$2.invoke():java.lang.String");
        }
    });
    private int model = -1;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getModelSuffix() {
        return (String) this.modelSuffix$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getMultiChannel() {
        return (String) this.multiChannel$delegate.getValue();
    }

    private final List<String> getPriority() {
        return (List) this.priority$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSpeakerModel() {
        return (String) this.speakerModel$delegate.getValue();
    }

    @Override // com.loostone.libtuning.data.config.ConfigMgr
    public String initConfig(String str) {
        h.d(str, "extraPath");
        for (String str2 : getPriority()) {
            b bVar = b.f41a;
            String str3 = TAG;
            h.c(str3, "TAG");
            bVar.b(str3, h.i("assets priority path = ", str2));
            if (getPathList$libTuning_BIYADI_OVERSEAS_8155Release().contains(str2)) {
                return str2;
            }
        }
        return "config.json";
    }

    @Override // com.loostone.libtuning.data.config.ConfigMgr
    public void listAssetsPath(String str) {
        h.d(str, "assetsFilePath");
        try {
            String[] list = OooO00o.OooO00o.OooO0O0.a.f39a.a().getAssets().list(str);
            if (list != null) {
                if (!(list.length == 0)) {
                    for (String str2 : list) {
                        if (TextUtils.isEmpty(str)) {
                            h.c(str2, "it");
                            listAssetsPath(str2);
                        } else {
                            listAssetsPath(str + '/' + ((Object) str2));
                        }
                    }
                    return;
                }
            }
            getPathList$libTuning_BIYADI_OVERSEAS_8155Release().add(str);
        } catch (IOException e) {
            b bVar = b.f41a;
            String str3 = TAG;
            h.c(str3, "TAG");
            bVar.d(str3, h.i("assetsFilePath = , error :: ", e));
            e.printStackTrace();
        }
    }

    @Override // com.loostone.libtuning.data.config.ConfigMgr
    public void setModel(int i) {
        this.model = i;
    }
}
