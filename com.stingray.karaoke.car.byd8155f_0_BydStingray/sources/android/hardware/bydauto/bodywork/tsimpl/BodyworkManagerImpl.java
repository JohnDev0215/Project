package android.hardware.bydauto.bodywork.tsimpl;

import M5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.ac.tsimpl.a;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.provider.Settings;
import android.util.SparseArray;
import b.d;
import com.ts.lib.caradapter.body.CarBodyPacket;
import com.ts.lib.caradapter.body.OnBodyChangedListener;
import com.ts.lib.engineeringmode.EngineeringSdkManager;
import com.ts.ota.otasdkmgr.OtaSdkManager;
import e.C1139b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BodyworkManagerImpl extends AbsTsMyCar {
    private static final String DEFAULT_VIN = "tes0000000000000000";
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_BodyworkManagerImpl";
    private static final int S_NUMBER_THREE = 3;
    private static final String TAG = "BodyworkManagerImpl";
    private static final int WINDOW_CONTROL_STATE_LEFT_FRONT_S = 1;
    private static final int WINDOW_CONTROL_STATE_LEFT_REAR_S = 3;
    private static final int WINDOW_CONTROL_STATE_RIGHT_FRONT_S = 2;
    private static final int WINDOW_CONTROL_STATE_RIGHT_REAR_S = 4;
    private static volatile BodyworkManagerImpl sInstance;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;
    private OtaSdkManager mOtaSdkManager;
    private EngineeringSdkManager mSdkManager;
    private C1139b mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoBodyworkListener> mAbsBydAutoBodyworkListeners = new CopyOnWriteArrayList<>();
    private boolean mIgnoreBydPermission = false;

    public BodyworkManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        ContentResolver contentResolver = this.mContentResolver;
        if (contentResolver != null) {
            int i7 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i7;
            if (1 == i7) {
                Log.v(TAG, "BodyworkManagerImpl mForTestValue is" + this.mForTest + " execute return");
                this.mTestCallback = new C1139b();
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnBodyChangedListener(new OnBodyChangedListener() { // from class: android.hardware.bydauto.bodywork.tsimpl.BodyworkManagerImpl.1
            public void onBinderStateChanged(boolean z6) {
                Log.v(BodyworkManagerImpl.TAG, "onBinderStateChanged is " + z6);
            }

            public void onBodyChanged(int i10, int i11, CarBodyPacket carBodyPacket) {
                BodyworkManagerImpl.this.bodyworkCallback(i10, i11, carBodyPacket);
            }
        });
        this.mSdkManager = EngineeringSdkManager.getInstance(context);
        this.mOtaSdkManager = OtaSdkManager.getInstance(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bodyworkCallback(int i7, int i10, CarBodyPacket carBodyPacket) {
        int i11;
        switch (i7) {
            case 557845265:
                Iterator<AbsBYDAutoBodyworkListener> it = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it.hasNext()) {
                    it.next().onFuelElecLowPowerChanged(carBodyPacket.getFuelElecLowPowerR());
                }
                break;
            case 557857850:
                Iterator<AbsBYDAutoBodyworkListener> it2 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it2.hasNext()) {
                    it2.next().onMainSaveOrRecoverStateChanged(2, carBodyPacket.getRecoverOrSaveParamsChanged5());
                }
                caseFeatureIds(4614, Integer.valueOf(carBodyPacket.getRecoverOrSaveParamsChanged5()));
                break;
            case 557860644:
                int lightRearPosition = carBodyPacket.getLightRearPosition();
                Iterator<AbsBYDAutoBodyworkListener> it3 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onRearLampLightStateChanged(lightRearPosition);
                }
                caseFeatureIds(2006, Integer.valueOf(carBodyPacket.getLightRearPosition()));
                break;
            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_REAR_PANELR /* 557862991 */:
                Iterator<AbsBYDAutoBodyworkListener> it4 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it4.hasNext()) {
                    it4.next().onWindowOpenPercentChanged(7, carBodyPacket.getWindowOpenPercentRearPanel());
                }
                caseFeatureIds(2012, Integer.valueOf(carBodyPacket.getWindowOpenPercentRearPanel()));
                break;
            case BodyworkHalId.ID_READY_INDICATOR_LIGHT_STATER /* 557863013 */:
                Iterator<AbsBYDAutoBodyworkListener> it5 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onReadyIndicatorLightStateChanged(carBodyPacket.getReadyIndicatorLightStateR());
                }
                break;
            case BodyworkHalId.ID_REAR_WINDOWBLIND_INIT_STATE_NEW_R /* 557863019 */:
                Iterator<AbsBYDAutoBodyworkListener> it6 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it6.hasNext()) {
                    it6.next().onRearWindowblindInitStateChanged(carBodyPacket.getRearWindowblindInitStateNewR());
                }
                caseFeatureIds(2009, Integer.valueOf(carBodyPacket.getRearWindowblindInitStateNewR()));
                break;
            case 557875227:
                Iterator<AbsBYDAutoBodyworkListener> it7 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it7.hasNext()) {
                    it7.next().onBatteryVoltageLevelLowTips(carBodyPacket.getBatteryVoltageLevelLowTips());
                }
                break;
            case BodyworkHalId.ID_CAN_055_STATE /* 557945600 */:
                int i12 = carBodyPacket.getCan055R()[0];
                hasMessageListener(CanIds.getRealCanId(i12), i12, carBodyPacket.getCan055R()[1]);
                break;
            case BodyworkHalId.ID_CAN_0D5_STATE /* 557945603 */:
                int i13 = carBodyPacket.getCan0d5()[0];
                hasMessageListener(CanIds.getRealCanId(i13), i13, carBodyPacket.getCan0d5()[1]);
                break;
            case BodyworkHalId.ID_CAN_10D_STATE /* 557945605 */:
                int i14 = carBodyPacket.getCan10d()[0];
                hasMessageListener(CanIds.getRealCanId(i14), i14, carBodyPacket.getCan10d()[1]);
                break;
            case BodyworkHalId.ID_CAN_121_STATE /* 557945606 */:
                int i15 = carBodyPacket.getCan121()[0];
                hasMessageListener(CanIds.getRealCanId(i15), i15, carBodyPacket.getCan121()[1]);
                break;
            case BodyworkHalId.ID_CAN_123_STATE /* 557945607 */:
                int i16 = carBodyPacket.getCan123()[0];
                hasMessageListener(CanIds.getRealCanId(i16), i16, carBodyPacket.getCan123()[1]);
                break;
            case BodyworkHalId.ID_CAN_12D_STATE /* 557945608 */:
                int i17 = carBodyPacket.getCan12d()[0];
                hasMessageListener(CanIds.getRealCanId(i17), i17, carBodyPacket.getCan12d()[1]);
                break;
            case BodyworkHalId.ID_CAN_12F_STATE /* 557945609 */:
                int i18 = carBodyPacket.getCan12f()[0];
                hasMessageListener(CanIds.getRealCanId(i18), i18, carBodyPacket.getCan12f()[1]);
                break;
            case BodyworkHalId.ID_CAN_133_STATE /* 557945610 */:
                int i19 = carBodyPacket.getCan133()[0];
                hasMessageListener(CanIds.getRealCanId(i19), i19, carBodyPacket.getCan133()[1]);
                break;
            case BodyworkHalId.ID_CAN_138_STATE /* 557945611 */:
                int i20 = carBodyPacket.getCan138()[0];
                hasMessageListener(CanIds.getRealCanId(i20), i20, carBodyPacket.getCan138()[1]);
                break;
            case BodyworkHalId.ID_CAN_13F_STATE /* 557945612 */:
                int i21 = carBodyPacket.getCan13f()[0];
                hasMessageListener(CanIds.getRealCanId(i21), i21, carBodyPacket.getCan13f()[1]);
                break;
            case BodyworkHalId.ID_CAN_144_STATE /* 557945613 */:
                int i22 = carBodyPacket.getCan144()[0];
                hasMessageListener(CanIds.getRealCanId(i22), i22, carBodyPacket.getCan144()[1]);
                break;
            case BodyworkHalId.ID_CAN_151_STATE /* 557945614 */:
                int i23 = carBodyPacket.getCan151()[0];
                hasMessageListener(CanIds.getRealCanId(i23), i23, carBodyPacket.getCan151()[1]);
                break;
            case BodyworkHalId.ID_CAN_167_STATE /* 557945615 */:
                int i24 = carBodyPacket.getCan167()[0];
                hasMessageListener(CanIds.getRealCanId(i24), i24, carBodyPacket.getCan167()[1]);
                break;
            case BodyworkHalId.ID_CAN_18A_STATE /* 557945616 */:
                int i25 = carBodyPacket.getCan18a()[0];
                hasMessageListener(CanIds.getRealCanId(i25), i25, carBodyPacket.getCan18a()[1]);
                break;
            case BodyworkHalId.ID_CAN_19F_STATE /* 557945617 */:
                int i26 = carBodyPacket.getCan19f()[0];
                hasMessageListener(CanIds.getRealCanId(i26), i26, carBodyPacket.getCan19f()[1]);
                break;
            case BodyworkHalId.ID_CAN_1C0_STATE /* 557945618 */:
                int i27 = carBodyPacket.getCan1c0()[0];
                hasMessageListener(CanIds.getRealCanId(i27), i27, carBodyPacket.getCan1c0()[1]);
                break;
            case BodyworkHalId.ID_CAN_1D1_STATE /* 557945619 */:
                int i28 = carBodyPacket.getCan1d1()[0];
                hasMessageListener(CanIds.getRealCanId(i28), i28, carBodyPacket.getCan1d1()[1]);
                break;
            case BodyworkHalId.ID_CAN_218_STATE /* 557945620 */:
                int i29 = carBodyPacket.getCan218()[0];
                hasMessageListener(CanIds.getRealCanId(i29), i29, carBodyPacket.getCan218()[1]);
                break;
            case BodyworkHalId.ID_CAN_223_STATE /* 557945621 */:
                int i30 = carBodyPacket.getCan223()[0];
                hasMessageListener(CanIds.getRealCanId(i30), i30, carBodyPacket.getCan223()[1]);
                break;
            case BodyworkHalId.ID_CAN_233_STATE /* 557945622 */:
                int i31 = carBodyPacket.getCan233()[0];
                hasMessageListener(CanIds.getRealCanId(i31), i31, carBodyPacket.getCan233()[1]);
                break;
            case BodyworkHalId.ID_CAN_235_STATE /* 557945623 */:
                int i32 = carBodyPacket.getCan235()[0];
                hasMessageListener(CanIds.getRealCanId(i32), i32, carBodyPacket.getCan235()[1]);
                break;
            case BodyworkHalId.ID_CAN_23A_STATE /* 557945624 */:
                int i33 = carBodyPacket.getCan23a()[0];
                hasMessageListener(CanIds.getRealCanId(i33), i33, carBodyPacket.getCan23a()[1]);
                break;
            case BodyworkHalId.ID_CAN_242_STATE /* 557945625 */:
                int i34 = carBodyPacket.getCan242()[0];
                hasMessageListener(CanIds.getRealCanId(i34), i34, carBodyPacket.getCan242()[1]);
                break;
            case BodyworkHalId.ID_CAN_245_STATE /* 557945626 */:
                int i35 = carBodyPacket.getCan245()[0];
                hasMessageListener(CanIds.getRealCanId(i35), i35, carBodyPacket.getCan245()[1]);
                break;
            case BodyworkHalId.ID_CAN_248_STATE /* 557945627 */:
                int i36 = carBodyPacket.getCan248()[0];
                hasMessageListener(CanIds.getRealCanId(i36), i36, carBodyPacket.getCan248()[1]);
                break;
            case BodyworkHalId.ID_CAN_24A_STATE /* 557945628 */:
                int i37 = carBodyPacket.getCan24a()[0];
                hasMessageListener(CanIds.getRealCanId(i37), i37, carBodyPacket.getCan24a()[1]);
                break;
            case BodyworkHalId.ID_CAN_24C_STATE /* 557945629 */:
                int i38 = carBodyPacket.getCan24c()[0];
                hasMessageListener(CanIds.getRealCanId(i38), i38, carBodyPacket.getCan24c()[1]);
                break;
            case BodyworkHalId.ID_CAN_25D_STATE /* 557945630 */:
                int i39 = carBodyPacket.getCan25d()[0];
                hasMessageListener(CanIds.getRealCanId(i39), i39, carBodyPacket.getCan25d()[1]);
                break;
            case BodyworkHalId.ID_CAN_261_STATE /* 557945631 */:
                int i40 = carBodyPacket.getCan261()[0];
                hasMessageListener(CanIds.getRealCanId(i40), i40, carBodyPacket.getCan261()[1]);
                break;
            case BodyworkHalId.ID_CAN_26F_STATE /* 557945632 */:
                int i41 = carBodyPacket.getCan26f()[0];
                hasMessageListener(CanIds.getRealCanId(i41), i41, carBodyPacket.getCan26f()[1]);
                break;
            case BodyworkHalId.ID_CAN_271_STATE /* 557945633 */:
                int i42 = carBodyPacket.getCan271()[0];
                hasMessageListener(CanIds.getRealCanId(i42), i42, carBodyPacket.getCan271()[1]);
                break;
            case BodyworkHalId.ID_CAN_272_STATE /* 557945634 */:
                int i43 = carBodyPacket.getCan272()[0];
                hasMessageListener(CanIds.getRealCanId(i43), i43, carBodyPacket.getCan272()[1]);
                break;
            case BodyworkHalId.ID_CAN_275_STATE /* 557945635 */:
                int i44 = carBodyPacket.getCan275()[0];
                hasMessageListener(CanIds.getRealCanId(i44), i44, carBodyPacket.getCan275()[1]);
                break;
            case BodyworkHalId.ID_CAN_27A_STATE /* 557945636 */:
                int i45 = carBodyPacket.getCan27a()[0];
                hasMessageListener(CanIds.getRealCanId(i45), i45, carBodyPacket.getCan27a()[1]);
                break;
            case BodyworkHalId.ID_CAN_287_STATE /* 557945637 */:
                int i46 = carBodyPacket.getCan287()[0];
                hasMessageListener(CanIds.getRealCanId(i46), i46, carBodyPacket.getCan287()[1]);
                break;
            case BodyworkHalId.ID_CAN_288_STATE /* 557945638 */:
                int i47 = carBodyPacket.getCan288()[0];
                hasMessageListener(CanIds.getRealCanId(i47), i47, carBodyPacket.getCan288()[1]);
                break;
            case BodyworkHalId.ID_CAN_28F_STATE /* 557945639 */:
                int i48 = carBodyPacket.getCan28f()[0];
                hasMessageListener(CanIds.getRealCanId(i48), i48, carBodyPacket.getCan28f()[1]);
                break;
            case BodyworkHalId.ID_CAN_294_STATE /* 557945640 */:
                int i49 = carBodyPacket.getCan294()[0];
                hasMessageListener(CanIds.getRealCanId(i49), i49, carBodyPacket.getCan294()[1]);
                break;
            case BodyworkHalId.ID_CAN_296_STATE /* 557945641 */:
                int i50 = carBodyPacket.getCan296()[0];
                hasMessageListener(CanIds.getRealCanId(i50), i50, carBodyPacket.getCan296()[1]);
                break;
            case BodyworkHalId.ID_CAN_29C_STATE /* 557945642 */:
                int i51 = carBodyPacket.getCan29c()[0];
                hasMessageListener(CanIds.getRealCanId(i51), i51, carBodyPacket.getCan29c()[1]);
                break;
            case BodyworkHalId.ID_CAN_29E_STATE /* 557945643 */:
                int i52 = carBodyPacket.getCan29e()[0];
                hasMessageListener(CanIds.getRealCanId(i52), i52, carBodyPacket.getCan29e()[1]);
                break;
            case BodyworkHalId.ID_CAN_2A4_STATE /* 557945644 */:
                int i53 = carBodyPacket.getCan2a4()[0];
                hasMessageListener(CanIds.getRealCanId(i53), i53, carBodyPacket.getCan2a4()[1]);
                break;
            case BodyworkHalId.ID_CAN_2A6_STATE /* 557945645 */:
                int i54 = carBodyPacket.getCan2a6()[0];
                hasMessageListener(CanIds.getRealCanId(i54), i54, carBodyPacket.getCan2a6()[1]);
                break;
            case BodyworkHalId.ID_CAN_2A7_STATE /* 557945646 */:
                int i55 = carBodyPacket.getCan2a7()[0];
                hasMessageListener(CanIds.getRealCanId(i55), i55, carBodyPacket.getCan2a7()[1]);
                break;
            case BodyworkHalId.ID_CAN_2B0_STATE /* 557945647 */:
                int i56 = carBodyPacket.getCan2b0()[0];
                hasMessageListener(CanIds.getRealCanId(i56), i56, carBodyPacket.getCan2b0()[1]);
                break;
            case BodyworkHalId.ID_CAN_2B4_STATE /* 557945648 */:
                int i57 = carBodyPacket.getCan2b4()[0];
                hasMessageListener(CanIds.getRealCanId(i57), i57, carBodyPacket.getCan2b4()[1]);
                break;
            case BodyworkHalId.ID_CAN_2B6_STATE /* 557945649 */:
                int i58 = carBodyPacket.getCan2b6()[0];
                hasMessageListener(CanIds.getRealCanId(i58), i58, carBodyPacket.getCan2b6()[1]);
                break;
            case BodyworkHalId.ID_CAN_2BA_STATE /* 557945650 */:
                int i59 = carBodyPacket.getCan2ba()[0];
                hasMessageListener(CanIds.getRealCanId(i59), i59, carBodyPacket.getCan2ba()[1]);
                break;
            case BodyworkHalId.ID_CAN_2BC_STATE /* 557945651 */:
                int i60 = carBodyPacket.getCan2bc()[0];
                hasMessageListener(CanIds.getRealCanId(i60), i60, carBodyPacket.getCan2bc()[1]);
                break;
            case BodyworkHalId.ID_CAN_2C0_STATE /* 557945652 */:
                int i61 = carBodyPacket.getCan2c0()[0];
                hasMessageListener(CanIds.getRealCanId(i61), i61, carBodyPacket.getCan2c0()[1]);
                break;
            case BodyworkHalId.ID_CAN_2D5_STATE /* 557945653 */:
                int i62 = carBodyPacket.getCan2d5()[0];
                hasMessageListener(CanIds.getRealCanId(i62), i62, carBodyPacket.getCan2d5()[1]);
                break;
            case BodyworkHalId.ID_CAN_2EB_STATE /* 557945654 */:
                int i63 = carBodyPacket.getCan2eb()[0];
                hasMessageListener(CanIds.getRealCanId(i63), i63, carBodyPacket.getCan2eb()[1]);
                break;
            case BodyworkHalId.ID_CAN_2F0_STATE /* 557945655 */:
                int i64 = carBodyPacket.getCan2f0()[0];
                hasMessageListener(CanIds.getRealCanId(i64), i64, carBodyPacket.getCan2f0()[1]);
                break;
            case BodyworkHalId.ID_CAN_30D_STATE /* 557945656 */:
                int i65 = carBodyPacket.getCan30d()[0];
                hasMessageListener(CanIds.getRealCanId(i65), i65, carBodyPacket.getCan30d()[1]);
                break;
            case BodyworkHalId.ID_CAN_31E_STATE /* 557945657 */:
                int i66 = carBodyPacket.getCan31e()[0];
                hasMessageListener(CanIds.getRealCanId(i66), i66, carBodyPacket.getCan31e()[1]);
                break;
            case BodyworkHalId.ID_CAN_321_STATE /* 557945658 */:
                int i67 = carBodyPacket.getCan321()[0];
                hasMessageListener(CanIds.getRealCanId(i67), i67, carBodyPacket.getCan321()[1]);
                break;
            case BodyworkHalId.ID_CAN_323_STATE /* 557945659 */:
                int i68 = carBodyPacket.getCan323()[0];
                hasMessageListener(CanIds.getRealCanId(i68), i68, carBodyPacket.getCan323()[1]);
                break;
            case BodyworkHalId.ID_CAN_32D_STATE /* 557945660 */:
                int i69 = carBodyPacket.getCan32d()[0];
                hasMessageListener(CanIds.getRealCanId(i69), i69, carBodyPacket.getCan32d()[1]);
                break;
            case BodyworkHalId.ID_CAN_334_STATE /* 557945661 */:
                int i70 = carBodyPacket.getCan334()[0];
                hasMessageListener(CanIds.getRealCanId(i70), i70, carBodyPacket.getCan334()[1]);
                break;
            case BodyworkHalId.ID_CAN_337_STATE /* 557945662 */:
                int i71 = carBodyPacket.getCan337()[0];
                hasMessageListener(CanIds.getRealCanId(i71), i71, carBodyPacket.getCan337()[1]);
                break;
            case BodyworkHalId.ID_CAN_341_STATE /* 557945663 */:
                int i72 = carBodyPacket.getCan341()[0];
                hasMessageListener(CanIds.getRealCanId(i72), i72, carBodyPacket.getCan341()[1]);
                break;
            case BodyworkHalId.ID_CAN_342_STATE /* 557945664 */:
                int i73 = carBodyPacket.getCan342()[0];
                hasMessageListener(CanIds.getRealCanId(i73), i73, carBodyPacket.getCan342()[1]);
                break;
            case BodyworkHalId.ID_CAN_346_STATE /* 557945665 */:
                int i74 = carBodyPacket.getCan346()[0];
                hasMessageListener(CanIds.getRealCanId(i74), i74, carBodyPacket.getCan346()[1]);
                break;
            case BodyworkHalId.ID_CAN_347_STATE /* 557945666 */:
                int i75 = carBodyPacket.getCan347()[0];
                hasMessageListener(CanIds.getRealCanId(i75), i75, carBodyPacket.getCan347()[1]);
                break;
            case BodyworkHalId.ID_CAN_34A_STATE /* 557945667 */:
                int i76 = carBodyPacket.getCan34a()[0];
                hasMessageListener(CanIds.getRealCanId(i76), i76, carBodyPacket.getCan34a()[1]);
                break;
            case BodyworkHalId.ID_CAN_34F_STATE /* 557945668 */:
                int i77 = carBodyPacket.getCan34f()[0];
                hasMessageListener(CanIds.getRealCanId(i77), i77, carBodyPacket.getCan34f()[1]);
                break;
            case BodyworkHalId.ID_CAN_36B_STATE /* 557945669 */:
                int i78 = carBodyPacket.getCan36b()[0];
                hasMessageListener(CanIds.getRealCanId(i78), i78, carBodyPacket.getCan36b()[1]);
                break;
            case BodyworkHalId.ID_CAN_36D_STATE /* 557945670 */:
                int i79 = carBodyPacket.getCan36d()[0];
                hasMessageListener(CanIds.getRealCanId(i79), i79, carBodyPacket.getCan36d()[1]);
                break;
            case BodyworkHalId.ID_CAN_36E_STATE /* 557945671 */:
                int i80 = carBodyPacket.getCan36e()[0];
                hasMessageListener(CanIds.getRealCanId(i80), i80, carBodyPacket.getCan36e()[1]);
                break;
            case BodyworkHalId.ID_CAN_37F_STATE /* 557945672 */:
                int i81 = carBodyPacket.getCan37f()[0];
                hasMessageListener(CanIds.getRealCanId(i81), i81, carBodyPacket.getCan37f()[1]);
                break;
            case BodyworkHalId.ID_CAN_38A_STATE /* 557945673 */:
                int i82 = carBodyPacket.getCan38a()[0];
                hasMessageListener(CanIds.getRealCanId(i82), i82, carBodyPacket.getCan38a()[1]);
                break;
            case BodyworkHalId.ID_CAN_38B_STATE /* 557945674 */:
                int i83 = carBodyPacket.getCan38b()[0];
                hasMessageListener(CanIds.getRealCanId(i83), i83, carBodyPacket.getCan38b()[1]);
                break;
            case BodyworkHalId.ID_CAN_394_STATE /* 557945675 */:
                int i84 = carBodyPacket.getCan394()[0];
                hasMessageListener(CanIds.getRealCanId(i84), i84, carBodyPacket.getCan394()[1]);
                break;
            case BodyworkHalId.ID_CAN_396_STATE /* 557945676 */:
                int i85 = carBodyPacket.getCan396()[0];
                hasMessageListener(CanIds.getRealCanId(i85), i85, carBodyPacket.getCan396()[1]);
                break;
            case BodyworkHalId.ID_CAN_3A0_STATE /* 557945677 */:
                int i86 = carBodyPacket.getCan3a0()[0];
                hasMessageListener(CanIds.getRealCanId(i86), i86, carBodyPacket.getCan3a0()[1]);
                break;
            case BodyworkHalId.ID_CAN_3A7_STATE /* 557945678 */:
                int i87 = carBodyPacket.getCan3a7()[0];
                hasMessageListener(CanIds.getRealCanId(i87), i87, carBodyPacket.getCan3a7()[1]);
                break;
            case BodyworkHalId.ID_CAN_3AC_STATE /* 557945679 */:
                int i88 = carBodyPacket.getCan3ac()[0];
                hasMessageListener(CanIds.getRealCanId(i88), i88, carBodyPacket.getCan3ac()[1]);
                break;
            case BodyworkHalId.ID_CAN_3AD_STATE /* 557945680 */:
                int i89 = carBodyPacket.getCan3ad()[0];
                hasMessageListener(CanIds.getRealCanId(i89), i89, carBodyPacket.getCan3ad()[1]);
                break;
            case BodyworkHalId.ID_CAN_3B0_STATE /* 557945681 */:
                int i90 = carBodyPacket.getCan3b0()[0];
                hasMessageListener(CanIds.getRealCanId(i90), i90, carBodyPacket.getCan3b0()[1]);
                break;
            case BodyworkHalId.ID_CAN_3B3_STATE /* 557945682 */:
                int i91 = carBodyPacket.getCan3b3()[0];
                hasMessageListener(CanIds.getRealCanId(i91), i91, carBodyPacket.getCan3b3()[1]);
                break;
            case BodyworkHalId.ID_CAN_3B4_STATE /* 557945683 */:
                int i92 = carBodyPacket.getCan3b4()[0];
                hasMessageListener(CanIds.getRealCanId(i92), i92, carBodyPacket.getCan3b4()[1]);
                break;
            case BodyworkHalId.ID_CAN_3BB_STATE /* 557945684 */:
                int i93 = carBodyPacket.getCan3bb()[0];
                hasMessageListener(CanIds.getRealCanId(i93), i93, carBodyPacket.getCan3bb()[1]);
                break;
            case BodyworkHalId.ID_CAN_3BD_STATE /* 557945685 */:
                int i94 = carBodyPacket.getCan3bd()[0];
                hasMessageListener(CanIds.getRealCanId(i94), i94, carBodyPacket.getCan3bd()[1]);
                break;
            case BodyworkHalId.ID_CAN_3C1_STATE /* 557945686 */:
                int i95 = carBodyPacket.getCan3c1()[0];
                hasMessageListener(CanIds.getRealCanId(i95), i95, carBodyPacket.getCan3c1()[1]);
                break;
            case BodyworkHalId.ID_CAN_3C8_STATE /* 557945687 */:
                int i96 = carBodyPacket.getCan3c8()[0];
                hasMessageListener(CanIds.getRealCanId(i96), i96, carBodyPacket.getCan3c8()[1]);
                break;
            case BodyworkHalId.ID_CAN_3CA_STATE /* 557945688 */:
                int i97 = carBodyPacket.getCan3ca()[0];
                hasMessageListener(CanIds.getRealCanId(i97), i97, carBodyPacket.getCan3ca()[1]);
                break;
            case BodyworkHalId.ID_CAN_3CD_STATE /* 557945689 */:
                int i98 = carBodyPacket.getCan3cd()[0];
                hasMessageListener(CanIds.getRealCanId(i98), i98, carBodyPacket.getCan3cd()[1]);
                break;
            case BodyworkHalId.ID_CAN_3D9_STATE /* 557945690 */:
                int i99 = carBodyPacket.getCan3d9()[0];
                hasMessageListener(CanIds.getRealCanId(i99), i99, carBodyPacket.getCan3d9()[1]);
                break;
            case BodyworkHalId.ID_CAN_3DA_STATE /* 557945691 */:
                int i100 = carBodyPacket.getCan3da()[0];
                hasMessageListener(CanIds.getRealCanId(i100), i100, carBodyPacket.getCan3da()[1]);
                break;
            case BodyworkHalId.ID_CAN_3F1_STATE /* 557945692 */:
                int i101 = carBodyPacket.getCan3f1()[0];
                hasMessageListener(CanIds.getRealCanId(i101), i101, carBodyPacket.getCan3f1()[1]);
                break;
            case BodyworkHalId.ID_CAN_3F3_STATE /* 557945693 */:
                int i102 = carBodyPacket.getCan3f3()[0];
                hasMessageListener(CanIds.getRealCanId(i102), i102, carBodyPacket.getCan3f3()[1]);
                break;
            case BodyworkHalId.ID_CAN_3FE_STATE /* 557945694 */:
                int i103 = carBodyPacket.getCan3fe()[0];
                hasMessageListener(CanIds.getRealCanId(i103), i103, carBodyPacket.getCan3fe()[1]);
                break;
            case BodyworkHalId.ID_CAN_3FF_STATE /* 557945695 */:
                int i104 = carBodyPacket.getCan3ff()[0];
                hasMessageListener(CanIds.getRealCanId(i104), i104, carBodyPacket.getCan3ff()[1]);
                break;
            case BodyworkHalId.ID_CAN_401_STATE /* 557945696 */:
                int i105 = carBodyPacket.getCan401()[0];
                hasMessageListener(CanIds.getRealCanId(i105), i105, carBodyPacket.getCan401()[1]);
                break;
            case BodyworkHalId.ID_CAN_402_STATE /* 557945697 */:
                int i106 = carBodyPacket.getCan402()[0];
                hasMessageListener(CanIds.getRealCanId(i106), i106, carBodyPacket.getCan402()[1]);
                break;
            case BodyworkHalId.ID_CAN_404_STATE /* 557945698 */:
                int i107 = carBodyPacket.getCan404()[0];
                hasMessageListener(CanIds.getRealCanId(i107), i107, carBodyPacket.getCan404()[1]);
                break;
            case BodyworkHalId.ID_CAN_407_STATE /* 557945699 */:
                int i108 = carBodyPacket.getCan407()[0];
                hasMessageListener(CanIds.getRealCanId(i108), i108, carBodyPacket.getCan407()[1]);
                break;
            case BodyworkHalId.ID_CAN_40D_STATE /* 557945700 */:
                int i109 = carBodyPacket.getCan40d()[0];
                hasMessageListener(CanIds.getRealCanId(i109), i109, carBodyPacket.getCan40d()[1]);
                break;
            case BodyworkHalId.ID_CAN_417_STATE /* 557945701 */:
                int i110 = carBodyPacket.getCan417()[0];
                hasMessageListener(CanIds.getRealCanId(i110), i110, carBodyPacket.getCan417()[1]);
                break;
            case BodyworkHalId.ID_CAN_418_STATE /* 557945702 */:
                int i111 = carBodyPacket.getCan418()[0];
                hasMessageListener(CanIds.getRealCanId(i111), i111, carBodyPacket.getCan418()[1]);
                break;
            case BodyworkHalId.ID_CAN_41A_STATE /* 557945703 */:
                int i112 = carBodyPacket.getCan41a()[0];
                hasMessageListener(CanIds.getRealCanId(i112), i112, carBodyPacket.getCan41a()[1]);
                break;
            case BodyworkHalId.ID_CAN_41C_STATE /* 557945704 */:
                int i113 = carBodyPacket.getCan41c()[0];
                hasMessageListener(CanIds.getRealCanId(i113), i113, carBodyPacket.getCan41c()[1]);
                break;
            case BodyworkHalId.ID_CAN_41E_STATE /* 557945705 */:
                int i114 = carBodyPacket.getCan41e()[0];
                hasMessageListener(CanIds.getRealCanId(i114), i114, carBodyPacket.getCan41e()[1]);
                break;
            case BodyworkHalId.ID_CAN_41F_STATE /* 557945706 */:
                int i115 = carBodyPacket.getCan41f()[0];
                hasMessageListener(CanIds.getRealCanId(i115), i115, carBodyPacket.getCan41f()[1]);
                break;
            case BodyworkHalId.ID_CAN_429_STATE /* 557945707 */:
                int i116 = carBodyPacket.getCan429()[0];
                hasMessageListener(CanIds.getRealCanId(i116), i116, carBodyPacket.getCan429()[1]);
                break;
            case BodyworkHalId.ID_CAN_42E_STATE /* 557945708 */:
                int i117 = carBodyPacket.getCan42e()[0];
                hasMessageListener(CanIds.getRealCanId(i117), i117, carBodyPacket.getCan42e()[1]);
                break;
            case BodyworkHalId.ID_CAN_42F_STATE /* 557945709 */:
                int i118 = carBodyPacket.getCan42f()[0];
                hasMessageListener(CanIds.getRealCanId(i118), i118, carBodyPacket.getCan42f()[1]);
                break;
            case BodyworkHalId.ID_CAN_432_STATE /* 557945710 */:
                int i119 = carBodyPacket.getCan432()[0];
                hasMessageListener(CanIds.getRealCanId(i119), i119, carBodyPacket.getCan432()[1]);
                break;
            case BodyworkHalId.ID_CAN_433_STATE /* 557945711 */:
                int i120 = carBodyPacket.getCan433()[0];
                hasMessageListener(CanIds.getRealCanId(i120), i120, carBodyPacket.getCan433()[1]);
                break;
            case BodyworkHalId.ID_CAN_434_STATE /* 557945712 */:
                int i121 = carBodyPacket.getCan434()[0];
                hasMessageListener(CanIds.getRealCanId(i121), i121, carBodyPacket.getCan434()[1]);
                break;
            case BodyworkHalId.ID_CAN_436_STATE /* 557945713 */:
                int i122 = carBodyPacket.getCan436()[0];
                hasMessageListener(CanIds.getRealCanId(i122), i122, carBodyPacket.getCan436()[1]);
                break;
            case BodyworkHalId.ID_CAN_437_STATE /* 557945714 */:
                int i123 = carBodyPacket.getCan437()[0];
                hasMessageListener(CanIds.getRealCanId(i123), i123, carBodyPacket.getCan437()[1]);
                break;
            case BodyworkHalId.ID_CAN_438_STATE /* 557945715 */:
                int i124 = carBodyPacket.getCan438()[0];
                hasMessageListener(CanIds.getRealCanId(i124), i124, carBodyPacket.getCan438()[1]);
                break;
            case BodyworkHalId.ID_CAN_43E_STATE /* 557945716 */:
                int i125 = carBodyPacket.getCan43e()[0];
                hasMessageListener(CanIds.getRealCanId(i125), i125, carBodyPacket.getCan43e()[1]);
                break;
            case BodyworkHalId.ID_CAN_43F_STATE /* 557945717 */:
                int i126 = carBodyPacket.getCan43f()[0];
                hasMessageListener(CanIds.getRealCanId(i126), i126, carBodyPacket.getCan43f()[1]);
                break;
            case BodyworkHalId.ID_CAN_443_STATE /* 557945718 */:
                int i127 = carBodyPacket.getCan443()[0];
                hasMessageListener(CanIds.getRealCanId(i127), i127, carBodyPacket.getCan443()[1]);
                break;
            case BodyworkHalId.ID_CAN_444_STATE /* 557945719 */:
                int i128 = carBodyPacket.getCan444()[0];
                hasMessageListener(CanIds.getRealCanId(i128), i128, carBodyPacket.getCan444()[1]);
                break;
            case BodyworkHalId.ID_CAN_447_STATE /* 557945720 */:
                int i129 = carBodyPacket.getCan447()[0];
                hasMessageListener(CanIds.getRealCanId(i129), i129, carBodyPacket.getCan447()[1]);
                break;
            case BodyworkHalId.ID_CAN_449_STATE /* 557945721 */:
                int i130 = carBodyPacket.getCan449()[0];
                hasMessageListener(CanIds.getRealCanId(i130), i130, carBodyPacket.getCan449()[1]);
                break;
            case BodyworkHalId.ID_CAN_453_STATE /* 557945722 */:
                int i131 = carBodyPacket.getCan453()[0];
                hasMessageListener(CanIds.getRealCanId(i131), i131, carBodyPacket.getCan453()[1]);
                break;
            case BodyworkHalId.ID_CAN_454_STATE /* 557945723 */:
                int i132 = carBodyPacket.getCan454()[0];
                hasMessageListener(CanIds.getRealCanId(i132), i132, carBodyPacket.getCan454()[1]);
                break;
            case BodyworkHalId.ID_CAN_461_STATE /* 557945724 */:
                int i133 = carBodyPacket.getCan461()[0];
                hasMessageListener(CanIds.getRealCanId(i133), i133, carBodyPacket.getCan461()[1]);
                break;
            case BodyworkHalId.ID_CAN_46C_STATE /* 557945725 */:
                int i134 = carBodyPacket.getCan46c()[0];
                hasMessageListener(CanIds.getRealCanId(i134), i134, carBodyPacket.getCan46c()[1]);
                break;
            case BodyworkHalId.ID_CAN_470_STATE /* 557945726 */:
                int i135 = carBodyPacket.getCan470()[0];
                hasMessageListener(CanIds.getRealCanId(i135), i135, carBodyPacket.getCan470()[1]);
                break;
            case BodyworkHalId.ID_CAN_475_STATE /* 557945727 */:
                int i136 = carBodyPacket.getCan475()[0];
                hasMessageListener(CanIds.getRealCanId(i136), i136, carBodyPacket.getCan475()[1]);
                break;
            case BodyworkHalId.ID_CAN_480_STATE /* 557945728 */:
                int i137 = carBodyPacket.getCan480()[0];
                hasMessageListener(CanIds.getRealCanId(i137), i137, carBodyPacket.getCan480()[1]);
                break;
            case BodyworkHalId.ID_CAN_481_STATE /* 557945729 */:
                int i138 = carBodyPacket.getCan481()[0];
                hasMessageListener(CanIds.getRealCanId(i138), i138, carBodyPacket.getCan481()[1]);
                break;
            case BodyworkHalId.ID_CAN_486_STATE /* 557945730 */:
                int i139 = carBodyPacket.getCan486()[0];
                hasMessageListener(CanIds.getRealCanId(i139), i139, carBodyPacket.getCan486()[1]);
                break;
            case BodyworkHalId.ID_CAN_487_STATE /* 557945731 */:
                int i140 = carBodyPacket.getCan487()[0];
                hasMessageListener(CanIds.getRealCanId(i140), i140, carBodyPacket.getCan487()[1]);
                break;
            case BodyworkHalId.ID_CAN_488_STATE /* 557945732 */:
                int i141 = carBodyPacket.getCan488()[0];
                hasMessageListener(CanIds.getRealCanId(i141), i141, carBodyPacket.getCan488()[1]);
                break;
            case BodyworkHalId.ID_CAN_489_STATE /* 557945733 */:
                int i142 = carBodyPacket.getCan489()[0];
                hasMessageListener(CanIds.getRealCanId(i142), i142, carBodyPacket.getCan489()[1]);
                break;
            case BodyworkHalId.ID_CAN_48A_STATE /* 557945734 */:
                int i143 = carBodyPacket.getCan48a()[0];
                hasMessageListener(CanIds.getRealCanId(i143), i143, carBodyPacket.getCan48a()[1]);
                break;
            case BodyworkHalId.ID_CAN_48E_STATE /* 557945735 */:
                int i144 = carBodyPacket.getCan48e()[0];
                hasMessageListener(CanIds.getRealCanId(i144), i144, carBodyPacket.getCan48e()[1]);
                break;
            case BodyworkHalId.ID_CAN_48F_STATE /* 557945736 */:
                int i145 = carBodyPacket.getCan48f()[0];
                hasMessageListener(CanIds.getRealCanId(i145), i145, carBodyPacket.getCan48f()[1]);
                break;
            case BodyworkHalId.ID_CAN_494_STATE /* 557945737 */:
                int i146 = carBodyPacket.getCan494()[0];
                hasMessageListener(CanIds.getRealCanId(i146), i146, carBodyPacket.getCan494()[1]);
                break;
            case BodyworkHalId.ID_CAN_49C_STATE /* 557945738 */:
                int i147 = carBodyPacket.getCan49c()[0];
                hasMessageListener(CanIds.getRealCanId(i147), i147, carBodyPacket.getCan49c()[1]);
                break;
            case BodyworkHalId.ID_CAN_4A1_STATE /* 557945739 */:
                int i148 = carBodyPacket.getCan4a1()[0];
                hasMessageListener(CanIds.getRealCanId(i148), i148, carBodyPacket.getCan4a1()[1]);
                break;
            case BodyworkHalId.ID_CAN_4A4_STATE /* 557945740 */:
                int i149 = carBodyPacket.getCan4a4()[0];
                hasMessageListener(CanIds.getRealCanId(i149), i149, carBodyPacket.getCan4a4()[1]);
                break;
            case BodyworkHalId.ID_CAN_4A5_STATE /* 557945741 */:
                int i150 = carBodyPacket.getCan4a5()[0];
                hasMessageListener(CanIds.getRealCanId(i150), i150, carBodyPacket.getCan4a5()[1]);
                break;
            case BodyworkHalId.ID_CAN_4A7_STATE /* 557945742 */:
                int i151 = carBodyPacket.getCan4a7()[0];
                hasMessageListener(CanIds.getRealCanId(i151), i151, carBodyPacket.getCan4a7()[1]);
                break;
            case BodyworkHalId.ID_CAN_4B1_STATE /* 557945743 */:
                int i152 = carBodyPacket.getCan4b1()[0];
                hasMessageListener(CanIds.getRealCanId(i152), i152, carBodyPacket.getCan4b1()[1]);
                break;
            case BodyworkHalId.ID_CAN_4B2_STATE /* 557945744 */:
                int i153 = carBodyPacket.getCan4b2()[0];
                hasMessageListener(CanIds.getRealCanId(i153), i153, carBodyPacket.getCan4b2()[1]);
                break;
            case BodyworkHalId.ID_CAN_4B3_STATE /* 557945745 */:
                int i154 = carBodyPacket.getCan4b3()[0];
                hasMessageListener(CanIds.getRealCanId(i154), i154, carBodyPacket.getCan4b3()[1]);
                break;
            case BodyworkHalId.ID_CAN_4B6_STATE /* 557945746 */:
                int i155 = carBodyPacket.getCan4b6()[0];
                hasMessageListener(CanIds.getRealCanId(i155), i155, carBodyPacket.getCan4b6()[1]);
                break;
            case BodyworkHalId.ID_CAN_4BB_STATE /* 557945747 */:
                int i156 = carBodyPacket.getCan4bb()[0];
                hasMessageListener(CanIds.getRealCanId(i156), i156, carBodyPacket.getCan4bb()[1]);
                break;
            case BodyworkHalId.ID_CAN_4BD_STATE /* 557945748 */:
                int i157 = carBodyPacket.getCan4bd()[0];
                hasMessageListener(CanIds.getRealCanId(i157), i157, carBodyPacket.getCan4bd()[1]);
                break;
            case BodyworkHalId.ID_CAN_4BF_STATE /* 557945749 */:
                int i158 = carBodyPacket.getCan4bf()[0];
                hasMessageListener(CanIds.getRealCanId(i158), i158, carBodyPacket.getCan4bf()[1]);
                break;
            case BodyworkHalId.ID_CAN_4C2_STATE /* 557945750 */:
                int i159 = carBodyPacket.getCan4c2()[0];
                hasMessageListener(CanIds.getRealCanId(i159), i159, carBodyPacket.getCan4c2()[1]);
                break;
            case BodyworkHalId.ID_CAN_4C8_STATE /* 557945751 */:
                int i160 = carBodyPacket.getCan4c8()[0];
                hasMessageListener(CanIds.getRealCanId(i160), i160, carBodyPacket.getCan4c8()[1]);
                break;
            case BodyworkHalId.ID_CAN_4D9_STATE /* 557945752 */:
                int i161 = carBodyPacket.getCan4d9()[0];
                hasMessageListener(CanIds.getRealCanId(i161), i161, carBodyPacket.getCan4d9()[1]);
                break;
            case BodyworkHalId.ID_CAN_4E5_STATE /* 557945753 */:
                int i162 = carBodyPacket.getCan4e5()[0];
                hasMessageListener(CanIds.getRealCanId(i162), i162, carBodyPacket.getCan4e5()[1]);
                break;
            case BodyworkHalId.ID_CAN_4E6_STATE /* 557945754 */:
                int i163 = carBodyPacket.getCan4e6()[0];
                hasMessageListener(CanIds.getRealCanId(i163), i163, carBodyPacket.getCan4e6()[1]);
                break;
            case BodyworkHalId.ID_CAN_4F2_STATE /* 557945755 */:
                int i164 = carBodyPacket.getCan4f2()[0];
                hasMessageListener(CanIds.getRealCanId(i164), i164, carBodyPacket.getCan4f2()[1]);
                break;
            case BodyworkHalId.ID_CAN_4F4_STATE /* 557945756 */:
                int i165 = carBodyPacket.getCan4f4()[0];
                hasMessageListener(CanIds.getRealCanId(i165), i165, carBodyPacket.getCan4f4()[1]);
                break;
            case BodyworkHalId.ID_CAN_4F6_STATE /* 557945757 */:
                int i166 = carBodyPacket.getCan4f6()[0];
                hasMessageListener(CanIds.getRealCanId(i166), i166, carBodyPacket.getCan4f6()[1]);
                break;
            case BodyworkHalId.ID_CAN_4F7_STATE /* 557945758 */:
                int i167 = carBodyPacket.getCan4f7()[0];
                hasMessageListener(CanIds.getRealCanId(i167), i167, carBodyPacket.getCan4f7()[1]);
                break;
            case BodyworkHalId.ID_CAN_4FA_STATE /* 557945759 */:
                int i168 = carBodyPacket.getCan4fa()[0];
                hasMessageListener(CanIds.getRealCanId(i168), i168, carBodyPacket.getCan4fa()[1]);
                break;
            case BodyworkHalId.ID_CAN_4FB_STATE /* 557945760 */:
                int i169 = carBodyPacket.getCan4fb()[0];
                hasMessageListener(CanIds.getRealCanId(i169), i169, carBodyPacket.getCan4fb()[1]);
                break;
            case BodyworkHalId.ID_CAN_4FD_STATE /* 557945761 */:
                int i170 = carBodyPacket.getCan4fd()[0];
                hasMessageListener(CanIds.getRealCanId(i170), i170, carBodyPacket.getCan4fd()[1]);
                break;
            case BodyworkHalId.ID_CAN_4FE_STATE /* 557945762 */:
                int i171 = carBodyPacket.getCan4fe()[0];
                hasMessageListener(CanIds.getRealCanId(i171), i171, carBodyPacket.getCan4fe()[1]);
                break;
            case BodyworkHalId.ID_CAN_344_STATE /* 557945763 */:
                int i172 = carBodyPacket.getCan344()[0];
                hasMessageListener(CanIds.getRealCanId(i172), i172, carBodyPacket.getCan344()[1]);
                break;
            case BodyworkHalId.ID_CAN_319_STATE /* 557945764 */:
                int i173 = carBodyPacket.getCan319()[0];
                hasMessageListener(CanIds.getRealCanId(i173), i173, carBodyPacket.getCan319()[1]);
                break;
            case BodyworkHalId.ID_CAN_258_STATE /* 557945765 */:
                int i174 = carBodyPacket.getCan258()[0];
                hasMessageListener(CanIds.getRealCanId(i174), i174, carBodyPacket.getCan258()[1]);
                break;
            case 559954810:
                Iterator<AbsBYDAutoBodyworkListener> it8 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it8.hasNext()) {
                    it8.next().turnSteeringWheelChanged(3, carBodyPacket.getSteeringWheelY(), carBodyPacket.getSteeringWheelZ());
                }
                caseFeatureIds(2100, Integer.valueOf((int) carBodyPacket.getSteeringWheelY()));
                break;
            case 559954811:
                Iterator<AbsBYDAutoBodyworkListener> it9 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it9.hasNext()) {
                    it9.next().turnSteeringWheelChanged(1, carBodyPacket.getSteeringWheelY(), carBodyPacket.getSteeringWheelZ());
                }
                caseFeatureIds(2098, Integer.valueOf((int) carBodyPacket.getSteeringWheelZ()));
                break;
            case 559954812:
                Iterator<AbsBYDAutoBodyworkListener> it10 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it10.hasNext()) {
                    it10.next().onTurnRearviewMirrorChanged(3, carBodyPacket.getRearviewMirrorXLeft(), carBodyPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954813:
                Iterator<AbsBYDAutoBodyworkListener> it11 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it11.hasNext()) {
                    it11.next().onTurnRearviewMirrorChanged(7, carBodyPacket.getRearviewMirrorXRight(), carBodyPacket.getRearviewMirrorYRight());
                }
                break;
            case 559954814:
                Iterator<AbsBYDAutoBodyworkListener> it12 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it12.hasNext()) {
                    it12.next().onTurnRearviewMirrorChanged(1, carBodyPacket.getRearviewMirrorXLeft(), carBodyPacket.getRearviewMirrorYLeft());
                }
                break;
            case 559954815:
                Iterator<AbsBYDAutoBodyworkListener> it13 = this.mAbsBydAutoBodyworkListeners.iterator();
                while (it13.hasNext()) {
                    it13.next().onTurnRearviewMirrorChanged(6, carBodyPacket.getRearviewMirrorXRight(), carBodyPacket.getRearviewMirrorYRight());
                }
                break;
            default:
                switch (i7) {
                    case 557857805:
                        Iterator<AbsBYDAutoBodyworkListener> it14 = this.mAbsBydAutoBodyworkListeners.iterator();
                        while (it14.hasNext()) {
                            it14.next().onRecoverOrSaveParamsChanged(0, 1, 1, carBodyPacket.getonSaveSeatParamsAllChangedPlace5());
                        }
                        caseFeatureIds(1305, Integer.valueOf(carBodyPacket.getonSaveSeatParamsAllChangedPlace5()));
                        break;
                    case 557857806:
                        Iterator<AbsBYDAutoBodyworkListener> it15 = this.mAbsBydAutoBodyworkListeners.iterator();
                        while (it15.hasNext()) {
                            it15.next().onMainSaveOrRecoverStateChanged(1, carBodyPacket.getonSaveSeatParamsAllChangedPlace7());
                        }
                        caseFeatureIds(2134, Integer.valueOf(carBodyPacket.getonSaveSeatParamsAllChangedPlace7()));
                        break;
                    default:
                        switch (i7) {
                            case 557858048:
                                int autoExternalRearMirrorFoldActionStateR = carBodyPacket.getAutoExternalRearMirrorFoldActionStateR();
                                Iterator<AbsBYDAutoBodyworkListener> it16 = this.mAbsBydAutoBodyworkListeners.iterator();
                                while (it16.hasNext()) {
                                    it16.next().onAutoExternalRearMirrorFoldActionStateChanged(autoExternalRearMirrorFoldActionStateR);
                                }
                                caseFeatureIds(2169, Integer.valueOf(autoExternalRearMirrorFoldActionStateR));
                                break;
                            case 557858049:
                                Iterator<AbsBYDAutoBodyworkListener> it17 = this.mAbsBydAutoBodyworkListeners.iterator();
                                while (it17.hasNext()) {
                                    it17.next().onAutoExternalRearMirrorFoldedState(carBodyPacket.getAutoExternalRearMirrorFoldedStateR());
                                }
                                break;
                            default:
                                switch (i7) {
                                    case BodyworkHalId.ID_WINDOW_STATE_LEFT_FRONT /* 557862912 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it18 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it18.hasNext()) {
                                            it18.next().onWindowStateChanged(1, carBodyPacket.getmWindowStateLeftFront());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_STATE_LEFT_REAR /* 557862913 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it19 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it19.hasNext()) {
                                            it19.next().onWindowStateChanged(3, carBodyPacket.getmWindowStateLeftRear());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_STATE_RIGHT_FRONT /* 557862914 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it20 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it20.hasNext()) {
                                            it20.next().onWindowStateChanged(2, carBodyPacket.getmWindowStateRightFront());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_STATE_RIGHT_REAR /* 557862915 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it21 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it21.hasNext()) {
                                            it21.next().onWindowStateChanged(4, carBodyPacket.getmWindowStateRightRear());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_LEFT_FRONT_R /* 557862916 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it22 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it22.hasNext()) {
                                            it22.next().onWindowControlStateChanged(1, carBodyPacket.getWindowControlStateLeftFrontR());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_RIGHT_FRONT_R /* 557862917 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it23 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it23.hasNext()) {
                                            it23.next().onWindowControlStateChanged(2, carBodyPacket.getWindowControlStateRightFrontR());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_LEFT_REAR_R /* 557862918 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it24 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it24.hasNext()) {
                                            it24.next().onWindowControlStateChanged(3, carBodyPacket.getWindowControlStateLeftRearR());
                                        }
                                        break;
                                    case BodyworkHalId.ID_WINDOW_CONTROL_STATE_RIGHT_REAR_R /* 557862919 */:
                                        Iterator<AbsBYDAutoBodyworkListener> it25 = this.mAbsBydAutoBodyworkListeners.iterator();
                                        while (it25.hasNext()) {
                                            it25.next().onWindowControlStateChanged(4, carBodyPacket.getWindowControlStateRightRearR());
                                        }
                                        break;
                                    default:
                                        switch (i7) {
                                            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_MOON_S /* 557862924 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it26 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it26.hasNext()) {
                                                    it26.next().onWindowOpenPercentChanged(5, carBodyPacket.getmWindowOpenPercentMoonS());
                                                }
                                                break;
                                            case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_SUN_S /* 557862925 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it27 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it27.hasNext()) {
                                                    it27.next().onWindowOpenPercentChanged(6, carBodyPacket.getmWindowOpenPercentSunS());
                                                }
                                                break;
                                            case 557862926:
                                                for (AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener : this.mAbsBydAutoBodyworkListeners) {
                                                    if (carBodyPacket.getmBatteryVoltageLevel() == 1) {
                                                        absBYDAutoBodyworkListener.onBatteryVoltageLevelChanged(1);
                                                    } else if (carBodyPacket.getmBatteryVoltageLevel() == 2) {
                                                        absBYDAutoBodyworkListener.onBatteryVoltageLevelChanged(0);
                                                    }
                                                }
                                                break;
                                            case 557862927:
                                                for (AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener2 : this.mAbsBydAutoBodyworkListeners) {
                                                    if (carBodyPacket.getmPowerLevel() == 0) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(255);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 255");
                                                    } else if (carBodyPacket.getmPowerLevel() == 1) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(0);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 0");
                                                    } else if (carBodyPacket.getmPowerLevel() == 2) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(1);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 1");
                                                    } else if (carBodyPacket.getmPowerLevel() == 3) {
                                                        absBYDAutoBodyworkListener2.onPowerLevelChanged(2);
                                                        Log.w(TAG, "TS-Manager -->" + this.mContext.getPackageName() + " value is 2");
                                                    }
                                                }
                                                caseFeatureIds(2002, Integer.valueOf(carBodyPacket.getmPowerLevel()));
                                                break;
                                            case BodyworkHalId.ID_STEERING_WHEEL_VALUE_ANGEL /* 557862928 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it28 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it28.hasNext()) {
                                                    it28.next().onSteeringWheelValueChanged(1, carBodyPacket.getmSteeringWheelValueAngel());
                                                }
                                                break;
                                            case BodyworkHalId.ID_STEERING_WHEEL_VALUE_SPEED /* 557862929 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it29 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it29.hasNext()) {
                                                    it29.next().onSteeringWheelValueChanged(2, carBodyPacket.getmSteeringWheelValueSpeed());
                                                }
                                                break;
                                            case BodyworkHalId.ID_AUTO_TYPE /* 557862930 */:
                                                caseFeatureIds(1682, Integer.valueOf(carBodyPacket.getmAutoType()));
                                                break;
                                            case BodyworkHalId.ID_AUTO_SYSTEM_STATE /* 557862931 */:
                                                Iterator<AbsBYDAutoBodyworkListener> it30 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                while (it30.hasNext()) {
                                                    it30.next().onAutoSystemStateChanged(carBodyPacket.getmAutoSystemState());
                                                }
                                                break;
                                            default:
                                                switch (i7) {
                                                    case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_LEFT_FRONT_R /* 557862936 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it31 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it31.hasNext()) {
                                                            it31.next().onWindowOpenPercentChanged(1, carBodyPacket.getmWindowOpenPercentLeftFrontR());
                                                        }
                                                        caseFeatureIds(2090, Integer.valueOf(carBodyPacket.getmWindowOpenPercentLeftFrontR()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_LEFT_REAR_R /* 557862937 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it32 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it32.hasNext()) {
                                                            it32.next().onWindowOpenPercentChanged(3, carBodyPacket.getmWindowOpenPercentLeftRearR());
                                                        }
                                                        caseFeatureIds(2091, Integer.valueOf(carBodyPacket.getmWindowOpenPercentLeftRearR()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_RIGHT_FRONT_R /* 557862938 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it33 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it33.hasNext()) {
                                                            it33.next().onWindowOpenPercentChanged(2, carBodyPacket.getmWindowOpenPercentRightFrontR());
                                                        }
                                                        caseFeatureIds(2092, Integer.valueOf(carBodyPacket.getmWindowOpenPercentRightFrontR()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_RIGHT_REAR_R /* 557862939 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it34 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it34.hasNext()) {
                                                            it34.next().onWindowOpenPercentChanged(4, carBodyPacket.getmWindowOpenPercentRightRearR());
                                                        }
                                                        caseFeatureIds(2093, Integer.valueOf(carBodyPacket.getmWindowOpenPercentRightRearR()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_MOON_R /* 557862940 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it35 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it35.hasNext()) {
                                                            it35.next().onWindowOpenPercentChanged(5, carBodyPacket.getmWindowOpenPercentMoonR());
                                                        }
                                                        caseFeatureIds(1789, Integer.valueOf(carBodyPacket.getmWindowOpenPercentMoonR()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_OPEN_PERCENT_SUN_R /* 557862941 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it36 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it36.hasNext()) {
                                                            it36.next().onWindowOpenPercentChanged(6, carBodyPacket.getmWindowOpenPercentSunR());
                                                        }
                                                        caseFeatureIds(2084, Integer.valueOf(carBodyPacket.getmWindowOpenPercentSunR()));
                                                        break;
                                                    case BodyworkHalId.ID_MOON_ROOF_CONFIG /* 557862942 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it37 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it37.hasNext()) {
                                                            it37.next().onMoonRoofConfigChanged(carBodyPacket.getmMoonRoofConfig());
                                                        }
                                                        caseFeatureIds(1786, Integer.valueOf(carBodyPacket.getmMoonRoofConfig()));
                                                        break;
                                                    case 557862943:
                                                        int i175 = carBodyPacket.getmDoorStateLeftFront();
                                                        i11 = i175 != 1 ? i175 == 0 ? -1 : i175 == 2 ? 1 : i175 : 0;
                                                        Iterator<AbsBYDAutoBodyworkListener> it38 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it38.hasNext()) {
                                                            it38.next().onDoorStateChanged(1, i11);
                                                        }
                                                        Log.i(TAG, "onDoorStateChanged: id " + i7 + ", doorStateLeftFront " + i11);
                                                        caseFeatureIds(1749, Integer.valueOf(i11));
                                                        break;
                                                    case 557862944:
                                                        int i176 = carBodyPacket.getmDoorStateRightFront();
                                                        i11 = i176 != 1 ? i176 == 0 ? -1 : i176 == 2 ? 1 : i176 : 0;
                                                        Iterator<AbsBYDAutoBodyworkListener> it39 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it39.hasNext()) {
                                                            it39.next().onDoorStateChanged(2, i11);
                                                        }
                                                        Log.i(TAG, "onDoorStateChanged: id " + i7 + ", doorStateRightFront " + i11);
                                                        caseFeatureIds(2040, Integer.valueOf(i11));
                                                        break;
                                                    case 557862945:
                                                        int i177 = carBodyPacket.getmDoorStateLeftRear();
                                                        i11 = i177 != 1 ? i177 == 0 ? -1 : i177 == 2 ? 1 : i177 : 0;
                                                        Iterator<AbsBYDAutoBodyworkListener> it40 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it40.hasNext()) {
                                                            it40.next().onDoorStateChanged(3, i11);
                                                        }
                                                        Log.i(TAG, "onDoorStateChanged: id " + i7 + ", doorStateLeftRear " + i11);
                                                        caseFeatureIds(1750, Integer.valueOf(i11));
                                                        break;
                                                    case 557862946:
                                                        int i178 = carBodyPacket.getmDoorStateRightRear();
                                                        i11 = i178 != 1 ? i178 == 0 ? -1 : i178 == 2 ? 1 : i178 : 0;
                                                        Iterator<AbsBYDAutoBodyworkListener> it41 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it41.hasNext()) {
                                                            it41.next().onDoorStateChanged(4, i11);
                                                        }
                                                        Log.i(TAG, "onDoorStateChanged: id " + i7 + ", doorStateRightRear " + i11);
                                                        caseFeatureIds(2041, Integer.valueOf(i11));
                                                        break;
                                                    case 557862947:
                                                        int i179 = carBodyPacket.getmDoorStateLuggage();
                                                        i11 = i179 != 1 ? i179 == 0 ? -1 : i179 == 2 ? 1 : i179 : 0;
                                                        Iterator<AbsBYDAutoBodyworkListener> it42 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it42.hasNext()) {
                                                            it42.next().onDoorStateChanged(6, i11);
                                                        }
                                                        caseFeatureIds(1782, Integer.valueOf(i11));
                                                        break;
                                                    case 557862948:
                                                        int i180 = carBodyPacket.getmDoorStateHood();
                                                        i11 = i180 != 1 ? i180 == 0 ? -1 : i180 == 2 ? 1 : i180 : 0;
                                                        Iterator<AbsBYDAutoBodyworkListener> it43 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it43.hasNext()) {
                                                            it43.next().onDoorStateChanged(5, i11);
                                                        }
                                                        caseFeatureIds(1742, Integer.valueOf(i11));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_STATE_MOON_ROOFR /* 557862949 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it44 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it44.hasNext()) {
                                                            it44.next().onWindowStateChanged(5, carBodyPacket.getmWindowStateMoonRoofR());
                                                        }
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_STATE_SUNSHADER /* 557862950 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it45 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it45.hasNext()) {
                                                            it45.next().onWindowblindStateChanged(carBodyPacket.getmWindowStateSunshadeR());
                                                        }
                                                        caseFeatureIds(2082, Integer.valueOf(carBodyPacket.getmWindowStateSunshadeR()));
                                                        break;
                                                    case BodyworkHalId.ID_ENERGY_TYPER /* 557862951 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it46 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it46.hasNext()) {
                                                            it46.next().onEnergyTypeChanged(carBodyPacket.getmEnergyTypeR());
                                                        }
                                                        break;
                                                    case BodyworkHalId.ID_SUN_ROOF_STATER /* 557862952 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it47 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it47.hasNext()) {
                                                            it47.next().onSunroofStateChanged(carBodyPacket.getmSunRoofStateR());
                                                        }
                                                        caseFeatureIds(2079, Integer.valueOf(carBodyPacket.getmSunRoofStateR()));
                                                        break;
                                                    case 557862953:
                                                        Iterator<AbsBYDAutoBodyworkListener> it48 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it48.hasNext()) {
                                                            it48.next().onLeftStrutMorotRunningStateChanged(carBodyPacket.getLeftStrutMorotRunningState());
                                                        }
                                                        caseFeatureIds(1759, Integer.valueOf(carBodyPacket.getLeftStrutMorotRunningState()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_INIT_STATE_FL /* 557862954 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it49 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it49.hasNext()) {
                                                            it49.next().onWindowInitStateChanged(1, carBodyPacket.getWindowInitStateFL());
                                                        }
                                                        caseFeatureIds(1765, Integer.valueOf(carBodyPacket.getWindowInitStateFL()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_INIT_STATE_RL /* 557862955 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it50 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it50.hasNext()) {
                                                            it50.next().onWindowInitStateChanged(3, carBodyPacket.getWindowInitStateRL());
                                                        }
                                                        caseFeatureIds(2036, Integer.valueOf(carBodyPacket.getWindowInitStateRL()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_INIT_STATE_FR /* 557862956 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it51 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it51.hasNext()) {
                                                            it51.next().onWindowInitStateChanged(2, carBodyPacket.getWindowInitStateFR());
                                                        }
                                                        caseFeatureIds(1780, Integer.valueOf(carBodyPacket.getWindowInitStateFR()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_INIT_STATE_RR /* 557862957 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it52 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it52.hasNext()) {
                                                            it52.next().onWindowInitStateChanged(4, carBodyPacket.getWindowInitStateRR());
                                                        }
                                                        caseFeatureIds(2062, Integer.valueOf(carBodyPacket.getWindowInitStateRR()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_RL /* 557862958 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it53 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it53.hasNext()) {
                                                            it53.next().onWindowThermalProtectionStateChanged(1, carBodyPacket.getWindowThermalProtectionStateFl());
                                                        }
                                                        caseFeatureIds(1748, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateFl()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_FL /* 557862959 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it54 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it54.hasNext()) {
                                                            it54.next().onWindowThermalProtectionStateChanged(3, carBodyPacket.getWindowThermalProtectionStateRl());
                                                        }
                                                        caseFeatureIds(1752, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateRl()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_FR /* 557862960 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it55 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it55.hasNext()) {
                                                            it55.next().onWindowThermalProtectionStateChanged(2, carBodyPacket.getWindowThermalProtectionStateFr());
                                                        }
                                                        caseFeatureIds(2039, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateFr()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_RR /* 557862961 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it56 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it56.hasNext()) {
                                                            it56.next().onWindowThermalProtectionStateChanged(4, carBodyPacket.getWindowThermalProtectionStateRr());
                                                        }
                                                        caseFeatureIds(2043, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateRr()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_SKYLIGHT /* 557862962 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it57 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it57.hasNext()) {
                                                            it57.next().onWindowThermalProtectionStateChanged(5, carBodyPacket.getWindowThermalProtectionStateSkylight());
                                                        }
                                                        caseFeatureIds(2068, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateSkylight()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_FRONT_SUNSHADE /* 557862963 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it58 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it58.hasNext()) {
                                                            it58.next().onWindowThermalProtectionStateChanged(6, carBodyPacket.getWindowThermalProtectionStateFrontSunshade());
                                                        }
                                                        caseFeatureIds(1735, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateFrontSunshade()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_THERMAL_PROTECTION_STATE_REAR_SUNSHADE /* 557862964 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it59 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it59.hasNext()) {
                                                            it59.next().onWindowThermalProtectionStateChanged(7, carBodyPacket.getWindowThermalProtectionStateRearSunshade());
                                                        }
                                                        caseFeatureIds(2014, Integer.valueOf(carBodyPacket.getWindowThermalProtectionStateRearSunshade()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOW_PERMIT_STATER /* 557862965 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it60 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it60.hasNext()) {
                                                            it60.next().onWindowPermitStateChanged(carBodyPacket.getWindowPermitState());
                                                        }
                                                        caseFeatureIds(1747, Integer.valueOf(carBodyPacket.getWindowPermitState()));
                                                        break;
                                                    case BodyworkHalId.ID_WINDOWBLIND_INIT_STATER /* 557862966 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it61 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it61.hasNext()) {
                                                            it61.next().onWindoblindInitStateChanged(carBodyPacket.getWindowblindInitStateR());
                                                        }
                                                        caseFeatureIds(2080, Integer.valueOf(carBodyPacket.getWindowblindInitStateR()));
                                                        break;
                                                    case BodyworkHalId.ID_SUNROOF_INIT_STATER /* 557862967 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it62 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it62.hasNext()) {
                                                            it62.next().onSunroofInitStateChanged(carBodyPacket.getSunroofInitStateR());
                                                        }
                                                        caseFeatureIds(2077, Integer.valueOf(carBodyPacket.getSunroofInitStateR()));
                                                        break;
                                                    case BodyworkHalId.ID_SUNROOF_CLOSE_NOTICER /* 557862968 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it63 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it63.hasNext()) {
                                                            it63.next().onSunroofCloseNoticeChanged(carBodyPacket.getSunroofCloseNoticeR());
                                                        }
                                                        caseFeatureIds(2076, Integer.valueOf(carBodyPacket.getSunroofCloseNoticeR()));
                                                        break;
                                                    case BodyworkHalId.ID_SUNROOF_WINDOWBLIND_POSITIONR /* 557862969 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it64 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it64.hasNext()) {
                                                            it64.next().onSunroofWindowblindPositionChanged(carBodyPacket.getSunroofWindowblindPositionR());
                                                        }
                                                        caseFeatureIds(2081, Integer.valueOf(carBodyPacket.getSunroofWindowblindPositionR()));
                                                        break;
                                                    case BodyworkHalId.ID_SUNROOF_POSITIONR /* 557862970 */:
                                                        Iterator<AbsBYDAutoBodyworkListener> it65 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                        while (it65.hasNext()) {
                                                            it65.next().onSunroofPositionChanged(carBodyPacket.getSunroofPositionR());
                                                        }
                                                        caseFeatureIds(2078, Integer.valueOf(carBodyPacket.getSunroofPositionR()));
                                                        break;
                                                    default:
                                                        switch (i7) {
                                                            case BodyworkHalId.ID_REAR_WINDOWBLIND_STATER /* 557862972 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it66 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it66.hasNext()) {
                                                                    it66.next().onRearWindowblindStateChanged(carBodyPacket.getRearWindowblindStateR());
                                                                }
                                                                caseFeatureIds(2007, Integer.valueOf(carBodyPacket.getRearWindowblindStateR()));
                                                                break;
                                                            case BodyworkHalId.ID_REAR_WINDOWBLIND_INIT_STATER /* 557862973 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it67 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it67.hasNext()) {
                                                                    it67.next().onRearWindowblindInitStateChanged(carBodyPacket.getRearWindowblindInitStateR());
                                                                }
                                                                caseFeatureIds(2009, Integer.valueOf(carBodyPacket.getRearWindowblindInitStateR()));
                                                                break;
                                                            case BodyworkHalId.ID_REAR_WINDOWBLIND_POSITIONR /* 557862974 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it68 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it68.hasNext()) {
                                                                    it68.next().onRearWindowblindPositionChanged(carBodyPacket.getRearWindowblindPositionR());
                                                                }
                                                                caseFeatureIds(2008, Integer.valueOf(carBodyPacket.getRearWindowblindPositionR()));
                                                                break;
                                                            case BodyworkHalId.ID_BACK_DOOR_INIT_STATER /* 557862975 */:
                                                                Iterator<AbsBYDAutoBodyworkListener> it69 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                while (it69.hasNext()) {
                                                                    it69.next().onBackDoorInitStateChanged(carBodyPacket.getBackDoorInitStateR());
                                                                }
                                                                caseFeatureIds(1686, Integer.valueOf(carBodyPacket.getBackDoorInitStateR()));
                                                                break;
                                                            default:
                                                                switch (i7) {
                                                                    case BodyworkHalId.ID_BACK_DOOR_CUR_STATER /* 557862978 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it70 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it70.hasNext()) {
                                                                            it70.next().onBackDoorCurStateChanged(carBodyPacket.getBackDoorCurStateR());
                                                                        }
                                                                        caseFeatureIds(1685, Integer.valueOf(carBodyPacket.getBackDoorCurStateR()));
                                                                        break;
                                                                    case BodyworkHalId.ID_RIGHT_STRUT_MOROT_RUNNING_STATER /* 557862979 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it71 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it71.hasNext()) {
                                                                            it71.next().onRightStrutMorotRunningStateChanged(carBodyPacket.getRightStrutMorotRunningStateR());
                                                                        }
                                                                        caseFeatureIds(2049, Integer.valueOf(carBodyPacket.getRightStrutMorotRunningStateR()));
                                                                        break;
                                                                    case BodyworkHalId.ID_BACK_DOOR_POSITIONR /* 557862980 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it72 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it72.hasNext()) {
                                                                            it72.next().onBackDoorPositionChanged(carBodyPacket.getBackDoorPositionR());
                                                                        }
                                                                        caseFeatureIds(1687, Integer.valueOf(carBodyPacket.getBackDoorPositionR()));
                                                                        break;
                                                                    case BodyworkHalId.ID_BACK_DOOR_ANTI_PLAY_MODER /* 557862981 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it73 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it73.hasNext()) {
                                                                            it73.next().onBackDoorAntiPlayModeChanged(carBodyPacket.getBackDoorAntiPlayModeR());
                                                                        }
                                                                        caseFeatureIds(1689, Integer.valueOf(carBodyPacket.getElecSlideDoorStateR()));
                                                                        break;
                                                                    case BodyworkHalId.ID_ELEC_SLIDE_DOOR_STATER /* 557862982 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it74 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it74.hasNext()) {
                                                                            it74.next().onElecSlideDoorStateChanged(carBodyPacket.getElecSlideDoorStateR());
                                                                        }
                                                                        caseFeatureIds(1717, Integer.valueOf(carBodyPacket.getElecSlideDoorStateR()));
                                                                        break;
                                                                    case BodyworkHalId.ID_LEFT_SLIDE_DOOR_STATER /* 557862983 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it75 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it75.hasNext()) {
                                                                            it75.next().onLeftSlideDoorStateChanged(carBodyPacket.getLeftSlideDoorStateR());
                                                                        }
                                                                        caseFeatureIds(1757, Integer.valueOf(carBodyPacket.getLeftSlideDoorStateR()));
                                                                        break;
                                                                    case BodyworkHalId.ID_RIGHT_SLIDE_DOOR_STATER /* 557862984 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it76 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it76.hasNext()) {
                                                                            it76.next().onRightSlideDoorStateChanged(carBodyPacket.getRightSlideDoorStateR());
                                                                        }
                                                                        caseFeatureIds(2047, Integer.valueOf(carBodyPacket.getRightSlideDoorStateR()));
                                                                        break;
                                                                    case BodyworkHalId.ID_LEFT_SLIDE_DOOR_ACTIONR /* 557862985 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it77 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it77.hasNext()) {
                                                                            it77.next().onLeftSlideDoorActionChanged(carBodyPacket.getLeftSlideDoorActionR());
                                                                        }
                                                                        caseFeatureIds(1753, Integer.valueOf(carBodyPacket.getLeftSlideDoorActionR()));
                                                                        break;
                                                                    case BodyworkHalId.ID_RIGHT_SLIDE_DOOR_ACTIONR /* 557862986 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it78 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it78.hasNext()) {
                                                                            it78.next().onRightSlideDoorActionChanged(carBodyPacket.getRightSlideDoorActionR());
                                                                        }
                                                                        caseFeatureIds(2044, Integer.valueOf(carBodyPacket.getRightSlideDoorActionR()));
                                                                        break;
                                                                    case BodyworkHalId.ID_LEFT_SLIDE_DOOR_PERCENTR /* 557862987 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it79 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it79.hasNext()) {
                                                                            it79.next().onLeftSlideDoorPercentChanged(carBodyPacket.getLeftSlideDoorPercentR());
                                                                        }
                                                                        caseFeatureIds(1756, Integer.valueOf(carBodyPacket.getLeftSlideDoorPercentR()));
                                                                        break;
                                                                    case BodyworkHalId.ID_RIGHT_SLIDE_DOOR_PERCENTR /* 557862988 */:
                                                                        Iterator<AbsBYDAutoBodyworkListener> it80 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                        while (it80.hasNext()) {
                                                                            it80.next().onRightSlideDoorPercentChanged(carBodyPacket.getRightSlideDoorPercentR());
                                                                        }
                                                                        caseFeatureIds(2046, Integer.valueOf(carBodyPacket.getRightSlideDoorPercentR()));
                                                                        break;
                                                                    default:
                                                                        switch (i7) {
                                                                            case BodyworkHalId.ID_SMART_VOICE_LIMIT_R /* 557863016 */:
                                                                                Iterator<AbsBYDAutoBodyworkListener> it81 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                while (it81.hasNext()) {
                                                                                    it81.next().onSmartVoiceLimitChanged(carBodyPacket.getSmartVoiceLimit());
                                                                                }
                                                                                break;
                                                                            case BodyworkHalId.ID_B_C_M_VECHILE_STATUS_R /* 557863017 */:
                                                                                Iterator<AbsBYDAutoBodyworkListener> it82 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                while (it82.hasNext()) {
                                                                                    it82.next().onBCMVechileStatusChanged(carBodyPacket.getBcmVechileStatus());
                                                                                }
                                                                                break;
                                                                            default:
                                                                                switch (i7) {
                                                                                    case BodyworkHalId.ID_DOOR_LOCK_AREA_RIGHT_FRONT /* 557863022 */:
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it83 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it83.hasNext()) {
                                                                                            it83.next().onDoorLockStatusChanged(3, carBodyPacket.getDoorLockStatusRightFront());
                                                                                        }
                                                                                        caseFeatureIds(2025, Integer.valueOf(carBodyPacket.getDoorLockStatusRightFront()));
                                                                                        break;
                                                                                    case BodyworkHalId.ID_DOOR_LOCK_AREA_LEFT_REAR /* 557863023 */:
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it84 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it84.hasNext()) {
                                                                                            it84.next().onDoorLockStatusChanged(2, carBodyPacket.getDoorLockStatusLeftRear());
                                                                                        }
                                                                                        caseFeatureIds(1768, Integer.valueOf(carBodyPacket.getDoorLockStatusLeftRear()));
                                                                                        break;
                                                                                    case BodyworkHalId.ID_DOOR_LOCK_AREA_RIGHT_REAR /* 557863024 */:
                                                                                        Iterator<AbsBYDAutoBodyworkListener> it85 = this.mAbsBydAutoBodyworkListeners.iterator();
                                                                                        while (it85.hasNext()) {
                                                                                            it85.next().onDoorLockStatusChanged(4, carBodyPacket.getDoorLockStatusAreaRightRear());
                                                                                        }
                                                                                        caseFeatureIds(2050, Integer.valueOf(carBodyPacket.getDoorLockStatusAreaRightRear()));
                                                                                        break;
                                                                                }
                                                                                break;
                                                                        }
                                                                        break;
                                                                }
                                                                break;
                                                        }
                                                        break;
                                                }
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    private boolean createWindowStateData(int i7, int i10, int[] iArr) {
        if (i7 == 1763) {
            iArr[0] = i10;
        } else if (i7 == 1778) {
            iArr[2] = i10;
        } else if (i7 == 2034) {
            iArr[1] = i10;
        } else {
            if (i7 != 2060) {
                Log.v(TAG, "no have control window feature_id.");
                return false;
            }
            iArr[3] = i10;
        }
        return true;
    }

    private void enforcePermission(String str) {
        if (!this.mIgnoreBydPermission && this.mContext.checkCallingOrSelfPermission(str) != 0) {
            throw new SecurityException(b.g("requires permission ", str));
        }
    }

    public static synchronized BodyworkManagerImpl getInstance(Context context) {
        try {
            if (sInstance == null) {
                synchronized (BodyworkManagerImpl.class) {
                    try {
                        if (sInstance == null) {
                            sInstance = new BodyworkManagerImpl(context);
                        }
                    } finally {
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
        return sInstance;
    }

    private void hasMessageListener(int i7, int i10, int i11) {
        Log.v(TAG, "hasMessageListener realId is " + i7 + ",hasMessageId" + i10);
        int i12 = i11 == 0 ? 1 : 0;
        for (AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener : this.mAbsBydAutoBodyworkListeners) {
            if (i10 != 315) {
                if (i10 == 985) {
                    absBYDAutoBodyworkListener.onMessage5sOnlineStateChanged(i7, i12);
                    absBYDAutoBodyworkListener.onHasMessageChanged(i7, i12);
                } else if (i10 != 1028 && i10 != 1054 && i10 != 1092 && i10 != 1268 && i10 != 1270) {
                    absBYDAutoBodyworkListener.onHasMessageChanged(i7, i12);
                }
            }
            absBYDAutoBodyworkListener.onMessage5sOnlineStateChanged(i7, i12);
        }
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i7 : iArr) {
                if (i7 > 0) {
                    switch (i7) {
                        case 1681:
                            int antiTheftStatus = getAntiTheftStatus();
                            bYDAutoEventValue.intValue = antiTheftStatus;
                            sparseArray.put(1681, Integer.valueOf(antiTheftStatus));
                            break;
                        case 1682:
                            int autoType = getAutoType();
                            bYDAutoEventValue.intValue = autoType;
                            sparseArray.put(1682, Integer.valueOf(autoType));
                            break;
                        case 1685:
                            int backDoorCurState = getBackDoorCurState();
                            bYDAutoEventValue.intValue = backDoorCurState;
                            sparseArray.put(1685, Integer.valueOf(backDoorCurState));
                            break;
                        case 1686:
                            int backDoorInitState = getBackDoorInitState();
                            bYDAutoEventValue.intValue = backDoorInitState;
                            sparseArray.put(1686, Integer.valueOf(backDoorInitState));
                            break;
                        case 1687:
                            int backDoorPosition = getBackDoorPosition();
                            bYDAutoEventValue.intValue = backDoorPosition;
                            sparseArray.put(1687, Integer.valueOf(backDoorPosition));
                            break;
                        case 1689:
                            int backDoorAntiPlayMode = getBackDoorAntiPlayMode();
                            bYDAutoEventValue.intValue = backDoorAntiPlayMode;
                            sparseArray.put(1689, Integer.valueOf(backDoorAntiPlayMode));
                            break;
                        case 1717:
                            int elecSlideDoorState = getElecSlideDoorState();
                            bYDAutoEventValue.intValue = elecSlideDoorState;
                            sparseArray.put(1717, Integer.valueOf(elecSlideDoorState));
                            break;
                        case 1731:
                            int doorStateHood = this.mTsManagerImpl.getCarBodyManager().getDoorStateHood();
                            bYDAutoEventValue.intValue = doorStateHood;
                            sparseArray.put(1731, Integer.valueOf(doorStateHood));
                            break;
                        case 1732:
                            int electricFrontHatchConfiguration = this.mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
                            bYDAutoEventValue.intValue = electricFrontHatchConfiguration;
                            sparseArray.put(1732, Integer.valueOf(electricFrontHatchConfiguration));
                            break;
                        case 1735:
                            int windowThermalProtectionState = getWindowThermalProtectionState(6);
                            bYDAutoEventValue.intValue = windowThermalProtectionState;
                            sparseArray.put(1735, Integer.valueOf(windowThermalProtectionState));
                            break;
                        case 1742:
                            int doorState = getDoorState(5);
                            bYDAutoEventValue.intValue = doorState;
                            sparseArray.put(1742, Integer.valueOf(doorState));
                            break;
                        case 1746:
                            int windowState = getWindowState(1);
                            bYDAutoEventValue.intValue = windowState;
                            sparseArray.put(1746, Integer.valueOf(windowState));
                            break;
                        case 1747:
                            int windowPermitState = this.mTsManagerImpl.getCarBodyManager().getWindowPermitState();
                            bYDAutoEventValue.intValue = windowPermitState;
                            sparseArray.put(1747, Integer.valueOf(windowPermitState));
                            break;
                        case 1748:
                            int windowThermalProtectionStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftFront();
                            bYDAutoEventValue.intValue = windowThermalProtectionStateLeftFront;
                            sparseArray.put(1748, Integer.valueOf(windowThermalProtectionStateLeftFront));
                            break;
                        case 1749:
                            int doorState2 = getDoorState(1);
                            bYDAutoEventValue.intValue = doorState2;
                            sparseArray.put(1749, Integer.valueOf(doorState2));
                            break;
                        case 1750:
                            int doorState3 = getDoorState(3);
                            bYDAutoEventValue.intValue = doorState3;
                            sparseArray.put(1750, Integer.valueOf(doorState3));
                            break;
                        case 1751:
                            int windowState2 = getWindowState(3);
                            bYDAutoEventValue.intValue = windowState2;
                            sparseArray.put(1751, Integer.valueOf(windowState2));
                            break;
                        case 1752:
                            int windowThermalProtectionStateLeftRear = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftRear();
                            bYDAutoEventValue.intValue = windowThermalProtectionStateLeftRear;
                            sparseArray.put(1752, Integer.valueOf(windowThermalProtectionStateLeftRear));
                            break;
                        case 1753:
                            int leftSlideDoorAction = getLeftSlideDoorAction();
                            bYDAutoEventValue.intValue = leftSlideDoorAction;
                            sparseArray.put(1753, Integer.valueOf(leftSlideDoorAction));
                            break;
                        case 1756:
                            int leftSlideDoorPercent = getLeftSlideDoorPercent();
                            bYDAutoEventValue.intValue = leftSlideDoorPercent;
                            sparseArray.put(1756, Integer.valueOf(leftSlideDoorPercent));
                            break;
                        case 1757:
                            int leftSlideDoorState = getLeftSlideDoorState();
                            bYDAutoEventValue.intValue = leftSlideDoorState;
                            sparseArray.put(1757, Integer.valueOf(leftSlideDoorState));
                            break;
                        case 1759:
                            int leftStrutMorotRunningState = getLeftStrutMorotRunningState();
                            bYDAutoEventValue.intValue = leftStrutMorotRunningState;
                            sparseArray.put(1759, Integer.valueOf(leftStrutMorotRunningState));
                            break;
                        case 1763:
                            int windowControlState = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(1);
                            bYDAutoEventValue.intValue = windowControlState;
                            sparseArray.put(1763, Integer.valueOf(windowControlState));
                            break;
                        case 1765:
                            int windowInitStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
                            bYDAutoEventValue.intValue = windowInitStateLeftFront;
                            sparseArray.put(1765, Integer.valueOf(windowInitStateLeftFront));
                            break;
                        case 1766:
                            int windowOpenPercent = getWindowOpenPercent(1);
                            bYDAutoEventValue.intValue = windowOpenPercent;
                            sparseArray.put(1766, Integer.valueOf(windowOpenPercent));
                            break;
                        case 1768:
                            int doorLockStatus = getDoorLockStatus(2);
                            bYDAutoEventValue.intValue = doorLockStatus;
                            sparseArray.put(1768, Integer.valueOf(doorLockStatus));
                            break;
                        case 1778:
                            int windowControlState2 = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(3);
                            bYDAutoEventValue.intValue = windowControlState2;
                            sparseArray.put(1778, Integer.valueOf(windowControlState2));
                            break;
                        case 1780:
                            int windowInitStateLeftRear = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
                            bYDAutoEventValue.intValue = windowInitStateLeftRear;
                            sparseArray.put(1780, Integer.valueOf(windowInitStateLeftRear));
                            break;
                        case 1781:
                            int windowOpenPercent2 = getWindowOpenPercent(3);
                            bYDAutoEventValue.intValue = windowOpenPercent2;
                            sparseArray.put(1781, Integer.valueOf(windowOpenPercent2));
                            break;
                        case 1782:
                            int doorStateLuggage = this.mTsManagerImpl.getCarBodyManager().getDoorStateLuggage();
                            bYDAutoEventValue.intValue = doorStateLuggage;
                            sparseArray.put(1782, Integer.valueOf(doorStateLuggage));
                            break;
                        case 1785:
                            int windowState3 = getWindowState(5);
                            bYDAutoEventValue.intValue = windowState3;
                            sparseArray.put(1785, Integer.valueOf(windowState3));
                            break;
                        case 1786:
                            int moonRoofConfig = getMoonRoofConfig();
                            bYDAutoEventValue.intValue = moonRoofConfig;
                            sparseArray.put(1786, Integer.valueOf(moonRoofConfig));
                            break;
                        case 1788:
                            int moonRoofConfig2 = getMoonRoofConfig();
                            bYDAutoEventValue.intValue = moonRoofConfig2;
                            sparseArray.put(1788, Integer.valueOf(moonRoofConfig2));
                            break;
                        case 1789:
                            int windowOpenPercent3 = getWindowOpenPercent(5);
                            bYDAutoEventValue.intValue = windowOpenPercent3;
                            sparseArray.put(1789, Integer.valueOf(windowOpenPercent3));
                            break;
                        case 1896:
                            int readyIndicatorLightState = getReadyIndicatorLightState();
                            bYDAutoEventValue.intValue = readyIndicatorLightState;
                            sparseArray.put(1896, Integer.valueOf(readyIndicatorLightState));
                            break;
                        case 2002:
                            int powerLevel = this.mTsManagerImpl.getCarBodyManager().getPowerLevel();
                            bYDAutoEventValue.intValue = powerLevel;
                            sparseArray.put(2002, Integer.valueOf(powerLevel));
                            break;
                        case 2005:
                            int rearviewMirrorSupport = getRearviewMirrorSupport();
                            bYDAutoEventValue.intValue = rearviewMirrorSupport;
                            sparseArray.put(2005, Integer.valueOf(rearviewMirrorSupport));
                            break;
                        case 2006:
                            int rearLampLightState = getRearLampLightState();
                            bYDAutoEventValue.intValue = rearLampLightState;
                            sparseArray.put(2006, Integer.valueOf(rearLampLightState));
                            break;
                        case 2007:
                            int rearWindowblindState = getRearWindowblindState();
                            bYDAutoEventValue.intValue = rearWindowblindState;
                            sparseArray.put(2007, Integer.valueOf(rearWindowblindState));
                            break;
                        case 2008:
                            int rearWindowblindPosition = getRearWindowblindPosition();
                            bYDAutoEventValue.intValue = rearWindowblindPosition;
                            sparseArray.put(2008, Integer.valueOf(rearWindowblindPosition));
                            break;
                        case 2009:
                            int rearWindowblindInitState = getRearWindowblindInitState();
                            bYDAutoEventValue.intValue = rearWindowblindInitState;
                            sparseArray.put(2009, Integer.valueOf(rearWindowblindInitState));
                            break;
                        case 2012:
                            int windowOpenPercent4 = getWindowOpenPercent(7);
                            bYDAutoEventValue.intValue = windowOpenPercent4;
                            sparseArray.put(2012, Integer.valueOf(windowOpenPercent4));
                            break;
                        case 2014:
                            int windowThermalProtectionState2 = getWindowThermalProtectionState(7);
                            bYDAutoEventValue.intValue = windowThermalProtectionState2;
                            sparseArray.put(2014, Integer.valueOf(windowThermalProtectionState2));
                            break;
                        case 2025:
                            int doorLockStatus2 = getDoorLockStatus(3);
                            bYDAutoEventValue.intValue = doorLockStatus2;
                            sparseArray.put(2025, Integer.valueOf(doorLockStatus2));
                            break;
                        case 2034:
                            int windowControlState3 = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(2);
                            bYDAutoEventValue.intValue = windowControlState3;
                            sparseArray.put(2034, Integer.valueOf(windowControlState3));
                            break;
                        case 2036:
                            int windowInitStateRightFront = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
                            bYDAutoEventValue.intValue = windowInitStateRightFront;
                            sparseArray.put(2036, Integer.valueOf(windowInitStateRightFront));
                            break;
                        case 2037:
                            int windowOpenPercent5 = getWindowOpenPercent(2);
                            bYDAutoEventValue.intValue = windowOpenPercent5;
                            sparseArray.put(2037, Integer.valueOf(windowOpenPercent5));
                            break;
                        case 2039:
                            int windowThermalProtectionStateRightFront = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightFront();
                            bYDAutoEventValue.intValue = windowThermalProtectionStateRightFront;
                            sparseArray.put(2039, Integer.valueOf(windowThermalProtectionStateRightFront));
                            break;
                        case 2040:
                            int doorState4 = getDoorState(2);
                            bYDAutoEventValue.intValue = doorState4;
                            sparseArray.put(2040, Integer.valueOf(doorState4));
                            break;
                        case 2041:
                            int doorState5 = getDoorState(4);
                            bYDAutoEventValue.intValue = doorState5;
                            sparseArray.put(2041, Integer.valueOf(doorState5));
                            break;
                        case 2043:
                            int windowThermalProtectionStateRightRear = this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightRear();
                            bYDAutoEventValue.intValue = windowThermalProtectionStateRightRear;
                            sparseArray.put(2043, Integer.valueOf(windowThermalProtectionStateRightRear));
                            break;
                        case 2044:
                            int rightSlideDoorAction = getRightSlideDoorAction();
                            bYDAutoEventValue.intValue = rightSlideDoorAction;
                            sparseArray.put(2044, Integer.valueOf(rightSlideDoorAction));
                            break;
                        case 2046:
                            int rightSlideDoorPercent = getRightSlideDoorPercent();
                            bYDAutoEventValue.intValue = rightSlideDoorPercent;
                            sparseArray.put(2046, Integer.valueOf(rightSlideDoorPercent));
                            break;
                        case 2047:
                            int rightSlideDoorState = getRightSlideDoorState();
                            bYDAutoEventValue.intValue = rightSlideDoorState;
                            sparseArray.put(2047, Integer.valueOf(rightSlideDoorState));
                            break;
                        case 2049:
                            int rightStrutMorotRunningState = getRightStrutMorotRunningState();
                            bYDAutoEventValue.intValue = rightStrutMorotRunningState;
                            sparseArray.put(2049, Integer.valueOf(rightStrutMorotRunningState));
                            break;
                        case 2050:
                            int doorLockStatus3 = getDoorLockStatus(4);
                            bYDAutoEventValue.intValue = doorLockStatus3;
                            sparseArray.put(2050, Integer.valueOf(doorLockStatus3));
                            break;
                        case 2060:
                            int windowControlState4 = this.mTsManagerImpl.getCarBodyManager().getWindowControlState(4);
                            bYDAutoEventValue.intValue = windowControlState4;
                            sparseArray.put(2060, Integer.valueOf(windowControlState4));
                            break;
                        case 2062:
                            int windowInitStateRightRear = this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
                            bYDAutoEventValue.intValue = windowInitStateRightRear;
                            sparseArray.put(2062, Integer.valueOf(windowInitStateRightRear));
                            break;
                        case 2063:
                            int windowOpenPercent6 = getWindowOpenPercent(4);
                            bYDAutoEventValue.intValue = windowOpenPercent6;
                            sparseArray.put(2063, Integer.valueOf(windowOpenPercent6));
                            break;
                        case 2068:
                            int windowThermalProtectionState3 = getWindowThermalProtectionState(5);
                            bYDAutoEventValue.intValue = windowThermalProtectionState3;
                            sparseArray.put(2068, Integer.valueOf(windowThermalProtectionState3));
                            break;
                        case 2072:
                            double steeringWheelValue = getSteeringWheelValue(1);
                            bYDAutoEventValue.doubleValue = steeringWheelValue;
                            sparseArray.put(2072, Double.valueOf(steeringWheelValue));
                            break;
                        case 2075:
                            double steeringWheelValue2 = getSteeringWheelValue(2);
                            bYDAutoEventValue.doubleValue = steeringWheelValue2;
                            sparseArray.put(2075, Double.valueOf(steeringWheelValue2));
                            break;
                        case 2076:
                            int sunroofCloseNotice = getSunroofCloseNotice();
                            bYDAutoEventValue.intValue = sunroofCloseNotice;
                            sparseArray.put(2076, Integer.valueOf(sunroofCloseNotice));
                            break;
                        case 2077:
                            int sunroofInitState = getSunroofInitState();
                            bYDAutoEventValue.intValue = sunroofInitState;
                            sparseArray.put(2077, Integer.valueOf(sunroofInitState));
                            break;
                        case 2078:
                            int sunroofPosition = getSunroofPosition();
                            bYDAutoEventValue.intValue = sunroofPosition;
                            sparseArray.put(2078, Integer.valueOf(sunroofPosition));
                            break;
                        case 2079:
                            int sunroofState = getSunroofState();
                            bYDAutoEventValue.intValue = sunroofState;
                            sparseArray.put(2079, Integer.valueOf(sunroofState));
                            break;
                        case 2080:
                            int windowblindInitState = getWindowblindInitState();
                            bYDAutoEventValue.intValue = windowblindInitState;
                            sparseArray.put(2080, Integer.valueOf(windowblindInitState));
                            break;
                        case 2081:
                            int sunroofWindowblindPosition = getSunroofWindowblindPosition();
                            bYDAutoEventValue.intValue = sunroofWindowblindPosition;
                            sparseArray.put(2081, Integer.valueOf(sunroofWindowblindPosition));
                            break;
                        case 2082:
                            int windowblindState = getWindowblindState();
                            bYDAutoEventValue.intValue = windowblindState;
                            sparseArray.put(2082, Integer.valueOf(windowblindState));
                            break;
                        case 2084:
                            int windowOpenPercent7 = getWindowOpenPercent(6);
                            bYDAutoEventValue.intValue = windowOpenPercent7;
                            sparseArray.put(2084, Integer.valueOf(windowOpenPercent7));
                            break;
                        case 2089:
                            int windowAntiPinchConfig = getWindowAntiPinchConfig();
                            bYDAutoEventValue.intValue = windowAntiPinchConfig;
                            sparseArray.put(2089, Integer.valueOf(windowAntiPinchConfig));
                            break;
                        case 2090:
                            int windowOpenPercent8 = getWindowOpenPercent(1);
                            bYDAutoEventValue.intValue = windowOpenPercent8;
                            sparseArray.put(2090, Integer.valueOf(windowOpenPercent8));
                            break;
                        case 2091:
                            int windowOpenPercent9 = getWindowOpenPercent(3);
                            bYDAutoEventValue.intValue = windowOpenPercent9;
                            sparseArray.put(2091, Integer.valueOf(windowOpenPercent9));
                            break;
                        case 2092:
                            int windowOpenPercent10 = getWindowOpenPercent(2);
                            bYDAutoEventValue.intValue = windowOpenPercent10;
                            sparseArray.put(2092, Integer.valueOf(windowOpenPercent10));
                            break;
                        case 2093:
                            int windowOpenPercent11 = getWindowOpenPercent(4);
                            bYDAutoEventValue.intValue = windowOpenPercent11;
                            sparseArray.put(2093, Integer.valueOf(windowOpenPercent11));
                            break;
                        case 2098:
                            float steeringWheelZ = this.mTsManagerImpl.getSettingAdapterManager().getSteeringWheelZ();
                            bYDAutoEventValue.floatValue = steeringWheelZ;
                            sparseArray.put(2098, Float.valueOf(steeringWheelZ));
                            break;
                        case 2100:
                            float steeringWheelY = getSteeringWheelY();
                            bYDAutoEventValue.floatValue = steeringWheelY;
                            sparseArray.put(2100, Float.valueOf(steeringWheelY));
                            break;
                        case 2120:
                            float rearviewMirrorX = getRearviewMirrorX(1);
                            bYDAutoEventValue.floatValue = rearviewMirrorX;
                            sparseArray.put(2120, Float.valueOf(rearviewMirrorX));
                            break;
                        case 2122:
                            float rearviewMirrorY = getRearviewMirrorY(1);
                            bYDAutoEventValue.floatValue = rearviewMirrorY;
                            sparseArray.put(2122, Float.valueOf(rearviewMirrorY));
                            break;
                        case 2130:
                            float rearviewMirrorX2 = getRearviewMirrorX(2);
                            bYDAutoEventValue.floatValue = rearviewMirrorX2;
                            sparseArray.put(2130, Float.valueOf(rearviewMirrorX2));
                            break;
                        case 2132:
                            float rearviewMirrorY2 = getRearviewMirrorY(2);
                            bYDAutoEventValue.floatValue = rearviewMirrorY2;
                            sparseArray.put(2132, Float.valueOf(rearviewMirrorY2));
                            break;
                        case 2134:
                            int mainSaveOrRecoverState = this.mTsManagerImpl.getSettingAdapterManager().getMainSaveOrRecoverState();
                            bYDAutoEventValue.intValue = mainSaveOrRecoverState;
                            sparseArray.put(2134, Integer.valueOf(mainSaveOrRecoverState));
                            break;
                        case 2169:
                            int autoExternalRearMirrorFoldActionState = getAutoExternalRearMirrorFoldActionState();
                            bYDAutoEventValue.intValue = autoExternalRearMirrorFoldActionState;
                            sparseArray.put(2169, Integer.valueOf(autoExternalRearMirrorFoldActionState));
                            break;
                        case 2445:
                            int energyType = getEnergyType();
                            bYDAutoEventValue.intValue = energyType;
                            sparseArray.put(2445, Integer.valueOf(energyType));
                            break;
                        case 3648:
                            int readyIndicatorLightState2 = getReadyIndicatorLightState();
                            bYDAutoEventValue.intValue = readyIndicatorLightState2;
                            sparseArray.put(3648, Integer.valueOf(readyIndicatorLightState2));
                            break;
                        case 4614:
                            int main2SaveOrRecoverState = this.mTsManagerImpl.getSettingAdapterManager().getMain2SaveOrRecoverState();
                            bYDAutoEventValue.intValue = main2SaveOrRecoverState;
                            sparseArray.put(4614, Integer.valueOf(main2SaveOrRecoverState));
                            break;
                        case 4615:
                            int moonRoofPercentControlFlag = getMoonRoofPercentControlFlag();
                            bYDAutoEventValue.intValue = moonRoofPercentControlFlag;
                            sparseArray.put(4615, Integer.valueOf(moonRoofPercentControlFlag));
                            break;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getAlarmState() {
        Log.v(TAG, "getAlarmState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getAlarmState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAlarmState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAntiTheftStatus() {
        Log.v(TAG, "getAntiTheftStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getAntiTheftStatus mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAntiTheftStatus();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoExternalRearMirrorFoldActionState() {
        Log.v(TAG, "getAutoExternalRearMirrorFoldActionState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAutoExternalRearMirrorFoldActionState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoExternalRearMirrorFoldedState() {
        Log.v(TAG, "getAutoExternalRearMirrorFoldedState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAutoExternalRearMirrorFoldedState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getAutoModelName() {
        Log.v(TAG, "getAutoModelName is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getAutoModelName mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAutoModelName();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getAutoSystemState() {
        Log.v(TAG, "getAutoSystemState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getAutoSystemState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int autoSystemState = this.mTsManagerImpl.getCarBodyManager().getAutoSystemState();
        if (autoSystemState == 1) {
            return 0;
        }
        if (autoSystemState == 2) {
            return 1;
        }
        return autoSystemState;
    }

    public int getAutoType() {
        Log.v(TAG, "getAutoType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getAutoType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getAutoType();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public String getAutoVIN() {
        Log.v(TAG, "getAutoVIN is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getAutoVIN mForTestValue is"), this.mForTest, " execute return", TAG);
            return "";
        }
        String autoVin = this.mOtaSdkManager.getAutoVin();
        return (autoVin == null || autoVin.length() == 0) ? DEFAULT_VIN : autoVin;
    }

    public int getBackDoorAntiPlayMode() {
        Log.v(TAG, "getBackDoorAntiPlayMode is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getBackDoorAntiPlayMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getBackDoorAntiPlayMode();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getBackDoorCurState() {
        Log.v(TAG, "getBackDoorCurState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getBackDoorCurState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getBackDoorCurState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getBackDoorInitState() {
        Log.v(TAG, "getBackDoorInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getBackDoorInitState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getBackDoorInitState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getBackDoorPosition() {
        Log.v(TAG, "getBackDoorPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getBackDoorPosition mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getBackDoorPosition();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getBatteryVoltageLevel() {
        Log.v(TAG, "getBatteryVoltageLevel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getBatteryVoltageLevel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int batteryVoltageLevel = this.mTsManagerImpl.getCarBodyManager().getBatteryVoltageLevel();
        if (batteryVoltageLevel == 0) {
            return 255;
        }
        if (batteryVoltageLevel == 1) {
            return 1;
        }
        if (batteryVoltageLevel == 2) {
            return 0;
        }
        return batteryVoltageLevel;
    }

    public int getBcmVechileStatus() {
        if (1 == this.mForTest) {
            a.q(new StringBuilder(" getBCMVechileStatus mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getBcmVechileStatus();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getDoorLockStatus(int i7) {
        Log.v(TAG, "getDoorLockStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getDoorLockStatus mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        if (i7 == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusLeftRear();
        }
        if (i7 == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusRightFront();
        }
        if (i7 != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getDoorLockStatusAreaRightRear();
    }

    public int getDoorState(int i7) {
        int doorStateLeftFront;
        Log.v(TAG, "getDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getDoorState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i7) {
            case 1:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateLeftFront();
                break;
            case 2:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateRightFront();
                break;
            case 3:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateLeftRear();
                break;
            case 4:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateRightRear();
                break;
            case 5:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateHood();
                break;
            case 6:
                doorStateLeftFront = this.mTsManagerImpl.getCarBodyManager().getDoorStateLuggage();
                break;
            default:
                doorStateLeftFront = 0;
                break;
        }
        if (doorStateLeftFront == 1) {
            return 0;
        }
        if (doorStateLeftFront == 0) {
            return -1;
        }
        if (doorStateLeftFront == 2) {
            return 1;
        }
        return doorStateLeftFront;
    }

    public int getElecSlideDoorState() {
        Log.v(TAG, "getElecSlideDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getElecSlideDoorState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getElecSlideDoorState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getElectricFrontHatchConfiguration() {
        Log.i(TAG, "getElectricFrontHatchConfiguration is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getElectricFrontHatchConfiguration mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getStudyAdapterManager() != null) {
            return this.mTsManagerImpl.getStudyAdapterManager().getElectricFrontHatchConfiguration();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getEnergyType() {
        Log.v(TAG, "getEnergyType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getEnergyType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getEnergyType();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getFuelElecLowPower() {
        Log.v(TAG, "getFuelElecLowPower is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getFuelElecLowPower mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getFuelElecLowPower();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getLeftSlideDoorAction() {
        Log.v(TAG, "getLeftSlideDoorAction is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getLeftSlideDoorAction mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftSlideDoorAction();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftSlideDoorPercent() {
        Log.v(TAG, "getLeftSlideDoorPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getLeftSlideDoorPercent mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftSlideDoorPercent();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftSlideDoorState() {
        Log.v(TAG, "getLeftSlideDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getLeftSlideDoorState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftSlideDoorState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getLeftStrutMorotRunningState() {
        Log.v(TAG, "getLeftStrutMorotRunningState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getLeftStrutMorotRunningState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getLeftStrutMorotRunningState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getLightStatus(int i7) {
        Log.v(TAG, "getLightStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder(" getBCMVechileStatus mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        int lightSideStatus = 0;
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        switch (i7) {
            case 1:
                Log.v(TAG, "getLightSideStatus");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightSideStatus();
                break;
            case 2:
                Log.v(TAG, "getLightLowBeamStatus");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightLowBeamStatus();
                break;
            case 3:
                Log.v(TAG, "getLightHighBeam");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightHighBeam();
                break;
            case 4:
                Log.v(TAG, "getLightLeftTurnSignal");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightLeftTurnSignal();
                break;
            case 5:
                Log.v(TAG, "getLightRightTurnSignal");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRightTurnSignal();
                break;
            case 6:
                Log.v(TAG, "getLightFrontFog");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightFrontFog();
                break;
            case 7:
                Log.v(TAG, "getLightRearFog");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRearFog();
                break;
            case 8:
                Log.v(TAG, "getLightFoot");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightFoot();
                break;
            case 9:
                Log.v(TAG, "getLightRearPosition");
                lightSideStatus = this.mTsManagerImpl.getCarBodyManager().getLightRearPosition();
                break;
        }
        Log.v(TAG, "getLightStatus Type is:" + i7 + " And Result is:" + lightSideStatus);
        return lightSideStatus;
    }

    public int getMain2SaveOrRecoverState() {
        Log.i(TAG, "getMain2SaveOrRecoverState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMain2SaveOrRecoverState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMain2SaveOrRecoverState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMainSaveOrRecoverState() {
        Log.i(TAG, "getMainSaveOrRecoverState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMainSaveOrRecoverState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMainSaveOrRecoverState();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMoonRoofConfig() {
        Log.v(TAG, "getMoonRoofConfig is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMoonRoofConfig mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMoonRoofConfig();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getMoonRoofPercentControlFlag() {
        if (1 == this.mForTest) {
            Log.v(TAG, " getMoonRoofPercentControlFlag mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMoonRoofPercentControlFlag();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getMoonRoofType() {
        Log.v(TAG, "getMoonRoofType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getMoonRoofType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getMoonRoofType();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int getPowerLevel() {
        Log.i(TAG, "getPowerLevel is execute and current process " + this.mContext.getPackageName());
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getPowerLevel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        int powerLevel = this.mTsManagerImpl.getCarBodyManager().getPowerLevel();
        if (powerLevel == 0) {
            return 255;
        }
        if (powerLevel == 1) {
            return 0;
        }
        if (powerLevel == 2) {
            return 1;
        }
        if (powerLevel == 3) {
            return 2;
        }
        return powerLevel;
    }

    public int getReadyIndicatorLightState() {
        Log.v(TAG, "getReadyIndicatorLightState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getReadyIndicatorLightState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public String getRealAutoVIN() {
        Log.v(TAG, "getRealAutoVIN is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 != this.mForTest) {
            return this.mSdkManager.getRealAutoVin();
        }
        a.q(new StringBuilder("getRealAutoVIN mForTestValue is"), this.mForTest, " execute return", TAG);
        return "";
    }

    public int getRearLampLightState() {
        Log.v(TAG, "getRearLampLightState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getRearLampLightState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRearLampLightState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getRearWindowblindInitState() {
        Log.v(TAG, "getRearWindowblindInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getRearWindowblindInitState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRearWindowblindInitState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getRearWindowblindPosition() {
        Log.v(TAG, "getRearWindowblindPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getRearWindowblindPosition mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRearWindowblindPosition();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getRearWindowblindState() {
        Log.v(TAG, "getRearWindowblindState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getRearWindowblindState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRearWindowblindState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getRearviewMirrorSupport() {
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getRearviewMirrorSupport mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorSupport();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public float getRearviewMirrorX(int i7) {
        Log.i(TAG, "getRearviewMirrorX is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i7 == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorXLeft();
        }
        if (i7 == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorXRight();
        }
        return 0.0f;
    }

    public float getRearviewMirrorY(int i7) {
        Log.i(TAG, "getRearviewMirrorY is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0.0f;
        }
        if (i7 == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorYLeft();
        }
        if (i7 == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getRearviewMirrorYRight();
        }
        return 0.0f;
    }

    public int getRightSlideDoorAction() {
        Log.v(TAG, "getRightSlideDoorAction is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getRightSlideDoorAction mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightSlideDoorAction();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getRightSlideDoorPercent() {
        Log.v(TAG, "getRightSlideDoorPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getRightSlideDoorPercent mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightSlideDoorPercent();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getRightSlideDoorState() {
        Log.v(TAG, "getRightSlideDoorState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getRightSlideDoorState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightSlideDoorState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getRightStrutMorotRunningState() {
        Log.v(TAG, "getRightStrutMorotRunningState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getRightStrutMorotRunningState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getRightStrutMorotRunningState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSmartVoiceLimit() {
        if (1 == this.mForTest) {
            a.q(new StringBuilder(" getSmartVoiceLimit mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSmartVoiceLimit();
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public double getSteeringWheelValue(int i7) {
        Log.v(TAG, "getSteeringWheelValue is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getSteeringWheelValue mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0.0d;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSteeringWheelValue(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0d;
    }

    public float getSteeringWheelY() {
        Log.i(TAG, "getSteeringWheelY is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSteeringWheelY();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public float getSteeringWheelZ() {
        Log.i(TAG, "getSteeringWheelZ is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSteeringWheelZ();
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0.0f;
    }

    public int getSunroofCloseNotice() {
        Log.v(TAG, "getSunroofCloseNotice is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getSunroofCloseNotice mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSunroofCloseNotice();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSunroofInitState() {
        Log.v(TAG, "getSunroofInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getSunroofInitState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSunroofInitState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSunroofPosition() {
        Log.v(TAG, "getSunroofPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getSunroofPosition mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSunroofPosition();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSunroofState() {
        Log.v(TAG, "getSunroofState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getSunroofState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSunroofState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getSunroofWindowblindPosition() {
        Log.v(TAG, "getSunroofWindowblindPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getSunroofWindowblindPosition mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getSunroofWindowblindPosition();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowAntiPinchConfig() {
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            Log.v(TAG, "getWindowAntiPinchConfig mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowAntiPinchConfig();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowControlState(int i7) {
        Log.v(TAG, "getWindowControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowControlState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowControlState(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowInitState(int i7) {
        Log.v(TAG, "getWindowInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowThermalProtectionStateRearPanel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i7 == 1) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
        }
        if (i7 == 2) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
        }
        if (i7 == 3) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
        }
        if (i7 != 4) {
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
    }

    public int getWindowInitStateLeftFront() {
        Log.v(TAG, "getWindowInitStateLeftFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowInitStateLeftFront mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftFront();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowInitStateLeftRear() {
        Log.v(TAG, "getWindowInitStateLeftRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowInitStateLeftRear mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateLeftRear();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowInitStateRightFront() {
        Log.v(TAG, "getWindowInitStateRightFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowInitStateRightFront mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightFront();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowInitStateRightRear() {
        Log.v(TAG, "getWindowInitStateRightRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowInitStateRightRear mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowInitStateRightRear();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowLockState(int i7) {
        Log.v(TAG, "getWindowLockState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowLockState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowLockState(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowOpenPercent(int i7) {
        Log.v(TAG, "getWindowOpenPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowOpenPercent mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        switch (i7) {
        }
        return 0;
    }

    public int getWindowPermitState() {
        Log.v(TAG, "getWindowPermitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowPermitState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowPermitState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowState(int i7) {
        Log.v(TAG, "getWindowState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i7) {
        }
        return 0;
    }

    public int getWindowThermalProtectionState(int i7) {
        Log.v(TAG, "getWindowThermalProtectionState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowThermalProtectionStateRearPanel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        switch (i7) {
        }
        return 0;
    }

    public int getWindowThermalProtectionStateLeftFront() {
        Log.v(TAG, "getWindowThermalProtectionStateLeftFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowThermalProtectionStateLeftFront mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftFront();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowThermalProtectionStateLeftRear() {
        Log.v(TAG, "getWindowThermalProtectionStateLeftRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowThermalProtectionStateLeftRear mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateLeftRear();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowThermalProtectionStateMoonRoof() {
        Log.v(TAG, "getWindowThermalProtectionStateMoonRoof is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowThermalProtectionStateMoonRoof mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateMoonRoof();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowThermalProtectionStatePanel() {
        Log.v(TAG, "getWindowThermalProtectionStatePanel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowThermalProtectionStatePanel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStatePanel();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowThermalProtectionStateRearPanel() {
        Log.v(TAG, "getWindowThermalProtectionStateRearPanel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowThermalProtectionStateRearPanel mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRearPanel();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowThermalProtectionStateRightFront() {
        Log.v(TAG, "getWindowThermalProtectionStateRightFront is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowThermalProtectionStateRightFront mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightFront();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowThermalProtectionStateRightRear() {
        Log.v(TAG, "getWindowThermalProtectionStateRightRear is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowThermalProtectionStateRightRear mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowThermalProtectionStateRightRear();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowblindInitState() {
        Log.v(TAG, "getWindowblindInitState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowblindInitState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowblindInitState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getWindowblindState() {
        Log.v(TAG, "getWindowblindState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getWindowblindState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().getWindowblindState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int hasMessage(int i7) {
        Log.v(TAG, "hasMessage is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().hasMessage(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public boolean isConnectSuccess() {
        return TsManagerImpl.isConnectSuccess();
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i7, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoBodyworkListener> it = this.mAbsBydAutoBodyworkListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i7, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        registerListener(absBYDAutoBodyworkListener, new int[0]);
    }

    public void remoteWhistle() {
        Log.v(TAG, "remoteWhistle is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("getSunroofState mForTestValue is"), this.mForTest, " execute return", TAG);
        } else if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().remoteWhistle();
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int set(int[] iArr, BYDAutoEventValue bYDAutoEventValue) {
        int[] iArr2;
        if (iArr != null && (iArr2 = bYDAutoEventValue.intArrayValue) != null && iArr.length == iArr2.length) {
            b.q(iArr, new StringBuilder("more param event type="), TAG);
            boolean zCreateWindowStateData = true;
            if (iArr.length == 1) {
                int i7 = iArr[0];
                if (i7 == 1763 || i7 == 1778 || i7 == 2034 || i7 == 2060) {
                    if (i7 == 1763) {
                        return setWindowControlState(1, bYDAutoEventValue.intArrayValue[0]);
                    }
                    if (i7 == 1778) {
                        return setWindowControlState(3, bYDAutoEventValue.intArrayValue[0]);
                    }
                    if (i7 == 2034) {
                        return setWindowControlState(2, bYDAutoEventValue.intArrayValue[0]);
                    }
                    if (i7 == 2060) {
                        return setWindowControlState(4, bYDAutoEventValue.intArrayValue[0]);
                    }
                    Log.v(TAG, "no have control window event.");
                    return -2147482648;
                }
            } else if (iArr.length > 1) {
                int[] iArr3 = {0, 0, 0, 0};
                for (int i10 = 0; i10 < iArr.length && (zCreateWindowStateData = createWindowStateData(iArr[i10], bYDAutoEventValue.intArrayValue[i10], iArr3)); i10++) {
                }
                if (zCreateWindowStateData) {
                    return setWindowControlState(iArr3);
                }
            }
        }
        return super.set(iArr, bYDAutoEventValue);
    }

    public int setAllDoorsLock(int i7) {
        Log.v(TAG, "setAllDoorsLock is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setAllDoorsLock mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAllDoorsLock(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setAllDoorsLockScene(int i7) {
        if (1 == this.mForTest) {
            a.q(new StringBuilder(" setAllDoorsLockScene mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAllDoorsLockScene(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setAntiTheftStatus(int i7) {
        Log.v(TAG, "setAntiTheftStatus is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setAntiTheftStatus mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAntiTheftStatus(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setAutoExternalRearMirrorFoldState(int i7) {
        Log.v(TAG, "setAutoExternalRearMirrorFoldState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setAutoExternalRearMirrorFoldState(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setBackDoorHeightPosition(int i7) {
        Log.v(TAG, "setBackDoorHeightPosition is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setBackDoorHeightPosition mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setBackDoorHeightPosition(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setBackDoorHeightPositionMyCar(int i7) {
        Log.v(TAG, "setBackDoorHeightPositionMyCar is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setBackDoorHeightPositionMyCar mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setBackDoorHeightPositionMyCar(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setBackDoorOpenedHeight(int i7) {
        Log.i(TAG, "setBackDoorOpenedHeight is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setBackDoorOpenedHeight mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setBackDoorOpenedHeight(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setDriveDistanceShowMode(int i7) {
        Log.v(TAG, "setDriveDistanceShowMode is execute");
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setDriveDistanceShowMode mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setDriveDistanceShowMode(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setElecBackDoorControlState(int i7) {
        Log.v(TAG, "setElecBackDoorControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setElecBackDoorControlState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setElecBackDoorControlState(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setElecSlideDoorState(int i7) {
        if (1 == this.mForTest) {
            Log.v(TAG, " setElecSlideDoorState mForTestValue is 0 execute return");
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setElecSlideDoorState(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return -1;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i7, Object obj) {
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).getClass();
            } else if (obj instanceof Double) {
                ((Double) obj).getClass();
            } else if (!(obj instanceof byte[])) {
                Log.v(TAG, "the type of value is not a suitable type");
                return -1;
            }
            iIntValue = 0;
        }
        switch (i7) {
            case 1688:
                setBackDoorHeightPosition(iIntValue);
                Log.v(TAG, "setElecBackDoorControlState" + iIntValue);
                return 0;
            case 1715:
                setElecBackDoorControlState(iIntValue);
                Log.v(TAG, "setElecBackDoorControlState" + iIntValue);
                return 0;
            case 1721:
                setAllDoorsLock(iIntValue);
                Log.v(TAG, "setDoorLockStatusAll" + iIntValue);
                return 0;
            case 1766:
                setWindowOpenPercent(1, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 1781:
                setWindowOpenPercent(3, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 1783:
                settingLuggage(iIntValue);
                Log.v(TAG, "settingLuggage" + iIntValue);
                return 0;
            case 1784:
                setLeftSlideDoorVoiceControlState(iIntValue);
                Log.v(TAG, "setLeftSlideDoorVoiceControlState" + iIntValue);
                return 0;
            case 1790:
                setWindowOpenPercent(5, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 2004:
                turnRearviewMirror(iIntValue);
                Log.v(TAG, "turnRearviewMirror:" + iIntValue);
                return 0;
            case 2013:
                setWindowOpenPercent(7, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 2015:
                setAutoExternalRearMirrorFoldState(iIntValue);
                Log.v(TAG, "setAutoExternalRearMirrorFoldS" + iIntValue);
                return 0;
            case 2037:
                setWindowOpenPercent(2, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 2063:
                setWindowOpenPercent(4, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 2067:
                setRightSlideDoorVoiceControlState(iIntValue);
                Log.v(TAG, "setRightSlideDoorVoiceControlState" + iIntValue);
                return 0;
            case 2085:
                setWindowOpenPercent(6, iIntValue);
                Log.v(TAG, "setWindowOpenPercent" + iIntValue);
                return 0;
            case 2135:
                turnSteeringWheel(iIntValue);
                Log.v(TAG, "turnSteeringWheel:" + iIntValue);
                return 0;
            case 4600:
                setBackDoorHeightPositionMyCar(iIntValue);
                Log.v(TAG, "setBackDoorHeightPosition" + iIntValue);
                return 0;
            default:
                Log.v(TAG, "case fail ! please check out");
                return -1;
        }
    }

    public int setLeftSlideDoorVoiceControlState(int i7) {
        Log.v(TAG, "setLeftSlideDoorVoiceControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setLeftSlideDoorVoiceControlState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setLeftSlideDoorVoiceControlState(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setMoonRoofType(int i7) {
        Log.v(TAG, "setMoonRoofType is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setMoonRoofType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setMoonRoofType(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setRightSlideDoorVoiceControlState(int i7) {
        Log.v(TAG, "setRightSlideDoorVoiceControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setRightSlideDoorVoiceControlState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setRightSlideDoorVoiceControlState(i7);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setSunroofAndSunshade(int i7) {
        if (1 == this.mForTest) {
            a.q(new StringBuilder(" setSunroofAndSunshade mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setSunroofAndSunshade(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int setVoiceCtlMoonRoofS(int i7) {
        Log.i(TAG, "setVoiceCtlMoonRoofS is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setVoiceCtlMoonRoofS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setVoiceCtlMoonRoofS(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setVoiceCtlSunshadePanelS(int i7) {
        Log.i(TAG, "setVoiceCtlSunshadePanelS is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            Log.v(TAG, "setVoiceCtlSunshadePanelS mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setVoiceCtlSunshadePanelS(i7);
        }
        Log.e(TAG, "caradatper still connecting, please wait");
        return 0;
    }

    public int setWindowControlState(int i7, int i10) {
        int[] iArr = {0, 0, 0, 0};
        if (i7 == 1) {
            iArr[0] = i10;
        } else if (i7 == 2) {
            iArr[1] = i10;
        } else if (i7 == 3) {
            iArr[2] = i10;
        } else if (i7 != 4) {
            Log.v(TAG, "no have area.");
        } else {
            iArr[3] = i10;
        }
        return setWindowControlState(iArr);
    }

    public int setWindowLockState(int i7, int i10) {
        Log.v(TAG, "setWindowLockState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.a(i10);
            a.q(new StringBuilder("setWindowLockState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setWindowLockState(i7, i10);
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int setWindowOpenPercent(int i7, int i10) {
        Log.v(TAG, "setWindowOpenPercent is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            this.mTestCallback.b(i7, i10);
            a.q(new StringBuilder("setWindowOpenPercent mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "caradatper still connecting, please wait");
            return 0;
        }
        switch (i7) {
        }
        return 0;
    }

    public int setWindowsMode(int i7) {
        if (1 == this.mForTest) {
            a.q(new StringBuilder(" setWindowsMode mForTestValue is "), this.mForTest, " execute return", TAG);
            return -1;
        }
        if (this.mTsManagerImpl.getCarBodyManager() != null) {
            return this.mTsManagerImpl.getCarBodyManager().setWindowsMode(i7);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public void settingLuggage(int i7) {
        Log.v(TAG, "settingLuggage is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("settingLuggage mForTestValue is"), this.mForTest, " execute return", TAG);
        } else if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().settingLuggage(i7);
        }
    }

    public void turnRearviewMirror(int i7) {
        Log.i(TAG, "turnRearviewMirror is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().turnRearviewMirror(i7);
        }
    }

    public void turnSteeringWheel(int i7) {
        Log.i(TAG, "turnSteeringWheel is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
        } else {
            this.mTsManagerImpl.getCarBodyManager().turnSteeringWheel(i7);
        }
    }

    public void unregisterListener(d dVar) {
    }

    public void registerListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe bodywork listener ");
        sb.append(absBYDAutoBodyworkListener);
        sb.append(" featureIDs = ");
        b.q(iArr, sb, TAG);
        if (absBYDAutoBodyworkListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            ArrayList arrayList = this.mTestCallback.f13651a;
            if (!arrayList.contains(absBYDAutoBodyworkListener)) {
                arrayList.add(absBYDAutoBodyworkListener);
            }
            a.q(new StringBuilder("registerListener mForTestValue is"), this.mForTest, " execute return", TAG);
            return;
        }
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (!this.mAbsBydAutoBodyworkListeners.contains(absBYDAutoBodyworkListener)) {
            this.mAbsBydAutoBodyworkListeners.add(absBYDAutoBodyworkListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoBodyworkListener);
        TsManagerImpl.getInstance(this.mContext).registerCarBodyCallback();
    }

    public void unregisterListener(AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener) {
        Log.v(TAG, "unregisterListener is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        if (1 == this.mForTest) {
            C1139b c1139b = this.mTestCallback;
            if (absBYDAutoBodyworkListener != null) {
                c1139b.f13651a.remove(absBYDAutoBodyworkListener);
            } else {
                c1139b.getClass();
            }
            a.q(new StringBuilder("unregisterListener mForTestValue is"), this.mForTest, " execute return", TAG);
            return;
        }
        Log.v(TAG, "unsubscribeBodyworkCallback: " + absBYDAutoBodyworkListener);
        if (absBYDAutoBodyworkListener == null) {
            return;
        }
        this.mAbsBydAutoBodyworkListeners.remove(absBYDAutoBodyworkListener);
        unregisterConnectListener(absBYDAutoBodyworkListener);
    }

    public int setWindowControlState(int[] iArr) {
        Log.v(TAG, "setWindowControlState is execute");
        enforcePermission(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_SET);
        if (1 == this.mForTest) {
            a.q(new StringBuilder("setWindowControlState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getCarBodyManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getCarBodyManager().setWindowControlState(iArr);
    }
}
