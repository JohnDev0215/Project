package com.ts.car.input;

import android.car.input.CarInputHandlingService;
import android.content.Context;
import android.view.KeyEvent;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class HardKeyInputService extends CarInputHandlingService {
    private static final String TAG = "HardKeyInputService";
    private static CarInputHandlingService.InputFilter[] sInputFilters = new CarInputHandlingService.InputFilter[ConfigUtils.getKeyCodes().size()];
    private Context mContext;
    private HardKeyManagerProxy mManager;

    static {
        int index = 0;
        Iterator<Integer> it = ConfigUtils.getKeyCodes().iterator();
        while (it.hasNext()) {
            int code = it.next().intValue();
            sInputFilters[index] = new CarInputHandlingService.InputFilter(code, 0);
            index++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
        HardKeyManagerProxy hardKeyManagerProxy = HardKeyManagerProxy.getInstance();
        this.mManager = hardKeyManagerProxy;
        hardKeyManagerProxy.init(getApplicationContext());
    }

    public HardKeyInputService() {
        super(sInputFilters);
        this.mManager = null;
        LogUtils.log(TAG, "HardKeyInputService started!");
    }

    protected void onKeyEvent(KeyEvent keyEvent, int targetDisplay) {
        this.mManager.processHardKeyFromPolicy(keyEvent, targetDisplay, this.mContext);
    }
}
