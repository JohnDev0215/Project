package com.amazon.music.externalstorage;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class StorageLocationFileManager {
    private static final Logger LOG = LoggerFactory.getLogger((Class<?>) StorageLocationFileManager.class);
    private final Context context;
    private final StorageStateProvider mediaStateProvider;
    private final String mediaType;
    private final boolean secondPartyApplication;
    private final StorageConfigurationProvider storageConfigurationProvider;
    private final StorageLocationPreference storageLocationPreference;

    public enum StorageConfiguration {
        EXTERNAL_PUBLIC_SUPPORTED,
        EXTERNAL_PUBLIC_NOT_SUPPORTED,
        EXTERNAL_STORAGE_NOT_SUPPORTED
    }

    public enum Visibility {
        PUBLIC,
        PRIVATE
    }

    /* synthetic */ StorageLocationFileManager(Context context, StorageConfigurationProvider storageConfigurationProvider, String str, StorageLocationPreference storageLocationPreference, StorageStateProvider storageStateProvider, boolean z, AnonymousClass1 anonymousClass1) {
        this(context, storageConfigurationProvider, str, storageLocationPreference, storageStateProvider, z);
    }

    public static StorageLocationFileManager create(Context context, StorageConfigurationProvider storageConfigurationProvider, String str) {
        return new Builder().withContext(context).withMediaType(str).withStorageLocationPreference(new StorageLocationPreference(context)).withStorageStateProvider(new StorageStateProvider(context)).withStorageConfigurationProvider(storageConfigurationProvider).build();
    }

    @Deprecated
    public static StorageLocationFileManager create(Context context, String str) {
        return create(context, new DeviceStorageConfiguration(context, str), str);
    }

    private StorageLocationFileManager(Context context, StorageConfigurationProvider storageConfigurationProvider, String str, StorageLocationPreference storageLocationPreference, StorageStateProvider storageStateProvider, boolean z) {
        this.context = context;
        this.secondPartyApplication = z;
        this.mediaType = str;
        this.storageLocationPreference = storageLocationPreference;
        this.mediaStateProvider = storageStateProvider;
        this.storageConfigurationProvider = storageConfigurationProvider;
    }

    public static class Builder {
        private Context context;
        private StorageStateProvider mediaStateProvider;
        private String mediaType;
        private boolean secondPartyApplication;
        private StorageConfigurationProvider storageConfigurationProvider;
        private StorageLocationPreference storageLocationPreference;

        public Builder withContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder withMediaType(String str) {
            this.mediaType = str;
            return this;
        }

        public Builder withStorageLocationPreference(StorageLocationPreference storageLocationPreference) {
            this.storageLocationPreference = storageLocationPreference;
            return this;
        }

        public Builder withStorageStateProvider(StorageStateProvider storageStateProvider) {
            this.mediaStateProvider = storageStateProvider;
            return this;
        }

        public Builder withStorageConfigurationProvider(StorageConfigurationProvider storageConfigurationProvider) {
            this.storageConfigurationProvider = storageConfigurationProvider;
            return this;
        }

        public StorageLocationFileManager build() {
            return new StorageLocationFileManager(this.context, this.storageConfigurationProvider, this.mediaType, this.storageLocationPreference, this.mediaStateProvider, this.secondPartyApplication, null);
        }
    }

    public File getPreferredStorageDirectory(Visibility visibility) {
        File directory;
        try {
            directory = getStorageDirectory(this.storageLocationPreference.getPreferredStorageLocation(), visibility);
        } catch (ExternalStorageNotSupportedException unused) {
            directory = new PrimaryStorageDirectory(this.context, this.mediaType).getDirectory(visibility);
        } catch (FileNotFoundException unused2) {
            directory = new PrimaryStorageDirectory(this.context, this.mediaType).getDirectory(visibility);
        }
        LOG.debug("getPreferredStorageDirectory() {}", directory);
        return directory;
    }

    public File getStorageDirectory(StorageLocation storageLocation, Visibility visibility) throws ExternalStorageNotSupportedException, IllegalArgumentException, FileNotFoundException {
        StorageDirectory primaryStorageDirectory;
        boolean zIsExternalStorageEmulated = Environment.isExternalStorageEmulated();
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$externalstorage$StorageLocation[storageLocation.ordinal()];
        if (i == 1) {
            primaryStorageDirectory = zIsExternalStorageEmulated ? new PrimaryStorageDirectory(this.context, this.mediaType) : getSecondaryStorage();
        } else if (i == 2) {
            primaryStorageDirectory = zIsExternalStorageEmulated ? getSecondaryStorage() : new PrimaryStorageDirectory(this.context, this.mediaType);
        } else {
            throw new IllegalArgumentException(String.format("Unknown StorageLocation: %s and Visibility: %s types passed in.", storageLocation.name(), visibility.name()));
        }
        File directory = primaryStorageDirectory.getDirectory(visibility);
        LOG.debug("getStorageDirectory(): {}, {},  {}", storageLocation, visibility, directory);
        return directory;
    }

    public boolean isStorageLocationWritable(File file) {
        if (file == null) {
            return false;
        }
        try {
            boolean zEquals = "mounted".equals(getStorageState(file));
            LOG.debug("isStorageLocationWritable() {}, {}", file, Boolean.valueOf(zEquals));
            return zEquals;
        } catch (ExternalStorageNotSupportedException unused) {
            return false;
        }
    }

    private String getStorageState(File file) throws ExternalStorageNotSupportedException {
        String externalStorageState = Environment.getExternalStorageState(file);
        LOG.debug("getStorageState() {}, {}", file, externalStorageState);
        return externalStorageState;
    }

    public StorageState getStorageLocationState(StorageLocation storageLocation) throws ExternalStorageNotSupportedException {
        return this.mediaStateProvider.getStorageLocationState(storageLocation, this.mediaType);
    }

    public long getAvailableBytesAtStorageLocation(StorageLocation storageLocation) throws ExternalStorageNotSupportedException, FileNotFoundException {
        long freeSpace = getStorageDirectory(storageLocation, Visibility.PUBLIC).getFreeSpace();
        LOG.debug("getAvailableBytesAtStorageLocation() {}, {}", storageLocation, Long.valueOf(freeSpace));
        return freeSpace;
    }

    public StorageConfiguration getStorageConfiguration() {
        return this.storageConfigurationProvider.getStorageConfiguration();
    }

    private StorageDirectory getSecondaryStorage() throws ExternalStorageNotSupportedException, IllegalArgumentException {
        return getSecondaryStorage(getStorageConfiguration());
    }

    private StorageDirectory getSecondaryStorage(StorageConfiguration storageConfiguration) throws ExternalStorageNotSupportedException, IllegalArgumentException {
        if (this.secondPartyApplication) {
            return new SecondaryStorageDirectory2P(this.context, this.mediaType);
        }
        return getDefaultSecondaryStorage(storageConfiguration);
    }

    /* JADX INFO: renamed from: com.amazon.music.externalstorage.StorageLocationFileManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$externalstorage$StorageLocation;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$StorageConfiguration;

        static {
            int[] iArr = new int[StorageConfiguration.values().length];
            $SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$StorageConfiguration = iArr;
            try {
                iArr[StorageConfiguration.EXTERNAL_PUBLIC_NOT_SUPPORTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$StorageConfiguration[StorageConfiguration.EXTERNAL_PUBLIC_SUPPORTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$StorageConfiguration[StorageConfiguration.EXTERNAL_STORAGE_NOT_SUPPORTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[StorageLocation.values().length];
            $SwitchMap$com$amazon$music$externalstorage$StorageLocation = iArr2;
            try {
                iArr2[StorageLocation.DEVICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$externalstorage$StorageLocation[StorageLocation.SDCARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private StorageDirectory getDefaultSecondaryStorage(StorageConfiguration storageConfiguration) throws ExternalStorageNotSupportedException, IllegalArgumentException {
        int i = AnonymousClass1.$SwitchMap$com$amazon$music$externalstorage$StorageLocationFileManager$StorageConfiguration[storageConfiguration.ordinal()];
        if (i == 1) {
            return new PublicNotSupportedSecondaryStorageDirectory(this.context, this.mediaType);
        }
        if (i == 2) {
            return new PublicSupportedSecondaryStorageDirectory(this.context, this.mediaType);
        }
        if (i == 3) {
            throw new ExternalStorageNotSupportedException();
        }
        throw new IllegalArgumentException(String.format("Unknown StorageConfiguration: %s", storageConfiguration.name()));
    }
}
