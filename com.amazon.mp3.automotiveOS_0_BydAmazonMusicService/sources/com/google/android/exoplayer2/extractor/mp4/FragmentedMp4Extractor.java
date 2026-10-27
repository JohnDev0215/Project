package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mp4.Atom;
import com.google.android.exoplayer2.text.cea.CeaUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.UUID;

/* JADX INFO: loaded from: classes6.dex */
public final class FragmentedMp4Extractor implements Extractor {
    private TrackOutput additionalEmsgTrackOutput;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private ParsableByteArray ccData;
    private TrackOutput[] cea608TrackOutputs;
    private final Stack<Atom.ContainerAtom> containerAtoms;
    private TrackBundle currentTrackBundle;
    private final ParsableByteArray defaultInitializationVector;
    private long durationUs;
    private TrackOutput[] emsgTrackOutputs;
    private final ParsableByteArray encryptionSignalByte;
    private long endOfMdatPosition;
    private final byte[] extendedTypeScratch;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private final ParsableByteArray nalBuffer;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int pendingMetadataSampleBytes;
    private final LinkedList<MetadataSampleInfo> pendingMetadataSampleInfos;
    private boolean processSeiNalUnitPayload;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private long segmentIndexEarliestPresentationTimeUs;
    private final Track sideloadedTrack;
    private final TimestampAdjuster timestampAdjuster;
    private final SparseArray<TrackBundle> trackBundles;
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() { // from class: com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor.1
        @Override // com.google.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new FragmentedMp4Extractor()};
        }
    };
    private static final Format EMSG_FORMAT = Format.createSampleFormat(null, "application/x-emsg", Long.MAX_VALUE);
    private static final int SAMPLE_GROUP_TYPE_seig = Util.getIntegerCodeForString("seig");
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = {-94, 57, 79, 82, 90, -101, 79, 20, -94, 68, 108, 66, 124, 100, -115, CBORConstants.BYTE_FALSE};

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    public FragmentedMp4Extractor() {
        this(0);
    }

    public FragmentedMp4Extractor(int i) {
        this(i, null);
    }

    public FragmentedMp4Extractor(int i, TimestampAdjuster timestampAdjuster) {
        this(i, timestampAdjuster, null, null);
    }

    public FragmentedMp4Extractor(int i, TimestampAdjuster timestampAdjuster, Track track, TrackOutput trackOutput) {
        this.ccData = new ParsableByteArray(1024);
        this.flags = i | (track != null ? 16 : 0);
        this.timestampAdjuster = timestampAdjuster;
        this.sideloadedTrack = track;
        this.additionalEmsgTrackOutput = trackOutput;
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(5);
        this.nalBuffer = new ParsableByteArray();
        this.encryptionSignalByte = new ParsableByteArray(1);
        this.defaultInitializationVector = new ParsableByteArray();
        this.extendedTypeScratch = new byte[16];
        this.containerAtoms = new Stack<>();
        this.pendingMetadataSampleInfos = new LinkedList<>();
        this.trackBundles = new SparseArray<>();
        this.durationUs = -9223372036854775807L;
        this.segmentIndexEarliestPresentationTimeUs = -9223372036854775807L;
        enterReadingAtomHeaderState();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return Sniffer.sniffFragmented(extractorInput);
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput) {
        this.extractorOutput = extractorOutput;
        Track track = this.sideloadedTrack;
        if (track != null) {
            TrackBundle trackBundle = new TrackBundle(extractorOutput.track(0, track.type));
            trackBundle.init(this.sideloadedTrack, new DefaultSampleValues(0, 0, 0, 0));
            this.trackBundles.put(0, trackBundle);
            maybeInitExtraTracks();
            this.extractorOutput.endTracks();
        }
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        int size = this.trackBundles.size();
        for (int i = 0; i < size; i++) {
            this.trackBundles.valueAt(i).reset();
        }
        this.pendingMetadataSampleInfos.clear();
        this.pendingMetadataSampleBytes = 0;
        this.containerAtoms.clear();
        enterReadingAtomHeaderState();
    }

    @Override // com.google.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws InterruptedException, IOException {
        while (true) {
            int i = this.parserState;
            if (i != 0) {
                if (i == 1) {
                    readAtomPayload(extractorInput);
                } else if (i == 2) {
                    readEncryptionData(extractorInput);
                } else if (readSample(extractorInput)) {
                    return 0;
                }
            } else if (!readAtomHeader(extractorInput)) {
                return -1;
            }
        }
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws InterruptedException, IOException {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.data, 0, 8, true)) {
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        long j = this.atomSize;
        if (j == 1) {
            extractorInput.readFully(this.atomHeader.data, 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        } else if (j == 0) {
            long length = extractorInput.getLength();
            if (length == -1 && !this.containerAtoms.isEmpty()) {
                length = this.containerAtoms.peek().endPosition;
            }
            if (length != -1) {
                this.atomSize = (length - extractorInput.getPosition()) + ((long) this.atomHeaderBytesRead);
            }
        }
        if (this.atomSize < this.atomHeaderBytesRead) {
            throw new ParserException("Atom size less than header length (unsupported).");
        }
        long position = extractorInput.getPosition() - ((long) this.atomHeaderBytesRead);
        if (this.atomType == Atom.TYPE_moof) {
            int size = this.trackBundles.size();
            for (int i = 0; i < size; i++) {
                TrackFragment trackFragment = this.trackBundles.valueAt(i).fragment;
                trackFragment.atomPosition = position;
                trackFragment.auxiliaryDataPosition = position;
                trackFragment.dataPosition = position;
            }
        }
        int i2 = this.atomType;
        if (i2 == Atom.TYPE_mdat) {
            this.currentTrackBundle = null;
            this.endOfMdatPosition = position + this.atomSize;
            if (!this.haveOutputSeekMap) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.haveOutputSeekMap = true;
            }
            this.parserState = 2;
            return true;
        }
        if (shouldParseContainerAtom(i2)) {
            long position2 = (extractorInput.getPosition() + this.atomSize) - 8;
            this.containerAtoms.add(new Atom.ContainerAtom(this.atomType, position2));
            if (this.atomSize == this.atomHeaderBytesRead) {
                processAtomEnded(position2);
            } else {
                enterReadingAtomHeaderState();
            }
        } else if (shouldParseLeafAtom(this.atomType)) {
            if (this.atomHeaderBytesRead != 8) {
                throw new ParserException("Leaf atom defines extended atom size (unsupported).");
            }
            long j2 = this.atomSize;
            if (j2 > 2147483647L) {
                throw new ParserException("Leaf atom with length > 2147483647 (unsupported).");
            }
            ParsableByteArray parsableByteArray = new ParsableByteArray((int) j2);
            this.atomData = parsableByteArray;
            System.arraycopy(this.atomHeader.data, 0, parsableByteArray.data, 0, 8);
            this.parserState = 1;
        } else {
            if (this.atomSize > 2147483647L) {
                throw new ParserException("Skipping atom with length > 2147483647 (unsupported).");
            }
            this.atomData = null;
            this.parserState = 1;
        }
        return true;
    }

    private void readAtomPayload(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i = ((int) this.atomSize) - this.atomHeaderBytesRead;
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.data, 8, i);
            onLeafAtomRead(new Atom.LeafAtom(this.atomType, this.atomData), extractorInput.getPosition());
        } else {
            extractorInput.skipFully(i);
        }
        processAtomEnded(extractorInput.getPosition());
    }

    private void processAtomEnded(long j) throws ParserException {
        while (!this.containerAtoms.isEmpty() && this.containerAtoms.peek().endPosition == j) {
            onContainerAtomRead(this.containerAtoms.pop());
        }
        enterReadingAtomHeaderState();
    }

    private void onLeafAtomRead(Atom.LeafAtom leafAtom, long j) throws ParserException {
        if (!this.containerAtoms.isEmpty()) {
            this.containerAtoms.peek().add(leafAtom);
            return;
        }
        int i = leafAtom.type;
        if (i == Atom.TYPE_sidx) {
            Pair<Long, ChunkIndex> sidx = parseSidx(leafAtom.data, j);
            this.segmentIndexEarliestPresentationTimeUs = ((Long) sidx.first).longValue();
            this.extractorOutput.seekMap((SeekMap) sidx.second);
            this.haveOutputSeekMap = true;
            return;
        }
        if (i == Atom.TYPE_emsg) {
            onEmsgLeafAtomRead(leafAtom.data);
        }
    }

    private void onContainerAtomRead(Atom.ContainerAtom containerAtom) throws ParserException {
        int i = containerAtom.type;
        if (i == Atom.TYPE_moov) {
            onMoovContainerAtomRead(containerAtom);
        } else if (i == Atom.TYPE_moof) {
            onMoofContainerAtomRead(containerAtom);
        } else {
            if (this.containerAtoms.isEmpty()) {
                return;
            }
            this.containerAtoms.peek().add(containerAtom);
        }
    }

    private void onMoovContainerAtomRead(Atom.ContainerAtom containerAtom) throws ParserException {
        int i;
        int i2;
        SparseArray sparseArray;
        int i3 = 0;
        Assertions.checkState(this.sideloadedTrack == null, "Unexpected moov box.");
        DrmInitData drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerAtom.leafChildren);
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mvex);
        SparseArray sparseArray2 = new SparseArray();
        int size = containerAtomOfType.leafChildren.size();
        long mehd = -9223372036854775807L;
        for (int i4 = 0; i4 < size; i4++) {
            Atom.LeafAtom leafAtom = containerAtomOfType.leafChildren.get(i4);
            int i5 = leafAtom.type;
            if (i5 == Atom.TYPE_trex) {
                Pair<Integer, DefaultSampleValues> trex = parseTrex(leafAtom.data);
                sparseArray2.put(((Integer) trex.first).intValue(), trex.second);
            } else if (i5 == Atom.TYPE_mehd) {
                mehd = parseMehd(leafAtom.data);
            }
        }
        SparseArray sparseArray3 = new SparseArray();
        int size2 = containerAtom.containerChildren.size();
        int i6 = 0;
        while (i6 < size2) {
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i6);
            if (containerAtom2.type == Atom.TYPE_trak) {
                Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_mvhd);
                int i7 = this.flags;
                i = i6;
                i2 = size2;
                sparseArray = sparseArray3;
                Track trak = AtomParsers.parseTrak(containerAtom2, leafAtomOfType, mehd, drmInitDataFromAtoms, (i7 & 32) != 0, false, (i7 & 64) != 0);
                if (trak != null) {
                    sparseArray.put(trak.id, trak);
                }
            } else {
                i = i6;
                i2 = size2;
                sparseArray = sparseArray3;
            }
            i6 = i + 1;
            sparseArray3 = sparseArray;
            size2 = i2;
        }
        SparseArray sparseArray4 = sparseArray3;
        int size3 = sparseArray4.size();
        if (this.trackBundles.size() == 0) {
            while (i3 < size3) {
                Track track = (Track) sparseArray4.valueAt(i3);
                TrackBundle trackBundle = new TrackBundle(this.extractorOutput.track(i3, track.type));
                trackBundle.init(track, (DefaultSampleValues) sparseArray2.get(track.id));
                this.trackBundles.put(track.id, trackBundle);
                this.durationUs = Math.max(this.durationUs, track.durationUs);
                i3++;
            }
            maybeInitExtraTracks();
            this.extractorOutput.endTracks();
            return;
        }
        Assertions.checkState(this.trackBundles.size() == size3);
        while (i3 < size3) {
            Track track2 = (Track) sparseArray4.valueAt(i3);
            this.trackBundles.get(track2.id).init(track2, (DefaultSampleValues) sparseArray2.get(track2.id));
            i3++;
        }
    }

    private void onMoofContainerAtomRead(Atom.ContainerAtom containerAtom) throws ParserException {
        parseMoof(containerAtom, this.trackBundles, this.flags, this.extendedTypeScratch);
        DrmInitData drmInitDataFromAtoms = getDrmInitDataFromAtoms(containerAtom.leafChildren);
        if (drmInitDataFromAtoms != null) {
            int size = this.trackBundles.size();
            for (int i = 0; i < size; i++) {
                this.trackBundles.valueAt(i).updateDrmInitData(drmInitDataFromAtoms);
            }
        }
    }

    private void maybeInitExtraTracks() {
        int i;
        if (this.emsgTrackOutputs == null) {
            TrackOutput[] trackOutputArr = new TrackOutput[2];
            this.emsgTrackOutputs = trackOutputArr;
            TrackOutput trackOutput = this.additionalEmsgTrackOutput;
            if (trackOutput != null) {
                trackOutputArr[0] = trackOutput;
                i = 1;
            } else {
                i = 0;
            }
            if ((this.flags & 4) != 0) {
                trackOutputArr[i] = this.extractorOutput.track(this.trackBundles.size(), 4);
                i++;
            }
            TrackOutput[] trackOutputArr2 = (TrackOutput[]) Arrays.copyOf(this.emsgTrackOutputs, i);
            this.emsgTrackOutputs = trackOutputArr2;
            for (TrackOutput trackOutput2 : trackOutputArr2) {
                trackOutput2.format(EMSG_FORMAT);
            }
        }
        if ((this.flags & 8) == 0 || this.cea608TrackOutputs != null) {
            return;
        }
        TrackOutput trackOutputTrack = this.extractorOutput.track(this.trackBundles.size() + 1, 3);
        trackOutputTrack.format(Format.createTextSampleFormat(null, "application/cea-608", 0, null));
        this.cea608TrackOutputs = new TrackOutput[]{trackOutputTrack};
    }

    private void onEmsgLeafAtomRead(ParsableByteArray parsableByteArray) {
        TrackOutput[] trackOutputArr = this.emsgTrackOutputs;
        if (trackOutputArr == null || trackOutputArr.length == 0) {
            return;
        }
        parsableByteArray.setPosition(12);
        int iBytesLeft = parsableByteArray.bytesLeft();
        parsableByteArray.readNullTerminatedString();
        parsableByteArray.readNullTerminatedString();
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), 1000000L, parsableByteArray.readUnsignedInt());
        for (TrackOutput trackOutput : this.emsgTrackOutputs) {
            parsableByteArray.setPosition(12);
            trackOutput.sampleData(parsableByteArray, iBytesLeft);
        }
        long j = this.segmentIndexEarliestPresentationTimeUs;
        if (j != -9223372036854775807L) {
            long jAdjustSampleTimestamp = j + jScaleLargeTimestamp;
            TimestampAdjuster timestampAdjuster = this.timestampAdjuster;
            if (timestampAdjuster != null) {
                jAdjustSampleTimestamp = timestampAdjuster.adjustSampleTimestamp(jAdjustSampleTimestamp);
            }
            long j2 = jAdjustSampleTimestamp;
            for (TrackOutput trackOutput2 : this.emsgTrackOutputs) {
                trackOutput2.sampleMetadata(j2, 1, iBytesLeft, 0, null);
            }
            return;
        }
        this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(jScaleLargeTimestamp, iBytesLeft));
        this.pendingMetadataSampleBytes += iBytesLeft;
    }

    private static Pair<Integer, DefaultSampleValues> parseTrex(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(12);
        return Pair.create(Integer.valueOf(parsableByteArray.readInt()), new DefaultSampleValues(parsableByteArray.readUnsignedIntToInt() - 1, parsableByteArray.readUnsignedIntToInt(), parsableByteArray.readUnsignedIntToInt(), parsableByteArray.readInt()));
    }

    private static long parseMehd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
    }

    private static void parseMoof(Atom.ContainerAtom containerAtom, SparseArray<TrackBundle> sparseArray, int i, byte[] bArr) throws ParserException {
        int size = containerAtom.containerChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            Atom.ContainerAtom containerAtom2 = containerAtom.containerChildren.get(i2);
            if (containerAtom2.type == Atom.TYPE_traf) {
                parseTraf(containerAtom2, sparseArray, i, bArr);
            }
        }
    }

    private static void parseTraf(Atom.ContainerAtom containerAtom, SparseArray<TrackBundle> sparseArray, int i, byte[] bArr) throws ParserException {
        TrackBundle tfhd = parseTfhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tfhd).data, sparseArray, i);
        if (tfhd == null) {
            return;
        }
        TrackFragment trackFragment = tfhd.fragment;
        long tfdt = trackFragment.nextFragmentDecodeTime;
        tfhd.reset();
        int i2 = Atom.TYPE_tfdt;
        if (containerAtom.getLeafAtomOfType(i2) != null && (i & 2) == 0) {
            tfdt = parseTfdt(containerAtom.getLeafAtomOfType(i2).data);
        }
        parseTruns(containerAtom, tfhd, tfdt, i);
        TrackEncryptionBox sampleDescriptionEncryptionBox = tfhd.track.getSampleDescriptionEncryptionBox(trackFragment.header.sampleDescriptionIndex);
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_saiz);
        if (leafAtomOfType != null) {
            parseSaiz(sampleDescriptionEncryptionBox, leafAtomOfType.data, trackFragment);
        }
        Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_saio);
        if (leafAtomOfType2 != null) {
            parseSaio(leafAtomOfType2.data, trackFragment);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_senc);
        if (leafAtomOfType3 != null) {
            parseSenc(leafAtomOfType3.data, trackFragment);
        }
        Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_sbgp);
        Atom.LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(Atom.TYPE_sgpd);
        if (leafAtomOfType4 != null && leafAtomOfType5 != null) {
            parseSgpd(leafAtomOfType4.data, leafAtomOfType5.data, sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null, trackFragment);
        }
        int size = containerAtom.leafChildren.size();
        for (int i3 = 0; i3 < size; i3++) {
            Atom.LeafAtom leafAtom = containerAtom.leafChildren.get(i3);
            if (leafAtom.type == Atom.TYPE_uuid) {
                parseUuid(leafAtom.data, trackFragment, bArr);
            }
        }
    }

    private static void parseTruns(Atom.ContainerAtom containerAtom, TrackBundle trackBundle, long j, int i) {
        List<Atom.LeafAtom> list = containerAtom.leafChildren;
        int size = list.size();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            Atom.LeafAtom leafAtom = list.get(i4);
            if (leafAtom.type == Atom.TYPE_trun) {
                ParsableByteArray parsableByteArray = leafAtom.data;
                parsableByteArray.setPosition(12);
                int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                if (unsignedIntToInt > 0) {
                    i3 += unsignedIntToInt;
                    i2++;
                }
            }
        }
        trackBundle.currentTrackRunIndex = 0;
        trackBundle.currentSampleInTrackRun = 0;
        trackBundle.currentSampleIndex = 0;
        trackBundle.fragment.initTables(i2, i3);
        int i5 = 0;
        int trun = 0;
        for (int i6 = 0; i6 < size; i6++) {
            Atom.LeafAtom leafAtom2 = list.get(i6);
            if (leafAtom2.type == Atom.TYPE_trun) {
                trun = parseTrun(trackBundle, i5, j, i, leafAtom2.data, trun);
                i5++;
            }
        }
    }

    private static void parseSaiz(TrackEncryptionBox trackEncryptionBox, ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        int i;
        int i2 = trackEncryptionBox.initializationVectorSize;
        parsableByteArray.setPosition(8);
        if ((Atom.parseFullAtomFlags(parsableByteArray.readInt()) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt != trackFragment.sampleCount) {
            throw new ParserException("Length mismatch: " + unsignedIntToInt + ", " + trackFragment.sampleCount);
        }
        if (unsignedByte == 0) {
            boolean[] zArr = trackFragment.sampleHasSubsampleEncryptionTable;
            i = 0;
            for (int i3 = 0; i3 < unsignedIntToInt; i3++) {
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                i += unsignedByte2;
                zArr[i3] = unsignedByte2 > i2;
            }
        } else {
            i = (unsignedByte * unsignedIntToInt) + 0;
            Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, unsignedIntToInt, unsignedByte > i2);
        }
        trackFragment.initEncryptionData(i);
    }

    private static void parseSaio(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(8);
        int i = parsableByteArray.readInt();
        if ((Atom.parseFullAtomFlags(i) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt != 1) {
            throw new ParserException("Unexpected saio entry count: " + unsignedIntToInt);
        }
        trackFragment.auxiliaryDataPosition += Atom.parseFullAtomVersion(i) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
    }

    private static TrackBundle parseTfhd(ParsableByteArray parsableByteArray, SparseArray<TrackBundle> sparseArray, int i) {
        parsableByteArray.setPosition(8);
        int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        int i2 = parsableByteArray.readInt();
        if ((i & 16) != 0) {
            i2 = 0;
        }
        TrackBundle trackBundle = sparseArray.get(i2);
        if (trackBundle == null) {
            return null;
        }
        if ((fullAtomFlags & 1) != 0) {
            long unsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            TrackFragment trackFragment = trackBundle.fragment;
            trackFragment.dataPosition = unsignedLongToLong;
            trackFragment.auxiliaryDataPosition = unsignedLongToLong;
        }
        DefaultSampleValues defaultSampleValues = trackBundle.defaultSampleValues;
        trackBundle.fragment.header = new DefaultSampleValues((fullAtomFlags & 2) != 0 ? parsableByteArray.readUnsignedIntToInt() - 1 : defaultSampleValues.sampleDescriptionIndex, (fullAtomFlags & 8) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.duration, (fullAtomFlags & 16) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.size, (fullAtomFlags & 32) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.flags);
        return trackBundle;
    }

    private static long parseTfdt(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
    }

    private static int parseTrun(TrackBundle trackBundle, int i, long j, int i2, ParsableByteArray parsableByteArray, int i3) {
        boolean z;
        int unsignedIntToInt;
        boolean z2;
        int i4;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        parsableByteArray.setPosition(8);
        int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        Track track = trackBundle.track;
        TrackFragment trackFragment = trackBundle.fragment;
        DefaultSampleValues defaultSampleValues = trackFragment.header;
        trackFragment.trunLength[i] = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = trackFragment.trunDataPosition;
        long j2 = trackFragment.dataPosition;
        jArr[i] = j2;
        if ((fullAtomFlags & 1) != 0) {
            jArr[i] = j2 + ((long) parsableByteArray.readInt());
        }
        boolean z7 = (fullAtomFlags & 4) != 0;
        int unsignedIntToInt2 = defaultSampleValues.flags;
        if (z7) {
            unsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
        }
        boolean z8 = (fullAtomFlags & 256) != 0;
        boolean z9 = (fullAtomFlags & 512) != 0;
        boolean z10 = (fullAtomFlags & 1024) != 0;
        boolean z11 = (fullAtomFlags & 2048) != 0;
        long[] jArr2 = track.editListDurations;
        long jScaleLargeTimestamp = 0;
        if (jArr2 != null && jArr2.length == 1 && jArr2[0] == 0) {
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(track.editListMediaTimes[0], 1000L, track.timescale);
        }
        int[] iArr = trackFragment.sampleSizeTable;
        int[] iArr2 = trackFragment.sampleCompositionTimeOffsetTable;
        long[] jArr3 = trackFragment.sampleDecodingTimeTable;
        boolean[] zArr = trackFragment.sampleIsSyncFrameTable;
        int i5 = unsignedIntToInt2;
        boolean z12 = track.type == 2 && (i2 & 1) != 0;
        int i6 = i3 + trackFragment.trunLength[i];
        long j3 = track.timescale;
        long j4 = jScaleLargeTimestamp;
        long j5 = i > 0 ? trackFragment.nextFragmentDecodeTime : j;
        int i7 = i3;
        while (i7 < i6) {
            int unsignedIntToInt3 = z8 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.duration;
            if (z9) {
                z = z8;
                unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            } else {
                z = z8;
                unsignedIntToInt = defaultSampleValues.size;
            }
            if (i7 == 0 && z7) {
                z2 = z7;
                i4 = i5;
            } else if (z10) {
                z2 = z7;
                i4 = parsableByteArray.readInt();
            } else {
                z2 = z7;
                i4 = defaultSampleValues.flags;
            }
            if (z11) {
                z3 = z11;
                z4 = z9;
                z5 = z10;
                iArr2[i7] = (int) ((((long) parsableByteArray.readInt()) * 1000) / j3);
                z6 = false;
            } else {
                z3 = z11;
                z4 = z9;
                z5 = z10;
                z6 = false;
                iArr2[i7] = 0;
            }
            jArr3[i7] = Util.scaleLargeTimestamp(j5, 1000L, j3) - j4;
            iArr[i7] = unsignedIntToInt;
            zArr[i7] = (((i4 >> 16) & 1) != 0 || (z12 && i7 != 0)) ? z6 : true;
            i7++;
            j5 += (long) unsignedIntToInt3;
            j3 = j3;
            z8 = z;
            z7 = z2;
            z11 = z3;
            z9 = z4;
            z10 = z5;
        }
        trackFragment.nextFragmentDecodeTime = j5;
        return i6;
    }

    private static void parseUuid(ParsableByteArray parsableByteArray, TrackFragment trackFragment, byte[] bArr) throws ParserException {
        parsableByteArray.setPosition(8);
        parsableByteArray.readBytes(bArr, 0, 16);
        if (Arrays.equals(bArr, PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE)) {
            parseSenc(parsableByteArray, 16, trackFragment);
        }
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parseSenc(parsableByteArray, 0, trackFragment);
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, int i, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(i + 8);
        int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        if ((fullAtomFlags & 1) != 0) {
            throw new ParserException("Overriding TrackEncryptionBox parameters is unsupported.");
        }
        boolean z = (fullAtomFlags & 2) != 0;
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (unsignedIntToInt != trackFragment.sampleCount) {
            throw new ParserException("Length mismatch: " + unsignedIntToInt + ", " + trackFragment.sampleCount);
        }
        Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, unsignedIntToInt, z);
        trackFragment.initEncryptionData(parsableByteArray.bytesLeft());
        trackFragment.fillEncryptionData(parsableByteArray);
    }

    private static void parseSgpd(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, String str, TrackFragment trackFragment) throws ParserException {
        byte[] bArr;
        parsableByteArray.setPosition(8);
        int i = parsableByteArray.readInt();
        int i2 = parsableByteArray.readInt();
        int i3 = SAMPLE_GROUP_TYPE_seig;
        if (i2 != i3) {
            return;
        }
        if (Atom.parseFullAtomVersion(i) == 1) {
            parsableByteArray.skipBytes(4);
        }
        if (parsableByteArray.readInt() != 1) {
            throw new ParserException("Entry count in sbgp != 1 (unsupported).");
        }
        parsableByteArray2.setPosition(8);
        int i4 = parsableByteArray2.readInt();
        if (parsableByteArray2.readInt() != i3) {
            return;
        }
        int fullAtomVersion = Atom.parseFullAtomVersion(i4);
        if (fullAtomVersion == 1) {
            if (parsableByteArray2.readUnsignedInt() == 0) {
                throw new ParserException("Variable length description in sgpd found (unsupported)");
            }
        } else if (fullAtomVersion >= 2) {
            parsableByteArray2.skipBytes(4);
        }
        if (parsableByteArray2.readUnsignedInt() != 1) {
            throw new ParserException("Entry count in sgpd != 1 (unsupported).");
        }
        parsableByteArray2.skipBytes(1);
        int unsignedByte = parsableByteArray2.readUnsignedByte();
        int i5 = (unsignedByte & 240) >> 4;
        int i6 = unsignedByte & 15;
        boolean z = parsableByteArray2.readUnsignedByte() == 1;
        if (z) {
            int unsignedByte2 = parsableByteArray2.readUnsignedByte();
            byte[] bArr2 = new byte[16];
            parsableByteArray2.readBytes(bArr2, 0, 16);
            if (z && unsignedByte2 == 0) {
                int unsignedByte3 = parsableByteArray2.readUnsignedByte();
                byte[] bArr3 = new byte[unsignedByte3];
                parsableByteArray2.readBytes(bArr3, 0, unsignedByte3);
                bArr = bArr3;
            } else {
                bArr = null;
            }
            trackFragment.definesEncryptionData = true;
            trackFragment.trackEncryptionBox = new TrackEncryptionBox(z, str, unsignedByte2, bArr2, i5, i6, bArr);
        }
    }

    private static Pair<Long, ChunkIndex> parseSidx(ParsableByteArray parsableByteArray, long j) throws ParserException {
        long unsignedLongToLong;
        long unsignedLongToLong2;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(4);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        if (fullAtomVersion == 0) {
            unsignedLongToLong = parsableByteArray.readUnsignedInt();
            unsignedLongToLong2 = parsableByteArray.readUnsignedInt();
        } else {
            unsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            unsignedLongToLong2 = parsableByteArray.readUnsignedLongToLong();
        }
        long j2 = unsignedLongToLong;
        long j3 = j + unsignedLongToLong2;
        long jScaleLargeTimestamp = Util.scaleLargeTimestamp(j2, 1000000L, unsignedInt);
        parsableByteArray.skipBytes(2);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int[] iArr = new int[unsignedShort];
        long[] jArr = new long[unsignedShort];
        long[] jArr2 = new long[unsignedShort];
        long[] jArr3 = new long[unsignedShort];
        long j4 = jScaleLargeTimestamp;
        int i = 0;
        long j5 = j2;
        while (i < unsignedShort) {
            int i2 = parsableByteArray.readInt();
            if ((i2 & Integer.MIN_VALUE) != 0) {
                throw new ParserException("Unhandled indirect reference");
            }
            long unsignedInt2 = parsableByteArray.readUnsignedInt();
            iArr[i] = i2 & Integer.MAX_VALUE;
            jArr[i] = j3;
            jArr3[i] = j4;
            long j6 = j5 + unsignedInt2;
            long[] jArr4 = jArr2;
            long[] jArr5 = jArr3;
            int i3 = unsignedShort;
            int[] iArr2 = iArr;
            long jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(j6, 1000000L, unsignedInt);
            jArr4[i] = jScaleLargeTimestamp2 - jArr5[i];
            parsableByteArray.skipBytes(4);
            j3 += (long) iArr2[i];
            i++;
            iArr = iArr2;
            jArr3 = jArr5;
            jArr2 = jArr4;
            jArr = jArr;
            unsignedShort = i3;
            j5 = j6;
            j4 = jScaleLargeTimestamp2;
        }
        return Pair.create(Long.valueOf(jScaleLargeTimestamp), new ChunkIndex(iArr, jArr, jArr2, jArr3));
    }

    private void readEncryptionData(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int size = this.trackBundles.size();
        TrackBundle trackBundleValueAt = null;
        long j = Long.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            TrackFragment trackFragment = this.trackBundles.valueAt(i).fragment;
            if (trackFragment.sampleEncryptionDataNeedsFill) {
                long j2 = trackFragment.auxiliaryDataPosition;
                if (j2 < j) {
                    trackBundleValueAt = this.trackBundles.valueAt(i);
                    j = j2;
                }
            }
        }
        if (trackBundleValueAt == null) {
            this.parserState = 3;
            return;
        }
        int position = (int) (j - extractorInput.getPosition());
        if (position < 0) {
            throw new ParserException("Offset to encryption data was negative.");
        }
        extractorInput.skipFully(position);
        trackBundleValueAt.fragment.fillEncryptionData(extractorInput);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private boolean readSample(ExtractorInput extractorInput) throws InterruptedException, IOException {
        int i;
        TrackOutput.CryptoData cryptoData;
        int iSampleData;
        int i2 = 4;
        int i3 = 1;
        int i4 = 0;
        if (this.parserState == 3) {
            if (this.currentTrackBundle == null) {
                TrackBundle nextFragmentRun = getNextFragmentRun(this.trackBundles);
                if (nextFragmentRun == null) {
                    int position = (int) (this.endOfMdatPosition - extractorInput.getPosition());
                    if (position < 0) {
                        throw new ParserException("Offset to end of mdat was negative.");
                    }
                    extractorInput.skipFully(position);
                    enterReadingAtomHeaderState();
                    return false;
                }
                int position2 = (int) (nextFragmentRun.fragment.trunDataPosition[nextFragmentRun.currentTrackRunIndex] - extractorInput.getPosition());
                if (position2 < 0) {
                    Log.w("FragmentedMp4Extractor", "Ignoring negative offset to sample data.");
                    position2 = 0;
                }
                extractorInput.skipFully(position2);
                this.currentTrackBundle = nextFragmentRun;
            }
            TrackBundle trackBundle = this.currentTrackBundle;
            TrackFragment trackFragment = trackBundle.fragment;
            this.sampleSize = trackFragment.sampleSizeTable[trackBundle.currentSampleIndex];
            if (trackFragment.definesEncryptionData) {
                int iAppendSampleEncryptionData = appendSampleEncryptionData(trackBundle);
                this.sampleBytesWritten = iAppendSampleEncryptionData;
                this.sampleSize += iAppendSampleEncryptionData;
            } else {
                this.sampleBytesWritten = 0;
            }
            if (this.currentTrackBundle.track.sampleTransformation == 1) {
                this.sampleSize -= 8;
                extractorInput.skipFully(8);
            }
            this.parserState = 4;
            this.sampleCurrentNalBytesRemaining = 0;
        }
        TrackBundle trackBundle2 = this.currentTrackBundle;
        TrackFragment trackFragment2 = trackBundle2.fragment;
        Track track = trackBundle2.track;
        TrackOutput trackOutput = trackBundle2.output;
        int i5 = trackBundle2.currentSampleIndex;
        int i6 = track.nalUnitLengthFieldLength;
        long j = 1000;
        if (i6 == 0) {
            while (true) {
                int i7 = this.sampleBytesWritten;
                int i8 = this.sampleSize;
                if (i7 >= i8) {
                    break;
                }
                this.sampleBytesWritten += trackOutput.sampleData(extractorInput, i8 - i7, false);
            }
        } else {
            byte[] bArr = this.nalPrefix.data;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i9 = i6 + 1;
            int i10 = 4 - i6;
            while (this.sampleBytesWritten < this.sampleSize) {
                int i11 = this.sampleCurrentNalBytesRemaining;
                if (i11 == 0) {
                    extractorInput.readFully(bArr, i10, i9);
                    this.nalPrefix.setPosition(i4);
                    this.sampleCurrentNalBytesRemaining = this.nalPrefix.readUnsignedIntToInt() - i3;
                    this.nalStartCode.setPosition(i4);
                    trackOutput.sampleData(this.nalStartCode, i2);
                    trackOutput.sampleData(this.nalPrefix, i3);
                    this.processSeiNalUnitPayload = (this.cea608TrackOutputs == null || !NalUnitUtil.isNalUnitSei(track.format.sampleMimeType, bArr[i2])) ? i4 : i3;
                    this.sampleBytesWritten += 5;
                    this.sampleSize += i10;
                } else {
                    if (this.processSeiNalUnitPayload) {
                        this.nalBuffer.reset(i11);
                        extractorInput.readFully(this.nalBuffer.data, i4, this.sampleCurrentNalBytesRemaining);
                        trackOutput.sampleData(this.nalBuffer, this.sampleCurrentNalBytesRemaining);
                        iSampleData = this.sampleCurrentNalBytesRemaining;
                        ParsableByteArray parsableByteArray = this.nalBuffer;
                        int iUnescapeStream = NalUnitUtil.unescapeStream(parsableByteArray.data, parsableByteArray.limit());
                        this.nalBuffer.setPosition("video/hevc".equals(track.format.sampleMimeType) ? 1 : 0);
                        this.nalBuffer.setLimit(iUnescapeStream);
                        CeaUtil.consume(trackFragment2.getSamplePresentationTime(i5) * j, this.nalBuffer, this.cea608TrackOutputs, this.ccData);
                    } else {
                        iSampleData = trackOutput.sampleData(extractorInput, i11, i4);
                    }
                    this.sampleBytesWritten += iSampleData;
                    this.sampleCurrentNalBytesRemaining -= iSampleData;
                    i2 = 4;
                    i3 = 1;
                    i4 = 0;
                    j = 1000;
                }
            }
        }
        long samplePresentationTime = trackFragment2.getSamplePresentationTime(i5) * 1000;
        TimestampAdjuster timestampAdjuster = this.timestampAdjuster;
        if (timestampAdjuster != null) {
            samplePresentationTime = timestampAdjuster.adjustSampleTimestamp(samplePresentationTime);
        }
        boolean z = trackFragment2.sampleIsSyncFrameTable[i5];
        if (trackFragment2.definesEncryptionData) {
            int i12 = (z ? 1 : 0) | 1073741824;
            TrackEncryptionBox sampleDescriptionEncryptionBox = trackFragment2.trackEncryptionBox;
            if (sampleDescriptionEncryptionBox == null) {
                sampleDescriptionEncryptionBox = track.getSampleDescriptionEncryptionBox(trackFragment2.header.sampleDescriptionIndex);
            }
            i = i12;
            cryptoData = sampleDescriptionEncryptionBox.cryptoData;
        } else {
            i = z ? 1 : 0;
            cryptoData = null;
        }
        trackOutput.sampleMetadata(samplePresentationTime, i, this.sampleSize, 0, cryptoData);
        while (!this.pendingMetadataSampleInfos.isEmpty()) {
            MetadataSampleInfo metadataSampleInfoRemoveFirst = this.pendingMetadataSampleInfos.removeFirst();
            this.pendingMetadataSampleBytes -= metadataSampleInfoRemoveFirst.size;
            long jAdjustSampleTimestamp = metadataSampleInfoRemoveFirst.presentationTimeDeltaUs + samplePresentationTime;
            TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
            if (timestampAdjuster2 != null) {
                jAdjustSampleTimestamp = timestampAdjuster2.adjustSampleTimestamp(jAdjustSampleTimestamp);
            }
            for (TrackOutput trackOutput2 : this.emsgTrackOutputs) {
                trackOutput2.sampleMetadata(jAdjustSampleTimestamp, 1, metadataSampleInfoRemoveFirst.size, this.pendingMetadataSampleBytes, null);
            }
        }
        TrackBundle trackBundle3 = this.currentTrackBundle;
        trackBundle3.currentSampleIndex++;
        int i13 = trackBundle3.currentSampleInTrackRun + 1;
        trackBundle3.currentSampleInTrackRun = i13;
        int[] iArr = trackFragment2.trunLength;
        int i14 = trackBundle3.currentTrackRunIndex;
        if (i13 == iArr[i14]) {
            trackBundle3.currentTrackRunIndex = i14 + 1;
            trackBundle3.currentSampleInTrackRun = 0;
            this.currentTrackBundle = null;
        }
        this.parserState = 3;
        return true;
    }

    private static TrackBundle getNextFragmentRun(SparseArray<TrackBundle> sparseArray) {
        int size = sparseArray.size();
        TrackBundle trackBundle = null;
        long j = Long.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            TrackBundle trackBundleValueAt = sparseArray.valueAt(i);
            int i2 = trackBundleValueAt.currentTrackRunIndex;
            TrackFragment trackFragment = trackBundleValueAt.fragment;
            if (i2 != trackFragment.trunCount) {
                long j2 = trackFragment.trunDataPosition[i2];
                if (j2 < j) {
                    trackBundle = trackBundleValueAt;
                    j = j2;
                }
            }
        }
        return trackBundle;
    }

    private int appendSampleEncryptionData(TrackBundle trackBundle) {
        ParsableByteArray parsableByteArray;
        TrackFragment trackFragment = trackBundle.fragment;
        int i = trackFragment.header.sampleDescriptionIndex;
        TrackEncryptionBox sampleDescriptionEncryptionBox = trackFragment.trackEncryptionBox;
        if (sampleDescriptionEncryptionBox == null) {
            sampleDescriptionEncryptionBox = trackBundle.track.getSampleDescriptionEncryptionBox(i);
        }
        int length = sampleDescriptionEncryptionBox.initializationVectorSize;
        if (length != 0) {
            parsableByteArray = trackFragment.sampleEncryptionData;
        } else {
            byte[] bArr = sampleDescriptionEncryptionBox.defaultInitializationVector;
            this.defaultInitializationVector.reset(bArr, bArr.length);
            parsableByteArray = this.defaultInitializationVector;
            length = bArr.length;
        }
        boolean z = trackFragment.sampleHasSubsampleEncryptionTable[trackBundle.currentSampleIndex];
        ParsableByteArray parsableByteArray2 = this.encryptionSignalByte;
        parsableByteArray2.data[0] = (byte) ((z ? 128 : 0) | length);
        parsableByteArray2.setPosition(0);
        TrackOutput trackOutput = trackBundle.output;
        trackOutput.sampleData(this.encryptionSignalByte, 1);
        trackOutput.sampleData(parsableByteArray, length);
        if (!z) {
            return length + 1;
        }
        ParsableByteArray parsableByteArray3 = trackFragment.sampleEncryptionData;
        int unsignedShort = parsableByteArray3.readUnsignedShort();
        parsableByteArray3.skipBytes(-2);
        int i2 = (unsignedShort * 6) + 2;
        trackOutput.sampleData(parsableByteArray3, i2);
        return length + 1 + i2;
    }

    private static DrmInitData getDrmInitDataFromAtoms(List<Atom.LeafAtom> list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            Atom.LeafAtom leafAtom = list.get(i);
            if (leafAtom.type == Atom.TYPE_pssh) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] bArr = leafAtom.data.data;
                UUID uuid = PsshAtomUtil.parseUuid(bArr);
                if (uuid == null) {
                    Log.w("FragmentedMp4Extractor", "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new DrmInitData.SchemeData(uuid, null, voMimeTypes.VOVIDEO_MP4, bArr));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new DrmInitData(arrayList);
    }

    private static boolean shouldParseLeafAtom(int i) {
        return i == Atom.TYPE_hdlr || i == Atom.TYPE_mdhd || i == Atom.TYPE_mvhd || i == Atom.TYPE_sidx || i == Atom.TYPE_stsd || i == Atom.TYPE_tfdt || i == Atom.TYPE_tfhd || i == Atom.TYPE_tkhd || i == Atom.TYPE_trex || i == Atom.TYPE_trun || i == Atom.TYPE_pssh || i == Atom.TYPE_saiz || i == Atom.TYPE_saio || i == Atom.TYPE_senc || i == Atom.TYPE_uuid || i == Atom.TYPE_sbgp || i == Atom.TYPE_sgpd || i == Atom.TYPE_elst || i == Atom.TYPE_mehd || i == Atom.TYPE_emsg;
    }

    private static boolean shouldParseContainerAtom(int i) {
        return i == Atom.TYPE_moov || i == Atom.TYPE_trak || i == Atom.TYPE_mdia || i == Atom.TYPE_minf || i == Atom.TYPE_stbl || i == Atom.TYPE_moof || i == Atom.TYPE_traf || i == Atom.TYPE_mvex || i == Atom.TYPE_edts;
    }

    private static final class MetadataSampleInfo {
        public final long presentationTimeDeltaUs;
        public final int size;

        public MetadataSampleInfo(long j, int i) {
            this.presentationTimeDeltaUs = j;
            this.size = i;
        }
    }

    private static final class TrackBundle {
        public int currentSampleInTrackRun;
        public int currentSampleIndex;
        public int currentTrackRunIndex;
        public DefaultSampleValues defaultSampleValues;
        public final TrackFragment fragment = new TrackFragment();
        public final TrackOutput output;
        public Track track;

        public TrackBundle(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        public void init(Track track, DefaultSampleValues defaultSampleValues) {
            this.track = (Track) Assertions.checkNotNull(track);
            this.defaultSampleValues = (DefaultSampleValues) Assertions.checkNotNull(defaultSampleValues);
            this.output.format(track.format);
            reset();
        }

        public void reset() {
            this.fragment.reset();
            this.currentSampleIndex = 0;
            this.currentTrackRunIndex = 0;
            this.currentSampleInTrackRun = 0;
        }

        public void updateDrmInitData(DrmInitData drmInitData) {
            TrackEncryptionBox sampleDescriptionEncryptionBox = this.track.getSampleDescriptionEncryptionBox(this.fragment.header.sampleDescriptionIndex);
            this.output.format(this.track.format.copyWithDrmInitData(drmInitData.copyWithSchemeType(sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null)));
        }
    }
}
