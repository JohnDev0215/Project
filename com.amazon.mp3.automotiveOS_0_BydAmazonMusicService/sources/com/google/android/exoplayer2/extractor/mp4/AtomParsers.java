package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.AmazonQuirks;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.android.exoplayer2.video.ColorInfo;
import com.google.android.exoplayer2.video.HevcConfig;
import com.visualon.OSMPUtils.voMimeTypes;
import com.visualon.OSMPUtils.voOSType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: loaded from: classes6.dex */
final class AtomParsers {
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_text = Util.getIntegerCodeForString("text");
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_cenc = Util.getIntegerCodeForString("cenc");
    private static final int TYPE_meta = Util.getIntegerCodeForString("meta");

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z, boolean z2, boolean z3) throws ParserException {
        long j2;
        long j3;
        long[] jArr;
        long[] jArr2;
        long j4;
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mdia);
        int hdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (hdlr == -1) {
            return null;
        }
        TkhdData tkhd = parseTkhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long mvhd = parseMvhd(leafAtom.data);
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        MdhdData mdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        if (j == -9223372036854775807L) {
            if (!z3) {
                j4 = tkhd.duration;
                j3 = mvhd;
            } else {
                j4 = mdhd.duration;
                j3 = mdhd.timeScale;
            }
            j2 = j4;
        } else {
            j2 = j;
            j3 = mvhd;
        }
        long jScaleLargeTimestamp = j2 != -9223372036854775807L ? Util.scaleLargeTimestamp(j2, 1000000L, j3) : -9223372036854775807L;
        StsdData stsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, tkhd.id, tkhd.rotationDegrees, mdhd.language, drmInitData, z2);
        if (z) {
            jArr = null;
            jArr2 = null;
        } else {
            Pair<long[], long[]> edts = parseEdts(containerAtom.getContainerAtomOfType(Atom.TYPE_edts));
            long[] jArr3 = (long[]) edts.first;
            jArr2 = (long[]) edts.second;
            jArr = jArr3;
        }
        if (stsd.format == null) {
            return null;
        }
        return new Track(tkhd.id, hdlr, mdhd.timeScale, mvhd, jScaleLargeTimestamp, stsd.format, stsd.requiredSampleTransformation, stsd.trackEncryptionBoxes, stsd.nalUnitLengthFieldLength, jArr, jArr2);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0200  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.exoplayer2.extractor.mp4.TrackSampleTable parseStbl(com.google.android.exoplayer2.extractor.mp4.Track r47, com.google.android.exoplayer2.extractor.mp4.Atom.ContainerAtom r48, com.google.android.exoplayer2.extractor.GaplessInfoHolder r49) throws com.google.android.exoplayer2.ParserException {
        /*
            Method dump skipped, instruction units count: 1195
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseStbl(com.google.android.exoplayer2.extractor.mp4.Track, com.google.android.exoplayer2.extractor.mp4.Atom$ContainerAtom, com.google.android.exoplayer2.extractor.GaplessInfoHolder):com.google.android.exoplayer2.extractor.mp4.TrackSampleTable");
    }

    public static Metadata parseUdta(Atom.LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int i = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(position);
                return parseMetaAtom(parsableByteArray, position + i);
            }
            parsableByteArray.skipBytes(i - 8);
        }
        return null;
    }

    private static Metadata parseMetaAtom(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int i2 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + i2);
            }
            parsableByteArray.skipBytes(i2 - 8);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry ilstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (ilstElement != null) {
                arrayList.add(ilstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        parsableByteArray.skipBytes(Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0 ? 16 : 8);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        int i = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        int i2 = fullAtomVersion == 0 ? 4 : 8;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= i2) {
                z = true;
                break;
            }
            if (parsableByteArray.data[position + i4] != -1) {
                z = false;
                break;
            }
            i4++;
        }
        long j = -9223372036854775807L;
        if (z) {
            parsableByteArray.skipBytes(i2);
        } else {
            long unsignedInt = fullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (unsignedInt != 0) {
                j = unsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int i5 = parsableByteArray.readInt();
        int i6 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int i7 = parsableByteArray.readInt();
        int i8 = parsableByteArray.readInt();
        if (i5 == 0 && i6 == 65536 && i7 == -65536 && i8 == 0) {
            i3 = 90;
        } else if (i5 == 0 && i6 == -65536 && i7 == 65536 && i8 == 0) {
            i3 = voOSType.VOOSMP_PID_DOLBY_CERTIFICATION_DIALOGUE_ENHANCEMENT;
        } else if (i5 == -65536 && i6 == 0 && i7 == 0 && i8 == -65536) {
            i3 = 180;
        }
        return new TkhdData(i, j, i3);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        int i = parsableByteArray.readInt();
        if (i == TYPE_soun) {
            return 1;
        }
        if (i == TYPE_vide) {
            return 2;
        }
        if (i == TYPE_text || i == TYPE_sbtl || i == TYPE_subt || i == TYPE_clcp) {
            return 3;
        }
        return i == TYPE_meta ? 4 : -1;
    }

    private static MdhdData parseMdhd(ParsableByteArray parsableByteArray) {
        long unsignedInt;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion != 0 ? 16 : 8);
        long unsignedInt2 = parsableByteArray.readUnsignedInt();
        if (fullAtomVersion == 0) {
            unsignedInt = (int) parsableByteArray.readUnsignedInt();
        } else {
            unsignedInt = parsableByteArray.readLong();
        }
        long j = unsignedInt;
        int unsignedShort = parsableByteArray.readUnsignedShort();
        return new MdhdData("" + ((char) (((unsignedShort >> 10) & 31) + 96)) + ((char) (((unsignedShort >> 5) & 31) + 96)) + ((char) ((unsignedShort & 31) + 96)), j, unsignedInt2);
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        parsableByteArray.setPosition(12);
        int i3 = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            int position = parsableByteArray.getPosition();
            int i5 = parsableByteArray.readInt();
            Assertions.checkArgument(i5 > 0, "childAtomSize should be positive");
            int i6 = parsableByteArray.readInt();
            if (i6 == Atom.TYPE_avc1 || i6 == Atom.TYPE_avc3 || i6 == Atom.TYPE_encv || i6 == Atom.TYPE_mp4v || i6 == Atom.TYPE_hvc1 || i6 == Atom.TYPE_hev1 || i6 == Atom.TYPE_s263 || i6 == Atom.TYPE_vp08 || i6 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray, i6, position, i5, i, i2, drmInitData, stsdData, i4);
            } else if (i6 == Atom.TYPE_mp4a || i6 == Atom.TYPE_enca || i6 == Atom.TYPE_ac_3 || i6 == Atom.TYPE_ec_3 || i6 == Atom.TYPE_dtsc || i6 == Atom.TYPE_dtse || i6 == Atom.TYPE_dtsh || i6 == Atom.TYPE_dtsl || i6 == Atom.TYPE_samr || i6 == Atom.TYPE_sawb || i6 == Atom.TYPE_lpcm || i6 == Atom.TYPE_sowt || i6 == Atom.TYPE__mp3 || i6 == Atom.TYPE_alac) {
                parseAudioSampleEntry(parsableByteArray, i6, position, i5, i, str, z, drmInitData, stsdData, i4);
            } else if (i6 == Atom.TYPE_TTML || i6 == Atom.TYPE_tx3g || i6 == Atom.TYPE_wvtt || i6 == Atom.TYPE_stpp || i6 == Atom.TYPE_c608) {
                parseTextSampleEntry(parsableByteArray, i6, position, i5, i, str, stsdData);
            } else if (i6 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), "application/x-camera-motion", null, -1, null);
            }
            parsableByteArray.setPosition(position + i5);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) throws ParserException {
        parsableByteArray.setPosition(i2 + 8 + 8);
        String str2 = "application/ttml+xml";
        List listSingletonList = null;
        long j = Long.MAX_VALUE;
        if (i != Atom.TYPE_TTML) {
            if (i == Atom.TYPE_tx3g) {
                int i5 = (i3 - 8) - 8;
                byte[] bArr = new byte[i5];
                parsableByteArray.readBytes(bArr, 0, i5);
                listSingletonList = Collections.singletonList(bArr);
                str2 = "application/x-quicktime-tx3g";
            } else if (i == Atom.TYPE_wvtt) {
                str2 = "application/x-mp4-vtt";
            } else if (i == Atom.TYPE_stpp) {
                j = 0;
            } else if (i == Atom.TYPE_c608) {
                stsdData.requiredSampleTransformation = 1;
                str2 = "application/x-mp4-cea-608";
            } else {
                throw new IllegalStateException();
            }
        }
        stsdData.format = Format.createTextSampleFormat(Integer.toString(i4), str2, null, -1, 0, str, -1, null, j, listSingletonList);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, int i5, DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        parsableByteArray.setPosition(i2 + 8 + 8);
        parsableByteArray.skipBytes(16);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        String str = null;
        int iIntValue = i;
        if (iIntValue == Atom.TYPE_encv) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i2, i3);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData.trackEncryptionBoxes[i6] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        DrmInitData drmInitData2 = drmInitDataCopyWithSchemeType;
        int i7 = -1;
        List<byte[]> listSingletonList = null;
        byte[] projFromParent = null;
        ColorInfo nclxFromParent = null;
        float paspFromParent = 1.0f;
        boolean z = false;
        while (position - i2 < i3) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int i8 = parsableByteArray.readInt();
            if (i8 == 0 && parsableByteArray.getPosition() - i2 == i3) {
                break;
            }
            Assertions.checkArgument(i8 > 0, "childAtomSize should be positive");
            int i9 = parsableByteArray.readInt();
            if (i9 == Atom.TYPE_avcC) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                AvcConfig avcConfig = AvcConfig.parse(parsableByteArray);
                listSingletonList = avcConfig.initializationData;
                stsdData.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
                if (!z) {
                    paspFromParent = avcConfig.pixelWidthAspectRatio;
                }
                str = "video/avc";
            } else if (i9 == Atom.TYPE_hvcC) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                HevcConfig hevcConfig = HevcConfig.parse(parsableByteArray);
                listSingletonList = hevcConfig.initializationData;
                stsdData.nalUnitLengthFieldLength = hevcConfig.nalUnitLengthFieldLength;
                str = "video/hevc";
            } else if (i9 == Atom.TYPE_vpcC) {
                Assertions.checkState(str == null);
                str = iIntValue == Atom.TYPE_vp08 ? voMimeTypes.VOVIDEO_VP8 : voMimeTypes.VOVIDEO_VP9;
            } else if (i9 == Atom.TYPE_d263) {
                Assertions.checkState(str == null);
                str = voMimeTypes.VOVIDEO_H263;
            } else if (i9 == Atom.TYPE_esds) {
                Assertions.checkState(str == null);
                Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                str = (String) esdsFromParent.first;
                listSingletonList = Collections.singletonList(esdsFromParent.second);
            } else if (i9 == Atom.TYPE_pasp) {
                paspFromParent = parsePaspFromParent(parsableByteArray, position2);
                z = true;
            } else if (i9 == Atom.TYPE_sv3d) {
                projFromParent = parseProjFromParent(parsableByteArray, position2, i8);
            } else if (i9 == Atom.TYPE_st3d) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray.skipBytes(3);
                if (unsignedByte == 0) {
                    int unsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (unsignedByte2 == 0) {
                        i7 = 0;
                    } else if (unsignedByte2 == 1) {
                        i7 = 1;
                    } else if (unsignedByte2 == 2) {
                        i7 = 2;
                    } else if (unsignedByte2 == 3) {
                        i7 = 3;
                    }
                }
            } else if (i9 == Atom.TYPE_colr && nclxFromParent == null) {
                nclxFromParent = parseNclxFromParent(parsableByteArray, position2);
            }
            position += i8;
        }
        if (str == null) {
            return;
        }
        stsdData.format = Format.createVideoSampleFormat(Integer.toString(i4), str, null, -1, -1, unsignedShort, unsignedShort2, -1.0f, listSingletonList, i5, paspFromParent, projFromParent, i7, nclxFromParent, drmInitData2);
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst)) == null) {
            return Pair.create(null, null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[unsignedIntToInt];
        long[] jArr2 = new long[unsignedIntToInt];
        for (int i = 0; i < unsignedIntToInt; i++) {
            jArr[i] = fullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = fullAtomVersion == 1 ? parsableByteArray.readLong() : parsableByteArray.readInt();
            short s = parsableByteArray.readShort();
            if (s != 1) {
                if (!AmazonQuirks.isDwellModeIgnored()) {
                    throw new IllegalArgumentException("Unsupported media rate.");
                }
                Log.w("AtomParsers", "Ignoring Unsupported Media Rate : " + ((int) s));
            }
            parsableByteArray.skipBytes(2);
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return parsableByteArray.readUnsignedIntToInt() / parsableByteArray.readUnsignedIntToInt();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) {
        int unsignedShort;
        int unsignedFixedPoint1616;
        int unsignedIntToInt;
        String str2;
        String str3;
        String str4;
        DrmInitData drmInitData2;
        boolean z2;
        int i6;
        int i7;
        int i8;
        int i9 = i2;
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        parsableByteArray.setPosition(i9 + 8 + 8);
        int i10 = 0;
        if (z) {
            unsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(8);
            unsignedShort = 0;
        }
        int i11 = 2;
        boolean z3 = true;
        if (unsignedShort == 0 || unsignedShort == 1) {
            int unsignedShort2 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            unsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
            if (unsignedShort == 1) {
                parsableByteArray.skipBytes(16);
            }
            unsignedIntToInt = unsignedShort2;
        } else {
            if (unsignedShort != 2) {
                return;
            }
            parsableByteArray.skipBytes(16);
            unsignedFixedPoint1616 = (int) Math.round(parsableByteArray.readDouble());
            unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray.skipBytes(20);
        }
        int position = parsableByteArray.getPosition();
        int iIntValue = i;
        if (iIntValue == Atom.TYPE_enca) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i9, i3);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData.trackEncryptionBoxes[i5] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        DrmInitData drmInitData3 = drmInitDataCopyWithSchemeType;
        int i12 = Atom.TYPE_ac_3;
        String str5 = voMimeTypes.VOAUDIO_RAW;
        if (iIntValue == i12) {
            str2 = voMimeTypes.VOAUDIO_AC3;
        } else if (iIntValue == Atom.TYPE_ec_3) {
            str2 = voMimeTypes.VOAUDIO_E_AC3;
        } else if (iIntValue == Atom.TYPE_dtsc) {
            str2 = voMimeTypes.VOAUDIO_DTS;
        } else if (iIntValue == Atom.TYPE_dtsh || iIntValue == Atom.TYPE_dtsl) {
            str2 = voMimeTypes.VOAUDIO_DTS_HD;
        } else if (iIntValue == Atom.TYPE_dtse) {
            str2 = voMimeTypes.VOAUDIO_DTS_EXPRESS;
        } else if (iIntValue == Atom.TYPE_samr) {
            str2 = voMimeTypes.VOAUDIO_AMR_NB;
        } else if (iIntValue == Atom.TYPE_sawb) {
            str2 = voMimeTypes.VOAUDIO_AMR_WB;
        } else if (iIntValue == Atom.TYPE_lpcm || iIntValue == Atom.TYPE_sowt) {
            str2 = voMimeTypes.VOAUDIO_RAW;
        } else if (iIntValue == Atom.TYPE__mp3) {
            str2 = voMimeTypes.VOAUDIO_MPEG;
        } else {
            str2 = iIntValue == Atom.TYPE_alac ? voMimeTypes.VOAUDIO_ALAC : null;
        }
        String str6 = str2;
        int iIntValue2 = unsignedFixedPoint1616;
        int iIntValue3 = unsignedIntToInt;
        int i13 = position;
        byte[] bArr = null;
        while (i13 - i9 < i3) {
            parsableByteArray.setPosition(i13);
            int i14 = parsableByteArray.readInt();
            Assertions.checkArgument(i14 > 0 ? z3 : i10, "childAtomSize should be positive");
            int i15 = parsableByteArray.readInt();
            int i16 = Atom.TYPE_esds;
            if (i15 == i16) {
                str3 = str6;
                str4 = str5;
                drmInitData2 = drmInitData3;
                z2 = z3;
                i6 = i11;
                i7 = i10;
            } else if (z && i15 == Atom.TYPE_wave) {
                str3 = str6;
                str4 = str5;
                drmInitData2 = drmInitData3;
                i6 = i11;
                i7 = i10;
                z2 = true;
            } else {
                if (i15 == Atom.TYPE_dac3) {
                    parsableByteArray.setPosition(i13 + 8);
                    stsdData.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData3);
                } else if (i15 == Atom.TYPE_dec3) {
                    parsableByteArray.setPosition(i13 + 8);
                    stsdData.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData3);
                } else {
                    if (i15 == Atom.TYPE_ddts) {
                        str3 = str6;
                        str4 = str5;
                        drmInitData2 = drmInitData3;
                        i8 = i13;
                        z2 = true;
                        i6 = i11;
                        i7 = i10;
                        stsdData.format = Format.createAudioSampleFormat(Integer.toString(i4), str6, null, -1, -1, iIntValue3, iIntValue2, null, drmInitData2, 0, str);
                        i14 = i14;
                    } else {
                        i8 = i13;
                        str3 = str6;
                        str4 = str5;
                        drmInitData2 = drmInitData3;
                        i6 = i11;
                        i7 = i10;
                        z2 = true;
                        if (i15 == Atom.TYPE_alac) {
                            byte[] bArr2 = new byte[i14];
                            i13 = i8;
                            parsableByteArray.setPosition(i13);
                            parsableByteArray.readBytes(bArr2, i7, i14);
                            bArr = bArr2;
                        }
                        str6 = str3;
                        i13 += i14;
                        i10 = i7;
                        z3 = z2;
                        drmInitData3 = drmInitData2;
                        i11 = i6;
                        str5 = str4;
                        i9 = i2;
                    }
                    i13 = i8;
                    str6 = str3;
                    i13 += i14;
                    i10 = i7;
                    z3 = z2;
                    drmInitData3 = drmInitData2;
                    i11 = i6;
                    str5 = str4;
                    i9 = i2;
                }
                str3 = str6;
                str4 = str5;
                drmInitData2 = drmInitData3;
                i6 = i11;
                i7 = i10;
                z2 = true;
                str6 = str3;
                i13 += i14;
                i10 = i7;
                z3 = z2;
                drmInitData3 = drmInitData2;
                i11 = i6;
                str5 = str4;
                i9 = i2;
            }
            int iFindEsdsPosition = i15 == i16 ? i13 : findEsdsPosition(parsableByteArray, i13, i14);
            if (iFindEsdsPosition != -1) {
                Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, iFindEsdsPosition);
                str6 = (String) esdsFromParent.first;
                bArr = (byte[]) esdsFromParent.second;
                if (voMimeTypes.VOAUDIO_AAC.equals(str6)) {
                    Pair<Integer, Integer> aacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                    iIntValue2 = ((Integer) aacAudioSpecificConfig.first).intValue();
                    iIntValue3 = ((Integer) aacAudioSpecificConfig.second).intValue();
                }
            } else {
                str6 = str3;
            }
            i13 += i14;
            i10 = i7;
            z3 = z2;
            drmInitData3 = drmInitData2;
            i11 = i6;
            str5 = str4;
            i9 = i2;
        }
        String str7 = str6;
        String str8 = str5;
        DrmInitData drmInitData4 = drmInitData3;
        int i17 = i11;
        if (stsdData.format != null || str7 == null) {
            return;
        }
        stsdData.format = Format.createAudioSampleFormat(Integer.toString(i4), str7, null, -1, -1, iIntValue3, iIntValue2, str8.equals(str7) ? i17 : -1, bArr != null ? Collections.singletonList(bArr) : null, drmInitData4, 0, str);
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            Assertions.checkArgument(i3 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += i3;
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.util.Pair<java.lang.String, byte[]> parseEsdsFromParent(com.google.android.exoplayer2.util.ParsableByteArray r3, int r4) {
        /*
            int r4 = r4 + 8
            int r4 = r4 + 4
            r3.setPosition(r4)
            r4 = 1
            r3.skipBytes(r4)
            parseExpandableClassSize(r3)
            r0 = 2
            r3.skipBytes(r0)
            int r1 = r3.readUnsignedByte()
            r2 = r1 & 128(0x80, float:1.8E-43)
            if (r2 == 0) goto L1d
            r3.skipBytes(r0)
        L1d:
            r2 = r1 & 64
            if (r2 == 0) goto L28
            int r2 = r3.readUnsignedShort()
            r3.skipBytes(r2)
        L28:
            r2 = 32
            r1 = r1 & r2
            if (r1 == 0) goto L30
            r3.skipBytes(r0)
        L30:
            r3.skipBytes(r4)
            parseExpandableClassSize(r3)
            int r0 = r3.readUnsignedByte()
            r1 = 0
            if (r0 == r2) goto L8b
            r2 = 33
            if (r0 == r2) goto L88
            r2 = 35
            if (r0 == r2) goto L85
            r2 = 64
            if (r0 == r2) goto L82
            r2 = 107(0x6b, float:1.5E-43)
            if (r0 == r2) goto L7b
            r2 = 96
            if (r0 == r2) goto L78
            r2 = 97
            if (r0 == r2) goto L78
            r2 = 165(0xa5, float:2.31E-43)
            if (r0 == r2) goto L75
            r2 = 166(0xa6, float:2.33E-43)
            if (r0 == r2) goto L72
            switch(r0) {
                case 102: goto L82;
                case 103: goto L82;
                case 104: goto L82;
                default: goto L60;
            }
        L60:
            switch(r0) {
                case 169: goto L6b;
                case 170: goto L64;
                case 171: goto L64;
                case 172: goto L6b;
                default: goto L63;
            }
        L63:
            goto L8d
        L64:
            java.lang.String r3 = "audio/vnd.dts.hd"
            android.util.Pair r3 = android.util.Pair.create(r3, r1)
            return r3
        L6b:
            java.lang.String r3 = "audio/vnd.dts"
            android.util.Pair r3 = android.util.Pair.create(r3, r1)
            return r3
        L72:
            java.lang.String r1 = "audio/eac3"
            goto L8d
        L75:
            java.lang.String r1 = "audio/ac3"
            goto L8d
        L78:
            java.lang.String r1 = "video/mpeg2"
            goto L8d
        L7b:
            java.lang.String r3 = "audio/mpeg"
            android.util.Pair r3 = android.util.Pair.create(r3, r1)
            return r3
        L82:
            java.lang.String r1 = "audio/mp4a-latm"
            goto L8d
        L85:
            java.lang.String r1 = "video/hevc"
            goto L8d
        L88:
            java.lang.String r1 = "video/avc"
            goto L8d
        L8b:
            java.lang.String r1 = "video/mp4v-es"
        L8d:
            r0 = 12
            r3.skipBytes(r0)
            r3.skipBytes(r4)
            int r4 = parseExpandableClassSize(r3)
            byte[] r0 = new byte[r4]
            r2 = 0
            r3.readBytes(r0, r2, r4)
            android.util.Pair r3 = android.util.Pair.create(r1, r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseEsdsFromParent(com.google.android.exoplayer2.util.ParsableByteArray, int):android.util.Pair");
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) {
        Pair<Integer, TrackEncryptionBox> commonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            Assertions.checkArgument(i3 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf && (commonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, i3)) != null) {
                return commonEncryptionSinfFromParent;
            }
            position += i3;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        int i4 = -1;
        String string = null;
        Integer numValueOf = null;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i6 = parsableByteArray.readInt();
            int i7 = parsableByteArray.readInt();
            if (i7 == Atom.TYPE_frma) {
                numValueOf = Integer.valueOf(parsableByteArray.readInt());
            } else if (i7 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                string = parsableByteArray.readString(4);
            } else if (i7 == Atom.TYPE_schi) {
                i4 = i3;
                i5 = i6;
            }
            i3 += i6;
        }
        if (!"cenc".equals(string) && !"cbc1".equals(string) && !"cens".equals(string) && !"cbcs".equals(string)) {
            return null;
        }
        Assertions.checkArgument(numValueOf != null, "frma atom is mandatory");
        Assertions.checkArgument(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox schiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, string);
        Assertions.checkArgument(schiFromParent != null, "tenc atom is mandatory");
        return Pair.create(numValueOf, schiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int i6 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (fullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int unsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = unsignedByte & 15;
                    i4 = (unsignedByte & 240) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z && unsignedByte2 == 0) {
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[unsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, unsignedByte3);
                }
                return new TrackEncryptionBox(z, str, unsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += i6;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, i4 + i3);
            }
            i3 += i4;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.android.exoplayer2.video.ColorInfo parseNclxFromParent(com.google.android.exoplayer2.util.ParsableByteArray r8, int r9) {
        /*
            int r9 = r9 + 8
            r8.setPosition(r9)
            int r9 = r8.readInt()
            int r0 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_nclx
            r1 = 0
            if (r9 == r0) goto L14
            int r2 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_nclc
            if (r9 != r2) goto L13
            goto L14
        L13:
            return r1
        L14:
            int r2 = r8.readUnsignedShort()
            int r3 = r8.readUnsignedShort()
            r8.readUnsignedShort()
            r4 = 0
            r5 = 7
            r6 = 1
            if (r9 != r0) goto L2c
            int r8 = r8.readUnsignedByte()
            int r8 = r8 >> r5
            if (r8 == 0) goto L2c
            r4 = r6
        L2c:
            r8 = 2
            r9 = -1
            r0 = 6
            if (r2 == r6) goto L45
            r7 = 9
            if (r2 == r7) goto L43
            r7 = 4
            if (r2 == r7) goto L41
            r7 = 5
            if (r2 == r7) goto L41
            if (r2 == r0) goto L41
            if (r2 == r5) goto L41
            r2 = r9
            goto L46
        L41:
            r2 = r8
            goto L46
        L43:
            r2 = r0
            goto L46
        L45:
            r2 = r6
        L46:
            if (r3 == r6) goto L58
            r7 = 16
            if (r3 == r7) goto L56
            r7 = 18
            if (r3 == r7) goto L59
            if (r3 == r0) goto L58
            if (r3 == r5) goto L58
            r5 = r9
            goto L59
        L56:
            r5 = r0
            goto L59
        L58:
            r5 = 3
        L59:
            com.google.android.exoplayer2.video.ColorInfo r9 = new com.google.android.exoplayer2.video.ColorInfo
            if (r4 == 0) goto L5e
            goto L5f
        L5e:
            r6 = r8
        L5f:
            r9.<init>(r2, r6, r5, r1)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseNclxFromParent(com.google.android.exoplayer2.util.ParsableByteArray, int):com.google.android.exoplayer2.video.ColorInfo");
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i = unsignedByte & WorkQueueKt.MASK;
        while ((unsignedByte & 128) == 128) {
            unsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (unsignedByte & WorkQueueKt.MASK);
        }
        return i;
    }

    private AtomParsers() {
    }

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            this.offset = this.chunkOffsetsAreLongs ? this.chunkOffsets.readUnsignedLongToLong() : this.chunkOffsets.readUnsignedInt();
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private static final class TkhdData {
        private final long duration;
        private final int id;
        private final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    private static final class MdhdData {
        private final long duration;
        private final String language;
        private final long timeScale;

        public MdhdData(String str, long j, long j2) {
            this.language = str;
            this.duration = j;
            this.timeScale = j2;
        }
    }

    private static final class StsdData {
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fixedSampleSize = parsableByteArray.readUnsignedIntToInt();
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == 0 ? this.data.readUnsignedIntToInt() : i;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = parsableByteArray.readUnsignedIntToInt() & 255;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 == 0) {
                int unsignedByte = this.data.readUnsignedByte();
                this.currentByte = unsignedByte;
                return (unsignedByte & 240) >> 4;
            }
            return this.currentByte & 15;
        }
    }
}
