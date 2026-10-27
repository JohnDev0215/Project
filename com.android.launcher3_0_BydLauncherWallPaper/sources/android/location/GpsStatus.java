package android.location;

import android.util.SparseArray;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public final class GpsStatus {
    private static final int BEIDOU_SVID_OFFSET = 200;
    private static final int GLONASS_SVID_OFFSET = 64;
    public static final int GPS_EVENT_FIRST_FIX = 3;
    public static final int GPS_EVENT_SATELLITE_STATUS = 4;
    public static final int GPS_EVENT_STARTED = 1;
    public static final int GPS_EVENT_STOPPED = 2;
    private static final int MAX_SATELLITES = 255;
    private static final int SBAS_SVID_OFFSET = -87;
    private int mTimeToFirstFix;
    private final SparseArray<GpsSatellite> mSatellites = new SparseArray<>();
    private Iterable<GpsSatellite> mSatelliteList = new Iterable() { // from class: android.location.-$$Lambda$GpsStatus$dThRStWdJLDKhUQ9_rP4r2XI10M
        @Override // java.lang.Iterable
        public final Iterator iterator() {
            return this.f$0.lambda$new$0$GpsStatus();
        }
    };

    @Deprecated
    public interface Listener {
        void onGpsStatusChanged(int i);
    }

    @Deprecated
    public interface NmeaListener {
        void onNmeaReceived(long j, String str);
    }

    private final class SatelliteIterator implements Iterator<GpsSatellite> {
        private int mIndex = 0;
        private final int mSatellitesCount;

        SatelliteIterator() {
            this.mSatellitesCount = GpsStatus.this.mSatellites.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (this.mIndex < this.mSatellitesCount) {
                if (((GpsSatellite) GpsStatus.this.mSatellites.valueAt(this.mIndex)).mValid) {
                    return true;
                }
                this.mIndex++;
            }
            return false;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public GpsSatellite next() {
            while (this.mIndex < this.mSatellitesCount) {
                GpsSatellite gpsSatellite = (GpsSatellite) GpsStatus.this.mSatellites.valueAt(this.mIndex);
                this.mIndex++;
                if (gpsSatellite.mValid) {
                    return gpsSatellite;
                }
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public /* synthetic */ Iterator lambda$new$0$GpsStatus() {
        return new SatelliteIterator();
    }

    public static GpsStatus create(GnssStatus gnssStatus, int i) {
        GpsStatus gpsStatus = new GpsStatus();
        gpsStatus.setStatus(gnssStatus, i);
        return gpsStatus;
    }

    static GpsStatus createEmpty() {
        return new GpsStatus();
    }

    private GpsStatus() {
    }

    void setStatus(GnssStatus gnssStatus, int i) {
        for (int i2 = 0; i2 < this.mSatellites.size(); i2++) {
            this.mSatellites.valueAt(i2).mValid = false;
        }
        this.mTimeToFirstFix = i;
        for (int i3 = 0; i3 < gnssStatus.getSatelliteCount(); i3++) {
            int constellationType = gnssStatus.getConstellationType(i3);
            int svid = gnssStatus.getSvid(i3);
            if (constellationType == 3) {
                svid += 64;
            } else if (constellationType == 5) {
                svid += 200;
            } else if (constellationType == 2) {
                svid += SBAS_SVID_OFFSET;
            } else {
                if (constellationType == 1 || constellationType == 4) {
                }
            }
            if (svid > 0 && svid <= 255) {
                GpsSatellite gpsSatellite = this.mSatellites.get(svid);
                if (gpsSatellite == null) {
                    gpsSatellite = new GpsSatellite(svid);
                    this.mSatellites.put(svid, gpsSatellite);
                }
                gpsSatellite.mValid = true;
                gpsSatellite.mSnr = gnssStatus.getCn0DbHz(i3);
                gpsSatellite.mElevation = gnssStatus.getElevationDegrees(i3);
                gpsSatellite.mAzimuth = gnssStatus.getAzimuthDegrees(i3);
                gpsSatellite.mHasEphemeris = gnssStatus.hasEphemerisData(i3);
                gpsSatellite.mHasAlmanac = gnssStatus.hasAlmanacData(i3);
                gpsSatellite.mUsedInFix = gnssStatus.usedInFix(i3);
            }
        }
    }

    public int getTimeToFirstFix() {
        return this.mTimeToFirstFix;
    }

    public Iterable<GpsSatellite> getSatellites() {
        return this.mSatelliteList;
    }

    public int getMaxSatellites() {
        return this.mSatellites.size();
    }
}
