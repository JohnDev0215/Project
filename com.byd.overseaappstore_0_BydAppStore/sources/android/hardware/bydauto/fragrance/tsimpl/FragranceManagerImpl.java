package android.hardware.bydauto.fragrance.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener;
import android.hardware.bydauto_test.TestFragranceManagerImplCallback;
import android.provider.Settings;
import com.ts.lib.caradapter.hvac.HvacPacket;
import com.ts.lib.caradapter.interfaces.OnHvacChangedListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import spi.d;
import spi.e;

/* JADX INFO: loaded from: classes.dex */
public class FragranceManagerImpl extends AbsTsMyCar {
    private static final int EMPTY = 32;
    private static final int S_FOR_TEST_VALUE = 1;
    private static final String S_KEY_FOR_TEST = "key_for_test_FragranceManagerImpl";
    private static final String TAG = "FragranceManagerImpl";
    private static volatile FragranceManagerImpl sInstance;
    private int fragranceNameOneAbR;
    private int fragranceNameThreeAbR;
    private int fragranceNameTwoAbR;
    private AbsBYDAutoFragranceListener mAbsBydAutoFragranceListener;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int[] mFeatureIds;
    private int mForTest;
    private TestFragranceManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoFragranceListener> mAbsBydAutoFragranceListeners = new CopyOnWriteArrayList<>();
    private int mOnline = 2;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    public FragranceManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new TestFragranceManagerImplCallback();
                e.s(e.k("FragranceManagerImpl mForTestValue is"), this.mForTest, " execute return", TAG);
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(context);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnHvacChangedListener(new OnHvacChangedListener() { // from class: android.hardware.bydauto.fragrance.tsimpl.FragranceManagerImpl.1
            public void onHvacChanged(int i2, int i3, HvacPacket hvacPacket) {
                if (FragranceManagerImpl.this.mAbsBydAutoFragranceListeners.isEmpty()) {
                    return;
                }
                try {
                    FragranceManagerImpl.this.hvacCallback(i2, i3, hvacPacket);
                } catch (Exception unused) {
                    Log.e(FragranceManagerImpl.TAG, "hvacCallback is error");
                }
            }
        });
    }

    public static FragranceManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            synchronized (FragranceManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new FragranceManagerImpl(context);
                }
            }
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hvacCallback(int i, int i2, HvacPacket hvacPacket) {
        int i3;
        int supportFragranceTypeOne;
        e.t("hvacCallback propertyId:", i, TAG);
        switch (i) {
            case FragranceHalId.ID_SUPPORT_FRAGRANCE_TYPE_ONE /* 557856028 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener.onFragranceInstallStateChanged(1, hvacPacket.getSupportFragranceTypeOne());
                    }
                }
                i3 = 3341;
                supportFragranceTypeOne = hvacPacket.getSupportFragranceTypeOne();
                break;
            case FragranceHalId.ID_SUPPORT_FRAGRANCE_TYPE_TWO /* 557856029 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener2 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener2.onFragranceInstallStateChanged(2, hvacPacket.getSupportFragranceTypeTwo());
                    }
                }
                i3 = 3519;
                supportFragranceTypeOne = hvacPacket.getSupportFragranceTypeTwo();
                break;
            case FragranceHalId.ID_SUPPORT_FRAGRANCE_TYPE_THREE /* 557856030 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener3 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener3.onFragranceInstallStateChanged(3, hvacPacket.getSupportFragranceTypeThree());
                    }
                }
                i3 = 3550;
                supportFragranceTypeOne = hvacPacket.getSupportFragranceTypeThree();
                break;
            case FragranceHalId.ID_CUR_FRAGRANCE_TYPE /* 557858816 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener4 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener4.onFragranceTypeChanged(hvacPacket.getCurFragranceType());
                    }
                }
                return;
            case FragranceHalId.ID_FRAGRANCE_RESIDUE_ONE /* 557858818 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener5 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener5.onFragranceResidueChanged(1, hvacPacket.getFragranceResidueOne());
                    }
                }
                i3 = 3540;
                supportFragranceTypeOne = hvacPacket.getFragranceResidueOne();
                break;
            case FragranceHalId.ID_FRAGRANCE_CONCENTRATIONR /* 557858819 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener6 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener6.onFragranceConcentrationChanged(hvacPacket.getCurFragranceType(), hvacPacket.getFragranceConcentrationR());
                    }
                }
                return;
            case FragranceHalId.ID_FRAGRANCE_RESIDUE_TWO /* 557858821 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener7 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener7.onFragranceResidueChanged(2, hvacPacket.getFragranceResidueTwo());
                    }
                }
                i3 = 3544;
                supportFragranceTypeOne = hvacPacket.getFragranceResidueTwo();
                break;
            case FragranceHalId.ID_FRAGRANCE_RESIDUE_THREE /* 557858822 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener8 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener8.onFragranceResidueChanged(3, hvacPacket.getFragranceResidueThree());
                    }
                }
                i3 = 3548;
                supportFragranceTypeOne = hvacPacket.getFragranceResidueThree();
                break;
            case FragranceHalId.ID_FRAGRANCE_LESS_ALLOWANCE /* 557858823 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener9 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener9.onFragranceLessAllowance(1, hvacPacket.getFragranceLessAllowance());
                    }
                }
                i3 = 3541;
                supportFragranceTypeOne = hvacPacket.getFragranceLessAllowance();
                break;
            case FragranceHalId.ID_FRAGRANCE_NAME_ONE_ABR /* 557858825 */:
                this.fragranceNameOneAbR = hvacPacket.getFragranceNameOneAbR();
                Iterator<AbsBYDAutoFragranceListener> it = this.mAbsBydAutoFragranceListeners.iterator();
                while (it.hasNext()) {
                    it.next().onFragranceNameChanged(1, this.fragranceNameOneAbR);
                }
                return;
            case FragranceHalId.ID_FRAGRANCE_NAME_ONE_BBR /* 557858827 */:
                if (this.fragranceNameOneAbR == 32) {
                    Iterator<AbsBYDAutoFragranceListener> it2 = this.mAbsBydAutoFragranceListeners.iterator();
                    while (it2.hasNext()) {
                        it2.next().onFragranceNameChanged(1, hvacPacket.getFragranceNameOneBbR());
                    }
                    return;
                }
                return;
            case FragranceHalId.ID_FRAGRANCE_NAME_TWO_ABR /* 557858833 */:
                this.fragranceNameTwoAbR = hvacPacket.getFragranceNameTwoAbR();
                Iterator<AbsBYDAutoFragranceListener> it3 = this.mAbsBydAutoFragranceListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onFragranceNameChanged(2, this.fragranceNameTwoAbR);
                }
                return;
            case FragranceHalId.ID_FRAGRANCE_NAME_TWO_BBR /* 557858835 */:
                if (this.fragranceNameTwoAbR == 32) {
                    Iterator<AbsBYDAutoFragranceListener> it4 = this.mAbsBydAutoFragranceListeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onFragranceNameChanged(2, hvacPacket.getFragranceNameTwoBbR());
                    }
                    return;
                }
                return;
            case FragranceHalId.ID_FRAGRANCE_NAME_THREE_ABR /* 557858841 */:
                this.fragranceNameThreeAbR = hvacPacket.getFragranceNameThreeAbR();
                Iterator<AbsBYDAutoFragranceListener> it5 = this.mAbsBydAutoFragranceListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onFragranceNameChanged(3, this.fragranceNameThreeAbR);
                }
                return;
            case FragranceHalId.ID_FRAGRANCE_NAME_THREE_BBR /* 557858843 */:
                if (this.fragranceNameThreeAbR == 32) {
                    Iterator<AbsBYDAutoFragranceListener> it6 = this.mAbsBydAutoFragranceListeners.iterator();
                    while (it6.hasNext()) {
                        it6.next().onFragranceNameChanged(3, hvacPacket.getFragranceNameThreeBbR());
                    }
                    return;
                }
                return;
            case FragranceHalId.ID_FRAGRANCE_LESS_ALLOWANCE_TWO_R /* 557858848 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener10 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener10.onFragranceLessAllowance(2, hvacPacket.getFragranceLessAllowanceTwoR());
                    }
                }
                i3 = 3545;
                supportFragranceTypeOne = hvacPacket.getFragranceLessAllowanceTwoR();
                break;
            case FragranceHalId.ID_FRAGRANCE_LESS_ALLOWANCE_THREE_R /* 557858849 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener11 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener11.onFragranceLessAllowance(3, hvacPacket.getFragranceLessAllowanceThreeR());
                    }
                }
                i3 = 3549;
                supportFragranceTypeOne = hvacPacket.getFragranceLessAllowanceThreeR();
                break;
            default:
                return;
        }
        caseFeatureIds(i3, Integer.valueOf(supportFragranceTypeOne));
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00b8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.hardware.bydauto.BYDAutoEventValue get(int[] r12, java.lang.Class<?> r13) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.hardware.bydauto.fragrance.tsimpl.FragranceManagerImpl.get(int[], java.lang.Class):android.hardware.bydauto.BYDAutoEventValue");
    }

    public int getCurFragranceType() {
        Log.v(TAG, "getCurFragranceType is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getCurFragranceType mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getCurFragranceType();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getFragranceConcentration(int i) {
        Log.v(TAG, "getFragranceConcentration is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getFragranceConcentration mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFragranceConcentrationTypeOne();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFragranceConcentrationTypeTwo();
        }
        if (i != 3) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getFragranceConcentrationTypeThree();
    }

    public int getFragranceInstallState(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getSupportFragranceType mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getFragranceInstallState(i);
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getFragranceLessAllowanceState(int i) {
        String str;
        if (1 == this.mForTest) {
            str = "getFragranceLessAllowanceState mForTestValue is 0 execute return";
        } else {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                if (i == 1) {
                    return this.mTsManagerImpl.getHvacAdapterManager().getFragranceLessAllowanceStateOne();
                }
                if (i == 2) {
                    return this.mTsManagerImpl.getHvacAdapterManager().getFragranceLessAllowanceStateTwo();
                }
                if (i != 3) {
                    return 0;
                }
                return this.mTsManagerImpl.getHvacAdapterManager().getFragranceLessAllowanceStateThree();
            }
            str = "adapter is not connect";
        }
        Log.v(TAG, str);
        return 0;
    }

    public int getFragranceName(int i) {
        Log.v(TAG, "getFragranceName is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getFragranceName mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            int fragranceNameOneAb = this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameOneAb();
            return fragranceNameOneAb != 32 ? fragranceNameOneAb : this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameOneBb();
        }
        if (i == 2) {
            int fragranceNameTwoAb = this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameTwoAb();
            return fragranceNameTwoAb != 32 ? fragranceNameTwoAb : this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameTwoBb();
        }
        if (i != 3) {
            return 0;
        }
        int fragranceNameThreeAb = this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameThreeAb();
        return fragranceNameThreeAb != 32 ? fragranceNameThreeAb : this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameThreeBb();
    }

    public int getFragranceOnlineState() {
        Log.v(TAG, "getFragranceOnlineState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getFragranceOnlineState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mOnline;
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getFragranceResidue(int i) {
        Log.v(TAG, "getFragranceResidue is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getFragranceResidue mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (i == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFragranceResidueOne();
        }
        if (i == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFragranceResidueTwo();
        }
        if (i != 3) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getFragranceResidueThree();
    }

    public int getFragranceSystemState() {
        Log.v(TAG, "getFragranceSystemState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getFragranceSystemState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFragranceSystemState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    public int getGreenSystemState() {
        Log.v(TAG, "getGreenSystemState is execute");
        if (1 == this.mForTest) {
            e.s(e.k("getGreenSystemState mForTestValue is"), this.mForTest, " execute return", TAG);
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getGreenSystemState();
        }
        Log.e(TAG, "adapter is not connect");
        return 0;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
        Iterator<AbsBYDAutoFragranceListener> it = this.mAbsBydAutoFragranceListeners.iterator();
        while (it.hasNext()) {
            it.next().onDataEventChanged(i, bYDAutoEventValue);
        }
    }

    public void registerListener(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        registerListener(absBYDAutoFragranceListener, new int[0]);
    }

    public void registerListener(AbsBYDAutoFragranceListener absBYDAutoFragranceListener, int[] iArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mContext.getPackageName());
        sb.append(" byd_subscribe fragrance listener ");
        sb.append(absBYDAutoFragranceListener);
        sb.append(" featureIDs = ");
        d.v(iArr, sb, TAG);
        if (absBYDAutoFragranceListener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoFragranceListener(absBYDAutoFragranceListener);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("registerListener mForTestValue is");
            e.u(sb2, this.mForTest, " execute return", TAG);
            return;
        }
        if (!this.mAbsBydAutoFragranceListeners.contains(absBYDAutoFragranceListener)) {
            this.mAbsBydAutoFragranceListeners.add(absBYDAutoFragranceListener);
        }
        setFeatureIds(iArr);
        registerConnectListener(this.mContext, absBYDAutoFragranceListener);
        TsManagerImpl.getInstance(this.mContext).registerHvacCallback();
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public int setForOneFeatureId(int i, Object obj) {
        String str;
        int iIntValue;
        if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Float) {
                ((Float) obj).floatValue();
            } else if (obj instanceof Double) {
                ((Double) obj).doubleValue();
            } else {
                if (!(obj instanceof byte[])) {
                    str = "the type of value is not a suitable type";
                    Log.v(TAG, str);
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i == 3330) {
            setFragranceType(iIntValue);
            return 1;
        }
        if (i == 3445) {
            setFragranceConcentration(iIntValue);
            return 1;
        }
        str = "case fail ! please check out";
        Log.v(TAG, str);
        return -1;
    }

    public int setFragranceConcentration(int i) {
        Log.v(TAG, "setFragranceConcentration is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().setFragranceConcentration(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.enqueueMessage(2001, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setFragranceConcentration mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public int setFragranceType(int i) {
        Log.v(TAG, "setFragranceType is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().setFragranceType(i);
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        this.mTestCallback.enqueueMessage(2000, i);
        StringBuilder sb = new StringBuilder();
        sb.append("setFragranceType mForTestValue is");
        e.s(sb, this.mForTest, " execute return", TAG);
        return 0;
    }

    public void unregisterListener(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        Log.d(TAG, "unregisterListener is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(absBYDAutoFragranceListener);
            StringBuilder sb = new StringBuilder();
            sb.append("unregisterListener mForTestValue is");
            e.u(sb, this.mForTest, " execute return", TAG);
            return;
        }
        Log.d(TAG, "unsubscribeFragranceCallback: " + absBYDAutoFragranceListener);
        if (absBYDAutoFragranceListener == null) {
            return;
        }
        this.mAbsBydAutoFragranceListeners.remove(absBYDAutoFragranceListener);
        unregisterConnectListener(absBYDAutoFragranceListener);
    }
}
