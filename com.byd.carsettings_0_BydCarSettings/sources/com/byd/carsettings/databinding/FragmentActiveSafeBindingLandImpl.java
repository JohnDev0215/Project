package com.byd.carsettings.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
public class FragmentActiveSafeBindingLandImpl extends FragmentActiveSafeBinding {

    @Nullable
    public static final ViewDataBinding.IncludedLayouts B1 = null;

    @Nullable
    public static final SparseIntArray C1;

    @NonNull
    public final TextView D1;

    @NonNull
    public final LinearLayout E1;

    @NonNull
    public final TextView F1;

    @NonNull
    public final TextView G1;

    @NonNull
    public final ConstraintLayout H1;

    @NonNull
    public final TextView I1;
    public long J1;
    public long K1;
    public long L1;
    public long M1;
    public long N1;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        C1 = sparseIntArray;
        sparseIntArray.put(R.id.tv_auto_parke, 102);
        sparseIntArray.put(R.id.tv_epb, 103);
        sparseIntArray.put(R.id.tv_epb_tips, 104);
        sparseIntArray.put(R.id.lottie_animation_lane_assist_sys, 105);
        sparseIntArray.put(R.id.tv_lane_assist_alarm_mode, 106);
        sparseIntArray.put(R.id.saoc_title, 107);
        sparseIntArray.put(R.id.lottie_animation_collision_prevention_assist, 108);
        sparseIntArray.put(R.id.tv_title_ai_emergency_brake, 109);
        sparseIntArray.put(R.id.tv_rear_crash_alert, 110);
        sparseIntArray.put(R.id.tv_predict_crash_alert, 111);
        sparseIntArray.put(R.id.cl_lateral_approach_assist, 112);
        sparseIntArray.put(R.id.lottie_animation_lateral_assist, 113);
        sparseIntArray.put(R.id.flow_front_warn, 114);
        sparseIntArray.put(R.id.tv_front_lateral_approach_warning, 115);
        sparseIntArray.put(R.id.tv_front_lateral_approach_braking, 116);
        sparseIntArray.put(R.id.tv_rear_lateral_approach_warning, 117);
        sparseIntArray.put(R.id.tv_rear_lateral_approach_braking, 118);
        sparseIntArray.put(R.id.tv_title_traffic_signs_recognition, 119);
        sparseIntArray.put(R.id.lottie_animation_traffic_signs_recogniation, 120);
        sparseIntArray.put(R.id.flow_traffic_signal, 121);
        sparseIntArray.put(R.id.slif_title, 122);
        sparseIntArray.put(R.id.tv_title_traffic_sound_remind_recognition, 123);
        sparseIntArray.put(R.id.tv_intelligent_speed_control_alarm, 124);
        sparseIntArray.put(R.id.tv_intelligent_speed_control, 125);
        sparseIntArray.put(R.id.tv_tsr_flow_title, 126);
        sparseIntArray.put(R.id.tv_limit_ctrl_type, 127);
        sparseIntArray.put(R.id.tv_speed_control_alarm_sensitivity, 128);
        sparseIntArray.put(R.id.tv_bsis, 129);
        sparseIntArray.put(R.id.tv_mois, 130);
        sparseIntArray.put(R.id.tv_title_gas_pedal, 131);
    }

    public FragmentActiveSafeBindingLandImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 132, B1, C1));
    }

    public final boolean A(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 131072;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 512;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 1024;
            }
            return true;
        }
        if (i != 3) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 2048;
        }
        return true;
    }

    public final boolean B(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 67108864;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 8589934592L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 17179869184L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 34359738368L;
        }
        return true;
    }

    public final boolean C(ActiveSafeStateModel activeSafeStateModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 256;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.J1 |= 8796093022208L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.J1 |= 17592186044416L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.J1 |= 35184372088832L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 70368744177664L;
        }
        return true;
    }

    public final boolean D(MutableLiveData<Byte> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 1;
        }
        return true;
    }

    @Override // com.byd.carsettings.databinding.FragmentActiveSafeBinding
    public void c(@Nullable ActiveSafeViewModel activeSafeViewModel) {
        this.A1 = activeSafeViewModel;
        synchronized (this) {
            this.J1 |= 268435456;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    public final boolean d(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 = 8388608 | this.J1;
            }
            return true;
        }
        if (i != 9) {
            return false;
        }
        synchronized (this) {
            this.K1 = 8388608 | this.K1;
        }
        return true;
    }

    public final boolean e(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 4096;
            }
            return true;
        }
        if (i == 10) {
            synchronized (this) {
                this.J1 |= 1152921504606846976L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.J1 |= 2305843009213693952L;
            }
            return true;
        }
        if (i != 1) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 4611686018427387904L;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:1022:0x0ff7  */
    /* JADX WARN: Removed duplicated region for block: B:1023:0x0ff9  */
    /* JADX WARN: Removed duplicated region for block: B:1024:0x1006  */
    /* JADX WARN: Removed duplicated region for block: B:1027:0x1012  */
    /* JADX WARN: Removed duplicated region for block: B:1028:0x1019  */
    /* JADX WARN: Removed duplicated region for block: B:1033:0x102f  */
    /* JADX WARN: Removed duplicated region for block: B:1034:0x1031  */
    /* JADX WARN: Removed duplicated region for block: B:1035:0x1034  */
    /* JADX WARN: Removed duplicated region for block: B:1040:0x1042  */
    /* JADX WARN: Removed duplicated region for block: B:1041:0x1044  */
    /* JADX WARN: Removed duplicated region for block: B:1042:0x1047  */
    /* JADX WARN: Removed duplicated region for block: B:1044:0x1050  */
    /* JADX WARN: Removed duplicated region for block: B:1048:0x105c  */
    /* JADX WARN: Removed duplicated region for block: B:1050:0x1060  */
    /* JADX WARN: Removed duplicated region for block: B:1051:0x1063  */
    /* JADX WARN: Removed duplicated region for block: B:1058:0x1086  */
    /* JADX WARN: Removed duplicated region for block: B:1059:0x1088  */
    /* JADX WARN: Removed duplicated region for block: B:1060:0x1095  */
    /* JADX WARN: Removed duplicated region for block: B:1067:0x10b1  */
    /* JADX WARN: Removed duplicated region for block: B:1068:0x10b3  */
    /* JADX WARN: Removed duplicated region for block: B:1069:0x10b8  */
    /* JADX WARN: Removed duplicated region for block: B:1076:0x10d5  */
    /* JADX WARN: Removed duplicated region for block: B:1077:0x10d7  */
    /* JADX WARN: Removed duplicated region for block: B:1078:0x10dc  */
    /* JADX WARN: Removed duplicated region for block: B:1083:0x10ef  */
    /* JADX WARN: Removed duplicated region for block: B:1084:0x10f1  */
    /* JADX WARN: Removed duplicated region for block: B:1085:0x10f7  */
    /* JADX WARN: Removed duplicated region for block: B:1088:0x1100  */
    /* JADX WARN: Removed duplicated region for block: B:1089:0x1103  */
    /* JADX WARN: Removed duplicated region for block: B:1099:0x1127  */
    /* JADX WARN: Removed duplicated region for block: B:1102:0x1130 A[PHI: r8
  0x1130: PHI (r8v59 long) = (r8v58 long), (r8v56 long) binds: [B:1114:0x114c, B:1100:0x112d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1103:0x1132  */
    /* JADX WARN: Removed duplicated region for block: B:1105:0x1136  */
    /* JADX WARN: Removed duplicated region for block: B:1106:0x1138  */
    /* JADX WARN: Removed duplicated region for block: B:1108:0x113b  */
    /* JADX WARN: Removed duplicated region for block: B:1110:0x1141  */
    /* JADX WARN: Removed duplicated region for block: B:1111:0x1143  */
    /* JADX WARN: Removed duplicated region for block: B:1112:0x1147  */
    /* JADX WARN: Removed duplicated region for block: B:1115:0x114e  */
    /* JADX WARN: Removed duplicated region for block: B:1123:0x1170  */
    /* JADX WARN: Removed duplicated region for block: B:1124:0x1172  */
    /* JADX WARN: Removed duplicated region for block: B:1125:0x117f  */
    /* JADX WARN: Removed duplicated region for block: B:1128:0x118b  */
    /* JADX WARN: Removed duplicated region for block: B:1131:0x1194 A[PHI: r8
  0x1194: PHI (r8v65 long) = (r8v63 long), (r8v60 long) binds: [B:1142:0x11ae, B:1129:0x1191] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1132:0x1196  */
    /* JADX WARN: Removed duplicated region for block: B:1134:0x119a  */
    /* JADX WARN: Removed duplicated region for block: B:1135:0x119c  */
    /* JADX WARN: Removed duplicated region for block: B:1139:0x11a5  */
    /* JADX WARN: Removed duplicated region for block: B:1140:0x11a7  */
    /* JADX WARN: Removed duplicated region for block: B:1141:0x11aa  */
    /* JADX WARN: Removed duplicated region for block: B:1144:0x11b1  */
    /* JADX WARN: Removed duplicated region for block: B:1146:0x11b5  */
    /* JADX WARN: Removed duplicated region for block: B:1149:0x11c4  */
    /* JADX WARN: Removed duplicated region for block: B:1153:0x11de  */
    /* JADX WARN: Removed duplicated region for block: B:1154:0x11e0  */
    /* JADX WARN: Removed duplicated region for block: B:1155:0x11ed  */
    /* JADX WARN: Removed duplicated region for block: B:1162:0x120b  */
    /* JADX WARN: Removed duplicated region for block: B:1163:0x120d  */
    /* JADX WARN: Removed duplicated region for block: B:1164:0x1212  */
    /* JADX WARN: Removed duplicated region for block: B:1168:0x1222  */
    /* JADX WARN: Removed duplicated region for block: B:1169:0x1224  */
    /* JADX WARN: Removed duplicated region for block: B:1170:0x122a  */
    /* JADX WARN: Removed duplicated region for block: B:1173:0x1233  */
    /* JADX WARN: Removed duplicated region for block: B:1174:0x1236  */
    /* JADX WARN: Removed duplicated region for block: B:1182:0x1252  */
    /* JADX WARN: Removed duplicated region for block: B:1185:0x125b A[PHI: r8
  0x125b: PHI (r8v72 long) = (r8v71 long), (r8v69 long) binds: [B:1197:0x1278, B:1183:0x1258] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1186:0x125e  */
    /* JADX WARN: Removed duplicated region for block: B:1188:0x1262  */
    /* JADX WARN: Removed duplicated region for block: B:1189:0x1265  */
    /* JADX WARN: Removed duplicated region for block: B:1191:0x1269  */
    /* JADX WARN: Removed duplicated region for block: B:1193:0x126f  */
    /* JADX WARN: Removed duplicated region for block: B:1194:0x1271  */
    /* JADX WARN: Removed duplicated region for block: B:1195:0x1274  */
    /* JADX WARN: Removed duplicated region for block: B:1199:0x127b  */
    /* JADX WARN: Removed duplicated region for block: B:1206:0x129b  */
    /* JADX WARN: Removed duplicated region for block: B:1207:0x129d  */
    /* JADX WARN: Removed duplicated region for block: B:1208:0x12aa  */
    /* JADX WARN: Removed duplicated region for block: B:1211:0x12b6  */
    /* JADX WARN: Removed duplicated region for block: B:1214:0x12bf A[PHI: r8
  0x12bf: PHI (r8v78 long) = (r8v76 long), (r8v73 long) binds: [B:1225:0x12dc, B:1212:0x12bc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1215:0x12c1  */
    /* JADX WARN: Removed duplicated region for block: B:1217:0x12c5  */
    /* JADX WARN: Removed duplicated region for block: B:1218:0x12c7  */
    /* JADX WARN: Removed duplicated region for block: B:1222:0x12d0  */
    /* JADX WARN: Removed duplicated region for block: B:1223:0x12d2  */
    /* JADX WARN: Removed duplicated region for block: B:1224:0x12da  */
    /* JADX WARN: Removed duplicated region for block: B:1227:0x12df  */
    /* JADX WARN: Removed duplicated region for block: B:1229:0x12e3  */
    /* JADX WARN: Removed duplicated region for block: B:1230:0x12e8  */
    /* JADX WARN: Removed duplicated region for block: B:1237:0x130f  */
    /* JADX WARN: Removed duplicated region for block: B:1238:0x1311  */
    /* JADX WARN: Removed duplicated region for block: B:1239:0x131e  */
    /* JADX WARN: Removed duplicated region for block: B:1245:0x133a A[PHI: r8
  0x133a: PHI (r8v90 long) = (r8v81 long), (r8v79 long) binds: [B:1256:0x1357, B:1243:0x1337] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1246:0x133d  */
    /* JADX WARN: Removed duplicated region for block: B:1247:0x133f  */
    /* JADX WARN: Removed duplicated region for block: B:1248:0x1342  */
    /* JADX WARN: Removed duplicated region for block: B:1252:0x134c  */
    /* JADX WARN: Removed duplicated region for block: B:1253:0x134e  */
    /* JADX WARN: Removed duplicated region for block: B:1254:0x1352  */
    /* JADX WARN: Removed duplicated region for block: B:1258:0x135a  */
    /* JADX WARN: Removed duplicated region for block: B:1264:0x136f  */
    /* JADX WARN: Removed duplicated region for block: B:1265:0x1371  */
    /* JADX WARN: Removed duplicated region for block: B:1266:0x1376  */
    /* JADX WARN: Removed duplicated region for block: B:1271:0x1389  */
    /* JADX WARN: Removed duplicated region for block: B:1272:0x138b  */
    /* JADX WARN: Removed duplicated region for block: B:1273:0x138e  */
    /* JADX WARN: Removed duplicated region for block: B:1275:0x1397  */
    /* JADX WARN: Removed duplicated region for block: B:1276:0x139a  */
    /* JADX WARN: Removed duplicated region for block: B:1282:0x13af  */
    /* JADX WARN: Removed duplicated region for block: B:1283:0x13b1  */
    /* JADX WARN: Removed duplicated region for block: B:1284:0x13b6  */
    /* JADX WARN: Removed duplicated region for block: B:1289:0x13c9  */
    /* JADX WARN: Removed duplicated region for block: B:1290:0x13cb  */
    /* JADX WARN: Removed duplicated region for block: B:1291:0x13ce  */
    /* JADX WARN: Removed duplicated region for block: B:1297:0x13e6  */
    /* JADX WARN: Removed duplicated region for block: B:1298:0x13e8  */
    /* JADX WARN: Removed duplicated region for block: B:1299:0x13ed  */
    /* JADX WARN: Removed duplicated region for block: B:1309:0x141b  */
    /* JADX WARN: Removed duplicated region for block: B:1313:0x1435  */
    /* JADX WARN: Removed duplicated region for block: B:1314:0x1437  */
    /* JADX WARN: Removed duplicated region for block: B:1315:0x1444  */
    /* JADX WARN: Removed duplicated region for block: B:1321:0x1460 A[PHI: r10
  0x1460: PHI (r10v58 long) = (r10v51 long), (r10v49 long) binds: [B:1332:0x1481, B:1319:0x145d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1322:0x1463  */
    /* JADX WARN: Removed duplicated region for block: B:1323:0x1465  */
    /* JADX WARN: Removed duplicated region for block: B:1324:0x1468  */
    /* JADX WARN: Removed duplicated region for block: B:1328:0x1472  */
    /* JADX WARN: Removed duplicated region for block: B:1329:0x1474  */
    /* JADX WARN: Removed duplicated region for block: B:1330:0x147a  */
    /* JADX WARN: Removed duplicated region for block: B:1334:0x1484  */
    /* JADX WARN: Removed duplicated region for block: B:1340:0x1499  */
    /* JADX WARN: Removed duplicated region for block: B:1341:0x149b  */
    /* JADX WARN: Removed duplicated region for block: B:1342:0x14a0  */
    /* JADX WARN: Removed duplicated region for block: B:1347:0x14b3  */
    /* JADX WARN: Removed duplicated region for block: B:1348:0x14b5  */
    /* JADX WARN: Removed duplicated region for block: B:1349:0x14b8  */
    /* JADX WARN: Removed duplicated region for block: B:1352:0x14be  */
    /* JADX WARN: Removed duplicated region for block: B:1353:0x14c1  */
    /* JADX WARN: Removed duplicated region for block: B:1361:0x14dc  */
    /* JADX WARN: Removed duplicated region for block: B:1365:0x14ed  */
    /* JADX WARN: Removed duplicated region for block: B:1366:0x14ef  */
    /* JADX WARN: Removed duplicated region for block: B:1367:0x14f4  */
    /* JADX WARN: Removed duplicated region for block: B:1372:0x1507  */
    /* JADX WARN: Removed duplicated region for block: B:1373:0x1509  */
    /* JADX WARN: Removed duplicated region for block: B:1374:0x150c  */
    /* JADX WARN: Removed duplicated region for block: B:1381:0x1522  */
    /* JADX WARN: Removed duplicated region for block: B:1382:0x1524  */
    /* JADX WARN: Removed duplicated region for block: B:1383:0x1529  */
    /* JADX WARN: Removed duplicated region for block: B:1391:0x154c  */
    /* JADX WARN: Removed duplicated region for block: B:1392:0x154e  */
    /* JADX WARN: Removed duplicated region for block: B:1393:0x155b  */
    /* JADX WARN: Removed duplicated region for block: B:1396:0x1567  */
    /* JADX WARN: Removed duplicated region for block: B:1397:0x156e  */
    /* JADX WARN: Removed duplicated region for block: B:1399:0x1571  */
    /* JADX WARN: Removed duplicated region for block: B:1400:0x1573  */
    /* JADX WARN: Removed duplicated region for block: B:1405:0x1582  */
    /* JADX WARN: Removed duplicated region for block: B:1406:0x1584  */
    /* JADX WARN: Removed duplicated region for block: B:1407:0x1587  */
    /* JADX WARN: Removed duplicated region for block: B:1410:0x158d  */
    /* JADX WARN: Removed duplicated region for block: B:1411:0x158f  */
    /* JADX WARN: Removed duplicated region for block: B:1414:0x1594  */
    /* JADX WARN: Removed duplicated region for block: B:1415:0x1596  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:1422:0x15be  */
    /* JADX WARN: Removed duplicated region for block: B:1423:0x15c0  */
    /* JADX WARN: Removed duplicated region for block: B:1424:0x15c7  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:1431:0x15e9  */
    /* JADX WARN: Removed duplicated region for block: B:1432:0x15eb  */
    /* JADX WARN: Removed duplicated region for block: B:1433:0x15f0  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:1440:0x160f  */
    /* JADX WARN: Removed duplicated region for block: B:1441:0x1611  */
    /* JADX WARN: Removed duplicated region for block: B:1442:0x1616  */
    /* JADX WARN: Removed duplicated region for block: B:1447:0x1629  */
    /* JADX WARN: Removed duplicated region for block: B:1448:0x162b  */
    /* JADX WARN: Removed duplicated region for block: B:1449:0x162f  */
    /* JADX WARN: Removed duplicated region for block: B:1452:0x1636  */
    /* JADX WARN: Removed duplicated region for block: B:1453:0x1639  */
    /* JADX WARN: Removed duplicated region for block: B:1459:0x164e  */
    /* JADX WARN: Removed duplicated region for block: B:1460:0x1650  */
    /* JADX WARN: Removed duplicated region for block: B:1461:0x1655  */
    /* JADX WARN: Removed duplicated region for block: B:1466:0x1668  */
    /* JADX WARN: Removed duplicated region for block: B:1467:0x166a  */
    /* JADX WARN: Removed duplicated region for block: B:1468:0x166d  */
    /* JADX WARN: Removed duplicated region for block: B:1478:0x169c  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:1482:0x16a6  */
    /* JADX WARN: Removed duplicated region for block: B:1483:0x16a8  */
    /* JADX WARN: Removed duplicated region for block: B:1484:0x16aa  */
    /* JADX WARN: Removed duplicated region for block: B:1488:0x16b3  */
    /* JADX WARN: Removed duplicated region for block: B:1489:0x16b5  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x034f  */
    /* JADX WARN: Removed duplicated region for block: B:1490:0x16bd  */
    /* JADX WARN: Removed duplicated region for block: B:1494:0x16c9  */
    /* JADX WARN: Removed duplicated region for block: B:1495:0x16cb  */
    /* JADX WARN: Removed duplicated region for block: B:1496:0x16ce  */
    /* JADX WARN: Removed duplicated region for block: B:1499:0x16d4  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:1502:0x16dd A[PHI: r16
  0x16dd: PHI (r16v139 long) = (r16v138 long), (r16v140 long) binds: [B:1504:0x16e1, B:1500:0x16da] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1503:0x16df  */
    /* JADX WARN: Removed duplicated region for block: B:1506:0x16e4  */
    /* JADX WARN: Removed duplicated region for block: B:1513:0x1713  */
    /* JADX WARN: Removed duplicated region for block: B:1514:0x1715  */
    /* JADX WARN: Removed duplicated region for block: B:1515:0x1722  */
    /* JADX WARN: Removed duplicated region for block: B:1522:0x1745  */
    /* JADX WARN: Removed duplicated region for block: B:1523:0x1747  */
    /* JADX WARN: Removed duplicated region for block: B:1524:0x1753  */
    /* JADX WARN: Removed duplicated region for block: B:1526:0x1759  */
    /* JADX WARN: Removed duplicated region for block: B:1527:0x175c  */
    /* JADX WARN: Removed duplicated region for block: B:1529:0x1760  */
    /* JADX WARN: Removed duplicated region for block: B:1530:0x1762  */
    /* JADX WARN: Removed duplicated region for block: B:1534:0x176d  */
    /* JADX WARN: Removed duplicated region for block: B:1535:0x176f  */
    /* JADX WARN: Removed duplicated region for block: B:1536:0x1773  */
    /* JADX WARN: Removed duplicated region for block: B:1541:0x1782  */
    /* JADX WARN: Removed duplicated region for block: B:1542:0x1784  */
    /* JADX WARN: Removed duplicated region for block: B:1543:0x178a  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:1553:0x17b3  */
    /* JADX WARN: Removed duplicated region for block: B:1557:0x17c2  */
    /* JADX WARN: Removed duplicated region for block: B:1558:0x17c4  */
    /* JADX WARN: Removed duplicated region for block: B:1559:0x17c9  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:1564:0x17dc  */
    /* JADX WARN: Removed duplicated region for block: B:1565:0x17de  */
    /* JADX WARN: Removed duplicated region for block: B:1566:0x17e1  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x037b  */
    /* JADX WARN: Removed duplicated region for block: B:1573:0x17f8  */
    /* JADX WARN: Removed duplicated region for block: B:1574:0x17fa  */
    /* JADX WARN: Removed duplicated region for block: B:1575:0x17ff  */
    /* JADX WARN: Removed duplicated region for block: B:1580:0x1812  */
    /* JADX WARN: Removed duplicated region for block: B:1581:0x1814  */
    /* JADX WARN: Removed duplicated region for block: B:1582:0x1817  */
    /* JADX WARN: Removed duplicated region for block: B:1585:0x181d  */
    /* JADX WARN: Removed duplicated region for block: B:1586:0x1820  */
    /* JADX WARN: Removed duplicated region for block: B:1593:0x1837  */
    /* JADX WARN: Removed duplicated region for block: B:1594:0x1839  */
    /* JADX WARN: Removed duplicated region for block: B:1595:0x183c  */
    /* JADX WARN: Removed duplicated region for block: B:1599:0x1846  */
    /* JADX WARN: Removed duplicated region for block: B:1600:0x1848  */
    /* JADX WARN: Removed duplicated region for block: B:1601:0x184b  */
    /* JADX WARN: Removed duplicated region for block: B:1608:0x1862  */
    /* JADX WARN: Removed duplicated region for block: B:1609:0x1864  */
    /* JADX WARN: Removed duplicated region for block: B:1610:0x1869  */
    /* JADX WARN: Removed duplicated region for block: B:1619:0x1890  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0389  */
    /* JADX WARN: Removed duplicated region for block: B:1622:0x1895  */
    /* JADX WARN: Removed duplicated region for block: B:1625:0x189d  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:1632:0x197a  */
    /* JADX WARN: Removed duplicated region for block: B:1633:0x197c  */
    /* JADX WARN: Removed duplicated region for block: B:1634:0x1989  */
    /* JADX WARN: Removed duplicated region for block: B:1637:0x1995  */
    /* JADX WARN: Removed duplicated region for block: B:1638:0x1997  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:1642:0x19a2  */
    /* JADX WARN: Removed duplicated region for block: B:1643:0x19a4  */
    /* JADX WARN: Removed duplicated region for block: B:1644:0x19a7  */
    /* JADX WARN: Removed duplicated region for block: B:1646:0x19b0  */
    /* JADX WARN: Removed duplicated region for block: B:1647:0x19b2  */
    /* JADX WARN: Removed duplicated region for block: B:1651:0x1a8f  */
    /* JADX WARN: Removed duplicated region for block: B:1655:0x1a99  */
    /* JADX WARN: Removed duplicated region for block: B:1656:0x1a9b  */
    /* JADX WARN: Removed duplicated region for block: B:1657:0x1a9e  */
    /* JADX WARN: Removed duplicated region for block: B:1661:0x1aa8  */
    /* JADX WARN: Removed duplicated region for block: B:1667:0x1ac4  */
    /* JADX WARN: Removed duplicated region for block: B:1671:0x1ad6  */
    /* JADX WARN: Removed duplicated region for block: B:1675:0x1ae1  */
    /* JADX WARN: Removed duplicated region for block: B:1676:0x1ae3  */
    /* JADX WARN: Removed duplicated region for block: B:1677:0x1ae6  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x039c  */
    /* JADX WARN: Removed duplicated region for block: B:1681:0x1af2  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x03a8  */
    /* JADX WARN: Removed duplicated region for block: B:1695:0x1b24  */
    /* JADX WARN: Removed duplicated region for block: B:1698:0x1b35  */
    /* JADX WARN: Removed duplicated region for block: B:1702:0x1b45  */
    /* JADX WARN: Removed duplicated region for block: B:1703:0x1b47  */
    /* JADX WARN: Removed duplicated region for block: B:1704:0x1b4a  */
    /* JADX WARN: Removed duplicated region for block: B:1708:0x1b56  */
    /* JADX WARN: Removed duplicated region for block: B:1714:0x1b72  */
    /* JADX WARN: Removed duplicated region for block: B:1718:0x1b7f  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:1723:0x1b94  */
    /* JADX WARN: Removed duplicated region for block: B:1727:0x1ba1  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03c9  */
    /* JADX WARN: Removed duplicated region for block: B:1741:0x1bd7  */
    /* JADX WARN: Removed duplicated region for block: B:1744:0x1be3  */
    /* JADX WARN: Removed duplicated region for block: B:1749:0x1bf4  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:1752:0x1c02  */
    /* JADX WARN: Removed duplicated region for block: B:1756:0x1c0d  */
    /* JADX WARN: Removed duplicated region for block: B:1757:0x1c0f  */
    /* JADX WARN: Removed duplicated region for block: B:1758:0x1c12  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:1762:0x1c1e  */
    /* JADX WARN: Removed duplicated region for block: B:1768:0x1c3b  */
    /* JADX WARN: Removed duplicated region for block: B:1772:0x1c4b  */
    /* JADX WARN: Removed duplicated region for block: B:1776:0x1c5b  */
    /* JADX WARN: Removed duplicated region for block: B:1777:0x1c5d  */
    /* JADX WARN: Removed duplicated region for block: B:1778:0x1c60  */
    /* JADX WARN: Removed duplicated region for block: B:1782:0x1c71  */
    /* JADX WARN: Removed duplicated region for block: B:1786:0x1c7b  */
    /* JADX WARN: Removed duplicated region for block: B:1787:0x1c7d  */
    /* JADX WARN: Removed duplicated region for block: B:1788:0x1c80  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x03eb  */
    /* JADX WARN: Removed duplicated region for block: B:1792:0x1c8c  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:1803:0x1cb8  */
    /* JADX WARN: Removed duplicated region for block: B:1807:0x1cc7  */
    /* JADX WARN: Removed duplicated region for block: B:1811:0x1cd7  */
    /* JADX WARN: Removed duplicated region for block: B:1812:0x1cd9  */
    /* JADX WARN: Removed duplicated region for block: B:1813:0x1cdc  */
    /* JADX WARN: Removed duplicated region for block: B:1817:0x1cea  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x03f2  */
    /* JADX WARN: Removed duplicated region for block: B:1822:0x1cff  */
    /* JADX WARN: Removed duplicated region for block: B:1826:0x1d13  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x03f5  */
    /* JADX WARN: Removed duplicated region for block: B:1839:0x1d44  */
    /* JADX WARN: Removed duplicated region for block: B:1842:0x1d50  */
    /* JADX WARN: Removed duplicated region for block: B:1845:0x1d57  */
    /* JADX WARN: Removed duplicated region for block: B:1848:0x1d61  */
    /* JADX WARN: Removed duplicated region for block: B:1852:0x1d71  */
    /* JADX WARN: Removed duplicated region for block: B:1853:0x1d73  */
    /* JADX WARN: Removed duplicated region for block: B:1854:0x1d76  */
    /* JADX WARN: Removed duplicated region for block: B:1858:0x1d87  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:1862:0x1d92  */
    /* JADX WARN: Removed duplicated region for block: B:1863:0x1d94  */
    /* JADX WARN: Removed duplicated region for block: B:1864:0x1d97  */
    /* JADX WARN: Removed duplicated region for block: B:1868:0x1da6  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:1872:0x1db5  */
    /* JADX WARN: Removed duplicated region for block: B:1873:0x1db7  */
    /* JADX WARN: Removed duplicated region for block: B:1874:0x1dba  */
    /* JADX WARN: Removed duplicated region for block: B:1878:0x1dc7  */
    /* JADX WARN: Removed duplicated region for block: B:1883:0x1ddf  */
    /* JADX WARN: Removed duplicated region for block: B:1887:0x1df1  */
    /* JADX WARN: Removed duplicated region for block: B:1891:0x1dfc  */
    /* JADX WARN: Removed duplicated region for block: B:1892:0x1dfe  */
    /* JADX WARN: Removed duplicated region for block: B:1896:0x1e08  */
    /* JADX WARN: Removed duplicated region for block: B:1897:0x1e0a  */
    /* JADX WARN: Removed duplicated region for block: B:1898:0x1e10  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:1901:0x1e19  */
    /* JADX WARN: Removed duplicated region for block: B:1902:0x1e1c  */
    /* JADX WARN: Removed duplicated region for block: B:1906:0x1e31  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:1910:0x1e3b  */
    /* JADX WARN: Removed duplicated region for block: B:1912:0x1e3e  */
    /* JADX WARN: Removed duplicated region for block: B:1916:0x1e48  */
    /* JADX WARN: Removed duplicated region for block: B:1917:0x1e4a  */
    /* JADX WARN: Removed duplicated region for block: B:1918:0x1e4d  */
    /* JADX WARN: Removed duplicated region for block: B:1922:0x1e5e  */
    /* JADX WARN: Removed duplicated region for block: B:1926:0x1e6d  */
    /* JADX WARN: Removed duplicated region for block: B:1927:0x1e6f  */
    /* JADX WARN: Removed duplicated region for block: B:1928:0x1e72  */
    /* JADX WARN: Removed duplicated region for block: B:1932:0x1e81  */
    /* JADX WARN: Removed duplicated region for block: B:1933:0x1e83  */
    /* JADX WARN: Removed duplicated region for block: B:1934:0x1e86  */
    /* JADX WARN: Removed duplicated region for block: B:1937:0x1e98  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x041b  */
    /* JADX WARN: Removed duplicated region for block: B:1941:0x1ea2  */
    /* JADX WARN: Removed duplicated region for block: B:1943:0x1ea5  */
    /* JADX WARN: Removed duplicated region for block: B:1947:0x1eaf  */
    /* JADX WARN: Removed duplicated region for block: B:1948:0x1eb1  */
    /* JADX WARN: Removed duplicated region for block: B:1949:0x1eb4  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:1952:0x1ec6  */
    /* JADX WARN: Removed duplicated region for block: B:1956:0x1ed1  */
    /* JADX WARN: Removed duplicated region for block: B:1957:0x1ed3  */
    /* JADX WARN: Removed duplicated region for block: B:1961:0x1edd  */
    /* JADX WARN: Removed duplicated region for block: B:1962:0x1edf  */
    /* JADX WARN: Removed duplicated region for block: B:1963:0x1ee3  */
    /* JADX WARN: Removed duplicated region for block: B:1966:0x1eea  */
    /* JADX WARN: Removed duplicated region for block: B:1967:0x1eed  */
    /* JADX WARN: Removed duplicated region for block: B:1971:0x1efe  */
    /* JADX WARN: Removed duplicated region for block: B:1975:0x1f09  */
    /* JADX WARN: Removed duplicated region for block: B:1976:0x1f0b  */
    /* JADX WARN: Removed duplicated region for block: B:1980:0x1f15  */
    /* JADX WARN: Removed duplicated region for block: B:1981:0x1f17  */
    /* JADX WARN: Removed duplicated region for block: B:1982:0x1f1b  */
    /* JADX WARN: Removed duplicated region for block: B:1985:0x1f22  */
    /* JADX WARN: Removed duplicated region for block: B:1986:0x1f25  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:1990:0x1f36  */
    /* JADX WARN: Removed duplicated region for block: B:1994:0x1f44  */
    /* JADX WARN: Removed duplicated region for block: B:1996:0x1f47  */
    /* JADX WARN: Removed duplicated region for block: B:1998:0x1f4b  */
    /* JADX WARN: Removed duplicated region for block: B:1999:0x1f5d  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:2003:0x1f6d  */
    /* JADX WARN: Removed duplicated region for block: B:2004:0x1f6f  */
    /* JADX WARN: Removed duplicated region for block: B:2005:0x1f75  */
    /* JADX WARN: Removed duplicated region for block: B:2008:0x1f7e  */
    /* JADX WARN: Removed duplicated region for block: B:2009:0x1f80  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x042e  */
    /* JADX WARN: Removed duplicated region for block: B:2012:0x1f8f  */
    /* JADX WARN: Removed duplicated region for block: B:2016:0x1f9d  */
    /* JADX WARN: Removed duplicated region for block: B:2018:0x1fa0  */
    /* JADX WARN: Removed duplicated region for block: B:2022:0x1faa  */
    /* JADX WARN: Removed duplicated region for block: B:2023:0x1fac  */
    /* JADX WARN: Removed duplicated region for block: B:2024:0x1fb2  */
    /* JADX WARN: Removed duplicated region for block: B:2027:0x1fbb  */
    /* JADX WARN: Removed duplicated region for block: B:2028:0x1fbe  */
    /* JADX WARN: Removed duplicated region for block: B:2032:0x1fd3  */
    /* JADX WARN: Removed duplicated region for block: B:2036:0x1fe3  */
    /* JADX WARN: Removed duplicated region for block: B:2038:0x1fe6  */
    /* JADX WARN: Removed duplicated region for block: B:2041:0x1fee  */
    /* JADX WARN: Removed duplicated region for block: B:2045:0x2001  */
    /* JADX WARN: Removed duplicated region for block: B:2048:0x2006  */
    /* JADX WARN: Removed duplicated region for block: B:2050:0x200a  */
    /* JADX WARN: Removed duplicated region for block: B:2052:0x2015  */
    /* JADX WARN: Removed duplicated region for block: B:2053:0x2017  */
    /* JADX WARN: Removed duplicated region for block: B:2054:0x201a  */
    /* JADX WARN: Removed duplicated region for block: B:2057:0x2020  */
    /* JADX WARN: Removed duplicated region for block: B:2058:0x2023  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x043d  */
    /* JADX WARN: Removed duplicated region for block: B:2062:0x2036  */
    /* JADX WARN: Removed duplicated region for block: B:2066:0x2040  */
    /* JADX WARN: Removed duplicated region for block: B:2068:0x2043  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:2072:0x204d  */
    /* JADX WARN: Removed duplicated region for block: B:2073:0x204f  */
    /* JADX WARN: Removed duplicated region for block: B:2074:0x2052  */
    /* JADX WARN: Removed duplicated region for block: B:2078:0x2063  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0442  */
    /* JADX WARN: Removed duplicated region for block: B:2082:0x206d  */
    /* JADX WARN: Removed duplicated region for block: B:2084:0x2070  */
    /* JADX WARN: Removed duplicated region for block: B:2088:0x207a  */
    /* JADX WARN: Removed duplicated region for block: B:2089:0x207c  */
    /* JADX WARN: Removed duplicated region for block: B:2090:0x207f  */
    /* JADX WARN: Removed duplicated region for block: B:2094:0x208d  */
    /* JADX WARN: Removed duplicated region for block: B:2100:0x20a8  */
    /* JADX WARN: Removed duplicated region for block: B:2101:0x20aa  */
    /* JADX WARN: Removed duplicated region for block: B:2104:0x20bb  */
    /* JADX WARN: Removed duplicated region for block: B:2107:0x20c9  */
    /* JADX WARN: Removed duplicated region for block: B:2109:0x20cd  */
    /* JADX WARN: Removed duplicated region for block: B:2114:0x20e2  */
    /* JADX WARN: Removed duplicated region for block: B:2115:0x20e4  */
    /* JADX WARN: Removed duplicated region for block: B:2116:0x20e7  */
    /* JADX WARN: Removed duplicated region for block: B:2119:0x20f3 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2123:0x20ff  */
    /* JADX WARN: Removed duplicated region for block: B:2127:0x2108  */
    /* JADX WARN: Removed duplicated region for block: B:2128:0x210a  */
    /* JADX WARN: Removed duplicated region for block: B:2129:0x210c  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:2133:0x2115  */
    /* JADX WARN: Removed duplicated region for block: B:2134:0x2117  */
    /* JADX WARN: Removed duplicated region for block: B:2135:0x211b  */
    /* JADX WARN: Removed duplicated region for block: B:2138:0x2122  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:2142:0x212e  */
    /* JADX WARN: Removed duplicated region for block: B:2144:0x2132  */
    /* JADX WARN: Removed duplicated region for block: B:2145:0x2135  */
    /* JADX WARN: Removed duplicated region for block: B:2147:0x2139  */
    /* JADX WARN: Removed duplicated region for block: B:2149:0x213f  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0458  */
    /* JADX WARN: Removed duplicated region for block: B:2150:0x2141  */
    /* JADX WARN: Removed duplicated region for block: B:2151:0x2144  */
    /* JADX WARN: Removed duplicated region for block: B:2154:0x214a  */
    /* JADX WARN: Removed duplicated region for block: B:2158:0x2156  */
    /* JADX WARN: Removed duplicated region for block: B:2160:0x215a  */
    /* JADX WARN: Removed duplicated region for block: B:2161:0x215d  */
    /* JADX WARN: Removed duplicated region for block: B:2163:0x2161  */
    /* JADX WARN: Removed duplicated region for block: B:2165:0x2167  */
    /* JADX WARN: Removed duplicated region for block: B:2166:0x2169  */
    /* JADX WARN: Removed duplicated region for block: B:2167:0x216c  */
    /* JADX WARN: Removed duplicated region for block: B:2170:0x2172  */
    /* JADX WARN: Removed duplicated region for block: B:2174:0x217e  */
    /* JADX WARN: Removed duplicated region for block: B:2176:0x2182  */
    /* JADX WARN: Removed duplicated region for block: B:2177:0x2185  */
    /* JADX WARN: Removed duplicated region for block: B:2179:0x2189  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:2181:0x218f  */
    /* JADX WARN: Removed duplicated region for block: B:2183:0x2192  */
    /* JADX WARN: Removed duplicated region for block: B:2186:0x219b  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:2190:0x21ae  */
    /* JADX WARN: Removed duplicated region for block: B:2192:0x21b2  */
    /* JADX WARN: Removed duplicated region for block: B:2193:0x21b5  */
    /* JADX WARN: Removed duplicated region for block: B:2196:0x21c2  */
    /* JADX WARN: Removed duplicated region for block: B:2198:0x21c8  */
    /* JADX WARN: Removed duplicated region for block: B:2199:0x21ca  */
    /* JADX WARN: Removed duplicated region for block: B:2200:0x21d0  */
    /* JADX WARN: Removed duplicated region for block: B:2206:0x21ea  */
    /* JADX WARN: Removed duplicated region for block: B:2207:0x21ec  */
    /* JADX WARN: Removed duplicated region for block: B:2208:0x21ef  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0468  */
    /* JADX WARN: Removed duplicated region for block: B:2214:0x220b  */
    /* JADX WARN: Removed duplicated region for block: B:2215:0x220d  */
    /* JADX WARN: Removed duplicated region for block: B:2216:0x2210  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x046b  */
    /* JADX WARN: Removed duplicated region for block: B:2224:0x2232  */
    /* JADX WARN: Removed duplicated region for block: B:2225:0x2234  */
    /* JADX WARN: Removed duplicated region for block: B:2226:0x2238  */
    /* JADX WARN: Removed duplicated region for block: B:2229:0x2240  */
    /* JADX WARN: Removed duplicated region for block: B:2233:0x224c  */
    /* JADX WARN: Removed duplicated region for block: B:2235:0x2250  */
    /* JADX WARN: Removed duplicated region for block: B:2236:0x2253  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x046f  */
    /* JADX WARN: Removed duplicated region for block: B:2240:0x225f  */
    /* JADX WARN: Removed duplicated region for block: B:2246:0x227b  */
    /* JADX WARN: Removed duplicated region for block: B:2249:0x2282  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:2253:0x228e  */
    /* JADX WARN: Removed duplicated region for block: B:2255:0x2292  */
    /* JADX WARN: Removed duplicated region for block: B:2256:0x2295  */
    /* JADX WARN: Removed duplicated region for block: B:2260:0x22a4  */
    /* JADX WARN: Removed duplicated region for block: B:2266:0x22be  */
    /* JADX WARN: Removed duplicated region for block: B:2270:0x22cb  */
    /* JADX WARN: Removed duplicated region for block: B:2276:0x22e6  */
    /* JADX WARN: Removed duplicated region for block: B:2279:0x22eb  */
    /* JADX WARN: Removed duplicated region for block: B:2283:0x22f7  */
    /* JADX WARN: Removed duplicated region for block: B:2285:0x22fb  */
    /* JADX WARN: Removed duplicated region for block: B:2286:0x22fe  */
    /* JADX WARN: Removed duplicated region for block: B:2289:0x2304  */
    /* JADX WARN: Removed duplicated region for block: B:2293:0x230d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2297:0x2317 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2301:0x2326  */
    /* JADX WARN: Removed duplicated region for block: B:2307:0x233f  */
    /* JADX WARN: Removed duplicated region for block: B:2311:0x2351  */
    /* JADX WARN: Removed duplicated region for block: B:2315:0x235d  */
    /* JADX WARN: Removed duplicated region for block: B:2317:0x2360  */
    /* JADX WARN: Removed duplicated region for block: B:2321:0x236a  */
    /* JADX WARN: Removed duplicated region for block: B:2322:0x236c  */
    /* JADX WARN: Removed duplicated region for block: B:2323:0x2372  */
    /* JADX WARN: Removed duplicated region for block: B:2326:0x237b  */
    /* JADX WARN: Removed duplicated region for block: B:2327:0x237e  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:2331:0x2391  */
    /* JADX WARN: Removed duplicated region for block: B:2335:0x239c  */
    /* JADX WARN: Removed duplicated region for block: B:2337:0x239f  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x049c  */
    /* JADX WARN: Removed duplicated region for block: B:2342:0x23b2  */
    /* JADX WARN: Removed duplicated region for block: B:2343:0x23b4  */
    /* JADX WARN: Removed duplicated region for block: B:2344:0x23b8  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x04a9  */
    /* JADX WARN: Removed duplicated region for block: B:2350:0x23d5  */
    /* JADX WARN: Removed duplicated region for block: B:2351:0x23d7  */
    /* JADX WARN: Removed duplicated region for block: B:2352:0x23dd  */
    /* JADX WARN: Removed duplicated region for block: B:2358:0x23f7  */
    /* JADX WARN: Removed duplicated region for block: B:2359:0x23f9  */
    /* JADX WARN: Removed duplicated region for block: B:2360:0x23fe  */
    /* JADX WARN: Removed duplicated region for block: B:2365:0x241c  */
    /* JADX WARN: Removed duplicated region for block: B:2366:0x241e  */
    /* JADX WARN: Removed duplicated region for block: B:2367:0x2421  */
    /* JADX WARN: Removed duplicated region for block: B:2370:0x2435  */
    /* JADX WARN: Removed duplicated region for block: B:2374:0x2441  */
    /* JADX WARN: Removed duplicated region for block: B:2376:0x2444  */
    /* JADX WARN: Removed duplicated region for block: B:2380:0x244e  */
    /* JADX WARN: Removed duplicated region for block: B:2381:0x2450  */
    /* JADX WARN: Removed duplicated region for block: B:2382:0x2453  */
    /* JADX WARN: Removed duplicated region for block: B:2384:0x245a  */
    /* JADX WARN: Removed duplicated region for block: B:2385:0x245d  */
    /* JADX WARN: Removed duplicated region for block: B:2389:0x2470  */
    /* JADX WARN: Removed duplicated region for block: B:2393:0x2481  */
    /* JADX WARN: Removed duplicated region for block: B:2395:0x2484  */
    /* JADX WARN: Removed duplicated region for block: B:2399:0x2493  */
    /* JADX WARN: Removed duplicated region for block: B:2400:0x2495  */
    /* JADX WARN: Removed duplicated region for block: B:2401:0x2499  */
    /* JADX WARN: Removed duplicated region for block: B:2404:0x24a0  */
    /* JADX WARN: Removed duplicated region for block: B:2405:0x24a3  */
    /* JADX WARN: Removed duplicated region for block: B:2409:0x24b6  */
    /* JADX WARN: Removed duplicated region for block: B:2413:0x24c7  */
    /* JADX WARN: Removed duplicated region for block: B:2415:0x24ca  */
    /* JADX WARN: Removed duplicated region for block: B:2419:0x24d9  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:2420:0x24db  */
    /* JADX WARN: Removed duplicated region for block: B:2421:0x24de  */
    /* JADX WARN: Removed duplicated region for block: B:2424:0x24e8  */
    /* JADX WARN: Removed duplicated region for block: B:2428:0x24f8  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x04ca  */
    /* JADX WARN: Removed duplicated region for block: B:2433:0x250f  */
    /* JADX WARN: Removed duplicated region for block: B:2437:0x251f  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x04cd  */
    /* JADX WARN: Removed duplicated region for block: B:2442:0x2534  */
    /* JADX WARN: Removed duplicated region for block: B:2446:0x2544  */
    /* JADX WARN: Removed duplicated region for block: B:2451:0x255a  */
    /* JADX WARN: Removed duplicated region for block: B:2455:0x2565  */
    /* JADX WARN: Removed duplicated region for block: B:2461:0x257c  */
    /* JADX WARN: Removed duplicated region for block: B:2465:0x258c  */
    /* JADX WARN: Removed duplicated region for block: B:2469:0x2597  */
    /* JADX WARN: Removed duplicated region for block: B:2471:0x259a  */
    /* JADX WARN: Removed duplicated region for block: B:2475:0x25ab  */
    /* JADX WARN: Removed duplicated region for block: B:2479:0x25bb  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x04d7  */
    /* JADX WARN: Removed duplicated region for block: B:2481:0x25be  */
    /* JADX WARN: Removed duplicated region for block: B:2485:0x25cf  */
    /* JADX WARN: Removed duplicated region for block: B:2489:0x25da  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:2491:0x25dd  */
    /* JADX WARN: Removed duplicated region for block: B:2495:0x25ee  */
    /* JADX WARN: Removed duplicated region for block: B:2499:0x25fe  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x04dd  */
    /* JADX WARN: Removed duplicated region for block: B:2501:0x2601  */
    /* JADX WARN: Removed duplicated region for block: B:2504:0x2609  */
    /* JADX WARN: Removed duplicated region for block: B:2508:0x261c  */
    /* JADX WARN: Removed duplicated region for block: B:2510:0x2628  */
    /* JADX WARN: Removed duplicated region for block: B:2512:0x2643  */
    /* JADX WARN: Removed duplicated region for block: B:2514:0x2650  */
    /* JADX WARN: Removed duplicated region for block: B:2516:0x2670  */
    /* JADX WARN: Removed duplicated region for block: B:2519:0x267e  */
    /* JADX WARN: Removed duplicated region for block: B:2522:0x2687  */
    /* JADX WARN: Removed duplicated region for block: B:2524:0x2692  */
    /* JADX WARN: Removed duplicated region for block: B:2527:0x269d  */
    /* JADX WARN: Removed duplicated region for block: B:2529:0x26a3  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x04e4  */
    /* JADX WARN: Removed duplicated region for block: B:2531:0x26ee  */
    /* JADX WARN: Removed duplicated region for block: B:2533:0x26f7  */
    /* JADX WARN: Removed duplicated region for block: B:2535:0x26ff  */
    /* JADX WARN: Removed duplicated region for block: B:2538:0x276b  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x04e6  */
    /* JADX WARN: Removed duplicated region for block: B:2540:0x2771  */
    /* JADX WARN: Removed duplicated region for block: B:2543:0x2780  */
    /* JADX WARN: Removed duplicated region for block: B:2545:0x2786  */
    /* JADX WARN: Removed duplicated region for block: B:2548:0x279e  */
    /* JADX WARN: Removed duplicated region for block: B:2550:0x27a4  */
    /* JADX WARN: Removed duplicated region for block: B:2552:0x27ad  */
    /* JADX WARN: Removed duplicated region for block: B:2554:0x27b3  */
    /* JADX WARN: Removed duplicated region for block: B:2557:0x27c9  */
    /* JADX WARN: Removed duplicated region for block: B:2559:0x27cf  */
    /* JADX WARN: Removed duplicated region for block: B:2562:0x27de  */
    /* JADX WARN: Removed duplicated region for block: B:2564:0x27e4  */
    /* JADX WARN: Removed duplicated region for block: B:2566:0x27ed  */
    /* JADX WARN: Removed duplicated region for block: B:2568:0x27f3  */
    /* JADX WARN: Removed duplicated region for block: B:2570:0x27fc  */
    /* JADX WARN: Removed duplicated region for block: B:2572:0x2802  */
    /* JADX WARN: Removed duplicated region for block: B:2575:0x286d  */
    /* JADX WARN: Removed duplicated region for block: B:2577:0x2873  */
    /* JADX WARN: Removed duplicated region for block: B:2580:0x2884  */
    /* JADX WARN: Removed duplicated region for block: B:2582:0x288a  */
    /* JADX WARN: Removed duplicated region for block: B:2585:0x28a2  */
    /* JADX WARN: Removed duplicated region for block: B:2587:0x28a8  */
    /* JADX WARN: Removed duplicated region for block: B:2590:0x28b9  */
    /* JADX WARN: Removed duplicated region for block: B:2592:0x28bf  */
    /* JADX WARN: Removed duplicated region for block: B:2595:0x28ce  */
    /* JADX WARN: Removed duplicated region for block: B:2597:0x28d4  */
    /* JADX WARN: Removed duplicated region for block: B:2600:0x28e5  */
    /* JADX WARN: Removed duplicated region for block: B:2602:0x28eb  */
    /* JADX WARN: Removed duplicated region for block: B:2605:0x28fc  */
    /* JADX WARN: Removed duplicated region for block: B:2607:0x2902  */
    /* JADX WARN: Removed duplicated region for block: B:2610:0x2913  */
    /* JADX WARN: Removed duplicated region for block: B:2612:0x2919  */
    /* JADX WARN: Removed duplicated region for block: B:2615:0x292a  */
    /* JADX WARN: Removed duplicated region for block: B:2617:0x2930  */
    /* JADX WARN: Removed duplicated region for block: B:2620:0x2941  */
    /* JADX WARN: Removed duplicated region for block: B:2622:0x2947  */
    /* JADX WARN: Removed duplicated region for block: B:2625:0x2958  */
    /* JADX WARN: Removed duplicated region for block: B:2627:0x295e  */
    /* JADX WARN: Removed duplicated region for block: B:2630:0x296f  */
    /* JADX WARN: Removed duplicated region for block: B:2632:0x2975  */
    /* JADX WARN: Removed duplicated region for block: B:2635:0x2988  */
    /* JADX WARN: Removed duplicated region for block: B:2637:0x298e  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0511  */
    /* JADX WARN: Removed duplicated region for block: B:2640:0x299f  */
    /* JADX WARN: Removed duplicated region for block: B:2642:0x29a5  */
    /* JADX WARN: Removed duplicated region for block: B:2645:0x29b6  */
    /* JADX WARN: Removed duplicated region for block: B:2647:0x29bc  */
    /* JADX WARN: Removed duplicated region for block: B:2649:0x29cc  */
    /* JADX WARN: Removed duplicated region for block: B:2651:0x29d4  */
    /* JADX WARN: Removed duplicated region for block: B:2654:0x29ea  */
    /* JADX WARN: Removed duplicated region for block: B:2656:0x29f0  */
    /* JADX WARN: Removed duplicated region for block: B:2659:0x2a08  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0517 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2661:0x2a0e  */
    /* JADX WARN: Removed duplicated region for block: B:2663:0x2a1c  */
    /* JADX WARN: Removed duplicated region for block: B:2665:0x2a29  */
    /* JADX WARN: Removed duplicated region for block: B:2669:0x2a3a  */
    /* JADX WARN: Removed duplicated region for block: B:2672:0x2a48  */
    /* JADX WARN: Removed duplicated region for block: B:2675:0x2a53  */
    /* JADX WARN: Removed duplicated region for block: B:2677:0x2a5e  */
    /* JADX WARN: Removed duplicated region for block: B:2680:0x2a6f  */
    /* JADX WARN: Removed duplicated region for block: B:2682:0x2a75  */
    /* JADX WARN: Removed duplicated region for block: B:2684:0x2a7d  */
    /* JADX WARN: Removed duplicated region for block: B:2688:0x2ae2  */
    /* JADX WARN: Removed duplicated region for block: B:2690:0x2ae8  */
    /* JADX WARN: Removed duplicated region for block: B:2695:0x2b0d  */
    /* JADX WARN: Removed duplicated region for block: B:2697:0x2b13  */
    /* JADX WARN: Removed duplicated region for block: B:2700:0x2b35  */
    /* JADX WARN: Removed duplicated region for block: B:2702:0x2b3b  */
    /* JADX WARN: Removed duplicated region for block: B:2705:0x2b5f  */
    /* JADX WARN: Removed duplicated region for block: B:2707:0x2b6a  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x052d  */
    /* JADX WARN: Removed duplicated region for block: B:2711:0x2b8a  */
    /* JADX WARN: Removed duplicated region for block: B:2713:0x2b97  */
    /* JADX WARN: Removed duplicated region for block: B:2715:0x2bb4  */
    /* JADX WARN: Removed duplicated region for block: B:2717:0x2bbc  */
    /* JADX WARN: Removed duplicated region for block: B:2719:0x2bde  */
    /* JADX WARN: Removed duplicated region for block: B:2721:0x2be6  */
    /* JADX WARN: Removed duplicated region for block: B:2724:0x2bfe  */
    /* JADX WARN: Removed duplicated region for block: B:2726:0x2c09  */
    /* JADX WARN: Removed duplicated region for block: B:2729:0x2c1a  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0533 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2731:0x2c20  */
    /* JADX WARN: Removed duplicated region for block: B:2734:0x2c31  */
    /* JADX WARN: Removed duplicated region for block: B:2736:0x2c37  */
    /* JADX WARN: Removed duplicated region for block: B:2739:0x2c59  */
    /* JADX WARN: Removed duplicated region for block: B:2741:0x2c5f  */
    /* JADX WARN: Removed duplicated region for block: B:2743:0x2c68  */
    /* JADX WARN: Removed duplicated region for block: B:2745:0x2c73  */
    /* JADX WARN: Removed duplicated region for block: B:2747:0x2c7c  */
    /* JADX WARN: Removed duplicated region for block: B:2749:0x2c87  */
    /* JADX WARN: Removed duplicated region for block: B:2752:0x2c96  */
    /* JADX WARN: Removed duplicated region for block: B:2754:0x2c9c  */
    /* JADX WARN: Removed duplicated region for block: B:2756:0x2ca7  */
    /* JADX WARN: Removed duplicated region for block: B:2758:0x2cb4  */
    /* JADX WARN: Removed duplicated region for block: B:2762:0x2cc3  */
    /* JADX WARN: Removed duplicated region for block: B:2765:0x2cd1  */
    /* JADX WARN: Removed duplicated region for block: B:2769:0x2ce2  */
    /* JADX WARN: Removed duplicated region for block: B:2771:0x2ce8  */
    /* JADX WARN: Removed duplicated region for block: B:2773:0x2cfe  */
    /* JADX WARN: Removed duplicated region for block: B:2776:0x2d07  */
    /* JADX WARN: Removed duplicated region for block: B:2779:0x2d12  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0549  */
    /* JADX WARN: Removed duplicated region for block: B:2781:0x2d1d  */
    /* JADX WARN: Removed duplicated region for block: B:2785:0x2d2e  */
    /* JADX WARN: Removed duplicated region for block: B:2788:0x2d3c  */
    /* JADX WARN: Removed duplicated region for block: B:2791:0x2d47  */
    /* JADX WARN: Removed duplicated region for block: B:2793:0x2d52  */
    /* JADX WARN: Removed duplicated region for block: B:2795:0x2d5b  */
    /* JADX WARN: Removed duplicated region for block: B:2797:0x2d66  */
    /* JADX WARN: Removed duplicated region for block: B:2799:0x2d6f  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x054f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2801:0x2d7a  */
    /* JADX WARN: Removed duplicated region for block: B:2803:0x2d82  */
    /* JADX WARN: Removed duplicated region for block: B:2806:0x2d92  */
    /* JADX WARN: Removed duplicated region for block: B:2809:0x2da0  */
    /* JADX WARN: Removed duplicated region for block: B:2812:0x2dab  */
    /* JADX WARN: Removed duplicated region for block: B:2814:0x2db6  */
    /* JADX WARN: Removed duplicated region for block: B:2818:0x2dc7  */
    /* JADX WARN: Removed duplicated region for block: B:2821:0x2dd5  */
    /* JADX WARN: Removed duplicated region for block: B:2824:0x2de0  */
    /* JADX WARN: Removed duplicated region for block: B:2826:0x2deb  */
    /* JADX WARN: Removed duplicated region for block: B:2828:0x2df4  */
    /* JADX WARN: Removed duplicated region for block: B:2830:0x2dff  */
    /* JADX WARN: Removed duplicated region for block: B:2832:0x2e08  */
    /* JADX WARN: Removed duplicated region for block: B:2834:0x2e0e  */
    /* JADX WARN: Removed duplicated region for block: B:2836:0x2e16  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0562  */
    /* JADX WARN: Removed duplicated region for block: B:2840:0x2e30  */
    /* JADX WARN: Removed duplicated region for block: B:2842:0x2e36  */
    /* JADX WARN: Removed duplicated region for block: B:2844:0x2e3f  */
    /* JADX WARN: Removed duplicated region for block: B:2846:0x2e4a  */
    /* JADX WARN: Removed duplicated region for block: B:2850:0x2e5b  */
    /* JADX WARN: Removed duplicated region for block: B:2853:0x2e69  */
    /* JADX WARN: Removed duplicated region for block: B:2856:0x2e74  */
    /* JADX WARN: Removed duplicated region for block: B:2858:0x2e7f  */
    /* JADX WARN: Removed duplicated region for block: B:2860:0x2e88  */
    /* JADX WARN: Removed duplicated region for block: B:2862:0x2e93  */
    /* JADX WARN: Removed duplicated region for block: B:2865:0x2ea4  */
    /* JADX WARN: Removed duplicated region for block: B:2867:0x2eaa  */
    /* JADX WARN: Removed duplicated region for block: B:2872:0x2ec5  */
    /* JADX WARN: Removed duplicated region for block: B:2874:0x2ecb  */
    /* JADX WARN: Removed duplicated region for block: B:2877:0x2edc  */
    /* JADX WARN: Removed duplicated region for block: B:2879:0x2ee2  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x057a  */
    /* JADX WARN: Removed duplicated region for block: B:2881:0x2eeb  */
    /* JADX WARN: Removed duplicated region for block: B:2883:0x2ef1  */
    /* JADX WARN: Removed duplicated region for block: B:2886:0x2f00  */
    /* JADX WARN: Removed duplicated region for block: B:2888:0x2f06  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x057c  */
    /* JADX WARN: Removed duplicated region for block: B:2890:0x2f0e  */
    /* JADX WARN: Removed duplicated region for block: B:2893:0x2f28  */
    /* JADX WARN: Removed duplicated region for block: B:2895:0x2f30  */
    /* JADX WARN: Removed duplicated region for block: B:2899:0x2f41  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0589  */
    /* JADX WARN: Removed duplicated region for block: B:2902:0x2f4a  */
    /* JADX WARN: Removed duplicated region for block: B:2905:0x2f55  */
    /* JADX WARN: Removed duplicated region for block: B:2907:0x2f5b  */
    /* JADX WARN: Removed duplicated region for block: B:2909:0x2f64  */
    /* JADX WARN: Removed duplicated region for block: B:2911:0x2f6f  */
    /* JADX WARN: Removed duplicated region for block: B:2915:0x2f80  */
    /* JADX WARN: Removed duplicated region for block: B:2918:0x2f8e  */
    /* JADX WARN: Removed duplicated region for block: B:2921:0x2f99  */
    /* JADX WARN: Removed duplicated region for block: B:2923:0x2f9f  */
    /* JADX WARN: Removed duplicated region for block: B:2925:0x2fa8  */
    /* JADX WARN: Removed duplicated region for block: B:2927:0x2fae  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0594  */
    /* JADX WARN: Removed duplicated region for block: B:2930:0x2fbf  */
    /* JADX WARN: Removed duplicated region for block: B:2932:0x2fc5  */
    /* JADX WARN: Removed duplicated region for block: B:2937:0x2fe0  */
    /* JADX WARN: Removed duplicated region for block: B:2939:0x2fe6  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x059b  */
    /* JADX WARN: Removed duplicated region for block: B:2942:0x2ff7  */
    /* JADX WARN: Removed duplicated region for block: B:2944:0x2ffd  */
    /* JADX WARN: Removed duplicated region for block: B:2946:0x3006  */
    /* JADX WARN: Removed duplicated region for block: B:2948:0x300c  */
    /* JADX WARN: Removed duplicated region for block: B:2951:0x301d  */
    /* JADX WARN: Removed duplicated region for block: B:2953:0x3023  */
    /* JADX WARN: Removed duplicated region for block: B:2958:0x303e  */
    /* JADX WARN: Removed duplicated region for block: B:2960:0x3044  */
    /* JADX WARN: Removed duplicated region for block: B:2963:0x3055  */
    /* JADX WARN: Removed duplicated region for block: B:2965:0x305b  */
    /* JADX WARN: Removed duplicated region for block: B:2967:0x3064  */
    /* JADX WARN: Removed duplicated region for block: B:2969:0x306a  */
    /* JADX WARN: Removed duplicated region for block: B:2972:0x307b  */
    /* JADX WARN: Removed duplicated region for block: B:2974:0x3081  */
    /* JADX WARN: Removed duplicated region for block: B:2978:0x3092  */
    /* JADX WARN: Removed duplicated region for block: B:2981:0x30a0  */
    /* JADX WARN: Removed duplicated region for block: B:2984:0x30ab  */
    /* JADX WARN: Removed duplicated region for block: B:2986:0x30b6  */
    /* JADX WARN: Removed duplicated region for block: B:2988:0x30bf  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x05b3  */
    /* JADX WARN: Removed duplicated region for block: B:2990:0x30ca  */
    /* JADX WARN: Removed duplicated region for block: B:2993:0x30db  */
    /* JADX WARN: Removed duplicated region for block: B:2995:0x30e1  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x05b5  */
    /* JADX WARN: Removed duplicated region for block: B:3000:0x30fc  */
    /* JADX WARN: Removed duplicated region for block: B:3002:0x3102  */
    /* JADX WARN: Removed duplicated region for block: B:3005:0x3113  */
    /* JADX WARN: Removed duplicated region for block: B:3007:0x3119  */
    /* JADX WARN: Removed duplicated region for block: B:3009:0x3122  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x05b8  */
    /* JADX WARN: Removed duplicated region for block: B:3011:0x3128  */
    /* JADX WARN: Removed duplicated region for block: B:3013:0x3131  */
    /* JADX WARN: Removed duplicated region for block: B:3015:0x3137  */
    /* JADX WARN: Removed duplicated region for block: B:3019:0x3148  */
    /* JADX WARN: Removed duplicated region for block: B:3022:0x3151  */
    /* JADX WARN: Removed duplicated region for block: B:3025:0x315c  */
    /* JADX WARN: Removed duplicated region for block: B:3027:0x3162  */
    /* JADX WARN: Removed duplicated region for block: B:3030:0x3173  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x05d7  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x05d9  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x05dc  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x05f3  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x05f8  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0612  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0614  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0617  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x062a  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x062c  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x062f  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0646  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0648  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0664  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0669  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0687  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0691  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x0693  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x0696  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x06b3  */
    /* JADX WARN: Removed duplicated region for block: B:370:0x06d2  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x06dc  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x06de  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x06e1  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x06e7  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x06ea  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x0705  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0707  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0714  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x071f  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0721  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x072c  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x072e  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0731  */
    /* JADX WARN: Removed duplicated region for block: B:404:0x0738  */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0747  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x075a  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x075c  */
    /* JADX WARN: Removed duplicated region for block: B:414:0x0769  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0787  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x0789  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x078c  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x0796  */
    /* JADX WARN: Removed duplicated region for block: B:428:0x0798  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x079b  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x07b1 A[PHI: r12
  0x07b1: PHI (r12v24 long) = (r12v22 long), (r12v20 long) binds: [B:447:0x07cb, B:434:0x07ae] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:437:0x07b3  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x07b5  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x07b8  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x07c2  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x07c4  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x07c7  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x07ce  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x07f6  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0813  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x0815  */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0824  */
    /* JADX WARN: Removed duplicated region for block: B:471:0x0844  */
    /* JADX WARN: Removed duplicated region for block: B:472:0x0846  */
    /* JADX WARN: Removed duplicated region for block: B:473:0x084b  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x0857  */
    /* JADX WARN: Removed duplicated region for block: B:479:0x0859  */
    /* JADX WARN: Removed duplicated region for block: B:480:0x085c  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x087b  */
    /* JADX WARN: Removed duplicated region for block: B:490:0x087d  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x0882  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x0895  */
    /* JADX WARN: Removed duplicated region for block: B:497:0x0897  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x089a  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x08bf  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x08c1  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x08c6  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x08d4  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x08d7  */
    /* JADX WARN: Removed duplicated region for block: B:518:0x08e6  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x08f2  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x08f4  */
    /* JADX WARN: Removed duplicated region for block: B:524:0x08f9  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x0917 A[PHI: r12
  0x0917: PHI (r12v35 long) = (r12v30 long), (r12v28 long) binds: [B:542:0x0932, B:529:0x0914] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:532:0x091a  */
    /* JADX WARN: Removed duplicated region for block: B:533:0x091c  */
    /* JADX WARN: Removed duplicated region for block: B:534:0x091f  */
    /* JADX WARN: Removed duplicated region for block: B:538:0x0929  */
    /* JADX WARN: Removed duplicated region for block: B:539:0x092b  */
    /* JADX WARN: Removed duplicated region for block: B:540:0x092e  */
    /* JADX WARN: Removed duplicated region for block: B:544:0x0935  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x094e  */
    /* JADX WARN: Removed duplicated region for block: B:552:0x0950  */
    /* JADX WARN: Removed duplicated region for block: B:553:0x0955  */
    /* JADX WARN: Removed duplicated region for block: B:558:0x0963  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x0965  */
    /* JADX WARN: Removed duplicated region for block: B:560:0x096d  */
    /* JADX WARN: Removed duplicated region for block: B:562:0x0971  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x0974  */
    /* JADX WARN: Removed duplicated region for block: B:578:0x09c3 A[PHI: r8
  0x09c3: PHI (r8v32 long) = (r8v27 long), (r8v25 long) binds: [B:589:0x09dd, B:576:0x09c0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:682:0x0b41  */
    /* JADX WARN: Removed duplicated region for block: B:683:0x0b43  */
    /* JADX WARN: Removed duplicated region for block: B:684:0x0b4b  */
    /* JADX WARN: Removed duplicated region for block: B:687:0x0b53  */
    /* JADX WARN: Removed duplicated region for block: B:688:0x0b55  */
    /* JADX WARN: Removed duplicated region for block: B:691:0x0b59  */
    /* JADX WARN: Removed duplicated region for block: B:692:0x0b5b  */
    /* JADX WARN: Removed duplicated region for block: B:696:0x0b66  */
    /* JADX WARN: Removed duplicated region for block: B:697:0x0b68  */
    /* JADX WARN: Removed duplicated region for block: B:698:0x0b6e  */
    /* JADX WARN: Removed duplicated region for block: B:701:0x0b77  */
    /* JADX WARN: Removed duplicated region for block: B:702:0x0b7a  */
    /* JADX WARN: Removed duplicated region for block: B:708:0x0b8d A[PHI: r6 r8
  0x0b8d: PHI (r6v26 long) = (r6v24 long), (r6v22 long) binds: [B:718:0x0baa, B:706:0x0b8a] A[DONT_GENERATE, DONT_INLINE]
  0x0b8d: PHI (r8v37 long) = (r8v35 long), (r8v33 long) binds: [B:718:0x0baa, B:706:0x0b8a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:709:0x0b90  */
    /* JADX WARN: Removed duplicated region for block: B:710:0x0b92  */
    /* JADX WARN: Removed duplicated region for block: B:711:0x0b95  */
    /* JADX WARN: Removed duplicated region for block: B:715:0x0b9f  */
    /* JADX WARN: Removed duplicated region for block: B:716:0x0ba1  */
    /* JADX WARN: Removed duplicated region for block: B:717:0x0ba6  */
    /* JADX WARN: Removed duplicated region for block: B:720:0x0bad  */
    /* JADX WARN: Removed duplicated region for block: B:727:0x0bc6  */
    /* JADX WARN: Removed duplicated region for block: B:728:0x0bc8  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x0bcd  */
    /* JADX WARN: Removed duplicated region for block: B:737:0x0bf0  */
    /* JADX WARN: Removed duplicated region for block: B:738:0x0bf2  */
    /* JADX WARN: Removed duplicated region for block: B:739:0x0bf7  */
    /* JADX WARN: Removed duplicated region for block: B:744:0x0c05  */
    /* JADX WARN: Removed duplicated region for block: B:745:0x0c07  */
    /* JADX WARN: Removed duplicated region for block: B:746:0x0c0a  */
    /* JADX WARN: Removed duplicated region for block: B:753:0x0c26  */
    /* JADX WARN: Removed duplicated region for block: B:754:0x0c28  */
    /* JADX WARN: Removed duplicated region for block: B:755:0x0c2d  */
    /* JADX WARN: Removed duplicated region for block: B:760:0x0c39  */
    /* JADX WARN: Removed duplicated region for block: B:761:0x0c3b  */
    /* JADX WARN: Removed duplicated region for block: B:762:0x0c41  */
    /* JADX WARN: Removed duplicated region for block: B:765:0x0c4a  */
    /* JADX WARN: Removed duplicated region for block: B:766:0x0c4d  */
    /* JADX WARN: Removed duplicated region for block: B:807:0x0cf8 A[PHI: r10
  0x0cf8: PHI (r10v44 long) = (r10v42 long), (r10v40 long) binds: [B:818:0x0d15, B:805:0x0cf5] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:872:0x0dec A[PHI: r8
  0x0dec: PHI (r8v44 long) = (r8v40 long), (r8v38 long) binds: [B:883:0x0e07, B:870:0x0de9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:958:0x0f33 A[PHI: r8
  0x0f33: PHI (r8v49 long) = (r8v47 long), (r8v45 long) binds: [B:970:0x0f4d, B:956:0x0f30] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:978:0x0f60  */
    /* JADX WARN: Removed duplicated region for block: B:979:0x0f62  */
    /* JADX WARN: Removed duplicated region for block: B:980:0x0f67  */
    /* JADX WARN: Removed duplicated region for block: B:985:0x0f73  */
    /* JADX WARN: Removed duplicated region for block: B:986:0x0f75  */
    /* JADX WARN: Removed duplicated region for block: B:987:0x0f78  */
    /* JADX WARN: Removed duplicated region for block: B:990:0x0f7e  */
    /* JADX WARN: Removed duplicated region for block: B:991:0x0f81  */
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
            Method dump skipped, instruction units count: 12694
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.databinding.FragmentActiveSafeBindingLandImpl.executeBindings():void");
    }

    public final boolean f(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 = 16777216 | this.J1;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 = 16777216 | this.K1;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 33554432;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 67108864;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 134217728;
        }
        return true;
    }

    public final boolean g(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 262144;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 4096;
            }
            return true;
        }
        if (i != 2) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 8192;
        }
        return true;
    }

    public final boolean h(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 = 2097152 | this.J1;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 524288;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 1048576;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 = 2097152 | this.K1;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 4194304;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.J1 == 0 && this.K1 == 0 && this.L1 == 0 && this.M1 == 0 && this.N1 == 0) {
                return false;
            }
            return true;
        }
    }

    public final boolean i(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 128;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.J1 |= 2199023255552L;
            }
            return true;
        }
        if (i != 2) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 4398046511104L;
        }
        return true;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.J1 = 0L;
            this.K1 = 68719476736L;
            this.L1 = 0L;
            this.M1 = 0L;
            this.N1 = 0L;
        }
        requestRebind();
    }

    public final boolean j(ActiveSafeStateModel activeSafeStateModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= SegmentPool.MAX_SIZE;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 64;
            }
            return true;
        }
        if (i == 9) {
            synchronized (this) {
                this.K1 |= 128;
            }
            return true;
        }
        if (i != 1) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 256;
        }
        return true;
    }

    public final boolean k(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 1048576;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 32768;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= SegmentPool.MAX_SIZE;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 131072;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 262144;
        }
        return true;
    }

    public final boolean l(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 2048;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.J1 |= 72057594037927936L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.J1 |= 144115188075855872L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.J1 |= 288230376151711744L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 576460752303423488L;
        }
        return true;
    }

    public final boolean m(MutableLiveData<Byte> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 4194304;
        }
        return true;
    }

    public final boolean n(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 16384;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 8;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 16;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 32;
        }
        return true;
    }

    public final boolean o(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 2;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.J1 |= 2147483648L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.J1 |= 4294967296L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 8589934592L;
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
                this.J1 |= 524288;
            }
            return true;
        }
        if (i != 9) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 16384;
        }
        return true;
    }

    public final boolean q(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 32768;
        }
        return true;
    }

    public final boolean r(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 33554432;
            }
            return true;
        }
        if (i == 10) {
            synchronized (this) {
                this.K1 |= 268435456;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 536870912;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.K1 |= 1073741824;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 2147483648L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 4294967296L;
        }
        return true;
    }

    public final boolean s(MutableLiveData<Byte> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 4;
        }
        return true;
    }

    @Override // com.byd.carsettings.databinding.FragmentActiveSafeBinding
    public void setOnCheckedChangeListener(@Nullable CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.x1 = onCheckedChangeListener;
        synchronized (this) {
            this.J1 |= 134217728;
        }
        notifyPropertyChanged(6);
        super.requestRebind();
    }

    @Override // com.byd.carsettings.databinding.FragmentActiveSafeBinding
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.z1 = onClickListener;
        synchronized (this) {
            this.J1 |= 536870912;
        }
        notifyPropertyChanged(7);
        super.requestRebind();
    }

    @Override // com.byd.carsettings.databinding.FragmentActiveSafeBinding
    public void setOnItemCheck(@Nullable SegmentedButtonGroup.d dVar) {
        this.y1 = dVar;
        synchronized (this) {
            this.J1 |= 1073741824;
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
                this.J1 |= 64;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.J1 |= 274877906944L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.J1 |= 549755813888L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 1099511627776L;
        }
        return true;
    }

    public final boolean u(MutableLiveData<Byte> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 8;
        }
        return true;
    }

    public final boolean v(ActiveSafeModel activeSafeModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 1024;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.J1 |= 2251799813685248L;
            }
            return true;
        }
        if (i == 9) {
            synchronized (this) {
                this.J1 |= 4503599627370496L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.J1 |= 9007199254740992L;
            }
            return true;
        }
        if (i == 11) {
            synchronized (this) {
                this.J1 |= 18014398509481984L;
            }
            return true;
        }
        if (i != 10) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 36028797018963968L;
        }
        return true;
    }

    public final boolean w(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 32;
        }
        return true;
    }

    public final boolean x(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 512;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.J1 |= 140737488355328L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.J1 |= 281474976710656L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.J1 |= 562949953421312L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 1125899906842624L;
        }
        return true;
    }

    public final boolean y(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 16;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.J1 |= 17179869184L;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.J1 |= 34359738368L;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.J1 |= 68719476736L;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.J1 |= 137438953472L;
        }
        return true;
    }

    public final boolean z(DriverAssistModel driverAssistModel, int i) {
        if (i == 0) {
            synchronized (this) {
                this.J1 |= 8192;
            }
            return true;
        }
        if (i == 1) {
            synchronized (this) {
                this.J1 |= Long.MIN_VALUE;
            }
            return true;
        }
        if (i == 2) {
            synchronized (this) {
                this.K1 |= 1;
            }
            return true;
        }
        if (i == 3) {
            synchronized (this) {
                this.K1 |= 2;
            }
            return true;
        }
        if (i != 11) {
            return false;
        }
        synchronized (this) {
            this.K1 |= 4;
        }
        return true;
    }

    public FragmentActiveSafeBindingLandImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 27, (BydSwitch) objArr[18], (ConstraintLayout) objArr[90], (ConstraintLayout) objArr[33], (ConstraintLayout) objArr[99], (ConstraintLayout) objArr[20], (ConstraintLayout) objArr[10], (ConstraintLayout) objArr[112], (ConstraintLayout) objArr[93], (ConstraintLayout) objArr[41], (View) objArr[64], (Flow) objArr[34], (Flow) objArr[114], (Flow) objArr[121], (ImageView) objArr[79], (ImageView) objArr[6], (ImageView) objArr[92], (ImageView) objArr[54], (ImageView) objArr[51], (ImageView) objArr[101], (ImageView) objArr[72], (ImageView) objArr[75], (ImageView) objArr[12], (ImageView) objArr[26], (ImageView) objArr[95], (ImageView) objArr[42], (ImageView) objArr[40], (ImageView) objArr[60], (ImageView) objArr[57], (ImageView) objArr[37], (ImageView) objArr[63], (View) objArr[35], (ConstraintLayout) objArr[78], (View) objArr[4], (ConstraintLayout) objArr[7], (View) objArr[52], (View) objArr[49], (View) objArr[70], (View) objArr[73], (LinearLayout) objArr[48], (View) objArr[38], (View) objArr[58], (View) objArr[55], (View) objArr[83], (View) objArr[61], (View) objArr[68], (ConstraintLayout) objArr[76], (LottieAnimationView) objArr[108], (LottieAnimationView) objArr[105], (LottieAnimationView) objArr[113], (LottieAnimationView) objArr[120], (DampingScrollView) objArr[0], (RecyclerView) objArr[1], (ConstraintLayout) objArr[29], (ImageView) objArr[31], (BydSwitch) objArr[30], (TextView) objArr[107], (SegmentedButton) objArr[81], (SegmentedButton) objArr[97], (SegmentedButton) objArr[98], (SegmentedButton) objArr[82], (SegmentedButton) objArr[47], (SegmentedButton) objArr[46], (SegmentedButton) objArr[44], (SegmentedButton) objArr[45], (SegmentedButton) objArr[85], (SegmentedButton) objArr[86], (SegmentedButton) objArr[87], (SegmentedButton) objArr[88], (SegmentedButton) objArr[89], (SegmentedButtonGroup) objArr[80], (SegmentedButtonGroup) objArr[13], (SegmentedButtonGroup) objArr[21], (SegmentedButtonGroup) objArr[96], (SegmentedButtonGroup) objArr[43], (SegmentedButtonGroup) objArr[84], (View) objArr[65], (ImageView) objArr[67], (BydSwitch) objArr[66], (TextView) objArr[122], (BydSwitch) objArr[36], (BydSwitch) objArr[5], (BydSwitch) objArr[91], (BydSwitch) objArr[8], (BydSwitch) objArr[53], (BydSwitch) objArr[50], (BydSwitch) objArr[100], (BydSwitch) objArr[74], (BydSwitch) objArr[71], (BydSwitch) objArr[94], (BydSwitch) objArr[39], (BydSwitch) objArr[59], (BydSwitch) objArr[56], (BydSwitch) objArr[62], (BydSwitch) objArr[69], (BydSwitch) objArr[77], (SegmentedButton) objArr[23], (SegmentedButton) objArr[22], (SegmentedButton) objArr[24], (SegmentedButton) objArr[17], (SegmentedButton) objArr[16], (SegmentedButton) objArr[15], (SegmentedButton) objArr[14], (TextView) objArr[102], (TextView) objArr[129], (TextView) objArr[103], (TextView) objArr[104], (TextView) objArr[116], (TextView) objArr[115], (TextView) objArr[125], (TextView) objArr[124], (TextView) objArr[11], (TextView) objArr[19], (TextView) objArr[106], (TextView) objArr[9], (TextView) objArr[127], (TextView) objArr[130], (TextView) objArr[111], (TextView) objArr[110], (TextView) objArr[118], (TextView) objArr[117], (TextView) objArr[128], (TextView) objArr[109], (TextView) objArr[131], (TextView) objArr[119], (TextView) objArr[123], (TextView) objArr[126]);
        this.J1 = -1L;
        this.K1 = -1L;
        this.L1 = -1L;
        this.M1 = -1L;
        this.N1 = -1L;
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
        TextView textView = (TextView) objArr[2];
        this.D1 = textView;
        textView.setTag(null);
        LinearLayout linearLayout = (LinearLayout) objArr[25];
        this.E1 = linearLayout;
        linearLayout.setTag(null);
        TextView textView2 = (TextView) objArr[27];
        this.F1 = textView2;
        textView2.setTag(null);
        TextView textView3 = (TextView) objArr[28];
        this.G1 = textView3;
        textView3.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[3];
        this.H1 = constraintLayout;
        constraintLayout.setTag(null);
        TextView textView4 = (TextView) objArr[32];
        this.I1 = textView4;
        textView4.setTag(null);
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
