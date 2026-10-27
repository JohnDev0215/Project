package net.lingala.zip4j.tasks;

import com.android.volley.BuildConfig;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.headers.HeaderUtil;
import net.lingala.zip4j.io.inputstream.SplitFileInputStream;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.UnzipParameters;
import net.lingala.zip4j.model.Zip4jConfig;
import net.lingala.zip4j.model.ZipModel;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.tasks.AsyncZipTask;
import net.lingala.zip4j.util.FileUtils;
import net.lingala.zip4j.util.InternalZipConstants;
import net.lingala.zip4j.util.UnzipUtil;
import net.lingala.zip4j.util.Zip4jUtil;

/* JADX INFO: loaded from: classes.dex */
public class ExtractFileTask extends AbstractExtractFileTask<ExtractFileTaskParameters> {
    private char[] password;
    private SplitFileInputStream splitInputStream;

    public static class ExtractFileTaskParameters extends AbstractZipTaskParameters {
        private String fileToExtract;
        private String newFileName;
        private String outputPath;

        public ExtractFileTaskParameters(String str, String str2, String str3, Zip4jConfig zip4jConfig) {
            super(zip4jConfig);
            this.outputPath = str;
            this.fileToExtract = str2;
            this.newFileName = str3;
        }
    }

    public ExtractFileTask(ZipModel zipModel, char[] cArr, UnzipParameters unzipParameters, AsyncZipTask.AsyncTaskParameters asyncTaskParameters) {
        super(zipModel, unzipParameters, asyncTaskParameters);
        this.password = cArr;
    }

    private ZipInputStream createZipInputStream(Zip4jConfig zip4jConfig) {
        this.splitInputStream = UnzipUtil.createSplitInputStream(getZipModel());
        return new ZipInputStream(this.splitInputStream, this.password, zip4jConfig);
    }

    private String determineNewFileName(String str, String str2, FileHeader fileHeader) {
        if (!Zip4jUtil.isStringNotNullAndNotEmpty(str) || !FileUtils.isZipEntryDirectory(str2)) {
            return str;
        }
        String str3 = InternalZipConstants.ZIP_FILE_SEPARATOR;
        if (str.endsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
            str3 = BuildConfig.FLAVOR;
        }
        return fileHeader.getFileName().replaceFirst(str2, str + str3);
    }

    private List<FileHeader> getFileHeadersToExtract(String str) throws ZipException {
        if (FileUtils.isZipEntryDirectory(str)) {
            return HeaderUtil.getFileHeadersUnderDirectory(getZipModel().getCentralDirectory().getFileHeaders(), str);
        }
        FileHeader fileHeader = HeaderUtil.getFileHeader(getZipModel(), str);
        if (fileHeader != null) {
            return Collections.singletonList(fileHeader);
        }
        throw new ZipException("No file found with name " + str + " in zip file", ZipException.Type.FILE_NOT_FOUND);
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public long calculateTotalWork(ExtractFileTaskParameters extractFileTaskParameters) {
        return HeaderUtil.getTotalUncompressedSizeOfAllFileHeaders(getFileHeadersToExtract(extractFileTaskParameters.fileToExtract));
    }

    @Override // net.lingala.zip4j.tasks.AsyncZipTask
    public void executeTask(ExtractFileTaskParameters extractFileTaskParameters, ProgressMonitor progressMonitor) throws IOException {
        List<FileHeader> fileHeadersToExtract = getFileHeadersToExtract(extractFileTaskParameters.fileToExtract);
        try {
            ZipInputStream zipInputStreamCreateZipInputStream = createZipInputStream(extractFileTaskParameters.zip4jConfig);
            try {
                byte[] bArr = new byte[extractFileTaskParameters.zip4jConfig.getBufferSize()];
                for (FileHeader fileHeader : fileHeadersToExtract) {
                    this.splitInputStream.prepareExtractionForFileHeader(fileHeader);
                    extractFile(zipInputStreamCreateZipInputStream, fileHeader, extractFileTaskParameters.outputPath, determineNewFileName(extractFileTaskParameters.newFileName, extractFileTaskParameters.fileToExtract, fileHeader), progressMonitor, bArr);
                }
                if (zipInputStreamCreateZipInputStream != null) {
                    zipInputStreamCreateZipInputStream.close();
                }
            } finally {
            }
        } finally {
            SplitFileInputStream splitFileInputStream = this.splitInputStream;
            if (splitFileInputStream != null) {
                splitFileInputStream.close();
            }
        }
    }
}
