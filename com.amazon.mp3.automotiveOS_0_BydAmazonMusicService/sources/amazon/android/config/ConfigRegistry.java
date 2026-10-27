package amazon.android.config;

import amazon.android.config.internal.ConfigOverrideBroadcastReceiver;
import amazon.android.config.internal.DefaultConfigEditor;
import amazon.android.config.internal.OverrideServerConfigEditor;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazon.avod.core.Framework;
import com.amazon.avod.util.DLog;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes.dex */
public class ConfigRegistry {
    private Context mAppContext;
    private Map<ConfigType, ConfigEditor> mConfigEditors;
    private final Supplier<SharedPreferences> mOverridePreferences;
    private final Map<String, ConfigEditor> mProfileEditors = new HashMap();

    @SuppressLint({"StaticFieldLeak"})
    private static final class SingletonHolder {
        public static ConfigRegistry sInstance = new ConfigRegistry();

        private SingletonHolder() {
        }
    }

    public static ConfigRegistry getInstance() {
        return SingletonHolder.sInstance;
    }

    ConfigRegistry() {
        Supplier<SharedPreferences> supplier = new Supplier() { // from class: amazon.android.config.ConfigRegistry$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return this.f$0.lambda$new$0();
            }
        };
        this.mOverridePreferences = supplier;
        ImmutableMap.Builder builderPut = ImmutableMap.builder().put(ConfigType.SERVER, createServerPreferencesEditor("ServerPreferences")).put(ConfigType.ACCOUNT, createUserPreferencesEditor());
        ConfigType configType = ConfigType.INTERNAL;
        ImmutableMap.Builder builderPut2 = builderPut.put(configType, createDefaultPreferencesEditor("InternalPreferences", configType));
        ConfigType configType2 = ConfigType.PERSISTENT;
        ImmutableMap.Builder builderPut3 = builderPut2.put(configType2, createDefaultPreferencesEditor("PersistentPreferences", configType2));
        ConfigType configType3 = ConfigType.SDK;
        ImmutableMap.Builder builderPut4 = builderPut3.put(configType3, createDefaultPreferencesEditor(new SdkSharedPreferencesSupplier(), configType3)).put(ConfigType.PLAYBACK_HEURISTICS, createServerPreferencesEditor("PlaybackHeuristicsPreferences"));
        ConfigType configType4 = ConfigType.LOCALIZATION;
        ImmutableMap.Builder builderPut5 = builderPut4.put(configType4, createDefaultPreferencesEditor("LocalizationPreferences", configType4));
        ConfigType configType5 = ConfigType.DEBUG_OVERRIDES;
        this.mConfigEditors = builderPut5.put(configType5, createDefaultPreferencesEditor(supplier, configType5)).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ SharedPreferences lambda$new$0() {
        Preconditions.checkNotNull(this.mAppContext, "ConfigRegistry must be initialized before accessing the config values.");
        return this.mAppContext.getSharedPreferences("OverrideServerConfigPreferences", 0);
    }

    public void initialize(@Nonnull Context context) {
        this.mAppContext = (Context) Preconditions.checkNotNull(context, "context");
        try {
            if (Framework.isDebugConfigurationEnabled()) {
                new ConfigOverrideBroadcastReceiver(this.mOverridePreferences).register(context);
            }
        } catch (Exception e) {
            DLog.exceptionf(e, "Exception parsing local override config value", new Object[0]);
        }
    }

    @Nonnull
    public ConfigEditor getConfigEditor(@Nonnull ConfigType configType) {
        Preconditions.checkNotNull(configType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        return this.mConfigEditors.get(configType);
    }

    @Nonnull
    private ConfigEditor createServerPreferencesEditor(@Nonnull final String str) {
        return new OverrideServerConfigEditor(new Supplier() { // from class: amazon.android.config.ConfigRegistry$$ExternalSyntheticLambda2
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return this.f$0.lambda$createServerPreferencesEditor$2(str);
            }
        }, this.mOverridePreferences);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ SharedPreferences lambda$createServerPreferencesEditor$2(String str) {
        Preconditions.checkState(this.mAppContext != null, "ConfigRegistry must be initialized before accessing the config values.");
        return this.mAppContext.getSharedPreferences(str, 0);
    }

    @Nonnull
    private ConfigEditor createUserPreferencesEditor() {
        return new DefaultConfigEditor(new Supplier() { // from class: amazon.android.config.ConfigRegistry$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return this.f$0.lambda$createUserPreferencesEditor$3();
            }
        }, ConfigType.ACCOUNT.name());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ SharedPreferences lambda$createUserPreferencesEditor$3() {
        Preconditions.checkState(this.mAppContext != null, "ConfigRegistry must be initialized before accessing the config values.");
        return PreferenceManager.getDefaultSharedPreferences(this.mAppContext);
    }

    @Nonnull
    private ConfigEditor createDefaultPreferencesEditor(@Nonnull Supplier<SharedPreferences> supplier, @Nonnull ConfigType configType) {
        return new DefaultConfigEditor(supplier, configType.name());
    }

    @Nonnull
    private ConfigEditor createDefaultPreferencesEditor(@Nonnull final String str, @Nonnull ConfigType configType) {
        return new DefaultConfigEditor(new Supplier() { // from class: amazon.android.config.ConfigRegistry$$ExternalSyntheticLambda3
            @Override // com.google.common.base.Supplier
            public final Object get() {
                return this.f$0.lambda$createDefaultPreferencesEditor$4(str);
            }
        }, configType.name());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ SharedPreferences lambda$createDefaultPreferencesEditor$4(String str) {
        Preconditions.checkState(this.mAppContext != null, "ConfigRegistry must be initialized before accessing the config values.");
        return this.mAppContext.getSharedPreferences(str, 0);
    }

    private class SdkSharedPreferencesSupplier implements Supplier<SharedPreferences> {
        private SdkSharedPreferencesSupplier() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.Supplier
        public SharedPreferences get() {
            Preconditions.checkState(ConfigRegistry.this.mAppContext != null, "ConfigRegistry must be initialized before accessing the config values.");
            return ConfigRegistry.this.mAppContext.getSharedPreferences("SDKPreferences", 0);
        }
    }
}
