package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Logger;
import com.google.android.exoplayer2.util.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes6.dex */
public final class HlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_CODECS = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128)");
    private static final Pattern REGEX_URI = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern REGEX_IV = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern REGEX_NAME = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern REGEX_AUTOSELECT = compileBooleanAttrPattern("AUTOSELECT");
    private static final Pattern REGEX_DEFAULT = compileBooleanAttrPattern("DEFAULT");
    private static final Pattern REGEX_FORCED = compileBooleanAttrPattern("FORCED");
    private static final Logger log = new Logger(Logger.Module.Source, HlsPlaylistParser.class.getSimpleName());

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.android.exoplayer2.upstream.ParsingLoadable.Parser
    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        String strTrim;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        LinkedList linkedList = new LinkedList();
        Logger logger = log;
        if (logger.allowVerbose()) {
            logger.v("------------------");
        }
        try {
            if (!checkPlaylistHeader(bufferedReader)) {
                throw new UnrecognizedInputFormatException("Input does not start with the #EXTM3U header.", uri);
            }
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    Logger logger2 = log;
                    if (logger2.allowVerbose()) {
                        logger2.v("------------------");
                    }
                    Util.closeQuietly(bufferedReader);
                    throw new ParserException("Failed to parse the playlist, could not identify any tags.");
                }
                strTrim = line.trim();
                if (!strTrim.isEmpty()) {
                    if (!strTrim.startsWith("#EXT-X-STREAM-INF")) {
                        if (strTrim.startsWith("#EXT-X-TARGETDURATION") || strTrim.startsWith("#EXT-X-MEDIA-SEQUENCE") || strTrim.startsWith("#EXTINF") || strTrim.startsWith("#EXT-X-KEY") || strTrim.startsWith("#EXT-X-BYTERANGE") || strTrim.equals("#EXT-X-DISCONTINUITY") || strTrim.equals("#EXT-X-DISCONTINUITY-SEQUENCE") || strTrim.equals("#EXT-X-ENDLIST")) {
                            break;
                        }
                        linkedList.add(strTrim);
                    } else {
                        linkedList.add(strTrim);
                        return parseMasterPlaylist(new LineIterator(linkedList, bufferedReader), uri.toString());
                    }
                }
            }
            linkedList.add(strTrim);
            HlsMediaPlaylist mediaPlaylist = parseMediaPlaylist(new LineIterator(linkedList, bufferedReader), uri.toString());
            Logger logger3 = log;
            if (logger3.allowVerbose()) {
                logger3.v("------------------");
            }
            Util.closeQuietly(bufferedReader);
            return mediaPlaylist;
        } finally {
            Logger logger4 = log;
            if (logger4.allowVerbose()) {
                logger4.v("------------------");
            }
            Util.closeQuietly(bufferedReader);
        }
    }

    private static boolean checkPlaylistHeader(BufferedReader bufferedReader) throws IOException {
        int i = bufferedReader.read();
        if (i == 239) {
            if (bufferedReader.read() != 187 || bufferedReader.read() != 191) {
                return false;
            }
            i = bufferedReader.read();
        }
        int iSkipIgnorableWhitespace = skipIgnorableWhitespace(bufferedReader, true, i);
        for (int i2 = 0; i2 < 7; i2++) {
            if (iSkipIgnorableWhitespace != "#EXTM3U".charAt(i2)) {
                return false;
            }
            iSkipIgnorableWhitespace = bufferedReader.read();
        }
        return Util.isLinebreak(skipIgnorableWhitespace(bufferedReader, false, iSkipIgnorableWhitespace));
    }

    private static int skipIgnorableWhitespace(BufferedReader bufferedReader, boolean z, int i) throws IOException {
        while (i != -1 && Character.isWhitespace(i) && (z || !Util.isLinebreak(i))) {
            i = bufferedReader.read();
        }
        return i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist parseMasterPlaylist(com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.LineIterator r34, java.lang.String r35) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 454
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.parseMasterPlaylist(com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser$LineIterator, java.lang.String):com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist");
    }

    private static int parseSelectionFlags(String str) {
        return (parseBooleanAttribute(str, REGEX_DEFAULT, false) ? 1 : 0) | (parseBooleanAttribute(str, REGEX_FORCED, false) ? 2 : 0) | (parseBooleanAttribute(str, REGEX_AUTOSELECT, false) ? 4 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static HlsMediaPlaylist parseMediaPlaylist(LineIterator lineIterator, String str) throws IOException {
        long j;
        long j2;
        String hexString;
        long j3;
        long j4;
        int i;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean zAllowVerbose = log.allowVerbose();
        char c = 0;
        int i2 = 1;
        long doubleAttr = -9223372036854775807L;
        long intAttr = -9223372036854775807L;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        boolean z = false;
        boolean z2 = false;
        int intAttr2 = 0;
        boolean zEquals = false;
        int intAttr3 = 1;
        long j5 = 0;
        long jMsToUs = 0;
        HlsMediaPlaylist.Segment segment = null;
        long j6 = 0;
        long j7 = -1;
        String optionalStringAttr = null;
        long doubleAttr2 = 0;
        loop0: while (true) {
            String str2 = null;
            while (lineIterator.hasNext()) {
                String next = lineIterator.next();
                if (zAllowVerbose) {
                    log.v(next);
                }
                if (next.startsWith("#EXT")) {
                    arrayList2.add(next);
                }
                if (next.startsWith("#EXT-X-PLAYLIST-TYPE")) {
                    String stringAttr = parseStringAttr(next, REGEX_PLAYLIST_TYPE);
                    if ("VOD".equals(stringAttr)) {
                        i3 = i2;
                    } else if ("EVENT".equals(stringAttr)) {
                        i3 = 2;
                    }
                } else if (next.startsWith("#EXT-X-START")) {
                    doubleAttr = (long) (parseDoubleAttr(next, REGEX_TIME_OFFSET) * 1000000.0d);
                } else if (next.startsWith("#EXT-X-MAP")) {
                    String stringAttr2 = parseStringAttr(next, REGEX_URI);
                    String optionalStringAttr2 = parseOptionalStringAttr(next, REGEX_ATTR_BYTERANGE);
                    if (optionalStringAttr2 != null) {
                        String[] strArrSplit = optionalStringAttr2.split("@");
                        j7 = Long.parseLong(strArrSplit[c]);
                        if (strArrSplit.length > i2) {
                            j6 = Long.parseLong(strArrSplit[i2]);
                        }
                    }
                    segment = new HlsMediaPlaylist.Segment(stringAttr2, j6, j7);
                    j6 = 0;
                    j7 = -1;
                } else if (next.startsWith("#EXT-X-TARGETDURATION")) {
                    intAttr = 1000000 * ((long) parseIntAttr(next, REGEX_TARGET_DURATION));
                } else if (next.startsWith("#EXT-X-MEDIA-SEQUENCE")) {
                    intAttr2 = parseIntAttr(next, REGEX_MEDIA_SEQUENCE);
                    i7 = intAttr2;
                } else if (next.startsWith("#EXT-X-VERSION")) {
                    intAttr3 = parseIntAttr(next, REGEX_VERSION);
                } else if (next.startsWith("#EXTINF")) {
                    doubleAttr2 = (long) (parseDoubleAttr(next, REGEX_MEDIA_DURATION) * 1000000.0d);
                } else if (next.startsWith("#EXT-X-KEY")) {
                    zEquals = "AES-128".equals(parseStringAttr(next, REGEX_METHOD));
                    if (zEquals) {
                        String stringAttr3 = parseStringAttr(next, REGEX_URI);
                        optionalStringAttr = parseOptionalStringAttr(next, REGEX_IV);
                        str2 = stringAttr3;
                    }
                } else if (next.startsWith("#EXT-X-BYTERANGE")) {
                    String[] strArrSplit2 = parseStringAttr(next, REGEX_BYTERANGE).split("@");
                    j7 = Long.parseLong(strArrSplit2[c]);
                    if (strArrSplit2.length > i2) {
                        j6 = Long.parseLong(strArrSplit2[i2]);
                    }
                } else if (next.startsWith("#EXT-X-DISCONTINUITY-SEQUENCE")) {
                    i6 = Integer.parseInt(next.substring(next.indexOf(58) + i2));
                    i5 = i2;
                } else if (next.equals("#EXT-X-DISCONTINUITY")) {
                    i4++;
                } else if (next.startsWith("#EXT-X-PROGRAM-DATE-TIME")) {
                    if (jMsToUs == 0) {
                        jMsToUs = C.msToUs(Util.parseXsDateTime(next.substring(next.indexOf(58) + i2))) - j5;
                    } else {
                        j = doubleAttr;
                        j2 = doubleAttr2;
                        doubleAttr2 = j2;
                        doubleAttr = j;
                        c = 0;
                        i2 = 1;
                    }
                } else if (!next.startsWith("#")) {
                    if (zEquals) {
                        hexString = optionalStringAttr != null ? optionalStringAttr : Integer.toHexString(intAttr2);
                    } else {
                        hexString = null;
                    }
                    int i8 = intAttr2 + 1;
                    if (j7 == -1) {
                        j6 = 0;
                    }
                    if (zAllowVerbose) {
                        Logger logger = log;
                        StringBuilder sb = new StringBuilder();
                        j3 = doubleAttr;
                        sb.append("Adding Segment : index = ");
                        sb.append(arrayList.size());
                        sb.append(" segmentMediaSequence = ");
                        sb.append(i8 - 1);
                        sb.append(" segmentStartTimeUs = ");
                        sb.append(j5);
                        sb.append(" segmentDurationUs = ");
                        j4 = doubleAttr2;
                        sb.append(j4);
                        i = i8;
                        sb.append(" relativeDiscontinuitySequence = ");
                        sb.append(i4);
                        logger.v(sb.toString());
                    } else {
                        j3 = doubleAttr;
                        j4 = doubleAttr2;
                        i = i8;
                    }
                    arrayList.add(new HlsMediaPlaylist.Segment(next, j4, i4, j5, zEquals, str2, hexString, j6, j7));
                    j5 += j4;
                    if (j7 != -1) {
                        j6 += j7;
                    }
                    j7 = -1;
                    intAttr2 = i;
                    doubleAttr = j3;
                    c = 0;
                    i2 = 1;
                    doubleAttr2 = 0;
                } else {
                    j = doubleAttr;
                    j2 = doubleAttr2;
                    if (next.equals("#EXT-X-INDEPENDENT-SEGMENTS")) {
                        doubleAttr2 = j2;
                        doubleAttr = j;
                        c = 0;
                        i2 = 1;
                        z = true;
                    } else if (next.equals("#EXT-X-ENDLIST")) {
                        doubleAttr2 = j2;
                        doubleAttr = j;
                        c = 0;
                        i2 = 1;
                        z2 = true;
                    } else {
                        doubleAttr2 = j2;
                        doubleAttr = j;
                        c = 0;
                        i2 = 1;
                    }
                }
            }
            optionalStringAttr = null;
        }
        long j8 = doubleAttr;
        if (zAllowVerbose) {
            log.v("Segment Count = " + arrayList.size());
        }
        return new HlsMediaPlaylist(i3, str, arrayList2, j8, jMsToUs, i5, i6, i7, intAttr3, intAttr, z, z2, jMsToUs != 0, segment, arrayList);
    }

    private static int parseIntAttr(String str, Pattern pattern) throws ParserException {
        return Integer.parseInt(parseStringAttr(str, pattern));
    }

    private static double parseDoubleAttr(String str, Pattern pattern) throws ParserException {
        return Double.parseDouble(parseStringAttr(str, pattern));
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String parseStringAttr(String str, Pattern pattern) throws ParserException {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find() && matcher.groupCount() == 1) {
            return matcher.group(1);
        }
        throw new ParserException("Couldn't match " + pattern.pattern() + " in " + str);
    }

    private static boolean parseBooleanAttribute(String str, Pattern pattern, boolean z) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1).equals("YES") : z;
    }

    private static Pattern compileBooleanAttrPattern(String str) {
        return Pattern.compile(str + "=(NO|YES)");
    }

    private static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> queue, BufferedReader bufferedReader) {
            this.extraLines = queue;
            this.reader = bufferedReader;
        }

        public boolean hasNext() throws IOException {
            String strTrim;
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = this.extraLines.poll();
                return true;
            }
            do {
                String line = this.reader.readLine();
                this.next = line;
                if (line == null) {
                    return false;
                }
                strTrim = line.trim();
                this.next = strTrim;
            } while (strTrim.isEmpty());
            return true;
        }

        public String next() throws IOException {
            if (!hasNext()) {
                return null;
            }
            String str = this.next;
            this.next = null;
            return str;
        }
    }
}
