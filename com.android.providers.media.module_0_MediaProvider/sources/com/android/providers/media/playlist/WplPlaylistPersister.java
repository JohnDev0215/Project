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
public class WplPlaylistPersister implements PlaylistPersister {
    private static final String ATTR_SRC = "src";
    private static final String TAG_BODY = "body";
    private static final String TAG_MEDIA = "media";
    private static final String TAG_SEQ = "seq";
    private static final String TAG_SMIL = "smil";

    @Override // com.android.providers.media.playlist.PlaylistPersister
    public void read(InputStream inputStream, List<Path> list) throws IOException {
        String attributeValue;
        FileSystem fileSystem = FileSystems.getDefault();
        try {
            XmlPullParser xmlPullParserNewPullParser = Xml.newPullParser();
            xmlPullParserNewPullParser.setInput(inputStream, StandardCharsets.UTF_8.name());
            while (true) {
                int next = xmlPullParserNewPullParser.next();
                if (next == 1) {
                    return;
                }
                if (next == 2 && TAG_MEDIA.equals(xmlPullParserNewPullParser.getName()) && (attributeValue = xmlPullParserNewPullParser.getAttributeValue(null, ATTR_SRC)) != null) {
                    list.add(fileSystem.getPath(attributeValue.replace('\\', '/'), new String[0]));
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
        xmlSerializerNewSerializer.startTag(null, TAG_SMIL);
        xmlSerializerNewSerializer.startTag(null, TAG_BODY);
        xmlSerializerNewSerializer.startTag(null, TAG_SEQ);
        for (Path path : list) {
            xmlSerializerNewSerializer.startTag(null, TAG_MEDIA);
            xmlSerializerNewSerializer.attribute(null, ATTR_SRC, path.toString());
            xmlSerializerNewSerializer.endTag(null, TAG_MEDIA);
        }
        xmlSerializerNewSerializer.endTag(null, TAG_SEQ);
        xmlSerializerNewSerializer.endTag(null, TAG_BODY);
        xmlSerializerNewSerializer.endTag(null, TAG_SMIL);
        xmlSerializerNewSerializer.endDocument();
    }
}
