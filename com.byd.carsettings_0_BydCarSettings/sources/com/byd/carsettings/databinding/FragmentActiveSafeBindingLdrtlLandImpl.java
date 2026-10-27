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
public class FragmentActiveSafeBindingLdrtlLandImpl extends FragmentActiveSafeBinding {

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

    public FragmentActiveSafeBindingLdrtlLandImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
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

    /* JADX WARN: Removed duplicated region for block: B:1022:0x0fed  */
    /* JADX WARN: Removed duplicated region for block: B:1023:0x0fef  */
    /* JADX WARN: Removed duplicated region for block: B:1024:0x0ffc  */
    /* JADX WARN: Removed duplicated region for block: B:1027:0x1008  */
    /* JADX WARN: Removed duplicated region for block: B:1028:0x100f  */
    /* JADX WARN: Removed duplicated region for block: B:1033:0x1025  */
    /* JADX WARN: Removed duplicated region for block: B:1034:0x1027  */
    /* JADX WARN: Removed duplicated region for block: B:1035:0x102b  */
    /* JADX WARN: Removed duplicated region for block: B:1040:0x103a  */
    /* JADX WARN: Removed duplicated region for block: B:1041:0x103c  */
    /* JADX WARN: Removed duplicated region for block: B:1042:0x103f  */
    /* JADX WARN: Removed duplicated region for block: B:1044:0x1045  */
    /* JADX WARN: Removed duplicated region for block: B:1048:0x1051  */
    /* JADX WARN: Removed duplicated region for block: B:1050:0x1055  */
    /* JADX WARN: Removed duplicated region for block: B:1051:0x1058  */
    /* JADX WARN: Removed duplicated region for block: B:1058:0x107b  */
    /* JADX WARN: Removed duplicated region for block: B:1059:0x107d  */
    /* JADX WARN: Removed duplicated region for block: B:1060:0x108a  */
    /* JADX WARN: Removed duplicated region for block: B:1067:0x10a6  */
    /* JADX WARN: Removed duplicated region for block: B:1068:0x10a8  */
    /* JADX WARN: Removed duplicated region for block: B:1069:0x10ad  */
    /* JADX WARN: Removed duplicated region for block: B:1076:0x10ca  */
    /* JADX WARN: Removed duplicated region for block: B:1077:0x10cc  */
    /* JADX WARN: Removed duplicated region for block: B:1078:0x10d1  */
    /* JADX WARN: Removed duplicated region for block: B:1083:0x10e4  */
    /* JADX WARN: Removed duplicated region for block: B:1084:0x10e6  */
    /* JADX WARN: Removed duplicated region for block: B:1085:0x10ec  */
    /* JADX WARN: Removed duplicated region for block: B:1088:0x10f5  */
    /* JADX WARN: Removed duplicated region for block: B:1089:0x10f8  */
    /* JADX WARN: Removed duplicated region for block: B:1099:0x111c  */
    /* JADX WARN: Removed duplicated region for block: B:1102:0x1125 A[PHI: r8
  0x1125: PHI (r8v61 long) = (r8v60 long), (r8v58 long) binds: [B:1114:0x1141, B:1100:0x1122] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1103:0x1127  */
    /* JADX WARN: Removed duplicated region for block: B:1105:0x112b  */
    /* JADX WARN: Removed duplicated region for block: B:1106:0x112d  */
    /* JADX WARN: Removed duplicated region for block: B:1108:0x1130  */
    /* JADX WARN: Removed duplicated region for block: B:1110:0x1136  */
    /* JADX WARN: Removed duplicated region for block: B:1111:0x1138  */
    /* JADX WARN: Removed duplicated region for block: B:1112:0x113c  */
    /* JADX WARN: Removed duplicated region for block: B:1115:0x1143  */
    /* JADX WARN: Removed duplicated region for block: B:1123:0x1168  */
    /* JADX WARN: Removed duplicated region for block: B:1124:0x116a  */
    /* JADX WARN: Removed duplicated region for block: B:1125:0x1177  */
    /* JADX WARN: Removed duplicated region for block: B:1133:0x1190  */
    /* JADX WARN: Removed duplicated region for block: B:1134:0x1192  */
    /* JADX WARN: Removed duplicated region for block: B:1135:0x1194  */
    /* JADX WARN: Removed duplicated region for block: B:1137:0x1197  */
    /* JADX WARN: Removed duplicated region for block: B:1138:0x119a  */
    /* JADX WARN: Removed duplicated region for block: B:1142:0x11a6  */
    /* JADX WARN: Removed duplicated region for block: B:1143:0x11a8  */
    /* JADX WARN: Removed duplicated region for block: B:1144:0x11ab  */
    /* JADX WARN: Removed duplicated region for block: B:1148:0x11b9  */
    /* JADX WARN: Removed duplicated region for block: B:1149:0x11bb  */
    /* JADX WARN: Removed duplicated region for block: B:1150:0x11be  */
    /* JADX WARN: Removed duplicated region for block: B:1153:0x11c4  */
    /* JADX WARN: Removed duplicated region for block: B:1154:0x11c6  */
    /* JADX WARN: Removed duplicated region for block: B:1157:0x11cb  */
    /* JADX WARN: Removed duplicated region for block: B:1159:0x11cf  */
    /* JADX WARN: Removed duplicated region for block: B:1162:0x11de  */
    /* JADX WARN: Removed duplicated region for block: B:1166:0x11f8  */
    /* JADX WARN: Removed duplicated region for block: B:1167:0x11fa  */
    /* JADX WARN: Removed duplicated region for block: B:1168:0x1207  */
    /* JADX WARN: Removed duplicated region for block: B:1175:0x1225  */
    /* JADX WARN: Removed duplicated region for block: B:1176:0x1227  */
    /* JADX WARN: Removed duplicated region for block: B:1177:0x122c  */
    /* JADX WARN: Removed duplicated region for block: B:1181:0x123c  */
    /* JADX WARN: Removed duplicated region for block: B:1182:0x123e  */
    /* JADX WARN: Removed duplicated region for block: B:1183:0x1244  */
    /* JADX WARN: Removed duplicated region for block: B:1186:0x124d  */
    /* JADX WARN: Removed duplicated region for block: B:1187:0x1250  */
    /* JADX WARN: Removed duplicated region for block: B:1195:0x126c  */
    /* JADX WARN: Removed duplicated region for block: B:1198:0x1275 A[PHI: r8
  0x1275: PHI (r8v74 long) = (r8v73 long), (r8v71 long) binds: [B:1210:0x1292, B:1196:0x1272] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1199:0x1278  */
    /* JADX WARN: Removed duplicated region for block: B:1201:0x127c  */
    /* JADX WARN: Removed duplicated region for block: B:1202:0x127f  */
    /* JADX WARN: Removed duplicated region for block: B:1204:0x1283  */
    /* JADX WARN: Removed duplicated region for block: B:1206:0x1289  */
    /* JADX WARN: Removed duplicated region for block: B:1207:0x128b  */
    /* JADX WARN: Removed duplicated region for block: B:1208:0x128e  */
    /* JADX WARN: Removed duplicated region for block: B:1212:0x1295  */
    /* JADX WARN: Removed duplicated region for block: B:1219:0x12b5  */
    /* JADX WARN: Removed duplicated region for block: B:1220:0x12b7  */
    /* JADX WARN: Removed duplicated region for block: B:1221:0x12c4  */
    /* JADX WARN: Removed duplicated region for block: B:1224:0x12d0  */
    /* JADX WARN: Removed duplicated region for block: B:1227:0x12d9 A[PHI: r8
  0x12d9: PHI (r8v79 long) = (r8v77 long), (r8v75 long) binds: [B:1239:0x12f9, B:1225:0x12d6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1228:0x12db  */
    /* JADX WARN: Removed duplicated region for block: B:1230:0x12df  */
    /* JADX WARN: Removed duplicated region for block: B:1231:0x12e1  */
    /* JADX WARN: Removed duplicated region for block: B:1235:0x12ea  */
    /* JADX WARN: Removed duplicated region for block: B:1236:0x12ec  */
    /* JADX WARN: Removed duplicated region for block: B:1237:0x12f2  */
    /* JADX WARN: Removed duplicated region for block: B:1241:0x12fc  */
    /* JADX WARN: Removed duplicated region for block: B:1243:0x1300  */
    /* JADX WARN: Removed duplicated region for block: B:1244:0x1305  */
    /* JADX WARN: Removed duplicated region for block: B:1251:0x132c  */
    /* JADX WARN: Removed duplicated region for block: B:1252:0x132e  */
    /* JADX WARN: Removed duplicated region for block: B:1253:0x133b  */
    /* JADX WARN: Removed duplicated region for block: B:1259:0x1357 A[PHI: r8
  0x1357: PHI (r8v91 long) = (r8v83 long), (r8v80 long) binds: [B:1269:0x1373, B:1257:0x1354] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1260:0x135a  */
    /* JADX WARN: Removed duplicated region for block: B:1261:0x135c  */
    /* JADX WARN: Removed duplicated region for block: B:1262:0x135f  */
    /* JADX WARN: Removed duplicated region for block: B:1266:0x1369  */
    /* JADX WARN: Removed duplicated region for block: B:1267:0x136b  */
    /* JADX WARN: Removed duplicated region for block: B:1268:0x136e  */
    /* JADX WARN: Removed duplicated region for block: B:1271:0x1376  */
    /* JADX WARN: Removed duplicated region for block: B:1277:0x138b  */
    /* JADX WARN: Removed duplicated region for block: B:1278:0x138d  */
    /* JADX WARN: Removed duplicated region for block: B:1279:0x1392  */
    /* JADX WARN: Removed duplicated region for block: B:1284:0x13a5  */
    /* JADX WARN: Removed duplicated region for block: B:1285:0x13a7  */
    /* JADX WARN: Removed duplicated region for block: B:1286:0x13aa  */
    /* JADX WARN: Removed duplicated region for block: B:1288:0x13b3  */
    /* JADX WARN: Removed duplicated region for block: B:1289:0x13b6  */
    /* JADX WARN: Removed duplicated region for block: B:1295:0x13cb  */
    /* JADX WARN: Removed duplicated region for block: B:1296:0x13cd  */
    /* JADX WARN: Removed duplicated region for block: B:1297:0x13d2  */
    /* JADX WARN: Removed duplicated region for block: B:1302:0x13e5  */
    /* JADX WARN: Removed duplicated region for block: B:1303:0x13e7  */
    /* JADX WARN: Removed duplicated region for block: B:1304:0x13ed  */
    /* JADX WARN: Removed duplicated region for block: B:1311:0x1407  */
    /* JADX WARN: Removed duplicated region for block: B:1312:0x1409  */
    /* JADX WARN: Removed duplicated region for block: B:1313:0x140e  */
    /* JADX WARN: Removed duplicated region for block: B:1323:0x143c  */
    /* JADX WARN: Removed duplicated region for block: B:1327:0x1456  */
    /* JADX WARN: Removed duplicated region for block: B:1328:0x1458  */
    /* JADX WARN: Removed duplicated region for block: B:1329:0x1465  */
    /* JADX WARN: Removed duplicated region for block: B:1335:0x1481 A[PHI: r10
  0x1481: PHI (r10v57 long) = (r10v50 long), (r10v48 long) binds: [B:1346:0x14a2, B:1333:0x147e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1336:0x1484  */
    /* JADX WARN: Removed duplicated region for block: B:1337:0x1486  */
    /* JADX WARN: Removed duplicated region for block: B:1338:0x1489  */
    /* JADX WARN: Removed duplicated region for block: B:1342:0x1493  */
    /* JADX WARN: Removed duplicated region for block: B:1343:0x1495  */
    /* JADX WARN: Removed duplicated region for block: B:1344:0x149b  */
    /* JADX WARN: Removed duplicated region for block: B:1348:0x14a5  */
    /* JADX WARN: Removed duplicated region for block: B:1354:0x14ba  */
    /* JADX WARN: Removed duplicated region for block: B:1355:0x14bc  */
    /* JADX WARN: Removed duplicated region for block: B:1356:0x14c1  */
    /* JADX WARN: Removed duplicated region for block: B:1361:0x14d4  */
    /* JADX WARN: Removed duplicated region for block: B:1362:0x14d6  */
    /* JADX WARN: Removed duplicated region for block: B:1363:0x14d9  */
    /* JADX WARN: Removed duplicated region for block: B:1366:0x14df  */
    /* JADX WARN: Removed duplicated region for block: B:1367:0x14e2  */
    /* JADX WARN: Removed duplicated region for block: B:1375:0x14fd  */
    /* JADX WARN: Removed duplicated region for block: B:1379:0x150e  */
    /* JADX WARN: Removed duplicated region for block: B:1380:0x1510  */
    /* JADX WARN: Removed duplicated region for block: B:1381:0x1515  */
    /* JADX WARN: Removed duplicated region for block: B:1386:0x1528  */
    /* JADX WARN: Removed duplicated region for block: B:1387:0x152a  */
    /* JADX WARN: Removed duplicated region for block: B:1388:0x152d  */
    /* JADX WARN: Removed duplicated region for block: B:1395:0x1543  */
    /* JADX WARN: Removed duplicated region for block: B:1396:0x1545  */
    /* JADX WARN: Removed duplicated region for block: B:1397:0x154a  */
    /* JADX WARN: Removed duplicated region for block: B:1405:0x156d  */
    /* JADX WARN: Removed duplicated region for block: B:1406:0x156f  */
    /* JADX WARN: Removed duplicated region for block: B:1407:0x157c  */
    /* JADX WARN: Removed duplicated region for block: B:1410:0x1588  */
    /* JADX WARN: Removed duplicated region for block: B:1411:0x158f  */
    /* JADX WARN: Removed duplicated region for block: B:1413:0x1592  */
    /* JADX WARN: Removed duplicated region for block: B:1414:0x1594  */
    /* JADX WARN: Removed duplicated region for block: B:1419:0x15a3  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:1420:0x15a5  */
    /* JADX WARN: Removed duplicated region for block: B:1421:0x15a8  */
    /* JADX WARN: Removed duplicated region for block: B:1424:0x15ae  */
    /* JADX WARN: Removed duplicated region for block: B:1425:0x15b0  */
    /* JADX WARN: Removed duplicated region for block: B:1428:0x15b5  */
    /* JADX WARN: Removed duplicated region for block: B:1429:0x15b7  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0329  */
    /* JADX WARN: Removed duplicated region for block: B:1436:0x15df  */
    /* JADX WARN: Removed duplicated region for block: B:1437:0x15e1  */
    /* JADX WARN: Removed duplicated region for block: B:1438:0x15e8  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:1445:0x160a  */
    /* JADX WARN: Removed duplicated region for block: B:1446:0x160c  */
    /* JADX WARN: Removed duplicated region for block: B:1447:0x1611  */
    /* JADX WARN: Removed duplicated region for block: B:1454:0x1630  */
    /* JADX WARN: Removed duplicated region for block: B:1455:0x1632  */
    /* JADX WARN: Removed duplicated region for block: B:1456:0x1637  */
    /* JADX WARN: Removed duplicated region for block: B:1461:0x164a  */
    /* JADX WARN: Removed duplicated region for block: B:1462:0x164c  */
    /* JADX WARN: Removed duplicated region for block: B:1463:0x164f  */
    /* JADX WARN: Removed duplicated region for block: B:1465:0x1656  */
    /* JADX WARN: Removed duplicated region for block: B:1466:0x1659  */
    /* JADX WARN: Removed duplicated region for block: B:1472:0x166e  */
    /* JADX WARN: Removed duplicated region for block: B:1473:0x1670  */
    /* JADX WARN: Removed duplicated region for block: B:1474:0x1675  */
    /* JADX WARN: Removed duplicated region for block: B:1479:0x1688  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x034c  */
    /* JADX WARN: Removed duplicated region for block: B:1480:0x168a  */
    /* JADX WARN: Removed duplicated region for block: B:1481:0x1690  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x034e  */
    /* JADX WARN: Removed duplicated region for block: B:1492:0x16c1  */
    /* JADX WARN: Removed duplicated region for block: B:1496:0x16cb  */
    /* JADX WARN: Removed duplicated region for block: B:1497:0x16cd  */
    /* JADX WARN: Removed duplicated region for block: B:1498:0x16cf  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:1502:0x16d8  */
    /* JADX WARN: Removed duplicated region for block: B:1503:0x16da  */
    /* JADX WARN: Removed duplicated region for block: B:1504:0x16e2  */
    /* JADX WARN: Removed duplicated region for block: B:1508:0x16ee  */
    /* JADX WARN: Removed duplicated region for block: B:1509:0x16f0  */
    /* JADX WARN: Removed duplicated region for block: B:1510:0x16f3  */
    /* JADX WARN: Removed duplicated region for block: B:1513:0x16f9  */
    /* JADX WARN: Removed duplicated region for block: B:1516:0x1702 A[PHI: r16
  0x1702: PHI (r16v139 long) = (r16v138 long), (r16v140 long) binds: [B:1518:0x1706, B:1514:0x16ff] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1517:0x1704  */
    /* JADX WARN: Removed duplicated region for block: B:1520:0x1709  */
    /* JADX WARN: Removed duplicated region for block: B:1527:0x1738  */
    /* JADX WARN: Removed duplicated region for block: B:1528:0x173a  */
    /* JADX WARN: Removed duplicated region for block: B:1529:0x1747  */
    /* JADX WARN: Removed duplicated region for block: B:1536:0x176a  */
    /* JADX WARN: Removed duplicated region for block: B:1537:0x176c  */
    /* JADX WARN: Removed duplicated region for block: B:1538:0x1778  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:1540:0x177e  */
    /* JADX WARN: Removed duplicated region for block: B:1541:0x1781  */
    /* JADX WARN: Removed duplicated region for block: B:1543:0x1785  */
    /* JADX WARN: Removed duplicated region for block: B:1544:0x1787  */
    /* JADX WARN: Removed duplicated region for block: B:1548:0x1792  */
    /* JADX WARN: Removed duplicated region for block: B:1549:0x1794  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:1550:0x1797  */
    /* JADX WARN: Removed duplicated region for block: B:1554:0x17a6  */
    /* JADX WARN: Removed duplicated region for block: B:1555:0x17a8  */
    /* JADX WARN: Removed duplicated region for block: B:1556:0x17ae  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0377  */
    /* JADX WARN: Removed duplicated region for block: B:1566:0x17d7  */
    /* JADX WARN: Removed duplicated region for block: B:1570:0x17e6  */
    /* JADX WARN: Removed duplicated region for block: B:1571:0x17e8  */
    /* JADX WARN: Removed duplicated region for block: B:1572:0x17ed  */
    /* JADX WARN: Removed duplicated region for block: B:1577:0x1800  */
    /* JADX WARN: Removed duplicated region for block: B:1578:0x1802  */
    /* JADX WARN: Removed duplicated region for block: B:1579:0x1805  */
    /* JADX WARN: Removed duplicated region for block: B:1586:0x181c  */
    /* JADX WARN: Removed duplicated region for block: B:1587:0x181e  */
    /* JADX WARN: Removed duplicated region for block: B:1588:0x1823  */
    /* JADX WARN: Removed duplicated region for block: B:1593:0x1836  */
    /* JADX WARN: Removed duplicated region for block: B:1594:0x1838  */
    /* JADX WARN: Removed duplicated region for block: B:1595:0x183b  */
    /* JADX WARN: Removed duplicated region for block: B:1598:0x1841  */
    /* JADX WARN: Removed duplicated region for block: B:1599:0x1844  */
    /* JADX WARN: Removed duplicated region for block: B:1606:0x185b  */
    /* JADX WARN: Removed duplicated region for block: B:1607:0x185d  */
    /* JADX WARN: Removed duplicated region for block: B:1608:0x1860  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:1612:0x186a  */
    /* JADX WARN: Removed duplicated region for block: B:1613:0x186c  */
    /* JADX WARN: Removed duplicated region for block: B:1614:0x186f  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:1621:0x1886  */
    /* JADX WARN: Removed duplicated region for block: B:1622:0x1888  */
    /* JADX WARN: Removed duplicated region for block: B:1623:0x188d  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x038c  */
    /* JADX WARN: Removed duplicated region for block: B:1632:0x18b4  */
    /* JADX WARN: Removed duplicated region for block: B:1635:0x18b9  */
    /* JADX WARN: Removed duplicated region for block: B:1638:0x18c1  */
    /* JADX WARN: Removed duplicated region for block: B:1645:0x19a0  */
    /* JADX WARN: Removed duplicated region for block: B:1646:0x19a2  */
    /* JADX WARN: Removed duplicated region for block: B:1647:0x19af  */
    /* JADX WARN: Removed duplicated region for block: B:1650:0x19bb  */
    /* JADX WARN: Removed duplicated region for block: B:1651:0x19bd  */
    /* JADX WARN: Removed duplicated region for block: B:1655:0x19c8  */
    /* JADX WARN: Removed duplicated region for block: B:1656:0x19ca  */
    /* JADX WARN: Removed duplicated region for block: B:1657:0x19cd  */
    /* JADX WARN: Removed duplicated region for block: B:1659:0x19d6  */
    /* JADX WARN: Removed duplicated region for block: B:1660:0x19d8  */
    /* JADX WARN: Removed duplicated region for block: B:1664:0x1aba  */
    /* JADX WARN: Removed duplicated region for block: B:1668:0x1ac4  */
    /* JADX WARN: Removed duplicated region for block: B:1669:0x1ac6  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0398  */
    /* JADX WARN: Removed duplicated region for block: B:1670:0x1ac9  */
    /* JADX WARN: Removed duplicated region for block: B:1674:0x1ad3  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:1680:0x1aef  */
    /* JADX WARN: Removed duplicated region for block: B:1684:0x1b01  */
    /* JADX WARN: Removed duplicated region for block: B:1688:0x1b0c  */
    /* JADX WARN: Removed duplicated region for block: B:1689:0x1b0e  */
    /* JADX WARN: Removed duplicated region for block: B:1690:0x1b11  */
    /* JADX WARN: Removed duplicated region for block: B:1694:0x1b1d  */
    /* JADX WARN: Removed duplicated region for block: B:1707:0x1b4e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:1710:0x1b5f  */
    /* JADX WARN: Removed duplicated region for block: B:1714:0x1b6f  */
    /* JADX WARN: Removed duplicated region for block: B:1715:0x1b71  */
    /* JADX WARN: Removed duplicated region for block: B:1716:0x1b74  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x03c5  */
    /* JADX WARN: Removed duplicated region for block: B:1720:0x1b80  */
    /* JADX WARN: Removed duplicated region for block: B:1726:0x1b9c  */
    /* JADX WARN: Removed duplicated region for block: B:1730:0x1ba9  */
    /* JADX WARN: Removed duplicated region for block: B:1735:0x1bbe  */
    /* JADX WARN: Removed duplicated region for block: B:1739:0x1bcb  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:1753:0x1c01  */
    /* JADX WARN: Removed duplicated region for block: B:1756:0x1c0d  */
    /* JADX WARN: Removed duplicated region for block: B:1761:0x1c1e  */
    /* JADX WARN: Removed duplicated region for block: B:1764:0x1c2c  */
    /* JADX WARN: Removed duplicated region for block: B:1768:0x1c37  */
    /* JADX WARN: Removed duplicated region for block: B:1769:0x1c39  */
    /* JADX WARN: Removed duplicated region for block: B:1770:0x1c3c  */
    /* JADX WARN: Removed duplicated region for block: B:1774:0x1c48  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:1780:0x1c65  */
    /* JADX WARN: Removed duplicated region for block: B:1784:0x1c75  */
    /* JADX WARN: Removed duplicated region for block: B:1788:0x1c85  */
    /* JADX WARN: Removed duplicated region for block: B:1789:0x1c87  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:1790:0x1c8a  */
    /* JADX WARN: Removed duplicated region for block: B:1794:0x1c9b  */
    /* JADX WARN: Removed duplicated region for block: B:1798:0x1ca5  */
    /* JADX WARN: Removed duplicated region for block: B:1799:0x1ca7  */
    /* JADX WARN: Removed duplicated region for block: B:1800:0x1caa  */
    /* JADX WARN: Removed duplicated region for block: B:1804:0x1cb4  */
    /* JADX WARN: Removed duplicated region for block: B:1809:0x1cc2  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x03ee  */
    /* JADX WARN: Removed duplicated region for block: B:1810:0x1cc4  */
    /* JADX WARN: Removed duplicated region for block: B:1815:0x1ce2  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:1821:0x1cf6  */
    /* JADX WARN: Removed duplicated region for block: B:1825:0x1d06  */
    /* JADX WARN: Removed duplicated region for block: B:1826:0x1d08  */
    /* JADX WARN: Removed duplicated region for block: B:1827:0x1d0b  */
    /* JADX WARN: Removed duplicated region for block: B:1831:0x1d19  */
    /* JADX WARN: Removed duplicated region for block: B:1836:0x1d2e  */
    /* JADX WARN: Removed duplicated region for block: B:1840:0x1d42  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:1853:0x1d73  */
    /* JADX WARN: Removed duplicated region for block: B:1856:0x1d7c  */
    /* JADX WARN: Removed duplicated region for block: B:1859:0x1d83  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:1862:0x1d8d  */
    /* JADX WARN: Removed duplicated region for block: B:1866:0x1d9d  */
    /* JADX WARN: Removed duplicated region for block: B:1867:0x1d9f  */
    /* JADX WARN: Removed duplicated region for block: B:1868:0x1da2  */
    /* JADX WARN: Removed duplicated region for block: B:1872:0x1db3  */
    /* JADX WARN: Removed duplicated region for block: B:1876:0x1dbe  */
    /* JADX WARN: Removed duplicated region for block: B:1877:0x1dc0  */
    /* JADX WARN: Removed duplicated region for block: B:1878:0x1dc3  */
    /* JADX WARN: Removed duplicated region for block: B:1882:0x1dd2  */
    /* JADX WARN: Removed duplicated region for block: B:1886:0x1de1  */
    /* JADX WARN: Removed duplicated region for block: B:1887:0x1de3  */
    /* JADX WARN: Removed duplicated region for block: B:1888:0x1de6  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x040e  */
    /* JADX WARN: Removed duplicated region for block: B:1892:0x1df3  */
    /* JADX WARN: Removed duplicated region for block: B:1897:0x1e0b  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0411  */
    /* JADX WARN: Removed duplicated region for block: B:1901:0x1e1d  */
    /* JADX WARN: Removed duplicated region for block: B:1905:0x1e28  */
    /* JADX WARN: Removed duplicated region for block: B:1906:0x1e2a  */
    /* JADX WARN: Removed duplicated region for block: B:1910:0x1e34  */
    /* JADX WARN: Removed duplicated region for block: B:1911:0x1e36  */
    /* JADX WARN: Removed duplicated region for block: B:1912:0x1e3c  */
    /* JADX WARN: Removed duplicated region for block: B:1915:0x1e45  */
    /* JADX WARN: Removed duplicated region for block: B:1916:0x1e48  */
    /* JADX WARN: Removed duplicated region for block: B:1920:0x1e5d  */
    /* JADX WARN: Removed duplicated region for block: B:1924:0x1e67  */
    /* JADX WARN: Removed duplicated region for block: B:1926:0x1e6a  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:1930:0x1e74  */
    /* JADX WARN: Removed duplicated region for block: B:1931:0x1e76  */
    /* JADX WARN: Removed duplicated region for block: B:1932:0x1e79  */
    /* JADX WARN: Removed duplicated region for block: B:1936:0x1e8a  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x041a  */
    /* JADX WARN: Removed duplicated region for block: B:1940:0x1e99  */
    /* JADX WARN: Removed duplicated region for block: B:1941:0x1e9b  */
    /* JADX WARN: Removed duplicated region for block: B:1942:0x1e9e  */
    /* JADX WARN: Removed duplicated region for block: B:1946:0x1ead  */
    /* JADX WARN: Removed duplicated region for block: B:1947:0x1eaf  */
    /* JADX WARN: Removed duplicated region for block: B:1948:0x1eb5  */
    /* JADX WARN: Removed duplicated region for block: B:1952:0x1ec9  */
    /* JADX WARN: Removed duplicated region for block: B:1956:0x1ed3  */
    /* JADX WARN: Removed duplicated region for block: B:1958:0x1ed6  */
    /* JADX WARN: Removed duplicated region for block: B:1962:0x1ee0  */
    /* JADX WARN: Removed duplicated region for block: B:1963:0x1ee2  */
    /* JADX WARN: Removed duplicated region for block: B:1964:0x1ee8  */
    /* JADX WARN: Removed duplicated region for block: B:1968:0x1efa  */
    /* JADX WARN: Removed duplicated region for block: B:1972:0x1f05  */
    /* JADX WARN: Removed duplicated region for block: B:1973:0x1f07  */
    /* JADX WARN: Removed duplicated region for block: B:1977:0x1f11  */
    /* JADX WARN: Removed duplicated region for block: B:1978:0x1f13  */
    /* JADX WARN: Removed duplicated region for block: B:1979:0x1f17  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:1982:0x1f1e  */
    /* JADX WARN: Removed duplicated region for block: B:1983:0x1f21  */
    /* JADX WARN: Removed duplicated region for block: B:1987:0x1f32  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:1991:0x1f3d  */
    /* JADX WARN: Removed duplicated region for block: B:1992:0x1f3f  */
    /* JADX WARN: Removed duplicated region for block: B:1996:0x1f49  */
    /* JADX WARN: Removed duplicated region for block: B:1997:0x1f4b  */
    /* JADX WARN: Removed duplicated region for block: B:1998:0x1f4f  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x042a  */
    /* JADX WARN: Removed duplicated region for block: B:2001:0x1f56  */
    /* JADX WARN: Removed duplicated region for block: B:2002:0x1f59  */
    /* JADX WARN: Removed duplicated region for block: B:2006:0x1f6a  */
    /* JADX WARN: Removed duplicated region for block: B:2010:0x1f78  */
    /* JADX WARN: Removed duplicated region for block: B:2012:0x1f7b  */
    /* JADX WARN: Removed duplicated region for block: B:2014:0x1f7f  */
    /* JADX WARN: Removed duplicated region for block: B:2015:0x1f91  */
    /* JADX WARN: Removed duplicated region for block: B:2019:0x1fa1  */
    /* JADX WARN: Removed duplicated region for block: B:2020:0x1fa3  */
    /* JADX WARN: Removed duplicated region for block: B:2021:0x1fa9  */
    /* JADX WARN: Removed duplicated region for block: B:2024:0x1fb2  */
    /* JADX WARN: Removed duplicated region for block: B:2025:0x1fb4  */
    /* JADX WARN: Removed duplicated region for block: B:2028:0x1fc3  */
    /* JADX WARN: Removed duplicated region for block: B:2032:0x1fd1  */
    /* JADX WARN: Removed duplicated region for block: B:2034:0x1fd4  */
    /* JADX WARN: Removed duplicated region for block: B:2038:0x1fde  */
    /* JADX WARN: Removed duplicated region for block: B:2039:0x1fe0  */
    /* JADX WARN: Removed duplicated region for block: B:2040:0x1fe8  */
    /* JADX WARN: Removed duplicated region for block: B:2042:0x1fec  */
    /* JADX WARN: Removed duplicated region for block: B:2043:0x1fef  */
    /* JADX WARN: Removed duplicated region for block: B:2047:0x2000  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x0439  */
    /* JADX WARN: Removed duplicated region for block: B:2051:0x2010  */
    /* JADX WARN: Removed duplicated region for block: B:2053:0x2013  */
    /* JADX WARN: Removed duplicated region for block: B:2056:0x201b  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:2060:0x202e  */
    /* JADX WARN: Removed duplicated region for block: B:2063:0x2033  */
    /* JADX WARN: Removed duplicated region for block: B:2065:0x2037  */
    /* JADX WARN: Removed duplicated region for block: B:2067:0x2042  */
    /* JADX WARN: Removed duplicated region for block: B:2068:0x2044  */
    /* JADX WARN: Removed duplicated region for block: B:2069:0x2047  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x043f  */
    /* JADX WARN: Removed duplicated region for block: B:2072:0x204d  */
    /* JADX WARN: Removed duplicated region for block: B:2073:0x2050  */
    /* JADX WARN: Removed duplicated region for block: B:2077:0x2065  */
    /* JADX WARN: Removed duplicated region for block: B:2081:0x206f  */
    /* JADX WARN: Removed duplicated region for block: B:2083:0x2072  */
    /* JADX WARN: Removed duplicated region for block: B:2087:0x207c  */
    /* JADX WARN: Removed duplicated region for block: B:2088:0x207e  */
    /* JADX WARN: Removed duplicated region for block: B:2089:0x2081  */
    /* JADX WARN: Removed duplicated region for block: B:2093:0x2092  */
    /* JADX WARN: Removed duplicated region for block: B:2097:0x209c  */
    /* JADX WARN: Removed duplicated region for block: B:2099:0x209f  */
    /* JADX WARN: Removed duplicated region for block: B:2103:0x20a9  */
    /* JADX WARN: Removed duplicated region for block: B:2104:0x20ab  */
    /* JADX WARN: Removed duplicated region for block: B:2105:0x20ae  */
    /* JADX WARN: Removed duplicated region for block: B:2109:0x20bc  */
    /* JADX WARN: Removed duplicated region for block: B:2115:0x20dc  */
    /* JADX WARN: Removed duplicated region for block: B:2116:0x20de  */
    /* JADX WARN: Removed duplicated region for block: B:2119:0x20ef  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:2122:0x20fd  */
    /* JADX WARN: Removed duplicated region for block: B:2124:0x2101  */
    /* JADX WARN: Removed duplicated region for block: B:2129:0x2116  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:2130:0x2118  */
    /* JADX WARN: Removed duplicated region for block: B:2131:0x211b  */
    /* JADX WARN: Removed duplicated region for block: B:2134:0x212c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2138:0x2138  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0456  */
    /* JADX WARN: Removed duplicated region for block: B:2142:0x2141  */
    /* JADX WARN: Removed duplicated region for block: B:2143:0x2143  */
    /* JADX WARN: Removed duplicated region for block: B:2144:0x2145  */
    /* JADX WARN: Removed duplicated region for block: B:2148:0x214e  */
    /* JADX WARN: Removed duplicated region for block: B:2149:0x2150  */
    /* JADX WARN: Removed duplicated region for block: B:2150:0x2154  */
    /* JADX WARN: Removed duplicated region for block: B:2153:0x215b  */
    /* JADX WARN: Removed duplicated region for block: B:2157:0x2167  */
    /* JADX WARN: Removed duplicated region for block: B:2159:0x216b  */
    /* JADX WARN: Removed duplicated region for block: B:2160:0x216e  */
    /* JADX WARN: Removed duplicated region for block: B:2162:0x2172  */
    /* JADX WARN: Removed duplicated region for block: B:2164:0x2178  */
    /* JADX WARN: Removed duplicated region for block: B:2165:0x217a  */
    /* JADX WARN: Removed duplicated region for block: B:2166:0x217d  */
    /* JADX WARN: Removed duplicated region for block: B:2169:0x2183  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:2173:0x218f  */
    /* JADX WARN: Removed duplicated region for block: B:2175:0x2193  */
    /* JADX WARN: Removed duplicated region for block: B:2176:0x2196  */
    /* JADX WARN: Removed duplicated region for block: B:2178:0x219a  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:2180:0x21a0  */
    /* JADX WARN: Removed duplicated region for block: B:2181:0x21a2  */
    /* JADX WARN: Removed duplicated region for block: B:2182:0x21a5  */
    /* JADX WARN: Removed duplicated region for block: B:2185:0x21ab  */
    /* JADX WARN: Removed duplicated region for block: B:2189:0x21b7  */
    /* JADX WARN: Removed duplicated region for block: B:2191:0x21bb  */
    /* JADX WARN: Removed duplicated region for block: B:2192:0x21be  */
    /* JADX WARN: Removed duplicated region for block: B:2194:0x21c2  */
    /* JADX WARN: Removed duplicated region for block: B:2196:0x21c8  */
    /* JADX WARN: Removed duplicated region for block: B:2197:0x21ca  */
    /* JADX WARN: Removed duplicated region for block: B:2198:0x21d0  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0466  */
    /* JADX WARN: Removed duplicated region for block: B:2201:0x21d9  */
    /* JADX WARN: Removed duplicated region for block: B:2205:0x21ec  */
    /* JADX WARN: Removed duplicated region for block: B:2207:0x21f0  */
    /* JADX WARN: Removed duplicated region for block: B:2208:0x21f3  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0469  */
    /* JADX WARN: Removed duplicated region for block: B:2211:0x2200  */
    /* JADX WARN: Removed duplicated region for block: B:2213:0x2206  */
    /* JADX WARN: Removed duplicated region for block: B:2214:0x2208  */
    /* JADX WARN: Removed duplicated region for block: B:2215:0x220b  */
    /* JADX WARN: Removed duplicated region for block: B:2220:0x2225  */
    /* JADX WARN: Removed duplicated region for block: B:2221:0x2227  */
    /* JADX WARN: Removed duplicated region for block: B:2222:0x222a  */
    /* JADX WARN: Removed duplicated region for block: B:2228:0x2246  */
    /* JADX WARN: Removed duplicated region for block: B:2229:0x2248  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x046d  */
    /* JADX WARN: Removed duplicated region for block: B:2230:0x224b  */
    /* JADX WARN: Removed duplicated region for block: B:2238:0x226f  */
    /* JADX WARN: Removed duplicated region for block: B:2239:0x2271  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:2240:0x2275  */
    /* JADX WARN: Removed duplicated region for block: B:2243:0x227d  */
    /* JADX WARN: Removed duplicated region for block: B:2247:0x2289  */
    /* JADX WARN: Removed duplicated region for block: B:2249:0x228d  */
    /* JADX WARN: Removed duplicated region for block: B:2250:0x2290  */
    /* JADX WARN: Removed duplicated region for block: B:2254:0x229c  */
    /* JADX WARN: Removed duplicated region for block: B:2260:0x22b8  */
    /* JADX WARN: Removed duplicated region for block: B:2263:0x22bf  */
    /* JADX WARN: Removed duplicated region for block: B:2267:0x22cc  */
    /* JADX WARN: Removed duplicated region for block: B:2269:0x22d0  */
    /* JADX WARN: Removed duplicated region for block: B:2270:0x22d3  */
    /* JADX WARN: Removed duplicated region for block: B:2274:0x22df  */
    /* JADX WARN: Removed duplicated region for block: B:2280:0x22f9  */
    /* JADX WARN: Removed duplicated region for block: B:2284:0x2306  */
    /* JADX WARN: Removed duplicated region for block: B:2290:0x2321  */
    /* JADX WARN: Removed duplicated region for block: B:2293:0x2326  */
    /* JADX WARN: Removed duplicated region for block: B:2297:0x2332  */
    /* JADX WARN: Removed duplicated region for block: B:2299:0x2336  */
    /* JADX WARN: Removed duplicated region for block: B:2300:0x2339  */
    /* JADX WARN: Removed duplicated region for block: B:2303:0x233f  */
    /* JADX WARN: Removed duplicated region for block: B:2307:0x2348 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2311:0x2352 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2315:0x2361  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:2321:0x237a  */
    /* JADX WARN: Removed duplicated region for block: B:2325:0x238c  */
    /* JADX WARN: Removed duplicated region for block: B:2329:0x2398  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x049a  */
    /* JADX WARN: Removed duplicated region for block: B:2331:0x239b  */
    /* JADX WARN: Removed duplicated region for block: B:2335:0x23a5  */
    /* JADX WARN: Removed duplicated region for block: B:2336:0x23a7  */
    /* JADX WARN: Removed duplicated region for block: B:2337:0x23ad  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x04a7  */
    /* JADX WARN: Removed duplicated region for block: B:2340:0x23b6  */
    /* JADX WARN: Removed duplicated region for block: B:2341:0x23b9  */
    /* JADX WARN: Removed duplicated region for block: B:2345:0x23cc  */
    /* JADX WARN: Removed duplicated region for block: B:2349:0x23d7  */
    /* JADX WARN: Removed duplicated region for block: B:2351:0x23da  */
    /* JADX WARN: Removed duplicated region for block: B:2356:0x23ed  */
    /* JADX WARN: Removed duplicated region for block: B:2357:0x23ef  */
    /* JADX WARN: Removed duplicated region for block: B:2358:0x23f2  */
    /* JADX WARN: Removed duplicated region for block: B:2363:0x240f  */
    /* JADX WARN: Removed duplicated region for block: B:2364:0x2411  */
    /* JADX WARN: Removed duplicated region for block: B:2370:0x242d  */
    /* JADX WARN: Removed duplicated region for block: B:2371:0x242f  */
    /* JADX WARN: Removed duplicated region for block: B:2372:0x2432  */
    /* JADX WARN: Removed duplicated region for block: B:2378:0x244e  */
    /* JADX WARN: Removed duplicated region for block: B:2379:0x2450  */
    /* JADX WARN: Removed duplicated region for block: B:2380:0x2456  */
    /* JADX WARN: Removed duplicated region for block: B:2384:0x246a  */
    /* JADX WARN: Removed duplicated region for block: B:2388:0x2476  */
    /* JADX WARN: Removed duplicated region for block: B:2390:0x2479  */
    /* JADX WARN: Removed duplicated region for block: B:2394:0x2483  */
    /* JADX WARN: Removed duplicated region for block: B:2395:0x2485  */
    /* JADX WARN: Removed duplicated region for block: B:2396:0x2489  */
    /* JADX WARN: Removed duplicated region for block: B:2399:0x2490  */
    /* JADX WARN: Removed duplicated region for block: B:2400:0x2493  */
    /* JADX WARN: Removed duplicated region for block: B:2404:0x24a6  */
    /* JADX WARN: Removed duplicated region for block: B:2408:0x24b7  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x04c6  */
    /* JADX WARN: Removed duplicated region for block: B:2410:0x24ba  */
    /* JADX WARN: Removed duplicated region for block: B:2414:0x24c9  */
    /* JADX WARN: Removed duplicated region for block: B:2415:0x24cb  */
    /* JADX WARN: Removed duplicated region for block: B:2416:0x24cf  */
    /* JADX WARN: Removed duplicated region for block: B:2419:0x24d6  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:2420:0x24d9  */
    /* JADX WARN: Removed duplicated region for block: B:2424:0x24ec  */
    /* JADX WARN: Removed duplicated region for block: B:2428:0x24fd  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:2430:0x2500  */
    /* JADX WARN: Removed duplicated region for block: B:2434:0x250f  */
    /* JADX WARN: Removed duplicated region for block: B:2435:0x2511  */
    /* JADX WARN: Removed duplicated region for block: B:2436:0x2517  */
    /* JADX WARN: Removed duplicated region for block: B:2440:0x2521  */
    /* JADX WARN: Removed duplicated region for block: B:2444:0x2531  */
    /* JADX WARN: Removed duplicated region for block: B:2449:0x2548  */
    /* JADX WARN: Removed duplicated region for block: B:2453:0x2555  */
    /* JADX WARN: Removed duplicated region for block: B:2458:0x256a  */
    /* JADX WARN: Removed duplicated region for block: B:2462:0x257a  */
    /* JADX WARN: Removed duplicated region for block: B:2467:0x2590  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x04d5  */
    /* JADX WARN: Removed duplicated region for block: B:2471:0x25a0  */
    /* JADX WARN: Removed duplicated region for block: B:2477:0x25b7  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x04d7  */
    /* JADX WARN: Removed duplicated region for block: B:2481:0x25c7  */
    /* JADX WARN: Removed duplicated region for block: B:2485:0x25d2  */
    /* JADX WARN: Removed duplicated region for block: B:2487:0x25d5  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x04da  */
    /* JADX WARN: Removed duplicated region for block: B:2491:0x25e6  */
    /* JADX WARN: Removed duplicated region for block: B:2495:0x25f6  */
    /* JADX WARN: Removed duplicated region for block: B:2497:0x25f9  */
    /* JADX WARN: Removed duplicated region for block: B:2501:0x260a  */
    /* JADX WARN: Removed duplicated region for block: B:2505:0x2615  */
    /* JADX WARN: Removed duplicated region for block: B:2507:0x2618  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x04e1  */
    /* JADX WARN: Removed duplicated region for block: B:2511:0x2629  */
    /* JADX WARN: Removed duplicated region for block: B:2515:0x2639  */
    /* JADX WARN: Removed duplicated region for block: B:2517:0x263c  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:2520:0x2644  */
    /* JADX WARN: Removed duplicated region for block: B:2524:0x2657  */
    /* JADX WARN: Removed duplicated region for block: B:2526:0x2663  */
    /* JADX WARN: Removed duplicated region for block: B:2528:0x267e  */
    /* JADX WARN: Removed duplicated region for block: B:2530:0x268b  */
    /* JADX WARN: Removed duplicated region for block: B:2532:0x26ab  */
    /* JADX WARN: Removed duplicated region for block: B:2535:0x26b9  */
    /* JADX WARN: Removed duplicated region for block: B:2538:0x26c2  */
    /* JADX WARN: Removed duplicated region for block: B:2540:0x26cd  */
    /* JADX WARN: Removed duplicated region for block: B:2543:0x26d8  */
    /* JADX WARN: Removed duplicated region for block: B:2545:0x26de  */
    /* JADX WARN: Removed duplicated region for block: B:2547:0x2729  */
    /* JADX WARN: Removed duplicated region for block: B:2549:0x2732  */
    /* JADX WARN: Removed duplicated region for block: B:2551:0x273a  */
    /* JADX WARN: Removed duplicated region for block: B:2554:0x27a6  */
    /* JADX WARN: Removed duplicated region for block: B:2556:0x27ac  */
    /* JADX WARN: Removed duplicated region for block: B:2559:0x27bb  */
    /* JADX WARN: Removed duplicated region for block: B:2561:0x27c1  */
    /* JADX WARN: Removed duplicated region for block: B:2564:0x27d9  */
    /* JADX WARN: Removed duplicated region for block: B:2566:0x27df  */
    /* JADX WARN: Removed duplicated region for block: B:2568:0x27e8  */
    /* JADX WARN: Removed duplicated region for block: B:2570:0x27ee  */
    /* JADX WARN: Removed duplicated region for block: B:2573:0x2804  */
    /* JADX WARN: Removed duplicated region for block: B:2575:0x280a  */
    /* JADX WARN: Removed duplicated region for block: B:2578:0x2819  */
    /* JADX WARN: Removed duplicated region for block: B:2580:0x281f  */
    /* JADX WARN: Removed duplicated region for block: B:2582:0x2828  */
    /* JADX WARN: Removed duplicated region for block: B:2584:0x282e  */
    /* JADX WARN: Removed duplicated region for block: B:2586:0x2837  */
    /* JADX WARN: Removed duplicated region for block: B:2588:0x283d  */
    /* JADX WARN: Removed duplicated region for block: B:2591:0x28a8  */
    /* JADX WARN: Removed duplicated region for block: B:2593:0x28ae  */
    /* JADX WARN: Removed duplicated region for block: B:2596:0x28bf  */
    /* JADX WARN: Removed duplicated region for block: B:2598:0x28c5  */
    /* JADX WARN: Removed duplicated region for block: B:2601:0x28dd  */
    /* JADX WARN: Removed duplicated region for block: B:2603:0x28e3  */
    /* JADX WARN: Removed duplicated region for block: B:2606:0x28f4  */
    /* JADX WARN: Removed duplicated region for block: B:2608:0x28fa  */
    /* JADX WARN: Removed duplicated region for block: B:2611:0x2909  */
    /* JADX WARN: Removed duplicated region for block: B:2613:0x290f  */
    /* JADX WARN: Removed duplicated region for block: B:2616:0x2920  */
    /* JADX WARN: Removed duplicated region for block: B:2618:0x2926  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x050e  */
    /* JADX WARN: Removed duplicated region for block: B:2621:0x2937  */
    /* JADX WARN: Removed duplicated region for block: B:2623:0x293d  */
    /* JADX WARN: Removed duplicated region for block: B:2626:0x294e  */
    /* JADX WARN: Removed duplicated region for block: B:2628:0x2954  */
    /* JADX WARN: Removed duplicated region for block: B:2631:0x2965  */
    /* JADX WARN: Removed duplicated region for block: B:2633:0x296b  */
    /* JADX WARN: Removed duplicated region for block: B:2636:0x297c  */
    /* JADX WARN: Removed duplicated region for block: B:2638:0x2982  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0514 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2641:0x2993  */
    /* JADX WARN: Removed duplicated region for block: B:2643:0x2999  */
    /* JADX WARN: Removed duplicated region for block: B:2646:0x29aa  */
    /* JADX WARN: Removed duplicated region for block: B:2648:0x29b0  */
    /* JADX WARN: Removed duplicated region for block: B:2651:0x29c3  */
    /* JADX WARN: Removed duplicated region for block: B:2653:0x29c9  */
    /* JADX WARN: Removed duplicated region for block: B:2656:0x29da  */
    /* JADX WARN: Removed duplicated region for block: B:2658:0x29e0  */
    /* JADX WARN: Removed duplicated region for block: B:2661:0x29f1  */
    /* JADX WARN: Removed duplicated region for block: B:2663:0x29f7  */
    /* JADX WARN: Removed duplicated region for block: B:2665:0x2a07  */
    /* JADX WARN: Removed duplicated region for block: B:2667:0x2a0f  */
    /* JADX WARN: Removed duplicated region for block: B:2670:0x2a25  */
    /* JADX WARN: Removed duplicated region for block: B:2672:0x2a2b  */
    /* JADX WARN: Removed duplicated region for block: B:2675:0x2a43  */
    /* JADX WARN: Removed duplicated region for block: B:2677:0x2a49  */
    /* JADX WARN: Removed duplicated region for block: B:2679:0x2a59  */
    /* JADX WARN: Removed duplicated region for block: B:2681:0x2a66  */
    /* JADX WARN: Removed duplicated region for block: B:2685:0x2a77  */
    /* JADX WARN: Removed duplicated region for block: B:2688:0x2a85  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:2691:0x2a90  */
    /* JADX WARN: Removed duplicated region for block: B:2693:0x2a9b  */
    /* JADX WARN: Removed duplicated region for block: B:2696:0x2aac  */
    /* JADX WARN: Removed duplicated region for block: B:2698:0x2ab2  */
    /* JADX WARN: Removed duplicated region for block: B:2700:0x2aba  */
    /* JADX WARN: Removed duplicated region for block: B:2704:0x2b1a  */
    /* JADX WARN: Removed duplicated region for block: B:2706:0x2b20  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0530 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2711:0x2b45  */
    /* JADX WARN: Removed duplicated region for block: B:2713:0x2b4b  */
    /* JADX WARN: Removed duplicated region for block: B:2716:0x2b6d  */
    /* JADX WARN: Removed duplicated region for block: B:2718:0x2b73  */
    /* JADX WARN: Removed duplicated region for block: B:2721:0x2b97  */
    /* JADX WARN: Removed duplicated region for block: B:2723:0x2ba2  */
    /* JADX WARN: Removed duplicated region for block: B:2727:0x2bc2  */
    /* JADX WARN: Removed duplicated region for block: B:2729:0x2bcf  */
    /* JADX WARN: Removed duplicated region for block: B:2731:0x2bec  */
    /* JADX WARN: Removed duplicated region for block: B:2733:0x2bf4  */
    /* JADX WARN: Removed duplicated region for block: B:2735:0x2c16  */
    /* JADX WARN: Removed duplicated region for block: B:2737:0x2c1e  */
    /* JADX WARN: Removed duplicated region for block: B:2740:0x2c36  */
    /* JADX WARN: Removed duplicated region for block: B:2742:0x2c41  */
    /* JADX WARN: Removed duplicated region for block: B:2745:0x2c52  */
    /* JADX WARN: Removed duplicated region for block: B:2747:0x2c58  */
    /* JADX WARN: Removed duplicated region for block: B:2750:0x2c69  */
    /* JADX WARN: Removed duplicated region for block: B:2752:0x2c6f  */
    /* JADX WARN: Removed duplicated region for block: B:2755:0x2c91  */
    /* JADX WARN: Removed duplicated region for block: B:2757:0x2c97  */
    /* JADX WARN: Removed duplicated region for block: B:2759:0x2ca0  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:2761:0x2cab  */
    /* JADX WARN: Removed duplicated region for block: B:2763:0x2cb4  */
    /* JADX WARN: Removed duplicated region for block: B:2765:0x2cbf  */
    /* JADX WARN: Removed duplicated region for block: B:2768:0x2cce  */
    /* JADX WARN: Removed duplicated region for block: B:2770:0x2cd4  */
    /* JADX WARN: Removed duplicated region for block: B:2772:0x2cdf  */
    /* JADX WARN: Removed duplicated region for block: B:2774:0x2cec  */
    /* JADX WARN: Removed duplicated region for block: B:2778:0x2cfb  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x054c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2781:0x2d09  */
    /* JADX WARN: Removed duplicated region for block: B:2785:0x2d1a  */
    /* JADX WARN: Removed duplicated region for block: B:2787:0x2d20  */
    /* JADX WARN: Removed duplicated region for block: B:2789:0x2d36  */
    /* JADX WARN: Removed duplicated region for block: B:2792:0x2d3f  */
    /* JADX WARN: Removed duplicated region for block: B:2795:0x2d4a  */
    /* JADX WARN: Removed duplicated region for block: B:2797:0x2d55  */
    /* JADX WARN: Removed duplicated region for block: B:2801:0x2d66  */
    /* JADX WARN: Removed duplicated region for block: B:2804:0x2d74  */
    /* JADX WARN: Removed duplicated region for block: B:2807:0x2d7f  */
    /* JADX WARN: Removed duplicated region for block: B:2809:0x2d8a  */
    /* JADX WARN: Removed duplicated region for block: B:2811:0x2d93  */
    /* JADX WARN: Removed duplicated region for block: B:2813:0x2d9e  */
    /* JADX WARN: Removed duplicated region for block: B:2815:0x2da7  */
    /* JADX WARN: Removed duplicated region for block: B:2817:0x2db2  */
    /* JADX WARN: Removed duplicated region for block: B:2819:0x2dba  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x055f  */
    /* JADX WARN: Removed duplicated region for block: B:2822:0x2dca  */
    /* JADX WARN: Removed duplicated region for block: B:2825:0x2dd8  */
    /* JADX WARN: Removed duplicated region for block: B:2828:0x2de3  */
    /* JADX WARN: Removed duplicated region for block: B:2830:0x2dee  */
    /* JADX WARN: Removed duplicated region for block: B:2834:0x2dff  */
    /* JADX WARN: Removed duplicated region for block: B:2837:0x2e0d  */
    /* JADX WARN: Removed duplicated region for block: B:2840:0x2e18  */
    /* JADX WARN: Removed duplicated region for block: B:2842:0x2e23  */
    /* JADX WARN: Removed duplicated region for block: B:2844:0x2e2c  */
    /* JADX WARN: Removed duplicated region for block: B:2846:0x2e37  */
    /* JADX WARN: Removed duplicated region for block: B:2848:0x2e40  */
    /* JADX WARN: Removed duplicated region for block: B:2850:0x2e46  */
    /* JADX WARN: Removed duplicated region for block: B:2852:0x2e4e  */
    /* JADX WARN: Removed duplicated region for block: B:2856:0x2e68  */
    /* JADX WARN: Removed duplicated region for block: B:2858:0x2e6e  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0577  */
    /* JADX WARN: Removed duplicated region for block: B:2860:0x2e77  */
    /* JADX WARN: Removed duplicated region for block: B:2862:0x2e82  */
    /* JADX WARN: Removed duplicated region for block: B:2866:0x2e93  */
    /* JADX WARN: Removed duplicated region for block: B:2869:0x2ea1  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0579  */
    /* JADX WARN: Removed duplicated region for block: B:2872:0x2eac  */
    /* JADX WARN: Removed duplicated region for block: B:2874:0x2eb7  */
    /* JADX WARN: Removed duplicated region for block: B:2876:0x2ec0  */
    /* JADX WARN: Removed duplicated region for block: B:2878:0x2ecb  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0586  */
    /* JADX WARN: Removed duplicated region for block: B:2881:0x2edc  */
    /* JADX WARN: Removed duplicated region for block: B:2883:0x2ee2  */
    /* JADX WARN: Removed duplicated region for block: B:2888:0x2efd  */
    /* JADX WARN: Removed duplicated region for block: B:2890:0x2f03  */
    /* JADX WARN: Removed duplicated region for block: B:2893:0x2f14  */
    /* JADX WARN: Removed duplicated region for block: B:2895:0x2f1a  */
    /* JADX WARN: Removed duplicated region for block: B:2897:0x2f23  */
    /* JADX WARN: Removed duplicated region for block: B:2899:0x2f29  */
    /* JADX WARN: Removed duplicated region for block: B:2902:0x2f38  */
    /* JADX WARN: Removed duplicated region for block: B:2904:0x2f3e  */
    /* JADX WARN: Removed duplicated region for block: B:2906:0x2f46  */
    /* JADX WARN: Removed duplicated region for block: B:2909:0x2f60  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:2911:0x2f68  */
    /* JADX WARN: Removed duplicated region for block: B:2915:0x2f79  */
    /* JADX WARN: Removed duplicated region for block: B:2918:0x2f82  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0598  */
    /* JADX WARN: Removed duplicated region for block: B:2921:0x2f8d  */
    /* JADX WARN: Removed duplicated region for block: B:2923:0x2f93  */
    /* JADX WARN: Removed duplicated region for block: B:2925:0x2f9c  */
    /* JADX WARN: Removed duplicated region for block: B:2927:0x2fa7  */
    /* JADX WARN: Removed duplicated region for block: B:2931:0x2fb8  */
    /* JADX WARN: Removed duplicated region for block: B:2934:0x2fc6  */
    /* JADX WARN: Removed duplicated region for block: B:2937:0x2fd1  */
    /* JADX WARN: Removed duplicated region for block: B:2939:0x2fd7  */
    /* JADX WARN: Removed duplicated region for block: B:2941:0x2fe0  */
    /* JADX WARN: Removed duplicated region for block: B:2943:0x2fe6  */
    /* JADX WARN: Removed duplicated region for block: B:2946:0x2ff7  */
    /* JADX WARN: Removed duplicated region for block: B:2948:0x2ffd  */
    /* JADX WARN: Removed duplicated region for block: B:2953:0x3018  */
    /* JADX WARN: Removed duplicated region for block: B:2955:0x301e  */
    /* JADX WARN: Removed duplicated region for block: B:2958:0x302f  */
    /* JADX WARN: Removed duplicated region for block: B:2960:0x3035  */
    /* JADX WARN: Removed duplicated region for block: B:2962:0x303e  */
    /* JADX WARN: Removed duplicated region for block: B:2964:0x3044  */
    /* JADX WARN: Removed duplicated region for block: B:2967:0x3055  */
    /* JADX WARN: Removed duplicated region for block: B:2969:0x305b  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x05b0  */
    /* JADX WARN: Removed duplicated region for block: B:2974:0x3076  */
    /* JADX WARN: Removed duplicated region for block: B:2976:0x307c  */
    /* JADX WARN: Removed duplicated region for block: B:2979:0x308d  */
    /* JADX WARN: Removed duplicated region for block: B:297:0x05b2  */
    /* JADX WARN: Removed duplicated region for block: B:2981:0x3093  */
    /* JADX WARN: Removed duplicated region for block: B:2983:0x309c  */
    /* JADX WARN: Removed duplicated region for block: B:2985:0x30a2  */
    /* JADX WARN: Removed duplicated region for block: B:2988:0x30b3  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x05b5  */
    /* JADX WARN: Removed duplicated region for block: B:2990:0x30b9  */
    /* JADX WARN: Removed duplicated region for block: B:2994:0x30ca  */
    /* JADX WARN: Removed duplicated region for block: B:2997:0x30d8  */
    /* JADX WARN: Removed duplicated region for block: B:3000:0x30e3  */
    /* JADX WARN: Removed duplicated region for block: B:3002:0x30ee  */
    /* JADX WARN: Removed duplicated region for block: B:3004:0x30f7  */
    /* JADX WARN: Removed duplicated region for block: B:3006:0x3102  */
    /* JADX WARN: Removed duplicated region for block: B:3009:0x3113  */
    /* JADX WARN: Removed duplicated region for block: B:3011:0x3119  */
    /* JADX WARN: Removed duplicated region for block: B:3016:0x3134  */
    /* JADX WARN: Removed duplicated region for block: B:3018:0x313a  */
    /* JADX WARN: Removed duplicated region for block: B:3021:0x314b  */
    /* JADX WARN: Removed duplicated region for block: B:3023:0x3151  */
    /* JADX WARN: Removed duplicated region for block: B:3025:0x315a  */
    /* JADX WARN: Removed duplicated region for block: B:3027:0x3160  */
    /* JADX WARN: Removed duplicated region for block: B:3029:0x3169  */
    /* JADX WARN: Removed duplicated region for block: B:3031:0x316f  */
    /* JADX WARN: Removed duplicated region for block: B:3035:0x3180  */
    /* JADX WARN: Removed duplicated region for block: B:3038:0x3189  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x05d4  */
    /* JADX WARN: Removed duplicated region for block: B:3041:0x3194  */
    /* JADX WARN: Removed duplicated region for block: B:3043:0x319a  */
    /* JADX WARN: Removed duplicated region for block: B:3046:0x31ab  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x05d6  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x05d9  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x05f0  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x05f2  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x060f  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0611  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0614  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0627  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x0629  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x062c  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0643  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0645  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x0649  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x0663  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x0684  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x068e  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x0690  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0693  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x06ad  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x06cc  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x06d6  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x06d8  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x06db  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x06e1  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x06e4  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x06ff  */
    /* JADX WARN: Removed duplicated region for block: B:387:0x0701  */
    /* JADX WARN: Removed duplicated region for block: B:388:0x070e  */
    /* JADX WARN: Removed duplicated region for block: B:391:0x0719  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x071b  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0726  */
    /* JADX WARN: Removed duplicated region for block: B:397:0x0728  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x072b  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x0732  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0741  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x0754  */
    /* JADX WARN: Removed duplicated region for block: B:411:0x0756  */
    /* JADX WARN: Removed duplicated region for block: B:412:0x0763  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0781  */
    /* JADX WARN: Removed duplicated region for block: B:420:0x0783  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x0786  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x0790  */
    /* JADX WARN: Removed duplicated region for block: B:426:0x0792  */
    /* JADX WARN: Removed duplicated region for block: B:427:0x0795  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x07ab A[PHI: r12
  0x07ab: PHI (r12v27 long) = (r12v25 long), (r12v23 long) binds: [B:445:0x07c7, B:432:0x07a8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:435:0x07ad  */
    /* JADX WARN: Removed duplicated region for block: B:436:0x07af  */
    /* JADX WARN: Removed duplicated region for block: B:437:0x07b2  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x07bc  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x07be  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x07c2  */
    /* JADX WARN: Removed duplicated region for block: B:447:0x07ca  */
    /* JADX WARN: Removed duplicated region for block: B:456:0x07f2  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x080f  */
    /* JADX WARN: Removed duplicated region for block: B:461:0x0811  */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0820  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x0840  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x0842  */
    /* JADX WARN: Removed duplicated region for block: B:471:0x0847  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0853  */
    /* JADX WARN: Removed duplicated region for block: B:477:0x0855  */
    /* JADX WARN: Removed duplicated region for block: B:478:0x0858  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0877  */
    /* JADX WARN: Removed duplicated region for block: B:488:0x0879  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x087e  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x0891  */
    /* JADX WARN: Removed duplicated region for block: B:495:0x0893  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x0896  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x08bb  */
    /* JADX WARN: Removed duplicated region for block: B:508:0x08bd  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x08c0  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x08ce  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x08d1  */
    /* JADX WARN: Removed duplicated region for block: B:517:0x08e0  */
    /* JADX WARN: Removed duplicated region for block: B:521:0x08ec  */
    /* JADX WARN: Removed duplicated region for block: B:522:0x08ee  */
    /* JADX WARN: Removed duplicated region for block: B:523:0x08f3  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x0911 A[PHI: r12
  0x0911: PHI (r12v37 long) = (r12v33 long), (r12v31 long) binds: [B:541:0x092c, B:528:0x090e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:531:0x0914  */
    /* JADX WARN: Removed duplicated region for block: B:532:0x0916  */
    /* JADX WARN: Removed duplicated region for block: B:533:0x0919  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x0923  */
    /* JADX WARN: Removed duplicated region for block: B:538:0x0925  */
    /* JADX WARN: Removed duplicated region for block: B:539:0x0928  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x092f  */
    /* JADX WARN: Removed duplicated region for block: B:550:0x0948  */
    /* JADX WARN: Removed duplicated region for block: B:551:0x094a  */
    /* JADX WARN: Removed duplicated region for block: B:552:0x094f  */
    /* JADX WARN: Removed duplicated region for block: B:557:0x095d  */
    /* JADX WARN: Removed duplicated region for block: B:558:0x095f  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x0965  */
    /* JADX WARN: Removed duplicated region for block: B:562:0x096e  */
    /* JADX WARN: Removed duplicated region for block: B:563:0x0971  */
    /* JADX WARN: Removed duplicated region for block: B:578:0x09c0 A[PHI: r8
  0x09c0: PHI (r8v34 long) = (r8v29 long), (r8v27 long) binds: [B:589:0x09da, B:576:0x09bd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:682:0x0b3b  */
    /* JADX WARN: Removed duplicated region for block: B:683:0x0b3d  */
    /* JADX WARN: Removed duplicated region for block: B:684:0x0b45  */
    /* JADX WARN: Removed duplicated region for block: B:687:0x0b4d  */
    /* JADX WARN: Removed duplicated region for block: B:688:0x0b4f  */
    /* JADX WARN: Removed duplicated region for block: B:691:0x0b53  */
    /* JADX WARN: Removed duplicated region for block: B:692:0x0b55  */
    /* JADX WARN: Removed duplicated region for block: B:696:0x0b60  */
    /* JADX WARN: Removed duplicated region for block: B:697:0x0b62  */
    /* JADX WARN: Removed duplicated region for block: B:698:0x0b65  */
    /* JADX WARN: Removed duplicated region for block: B:700:0x0b6e  */
    /* JADX WARN: Removed duplicated region for block: B:701:0x0b71  */
    /* JADX WARN: Removed duplicated region for block: B:707:0x0b84 A[PHI: r8
  0x0b84: PHI (r8v39 long) = (r8v37 long), (r8v35 long) binds: [B:718:0x0b9f, B:705:0x0b81] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:708:0x0b87  */
    /* JADX WARN: Removed duplicated region for block: B:709:0x0b89  */
    /* JADX WARN: Removed duplicated region for block: B:710:0x0b8c  */
    /* JADX WARN: Removed duplicated region for block: B:714:0x0b96  */
    /* JADX WARN: Removed duplicated region for block: B:715:0x0b98  */
    /* JADX WARN: Removed duplicated region for block: B:716:0x0b9b  */
    /* JADX WARN: Removed duplicated region for block: B:720:0x0ba2  */
    /* JADX WARN: Removed duplicated region for block: B:727:0x0bbb  */
    /* JADX WARN: Removed duplicated region for block: B:728:0x0bbd  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x0bc2  */
    /* JADX WARN: Removed duplicated region for block: B:737:0x0be5  */
    /* JADX WARN: Removed duplicated region for block: B:738:0x0be7  */
    /* JADX WARN: Removed duplicated region for block: B:739:0x0bec  */
    /* JADX WARN: Removed duplicated region for block: B:744:0x0bfa  */
    /* JADX WARN: Removed duplicated region for block: B:745:0x0bfc  */
    /* JADX WARN: Removed duplicated region for block: B:746:0x0c00  */
    /* JADX WARN: Removed duplicated region for block: B:754:0x0c1c  */
    /* JADX WARN: Removed duplicated region for block: B:755:0x0c1e  */
    /* JADX WARN: Removed duplicated region for block: B:756:0x0c23  */
    /* JADX WARN: Removed duplicated region for block: B:761:0x0c2f  */
    /* JADX WARN: Removed duplicated region for block: B:762:0x0c31  */
    /* JADX WARN: Removed duplicated region for block: B:763:0x0c39  */
    /* JADX WARN: Removed duplicated region for block: B:765:0x0c3d  */
    /* JADX WARN: Removed duplicated region for block: B:766:0x0c40  */
    /* JADX WARN: Removed duplicated region for block: B:808:0x0cee A[PHI: r10
  0x0cee: PHI (r10v43 long) = (r10v41 long), (r10v38 long) binds: [B:818:0x0d0a, B:806:0x0ceb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:872:0x0de1 A[PHI: r8
  0x0de1: PHI (r8v46 long) = (r8v42 long), (r8v40 long) binds: [B:883:0x0dfc, B:870:0x0dde] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:959:0x0f29 A[PHI: r8
  0x0f29: PHI (r8v51 long) = (r8v49 long), (r8v47 long) binds: [B:971:0x0f43, B:957:0x0f26] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:979:0x0f56  */
    /* JADX WARN: Removed duplicated region for block: B:980:0x0f58  */
    /* JADX WARN: Removed duplicated region for block: B:981:0x0f5d  */
    /* JADX WARN: Removed duplicated region for block: B:986:0x0f69  */
    /* JADX WARN: Removed duplicated region for block: B:987:0x0f6b  */
    /* JADX WARN: Removed duplicated region for block: B:988:0x0f6e  */
    /* JADX WARN: Removed duplicated region for block: B:990:0x0f74  */
    /* JADX WARN: Removed duplicated region for block: B:991:0x0f77  */
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
            Method dump skipped, instruction units count: 12750
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.databinding.FragmentActiveSafeBindingLdrtlLandImpl.executeBindings():void");
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
        if (i == 9) {
            synchronized (this) {
                this.K1 |= 64;
            }
            return true;
        }
        if (i == 3) {
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

    public FragmentActiveSafeBindingLdrtlLandImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
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
