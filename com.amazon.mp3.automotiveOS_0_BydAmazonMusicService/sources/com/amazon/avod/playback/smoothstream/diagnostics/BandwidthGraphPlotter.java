package com.amazon.avod.playback.smoothstream.diagnostics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.ViewCompat;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.dash.quality.heuristic.FragmentType;
import com.amazon.avod.content.smoothstream.manifest.Manifest;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.manifest.VideoQualityLevel;
import com.amazon.avod.media.AudioStreamType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.diagnostics.DiagnosticsController;
import com.amazon.avod.util.DataUnit;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import org.objectweb.asm.commons.GeneratorAdapter;

/* JADX INFO: loaded from: classes2.dex */
class BandwidthGraphPlotter extends GraphPlotter {
    private static final int COLOR_DARK_PINK;
    private static final int COLOR_GOLD_YELLOW;
    private static final int COLOR_LIGHT_BULE;
    private static final String HD;
    private static final String HD_1080P;
    private static final String SD;
    private static final String SUB_SD;
    private static final String UHD;
    private double mHeightRatio;
    private boolean mIsCompactViewEnabled;
    private double mLogMin;
    private int mMaxBitrate;
    private int mMaxBitrateEqualSpacedBandwidth;
    private int mMaxBitrateLinear;
    private int mMaxBitrateLogarithmic;
    private int mMinBitrate;
    private int mNumQualityLevels;
    private DiagnosticsController.BandwidthScale mScale;
    private QualityLevel[] mSortedVideoQualityLevels;
    private float mSpaceBetweenYLabels;
    private float thickStrokeWidth;
    private int mLineGraphHeight = 0;
    private Paint mBitratePoint = null;
    private Paint mUpgradeBitratePoint = null;
    private Paint mBaselineBitratePoint = null;
    private Paint mAudioBitratePoint = null;
    private Paint mBitrateLine = null;
    private Paint mBitrateCapLine = null;
    private Paint mUpgradeBitrateLine = null;
    private Paint mBaselineBitrateLine = null;
    private Paint mAudioBitrateLine = null;
    private Paint mAudioBitrateCapLine = null;
    private Paint mResolutionSchemeLine = null;
    private Paint mRectPaint = null;
    private Paint mBandwidthPoint = null;
    private Paint mBandwidthErrorPoint = null;
    private Paint mCdnSwitchedLine = null;
    private Paint mQoeTriggeredCdnSwitchedLine = null;
    private Paint mCdnSwitchedText = null;
    private Paint mBufferingPoint = null;
    private Paint mBandwidthLine = null;
    private Paint mManifestRefreshLine = null;
    private Paint mPlayHeadLine = null;
    private Paint mPlayHeadText = null;
    private Paint mResolutionPaint = null;
    private boolean mInitForSession = false;
    private final TreeMap<Integer, Integer> mBitrateIndexMap = Maps.newTreeMap();
    private final TreeMap<Integer, Integer> mResolutionColorSchemeMap = Maps.newTreeMap();
    private final Map<Integer, Float> mBitrateYMap = Maps.newHashMap();
    private final Map<Integer, Integer> mBitrateResolutionMap = Maps.newHashMap();
    private final DataPointDrawer<ErrorDataPoint> mBandwidthErrorDataDrawer = new DataPointDrawer<ErrorDataPoint>() { // from class: com.amazon.avod.playback.smoothstream.diagnostics.BandwidthGraphPlotter.1
        @Override // com.amazon.avod.playback.smoothstream.diagnostics.BandwidthGraphPlotter.DataPointDrawer
        public void drawDataPoint(Canvas canvas, ErrorDataPoint errorDataPoint, float f) {
            float f2 = BandwidthGraphPlotter.this.mOrigin.y - r0.mLineGraphHeight;
            BandwidthGraphPlotter bandwidthGraphPlotter = BandwidthGraphPlotter.this;
            canvas.drawLine(f, bandwidthGraphPlotter.mOrigin.y, f, f2, bandwidthGraphPlotter.mBandwidthErrorPoint);
            canvas.drawCircle(f, BandwidthGraphPlotter.this.createPoint(f, Math.min(r0.mMaxBitrate, DataUnit.BITS.toKiloBits(errorDataPoint.getBitrate()))).y, BandwidthGraphPlotter.this.thickStrokeWidth, BandwidthGraphPlotter.this.mBandwidthErrorPoint);
        }
    };
    private final DataPointDrawer<BufferingDataPoint> mBufferingDataDrawer = new DataPointDrawer<BufferingDataPoint>() { // from class: com.amazon.avod.playback.smoothstream.diagnostics.BandwidthGraphPlotter.2
        @Override // com.amazon.avod.playback.smoothstream.diagnostics.BandwidthGraphPlotter.DataPointDrawer
        public void drawDataPoint(Canvas canvas, BufferingDataPoint bufferingDataPoint, float f) {
            float f2 = BandwidthGraphPlotter.this.mOrigin.y - r9.mLineGraphHeight;
            BandwidthGraphPlotter bandwidthGraphPlotter = BandwidthGraphPlotter.this;
            canvas.drawLine(f, bandwidthGraphPlotter.mOrigin.y, f, f2, bandwidthGraphPlotter.mBufferingPoint);
        }
    };
    private final DataPointDrawer<CdnSwitchedDataPoint> mCdnSwitchedDataDrawer = new DataPointDrawer<CdnSwitchedDataPoint>() { // from class: com.amazon.avod.playback.smoothstream.diagnostics.BandwidthGraphPlotter.3
        @Override // com.amazon.avod.playback.smoothstream.diagnostics.BandwidthGraphPlotter.DataPointDrawer
        public void drawDataPoint(Canvas canvas, CdnSwitchedDataPoint cdnSwitchedDataPoint, float f) {
            float f2 = BandwidthGraphPlotter.this.mOrigin.y;
            while (true) {
                if (f2 <= BandwidthGraphPlotter.this.mOrigin.y - r1.mLineGraphHeight) {
                    return;
                }
                if (!"qoe".equals(cdnSwitchedDataPoint.getFailoverRuleId())) {
                    canvas.drawLine(f, f2, f, f2 - 15.0f, BandwidthGraphPlotter.this.mCdnSwitchedLine);
                } else {
                    canvas.drawLine(f, f2, f, f2 - 15.0f, BandwidthGraphPlotter.this.mQoeTriggeredCdnSwitchedLine);
                }
                f2 -= 30.0f;
            }
        }
    };
    private final DataPointDrawer<ManifestRefreshDataPoint> mManifestRefreshDataDrawer = new DataPointDrawer<ManifestRefreshDataPoint>() { // from class: com.amazon.avod.playback.smoothstream.diagnostics.BandwidthGraphPlotter.4
        @Override // com.amazon.avod.playback.smoothstream.diagnostics.BandwidthGraphPlotter.DataPointDrawer
        public void drawDataPoint(Canvas canvas, ManifestRefreshDataPoint manifestRefreshDataPoint, float f) {
            float f2 = BandwidthGraphPlotter.this.mOrigin.y - (r9.mLineGraphHeight * 0.5f);
            canvas.drawLine(f, f2, f, f2 - 10.0f, BandwidthGraphPlotter.this.mManifestRefreshLine);
        }
    };

    private interface DataPointDrawer<T extends DataPoint> {
        void drawDataPoint(Canvas canvas, T t, float f);
    }

    BandwidthGraphPlotter() {
    }

    static {
        Locale locale = Locale.US;
        SUB_SD = String.format(locale, "%s ,", VideoResolution.ResolutionBand.SUB_SD.name());
        SD = String.format(locale, "%s ,", VideoResolution.ResolutionBand.SD.name());
        HD = String.format(locale, "%s ,", VideoResolution.ResolutionBand.HD.name());
        HD_1080P = String.format(locale, "%s ,", VideoResolution.ResolutionBand.HD_1080P.name());
        UHD = String.format(locale, "%s", VideoResolution.ResolutionBand.ULTRA_HD.name());
        COLOR_LIGHT_BULE = Color.rgb(62, 112, 166);
        COLOR_GOLD_YELLOW = Color.rgb(191, GeneratorAdapter.GT, 41);
        COLOR_DARK_PINK = Color.rgb(167, 27, 64);
    }

    public void init(PointF pointF, PointF pointF2, boolean z) {
        super.initBase(pointF, pointF2, 40);
        this.mIsCompactViewEnabled = z;
        int i = COLOR_LIGHT_BULE;
        int i2 = COLOR_GOLD_YELLOW;
        int i3 = COLOR_DARK_PINK;
        this.mLineGraphHeight = (getHeight() - 40) - 25;
        this.thickStrokeWidth = getHeight() * 0.01f;
        Paint paint = new Paint();
        this.mBitratePoint = paint;
        paint.setColor(SupportMenu.CATEGORY_MASK);
        this.mBitratePoint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint2 = new Paint();
        this.mUpgradeBitratePoint = paint2;
        paint2.setColor(i);
        this.mUpgradeBitratePoint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint3 = new Paint();
        this.mBaselineBitratePoint = paint3;
        paint3.setColor(i2);
        this.mBaselineBitratePoint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint4 = new Paint();
        this.mAudioBitratePoint = paint4;
        paint4.setColor(i3);
        this.mAudioBitratePoint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint5 = new Paint();
        this.mBitrateLine = paint5;
        paint5.setColor(SupportMenu.CATEGORY_MASK);
        this.mBitrateLine.setTypeface(Typeface.MONOSPACE);
        this.mBitrateLine.setTextSize(16.0f);
        Paint paint6 = new Paint();
        this.mBitrateCapLine = paint6;
        paint6.setColor(-16776961);
        this.mBitrateCapLine.setTypeface(Typeface.MONOSPACE);
        this.mBitrateCapLine.setTextSize(16.0f);
        Paint paint7 = new Paint();
        this.mUpgradeBitrateLine = paint7;
        paint7.setColor(i);
        this.mUpgradeBitrateLine.setTextSize(16.0f);
        Paint paint8 = new Paint();
        this.mBaselineBitrateLine = paint8;
        paint8.setColor(i2);
        this.mBaselineBitrateLine.setTextSize(16.0f);
        Paint paint9 = new Paint();
        this.mAudioBitrateLine = paint9;
        paint9.setColor(i3);
        this.mAudioBitrateLine.setTypeface(Typeface.MONOSPACE);
        this.mAudioBitrateLine.setTextSize(16.0f);
        Paint paint10 = new Paint();
        this.mAudioBitrateCapLine = paint10;
        paint10.setColor(-16711681);
        this.mAudioBitrateCapLine.setTypeface(Typeface.MONOSPACE);
        this.mAudioBitrateCapLine.setTextSize(16.0f);
        Paint paint11 = new Paint();
        this.mBandwidthPoint = paint11;
        paint11.setColor(InputDeviceCompat.SOURCE_ANY);
        this.mBandwidthPoint.setAlpha(128);
        this.mBandwidthPoint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint12 = new Paint();
        this.mBandwidthErrorPoint = paint12;
        paint12.setColor(-65281);
        this.mBandwidthErrorPoint.setAlpha(128);
        this.mBandwidthErrorPoint.setStrokeWidth(this.thickStrokeWidth);
        this.mBandwidthErrorPoint.setStyle(Paint.Style.FILL_AND_STROKE);
        Paint paint13 = new Paint();
        this.mBufferingPoint = paint13;
        paint13.setColor(-16711681);
        this.mBufferingPoint.setAlpha(128);
        this.mBufferingPoint.setStrokeWidth(this.thickStrokeWidth);
        Paint paint14 = new Paint();
        this.mBandwidthLine = paint14;
        paint14.setColor(InputDeviceCompat.SOURCE_ANY);
        this.mBandwidthLine.setStrokeWidth(2.0f);
        this.mBandwidthLine.setTypeface(Typeface.MONOSPACE);
        this.mBandwidthLine.setTextSize(16.0f);
        Paint paint15 = new Paint();
        this.mCdnSwitchedLine = paint15;
        paint15.setColor(-1);
        this.mCdnSwitchedLine.setAlpha(128);
        this.mCdnSwitchedLine.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mCdnSwitchedLine.setStrokeWidth(this.mMediumStrokeWidth);
        Paint paint16 = new Paint();
        this.mQoeTriggeredCdnSwitchedLine = paint16;
        paint16.setColor(SupportMenu.CATEGORY_MASK);
        this.mQoeTriggeredCdnSwitchedLine.setAlpha(128);
        this.mQoeTriggeredCdnSwitchedLine.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mQoeTriggeredCdnSwitchedLine.setStrokeWidth(this.mMediumStrokeWidth);
        Paint paint17 = new Paint();
        this.mCdnSwitchedText = paint17;
        paint17.setColor(-1);
        this.mCdnSwitchedText.setAlpha(255);
        this.mCdnSwitchedText.setStrokeWidth(2.0f);
        this.mCdnSwitchedText.setTextSize(16.0f);
        Paint paint18 = new Paint();
        this.mManifestRefreshLine = paint18;
        paint18.setColor(-16711936);
        this.mManifestRefreshLine.setStrokeWidth(this.mMediumStrokeWidth);
        Paint paint19 = new Paint();
        this.mPlayHeadLine = paint19;
        paint19.setColor(-16711936);
        this.mPlayHeadLine.setStrokeWidth(this.mMediumStrokeWidth);
        Paint paint20 = new Paint();
        this.mPlayHeadText = paint20;
        paint20.setColor(-16711936);
        this.mPlayHeadText.setTextSize(16.0f);
        this.mPlayHeadText.setTextAlign(Paint.Align.CENTER);
        Paint paint21 = new Paint();
        this.mResolutionPaint = paint21;
        paint21.setTextSize(14.0f);
        this.mResolutionPaint.setTextAlign(Paint.Align.RIGHT);
        Paint paint22 = new Paint();
        this.mResolutionSchemeLine = paint22;
        paint22.setStrokeWidth(this.mMediumStrokeWidth);
        this.mResolutionSchemeLine.setTypeface(Typeface.MONOSPACE);
        this.mResolutionSchemeLine.setTextSize(16.0f);
        if (this.mIsCompactViewEnabled) {
            Paint paint23 = new Paint();
            this.mRectPaint = paint23;
            paint23.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mRectPaint.setAlpha(100);
            this.mRectPaint.setStyle(Paint.Style.FILL);
        }
    }

    public void setAxisScale(DiagnosticsController.BandwidthScale bandwidthScale) {
        this.mScale = bandwidthScale;
        int i = AnonymousClass5.$SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale[bandwidthScale.ordinal()];
        if (i == 1) {
            this.mMaxBitrate = this.mMaxBitrateLinear;
        } else if (i == 2) {
            this.mMaxBitrate = this.mMaxBitrateLogarithmic;
        } else {
            if (i != 3) {
                return;
            }
            this.mMaxBitrate = this.mMaxBitrateEqualSpacedBandwidth;
        }
    }

    private boolean initForSession(ContentSessionContext contentSessionContext) {
        Manifest manifest = contentSessionContext.getManifest();
        if (this.mInitForSession) {
            return true;
        }
        StreamIndex videoStream = manifest.getVideoStream();
        QualityLevel[] sortedQualityLevels = videoStream.getSortedQualityLevels(contentSessionContext.getState().getConsumptionHead(videoStream.getIndex()));
        this.mSortedVideoQualityLevels = sortedQualityLevels;
        this.mBitrateResolutionMap.clear();
        int consumptionHead = contentSessionContext.getState().getConsumptionHead(videoStream.getIndex());
        for (QualityLevel qualityLevel : sortedQualityLevels) {
            this.mBitrateResolutionMap.put(Integer.valueOf((int) DataUnit.BITS.toKiloBits(r8.getBitrate())), Integer.valueOf(((VideoQualityLevel) videoStream.getQualityLevelGreaterThanEqual(consumptionHead, qualityLevel.getBitrate())).getMaxHeight()));
        }
        int iMin = Integer.MAX_VALUE;
        int iMax = 0;
        for (QualityLevel qualityLevel2 : sortedQualityLevels) {
            iMax = Math.max(iMax, qualityLevel2.getBitrate());
            iMin = Math.min(iMin, qualityLevel2.getBitrate());
        }
        Iterator it = Lists.newArrayList(AudioStreamType.AACH.getFourCC(), AudioStreamType.AACHV2.getFourCC(), AudioStreamType.AACL.getFourCC(), AudioStreamType.DDP.getFourCC()).iterator();
        while (it.hasNext()) {
            StreamIndex audioStream = manifest.getAudioStream((String) it.next(), null);
            if (audioStream != null) {
                QualityLevel[] sortedQualityLevels2 = audioStream.getSortedQualityLevels(contentSessionContext.getState().getConsumptionHead(audioStream.getIndex()));
                if (sortedQualityLevels2.length > 0) {
                    iMin = Math.min(iMin, sortedQualityLevels2[0].getBitrate());
                }
            }
        }
        int i = iMax / 1000;
        this.mMaxBitrateLinear = i + 500;
        int i2 = i + WearableStatusCodes.TARGET_NODE_NOT_CONNECTED;
        this.mMaxBitrateLogarithmic = i2;
        this.mMaxBitrateEqualSpacedBandwidth = i2;
        setAxisScale(this.mScale);
        int i3 = (int) (((double) (iMin / 1000)) * 0.75d);
        this.mMinBitrate = i3;
        this.mLogMin = Math.log10(i3);
        this.mHeightRatio = 1.0d / ((Math.log10(this.mMaxBitrateLogarithmic) / this.mLogMin) - 1.0d);
        this.mNumQualityLevels = sortedQualityLevels.length;
        createBitrateIndexMap(sortedQualityLevels);
        this.mBitrateYMap.clear();
        populateResolutionColorSchemeMap();
        this.mInitForSession = true;
        return true;
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.GraphPlotter
    public void draw(Canvas canvas, DiagnosticDataCollector diagnosticDataCollector) {
        if (initForSession(diagnosticDataCollector.getContext())) {
            drawAxis(canvas, diagnosticDataCollector.getVideoFragmentQualityCounter());
            drawKey(canvas, diagnosticDataCollector);
            drawData(canvas, diagnosticDataCollector);
        }
    }

    private void drawKey(Canvas canvas, DiagnosticDataCollector diagnosticDataCollector) {
        Optional optionalAbsent = Optional.absent();
        Optional optionalAbsent2 = Optional.absent();
        TreeSet<DiagnosticDataPoint> diagnosticDataPoints = diagnosticDataCollector.getDiagnosticDataPoints();
        String str = "Audio Bitrate:";
        String str2 = "Video Bitrate:";
        String str3 = "Bandwidth:    ";
        if (!diagnosticDataPoints.isEmpty()) {
            str3 = String.format(Locale.US, "%s %s kbps", "Bandwidth:    ", padLeft(diagnosticDataPoints.last().getDownloadRate() / 1000, 8));
            Iterator<DiagnosticDataPoint> it = diagnosticDataPoints.descendingSet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DiagnosticDataPoint next = it.next();
                if (next.getStreamType() == StreamType.VIDEO) {
                    Locale locale = Locale.US;
                    String str4 = String.format(locale, "%s %s", "Video Bitrate:", padLeft(next.getBitrate(), 8));
                    if (next.getBitrateCap().isPresent()) {
                        optionalAbsent = Optional.of(String.format(locale, " (%s%s - %s)", "Cap:", padLeft(next.getBitrateCap().get().intValue(), 8), next.getBitrateCapDescription().or("Unknown")));
                    } else {
                        optionalAbsent = Optional.absent();
                    }
                    str2 = str4;
                }
            }
            Iterator<DiagnosticDataPoint> it2 = diagnosticDataPoints.descendingSet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                DiagnosticDataPoint next2 = it2.next();
                if (next2.getStreamType() == StreamType.AUDIO) {
                    Locale locale2 = Locale.US;
                    str = String.format(locale2, "%s %s", "Audio Bitrate:", padLeft(next2.getBitrate(), 8));
                    optionalAbsent2 = Optional.of(String.format(locale2, " (%s)", next2.getBitrateCapDescription().or("Unknown")));
                    break;
                }
            }
        }
        PointF pointF = this.mOrigin;
        PointF pointF2 = new PointF(pointF.x + 5.0f, (pointF.y - this.mLineGraphHeight) + 20.0f);
        pointF2.y += writeText(canvas, str3, pointF2, this.mBandwidthLine).y;
        if (this.mIsCompactViewEnabled) {
            return;
        }
        float f = this.mOrigin.x + 5.0f;
        pointF2.x = f;
        PointF pointFWriteText = writeText(canvas, str2, pointF2, this.mBitrateLine);
        pointF2.x += pointFWriteText.x;
        float f2 = pointFWriteText.y;
        if (optionalAbsent.isPresent()) {
            pointFWriteText = writeText(canvas, (String) optionalAbsent.get(), pointF2, this.mBitrateCapLine);
        }
        pointF2.y += Math.max(pointFWriteText.y, f2);
        pointF2.x = f;
        PointF pointFWriteText2 = writeText(canvas, str, pointF2, this.mAudioBitrateLine);
        pointF2.x += pointFWriteText2.x;
        if (optionalAbsent2.isPresent()) {
            writeText(canvas, (String) optionalAbsent2.get(), pointF2, this.mAudioBitrateLine);
        }
        pointF2.y += Math.max(pointFWriteText2.y, f2);
        pointF2.x = f;
        this.mResolutionSchemeLine.setColor(-16711681);
        pointF2.x += writeText(canvas, SUB_SD, pointF2, this.mResolutionSchemeLine).x;
        this.mResolutionSchemeLine.setColor(COLOR_GOLD_YELLOW);
        pointF2.x += writeText(canvas, SD, pointF2, this.mResolutionSchemeLine).x;
        this.mResolutionSchemeLine.setColor(SupportMenu.CATEGORY_MASK);
        pointF2.x += writeText(canvas, HD, pointF2, this.mResolutionSchemeLine).x;
        this.mResolutionSchemeLine.setColor(-16711936);
        pointF2.x += writeText(canvas, HD_1080P, pointF2, this.mResolutionSchemeLine).x;
        this.mResolutionSchemeLine.setColor(COLOR_DARK_PINK);
        writeText(canvas, UHD, pointF2, this.mResolutionSchemeLine);
    }

    private String padLeft(long j, int i) {
        return Strings.padStart(Long.toString(j), i, ' ');
    }

    private PointF writeText(Canvas canvas, String str, PointF pointF, Paint paint) {
        canvas.drawText(str, pointF.x, pointF.y, paint);
        paint.getTextBounds(str, 0, str.length(), new Rect());
        return new PointF(Math.abs(r2.right - r2.left), Math.abs(r2.top - r2.bottom));
    }

    private void drawAxis(Canvas canvas, VideoFragmentQualityCounter videoFragmentQualityCounter) {
        super.drawAxis(canvas, this.mMaxBitrate, this.mLineGraphHeight);
        if (this.mIsCompactViewEnabled) {
            PointF pointF = this.mOrigin;
            canvas.drawRect(pointF.x - 50.0f, (pointF.y + 40.0f) - getHeight(), (this.mOrigin.x - 50.0f) + getWidth(), this.mOrigin.y + 40.0f, this.mRectPaint);
        }
        QualityLevel[] qualityLevelArr = this.mSortedVideoQualityLevels;
        int totalSampleCount = videoFragmentQualityCounter.getTotalSampleCount();
        for (QualityLevel qualityLevel : qualityLevelArr) {
            float f = this.mOrigin.x;
            DataUnit dataUnit = DataUnit.BITS;
            PointF pointFCreatePoint = createPoint(f, dataUnit.toKiloBits(qualityLevel.getBitrate()));
            this.mBitrateYMap.put(Integer.valueOf((int) dataUnit.toKiloBits(qualityLevel.getBitrate())), Float.valueOf(pointFCreatePoint.y));
            int iIntValue = this.mBitrateResolutionMap.get(Integer.valueOf((int) dataUnit.toKiloBits(qualityLevel.getBitrate()))).intValue();
            int sampleCountForBitrate = videoFragmentQualityCounter.getSampleCountForBitrate(qualityLevel.getBitrate());
            drawHorizontalAxis(canvas, pointFCreatePoint, String.valueOf((int) dataUnit.toKiloBits(qualityLevel.getBitrate())), (totalSampleCount <= 0 ? " No data. " : String.format(Locale.US, " %s(%s%%) ", Integer.valueOf(sampleCountForBitrate), Integer.valueOf((sampleCountForBitrate * 100) / totalSampleCount))) + "(" + String.valueOf(iIntValue) + ")", this.mLightAxisPaint, getResolutionPaint(iIntValue));
        }
        this.mSpaceBetweenYLabels = this.mOrigin.y - this.mBitrateYMap.get(Integer.valueOf((int) DataUnit.BITS.toKiloBits(qualityLevelArr[0].getBitrate()))).floatValue();
    }

    private void populateResolutionColorSchemeMap() {
        this.mResolutionColorSchemeMap.put(Integer.valueOf(VideoResolution.ResolutionBand.SUB_SD.getMinHeight()), -16711681);
        this.mResolutionColorSchemeMap.put(Integer.valueOf(VideoResolution.ResolutionBand.SD.getMinHeight()), Integer.valueOf(COLOR_GOLD_YELLOW));
        this.mResolutionColorSchemeMap.put(Integer.valueOf(VideoResolution.ResolutionBand.HD.getMinHeight()), Integer.valueOf(SupportMenu.CATEGORY_MASK));
        this.mResolutionColorSchemeMap.put(Integer.valueOf(VideoResolution.ResolutionBand.HD_1080P.getMinHeight()), -16711936);
        this.mResolutionColorSchemeMap.put(Integer.valueOf(VideoResolution.ResolutionBand.ULTRA_HD.getMinHeight()), Integer.valueOf(COLOR_DARK_PINK));
    }

    @Nonnull
    private Paint getResolutionPaint(int i) {
        Integer value;
        Map.Entry<Integer, Integer> entryFloorEntry = this.mResolutionColorSchemeMap.floorEntry(Integer.valueOf(i));
        Map.Entry<Integer, Integer> entryCeilingEntry = this.mResolutionColorSchemeMap.ceilingEntry(Integer.valueOf(i));
        if (i < entryCeilingEntry.getKey().intValue()) {
            value = entryFloorEntry.getValue();
        } else {
            value = entryCeilingEntry.getValue();
        }
        this.mResolutionPaint.setColor(value.intValue());
        return this.mResolutionPaint;
    }

    private void createBitrateIndexMap(QualityLevel[] qualityLevelArr) {
        this.mBitrateIndexMap.clear();
        int i = 0;
        while (i < qualityLevelArr.length) {
            TreeMap<Integer, Integer> treeMap = this.mBitrateIndexMap;
            Integer numValueOf = Integer.valueOf((int) DataUnit.BITS.toKiloBits(qualityLevelArr[i].getBitrate()));
            i++;
            treeMap.put(numValueOf, Integer.valueOf(i));
        }
        this.mBitrateIndexMap.put(Integer.valueOf(this.mMaxBitrate), Integer.valueOf(this.mNumQualityLevels + 1));
    }

    private PointF getEquispacedScalePoint(float f, float f2) {
        float fFloatValue;
        int i = (int) f2;
        Map.Entry<Integer, Integer> entryFloorEntry = this.mBitrateIndexMap.floorEntry(Integer.valueOf(i));
        Map.Entry<Integer, Integer> entryCeilingEntry = this.mBitrateIndexMap.ceilingEntry(Integer.valueOf(i));
        int iIntValue = 0;
        if (entryFloorEntry == null || entryCeilingEntry == null) {
            if (entryFloorEntry != null || entryCeilingEntry != null) {
                if (entryFloorEntry != null) {
                    iIntValue = entryFloorEntry.getValue().intValue();
                } else {
                    iIntValue = entryCeilingEntry.getValue().intValue();
                }
            }
        } else if (Math.abs(f2 - entryFloorEntry.getKey().intValue()) < Math.abs(f2 - entryCeilingEntry.getKey().intValue())) {
            iIntValue = entryFloorEntry.getValue().intValue();
        } else {
            iIntValue = entryCeilingEntry.getValue().intValue();
        }
        float f3 = this.mOrigin.y - ((iIntValue / (this.mNumQualityLevels + 1)) * this.mLineGraphHeight);
        if (entryFloorEntry == null) {
            TreeMap treeMapNewTreeMap = Maps.newTreeMap();
            treeMapNewTreeMap.put(0, 0);
            entryFloorEntry = treeMapNewTreeMap.firstEntry();
        }
        if (entryCeilingEntry == null) {
            entryCeilingEntry = entryFloorEntry;
        }
        if (entryFloorEntry.getKey().intValue() == entryCeilingEntry.getKey().intValue()) {
            return new PointF(f, f3);
        }
        if (Math.abs(f2 - entryFloorEntry.getKey().intValue()) < Math.abs(f2 - entryCeilingEntry.getKey().intValue())) {
            fFloatValue = entryFloorEntry.getKey().floatValue();
        } else {
            fFloatValue = entryCeilingEntry.getKey().floatValue();
        }
        return new PointF(f, f3 - (((f2 - fFloatValue) * this.mSpaceBetweenYLabels) / (entryCeilingEntry.getKey().intValue() - entryFloorEntry.getKey().intValue())));
    }

    private void drawData(Canvas canvas, DiagnosticDataCollector diagnosticDataCollector) {
        TimeSpan timeSpan = new TimeSpan(diagnosticDataCollector.getPlayHeadInNanos());
        long jDrawDiagnosticPoints = drawDiagnosticPoints(canvas, diagnosticDataCollector, timeSpan);
        drawBandwidthErrorDataPoints(canvas, diagnosticDataCollector, timeSpan, jDrawDiagnosticPoints);
        drawBufferingDataPoints(canvas, diagnosticDataCollector, timeSpan, jDrawDiagnosticPoints);
        drawCdnSwitchedDataPoints(canvas, diagnosticDataCollector, timeSpan, jDrawDiagnosticPoints);
        drawManifestRefreshDataPoints(canvas, diagnosticDataCollector, timeSpan, jDrawDiagnosticPoints);
        drawLabels(canvas, jDrawDiagnosticPoints);
        float fMax = Math.max(this.mFirstXLocation, getXLocationFromTimeStamp(timeSpan, jDrawDiagnosticPoints));
        float f = this.mOrigin.y;
        canvas.drawLine(fMax, f, fMax, f - this.mLineGraphHeight, this.mPlayHeadLine);
        canvas.drawText(timeSpan.toString(), fMax, (this.mOrigin.y - this.mLineGraphHeight) - 3.0f, this.mPlayHeadText);
    }

    private long drawDiagnosticPoints(Canvas canvas, DiagnosticDataCollector diagnosticDataCollector, TimeSpan timeSpan) {
        TreeSet<DiagnosticDataPoint> diagnosticDataPoints = diagnosticDataCollector.getDiagnosticDataPoints();
        synchronized (diagnosticDataPoints) {
            if (diagnosticDataPoints.isEmpty()) {
                return 0L;
            }
            Iterator itFindStartingIterator = findStartingIterator(diagnosticDataPoints, timeSpan);
            DiagnosticDataPoint diagnosticDataPoint = (DiagnosticDataPoint) itFindStartingIterator.next();
            long totalMilliseconds = diagnosticDataPoint.getTimeStamp().getTotalMilliseconds();
            PointF pointFDrawLineSegment = null;
            PointF pointFDrawLineSegment2 = null;
            PointF pointFDrawLineSegment3 = null;
            PointF pointFDrawLineSegment4 = null;
            PointF pointFDrawLineSegment5 = null;
            PointF pointFDrawLineSegment6 = null;
            while (itFindStartingIterator.hasNext()) {
                long downloadRate = diagnosticDataPoint.getDownloadRate();
                long averageDownloadRate = diagnosticDataPoint.getAverageDownloadRate();
                float xLocationFromTimeStamp = getXLocationFromTimeStamp(diagnosticDataPoint.getTimeStamp(), totalMilliseconds);
                int i = AnonymousClass5.$SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[diagnosticDataPoint.getStreamType().ordinal()];
                if (i == 1) {
                    int i2 = AnonymousClass5.$SwitchMap$com$amazon$avod$content$dash$quality$heuristic$FragmentType[diagnosticDataPoint.getFragmentType().ordinal()];
                    if (i2 == 1) {
                        pointFDrawLineSegment2 = drawLineSegment(canvas, xLocationFromTimeStamp, pointFDrawLineSegment2, diagnosticDataPoint.getBitrate(), this.mUpgradeBitrateLine, this.mUpgradeBitratePoint);
                    } else if (i2 == 2) {
                        pointFDrawLineSegment3 = drawLineSegment(canvas, xLocationFromTimeStamp, pointFDrawLineSegment3, diagnosticDataPoint.getBitrate(), this.mBaselineBitrateLine, this.mBaselineBitratePoint);
                    } else {
                        pointFDrawLineSegment4 = drawLineSegment(canvas, xLocationFromTimeStamp, pointFDrawLineSegment4, diagnosticDataPoint.getBitrate(), this.mBitrateLine, this.mBitratePoint);
                    }
                    pointFDrawLineSegment5 = drawLineSegment(canvas, xLocationFromTimeStamp, pointFDrawLineSegment5, downloadRate, null, this.mBandwidthPoint);
                    pointFDrawLineSegment6 = drawLineSegment(canvas, xLocationFromTimeStamp, pointFDrawLineSegment6, averageDownloadRate, this.mBandwidthLine, null);
                } else if (i == 2) {
                    pointFDrawLineSegment = drawLineSegment(canvas, xLocationFromTimeStamp, pointFDrawLineSegment, diagnosticDataPoint.getBitrate(), this.mAudioBitrateLine, this.mAudioBitratePoint);
                }
                diagnosticDataPoint = (DiagnosticDataPoint) itFindStartingIterator.next();
            }
            return totalMilliseconds;
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.playback.smoothstream.diagnostics.BandwidthGraphPlotter$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$FragmentType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale;

        static {
            int[] iArr = new int[StreamType.values().length];
            $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType = iArr;
            try {
                iArr[StreamType.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$smoothstream$manifest$StreamType[StreamType.AUDIO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[FragmentType.values().length];
            $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$FragmentType = iArr2;
            try {
                iArr2[FragmentType.UPGRADE.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$FragmentType[FragmentType.BASELINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$dash$quality$heuristic$FragmentType[FragmentType.DEFAULT.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[DiagnosticsController.BandwidthScale.values().length];
            $SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale = iArr3;
            try {
                iArr3[DiagnosticsController.BandwidthScale.LINEAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale[DiagnosticsController.BandwidthScale.LOGARITHMIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale[DiagnosticsController.BandwidthScale.EQUISPACED.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void drawBandwidthErrorDataPoints(Canvas canvas, DiagnosticDataCollector diagnosticDataCollector, TimeSpan timeSpan, long j) {
        drawDataPoints(canvas, diagnosticDataCollector.getErrorDataPoints(), timeSpan, j, this.mBandwidthErrorDataDrawer);
    }

    private void drawBufferingDataPoints(Canvas canvas, DiagnosticDataCollector diagnosticDataCollector, TimeSpan timeSpan, long j) {
        drawDataPoints(canvas, diagnosticDataCollector.getBufferingDataPoints(), timeSpan, j, this.mBufferingDataDrawer);
    }

    private void drawCdnSwitchedDataPoints(Canvas canvas, DiagnosticDataCollector diagnosticDataCollector, TimeSpan timeSpan, long j) {
        TreeSet<CdnSwitchedDataPoint> cdnSwitchedDataPoints = diagnosticDataCollector.getCdnSwitchedDataPoints();
        drawDataPoints(canvas, cdnSwitchedDataPoints, timeSpan, j, this.mCdnSwitchedDataDrawer);
        if (cdnSwitchedDataPoints.isEmpty()) {
            return;
        }
        PointF pointF = this.mOrigin;
        PointF pointF2 = new PointF(pointF.x + 5.0f, (pointF.y - this.mLineGraphHeight) + 60.0f);
        canvas.drawText("Old CDN: " + cdnSwitchedDataPoints.last().getOldCdn() + " -> New CDN: " + cdnSwitchedDataPoints.last().getNewCdn(), pointF2.x, pointF2.y, this.mCdnSwitchedText);
    }

    private void drawManifestRefreshDataPoints(Canvas canvas, @Nonnull DiagnosticDataCollector diagnosticDataCollector, TimeSpan timeSpan, long j) {
        Preconditions.checkNotNull(diagnosticDataCollector, "collector");
        drawDataPoints(canvas, diagnosticDataCollector.getManifestRefreshDataPoints(), timeSpan, j, this.mManifestRefreshDataDrawer);
    }

    private <T extends DataPoint> void drawDataPoints(Canvas canvas, TreeSet<T> treeSet, TimeSpan timeSpan, long j, DataPointDrawer<T> dataPointDrawer) {
        synchronized (treeSet) {
            if (!treeSet.isEmpty()) {
                Iterator<T> itFindStartingIterator = findStartingIterator(treeSet, timeSpan);
                while (itFindStartingIterator.hasNext()) {
                    T next = itFindStartingIterator.next();
                    float xLocationFromTimeStamp = getXLocationFromTimeStamp(next.getTimeStamp(), j);
                    if (xLocationFromTimeStamp > this.mFirstXLocation) {
                        dataPointDrawer.drawDataPoint(canvas, next, xLocationFromTimeStamp);
                    }
                }
            }
        }
    }

    private void drawLabels(Canvas canvas, long j) {
        float width = this.mOrigin.x;
        Long lValueOf = Long.valueOf(j);
        for (int i = 0; i < 10; i++) {
            drawXAxisLabel(canvas, new PointF(width, this.mOrigin.y), TimeSpan.fromMilliseconds(lValueOf.longValue()).toString(TimeUnit.MILLISECONDS), this.mXAxisTextPaint);
            lValueOf = Long.valueOf(lValueOf.longValue() + 30000);
            width += (((getWidth() - 50) - 0) - 5.0f) / 10.0f;
        }
    }

    private PointF drawLineSegment(Canvas canvas, float f, PointF pointF, long j, Paint paint, Paint paint2) {
        PointF pointFCreatePoint = createPoint(f, Math.min(this.mMaxBitrate, DataUnit.BITS.toKiloBits(j)));
        if (paint2 != null) {
            canvas.drawCircle(pointFCreatePoint.x, pointFCreatePoint.y, this.mMediumStrokeWidth, paint2);
        }
        if (paint != null && pointF != null) {
            canvas.drawLine(pointF.x, pointF.y, pointFCreatePoint.x, pointFCreatePoint.y, paint);
        }
        return pointFCreatePoint;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PointF createPoint(float f, float f2) {
        PointF pointF;
        int i = AnonymousClass5.$SwitchMap$com$amazon$avod$media$diagnostics$DiagnosticsController$BandwidthScale[this.mScale.ordinal()];
        if (i == 1) {
            pointF = new PointF(f, this.mOrigin.y - ((f2 / this.mMaxBitrateLinear) * this.mLineGraphHeight));
        } else {
            if (i != 2) {
                if (i != 3) {
                    return null;
                }
                return getEquispacedScalePoint(f, f2);
            }
            pointF = new PointF(f, (float) (((double) this.mOrigin.y) - ((((Math.log10(f2) / this.mLogMin) - 1.0d) * ((double) this.mLineGraphHeight)) * this.mHeightRatio)));
        }
        return pointF;
    }
}
