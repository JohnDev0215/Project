package org.apache.tools.ant;

import java.io.Serializable;
import org.apache.tools.ant.util.FileUtils;
import org.xml.sax.Locator;

/* JADX INFO: loaded from: classes.dex */
public class Location implements Serializable {
    public static final Location UNKNOWN_LOCATION = new Location();
    private int columnNumber;
    private String fileName;
    private int lineNumber;

    private Location() {
        this(null, 0, 0);
    }

    public Location(String fileName) {
        this(fileName, 0, 0);
    }

    public Location(Locator loc) {
        this(loc.getSystemId(), loc.getLineNumber(), loc.getColumnNumber());
    }

    public Location(String fileName, int lineNumber, int columnNumber) {
        if (fileName != null && fileName.startsWith("file:")) {
            this.fileName = FileUtils.newFileUtils().fromURI(fileName);
        } else {
            this.fileName = fileName;
        }
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
    }

    public String getFileName() {
        return this.fileName;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        String str = this.fileName;
        if (str != null) {
            buf.append(str);
            if (this.lineNumber != 0) {
                buf.append(":");
                buf.append(this.lineNumber);
            }
            buf.append(": ");
        }
        return buf.toString();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || other.getClass() != getClass()) {
            return false;
        }
        return toString().equals(other.toString());
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
