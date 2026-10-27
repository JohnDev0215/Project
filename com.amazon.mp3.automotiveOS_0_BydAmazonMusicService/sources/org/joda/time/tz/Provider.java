package org.joda.time.tz;

import java.util.Set;
import org.joda.time.DateTimeZone;

/* JADX INFO: loaded from: classes6.dex */
public interface Provider {
    Set<String> getAvailableIDs();

    DateTimeZone getZone(String str);
}
