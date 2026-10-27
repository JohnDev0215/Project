package org.apache.tools.ant.types.selectors.modifiedselector;

import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: classes.dex */
public class HashvalueAlgorithm implements Algorithm {
    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Algorithm
    public boolean isValid() {
        return true;
    }

    @Override // org.apache.tools.ant.types.selectors.modifiedselector.Algorithm
    public String getValue(File file) {
        try {
            if (!file.canRead()) {
                return null;
            }
            FileInputStream fis = new FileInputStream(file);
            byte[] content = new byte[fis.available()];
            fis.read(content);
            fis.close();
            String s = new String(content);
            int hash = s.hashCode();
            return Integer.toString(hash);
        } catch (Exception e) {
            return null;
        }
    }

    public String toString() {
        return "HashvalueAlgorithm";
    }
}
