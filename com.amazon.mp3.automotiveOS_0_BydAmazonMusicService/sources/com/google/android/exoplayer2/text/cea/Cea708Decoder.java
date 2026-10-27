package com.google.android.exoplayer2.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import android.util.Pair;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.fasterxml.jackson.dataformat.cbor.CBORConstants;
import com.google.android.exoplayer2.text.Cue;
import com.google.android.exoplayer2.text.Subtitle;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.text.SubtitleInputBuffer;
import com.google.android.exoplayer2.text.SubtitleOutputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CircularByteQueue;
import com.google.android.exoplayer2.util.EdgeTypeSpan;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.visualon.OSMPUtils.voOSType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import kotlin.text.Typography;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: loaded from: classes6.dex */
public final class Cea708Decoder extends CeaDecoder {
    private static final boolean DEBUG;
    private static final Logger log;
    private int ccpDataIndex;
    private int ccpSize;
    private final CueBuilder[] cueBuilders;
    private List<Cue> cues;
    private boolean cuesNeedUpdate;
    private CueBuilder currentCueBuilder;
    private int currentWindow;
    private long delayUs;
    private long inputTimestampUs;
    private List<Cue> lastCues;
    private final int selectedServiceNumber;
    private int serviceBlockSize;
    private int serviceNumber;
    private long startOfDelayUs;
    private int lastSequenceNo = -1;
    private int serviceBlockProcessingState = 0;
    private int serviceBlockDataBufferOffset = 0;
    CircularByteQueue serviceInputBufferQ = new CircularByteQueue(128);
    private int ccDataState = 0;
    private int ccDataSkipCount = 0;
    private int currentCommand = -1;
    private boolean isExtendedCommand = false;
    private final ParsableByteArray ccData = new ParsableByteArray();

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() throws SubtitleDecoderException {
        return super.dequeueInputBuffer();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() throws SubtitleDecoderException {
        return super.dequeueOutputBuffer();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public /* bridge */ /* synthetic */ void onNewSubtitleDataAvailable(long j) {
        super.onNewSubtitleDataAvailable(j);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer subtitleInputBuffer) throws SubtitleDecoderException {
        super.queueInputBuffer(subtitleInputBuffer);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.text.SubtitleDecoder
    public /* bridge */ /* synthetic */ void setPositionUs(long j) {
        super.setPositionUs(j);
    }

    static {
        Logger logger = new Logger(Logger.Module.Text, "Cea708Decoder");
        log = logger;
        DEBUG = logger.allowDebug();
    }

    public Cea708Decoder(int i, boolean z) {
        int i2 = i == -1 ? 1 : i;
        this.selectedServiceNumber = i2;
        log.i("selectedServiceNumber = " + i2 + " accessibilityChannel = " + i + " isWideAspectRatio = " + z);
        this.cueBuilders = new CueBuilder[8];
        for (int i3 = 0; i3 < 8; i3++) {
            this.cueBuilders[i3] = new CueBuilder(z);
        }
        this.currentCueBuilder = this.cueBuilders[0];
        resetCueBuilders();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder, com.google.android.exoplayer2.decoder.Decoder
    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        this.currentWindow = 0;
        this.currentCueBuilder = this.cueBuilders[0];
        resetCueBuilders();
        finishCCPacket();
        resetCCDataState();
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    protected boolean isNewSubtitleDataAvailable() {
        return this.cues != this.lastCues;
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    protected Subtitle createSubtitle() {
        List<Cue> list = this.cues;
        this.lastCues = list;
        return new CeaSubtitle(list);
    }

    @Override // com.google.android.exoplayer2.text.cea.CeaDecoder
    protected void decode(SubtitleInputBuffer subtitleInputBuffer) {
        this.inputTimestampUs = subtitleInputBuffer.timeUs;
        this.ccData.reset(subtitleInputBuffer.data.array(), subtitleInputBuffer.data.limit());
        while (this.ccData.bytesLeft() >= 3) {
            int unsignedByte = this.ccData.readUnsignedByte() & 7;
            int i = unsignedByte & 3;
            boolean z = (unsignedByte & 4) == 4;
            byte unsignedByte2 = (byte) this.ccData.readUnsignedByte();
            byte unsignedByte3 = (byte) this.ccData.readUnsignedByte();
            if (i == 2 || i == 3) {
                if (z) {
                    if (i == 3) {
                        finishCCPacket();
                        int i2 = (unsignedByte2 & 192) >> 6;
                        int i3 = this.lastSequenceNo;
                        if (i3 != -1 && i2 != (i3 + 1) % 4) {
                            resetCueBuilders();
                            log.w("discontinuity in sequence number detected : lastSequenceNo = " + this.lastSequenceNo + " sequenceNumber = " + i2);
                        }
                        this.lastSequenceNo = i2;
                        int i4 = unsignedByte2 & 63;
                        this.ccpSize = i4;
                        if (i4 == 0) {
                            this.ccpSize = WorkQueueKt.MASK;
                        } else {
                            this.ccpSize = (i4 * 2) - 1;
                        }
                        if (DEBUG) {
                            log.d("DTVCC_PACKET_START : sequenceNumber = " + i2 + " ccpSize = " + this.ccpSize);
                        }
                        processCCPacket(unsignedByte3);
                        this.ccpDataIndex = 1;
                    } else {
                        Assertions.checkArgument(i == 2);
                        if (this.ccpSize == 0) {
                            log.w("Encountered DTVCC_PACKET_DATE before DTVCC_PACKET_START, ignoring...");
                        } else {
                            processCCPacket(unsignedByte2, unsignedByte3);
                            this.ccpDataIndex += 2;
                            if (DEBUG) {
                                log.d("DTVCC_PACKET_DATA : ccpDataIndex = " + this.ccpDataIndex);
                            }
                        }
                    }
                }
            }
        }
        if (this.cuesNeedUpdate) {
            updateCues();
        }
    }

    private void finishCCPacket() {
        this.ccpDataIndex = 0;
        this.ccpSize = 0;
        resetServiceBlockState();
    }

    private void updateCues() {
        this.cuesNeedUpdate = false;
        this.cues = getDisplayCues();
        onNewSubtitleDataAvailable(this.inputTimestampUs);
    }

    private void processCCPacket(byte... bArr) {
        for (int i = 0; i < bArr.length; i++) {
            int i2 = this.serviceBlockProcessingState;
            if (i2 == 0) {
                byte b = bArr[i];
                this.serviceNumber = (b & 224) >> 5;
                this.serviceBlockSize = b & 31;
                if (DEBUG) {
                    log.d("SERVICE_BLOCK_HEADER: serviceNumber = " + this.serviceNumber + ", serviceBlockSize = " + this.serviceBlockSize);
                }
                if (this.serviceNumber == 7 && this.serviceBlockSize != 0) {
                    this.serviceBlockProcessingState = 1;
                } else if (this.serviceBlockSize != 0) {
                    this.serviceBlockProcessingState = 2;
                    this.serviceBlockDataBufferOffset = 0;
                } else {
                    this.serviceNumber = 0;
                }
            } else if (i2 == 1) {
                this.serviceNumber = bArr[i] & 63;
                if (this.serviceBlockSize != 0) {
                    this.serviceBlockProcessingState = 2;
                    this.serviceBlockDataBufferOffset = 0;
                    if (DEBUG) {
                        log.d("SERVICE_BLOCK_EXT_HEADER: serviceNumber = " + this.serviceNumber + ", serviceBlockSize = " + this.serviceBlockSize);
                    }
                } else {
                    this.serviceBlockProcessingState = 0;
                    this.serviceNumber = 0;
                }
            } else if (i2 == 2) {
                this.serviceBlockDataBufferOffset++;
                if (this.serviceNumber == this.selectedServiceNumber) {
                    processCCData(bArr[i]);
                }
                if (this.serviceBlockDataBufferOffset == this.serviceBlockSize) {
                    if (DEBUG) {
                        log.d("End of Service Block");
                    }
                    resetServiceBlockState();
                    if (this.cuesNeedUpdate) {
                        updateCues();
                    }
                }
            }
        }
    }

    private void resetServiceBlockState() {
        this.serviceBlockDataBufferOffset = 0;
        this.serviceBlockProcessingState = 0;
        this.serviceNumber = 0;
        this.serviceBlockSize = 0;
    }

    private void resetCCDataState() {
        this.currentCommand = -1;
        this.cuesNeedUpdate = false;
        this.ccDataSkipCount = 0;
        this.ccDataState = 0;
    }

    private void processCCData(byte b) {
        int i = b & 255;
        if (i == 143 || i == 142) {
            this.delayUs = 0L;
            if (i == 143) {
                this.serviceInputBufferQ.reset();
            }
        }
        if (this.serviceInputBufferQ.canWrite()) {
            this.serviceInputBufferQ.write(b);
        } else {
            log.w("Service Input buffer FULL!!!");
            this.delayUs = 0L;
        }
        long j = this.delayUs;
        if (j != 0 && this.inputTimestampUs - this.startOfDelayUs >= j) {
            this.delayUs = 0L;
        }
        if (this.delayUs != 0) {
            return;
        }
        while (this.serviceInputBufferQ.canRead(1)) {
            int i2 = this.ccDataState;
            if (i2 == 0) {
                int i3 = this.serviceInputBufferQ.read();
                this.currentCommand = i3;
                if (i3 == 143 || this.delayUs == 0) {
                    this.cuesNeedUpdate = handleCommand(i3) | this.cuesNeedUpdate;
                } else if (i3 == 142 || this.serviceInputBufferQ.size() >= 128 || this.inputTimestampUs - this.startOfDelayUs >= this.delayUs) {
                    this.delayUs = 0L;
                    this.cuesNeedUpdate |= handleCommand(this.currentCommand);
                }
            } else if (i2 == 1) {
                this.ccDataState = 0;
                this.cuesNeedUpdate |= handleCommand(this.currentCommand);
            } else if (i2 == 2) {
                this.serviceInputBufferQ.read();
                int i4 = this.ccDataSkipCount - 1;
                this.ccDataSkipCount = i4;
                if (i4 == 0) {
                    this.ccDataState = 0;
                    this.isExtendedCommand = false;
                }
            }
            if (this.ccDataState == 1) {
                return;
            }
        }
    }

    private void skipBytes(int i) {
        if (i > 0) {
            this.ccDataState = 2;
            this.ccDataSkipCount = i;
        }
    }

    private void printCommandName(int i) {
        String str;
        if (i == 0) {
            str = "Null";
        } else if (i == 3) {
            str = "EndOfText";
        } else if (i != 8) {
            switch (i) {
                case 12:
                    str = "FormFeed (Flush)";
                    break;
                case 13:
                    str = "Carriage Return";
                    break;
                case 14:
                    str = "ClearLine";
                    break;
                default:
                    switch (i) {
                        case 128:
                            str = "SetCurrentWindow 0";
                            break;
                        case voOSType.VOOSMP_PID_AUDIO_EFFECT_ENDPOINT_TYPE /* 129 */:
                            str = "SetCurrentWindow 1";
                            break;
                        case 130:
                            str = "SetCurrentWindow 2";
                            break;
                        case voOSType.VOOSMP_PID_ANALYTICS_FOUNDATION_CUID /* 131 */:
                            str = "SetCurrentWindow 3";
                            break;
                        case voOSType.VOOSMP_PID_ANALYTICS_FOUNDATION_LOCATION /* 132 */:
                            str = "SetCurrentWindow 4";
                            break;
                        case 133:
                            str = "SetCurrentWindow 5";
                            break;
                        case 134:
                            str = "SetCurrentWindow 6";
                            break;
                        case 135:
                            str = "SetCurrentWindow 7";
                            break;
                        case 136:
                            str = "ClearWindows";
                            break;
                        case voOSType.VOOSMP_PID_PREFERENCE /* 137 */:
                            str = "DisplayWindows";
                            break;
                        case 138:
                            str = "HideWindows";
                            break;
                        case voOSType.VOOSMP_PID_ANALYTICS_EXPORT /* 139 */:
                            str = "ToggleWindows";
                            break;
                        case voOSType.VOOSMP_PID_ANALYTICS_EXPORT_GET_PACKET /* 140 */:
                            str = "DeleteWindows";
                            break;
                        case voOSType.VOOSMP_PID_ANALYTICS_EXPORT_LISTENER /* 141 */:
                            str = "Delay";
                            break;
                        case 142:
                            str = "DelayCancel";
                            break;
                        case voOSType.VOOSMP_PID_SET_I_FRAME_ONLY_MODE /* 143 */:
                            str = "Reset";
                            break;
                        case voOSType.VOOSMP_PID_ANALYTICS_AGENT /* 144 */:
                            str = "SetPenAttributes";
                            break;
                        case voOSType.VOOSMP_PID_ANALYTICS_AGENT_CUID /* 145 */:
                            str = "SetPenColor";
                            break;
                        case voOSType.VOOSMP_PID_ANALYTICS_AGENT_APPID /* 146 */:
                            str = "SetPenLocation";
                            break;
                        default:
                            switch (i) {
                                case 151:
                                    str = "SetWindowAttributes";
                                    break;
                                case 152:
                                    str = "DefineWindow 0";
                                    break;
                                case GeneratorAdapter.EQ /* 153 */:
                                    str = "DefineWindow 1";
                                    break;
                                case GeneratorAdapter.NE /* 154 */:
                                    str = "DefineWindow 2";
                                    break;
                                case GeneratorAdapter.LT /* 155 */:
                                    str = "DefineWindow 3";
                                    break;
                                case 156:
                                    str = "DefineWindow 4";
                                    break;
                                case GeneratorAdapter.GT /* 157 */:
                                    str = "DefineWindow 5";
                                    break;
                                case GeneratorAdapter.LE /* 158 */:
                                    str = "DefineWindow 6";
                                    break;
                                case 159:
                                    str = "DefineWindow 7";
                                    break;
                                default:
                                    str = null;
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            str = "Backspace";
        }
        if (str != null) {
            log.d("handleCommand: " + str);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean handleCommand(int r7) {
        /*
            r6 = this;
            boolean r0 = com.google.android.exoplayer2.text.cea.Cea708Decoder.DEBUG
            if (r0 == 0) goto L7
            r6.printCommandName(r7)
        L7:
            r0 = 16
            r1 = 0
            if (r7 != r0) goto L22
            com.google.android.exoplayer2.util.CircularByteQueue r7 = r6.serviceInputBufferQ     // Catch: java.lang.IllegalStateException -> La0
            r0 = 1
            boolean r7 = r7.canRead(r0)     // Catch: java.lang.IllegalStateException -> La0
            if (r7 != 0) goto L18
            r6.ccDataState = r0     // Catch: java.lang.IllegalStateException -> La0
            return r1
        L18:
            com.google.android.exoplayer2.util.CircularByteQueue r7 = r6.serviceInputBufferQ     // Catch: java.lang.IllegalStateException -> La0
            int r7 = r7.read()     // Catch: java.lang.IllegalStateException -> La0
            r6.currentCommand = r7     // Catch: java.lang.IllegalStateException -> La0
            r6.isExtendedCommand = r0     // Catch: java.lang.IllegalStateException -> La0
        L22:
            boolean r0 = r6.isExtendedCommand     // Catch: java.lang.IllegalStateException -> La0
            r2 = 255(0xff, float:3.57E-43)
            r3 = 159(0x9f, float:2.23E-43)
            r4 = 127(0x7f, float:1.78E-43)
            r5 = 31
            if (r0 != 0) goto L61
            if (r7 > r5) goto L35
            boolean r7 = r6.handleC0Command(r7)     // Catch: java.lang.IllegalStateException -> La0
            goto L67
        L35:
            if (r7 > r4) goto L3c
            boolean r7 = r6.handleG0Character(r7)     // Catch: java.lang.IllegalStateException -> La0
            goto L67
        L3c:
            if (r7 > r3) goto L43
            boolean r7 = r6.handleC1Command(r7)     // Catch: java.lang.IllegalStateException -> La0
            goto L67
        L43:
            if (r7 > r2) goto L4a
            boolean r7 = r6.handleG1Character(r7)     // Catch: java.lang.IllegalStateException -> La0
            goto L67
        L4a:
            com.google.android.exoplayer2.util.Logger r0 = com.google.android.exoplayer2.text.cea.Cea708Decoder.log     // Catch: java.lang.IllegalStateException -> La0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.IllegalStateException -> La0
            r2.<init>()     // Catch: java.lang.IllegalStateException -> La0
            java.lang.String r3 = "Invalid base command: "
            r2.append(r3)     // Catch: java.lang.IllegalStateException -> La0
            r2.append(r7)     // Catch: java.lang.IllegalStateException -> La0
            java.lang.String r7 = r2.toString()     // Catch: java.lang.IllegalStateException -> La0
            r0.w(r7)     // Catch: java.lang.IllegalStateException -> La0
            goto La7
        L61:
            if (r7 > r5) goto L69
            boolean r7 = r6.handleC2Command(r7)     // Catch: java.lang.IllegalStateException -> La0
        L67:
            r1 = r1 | r7
            goto La7
        L69:
            if (r7 > r4) goto L76
            boolean r7 = r6.handleG2Character(r7)     // Catch: java.lang.IllegalStateException -> La0
            r7 = r7 | r1
            r6.isExtendedCommand = r1     // Catch: java.lang.IllegalStateException -> L74
        L72:
            r1 = r7
            goto La7
        L74:
            r1 = r7
            goto La0
        L76:
            if (r7 > r3) goto L7d
            boolean r7 = r6.handleC3Command(r7)     // Catch: java.lang.IllegalStateException -> La0
            goto L67
        L7d:
            if (r7 > r2) goto L87
            boolean r7 = r6.handleG3Character(r7)     // Catch: java.lang.IllegalStateException -> La0
            r7 = r7 | r1
            r6.isExtendedCommand = r1     // Catch: java.lang.IllegalStateException -> L74
            goto L72
        L87:
            com.google.android.exoplayer2.util.Logger r0 = com.google.android.exoplayer2.text.cea.Cea708Decoder.log     // Catch: java.lang.IllegalStateException -> La0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.IllegalStateException -> La0
            r2.<init>()     // Catch: java.lang.IllegalStateException -> La0
            java.lang.String r3 = "Invalid extended command: "
            r2.append(r3)     // Catch: java.lang.IllegalStateException -> La0
            r2.append(r7)     // Catch: java.lang.IllegalStateException -> La0
            java.lang.String r7 = r2.toString()     // Catch: java.lang.IllegalStateException -> La0
            r0.w(r7)     // Catch: java.lang.IllegalStateException -> La0
            r6.isExtendedCommand = r1     // Catch: java.lang.IllegalStateException -> La0
            goto La7
        La0:
            com.google.android.exoplayer2.util.Logger r7 = com.google.android.exoplayer2.text.cea.Cea708Decoder.log
            java.lang.String r0 = "CEA708 stream seems to be broken, captions might be incorrect as data in invalid"
            r7.w(r0)
        La7:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea708Decoder.handleCommand(int):boolean");
    }

    private boolean handleC0Command(int i) {
        if (i != 0) {
            if (i != 3) {
                if (i == 8) {
                    this.currentCueBuilder.backspace();
                } else {
                    switch (i) {
                        case 12:
                            this.cueBuilders[this.currentWindow].clear();
                            this.currentCueBuilder.setPenLocation(0, 0);
                            break;
                        case 13:
                            this.currentCueBuilder.append('\n');
                            break;
                        case 14:
                            this.currentCueBuilder.hcr();
                            break;
                        default:
                            if (i >= 17 && i <= 23) {
                                log.w("Currently unsupported COMMAND_EXT1 Command: " + i);
                                skipBytes(1);
                            } else if (i >= 24 && i <= 31) {
                                log.w("Currently unsupported COMMAND_P16 Command: " + i);
                                skipBytes(2);
                            } else {
                                log.w("Invalid C0 command: " + i);
                            }
                            break;
                    }
                }
            } else {
                updateCues();
            }
        }
        return false;
    }

    private boolean handleC1Command(int i) {
        switch (i) {
            case 128:
            case voOSType.VOOSMP_PID_AUDIO_EFFECT_ENDPOINT_TYPE /* 129 */:
            case 130:
            case voOSType.VOOSMP_PID_ANALYTICS_FOUNDATION_CUID /* 131 */:
            case voOSType.VOOSMP_PID_ANALYTICS_FOUNDATION_LOCATION /* 132 */:
            case 133:
            case 134:
            case 135:
                int i2 = i - 128;
                if (this.currentWindow != i2) {
                    this.currentWindow = i2;
                    this.currentCueBuilder = this.cueBuilders[i2];
                    if (DEBUG) {
                        log.d("Setting current window to " + i2);
                    }
                }
                break;
            case 136:
                if (!this.serviceInputBufferQ.canRead(1)) {
                    this.ccDataState = 1;
                } else {
                    int i3 = this.serviceInputBufferQ.read();
                    for (int i4 = 0; i4 < 8; i4++) {
                        if (((1 << i4) & i3) != 0) {
                            this.cueBuilders[i4].clear();
                            if (DEBUG) {
                                log.d("Clearing window with ID: " + i4);
                            }
                        }
                    }
                }
                break;
            case voOSType.VOOSMP_PID_PREFERENCE /* 137 */:
                if (!this.serviceInputBufferQ.canRead(1)) {
                    this.ccDataState = 1;
                } else {
                    int i5 = this.serviceInputBufferQ.read();
                    for (int i6 = 0; i6 < 8; i6++) {
                        if (((1 << i6) & i5) != 0) {
                            if (!this.cueBuilders[i6].defined) {
                                if (DEBUG) {
                                    log.d("DisplayWindow command skipped for undefined window ID: " + i6);
                                }
                            } else {
                                this.cueBuilders[i6].setVisibility(true);
                                if (DEBUG) {
                                    log.d("Showing window with ID: " + i6);
                                }
                            }
                        }
                    }
                }
                break;
            case 138:
                if (!this.serviceInputBufferQ.canRead(1)) {
                    this.ccDataState = 1;
                } else {
                    int i7 = this.serviceInputBufferQ.read();
                    for (int i8 = 0; i8 < 8; i8++) {
                        if (((1 << i8) & i7) != 0) {
                            this.cueBuilders[i8].setVisibility(false);
                            if (DEBUG) {
                                log.d("Hiding window with ID: " + i8);
                            }
                        }
                    }
                }
                break;
            case voOSType.VOOSMP_PID_ANALYTICS_EXPORT /* 139 */:
                if (!this.serviceInputBufferQ.canRead(1)) {
                    this.ccDataState = 1;
                } else {
                    int i9 = this.serviceInputBufferQ.read();
                    for (int i10 = 0; i10 < 8; i10++) {
                        if (((1 << i10) & i9) != 0) {
                            CueBuilder cueBuilder = this.cueBuilders[i10];
                            if (!cueBuilder.defined) {
                                if (DEBUG) {
                                    log.d("ToggleWindow command skipped for undefined window ID: " + i10);
                                }
                            } else {
                                cueBuilder.setVisibility(!cueBuilder.isVisible());
                                if (DEBUG) {
                                    log.d("Toggling window with ID: " + i10);
                                }
                            }
                        }
                    }
                }
                break;
            case voOSType.VOOSMP_PID_ANALYTICS_EXPORT_GET_PACKET /* 140 */:
                if (!this.serviceInputBufferQ.canRead(1)) {
                    this.ccDataState = 1;
                } else {
                    int i11 = this.serviceInputBufferQ.read();
                    for (int i12 = 0; i12 < 8; i12++) {
                        if (((1 << i12) & i11) != 0) {
                            this.cueBuilders[i12].reset();
                            if (DEBUG) {
                                log.d("Deleting window: " + i12);
                            }
                        }
                    }
                }
                break;
            case voOSType.VOOSMP_PID_ANALYTICS_EXPORT_LISTENER /* 141 */:
                if (!this.serviceInputBufferQ.canRead(1)) {
                    this.ccDataState = 1;
                } else {
                    this.delayUs = ((long) this.serviceInputBufferQ.read()) * 100000;
                    this.startOfDelayUs = this.inputTimestampUs;
                }
                break;
            case 142:
            case voOSType.VOOSMP_PID_ANALYTICS_INFO /* 147 */:
            case voOSType.VOOSMP_PID_ANALYTICS_LICENSE_COUNT /* 148 */:
            case 149:
            case 150:
            default:
                log.w("Invalid C1 command: " + i);
                break;
            case voOSType.VOOSMP_PID_SET_I_FRAME_ONLY_MODE /* 143 */:
                resetCueBuilders();
                break;
            case voOSType.VOOSMP_PID_ANALYTICS_AGENT /* 144 */:
                if (!this.currentCueBuilder.isDefined()) {
                    skipBytes(2);
                } else if (!this.serviceInputBufferQ.canRead(2)) {
                    this.ccDataState = 1;
                } else {
                    handleSetPenAttributes();
                }
                break;
            case voOSType.VOOSMP_PID_ANALYTICS_AGENT_CUID /* 145 */:
                if (!this.currentCueBuilder.isDefined()) {
                    skipBytes(3);
                } else if (!this.serviceInputBufferQ.canRead(3)) {
                    this.ccDataState = 1;
                } else {
                    handleSetPenColor();
                }
                break;
            case voOSType.VOOSMP_PID_ANALYTICS_AGENT_APPID /* 146 */:
                if (!this.currentCueBuilder.isDefined()) {
                    skipBytes(2);
                } else if (!this.serviceInputBufferQ.canRead(2)) {
                    this.ccDataState = 1;
                } else {
                    handleSetPenLocation();
                }
                break;
            case 151:
                if (!this.currentCueBuilder.isDefined()) {
                    skipBytes(4);
                } else if (!this.serviceInputBufferQ.canRead(4)) {
                    this.ccDataState = 1;
                } else {
                    handleSetWindowAttributes();
                }
                break;
            case 152:
            case GeneratorAdapter.EQ /* 153 */:
            case GeneratorAdapter.NE /* 154 */:
            case GeneratorAdapter.LT /* 155 */:
            case 156:
            case GeneratorAdapter.GT /* 157 */:
            case GeneratorAdapter.LE /* 158 */:
            case 159:
                if (!this.serviceInputBufferQ.canRead(6)) {
                    this.ccDataState = 1;
                } else {
                    int i13 = i - 152;
                    handleDefineWindow(i13);
                    if (this.currentWindow != i13) {
                        this.currentWindow = i13;
                        this.currentCueBuilder = this.cueBuilders[i13];
                    }
                }
                break;
        }
        return this.ccDataState == 0;
    }

    private boolean handleC2Command(int i) {
        if (i <= 7) {
            return false;
        }
        if (i <= 15) {
            skipBytes(1);
            return false;
        }
        if (i <= 23) {
            skipBytes(2);
            return false;
        }
        if (i > 31) {
            return false;
        }
        skipBytes(3);
        return false;
    }

    private boolean handleC3Command(int i) {
        if (i <= 135) {
            skipBytes(4);
        } else if (i <= 143) {
            skipBytes(5);
        } else if (i <= 159) {
            if (!this.serviceInputBufferQ.canRead(1)) {
                this.ccDataState = 1;
                return false;
            }
            skipBytes(this.serviceInputBufferQ.read() & 63);
        }
        return false;
    }

    private boolean handleG0Character(int i) {
        if (i == 127) {
            this.currentCueBuilder.append((char) 9835);
            return true;
        }
        this.currentCueBuilder.append((char) (i & 255));
        return true;
    }

    private boolean handleG1Character(int i) {
        this.currentCueBuilder.append((char) (i & 255));
        return true;
    }

    private boolean handleG2Character(int i) {
        if (i == 32) {
            this.currentCueBuilder.append(' ');
            return true;
        }
        if (i == 33) {
            this.currentCueBuilder.append(Typography.nbsp);
            return true;
        }
        if (i == 37) {
            this.currentCueBuilder.append(Typography.ellipsis);
            return true;
        }
        if (i == 42) {
            this.currentCueBuilder.append((char) 352);
            return true;
        }
        if (i == 44) {
            this.currentCueBuilder.append((char) 338);
            return true;
        }
        if (i == 63) {
            this.currentCueBuilder.append((char) 376);
            return true;
        }
        if (i == 57) {
            this.currentCueBuilder.append(Typography.tm);
            return true;
        }
        if (i == 58) {
            this.currentCueBuilder.append((char) 353);
            return true;
        }
        if (i == 60) {
            this.currentCueBuilder.append((char) 339);
            return true;
        }
        if (i != 61) {
            switch (i) {
                case 48:
                    this.currentCueBuilder.append((char) 9608);
                    return true;
                case 49:
                    this.currentCueBuilder.append(Typography.leftSingleQuote);
                    return true;
                case 50:
                    this.currentCueBuilder.append(Typography.rightSingleQuote);
                    return true;
                case 51:
                    this.currentCueBuilder.append(Typography.leftDoubleQuote);
                    return true;
                case 52:
                    this.currentCueBuilder.append(Typography.rightDoubleQuote);
                    return true;
                case 53:
                    this.currentCueBuilder.append(Typography.bullet);
                    return true;
                default:
                    switch (i) {
                        case 118:
                            this.currentCueBuilder.append((char) 8539);
                            return true;
                        case 119:
                            this.currentCueBuilder.append((char) 8540);
                            return true;
                        case 120:
                            this.currentCueBuilder.append((char) 8541);
                            return true;
                        case 121:
                            this.currentCueBuilder.append((char) 8542);
                            return true;
                        case 122:
                            this.currentCueBuilder.append((char) 9474);
                            return true;
                        case 123:
                            this.currentCueBuilder.append((char) 9488);
                            return true;
                        case 124:
                            this.currentCueBuilder.append((char) 9492);
                            return true;
                        case 125:
                            this.currentCueBuilder.append((char) 9472);
                            return true;
                        case GeneratorAdapter.AND /* 126 */:
                            this.currentCueBuilder.append((char) 9496);
                            return true;
                        case WorkQueueKt.MASK /* 127 */:
                            this.currentCueBuilder.append((char) 9484);
                            return true;
                        default:
                            log.w("Invalid G2 character: " + i);
                            return false;
                    }
            }
        }
        this.currentCueBuilder.append((char) 8480);
        return true;
    }

    private boolean handleG3Character(int i) {
        if (i == 160) {
            this.currentCueBuilder.append((char) 13252);
            return true;
        }
        log.w("Invalid G3 character: " + i);
        this.currentCueBuilder.append('_');
        return true;
    }

    private void handleSetPenAttributes() {
        int i = this.serviceInputBufferQ.read();
        int i2 = (i & 240) >> 4;
        int i3 = (i & 12) >> 2;
        int i4 = i & 3;
        int i5 = this.serviceInputBufferQ.read();
        this.currentCueBuilder.setPenAttributes(i2, i3, i4, ((i5 & 128) >> 7) == 1, ((i5 & 64) >> 6) == 1, (i5 & 56) >> 3, i5 & 7);
    }

    private void handleSetPenColor() {
        int i = this.serviceInputBufferQ.read();
        int argbColorFromCeaColor = CueBuilder.getArgbColorFromCeaColor((i & 48) >> 4, (i & 12) >> 2, i & 3, (i & 192) >> 6);
        int i2 = this.serviceInputBufferQ.read();
        int argbColorFromCeaColor2 = CueBuilder.getArgbColorFromCeaColor((i2 & 48) >> 4, (i2 & 12) >> 2, i2 & 3, (i2 & 192) >> 6);
        int i3 = this.serviceInputBufferQ.read();
        this.currentCueBuilder.setPenColor(argbColorFromCeaColor, argbColorFromCeaColor2, CueBuilder.getArgbColorFromCeaColor((i3 & 48) >> 4, (i3 & 12) >> 2, i3 & 3));
    }

    private void handleSetPenLocation() {
        this.currentCueBuilder.setPenLocation(this.serviceInputBufferQ.read() & 15, this.serviceInputBufferQ.read() & 63);
    }

    private void handleSetWindowAttributes() {
        int i = this.serviceInputBufferQ.read();
        int argbColorFromCeaColor = CueBuilder.getArgbColorFromCeaColor((i & 48) >> 4, (i & 12) >> 2, i & 3, (i & 192) >> 6);
        int i2 = this.serviceInputBufferQ.read();
        int i3 = (i2 & 192) >> 6;
        int argbColorFromCeaColor2 = CueBuilder.getArgbColorFromCeaColor((i2 & 48) >> 4, (i2 & 12) >> 2, i2 & 3);
        int i4 = this.serviceInputBufferQ.read();
        if (((i4 & 128) >> 7) == 1) {
            i3 |= 4;
        }
        int i5 = i3;
        boolean z = ((i4 & 64) >> 6) == 1;
        this.serviceInputBufferQ.read();
        this.currentCueBuilder.setWindowAttributes(argbColorFromCeaColor, argbColorFromCeaColor2, z, i5, (i4 & 48) >> 4, (i4 & 12) >> 2, i4 & 3);
    }

    private void handleDefineWindow(int i) {
        CueBuilder cueBuilder = this.cueBuilders[i];
        int i2 = this.serviceInputBufferQ.read();
        boolean z = ((i2 & 32) >> 5) == 1;
        boolean z2 = ((i2 & 16) >> 4) == 1;
        boolean z3 = ((i2 & 8) >> 3) == 1;
        int i3 = i2 & 7;
        int i4 = this.serviceInputBufferQ.read();
        boolean z4 = ((i4 & 128) >> 7) == 1;
        int i5 = i4 & WorkQueueKt.MASK;
        int i6 = this.serviceInputBufferQ.read();
        int i7 = this.serviceInputBufferQ.read();
        int i8 = (i7 & 240) >> 4;
        int i9 = i7 & 15;
        int i10 = this.serviceInputBufferQ.read() & 63;
        int i11 = this.serviceInputBufferQ.read();
        cueBuilder.defineWindow(z, z2, z3, i3, z4, i5, i6, i9, i10, i8, (i11 & 56) >> 3, i11 & 7);
    }

    private List<Cue> getDisplayCues() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 8; i++) {
            if (this.cueBuilders[i].isVisible()) {
                arrayList.add(this.cueBuilders[i].build());
            }
        }
        Collections.sort(arrayList);
        return Collections.unmodifiableList(arrayList);
    }

    private void resetCueBuilders() {
        for (int i = 0; i < 8; i++) {
            this.cueBuilders[i].reset();
        }
        this.delayUs = 0L;
        this.serviceInputBufferQ.reset();
    }

    private static final class CueBuilder {
        public static final int COLOR_SOLID_BLACK;
        public static final int COLOR_SOLID_WHITE = getArgbColorFromCeaColor(2, 2, 2, 0);
        public static final int COLOR_TRANSPARENT;
        private static final int[] PEN_STYLE_BACKGROUND;
        private static final int[] PEN_STYLE_EDGE_TYPE;
        private static final int[] PEN_STYLE_FONT_STYLE;
        private static final int[] WINDOW_STYLE_FILL;
        private static final int[] WINDOW_STYLE_JUSTIFICATION;
        private static final int[] WINDOW_STYLE_PRINT_DIRECTION;
        private static final int[] WINDOW_STYLE_SCROLL_DIRECTION;
        private static final boolean[] WINDOW_STYLE_WORD_WRAP;
        private int anchorId;
        private int backgroundColor;
        private int backgroundColorStartPosition;
        private int column;
        private int columnCount;
        private boolean defined;
        private int edgeColor;
        private int edgeType;
        private int edgeTypeStartPosition;
        private int fontStyle;
        private int fontStyleStartPosition;
        private int foregroundColor;
        private int foregroundColorStartPosition;
        private int horizontalAnchor;
        private int horizontalSize;
        private boolean isWideAspectRatio;
        private int italicsStartPosition;
        private int justification;
        private int maxColumns;
        private int offset;
        private int offsetStartPosition;
        private int penSize;
        private int penStyleId;
        private int printDirection;
        private int priority;
        private boolean relativePositioning;
        private int row;
        private int rowCount;
        private boolean rowLock;
        private int scrollDirection;
        private int sizeStartPosition;
        private int underlineStartPosition;
        private int verticalAnchor;
        private boolean visible;
        private int windowBorderColor;
        private int windowBorderType;
        private int windowFillColor;
        private int windowStyleId;
        private final LinkedList<Pair<Integer, CharSequence>> rolledUpCaptions = new LinkedList<>();
        private final SpannableStringBuilder captionStringBuilder = new SpannableStringBuilder();

        private static float getFontScaleFromPenSize(int i) {
            if (i != 0) {
                return i != 2 ? 1.0f : 1.21f;
            }
            return 0.76f;
        }

        private String getFontStyleName(int i) {
            switch (i) {
                case 2:
                    return "serif";
                case 3:
                    return "sans-serif-monospace";
                case 4:
                    return "sans-serif";
                case 5:
                    return "casual";
                case 6:
                    return "cursive";
                case 7:
                    return "sans-serif-smallcaps";
                default:
                    return "serif-monospace";
            }
        }

        static {
            int argbColorFromCeaColor = getArgbColorFromCeaColor(0, 0, 0, 0);
            COLOR_SOLID_BLACK = argbColorFromCeaColor;
            int argbColorFromCeaColor2 = getArgbColorFromCeaColor(0, 0, 0, 3);
            COLOR_TRANSPARENT = argbColorFromCeaColor2;
            WINDOW_STYLE_JUSTIFICATION = new int[]{0, 0, 2, 0, 0, 2, 0};
            WINDOW_STYLE_PRINT_DIRECTION = new int[]{0, 0, 0, 0, 0, 0, 2};
            WINDOW_STYLE_SCROLL_DIRECTION = new int[]{3, 3, 3, 3, 3, 3, 1};
            WINDOW_STYLE_WORD_WRAP = new boolean[]{false, false, false, true, true, true, false};
            WINDOW_STYLE_FILL = new int[]{argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor, argbColorFromCeaColor};
            PEN_STYLE_FONT_STYLE = new int[]{0, 1, 2, 3, 4, 3, 4};
            PEN_STYLE_EDGE_TYPE = new int[]{0, 0, 0, 0, 0, 3, 3};
            PEN_STYLE_BACKGROUND = new int[]{argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor, argbColorFromCeaColor2, argbColorFromCeaColor2};
        }

        public CueBuilder(boolean z) {
            if (z) {
                this.maxColumns = 42;
                this.horizontalSize = ContentCatalogStatus.SONIC_RUSH_OR_NOT_BOUNDARY;
            } else {
                this.maxColumns = 32;
                this.horizontalSize = CBORConstants.PREFIX_TYPE_OBJECT;
            }
            this.isWideAspectRatio = z;
            reset();
        }

        public void reset() {
            clear();
            this.defined = false;
            this.visible = false;
            this.priority = 4;
            this.relativePositioning = false;
            this.verticalAnchor = 0;
            this.horizontalAnchor = 0;
            this.anchorId = 0;
            this.rowCount = 15;
            this.rowLock = true;
            this.justification = 0;
            this.windowStyleId = 0;
            this.penStyleId = 0;
            int i = COLOR_SOLID_BLACK;
            this.windowFillColor = i;
            this.scrollDirection = 3;
            this.printDirection = 0;
            this.columnCount = this.maxColumns;
            this.windowBorderColor = COLOR_TRANSPARENT;
            this.windowBorderType = 0;
            this.penSize = 1;
            this.offset = 1;
            this.fontStyle = 0;
            this.foregroundColor = COLOR_SOLID_WHITE;
            this.backgroundColor = i;
        }

        public void clear() {
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.clear();
            this.sizeStartPosition = -1;
            this.offsetStartPosition = -1;
            this.fontStyleStartPosition = -1;
            this.edgeTypeStartPosition = -1;
            this.column = 0;
            this.italicsStartPosition = -1;
            this.underlineStartPosition = -1;
            this.foregroundColorStartPosition = -1;
            this.backgroundColorStartPosition = -1;
            this.row = 0;
        }

        public boolean isDefined() {
            return this.defined;
        }

        public void setVisibility(boolean z) {
            this.visible = z;
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void defineWindow(boolean z, boolean z2, boolean z3, int i, boolean z4, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            if (Cea708Decoder.DEBUG) {
                Cea708Decoder.log.d(" defineWindow: visible = " + z + " priority = " + i + " relativePositioning = " + z4 + " verticalAnchor = " + i2 + " horizontalAnchor = " + i3 + " rowCount = " + i4 + " columnCount = " + i5 + " anchorId = " + i6 + " windowStyleId = " + i7 + " penStyleId = " + i8);
            }
            this.defined = true;
            this.visible = z;
            this.rowLock = z2;
            this.priority = i;
            this.relativePositioning = z4;
            this.verticalAnchor = i2;
            this.horizontalAnchor = i3;
            this.anchorId = i6;
            int i9 = i4 + 1;
            if (this.rowCount != i9) {
                this.rowCount = i9;
                while (true) {
                    if ((!z2 || this.rolledUpCaptions.size() < this.rowCount) && this.rolledUpCaptions.size() < 15) {
                        break;
                    } else {
                        this.rolledUpCaptions.remove(0);
                    }
                }
            }
            this.columnCount = Math.min(i5 + 1, this.maxColumns);
            if (i7 != 0 && this.windowStyleId != i7) {
                this.windowStyleId = i7;
                int i10 = i7 - 1;
                setWindowAttributes(WINDOW_STYLE_FILL[i10], COLOR_TRANSPARENT, WINDOW_STYLE_WORD_WRAP[i10], 0, WINDOW_STYLE_PRINT_DIRECTION[i10], WINDOW_STYLE_SCROLL_DIRECTION[i10], WINDOW_STYLE_JUSTIFICATION[i10]);
            }
            if (i8 == 0 || this.penStyleId == i8) {
                return;
            }
            this.penStyleId = i8;
            int i11 = i8 - 1;
            setPenAttributes(0, 1, 1, false, false, PEN_STYLE_EDGE_TYPE[i11], PEN_STYLE_FONT_STYLE[i11]);
            setPenColor(COLOR_SOLID_WHITE, PEN_STYLE_BACKGROUND[i11], COLOR_SOLID_BLACK);
        }

        public void setWindowAttributes(int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
            this.windowFillColor = i;
            if (Cea708Decoder.DEBUG) {
                Cea708Decoder.log.d("setWindowAttributes: fillColor = " + i + " borderColor = " + i2 + " wordWrapToggle = " + z + " borderType = " + i3 + " printDirection = " + i4 + " scrollDirection = " + i5 + " justification = " + i6);
            }
            this.windowBorderColor = i2;
            this.windowBorderType = i3;
            this.scrollDirection = i5;
            this.printDirection = i4;
            this.justification = i6;
        }

        public void setPenAttributes(int i, int i2, int i3, boolean z, boolean z2, int i4, int i5) {
            if (Cea708Decoder.DEBUG) {
                Cea708Decoder.log.d("setPenAttributes: textTag = " + i + " offset = " + i2 + " penSize = " + i3 + " italicsToggle = " + z + " underlineToggle = " + z2 + " edgeType = " + i4 + " fontStyle = " + i5);
            }
            int i6 = this.fontStyleStartPosition;
            if (i6 != -1 && this.fontStyle != i5 && i6 < this.captionStringBuilder.length()) {
                this.captionStringBuilder.setSpan(new TypefaceSpan(getFontStyleName(this.fontStyle)), this.fontStyleStartPosition, this.captionStringBuilder.length(), 33);
                this.fontStyleStartPosition = -1;
            }
            if (i5 != 0) {
                this.fontStyleStartPosition = this.captionStringBuilder.length();
            }
            this.fontStyle = i5;
            int i7 = this.offsetStartPosition;
            if (i7 != -1 && i2 != this.offset && i7 < this.captionStringBuilder.length()) {
                this.captionStringBuilder.setSpan(this.offset == 0 ? new SubscriptSpan() : new SuperscriptSpan(), this.offsetStartPosition, this.captionStringBuilder.length(), 33);
                this.offsetStartPosition = -1;
            }
            if (i2 != 1) {
                this.offsetStartPosition = this.captionStringBuilder.length();
            }
            this.offset = i2;
            int i8 = this.sizeStartPosition;
            if (i8 != -1 && i3 != this.penSize && i8 < this.captionStringBuilder.length()) {
                this.captionStringBuilder.setSpan(new RelativeSizeSpan(getFontScaleFromPenSize(this.penSize)), this.sizeStartPosition, this.captionStringBuilder.length(), 33);
                this.sizeStartPosition = -1;
            }
            if (i3 != 1) {
                this.sizeStartPosition = this.captionStringBuilder.length();
            }
            this.penSize = i3;
            int i9 = this.edgeTypeStartPosition;
            if (i9 != -1 && i4 != this.edgeType && i9 < this.captionStringBuilder.length()) {
                this.captionStringBuilder.setSpan(new EdgeTypeSpan(this.edgeType, this.edgeColor), this.edgeTypeStartPosition, this.captionStringBuilder.length(), 33);
                this.edgeTypeStartPosition = -1;
            }
            if (i4 != 0) {
                this.edgeTypeStartPosition = this.captionStringBuilder.length();
            }
            this.edgeType = i4;
            if (this.italicsStartPosition != -1) {
                if (!z) {
                    this.captionStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, this.captionStringBuilder.length(), 33);
                    this.italicsStartPosition = -1;
                }
            } else if (z) {
                this.italicsStartPosition = this.captionStringBuilder.length();
            }
            if (this.underlineStartPosition == -1) {
                if (z2) {
                    this.underlineStartPosition = this.captionStringBuilder.length();
                }
            } else {
                if (z2) {
                    return;
                }
                this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, this.captionStringBuilder.length(), 33);
                this.underlineStartPosition = -1;
            }
        }

        public void setPenColor(int i, int i2, int i3) {
            if (Cea708Decoder.DEBUG) {
                Cea708Decoder.log.d("setPenColor: foregroundColor = " + i + " backgroundColor = " + i2 + " edgeColor = " + i3);
            }
            if (this.foregroundColorStartPosition != -1 && this.foregroundColor != i) {
                this.captionStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i != COLOR_SOLID_WHITE) {
                this.foregroundColorStartPosition = this.captionStringBuilder.length();
                this.foregroundColor = i;
            } else {
                this.foregroundColorStartPosition = -1;
            }
            if (this.backgroundColorStartPosition != -1 && this.backgroundColor != i2) {
                this.captionStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, this.captionStringBuilder.length(), 33);
            }
            if (i2 != COLOR_SOLID_BLACK) {
                this.backgroundColorStartPosition = this.captionStringBuilder.length();
                this.backgroundColor = i2;
            } else {
                this.backgroundColorStartPosition = -1;
            }
            int i4 = this.edgeTypeStartPosition;
            if (i4 != -1 && i3 != this.edgeColor && i4 < this.captionStringBuilder.length()) {
                this.captionStringBuilder.setSpan(new EdgeTypeSpan(this.edgeType, this.edgeColor), this.edgeTypeStartPosition, this.captionStringBuilder.length(), 33);
                this.edgeTypeStartPosition = -1;
            }
            if (this.edgeType != 0) {
                this.edgeTypeStartPosition = this.captionStringBuilder.length();
            }
            this.edgeColor = i3;
        }

        public void setPenLocation(int i, int i2) {
            if (Cea708Decoder.DEBUG) {
                Cea708Decoder.log.d("setPenLocation : row = " + i + " column = " + i2);
            }
            int i3 = i - this.row;
            for (int i4 = 0; i4 < i3; i4++) {
                append('\n');
            }
            this.row = i;
            if (this.captionStringBuilder.length() == 0) {
                this.column = i2;
            }
        }

        public void backspace() {
            int length = this.captionStringBuilder.length();
            if (length > 0) {
                this.captionStringBuilder.delete(length - 1, length);
            }
        }

        public void hcr() {
            this.captionStringBuilder.clear();
        }

        public void append(char c) {
            if (!isDefined()) {
                Cea708Decoder.log.w("Window is not initialized with DefineWindow() yet, dropping char:" + c);
                return;
            }
            if (Cea708Decoder.DEBUG) {
                Logger logger = Cea708Decoder.log;
                Locale locale = Locale.US;
                Object[] objArr = new Object[2];
                objArr[0] = c == '\n' ? "\\n" : Character.valueOf(c);
                objArr[1] = this.captionStringBuilder.toString();
                logger.d(String.format(locale, "Incoming char: '%s' for text: '%s'", objArr));
            }
            if (c == '\n') {
                if (this.scrollDirection == 2) {
                    this.rolledUpCaptions.addFirst(new Pair<>(Integer.valueOf(this.column), buildSpannableString()));
                    int i = this.row;
                    if (i > 0) {
                        this.row = i - 1;
                    }
                } else {
                    this.rolledUpCaptions.add(new Pair<>(Integer.valueOf(this.column), buildSpannableString()));
                    if ((this.rowLock && this.row < this.rowCount - 1) || this.row < 14) {
                        this.row++;
                    }
                }
                this.column = 0;
                this.captionStringBuilder.clear();
                if (this.sizeStartPosition != -1) {
                    this.sizeStartPosition = 0;
                }
                if (this.fontStyleStartPosition != -1) {
                    this.fontStyleStartPosition = 0;
                }
                if (this.offsetStartPosition != -1) {
                    this.offsetStartPosition = 0;
                }
                if (this.edgeTypeStartPosition != -1) {
                    this.edgeTypeStartPosition = 0;
                }
                if (this.italicsStartPosition != -1) {
                    this.italicsStartPosition = 0;
                }
                if (this.underlineStartPosition != -1) {
                    this.underlineStartPosition = 0;
                }
                if (this.foregroundColorStartPosition != -1) {
                    this.foregroundColorStartPosition = 0;
                }
                if (this.backgroundColorStartPosition != -1) {
                    this.backgroundColorStartPosition = 0;
                }
                while (true) {
                    if ((!this.rowLock || this.rolledUpCaptions.size() < this.rowCount) && this.rolledUpCaptions.size() < 15) {
                        return;
                    }
                    if (this.scrollDirection == 2) {
                        this.rolledUpCaptions.removeLast();
                    } else {
                        this.rolledUpCaptions.remove(0);
                    }
                }
            } else {
                if (this.captionStringBuilder.length() < this.columnCount) {
                    if (this.printDirection == 0) {
                        this.captionStringBuilder.append(c);
                        return;
                    } else {
                        this.captionStringBuilder.insert(0, (CharSequence) String.valueOf(c));
                        return;
                    }
                }
                append('\n');
                append(c);
            }
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.captionStringBuilder);
            int length = spannableStringBuilder.length();
            if (length > 0) {
                int i = this.fontStyleStartPosition;
                if (i != -1 && i <= length) {
                    spannableStringBuilder.setSpan(new TypefaceSpan(getFontStyleName(this.fontStyle)), this.fontStyleStartPosition, length, 33);
                }
                int i2 = this.sizeStartPosition;
                if (i2 != -1 && i2 <= length) {
                    spannableStringBuilder.setSpan(new RelativeSizeSpan(getFontScaleFromPenSize(this.penSize)), this.sizeStartPosition, length, 33);
                }
                int i3 = this.offsetStartPosition;
                if (i3 != -1 && i3 <= length) {
                    spannableStringBuilder.setSpan(this.offset == 0 ? new SubscriptSpan() : new SuperscriptSpan(), this.offsetStartPosition, length, 33);
                }
                int i4 = this.edgeTypeStartPosition;
                if (i4 != -1 && i4 <= length) {
                    spannableStringBuilder.setSpan(new EdgeTypeSpan(this.edgeType, this.edgeColor), this.edgeTypeStartPosition, length, 33);
                }
                int i5 = this.italicsStartPosition;
                if (i5 != -1 && i5 <= length) {
                    spannableStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, length, 33);
                }
                int i6 = this.underlineStartPosition;
                if (i6 != -1 && i6 <= length) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, length, 33);
                }
                int i7 = this.foregroundColorStartPosition;
                if (i7 != -1 && i7 <= length) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, length, 33);
                }
                int i8 = this.backgroundColorStartPosition;
                if (i8 != -1 && i8 <= length) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, length, 33);
                }
            }
            return new SpannableString(spannableStringBuilder);
        }

        public Cea708Cue build() {
            Layout.Alignment alignment;
            float f;
            float f2;
            int i;
            LinkedList linkedList = new LinkedList(this.rolledUpCaptions);
            if (this.scrollDirection == 2) {
                linkedList.addFirst(new Pair(Integer.valueOf(this.column), buildSpannableString()));
            } else {
                linkedList.add(new Pair(Integer.valueOf(this.column), buildSpannableString()));
            }
            int i2 = this.justification;
            if (i2 == 0) {
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else if (i2 == 1) {
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            } else if (i2 != 2) {
                if (i2 != 3) {
                    throw new IllegalArgumentException("Unexpected justification value: " + this.justification);
                }
                alignment = Layout.Alignment.ALIGN_NORMAL;
            } else {
                alignment = Layout.Alignment.ALIGN_CENTER;
            }
            Layout.Alignment alignment2 = alignment;
            if (this.relativePositioning) {
                f = this.horizontalAnchor / 100.0f;
                f2 = this.verticalAnchor / 100.0f;
            } else {
                f = this.horizontalAnchor / this.horizontalSize;
                f2 = this.verticalAnchor / 75.0f;
            }
            float f3 = f;
            int i3 = this.anchorId;
            int i4 = i3 <= 2 ? 0 : i3 <= 5 ? 1 : 2;
            if (i3 % 3 == 0) {
                i = 0;
            } else {
                i = i3 % 3 == 1 ? 1 : 2;
            }
            if (Cea708Decoder.DEBUG) {
                StringBuilder sb = new StringBuilder();
                Iterator it = linkedList.iterator();
                while (it.hasNext()) {
                    sb.append(((Pair) it.next()).second + "\n");
                }
                Cea708Decoder.log.d("line = " + f2 + " position = " + f3 + " rowCount = " + this.rowCount + " columnCount = " + this.columnCount + " cueRows count = " + linkedList.size() + " cueText = " + sb.toString());
            }
            return new Cea708Cue(linkedList, alignment2, f2, 0, i4, f3, i, Float.MIN_VALUE, true, this.windowFillColor, this.windowBorderColor, this.windowBorderType, this.priority, this.rowCount, this.columnCount, this.isWideAspectRatio);
        }

        public static int getArgbColorFromCeaColor(int i, int i2, int i3) {
            return getArgbColorFromCeaColor(i, i2, i3, 0);
        }

        /* JADX WARN: Removed duplicated region for block: B:12:0x001f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public static int getArgbColorFromCeaColor(int r3, int r4, int r5, int r6) {
            /*
                r0 = 0
                r1 = 4
                com.google.android.exoplayer2.util.Assertions.checkIndex(r3, r0, r1)
                com.google.android.exoplayer2.util.Assertions.checkIndex(r4, r0, r1)
                com.google.android.exoplayer2.util.Assertions.checkIndex(r5, r0, r1)
                com.google.android.exoplayer2.util.Assertions.checkIndex(r6, r0, r1)
                r1 = 255(0xff, float:3.57E-43)
                if (r6 == 0) goto L1f
                r2 = 1
                if (r6 == r2) goto L1f
                r2 = 2
                if (r6 == r2) goto L1c
                r2 = 3
                if (r6 == r2) goto L20
                goto L1f
            L1c:
                r0 = 127(0x7f, float:1.78E-43)
                goto L20
            L1f:
                r0 = r1
            L20:
                int r3 = r3 * 85
                int r4 = r4 * 85
                int r5 = r5 * 85
                int r3 = android.graphics.Color.argb(r0, r3, r4, r5)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.text.cea.Cea708Decoder.CueBuilder.getArgbColorFromCeaColor(int, int, int, int):int");
        }
    }
}
