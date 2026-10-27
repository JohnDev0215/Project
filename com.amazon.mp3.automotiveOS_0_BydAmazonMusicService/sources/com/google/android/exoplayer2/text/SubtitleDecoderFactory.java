package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.text.cea.Cea608Decoder;
import com.google.android.exoplayer2.text.cea.Cea708Decoder;
import com.google.android.exoplayer2.text.dvb.DvbDecoder;
import com.google.android.exoplayer2.text.ssa.SsaDecoder;
import com.google.android.exoplayer2.text.subrip.SubripDecoder;
import com.google.android.exoplayer2.text.ttml.TtmlDecoder;
import com.google.android.exoplayer2.text.tx3g.Tx3gDecoder;
import com.google.android.exoplayer2.text.webvtt.Mp4WebvttDecoder;
import com.google.android.exoplayer2.text.webvtt.WebvttDecoder;

/* JADX INFO: loaded from: classes6.dex */
public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory DEFAULT = new SubtitleDecoderFactory() { // from class: com.google.android.exoplayer2.text.SubtitleDecoderFactory.1
        @Override // com.google.android.exoplayer2.text.SubtitleDecoderFactory
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return "text/vtt".equals(str) || "text/x-ssa".equals(str) || "application/ttml+xml".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-subrip".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/cea-608".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/cea-708".equals(str) || "application/dvbsubs".equals(str);
        }

        @Override // com.google.android.exoplayer2.text.SubtitleDecoderFactory
        public SubtitleDecoder createDecoder(Format format) {
            String str = format.sampleMimeType;
            str.hashCode();
            switch (str) {
                case "application/dvbsubs":
                    return new DvbDecoder(format.initializationData);
                case "application/x-mp4-vtt":
                    return new Mp4WebvttDecoder();
                case "text/vtt":
                    return new WebvttDecoder();
                case "application/x-quicktime-tx3g":
                    return new Tx3gDecoder(format.initializationData);
                case "text/x-ssa":
                    return new SsaDecoder(format.initializationData);
                case "application/x-mp4-cea-608":
                case "application/cea-608":
                    return new Cea608Decoder(format.sampleMimeType, format.accessibilityChannel, format.isWideAspectRatio);
                case "application/cea-708":
                    return new Cea708Decoder(format.accessibilityChannel, format.isWideAspectRatio);
                case "application/x-subrip":
                    return new SubripDecoder();
                case "application/ttml+xml":
                    return new TtmlDecoder();
                default:
                    throw new IllegalArgumentException("Attempted to create decoder for unsupported format");
            }
        }
    };

    SubtitleDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
