package org.codehaus.stax2.ri;

import javax.xml.stream.Location;
import org.codehaus.stax2.XMLStreamLocation2;

/* JADX INFO: loaded from: classes7.dex */
public class Stax2LocationAdapter implements XMLStreamLocation2 {
    protected final Location mParentLocation;
    protected final Location mWrappedLocation;

    public Stax2LocationAdapter(Location location) {
        this(location, null);
    }

    public Stax2LocationAdapter(Location location, Location location2) {
        this.mWrappedLocation = location;
        this.mParentLocation = location2;
    }

    public int getCharacterOffset() {
        return this.mWrappedLocation.getCharacterOffset();
    }

    public int getColumnNumber() {
        return this.mWrappedLocation.getColumnNumber();
    }

    public int getLineNumber() {
        return this.mWrappedLocation.getLineNumber();
    }
}
