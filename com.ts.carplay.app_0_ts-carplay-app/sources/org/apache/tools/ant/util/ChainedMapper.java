package org.apache.tools.ant.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ChainedMapper extends ContainerMapper {
    @Override // org.apache.tools.ant.util.FileNameMapper
    public String[] mapFileName(String sourceFileName) {
        List inputs = new ArrayList();
        ArrayList arrayList = new ArrayList();
        arrayList.add(sourceFileName);
        for (FileNameMapper mapper : getMappers()) {
            if (mapper != null) {
                inputs.clear();
                inputs.addAll(arrayList);
                arrayList.clear();
                Iterator it = inputs.iterator();
                while (it.hasNext()) {
                    String[] mapped = mapper.mapFileName((String) it.next());
                    if (mapped != null) {
                        arrayList.addAll(Arrays.asList(mapped));
                    }
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
