package com.amazon.podcast.transcription;

import com.amazon.podcast.Podcast;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.scrublib.models.ContentType;
import com.amazon.scrublib.models.ScrubSegment;
import com.amazon.scrublib.models.Signature;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public class TranscriptProvider {
    private static final Logger logger = LoggerFactory.getLogger("TranscriptionProvider");
    private static String transcript;

    private static String decompressAndGetTranscript(final String transcriptUrl) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) ((URLConnection) FirebasePerfUrlConnection.instrument(new URL(transcriptUrl).openConnection()));
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(httpURLConnection.getInputStream())));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    sb.append(line);
                } else {
                    String string = sb.toString();
                    transcript = string;
                    return string;
                }
            }
        } catch (IOException e) {
            logger.debug("Unable to retrieve transcript JSON: " + e.getMessage());
            return null;
        }
    }

    private static JSONObject getTranscriptJson(final String transcriptUrl) throws JSONException {
        String strDecompressAndGetTranscript = decompressAndGetTranscript(transcriptUrl);
        if (strDecompressAndGetTranscript == null) {
            return null;
        }
        return new JSONObject(strDecompressAndGetTranscript);
    }

    public static List<ScrubSegment> getScrubSegments(final String transcriptUrl) {
        try {
            JSONArray jSONArray = getTranscriptJson(transcriptUrl).getJSONArray("segments");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                int i2 = jSONObject.getInt("startTime");
                int i3 = jSONObject.getInt("endTime");
                String string = jSONObject.getString(MediaTrack.ROLE_CAPTION);
                String string2 = jSONObject.getString("speaker");
                ArrayList arrayList2 = new ArrayList();
                JSONArray jSONArray2 = jSONObject.getJSONArray("audioSignature");
                for (int i4 = 0; i4 < jSONArray2.length(); i4++) {
                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i4);
                    arrayList2.add(new Signature(jSONObject2.getInt("startTime"), jSONObject2.getString("signature")));
                }
                arrayList.add(new ScrubSegment(i2, i3, arrayList2, "", string, string2, false, ContentType.TRANSCRIBED_CONTENT));
            }
            return arrayList;
        } catch (JSONException e) {
            logger.debug("Unable to fetch scrubSegments from the TranscriptJSON:  " + e.getMessage());
            return null;
        }
    }

    public static Double getPeakHeight() {
        try {
            return (Double) new JSONObject(transcript).get("peakHeight");
        } catch (JSONException e) {
            logger.debug("Unable to fetch peakHeight, returning null:  " + e.getMessage());
            return null;
        }
    }

    public static boolean isTranscriptSupported() {
        boolean zIsEnabled = PodcastFeatureGating.SCRUB_TRANSCRIPTION.isEnabled();
        boolean zIsFireOS = Podcast.isFireOS();
        if (Podcast.getPlayback().isPodcastTranscriptsDisabledFromSettings() || zIsFireOS) {
            return false;
        }
        return zIsEnabled;
    }
}
