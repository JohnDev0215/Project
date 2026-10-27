package android.timezone;

import java.io.IOException;
import java.util.Objects;
import libcore.timezone.TzDataSetVersion;

/* JADX INFO: loaded from: classes3.dex */
public final class TzDataSetVersion {
    private final libcore.timezone.TzDataSetVersion mDelegate;

    public static int currentFormatMajorVersion() {
        return libcore.timezone.TzDataSetVersion.currentFormatMajorVersion();
    }

    public static int currentFormatMinorVersion() {
        return libcore.timezone.TzDataSetVersion.currentFormatMinorVersion();
    }

    public static boolean isCompatibleWithThisDevice(TzDataSetVersion tzDataSetVersion) {
        return libcore.timezone.TzDataSetVersion.isCompatibleWithThisDevice(tzDataSetVersion.mDelegate);
    }

    public static TzDataSetVersion read() throws TzDataSetException, IOException {
        try {
            return new TzDataSetVersion(libcore.timezone.TzDataSetVersion.readTimeZoneModuleVersion());
        } catch (TzDataSetVersion.TzDataSetException e) {
            throw new TzDataSetException(e.getMessage(), e);
        }
    }

    public static final class TzDataSetException extends Exception {
        public TzDataSetException(String str) {
            super(str);
        }

        public TzDataSetException(String str, Throwable th) {
            super(str, th);
        }
    }

    private TzDataSetVersion(libcore.timezone.TzDataSetVersion tzDataSetVersion) {
        this.mDelegate = (libcore.timezone.TzDataSetVersion) Objects.requireNonNull(tzDataSetVersion);
    }

    public int getFormatMajorVersion() {
        return this.mDelegate.getFormatMajorVersion();
    }

    public int getFormatMinorVersion() {
        return this.mDelegate.getFormatMinorVersion();
    }

    public String getRulesVersion() {
        return this.mDelegate.getRulesVersion();
    }

    public int getRevision() {
        return this.mDelegate.getRevision();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.mDelegate.equals(((TzDataSetVersion) obj).mDelegate);
    }

    public int hashCode() {
        return Objects.hash(this.mDelegate);
    }

    public String toString() {
        return this.mDelegate.toString();
    }
}
