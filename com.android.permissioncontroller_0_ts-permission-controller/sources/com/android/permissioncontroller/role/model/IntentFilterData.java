package com.android.permissioncontroller.role.model;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class IntentFilterData {
    private final String mAction;
    private final List<String> mCategories;
    private final String mDataScheme;
    private final String mDataType;

    public IntentFilterData(String action, List<String> categories, String dataScheme, String dataType) {
        this.mAction = action;
        this.mCategories = categories;
        this.mDataScheme = dataScheme;
        this.mDataType = dataType;
    }

    public String getAction() {
        return this.mAction;
    }

    public List<String> getCategories() {
        return this.mCategories;
    }

    public String getDataScheme() {
        return this.mDataScheme;
    }

    public String getDataType() {
        return this.mDataType;
    }

    public Intent createIntent() {
        Intent intent = new Intent(this.mAction);
        String str = this.mDataScheme;
        Uri data = str != null ? Uri.fromParts(str, "", null) : null;
        int categoriesSize = this.mCategories.size();
        for (int i = 0; i < categoriesSize; i++) {
            String category = this.mCategories.get(i);
            intent.addCategory(category);
        }
        intent.setDataAndType(data, this.mDataType);
        return intent;
    }

    public IntentFilter createIntentFilter() {
        IntentFilter intentFilter = new IntentFilter(this.mAction);
        int categoriesSize = this.mCategories.size();
        for (int i = 0; i < categoriesSize; i++) {
            String category = this.mCategories.get(i);
            intentFilter.addCategory(category);
        }
        String str = this.mDataScheme;
        if (str != null) {
            intentFilter.addDataScheme(str);
        }
        String str2 = this.mDataType;
        if (str2 != null) {
            try {
                intentFilter.addDataType(str2);
            } catch (IntentFilter.MalformedMimeTypeException e) {
                throw new IllegalStateException(e);
            }
        }
        return intentFilter;
    }

    public String toString() {
        return "IntentFilterData{mAction='" + this.mAction + "', mCategories='" + this.mCategories + "', mDataScheme='" + this.mDataScheme + "', mDataType='" + this.mDataType + "'}";
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        IntentFilterData that = (IntentFilterData) object;
        if (Objects.equals(this.mAction, that.mAction) && Objects.equals(this.mCategories, that.mCategories) && Objects.equals(this.mDataScheme, that.mDataScheme) && Objects.equals(this.mDataType, that.mDataType)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.mAction, this.mCategories, this.mDataScheme, this.mDataType);
    }
}
