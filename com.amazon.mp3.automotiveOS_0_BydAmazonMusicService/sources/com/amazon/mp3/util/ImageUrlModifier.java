package com.amazon.mp3.util;

/* JADX INFO: loaded from: classes4.dex */
public final class ImageUrlModifier {
    private final StringBuilder mAppendedOptions = new StringBuilder();
    private String mUrl;

    public static ImageUrlModifier with(String url) {
        if (url == null) {
            throw new NullPointerException("Input url cannot be null");
        }
        return new ImageUrlModifier(url.replace("*_.", ""));
    }

    private ImageUrlModifier(String url) {
        this.mUrl = url;
    }

    public String toUrl() {
        if (this.mAppendedOptions.length() > 0 && this.mUrl.length() > 0) {
            int iIndexOf = this.mUrl.indexOf("._");
            int iLastIndexOf = this.mUrl.lastIndexOf(46);
            if (iLastIndexOf == -1) {
                return this.mUrl;
            }
            if (iIndexOf == -1) {
                iIndexOf = iLastIndexOf;
            }
            return this.mUrl.substring(0, iIndexOf) + '.' + ((Object) this.mAppendedOptions) + '_' + this.mUrl.substring(iLastIndexOf);
        }
        return this.mUrl;
    }

    public ImageUrlModifier clearLastModifier() {
        int iLastIndexOf = this.mUrl.lastIndexOf(46);
        if (iLastIndexOf == -1) {
            return this;
        }
        String str = this.mUrl;
        String strSubstring = str.substring(iLastIndexOf, str.length());
        String strSubstring2 = this.mUrl.substring(0, iLastIndexOf);
        int iLastIndexOf2 = strSubstring2.lastIndexOf(46);
        String strSubstring3 = this.mUrl.substring(0, iLastIndexOf2);
        String strSubstring4 = this.mUrl.substring(iLastIndexOf2, strSubstring2.length());
        if (strSubstring4.charAt(1) == '_' && strSubstring4.charAt(strSubstring4.length() - 1) == '_') {
            this.mUrl = strSubstring3 + strSubstring;
        }
        return this;
    }

    public ImageUrlModifier scaleToWidth(int width) {
        StringBuilder sb = this.mAppendedOptions;
        sb.append("_SX");
        sb.append(width);
        return this;
    }

    public ImageUrlModifier scaleToHeight(int height) {
        StringBuilder sb = this.mAppendedOptions;
        sb.append("_SY");
        sb.append(height);
        return this;
    }

    public ImageUrlModifier scaleToLongest(int longest) {
        StringBuilder sb = this.mAppendedOptions;
        sb.append("_SL");
        sb.append(longest);
        return this;
    }

    public ImageUrlModifier upscaleToLongest(int longest) {
        StringBuilder sb = this.mAppendedOptions;
        sb.append("_UL");
        sb.append(longest);
        return this;
    }

    public static boolean containsModifiers(String url) {
        return url.contains("._") && url.contains("_.");
    }
}
