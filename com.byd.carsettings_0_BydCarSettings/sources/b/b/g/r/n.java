package b.b.g.r;

import com.byd.carsettings.R;

/* JADX INFO: compiled from: DrawableBgUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class n {
    public static int[] a(int i) {
        return new int[]{R.drawable.electricity_suv, R.drawable.electricity_suv_bg};
    }

    public static int[] b(int i) {
        int[] iArr = new int[2];
        if (i == 60 || i == 123 || i == 317 || i == 180) {
            iArr[0] = R.drawable.energy_car_suv;
            iArr[1] = R.drawable.energy_car_suv_bg;
        } else {
            iArr[0] = R.drawable.energy_car_suv;
            iArr[1] = R.drawable.energy_car_suv_bg;
        }
        return iArr;
    }

    public static int c(int i, boolean z) {
        return i == 143 ? z ? R.drawable.energy_engine_port_red : R.drawable.energy_engine_port_gray : z ? R.drawable.energy_engine_red : R.drawable.energy_engine_gray;
    }

    public static int d(int i, int i2) {
        if (i == 182 || i == 208) {
            if (i2 == 1) {
                return R.drawable.common_newenergy_charge2_car_img_uke;
            }
            if (i2 == 3) {
                return R.drawable.common_newenergy_charge_car_img_uke;
            }
            if (i2 == 2) {
                return R.drawable.common_newenergy_charge3_car_img_uke;
            }
            return 0;
        }
        if (i2 == 1) {
            return R.drawable.common_newenergy_charge2_car_img_uke;
        }
        if (i2 == 3) {
            return R.drawable.common_newenergy_charge_car_img_uke;
        }
        if (i2 == 2) {
            return R.drawable.common_newenergy_charge3_car_img_uke;
        }
        return 0;
    }

    public static int e(int i, int i2, boolean z) {
        return (i == 182 || i == 208) ? i2 == 1 ? z ? R.drawable.sw_front_wiper_uke : b.b.a.i.a.f531a ? R.drawable.uke_carlight_car_img_right2 : R.drawable.uke_carlight_car_img : i2 == 2 ? z ? R.drawable.sw_rear_wiper_uke : b.b.a.i.a.f531a ? R.drawable.uke_carlight_car_img_right2 : R.drawable.uke_carlight_car_img : b.b.a.i.a.f531a ? R.drawable.uke_carlight_car_img_right2 : R.drawable.uke_carlight_car_img : i2 == 1 ? z ? R.drawable.sw_front_wiper_uke : b.b.a.i.a.f531a ? R.drawable.uke_carlight_car_img_right2 : R.drawable.uke_carlight_car_img : i2 == 2 ? z ? R.drawable.sw_rear_wiper_uke : b.b.a.i.a.f531a ? R.drawable.uke_carlight_car_img_right2 : R.drawable.uke_carlight_car_img : b.b.a.i.a.f531a ? R.drawable.uke_carlight_car_img_right2 : R.drawable.uke_carlight_car_img;
    }

    public static int f(int i, int i2) {
        return (i == 182 || i == 208) ? b.b.a.i.a.f531a ? R.drawable.common_newenergy_itac_car_img_uke_right : R.drawable.common_newenergy_itac_car_img_uke : b.b.a.i.a.f531a ? R.drawable.common_newenergy_itac_car_img_uke_right : R.drawable.common_newenergy_itac_car_img_uke;
    }
}
