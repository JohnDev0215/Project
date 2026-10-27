package net.lingala.zip4j.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ZipModel implements Cloneable {
    private long end;
    private boolean isNestedZipFile;
    private boolean splitArchive;
    private long start;
    private File zipFile;
    private List<LocalFileHeader> localFileHeaders = new ArrayList();
    private List<DataDescriptor> dataDescriptors = new ArrayList();
    private ArchiveExtraDataRecord archiveExtraDataRecord = new ArchiveExtraDataRecord();
    private CentralDirectory centralDirectory = new CentralDirectory();
    private EndOfCentralDirectoryRecord endOfCentralDirectoryRecord = new EndOfCentralDirectoryRecord();
    private Zip64EndOfCentralDirectoryLocator zip64EndOfCentralDirectoryLocator = new Zip64EndOfCentralDirectoryLocator();
    private Zip64EndOfCentralDirectoryRecord zip64EndOfCentralDirectoryRecord = new Zip64EndOfCentralDirectoryRecord();
    private boolean isZip64Format = false;
    private long splitLength = -1;

    public Object clone() {
        return super.clone();
    }

    public ArchiveExtraDataRecord getArchiveExtraDataRecord() {
        return this.archiveExtraDataRecord;
    }

    public CentralDirectory getCentralDirectory() {
        return this.centralDirectory;
    }

    public List<DataDescriptor> getDataDescriptors() {
        return this.dataDescriptors;
    }

    public long getEnd() {
        return this.end;
    }

    public EndOfCentralDirectoryRecord getEndOfCentralDirectoryRecord() {
        return this.endOfCentralDirectoryRecord;
    }

    public List<LocalFileHeader> getLocalFileHeaders() {
        return this.localFileHeaders;
    }

    public long getSplitLength() {
        return this.splitLength;
    }

    public long getStart() {
        return this.start;
    }

    public Zip64EndOfCentralDirectoryLocator getZip64EndOfCentralDirectoryLocator() {
        return this.zip64EndOfCentralDirectoryLocator;
    }

    public Zip64EndOfCentralDirectoryRecord getZip64EndOfCentralDirectoryRecord() {
        return this.zip64EndOfCentralDirectoryRecord;
    }

    public File getZipFile() {
        return this.zipFile;
    }

    public boolean isNestedZipFile() {
        return this.isNestedZipFile;
    }

    public boolean isSplitArchive() {
        return this.splitArchive;
    }

    public boolean isZip64Format() {
        return this.isZip64Format;
    }

    public void setArchiveExtraDataRecord(ArchiveExtraDataRecord archiveExtraDataRecord) {
        this.archiveExtraDataRecord = archiveExtraDataRecord;
    }

    public void setCentralDirectory(CentralDirectory centralDirectory) {
        this.centralDirectory = centralDirectory;
    }

    public void setDataDescriptors(List<DataDescriptor> list) {
        this.dataDescriptors = list;
    }

    public void setEnd(long j) {
        this.end = j;
    }

    public void setEndOfCentralDirectoryRecord(EndOfCentralDirectoryRecord endOfCentralDirectoryRecord) {
        this.endOfCentralDirectoryRecord = endOfCentralDirectoryRecord;
    }

    public void setLocalFileHeaders(List<LocalFileHeader> list) {
        this.localFileHeaders = list;
    }

    public void setNestedZipFile(boolean z) {
        this.isNestedZipFile = z;
    }

    public void setSplitArchive(boolean z) {
        this.splitArchive = z;
    }

    public void setSplitLength(long j) {
        this.splitLength = j;
    }

    public void setStart(long j) {
        this.start = j;
    }

    public void setZip64EndOfCentralDirectoryLocator(Zip64EndOfCentralDirectoryLocator zip64EndOfCentralDirectoryLocator) {
        this.zip64EndOfCentralDirectoryLocator = zip64EndOfCentralDirectoryLocator;
    }

    public void setZip64EndOfCentralDirectoryRecord(Zip64EndOfCentralDirectoryRecord zip64EndOfCentralDirectoryRecord) {
        this.zip64EndOfCentralDirectoryRecord = zip64EndOfCentralDirectoryRecord;
    }

    public void setZip64Format(boolean z) {
        this.isZip64Format = z;
    }

    public void setZipFile(File file) {
        this.zipFile = file;
    }
}
