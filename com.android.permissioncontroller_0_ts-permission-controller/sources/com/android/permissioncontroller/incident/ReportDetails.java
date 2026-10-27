package com.android.permissioncontroller.incident;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.IncidentManager;
import com.google.protobuf.ByteString;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes8.dex */
public class ReportDetails {
    private static final String TAG = "ReportDetails";
    private ArrayList<String> mReasons = new ArrayList<>();
    private ArrayList<Drawable> mImages = new ArrayList<>();

    public static class ParseException extends Exception {
        public ParseException(String message) {
            super(message);
        }

        public ParseException(String message, Throwable ex) {
            super(message, ex);
        }
    }

    private ReportDetails() {
    }

    public static ReportDetails parseIncidentReport(Context context, Uri uri) throws ParseException {
        InputStream stream;
        IncidentMinimal incident;
        ReportDetails details = new ReportDetails();
        try {
            IncidentManager incidentManager = (IncidentManager) context.getSystemService(IncidentManager.class);
            IncidentManager.IncidentReport report = incidentManager.getIncidentReport(uri);
            if (report != null && (stream = report.getInputStream()) != null && (incident = IncidentMinimal.parseFrom(stream)) != null) {
                parseImages(details.mImages, incident, context.getResources());
                parseReasons(details.mReasons, incident);
            }
            return details;
        } catch (IOException ex) {
            throw new ParseException("Error while reading stream.", ex);
        } catch (OutOfMemoryError ex2) {
            throw new ParseException("Out of memory while loading incident report.", ex2);
        }
    }

    private static void parseReasons(ArrayList<String> result, IncidentMinimal incident) {
        String reason;
        int headerSize = incident.getHeaderCount();
        for (int i = 0; i < headerSize; i++) {
            IncidentHeaderProto header = incident.getHeader(i);
            if (header.hasReason() && (reason = header.getReason()) != null && reason.length() > 0) {
                result.add(reason);
            }
        }
    }

    private static void parseImages(ArrayList<Drawable> result, IncidentMinimal incident, Resources res) throws ParseException {
        int totalImageCount = 0;
        if (incident.hasRestrictedImagesSection()) {
            RestrictedImagesDumpProto section = incident.getRestrictedImagesSection();
            int setsCount = section.getSetsCount();
            for (int i = 0; i < setsCount; i++) {
                RestrictedImageSetProto set = section.getSets(i);
                if (set != null) {
                    int imageCount = set.getImagesCount();
                    for (int j = 0; j < imageCount; j++) {
                        totalImageCount++;
                        if (totalImageCount > 200) {
                            throw new ParseException("Image count is greater than the limit of 200");
                        }
                        RestrictedImageProto image = set.getImages(j);
                        if (image != null) {
                            String mimeType = image.getMimeType();
                            if (!"image/jpeg".equals(mimeType) && !"image/png".equals(mimeType)) {
                                throw new ParseException("Unsupported image type " + mimeType);
                            }
                            ByteString bytes = image.getImageData();
                            if (bytes != null) {
                                byte[] buf = bytes.toByteArray();
                                if (buf.length != 0) {
                                    Drawable drawable = new BitmapDrawable(res, new ByteArrayInputStream(buf));
                                    result.add(drawable);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public ArrayList<String> getReasons() {
        return this.mReasons;
    }

    public ArrayList<Drawable> getImages() {
        return this.mImages;
    }
}
