package com.amazon.music.push;

import android.content.Context;
import android.util.Log;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.push.sonarpush.NotificationUtil;
import com.amazon.music.push.util.Utils;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class PushLocaleInPayloadManager {
    private static final String TAG = "PushLocaleInPayloadManager";
    private final Context mContext;

    public PushLocaleInPayloadManager(Context context) {
        this.mContext = context;
    }

    public Map<String, String> pushLocaleHandler(Map<String, String> map) {
        NotificationUtilData notificationUtilData;
        String str;
        HashMap map2 = new HashMap();
        try {
            Map<String, String> mapDecodeDeeplinkUrl = decodeDeeplinkUrl(map.get("pinpoint.deeplink"));
            if (mapDecodeDeeplinkUrl.isEmpty()) {
                return map2;
            }
            String str2 = mapDecodeDeeplinkUrl.get(Splash.PARAMS_LOCALE);
            String currentLocale = NotificationUtil.getCurrentLocale(this.mContext);
            if (StringUtils.equals(str2, currentLocale)) {
                return map;
            }
            String str3 = mapDecodeDeeplinkUrl.get("notificationtype");
            String file = Utils.readFile(this.mContext, "pushlocale.json");
            if (StringUtils.isBlank(str3) || StringUtils.isBlank(file)) {
                Log.w(TAG, "Either notification type or json file object is empty/null");
                return map2;
            }
            notificationUtilData = new NotificationUtilData(mapDecodeDeeplinkUrl, str3, file, currentLocale);
            str = TAG;
            Log.i(str, "Original push title: " + map.get("pinpoint.notification.title"));
            Log.i(str, "Original push text: " + map.get("pinpoint.notification.body"));
            str3.hashCode();
            switch (str3) {
                case "ARTIST_STREAMING":
                    return handleArtistStreamingNotificationType(notificationUtilData);
                case "ARTIST_RELEASE_SONG":
                case "ARTIST_RELEASE_AMU_SONG":
                case "ARTIST_RELEASE_AMU_ALBUM":
                case "ARTIST_RELEASE_ALBUM":
                    return handleArtistReleaseNotificationType(notificationUtilData);
                case "CUSTOMER_FOLLOW":
                    return handleCustomerFollowNotificationType(notificationUtilData);
                default:
                    Log.w(str, "Notification type not found");
                    return map2;
            }
        } catch (URISyntaxException e) {
            Log.w(TAG, "URISyntaxException while decoding deeplink " + e);
            return map2;
        }
    }

    private Map<String, String> handleArtistReleaseNotificationType(NotificationUtilData notificationUtilData) {
        HashMap map = new HashMap();
        String albumOrSong = getAlbumOrSong(notificationUtilData);
        String str = notificationUtilData.getDecodedDeeplinkMap().get("artist");
        if (StringUtils.isBlank(str) || StringUtils.isBlank(albumOrSong)) {
            Log.w(TAG, "Either artistName or albumOrSongString variable is null/empty");
            return map;
        }
        String str2 = TAG;
        Log.i(str2, "ArtistRelease: AlbumOrSong is " + albumOrSong);
        Log.i(str2, "ArtistRelease: ArtistName is " + str);
        String strExtractFileTag = Utils.extractFileTag(notificationUtilData, "text");
        String strReplace = StringUtils.replace(StringUtils.replace(strExtractFileTag, "{ALBUM_OR_SONG_NAME}", albumOrSong.trim()), "{ARTIST_NAME}", str.trim());
        String strExtractFileTag2 = Utils.extractFileTag(notificationUtilData, "title");
        Log.i(str2, "Text from file in device locale: " + strExtractFileTag);
        Log.i(str2, "Push Modified Text : " + strReplace);
        Log.i(str2, "Push Modified Title : " + strExtractFileTag2);
        if (!StringUtils.isBlank(strReplace) && !StringUtils.isBlank(strExtractFileTag2)) {
            map.put("pinpoint.notification.body", strReplace);
            map.put("pinpoint.notification.title", strExtractFileTag2);
        }
        return map;
    }

    private Map<String, String> handleCustomerFollowNotificationType(NotificationUtilData notificationUtilData) {
        String str = notificationUtilData.getDecodedDeeplinkMap().get("custname");
        HashMap map = new HashMap();
        if (StringUtils.isBlank(str)) {
            Log.w(TAG, "Extracted customerName is blank");
            return map;
        }
        String str2 = TAG;
        Log.i(str2, "Extracted Customer name: " + str);
        String strExtractFileTag = Utils.extractFileTag(notificationUtilData, "text");
        String strReplace = StringUtils.replace(strExtractFileTag, "{placeHolderName}", str.trim());
        String strExtractFileTag2 = Utils.extractFileTag(notificationUtilData, "title");
        Log.i(str2, "Text from file in device locale: " + strExtractFileTag);
        Log.i(str2, "Push Modified Text: " + strReplace);
        Log.i(str2, "Push Modified Title: " + strExtractFileTag2);
        if (!StringUtils.isBlank(strReplace) && !StringUtils.isBlank(strExtractFileTag2)) {
            map.put("pinpoint.notification.body", strReplace);
            map.put("pinpoint.notification.title", strExtractFileTag2);
        }
        return map;
    }

    private Map<String, String> handleArtistStreamingNotificationType(NotificationUtilData notificationUtilData) {
        String str = notificationUtilData.getDecodedDeeplinkMap().get("artist");
        HashMap map = new HashMap();
        if (StringUtils.isBlank(str)) {
            Log.w(TAG, "Extracted artistName is blank");
            return map;
        }
        String str2 = TAG;
        Log.i(str2, "Extracted Artist name: " + str);
        String strExtractFileTag = Utils.extractFileTag(notificationUtilData, "title");
        String strExtractFileTag2 = Utils.extractFileTag(notificationUtilData, "text");
        String strReplace = StringUtils.replace(strExtractFileTag, "{placeHolderName}", str.trim());
        Log.i(str2, "Title from file in device locale: " + strExtractFileTag);
        Log.i(str2, "Push Modified Text: " + strExtractFileTag2);
        Log.i(str2, "Push Modified Title: " + strReplace);
        if (!StringUtils.isBlank(strExtractFileTag2) && !StringUtils.isBlank(strReplace)) {
            map.put("pinpoint.notification.body", strExtractFileTag2);
            map.put("pinpoint.notification.title", strReplace);
        }
        return map;
    }

    private String getAlbumOrSong(NotificationUtilData notificationUtilData) {
        if (notificationUtilData.getNotificationType().contains("ALBUM")) {
            return notificationUtilData.getDecodedDeeplinkMap().get("album");
        }
        return notificationUtilData.getNotificationType().contains("SONG") ? notificationUtilData.getDecodedDeeplinkMap().get("song") : "";
    }

    private Map<String, String> decodeDeeplinkUrl(String str) throws URISyntaxException {
        String rawQuery = new URI(str).getRawQuery();
        if (StringUtils.isBlank(rawQuery)) {
            Log.w(TAG, "Decoded deeplink query is blank");
            return new HashMap();
        }
        return (Map) Arrays.stream(rawQuery.split("&")).map(new Function() { // from class: com.amazon.music.push.PushLocaleInPayloadManager$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$decodeDeeplinkUrl$0((String) obj);
            }
        }).filter(new Predicate() { // from class: com.amazon.music.push.PushLocaleInPayloadManager$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$decodeDeeplinkUrl$1((String) obj);
            }
        }).collect(Collectors.toMap(new Function() { // from class: com.amazon.music.push.PushLocaleInPayloadManager$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PushLocaleInPayloadManager.lambda$decodeDeeplinkUrl$2((String) obj);
            }
        }, new Function() { // from class: com.amazon.music.push.PushLocaleInPayloadManager$$ExternalSyntheticLambda3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PushLocaleInPayloadManager.lambda$decodeDeeplinkUrl$3((String) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ String lambda$decodeDeeplinkUrl$0(String str) {
        return str.split("=")[0] + "=" + decode(str.split("=")[1], StandardCharsets.UTF_8.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$decodeDeeplinkUrl$2(String str) {
        return StringUtils.substringBefore(str, "=");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$decodeDeeplinkUrl$3(String str) {
        return StringUtils.substringAfter(str, "=");
    }

    String decode(String str, String str2) {
        try {
            return URLDecoder.decode(str, str2);
        } catch (UnsupportedEncodingException e) {
            Log.w(TAG, "UnsupportedEncodingException while decoding deeplink " + e);
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: shouldIncludeNotificationTag, reason: merged with bridge method [inline-methods] */
    public boolean lambda$decodeDeeplinkUrl$1(String str) {
        Iterator it = Arrays.asList(Splash.PARAMS_LOCALE, "notificationtype", "custname", "artist", "song", "album").iterator();
        while (it.hasNext()) {
            if (StringUtils.contains(str, (String) it.next())) {
                return true;
            }
        }
        return false;
    }
}
