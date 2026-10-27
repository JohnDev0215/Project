package ts.car.service.persistence;

import ts.car.service.IPlatformService;

/* JADX INFO: loaded from: classes2.dex */
public interface IPersistenceAdminManager extends IPlatformService {
    boolean exportConfig(String str, String str2) throws IllegalArgumentException;

    boolean importConfig(String str, String str2) throws IllegalArgumentException;

    boolean resetConfig(String str, int i) throws IllegalArgumentException;

    boolean updateConfig(boolean z, boolean z2, String str) throws IllegalArgumentException;
}
