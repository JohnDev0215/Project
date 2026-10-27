package com.android.car.settings.common.rotary;

import android.view.KeyEvent;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class NumberPickerNudgeHandler implements View.OnKeyListener {
    private static final Map<Integer, Integer> KEYCODE_TO_DIRECTION_MAP = new HashMap<Integer, Integer>() { // from class: com.android.car.settings.common.rotary.NumberPickerNudgeHandler.1
        {
            put(19, 33);
            put(20, 130);
            put(21, 17);
            put(22, 66);
        }
    };

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 21:
            case 22:
                if (event.getAction() == 1) {
                    int direction = KEYCODE_TO_DIRECTION_MAP.get(Integer.valueOf(keyCode)).intValue();
                    View nextView = view.focusSearch(direction);
                    if (NumberPickerUtils.hasCommonNumberPickerParent(view, nextView)) {
                        nextView.requestFocus(direction);
                    }
                }
                break;
        }
        return true;
    }
}
