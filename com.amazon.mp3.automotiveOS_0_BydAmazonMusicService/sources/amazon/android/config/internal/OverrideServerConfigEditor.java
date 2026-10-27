package amazon.android.config.internal;

import android.content.SharedPreferences;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes.dex */
public final class OverrideServerConfigEditor extends ServerConfigEditor {
    private final Supplier<SharedPreferences> mConfigOverridePrefSupplier;

    public OverrideServerConfigEditor(@Nonnull Supplier<SharedPreferences> supplier, @Nonnull Supplier<SharedPreferences> supplier2) {
        super(supplier);
        this.mConfigOverridePrefSupplier = Suppliers.memoize(supplier2);
    }

    @Override // amazon.android.config.internal.ServerConfigEditor, amazon.android.config.ConfigEditor
    public String getStringConfig(String str, String str2) {
        try {
            if (this.mConfigOverridePrefSupplier.get().contains(str)) {
                return this.mConfigOverridePrefSupplier.get().getString(str, str2);
            }
        } catch (Exception e) {
            handleReadException(e, str, str2);
        }
        return super.getStringConfig(str, str2);
    }

    @Override // amazon.android.config.internal.ServerConfigEditor, amazon.android.config.ConfigEditor
    public Map<String, Object> getAll() {
        Map<String, Object> all = super.getAll();
        all.putAll(this.mConfigOverridePrefSupplier.get().getAll());
        return all;
    }
}
