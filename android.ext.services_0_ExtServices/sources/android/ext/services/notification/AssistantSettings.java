package android.ext.services.notification;

import android.os.Handler;
import android.os.Looper;
import android.provider.DeviceConfig;
import android.util.Log;
import com.android.textclassifier.notification.SmartSuggestionsConfig;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes.dex */
final class AssistantSettings implements SmartSuggestionsConfig {
    static final int DEFAULT_MAX_SUGGESTIONS = 3;
    public static Factory FACTORY = new Factory() { // from class: android.ext.services.notification.-$$Lambda$AssistantSettings$W20RXn2fgFeBaFkEBk7m2hJOv9A
        @Override // android.ext.services.notification.AssistantSettings.Factory
        public final AssistantSettings createAndRegister() {
            return AssistantSettings.createAndRegister();
        }
    };
    DeviceConfig.OnPropertiesChangedListener mDeviceConfigChangedListener;
    boolean mGenerateReplies = true;
    boolean mGenerateActions = true;
    int mMaxMessagesToExtract = 5;
    int mMaxSuggestions = 3;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public interface Factory {
        AssistantSettings createAndRegister();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AssistantSettings createAndRegister() {
        AssistantSettings assistantSettings = new AssistantSettings();
        assistantSettings.registerDeviceConfigs();
        return assistantSettings;
    }

    private void registerDeviceConfigs() {
        this.mDeviceConfigChangedListener = new DeviceConfig.OnPropertiesChangedListener() { // from class: android.ext.services.notification.-$$Lambda$AssistantSettings$7UYjfS1qjCUtR0XPhfm4M_F5NQE
            public final void onPropertiesChanged(DeviceConfig.Properties properties) {
                this.f$0.lambda$registerDeviceConfigs$0$AssistantSettings(properties);
            }
        };
        DeviceConfig.addOnPropertiesChangedListener("systemui", new Executor() { // from class: android.ext.services.notification.-$$Lambda$AssistantSettings$2swz6XVVRpcB5k2897oDHYrCXyU
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                this.f$0.postToHandler(runnable);
            }
        }, this.mDeviceConfigChangedListener);
        updateFromDeviceConfigFlags();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$registerDeviceConfigs$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$registerDeviceConfigs$0$AssistantSettings(DeviceConfig.Properties properties) {
        onDeviceConfigPropertiesChanged(properties.getNamespace());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postToHandler(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    void unregisterDeviceConfigs() {
        DeviceConfig.OnPropertiesChangedListener onPropertiesChangedListener = this.mDeviceConfigChangedListener;
        if (onPropertiesChangedListener != null) {
            DeviceConfig.removeOnPropertiesChangedListener(onPropertiesChangedListener);
            this.mDeviceConfigChangedListener = null;
        }
    }

    void onDeviceConfigPropertiesChanged(String str) {
        if (!"systemui".equals(str)) {
            Log.e("AssistantSettings", "Received update from DeviceConfig for unrelated namespace: " + str);
            return;
        }
        updateFromDeviceConfigFlags();
    }

    private void updateFromDeviceConfigFlags() {
        this.mGenerateReplies = DeviceConfig.getBoolean("systemui", "nas_generate_replies", true);
        this.mGenerateActions = DeviceConfig.getBoolean("systemui", "nas_generate_actions", true);
        this.mMaxMessagesToExtract = DeviceConfig.getInt("systemui", "nas_max_messages_to_extract", 5);
        this.mMaxSuggestions = DeviceConfig.getInt("systemui", "nas_max_suggestions", 3);
    }

    @Override // com.android.textclassifier.notification.SmartSuggestionsConfig
    public boolean shouldGenerateReplies() {
        return this.mGenerateReplies;
    }

    @Override // com.android.textclassifier.notification.SmartSuggestionsConfig
    public boolean shouldGenerateActions() {
        return this.mGenerateActions;
    }

    @Override // com.android.textclassifier.notification.SmartSuggestionsConfig
    public int getMaxSuggestions() {
        return this.mMaxSuggestions;
    }

    @Override // com.android.textclassifier.notification.SmartSuggestionsConfig
    public int getMaxMessagesToExtract() {
        return this.mMaxMessagesToExtract;
    }
}
