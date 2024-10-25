package org.insset.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>CalculatorPourcentageService</code>.
 */
public interface CalculatorPourcentageServiceAsync {

    void calculateDiscount(double montant, double pourcentage, AsyncCallback<Double> callback);

}
