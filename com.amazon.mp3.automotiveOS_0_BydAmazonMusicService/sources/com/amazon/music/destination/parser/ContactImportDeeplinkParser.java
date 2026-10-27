package com.amazon.music.destination.parser;

import android.net.Uri;
import com.amazon.music.destination.ContactImportDestination;
import com.amazon.music.destination.core.DeeplinkParser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ContactImportDeeplinkParser.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/music/destination/parser/ContactImportDeeplinkParser;", "Lcom/amazon/music/destination/core/DeeplinkParser;", "Lcom/amazon/music/destination/ContactImportDestination;", "()V", "getUrlRegex", "", "parse", "uri", "Landroid/net/Uri;", "Companion", "DMMDestination_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ContactImportDeeplinkParser implements DeeplinkParser<ContactImportDestination> {
    private static final String CONTACT_IMPORT_SEGMENT = "contact-import";

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public ContactImportDestination parse(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        String strFindSegmentAfter = ParserUtil.findSegmentAfter(uri, CONTACT_IMPORT_SEGMENT);
        Intrinsics.checkNotNullExpressionValue(strFindSegmentAfter, "findSegmentAfter(uri, CONTACT_IMPORT_SEGMENT)");
        return new ContactImportDestination(strFindSegmentAfter, ParserUtil.getRefQueryParam(uri), ParserUtil.getTagQueryParam(uri));
    }

    @Override // com.amazon.music.destination.core.DeeplinkParser
    public String getUrlRegex() {
        String str = "/" + CONTACT_IMPORT_SEGMENT + ParserUtil.optional("/.*");
        Intrinsics.checkNotNullExpressionValue(str, "StringBuilder().apply(builderAction).toString()");
        return str;
    }
}
