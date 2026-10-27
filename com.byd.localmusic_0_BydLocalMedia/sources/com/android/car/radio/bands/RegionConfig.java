package com.android.car.radio.bands;

import android.hardware.radio.RadioManager;
import android.os.Parcel;
import android.os.Parcelable;
import com.byd.music.utils.LogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class RegionConfig implements Parcelable {
    public static final Parcelable.Creator<RegionConfig> CREATOR = new Parcelable.Creator<RegionConfig>() { // from class: com.android.car.radio.bands.RegionConfig.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RegionConfig createFromParcel(Parcel parcel) {
            return new RegionConfig(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RegionConfig[] newArray(int i) {
            return new RegionConfig[i];
        }
    };
    private static final String TAG = "BcRadioApp.region";
    private final List<RadioManager.BandDescriptor> mAmConfig;
    private final List<RadioManager.BandDescriptor> mFmConfig;
    private final List<ProgramType> mSupported;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RegionConfig(List<RadioManager.BandDescriptor> list) {
        this.mAmConfig = new ArrayList();
        this.mFmConfig = new ArrayList();
        if (list == null) {
            this.mSupported = new ArrayList();
            return;
        }
        for (RadioManager.BandDescriptor bandDescriptor : list) {
            if (bandDescriptor instanceof RadioManager.AmBandDescriptor) {
                this.mAmConfig.add(bandDescriptor);
            } else if (bandDescriptor instanceof RadioManager.FmBandDescriptor) {
                this.mFmConfig.add(bandDescriptor);
            } else {
                LogUtil.w(TAG, "Unknown band type: " + bandDescriptor);
            }
        }
        $$Lambda$RegionConfig$GiPSsaRs4gT0h4H3D35hYWtqps __lambda_regionconfig_gipssars4gt0h4h3d35hywtqps = new Comparator() { // from class: com.android.car.radio.bands.-$$Lambda$RegionConfig$GiPSsaRs4gT0h4H3D3-5hYWtqps
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return RegionConfig.lambda$new$0((RadioManager.BandDescriptor) obj, (RadioManager.BandDescriptor) obj2);
            }
        };
        Collections.sort(this.mAmConfig, __lambda_regionconfig_gipssars4gt0h4h3d35hywtqps);
        Collections.sort(this.mFmConfig, __lambda_regionconfig_gipssars4gt0h4h3d35hywtqps);
        this.mSupported = createSupportedProgramTypes();
    }

    static /* synthetic */ int lambda$new$0(RadioManager.BandDescriptor bandDescriptor, RadioManager.BandDescriptor bandDescriptor2) {
        return bandDescriptor.getLowerLimit() - bandDescriptor2.getLowerLimit();
    }

    private RegionConfig(Parcel parcel) {
        this.mAmConfig = parcel.createTypedArrayList(RadioManager.BandDescriptor.CREATOR);
        this.mFmConfig = parcel.createTypedArrayList(RadioManager.BandDescriptor.CREATOR);
        this.mSupported = createSupportedProgramTypes();
    }

    private List<ProgramType> createSupportedProgramTypes() {
        ArrayList arrayList = new ArrayList();
        if (!this.mAmConfig.isEmpty()) {
            arrayList.add(ProgramType.AM);
        }
        if (!this.mFmConfig.isEmpty()) {
            arrayList.add(ProgramType.FM);
        }
        return arrayList;
    }

    public List<ProgramType> getSupportedProgramTypes() {
        return this.mSupported;
    }

    public List<RadioManager.BandDescriptor> getAmConfig() {
        return this.mAmConfig;
    }

    public List<RadioManager.BandDescriptor> getFmConfig() {
        return this.mFmConfig;
    }

    public String toString() {
        return "RegionConfig{AM=" + this.mAmConfig + ", FM=" + this.mFmConfig + "}";
    }

    public int hashCode() {
        return Objects.hash(this.mAmConfig, this.mFmConfig);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RegionConfig)) {
            return false;
        }
        RegionConfig regionConfig = (RegionConfig) obj;
        return this.mAmConfig.equals(regionConfig.mAmConfig) && this.mFmConfig.equals(regionConfig.mFmConfig);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.mAmConfig);
        parcel.writeTypedList(this.mFmConfig);
    }
}
