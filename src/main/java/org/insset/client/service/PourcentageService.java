package org.insset.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the percentage discount calculation RPC service.
 */
@RemoteServiceRelativePath("pourcentage")
public interface PourcentageService extends RemoteService {
    
    double calculateDiscount(double montant, double pourcentage) throws IllegalArgumentException;

}
