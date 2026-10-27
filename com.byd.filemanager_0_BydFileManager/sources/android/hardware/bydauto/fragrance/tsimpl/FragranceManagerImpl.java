package android.hardware.bydauto.fragrance.tsimpl;

import a.b.e.h;
import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.AbsTsMyCar;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.Log;
import android.hardware.bydauto.TsManagerImpl;
import android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener;
import android.provider.Settings;
import android.util.SparseArray;
import com.ts.lib.caradapter.hvac.HvacPacket;
import com.ts.lib.caradapter.interfaces.OnHvacChangedListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

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
    private h mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoFragranceListener> mAbsBydAutoFragranceListeners = new CopyOnWriteArrayList<>();
    private int mOnline = 2;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    FragranceManagerImpl(Context context) {
        this.mForTest = 0;
        this.mContext = context;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new h();
                Log.v(TAG, "FragranceManagerImpl mForTestValue is" + this.mForTest + " execute return");
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
                FragranceManagerImpl.this.hvacCallback(i2, i3, hvacPacket);
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
        Log.i(TAG, "hvacCallback propertyId:" + i);
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
                caseFeatureIds(3341, Integer.valueOf(hvacPacket.getSupportFragranceTypeOne()));
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
                caseFeatureIds(3519, Integer.valueOf(hvacPacket.getSupportFragranceTypeTwo()));
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
                caseFeatureIds(3550, Integer.valueOf(hvacPacket.getSupportFragranceTypeThree()));
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
                break;
            case FragranceHalId.ID_FRAGRANCE_RESIDUE_ONE /* 557858818 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener5 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener5.onFragranceResidueChanged(1, hvacPacket.getFragranceResidueOne());
                    }
                }
                caseFeatureIds(3540, Integer.valueOf(hvacPacket.getFragranceResidueOne()));
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
                break;
            case FragranceHalId.ID_FRAGRANCE_RESIDUE_TWO /* 557858821 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener7 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener7.onFragranceResidueChanged(2, hvacPacket.getFragranceResidueTwo());
                    }
                }
                caseFeatureIds(3544, Integer.valueOf(hvacPacket.getFragranceResidueTwo()));
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
                caseFeatureIds(3548, Integer.valueOf(hvacPacket.getFragranceResidueThree()));
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
                caseFeatureIds(3541, Integer.valueOf(hvacPacket.getFragranceLessAllowance()));
                break;
            case FragranceHalId.ID_FRAGRANCE_NAME_ONE_ABR /* 557858825 */:
                this.fragranceNameOneAbR = hvacPacket.getFragranceNameOneAbR();
                Iterator<AbsBYDAutoFragranceListener> it = this.mAbsBydAutoFragranceListeners.iterator();
                while (it.hasNext()) {
                    it.next().onFragranceNameChanged(1, this.fragranceNameOneAbR);
                }
                break;
            case FragranceHalId.ID_FRAGRANCE_NAME_ONE_BBR /* 557858827 */:
                if (this.fragranceNameOneAbR == 32) {
                    Iterator<AbsBYDAutoFragranceListener> it2 = this.mAbsBydAutoFragranceListeners.iterator();
                    while (it2.hasNext()) {
                        it2.next().onFragranceNameChanged(1, hvacPacket.getFragranceNameOneBbR());
                    }
                }
                break;
            case FragranceHalId.ID_FRAGRANCE_NAME_TWO_ABR /* 557858833 */:
                this.fragranceNameTwoAbR = hvacPacket.getFragranceNameTwoAbR();
                Iterator<AbsBYDAutoFragranceListener> it3 = this.mAbsBydAutoFragranceListeners.iterator();
                while (it3.hasNext()) {
                    it3.next().onFragranceNameChanged(2, this.fragranceNameTwoAbR);
                }
                break;
            case FragranceHalId.ID_FRAGRANCE_NAME_TWO_BBR /* 557858835 */:
                if (this.fragranceNameTwoAbR == 32) {
                    Iterator<AbsBYDAutoFragranceListener> it4 = this.mAbsBydAutoFragranceListeners.iterator();
                    while (it4.hasNext()) {
                        it4.next().onFragranceNameChanged(2, hvacPacket.getFragranceNameTwoBbR());
                    }
                }
                break;
            case FragranceHalId.ID_FRAGRANCE_NAME_THREE_ABR /* 557858841 */:
                this.fragranceNameThreeAbR = hvacPacket.getFragranceNameThreeAbR();
                Iterator<AbsBYDAutoFragranceListener> it5 = this.mAbsBydAutoFragranceListeners.iterator();
                while (it5.hasNext()) {
                    it5.next().onFragranceNameChanged(3, this.fragranceNameThreeAbR);
                }
                break;
            case FragranceHalId.ID_FRAGRANCE_NAME_THREE_BBR /* 557858843 */:
                if (this.fragranceNameThreeAbR == 32) {
                    Iterator<AbsBYDAutoFragranceListener> it6 = this.mAbsBydAutoFragranceListeners.iterator();
                    while (it6.hasNext()) {
                        it6.next().onFragranceNameChanged(3, hvacPacket.getFragranceNameThreeBbR());
                    }
                }
                break;
            case FragranceHalId.ID_FRAGRANCE_LESS_ALLOWANCE_TWO_R /* 557858848 */:
                for (AbsBYDAutoFragranceListener absBYDAutoFragranceListener10 : this.mAbsBydAutoFragranceListeners) {
                    if (i2 == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        absBYDAutoFragranceListener10.onFragranceLessAllowance(2, hvacPacket.getFragranceLessAllowanceTwoR());
                    }
                }
                caseFeatureIds(3545, Integer.valueOf(hvacPacket.getFragranceLessAllowanceTwoR()));
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
                caseFeatureIds(3549, Integer.valueOf(hvacPacket.getFragranceLessAllowanceThreeR()));
                break;
        }
    }

    public BYDAutoEventValue get(int[] iArr, Class<?> cls) {
        BYDAutoEventValue bYDAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bYDAutoEventValue.intArrayValue = new int[iArr.length];
        if (iArr.length < 1) {
            bYDAutoEventValue.intValue = -2147482648;
        } else {
            for (int i : iArr) {
                if (i > 0) {
                    if (i == 3329) {
                        int curFragranceType = getCurFragranceType();
                        bYDAutoEventValue.intValue = curFragranceType;
                        sparseArray.put(3329, Integer.valueOf(curFragranceType));
                    } else if (i == 3341) {
                        int fragranceInstallState = getFragranceInstallState(1);
                        bYDAutoEventValue.intValue = fragranceInstallState;
                        sparseArray.put(3341, Integer.valueOf(fragranceInstallState));
                    } else if (i == 3444) {
                        int fragranceConcentration = getFragranceConcentration(3);
                        bYDAutoEventValue.intValue = fragranceConcentration;
                        sparseArray.put(3444, Integer.valueOf(fragranceConcentration));
                    } else if (i == 3519) {
                        int fragranceInstallState2 = getFragranceInstallState(2);
                        bYDAutoEventValue.intValue = fragranceInstallState2;
                        sparseArray.put(3519, Integer.valueOf(fragranceInstallState2));
                    } else if (i == 3540) {
                        int fragranceResidue = getFragranceResidue(1);
                        bYDAutoEventValue.intValue = fragranceResidue;
                        sparseArray.put(3540, Integer.valueOf(fragranceResidue));
                    } else if (i == 3541) {
                        int fragranceLessAllowanceState = getFragranceLessAllowanceState(1);
                        bYDAutoEventValue.intValue = fragranceLessAllowanceState;
                        sparseArray.put(3541, Integer.valueOf(fragranceLessAllowanceState));
                    } else if (i == 3544) {
                        int fragranceResidue2 = getFragranceResidue(2);
                        bYDAutoEventValue.intValue = fragranceResidue2;
                        sparseArray.put(3544, Integer.valueOf(fragranceResidue2));
                    } else if (i != 3545) {
                        switch (i) {
                            case 3548:
                                int fragranceResidue3 = getFragranceResidue(3);
                                bYDAutoEventValue.intValue = fragranceResidue3;
                                sparseArray.put(3548, Integer.valueOf(fragranceResidue3));
                                break;
                            case 3549:
                                int fragranceLessAllowanceState2 = getFragranceLessAllowanceState(3);
                                bYDAutoEventValue.intValue = fragranceLessAllowanceState2;
                                sparseArray.put(3549, Integer.valueOf(fragranceLessAllowanceState2));
                                break;
                            case 3550:
                                int fragranceInstallState3 = getFragranceInstallState(3);
                                bYDAutoEventValue.intValue = fragranceInstallState3;
                                sparseArray.put(3550, Integer.valueOf(fragranceInstallState3));
                                break;
                            default:
                                switch (i) {
                                    case 4550:
                                        int fragranceName = getFragranceName(1);
                                        bYDAutoEventValue.intValue = fragranceName;
                                        sparseArray.put(4550, Integer.valueOf(fragranceName));
                                        break;
                                    case 4551:
                                        int fragranceName2 = getFragranceName(2);
                                        bYDAutoEventValue.intValue = fragranceName2;
                                        sparseArray.put(4551, Integer.valueOf(fragranceName2));
                                        break;
                                    case 4552:
                                        int fragranceName3 = getFragranceName(3);
                                        bYDAutoEventValue.intValue = fragranceName3;
                                        sparseArray.put(4552, Integer.valueOf(fragranceName3));
                                        break;
                                }
                                break;
                        }
                    } else {
                        int fragranceLessAllowanceState3 = getFragranceLessAllowanceState(2);
                        bYDAutoEventValue.intValue = fragranceLessAllowanceState3;
                        sparseArray.put(3545, Integer.valueOf(fragranceLessAllowanceState3));
                    }
                    int i2 = 0;
                    for (int i3 : iArr) {
                        try {
                            bYDAutoEventValue.intArrayValue[i2] = ((Integer) sparseArray.get(i3)).intValue();
                        } catch (Exception unused) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        i2++;
                    }
                } else {
                    bYDAutoEventValue.intValue = -2147482648;
                }
            }
        }
        return bYDAutoEventValue;
    }

    public int getCurFragranceType() {
        Log.v(TAG, "getCurFragranceType is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getCurFragranceType();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getCurFragranceType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getFragranceConcentration(int i) {
        Log.v(TAG, "getFragranceConcentration is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getFragranceConcentration mForTestValue is" + this.mForTest + " execute return");
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
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFragranceType mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFragranceInstallState(i);
        }
        Log.v(TAG, "adapter is not connect");
        return 0;
    }

    public int getFragranceLessAllowanceState(int i) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getFragranceLessAllowanceState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
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

    public int getFragranceName(int i) {
        Log.v(TAG, "getFragranceName is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getFragranceName mForTestValue is" + this.mForTest + " execute return");
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
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mOnline;
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getFragranceOnlineState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getFragranceResidue(int i) {
        Log.v(TAG, "getFragranceResidue is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getFragranceResidue mForTestValue is" + this.mForTest + " execute return");
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
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getFragranceSystemState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getFragranceSystemState mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public int getGreenSystemState() {
        Log.v(TAG, "getGreenSystemState is execute");
        if (1 != this.mForTest) {
            if (this.mTsManagerImpl.getHvacAdapterManager() != null) {
                return this.mTsManagerImpl.getHvacAdapterManager().getGreenSystemState();
            }
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        Log.v(TAG, "getGreenSystemState mForTestValue is" + this.mForTest + " execute return");
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

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int i, Object obj) {
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
                    Log.v(TAG, "the type of value is not a suitable type");
                    return -1;
                }
            }
            iIntValue = 0;
        }
        if (i == 3330) {
            setFragranceType(iIntValue);
            return 1;
        }
        if (i != 3445) {
            Log.v(TAG, "case fail ! please check out");
            return -1;
        }
        setFragranceConcentration(iIntValue);
        return 1;
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
        this.mTestCallback.b(2001, i);
        Log.v(TAG, "setFragranceConcentration mForTestValue is" + this.mForTest + " execute return");
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
        this.mTestCallback.b(2000, i);
        Log.v(TAG, "setFragranceType mForTestValue is" + this.mForTest + " execute return");
        return 0;
    }

    public void unregisterListener(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        Log.d(TAG, "unregisterListener is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.c(absBYDAutoFragranceListener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribeFragranceCallback: " + absBYDAutoFragranceListener);
        if (absBYDAutoFragranceListener == null) {
            return;
        }
        this.mAbsBydAutoFragranceListeners.remove(absBYDAutoFragranceListener);
        unregisterConnectListener(absBYDAutoFragranceListener);
    }

    public void registerListener(AbsBYDAutoFragranceListener absBYDAutoFragranceListener, int[] iArr) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe fragrance listener " + absBYDAutoFragranceListener + " featureIDs = " + Arrays.toString(iArr));
        if (absBYDAutoFragranceListener == null) {
            return;
        }
        if (1 != this.mForTest) {
            if (!this.mAbsBydAutoFragranceListeners.contains(absBYDAutoFragranceListener)) {
                this.mAbsBydAutoFragranceListeners.add(absBYDAutoFragranceListener);
            }
            setFeatureIds(iArr);
            registerConnectListener(this.mContext, absBYDAutoFragranceListener);
            TsManagerImpl.getInstance(this.mContext).registerHvacCallback();
            return;
        }
        this.mTestCallback.d(absBYDAutoFragranceListener);
        Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
    }
}
