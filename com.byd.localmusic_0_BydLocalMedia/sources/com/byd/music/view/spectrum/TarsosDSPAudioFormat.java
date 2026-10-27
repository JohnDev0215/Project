package com.byd.music.view.spectrum;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class TarsosDSPAudioFormat {
    public static final int NOT_SPECIFIED = -1;
    protected boolean bigEndian;
    protected int channels;
    protected Encoding encoding;
    protected float frameRate;
    protected int frameSize;
    private HashMap<String, Object> properties;
    protected float sampleRate;
    protected int sampleSizeInBits;

    public TarsosDSPAudioFormat(Encoding encoding, float f, int i, int i2, int i3, float f2, boolean z) {
        this.encoding = encoding;
        this.sampleRate = f;
        this.sampleSizeInBits = i;
        this.channels = i2;
        this.frameSize = i3;
        this.frameRate = f2;
        this.bigEndian = z;
        this.properties = null;
    }

    public TarsosDSPAudioFormat(Encoding encoding, float f, int i, int i2, int i3, float f2, boolean z, Map<String, Object> map) {
        this(encoding, f, i, i2, i3, f2, z);
        this.properties = new HashMap<>(map);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TarsosDSPAudioFormat(float f, int i, int i2, boolean z, boolean z2) {
        Encoding encoding = z ? Encoding.PCM_SIGNED : Encoding.PCM_UNSIGNED;
        int i3 = -1;
        if (i2 != -1 && i != -1) {
            i3 = ((i + 7) / 8) * i2;
        }
        this(encoding, f, i, i2, i3, f, z2);
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public float getSampleRate() {
        return this.sampleRate;
    }

    public int getSampleSizeInBits() {
        return this.sampleSizeInBits;
    }

    public int getChannels() {
        return this.channels;
    }

    public int getFrameSize() {
        return this.frameSize;
    }

    public float getFrameRate() {
        return this.frameRate;
    }

    public boolean isBigEndian() {
        return this.bigEndian;
    }

    public Map<String, Object> properties() {
        Map map;
        HashMap<String, Object> map2 = this.properties;
        if (map2 == null) {
            map = new HashMap(0);
        } else {
            map = (Map) map2.clone();
        }
        return Collections.unmodifiableMap(map);
    }

    public Object getProperty(String str) {
        HashMap<String, Object> map = this.properties;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public boolean matches(TarsosDSPAudioFormat tarsosDSPAudioFormat) {
        if (!tarsosDSPAudioFormat.getEncoding().equals(getEncoding())) {
            return false;
        }
        if ((tarsosDSPAudioFormat.getSampleRate() != -1.0f && tarsosDSPAudioFormat.getSampleRate() != getSampleRate()) || tarsosDSPAudioFormat.getSampleSizeInBits() != getSampleSizeInBits() || tarsosDSPAudioFormat.getChannels() != getChannels() || tarsosDSPAudioFormat.getFrameSize() != getFrameSize()) {
            return false;
        }
        if (tarsosDSPAudioFormat.getFrameRate() == -1.0f || tarsosDSPAudioFormat.getFrameRate() == getFrameRate()) {
            return tarsosDSPAudioFormat.getSampleSizeInBits() <= 8 || tarsosDSPAudioFormat.isBigEndian() == isBigEndian();
        }
        return false;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7 = "";
        if (getEncoding() != null) {
            str = getEncoding().toString() + " ";
        } else {
            str = "";
        }
        if (getSampleRate() == -1.0f) {
            str2 = "unknown sample rate, ";
        } else {
            str2 = "" + getSampleRate() + " Hz, ";
        }
        if (getSampleSizeInBits() == -1.0f) {
            str3 = "unknown bits per sample, ";
        } else {
            str3 = "" + getSampleSizeInBits() + " bit, ";
        }
        if (getChannels() == 1) {
            str4 = "mono, ";
        } else if (getChannels() == 2) {
            str4 = "stereo, ";
        } else if (getChannels() == -1) {
            str4 = " unknown number of channels, ";
        } else {
            str4 = "" + getChannels() + " channels, ";
        }
        if (getFrameSize() == -1.0f) {
            str5 = "unknown frame size, ";
        } else {
            str5 = "" + getFrameSize() + " bytes/frame, ";
        }
        if (Math.abs(getSampleRate() - getFrameRate()) <= 1.0E-5d) {
            str6 = "";
        } else if (getFrameRate() == -1.0f) {
            str6 = "unknown frame rate, ";
        } else {
            str6 = getFrameRate() + " frames/second, ";
        }
        if ((getEncoding().equals(Encoding.PCM_SIGNED) || getEncoding().equals(Encoding.PCM_UNSIGNED)) && (getSampleSizeInBits() > 8 || getSampleSizeInBits() == -1)) {
            str7 = isBigEndian() ? "big-endian" : "little-endian";
        }
        return str + str2 + str3 + str4 + str5 + str6 + str7;
    }

    public static class Encoding {
        private String name;
        public static final Encoding PCM_SIGNED = new Encoding("PCM_SIGNED");
        public static final Encoding PCM_UNSIGNED = new Encoding("PCM_UNSIGNED");
        public static final Encoding ULAW = new Encoding("ULAW");
        public static final Encoding ALAW = new Encoding("ALAW");

        public Encoding(String str) {
            this.name = str;
        }

        public final boolean equals(Object obj) {
            if (toString() == null) {
                return obj != null && obj.toString() == null;
            }
            if (obj instanceof Encoding) {
                return toString().equals(obj.toString());
            }
            return false;
        }

        public final int hashCode() {
            if (toString() == null) {
                return 0;
            }
            return toString().hashCode();
        }

        public final String toString() {
            return this.name;
        }
    }
}
