package com.neusoft.na.ocservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.util.Log;
import com.neusoft.na.common.ApplicationStatus;
import com.neusoft.na.common.LaneLineInfo;
import com.neusoft.na.common.ServiceBase;
import com.neusoft.na.common.Tool;
import com.neusoft.na.sens.IOneCoreSenService;
import com.neusoft.na.sens.OneCoreSenRegister;
import com.neusoft.na.sens.OneCoreSenServiceImp;
import com.neusoft.na.sens.VisionLaneLineInfo;
import com.neusoft.na.sens.VisionLaneLineInfo.LaneLine;
import com.neusoft.na.sens.VisionLaneLineInfo.Point;
import com.neusoft.na.sens.VisionLaneLineInfo.StatisInfo;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SensService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001a\u001a\u00020\u0018H\u0016J\b\u0010\u001b\u001a\u00020\u0018H\u0014J\"\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001dH\u0017J\b\u0010 \u001a\u00020\u0018H\u0002J\b\u0010!\u001a\u00020\"H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/neusoft/na/ocservice/SensService;", "Lcom/neusoft/na/common/ServiceBase;", "()V", "intent", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "oneCoreSenstance", "Lcom/neusoft/na/sens/IOneCoreSenService$Stub;", "register", "Lcom/neusoft/na/sens/OneCoreSenRegister;", "getEnColor", "Lcom/neusoft/na/sens/VisionLaneLineInfo$EnColor;", "color", "Lcom/neusoft/na/common/LaneLineInfo$EnColor;", "getEnConfidence", "Lcom/neusoft/na/sens/VisionLaneLineInfo$EnConfidence;", "confidence", "Lcom/neusoft/na/common/LaneLineInfo$EnConfidence;", "getEnType", "Lcom/neusoft/na/sens/VisionLaneLineInfo$EnType;", "type", "Lcom/neusoft/na/common/LaneLineInfo$EnType;", "init", "", "onCreate", "onDestroy", "onStart", "onStartCommand", "", "flags", "startId", "sendSensorData", "startOneCoreSens", "", "ocservice_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SensService extends ServiceBase {
    private final Intent intent;
    private IOneCoreSenService.Stub oneCoreSenstance;
    private OneCoreSenRegister register;

    /* JADX INFO: compiled from: SensService.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[LaneLineInfo.EnColor.values().length];
            try {
                iArr[LaneLineInfo.EnColor.COLOR_UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LaneLineInfo.EnColor.COLOR_YELLOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[LaneLineInfo.EnColor.COLOR_WHITE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[LaneLineInfo.EnType.values().length];
            try {
                iArr2[LaneLineInfo.EnType.TYPE_UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_SOLID.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_DASHED.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_DOUBLELINE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_DOUBLESOLIDDASHED.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_DOUBLEDASHEDSOLID.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_DOUBLESOLIDSOLID.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_DOUBLEDASHEDDASHED.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_DECELERATION.ordinal()] = 9;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_HOVLANE.ordinal()] = 10;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_LINEROADEDGE.ordinal()] = 11;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_CURB.ordinal()] = 12;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_CONESPOLES.ordinal()] = 13;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_BOTTSDOTS.ordinal()] = 14;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_BARRIER.ordinal()] = 15;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr2[LaneLineInfo.EnType.TYPE_ROADEDGE.ordinal()] = 16;
            } catch (NoSuchFieldError unused19) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[LaneLineInfo.EnConfidence.values().length];
            try {
                iArr3[LaneLineInfo.EnConfidence.CONFIDENCE_UNKNOWN.ordinal()] = 1;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr3[LaneLineInfo.EnConfidence.CONFIDENCE_LOW.ordinal()] = 2;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr3[LaneLineInfo.EnConfidence.CONFIDENCE_MEDIUM.ordinal()] = 3;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr3[LaneLineInfo.EnConfidence.CONFIDENCE_HIGH.ordinal()] = 4;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr3[LaneLineInfo.EnConfidence.CONFIDENCE_LOWHS.ordinal()] = 5;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr3[LaneLineInfo.EnConfidence.CONFIDENCE_MEDIUMHS.ordinal()] = 6;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr3[LaneLineInfo.EnConfidence.CONFIDENCE_HIGHHS.ordinal()] = 7;
            } catch (NoSuchFieldError unused26) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    public SensService() {
        super("sens");
        this.intent = new Intent();
    }

    public final Intent getIntent() {
        return this.intent;
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public void onCreate() {
        super.onCreate();
        ServiceBase.INSTANCE.getLogger().info("cache dir: " + getPrivateCacheDir());
        ServiceBase.INSTANCE.getLogger().info("temp dir: " + getPrivateTempDir());
        ServiceBase.INSTANCE.getLogger().info("assets install dir: " + getAssetsInstallDir());
        if (getMApplicationStatus().getMStatus() == ApplicationStatus.Status.UNINITIALIZED) {
            init();
        }
        registerReceiver(new BroadcastReceiver() { // from class: com.neusoft.na.ocservice.SensService.onCreate.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Log.i(ServiceBase.INSTANCE.getTAG(), "Ar LaneInfo received");
                if (StringsKt.equals$default(intent != null ? intent.getAction() : null, "com.neusoft.arComponent.LaneInfo", false, 2, null)) {
                    LaneLineInfo laneLineInfo = intent != null ? (LaneLineInfo) intent.getParcelableExtra("LaneLineInfo") : null;
                    if (laneLineInfo != null) {
                        Log.i(ServiceBase.INSTANCE.getTAG(), "Ar LaneInfo received size = " + laneLineInfo.getLaneLineList().size());
                    } else {
                        Log.i(ServiceBase.INSTANCE.getTAG(), "Ar LaneInfo received laneLineInfo Null");
                    }
                    VisionLaneLineInfo visionLaneLineInfo = new VisionLaneLineInfo();
                    Intrinsics.checkNotNull(laneLineInfo);
                    visionLaneLineInfo.set_Timestamp(laneLineInfo.getTimeStamp());
                    visionLaneLineInfo.set_CommunicationStatisInfo(visionLaneLineInfo.new StatisInfo(laneLineInfo.getCommunicationCostTime(), laneLineInfo.getCommunicationFps(), laneLineInfo.getCommunicationAvFps()));
                    visionLaneLineInfo.set_VisionStatisInfo(visionLaneLineInfo.new StatisInfo(laneLineInfo.getVisionCostTime(), laneLineInfo.getVisionFps(), laneLineInfo.getVisionAvFps()));
                    int size = laneLineInfo.getLaneLineList().size();
                    for (int i = 0; i < size; i++) {
                        Log.i(ServiceBase.INSTANCE.getTAG(), "Ar LaneInfo received detail i = " + i + " color = " + laneLineInfo.getLaneLineColor(i));
                        visionLaneLineInfo.addLaneLineList(visionLaneLineInfo.new LaneLine(laneLineInfo.getLaneLineId(i), SensService.this.getEnColor(laneLineInfo.getLaneLineColor(i)), SensService.this.getEnType(laneLineInfo.getLaneLineType(i)), laneLineInfo.getLaneLineList().get(i).getCoeffsList(), laneLineInfo.getLaneLineWidth(i), visionLaneLineInfo.new Point(laneLineInfo.getLaneLinePointStartX(i), laneLineInfo.getLaneLinePointStartY(i), laneLineInfo.getLaneLinePointStartZ(i)), visionLaneLineInfo.new Point(laneLineInfo.getLaneLinePointEndX(i), laneLineInfo.getLaneLinePointEndY(i), laneLineInfo.getLaneLinePointEndZ(i)), laneLineInfo.getLaneLineChange(i), SensService.this.getEnConfidence(laneLineInfo.getLaneLineConfidence(i))));
                    }
                    Log.i(ServiceBase.INSTANCE.getTAG(), "setNaviVisionLaneLine");
                    OneCoreSenRegister oneCoreSenRegister = SensService.this.register;
                    if (oneCoreSenRegister != null) {
                        oneCoreSenRegister.setNaviVisionLaneLine(visionLaneLineInfo, laneLineInfo.getTimeStamp());
                    }
                }
            }
        }, new IntentFilter("com.neusoft.arComponent.LaneInfo"));
    }

    @Override // android.app.Service
    public void onDestroy() {
        IOneCoreSenService.Stub stub = this.oneCoreSenstance;
        if (stub != null) {
            stub.stopSens();
        }
        Process.killProcess(Process.myPid());
        OneCoreSenRegister oneCoreSenRegister = this.register;
        if (oneCoreSenRegister != null) {
            oneCoreSenRegister.unregisterFiter();
        }
        super.onDestroy();
    }

    @Override // com.neusoft.na.common.ServiceBase, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        ServiceBase.INSTANCE.getLogger().debug("SensorService.onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    private final synchronized void init() {
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.INITIALIZING, null, 2, null));
        getPrivateTempDir().mkdirs();
        if (this.oneCoreSenstance == null) {
            this.oneCoreSenstance = new OneCoreSenServiceImp();
            OneCoreSenRegister oneCoreSenRegister = OneCoreSenRegister.getInstance();
            this.register = oneCoreSenRegister;
            if (oneCoreSenRegister != null) {
                oneCoreSenRegister.registerFiter(this, true);
            }
        }
        startOneCoreSens();
        updateApplicationStatus(new ApplicationStatus(ApplicationStatus.Status.RUNNING, null, 2, null));
        Tool.getSelfMaps("SENS");
    }

    @Override // com.neusoft.na.common.ServiceBase
    protected synchronized void onStart() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean startOneCoreSens() throws java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instruction units count: 628
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.neusoft.na.ocservice.SensService.startOneCoreSens():boolean");
    }

    private final void sendSensorData() {
        this.intent.setAction("location.reportsucc");
        OneCoreSenRegister oneCoreSenRegister = this.register;
        String strValueOf = String.valueOf(oneCoreSenRegister != null ? oneCoreSenRegister.getGnssString() : null);
        String str = strValueOf;
        if (str.length() > 0) {
            if (!(str == null || str.length() == 0)) {
                this.intent.putExtra("SENSORS_NMEA", "" + strValueOf);
                sendBroadcast(this.intent);
            }
        }
        OneCoreSenRegister oneCoreSenRegister2 = this.register;
        float[] naviAccelerSensor = oneCoreSenRegister2 != null ? oneCoreSenRegister2.getNaviAccelerSensor() : null;
        if (naviAccelerSensor != null && naviAccelerSensor.length == 3) {
            Float fValueOf = Float.valueOf(naviAccelerSensor[0]);
            Float fValueOf2 = Float.valueOf(naviAccelerSensor[1]);
            Float fValueOf3 = Float.valueOf(naviAccelerSensor[2]);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str2 = String.format("%.8f, %.8f, %.8f\n\n", Arrays.copyOf(new Object[]{fValueOf, fValueOf2, fValueOf3}, 3));
            Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
            this.intent.putExtra("SENSORS_ACC", "Acceler(x, y, z):\n" + str2);
            sendBroadcast(this.intent);
        }
        OneCoreSenRegister oneCoreSenRegister3 = this.register;
        float[] naviGyroSensor = oneCoreSenRegister3 != null ? oneCoreSenRegister3.getNaviGyroSensor() : null;
        if (naviGyroSensor != null && naviGyroSensor.length == 3) {
            Float fValueOf4 = Float.valueOf(naviGyroSensor[0]);
            Float fValueOf5 = Float.valueOf(naviGyroSensor[1]);
            Float fValueOf6 = Float.valueOf(naviGyroSensor[2]);
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String str3 = String.format("%.8f, %.8f, %.8f\n\n", Arrays.copyOf(new Object[]{fValueOf4, fValueOf5, fValueOf6}, 3));
            Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
            this.intent.putExtra("SENSORS_GYRO", "Gyro(x, y, z):\n" + str3);
            sendBroadcast(this.intent);
        }
        OneCoreSenRegister oneCoreSenRegister4 = this.register;
        float[] naviMagneticSensor = oneCoreSenRegister4 != null ? oneCoreSenRegister4.getNaviMagneticSensor() : null;
        if (naviMagneticSensor != null && naviMagneticSensor.length == 3) {
            Float fValueOf7 = Float.valueOf(naviMagneticSensor[0]);
            Float fValueOf8 = Float.valueOf(naviMagneticSensor[1]);
            Float fValueOf9 = Float.valueOf(naviMagneticSensor[2]);
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            String str4 = String.format("%.8f, %.8f, %.8f\n\n", Arrays.copyOf(new Object[]{fValueOf7, fValueOf8, fValueOf9}, 3));
            Intrinsics.checkNotNullExpressionValue(str4, "format(format, *args)");
            this.intent.putExtra("SENSORS_MAGENTIC", "Magnetic(x, y, z):\n" + str4);
            sendBroadcast(this.intent);
        }
        OneCoreSenRegister oneCoreSenRegister5 = this.register;
        float[] naviRotationVectorSensor = oneCoreSenRegister5 != null ? oneCoreSenRegister5.getNaviRotationVectorSensor() : null;
        if (naviRotationVectorSensor == null || naviRotationVectorSensor.length != 4) {
            return;
        }
        Float fValueOf10 = Float.valueOf(naviRotationVectorSensor[0]);
        Float fValueOf11 = Float.valueOf(naviRotationVectorSensor[1]);
        Float fValueOf12 = Float.valueOf(naviRotationVectorSensor[2]);
        Float fValueOf13 = Float.valueOf(naviRotationVectorSensor[3]);
        StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
        String str5 = String.format("%08f, %.8f, %.8f, %.8f\n\n", Arrays.copyOf(new Object[]{fValueOf10, fValueOf11, fValueOf12, fValueOf13}, 4));
        Intrinsics.checkNotNullExpressionValue(str5, "format(format, *args)");
        this.intent.putExtra("SENSORS_ROTATION_VECTOR", "RotationVector(v, x, y, z):\n" + str5);
        sendBroadcast(this.intent);
    }

    public final VisionLaneLineInfo.EnColor getEnColor(LaneLineInfo.EnColor color) {
        int i = color == null ? -1 : WhenMappings.$EnumSwitchMapping$0[color.ordinal()];
        if (i == 1) {
            return VisionLaneLineInfo.EnColor.COLOR_UNKNOWN;
        }
        if (i == 2) {
            return VisionLaneLineInfo.EnColor.COLOR_YELLOW;
        }
        if (i == 3) {
            return VisionLaneLineInfo.EnColor.COLOR_WHITE;
        }
        return VisionLaneLineInfo.EnColor.COLOR_UNKNOWN;
    }

    public final VisionLaneLineInfo.EnType getEnType(LaneLineInfo.EnType type) {
        switch (type == null ? -1 : WhenMappings.$EnumSwitchMapping$1[type.ordinal()]) {
            case 1:
                return VisionLaneLineInfo.EnType.TYPE_UNKNOWN;
            case 2:
                return VisionLaneLineInfo.EnType.TYPE_SOLID;
            case 3:
                return VisionLaneLineInfo.EnType.TYPE_DASHED;
            case 4:
                return VisionLaneLineInfo.EnType.TYPE_DOUBLE_LINE;
            case 5:
                return VisionLaneLineInfo.EnType.TYPE_DOUBLE_SOLID_DASHED;
            case 6:
                return VisionLaneLineInfo.EnType.TYPE_DOUBLE_DASHED_SOLID;
            case 7:
                return VisionLaneLineInfo.EnType.TYPE_DOUBLE_SOLID_SOLID;
            case 8:
                return VisionLaneLineInfo.EnType.TYPE_DOUBLE_DASHED_DASHED;
            case 9:
                return VisionLaneLineInfo.EnType.TYPE_DECELERATION;
            case 10:
                return VisionLaneLineInfo.EnType.TYPE_HOVLANE;
            case 11:
                return VisionLaneLineInfo.EnType.TYPE_LINE_ROAD_EDGE;
            case 12:
                return VisionLaneLineInfo.EnType.TYPE_LINE_CURB;
            case 13:
                return VisionLaneLineInfo.EnType.TYPE_LINE_CONESPOLES;
            case 14:
                return VisionLaneLineInfo.EnType.TYPE_BOTTSDOTS;
            case 15:
                return VisionLaneLineInfo.EnType.TYPE_BARRIER;
            case 16:
                return VisionLaneLineInfo.EnType.TYPE_ROADEDGE;
            default:
                return VisionLaneLineInfo.EnType.TYPE_UNKNOWN;
        }
    }

    public final VisionLaneLineInfo.EnConfidence getEnConfidence(LaneLineInfo.EnConfidence confidence) {
        switch (confidence == null ? -1 : WhenMappings.$EnumSwitchMapping$2[confidence.ordinal()]) {
        }
        return VisionLaneLineInfo.EnConfidence.CONFIDENCE_UNKNOWN;
    }
}
