package amazon.android.config;

import android.content.SharedPreferences;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public interface ConfigEditor {
    boolean contains(String str);

    Map<String, Object> getAll();

    boolean getBooleanConfig(String str, boolean z);

    float getFloatConfig(String str, float f);

    int getIntConfig(String str, int i);

    long getLongConfig(String str, long j);

    SharedPreferences getSharedPreferences();

    String getStringConfig(String str, String str2);

    void remove(String str);

    void setBooleanConfig(String str, String str2);

    void setFloatConfig(String str, String str2);

    void setIntConfig(String str, String str2);

    void setLongConfig(String str, String str2);

    void setStringConfig(String str, String str2);
}
