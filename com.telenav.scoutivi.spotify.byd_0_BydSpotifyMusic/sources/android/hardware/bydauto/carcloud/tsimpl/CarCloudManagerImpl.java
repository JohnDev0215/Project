package android.hardware.bydauto.carcloud.tsimpl;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.bydauto.doorlock.tsimpl.DoorlockManagerImpl;
import android.provider.Settings;
import com.ts.lib.caradapter.CarAdapterManager;
import com.ts.lib.caradapter.body.CarBodyManager;
import com.ts.lib.caradapter.interfaces.OnCarAdapterConnectListener;

/* JADX INFO: loaded from: classes.dex */
public class CarCloudManagerImpl {
    private static final int S_FOR_TEST_VALUE = 0;
    private static final String S_KEY_FOR_TEST = "key_for_test_ChargingManagerImpl";
    private static final String TAG = "ChargingManagerImpl";
    private static volatile CarCloudManagerImpl mInstance;
    private CarBodyManager mBodyManager;
    private CarAdapterManager mCarAdapterManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private int mForTest;

    private CarCloudManagerImpl(Context context) {
        this.mForTest = 0;
        ContentResolver contentResolver = context.getContentResolver();
        this.mContentResolver = contentResolver;
        if (contentResolver != null) {
            int i7 = Settings.System.getInt(contentResolver, S_KEY_FOR_TEST, 0);
            this.mForTest = i7;
            if (i7 == 0) {
                return;
            }
        }
        this.mCarAdapterManager = CarAdapterManager.getInstance(context);
        this.mCarAdapterManager.setOnManagerConnChangedListener(new OnCarAdapterConnectListener() { // from class: android.hardware.bydauto.carcloud.tsimpl.CarCloudManagerImpl.1
            public void onBinderStateChanged(boolean z6) {
                CarCloudManagerImpl carCloudManagerImpl = CarCloudManagerImpl.this;
                carCloudManagerImpl.mBodyManager = (CarBodyManager) carCloudManagerImpl.mCarAdapterManager.getCarAdapterManager("general");
            }
        });
        this.mCarAdapterManager.connect();
    }

    public static CarCloudManagerImpl getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DoorlockManagerImpl.class) {
                if (mInstance == null) {
                    mInstance = new CarCloudManagerImpl(context);
                }
            }
        }
        return mInstance;
    }
}
