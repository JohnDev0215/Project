package android.net.captiveportal;

import android.text.TextUtils;
import android.util.Log;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* JADX INFO: loaded from: classes.dex */
public abstract class CaptivePortalProbeSpec {
    private static final String TAG = "CaptivePortalProbeSpec";
    private final String mEncodedSpec;
    private final URL mUrl;

    public abstract CaptivePortalProbeResult getResult(int i, String str);

    CaptivePortalProbeSpec(String str, URL url) {
        checkNotNull(str);
        this.mEncodedSpec = str;
        checkNotNull(url);
        this.mUrl = url;
    }

    public static CaptivePortalProbeSpec parseSpec(String str) throws MalformedURLException, ParseException {
        if (TextUtils.isEmpty(str)) {
            throw new ParseException("Empty probe spec", 0);
        }
        String[] strArrSplit = TextUtils.split(str, "@@/@@");
        if (strArrSplit.length != 3) {
            throw new ParseException("Probe spec does not have 3 parts", 0);
        }
        int length = strArrSplit[0].length() + 5;
        int length2 = strArrSplit[1].length() + length + 5;
        return new RegexMatchProbeSpec(str, new URL(strArrSplit[0]), parsePatternIfNonEmpty(strArrSplit[1], length), parsePatternIfNonEmpty(strArrSplit[2], length2));
    }

    private static Pattern parsePatternIfNonEmpty(String str, int i) throws ParseException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Pattern.compile(str);
        } catch (PatternSyntaxException e) {
            throw new ParseException(String.format("Invalid status pattern [%s]: %s", str, e), i);
        }
    }

    public static CaptivePortalProbeSpec parseSpecOrNull(String str) {
        if (str == null) {
            return null;
        }
        try {
            return parseSpec(str);
        } catch (MalformedURLException | ParseException e) {
            Log.e(TAG, "Invalid probe spec: " + str, e);
            return null;
        }
    }

    public static Collection<CaptivePortalProbeSpec> parseCaptivePortalProbeSpecs(String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            for (String str2 : TextUtils.split(str, "@@,@@")) {
                try {
                    arrayList.add(parseSpec(str2));
                } catch (MalformedURLException | ParseException e) {
                    Log.e(TAG, "Invalid probe spec: " + str2, e);
                }
            }
        }
        if (arrayList.isEmpty()) {
            Log.e(TAG, String.format("could not create any validation spec from %s", str));
        }
        return arrayList;
    }

    public String getEncodedSpec() {
        return this.mEncodedSpec;
    }

    public URL getUrl() {
        return this.mUrl;
    }

    private static class RegexMatchProbeSpec extends CaptivePortalProbeSpec {
        final Pattern mLocationHeaderRegex;
        final Pattern mStatusRegex;

        RegexMatchProbeSpec(String str, URL url, Pattern pattern, Pattern pattern2) throws ParseException {
            super(str, url);
            String protocol = url.getProtocol();
            if (!"http".equals(protocol)) {
                throw new IllegalArgumentException("Protocol for probe spec should be http but was" + protocol);
            }
            this.mStatusRegex = pattern;
            this.mLocationHeaderRegex = pattern2;
        }

        @Override // android.net.captiveportal.CaptivePortalProbeSpec
        public CaptivePortalProbeResult getResult(int i, String str) {
            return new CaptivePortalProbeResult((CaptivePortalProbeSpec.safeMatch(String.valueOf(i), this.mStatusRegex) && CaptivePortalProbeSpec.safeMatch(str, this.mLocationHeaderRegex)) ? 204 : 302, str, getUrl().toString(), this, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean safeMatch(String str, Pattern pattern) {
        return pattern == null || TextUtils.isEmpty(str) || pattern.matcher(str).matches();
    }

    private static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw null;
    }
}
