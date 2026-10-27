package com.google.android.exoplayer2.extractor.mp4;

import android.util.Log;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.google.android.exoplayer2.metadata.id3.Id3Frame;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;

/* JADX INFO: loaded from: classes6.dex */
final class MetadataUtil {
    private static final int SHORT_TYPE_NAME_1 = Util.getIntegerCodeForString("nam");
    private static final int SHORT_TYPE_NAME_2 = Util.getIntegerCodeForString("trk");
    private static final int SHORT_TYPE_COMMENT = Util.getIntegerCodeForString("cmt");
    private static final int SHORT_TYPE_YEAR = Util.getIntegerCodeForString("day");
    private static final int SHORT_TYPE_ARTIST = Util.getIntegerCodeForString("ART");
    private static final int SHORT_TYPE_ENCODER = Util.getIntegerCodeForString("too");
    private static final int SHORT_TYPE_ALBUM = Util.getIntegerCodeForString("alb");
    private static final int SHORT_TYPE_COMPOSER_1 = Util.getIntegerCodeForString("com");
    private static final int SHORT_TYPE_COMPOSER_2 = Util.getIntegerCodeForString("wrt");
    private static final int SHORT_TYPE_LYRICS = Util.getIntegerCodeForString("lyr");
    private static final int SHORT_TYPE_GENRE = Util.getIntegerCodeForString("gen");
    private static final int TYPE_COVER_ART = Util.getIntegerCodeForString("covr");
    private static final int TYPE_GENRE = Util.getIntegerCodeForString("gnre");
    private static final int TYPE_GROUPING = Util.getIntegerCodeForString("grp");
    private static final int TYPE_DISK_NUMBER = Util.getIntegerCodeForString("disk");
    private static final int TYPE_TRACK_NUMBER = Util.getIntegerCodeForString("trkn");
    private static final int TYPE_TEMPO = Util.getIntegerCodeForString("tmpo");
    private static final int TYPE_COMPILATION = Util.getIntegerCodeForString("cpil");
    private static final int TYPE_ALBUM_ARTIST = Util.getIntegerCodeForString("aART");
    private static final int TYPE_SORT_TRACK_NAME = Util.getIntegerCodeForString("sonm");
    private static final int TYPE_SORT_ALBUM = Util.getIntegerCodeForString("soal");
    private static final int TYPE_SORT_ARTIST = Util.getIntegerCodeForString("soar");
    private static final int TYPE_SORT_ALBUM_ARTIST = Util.getIntegerCodeForString("soaa");
    private static final int TYPE_SORT_COMPOSER = Util.getIntegerCodeForString("soco");
    private static final int TYPE_RATING = Util.getIntegerCodeForString("rtng");
    private static final int TYPE_GAPLESS_ALBUM = Util.getIntegerCodeForString("pgap");
    private static final int TYPE_TV_SORT_SHOW = Util.getIntegerCodeForString("sosn");
    private static final int TYPE_TV_SHOW = Util.getIntegerCodeForString("tvsh");
    private static final int TYPE_INTERNAL = Util.getIntegerCodeForString("----");
    private static final String[] STANDARD_GENRES = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};

    private MetadataUtil() {
    }

    public static Metadata.Entry parseIlstElement(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition() + parsableByteArray.readInt();
        int i = parsableByteArray.readInt();
        int i2 = (i >> 24) & 255;
        try {
            if (i2 == 169 || i2 == 65533) {
                int i3 = 16777215 & i;
                if (i3 == SHORT_TYPE_COMMENT) {
                    return parseCommentAttribute(i, parsableByteArray);
                }
                if (i3 != SHORT_TYPE_NAME_1 && i3 != SHORT_TYPE_NAME_2) {
                    if (i3 != SHORT_TYPE_COMPOSER_1 && i3 != SHORT_TYPE_COMPOSER_2) {
                        if (i3 == SHORT_TYPE_YEAR) {
                            return parseTextAttribute(i, "TDRC", parsableByteArray);
                        }
                        if (i3 == SHORT_TYPE_ARTIST) {
                            return parseTextAttribute(i, "TPE1", parsableByteArray);
                        }
                        if (i3 == SHORT_TYPE_ENCODER) {
                            return parseTextAttribute(i, "TSSE", parsableByteArray);
                        }
                        if (i3 == SHORT_TYPE_ALBUM) {
                            return parseTextAttribute(i, "TALB", parsableByteArray);
                        }
                        if (i3 == SHORT_TYPE_LYRICS) {
                            return parseTextAttribute(i, "USLT", parsableByteArray);
                        }
                        if (i3 == SHORT_TYPE_GENRE) {
                            return parseTextAttribute(i, "TCON", parsableByteArray);
                        }
                        if (i3 == TYPE_GROUPING) {
                            return parseTextAttribute(i, "TIT1", parsableByteArray);
                        }
                    }
                    return parseTextAttribute(i, "TCOM", parsableByteArray);
                }
                return parseTextAttribute(i, "TIT2", parsableByteArray);
            }
            if (i == TYPE_GENRE) {
                return parseStandardGenreAttribute(parsableByteArray);
            }
            if (i == TYPE_DISK_NUMBER) {
                return parseIndexAndCountAttribute(i, "TPOS", parsableByteArray);
            }
            if (i == TYPE_TRACK_NUMBER) {
                return parseIndexAndCountAttribute(i, "TRCK", parsableByteArray);
            }
            if (i == TYPE_TEMPO) {
                return parseUint8Attribute(i, "TBPM", parsableByteArray, true, false);
            }
            if (i == TYPE_COMPILATION) {
                return parseUint8Attribute(i, "TCMP", parsableByteArray, true, true);
            }
            if (i == TYPE_COVER_ART) {
                return parseCoverArt(parsableByteArray);
            }
            if (i == TYPE_ALBUM_ARTIST) {
                return parseTextAttribute(i, "TPE2", parsableByteArray);
            }
            if (i == TYPE_SORT_TRACK_NAME) {
                return parseTextAttribute(i, "TSOT", parsableByteArray);
            }
            if (i == TYPE_SORT_ALBUM) {
                return parseTextAttribute(i, "TSO2", parsableByteArray);
            }
            if (i == TYPE_SORT_ARTIST) {
                return parseTextAttribute(i, "TSOA", parsableByteArray);
            }
            if (i == TYPE_SORT_ALBUM_ARTIST) {
                return parseTextAttribute(i, "TSOP", parsableByteArray);
            }
            if (i == TYPE_SORT_COMPOSER) {
                return parseTextAttribute(i, "TSOC", parsableByteArray);
            }
            if (i == TYPE_RATING) {
                return parseUint8Attribute(i, "ITUNESADVISORY", parsableByteArray, false, false);
            }
            if (i == TYPE_GAPLESS_ALBUM) {
                return parseUint8Attribute(i, "ITUNESGAPLESS", parsableByteArray, false, true);
            }
            if (i == TYPE_TV_SORT_SHOW) {
                return parseTextAttribute(i, "TVSHOWSORT", parsableByteArray);
            }
            if (i == TYPE_TV_SHOW) {
                return parseTextAttribute(i, "TVSHOW", parsableByteArray);
            }
            if (i == TYPE_INTERNAL) {
                return parseInternalAttribute(parsableByteArray, position);
            }
            Log.d("MetadataUtil", "Skipped unknown metadata entry: " + Atom.getAtomTypeString(i));
            return null;
        } finally {
            parsableByteArray.setPosition(position);
        }
    }

    private static TextInformationFrame parseTextAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int i2 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            return new TextInformationFrame(str, null, parsableByteArray.readNullTerminatedString(i2 - 16));
        }
        Log.w("MetadataUtil", "Failed to parse text attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    private static CommentFrame parseCommentAttribute(int i, ParsableByteArray parsableByteArray) {
        int i2 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            String nullTerminatedString = parsableByteArray.readNullTerminatedString(i2 - 16);
            return new CommentFrame("und", nullTerminatedString, nullTerminatedString);
        }
        Log.w("MetadataUtil", "Failed to parse comment attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    private static Id3Frame parseUint8Attribute(int i, String str, ParsableByteArray parsableByteArray, boolean z, boolean z2) {
        int uint8AttributeValue = parseUint8AttributeValue(parsableByteArray);
        if (z2) {
            uint8AttributeValue = Math.min(1, uint8AttributeValue);
        }
        if (uint8AttributeValue >= 0) {
            if (z) {
                return new TextInformationFrame(str, null, Integer.toString(uint8AttributeValue));
            }
            return new CommentFrame("und", str, Integer.toString(uint8AttributeValue));
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    private static TextInformationFrame parseIndexAndCountAttribute(int i, String str, ParsableByteArray parsableByteArray) {
        int i2 = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data && i2 >= 22) {
            parsableByteArray.skipBytes(10);
            int unsignedShort = parsableByteArray.readUnsignedShort();
            if (unsignedShort > 0) {
                String str2 = "" + unsignedShort;
                int unsignedShort2 = parsableByteArray.readUnsignedShort();
                if (unsignedShort2 > 0) {
                    str2 = str2 + "/" + unsignedShort2;
                }
                return new TextInformationFrame(str, null, str2);
            }
        }
        Log.w("MetadataUtil", "Failed to parse index/count attribute: " + Atom.getAtomTypeString(i));
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0011  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.google.android.exoplayer2.metadata.id3.TextInformationFrame parseStandardGenreAttribute(com.google.android.exoplayer2.util.ParsableByteArray r3) {
        /*
            int r3 = parseUint8AttributeValue(r3)
            r0 = 0
            if (r3 <= 0) goto L11
            java.lang.String[] r1 = com.google.android.exoplayer2.extractor.mp4.MetadataUtil.STANDARD_GENRES
            int r2 = r1.length
            if (r3 > r2) goto L11
            int r3 = r3 + (-1)
            r3 = r1[r3]
            goto L12
        L11:
            r3 = r0
        L12:
            if (r3 == 0) goto L1c
            com.google.android.exoplayer2.metadata.id3.TextInformationFrame r1 = new com.google.android.exoplayer2.metadata.id3.TextInformationFrame
            java.lang.String r2 = "TCON"
            r1.<init>(r2, r0, r3)
            return r1
        L1c:
            java.lang.String r3 = "MetadataUtil"
            java.lang.String r1 = "Failed to parse standard genre code"
            android.util.Log.w(r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.MetadataUtil.parseStandardGenreAttribute(com.google.android.exoplayer2.util.ParsableByteArray):com.google.android.exoplayer2.metadata.id3.TextInformationFrame");
    }

    private static ApicFrame parseCoverArt(ParsableByteArray parsableByteArray) {
        int i = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            int fullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
            String str = fullAtomFlags == 13 ? "image/jpeg" : fullAtomFlags == 14 ? "image/png" : null;
            if (str == null) {
                Log.w("MetadataUtil", "Unrecognized cover art flags: " + fullAtomFlags);
                return null;
            }
            parsableByteArray.skipBytes(4);
            int i2 = i - 16;
            byte[] bArr = new byte[i2];
            parsableByteArray.readBytes(bArr, 0, i2);
            return new ApicFrame(str, null, 3, bArr);
        }
        Log.w("MetadataUtil", "Failed to parse cover art attribute");
        return null;
    }

    private static Id3Frame parseInternalAttribute(ParsableByteArray parsableByteArray, int i) {
        int i2 = -1;
        int i3 = -1;
        String nullTerminatedString = null;
        String nullTerminatedString2 = null;
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int i4 = parsableByteArray.readInt();
            int i5 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            if (i5 == Atom.TYPE_mean) {
                nullTerminatedString = parsableByteArray.readNullTerminatedString(i4 - 12);
            } else if (i5 == Atom.TYPE_name) {
                nullTerminatedString2 = parsableByteArray.readNullTerminatedString(i4 - 12);
            } else {
                if (i5 == Atom.TYPE_data) {
                    i2 = position;
                    i3 = i4;
                }
                parsableByteArray.skipBytes(i4 - 12);
            }
        }
        if (!"com.apple.iTunes".equals(nullTerminatedString) || !"iTunSMPB".equals(nullTerminatedString2) || i2 == -1) {
            return null;
        }
        parsableByteArray.setPosition(i2);
        parsableByteArray.skipBytes(16);
        return new CommentFrame("und", nullTerminatedString2, parsableByteArray.readNullTerminatedString(i3 - 16));
    }

    private static int parseUint8AttributeValue(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() == Atom.TYPE_data) {
            parsableByteArray.skipBytes(8);
            return parsableByteArray.readUnsignedByte();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }
}
