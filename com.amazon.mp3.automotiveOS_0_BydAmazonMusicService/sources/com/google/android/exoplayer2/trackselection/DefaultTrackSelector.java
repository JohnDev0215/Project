package com.google.android.exoplayer2.trackselection;

import android.graphics.Point;
import android.text.TextUtils;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes6.dex */
public class DefaultTrackSelector extends MappingTrackSelector {
    private static final int[] NO_TRACKS = new int[0];
    private final TrackSelection.Factory adaptiveTrackSelectionFactory;
    private final AtomicReference<Parameters> paramsReference;

    private static int compareFormatValues(int i, int i2) {
        if (i == -1) {
            return i2 == -1 ? 0 : -1;
        }
        if (i2 == -1) {
            return 1;
        }
        return i - i2;
    }

    protected static boolean isSupported(int i, boolean z) {
        int i2 = i & 7;
        return i2 == 4 || (z && i2 == 3);
    }

    public static final class Parameters {
        public final boolean allowMixedMimeAdaptiveness;
        public final boolean allowNonSeamlessAdaptiveness;
        public final boolean exceedRendererCapabilitiesIfNecessary;
        public final boolean exceedVideoConstraintsIfNecessary;
        public final int maxVideoBitrate;
        public final int maxVideoHeight;
        public final int maxVideoWidth;
        public final String preferredAudioLanguage;
        public final String preferredTextLanguage;
        public final int viewportHeight;
        public final boolean viewportOrientationMayChange;
        public final int viewportWidth;

        public Parameters() {
            this(null, null, false, true, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, true, true, Integer.MAX_VALUE, Integer.MAX_VALUE, true);
        }

        public Parameters(String str, String str2, boolean z, boolean z2, int i, int i2, int i3, boolean z3, boolean z4, int i4, int i5, boolean z5) {
            this.preferredAudioLanguage = str;
            this.preferredTextLanguage = str2;
            this.allowMixedMimeAdaptiveness = z;
            this.allowNonSeamlessAdaptiveness = z2;
            this.maxVideoWidth = i;
            this.maxVideoHeight = i2;
            this.maxVideoBitrate = i3;
            this.exceedVideoConstraintsIfNecessary = z3;
            this.exceedRendererCapabilitiesIfNecessary = z4;
            this.viewportWidth = i4;
            this.viewportHeight = i5;
            this.viewportOrientationMayChange = z5;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || Parameters.class != obj.getClass()) {
                return false;
            }
            Parameters parameters = (Parameters) obj;
            return this.allowMixedMimeAdaptiveness == parameters.allowMixedMimeAdaptiveness && this.allowNonSeamlessAdaptiveness == parameters.allowNonSeamlessAdaptiveness && this.maxVideoWidth == parameters.maxVideoWidth && this.maxVideoHeight == parameters.maxVideoHeight && this.exceedVideoConstraintsIfNecessary == parameters.exceedVideoConstraintsIfNecessary && this.exceedRendererCapabilitiesIfNecessary == parameters.exceedRendererCapabilitiesIfNecessary && this.viewportOrientationMayChange == parameters.viewportOrientationMayChange && this.viewportWidth == parameters.viewportWidth && this.viewportHeight == parameters.viewportHeight && this.maxVideoBitrate == parameters.maxVideoBitrate && TextUtils.equals(this.preferredAudioLanguage, parameters.preferredAudioLanguage) && TextUtils.equals(this.preferredTextLanguage, parameters.preferredTextLanguage);
        }

        public int hashCode() {
            return (((((((((((((((((((((this.preferredAudioLanguage.hashCode() * 31) + this.preferredTextLanguage.hashCode()) * 31) + (this.allowMixedMimeAdaptiveness ? 1 : 0)) * 31) + (this.allowNonSeamlessAdaptiveness ? 1 : 0)) * 31) + this.maxVideoWidth) * 31) + this.maxVideoHeight) * 31) + this.maxVideoBitrate) * 31) + (this.exceedVideoConstraintsIfNecessary ? 1 : 0)) * 31) + (this.exceedRendererCapabilitiesIfNecessary ? 1 : 0)) * 31) + (this.viewportOrientationMayChange ? 1 : 0)) * 31) + this.viewportWidth) * 31) + this.viewportHeight;
        }
    }

    public DefaultTrackSelector() {
        this(null);
    }

    public DefaultTrackSelector(TrackSelection.Factory factory) {
        this.adaptiveTrackSelectionFactory = factory;
        this.paramsReference = new AtomicReference<>(new Parameters());
    }

    @Override // com.google.android.exoplayer2.trackselection.MappingTrackSelector
    protected TrackSelection[] selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        TrackSelection[] trackSelectionArr = new TrackSelection[length];
        Parameters parameters = this.paramsReference.get();
        boolean z = false;
        int i = 0;
        boolean z2 = false;
        while (true) {
            if (i >= length) {
                break;
            }
            if (2 == rendererCapabilitiesArr[i].getTrackType()) {
                if (!z) {
                    TrackSelection trackSelectionSelectVideoTrack = selectVideoTrack(rendererCapabilitiesArr[i], trackGroupArrayArr[i], iArr[i], parameters, this.adaptiveTrackSelectionFactory);
                    trackSelectionArr[i] = trackSelectionSelectVideoTrack;
                    z = trackSelectionSelectVideoTrack != null;
                }
                z2 |= trackGroupArrayArr[i].length > 0;
            }
            i++;
        }
        boolean z3 = false;
        boolean z4 = false;
        for (int i2 = 0; i2 < length; i2++) {
            int trackType = rendererCapabilitiesArr[i2].getTrackType();
            if (trackType != 1) {
                if (trackType != 2) {
                    if (trackType != 3) {
                        trackSelectionArr[i2] = selectOtherTrack(rendererCapabilitiesArr[i2].getTrackType(), trackGroupArrayArr[i2], iArr[i2], parameters);
                    } else if (!z4) {
                        TrackSelection trackSelectionSelectTextTrack = selectTextTrack(trackGroupArrayArr[i2], iArr[i2], parameters);
                        trackSelectionArr[i2] = trackSelectionSelectTextTrack;
                        z4 = trackSelectionSelectTextTrack != null;
                    }
                }
            } else if (!z3) {
                TrackSelection trackSelectionSelectAudioTrack = selectAudioTrack(trackGroupArrayArr[i2], iArr[i2], parameters, z2 ? null : this.adaptiveTrackSelectionFactory);
                trackSelectionArr[i2] = trackSelectionSelectAudioTrack;
                z3 = trackSelectionSelectAudioTrack != null;
            }
        }
        return trackSelectionArr;
    }

    protected TrackSelection selectVideoTrack(RendererCapabilities rendererCapabilities, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, TrackSelection.Factory factory) throws ExoPlaybackException {
        TrackSelection trackSelectionSelectAdaptiveVideoTrack = factory != null ? selectAdaptiveVideoTrack(rendererCapabilities, trackGroupArray, iArr, parameters, factory) : null;
        return trackSelectionSelectAdaptiveVideoTrack == null ? selectFixedVideoTrack(trackGroupArray, iArr, parameters) : trackSelectionSelectAdaptiveVideoTrack;
    }

    private static TrackSelection selectAdaptiveVideoTrack(RendererCapabilities rendererCapabilities, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, TrackSelection.Factory factory) throws ExoPlaybackException {
        int i = parameters.allowNonSeamlessAdaptiveness ? 24 : 16;
        boolean z = parameters.allowMixedMimeAdaptiveness && (rendererCapabilities.supportsMixedMimeTypeAdaptation() & i) != 0;
        for (int i2 = 0; i2 < trackGroupArray.length; i2++) {
            TrackGroup trackGroup = trackGroupArray.get(i2);
            int[] adaptiveVideoTracksForGroup = getAdaptiveVideoTracksForGroup(trackGroup, iArr[i2], z, i, parameters.maxVideoWidth, parameters.maxVideoHeight, parameters.maxVideoBitrate, parameters.viewportWidth, parameters.viewportHeight, parameters.viewportOrientationMayChange);
            if (adaptiveVideoTracksForGroup.length > 0) {
                return factory.createTrackSelection(trackGroup, adaptiveVideoTracksForGroup);
            }
        }
        return null;
    }

    private static int[] getAdaptiveVideoTracksForGroup(TrackGroup trackGroup, int[] iArr, boolean z, int i, int i2, int i3, int i4, int i5, int i6, boolean z2) {
        String str;
        int adaptiveVideoTrackCountForMimeType;
        if (trackGroup.length < 2) {
            return NO_TRACKS;
        }
        List<Integer> viewportFilteredTrackIndices = getViewportFilteredTrackIndices(trackGroup, i5, i6, z2);
        if (viewportFilteredTrackIndices.size() < 2) {
            return NO_TRACKS;
        }
        if (z) {
            str = null;
        } else {
            HashSet hashSet = new HashSet();
            String str2 = null;
            int i7 = 0;
            for (int i8 = 0; i8 < viewportFilteredTrackIndices.size(); i8++) {
                String str3 = trackGroup.getFormat(viewportFilteredTrackIndices.get(i8).intValue()).sampleMimeType;
                if (hashSet.add(str3) && (adaptiveVideoTrackCountForMimeType = getAdaptiveVideoTrackCountForMimeType(trackGroup, iArr, i, str3, i2, i3, i4, viewportFilteredTrackIndices)) > i7) {
                    i7 = adaptiveVideoTrackCountForMimeType;
                    str2 = str3;
                }
            }
            str = str2;
        }
        filterAdaptiveVideoTrackCountForMimeType(trackGroup, iArr, i, str, i2, i3, i4, viewportFilteredTrackIndices);
        return viewportFilteredTrackIndices.size() < 2 ? NO_TRACKS : Util.toArray(viewportFilteredTrackIndices);
    }

    private static int getAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, String str, int i2, int i3, int i4, List<Integer> list) {
        int i5 = 0;
        for (int i6 = 0; i6 < list.size(); i6++) {
            int iIntValue = list.get(i6).intValue();
            if (isSupportedAdaptiveVideoTrack(trackGroup.getFormat(iIntValue), str, iArr[iIntValue], i, i2, i3, i4)) {
                i5++;
            }
        }
        return i5;
    }

    private static void filterAdaptiveVideoTrackCountForMimeType(TrackGroup trackGroup, int[] iArr, int i, String str, int i2, int i3, int i4, List<Integer> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            int iIntValue = list.get(size).intValue();
            if (!isSupportedAdaptiveVideoTrack(trackGroup.getFormat(iIntValue), str, iArr[iIntValue], i, i2, i3, i4)) {
                list.remove(size);
            }
        }
    }

    private static boolean isSupportedAdaptiveVideoTrack(Format format, String str, int i, int i2, int i3, int i4, int i5) {
        if (!isSupported(i, false) || (i & i2) == 0) {
            return false;
        }
        if (str != null && !Util.areEqual(format.sampleMimeType, str)) {
            return false;
        }
        int i6 = format.width;
        if (i6 != -1 && i6 > i3) {
            return false;
        }
        int i7 = format.height;
        if (i7 != -1 && i7 > i4) {
            return false;
        }
        int i8 = format.bitrate;
        return i8 == -1 || i8 <= i5;
    }

    private static TrackSelection selectFixedVideoTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) {
        int iCompareFormatValues;
        int i;
        int i2;
        int i3;
        TrackGroupArray trackGroupArray2 = trackGroupArray;
        int i4 = -1;
        int pixelCount = -1;
        int i5 = -1;
        int i6 = 0;
        TrackGroup trackGroup = null;
        int i7 = 0;
        int i8 = 0;
        while (i6 < trackGroupArray2.length) {
            TrackGroup trackGroup2 = trackGroupArray2.get(i6);
            List<Integer> viewportFilteredTrackIndices = getViewportFilteredTrackIndices(trackGroup2, parameters.viewportWidth, parameters.viewportHeight, parameters.viewportOrientationMayChange);
            int[] iArr2 = iArr[i6];
            int i9 = 0;
            while (i9 < trackGroup2.length) {
                if (isSupported(iArr2[i9], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    Format format = trackGroup2.getFormat(i9);
                    boolean z = true;
                    boolean z2 = viewportFilteredTrackIndices.contains(Integer.valueOf(i9)) && ((i = format.width) == i4 || i <= parameters.maxVideoWidth) && (((i2 = format.height) == i4 || i2 <= parameters.maxVideoHeight) && ((i3 = format.bitrate) == i4 || i3 <= parameters.maxVideoBitrate));
                    if (z2 || parameters.exceedVideoConstraintsIfNecessary) {
                        int i10 = z2 ? 2 : 1;
                        boolean zIsSupported = isSupported(iArr2[i9], false);
                        if (zIsSupported) {
                            i10 += 1000;
                        }
                        boolean z3 = i10 > i8;
                        if (i10 == i8) {
                            if (format.getPixelCount() != pixelCount) {
                                iCompareFormatValues = compareFormatValues(format.getPixelCount(), pixelCount);
                            } else {
                                iCompareFormatValues = compareFormatValues(format.bitrate, i5);
                            }
                            if (!zIsSupported || !z2 ? iCompareFormatValues >= 0 : iCompareFormatValues <= 0) {
                                z = false;
                            }
                            z3 = z;
                        }
                        if (z3) {
                            i5 = format.bitrate;
                            pixelCount = format.getPixelCount();
                            trackGroup = trackGroup2;
                            i7 = i9;
                            i8 = i10;
                        }
                    }
                }
                i9++;
                i4 = -1;
            }
            i6++;
            trackGroupArray2 = trackGroupArray;
            i4 = -1;
        }
        if (trackGroup == null) {
            return null;
        }
        return new FixedTrackSelection(trackGroup, i7);
    }

    protected TrackSelection selectAudioTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters, TrackSelection.Factory factory) throws ExoPlaybackException {
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < trackGroupArray.length; i4++) {
            TrackGroup trackGroup = trackGroupArray.get(i4);
            int[] iArr2 = iArr[i4];
            for (int i5 = 0; i5 < trackGroup.length; i5++) {
                if (isSupported(iArr2[i5], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    int audioTrackScore = getAudioTrackScore(iArr2[i5], parameters.preferredAudioLanguage, trackGroup.getFormat(i5));
                    if (audioTrackScore > i) {
                        i2 = i4;
                        i3 = i5;
                        i = audioTrackScore;
                    }
                }
            }
        }
        if (i2 == -1) {
            return null;
        }
        TrackGroup trackGroup2 = trackGroupArray.get(i2);
        if (factory != null) {
            int[] adaptiveAudioTracks = getAdaptiveAudioTracks(trackGroup2, iArr[i2], parameters.allowMixedMimeAdaptiveness);
            if (adaptiveAudioTracks.length > 0) {
                return factory.createTrackSelection(trackGroup2, adaptiveAudioTracks);
            }
        }
        return new FixedTrackSelection(trackGroup2, i3);
    }

    private static int getAudioTrackScore(int i, String str, Format format) {
        int i2 = 1;
        boolean z = (format.selectionFlags & 1) != 0;
        if (formatHasLanguage(format, str)) {
            i2 = z ? 4 : 3;
        } else if (z) {
            i2 = 2;
        }
        return isSupported(i, false) ? i2 + 1000 : i2;
    }

    private static int[] getAdaptiveAudioTracks(TrackGroup trackGroup, int[] iArr, boolean z) {
        int adaptiveAudioTrackCount;
        HashSet hashSet = new HashSet();
        AudioConfigurationTuple audioConfigurationTuple = null;
        int i = 0;
        for (int i2 = 0; i2 < trackGroup.length; i2++) {
            Format format = trackGroup.getFormat(i2);
            AudioConfigurationTuple audioConfigurationTuple2 = new AudioConfigurationTuple(format.channelCount, format.sampleRate, z ? null : format.sampleMimeType);
            if (hashSet.add(audioConfigurationTuple2) && (adaptiveAudioTrackCount = getAdaptiveAudioTrackCount(trackGroup, iArr, audioConfigurationTuple2)) > i) {
                i = adaptiveAudioTrackCount;
                audioConfigurationTuple = audioConfigurationTuple2;
            }
        }
        if (i > 1) {
            int[] iArr2 = new int[i];
            int i3 = 0;
            for (int i4 = 0; i4 < trackGroup.length; i4++) {
                if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i4), iArr[i4], audioConfigurationTuple)) {
                    iArr2[i3] = i4;
                    i3++;
                }
            }
            return iArr2;
        }
        return NO_TRACKS;
    }

    private static int getAdaptiveAudioTrackCount(TrackGroup trackGroup, int[] iArr, AudioConfigurationTuple audioConfigurationTuple) {
        int i = 0;
        for (int i2 = 0; i2 < trackGroup.length; i2++) {
            if (isSupportedAdaptiveAudioTrack(trackGroup.getFormat(i2), iArr[i2], audioConfigurationTuple)) {
                i++;
            }
        }
        return i;
    }

    private static boolean isSupportedAdaptiveAudioTrack(Format format, int i, AudioConfigurationTuple audioConfigurationTuple) {
        if (!isSupported(i, false) || format.channelCount != audioConfigurationTuple.channelCount || format.sampleRate != audioConfigurationTuple.sampleRate) {
            return false;
        }
        String str = audioConfigurationTuple.mimeType;
        return str == null || TextUtils.equals(str, format.sampleMimeType);
    }

    protected TrackSelection selectTextTrack(TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) throws ExoPlaybackException {
        TrackGroup trackGroup = null;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < trackGroupArray.length; i3++) {
            TrackGroup trackGroup2 = trackGroupArray.get(i3);
            int[] iArr2 = iArr[i3];
            for (int i4 = 0; i4 < trackGroup2.length; i4++) {
                if (isSupported(iArr2[i4], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    Format format = trackGroup2.getFormat(i4);
                    int i5 = format.selectionFlags;
                    int i6 = 1;
                    boolean z = (i5 & 1) != 0;
                    boolean z2 = (i5 & 2) != 0;
                    if (formatHasLanguage(format, parameters.preferredTextLanguage)) {
                        i6 = z ? 6 : !z2 ? 5 : 4;
                    } else if (z) {
                        i6 = 3;
                    } else if (z2) {
                        if (formatHasLanguage(format, parameters.preferredAudioLanguage)) {
                            i6 = 2;
                        }
                    }
                    if (isSupported(iArr2[i4], false)) {
                        i6 += 1000;
                    }
                    if (i6 > i2) {
                        trackGroup = trackGroup2;
                        i = i4;
                        i2 = i6;
                    }
                }
            }
        }
        if (trackGroup == null) {
            return null;
        }
        return new FixedTrackSelection(trackGroup, i);
    }

    protected TrackSelection selectOtherTrack(int i, TrackGroupArray trackGroupArray, int[][] iArr, Parameters parameters) throws ExoPlaybackException {
        TrackGroup trackGroup = null;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < trackGroupArray.length; i4++) {
            TrackGroup trackGroup2 = trackGroupArray.get(i4);
            int[] iArr2 = iArr[i4];
            for (int i5 = 0; i5 < trackGroup2.length; i5++) {
                if (isSupported(iArr2[i5], parameters.exceedRendererCapabilitiesIfNecessary)) {
                    int i6 = (trackGroup2.getFormat(i5).selectionFlags & 1) != 0 ? 2 : 1;
                    if (isSupported(iArr2[i5], false)) {
                        i6 += 1000;
                    }
                    if (i6 > i3) {
                        trackGroup = trackGroup2;
                        i2 = i5;
                        i3 = i6;
                    }
                }
            }
        }
        if (trackGroup == null) {
            return null;
        }
        return new FixedTrackSelection(trackGroup, i2);
    }

    protected static boolean formatHasLanguage(Format format, String str) {
        return str != null && TextUtils.equals(str, Util.normalizeLanguageCode(format.language));
    }

    private static List<Integer> getViewportFilteredTrackIndices(TrackGroup trackGroup, int i, int i2, boolean z) {
        int i3;
        ArrayList arrayList = new ArrayList(trackGroup.length);
        for (int i4 = 0; i4 < trackGroup.length; i4++) {
            arrayList.add(Integer.valueOf(i4));
        }
        if (i != Integer.MAX_VALUE && i2 != Integer.MAX_VALUE) {
            int i5 = Integer.MAX_VALUE;
            for (int i6 = 0; i6 < trackGroup.length; i6++) {
                Format format = trackGroup.getFormat(i6);
                int i7 = format.width;
                if (i7 > 0 && (i3 = format.height) > 0) {
                    Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z, i, i2, i7, i3);
                    int i8 = format.width;
                    int i9 = format.height;
                    int i10 = i8 * i9;
                    if (i8 >= ((int) (maxVideoSizeInViewport.x * 0.98f)) && i9 >= ((int) (maxVideoSizeInViewport.y * 0.98f)) && i10 < i5) {
                        i5 = i10;
                    }
                }
            }
            if (i5 != Integer.MAX_VALUE) {
                for (int size = arrayList.size() - 1; size >= 0; size--) {
                    int pixelCount = trackGroup.getFormat(((Integer) arrayList.get(size)).intValue()).getPixelCount();
                    if (pixelCount == -1 || pixelCount > i5) {
                        arrayList.remove(size);
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0010  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.graphics.Point getMaxVideoSizeInViewport(boolean r3, int r4, int r5, int r6, int r7) {
        /*
            if (r3 == 0) goto L10
            r3 = 1
            r0 = 0
            if (r6 <= r7) goto L8
            r1 = r3
            goto L9
        L8:
            r1 = r0
        L9:
            if (r4 <= r5) goto Lc
            goto Ld
        Lc:
            r3 = r0
        Ld:
            if (r1 == r3) goto L10
            goto L13
        L10:
            r2 = r5
            r5 = r4
            r4 = r2
        L13:
            int r3 = r6 * r4
            int r0 = r7 * r5
            if (r3 < r0) goto L23
            android.graphics.Point r3 = new android.graphics.Point
            int r4 = com.google.android.exoplayer2.util.Util.ceilDivide(r0, r6)
            r3.<init>(r5, r4)
            return r3
        L23:
            android.graphics.Point r5 = new android.graphics.Point
            int r3 = com.google.android.exoplayer2.util.Util.ceilDivide(r3, r7)
            r5.<init>(r3, r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.trackselection.DefaultTrackSelector.getMaxVideoSizeInViewport(boolean, int, int, int, int):android.graphics.Point");
    }

    private static final class AudioConfigurationTuple {
        public final int channelCount;
        public final String mimeType;
        public final int sampleRate;

        public AudioConfigurationTuple(int i, int i2, String str) {
            this.channelCount = i;
            this.sampleRate = i2;
            this.mimeType = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || AudioConfigurationTuple.class != obj.getClass()) {
                return false;
            }
            AudioConfigurationTuple audioConfigurationTuple = (AudioConfigurationTuple) obj;
            return this.channelCount == audioConfigurationTuple.channelCount && this.sampleRate == audioConfigurationTuple.sampleRate && TextUtils.equals(this.mimeType, audioConfigurationTuple.mimeType);
        }

        public int hashCode() {
            int i = ((this.channelCount * 31) + this.sampleRate) * 31;
            String str = this.mimeType;
            return i + (str != null ? str.hashCode() : 0);
        }
    }
}
