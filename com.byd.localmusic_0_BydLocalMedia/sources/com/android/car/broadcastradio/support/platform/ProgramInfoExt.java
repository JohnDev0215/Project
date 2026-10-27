package com.android.car.broadcastradio.support.platform;

import android.graphics.Bitmap;
import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.hardware.radio.RadioMetadata;
import android.media.MediaMetadata;
import android.media.Rating;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
public class ProgramInfoExt {
    private static final char EN_DASH = 8211;
    public static final int NAME_NO_CHANNEL_FALLBACK = 65536;
    private static final String[] PROGRAM_NAME_ORDER = {RadioMetadata.METADATA_KEY_PROGRAM_NAME, RadioMetadata.METADATA_KEY_DAB_COMPONENT_NAME, RadioMetadata.METADATA_KEY_DAB_SERVICE_NAME, RadioMetadata.METADATA_KEY_DAB_ENSEMBLE_NAME, RadioMetadata.METADATA_KEY_RDS_PS};
    private static final String TAG = "BcRadioApp.pinfoext";
    private static final String TITLE_SEPARATOR = " – ";

    @Retention(RetentionPolicy.SOURCE)
    public @interface NameFlag {
    }

    public static String getProgramName(RadioManager.ProgramInfo programInfo, int i) {
        ProgramSelector.Identifier physicallyTunedTo;
        String amFmFrequency;
        RadioMetadata metadata = programInfo.getMetadata();
        if (metadata != null) {
            for (String str : PROGRAM_NAME_ORDER) {
                String string = metadata.getString(str);
                if (string != null) {
                    return string;
                }
            }
        }
        if ((65536 & i) != 0) {
            return "";
        }
        ProgramSelector selector = programInfo.getSelector();
        if (ProgramSelectorExt.isAmFmProgram(selector) && (physicallyTunedTo = programInfo.getPhysicallyTunedTo()) != null && physicallyTunedTo.getType() == 1 && (amFmFrequency = ProgramSelectorExt.formatAmFmFrequency(physicallyTunedTo.getValue(), i)) != null) {
            return amFmFrequency;
        }
        String displayName = ProgramSelectorExt.getDisplayName(selector, i);
        if (displayName != null) {
            return displayName;
        }
        Log.w(TAG, "ProgramInfo without a name nor channel name");
        return "";
    }

    public static RadioMetadata getMetadata(RadioManager.ProgramInfo programInfo) {
        RadioMetadata metadata = programInfo.getMetadata();
        return metadata != null ? metadata : new RadioMetadata.Builder().build();
    }

    public static MediaMetadata toMediaMetadata(RadioManager.ProgramInfo programInfo, boolean z, ImageResolver imageResolver) {
        Bitmap bitmapResolve;
        MediaMetadata.Builder builder = new MediaMetadata.Builder();
        builder.putString("android.media.metadata.DISPLAY_TITLE", getProgramName(programInfo, 0));
        RadioMetadata metadata = programInfo.getMetadata();
        if (metadata != null) {
            String string = metadata.getString(RadioMetadata.METADATA_KEY_TITLE);
            if (string != null) {
                builder.putString("android.media.metadata.TITLE", string);
            }
            String string2 = metadata.getString(RadioMetadata.METADATA_KEY_ARTIST);
            if (string2 != null) {
                builder.putString("android.media.metadata.ARTIST", string2);
            }
            String string3 = metadata.getString(RadioMetadata.METADATA_KEY_ALBUM);
            if (string3 != null) {
                builder.putString("android.media.metadata.ALBUM", string3);
            }
            if (string != null || string2 != null) {
                if (string == null) {
                    string = string2;
                } else if (string2 != null) {
                    string = string + TITLE_SEPARATOR + string2;
                }
                builder.putString("android.media.metadata.DISPLAY_SUBTITLE", string);
            }
            long globalBitmapId = RadioMetadataExt.getGlobalBitmapId(metadata, RadioMetadata.METADATA_KEY_ART);
            if (globalBitmapId != 0 && imageResolver != null && (bitmapResolve = imageResolver.resolve(globalBitmapId)) != null) {
                builder.putBitmap("android.media.metadata.ALBUM_ART", bitmapResolve);
            }
        }
        builder.putRating("android.media.metadata.USER_RATING", Rating.newHeartRating(z));
        return builder.build();
    }
}
