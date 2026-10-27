package com.android.providers.media.playlist;

import android.util.Xml;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: classes.dex */
public class XspfPlaylistPersister implements PlaylistPersister {
    private static final String TAG_LOCATION = "location";
    private static final String TAG_PLAYLIST = "playlist";
    private static final String TAG_TRACK = "track";
    private static final String TAG_TRACK_LIST = "trackList";

    @Override // com.android.providers.media.playlist.PlaylistPersister
    public void read(InputStream inputStream, List<Path> list) throws IOException {
        String strNextText;
        FileSystem fileSystem = FileSystems.getDefault();
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, StandardCharsets.UTF_8.name());
            while (true) {
                int next = xmlPullParserNewPullParser.next();
                if (next == 1) {
                    return;
                }
                if (next == 2 && TAG_LOCATION.equals(xmlPullParserNewPullParser.getName()) && (strNextText = xmlPullParserNewPullParser.nextText()) != null) {
                    list.add(fileSystem.getPath(strNextText.replace('\\', '/'), new String[0]));
                }
            }
        } catch (XmlPullParserException e) {
            throw new IOException(e);
        }
    }

    @Override // com.android.providers.media.playlist.PlaylistPersister
    public void write(OutputStream outputStream, List<Path> list) throws IOException {
        XmlSerializer xmlSerializerNewSerializer = Xml.newSerializer();
        xmlSerializerNewSerializer.setOutput(outputStream, StandardCharsets.UTF_8.name());
        xmlSerializerNewSerializer.startDocument(null, Boolean.TRUE);
        xmlSerializerNewSerializer.startTag(null, TAG_PLAYLIST);
        xmlSerializerNewSerializer.startTag(null, TAG_TRACK_LIST);
        for (Path path : list) {
            xmlSerializerNewSerializer.startTag(null, TAG_TRACK);
            xmlSerializerNewSerializer.startTag(null, TAG_LOCATION);
            xmlSerializerNewSerializer.text(path.toString());
            xmlSerializerNewSerializer.endTag(null, TAG_LOCATION);
            xmlSerializerNewSerializer.endTag(null, TAG_TRACK);
        }
        xmlSerializerNewSerializer.endTag(null, TAG_TRACK_LIST);
        xmlSerializerNewSerializer.endTag(null, TAG_PLAYLIST);
        xmlSerializerNewSerializer.endDocument();
    }
}
