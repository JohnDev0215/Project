package org.apache.tools.ant.util;

import java.util.Arrays;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class CompositeMapper extends ContainerMapper {
    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String sourceFileName) {
        String[] mapped;
        HashSet results = new HashSet();
        for (FileNameMapper mapper : getMappers()) {
            if (mapper != null && (mapped = mapper.mapFileName(sourceFileName)) != null) {
                results.addAll(Arrays.asList(mapped));
            }
        }
        if (results.size() == 0) {
            return null;
        }
        return (String[]) results.toArray(new String[results.size()]);
    }
}
