package p;

import android.net.Uri;
import com.spotify.base.java.logging.Logger;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import com.spotify.webapi.service.models.Entity;
import com.spotify.webapi.service.models.EpisodeSimple;
import com.spotify.webapi.service.models.Image;
import com.spotify.webapi.service.models.ResumePoint;
import com.spotify.webapi.service.models.Search;
import com.spotify.webapi.service.models.TrackSimple;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class wb1 {
    public static Uri a(List list, ki1 ki1Var, MediaUriUtil$Transformation mediaUriUtil$Transformation, Uri uri, String str) {
        Uri uri2 = null;
        if (list != null && !list.isEmpty()) {
            if (str != null) {
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Image image = (Image) it.next();
                    if (str.equals(image.name)) {
                        uri2 = Uri.parse(image.url);
                        break;
                    }
                }
            } else {
                uri2 = Uri.parse(((Image) list.get(0)).url);
            }
        }
        return b(uri2, ki1Var, mediaUriUtil$Transformation, uri);
    }

    public static Uri b(Uri uri, ki1 ki1Var, MediaUriUtil$Transformation mediaUriUtil$Transformation, Uri uri2) {
        Uri uriB = uri != null ? ki1Var.b(uri, mediaUriUtil$Transformation) : null;
        return (uriB == null || uriB == Uri.EMPTY) ? uri2 != null ? uri2 : Uri.EMPTY : uriB;
    }

    public static String c(tx1 tx1Var) {
        ArrayList arrayListE = he1.E(Search.Type.ALBUM, Search.Type.ARTIST, Search.Type.PLAYLIST);
        if (tx1Var.c) {
            arrayListE.add(Search.Type.TRACK);
        }
        arrayListE.add(Search.Type.STATION);
        if (tx1Var.a) {
            arrayListE.add(Search.Type.SHOW);
            arrayListE.add(Search.Type.EPISODE);
        }
        return new tz2(String.valueOf(','), 1).a(arrayListE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0093  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p.jh1 d(com.spotify.webapi.service.models.Entity r7, p.ki1 r8, java.lang.String r9, java.lang.Integer r10) {
        /*
            Method dump skipped, instruction units count: 279
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.wb1.d(com.spotify.webapi.service.models.Entity, p.ki1, java.lang.String, java.lang.Integer):p.jh1");
    }

    public static p01 e(Entity entity) {
        p01 p01Var = new p01(25);
        p01Var.J(entity.isExplicit());
        if (entity instanceof TrackSimple) {
            TrackSimple trackSimple = (TrackSimple) entity;
            p01Var.G(trackSimple.duration_ms);
            String[] strArr = trackSimple.tags;
            p01Var.I(strArr != null && strArr.length > 0 && Arrays.asList(strArr).contains("MOGEF-19+"));
        } else if (entity instanceof EpisodeSimple) {
            EpisodeSimple episodeSimple = (EpisodeSimple) entity;
            p01Var.G(episodeSimple.durationMs);
            ResumePoint resumePoint = episodeSimple.resumePoint;
            long j = episodeSimple.durationMs;
            double d = 0.0d;
            if (resumePoint != null) {
                Boolean bool = resumePoint.fullyPlayed;
                if (bool != null && bool.booleanValue()) {
                    d = 1.0d;
                } else if (j != 0) {
                    d = ((double) resumePoint.resumePositionMs) / j;
                }
            }
            p01Var.C(d);
        }
        return p01Var;
    }

    public static Date f(String str) {
        int length = str.length();
        try {
            return (length != 4 ? length != 7 ? new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH) : new SimpleDateFormat("yyyy-MM", Locale.ENGLISH) : new SimpleDateFormat("yyyy", Locale.ENGLISH)).parse(str);
        } catch (ParseException e) {
            Logger.c(e, "Exception when parsing date: %s", str);
            return null;
        }
    }
}
