package org.apache.tools.ant.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.tools.ant.types.Mapper;

/* JADX INFO: loaded from: classes.dex */
public abstract class ContainerMapper implements FileNameMapper {
    private List mappers = new ArrayList();

    public void addConfiguredMapper(Mapper mapper) {
        add(mapper.getImplementation());
    }

    public synchronized void add(FileNameMapper fileNameMapper) {
        if (this != fileNameMapper) {
            if (!(fileNameMapper instanceof ContainerMapper) || !((ContainerMapper) fileNameMapper).contains(this)) {
                this.mappers.add(fileNameMapper);
            }
        }
        throw new IllegalArgumentException("Circular mapper containment condition detected");
    }

    protected synchronized boolean contains(FileNameMapper fileNameMapper) {
        boolean foundit;
        foundit = false;
        Iterator iter = this.mappers.iterator();
        while (iter.hasNext() && !foundit) {
            FileNameMapper next = (FileNameMapper) iter.next();
            foundit |= next == fileNameMapper || ((next instanceof ContainerMapper) && ((ContainerMapper) next).contains(fileNameMapper));
        }
        return foundit;
    }

    public synchronized List getMappers() {
        return Collections.unmodifiableList(this.mappers);
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setFrom(String ignore) {
    }

    @Override // org.apache.tools.ant.util.FileNameMapper
    public void setTo(String ignore) {
    }
}
