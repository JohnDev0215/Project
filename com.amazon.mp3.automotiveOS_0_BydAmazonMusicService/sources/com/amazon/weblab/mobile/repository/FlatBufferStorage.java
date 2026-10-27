package com.amazon.weblab.mobile.repository;

import com.amazon.weblab.mobile.model.FlatBufferTreatmentAssignment;
import com.amazon.weblab.mobile.model.SessionInfo;
import com.amazon.weblab.mobile.model.TreatmentAssignment;
import com.amazon.weblab.mobile.model.flatbuffer.Assignment;
import com.amazon.weblab.mobile.model.flatbuffer.Assignments;
import com.google.flatbuffers.FlatBufferBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;

/* JADX INFO: loaded from: classes5.dex */
public class FlatBufferStorage extends FileStorage {
    @Override // com.amazon.weblab.mobile.repository.FileStorage, com.amazon.weblab.mobile.repository.IStorage
    public /* bridge */ /* synthetic */ boolean hasBackup() {
        return super.hasBackup();
    }

    public FlatBufferStorage(String str, String str2) {
        super(str, str2);
    }

    @Override // com.amazon.weblab.mobile.repository.FileStorage, com.amazon.weblab.mobile.repository.IStorage
    public void writeBackup(StorageEntity storageEntity) throws IOException, RuntimeException {
        if (storageEntity == null) {
            throw new IllegalArgumentException("data can't be null");
        }
        int i = 0;
        FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(0);
        int[] iArr = new int[storageEntity.getTreatments().size()];
        storageEntity.getTreatments().entrySet().iterator();
        for (TreatmentAssignment treatmentAssignment : storageEntity.getTreatments().values()) {
            int iCreateString = flatBufferBuilder.createString(treatmentAssignment.getWeblabName());
            int iCreateString2 = flatBufferBuilder.createString(treatmentAssignment.getVersion());
            int iCreateString3 = flatBufferBuilder.createString(treatmentAssignment.getTreatment());
            Assignment.startAssignment(flatBufferBuilder);
            Assignment.addName(flatBufferBuilder, iCreateString);
            Assignment.addVersion(flatBufferBuilder, iCreateString2);
            Assignment.addTreatment(flatBufferBuilder, iCreateString3);
            Assignment.addCacheInsertionSecs(flatBufferBuilder, treatmentAssignment.getKeepInCacheDateInMillis());
            Assignment.addCanTrigger(flatBufferBuilder, treatmentAssignment.canTrigger());
            Assignment.addSampleTrigger(flatBufferBuilder, true);
            Assignment.addIsLocked(flatBufferBuilder, treatmentAssignment.isLocked());
            iArr[i] = Assignment.endAssignment(flatBufferBuilder);
            i++;
        }
        flatBufferBuilder.finish(Assignments.createAssignments(flatBufferBuilder, flatBufferBuilder.createString(storageEntity.getApplicationVersion()), flatBufferBuilder.createString(storageEntity.getSessionInfo().getSessionId()), flatBufferBuilder.createString(storageEntity.getSessionInfo().getMarketplaceId()), Assignments.createTreatmentAssignmentsVector(flatBufferBuilder, iArr)));
        ByteBuffer byteBufferDataBuffer = flatBufferBuilder.dataBuffer();
        File file = new File(this.mDirectory, this.mFile.getName() + System.currentTimeMillis() + ".tmp");
        FileChannel channel = new FileOutputStream(file).getChannel();
        try {
            channel.write(byteBufferDataBuffer);
            if (!file.renameTo(this.mFile)) {
                throw new IOException("Cannot rename the temporary file to backup file.");
            }
            channel.close();
        } finally {
        }
    }

    @Override // com.amazon.weblab.mobile.repository.FileStorage, com.amazon.weblab.mobile.repository.IStorage
    public StorageEntity readBackup() throws IOException, RuntimeException {
        if (!hasBackup()) {
            throw new IOException();
        }
        try {
            return loadStorageEntityFromFlatBuffers();
        } catch (IndexOutOfBoundsException unused) {
            throw new IOException("Error while reading data in flatbuffer format");
        }
    }

    private StorageEntity loadStorageEntityFromFlatBuffers() throws Throwable {
        FileChannel channel;
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.mFile, "r");
        try {
            channel = randomAccessFile.getChannel();
        } catch (Throwable th) {
            th = th;
            channel = null;
        }
        try {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) channel.size());
            channel.read(byteBufferAllocate);
            byteBufferAllocate.flip();
            Assignments rootAsAssignments = Assignments.getRootAsAssignments(byteBufferAllocate);
            HashMap map = new HashMap();
            for (int i = 0; i < rootAsAssignments.treatmentAssignmentsLength(); i++) {
                Assignment assignmentTreatmentAssignments = rootAsAssignments.treatmentAssignments(i);
                map.put(assignmentTreatmentAssignments.name(), new FlatBufferTreatmentAssignment(assignmentTreatmentAssignments));
            }
            StorageEntity storageEntity = new StorageEntity(rootAsAssignments.appVersion(), new SessionInfo(rootAsAssignments.sessionId(), rootAsAssignments.marketplaceId()), map);
            channel.close();
            randomAccessFile.close();
            return storageEntity;
        } catch (Throwable th2) {
            th = th2;
            if (channel != null) {
                channel.close();
            }
            randomAccessFile.close();
            throw th;
        }
    }
}
