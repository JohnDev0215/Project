package com.android.car.storagemonitoring;

import java.io.File;

/* JADX INFO: loaded from: classes3.dex */
public class UfsWearInformationProvider implements WearInformationProvider {
    private static File DEFAULT_FILE = new File("/sys/devices/soc/624000.ufshc/health");
    private File mFile;

    public UfsWearInformationProvider() {
        this(DEFAULT_FILE);
    }

    public UfsWearInformationProvider(File file) {
        this.mFile = file;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007c  */
    @Override // com.android.car.storagemonitoring.WearInformationProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.android.car.storagemonitoring.WearInformation load() {
        /*
            Method dump skipped, instruction units count: 340
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.storagemonitoring.UfsWearInformationProvider.load():com.android.car.storagemonitoring.WearInformation");
    }
}
