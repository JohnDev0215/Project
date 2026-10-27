package org.threeten.bp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.regex.Pattern;
import org.slf4j.Marker;
import org.threeten.bp.jdk8.Jdk8Methods;
import org.threeten.bp.zone.ZoneRules;
import org.threeten.bp.zone.ZoneRulesException;
import org.threeten.bp.zone.ZoneRulesProvider;

/* JADX INFO: loaded from: classes4.dex */
final class ZoneRegion extends ZoneId implements Serializable {
    private static final Pattern PATTERN = Pattern.compile("[A-Za-z][A-Za-z0-9~/._+-]+");
    private static final long serialVersionUID = 8386373296231747096L;
    private final String id;
    private final transient ZoneRules rules;

    private static ZoneRegion ofLenient(String str) {
        if (str.equals("Z") || str.startsWith(Marker.ANY_NON_NULL_MARKER) || str.startsWith("-")) {
            throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: " + str);
        }
        if (str.equals("UTC") || str.equals("GMT") || str.equals("UT")) {
            return new ZoneRegion(str, ZoneOffset.UTC.getRules());
        }
        if (str.startsWith("UTC+") || str.startsWith("GMT+") || str.startsWith("UTC-") || str.startsWith("GMT-")) {
            ZoneOffset zoneOffsetOf = ZoneOffset.of(str.substring(3));
            if (zoneOffsetOf.getTotalSeconds() == 0) {
                return new ZoneRegion(str.substring(0, 3), zoneOffsetOf.getRules());
            }
            return new ZoneRegion(str.substring(0, 3) + zoneOffsetOf.getId(), zoneOffsetOf.getRules());
        }
        if (str.startsWith("UT+") || str.startsWith("UT-")) {
            ZoneOffset zoneOffsetOf2 = ZoneOffset.of(str.substring(2));
            if (zoneOffsetOf2.getTotalSeconds() == 0) {
                return new ZoneRegion("UT", zoneOffsetOf2.getRules());
            }
            return new ZoneRegion("UT" + zoneOffsetOf2.getId(), zoneOffsetOf2.getRules());
        }
        return ofId(str, false);
    }

    static ZoneRegion ofId(String str, boolean z) {
        ZoneRules rules;
        Jdk8Methods.requireNonNull(str, "zoneId");
        if (str.length() < 2 || !PATTERN.matcher(str).matches()) {
            throw new DateTimeException("Invalid ID for region-based ZoneId, invalid format: " + str);
        }
        try {
            rules = ZoneRulesProvider.getRules(str, true);
        } catch (ZoneRulesException e) {
            if (str.equals("GMT0")) {
                rules = ZoneOffset.UTC.getRules();
            } else {
                if (z) {
                    throw e;
                }
                rules = null;
            }
        }
        return new ZoneRegion(str, rules);
    }

    ZoneRegion(String str, ZoneRules zoneRules) {
        this.id = str;
        this.rules = zoneRules;
    }

    @Override // org.threeten.bp.ZoneId
    public String getId() {
        return this.id;
    }

    @Override // org.threeten.bp.ZoneId
    public ZoneRules getRules() {
        ZoneRules zoneRules = this.rules;
        return zoneRules != null ? zoneRules : ZoneRulesProvider.getRules(this.id, false);
    }

    private Object writeReplace() {
        return new Ser((byte) 7, this);
    }

    private Object readResolve() throws ObjectStreamException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    @Override // org.threeten.bp.ZoneId
    void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeByte(7);
        writeExternal(dataOutput);
    }

    void writeExternal(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.id);
    }

    static ZoneId readExternal(DataInput dataInput) throws IOException {
        return ofLenient(dataInput.readUTF());
    }
}
