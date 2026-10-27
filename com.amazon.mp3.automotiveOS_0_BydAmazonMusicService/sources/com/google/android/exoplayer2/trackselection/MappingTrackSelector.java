package com.google.android.exoplayer2.trackselection;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.RendererConfiguration;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public abstract class MappingTrackSelector extends TrackSelector {
    private MappedTrackInfo currentMappedTrackInfo;
    private final SparseArray<Map<TrackGroupArray, SelectionOverride>> selectionOverrides = new SparseArray<>();
    private final SparseBooleanArray rendererDisabledFlags = new SparseBooleanArray();
    private int tunnelingAudioSessionId = 0;

    protected abstract TrackSelection[] selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr) throws ExoPlaybackException;

    public static final class MappedTrackInfo {
        private final int[][][] formatSupport;
        public final int length;
        private final int[] mixedMimeTypeAdaptiveSupport;
        private final int[] rendererTrackTypes;
        private final TrackGroupArray[] trackGroups;
        private final TrackGroupArray unassociatedTrackGroups;

        MappedTrackInfo(int[] iArr, TrackGroupArray[] trackGroupArrayArr, int[] iArr2, int[][][] iArr3, TrackGroupArray trackGroupArray) {
            this.rendererTrackTypes = iArr;
            this.trackGroups = trackGroupArrayArr;
            this.formatSupport = iArr3;
            this.mixedMimeTypeAdaptiveSupport = iArr2;
            this.unassociatedTrackGroups = trackGroupArray;
            this.length = trackGroupArrayArr.length;
        }
    }

    public static final class SelectionOverride {
        public final TrackSelection.Factory factory;
        public final int groupIndex;
        public final int[] tracks;

        public TrackSelection createTrackSelection(TrackGroupArray trackGroupArray) {
            return this.factory.createTrackSelection(trackGroupArray.get(this.groupIndex), this.tracks);
        }
    }

    public final boolean hasSelectionOverride(int i, TrackGroupArray trackGroupArray) {
        Map<TrackGroupArray, SelectionOverride> map = this.selectionOverrides.get(i);
        return map != null && map.containsKey(trackGroupArray);
    }

    public void setTunnelingAudioSessionId(int i) {
        if (this.tunnelingAudioSessionId != i) {
            this.tunnelingAudioSessionId = i;
            invalidate();
        }
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector
    public final TrackSelectorResult selectTracks(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray trackGroupArray) throws ExoPlaybackException {
        int[] iArr = new int[rendererCapabilitiesArr.length + 1];
        int length = rendererCapabilitiesArr.length + 1;
        TrackGroup[][] trackGroupArr = new TrackGroup[length][];
        int[][][] iArr2 = new int[rendererCapabilitiesArr.length + 1][][];
        for (int i = 0; i < length; i++) {
            int i2 = trackGroupArray.length;
            trackGroupArr[i] = new TrackGroup[i2];
            iArr2[i] = new int[i2][];
        }
        int[] mixedMimeTypeAdaptationSupport = getMixedMimeTypeAdaptationSupport(rendererCapabilitiesArr);
        for (int i3 = 0; i3 < trackGroupArray.length; i3++) {
            TrackGroup trackGroup = trackGroupArray.get(i3);
            int iFindRenderer = findRenderer(rendererCapabilitiesArr, trackGroup);
            int[] formatSupport = iFindRenderer == rendererCapabilitiesArr.length ? new int[trackGroup.length] : getFormatSupport(rendererCapabilitiesArr[iFindRenderer], trackGroup);
            int i4 = iArr[iFindRenderer];
            trackGroupArr[iFindRenderer][i4] = trackGroup;
            iArr2[iFindRenderer][i4] = formatSupport;
            iArr[iFindRenderer] = i4 + 1;
        }
        TrackGroupArray[] trackGroupArrayArr = new TrackGroupArray[rendererCapabilitiesArr.length];
        int[] iArr3 = new int[rendererCapabilitiesArr.length];
        for (int i5 = 0; i5 < rendererCapabilitiesArr.length; i5++) {
            int i6 = iArr[i5];
            trackGroupArrayArr[i5] = new TrackGroupArray((TrackGroup[]) Arrays.copyOf(trackGroupArr[i5], i6));
            iArr2[i5] = (int[][]) Arrays.copyOf(iArr2[i5], i6);
            iArr3[i5] = rendererCapabilitiesArr[i5].getTrackType();
        }
        TrackGroupArray trackGroupArray2 = new TrackGroupArray((TrackGroup[]) Arrays.copyOf(trackGroupArr[rendererCapabilitiesArr.length], iArr[rendererCapabilitiesArr.length]));
        TrackSelection[] trackSelectionArrSelectTracks = selectTracks(rendererCapabilitiesArr, trackGroupArrayArr, iArr2);
        int i7 = 0;
        while (true) {
            if (i7 >= rendererCapabilitiesArr.length) {
                break;
            }
            if (this.rendererDisabledFlags.get(i7)) {
                trackSelectionArrSelectTracks[i7] = null;
            } else {
                TrackGroupArray trackGroupArray3 = trackGroupArrayArr[i7];
                if (hasSelectionOverride(i7, trackGroupArray3)) {
                    SelectionOverride selectionOverride = this.selectionOverrides.get(i7).get(trackGroupArray3);
                    trackSelectionArrSelectTracks[i7] = selectionOverride != null ? selectionOverride.createTrackSelection(trackGroupArray3) : null;
                }
            }
            i7++;
        }
        MappedTrackInfo mappedTrackInfo = new MappedTrackInfo(iArr3, trackGroupArrayArr, mixedMimeTypeAdaptationSupport, iArr2, trackGroupArray2);
        RendererConfiguration[] rendererConfigurationArr = new RendererConfiguration[rendererCapabilitiesArr.length];
        for (int i8 = 0; i8 < rendererCapabilitiesArr.length; i8++) {
            rendererConfigurationArr[i8] = trackSelectionArrSelectTracks[i8] != null ? RendererConfiguration.DEFAULT : null;
        }
        maybeConfigureRenderersForTunneling(rendererCapabilitiesArr, trackGroupArrayArr, iArr2, rendererConfigurationArr, trackSelectionArrSelectTracks, this.tunnelingAudioSessionId);
        return new TrackSelectorResult(trackGroupArray, new TrackSelectionArray(trackSelectionArrSelectTracks), mappedTrackInfo, rendererConfigurationArr);
    }

    @Override // com.google.android.exoplayer2.trackselection.TrackSelector
    public final void onSelectionActivated(Object obj) {
        this.currentMappedTrackInfo = (MappedTrackInfo) obj;
    }

    private static int findRenderer(RendererCapabilities[] rendererCapabilitiesArr, TrackGroup trackGroup) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int i = 0;
        for (int i2 = 0; i2 < rendererCapabilitiesArr.length; i2++) {
            RendererCapabilities rendererCapabilities = rendererCapabilitiesArr[i2];
            for (int i3 = 0; i3 < trackGroup.length; i3++) {
                int iSupportsFormat = rendererCapabilities.supportsFormat(trackGroup.getFormat(i3)) & 7;
                if (iSupportsFormat > i) {
                    if (iSupportsFormat == 4) {
                        return i2;
                    }
                    length = i2;
                    i = iSupportsFormat;
                }
            }
        }
        return length;
    }

    private static int[] getFormatSupport(RendererCapabilities rendererCapabilities, TrackGroup trackGroup) throws ExoPlaybackException {
        int[] iArr = new int[trackGroup.length];
        for (int i = 0; i < trackGroup.length; i++) {
            iArr[i] = rendererCapabilities.supportsFormat(trackGroup.getFormat(i));
        }
        return iArr;
    }

    private static int[] getMixedMimeTypeAdaptationSupport(RendererCapabilities[] rendererCapabilitiesArr) throws ExoPlaybackException {
        int length = rendererCapabilitiesArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = rendererCapabilitiesArr[i].supportsMixedMimeTypeAdaptation();
        }
        return iArr;
    }

    private static void maybeConfigureRenderersForTunneling(RendererCapabilities[] rendererCapabilitiesArr, TrackGroupArray[] trackGroupArrayArr, int[][][] iArr, RendererConfiguration[] rendererConfigurationArr, TrackSelection[] trackSelectionArr, int i) {
        boolean z;
        if (i == 0) {
            return;
        }
        boolean z2 = false;
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < rendererCapabilitiesArr.length; i4++) {
            int trackType = rendererCapabilitiesArr[i4].getTrackType();
            TrackSelection trackSelection = trackSelectionArr[i4];
            if ((trackType == 1 || trackType == 2) && trackSelection != null && rendererSupportsTunneling(iArr[i4], trackGroupArrayArr[i4], trackSelection)) {
                if (trackType == 1) {
                    if (i3 != -1) {
                        z = false;
                        break;
                    }
                    i3 = i4;
                } else {
                    if (i2 != -1) {
                        z = false;
                        break;
                    }
                    i2 = i4;
                }
            }
        }
        z = true;
        if (i3 != -1 && i2 != -1) {
            z2 = true;
        }
        if (z && z2) {
            RendererConfiguration rendererConfiguration = new RendererConfiguration(i);
            rendererConfigurationArr[i3] = rendererConfiguration;
            rendererConfigurationArr[i2] = rendererConfiguration;
        }
    }

    private static boolean rendererSupportsTunneling(int[][] iArr, TrackGroupArray trackGroupArray, TrackSelection trackSelection) {
        if (trackSelection == null) {
            return false;
        }
        int iIndexOf = trackGroupArray.indexOf(trackSelection.getTrackGroup());
        for (int i = 0; i < trackSelection.length(); i++) {
            if ((iArr[iIndexOf][trackSelection.getIndexInTrackGroup(i)] & 32) != 32) {
                return false;
            }
        }
        return true;
    }
}
