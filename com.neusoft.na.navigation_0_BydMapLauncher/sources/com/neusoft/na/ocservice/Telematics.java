package com.neusoft.na.ocservice;

import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.util.Log;
import com.neusoft.na.nodejs.NodeInstance;
import com.neusoft.na.nodejs.NodeJs;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.na.ocservice.util.SystemUtils;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import mu.KLogging;

/* JADX INFO: compiled from: Telematics.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0002J\u001c\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0002J\u0014\u0010\u000f\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0011J\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/neusoft/na/ocservice/Telematics;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "nodeInst", "Lcom/neusoft/na/nodejs/NodeInstance;", "running", "", "getRunning", "()Z", "getPath", "", "getSystemProperties", "key", "start", "args", "", "stop", "", "Companion", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class Telematics {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "XJTelematics";
    private final Context context;
    private NodeInstance nodeInst;

    /* JADX INFO: compiled from: Telematics.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/neusoft/na/ocservice/Telematics$Companion;", "Lmu/KLogging;", "()V", "TAG", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion extends KLogging {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public Telematics(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        try {
            NodeJs.init(new String[0]);
        } catch (Exception e) {
            INSTANCE.getLogger().warn(new Function0<Object>() { // from class: com.neusoft.na.ocservice.Telematics.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return e.getMessage();
                }
            });
        }
    }

    public final boolean getRunning() {
        return this.nodeInst != null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01b7 A[PHI: r10
  0x01b7: PHI (r10v5 java.lang.String) = 
  (r10v0 java.lang.String)
  (r10v0 java.lang.String)
  (r10v0 java.lang.String)
  (r10v1 java.lang.String)
  (r10v2 java.lang.String)
  (r10v0 java.lang.String)
  (r10v0 java.lang.String)
  (r10v0 java.lang.String)
  (r10v3 java.lang.String)
  (r10v0 java.lang.String)
  (r10v4 java.lang.String)
  (r10v0 java.lang.String)
  (r10v0 java.lang.String)
  (r10v0 java.lang.String)
 binds: [B:135:0x0275, B:126:0x025a, B:98:0x01d0, B:99:0x01d2, B:93:0x01b5, B:92:0x01b3, B:85:0x019f, B:82:0x0196, B:90:0x01ab, B:66:0x0167, B:80:0x018e, B:49:0x0111, B:46:0x0108, B:43:0x00ff] A[DONT_GENERATE, DONT_INLINE]] */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:217)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:68)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean start(java.util.List<java.lang.String> r22) {
        /*
            Method dump skipped, instruction units count: 1064
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.Telematics.start(java.util.List):boolean");
    }

    public final void stop() {
        NodeInstance nodeInstance = this.nodeInst;
        if (nodeInstance != null) {
            nodeInstance.terminate();
        }
    }

    private final String getSystemProperties(Context context, String key) {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            Object objInvoke = clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, key);
            Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.String");
            return (String) objInvoke;
        } catch (Exception e) {
            Log.e(TAG, "get: " + e.getMessage());
            return "";
        }
    }

    private final String getPath() throws ClassNotFoundException {
        String byd8155CountryCode;
        if (CheckBoardKt.isByd()) {
            byd8155CountryCode = new SystemUtils().getSystemPropertiesValue(BYDAutoSysPropertiesDevice.COUNTRY_CODE);
        } else {
            byd8155CountryCode = com.neusoft.na.common.util.SystemUtils.INSTANCE.getByd8155CountryCode(this.context);
        }
        Log.i(TAG, "onecloud countryCode: " + byd8155CountryCode);
        return (CollectionsKt.contains(CollectionsKt.arrayListOf("966", "971", "974", "973", "968", "965", "961", "962"), byd8155CountryCode) || CollectionsKt.contains(CollectionsKt.arrayListOf("20", "212", "238", "216"), byd8155CountryCode) || CollectionsKt.contains(CollectionsKt.arrayListOf("27", "262", "263", "260", "230", "261", "244", "267"), byd8155CountryCode)) ? "occlient/config/na_onecloudclient-EU.json" : "occlient/config/na_onecloudclient-US.json";
    }
}
