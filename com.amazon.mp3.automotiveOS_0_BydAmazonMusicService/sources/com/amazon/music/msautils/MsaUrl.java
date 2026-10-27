package com.amazon.music.msautils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class MsaUrl {
    private static String VALID_MSA_DOMAIN_CHECK = "[^:]*://[^/]*amazon[^/]*/";
    private static Set<Pattern> VALID_MSA_PATHS;
    private boolean isCleared;
    private final StringBuilder modifiers = new StringBuilder();
    private String url;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(Pattern.compile(VALID_MSA_DOMAIN_CHECK + "images/[IPSMGRA]/.*"));
        hashSet.add(Pattern.compile(VALID_MSA_DOMAIN_CHECK + "media/i3d/.*"));
        hashSet.add(Pattern.compile(VALID_MSA_DOMAIN_CHECK + "media/gsl/.*"));
        hashSet.add(Pattern.compile(VALID_MSA_DOMAIN_CHECK + "media/eBook/.*"));
        hashSet.add(Pattern.compile(VALID_MSA_DOMAIN_CHECK + "media/IMDB/.*"));
        hashSet.add(Pattern.compile(VALID_MSA_DOMAIN_CHECK + "media/gmf/.*"));
        hashSet.add(Pattern.compile(VALID_MSA_DOMAIN_CHECK + "media/edoc/.*"));
        VALID_MSA_PATHS = Collections.unmodifiableSet(hashSet);
    }

    public static boolean isMsaUrl(String str) {
        Iterator<Pattern> it = VALID_MSA_PATHS.iterator();
        while (it.hasNext()) {
            if (it.next().matcher(str).matches()) {
                return true;
            }
        }
        return false;
    }

    public static MsaUrl with(String str) {
        if (str == null) {
            throw new NullPointerException("Input url cannot be null");
        }
        return new MsaUrl(str.replace("*_.", ""));
    }

    private MsaUrl(String str) {
        this.url = str;
    }

    public String toUrl() {
        int iLastIndexOf;
        int iLastIndexOf2;
        int i;
        if (!containsModifiers(this.url)) {
            iLastIndexOf = this.url.lastIndexOf(46) + 1;
            i = iLastIndexOf;
        } else {
            if (this.isCleared) {
                iLastIndexOf = this.url.indexOf("._") + 1;
                iLastIndexOf2 = this.url.lastIndexOf("_.");
            } else {
                iLastIndexOf = this.url.lastIndexOf("_.");
                iLastIndexOf2 = this.url.lastIndexOf("_.");
            }
            i = iLastIndexOf2 + 2;
        }
        if (this.modifiers.length() > 0) {
            this.modifiers.append("_.");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.url.substring(0, iLastIndexOf));
        sb.append((Object) this.modifiers);
        String str = this.url;
        sb.append(str.substring(i, str.length()));
        return sb.toString();
    }

    public MsaUrl clear() {
        StringBuilder sb = this.modifiers;
        sb.delete(0, sb.length());
        this.isCleared = true;
        return this;
    }

    public MsaUrl scaleToWidth(int i) {
        StringBuilder sb = this.modifiers;
        sb.append("_SX");
        sb.append(i);
        return this;
    }

    public MsaUrl scaleToHeight(int i) {
        StringBuilder sb = this.modifiers;
        sb.append("_SY");
        sb.append(i);
        return this;
    }

    public MsaUrl scaleToLongest(int i) {
        StringBuilder sb = this.modifiers;
        sb.append("_SL");
        sb.append(i);
        return this;
    }

    public MsaUrl scaleToSquare(int i) {
        StringBuilder sb = this.modifiers;
        sb.append("_SS");
        sb.append(i);
        return this;
    }

    public MsaUrl upscaleToSquare(int i) {
        StringBuilder sb = this.modifiers;
        sb.append("_US");
        sb.append(i);
        return this;
    }

    public MsaUrl qualityPercent(int i) {
        if (i < 1 || i > 100) {
            throw new IllegalArgumentException("Quality must be in the range (1-100)");
        }
        StringBuilder sb = this.modifiers;
        sb.append("_QL");
        sb.append(i);
        return this;
    }

    public MsaUrl forceMediaExtension(String str) {
        StringBuilder sb = this.modifiers;
        sb.append("_FM");
        sb.append(str);
        return this;
    }

    public MsaUrl crop(int i, int i2, int i3, int i4) {
        StringBuilder sb = this.modifiers;
        sb.append("_CR");
        sb.append(i);
        sb.append(',');
        sb.append(i2);
        sb.append(',');
        sb.append(i3);
        sb.append(',');
        sb.append(i4);
        return this;
    }

    public MsaUrl border(int i, int i2, int i3, int i4) {
        StringBuilder sb = this.modifiers;
        sb.append("_BO");
        sb.append(i);
        sb.append(',');
        sb.append(i2);
        sb.append(',');
        sb.append(i3);
        sb.append(',');
        sb.append(i4);
        return this;
    }

    public MsaUrl stickerBottom(String str) {
        StringBuilder sb = this.modifiers;
        sb.append("_PG");
        sb.append(str);
        return this;
    }

    public MsaUrl centeredText(String str, int i, int i2, int i3, int i4, String str2, int i5, int i6, int i7, int i8, int i9) {
        StringBuilder sb = this.modifiers;
        sb.append("_ZB");
        sb.append(str);
        sb.append(',');
        sb.append(i);
        sb.append(',');
        sb.append(i2);
        sb.append(',');
        sb.append(i3);
        sb.append(',');
        sb.append(i4);
        sb.append(',');
        sb.append(str2);
        sb.append(',');
        sb.append(i5);
        sb.append(',');
        sb.append(i6);
        sb.append(',');
        sb.append(i7);
        sb.append(',');
        sb.append(i8);
        sb.append(',');
        sb.append(i9);
        return this;
    }

    public static boolean containsModifiers(String str) {
        return str.contains("._") && str.contains("_.");
    }
}
