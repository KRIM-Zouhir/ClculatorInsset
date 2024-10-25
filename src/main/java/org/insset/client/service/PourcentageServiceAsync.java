package org.insset.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>CalculatorPourcentageService</code>.
 */
public interface PourcentageServiceAsync {

    void calculateDiscount(double montant, double pourcentage, AsyncCallback<Double> callback);

}
