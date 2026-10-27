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
import android.util.SparseArray;
import com.ts.lib.caradapter.hvac.HvacPacket;
import com.ts.lib.caradapter.interfaces.OnHvacChangedListener;
import java.util.Arrays;
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
    private TestFragranceManagerImplCallback mTestCallback;
    private TsManagerImpl mTsManagerImpl;
    private final CopyOnWriteArrayList<AbsBYDAutoFragranceListener> mAbsBydAutoFragranceListeners = new CopyOnWriteArrayList<>();
    private int mOnline = 2;
    private BYDAutoEventValue bydAutoEventValueListener = new BYDAutoEventValue();

    FragranceManagerImpl(Context con) {
        this.mForTest = 0;
        this.mContext = con;
        ContentResolver contentResolver = con.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i;
            if (1 == i) {
                this.mTestCallback = new TestFragranceManagerImplCallback();
                Log.v(TAG, "FragranceManagerImpl mForTestValue is" + this.mForTest + " execute return");
                return;
            }
        }
        TsManagerImpl tsManagerImpl = TsManagerImpl.getInstance(con);
        this.mTsManagerImpl = tsManagerImpl;
        tsManagerImpl.setOnHvacChangedListener(new OnHvacChangedListener() { // from class: android.hardware.bydauto.fragrance.tsimpl.FragranceManagerImpl.1
            public void onHvacChanged(int id, int statusId, HvacPacket packet) {
                if (!FragranceManagerImpl.this.mAbsBydAutoFragranceListeners.isEmpty()) {
                    try {
                        FragranceManagerImpl.this.hvacCallback(id, statusId, packet);
                    } catch (Exception e) {
                        Log.e(FragranceManagerImpl.TAG, "hvacCallback is error");
                    }
                }
            }
        });
    }

    public static FragranceManagerImpl getInstance(Context con) {
        if (sInstance == null) {
            synchronized (FragranceManagerImpl.class) {
                if (sInstance == null) {
                    sInstance = new FragranceManagerImpl(con);
                }
            }
        }
        return sInstance;
    }

    public int getFragranceConcentration(int type) {
        Log.v(TAG, "getFragranceConcentration is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getFragranceConcentration mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            int fragranceDegree = this.mTsManagerImpl.getHvacAdapterManager().getFragranceConcentrationTypeOne();
            return fragranceDegree;
        }
        if (type == 2) {
            int fragranceDegree2 = this.mTsManagerImpl.getHvacAdapterManager().getFragranceConcentrationTypeTwo();
            return fragranceDegree2;
        }
        if (type != 3) {
            return 0;
        }
        int fragranceDegree3 = this.mTsManagerImpl.getHvacAdapterManager().getFragranceConcentrationTypeThree();
        return fragranceDegree3;
    }

    public int getCurFragranceType() {
        Log.v(TAG, "getCurFragranceType is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getCurFragranceType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getCurFragranceType();
    }

    public int getFragranceResidue(int type) {
        Log.v(TAG, "getFragranceResidue is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getFragranceResidue mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            int fragrance = this.mTsManagerImpl.getHvacAdapterManager().getFragranceResidueOne();
            return fragrance;
        }
        if (type == 2) {
            int fragrance2 = this.mTsManagerImpl.getHvacAdapterManager().getFragranceResidueTwo();
            return fragrance2;
        }
        if (type != 3) {
            return 0;
        }
        int fragrance3 = this.mTsManagerImpl.getHvacAdapterManager().getFragranceResidueThree();
        return fragrance3;
    }

    public int setFragranceConcentration(int value) {
        Log.v(TAG, "setFragranceConcentration is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(2001, value);
            Log.v(TAG, "setFragranceConcentration mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setFragranceConcentration(value);
    }

    public int setFragranceType(int type) {
        Log.v(TAG, "setFragranceType is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.enqueueMessage(2000, type);
            Log.v(TAG, "setFragranceType mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().setFragranceType(type);
    }

    public int getGreenSystemState() {
        Log.v(TAG, "getGreenSystemState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getGreenSystemState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getGreenSystemState();
    }

    public int getFragranceSystemState() {
        Log.v(TAG, "getFragranceSystemState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getFragranceSystemState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getFragranceSystemState();
    }

    public void registerListener(AbsBYDAutoFragranceListener listener) {
        registerListener(listener, new int[0]);
    }

    public void registerListener(AbsBYDAutoFragranceListener listener, int[] featureIds) {
        android.util.Log.i(TAG, this.mContext.getPackageName() + " byd_subscribe fragrance listener " + listener + " featureIDs = " + Arrays.toString(featureIds));
        if (listener == null) {
            return;
        }
        if (1 == this.mForTest) {
            this.mTestCallback.setAbsBydAutoFragranceListener(listener);
            Log.d(TAG, "registerListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        if (!this.mAbsBydAutoFragranceListeners.contains(listener)) {
            this.mAbsBydAutoFragranceListeners.add(listener);
        }
        setFeatureIds(featureIds);
        registerConnectListener(this.mContext, listener);
        TsManagerImpl.getInstance(this.mContext).registerHvacCallback();
    }

    public void unregisterListener(AbsBYDAutoFragranceListener listener) {
        Log.d(TAG, "unregisterListener is execute");
        if (1 == this.mForTest) {
            this.mTestCallback.removeListener(listener);
            Log.d(TAG, "unregisterListener mForTestValue is" + this.mForTest + " execute return");
            return;
        }
        Log.d(TAG, "unsubscribeFragranceCallback: " + listener);
        if (listener == null) {
            return;
        }
        this.mAbsBydAutoFragranceListeners.remove(listener);
        unregisterConnectListener(listener);
    }

    public int getFragranceOnlineState() {
        Log.v(TAG, "getFragranceOnlineState is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getFragranceOnlineState mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        return this.mOnline;
    }

    public int getFragranceName(int type) {
        Log.v(TAG, "getFragranceName is execute");
        if (1 == this.mForTest) {
            Log.v(TAG, "getFragranceName mForTestValue is" + this.mForTest + " execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.e(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            int firstUnicodeLowerBits = this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameOneAb();
            if (firstUnicodeLowerBits != 32) {
                return firstUnicodeLowerBits;
            }
            int secondUnicodeLowerBits = this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameOneBb();
            return secondUnicodeLowerBits;
        }
        if (type == 2) {
            int firstUnicodeLowerBits2 = this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameTwoAb();
            if (firstUnicodeLowerBits2 != 32) {
                return firstUnicodeLowerBits2;
            }
            int secondUnicodeLowerBits2 = this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameTwoBb();
            return secondUnicodeLowerBits2;
        }
        if (type != 3) {
            return 0;
        }
        int firstUnicodeLowerBits3 = this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameThreeAb();
        if (firstUnicodeLowerBits3 != 32) {
            return firstUnicodeLowerBits3;
        }
        int secondUnicodeLowerBits3 = this.mTsManagerImpl.getHvacAdapterManager().getFragranceNameThreeBb();
        return secondUnicodeLowerBits3;
    }

    public int getFragranceInstallState(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getSupportFragranceType mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getFragranceInstallState(type);
    }

    public int getFragranceLessAllowanceState(int type) {
        if (1 == this.mForTest) {
            Log.v(TAG, "getFragranceLessAllowanceState mForTestValue is 0 execute return");
            return 0;
        }
        if (this.mTsManagerImpl.getHvacAdapterManager() == null) {
            Log.v(TAG, "adapter is not connect");
            return 0;
        }
        if (type == 1) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFragranceLessAllowanceStateOne();
        }
        if (type == 2) {
            return this.mTsManagerImpl.getHvacAdapterManager().getFragranceLessAllowanceStateTwo();
        }
        if (type != 3) {
            return 0;
        }
        return this.mTsManagerImpl.getHvacAdapterManager().getFragranceLessAllowanceStateThree();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hvacCallback(int id, int statusId, HvacPacket packet) {
        Log.i(TAG, "hvacCallback propertyId:" + id);
        switch (id) {
            case 557856028:
                for (AbsBYDAutoFragranceListener listener : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener.onFragranceInstallStateChanged(1, packet.getSupportFragranceTypeOne());
                    }
                }
                caseFeatureIds(3341, Integer.valueOf(packet.getSupportFragranceTypeOne()));
                break;
            case 557856029:
                for (AbsBYDAutoFragranceListener listener2 : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener2.onFragranceInstallStateChanged(2, packet.getSupportFragranceTypeTwo());
                    }
                }
                caseFeatureIds(3519, Integer.valueOf(packet.getSupportFragranceTypeTwo()));
                break;
            case 557856030:
                for (AbsBYDAutoFragranceListener listener3 : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener3.onFragranceInstallStateChanged(3, packet.getSupportFragranceTypeThree());
                    }
                }
                caseFeatureIds(3550, Integer.valueOf(packet.getSupportFragranceTypeThree()));
                break;
            case 557858816:
                for (AbsBYDAutoFragranceListener listener4 : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener4.onFragranceTypeChanged(packet.getCurFragranceType());
                    }
                }
                break;
            case 557858818:
                for (AbsBYDAutoFragranceListener listener5 : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener5.onFragranceResidueChanged(1, packet.getFragranceResidueOne());
                    }
                }
                caseFeatureIds(3540, Integer.valueOf(packet.getFragranceResidueOne()));
                break;
            case 557858819:
                for (AbsBYDAutoFragranceListener listener6 : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener6.onFragranceConcentrationChanged(packet.getCurFragranceType(), packet.getFragranceConcentrationR());
                    }
                }
                break;
            case 557858821:
                for (AbsBYDAutoFragranceListener listener7 : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener7.onFragranceResidueChanged(2, packet.getFragranceResidueTwo());
                    }
                }
                caseFeatureIds(3544, Integer.valueOf(packet.getFragranceResidueTwo()));
                break;
            case 557858822:
                for (AbsBYDAutoFragranceListener listener8 : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener8.onFragranceResidueChanged(3, packet.getFragranceResidueThree());
                    }
                }
                caseFeatureIds(3548, Integer.valueOf(packet.getFragranceResidueThree()));
                break;
            case 557858823:
                for (AbsBYDAutoFragranceListener listener9 : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener9.onFragranceLessAllowance(1, packet.getFragranceLessAllowance());
                    }
                }
                caseFeatureIds(3541, Integer.valueOf(packet.getFragranceLessAllowance()));
                break;
            case 557858825:
                this.fragranceNameOneAbR = packet.getFragranceNameOneAbR();
                for (AbsBYDAutoFragranceListener listener10 : this.mAbsBydAutoFragranceListeners) {
                    listener10.onFragranceNameChanged(1, this.fragranceNameOneAbR);
                }
                break;
            case 557858827:
                if (this.fragranceNameOneAbR == 32) {
                    for (AbsBYDAutoFragranceListener listener11 : this.mAbsBydAutoFragranceListeners) {
                        listener11.onFragranceNameChanged(1, packet.getFragranceNameOneBbR());
                    }
                }
                break;
            case 557858833:
                this.fragranceNameTwoAbR = packet.getFragranceNameTwoAbR();
                for (AbsBYDAutoFragranceListener listener12 : this.mAbsBydAutoFragranceListeners) {
                    listener12.onFragranceNameChanged(2, this.fragranceNameTwoAbR);
                }
                break;
            case 557858835:
                if (this.fragranceNameTwoAbR == 32) {
                    for (AbsBYDAutoFragranceListener listener13 : this.mAbsBydAutoFragranceListeners) {
                        listener13.onFragranceNameChanged(2, packet.getFragranceNameTwoBbR());
                    }
                }
                break;
            case 557858841:
                this.fragranceNameThreeAbR = packet.getFragranceNameThreeAbR();
                for (AbsBYDAutoFragranceListener listener14 : this.mAbsBydAutoFragranceListeners) {
                    listener14.onFragranceNameChanged(3, this.fragranceNameThreeAbR);
                }
                break;
            case 557858843:
                if (this.fragranceNameThreeAbR == 32) {
                    for (AbsBYDAutoFragranceListener listener15 : this.mAbsBydAutoFragranceListeners) {
                        listener15.onFragranceNameChanged(3, packet.getFragranceNameThreeBbR());
                    }
                }
                break;
            case 557858848:
                for (AbsBYDAutoFragranceListener listener16 : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener16.onFragranceLessAllowance(2, packet.getFragranceLessAllowanceTwoR());
                    }
                }
                caseFeatureIds(3545, Integer.valueOf(packet.getFragranceLessAllowanceTwoR()));
                break;
            case 557858849:
                for (AbsBYDAutoFragranceListener listener17 : this.mAbsBydAutoFragranceListeners) {
                    if (statusId == 2) {
                        this.mOnline = 2;
                    } else {
                        this.mOnline = 1;
                        listener17.onFragranceLessAllowance(3, packet.getFragranceLessAllowanceThreeR());
                    }
                }
                caseFeatureIds(3549, Integer.valueOf(packet.getFragranceLessAllowanceThreeR()));
                break;
        }
    }

    public BYDAutoEventValue get(int[] eventTypes, Class<?> type) {
        BYDAutoEventValue bydAutoEventValue = new BYDAutoEventValue();
        SparseArray sparseArray = new SparseArray();
        bydAutoEventValue.intArrayValue = new int[eventTypes.length];
        int i = 1;
        if (eventTypes.length < 1) {
            bydAutoEventValue.intValue = -2147482648;
        } else {
            int length = eventTypes.length;
            int i2 = 0;
            while (i2 < length) {
                int eventType = eventTypes[i2];
                if (eventType > 0) {
                    if (eventType != 3329) {
                        if (eventType != 3341) {
                            if (eventType != 3444) {
                                if (eventType != 3519) {
                                    if (eventType != 3540) {
                                        if (eventType != 3541) {
                                            if (eventType != 3544) {
                                                if (eventType == 3545) {
                                                    bydAutoEventValue.intValue = getFragranceLessAllowanceState(2);
                                                    sparseArray.put(3545, Integer.valueOf(bydAutoEventValue.intValue));
                                                } else {
                                                    switch (eventType) {
                                                        case 3548:
                                                            bydAutoEventValue.intValue = getFragranceResidue(3);
                                                            sparseArray.put(3548, Integer.valueOf(bydAutoEventValue.intValue));
                                                            break;
                                                        case 3549:
                                                            bydAutoEventValue.intValue = getFragranceLessAllowanceState(3);
                                                            sparseArray.put(3549, Integer.valueOf(bydAutoEventValue.intValue));
                                                            break;
                                                        case 3550:
                                                            bydAutoEventValue.intValue = getFragranceInstallState(3);
                                                            sparseArray.put(3550, Integer.valueOf(bydAutoEventValue.intValue));
                                                            break;
                                                        default:
                                                            switch (eventType) {
                                                                case 4550:
                                                                    bydAutoEventValue.intValue = getFragranceName(i);
                                                                    sparseArray.put(4550, Integer.valueOf(bydAutoEventValue.intValue));
                                                                    break;
                                                                case 4551:
                                                                    bydAutoEventValue.intValue = getFragranceName(2);
                                                                    sparseArray.put(4551, Integer.valueOf(bydAutoEventValue.intValue));
                                                                    break;
                                                                case 4552:
                                                                    bydAutoEventValue.intValue = getFragranceName(3);
                                                                    sparseArray.put(4552, Integer.valueOf(bydAutoEventValue.intValue));
                                                                    break;
                                                            }
                                                            break;
                                                    }
                                                }
                                            } else {
                                                bydAutoEventValue.intValue = getFragranceResidue(2);
                                                sparseArray.put(3544, Integer.valueOf(bydAutoEventValue.intValue));
                                            }
                                        } else {
                                            bydAutoEventValue.intValue = getFragranceLessAllowanceState(i);
                                            sparseArray.put(3541, Integer.valueOf(bydAutoEventValue.intValue));
                                        }
                                    } else {
                                        bydAutoEventValue.intValue = getFragranceResidue(i);
                                        sparseArray.put(3540, Integer.valueOf(bydAutoEventValue.intValue));
                                    }
                                } else {
                                    bydAutoEventValue.intValue = getFragranceInstallState(2);
                                    sparseArray.put(3519, Integer.valueOf(bydAutoEventValue.intValue));
                                }
                            } else {
                                bydAutoEventValue.intValue = getFragranceConcentration(3);
                                sparseArray.put(3444, Integer.valueOf(bydAutoEventValue.intValue));
                            }
                        } else {
                            bydAutoEventValue.intValue = getFragranceInstallState(i);
                            sparseArray.put(3341, Integer.valueOf(bydAutoEventValue.intValue));
                        }
                    } else {
                        bydAutoEventValue.intValue = getCurFragranceType();
                        sparseArray.put(3329, Integer.valueOf(bydAutoEventValue.intValue));
                    }
                    int index = 0;
                    for (int i3 : eventTypes) {
                        try {
                            int getNum = ((Integer) sparseArray.get(i3)).intValue();
                            bydAutoEventValue.intArrayValue[index] = getNum;
                        } catch (Exception e) {
                            Log.e(TAG, "Feature id doesn't exist");
                        }
                        index++;
                    }
                } else {
                    bydAutoEventValue.intValue = -2147482648;
                }
                i2++;
                i = 1;
            }
        }
        return bydAutoEventValue;
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    public void onDataEventChanged(int id, BYDAutoEventValue value) {
        for (AbsBYDAutoFragranceListener listener : this.mAbsBydAutoFragranceListeners) {
            listener.onDataEventChanged(id, value);
        }
    }

    @Override // android.hardware.bydauto.AbsTsMyCar
    protected int setForOneFeatureId(int eventType, Object value) {
        int intValue = 0;
        if (value instanceof Integer) {
            intValue = ((Integer) value).intValue();
        } else if (value instanceof Float) {
            ((Float) value).floatValue();
        } else if (value instanceof Double) {
            ((Double) value).doubleValue();
        } else if (value instanceof byte[]) {
        } else {
            Log.v(TAG, "the type of value is not a suitable type");
            return -1;
        }
        if (eventType == 3330) {
            setFragranceType(intValue);
            return 1;
        }
        if (eventType == 3445) {
            setFragranceConcentration(intValue);
            return 1;
        }
        Log.v(TAG, "case fail ! please check out");
        return -1;
    }
}
