package com.amazon.avod.playbackclient.subtitle.internal;

import com.amazon.avod.core.Subtitle;
import com.amazon.avod.playbackclient.subtitle.internal.SubtitleException;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.Nonnull;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class SubtitleDataProvider {
    private final SubtitleParser mSubtitleParser;

    public SubtitleDataProvider() {
        this(new SubtitleParser());
    }

    SubtitleDataProvider(SubtitleParser subtitleParser) {
        this.mSubtitleParser = subtitleParser;
    }

    @Nonnull
    public SubtitleCollection getSubtitleCollection(@Nonnull File file, @Nonnull Subtitle subtitle) throws SubtitleException {
        Preconditions.checkNotNull(file, "subtitleFile");
        Preconditions.checkNotNull(subtitle, "subtitleData");
        try {
            SubtitleCollection xmlFile = this.mSubtitleParser.parseXmlFile(file, subtitle);
            if (xmlFile.isEmpty()) {
                throw new EmptySubtitleException(SubtitleException.SubtitleErrorCode.PARSING, "There was a problem parsing the subtitles.");
            }
            DLog.logf("Subtitles Have Been Successfully Processed");
            return xmlFile;
        } catch (FileNotFoundException e) {
            throw new SubtitleException(SubtitleException.SubtitleErrorCode.PARSING, "Subtitles file not found.", e);
        } catch (IOException e2) {
            throw new SubtitleException(SubtitleException.SubtitleErrorCode.PARSING, "Problem handling input file for subtitles.", e2);
        } catch (NoSuchFieldException e3) {
            throw new SubtitleException(SubtitleException.SubtitleErrorCode.PARSING, "Problem finding an XML field for subtitles.", e3);
        } catch (XmlPullParserException e4) {
            throw new SubtitleException(SubtitleException.SubtitleErrorCode.PARSING, "Problem with the subtitle XML Parser.", e4);
        }
    }

    @Nonnull
    public SubtitleCollection getSubtitleCollection(@Nonnull InputStream inputStream, long j, long j2) throws SubtitleException {
        Preconditions.checkNotNull(inputStream, "Subtitle stream cannot be null");
        try {
            SubtitleCollection xmlStream = this.mSubtitleParser.parseXmlStream(inputStream, j, j2);
            if (xmlStream.isEmpty()) {
                throw new EmptySubtitleException(SubtitleException.SubtitleErrorCode.PARSING, "There was a problem parsing the subtitles.");
            }
            DLog.logf("Subtitles Have Been Successfully Processed");
            return xmlStream;
        } catch (FileNotFoundException e) {
            throw new SubtitleException(SubtitleException.SubtitleErrorCode.PARSING, "Subtitles file not found.", e);
        } catch (IOException e2) {
            throw new SubtitleException(SubtitleException.SubtitleErrorCode.PARSING, "Problem handling input file for subtitles.", e2);
        } catch (NoSuchFieldException e3) {
            throw new SubtitleException(SubtitleException.SubtitleErrorCode.PARSING, "Problem finding an XML field for subtitles.", e3);
        } catch (XmlPullParserException e4) {
            throw new SubtitleException(SubtitleException.SubtitleErrorCode.PARSING, "Problem with the subtitle XML Parser.", e4);
        }
    }
}
