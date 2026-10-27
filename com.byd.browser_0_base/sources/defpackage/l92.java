package defpackage;

import android.content.Context;
import defpackage.yf;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$string;

/* JADX INFO: compiled from: ReadingListSectionHeader.java */
/* JADX INFO: loaded from: classes.dex */
public final class l92 {
    public static yf a(Context context, boolean z) {
        return new yf(6, null, new yf.a(z ? context.getResources().getDimensionPixelSize(R$dimen.bookmark_reading_list_section_header_padding_top) : 0, context.getString(z ? R$string.reading_list_read : R$string.reading_list_unread)));
    }
}
