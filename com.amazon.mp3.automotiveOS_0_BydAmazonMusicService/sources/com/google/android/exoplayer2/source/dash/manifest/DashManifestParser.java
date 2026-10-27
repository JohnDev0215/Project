package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.perf.plugin.util.AsmString;
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes6.dex */
public class DashManifestParser extends DefaultHandler implements ParsingLoadable.Parser<DashManifest> {
    private final String contentId;
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");

    protected void parseAdaptationSetChild(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
    }

    public DashManifestParser() {
        this(null);
    }

    public DashManifestParser(String str) {
        this.contentId = str;
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.upstream.ParsingLoadable.Parser
    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, null);
            if (xmlPullParserNewPullParser.next() != 2 || !"MPD".equals(xmlPullParserNewPullParser.getName())) {
                throw new ParserException("inputStream does not contain a valid media presentation description");
            }
            return parseMediaPresentationDescription(xmlPullParserNewPullParser, uri.toString());
        } catch (XmlPullParserException e) {
            throw new ParserException(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected com.google.android.exoplayer2.source.dash.manifest.DashManifest parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser r33, java.lang.String r34) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser, java.lang.String):com.google.android.exoplayer2.source.dash.manifest.DashManifest");
    }

    protected DashManifest buildMediaPresentationDescription(long j, long j2, long j3, boolean z, long j4, long j5, long j6, UtcTimingElement utcTimingElement, Uri uri, List<Period> list, long j7) {
        return new DashManifest(j, j2, j3, z, j4, j5, j6, utcTimingElement, uri, list, j7);
    }

    protected UtcTimingElement parseUtcTiming(XmlPullParser xmlPullParser) {
        return buildUtcTimingElement(xmlPullParser.getAttributeValue(null, "schemeIdUri"), xmlPullParser.getAttributeValue(null, "value"));
    }

    protected UtcTimingElement buildUtcTimingElement(String str, String str2) {
        return new UtcTimingElement(str, str2);
    }

    protected Pair<Period, Long> parsePeriod(XmlPullParser xmlPullParser, String str, long j) throws XmlPullParserException, IOException {
        String attributeValue = xmlPullParser.getAttributeValue(null, "id");
        long duration = parseDuration(xmlPullParser, AsmString.METHOD_FIREBASE_PERF_TRACE_START, j);
        long duration2 = parseDuration(xmlPullParser, "duration", -9223372036854775807L);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        SegmentBase segmentTemplate = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (!z) {
                    str = parseBaseUrl(xmlPullParser, str);
                    z = true;
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "AdaptationSet")) {
                arrayList.add(parseAdaptationSet(xmlPullParser, str, segmentTemplate));
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                segmentTemplate = parseSegmentBase(xmlPullParser, null);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                segmentTemplate = parseSegmentList(xmlPullParser, null);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                segmentTemplate = parseSegmentTemplate(xmlPullParser, null);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Period"));
        return Pair.create(buildPeriod(attributeValue, duration, arrayList), Long.valueOf(duration2));
    }

    protected Period buildPeriod(String str, long j, List<AdaptationSet> list) {
        return new Period(str, j, list);
    }

    protected AdaptationSet parseAdaptationSet(XmlPullParser xmlPullParser, String str, SegmentBase segmentBase) throws XmlPullParserException, IOException {
        String strCheckLanguageConsistency;
        String str2;
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3;
        ArrayList<DrmInitData.SchemeData> arrayList4;
        String str3;
        String str4;
        XmlPullParser xmlPullParser2;
        int i;
        ArrayList<Descriptor> arrayList5;
        SegmentBase segmentTemplate;
        XmlPullParser xmlPullParser3 = xmlPullParser;
        int i2 = parseInt(xmlPullParser3, "id", -1);
        int contentType = parseContentType(xmlPullParser);
        String str5 = null;
        String attributeValue = xmlPullParser3.getAttributeValue(null, "mimeType");
        String attributeValue2 = xmlPullParser3.getAttributeValue(null, "codecs");
        int i3 = parseInt(xmlPullParser3, "width", -1);
        int i4 = parseInt(xmlPullParser3, "height", -1);
        float frameRate = parseFrameRate(xmlPullParser3, -1.0f);
        int i5 = parseInt(xmlPullParser3, "audioSamplingRate", -1);
        String str6 = "lang";
        String attributeValue3 = xmlPullParser3.getAttributeValue(null, "lang");
        ArrayList<DrmInitData.SchemeData> arrayList6 = new ArrayList<>();
        ArrayList<Descriptor> arrayList7 = new ArrayList<>();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        String baseUrl = str;
        SegmentBase segmentBase2 = segmentBase;
        int iCheckContentTypeConsistency = contentType;
        int audioChannelConfiguration = -1;
        String str7 = attributeValue3;
        boolean z = false;
        int role = 0;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser3, "BaseURL")) {
                if (!z) {
                    z = true;
                    baseUrl = parseBaseUrl(xmlPullParser3, baseUrl);
                    strCheckLanguageConsistency = str7;
                    arrayList = arrayList10;
                    arrayList2 = arrayList9;
                    arrayList3 = arrayList8;
                    arrayList5 = arrayList7;
                    arrayList4 = arrayList6;
                    str3 = str6;
                    str4 = str5;
                    xmlPullParser2 = xmlPullParser3;
                }
                strCheckLanguageConsistency = str7;
                i = iCheckContentTypeConsistency;
                str2 = baseUrl;
                arrayList = arrayList10;
                arrayList2 = arrayList9;
                arrayList3 = arrayList8;
                arrayList5 = arrayList7;
                arrayList4 = arrayList6;
                str3 = str6;
                str4 = str5;
                xmlPullParser2 = xmlPullParser3;
                iCheckContentTypeConsistency = i;
                baseUrl = str2;
            } else {
                if (XmlPullParserUtil.isStartTag(xmlPullParser3, "ContentProtection")) {
                    DrmInitData.SchemeData contentProtection = parseContentProtection(xmlPullParser);
                    if (contentProtection != null) {
                        arrayList6.add(contentProtection);
                    }
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser3, "ContentComponent")) {
                    strCheckLanguageConsistency = checkLanguageConsistency(str7, xmlPullParser3.getAttributeValue(str5, str6));
                    iCheckContentTypeConsistency = checkContentTypeConsistency(iCheckContentTypeConsistency, parseContentType(xmlPullParser));
                    arrayList = arrayList10;
                    arrayList2 = arrayList9;
                    arrayList3 = arrayList8;
                    arrayList5 = arrayList7;
                    arrayList4 = arrayList6;
                    str3 = str6;
                    str4 = str5;
                    xmlPullParser2 = xmlPullParser3;
                } else {
                    if (XmlPullParserUtil.isStartTag(xmlPullParser3, "Role")) {
                        role |= parseRole(xmlPullParser);
                    } else if (XmlPullParserUtil.isStartTag(xmlPullParser3, "AudioChannelConfiguration")) {
                        audioChannelConfiguration = parseAudioChannelConfiguration(xmlPullParser);
                    } else if (XmlPullParserUtil.isStartTag(xmlPullParser3, "Accessibility")) {
                        arrayList8.add(parseDescriptor(xmlPullParser3, "Accessibility"));
                    } else if (XmlPullParserUtil.isStartTag(xmlPullParser3, "SupplementalProperty")) {
                        arrayList9.add(parseDescriptor(xmlPullParser3, "SupplementalProperty"));
                    } else if (XmlPullParserUtil.isStartTag(xmlPullParser3, "Representation")) {
                        strCheckLanguageConsistency = str7;
                        arrayList2 = arrayList9;
                        arrayList3 = arrayList8;
                        arrayList4 = arrayList6;
                        str3 = str6;
                        str4 = str5;
                        RepresentationInfo representation = parseRepresentation(xmlPullParser, baseUrl, attributeValue, attributeValue2, i3, i4, frameRate, audioChannelConfiguration, i5, strCheckLanguageConsistency, role, arrayList3, segmentBase2);
                        int iCheckContentTypeConsistency2 = checkContentTypeConsistency(iCheckContentTypeConsistency, getContentType(representation.format));
                        arrayList = arrayList10;
                        arrayList.add(representation);
                        iCheckContentTypeConsistency = iCheckContentTypeConsistency2;
                        baseUrl = baseUrl;
                        arrayList5 = arrayList7;
                        xmlPullParser2 = xmlPullParser;
                    } else {
                        strCheckLanguageConsistency = str7;
                        str2 = baseUrl;
                        arrayList = arrayList10;
                        arrayList2 = arrayList9;
                        arrayList3 = arrayList8;
                        ArrayList<Descriptor> arrayList11 = arrayList7;
                        arrayList4 = arrayList6;
                        str3 = str6;
                        str4 = str5;
                        xmlPullParser2 = xmlPullParser;
                        i = iCheckContentTypeConsistency;
                        if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentBase")) {
                            segmentTemplate = parseSegmentBase(xmlPullParser2, (SegmentBase.SingleSegmentBase) segmentBase2);
                        } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentList")) {
                            segmentTemplate = parseSegmentList(xmlPullParser2, (SegmentBase.SegmentList) segmentBase2);
                        } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTemplate")) {
                            segmentTemplate = parseSegmentTemplate(xmlPullParser2, (SegmentBase.SegmentTemplate) segmentBase2);
                        } else {
                            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "InbandEventStream")) {
                                arrayList5 = arrayList11;
                                arrayList5.add(parseDescriptor(xmlPullParser2, "InbandEventStream"));
                            } else {
                                arrayList5 = arrayList11;
                                if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
                                    parseAdaptationSetChild(xmlPullParser);
                                }
                            }
                            iCheckContentTypeConsistency = i;
                            baseUrl = str2;
                        }
                        segmentBase2 = segmentTemplate;
                        iCheckContentTypeConsistency = i;
                        baseUrl = str2;
                        arrayList5 = arrayList11;
                    }
                    strCheckLanguageConsistency = str7;
                    arrayList = arrayList10;
                    arrayList2 = arrayList9;
                    arrayList3 = arrayList8;
                    arrayList5 = arrayList7;
                    arrayList4 = arrayList6;
                    str3 = str6;
                    str4 = str5;
                    xmlPullParser2 = xmlPullParser3;
                }
                strCheckLanguageConsistency = str7;
                i = iCheckContentTypeConsistency;
                str2 = baseUrl;
                arrayList = arrayList10;
                arrayList2 = arrayList9;
                arrayList3 = arrayList8;
                arrayList5 = arrayList7;
                arrayList4 = arrayList6;
                str3 = str6;
                str4 = str5;
                xmlPullParser2 = xmlPullParser3;
                iCheckContentTypeConsistency = i;
                baseUrl = str2;
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser2, "AdaptationSet")) {
                break;
            }
            xmlPullParser3 = xmlPullParser2;
            arrayList10 = arrayList;
            arrayList7 = arrayList5;
            str7 = strCheckLanguageConsistency;
            arrayList9 = arrayList2;
            arrayList8 = arrayList3;
            arrayList6 = arrayList4;
            str6 = str3;
            str5 = str4;
        }
        List<Representation> arrayList12 = new ArrayList<>(arrayList.size());
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            arrayList12.add(buildRepresentation((RepresentationInfo) arrayList.get(i6), this.contentId, arrayList4, arrayList5));
        }
        return buildAdaptationSet(i2, iCheckContentTypeConsistency, arrayList12, arrayList3, arrayList2);
    }

    protected AdaptationSet buildAdaptationSet(int i, int i2, List<Representation> list, List<Descriptor> list2, List<Descriptor> list3) {
        return new AdaptationSet(i, i2, list, list2, list3);
    }

    protected int parseContentType(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if (voMimeTypes.VOBASE_TYPE_AUDIO.equals(attributeValue)) {
            return 1;
        }
        if (voMimeTypes.VOBASE_TYPE_VIDEO.equals(attributeValue)) {
            return 2;
        }
        return "text".equals(attributeValue) ? 3 : -1;
    }

    protected int getContentType(Format format) {
        String str = format.sampleMimeType;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (MimeTypes.isVideo(str)) {
            return 2;
        }
        if (MimeTypes.isAudio(str)) {
            return 1;
        }
        return mimeTypeIsRawText(str) ? 3 : -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0096 A[LOOP:0: B:3:0x0017->B:34:0x0096, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008b A[EDGE_INSN: B:35:0x008b->B:31:0x008b BREAK  A[LOOP:0: B:3:0x0017->B:34:0x0096], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected com.google.android.exoplayer2.drm.DrmInitData.SchemeData parseContentProtection(org.xmlpull.v1.XmlPullParser r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r9 = this;
            r0 = 0
            java.lang.String r1 = "schemeIdUri"
            java.lang.String r1 = r10.getAttributeValue(r0, r1)
            java.lang.String r2 = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95"
            boolean r1 = r2.equals(r1)
            java.lang.String r2 = "value"
            java.lang.String r5 = r10.getAttributeValue(r0, r2)
            r2 = 0
            r3 = r0
            r4 = r3
            r6 = r2
        L17:
            r10.next()
            r7 = 4
            if (r3 != 0) goto L42
            java.lang.String r8 = "cenc:pssh"
            boolean r8 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r10, r8)
            if (r8 == 0) goto L42
            int r8 = r10.next()
            if (r8 != r7) goto L42
            java.lang.String r3 = r10.getText()
            byte[] r3 = android.util.Base64.decode(r3, r2)
            java.util.UUID r4 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.parseUuid(r3)
            if (r4 != 0) goto L81
            java.lang.String r3 = "MpdParser"
            java.lang.String r7 = "Skipping malformed cenc:pssh data"
            android.util.Log.w(r3, r7)
            r7 = r0
            goto L82
        L42:
            if (r3 != 0) goto L66
            if (r1 == 0) goto L66
            java.lang.String r8 = "mspr:pro"
            boolean r8 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r10, r8)
            if (r8 == 0) goto L66
            int r8 = r10.next()
            if (r8 != r7) goto L66
            java.util.UUID r3 = com.google.android.exoplayer2.C.PLAYREADY_UUID
            java.lang.String r4 = r10.getText()
            byte[] r4 = android.util.Base64.decode(r4, r2)
            byte[] r4 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.buildPsshAtom(r3, r4)
            r7 = r4
            r8 = r6
            r4 = r3
            goto L83
        L66:
            java.lang.String r7 = "widevine:license"
            boolean r7 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r10, r7)
            if (r7 == 0) goto L81
            java.lang.String r6 = "robustness_level"
            java.lang.String r6 = r10.getAttributeValue(r0, r6)
            if (r6 == 0) goto L80
            java.lang.String r7 = "HW"
            boolean r6 = r6.startsWith(r7)
            if (r6 == 0) goto L80
            r6 = 1
            goto L81
        L80:
            r6 = r2
        L81:
            r7 = r3
        L82:
            r8 = r6
        L83:
            java.lang.String r3 = "ContentProtection"
            boolean r3 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r10, r3)
            if (r3 == 0) goto L96
            if (r7 == 0) goto L95
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r0 = new com.google.android.exoplayer2.drm.DrmInitData$SchemeData
            java.lang.String r6 = "video/mp4"
            r3 = r0
            r3.<init>(r4, r5, r6, r7, r8)
        L95:
            return r0
        L96:
            r3 = r7
            r6 = r8
            goto L17
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseContentProtection(org.xmlpull.v1.XmlPullParser):com.google.android.exoplayer2.drm.DrmInitData$SchemeData");
    }

    protected int parseRole(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String string = parseString(xmlPullParser, "schemeIdUri", null);
        String string2 = parseString(xmlPullParser, "value", null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Role"));
        return ("urn:mpeg:dash:role:2011".equals(string) && MediaTrack.ROLE_MAIN.equals(string2)) ? 1 : 0;
    }

    protected RepresentationInfo parseRepresentation(XmlPullParser xmlPullParser, String str, String str2, String str3, int i, int i2, float f, int i3, int i4, String str4, int i5, List<Descriptor> list, SegmentBase segmentBase) throws XmlPullParserException, IOException {
        SegmentBase segmentTemplate;
        String baseUrl;
        SegmentBase segmentBase2;
        String attributeValue = xmlPullParser.getAttributeValue(null, "id");
        int i6 = parseInt(xmlPullParser, "bandwidth", -1);
        String string = parseString(xmlPullParser, "mimeType", str2);
        String string2 = parseString(xmlPullParser, "codecs", str3);
        int i7 = parseInt(xmlPullParser, "width", i);
        int i8 = parseInt(xmlPullParser, "height", i2);
        float frameRate = parseFrameRate(xmlPullParser, f);
        int i9 = parseInt(xmlPullParser, "audioSamplingRate", i4);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int audioChannelConfiguration = i3;
        SegmentBase segmentBase3 = segmentBase;
        boolean z = false;
        String str5 = str;
        while (true) {
            xmlPullParser.next();
            int i10 = audioChannelConfiguration;
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "BaseURL")) {
                if (!z) {
                    baseUrl = parseBaseUrl(xmlPullParser, str5);
                    z = true;
                    segmentBase2 = segmentBase3;
                    audioChannelConfiguration = i10;
                }
                audioChannelConfiguration = i10;
                baseUrl = str5;
                segmentBase2 = segmentBase3;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "AudioChannelConfiguration")) {
                audioChannelConfiguration = parseAudioChannelConfiguration(xmlPullParser);
                baseUrl = str5;
                segmentBase2 = segmentBase3;
            } else {
                if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentBase")) {
                    segmentTemplate = parseSegmentBase(xmlPullParser, (SegmentBase.SingleSegmentBase) segmentBase3);
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentList")) {
                    segmentTemplate = parseSegmentList(xmlPullParser, (SegmentBase.SegmentList) segmentBase3);
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTemplate")) {
                    segmentTemplate = parseSegmentTemplate(xmlPullParser, (SegmentBase.SegmentTemplate) segmentBase3);
                } else {
                    if (XmlPullParserUtil.isStartTag(xmlPullParser, "ContentProtection")) {
                        DrmInitData.SchemeData contentProtection = parseContentProtection(xmlPullParser);
                        if (contentProtection != null) {
                            arrayList.add(contentProtection);
                        }
                    } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "InbandEventStream")) {
                        arrayList2.add(parseDescriptor(xmlPullParser, "InbandEventStream"));
                    }
                    audioChannelConfiguration = i10;
                    baseUrl = str5;
                    segmentBase2 = segmentBase3;
                }
                baseUrl = str5;
                segmentBase2 = segmentTemplate;
                audioChannelConfiguration = i10;
            }
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "Representation")) {
                break;
            }
            str5 = baseUrl;
            segmentBase3 = segmentBase2;
        }
        return new RepresentationInfo(buildFormat(attributeValue, string, i7, i8, frameRate, audioChannelConfiguration, i9, i6, str4, i5, list, string2), baseUrl, segmentBase2 != null ? segmentBase2 : new SegmentBase.SingleSegmentBase(), arrayList, arrayList2);
    }

    protected Format buildFormat(String str, String str2, int i, int i2, float f, int i3, int i4, int i5, String str3, int i6, List<Descriptor> list, String str4) {
        int cea708AccessibilityChannel;
        String sampleMimeType = getSampleMimeType(str2, str4);
        if (sampleMimeType != null) {
            if (MimeTypes.isVideo(sampleMimeType)) {
                return Format.createVideoContainerFormat(str, str2, sampleMimeType, str4, i5, i, i2, f, null, i6);
            }
            if (MimeTypes.isAudio(sampleMimeType)) {
                return Format.createAudioContainerFormat(str, str2, sampleMimeType, str4, i5, i3, i4, null, i6, str3);
            }
            if (mimeTypeIsRawText(sampleMimeType)) {
                if ("application/cea-608".equals(sampleMimeType)) {
                    cea708AccessibilityChannel = parseCea608AccessibilityChannel(list);
                } else {
                    cea708AccessibilityChannel = "application/cea-708".equals(sampleMimeType) ? parseCea708AccessibilityChannel(list) : -1;
                }
                return Format.createTextContainerFormat(str, str2, sampleMimeType, str4, i5, i6, str3, cea708AccessibilityChannel);
            }
        }
        return Format.createContainerFormat(str, str2, sampleMimeType, str4, i5, i6, str3);
    }

    protected Representation buildRepresentation(RepresentationInfo representationInfo, String str, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2) {
        Format formatCopyWithDrmInitData = representationInfo.format;
        ArrayList<DrmInitData.SchemeData> arrayList3 = representationInfo.drmSchemeDatas;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            formatCopyWithDrmInitData = formatCopyWithDrmInitData.copyWithDrmInitData(new DrmInitData(arrayList3));
        }
        ArrayList<Descriptor> arrayList4 = representationInfo.inbandEventStreams;
        arrayList4.addAll(arrayList2);
        return Representation.newInstance(str, -1L, formatCopyWithDrmInitData, representationInfo.baseUrl, representationInfo.segmentBase, arrayList4);
    }

    protected SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser xmlPullParser, SegmentBase.SingleSegmentBase singleSegmentBase) throws XmlPullParserException, IOException {
        long j;
        long j2;
        long j3 = parseLong(xmlPullParser, "timescale", singleSegmentBase != null ? singleSegmentBase.timescale : 1L);
        long j4 = parseLong(xmlPullParser, "presentationTimeOffset", singleSegmentBase != null ? singleSegmentBase.presentationTimeOffset : 0L);
        long j5 = singleSegmentBase != null ? singleSegmentBase.indexStart : 0L;
        long j6 = singleSegmentBase != null ? singleSegmentBase.indexLength : 0L;
        String attributeValue = xmlPullParser.getAttributeValue(null, "indexRange");
        if (attributeValue != null) {
            String[] strArrSplit = attributeValue.split("-");
            long j7 = Long.parseLong(strArrSplit[0]);
            j = (Long.parseLong(strArrSplit[1]) - j7) + 1;
            j2 = j7;
        } else {
            j = j6;
            j2 = j5;
        }
        RangedUri initialization = singleSegmentBase != null ? singleSegmentBase.initialization : null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentBase"));
        return buildSingleSegmentBase(initialization, j3, j4, j2, j);
    }

    protected SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4) {
        return new SegmentBase.SingleSegmentBase(rangedUri, j, j2, j3, j4);
    }

    protected SegmentBase.SegmentList parseSegmentList(XmlPullParser xmlPullParser, SegmentBase.SegmentList segmentList) throws XmlPullParserException, IOException {
        long j = parseLong(xmlPullParser, "timescale", segmentList != null ? segmentList.timescale : 1L);
        long j2 = parseLong(xmlPullParser, "presentationTimeOffset", segmentList != null ? segmentList.presentationTimeOffset : 0L);
        long j3 = parseLong(xmlPullParser, "duration", segmentList != null ? segmentList.duration : -9223372036854775807L);
        int i = parseInt(xmlPullParser, "startNumber", segmentList != null ? segmentList.startNumber : 1);
        List<RangedUri> arrayList = null;
        RangedUri initialization = null;
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                segmentTimeline = parseSegmentTimeline(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentURL")) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(parseSegmentUrl(xmlPullParser));
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentList"));
        if (segmentList != null) {
            if (initialization == null) {
                initialization = segmentList.initialization;
            }
            if (segmentTimeline == null) {
                segmentTimeline = segmentList.segmentTimeline;
            }
            if (arrayList == null) {
                arrayList = segmentList.mediaSegments;
            }
        }
        return buildSegmentList(initialization, j, j2, i, j3, segmentTimeline, arrayList);
    }

    protected SegmentBase.SegmentList buildSegmentList(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentBase.SegmentTimelineElement> list, List<RangedUri> list2) {
        return new SegmentBase.SegmentList(rangedUri, j, j2, i, j3, list, list2);
    }

    protected SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser xmlPullParser, SegmentBase.SegmentTemplate segmentTemplate) throws XmlPullParserException, IOException {
        long j = parseLong(xmlPullParser, "timescale", segmentTemplate != null ? segmentTemplate.timescale : 1L);
        long j2 = parseLong(xmlPullParser, "presentationTimeOffset", segmentTemplate != null ? segmentTemplate.presentationTimeOffset : 0L);
        long j3 = parseLong(xmlPullParser, "duration", segmentTemplate != null ? segmentTemplate.duration : -9223372036854775807L);
        int i = parseInt(xmlPullParser, "startNumber", segmentTemplate != null ? segmentTemplate.startNumber : 1);
        RangedUri initialization = null;
        UrlTemplate urlTemplate = parseUrlTemplate(xmlPullParser, "media", segmentTemplate != null ? segmentTemplate.mediaTemplate : null);
        UrlTemplate urlTemplate2 = parseUrlTemplate(xmlPullParser, "initialization", segmentTemplate != null ? segmentTemplate.initializationTemplate : null);
        List<SegmentBase.SegmentTimelineElement> segmentTimeline = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Initialization")) {
                initialization = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "SegmentTimeline")) {
                segmentTimeline = parseSegmentTimeline(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentTemplate"));
        if (segmentTemplate != null) {
            if (initialization == null) {
                initialization = segmentTemplate.initialization;
            }
            if (segmentTimeline == null) {
                segmentTimeline = segmentTemplate.segmentTimeline;
            }
        }
        return buildSegmentTemplate(initialization, j, j2, i, j3, segmentTimeline, urlTemplate2, urlTemplate);
    }

    protected SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j, long j2, int i, long j3, List<SegmentBase.SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2) {
        return new SegmentBase.SegmentTemplate(rangedUri, j, j2, i, j3, list, urlTemplate, urlTemplate2);
    }

    protected List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        long j = 0;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "S")) {
                j = parseLong(xmlPullParser, "t", j);
                long j2 = parseLong(xmlPullParser, "d", -9223372036854775807L);
                int i = parseInt(xmlPullParser, "r", 0) + 1;
                for (int i2 = 0; i2 < i; i2++) {
                    arrayList.add(buildSegmentTimelineElement(j, j2));
                    j += j2;
                }
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "SegmentTimeline"));
        return arrayList;
    }

    protected SegmentBase.SegmentTimelineElement buildSegmentTimelineElement(long j, long j2) {
        return new SegmentBase.SegmentTimelineElement(j, j2);
    }

    protected UrlTemplate parseUrlTemplate(XmlPullParser xmlPullParser, String str, UrlTemplate urlTemplate) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue != null ? UrlTemplate.compile(attributeValue) : urlTemplate;
    }

    protected RangedUri parseInitialization(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "sourceURL", "range");
    }

    protected RangedUri parseSegmentUrl(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "media", "mediaRange");
    }

    protected RangedUri parseRangedUrl(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue(null, str2);
        if (attributeValue2 != null) {
            String[] strArrSplit = attributeValue2.split("-");
            j = Long.parseLong(strArrSplit[0]);
            if (strArrSplit.length == 2) {
                j2 = (Long.parseLong(strArrSplit[1]) - j) + 1;
            }
            return buildRangedUri(attributeValue, j, j2);
        }
        j = 0;
        j2 = -1;
        return buildRangedUri(attributeValue, j, j2);
    }

    protected RangedUri buildRangedUri(String str, long j, long j2) {
        return new RangedUri(str, j, j2);
    }

    protected int parseAudioChannelConfiguration(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int i = "urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(parseString(xmlPullParser, "schemeIdUri", null)) ? parseInt(xmlPullParser, "value", -1) : -1;
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    private static String getSampleMimeType(String str, String str2) {
        if (MimeTypes.isAudio(str)) {
            return MimeTypes.getAudioMediaMimeType(str2);
        }
        if (MimeTypes.isVideo(str)) {
            return MimeTypes.getVideoMediaMimeType(str2);
        }
        if (mimeTypeIsRawText(str)) {
            return str;
        }
        if ("application/mp4".equals(str)) {
            if ("stpp".equals(str2)) {
                return "application/ttml+xml";
            }
            if ("wvtt".equals(str2)) {
                return "application/x-mp4-vtt";
            }
        } else if ("application/x-rawcc".equals(str) && str2 != null) {
            if (str2.contains("cea708")) {
                return "application/cea-708";
            }
            if (str2.contains("eia608") || str2.contains("cea608")) {
                return "application/cea-608";
            }
        }
        return null;
    }

    private static boolean mimeTypeIsRawText(String str) {
        return MimeTypes.isText(str) || "application/ttml+xml".equals(str) || "application/x-mp4-vtt".equals(str) || "application/cea-708".equals(str) || "application/cea-608".equals(str);
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        Assertions.checkState(str.equals(str2));
        return str;
    }

    private static int checkContentTypeConsistency(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Assertions.checkState(i == i2);
        return i;
    }

    protected static Descriptor parseDescriptor(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String string = parseString(xmlPullParser, "schemeIdUri", "");
        String string2 = parseString(xmlPullParser, "value", null);
        String string3 = parseString(xmlPullParser, "id", null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return new Descriptor(string, string2, string3);
    }

    protected static int parseCea608AccessibilityChannel(List<Descriptor> list) {
        String str;
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri) && (str = descriptor.value) != null) {
                Matcher matcher = CEA_608_ACCESSIBILITY_PATTERN.matcher(str);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w("MpdParser", "Unable to parse CEA-608 channel number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static int parseCea708AccessibilityChannel(List<Descriptor> list) {
        String str;
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri) && (str = descriptor.value) != null) {
                Matcher matcher = CEA_708_ACCESSIBILITY_PATTERN.matcher(str);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.w("MpdParser", "Unable to parse CEA-708 service block number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static float parseFrameRate(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue(null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = FRAME_RATE_PATTERN.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int i = Integer.parseInt(matcher.group(1));
        return !TextUtils.isEmpty(matcher.group(2)) ? i / Integer.parseInt(r2) : i;
    }

    protected static long parseDuration(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Util.parseXsDuration(attributeValue);
    }

    protected static long parseDateTime(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Util.parseXsDateTime(attributeValue);
    }

    protected static String parseBaseUrl(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        xmlPullParser.next();
        return UriUtil.resolve(str, xmlPullParser.getText());
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    protected static String parseString(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue(null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    private static final class RepresentationInfo {
        public final String baseUrl;
        public final ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        public final Format format;
        public final ArrayList<Descriptor> inbandEventStreams;
        public final SegmentBase segmentBase;

        public RepresentationInfo(Format format, String str, SegmentBase segmentBase, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2) {
            this.format = format;
            this.baseUrl = str;
            this.segmentBase = segmentBase;
            this.drmSchemeDatas = arrayList;
            this.inbandEventStreams = arrayList2;
        }
    }
}
