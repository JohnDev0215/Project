package com.amazon.avod.playbackclient.subtitle.internal;

import android.annotation.SuppressLint;
import android.util.Xml;
import androidx.core.view.GravityCompat;
import com.amazon.avod.core.Subtitle;
import com.amazon.avod.core.subtitle.SubtitleAnnotationTextPosition;
import com.amazon.avod.core.subtitle.SubtitleFormat;
import com.amazon.avod.core.subtitle.SubtitleRegionWritingMode;
import com.amazon.avod.core.subtitle.SubtitleRubyPosition;
import com.amazon.avod.core.subtitle.SubtitleShowBackgroundStatus;
import com.amazon.avod.core.subtitle.SubtitleTextCombineType;
import com.amazon.avod.core.subtitle.SubtitleTextStylingType;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleRegionElement;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleStyleElement;
import com.amazon.avod.playbackclient.subtitle.internal.attributes.Dimension;
import com.amazon.avod.playbackclient.subtitle.internal.attributes.Overflow;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.playback.DurationUtils;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.firebase.perf.plugin.util.AsmString;
import edu.umd.cs.findbugs.annotations.SuppressWarnings;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes2.dex */
class SubtitleParser {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private final boolean mShouldHonorWhitespacesBetweenTags;
    private final boolean mShouldTrimNewLineOverflow;
    private final SubtitleConfig mSubtitleConfig;

    interface RegionElementParser {
        boolean hasValidAttributes(@Nonnull Map<SubtitleAttribute, String> map);

        @Nonnull
        SubtitleRegionElement parse(@Nonnull Map<SubtitleAttribute, String> map);
    }

    interface StyleElementParser {
        boolean hasValidAttributes(@Nonnull Map<SubtitleAttribute, String> map);

        @Nonnull
        SubtitleStyleElement parse(@Nonnull Map<SubtitleAttribute, String> map);
    }

    public SubtitleParser() {
        this(SubtitleConfig.getInstance(), SubtitleConfig.getInstance().shouldTrimNewLineOverflow(), SubtitleConfig.getInstance().shouldHonorWhitespacesBetweenTags());
    }

    SubtitleParser(SubtitleConfig subtitleConfig, boolean z, boolean z2) {
        this.mSubtitleConfig = subtitleConfig;
        this.mShouldTrimNewLineOverflow = z;
        this.mShouldHonorWhitespacesBetweenTags = z2;
    }

    @Nonnull
    SubtitleCollection parseXmlStream(@Nonnull InputStream inputStream, long j, long j2) throws XmlPullParserException, NoSuchFieldException, IOException {
        TextElementParser textElementParserV1;
        StyleElementParser styleElementParserV1;
        RegionElementParser regionElementParserV1;
        Preconditions.checkNotNull(inputStream);
        if (this.mSubtitleConfig.shouldUseTTMLV2Parser()) {
            textElementParserV1 = new TextElementParserV2();
            styleElementParserV1 = new StyleElementParserV2(this.mSubtitleConfig);
            regionElementParserV1 = new RegionElementParserV2(this.mSubtitleConfig);
        } else {
            textElementParserV1 = new TextElementParserV1();
            styleElementParserV1 = new StyleElementParserV1();
            regionElementParserV1 = new RegionElementParserV1();
        }
        TextElementParser textElementParser = textElementParserV1;
        StyleElementParser styleElementParser = styleElementParserV1;
        RegionElementParser regionElementParser = regionElementParserV1;
        try {
            DLog.devf("SubtitleParser - parseXmlStream fragmentStartTimeMillis [%d]", Long.valueOf(j2));
            return parse(inputStream, false, j, j2, textElementParser, styleElementParser, regionElementParser);
        } finally {
            DLog.devf("-----ENDING DOCUMENT READ-----");
        }
    }

    @Nonnull
    SubtitleCollection parseXmlFile(@Nonnull File file, Subtitle subtitle) throws XmlPullParserException, NoSuchFieldException, IOException {
        TextElementParser textElementParserV1;
        StyleElementParser styleElementParserV1;
        RegionElementParser regionElementParserV1;
        Preconditions.checkNotNull(file, "subtitleFile");
        Preconditions.checkNotNull(subtitle, "subtitleData");
        if (subtitle.getFormat() == SubtitleFormat.TTMLv2) {
            textElementParserV1 = new TextElementParserV2();
            styleElementParserV1 = new StyleElementParserV2(this.mSubtitleConfig);
            regionElementParserV1 = new RegionElementParserV2(this.mSubtitleConfig);
        } else {
            textElementParserV1 = new TextElementParserV1();
            styleElementParserV1 = new StyleElementParserV1();
            regionElementParserV1 = new RegionElementParserV1();
        }
        return parseXmlFile(file, subtitle.isForcedNarrative(), textElementParserV1, styleElementParserV1, regionElementParserV1);
    }

    @Nonnull
    SubtitleCollection parseXmlFile(@Nonnull File file, boolean z, @Nonnull TextElementParser textElementParser, @Nonnull StyleElementParser styleElementParser, @Nonnull RegionElementParser regionElementParser) throws XmlPullParserException, NoSuchFieldException, IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                try {
                    SubtitleCollection subtitleCollection = parse(bufferedInputStream, z, 0L, -1L, textElementParser, styleElementParser, regionElementParser);
                    bufferedInputStream.close();
                    fileInputStream.close();
                    return subtitleCollection;
                } finally {
                }
            } finally {
            }
        } finally {
            DLog.devf("-----ENDING DOCUMENT READ-----");
        }
    }

    private SubtitleCollection parse(@Nonnull InputStream inputStream, boolean z, long j, long j2, @Nonnull TextElementParser textElementParser, @Nonnull StyleElementParser styleElementParser, @Nonnull RegionElementParser regionElementParser) throws XmlPullParserException, NoSuchFieldException, IOException {
        ArrayList arrayList;
        ArrayList arrayList2;
        Stack<SubtitleTextStyle> stack;
        ArrayList arrayList3;
        StringBuilder sb;
        EnumMap enumMap;
        XmlPullParser xmlPullParser;
        int i;
        HashMap map;
        int i2;
        HashMap map2;
        ArrayList arrayList4;
        int i3;
        ArrayList arrayList5;
        HashMap map3;
        SubtitleParser subtitleParser = this;
        ArrayList arrayListNewArrayList = Lists.newArrayList();
        HashMap map4 = new HashMap();
        HashMap map5 = new HashMap();
        HashMap map6 = new HashMap();
        XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
        int i4 = 1;
        xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        XmlPullParser xmlPullParserNewPullParser = xmlPullParserFactoryNewInstance.newPullParser();
        xmlPullParserNewPullParser.setInput(inputStream, Xml.Encoding.UTF_8.toString());
        StringBuilder sb2 = new StringBuilder(64);
        SubtitleShowBackgroundStatus subtitleShowBackgroundStatus = SubtitleShowBackgroundStatus.UNRECOGNIZED;
        EnumMap enumMapNewEnumMap = Maps.newEnumMap(SubtitleAttribute.class);
        Stack<SubtitleTextStyle> stack2 = new Stack<>();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = arrayListNewArrayList;
        long j3 = j2;
        SubtitleShowBackgroundStatus subtitleShowBackgroundStatusFindMatch = subtitleShowBackgroundStatus;
        HashMap map7 = map5;
        int eventType = xmlPullParserNewPullParser.getEventType();
        long j4 = 0;
        int length = -1;
        int length2 = -1;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (eventType != i4) {
            if (eventType == 0) {
                DLog.devf("-----STARTING DOCUMENT READ-----");
            } else {
                if (eventType == 2) {
                    switch (AnonymousClass1.$SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.findMatch(xmlPullParserNewPullParser.getName()).ordinal()]) {
                        case 1:
                            arrayList2 = arrayList7;
                            Map<SubtitleAttribute, String> attributes = subtitleParser.getAttributes(xmlPullParserNewPullParser);
                            SubtitleAttribute subtitleAttribute = SubtitleAttribute.LANGUAGE;
                            if (attributes.containsKey(subtitleAttribute)) {
                                attributes.get(subtitleAttribute);
                            }
                            arrayList = arrayList8;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            map = map6;
                            map2 = map4;
                            arrayList4 = arrayList9;
                            i = 1;
                            break;
                        case 2:
                            arrayList2 = arrayList7;
                            Map<SubtitleAttribute, String> attributes2 = subtitleParser.getAttributes(xmlPullParserNewPullParser);
                            str2 = attributes2.get(SubtitleAttribute.STYLE);
                            str = attributes2.get(SubtitleAttribute.REGION);
                            arrayList = arrayList8;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            map = map6;
                            map2 = map4;
                            arrayList4 = arrayList9;
                            i = 1;
                            break;
                        case 3:
                            arrayList2 = arrayList7;
                            Map<SubtitleAttribute, String> attributes3 = subtitleParser.getAttributes(xmlPullParserNewPullParser);
                            if (subtitleParser.mShouldHonorWhitespacesBetweenTags) {
                                sb2.setLength(0);
                            }
                            SubtitleAttribute subtitleAttribute2 = SubtitleAttribute.STYLE;
                            if (attributes3.containsKey(subtitleAttribute2)) {
                                arrayList6.add(attributes3.get(subtitleAttribute2));
                            }
                            enumMapNewEnumMap.putAll(attributes3);
                            arrayList = arrayList8;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            map = map6;
                            map2 = map4;
                            arrayList4 = arrayList9;
                            i = 1;
                            break;
                        case 4:
                        case 5:
                        case 6:
                            arrayList2 = arrayList7;
                            enumMapNewEnumMap.putAll(subtitleParser.getAttributes(xmlPullParserNewPullParser));
                            arrayList = arrayList8;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            map = map6;
                            map2 = map4;
                            arrayList4 = arrayList9;
                            i = 1;
                            break;
                        case 7:
                            Map<SubtitleAttribute, String> attributes4 = subtitleParser.getAttributes(xmlPullParserNewPullParser);
                            SubtitleAttribute subtitleAttribute3 = SubtitleAttribute.STYLE;
                            if (attributes4.containsKey(subtitleAttribute3)) {
                                SubtitleTextStyle subtitleTextStyle = new SubtitleTextStyle();
                                String str5 = attributes4.get(subtitleAttribute3);
                                subtitleTextStyle.setStyleId(str5);
                                subtitleTextStyle.setStart(sb2.length());
                                SubtitleStyleElement subtitleStyleElement = map4.get(str5);
                                if (subtitleStyleElement != null) {
                                    arrayList2 = arrayList7;
                                    subtitleTextStyle.setHasTextCombineTag(subtitleStyleElement.getTextCombine() == SubtitleTextCombineType.ALL);
                                } else {
                                    arrayList2 = arrayList7;
                                }
                                stack2.push(subtitleTextStyle);
                                arrayList6.add(str5);
                            } else {
                                arrayList2 = arrayList7;
                            }
                            enumMapNewEnumMap.putAll(attributes4);
                            arrayList = arrayList8;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            map = map6;
                            map2 = map4;
                            arrayList4 = arrayList9;
                            i = 1;
                            break;
                        case 8:
                            sb2.append(LINE_SEPARATOR);
                            arrayList2 = arrayList7;
                            arrayList = arrayList8;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            map = map6;
                            map2 = map4;
                            arrayList4 = arrayList9;
                            i = 1;
                            break;
                        case 9:
                            DLog.warnf("UNSUPPORTED START TAG: <%s>", xmlPullParserNewPullParser.getName());
                            subtitleParser.skipUnsupportedTag(xmlPullParserNewPullParser);
                            eventType = xmlPullParserNewPullParser.next();
                            i4 = 1;
                            break;
                        default:
                            arrayList2 = arrayList7;
                            arrayList = arrayList8;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            map = map6;
                            map2 = map4;
                            arrayList4 = arrayList9;
                            i = 1;
                            break;
                    }
                } else {
                    arrayList2 = arrayList7;
                    if (eventType == 4) {
                        if (!xmlPullParserNewPullParser.isWhitespace() || subtitleParser.mShouldHonorWhitespacesBetweenTags) {
                            String text = xmlPullParserNewPullParser.getText();
                            if (subtitleParser.mShouldTrimNewLineOverflow) {
                                text = text.replace(LINE_SEPARATOR, "");
                            }
                            int i5 = AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextStylingType[subtitleParser.getTextStyleType(stack2, map4).ordinal()];
                            if (i5 == 1) {
                                length2 = sb2.length();
                                length = (text.length() + length2) - 1;
                                sb2.append(text);
                            } else if (i5 == 2) {
                                arrayList8.add(SubtitleAnnotationElement.newBuilder().setStart(length2).setEnd(length).setAnnotationText(text).setTextPosition(subtitleParser.getAnnotationPosition(enumMapNewEnumMap, map6, stack2, map4)).build());
                            } else {
                                sb2.append(text);
                            }
                            arrayList = arrayList8;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            map = map6;
                            map2 = map4;
                            arrayList4 = arrayList9;
                            i = 1;
                        }
                    } else if (eventType == 3) {
                        int i6 = AnonymousClass1.$SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.findMatch(xmlPullParserNewPullParser.getName()).ordinal()];
                        if (i6 == 3) {
                            HashMap map8 = map7;
                            int i7 = 0;
                            if (textElementParser.hasValidAttributes(enumMapNewEnumMap)) {
                                if (j3 != -1) {
                                    long time = parseTime(enumMapNewEnumMap.get(SubtitleAttribute.BEGIN));
                                    if (j4 == 0) {
                                        j4 = time;
                                    } else {
                                        j3 = j2 + (time - j4);
                                    }
                                    Long lValueOf = Long.valueOf(j2);
                                    map7 = map8;
                                    Long lValueOf2 = Long.valueOf(time);
                                    arrayList5 = arrayList8;
                                    DLog.devf("SubtitleParser - fragmentStartTimeMillis [%d], elementBeginTime [%d]", lValueOf, lValueOf2);
                                } else {
                                    map7 = map8;
                                    arrayList5 = arrayList8;
                                }
                                arrayList = arrayList5;
                                arrayList2 = arrayList2;
                                stack = stack2;
                                arrayList3 = arrayList6;
                                sb = sb2;
                                enumMap = enumMapNewEnumMap;
                                xmlPullParser = xmlPullParserNewPullParser;
                                i = 1;
                                map = map6;
                                i2 = length;
                                map2 = map4;
                                arrayList4 = arrayList9;
                                i3 = length2;
                                arrayList4.add(textElementParser.parse(enumMapNewEnumMap, sb2.toString(), j, j3, arrayList2, arrayList, map6, arrayList3, map7, styleElementParser));
                                i7 = 0;
                            } else {
                                map7 = map8;
                                arrayList = arrayList8;
                                arrayList2 = arrayList2;
                                stack = stack2;
                                arrayList3 = arrayList6;
                                sb = sb2;
                                enumMap = enumMapNewEnumMap;
                                xmlPullParser = xmlPullParserNewPullParser;
                                map = map6;
                                i2 = length;
                                map2 = map4;
                                arrayList4 = arrayList9;
                                i = 1;
                                i3 = length2;
                            }
                            sb.setLength(i7);
                            enumMap.clear();
                            stack.clear();
                            arrayList3.clear();
                            arrayList2.clear();
                            arrayList.clear();
                            length2 = i3;
                            length = i2;
                        } else if (i6 == 4) {
                            if (styleElementParser.hasValidAttributes(enumMapNewEnumMap)) {
                                SubtitleStyleElement subtitleStyleElement2 = styleElementParser.parse(enumMapNewEnumMap);
                                map4.put(subtitleStyleElement2.getId(), subtitleStyleElement2);
                                map3 = map7;
                                map3.put(subtitleStyleElement2.getId(), new HashMap(enumMapNewEnumMap));
                            } else {
                                map3 = map7;
                            }
                            enumMapNewEnumMap.clear();
                            map7 = map3;
                            arrayList = arrayList8;
                            arrayList2 = arrayList2;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            map = map6;
                            i2 = length;
                            map2 = map4;
                            arrayList4 = arrayList9;
                            i = 1;
                            i3 = length2;
                            length2 = i3;
                            length = i2;
                        } else if (i6 == 5) {
                            arrayList7 = arrayList2;
                            i4 = 1;
                            if (regionElementParser.hasValidAttributes(enumMapNewEnumMap)) {
                                SubtitleRegionElement subtitleRegionElement = regionElementParser.parse(enumMapNewEnumMap);
                                map6.put(subtitleRegionElement.getId(), subtitleRegionElement);
                            }
                            enumMapNewEnumMap.clear();
                        } else if (i6 == 6) {
                            Map<SubtitleAttribute, String> attributes5 = subtitleParser.getAttributes(xmlPullParserNewPullParser);
                            SubtitleAttribute subtitleAttribute4 = SubtitleAttribute.FONT_SIZE;
                            if (attributes5.containsKey(subtitleAttribute4)) {
                                str3 = attributes5.get(subtitleAttribute4);
                            }
                            SubtitleAttribute subtitleAttribute5 = SubtitleAttribute.LINE_HEIGHT;
                            if (attributes5.containsKey(subtitleAttribute5)) {
                                str4 = attributes5.get(subtitleAttribute5);
                            }
                            SubtitleAttribute subtitleAttribute6 = SubtitleAttribute.SHOW_BACKGROUND;
                            if (attributes5.containsKey(subtitleAttribute6)) {
                                subtitleShowBackgroundStatusFindMatch = SubtitleShowBackgroundStatus.findMatch(attributes5.get(subtitleAttribute6));
                            }
                            arrayList = arrayList8;
                            arrayList2 = arrayList2;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            i = 1;
                            map = map6;
                            map2 = map4;
                            arrayList4 = arrayList9;
                        } else if (i6 == 7) {
                            i4 = 1;
                            if (stack2.size() > 0) {
                                SubtitleTextStyle subtitleTextStylePop = stack2.pop();
                                int length3 = sb2.length();
                                subtitleTextStylePop.setEnd(length3 != 0 ? length3 - 1 : 0);
                                arrayList2.add(subtitleTextStylePop);
                            }
                            arrayList = arrayList8;
                            stack = stack2;
                            arrayList3 = arrayList6;
                            sb = sb2;
                            enumMap = enumMapNewEnumMap;
                            xmlPullParser = xmlPullParserNewPullParser;
                            i = i4;
                            map = map6;
                            i2 = length;
                            map2 = map4;
                            arrayList4 = arrayList9;
                            i3 = length2;
                            length2 = i3;
                            length = i2;
                        } else if (i6 == 9) {
                            i4 = 1;
                            DLog.warnf("MALFORMED XML: END TAG: <%s> DOES NOT HAVE A START TAG AND IS NOT RECOGNIZED.", xmlPullParserNewPullParser.getName());
                            eventType = xmlPullParserNewPullParser.next();
                            arrayList7 = arrayList2;
                        }
                    }
                    arrayList = arrayList8;
                    stack = stack2;
                    arrayList3 = arrayList6;
                    sb = sb2;
                    enumMap = enumMapNewEnumMap;
                    xmlPullParser = xmlPullParserNewPullParser;
                    map = map6;
                    i2 = length;
                    map2 = map4;
                    arrayList4 = arrayList9;
                    i = 1;
                    i3 = length2;
                    length2 = i3;
                    length = i2;
                }
                eventType = xmlPullParser.next();
                stack2 = stack;
                arrayList9 = arrayList4;
                sb2 = sb;
                i4 = i;
                arrayList8 = arrayList;
                arrayList7 = arrayList2;
                arrayList6 = arrayList3;
                enumMapNewEnumMap = enumMap;
                xmlPullParserNewPullParser = xmlPullParser;
                map6 = map;
                map4 = map2;
                subtitleParser = this;
            }
            arrayList = arrayList8;
            arrayList2 = arrayList7;
            stack = stack2;
            arrayList3 = arrayList6;
            sb = sb2;
            enumMap = enumMapNewEnumMap;
            xmlPullParser = xmlPullParserNewPullParser;
            i = i4;
            map = map6;
            i2 = length;
            map2 = map4;
            arrayList4 = arrayList9;
            i3 = length2;
            length2 = i3;
            length = i2;
            eventType = xmlPullParser.next();
            stack2 = stack;
            arrayList9 = arrayList4;
            sb2 = sb;
            i4 = i;
            arrayList8 = arrayList;
            arrayList7 = arrayList2;
            arrayList6 = arrayList3;
            enumMapNewEnumMap = enumMap;
            xmlPullParserNewPullParser = xmlPullParser;
            map6 = map;
            map4 = map2;
            subtitleParser = this;
        }
        HashMap map9 = map4;
        ArrayList arrayList10 = arrayList9;
        Collections.sort(arrayList10, new StartTimeComparator(null));
        return new SubtitleCollection(ImmutableList.copyOf((Collection) arrayList10), map9, map6, str, str2, z, str3, str4, subtitleShowBackgroundStatusFindMatch);
    }

    public SubtitleAnnotationTextPosition getAnnotationPosition(Map<SubtitleAttribute, String> map, Map<String, SubtitleRegionElement> map2, Stack<SubtitleTextStyle> stack, Map<String, SubtitleStyleElement> map3) {
        SubtitleRegionWritingMode writingMode = getWritingMode(map, map2);
        SubtitleRubyPosition rubyPosition = getRubyPosition(stack, map3);
        switch (AnonymousClass1.$SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[writingMode.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
                if (rubyPosition == SubtitleRubyPosition.BEFORE) {
                    return SubtitleAnnotationTextPosition.OVER;
                }
                if (rubyPosition == SubtitleRubyPosition.AFTER) {
                    return SubtitleAnnotationTextPosition.UNDER;
                }
                break;
            case 5:
            case 6:
                if (rubyPosition == SubtitleRubyPosition.BEFORE) {
                    return SubtitleAnnotationTextPosition.LEFT;
                }
                if (rubyPosition == SubtitleRubyPosition.AFTER) {
                    return SubtitleAnnotationTextPosition.RIGHT;
                }
                break;
            case 7:
                if (rubyPosition == SubtitleRubyPosition.BEFORE) {
                    return SubtitleAnnotationTextPosition.RIGHT;
                }
                if (rubyPosition == SubtitleRubyPosition.AFTER) {
                    return SubtitleAnnotationTextPosition.LEFT;
                }
                break;
        }
        return SubtitleAnnotationTextPosition.OVER;
    }

    /* JADX INFO: renamed from: com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextStylingType;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag;

        static {
            int[] iArr = new int[SubtitleRegionWritingMode.values().length];
            $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode = iArr;
            try {
                iArr[SubtitleRegionWritingMode.LRTB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.RLTB.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.LR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.RL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.TBLR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.TB.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleRegionWritingMode[SubtitleRegionWritingMode.TBRL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[SubtitleTextStylingType.values().length];
            $SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextStylingType = iArr2;
            try {
                iArr2[SubtitleTextStylingType.BASE.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$avod$core$subtitle$SubtitleTextStylingType[SubtitleTextStylingType.TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            int[] iArr3 = new int[SubtitleTag.values().length];
            $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag = iArr3;
            try {
                iArr3[SubtitleTag.TT.ordinal()] = 1;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.BODY.ordinal()] = 2;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.P.ordinal()] = 3;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.STYLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.REGION.ordinal()] = 5;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.INITIAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.SPAN.ordinal()] = 7;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.BR.ordinal()] = 8;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.UNRECOGNIZED.ordinal()] = 9;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.LAYOUT.ordinal()] = 10;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.STYLING.ordinal()] = 11;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.DIV.ordinal()] = 12;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.HEAD.ordinal()] = 13;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.INFORMATION.ordinal()] = 14;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$avod$playbackclient$subtitle$internal$SubtitleTag[SubtitleTag.METADATA.ordinal()] = 15;
            } catch (NoSuchFieldError unused24) {
            }
        }
    }

    private SubtitleRegionWritingMode getWritingMode(Map<SubtitleAttribute, String> map, Map<String, SubtitleRegionElement> map2) {
        SubtitleRegionElement subtitleRegionElement;
        SubtitleRegionWritingMode subtitleRegionWritingMode = SubtitleRegionWritingMode.LRTB;
        String str = map.get(SubtitleAttribute.REGION);
        return (str == null || (subtitleRegionElement = map2.get(str)) == null) ? subtitleRegionWritingMode : subtitleRegionElement.getWriteMode();
    }

    private SubtitleRubyPosition getRubyPosition(Stack<SubtitleTextStyle> stack, Map<String, SubtitleStyleElement> map) {
        SubtitleStyleElement subtitleStyleElement;
        SubtitleRubyPosition subtitleRubyPosition = SubtitleRubyPosition.UNRECOGNIZED;
        return (stack.size() <= 0 || (subtitleStyleElement = map.get(stack.peek().getStyleId())) == null) ? subtitleRubyPosition : subtitleStyleElement.getRubyPosition();
    }

    private SubtitleTextStylingType getTextStyleType(Stack<SubtitleTextStyle> stack, Map<String, SubtitleStyleElement> map) {
        if (stack.size() > 0) {
            SubtitleStyleElement subtitleStyleElement = map.get(stack.peek().getStyleId());
            if (subtitleStyleElement != null) {
                SubtitleTextStylingType ruby = subtitleStyleElement.getRuby();
                SubtitleTextStylingType subtitleTextStylingType = SubtitleTextStylingType.TEXT;
                if (ruby == subtitleTextStylingType) {
                    return subtitleTextStylingType;
                }
            }
            if (subtitleStyleElement != null) {
                SubtitleTextStylingType ruby2 = subtitleStyleElement.getRuby();
                SubtitleTextStylingType subtitleTextStylingType2 = SubtitleTextStylingType.BASE;
                if (ruby2 == subtitleTextStylingType2) {
                    return subtitleTextStylingType2;
                }
            }
        }
        return SubtitleTextStylingType.NONE;
    }

    @SuppressWarnings(justification = "We currently don't need to serialize SubtitleTextElement, so we don't care whether the comparator is serializable or not", value = {"SE_COMPARATOR_SHOULD_BE_SERIALIZABLE"})
    private static class StartTimeComparator implements Comparator<SubtitleTextElement> {
        private StartTimeComparator() {
        }

        /* synthetic */ StartTimeComparator(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.util.Comparator
        public int compare(SubtitleTextElement subtitleTextElement, SubtitleTextElement subtitleTextElement2) {
            return ComparisonChain.start().compare(subtitleTextElement.getBeginTimeStamp(), subtitleTextElement2.getBeginTimeStamp()).compare(subtitleTextElement.getEndTimeStamp(), subtitleTextElement2.getEndTimeStamp()).result();
        }
    }

    private Map<SubtitleAttribute, String> getAttributes(XmlPullParser xmlPullParser) throws NoSuchFieldException {
        int attributeCount = xmlPullParser.getAttributeCount();
        if (attributeCount == -1) {
            throw new NoSuchFieldException("Required entity attributes missing");
        }
        EnumMap enumMap = new EnumMap(SubtitleAttribute.class);
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            SubtitleAttribute subtitleAttributeFindMatch = SubtitleAttribute.findMatch(attributeName);
            if (subtitleAttributeFindMatch == SubtitleAttribute.UNRECOGNIZED) {
                DLog.devf("Unrecognized attribute [%s] with value [%s]", attributeName, attributeValue);
            } else {
                enumMap.put(subtitleAttributeFindMatch, attributeValue);
            }
        }
        return enumMap;
    }

    private void skipUnsupportedTag(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i = 1;
        while (i != 0) {
            int next = xmlPullParser.next();
            if (next == 3) {
                i--;
            } else if (next == 2) {
                i++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"RtlHardcoded"})
    public static int getGravityForTextAlign(@Nonnull Map<SubtitleAttribute, String> map) {
        ImmutableMap immutableMapOf = ImmutableMap.of("left", (Integer) 3, "right", (Integer) 5, "center", (Integer) 1, AsmString.METHOD_FIREBASE_PERF_TRACE_START, (Integer) 8388611, "end", Integer.valueOf(GravityCompat.END));
        SubtitleAttribute subtitleAttribute = SubtitleAttribute.TEXT_ALIGN;
        if (!map.containsKey(subtitleAttribute)) {
            return 1;
        }
        String lowerCase = map.get(subtitleAttribute).toLowerCase(Locale.US);
        if (immutableMapOf.containsKey(lowerCase)) {
            return ((Integer) immutableMapOf.get(lowerCase)).intValue();
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getGravityForDisplayAlign(@Nonnull Map<SubtitleAttribute, String> map) {
        ImmutableMap immutableMapOf = ImmutableMap.of("before", 48, "center", 16, "after", 80);
        SubtitleAttribute subtitleAttribute = SubtitleAttribute.DISPLAY_ALIGN;
        if (!map.containsKey(subtitleAttribute)) {
            return 48;
        }
        String lowerCase = map.get(subtitleAttribute).toLowerCase(Locale.US);
        if (immutableMapOf.containsKey(lowerCase)) {
            return ((Integer) immutableMapOf.get(lowerCase)).intValue();
        }
        return 48;
    }

    static abstract class TextElementParser {
        abstract SubtitleFormat getFormat();

        TextElementParser() {
        }

        boolean hasValidAttributes(@Nonnull Map<SubtitleAttribute, String> map) {
            SubtitleAttribute subtitleAttribute = SubtitleAttribute.BEGIN;
            if (map.get(subtitleAttribute) == null) {
                return false;
            }
            SubtitleAttribute subtitleAttribute2 = SubtitleAttribute.END;
            if (map.get(subtitleAttribute2) == null) {
                return false;
            }
            long time = SubtitleParser.parseTime(map.get(subtitleAttribute));
            long time2 = SubtitleParser.parseTime(map.get(subtitleAttribute2));
            return (time == -1 || time2 == -1 || time >= time2) ? false : true;
        }

        @Nonnull
        SubtitleTextElement parse(@Nonnull Map<SubtitleAttribute, String> map, @Nonnull String str, long j, long j2, List<SubtitleTextStyle> list, List<SubtitleAnnotationElement> list2, @Nonnull Map<String, SubtitleRegionElement> map2, @Nonnull List<String> list3, @Nonnull Map<String, Map<SubtitleAttribute, String>> map3, @Nonnull StyleElementParser styleElementParser) {
            long j3;
            long j4;
            Preconditions.checkNotNull(map, "attributeMap");
            Preconditions.checkNotNull(str, "subtitleText");
            Preconditions.checkNotNull(map2, "regionMap");
            Preconditions.checkNotNull(list3, "subtitleStyleIdList");
            Preconditions.checkNotNull(map3, "styleIdToAttributesMap");
            Preconditions.checkNotNull(styleElementParser, "styleElementParser");
            long time = SubtitleParser.parseTime(map.get(SubtitleAttribute.BEGIN));
            long time2 = SubtitleParser.parseTime(map.get(SubtitleAttribute.END));
            if (j2 != -1) {
                j3 = j2 + j;
                j4 = (time2 - time) + j3;
            } else {
                j3 = time + j;
                j4 = time2 + j;
            }
            DLog.devf("SubtitleParser - adjusted begin [%d], adjusted end [%d]", Long.valueOf(j3), Long.valueOf(j4));
            String str2 = map.get(SubtitleAttribute.REGION);
            HashMap map4 = new HashMap();
            Iterator<String> it = list3.iterator();
            while (it.hasNext()) {
                Map<SubtitleAttribute, String> map5 = map3.get(it.next());
                if (map5 != null) {
                    map4.putAll(map5);
                }
            }
            return SubtitleTextElement.newBuilder().setFormat(getFormat()).setSubtitleText(str).setBeginTime(j3).setEndTime(j4).setRegion(map2.get(str2)).setStyle(list3.isEmpty() ? null : styleElementParser.parse(map4)).setTextStyles(new ArrayList(list)).setAnnotationElements(new ArrayList(list2)).build();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long parseTime(String str) {
        try {
            return DurationUtils.makeTimeInMillisFromString("HH:mm:ss.SSS", str);
        } catch (ParseException e) {
            DLog.exceptionf(e, "Cannot parse subtitle time %s", str);
            return -1L;
        }
    }

    static class TextElementParserV1 extends TextElementParser {
        TextElementParserV1() {
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser.TextElementParser
        public SubtitleFormat getFormat() {
            return SubtitleFormat.DFXP;
        }
    }

    static class TextElementParserV2 extends TextElementParser {
        TextElementParserV2() {
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser.TextElementParser
        public SubtitleFormat getFormat() {
            return SubtitleFormat.TTMLv2;
        }
    }

    static class StyleElementParserV1 implements StyleElementParser {
        StyleElementParserV1() {
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser.StyleElementParser
        public boolean hasValidAttributes(@Nonnull Map<SubtitleAttribute, String> map) {
            return map.get(SubtitleAttribute.ID) != null;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser.StyleElementParser
        @Nonnull
        public SubtitleStyleElement parse(@Nonnull Map<SubtitleAttribute, String> map) {
            SubtitleStyleElement.StyleElementV1.Builder color = SubtitleStyleElement.StyleElementV1.newBuilder().setId(map.get(SubtitleAttribute.ID)).setFontFamily(map.get(SubtitleAttribute.FONT_FAMILY)).setFontWeight(map.get(SubtitleAttribute.FONT_WEIGHT)).setFontStyle(map.get(SubtitleAttribute.FONT_STYLE)).setFontSize(map.get(SubtitleAttribute.FONT_SIZE)).setColor(map.get(SubtitleAttribute.COLOR));
            String str = map.get(SubtitleAttribute.ORIGIN);
            if (Dimension.isValid(str)) {
                color.setOrigin(Dimension.from(str));
            }
            String str2 = map.get(SubtitleAttribute.EXTENT);
            if (Dimension.isValid(str2)) {
                color.setExtent(Dimension.from(str2));
            }
            String str3 = map.get(SubtitleAttribute.OVERFLOW);
            if (Overflow.isValid(str3)) {
                color.setOverflow(Overflow.lookup(str3));
            }
            color.setTextAlignGravity(SubtitleParser.getGravityForTextAlign(map));
            color.setDisplayAlignGravity(SubtitleParser.getGravityForDisplayAlign(map));
            return color.build();
        }
    }

    static class StyleElementParserV2 implements StyleElementParser {
        private final SubtitleConfig mSubtitleConfig;

        StyleElementParserV2(@Nonnull SubtitleConfig subtitleConfig) {
            this.mSubtitleConfig = (SubtitleConfig) Preconditions.checkNotNull(subtitleConfig, "subtitleConfig");
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser.StyleElementParser
        public boolean hasValidAttributes(@Nonnull Map<SubtitleAttribute, String> map) {
            return map.get(SubtitleAttribute.ID) != null;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser.StyleElementParser
        @Nonnull
        public SubtitleStyleElement parse(@Nonnull Map<SubtitleAttribute, String> map) {
            SubtitleStyleElement.StyleElementV2.Builder fontShear = SubtitleStyleElement.StyleElementV2.newBuilder().setId(map.get(SubtitleAttribute.ID)).setFontFamily(map.get(SubtitleAttribute.FONT_FAMILY)).setFontWeight(map.get(SubtitleAttribute.FONT_WEIGHT)).setFontStyle(map.get(SubtitleAttribute.FONT_STYLE)).setFontSize(map.get(SubtitleAttribute.FONT_SIZE)).setColor(map.get(SubtitleAttribute.COLOR)).setFontShear(map.get(SubtitleAttribute.FONT_SHEAR));
            if (this.mSubtitleConfig.isTextCombineEnabled()) {
                fontShear.setTextCombine(map.get(SubtitleAttribute.TEXT_COMBINE));
            }
            if (this.mSubtitleConfig.isTextEmphasisEnabled()) {
                fontShear.setTextEmphasis(map.get(SubtitleAttribute.TEXT_EMPHASIS));
            }
            if (this.mSubtitleConfig.areAnnotationsEnabled()) {
                fontShear.setRuby(map.get(SubtitleAttribute.RUBY)).setRubyPosition(map.get(SubtitleAttribute.RUBY_POSITION));
            }
            if (this.mSubtitleConfig.isTextAlignEnabled()) {
                fontShear.setTextAlignment(map.get(SubtitleAttribute.TEXT_ALIGN));
            }
            return fontShear.build();
        }
    }

    static class RegionElementParserV1 implements RegionElementParser {
        RegionElementParserV1() {
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser.RegionElementParser
        public boolean hasValidAttributes(@Nonnull Map<SubtitleAttribute, String> map) {
            return (map.get(SubtitleAttribute.ID) == null || map.get(SubtitleAttribute.STYLE) == null) ? false : true;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser.RegionElementParser
        @Nonnull
        public SubtitleRegionElement parse(@Nonnull Map<SubtitleAttribute, String> map) {
            return SubtitleRegionElement.RegionElementV1.newBuilder().setId(map.get(SubtitleAttribute.ID)).setStyleId(map.get(SubtitleAttribute.STYLE)).build();
        }
    }

    static class RegionElementParserV2 implements RegionElementParser {
        private final SubtitleConfig mSubtitleConfig;

        RegionElementParserV2(@Nonnull SubtitleConfig subtitleConfig) {
            this.mSubtitleConfig = (SubtitleConfig) Preconditions.checkNotNull(subtitleConfig, "subtitleConfig");
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser.RegionElementParser
        public boolean hasValidAttributes(@Nonnull Map<SubtitleAttribute, String> map) {
            return map.get(SubtitleAttribute.ID) != null;
        }

        @Override // com.amazon.avod.playbackclient.subtitle.internal.SubtitleParser.RegionElementParser
        @Nonnull
        public SubtitleRegionElement parse(@Nonnull Map<SubtitleAttribute, String> map) {
            SubtitleRegionElement.RegionElementV2.Builder id = SubtitleRegionElement.RegionElementV2.newBuilder().setId(map.get(SubtitleAttribute.ID));
            String str = map.get(SubtitleAttribute.ORIGIN);
            if (Dimension.isValid(str)) {
                id.setOrigin(Dimension.from(str));
            }
            String str2 = map.get(SubtitleAttribute.EXTENT);
            if (Dimension.isValid(str2)) {
                id.setExtent(Dimension.from(str2));
            }
            String str3 = map.get(SubtitleAttribute.OVERFLOW);
            if (Overflow.isValid(str3)) {
                id.setOverflow(Overflow.lookup(str3));
            }
            id.setTextAlignGravity(SubtitleParser.getGravityForTextAlign(map));
            id.setDisplayAlignGravity(SubtitleParser.getGravityForDisplayAlign(map));
            id.setDisplayAlignment(map.get(SubtitleAttribute.DISPLAY_ALIGN));
            id.setPosition(map.get(SubtitleAttribute.POSITION));
            if (this.mSubtitleConfig.areVerticalSubtitlesEnabled()) {
                id.setWriteMode(map.get(SubtitleAttribute.WRITING_MODE));
            }
            id.setTextAlignment(map.get(SubtitleAttribute.TEXT_ALIGN));
            return id.build();
        }
    }
}
