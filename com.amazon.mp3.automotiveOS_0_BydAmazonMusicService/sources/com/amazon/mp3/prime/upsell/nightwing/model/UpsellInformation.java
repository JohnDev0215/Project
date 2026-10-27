package com.amazon.mp3.prime.upsell.nightwing.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellInformation implements Parcelable {
    private String albumAsin;
    private String artistName;
    private long disappearsAfterDuration;
    private String itemUrl;
    private List<PlayableEntityIdentifier> playableEntityIdentifierList;
    private String seedName;
    private List<String> tags;
    private String trackAsinQueryParameter;
    private UpsellUIConfig upsellConfig;
    private UpsellReason upsellReason;
    private UpsellSourceEntity upsellSourceEntity;
    private static final String TAG = UpsellInformation.class.getSimpleName();
    public static final Parcelable.Creator<UpsellInformation> CREATOR = new Parcelable.Creator<UpsellInformation>() { // from class: com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UpsellInformation createFromParcel(Parcel in) {
            return new UpsellInformation(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UpsellInformation[] newArray(int size) {
            return new UpsellInformation[size];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UpsellInformation(final List<PlayableEntityIdentifier> playableEntityIdentifierList, final UpsellSourceEntity upsellSourceEntity, final UpsellReason upsellReason, final String seedName, final String artistName, final String albumAsin, final String itemUrl, final String trackAsinQueryParameter, final long disappearsAfterDuration, final List<String> tags) {
        this.playableEntityIdentifierList = playableEntityIdentifierList;
        this.upsellSourceEntity = upsellSourceEntity;
        this.upsellReason = upsellReason;
        this.seedName = seedName;
        this.artistName = artistName;
        this.albumAsin = albumAsin;
        this.itemUrl = itemUrl;
        this.trackAsinQueryParameter = trackAsinQueryParameter;
        this.disappearsAfterDuration = disappearsAfterDuration;
        this.upsellConfig = getDefaultUpsellConfig(upsellReason);
        this.tags = tags;
    }

    private UpsellUIConfig getDefaultUpsellConfig(UpsellReason upsellReason) {
        UpsellUIConfig upsellUIConfig = new UpsellUIConfig();
        upsellUIConfig.setUpsellDialogBodyId(R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_message);
        upsellUIConfig.setUpsellDialogComfortingTextId(R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_comfort_text);
        upsellUIConfig.setUpsellPositiveButtonTextId(R.string.dmusic_hawkfire_accoun_nightwing_upsell_dialog_positive_btn);
        upsellUIConfig.setSeedName(this.seedName);
        if (AnonymousClass2.$SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[upsellReason.ordinal()] == 1) {
            upsellUIConfig.setUpsellDialogTitleId(R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_skip_limit_reached_title);
            upsellUIConfig.setUpsellDialogBodyId(R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_skip_limit_reached_message);
        } else {
            upsellUIConfig.setUpsellDialogTitleId(R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_title);
        }
        return upsellUIConfig;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason;

        static {
            int[] iArr = new int[UpsellReason.values().length];
            $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason = iArr;
            try {
                iArr[UpsellReason.TRACK_SKIP_LIMIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public List<PlayableEntityIdentifier> getPlayableEntityIdentifierList() {
        return this.playableEntityIdentifierList;
    }

    public UpsellSourceEntity getUpsellSourceEntity() {
        return this.upsellSourceEntity;
    }

    public UpsellReason getUpsellReason() {
        return this.upsellReason;
    }

    public String getSeedName() {
        return this.seedName;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getAlbumAsin() {
        return this.albumAsin;
    }

    public String getItemUrl() {
        return this.itemUrl;
    }

    public String getTrackAsinQueryParameter() {
        return this.trackAsinQueryParameter;
    }

    public List<String> getTags() {
        return this.tags;
    }

    protected UpsellInformation(Parcel in) {
        if (in.readByte() == 1) {
            ArrayList arrayList = new ArrayList();
            this.playableEntityIdentifierList = arrayList;
            in.readList(arrayList, PlayableEntityIdentifier.class.getClassLoader());
        } else {
            this.playableEntityIdentifierList = null;
        }
        this.upsellConfig = (UpsellUIConfig) in.readValue(UpsellUIConfig.class.getClassLoader());
        this.upsellSourceEntity = (UpsellSourceEntity) in.readValue(UpsellSourceEntity.class.getClassLoader());
        this.upsellReason = (UpsellReason) in.readValue(UpsellReason.class.getClassLoader());
        this.seedName = in.readString();
        this.artistName = in.readString();
        this.disappearsAfterDuration = in.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(final Parcel dest, final int flags) {
        if (this.playableEntityIdentifierList == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeList(this.playableEntityIdentifierList);
        }
        dest.writeValue(this.upsellConfig);
        dest.writeValue(this.upsellSourceEntity);
        dest.writeValue(this.upsellReason);
        dest.writeString(this.seedName);
        dest.writeString(this.artistName);
        dest.writeLong(this.disappearsAfterDuration);
    }
}
