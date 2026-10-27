package org.apache.tools.ant.util;

import java.io.File;
import java.util.Vector;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceFactory;

/* JADX INFO: loaded from: classes.dex */
public class SourceFileScanner implements ResourceFactory {
    private File destDir;
    private FileUtils fileUtils = FileUtils.newFileUtils();
    protected Task task;

    public SourceFileScanner(Task task) {
        this.task = task;
    }

    public String[] restrict(String[] files, File srcDir, File destDir, FileNameMapper mapper) {
        return restrict(files, srcDir, destDir, mapper, this.fileUtils.getFileTimestampGranularity());
    }

    public String[] restrict(String[] files, File srcDir, File destDir, FileNameMapper mapper, long granularity) {
        this.destDir = destDir;
        Vector v = new Vector();
        for (int i = 0; i < files.length; i++) {
            File src = this.fileUtils.resolveFile(srcDir, files[i]);
            v.addElement(new Resource(files[i], src.exists(), src.lastModified(), src.isDirectory()));
        }
        Resource[] sourceresources = new Resource[v.size()];
        v.copyInto(sourceresources);
        Resource[] outofdate = ResourceUtils.selectOutOfDateSources(this.task, sourceresources, mapper, this, granularity);
        String[] result = new String[outofdate.length];
        for (int counter = 0; counter < outofdate.length; counter++) {
            result[counter] = outofdate[counter].getName();
        }
        return result;
    }

    public File[] restrictAsFiles(String[] files, File srcDir, File destDir, FileNameMapper mapper) {
        return restrictAsFiles(files, srcDir, destDir, mapper, this.fileUtils.getFileTimestampGranularity());
    }

    public File[] restrictAsFiles(String[] files, File srcDir, File destDir, FileNameMapper mapper, long granularity) {
        String[] res = restrict(files, srcDir, destDir, mapper, granularity);
        File[] result = new File[res.length];
        for (int i = 0; i < res.length; i++) {
            result[i] = new File(srcDir, res[i]);
        }
        return result;
    }

    @Override // org.apache.tools.ant.types.ResourceFactory
    public Resource getResource(String name) {
        File src = this.fileUtils.resolveFile(this.destDir, name);
        return new Resource(name, src.exists(), src.lastModified(), src.isDirectory());
    }
}
