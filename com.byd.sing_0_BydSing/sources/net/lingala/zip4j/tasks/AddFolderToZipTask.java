package net.lingala.zip4j.tasks;

import java.io.File;
import java.io.IOException;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderWriter;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class AddFolderToZipTask extends AbstractAddFileToZipTask<AddFolderToZipTaskParameters> {

    public static class AddFolderToZipTaskParameters extends AbstractZipTaskParameters {
        private final File folderToAdd;
        private final ZipParameters zipParameters;

        public AddFolderToZipTaskParameters(File file, ZipParameters zipParameters, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.folderToAdd = file;
            this.zipParameters = zipParameters;
        }
    }

    public AddFolderToZipTask(ZipModel zipModel, char[] cArr, HeaderWriter headerWriter, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, cArr, headerWriter, asyncTaskParameters);
    }

    private List<File> getFilesToAdd(AddFolderToZipTaskParameters addFolderToZipTaskParameters) throws ZipException {
        List<File> filesInDirectoryRecursive = FileUtils.getFilesInDirectoryRecursive(addFolderToZipTaskParameters.folderToAdd, addFolderToZipTaskParameters.zipParameters);
        if (addFolderToZipTaskParameters.zipParameters.isIncludeRootFolder()) {
            filesInDirectoryRecursive.add(addFolderToZipTaskParameters.folderToAdd);
        }
        return filesInDirectoryRecursive;
    }

    private void setDefaultFolderPath(AddFolderToZipTaskParameters addFolderToZipTaskParameters) throws IOException {
        File file = addFolderToZipTaskParameters.folderToAdd;
        String canonicalPath = (!addFolderToZipTaskParameters.zipParameters.isIncludeRootFolder() || file.getCanonicalFile().getParentFile() == null) ? file.getCanonicalPath() : file.getCanonicalFile().getParentFile().getCanonicalPath();
        addFolderToZipTaskParameters.zipParameters.setDefaultFolderPath(canonicalPath);
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(AddFolderToZipTaskParameters addFolderToZipTaskParameters) throws ZipException {
        List<File> filesToAdd = getFilesToAdd(addFolderToZipTaskParameters);
        if (addFolderToZipTaskParameters.zipParameters.isIncludeRootFolder()) {
            filesToAdd.add(addFolderToZipTaskParameters.folderToAdd);
        }
        return calculateWorkForFiles(filesToAdd, addFolderToZipTaskParameters.zipParameters);
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(AddFolderToZipTaskParameters addFolderToZipTaskParameters, ProgressMonitor progressMonitor) throws IOException {
        List<File> filesToAdd = getFilesToAdd(addFolderToZipTaskParameters);
        setDefaultFolderPath(addFolderToZipTaskParameters);
        addFilesToZip(filesToAdd, progressMonitor, addFolderToZipTaskParameters.zipParameters, addFolderToZipTaskParameters.zip4jConfig);
    }
}
