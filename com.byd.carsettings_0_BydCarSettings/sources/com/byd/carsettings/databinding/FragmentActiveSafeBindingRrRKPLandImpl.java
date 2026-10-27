package com.byd.carsettings.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.byd.base.view.BydSwitch;
import com.byd.base.view.segmentedbutton.SegmentedButton;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.dipilot.model.ActiveSafeModel;
import com.byd.dipilot.model.ActiveSafeStateModel;
import com.byd.dipilot.model.DriverAssistModel;
import com.byd.dipilot.viewmodel.ActiveSafeViewModel;
import com.bydux.api.common.views.window.DampingScrollView;
import okio.SegmentPool;

/* JADX INFO: loaded from: classes2.dex */
public class FragmentActiveSafeBindingRrRKPLandImpl extends FragmentActiveSafeBinding {

    @Nullable
    public static final ViewDataBinding.IncludedLayouts B1 = null;

    @Nullable
    public static final SparseIntArray C1;

    @NonNull
    public final RelativeLayout D1;

    @NonNull
    public final LinearLayout E1;

    @NonNull
    public final TextView F1;

    @NonNull
    public final TextView G1;

    @NonNull
    public final TextView H1;

    @NonNull
    public final TextView I1;

    @NonNull
    public final ConstraintLayout J1;
    public long K1;
    public long L1;
    public long M1;
    public long N1;
    public long O1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        C1 = sparseIntArray;
        sparseIntArray.put(R.id.tv_auto_parke, 103);
        sparseIntArray.put(R.id.tv_epb, 104);
        sparseIntArray.put(R.id.tv_epb_tips, 105);
        sparseIntArray.put(R.id.lottie_animation_lane_assist_sys, 106);
        sparseIntArray.put(R.id.tv_lane_assist_alarm_mode, 107);
        sparseIntArray.put(R.id.saoc_title, 108);
        sparseIntArray.put(R.id.lottie_animation_collision_prevention_assist, 109);
        sparseIntArray.put(R.id.tv_title_ai_emergency_brake, 110);
        sparseIntArray.put(R.id.tv_rear_crash_alert, 111);
        sparseIntArray.put(R.id.tv_predict_crash_alert, 112);
        sparseIntArray.put(R.id.cl_lateral_approach_assist, 113);
        sparseIntArray.put(R.id.lottie_animation_lateral_assist, 114);
        sparseIntArray.put(R.id.flow_front_warn, 115);
        sparseIntArray.put(R.id.tv_front_lateral_approach_warning, 116);
        sparseIntArray.put(R.id.tv_front_lateral_approach_braking, 117);
        sparseIntArray.put(R.id.tv_rear_lateral_approach_warning, 118);
        sparseIntArray.put(R.id.tv_rear_lateral_approach_braking, 119);
        sparseIntArray.put(R.id.tv_title_traffic_signs_recognition, 120);
        sparseIntArray.put(R.id.lottie_animation_traffic_signs_recogniation, 121);
        sparseIntArray.put(R.id.flow_traffic_signal, 122);
        sparseIntArray.put(R.id.slif_title, 123);
        sparseIntArray.put(R.id.tv_title_traffic_sound_remind_recognition, 124);
        sparseIntArray.put(R.id.tv_intelligent_speed_control_alarm, 125);
        sparseIntArray.put(R.id.tv_intelligent_speed_control, 126);
        sparseIntArray.put(R.id.tv_tsr_flow_title, 127);
        sparseIntArray.put(R.id.tv_limit_ctrl_type, 128);
        sparseIntArray.put(R.id.tv_speed_control_alarm_sensitivity, 129);
        sparseIntArray.put(R.id.tv_bsis, 130);
        sparseIntArray.put(R.id.tv_mois, 131);
        sparseIntArray.put(R.id.tv_title_gas_pedal, 132);
    }

    public FragmentActiveSafeBindingRrRKPLandImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 133, B1, C1));
    }

    public final boolean A(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 131072;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.L1 |= 512;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.L1 |= 1024;
            }
            return true;
        }
        if (i != 3) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 2048;
        }
        return true;
    }

    public final boolean B(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 67108864;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.L1 |= 8589934592L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.L1 |= 17179869184L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 34359738368L;
        }
        return true;
    }

    public final boolean C(ActiveSafeStateModel activeSafeStateModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 256;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 8796093022208L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 17592186044416L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 35184372088832L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 70368744177664L;
        }
        return true;
    }

    public final boolean D(MutableLiveData<Byte> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 1;
        }
        return true;
    }

    @Override // com.byd.carsettings.databinding.FragmentActiveSafeBinding
    public void c(@Nullable ActiveSafeViewModel activeSafeViewModel) {
        this.A1 = activeSafeViewModel;
        synchronized (this) {
            this.K1 |= 268435456;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    public final boolean d(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 = 8388608 | this.K1;
            }
            return true;
        }
        if (i != 9) {
            return false;
        }
        synchronized (this) {
            this.L1 = 8388608 | this.L1;
        }
        return true;
    }

    public final boolean e(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 4096;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 1152921504606846976L;
            }
            return true;
        }
        if (i == 10) {
            synchronized (this) {
                this.K1 |= 2305843009213693952L;
            }
            return true;
        }
        if (i != 1) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 4611686018427387904L;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:1025:0x0fff  */
    /* JADX WARN: Removed duplicated region for block: B:1026:0x1001  */
    /* JADX WARN: Removed duplicated region for block: B:1027:0x100e  */
    /* JADX WARN: Removed duplicated region for block: B:1030:0x101a  */
    /* JADX WARN: Removed duplicated region for block: B:1031:0x1021  */
    /* JADX WARN: Removed duplicated region for block: B:1036:0x1037  */
    /* JADX WARN: Removed duplicated region for block: B:1037:0x1039  */
    /* JADX WARN: Removed duplicated region for block: B:1038:0x103c  */
    /* JADX WARN: Removed duplicated region for block: B:1043:0x104a  */
    /* JADX WARN: Removed duplicated region for block: B:1044:0x104c  */
    /* JADX WARN: Removed duplicated region for block: B:1045:0x104f  */
    /* JADX WARN: Removed duplicated region for block: B:1047:0x1058  */
    /* JADX WARN: Removed duplicated region for block: B:1051:0x1064  */
    /* JADX WARN: Removed duplicated region for block: B:1053:0x1068  */
    /* JADX WARN: Removed duplicated region for block: B:1054:0x106b  */
    /* JADX WARN: Removed duplicated region for block: B:1061:0x108e  */
    /* JADX WARN: Removed duplicated region for block: B:1062:0x1090  */
    /* JADX WARN: Removed duplicated region for block: B:1063:0x109d  */
    /* JADX WARN: Removed duplicated region for block: B:1070:0x10b9  */
    /* JADX WARN: Removed duplicated region for block: B:1071:0x10bb  */
    /* JADX WARN: Removed duplicated region for block: B:1072:0x10c0  */
    /* JADX WARN: Removed duplicated region for block: B:1079:0x10dd  */
    /* JADX WARN: Removed duplicated region for block: B:1080:0x10df  */
    /* JADX WARN: Removed duplicated region for block: B:1081:0x10e4  */
    /* JADX WARN: Removed duplicated region for block: B:1086:0x10f7  */
    /* JADX WARN: Removed duplicated region for block: B:1087:0x10f9  */
    /* JADX WARN: Removed duplicated region for block: B:1088:0x10ff  */
    /* JADX WARN: Removed duplicated region for block: B:1091:0x1108  */
    /* JADX WARN: Removed duplicated region for block: B:1092:0x110b  */
    /* JADX WARN: Removed duplicated region for block: B:1102:0x112f  */
    /* JADX WARN: Removed duplicated region for block: B:1105:0x1138 A[PHI: r8
  0x1138: PHI (r8v59 long) = (r8v58 long), (r8v56 long) binds: [B:1117:0x1154, B:1103:0x1135] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1106:0x113a  */
    /* JADX WARN: Removed duplicated region for block: B:1108:0x113e  */
    /* JADX WARN: Removed duplicated region for block: B:1109:0x1140  */
    /* JADX WARN: Removed duplicated region for block: B:1111:0x1143  */
    /* JADX WARN: Removed duplicated region for block: B:1113:0x1149  */
    /* JADX WARN: Removed duplicated region for block: B:1114:0x114b  */
    /* JADX WARN: Removed duplicated region for block: B:1115:0x114f  */
    /* JADX WARN: Removed duplicated region for block: B:1118:0x1156  */
    /* JADX WARN: Removed duplicated region for block: B:1126:0x1178  */
    /* JADX WARN: Removed duplicated region for block: B:1127:0x117a  */
    /* JADX WARN: Removed duplicated region for block: B:1128:0x1187  */
    /* JADX WARN: Removed duplicated region for block: B:1131:0x1193  */
    /* JADX WARN: Removed duplicated region for block: B:1134:0x119c A[PHI: r8
  0x119c: PHI (r8v64 long) = (r8v62 long), (r8v60 long) binds: [B:1146:0x11b6, B:1132:0x1199] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1135:0x119e  */
    /* JADX WARN: Removed duplicated region for block: B:1137:0x11a2  */
    /* JADX WARN: Removed duplicated region for block: B:1138:0x11a4  */
    /* JADX WARN: Removed duplicated region for block: B:1142:0x11ad  */
    /* JADX WARN: Removed duplicated region for block: B:1143:0x11af  */
    /* JADX WARN: Removed duplicated region for block: B:1144:0x11b2  */
    /* JADX WARN: Removed duplicated region for block: B:1148:0x11b9  */
    /* JADX WARN: Removed duplicated region for block: B:1150:0x11bd  */
    /* JADX WARN: Removed duplicated region for block: B:1153:0x11cc  */
    /* JADX WARN: Removed duplicated region for block: B:1157:0x11e6  */
    /* JADX WARN: Removed duplicated region for block: B:1158:0x11e8  */
    /* JADX WARN: Removed duplicated region for block: B:1159:0x11f5  */
    /* JADX WARN: Removed duplicated region for block: B:1166:0x1213  */
    /* JADX WARN: Removed duplicated region for block: B:1167:0x1215  */
    /* JADX WARN: Removed duplicated region for block: B:1168:0x121a  */
    /* JADX WARN: Removed duplicated region for block: B:1172:0x122a  */
    /* JADX WARN: Removed duplicated region for block: B:1173:0x122c  */
    /* JADX WARN: Removed duplicated region for block: B:1174:0x1232  */
    /* JADX WARN: Removed duplicated region for block: B:1177:0x123b  */
    /* JADX WARN: Removed duplicated region for block: B:1178:0x123e  */
    /* JADX WARN: Removed duplicated region for block: B:1186:0x125a  */
    /* JADX WARN: Removed duplicated region for block: B:1189:0x1263 A[PHI: r8
  0x1263: PHI (r8v71 long) = (r8v70 long), (r8v68 long) binds: [B:1201:0x1280, B:1187:0x1260] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1190:0x1266  */
    /* JADX WARN: Removed duplicated region for block: B:1192:0x126a  */
    /* JADX WARN: Removed duplicated region for block: B:1193:0x126d  */
    /* JADX WARN: Removed duplicated region for block: B:1195:0x1271  */
    /* JADX WARN: Removed duplicated region for block: B:1197:0x1277  */
    /* JADX WARN: Removed duplicated region for block: B:1198:0x1279  */
    /* JADX WARN: Removed duplicated region for block: B:1199:0x127c  */
    /* JADX WARN: Removed duplicated region for block: B:1203:0x1283  */
    /* JADX WARN: Removed duplicated region for block: B:1210:0x12a3  */
    /* JADX WARN: Removed duplicated region for block: B:1211:0x12a5  */
    /* JADX WARN: Removed duplicated region for block: B:1212:0x12b2  */
    /* JADX WARN: Removed duplicated region for block: B:1215:0x12be  */
    /* JADX WARN: Removed duplicated region for block: B:1218:0x12c7 A[PHI: r8
  0x12c7: PHI (r8v77 long) = (r8v75 long), (r8v72 long) binds: [B:1229:0x12e4, B:1216:0x12c4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1219:0x12c9  */
    /* JADX WARN: Removed duplicated region for block: B:1221:0x12cd  */
    /* JADX WARN: Removed duplicated region for block: B:1222:0x12cf  */
    /* JADX WARN: Removed duplicated region for block: B:1226:0x12d8  */
    /* JADX WARN: Removed duplicated region for block: B:1227:0x12da  */
    /* JADX WARN: Removed duplicated region for block: B:1228:0x12e2  */
    /* JADX WARN: Removed duplicated region for block: B:1231:0x12e7  */
    /* JADX WARN: Removed duplicated region for block: B:1233:0x12eb  */
    /* JADX WARN: Removed duplicated region for block: B:1234:0x12f0  */
    /* JADX WARN: Removed duplicated region for block: B:1241:0x1317  */
    /* JADX WARN: Removed duplicated region for block: B:1242:0x1319  */
    /* JADX WARN: Removed duplicated region for block: B:1243:0x1326  */
    /* JADX WARN: Removed duplicated region for block: B:1249:0x1342 A[PHI: r8
  0x1342: PHI (r8v89 long) = (r8v80 long), (r8v78 long) binds: [B:1260:0x135f, B:1247:0x133f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1250:0x1345  */
    /* JADX WARN: Removed duplicated region for block: B:1251:0x1347  */
    /* JADX WARN: Removed duplicated region for block: B:1252:0x134a  */
    /* JADX WARN: Removed duplicated region for block: B:1256:0x1354  */
    /* JADX WARN: Removed duplicated region for block: B:1257:0x1356  */
    /* JADX WARN: Removed duplicated region for block: B:1258:0x135a  */
    /* JADX WARN: Removed duplicated region for block: B:1262:0x1362  */
    /* JADX WARN: Removed duplicated region for block: B:1268:0x1377  */
    /* JADX WARN: Removed duplicated region for block: B:1269:0x1379  */
    /* JADX WARN: Removed duplicated region for block: B:1270:0x137e  */
    /* JADX WARN: Removed duplicated region for block: B:1275:0x1391  */
    /* JADX WARN: Removed duplicated region for block: B:1276:0x1393  */
    /* JADX WARN: Removed duplicated region for block: B:1277:0x1396  */
    /* JADX WARN: Removed duplicated region for block: B:1279:0x139f  */
    /* JADX WARN: Removed duplicated region for block: B:1280:0x13a2  */
    /* JADX WARN: Removed duplicated region for block: B:1286:0x13b7  */
    /* JADX WARN: Removed duplicated region for block: B:1287:0x13b9  */
    /* JADX WARN: Removed duplicated region for block: B:1288:0x13be  */
    /* JADX WARN: Removed duplicated region for block: B:1293:0x13d1  */
    /* JADX WARN: Removed duplicated region for block: B:1294:0x13d3  */
    /* JADX WARN: Removed duplicated region for block: B:1295:0x13d6  */
    /* JADX WARN: Removed duplicated region for block: B:1301:0x13eb  */
    /* JADX WARN: Removed duplicated region for block: B:1302:0x13ed  */
    /* JADX WARN: Removed duplicated region for block: B:1303:0x13f2  */
    /* JADX WARN: Removed duplicated region for block: B:1313:0x1420  */
    /* JADX WARN: Removed duplicated region for block: B:1317:0x143a  */
    /* JADX WARN: Removed duplicated region for block: B:1318:0x143c  */
    /* JADX WARN: Removed duplicated region for block: B:1319:0x1449  */
    /* JADX WARN: Removed duplicated region for block: B:1325:0x1465 A[PHI: r10
  0x1465: PHI (r10v57 long) = (r10v50 long), (r10v47 long) binds: [B:1335:0x1483, B:1323:0x1462] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1326:0x1468  */
    /* JADX WARN: Removed duplicated region for block: B:1327:0x146a  */
    /* JADX WARN: Removed duplicated region for block: B:1328:0x146d  */
    /* JADX WARN: Removed duplicated region for block: B:1332:0x1477  */
    /* JADX WARN: Removed duplicated region for block: B:1333:0x1479  */
    /* JADX WARN: Removed duplicated region for block: B:1334:0x1481  */
    /* JADX WARN: Removed duplicated region for block: B:1337:0x1486  */
    /* JADX WARN: Removed duplicated region for block: B:1343:0x149b  */
    /* JADX WARN: Removed duplicated region for block: B:1344:0x149d  */
    /* JADX WARN: Removed duplicated region for block: B:1345:0x14a2  */
    /* JADX WARN: Removed duplicated region for block: B:1350:0x14b5  */
    /* JADX WARN: Removed duplicated region for block: B:1351:0x14b7  */
    /* JADX WARN: Removed duplicated region for block: B:1352:0x14ba  */
    /* JADX WARN: Removed duplicated region for block: B:1355:0x14c0  */
    /* JADX WARN: Removed duplicated region for block: B:1356:0x14c3  */
    /* JADX WARN: Removed duplicated region for block: B:1364:0x14de  */
    /* JADX WARN: Removed duplicated region for block: B:1368:0x14ef  */
    /* JADX WARN: Removed duplicated region for block: B:1369:0x14f1  */
    /* JADX WARN: Removed duplicated region for block: B:1370:0x14f6  */
    /* JADX WARN: Removed duplicated region for block: B:1375:0x1509  */
    /* JADX WARN: Removed duplicated region for block: B:1376:0x150b  */
    /* JADX WARN: Removed duplicated region for block: B:1377:0x150e  */
    /* JADX WARN: Removed duplicated region for block: B:1384:0x1524  */
    /* JADX WARN: Removed duplicated region for block: B:1385:0x1526  */
    /* JADX WARN: Removed duplicated region for block: B:1386:0x152b  */
    /* JADX WARN: Removed duplicated region for block: B:1394:0x154e  */
    /* JADX WARN: Removed duplicated region for block: B:1395:0x1550  */
    /* JADX WARN: Removed duplicated region for block: B:1396:0x155d  */
    /* JADX WARN: Removed duplicated region for block: B:1399:0x1569  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:1400:0x1570  */
    /* JADX WARN: Removed duplicated region for block: B:1402:0x1573  */
    /* JADX WARN: Removed duplicated region for block: B:1403:0x1575  */
    /* JADX WARN: Removed duplicated region for block: B:1408:0x1584  */
    /* JADX WARN: Removed duplicated region for block: B:1409:0x1586  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:1410:0x1589  */
    /* JADX WARN: Removed duplicated region for block: B:1413:0x158f  */
    /* JADX WARN: Removed duplicated region for block: B:1414:0x1591  */
    /* JADX WARN: Removed duplicated region for block: B:1417:0x1596  */
    /* JADX WARN: Removed duplicated region for block: B:1418:0x1598  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:1425:0x15c0  */
    /* JADX WARN: Removed duplicated region for block: B:1426:0x15c2  */
    /* JADX WARN: Removed duplicated region for block: B:1427:0x15c9  */
    /* JADX WARN: Removed duplicated region for block: B:1434:0x15eb  */
    /* JADX WARN: Removed duplicated region for block: B:1435:0x15ed  */
    /* JADX WARN: Removed duplicated region for block: B:1436:0x15f2  */
    /* JADX WARN: Removed duplicated region for block: B:1443:0x1611  */
    /* JADX WARN: Removed duplicated region for block: B:1444:0x1613  */
    /* JADX WARN: Removed duplicated region for block: B:1445:0x1618  */
    /* JADX WARN: Removed duplicated region for block: B:1450:0x162b  */
    /* JADX WARN: Removed duplicated region for block: B:1451:0x162d  */
    /* JADX WARN: Removed duplicated region for block: B:1452:0x1631  */
    /* JADX WARN: Removed duplicated region for block: B:1455:0x1638  */
    /* JADX WARN: Removed duplicated region for block: B:1456:0x163b  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0351  */
    /* JADX WARN: Removed duplicated region for block: B:1462:0x1650  */
    /* JADX WARN: Removed duplicated region for block: B:1463:0x1652  */
    /* JADX WARN: Removed duplicated region for block: B:1464:0x1657  */
    /* JADX WARN: Removed duplicated region for block: B:1469:0x166a  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:1470:0x166c  */
    /* JADX WARN: Removed duplicated region for block: B:1471:0x166f  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0361  */
    /* JADX WARN: Removed duplicated region for block: B:1481:0x169e  */
    /* JADX WARN: Removed duplicated region for block: B:1485:0x16a8  */
    /* JADX WARN: Removed duplicated region for block: B:1486:0x16aa  */
    /* JADX WARN: Removed duplicated region for block: B:1487:0x16ac  */
    /* JADX WARN: Removed duplicated region for block: B:1491:0x16b5  */
    /* JADX WARN: Removed duplicated region for block: B:1492:0x16b7  */
    /* JADX WARN: Removed duplicated region for block: B:1493:0x16bf  */
    /* JADX WARN: Removed duplicated region for block: B:1497:0x16cb  */
    /* JADX WARN: Removed duplicated region for block: B:1498:0x16cd  */
    /* JADX WARN: Removed duplicated region for block: B:1499:0x16d0  */
    /* JADX WARN: Removed duplicated region for block: B:1502:0x16d6  */
    /* JADX WARN: Removed duplicated region for block: B:1505:0x16df A[PHI: r16
  0x16df: PHI (r16v139 long) = (r16v138 long), (r16v140 long) binds: [B:1507:0x16e3, B:1503:0x16dc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1506:0x16e1  */
    /* JADX WARN: Removed duplicated region for block: B:1509:0x16e6  */
    /* JADX WARN: Removed duplicated region for block: B:1516:0x1717  */
    /* JADX WARN: Removed duplicated region for block: B:1517:0x1719  */
    /* JADX WARN: Removed duplicated region for block: B:1518:0x1726  */
    /* JADX WARN: Removed duplicated region for block: B:1525:0x1749  */
    /* JADX WARN: Removed duplicated region for block: B:1526:0x174b  */
    /* JADX WARN: Removed duplicated region for block: B:1527:0x1757  */
    /* JADX WARN: Removed duplicated region for block: B:1529:0x175d  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:1530:0x1760  */
    /* JADX WARN: Removed duplicated region for block: B:1532:0x1764  */
    /* JADX WARN: Removed duplicated region for block: B:1533:0x1766  */
    /* JADX WARN: Removed duplicated region for block: B:1537:0x1771  */
    /* JADX WARN: Removed duplicated region for block: B:1538:0x1773  */
    /* JADX WARN: Removed duplicated region for block: B:1539:0x1777  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:1544:0x1786  */
    /* JADX WARN: Removed duplicated region for block: B:1545:0x1788  */
    /* JADX WARN: Removed duplicated region for block: B:1546:0x178b  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:1555:0x17af  */
    /* JADX WARN: Removed duplicated region for block: B:1559:0x17be  */
    /* JADX WARN: Removed duplicated region for block: B:1560:0x17c0  */
    /* JADX WARN: Removed duplicated region for block: B:1561:0x17c5  */
    /* JADX WARN: Removed duplicated region for block: B:1566:0x17d8  */
    /* JADX WARN: Removed duplicated region for block: B:1567:0x17da  */
    /* JADX WARN: Removed duplicated region for block: B:1568:0x17dd  */
    /* JADX WARN: Removed duplicated region for block: B:1575:0x17f4  */
    /* JADX WARN: Removed duplicated region for block: B:1576:0x17f6  */
    /* JADX WARN: Removed duplicated region for block: B:1577:0x17fb  */
    /* JADX WARN: Removed duplicated region for block: B:1582:0x180e  */
    /* JADX WARN: Removed duplicated region for block: B:1583:0x1810  */
    /* JADX WARN: Removed duplicated region for block: B:1584:0x1813  */
    /* JADX WARN: Removed duplicated region for block: B:1587:0x1819  */
    /* JADX WARN: Removed duplicated region for block: B:1588:0x181c  */
    /* JADX WARN: Removed duplicated region for block: B:1595:0x1833  */
    /* JADX WARN: Removed duplicated region for block: B:1596:0x1835  */
    /* JADX WARN: Removed duplicated region for block: B:1597:0x1838  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x038d  */
    /* JADX WARN: Removed duplicated region for block: B:1601:0x1842  */
    /* JADX WARN: Removed duplicated region for block: B:1602:0x1844  */
    /* JADX WARN: Removed duplicated region for block: B:1603:0x1847  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x038f  */
    /* JADX WARN: Removed duplicated region for block: B:1610:0x1860  */
    /* JADX WARN: Removed duplicated region for block: B:1611:0x1862  */
    /* JADX WARN: Removed duplicated region for block: B:1612:0x1867  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0394  */
    /* JADX WARN: Removed duplicated region for block: B:1621:0x188e  */
    /* JADX WARN: Removed duplicated region for block: B:1624:0x1893  */
    /* JADX WARN: Removed duplicated region for block: B:1627:0x189b  */
    /* JADX WARN: Removed duplicated region for block: B:1634:0x197a  */
    /* JADX WARN: Removed duplicated region for block: B:1635:0x197c  */
    /* JADX WARN: Removed duplicated region for block: B:1636:0x1989  */
    /* JADX WARN: Removed duplicated region for block: B:1639:0x1995  */
    /* JADX WARN: Removed duplicated region for block: B:1640:0x1997  */
    /* JADX WARN: Removed duplicated region for block: B:1644:0x19a2  */
    /* JADX WARN: Removed duplicated region for block: B:1645:0x19a4  */
    /* JADX WARN: Removed duplicated region for block: B:1646:0x19aa  */
    /* JADX WARN: Removed duplicated region for block: B:1649:0x19b3  */
    /* JADX WARN: Removed duplicated region for block: B:1650:0x19b5  */
    /* JADX WARN: Removed duplicated region for block: B:1654:0x1a91  */
    /* JADX WARN: Removed duplicated region for block: B:1658:0x1a9b  */
    /* JADX WARN: Removed duplicated region for block: B:1659:0x1a9d  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:1660:0x1aa0  */
    /* JADX WARN: Removed duplicated region for block: B:1664:0x1aaa  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:1670:0x1ac6  */
    /* JADX WARN: Removed duplicated region for block: B:1674:0x1ad8  */
    /* JADX WARN: Removed duplicated region for block: B:1678:0x1ae3  */
    /* JADX WARN: Removed duplicated region for block: B:1679:0x1ae5  */
    /* JADX WARN: Removed duplicated region for block: B:1680:0x1ae8  */
    /* JADX WARN: Removed duplicated region for block: B:1684:0x1af4  */
    /* JADX WARN: Removed duplicated region for block: B:1698:0x1b26  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:1701:0x1b37  */
    /* JADX WARN: Removed duplicated region for block: B:1705:0x1b47  */
    /* JADX WARN: Removed duplicated region for block: B:1706:0x1b49  */
    /* JADX WARN: Removed duplicated region for block: B:1707:0x1b4c  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:1711:0x1b5a  */
    /* JADX WARN: Removed duplicated region for block: B:1717:0x1b76  */
    /* JADX WARN: Removed duplicated region for block: B:1721:0x1b83  */
    /* JADX WARN: Removed duplicated region for block: B:1726:0x1b98  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03de  */
    /* JADX WARN: Removed duplicated region for block: B:1730:0x1ba5  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:1744:0x1bdb  */
    /* JADX WARN: Removed duplicated region for block: B:1747:0x1be7  */
    /* JADX WARN: Removed duplicated region for block: B:1752:0x1bf8  */
    /* JADX WARN: Removed duplicated region for block: B:1755:0x1c06  */
    /* JADX WARN: Removed duplicated region for block: B:1759:0x1c11  */
    /* JADX WARN: Removed duplicated region for block: B:1760:0x1c13  */
    /* JADX WARN: Removed duplicated region for block: B:1761:0x1c16  */
    /* JADX WARN: Removed duplicated region for block: B:1765:0x1c22  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x03ef  */
    /* JADX WARN: Removed duplicated region for block: B:1771:0x1c3f  */
    /* JADX WARN: Removed duplicated region for block: B:1775:0x1c4f  */
    /* JADX WARN: Removed duplicated region for block: B:1779:0x1c5f  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03f2  */
    /* JADX WARN: Removed duplicated region for block: B:1780:0x1c61  */
    /* JADX WARN: Removed duplicated region for block: B:1781:0x1c64  */
    /* JADX WARN: Removed duplicated region for block: B:1785:0x1c75  */
    /* JADX WARN: Removed duplicated region for block: B:1789:0x1c7f  */
    /* JADX WARN: Removed duplicated region for block: B:1790:0x1c81  */
    /* JADX WARN: Removed duplicated region for block: B:1791:0x1c84  */
    /* JADX WARN: Removed duplicated region for block: B:1795:0x1c90  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03f6  */
    /* JADX WARN: Removed duplicated region for block: B:1806:0x1cc0  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:1810:0x1ccf  */
    /* JADX WARN: Removed duplicated region for block: B:1814:0x1cdf  */
    /* JADX WARN: Removed duplicated region for block: B:1815:0x1ce1  */
    /* JADX WARN: Removed duplicated region for block: B:1816:0x1ce4  */
    /* JADX WARN: Removed duplicated region for block: B:1820:0x1cf2  */
    /* JADX WARN: Removed duplicated region for block: B:1825:0x1d07  */
    /* JADX WARN: Removed duplicated region for block: B:1829:0x1d16  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:1842:0x1d47  */
    /* JADX WARN: Removed duplicated region for block: B:1845:0x1d53  */
    /* JADX WARN: Removed duplicated region for block: B:1848:0x1d5a  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:1851:0x1d64  */
    /* JADX WARN: Removed duplicated region for block: B:1855:0x1d74  */
    /* JADX WARN: Removed duplicated region for block: B:1856:0x1d76  */
    /* JADX WARN: Removed duplicated region for block: B:1857:0x1d79  */
    /* JADX WARN: Removed duplicated region for block: B:1861:0x1d8a  */
    /* JADX WARN: Removed duplicated region for block: B:1865:0x1d95  */
    /* JADX WARN: Removed duplicated region for block: B:1866:0x1d97  */
    /* JADX WARN: Removed duplicated region for block: B:1867:0x1d9a  */
    /* JADX WARN: Removed duplicated region for block: B:1871:0x1da9  */
    /* JADX WARN: Removed duplicated region for block: B:1875:0x1db8  */
    /* JADX WARN: Removed duplicated region for block: B:1876:0x1dba  */
    /* JADX WARN: Removed duplicated region for block: B:1877:0x1dbd  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0416  */
    /* JADX WARN: Removed duplicated region for block: B:1881:0x1dca  */
    /* JADX WARN: Removed duplicated region for block: B:1886:0x1de2  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0419  */
    /* JADX WARN: Removed duplicated region for block: B:1890:0x1df4  */
    /* JADX WARN: Removed duplicated region for block: B:1894:0x1dff  */
    /* JADX WARN: Removed duplicated region for block: B:1895:0x1e01  */
    /* JADX WARN: Removed duplicated region for block: B:1899:0x1e0b  */
    /* JADX WARN: Removed duplicated region for block: B:1900:0x1e0d  */
    /* JADX WARN: Removed duplicated region for block: B:1901:0x1e13  */
    /* JADX WARN: Removed duplicated region for block: B:1904:0x1e1c  */
    /* JADX WARN: Removed duplicated region for block: B:1905:0x1e1f  */
    /* JADX WARN: Removed duplicated region for block: B:1909:0x1e34  */
    /* JADX WARN: Removed duplicated region for block: B:1913:0x1e3e  */
    /* JADX WARN: Removed duplicated region for block: B:1915:0x1e41  */
    /* JADX WARN: Removed duplicated region for block: B:1919:0x1e4b  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:1920:0x1e4d  */
    /* JADX WARN: Removed duplicated region for block: B:1921:0x1e50  */
    /* JADX WARN: Removed duplicated region for block: B:1925:0x1e61  */
    /* JADX WARN: Removed duplicated region for block: B:1929:0x1e70  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:1930:0x1e72  */
    /* JADX WARN: Removed duplicated region for block: B:1931:0x1e75  */
    /* JADX WARN: Removed duplicated region for block: B:1935:0x1e84  */
    /* JADX WARN: Removed duplicated region for block: B:1936:0x1e86  */
    /* JADX WARN: Removed duplicated region for block: B:1937:0x1e89  */
    /* JADX WARN: Removed duplicated region for block: B:1940:0x1e98  */
    /* JADX WARN: Removed duplicated region for block: B:1944:0x1ea2  */
    /* JADX WARN: Removed duplicated region for block: B:1946:0x1ea5  */
    /* JADX WARN: Removed duplicated region for block: B:1950:0x1eaf  */
    /* JADX WARN: Removed duplicated region for block: B:1951:0x1eb1  */
    /* JADX WARN: Removed duplicated region for block: B:1952:0x1eb7  */
    /* JADX WARN: Removed duplicated region for block: B:1956:0x1ec9  */
    /* JADX WARN: Removed duplicated region for block: B:1960:0x1ed4  */
    /* JADX WARN: Removed duplicated region for block: B:1961:0x1ed6  */
    /* JADX WARN: Removed duplicated region for block: B:1965:0x1ee0  */
    /* JADX WARN: Removed duplicated region for block: B:1966:0x1ee2  */
    /* JADX WARN: Removed duplicated region for block: B:1967:0x1ee6  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x042c  */
    /* JADX WARN: Removed duplicated region for block: B:1970:0x1eed  */
    /* JADX WARN: Removed duplicated region for block: B:1971:0x1ef0  */
    /* JADX WARN: Removed duplicated region for block: B:1975:0x1f01  */
    /* JADX WARN: Removed duplicated region for block: B:1979:0x1f0c  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:1980:0x1f0e  */
    /* JADX WARN: Removed duplicated region for block: B:1984:0x1f18  */
    /* JADX WARN: Removed duplicated region for block: B:1985:0x1f1a  */
    /* JADX WARN: Removed duplicated region for block: B:1986:0x1f1e  */
    /* JADX WARN: Removed duplicated region for block: B:1989:0x1f25  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:1990:0x1f28  */
    /* JADX WARN: Removed duplicated region for block: B:1994:0x1f39  */
    /* JADX WARN: Removed duplicated region for block: B:1998:0x1f47  */
    /* JADX WARN: Removed duplicated region for block: B:2000:0x1f4a  */
    /* JADX WARN: Removed duplicated region for block: B:2002:0x1f4e  */
    /* JADX WARN: Removed duplicated region for block: B:2003:0x1f60  */
    /* JADX WARN: Removed duplicated region for block: B:2007:0x1f70  */
    /* JADX WARN: Removed duplicated region for block: B:2008:0x1f72  */
    /* JADX WARN: Removed duplicated region for block: B:2009:0x1f78  */
    /* JADX WARN: Removed duplicated region for block: B:2012:0x1f81  */
    /* JADX WARN: Removed duplicated region for block: B:2013:0x1f83  */
    /* JADX WARN: Removed duplicated region for block: B:2016:0x1f92  */
    /* JADX WARN: Removed duplicated region for block: B:2020:0x1fa0  */
    /* JADX WARN: Removed duplicated region for block: B:2022:0x1fa3  */
    /* JADX WARN: Removed duplicated region for block: B:2026:0x1fad  */
    /* JADX WARN: Removed duplicated region for block: B:2027:0x1faf  */
    /* JADX WARN: Removed duplicated region for block: B:2028:0x1fb2  */
    /* JADX WARN: Removed duplicated region for block: B:2030:0x1fbb  */
    /* JADX WARN: Removed duplicated region for block: B:2031:0x1fbe  */
    /* JADX WARN: Removed duplicated region for block: B:2035:0x1fd3  */
    /* JADX WARN: Removed duplicated region for block: B:2039:0x1fe3  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0441  */
    /* JADX WARN: Removed duplicated region for block: B:2041:0x1fe6  */
    /* JADX WARN: Removed duplicated region for block: B:2044:0x1fee  */
    /* JADX WARN: Removed duplicated region for block: B:2048:0x2001  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:2051:0x2006  */
    /* JADX WARN: Removed duplicated region for block: B:2053:0x200a  */
    /* JADX WARN: Removed duplicated region for block: B:2055:0x2015  */
    /* JADX WARN: Removed duplicated region for block: B:2056:0x2017  */
    /* JADX WARN: Removed duplicated region for block: B:2057:0x201a  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:2060:0x2020  */
    /* JADX WARN: Removed duplicated region for block: B:2061:0x2023  */
    /* JADX WARN: Removed duplicated region for block: B:2065:0x2038  */
    /* JADX WARN: Removed duplicated region for block: B:2069:0x2042  */
    /* JADX WARN: Removed duplicated region for block: B:2071:0x2045  */
    /* JADX WARN: Removed duplicated region for block: B:2075:0x204f  */
    /* JADX WARN: Removed duplicated region for block: B:2076:0x2051  */
    /* JADX WARN: Removed duplicated region for block: B:2077:0x2054  */
    /* JADX WARN: Removed duplicated region for block: B:2081:0x2065  */
    /* JADX WARN: Removed duplicated region for block: B:2085:0x206f  */
    /* JADX WARN: Removed duplicated region for block: B:2087:0x2072  */
    /* JADX WARN: Removed duplicated region for block: B:2091:0x207c  */
    /* JADX WARN: Removed duplicated region for block: B:2092:0x207e  */
    /* JADX WARN: Removed duplicated region for block: B:2093:0x2081  */
    /* JADX WARN: Removed duplicated region for block: B:2097:0x208f  */
    /* JADX WARN: Removed duplicated region for block: B:2103:0x20aa  */
    /* JADX WARN: Removed duplicated region for block: B:2104:0x20ac  */
    /* JADX WARN: Removed duplicated region for block: B:2107:0x20bd  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:2110:0x20cb  */
    /* JADX WARN: Removed duplicated region for block: B:2112:0x20cf  */
    /* JADX WARN: Removed duplicated region for block: B:2117:0x20e4  */
    /* JADX WARN: Removed duplicated region for block: B:2118:0x20e6  */
    /* JADX WARN: Removed duplicated region for block: B:2119:0x20e9  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:2122:0x20f5 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2126:0x2101  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:2130:0x210a  */
    /* JADX WARN: Removed duplicated region for block: B:2131:0x210c  */
    /* JADX WARN: Removed duplicated region for block: B:2132:0x210e  */
    /* JADX WARN: Removed duplicated region for block: B:2136:0x2117  */
    /* JADX WARN: Removed duplicated region for block: B:2137:0x2119  */
    /* JADX WARN: Removed duplicated region for block: B:2138:0x211d  */
    /* JADX WARN: Removed duplicated region for block: B:2141:0x2124  */
    /* JADX WARN: Removed duplicated region for block: B:2145:0x2130  */
    /* JADX WARN: Removed duplicated region for block: B:2147:0x2134  */
    /* JADX WARN: Removed duplicated region for block: B:2148:0x2137  */
    /* JADX WARN: Removed duplicated region for block: B:2150:0x213b  */
    /* JADX WARN: Removed duplicated region for block: B:2152:0x2141  */
    /* JADX WARN: Removed duplicated region for block: B:2153:0x2143  */
    /* JADX WARN: Removed duplicated region for block: B:2154:0x2146  */
    /* JADX WARN: Removed duplicated region for block: B:2157:0x214c  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:2161:0x2158  */
    /* JADX WARN: Removed duplicated region for block: B:2163:0x215c  */
    /* JADX WARN: Removed duplicated region for block: B:2164:0x215f  */
    /* JADX WARN: Removed duplicated region for block: B:2166:0x2163  */
    /* JADX WARN: Removed duplicated region for block: B:2168:0x2169  */
    /* JADX WARN: Removed duplicated region for block: B:2169:0x216b  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:2170:0x216e  */
    /* JADX WARN: Removed duplicated region for block: B:2173:0x2174  */
    /* JADX WARN: Removed duplicated region for block: B:2177:0x2180  */
    /* JADX WARN: Removed duplicated region for block: B:2179:0x2184  */
    /* JADX WARN: Removed duplicated region for block: B:2180:0x2187  */
    /* JADX WARN: Removed duplicated region for block: B:2182:0x218b  */
    /* JADX WARN: Removed duplicated region for block: B:2184:0x2191  */
    /* JADX WARN: Removed duplicated region for block: B:2186:0x2194  */
    /* JADX WARN: Removed duplicated region for block: B:2189:0x219d  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x046c  */
    /* JADX WARN: Removed duplicated region for block: B:2193:0x21b0  */
    /* JADX WARN: Removed duplicated region for block: B:2195:0x21b4  */
    /* JADX WARN: Removed duplicated region for block: B:2196:0x21b7  */
    /* JADX WARN: Removed duplicated region for block: B:2199:0x21c4  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:2201:0x21ca  */
    /* JADX WARN: Removed duplicated region for block: B:2202:0x21cc  */
    /* JADX WARN: Removed duplicated region for block: B:2203:0x21d2  */
    /* JADX WARN: Removed duplicated region for block: B:2209:0x21ec  */
    /* JADX WARN: Removed duplicated region for block: B:2210:0x21ee  */
    /* JADX WARN: Removed duplicated region for block: B:2211:0x21f1  */
    /* JADX WARN: Removed duplicated region for block: B:2217:0x220d  */
    /* JADX WARN: Removed duplicated region for block: B:2218:0x220f  */
    /* JADX WARN: Removed duplicated region for block: B:2219:0x2212  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0473  */
    /* JADX WARN: Removed duplicated region for block: B:2227:0x2234  */
    /* JADX WARN: Removed duplicated region for block: B:2228:0x2236  */
    /* JADX WARN: Removed duplicated region for block: B:2229:0x223a  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:2232:0x2242  */
    /* JADX WARN: Removed duplicated region for block: B:2236:0x224e  */
    /* JADX WARN: Removed duplicated region for block: B:2238:0x2252  */
    /* JADX WARN: Removed duplicated region for block: B:2239:0x2255  */
    /* JADX WARN: Removed duplicated region for block: B:2243:0x2261  */
    /* JADX WARN: Removed duplicated region for block: B:2249:0x227d  */
    /* JADX WARN: Removed duplicated region for block: B:2252:0x2284  */
    /* JADX WARN: Removed duplicated region for block: B:2256:0x2290  */
    /* JADX WARN: Removed duplicated region for block: B:2258:0x2294  */
    /* JADX WARN: Removed duplicated region for block: B:2259:0x2297  */
    /* JADX WARN: Removed duplicated region for block: B:2263:0x22a6  */
    /* JADX WARN: Removed duplicated region for block: B:2269:0x22c0  */
    /* JADX WARN: Removed duplicated region for block: B:2273:0x22cd  */
    /* JADX WARN: Removed duplicated region for block: B:2279:0x22e8  */
    /* JADX WARN: Removed duplicated region for block: B:2282:0x22ed  */
    /* JADX WARN: Removed duplicated region for block: B:2286:0x22f9  */
    /* JADX WARN: Removed duplicated region for block: B:2288:0x22fd  */
    /* JADX WARN: Removed duplicated region for block: B:2289:0x2300  */
    /* JADX WARN: Removed duplicated region for block: B:2292:0x2306  */
    /* JADX WARN: Removed duplicated region for block: B:2296:0x230f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2300:0x2319 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2304:0x2328  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x049e  */
    /* JADX WARN: Removed duplicated region for block: B:2310:0x2341  */
    /* JADX WARN: Removed duplicated region for block: B:2314:0x2353  */
    /* JADX WARN: Removed duplicated region for block: B:2318:0x235f  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x04a0  */
    /* JADX WARN: Removed duplicated region for block: B:2320:0x2362  */
    /* JADX WARN: Removed duplicated region for block: B:2324:0x236c  */
    /* JADX WARN: Removed duplicated region for block: B:2325:0x236e  */
    /* JADX WARN: Removed duplicated region for block: B:2326:0x2374  */
    /* JADX WARN: Removed duplicated region for block: B:2329:0x237d  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x04ad  */
    /* JADX WARN: Removed duplicated region for block: B:2330:0x2380  */
    /* JADX WARN: Removed duplicated region for block: B:2334:0x2393  */
    /* JADX WARN: Removed duplicated region for block: B:2338:0x239e  */
    /* JADX WARN: Removed duplicated region for block: B:2340:0x23a1  */
    /* JADX WARN: Removed duplicated region for block: B:2345:0x23b4  */
    /* JADX WARN: Removed duplicated region for block: B:2346:0x23b6  */
    /* JADX WARN: Removed duplicated region for block: B:2347:0x23ba  */
    /* JADX WARN: Removed duplicated region for block: B:2353:0x23d7  */
    /* JADX WARN: Removed duplicated region for block: B:2354:0x23d9  */
    /* JADX WARN: Removed duplicated region for block: B:2355:0x23df  */
    /* JADX WARN: Removed duplicated region for block: B:2361:0x23f9  */
    /* JADX WARN: Removed duplicated region for block: B:2362:0x23fb  */
    /* JADX WARN: Removed duplicated region for block: B:2363:0x2400  */
    /* JADX WARN: Removed duplicated region for block: B:2368:0x241e  */
    /* JADX WARN: Removed duplicated region for block: B:2369:0x2420  */
    /* JADX WARN: Removed duplicated region for block: B:2370:0x2423  */
    /* JADX WARN: Removed duplicated region for block: B:2373:0x2437  */
    /* JADX WARN: Removed duplicated region for block: B:2377:0x2443  */
    /* JADX WARN: Removed duplicated region for block: B:2379:0x2446  */
    /* JADX WARN: Removed duplicated region for block: B:2383:0x2450  */
    /* JADX WARN: Removed duplicated region for block: B:2384:0x2452  */
    /* JADX WARN: Removed duplicated region for block: B:2385:0x2455  */
    /* JADX WARN: Removed duplicated region for block: B:2387:0x245c  */
    /* JADX WARN: Removed duplicated region for block: B:2388:0x245f  */
    /* JADX WARN: Removed duplicated region for block: B:2392:0x2472  */
    /* JADX WARN: Removed duplicated region for block: B:2396:0x2483  */
    /* JADX WARN: Removed duplicated region for block: B:2398:0x2486  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x04cc  */
    /* JADX WARN: Removed duplicated region for block: B:2402:0x2495  */
    /* JADX WARN: Removed duplicated region for block: B:2403:0x2497  */
    /* JADX WARN: Removed duplicated region for block: B:2404:0x249b  */
    /* JADX WARN: Removed duplicated region for block: B:2407:0x24a2  */
    /* JADX WARN: Removed duplicated region for block: B:2408:0x24a5  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x04ce  */
    /* JADX WARN: Removed duplicated region for block: B:2412:0x24b8  */
    /* JADX WARN: Removed duplicated region for block: B:2416:0x24c9  */
    /* JADX WARN: Removed duplicated region for block: B:2418:0x24cc  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x04d1  */
    /* JADX WARN: Removed duplicated region for block: B:2422:0x24db  */
    /* JADX WARN: Removed duplicated region for block: B:2423:0x24dd  */
    /* JADX WARN: Removed duplicated region for block: B:2424:0x24e0  */
    /* JADX WARN: Removed duplicated region for block: B:2427:0x24ea  */
    /* JADX WARN: Removed duplicated region for block: B:2431:0x24fa  */
    /* JADX WARN: Removed duplicated region for block: B:2436:0x2511  */
    /* JADX WARN: Removed duplicated region for block: B:2440:0x2521  */
    /* JADX WARN: Removed duplicated region for block: B:2445:0x2536  */
    /* JADX WARN: Removed duplicated region for block: B:2449:0x2546  */
    /* JADX WARN: Removed duplicated region for block: B:2454:0x255c  */
    /* JADX WARN: Removed duplicated region for block: B:2458:0x2567  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x04db  */
    /* JADX WARN: Removed duplicated region for block: B:2464:0x257e  */
    /* JADX WARN: Removed duplicated region for block: B:2468:0x258e  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x04dd  */
    /* JADX WARN: Removed duplicated region for block: B:2472:0x2599  */
    /* JADX WARN: Removed duplicated region for block: B:2474:0x259c  */
    /* JADX WARN: Removed duplicated region for block: B:2478:0x25ad  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:2482:0x25bd  */
    /* JADX WARN: Removed duplicated region for block: B:2484:0x25c0  */
    /* JADX WARN: Removed duplicated region for block: B:2488:0x25d1  */
    /* JADX WARN: Removed duplicated region for block: B:2492:0x25dc  */
    /* JADX WARN: Removed duplicated region for block: B:2494:0x25df  */
    /* JADX WARN: Removed duplicated region for block: B:2498:0x25f0  */
    /* JADX WARN: Removed duplicated region for block: B:2502:0x2600  */
    /* JADX WARN: Removed duplicated region for block: B:2504:0x2603  */
    /* JADX WARN: Removed duplicated region for block: B:2507:0x260b  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:2511:0x261e  */
    /* JADX WARN: Removed duplicated region for block: B:2513:0x262a  */
    /* JADX WARN: Removed duplicated region for block: B:2515:0x2645  */
    /* JADX WARN: Removed duplicated region for block: B:2517:0x2652  */
    /* JADX WARN: Removed duplicated region for block: B:2519:0x265e  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:2522:0x266c  */
    /* JADX WARN: Removed duplicated region for block: B:2525:0x2675  */
    /* JADX WARN: Removed duplicated region for block: B:2527:0x2680  */
    /* JADX WARN: Removed duplicated region for block: B:2530:0x268b  */
    /* JADX WARN: Removed duplicated region for block: B:2532:0x2691  */
    /* JADX WARN: Removed duplicated region for block: B:2534:0x26dc  */
    /* JADX WARN: Removed duplicated region for block: B:2536:0x26ea  */
    /* JADX WARN: Removed duplicated region for block: B:2538:0x26f2  */
    /* JADX WARN: Removed duplicated region for block: B:2541:0x275e  */
    /* JADX WARN: Removed duplicated region for block: B:2543:0x2764  */
    /* JADX WARN: Removed duplicated region for block: B:2546:0x2773  */
    /* JADX WARN: Removed duplicated region for block: B:2548:0x2779  */
    /* JADX WARN: Removed duplicated region for block: B:2551:0x2791  */
    /* JADX WARN: Removed duplicated region for block: B:2553:0x2797  */
    /* JADX WARN: Removed duplicated region for block: B:2555:0x27a0  */
    /* JADX WARN: Removed duplicated region for block: B:2557:0x27a6  */
    /* JADX WARN: Removed duplicated region for block: B:2560:0x27bc  */
    /* JADX WARN: Removed duplicated region for block: B:2562:0x27c2  */
    /* JADX WARN: Removed duplicated region for block: B:2565:0x27d1  */
    /* JADX WARN: Removed duplicated region for block: B:2567:0x27d7  */
    /* JADX WARN: Removed duplicated region for block: B:2569:0x27e0  */
    /* JADX WARN: Removed duplicated region for block: B:2571:0x27e6  */
    /* JADX WARN: Removed duplicated region for block: B:2573:0x27ef  */
    /* JADX WARN: Removed duplicated region for block: B:2575:0x27f5  */
    /* JADX WARN: Removed duplicated region for block: B:2578:0x2860  */
    /* JADX WARN: Removed duplicated region for block: B:2580:0x2866  */
    /* JADX WARN: Removed duplicated region for block: B:2583:0x2877  */
    /* JADX WARN: Removed duplicated region for block: B:2585:0x287d  */
    /* JADX WARN: Removed duplicated region for block: B:2588:0x2895  */
    /* JADX WARN: Removed duplicated region for block: B:2590:0x289b  */
    /* JADX WARN: Removed duplicated region for block: B:2593:0x28ac  */
    /* JADX WARN: Removed duplicated region for block: B:2595:0x28b2  */
    /* JADX WARN: Removed duplicated region for block: B:2598:0x28c1  */
    /* JADX WARN: Removed duplicated region for block: B:2600:0x28c7  */
    /* JADX WARN: Removed duplicated region for block: B:2603:0x28d8  */
    /* JADX WARN: Removed duplicated region for block: B:2605:0x28de  */
    /* JADX WARN: Removed duplicated region for block: B:2608:0x28ef  */
    /* JADX WARN: Removed duplicated region for block: B:2610:0x28f5  */
    /* JADX WARN: Removed duplicated region for block: B:2613:0x2906  */
    /* JADX WARN: Removed duplicated region for block: B:2615:0x290c  */
    /* JADX WARN: Removed duplicated region for block: B:2618:0x291d  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0515  */
    /* JADX WARN: Removed duplicated region for block: B:2620:0x2923  */
    /* JADX WARN: Removed duplicated region for block: B:2623:0x2934  */
    /* JADX WARN: Removed duplicated region for block: B:2625:0x293a  */
    /* JADX WARN: Removed duplicated region for block: B:2628:0x294b  */
    /* JADX WARN: Removed duplicated region for block: B:2630:0x2951  */
    /* JADX WARN: Removed duplicated region for block: B:2633:0x2962  */
    /* JADX WARN: Removed duplicated region for block: B:2635:0x2968  */
    /* JADX WARN: Removed duplicated region for block: B:2638:0x297b  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x051b A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2640:0x2981  */
    /* JADX WARN: Removed duplicated region for block: B:2643:0x2992  */
    /* JADX WARN: Removed duplicated region for block: B:2645:0x2998  */
    /* JADX WARN: Removed duplicated region for block: B:2648:0x29a9  */
    /* JADX WARN: Removed duplicated region for block: B:2650:0x29af  */
    /* JADX WARN: Removed duplicated region for block: B:2653:0x29c7  */
    /* JADX WARN: Removed duplicated region for block: B:2655:0x29cd  */
    /* JADX WARN: Removed duplicated region for block: B:2658:0x29e5  */
    /* JADX WARN: Removed duplicated region for block: B:2660:0x29eb  */
    /* JADX WARN: Removed duplicated region for block: B:2662:0x29f9  */
    /* JADX WARN: Removed duplicated region for block: B:2664:0x2a01  */
    /* JADX WARN: Removed duplicated region for block: B:2666:0x2a0f  */
    /* JADX WARN: Removed duplicated region for block: B:2668:0x2a1c  */
    /* JADX WARN: Removed duplicated region for block: B:2672:0x2a2d  */
    /* JADX WARN: Removed duplicated region for block: B:2675:0x2a3b  */
    /* JADX WARN: Removed duplicated region for block: B:2678:0x2a46  */
    /* JADX WARN: Removed duplicated region for block: B:2680:0x2a51  */
    /* JADX WARN: Removed duplicated region for block: B:2683:0x2a62  */
    /* JADX WARN: Removed duplicated region for block: B:2685:0x2a68  */
    /* JADX WARN: Removed duplicated region for block: B:2687:0x2a70  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0531  */
    /* JADX WARN: Removed duplicated region for block: B:2691:0x2ad5  */
    /* JADX WARN: Removed duplicated region for block: B:2693:0x2adb  */
    /* JADX WARN: Removed duplicated region for block: B:2698:0x2b00  */
    /* JADX WARN: Removed duplicated region for block: B:2700:0x2b06  */
    /* JADX WARN: Removed duplicated region for block: B:2703:0x2b28  */
    /* JADX WARN: Removed duplicated region for block: B:2705:0x2b2e  */
    /* JADX WARN: Removed duplicated region for block: B:2708:0x2b52  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0537 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2710:0x2b5d  */
    /* JADX WARN: Removed duplicated region for block: B:2714:0x2b7d  */
    /* JADX WARN: Removed duplicated region for block: B:2716:0x2b8a  */
    /* JADX WARN: Removed duplicated region for block: B:2718:0x2ba7  */
    /* JADX WARN: Removed duplicated region for block: B:2720:0x2baf  */
    /* JADX WARN: Removed duplicated region for block: B:2722:0x2bd1  */
    /* JADX WARN: Removed duplicated region for block: B:2724:0x2bd9  */
    /* JADX WARN: Removed duplicated region for block: B:2727:0x2bf1  */
    /* JADX WARN: Removed duplicated region for block: B:2729:0x2bfc  */
    /* JADX WARN: Removed duplicated region for block: B:2732:0x2c0d  */
    /* JADX WARN: Removed duplicated region for block: B:2734:0x2c13  */
    /* JADX WARN: Removed duplicated region for block: B:2737:0x2c35  */
    /* JADX WARN: Removed duplicated region for block: B:2739:0x2c3b  */
    /* JADX WARN: Removed duplicated region for block: B:2742:0x2c4c  */
    /* JADX WARN: Removed duplicated region for block: B:2744:0x2c52  */
    /* JADX WARN: Removed duplicated region for block: B:2746:0x2c5b  */
    /* JADX WARN: Removed duplicated region for block: B:2748:0x2c66  */
    /* JADX WARN: Removed duplicated region for block: B:2750:0x2c6f  */
    /* JADX WARN: Removed duplicated region for block: B:2752:0x2c7a  */
    /* JADX WARN: Removed duplicated region for block: B:2755:0x2c89  */
    /* JADX WARN: Removed duplicated region for block: B:2757:0x2c8f  */
    /* JADX WARN: Removed duplicated region for block: B:2759:0x2c9a  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x054d  */
    /* JADX WARN: Removed duplicated region for block: B:2761:0x2ca7  */
    /* JADX WARN: Removed duplicated region for block: B:2765:0x2cb6  */
    /* JADX WARN: Removed duplicated region for block: B:2768:0x2cc4  */
    /* JADX WARN: Removed duplicated region for block: B:2772:0x2cd5  */
    /* JADX WARN: Removed duplicated region for block: B:2774:0x2cdb  */
    /* JADX WARN: Removed duplicated region for block: B:2776:0x2cf1  */
    /* JADX WARN: Removed duplicated region for block: B:2779:0x2cfa  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0553 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2782:0x2d05  */
    /* JADX WARN: Removed duplicated region for block: B:2784:0x2d10  */
    /* JADX WARN: Removed duplicated region for block: B:2788:0x2d21  */
    /* JADX WARN: Removed duplicated region for block: B:2791:0x2d2f  */
    /* JADX WARN: Removed duplicated region for block: B:2794:0x2d3a  */
    /* JADX WARN: Removed duplicated region for block: B:2796:0x2d45  */
    /* JADX WARN: Removed duplicated region for block: B:2798:0x2d4e  */
    /* JADX WARN: Removed duplicated region for block: B:2800:0x2d59  */
    /* JADX WARN: Removed duplicated region for block: B:2802:0x2d62  */
    /* JADX WARN: Removed duplicated region for block: B:2804:0x2d6d  */
    /* JADX WARN: Removed duplicated region for block: B:2806:0x2d75  */
    /* JADX WARN: Removed duplicated region for block: B:2809:0x2d85  */
    /* JADX WARN: Removed duplicated region for block: B:2812:0x2d93  */
    /* JADX WARN: Removed duplicated region for block: B:2815:0x2d9e  */
    /* JADX WARN: Removed duplicated region for block: B:2817:0x2da9  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0566  */
    /* JADX WARN: Removed duplicated region for block: B:2821:0x2dba  */
    /* JADX WARN: Removed duplicated region for block: B:2824:0x2dc8  */
    /* JADX WARN: Removed duplicated region for block: B:2827:0x2dd3  */
    /* JADX WARN: Removed duplicated region for block: B:2829:0x2dde  */
    /* JADX WARN: Removed duplicated region for block: B:2831:0x2de7  */
    /* JADX WARN: Removed duplicated region for block: B:2833:0x2df2  */
    /* JADX WARN: Removed duplicated region for block: B:2835:0x2dfb  */
    /* JADX WARN: Removed duplicated region for block: B:2837:0x2e01  */
    /* JADX WARN: Removed duplicated region for block: B:2839:0x2e09  */
    /* JADX WARN: Removed duplicated region for block: B:2843:0x2e23  */
    /* JADX WARN: Removed duplicated region for block: B:2845:0x2e29  */
    /* JADX WARN: Removed duplicated region for block: B:2847:0x2e32  */
    /* JADX WARN: Removed duplicated region for block: B:2849:0x2e3d  */
    /* JADX WARN: Removed duplicated region for block: B:2853:0x2e4e  */
    /* JADX WARN: Removed duplicated region for block: B:2856:0x2e5c  */
    /* JADX WARN: Removed duplicated region for block: B:2859:0x2e67  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x057e  */
    /* JADX WARN: Removed duplicated region for block: B:2861:0x2e72  */
    /* JADX WARN: Removed duplicated region for block: B:2863:0x2e7b  */
    /* JADX WARN: Removed duplicated region for block: B:2865:0x2e86  */
    /* JADX WARN: Removed duplicated region for block: B:2868:0x2e97  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0580  */
    /* JADX WARN: Removed duplicated region for block: B:2870:0x2e9d  */
    /* JADX WARN: Removed duplicated region for block: B:2875:0x2eb8  */
    /* JADX WARN: Removed duplicated region for block: B:2877:0x2ebe  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x058d  */
    /* JADX WARN: Removed duplicated region for block: B:2880:0x2ecf  */
    /* JADX WARN: Removed duplicated region for block: B:2882:0x2ed5  */
    /* JADX WARN: Removed duplicated region for block: B:2884:0x2ede  */
    /* JADX WARN: Removed duplicated region for block: B:2886:0x2ee4  */
    /* JADX WARN: Removed duplicated region for block: B:2889:0x2ef3  */
    /* JADX WARN: Removed duplicated region for block: B:2891:0x2ef9  */
    /* JADX WARN: Removed duplicated region for block: B:2893:0x2f01  */
    /* JADX WARN: Removed duplicated region for block: B:2896:0x2f1b  */
    /* JADX WARN: Removed duplicated region for block: B:2898:0x2f23  */
    /* JADX WARN: Removed duplicated region for block: B:2902:0x2f34  */
    /* JADX WARN: Removed duplicated region for block: B:2905:0x2f3d  */
    /* JADX WARN: Removed duplicated region for block: B:2908:0x2f48  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0598  */
    /* JADX WARN: Removed duplicated region for block: B:2910:0x2f4e  */
    /* JADX WARN: Removed duplicated region for block: B:2912:0x2f57  */
    /* JADX WARN: Removed duplicated region for block: B:2914:0x2f62  */
    /* JADX WARN: Removed duplicated region for block: B:2918:0x2f73  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x059f  */
    /* JADX WARN: Removed duplicated region for block: B:2921:0x2f81  */
    /* JADX WARN: Removed duplicated region for block: B:2924:0x2f8c  */
    /* JADX WARN: Removed duplicated region for block: B:2926:0x2f92  */
    /* JADX WARN: Removed duplicated region for block: B:2928:0x2f9b  */
    /* JADX WARN: Removed duplicated region for block: B:2930:0x2fa1  */
    /* JADX WARN: Removed duplicated region for block: B:2933:0x2fb2  */
    /* JADX WARN: Removed duplicated region for block: B:2935:0x2fb8  */
    /* JADX WARN: Removed duplicated region for block: B:2940:0x2fd3  */
    /* JADX WARN: Removed duplicated region for block: B:2942:0x2fd9  */
    /* JADX WARN: Removed duplicated region for block: B:2945:0x2fea  */
    /* JADX WARN: Removed duplicated region for block: B:2947:0x2ff0  */
    /* JADX WARN: Removed duplicated region for block: B:2949:0x2ff9  */
    /* JADX WARN: Removed duplicated region for block: B:2951:0x2fff  */
    /* JADX WARN: Removed duplicated region for block: B:2954:0x3010  */
    /* JADX WARN: Removed duplicated region for block: B:2956:0x3016  */
    /* JADX WARN: Removed duplicated region for block: B:2961:0x3031  */
    /* JADX WARN: Removed duplicated region for block: B:2963:0x3037  */
    /* JADX WARN: Removed duplicated region for block: B:2966:0x3048  */
    /* JADX WARN: Removed duplicated region for block: B:2968:0x304e  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x05b7  */
    /* JADX WARN: Removed duplicated region for block: B:2970:0x3057  */
    /* JADX WARN: Removed duplicated region for block: B:2972:0x305d  */
    /* JADX WARN: Removed duplicated region for block: B:2975:0x306e  */
    /* JADX WARN: Removed duplicated region for block: B:2977:0x3074  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x05b9  */
    /* JADX WARN: Removed duplicated region for block: B:2981:0x3085  */
    /* JADX WARN: Removed duplicated region for block: B:2984:0x3093  */
    /* JADX WARN: Removed duplicated region for block: B:2987:0x309e  */
    /* JADX WARN: Removed duplicated region for block: B:2989:0x30a9  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x05bc  */
    /* JADX WARN: Removed duplicated region for block: B:2991:0x30b2  */
    /* JADX WARN: Removed duplicated region for block: B:2993:0x30bd  */
    /* JADX WARN: Removed duplicated region for block: B:2996:0x30ce  */
    /* JADX WARN: Removed duplicated region for block: B:2998:0x30d4  */
    /* JADX WARN: Removed duplicated region for block: B:3003:0x30ef  */
    /* JADX WARN: Removed duplicated region for block: B:3005:0x30f5  */
    /* JADX WARN: Removed duplicated region for block: B:3008:0x3106  */
    /* JADX WARN: Removed duplicated region for block: B:3010:0x310c  */
    /* JADX WARN: Removed duplicated region for block: B:3012:0x3115  */
    /* JADX WARN: Removed duplicated region for block: B:3014:0x311b  */
    /* JADX WARN: Removed duplicated region for block: B:3016:0x3124  */
    /* JADX WARN: Removed duplicated region for block: B:3018:0x312a  */
    /* JADX WARN: Removed duplicated region for block: B:3022:0x313b  */
    /* JADX WARN: Removed duplicated region for block: B:3025:0x3144  */
    /* JADX WARN: Removed duplicated region for block: B:3028:0x314f  */
    /* JADX WARN: Removed duplicated region for block: B:3030:0x3155  */
    /* JADX WARN: Removed duplicated region for block: B:3033:0x3166  */
    /* JADX WARN: Removed duplicated region for block: B:3035:0x316c  */
    /* JADX WARN: Removed duplicated region for block: B:3039:0x3187  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x05db  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x05dd  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x05e0  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x05f7  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x05f9  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x05fc  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0616  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0618  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x061b  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0630  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0633  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x064a  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0650  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0668  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x066a  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x068b  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x0695  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0697  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:364:0x06b7  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x06d6  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x06e0  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x06e2  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x06e5  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x06eb  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x06ee  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0709  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x070b  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0718  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0723  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x0725  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0730  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0732  */
    /* JADX WARN: Removed duplicated region for block: B:401:0x0735  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x073c  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x074b  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x075e  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0760  */
    /* JADX WARN: Removed duplicated region for block: B:415:0x076d  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x078b  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x078d  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0790  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x079a  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x079c  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x079f  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x07b5 A[PHI: r12
  0x07b5: PHI (r12v24 long) = (r12v22 long), (r12v20 long) binds: [B:448:0x07cf, B:435:0x07b2] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:438:0x07b7  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x07b9  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x07bc  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x07c6  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x07c8  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x07cb  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x07d2  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x07fa  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x0817  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0819  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x0828  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0848  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x084a  */
    /* JADX WARN: Removed duplicated region for block: B:474:0x084f  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x085b  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x085d  */
    /* JADX WARN: Removed duplicated region for block: B:481:0x0860  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x087f  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x0881  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x0886  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x0899  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x089b  */
    /* JADX WARN: Removed duplicated region for block: B:499:0x089e  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x08c3  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x08c5  */
    /* JADX WARN: Removed duplicated region for block: B:512:0x08ca  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x08d8  */
    /* JADX WARN: Removed duplicated region for block: B:516:0x08db  */
    /* JADX WARN: Removed duplicated region for block: B:519:0x08ea  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x08f6  */
    /* JADX WARN: Removed duplicated region for block: B:524:0x08f8  */
    /* JADX WARN: Removed duplicated region for block: B:525:0x08fd  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x091b A[PHI: r12
  0x091b: PHI (r12v35 long) = (r12v30 long), (r12v28 long) binds: [B:543:0x0936, B:530:0x0918] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:533:0x091e  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x0920  */
    /* JADX WARN: Removed duplicated region for block: B:535:0x0923  */
    /* JADX WARN: Removed duplicated region for block: B:539:0x092d  */
    /* JADX WARN: Removed duplicated region for block: B:540:0x092f  */
    /* JADX WARN: Removed duplicated region for block: B:541:0x0932  */
    /* JADX WARN: Removed duplicated region for block: B:545:0x0939  */
    /* JADX WARN: Removed duplicated region for block: B:552:0x0952  */
    /* JADX WARN: Removed duplicated region for block: B:553:0x0954  */
    /* JADX WARN: Removed duplicated region for block: B:554:0x0959  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x0967  */
    /* JADX WARN: Removed duplicated region for block: B:560:0x0969  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x0971  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x0975  */
    /* JADX WARN: Removed duplicated region for block: B:564:0x0978  */
    /* JADX WARN: Removed duplicated region for block: B:579:0x09c7 A[PHI: r8
  0x09c7: PHI (r8v32 long) = (r8v27 long), (r8v25 long) binds: [B:590:0x09e1, B:577:0x09c4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:683:0x0b45  */
    /* JADX WARN: Removed duplicated region for block: B:684:0x0b47  */
    /* JADX WARN: Removed duplicated region for block: B:685:0x0b4f  */
    /* JADX WARN: Removed duplicated region for block: B:688:0x0b57  */
    /* JADX WARN: Removed duplicated region for block: B:689:0x0b59  */
    /* JADX WARN: Removed duplicated region for block: B:692:0x0b5d  */
    /* JADX WARN: Removed duplicated region for block: B:693:0x0b5f  */
    /* JADX WARN: Removed duplicated region for block: B:697:0x0b6a  */
    /* JADX WARN: Removed duplicated region for block: B:698:0x0b6c  */
    /* JADX WARN: Removed duplicated region for block: B:699:0x0b72  */
    /* JADX WARN: Removed duplicated region for block: B:702:0x0b7b  */
    /* JADX WARN: Removed duplicated region for block: B:703:0x0b7e  */
    /* JADX WARN: Removed duplicated region for block: B:709:0x0b91 A[PHI: r6 r8
  0x0b91: PHI (r6v25 long) = (r6v23 long), (r6v21 long) binds: [B:719:0x0bae, B:707:0x0b8e] A[DONT_GENERATE, DONT_INLINE]
  0x0b91: PHI (r8v37 long) = (r8v35 long), (r8v33 long) binds: [B:719:0x0bae, B:707:0x0b8e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:710:0x0b94  */
    /* JADX WARN: Removed duplicated region for block: B:711:0x0b96  */
    /* JADX WARN: Removed duplicated region for block: B:712:0x0b99  */
    /* JADX WARN: Removed duplicated region for block: B:716:0x0ba3  */
    /* JADX WARN: Removed duplicated region for block: B:717:0x0ba5  */
    /* JADX WARN: Removed duplicated region for block: B:718:0x0baa  */
    /* JADX WARN: Removed duplicated region for block: B:721:0x0bb1  */
    /* JADX WARN: Removed duplicated region for block: B:728:0x0bca  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x0bcc  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x0bd1  */
    /* JADX WARN: Removed duplicated region for block: B:738:0x0bf4  */
    /* JADX WARN: Removed duplicated region for block: B:739:0x0bf6  */
    /* JADX WARN: Removed duplicated region for block: B:740:0x0bfb  */
    /* JADX WARN: Removed duplicated region for block: B:745:0x0c09  */
    /* JADX WARN: Removed duplicated region for block: B:746:0x0c0b  */
    /* JADX WARN: Removed duplicated region for block: B:747:0x0c0f  */
    /* JADX WARN: Removed duplicated region for block: B:755:0x0c2b  */
    /* JADX WARN: Removed duplicated region for block: B:756:0x0c2d  */
    /* JADX WARN: Removed duplicated region for block: B:757:0x0c32  */
    /* JADX WARN: Removed duplicated region for block: B:762:0x0c3e  */
    /* JADX WARN: Removed duplicated region for block: B:763:0x0c40  */
    /* JADX WARN: Removed duplicated region for block: B:764:0x0c46  */
    /* JADX WARN: Removed duplicated region for block: B:767:0x0c4f  */
    /* JADX WARN: Removed duplicated region for block: B:768:0x0c52  */
    /* JADX WARN: Removed duplicated region for block: B:810:0x0d00 A[PHI: r10
  0x0d00: PHI (r10v42 long) = (r10v40 long), (r10v38 long) binds: [B:821:0x0d1d, B:808:0x0cfd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:875:0x0df4 A[PHI: r8
  0x0df4: PHI (r8v44 long) = (r8v40 long), (r8v38 long) binds: [B:886:0x0e0f, B:873:0x0df1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:961:0x0f3b A[PHI: r8
  0x0f3b: PHI (r8v49 long) = (r8v47 long), (r8v45 long) binds: [B:973:0x0f55, B:959:0x0f38] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:981:0x0f68  */
    /* JADX WARN: Removed duplicated region for block: B:982:0x0f6a  */
    /* JADX WARN: Removed duplicated region for block: B:983:0x0f6f  */
    /* JADX WARN: Removed duplicated region for block: B:988:0x0f7b  */
    /* JADX WARN: Removed duplicated region for block: B:989:0x0f7d  */
    /* JADX WARN: Removed duplicated region for block: B:990:0x0f80  */
    /* JADX WARN: Removed duplicated region for block: B:993:0x0f86  */
    /* JADX WARN: Removed duplicated region for block: B:994:0x0f89  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void executeBindings() {
        /*
            Method dump skipped, instruction units count: 12718
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.databinding.FragmentActiveSafeBindingRrRKPLandImpl.executeBindings():void");
    }

    public final boolean f(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 = 16777216 | this.K1;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.L1 = 16777216 | this.L1;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.L1 |= 33554432;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.L1 |= 67108864;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 134217728;
        }
        return true;
    }

    public final boolean g(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 262144;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.L1 |= 4096;
            }
            return true;
        }
        if (i != 2) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 8192;
        }
        return true;
    }

    public final boolean h(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 = 2097152 | this.K1;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.L1 |= 524288;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.L1 |= 1048576;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.L1 = 2097152 | this.L1;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 4194304;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.K1 == 0 && this.L1 == 0 && this.M1 == 0 && this.N1 == 0 && this.O1 == 0) {
                return false;
            }
            return true;
        }
    }

    public final boolean i(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 128;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 2199023255552L;
            }
            return true;
        }
        if (i != 2) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 4398046511104L;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.K1 = 0L;
            this.L1 = 68719476736L;
            this.M1 = 0L;
            this.N1 = 0L;
            this.O1 = 0L;
        }
        requestRebind();
    }

    public final boolean j(ActiveSafeStateModel activeSafeStateModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= SegmentPool.MAX_SIZE;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.L1 |= 64;
            }
            return true;
        }
        if (i == 9) {
            synchronized (this) {
                this.L1 |= 128;
            }
            return true;
        }
        if (i != 1) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 256;
        }
        return true;
    }

    public final boolean k(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 1048576;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.L1 |= 32768;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.L1 |= SegmentPool.MAX_SIZE;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.L1 |= 131072;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 262144;
        }
        return true;
    }

    public final boolean l(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 2048;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 72057594037927936L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 144115188075855872L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 288230376151711744L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 576460752303423488L;
        }
        return true;
    }

    public final boolean m(MutableLiveData<Byte> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 4194304;
        }
        return true;
    }

    public final boolean n(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 16384;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.L1 |= 8;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.L1 |= 16;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 32;
        }
        return true;
    }

    public final boolean o(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 2;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 2147483648L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 4294967296L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 8589934592L;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return D((MutableLiveData) obj, i2);
            case 1:
                return o((ActiveSafeModel) obj, i2);
            case 2:
                return s((MutableLiveData) obj, i2);
            case 3:
                return u((MutableLiveData) obj, i2);
            case 4:
                return y((DriverAssistModel) obj, i2);
            case 5:
                return w((MutableLiveData) obj, i2);
            case 6:
                return t((DriverAssistModel) obj, i2);
            case 7:
                return i((DriverAssistModel) obj, i2);
            case 8:
                return C((ActiveSafeStateModel) obj, i2);
            case 9:
                return x((DriverAssistModel) obj, i2);
            case 10:
                return v((ActiveSafeModel) obj, i2);
            case 11:
                return l((DriverAssistModel) obj, i2);
            case 12:
                return e((DriverAssistModel) obj, i2);
            case 13:
                return z((DriverAssistModel) obj, i2);
            case 14:
                return n((ActiveSafeModel) obj, i2);
            case 15:
                return q((MutableLiveData) obj, i2);
            case 16:
                return j((ActiveSafeStateModel) obj, i2);
            case 17:
                return A((DriverAssistModel) obj, i2);
            case 18:
                return g((ActiveSafeModel) obj, i2);
            case 19:
                return p((ActiveSafeModel) obj, i2);
            case 20:
                return k((DriverAssistModel) obj, i2);
            case 21:
                return h((DriverAssistModel) obj, i2);
            case 22:
                return m((MutableLiveData) obj, i2);
            case 23:
                return d((ActiveSafeModel) obj, i2);
            case 24:
                return f((DriverAssistModel) obj, i2);
            case 25:
                return r((ActiveSafeModel) obj, i2);
            case 26:
                return B((DriverAssistModel) obj, i2);
            default:
                return false;
        }
    }

    public final boolean p(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 524288;
            }
            return true;
        }
        if (i != 9) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 16384;
        }
        return true;
    }

    public final boolean q(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 32768;
        }
        return true;
    }

    public final boolean r(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 33554432;
            }
            return true;
        }
        if (i == 10) {
            synchronized (this) {
                this.L1 |= 268435456;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.L1 |= 536870912;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.L1 |= 1073741824;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.L1 |= 2147483648L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 4294967296L;
        }
        return true;
    }

    public final boolean s(MutableLiveData<Byte> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 4;
        }
        return true;
    }

    @Override // com.byd.carsettings.databinding.FragmentActiveSafeBinding
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.x1 = onCheckedChangeListener;
        synchronized (this) {
            this.K1 |= 134217728;
        }
        notifyPropertyChanged(6);
        super.requestRebind();
    }

    @Override // com.byd.carsettings.databinding.FragmentActiveSafeBinding
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.z1 = onClickListener;
        synchronized (this) {
            this.K1 |= 536870912;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Override // com.byd.carsettings.databinding.FragmentActiveSafeBinding
    public void setOnItemCheck(@Nullable SegmentedButtonGroup.d dVar) {
        this.y1 = dVar;
        synchronized (this) {
            this.K1 |= 1073741824;
        }
        notifyPropertyChanged(8);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (6 == i) {
            setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) obj);
        } else if (4 == i) {
            c((ActiveSafeViewModel) obj);
        } else if (7 == i) {
            setOnClickListener((View.OnClickListener) obj);
        } else {
            if (8 != i) {
                return false;
            }
            setOnItemCheck((SegmentedButtonGroup.d) obj);
        }
        return true;
    }

    public final boolean t(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 64;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 274877906944L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 549755813888L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 1099511627776L;
        }
        return true;
    }

    public final boolean u(MutableLiveData<Byte> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 8;
        }
        return true;
    }

    public final boolean v(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 1024;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 2251799813685248L;
            }
            return true;
        }
        if (i == 9) {
            synchronized (this) {
                this.K1 |= 4503599627370496L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 9007199254740992L;
            }
            return true;
        }
        if (i == 11) {
            synchronized (this) {
                this.K1 |= 18014398509481984L;
            }
            return true;
        }
        if (i != 10) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 36028797018963968L;
        }
        return true;
    }

    public final boolean w(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 32;
        }
        return true;
    }

    public final boolean x(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 512;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 140737488355328L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 281474976710656L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 562949953421312L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 1125899906842624L;
        }
        return true;
    }

    public final boolean y(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 16;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 17179869184L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 34359738368L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 68719476736L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 137438953472L;
        }
        return true;
    }

    public final boolean z(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.K1 |= 8192;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= Long.MIN_VALUE;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.L1 |= 1;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.L1 |= 2;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.L1 |= 4;
        }
        return true;
    }

    public FragmentActiveSafeBindingRrRKPLandImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 27, (BydSwitch) objArr[19], (ConstraintLayout) objArr[91], (ConstraintLayout) objArr[34], (ConstraintLayout) objArr[100], (ConstraintLayout) objArr[21], (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[113], (ConstraintLayout) objArr[94], (ConstraintLayout) objArr[42], (View) objArr[65], (Flow) objArr[35], (Flow) objArr[115], (Flow) objArr[122], (ImageView) objArr[80], (ImageView) objArr[7], (ImageView) objArr[93], (ImageView) objArr[55], (ImageView) objArr[52], (ImageView) objArr[102], (ImageView) objArr[73], (ImageView) objArr[76], (ImageView) objArr[13], (ImageView) objArr[27], (ImageView) objArr[96], (ImageView) objArr[43], (ImageView) objArr[41], (ImageView) objArr[61], (ImageView) objArr[58], (ImageView) objArr[38], (ImageView) objArr[64], (View) objArr[36], (ConstraintLayout) objArr[79], (View) objArr[5], (ConstraintLayout) objArr[8], (View) objArr[53], (View) objArr[50], (View) objArr[71], (View) objArr[74], (LinearLayout) objArr[49], (View) objArr[39], (View) objArr[59], (View) objArr[56], (View) objArr[84], (View) objArr[62], (View) objArr[69], (ConstraintLayout) objArr[77], (LottieAnimationView) objArr[109], (LottieAnimationView) objArr[106], (LottieAnimationView) objArr[114], (LottieAnimationView) objArr[121], (DampingScrollView) objArr[0], (RecyclerView) objArr[2], (ConstraintLayout) objArr[30], (ImageView) objArr[32], (BydSwitch) objArr[31], (TextView) objArr[108], (SegmentedButton) objArr[82], (SegmentedButton) objArr[98], (SegmentedButton) objArr[99], (SegmentedButton) objArr[83], (SegmentedButton) objArr[48], (SegmentedButton) objArr[47], (SegmentedButton) objArr[45], (SegmentedButton) objArr[46], (SegmentedButton) objArr[86], (SegmentedButton) objArr[87], (SegmentedButton) objArr[88], (SegmentedButton) objArr[89], (SegmentedButton) objArr[90], (SegmentedButtonGroup) objArr[81], (SegmentedButtonGroup) objArr[14], (SegmentedButtonGroup) objArr[22], (SegmentedButtonGroup) objArr[97], (SegmentedButtonGroup) objArr[44], (SegmentedButtonGroup) objArr[85], (View) objArr[66], (ImageView) objArr[68], (BydSwitch) objArr[67], (TextView) objArr[123], (BydSwitch) objArr[37], (BydSwitch) objArr[6], (BydSwitch) objArr[92], (BydSwitch) objArr[9], (BydSwitch) objArr[54], (BydSwitch) objArr[51], (BydSwitch) objArr[101], (BydSwitch) objArr[75], (BydSwitch) objArr[72], (BydSwitch) objArr[95], (BydSwitch) objArr[40], (BydSwitch) objArr[60], (BydSwitch) objArr[57], (BydSwitch) objArr[63], (BydSwitch) objArr[70], (BydSwitch) objArr[78], (SegmentedButton) objArr[24], (SegmentedButton) objArr[23], (SegmentedButton) objArr[25], (SegmentedButton) objArr[18], (SegmentedButton) objArr[17], (SegmentedButton) objArr[16], (SegmentedButton) objArr[15], (TextView) objArr[103], (TextView) objArr[130], (TextView) objArr[104], (TextView) objArr[105], (TextView) objArr[117], (TextView) objArr[116], (TextView) objArr[126], (TextView) objArr[125], (TextView) objArr[12], (TextView) objArr[20], (TextView) objArr[107], (TextView) objArr[10], (TextView) objArr[128], (TextView) objArr[131], (TextView) objArr[112], (TextView) objArr[111], (TextView) objArr[119], (TextView) objArr[118], (TextView) objArr[129], (TextView) objArr[110], (TextView) objArr[132], (TextView) objArr[120], (TextView) objArr[124], (TextView) objArr[127]);
        this.K1 = -1L;
        this.L1 = -1L;
        this.M1 = -1L;
        this.N1 = -1L;
        this.O1 = -1L;
        this.f2802a.setTag(null);
        this.f2803b.setTag(null);
        this.f2804c.setTag(null);
        this.f2805d.setTag(null);
        this.f2806e.setTag(null);
        this.f2807f.setTag(null);
        this.h.setTag(null);
        this.i.setTag(null);
        this.j.setTag(null);
        this.k.setTag(null);
        this.n.setTag(null);
        this.o.setTag(null);
        this.p.setTag(null);
        this.q.setTag(null);
        this.r.setTag(null);
        this.s.setTag(null);
        this.t.setTag(null);
        this.u.setTag(null);
        this.v.setTag(null);
        this.w.setTag(null);
        this.x.setTag(null);
        this.y.setTag(null);
        this.z.setTag(null);
        this.A.setTag(null);
        this.B.setTag(null);
        this.D.setTag(null);
        this.E.setTag(null);
        this.F.setTag(null);
        this.G.setTag(null);
        this.H.setTag(null);
        this.I.setTag(null);
        this.J.setTag(null);
        this.K.setTag(null);
        this.L.setTag(null);
        this.M.setTag(null);
        this.N.setTag(null);
        this.O.setTag(null);
        this.P.setTag(null);
        this.Q.setTag(null);
        this.R.setTag(null);
        this.S.setTag(null);
        this.T.setTag(null);
        this.U.setTag(null);
        RelativeLayout relativeLayout = (RelativeLayout) objArr[1];
        this.D1 = relativeLayout;
        relativeLayout.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[26];
        this.E1 = linearLayout;
        linearLayout.setTag(null);
        TextView textView = (TextView) objArr[28];
        this.F1 = textView;
        textView.setTag(null);
        TextView textView2 = (TextView) objArr[29];
        this.G1 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) objArr[3];
        this.H1 = textView3;
        textView3.setTag(null);
        TextView textView4 = (TextView) objArr[33];
        this.I1 = textView4;
        textView4.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[4];
        this.J1 = constraintLayout;
        constraintLayout.setTag(null);
        this.Z.setTag(null);
        this.a0.setTag(null);
        this.b0.setTag(null);
        this.c0.setTag(null);
        this.d0.setTag(null);
        this.f0.setTag(null);
        this.g0.setTag(null);
        this.h0.setTag(null);
        this.i0.setTag(null);
        this.j0.setTag(null);
        this.k0.setTag(null);
        this.l0.setTag(null);
        this.m0.setTag(null);
        this.n0.setTag(null);
        this.o0.setTag(null);
        this.p0.setTag(null);
        this.q0.setTag(null);
        this.r0.setTag(null);
        this.s0.setTag(null);
        this.t0.setTag(null);
        this.u0.setTag(null);
        this.v0.setTag(null);
        this.w0.setTag(null);
        this.x0.setTag(null);
        this.y0.setTag(null);
        this.z0.setTag(null);
        this.A0.setTag(null);
        this.C0.setTag(null);
        this.D0.setTag(null);
        this.E0.setTag(null);
        this.F0.setTag(null);
        this.G0.setTag(null);
        this.H0.setTag(null);
        this.I0.setTag(null);
        this.J0.setTag(null);
        this.K0.setTag(null);
        this.L0.setTag(null);
        this.M0.setTag(null);
        this.N0.setTag(null);
        this.O0.setTag(null);
        this.P0.setTag(null);
        this.Q0.setTag(null);
        this.R0.setTag(null);
        this.S0.setTag(null);
        this.T0.setTag(null);
        this.U0.setTag(null);
        this.V0.setTag(null);
        this.W0.setTag(null);
        this.X0.setTag(null);
        this.Y0.setTag(null);
        this.h1.setTag(null);
        this.i1.setTag(null);
        this.k1.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
