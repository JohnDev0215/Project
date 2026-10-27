package com.android.car.storagemonitoring;

import android.util.Log;
import com.android.car.CarLog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class EMmcWearInformationProvider implements WearInformationProvider {
    private static File DEFAULT_LIFE_TIME_FILE = new File("/sys/bus/mmc/devices/mmc0:0001/life_time");
    private static File DEFAULT_PRE_EOL_FILE = new File("/sys/bus/mmc/devices/mmc0:0001/pre_eol_info");
    private File mLifetimeFile;
    private File mPreEolFile;

    public EMmcWearInformationProvider() {
        this(DEFAULT_LIFE_TIME_FILE, DEFAULT_PRE_EOL_FILE);
    }

    EMmcWearInformationProvider(File lifetimeFile, File preEolFile) {
        this.mLifetimeFile = lifetimeFile;
        this.mPreEolFile = preEolFile;
    }

    private String readLineFromFile(File f) {
        if (!f.exists() || !f.isFile()) {
            Log.i(CarLog.TAG_STORAGE, f + " does not exist or is not a file");
            return null;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String data = reader.readLine();
            reader.close();
            return data;
        } catch (IOException e) {
            Log.w(CarLog.TAG_STORAGE, f + " cannot be read from", e);
            return null;
        }
    }

    @Override // com.android.car.storagemonitoring.WearInformationProvider
    public WearInformation load() {
        String lifetimeData = readLineFromFile(this.mLifetimeFile);
        String eolData = readLineFromFile(this.mPreEolFile);
        if (lifetimeData == null || eolData == null) {
            return null;
        }
        String[] lifetimes = lifetimeData.split(" ");
        if (lifetimes.length != 2) {
            Log.w(CarLog.TAG_STORAGE, "lifetime data not in expected format: " + lifetimeData);
            return null;
        }
        try {
            int lifetimeA = Integer.decode(lifetimes[0]).intValue();
            int lifetimeB = Integer.decode(lifetimes[1]).intValue();
            int eol = Integer.decode("0x" + eolData).intValue();
            return new WearInformation(convertLifetime(lifetimeA), convertLifetime(lifetimeB), adjustEol(eol));
        } catch (NumberFormatException e) {
            Log.w(CarLog.TAG_STORAGE, "lifetime data not in expected format: " + lifetimeData, e);
            return null;
        }
    }
}
