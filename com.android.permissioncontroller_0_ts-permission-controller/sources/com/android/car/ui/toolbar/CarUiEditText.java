package com.android.car.ui.toolbar;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.util.AttributeSet;
import android.widget.EditText;
import com.android.car.ui.imewidescreen.CarUiImeWideScreenController;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes7.dex */
class CarUiEditText extends EditText {
    private final Set<PrivateImeCommandCallback> mPrivateImeCommandCallback;

    public CarUiEditText(Context context) {
        super(context);
        this.mPrivateImeCommandCallback = new HashSet();
    }

    public CarUiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPrivateImeCommandCallback = new HashSet();
    }

    public CarUiEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mPrivateImeCommandCallback = new HashSet();
    }

    public CarUiEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mPrivateImeCommandCallback = new HashSet();
    }

    @Override // android.widget.TextView
    public boolean onPrivateIMECommand(String action, Bundle data) {
        if (CarUiImeWideScreenController.WIDE_SCREEN_CLEAR_DATA_ACTION.equals(action)) {
            setText("");
        }
        if (CarUiImeWideScreenController.WIDE_SCREEN_POST_LOAD_SEARCH_RESULTS_ACTION.equals(action)) {
            for (PrivateImeCommandCallback listener : this.mPrivateImeCommandCallback) {
                listener.onPostLoadSearchResults();
            }
        }
        if (data == null || this.mPrivateImeCommandCallback == null) {
            return false;
        }
        if (data.getString(CarUiImeWideScreenController.SEARCH_RESULT_ITEM_ID_LIST) != null) {
            for (PrivateImeCommandCallback listener2 : this.mPrivateImeCommandCallback) {
                listener2.onItemClicked(data.getString(CarUiImeWideScreenController.SEARCH_RESULT_ITEM_ID_LIST));
            }
        }
        if (data.getString(CarUiImeWideScreenController.SEARCH_RESULT_SUPPLEMENTAL_ICON_ID_LIST) != null) {
            for (PrivateImeCommandCallback listener3 : this.mPrivateImeCommandCallback) {
                listener3.onSecondaryImageClicked(data.getString(CarUiImeWideScreenController.SEARCH_RESULT_SUPPLEMENTAL_ICON_ID_LIST));
            }
        }
        int displayId = data.getInt(CarUiImeWideScreenController.CONTENT_AREA_SURFACE_DISPLAY_ID);
        int height = data.getInt(CarUiImeWideScreenController.CONTENT_AREA_SURFACE_HEIGHT);
        int width = data.getInt(CarUiImeWideScreenController.CONTENT_AREA_SURFACE_WIDTH);
        IBinder binder = data.getBinder(CarUiImeWideScreenController.CONTENT_AREA_SURFACE_HOST_TOKEN);
        if (binder != null) {
            for (PrivateImeCommandCallback listener4 : this.mPrivateImeCommandCallback) {
                listener4.onSurfaceInfo(displayId, binder, height, width);
            }
        }
        return false;
    }

    public void registerOnPrivateImeCommandListener(PrivateImeCommandCallback listener) {
        this.mPrivateImeCommandCallback.add(listener);
    }

    public boolean unregisterOnPrivateImeCommandListener(PrivateImeCommandCallback listener) {
        return this.mPrivateImeCommandCallback.remove(listener);
    }
}
