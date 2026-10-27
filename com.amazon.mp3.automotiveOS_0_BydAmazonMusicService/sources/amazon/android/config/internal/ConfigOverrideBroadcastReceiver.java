package amazon.android.config.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import com.amazon.avod.util.DLog;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ConfigOverrideBroadcastReceiver extends BroadcastReceiver {
    private final Supplier<SharedPreferences> mOverrideSupplier;

    public ConfigOverrideBroadcastReceiver(Supplier<SharedPreferences> supplier) {
        this.mOverrideSupplier = Suppliers.memoize(supplier);
    }

    public void register(Context context) {
        context.registerReceiver(this, new IntentFilter("avodoverrideconfig"));
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("key");
        String stringExtra2 = intent.getStringExtra("value");
        if (stringExtra != null && stringExtra2 != null) {
            putConfig(stringExtra, stringExtra2);
            printOverrides();
            return;
        }
        String stringExtra3 = intent.getStringExtra("remove");
        if (stringExtra3 != null) {
            deleteConfig(stringExtra3);
            printOverrides();
            return;
        }
        String stringExtra4 = intent.getStringExtra("action");
        if (stringExtra4 != null) {
            if (stringExtra4.equals("clear")) {
                clear();
                printOverrides();
            } else {
                if (stringExtra4.equals("print")) {
                    printOverrides();
                    return;
                }
                DLog.errorf("ConfigOverride intent action unrecognized. Acceptable action commands are \"print\" and \"clear\"");
                DLog.errorf("ConfigOverride Acceptable key action is --es key {key} --es value {value}");
                DLog.errorf("ConfigOverride Acceptable remove action is --es remove {key}");
            }
        }
    }

    private void printOverrides() {
        Map<String, ?> all = this.mOverrideSupplier.get().getAll();
        DLog.errorf("------------------------ ConfigOverride Printing all config overrides ------------------------");
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            DLog.errorf("ConfigOverride key: %s value: %s", entry.getKey(), entry.getValue());
        }
        DLog.errorf("------------------------ ConfigOverride Done printing all config overrides ------------------------");
    }

    private void putConfig(String str, String str2) {
        DLog.errorf("ConfigOverride adding override for key=%s, value=%s", str, str2);
        this.mOverrideSupplier.get().edit().putString(str, str2).commit();
    }

    private void clear() {
        DLog.errorf("ConfigOverride removing all overrides");
        this.mOverrideSupplier.get().edit().clear().commit();
    }

    private void deleteConfig(String str) {
        DLog.errorf("ConfigOverride removing override for key=%s", str);
        this.mOverrideSupplier.get().edit().remove(str).commit();
    }
}
