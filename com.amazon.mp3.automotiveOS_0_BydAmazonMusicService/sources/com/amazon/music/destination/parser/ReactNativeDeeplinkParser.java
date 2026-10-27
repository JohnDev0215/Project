package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.ReactNativeDestination;
import com.amazon.music.destination.core.DeeplinkParser;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReactNativeDeeplinkParser.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u0010\r\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/destination/parser/ReactNativeDeeplinkParser;", "Lcom/amazon/music/destination/core/DeeplinkParser;", "Lcom/amazon/music/destination/ReactNativeDestination;", "urlRegex", "", "presentationStyle", "(Ljava/lang/String;Ljava/lang/String;)V", "REF_QUERY_PARAM_NAMES", "", "TAG_QUERY_PARAM_NAME", "getRefQueryParam", "uri", "Landroid/net/Uri;", "getTagQueryParam", "getUrlRegex", "parse", "DMMDestination_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ReactNativeDeeplinkParser implements DeeplinkParser<ReactNativeDestination> {
    private final List<String> REF_QUERY_PARAM_NAMES;
    private final String TAG_QUERY_PARAM_NAME;
    private final String presentationStyle;
    private final String urlRegex;

    public ReactNativeDeeplinkParser(String urlRegex, String presentationStyle) {
        Intrinsics.checkNotNullParameter(urlRegex, "urlRegex");
        Intrinsics.checkNotNullParameter(presentationStyle, "presentationStyle");
        this.urlRegex = urlRegex;
        this.presentationStyle = presentationStyle;
        this.REF_QUERY_PARAM_NAMES = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"ref", "ref_"});
        this.TAG_QUERY_PARAM_NAME = ParserUtil.TAG_QUERY_PARAM_NAME;
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public ReactNativeDestination parse(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        String refQueryParam = getRefQueryParam(uri);
        String tagQueryParam = getTagQueryParam(uri);
        String string = uri.toString();
        Intrinsics.checkNotNullExpressionValue(string, "uri.toString()");
        return new ReactNativeDestination(string, this.presentationStyle, refQueryParam, tagQueryParam);
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        return this.urlRegex;
    }

    private final String getRefQueryParam(Uri uri) {
        if (uri == null) {
            return "";
        }
        Iterator<String> it = this.REF_QUERY_PARAM_NAMES.iterator();
        while (it.hasNext()) {
            String queryParameter = uri.getQueryParameter(it.next());
            if (queryParameter != null) {
                return queryParameter;
            }
        }
        return "";
    }

    private final String getTagQueryParam(Uri uri) {
        String queryParameter;
        return (uri == null || (queryParameter = uri.getQueryParameter(this.TAG_QUERY_PARAM_NAME)) == null) ? "" : queryParameter;
    }
}
