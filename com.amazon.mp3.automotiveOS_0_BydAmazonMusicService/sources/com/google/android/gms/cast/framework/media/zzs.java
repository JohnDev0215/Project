package com.google.android.gms.cast.framework.media;

import androidx.annotation.VisibleForTesting;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.internal.CastUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@VisibleForTesting
public final class zzs extends RemoteMediaClient.Callback {
    final /* synthetic */ MediaQueue zza;

    public zzs(MediaQueue mediaQueue) {
        this.zza = mediaQueue;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void onStatusUpdated() {
        long jZzp = this.zza.zzp();
        MediaQueue mediaQueue = this.zza;
        if (jZzp != mediaQueue.zza) {
            mediaQueue.zza = jZzp;
            mediaQueue.zzl();
            MediaQueue mediaQueue2 = this.zza;
            if (mediaQueue2.zza != 0) {
                mediaQueue2.zzo();
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zza(int[] iArr) {
        List listZzf = CastUtils.zzf(iArr);
        if (this.zza.zzb.equals(listZzf)) {
            return;
        }
        this.zza.zzx();
        this.zza.zzd.evictAll();
        this.zza.zze.clear();
        MediaQueue mediaQueue = this.zza;
        mediaQueue.zzb = listZzf;
        MediaQueue.zzk(mediaQueue);
        this.zza.zzv();
        this.zza.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzb(int[] iArr, int i) {
        int size;
        int length = iArr.length;
        if (i == 0) {
            size = this.zza.zzb.size();
        } else {
            size = this.zza.zzc.get(i, -1);
            if (size == -1) {
                this.zza.zzo();
                return;
            }
        }
        this.zza.zzx();
        this.zza.zzb.addAll(size, CastUtils.zzf(iArr));
        MediaQueue.zzk(this.zza);
        MediaQueue.zze(this.zza, size, length);
        this.zza.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzc(MediaQueueItem[] mediaQueueItemArr) {
        HashSet hashSet = new HashSet();
        this.zza.zze.clear();
        for (MediaQueueItem mediaQueueItem : mediaQueueItemArr) {
            int itemId = mediaQueueItem.getItemId();
            this.zza.zzd.put(Integer.valueOf(itemId), mediaQueueItem);
            int i = this.zza.zzc.get(itemId, -1);
            if (i == -1) {
                this.zza.zzo();
                return;
            }
            hashSet.add(Integer.valueOf(i));
        }
        Iterator it = this.zza.zze.iterator();
        while (it.hasNext()) {
            int i2 = this.zza.zzc.get(((Integer) it.next()).intValue(), -1);
            if (i2 != -1) {
                hashSet.add(Integer.valueOf(i2));
            }
        }
        this.zza.zze.clear();
        ArrayList arrayList = new ArrayList(hashSet);
        Collections.sort(arrayList);
        this.zza.zzx();
        this.zza.zzw(CastUtils.zzi(arrayList));
        this.zza.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzd(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            this.zza.zzd.remove(Integer.valueOf(i));
            int i2 = this.zza.zzc.get(i, -1);
            if (i2 == -1) {
                this.zza.zzo();
                return;
            } else {
                this.zza.zzc.delete(i);
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Collections.sort(arrayList);
        this.zza.zzx();
        this.zza.zzb.removeAll(CastUtils.zzf(iArr));
        MediaQueue.zzk(this.zza);
        MediaQueue.zzf(this.zza, CastUtils.zzi(arrayList));
        this.zza.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zze(List list, List list2, int i) {
        int size;
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            size = this.zza.zzb.size();
        } else if (list2.isEmpty()) {
            this.zza.zzg.w("Received a Queue Reordered message with an empty reordered items IDs list.", new Object[0]);
            size = -1;
        } else {
            size = this.zza.zzc.get(i, -1);
            if (size == -1) {
                size = this.zza.zzc.get(((Integer) list2.get(0)).intValue(), -1);
            }
        }
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            int i2 = this.zza.zzc.get(((Integer) it.next()).intValue(), -1);
            if (i2 == -1) {
                this.zza.zzo();
                return;
            }
            arrayList.add(Integer.valueOf(i2));
        }
        this.zza.zzx();
        MediaQueue mediaQueue = this.zza;
        mediaQueue.zzb = list;
        MediaQueue.zzk(mediaQueue);
        MediaQueue.zzg(this.zza, arrayList, size);
        this.zza.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzf(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            this.zza.zzd.remove(Integer.valueOf(i));
            int i2 = this.zza.zzc.get(i, -1);
            if (i2 == -1) {
                this.zza.zzo();
                return;
            }
            arrayList.add(Integer.valueOf(i2));
        }
        Collections.sort(arrayList);
        this.zza.zzx();
        this.zza.zzw(CastUtils.zzi(arrayList));
        this.zza.zzu();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Callback
    public final void zzg() {
        this.zza.zzo();
    }
}
