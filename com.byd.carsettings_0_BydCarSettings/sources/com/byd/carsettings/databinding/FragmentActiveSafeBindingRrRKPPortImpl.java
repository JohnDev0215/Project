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
public class FragmentActiveSafeBindingRrRKPPortImpl extends FragmentActiveSafeBinding {

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
        sparseIntArray.put(R.id.tv_front_lateral_approach_warning, 114);
        sparseIntArray.put(R.id.tv_front_lateral_approach_braking, 115);
        sparseIntArray.put(R.id.tv_rear_lateral_approach_warning, 116);
        sparseIntArray.put(R.id.tv_rear_lateral_approach_braking, 117);
        sparseIntArray.put(R.id.tv_title_traffic_signs_recognition, 118);
        sparseIntArray.put(R.id.lottie_animation_traffic_signs_recogniation, 119);
        sparseIntArray.put(R.id.slif_title, 120);
        sparseIntArray.put(R.id.tv_title_traffic_sound_remind_recognition, 121);
        sparseIntArray.put(R.id.tv_intelligent_speed_control_alarm, 122);
        sparseIntArray.put(R.id.tv_intelligent_speed_control, 123);
        sparseIntArray.put(R.id.tv_tsr_flow_title, 124);
        sparseIntArray.put(R.id.tv_limit_ctrl_type, 125);
        sparseIntArray.put(R.id.tv_speed_control_alarm_sensitivity, 126);
        sparseIntArray.put(R.id.tv_bsis, 127);
        sparseIntArray.put(R.id.tv_mois, 128);
        sparseIntArray.put(R.id.tv_title_gas_pedal, 129);
    }

    public FragmentActiveSafeBindingRrRKPPortImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 130, B1, C1));
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

    /* JADX WARN: Removed duplicated region for block: B:1019:0x0fe4  */
    /* JADX WARN: Removed duplicated region for block: B:1020:0x0fe6  */
    /* JADX WARN: Removed duplicated region for block: B:1021:0x0ff3  */
    /* JADX WARN: Removed duplicated region for block: B:1024:0x0fff  */
    /* JADX WARN: Removed duplicated region for block: B:1025:0x1006  */
    /* JADX WARN: Removed duplicated region for block: B:1030:0x101c  */
    /* JADX WARN: Removed duplicated region for block: B:1031:0x101e  */
    /* JADX WARN: Removed duplicated region for block: B:1032:0x1021  */
    /* JADX WARN: Removed duplicated region for block: B:1037:0x102f  */
    /* JADX WARN: Removed duplicated region for block: B:1038:0x1031  */
    /* JADX WARN: Removed duplicated region for block: B:1039:0x1034  */
    /* JADX WARN: Removed duplicated region for block: B:1041:0x103d  */
    /* JADX WARN: Removed duplicated region for block: B:1045:0x1049  */
    /* JADX WARN: Removed duplicated region for block: B:1047:0x104d  */
    /* JADX WARN: Removed duplicated region for block: B:1048:0x1050  */
    /* JADX WARN: Removed duplicated region for block: B:1055:0x1073  */
    /* JADX WARN: Removed duplicated region for block: B:1056:0x1075  */
    /* JADX WARN: Removed duplicated region for block: B:1057:0x1082  */
    /* JADX WARN: Removed duplicated region for block: B:1064:0x109e  */
    /* JADX WARN: Removed duplicated region for block: B:1065:0x10a0  */
    /* JADX WARN: Removed duplicated region for block: B:1066:0x10a5  */
    /* JADX WARN: Removed duplicated region for block: B:1073:0x10c2  */
    /* JADX WARN: Removed duplicated region for block: B:1074:0x10c4  */
    /* JADX WARN: Removed duplicated region for block: B:1075:0x10c9  */
    /* JADX WARN: Removed duplicated region for block: B:1080:0x10dc  */
    /* JADX WARN: Removed duplicated region for block: B:1081:0x10de  */
    /* JADX WARN: Removed duplicated region for block: B:1082:0x10e1  */
    /* JADX WARN: Removed duplicated region for block: B:1084:0x10e8  */
    /* JADX WARN: Removed duplicated region for block: B:1085:0x10eb  */
    /* JADX WARN: Removed duplicated region for block: B:1095:0x110f  */
    /* JADX WARN: Removed duplicated region for block: B:1098:0x1118 A[PHI: r97
  0x1118: PHI (r97v22 long) = (r97v21 long), (r97v19 long) binds: [B:1110:0x1134, B:1096:0x1115] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1099:0x111a  */
    /* JADX WARN: Removed duplicated region for block: B:1101:0x111e  */
    /* JADX WARN: Removed duplicated region for block: B:1102:0x1120  */
    /* JADX WARN: Removed duplicated region for block: B:1104:0x1123  */
    /* JADX WARN: Removed duplicated region for block: B:1106:0x1129  */
    /* JADX WARN: Removed duplicated region for block: B:1107:0x112b  */
    /* JADX WARN: Removed duplicated region for block: B:1108:0x112f  */
    /* JADX WARN: Removed duplicated region for block: B:1111:0x1136  */
    /* JADX WARN: Removed duplicated region for block: B:1119:0x1158  */
    /* JADX WARN: Removed duplicated region for block: B:1120:0x115a  */
    /* JADX WARN: Removed duplicated region for block: B:1121:0x1167  */
    /* JADX WARN: Removed duplicated region for block: B:1124:0x1173  */
    /* JADX WARN: Removed duplicated region for block: B:1127:0x117c A[PHI: r97
  0x117c: PHI (r97v27 long) = (r97v25 long), (r97v23 long) binds: [B:1139:0x1196, B:1125:0x1179] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1128:0x117e  */
    /* JADX WARN: Removed duplicated region for block: B:1130:0x1182  */
    /* JADX WARN: Removed duplicated region for block: B:1131:0x1184  */
    /* JADX WARN: Removed duplicated region for block: B:1135:0x118d  */
    /* JADX WARN: Removed duplicated region for block: B:1136:0x118f  */
    /* JADX WARN: Removed duplicated region for block: B:1137:0x1192  */
    /* JADX WARN: Removed duplicated region for block: B:1141:0x1199  */
    /* JADX WARN: Removed duplicated region for block: B:1143:0x119d  */
    /* JADX WARN: Removed duplicated region for block: B:1146:0x11ac  */
    /* JADX WARN: Removed duplicated region for block: B:1150:0x11c6  */
    /* JADX WARN: Removed duplicated region for block: B:1151:0x11c8  */
    /* JADX WARN: Removed duplicated region for block: B:1152:0x11d5  */
    /* JADX WARN: Removed duplicated region for block: B:1159:0x11f3  */
    /* JADX WARN: Removed duplicated region for block: B:1160:0x11f5  */
    /* JADX WARN: Removed duplicated region for block: B:1161:0x11fa  */
    /* JADX WARN: Removed duplicated region for block: B:1165:0x120a  */
    /* JADX WARN: Removed duplicated region for block: B:1166:0x120c  */
    /* JADX WARN: Removed duplicated region for block: B:1167:0x1212  */
    /* JADX WARN: Removed duplicated region for block: B:1170:0x121b  */
    /* JADX WARN: Removed duplicated region for block: B:1171:0x121e  */
    /* JADX WARN: Removed duplicated region for block: B:1179:0x123a  */
    /* JADX WARN: Removed duplicated region for block: B:1182:0x1243 A[PHI: r97
  0x1243: PHI (r97v34 long) = (r97v33 long), (r97v31 long) binds: [B:1194:0x1260, B:1180:0x1240] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1183:0x1246  */
    /* JADX WARN: Removed duplicated region for block: B:1185:0x124a  */
    /* JADX WARN: Removed duplicated region for block: B:1186:0x124d  */
    /* JADX WARN: Removed duplicated region for block: B:1188:0x1251  */
    /* JADX WARN: Removed duplicated region for block: B:1190:0x1257  */
    /* JADX WARN: Removed duplicated region for block: B:1191:0x1259  */
    /* JADX WARN: Removed duplicated region for block: B:1192:0x125c  */
    /* JADX WARN: Removed duplicated region for block: B:1196:0x1263  */
    /* JADX WARN: Removed duplicated region for block: B:1203:0x1283  */
    /* JADX WARN: Removed duplicated region for block: B:1204:0x1285  */
    /* JADX WARN: Removed duplicated region for block: B:1205:0x1292  */
    /* JADX WARN: Removed duplicated region for block: B:1208:0x129e  */
    /* JADX WARN: Removed duplicated region for block: B:1211:0x12a7 A[PHI: r97
  0x12a7: PHI (r97v40 long) = (r97v38 long), (r97v35 long) binds: [B:1222:0x12c4, B:1209:0x12a4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1212:0x12a9  */
    /* JADX WARN: Removed duplicated region for block: B:1214:0x12ad  */
    /* JADX WARN: Removed duplicated region for block: B:1215:0x12af  */
    /* JADX WARN: Removed duplicated region for block: B:1219:0x12b8  */
    /* JADX WARN: Removed duplicated region for block: B:1220:0x12ba  */
    /* JADX WARN: Removed duplicated region for block: B:1221:0x12bd  */
    /* JADX WARN: Removed duplicated region for block: B:1224:0x12c7  */
    /* JADX WARN: Removed duplicated region for block: B:1226:0x12cb  */
    /* JADX WARN: Removed duplicated region for block: B:1227:0x12d0  */
    /* JADX WARN: Removed duplicated region for block: B:1234:0x12f7  */
    /* JADX WARN: Removed duplicated region for block: B:1235:0x12f9  */
    /* JADX WARN: Removed duplicated region for block: B:1236:0x1306  */
    /* JADX WARN: Removed duplicated region for block: B:1242:0x1322 A[PHI: r97
  0x1322: PHI (r97v51 long) = (r97v43 long), (r97v41 long) binds: [B:1253:0x133d, B:1240:0x131f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1243:0x1325  */
    /* JADX WARN: Removed duplicated region for block: B:1244:0x1327  */
    /* JADX WARN: Removed duplicated region for block: B:1245:0x132a  */
    /* JADX WARN: Removed duplicated region for block: B:1249:0x1334  */
    /* JADX WARN: Removed duplicated region for block: B:1250:0x1336  */
    /* JADX WARN: Removed duplicated region for block: B:1251:0x1339  */
    /* JADX WARN: Removed duplicated region for block: B:1255:0x1340  */
    /* JADX WARN: Removed duplicated region for block: B:1261:0x1355  */
    /* JADX WARN: Removed duplicated region for block: B:1262:0x1357  */
    /* JADX WARN: Removed duplicated region for block: B:1263:0x135c  */
    /* JADX WARN: Removed duplicated region for block: B:1268:0x136f  */
    /* JADX WARN: Removed duplicated region for block: B:1269:0x1371  */
    /* JADX WARN: Removed duplicated region for block: B:1270:0x1374  */
    /* JADX WARN: Removed duplicated region for block: B:1272:0x137d  */
    /* JADX WARN: Removed duplicated region for block: B:1273:0x1380  */
    /* JADX WARN: Removed duplicated region for block: B:1279:0x1395  */
    /* JADX WARN: Removed duplicated region for block: B:1280:0x1397  */
    /* JADX WARN: Removed duplicated region for block: B:1281:0x139c  */
    /* JADX WARN: Removed duplicated region for block: B:1286:0x13af  */
    /* JADX WARN: Removed duplicated region for block: B:1287:0x13b1  */
    /* JADX WARN: Removed duplicated region for block: B:1288:0x13b5  */
    /* JADX WARN: Removed duplicated region for block: B:1295:0x13cd  */
    /* JADX WARN: Removed duplicated region for block: B:1296:0x13cf  */
    /* JADX WARN: Removed duplicated region for block: B:1297:0x13d4  */
    /* JADX WARN: Removed duplicated region for block: B:1307:0x1402  */
    /* JADX WARN: Removed duplicated region for block: B:1311:0x141c  */
    /* JADX WARN: Removed duplicated region for block: B:1312:0x141e  */
    /* JADX WARN: Removed duplicated region for block: B:1313:0x142b  */
    /* JADX WARN: Removed duplicated region for block: B:1319:0x1447 A[PHI: r10
  0x1447: PHI (r10v57 long) = (r10v50 long), (r10v47 long) binds: [B:1329:0x1465, B:1317:0x1444] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1320:0x144a  */
    /* JADX WARN: Removed duplicated region for block: B:1321:0x144c  */
    /* JADX WARN: Removed duplicated region for block: B:1322:0x144f  */
    /* JADX WARN: Removed duplicated region for block: B:1326:0x1459  */
    /* JADX WARN: Removed duplicated region for block: B:1327:0x145b  */
    /* JADX WARN: Removed duplicated region for block: B:1328:0x145e  */
    /* JADX WARN: Removed duplicated region for block: B:1331:0x1468  */
    /* JADX WARN: Removed duplicated region for block: B:1337:0x147d  */
    /* JADX WARN: Removed duplicated region for block: B:1338:0x147f  */
    /* JADX WARN: Removed duplicated region for block: B:1339:0x1484  */
    /* JADX WARN: Removed duplicated region for block: B:1344:0x1497  */
    /* JADX WARN: Removed duplicated region for block: B:1345:0x1499  */
    /* JADX WARN: Removed duplicated region for block: B:1346:0x149f  */
    /* JADX WARN: Removed duplicated region for block: B:1349:0x14a8  */
    /* JADX WARN: Removed duplicated region for block: B:1350:0x14ab  */
    /* JADX WARN: Removed duplicated region for block: B:1358:0x14c6  */
    /* JADX WARN: Removed duplicated region for block: B:1362:0x14d7  */
    /* JADX WARN: Removed duplicated region for block: B:1363:0x14d9  */
    /* JADX WARN: Removed duplicated region for block: B:1364:0x14de  */
    /* JADX WARN: Removed duplicated region for block: B:1369:0x14f1  */
    /* JADX WARN: Removed duplicated region for block: B:1370:0x14f3  */
    /* JADX WARN: Removed duplicated region for block: B:1371:0x14f6  */
    /* JADX WARN: Removed duplicated region for block: B:1378:0x150c  */
    /* JADX WARN: Removed duplicated region for block: B:1379:0x150e  */
    /* JADX WARN: Removed duplicated region for block: B:1380:0x1513  */
    /* JADX WARN: Removed duplicated region for block: B:1388:0x1536  */
    /* JADX WARN: Removed duplicated region for block: B:1389:0x1538  */
    /* JADX WARN: Removed duplicated region for block: B:1390:0x1545  */
    /* JADX WARN: Removed duplicated region for block: B:1393:0x1551  */
    /* JADX WARN: Removed duplicated region for block: B:1394:0x1558  */
    /* JADX WARN: Removed duplicated region for block: B:1396:0x155b  */
    /* JADX WARN: Removed duplicated region for block: B:1397:0x155d  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0322  */
    /* JADX WARN: Removed duplicated region for block: B:1402:0x156c  */
    /* JADX WARN: Removed duplicated region for block: B:1403:0x156e  */
    /* JADX WARN: Removed duplicated region for block: B:1404:0x1571  */
    /* JADX WARN: Removed duplicated region for block: B:1407:0x1577  */
    /* JADX WARN: Removed duplicated region for block: B:1408:0x1579  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:1411:0x157e  */
    /* JADX WARN: Removed duplicated region for block: B:1412:0x1580  */
    /* JADX WARN: Removed duplicated region for block: B:1419:0x15a6  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:1420:0x15a8  */
    /* JADX WARN: Removed duplicated region for block: B:1421:0x15b5  */
    /* JADX WARN: Removed duplicated region for block: B:1428:0x15d1  */
    /* JADX WARN: Removed duplicated region for block: B:1429:0x15d3  */
    /* JADX WARN: Removed duplicated region for block: B:1430:0x15d8  */
    /* JADX WARN: Removed duplicated region for block: B:1437:0x15f5  */
    /* JADX WARN: Removed duplicated region for block: B:1438:0x15f7  */
    /* JADX WARN: Removed duplicated region for block: B:1439:0x15fc  */
    /* JADX WARN: Removed duplicated region for block: B:1444:0x160f  */
    /* JADX WARN: Removed duplicated region for block: B:1445:0x1611  */
    /* JADX WARN: Removed duplicated region for block: B:1446:0x1615  */
    /* JADX WARN: Removed duplicated region for block: B:1449:0x161c  */
    /* JADX WARN: Removed duplicated region for block: B:1450:0x161f  */
    /* JADX WARN: Removed duplicated region for block: B:1456:0x1634  */
    /* JADX WARN: Removed duplicated region for block: B:1457:0x1636  */
    /* JADX WARN: Removed duplicated region for block: B:1458:0x163b  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0347  */
    /* JADX WARN: Removed duplicated region for block: B:1463:0x164e  */
    /* JADX WARN: Removed duplicated region for block: B:1464:0x1650  */
    /* JADX WARN: Removed duplicated region for block: B:1465:0x1654  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:1475:0x167a  */
    /* JADX WARN: Removed duplicated region for block: B:1478:0x1683 A[PHI: r97
  0x1683: PHI (r97v55 long) = (r97v54 long), (r97v52 long) binds: [B:1490:0x169d, B:1476:0x1680] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:1479:0x1685  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:1481:0x1689  */
    /* JADX WARN: Removed duplicated region for block: B:1482:0x168b  */
    /* JADX WARN: Removed duplicated region for block: B:1484:0x168e  */
    /* JADX WARN: Removed duplicated region for block: B:1486:0x1694  */
    /* JADX WARN: Removed duplicated region for block: B:1487:0x1696  */
    /* JADX WARN: Removed duplicated region for block: B:1488:0x1699  */
    /* JADX WARN: Removed duplicated region for block: B:1492:0x16a0  */
    /* JADX WARN: Removed duplicated region for block: B:1499:0x16d1  */
    /* JADX WARN: Removed duplicated region for block: B:1500:0x16d3  */
    /* JADX WARN: Removed duplicated region for block: B:1501:0x16da  */
    /* JADX WARN: Removed duplicated region for block: B:1508:0x16fd  */
    /* JADX WARN: Removed duplicated region for block: B:1509:0x16ff  */
    /* JADX WARN: Removed duplicated region for block: B:1510:0x170b  */
    /* JADX WARN: Removed duplicated region for block: B:1512:0x1713  */
    /* JADX WARN: Removed duplicated region for block: B:1513:0x1716  */
    /* JADX WARN: Removed duplicated region for block: B:1515:0x171a  */
    /* JADX WARN: Removed duplicated region for block: B:1516:0x171c  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:1520:0x1727  */
    /* JADX WARN: Removed duplicated region for block: B:1521:0x1729  */
    /* JADX WARN: Removed duplicated region for block: B:1522:0x172c  */
    /* JADX WARN: Removed duplicated region for block: B:1527:0x173a  */
    /* JADX WARN: Removed duplicated region for block: B:1528:0x173c  */
    /* JADX WARN: Removed duplicated region for block: B:1529:0x1740  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:1539:0x1767  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:1543:0x1776  */
    /* JADX WARN: Removed duplicated region for block: B:1544:0x1778  */
    /* JADX WARN: Removed duplicated region for block: B:1545:0x177d  */
    /* JADX WARN: Removed duplicated region for block: B:1550:0x1790  */
    /* JADX WARN: Removed duplicated region for block: B:1551:0x1792  */
    /* JADX WARN: Removed duplicated region for block: B:1552:0x1795  */
    /* JADX WARN: Removed duplicated region for block: B:1559:0x17ac  */
    /* JADX WARN: Removed duplicated region for block: B:1560:0x17ae  */
    /* JADX WARN: Removed duplicated region for block: B:1561:0x17b3  */
    /* JADX WARN: Removed duplicated region for block: B:1566:0x17c6  */
    /* JADX WARN: Removed duplicated region for block: B:1567:0x17c8  */
    /* JADX WARN: Removed duplicated region for block: B:1568:0x17cb  */
    /* JADX WARN: Removed duplicated region for block: B:1571:0x17d1  */
    /* JADX WARN: Removed duplicated region for block: B:1572:0x17d4  */
    /* JADX WARN: Removed duplicated region for block: B:1579:0x17eb  */
    /* JADX WARN: Removed duplicated region for block: B:1580:0x17ed  */
    /* JADX WARN: Removed duplicated region for block: B:1581:0x17f0  */
    /* JADX WARN: Removed duplicated region for block: B:1585:0x17fa  */
    /* JADX WARN: Removed duplicated region for block: B:1586:0x17fc  */
    /* JADX WARN: Removed duplicated region for block: B:1587:0x17ff  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:1594:0x1818  */
    /* JADX WARN: Removed duplicated region for block: B:1595:0x181a  */
    /* JADX WARN: Removed duplicated region for block: B:1596:0x181f  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0380  */
    /* JADX WARN: Removed duplicated region for block: B:1604:0x1845  */
    /* JADX WARN: Removed duplicated region for block: B:1607:0x184a  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0387  */
    /* JADX WARN: Removed duplicated region for block: B:1610:0x1852  */
    /* JADX WARN: Removed duplicated region for block: B:1611:0x1857  */
    /* JADX WARN: Removed duplicated region for block: B:1619:0x1935  */
    /* JADX WARN: Removed duplicated region for block: B:1620:0x1937  */
    /* JADX WARN: Removed duplicated region for block: B:1621:0x1944  */
    /* JADX WARN: Removed duplicated region for block: B:1624:0x1950  */
    /* JADX WARN: Removed duplicated region for block: B:1625:0x1952  */
    /* JADX WARN: Removed duplicated region for block: B:1629:0x195d  */
    /* JADX WARN: Removed duplicated region for block: B:1630:0x195f  */
    /* JADX WARN: Removed duplicated region for block: B:1631:0x1965  */
    /* JADX WARN: Removed duplicated region for block: B:1634:0x196e  */
    /* JADX WARN: Removed duplicated region for block: B:1635:0x1970  */
    /* JADX WARN: Removed duplicated region for block: B:1639:0x1a4b  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:1643:0x1a55  */
    /* JADX WARN: Removed duplicated region for block: B:1644:0x1a57  */
    /* JADX WARN: Removed duplicated region for block: B:1645:0x1a5a  */
    /* JADX WARN: Removed duplicated region for block: B:1649:0x1a64  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:1655:0x1a80  */
    /* JADX WARN: Removed duplicated region for block: B:1659:0x1a92  */
    /* JADX WARN: Removed duplicated region for block: B:1663:0x1a9d  */
    /* JADX WARN: Removed duplicated region for block: B:1664:0x1a9f  */
    /* JADX WARN: Removed duplicated region for block: B:1665:0x1aa2  */
    /* JADX WARN: Removed duplicated region for block: B:1669:0x1ab1  */
    /* JADX WARN: Removed duplicated region for block: B:1673:0x1ac1  */
    /* JADX WARN: Removed duplicated region for block: B:1674:0x1ac3  */
    /* JADX WARN: Removed duplicated region for block: B:1675:0x1ac6  */
    /* JADX WARN: Removed duplicated region for block: B:1679:0x1ad4  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:1685:0x1af0  */
    /* JADX WARN: Removed duplicated region for block: B:1689:0x1afd  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:1695:0x1b13  */
    /* JADX WARN: Removed duplicated region for block: B:1699:0x1b1e  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03cf  */
    /* JADX WARN: Removed duplicated region for block: B:1713:0x1b52  */
    /* JADX WARN: Removed duplicated region for block: B:1716:0x1b5e  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:1721:0x1b6f  */
    /* JADX WARN: Removed duplicated region for block: B:1724:0x1b7d  */
    /* JADX WARN: Removed duplicated region for block: B:1728:0x1b88  */
    /* JADX WARN: Removed duplicated region for block: B:1729:0x1b8a  */
    /* JADX WARN: Removed duplicated region for block: B:1730:0x1b8d  */
    /* JADX WARN: Removed duplicated region for block: B:1734:0x1b99  */
    /* JADX WARN: Removed duplicated region for block: B:1740:0x1bb6  */
    /* JADX WARN: Removed duplicated region for block: B:1744:0x1bc6  */
    /* JADX WARN: Removed duplicated region for block: B:1748:0x1bd6  */
    /* JADX WARN: Removed duplicated region for block: B:1749:0x1bd8  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:1750:0x1bdb  */
    /* JADX WARN: Removed duplicated region for block: B:1754:0x1bec  */
    /* JADX WARN: Removed duplicated region for block: B:1758:0x1bf6  */
    /* JADX WARN: Removed duplicated region for block: B:1759:0x1bf8  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:1760:0x1bfb  */
    /* JADX WARN: Removed duplicated region for block: B:1764:0x1c07  */
    /* JADX WARN: Removed duplicated region for block: B:1775:0x1c35  */
    /* JADX WARN: Removed duplicated region for block: B:1779:0x1c44  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:1783:0x1c54  */
    /* JADX WARN: Removed duplicated region for block: B:1784:0x1c56  */
    /* JADX WARN: Removed duplicated region for block: B:1785:0x1c59  */
    /* JADX WARN: Removed duplicated region for block: B:1789:0x1c6a  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x03ea  */
    /* JADX WARN: Removed duplicated region for block: B:1794:0x1c7f  */
    /* JADX WARN: Removed duplicated region for block: B:1798:0x1c91  */
    /* JADX WARN: Removed duplicated region for block: B:1811:0x1cc2  */
    /* JADX WARN: Removed duplicated region for block: B:1814:0x1ccd  */
    /* JADX WARN: Removed duplicated region for block: B:1817:0x1cd4  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:1820:0x1cde  */
    /* JADX WARN: Removed duplicated region for block: B:1824:0x1cee  */
    /* JADX WARN: Removed duplicated region for block: B:1825:0x1cf0  */
    /* JADX WARN: Removed duplicated region for block: B:1826:0x1cf3  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0401  */
    /* JADX WARN: Removed duplicated region for block: B:1830:0x1d04  */
    /* JADX WARN: Removed duplicated region for block: B:1834:0x1d0f  */
    /* JADX WARN: Removed duplicated region for block: B:1835:0x1d11  */
    /* JADX WARN: Removed duplicated region for block: B:1836:0x1d14  */
    /* JADX WARN: Removed duplicated region for block: B:1840:0x1d23  */
    /* JADX WARN: Removed duplicated region for block: B:1844:0x1d32  */
    /* JADX WARN: Removed duplicated region for block: B:1845:0x1d34  */
    /* JADX WARN: Removed duplicated region for block: B:1846:0x1d37  */
    /* JADX WARN: Removed duplicated region for block: B:1850:0x1d44  */
    /* JADX WARN: Removed duplicated region for block: B:1855:0x1d5c  */
    /* JADX WARN: Removed duplicated region for block: B:1859:0x1d70  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:1863:0x1d7a  */
    /* JADX WARN: Removed duplicated region for block: B:1865:0x1d7d  */
    /* JADX WARN: Removed duplicated region for block: B:1869:0x1d87  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:1870:0x1d89  */
    /* JADX WARN: Removed duplicated region for block: B:1871:0x1d8c  */
    /* JADX WARN: Removed duplicated region for block: B:1875:0x1d9d  */
    /* JADX WARN: Removed duplicated region for block: B:1879:0x1dac  */
    /* JADX WARN: Removed duplicated region for block: B:1880:0x1dae  */
    /* JADX WARN: Removed duplicated region for block: B:1881:0x1db1  */
    /* JADX WARN: Removed duplicated region for block: B:1885:0x1dc0  */
    /* JADX WARN: Removed duplicated region for block: B:1886:0x1dc2  */
    /* JADX WARN: Removed duplicated region for block: B:1887:0x1dc6  */
    /* JADX WARN: Removed duplicated region for block: B:1891:0x1dd8  */
    /* JADX WARN: Removed duplicated region for block: B:1895:0x1de2  */
    /* JADX WARN: Removed duplicated region for block: B:1897:0x1de5  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:1901:0x1def  */
    /* JADX WARN: Removed duplicated region for block: B:1902:0x1df1  */
    /* JADX WARN: Removed duplicated region for block: B:1903:0x1df4  */
    /* JADX WARN: Removed duplicated region for block: B:1906:0x1e04  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0413  */
    /* JADX WARN: Removed duplicated region for block: B:1910:0x1e0f  */
    /* JADX WARN: Removed duplicated region for block: B:1911:0x1e11  */
    /* JADX WARN: Removed duplicated region for block: B:1915:0x1e1b  */
    /* JADX WARN: Removed duplicated region for block: B:1916:0x1e1d  */
    /* JADX WARN: Removed duplicated region for block: B:1917:0x1e20  */
    /* JADX WARN: Removed duplicated region for block: B:1920:0x1e26  */
    /* JADX WARN: Removed duplicated region for block: B:1921:0x1e29  */
    /* JADX WARN: Removed duplicated region for block: B:1925:0x1e3a  */
    /* JADX WARN: Removed duplicated region for block: B:1929:0x1e45  */
    /* JADX WARN: Removed duplicated region for block: B:1930:0x1e47  */
    /* JADX WARN: Removed duplicated region for block: B:1934:0x1e51  */
    /* JADX WARN: Removed duplicated region for block: B:1935:0x1e53  */
    /* JADX WARN: Removed duplicated region for block: B:1936:0x1e57  */
    /* JADX WARN: Removed duplicated region for block: B:1939:0x1e5e  */
    /* JADX WARN: Removed duplicated region for block: B:1940:0x1e61  */
    /* JADX WARN: Removed duplicated region for block: B:1944:0x1e74  */
    /* JADX WARN: Removed duplicated region for block: B:1949:0x1e82  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:1951:0x1e85  */
    /* JADX WARN: Removed duplicated region for block: B:1953:0x1e89  */
    /* JADX WARN: Removed duplicated region for block: B:1954:0x1e9b  */
    /* JADX WARN: Removed duplicated region for block: B:1958:0x1eab  */
    /* JADX WARN: Removed duplicated region for block: B:1959:0x1ead  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:1960:0x1eb3  */
    /* JADX WARN: Removed duplicated region for block: B:1964:0x1ebd  */
    /* JADX WARN: Removed duplicated region for block: B:1967:0x1ecc  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:1971:0x1eda  */
    /* JADX WARN: Removed duplicated region for block: B:1973:0x1edd  */
    /* JADX WARN: Removed duplicated region for block: B:1977:0x1ee7  */
    /* JADX WARN: Removed duplicated region for block: B:1978:0x1ee9  */
    /* JADX WARN: Removed duplicated region for block: B:1979:0x1eef  */
    /* JADX WARN: Removed duplicated region for block: B:1982:0x1ef8  */
    /* JADX WARN: Removed duplicated region for block: B:1983:0x1efb  */
    /* JADX WARN: Removed duplicated region for block: B:1987:0x1f10  */
    /* JADX WARN: Removed duplicated region for block: B:1991:0x1f20  */
    /* JADX WARN: Removed duplicated region for block: B:1993:0x1f23  */
    /* JADX WARN: Removed duplicated region for block: B:1996:0x1f2b  */
    /* JADX WARN: Removed duplicated region for block: B:2000:0x1f3e  */
    /* JADX WARN: Removed duplicated region for block: B:2003:0x1f43  */
    /* JADX WARN: Removed duplicated region for block: B:2005:0x1f47  */
    /* JADX WARN: Removed duplicated region for block: B:2007:0x1f52  */
    /* JADX WARN: Removed duplicated region for block: B:2008:0x1f54  */
    /* JADX WARN: Removed duplicated region for block: B:2009:0x1f57  */
    /* JADX WARN: Removed duplicated region for block: B:2012:0x1f60  */
    /* JADX WARN: Removed duplicated region for block: B:2013:0x1f63  */
    /* JADX WARN: Removed duplicated region for block: B:2017:0x1f76  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:2021:0x1f80  */
    /* JADX WARN: Removed duplicated region for block: B:2023:0x1f83  */
    /* JADX WARN: Removed duplicated region for block: B:2027:0x1f8d  */
    /* JADX WARN: Removed duplicated region for block: B:2028:0x1f8f  */
    /* JADX WARN: Removed duplicated region for block: B:2029:0x1f94  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:2032:0x1fa5  */
    /* JADX WARN: Removed duplicated region for block: B:2036:0x1faf  */
    /* JADX WARN: Removed duplicated region for block: B:2038:0x1fb2  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:2042:0x1fbc  */
    /* JADX WARN: Removed duplicated region for block: B:2043:0x1fbe  */
    /* JADX WARN: Removed duplicated region for block: B:2044:0x1fc1  */
    /* JADX WARN: Removed duplicated region for block: B:2048:0x1fcf  */
    /* JADX WARN: Removed duplicated region for block: B:2054:0x1fed  */
    /* JADX WARN: Removed duplicated region for block: B:2055:0x1fef  */
    /* JADX WARN: Removed duplicated region for block: B:2058:0x2000  */
    /* JADX WARN: Removed duplicated region for block: B:2061:0x200e  */
    /* JADX WARN: Removed duplicated region for block: B:2063:0x2012  */
    /* JADX WARN: Removed duplicated region for block: B:2068:0x2027  */
    /* JADX WARN: Removed duplicated region for block: B:2069:0x2029  */
    /* JADX WARN: Removed duplicated region for block: B:2070:0x202c  */
    /* JADX WARN: Removed duplicated region for block: B:2073:0x2039 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2077:0x2045  */
    /* JADX WARN: Removed duplicated region for block: B:2081:0x204e  */
    /* JADX WARN: Removed duplicated region for block: B:2082:0x2050  */
    /* JADX WARN: Removed duplicated region for block: B:2083:0x2052  */
    /* JADX WARN: Removed duplicated region for block: B:2087:0x205b  */
    /* JADX WARN: Removed duplicated region for block: B:2088:0x205d  */
    /* JADX WARN: Removed duplicated region for block: B:2089:0x2061  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:2092:0x2068  */
    /* JADX WARN: Removed duplicated region for block: B:2096:0x2074  */
    /* JADX WARN: Removed duplicated region for block: B:2098:0x2078  */
    /* JADX WARN: Removed duplicated region for block: B:2099:0x207b  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:2101:0x207f  */
    /* JADX WARN: Removed duplicated region for block: B:2103:0x2085  */
    /* JADX WARN: Removed duplicated region for block: B:2104:0x2087  */
    /* JADX WARN: Removed duplicated region for block: B:2105:0x208a  */
    /* JADX WARN: Removed duplicated region for block: B:2108:0x2090  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:2112:0x209c  */
    /* JADX WARN: Removed duplicated region for block: B:2114:0x20a0  */
    /* JADX WARN: Removed duplicated region for block: B:2115:0x20a3  */
    /* JADX WARN: Removed duplicated region for block: B:2117:0x20a7  */
    /* JADX WARN: Removed duplicated region for block: B:2119:0x20ad  */
    /* JADX WARN: Removed duplicated region for block: B:2120:0x20af  */
    /* JADX WARN: Removed duplicated region for block: B:2121:0x20b2  */
    /* JADX WARN: Removed duplicated region for block: B:2124:0x20b8  */
    /* JADX WARN: Removed duplicated region for block: B:2128:0x20c4  */
    /* JADX WARN: Removed duplicated region for block: B:2130:0x20c8  */
    /* JADX WARN: Removed duplicated region for block: B:2131:0x20cb  */
    /* JADX WARN: Removed duplicated region for block: B:2133:0x20cf  */
    /* JADX WARN: Removed duplicated region for block: B:2135:0x20d5  */
    /* JADX WARN: Removed duplicated region for block: B:2137:0x20d8  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:2140:0x20e1  */
    /* JADX WARN: Removed duplicated region for block: B:2144:0x20f4  */
    /* JADX WARN: Removed duplicated region for block: B:2146:0x20f8  */
    /* JADX WARN: Removed duplicated region for block: B:2147:0x20fb  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:2150:0x2108  */
    /* JADX WARN: Removed duplicated region for block: B:2152:0x210e  */
    /* JADX WARN: Removed duplicated region for block: B:2153:0x2110  */
    /* JADX WARN: Removed duplicated region for block: B:2154:0x2113  */
    /* JADX WARN: Removed duplicated region for block: B:2159:0x212b  */
    /* JADX WARN: Removed duplicated region for block: B:2160:0x212d  */
    /* JADX WARN: Removed duplicated region for block: B:2161:0x2130  */
    /* JADX WARN: Removed duplicated region for block: B:2167:0x214c  */
    /* JADX WARN: Removed duplicated region for block: B:2168:0x214e  */
    /* JADX WARN: Removed duplicated region for block: B:2169:0x2151  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x045b  */
    /* JADX WARN: Removed duplicated region for block: B:2177:0x2173  */
    /* JADX WARN: Removed duplicated region for block: B:2178:0x2175  */
    /* JADX WARN: Removed duplicated region for block: B:2179:0x2179  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x045e  */
    /* JADX WARN: Removed duplicated region for block: B:2182:0x2181  */
    /* JADX WARN: Removed duplicated region for block: B:2186:0x218d  */
    /* JADX WARN: Removed duplicated region for block: B:2188:0x2191  */
    /* JADX WARN: Removed duplicated region for block: B:2189:0x2194  */
    /* JADX WARN: Removed duplicated region for block: B:2193:0x21a0  */
    /* JADX WARN: Removed duplicated region for block: B:2199:0x21bc  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:2202:0x21c3  */
    /* JADX WARN: Removed duplicated region for block: B:2205:0x21cc A[PHI: r16
  0x21cc: PHI (r16v216 long) = (r16v189 long), (r16v217 long) binds: [B:2207:0x21d0, B:2203:0x21c9] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:2206:0x21ce  */
    /* JADX WARN: Removed duplicated region for block: B:2208:0x21d2  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x0465  */
    /* JADX WARN: Removed duplicated region for block: B:2211:0x21d9  */
    /* JADX WARN: Removed duplicated region for block: B:2217:0x21f5  */
    /* JADX WARN: Removed duplicated region for block: B:2221:0x2204  */
    /* JADX WARN: Removed duplicated region for block: B:2227:0x221f  */
    /* JADX WARN: Removed duplicated region for block: B:2230:0x2224  */
    /* JADX WARN: Removed duplicated region for block: B:2233:0x222d A[PHI: r16
  0x222d: PHI (r16v214 long) = (r16v193 long), (r16v215 long) binds: [B:2235:0x2231, B:2231:0x222a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:2234:0x222f  */
    /* JADX WARN: Removed duplicated region for block: B:2236:0x2233  */
    /* JADX WARN: Removed duplicated region for block: B:2238:0x2236  */
    /* JADX WARN: Removed duplicated region for block: B:2242:0x223f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2246:0x2248 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2250:0x2257  */
    /* JADX WARN: Removed duplicated region for block: B:2256:0x2278  */
    /* JADX WARN: Removed duplicated region for block: B:2260:0x228c  */
    /* JADX WARN: Removed duplicated region for block: B:2264:0x2298  */
    /* JADX WARN: Removed duplicated region for block: B:2266:0x229b  */
    /* JADX WARN: Removed duplicated region for block: B:2268:0x229f  */
    /* JADX WARN: Removed duplicated region for block: B:2270:0x22a5  */
    /* JADX WARN: Removed duplicated region for block: B:2271:0x22a8  */
    /* JADX WARN: Removed duplicated region for block: B:2275:0x22bb  */
    /* JADX WARN: Removed duplicated region for block: B:2279:0x22c6  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:2281:0x22c9  */
    /* JADX WARN: Removed duplicated region for block: B:2286:0x22dc  */
    /* JADX WARN: Removed duplicated region for block: B:2287:0x22de  */
    /* JADX WARN: Removed duplicated region for block: B:2288:0x22e2  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:2294:0x22ff  */
    /* JADX WARN: Removed duplicated region for block: B:2295:0x2301  */
    /* JADX WARN: Removed duplicated region for block: B:2296:0x2307  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0498  */
    /* JADX WARN: Removed duplicated region for block: B:2302:0x2321  */
    /* JADX WARN: Removed duplicated region for block: B:2303:0x2323  */
    /* JADX WARN: Removed duplicated region for block: B:2304:0x2326  */
    /* JADX WARN: Removed duplicated region for block: B:2310:0x2344  */
    /* JADX WARN: Removed duplicated region for block: B:2311:0x2346  */
    /* JADX WARN: Removed duplicated region for block: B:2312:0x234c  */
    /* JADX WARN: Removed duplicated region for block: B:2316:0x2360  */
    /* JADX WARN: Removed duplicated region for block: B:2320:0x236c  */
    /* JADX WARN: Removed duplicated region for block: B:2322:0x236f  */
    /* JADX WARN: Removed duplicated region for block: B:2326:0x2379  */
    /* JADX WARN: Removed duplicated region for block: B:2327:0x237b  */
    /* JADX WARN: Removed duplicated region for block: B:2328:0x237f  */
    /* JADX WARN: Removed duplicated region for block: B:2331:0x2386  */
    /* JADX WARN: Removed duplicated region for block: B:2332:0x2389  */
    /* JADX WARN: Removed duplicated region for block: B:2336:0x239c  */
    /* JADX WARN: Removed duplicated region for block: B:2340:0x23ad  */
    /* JADX WARN: Removed duplicated region for block: B:2342:0x23b0  */
    /* JADX WARN: Removed duplicated region for block: B:2346:0x23bf  */
    /* JADX WARN: Removed duplicated region for block: B:2347:0x23c1  */
    /* JADX WARN: Removed duplicated region for block: B:2348:0x23c4  */
    /* JADX WARN: Removed duplicated region for block: B:2351:0x23ca  */
    /* JADX WARN: Removed duplicated region for block: B:2352:0x23cd  */
    /* JADX WARN: Removed duplicated region for block: B:2356:0x23e0  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x04b3 A[PHI: r12
  0x04b3: PHI (r12v14 long) = (r12v12 long), (r12v10 long) binds: [B:246:0x04cd, B:233:0x04b0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:2360:0x23f1  */
    /* JADX WARN: Removed duplicated region for block: B:2362:0x23f4  */
    /* JADX WARN: Removed duplicated region for block: B:2366:0x2403  */
    /* JADX WARN: Removed duplicated region for block: B:2367:0x2405  */
    /* JADX WARN: Removed duplicated region for block: B:2368:0x240b  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x04b5  */
    /* JADX WARN: Removed duplicated region for block: B:2372:0x2415  */
    /* JADX WARN: Removed duplicated region for block: B:2376:0x2425  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x04b7  */
    /* JADX WARN: Removed duplicated region for block: B:2381:0x243c  */
    /* JADX WARN: Removed duplicated region for block: B:2385:0x244c  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:2390:0x2461  */
    /* JADX WARN: Removed duplicated region for block: B:2394:0x2471  */
    /* JADX WARN: Removed duplicated region for block: B:2399:0x2487  */
    /* JADX WARN: Removed duplicated region for block: B:2403:0x2497  */
    /* JADX WARN: Removed duplicated region for block: B:2409:0x24ac  */
    /* JADX WARN: Removed duplicated region for block: B:2412:0x24ba  */
    /* JADX WARN: Removed duplicated region for block: B:2416:0x24c5  */
    /* JADX WARN: Removed duplicated region for block: B:2418:0x24c8  */
    /* JADX WARN: Removed duplicated region for block: B:2422:0x24d9  */
    /* JADX WARN: Removed duplicated region for block: B:2426:0x24e9  */
    /* JADX WARN: Removed duplicated region for block: B:2428:0x24ec  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x04c4  */
    /* JADX WARN: Removed duplicated region for block: B:2432:0x24fd  */
    /* JADX WARN: Removed duplicated region for block: B:2436:0x2508  */
    /* JADX WARN: Removed duplicated region for block: B:2438:0x250b  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x04c6  */
    /* JADX WARN: Removed duplicated region for block: B:2442:0x251c  */
    /* JADX WARN: Removed duplicated region for block: B:2446:0x252c  */
    /* JADX WARN: Removed duplicated region for block: B:2448:0x252f  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:2451:0x2537  */
    /* JADX WARN: Removed duplicated region for block: B:2455:0x254a  */
    /* JADX WARN: Removed duplicated region for block: B:2457:0x2556  */
    /* JADX WARN: Removed duplicated region for block: B:2459:0x2571  */
    /* JADX WARN: Removed duplicated region for block: B:2461:0x257e  */
    /* JADX WARN: Removed duplicated region for block: B:2463:0x258a  */
    /* JADX WARN: Removed duplicated region for block: B:2466:0x2598  */
    /* JADX WARN: Removed duplicated region for block: B:2469:0x25a1  */
    /* JADX WARN: Removed duplicated region for block: B:2471:0x25ac  */
    /* JADX WARN: Removed duplicated region for block: B:2474:0x25b7  */
    /* JADX WARN: Removed duplicated region for block: B:2476:0x25bd  */
    /* JADX WARN: Removed duplicated region for block: B:2478:0x260a  */
    /* JADX WARN: Removed duplicated region for block: B:2480:0x2618  */
    /* JADX WARN: Removed duplicated region for block: B:2482:0x2620  */
    /* JADX WARN: Removed duplicated region for block: B:2485:0x268c  */
    /* JADX WARN: Removed duplicated region for block: B:2487:0x2692  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:2490:0x26a1  */
    /* JADX WARN: Removed duplicated region for block: B:2492:0x26a7  */
    /* JADX WARN: Removed duplicated region for block: B:2495:0x26bf  */
    /* JADX WARN: Removed duplicated region for block: B:2497:0x26c5  */
    /* JADX WARN: Removed duplicated region for block: B:2499:0x26ce  */
    /* JADX WARN: Removed duplicated region for block: B:2501:0x26d4  */
    /* JADX WARN: Removed duplicated region for block: B:2504:0x26ea  */
    /* JADX WARN: Removed duplicated region for block: B:2506:0x26f0  */
    /* JADX WARN: Removed duplicated region for block: B:2509:0x26ff  */
    /* JADX WARN: Removed duplicated region for block: B:2511:0x2705  */
    /* JADX WARN: Removed duplicated region for block: B:2513:0x270e  */
    /* JADX WARN: Removed duplicated region for block: B:2515:0x2714  */
    /* JADX WARN: Removed duplicated region for block: B:2518:0x277f  */
    /* JADX WARN: Removed duplicated region for block: B:2520:0x2785  */
    /* JADX WARN: Removed duplicated region for block: B:2523:0x2796  */
    /* JADX WARN: Removed duplicated region for block: B:2525:0x279c  */
    /* JADX WARN: Removed duplicated region for block: B:2528:0x27b4  */
    /* JADX WARN: Removed duplicated region for block: B:2530:0x27ba  */
    /* JADX WARN: Removed duplicated region for block: B:2533:0x27cb  */
    /* JADX WARN: Removed duplicated region for block: B:2535:0x27d1  */
    /* JADX WARN: Removed duplicated region for block: B:2538:0x27e0  */
    /* JADX WARN: Removed duplicated region for block: B:2540:0x27e6  */
    /* JADX WARN: Removed duplicated region for block: B:2543:0x27f7  */
    /* JADX WARN: Removed duplicated region for block: B:2545:0x27fd  */
    /* JADX WARN: Removed duplicated region for block: B:2548:0x280e  */
    /* JADX WARN: Removed duplicated region for block: B:2550:0x2814  */
    /* JADX WARN: Removed duplicated region for block: B:2553:0x2825  */
    /* JADX WARN: Removed duplicated region for block: B:2555:0x282b  */
    /* JADX WARN: Removed duplicated region for block: B:2558:0x283c  */
    /* JADX WARN: Removed duplicated region for block: B:2560:0x2842  */
    /* JADX WARN: Removed duplicated region for block: B:2563:0x2853  */
    /* JADX WARN: Removed duplicated region for block: B:2565:0x2859  */
    /* JADX WARN: Removed duplicated region for block: B:2568:0x286a  */
    /* JADX WARN: Removed duplicated region for block: B:2570:0x2870  */
    /* JADX WARN: Removed duplicated region for block: B:2573:0x2883  */
    /* JADX WARN: Removed duplicated region for block: B:2575:0x2889  */
    /* JADX WARN: Removed duplicated region for block: B:2578:0x289c  */
    /* JADX WARN: Removed duplicated region for block: B:2580:0x28a2  */
    /* JADX WARN: Removed duplicated region for block: B:2583:0x28b3  */
    /* JADX WARN: Removed duplicated region for block: B:2585:0x28b9  */
    /* JADX WARN: Removed duplicated region for block: B:2588:0x28ca  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x04fb  */
    /* JADX WARN: Removed duplicated region for block: B:2590:0x28d0  */
    /* JADX WARN: Removed duplicated region for block: B:2593:0x28e9  */
    /* JADX WARN: Removed duplicated region for block: B:2595:0x28ef  */
    /* JADX WARN: Removed duplicated region for block: B:2598:0x2908  */
    /* JADX WARN: Removed duplicated region for block: B:2602:0x2912  */
    /* JADX WARN: Removed duplicated region for block: B:2604:0x2922  */
    /* JADX WARN: Removed duplicated region for block: B:2606:0x292a  */
    /* JADX WARN: Removed duplicated region for block: B:2608:0x2938  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0501 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2610:0x2945  */
    /* JADX WARN: Removed duplicated region for block: B:2614:0x2956  */
    /* JADX WARN: Removed duplicated region for block: B:2617:0x2964  */
    /* JADX WARN: Removed duplicated region for block: B:2620:0x296f  */
    /* JADX WARN: Removed duplicated region for block: B:2622:0x297a  */
    /* JADX WARN: Removed duplicated region for block: B:2625:0x298c  */
    /* JADX WARN: Removed duplicated region for block: B:2627:0x2992  */
    /* JADX WARN: Removed duplicated region for block: B:2629:0x299a  */
    /* JADX WARN: Removed duplicated region for block: B:2633:0x29fb  */
    /* JADX WARN: Removed duplicated region for block: B:2635:0x2a01  */
    /* JADX WARN: Removed duplicated region for block: B:2640:0x2a27  */
    /* JADX WARN: Removed duplicated region for block: B:2642:0x2a2d  */
    /* JADX WARN: Removed duplicated region for block: B:2645:0x2a50  */
    /* JADX WARN: Removed duplicated region for block: B:2647:0x2a56  */
    /* JADX WARN: Removed duplicated region for block: B:2650:0x2a7b  */
    /* JADX WARN: Removed duplicated region for block: B:2652:0x2a86  */
    /* JADX WARN: Removed duplicated region for block: B:2656:0x2aa6  */
    /* JADX WARN: Removed duplicated region for block: B:2658:0x2ab3  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x0517  */
    /* JADX WARN: Removed duplicated region for block: B:2660:0x2ad0  */
    /* JADX WARN: Removed duplicated region for block: B:2662:0x2ad8  */
    /* JADX WARN: Removed duplicated region for block: B:2664:0x2afa  */
    /* JADX WARN: Removed duplicated region for block: B:2666:0x2b02  */
    /* JADX WARN: Removed duplicated region for block: B:2669:0x2b1b  */
    /* JADX WARN: Removed duplicated region for block: B:2671:0x2b26  */
    /* JADX WARN: Removed duplicated region for block: B:2674:0x2b38  */
    /* JADX WARN: Removed duplicated region for block: B:2676:0x2b3e  */
    /* JADX WARN: Removed duplicated region for block: B:2679:0x2b61  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x051d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2681:0x2b67  */
    /* JADX WARN: Removed duplicated region for block: B:2684:0x2b79  */
    /* JADX WARN: Removed duplicated region for block: B:2686:0x2b7f  */
    /* JADX WARN: Removed duplicated region for block: B:2688:0x2b88  */
    /* JADX WARN: Removed duplicated region for block: B:2690:0x2b93  */
    /* JADX WARN: Removed duplicated region for block: B:2692:0x2b9c  */
    /* JADX WARN: Removed duplicated region for block: B:2694:0x2ba7  */
    /* JADX WARN: Removed duplicated region for block: B:2697:0x2bb6  */
    /* JADX WARN: Removed duplicated region for block: B:2699:0x2bbc  */
    /* JADX WARN: Removed duplicated region for block: B:2701:0x2bc7  */
    /* JADX WARN: Removed duplicated region for block: B:2703:0x2bd4  */
    /* JADX WARN: Removed duplicated region for block: B:2707:0x2be3  */
    /* JADX WARN: Removed duplicated region for block: B:2710:0x2bf1  */
    /* JADX WARN: Removed duplicated region for block: B:2714:0x2c02  */
    /* JADX WARN: Removed duplicated region for block: B:2716:0x2c08  */
    /* JADX WARN: Removed duplicated region for block: B:2718:0x2c1e  */
    /* JADX WARN: Removed duplicated region for block: B:2721:0x2c27  */
    /* JADX WARN: Removed duplicated region for block: B:2724:0x2c32  */
    /* JADX WARN: Removed duplicated region for block: B:2726:0x2c3d  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0533  */
    /* JADX WARN: Removed duplicated region for block: B:2730:0x2c4e  */
    /* JADX WARN: Removed duplicated region for block: B:2733:0x2c5c  */
    /* JADX WARN: Removed duplicated region for block: B:2736:0x2c67  */
    /* JADX WARN: Removed duplicated region for block: B:2738:0x2c72  */
    /* JADX WARN: Removed duplicated region for block: B:2740:0x2c7b  */
    /* JADX WARN: Removed duplicated region for block: B:2742:0x2c86  */
    /* JADX WARN: Removed duplicated region for block: B:2744:0x2c8f  */
    /* JADX WARN: Removed duplicated region for block: B:2746:0x2c9a  */
    /* JADX WARN: Removed duplicated region for block: B:2748:0x2ca2  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0539 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:2751:0x2cb2  */
    /* JADX WARN: Removed duplicated region for block: B:2754:0x2cc0  */
    /* JADX WARN: Removed duplicated region for block: B:2757:0x2ccb  */
    /* JADX WARN: Removed duplicated region for block: B:2759:0x2cd6  */
    /* JADX WARN: Removed duplicated region for block: B:2763:0x2ce7  */
    /* JADX WARN: Removed duplicated region for block: B:2766:0x2cf5  */
    /* JADX WARN: Removed duplicated region for block: B:2769:0x2d00  */
    /* JADX WARN: Removed duplicated region for block: B:2771:0x2d0b  */
    /* JADX WARN: Removed duplicated region for block: B:2773:0x2d14  */
    /* JADX WARN: Removed duplicated region for block: B:2775:0x2d1f  */
    /* JADX WARN: Removed duplicated region for block: B:2777:0x2d28  */
    /* JADX WARN: Removed duplicated region for block: B:2779:0x2d2e  */
    /* JADX WARN: Removed duplicated region for block: B:2781:0x2d36  */
    /* JADX WARN: Removed duplicated region for block: B:2785:0x2d50  */
    /* JADX WARN: Removed duplicated region for block: B:2787:0x2d56  */
    /* JADX WARN: Removed duplicated region for block: B:2789:0x2d5f  */
    /* JADX WARN: Removed duplicated region for block: B:2791:0x2d6a  */
    /* JADX WARN: Removed duplicated region for block: B:2795:0x2d7b  */
    /* JADX WARN: Removed duplicated region for block: B:2798:0x2d89  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:2801:0x2d94  */
    /* JADX WARN: Removed duplicated region for block: B:2803:0x2d9f  */
    /* JADX WARN: Removed duplicated region for block: B:2805:0x2da8  */
    /* JADX WARN: Removed duplicated region for block: B:2807:0x2db3  */
    /* JADX WARN: Removed duplicated region for block: B:2810:0x2dc4  */
    /* JADX WARN: Removed duplicated region for block: B:2812:0x2dca  */
    /* JADX WARN: Removed duplicated region for block: B:2817:0x2de5  */
    /* JADX WARN: Removed duplicated region for block: B:2819:0x2deb  */
    /* JADX WARN: Removed duplicated region for block: B:2822:0x2dfc  */
    /* JADX WARN: Removed duplicated region for block: B:2824:0x2e02  */
    /* JADX WARN: Removed duplicated region for block: B:2826:0x2e0b  */
    /* JADX WARN: Removed duplicated region for block: B:2828:0x2e11  */
    /* JADX WARN: Removed duplicated region for block: B:2831:0x2e20  */
    /* JADX WARN: Removed duplicated region for block: B:2833:0x2e26  */
    /* JADX WARN: Removed duplicated region for block: B:2835:0x2e2e  */
    /* JADX WARN: Removed duplicated region for block: B:2838:0x2e48  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0567  */
    /* JADX WARN: Removed duplicated region for block: B:2840:0x2e50  */
    /* JADX WARN: Removed duplicated region for block: B:2844:0x2e5f  */
    /* JADX WARN: Removed duplicated region for block: B:2847:0x2e68  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0569  */
    /* JADX WARN: Removed duplicated region for block: B:2850:0x2e73  */
    /* JADX WARN: Removed duplicated region for block: B:2852:0x2e79  */
    /* JADX WARN: Removed duplicated region for block: B:2854:0x2e82  */
    /* JADX WARN: Removed duplicated region for block: B:2856:0x2e8d  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:2860:0x2e9e  */
    /* JADX WARN: Removed duplicated region for block: B:2863:0x2eac  */
    /* JADX WARN: Removed duplicated region for block: B:2866:0x2eb7  */
    /* JADX WARN: Removed duplicated region for block: B:2868:0x2ebd  */
    /* JADX WARN: Removed duplicated region for block: B:2870:0x2ec6  */
    /* JADX WARN: Removed duplicated region for block: B:2872:0x2ecc  */
    /* JADX WARN: Removed duplicated region for block: B:2875:0x2edd  */
    /* JADX WARN: Removed duplicated region for block: B:2877:0x2ee3  */
    /* JADX WARN: Removed duplicated region for block: B:2882:0x2efe  */
    /* JADX WARN: Removed duplicated region for block: B:2884:0x2f04  */
    /* JADX WARN: Removed duplicated region for block: B:2887:0x2f15  */
    /* JADX WARN: Removed duplicated region for block: B:2889:0x2f1b  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0581  */
    /* JADX WARN: Removed duplicated region for block: B:2891:0x2f24  */
    /* JADX WARN: Removed duplicated region for block: B:2893:0x2f2a  */
    /* JADX WARN: Removed duplicated region for block: B:2896:0x2f3b  */
    /* JADX WARN: Removed duplicated region for block: B:2898:0x2f41  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:2903:0x2f5c  */
    /* JADX WARN: Removed duplicated region for block: B:2905:0x2f62  */
    /* JADX WARN: Removed duplicated region for block: B:2908:0x2f73  */
    /* JADX WARN: Removed duplicated region for block: B:2910:0x2f79  */
    /* JADX WARN: Removed duplicated region for block: B:2912:0x2f82  */
    /* JADX WARN: Removed duplicated region for block: B:2914:0x2f88  */
    /* JADX WARN: Removed duplicated region for block: B:2917:0x2f99  */
    /* JADX WARN: Removed duplicated region for block: B:2919:0x2f9f  */
    /* JADX WARN: Removed duplicated region for block: B:2923:0x2fb0  */
    /* JADX WARN: Removed duplicated region for block: B:2926:0x2fbe  */
    /* JADX WARN: Removed duplicated region for block: B:2929:0x2fc9  */
    /* JADX WARN: Removed duplicated region for block: B:2931:0x2fd4  */
    /* JADX WARN: Removed duplicated region for block: B:2933:0x2fdd  */
    /* JADX WARN: Removed duplicated region for block: B:2935:0x2fe8  */
    /* JADX WARN: Removed duplicated region for block: B:2938:0x2ff9  */
    /* JADX WARN: Removed duplicated region for block: B:2940:0x2fff  */
    /* JADX WARN: Removed duplicated region for block: B:2945:0x301a  */
    /* JADX WARN: Removed duplicated region for block: B:2947:0x3020  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x05a0  */
    /* JADX WARN: Removed duplicated region for block: B:2950:0x3031  */
    /* JADX WARN: Removed duplicated region for block: B:2952:0x3037  */
    /* JADX WARN: Removed duplicated region for block: B:2954:0x3040  */
    /* JADX WARN: Removed duplicated region for block: B:2956:0x3046  */
    /* JADX WARN: Removed duplicated region for block: B:2958:0x304f  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x05a2  */
    /* JADX WARN: Removed duplicated region for block: B:2960:0x3055  */
    /* JADX WARN: Removed duplicated region for block: B:2964:0x3066  */
    /* JADX WARN: Removed duplicated region for block: B:2967:0x306f  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x05a5  */
    /* JADX WARN: Removed duplicated region for block: B:2970:0x307a  */
    /* JADX WARN: Removed duplicated region for block: B:2972:0x3080  */
    /* JADX WARN: Removed duplicated region for block: B:2975:0x3091  */
    /* JADX WARN: Removed duplicated region for block: B:2977:0x3097  */
    /* JADX WARN: Removed duplicated region for block: B:2981:0x30b2  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x05c4  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x05c6  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x05c9  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x05e0  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x05e2  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x05e5  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0601  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0604  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0617  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x0619  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x061c  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0633  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x0635  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0639  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x0651  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x0653  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x0656  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0674  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x067e  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0680  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x0683  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x06a1  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x06c2  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x06cb  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x06cd  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x06d0  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x06d9  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x06dc  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x06f5  */
    /* JADX WARN: Removed duplicated region for block: B:385:0x06f7  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x06fe  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0707  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x0709  */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0714  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x0716  */
    /* JADX WARN: Removed duplicated region for block: B:396:0x0719  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x0722  */
    /* JADX WARN: Removed duplicated region for block: B:400:0x0724  */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0746  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x0748  */
    /* JADX WARN: Removed duplicated region for block: B:410:0x0755  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x0773  */
    /* JADX WARN: Removed duplicated region for block: B:418:0x0775  */
    /* JADX WARN: Removed duplicated region for block: B:419:0x0778  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x0782  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0784  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x078a  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x07a3 A[PHI: r12
  0x07a3: PHI (r12v25 long) = (r12v23 long), (r12v21 long) binds: [B:443:0x07bd, B:430:0x07a0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:433:0x07a5  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x07a7  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x07aa  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x07b4  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x07b6  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x07b9  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x07c0  */
    /* JADX WARN: Removed duplicated region for block: B:454:0x07e8  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x0805  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x0807  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0816  */
    /* JADX WARN: Removed duplicated region for block: B:467:0x0836  */
    /* JADX WARN: Removed duplicated region for block: B:468:0x0838  */
    /* JADX WARN: Removed duplicated region for block: B:469:0x083d  */
    /* JADX WARN: Removed duplicated region for block: B:474:0x0849  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x084b  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x084e  */
    /* JADX WARN: Removed duplicated region for block: B:485:0x086d  */
    /* JADX WARN: Removed duplicated region for block: B:486:0x086f  */
    /* JADX WARN: Removed duplicated region for block: B:487:0x0874  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x0887  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x0889  */
    /* JADX WARN: Removed duplicated region for block: B:494:0x088c  */
    /* JADX WARN: Removed duplicated region for block: B:505:0x08af  */
    /* JADX WARN: Removed duplicated region for block: B:506:0x08b1  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x08b4  */
    /* JADX WARN: Removed duplicated region for block: B:511:0x08c2  */
    /* JADX WARN: Removed duplicated region for block: B:512:0x08c5  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x08d4  */
    /* JADX WARN: Removed duplicated region for block: B:519:0x08e0  */
    /* JADX WARN: Removed duplicated region for block: B:520:0x08e2  */
    /* JADX WARN: Removed duplicated region for block: B:521:0x08e7  */
    /* JADX WARN: Removed duplicated region for block: B:528:0x0905 A[PHI: r12
  0x0905: PHI (r12v33 long) = (r12v28 long), (r12v26 long) binds: [B:539:0x0920, B:526:0x0902] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0908  */
    /* JADX WARN: Removed duplicated region for block: B:530:0x090a  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x090d  */
    /* JADX WARN: Removed duplicated region for block: B:535:0x0917  */
    /* JADX WARN: Removed duplicated region for block: B:536:0x0919  */
    /* JADX WARN: Removed duplicated region for block: B:537:0x091c  */
    /* JADX WARN: Removed duplicated region for block: B:541:0x0923  */
    /* JADX WARN: Removed duplicated region for block: B:548:0x093c  */
    /* JADX WARN: Removed duplicated region for block: B:549:0x093e  */
    /* JADX WARN: Removed duplicated region for block: B:550:0x0943  */
    /* JADX WARN: Removed duplicated region for block: B:555:0x0951  */
    /* JADX WARN: Removed duplicated region for block: B:556:0x0953  */
    /* JADX WARN: Removed duplicated region for block: B:557:0x0956  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x095f  */
    /* JADX WARN: Removed duplicated region for block: B:560:0x0962  */
    /* JADX WARN: Removed duplicated region for block: B:575:0x09b1 A[PHI: r6 r8
  0x09b1: PHI (r6v24 long) = (r6v20 long), (r6v18 long) binds: [B:585:0x09cd, B:573:0x09ae] A[DONT_GENERATE, DONT_INLINE]
  0x09b1: PHI (r8v35 long) = (r8v30 long), (r8v28 long) binds: [B:585:0x09cd, B:573:0x09ae] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:678:0x0b30  */
    /* JADX WARN: Removed duplicated region for block: B:679:0x0b32  */
    /* JADX WARN: Removed duplicated region for block: B:680:0x0b3a  */
    /* JADX WARN: Removed duplicated region for block: B:683:0x0b42  */
    /* JADX WARN: Removed duplicated region for block: B:684:0x0b44  */
    /* JADX WARN: Removed duplicated region for block: B:687:0x0b48  */
    /* JADX WARN: Removed duplicated region for block: B:688:0x0b4a  */
    /* JADX WARN: Removed duplicated region for block: B:692:0x0b55  */
    /* JADX WARN: Removed duplicated region for block: B:693:0x0b57  */
    /* JADX WARN: Removed duplicated region for block: B:694:0x0b5a  */
    /* JADX WARN: Removed duplicated region for block: B:696:0x0b61  */
    /* JADX WARN: Removed duplicated region for block: B:697:0x0b64  */
    /* JADX WARN: Removed duplicated region for block: B:703:0x0b77 A[PHI: r6
  0x0b77: PHI (r6v29 long) = (r6v27 long), (r6v25 long) binds: [B:714:0x0b91, B:701:0x0b74] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:704:0x0b79  */
    /* JADX WARN: Removed duplicated region for block: B:705:0x0b7b  */
    /* JADX WARN: Removed duplicated region for block: B:706:0x0b7e  */
    /* JADX WARN: Removed duplicated region for block: B:710:0x0b88  */
    /* JADX WARN: Removed duplicated region for block: B:711:0x0b8a  */
    /* JADX WARN: Removed duplicated region for block: B:712:0x0b8d  */
    /* JADX WARN: Removed duplicated region for block: B:716:0x0b94  */
    /* JADX WARN: Removed duplicated region for block: B:723:0x0bad  */
    /* JADX WARN: Removed duplicated region for block: B:724:0x0baf  */
    /* JADX WARN: Removed duplicated region for block: B:725:0x0bb4  */
    /* JADX WARN: Removed duplicated region for block: B:733:0x0bd7  */
    /* JADX WARN: Removed duplicated region for block: B:734:0x0bd9  */
    /* JADX WARN: Removed duplicated region for block: B:735:0x0bde  */
    /* JADX WARN: Removed duplicated region for block: B:740:0x0bec  */
    /* JADX WARN: Removed duplicated region for block: B:741:0x0bee  */
    /* JADX WARN: Removed duplicated region for block: B:742:0x0bf2  */
    /* JADX WARN: Removed duplicated region for block: B:750:0x0c0e  */
    /* JADX WARN: Removed duplicated region for block: B:751:0x0c10  */
    /* JADX WARN: Removed duplicated region for block: B:752:0x0c15  */
    /* JADX WARN: Removed duplicated region for block: B:757:0x0c21  */
    /* JADX WARN: Removed duplicated region for block: B:758:0x0c23  */
    /* JADX WARN: Removed duplicated region for block: B:759:0x0c29  */
    /* JADX WARN: Removed duplicated region for block: B:762:0x0c32  */
    /* JADX WARN: Removed duplicated region for block: B:763:0x0c35  */
    /* JADX WARN: Removed duplicated region for block: B:805:0x0ce3 A[PHI: r10
  0x0ce3: PHI (r10v42 long) = (r10v40 long), (r10v38 long) binds: [B:816:0x0d00, B:803:0x0ce0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:870:0x0dd7 A[PHI: r6
  0x0dd7: PHI (r6v34 long) = (r6v32 long), (r6v30 long) binds: [B:881:0x0df2, B:868:0x0dd4] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:957:0x0f1f A[PHI: r97
  0x0f1f: PHI (r97v11 long) = (r97v9 long), (r97v6 long) binds: [B:968:0x0f39, B:955:0x0f1c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:976:0x0f4c  */
    /* JADX WARN: Removed duplicated region for block: B:977:0x0f4e  */
    /* JADX WARN: Removed duplicated region for block: B:978:0x0f53  */
    /* JADX WARN: Removed duplicated region for block: B:983:0x0f5f  */
    /* JADX WARN: Removed duplicated region for block: B:984:0x0f61  */
    /* JADX WARN: Removed duplicated region for block: B:985:0x0f67  */
    /* JADX WARN: Removed duplicated region for block: B:988:0x0f70  */
    /* JADX WARN: Removed duplicated region for block: B:989:0x0f73  */
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
            Method dump skipped, instruction units count: 12505
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.carsettings.databinding.FragmentActiveSafeBindingRrRKPPortImpl.executeBindings():void");
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

    public FragmentActiveSafeBindingRrRKPPortImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 27, (BydSwitch) objArr[19], (ConstraintLayout) objArr[90], (ConstraintLayout) objArr[34], (ConstraintLayout) objArr[99], (ConstraintLayout) objArr[21], (ConstraintLayout) objArr[11], (ConstraintLayout) objArr[112], (ConstraintLayout) objArr[93], (ConstraintLayout) objArr[41], (View) objArr[64], null, null, null, (ImageView) objArr[79], (ImageView) objArr[7], (ImageView) objArr[92], (ImageView) objArr[54], (ImageView) objArr[51], (ImageView) objArr[101], (ImageView) objArr[72], (ImageView) objArr[75], (ImageView) objArr[13], (ImageView) objArr[27], (ImageView) objArr[95], (ImageView) objArr[42], (ImageView) objArr[40], (ImageView) objArr[60], (ImageView) objArr[57], (ImageView) objArr[37], (ImageView) objArr[63], (View) objArr[35], (ConstraintLayout) objArr[78], (View) objArr[5], (ConstraintLayout) objArr[8], (View) objArr[52], (View) objArr[49], (View) objArr[70], (View) objArr[73], (LinearLayout) objArr[48], (View) objArr[38], (View) objArr[58], (View) objArr[55], (View) objArr[83], (View) objArr[61], (View) objArr[68], (ConstraintLayout) objArr[76], (LottieAnimationView) objArr[108], (LottieAnimationView) objArr[105], (LottieAnimationView) objArr[113], (LottieAnimationView) objArr[119], (DampingScrollView) objArr[0], (RecyclerView) objArr[2], (ConstraintLayout) objArr[30], (ImageView) objArr[32], (BydSwitch) objArr[31], (TextView) objArr[107], (SegmentedButton) objArr[81], (SegmentedButton) objArr[97], (SegmentedButton) objArr[98], (SegmentedButton) objArr[82], (SegmentedButton) objArr[47], (SegmentedButton) objArr[46], (SegmentedButton) objArr[44], (SegmentedButton) objArr[45], (SegmentedButton) objArr[85], (SegmentedButton) objArr[86], (SegmentedButton) objArr[87], (SegmentedButton) objArr[88], (SegmentedButton) objArr[89], (SegmentedButtonGroup) objArr[80], (SegmentedButtonGroup) objArr[14], (SegmentedButtonGroup) objArr[22], (SegmentedButtonGroup) objArr[96], (SegmentedButtonGroup) objArr[43], (SegmentedButtonGroup) objArr[84], (View) objArr[65], (ImageView) objArr[67], (BydSwitch) objArr[66], (TextView) objArr[120], (BydSwitch) objArr[36], (BydSwitch) objArr[6], (BydSwitch) objArr[91], (BydSwitch) objArr[9], (BydSwitch) objArr[53], (BydSwitch) objArr[50], (BydSwitch) objArr[100], (BydSwitch) objArr[74], (BydSwitch) objArr[71], (BydSwitch) objArr[94], (BydSwitch) objArr[39], (BydSwitch) objArr[59], (BydSwitch) objArr[56], (BydSwitch) objArr[62], (BydSwitch) objArr[69], (BydSwitch) objArr[77], (SegmentedButton) objArr[24], (SegmentedButton) objArr[23], (SegmentedButton) objArr[25], (SegmentedButton) objArr[18], (SegmentedButton) objArr[17], (SegmentedButton) objArr[16], (SegmentedButton) objArr[15], (TextView) objArr[102], (TextView) objArr[127], (TextView) objArr[103], (TextView) objArr[104], (TextView) objArr[115], (TextView) objArr[114], (TextView) objArr[123], (TextView) objArr[122], (TextView) objArr[12], (TextView) objArr[20], (TextView) objArr[106], (TextView) objArr[10], (TextView) objArr[125], (TextView) objArr[128], (TextView) objArr[111], (TextView) objArr[110], (TextView) objArr[117], (TextView) objArr[116], (TextView) objArr[126], (TextView) objArr[109], (TextView) objArr[129], (TextView) objArr[118], (TextView) objArr[121], (TextView) objArr[124]);
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
