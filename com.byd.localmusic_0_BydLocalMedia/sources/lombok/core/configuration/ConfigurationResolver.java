package lombok.core.configuration;

/* JADX INFO: loaded from: com.byd.localmusic_0_BydLocalMedia.apk:lombok/core/configuration/ConfigurationResolver.SCL.lombok */
public interface ConfigurationResolver {
    <T> T resolve(ConfigurationKey<T> configurationKey);
}
