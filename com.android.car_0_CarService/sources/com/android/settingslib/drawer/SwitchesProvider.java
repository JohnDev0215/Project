package com.android.settingslib.drawer;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public abstract class SwitchesProvider extends ContentProvider {
    public static final String EXTRA_SWITCH_CHECKED_STATE = "checked_state";
    public static final String EXTRA_SWITCH_DATA = "switch_data";
    public static final String EXTRA_SWITCH_SET_CHECKED_ERROR = "set_checked_error";
    public static final String EXTRA_SWITCH_SET_CHECKED_ERROR_MESSAGE = "set_checked_error_message";
    public static final String METHOD_GET_DYNAMIC_SUMMARY = "getDynamicSummary";
    public static final String METHOD_GET_DYNAMIC_TITLE = "getDynamicTitle";
    public static final String METHOD_GET_PROVIDER_ICON = "getProviderIcon";
    public static final String METHOD_GET_SWITCH_DATA = "getSwitchData";
    public static final String METHOD_IS_CHECKED = "isChecked";
    public static final String METHOD_ON_CHECKED_CHANGED = "onCheckedChanged";
    private static final String TAG = "SwitchesProvider";
    private String mAuthority;
    private final Map<String, SwitchController> mControllerMap = new LinkedHashMap();
    private final List<Bundle> mSwitchDataList = new ArrayList();

    protected abstract List<SwitchController> createSwitchControllers();

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo info) {
        String str = info.authority;
        this.mAuthority = str;
        Log.i(TAG, str);
        super.attachInfo(context, info);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        List<SwitchController> controllers = createSwitchControllers();
        if (controllers == null || controllers.isEmpty()) {
            throw new IllegalArgumentException();
        }
        controllers.forEach(new Consumer() { // from class: com.android.settingslib.drawer.-$$Lambda$SwitchesProvider$Todk-P6pI0mvKmdxKm2pz5ruW68
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$onCreate$0$SwitchesProvider((SwitchController) obj);
            }
        });
        return true;
    }

    public /* synthetic */ void lambda$onCreate$0$SwitchesProvider(SwitchController controller) {
        String key = controller.getSwitchKey();
        if (TextUtils.isEmpty(key)) {
            throw new NullPointerException("Switch key cannot be null: " + controller.getClass().getSimpleName());
        }
        if (this.mControllerMap.containsKey(key)) {
            throw new IllegalArgumentException("Switch key " + key + " is duplicated by: " + controller.getClass().getSimpleName());
        }
        controller.setAuthority(this.mAuthority);
        this.mControllerMap.put(key, controller);
        if (!(controller instanceof MasterSwitchController)) {
            this.mSwitchDataList.add(controller.getBundle());
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.content.ContentProvider
    public Bundle call(String method, String uriString, Bundle extras) {
        String key;
        Bundle bundle = new Bundle();
        if (extras != null) {
            key = extras.getString(TileUtils.META_DATA_PREFERENCE_KEYHINT);
        } else {
            key = null;
        }
        if (TextUtils.isEmpty(key)) {
            if (!METHOD_GET_SWITCH_DATA.equals(method)) {
                return null;
            }
            bundle.putParcelableList(EXTRA_SWITCH_DATA, this.mSwitchDataList);
            return bundle;
        }
        SwitchController switchController = this.mControllerMap.get(key);
        if (switchController == 0) {
            return null;
        }
        byte b = -1;
        switch (method.hashCode()) {
            case -2097433649:
                if (method.equals(METHOD_GET_DYNAMIC_TITLE)) {
                    b = 2;
                }
                break;
            case -1844463779:
                if (method.equals(METHOD_GET_DYNAMIC_SUMMARY)) {
                    b = 3;
                }
                break;
            case -952092468:
                if (method.equals(METHOD_ON_CHECKED_CHANGED)) {
                    b = 5;
                }
                break;
            case -645523212:
                if (method.equals(METHOD_GET_SWITCH_DATA)) {
                    b = 0;
                }
                break;
            case 162535197:
                if (method.equals(METHOD_IS_CHECKED)) {
                    b = 4;
                }
                break;
            case 1207101568:
                if (method.equals(METHOD_GET_PROVIDER_ICON)) {
                    b = 1;
                }
                break;
        }
        if (b != 0) {
            if (b != 1) {
                if (b != 2) {
                    if (b != 3) {
                        if (b == 4) {
                            bundle.putBoolean(EXTRA_SWITCH_CHECKED_STATE, switchController.isChecked());
                            return bundle;
                        }
                        if (b == 5) {
                            return onCheckedChanged(extras.getBoolean(EXTRA_SWITCH_CHECKED_STATE), switchController);
                        }
                    } else if (switchController instanceof DynamicSummary) {
                        bundle.putString(TileUtils.META_DATA_PREFERENCE_SUMMARY, ((DynamicSummary) switchController).getDynamicSummary());
                        return bundle;
                    }
                } else if (switchController instanceof DynamicTitle) {
                    bundle.putString(TileUtils.META_DATA_PREFERENCE_TITLE, ((DynamicTitle) switchController).getDynamicTitle());
                    return bundle;
                }
            } else if (switchController instanceof ProviderIcon) {
                return ((ProviderIcon) switchController).getProviderIcon();
            }
        } else if (!(switchController instanceof MasterSwitchController)) {
            return switchController.getBundle();
        }
        return null;
    }

    private Bundle onCheckedChanged(boolean checked, SwitchController controller) {
        boolean success = controller.onCheckedChanged(checked);
        Bundle bundle = new Bundle();
        bundle.putBoolean(EXTRA_SWITCH_SET_CHECKED_ERROR, !success);
        if (success) {
            if (controller instanceof DynamicSummary) {
                controller.notifySummaryChanged(getContext());
            }
        } else {
            bundle.putString(EXTRA_SWITCH_SET_CHECKED_ERROR_MESSAGE, controller.getErrorMessage(checked));
        }
        return bundle;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }
}
